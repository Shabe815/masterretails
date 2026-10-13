package sales_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.border.Border;

import org.json.JSONException;

import CafePro.Mainpage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Customer_master {

	private static JFrame frame;
	private static JTextField textField;
	private static JTextField textField_1;
	public static JTextField textField_11;
	private static JTextField textField_12;
	private static JTextField textField_13;
	private static JTextField textField_14;
	private static JTextField textField_15;
	private static JTextField textField_16;
	private static JTextField textField_17;
	private static JTextField textField_18;
	private static JTextField textField_8;
	private static JTextField textField_9;
	private static JTextField textField_10;
	private static JTextField textField_2;
	private static JTextField txtUae;
	private static JTextField textField_4;
	private static JTextField textField_5;
	private static JTextField textField_6;
	private static JTextField textField_7;
	private static JComboBox<String> comboBox;
	private static JComboBox<String> comboBox_1;
	private static JComboBox<String> comboBox_2;
	private static JComboBox<String> comboBox_3;
	private static JComboBox<String> comboBox_4;
	private static JComboBox<String> comboBox_5;
	private static JComboBox<String> comboBox_6;
	private static JComboBox<String> comboBox_7;
	private static JComboBox<String> comboBox_8;
	private static JComboBox<String> comboBox_9;
	private static JLabel lblNewLabel;
	private static JTextField textField_3;
	private static JTextField textField_19;
	
	public static String Active="0";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					Customer_master window = new Customer_master("mr_pro", "admin","S01","", null,"","");
					Customer_master.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param reg_nme 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param store_name 
	 */
	public Customer_master(String company, String username, String reg_nme, String store_name,String version,String pagetype,String language) {
		initialize(company,username,reg_nme,store_name, version, pagetype, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param reg_nme 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param store_name 
	 */
	private void initialize(final String company,final String username, final String reg_nme,final String store_name,final String version,final String pagetype,final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 757, 695);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(Active.equals("1"))
				{
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.txtDisc.requestFocus();
						PosscreenArabic.btnNewCustomer.setEnabled(true);
					}
					else
					{
						posscreen.txtDisc.requestFocus();
						posscreen.btnNewCustomer.setEnabled(true);
					}
					
					Active="0";
					
				}
			}
			@Override
		      public void windowIconified(WindowEvent we) {
		        frame.setState(JFrame.NORMAL);
		      }
		});
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 739, 648);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JCheckBox checkBox = new JCheckBox("On Screen Keyboard");
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ad) {
				try {
					textField.requestFocus();
					if(ad.getStateChange() == ItemEvent.SELECTED) {
						//lblNewLabel.
						Runtime.getRuntime().exec("cmd /c osk");
					} 
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		checkBox.setBackground(new Color(243, 229, 216));
		checkBox.setBounds(10, 70, 145, 23);
		panel.add(checkBox);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 68, 739, 2);
		panel.add(separator);
		
		JLabel lblRegisterControl = new JLabel("CUSTOMER MASTER");
		lblRegisterControl.setIcon(new ImageIcon("C:\\Users\\abdullasuhail\\Desktop\\caferpro icons\\control-panel-2-icon.png"));
		lblRegisterControl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRegisterControl.setBounds(4, 11, 178, 44);
		panel.add(lblRegisterControl);
		
		JLabel lblCustomerCode = new JLabel("Customer Code");
		lblCustomerCode.setBounds(3, 99, 108, 20);
		panel.add(lblCustomerCode);
		
		textField = new JTextField();
//		textField.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent arg0) {
//				if(!textField.getText().equals(""))
//				{
//					cust_disp(company, textField.getText());
//				}
//			}
//		});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField.getText().equals(""))
				{
					cust_disp(company, textField.getText());
				}
			}
		});
		textField.setBounds(114, 99, 171, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		Image search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(search));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new sales_master.Cust_Chooser(company,"", username, store_name,"","", version,"");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(288, 99, 45, 23);
		panel.add(btnNewButton);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(10, 125, 108, 20);
		panel.add(lblCustomerName);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				
				if ((vChar == KeyEvent.VK_COMMA))
		        {
		                e.consume();
		        }
			}
		});
		textField_1.setBounds(129, 125, 337, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnInvoices = new JButton("Payment");
		btnInvoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setAlwaysOnTop(false);
//					if(Mainpage.activeSaD==true)
//					{
//						new sales_master.cust_account_payable(company,username, store_name, version,"2");
//					}
//					else
//					{
//						new customer_payment( company, username, store_name, reg_nme, version,posscreen.printer,"");
//					}
				if(Mainpage.SoftLang.equals("Ar"))
				{
					new customer_payment( company, username, store_name, reg_nme, version,PosscreenArabic.printer,"SD");
				}
				else
				{
					new customer_payment( company, username, store_name, reg_nme, version,posscreen.printer,"SD");
				}
				
			}
		});
		btnInvoices.setBounds(473, 70, 134, 44);
		panel.add(btnInvoices);
		
		JButton btnStatement = new JButton("Statement");
		btnStatement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setAlwaysOnTop(false);
				new customer_statement(company, username, store_name,  reg_nme, version,textField.getText());
			}
		});
		btnStatement.setBounds(610, 70, 123, 44);
		panel.add(btnStatement);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 148, 739, 2);
		panel.add(separator_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(310, 150, 2, 371);
		panel.add(separator_3);
		
		JLabel lblDeafaultArAccount = new JLabel("Default A/R Account :");
		lblDeafaultArAccount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDeafaultArAccount.setBounds(338, 287, 145, 14);
		panel.add(lblDeafaultArAccount);
		
		textField_11 = new JTextField("0101013001");
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBounds(478, 284, 165, 23);
		panel.add(textField_11);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		//Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				if(Active.equals("1"))
				{
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.txtDisc.requestFocus();
						
						PosscreenArabic.btnNewCustomer.setEnabled(true);
					}
					else
					{
						posscreen.txtDisc.requestFocus();
						
						posscreen.btnNewCustomer.setEnabled(true);
					}
					Active="0";
					
				}
			}
		});
		menuItem.setBorder(null);
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(511, 11, 60, 48);
		panel.add(menuItem);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement = null;
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
					String code=textField.getText().trim();
					if(code.equals("NEW"))
					{
						code="";
					}
					String name=textField_1.getText();
					String disc=textField_2.getText();
					if(disc.equals(""))
					{
						disc="0";
					}
					String country=txtUae.getText();
					String city=textField_4.getText();
					String trn=textField_5.getText();
					String addr2=textField_6.getText();
					String addr1=textField_7.getText();
					String id_num=textField_8.getText();
					String ph2=textField_9.getText();
					String ph1=textField_10.getText();
					String ar_acc=textField_11.getText();
					String credit_lmt=textField_12.getText();
					String posted_bal=textField_13.getText();
					String open_bal=textField_3.getText();
					String contact_person=textField_19.getText();
					if(posted_bal.equals(""))
					{
						posted_bal="0";
					}
					String unpost_bal=textField_14.getText();
					if(unpost_bal.equals(""))
					{
						unpost_bal="0";
					}
					String last_sle_dt=textField_16.getText();
					if(last_sle_dt.equals(""))
					{
						last_sle_dt=formattedDate;
					}
					String last_paym_dt=textField_17.getText();
					if(last_paym_dt.equals(""))
					{
						last_paym_dt=formattedDate;
					}
					String last_paid_amt=textField_18.getText();
					if(last_paid_amt.equals(""))
					{
						last_paid_amt="0";
					}
					if(name.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Plaese Enter the Customer Name");
					}
