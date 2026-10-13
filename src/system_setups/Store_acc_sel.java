package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.json.JSONException;

import accounts_master.SelectAccount;

import javax.swing.JPanel;

public class Store_acc_sel {

	private static JFrame frame;
	public static JTextField txtacc_code;
	public static JTextField textField_1;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_4;
	public static JTextField textField_5;
	public static JTextField textField_6;
	private JTextField textField;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField txtNa;
	private JTextField textField_13;
	private JTextField txtNa_1;
	private JTextField textField_15;
	private JTextField textField_16;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Store_acc_sel window = new Store_acc_sel("mr_pro","1.0","S02","");
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
	public Store_acc_sel(String company, String version,String store_id, String language) {
		initialize(company,version,store_id,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 */
	private void initialize(final String company,final String version,final String store_id, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBounds(369, 0, 60, 48);
		frame.getContentPane().add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBounds(429, 0, 80, 48);
		frame.getContentPane().add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    String formattedDate = myDateObj.format(myFormatObj);  
			    Connection con=Dbsetup.dbconn.getConnection();
				Statement statement;
				try {
					statement = con.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store_accounts where ref_store_id='"+store_id+"' ");
					if(resultSet.next())
					{
						String query="delete from "+company+".mr_store_accounts where ref_store_id ='"+store_id+"'";
						statement.executeUpdate(query);
						String query1="INSERT INTO "+company+".mr_store_accounts(`ref_store_id`, `cash_acc`, `ar_acc`, `gift_voucher_acc`, `loyalty_acc`, `store_credit_acc`, `cash_adv_acc`, `store_sale_acc`, `created_date`, `modified_date`, `gift_voucher_max`, `gift_voucher_min`, `store_credit_max`, `store_credit_min`, `sales_refund_max`, `sales_refund_min`, `cash_shortage_max`, `cash_excess_max`, `store_credit_validity`) values('"+store_id+"','"+txtacc_code.getText()+"','"+textField_1.getText()+"','"+textField_2.getText()+"','"+textField_3.getText()+"','"+textField_4.getText()+"','"+textField_5.getText()+"','"+textField_6.getText()+"','"+formattedDate+"','"+formattedDate+"','"+textField.getText()+"','"+textField_7.getText()+"','"+textField_9.getText()+"','"+textField_8.getText()+"','"+textField_11.getText()+"','"+textField_10.getText()+"','"+textField_13.getText()+"','"+textField_15.getText()+"','"+textField_16.getText()+"')";
						statement.executeUpdate(query1);
						JOptionPane.showMessageDialog(frame, "Saved Successfully");
					}
					else
					{
						String query1="INSERT INTO "+company+".mr_store_accounts (`ref_store_id`, `cash_acc`, `ar_acc`, `gift_voucher_acc`, `loyalty_acc`, `store_credit_acc`, `cash_adv_acc`, `store_sale_acc`, `created_date`, `modified_date`, `gift_voucher_max`, `gift_voucher_min`, `store_credit_max`, `store_credit_min`, `sales_refund_max`, `sales_refund_min`, `cash_shortage_max`, `cash_excess_max`, `store_credit_validity`) values('"+store_id+"','"+txtacc_code.getText()+"','"+textField_1.getText()+"','"+textField_2.getText()+"','"+textField_3.getText()+"','"+textField_4.getText()+"','"+textField_5.getText()+"','"+textField_6.getText()+"','"+formattedDate+"','"+formattedDate+"','"+textField.getText()+"','"+textField_7.getText()+"','"+textField_9.getText()+"','"+textField_8.getText()+"','"+textField_11.getText()+"','"+textField_10.getText()+"','"+textField_13.getText()+"','"+textField_15.getText()+"','"+textField_16.getText()+"')";
						statement.executeUpdate(query1);
						JOptionPane.showMessageDialog(frame, "Saved Successfully");
					}
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBounds(511, 0, 70, 48);
		frame.getContentPane().add(menuItem_2);
		
		Image reports = new ImageIcon(this.getClass().getResource("/reports.png")).getImage();
		JLabel lblCompanySetup = new JLabel("Store Accounts List");
		lblCompanySetup.setIcon(new ImageIcon(reports));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCompanySetup.setBounds(21, 11, 228, 44);
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		Image search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 68, 571, 218);
		frame.getContentPane().add(panel);
		panel.setBackground(new Color(243, 229, 216));
		panel.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(167, 57, 371, 21);
		panel.add(textField_1);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		JLabel lblInventoryCash = new JLabel("2. A/R Account");
		lblInventoryCash.setBounds(12, 55, 150, 21);
		panel.add(lblInventoryCash);
		lblInventoryCash.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblWebsiteAddress = new JLabel("1. Cash Account");
		lblWebsiteAddress.setBounds(12, 28, 130, 21);
		panel.add(lblWebsiteAddress);
		lblWebsiteAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtacc_code = new JTextField();
		txtacc_code.setBounds(167, 31, 371, 21);
		panel.add(txtacc_code);
		txtacc_code.setEditable(false);
		txtacc_code.setColumns(10);
		
		JButton button = new JButton("");
		button.setBounds(539, 31, 36, 21);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"store_acc_1",0, version,"", "", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(search));
		
		JButton button_2 = new JButton("");
		button_2.setBounds(539, 57, 36, 21);
		panel.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"store_acc_2",0, version,"", "", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_2.setIcon(new ImageIcon(search));
		
		JButton button_1 = new JButton("");
		button_1.setBounds(539, 83, 36, 21);
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"store_acc_3",0, version,"", "", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setIcon(new ImageIcon(search));
		
		textField_2 = new JTextField();
		textField_2.setBounds(167, 83, 371, 21);
		panel.add(textField_2);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		JLabel lblCostOf = new JLabel("3. Gift Voucher Account");
		lblCostOf.setBounds(12, 82, 143, 21);
		panel.add(lblCostOf);
		lblCostOf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblInventoryAdjustment = new JLabel("4. Loyality Code Account");
		lblInventoryAdjustment.setBounds(12, 109, 143, 21);
		panel.add(lblInventoryAdjustment);
		lblInventoryAdjustment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		textField_3 = new JTextField();
		textField_3.setBounds(167, 109, 371, 21);
		panel.add(textField_3);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		
		JButton button_3 = new JButton("");
		button_3.setBounds(539, 109, 36, 21);
		panel.add(button_3);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"store_acc_4",0, version,"", "", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_3.setIcon(new ImageIcon(search));
		
		JButton button_4 = new JButton("");
		button_4.setBounds(539, 135, 36, 21);
		panel.add(button_4);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"store_acc_5",0, version,"", "", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_4.setIcon(new ImageIcon(search));
		
		textField_4 = new JTextField();
		textField_4.setBounds(167, 135, 371, 21);
		panel.add(textField_4);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		
		JLabel lblDefaultSale = new JLabel("5. Store Credit Account");
		lblDefaultSale.setBounds(12, 136, 150, 21);
		panel.add(lblDefaultSale);
		lblDefaultSale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblDefaultSale_1 = new JLabel("6. Cash Advance Account");
		lblDefaultSale_1.setBounds(12, 163, 150, 21);
		panel.add(lblDefaultSale_1);
		lblDefaultSale_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		textField_5 = new JTextField();
		textField_5.setBounds(167, 161, 371, 21);
		panel.add(textField_5);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		
		JButton button_5 = new JButton("");
		button_5.setBounds(539, 161, 36, 21);
		panel.add(button_5);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"store_acc_6",0, version,"", "", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_5.setIcon(new ImageIcon(search));
		
		JButton button_6 = new JButton("");
		button_6.setBounds(539, 187, 36, 21);
		panel.add(button_6);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company,"store_acc_7",0, version,"", "", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_6.setIcon(new ImageIcon(search));
		
		textField_6 = new JTextField();
		textField_6.setBounds(167, 187, 371, 21);
		panel.add(textField_6);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		
		JLabel lblInventoryTransfer = new JLabel("7. Store Sales Account");
		lblInventoryTransfer.setBounds(12, 190, 130, 21);
		panel.add(lblInventoryTransfer);
		lblInventoryTransfer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblStoreCode = new JLabel("Store Code : ");
		lblStoreCode.setBounds(183, 5, 95, 21);
		panel.add(lblStoreCode);
		lblStoreCode.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblS = new JLabel(store_id);
		lblS.setBounds(278, 5, 86, 21);
		panel.add(lblS);
		lblS.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 299, 569, 195);
		frame.getContentPane().add(panel_1);
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setLayout(null);
		
		JLabel lblMaxAmount = new JLabel("Max Amount");
		lblMaxAmount.setBounds(246, 5, 85, 22);
		panel_1.add(lblMaxAmount);
		
		JLabel lblMinAmount = new JLabel("Min Amount");
		lblMinAmount.setBounds(409, 5, 85, 22);
		panel_1.add(lblMinAmount);
		
		JLabel lblGiftVoucher = new JLabel("Gift Voucher");
		lblGiftVoucher.setBounds(55, 32, 124, 22);
		panel_1.add(lblGiftVoucher);
		
		textField = new JTextField();
		textField.setBounds(230, 32, 116, 22);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(403, 32, 116, 22);
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(403, 59, 116, 22);
		panel_1.add(textField_8);
		
		JLabel lblStoreCredites = new JLabel("Store Credits");
		lblStoreCredites.setBounds(55, 59, 124, 22);
		panel_1.add(lblStoreCredites);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(230, 59, 116, 22);
		panel_1.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(403, 86, 116, 22);
		panel_1.add(textField_10);
		
		JLabel lblSalesRefund = new JLabel("Sales Refund");
		lblSalesRefund.setBounds(55, 86, 124, 22);
		panel_1.add(lblSalesRefund);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(230, 86, 116, 22);
		panel_1.add(textField_11);
		
		txtNa = new JTextField();
		txtNa.setText("N/A");
		txtNa.setEditable(false);
		txtNa.setColumns(10);
		txtNa.setBounds(403, 113, 116, 22);
		panel_1.add(txtNa);
		
		JLabel lblCashShortage = new JLabel("Cash Shortage");
		lblCashShortage.setBounds(55, 113, 124, 22);
		panel_1.add(lblCashShortage);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(230, 113, 116, 22);
		panel_1.add(textField_13);
		
		txtNa_1 = new JTextField();
		txtNa_1.setText("N/A");
		txtNa_1.setEditable(false);
		txtNa_1.setColumns(10);
		txtNa_1.setBounds(403, 140, 116, 22);
		panel_1.add(txtNa_1);
		
		JLabel lblCashExcess = new JLabel("Cash Excess");
		lblCashExcess.setBounds(55, 140, 124, 22);
		panel_1.add(lblCashExcess);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(230, 140, 116, 22);
		panel_1.add(textField_15);
		
		JLabel lblStoreCreditsValidity = new JLabel("Store Credits Validity");
		lblStoreCreditsValidity.setBounds(55, 167, 124, 22);
		panel_1.add(lblStoreCreditsValidity);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(230, 167, 56, 22);
		panel_1.add(textField_16);
		
		JLabel lblNewLabel_2 = new JLabel("Days from Issue Date");
		lblNewLabel_2.setBounds(313, 167, 224, 22);
		panel_1.add(lblNewLabel_2);
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 599, 537);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		get_acc_disp(company, store_id);
	}
	public static void get_acc_disp(String company, String store_id) {
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store_accounts where ref_store_id='"+store_id+"' ");
			if(resultSet.next())
			{
				txtacc_code.setText(resultSet.getString("cash_acc"));
				textField_1.setText(resultSet.getString("ar_acc"));
				textField_2.setText(resultSet.getString("gift_voucher_acc"));
				textField_3.setText(resultSet.getString("loyalty_acc"));
				textField_4.setText(resultSet.getString("store_credit_acc"));
				textField_5.setText(resultSet.getString("cash_adv_acc"));
				textField_6.setText(resultSet.getString("store_sale_acc"));
				
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
