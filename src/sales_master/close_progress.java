package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

import org.json.JSONException;

import CafePro.Mainpage;

/**
 * Uploads the closed register(s) to the server and posts the accounting entries
 * (sales invoice header, JV, ledger), then restarts the application.
 *
 * What changed compared with the old version:
 *  - Everything runs in a SwingWorker (the window used to freeze and the bars never repainted).
 *  - The account ledger is summed by the database (SUM) instead of downloading every ledger row of an
 *    account over the network - this was one of the biggest costs on a remote server.
 *  - The whole server side of one register is ONE transaction: it is posted completely or not at all.
 *    Sequence numbers are read with SELECT ... FOR UPDATE so two counters cannot get the same number.
 *  - Inserts are batched, the chart of accounts / kit items are loaded once instead of per row, and the
 *    "flag as uploaded" updates are single UPDATE statements instead of one per row.
 *  - The two near-identical branches (all registers / single register) are merged.
 *  - Statements / result sets / connections are closed properly.
 */
public class close_progress {

	private JFrame frame;
	private JProgressBar progressBar;
	private JProgressBar progressBar_1;
	private JLabel lblStage;
	private String Version = "MR Ver_1.1.0";

	public String company;
	public String reg_name;
	public String username;
	public String store_name;

	/** text typed in the Close_sales "Remarks" box (copied on the UI thread) */
	private String closeRemarks = "";

	private static final String CONFIG_FILE = "C:/MasterRetails/config.properties";
	private static final String RESTART_CMD = "cmd /c start \"\" C:\\MasterRetails\\MasterRetails.jar";
	private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/** order in which close-item paycodes are matched to the 15 paycode slots (slot 1 = default/cash) */
	private static final int[] SLOT_ORDER = { 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 4, 3, 2 };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					close_progress window = new close_progress("mr_pro", "S0102", "SHABEER", "S01");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * The throws clause is kept so existing callers still compile; nothing is thrown from here any more,
	 * errors are reported by the background worker.
	 */
	public close_progress(String company, String reg_nme, String username, String store_name)
			throws JSONException, IOException, SQLException, ClassNotFoundException {
		initialize(company, reg_nme, username, store_name);
	}

	private void initialize(String companyname, String reg_nme, String user, String store) {
		frame = new JFrame(Version);
		java.net.URL iconUrl = this.getClass().getResource("/cafepro.png");
		if (iconUrl != null) {
			Image img = new ImageIcon(iconUrl).getImage();
			frame.setIconImage(img);
		}
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.setBounds(100, 100, 500, 150);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setAlwaysOnTop(true);

		company = companyname;
		reg_name = reg_nme;
		username = user;
		store_name = store;
		closeRemarks = Close_sales.textField != null ? Close_sales.textField.getText() : "";

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setForeground(new Color(102, 51, 204));
		progressBar.setBounds(0, 125, 500, 25);
		frame.getContentPane().add(progressBar);
		progressBar.setBackground(new Color(255, 255, 255));
		progressBar.setMaximum(100);
		progressBar.setBorder(new LineBorder(Color.WHITE));

		progressBar_1 = new JProgressBar();
		progressBar_1.setBounds(0, 91, 500, 26);
		frame.getContentPane().add(progressBar_1);
		progressBar_1.setStringPainted(true);
		progressBar_1.setForeground(new Color(102, 51, 204));
		progressBar_1.setBackground(new Color(255, 255, 255));
		progressBar_1.setMaximum(100);
		progressBar_1.setBorder(new LineBorder(Color.WHITE));

		JLabel lblUploadingData = new JLabel("Updating Data To Server .....");
		lblUploadingData.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUploadingData.setBounds(10, 8, 480, 16);
		frame.getContentPane().add(lblUploadingData);

		lblStage = new JLabel("Processing ......");
		lblStage.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStage.setBounds(10, 32, 480, 16);
		frame.getContentPane().add(lblStage);

		JLabel lblPleaseWait = new JLabel("Please Wait ....");
		lblPleaseWait.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPleaseWait.setBounds(10, 56, 194, 16);
		frame.getContentPane().add(lblPleaseWait);

		frame.setVisible(true);
		new UploadWorker().execute();
	}

	// ------------------------------------------------------------------------------------------
	// Worker
	// ------------------------------------------------------------------------------------------

	private static class Prog {
		final int overall;
		final int item;
		final String message;

		Prog(int overall, int item, String message) {
			this.overall = overall;
			this.item = item;
			this.message = message;
		}
	}

	private static class CloseItem {
		String paycode;
		BigDecimal sales;
		BigDecimal exRate;
	}

