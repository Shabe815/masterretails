package purchase_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JComboBox;

public class post_po {

	public static JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private boolean dot = false; 
	private static DecimalFormat df2;
	private int decimal_count;
	private JTextField textField_3;
	private JComboBox<String> comboBox;
	private JProgressBar progressBar;
	private static String POcost="";
	private static String ItemTax="";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					post_po window = new post_po("mr_pro", "PO-19-01-000001", "987456", "41.5", "2019-05-09", null,0,null,"S01","");
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
	public post_po(String company,String ponum, String inv_num, String string, String po_date, String version,int decimal,String username,String store, String language) {
		initialize(company,ponum, inv_num, string, po_date, version,decimal,username,store,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param decimal 
	 * @param username 
	 * @param language 
	 */
	private void initialize(final String company,final String ponum,final String inv_num,final String inv_amt,final String po_date,final String version, final int decimal, final String username,final String store,final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 337, 374);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setAlwaysOnTop(true);
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
		panel.setBounds(0, 0, 331, 339);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 0, 205));
		progressBar.setBackground(new Color(192, 192, 192));
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 298, 225, 31);
		progressBar.setVisible(false);
		panel.add(progressBar);
		
		
		JLabel lblPostPurchaseOrser = new JLabel("POST PURCHASE ORDER");
		lblPostPurchaseOrser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPostPurchaseOrser.setBounds(10, 11, 182, 14);
		panel.add(lblPostPurchaseOrser);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 46, 301, 255);
		panel.add(panel_1);
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setLayout(null);
		
		JLabel lblPoNumber = new JLabel("PO Number");
		lblPoNumber.setBounds(0, 23, 75, 14);
		panel_1.add(lblPoNumber);
		lblPoNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblInvoiceNumber = new JLabel("Invoice Number");
		lblInvoiceNumber.setBounds(0, 60, 89, 14);
		panel_1.add(lblInvoiceNumber);
		lblInvoiceNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblInvoiceAmount = new JLabel("Invoice Amount");
		lblInvoiceAmount.setBounds(0, 97, 129, 14);
		panel_1.add(lblInvoiceAmount);
		lblInvoiceAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblPoDate = new JLabel("Posting Date");
		lblPoDate.setBounds(0, 134, 89, 14);
		panel_1.add(lblPoDate);
		lblPoDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textField = new JTextField(ponum.toUpperCase());
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				textField.setText(textField.getText().toUpperCase());
			}
		});
		textField.setBounds(118, 18, 159, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(118, 56, 159, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(inv_amt);
		textField_2.setBounds(118, 94, 159, 20);
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
		
		final JDateChooser dateChooser = new JDateChooser(new Date());
		dateChooser.setBounds(118, 132, 159, 20);
		dateChooser.getCalendarButton().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Date date = new Date();
						dateChooser.getJCalendar().setMaxSelectableDate(date);
						dateChooser.setDate(date);
					}
				});
		dateChooser.setDateFormatString("yyyy-MM-dd");
		panel_1.add(dateChooser);
		java.util.Date date =null;
		if(po_date!="")
		{
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(po_date);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		dateChooser.setDate(date);
		
		JLabel lblReferance = new JLabel("Referance");
		lblReferance.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblReferance.setBounds(0, 171, 102, 16);
		panel_1.add(lblReferance);
		
		textField_3 = new JTextField();
		textField_3.setBounds(116, 170, 161, 22);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblBranch = new JLabel("Branch");
		lblBranch.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBranch.setBounds(0, 210, 56, 16);
		panel_1.add(lblBranch);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(118, 210, 159, 22);
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
						JOptionPane.showMessageDialog(frame, "<html>Select A Posting Date<br>Õœœ  «—ÌŒ «·‰‘—</html>");
					}
					else
					{
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement;
						try {
							statement = con.createStatement();
							Statement statement155 = con.createStatement();
							ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_purchase_list where po_no='"+ponum+"' ");
							if(resultSet.next())
							{
								if(resultSet.getString("status").equals("OPEN"))
								{
									String tax_amt="5";//resultSet.getString("vat_perc");
									double ttl_price=Double.parseDouble(resultSet.getString("ttl_price"));
									double grand_ttl=Double.parseDouble(resultSet.getString("sub_ttl_amt"));
									double grand_total=Double.parseDouble(resultSet.getString("grand_total"));
									double total_vat=Double.parseDouble(resultSet.getString("total_vat"));
									String po_terms=resultSet.getString("po_terms");
									String po_date=resultSet.getString("po_date");
									String vendor_id=resultSet.getString("vendor_id");
									String query444="SELECT * FROM "+company+".mr_vendor where vendor_code='"+vendor_id+"'"; 
									Statement statement121=con.createStatement();  
					                ResultSet resultSet121 = statement121.executeQuery(query444);
					                String vendor_name="";
					                if(resultSet121.next())
					                {
					                	vendor_name=resultSet121.getString("vendor_name");
					                }
									String inv_acc_code="GENERAL";
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
										double count=0;
										String qury5="select count(*) as ttl FROM "+company+".mr_purchase_order where ref_po_num='"+ponum+"'";
										ResultSet resultSet53 = statement155.executeQuery(qury5);
										if(resultSet53.next())
										{
											count=Double.parseDouble(resultSet53.getString("ttl"));
											number_progress=100/count;
											
										}
										double grand_disc=Double.parseDouble(resultSet.getString("grand_disc"));
										double double_discont=Double.parseDouble(resultSet.getString("double_discont"));
										String query2="SELECT * FROM "+company+".mr_purchase_order where ref_po_num='"+ponum+"'"; 
						                Statement statement2=con.createStatement();  
						                ResultSet resultSet2 = statement2.executeQuery(query2);
						                while(resultSet2.next())
						                {
						                	
						                	String unit_price="0";
						                	//double total=grand_ttl-dt;
						                	String tax=resultSet2.getString("input_tax");
						                	String taxrate = "0";
						                	String itm_code=resultSet2.getString("item_code");
						                	//String sp=resultSet2.getString("sp");
						                	String tax_type=resultSet2.getString("tax_type");
						                	String total_qty = "0";
						                	String item_cost = "0";
						                	
						                	if(!tax.equals(""))
											{
												Statement statement1=con.createStatement();
												String query4="SELECT * FROM "+company+".mr_tax where tax_code='"+tax+"'"; 
												ResultSet resultSet4 = statement1.executeQuery(query4);
												if(resultSet4.next())
												{
													taxrate=resultSet4.getString("tax_rate");
												}
											}
						                	
						                	String query1="SELECT * FROM "+company+".mr_tmp_item_master where item_code='"+itm_code+"'"; 
							                Statement statement1=con.createStatement();  
							                ResultSet resultSet1 = statement1.executeQuery(query1);
							                if(resultSet1.next())
							                {
							                	item_cost=resultSet1.getString("cost");
							                	total_qty=resultSet1.getString("qty");
							                }
							                String itm_cost_after_disc=resultSet2.getString("itm_cost_after_disc");
						                	if(grand_disc!=0)
											{
												
												//disc_perc=0;
												if(double_discont==0)
												{
													/* Double discount not valid*/
													
													double unitdisc=Double.parseDouble(resultSet2.getString("itm_disc"));
													if(unitdisc==0)
													{
														String qty=resultSet2.getString("qty");
														String cf=resultSet2.getString("cf");
														//disc_perc=grand_disc;//df2.format((grand_disc/total)*100);
														String unit_total=df2.format(Double.parseDouble(itm_cost_after_disc)-(Double.parseDouble(itm_cost_after_disc)*grand_disc)/100);
														String vat_val=df2.format((Double.parseDouble(unit_total)*Double.parseDouble(taxrate))/100);
														double after_subttl_disc=Double.parseDouble(unit_total);//+Double.parseDouble(vat_val);
														if(ItemTax.equals("0"))
														{
															unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														}
														else
														{
															unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														}
														
														
														String query5="update "+company+".mr_purchase_order set sub_ttl_disc='"+grand_disc+"',sub_cost_after_sub_ttl_disc='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl_disc='"+unit_price+"' where ref_po_num='"+ponum+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' and id='"+resultSet2.getString("id")+"' ";
														statement.executeUpdate(query5);
													}
													else
													{
														String qty=resultSet2.getString("qty");
														String cf=resultSet2.getString("cf");
														String unit_total=df2.format(Double.parseDouble(itm_cost_after_disc)-(Double.parseDouble(itm_cost_after_disc)*grand_disc)/100);
														String vat_val=df2.format((Double.parseDouble(unit_total)*Double.parseDouble(taxrate))/100);
														double after_subttl_disc=Double.parseDouble(unit_total);//+Double.parseDouble(vat_val);
														if(ItemTax.equals("0"))
														{
															unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														}
														else
														{
															unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														}
														
														
														
														String query5="update "+company+".mr_purchase_order set sub_ttl_disc='"+grand_disc+"',sub_cost_after_sub_ttl_disc='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl_disc='"+unit_price+"' where ref_po_num='"+ponum+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' and id='"+resultSet2.getString("id")+"'";
														statement.executeUpdate(query5);
													}
												}
												else
												{
													String qty=resultSet2.getString("qty");
													String cf=resultSet2.getString("cf");
													String unit_total=df2.format(Double.parseDouble(itm_cost_after_disc)-(Double.parseDouble(itm_cost_after_disc)*grand_disc)/100);
													String vat_val=df2.format((Double.parseDouble(unit_total)*Double.parseDouble(taxrate))/100);
													double after_subttl_disc=Double.parseDouble(unit_total);//+Double.parseDouble(vat_val);
													if(ItemTax.equals("0"))
													{
														unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
													}
													else
													{
														unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
													}
													
													
													String query5="update "+company+".mr_purchase_order set sub_ttl_disc='"+grand_disc+"',sub_cost_after_sub_ttl_disc='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl_disc='"+unit_price+"' where ref_po_num='"+ponum+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' and id='"+resultSet2.getString("id")+"' ";
													statement.executeUpdate(query5);
												}
												
											}	
											else
											{
												
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
														double after_subttl_disc=Double.parseDouble(unit_total);//+Double.parseDouble(vat_val);
														if(ItemTax.equals("0"))
														{
															unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														}
														else
														{
															unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														}
														
														
														String query5="update "+company+".mr_purchase_order set sub_ttl_disc='"+grand_disc+"',sub_cost_after_sub_ttl_disc='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl_disc='"+unit_price+"' where ref_po_num='"+ponum+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' and id='"+resultSet2.getString("id")+"'";
														statement.executeUpdate(query5);
													}
													else
													{
														String qty=resultSet2.getString("qty");
														String cf=resultSet2.getString("cf");
														String unit_total=df2.format(Double.parseDouble(itm_cost_after_disc)-(Double.parseDouble(itm_cost_after_disc)*grand_disc)/100);
														String vat_val=df2.format((Double.parseDouble(unit_total)*Double.parseDouble(taxrate))/100);
														double after_subttl_disc=Double.parseDouble(unit_total);//+Double.parseDouble(vat_val);
														
														if(ItemTax.equals("0"))
														{
															unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														}
														else
														{
															unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
														}
														
														
														String query5="update "+company+".mr_purchase_order set sub_ttl_disc='"+grand_disc+"',sub_cost_after_sub_ttl_disc='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl_disc='"+unit_price+"' where ref_po_num='"+ponum+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' and id='"+resultSet2.getString("id")+"'";
														statement.executeUpdate(query5);
													}
												}
												else
												{
													String qty=resultSet2.getString("qty");
													String cf=resultSet2.getString("cf");
													//disc_perc=grand_disc//df2.format((grand_disc/total)*100);
													String unit_total=df2.format(Double.parseDouble(itm_cost_after_disc)-(Double.parseDouble(itm_cost_after_disc)*grand_disc)/100);
													String vat_val=df2.format((Double.parseDouble(unit_total)*Double.parseDouble(taxrate))/100);
													double after_subttl_disc=Double.parseDouble(unit_total);//+Double.parseDouble(vat_val);
													if(ItemTax.equals("0"))
													{
														unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
													}
													else
													{
														unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
													}
													
													
													
													String query5="update "+company+".mr_purchase_order set sub_ttl_disc='"+grand_disc+"',sub_cost_after_sub_ttl_disc='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl_disc='"+unit_price+"' where ref_po_num='"+ponum+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' and id='"+resultSet2.getString("id")+"'";
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
											String new_qty=df2.format(ttl_qty+Float.parseFloat(avg_qty));
											double new_avg=0;
											double avgcost=0;
											if(Double.parseDouble(avg_qty) <= 0)
											{
												new_avg=(Double.parseDouble(unit_price)*ttl_qty);
												if(ttl_qty<=0)
												{
													avgcost=ttl_qty;
												}
												else
												{
													avgcost=new_avg/ttl_qty;
												}
											}
											else
											{
												new_avg=((Double.parseDouble(avg_cst)*Float.parseFloat(avg_qty))+(Double.parseDouble(unit_price)*ttl_qty));
												if(Float.parseFloat(new_qty)<=0)
												{
													avgcost=new_avg;
												}
												else
												{
													avgcost=new_avg/(Float.parseFloat(new_qty));
												}
											}
											
											
											
											
											String suhail=df2.format(avgcost);
											/*
											 item master
											 item_cost=new_avg
											 qty= new_qty
											 last cost= unit_price
											 */
											
											int taxtype=0;
											String inputtax="";
											if(tax_type.equals("INC"))
											{
												taxtype=1;
												inputtax="VAT 5%";
											}
											if(tax_type.equals("EXC"))
											{
												taxtype=0;
												inputtax="VAT 5%";
											}
											if(tax_type.equals("NON"))
											{
												taxtype=0;
												inputtax="";
											}
											//String selling_price=validate_barcode_unit(company,itm_code,unit);
											//"+selling_price+"='"+sp+"',
											String last_cost="";
											if(Double.parseDouble(unit_price)!=0)
											{
												last_cost=",item_lastcost='"+unit_price+"'";
											}
											String query5="update "+company+".mr_item_master set input_tax='"+inputtax+"',po_inclusive_tax='"+taxtype+"',total_qty='"+new_qty+"',item_cost='"+suhail+"',modified_date='"+formattedDate+"' "+last_cost+" where item_code='"+itm_code+"' ";
											statement.executeUpdate(query5);
											String query55="update "+company+".mr_tmp_item_master set input_tax='"+inputtax+"',po_inclusive_tax='"+taxtype+"',qty='"+new_qty+"',cost='"+suhail+"' where item_code='"+itm_code+"' ";
											statement.executeUpdate(query55);
											/* kit item*/
											
											String kititm_validate="select * from "+company+".mr_kit_items where parent_itemcode='"+itm_code+"' ";
											Statement statement125=con.createStatement();
											ResultSet resultSet125 = statement125.executeQuery(kititm_validate);
											if(resultSet125.next())
											{
												Statement statement150=con.createStatement();
												ResultSet resultSet150 = statement150.executeQuery(kititm_validate);
												while(resultSet150.next())
												{
													String child_itemcode=resultSet150.getString("child_itemcode");
													String query15="update "+company+".mr_item_master set item_cost='"+suhail+"',modified_date='"+formattedDate+"' "+last_cost+" where item_code='"+child_itemcode+"' ";
													statement.executeUpdate(query15);
												}
											}
											
											
											/* item history */
											String query11="insert into "+company+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) values('"+itm_code+"','PO','"+ponum+"','"+qty+"','"+unit+"','"+cf+"','"+unit_price+"','"+(Double.parseDouble(unit_price)*ttl_qty)+"','0','0','0','Recieved "+ponum+"','"+username+"','"+po_date+"','"+resultSet2.getString("barcode")+"','"+vendor_id+"','"+vendor_name+"')";
											statement.executeUpdate(query11);
											/*
											link items to vendor
											*/
											String query10="SELECT * FROM "+company+".mr_vendor_items where ref_item_id='"+itm_code+"' and ref_vendor_id='"+vendor_id+"' "; 
											ResultSet resultSet15 = statement.executeQuery(query10);
											if(!resultSet15.next())
											{
												String query13="insert into "+company+".mr_vendor_items(`ref_vendor_id`, `ref_item_id`, `created_date`) VALUES ('"+vendor_id+"','"+itm_code+"','"+formattedDate+"')";
												statement.executeUpdate(query13);
											}
											
											if(POcost.equals("1"))
											{
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
													String query19="update "+company+".mr_sd_lines set item_cost=qty*cf*'"+suhail+"' where item_code='"+itm_code+"' and created_date >= '"+po_date+"' ";
													statement.executeUpdate(query19);
												}
												String query21="select item_cost from "+company+".mr_sd_lines_history where item_code='"+itm_code+"' and created_date >= '"+po_date+"' ";
												ResultSet resultSet21 = statement.executeQuery(query21);
												if(resultSet21.next())
												{
													String query19="update "+company+".mr_sd_lines_history set item_cost=qty*cf*'"+suhail+"' where item_code='"+itm_code+"' and created_date >= '"+po_date+"' ";
													statement.executeUpdate(query19);
												}
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
										String subtotal=df2.format(sub_total);
										String query5="insert into "+company+".mr_purchase_tax(ref_po_no,po_total,po_date,supp_invoice,tax_amount,created_date) values ('"+ponum+"','"+subtotal+"','"+po_date+"','"+inv_num+"','"+total_vat+"','"+formattedDate+"')";
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
						                	double new_po_amt=grand_total+Double.parseDouble(vend_purch_amt);
						                	String new_poamt=df2.format(new_po_amt);
						                	double new_po_pend_amt=grand_total+Double.parseDouble(vend_pend_amt);
						                	String new_po_pendamt=df2.format(new_po_pend_amt);
						                	String query6="update "+company+".mr_vendor set vendor_purch_amt='"+new_poamt+"', vendor_pending_amt='"+new_po_pendamt+"' where vendor_code ='"+vendor_id+"' ";
											statement.executeUpdate(query6);
						                }
						                
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
											statement.executeUpdate(save_po);
									    }
						                
						                String insert_acc_header="insert into "+company+".mr_accounts_trans_header (tran_seqencecode,tran_date,tran_time,tran_remark,tran_debit_amt,tran_credit_amt,tran_status,tran_user,tran_store,tran_orgin,created_date,modified_date) values('"+ponum+"','"+po_date+"','"+times.format(cal.getTime())+"','SUP.INVOICE NO. "+inv_num+"','"+grand_total+"','"+grand_total+"','POSTED','"+username+"','"+store+"','PO','"+formattedDate+"','"+formattedDate+"' )";
						                statement.executeUpdate(insert_acc_header);
						                
						                String def_payable=vendor_payable_ac;
//						                String query15="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_code='"+vendor_payable_ac+"' and acc_store='"+store+"' "; 
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
						                
						                String tax_applied="";
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
						                tax_applied=",('"+ponum+"','"+po_date+"','"+times.format(cal.getTime())+"','ASSET','"+tax_input+"','"+tax_name+"','"+total_vat+"','0','SUP.INVOICE NO. "+inv_num+"','"+formattedDate+"','"+formattedDate+"')";
						                tax_jv=",('"+seq_num+"','"+seq_num+"','"+tax_input+"','"+tax_name+"','"+total_vat+"','0','"+inv_num+"','"+ponum+"-"+vendor_id+"-"+vendor_name+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+po_date+"')";
						                
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
										double bal_taxable=bal_tax+total_vat;
						                tax_leger=",('"+seq_num+"','"+po_date+"','"+formattedDate+"','"+tax_input+"','"+total_vat+"','0','"+bal_taxable+"','"+ponum+"','"+inv_num+"','PO','"+formattedDate+"','"+formattedDate+"','OPEN' )";
						                
						                /*discount open
						                
						                String disc_applied="";
						                String disc_jv="";
						                String disc_leger="";
						                String query118="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_code='0301002001' and acc_store='"+store+"'"; 
										Statement statement118=con.createStatement();  
						                ResultSet resultSet118= statement118.executeQuery(query118);
						                String disc_name="";
						                if(resultSet118.next())
						                {
						                	disc_name=resultSet118.getString("acc_name");
						                }
						                double disc_amt=(ttl_price*grand_disc)/100;
						                disc_applied=",('"+ponum+"','"+po_date+"','"+times.format(cal.getTime())+"','INCOME','0301002001','"+disc_name+"','"+disc_amt+"','0','SUP.INVOICE NO. "+inv_num+"','"+formattedDate+"','"+formattedDate+"')";
						                disc_jv=",('"+seq_num+"','"+seq_num+"','0301002001','"+disc_name+"','"+disc_amt+"','0','"+ponum+"','"+inv_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"')";
						                
						                String query219="SELECT * FROM "+company+".mr_acc_ledger where ac_code='0301002001'"; 
										Statement statement219=con.createStatement();  
						                ResultSet resultSet219= statement219.executeQuery(query219);
						                float cr_disc=0;
						                float dr_disc=0;
						                while(resultSet219.next())
						                {
						                	cr_disc+=Float.parseFloat(resultSet219.getString("cr_amt"));
						                	dr_disc+=Float.parseFloat(resultSet219.getString("dr_amt"));
						                }
										float bal_disc=dr_disc-cr_disc;
										double bal_discount=bal_disc+grand_disc;
										disc_leger=",('"+seq_num+"','"+formattedDate+"','"+formattedDate+"','0301002001','"+disc_amt+"','0','"+bal_discount+"','"+ponum+"','"+inv_num+"','PO','"+formattedDate+"','"+formattedDate+"','OPEN' )";
						                 
						                discount close*/
						                
						                
						                String insert_acc_detls="insert into "+company+".mr_accounts_tran_detls (tran_seqence_code,tran_date,tran_time,tran_type,tran_accode,tran_acname,tran_debit_amt,tran_credit_amt,tran_remark,created_date,modified_date) values('"+ponum+"','"+po_date+"','"+times.format(cal.getTime())+"','ASSET','"+inv_acc+"','"+acc_name+"','"+sub_total+"','0','SUP.INVOICE NO. "+inv_num+"','"+formattedDate+"','"+formattedDate+"'),('"+ponum+"','"+po_date+"','"+times.format(cal.getTime())+"','LIABILITY','"+def_payable+"','"+acc_pay+"','0','"+grand_total+"','SUP.INVOICE NO. "+inv_num+"','"+formattedDate+"','"+formattedDate+"') "+tax_applied+"  ";
										statement.executeUpdate(insert_acc_detls);

										
										
										String save_po="insert into "+company+".mr_jv_header( `jv_num`, `date`, `branch`, `username`, `doc_num`, `remark`, `notes`, `store_id`, `created_date`, `modified_date`, `status`,`is_approved`, `approved_by`, `is_posted`, `posted_by`, `posted_date`, `approved_date`,jv_amt,vendor_cust_id) "
												+ "values('"+seq_num+"','"+po_date+"','"+comboBox.getSelectedItem().toString()+"','"+username+"','"+inv_num+"','"+ponum+"-"+vendor_id+"-"+vendor_name+"','"+ponum+"','"+store+"','"+formattedDate+"','"+formattedDate+"','Posted','1','"+username+"','1','"+username+"','"+formattedDate+"','"+formattedDate+"','"+grand_total+"','"+vendor_id+"' )";
										statement.executeUpdate(save_po);
										
										String query1="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,cheque_date)"
												+ " VALUES ('"+seq_num+"','"+seq_num+"','"+inv_acc+"','"+acc_name+"','"+sub_total+"','0','"+inv_num+"','"+ponum+"-"+vendor_id+"-"+vendor_name+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+po_date+"'),"
														+ "('"+seq_num+"','"+seq_num+"','"+def_payable+"','"+acc_pay+"','0','"+grand_total+"','"+inv_num+"','"+ponum+"-"+vendor_id+"-"+vendor_name+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+po_date+"') "
																+ ""+tax_jv+" ";
										statement.executeUpdate(query1);
										
										String query37="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+inv_acc+"'"; 
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
										double bal=bal_asset+sub_total;
										
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
										
										String save_ledger="insert into "+company+".mr_acc_ledger (`jv_num`, `tran_date`, `post_date`, `ac_code`, `dr_amt`, `cr_amt`, `balance`, `notes`, `remark`, `source`, `created_date`, `modified_date`,status) values ('"+seq_num+"','"+po_date+"','"+formattedDate+"','"+inv_acc+"','"+sub_total+"','0','"+bal+"','"+ponum+"','"+inv_num+"','PO','"+formattedDate+"','"+formattedDate+"','OPEN' ),('"+seq_num+"','"+po_date+"','"+formattedDate+"','"+def_payable+"','0','"+grand_total+"','"+bal_lia+"','"+ponum+"','"+inv_num+"','PO','"+formattedDate+"','"+formattedDate+"','OPEN' ) "+tax_leger+" ";
										statement.executeUpdate(save_ledger);
										
										
										String insert_vendor_summery="insert into "+company+".mr_vendor_summary(ref_vendor_id,ref_po_num,created_date)values('"+vendor_id+"','"+ponum+"','"+formattedDate+"')";
										statement.executeUpdate(insert_vendor_summery);
										
										int days=0;
										String sel_po_terms="select * from "+company+".mr_po_terms where po_term_code='"+po_terms+"' ";
										ResultSet resultSet4 = statement1.executeQuery(sel_po_terms);
										if(resultSet4.next())
										{
											days=Integer.parseInt(resultSet4.getString("due_days"));
										}
										double dr_amt=0;
//										if(po_terms.equals("CASH"))
//										{
//											dr_amt=grand_total;
//										}
										double bal_vendor=grand_total-dr_amt;
										String balnce=df2.format(bal_vendor);
										
										if(grand_total<0)
										{
											String vendor_payment="insert INTO "+company+".mr_vendor_payment(`ref_po_num`, `invo_num`, `invo_date`, `invo_amt`, `paid_amt`, `bal_amt`, `pay_terms`, `due_days`, `created_date`, `modified_date`,ref_vendor_id,store_id,status,branch) VALUES('"+ponum+"','"+inv_num+"','"+po_date+"','"+grand_total+"','"+dr_amt+"','"+balnce+"','"+po_terms+"','"+days+"','"+formattedDate+"','"+formattedDate+"','"+vendor_id+"','"+store+"','2','"+comboBox.getSelectedItem().toString()+"')";
											statement.executeUpdate(vendor_payment);
											
											String vendor_payment_dt="insert INTO "+company+".mr_vendor_payment_details(`ref_po_num`,trans_num, `ref_vendor_id`, `cr_amt`, `dr_amt`, `store_id`, `username`, `created_date`, `modified_date`,issue_date,invoicetype) VALUES('"+ponum+"','"+ponum+"','"+vendor_id+"','"+grand_total+"','"+dr_amt+"','"+store+"','"+username+"','"+formattedDate+"','"+formattedDate+"','"+start_date+"','PO' )";
											statement.executeUpdate(vendor_payment_dt);
										}
										else
										{
											String vendor_payment="insert INTO "+company+".mr_vendor_payment(`ref_po_num`, `invo_num`, `invo_date`, `invo_amt`, `paid_amt`, `bal_amt`, `pay_terms`, `due_days`, `created_date`, `modified_date`,ref_vendor_id,store_id,status,branch) VALUES('"+ponum+"','"+inv_num+"','"+po_date+"','"+grand_total+"','"+dr_amt+"','"+balnce+"','"+po_terms+"','"+days+"','"+formattedDate+"','"+formattedDate+"','"+vendor_id+"','"+store+"','1','"+comboBox.getSelectedItem().toString()+"')";
											statement.executeUpdate(vendor_payment);
											
											String vendor_payment_dt="insert INTO "+company+".mr_vendor_payment_details(`ref_po_num`,trans_num, `ref_vendor_id`, `cr_amt`, `dr_amt`, `store_id`, `username`, `created_date`, `modified_date`,issue_date,status,invoicetype) VALUES('"+ponum+"','"+ponum+"','"+vendor_id+"','"+grand_total+"','"+dr_amt+"','"+store+"','"+username+"','"+formattedDate+"','"+formattedDate+"','"+start_date+"','OPEN','PO' )";
											statement.executeUpdate(vendor_payment_dt);
										}
										
										
										String update_pl="update "+company+".mr_purchase_list set status='POSTED' , posted_date='"+start_date+"',referance='"+textField_3.getText()+"',branch='"+comboBox.getSelectedItem().toString()+"' where po_no ='"+ponum+"' ";
										statement.executeUpdate(update_pl);
										
										String history="INSERT INTO "+company+".mr_purchase_list_history( `com_date`, `po_date`, `user_id`, `po_no`, `invo_nu`, `ttl_items`, `ttl_price`, `sub_ttl_amt`, `grand_disc`, `total_vat`, `grand_total`, `promt_print`, `double_discont`, `vendor_id`, `assume_ttl_cst`, `currency_id`, `status`, `posted_date`, `po_terms`, `store_name`, `created_date`, `modified_date`, `referance`, `branch`, `vat_account`,remark,roundoff) SELECT `com_date`, `po_date`, `user_id`, `po_no`, `invo_nu`, `ttl_items`, `ttl_price`, `sub_ttl_amt`, `grand_disc`, `total_vat`, `grand_total`, `promt_print`, `double_discont`, `vendor_id`, `assume_ttl_cst`, `currency_id`, `status`, `posted_date`, `po_terms`, `store_name`, `created_date`, `modified_date`, `referance`, `branch`, `vat_account`,remark,roundoff FROM "+company+".mr_purchase_list WHERE po_no ='"+ponum+"' ";
										statement.executeUpdate(history);
										
										String pohistory="INSERT INTO "+company+".mr_purchase_order_history( `ref_po_num`, `barcode`, `item_code`, `qty`, `unit`, `cf`, `unit_cost`, `itm_disc`, `itm_cost_after_disc`, `sub_ttl_disc`, `sub_cost_after_sub_ttl_disc`, `tax_val`, `total`, `cost_after_sub_ttl_disc`, `created_date`, `modified_date`, `sp`, `tax_type`, `item_name`, `input_tax`) SELECT  `ref_po_num`, `barcode`, `item_code`, `qty`, `unit`, `cf`, `unit_cost`, `itm_disc`, `itm_cost_after_disc`, `sub_ttl_disc`, `sub_cost_after_sub_ttl_disc`, `tax_val`, `total`, `cost_after_sub_ttl_disc`, `created_date`, `modified_date`, `sp`, `tax_type`, `item_name`, `input_tax` FROM "+company+".mr_purchase_order WHERE ref_po_num ='"+ponum+"' ";
										statement.executeUpdate(pohistory);
										
										String delete_pl="delete from "+company+".mr_purchase_list where po_no ='"+ponum+"' ";
										statement.executeUpdate(delete_pl);
										
										String delete_po="delete from "+company+".mr_purchase_order where ref_po_num ='"+ponum+"' ";
										statement.executeUpdate(delete_po);
										
						                //JOptionPane.showMessageDialog(frame, "PO Posted Successfully");
										if(po_terms.equals("CASH"))
										{
											int cofrm = JOptionPane.showConfirmDialog(frame, "<html>Do you Want to Pay This Invoice<br>Â·  —Ìœ œ›⁄ Â–Â «·›« Ê—…ø</html>","Warning",JOptionPane.YES_NO_OPTION);
									        // 0=yes, 1=no, 2=cancel
									        if(cofrm==0)
									        {
									        	Object[] item_list = new String[1];
									        	Object[] invoice_list = new String[1];
									        	Object[] return_list = new String[1];
									        	item_list[0]=textField.getText();
									        	invoice_list[0]=textField.getText();
									        	new accounts_master.Paymentvouch(company,username,store,item_list,grand_total,vendor_id,vendor_name, version,Integer.toString(decimal),0,invoice_list,return_list,"PO", language);
									        }
									        else
									        {
									        	JOptionPane.showMessageDialog(frame, "<html>PO Posted Successfully<br> „ ‰‘— ÿ·» «·‘—«¡ »‰Ã«Õ</html>");
									        	frame.setVisible(false);
								                frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								                purchase_order_list.textField.setText("");
								                purchase_order_list.get_po_disp( company, "OPEN", "");	
									        }
										}
										else
										{
											JOptionPane.showMessageDialog(frame, "<html>PO Posted Successfully<br> „ ‰‘— ÿ·» «·‘—«¡ »‰Ã«Õ</html>");
								        	frame.setVisible(false);
							                frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
							                purchase_order_list.textField.setText("");
							                purchase_order_list.get_po_disp( company, "OPEN", "");	
										}
										
									}
									else{
										JOptionPane.showMessageDialog(frame, "<html>Grand Total is not Matching<br>«·„Ã„Ê⁄ «·ﬂ·Ì €Ì— „ ÿ«»ﬁ</html>");
										frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
									}
								}
								else{
									JOptionPane.showMessageDialog(frame, "<html>This Item Already Posted<br> „ ‰‘— Â–« «·⁄‰’— »«·›⁄·</html>");
									frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								}
							}
							else{
								JOptionPane.showMessageDialog(frame, "<html>Some error occur please try again<br>ÕœÀ Œÿ√ „«° Ì—ÃÏ «·„Õ«Ê·… „—… √Œ—Ï</html>");
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
					JOptionPane.showMessageDialog(frame, "<html>Recheck Your Values<br>√⁄œ «· Õﬁﬁ „‰ ﬁÌ„ﬂ</html>");
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		button.setBounds(238, 298, 83, 31);
		panel.add(button);
		
		textField_1.requestFocus();
		
		get_store_validate_dt( company,store);
	}
	public static void get_store_validate_dt(String company,String store_name)
	{
		try {
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store where store_id='"+store_name+"' ");
			if(resultSet.next())
			{
				
				POcost=resultSet.getString("POcost");
				ItemTax=resultSet.getString("acc_active");
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	public String validate_barcode_unit(String company,  String itemcode, String unitname) {
		String sp="";
		try{
			
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_item_master where item_code='"+itemcode+"' and '"+unitname+"' IN (unit1,unit2,unit3,unit4,unit5,unit6) "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				if(unitname.equals(resultSet.getString("unit1")))
				{
					sp="SP1";
				}
				if(unitname.equals(resultSet.getString("unit2")))
				{
					sp="sp2";
				}
				if(unitname.equals(resultSet.getString("unit3")))
				{
					sp="sp3";
				}
				if(unitname.equals(resultSet.getString("unit4")))
				{
					sp="sp4";
				}
				if(unitname.equals(resultSet.getString("unit5")))
				{
					sp="sp5";
				}
				if(unitname.equals(resultSet.getString("unit6")))
				{
					sp="sp6";
				}
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		return sp;
	}
}
