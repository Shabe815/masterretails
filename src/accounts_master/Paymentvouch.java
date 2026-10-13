package accounts_master;

import java.awt.Color;
import java.awt.Cursor;
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
import java.sql.SQLException;
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

import purchase_master.po_posted_list;
import purchase_master.po_view;
import purchase_master.post_po;
import purchase_master.purchase_order_list;

import java.awt.event.KeyAdapter;

public class Paymentvouch {

	
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
	private JTextField textField_3;
	private JTextField textField_9;
	private JDateChooser dateChooser;
	private JTextArea textArea;
	private int x=0;
	private JTextField textField_7;
	private boolean dot=false;
	private JTextField textField_10;
	private JComboBox<String> comboBox_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Paymentvouch window = new Paymentvouch( "mr_pro","admin", "S01",null,120,"v001","DIJI","1.0","2",0,null,null,"","");
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
	 * @param invoice_list 
	 * @param return_list 
	 * @param pagetype 
	 */
	public Paymentvouch( String company, String username,  String store,Object[] item_list, double balance, String vendor_id, String vendor_name,String version,String decimal,double amt_rtn, Object[] invoice_list, Object[] return_list, String pagetype, String language) {
		initialize(company,username,store,item_list,balance,vendor_id,vendor_name,version,decimal,amt_rtn, invoice_list, return_list,pagetype,language);
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
	 * @param language 
	 * @param vendor_name2 
	 */
	private void initialize( final String company,final String username,final String store,final Object[] item_list, double balance,final String vendor_id, String vendor_name, String version, final String decimal,double amt_rtn,final Object[] invoice_list,final Object[] return_list,final String pagetype, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(pagetype.equals("PO"))
				{
					purchase_order_list.frame.setVisible(false);
					purchase_order_list. frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		            purchase_order_list.textField.setText("");
		            purchase_order_list.get_po_disp( company, "OPEN", "");	
				}
				else
				{
					accounts_master.Accountpayable.get_po_term_disp(company, vendor_id, decimal);	
				}
			}
			@Override
		      public void windowIconified(WindowEvent we) {
		        frame.setState(JFrame.NORMAL);
		      }
		});
		
		
		
		Image Billing=new ImageIcon(this.getClass().getResource("/Billing-icon.png")).getImage();
		
		JLabel lblCompanyName = new JLabel("Balance Amount:");
		if(language.equals("Arabic"))
		{
			lblCompanyName.setText("„»·€ «·—’Ìœ:");
		}
		lblCompanyName.setBounds(354, 66, 108, 21);
		lblCompanyName.setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.getContentPane().add(lblCompanyName);
		
		
		JLabel lblCompanySetup = new JLabel("Payment Voucher");
		if(language.equals("Arabic"))
		{
			lblCompanySetup.setText("ﬁ”Ì„… «·œ›⁄");
		}
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
		if(language.equals("Arabic"))
		{
			menuItem.setText("ÌÕ›Ÿ");
		}
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_1.getText();
				String start_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if(amt.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "<html>Please Enter The Amount<br>«·—Ã«¡ ≈œŒ«· «·„»·€</html>");
				}
				else if(start_date.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "<html>Please choose a Date<br>«·—Ã«¡ «Œ Ì«— «· «—ÌŒ</html>");
				}
				else
				{
					boolean status=false;
					boolean type=false;
					if(!comboBox.getSelectedItem().toString().equals("CASH"))
					{
						if(comboBox_2.getSelectedItem().toString().equals(""))
						{
							JOptionPane.showMessageDialog(frame, "<html>please select Bank Name<br>«·—Ã«¡  ÕœÌœ «”„ «·»‰ﬂ</html>");
						}
						else if(textField_4.getText().equals(""))
						{
							JOptionPane.showMessageDialog(frame, "<html>please enter Cheque No.<br>«·—Ã«¡ ≈œŒ«· —ﬁ„ «·‘Ìﬂ</html>");
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
							JOptionPane.showMessageDialog(frame, "<html>Enter Amount Greater Than Balance Amount<br>√œŒ· «·„»·€ «·√ﬂ»— „‰ „»·€ «·—’Ìœ</html>");
							textField_1.requestFocus();						}
						else if(Double.parseDouble(textField_10.getText())>Double.parseDouble(textField.getText()))
						{
							JOptionPane.showMessageDialog(frame, "<html>Net Amount Greater Than Balance Amount<br>«·„»·€ «·’«›Ì √ﬂ»— „‰ «·„»·€ «·„ »ﬁÌ</html>");
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
									JOptionPane.showMessageDialog(frame, "<html>Net Amount Not Equal Balance Amount<br>«·„»·€ «·’«›Ì €Ì— „ ”«ÊÚ „»·€ «·—’Ìœ</html>");
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
								String pdc="0";
								String ac_payee="0";
								String remark="";
								String pay_to="";
								String pay_note="";
								String po_num="";
								String disc="";
								String payment_mode=comboBox.getSelectedItem().toString();
								String tranname="";
								if(payment_mode.equals("CASH"))
								{
									paycode=comboBox_1.getSelectedItem().toString();
									tranname="AP";
								}
								else
								{
									tranname="AP";
									bank_name=comboBox_2.getSelectedItem().toString();
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
										try {
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
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}  
										double new_amt_lt=Double.parseDouble(amt);
										BigDecimal ba = new BigDecimal(new_amt_lt);
				                		ba = ba.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
				                		new_amt_lt = ba.doubleValue();
										String save_ap="insert into "+company+".mr_ap_vouchers(trans_num,`ref_vendor_id`, `dr_amt`,cr_amt, `payment_method`, `paycode`, `bank_name`, `cheque_no`, `pdc_check`, `ac_payee`, `remark`, `pay_to`, `note`, `store_id`, `username`, `created_date`, `modified_date`,disc_amt,issue_date,status) VALUES"
												+ "('"+seq_num+"','"+vendor_id+"','"+new_amt_lt+"','"+textField_9.getText()+"','"+payment_mode+"','"+paycode+"','"+bank_name+"','"+cheque+"','"+pdc+"','"+ac_payee+"','"+remark+"','"+pay_to+"','"+pay_note+"','"+store+"','"+username+"','"+formattedDate+"','"+formattedDate+"','"+disc+"','"+start_date+"','OPEN') ";
										try {
											statement.executeUpdate(save_ap);
											if(!payment_mode.equals("CASH"))
											{
											String query21="insert into "+company+".mr_cheque_details( `tran_num`, `pay_by`,`pay_id`, `cr`, `dr`, `issue_date`, `cheque_no`, `cheque_date`,  `printing_user`,  `handover_user`, `posted_user`, `remark`, `note`,bank_name,status,page_type) "
													+ "VALUES ('"+seq_num+"','"+pay_to+"','"+vendor_id+"','0','"+new_amt_lt+"','"+formattedDate+"','"+cheque+"','"+start_date+"','"+username+"','"+username+"','"+username+"','"+textField_5.getText()+"','"+textArea.getText()+"','"+bank_name+"','OPEN','AP')";
											statement.executeUpdate(query21);
											}
											
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										String select_vendor="select * from "+company+".mr_vendor where vendor_code='"+vendor_id+"' ";
										ResultSet resultSet1 = statement.executeQuery(select_vendor);
										double new_paid=0;
										double new_pending=0;
										if(resultSet1.next())
										{
											String old_paid=resultSet1.getString("vendor_paid_amt");
											String old_pending=resultSet1.getString("vendor_pending_amt");
											new_paid=Double.parseDouble(old_paid)+Double.parseDouble(amt)+Double.parseDouble(disc);
											new_pending=Double.parseDouble(old_pending)-(Double.parseDouble(amt)+Double.parseDouble(disc));
											
											BigDecimal bp = new BigDecimal(new_paid);
					                		bp = bp.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
					                		new_paid = bp.doubleValue();
					                		
					                		BigDecimal bl = new BigDecimal(new_pending);
					                		bl= bl.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
					                		new_pending = bl.doubleValue();
										}
										
										String updates="update "+company+".mr_vendor set vendor_pending_amt='"+new_pending+"',vendor_paid_amt='"+new_paid+"' where vendor_code = '"+vendor_id+"' ";
										statement.executeUpdate(updates);
										String disc_amt="0";
										
										for(int i=0;i<return_list.length;i++)
										{
											if(return_list[i]!=null)
											{
									    		String pr_num=return_list[i].toString();
									    		String query="SELECT * FROM "+company+".mr_vendor_payment where ref_po_num='"+pr_num+"' and ref_vendor_id='"+vendor_id+"' "; 
												ResultSet resultSet = statement1.executeQuery(query);
												if(resultSet.next())
												{
													String bal=resultSet.getString("bal_amt");
													String paid=resultSet.getString("paid_amt");
													
													
													String update="update "+company+".mr_vendor_payment set paid_amt='"+bal+"',bal_amt='0' where ref_po_num='"+pr_num+"' and ref_vendor_id='"+vendor_id+"'  ";
													statement.executeUpdate(update);
													String save_file="insert into "+company+".mr_vendor_payment_details(`ref_po_num`,trans_num,`ref_vendor_id`, `dr_amt`,cr_amt, `payment_method`, `paycode`, `bank_name`, `cheque_no`, `pdc_check`, `ac_payee`, `remark`, `pay_to`, `note`, `store_id`, `username`, `created_date`, `modified_date`,disc_amt,issue_date,status,invoicetype) VALUES('"+pr_num+"','"+seq_num+"','"+vendor_id+"','"+paid+"','"+bal+"','"+payment_mode+"','"+paycode+"','"+bank_name+"','"+cheque+"','"+pdc+"','"+ac_payee+"','"+remark+"','"+pay_to+"','"+pay_note+"','"+store+"','"+username+"','"+formattedDate+"','"+formattedDate+"','0','"+start_date+"','OPEN','AP') ";
													statement.executeUpdate(save_file);
													
													
													
												}
											}
										}
										for(int i=0;i<invoice_list.length;i++)
										{
									    	if(invoice_list[i]!=null)
											{
									    		po_num=invoice_list[i].toString();
									    		try{
													if(x>1)
													{
														disc_amt=Double.toString(Double.parseDouble(disc)/x);
														
														String query="SELECT * FROM "+company+".mr_vendor_payment where ref_po_num='"+po_num+"' and ref_vendor_id='"+vendor_id+"' "; 
														ResultSet resultSet = statement1.executeQuery(query);
														if(resultSet.next())
														{
															String paid=resultSet.getString("paid_amt");
															String bal=resultSet.getString("bal_amt");
															
															double new_paid_bal=Double.parseDouble(paid)+Double.parseDouble(bal);
															double new_bal=Double.parseDouble(bal)-Double.parseDouble(bal);
															double new_ttl=Double.parseDouble(bal)-Double.parseDouble(disc_amt);
															double new_pr_ttl=Double.parseDouble(bal)+Double.parseDouble(disc_amt);
															BigDecimal bj = new BigDecimal(new_paid_bal);
									                		bj = bj.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
									                		new_paid_bal = bj.doubleValue();
									                		BigDecimal bd = new BigDecimal(new_bal);
									                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
									                		new_bal = bd.doubleValue();
															String update="update "+company+".mr_vendor_payment set paid_amt='"+new_paid_bal+"',bal_amt='"+new_bal+"' where ref_po_num='"+po_num+"' and ref_vendor_id='"+vendor_id+"'  ";
															statement.executeUpdate(update);
															if(resultSet.getString("status").equals("2"))
															{
																String save_file="insert into "+company+".mr_vendor_payment_details(`ref_po_num`,trans_num,`ref_vendor_id`, `dr_amt`,cr_amt, `payment_method`, `paycode`, `bank_name`, `cheque_no`, `pdc_check`, `ac_payee`, `remark`, `pay_to`, `note`, `store_id`, `username`, `created_date`, `modified_date`,disc_amt,issue_date,status) VALUES('"+po_num+"','"+seq_num+"','"+vendor_id+"','0','"+new_pr_ttl+"','"+payment_mode+"','"+paycode+"','"+bank_name+"','"+cheque+"','"+pdc+"','"+ac_payee+"','"+remark+"','"+pay_to+"','"+pay_note+"','"+store+"','"+username+"','"+formattedDate+"','"+formattedDate+"','0','"+start_date+"','OPEN') ";
																statement.executeUpdate(save_file);
															}
															else
															{
																String save_file="insert into "+company+".mr_vendor_payment_details(`ref_po_num`,trans_num,`ref_vendor_id`, `dr_amt`,cr_amt, `payment_method`, `paycode`, `bank_name`, `cheque_no`, `pdc_check`, `ac_payee`, `remark`, `pay_to`, `note`, `store_id`, `username`, `created_date`, `modified_date`,disc_amt,issue_date,status) VALUES('"+po_num+"','"+seq_num+"','"+vendor_id+"','"+new_ttl+"','0','"+payment_mode+"','"+paycode+"','"+bank_name+"','"+cheque+"','"+pdc+"','"+ac_payee+"','"+remark+"','"+pay_to+"','"+pay_note+"','"+store+"','"+username+"','"+formattedDate+"','"+formattedDate+"','"+disc_amt+"','"+start_date+"','OPEN') ";
																statement.executeUpdate(save_file);
															}
															
															
															
														}
													}
													else
													{
														//double total=Double.parseDouble(amt)+Double.parseDouble(textField_9.getText());
														String save_file="insert into "+company+".mr_vendor_payment_details(`ref_po_num`,trans_num, `ref_vendor_id`, `dr_amt`,cr_amt, `payment_method`, `paycode`, `bank_name`, `cheque_no`, `pdc_check`, `ac_payee`, `remark`, `pay_to`, `note`, `store_id`, `username`, `created_date`, `modified_date`,disc_amt,issue_date,status) VALUES('"+po_num+"','"+seq_num+"','"+vendor_id+"','"+amt+"','0','"+payment_mode+"','"+paycode+"','"+bank_name+"','"+cheque+"','"+pdc+"','"+ac_payee+"','"+remark+"','"+pay_to+"','"+pay_note+"','"+store+"','"+username+"','"+formattedDate+"','"+formattedDate+"','"+disc+"','"+start_date+"','OPEN') ";
														statement.executeUpdate(save_file);
														
														
														
														String query="SELECT * FROM "+company+".mr_vendor_payment where ref_po_num='"+po_num+"' and ref_vendor_id='"+vendor_id+"' "; 
														ResultSet resultSet = statement.executeQuery(query);
														if(resultSet.next())
														{
															String paid=resultSet.getString("paid_amt");
															String bal=resultSet.getString("bal_amt");
															double new_paid_bal=Double.parseDouble(paid)+Double.parseDouble(amt)+Double.parseDouble(disc)+Double.parseDouble(textField_9.getText());
															double new_bal=Double.parseDouble(bal)-(Double.parseDouble(amt)+Double.parseDouble(disc)+Double.parseDouble(textField_9.getText()));
															BigDecimal bd = new BigDecimal(new_bal);
									                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
									                		new_bal = bd.doubleValue();
															String update="update "+company+".mr_vendor_payment set paid_amt='"+new_paid_bal+"',bal_amt='"+new_bal+"' where ref_po_num='"+po_num+"' and ref_vendor_id='"+vendor_id+"'  ";
															statement.executeUpdate(update);
															
														}
													}
												}
												catch(Exception e){
													System.err.println(e);
												}
									    		
									    	}
										}
										
										
										try {
											
											report_master.Vendor_voucher.report(con,seq_num,company);
											con.close();
											JOptionPane.showMessageDialog(frame, "<html>Saved Successfully<br> „ «·Õ›Ÿ »‰Ã«Õ</html>");	
											frame.setVisible(false);
											if(pagetype.equals("PO"))
											{
												post_po.frame.setVisible(false);
												post_po. frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								                purchase_order_list.textField.setText("");
								                purchase_order_list.get_po_disp( company, "OPEN", "");	
											}
											else if(pagetype.equals("PR"))
											{
												po_view.frame.setVisible(false);
												po_posted_list.get_po_disp( company,"POSTED","","ALL");
											}
											else
											{
												accounts_master.Accountpayable.get_po_term_disp(company, vendor_id, decimal);	
											}
											
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
								    }
									else
									{
										JOptionPane.showMessageDialog(frame, "<html>Cheque Number Already Exist<br>—ﬁ„ «·‘Ìﬂ „ÊÃÊœ »«·›⁄·</html>");
									}
						        } catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
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
	    if(language.equals("Arabic"))
		{
	    	menuItem_1.setText("Ì‰⁄‘");
		}
	    menuItem_1 = new JMenuItem("Refresh",KeyEvent.VK_F5);
	    menuItem_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		textField_1.setText("");
	    		comboBox_2.setSelectedIndex(-1);
	    		textField_4.setText("");
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
	    if(language.equals("Arabic"))
		{
	    	menuItem_2.setText("„Œ—Ã");
		}
	    menuItem_2.setAccelerator(KeyStroke.getKeyStroke("F4"));
	    menuItem_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		frame.setVisible(false);
	    		//accounts_master.Accountpayable.get_po_term_disp(company, vendor_id, decimal);
	    		if(pagetype.equals("PO"))
				{
					post_po.frame.setVisible(false);
					post_po. frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	                purchase_order_list.textField.setText("");
	                purchase_order_list.get_po_disp( company, "OPEN", "");	
				}
				else
				{
					accounts_master.Accountpayable.get_po_term_disp(company, vendor_id, decimal);	
				}
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
		if(language.equals("Arabic"))
		{
			model.addColumn("‘›—…"); 
		    model.addColumn("≈”„ «·Õ”«»"); 
			model.addColumn("„Ã„Ê⁄… «·Õ”«»"); 
			model.addColumn("‰Ê⁄ «·Õ”«»"); 
		}
		else
		{
			model.addColumn("Code"); 
		    model.addColumn("Acccount Name"); 
			model.addColumn("Account Group"); 
			model.addColumn("Account Type"); 
		}
		    
			model.addRow(new Object[]{"","","",""});
		    
		    
		    
		    JLabel lblAmount = new JLabel("Paying Amount :");
		    if(language.equals("Arabic"))
			{
		    	lblAmount.setText("„»·€ «·œ›⁄ :");
			}
		    lblAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
		    lblAmount.setBounds(21, 138, 172, 21);
		    frame.getContentPane().add(lblAmount);
		    
		    textField_1 = new JTextField();
		    textField_1.setText("0");
		    textField_1.addKeyListener(new KeyAdapter() {
		    	@Override
		    	public void keyTyped(KeyEvent e) {
		    		char vChar = e.getKeyChar();
				    if (textField_1.getText().equals(""))
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
		    });
		    textField_1.addFocusListener(new FocusAdapter() {
		    	@Override
		    	public void focusLost(FocusEvent arg0) {
		    		if(x>1)
		    		{
		    			String enter_amt=textField_1.getText();
		    			if(enter_amt.equals(""))
		    			{
		    				JOptionPane.showMessageDialog(frame, "<html>Please Enter the Amount<br>«·—Ã«¡ ≈œŒ«· «·„»·€</html>");
		    				textField_1.requestFocus();
		    			}
		    			else
		    			{
		    				String disc=textField_7.getText();
		    				if(disc.equals(""))
		    				{
		    					disc="0";
		    				}
		    				double net_amt=(Double.parseDouble(enter_amt)+Double.parseDouble(disc));
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
		    				JOptionPane.showMessageDialog(frame, "<html>Please Enter the Amount<br>«·—Ã«¡ ≈œŒ«· «·„»·€</html>");
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
				    				double net_amt=(Double.parseDouble(enter_amt)+Double.parseDouble(disc));
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
		    textField_1.setBounds(21, 163, 135, 37);
		    frame.getContentPane().add(textField_1);
		    textField_1.requestFocus();
		    
		    comboBox_2 = new JComboBox<String>();
		    comboBox_2.setEnabled(false);
		    comboBox_2.setBounds(128, 318, 277, 22);
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
		    if(language.equals("Arabic"))
			{
		    	lblPaymentMode.setText("Ê÷⁄ «·œ›⁄:");
			}
		    lblPaymentMode.setFont(new Font("Tahoma", Font.BOLD, 11));
		    lblPaymentMode.setBounds(21, 243, 97, 21);
		    frame.getContentPane().add(lblPaymentMode);
		    
		    JLabel lblBankName = new JLabel("Bank Name:");
		    if(language.equals("Arabic"))
			{
		    	lblBankName.setText("«”„ «·»‰ﬂ:");
			}
		    lblBankName.setBounds(22, 319, 73, 21);
		    frame.getContentPane().add(lblBankName);
		    
		    
		    final JLabel lblDate = new JLabel("Date :");
		    if(language.equals("Arabic"))
			{
		    	lblDate.setText(" «—ÌŒ :");
			}
		    lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		    lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		    lblDate.setBounds(296, 241, 97, 22);
		    frame.getContentPane().add(lblDate);
		    
		    JLabel lblCheque = new JLabel("Cheque# :");
		    if(language.equals("Arabic"))
			{
		    	lblCheque.setText("Ì›Õ’# :");
			}
		    lblCheque.setBounds(22, 361, 73, 14);
		    frame.getContentPane().add(lblCheque);
		    
		    textField_4 = new JTextField();
		    textField_4.setEnabled(false);
		    textField_4.setColumns(10);
		    textField_4.setBounds(128, 358, 277, 20);
		    frame.getContentPane().add(textField_4);
		    
		    chckbxNewCheckBox = new JCheckBox("PDC");
		    if(language.equals("Arabic"))
			{
		    	chckbxNewCheckBox.setText("»Ì œÌ ”Ì");
			}
		    chckbxNewCheckBox.setEnabled(false);
		    chckbxNewCheckBox.setBounds(427, 318, 97, 23);
		    frame.getContentPane().add(chckbxNewCheckBox);
		    
		    chckbxAcPayee = new JCheckBox("A/C PAYEE");
		    if(language.equals("Arabic"))
			{
		    	chckbxAcPayee.setText("«·„” ›Ìœ „‰ «·Õ”«»");
			}
		    chckbxAcPayee.setEnabled(false);
		    chckbxAcPayee.setBounds(427, 357, 97, 23);
		    frame.getContentPane().add(chckbxAcPayee);
		    
		    JLabel lblRemarks = new JLabel("Remarks:");
		    if(language.equals("Arabic"))
			{
		    	lblRemarks.setText("„·«ÕŸ« :");
			}
		    lblRemarks.setBounds(22, 394, 73, 14);
		    frame.getContentPane().add(lblRemarks);
		    
		    textField_5 = new JTextField();
		    textField_5.setColumns(10);
		    textField_5.setBounds(128, 391, 395, 20);
		    frame.getContentPane().add(textField_5);
		    
		    JLabel lblPayTo = new JLabel("Pay to:");
		    if(language.equals("Arabic"))
			{
		    	lblPayTo.setText("«·œ›⁄ ≈·Ï:");
			}
		    lblPayTo.setBounds(22, 432, 73, 14);
		    frame.getContentPane().add(lblPayTo);
		    
		    textField_6 = new JTextField(vendor_name);
		    textField_6.setColumns(10);
		    textField_6.setBounds(128, 429, 395, 20);
		    frame.getContentPane().add(textField_6);
		    
		    JLabel lblNote = new JLabel("Note:");
		    if(language.equals("Arabic"))
			{
		    	lblNote.setText("„·ÕÊŸ…:");
			}
		    lblNote.setBounds(21, 462, 73, 14);
		    frame.getContentPane().add(lblNote);
		    
		    
		    
		    JLabel lblPaycode = new JLabel("Paycode:");
		    if(language.equals("Arabic"))
			{
		    	lblPaycode.setText("—„“ «·œ›⁄:");
			}
		    lblPaycode.setFont(new Font("Tahoma", Font.BOLD, 12));
		    lblPaycode.setBounds(172, 242, 73, 21);
		    frame.getContentPane().add(lblPaycode);
		    
		    comboBox_1 = new JComboBox<String>();
		    comboBox_1.setEnabled(false);
		    comboBox_1.addItem("CASH");
		    comboBox_1.addItem("OMANI RIYAL");
		    comboBox_1.addItem("USD");
		    comboBox_1.addItem("SAUDI RIYAL");
		    comboBox_1.setBounds(158, 276, 119, 29);
		    frame.getContentPane().add(comboBox_1);
		    
		    
		    JLabel lblPoNumber = new JLabel("PO COUNT :");
		    if(language.equals("Arabic"))
			{
		    	lblPoNumber.setText("⁄œœ √Ê«„— «·‘—«¡ :");
			}
		    lblPoNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		    lblPoNumber.setBounds(458, 243, 66, 21);
		    frame.getContentPane().add(lblPoNumber);
		    
		    
		  	dateChooser = new JDateChooser();
		    dateChooser.setBounds(307, 276, 135, 30);
		    frame.getContentPane().add(dateChooser);
		    dateChooser.setDateFormatString("yyyy-MM-dd");
		    dateChooser.setVisible(false);
		    
		    
		    comboBox = new JComboBox<String>();
		    comboBox.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(!comboBox.getSelectedItem().toString().equals("CASH"))
		    		{
		    			comboBox_1.setEnabled(false);
		    			comboBox_2.setEnabled(true);
		    			textField_4.setEnabled(true);
		    			chckbxAcPayee.setEnabled(true);
		    			chckbxNewCheckBox.setEnabled(true);
		    			dateChooser.setVisible(true);
		    			lblDate.setText("Cheque Date :");
		    			if(language.equals("Arabic"))
		    			{
		    				lblDate.setText(" «—ÌŒ «· Õﬁﬁ :");
		    			}
		    		}
		    		else
		    		{
		    			comboBox_1.setEnabled(true);
		    			comboBox_2.setSelectedIndex(-1);
		    			comboBox_2.setEnabled(false);
		    			textField_4.setEnabled(false);
		    			chckbxAcPayee.setEnabled(false);
		    			chckbxNewCheckBox.setEnabled(false);
		    			lblDate.setText("Date :");
		    			if(language.equals("Arabic"))
		    			{
		    				lblDate.setText(" «—ÌŒ :");
		    			}
		    			dateChooser.setVisible(true);
		    		}
		    	}
		    });
		    comboBox.addItem("CASH");
		    comboBox.addItem("BANK");
		    comboBox.setBounds(21, 277, 113, 29);
		    frame.getContentPane().add(comboBox);
		    
		    
		    textField_8 = new JTextField();
		    textField_8.setEditable(false);
		    textField_8.setBounds(464, 275, 60, 30);
		    frame.getContentPane().add(textField_8);
		    textField_8.setColumns(10);
		    
		   
		    
		    
			JLabel lblPoAmount = new JLabel("PO Amount");
			if(language.equals("Arabic"))
			{
				lblPoAmount.setText("„»·€ √„— «·‘—«¡");
			}
			lblPoAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblPoAmount.setBounds(21, 68, 116, 16);
			frame.getContentPane().add(lblPoAmount);
			
			textField_3 = new JTextField(Double.toString(balance));
			textField_3.setEditable(false);
			textField_3.setBounds(21, 90, 116, 37);
			frame.getContentPane().add(textField_3);
			textField_3.setColumns(10);
			
			JLabel lblPrAmount = new JLabel("PR Amount");
			if(language.equals("Arabic"))
			{
				lblPrAmount.setText("„»·€ «·⁄·«ﬁ«  «·⁄«„…");
			}
			lblPrAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblPrAmount.setBounds(189, 68, 116, 16);
			frame.getContentPane().add(lblPrAmount);
			
//			double amt=0;
//			try{
//				Connection con=Dbsetup.dbconn.getConnection();
//				Statement statement=con.createStatement();  
//				String query="SELECT * FROM "+company+".mr_vendor_payment where ref_vendor_id='"+vendor_id+"' and status='2' "; 
//				ResultSet resultSet = statement.executeQuery(query);
//				
//				while(resultSet.next())
//				{
//					String paid=resultSet.getString("invo_amt");
//					amt+=Float.parseFloat(paid);
//					y=y+1;
//				}
//				con.close();
//			}
//			catch(Exception e){
//				System.err.println(e);
//			}
//			
//			BigDecimal bd = new BigDecimal(amt);
//    		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
//    		amt = bd.doubleValue();
			
			textField_9 = new JTextField(Double.toString(amt_rtn));
			textField_9.setEditable(false);
			textField_9.setBounds(189, 90, 116, 37);
			frame.getContentPane().add(textField_9);
			textField_9.setColumns(10);
			
			JLabel label = new JLabel("-");
			label.setFont(new Font("Tahoma", Font.BOLD, 25));
			label.setBounds(158, 100, 23, 16);
			frame.getContentPane().add(label);
			
			JLabel label_1 = new JLabel("=");
			label_1.setFont(new Font("Tahoma", Font.BOLD, 25));
			label_1.setBounds(317, 92, 35, 35);
			frame.getContentPane().add(label_1);
			
			double bal = balance-amt_rtn; 
			BigDecimal bq = new BigDecimal(bal);
    		bq = bq.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
    		bal = bq.doubleValue();
			textField = new JTextField(Double.toString(bal));
			textField.setEditable(false);
		    textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		    textField.setBackground(new Color(255, 102, 153));
		    textField.setBounds(354, 90, 116, 37);
		    frame.getContentPane().add(textField);
		    textField.setColumns(10);
		    
		    JLabel lblDiscount = new JLabel("Discount");
		    if(language.equals("Arabic"))
			{
		    	lblDiscount.setText(" Œ›Ì÷");
			}
		    lblDiscount.setFont(new Font("Tahoma", Font.BOLD, 12));
		    lblDiscount.setBounds(193, 140, 56, 16);
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
		    });
		    textField_7.addFocusListener(new FocusAdapter() {
		    	@Override
		    	public void focusLost(FocusEvent arg0) {
		    		if(x>1)
		    		{
		    			String enter_amt=textField_1.getText();
		    			if(enter_amt.equals(""))
		    			{
		    				JOptionPane.showMessageDialog(frame, "<html>Please Enter the Amount<br>«·—Ã«¡ ≈œŒ«· «·„»·€</html>");
		    				textField_1.requestFocus();
		    			}
		    			else
		    			{
		    				String disc=textField_7.getText();
		    				if(disc.equals(""))
		    				{
		    					disc="0";
		    				}
		    				double net_amt=(Double.parseDouble(enter_amt)+Double.parseDouble(disc));
		    				BigDecimal bd = new BigDecimal(net_amt);
	                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
	                		net_amt = bd.doubleValue();
		    				textField_10.setText(Double.toString(net_amt));
		    				String net_ttl=textField_10.getText();
		    				if(Double.parseDouble(net_ttl)>Double.parseDouble(textField.getText()))
							{
								JOptionPane.showMessageDialog(frame, "<html>Enter Amount Greater Than Balance Amount<br>√œŒ· «·„»·€ «·√ﬂ»— „‰ „»·€ «·—’Ìœ</html>");
								textField_1.requestFocus();
							}
		    			}
		    			
		    		}
		    		else
		    		{
		    			String enter_amt=textField_1.getText();
		    			if(enter_amt.equals(""))
		    			{
		    				JOptionPane.showMessageDialog(frame, "<html>Please Enter the Amount<br><«·—Ã«¡ ≈œŒ«· «·„»·€/html>");
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
			    					JOptionPane.showMessageDialog(frame, "<html>Enter Amount Greater Than Balance Amount<br>√œŒ· «·„»·€ «·√ﬂ»— „‰ „»·€ «·—’Ìœ</html>");
									textField_1.requestFocus();
								}
			    				else
				    			{
				    				String disc=textField_7.getText();
				    				if(disc.equals(""))
				    				{
				    					disc="0";
				    				}
				    				double net_amt=(Double.parseDouble(enter_amt)+Double.parseDouble(disc));
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
		    textField_7.setBounds(184, 163, 108, 37);
		    frame.getContentPane().add(textField_7);
		    textField_7.setColumns(10);
		    
		    textArea = new JTextArea();
		    frame.getContentPane().add(textArea);
		    textArea.setBounds(131, 458, 393, 65);
		    
		    
		    
		    ScrollPane scrollPane = new ScrollPane();
		    scrollPane.setBounds(127, 458, 397, 69);
		    frame.getContentPane().add(scrollPane);
		    
		    JLabel label_2 = new JLabel("+");
		    label_2.setHorizontalAlignment(SwingConstants.CENTER);
		    label_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		    label_2.setBounds(158, 173, 23, 16);
		    frame.getContentPane().add(label_2);
		    
		    JLabel label_3 = new JLabel("=");
		    label_3.setFont(new Font("Tahoma", Font.BOLD, 25));
		    label_3.setBounds(307, 163, 35, 35);
		    frame.getContentPane().add(label_3);
		    
		    textField_10 = new JTextField();
		    textField_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		    textField_10.setText("0.0");
		    textField_10.setEditable(false);
		    textField_10.setBounds(354, 163, 116, 37);
		    frame.getContentPane().add(textField_10);
		    textField_10.setBackground(Color.GREEN);
		    textField_10.setColumns(10);
		    
		    JLabel lblNetAmount = new JLabel("Net Amount:");
		    if(language.equals("Arabic"))
			{
		    	lblNetAmount.setText("«·„»·€ «·’«›Ì:");
			}
		    lblNetAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
		    lblNetAmount.setBounds(354, 140, 108, 21);
		    frame.getContentPane().add(lblNetAmount);
		    
		   
		    
		    for(int i=0;i<item_list.length;i++)
			{
		    	if(item_list[i]!=null)
				{
		    		String[] po_list = item_list[i].toString().split("-");
		    		if(po_list[0].equals("PR"))
		    		{
		    			
		    		}
		    		else
		    		{
		    			x=x+1;
		    		}
		    		
		    		textArea.append(item_list[i].toString()+",");
		    		textField_8.setText(Integer.toString(x));
		    	}
			}
		    String bal_amt=textField.getText();
			if(Double.parseDouble(bal_amt)<0)
			{
				textField_1.setEditable(false);
				menuItem.setEnabled(false);
			}
			
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 544, 575);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