	/** Collects the three accounting tables' rows and keeps a running ledger balance per account. */
	private static class Posting {
		final Connection server;
		final String db;
		final String saleSeq;
		final String jvSeq;
		final String today;
		final String time;
		final List<Object[]> detls = new ArrayList<Object[]>();
		final List<Object[]> jvd = new ArrayList<Object[]>();
		final List<Object[]> ledg = new ArrayList<Object[]>();
		private final Map<String, BigDecimal> ledger = new HashMap<String, BigDecimal>();

		Posting(Connection server, String db, String saleSeq, String jvSeq, String today, String time) {
			this.server = server;
			this.db = db;
			this.saleSeq = saleSeq;
			this.jvSeq = jvSeq;
			this.today = today;
			this.time = time;
		}

		private BigDecimal base(String ac) throws SQLException {
			BigDecimal b = ledger.get(ac);
			if (b == null) {
				// summed by the database - no ledger rows travel over the network
				BigDecimal[] s = queryDecimals(server, "SELECT IFNULL(SUM(dr_amt),0), IFNULL(SUM(cr_amt),0) FROM "
						+ db + "mr_acc_ledger WHERE ac_code=?", 2, ac);
				b = s[0].subtract(s[1]);
			}
			return b;
		}

		/** @param delta change applied to the running (debit - credit) balance of the account */
		void line(String type, String code, String name, BigDecimal dr, BigDecimal cr, String remark,
				BigDecimal delta) throws SQLException {
			BigDecimal bal = base(code).add(delta);
			ledger.put(code, bal);
			detls.add(new Object[] { saleSeq, today, time, type, code, name, dr, cr, remark, today, today });
			jvd.add(new Object[] { jvSeq, jvSeq, code, name, dr, cr, saleSeq, saleSeq, today, today, "OPEN", today });
			ledg.add(new Object[] { jvSeq, today, today, code, dr, cr, bal, saleSeq, saleSeq, "SA", today, today,
					"OPEN" });
		}
	}

	private class UploadWorker extends SwingWorker<Void, Prog> {
		private final StringBuilder doneRegs = new StringBuilder();
		private Map<String, List<String[]>> kits;

		private void step(int idx, int n, int stage, int itemPct, String msg) {
			publish(new Prog(Math.min(100, (idx * 100 + stage * 20) / n), itemPct, msg));
		}

		@Override
		protected Void doInBackground() throws Exception {
			final boolean all = "1".equals(Close_sales.register_all_invoice);
			final boolean bo = "BO".equals(readCounterType());
			final String db = company + ".";

			Connection local = Dbsetup.local_server.getConnection();
			Connection server = Dbsetup.server.getConnection();
			try {
				if (server == null) {
					throw new SQLException("Cannot connect to the server");
				}
				List<String> regs;
				if (all) {
					regs = queryList(local, "SELECT reg_num FROM " + db
							+ "mr_pos_settlement_history WHERE type!='VOID' AND status='0' GROUP BY reg_num ORDER BY reg_num");
				} else {
					regs = new ArrayList<String>(Collections.singletonList(reg_name));
				}
				for (int i = 0; i < regs.size(); i++) {
					uploadRegister(local, server, bo, regs.get(i), i, regs.size());
					doneRegs.append(regs.get(i)).append(' ');
				}
				publish(new Prog(100, 100, "Finishing"));
			} finally {
				closeQuietly(server);
				closeQuietly(local);
			}
			updateLoginControls(all);
			return null;
		}

		@Override
		protected void process(List<Prog> chunks) {
			Prog p = chunks.get(chunks.size() - 1);
			progressBar.setValue(p.overall);
			progressBar_1.setValue(p.item);
			if (p.message != null) {
				lblStage.setText(p.message);
			}
		}

