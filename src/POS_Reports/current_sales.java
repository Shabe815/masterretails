package POS_Reports;

import java.awt.Color;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import CafePro.Mainpage;

import javax.swing.JComboBox;

public class current_sales {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private String printer;
	private static int decimal;
	private static DecimalFormat df2;
	private JComboBox<String> comboBox ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					current_sales window = new current_sales("mr_pro","S01","CASHIER 2", null, "S0102","2");
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
	public current_sales(String company, String store_name, String username,String version,String reg_nme,String decimal) {
		initialize(company,store_name,username, version,reg_nme,decimal);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store_name 
	 * @param host_name 
	 * @param company 
	 */
	private void initialize(final String company,final String store_name,final String username,String version,final String reg_nme,final String decimal) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 792, 720);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				Mainpage.btnXReports.setEnabled(true); 
			}
		});
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		Image printer1=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 786, 682);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 106, 786, 576);
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
		    model.addColumn("Paycode"); 
			model.addColumn("Opening Amt/QTY"); 
			model.addColumn("Sales Amt/QTY"); 
		
	
			int a=table.getColumnCount();
			for(int i=0;i<a;i++)
			{
				TableColumn column=table.getColumnModel().getColumn(i);
				if(i==0){
					column.setPreferredWidth(15);
				}
				if(i==3){
					column.setPreferredWidth(25);
				}
				if(i==4){
					column.setPreferredWidth(10);
				}
				if(i==5){
					column.setPreferredWidth(10);
				}
			}
		    scrollPane.setViewportView(table);
			
			
			
			JLabel lblCloseSales = new JLabel("Sales Summary");
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
						String counter="";
						if(comboBox.getSelectedItem().toString().equals("ALL"))
						{
							counter="S0101";
						}
						else
						{
							counter=comboBox.getSelectedItem().toString();
						}
						report_master.Xreports.report(counter, con, "XRPT",printer,"View",company,"");
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			menuItem_1.setBorder(null);
			menuItem_1.setBackground(new Color(255, 228, 196));
			menuItem_1.setIcon(new ImageIcon(printer1));
			
			JLabel lblSelectACounter = new JLabel("Select a counter");
			lblSelectACounter.setBounds(10, 75, 101, 14);
			panel.add(lblSelectACounter);
			
			comboBox = new JComboBox<String>();
			comboBox.addItem("ALL");
			comboBox.setBounds(121, 75, 112, 20);
			panel.add(comboBox);
			try
			{
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				ResultSet resultSet = statement.executeQuery("select register_number from "+company+".mr_register_setup ");
				while(resultSet.next())
				{
					comboBox.addItem(resultSet.getString("register_number"));
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(frame, e.getMessage());
			}
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(comboBox.getSelectedItem().toString().equals("ALL"))
					{
						get_register_name(company,"S0101");
						get_store_validate_dt(company,store_name,username, comboBox.getSelectedItem().toString());
						total_sale(company, "S0101",username,store_name);
					}
					else
					{
						get_register_name(company,comboBox.getSelectedItem().toString());
						get_store_validate_dt(company,store_name,username, comboBox.getSelectedItem().toString());
						get_full_paycode(company, comboBox.getSelectedItem().toString(),username,store_name);
					}
					
				}

				
			});
			
			
	}
	private void total_sale(String company,String reg_nme,String username,String store_name) {
		
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	    final Calendar cal = Calendar.getInstance();
	    final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
	    //final SimpleDateFormat times = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    String date_time=formattedDate +" "+times.format(cal.getTime()) ;
	    int rowCount = model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	    Connection con=Dbsetup.local_server.getConnection();
		Statement statement;
		Statement statement1;
		HashMap<String, Integer> cardqty = new HashMap<String, Integer>();
		InetAddress addr = null;
	    try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String sys_name = addr.getHostName();
		List<String> cardlist = new ArrayList<String>();
		try {
			statement = con.createStatement();
			statement1 = con.createStatement();
			ResultSet resultSet1 = statement1.executeQuery("select item_code from "+company+".mr_item_open_qty");
			if(resultSet1.next())
			{
				ResultSet resultSet0 = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash_items where item_code='"+resultSet1.getString("item_code")+"' ");
				while(resultSet0.next())
				{
					for (String str : cardlist) {
					    if (resultSet0.getString("item_code").equals(str)) {
					    	if(cardqty.containsKey(str))
					    	{
					    		cardqty.put(str, cardqty.get(str) + 1);
					    	}
					    	else
					    	{
					    		cardqty.put(str, 1);
					    	}
					    } 
					}
				}
			}
			String invo_no="0";
			ResultSet resultSet33 = statement.executeQuery("select count(*) as ttl_cnt from "+company+".mr_pos_settlement_cash ");
			if(resultSet33.next())
			{
				invo_no=resultSet33.getString("ttl_cnt");
			}
			ResultSet resultSet34 = statement.executeQuery("select * from "+company+".mr_close_pay_list  ");
			if(resultSet34.next())
			{
				String query="delete from "+company+".mr_close_pay_list  ";
				statement.executeUpdate(query);
			}
			String get_ttl_vat= "select * from "+company+".mr_pos_settlement_cash where  type!='VOID' ";
			ResultSet resultSet39 = statement1.executeQuery(get_ttl_vat);
			float vat_amt=0;
			while(resultSet39.next())
			{
				vat_amt+=Float.parseFloat(resultSet39.getString("vat"));
			}
			String query="insert into "+company+".mr_close_pay_list (`ref_reg_num`, `username`, `store`, `computer`, `date_time`,remarks, `created_date`,invoice_num,total_vat) values('"+reg_nme+"','"+username+"','"+store_name+"','"+sys_name+"','"+date_time+"','"+formattedDate+"','"+formattedDate+"','"+invo_no+"','"+vat_amt+"')";
			statement.executeUpdate(query);
			
			ResultSet resultSet35 = statement.executeQuery("select * from "+company+".mr_close_pay_list_items");
			if(resultSet35.next())
			{
				String query3="delete from "+company+".mr_close_pay_list_items   ";
				statement.executeUpdate(query3);
			}
			String get_cust_paylist= "select * from "+company+".mr_customer_payment where  status='0'  ";
			ResultSet resultSet7 = statement.executeQuery(get_cust_paylist);
			float cust_amt=0;
			float cust_amt_bnk=0;
			while(resultSet7.next())
			{
				if(resultSet7.getString("paycode").equals("CASH"))
				{
					cust_amt+=Float.parseFloat(resultSet7.getString("amount")) ;
				}
				else
				{
					cust_amt_bnk+=Float.parseFloat(resultSet7.getString("amount")) ;
				}
				
			}
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_register_open_items group by paycode ");
			while(resultSet.next())
			{
				String pay_desc=resultSet.getString("pay_desc");
				if(!pay_desc.equals("STORE CREDIT"))
				{
					float sales_amt=0;
					float vat=0;
					float ex_rate=0;
					double diff=0;
					String get_ttl_cash= "select * from "+company+".mr_pos_settlement_cash where type='PAYCODE' and paycode_disc!='0' ";
					ResultSet resultSet37 = statement1.executeQuery(get_ttl_cash);
					while(resultSet37.next())
					{
						if(pay_desc.equals("CASH"))
						{
							sales_amt+=Float.parseFloat(resultSet37.getString("paycode_disc"));
						}
					}
					String get_ttl_amt="";
					if(pay_desc.equals("CASH"))
					{
						get_ttl_amt= "select * from "+company+".mr_pos_settlement_cash where (type!='PAYCODE' AND type!='RETURN' AND type!='A/R' AND type!='VOID') ";
					}
					else
					{
						get_ttl_amt= "select * from "+company+".mr_pos_settlement_cash where type='"+pay_desc+"' AND type!='VOID'";
					}
					//
					
					ResultSet resultSet3 = statement1.executeQuery(get_ttl_amt);
					while(resultSet3.next())
					{
						sales_amt+=Float.parseFloat(resultSet3.getString("grand_total"));
						sales_amt+=Float.parseFloat(resultSet3.getString("paycode_disc"));
						vat+=Float.parseFloat(resultSet3.getString("vat"));
					}
					//String get_paycode_list= "select * from "+company+".mr_paycode_settlement where paycode_name='"+pay_desc+"' and status!='1'  ";
					String get_paycode_list= "select pay_code_vat,paycode_amt from "+company+".mr_paycode_settlement inner join "+company+".mr_pos_settlement_cash on mr_pos_settlement_cash.reg_num=mr_paycode_settlement.ref_reg_num and mr_pos_settlement_cash.receipt_no=mr_paycode_settlement.ref_receipt_num where paycode_name='"+pay_desc+"'  and status!='1' and type!='VOID'  ";
					ResultSet resultSet4 = statement1.executeQuery(get_paycode_list);
					while(resultSet4.next())
					{
						sales_amt+=Float.parseFloat(resultSet4.getString("paycode_amt"));
					}
					if(pay_desc.equals("VISA"))
					{
						sales_amt=sales_amt+cust_amt_bnk;
					}
					String get_store_paycode_list= "select * from "+company+".mr_store_paycodes where paycode='"+pay_desc+"' ";
					ResultSet resultSet5 = statement1.executeQuery(get_store_paycode_list);
					if(resultSet5.next())
					{
						ex_rate=Float.parseFloat(resultSet5.getString("ex_rate"));
					}
					double open_amt = 0;
					ResultSet resultSet2 = statement1.executeQuery("select * from "+company+".mr_register_open_items where  paycode='"+pay_desc+"' ");
					while(resultSet2.next())
					{
						open_amt+=Double.parseDouble(resultSet2.getString("amount"));
					}
					String get_card_list= "select * from "+company+".mr_tmp_item_master  where item_name='"+pay_desc+"'";
					ResultSet resultSet13 = statement1.executeQuery(get_card_list);
					if(resultSet13.next())
					{
						String code=resultSet13.getString("item_code");
						if(cardqty.containsKey(code))
				    	{
				    		sales_amt=cardqty.get(code);
				    	}
					}
					double ttl_sale=(sales_amt);
	        		BigDecimal be = new BigDecimal(ttl_sale);
	        		be = be.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	        		ttl_sale = be.doubleValue();
					
	        		String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt,vat_amt) values('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','"+pay_desc+"','"+open_amt+"','"+df2.format(sales_amt)+"','"+df2.format(open_amt+sales_amt)+"','0','"+ex_rate+"','"+df2.format(diff)+"','"+formattedDate+"','"+((sales_amt)-(vat))*ex_rate+"','"+vat+"') ";
					statement1.executeUpdate(add_open_itm);
					model.addRow(new Object[]{model.getRowCount()+1,pay_desc,open_amt,ttl_sale});
				}
			}
			String get_paylist="select * from "+company+".mr_paylist where  status='0'  ";
			ResultSet resultSet6 = statement.executeQuery(get_paylist);
			float pay_in=0;
			float pay_out=0;
			while(resultSet6.next())
			{
				if(resultSet6.getString("type").equals("payin"))
				{
					pay_in+=Float.parseFloat(resultSet6.getString("amount")) ;
				}
				else{
					pay_out+=Float.parseFloat(resultSet6.getString("amount")) ;
				}
			}
			model.addRow(new Object[]{model.getRowCount()+1,"PAYIN","0",pay_in});
			model.addRow(new Object[]{model.getRowCount()+1,"PAYOUT","0",pay_out});
			
			String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt) values('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','CUSTOMERPAYMENTS','0','"+cust_amt+"','"+cust_amt+"','0','1','"+(0-cust_amt)+"','"+formattedDate+"','"+cust_amt+"'),('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','PAYIN','0','"+pay_in+"','"+pay_in+"','0','1','"+(0-pay_in)+"','"+formattedDate+"','"+pay_in+"'),('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','PAYOUT','0','"+pay_out+"','"+pay_out+"','0','1','"+(0-pay_out)+"','"+formattedDate+"','"+pay_out+"') ";
			statement.executeUpdate(add_open_itm);
			model.addRow(new Object[]{model.getRowCount()+1,"CUSTOMERPAYMENTS",0,cust_amt});
			
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	private void get_full_paycode(String company,String reg_nme,String username,String store_name)
	{
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	    final Calendar cal = Calendar.getInstance();
	    final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
	    //final SimpleDateFormat times = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    String date_time=formattedDate +" "+times.format(cal.getTime()) ;
	    int rowCount = model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	    Connection con=Dbsetup.local_server.getConnection();
		Statement statement;
		Statement statement1;
		HashMap<String, Integer> cardqty = new HashMap<String, Integer>();
		InetAddress addr = null;
	    try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String sys_name = addr.getHostName();
		List<String> cardlist = new ArrayList<String>();
		try {
			statement = con.createStatement();
			statement1 = con.createStatement();
			ResultSet resultSet1 = statement1.executeQuery("select item_code from "+company+".mr_item_open_qty");
			if(resultSet1.next())
			{
				ResultSet resultSet0 = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash_items where ref_reg_num='"+reg_nme+"' and item_code='"+resultSet1.getString("item_code")+"' ");
				while(resultSet0.next())
				{
					for (String str : cardlist) {
					    if (resultSet0.getString("item_code").equals(str)) {
					    	if(cardqty.containsKey(str))
					    	{
					    		cardqty.put(str, cardqty.get(str) + 1);
					    	}
					    	else
					    	{
					    		cardqty.put(str, 1);
					    	}
					    } 
					}
				}
			}
			String invo_no="0";
			ResultSet resultSet33 = statement.executeQuery("select count(*) as ttl_cnt from "+company+".mr_pos_settlement_cash where reg_num='"+reg_nme+"' ");
			if(resultSet33.next())
			{
				invo_no=resultSet33.getString("ttl_cnt");
			}
			ResultSet resultSet34 = statement.executeQuery("select * from "+company+".mr_close_pay_list where ref_reg_num='"+reg_nme+"' ");
			if(resultSet34.next())
			{
				String query="delete from "+company+".mr_close_pay_list where ref_reg_num='"+reg_nme+"' ";
				statement.executeUpdate(query);
			}
			String get_ttl_vat= "select * from "+company+".mr_pos_settlement_cash where reg_num='"+reg_nme+"' and type!='VOID' ";
			ResultSet resultSet39 = statement1.executeQuery(get_ttl_vat);
			float vat_amt=0;
			while(resultSet39.next())
			{
				vat_amt+=Float.parseFloat(resultSet39.getString("vat"));
			}
			String query="insert into "+company+".mr_close_pay_list (`ref_reg_num`, `username`, `store`, `computer`, `date_time`,remarks, `created_date`,invoice_num,total_vat) values('"+reg_nme+"','"+username+"','"+store_name+"','"+sys_name+"','"+date_time+"','"+formattedDate+"','"+formattedDate+"','"+invo_no+"','"+vat_amt+"')";
			statement.executeUpdate(query);
			
			ResultSet resultSet35 = statement.executeQuery("select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' ");
			if(resultSet35.next())
			{
				String query3="delete from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"'  ";
				statement.executeUpdate(query3);
			}
			String get_cust_paylist= "select * from "+company+".mr_customer_payment where ref_reg_num='"+reg_nme+"'  and status='0'  ";
			ResultSet resultSet7 = statement.executeQuery(get_cust_paylist);
			float cust_amt=0;
			float cust_amt_bnk=0;
			while(resultSet7.next())
			{
				if(resultSet7.getString("paycode").equals("CASH"))
				{
					cust_amt+=Float.parseFloat(resultSet7.getString("amount")) ;
				}
				else
				{
					cust_amt_bnk+=Float.parseFloat(resultSet7.getString("amount")) ;
				}
				
			}
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"' ");
			while(resultSet.next())
			{
				String pay_desc=resultSet.getString("pay_desc");
				if(!pay_desc.equals("STORE CREDIT"))
				{
					float sales_amt=0;
					float vat=0;
					float ex_rate=0;
					double diff=0;
					String get_ttl_cash= "select * from "+company+".mr_pos_settlement_cash where type='PAYCODE' AND reg_num='"+reg_nme+"' and paycode_disc!='0' ";
					ResultSet resultSet37 = statement1.executeQuery(get_ttl_cash);
					while(resultSet37.next())
					{
						if(pay_desc.equals("CASH"))
						{
							sales_amt+=Float.parseFloat(resultSet37.getString("paycode_disc"));
						}
					}
					String get_ttl_amt="";
					if(pay_desc.equals("CASH"))
					{
						get_ttl_amt= "select * from "+company+".mr_pos_settlement_cash where reg_num='"+reg_nme+"' AND (type!='PAYCODE' AND type!='RETURN' AND type!='A/R' AND type!='VOID') ";
					}
					else
					{
						get_ttl_amt= "select * from "+company+".mr_pos_settlement_cash where type='"+pay_desc+"' AND reg_num='"+reg_nme+"' AND type!='VOID'";
					}
					//
					
					ResultSet resultSet3 = statement1.executeQuery(get_ttl_amt);
					while(resultSet3.next())
					{
						sales_amt+=Float.parseFloat(resultSet3.getString("grand_total"));
						sales_amt+=Float.parseFloat(resultSet3.getString("paycode_disc"));
						vat+=Float.parseFloat(resultSet3.getString("vat"));
					}
					String get_paycode_list= "select pay_code_vat,paycode_amt from "+company+".mr_paycode_settlement inner join "+company+".mr_pos_settlement_cash on mr_pos_settlement_cash.reg_num=mr_paycode_settlement.ref_reg_num and mr_pos_settlement_cash.receipt_no=mr_paycode_settlement.ref_receipt_num where paycode_name='"+pay_desc+"' AND ref_reg_num='"+reg_nme+"' and status!='1' and type!='VOID'  ";

					//String get_paycode_list= "select * from "+company+".mr_paycode_settlement where paycode_name='"+pay_desc+"' AND ref_reg_num='"+reg_nme+"' and status!='1' ";
					ResultSet resultSet4 = statement1.executeQuery(get_paycode_list);
					while(resultSet4.next())
					{
						sales_amt+=Float.parseFloat(resultSet4.getString("paycode_amt"));
					}
					if(pay_desc.equals("VISA"))
					{
						sales_amt=sales_amt+cust_amt_bnk;
					}
					String get_store_paycode_list= "select * from "+company+".mr_store_paycodes where paycode='"+pay_desc+"' ";
					ResultSet resultSet5 = statement1.executeQuery(get_store_paycode_list);
					if(resultSet5.next())
					{
						ex_rate=Float.parseFloat(resultSet5.getString("ex_rate"));
					}
					String open_amt = "";
					ResultSet resultSet2 = statement1.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"' and paycode='"+pay_desc+"' ");
					if(resultSet2.next())
					{
						open_amt=resultSet2.getString("amount");
					}
					String get_card_list= "select * from "+company+".mr_tmp_item_master  where item_name='"+pay_desc+"'";
					ResultSet resultSet13 = statement1.executeQuery(get_card_list);
					if(resultSet13.next())
					{
						String code=resultSet13.getString("item_code");
						if(cardqty.containsKey(code))
				    	{
				    		sales_amt=cardqty.get(code);
				    	}
					}
					double ttl_sale=(sales_amt);
	        		BigDecimal be = new BigDecimal(ttl_sale);
	        		be = be.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	        		ttl_sale = be.doubleValue();
					
	        		String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt,vat_amt) values('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','"+pay_desc+"','"+open_amt+"','"+df2.format(sales_amt)+"','"+df2.format(Float.parseFloat(open_amt)+sales_amt)+"','0','"+ex_rate+"','"+df2.format(diff)+"','"+formattedDate+"','"+((sales_amt)-(vat))*ex_rate+"','"+vat+"') ";
					statement1.executeUpdate(add_open_itm);
					model.addRow(new Object[]{model.getRowCount()+1,pay_desc,open_amt,ttl_sale});
				}
			}
			String get_paylist="select * from "+company+".mr_paylist where ref_reg_no='"+reg_nme+"' and status='0'  ";
			ResultSet resultSet6 = statement.executeQuery(get_paylist);
			float pay_in=0;
			float pay_out=0;
			while(resultSet6.next())
			{
				if(resultSet6.getString("type").equals("payin"))
				{
					pay_in+=Float.parseFloat(resultSet6.getString("amount")) ;
				}
				else{
					pay_out+=Float.parseFloat(resultSet6.getString("amount")) ;
				}
			}
			model.addRow(new Object[]{model.getRowCount()+1,"PAYIN","0",pay_in});
			model.addRow(new Object[]{model.getRowCount()+1,"PAYOUT","0",pay_out});
			
			String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt) values('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','CUSTOMERPAYMENTS','0','"+cust_amt+"','"+cust_amt+"','0','1','"+(0-cust_amt)+"','"+formattedDate+"','"+cust_amt+"'),('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','PAYIN','0','"+pay_in+"','"+pay_in+"','0','1','"+(0-pay_in)+"','"+formattedDate+"','"+pay_in+"'),('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','PAYOUT','0','"+pay_out+"','"+pay_out+"','0','1','"+(0-pay_out)+"','"+formattedDate+"','"+pay_out+"') ";
			statement.executeUpdate(add_open_itm);
			model.addRow(new Object[]{model.getRowCount()+1,"CUSTOMERPAYMENTS",0,cust_amt});
			
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
