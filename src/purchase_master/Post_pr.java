package purchase_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Post_pr {

	public static JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private boolean dot = false; 
	private JComboBox<String> comboBox;
	private static DecimalFormat df2;
	private int decimal_count;
	private JProgressBar progressBar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Post_pr window = new Post_pr(null, null, null, null, null, null,0,null,null);
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
	 */
	public Post_pr(String company,String ponum, String inv_num, String inv_amt, String po_date, String version,int decimal,String username,String store) {
		initialize(company,ponum, inv_num, inv_amt, po_date, version,decimal,username,store);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param decimal 
	 * @param username 
	 */
	private void initialize(final String company,final String ponum,final String inv_num,final String inv_amt,final String po_date,final String version, final int decimal, final String username,final String store) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 337, 326);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		decimal_count=decimal;
		if(decimal_count==3)
		{
			df2=new DecimalFormat("0.000");
		}
		else
		{
			df2=new DecimalFormat("0.00");
		}
		
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	    final String formattedDate = myDateObj.format(myFormatObj);  
	    final Calendar cal = Calendar.getInstance();
        final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 331, 292);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 0, 205));
		progressBar.setBackground(new Color(192, 192, 192));
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 250, 215, 31);
		//progressBar.setVisible(false);
		panel.add(progressBar);
		
		JLabel lblPostPurchaseOrser = new JLabel("POST PURCHASE RETURN");
		lblPostPurchaseOrser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPostPurchaseOrser.setBounds(10, 11, 182, 14);
		panel.add(lblPostPurchaseOrser);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 46, 301, 207);
		panel.add(panel_1);
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setLayout(null);
		
		JLabel lblPoNumber = new JLabel("PR Number");
		lblPoNumber.setBounds(0, 25, 75, 14);
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
		
		JLabel lblPoDate = new JLabel("Posting Date");
		lblPoDate.setBounds(0, 142, 89, 14);
		panel_1.add(lblPoDate);
		lblPoDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textField = new JTextField(ponum.toUpperCase());
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				textField.setText(textField.getText().toUpperCase());
			}
		});
		textField.setBounds(118, 22, 159, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
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
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(118, 169, 159, 22);
		panel_1.add(comboBox);
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String qu="select * from "+company+".mr_acc_segment where segment='Branch' and store_id='"+store+"' ";
			ResultSet resultSet1 = statement.executeQuery(qu);
			while(resultSet1.next())
			{
				 String name=resultSet1.getString("name");
				 comboBox.addItem(name);
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		
		
		final JDateChooser dateChooser = new JDateChooser(new Date());
		dateChooser.setBounds(118, 136, 159, 20);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.getCalendarButton().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Date date = new Date();
						dateChooser.getJCalendar().setMaxSelectableDate(date);
						dateChooser.setDate(date);
					}
				});
		panel_1.add(dateChooser);
		
		JLabel lblBranch = new JLabel("Branch");
		lblBranch.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBranch.setBounds(0, 172, 56, 16);
		panel_1.add(lblBranch);
		
		Button button = new Button("Proceed ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				progressBar.setVisible(true);
				double number_progress=0;
				int b=1;
				String start_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				double inv_amount=Double.parseDouble(inv_amt);
				double amount=Double.parseDouble(textField_2.getText());
				if(textField.getText().equals(ponum) && textField_1.getText().equals(inv_num) && amount==inv_amount )
				{
					if(start_date.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Select A Posting Date");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
					else
					{
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement;
						try {
							statement = con.createStatement();
							Statement statement155 = con.createStatement();
							ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_purchase_return_list where pr_no='"+ponum+"' ");
							if(resultSet.next())
							{
								if(resultSet.getString("status").equals("OPEN"))
								{
									double ttl_price=Double.parseDouble(resultSet.getString("ttl_price"));
									double grand_ttl=Double.parseDouble(resultSet.getString("sub_ttl_amt"));
									double grand_total=Double.parseDouble(resultSet.getString("grand_total"));
									double total_vat=Double.parseDouble(resultSet.getString("total_vat"));
									String vendor_id=resultSet.getString("vendor_id");
									String po_date=resultSet.getString("pr_date");
									String query444="SELECT * FROM "+company+".mr_vendor where vendor_code='"+vendor_id+"'"; 
									Statement statement121=con.createStatement();  
					                ResultSet resultSet121 = statement121.executeQuery(query444);
					                String vendor_name="";
					                if(resultSet121.next())
					                {
					                	vendor_name=resultSet121.getString("vendor_name");
					                }
									String inv_acc_code="";
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
										double grand_disc=Double.parseDouble(resultSet.getString("grand_disc"));
										double double_discont=0;
										
//										String query3="SELECT * FROM "+company+".mr_purchase_return where ref_pr_num='"+ponum+"'"; 
//						                Statement statement3=con.createStatement();  
//						                ResultSet resultSet3 = statement3.executeQuery(query3);
						               // String disc_perc="0";
//						                double dt=0;
//						                while(resultSet3.next())
//						                {
//						                	double unitdisc=Double.parseDouble(resultSet3.getString("itm_disc"));
//						                	double total = Double.parseDouble(resultSet3.getString("itm_cost_after_disc"));
//											if(unitdisc!=0)
//											{
//												dt+=total;
//											}
//						                }
										
										double count=0;
										String qury5="select count(*) as ttl FROM "+company+".mr_purchase_return where ref_pr_num='"+ponum+"'";
										ResultSet resultSet53 = statement155.executeQuery(qury5);
										if(resultSet53.next())
										{
											count=Double.parseDouble(resultSet53.getString("ttl"));
											number_progress=100/count;
											
										}
						                String query2="SELECT * FROM "+company+".mr_purchase_return where ref_pr_num='"+ponum+"'"; 
						                Statement statement2=con.createStatement();  
						                ResultSet resultSet2 = statement2.executeQuery(query2);
						                while(resultSet2.next())
						                {
						                	
						                	String unit_price="0";
						                //	double total=grand_ttl-dt;
						                	String tax="0";
						                	String taxrate = "0";
						                	String itm_code=resultSet2.getString("item_code");
						                	String total_qty = "0";
						                	String item_cost = "0";
						                	String query1="SELECT acc_code,input_tax,item_cost,total_qty FROM "+company+".mr_item_master where item_code='"+itm_code+"'"; 
							                Statement statement1=con.createStatement();  
							                ResultSet resultSet1 = statement1.executeQuery(query1);
							                if(resultSet1.next())
							                {
							                	inv_acc_code=resultSet1.getString("acc_code");
							                	tax=resultSet1.getString("input_tax");
							                	item_cost=resultSet1.getString("item_cost");
							                	total_qty=resultSet1.getString("total_qty");
							                	String query4="SELECT * FROM "+company+".mr_tax where tax_code='"+tax+"'"; 
								                ResultSet resultSet4 = statement1.executeQuery(query4);
								                if(resultSet4.next())
								                {
								                	taxrate=resultSet4.getString("tax_rate");
								                }
							                	
							                }
							                String itm_cost_after_disc=resultSet2.getString("itm_cost_after_disc");
						                	if(grand_disc!=0)
											{
												
												//disc_perc="0";
												if(double_discont==0)
												{
													/* Double discount not valid*/
													
													double unitdisc=Double.parseDouble(resultSet2.getString("itm_disc"));
													if(unitdisc==0)
													{
														String qty=resultSet2.getString("qty");
														String cf=resultSet2.getString("cf");
														//disc_perc=df2.format((grand_disc/total)*100);
														String unit_total=df2.format(Double.parseDouble(itm_cost_after_disc)-(Double.parseDouble(itm_cost_after_disc)*grand_disc)/100);
														String vat_val=df2.format((Double.parseDouble(unit_total)*Double.parseDouble(taxrate))/100);
														double after_subttl_disc=Double.parseDouble(unit_total)+Double.parseDouble(vat_val);
														//unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														String query5="update "+company+".mr_purchase_return set sub_ttl_disc='"+grand_disc+"',sub_cost_after_sub_ttl='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl='"+unit_price+"' where ref_pr_num='"+ponum+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' ";
														statement.executeUpdate(query5);
													}
													else
													{
														String qty=resultSet2.getString("qty");
														String cf=resultSet2.getString("cf");
														String unit_total=df2.format(Double.parseDouble(itm_cost_after_disc)-(Double.parseDouble(itm_cost_after_disc)*grand_disc)/100);
														String vat_val=df2.format((Double.parseDouble(unit_total)*Double.parseDouble(taxrate))/100);
														double after_subttl_disc=Double.parseDouble(unit_total)+Double.parseDouble(vat_val);
														//unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														String query5="update "+company+".mr_purchase_return set sub_ttl_disc='"+grand_disc+"',sub_cost_after_sub_ttl='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl='"+unit_price+"' where ref_pr_num='"+ponum+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' ";
														statement.executeUpdate(query5);
													}
												}
												else
												{
													String qty=resultSet2.getString("qty");
													String cf=resultSet2.getString("cf");
													//disc_perc=df2.format((grand_disc/total)*100);
													String unit_total=df2.format(Double.parseDouble(itm_cost_after_disc)-(Double.parseDouble(itm_cost_after_disc)*grand_disc)/100);
													String vat_val=df2.format((Double.parseDouble(unit_total)*Double.parseDouble(taxrate))/100);
													double after_subttl_disc=Double.parseDouble(unit_total)+Double.parseDouble(vat_val);
													//unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
													unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
													String query5="update "+company+".mr_purchase_return set sub_ttl_disc='"+grand_disc+"',sub_cost_after_sub_ttl='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl='"+unit_price+"' where ref_pr_num='"+ponum+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' ";
													statement.executeUpdate(query5);
												}
												
											}	
											else
											{
												
												//disc_perc="0";
												if(double_discont==0)
												{
													/* Double discount not valid*/
													
													double unitdisc=Double.parseDouble(resultSet2.getString("itm_disc"));
													if(unitdisc==0)
													{
														String qty=resultSet2.getString("qty");
														String cf=resultSet2.getString("cf");
														grand_disc=0;
														String unit_total=df2.format(Double.parseDouble(itm_cost_after_disc)-(Double.parseDouble(itm_cost_after_disc)*grand_disc)/100);
														String vat_val=df2.format((Double.parseDouble(unit_total)*Double.parseDouble(taxrate))/100);
														double after_subttl_disc=Double.parseDouble(unit_total)+Double.parseDouble(vat_val);
														//unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														String query5="update "+company+".mr_purchase_return set sub_ttl_disc='"+grand_disc+"',sub_cost_after_sub_ttl='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl='"+unit_price+"' where ref_pr_num='"+ponum+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' ";
														statement.executeUpdate(query5);
													}
													else
													{
														String qty=resultSet2.getString("qty");
														String cf=resultSet2.getString("cf");
														String unit_total=df2.format(Double.parseDouble(itm_cost_after_disc)-(Double.parseDouble(itm_cost_after_disc)*grand_disc)/100);
														String vat_val=df2.format((Double.parseDouble(unit_total)*Double.parseDouble(taxrate))/100);
														double after_subttl_disc=Double.parseDouble(unit_total)+Double.parseDouble(vat_val);
														//unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														String query5="update "+company+".mr_purchase_return set sub_ttl_disc='"+grand_disc+"',sub_cost_after_sub_ttl='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl='"+unit_price+"' where ref_pr_num='"+ponum+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' ";
														statement.executeUpdate(query5);
													}
												}
												else
												{
													String qty=resultSet2.getString("qty");
													String cf=resultSet2.getString("cf");
													//disc_perc=df2.format((grand_disc/total)*100);
													String unit_total=df2.format(Double.parseDouble(itm_cost_after_disc)-(Double.parseDouble(itm_cost_after_disc)*grand_disc)/100);
													String vat_val=df2.format((Double.parseDouble(unit_total)*Double.parseDouble(taxrate))/100);
													double after_subttl_disc=Double.parseDouble(unit_total)+Double.parseDouble(vat_val);
													//unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
													unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
													String query5="update "+company+".mr_purchase_return set sub_ttl_disc='"+grand_disc+"',sub_cost_after_sub_ttl='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl='"+unit_price+"' where ref_pr_num='"+ponum+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' ";
													statement.executeUpdate(query5);
												}
												
											}
						                	String avg_cst=item_cost;
											String avg_qty=total_qty;
											//$total=$itm_get->total;
											String qty=resultSet2.getString("qty");
											String cf=resultSet2.getString("cf");
											String unit=resultSet2.getString("unit");
											float ttl_qty=Float.parseFloat(qty)*Integer.parseInt(cf);
											String new_qty=df2.format(Float.parseFloat(avg_qty) - ttl_qty);
											double new_avg;
											if(Double.parseDouble(new_qty) <= 0)
											{
												new_avg=Double.parseDouble(unit_price);	
											}
											else
											{
												new_avg=((Double.parseDouble(avg_cst)*Float.parseFloat(avg_qty))-(Double.parseDouble(unit_price)*ttl_qty))/(Float.parseFloat(new_qty));	
											}
											String suhail=df2.format(new_avg);
											/*
											 item master
											 item_cost=new_avg
											 qty= new_qty
											 last cost= unit_price
											 
											 
											 item history insertion
											 */
											String query5="update "+company+".mr_item_master set total_qty='"+new_qty+"',item_cost='"+suhail+"',item_lastcost='"+unit_price+"',modified_date='"+formattedDate+"' where item_code='"+itm_code+"' ";
											statement.executeUpdate(query5);
											
											String query55="update "+company+".mr_tmp_item_master set qty='"+new_qty+"',cost='"+suhail+"' where item_code='"+itm_code+"' ";
											statement.executeUpdate(query55);
											
											
											String query11="insert into "+company+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) values('"+itm_code+"','PR','"+ponum+"','"+qty+"','"+unit+"','"+cf+"','"+unit_price+"','"+(Double.parseDouble(unit_price)*ttl_qty)+"','0','0','0','Recieved "+ponum+"','"+username+"','"+po_date+"','"+resultSet2.getString("barcode")+"','"+vendor_id+"','"+vendor_name+"')";
											statement.executeUpdate(query11);
											
											
											String query12="select qty,cf from "+company+".mr_item_master_history where itemcode='"+itm_code+"' and created_date >= '"+po_date+"' and (trans_type='SA' or trans_type='IN')  ";
											ResultSet resultSet12 = statement.executeQuery(query12);
											if(resultSet12.next())
											{
												String query13="update "+company+".mr_item_master_history set cost='"+suhail+"',ttl_cost=(cost*cf*qty) where itemcode='"+itm_code+"' and created_date >= '"+po_date+"' and (trans_type='SA' or trans_type='IN')  ";
												statement.executeUpdate(query13);
											}
											String query14="select item_cost from "+company+".mr_pos_settlement_cash_items where item_code='"+itm_code+"' and created_date >= '"+po_date+"' ";
											ResultSet resultSet14 = statement.executeQuery(query14);
											if(resultSet14.next())
											{
												String query13="update "+company+".mr_pos_settlement_cash_items set item_cost='"+suhail+"' where item_code='"+itm_code+"' and created_date >= '"+po_date+"' ";
												statement.executeUpdate(query13);
											}
											String query16="select item_cost from "+company+".mr_pos_settlement_history_items where item_code='"+itm_code+"' and created_date >= '"+po_date+"' ";
											ResultSet resultSet16 = statement.executeQuery(query16);
											if(resultSet16.next())
											{
												String query17="update "+company+".mr_pos_settlement_history_items set item_cost='"+suhail+"' where item_code='"+itm_code+"' and created_date >= '"+po_date+"' ";
												statement.executeUpdate(query17);
											}
											String query18="select cost from "+company+".mr_pos_hold_invoice_items where item_code='"+itm_code+"' and created_date >= '"+po_date+"' ";
											ResultSet resultSet18 = statement.executeQuery(query18);
											if(resultSet18.next())
											{
												String query19="update "+company+".mr_pos_hold_invoice_items set cost='"+suhail+"' where item_code='"+itm_code+"' and created_date >= '"+po_date+"' ";
												statement.executeUpdate(query19);
											}
											String query20="select item_cost from "+company+".mr_sd_lines where item_code='"+itm_code+"' and created_date >= '"+po_date+"' ";
											ResultSet resultSet20 = statement.executeQuery(query20);
											if(resultSet20.next())
											{
												String query19="update "+company+".mr_sd_lines set item_cost='"+suhail+"' where item_code='"+itm_code+"' and created_date >= '"+po_date+"' ";
												statement.executeUpdate(query19);
											}
											String query21="select item_cost from "+company+".mr_sd_lines_history where item_code='"+itm_code+"' and created_date >= '"+po_date+"' ";
											ResultSet resultSet21 = statement.executeQuery(query21);
											if(resultSet21.next())
											{
												String query19="update "+company+".mr_sd_lines_history set item_cost='"+suhail+"' where item_code='"+itm_code+"' and created_date >= '"+po_date+"' ";
												statement.executeUpdate(query19);
											}
											double ttl_count=b*number_progress;
											 Thread.sleep(50);
						                	 progressBar.paintImmediately(0, 0, 854, 31);
						                	 progressBar.setValue((int)ttl_count);
						                	 b++;
						                }
						                /*
										 purchase tax
										po num
										po total
										po date
										supp invoice
										tax amt
										 */
										double sub_total=grand_total-total_vat;
										BigDecimal bd = new BigDecimal(sub_total);
				                		bd = bd.setScale(decimal_count, BigDecimal.ROUND_HALF_UP);
				                		sub_total = bd.doubleValue();
										String query5="insert into "+company+".mr_purchase_return_tax(`ref_pr_no`,  `pr_total`, `pr_date`, `inv_num`, `tax_amt`, `created_date`) values ('"+ponum+"','"+sub_total+"','"+po_date+"','"+inv_num+"','"+total_vat+"','"+formattedDate+"')";
										statement.executeUpdate(query5);

										/*
										vendor
										purch amt
										pending amt
										*/
										String vendor_payable_ac="";
										String query4="SELECT * FROM "+company+".mr_vendor where vendor_code='"+vendor_id+"'"; 
										Statement statement1=con.createStatement();  
						                ResultSet resultSet1 = statement1.executeQuery(query4);
						                if(resultSet1.next())
						                {
						                	vendor_payable_ac=resultSet1.getString("vendor_payable_ac");
						                	String vend_purch_amt=resultSet1.getString("vendor_purch_amt");
						                	String vend_pend_amt=resultSet1.getString("vendor_pending_amt");
						                	double new_po_amt=Double.parseDouble(vend_purch_amt)-grand_total;
						                	BigDecimal be = new BigDecimal(new_po_amt);
					                		be = be.setScale(decimal_count, BigDecimal.ROUND_HALF_UP);
					                		new_po_amt = be.doubleValue();
						                	double new_po_pend_amt=Double.parseDouble(vend_pend_amt)-grand_total;
						                	BigDecimal bc = new BigDecimal(new_po_pend_amt);
					                		bc = bc.setScale(decimal_count, BigDecimal.ROUND_HALF_UP);
					                		new_po_pend_amt = bc.doubleValue();
						                	String query6="update "+company+".mr_vendor set vendor_purch_amt='"+new_po_amt+"',vendor_pending_amt='"+new_po_pend_amt+"' where vendor_code ='"+vendor_id+"' ";
											statement.executeUpdate(query6);
						                }
						                String insert_acc_header="insert into "+company+".mr_accounts_trans_header (tran_seqencecode,tran_date,tran_time,tran_remark,tran_debit_amt,tran_credit_amt,tran_status,tran_user,tran_store,tran_orgin,created_date,modified_date) values('"+ponum+"','"+po_date+"','"+times.format(cal.getTime())+"','SUP.INVOICE NO. "+inv_num+"','"+grand_total+"','"+grand_total+"','POSTED','"+username+"','"+store+"','PO','"+formattedDate+"','"+formattedDate+"' )";
						                statement.executeUpdate(insert_acc_header);
						                
						                String def_payable=vendor_payable_ac;
//						                String query15="SELECT * FROM "+company+".mr_assign_account_group where group_code='"+vendor_payable_ac+"' and acc_store='"+store+"' and status='1' "; 
//										Statement statement5=con.createStatement();  
//						                ResultSet resultSet5 = statement5.executeQuery(query15);
//						                if(resultSet5.next())
//						                {
//						                	def_payable=resultSet5.getString("def_payable");
//						                }
						                
						                String inv_acc="";
						                String query26="SELECT * FROM "+company+".mr_assign_account_group where group_code='"+inv_acc_code+"' and acc_store='"+store+"' and status='1' "; 
										Statement statement26=con.createStatement();  
						                ResultSet resultSet26 = statement26.executeQuery(query26);
						                if(resultSet26.next())
						                {
						                	inv_acc=resultSet26.getString("inv_acc");
						                }
						                
						                
						                String query25="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_code='"+inv_acc+"' and acc_store='"+store+"'"; 
										Statement statement25=con.createStatement();  
						                ResultSet resultSet25 = statement25.executeQuery(query25);
						                String acc_name="";
						                if(resultSet25.next())
						                {
						                	acc_name=resultSet25.getString("acc_name");
						                }
						                
						                String query16="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_code='"+def_payable+"' and acc_store='"+store+"'"; 
										Statement statement6=con.createStatement();  
						                ResultSet resultSet6= statement6.executeQuery(query16);
						                String acc_pay="";
						                if(resultSet6.next())
						                {
						                	acc_pay=resultSet6.getString("acc_name");
						                }
						                
						               

										String seq_num=null;
										String trans_seq = "select tran_seq from "+company+".mr_transaction_seq where tran_orgin='JV' ";
										ResultSet resultSet10 = statement.executeQuery(trans_seq);
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
											statement.executeUpdate(save_po);
									    }
									    
									    String tax_applied="";
						                String tax_jv="";
						                String tax_leger="";
						                String query18="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_grp_name='VAT 5% ON PURCHASE' and acc_store='"+store+"'"; 
										Statement statement18=con.createStatement();  
						                ResultSet resultSet18= statement18.executeQuery(query18);
						                String tax_name="";
						                String tax_code="";
						                if(resultSet18.next())
						                {
						                	tax_name=resultSet18.getString("acc_name");
						                	tax_code=resultSet18.getString("acc_code");
						                }
						                tax_applied=",('"+ponum+"','"+po_date+"','"+times.format(cal.getTime())+"','ASSET','"+tax_code+"','"+tax_name+"','0','"+total_vat+"','SUP.INVOICE NO. "+inv_num+"','"+formattedDate+"','"+formattedDate+"')";
						                tax_jv=",('"+seq_num+"','"+seq_num+"','"+tax_code+"','"+tax_name+"','0','"+total_vat+"','"+ponum+"','"+inv_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"')";
						                
						                String query29="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+tax_code+"'"; 
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
										double bal_taxable=bal_tax-total_vat;
						                tax_leger=",('"+seq_num+"','"+formattedDate+"','"+formattedDate+"','"+tax_code+"','0','"+total_vat+"','"+bal_taxable+"','"+ponum+"','"+inv_num+"','PR','"+formattedDate+"','"+formattedDate+"','OPEN')";
						                
									    
						                String insert_acc_detls="insert into "+company+".mr_accounts_tran_detls (tran_seqence_code,tran_date,tran_time,tran_type,tran_accode,tran_acname,tran_debit_amt,tran_credit_amt,tran_remark,created_date,modified_date) values('"+ponum+"','"+po_date+"','"+times.format(cal.getTime())+"','ASSET','"+inv_acc+"','"+acc_name+"','0','"+sub_total+"','SUP.INVOICE NO. "+inv_num+"','"+formattedDate+"','"+formattedDate+"'),('"+ponum+"','"+po_date+"','"+times.format(cal.getTime())+"','LIABILITY','"+def_payable+"','"+acc_pay+"','"+grand_total+"','0','SUP.INVOICE NO. "+inv_num+"','"+formattedDate+"','"+formattedDate+"') "+tax_applied+" ";
										statement.executeUpdate(insert_acc_detls);
										
										String save_po="insert into "+company+".mr_jv_header( `jv_num`, `date`, `branch`, `username`, `doc_num`, `remark`, `notes`, `store_id`, `created_date`, `modified_date`, `status`,`is_approved`, `approved_by`, `is_posted`, `posted_by`, `posted_date`, `approved_date`,jv_amt,vendor_cust_id) values('"+seq_num+"','"+formattedDate+"','"+comboBox.getSelectedItem().toString()+"','"+username+"','"+inv_num+"','"+ponum+"','"+ponum+"','"+store+"','"+formattedDate+"','"+formattedDate+"','Posted','1','"+username+"','1','"+username+"','"+formattedDate+"','"+formattedDate+"','"+grand_total+"','"+vendor_id+"' )";
										statement.executeUpdate(save_po);
										
										String query1="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,cheque_date) VALUES ('"+seq_num+"','"+seq_num+"','"+inv_acc+"','"+acc_name+"','0','"+sub_total+"','"+ponum+"','"+inv_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"'),('"+seq_num+"','"+seq_num+"','"+def_payable+"','"+acc_pay+"','"+grand_total+"','0','"+ponum+"','"+inv_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"') "+tax_jv+" ";
										statement.executeUpdate(query1);
										
										String query17="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+inv_acc+"'"; 
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
										double bal=bal_asset-sub_total;
										
										String query20="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+def_payable+"'"; 
										Statement statement8=con.createStatement();  
						                ResultSet resultSet8= statement8.executeQuery(query20);
						                float cr_pay=0;
						                float dr_pay=0;
						                while(resultSet8.next())
						                {
						                	cr_pay+=Float.parseFloat(resultSet8.getString("cr_amt"));
						                	dr_pay+=Float.parseFloat(resultSet8.getString("dr_amt"));
						                }
										float bal_liable=dr_pay-cr_pay;
										double bal_lia=bal_liable+grand_total;
										
										String save_ledger="insert into "+company+".mr_acc_ledger (`jv_num`, `tran_date`, `post_date`, `ac_code`, `dr_amt`, `cr_amt`, `balance`, `notes`, `remark`, `source`, `created_date`, `modified_date`,status) values ('"+seq_num+"','"+formattedDate+"','"+formattedDate+"','"+inv_acc+"','0','"+sub_total+"','"+bal+"','"+ponum+"','"+inv_num+"','PR','"+formattedDate+"','"+formattedDate+"','OPEN' ),('"+seq_num+"','"+formattedDate+"','"+formattedDate+"','"+def_payable+"','"+grand_total+"','0','"+bal_lia+"','"+ponum+"','"+inv_num+"','PR','"+formattedDate+"','"+formattedDate+"','OPEN' ) "+tax_leger+"";
										statement.executeUpdate(save_ledger);
						                
//						                String insert_acc_detls="insert into "+company+".mr_accounts_tran_detls (tran_seqence_code,tran_date,tran_time,tran_type,tran_accode,tran_acname,tran_debit_amt,tran_credit_amt,tran_remark,created_date,modified_date) values('"+ponum+"','"+po_date+"','"+times.format(cal.getTime())+"','ASSET','0100101001','INVENTORY AT STORE "+store+"','0','"+grand_total+"','SUP.INVOICE NO. "+inv_num+"','"+formattedDate+"','"+formattedDate+"'),('"+ponum+"','"+po_date+"','"+times.format(cal.getTime())+"','LIABILITY','0200101001','VENDOR PAYABLE "+store+"','"+grand_total+"','0','SUP.INVOICE NO. "+inv_num+"','"+formattedDate+"','"+formattedDate+"') ";
//										statement.executeUpdate(insert_acc_detls);

										String insert_vendor_summery="insert into "+company+".mr_vendor_summary(ref_vendor_id,ref_po_num,created_date)values('"+vendor_id+"','"+ponum+"','"+formattedDate+"')";
										statement.executeUpdate(insert_vendor_summery);
										
										//String vendor_payment="insert INTO "+company+".mr_vendor_return_payment(`ref_pr_no`, `ref_vendor_id`, `amount`, `status`, store ,`created_date`, `modified_date`) VALUES ('"+ponum+"','"+vendor_id+"','"+grand_total+"','0','"+store+"','"+formattedDate+"','"+formattedDate+"')";
										//statement.executeUpdate(vendor_payment);
										
										String vendor_payments="insert INTO "+company+".mr_vendor_payment(`ref_po_num`, `invo_num`, `invo_date`, `invo_amt`, `paid_amt`, `bal_amt`, `pay_terms`, `due_days`, `created_date`, `modified_date`,ref_vendor_id,store_id,status,branch) VALUES('"+ponum+"','"+inv_num+"','"+po_date+"','"+grand_total+"','0','"+grand_total+"','CASH','0','"+formattedDate+"','"+formattedDate+"','"+vendor_id+"','"+store+"','2','"+comboBox.getSelectedItem().toString()+"')";
										statement.executeUpdate(vendor_payments);
										
										String vendor_payment_dt="insert INTO "+company+".mr_vendor_payment_details(`ref_po_num`,trans_num, `ref_vendor_id`, `cr_amt`, `dr_amt`, `store_id`, `username`, `created_date`, `modified_date`,issue_date,status,invoicetype) VALUES('"+ponum+"','"+ponum+"','"+vendor_id+"','0','"+grand_total+"','"+store+"','"+username+"','"+formattedDate+"','"+formattedDate+"','"+start_date+"','OPEN','PR' )";
										statement.executeUpdate(vendor_payment_dt);
										
										String update_pl="update "+company+".mr_purchase_return_list set status='POSTED' where pr_no ='"+ponum+"' ";
										statement.executeUpdate(update_pl);
						                JOptionPane.showMessageDialog(frame, "PR Posted Successfully");
						                frame.setVisible(false);
						                purchase_return_list.get_po_disp(company, "OPEN", "");
									}
									else{
										frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
										JOptionPane.showMessageDialog(frame, "Grand Total is not Matching");
										
									}
								}
								else{
									frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
									JOptionPane.showMessageDialog(frame, "This Item Already Posted");
								}
							}
							else{
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								JOptionPane.showMessageDialog(frame, "Some error occur please try again");
							}
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Recheck Your Values");
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
				
			}
		});
		button.setBounds(228, 250, 83, 31);
		panel.add(button);
	}
}