		@Override
		protected void done() {
			try {
				get();
				JOptionPane.showMessageDialog(frame,
						"<html>Data Uploaded Successfully<br> „  Õ„Ì· «·»Ì«‰«  »‰Ã«Õ</html>");
				JOptionPane.showMessageDialog(frame,
						"<html>Counter Closed Successfully<br> „ ≈€·«ﬁ «·⁄œ«œ »‰Ã«Õ</html>");
				frame.dispose();
				if (Close_sales.frame != null) {
					Close_sales.frame.setVisible(false);
				}
				Runtime.getRuntime().exec(RESTART_CMD);
				System.exit(0);
			} catch (ExecutionException ex) {
				Throwable c = ex.getCause();
				c.printStackTrace();
				frame.dispose();
				String uploaded = doneRegs.length() == 0 ? "none" : doneRegs.toString().trim();
				JOptionPane.showMessageDialog(Mainpage.frame,
						"<html>Upload to the server failed. The data is still pending and was not lost.<br>"
								+ "Registers already uploaded: " + uploaded + "<br><br>" + String.valueOf(c.getMessage())
								+ "</html>",
						"Exception error", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				ex.printStackTrace();
				frame.dispose();
				JOptionPane.showMessageDialog(Mainpage.frame, String.valueOf(ex.getMessage()), "Exception error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		// --------------------------------------------------------------------------------------
		// One register
		// --------------------------------------------------------------------------------------

		private void uploadRegister(Connection local, Connection server, boolean bo, String reg, int idx, int n)
				throws Exception {
			final String db = company + ".";
			final String today = LocalDate.now().format(DATE);
			final String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

			step(idx, n, 0, 0, "Reading closing data (" + reg + ")");

			// ---------------- 1. data from the LOCAL database ----------------

			// NOTE: kept exactly as the old code: only the FIRST (created_date, paycode) group is used here.
			BigDecimal[] first = queryDecimals(local,
					"SELECT SUM(sales_amt*ex_rate), SUM(sale_amt), SUM(vat_amt) FROM " + db
							+ "mr_close_pay_list_history_items WHERE ref_reg_num=? AND status='0' GROUP BY created_date, paycode ORDER BY created_date, paycode LIMIT 1",
					3, reg);
			final BigDecimal netSale = first[0];
			final BigDecimal vatTtl = first[2];

			String userId = queryString(local, "SELECT id FROM " + db + "mr_users WHERE user_name=? LIMIT 1",
					username);

			String[] payCode = new String[16];
			Arrays.fill(payCode, "");
			StringBuilder pc = new StringBuilder();
			for (int i = 1; i <= 15; i++) {
				pc.append(i > 1 ? "," : "").append("pay_code").append(i);
			}
			List<String[]> pcRows = queryRows(local, "SELECT " + pc + " FROM " + db
					+ "mr_close_pay_list_history WHERE ref_reg_num=? AND status='0' LIMIT 1", 15, reg);
			if (!pcRows.isEmpty()) {
				for (int i = 1; i <= 15; i++) {
					payCode[i] = pcRows.get(0)[i - 1];
				}
			}

			BigDecimal newCost = queryDecimals(local,
					"SELECT IFNULL(SUM(i.item_cost*i.qty),0) FROM " + db + "mr_pos_settlement_history h INNER JOIN "
							+ db + "mr_pos_settlement_history_items i ON h.reg_num=i.ref_reg_num AND h.receipt_no=i.ref_receipt_num"
							+ " WHERE i.ref_reg_num=? AND h.type!='VOID' AND i.status='0'",
					1, reg)[0];
			BigDecimal[] sv = queryDecimals(local, "SELECT IFNULL(SUM(total),0), IFNULL(SUM(vat),0) FROM " + db
					+ "mr_pos_settlement_history WHERE reg_num=? AND status='0' AND type!='VOID'", 2, reg);
			final BigDecimal saleTtl = sv[0];
			final BigDecimal vatVal = sv[1];

			List<CloseItem> closeItems = new ArrayList<CloseItem>();
			for (String[] r : queryRows(local, "SELECT paycode, sales_amt, ex_rate FROM " + db
					+ "mr_close_pay_list_history_items WHERE ref_reg_num=? AND status='0'", 3, reg)) {
				CloseItem ci = new CloseItem();
				ci.paycode = r[0];
				ci.sales = dec(r[1]);
				ci.exRate = dec(r[2]);
				closeItems.add(ci);
			}

			// ---------------- 2. everything on the SERVER in ONE transaction ----------------
			server.setAutoCommit(false);
			final String saleSeq;
			final String jvSeq;
			try {
				saleSeq = nextSequence(server, db, "tran_name='sales invoice'", false);
				jvSeq = nextSequence(server, db, "tran_orgin='JV'", true);

				// chart of accounts: loaded once
				Map<String, String> codeToName = new HashMap<String, String>();
				Map<String, String[]> byName = new HashMap<String, String[]>(); // name -> {code, name, type}
				for (String[] r : queryRows(server,
						"SELECT acc_code, acc_name, acc_type FROM " + db + "mr_chart_accnt_mstr WHERE acc_store=?", 3,
						store_name)) {
					if (r[0] != null && !codeToName.containsKey(r[0])) {
						codeToName.put(r[0], r[1]);
					}
					if (r[1] != null && !byName.containsKey(r[1])) {
						byName.put(r[1], new String[] { r[0], r[1], r[2] });
					}
				}

				String storeSaleAcc = nz(queryString(server,
						"SELECT store_sale_acc FROM " + db + "mr_store_accounts WHERE ref_store_id=? LIMIT 1",
						store_name));

				String[] payGl = new String[16];
				Arrays.fill(payGl, "");
				for (String[] r : queryRows(server,
						"SELECT paycode, gl_account FROM " + db + "mr_store_paycodes WHERE ref_store_id=?", 2,
						store_name)) {
					if (r[0] != null && !r[0].equals("")) {
						for (int i = 1; i <= 15; i++) {
							if (eq(r[0], payCode[i])) {
								payGl[i] = nz(r[1]);
							}
						}
					}
				}
				String[] payName = new String[16];
				for (int i = 1; i <= 15; i++) {
					payName[i] = payGl[i].equals("") ? "" : nz(codeToName.get(payGl[i]));
				}

				String taxOutput = "";
				String costSaleAcc = "";
				String invAcc = "";
				List<String[]> grp = queryRows(server, "SELECT tax_output, cost_sale_acc, inv_acc FROM " + db
						+ "mr_assign_account_group WHERE acc_store=? AND status='1' LIMIT 1", 3, store_name);
				if (!grp.isEmpty()) {
					taxOutput = nz(grp.get(0)[0]);
					costSaleAcc = nz(grp.get(0)[1]);
					invAcc = nz(grp.get(0)[2]);
				}
				String saleAcc = nz(codeToName.get(storeSaleAcc));
				String outputTax = nz(codeToName.get(taxOutput));
				String costSale = nz(codeToName.get(costSaleAcc));
				String invStock = nz(codeToName.get(invAcc));

				String branch = "";
				List<String[]> seg = queryRows(server,
						"SELECT segment, name FROM " + db + "mr_acc_segment WHERE store_id=? LIMIT 1", 2, store_name);
				if (!seg.isEmpty() && "Branch".equals(seg.get(0)[0])) {
					branch = nz(seg.get(0)[1]);
				}

				step(idx, n, 1, 0, "Posting accounting entries (" + reg + ")");

				// sales invoice header
				update(server, "INSERT INTO " + db
						+ "mr_accounts_trans_header (tran_seqencecode,tran_date,tran_time,tran_remark,tran_debit_amt,tran_credit_amt,tran_status,tran_user,tran_store,tran_orgin,created_date,modified_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
						saleSeq, today, time, "POS SALES CLOSING " + reg + " ON " + today, netSale, netSale, "OPEN",
						userId, store_name, "SA", today, today);

				// accounting lines
				Posting p = new Posting(server, db, saleSeq, jvSeq, today, time);
				p.line("ASSET", invAcc, invStock, BigDecimal.ZERO, newCost, "POS SALES CLOSING AT " + reg + " ON " + today,
						newCost.negate());
				// NOTE: balance sign for the cost-of-sales account kept as in the old code (debit line, balance minus)
				p.line("EXPENSE", costSaleAcc, costSale, newCost, BigDecimal.ZERO, "SOLD INVENTORY AT " + reg,
						newCost.negate());
				p.line("INCOME", storeSaleAcc, saleAcc, BigDecimal.ZERO, saleTtl, "SALES AT " + reg + " ON " + today,
						saleTtl.negate());
				p.line("LIABILITY", taxOutput, outputTax, BigDecimal.ZERO, vatVal,
						"VAT COLLECTED AT " + reg + " ON " + today, vatVal.negate());

				boolean cashUsed = false;
				BigDecimal cashAcc = BigDecimal.ZERO;
				String accNme1 = "";
				Map<String, String> arAcc = new HashMap<String, String>();
				for (CloseItem ci : closeItems) {
					if (ci.sales.signum() == 0) {
						continue;
					}
					String accNme;
					if ("CUSTOMERPAYMENTS".equals(ci.paycode)) {
						accNme = payName[2];
					} else {
						int slot = 1;
						for (int s : SLOT_ORDER) {
							if (eq(ci.paycode, payCode[s])) {
								slot = s;
								break;
							}
						}
						accNme = payName[slot];
						if (slot == 1) {
							accNme1 = payName[1];
						}
					}
					String[] acc = byName.get(accNme);
					if (acc == null) {
						continue;
					}
					if ("A/R".equals(ci.paycode)) {
						for (String[] inv : queryRows(local,
								"SELECT cust_code, total, vat FROM " + db
										+ "mr_pos_settlement_history WHERE type='A/R' AND store_id=? AND reg_num=? AND status='0'",
								3, store_name, reg)) {
							String cust = inv[0];
							if (!arAcc.containsKey(cust)) {
								arAcc.put(cust, queryString(server, "SELECT default_ar_acc FROM " + db
										+ "mr_customer_master WHERE status='1' AND cust_code=? LIMIT 1", cust));
							}
							String dflt = arAcc.get(cust);
							if (dflt == null) {
								continue; // customer not found
							}
							String dfltName = dflt.equals("") ? "" : nz(codeToName.get(dflt));
							BigDecimal totl = dec(inv[1]).add(dec(inv[2]));
							if (totl.signum() != 0) {
								p.line(acc[2], dflt, dfltName, totl, BigDecimal.ZERO,
										ci.paycode + " COLLECTED AT " + reg + " ON  " + today, totl);
							}
						}
					} else if ("CUSTOMERPAYMENTS".equals(ci.paycode)) {
						// nothing to post
					} else if ("VISA".equals(ci.paycode)) {
						p.line(acc[2], acc[0], acc[1], ci.sales, BigDecimal.ZERO,
								"CASH COLLECTED AT " + reg + " ON  " + today, ci.sales);
					} else {
						cashUsed = true;
						cashAcc = cashAcc.add(ci.sales.multiply(ci.exRate));
					}
				}
				if (cashUsed) {
					String[] acc = byName.get(accNme1);
					if (acc != null) {
						p.line(acc[2], acc[0], acc[1], cashAcc, BigDecimal.ZERO,
								"CASH COLLECTED AT " + reg + " ON  " + today, cashAcc);
					}
				}

				batch(server, "INSERT INTO " + db
						+ "mr_accounts_tran_detls (tran_seqence_code,tran_date,tran_time,tran_type,tran_accode,tran_acname,tran_debit_amt,tran_credit_amt,tran_remark,created_date,modified_date) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
						p.detls);

				update(server, "INSERT INTO " + db
						+ "mr_jv_header (`jv_num`,`date`,`branch`,`username`,`doc_num`,`remark`,`notes`,`store_id`,`created_date`,`modified_date`,`status`,`is_approved`,`approved_by`,`is_posted`,`posted_by`,`posted_date`,`approved_date`,jv_amt,vendor_cust_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
						jvSeq, today, branch, username, saleSeq, saleSeq, saleSeq, store_name, today, today, "Posted",
						"1", username, "1", username, today, today, saleTtl, closeRemarks);

				batch(server, "INSERT INTO " + db
						+ "mr_jv_details (jv_seq,`ref_jv_num`,`ac_code`,`ac_desc`,`dr_amt`,`cr_amt`,`note`,`remark`,`created_date`,`modified_date`,status,cheque_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
						p.jvd);

				batch(server, "INSERT INTO " + db
						+ "mr_acc_ledger (`jv_num`,`tran_date`,`post_date`,`ac_code`,`dr_amt`,`cr_amt`,`balance`,`notes`,`remark`,`source`,`created_date`,`modified_date`,status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
						p.ledg);

				// ---------------- history rows: local -> server ----------------
				step(idx, n, 2, 0, "Uploading history (" + reg + ")");
				if (!bo) {
					uploadHistory(local, server, db, reg, jvSeq);
				}

				// ---------------- items: kit stock + (BO) item history ----------------
				step(idx, n, 3, 0, "Updating items (" + reg + ")");
				processItems(local, server, bo, db, reg, saleSeq, idx, n);

				update(server, "INSERT INTO " + db
						+ "mr_sales_tax (`reg_num`,`sales_total`,`tax_amount`,`sale_date`,`remark`,`created_date`) VALUES (?,?,?,?,?,?)",
						reg, netSale, vatTtl, today, "sales closed by " + username, today);

				String nextNum = queryString(local,
						"SELECT next_num FROM " + db + "mr_register_setup WHERE register_number=? LIMIT 1", reg);
				if (nextNum != null) {
					update(server, "UPDATE " + db + "mr_register_setup SET next_num=? WHERE register_number=?",
							nextNum, reg);
				}

				server.commit();
			} catch (Exception ex) {
				try {
					server.rollback();
				} catch (SQLException ignore) {
					// nothing more we can do
				}
				throw ex;
			} finally {
				try {
					server.setAutoCommit(true);
				} catch (SQLException ignore) {
					// ignore
				}
			}

			// ---------------- 3. flag the local rows as uploaded ----------------
			step(idx, n, 4, 100, "Finishing register (" + reg + ")");
			local.setAutoCommit(false);
			try {
				flagLocalUploaded(local, db, reg, jvSeq);
				local.commit();
			} catch (Exception ex) {
				try {
					local.rollback();
				} catch (SQLException ignore) {
					// ignore
				}
				throw new SQLException("Register " + reg + " WAS posted on the server (" + saleSeq
						+ "), but flagging the local rows failed. Do not upload it again - contact support. "
						+ ex.getMessage(), ex);
			} finally {
				try {
					local.setAutoCommit(true);
				} catch (SQLException ignore) {
					// ignore
				}
			}
			step(idx, n, 5, 100, "Register uploaded (" + reg + ")");
		}

		// --------------------------------------------------------------------------------------

		private void uploadHistory(Connection local, Connection server, String db, String reg, String jvSeq)
				throws SQLException {
			List<String> hist = new ArrayList<String>(Arrays.asList("ref_id", "ref_reg_num", "username", "store",
					"computer", "date_time", "invoice_num", "total_sale_amt", "total_vat", "total_disc", "total_cost",
					"created_date", "close_date"));
			for (int i = 1; i <= 15; i++) {
				hist.add("pay_code" + i);
				hist.add("open_amt" + i);
				hist.add("sales_amt" + i);
				hist.add("total_amt" + i);
				hist.add("ex_rate" + i);
				hist.add("varience" + i);
			}
			copyToServer(local, server, db + "mr_close_pay_list_history", hist, "ref_reg_num=? AND status='0'",
					new Object[] { reg }, new String[] { "remarks", "status" }, new Object[] { jvSeq, "1" });

			copyToServer(local, server, db + "mr_close_pay_list_history_items",
					Arrays.asList("ref_reg_num", "date_time", "paycode", "openings", "sales_amt", "total_amt", "ex_rate",
							"settle_amt", "varience", "sale_amt", "vat_amt", "created_date"),
					"ref_reg_num=? AND status='0'", new Object[] { reg }, new String[] { "status", "jv_num" },
					new Object[] { "1", jvSeq });

			copyToServer(local, server, db + "mr_register_openings_history",
					Arrays.asList("reg_num", "store_name", "user_name", "open_receipt", "remarks", "date_time",
							"created_date"),
					"reg_num=? AND status='0'", new Object[] { reg }, new String[] { "status" }, new Object[] { "1" });

			copyToServer(local, server, db + "mr_register_open_items_history",
					Arrays.asList("ref_reg_num", "date_time", "paycode", "pay_desc", "amount", "created_date"),
					"ref_reg_num=? AND status='0'", new Object[] { reg }, new String[] { "status" },
					new Object[] { "1" });

			copyToServer(local, server, db + "mr_paylist",
					Arrays.asList("type", "reason_code", "reason", "amount", "ref_reg_no", "user", "status",
							"created_date", "time", "store_id"),
					"status='1' AND ref_reg_no=?", new Object[] { reg }, new String[0], new Object[0]);

			copyToServer(local, server, db + "mr_customer_payment",
					Arrays.asList("seq_num", "cust_code", "amount", "discount", "paycode", "remarks", "ref_reg_num",
							"status", "store_id", "username", "created_date", "time"),
					"status='1' AND ref_reg_num=?", new Object[] { reg }, new String[0], new Object[0]);
		}

		private void flagLocalUploaded(Connection local, String db, String reg, String jvSeq) throws SQLException {
			update(local, "UPDATE " + db + "mr_close_pay_list_history SET status='1', remarks=? WHERE ref_reg_num=? AND status='0'",
					jvSeq, reg);
			update(local, "UPDATE " + db + "mr_close_pay_list_history_items SET status='1', jv_num=? WHERE ref_reg_num=? AND status='0'",
					jvSeq, reg);
			update(local, "UPDATE " + db + "mr_register_openings_history SET status='1' WHERE reg_num=? AND status='0'", reg);
			update(local, "UPDATE " + db + "mr_register_open_items_history SET status='1' WHERE ref_reg_num=? AND status='0'", reg);
			update(local, "UPDATE " + db + "mr_paylist SET status='2' WHERE ref_reg_no=? AND status='1'", reg);
			update(local, "UPDATE " + db + "mr_customer_payment SET status='2' WHERE ref_reg_num=? AND status='1'", reg);
			update(local, "UPDATE " + db + "mr_pos_settlement_history SET status='1', jv_num=? WHERE reg_num=? AND status='0'",
					jvSeq, reg);
			update(local, "UPDATE " + db + "mr_pos_settlement_history_items SET status='1' WHERE ref_reg_num=? AND status='0'", reg);
			update(local, "UPDATE " + db + "mr_paycode_settlement_history SET status='1' WHERE ref_reg_num=? AND status='0'", reg);
		}

		private void processItems(Connection local, Connection server, boolean bo, String db, String reg,
				String saleSeq, int idx, int n) throws SQLException {
			final boolean doKits = "0".equals(Close_sales.assembly_of_item);
			if (!doKits && !bo) {
				return; // nothing to do for items
			}
			List<String[]> items = queryRows(local,
					"SELECT cust_name, cust_code, item_code, barcode, SUM(i.qty), unit, item_cost, unit_price, i.cf FROM "
							+ db + "mr_pos_settlement_history h INNER JOIN " + db
							+ "mr_pos_settlement_history_items i ON h.reg_num=i.ref_reg_num AND h.receipt_no=i.ref_receipt_num"
							+ " WHERE i.ref_reg_num=? AND i.status='0' GROUP BY item_code, unit",
					9, reg);
			if (items.isEmpty()) {
				return;
			}
			if (doKits && kits == null) {
				kits = new HashMap<String, List<String[]>>();
				for (String[] k : queryRows(server, "SELECT parent_itemcode, child_itemcode, child_barcode, sp, cost, qty FROM "
						+ db + "mr_kit_items", 6)) {
					List<String[]> l = kits.get(k[0]);
					if (l == null) {
						l = new ArrayList<String[]>();
						kits.put(k[0], l);
					}
					l.add(k);
				}
			}

			final String today = LocalDate.now().format(DATE);
			List<Object[]> hist = new ArrayList<Object[]>();
			for (int i = 0; i < items.size(); i++) {
				String[] r = items.get(i);
				String custName = r[0];
				String custCode = r[1];
				String itemcode = r[2];
				String barcode = r[3];
				BigDecimal qty = dec(r[4]);
				String unit = r[5];
				BigDecimal itemCost = dec(r[6]);
				BigDecimal unitPrice = dec(r[7]);
				String cf = r[8];

				if (i % 20 == 0) {
					step(idx, n, 3, (i * 100) / items.size(), "Updating items (" + reg + ")");
				}

				List<String[]> kit = doKits ? kits.get(itemcode) : null;
				if (kit != null) {
					for (String[] k : kit) {
						String child = k[1];
						BigDecimal kitQty = dec(k[5]);
						BigDecimal sp = dec(k[3]);
						BigDecimal cost = dec(k[4]);
						String tmpQty = queryString(server,
								"SELECT qty FROM " + db + "mr_tmp_item_master WHERE item_code=? LIMIT 1", child);
						if (tmpQty != null) {
							BigDecimal newQty = dec(tmpQty).subtract(qty);
							update(server, "UPDATE " + db + "mr_item_master SET total_qty=?, modified_date=? WHERE item_code=?",
									newQty, today, child);
							update(server, "UPDATE " + db + "mr_tmp_item_master SET qty=? WHERE item_code=?", newQty, child);
							if (bo) {
								hist.add(new Object[] { child, "SA", saleSeq, kitQty, unit, "1", cost,
										cost.multiply(kitQty), qty, sp, kitQty.multiply(sp), "KIT SALE", username, today,
										k[2], custCode, custName });
							}
						}
					}
					update(server, "UPDATE " + db + "mr_item_master SET total_qty='0', modified_date=? WHERE item_code=?",
							today, itemcode);
					update(server, "UPDATE " + db + "mr_tmp_item_master SET qty='0' WHERE item_code=?", itemcode);
				}

				if (bo) {
					hist.add(new Object[] { itemcode, "SA", saleSeq, qty, unit, cf, itemCost,
							itemCost.multiply(qty).multiply(dec(cf)), qty, unitPrice, unitPrice.multiply(qty),
							"Recieved " + saleSeq, username, today, barcode, custCode, custName });
				}
			}
			batch(server, "INSERT INTO " + db
					+ "mr_item_master_history (`itemcode`,`trans_type`,`trans_code`,`qty`,`unit`,`cf`,`cost`,`ttl_cost`,`tranQty`,`price`,`ttl_price`,`remark`,`user`,`created_date`,barcode,cust_id,cust_name) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					hist);
		}

		private void updateLoginControls(boolean all) {
			try (Connection lc = all ? Dbsetup.local_server.getConnection() : Dbsetup.dbconn.getConnection()) {
				// same connections as the old code: all-registers mode used the local server, single mode dbconn
				String sys = InetAddress.getLocalHost().getHostName();
				String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				update(lc, "UPDATE " + company
						+ ".mr_loggin_controls SET status='1', modified_date=? WHERE username=? AND status='0' AND device_name=?",
						now, username, sys);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// ------------------------------------------------------------------------------------------
	// Helpers
	// ------------------------------------------------------------------------------------------

	private String readCounterType() throws IOException {
		Properties props = new Properties();
		try (FileReader reader = new FileReader(new File(CONFIG_FILE))) {
			props.load(reader);
		}
		return props.getProperty("counter", "");
	}

	/**
	 * Reads the next number of a transaction sequence (locking the row) and stores the incremented value.
	 * @return the sequence value to use for this posting
	 */
	private static String nextSequence(Connection server, String db, String where, boolean setModified)
			throws SQLException {
		String cur = queryString(server,
				"SELECT tran_seq FROM " + db + "mr_transaction_seq WHERE " + where + " LIMIT 1 FOR UPDATE");
		if (cur == null) {
			throw new SQLException("Transaction sequence not found (" + where + ")");
		}
		String[] seq = cur.split("-");
		if (seq.length < 4) {
			throw new SQLException("Unexpected transaction sequence format: " + cur);
		}
		seq[1] = new SimpleDateFormat("yy").format(new Date());
		String used = seq[0] + "-" + seq[1] + "-" + seq[2] + "-" + seq[3];

		StringBuilder num = new StringBuilder(Integer.toString(Integer.parseInt(seq[3]) + 1));
		while (num.length() < 6) {
			num.insert(0, '0');
		}
		String next = seq[0] + "-" + seq[1] + "-" + seq[2] + "-" + num;
		if (setModified) {
			update(server, "UPDATE " + db + "mr_transaction_seq SET modified_date=?, tran_seq=? WHERE " + where,
					LocalDate.now().format(DATE), next);
		} else {
			update(server, "UPDATE " + db + "mr_transaction_seq SET tran_seq=? WHERE " + where, next);
		}
		return used;
	}

	/** Copies rows between two databases in batches (values travel as strings, NULL stays NULL). */
	private static void copyToServer(Connection local, Connection server, String table, List<String> copyCols,
			String where, Object[] whereParams, String[] extraCols, Object[] extraVals) throws SQLException {
		StringBuilder sel = new StringBuilder();
		StringBuilder all = new StringBuilder();
		for (int i = 0; i < copyCols.size(); i++) {
			sel.append(i > 0 ? "," : "").append('`').append(copyCols.get(i)).append('`');
		}
		all.append(sel);
		for (String c : extraCols) {
			all.append(",`").append(c).append('`');
		}
		String insert = "INSERT INTO " + table + " (" + all + ") VALUES (" + placeholders(copyCols.size() + extraCols.length) + ")";
		try (PreparedStatement ps = local.prepareStatement("SELECT " + sel + " FROM " + table + " WHERE " + where);
				PreparedStatement pi = server.prepareStatement(insert)) {
			bind(ps, whereParams);
			try (ResultSet rs = ps.executeQuery()) {
				int count = 0;
				while (rs.next()) {
					for (int i = 0; i < copyCols.size(); i++) {
						pi.setString(i + 1, rs.getString(i + 1));
					}
					for (int j = 0; j < extraVals.length; j++) {
						pi.setObject(copyCols.size() + j + 1, extraVals[j]);
					}
					pi.addBatch();
					if (++count % 500 == 0) {
						pi.executeBatch();
					}
				}
				pi.executeBatch();
			}
		}
	}

	private static void bind(PreparedStatement ps, Object... p) throws SQLException {
		for (int i = 0; i < p.length; i++) {
			ps.setObject(i + 1, p[i]);
		}
	}

	private static int update(Connection con, String sql, Object... p) throws SQLException {
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			bind(ps, p);
			return ps.executeUpdate();
		}
	}

	private static void batch(Connection con, String sql, List<Object[]> rows) throws SQLException {
		if (rows.isEmpty()) {
			return;
		}
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			int count = 0;
			for (Object[] r : rows) {
				bind(ps, r);
				ps.addBatch();
				if (++count % 500 == 0) {
					ps.executeBatch();
				}
			}
			ps.executeBatch();
		}
	}

	private static String queryString(Connection con, String sql, Object... p) throws SQLException {
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			bind(ps, p);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() ? rs.getString(1) : null;
			}
		}
	}

	private static List<String> queryList(Connection con, String sql, Object... p) throws SQLException {
		List<String> out = new ArrayList<String>();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			bind(ps, p);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					out.add(rs.getString(1));
				}
			}
		}
		return out;
	}

