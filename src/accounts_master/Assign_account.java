package accounts_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import org.json.JSONException;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class Assign_account {

	private static JFrame frame;
	public static JTextField textField;
	public static JTextField txtDubai;
	public static JTextField txtacc_code;
	public static JTextField textField_1;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_4;
	public static JTextField textField_5;
	public static JTextField textField_6;
	public static JTextField textField_7;
	public static JTextField textField_8;
	public static JTextField textField_9;
	public static JTextField textField_10;
	public static JCheckBox chckbxActiveForTransaction;
	static JTextField textField_11;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Assign_account window = new Assign_account("mr_pro", "1.0","S01","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Assign_account(String company, String version, String store, String language) {
		initialize(company,version,store,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param store 
	 * @param language 
	 */
	private void initialize(final String company,final String version, final String store, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
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
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBounds(470, 7, 60, 48);
		frame.getContentPane().add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		if(language.equals("Arabic"))
		{
			menuItem_1.setText("Ì‰⁄‘");
		}
		menuItem_1 = new JMenuItem("Refresh",KeyEvent.VK_F5);
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBounds(530, 7, 80, 48);
		frame.getContentPane().add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
		menuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String group_code=textField.getText();
				String group_name=txtDubai.getText();
				if(group_code.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter or Select Group code «·—Ã«¡ ≈œŒ«· √Ê  ÕœÌœ —„“ «·„Ã„Ê⁄…");
				}
				else if(group_name.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter Group Name «·—Ã«¡ ≈œŒ«· «”„ «·„Ã„Ê⁄…");
				}
				else
				{
					int status;
					if(chckbxActiveForTransaction.isSelected())
					{
						status=1;
					}
					else
					{
						status=0;
					}
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					try {
						statement = con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_assign_account_group where group_code='"+group_code+"' ");
						if(resultSet.next())
						{
							String query="update "+company+".mr_assign_account_group set status='"+status+"',inv_acc='"+txtacc_code.getText()+"',inv_cash_po='"+textField_1.getText()+"',inv_cash_pr='"+textField_2.getText()+"',cost_sale_acc='"+textField_3.getText()+"',inv_adj='"+textField_4.getText()+"',sale_acc='"+textField_5.getText()+"',sale_retn='"+textField_6.getText()+"',inv_transfer='"+textField_7.getText()+"',def_payable='"+textField_8.getText()+"',cost_correction='"+textField_9.getText()+"',tax_input='"+textField_10.getText()+"',tax_output='"+textField_11.getText()+"',modified_date='"+formattedDate+"',acc_store='"+store+"'  where group_code='"+group_code+"' ";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully  „ «·Õ›Ÿ »‰Ã«Õ");
						}
						else
						{
							String query="insert into "+company+".mr_assign_account_group (`acc_store`, `group_code`, `group_name`, `status`, `inv_acc`, `inv_cash_po`, `inv_cash_pr`, `cost_sale_acc`, `inv_adj`, `sale_acc`, `sale_retn`, `inv_transfer`, `def_payable`, `cost_correction`, `tax_input`, `tax_output`, `created_date`, `modified_date`) values ('"+store+"','"+group_code+"','"+group_name+"','"+status+"','"+txtacc_code.getText()+"','"+textField_1.getText()+"','"+textField_2.getText()+"','"+textField_3.getText()+"','"+textField_4.getText()+"','"+textField_5.getText()+"','"+textField_6.getText()+"','"+textField_7.getText()+"','"+textField_8.getText()+"','"+textField_9.getText()+"','"+textField_10.getText()+"','"+textField_11.getText()+"','"+formattedDate+"','"+formattedDate+"')";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully  „ «·Õ›Ÿ »‰Ã«Õ");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBounds(612, 7, 142, 48);
		frame.getContentPane().add(menuItem_2);
		
		
		
		
		JLabel lblCompanyName = new JLabel("Account Group Code :");
		if(language.equals("Arabic"))
		{
			lblCompanyName.setText("—„“ „Ã„Ê⁄… «·Õ”«»:");
		}
		lblCompanyName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCompanyName.setBounds(21, 79, 142, 21);
		frame.getContentPane().add(lblCompanyName);
		
		JLabel lblAddress = new JLabel("Group Name :");
		if(language.equals("Arabic"))
		{
			lblAddress.setText("«”„ «·„Ã„Ê⁄… :");
		}
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(21, 122, 102, 21);
		frame.getContentPane().add(lblAddress);
		
		Image reports = new ImageIcon(this.getClass().getResource("/reports.png")).getImage();
		JLabel lblCompanySetup = new JLabel("Inventory Accounts");
		if(language.equals("Arabic"))
		{
			lblCompanySetup.setText("Õ”«»«  «·„Œ“Ê‰");
		}
		lblCompanySetup.setIcon(new ImageIcon(reports));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCompanySetup.setBounds(21, 11, 228, 44);
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		textField = new JTextField();
		textField.setBounds(175, 80, 408, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtDubai = new JTextField();
		txtDubai.setBounds(175, 123, 445, 20);
		frame.getContentPane().add(txtDubai);
		txtDubai.setColumns(10);
		
		Image search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Acc_group_list(company,"assign_acc", version,"", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(search));
		btnNewButton.setBounds(584, 77, 36, 23);
		frame.getContentPane().add(btnNewButton);
		
		chckbxActiveForTransaction = new JCheckBox("Active For Transaction");
		if(language.equals("Arabic"))
		{
			chckbxActiveForTransaction.setText("‰‘ÿ ··„⁄«„·…");
		}
		chckbxActiveForTransaction.setBounds(175, 163, 172, 25);
		frame.getContentPane().add(chckbxActiveForTransaction);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 197, 694, 2);
		frame.getContentPane().add(separator_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 207, 672, 424);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton button_10 = new JButton("");
		button_10.setBounds(570, 353, 36, 21);
		panel.add(button_10);
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"acc_group_11",0, version,"", "",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_10.setIcon(new ImageIcon(search));
		
		textField_10 = new JTextField();
		textField_10.setBounds(200, 353, 371, 21);
		panel.add(textField_10);
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		
		JLabel lblReserved = new JLabel("12. Default Tax Output");
		if(language.equals("Arabic"))
		{
			lblReserved.setText("12. «·‰« Ã «·÷—Ì»Ì «·«› —«÷Ì");
		}
		lblReserved.setBounds(0, 387, 130, 21);
		panel.add(lblReserved);
		lblReserved.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblWorkIn = new JLabel("11. Default Tax Input");
		if(language.equals("Arabic"))
		{
			lblWorkIn.setText("11. ≈œŒ«· «·÷—Ì»… «·«› —«÷Ì…");
		}
		lblWorkIn.setBounds(0, 353, 130, 21);
		panel.add(lblWorkIn);
		lblWorkIn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		textField_9 = new JTextField();
		textField_9.setBounds(200, 319, 371, 21);
		panel.add(textField_9);
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		
		JButton button_9 = new JButton("");
		button_9.setBounds(570, 319, 36, 21);
		panel.add(button_9);
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"acc_group_10",0, version,"", "",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_9.setIcon(new ImageIcon(search));
		
		JButton button_8 = new JButton("");
		button_8.setBounds(570, 285, 36, 21);
		panel.add(button_8);
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"acc_group_9",0, version,"", "",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_8.setIcon(new ImageIcon(search));
		
		textField_8 = new JTextField();
		textField_8.setBounds(200, 285, 371, 21);
		panel.add(textField_8);
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		
		JLabel lblCostCorrection = new JLabel("10. Cost Correction");
		if(language.equals("Arabic"))
		{
			lblCostCorrection.setText("10.  ’ÕÌÕ «· ﬂ·›…");
		}
		lblCostCorrection.setBounds(0, 319, 130, 21);
		panel.add(lblCostCorrection);
		lblCostCorrection.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblDefaultPayable = new JLabel("9. Vendor Payable");
		if(language.equals("Arabic"))
		{
			lblDefaultPayable.setText("9. „” Õﬁ«  «·»«∆⁄");
		}
		lblDefaultPayable.setBounds(0, 285, 130, 21);
		panel.add(lblDefaultPayable);
		lblDefaultPayable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		textField_7 = new JTextField();
		textField_7.setBounds(200, 251, 371, 21);
		panel.add(textField_7);
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		
		JButton button_7 = new JButton("");
		button_7.setBounds(570, 251, 36, 21);
		panel.add(button_7);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"acc_group_8",0, version,"", "",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_7.setIcon(new ImageIcon(search));
		
		JButton button_6 = new JButton("");
		button_6.setBounds(570, 217, 36, 21);
		panel.add(button_6);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"acc_group_7",0, version,"", "",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_6.setIcon(new ImageIcon(search));
		
		textField_6 = new JTextField();
		textField_6.setBounds(200, 217, 371, 21);
		panel.add(textField_6);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		
		JLabel lblInventoryTransfer = new JLabel("8. Inventory Transfer");
		if(language.equals("Arabic"))
		{
			lblInventoryTransfer.setText("8. ‰ﬁ· «·„Œ“Ê‰");
		}
		lblInventoryTransfer.setBounds(0, 251, 130, 21);
		panel.add(lblInventoryTransfer);
		lblInventoryTransfer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblDefaultSale_1 = new JLabel("7. Default Sale Return");
		if(language.equals("Arabic"))
		{
			lblDefaultSale_1.setText("7. ≈—Ã«⁄ «·»Ì⁄ «·«› —«÷Ì");
		}
		lblDefaultSale_1.setBounds(0, 217, 130, 21);
		panel.add(lblDefaultSale_1);
		lblDefaultSale_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblDefaultSale = new JLabel("6. Default Sale Account");
		if(language.equals("Arabic"))
		{
			lblDefaultSale.setText("6. Õ”«» «·»Ì⁄ «·«› —«÷Ì");
		}
		lblDefaultSale.setBounds(0, 183, 130, 21);
		panel.add(lblDefaultSale);
		lblDefaultSale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblInventoryAdjustment = new JLabel("5. Inventory Adjustment");
		if(language.equals("Arabic"))
		{
			lblInventoryAdjustment.setText("5.  ⁄œÌ· «·„Œ“Ê‰");
		}
		lblInventoryAdjustment.setBounds(0, 149, 143, 21);
		panel.add(lblInventoryAdjustment);
		lblInventoryAdjustment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblCostOf = new JLabel("4. Cost of Sales Account");
		if(language.equals("Arabic"))
		{
			lblCostOf.setText("4. Õ”«»  ﬂ·›… «·„»Ì⁄« ");
		}
		lblCostOf.setBounds(0, 115, 143, 21);
		panel.add(lblCostOf);
		lblCostOf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblInventoryCash = new JLabel("2. Inventory Cash PO");
		if(language.equals("Arabic"))
		{
			lblInventoryCash.setText("2. «·„Œ“Ê‰ «·‰ﬁœÌ √„— «·‘—«¡");
		}
		lblInventoryCash.setBounds(0, 47, 150, 21);
		panel.add(lblInventoryCash);
		lblInventoryCash.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblWebsiteAddress = new JLabel("1. Inventory Account");
		if(language.equals("Arabic"))
		{
			lblWebsiteAddress.setText("1. Õ”«» «·„Œ“Ê‰");
		}
		lblWebsiteAddress.setBounds(0, 13, 130, 21);
		panel.add(lblWebsiteAddress);
		lblWebsiteAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtacc_code = new JTextField();
		txtacc_code.setBounds(200, 13, 371, 21);
		panel.add(txtacc_code);
		txtacc_code.setEditable(false);
		txtacc_code.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(200, 47, 371, 21);
		panel.add(textField_1);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(200, 81, 371, 21);
		panel.add(textField_2);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(200, 115, 371, 21);
		panel.add(textField_3);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(200, 149, 371, 21);
		panel.add(textField_4);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(200, 183, 371, 21);
		panel.add(textField_5);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		
		JButton button_5 = new JButton("");
		button_5.setBounds(570, 183, 36, 21);
		panel.add(button_5);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"acc_group_6",0, version,"", "",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_5.setIcon(new ImageIcon(search));
		
		JButton button_4 = new JButton("");
		button_4.setBounds(570, 149, 36, 21);
		panel.add(button_4);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"acc_group_5",0, version, "", "",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_4.setIcon(new ImageIcon(search));
		
		JButton button_3 = new JButton("");
		button_3.setBounds(570, 115, 36, 21);
		panel.add(button_3);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"acc_group_4",0, version,"", "",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_3.setIcon(new ImageIcon(search));
		
		JButton button_1 = new JButton("");
		button_1.setBounds(570, 81, 36, 21);
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"acc_group_3",0, version, "", "",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setIcon(new ImageIcon(search));
		
		JButton button_2 = new JButton("");
		button_2.setBounds(570, 47, 36, 21);
		panel.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"acc_group_2",0, version,"", "",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_2.setIcon(new ImageIcon(search));
		
		JButton button = new JButton("");
		button.setBounds(570, 13, 36, 21);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"acc_group_1",0, version,"", "",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(search));
		
		JLabel lblNewLabel = new JLabel("3. Inventory Cash PR");
		if(language.equals("Arabic"))
		{
			lblNewLabel.setText("3. ≈—Ã«⁄ „‘ —Ì«  «·„Œ“Ê‰ «·‰ﬁœÌ…");
		}
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(0, 81, 170, 21);
		panel.add(lblNewLabel);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setBounds(200, 387, 371, 21);
		panel.add(textField_11);
		textField_11.setColumns(10);
		
		JButton button_11 = new JButton("");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"acc_group_12",0, version,"", "",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_11.setBounds(570, 387, 36, 21);
		button_11.setIcon(new ImageIcon(search));
		panel.add(button_11);
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	public static void get_group_disp(String company, String sel_val) {
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_assign_account_group where group_code='"+sel_val+"' ");
			if(resultSet.next())
			{
				textField.setText(resultSet.getString("group_code"));
				txtDubai.setText(resultSet.getString("group_name"));
				txtacc_code.setText(resultSet.getString("inv_acc"));
				textField_1.setText(resultSet.getString("inv_cash_po"));
				textField_2.setText(resultSet.getString("inv_cash_pr"));
				textField_3.setText(resultSet.getString("cost_sale_acc"));
				textField_4.setText(resultSet.getString("inv_adj"));
				textField_5.setText(resultSet.getString("sale_acc"));
				textField_6.setText(resultSet.getString("sale_retn"));
				textField_7.setText(resultSet.getString("inv_transfer"));
				textField_8.setText(resultSet.getString("def_payable"));
				textField_9.setText(resultSet.getString("cost_correction"));
				textField_10.setText(resultSet.getString("tax_input"));
				textField_11.setText(resultSet.getString("tax_output"));
				if(resultSet.getString("status").equals("1"))
				{
					chckbxActiveForTransaction.setSelected(true);
				}
				
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
