package sales_distribution;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;

public class sd_manager {

	private JFrame frame;
	private JPasswordField textField_12;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sd_manager window = new sd_manager("mr_pro","admin","S01","S0102", null);
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
	public sd_manager(String company, String username, String store_name, String amount,String version) {
		initialize(company, username, store_name, amount, version);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company,final String username ,final String store_name ,final String amount,String version) {
		frame = new JFrame(version);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				sd_lines.textField_19.requestFocus();
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 303, 540);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(85, 107, 47));
		panel.setLayout(null);
		panel.setBounds(0, 1, 290, 452);
		frame.getContentPane().add(panel);
		
		JButton button_12 = new JButton("1");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("1");
			}
		});
		button_12.setFont(new Font("Arial", Font.BOLD, 30));
		button_12.setBounds(16, 106, 75, 75);
		panel.add(button_12);
		
		JButton button_13 = new JButton("2");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("2");
			}
		});
		button_13.setFont(new Font("Arial", Font.BOLD, 30));
		button_13.setBounds(107, 106, 75, 75);
		panel.add(button_13);
		
		JButton button_14 = new JButton("3");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("3");
			}
		});
		button_14.setFont(new Font("Arial", Font.BOLD, 30));
		button_14.setBounds(198, 106, 75, 75);
		panel.add(button_14);
		
		JButton button_15 = new JButton("4");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("4");
			}
		});
		button_15.setFont(new Font("Arial", Font.BOLD, 30));
		button_15.setBounds(16, 191, 75, 75);
		panel.add(button_15);
		
		JButton button_16 = new JButton("5");
		button_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("5");
			}
		});
		button_16.setFont(new Font("Arial", Font.BOLD, 30));
		button_16.setBounds(107, 191,75, 75);
		panel.add(button_16);
		
		JButton button_17 = new JButton("6");
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("6");
			}
		});
		button_17.setFont(new Font("Arial", Font.BOLD, 30));
		button_17.setBounds(198, 191, 75, 75);
		panel.add(button_17);
		
		JButton button_18 = new JButton("7");
		button_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("7");
			}
		});
		button_18.setFont(new Font("Arial", Font.BOLD, 30));
		button_18.setBounds(16, 276, 75, 75);
		panel.add(button_18);
		
		JButton button_19 = new JButton("8");
		button_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("8");
			}
		});
		button_19.setFont(new Font("Arial", Font.BOLD, 30));
		button_19.setBounds(107, 276, 75, 75);
		panel.add(button_19);
		
		JButton button_20 = new JButton("9");
		button_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("9");
			}
		});
		button_20.setFont(new Font("Arial", Font.BOLD, 30));
		button_20.setBounds(198, 276, 75, 75);
		panel.add(button_20);
		
		JButton button_21 = new JButton("0");
		button_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("0");
			}
		});
		button_21.setFont(new Font("Arial", Font.BOLD, 30));
		button_21.setBounds(107, 361, 75, 75);
		panel.add(button_21);
		
		JButton button_22 = new JButton("<<");
		button_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pass=new String(textField_12.getPassword()); 
				if(!pass.equals(""))
				{
					textField_12.setText(pass.substring(0, pass.length() - 1));
				}
			}
		});
		button_22.setFont(new Font("Arial", Font.BOLD, 20));
		button_22.setBounds(198, 361, 75, 75);
		panel.add(button_22);
		
		JButton button_23 = new JButton("C");
		button_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_12.setText("");
			}
		});
		button_23.setFont(new Font("Arial", Font.BOLD, 30));
		button_23.setBounds(16, 361, 75, 75);
		panel.add(button_23);
		
		
		textField_12 = new JPasswordField(16);
		textField_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submit_btn(company, store_name,username,amount);
			}
		});
//		textField_12.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyTyped(KeyEvent e) {
//				char vChar = e.getKeyChar();
//				String pass=new String(textField_12.getPassword()); 
//			    if (pass.equals(""))
//			        dot = false;
//			    if (dot == false){
//			        if (vChar == '.') dot = true;
//			        else if (!(Character.isDigit(vChar)
//			                || (vChar == KeyEvent.VK_BACK_SPACE)
//			                || (vChar == KeyEvent.VK_DELETE))) {
//			                e.consume();
//			        }
//			    } else {
//			        if (!(Character.isDigit(vChar)
//			                || (vChar == KeyEvent.VK_BACK_SPACE)
//			                || (vChar == KeyEvent.VK_DELETE))) {
//			                e.consume();
//			        }
//			    }
//			}
//		});
		textField_12.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLUE, null, null, null));
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setForeground(Color.DARK_GRAY);
		textField_12.setFont(new Font("Tahoma", Font.PLAIN, 40));
		textField_12.setEchoChar('*');
		textField_12.setColumns(10);
		textField_12.setBackground(new Color(176, 224, 230));
		textField_12.setBounds(0, 46, 286, 50);
		textField_12.requestFocus();
		panel.add(textField_12);
		
		JLabel lblEnterPasswordFor = new JLabel("ENTER PASSWORD FOR PRIGE CHANGE");
		lblEnterPasswordFor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnterPasswordFor.setForeground(Color.WHITE);
		lblEnterPasswordFor.setBounds(0, 10, 286, 26);
		panel.add(lblEnterPasswordFor);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submit_btn(company, store_name,username,amount);
			}
		});
		btnNewButton.setBounds(0, 454, 290, 44);
		frame.getContentPane().add(btnNewButton);
		
	}
	private void click_btn(String num)
	{
		try{
			String pass_string=new String(textField_12.getPassword()); 
			pass_string+=num;
			textField_12.setText(pass_string);
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	private void submit_btn(String company,String store,String username,String amount)
	{
		try{
			String pass_string=new String(textField_12.getPassword()); 
			if(pass_string.equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Please Enter swipe card ID");
			}
			else
			{
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_users where user_name='ADMIN' and swipe_id='"+pass_string+"' ");
				if(resultSet1.next())
				{
					frame.setVisible(false);
					sd_lines.sellingprice=amount;
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Swipe ID not valid");
				}
				
			}
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
