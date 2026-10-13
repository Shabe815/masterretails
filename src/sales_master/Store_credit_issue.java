package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import org.json.JSONException;

import CafePro.Mainpage;

import javax.swing.JTextField;
import javax.swing.JPanel;

public class Store_credit_issue {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	static JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	static JTextField textField_13;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Store_credit_issue window = new Store_credit_issue("mr_pro","admin", "1.0", "S0101", "-123", "WALKIN", "", "S01","1", "1900006","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param vendor_id 
	 */
	public Store_credit_issue(String company,String username,String version,String reg_nme,String amount,String cust_name,String cust_code,String store,String store_credit_validity,String invoice_num,String language) throws JSONException {
		initialize(company,username, version,reg_nme,amount,cust_name,cust_code,store,store_credit_validity,invoice_num, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param cust_code 
	 * @param cust_name 
	 * @param amount 
	 * @param reg_nme 
	 * @param version 
	 * @param username 
	 * @param company 
	 * @param store 
	 * @param store_credit_validity 
	 * @param invoice_num 
	 * @param vendor_id 
	 */
	private void initialize(final String company, final String username, final String version, final String reg_nme, final String amount, final String cust_name,final String cust_code, final String store,final String store_credit_validity, final String invoice_num,final String language) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
	    frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 540);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		
		frame.setLocationRelativeTo(null);
		
		String[] amounts=amount.split("-");
		final String amount_ttl=amounts[1];
		
		JLabel lblCompanySetup = new JLabel("Issue Store Credit");
		lblCompanySetup.setBounds(21, 11, 228, 44);
		Image user=new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		lblCompanySetup.setIcon(new ImageIcon(user));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(482, 7, 60, 48);
		frame.getContentPane().add(mntmNewMenuItem);

		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mntmRefresh.setBackground(new Color(243, 229, 216));
		mntmRefresh.setIcon(new ImageIcon(refresh));
		mntmRefresh.setBounds(542, 7, 80, 48);
		mntmRefresh.setBorder(null);
		frame.getContentPane().add(mntmRefresh);

		JMenuItem mntmSave = new JMenuItem("Issue");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);
				    final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
				    final Calendar cal = Calendar.getInstance();
				    
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement();  
					Statement statement1=con.createStatement();  
					ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store_credit where ref_invoice_num='"+invoice_num+"' ");
					if(!resultSet.next())
					{
						String query="insert into "+company+".mr_store_credit( `store_id`, `ref_invoice_num`, `username`, `register`, `issue_date`, `valid_upto`, `cust_code`, amount,`cust_name`, `address1`, `address2`, `address3`, `mobile`, `remark`, `created_date`, `modified_date`) values('"+textField.getText()+"','"+invoice_num+"','"+textField_1.getText()+"','"+textField_2.getText()+"','"+textField_3.getText()+"','"+textField_4.getText()+"','"+textField_13.getText()+"','"+textField_6.getText()+"','"+textField_7.getText()+"','"+textField_8.getText()+"','"+textField_9.getText()+"','"+textField_10.getText()+"','"+textField_11.getText()+"','"+textField_12.getText()+"','"+formattedDate+"','"+formattedDate+"')";
						statement.executeUpdate(query);
						
						ResultSet resultSet1 = statement1.executeQuery("select * from "+company+".mr_pos_settlement_cash where receipt_no='"+invoice_num+"' and reg_num='"+reg_nme+"' ");
						if(!resultSet1.next())
						{
							if(Mainpage.SoftLang.equals("Ar"))
							{
								String query1="insert into "+company+".mr_pos_settlement_cash (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, type,store_id, `created_date`,cash_paid) values('"+reg_nme+"','"+invoice_num+"','"+username+"','"+times.format(cal.getTime())+"','"+amount+"','"+textField_7.getText()+"','"+PosscreenArabic.textField_1.getText()+"','"+PosscreenArabic.textField_2.getText()+"','"+PosscreenArabic.textField_4.getText()+"','RETURN','"+store+"','"+formattedDate+"','0') ";
								statement.executeUpdate(query1);
								if(PosscreenArabic.old_recipt.equals(""))
								{
									ResultSet resultSet2 = statement1.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_nme+"' ");
									if(resultSet2.next())
									{
										int nxt_num=Integer.parseInt(resultSet2.getString("next_num"))+1;
										String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+reg_nme+"' ";
										statement1.executeUpdate(update_receipt_num);
									}
								}
								for(int i=0;i<PosscreenArabic.model.getRowCount();i++)
								{
									String item_code="";
									String item_cost="0";
									String query3="SELECT * FROM "+company+".mr_item_master where item_code='"+PosscreenArabic.model.getValueAt(i, 1).toString()+"' or '"+PosscreenArabic.model.getValueAt(i, 1).toString()+"' IN ("+company+".mr_item_master.barcode1,"+company+".mr_item_master.barcode2,"+company+".mr_item_master.barcode3,"+company+".mr_item_master.barcode4,"+company+".mr_item_master.barcode5,"+company+".mr_item_master.barcode6,"+company+".mr_item_master.barcode7,"+company+".mr_item_master.barcode8,"+company+".mr_item_master.barcode9,"+company+".mr_item_master.barcode10,"+company+".mr_item_master.barcode11,"+company+".mr_item_master.barcode12,"+company+".mr_item_master.barcode13,"+company+".mr_item_master.barcode14,"+company+".mr_item_master.barcode15,"+company+".mr_item_master.barcode16,"+company+".mr_item_master.barcode17,"+company+".mr_item_master.barcode18)";
									ResultSet resultSet3= statement1.executeQuery(query3);
									if(resultSet3.next())
									{
										item_code=resultSet3.getString("item_code");
										item_cost=resultSet3.getString("item_cost");
									}
									String query4="insert into "+company+".mr_pos_settlement_cash_items ( `ref_receipt_num`,ref_reg_num, `barcode`, `item_desc`,item_code,item_cost, `unit`, `unit_price`, `qty`, `total`, `vat`,discount, `created_date`) values ('"+invoice_num+"','"+reg_nme+"','"+PosscreenArabic.model.getValueAt(i, 1).toString()+"','"+PosscreenArabic.model.getValueAt(i, 2).toString()+"','"+item_code+"','"+item_cost+"','"+PosscreenArabic.model.getValueAt(i, 3).toString()+"','"+PosscreenArabic.model.getValueAt(i,4).toString()+"','"+PosscreenArabic.model.getValueAt(i, 5).toString()+"','"+PosscreenArabic.model.getValueAt(i, 7).toString()+"','"+PosscreenArabic.model.getValueAt(i, 8).toString()+"','"+PosscreenArabic.model.getValueAt(i, 6).toString()+"','"+formattedDate+"') ";
									statement1.executeUpdate(query4);
								}
								String query5="SELECT * FROM "+company+".mr_pos_hold_invoice where receipt_no='"+invoice_num+"' and reg_num='"+reg_nme+"' ";
								ResultSet resultSet5= statement1.executeQuery(query5);
								if(resultSet5.next())
								{
									String query6="delete FROM "+company+".mr_pos_hold_invoice where receipt_no='"+invoice_num+"' and reg_num='"+reg_nme+"' ";
									statement.executeUpdate(query6);
									String query7="delete FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+invoice_num+"' and ref_reg_no='"+reg_nme+"' ";
									statement1.executeUpdate(query7);
								}
								int copy=1;
								if(PosscreenArabic.change_hold_type.equals("1"))
								{
									copy=2;
								}
								report_master.Bill_template.report(invoice_num, con,PosscreenArabic.printer,PosscreenArabic.reg_nme,"",company,copy);
								//new sales_master.cash_change(company, username, store, amount, textField_19.getText(), decimal);
								sales_master.PosscreenArabic.get_register_name(company,  username, store,con,reg_nme);
								sales_master.PosscreenArabic.txtDisc.setText("");
								sales_master.PosscreenArabic.btnNewButton.setText("WALK IN");
								sales_master.PosscreenArabic.textField_6.setText("");
								
								sales_master.PosscreenArabic.textField_4.setText("0");
								sales_master.PosscreenArabic.textField_2.setText("0");
								sales_master.PosscreenArabic.textField.setText("0.00");
								sales_master.PosscreenArabic.textField_1.setText("0.00");
								sales_master.PosscreenArabic.textField_3.setText("0.00");
								sales_master.PosscreenArabic.textField_5.setText("");
								sales_master.PosscreenArabic.model.setRowCount(0);
								sales_master.PosscreenArabic.txtDisc.requestFocus();
							}
							else
							{
								String query1="insert into "+company+".mr_pos_settlement_cash (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, type,store_id, `created_date`,cash_paid) values('"+reg_nme+"','"+invoice_num+"','"+username+"','"+times.format(cal.getTime())+"','"+amount+"','"+textField_7.getText()+"','"+posscreen.textField_1.getText()+"','"+posscreen.textField_2.getText()+"','"+posscreen.textField_4.getText()+"','RETURN','"+store+"','"+formattedDate+"','0') ";
								statement.executeUpdate(query1);
								if(posscreen.old_recipt.equals(""))
								{
									ResultSet resultSet2 = statement1.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_nme+"' ");
									if(resultSet2.next())
									{
										int nxt_num=Integer.parseInt(resultSet2.getString("next_num"))+1;
										String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+reg_nme+"' ";
										statement1.executeUpdate(update_receipt_num);
									}
								}
								for(int i=0;i<posscreen.model.getRowCount();i++)
								{
									String item_code="";
									String item_cost="0";
									String query3="SELECT * FROM "+company+".mr_item_master where item_code='"+posscreen.model.getValueAt(i, 1).toString()+"' or '"+posscreen.model.getValueAt(i, 1).toString()+"' IN ("+company+".mr_item_master.barcode1,"+company+".mr_item_master.barcode2,"+company+".mr_item_master.barcode3,"+company+".mr_item_master.barcode4,"+company+".mr_item_master.barcode5,"+company+".mr_item_master.barcode6,"+company+".mr_item_master.barcode7,"+company+".mr_item_master.barcode8,"+company+".mr_item_master.barcode9,"+company+".mr_item_master.barcode10,"+company+".mr_item_master.barcode11,"+company+".mr_item_master.barcode12,"+company+".mr_item_master.barcode13,"+company+".mr_item_master.barcode14,"+company+".mr_item_master.barcode15,"+company+".mr_item_master.barcode16,"+company+".mr_item_master.barcode17,"+company+".mr_item_master.barcode18)";
									ResultSet resultSet3= statement1.executeQuery(query3);
									if(resultSet3.next())
									{
										item_code=resultSet3.getString("item_code");
										item_cost=resultSet3.getString("item_cost");
									}
									String query4="insert into "+company+".mr_pos_settlement_cash_items ( `ref_receipt_num`,ref_reg_num, `barcode`, `item_desc`,item_code,item_cost, `unit`, `unit_price`, `qty`, `total`, `vat`,discount, `created_date`) values ('"+invoice_num+"','"+reg_nme+"','"+posscreen.model.getValueAt(i, 1).toString()+"','"+posscreen.model.getValueAt(i, 2).toString()+"','"+item_code+"','"+item_cost+"','"+posscreen.model.getValueAt(i, 3).toString()+"','"+posscreen.model.getValueAt(i,4).toString()+"','"+posscreen.model.getValueAt(i, 5).toString()+"','"+posscreen.model.getValueAt(i, 7).toString()+"','"+posscreen.model.getValueAt(i, 8).toString()+"','"+posscreen.model.getValueAt(i, 6).toString()+"','"+formattedDate+"') ";
									statement1.executeUpdate(query4);
								}
								String query5="SELECT * FROM "+company+".mr_pos_hold_invoice where receipt_no='"+invoice_num+"' and reg_num='"+reg_nme+"' ";
								ResultSet resultSet5= statement1.executeQuery(query5);
								if(resultSet5.next())
								{
									String query6="delete FROM "+company+".mr_pos_hold_invoice where receipt_no='"+invoice_num+"' and reg_num='"+reg_nme+"' ";
									statement.executeUpdate(query6);
									String query7="delete FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+invoice_num+"' and ref_reg_no='"+reg_nme+"' ";
									statement1.executeUpdate(query7);
								}
								int copy=1;
								if(posscreen.change_hold_type.equals("1"))
								{
									copy=2;
								}
								report_master.Bill_template.report(invoice_num, con,posscreen.printer,posscreen.reg_nme,"",company,copy);
								//new sales_master.cash_change(company, username, store, amount, textField_19.getText(), decimal);
								sales_master.posscreen.get_register_name(company,  username, store,con,reg_nme);
								sales_master.posscreen.txtDisc.setText("");
								sales_master.posscreen.btnNewButton.setText("WALK IN");
								sales_master.posscreen.textField_6.setText("");
								
								sales_master.posscreen.textField_4.setText("0");
								sales_master.posscreen.textField_2.setText("0");
								sales_master.posscreen.textField.setText("0.00");
								sales_master.posscreen.textField_1.setText("0.00");
								sales_master.posscreen.textField_3.setText("0.00");
								sales_master.posscreen.textField_5.setText("");
								sales_master.posscreen.model.setRowCount(0);
								sales_master.posscreen.txtDisc.requestFocus();
							}
							
							
						}
						JOptionPane.showMessageDialog(frame, "Store Credit Issued Succesfully");
						
