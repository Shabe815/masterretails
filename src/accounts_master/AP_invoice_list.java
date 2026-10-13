package accounts_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
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
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

public class AP_invoice_list {

	static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	static JTextField textField;
	private static JButton btnProceed;
	public static JMenuItem menuItem;
	public static JButton btnNewButton;
	public static JLabel ttlamount;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					AP_invoice_list window = new AP_invoice_list("","demo","admin","S01", null,"2","");
					AP_invoice_list.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param decimal 
	 */
	public AP_invoice_list(String db_name,String company, String username,String store,String version, String decimal, String language) throws JSONException {
		initialize(db_name,company,username,store, version,decimal,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 * @param page 
	 */
	private void initialize(final String db_name,final String company,final String username,final String store,final String version,final String decimal, final String language) throws JSONException {
		
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 696);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    	//CafePro.Mainpage.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	CafePro.Mainpage.btnPurchaseOrder.setEnabled(true);
		    	//CafePro.Mainpage.btnNewButton_1.setEnabled(true);
		    }
		});
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		//frame.setAlwaysOnTop(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Vendor Invoice List");
		if(language.equals("Arabic"))
		{
			lblItemMasterList.setText("ﬁ«∆„… ›Ê« Ì— «·»«∆⁄Ì‰");
		}
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		menuItem = new JMenuItem("Exit");
		if(language.equals("Arabic"))
		{
			lblItemMasterList.setText("„Œ—Ã");
		}
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(780, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		
		
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		if(language.equals("Arabic"))
		{
			menuItem_1.setText("Ì‰⁄‘");
		}
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				get_po_disp( company, "OPEN", "",language);
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		btnProceed = new JButton("Post Invoice");
		if(language.equals("Arabic"))
		{
			btnProceed.setText("‰‘— «·›« Ê—…");
		}
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int row=table.getSelectedRow();
					if(row==-1)
					{
						
						if(language.equals("Arabic"))
						{
							JOptionPane.showMessageDialog(frame, "Õœœ ’›");
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Select A Row");
						}
					}
					else
					{
						int a= JOptionPane.showConfirmDialog(frame, "Do You Want to Post This Invoice Â·  —Ìœ ‰‘— Â–Â «·›« Ê—…ø","",JOptionPane.YES_NO_OPTION);
						if(a==0)
						{
							String tax_amt="5";
							LocalDateTime myDateObj = LocalDateTime.now();  
						    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
						    String formattedDate = myDateObj.format(myFormatObj);  
						    frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement = con.createStatement();
							Statement statement11 = con.createStatement();
							String invoice_num=model.getValueAt(row, 0).toString();
							ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_invoice where invoice_num='"+invoice_num+"' ");
							if(resultSet.next())
							{
								if(resultSet.getString("status").equals("OPEN"))
								{
									if(resultSet.getString("type").equals("PAYMENT"))
									{
										String seq_num=null;
										String trans_seq = "select tran_seq from "+company+".mr_transaction_seq where tran_orgin='JV' ";
										Statement statement30=con.createStatement();  
										ResultSet resultSet10 = statement30.executeQuery(trans_seq);
									    if (resultSet10.next())
									    {
									    	seq_num=resultSet10.getString("tran_seq");
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
									    	String save_po="update "+company+".mr_transaction_seq set modified_date='"+formattedDate+"',tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_orgin='JV' ";
											statement11.executeUpdate(save_po);
									    }
						                double grand_total =Double.parseDouble(resultSet.getString("net_total"));
						              
						                
						                String vendor_payable_ac="";
										String query4="SELECT * FROM "+company+".mr_vendor where vendor_code='"+resultSet.getString("supp_code")+"'"; 
										Statement statement1=con.createStatement();  
						                ResultSet resultSet1 = statement1.executeQuery(query4);
						                if(resultSet1.next())
						                {
						                	vendor_payable_ac=resultSet1.getString("vendor_payable_ac");
//						                	String vend_purch_amt=resultSet1.getString("vendor_purch_amt");
//						                	String vend_pend_amt=resultSet1.getString("vendor_pending_amt");
//						                	double new_po_amt=grand_total+Double.parseDouble(vend_purch_amt);
//						                	String new_poamt=df2.format(new_po_amt);
//						                	double new_po_pend_amt=grand_total+Double.parseDouble(vend_pend_amt);
//						                	String new_po_pendamt=df2.format(new_po_pend_amt);
//						                	String query6="update "+company+".mr_vendor set vendor_purch_amt='"+new_poamt+"', vendor_pending_amt='"+new_po_pendamt+"' where vendor_code ='"+resultSet.getString("supp_code")+"' ";
//											statement11.executeUpdate(query6);
						                }
						                
						                String def_payable=vendor_payable_ac;
						                String query16="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_code='"+def_payable+"' and acc_store='"+store+"'"; 
										Statement statement6=con.createStatement();  
						                ResultSet resultSet6= statement6.executeQuery(query16);
						                String acc_pay="";
						                if(resultSet6.next())
						                {
						                	acc_pay=resultSet6.getString("acc_name");
						                }
//						       
						                String tax_code="";
						                String query27="SELECT * FROM "+company+".mr_tax where tax_rate='"+tax_amt+"' and store='"+store+"'"; 
										Statement statement27=con.createStatement();  
						                ResultSet resultSet27 = statement27.executeQuery(query27);
						                if(resultSet27.next())
						                {
						                	tax_code=resultSet27.getString("account_code");
						                }
						                
						                String tax_input="";
						                String query28="SELECT * FROM "+company+".mr_assign_account_group where group_code='"+tax_code+"' and acc_store='"+store+"' and status='1' "; 
										Statement statement28=con.createStatement();  
						                ResultSet resultSet28 = statement28.executeQuery(query28);
						                if(resultSet28.next())
						                {
						                	tax_input=resultSet28.getString("tax_input");
						                }
						                
						                String tax_jv="";
						                String tax_leger="";
						                String query18="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_code='"+tax_input+"' and acc_store='"+store+"'"; 
										Statement statement18=con.createStatement();  
						                ResultSet resultSet18= statement18.executeQuery(query18);
						                String tax_name="";
						                if(resultSet18.next())
						                {
						                	tax_name=resultSet18.getString("acc_name");
						                }
						                tax_jv=",('"+seq_num+"','"+seq_num+"','"+tax_input+"','"+tax_name+"','"+resultSet.getString("vat_amt")+"','0','"+invoice_num+"','"+resultSet.getString("doc_num")+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+resultSet.getString("inv_date")+"')";
						                
						                String query29="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+tax_input+"'"; 
										Statement statement9=con.createStatement();  
						                ResultSet resultSet9= statement9.executeQuery(query29);
						                float cr_tax=0;
						                float dr_tax=0;
						                while(resultSet9.next())
						                {
						                	cr_tax+=Float.parseFloat(resultSet9.getString("cr_amt"));
						                	dr_tax+=Float.parseFloat(resultSet9.getString("dr_amt"));
						                }
										float bal_tax=dr_tax-cr_tax;
										double bal_taxable=bal_tax+Double.parseDouble(resultSet.getString("vat_amt"));
						                tax_leger=",('"+seq_num+"','"+resultSet.getString("inv_date")+"','"+formattedDate+"','"+tax_input+"','"+resultSet.getString("vat_amt")+"','0','"+bal_taxable+"','"+invoice_num+"','"+resultSet.getString("doc_num")+"','PO','"+formattedDate+"','"+formattedDate+"','OPEN' )";
						                
						                String disc_jv="";
						                String disc_leger="";
						                if(Double.parseDouble(resultSet.getString("disc_amt"))!=0)
									    {
									    	
							                
							                String query118="SELECT * FROM "+db_name+".mr_chart_accnt_mstr where acc_grp_name='DISCOUNT ON PURCHASE' and acc_store='"+store+"'"; 
											Statement statement118=con.createStatement();  
							                ResultSet resultSet118= statement118.executeQuery(query118);
							                String disc_name="";
							                String disc_code="";
							                if(resultSet118.next())
							                {
							                	disc_name=resultSet118.getString("acc_name");
							                	disc_code=resultSet118.getString("acc_code");
							                }
							                disc_jv=",('"+seq_num+"','"+seq_num+"','"+disc_code+"','"+disc_name+"','"+resultSet.getString("disc_amt")+"','0','"+invoice_num+"','"+resultSet.getString("doc_num")+"-"+resultSet.getString("supp_code")+"-"+resultSet.getString("acc_no")+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"')";
							                
							                String query219="SELECT * FROM "+db_name+".mr_acc_ledger where ac_code='"+disc_code+"'"; 
											Statement statement19=con.createStatement();  
							                ResultSet resultSet19= statement19.executeQuery(query219);
							                float cr_disc=0;
							                float dr_disc=0;
							                while(resultSet19.next())
							                {
							                	cr_disc+=Float.parseFloat(resultSet19.getString("cr_amt"));
							                	dr_disc+=Float.parseFloat(resultSet19.getString("dr_amt"));
							                }
											float bal_disc=dr_disc-cr_disc;
											//double bal_discount=bal_disc+Double.parseDouble(resultSet.getString("disc_amt"));
											
											double new_disc_bal=bal_disc-Double.parseDouble(resultSet.getString("disc_amt"));
											
											disc_leger=",('"+seq_num+"','"+formattedDate+"','"+formattedDate+"','"+disc_code+"','"+resultSet.getString("disc_amt")+"','0','"+new_disc_bal+"','"+invoice_num+"','"+resultSet.getString("doc_num")+"','PO','"+formattedDate+"','"+formattedDate+"','OPEN' )";
											
									    }
						               			
										
										String save_po="insert into "+company+".mr_jv_header( `jv_num`, `date`, `branch`, `username`, `doc_num`, `remark`, `notes`, `store_id`, `created_date`, `modified_date`, `status`,`is_approved`, `approved_by`, `is_posted`, `posted_by`, `posted_date`, `approved_date`,jv_amt,vendor_cust_id) values('"+seq_num+"','"+resultSet.getString("inv_date")+"','"+resultSet.getString("branch")+"','"+username+"','"+resultSet.getString("doc_num")+"','"+invoice_num+"','"+invoice_num+"','"+store+"','"+formattedDate+"','"+formattedDate+"','Unapproved','1','"+username+"','1','"+username+"','"+formattedDate+"','"+formattedDate+"','0','"+resultSet.getString("supp_code")+"' )";
										statement11.executeUpdate(save_po);
										
										String query1="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,cheque_date) VALUES ('"+seq_num+"','"+seq_num+"','"+resultSet.getString("acc_no")+"','"+resultSet.getString("acc_desc")+"','"+(Double.parseDouble(resultSet.getString("net_total"))-Double.parseDouble(resultSet.getString("vat_amt")))+"','0','"+invoice_num+"','"+resultSet.getString("doc_num")+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+resultSet.getString("inv_date")+"'),('"+seq_num+"','"+seq_num+"','"+def_payable+"','"+acc_pay+"','0','"+grand_total+"','"+invoice_num+"','"+resultSet.getString("doc_num")+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+resultSet.getString("inv_date")+"') "+tax_jv+" "+disc_jv+" ";
										statement11.executeUpdate(query1);
										
										String query37="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+resultSet.getString("acc_no")+"'"; 
										Statement statement37=con.createStatement();  
						                ResultSet resultSet37= statement37.executeQuery(query37);
						                float cr=0;
						                float dr=0;
						                while(resultSet37.next())
						                {
						                	cr+=Float.parseFloat(resultSet37.getString("cr_amt"));
						                	dr+=Float.parseFloat(resultSet37.getString("dr_amt"));
						                }
										float bal_asset=dr-cr;
										double bal=bal_asset+Double.parseDouble(resultSet.getString("net_total"));
										
										String query38="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+def_payable+"'"; 
										Statement statement38=con.createStatement();  
						                ResultSet resultSet38= statement38.executeQuery(query38);
						                float cr_pay=0;
						                float dr_pay=0;
						                while(resultSet38.next())
						                {
						                	cr_pay+=Float.parseFloat(resultSet38.getString("cr_amt"));
						                	dr_pay+=Float.parseFloat(resultSet38.getString("dr_amt"));
						                }
										float bal_liable=dr_pay-cr_pay;
										double bal_lia=bal_liable-grand_total;
										
										
										
										String save_ledger="insert into "+company+".mr_acc_ledger (`jv_num`, `tran_date`, `post_date`, `ac_code`, `dr_amt`, `cr_amt`, `balance`, `notes`, `remark`, `source`, `created_date`, `modified_date`,status) values ('"+seq_num+"','"+resultSet.getString("inv_date")+"','"+formattedDate+"','"+resultSet.getString("acc_no")+"','"+(Double.parseDouble(resultSet.getString("net_total"))-Double.parseDouble(resultSet.getString("vat_amt")))+"','0','"+bal+"','"+invoice_num+"','"+resultSet.getString("doc_num")+"','PO','"+formattedDate+"','"+formattedDate+"','OPEN' ),('"+seq_num+"','"+resultSet.getString("inv_date")+"','"+formattedDate+"','"+def_payable+"','0','"+grand_total+"','"+bal_lia+"','"+invoice_num+"','"+resultSet.getString("doc_num")+"','PO','"+formattedDate+"','"+formattedDate+"','OPEN' ) "+tax_leger+" "+disc_leger+" ";
										statement11.executeUpdate(save_ledger);
										
										
										
										String update_pl="update "+company+".mr_invoice set status='POSTED' where invoice_num ='"+invoice_num+"' ";
										statement11.executeUpdate(update_pl);
										
										String history="INSERT INTO "+company+".mr_invoice_history( invoice_num, branch, inv_date, username, doc_num, remark, supp_name, inv_amt, disc_amt, vat_type, vat_amt, net_total, acc_no, acc_desc, created_date, modified_date, status,supp_code,type,store) SELECT invoice_num, branch, inv_date, username, doc_num, remark, supp_name, inv_amt, disc_amt, vat_type, vat_amt, net_total, acc_no, acc_desc, created_date, modified_date, status,supp_code,type,store FROM "+company+".mr_invoice WHERE invoice_num ='"+invoice_num+"' ";
										statement11.executeUpdate(history);
										
										String delete_pl="delete from "+company+".mr_invoice where invoice_num ='"+invoice_num+"' ";
										statement11.executeUpdate(delete_pl);
										
										frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
										
										if(language.equals("Arabic"))
										{
											JOptionPane.showMessageDialog(frame, " „ ≈—”«· «·›« Ê—… »‰Ã«Õ");
										}
										else
										{
											JOptionPane.showMessageDialog(frame, "Invoice Posted Successfully");
										}
										get_po_disp(company, "OPEN", "",language);
									}
									else
									{
										String seq_num=null;
										String trans_seq = "select tran_seq from "+company+".mr_transaction_seq where tran_orgin='JV' ";
										Statement statement30=con.createStatement();  
										ResultSet resultSet10 = statement30.executeQuery(trans_seq);
									    if (resultSet10.next())
									    {
									    	seq_num=resultSet10.getString("tran_seq");
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
									    	String save_po="update "+company+".mr_transaction_seq set modified_date='"+formattedDate+"',tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_orgin='JV' ";
											statement11.executeUpdate(save_po);
									    }
						                double grand_total =Double.parseDouble(resultSet.getString("net_total"));
						              
						                
						                String vendor_payable_ac="";
										String query4="SELECT * FROM "+company+".mr_vendor where vendor_code='"+resultSet.getString("supp_code")+"'"; 
										Statement statement1=con.createStatement();  
						                ResultSet resultSet1 = statement1.executeQuery(query4);
						                if(resultSet1.next())
						                {
						                	vendor_payable_ac=resultSet1.getString("vendor_payable_ac");
//						                	String vend_purch_amt=resultSet1.getString("vendor_purch_amt");
//						                	String vend_pend_amt=resultSet1.getString("vendor_pending_amt");
//						                	double new_po_amt=grand_total+Double.parseDouble(vend_purch_amt);
//						                	String new_poamt=df2.format(new_po_amt);
//						                	double new_po_pend_amt=grand_total+Double.parseDouble(vend_pend_amt);
//						                	String new_po_pendamt=df2.format(new_po_pend_amt);
//						                	String query6="update "+company+".mr_vendor set vendor_purch_amt='"+new_poamt+"', vendor_pending_amt='"+new_po_pendamt+"' where vendor_code ='"+resultSet.getString("supp_code")+"' ";
//											statement11.executeUpdate(query6);
						                }
						                
						                String def_payable=vendor_payable_ac;
						                String query16="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_code='"+def_payable+"' and acc_store='"+store+"'"; 
										Statement statement6=con.createStatement();  
						                ResultSet resultSet6= statement6.executeQuery(query16);
						                String acc_pay="";
						                if(resultSet6.next())
						                {
						                	acc_pay=resultSet6.getString("acc_name");
						                }
//						       
						                String tax_code="";
						                String query27="SELECT * FROM "+company+".mr_tax where tax_rate='"+tax_amt+"' and store='"+store+"'"; 
										Statement statement27=con.createStatement();  
						                ResultSet resultSet27 = statement27.executeQuery(query27);
						                if(resultSet27.next())
						                {
						                	tax_code=resultSet27.getString("account_code");
						                }
						                
						                String tax_input="";
						                String query28="SELECT * FROM "+company+".mr_assign_account_group where group_code='"+tax_code+"' and acc_store='"+store+"' and status='1' "; 
										Statement statement28=con.createStatement();  
						                ResultSet resultSet28 = statement28.executeQuery(query28);
						                if(resultSet28.next())
						                {
						                	tax_input=resultSet28.getString("tax_input");
						                }
						                
						                String tax_jv="";
						                String tax_leger="";
						                String query18="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_code='"+tax_input+"' and acc_store='"+store+"'"; 
										Statement statement18=con.createStatement();  
						                ResultSet resultSet18= statement18.executeQuery(query18);
						                String tax_name="";
						                if(resultSet18.next())
						                {
						                	tax_name=resultSet18.getString("acc_name");
						                }
						                tax_jv=",('"+seq_num+"','"+seq_num+"','"+tax_input+"','"+tax_name+"','0','"+resultSet.getString("vat_amt")+"','"+invoice_num+"','"+resultSet.getString("doc_num")+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+resultSet.getString("inv_date")+"')";
						                
						                String query29="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+tax_input+"'"; 
										Statement statement9=con.createStatement();  
						                ResultSet resultSet9= statement9.executeQuery(query29);
						                float cr_tax=0;
						                float dr_tax=0;
						                while(resultSet9.next())
						                {
						                	cr_tax+=Float.parseFloat(resultSet9.getString("cr_amt"));
						                	dr_tax+=Float.parseFloat(resultSet9.getString("dr_amt"));
						                }
										float bal_tax=dr_tax-cr_tax;
										double bal_taxable=bal_tax-Double.parseDouble(resultSet.getString("vat_amt"));
						                tax_leger=",('"+seq_num+"','"+resultSet.getString("inv_date")+"','"+formattedDate+"','"+tax_input+"','0','"+resultSet.getString("vat_amt")+"','"+bal_taxable+"','"+invoice_num+"','"+resultSet.getString("doc_num")+"','PO','"+formattedDate+"','"+formattedDate+"','OPEN' )";
						                
						                
						               			
										
										String save_po="insert into "+company+".mr_jv_header( `jv_num`, `date`, `branch`, `username`, `doc_num`, `remark`, `notes`, `store_id`, `created_date`, `modified_date`, `status`,`is_approved`, `approved_by`, `is_posted`, `posted_by`, `posted_date`, `approved_date`,jv_amt,vendor_cust_id) values('"+seq_num+"','"+resultSet.getString("inv_date")+"','"+resultSet.getString("branch")+"','"+username+"','"+resultSet.getString("doc_num")+"','"+invoice_num+"','"+invoice_num+"','"+store+"','"+formattedDate+"','"+formattedDate+"','Posted','1','"+username+"','1','"+username+"','"+formattedDate+"','"+formattedDate+"','"+grand_total+"','"+resultSet.getString("supp_code")+"' )";
										statement11.executeUpdate(save_po);
										
										String query1="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,cheque_date) VALUES ('"+seq_num+"','"+seq_num+"','"+resultSet.getString("acc_no")+"','"+resultSet.getString("acc_desc")+"','0','"+(Double.parseDouble(resultSet.getString("net_total"))-Double.parseDouble(resultSet.getString("vat_amt")))+"','"+invoice_num+"','"+resultSet.getString("doc_num")+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+resultSet.getString("inv_date")+"'),('"+seq_num+"','"+seq_num+"','"+def_payable+"','"+acc_pay+"','"+grand_total+"','0','"+invoice_num+"','"+resultSet.getString("doc_num")+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+resultSet.getString("inv_date")+"') "+tax_jv+" ";
										statement11.executeUpdate(query1);
										
										String query37="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+resultSet.getString("acc_no")+"'"; 
										Statement statement37=con.createStatement();  
						                ResultSet resultSet37= statement37.executeQuery(query37);
						                float cr=0;
						                float dr=0;
						                while(resultSet37.next())
						                {
						                	cr+=Float.parseFloat(resultSet37.getString("cr_amt"));
						                	dr+=Float.parseFloat(resultSet37.getString("dr_amt"));
						                }
										float bal_asset=dr-cr;
										double bal=bal_asset-Double.parseDouble(resultSet.getString("net_total"));
										
										String query38="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+def_payable+"'"; 
										Statement statement38=con.createStatement();  
						                ResultSet resultSet38= statement38.executeQuery(query38);
						                float cr_pay=0;
						                float dr_pay=0;
						                while(resultSet38.next())
						                {
						                	cr_pay+=Float.parseFloat(resultSet38.getString("cr_amt"));
						                	dr_pay+=Float.parseFloat(resultSet38.getString("dr_amt"));
						                }
										float bal_liable=dr_pay-cr_pay;
										double bal_lia=bal_liable+grand_total;
										
										
										
										String save_ledger="insert into "+company+".mr_acc_ledger (`jv_num`, `tran_date`, `post_date`, `ac_code`, `dr_amt`, `cr_amt`, `balance`, `notes`, `remark`, `source`, `created_date`, `modified_date`,status) values ('"+seq_num+"','"+resultSet.getString("inv_date")+"','"+formattedDate+"','"+resultSet.getString("acc_no")+"','0','"+(Double.parseDouble(resultSet.getString("net_total"))-Double.parseDouble(resultSet.getString("vat_amt")))+"','"+bal+"','"+invoice_num+"','"+resultSet.getString("doc_num")+"','PO','"+formattedDate+"','"+formattedDate+"','OPEN' ),('"+seq_num+"','"+resultSet.getString("inv_date")+"','"+formattedDate+"','"+def_payable+"','"+grand_total+"','0','"+bal_lia+"','"+invoice_num+"','"+resultSet.getString("doc_num")+"','PO','"+formattedDate+"','"+formattedDate+"','OPEN' ) "+tax_leger+" ";
										statement11.executeUpdate(save_ledger);
										
										
										
										String update_pl="update "+company+".mr_invoice set status='POSTED' where invoice_num ='"+invoice_num+"' ";
										statement11.executeUpdate(update_pl);
										
										String history="INSERT INTO "+company+".mr_invoice_history( invoice_num, branch, inv_date, username, doc_num, remark, supp_name, inv_amt, disc_amt, vat_type, vat_amt, net_total, acc_no, acc_desc, created_date, modified_date, status,supp_code,type,store) SELECT invoice_num, branch, inv_date, username, doc_num, remark, supp_name, inv_amt, disc_amt, vat_type, vat_amt, net_total, acc_no, acc_desc, created_date, modified_date, status,supp_code,type,store FROM "+company+".mr_invoice WHERE invoice_num ='"+invoice_num+"' ";
										statement11.executeUpdate(history);
										
										String delete_pl="delete from "+company+".mr_invoice where invoice_num ='"+invoice_num+"' ";
										statement11.executeUpdate(delete_pl);
										
										frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
										if(language.equals("Arabic"))
										{
											JOptionPane.showMessageDialog(frame, " „ ≈—”«· «·›« Ê—… »‰Ã«Õ");
										}
										else
										{
											JOptionPane.showMessageDialog(frame, "Invoice Posted Successfully");
										}
										get_po_disp(company, "OPEN", "",language);
									}
									
									
								}
							}
						
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		btnProceed.setBounds(652, 24, 103, 25);
		panel.add(btnProceed);
		
		btnNewButton = new JButton("New Invoice");
		if(language.equals("Arabic"))
		{
			btnNewButton.setText("›« Ê—… ÃœÌœ…");
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new AP_invoice(company,version,store,username,"",decimal,"OPEN",language);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(530, 23, 112, 25);
		panel.add(btnNewButton);
		
		JButton btnVoid = new JButton("Void Invoice");
		if(language.equals("Arabic"))
		{
			btnVoid.setText("›« Ê—… »«ÿ·…");
		}
		btnVoid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row==-1)
				{
					
					if(language.equals("Arabic"))
					{
						JOptionPane.showMessageDialog(frame, "Õœœ ’›");
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Select A Row");
					}
				}
				else
				{
					int a= JOptionPane.showConfirmDialog(frame, "Do You Want to Void This Invoice Â·  —Ìœ ≈·€«¡ Â–Â «·›« Ê—…ø","",JOptionPane.YES_NO_OPTION);
					if(a==0)
					{
						try{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String invoice_num=model.getValueAt(row, 0).toString();
							String query="SELECT * FROM "+company+".mr_invoice where invoice_num='"+invoice_num+"'"; 
							ResultSet resultSet = statement.executeQuery(query);
							if(resultSet.next())
							{
								
								String update_pl="update "+company+".mr_invoice set status='VOID' where invoice_num ='"+invoice_num+"' ";
								statement.executeUpdate(update_pl);
								
								String history="INSERT INTO "+company+".mr_invoice_history( invoice_num, branch, inv_date, username, doc_num, remark, supp_name, inv_amt, disc_amt, vat_type, vat_amt, net_total, acc_no, acc_desc, created_date, modified_date, status,supp_code,type,store) SELECT invoice_num, branch, inv_date, username, doc_num, remark, supp_name, inv_amt, disc_amt, vat_type, vat_amt, net_total, acc_no, acc_desc, created_date, modified_date, status,supp_code,type,store FROM "+company+".mr_invoice WHERE invoice_num ='"+invoice_num+"' ";
								statement.executeUpdate(history);
								
								String delete_pl="delete from "+company+".mr_invoice where invoice_num ='"+invoice_num+"' ";
								statement.executeUpdate(delete_pl);
								
								
								if(language.equals("Arabic"))
								{
									JOptionPane.showMessageDialog(frame, " „ ≈·€«¡ «·›« Ê—… »‰Ã«Õ");
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Invoice Voided Successfully");
								}
								get_po_disp(company, "OPEN", "",language);
							}
							con.close();
						}
						catch(Exception e){
							System.err.println(e);
						}
					}
				}
				
			}
		});
		btnVoid.setBounds(396, 23, 124, 25);
		panel.add(btnVoid);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 589);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 88, 908, 455);
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
		    	model.addColumn("—ﬁ„ «·›« Ê—…");
			    model.addColumn("—ﬁ„ «·ÊÀÌﬁ…"); 
			    model.addColumn("«”„ «·»«∆⁄");
			    model.addColumn("„»·€ «·›« Ê—…"); 
			    model.addColumn("„»·€ «·÷—Ì»…"); 
			    model.addColumn("«·„»·€ «·≈Ã„«·Ì"); 
			    model.addColumn(" «—ÌŒ «·›« Ê—…");
			    model.addColumn("„·«ÕŸ…");
			}
		    else
		    {
		    	model.addColumn("Invoice Number");
			    model.addColumn("Doc No."); 
			    model.addColumn("vendor Name");
			    model.addColumn("Invoice Amount"); 
			    model.addColumn("Vat Amount"); 
			    model.addColumn("Total Amount"); 
			    model.addColumn("Invoice Date");
			    model.addColumn("Remark");
		    }
		    
		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(150);
			  if (i==1) column.setPreferredWidth(140);
			  if (i==2) column.setPreferredWidth(200);
			  if (i==3) column.setPreferredWidth(105);
			  if (i==4) column.setPreferredWidth(50);
			  if (i==5) column.setPreferredWidth(50);
			  if (i==6) column.setPreferredWidth(90);
			  if (i==7) column.setPreferredWidth(105);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 908, 52);
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
				  get_po_disp( company,"OPEN",textField.getText(),language);
			  }
			});
		textField.setBounds(12, 13, 493, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		if(language.equals("Arabic"))
		{
			btnSearch.setText("Ì»ÕÀ");
		}
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( company,"OPEN",textField.getText(),language);
			}
		});
		btnSearch.setBounds(518, 13, 97, 25);
		panel_2.add(btnSearch);
		
		JButton btnNewButton_1 = new JButton("Inv Posted List");
		if(language.equals("Arabic"))
		{
			btnNewButton_1.setText("ﬁ«∆„… «·›Ê« Ì— «·„—”·…");
		}
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setAlwaysOnTop(false);
					new posted_AP_list(db_name, company, username, store, version, decimal,"POSTED",language);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(753, 13, 143, 25);
		panel_2.add(btnNewButton_1);
		
		JButton btnVoidPoList = new JButton("Void Inv List");
		if(language.equals("Arabic"))
		{
			btnVoidPoList.setText("ﬁ«∆„… «·›Ê« Ì— «·»«ÿ·…");
		}
		btnVoidPoList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setAlwaysOnTop(false);
					new posted_AP_list(db_name, company, username, store, version, decimal,"VOID",language);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnVoidPoList.setBounds(633, 13, 108, 25);
		panel_2.add(btnVoidPoList);
		
		JLabel lblTotalAmount = new JLabel("Total Amount : ");
		lblTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalAmount.setBounds(439, 554, 96, 14);
		panel_1.add(lblTotalAmount);
		
		ttlamount = new JLabel("0");
		ttlamount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ttlamount.setBounds(545, 554, 231, 14);
		panel_1.add(ttlamount);
		 
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 0;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	try{
            			
	 					frame.setAlwaysOnTop(false);
	 					new AP_invoice(company,version,store,username,value,decimal,"OPEN",language);
	 					//purchase_order.frame.setAlwaysOnTop(true);
                	}
                	catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	//frame.setVisible(false);
                }
            }
        });
        get_po_disp( company,"OPEN",textField.getText(),language);
        
	}
	public static void  get_po_disp( String company, String type, String search,String language)
	{
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and (invoice_num LIKE '%"+search+"%' OR supp_name LIKE '%"+search+"%' OR doc_num LIKE '%"+search+"%' )";
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_invoice where status='"+type+"' "+serch_str+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			double ttl=0;
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+company+".mr_invoice where status='"+type+"' "+serch_str+" "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String vendor_name=resultSet1.getString("supp_name");
					String name=resultSet1.getString("invoice_num");
	                String inv_amt=resultSet1.getString("inv_amt");
	                String vat_amt=resultSet1.getString("vat_amt");
	                String net_total=resultSet1.getString("net_total");
	                String inv_date=resultSet1.getString("inv_date");
	                String remark=resultSet1.getString("remark");
	                String doc_num=resultSet1.getString("doc_num");
	                ttl+=Double.parseDouble(net_total);
	                model.addRow(new Object[]{name,doc_num,vendor_name,inv_amt,vat_amt,net_total,inv_date,remark});
				}
				ttlamount.setText(Double.toString(ttl));
	        	btnProceed.setEnabled(true);
			}
			else
			{
				
				if(language.equals("Arabic"))
				{
					JOptionPane.showMessageDialog(frame, "·„ Ì „ «·⁄ÀÊ— ⁄·Ï ﬁ«∆„… «·›Ê« Ì—");
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "No Invoice List Found");
				}
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
