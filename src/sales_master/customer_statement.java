package sales_master;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import CafePro.Mainpage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class customer_statement {

	private static JFrame frmCustomerStatment;
	static JTextField textField;
	static JTextField textField_1;
	static JTextField textField_2;
	static JTextField textField_3;
	private JTable table;
	private static DefaultTableModel model;
	static JTextField textField_4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					customer_statement window = new customer_statement("mr_pro","admin","S01","S0101", null,"");
					customer_statement.frmCustomerStatment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public customer_statement(String company, String username, String store_name, String reg_nme,String version,String custid) {
		initialize(company, username, store_name, reg_nme, version,custid);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company,final String username ,final String store_name ,final String reg_nme,final String version,final String custid) {
		frmCustomerStatment = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frmCustomerStatment.setIconImage(img);
		frmCustomerStatment.setVisible(true);
		frmCustomerStatment.getContentPane().setBackground(Color.DARK_GRAY);
		frmCustomerStatment.setTitle("CUSTOMER STATMENT");
		frmCustomerStatment.setBounds(100, 100, 751, 590);
		frmCustomerStatment.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				frmCustomerStatment.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btncustStatement.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btncustStatement.setEnabled(true);
				}
				
			}
			@Override
		      public void windowIconified(WindowEvent we) {
				frmCustomerStatment.setState(JFrame.NORMAL);
		      }
		});
		frmCustomerStatment.setAlwaysOnTop(true);
		frmCustomerStatment.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Customer:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(new Color(100, 149, 237));
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setBounds(27, 11, 160, 25);
		frmCustomerStatment.getContentPane().add(label);
		
		textField = new JTextField(custid);
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_userdisp(company, textField.getText());
			}
		});
		textField.setColumns(10);
		textField.setBounds(197, 11, 397, 25);
		frmCustomerStatment.getContentPane().add(textField);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton button = new JButton("SEARCH");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Cust_Chooser(company, "cust_state", username, store_name, "","", version,"1");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setBounds(604, 11, 110, 27);
		button.setIcon(new ImageIcon(serch));
		frmCustomerStatment.getContentPane().add(button);
		
		JLabel label_1 = new JLabel("Balance Outstanding:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(new Color(100, 149, 237));
		label_1.setFont(new Font("Arial", Font.BOLD, 15));
		label_1.setBounds(10, 49, 160, 25);
		frmCustomerStatment.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setFont(new Font("Arial", Font.BOLD, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(180, 50, 100, 25);
		frmCustomerStatment.getContentPane().add(textField_1);
		
		JLabel label_2 = new JLabel("Status:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setForeground(new Color(100, 149, 237));
		label_2.setFont(new Font("Arial", Font.BOLD, 15));
		label_2.setBounds(290, 49, 58, 25);
		frmCustomerStatment.getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(358, 49, 79, 25);
		frmCustomerStatment.getContentPane().add(textField_2);
		
		JLabel lblBalanceCreditLimit = new JLabel("Balance credit Limit:");
		lblBalanceCreditLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBalanceCreditLimit.setForeground(new Color(100, 149, 237));
		lblBalanceCreditLimit.setFont(new Font("Arial", Font.BOLD, 15));
		lblBalanceCreditLimit.setBounds(445, 49, 164, 25);
		frmCustomerStatment.getContentPane().add(lblBalanceCreditLimit);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setFont(new Font("Arial", Font.BOLD, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(614, 49, 100, 25);
		frmCustomerStatment.getContentPane().add(textField_3);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(10, 85, 711, 399);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(Color.BLACK);
		frmCustomerStatment.getContentPane().add(scrollPane);
		
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		table = new JTable(model);
		table.setShowGrid(false);
		table.setForeground(Color.WHITE);
		table.setRowHeight(25);
		//table.setBorder(new LineBorder(Color.WHITE));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Arial", Font.BOLD, 15));
		table.setBackground(Color.DARK_GRAY);
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(100,35));
		
		model.addColumn("S.No.");
	    model.addColumn("Inv.Date/AR.Date");
	    model.addColumn("Post Date");
	    model.addColumn("Inv. No. /AR. No."); 
	    model.addColumn("Inv. items"); 
	    model.addColumn("Inv.Amount");
	    model.addColumn("Inv.discount");
	    model.addColumn("Inv.Tax");
	    model.addColumn("<html>Grand total/AR Amt.<br>received</html>");
	    int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(30);
			  if (i==1) column.setPreferredWidth(70);
			  if (i==2) column.setPreferredWidth(81);
			  if (i==3) column.setPreferredWidth(68);
			  if (i==4) column.setPreferredWidth(50);
			  if (i==5) column.setPreferredWidth(80);
			  if (i==6) column.setPreferredWidth(40);
			  if (i==7) column.setPreferredWidth(55);
			  if (i==8) column.setPreferredWidth(100);
			}
		
	    scrollPane.setViewportView(table);
	    Image printer1=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JButton button_1 = new JButton("PRINT STATEMENT");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField_4.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frmCustomerStatment, "Select One Customer");
				}
				else
				{
					frmCustomerStatment.setAlwaysOnTop(false);
					new Date_range(company, username, store_name, textField_4.getText(), version,textField.getText());
				}
			}
		});
		button_1.setBounds(82, 494, 171, 38);
		button_1.setIcon(new ImageIcon(printer1));
		frmCustomerStatment.getContentPane().add(button_1);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCustomerStatment.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btncustStatement.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btncustStatement.setEnabled(true);
				}
				
			}
		});
		btnExit.setBounds(516, 494, 135, 38);
		btnExit.setIcon(new ImageIcon(exit));
		frmCustomerStatment.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				model.setRowCount(0);
			}
		});
		btnClear.setBounds(299, 494, 135, 38);
		frmCustomerStatment.getContentPane().add(btnClear);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setEditable(false);
		textField_4.setVisible(false);
		textField_4.setBounds(10, 26, 116, 22);
		frmCustomerStatment.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		get_userdisp(company, textField.getText());
		
	}
	private void  get_userdisp(String company, String serch)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_customer_master where cust_name='"+serch+"' or cust_code='"+serch+"' ");
			if(resultSet.next())
			{
				String id=resultSet.getString("cust_code");
	            String name=resultSet.getString("cust_name");
	            String avail_limit=resultSet.getString("avail_credit");
	            String crdit_limit=resultSet.getString("credit_limit");
	            String adv_amt=resultSet.getString("advance_amt");
	            String rating=resultSet.getString("rating");
	            double credit=Float.parseFloat(crdit_limit)+Float.parseFloat(adv_amt);
	            double avail=Float.parseFloat(avail_limit)+Float.parseFloat(adv_amt);
	            double bal=credit-avail;
	            BigDecimal bw = new BigDecimal(bal);
        		bw = bw.setScale(2, BigDecimal.ROUND_HALF_UP);
        		bal = bw.doubleValue();
        		BigDecimal bc = new BigDecimal(avail);
        		bc = bc.setScale(2, BigDecimal.ROUND_HALF_UP);
        		avail = bc.doubleValue();
	            textField.setText(name);
    			textField_3.setText(Double.toString(avail));
    			textField_2.setText(rating);
    			//double bal=(Float.parseFloat(crdit_limit)+Float.parseFloat(adv_amt))-(Double.parseDouble(avail_limit)+Double.parseDouble(adv_amt));
    			textField_1.setText(Double.toString(bal));
    			textField_4.setText(id);
    			get_cust_invo_list(company, id);
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	static void  get_cust_invo_list(String company, String cust_name)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="select "+company+".mr_pos_settlement_cash.created_date as tran_date,"+company+".mr_pos_settlement_cash.created_date as post_date,"+company+".mr_pos_settlement_cash.receipt_no as tran_num,"+company+".mr_pos_settlement_cash.qty as inv_items,"+company+".mr_pos_settlement_cash.total as inv_amt,"+company+".mr_pos_settlement_cash.vat as inv_vat,"+company+".mr_pos_settlement_cash.disc as inv_disc,"+company+".mr_pos_settlement_cash.total+"+company+".mr_pos_settlement_cash.vat as inv_ttl from "+company+".mr_pos_settlement_cash where "+company+".mr_pos_settlement_cash.cust_code='"+cust_name+"'  UNION select	"+company+".mr_pos_settlement_history.created_date as tran_date,"+company+".mr_pos_settlement_history.created_date as post_date,	"+company+".mr_pos_settlement_history.receipt_no as tran_num,"+company+".mr_pos_settlement_history.qty as inv_items,"+company+".mr_pos_settlement_history.total as inv_amt,"+company+".mr_pos_settlement_history.vat as inv_vat,"+company+".mr_pos_settlement_history.disc as inv_disc,"+company+".mr_pos_settlement_history.total+"+company+".mr_pos_settlement_history.vat as inv_ttl from "+company+".mr_pos_settlement_history where "+company+".mr_pos_settlement_history.cust_code='"+cust_name+"' union select "+company+".mr_customer_payment.created_date as tran_date,"+company+".mr_customer_payment.created_date as post_date,	"+company+".mr_customer_payment.seq_num as tran_num,	'0' as inv_items,	"+company+".mr_customer_payment.amount as inv_amt,	'0' as inv_vat,	"+company+".mr_customer_payment.discount as inv_disc,	"+company+".mr_customer_payment.amount as inv_ttl from "+company+".mr_customer_payment where  "+company+".mr_customer_payment.cust_code='"+cust_name+"'  union select "+company+".mr_sd_customer_payment_details.issue_date as tran_date,"+company+".mr_sd_customer_payment_details.created_date as post_date,"+company+".mr_sd_customer_payment_details.seq_num as tran_num,	'0' as inv_items,	if(mr_sd_customer_payment_details.status IN ('1','0'),"+company+".mr_sd_customer_payment_details.cr_amt,"+company+".mr_sd_customer_payment_details.dr_amt) as inv_amt,'0' as inv_vat,	'0' as inv_disc,	if(mr_sd_customer_payment_details.status IN ('1','0'),"+company+".mr_sd_customer_payment_details.cr_amt,"+company+".mr_sd_customer_payment_details.dr_amt) as inv_ttl from "+company+".mr_sd_customer_payment inner join "+company+".mr_sd_customer_payment_details ON mr_sd_customer_payment.ref_tran_num=mr_sd_customer_payment_details.ref_tran_num where mr_sd_customer_payment.ref_cust_id='"+cust_name+"' and mr_sd_customer_payment_details.ref_cust_id='"+cust_name+"' and mr_sd_customer_payment_details.status!='4' order by tran_date ";
			//String query="select mr_pro.mr_pos_settlement_cash.created_date as tran_date,mr_pro.mr_pos_settlement_cash.receipt_no as tran_num,mr_pro.mr_pos_settlement_cash.qty as inv_items,-mr_pro.mr_pos_settlement_cash.total as inv_amt,mr_pro.mr_pos_settlement_cash.vat as inv_vat,mr_pro.mr_pos_settlement_cash.disc as inv_disc,-mr_pro.mr_pos_settlement_cash.total+mr_pro.mr_pos_settlement_cash.vat as inv_ttl from mr_pro.mr_pos_settlement_cash where mr_pro.mr_pos_settlement_cash.cust_code='"+cust_name+"'  UNION select	mr_pro.mr_pos_settlement_history.created_date as tran_date,	mr_pro.mr_pos_settlement_history.receipt_no as tran_num,mr_pro.mr_pos_settlement_history.qty as inv_items,-mr_pro.mr_pos_settlement_history.total as inv_amt,mr_pro.mr_pos_settlement_history.vat as inv_vat,mr_pro.mr_pos_settlement_history.disc as inv_disc,-mr_pro.mr_pos_settlement_history.total+mr_pro.mr_pos_settlement_history.vat as inv_ttl from mr_pro.mr_pos_settlement_history where mr_pro.mr_pos_settlement_history.cust_code='"+cust_name+"' union select mr_pro.mr_customer_payment.created_date as tran_date,	mr_pro.mr_customer_payment.seq_num as tran_num,	'0' as inv_items,	mr_pro.mr_customer_payment.amount as inv_amt,	'0' as inv_vat,	mr_pro.mr_customer_payment.discount as inv_disc,	mr_pro.mr_customer_payment.amount as inv_ttl from mr_pro.mr_customer_payment where  mr_pro.mr_customer_payment.cust_code='"+cust_name+"' union select mr_pro.mr_sd_list_history.date as tran_date,	mr_pro.mr_sd_list_history.invoice_num as tran_num,	'0' as inv_items,	-mr_pro.mr_sd_list_history.total_amt as inv_amt,	-mr_pro.mr_sd_list_history.total_vat as inv_vat,	mr_pro.mr_sd_list_history.disc as inv_disc,	(-mr_pro.mr_sd_list_history.total_amt)+(-mr_pro.mr_sd_list_history.total_vat) as inv_ttl from mr_pro.mr_sd_list_history where mr_pro.mr_sd_list_history.cust_id='"+cust_name+"' and type='sd_invoice' order by tran_date";
			ResultSet resultSet = statement.executeQuery(query);
			//ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash where cust_code='"+cust_name+"' ");
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			while(resultSet.next())
			{
				String inv_date=resultSet.getString("tran_date");
				String post_date=resultSet.getString("post_date");
	            String inv_no=resultSet.getString("tran_num");
	            String inv_qty=resultSet.getString("inv_items");
	            double inv_grandttl=Double.parseDouble(resultSet.getString("inv_amt"));
	            String inv_disc=resultSet.getString("inv_disc");
	            String inv_tax=resultSet.getString("inv_vat");
	            double ttl_amt=Double.parseDouble(resultSet.getString("inv_ttl"));
	            BigDecimal be = new BigDecimal(ttl_amt);
        		be = be.setScale(2, BigDecimal.ROUND_HALF_UP);
        		ttl_amt = be.doubleValue();
        		BigDecimal ba = new BigDecimal(inv_grandttl);
        		ba = ba.setScale(2, BigDecimal.ROUND_HALF_UP);
        		inv_grandttl = ba.doubleValue();
	            model.addRow(new Object[]{model.getRowCount()+1,inv_date,post_date,inv_no,inv_qty,inv_grandttl,inv_disc,inv_tax,ttl_amt});
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
