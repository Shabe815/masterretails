package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import CafePro.Mainpage;

public class open_item {

	private JFrame frame;
	public static JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					open_item window = new open_item( "mr_pro","admin","S01","S0102", null,"");
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
	public open_item(String company, String itemcode, String type, String input,String qty,String qtysts) {
		initialize(company, itemcode, type, input, qty,qtysts);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param qtysts 
	 */
	private void initialize(final String company,final String itemcode ,final String type ,final String input,final String qty,final String qtysts) {
		frame = new JFrame("");
		
		
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 227, 491);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
				}
				else
				{
					posscreen.txtDisc.requestFocus();
				}
				
			}
		});
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);
		panel.setBounds(12, 41, 190, 313);
		frame.getContentPane().add(panel);
		
		JButton button_12 = new JButton("1");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("1");
			}
		});
		button_12.setFont(new Font("Arial", Font.BOLD, 30));
		button_12.setBounds(0, 53, 60, 60);
		panel.add(button_12);
		
		JButton button_13 = new JButton("2");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("2");
			}
		});
		button_13.setFont(new Font("Arial", Font.BOLD, 30));
		button_13.setBounds(65, 53, 60, 60);
		panel.add(button_13);
		
		JButton button_14 = new JButton("3");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("3");
			}
		});
		button_14.setFont(new Font("Arial", Font.BOLD, 30));
		button_14.setBounds(130, 53, 60, 60);
		panel.add(button_14);
		
		JButton button_15 = new JButton("4");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("4");
			}
		});
		button_15.setFont(new Font("Arial", Font.BOLD, 30));
		button_15.setBounds(0, 118, 60, 60);
		panel.add(button_15);
		
		JButton button_16 = new JButton("5");
		button_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("5");
			}
		});
		button_16.setFont(new Font("Arial", Font.BOLD, 30));
		button_16.setBounds(65, 118, 60, 60);
		panel.add(button_16);
		
		JButton button_17 = new JButton("6");
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("6");
			}
		});
		button_17.setFont(new Font("Arial", Font.BOLD, 30));
		button_17.setBounds(130, 118, 60, 60);
		panel.add(button_17);
		
		JButton button_18 = new JButton("7");
		button_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("7");
			}
		});
		button_18.setFont(new Font("Arial", Font.BOLD, 30));
		button_18.setBounds(0, 183, 60, 60);
		panel.add(button_18);
		
		JButton button_19 = new JButton("8");
		button_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("8");
			}
		});
		button_19.setFont(new Font("Arial", Font.BOLD, 30));
		button_19.setBounds(65, 183, 60, 60);
		panel.add(button_19);
		
		JButton button_20 = new JButton("9");
		button_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("9");
			}
		});
		button_20.setFont(new Font("Arial", Font.BOLD, 30));
		button_20.setBounds(130, 183, 60, 60);
		panel.add(button_20);
		
		JButton button_21 = new JButton("0");
		button_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("0");
			}
		});
		button_21.setFont(new Font("Arial", Font.BOLD, 30));
		button_21.setBounds(65, 248, 60, 60);
		panel.add(button_21);
		
		JButton button_22 = new JButton("<<");
		button_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pass=textField.getText().trim(); 
				if(!pass.equals(""))
				{
					textField.setText(pass.substring(0, pass.length() - 1));
				}
			}
		});
		button_22.setFont(new Font("Arial", Font.BOLD, 20));
		button_22.setBounds(130, 248, 60, 60);
		panel.add(button_22);
		
		JButton button_23 = new JButton(".");
		button_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn(".");
			}
		});
		button_23.setFont(new Font("Arial", Font.BOLD, 30));
		button_23.setBounds(0, 248, 60, 60);
		panel.add(button_23);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submit_btn(company, itemcode, type, input, qty, qtysts);
			}
		});
		textField.setBounds(0, 0, 190, 50);
		panel.add(textField);
		textField.setColumns(10);
		textField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLUE, null, null, null));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setForeground(Color.DARK_GRAY);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 40));
		textField.setBackground(new Color(176, 224, 230));
		textField.setBounds(0, 0, 190, 50);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submit_btn(company, itemcode, type, input, qty,qtysts);
			}
		});
		btnNewButton.setBounds(12, 367, 190, 44);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblEnterPrice = new JLabel("Enter Price");
		lblEnterPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnterPrice.setBounds(12, 13, 190, 28);
		frame.getContentPane().add(lblEnterPrice);
		
		//textField.requestFocus();
		textField.requestFocusInWindow();
		
	}
	private void click_btn(String num)
	{
		try{
			String pass_string=textField.getText().trim(); 
			pass_string+=num;
			textField.setText(pass_string);
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	private void submit_btn(String company,String itemcode,String type,String input,String qty, String qtysts)
	{
		try{
			String pass_string=textField.getText().trim(); 
			if(pass_string.equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Please Enter Price");
			}
			else
			{
				java.sql.Connection con=Dbsetup.local_server.getConnection();
				if(qtysts.equals("1"))
				{
					new OpenQty( company, itemcode,type,input,"1",pass_string);
					open_item.textField.requestFocus();
				}
				else
				{
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.item_result( company, itemcode,type,input,qty,con,pass_string,'0',0);
					}
					else
					{
						posscreen.item_result( company, itemcode,type,input,qty,con,pass_string,'0',0);
					}
				}
				
				
				
				frame.setVisible(false);
			}
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
