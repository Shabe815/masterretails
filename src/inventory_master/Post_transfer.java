package inventory_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Post_transfer {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private boolean dot = false; 
	private String tran_company="";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Post_transfer window = new Post_transfer("mr_pro", "AD-19-01-000003", "1", "127.2", "2019-05-08", null,0,null,null, null,"");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param version2 
	 * @param string3 
	 * @param string2 
	 * @param string 
	 * @throws ParseException 
	 */
	public Post_transfer(String company,String ponum, String inv_num, String inv_amt, String po_date, String version,int decimal,String username,String store,String page_type,String tostore) throws ParseException {
		initialize(company,ponum, inv_num, inv_amt, po_date, version,decimal,username,store,page_type,tostore);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param decimal 
	 * @param username 
	 * @throws ParseException 
	 */
	private void initialize(final String company,final String ponum,final String inv_num,final String inv_amt,final String po_date,final String version, int decimal, final String username,final String store,final String page_type,final String tostore) throws ParseException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 337, 314);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 321, 276);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPostPurchaseOrser = new JLabel("POST INVENTORY TRANSFER "+page_type);
		lblPostPurchaseOrser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPostPurchaseOrser.setBounds(10, 11, 301, 14);
		panel.add(lblPostPurchaseOrser);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 46, 301, 183);
		panel.add(panel_1);
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setLayout(null);
		
		JLabel lblPoNumber = new JLabel("Transfer Number");
		lblPoNumber.setBounds(0, 25, 108, 14);
		panel_1.add(lblPoNumber);
		lblPoNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblInvoiceNumber = new JLabel("Invoice Number");
		lblInvoiceNumber.setBounds(0, 64, 89, 14);
		panel_1.add(lblInvoiceNumber);
		lblInvoiceNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblInvoiceAmount = new JLabel("Invoice Amount");
		lblInvoiceAmount.setBounds(0, 103, 129, 14);
		panel_1.add(lblInvoiceAmount);
		lblInvoiceAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblPoDate = new JLabel("Tran Date");
		lblPoDate.setBounds(0, 142, 108, 14);
		panel_1.add(lblPoDate);
		lblPoDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textField = new JTextField(ponum);
		textField.setBounds(118, 22, 159, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(inv_num);
		textField_1.setBounds(118, 61, 159, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(inv_amt);
		textField_2.setBounds(118, 100, 159, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_2.getText().equals(""))
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
		java.util.Date date =null;
		if(po_date!="")
		{
			date = new SimpleDateFormat("yyyy-MM-dd").parse(po_date);
		}
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select store_short_name from "+company+".mr_store where store_id='"+tostore+"' ");
			if(resultSet.next())
			{
				tran_company=resultSet.getString("store_short_name");
				
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "STORE NOT FOUND");
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
		final JDateChooser dateChooser = new JDateChooser(new Date());
		dateChooser.setBounds(118, 136, 159, 20);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setDate(date);
		panel_1.add(dateChooser);
		
		Button button = new Button("Proceed ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    final String formattedDate = myDateObj.format(myFormatObj);  
				    //final Calendar cal = Calendar.getInstance();
			        //final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String startdate = null;
					String start_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					if(!start_date.equals(""))
					{
						startdate = sdf.format(dateChooser.getDate());
					}
					if(textField.getText().equals(ponum) && textField_1.getText().equals(inv_num) && textField_2.getText().equals(inv_amt) && startdate.equals(po_date) )
					{
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement = con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_inventory_transfer_list where tran_no='"+ponum+"' ");
						if(resultSet.next())
						{
							if(resultSet.getString("status").equals("OPEN"))
							{
								String ad_date=resultSet.getString("tran_date");
								double ttl_cost=0;
								double ttl_price=Double.parseDouble(resultSet.getString("ttl_price"));
								double grand_ttl=Double.parseDouble(resultSet.getString("sub_ttl_amt"));
								if(ttl_price==grand_ttl)
								{
									/*
									vendor statement
									transaction sequence
									item master
									tax
									history
									po
									*/
									String adj_list="select * from "+company+".mr_inventory_transfer_details where ref_tran_num='"+ponum+"' ";
									Statement statement1=con.createStatement();
									Statement statement4=con.createStatement();
									ResultSet resultSet1 = statement1.executeQuery(adj_list);
									while(resultSet1.next())
									{
										
										String avg_qty="0";
										String item_code=resultSet1.getString("item_code");
										String qty=resultSet1.getString("qty");
										String unit=resultSet1.getString("unit");
										//String qtyoh=resultSet1.getString("qtyOh");
										String cf=resultSet1.getString("cf");
										String unit_cost=resultSet1.getString("unit_cost");
										ttl_cost+=Double.parseDouble(unit_cost)*Float.parseFloat(qty)*Float.parseFloat(cf);
										String barcode=resultSet1.getString("barcode");
										double ttl_qty=Double.parseDouble(qty)*Double.parseDouble(cf);
										double new_qty=ttl_qty;
										String query1="SELECT * FROM "+company+".mr_item_master where item_code='"+item_code+"' "; 
										ResultSet resultSet2 = statement.executeQuery(query1);
										if(resultSet2.next())
										{
											
											avg_qty=resultSet2.getString("total_qty");
										}
										String trancode="";
										if(page_type.equals("ISSUE"))
										{
											new_qty=Double.parseDouble(avg_qty)-new_qty;
											trancode="TF";
										}
										else
										{
											new_qty=Double.parseDouble(avg_qty)+new_qty;
											trancode="RV";
										}
										String update_itm_mstr="update "+company+".mr_item_master set total_qty='"+new_qty+"',modified_date='"+formattedDate+"' where item_code='"+item_code+"' ";
										statement.executeUpdate(update_itm_mstr);
										String update_itm_tmp_mstr="update "+company+".mr_tmp_item_master set qty='"+new_qty+"' where item_code='"+item_code+"' ";
										statement.executeUpdate(update_itm_tmp_mstr);
										
										String query11="insert into "+company+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) "
												+ "values('"+item_code+"','"+trancode+"','"+ponum+"','"+ttl_qty+"','"+unit+"','"+cf+"','"+(Double.parseDouble(unit_cost)*Float.parseFloat(cf))+"','"+(Double.parseDouble(unit_cost)*Float.parseFloat(qty)*Float.parseFloat(cf))+"','0','0','0','Inventory Transfer "+page_type+" - "+ponum+" ','"+username+"','"+ad_date+"','"+barcode+"','','')";
										statement.executeUpdate(query11);
										
										String kititm_validate="select * from "+company+".mr_kit_items where parent_itemcode='"+item_code+"' ";
										Statement statement125=con.createStatement();
										ResultSet resultSet125 = statement125.executeQuery(kititm_validate);
										if(resultSet125.next())
										{
											Statement statement150=con.createStatement();
											ResultSet resultSet150 = statement150.executeQuery(kititm_validate);
											while(resultSet150.next())
											{
												
												String sp=resultSet150.getString("sp");
												String child_barcode=resultSet150.getString("child_barcode");
												String cost=resultSet150.getString("cost");
												String kit_qty=resultSet150.getString("qty");
												double ttlprice=Double.parseDouble(kit_qty)*Double.parseDouble(sp);
												
												String itmvalidate="select * from "+company+".mr_tmp_item_master where item_code='"+resultSet150.getString("child_itemcode")+"' ";
												Statement statement151=con.createStatement();
												ResultSet resultSet151 = statement151.executeQuery(itmvalidate);
												if(resultSet151.next())
												{
													String total_qty=resultSet151.getString("qty"); 
													float newqty=Float.parseFloat(total_qty)-(Float.parseFloat(qty)*Float.parseFloat(kit_qty));
													String update_qty="update "+company+".mr_item_master set total_qty='"+newqty+"',modified_date='"+formattedDate+"' where item_code='"+resultSet150.getString("child_itemcode")+"'  ";
													statement4.executeUpdate(update_qty);
													String update_temp="update "+company+".mr_tmp_item_master set qty='"+newqty+"' where item_code='"+resultSet150.getString("child_itemcode")+"'  ";
													statement4.executeUpdate(update_temp);
													String item_hist_update="insert into "+company+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) "
															+ "values('"+resultSet150.getString("child_itemcode")+"','"+trancode+"','"+ponum+"','"+kit_qty+"','"+unit+"','1','"+cost+"','"+(Double.parseDouble(cost)*Float.parseFloat(kit_qty))+"','"+qty+"','"+sp+"','"+ttlprice+"','KIT SALE','"+username+"','"+formattedDate+"','"+child_barcode+"','','')";
													statement4.executeUpdate(item_hist_update);
													
												}
											}
//											String update_qty="update "+company+".mr_item_master set total_qty='0',modified_date='"+formattedDate+"' where item_code='"+item_code+"'  ";
//											statement4.executeUpdate(update_qty);
//											String update_temp="update "+company+".mr_tmp_item_master set qty='0' where item_code='"+item_code+"'  ";
//											statement4.executeUpdate(update_temp);
										}
									}
									
									//ACCOUNTS
									
									String seqnum=null;
									String transseq = "select tran_seq from "+company+".mr_transaction_seq where tran_orgin='JV' ";
									Statement statement30=con.createStatement();  
									ResultSet resultSet30 = statement30.executeQuery(transseq);
								    if (resultSet30.next())
								    {
								    	seqnum=resultSet30.getString("tran_seq");
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
								    	String save_po="update "+company+".mr_transaction_seq set modified_date='"+formattedDate+"',tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_orgin='JV' ";
										statement.executeUpdate(save_po);
								    }
									
									
									
							        String inv_acc="";
							        String cost_correction="";
							        String query28="SELECT * FROM "+company+".mr_assign_account_group where acc_store='"+store+"' and status='1' "; 
									Statement statement28=con.createStatement();  
							        ResultSet resultSet28 = statement28.executeQuery(query28);
							        if(resultSet28.next())
							        {
							        	inv_acc=resultSet28.getString("inv_acc");
							        	cost_correction=resultSet28.getString("cost_correction");
							        }
							        String inv_stock="";
							        String costcorrection="";
							        
							        String query29="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_store='"+store+"'"; 
									Statement statement29=con.createStatement();  
							        ResultSet resultSet29= statement29.executeQuery(query29);
							        while(resultSet29.next())
							        {
							        	if(inv_acc.equals(resultSet29.getString("acc_code")))
							        	{
							        		inv_stock=resultSet29.getString("acc_name");
							        	}
							        	if(cost_correction.equals(resultSet29.getString("acc_code")))
							        	{
							        		costcorrection=resultSet29.getString("acc_name");
							        	}
							        }
							        
							        String branch="";
							        String branch_query = "select * from "+company+".mr_acc_segment where store_id='"+store+"' ";
									Statement statement33=con.createStatement();  
									ResultSet resultSet33 = statement33.executeQuery(branch_query);
								    if (resultSet33.next())
								    {
								    	if(resultSet33.getString("segment").equals("Branch"))
								    	{
								    		branch=resultSet33.getString("name");
								    	}
								    }
								    
								    BigDecimal bd = new BigDecimal(ttl_cost);
		                    		bd = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
		                    		ttl_cost = bd.doubleValue();
		                    		
							    	
							    	
		                    		
								    String save_jv="insert into "+company+".mr_jv_header( `jv_num`, `date`, `branch`, `username`, `doc_num`, `remark`, `notes`, `store_id`, `created_date`, `modified_date`, `status`,`is_approved`, `approved_by`, `is_posted`, `posted_by`, `posted_date`, `approved_date`,jv_amt,vendor_cust_id) values('"+seqnum+"','"+formattedDate+"','"+branch+"','"+username+"','"+seqnum+"','"+seqnum+"','"+seqnum+"','"+store+"','"+formattedDate+"','"+formattedDate+"','Posted','1','"+username+"','1','"+username+"','"+formattedDate+"','"+formattedDate+"','"+inv_amt+"','' )";
									statement.executeUpdate(save_jv);
									
									String queryjd="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,cheque_date) VALUES "
											+ "('"+seqnum+"','"+seqnum+"','"+inv_acc+"','"+inv_stock+"','"+ttl_cost+"','0','"+ponum+"','"+ponum+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"'),"
											+ "('"+seqnum+"','"+seqnum+"','"+cost_correction+"','"+costcorrection+"','0','"+ttl_cost+"','"+ponum+"','"+ponum+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"')";
									statement.executeUpdate(queryjd);
									
									String query38="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+inv_acc+"'"; 
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
									double bal_lia=bal_liable+ttl_cost;
									
									String query39="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+cost_correction+"'"; 
									Statement statement39=con.createStatement();  
							        ResultSet resultSet39= statement39.executeQuery(query39);
							        float cr_pay9=0;
							        float dr_pay9=0;
							        while(resultSet39.next())
							        {
							        	cr_pay9+=Float.parseFloat(resultSet39.getString("cr_amt"));
							        	dr_pay9+=Float.parseFloat(resultSet39.getString("dr_amt"));
							        }
									float bal_cost=dr_pay9-cr_pay9;
									double bal_cost_ttl=bal_cost-ttl_cost;
									
									
									
									String save_ledger="insert into "+company+".mr_acc_ledger (`jv_num`, `tran_date`, `post_date`, `ac_code`, `dr_amt`, `cr_amt`, `balance`, `notes`, `remark`, `source`, `created_date`, `modified_date`,status) values "
											+ "('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+cost_correction+"','0','"+ttl_price+"','"+bal_cost_ttl+"','"+ponum+"','"+ponum+"','TF','"+formattedDate+"','"+formattedDate+"','OPEN' ),"
											+ "('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+inv_acc+"','"+ttl_cost+"','0','"+bal_lia+"','"+ponum+"','"+ponum+"','TF','"+formattedDate+"','"+formattedDate+"','OPEN' ) ";
									statement.executeUpdate(save_ledger);
									
									String seq_num = null;
									String trans_seq = "select tran_seq from "+company+".mr_transaction_seq where tran_name='inventory receive' ";
									ResultSet resultSet12 = statement.executeQuery(trans_seq);
								    if (resultSet12.next())
								    {
								    	seq_num=resultSet12.getString("tran_seq");
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
								    	String save_po="update "+company+".mr_transaction_seq set tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_name='inventory receive' ";
										statement.executeUpdate(save_po);
								    }
								    
									
									String update_pl="update "+company+".mr_inventory_transfer_list set recv_no='"+seq_num+"',status='POSTED',modified_date='"+formattedDate+"' where tran_no ='"+ponum+"' ";
									statement.executeUpdate(update_pl);
									
									String copy_inv_tran="INSERT INTO "+company+".mr_inventory_transfer_details_history(`ref_tran_num`, `barcode`, `item_code`, `qtyOh`, `unit`, `cf`, `unit_cost`, `qty`, `total`, `created_date`, `modified_date`, `item_name`) SELECT `ref_tran_num`, `barcode`, `item_code`, `qtyOh`, `unit`, `cf`, `unit_cost`, `qty`, `total`, `created_date`, `modified_date`, `item_name` FROM "+company+".mr_inventory_transfer_details where ref_tran_num ='"+ponum+"' ";
									statement.executeUpdate(copy_inv_tran);
									
									
									String copy_inv_tran_list="INSERT INTO "+company+".mr_inventory_transfer_list_history( `com_date`, `store_from`, `store_to`, `tran_date`, `user_id`, `tran_no`,recv_no, `invo_nu`, `ttl_items`, `ttl_price`, `sub_ttl_amt`, `assume_ttl_cst`, `status`, `tran_type`, `created_date`, `modified_date`, `remark`) SELECT `com_date`, `store_from`, `store_to`, `tran_date`, `user_id`, `tran_no`,recv_no, `invo_nu`, `ttl_items`, `ttl_price`, `sub_ttl_amt`, `assume_ttl_cst`, `status`, `tran_type`, `created_date`, `modified_date`, `remark` FROM "+company+".mr_inventory_transfer_list where tran_no ='"+ponum+"' ";
									statement.executeUpdate(copy_inv_tran_list);
									
									if(page_type.equals("ISSUE"))
									{
										String tran_inv_tran_list="INSERT INTO "+tran_company+".mr_inventory_transfer_list (`com_date`,`store_from`,`store_to`,`tran_date`,`user_id`,`recv_no`,`tran_no`,`invo_nu`,`ttl_items`,`ttl_price`,`sub_ttl_amt`,`assume_ttl_cst`,`status`,`tran_type`,`created_date`,`modified_date`,`remark`) select `com_date`,`store_from`,`store_to`,`tran_date`,`user_id`,`tran_no`,`recv_no`,`invo_nu`,`ttl_items`,`ttl_price`,`sub_ttl_amt`,`assume_ttl_cst`,'OPEN','RECEIVE',`created_date`,`modified_date`,`remark` from "+company+".mr_inventory_transfer_list where tran_no ='"+ponum+"' ";
										statement.executeUpdate(tran_inv_tran_list);
										
										String tran_inv_tran="INSERT INTO "+tran_company+".mr_inventory_transfer_details (`barcode`,`cf`,`created_date`,`item_code`,`item_name`,`modified_date`,`qty`,`qtyOh`,`ref_tran_num`,`total`,`unit`,`unit_cost`) select `barcode`,`cf`,`created_date`,`item_code`,`item_name`,`modified_date`,`qty`,`qtyOh`,'"+seq_num+"',`total`,`unit`,`unit_cost` from "+company+".mr_inventory_transfer_details where ref_tran_num ='"+ponum+"' ";
										statement.executeUpdate(tran_inv_tran);
											
									}
									
									String del_inv_tran_list="delete from "+company+".mr_inventory_transfer_list where tran_no ='"+ponum+"' ";
									statement.executeUpdate(del_inv_tran_list);
									
									String del_inv_tran="delete from "+company+".mr_inventory_transfer_details where ref_tran_num ='"+ponum+"' ";
									statement.executeUpdate(del_inv_tran);
									
									
									frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					                JOptionPane.showMessageDialog(frame, "Inventory Transfer Posted Succesfully");
					                frame.setVisible(false);
					                inventory_transfer_list.get_po_disp(company,"OPEN", "",page_type,store);//db_name, type, search, page_type, store_name
								}
								else{
									JOptionPane.showMessageDialog(frame, "Grand Total is not Matching");
									frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								}
							}
							else{
								JOptionPane.showMessageDialog(frame, "This Adjustment Already Posted");
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
							}
						}
						else{
							JOptionPane.showMessageDialog(frame, "Some error occur please try again");
							frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}
						con.close();
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Recheck Your Values");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				} catch (Exception e) {
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		button.setBounds(228, 229, 83, 22);
		panel.add(button);
	}
}
