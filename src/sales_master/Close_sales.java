package sales_master;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import CafePro.Mainpage;

import com.toedter.calendar.JDateChooser;

/**
 * Close sales / close register screen.
 *
 * What changed compared with the old version:
 *  - All the work runs in a SwingWorker (the window no longer freezes).
 *  - Row-by-row INSERT loops were replaced by INSERT ... SELECT, and the item-master
 *    update is one set-based UPDATE.
 *  - Totals are calculated with SQL SUM() instead of loading every invoice row into Java.
 *  - The three duplicated branches and the 8 copy-pasted paycode blocks were merged.
 *  - The close is split in two transactions: (1) build the closing rows, (2) move to history.
 *    Printing happens between them, so no DB locks are held while a printer is busy.
 *    If anything fails, the transaction is rolled back and nothing is half-moved.
 *  - Connections are always closed; the backup has a timeout.
 */
public class Close_sales {

	static JFrame frame;
	private JTextField textField_12;
	private boolean dot = false;
	private JTable table;
	static DefaultTableModel model;
	static JTextField textField;
	private JLabel reg_num;
	private JLabel label_3;
	private JLabel label_5;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_2;
	private String printer;
	private JDateChooser dateChooser;
	private JProgressBar progressBar;
	private JButton btnOk;
	private JButton btnExit;
	private volatile boolean busy = false;

	public static String display_port;
	public static String denominationPrint;
	public static String not_ready_msg1;
	public static String not_ready_msg2;
	private static String change_hold_type = "0";
	public static String assembly_of_item = "0";
	public static String register_all_invoice = "0";
	public static String registerAsWaiter = "0";
	public static String DonotPerformEod = "0";
	public static JLabel lblNewLabel;

	private static final String BACKUP_BAT = "C:\\MasterRetails\\backup\\backupbatch.bat";
	private static final String CONFIG_FILE = "C:/MasterRetails/config.properties";
	private static final int BACKUP_TIMEOUT_MINUTES = 30;
	/** Column in mr_pos_hold_invoice that stores the register number - change if yours differs. */
	private static final String HOLD_REG_COLUMN = "reg_num";

