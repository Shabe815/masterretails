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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import org.json.JSONException;

public class Txn_Currnc {

	private JFrame frame;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;
	private static JCheckBox chckbxActiveForTransactions;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Txn_Currnc window = new Txn_Currnc("mr_pro","1.0","");
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
	public Txn_Currnc(String company,String version,String language) {
		initialize(company,version,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 */
	private void initialize(final String company,final String version, final String language) {
		frame = new JFrame();
		frame.setBounds(0, 0, 650, 400);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 634, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 75, 634, 2);
		panel.add(separator);
		
		Image cntrl = new ImageIcon(this.getClass().getResource("/control-panel.png")).getImage();
		
		JLabel lblRegisterControl = new JLabel("Transaction Currencies");
		if(language.equals("Arabic"))
		{
			lblRegisterControl.setText("⁄„·«  «·„⁄«„·« ");
		}
		lblRegisterControl.setIcon(new ImageIcon(cntrl));
		lblRegisterControl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRegisterControl.setBounds(10, 11, 228, 44);
		panel.add(lblRegisterControl);
		
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
		menuItem.setBounds(410, 11, 60, 48);
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		if(language.equals("Arabic"))
		{
			menuItem_1.setText("Ì‰⁄‘");
		}
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBounds(470, 11, 80, 48);
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
					JOptionPane.showMessageDialog(frame, "Enter or Choose Currency Code √œŒ· √Ê «Œ — —„“ «·⁄„·…");
				}
				else if(textField_1.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Enter Currency Description √œŒ· Ê’› «·⁄„·…");
				}
				else if(textField_2.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Enter Currency Buying Rate √œŒ· ”⁄— ‘—«¡ «·⁄„·…");
				}
				else if(textField_3.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Enter Currency Selling Rate √œŒ· ”⁄— »Ì⁄ «·⁄„·…");
				}
				else
				{
					int sts=0;
					if(chckbxActiveForTransactions.isSelected())
					{
						sts=1;
					}
					else
					{
						sts=0;
					}
					try {
						LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);  
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						String query="select * from "+company+".mr_currency where code='"+textField.getText()+"' ";
						ResultSet resultSet = statement.executeQuery(query);
						if(resultSet.next())
						{
							String query1="update "+company+".mr_currency set  buy_rate='"+textField_2.getText()+"',sell_rate='"+textField_3.getText()+"',status='"+sts+"',modified_date='"+formattedDate+"' where code='"+textField.getText()+"'  ";
							statement.executeUpdate(query1);
							JOptionPane.showMessageDialog(frame, "Saved Successfull");
						}
						else
						{
							String query1="INSERT INTO "+company+".mr_currency(`code`, `name`, `buy_rate`, `sell_rate`, `status`, `created`, `modified_date`) VALUES ('"+textField.getText()+"','"+textField_1.getText()+"','"+textField_2.getText()+"','"+textField_3.getText()+"','"+sts+"','"+formattedDate+"','"+formattedDate+"')";
							statement.executeUpdate(query1);
							JOptionPane.showMessageDialog(frame, "Saved Successfull  „ «·Õ›Ÿ »‰Ã«Õ");
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
		menuItem_2.setBounds(552, 11, 70, 48);
		panel.add(menuItem_2);
		
		JLabel lblCurrencyCode = new JLabel("Currency code :");
		if(language.equals("Arabic"))
		{
			lblCurrencyCode.setText("—„“ «·⁄„·… :");
		}
		lblCurrencyCode.setBounds(47, 88, 102, 14);
		panel.add(lblCurrencyCode);
		
		textField = new JTextField();
		textField.setBounds(176, 85, 128, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		Image search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Currency_choose(company, version,language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(search));
		btnNewButton.setBounds(330, 84, 48, 23);
		panel.add(btnNewButton);
		
		JLabel lblDescription = new JLabel("Description :");
		if(language.equals("Arabic"))
		{
			lblDescription.setText("Ê’› :");
		}
		lblDescription.setBounds(47, 127, 102, 14);
		panel.add(lblDescription);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(176, 124, 202, 20);
		panel.add(textField_1);
		
		JLabel lblBuyingRate = new JLabel("Buying Rate :");
		if(language.equals("Arabic"))
		{
			lblBuyingRate.setText("”⁄— «·‘—«¡ :");
		}
		lblBuyingRate.setBounds(47, 170, 102, 14);
		panel.add(lblBuyingRate);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(176, 167, 202, 20);
		panel.add(textField_2);
		
		JLabel lblSellingRate = new JLabel("Selling Rate :");
		if(language.equals("Arabic"))
		{
			lblSellingRate.setText("„⁄œ· «·»Ì⁄ :");
		}
		lblSellingRate.setBounds(47, 210, 102, 14);
		panel.add(lblSellingRate);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(176, 207, 202, 20);
		panel.add(textField_3);
		
		chckbxActiveForTransactions = new JCheckBox("Active for Transactions");
		if(language.equals("Arabic"))
		{
			chckbxActiveForTransactions.setText("‰‘ÿ ··„⁄«„·« ");
		}
		chckbxActiveForTransactions.setBackground(new Color(243, 229, 216));
		chckbxActiveForTransactions.setBounds(176, 252, 254, 23);
		panel.add(chckbxActiveForTransactions);
		
		JLabel lblLastUdatedOn = new JLabel("Last udated On :");
		if(language.equals("Arabic"))
		{
			lblLastUdatedOn.setText("¬Œ—  ÕœÌÀ ›Ì :");
		}
		lblLastUdatedOn.setBounds(47, 290, 102, 14);
		panel.add(lblLastUdatedOn);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(176, 287, 202, 20);
		panel.add(textField_4);
		
	}
	public static void get_currency_dt(String company,String sel_code)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_currency where code='"+sel_code+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				textField.setText(resultSet.getString("code"));
				textField_1.setText(resultSet.getString("name"));
				textField_2.setText(resultSet.getString("buy_rate"));
				textField_3.setText(resultSet.getString("sell_rate"));
				textField_4.setText(resultSet.getString("modified_date"));
				if(resultSet.getString("status").equals("1"))
				{
					chckbxActiveForTransactions.setSelected(true);
				}
				else
				{
					chckbxActiveForTransactions.setSelected(false);
				}
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
