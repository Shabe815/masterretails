package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Change_Pwd {

	private JFrame frame;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JCheckBox chckbxShowPassword ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Change_Pwd window = new Change_Pwd("mr_pro", "admin", null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param username 
	 * @param host_name 
	 * @param company 
	 */
	public Change_Pwd(String company, String username,String version) {
		initialize(company, username, version);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company,final String username,String version) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		JLabel lblCompanyName = new JLabel("Current Password :");
		lblCompanyName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCompanyName.setBounds(21, 79, 129, 21);
		frame.getContentPane().add(lblCompanyName);
		
		JLabel lblAddress = new JLabel("New Password  :");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(21, 122, 102, 21);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblWebsiteAddress = new JLabel("Confirm Password :");
		lblWebsiteAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWebsiteAddress.setBounds(21, 173, 129, 21);
		frame.getContentPane().add(lblWebsiteAddress);
		
		Image settings = new ImageIcon(this.getClass().getResource("/Settings1.png")).getImage();	
		JLabel lblCompanySetup = new JLabel("4. Change Password");
		lblCompanySetup.setIcon(new ImageIcon(settings));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCompanySetup.setBounds(21, 11, 228, 44);
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(470, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		frame.getContentPane().add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				passwordField.setText("");
				passwordField_1.setText("");
				passwordField_2.setText("");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(530, 7, 80, 48);
		frame.getContentPane().add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user_name=username;
				String old_pwd = new String(passwordField.getPassword());
				String pwd = new String(passwordField_1.getPassword());
				String c_pwd = new String(passwordField_2.getPassword());
				if(old_pwd.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter the Current Password");
					passwordField.requestFocus();
				}
				else if(pwd.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter the New Password");
					passwordField_1.requestFocus();
				}
				else if(c_pwd.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter the Confirm Password");
					passwordField_2.requestFocus();
				}
				else
				{
					if(pwd.equals(c_pwd))
					{
						LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);  
					    Connection con=Dbsetup.dbconn.getConnection();
						Statement statement;
						try {
							statement = con.createStatement();
							ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_users where user_name='"+user_name+"' ");
							if(resultSet.next())
							{
								String query="update "+company+".mr_users set password='"+pwd+"',modified_date='"+formattedDate+"' WHERE id='"+resultSet.getString("id")+"' ";
								statement.executeUpdate(query);
								JOptionPane.showMessageDialog(frame, "Saved Successfully");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}  
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Password Not Match");
					}
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(612, 7, 70, 48);
		frame.getContentPane().add(menuItem_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(163, 78, 248, 22);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(163, 121, 248, 22);
		frame.getContentPane().add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(163, 172, 248, 22);
		frame.getContentPane().add(passwordField_2);
		
		chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxShowPassword.isSelected())
				{
					passwordField_1.setEchoChar((char) 0);
					passwordField_2.setEchoChar((char) 0);
				}
				else
				{
					passwordField_1.setEchoChar('*');
					passwordField_2.setEchoChar('*');
				}
			}
		});
		chckbxShowPassword.setBounds(163, 213, 172, 25);
		frame.getContentPane().add(chckbxShowPassword);
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 699, 402);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