	/** history column index -> paycode (pay_code1..pay_code8 in mr_close_pay_list_history) */
	private static final String[] HISTORY_PAYCODES = { null, "CASH", "A/R", "STORE CREDIT", "VISA",
			"OMANI RIYAL", "SAUDI RIYAL", "US$", "TALABAT" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Close_sales window = new Close_sales("mr_pro", "S01", "SHABEER", null, "S0101", "2", "");
					Close_sales.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Close_sales(String company, String store_name, String username, String version, String reg_nme,
			String decimal, String language) {
		initialize(company, store_name, username, version, reg_nme, decimal, language);
	}

	// ------------------------------------------------------------------------------------------
	// UI
	// ------------------------------------------------------------------------------------------

	private void initialize(final String company, final String store_name, final String username,
			final String version, final String reg_nme, final String decimal, String language) {
		final boolean arabic = "Arabic".equals(language);

		frame = new JFrame(version);
		java.net.URL iconUrl = this.getClass().getResource("/cafepro.png");
		if (iconUrl != null) {
			Image img = new ImageIcon(iconUrl).getImage();
			frame.setIconImage(img);
		}
		frame.setBounds(100, 100, 792, 720);
		// same as HIDE_ON_CLOSE, but ignored while a close is running
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (busy) {
					return;
				}
				frame.setVisible(false);
				Mainpage.btnCloseSales.setEnabled(true);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		Date date1 = new Date();

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 776, 682);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 0, 205));
		progressBar.setBackground(new Color(192, 192, 192));
		progressBar.setStringPainted(true);
		progressBar.setBounds(0, 46, 416, 31);
		progressBar.setVisible(false);
		panel.add(progressBar);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(415, 77, 1, 605);
		panel.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 77, 776, 2);
		panel.add(separator_2);

		JButton btnDrawer = new JButton(arabic ? "œ—Ã «·‰ﬁÊœ" : "DRAWER");
		btnDrawer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openCashDrawer();
			}
		});
		btnDrawer.setBounds(415, 11, 105, 43);
		panel.add(btnDrawer);

		btnOk = new JButton(arabic ? "‰⁄„" : "OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				button_ok_click(company, reg_nme, username, store_name, decimal);
			}
		});
		btnOk.setBounds(540, 11, 105, 43);
		panel.add(btnOk);

		btnExit = new JButton(arabic ? "„Œ—Ã" : "EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (busy) {
					return;
				}
				frame.setVisible(false);
				Mainpage.btnCloseSales.setEnabled(true);
			}
		});
		btnExit.setBounds(670, 11, 105, 43);
		panel.add(btnExit);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(415, 299, 361, 2);
		panel.add(separator_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 77, 416, 605);
		panel.add(scrollPane);

		model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 1 || column == 3;
			}

			@Override
			public Class<?> getColumnClass(int column) {
				return column == 3 ? Double.class : String.class;
			}
		};
		table = new JTable(model);
		table.setFont(new Font("Serif", Font.BOLD, 15));
		table.setRowHeight(40);
		if (arabic) {
			model.addColumn("«·—ﬁ„ «· ”·”·Ì:");
			model.addColumn("—„“ «·œ›⁄");
			model.addColumn("Ê’›");
			model.addColumn("«·„»·€/ «·ﬂ„Ì…");
		} else {
			model.addColumn("Sl No:");
			model.addColumn("Paycode");
			model.addColumn("Description");
			model.addColumn("Amount/ Qty");
		}
		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(15);
			}
			if (i == 3) {
				column.setPreferredWidth(25);
			}
		}
		table.getColumnModel().getColumn(1).setCellRenderer(new ClientsTableButtonRenderer());
		table.getColumnModel().getColumn(1).setCellEditor(new ClientsTableRenderer(new JCheckBox()));
		scrollPane.setViewportView(table);

		// ---- number pad ----
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(477, 312, 196, 370);
		panel.add(panel_1);

		for (int d = 1; d <= 9; d++) {
			final String digit = String.valueOf(d);
			int col = (d - 1) % 3;
			int row = (d - 1) / 3;
			addPadButton(panel_1, digit, 3 + 63 * col, 53 + 63 * row, 30, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					click_btn(digit);
				}
			});
		}
		addPadButton(panel_1, "0", 3, 242, 30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click_btn("0");
			}
		});
		addPadButton(panel_1, "00", 66, 242, 24, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click_btn("00");
			}
		});
		addPadButton(panel_1, "000", 129, 242, 15, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click_btn("000");
			}
		});
		addPadButton(panel_1, "C", 3, 305, 30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_12.setText("");
			}
		});
		addPadButton(panel_1, "<<", 66, 305, 20, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String t = textField_12.getText();
				if (!t.isEmpty()) {
					textField_12.setText(t.substring(0, t.length() - 1));
				}
			}
		});
		addPadButton(panel_1, ".", 129, 305, 30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click_btn(".");
			}
		});

		textField_12 = new JTextField("");
		textField_12.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if (textField_12.getText().equals("")) {
					dot = false;
				}
				boolean allowed = Character.isDigit(vChar) || vChar == KeyEvent.VK_BACK_SPACE
						|| vChar == KeyEvent.VK_DELETE;
				if (!dot) {
					if (vChar == '.') {
						dot = true;
					} else if (!allowed) {
						e.consume();
					}
				} else if (!allowed) {
					e.consume();
				}
			}
		});
		textField_12.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLUE, null, null, null));
		textField_12.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_12.setForeground(Color.DARK_GRAY);
		textField_12.setFont(new Font("Tahoma", Font.PLAIN, 40));
		textField_12.setColumns(10);
		textField_12.setBackground(new Color(176, 224, 230));
		textField_12.setBounds(0, 0, 189, 50);
		panel_1.add(textField_12);

		// ---- info panel ----
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(425, 81, 341, 211);
		panel_2.setBackground(new Color(243, 229, 216));
		panel.add(panel_2);
		panel_2.setLayout(null);

		addLabel(panel_2, arabic ? "„·«ÕŸ«  :" : "Remarks :", 0, 181, 113, 22);
		addLabel(panel_2, arabic ? "≈€·«ﬁ —ﬁ„ «·≈Ì’«· :" : "Close Rept no. :", 0, 94, 113, 22);
		addLabel(panel_2, arabic ? "—ﬁ„ «·≈Ì’«· «·„› ÊÕ:" : "Open Rept no. :", 0, 65, 113, 22);
		addLabel(panel_2, arabic ? "„Õ· :" : "Store :", 201, 39, 48, 22);
		addLabel(panel_2, arabic ? "«·„” Œœ„/√„Ì‰ «·’‰œÊﬁ :" : "User/Cashier :", 0, 36, 90, 22);
		addLabel(panel_2, arabic ? " «—ÌŒ :" : "Date :", 201, 7, 40, 22);
		addLabel(panel_2, arabic ? "—ﬁ„ «· ”ÃÌ· :" : "Register Number :", 0, 7, 113, 22);
		addLabel(panel_2, arabic ? "Õ«·…" : "status", 0, 152, 56, 22);
		addLabel(panel_2, arabic ? "Õ«”Ê»" : "computer", 0, 123, 56, 22);
		addLabel(panel_2, arabic ? "⁄œœ «·›Ê« Ì— :" : "#of invoice : ", 201, 152, 77, 22);

		reg_num = addLabel(panel_2, reg_nme, 110, 7, 79, 22);
		addLabel(panel_2, username, 110, 36, 79, 22);
		addLabel(panel_2, store_name, 246, 42, 68, 16);
		label_3 = addLabel(panel_2, "", 110, 65, 212, 22);
		label_5 = addLabel(panel_2, "", 110, 94, 212, 22);

		String sys_name = "";
		try {
			sys_name = InetAddress.getLocalHost().getHostName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		lblNewLabel_2 = addLabel(panel_2, sys_name, 110, 123, 204, 22);

		textField = new JTextField();
		textField.setBounds(110, 181, 221, 22);
		panel_2.add(textField);
		textField.setColumns(10);

		addLabel(panel_2, "OPEN", 110, 152, 56, 22);
		lblNewLabel_4 = addLabel(panel_2, "", 276, 152, 41, 22);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setDate(date1);
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date today = new Date();
				dateChooser.getJCalendar().setMaxSelectableDate(today);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, -1);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				dateChooser.getJCalendar().setMinSelectableDate(cal.getTime());
				dateChooser.setDate(today);
			}
		});
		dateChooser.setBounds(237, 7, 100, 22);
		panel_2.add(dateChooser);

		JLabel lblCloseSales = new JLabel(arabic ? "≈€·«ﬁ «·„»Ì⁄« " : "Close Sales");
		lblCloseSales.setFont(new Font("Monospaced", Font.BOLD, 25));
		lblCloseSales.setBounds(0, 0, 229, 43);
		panel.add(lblCloseSales);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(425, 53, 341, 24);
		panel.add(lblNewLabel);

		get_register_name(company, reg_nme);
		get_store_validate_dt(company, store_name, username, reg_nme);
		get_full_paycode(company, reg_nme);

		if ("1".equals(denominationPrint)) {
			JButton btnDenom = new JButton("Denomination");
			btnDenom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new denomination(company, reg_nme, version);
				}
			});
			btnDenom.setBounds(262, 11, 125, 43);
			panel.add(btnDenom);
		}

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private JLabel addLabel(JPanel parent, String text, int x, int y, int w, int h) {
		JLabel l = new JLabel(text);
		l.setBounds(x, y, w, h);
		parent.add(l);
		return l;
	}

	private JButton addPadButton(JPanel parent, String text, int x, int y, int fontSize, ActionListener l) {
		JButton b = new JButton(text);
		b.setFont(new Font("Arial", Font.BOLD, fontSize));
		b.setBounds(x, y, 60, 60);
		b.addActionListener(l);
		parent.add(b);
		return b;
	}

	private void click_btn(String num) {
		textField_12.setText(textField_12.getText() + num);
	}

	private void openCashDrawer() {
		try {
			byte[] open = { 27, 112, 48, 55, 121 };
			PrintService pservice = PrintServiceLookup.lookupDefaultPrintService();
			if (pservice == null) {
				JOptionPane.showMessageDialog(frame, "No default printer found.");
				return;
			}
			DocPrintJob job = pservice.createPrintJob();
			Doc doc = new SimpleDoc(open, DocFlavor.BYTE_ARRAY.AUTOSENSE, null);
			PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
			job.print(doc, aset);
		} catch (PrintException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void setBusy(boolean b) {
		busy = b;
		btnOk.setEnabled(!b);
		btnExit.setEnabled(!b);
		if (b) {
			progressBar.setValue(0);
			progressBar.setVisible(true);
		}
	}

	// ------------------------------------------------------------------------------------------
	// OK button: collect everything from the UI (EDT), then run the close in a background worker
	// ------------------------------------------------------------------------------------------

	private void button_ok_click(String company, String reg_nme, String username, String store_name,
			String decimal) {
		if (busy) {
			return;
		}
		int answer = JOptionPane.showConfirmDialog(frame,
				"<html>Do you Want to Close Register<br>Â·  —Ìœ ≈€·«ﬁ «· ”ÃÌ·ø</html>", "WARNING",
				JOptionPane.YES_NO_OPTION);
		if (answer != JOptionPane.YES_OPTION) {
			return;
		}
		if (table.isEditing() && table.getCellEditor() != null) {
			table.getCellEditor().stopCellEditing();
		}

		CloseRequest req = new CloseRequest();
		req.company = company;
		req.db = company + ".";
		req.regName = reg_nme;
		req.username = username;
		req.storeName = store_name;
		try {
			req.scale = Integer.parseInt(decimal);
		} catch (NumberFormatException ex) {
			req.scale = 2;
		}
		Date chosen = dateChooser.getDate() != null ? dateChooser.getDate() : new Date();
		req.dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(chosen);
		req.today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		req.computer = lblNewLabel_2.getText();
		req.remarks = textField.getText();
		req.invoiceCount = lblNewLabel_4.getText();
		for (int i = 0; i < model.getRowCount(); i++) {
			Object code = model.getValueAt(i, 1);
			if (code == null || "STORE CREDIT".equals(code.toString())) {
				continue;
			}
			req.rows.add(new PayRow(code.toString(), toDecimal(model.getValueAt(i, 3))));
		}

		setBusy(true);
		new CloseWorker(req).execute();
	}

	// ------------------------------------------------------------------------------------------
	// Data holders
	// ------------------------------------------------------------------------------------------

	private static class PayRow {
		final String paycode;
		final BigDecimal entered;

		PayRow(String paycode, BigDecimal entered) {
			this.paycode = paycode;
			this.entered = entered;
		}
	}

	private static class CloseRequest {
		String company;
		String db;
		String regName;
		String username;
		String storeName;
		int scale;
		String dateTime;
		String today;
		String computer;
		String remarks;
		String invoiceCount;
		List<PayRow> rows = new ArrayList<PayRow>();
	}

	private static class RegTotals {
		BigDecimal sales = BigDecimal.ZERO;
		BigDecimal cash = BigDecimal.ZERO;
	}

	private enum Outcome {
		DONE, HOLD_INVOICES
	}

	private static class CloseResult {
		Outcome outcome = Outcome.DONE;
		String lastReg;
		boolean remoteAvailable;
		boolean backupOk = true;
	}

	private static class Step {
		final int percent;
		final String message;

		Step(int percent, String message) {
			this.percent = percent;
			this.message = message;
		}
	}

	// ------------------------------------------------------------------------------------------
	// Background worker: all DB / backup / printing work happens here
	// ------------------------------------------------------------------------------------------

	private class CloseWorker extends SwingWorker<CloseResult, Step> {
		private final CloseRequest r;

		CloseWorker(CloseRequest r) {
			this.r = r;
		}

		private void step(int percent, String message) {
			publish(new Step(Math.min(100, percent), message));
		}

		@Override
		protected CloseResult doInBackground() throws Exception {
			CloseResult res = new CloseResult();
			final String db = r.db;
			Connection con = Dbsetup.local_server.getConnection();
			try {
				// 0a. Read register mode first (needed by the hold-invoice check)
				boolean allInvoices = "1".equals(register_all_invoice);
				String multiRaw = "0";
				List<String> openRegs = new ArrayList<String>();
				if (allInvoices) {
					Properties props = new Properties();
					try (FileReader fr = new FileReader(new File(CONFIG_FILE))) {
						props.load(fr);
					}
					multiRaw = props.getProperty("multi_register", "0").trim();
					for (String s : props.getProperty("open_register", "").split(",")) {
						s = s.trim();
						if (!s.isEmpty()) {
							openRegs.add(s);
						}
					}
				}
				boolean multi = "1".equals(multiRaw);

				// 0b. Do not start a long backup if the close is not allowed anyway.
				// multi_register=1 -> any hold invoice on any register blocks the close
				// otherwise         -> only hold invoices of the current register block it
				if ("1".equals(DonotPerformEod)) {
					boolean holdFound;
					if (allInvoices && multi) {
						holdFound = exists(con, "SELECT 1 FROM " + db + "mr_pos_hold_invoice LIMIT 1");
					} else {
						holdFound = exists(con, "SELECT 1 FROM " + db + "mr_pos_hold_invoice WHERE "
								+ HOLD_REG_COLUMN + "=? LIMIT 1", r.regName);
					}
					if (holdFound) {
						res.outcome = Outcome.HOLD_INVOICES;
						return res;
					}
				}

				// 1. customer display "not ready" message
				showNotReadyOnDisplay();

				// 2. backup (with timeout)
				step(1, "Starting Backup");
				res.backupOk = runBackup();
				step(10, "Backup Completed");

				// 3. which registers are we closing?
				List<String> regs;
				if (multi) {
					regs = queryList(con,
							"SELECT reg_num FROM " + db + "mr_pos_settlement_cash GROUP BY reg_num ORDER BY reg_num");
					if (regs.isEmpty()) {
						regs.add(r.regName); // nothing sold: still close the current register
					}
				} else {
					regs = new ArrayList<String>(Collections.singletonList(r.regName));
				}

				// registers whose closing rows are built (multi_register: only the first, but its
				// totals cover every open register)
				List<String> calcRegs = multi ? Collections.singletonList(regs.get(0)) : regs;
				List<String> multiScope = new ArrayList<String>(openRegs);
				if (!multiScope.contains(r.regName)) {
					multiScope.add(r.regName);
				}
				// string used by the report classes: R1','R2','CURRENT  (for IN ('...') clauses)
				StringBuilder rl = new StringBuilder();
				for (String s : openRegs) {
					rl.append(s).append("','");
				}
				rl.append(r.regName);
				String registerListSql = rl.toString();

				// 4. PHASE 1 - build closing rows (one transaction)
				Map<String, RegTotals> totals = new HashMap<String, RegTotals>();
				con.setAutoCommit(false);
				for (int i = 0; i < calcRegs.size(); i++) {
					String reg = calcRegs.get(i);
					List<String> scope = multi ? multiScope : Collections.singletonList(reg);
					step(10 + 25 * i / calcRegs.size(), "Creating Register Closing Items");

					String in = "(" + placeholders(scope.size()) + ")";
					Object[] scopeParams = scope.toArray();
					update(con, "DELETE FROM " + db + "mr_close_pay_list WHERE status='0' AND ref_reg_num IN " + in,
							scopeParams);
					update(con,
							"DELETE FROM " + db + "mr_close_pay_list_items WHERE status='0' AND ref_reg_num IN " + in,
							scopeParams);
					update(con, "INSERT INTO " + db
							+ "mr_close_pay_list (`ref_reg_num`,`username`,`store`,`computer`,`date_time`,remarks,`created_date`,invoice_num) VALUES (?,?,?,?,?,?,?,?)",
							reg, r.username, r.storeName, r.computer, r.dateTime, r.remarks, r.today, r.invoiceCount);
					totals.put(reg, buildClosingItems(con, reg, scope));
				}
				con.commit();
				con.setAutoCommit(true);

				// 5. reports (no transaction open while printing)
				step(38, "Printing Reports");
				for (String reg : calcRegs) {
					if (allInvoices && !multi && !"0".equals(multiRaw)) {
						continue;
					}
					report_master.Closing_reg.report(reg, con, printer, r.company);
					if ("1".equals(denominationPrint)) {
						report_master.denomination.report(reg, con, printer, r.company);
					}
					if ("1".equals(change_hold_type)) {
						if (allInvoices) {
							report_master.order_summary.report(reg, con, "XRPT", printer, "", register_all_invoice,
									r.company, multiRaw, registerListSql);
						} else {
							report_master.order_summary.report(reg, con, "XRPT", printer, "", register_all_invoice,
									r.company, "", "");
						}
					}
				}

				// 6. PHASE 2 - move everything to history (one transaction)
				con.setAutoCommit(false);
				for (int i = 0; i < regs.size(); i++) {
					String reg = regs.get(i);
					RegTotals t = totals.containsKey(reg) ? totals.get(reg) : new RegTotals();
					finalizeRegister(con, reg, t, i, regs.size());
				}
				con.commit();
				con.setAutoCommit(true);
				res.lastReg = regs.get(regs.size() - 1);
				step(100, "Register Closed");

				// 7. is the head-office server reachable? (checked after the commit, so a slow or
				// failing remote connection can never undo or delay the local close)
				try (Connection con2 = Dbsetup.server.getConnection()) {
					res.remoteAvailable = con2 != null;
				} catch (Exception ex) {
					ex.printStackTrace();
					res.remoteAvailable = false;
				}
				return res;

			} catch (Exception ex) {
				try {
					if (!con.getAutoCommit()) {
						con.rollback();
					}
				} catch (SQLException ignore) {
					// nothing more we can do
				}
				throw ex;
			} finally {
				try {
					con.setAutoCommit(true);
				} catch (SQLException ignore) {
					// ignore
				}
				try {
					con.close();
				} catch (SQLException ignore) {
					// ignore
				}
			}
		}

		@Override
		protected void process(List<Step> chunks) {
			Step s = chunks.get(chunks.size() - 1);
			progressBar.setValue(s.percent);
			lblNewLabel.setText(s.message);
		}

		@Override
		protected void done() {
			setBusy(false);
			try {
				CloseResult res = get();
				if (res.outcome == Outcome.HOLD_INVOICES) {
					progressBar.setVisible(false);
					lblNewLabel.setText("");
					JOptionPane.showMessageDialog(frame,
							"<html>You Need To Settle Hold Invoices Before Closing<br>ÌÃ» ⁄·Ìﬂ  ”ÊÌ… ›Ê« Ì— «·ÕÃ“ ﬁ»· «·≈€·«ﬁ</html>");
					return;
				}
				if (!res.backupOk) {
					JOptionPane.showMessageDialog(frame,
							"<html>The register was closed, but the backup did not finish correctly.<br>Please check the backup.</html>",
							"BACKUP", JOptionPane.WARNING_MESSAGE);
				}
				if (res.remoteAvailable) {
					try {
						new sales_master.close_progress(r.company, res.lastReg, r.username, r.storeName);
					} catch (ClassNotFoundException | JSONException
							| IOException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(frame,
							"<html>Daily Sale Closed Successfully<br> „ ≈€·«ﬁ «·»Ì⁄ «·ÌÊ„Ì »‰Ã«Õ</html>");
					frame.setVisible(false);
				}
				Mainpage.btnCloseSales.setEnabled(true);
			} catch (ExecutionException ex) {
				ex.getCause().printStackTrace();
				lblNewLabel.setText("Closing failed");
				JOptionPane.showMessageDialog(frame,
						"<html>Closing failed and the register was NOT closed. No sales data was moved.<br>Please try again.<br><br>"
								+ String.valueOf(ex.getCause().getMessage()) + "</html>",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}

		// ---------------- backup / display ----------------

		private void showNotReadyOnDisplay() {
			if (display_port == null) {
				return;
			}
			try {
				Runtime.getRuntime().exec("cmd /c \"CLS>" + display_port + "&&ECHO " + not_ready_msg1 + ">"
						+ display_port + "&&ECHO " + not_ready_msg2 + ">" + display_port + "\"");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		/** @return false if the backup timed out or could not be started */
		private boolean runBackup() {
			try {
				ProcessBuilder pb = new ProcessBuilder(BACKUP_BAT, "start");
				pb.redirectErrorStream(true);
				final Process p = pb.start();
				Thread drain = new Thread(new Runnable() {
					public void run() {
						try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
							String line;
							while ((line = br.readLine()) != null) {
								System.out.println(line);
							}
						} catch (IOException ignore) {
							// process ended
						}
					}
				}, "backup-output");
				drain.setDaemon(true);
				drain.start();
				if (!p.waitFor(BACKUP_TIMEOUT_MINUTES, TimeUnit.MINUTES)) {
					System.err.println("Backup timed out after " + BACKUP_TIMEOUT_MINUTES + " minutes, killing it.");
					p.destroyForcibly();
					return false;
				}
				if (p.exitValue() != 0) {
					System.err.println("Backup exit code: " + p.exitValue());
				}
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}

		// ---------------- phase 1: closing items ----------------

		private RegTotals buildClosingItems(Connection con, String reg, List<String> scopeRegs)
				throws SQLException {
			final String db = r.db;
			final String in = "(" + placeholders(scopeRegs.size()) + ")";
			final Object[] scope = scopeRegs.toArray();
			final RegTotals t = new RegTotals();

			// pay in / pay out
			BigDecimal[] pio = queryDecimals(con,
					"SELECT IFNULL(SUM(CASE WHEN BINARY `type`='payin' THEN amount ELSE 0 END),0),"
							+ " IFNULL(SUM(CASE WHEN BINARY `type`='payin' THEN 0 ELSE amount END),0)"
							+ " FROM " + db + "mr_paylist WHERE ref_reg_no IN " + in + " AND status='0'",
					2, scope);
			BigDecimal payIn = pio[0];
			BigDecimal payOut = pio[1];

			// customer payments: cash / non-cash
			BigDecimal[] cust = queryDecimals(con,
					"SELECT IFNULL(SUM(CASE WHEN BINARY paycode='CASH' THEN amount ELSE 0 END),0),"
							+ " IFNULL(SUM(CASE WHEN BINARY paycode='CASH' THEN 0 ELSE amount END),0)"
							+ " FROM " + db + "mr_customer_payment WHERE ref_reg_num IN " + in + " AND status='0'",
					2, scope);

			String insItem = "INSERT INTO " + db
					+ "mr_close_pay_list_items (`ref_reg_num`,`date_time`,`paycode`,`openings`,`sales_amt`,`total_amt`,`settle_amt`,`ex_rate`,`varience`,`created_date`,sale_amt,vat_amt) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			try (PreparedStatement ps = con.prepareStatement(insItem)) {
				for (PayRow row : r.rows) {
					boolean cash = "CASH".equals(row.paycode);
					BigDecimal sales = BigDecimal.ZERO;
					BigDecimal vat = BigDecimal.ZERO;

					if (cash) {
						BigDecimal d = queryDecimals(con,
								"SELECT IFNULL(SUM(paycode_disc),0) FROM " + db
										+ "mr_pos_settlement_cash WHERE type='PAYCODE' AND paycode_disc!='0' AND reg_num IN "
										+ in,
								1, scope)[0];
						sales = sales.add(d);
						t.sales = t.sales.add(d);
						t.cash = t.cash.add(d);
					}

					String typeCond;
					Object[] typeParams;
					if (cash) {
						typeCond = " AND type NOT IN ('PAYCODE','RETURN','A/R','VOID')";
						typeParams = scope;
					} else {
						typeCond = " AND type=?";
						typeParams = concat(scope, row.paycode);
					}
					BigDecimal[] a = queryDecimals(con,
							"SELECT IFNULL(SUM(grand_total),0), IFNULL(SUM(paycode_disc),0), IFNULL(SUM(vat),0) FROM "
									+ db + "mr_pos_settlement_cash WHERE reg_num IN " + in + typeCond,
							3, typeParams);
					BigDecimal grandPlusDisc = a[0].add(a[1]);
					sales = sales.add(grandPlusDisc);
					vat = vat.add(a[2]);
					t.sales = t.sales.add(grandPlusDisc);
					if (cash) {
						t.cash = t.cash.add(grandPlusDisc);
					}

					BigDecimal[] pc = queryDecimals(con,
							"SELECT IFNULL(SUM(paycode_amt),0), IFNULL(SUM(pay_code_vat),0) FROM " + db
									+ "mr_paycode_settlement INNER JOIN " + db
									+ "mr_pos_settlement_cash ON mr_pos_settlement_cash.reg_num=mr_paycode_settlement.ref_reg_num AND mr_pos_settlement_cash.receipt_no=mr_paycode_settlement.ref_receipt_num"
									+ " WHERE paycode_name=? AND status!='1' AND type!='VOID' AND mr_pos_settlement_cash.reg_num IN "
									+ in,
							2, concat(new Object[] { row.paycode }, scope));
					sales = sales.add(pc[0]);
					t.sales = t.sales.add(pc[0]);
					vat = vat.add(pc[1]);

					BigDecimal exRate = decimalOrZero(queryString(con,
							"SELECT ex_rate FROM " + db + "mr_store_paycodes WHERE paycode=? LIMIT 1", row.paycode));
					BigDecimal open = decimalOrZero(queryString(con, "SELECT amount FROM " + db
							+ "mr_register_open_items WHERE ref_reg_num IN " + in + " AND paycode=? LIMIT 1",
							concat(scope, row.paycode)));

					BigDecimal expected = open.add(sales);
					BigDecimal diff = row.entered.subtract(expected).setScale(r.scale, RoundingMode.HALF_UP);
					BigDecimal ttlSale = sales.setScale(r.scale, RoundingMode.HALF_UP);
					BigDecimal totalAmt = expected.setScale(r.scale, RoundingMode.HALF_UP);
					BigDecimal saleAmt = sales.multiply(exRate).subtract(vat).setScale(r.scale, RoundingMode.HALF_UP);

					ps.setString(1, reg);
					ps.setString(2, r.dateTime);
					ps.setString(3, row.paycode);
					ps.setBigDecimal(4, open);
					ps.setBigDecimal(5, ttlSale);
					ps.setBigDecimal(6, totalAmt);
					ps.setBigDecimal(7, row.entered);
					ps.setBigDecimal(8, exRate);
					ps.setBigDecimal(9, diff);
					ps.setString(10, r.today);
					ps.setBigDecimal(11, saleAmt);
					ps.setBigDecimal(12, vat.setScale(r.scale, RoundingMode.HALF_UP));
					ps.executeUpdate();
				}
			}

			String insSum = "INSERT INTO " + db
					+ "mr_close_pay_list_items (`ref_reg_num`,`date_time`,`paycode`,`openings`,`sales_amt`,`total_amt`,`settle_amt`,`ex_rate`,`varience`,`created_date`,sale_amt) VALUES (?,?,?,0,?,?,0,1,?,?,?)";
			try (PreparedStatement ps = con.prepareStatement(insSum)) {
				addSummaryRow(ps, reg, "CUSTOMERPAYMENTS", cust[0]);
				addSummaryRow(ps, reg, "AR PAYMENT BANK", cust[1]);
				addSummaryRow(ps, reg, "PAYIN", payIn);
				addSummaryRow(ps, reg, "PAYOUT", payOut);
			}
			return t;
		}

		private void addSummaryRow(PreparedStatement ps, String reg, String code, BigDecimal amount)
				throws SQLException {
			ps.setString(1, reg);
			ps.setString(2, r.dateTime);
			ps.setString(3, code);
			ps.setBigDecimal(4, amount);
			ps.setBigDecimal(5, amount);
			ps.setBigDecimal(6, amount.negate());
			ps.setString(7, r.today);
			ps.setBigDecimal(8, amount);
			ps.executeUpdate();
		}

		// ---------------- phase 2: move to history ----------------

		private int pct(int regIdx, int regCount, int k) {
			// 45% .. 99% spread over registers and 10 sub-steps
			return 45 + (54 * (regIdx * 10 + k)) / (regCount * 10);
		}

		private void finalizeRegister(Connection con, String reg, RegTotals t, int idx, int n)
				throws SQLException {
			final String db = r.db;

			step(pct(idx, n, 0), "Starting Register Closing");
			update(con, "UPDATE " + db + "mr_close_pay_list SET status='1' WHERE ref_reg_num=?", reg);
			update(con, "UPDATE " + db + "mr_close_pay_list_items SET status='1' WHERE ref_reg_num=?", reg);
			update(con, "UPDATE " + db + "mr_denomination SET status='1' WHERE ref_reg_num=?", reg);

			String closeDate = queryString(con,
					"SELECT created_date FROM " + db + "mr_register_openings WHERE reg_num=? LIMIT 1", reg);
			if (closeDate == null) {
				closeDate = "";
			}
			update(con, "UPDATE " + db + "mr_close_pay_list SET total_sale_amt=? WHERE ref_reg_num=?", t.sales, reg);

			step(pct(idx, n, 1), "Calculating Vat and Discount");
			BigDecimal newCost = queryDecimals(con,
					"SELECT IFNULL(SUM(i.item_cost*i.qty*i.cf),0) FROM " + db + "mr_pos_settlement_cash c INNER JOIN "
							+ db + "mr_pos_settlement_cash_items i ON c.reg_num=i.ref_reg_num AND c.receipt_no=i.ref_receipt_num"
							+ " WHERE i.ref_reg_num=? AND c.type!='VOID'",
					1, reg)[0];
			BigDecimal[] vd = queryDecimals(con,
					"SELECT IFNULL(SUM(vat),0), IFNULL(SUM(disc),0), IFNULL(SUM(paycode_disc),0) FROM " + db
							+ "mr_pos_settlement_cash WHERE reg_num=? AND type!='VOID'",
					3, reg);
			update(con, "UPDATE " + db
					+ "mr_close_pay_list SET total_disc=?,total_vat=?,total_cost=? WHERE ref_reg_num=?",
					vd[1].add(vd[2]), vd[0], newCost, reg);

			// close list -> history
			String idStr = queryString(con,
					"SELECT id FROM " + db + "mr_close_pay_list WHERE ref_reg_num=? AND status='1' LIMIT 1", reg);
			long id = 0;
			if (idStr != null) {
				update(con, "INSERT INTO " + db
						+ "mr_close_pay_list_history (ref_id,`ref_reg_num`,`username`,`store`,`computer`,`date_time`,`remarks`,total_sale_amt,`created_date`,invoice_num,total_vat,total_disc,total_cost,close_date)"
						+ " SELECT id,`ref_reg_num`,`username`,`store`,`computer`,`date_time`,`remarks`,total_sale_amt,`created_date`,invoice_num,total_vat,total_disc,total_cost,? FROM "
						+ db + "mr_close_pay_list WHERE ref_reg_num=?", closeDate, reg);
				id = Long.parseLong(idStr.trim());
				update(con, "DELETE FROM " + db + "mr_close_pay_list WHERE ref_reg_num=?", reg);
			}

			// open-item paycodes -> pay_code1..8 columns of the history row
			step(pct(idx, n, 2), "Checking Register Open Items in Close Register");
			List<String> openCodes = queryList(con,
					"SELECT DISTINCT paycode FROM " + db + "mr_register_open_items WHERE ref_reg_num=?", reg);
			for (String code : openCodes) {
				int k = historyIndex(code);
				if (k < 0) {
					continue;
				}
				if (k == 1) {
					BigDecimal[] c = queryDecimals(con,
							"SELECT COUNT(*), IFNULL(SUM(openings),0), IFNULL(SUM(total_amt),0), IFNULL(SUM(varience),0) FROM "
									+ db + "mr_close_pay_list_items WHERE ref_reg_num=? AND status='1'"
									+ " AND paycode IN ('CASH','PAYIN','PAYOUT','CUSTOMERPAYMENTS')",
							4, reg);
					boolean found = c[0].signum() > 0;
					updateHistoryPaycode(con, k, reg, id, found ? "CASH" : "", c[1], t.cash, c[2],
							found ? BigDecimal.ONE : BigDecimal.ZERO, c[3]);
				} else {
					BigDecimal[] c = queryDecimals(con,
							"SELECT COUNT(*), IFNULL(SUM(openings),0), IFNULL(SUM(sales_amt),0), IFNULL(SUM(total_amt),0), IFNULL(SUM(ex_rate),0), IFNULL(SUM(varience),0) FROM "
									+ db + "mr_close_pay_list_items WHERE ref_reg_num=? AND status='1' AND paycode=?",
							6, reg, code);
					updateHistoryPaycode(con, k, reg, id, c[0].signum() > 0 ? code : "", c[1], c[2], c[3], c[4], c[5]);
				}
			}

			step(pct(idx, n, 3), "Creating History Register");
			update(con, "INSERT INTO " + db
					+ "mr_close_pay_list_history_items (ref_reg_num,date_time,paycode,openings,sales_amt,total_amt,ex_rate,settle_amt,varience,created_date,sale_amt,vat_amt)"
					+ " SELECT ref_reg_num,date_time,paycode,openings,sales_amt,total_amt,ex_rate,settle_amt,varience,created_date,sale_amt,vat_amt FROM "
					+ db + "mr_close_pay_list_items WHERE ref_reg_num=?", reg);
			update(con, "DELETE FROM " + db + "mr_close_pay_list_items WHERE ref_reg_num=?", reg);

			update(con, "INSERT INTO " + db
					+ "mr_register_openings_history (`reg_num`,`store_name`,`user_name`,`open_receipt`,`remarks`,`date_time`,`created_date`)"
					+ " SELECT `reg_num`,`store`,`user_name`,`open_receipt`,`remarks`,`date_time`,`created_date` FROM "
					+ db + "mr_register_openings WHERE reg_num=?", reg);
			update(con, "DELETE FROM " + db + "mr_register_openings WHERE reg_num=?", reg);

			step(pct(idx, n, 4), "Creating History Register Items");
			update(con, "INSERT INTO " + db
					+ "mr_register_open_items_history (`ref_reg_num`,`date_time`,`paycode`,`pay_desc`,`amount`,`created_date`)"
					+ " SELECT `ref_reg_num`,`date_time`,`paycode`,`pay_desc`,`amount`,`created_date` FROM " + db
					+ "mr_register_open_items WHERE ref_reg_num=?", reg);
			update(con, "DELETE FROM " + db + "mr_register_open_items WHERE ref_reg_num=?", reg);

			step(pct(idx, n, 5), "Updating Customer Payment Status");
			update(con, "UPDATE " + db + "mr_paylist SET status='1' WHERE ref_reg_no=? AND status='0'", reg);
			update(con, "UPDATE " + db + "mr_customer_payment SET status='1' WHERE ref_reg_num=? AND status='0'",
					reg);

			step(pct(idx, n, 6), "Creating Invoice History Header");
			update(con, "INSERT INTO " + db
					+ "mr_pos_settlement_history (`reg_num`,`receipt_no`,`user`,`time`,`total`,`cust_name`,`vat`,`disc`,`qty`,`cash_paid`,`type`,`paycode_disc`,`store_id`,`created_date`,grand_total,cust_code,cust_prev_balance,close_date,`mode`,`delivery_user`,`table_num`)"
					+ " SELECT `reg_num`,`receipt_no`,`user`,`time`,`total`,`cust_name`,`vat`,`disc`,`qty`,`cash_paid`,`type`,`paycode_disc`,`store_id`,`created_date`,grand_total,cust_code,cust_prev_balance,?,`mode`,`delivery_user`,`table_num` FROM "
					+ db + "mr_pos_settlement_cash WHERE reg_num=?", closeDate, reg);
			update(con, "DELETE FROM " + db + "mr_pos_settlement_cash WHERE reg_num=?", reg);

			step(pct(idx, n, 7), "Updating Item Master");
			// one set-based update instead of 3 queries per sold item
			update(con, "UPDATE " + db + "mr_tmp_item_master t INNER JOIN (SELECT item_code, SUM(qty*cf) AS q FROM "
					+ db + "mr_pos_settlement_cash_items WHERE ref_reg_num=? GROUP BY item_code) s"
					+ " ON s.item_code=t.item_code SET t.qty=t.qty-s.q", reg);
			update(con, "UPDATE " + db + "mr_item_master m INNER JOIN " + db
					+ "mr_tmp_item_master t ON t.item_code=m.item_code INNER JOIN (SELECT DISTINCT item_code FROM " + db
					+ "mr_pos_settlement_cash_items WHERE ref_reg_num=?) s ON s.item_code=m.item_code"
					+ " SET m.total_qty=t.qty, m.modified_date=?", reg, r.today);

			step(pct(idx, n, 8), "Creating Invoice History Details");
			update(con, "INSERT INTO " + db
					+ "mr_pos_settlement_history_items (`ref_receipt_num`,`ref_reg_num`,`barcode`,`item_desc`,`item_code`,item_cost,`unit`,`unit_price`,`qty`,`total`,`vat`,`discount`,`created_date`,sp,`vat_type`,`vat_perc`,`disc_type`,cf)"
					+ " SELECT `ref_receipt_num`,`ref_reg_num`,`barcode`,`item_desc`,`item_code`,item_cost,`unit`,`unit_price`,`qty`,`total`,`vat`,`discount`,`created_date`,sp,`vat_type`,`vat_perc`,`disc_type`,cf FROM "
					+ db + "mr_pos_settlement_cash_items WHERE ref_reg_num=?", reg);
			update(con, "DELETE FROM " + db + "mr_pos_settlement_cash_items WHERE ref_reg_num=?", reg);

			step(pct(idx, n, 9), "Creating Paycode Settlement History");
			update(con, "INSERT INTO " + db
					+ "mr_paycode_settlement_history (`ref_reg_num`,`ref_receipt_num`,`paycode_name`,`paycode_rate`,`paycode_amt`,`paid_amt`,`created_date`)"
					+ " SELECT `ref_reg_num`,`ref_receipt_num`,`paycode_name`,`paycode_rate`,`paycode_amt`,`paid_amt`,`created_date` FROM "
					+ db + "mr_paycode_settlement WHERE ref_reg_num=?", reg);
			update(con, "DELETE FROM " + db + "mr_paycode_settlement WHERE ref_reg_num=?", reg);
		}

		private void updateHistoryPaycode(Connection con, int k, String reg, long id, String code,
				BigDecimal open, BigDecimal sales, BigDecimal total, BigDecimal exRate, BigDecimal variance)
				throws SQLException {
			// k is always 1..8 from HISTORY_PAYCODES, so building the column names is safe
			update(con,
					"UPDATE " + r.db + "mr_close_pay_list_history SET pay_code" + k + "=?, open_amt" + k
							+ "=?, sales_amt" + k + "=?, total_amt" + k + "=?, ex_rate" + k + "=?, varience" + k
							+ "=? WHERE ref_reg_num=? AND ref_id=?",
					code, open, sales, total, exRate, variance, reg, id);
		}
	}

	private static int historyIndex(String paycode) {
		for (int i = 1; i < HISTORY_PAYCODES.length; i++) {
			if (HISTORY_PAYCODES[i].equals(paycode)) {
				return i;
			}
		}
		return -1;
	}

	// ------------------------------------------------------------------------------------------
	// Small JDBC helpers
	// ------------------------------------------------------------------------------------------

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

	private static String queryString(Connection con, String sql, Object... p) throws SQLException {
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			bind(ps, p);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() ? rs.getString(1) : null;
			}
		}
	}

	private static boolean exists(Connection con, String sql, Object... p) throws SQLException {
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			bind(ps, p);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
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

	private static BigDecimal[] queryDecimals(Connection con, String sql, int n, Object... p)
			throws SQLException {
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
			if (i > 0) {
				sb.append(',');
			}
			sb.append('?');
		}
		return sb.toString();
	}

	private static Object[] concat(Object[] head, Object... tail) {
		Object[] out = new Object[head.length + tail.length];
		System.arraycopy(head, 0, out, 0, head.length);
		System.arraycopy(tail, 0, out, head.length, tail.length);
		return out;
	}

	private static BigDecimal decimalOrZero(String s) {
		if (s == null || s.trim().isEmpty()) {
			return BigDecimal.ZERO;
		}
		try {
			return new BigDecimal(s.trim());
		} catch (NumberFormatException ex) {
			return BigDecimal.ZERO;
		}
	}

	private static BigDecimal toDecimal(Object o) {
		return o == null ? BigDecimal.ZERO : decimalOrZero(o.toString());
	}

	// ------------------------------------------------------------------------------------------
	// Screen initialisation queries (run once when the window opens)
	// ------------------------------------------------------------------------------------------

	public static void get_store_validate_dt(String company, String store_name, String username, String posreg) {
		try (Connection con = Dbsetup.local_server.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM " + company + ".mr_store WHERE store_id=?")) {
			ps.setString(1, store_name);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					change_hold_type = rs.getString("change_hold_type");
					assembly_of_item = rs.getString("assembly_of_item");
					DonotPerformEod = rs.getString("DonotPerformEod");
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void get_register_name(String company, String reg_nme) {
		String db = company + ".";
		try (Connection con = Dbsetup.local_server.getConnection()) {
			int close = 0;
			try (PreparedStatement ps = con
					.prepareStatement("SELECT * FROM " + db + "mr_register_setup WHERE register_number=?")) {
				ps.setString(1, reg_nme);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						display_port = rs.getString("pole_display_port");
						not_ready_msg1 = rs.getString("ready_msg1");
						not_ready_msg2 = rs.getString("ready_msg2");
						printer = rs.getString("printer1");
						close = Integer.parseInt(rs.getString("invoice_number"))
								+ Integer.parseInt(rs.getString("next_num"));
					}
				}
			}
			String recpt = queryString(con, "SELECT receipt_no FROM " + db
					+ "mr_pos_settlement_cash WHERE reg_num=? ORDER BY receipt_no ASC LIMIT 1", reg_nme);

			try (PreparedStatement ps = con
					.prepareStatement("SELECT * FROM " + db + "mr_register_controls WHERE ref_register_num=?")) {
				ps.setString(1, reg_nme);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						denominationPrint = rs.getString("denominationPrint");
						register_all_invoice = rs.getString("register_all_invoice");
						registerAsWaiter = rs.getString("registerAsWaiter");
					}
				}
			}

			String cnt;
			if ("1".equals(register_all_invoice)) {
				cnt = queryString(con,
						"SELECT COUNT(*) FROM " + db + "mr_pos_settlement_cash WHERE type!='VOID'");
			} else {
				cnt = queryString(con, "SELECT COUNT(*) FROM " + db
						+ "mr_pos_settlement_cash WHERE reg_num=? AND type!='VOID'", reg_nme);
			}
			if (cnt != null) {
				lblNewLabel_4.setText(cnt);
			}
			label_3.setText(recpt == null ? "" : recpt);
			label_5.setText(Integer.toString(close));
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private void get_full_paycode(String company, String reg_nme) {
		try (Connection con = Dbsetup.local_server.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"SELECT * FROM " + company + ".mr_register_open_items WHERE ref_reg_num=?")) {
			ps.setString(1, reg_nme);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String name = rs.getString("paycode");
					String pay_desc = rs.getString("pay_desc");
					if (name != null && !name.equals("")) {
						model.addRow(new Object[] { model.getRowCount() + 1, name, pay_desc, 0 });
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	// ------------------------------------------------------------------------------------------
	// Table renderer / editor (button in the "Paycode" column that takes the pad value)
	// ------------------------------------------------------------------------------------------

	public class ClientsTableButtonRenderer extends JButton implements TableCellRenderer {
		private static final long serialVersionUID = 1L;

		public ClientsTableButtonRenderer() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column) {
			setForeground(Color.black);
			setBackground(UIManager.getColor("Button.background"));
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}

	public class ClientsTableRenderer extends DefaultCellEditor {
		private static final long serialVersionUID = 1L;
		private JButton button;
		private String label;
		private boolean clicked;
		private int row, col;
		private JTable table;

		public ClientsTableRenderer(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fireEditingStopped();
				}
			});
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			this.table = table;
			this.row = row;
			this.col = column;
			button.setForeground(Color.black);
			button.setBackground(UIManager.getColor("Button.background"));
			label = (value == null) ? "" : value.toString();
			button.setText(label);
			clicked = true;
			return button;
		}

		public Object getCellEditorValue() {
			if (clicked) {
				String val = textField_12.getText();
				if (!val.equals("")) {
					try {
						table.setValueAt(Double.parseDouble(val), row, 3);
						textField_12.setText("");
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frame, "Invalid amount");
					}
				} else {
					JOptionPane.showMessageDialog(frame,
							"<html>Please Enter Some Values First<br>«·—Ã«¡ ≈œŒ«· »⁄÷ «·ﬁÌ„ √Ê·«</html>");
				}
			}
			clicked = false;
			return new String(label);
		}

		public boolean stopCellEditing() {
			clicked = false;
			return super.stopCellEditing();
		}

		protected void fireEditingStopped() {
			super.fireEditingStopped();
		}
	}
}