package POS_Reports;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import CafePro.Mainpage;
import History_Reports.CustomerPaymentChange;

public class cust_payment {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private String printer;
	private static int decimal;
	private static DecimalFormat df2;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cust_payment window = new cust_payment("mr_pro","S01","SHABEER", null, "S0101","2","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public cust_payment(String company, String store_name, String username,String version,String reg_nme,String decimal,String language) {
		initialize(company,store_name,username, version,reg_nme,decimal,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store_name 
	 * @param host_name 
	 * @param company 
	 * @param language 
	 */
	private void initialize(final String company,final String store_name,final String username,String version,final String reg_nme,final String decimal, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 792, 720);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		Image printer1=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 13, 786, 672);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 125, 786, 500);
		panel.add(scrollPane);
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
		
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(20);
		    model.addColumn("Sl No:"); 
		    model.addColumn("Voucher No:"); 
		    model.addColumn("Cust Code"); 
			model.addColumn("Cust Name"); 
			model.addColumn("Paid Amt"); 
			model.addColumn("Paycode"); 
		
	
			//int a=table.getColumnCount();
//			for(int i=0;i<a;i++)
//			{
//				TableColumn column=table.getColumnModel().getColumn(i);
//				if(i==0){
//					column.setPreferredWidth(15);
//				}
//				if(i==3){
//					column.setPreferredWidth(25);
//				}
//				if(i==4){
//					column.setPreferredWidth(10);
//				}
//				if(i==5){
//					column.setPreferredWidth(10);
//				}
//			}
		    scrollPane.setViewportView(table);
			
		    
			
			JLabel lblCloseSales = new JLabel("Customer Payment Summary");
			lblCloseSales.setFont(new Font("Monospaced", Font.BOLD, 25));
			lblCloseSales.setBounds(12, 11, 373, 53);
			panel.add(lblCloseSales);
			