//					else if(code.equals(""))
//					{
//						//JOptionPane.showMessageDialog(frame, "Plaese Enter or Select the Customer Code");
//						
//					}
					 
					else
					{
						if(comboBox.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(frame, "Plaese Choose the Customer Type");
						}
						else if(comboBox_1.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(frame, "Plaese Choose the Categry");
						}
						else if(comboBox_2.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(frame, "Plaese Choose the Sub Categry");
						}
						else if(comboBox_3.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(frame, "Plaese Choose the Rating");
						}
						else if(comboBox_4.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(frame, "Plaese Choose the Terms");
						}
						else if(comboBox_5.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(frame, "Plaese Choose the Shipping Code");
						}
						else if(comboBox_6.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(frame, "Plaese Choose the Currency");
						}
						else if(comboBox_7.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(frame, "Plaese Choose the Sale Rep");
						}
						else if(comboBox_8.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(frame, "Plaese Choose the ID Type");
						}
						else if(comboBox_9.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(frame, "Plaese Choose the Customer Status");
						}
						else
						{
							String type=comboBox.getSelectedItem().toString();
							String categry=comboBox_1.getSelectedItem().toString();
							String sub_categry=comboBox_2.getSelectedItem().toString();
							String rating=comboBox_3.getSelectedItem().toString();
							String terms=comboBox_4.getSelectedItem().toString();
							String shipment=comboBox_5.getSelectedItem().toString();
							String curr=comboBox_6.getSelectedItem().toString();
							String sale_rep=comboBox_7.getSelectedItem().toString();
							String id_type=comboBox_8.getSelectedItem().toString();
							String status=comboBox_9.getSelectedItem().toString();
							if(status.equals("Active"))
							{
								status="1";
							}
							else
							{
								status="0";
							}
							if(code.equals(""))
							{
								String querty_code="select cust_code from "+company+".mr_customer_master order by cast(cust_code as UNSIGNED ) desc limit  1";
								statement = con.createStatement();
								ResultSet resultSet = statement.executeQuery(querty_code);
								if(resultSet.next())
								{
									String code_old=resultSet.getString("cust_code");
									code=Integer.toString(Integer.parseInt(code_old)+1);
								}
							}
							
							try {
								statement = con.createStatement();
								ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_customer_master where cust_code='"+code+"' ");
								if(resultSet.next())
								{
									String credit_limit_old=resultSet.getString("credit_limit");
									String avail_bal=resultSet.getString("avail_credit");
									float previous_bal=Float.parseFloat(credit_limit_old)-Float.parseFloat(avail_bal);
									float new_credit_bal=Float.parseFloat(credit_lmt)-previous_bal;
									
									String query="update "+company+".mr_customer_master set contact_person='"+contact_person+"',open_bal='"+open_bal+"',status='"+status+"',`cust_name`='"+name+"', `type`='"+type+"', `category`='"+categry+"', `sub_category`='"+sub_categry+"', `rating`='"+rating+"', `terms_code`='"+terms+"', `shipping_code`='"+shipment+"', `currency`='"+curr+"', `sales_rep`='"+sale_rep+"', `disc`='"+disc+"', `addr1`='"+addr1+"', `addr2`='"+addr2+"', `TRN`='"+trn+"', `city`='"+city+"', `country`='"+country+"', `id_type`='"+id_type+"', `id_num`='"+id_num+"', `ph1`='"+ph1+"', `ph2`='"+ph2+"', `default_ar_acc`='"+ar_acc+"', `credit_limit`='"+credit_lmt+"', `posted_bal`='"+posted_bal+"', `unposted_bal`='"+unpost_bal+"', `avail_credit`='"+new_credit_bal+"', `last_sale_date`='"+last_sle_dt+"', `last_payment_date`='"+last_paym_dt+"', `last_paid_amt`='"+last_paid_amt+"',  `modified_date`='"+formattedDate+"' where cust_code='"+code+"'  ";
									statement.executeUpdate(query);
									JOptionPane.showMessageDialog(frame, "Saved Successfully");
									
								}
								else
								{
									String query="insert into "+company+".mr_customer_master(`cust_code`, `cust_name`, `type`, `category`, `sub_category`, `rating`, `terms_code`, `shipping_code`, `currency`, `sales_rep`, `disc`, `addr1`, `addr2`, `city`, `country`, `id_type`, `id_num`, `ph1`, `ph2`, `default_ar_acc`, `credit_limit`, `posted_bal`, `unposted_bal`, `avail_credit`, `last_sale_date`, `last_payment_date`, `last_paid_amt`, `created_date`, `modified_date`,TRN,status,open_bal,contact_person) values('"+code+"','"+name+"','"+type+"','"+categry+"','"+sub_categry+"','"+rating+"','"+terms+"','"+shipment+"','"+curr+"','"+sale_rep+"','"+disc+"','"+addr1+"','"+addr2+"','"+city+"','"+country+"','"+id_type+"','"+id_num+"','"+ph1+"','"+ph2+"','"+ar_acc+"','"+credit_lmt+"','"+posted_bal+"','"+unpost_bal+"','"+credit_lmt+"','"+last_sle_dt+"','"+last_paym_dt+"','"+last_paid_amt+"','"+formattedDate+"','"+formattedDate+"','"+trn+"','"+status+"','"+open_bal+"','"+contact_person+"') ";
									statement.executeUpdate(query);
									JOptionPane.showMessageDialog(frame, "Saved Successfully");
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
							frame.setVisible(false);
							if(Active.equals("1"))
							{
								if(Mainpage.SoftLang.equals("Ar"))
								{
									PosscreenArabic.txtDisc.requestFocus();
									PosscreenArabic.btnNewCustomer.setEnabled(true);
								}
								else
								{
									posscreen.txtDisc.requestFocus();
									posscreen.btnNewCustomer.setEnabled(true);
								}
								
								Active="0";
							}
							//posscreen.txtDisc.requestFocus();
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(659, 11, 70, 48);
		panel.add(menuItem_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(338, 158, 317, 128);
		panel_2.setBackground(new Color(243, 229, 216));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_9 = new JLabel("ID Type :");
		label_9.setBounds(12, 4, 76, 20);
		panel_2.add(label_9);
		
		comboBox_8 = new JComboBox<String>();
		comboBox_8.setBounds(100, 4, 208, 20);
		comboBox_8.setModel(new DefaultComboBoxModel<String>(new String[] {"EMIRATES ID", "VISA COPY", "LICENSE"}));
		panel_2.add(comboBox_8);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(100, 28, 208, 20);
		panel_2.add(textField_8);
		
		JLabel label_10 = new JLabel("ID Number :");
		label_10.setBounds(12, 28, 76, 20);
		panel_2.add(label_10);
		
		JLabel label_11 = new JLabel("Phone 1# :");
		label_11.setBounds(12, 52, 76, 20);
		panel_2.add(label_11);
		
		JLabel label_12 = new JLabel("Phone 2# :");
		label_12.setBounds(12, 76, 76, 20);
		panel_2.add(label_12);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(100, 76, 208, 20);
		panel_2.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setText("0");
		textField_10.setColumns(10);
		textField_10.setBounds(100, 52, 208, 20);
		panel_2.add(textField_10);
		
		JLabel lblContactPerson = new JLabel("Contact Person");
		lblContactPerson.setBounds(10, 100, 89, 20);
		panel_2.add(lblContactPerson);
		
		textField_19 = new JTextField();
		textField_19.setBounds(100, 100, 208, 20);
		panel_2.add(textField_19);
		textField_19.setColumns(10);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new accounts_master.SelectAccount(company,"cust_mstr",0, version,"","", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(search));
		button.setBounds(644, 284, 45, 23);
		
		panel.add(button);
		
		JLabel lblStatus = new JLabel("Status : ");
		lblStatus.setBounds(478, 126, 56, 16);
		panel.add(lblStatus);
		
		comboBox_9 = new JComboBox<String>();
		comboBox_9.setBounds(535, 123, 200, 22);
		comboBox_9.addItem("Active");
		comboBox_9.addItem("Inactive");
		panel.add(comboBox_9);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 279, 279, 231);
		panel.add(panel_3);
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(243, 229, 216));
		
		JLabel label = new JLabel("Type :");
		label.setBounds(0, 5, 98, 20);
		panel_3.add(label);
		
		JLabel label_1 = new JLabel("Category :");
		label_1.setBounds(0, 30, 98, 20);
		panel_3.add(label_1);
		
		JLabel label_2 = new JLabel("Vat Type :");
		label_2.setBounds(0, 55, 98, 20);
		panel_3.add(label_2);
		
		JLabel label_3 = new JLabel("Rating :");
		label_3.setBounds(0, 80, 98, 20);
		panel_3.add(label_3);
		
		JLabel label_4 = new JLabel("Terms Code :");
		label_4.setBounds(0, 105, 98, 20);
		panel_3.add(label_4);
		
		JLabel label_5 = new JLabel("Shipping Code :");
		label_5.setBounds(0, 130, 98, 20);
		panel_3.add(label_5);
		
		JLabel label_6 = new JLabel("Currency :");
		label_6.setBounds(0, 155, 98, 20);
		panel_3.add(label_6);
		
		JLabel label_7 = new JLabel("Sales Rep :");
		label_7.setBounds(0, 180, 98, 20);
		panel_3.add(label_7);
		
		JLabel label_8 = new JLabel("Discount % :");
		label_8.setBounds(0, 205, 98, 20);
		panel_3.add(label_8);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(100, 5, 175, 20);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"A/R", "CASH"}));
		panel_3.add(comboBox);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(100, 30, 175, 20);
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"NIL"}));
		panel_3.add(comboBox_1);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(100, 55, 175, 20);
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"INC","EXC","NON"}));
		panel_3.add(comboBox_2);
		
		comboBox_3 = new JComboBox<String>();
		comboBox_3.setBounds(100, 80, 175, 20);
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"GOOD"}));
		panel_3.add(comboBox_3);
		
		comboBox_4 = new JComboBox<String>();
		comboBox_4.setBounds(100, 105, 175, 20);
		comboBox_4.setModel(new DefaultComboBoxModel<String>(new String[] {"CASH", "BANK"}));
		panel_3.add(comboBox_4);
		
		comboBox_5 = new JComboBox<String>();
		comboBox_5.setBounds(100, 130, 175, 20);
		comboBox_5.setModel(new DefaultComboBoxModel<String>(new String[] {"BY ROAD"}));
		panel_3.add(comboBox_5);
		
		comboBox_6 = new JComboBox<String>();
		comboBox_6.setBounds(100, 155, 175, 20);
		comboBox_6.setModel(new DefaultComboBoxModel<String>(new String[] {"AED"}));
		panel_3.add(comboBox_6);
		
		comboBox_7 = new JComboBox<String>();
		comboBox_7.setBounds(100, 180, 175, 20);
		comboBox_7.setModel(new DefaultComboBoxModel<String>(new String[] {username}));
		panel_3.add(comboBox_7);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(100, 205, 175, 20);
		panel_3.add(textField_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 155, 259, 123);
		panel.add(panel_4);
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(243, 229, 216));
		
		JLabel label_13 = new JLabel("Address 1:");
		label_13.setBounds(0, 8, 70, 14);
		panel_4.add(label_13);
		
		JLabel label_14 = new JLabel("Address 2:");
		label_14.setBounds(0, 30, 70, 14);
		panel_4.add(label_14);
		
		JLabel lblTrn = new JLabel("TRN:");
		lblTrn.setBounds(0, 52, 70, 14);
		panel_4.add(lblTrn);
		
		JLabel label_16 = new JLabel("City :");
		label_16.setBounds(0, 74, 70, 14);
		panel_4.add(label_16);
		
		JLabel label_17 = new JLabel("Country :");
		label_17.setBounds(0, 96, 70, 14);
		panel_4.add(label_17);
		
		txtUae = new JTextField();
		txtUae.setText("UAE");
		txtUae.setColumns(10);
		txtUae.setBounds(72, 95, 183, 20);
		panel_4.add(txtUae);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(72, 72, 183, 20);
		panel_4.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(72, 49, 183, 20);
		panel_4.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(72, 26, 183, 20);
		panel_4.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(72, 3, 183, 20);
		panel_4.add(textField_7);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setBounds(338, 314, 306, 194);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textField_12 = new JTextField();
		textField_12.setText("0");
		textField_12.setBounds(126, 3, 160, 20);
		panel_1.add(textField_12);
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		textField_13.setBounds(126, 26, 160, 20);
		panel_1.add(textField_13);
		textField_13.setEditable(false);
		textField_13.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setBounds(126, 49, 160, 20);
		panel_1.add(textField_14);
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		
		textField_15 = new JTextField();
		textField_15.setBounds(126, 72, 160, 20);
		panel_1.add(textField_15);
		textField_15.setEditable(false);
		textField_15.setColumns(10);
		
		textField_16 = new JTextField();
		textField_16.setBounds(126, 95, 160, 20);
		panel_1.add(textField_16);
		textField_16.setEditable(false);
		textField_16.setColumns(10);
		
		textField_17 = new JTextField();
		textField_17.setBounds(126, 118, 160, 20);
		panel_1.add(textField_17);
		textField_17.setEditable(false);
		textField_17.setColumns(10);
		
		textField_18 = new JTextField();
		textField_18.setBounds(126, 141, 160, 20);
		panel_1.add(textField_18);
		textField_18.setEditable(false);
		textField_18.setColumns(10);
		
		JLabel lblCreditLimit = new JLabel("Credit Limit  # :");
		lblCreditLimit.setBounds(0, 3, 122, 20);
		panel_1.add(lblCreditLimit);
		
		JLabel lblPostedBalance = new JLabel("Advance Amount : ");
		lblPostedBalance.setBounds(0, 26, 122, 20);
		panel_1.add(lblPostedBalance);
		
		JLabel lblAvaliableCredit = new JLabel("Avaliable Credit :");
		lblAvaliableCredit.setBounds(0, 72, 122, 20);
		panel_1.add(lblAvaliableCredit);
		lblAvaliableCredit.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblLastSalesDate = new JLabel("Last Sales Date :");
		lblLastSalesDate.setBounds(0, 95, 122, 20);
		panel_1.add(lblLastSalesDate);
		
		JLabel lblUnpostedBalance = new JLabel("UnPosted Balance :");
		lblUnpostedBalance.setBounds(0, 49, 122, 20);
		panel_1.add(lblUnpostedBalance);
		lblUnpostedBalance.setForeground(Color.BLUE);
		lblUnpostedBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblLastPaymentsDate = new JLabel("Last Payments Date :");
		lblLastPaymentsDate.setBounds(-1, 118, 121, 20);
		panel_1.add(lblLastPaymentsDate);
		
		JLabel lblLastPaidAmount = new JLabel("Last Paid Amount :");
		lblLastPaidAmount.setBounds(-1, 141, 122, 20);
		panel_1.add(lblLastPaidAmount);
		
		JLabel lblOpeningBalance = new JLabel("Opening Balance : ");
		lblOpeningBalance.setBounds(0, 164, 122, 20);
		panel_1.add(lblOpeningBalance);
		
		textField_3 = new JTextField();
		textField_3.setText("0");
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(127, 164, 160, 20);
		panel_1.add(textField_3);
		
		JLabel lblImages = new JLabel("Images");
		lblImages.setBounds(10, 527, 56, 16);
		panel.add(lblImages);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(743, 162, 2, 459);
		panel.add(separator_4);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 518, 739, 2);
		panel.add(separator_2);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(78, 523, 123, 117);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		lblNewLabel.setBorder(border);
		panel.add(lblNewLabel);
		
		JLabel lblMoreImages = new JLabel("More Images...");
		lblMoreImages.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if(textField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Select One Customer");
					}
					else
					{
						File configFile = new File("C:/MasterRetails/config.properties");
						FileReader reader = new FileReader(configFile);
					    Properties props = new Properties();
						props.load(reader);
						String imageurl = props.getProperty("image");
					    reader.close();
					    File path=File.createTempFile( imageurl,""+textField.getText()+"-1.jpg");
					    if(path.exists())
					    {
					    	new cust_image_viewer(company,username,store_name,version,textField.getText());
					    }
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		lblMoreImages.setBounds(213, 624, 117, 16);
		panel.add(lblMoreImages);
		
		JButton btnOpeningBalance = new JButton("OPENING BALANCE");
		btnOpeningBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new sales_distribution.opening_bal_cust(company,username,store_name, version,"2");
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnOpeningBalance.setBounds(342, 30, 165, 25);
		panel.add(btnOpeningBalance);
		
		JButton btnCustmorItemprice = new JButton("Custmor ItemPrice");
		btnCustmorItemprice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(textField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Select One Customer");
					}
					else
					{
						frame.setAlwaysOnTop(false);
						new Cust_item_price(company,textField.getText(),textField_1.getText(),language);	
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCustmorItemprice.setBounds(186, 30, 152, 25);
		panel.add(btnCustmorItemprice);
		
		JButton btnDeleteCustomer = new JButton("Delete Customer");
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(textField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Select One Customer");
					}
					else
					{
						String query="SELECT * FROM "+company+".mr_customer_master inner join "+company+".mr_pos_settlement_history on mr_pos_settlement_history.cust_code=mr_customer_master.cust_code  where mr_customer_master.cust_code='"+textField.getText()+"'";
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						ResultSet resultSet = statement.executeQuery(query);
						if(resultSet.next())
						{
							JOptionPane.showMessageDialog(frame, "Sorry, Some Transactions Found For This Customer");
						}
						else
						{
							String query1="SELECT * FROM "+company+".mr_customer_master INNER join "+company+".mr_pos_settlement_cash on mr_pos_settlement_cash.cust_code=mr_customer_master.cust_code where mr_customer_master.cust_code='"+textField.getText()+"'";
							Statement statement1=con.createStatement();  
							ResultSet resultSet1 = statement1.executeQuery(query1);
							if(resultSet1.next())
							{
								JOptionPane.showMessageDialog(frame, "Sorry, Some Transactions Found For This Customer");
							}
							else
							{
								String query11="Delete FROM "+company+".mr_customer_master where mr_customer_master.cust_code='"+textField.getText()+"'";
								statement.executeUpdate(query11);
								JOptionPane.showMessageDialog(frame, "Deleted Successfully");
							}
						}
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDeleteCustomer.setBounds(605, 521, 134, 44);
		panel.add(btnDeleteCustomer);
		
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					textField.setText("");
					textField.setEditable(true);
					textField_1.setText("");
					textField_2.setText("");
					txtUae.setText("");
					textField_4.setText("");
					textField_5.setText("");
					textField_6.setText("");
					textField_7.setText("");
					textField_8.setText("");
					textField_9.setText("");
					textField_10.setText("0");
					textField_11.setText("0101013001");
					textField_12.setText("0");
					textField_13.setText("");
					textField_3.setText("0");
					textField_19.setText("");
					textField_14.setText("");
					textField_15.setText("");
					textField_16.setText("");
					textField_17.setText("");
					textField_18.setText("");
					textField.requestFocus();
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(575, 11, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JButton btnUpdateCredit = new JButton("Update Credit");
		btnUpdateCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);    
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement(); 
					double opencr=0;
					double opendr=0;
					String cust_id=textField.getText();
					//String as="select sum(grand_total) from "+company+".mr_pos_settlement_cash where "+company+".mr_pos_settlement_cash.cust_code='"+cust_name+"' and tran_date between '"+date1+"' and '"+date2+"'";
					String custsumary="select "+company+".mr_pos_settlement_cash.created_date as tran_date,"+company+".mr_pos_settlement_cash.created_date as post_date,"+company+".mr_pos_settlement_cash.receipt_no as tran_num,	'Invoice' as type,	'' as cheque,	if(type='A/R',0,"+company+".mr_pos_settlement_cash.grand_total) as cramt,	"+company+".mr_pos_settlement_cash.grand_total as dramt,	'0' as disc,	'' as inv_num,	type as method from "+company+".mr_pos_settlement_cash where "+company+".mr_pos_settlement_cash.cust_code='"+cust_id+"' and "+company+".mr_pos_settlement_cash.created_date between '1900-01-01' AND '"+formattedDate+" 23:59:59' UNION select	"+company+".mr_pos_settlement_history.created_date as tran_date,	"+company+".mr_pos_settlement_history.created_date as post_date,	"+company+".mr_pos_settlement_history.receipt_no as tran_num,	'Invoice' as type,	'' as cheque,	if(type='A/R',0,"+company+".mr_pos_settlement_history.total+"+company+".mr_pos_settlement_history.vat) as cramt,	"+company+".mr_pos_settlement_history.total+"+company+".mr_pos_settlement_history.vat as dramt,	'0' as disc,	'' as inv_num,	type as method from "+company+".mr_pos_settlement_history where "+company+".mr_pos_settlement_history.cust_code='"+cust_id+"' and "+company+".mr_pos_settlement_history.created_date between '1900-01-01' AND '"+formattedDate+" 23:59:59' union select "+company+".mr_customer_payment.created_date as tran_date,	"+company+".mr_customer_payment.created_date as post_date,	"+company+".mr_customer_payment.seq_num as tran_num,	'Payment' as type,	'' as cheque,	if("+company+".mr_customer_payment.amount='','0',"+company+".mr_customer_payment.amount) as cramt,	'0' as dramt,	"+company+".mr_customer_payment.discount as disc,	'' as inv_num,	if(paycode='CASH',paycode,'A/R') as method from "+company+".mr_customer_payment where  "+company+".mr_customer_payment.cust_code='"+cust_id+"' and "+company+".mr_customer_payment.created_date between '1900-01-01' AND '"+formattedDate+" 23:59:59' union select	"+company+".mr_sd_customer_payment_details.issue_date as tran_date,	"+company+".mr_sd_customer_payment_details.created_date as post_date,	"+company+".mr_sd_customer_payment_details.seq_num as tran_num,	if("+company+".mr_sd_customer_payment_details.status='2','Payment','Invoice') as type,	cheque_no as cheque,	"+company+".mr_sd_customer_payment_details.dr_amt as cramt,	"+company+".mr_sd_customer_payment_details.cr_amt as dramt,	"+company+".mr_sd_customer_payment_details.disc_amt as disc,	"+company+".mr_sd_customer_payment_details.ref_tran_num as inv_num,	if(paycode='CASH',paycode,'A/R') as method from "+company+".mr_sd_customer_payment_details where "+company+".mr_sd_customer_payment_details.ref_cust_id='"+cust_id+"' and "+company+".mr_sd_customer_payment_details.created_date between '1900-01-01' AND '"+formattedDate+" 23:59:59' and  "+company+".mr_sd_customer_payment_details.status!='4' order by tran_date";
					ResultSet resultSet = statement.executeQuery(custsumary);
					while(resultSet.next())
					{
						opencr += Double.parseDouble(resultSet.getString("cramt"));
						opendr += Double.parseDouble(resultSet.getString("dramt"));
					}
					double total=opendr-opencr;
					String query="update "+company+".mr_customer_master set avail_credit=credit_limit-'"+total+"' where cust_code='"+cust_id+"'  ";
					statement.executeUpdate(query); 
					JOptionPane.showMessageDialog(frame, "Updated Successfully");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		btnUpdateCredit.setBounds(615, 577, 118, 44);
		panel.add(btnUpdateCredit);
		frame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, textField_1, textField_7, textField_6, textField_5, textField_4, txtUae, comboBox, comboBox_1, comboBox_2, comboBox_3, comboBox_4, comboBox_5, comboBox_6, comboBox_7, textField_2, comboBox_8, textField_8, textField_10, textField_9, textField_19, button, textField_12, frame.getContentPane(), panel, checkBox, separator, lblRegisterControl, lblCustomerCode, btnNewButton, lblCustomerName, btnInvoices, btnStatement, separator_1, separator_3, lblDeafaultArAccount, textField_11, menuItem, menuItem_2, panel_2, label_9, label_10, label_11, label_12, lblContactPerson, lblStatus, comboBox_9, panel_3, label, label_1, label_2, label_3, label_4, label_5, label_6, label_7, label_8, panel_4, label_13, label_14, lblTrn, label_16, label_17, panel_1, textField_13, textField_14, textField_15, textField_16, textField_17, textField_18, lblCreditLimit, lblPostedBalance, lblAvaliableCredit, lblLastSalesDate, lblUnpostedBalance, lblLastPaymentsDate, lblLastPaidAmount, lblOpeningBalance, textField_3, lblImages, separator_4, separator_2, lblNewLabel, lblMoreImages, btnOpeningBalance, btnCustmorItemprice}));
		
		if(Mainpage.activeSaD==true)
		{
			JButton btnInvoice = new JButton("Invoice");
			btnInvoice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if(textField.getText().equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Select One Customer");
						}
						else
						{
							frame.setAlwaysOnTop(false);
							new sales_distribution.invoice_list(company, username, store_name, version, "2", textField.getText());
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			});
			btnInvoice.setBounds(356, 70, 117, 44);
			panel.add(btnInvoice);
		}
		
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_users where user_name='"+username+"' ");
			if(resultSet.next())
			{
				String userauth=resultSet.getString("user_type");
				if(!userauth.equals("Administrator"))
				{
					btnOpeningBalance.setVisible(false);
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		if(pagetype.equals("POS"))
		{
			if(Mainpage.SoftLang.equals("Ar"))
			{
				if(!PosscreenArabic.textField_9.getText().trim().equals("1"))
				{
					cust_disp(company, PosscreenArabic.textField_9.getText());
				}
			}
			else
			{
				if(!posscreen.textField_9.getText().trim().equals("1"))
				{
					cust_disp(company, posscreen.textField_9.getText());
				}
			}
			
		}
		
		
	}
	public static ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
	static void cust_disp(String company, String cust_code)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_customer_master where cust_code='"+cust_code+"' ");
			if(resultSet.next())
			{
				textField.setText(resultSet.getString("cust_code"));
				textField.setEditable(false);
				textField_1.setText(resultSet.getString("cust_name"));
				textField_2.setText(resultSet.getString("disc"));
				txtUae.setText(resultSet.getString("country"));
				textField_4.setText(resultSet.getString("city"));
				textField_5.setText(resultSet.getString("TRN"));
				textField_6.setText(resultSet.getString("addr2"));
				textField_7.setText(resultSet.getString("addr1"));
				textField_8.setText(resultSet.getString("id_num"));
				textField_9.setText(resultSet.getString("ph2"));
				textField_10.setText(resultSet.getString("ph1"));
				textField_11.setText(resultSet.getString("default_ar_acc"));
				textField_12.setText(resultSet.getString("credit_limit"));
				textField_13.setText(resultSet.getString("advance_amt"));
				textField_14.setText(resultSet.getString("unposted_bal"));
				textField_15.setText(resultSet.getString("avail_credit"));
				textField_16.setText(resultSet.getString("last_sale_date"));
				textField_17.setText(resultSet.getString("last_payment_date"));
				textField_18.setText(resultSet.getString("last_paid_amt"));
				textField_3.setText(resultSet.getString("open_bal"));
				textField_19.setText(resultSet.getString("contact_person"));
				
				comboBox.setSelectedItem(resultSet.getString("type"));
				comboBox_1.setSelectedItem(resultSet.getString("category"));
				comboBox_2.setSelectedItem(resultSet.getString("sub_category"));
				comboBox_3.setSelectedItem(resultSet.getString("rating"));
				comboBox_4.setSelectedItem(resultSet.getString("terms_code"));
				comboBox_5.setSelectedItem(resultSet.getString("shipping_code"));
				comboBox_6.setSelectedItem(resultSet.getString("currency"));
				comboBox_7.setSelectedItem(resultSet.getString("sales_rep"));
				comboBox_8.setSelectedItem(resultSet.getString("id_type"));
				if(resultSet.getString("status").equals("1"))
				{
					comboBox_9.setSelectedItem("Active");
				}
				else
				{
					comboBox_9.setSelectedItem("Inactive");
				}
				
				File configFile = new File("C:/MasterRetails/config.properties");
				FileReader reader = new FileReader(configFile);
			    Properties props = new Properties();
				props.load(reader);
				String imageurl = props.getProperty("image");
			    reader.close();
			    File path=File.createTempFile( imageurl,""+cust_code+"-1.jpg");
			    if(path.exists())
			    {
			    	lblNewLabel.setIcon(ResizeImage(imageurl+"\\"+cust_code+"-1.jpg"));
			    }
			    
				
			}
			else
			{
				int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Create New Customer","WARNING",JOptionPane.YES_NO_OPTION);
		        if(cofrm_bx==0)
		        {
		        	textField.setText("NEW");
		        	textField.setEditable(false);
		        	textField_1.requestFocus();
		        }
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