						frame.setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "This Store Credit already saved");
					}
					
				} catch (Exception e1) {
					System.err.println(e1);
				}
			}

		});
		mntmSave.setBackground(new Color(243, 229, 216));
		mntmSave.setIcon(new ImageIcon(save));
		mntmSave.setBounds(624, 7, 70, 48);
		mntmSave.setBorder(null);
		frame.getContentPane().add(mntmSave);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 68, 670, 120);
		frame.getContentPane().add(panel);
		panel.setBackground(new Color(243, 229, 216));
		panel.setLayout(null);
		
		JLabel lblStore = new JLabel("Store :");
		lblStore.setBounds(33, 13, 56, 22);
		panel.add(lblStore);
		
		textField = new JTextField(store);
		textField.setEditable(false);
		textField.setBounds(122, 13, 188, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(username);
		textField_1.setEditable(false);
		textField_1.setBounds(122, 48, 188, 22);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(reg_nme);
		textField_2.setEditable(false);
		textField_2.setBounds(122, 83, 188, 22);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblRegister = new JLabel("Register : ");
		lblRegister.setBounds(27, 83, 65, 22);
		panel.add(lblRegister);
		
		JLabel lblCashier = new JLabel("Cashier : ");
		lblCashier.setBounds(33, 48, 56, 22);
		panel.add(lblCashier);
		
		JLabel lblStoreCredit = new JLabel("Store Credit # :");
		lblStoreCredit.setBounds(334, 83, 98, 22);
		panel.add(lblStoreCredit);
		
		textField_5 = new JTextField("1");
		textField_5.setEditable(false);
		textField_5.setBounds(459, 83, 184, 22);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		Calendar dateAndTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		textField_3 = new JTextField(sdf.format(dateAndTime.getTime()));
		textField_3.setEditable(false);
		textField_3.setBounds(456, 13, 181, 22);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		
		dateAndTime.add(Calendar.DATE, Integer.parseInt(store_credit_validity));
		textField_4 = new JTextField(sdf.format(dateAndTime.getTime()));
		textField_4.setEditable(false);
		textField_4.setBounds(456, 48, 181, 22);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		
		JLabel lblIssueDate = new JLabel("Issue Date : ");
		lblIssueDate.setBounds(343, 13, 80, 22);
		panel.add(lblIssueDate);
		
		JLabel lblValidUpto = new JLabel("Valid Upto :");
		lblValidUpto.setBounds(343, 48, 80, 22);
		panel.add(lblValidUpto);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 187, 668, 318);
		frame.getContentPane().add(panel_1);
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setLayout(null);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(12, 52, 44, 22);
		panel_1.add(lblAmount);
		
		
		textField_6 = new JTextField(amount_ttl);
		textField_6.setEditable(false);
		textField_6.setBounds(130, 52, 269, 22);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(12, 89, 101, 22);
		panel_1.add(lblCustomerName);
		
		textField_7 = new JTextField(cust_name);
		textField_7.setBounds(130, 89, 500, 22);
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Cust_Chooser(company, "store_credit", username,store , "", "", version, "1");
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(serch));
		button.setBounds(257, 17, 35, 22);
		panel_1.add(button);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(12, 126, 56, 22);
		panel_1.add(lblAddress);
		
		textField_8 = new JTextField();
		textField_8.setBounds(130, 126, 500, 22);
		panel_1.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(130, 163, 500, 22);
		panel_1.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(130, 200, 500, 22);
		panel_1.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setBounds(12, 237, 56, 22);
		panel_1.add(lblMobile);
		
		textField_11 = new JTextField();
		textField_11.setBounds(130, 237, 221, 22);
		panel_1.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblRemark = new JLabel("Remark");
		lblRemark.setBounds(12, 274, 56, 22);
		panel_1.add(lblRemark);
		
		textField_12 = new JTextField();
		textField_12.setBounds(130, 274, 500, 22);
		panel_1.add(textField_12);
		textField_12.setColumns(10);
		
		JLabel lblCustomerCode = new JLabel("Customer Code");
		lblCustomerCode.setBounds(12, 15, 101, 22);
		panel_1.add(lblCustomerCode);
		
		textField_13 = new JTextField(cust_code);
		textField_13.setEditable(false);
		textField_13.setBounds(130, 17, 129, 22);
		panel_1.add(textField_13);
		textField_13.setColumns(10);
		
		JButton btnNew = new JButton("NEW");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setAlwaysOnTop(false);
				new Customer_master(company,username,reg_nme,store, version,"",language);
				//JOptionPane.setRootFrame(new Inv_More(company, version));
				//JOptionPane.getRootFrame(sales_master.Customer_master.cust_disp(company, "A0001"));
				//JOptionPane.
			}
		});
		btnNew.setBounds(302, 17, 97, 25);
		panel_1.add(btnNew);
	}
}
