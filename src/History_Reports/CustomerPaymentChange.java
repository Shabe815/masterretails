package History_Reports;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.json.JSONException;

import javax.swing.JComboBox;

public class CustomerPaymentChange {

	private JFrame frame;
	private DefaultTableModel model;
	public static JTextField textField;
	public static JTextField textField_1;
	private JTextField textField_2;
	private JComboBox<String> comboBox;
	private JLabel lblVoucher;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerPaymentChange window = new CustomerPaymentChange("mr_pro","S01","SHABEER", "1","2","");
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
	public CustomerPaymentChange(String company, String store_name, String username,String decimal, String voucher, String language) {
		initialize(company,store_name,username, decimal,voucher,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store_name 
	 * @param host_name 
	 * @param company 
	 * @param language 
	 */
	private void initialize(final String company,final String store_name,final String username,final String decimal,final String voucher, String language) {
		frame = new JFrame("");
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 792, 407);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 786, 372);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
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
		
		if(language.equals("Arabic"))
		{
			 model.addColumn("«·—ﬁ„ «· ”·”·Ì:"); 
			    model.addColumn("—ﬁ„ «·ﬁ”Ì„…:"); 
			    model.addColumn("—„“ «·⁄„Ì·"); 
				model.addColumn("«”„ «·⁄„Ì·"); 
				model.addColumn("«·„»·€ «·„œ›Ê⁄"); 
				model.addColumn("—„“ «·œ›⁄");
		}
		else
		{
			 model.addColumn("Sl No:"); 
			    model.addColumn("Voucher No:"); 
			    model.addColumn("Cust Code"); 
				model.addColumn("Cust Name"); 
				model.addColumn("Paid Amt"); 
				model.addColumn("Paycode");
		}
		   
			
			
			
			JLabel lblCloseSales = new JLabel("Customer Payment");
			if(language.equals("Arabic"))
			{
				lblCloseSales.setText("œ›⁄ «·⁄„·«¡");
			}
			lblCloseSales.setFont(new Font("Monospaced", Font.BOLD, 25));
			lblCloseSales.setBounds(12, 21, 373, 53);
			panel.add(lblCloseSales);
			
			JLabel lblCustomerCode = new JLabel("Customer Code");
			if(language.equals("Arabic"))
			{
				lblCustomerCode.setText("—„“ «·⁄„Ì·");
			}
			lblCustomerCode.setBounds(12, 138, 104, 22);
			panel.add(lblCustomerCode);
			
			JLabel lblCustomerName = new JLabel("Customer Name");
			if(language.equals("Arabic"))
			{
				lblCustomerName.setText("«”„ «·⁄„Ì·");
			}
			lblCustomerName.setBounds(12, 181, 104, 22);
			panel.add(lblCustomerName);
			
			JLabel lblPaymentMethod = new JLabel("Payment Method");
			if(language.equals("Arabic"))
			{
				lblPaymentMethod.setText("ÿ—Ìﬁ… «·œ›⁄");
			}
			lblPaymentMethod.setBounds(12, 224, 104, 22);
			panel.add(lblPaymentMethod);
			
			JLabel lblAmount = new JLabel("Amount");
			if(language.equals("Arabic"))
			{
				lblAmount.setText("ﬂ„Ì…");
			}
			lblAmount.setBounds(12, 267, 104, 22);
			panel.add(lblAmount);
			
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(152, 138, 458, 22);
			panel.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setBounds(152, 181, 605, 22);
			panel.add(textField_1);
			textField_1.setColumns(10);
			Image search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
			JButton button = new JButton("");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new sales_master.Cust_Chooser(company,"CustomerPaymentHistory", username, store_name,"","", "","");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			button.setIcon(new ImageIcon(search));
			button.setBounds(627, 137, 45, 23);
			panel.add(button);
			
			textField_2 = new JTextField();
			textField_2.setBounds(152, 267, 178, 22);
			panel.add(textField_2);
			textField_2.setColumns(10);
			
			comboBox = new JComboBox<String>();
			comboBox.setBounds(152, 224, 178, 22);
			panel.add(comboBox);
			
			JLabel lblNewLabel = new JLabel("Voucher Number");
			if(language.equals("Arabic"))
			{
				lblNewLabel.setText("—ﬁ„ «·ﬁ”Ì„…");
			}
			lblNewLabel.setBounds(12, 95, 104, 22);
			panel.add(lblNewLabel);
			
			lblVoucher = new JLabel("Voucher");
			if(language.equals("Arabic"))
			{
				lblVoucher.setText("ﬁ”Ì„…");
			}
			lblVoucher.setBounds(152, 95, 458, 22);
			panel.add(lblVoucher);
			
			JButton btnUpdatePayment = new JButton("Update Payment");
			if(language.equals("Arabic"))
			{
				btnUpdatePayment.setText(" ÕœÌÀ «·œ›⁄");
			}
			btnUpdatePayment.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try
					{
						int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Change Paycode Â·  —Ìœ  €ÌÌ— —„“ «·œ›⁄ø","Warning",JOptionPane.YES_NO_OPTION);
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
							
							String query="SELECT * from "+company+".mr_customer_payment inner join "+company+".mr_customer_master on mr_customer_payment.cust_code=mr_customer_master.cust_code where mr_customer_payment.seq_num='"+voucher+"'";
							ResultSet resultSet = statement.executeQuery(query);
							if(resultSet.next())
							{
								String custcode=resultSet.getString("cust_code");
								String amount=resultSet.getString("amount");
								String paycode=resultSet.getString("paycode");
								
								String default_ar_acc="";
								ResultSet resultSet1 = statement1.executeQuery("select * from "+company+".mr_customer_master where cust_code='"+textField.getText()+"' ");
								if(resultSet1.next())
								{
									default_ar_acc=resultSet1.getString("default_ar_acc");
								}
								String default_oldar_acc="";
								ResultSet resultSet2 = statement2.executeQuery("select * from "+company+".mr_customer_master where cust_code='"+custcode+"' ");
								if(resultSet2.next())
								{
									default_oldar_acc=resultSet2.getString("default_ar_acc");
								}
								String oldarpayable=default_oldar_acc;
								String ar_payable=default_ar_acc;
								
								String cash_acc="";
				                String query15="SELECT * FROM "+company+".mr_store_accounts where  ref_store_id='"+store_name+"' "; 
								ResultSet resultSet3 = statement3.executeQuery(query15);
				                if(resultSet3.next())
				                {
				                	//ar_payable=default_ar_acc;
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
				                
				                
							    
							    if(textField.getText().equals(custcode))
							    {
							    	if(comboBox.getSelectedItem().toString().equals(paycode))
								    {
							    		if(textField_2.getText().equals(amount))
									    {
							    			
										    
									    }
								    	else
								    	{
								    		String query1="update "+company+".mr_customer_master set  `avail_credit`=avail_credit-'"+amount+"' where cust_code='"+custcode+"'  ";
											statement6.executeUpdate(query1);
											
								    		String updatejv="update "+company+".mr_jv_details set cr_amt='"+textField_2.getText().trim()+"' where note='"+voucher+"' and ac_code='"+default_ar_acc+"' ";
										    statement6.executeUpdate(updatejv);
										    if(paycode.equals("BANK"))
										    {
										    	String updatejv1="update "+company+".mr_jv_details set dr_amt='"+textField_2.getText().trim()+"' where note='"+voucher+"' and ac_code='"+bank_acc+"' ";
											    statement6.executeUpdate(updatejv1);
										    }
										    else
										    {
										    	String updatejv1="update "+company+".mr_jv_details set dr_amt='"+textField_2.getText().trim()+"' where note='"+voucher+"'  and ac_code='"+cash_acc+"' ";
											    statement6.executeUpdate(updatejv1);
										    }
										    String updatecust="update "+company+".mr_customer_payment set  amount='"+textField_2.getText().trim()+"' where seq_num='"+voucher+"' ";
										    statement6.executeUpdate(updatecust);
										    
										    String query11="update "+company+".mr_customer_master set  `avail_credit`=avail_credit+'"+textField_2.getText().trim()+"' where cust_code='"+textField.getText()+"'  ";
											statement6.executeUpdate(query11);
								    	}
								    }
							    	else
							    	{
							    		if(textField_2.getText().equals(amount))
									    {
							    			
										    
									    }
								    	else
								    	{
								    		String query1="update "+company+".mr_customer_master set  `avail_credit`=avail_credit-'"+amount+"' where cust_code='"+custcode+"'  ";
											statement6.executeUpdate(query1);
											
								    		String updatejv="update "+company+".mr_jv_details set cr_amt='"+textField_2.getText().trim()+"' where note='"+voucher+"' and ac_code='"+default_ar_acc+"' ";
										    statement6.executeUpdate(updatejv);
										    if(paycode.equals("BANK"))
										    {
										    	String updatejv1="update "+company+".mr_jv_details set dr_amt='"+textField_2.getText().trim()+"' where note='"+voucher+"' and ac_code='"+bank_acc+"' ";
											    statement6.executeUpdate(updatejv1);
										    }
										    else
										    {
										    	String updatejv1="update "+company+".mr_jv_details set dr_amt='"+textField_2.getText().trim()+"' where note='"+voucher+"'  and ac_code='"+cash_acc+"' ";
											    statement6.executeUpdate(updatejv1);
										    }
										    String updatecust="update "+company+".mr_customer_payment set  amount='"+textField_2.getText().trim()+"' where seq_num='"+voucher+"' ";
										    statement6.executeUpdate(updatecust);
										    
										    String query11="update "+company+".mr_customer_master set  `avail_credit`=avail_credit+'"+textField_2.getText().trim()+"' where cust_code='"+textField.getText()+"'  ";
											statement6.executeUpdate(query11);
								    	}
							    		if(comboBox.getSelectedItem().toString().equals("BANK"))
									    {
							    			String updatejv="update "+company+".mr_jv_details set ac_code='"+bank_acc+"',ac_desc='"+bankname+"' where note='"+voucher+"' and ac_code='"+cash_acc+"' ";
										    statement6.executeUpdate(updatejv);
										    String updatecust="update "+company+".mr_customer_payment set paycode='"+comboBox.getSelectedItem().toString()+"' where seq_num='"+voucher+"' ";
										    statement6.executeUpdate(updatecust);
									    }
									    else
									    {
									    	String updatejv="update "+company+".mr_jv_details set ac_code='"+cash_acc+"',ac_desc='"+cash_name+"' where note='"+voucher+"' and ac_code='"+bank_acc+"' ";
										    statement6.executeUpdate(updatejv);
										    String updatecust="update "+company+".mr_customer_payment set paycode='"+comboBox.getSelectedItem().toString()+"' where seq_num='"+voucher+"' ";
										    statement6.executeUpdate(updatecust);
									    }
							    	}
							    }
							    else
							    {
							    	if(comboBox.getSelectedItem().toString().equals(paycode))
								    {
							    		if(textField_2.getText().equals(amount))
									    {
							    			
										    
									    }
								    	else
								    	{
								    		String query1="update "+company+".mr_customer_master set  `avail_credit`=avail_credit-'"+amount+"' where cust_code='"+custcode+"'  ";
											statement6.executeUpdate(query1);
											
								    		String updatejv="update "+company+".mr_jv_details set cr_amt='"+textField_2.getText().trim()+"' where note='"+voucher+"' and ac_code='"+default_ar_acc+"' ";
										    statement6.executeUpdate(updatejv);
										    if(paycode.equals("BANK"))
										    {
										    	String updatejv1="update "+company+".mr_jv_details set dr_amt='"+textField_2.getText().trim()+"' where note='"+voucher+"' and ac_code='"+bank_acc+"' ";
											    statement6.executeUpdate(updatejv1);
										    }
										    else
										    {
										    	String updatejv1="update "+company+".mr_jv_details set dr_amt='"+textField_2.getText().trim()+"' where note='"+voucher+"'  and ac_code='"+cash_acc+"' ";
											    statement6.executeUpdate(updatejv1);
										    }
										    String updatecust="update "+company+".mr_customer_payment set  amount='"+textField_2.getText().trim()+"' where seq_num='"+voucher+"' ";
										    statement6.executeUpdate(updatecust);
										    
										    String query11="update "+company+".mr_customer_master set  `avail_credit`=avail_credit+'"+textField_2.getText().trim()+"' where cust_code='"+textField.getText()+"'  ";
											statement6.executeUpdate(query11);
								    	}
								    }
							    	else
							    	{
							    		if(textField_2.getText().equals(amount))
									    {
							    			
										    
									    }
								    	else
								    	{
								    		String query1="update "+company+".mr_customer_master set  `avail_credit`=avail_credit-'"+amount+"' where cust_code='"+custcode+"'  ";
											statement6.executeUpdate(query1);
											
								    		String updatejv="update "+company+".mr_jv_details set cr_amt='"+textField_2.getText().trim()+"' where note='"+voucher+"' and ac_code='"+default_ar_acc+"' ";
										    statement6.executeUpdate(updatejv);
										    if(paycode.equals("BANK"))
										    {
										    	String updatejv1="update "+company+".mr_jv_details set dr_amt='"+textField_2.getText().trim()+"' where note='"+voucher+"' and ac_code='"+bank_acc+"' ";
											    statement6.executeUpdate(updatejv1);
										    }
										    else
										    {
										    	String updatejv1="update "+company+".mr_jv_details set dr_amt='"+textField_2.getText().trim()+"' where note='"+voucher+"'  and ac_code='"+cash_acc+"' ";
											    statement6.executeUpdate(updatejv1);
										    }
										    String updatecust="update "+company+".mr_customer_payment set  amount='"+textField_2.getText().trim()+"' where seq_num='"+voucher+"' ";
										    statement6.executeUpdate(updatecust);
										    
										    String query11="update "+company+".mr_customer_master set  `avail_credit`=avail_credit+'"+textField_2.getText().trim()+"' where cust_code='"+textField.getText()+"'  ";
											statement6.executeUpdate(query11);
								    	}
							    		if(comboBox.getSelectedItem().toString().equals("BANK"))
									    {
							    			String updatejv="update "+company+".mr_jv_details set ac_code='"+bank_acc+"',ac_desc='"+bankname+"' where note='"+voucher+"' and ac_code='"+cash_acc+"' ";
										    statement6.executeUpdate(updatejv);
										    String updatecust="update "+company+".mr_customer_payment set paycode='"+comboBox.getSelectedItem().toString()+"' where seq_num='"+voucher+"' ";
										    statement6.executeUpdate(updatecust);
									    }
									    else
									    {
									    	String updatejv="update "+company+".mr_jv_details set ac_code='"+cash_acc+"',ac_desc='"+cash_name+"' where note='"+voucher+"' and ac_code='"+bank_acc+"' ";
										    statement6.executeUpdate(updatejv);
										    String updatecust="update "+company+".mr_customer_payment set paycode='"+comboBox.getSelectedItem().toString()+"' where seq_num='"+voucher+"' ";
										    statement6.executeUpdate(updatecust);
									    }
							    	}
							    	String query1="update "+company+".mr_customer_master set  `avail_credit`=avail_credit-'"+amount+"' where cust_code='"+custcode+"'  ";
									statement6.executeUpdate(query1);
							    	String updatejv="update "+company+".mr_jv_details set ac_code='"+ar_payable+"',ac_desc='"+ar_name+"' where note='"+voucher+"' and ac_code='"+oldarpayable+"' ";
								    statement6.executeUpdate(updatejv);
								    String updatecust="update "+company+".mr_customer_payment set cust_code='"+textField.getText()+"' where seq_num='"+voucher+"' ";
								    statement6.executeUpdate(updatecust);
									String query11="update "+company+".mr_customer_master set  `avail_credit`=avail_credit+'"+amount+"' where cust_code='"+textField.getText()+"'  ";
									statement6.executeUpdate(query11);
							    }
							}
							JOptionPane.showMessageDialog(frame, "Updated Successfully  „ «· ÕœÌÀ »‰Ã«Õ");
				        }
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnUpdatePayment.setBounds(12, 310, 157, 35);
			panel.add(btnUpdatePayment);
			comboBox.addItem("BANK");
			comboBox.addItem("CASH");
			
			get_full_paycode(company, username,store_name,voucher);
	}
	private void get_full_paycode(String company,String username,String store_name, String voucher)
	{
		Connection con=Dbsetup.local_server.getConnection();
		Statement statement;
		try {
			
		statement = con.createStatement();
		//String query="SELECT mr_pro.mr_tmp_item_master.department,mr_pro.mr_tmp_item_master.item_code,mr_pro.mr_tmp_item_master.item_name,sum(mr_pro.mr_pos_settlement_cash_items.qty) as qty,sum(mr_pro.mr_pos_settlement_cash_items.unit_price*mr_pro.mr_pos_settlement_cash_items.qty) as net_sale,sum(mr_pro.mr_pos_settlement_cash_items.total+mr_pro.mr_pos_settlement_cash_items.vat) as sale_amt  FROM mr_pro.mr_tmp_item_master INNER JOIN mr_pro.mr_pos_settlement_cash_items on mr_pro.mr_pos_settlement_cash_items.item_code=mr_pro.mr_tmp_item_master.item_code INNER JOIN mr_pro.mr_pos_settlement_cash on mr_pro.mr_pos_settlement_cash.reg_num=mr_pro.mr_pos_settlement_cash_items.ref_reg_num and mr_pro.mr_pos_settlement_cash.receipt_no=mr_pro.mr_pos_settlement_cash_items.ref_receipt_num where mr_pro.mr_pos_settlement_cash_items.ref_reg_num='"+reg_nme+"' and (mr_pos_settlement_cash.type='PAYCODE' or mr_pos_settlement_cash.type='CASH') GROUP BY mr_pro.mr_tmp_item_master.department";
		String query="SELECT * from "+company+".mr_customer_payment inner join "+company+".mr_customer_master on mr_customer_payment.cust_code=mr_customer_master.cust_code where  seq_num='"+voucher+"' ";
		ResultSet resultSet0 = statement.executeQuery(query);
		if(resultSet0.next())
		{
			String seq_num=resultSet0.getString("seq_num");
			lblVoucher.setText(seq_num);
			String code=resultSet0.getString("cust_code");
			textField.setText(code);
			String name=resultSet0.getString("cust_name");
			textField_1.setText(name);
			String amt=resultSet0.getString("amount");
			textField_2.setText(amt);
			String paycode=resultSet0.getString("paycode");
			comboBox.setSelectedItem(paycode);
			
			
			
		}
		con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	
	
}
