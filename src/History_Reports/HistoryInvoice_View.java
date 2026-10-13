package History_Reports;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.json.JSONException;

public class HistoryInvoice_View {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private static int decimal;
	private static DecimalFormat df2;
	private JLabel lblcashier;
	private JLabel lblinv;
	private JLabel lblDatetime;
	private JLabel reg;
	private JLabel lblTotalQty;
	private JLabel lblPrice;
	private JLabel lblDiscount;
	private JLabel lblSubTotal;
	private JLabel lblVat;
	private JLabel lblGrandTotal;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel lblPaymentSummary;
	private JLabel lblpayment;
	private JLabel changedue;
	private JButton btnChangePaycode;
	public static JTextField lblcustname;
	public static JTextField lblCust;
	private JLabel lblCash;
	private JLabel lblCard;
	private JLabel lblAr;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewPaymentMode;
	private JButton btnUpdateTable;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoryInvoice_View window = new HistoryInvoice_View("mr_pro","S01","SHABEER", null, "S0101","2","","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param inv 
	 */
	public HistoryInvoice_View(String company, String store_name, String username,String version,String reg_nme,String decimal, String inv, String language) {
		initialize(company,store_name,username, version,reg_nme,decimal,inv,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store_name 
	 * @param host_name 
	 * @param company 
	 * @param inv 
	 * @param language 
	 */
	private void initialize(final String company,final String store_name,final String username,String version,final String reg_nme,final String decimal,final String inv, String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 792, 751);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 786, 716);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 155, 786, 309);
		panel.add(scrollPane);
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
				if(column==3)
				{
					return true;
				}
				if(column==4)
				{
					return true;
				}
				if(column==5)
				{
					return true;
				}
				if(column==6)
				{
					return true;
				}
				
		       return false;
		    }
			
			
		};
		table = new JTable(model);
		
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(20);
			if(language.equals("Arabic"))
			{
				model.addColumn("«·—ﬁ„ «· ”·”·Ì"); 
			    model.addColumn("«·»«—ﬂÊœ");
			    model.addColumn("—„“ «·⁄‰’—");
			    model.addColumn(" ›«’Ì· «·”·⁄…");
			    model.addColumn("«·ﬂ„Ì…"); 
				model.addColumn("”⁄— «·ÊÕœ…"); 
				model.addColumn("«·„Ã„Ê⁄");
				model.addColumn("÷—Ì»…");
				model.addColumn("Ìﬂ »");
				model.addColumn("id");
			}
			else
			{
				model.addColumn("Sl No"); 
			    model.addColumn("Barcode");
			    model.addColumn("itemcode");
			    model.addColumn("Item Details");
			    model.addColumn("Qty"); 
				model.addColumn("Unit Price"); 
				model.addColumn("Total");
				model.addColumn("Vat");
				model.addColumn("type");
				model.addColumn("id");
			}
		
	
			int columns=model.getColumnCount();
			for (int i=0; i<columns; i++) {
				  TableColumn column = table.getColumnModel().getColumn(i);
				  if (i==0) column.setPreferredWidth(50);
				  if (i==1) column.setPreferredWidth(120);
				  if (i==2) column.setPreferredWidth(100);
				  if (i==3) column.setPreferredWidth(250);
				  if (i==4) column.setPreferredWidth(100);
				  if (i==5) column.setPreferredWidth(100);
				  if (i==6) column.setPreferredWidth(100);
				  if (i==7) column.setPreferredWidth(100);
				  if (i==8) column.setPreferredWidth(0);
				  if (i==9) column.setPreferredWidth(0);
				}
		    scrollPane.setViewportView(table);
			
			
			
			JLabel lblCloseSales = new JLabel("Invoice");
			if(language.equals("Arabic"))
			{
				lblCloseSales.setText("›« Ê—…");
			}
			lblCloseSales.setFont(new Font("Monospaced", Font.BOLD, 25));
			lblCloseSales.setBounds(12, 11, 147, 53);
			panel.add(lblCloseSales);
			
			JLabel lblRegister = new JLabel("Register");
			if(language.equals("Arabic"))
			{
				lblRegister.setText("Ì”Ã·");
			}
			lblRegister.setBounds(12, 64, 56, 16);
			panel.add(lblRegister);
			
			JLabel lblInvoice = new JLabel("Invoice#");
			if(language.equals("Arabic"))
			{
				lblInvoice.setText("›« Ê—…#");
			}
			lblInvoice.setBounds(306, 64, 56, 16);
			panel.add(lblInvoice);
			
			JLabel lblDate = new JLabel("Date:");
			if(language.equals("Arabic"))
			{
				lblDate.setText(" «—ÌŒ:");
			}
			lblDate.setBounds(588, 93, 56, 16);
			panel.add(lblDate);
			
			JLabel lblCashier = new JLabel("Cashier");
			if(language.equals("Arabic"))
			{
				lblCashier.setText("√„Ì‰ «·’‰œÊﬁ");
			}
			lblCashier.setBounds(588, 64, 56, 16);
			panel.add(lblCashier);
			
			JLabel lblCustomer = new JLabel("Customer");
			if(language.equals("Arabic"))
			{
				lblCustomer.setText("⁄„Ì·");
			}
			lblCustomer.setBounds(12, 93, 56, 16);
			panel.add(lblCustomer);
			
			reg = new JLabel("New label");
			
			reg.setBounds(80, 64, 171, 16);
			panel.add(reg);
			
			lblDatetime = new JLabel("datetime");
			
			lblDatetime.setBounds(633, 93, 141, 16);
			panel.add(lblDatetime);
			
			lblcashier = new JLabel("cashier");
			
			lblcashier.setBounds(643, 64, 131, 16);
			panel.add(lblcashier);
			
			lblinv = new JLabel("New label");
			
			lblinv.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblinv.setBounds(374, 64, 180, 16);
			panel.add(lblinv);
			
			lblTotalQty = new JLabel("Total Qty");
			if(language.equals("Arabic"))
			{
				lblTotalQty.setText("≈Ã„«·Ì «·ﬂ„Ì…");
			}
			lblTotalQty.setBounds(10, 477, 56, 16);
			panel.add(lblTotalQty);
			
			lblPrice = new JLabel("Price");
			if(language.equals("Arabic"))
			{
				lblPrice.setText("”⁄—");
			}
			lblPrice.setBounds(166, 477, 56, 16);
			panel.add(lblPrice);
			
			lblDiscount = new JLabel("Discount");
			if(language.equals("Arabic"))
			{
				lblDiscount.setText(" Œ›Ì÷");
			}
			lblDiscount.setBounds(319, 477, 56, 16);
			panel.add(lblDiscount);
			
			lblSubTotal = new JLabel("Sub total");
			if(language.equals("Arabic"))
			{
				lblSubTotal.setText("«·„Ã„Ê⁄ «·›—⁄Ì");
			}
			lblSubTotal.setBounds(454, 477, 56, 16);
			panel.add(lblSubTotal);
			
			lblVat = new JLabel("Vat");
			if(language.equals("Arabic"))
			{
				lblVat.setText("÷—Ì»…");
			}
			lblVat.setBounds(588, 477, 56, 16);
			panel.add(lblVat);
			
			lblGrandTotal = new JLabel("Grand Total");
			if(language.equals("Arabic"))
			{
				lblGrandTotal.setText("«·„Ã„Ê⁄ «·≈Ã„«·Ì");
			}
			lblGrandTotal.setBounds(692, 477, 82, 16);
			panel.add(lblGrandTotal);
			
			label_5 = new JLabel("Total Qty");
			
			label_5.setBounds(12, 512, 56, 16);
			panel.add(label_5);
			
			label_6 = new JLabel("Total Qty");
			
			label_6.setBounds(166, 512, 56, 16);
			panel.add(label_6);
			
			label_7 = new JLabel("Total Qty");
			
			label_7.setBounds(319, 512, 56, 16);
			panel.add(label_7);
			
			label_8 = new JLabel("Total Qty");
			
			label_8.setBounds(454, 512, 56, 16);
			panel.add(label_8);
			
			label_9 = new JLabel("Total Qty");
			
			label_9.setBounds(588, 512, 56, 16);
			panel.add(label_9);
			
			label_10 = new JLabel("Total Qty");
			
			label_10.setBounds(692, 512, 82, 16);
			panel.add(label_10);
			
			lblPaymentSummary = new JLabel("Payment Summary");
			if(language.equals("Arabic"))
			{
				lblPaymentSummary.setText("„·Œ’ «·œ›⁄");
			}
			lblPaymentSummary.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblPaymentSummary.setBounds(12, 572, 147, 16);
			panel.add(lblPaymentSummary);
			
			lblpayment = new JLabel("New label");
			
			lblpayment.setBounds(12, 601, 510, 16);
			panel.add(lblpayment);
			
			changedue = new JLabel("New label");
			
			changedue.setBounds(12, 624, 762, 16);
			panel.add(changedue);
			
			btnChangePaycode = new JButton("Update Invoice");
			if(language.equals("Arabic"))
			{
				btnChangePaycode.setText(" ÕœÌÀ «·›« Ê—…");
			}
			btnChangePaycode.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try
					{
						int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Update Invoice Â·  —Ìœ  ÕœÌÀ «·›« Ê—…ø","Warning",JOptionPane.YES_NO_OPTION);
				        // 0=yes, 1=no, 2=cancel
				        if(cofrm_bx==0)
				        {
				        	Connection con=Dbsetup.local_server.getConnection();
							Statement statement=con.createStatement();
							Statement statement1=con.createStatement();
							Statement statement2=con.createStatement();
							Statement statement3=con.createStatement();
							Statement statement4=con.createStatement();
							Statement statement5=con.createStatement();
							Statement statement6=con.createStatement();
							Statement statement7=con.createStatement();
							Statement statement8=con.createStatement();
							Statement statement9=con.createStatement();
							Statement statement10=con.createStatement();
							Statement statement11=con.createStatement();
							Statement statement12=con.createStatement();
							
							String newcash=textField.getText().trim();
							if(newcash.equals(""))
							{
								newcash="0";
							}
							String newcard=textField_1.getText().trim();
							if(newcard.equals(""))
							{
								newcard="0";
							}
							String newar=textField_2.getText().trim();
							if(newar.equals(""))
							{
								newar="0";
							}
							
							double newttl=Double.parseDouble(newcash)+Double.parseDouble(newcard)+Double.parseDouble(newar);
							if(newttl==Double.parseDouble(label_10.getText()))
							{
								String query="SELECT * from "+company+".mr_pos_settlement_history where reg_num='"+reg_nme+"' and  receipt_no='"+inv+"'";
								ResultSet resultSet = statement.executeQuery(query);
								if(resultSet.next())
								{
									String cashamt="0";
									String visaamt="0";
									String aramt="0";
									String grand_total=resultSet.getString("grand_total");
									String created_date=resultSet.getString("created_date");
									String oldcustomer=resultSet.getString("cust_code");
									String paycode=resultSet.getString("type");
									String jvnum=resultSet.getString("jv_num");
									if(paycode.equals("PAYCODE"))
									{
										ResultSet resultSet1 = statement1.executeQuery("select * from "+company+".mr_paycode_settlement_history where ref_reg_num='"+reg_nme+"' and  ref_receipt_num='"+inv+"'");
										while(resultSet1.next())
										{
											if(resultSet1.getString("paycode_name").equals("CASH"))
											{
												cashamt=resultSet1.getString("paycode_amt");
											}
											if(resultSet1.getString("paycode_name").equals("VISA"))
											{
												visaamt=resultSet1.getString("paycode_amt");
											}
											if(resultSet1.getString("paycode_name").equals("A/R"))
											{
												aramt=resultSet1.getString("paycode_amt");
											}
										}
									}
									else
									{
										if(paycode.equals("CASH"))
										{
											cashamt=resultSet.getString("grand_total");
										}
										else
										{
											aramt=resultSet.getString("grand_total");
										}
									}
									double oldcustbalance=0;
									double newcustbalance=0;
									String default_ar_acc="";
									ResultSet resultSet2 = statement2.executeQuery("select * from "+company+".mr_customer_master where cust_code='"+lblCust.getText()+"' ");
									if(resultSet2.next())
									{
										String oldcustcredit_limit=resultSet2.getString("credit_limit");
										String oldcustavail_credit=resultSet2.getString("avail_credit");
										newcustbalance=Double.parseDouble(oldcustcredit_limit)-Double.parseDouble(oldcustavail_credit);
										default_ar_acc=resultSet2.getString("default_ar_acc");
									}
									String default_oldar_acc="";
									ResultSet resultSet8 = statement8.executeQuery("select * from "+company+".mr_customer_master where cust_code='"+oldcustomer+"' ");
									if(resultSet8.next())
									{
										String oldcustcredit_limit=resultSet8.getString("credit_limit");
										String oldcustavail_credit=resultSet8.getString("avail_credit");
										oldcustbalance=Double.parseDouble(oldcustcredit_limit)-Double.parseDouble(oldcustavail_credit);
										default_oldar_acc=resultSet8.getString("default_ar_acc");
									}
									String oldarpayable=default_oldar_acc;
									String ar_payable=default_ar_acc;
									String cash_acc="";
					                String query3="SELECT * FROM "+company+".mr_store_accounts where  ref_store_id='"+store_name+"' "; 
									ResultSet resultSet3 = statement3.executeQuery(query3);
					                if(resultSet3.next())
					                {
					                	cash_acc=resultSet3.getString("cash_acc");
					                }
					                String bank_acc="";
					                String query4="SELECT * FROM "+company+".mr_acc_paycode where status='1' "; 
									ResultSet resultSet4 = statement4.executeQuery(query4);
					                if(resultSet4.next())
					                {
					                	bank_acc=resultSet4.getString("default_ac");
					                }
					                String query5="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_store='"+store_name+"'"; 
									ResultSet resultSet5 = statement5.executeQuery(query5);
					                String bankname="";
					                String cash_name="";
					                String ar_name="";
					                
					                while(resultSet5.next())
					                {
					                	if(resultSet5.getString("acc_code").equals(ar_payable))
					                	{
					                		ar_name=resultSet5.getString("acc_name");
					                	}
					                	
					                	if(resultSet5.getString("acc_code").equals(cash_acc))
					                	{
					                		cash_name=resultSet5.getString("acc_name");
					                	}
					                	if(resultSet5.getString("acc_code").equals(bank_acc))
					                	{
					                		bankname=resultSet5.getString("acc_name");
					                	}
					                }
									//update
					                if(!grand_total.equals(label_10.getText()))
					                {
					                	String poslines="select * from "+company+".mr_pos_settlement_history_items where ref_reg_num='"+reg_nme+"' and  ref_receipt_num='"+inv+"'";
						                ResultSet resultSet10 = statement10.executeQuery(poslines);
						                while(resultSet10.next())
						                {
						                	String itemcode=resultSet10.getString("item_code");
						                	String qty=resultSet10.getString("qty");
						                	String itemmaster="select * from "+company+".mr_item_master where item_code='"+itemcode+"'";
						                	ResultSet resultSet11 = statement11.executeQuery(itemmaster);
							                if(resultSet11.next())
							                {
							                	String updateitem="update "+company+".mr_item_master set total_qty=total_qty+'"+qty+"' where item_code='"+itemcode+"'";
							                	statement12.executeUpdate(updateitem);
							                	
							                	String updatetmpitem="update "+company+".mr_tmp_item_master set qty=qty+'"+qty+"' where item_code='"+itemcode+"'";
							                	statement12.executeUpdate(updatetmpitem);
							                }
						                }
						                for(int i=0;i<table.getRowCount();i++)
										{
						                	String newitemcode=model.getValueAt(i, 2).toString().trim();
						                	String newqty=model.getValueAt(i, 4).toString().trim();
						                	String newprice=model.getValueAt(i, 5).toString().trim();
						                	String newtotal=model.getValueAt(i, 6).toString().trim();
						                	String newvat=model.getValueAt(i, 7).toString().trim();
						                	String id=model.getValueAt(i, 9).toString().trim();
											String item_qty="update "+company+".mr_tmp_item_master set qty=qty-"+newqty+" where item_code='"+newitemcode+"'";
											statement12.executeUpdate(item_qty);
											String item_qty1="update "+company+".mr_item_master set total_qty=total_qty-"+newqty+" where item_code='"+newitemcode+"'";
											statement12.executeUpdate(item_qty1);
											String posline="update "+company+".mr_pos_settlement_history_items set unit_price="+newprice+",qty="+newqty+",total="+newtotal+",vat="+newvat+" where item_code='"+newitemcode+"' and id='"+id+"'  ";
											statement12.executeUpdate(posline);
											
										}
						                String poshead="update "+company+".mr_pos_settlement_history set total="+label_6.getText()+",qty="+label_5.getText()+",grand_total="+label_10.getText()+",vat="+label_9.getText()+" where reg_num='"+reg_nme+"' and receipt_no='"+inv+"'  ";
										statement12.executeUpdate(poshead);
					                }
					                if(!cashamt.equals("0"))
					                {
					                	String updatejv="update "+company+".mr_jv_details set dr_amt=dr_amt-'"+cashamt+"' where ref_jv_num='"+jvnum+"' and ac_code='"+cash_acc+"' ";
									    statement6.executeUpdate(updatejv);
									    
									    String updateclose="update "+company+".mr_close_pay_list_history set sales_amt1=sales_amt1-'"+cashamt+"',total_amt1=total_amt1-'"+cashamt+"',varience1=varience1+'"+cashamt+"' where remarks='"+jvnum+"' ";
									    statement6.executeUpdate(updateclose);
									    
									    String updatecloseitem="update "+company+".mr_close_pay_list_history_items set sales_amt=sales_amt-'"+cashamt+"',total_amt=total_amt-'"+cashamt+"',varience=varience+'"+cashamt+"' where jv_num='"+jvnum+"' and paycode='CASH' ";
									    statement6.executeUpdate(updatecloseitem);
									    
									    String updatepaycode="update "+company+".mr_paycode_settlement_history set paycode_amt=paycode_amt-'"+cashamt+"',paid_amt=paid_amt-'"+cashamt+"' where paycode_name='CASH' ";
									    statement6.executeUpdate(updatepaycode);
									    
					                }
					                if(!newcash.equals("0"))
					                {
					                	ResultSet resultSet6 = statement6.executeQuery("select * from "+company+".mr_jv_details where ref_jv_num='"+jvnum+"' and  ac_code='"+cash_acc+"' ");
										if(resultSet6.next())
										{
											String updatejv1="update "+company+".mr_jv_details set dr_amt=dr_amt+'"+newcash+"' where ref_jv_num='"+jvnum+"' and ac_code='"+cash_acc+"' ";
										    statement6.executeUpdate(updatejv1);
										}
										else
										{
											ResultSet resultSet7 = statement7.executeQuery("select * from "+company+".mr_jv_details where ref_jv_num='"+jvnum+"'");
											if(resultSet7.next())
											{
												String updatejv="insert into "+company+".mr_jv_details (ref_jv_num,jv_seq,ac_code,ac_desc,dr_amt,cr_amt,note,remark,created_date,modified_date,status,cheque_date)"
														+ "values('"+jvnum+"','"+jvnum+"','"+cash_acc+"','"+cash_name+"','"+newcash+"','0','"+resultSet7.getString("note")+"','"+resultSet7.getString("remark")+"','"+resultSet7.getString("created_date")+"','"+resultSet7.getString("modified_date")+"','OPEN','"+resultSet7.getString("cheque_date")+"') ";
											    statement6.executeUpdate(updatejv);
											}
										}
										ResultSet resultSet9 = statement9.executeQuery("select * from "+company+".mr_paycode_settlement_history where paycode_name='CASH' ");
										if(resultSet9.next())
										{
											String updatepaycode="update "+company+".mr_paycode_settlement_history set paycode_amt=paycode_amt+'"+newcash+"',paid_amt=paid_amt+'"+newcash+"' where paycode_name='CASH' ";
										    statement6.executeUpdate(updatepaycode);
										}
										else
										{
											String updatejv="insert into "+company+".mr_paycode_settlement_history (ref_reg_num,ref_receipt_num,paycode_name,paycode_rate,paycode_amt,paid_amt,created_date,status,pay_code_vat)"
													+ "values('"+reg_nme+"','"+inv+"','CASH','1','"+newcash+"','"+newcash+"','"+created_date+"','1','0') ";
										    statement6.executeUpdate(updatejv);
										}
										String updateclose="update "+company+".mr_close_pay_list_history set sales_amt1=sales_amt1+'"+newcash+"',total_amt1=total_amt1+'"+newcash+"',varience1=varience1-'"+newcash+"' where remarks='"+jvnum+"' ";
									    statement6.executeUpdate(updateclose);
									    String updatecloseitem="update "+company+".mr_close_pay_list_history_items set sales_amt=sales_amt+'"+newcash+"',total_amt=total_amt+'"+newcash+"',varience=varience-'"+newcash+"' where jv_num='"+jvnum+"' and paycode='CASH' ";
									    statement6.executeUpdate(updatecloseitem);
					                }
					                if(!visaamt.equals("0"))
					                {
					                	String updatejv="update "+company+".mr_jv_details set dr_amt=dr_amt-'"+visaamt+"' where ref_jv_num='"+jvnum+"' and ac_code='"+bank_acc+"' ";
									    statement6.executeUpdate(updatejv);
									    String updateclose="update "+company+".mr_close_pay_list_history set sales_amt4=sales_amt4-'"+visaamt+"',total_amt4=total_amt4-'"+visaamt+"',varience4=varience4+'"+visaamt+"' where remarks='"+jvnum+"' ";
									    statement6.executeUpdate(updateclose);
									    String updatecloseitem="update "+company+".mr_close_pay_list_history_items set sales_amt=sales_amt-'"+visaamt+"',total_amt=total_amt-'"+visaamt+"',varience=varience+'"+visaamt+"' where jv_num='"+jvnum+"' and paycode='VISA' ";
									    statement6.executeUpdate(updatecloseitem);
									    String updatepaycode="update "+company+".mr_paycode_settlement_history set paycode_amt=paycode_amt-'"+visaamt+"',paid_amt=paid_amt-'"+visaamt+"' where paycode_name='VISA' ";
									    statement6.executeUpdate(updatepaycode);
					                }
					                if(!newcard.equals("0"))
					                {
					                	ResultSet resultSet6 = statement6.executeQuery("select * from "+company+".mr_jv_details where ref_jv_num='"+jvnum+"' and  ac_code='"+bank_acc+"' ");
										if(resultSet6.next())
										{
											
										    String updatejv1="update "+company+".mr_jv_details set dr_amt=dr_amt+'"+newcard+"' where ref_jv_num='"+jvnum+"' and ac_code='"+bank_acc+"' ";
										    statement6.executeUpdate(updatejv1);
										}
										else
										{
											ResultSet resultSet7 = statement7.executeQuery("select * from "+company+".mr_jv_details where ref_jv_num='"+jvnum+"'");
											if(resultSet7.next())
											{
												String updatejv="insert into "+company+".mr_jv_details (ref_jv_num,jv_seq,ac_code,ac_desc,dr_amt,cr_amt,note,remark,created_date,modified_date,status,cheque_date)"
														+ "values('"+jvnum+"','"+jvnum+"','"+bank_acc+"','"+bankname+"','"+newcard+"','0','"+resultSet7.getString("note")+"','"+resultSet7.getString("remark")+"','"+resultSet7.getString("created_date")+"','"+resultSet7.getString("modified_date")+"','OPEN','"+resultSet7.getString("cheque_date")+"') ";
											    statement6.executeUpdate(updatejv);
											}
											
										}
										ResultSet resultSet9 = statement9.executeQuery("select * from "+company+".mr_paycode_settlement_history where paycode_name='VISA' and ref_reg_num='"+reg_nme+"' and ref_receipt_num='"+inv+"' ");
										if(resultSet9.next())
										{
											String updatepaycode="update "+company+".mr_paycode_settlement_history set paycode_amt=paycode_amt+'"+newcard+"',paid_amt=paid_amt+'"+newcard+"' where paycode_name='VISA' and ref_reg_num='"+reg_nme+"' and ref_receipt_num='"+inv+"' ";
										    statement6.executeUpdate(updatepaycode);
										}
										else
										{
											String updatejv="insert into "+company+".mr_paycode_settlement_history (ref_reg_num,ref_receipt_num,paycode_name,paycode_rate,paycode_amt,paid_amt,created_date,status,pay_code_vat)"
													+ "values('"+reg_nme+"','"+inv+"','VISA','1','"+newcard+"','"+newcard+"','"+created_date+"','1','0') ";
										    statement6.executeUpdate(updatejv);
										}
										String updateclose="update "+company+".mr_close_pay_list_history set sales_amt4=sales_amt4+'"+newcard+"',total_amt4=total_amt4+'"+newcard+"',varience4=varience4-'"+newcard+"' where remarks='"+jvnum+"' ";
									    statement6.executeUpdate(updateclose);
									    String updatecloseitem="update "+company+".mr_close_pay_list_history_items set sales_amt=sales_amt+'"+newcard+"',total_amt=total_amt+'"+newcard+"',varience=varience-'"+newcard+"' where jv_num='"+jvnum+"' and paycode='VISA' ";
									    statement6.executeUpdate(updatecloseitem);
					                }
					                if(!aramt.equals("0"))
					                {
					                	String updatejv="update "+company+".mr_jv_details set dr_amt=dr_amt-'"+aramt+"' where ref_jv_num='"+jvnum+"' and ac_code='"+oldarpayable+"' ";
									    statement6.executeUpdate(updatejv);
									    
									    String query1="update "+company+".mr_customer_master set  `avail_credit`=avail_credit+'"+aramt+"' where cust_code='"+oldcustomer+"'  ";
										statement6.executeUpdate(query1);
										
										String updateclose="update "+company+".mr_close_pay_list_history set sales_amt2=sales_amt2-'"+aramt+"',total_amt2=total_amt2-'"+aramt+"',varience2=varience2+'"+aramt+"' where remarks='"+jvnum+"' ";
									    statement6.executeUpdate(updateclose);
									    
									    String updatecloseitem="update "+company+".mr_close_pay_list_history_items set sales_amt=sales_amt-'"+aramt+"',total_amt=total_amt-'"+aramt+"',varience=varience+'"+aramt+"' where jv_num='"+jvnum+"' and paycode='A/R' ";
									    statement6.executeUpdate(updatecloseitem);
									    
									    String updatepaycode="update "+company+".mr_paycode_settlement_history set paycode_amt=paycode_amt-'"+aramt+"',paid_amt=paid_amt-'"+aramt+"' where paycode_name='A/R' ";
									    statement6.executeUpdate(updatepaycode);
									    
									    String updatecust="update "+company+".mr_pos_settlement_history set cust_prev_balance='"+oldcustbalance+"' where reg_num='"+reg_nme+"' and  receipt_no='"+inv+"'";
									    statement6.executeUpdate(updatecust);
					                }
					                if(!newar.equals("0"))
					                {
					                	ResultSet resultSet6 = statement6.executeQuery("select * from "+company+".mr_jv_details where ref_jv_num='"+jvnum+"' and  ac_code='"+ar_payable+"' ");
										if(resultSet6.next())
										{
											String updatejv1="update "+company+".mr_jv_details set dr_amt=dr_amt+'"+newar+"',ac_code='"+ar_payable+"',ac_desc='"+ar_name+"' where ref_jv_num='"+jvnum+"' and ac_code='"+ar_payable+"' ";
										    statement6.executeUpdate(updatejv1);
										    
										    String query2="update "+company+".mr_customer_master set  `avail_credit`=avail_credit-'"+newar+"' where cust_code='"+lblCust.getText()+"'  ";
											statement6.executeUpdate(query2);
										}
										else
										{
											ResultSet resultSet7 = statement7.executeQuery("select * from "+company+".mr_jv_details where ref_jv_num='"+jvnum+"'");
											if(resultSet7.next())
											{
												String updatejv="insert into "+company+".mr_jv_details (ref_jv_num,jv_seq,ac_code,ac_desc,dr_amt,cr_amt,note,remark,created_date,modified_date,status,cheque_date)"
														+ "values('"+jvnum+"','"+jvnum+"','"+ar_payable+"','"+ar_name+"','"+newar+"','0','"+resultSet7.getString("note")+"','"+resultSet7.getString("remark")+"','"+resultSet7.getString("created_date")+"','"+resultSet7.getString("modified_date")+"','OPEN','"+resultSet7.getString("cheque_date")+"') ";
											    statement6.executeUpdate(updatejv);
											    
											    String query2="update "+company+".mr_customer_master set  `avail_credit`=avail_credit-'"+newar+"' where cust_code='"+lblCust.getText()+"'  ";
												statement6.executeUpdate(query2);
											}
											
										}
										ResultSet resultSet9 = statement9.executeQuery("select * from "+company+".mr_paycode_settlement_history where paycode_name='A/R' ");
										if(resultSet9.next())
										{
											String updatepaycode="update "+company+".mr_paycode_settlement_history set paycode_amt=paycode_amt+'"+newar+"',paid_amt=paid_amt+'"+newar+"' where paycode_name='A/R' ";
										    statement6.executeUpdate(updatepaycode);
										}
										else
										{
											String updatejv="insert into "+company+".mr_paycode_settlement_history (ref_reg_num,ref_receipt_num,paycode_name,paycode_rate,paycode_amt,paid_amt,created_date,status,pay_code_vat)"
													+ "values('"+reg_nme+"','"+inv+"','A/R','1','"+newar+"','"+newar+"','"+created_date+"','1','0') ";
										    statement6.executeUpdate(updatejv);
										}
										String updateclose="update "+company+".mr_close_pay_list_history set sales_amt2=sales_amt2+'"+newar+"',total_amt2=total_amt2+'"+newar+"',varience2=varience2-'"+newar+"' where remarks='"+jvnum+"' ";
									    statement6.executeUpdate(updateclose);
									    
									    String updatecloseitem="update "+company+".mr_close_pay_list_history_items set sales_amt=sales_amt+'"+newar+"',total_amt=total_amt+'"+newar+"',varience=varience-'"+newar+"' where jv_num='"+jvnum+"' and paycode='A/R' ";
									    statement6.executeUpdate(updatecloseitem);
									    
									    String updatecust="update "+company+".mr_pos_settlement_history set cust_prev_balance='"+newcustbalance+"' where reg_num='"+reg_nme+"' and  receipt_no='"+inv+"'";
									    statement6.executeUpdate(updatecust);
					                	
					                }
					                String updateclose="update "+company+".mr_pos_settlement_history set type='PAYCODE',cust_name='"+lblcustname.getText()+"',cust_code='"+lblCust.getText()+"' where reg_num='"+reg_nme+"' and  receipt_no='"+inv+"'";
								    statement6.executeUpdate(updateclose);
								    JOptionPane.showMessageDialog(frame, "Updated Successfully  „ «· ÕœÌÀ »‰Ã«Õ");
					                //closesale
									
								}
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Total Amount not matching «·„»·€ «·≈Ã„«·Ì €Ì— „ÿ«»ﬁ");
							}
				        }
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnChangePaycode.setBounds(645, 0, 141, 42);
			panel.add(btnChangePaycode);
			
			lblcustname = new JTextField();
			lblcustname.setEditable(false);
			lblcustname.setBounds(80, 120, 679, 22);
			panel.add(lblcustname);
			lblcustname.setColumns(10);
			
			lblCust = new JTextField();
			lblCust.setEditable(false);
			lblCust.setBounds(80, 90, 275, 22);
			panel.add(lblCust);
			lblCust.setColumns(10);
			
			Image search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
			JButton btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(search));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new sales_master.Cust_Chooser(company,"HistoryPaycode", username, store_name,"","", "","");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnNewButton.setBounds(367, 86, 45, 23);
			panel.add(btnNewButton);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(243, 229, 216));
			panel_1.setBounds(550, 594, 224, 109);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			lblCash = new JLabel("CASH");
			if(language.equals("Arabic"))
			{
				lblCash.setText("‰ﬁœÌ");
			}
			lblCash.setBounds(31, 18, 56, 16);
			panel_1.add(lblCash);
			
			lblCard = new JLabel("CARD");
			if(language.equals("Arabic"))
			{
				lblCard.setText("»ÿ«ﬁ…");
			}
			lblCard.setBounds(31, 49, 56, 16);
			panel_1.add(lblCard);
			
			lblAr = new JLabel("A/R");
			if(language.equals("Arabic"))
			{
				lblAr.setText("√/—");
			}
			lblAr.setBounds(31, 80, 56, 16);
			panel_1.add(lblAr);
			
			textField = new JTextField();
			textField.setBounds(99, 12, 116, 22);
			panel_1.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(99, 43, 116, 22);
			panel_1.add(textField_1);
			
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(99, 74, 116, 22);
			panel_1.add(textField_2);
			
			lblNewPaymentMode = new JLabel("New Payment Mode");
			if(language.equals("Arabic"))
			{
				lblNewPaymentMode.setText("Ê÷⁄ «·œ›⁄ «·ÃœÌœ");
			}
			lblNewPaymentMode.setBounds(612, 572, 147, 16);
			panel.add(lblNewPaymentMode);
			
			btnUpdateTable = new JButton("Update Table");
			if(language.equals("Arabic"))
			{
				btnUpdateTable.setText(" ÕœÌÀ «·ÃœÊ·");
			}
			btnUpdateTable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try
					{
						int count=table.getRowCount();
						double vat_ttl=0.0;
						double total_price=0.0;
						double ttl_qty=0;
						double ttl_amt=0;
						double ttl_bf_disc=0;
						double ttl_af_disc=0;
						for(int i=0;i<count;i++)
						{
							String vat_type=table.getModel().getValueAt(i, 8).toString();
							double unitprice=Double.parseDouble(table.getValueAt(i, 5).toString());
							double qty_line=Double.parseDouble(table.getValueAt(i, 4).toString());
							ttl_qty+=Double.parseDouble(table.getModel().getValueAt(i, 4).toString());
							String total_val=table.getModel().getValueAt(i, 6).toString();
							ttl_amt+=Double.parseDouble(total_val);
							String ttl_vat=table.getModel().getValueAt(i, 7).toString();
							vat_ttl+=Double.parseDouble(ttl_vat);
							total_price+= Double.parseDouble(total_val)+Double.parseDouble(ttl_vat);
							double totl=Double.parseDouble(table.getValueAt(i, 6).toString());
							double vt=Double.parseDouble(table.getValueAt(i, 7).toString());
							
							if(vat_type.equals("INC"))
							{
								ttl_af_disc+=totl+vt;
							}
							else
							{
								ttl_af_disc+=totl;
							}
							
							String ttl_before=df2.format(unitprice*qty_line);
							
							ttl_bf_disc+=Double.parseDouble(ttl_before);
							//ttl_bf_disc+=unitprice*qty_line;
						}
						
						//double ttl_disc_perc=0;//((ttl_bf_disc-ttl_af_disc)*100/ttl_bf_disc);
						//double ttl_disc_amt=0;//ttl_bf_disc-ttl_af_disc;
						label_5.setText(Double.toString(ttl_qty));
						
						String before_disc=df2.format(ttl_bf_disc);
						label_6.setText(before_disc);
						
						String after_disc=df2.format(ttl_af_disc);
						label_8.setText(Double.toString(ttl_amt));
						double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
						if(Double.isNaN(ttl_disc_perc))
						{
							ttl_disc_perc=0;
						}
						double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
						label_7.setText(Double.toString(ttl_disc_amt));
						
						label_9.setText(Double.toString(vat_ttl));
						
						String total_price4=df2.format(total_price);
						
						label_10.setText(total_price4);
					}
					catch (Exception e) {
						System.err.println(e);
					}
				}
			});
			btnUpdateTable.setBounds(633, 534, 141, 25);
			panel.add(btnUpdateTable);
			
			get_store_validate_dt(company,store_name,username, reg_nme);
			get_invoice_dt(company,store_name,username, reg_nme,inv);
			get_full_paycode(company, reg_nme,username,store_name,inv);
	}
	private void get_invoice_dt(String company, String store_name,String username, String reg_nme, String receipt) {
		Connection con=Dbsetup.local_server.getConnection();
		
		try {
			
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			Statement  statement = con.createStatement();
			Statement  statement1 = con.createStatement();
		//String query="SELECT mr_pro.mr_tmp_item_master.department,mr_pro.mr_tmp_item_master.item_code,mr_pro.mr_tmp_item_master.item_name,sum(mr_pro.mr_pos_settlement_cash_items.qty) as qty,sum(mr_pro.mr_pos_settlement_cash_items.unit_price*mr_pro.mr_pos_settlement_cash_items.qty) as net_sale,sum(mr_pro.mr_pos_settlement_cash_items.total+mr_pro.mr_pos_settlement_cash_items.vat) as sale_amt  FROM mr_pro.mr_tmp_item_master INNER JOIN mr_pro.mr_pos_settlement_cash_items on mr_pro.mr_pos_settlement_cash_items.item_code=mr_pro.mr_tmp_item_master.item_code INNER JOIN mr_pro.mr_pos_settlement_cash on mr_pro.mr_pos_settlement_cash.reg_num=mr_pro.mr_pos_settlement_cash_items.ref_reg_num and mr_pro.mr_pos_settlement_cash.receipt_no=mr_pro.mr_pos_settlement_cash_items.ref_receipt_num where mr_pro.mr_pos_settlement_cash_items.ref_reg_num='"+reg_nme+"' and (mr_pos_settlement_cash.type='PAYCODE' or mr_pos_settlement_cash.type='CASH') GROUP BY mr_pro.mr_tmp_item_master.department";
		String query="SELECT * from "+company+".mr_pos_settlement_history where receipt_no='"+receipt+"' and reg_num='"+reg_nme+"' ";
		ResultSet resultSet0 = statement.executeQuery(query);
		if(resultSet0.next())
		{
			reg.setText(resultSet0.getString("reg_num"));
			lblinv.setText(resultSet0.getString("receipt_no"));
			String created_date=resultSet0.getString("created_date");
			String time=resultSet0.getString("time");
			String inv_date=created_date+" "+time;
			lblDatetime.setText(inv_date);
			lblcashier.setText(resultSet0.getString("user"));
			lblcustname.setText(resultSet0.getString("cust_name"));
			lblCust.setText(resultSet0.getString("cust_code"));
			String discount=resultSet0.getString("disc");
			
			String total=resultSet0.getString("total");
			//double sub_ttl= Double.parseDouble(total)+Double.parseDouble(vat);
			double sub_ttl= Double.parseDouble(resultSet0.getString("grand_total"));
			double sub_disc= Double.parseDouble(total)+Double.parseDouble(discount);
			
			label_5.setText(resultSet0.getString("qty"));
			label_6.setText(Double.toString(sub_disc));
			label_7.setText(resultSet0.getString("disc"));
			label_8.setText(resultSet0.getString("total"));
			label_9.setText(resultSet0.getString("vat"));
			label_10.setText(Double.toString(sub_ttl));
			
			if(!resultSet0.getString("type").equals("PAYCODE"))
			{
				if(resultSet0.getString("type").equals("A/R"))
				{
					lblpayment.setText(resultSet0.getString("type")+" : "+resultSet0.getString("grand_total"));
					changedue.setText("Change Due : 0");
				}
				else
				{
					lblpayment.setText(resultSet0.getString("type")+" : "+resultSet0.getString("cash_paid"));
					double change=Double.parseDouble(resultSet0.getString("grand_total"))-Double.parseDouble(resultSet0.getString("cash_paid"));
					changedue.setText("Change Due : "+Double.toString(change));
				}
				
			}
			else
			{
				String paytext="";
				String query1="SELECT * from "+company+".mr_paycode_settlement_history where ref_receipt_num='"+receipt+"' and ref_reg_num='"+reg_nme+"' ";
				ResultSet resultSet01 = statement1.executeQuery(query1);
				while(resultSet01.next())
				{
					paytext+=resultSet01.getString("paycode_name")+" : "+resultSet01.getString("paycode_amt")+"             ";
					double change=Double.parseDouble(resultSet0.getString("grand_total"))-Double.parseDouble(resultSet0.getString("cash_paid"));
					changedue.setText("Change Due : "+Double.toString(change));
				}
				lblpayment.setText(paytext);
			}
			
		}
		con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private void get_full_paycode(String company,String reg_nme,String username,String store_name, String receipt)
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
		String query="SELECT * from "+company+".mr_pos_settlement_history_items where ref_receipt_num='"+receipt+"' and ref_reg_num='"+reg_nme+"' ";
		ResultSet resultSet0 = statement.executeQuery(query);
		while(resultSet0.next())
		{
			String seq_num=resultSet0.getString("barcode");
			String itemcode=resultSet0.getString("item_code");
			String item=resultSet0.getString("item_desc");
			String qty=resultSet0.getString("qty");
			String unit_price=resultSet0.getString("unit_price");
			String amt=resultSet0.getString("total");
			String vat=resultSet0.getString("vat");
			String type=resultSet0.getString("vat_type");
			String id=resultSet0.getString("id");
			model.addRow(new Object[]{model.getRowCount()+1,seq_num,itemcode,item,qty,unit_price,amt,vat,type,id});
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
