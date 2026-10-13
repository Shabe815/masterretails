package system_setups;

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

import org.json.JSONException;

import accounts_master.Acc_group_list;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Item_Tax {

	private static JFrame frame;
	private static JTextField textField;
	private static JTextField txtDubai;
	private static JTextField txttaxrate;
	public static JTextField textField_9;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					Item_Tax window = new Item_Tax("mr_pro", null,"S01","");
					Item_Tax.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Item_Tax(String company,String version,String store, String language) {
		initialize(company,version,store,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param store 
	 * @param language 
	 */
	private void initialize(final String company,final String version,final String store, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JLabel lblCompanyName = new JLabel("Tax Code :");
		lblCompanyName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCompanyName.setBounds(21, 79, 102, 21);
		frame.getContentPane().add(lblCompanyName);
		
		JLabel lblAddress = new JLabel("Description  :");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(21, 122, 102, 21);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblWebsiteAddress = new JLabel("Tax Rate :");
		lblWebsiteAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWebsiteAddress.setBounds(21, 173, 102, 21);
		frame.getContentPane().add(lblWebsiteAddress);
		
		JLabel lblTrn = new JLabel("Default A/C :");
		lblTrn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTrn.setBounds(21, 228, 129, 21);
		frame.getContentPane().add(lblTrn);
		
		Image percentage=new ImageIcon(this.getClass().getResource("/percentage.png")).getImage();
		JLabel lblCompanySetup = new JLabel("Item Tax");
		lblCompanySetup.setIcon(new ImageIcon(percentage));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCompanySetup.setBounds(21, 11, 228, 44);
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		textField = new JTextField();
		textField.setBounds(175, 80, 165, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtDubai = new JTextField();
		txtDubai.setBounds(175, 123, 247, 20);
		frame.getContentPane().add(txtDubai);
		txtDubai.setColumns(10);
		
		txttaxrate = new JTextField();
		txttaxrate.setColumns(10);
		txttaxrate.setBounds(175, 174, 221, 20);
		frame.getContentPane().add(txttaxrate);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBounds(175, 229, 221, 20);
		frame.getContentPane().add(textField_9);
		
		Image binoculars = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Select_Tax(company, "", version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(binoculars));
		btnNewButton.setBounds(371, 79, 70, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Acc_group_list(company,"item_tax", version,"", language);
					//new SelectAccount(company,"",0, version,"", "");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setIcon(new ImageIcon(binoculars));
		button_1.setBounds(406, 228, 70, 23);
		frame.getContentPane().add(button_1);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBounds(482, 0, 60, 48);
		frame.getContentPane().add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				txtDubai.setText("");
				txttaxrate.setText("");
				textField_9.setText("");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(542, 0, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		frame.getContentPane().add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tax_code=textField.getText();
				String tax_desc=txtDubai.getText();
				String tax_rate=txttaxrate.getText();
				String account_code=textField_9.getText();
				if(tax_code.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter or Select Tax Code");
				}
				else if(tax_desc.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter Tax Description");
				}
				else if(tax_rate.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter Tax Rate");
				}
				else if(!tax_rate.matches("^[-+]?\\d+(\\.\\d+)?$"))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter Tax Rate");
				}
				else if(account_code.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter or Select Account");
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
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_tax where tax_code='"+tax_code+"' ");
						if(resultSet.next())
						{
							 String query="update "+company+".mr_tax set store='"+store+"',tax_desc='"+tax_desc+"',tax_rate='"+tax_rate+"',account_code='"+account_code+"', `created_date`='"+formattedDate+"' WHERE id='"+resultSet.getString("id")+"' ";
							 statement.executeUpdate(query);
							 JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
						else
						{
							String query="insert into "+company+".mr_tax(tax_desc,tax_rate,account_code,created_date,tax_code,store)values('"+tax_desc+"','"+tax_rate+"','"+account_code+"','"+formattedDate+"','"+tax_code+"','"+store+"')";
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
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(624, 0, 70, 48);
		frame.getContentPane().add(menuItem_2);
	//	Border border = BorderFactory.createLineBorder(Color.BLACK);
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 350);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	static void get_tax_val(String company, String sel_val) {
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_tax where tax_code='"+sel_val+"' ");
			if(resultSet.next())
			{
				textField.setText(resultSet.getString("tax_code"));
            	txtDubai.setText(resultSet.getString("tax_desc"));
            	txttaxrate.setText(resultSet.getString("tax_rate"));
            	textField_9.setText(resultSet.getString("account_code"));
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
