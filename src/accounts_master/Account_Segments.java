package accounts_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;

import system_setups.user_list;

public class Account_Segments {

	private JFrame frame;
	private static JTextField textField;
	public static JTextField textField_3;
	public static JTextField textField_4;
	private JComboBox<String> comboBox;
	private static JTextField textField_7;
	public static JTextField textField_1;
	private JComboBox<String> comboBox_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Account_Segments window = new Account_Segments("mr_pro","1.0","");
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
	public Account_Segments(String company,String version, String language) {
		initialize(company,version,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 */
	private void initialize(final String company,final String version, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 516, 411);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 498, 364);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblRegisterControl = new JLabel("Account Segments");
		if(language.equals("Arabic"))
		{
			lblRegisterControl.setText("ﬁÿ«⁄«  «·Õ”«»");
		}
		lblRegisterControl.setIcon(new ImageIcon("C:\\Users\\abdullasuhail\\Desktop\\caferpro icons\\Bank-Check-icon.png"));
		lblRegisterControl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRegisterControl.setBounds(10, 20, 228, 44);
		panel.add(lblRegisterControl);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 77, 498, 2);
		panel.add(separator);
		
		Image search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		
		JLabel lblNewLabel = new JLabel("Segment ");
		if(language.equals("Arabic"))
		{
			lblNewLabel.setText("‘—ÌÕ…");
		}
		lblNewLabel.setBounds(22, 134, 70, 14);
		panel.add(lblNewLabel);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("Branch");
		comboBox.setBounds(161, 126, 304, 20);
		panel.add(comboBox);
		
		JLabel lblPaycode = new JLabel("Description ");
		lblPaycode.setBounds(22, 212, 70, 14);
		panel.add(lblPaycode);
		
		textField = new JTextField();
		textField.setBounds(161, 208, 245, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new user_list(company, "acc_segment1", version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(search));
		btnNewButton.setBounds(418, 248, 47, 23);
		panel.add(btnNewButton);
		
		JLabel lblDefaultAccount = new JLabel("A/P");
		lblDefaultAccount.setBounds(22, 290, 47, 14);
		panel.add(lblDefaultAccount);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(161, 288, 245, 20);
		panel.add(textField_3);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new user_list(company, "acc_segment2", version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_4.setIcon(new ImageIcon(search));
		button_4.setBounds(418, 284, 47, 23);
		panel.add(button_4);
		
		JLabel lblClearingAccount = new JLabel("A/R");
		lblClearingAccount.setBounds(22, 328, 70, 14);
		panel.add(lblClearingAccount);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(161, 328, 245, 20);
		panel.add(textField_4);
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new user_list(company, "acc_segment3", version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_5.setIcon(new ImageIcon(search));
		button_5.setBounds(418, 328, 47, 23);
		panel.add(button_5);
		
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
		menuItem.setBounds(278, 13, 60, 48);
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		if(language.equals("Arabic"))
		{
			menuItem.setText("Ì‰⁄‘");
		}
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_7.setText("");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBounds(338, 13, 80, 48);
		panel.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		if(language.equals("Arabic"))
		{
			menuItem_2.setText("ÌÕ›Ÿ");
		}
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField_7.getText().equals(""))
				{
					
					if(language.equals("Arabic"))
					{
						JOptionPane.showMessageDialog(frame, "√œŒ· √Ê «Œ — —„“ «·ﬁÿ⁄…"); 
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Enter or Choose segment code");
					}
				}
				else if(textField.getText().equals(""))
				{
					if(language.equals("Arabic"))
					{
						JOptionPane.showMessageDialog(frame, "√œŒ· «”„ «·„ﬁÿ⁄");  
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Enter segment name"); 
					}
					
				}
				else
				{
					try {
						LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);  
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						String query="select * from "+company+".mr_acc_segment where code='"+textField_7.getText()+"' and segment='"+comboBox.getSelectedItem().toString()+"' ";
						ResultSet resultSet = statement.executeQuery(query);
						if(resultSet.next())
						{
							String query1="update "+company+".mr_acc_segment set  gl_account='"+textField_1.getText()+"',ap_account='"+textField_3.getText()+"',ar_account='"+textField_4.getText()+"' where code='"+textField_7.getText()+"' and segment='"+comboBox.getSelectedItem().toString()+"' ";
							statement.executeUpdate(query1);
							JOptionPane.showMessageDialog(frame, "Saved Successfull  „ «·Õ›Ÿ »‰Ã«Õ");
						}
						else
						{
							String query1="INSERT INTO "+company+".mr_acc_segment( `segment`, `code`, `name`, `gl_account`, `ap_account`, `ar_account`, `created_date`, `modified_date`,store_id) VALUES ('"+comboBox.getSelectedItem().toString()+"','"+textField_7.getText()+"','"+textField.getText()+"','"+textField_1.getText()+"','"+textField_3.getText()+"','"+textField_4.getText()+"','"+formattedDate+"','"+formattedDate+"','"+comboBox_1.getSelectedItem().toString()+"')";
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
		menuItem_2.setBounds(420, 13, 70, 48);
		panel.add(menuItem_2);
		
		JLabel lblCode = new JLabel("Code");
		if(language.equals("Arabic"))
		{
			lblCode.setText("‘›—…");
		}
		lblCode.setBounds(22, 172, 56, 16);
		panel.add(lblCode);
		
		textField_7 = new JTextField();
		textField_7.setBounds(161, 166, 116, 22);
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(161, 248, 245, 20);
		panel.add(textField_1);
		
		JLabel lblGl = new JLabel("G/L");
		if(language.equals("Arabic"))
		{
			lblGl.setText("œ› — «·√” «– «·⁄«„");
		}
		lblGl.setBounds(22, 250, 56, 16);
		panel.add(lblGl);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Segment_chooser(company, version, comboBox.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setBounds(280, 166, 47, 23);
		button.setIcon(new ImageIcon(search));
		panel.add(button);
		
		JLabel lblStore = new JLabel("Store : ");
		if(language.equals("Arabic"))
		{
			lblStore.setText("„Õ· :");
		}
		lblStore.setBounds(20, 87, 56, 16);
		panel.add(lblStore);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(161, 84, 193, 22);
		panel.add(comboBox_1);
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store");
			while(resultSet.next())
			{
				comboBox_1.addItem(resultSet.getString("store_id"));
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
	public static void get_segment_dt(String company,String sel_code,String sel_seg)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_acc_segment where code='"+sel_code+"' and segment='"+sel_seg+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				textField_7.setText(resultSet.getString("code"));
				textField.setText(resultSet.getString("name"));
				textField_1.setText(resultSet.getString("gl_account"));
				textField_3.setText(resultSet.getString("ap_account"));
				textField_4.setText(resultSet.getString("ar_account"));
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}

