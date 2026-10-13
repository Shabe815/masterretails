package accounts_master;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;

public class cheque_list {

	public static JFrame frame;
	private static DefaultTableModel model;
	private static JTable table;
	private JTextField textField;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JButton btnVoidCheque;
	private JButton btnPrintCheque;
	private JButton btnHandoverCheque; 
	private JButton btnPostCheque;
	private JButton btnChequePrint;
	Connection con=Dbsetup.dbconn.getConnection();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					cheque_list window = new cheque_list("mr_pro","","","","");
					cheque_list.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param pagetype 
	 * @param store_name 
	 * @param con 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	public cheque_list(String db_name,String version,String username,String store,String language) throws JSONException, SQLException {
		initialize(db_name,version,username,store,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 * @param pagetype 
	 * @param store_name 
	 * @param decimal 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String db_name,final String version,final String username,final String store, final String language) throws JSONException, SQLException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 1024, 734);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				frame.setVisible(false);
			}
		});
		frame.getContentPane().setLayout(null);
		
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		//frame.setUndecorated(true);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 994, 60);
		frame.getContentPane().add(panel);
		
		Image doc = new ImageIcon(this.getClass().getResource("/doc.png")).getImage();
		
		JButton btnExportToCsv = new JButton("Export To CSV");
		if(language.equals("Arabic"))
		{
			btnExportToCsv.setText(" ’œÌ— ≈·Ï CSV");
		}
		btnExportToCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					export_as_csv();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		});
		btnExportToCsv.setBounds(767, 15, 157, 33);
		btnExportToCsv.setIcon(new ImageIcon(doc));
		panel.add(btnExportToCsv);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Cheque List");
		if(language.equals("Arabic"))
		{
			lblItemMasterList.setText("ﬁ«∆„… «· Õﬁﬁ");
		}
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(1, 0, 124, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(925, 15, 60, 33);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		btnVoidCheque = new JButton("VOID CHEQUE");
		if(language.equals("Arabic"))
		{
			btnVoidCheque.setText("‘Ìﬂ »«ÿ·");
		}
		btnVoidCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int row=table.getSelectedRow();
					if(row>-1)
					{
						int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to VOID This Cheque  Â·  —Ìœ ≈·€«¡ Â–« «·‘Ìﬂø","Warning",JOptionPane.YES_NO_OPTION);
				        if(cofrm_bx==0)
				        {
				        	frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				        	Statement st=con.createStatement();
							String query1="SELECT * FROM "+db_name+".mr_cheque_details where tran_num='"+table.getModel().getValueAt(row, 2).toString()+"' ";
							ResultSet resultSet111 = st.executeQuery(query1);
							if(resultSet111.next())
							{
								String pagetype=resultSet111.getString("page_type");
								String update_query=" update "+db_name+".mr_cheque_details set status='VOID' where tran_num='"+table.getModel().getValueAt(row, 2).toString()+"' ";
								st.executeUpdate(update_query);
								
								if(pagetype.equals("AP"))
								{
									int countPO=0;
									Connection con=Dbsetup.dbconn.getConnection();
									Statement statement=con.createStatement();
									Statement statement1=con.createStatement();
									String querytest="SELECT count(*) as testingAP FROM "+db_name+".mr_vendor_payment_details where trans_num='"+table.getModel().getValueAt(row, 2).toString()+"'"; 
									ResultSet resultSettest = statement.executeQuery(querytest);
									if(resultSettest.next())
									{
										countPO=Integer.parseInt(resultSettest.getString("testingAP"));
									}
									
									
									String query="SELECT * FROM "+db_name+".mr_ap_vouchers where trans_num='"+table.getModel().getValueAt(row, 2).toString()+"'"; 
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										//String cr=resultSet.getString("cr_amt");
										String dr=resultSet.getString("dr_amt");
										String disc_amt=resultSet.getString("disc_amt");
										String vendor_id=resultSet.getString("ref_vendor_id");
												
										String update_ap="update "+db_name+".mr_ap_vouchers set status='VOID' where trans_num ='"+table.getModel().getValueAt(row, 2).toString()+"' ";
										statement1.executeUpdate(update_ap);
										String update_vendor="update "+db_name+".mr_vendor_payment_details set status='VOID' where trans_num ='"+table.getModel().getValueAt(row, 2).toString()+"' ";
										statement1.executeUpdate(update_vendor);
										
										
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
					                		bp = bp.setScale(2, BigDecimal.ROUND_HALF_UP);
					                		new_paid = bp.doubleValue();
					                		
					                		BigDecimal bl = new BigDecimal(new_pending);
					                		bl= bl.setScale(2, BigDecimal.ROUND_HALF_UP);
					                		new_pending = bl.doubleValue();
										}
										
										String updates="update "+db_name+".mr_vendor set vendor_pending_amt='"+new_pending+"',vendor_paid_amt='"+new_paid+"' where vendor_code = '"+vendor_id+"' ";
										statement1.executeUpdate(updates);
										
										String querys="SELECT * FROM "+db_name+".mr_vendor_payment_details  where trans_num ='"+table.getModel().getValueAt(row, 2).toString()+"' "; 
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
							                		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
							                		new_bal = bd.doubleValue();
							                		String update="update "+db_name+".mr_vendor_payment set paid_amt='"+new_paid_bal+"',bal_amt='"+new_bal+"' where ref_po_num='"+po_num+"' ";
													statement1.executeUpdate(update);
												}
												
												
											}
										}
									}
								}
								if(pagetype.equals("AR"))
								{
									Statement statement=con.createStatement();
									Statement statement1=con.createStatement();
									String query="SELECT * FROM "+db_name+".mr_sd_customer_payment_details where seq_num='"+table.getModel().getValueAt(row, 2).toString()+"'"; 
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										String cr=resultSet.getString("cr_amt");
										String dr=resultSet.getString("dr_amt");
										String disc_amt=resultSet.getString("disc_amt");
										String vendor_id=resultSet.getString("ref_cust_id");
										String po_num=resultSet.getString("ref_tran_num");
										
										
										String select_vendor="select * from "+db_name+".mr_customer_master where cust_code='"+vendor_id+"' ";
										ResultSet resultSet1 = statement1.executeQuery(select_vendor);
										double new_paid=0;
										if(resultSet1.next())
										{
											String avail_credit=resultSet1.getString("avail_credit");
											new_paid=Double.parseDouble(avail_credit)-(Double.parseDouble(dr)+Double.parseDouble(disc_amt));
											BigDecimal bp = new BigDecimal(new_paid);
					                		bp = bp.setScale(2, BigDecimal.ROUND_HALF_UP);
					                		new_paid = bp.doubleValue();
											
										}
										
										String updates="update "+db_name+".mr_customer_master set avail_credit='"+new_paid+"' where cust_code = '"+vendor_id+"' ";
										statement1.executeUpdate(updates);
										
										
										
										
										String query11="SELECT * FROM "+db_name+".mr_sd_customer_payment  where ref_tran_num ='"+po_num+"' and ref_cust_id='"+vendor_id+"' "; 
										ResultSet resultSet11 = statement1.executeQuery(query11);
										if(resultSet11.next())
										{
											String paid=resultSet11.getString("paid_amt");
											String bal=resultSet11.getString("bal_amt");
											String ad_sts="";
											double new_paid_bal=0;
											double new_bal=0;
					                		if(resultSet11.getString("type").equals("AD"))
					                		{
					                			ad_sts=",status='1'";
					                			new_paid_bal=Double.parseDouble(paid)-(Double.parseDouble(cr)+Double.parseDouble(disc_amt));
					                			new_bal=Double.parseDouble(bal)+(Double.parseDouble(cr)+Double.parseDouble(disc_amt));
					                		}
					                		else
					                		{
					                			new_paid_bal=Double.parseDouble(paid)-(Double.parseDouble(dr)+Double.parseDouble(disc_amt));
					                			new_bal=Double.parseDouble(bal)+(Double.parseDouble(dr)+Double.parseDouble(disc_amt));
					                		}
											
											
											BigDecimal bd = new BigDecimal(new_bal);
					                		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
					                		new_bal = bd.doubleValue();
					                		
											String update="update "+db_name+".mr_sd_customer_payment set paid_amt='"+new_paid_bal+"',bal_amt='"+new_bal+"' "+ad_sts+" where ref_tran_num='"+po_num+"' and ref_cust_id='"+vendor_id+"'  ";
											statement1.executeUpdate(update);
										}
										
										String update_vendor="update "+db_name+".mr_sd_customer_payment_details set status='4' where seq_num ='"+table.getModel().getValueAt(row, 2).toString()+"' ";
										statement1.executeUpdate(update_vendor);
										
									}
								}
								if(pagetype.equals("JV"))
								{
									Statement statement1=con.createStatement();
									String query121="update "+db_name+".mr_jv_details set status='VOID' where ref_jv_num='"+table.getModel().getValueAt(row, 2).toString()+"' and bank_name='"+table.getValueAt(row, 4).toString()+"' and cheque_no='"+table.getValueAt(row, 5).toString()+"' ";
									statement1.executeUpdate(query121);
								}
								if(pagetype.equals("DN") || pagetype.equals("CN"))
								{
									Statement statement=con.createStatement();
									Statement statement1=con.createStatement();
									
									
									String query="SELECT * FROM "+db_name+".mr_vendor_payment_details where trans_num='"+table.getModel().getValueAt(row, 2).toString()+"'"; 
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										String dr="";
										if(pagetype.equals("Credit Note"))
										{
											dr=resultSet.getString("cr_amt");
										}
										else
										{
											dr=resultSet.getString("dr_amt");
										}
										//String cr=resultSet.getString("cr_amt");
										
										String disc_amt=resultSet.getString("disc_amt");
										String vendor_id=resultSet.getString("ref_vendor_id");
												
										String update_vendor="update "+db_name+".mr_vendor_payment_details set status='VOID' where trans_num ='"+model.getValueAt(row, 1).toString()+"' ";
										statement1.executeUpdate(update_vendor);
										
										
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
					                		bp = bp.setScale(2, BigDecimal.ROUND_HALF_UP);
					                		new_paid = bp.doubleValue();
					                		
					                		BigDecimal bl = new BigDecimal(new_pending);
					                		bl= bl.setScale(2, BigDecimal.ROUND_HALF_UP);
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
						                		String update="update "+db_name+".mr_vendor_payment set status='3' where ref_po_num='"+po_num+"' ";
												statement1.executeUpdate(update);
												
												
											}
										}
									}
								}
								
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								JOptionPane.showMessageDialog(frame, "Cheque Voided Successfully  „ ≈·€«¡ «·‘Ìﬂ »‰Ã«Õ");
								String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
								String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
								String search=textField.getText();
								get_itemdisp( db_name,  con,date1,date2,search);
								btnChequePrint.setEnabled(false);
			            		btnPrintCheque.setEnabled(false);
			            		btnHandoverCheque.setEnabled(false);
			            		btnPostCheque.setEnabled(false);
			            		btnVoidCheque.setEnabled(false);
							}
				        }
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Select A Row from Table Õœœ ’›« „‰ «·ÃœÊ·");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}
				catch(Exception e){
					System.err.println(e);
				}
				
			}
		});
		btnVoidCheque.setEnabled(false);
		btnVoidCheque.setBounds(126, 15, 124, 33);
		panel.add(btnVoidCheque);
		
		btnPrintCheque = new JButton("PRINT CHEQUE");
		if(language.equals("Arabic"))
		{
			btnPrintCheque.setText("ÿ»«⁄… «·‘Ìﬂ");
		}
		btnPrintCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int row=table.getSelectedRow();
					if(row>-1)
					{
						int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Already Printed This Cheque Â· ﬁ„  »ÿ»«⁄… Â–« «·‘Ìﬂ »«·›⁄·ø","Warning",JOptionPane.YES_NO_OPTION);
				        if(cofrm_bx==0)
				        {
				        	frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				        	Statement st=con.createStatement();
							String query1="SELECT * FROM "+db_name+".mr_cheque_details where tran_num='"+table.getModel().getValueAt(row, 2).toString()+"' and cheque_no='"+table.getModel().getValueAt(row, 5).toString()+"' and bank_name='"+table.getModel().getValueAt(row, 4).toString()+"' ";
							ResultSet resultSet = st.executeQuery(query1);
							if(resultSet.next())
							{
								String update_query=" update "+db_name+".mr_cheque_details set cheque_printed='1' where tran_num='"+table.getModel().getValueAt(row, 2).toString()+"' and cheque_no='"+table.getModel().getValueAt(row, 5).toString()+"' and bank_name='"+table.getModel().getValueAt(row, 4).toString()+"' ";
								st.executeUpdate(update_query);
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								JOptionPane.showMessageDialog(frame, "Cheque Printed Successfully  „  ÿ»«⁄… «·‘Ìﬂ »‰Ã«Õ");
								Connection con=Dbsetup.dbconn.getConnection();
								String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
								String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
								String search=textField.getText();
								get_itemdisp( db_name,  con,date1,date2,search);
								btnChequePrint.setEnabled(false);
			            		btnPrintCheque.setEnabled(false);
			            		btnHandoverCheque.setEnabled(false);
			            		btnPostCheque.setEnabled(false);
			            		btnVoidCheque.setEnabled(false);
							}
				        }
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Select A Row from Table Õœœ ’›« „‰ «·ÃœÊ·");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}
				catch(Exception e){
					System.err.println(e);
				}
			}
		});
		btnPrintCheque.setEnabled(false);
		btnPrintCheque.setBounds(371, 15, 119, 33);
		panel.add(btnPrintCheque);
		
		btnHandoverCheque = new JButton("HANDOVER CHEQUE");
		if(language.equals("Arabic"))
		{
			btnHandoverCheque.setText("‘Ìﬂ «· ”·Ì„");
		}
		btnHandoverCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int row=table.getSelectedRow();
					if(row>-1)
					{
						int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Handover This Cheque Â·  —Ìœ  ”·Ì„ Â–« «·‘Ìﬂø","Warning",JOptionPane.YES_NO_OPTION);
				        if(cofrm_bx==0)
				        {
				        	frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				        	Statement st=con.createStatement();
							String query1="SELECT * FROM "+db_name+".mr_cheque_details where tran_num='"+table.getModel().getValueAt(row, 2).toString()+"' and cheque_no='"+table.getModel().getValueAt(row, 5).toString()+"' and bank_name='"+table.getModel().getValueAt(row, 4).toString()+"' ";
							ResultSet resultSet = st.executeQuery(query1);
							if(resultSet.next())
							{
								String update_query=" update "+db_name+".mr_cheque_details set cheque_handover='1' where tran_num='"+table.getModel().getValueAt(row, 2).toString()+"' and cheque_no='"+table.getModel().getValueAt(row, 5).toString()+"' and bank_name='"+table.getModel().getValueAt(row, 4).toString()+"' ";
								st.executeUpdate(update_query);
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								JOptionPane.showMessageDialog(frame, "Cheque Handover Successfully  „  ”·Ì„ «·‘Ìﬂ »‰Ã«Õ");
								Connection con=Dbsetup.dbconn.getConnection(); 
								String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
								String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
								String search=textField.getText();
								get_itemdisp( db_name,  con,date1,date2,search);
								btnChequePrint.setEnabled(false);
			            		btnPrintCheque.setEnabled(false);
			            		btnHandoverCheque.setEnabled(false);
			            		btnPostCheque.setEnabled(false);
			            		btnVoidCheque.setEnabled(false);
							}
				        }
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Select A Row from Table Õœœ ’›« „‰ «·ÃœÊ·");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}
				catch(Exception e){
					System.err.println(e);
				}
				
			}
		});
		btnHandoverCheque.setEnabled(false);
		btnHandoverCheque.setBounds(491, 15, 153, 33);
		panel.add(btnHandoverCheque);
		
		btnPostCheque = new JButton("POST CHEQUE");
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
		btnPostCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int row=table.getSelectedRow();
					if(row>-1)
					{
						int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to POST This Cheque Â·  —Ìœ ≈—”«· Â–« «·‘Ìﬂø","Warning",JOptionPane.YES_NO_OPTION);
				        if(cofrm_bx==0)
				        {
				        	frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				        	Statement st=con.createStatement();
							String query1="SELECT * FROM "+db_name+".mr_cheque_details where tran_num='"+table.getModel().getValueAt(row, 2).toString()+"' and cheque_no='"+table.getModel().getValueAt(row, 5).toString()+"' and bank_name='"+table.getModel().getValueAt(row, 4).toString()+"' ";
							ResultSet resultSet = st.executeQuery(query1);
							if(resultSet.next())
							{
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("cheque_date"));
								Calendar cal = Calendar.getInstance();
								cal.setTime(date1);
								String newDate = sdf.format(cal.getTime());  
								Date exp_date=new SimpleDateFormat("yyyy-MM-dd").parse(newDate);
								Date date = new Date();  
								int day= exp_date.compareTo(date);
								if(day == -1)
								{
									String update_query=" update "+db_name+".mr_cheque_details set cheque_posted='1',status='POSTED' where tran_num='"+table.getModel().getValueAt(row, 2).toString()+"' and cheque_no='"+table.getModel().getValueAt(row, 5).toString()+"' and bank_name='"+table.getModel().getValueAt(row, 4).toString()+"' ";
									st.executeUpdate(update_query);
									frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
									JOptionPane.showMessageDialog(frame, "Cheque Posted Successfully");
									Connection con=Dbsetup.dbconn.getConnection();
									String date3=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
									String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
									String search=textField.getText();
									get_itemdisp( db_name,  con,date3,date2,search);
									btnChequePrint.setEnabled(false);
				            		btnPrintCheque.setEnabled(false);
				            		btnHandoverCheque.setEnabled(false);
				            		btnPostCheque.setEnabled(false);
				            		btnVoidCheque.setEnabled(false);
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "The cheque date is different from the current day  «—ÌŒ «·‘Ìﬂ „Œ ·› ⁄‰ «·ÌÊ„ «·Õ«·Ì");
									frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								}
								
							}
				        }
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Select A Row from Table Õœœ ’›« „‰ «·ÃœÊ·");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}
				catch(Exception e){
					System.err.println(e);
				}
			}
		});
		btnPostCheque.setEnabled(false);
		btnPostCheque.setBounds(645, 15, 121, 33);
		panel.add(btnPostCheque);
		
		btnChequePrint = new JButton("CHEQUE PRINT");
		if(language.equals("Arabic"))
		{
			btnChequePrint.setText("ÿ»«⁄… «·‘Ìﬂ« ");
		}
		btnChequePrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int row=table.getSelectedRow();
					if(row>-1)
					{
						frame.setAlwaysOnTop(false);
						String amt="";
						if(table.getModel().getValueAt(row, 8).toString().equals("0"))
						{
							amt=table.getModel().getValueAt(row, 7).toString();
						}
						else
						{
							amt=table.getModel().getValueAt(row, 8).toString();
						}
						new accounts_master.cheque_format(db_name,username,store, version,"2",table.getModel().getValueAt(row, 2).toString(),amt, language);
					}
					else
					{
						
						JOptionPane.showMessageDialog(frame, "Select A Row from Table Õœœ ’›« „‰ «·ÃœÊ·");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}
				catch(Exception e){
					System.err.println(e);
				}
			}
		});
		btnChequePrint.setEnabled(false);
		btnChequePrint.setBounds(251, 15, 119, 33);
		panel.add(btnChequePrint);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 994, 768);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 118, 970, 497);
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
		    if(language.equals("Arabic"))
			{
		    	model.addColumn("«·—ﬁ„ «· ”·”·Ì"); 
			    model.addColumn(" «—ÌŒ «·≈’œ«—"); 
			    model.addColumn("—ﬁ„ «·›« Ê—…");
			    model.addColumn("«”„ «·⁄„Ì·");
			    model.addColumn("«”„ «·»‰ﬂ");
			    model.addColumn("—ﬁ„ «·‘Ìﬂ"); 
			    model.addColumn("«· Õﬁﬁ „‰ «· «—ÌŒ"); 
			    model.addColumn("«∆ „«‰");
			    model.addColumn("œÛÌ‰");
			    model.addColumn("„ÿ»Ê⁄…");
			    model.addColumn("”·„ ");
			    model.addColumn("‰‘—");
			}
		    else
		    {
		    	model.addColumn("S.No"); 
			    model.addColumn("Issue Date"); 
			    model.addColumn("Invoice Number");
			    model.addColumn("Customer Name");
			    model.addColumn("Bank Name");
			    model.addColumn("Cheque No."); 
			    model.addColumn("Cheque Date"); 
			    model.addColumn("Cr");
			    model.addColumn("Dr");
			    model.addColumn("Printed");
			    model.addColumn("Handovered");
			    model.addColumn("Posted");
		    }
		    
		scrollPane.setViewportView(table);
		
		int columns=model.getColumnCount();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//JTableHeader header=table.getTableHeader();
		  
	    //header.setPreferredSize(new Dimension(0,50));
	    for (int i=0; i<columns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(40);
		  if (i==1) column.setPreferredWidth(130);
		  if (i==2) column.setPreferredWidth(280);
		  if (i==3) column.setPreferredWidth(100);
		  if (i==4) column.setPreferredWidth(100);
		  if (i==5) column.setPreferredWidth(100);
		  if (i==6) column.setPreferredWidth(100);
		  if (i==7) column.setPreferredWidth(100);
		  if (i==8) column.setPreferredWidth(100);
		  if (i==9) column.setPreferredWidth(100);
		  if (i==10) column.setPreferredWidth(100);
		}
	
		table.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent mouseEvent) {
	            JTable table =(JTable) mouseEvent.getSource();
	            if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
	            	int rows = table.getSelectedRow();
	            	String printed = table.getModel().getValueAt(rows, 9).toString();
	            	String handover = table.getModel().getValueAt(rows, 10).toString();
	            	String posted = table.getModel().getValueAt(rows, 11).toString();
	            	if(printed.equals("0") && handover.equals("0") && posted.equals("0"))
	            	{
	            		btnChequePrint.setEnabled(true);
	            		btnPrintCheque.setEnabled(true);
	            		btnHandoverCheque.setEnabled(false);
	            		btnPostCheque.setEnabled(false);
	            		btnVoidCheque.setEnabled(true);

	            	}
	            	else if(printed.equals("1") && handover.equals("0") && posted.equals("0"))
	            	{
	            		btnChequePrint.setEnabled(false);
	            		btnPrintCheque.setEnabled(false);
	            		btnHandoverCheque.setEnabled(true);
	            		btnPostCheque.setEnabled(false);
	            		btnVoidCheque.setEnabled(true);
	            	}
	            	else if(printed.equals("1") && handover.equals("1") && posted.equals("0"))
	            	{
	            		btnChequePrint.setEnabled(false);
	            		btnPrintCheque.setEnabled(false);
	            		btnHandoverCheque.setEnabled(false);
	            		btnPostCheque.setEnabled(true);
	            		btnVoidCheque.setEnabled(true);
	            	}
	            	else if(printed.equals("1") && handover.equals("1") && posted.equals("1"))
	            	{
	            		btnChequePrint.setEnabled(false);
	            		btnPrintCheque.setEnabled(false);
	            		btnHandoverCheque.setEnabled(false);
	            		btnPostCheque.setEnabled(false);
	            		btnVoidCheque.setEnabled(false);
	            	}
	            }
	            
	        }
	    });
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 970, 92);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblDateRange = new JLabel("Date Range");
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·‰ÿ«ﬁ «·“„‰Ì");
		}
		lblDateRange.setBounds(12, 16, 87, 22);
		panel_2.add(lblDateRange);
		
		textField = new JTextField();
		textField.setBounds(12, 54, 806, 22);
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
				  int rowCount = model.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					Connection con=Dbsetup.dbconn.getConnection();
					String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					String search=textField.getText();
					get_itemdisp( db_name,  con,date1,date2,search);
			  }
			});
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblFrom = new JLabel("From");
		if(language.equals("Arabic"))
		{
			lblFrom.setText("„‰");
		}
		lblFrom.setBounds(229, 16, 38, 22);
		panel_2.add(lblFrom);
		
		Date date = new Date();
		
		dateChooser = new JDateChooser();
		dateChooser = new JDateChooser(date);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(338, 16, 100, 22);
		panel_2.add(dateChooser);
		
		JLabel lblTo = new JLabel("To");
		if(language.equals("Arabic"))
		{
			lblTo.setText("·");
		}
		lblTo.setBounds(509, 16, 25, 22);
		panel_2.add(lblTo);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setDate(date);
		dateChooser_1.setBounds(605, 16, 100, 22);
		panel_2.add(dateChooser_1);
		
		JButton btnNewButton = new JButton("Search");
		if(language.equals("Arabic"))
		{
			btnNewButton.setText("Ì»ÕÀ");
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				String search=textField.getText();
				get_itemdisp( db_name,  con,date1,date2,search);
			}
		});
		btnNewButton.setBounds(841, 16, 117, 60);
		panel_2.add(btnNewButton);
		 
		
		Connection con=Dbsetup.dbconn.getConnection();
		String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
		String search=textField.getText();
		get_itemdisp( db_name,  con,date1,date2,search);
        
	}
	
	private void export_as_csv( ) throws IOException {
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\cheque_list.csv");

			for(int x = 0; x < model.getColumnCount(); x++){
	            excel.write(model.getColumnName(x) );
	            excel.write(",");
	        }

	        excel.write("\n");

	        for(int x=0; x< model.getRowCount(); x++) {
	            for(int y=0; y < model.getColumnCount(); y++) {
	                excel.write(model.getValueAt(x,y).toString());
	                if(y < model.getColumnCount()-1)
	                {
	                	excel.write(",");
	                }
	                else
	                {
	                	excel.write("\r\n");
	                }
	            }
	        }

	        excel.close();
	        frame.setCursor(Cursor.getDefaultCursor());
	        JOptionPane.showMessageDialog(frame, "Export Completed «ﬂ „· «· ’œÌ—");
		
	}
	private void  get_itemdisp( String company,  Connection con,String date1,String date2,String search)
	{
		try
		{
			String searchStr1="";
			if(!search.equals(""))
			{
				searchStr1="and ( pay_by like '%"+search+"%' or tran_num like '%"+search+"%') ";
			}
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			Statement statement=con.createStatement();
			String query1="SELECT * FROM "+company+".mr_cheque_details where cheque_date between '"+date1+"' and  '"+date2+"' and status!='VOID' "+searchStr1+" ";
					 
			ResultSet resultSet2 = statement.executeQuery(query1);
			if(resultSet2.next())
			{
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					double dr=Double.parseDouble(resultSet1.getString("dr"));
					double cr=Double.parseDouble(resultSet1.getString("cr"));
					BigDecimal bz = new BigDecimal(dr);
		    		bz = bz.setScale(2, BigDecimal.ROUND_HALF_UP);
		    		dr = bz.doubleValue();
		    		BigDecimal by = new BigDecimal(cr);
		    		by = by.setScale(2, BigDecimal.ROUND_HALF_UP);
		    		cr = by.doubleValue();
					model.addRow(new Object[]{model.getRowCount()+1,resultSet1.getString("issue_date"),resultSet1.getString("tran_num"),resultSet1.getString("pay_by"),resultSet1.getString("bank_name"),resultSet1.getString("cheque_no"),resultSet1.getString("cheque_date"),cr,dr,resultSet1.getString("cheque_printed"),resultSet1.getString("cheque_handover"),resultSet1.getString("cheque_posted") });
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Cheque Founded ·« ÌÊÃœ ‘Ìﬂ  „ ≈‰‘«ƒÂ");
			}
			
			
			
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
