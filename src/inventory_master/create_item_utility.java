package inventory_master;

import java.awt.Color;
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
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import org.json.JSONException;

import system_setups.department_list;

import javax.swing.JCheckBox;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class create_item_utility {

	public static JFrame frame;
	private JTextField textField_7;
	private JTextField textField_8;
	public static JTextField textField;
	public static JTextField textField_1;
	private JProgressBar progressBar;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private boolean dot=false;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					create_item_utility window = new create_item_utility("","mr_pro","admin", "S01", null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public create_item_utility(String db_name,String company, String username,  String store,String version) throws ParseException {
		initialize(db_name,company,username,store, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param balance 
	 * @param po_num 
	 * @param company 
	 * @param host_name 
	 * @param store 
	 * @param username 
	 * @param vendor_name 
	 * @param vendor_name2 
	 * @throws ParseException 
	 */
	private void initialize(final String db_name,final String company,final String username,final String store,final String version) throws ParseException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.requestFocus();
		
		JLabel lblDateRange = new JLabel("Item Creation Utility");
		lblDateRange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateRange.setBounds(12, 7, 233, 32);
		frame.getContentPane().add(lblDateRange);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(85, 75, 43, 22);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(213, 75, 27, 22);
		frame.getContentPane().add(lblTo);
		
		
		JLabel lblDateRange_1 = new JLabel("Price Range :");
		lblDateRange_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateRange_1.setBounds(12, 46, 89, 22);
		frame.getContentPane().add(lblDateRange_1);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		
		textField_7 = new JTextField();
		textField_7.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_7.getText().equals(""))
			        dot = false;
			    if (dot == false)
			    {
			        if (vChar == '.') 
			        {
			        	dot = true;
			        }
			        else if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE)))
			        {
			                e.consume();
			        }
			    } 
			    else 
			    {
			        if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) 
			        {
			                e.consume();
			        }
			    }
			}
		});
		textField_7.setBounds(59, 104, 86, 20);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(204, 106, 86, 20);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblDeoartment = new JLabel("Department");
		lblDeoartment.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDeoartment.setBounds(12, 131, 166, 22);
		frame.getContentPane().add(lblDeoartment);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(108, 160, 314, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Customer List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new department_list(company,version,"item_utlity");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(432, 159, 28, 21);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblTaxType = new JLabel("Tax Type :");
		lblTaxType.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTaxType.setBounds(12, 187, 115, 22);
		frame.getContentPane().add(lblTaxType);
		
		JLabel lblInputTax = new JLabel("Input Tax");
		lblInputTax.setBounds(12, 216, 68, 20);
		frame.getContentPane().add(lblInputTax);
		
		JLabel lblOutputTax = new JLabel("Output Tax");
		lblOutputTax.setBounds(12, 249, 87, 20);
		frame.getContentPane().add(lblOutputTax);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(90, 216, 135, 20);
		frame.getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(90, 249, 135, 20);
		frame.getContentPane().add(comboBox_1);
		
		try
		{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_tax");
			while(resultSet.next())
			{
				comboBox.addItem(resultSet.getString("tax_code"));
				comboBox_1.addItem(resultSet.getString("tax_code"));
			}
			comboBox.addItem("");
			comboBox_1.addItem("");
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("Price inclusive of tax");
		chckbxNewCheckBox.setBounds(12, 276, 272, 23);
		chckbxNewCheckBox.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JButton btnCreateItems = new JButton("Create Items");
		btnCreateItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String from=textField_7.getText();
					String to = textField_8.getText();
					String dept=textField.getText();
					String dept_code=textField_1.getText();
					String input=comboBox.getSelectedItem().toString();
					String output=comboBox_1.getSelectedItem().toString();
					String incr=textField_2.getText();
					if(from.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Enter from number");
					}
					else if(to.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Enter to number");
					}
					else if(incr.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Enter increment");
					}
					else if(dept.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Select department");
					}
					else
					{
						String inc="0";
						String vat="0";
						if(!input.equals(""))
						{
							if(chckbxNewCheckBox.isSelected())
							{
								inc="1";
								vat="5";
							}
						}
						if(!output.equals(""))
						{
							if(chckbxNewCheckBox.isSelected())
							{
								inc="1";
								vat="5";
							}
						}
						
						progressBar.setVisible(true);
						double number_progress=0;
						int b=1;
						
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						
						double ttl_count=(Double.parseDouble(to)-Double.parseDouble(from))/Double.parseDouble(incr);
						
						number_progress=100/ttl_count;
						
						String seq="";
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_department where dept_name='"+dept+"'");
						if(resultSet.next())
						{
							seq=resultSet.getString("seq_num");
						}
						double price=Double.parseDouble(from);
						LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);  
						for(int i=0; i<ttl_count;i++)
						{
							if(i==209)
							{
								price=price+Double.parseDouble(incr);
							}
							price=price+Double.parseDouble(incr);
							BigDecimal bd = new BigDecimal(price);
                    		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                    		price = bd.doubleValue();
							String namestr = Double.toString(price).replace(".", "");
							String name=dept+""+namestr;
							ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_item_master where item_name='"+name+"'");
							if(!resultSet1.next())
							{
								StringBuilder sb = new StringBuilder();
								for (int toPrepend=3-dept_code.length(); toPrepend>0; toPrepend--) {
								    sb.append('0');
								}
								sb.append(dept_code);
								StringBuilder sq = new StringBuilder();
								for (int toPrepend=5-seq.length(); toPrepend>0; toPrepend--) {
								    sq.append('0');
								}
								sq.append(seq);
								String itemcode = sb.toString()+""+sq.toString();
								 String insert = "INSERT INTO "+company+".mr_item_master (item_code,item_name,description1,description2,item_details,department,sub_department,category,sub_category,item_type,item_cost,item_lastcost,item_stdcost,total_qty,item_image,input_tax,output_tax,sp_include_tax,po_inclusive_tax,deliItemsweighing,exp_days,weighing_sts,parentKitItem,allowDiscount,disc_perc,activeForSale,activeForPurchase,Onpromo,start_date,enddate,min_order,acc_grp,acc_code,open_qty,"
								 		+ "unit1,cf1,sp1,barcode1,barcode2,barcode3,unit2,cf2,sp2,barcode4,barcode5,barcode6,unit3,cf3,sp3,barcode7,barcode8,barcode9,unit4,cf4,sp4,barcode10,barcode11,barcode12,unit5,cf5,sp5,barcode13,barcode14,barcode15,unit6,cf6,sp6,barcode16,barcode17,barcode18,created_date,modified_date,input_tax_rate,output_tax_rate) "
						            		+ "VALUES ('"+itemcode+"', '"+name+"', '"+name+"', '', '', '"+dept+"', 'NIL', 'NIL', 'NIL', 'Inventory', '0', '0', '0', '0', '"+itemcode+".jpg', '"+input+"', '"+output+"', '"+inc+"', '"+inc+"', '0', '', '', '0', '0', '0', '1', '1', '0', '"+formattedDate+"',  '"+formattedDate+"', '0', 'STOCK  AT S01', 'GENERAL', '0',"
						            	+ " 'Each', '1', '"+price+"', '"+itemcode+"03', '', '', 'Box', '', '', '', '', '', 'Pack1', '', '', '', '', '', 'Pack2', '','', '', '', '', 'Pack3', '', '', '', '', '', 'Pack4', '', '', '', '', '', '"+formattedDate+"', '"+formattedDate+"', '"+vat+"', '"+vat+"')";
								 statement.executeUpdate(insert);
								 
							}
							double ttlcount=b*number_progress;
							 Thread.sleep(50);
							 progressBar.paintImmediately(0, 0, 854, 31);
							 progressBar.setValue((int)ttlcount);
							 b++;
							seq=Integer.toString(Integer.parseInt(seq)+1);
						}
						String seqnum=Integer.toString(Integer.parseInt(seq)+1);
						String updte_dept="update "+company+".mr_department set seq_num='"+seqnum+"' where dept_name='"+dept+"' ";
						statement.executeUpdate(updte_dept);
						String query="TRUNCATE TABLE "+company+".mr_tmp_item_master ";
						statement.executeUpdate(query);
						
						ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_item_master ");
						if(resultSet2.next())
						{
							String query1="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode1,category,department,unit1,sp1,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf1,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode1!='' and sp1!='' and  cf1!='' ";
							statement.executeUpdate(query1);
							
							String query2="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode2,category,department,unit1,sp1,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf1,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode2!='' and sp1!='' and  cf1!='' ";
							statement.executeUpdate(query2);
							
							String query3="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode3,category,department,unit1,sp1,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf1,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode3!='' and sp1!='' and  cf1!='' ";
							statement.executeUpdate(query3);
						
							String query4="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode4,category,department,unit2,sp2,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf2,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode4!='' and sp2!='' and  cf2!=''  ";
							statement.executeUpdate(query4);
							
							String query5="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode5,category,department,unit2,sp2,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf2,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode5!='' and sp2!='' and  cf2!='' ";
							statement.executeUpdate(query5);
							
							String query6="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode6,category,department,unit2,sp2,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf2,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode6!=''  and sp2!='' and  cf2!='' ";
							statement.executeUpdate(query6);
							
							String query7="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode7,category,department,unit3,sp3,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf3,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode7!='' and sp3!='' and  cf3!='' ";
							statement.executeUpdate(query7);
							
							String query8="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode8,category,department,unit3,sp3,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf3,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode8!='' and sp3!='' and  cf3!='' ";
							statement.executeUpdate(query8);
							
							String query9="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode9,category,department,unit3,sp3,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf3,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode9!='' and sp3!='' and  cf3!='' ";
							statement.executeUpdate(query9);
							
							String query10="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode10,category,department,unit4,sp4,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf4,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode10!='' and sp4!='' and  cf4!='' ";
							statement.executeUpdate(query10);
							
							String query11="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode11,category,department,unit4,sp4,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf4,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode11!='' and sp4!='' and  cf4!='' ";
							statement.executeUpdate(query11);
							
							String query12="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode12,category,department,unit4,sp4,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf4,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode12!='' and sp4!='' and  cf4!='' ";
							statement.executeUpdate(query12);
							
							String query13="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode13,category,department,unit5,sp5,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf5,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode13!='' and sp5!='' and  cf5!='' ";
							statement.executeUpdate(query13);
							
							String query14="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode14,category,department,unit5,sp5,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf5,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode14!='' and sp5!='' and  cf5!='' ";
							statement.executeUpdate(query14);
							
							String query15="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode15,category,department,unit5,sp5,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf5,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode15!='' and sp5!='' and  cf5!='' ";
							statement.executeUpdate(query15);
							
							String query16="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode16,category,department,unit6,sp6,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf6,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode16!='' and sp6!='' and  cf6!='' ";
							statement.executeUpdate(query16);
							
							String query17="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode17,category,department,unit6,sp6,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf6,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode17!='' and sp6!='' and  cf6!='' ";
							statement.executeUpdate(query17);
							
							String query18="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept) select item_code,item_name,barcode18,category,department,unit6,sp6,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf6,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department from "+company+".mr_item_master where barcode18!='' and sp6!='' and  cf6!='' ";
							statement.executeUpdate(query18);
						}
						progressBar.setVisible(false);
						JOptionPane.showMessageDialog(frame, "Completed");
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		});
		btnCreateItems.setBounds(168, 330, 135, 32);
		frame.getContentPane().add(btnCreateItems);
		
		JLabel lblIncrement = new JLabel("Increment");
		lblIncrement.setBounds(349, 75, 60, 22);
		frame.getContentPane().add(lblIncrement);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(12, 160, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 0, 205));
		progressBar.setBackground(new Color(192, 192, 192));
		progressBar.setStringPainted(true);
		//progressBar.setBounds(12, 198, 622, 31);
		//frame.getContentPane().add(progressBar);
		progressBar.setBounds(12, 369, 448, 22);
		progressBar.setVisible(false);
		frame.getContentPane().add(progressBar);
		
		textField_2 = new JTextField();
		textField_2.setBounds(336, 106, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 476, 432);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
