package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextArea;

import java.awt.ScrollPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.SwingConstants;

import sales_distribution.sd_post_list;
import sales_distribution.sd_view;

import java.awt.event.KeyAdapter;

public class cust_payment_voucher {

	
	private JFrame frame;
	private DefaultTableModel model;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_8;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxAcPayee;
	private JDateChooser dateChooser;
	private JTextArea textArea;
	private int x=0;
	private int y=0;
	private JTextField textField_7;
	private boolean dot=false;
	private JTextField textField_10;
	private JComboBox<String> comboBox_2;
	private double ad_amt=0;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_9;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cust_payment_voucher window = new cust_payment_voucher( "mr_pro","admin", "S01",null,120,"v001","DIJI","1.0","2","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param object2 
	 * @param object 
	 * @param company 
	 * @param host_name 
	 * @param store 
	 * @param username 
	 * @param vendor_name 
	 * @param vendor_name 
	 * @param type 
	 */
	public cust_payment_voucher( String company, String username,  String store,Object[] item_list, double balance, String vendor_id, String vendor_name,String version,String decimal, String pagetype) {
		initialize(company,username,store,item_list,balance,vendor_id,vendor_name,version,decimal,pagetype);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param balance 
	 * @param item_list 
	 * @param company 
	 * @param host_name 
	 * @param store 
	 * @param username 
	 * @param vendor_name 
	 * @param version 
	 * @param decimal 
	 * @param vendor_name2 
	 */
	private void initialize( final String company,final String username,final String store,final Object[] item_list, double balance,final String vendor_id, final String vendor_name, String version, final String decimal,final String pagetype) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(pagetype.equals("sd"))
				{
					sales_distribution.sd_list.get_po_disp(company, "OPEN", "",sales_distribution.sd_list.comboBox.getSelectedItem().toString());	
				}
				else
				{
					cust_account_payable.get_po_term_disp(company);	
				}
			}
			@Override
		      public void windowIconified(WindowEvent we) {
		        frame.setState(JFrame.NORMAL);
		      }
		});
		
		
		Image Billing=new ImageIcon(this.getClass().getResource("/Billing-icon.png")).getImage();
		
		JLabel lblCompanyName = new JLabel("Balance Amount:");
		lblCompanyName.setBounds(10, 67, 95, 21);
		lblCompanyName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblCompanyName);
		
		
		JLabel lblCompanySetup = new JLabel("Payment Voucher");
		lblCompanySetup.setBounds(21, 11, 228, 44);
		lblCompanySetup.setIcon(new ImageIcon(Billing));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Save");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_1.getText();
				String inv=textField_2.getText();
				String start_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if(amt.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter The Amount");
				}
				else if(start_date.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please choose a Date");
				}
				else
				{
					boolean status=false;
					boolean type=false;
					if(!comboBox.getSelectedItem().toString().equals("CASH"))
					{
						if(comboBox_2.getSelectedItem().toString().equals(""))
						{
							JOptionPane.showMessageDialog(frame, "please select Bank Name");
						}
						else if(textField_4.getText().equals(""))
						{
							JOptionPane.showMessageDialog(frame, "please enter Cheque No.");
							textField_4.requestFocus();
						}
						else
						{
							type=true;
						}
					}
					else
					{
						type=true;
					}
					if(type==true)
					{
						
						if(Double.parseDouble(amt)>Double.parseDouble(textField.getText()))
						{
							JOptionPane.showMessageDialog(frame, "Enter Amount Greater Than Balance Amount");
							textField_1.requestFocus();						}
						else if(Double.parseDouble(textField_10.getText())>Double.parseDouble(textField.getText()))
						{
							JOptionPane.showMessageDialog(frame, "Net Amount Greater Than Balance Amount");
						}
						else
						{
							if(x>1)
							{
								if(Double.parseDouble(textField_10.getText())==Double.parseDouble(textField.getText()))
								{
									status=true;
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Net Amount Not Equal Balance Amount");
								}
							}
							else
							{
								status=true;
							}
							if(status==true)
							{
								String paycode="";
								String bank_name="";
								String cheque="";
								String cust_bank="";
								String pdc="0";
								String ac_payee="0";
								String remark="";
								String pay_to="";
								String pay_note="";
								String po_num="";
								String disc="";
								String roundoff="";
								String payment_mode=comboBox.getSelectedItem().toString();
								String tranname="";
								if(payment_mode.equals("CASH"))
								{
									paycode=comboBox_1.getSelectedItem().toString();
									tranname="AR";
								}
								else
								{
									tranname="AR";
									bank_name=comboBox_2.getSelectedItem().toString();
									cust_bank=textField_3.getText();
									cheque=textField_4.getText();
									if(chckbxAcPayee.isSelected())
									{
										pdc="1";
									}
									if(chckbxNewCheckBox.isSelected())
									{
										ac_payee="1";
									}
								}
								disc=textField_7.getText();
								if(disc.equals(""))
								{
									disc="0";
								}
								roundoff=textField_9.getText();
								if(roundoff.equals(""))
								{
									roundoff="0";
								}
								
								remark=textField_5.getText();
								pay_to=textField_6.getText();
								pay_note=textArea.getText();
								//po_num=textField_8.getText();
								LocalDateTime myDateObj = LocalDateTime.now();  
							    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
							    String formattedDate = myDateObj.format(myFormatObj);
							    Connection con=Dbsetup.dbconn.getConnection();
						        String seq_num = null;
						        Statement statement = null;
						        Statement statement1 = null;
						        try {
						        	statement = con.createStatement();
						        	String check_qty="select * from "+company+".mr_cheque_details where cheque_no='"+cheque+"' and bank_name='"+bank_name+"' ";
									ResultSet resultSet25 = statement.executeQuery(check_qty);
									if (!resultSet25.next())
								    {
										statement = con.createStatement();
										statement1 = con.createStatement();
										String trans_seq = "select tran_seq from "+company+".mr_transaction_seq where tran_orgin='"+tranname+"' ";
										ResultSet resultSet2 = statement.executeQuery(trans_seq);
									    if (resultSet2.next())
									    {
									    	seq_num=resultSet2.getString("tran_seq");
									    	String[] seq=seq_num.split("-");
									    	DateFormat df = new SimpleDateFormat("yy");
									    	String formatdDate = df.format(Calendar.getInstance().getTime());
									    	if(!seq[1].equals(formattedDate))
									    	{
									    		seq[1]=formatdDate;
									    	}
									    	seq_num=seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+seq[3];
									    	String sqnum=Integer.toString(Integer.parseInt(seq[3])+1);
									    	StringBuilder sb = new StringBuilder();
											for (int toPrepend=6-sqnum.length(); toPrepend>0; toPrepend--) {
											    sb.append('0');
											}
											sb.append(sqnum);
									    	String save_po="update "+company+".mr_transaction_seq set modified_date='"+formattedDate+"',tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_orgin='"+tranname+"' ";
											statement.executeUpdate(save_po);
									    }
										double new_amt_lt=Double.parseDouble(amt);
										BigDecimal ba = new BigDecimal(new_amt_lt);
				                		ba = ba.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
				                		new_amt_lt = ba.doubleValue();
				                		String select_vendor="select * from "+company+".mr_customer_master where cust_code='"+vendor_id+"' ";
										ResultSet resultSet1 = statement.executeQuery(select_vendor);
										double new_paid=0;
										if(resultSet1.next())
										{
											String avail_credit=resultSet1.getString("avail_credit");
											new_paid=Double.parseDouble(avail_credit)+(Double.parseDouble(amt)+Double.parseDouble(disc)+Double.parseDouble(roundoff));
											BigDecimal bp = new BigDecimal(new_paid);
					                		bp = bp.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
					                		new_paid = bp.doubleValue();
					                		
										}
										double last_paid_amt=Double.parseDouble(amt)+Double.parseDouble(disc)+Double.parseDouble(roundoff);
										String updates="update "+company+".mr_customer_master set avail_credit='"+new_paid+"',last_payment_date='"+formattedDate+"',last_paid_amt='"+last_paid_amt+"' where cust_code = '"+vendor_id+"' ";
										statement.executeUpdate(updates);
										
										
										for(int i=0;i<item_list.length;i++)
										{
									    	if(item_list[i]!=null)
											{
									    		po_num=item_list[i].toString();
									    		try{
													if(x>1)
													{
														
														String disc_applied=Float.toString(Float.parseFloat(disc)/x);
														
														String query="SELECT * FROM "+company+".mr_sd_customer_payment where ref_tran_num='"+po_num+"' and ref_cust_id='"+vendor_id+"' "; 
														ResultSet resultSet = statement1.executeQuery(query);
														if(resultSet.next())
														{
															String paid=resultSet.getString("paid_amt");
															String bal=resultSet.getString("bal_amt");
															double paidamt=(Double.parseDouble(bal)-Double.parseDouble(disc_applied));
															//String inv=resultSet.getString("invo_num");
															double new_paid_bal=Double.parseDouble(paid)+Double.parseDouble(bal);
															double new_bal=Double.parseDouble(bal)-Double.parseDouble(bal);
															BigDecimal bj = new BigDecimal(new_paid_bal);
									                		bj = bj.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
									                		new_paid_bal = bj.doubleValue();
									                		BigDecimal bd = new BigDecimal(new_bal);
									                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
									                		new_bal = bd.doubleValue();
															String update="update "+company+".mr_sd_customer_payment set doc_num='"+inv+"',paid_amt='"+new_paid_bal+"',bal_amt='"+new_bal+"' where ref_tran_num='"+po_num+"' and ref_cust_id='"+vendor_id+"' ";
															statement.executeUpdate(update);
															
															
															String save_file="insert into "+company+".mr_sd_customer_payment_details(`ref_tran_num`,seq_num,`ref_cust_id`, `dr_amt`,cr_amt, `payment_method`, `paycode`, `bank_name`, `cheque_no`, `pdc_check`, `ac_payee`, `remark`, `pay_by`, `note`, `store_id`, `username`, `created_date`, `modified_date`,disc_amt,issue_date,status,cust_bank,roundoff) VALUES('"+po_num+"','"+seq_num+"','"+vendor_id+"','"+paidamt+"','0','"+payment_mode+"','"+paycode+"','"+bank_name+"','"+cheque+"','"+pdc+"','"+ac_payee+"','"+remark+"','"+pay_to+"','"+pay_note+"','"+store+"','"+username+"','"+formattedDate+"','"+formattedDate+"','"+disc_applied+"','"+start_date+"','2','"+cust_bank+"','"+roundoff+"') ";
															statement.executeUpdate(save_file);
															
															
														}
													}
													else
													{
														String save_file="insert into "+company+".mr_sd_customer_payment_details(`ref_tran_num`,seq_num, `ref_cust_id`, `dr_amt`,cr_amt, `payment_method`, `paycode`, `bank_name`, `cheque_no`, `pdc_check`, `ac_payee`, `remark`, `pay_by`, `note`, `store_id`, `username`, `created_date`, `modified_date`,disc_amt,issue_date,status,cust_bank,roundoff) VALUES('"+po_num+"','"+seq_num+"','"+vendor_id+"','"+amt+"','0','"+payment_mode+"','"+paycode+"','"+bank_name+"','"+cheque+"','"+pdc+"','"+ac_payee+"','"+remark+"','"+pay_to+"','"+pay_note+"','"+store+"','"+username+"','"+formattedDate+"','"+formattedDate+"','"+disc+"','"+start_date+"','2','"+cust_bank+"','"+roundoff+"') ";
														statement.executeUpdate(save_file);
														
														
														
														String query="SELECT * FROM "+company+".mr_sd_customer_payment where ref_tran_num='"+po_num+"' and ref_cust_id='"+vendor_id+"' "; 
														ResultSet resultSet = statement1.executeQuery(query);
														if(resultSet.next())
														{
															String paid=resultSet.getString("paid_amt");
															String bal=resultSet.getString("bal_amt");
															double new_paid_bal=Double.parseDouble(paid)+Double.parseDouble(amt)+Double.parseDouble(disc)+Double.parseDouble(roundoff)+ad_amt;
															double new_bal=Double.parseDouble(bal)-(Double.parseDouble(amt)+Double.parseDouble(disc)+Double.parseDouble(roundoff)+ad_amt);
															BigDecimal bd = new BigDecimal(new_bal);
									                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
									                		new_bal = bd.doubleValue();
									                		String update="update "+company+".mr_sd_customer_payment set doc_num='"+inv+"',paid_amt='"+new_paid_bal+"',bal_amt='"+new_bal+"' where ref_tran_num='"+po_num+"' and ref_cust_id='"+vendor_id+"' ";
															statement.executeUpdate(update);
															 
															
														}
													}
												}
												catch(Exception e){
													System.err.println(e);
												}
									    		
									    	}
										}
										if(y>0)
										{
											String query="SELECT * FROM "+company+".mr_sd_customer_payment where ref_cust_id='"+vendor_id+"' and status='1' "; 
											ResultSet resultSet;
											resultSet = statement.executeQuery(query);
											while(resultSet.next())
											{
												String updated="update "+company+".mr_sd_customer_payment set doc_num='"+inv+"',paid_amt='"+resultSet.getString("bal_amt")+"',bal_amt='0',status='3' where ref_tran_num = '"+resultSet.getString("ref_tran_num")+"' and ref_cust_id='"+vendor_id+"' ";
												statement1.executeUpdate(updated);
											}
										}
										if(!comboBox.getSelectedItem().toString().equals("CASH"))
										{
											String query21="insert into "+company+".mr_cheque_details( `tran_num`, `pay_by`,`pay_id`, `cr`, `dr`, `issue_date`, `cheque_no`, `cheque_date`,  `printing_user`,  `handover_user`, `posted_user`, `remark`, `note`,bank_name,status,page_type,cheque_handover,cheque_printed) "
													+ "VALUES ('"+seq_num+"','"+vendor_name+"','"+vendor_id+"','"+new_amt_lt+"','0','"+formattedDate+"','"+cheque+"','"+start_date+"','"+username+"','"+username+"','"+username+"','"+textField_5.getText()+"','"+textArea.getText()+"','"+bank_name+"','OPEN','AR','1','1')";
											statement.executeUpdate(query21);
										}
										
										report_master.sd_pay_voucher.report(con,seq_num,vendor_id,company);
										
										JOptionPane.showMessageDialog(frame, "Saved Successfully");	
										frame.setVisible(false);
										if(pagetype.equals("sd"))
										{
											sales_distribution.sd_list.get_po_disp(company, "OPEN", "",sales_distribution.sd_list.comboBox.getSelectedItem().toString());	
										}
										else if(pagetype.equals("sv"))
										{
											sd_view.frame.setVisible(false);
											sd_post_list.get_po_disp( company,"","","","","","ALL");
											//String company, String type, String custname,String itemname, String date1,String date2,String invoice, String mode
										}
										else
										{
											cust_account_payable.get_po_term_disp(company);	
										}
										
										
										
								    }
									else
									{
										JOptionPane.showMessageDialog(frame, "Cheque Number Already Exist");
									}
						        } catch (Exception e1) {
						        	JOptionPane.showMessageDialog(frame, e1.getMessage());
									
								}  
						        
								
							}
						}
					}
					
				}
			}
		});
	    menuItem.setBorder(null);
	    menuItem.setIcon(new ImageIcon(save));
	    menuItem.setBackground(new Color(243, 229, 216));
	    menuItem.setBounds(457, 11, 135, 48);
	    frame.getContentPane().add(menuItem);
	    
	    JMenuItem menuItem_1 = new JMenuItem("Refresh");
	    menuItem_1 = new JMenuItem("Refresh",KeyEvent.VK_F5);
	    menuItem_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		textField_1.setText("");
	    		comboBox_2.setSelectedIndex(-1);
	    		textField_4.setText("");
	    		textField_3.setText("");
	    		textField_5.setText("");
	    		textField_6.setText("");
	    		textArea.setText("");
	    	}
	    });
	    menuItem_1.setBorder(null);
	    menuItem_1.setIcon(new ImageIcon(refresh));
	    menuItem_1.setBackground(new Color(243, 229, 216));
	    menuItem_1.setBounds(377, 11, 80, 48);
	    frame.getContentPane().add(menuItem_1);
	    
	    JMenuItem menuItem_2 = new JMenuItem("Exit");
	    menuItem_2.setAccelerator(KeyStroke.getKeyStroke("F4"));
	    menuItem_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		frame.setVisible(false);
	    		if(pagetype.equals("sd"))
				{
					sales_distribution.sd_list.get_po_disp(company, "OPEN", "",sales_distribution.sd_list.comboBox.getSelectedItem().toString());	
				}
				else
				{
					cust_account_payable.get_po_term_disp(company);	
				}
	    		//accounts_master.Accountpayable.get_po_term_disp(company, vendor_id, decimal);
	    	}
	    });
	    menuItem_2.setBorder(null);
	    menuItem_2.setIcon(new ImageIcon(exit));
	    menuItem_2.setBackground(new Color(243, 229, 216));
	    menuItem_2.setBounds(317, 11, 60, 48);
	    frame.getContentPane().add(menuItem_2);
		
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
		   
		};
		    model.addColumn("Code"); 
		    model.addColumn("Acccount Name"); 
			model.addColumn("Account Group"); 
			model.addColumn("Account Type"); 
			model.addRow(new Object[]{"","","",""});
		    
		    
		    
		    JLabel lblAmount = new JLabel("Paying Amount :");
		    lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    lblAmount.setBounds(110, 67, 95, 21);
		    frame.getContentPane().add(lblAmount);
		    
		    textField_1 = new JTextField();
		    textField_1.addKeyListener(new KeyAdapter() {
		    	@Override
		    	public void keyTyped(KeyEvent e) {
		    	    char vChar = e.getKeyChar();
		    	    String text = textField_1.getText();

		    	    // Reset dot if field empty
		    	    if (text.equals(""))
		    	        dot = false;

		    	    // Allow minus only at first position and only once
		    	    if (vChar == '-') {
		    	        if (text.length() == 0 && !text.contains("-")) {
		    	            return; // allow
		    	        } else {
		    	            e.consume();
		    	            return;
		    	        }
		    	    }

		    	    if (!dot) {
		    	        if (vChar == '.') {
		    	            dot = true;
		    	        } 
		    	        else if (!(Character.isDigit(vChar)
		    	                || vChar == KeyEvent.VK_BACK_SPACE
		    	                || vChar == KeyEvent.VK_DELETE)) {
		    	            e.consume();
		    	        }
		    	    } else {
		    	        if (!(Character.isDigit(vChar)
		    	                || vChar == KeyEvent.VK_BACK_SPACE
		    	                || vChar == KeyEvent.VK_DELETE)) {
		    	            e.consume();
		    	        }
		    	    }
		    	}

		    	@Override
		    	public void keyPressed(KeyEvent e) {
		    		if (e.getKeyCode()==KeyEvent.VK_ENTER){
				    	textField_7.requestFocus();
					}
		    	}
		    });
		    textField_1.addFocusListener(new FocusAdapter() {
		    	@Override
		    	public void focusLost(FocusEvent arg0) {
		    		if(x>1)
		    		{
		    			String enter_amt=textField_1.getText();
		    			if(enter_amt.equals(""))
		    			{
		    				JOptionPane.showMessageDialog(frame, "Please Enter the Amount");
		    				textField_1.requestFocus();
		    			}
		    			else
		    			{
		    				String disc=textField_7.getText();
		    				
		    				if(disc.equals(""))
		    				{
		    					disc="0";
		    				}
		    				String roundoff=textField_9.getText();
		    				if(roundoff.equals(""))
		    				{
		    					roundoff="0";
		    				}
		    				double net_amt=(Double.parseDouble(enter_amt)+Double.parseDouble(disc)+Double.parseDouble(roundoff));
		    				BigDecimal bd = new BigDecimal(net_amt);
	                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
	                		net_amt = bd.doubleValue();
		    				textField_10.setText(Double.toString(net_amt));
		    				if(net_amt>Double.parseDouble(textField.getText()))
							{
								//JOptionPane.showMessageDialog(frame, "Enter Amount Greater Than Balance Amount");
								//textField_1.requestFocus();
							}
		    			}
		    			
		    		}
		    		else
		    		{
		    			String enter_amt=textField_1.getText();
		    			if(enter_amt.equals(""))
		    			{
		    				JOptionPane.showMessageDialog(frame, "Please Enter the Amount");
		    				textField_1.requestFocus();
		    			}
		    			else
		    			{
		    				String bal_amt=textField.getText();
			    			if(Double.parseDouble(bal_amt)<0)
			    			{
			    				textField_1.setEditable(false);
			    			}
			    			else
			    			{
			    				if(Double.parseDouble(enter_amt)>Double.parseDouble(bal_amt))
								{
									//JOptionPane.showMessageDialog(frame, "Enter Amount Greater Than Balance Amount");
									//textField_1.requestFocus();
								}
			    				else
				    			{
				    				String disc=textField_7.getText();
				    				if(disc.equals(""))
				    				{
				    					disc="0";
				    				}
				    				String roundoff=textField_9.getText();
				    				if(roundoff.equals(""))
				    				{
				    					roundoff="0";
				    				}
				    				double net_amt=(Double.parseDouble(enter_amt)+Double.parseDouble(disc)+Double.parseDouble(roundoff));
				    				BigDecimal bd = new BigDecimal(net_amt);
			                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
			                		net_amt = bd.doubleValue();
				    				textField_10.setText(Double.toString(net_amt));
				    			}
			    			}
		    			}
		    			
		    			
		    		}
		    	}
		    });
		    textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		    textField_1.setBackground(Color.WHITE);
		    textField_1.setColumns(10);
		    textField_1.setBounds(110, 92, 90, 37);
		    frame.getContentPane().add(textField_1);
		    textField_1.requestFocus();
		    
		    comboBox_2 = new JComboBox<String>();
		    comboBox_2.setEnabled(false);
		    comboBox_2.setBounds(124, 218, 277, 22);
		    frame.getContentPane().add(comboBox_2);
		    try{
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				String query="SELECT * FROM "+company+".mr_acc_paycode where store_id='"+store+"' and paycode_type='BANK' "; 
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next())
				{
					comboBox_2.addItem(resultSet.getString("paycode_name"));
				}
				con.close();
			}
			catch(Exception e){
				System.err.println(e);
			}
		    
		    
		    JLabel lblPaymentMode = new JLabel("Payment Mode: ");
		    lblPaymentMode.setFont(new Font("Tahoma", Font.BOLD, 11));
		    lblPaymentMode.setBounds(17, 143, 97, 21);
		    frame.getContentPane().add(lblPaymentMode);
		    
		    JLabel lblBankName = new JLabel("Bank Name:");
		    lblBankName.setBounds(18, 219, 73, 21);
		    frame.getContentPane().add(lblBankName);
		    
		    
		    final JLabel lblDate = new JLabel("Date :");
		    lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		    lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		    lblDate.setBounds(292, 141, 97, 22);
		    frame.getContentPane().add(lblDate);
		    
		    JLabel lblCheque = new JLabel("Cheque# :");
		    lblCheque.setBounds(18, 274, 73, 14);
		    frame.getContentPane().add(lblCheque);
		    
		    textField_4 = new JTextField();
		    textField_4.setEnabled(false);
		    textField_4.setColumns(10);
		    textField_4.setBounds(124, 271, 277, 20);
		    frame.getContentPane().add(textField_4);
		    
		    chckbxNewCheckBox = new JCheckBox("PDC");
		    chckbxNewCheckBox.setEnabled(false);
		    chckbxNewCheckBox.setBounds(423, 218, 97, 23);
		    frame.getContentPane().add(chckbxNewCheckBox);
		    
		    chckbxAcPayee = new JCheckBox("A/C PAYEE");
		    chckbxAcPayee.setEnabled(false);
		    chckbxAcPayee.setBounds(423, 270, 97, 23);
		    frame.getContentPane().add(chckbxAcPayee);
		    
		    JLabel lblRemarks = new JLabel("Remarks:");
		    lblRemarks.setBounds(17, 302, 73, 14);
		    frame.getContentPane().add(lblRemarks);
		    
		    textField_5 = new JTextField();
		    textField_5.setColumns(10);
		    textField_5.setBounds(123, 299, 395, 20);
		    frame.getContentPane().add(textField_5);
		    
		    JLabel lblPayTo = new JLabel("Received From: ");
		    lblPayTo.setBounds(18, 332, 94, 14);
		    frame.getContentPane().add(lblPayTo);
		    
		    textField_6 = new JTextField(vendor_name);
		    textField_6.setColumns(10);
		    textField_6.setBounds(124, 329, 395, 20);
		    frame.getContentPane().add(textField_6);
		    
		    JLabel lblNote = new JLabel("Note:");
		    lblNote.setBounds(17, 362, 73, 14);
		    frame.getContentPane().add(lblNote);
		    
		    
		    
		    JLabel lblPaycode = new JLabel("Paycode:");
		    lblPaycode.setFont(new Font("Tahoma", Font.BOLD, 12));
		    lblPaycode.setBounds(168, 142, 73, 21);
		    frame.getContentPane().add(lblPaycode);
		    
		    comboBox_1 = new JComboBox<String>();
		    comboBox_1.setEnabled(false);
		    comboBox_1.addItem("CASH");
		    comboBox_1.addItem("OMANI RIYAL");
		    comboBox_1.addItem("USD");
		    comboBox_1.addItem("SAUDI RIYAL");
		    comboBox_1.setBounds(154, 176, 119, 29);
		    frame.getContentPane().add(comboBox_1);
		    
		    
		    JLabel lblPoNumber = new JLabel("PO COUNT :");
		    lblPoNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		    lblPoNumber.setBounds(454, 143, 66, 21);
		    frame.getContentPane().add(lblPoNumber);
		    
		    
		  	dateChooser = new JDateChooser();
		    dateChooser.setBounds(303, 176, 135, 30);
		    frame.getContentPane().add(dateChooser);
		    dateChooser.setDateFormatString("yyyy-MM-dd");
		    dateChooser.setVisible(false);
		    
		    textField_3 = new JTextField();
		    textField_3.setEnabled(false);
		    textField_3.setBounds(124, 247, 277, 22);
		    frame.getContentPane().add(textField_3);
		    textField_3.setColumns(10);
		    
		    
		    comboBox = new JComboBox<String>();
		    comboBox.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(!comboBox.getSelectedItem().toString().equals("CASH"))
		    		{
		    			comboBox_1.setEnabled(false);
		    			comboBox_2.setEnabled(true);
		    			textField_3.setEnabled(true);
		    			textField_4.setEnabled(true);
		    			chckbxAcPayee.setEnabled(true);
		    			chckbxNewCheckBox.setEnabled(true);
		    			dateChooser.setVisible(true);
		    			lblDate.setText("Cheque Date :");
		    		}
		    		else
		    		{
		    			comboBox_1.setEnabled(true);
		    			comboBox_2.setSelectedIndex(-1);
		    			comboBox_2.setEnabled(false);
		    			textField_3.setEnabled(false);
		    			textField_4.setEnabled(false);
		    			chckbxAcPayee.setEnabled(false);
		    			chckbxNewCheckBox.setEnabled(false);
		    			lblDate.setText("Date :");
		    			dateChooser.setVisible(true);
		    		}
		    	}
		    });
		    comboBox.addItem("CASH");
		    comboBox.addItem("BANK");
		    comboBox.setBounds(17, 177, 113, 29);
		    frame.getContentPane().add(comboBox);
		    
		    
		    textField_8 = new JTextField();
		    textField_8.setEditable(false);
		    textField_8.setBounds(460, 175, 60, 30);
		    frame.getContentPane().add(textField_8);
		    textField_8.setColumns(10);
			
			
			try{
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				String query="SELECT * FROM "+company+".mr_sd_customer_payment where ref_cust_id='"+vendor_id+"' and type='AD' and status='1' "; 
				ResultSet resultSet = statement.executeQuery(query);
				
				while(resultSet.next())
				{
					String paid=resultSet.getString("bal_amt");
					ad_amt+=Float.parseFloat(paid);
					y=y+1;
				}
				
			}
			catch(Exception e){
				System.err.println(e);
			}
			
			BigDecimal bd = new BigDecimal(ad_amt);
    		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
    		ad_amt = bd.doubleValue();
			
			double bal = balance-ad_amt; 
			BigDecimal bq = new BigDecimal(bal);
    		bq = bq.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
    		bal = bq.doubleValue();
			textField = new JTextField(Double.toString(bal));
			textField.setEditable(false);
		    textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		    textField.setBackground(new Color(255, 102, 153));
		    textField.setBounds(10, 91, 90, 37);
		    frame.getContentPane().add(textField);
		    textField.setColumns(10);
		    
		    JLabel lblDiscount = new JLabel("Discount");
		    lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    lblDiscount.setBounds(227, 69, 90, 16);
		    frame.getContentPane().add(lblDiscount);
		    
		    textField_7 = new JTextField();
		    textField_7.addKeyListener(new KeyAdapter() {
		    	@Override
		    	public void keyTyped(KeyEvent e) {
		    		char vChar = e.getKeyChar();
				    if (textField_7.getText().equals(""))
				        dot = false;
				    if (dot == false){
				        if (vChar == '.') dot = true;
				        else if (!(Character.isDigit(vChar)
				                || (vChar == KeyEvent.VK_BACK_SPACE)
				                || (vChar == KeyEvent.VK_DELETE))) {
				                e.consume();
				        }
				    } else {
				        if (!(Character.isDigit(vChar)
				                || (vChar == KeyEvent.VK_BACK_SPACE)
				                || (vChar == KeyEvent.VK_DELETE))) {
				                e.consume();
				        }
				    }
		    	}
		    	@Override
		    	public void keyPressed(KeyEvent e) {
		    		if (e.getKeyCode()==KeyEvent.VK_ENTER){
				    	textField_9.requestFocus();
					}
		    	}
		    });
		    textField_7.addFocusListener(new FocusAdapter() {
		    	@Override
		    	public void focusLost(FocusEvent arg0) {
		    		if(x>1)
		    		{
		    			String enter_amt=textField_1.getText();
		    			if(enter_amt.equals(""))
		    			{
		    				JOptionPane.showMessageDialog(frame, "Please Enter the Amount");
		    				textField_1.requestFocus();
		    			}
		    			else
		    			{
		    				String disc=textField_7.getText();
		    				if(disc.equals(""))
		    				{
		    					disc="0";
		    				}
		    				String roundoff=textField_9.getText();
		    				if(roundoff.equals(""))
		    				{
		    					roundoff="0";
		    				}
		    				double net_amt=(Double.parseDouble(enter_amt)+Double.parseDouble(disc)+Double.parseDouble(roundoff));
		    				BigDecimal bd = new BigDecimal(net_amt);
	                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
	                		net_amt = bd.doubleValue();
		    				textField_10.setText(Double.toString(net_amt));
		    				String net_ttl=textField_10.getText();
		    				if(Double.parseDouble(net_ttl)>Double.parseDouble(textField.getText()))
							{
								JOptionPane.showMessageDialog(frame, "Enter Amount Greater Than Balance Amount");
								textField_1.requestFocus();
							}
		    			}
		    			
		    		}
		    		else
		    		{
		    			String enter_amt=textField_1.getText();
		    			if(enter_amt.equals(""))
		    			{
		    				JOptionPane.showMessageDialog(frame, "Please Enter the Amount");
		    				textField_1.requestFocus();
		    			}
		    			else
		    			{
		    				String bal_amt=textField.getText();
			    			if(Double.parseDouble(bal_amt)<0)
			    			{
			    				textField_1.setEditable(false);
			    				String disc=textField_7.getText();
			    				if(disc.equals(""))
			    				{
			    					disc="0";
			    				}
			    				String roundoff=textField_9.getText();
			    				if(roundoff.equals(""))
			    				{
			    					roundoff="0";
			    				}
			    				double net_amt=(Double.parseDouble(enter_amt)+Double.parseDouble(disc)+Double.parseDouble(roundoff));
			    				BigDecimal bd = new BigDecimal(net_amt);
		                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
		                		net_amt = bd.doubleValue();
			    				textField_10.setText(Double.toString(net_amt));
			    			}
			    			else
			    			{
			    				if(Double.parseDouble(enter_amt)>Double.parseDouble(bal_amt))
								{
									JOptionPane.showMessageDialog(frame, "Enter Amount Greater Than Balance Amount");
									textField_1.requestFocus();
								}
			    				else
				    			{
				    				String disc=textField_7.getText();
				    				if(disc.equals(""))
				    				{
				    					disc="0";
				    				}
				    				String roundoff=textField_9.getText();
				    				if(roundoff.equals(""))
				    				{
				    					roundoff="0";
				    				}
				    				double net_amt=(Double.parseDouble(enter_amt)+Double.parseDouble(disc)+Double.parseDouble(roundoff));
				    				BigDecimal bd = new BigDecimal(net_amt);
			                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
			                		net_amt = bd.doubleValue();
				    				textField_10.setText(Double.toString(net_amt));
				    			}
			    			}
		    			}
		    			
		    			
		    		}
		    	}
		    });
		    textField_7.setText("0");
		    textField_7.setBounds(227, 92, 90, 37);
		    frame.getContentPane().add(textField_7);
		    textField_7.setColumns(10);
		    
		    textArea = new JTextArea();
		    frame.getContentPane().add(textArea);
		    textArea.setBounds(127, 358, 393, 65);
		    
		    
		    
		    ScrollPane scrollPane = new ScrollPane();
		    scrollPane.setBounds(124, 355, 397, 69);
		    frame.getContentPane().add(scrollPane);
		    
		    JLabel label_2 = new JLabel("+");
		    label_2.setHorizontalAlignment(SwingConstants.CENTER);
		    label_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		    label_2.setBounds(199, 92, 23, 37);
		    frame.getContentPane().add(label_2);
		    
		    JLabel label_3 = new JLabel("=");
		    label_3.setFont(new Font("Tahoma", Font.BOLD, 25));
		    label_3.setBounds(415, 92, 23, 35);
		    frame.getContentPane().add(label_3);
		    
		    textField_10 = new JTextField();
		    textField_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		    textField_10.setText("0.0");
		    textField_10.setEditable(false);
		    textField_10.setBounds(438, 92, 90, 37);
		    frame.getContentPane().add(textField_10);
		    textField_10.setBackground(Color.GREEN);
		    textField_10.setColumns(10);
		    
		    JLabel lblNetAmount = new JLabel("Net Amount:");
		    lblNetAmount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    lblNetAmount.setBounds(438, 69, 90, 21);
		    frame.getContentPane().add(lblNetAmount);
		    
		    JLabel lblDocNo = new JLabel("Doc No. : ");
		    lblDocNo.setBounds(17, 442, 95, 14);
		    frame.getContentPane().add(lblDocNo);
		    
		    textField_2 = new JTextField();
		    textField_2.setBounds(124, 439, 396, 20);
		    frame.getContentPane().add(textField_2);
		    textField_2.setColumns(10);
		    
		    JLabel lblCustomerBankName = new JLabel("Customer Bank Name");
		    lblCustomerBankName.setBounds(0, 247, 130, 22);
		    frame.getContentPane().add(lblCustomerBankName);
		    
		    textField_9 = new JTextField();
		    textField_9.addKeyListener(new KeyAdapter() {
		    	@Override
		    	public void keyTyped(KeyEvent e) {
		    	    char vChar = e.getKeyChar();
		    	    String text = textField_9.getText();

		    	    // Reset dot if field empty
		    	    if (text.equals(""))
		    	        dot = false;

		    	    // Allow minus only at first position and only once
		    	    if (vChar == '-') {
		    	        if (text.length() == 0 && !text.contains("-")) {
		    	            return; // allow
		    	        } else {
		    	            e.consume();
		    	            return;
		    	        }
		    	    }

		    	    if (!dot) {
		    	        if (vChar == '.') {
		    	            dot = true;
		    	        } 
		    	        else if (!(Character.isDigit(vChar)
		    	                || vChar == KeyEvent.VK_BACK_SPACE
		    	                || vChar == KeyEvent.VK_DELETE)) {
		    	            e.consume();
		    	        }
		    	    } else {
		    	        if (!(Character.isDigit(vChar)
		    	                || vChar == KeyEvent.VK_BACK_SPACE
		    	                || vChar == KeyEvent.VK_DELETE)) {
		    	            e.consume();
		    	        }
		    	    }
		    	}

		    	
		    	@Override
		    	public void keyPressed(KeyEvent e) {
		    		if (e.getKeyCode()==KeyEvent.VK_ENTER){
		    			comboBox.requestFocus();
					}
		    	}
		    });
		    textField_9.addFocusListener(new FocusAdapter() {
		    	@Override
		    	public void focusLost(FocusEvent arg0) {
		    		if(x>1)
		    		{
		    			String enter_amt=textField_1.getText();
		    			if(enter_amt.equals(""))
		    			{
		    				JOptionPane.showMessageDialog(frame, "Please Enter the Amount");
		    				textField_1.requestFocus();
		    			}
		    			else
		    			{
		    				String disc=textField_7.getText();
		    				if(disc.equals(""))
		    				{
		    					disc="0";
		    				}
		    				String roundoff=textField_9.getText();
		    				if(roundoff.equals(""))
		    				{
		    					roundoff="0";
		    				}
		    				double net_amt=(Double.parseDouble(enter_amt)+Double.parseDouble(disc)+Double.parseDouble(roundoff));
		    				BigDecimal bd = new BigDecimal(net_amt);
	                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
	                		net_amt = bd.doubleValue();
		    				textField_10.setText(Double.toString(net_amt));
		    				String net_ttl=textField_10.getText();
		    				if(Double.parseDouble(net_ttl)>Double.parseDouble(textField.getText()))
							{
								JOptionPane.showMessageDialog(frame, "Enter Amount Greater Than Balance Amount");
								textField_1.requestFocus();
							}
		    			}
		    			
		    		}
		    		else
		    		{
		    			String enter_amt=textField_1.getText();
		    			if(enter_amt.equals(""))
		    			{
		    				JOptionPane.showMessageDialog(frame, "Please Enter the Amount");
		    				textField_1.requestFocus();
		    			}
		    			else
		    			{
		    				String bal_amt=textField.getText();
			    			if(Double.parseDouble(bal_amt)<0)
			    			{
			    				textField_1.setEditable(false);
			    				String disc=textField_7.getText();
			    				if(disc.equals(""))
			    				{
			    					disc="0";
			    				}
			    				String roundoff=textField_9.getText();
			    				if(roundoff.equals(""))
			    				{
			    					roundoff="0";
			    				}
			    				double net_amt=(Double.parseDouble(enter_amt)+Double.parseDouble(disc)+Double.parseDouble(roundoff));
			    				BigDecimal bd = new BigDecimal(net_amt);
		                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
		                		net_amt = bd.doubleValue();
			    				textField_10.setText(Double.toString(net_amt));
			    			}
			    			else
			    			{
			    				if(Double.parseDouble(enter_amt)>Double.parseDouble(bal_amt))
								{
									JOptionPane.showMessageDialog(frame, "Enter Amount Greater Than Balance Amount");
									textField_1.requestFocus();
								}
			    				else
				    			{
				    				String disc=textField_7.getText();
				    				if(disc.equals(""))
				    				{
				    					disc="0";
				    				}
				    				String roundoff=textField_9.getText();
				    				if(roundoff.equals(""))
				    				{
				    					roundoff="0";
				    				}
				    				double net_amt=(Double.parseDouble(enter_amt)+Double.parseDouble(disc)+Double.parseDouble(roundoff));
				    				BigDecimal bd = new BigDecimal(net_amt);
			                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
			                		net_amt = bd.doubleValue();
				    				textField_10.setText(Double.toString(net_amt));
				    			}
			    			}
		    			}
		    			
		    			
		    		}
		    	}
		    });
		    textField_9.setText("0");
		    textField_9.setColumns(10);
		    textField_9.setBounds(355, 92, 60, 37);
		    frame.getContentPane().add(textField_9);
		    
		    JLabel lblRoundoff = new JLabel("Roundoff");
		    lblRoundoff.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    lblRoundoff.setBounds(355, 69, 60, 16);
		    frame.getContentPane().add(lblRoundoff);
		    
		    JLabel label = new JLabel("+");
		    label.setHorizontalAlignment(SwingConstants.CENTER);
		    label.setFont(new Font("Tahoma", Font.BOLD, 25));
		    label.setBounds(322, 92, 23, 37);
		    frame.getContentPane().add(label);
		    
		    
		    
		   
		    
		    for(int i=0;i<item_list.length;i++)
			{
		    	if(item_list[i]!=null)
				{
		    		x=x+1;
		    		textArea.append(item_list[i].toString()+",");
		    		textField_8.setText(Integer.toString(x));
		    	}
			}
		    String bal_amt=textField.getText();
			if(Double.parseDouble(bal_amt)<0)
			{
				textField_1.setEditable(false);
				textField_1.setText(bal_amt);
				//menuItem.setEnabled(false);
			}
			
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 544, 511);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