			JMenuItem menuItem_1 = new JMenuItem("Print");
			menuItem_1.setBounds(694, 13, 80, 30);
			panel.add(menuItem_1);
			menuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
			menuItem_1.setForeground(Color.GRAY);
			menuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Connection con=Dbsetup.local_server.getConnection();
					try {
						report_master.Xreports.report(reg_nme, con, "PAYMENT",printer,"",company,"");
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			menuItem_1.setBorder(null);
			menuItem_1.setBackground(new Color(255, 228, 196));
			menuItem_1.setIcon(new ImageIcon(printer1));
			
			JLabel lblTotal = new JLabel("Total");
			lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblTotal.setBounds(549, 636, 56, 25);
			panel.add(lblTotal);
			
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.BOLD, 13));
			textField.setEditable(false);
			textField.setBounds(658, 636, 116, 25);
			panel.add(textField);
			textField.setColumns(10);
			
			JMenuItem mntmPrintVoucher = new JMenuItem("Print Voucher");
			mntmPrintVoucher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int row=table.getSelectedRow();
					if(row>-1)
					{
						try
						{
							double prevbal=0;
							Connection con=Dbsetup.local_server.getConnection();
							Statement statement=con.createStatement();
							String voucher= table.getValueAt(row, 1).toString();
							String custcode= table.getValueAt(row, 2).toString();
							String query="SELECT credit_limit,avail_credit,amount from "+company+".mr_customer_payment inner join "+company+".mr_customer_master on mr_customer_payment.cust_code=mr_customer_master.cust_code where mr_customer_payment.cust_code='"+custcode+"' and  mr_customer_payment.seq_num='"+voucher+"'";
							ResultSet resultSet0 = statement.executeQuery(query);
							if(resultSet0.next())
							{
								String credit_limit=resultSet0.getString("credit_limit");
								String avail_credit=resultSet0.getString("avail_credit");
								String amount=resultSet0.getString("amount");
								 prevbal=(Double.parseDouble(credit_limit)-Double.parseDouble(avail_credit))+Double.parseDouble(amount);
							}
							report_master.customer_voucher.report(voucher,con, printer,Double.toString(prevbal),company,"");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Select A Row from Table");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}
			});
			mntmPrintVoucher.setForeground(Color.GRAY);
			mntmPrintVoucher.setFont(new Font("Segoe UI", Font.BOLD, 15));
			mntmPrintVoucher.setBorder(null);
			mntmPrintVoucher.setBackground(new Color(255, 228, 196));
			mntmPrintVoucher.setBounds(501, 13, 147, 30);
			mntmPrintVoucher.setIcon(new ImageIcon(printer1));
			panel.add(mntmPrintVoucher);
			
			if(Mainpage.customerpaymenthist.equals("1"))
			{
				table.addMouseListener(new MouseAdapter() {
		            public void mousePressed(MouseEvent mouseEvent) {
		                JTable table =(JTable) mouseEvent.getSource();
		                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		                	int rows = table.getSelectedRow();
		                	String voucher = table.getModel().getValueAt(rows, 1).toString();
		                	new CustomerPaymentChange(company, store_name, username, decimal,voucher, language);
		                }
		                
		            }
		        });
			}
			get_register_name(company,reg_nme);
			get_store_validate_dt(company,store_name,username, reg_nme);
			get_full_paycode(company, reg_nme,username,store_name);
	}
	private void get_full_paycode(String company,String reg_nme,String username,String store_name)
	{
		Connection con=Dbsetup.local_server.getConnection();
		Statement statement;
		try {
			
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		statement = con.createStatement();
		//String query="SELECT mr_pro.mr_tmp_item_master.department,mr_pro.mr_tmp_item_master.item_code,mr_pro.mr_tmp_item_master.item_name,sum(mr_pro.mr_pos_settlement_cash_items.qty) as qty,sum(mr_pro.mr_pos_settlement_cash_items.unit_price*mr_pro.mr_pos_settlement_cash_items.qty) as net_sale,sum(mr_pro.mr_pos_settlement_cash_items.total+mr_pro.mr_pos_settlement_cash_items.vat) as sale_amt  FROM mr_pro.mr_tmp_item_master INNER JOIN mr_pro.mr_pos_settlement_cash_items on mr_pro.mr_pos_settlement_cash_items.item_code=mr_pro.mr_tmp_item_master.item_code INNER JOIN mr_pro.mr_pos_settlement_cash on mr_pro.mr_pos_settlement_cash.reg_num=mr_pro.mr_pos_settlement_cash_items.ref_reg_num and mr_pro.mr_pos_settlement_cash.receipt_no=mr_pro.mr_pos_settlement_cash_items.ref_receipt_num where mr_pro.mr_pos_settlement_cash_items.ref_reg_num='"+reg_nme+"' and (mr_pos_settlement_cash.type='PAYCODE' or mr_pos_settlement_cash.type='CASH') GROUP BY mr_pro.mr_tmp_item_master.department";
		String query="SELECT * from "+company+".mr_customer_payment inner join "+company+".mr_customer_master on mr_customer_payment.cust_code=mr_customer_master.cust_code where mr_customer_payment.status='0'";
		ResultSet resultSet0 = statement.executeQuery(query);
		double net_payment=0;
		while(resultSet0.next())
		{
			String seq_num=resultSet0.getString("seq_num");
			String code=resultSet0.getString("cust_code");
			String name=resultSet0.getString("cust_name");
			String amt=resultSet0.getString("amount");
			String paycode=resultSet0.getString("paycode");
			net_payment+=Double.parseDouble(amt);
			model.addRow(new Object[]{model.getRowCount()+1,seq_num,code,name,amt,paycode});
		}
		String ttlnet_payment=df2.format(net_payment);
		textField.setText(ttlnet_payment);
		con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	public void get_register_name(String company, String reg_nme){
		try {
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_nme+"' ");
			if(resultSet2.next())
			{
				printer=resultSet2.getString("printer1");
			}
			
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static void get_store_validate_dt(String company,String store_name,String username, String posreg)
	{
		try {
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store where store_id='"+store_name+"' ");
			if(resultSet.next())
			{
				
				decimal=Integer.parseInt(resultSet.getString("decimal_count"));
				if(decimal==2)
				{
					df2=new DecimalFormat("0.00");
				}
				else
				{
					df2=new DecimalFormat("0.000");
				}
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
