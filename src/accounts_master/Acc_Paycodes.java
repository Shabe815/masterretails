package accounts_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;

import org.json.JSONException;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Acc_Paycodes {

	private JFrame frame;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField txtBank;
	static JTextField textField_3;
	static JTextField textField_4;
	private static JTextField textField_5;
	private static JTextField textField_6;
	private static JComboBox<String> comboBox;
	private static JTextArea textArea;
	private static JComboBox<String> comboBox_1;
	private static JComboBox<String> comboBox_2;
	private static JCheckBox chckbxUseThisPaycode;
	private static JCheckBox chckbxActiveForTransactions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acc_Paycodes window = new Acc_Paycodes("mr_pro","10.0","S01","");
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
	public Acc_Paycodes(String company,String version,String store,String language) {
		initialize(company,version, store,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 */
	private void initialize(final String company,final String version,final String store, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 685, 607);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 667, 562);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRegisterControl = new JLabel("Accounting Paycodes");
		if(language.equals("Arabic"))
		{
			lblRegisterControl.setText("—„Ê“ «·œ›⁄ «·„Õ«”»Ì…");
		}
		lblRegisterControl.setIcon(new ImageIcon("C:\\Users\\abdullasuhail\\Desktop\\caferpro icons\\Bank-Check-icon.png"));
		lblRegisterControl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRegisterControl.setBounds(10, 11, 228, 44);
		panel.add(lblRegisterControl);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 77, 734, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Reciepts/Payments :");
		if(language.equals("Arabic"))
		{
			lblNewLabel.setText("«·≈Ì’«·« /«·„œ›Ê⁄« :");
		}
		lblNewLabel.setBounds(20, 90, 128, 14);
		panel.add(lblNewLabel);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("BOTH");
		comboBox.addItem("PAYMENT");
		comboBox.addItem("RECEIPT");
		comboBox.setBounds(159, 87, 128, 20);
		panel.add(comboBox);
		
		JLabel lblBranch = new JLabel("Branch :");
		if(language.equals("Arabic"))
		{
			lblBranch.setText("›—⁄ :");
		}
		lblBranch.setBounds(20, 128, 128, 14);
		panel.add(lblBranch);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(159, 125, 321, 20);
		panel.add(comboBox_1);
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_acc_segment where segment='Branch' ");
			while(resultSet.next())
			{
				 String name=resultSet.getString("name");
				 comboBox_1.addItem(name);
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		JLabel lblPaycode = new JLabel("Paycode :");
		if(language.equals("Arabic"))
		{
			lblPaycode.setText("—„“ «·œ›⁄ :");
		}
		lblPaycode.setBounds(20, 165, 128, 14);
		panel.add(lblPaycode);
		
		textField = new JTextField();
		textField.setBounds(159, 162, 245, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		Image search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Paycde_Chooser(company, version, language);
			}
		});
		btnNewButton.setIcon(new ImageIcon(search));
		btnNewButton.setBounds(416, 161, 47, 23);
		panel.add(btnNewButton);
		
		JLabel lblPaycodeType = new JLabel("Paycode Type :");
		if(language.equals("Arabic"))
		{
			lblPaycodeType.setText("‰Ê⁄ —„“ «·œ›⁄ :");
		}
		lblPaycodeType.setBounds(20, 207, 128, 14);
		panel.add(lblPaycodeType);
		
		comboBox_2 = new JComboBox<String>();
		
		comboBox_2.setBounds(159, 204, 128, 20);
		comboBox_2.addItem("BANK");
		comboBox_2.addItem("CASH");
		comboBox_2.addItem("CARD");
		comboBox_2.addItem("DEMAND DRAFT");
		comboBox_2.addItem("ONLINE");
		comboBox_2.addItem("OTHER");
		panel.add(comboBox_2);
		comboBox_2.setSelectedItem("CASH");
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent E) {
				if(E.getStateChange()==ItemEvent.SELECTED)
				{
					if(comboBox_2.getSelectedItem().toString().equals("BANK"))
					{
						textArea.setEditable(true);
						textField_5.setEditable(true);
						textField_6.setEditable(true);
					}
					else
					{
						textArea.setEditable(false);
						textField_5.setEditable(false);
						textField_6.setEditable(false);
						textArea.setText("");
						textField_5.setText("");
						textField_6.setText("");
					}
				}
					
			}
		});
		
		JLabel lblPayName = new JLabel("Pay Name :");
		if(language.equals("Arabic"))
		{
			lblPayName.setText("«”„ «·œ›⁄ :");
		}
		lblPayName.setBounds(20, 249, 128, 14);
		panel.add(lblPayName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(159, 246, 321, 20);
		panel.add(textField_1);
		
		JLabel lblVoucherFormat = new JLabel("Voucher Format  :");
		if(language.equals("Arabic"))
		{
			lblVoucherFormat.setText(" ‰”Ìﬁ «·ﬁ”Ì„… :");
		}
		lblVoucherFormat.setBounds(20, 288, 128, 14);
		panel.add(lblVoucherFormat);
		
		txtBank = new JTextField();
		txtBank.setText("BANK");
		txtBank.setColumns(10);
		txtBank.setBounds(159, 285, 321, 20);
		panel.add(txtBank);
		
		JButton button_3 = new JButton("");
		button_3.setBounds(505, 284, 47, 23);
		panel.add(button_3);
		
		chckbxUseThisPaycode = new JCheckBox("Use this Paycode from other brances Payments & Reciepts");
		if(language.equals("Arabic"))
		{
			chckbxUseThisPaycode.setText("«” Œœ„ —„“ «·œ›⁄ Â–« „‰ ›—Ê⁄ √Œ—Ï ··„œ›Ê⁄«  Ê«·≈Ì’«·« ");
		}
		chckbxUseThisPaycode.setBackground(new Color(243, 229, 216));
		chckbxUseThisPaycode.setBounds(159, 324, 364, 23);
		panel.add(chckbxUseThisPaycode);
		
		chckbxActiveForTransactions = new JCheckBox("Active for Transactions");
		if(language.equals("Arabic"))
		{
			chckbxActiveForTransactions.setText("‰‘ÿ ··„⁄«„·« ");
		}
		chckbxActiveForTransactions.setBackground(new Color(243, 229, 216));
		chckbxActiveForTransactions.setBounds(402, 86, 228, 23);
		panel.add(chckbxActiveForTransactions);
		
		JLabel lblDefaultAccount = new JLabel("Default Account  :");
		if(language.equals("Arabic"))
		{
			lblDefaultAccount.setText("«·Õ”«» «·«› —«÷Ì :");
		}
		lblDefaultAccount.setBounds(20, 356, 128, 14);
		panel.add(lblDefaultAccount);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(159, 353, 245, 20);
		panel.add(textField_3);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company, "acc_paycode1", 0, version,"","",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_4.setIcon(new ImageIcon(search));
		button_4.setBounds(416, 352, 47, 23);
		panel.add(button_4);
		
		JLabel lblClearingAccount = new JLabel("Clearing Account  :");
		if(language.equals("Arabic"))
		{
			lblClearingAccount.setText("Õ”«» «·„ﬁ«’… :");
		}
		lblClearingAccount.setBounds(20, 389, 128, 14);
		panel.add(lblClearingAccount);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(159, 386, 245, 20);
		panel.add(textField_4);
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company, "acc_paycode2", 0, version,"","",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_5.setIcon(new ImageIcon(search));
		button_5.setBounds(416, 385, 47, 23);
		panel.add(button_5);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 427, 734, 2);
		panel.add(separator_1);
		
		JLabel lblBankDetails = new JLabel("Bank Details");
		if(language.equals("Arabic"))
		{
			lblBankDetails.setText(" ›«’Ì· «·»‰ﬂ");
		}
		lblBankDetails.setBounds(21, 432, 88, 14);
		panel.add(lblBankDetails);
		
		JLabel lblAddress = new JLabel("Address :");
		if(language.equals("Arabic"))
		{
			lblAddress.setText("⁄‰Ê«‰ :");
		}
		lblAddress.setBounds(20, 457, 128, 14);
		panel.add(lblAddress);
		
		JLabel lblCity = new JLabel("City :");
		if(language.equals("Arabic"))
		{
			lblCity.setText("„œÌ‰… :");
		}
		lblCity.setBounds(20, 504, 128, 14);
		panel.add(lblCity);
		
		JLabel lblPhone = new JLabel("Phone :");
		if(language.equals("Arabic"))
		{
			lblPhone.setText("Â« › :");
		}
		lblPhone.setBounds(20, 529, 128, 14);
		panel.add(lblPhone);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(159, 452, 245, 44);
		panel.add(textArea);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(159, 501, 245, 20);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(159, 526, 245, 20);
		panel.add(textField_6);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Exit");
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBounds(443, 7, 60, 48);
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		if(language.equals("Arabic"))
		{
			menuItem_1.setText("Ì‰⁄‘");
		}
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBounds(503, 7, 80, 48);
		panel.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		if(language.equals("Arabic"))
		{
			menuItem_2.setText("ÌÕ›Ÿ");
		}
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().equals(""))
				{
					
					if(language.equals("Arabic"))
					{
						JOptionPane.showMessageDialog(frame, "√œŒ· √Ê Õœœ —„“ «·œ›⁄");
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Enter or Select Paycode");
					}
				}
				else if(textField_1.getText().equals(""))
				{
					if(language.equals("Arabic"))
					{
						JOptionPane.showMessageDialog(frame, "√œŒ· «”„ —„“ «·œ›⁄");
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Enter Paycode Name ");
					}
					
				}
				else if(txtBank.getText().equals(""))
				{
					if(language.equals("Arabic"))
					{
						JOptionPane.showMessageDialog(frame, "√œŒ· √Ê «Œ —  ‰”Ìﬁ «·ﬁ”Ì„…");
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Enter or Choose Voucher Format");
					}
					
				}
				else
				{
					int sts=0;
					if(chckbxActiveForTransactions.isSelected())
					{
						sts=1;
					}
					int pay_sts=0;
					if(chckbxUseThisPaycode.isSelected())
					{
						pay_sts=1;
					}
					try {
						LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);  
						
					    Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						String query="select * from "+company+".mr_acc_paycode where paycode='"+textField.getText()+"' and type='"+comboBox.getSelectedItem().toString()+"' and branch='"+comboBox_1.getSelectedItem().toString()+"' ";
						ResultSet resultSet = statement.executeQuery(query);
						if(resultSet.next())
						{
							String query1="update "+company+".mr_acc_paycode set  paycode_name='"+textField_1.getText()+"',paycode_type='"+comboBox_2.getSelectedItem().toString()+"',vocher_format='"+txtBank.getText()+"',default_ac='"+textField_3.getText()+"',clearing_ac='"+textField_4.getText()+"',Address='"+textArea.getText()+"',city='"+textField_5.getText()+"',phone='"+textField_6.getText()+"',status='"+sts+"',paycode_flag='"+pay_sts+"',modified_date='"+formattedDate+"' where paycode='"+textField.getText()+"' and type='"+comboBox.getSelectedItem().toString()+"' and branch='"+comboBox_1.getSelectedItem().toString()+"' ";
							statement.executeUpdate(query1);
							
							if(language.equals("Arabic"))
							{
								JOptionPane.showMessageDialog(frame, " „ «·Õ›Ÿ »‰Ã«Õ");
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Saved Successfull");
							}
						}
						else
						{
							String query1="INSERT INTO "+company+".mr_acc_paycode( `type`, `branch`, `store_id`, `paycode`, `paycode_name`, `paycode_type`, `vocher_format`, `default_ac`, `clearing_ac`, `Address`, `city`, `phone`, `status`, `paycode_flag`, `created_date`, `modified_date`) VALUES ('"+comboBox.getSelectedItem().toString()+"','"+comboBox_1.getSelectedItem().toString()+"','"+store+"','"+textField.getText()+"','"+textField_1.getText()+"','"+comboBox_2.getSelectedItem().toString()+"','"+txtBank.getText()+"','"+textField_3.getText()+"','"+textField_4.getText()+"','"+textArea.getText()+"','"+textField_5.getText()+"','"+textField_6.getText()+"','"+sts+"','"+pay_sts+"','"+formattedDate+"','"+formattedDate+"')";
							statement.executeUpdate(query1);
							if(language.equals("Arabic"))
							{
								JOptionPane.showMessageDialog(frame, " „ «·Õ›Ÿ »‰Ã«Õ");
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Saved Successfull");
							}
						}
						con.close();
					} catch (Exception e) {
						System.err.println(e);
					}
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBounds(585, 7, 70, 48);
		panel.add(menuItem_2);
	}
	
	public static void get_paycode_dt(String company,String sel_code)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_acc_paycode where paycode='"+sel_code+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				comboBox.setSelectedItem(resultSet.getString("type"));
				comboBox_1.setSelectedItem(resultSet.getString("branch"));
				textField.setText(resultSet.getString("paycode"));
				textField_1.setText(resultSet.getString("paycode_name"));
				comboBox_2.setSelectedItem(resultSet.getString("paycode_type"));
				txtBank.setText(resultSet.getString("vocher_format"));
				textField_3.setText(resultSet.getString("default_ac"));
				textField_4.setText(resultSet.getString("clearing_ac"));
				textArea.setText(resultSet.getString("Address"));
				textField_5.setText(resultSet.getString("city"));
				textField_6.setText(resultSet.getString("phone"));
				if(resultSet.getString("status").equals("1"))
				{
					chckbxActiveForTransactions.setSelected(true);
				}
				else
				{
					chckbxActiveForTransactions.setSelected(false);
				}
				if(resultSet.getString("paycode_flag").equals("1"))
				{
					chckbxUseThisPaycode.setSelected(true);
				}
				else
				{
					chckbxUseThisPaycode.setSelected(false);
				}
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
