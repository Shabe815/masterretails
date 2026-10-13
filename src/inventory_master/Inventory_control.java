package inventory_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;


import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import org.json.JSONException;

import accounts_master.Acc_group_list;
import system_setups.store_list;
import system_setups.Select_Tax;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Inventory_control {

	private static JFrame frame;
	public static JTextField textField;
	public static JTextField txtDubai;
	public static JTextField txtacc_code;
	public static JTextField textField_9;
	private static JTextArea textArea ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					Inventory_control window = new Inventory_control("mr_pro", null,"S01","");
					Inventory_control.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inventory_control(String company, String version, String store,String language) {
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
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBounds(530, 7, 80, 48);
		frame.getContentPane().add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String store_code=textField.getText();
				String store_name=txtDubai.getText();
				String acc_code=txtacc_code.getText();
				String tax_code=textField_9.getText();
				if(store_code.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter or Select Store code");
				}
				else if(store_name.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter Store Name");
				}
				else if(acc_code.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter or Select Account code");
				}
				else if(tax_code.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter or Select Tax code");
				}
				else
				{
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					try {
						statement = con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_inventory_config where store_code='"+store_code+"' ");
						if(resultSet.next())
						{
							String query="update "+company+".mr_inventory_config set store_name='"+store_name+"',account_code='"+acc_code+"',tax_code='"+tax_code+"' where store_code='"+store_code+"' ";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
						else
						{
							String query="insert into "+company+".mr_inventory_config (store_code,store_name,account_code,tax_code,created_date) values ('"+store_code+"','"+store_name+"','"+acc_code+"','"+tax_code+"','"+formattedDate+"')";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
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
		menuItem_2.setBounds(612, 7, 70, 48);
		frame.getContentPane().add(menuItem_2);
		
		
		
		
		JLabel lblCompanyName = new JLabel("Store Code :");
		lblCompanyName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCompanyName.setBounds(21, 79, 102, 21);
		frame.getContentPane().add(lblCompanyName);
		
		JLabel lblAddress = new JLabel("Store Name :");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(21, 122, 102, 21);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblAddress_1 = new JLabel("Address  :");
		lblAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress_1.setBounds(21, 169, 102, 21);
		frame.getContentPane().add(lblAddress_1);
		
		JLabel lblWebsiteAddress = new JLabel("Account Code :");
		lblWebsiteAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWebsiteAddress.setBounds(21, 326, 102, 21);
		frame.getContentPane().add(lblWebsiteAddress);
		
		JLabel lblTrn = new JLabel("Default TAX Code :");
		lblTrn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTrn.setBounds(21, 367, 129, 21);
		frame.getContentPane().add(lblTrn);
		
		Image reports = new ImageIcon(this.getClass().getResource("/reports.png")).getImage();
		JLabel lblCompanySetup = new JLabel("Inventory Accounts");
		lblCompanySetup.setIcon(new ImageIcon(reports));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCompanySetup.setBounds(21, 11, 228, 44);
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(175, 80, 165, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtDubai = new JTextField();
		txtDubai.setEditable(false);
		txtDubai.setBounds(175, 123, 247, 20);
		frame.getContentPane().add(txtDubai);
		txtDubai.setColumns(10);
		
		txtacc_code = new JTextField();
		txtacc_code.setEditable(false);
		txtacc_code.setColumns(10);
		txtacc_code.setBounds(175, 327, 221, 20);
		frame.getContentPane().add(txtacc_code);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBounds(175, 368, 221, 20);
		frame.getContentPane().add(textField_9);
		
		Image search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new store_list(company,"inventory_cntrl", version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(search));
		btnNewButton.setBounds(371, 79, 36, 23);
		frame.getContentPane().add(btnNewButton);
		
		textArea= new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(175, 168, 247, 113);
		frame.getContentPane().add(textArea);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Acc_group_list(company,"inventory_cntrl", version,"where status='1'", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(search));
		button.setBounds(406, 324, 36, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Select_Tax(company,"inventory_cntrl", version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setIcon(new ImageIcon(search));
		button_1.setBounds(406, 367, 36, 23);
		frame.getContentPane().add(button_1);
		
		JButton btnNew = new JButton("NEW");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new accounts_master.Assign_account(company, version,store, language);
			}
		});
		btnNew.setBounds(454, 324, 70, 25);
		frame.getContentPane().add(btnNew);
		
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	public static void get_invnrty_disp(String company, String sel_val) {
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_inventory_config where store_code='"+sel_val+"' ");
			if(resultSet.next())
			{
				textField.setText(resultSet.getString("store_code"));
				txtDubai.setText(resultSet.getString("store_name"));
				txtacc_code.setText(resultSet.getString("account_code"));
				textField_9.setText(resultSet.getString("tax_code"));
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	public static void get_store_addrs(String company, String sel_val) {
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store where store_id='"+sel_val+"' ");
			if(resultSet.next())
			{
				String addr1="";
				if(!resultSet.getString("addr1").equals(""))
				{
					addr1=resultSet.getString("addr1")+","+"\n";
				}
				String addr2="";
				if(!resultSet.getString("addr2").equals(""))
				{
					addr2=resultSet.getString("addr2")+","+"\n";
				}
				String addr3="";
				if(!resultSet.getString("addr3").equals(""))
				{
					addr3=resultSet.getString("addr3")+","+"\n";
				}
				String addr4="";
				if(!resultSet.getString("addr4").equals(""))
				{
					addr4=resultSet.getString("addr4")+","+"\n";
				}
				String city="";
				if(!resultSet.getString("city").equals(""))
				{
					city=resultSet.getString("city")+","+"\n";
				}
				String country="";
				if(!resultSet.getString("country").equals(""))
				{
					country=resultSet.getString("country");
				}
				textArea.setText(addr1+addr2+addr3+addr4+city+country);
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