	private static List<String[]> queryRows(Connection con, String sql, int cols, Object... p) throws SQLException {
		List<String[]> out = new ArrayList<String[]>();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			bind(ps, p);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String[] r = new String[cols];
					for (int i = 0; i < cols; i++) {
						r[i] = rs.getString(i + 1);
					}
					out.add(r);
				}
			}
		}
		return out;
	}

	private static BigDecimal[] queryDecimals(Connection con, String sql, int n, Object... p) throws SQLException {
		BigDecimal[] out = new BigDecimal[n];
		Arrays.fill(out, BigDecimal.ZERO);
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			bind(ps, p);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					for (int i = 0; i < n; i++) {
						BigDecimal v = rs.getBigDecimal(i + 1);
						if (v != null) {
							out[i] = v;
						}
					}
				}
			}
		}
		return out;
	}

	private static String placeholders(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(i > 0 ? ",?" : "?");
		}
		return sb.toString();
	}

	private static BigDecimal dec(String s) {
		if (s == null || s.trim().isEmpty()) {
			return BigDecimal.ZERO;
		}
		try {
			return new BigDecimal(s.trim());
		} catch (NumberFormatException ex) {
			return BigDecimal.ZERO;
		}
	}

	private static String nz(String s) {
		return s == null ? "" : s;
	}

	private static boolean eq(String a, String b) {
		return a != null && a.equals(b);
	}

	private static void closeQuietly(Connection c) {
		if (c == null) {
			return;
		}
		try {
			c.setAutoCommit(true);
		} catch (SQLException ignore) {
			// ignore
		}
		try {
			c.close();
		} catch (SQLException ignore) {
			// ignore
		}
	}
}