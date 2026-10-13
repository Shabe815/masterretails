package accounts_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ap_voucher_list {

	public static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	private JTextField textField;
	private static JButton btnProceed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					ap_voucher_list window = new ap_voucher_list("mr_pro","admin","S01", null,"2","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param language 
	 */
	public ap_voucher_list(String db_name, String username,String store,String version,String decimal, String language) throws JSONException {
		initialize(db_name,username,store, version,decimal,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param decimal 
	 * @param language 
	 * @param page 
	 */
	private void initialize(final String db_name,final String username,final String store,final String version, final String decimal,final String language) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 1023, 748);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    	//CafePro.Mainpage.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	CafePro.Mainpage.btnPurchaseReturn.setEnabled(true);
		    	//CafePro.Mainpage.btnNewButton_1.setEnabled(true);
		    }
		});
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 1017, 96);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image printer = new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		JLabel lblItemMasterList = new JLabel("Vendor Payment List");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(10, 11, 199, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				CafePro.Mainpage.btnPurchaseReturn.setEnabled(true);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(857, 11, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name, "OPEN", "");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(927, 11, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		btnProceed = new JButton("Post Voucher");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int row=table.getSelectedRow();
					if(row>-1)
					{
						frame.setAlwaysOnTop(false);
						int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Post This Voucher","Warning",JOptionPane.YES_NO_OPTION);
				        if(cofrm_bx==0)
				        {
				        	frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			        	
				        	String vendor_id=table.getValueAt(row, 2).toString();
				        	String ap_num=table.getValueAt(row, 1).toString();
				        	
				        	LocalDateTime myDateObj = LocalDateTime.now();  
						    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
						    String formattedDate = myDateObj.format(myFormatObj);
						    final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
						    Calendar cal = Calendar.getInstance();
					        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				        	Connection con=Dbsetup.dbconn.getConnection();
				        	Statement statement22 = con.createStatement();
				        	Statement statement23 = con.createStatement();
				        	boolean sts=false;
				        	String apvoucher1="select * from "+db_name+".mr_vendor_payment_details  where trans_num='"+ap_num+"' and mr_vendor_payment_details.ref_vendor_id='"+vendor_id+"'  ";
				        	ResultSet resultSet53 = statement23.executeQuery(apvoucher1);
							if(resultSet53.next())
							{
								if(resultSet53.getString("payment_method").equals("BANK"))
								{
									String query111="SELECT * FROM "+db_name+".mr_cheque_details where tran_num='"+ap_num+"' and cheque_posted='1' ";
									ResultSet resultSet555 = statement22.executeQuery(query111);
									if(resultSet555.next())
									{
										sts=true;
									}
									else
									{
										JOptionPane.showMessageDialog(frame, "Cheque Not Posted, Post the Cheque and try again");
										frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
									}
								}
								else
								{
									sts=true;
								}
							}
				        	if(sts==true)
				        	{
				        		String apvoucher="select * from "+db_name+".mr_vendor_payment inner join "+db_name+".mr_vendor_payment_details on mr_vendor_payment.ref_po_num=mr_vendor_payment_details.ref_po_num and mr_vendor_payment.ref_vendor_id=mr_vendor_payment_details.ref_vendor_id where trans_num='"+ap_num+"' and mr_vendor_payment_details.ref_vendor_id='"+vendor_id+"'  ";
					        	Statement statement = con.createStatement();
						        Statement statement1 = con.createStatement();
						        ResultSet resultSet = statement.executeQuery(apvoucher);
								while(resultSet.next())
							    {
									String dr_amt=resultSet.getString("dr_amt");
									String disc_amt=resultSet.getString("disc_amt");
									String payment_mode=resultSet.getString("payment_method");
									String invo_num=resultSet.getString("invo_num");
									String po_num=resultSet.getString("ref_po_num");
									//String paid_amt=resultSet.getString("paid_amt");
									String bank_name=resultSet.getString("bank_name");		
									String branch=resultSet.getString("branch");
									double new_ttl=Double.parseDouble(dr_amt)+Double.parseDouble(disc_amt);
									double new_paid=Double.parseDouble(dr_amt);
									
									String vendor_payable_ac="";
									String select_vendor="select * from "+db_name+".mr_vendor where vendor_code='"+vendor_id+"' ";
									ResultSet resultSet1 = statement1.executeQuery(select_vendor);
									if(resultSet1.next())
									{
										vendor_payable_ac=resultSet1.getString("vendor_payable_ac");
										
									}
									
									String cash_acc="";
									String acc_name="";
					                String acc_code="";
									if(payment_mode.equals("CASH"))
									{
										String query15a="SELECT * FROM "+db_name+".mr_store_accounts where ref_store_id='"+store+"'"; 
										Statement statement5a=con.createStatement();  
						                ResultSet resultSet5a = statement5a.executeQuery(query15a);
						                if(resultSet5a.next())
						                {
						                	cash_acc=resultSet5a.getString("cash_acc");
						                }
										
										
										String query15="SELECT * FROM "+db_name+".mr_chart_accnt_mstr where acc_code='"+cash_acc+"' and acc_store='"+store+"'"; 
										Statement statement5=con.createStatement();  
						                ResultSet resultSet5 = statement5.executeQuery(query15);
						                if(resultSet5.next())
						                {
						                	acc_name=resultSet5.getString("acc_name");
						                	acc_code=resultSet5.getString("acc_code");
						                }
									}
									else
									{
										String default_ac="";
										String query15a="SELECT * FROM "+db_name+".mr_acc_paycode where store_id='"+store+"' and paycode_name='"+bank_name+"'"; 
										Statement statement5a=con.createStatement();  
						                ResultSet resultSet5a = statement5a.executeQuery(query15a);
						                if(resultSet5a.next())
						                {
						                	default_ac=resultSet5a.getString("default_ac");
						                }
										
										
										String query15="SELECT * FROM "+db_name+".mr_chart_accnt_mstr where acc_code='"+default_ac+"' and acc_store='"+store+"'"; 
										Statement statement5=con.createStatement();  
						                ResultSet resultSet5 = statement5.executeQuery(query15);
						                
						                if(resultSet5.next())
						                {
						                	acc_name=resultSet5.getString("acc_name");
						                	acc_code=resultSet5.getString("acc_code");
						                }
						                
						                
									}
									Statement statement5=con.createStatement();  
									String def_payable=vendor_payable_ac;
//					                String query15="SELECT * FROM "+db_name+".mr_assign_account_group where group_code='"+vendor_payable_ac+"' and acc_store='"+store+"' and status='1' "; 
//									
//					                ResultSet resultSet5 = statement5.executeQuery(query15);
//					                if(resultSet5.next())
//					                {
//					                	def_payable=resultSet5.getString("def_payable");
//					                }
									
					                String query16="SELECT * FROM "+db_name+".mr_chart_accnt_mstr where acc_code='"+def_payable+"' and acc_store='"+store+"'"; 
									Statement statement6=con.createStatement();  
					                ResultSet resultSet6= statement6.executeQuery(query16);
					                String acc_pay="";
					                if(resultSet6.next())
					                {
					                	acc_pay=resultSet6.getString("acc_name");
					                }
									
									String seqnum=null;
									String trans_seq = "select tran_seq from "+db_name+".mr_transaction_seq where tran_orgin='JV' ";
									ResultSet resultSet10 = statement1.executeQuery(trans_seq);
								    if (resultSet10.next())
								    {
								    	seqnum=resultSet10.getString("tran_seq");
								    	String[] seq=seqnum.split("-");
								    	DateFormat df = new SimpleDateFormat("yy");
								    	String formatdDate = df.format(Calendar.getInstance().getTime());
								    	if(!seq[1].equals(formattedDate))
								    	{
								    		seq[1]=formatdDate;
								    	}
								    	seqnum=seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+seq[3];
								    	String sqnum=Integer.toString(Integer.parseInt(seq[3])+1);
								    	StringBuilder sb = new StringBuilder();
										for (int toPrepend=6-sqnum.length(); toPrepend>0; toPrepend--) {
										    sb.append('0');
										}
										sb.append(sqnum);
								    	String save_po="update "+db_name+".mr_transaction_seq set modified_date='"+formattedDate+"',tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_orgin='JV' ";
								    	statement1.executeUpdate(save_po);
								    }
								    
								    String disc_applied="";
					                String disc_jv="";
					                String disc_leger="";
								    
					                String query17="SELECT * FROM "+db_name+".mr_acc_ledger where ac_code='"+acc_code+"'"; 
									Statement statement7=con.createStatement();  
					                ResultSet resultSet7= statement7.executeQuery(query17);
					                float cr=0;
					                float dr=0;
					                while(resultSet7.next())
					                {
					                	cr+=Float.parseFloat(resultSet7.getString("cr_amt"));
					                	dr+=Float.parseFloat(resultSet7.getString("dr_amt"));
					                }
									float bal_asset=dr-cr;
									double balac=bal_asset-new_paid;
									BigDecimal bc = new BigDecimal(balac);
			                		bc = bc.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
			                		balac = bc.doubleValue();
									
									String query18="SELECT * FROM "+db_name+".mr_acc_ledger where ac_code='"+def_payable+"'"; 
									Statement statement8=con.createStatement();  
					                ResultSet resultSet8= statement8.executeQuery(query18);
					                float cr_pay=0;
					                float dr_pay=0;
					                while(resultSet8.next())
					                {
					                	cr_pay+=Float.parseFloat(resultSet8.getString("cr_amt"));
					                	dr_pay+=Float.parseFloat(resultSet8.getString("dr_amt"));
					                }
									float bal_liable=dr_pay-cr_pay;
									double bal_lia=bal_liable+new_paid;
									BigDecimal be = new BigDecimal(bal_lia);
			                		be = be.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
			                		bal_lia = be.doubleValue();
			                		
			                		if(Double.parseDouble(disc_amt)!=0)
								    {
								    	
						                
						                String query118="SELECT * FROM "+db_name+".mr_chart_accnt_mstr where acc_grp_name='DISCOUNT ON PURCHASE' and acc_store='"+store+"'"; 
										Statement statement18=con.createStatement();  
						                ResultSet resultSet18= statement18.executeQuery(query118);
						                String disc_name="";
						                String disc_code="";
						                if(resultSet18.next())
						                {
						                	disc_name=resultSet18.getString("acc_name");
						                	disc_code=resultSet18.getString("acc_code");
						                }
						                disc_applied=",('"+seqnum+"','"+formattedDate+"','"+times.format(cal.getTime())+"','EXPENSE','"+disc_code+"','"+disc_name+"','0','"+disc_amt+"','DISCOUNT APPLIED ON VENDOR PAYMENT ','"+formattedDate+"','"+formattedDate+"')";
						                disc_jv=",('"+seqnum+"','"+seqnum+"','"+disc_code+"','"+disc_name+"','0','"+disc_amt+"','"+po_num+"','"+ap_num+"','"+formattedDate+"','"+formattedDate+"','POSTED','"+formattedDate+"')";
						                
						                String query29="SELECT * FROM "+db_name+".mr_acc_ledger where ac_code='"+disc_code+"'"; 
										Statement statement9=con.createStatement();  
						                ResultSet resultSet9= statement9.executeQuery(query29);
						                float cr_disc=0;
						                float dr_disc=0;
						                while(resultSet9.next())
						                {
						                	cr_disc+=Float.parseFloat(resultSet9.getString("cr_amt"));
						                	dr_disc+=Float.parseFloat(resultSet9.getString("dr_amt"));
						                }
										float bal_disc=dr_disc-cr_disc;
										double bal_discount=bal_disc-Double.parseDouble(disc_amt);
										BigDecimal bb = new BigDecimal(bal_discount);
				                		bb = bb.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
				                		bal_discount = bb.doubleValue();
				                		
				                		//double new_disc_bal=bal_lia+Double.parseDouble(disc_amt);
				                		
										disc_leger=",('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+disc_code+"','0','"+disc_amt+"','"+bal_discount+"','"+po_num+"','"+ap_num+"','AP','"+formattedDate+"','"+formattedDate+"','POSTED' )";
						                
								    }
			                		
			                		String insert_acc_header="insert into "+db_name+".mr_accounts_trans_header (tran_seqencecode,tran_date,tran_time,tran_remark,tran_debit_amt,tran_credit_amt,tran_status,tran_user,tran_store,tran_orgin,created_date,modified_date) values('"+po_num+"','"+formattedDate+"','"+sdf.format(cal.getTime())+"','SUP.INVOICE NO. "+invo_num+"','"+new_ttl+"','"+new_ttl+"','POSTED','"+username+"','"+store+"','AP','"+formattedDate+"','"+formattedDate+"') ";
									statement1.executeUpdate(insert_acc_header);
									
			                		String insert_acc_detls="insert into "+db_name+".mr_accounts_tran_detls (tran_seqence_code,tran_date,tran_time,tran_type,tran_accode,tran_acname,tran_debit_amt,tran_credit_amt,tran_remark,created_date,modified_date) values('"+po_num+"','"+formattedDate+"','"+sdf.format(cal.getTime())+"','ASSET','"+acc_code+"','"+acc_name+"','0','"+new_ttl+"','SUP.INVOICE NO. "+invo_num+"','"+formattedDate+"','"+formattedDate+"'),('"+po_num+"','"+formattedDate+"','"+sdf.format(cal.getTime())+"','LIABILITY','"+def_payable+"','"+acc_pay+"','"+new_ttl+"','0','SUP.INVOICE NO. "+invo_num+"','"+formattedDate+"','"+formattedDate+"')  "+disc_applied+" ";
			                		statement1.executeUpdate(insert_acc_detls);
								    
								    String save_po="insert into "+db_name+".mr_jv_header( `jv_num`, `date`, `branch`, `username`, `doc_num`, `remark`, `notes`, `store_id`, `created_date`, `modified_date`, `status`,`is_approved`, `approved_by`, `is_posted`, `posted_by`, `posted_date`, `approved_date`,jv_amt,vendor_cust_id) "
								    		+ "values('"+seqnum+"','"+formattedDate+"','"+branch+"','"+username+"','"+invo_num+"','"+ap_num+"','"+po_num+"','"+store+"','"+formattedDate+"','"+formattedDate+"','POSTED','1','"+username+"','1','"+username+"','"+formattedDate+"','"+formattedDate+"','"+new_ttl+"','"+vendor_id+"' )";
								    statement1.executeUpdate(save_po);
									
									String query1="insert into "+db_name+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,cheque_date) "
											+ "VALUES ('"+seqnum+"','"+seqnum+"','"+acc_code+"','"+acc_name+"','0','"+new_paid+"','"+po_num+"','"+invo_num+"','"+formattedDate+"','"+formattedDate+"','POSTED','"+formattedDate+"'),"
													+ "('"+seqnum+"','"+seqnum+"','"+def_payable+"','"+acc_pay+"','"+new_ttl+"','0','"+po_num+"','"+invo_num+"','"+formattedDate+"','"+formattedDate+"','POSTED','"+formattedDate+"') "
															+ ""+disc_jv+" ";
									statement1.executeUpdate(query1);
									
									String save_ledger="insert into "+db_name+".mr_acc_ledger (`jv_num`, `tran_date`, `post_date`, `ac_code`, `dr_amt`, `cr_amt`, `balance`, `notes`, `remark`, `source`, `created_date`, `modified_date`,status) values ('"+ap_num+"','"+formattedDate+"','"+formattedDate+"','"+acc_code+"','0','"+new_paid+"','"+balac+"','"+po_num+"','"+invo_num+"','AP','"+formattedDate+"','"+formattedDate+"','POSTED' ),('"+ap_num+"','"+formattedDate+"','"+formattedDate+"','"+def_payable+"','"+new_paid+"','0','"+bal_lia+"','"+po_num+"','"+invo_num+"','AP','"+formattedDate+"','"+formattedDate+"','POSTED' ) "+disc_leger+" ";
									statement1.executeUpdate(save_ledger);
									
									String query="SELECT * FROM "+db_name+".mr_vendor_payment where ref_po_num='"+po_num+"' and status='1' "; 
									ResultSet resultSet123 = statement5.executeQuery(query);
									while(resultSet123.next())
									{
										String updated="update "+db_name+".mr_vendor_payment set status='3' where ref_po_num = '"+po_num+"' and invo_amt='"+resultSet123.getString("paid_amt")+"' ";
										statement1.executeUpdate(updated);
									}
									
									String update_payment="update "+db_name+".mr_vendor_payment_details set status='POSTED' where trans_num='"+ap_num+"'";
									statement1.executeUpdate(update_payment);
									
									String update_ap="update "+db_name+".mr_ap_vouchers set status='POSTED' where trans_num='"+ap_num+"'";
									statement1.executeUpdate(update_ap);
									
									
								}
								JOptionPane.showMessageDialog(frame, "Voucher posted Successfully");
								get_po_disp( db_name,"OPEN",textField.getText());
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				        	}
				        }
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Select A Row from Table");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, e1.getMessage());
				}
			}
		});
		btnProceed.setBounds(645, 23, 132, 25);
		panel.add(btnProceed);
		
		JButton button = new JButton("New Voucher");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Accountpayable(db_name, username, store,version,decimal, language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setBounds(503, 23, 132, 25);
		panel.add(button);
		
		JButton button_1 = new JButton("Void Voucher");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(frame, "Select A Row");
				}
				else
				{
					int a= JOptionPane.showConfirmDialog(frame, "Do You Want to Void This Voucher","Warning",JOptionPane.YES_NO_OPTION);
					if(a==0)
					{
						try{
							int countPO=0;
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();
							Statement statement1=con.createStatement();
							String querytest="SELECT count(*) as testingAP FROM "+db_name+".mr_vendor_payment_details where trans_num='"+model.getValueAt(row, 1).toString()+"'"; 
							ResultSet resultSettest = statement.executeQuery(querytest);
							if(resultSettest.next())
							{
								countPO=Integer.parseInt(resultSettest.getString("testingAP"));
							}
							
							
							String query="SELECT * FROM "+db_name+".mr_ap_vouchers where trans_num='"+model.getValueAt(row, 1).toString()+"'"; 
							ResultSet resultSet = statement.executeQuery(query);
							while(resultSet.next())
							{
								//String cr=resultSet.getString("cr_amt");
								String dr=resultSet.getString("dr_amt");
								String disc_amt=resultSet.getString("disc_amt");
								String vendor_id=resultSet.getString("ref_vendor_id");
										
								String update_ap="update "+db_name+".mr_ap_vouchers set status='VOID' where trans_num ='"+model.getValueAt(row, 1).toString()+"' ";
								statement1.executeUpdate(update_ap);
								String update_vendor="update "+db_name+".mr_vendor_payment_details set status='VOID' where trans_num ='"+model.getValueAt(row, 1).toString()+"' ";
								statement1.executeUpdate(update_vendor);
								if(resultSet.getString("payment_method").equals("BANK"))
								{
									String update_chque="update "+db_name+".mr_cheque_details set status='VOID' where tran_num ='"+model.getValueAt(row, 1).toString()+"' ";
									statement1.executeUpdate(update_chque);
								}
								
								String select_vendor="select * from "+db_name+".mr_vendor where vendor_code='"+vendor_id+"' ";
								ResultSet resultSet1 = statement.executeQuery(select_vendor);
								double new_paid=0;
								double new_pending=0;
								if(resultSet1.next())
								{
									String old_paid=resultSet1.getString("vendor_paid_amt");
									String old_pending=resultSet1.getString("vendor_pending_amt");
									new_paid=Double.parseDouble(old_paid)-(Double.parseDouble(dr)+Double.parseDouble(disc_amt));
									new_pending=Double.parseDouble(old_pending)+(Double.parseDouble(dr)+Double.parseDouble(disc_amt));
									
									BigDecimal bp = new BigDecimal(new_paid);
			                		bp = bp.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
			                		new_paid = bp.doubleValue();
			                		
			                		BigDecimal bl = new BigDecimal(new_pending);
			                		bl= bl.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
			                		new_pending = bl.doubleValue();
								}
								
								String updates="update "+db_name+".mr_vendor set vendor_pending_amt='"+new_pending+"',vendor_paid_amt='"+new_paid+"' where vendor_code = '"+vendor_id+"' ";
								statement1.executeUpdate(updates);
								
								String querys="SELECT * FROM "+db_name+".mr_vendor_payment_details  where trans_num ='"+model.getValueAt(row, 1).toString()+"' "; 
								ResultSet resultSet12 = statement.executeQuery(querys);
								while(resultSet12.next())
								{
									String po_num=resultSet12.getString("ref_po_num");
									String query11="SELECT * FROM "+db_name+".mr_vendor_payment  where ref_po_num ='"+po_num+"' "; 
									ResultSet resultSet11 = statement1.executeQuery(query11);
									if(resultSet11.next())
									{
										if(countPO>1)
										{
											String update="update "+db_name+".mr_vendor_payment set bal_amt=paid_amt,paid_amt='0' where ref_po_num='"+po_num+"' ";
											statement1.executeUpdate(update);
										}
										else
										{
											String paid=resultSet11.getString("paid_amt");
											String bal=resultSet11.getString("bal_amt");
											double new_paid_bal=Double.parseDouble(paid)-(Double.parseDouble(dr)+Double.parseDouble(disc_amt));
											double new_bal=Double.parseDouble(bal)+(Double.parseDouble(dr)+Double.parseDouble(disc_amt));
											BigDecimal bd = new BigDecimal(new_bal);
					                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
					                		new_bal = bd.doubleValue();
					                		String update="update "+db_name+".mr_vendor_payment set paid_amt='"+new_paid_bal+"',bal_amt='"+new_bal+"' where ref_po_num='"+po_num+"' ";
											statement1.executeUpdate(update);
										}
										
										
									}
								}
								JOptionPane.showMessageDialog(frame, "Void Successfully");
								get_po_disp( db_name,"OPEN",textField.getText());
							}
							
							
						}
						catch(Exception e){
							System.err.println(e);
						}
					}
				}
			}
		});
		button_1.setBounds(361, 23, 132, 25);
		panel.add(button_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Print");
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(787, 11, 60, 48);
		menuItem_2.setIcon(new ImageIcon(printer));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row>-1)
				{
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					int rows = table.getSelectedRow();
                	String inv_num = table.getModel().getValueAt(rows, 1).toString();
					try {
						Connection con=Dbsetup.dbconn.getConnection();
						report_master.Vendor_voucher.report(con,inv_num,db_name);
						con.close();
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
					JOptionPane.showMessageDialog(frame, "Select A Row from Table");
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		panel.add(menuItem_2);
		
		JButton btnChequeList = new JButton("Cheque List");
		btnChequeList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new accounts_master.cheque_list (db_name,version,username,store, language);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnChequeList.setBounds(219, 23, 132, 25);
		panel.add(btnChequeList);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 95, 1005, 605);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 88, 981, 504);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel_1.add(scrollPane);

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
		    
		    model.addColumn("Sl	 No.");
		    model.addColumn("Invoice No."); 
		    model.addColumn("Vendor Code");
		    model.addColumn("Vendor Name");
		    model.addColumn("Issue Date");
		    model.addColumn("Pay Type");
		    model.addColumn("Bank Name");
		    model.addColumn("Cheque No.");
		    model.addColumn("Cheque Date");
		    model.addColumn("Pay amount");
		    model.addColumn("Disc amount");
		    model.addColumn("Applied Credit");
		scrollPane.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(40);
			  if (i==1) column.setPreferredWidth(140);
			  if (i==2) column.setPreferredWidth(140);
			  if (i==3) column.setPreferredWidth(140);
			  if (i==4) column.setPreferredWidth(140);
			  if (i==5) column.setPreferredWidth(140);
			  if (i==6) column.setPreferredWidth(140);
			  if (i==7) column.setPreferredWidth(140);
			  if (i==8) column.setPreferredWidth(140);
			  if (i==9) column.setPreferredWidth(140);
			  if (i==10) column.setPreferredWidth(140);
			  if (i==11) column.setPreferredWidth(140);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 981, 52);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }

			  public void warn() {
				  get_po_disp( db_name,"OPEN",textField.getText());
			  }
			});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					get_po_disp( db_name,"OPEN",textField.getText());
				}
			}
		});
		textField.setBounds(12, 13, 564, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name,"OPEN",textField.getText());
			}
		});
		btnSearch.setBounds(588, 13, 97, 25);
		panel_2.add(btnSearch);
		
		JButton btnNewButton_1 = new JButton("AP Posted List");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new accounts_master.ap_posted_list(db_name,username,store, version,decimal, language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(826, 13, 143, 25);
		panel_2.add(btnNewButton_1);
		
		JButton btnVoidPoList = new JButton("Void AP List");
		btnVoidPoList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new accounts_master.ap_void_list(db_name,username,store, version,decimal, language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnVoidPoList.setBounds(697, 13, 117, 25);
		panel_2.add(btnVoidPoList);
		//comboBox.addItem("DELIVERY");
		 
//        table.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent mouseEvent) {
//                JTable table =(JTable) mouseEvent.getSource();
//                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
//                	int column = 0;
//                	int rows = table.getSelectedRow();
//                	String value = table.getModel().getValueAt(rows, column).toString();
//                	try{
//            			
//	 					new sales_distribution.sd_lines(db_name, username, table.getModel().getValueAt(rows, 2).toString(), value, store, version,decimal);
//	 					//sd_lines.frame.setAlwaysOnTop(true);
//                	}
//                	catch (JSONException | SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//                }
//                
//            }
//        });
        
        get_po_disp( db_name,"OPEN",textField.getText());
        
	}
	static void  get_po_disp( String company, String type, String search)
	{
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and (trans_num LIKE '%"+search+"%' OR ref_vendor_id LIKE '%"+search+"%' or pay_to like '%"+search+"%' )";
		}
		
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_ap_vouchers where status='"+type+"' "+serch_str+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+company+".mr_ap_vouchers where status='"+type+"' "+serch_str+" "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String invoice_num=resultSet1.getString("trans_num");
	                String vend_id=resultSet1.getString("ref_vendor_id");
					String vend_name=resultSet1.getString("pay_to");
					String issue_date=resultSet1.getString("created_date");
					String payment_method=resultSet1.getString("payment_method");
					String bank_name=resultSet1.getString("bank_name");
					String cheque_no=resultSet1.getString("cheque_no");
					String cheque_dt=resultSet1.getString("issue_date");
					String dr_amt=resultSet1.getString("dr_amt");
					String disc=resultSet1.getString("disc_amt");
					String cr_amt=resultSet1.getString("cr_amt");
	                model.addRow(new Object[]{model.getRowCount()+1,invoice_num,vend_id,vend_name,issue_date,payment_method,bank_name,cheque_no,cheque_dt,dr_amt,disc,cr_amt});
				}
	        	btnProceed.setEnabled(true);
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Voucher List Found");
	        	for (int i = model.getRowCount() - 1; i >= 0; i--) {
	        	    model.removeRow(i);
	        	}
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}