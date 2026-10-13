package CafePro;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import org.json.JSONException;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class loginscreen {

	private static JFrame frame;
	private final JSeparator separator_2 = new JSeparator();
	private JTextField textField_1;
	private JTextField hiddncard;
	private JTextField company_nme;
	private JTextField txtLocalhost;
	private JTextField textField;
	private JPasswordField passwordField;
	private static JComboBox<String> comboBox;
	private String decimal="2";
	public static String display_port;
	public static String logout_msg1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					loginscreen window = new loginscreen("S01","S0101","127.0.0.1","","","1","1","Ar");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application
	 * @param acc_active 
	 * @param activeSD 
	 * @param language 
	 * @param host_name 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	public loginscreen(String store,String posuser,String server,String version,String company_name, String activeSD, String acc_active, String language) throws JSONException, SQLException {
		initialize(store,posuser,server, version,company_name,activeSD,acc_active,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 * @param host_name 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String store,final String posuser,final String server,final String version,final String company_name,final String activeSD, final String acc_active, final String language) throws JSONException, SQLException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setMaximumSize(new Dimension(57, 27));
		frame.setBounds(100, 100, 775, 557);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		JMenuItem menuItem_1 = new JMenuItem("");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					setSync(comboBox.getSelectedItem().toString(), store);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(0, 0, 32, 30);
		menuItem_1.setIcon(new ImageIcon(refresh));
		frame.getContentPane().add(menuItem_1);
		
		JLabel lblRegistredTo = new JLabel("REGISTRED TO :");
		lblRegistredTo.setBounds(69, 11, 100, 14);
		frame.getContentPane().add(lblRegistredTo);
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 33, 767, 2);
		frame.getContentPane().add(separator);
		JLabel lblServer = new JLabel("SERVER :");
		lblServer.setBounds(69, 47, 87, 14);
		frame.getContentPane().add(lblServer);
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 72, 767, 2);
		frame.getContentPane().add(separator_1);
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(187, 85, 179, 24);
		frame.getContentPane().add(lblNewLabel);
		comboBox = new JComboBox<String>();
		final Connection con=Dbsetup.dbconn.getConnection();
		Statement statement=con.createStatement();  
		ResultSet resultSet = statement.executeQuery("select company_name from config_db.company ");
		while(resultSet.next())
		{
			comboBox.addItem(resultSet.getString("company_name"));
		}
		separator_2.setBounds(0, 360, 767, 2);
		frame.getContentPane().add(separator_2);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(543, 72, -11, 304);
		frame.getContentPane().add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(507, 72, 5, 290);
		frame.getContentPane().add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(506, 72, 2, 290);
		frame.getContentPane().add(separator_6);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_1.setEditable(false);
		textField_1.setBounds(522, 101, 235, 28);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		JLabel lblEnterTheSwipe = new JLabel("Enter the Swipe Card Code");
		lblEnterTheSwipe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterTheSwipe.setBounds(544, 85, 202, 14);
		frame.getContentPane().add(lblEnterTheSwipe);
		
		Image num1=new ImageIcon(this.getClass().getResource("/1.png")).getImage();
		Image num2=new ImageIcon(this.getClass().getResource("/2.png")).getImage();
		Image num3=new ImageIcon(this.getClass().getResource("/3.png")).getImage();
		Image num4=new ImageIcon(this.getClass().getResource("/4.png")).getImage();
		Image num5=new ImageIcon(this.getClass().getResource("/5.png")).getImage();
		Image num6=new ImageIcon(this.getClass().getResource("/6.png")).getImage();
		Image num7=new ImageIcon(this.getClass().getResource("/7.png")).getImage();
		Image num8=new ImageIcon(this.getClass().getResource("/8.png")).getImage();
		Image num9=new ImageIcon(this.getClass().getResource("/9.png")).getImage();
		Image num0=new ImageIcon(this.getClass().getResource("/0.png")).getImage();
		Image back=new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		Image ok=new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		
		final JButton btn1 = new JButton("");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn1.setBorder(null);
				click_btn("1");
			}
		});
		btn1.setForeground(new Color(243, 229, 216));
		btn1.setBackground(new Color(243, 229, 216));
		btn1.setBorder(null);
		btn1.setFocusPainted(false);
		btn1.setIcon(new ImageIcon(num1));
		btn1.setBounds(554, 140, 50, 50);
		frame.getContentPane().add(btn1);
		
		final JButton btn2 = new JButton("");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn2.setBorder(null);
				click_btn("2");
			}
		});
		btn2.setForeground(new Color(243, 229, 216));
		btn2.setBackground(new Color(243, 229, 216));
		btn2.setBorder(null);
		btn2.setIcon(new ImageIcon(num2));
		btn2.setBounds(615, 140, 50, 50);
		btn2.setFocusPainted(false);
		frame.getContentPane().add(btn2);
		
		final JButton btn3 = new JButton("");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn3.setBorder(null);
				click_btn("3");
			}
		});
		btn3.setForeground(new Color(243, 229, 216));
		btn3.setBackground(new Color(243, 229, 216));
		btn3.setBorder(null);
		btn3.setIcon(new ImageIcon(num3));
		btn3.setBounds(673, 140, 50, 50);
		btn3.setFocusPainted(false);
		frame.getContentPane().add(btn3);
		
		final JButton btn4 = new JButton("");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn4.setBorder(null);
				click_btn("4");
			}
		});
		btn4.setForeground(new Color(243, 229, 216));
		btn4.setBackground(new Color(243, 229, 216));
		btn4.setBorder(null);
		btn4.setIcon(new ImageIcon(num4));
		btn4.setBounds(554, 198, 50, 50);
		btn4.setFocusPainted(false);
		frame.getContentPane().add(btn4);
		
		final JButton btn5 = new JButton("");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn1.setBorder(null);
				click_btn("5");
			}
		});
		btn5.setForeground(new Color(243, 229, 216));
		btn5.setBackground(new Color(243, 229, 216));
		btn5.setBorder(null);
		btn5.setIcon(new ImageIcon(num5));
		btn5.setBounds(615, 198, 50, 50);
		btn5.setFocusPainted(false);
		frame.getContentPane().add(btn5);
		
		final JButton btn6 = new JButton("");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn1.setBorder(null);
				click_btn("6");
			}
		});
		btn6.setForeground(new Color(243, 229, 216));
		btn6.setBackground(new Color(243, 229, 216));
		btn6.setBorder(null);
		btn6.setIcon(new ImageIcon(num6));
		btn6.setBounds(675, 198, 50, 50);
		btn6.setFocusPainted(false);
		frame.getContentPane().add(btn6);
		
		final JButton btn7 = new JButton("");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn1.setBorder(null);
				click_btn("7");
			}
		});
		btn7.setForeground(new Color(243, 229, 216));
		btn7.setBackground(new Color(243, 229, 216));
		btn7.setBorder(null);
		btn7.setIcon(new ImageIcon(num7));
		btn7.setBounds(554, 251, 50, 50);
		btn7.setFocusPainted(false);
		frame.getContentPane().add(btn7);
		
		final JButton btn8 = new JButton("");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn1.setBorder(null);
				click_btn("8");
			}
		});
		btn8.setForeground(new Color(243, 229, 216));
		btn8.setBackground(new Color(243, 229, 216));
		btn8.setBorder(null);
		btn8.setIcon(new ImageIcon(num8));
		btn8.setBounds(615, 251, 50, 50);
		btn8.setFocusPainted(false);
		frame.getContentPane().add(btn8);
		
		final JButton btn9 = new JButton("");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn1.setBorder(null);
				click_btn("9");
			}
		});
		btn9.setForeground(new Color(243, 229, 216));
		btn9.setBackground(new Color(243, 229, 216));
		btn9.setBorder(null);
		btn9.setIcon(new ImageIcon(num9));
		btn9.setBounds(673, 251, 50, 50);
		btn9.setFocusPainted(false);
		frame.getContentPane().add(btn9);
		
		final JButton btn0 = new JButton("");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn1.setBorder(null);
				click_btn("0");
			}
		});
		btn0.setForeground(new Color(243, 229, 216));
		btn0.setBackground(new Color(243, 229, 216));
		btn0.setBorder(null);
		btn0.setIcon(new ImageIcon(num0));
		btn0.setBounds(615, 310, 50, 50);
		btn0.setFocusPainted(false);
		frame.getContentPane().add(btn0);
		
		final JButton btnbk = new JButton("");
		btnbk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnbk.setBorder(null);
				clear_btn();
			}
		});
		btnbk.setForeground(new Color(243, 229, 216));
		btnbk.setBackground(new Color(243, 229, 216));
		btnbk.setBorder(null);
		btnbk.setIcon(new ImageIcon(back));
		btnbk.setBounds(554, 310, 50, 50);
		btnbk.setFocusPainted(false);
		frame.getContentPane().add(btnbk);
		
		final JButton btnok = new JButton("");
		btnok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn1.setBorder(null);
				String combo=comboBox.getSelectedItem().toString();
				submit_btn(combo, store,posuser,version,server, activeSD, acc_active,language);	
			}
		});
		btnok.setForeground(new Color(243, 229, 216));
		btnok.setBackground(new Color(243, 229, 216));
		btnok.setBorder(null);
		btnok.setIcon(new ImageIcon(ok));
		btnok.setBounds(673, 310, 50, 50);
		btnok.setFocusPainted(false);
		frame.getContentPane().add(btnok);
		
		hiddncard = new JTextField();
		hiddncard.setEditable(false);
		hiddncard.setVisible(false);
		hiddncard.setBounds(524, 104, 116, 22);
		frame.getContentPane().add(hiddncard);
		hiddncard.setColumns(10);
		
		txtLocalhost = new JTextField();
		txtLocalhost.setText(server);
		txtLocalhost.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLocalhost.setEditable(false);
		txtLocalhost.setColumns(10);
		txtLocalhost.setBorder(null);
		txtLocalhost.setBackground(new Color(243, 229, 216));
		txtLocalhost.setBounds(147, 43, 610, 22);
		frame.getContentPane().add(txtLocalhost);
		
		JLabel lblCafpro = new JLabel("Master Retails");
		lblCafpro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCafpro.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblCafpro.setBounds(10, 371, 129, 24);
		frame.getContentPane().add(lblCafpro);
		
		JLabel lblCafeproVersion = new JLabel(version);
		lblCafeproVersion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCafeproVersion.setBounds(27, 497, 129, 14);
		frame.getContentPane().add(lblCafeproVersion);
		
		JLabel lblNewLabel_2 = new JLabel("");
		Image logos=new ImageIcon(this.getClass().getResource("/mr_130.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(logos));
		lblNewLabel_2.setBounds(10, 394, 129, 99);
		frame.getContentPane().add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(27, 104, 459, 251);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("COMPANY");
		label.setBounds(25, 19, 115, 28);
		panel.add(label);
		
		comboBox.setBounds(180, 15, 253, 34);
		panel.add(comboBox);
		
		JCheckBox checkBox = new JCheckBox("On Screen Keyboard");
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ad) {
				try {
					if(ad.getStateChange() == ItemEvent.SELECTED) {
						Runtime.getRuntime().exec("cmd /c osk");
					} 
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		checkBox.setBackground(new Color(243, 229, 216));
		checkBox.setBounds(190, 66, 145, 23);
		panel.add(checkBox);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(180, 102, 253, 34);
		panel.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER){
					String userName = textField.getText();
					@SuppressWarnings("deprecation")
					String Password = passwordField.getText();
					String combo=comboBox.getSelectedItem().toString();
					validation(userName,Password,combo, store,posuser,version,server, activeSD, acc_active,language);
		        }
			}
		});
		
		passwordField.setBounds(180, 151, 253, 34);
		panel.add(passwordField);
		
		JLabel label_1 = new JLabel("PASSWORD");
		label_1.setBounds(25, 152, 115, 25);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("USER NAME");
		label_2.setBorder(null);
		label_2.setBackground(Color.CYAN);
		label_2.setBounds(25, 108, 115, 25);
		panel.add(label_2);
		
		JButton button = new JButton("OK");
		button.setBounds(66, 196, 89, 34);
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER){
					String userName = textField.getText();
					@SuppressWarnings("deprecation")
					String Password = passwordField.getText();
					String combo=comboBox.getSelectedItem().toString();
					validation(userName,Password,combo, store,posuser,version,server, activeSD, acc_active,language);
		        }
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = textField.getText();
				@SuppressWarnings("deprecation")
				String Password = passwordField.getText();
				String combo=comboBox.getSelectedItem().toString();
				validation(userName,Password,combo,  store,posuser, version,server, activeSD, acc_active,language);
			}

		});
		panel.add(button);
		
		JButton button_1 = new JButton("CANCEL");
		button_1.setBounds(281, 200, 89, 34);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		panel.add(button_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(147, 366, 576, 156);
		frame.getContentPane().add(panel_1);
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Master Retails - Retail Management Software 2019 Allrights Reserved.");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 4, 576, 16);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblForAnyEnquiry = new JLabel("For any enquiry email us on info@alsafitrading.com");
		lblForAnyEnquiry.setHorizontalAlignment(SwingConstants.CENTER);
		lblForAnyEnquiry.setBounds(0, 24, 576, 14);
		panel_1.add(lblForAnyEnquiry);
		
		JLabel lblInformation = new JLabel("Information");
		lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformation.setBounds(0, 62, 576, 24);
		panel_1.add(lblInformation);
		lblInformation.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblAfterLogin = new JLabel("After Login , It is understood that user agrees to the terms and conditions.");
		lblAfterLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAfterLogin.setBounds(0, 90, 576, 14);
		panel_1.add(lblAfterLogin);
		
		JLabel lblTemperingTheDatabase = new JLabel("Tampering the database from the backend operation voids the warranty ");
		lblTemperingTheDatabase.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperingTheDatabase.setBounds(0, 108, 576, 14);
		panel_1.add(lblTemperingTheDatabase);
		
		JLabel lblWithImmediateEffect = new JLabel("with immediate effect.");
		lblWithImmediateEffect.setHorizontalAlignment(SwingConstants.CENTER);
		lblWithImmediateEffect.setBounds(0, 126, 576, 14);
		panel_1.add(lblWithImmediateEffect);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(249, 84, 87, 2);
		panel_1.add(separator_3);
		
		JLabel lblCallUsOn = new JLabel("Call us on +971 55 963 43 55");
		lblCallUsOn.setHorizontalAlignment(SwingConstants.CENTER);
		lblCallUsOn.setBounds(0, 42, 576, 16);
		panel_1.add(lblCallUsOn);
		
		company_nme = new JTextField(company_name);
		company_nme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		company_nme.setBorder(null);
		company_nme.setEditable(false);
		company_nme.setBackground(new Color(243, 229, 216));
		company_nme.setBounds(169, 7, 588, 22);
		company_nme.setColumns(10);
		frame.getContentPane().add(company_nme);
		
		JButton btnNewButton = new JButton(".");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setMysqldbcolumns(comboBox.getSelectedItem().toString(), store,con);
			}
		});
		btnNewButton.setBounds(0, 65, 5, 5);
		frame.getContentPane().add(btnNewButton);
		
		JButton button_2 = new JButton(".");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setdbcolumns(comboBox.getSelectedItem().toString(), store,con);
			}
		});
		button_2.setBounds(0, 35, 5, 5);
		frame.getContentPane().add(button_2);
		
		get_register_name(comboBox.getSelectedItem().toString(),posuser, store,con);
		//setdbcolumns(comboBox.getSelectedItem().toString(), store,con);
		clickOnValidate(button_2, "play", KeyEvent.VK_F2 , store,  con);
		
		
		clickOnback( btnNewButton, "play", KeyEvent.VK_F1 , store,  con);
		
		
		
		try {
			Runtime.getRuntime().exec(new String[] {"cmd","/c","cls >"+display_port+"\""});
			Runtime.getRuntime().exec("cmd /c start \"\" C:\\MasterRetails\\SETPOS.bat");
			Runtime.getRuntime().exec(new String[] {"cmd","/c","run","C:\\MasterRetails\\SETPOS.bat "});
//			String[] command = {"C:\\MasterRetails\\SETPOS.bat","start"};
//		    ProcessBuilder pb = new ProcessBuilder(command);
//		    pb.redirectErrorStream(true);
//		    Process exec = pb.start();

//		    BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream()));
//		    String text = null;
//		    while ((text = br.readLine()) != null) {
//		        System.out.println(text);
//		    }
			//
			Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO "+logout_msg1+" >"+display_port+"\""});
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public static void clickOnValidate(final AbstractButton button, String actionName, int key,final String store, final Connection con)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6694624852420723982L;

			/**
			 * 
			 */
			public void actionPerformed( ActionEvent e )
	        {
				//setdbcolumns(comboBox.getSelectedItem().toString(), store,con);
	        }
	    } );
	}
	public static void clickOnback(final AbstractButton button, String actionName, int key,final String store, final Connection con)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6694624852420723982L;

			/**
			 * 
			 */
			public void actionPerformed( ActionEvent e )
	        {
				setMysqldbcolumns(comboBox.getSelectedItem().toString(), store,con);
	        }
	    } );
	}
	private void setSync(String dbname, String store) throws SQLException {
		File configFile = new File("C:/MasterRetails/config.properties");
		try {
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    String counter_type = props.getProperty("counter");
		    reader.close();
		    if(!counter_type.equals("BO"))
		    {
		    	Connection con1=Dbsetup.local_server.getConnection();
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement(); 
				Statement statement1 = con1.createStatement();
				ResultSet resultSet1 = statement.executeQuery("select db_name from config_db.company where company_name='"+dbname+"' ");
				if(resultSet1.next())
				{
					String dbname1=resultSet1.getString("db_name");
					String query="TRUNCATE TABLE "+dbname1+".mr_users ";
					statement1.executeUpdate(query);
					ResultSet resultSet = statement.executeQuery("select * from "+dbname1+".mr_users ");
					while (resultSet.next()) 
					{
						String ins_query="insert into "+dbname1+".mr_users(`user_code`, `user_name`, `swipe_id`, `password`, `user_type`, `image`, `shift_code`, `language`, `created_date`, `modified_date`, `status`) values('"+resultSet.getString("user_code")+"','"+resultSet.getString("user_name")+"','"+resultSet.getString("swipe_id")+"','"+resultSet.getString("password")+"','"+resultSet.getString("user_type")+"','"+resultSet.getString("image")+"','"+resultSet.getString("shift_code")+"','"+resultSet.getString("language")+"','"+resultSet.getString("created_date")+"','"+resultSet.getString("modified_date")+"','"+resultSet.getString("status")+"')";
						statement1.executeUpdate(ins_query);
					}
					String query1="TRUNCATE TABLE "+dbname1+".mr_user_poscontrol";
					statement1.executeUpdate(query1);
					ResultSet resultSet2 = statement.executeQuery("select * from "+dbname1+".mr_user_poscontrol");
					while (resultSet2.next()) 
					{
						String ins_query="insert into "+dbname1+".mr_user_poscontrol( `ref_user_code`, `chckbxUserReopencloseregi`, `UseradditemsnotfoundmakingPO`, `userHoldinvoive_pos`, `userchangesaleinvosave_reopen`, `Credit_lmt_override`, `DeliveryAgent`, `User_Change_qnty_pos`, `userquick_retun`, `usersalerep`, `usermake_sale_order`, `ManageSettledInvoices`, `MaximumRefundAmount`, `maxdisc_user_sale`, `ChangePrice`, `PosUser`, `VoidCurrentTransation`, `VoidSettledinvo`, `OverrideGrossMargin`, `DeleteLinespos`, `userview_cost`, `AllowNoSale`, `AllowPayIn`, `AllowPayOut`, `usermake_sale_retrn`, `refund_amount`, `discount_amount`, `commission_dt`, `change_unit`, `issue_store_credit`, `created_date`, `modified_date`) values ('"+resultSet2.getString("ref_user_code")+"','"+resultSet2.getString("chckbxUserReopencloseregi")+"','"+resultSet2.getString("UseradditemsnotfoundmakingPO")+"','"+resultSet2.getString("userHoldinvoive_pos")+"','"+resultSet2.getString("userchangesaleinvosave_reopen")+"','"+resultSet2.getString("Credit_lmt_override")+"','"+resultSet2.getString("DeliveryAgent")+"','"+resultSet2.getString("User_Change_qnty_pos")+"','"+resultSet2.getString("userquick_retun")+"','"+resultSet2.getString("usersalerep")+"','"+resultSet2.getString("usermake_sale_order")+"','"+resultSet2.getString("ManageSettledInvoices")+"','"+resultSet2.getString("MaximumRefundAmount")+"','"+resultSet2.getString("maxdisc_user_sale")+"','"+resultSet2.getString("ChangePrice")+"','"+resultSet2.getString("PosUser")+"','"+resultSet2.getString("VoidCurrentTransation")+"','"+resultSet2.getString("VoidSettledinvo")+"','"+resultSet2.getString("OverrideGrossMargin")+"','"+resultSet2.getString("DeleteLinespos")+"','"+resultSet2.getString("userview_cost")+"','"+resultSet2.getString("AllowNoSale")+"','"+resultSet2.getString("AllowPayIn")+"','"+resultSet2.getString("AllowPayOut")+"','"+resultSet2.getString("commission_dt")+"','"+resultSet2.getString("discount_amount")+"','"+resultSet2.getString("refund_amount")+"','"+resultSet2.getString("usermake_sale_retrn")+"','"+resultSet2.getString("change_unit")+"','"+resultSet2.getString("issue_store_credit")+"','"+resultSet2.getString("created_date")+"','"+resultSet2.getString("modified_date")+"')";
						statement1.executeUpdate(ins_query);
					}
					String query2="TRUNCATE TABLE "+dbname1+".mr_assign_register ";
					statement1.executeUpdate(query2);
					ResultSet resultSet3 = statement.executeQuery("select * from "+dbname1+".mr_assign_register ");
					while (resultSet3.next()) 
					{
						String ins_query="insert into "+dbname1+".mr_assign_register( `ref_user_code`, `ref_register_num`, `select_status`, `created_date`, `modified_date`) values ('"+resultSet3.getString("ref_user_code")+"','"+resultSet3.getString("ref_register_num")+"','"+resultSet3.getString("select_status")+"','"+resultSet3.getString("created_date")+"','"+resultSet3.getString("modified_date")+"')";
						statement1.executeUpdate(ins_query);
					}
					String query3="TRUNCATE TABLE "+dbname1+".mr_store ";
					statement1.executeUpdate(query3);
					ResultSet resultSet4 = statement.executeQuery("select * from "+dbname1+".mr_store ");
					while (resultSet4.next()) 
					{
						String ins_query="insert into "+dbname1+".mr_store(  `store_id`, `store_short_name`, `store_name`, `addr1`, `addr2`, `addr3`, `addr4`, `city`, `country`, `land_line`, `web`, `ph`, `email`, `trn`, `barcode_flag`, `flag`, `ApplyItemDiscount`, `ChangeCustomerAt`, `ApplyCustomerDiscount`, `DonotPerformEod`, `UseLoyalityProgram`, `ConsolidatedPosLines`, `NoofDecimal`, `decimal_count`, `AllowNegativeQuantity`, `apply_store_credit`, `created_date`, `modified_date`, `status`, `sd_active`, `barcode_label`) values ('"+resultSet4.getString("store_id")+"','"+resultSet4.getString("store_short_name")+"','"+resultSet4.getString("store_name")+"','"+resultSet4.getString("addr1")+"','"+resultSet4.getString("addr2")+"','"+resultSet4.getString("addr3")+"','"+resultSet4.getString("addr4")+"','"+resultSet4.getString("city")+"','"+resultSet4.getString("country")+"','"+resultSet4.getString("land_line")+"','"+resultSet4.getString("web")+"','"+resultSet4.getString("ph")+"','"+resultSet4.getString("email")+"','"+resultSet4.getString("trn")+"','"+resultSet4.getString("barcode_flag")+"','"+resultSet4.getString("flag")+"','"+resultSet4.getString("ApplyItemDiscount")+"','"+resultSet4.getString("ChangeCustomerAt")+"','"+resultSet4.getString("ApplyCustomerDiscount")+"','"+resultSet4.getString("DonotPerformEod")+"','"+resultSet4.getString("UseLoyalityProgram")+"','"+resultSet4.getString("ConsolidatedPosLines")+"','"+resultSet4.getString("NoofDecimal")+"','"+resultSet4.getString("decimal_count")+"','"+resultSet4.getString("AllowNegativeQuantity")+"','"+resultSet4.getString("apply_store_credit")+"','"+resultSet4.getString("created_date")+"','"+resultSet4.getString("modified_date")+"','"+resultSet4.getString("status")+"','"+resultSet4.getString("sd_active")+"','"+resultSet4.getString("barcode_label")+"')";
						statement1.executeUpdate(ins_query);
					}
					
				}
		    }
		    JOptionPane.showMessageDialog(frame, "Sync Completed");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frame, e.getMessage());
			//JOptionPane.showMessageDialog( Mainpage.frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}

	private static void setMysqldbcolumns(String dbname, String store, Connection con) {
		try {
			Statement statement=con.createStatement();
			Statement statement1=con.createStatement();
			Statement statement2=con.createStatement();
			ResultSet resultSet = statement.executeQuery("select db_name from config_db.company ");
			while(resultSet.next())
			{
				String dbname1=resultSet.getString("db_name");
				Mysqlqueries(dbname1,statement1,statement2);
				
			}
			Connection con1=Dbsetup.local_server.getConnection();
			Statement statement3=con1.createStatement();
			Statement statement4=con1.createStatement();
			ResultSet resultSet1 = statement.executeQuery("select db_name from config_db.company ");
			while(resultSet1.next())
			{
				String dbname1=resultSet1.getString("db_name");
				Mysqlqueries(dbname1,statement3,statement4);
			}
			frame.setCursor(Cursor.getDefaultCursor());
			JOptionPane.showMessageDialog(frame, "DataBase Updated Successfully");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
	}
	
	private static void Mysqlqueries(String dbname1, Statement statement, Statement statement1) throws SQLException {
		try
		{
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			//1. `mr_accounts_category`
			String query1="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_accounts_category' ";
			ResultSet resultSet1 = statement1.executeQuery(query1);
			if(!resultSet1.next())
			{
				String query1_1="ALTER TABLE "+dbname1+".mr_accounts_category ADD INDEX `trans_indx` (`acc_type`(100));";
				statement.executeUpdate(query1_1);
			}
			
			//2. `mr_accounts_trans_header`
			String query2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_accounts_trans_header'  ";
			ResultSet resultSet2 = statement1.executeQuery(query2);
			if(!resultSet2.next())
			{
				String query2_1="ALTER TABLE "+dbname1+".mr_accounts_trans_header ADD INDEX `trans_indx` (`tran_orgin`(100),tran_seqencecode(100)); ";
				statement.executeUpdate(query2_1);
			}
			//3. `mr_accounts_tran_detls`
			String query3="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_accounts_tran_detls'";
			ResultSet resultSet3 = statement1.executeQuery(query3);
			if(!resultSet3.next())
			{
				String query3_1="ALTER TABLE "+dbname1+".mr_accounts_tran_detls ADD INDEX `trans_indx` (`tran_accode`(100),tran_seqence_code(100));";
				statement.executeUpdate(query3_1);
			}

			//4. `mr_account_config`
			

			//5. `mr_account_group_master`
			String query5="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_account_group_master' ";
			ResultSet resultSet5 = statement1.executeQuery(query5);
			if(!resultSet5.next())
			{
				String query5_1="ALTER TABLE "+dbname1+".mr_account_group_master ADD INDEX  `trans_indx` (`acc_grp_code`(100),acc_grp_type(100),acc_grp_cat(100));";
				statement.executeUpdate(query5_1);
			}
			
			//6. `mr_account_type`
			String query6="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_account_type' ";
			ResultSet resultSet6 = statement1.executeQuery(query6);
			if(!resultSet6.next())
			{
				String query6_1="ALTER TABLE "+dbname1+".mr_account_type ADD INDEX  `trans_indx` (`acc_type_name`(100));";
				statement.executeUpdate(query6_1);
			}
			
			//7. `mr_acc_ledger`
			String query7="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_acc_ledger' ";
			ResultSet resultSet7 = statement1.executeQuery(query7);
			if(!resultSet7.next())
			{
				String query7_1="ALTER TABLE "+dbname1+".mr_acc_ledger ADD INDEX `trans_indx` (`jv_num`(100),ac_code(100),source(100));";
				statement.executeUpdate(query7_1);
			}
			String query7_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_acc_ledger' and column_name='status' ; ";
			ResultSet resultSet7_6 = statement1.executeQuery(query7_6);
			if(!resultSet7_6.next())
			{
				String query7_7="ALTER TABLE "+dbname1+".mr_acc_ledger ADD COLUMN  `status` VARCHAR(10) NULL DEFAULT 'OPEN' ;";
				statement.executeUpdate(query7_7);
			}
			
			//8. `mr_acc_paycode`
			String query8="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_acc_paycode'";
			ResultSet resultSet8 = statement1.executeQuery(query8);
			if(!resultSet8.next())
			{
				String query8_1="ALTER TABLE "+dbname1+".mr_acc_paycode ADD INDEX `trans_indx` (`store_id`(100));";
				statement.executeUpdate(query8_1);
			}
			
			//9. `mr_acc_segment`
			String query9="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_acc_segment' ";
			ResultSet resultSet9 = statement1.executeQuery(query9);
			if(!resultSet9.next())
			{
				String query9_1="ALTER TABLE "+dbname1+".mr_acc_segment ADD INDEX `trans_indx` (`store_id`(100));";
				statement.executeUpdate(query9_1);
			}
			
			//10. `mr_ap_vouchers`
			String query10="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_ap_vouchers' ";
			ResultSet resultSet10 = statement1.executeQuery(query10);
			if(!resultSet10.next())
			{
				String query10_1="ALTER TABLE "+dbname1+".mr_ap_vouchers ADD INDEX `trans_indx` (`store_id`(100),`trans_num`(100),`ref_vendor_id`(100));";
				statement.executeUpdate(query10_1);
			}
			String query10_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_ap_vouchers' and column_name='status' ; ";
			ResultSet resultSet10_6 = statement1.executeQuery(query10_6);
			if(!resultSet10_6.next())
			{
				String query10_7="ALTER TABLE "+dbname1+".mr_ap_vouchers ADD COLUMN `status` TEXT NULL ;";
				statement.executeUpdate(query10_7);
			}
			
			//11.	mr_assembly_item
			String query11="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_assembly_item' ; ";
			ResultSet resultSet11 = statement1.executeQuery(query11);
			if(!resultSet11.next())
			{
				String query11_1="CREATE TABLE "+dbname1+".mr_assembly_item (`id` int(255) NOT NULL AUTO_INCREMENT,  `itemcode` text NOT NULL,  `barcode` text NOT NULL,  `itemname` text NOT NULL,  `qtyoh` double NOT NULL DEFAULT '0',  `new_qty` double NOT NULL DEFAULT '0', `sale_qty` double NOT NULL DEFAULT '0', `bal_qty` double NOT NULL DEFAULT '0',  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query11_1);
			}
			String query11_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_assembly_item' ";
			ResultSet resultSet11_2 = statement1.executeQuery(query11_2);
			if(!resultSet11_2.next())
			{
				String query11_3="ALTER TABLE "+dbname1+".mr_assembly_item ADD INDEX  `trans_indx` (`itemcode`(100),barcode(100));";
				statement.executeUpdate(query11_3);
			}
			
			//12. mr_assign_account_group
			String query12="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_assign_account_group' ";
			ResultSet resultSet12 = statement1.executeQuery(query12);
			if(!resultSet12.next())
			{
				String query12_1="ALTER TABLE "+dbname1+".mr_assign_account_group ADD INDEX `trans_indx` (`acc_store`(100));";
				statement.executeUpdate(query12_1);
			}
			
			//13.	mr_assign_kot_printer
			String query13="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_assign_kot_printer' ; ";
			ResultSet resultSet13 = statement1.executeQuery(query13);
			if(!resultSet13.next())
			{
				String query13_1="CREATE TABLE  "+dbname1+".mr_assign_kot_printer (`id` int(255) NOT NULL AUTO_INCREMENT,  `reg_nme` text NOT NULL, `dept_code` text NOT NULL,  `dept_name` text NOT NULL,   `printer` text NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query13_1);
			}
			String query13_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_assign_kot_printer' ";
			ResultSet resultSet13_2 = statement1.executeQuery(query13_2);
			if(!resultSet13_2.next())
			{
				String query13_3="ALTER TABLE "+dbname1+".mr_assign_kot_printer ADD INDEX `trans_indx` (`reg_nme`(100),dept_name(100));";
				statement.executeUpdate(query13_3);
			}
			
			//14. mr_assign_register
			String query14="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_assign_register' ";
			ResultSet resultSet14 = statement1.executeQuery(query14);
			if(!resultSet14.next())
			{
				String query14_1="ALTER TABLE "+dbname1+".mr_assign_register ADD INDEX `trans_indx` (`ref_user_code`(100),`ref_register_num`(100));";
				statement.executeUpdate(query14_1);
			}
			
			//15.	mr_barcode_variable
			String query15="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_barcode_variable' ; ";
			ResultSet resultSet15 = statement1.executeQuery(query15);
			if(!resultSet15.next())
			{
				String query15_1="CREATE TABLE "+dbname1+".mr_barcode_variable (`id` int(255) NOT NULL AUTO_INCREMENT,`barcode_variable_for` text NOT NULL,`barcode_variable_list` text NOT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query15_1);
			}
			String query15_2="select * from "+dbname1+".mr_barcode_variable ";
			ResultSet resultSet15_2 = statement1.executeQuery(query15_2);
			if(!resultSet15_2.next())
			{
				String barcodevariable="INSERT INTO "+dbname1+".mr_barcode_variable VALUES (1,'qty','qu_ty'),(2,'item name','itm_code'),(3,'barcode','bar_code'),(4,'amount','am_tl'),(5,'decimal','decimal');";
				statement.executeUpdate(barcodevariable);
			}
			String query15_3="select * from "+dbname1+".mr_barcode_variable where barcode_variable_for='decimal'";
			ResultSet resultSet15_3 = statement1.executeQuery(query15_3);
			if(!resultSet15_3.next())
			{
				String barcodevariable="INSERT INTO "+dbname1+".mr_barcode_variable (barcode_variable_for,barcode_variable_list) VALUES ('decimal','decimal');";
				statement.executeUpdate(barcodevariable);
			}
			String query15_4="select * from "+dbname1+".mr_barcode_variable where barcode_variable_for='cf'";
			ResultSet resultSet15_4 = statement1.executeQuery(query15_4);
			if(!resultSet15_4.next())
			{
				String barcodevariable="INSERT INTO "+dbname1+".mr_barcode_variable (barcode_variable_for,barcode_variable_list) VALUES ('cf','cf');";
				statement.executeUpdate(barcodevariable);
			}
			String query15_5="select * from "+dbname1+".mr_barcode_variable where barcode_variable_for='itemqty'";
			ResultSet resultSet15_5 = statement1.executeQuery(query15_5);
			if(!resultSet15_5.next())
			{
				String barcodevariable="INSERT INTO "+dbname1+".mr_barcode_variable (barcode_variable_for,barcode_variable_list) VALUES ('itemqty','itemqty');";
				statement.executeUpdate(barcodevariable);
			}
			String query15_6="select * from "+dbname1+".mr_barcode_variable where barcode_variable_for='ttlitemqty'";
			ResultSet resultSet15_6 = statement1.executeQuery(query15_6);
			if(!resultSet15_6.next())
			{
				String barcodevariable="INSERT INTO "+dbname1+".mr_barcode_variable (barcode_variable_for,barcode_variable_list) VALUES ('ttlitemqty','ttlitemqty');";
				statement.executeUpdate(barcodevariable);
			}
			String query15_7="select * from "+dbname1+".mr_barcode_variable where barcode_variable_for='itm_desc1'";
			ResultSet resultSet15_7= statement1.executeQuery(query15_7);
			if(!resultSet15_7.next())
			{
				String barcodevariable="INSERT INTO "+dbname1+".mr_barcode_variable (barcode_variable_for,barcode_variable_list) VALUES ('itm_desc1','itm_desc1');";
				statement.executeUpdate(barcodevariable);
			}
			String query15_8="select * from "+dbname1+".mr_barcode_variable where barcode_variable_for='itm_desc2'";
			ResultSet resultSet15_8 = statement1.executeQuery(query15_8);
			if(!resultSet15_8.next())
			{
				String barcodevariable="INSERT INTO "+dbname1+".mr_barcode_variable (barcode_variable_for,barcode_variable_list) VALUES ('itm_desc2','itm_desc2');";
				statement.executeUpdate(barcodevariable);
			}
			
			//16. mr_category
			String query16="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_category'";
			ResultSet resultSet16 = statement1.executeQuery(query16);
			if(!resultSet16.next())
			{
				String query16_1="ALTER TABLE "+dbname1+".mr_category ADD INDEX `trans_indx` (`cat_name`(100));";
				statement.executeUpdate(query16_1);
			}
			
			//17. mr_chart_accnt_mstr
			String query17="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_chart_accnt_mstr'";
			ResultSet resultSet17 = statement1.executeQuery(query17);
			if(!resultSet17.next())
			{
				String query17_1="ALTER TABLE "+dbname1+".mr_chart_accnt_mstr ADD INDEX `trans_indx` (`acc_code`(100),acc_type(100),acc_grp_name(100),acc_category(100));";
				statement.executeUpdate(query17_1);
			}
			
			//18.	mr_cheque_details
			String query18="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_cheque_details' ; ";
			ResultSet resultSet18 = statement1.executeQuery(query18);
			if(!resultSet18.next())
			{
				String query18_1="CREATE TABLE  "+dbname1+".mr_cheque_details (  `id` int(255) NOT NULL AUTO_INCREMENT,  `tran_num` text NOT NULL,  `pay_by` text NOT NULL, `bank_name` TEXT NOT NULL,`status` TEXT NOT NULL, `pay_id` TEXT NOT NULL,  `cr` double NOT NULL,  `dr` double NOT NULL,  `issue_date` date NOT NULL,  `cheque_no` text NOT NULL,  `cheque_date` date NOT NULL,  `cheque_printed` int(11) NOT NULL DEFAULT '0',  `printing_user` text NOT NULL,  `cheque_handover` int(11) NOT NULL DEFAULT '0',  `handover_user` text NOT NULL,  `cheque_posted` int(11) NOT NULL DEFAULT '0',  `posted_user` text NOT NULL,  `remark` text NOT NULL,  `note` text NOT NULL, page_type  TEXT NOT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query18_1);
			}
			String query18_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_cheque_details' ";
			ResultSet resultSet18_2 = statement1.executeQuery(query18_2);
			if(!resultSet18_2.next())
			{
				String query18_3="ALTER TABLE "+dbname1+".mr_cheque_details ADD INDEX  `trans_indx` (`tran_num`(100),bank_name(100));";
				statement.executeUpdate(query18_3);
			}
			String query18_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_cheque_details' and column_name='page_type'; ";
			ResultSet resultSet18_4 = statement1.executeQuery(query18_4);
			if(!resultSet18_4.next())
			{
				String query18_5="ALTER TABLE "+dbname1+".mr_cheque_details ADD COLUMN  `page_type`  TEXT NOT NULL; ";
				statement.executeUpdate(query18_5);
			}
			
			//19.	mr_close_pay_list
			String query19="ALTER TABLE "+dbname1+".mr_close_pay_list CHANGE `date_time` `date_time` datetime ";
			statement.executeUpdate(query19);
			String query19_1="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_close_pay_list' ";
			ResultSet resultSet19_1 = statement1.executeQuery(query19_1);
			if(!resultSet19_1.next())
			{
				String query19_2="ALTER TABLE "+dbname1+".mr_close_pay_list  ADD INDEX  `trans_indx` (`ref_reg_num`(100));";
				statement.executeUpdate(query19_2);
			}
			
			//20.	mr_close_pay_list_history
			String query20="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_close_pay_list_history' and column_name='close_date'; ";
			ResultSet resultSet20 = statement1.executeQuery(query20);
			if(!resultSet20.next())
			{
				String query20_1="ALTER TABLE "+dbname1+".mr_close_pay_list_history ADD COLUMN  `close_date` date NOT NULL ;";
				statement.executeUpdate(query20_1);
				String barcodevariable="Update "+dbname1+".mr_close_pay_list_history set close_date=created_date ";
				statement.executeUpdate(barcodevariable);
			}
			String query20_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_close_pay_list_history' ";
			ResultSet resultSet20_2 = statement1.executeQuery(query20_2);
			if(!resultSet20_2.next())
			{
				String query20_3="ALTER TABLE "+dbname1+".mr_close_pay_list_history  ADD INDEX  `trans_indx` (`ref_reg_num`(100), `invoice_num`(100));";
				statement.executeUpdate(query20_3);
			}
			
			//21.	mr_close_pay_list_history_items
			String query21="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_close_pay_list_history_items'";
			ResultSet resultSet21 = statement1.executeQuery(query21);
			if(!resultSet21.next())
			{
				String query21_1="ALTER TABLE "+dbname1+".mr_close_pay_list_history_items  ADD INDEX  `trans_indx` (`ref_reg_num`(100),paycode(100));";
				statement.executeUpdate(query21_1);
			}
			String query21_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_close_pay_list_history_items' and column_name='jv_num'; ";
			ResultSet resultSet21_4 = statement1.executeQuery(query21_4);
			if(!resultSet21_4.next())
			{
				String query18_5="ALTER TABLE "+dbname1+".mr_close_pay_list_history_items ADD COLUMN  `jv_num`  TEXT  NULL; ";
				statement.executeUpdate(query18_5);
			}
			
			//22.	mr_close_pay_list_items
			String query22="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_close_pay_list_items'";
			ResultSet resultSet22 = statement1.executeQuery(query22);
			if(!resultSet22.next())
			{
				String query22_1="ALTER TABLE "+dbname1+".mr_close_pay_list_items  ADD INDEX  `trans_indx` (`ref_reg_num`(100),paycode(100));";
				statement.executeUpdate(query22_1);
			}
			
			//23.	mr_customer_items
			String query23="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_customer_items' ; ";
			ResultSet resultSet23 = statement1.executeQuery(query23);
			if(!resultSet23.next())
			{
				String query23_1="CREATE TABLE  "+dbname1+".mr_customer_items (`id` int(255) NOT NULL AUTO_INCREMENT,  `ref_cust_id` text NOT NULL,  `ref_itemcode` text NOT NULL,  `price` double NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `status` int(11) NOT NULL DEFAULT '0',  `unit` text NOT NULL,  `barcode` text NOT NULL,  `invoice_num` text,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query23_1);
			}
			String query23_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_customer_items' and column_name='invoice_num'; ";
			ResultSet resultSet23_2 = statement1.executeQuery(query23_2);
			if(!resultSet23_2.next())
			{
				String query23_3="ALTER TABLE "+dbname1+".mr_customer_items ADD COLUMN  `invoice_num`  TEXT NULL  ;";
				statement.executeUpdate(query23_3);
			}
			String query23_4="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_customer_items' ";
			ResultSet resultSet23_4 = statement1.executeQuery(query23_4);
			if(!resultSet23_4.next())
			{
				String query18_3="ALTER TABLE "+dbname1+".mr_customer_items  ADD INDEX  `trans_indx` (`ref_cust_id`(100), `ref_itemcode`(100));";
				statement.executeUpdate(query18_3);
			}
			
			//24.	mr_customer_master
			String query24="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_customer_master' and column_name='open_bal'; ";
			ResultSet resultSet24 = statement1.executeQuery(query24);
			if(!resultSet24.next())
			{
				String query24_1="ALTER TABLE "+dbname1+".mr_customer_master ADD COLUMN  `open_bal` DOUBLE NOT NULL DEFAULT '0' ;";
				statement.executeUpdate(query24_1);
			}
			String query24_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_customer_master' and column_name='open_bal_status'; ";
			ResultSet resultSet24_2 = statement1.executeQuery(query24_2);
			if(!resultSet24_2.next())
			{
				String query24_3="ALTER TABLE "+dbname1+".mr_customer_master ADD COLUMN  `open_bal_status` INT(11) NOT NULL DEFAULT '0'  ;";
				statement.executeUpdate(query24_3);
			}
			String query24_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_customer_master' and column_name='contact_person'; ";
			ResultSet resultSet24_4 = statement1.executeQuery(query24_4);
			if(!resultSet24_4.next())
			{
				String query24_5="ALTER TABLE "+dbname1+".mr_customer_master ADD COLUMN  `contact_person` TEXT  NULL;";
				statement.executeUpdate(query24_5);
			}
			String query24_6="update "+dbname1+".mr_customer_master set status='1',visible='1' where cust_code='WALK IN' ";
			statement.executeUpdate(query24_6);
			
			String query24_7="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_customer_master' ";
			ResultSet resultSet24_7 = statement1.executeQuery(query24_7);
			if(!resultSet24_7.next())
			{
				String query24_8="ALTER TABLE "+dbname1+".mr_customer_master  ADD INDEX  `trans_indx` (`cust_code`(100), `cust_name`(100));";
				statement.executeUpdate(query24_8);
			}
			
			//25.	mr_customer_payment
			String query25="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_customer_payment' ";
			ResultSet resultSet25 = statement1.executeQuery(query25);
			if(!resultSet25.next())
			{
				String query25_1="ALTER TABLE "+dbname1+".mr_customer_payment  ADD INDEX  `trans_indx` (`seq_num`(100), `cust_code`(100), `paycode`(100), `ref_reg_num`(100));";
				statement.executeUpdate(query25_1);
			}
			
			//26.	mr_delivery_list
			String query26="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_delivery_list' ; ";
			ResultSet resultSet26 = statement1.executeQuery(query26);
			if(!resultSet26.next())
			{
				String query26_1="CREATE TABLE  "+dbname1+".mr_delivery_list (`id` int(255) NOT NULL AUTO_INCREMENT,  `date` date NOT NULL,  `user_id` text NOT NULL,  `invoice_num` text NOT NULL,  `do_num` text NOT NULL,  `remark` text NOT NULL,  `cust_id` text NOT NULL,  `cust_addr` text NOT NULL,  `status` text NOT NULL,  `referance` text NOT NULL,  `store_name` text NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `cust_name` text NOT NULL, `location` text NOT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query26_1);
			}
			String query26_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_delivery_list' ";
			ResultSet resultSet26_2 = statement1.executeQuery(query26_2);
			if(!resultSet26_2.next())
			{
				String query26_3="ALTER TABLE "+dbname1+".mr_delivery_list ADD INDEX  `trans_indx` (`invoice_num`(100),`do_num`(100));";
				statement.executeUpdate(query26_3);
			}
			
			//27.	mr_delivery_list_history
			String query27="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_delivery_list_history' ; ";
			ResultSet resultSet27 = statement1.executeQuery(query27);
			if(!resultSet27.next())
			{
				String query27_1="CREATE TABLE  "+dbname1+".mr_delivery_list_history (`id` int(255) NOT NULL AUTO_INCREMENT,  `date` date NOT NULL,  `user_id` text NOT NULL,  `invoice_num` text NOT NULL,  `do_num` text NOT NULL,  `remark` text NOT NULL,  `cust_id` text NOT NULL,  `cust_addr` text NOT NULL,  `status` text NOT NULL,  `referance` text NOT NULL,  `store_name` text NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `cust_name` text NOT NULL,`location` text NOT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query27_1);
			}
			String query27_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_delivery_list_history' ";
			ResultSet resultSet27_2 = statement1.executeQuery(query27_2);
			if(!resultSet27_2.next())
			{
				String query27_3="ALTER TABLE "+dbname1+".mr_delivery_list_history ADD INDEX  `trans_indx` (`invoice_num`(100),`do_num`(100));";
				statement.executeUpdate(query27_3);
			}
			
			//28.	mr_delivery_note
			String query28="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_delivery_note' ; ";
			ResultSet resultSet28= statement1.executeQuery(query28);
			if(!resultSet28.next())
			{
				String query28_1="CREATE TABLE  "+dbname1+".mr_delivery_note (`id` int(255) NOT NULL AUTO_INCREMENT,  `ref_inv_num` text NOT NULL,  `ref_do_num` text NOT NULL,  `itemcode` text NOT NULL,  `barcode` text NOT NULL,  `item_name` text NOT NULL,  `unit` text NOT NULL,  `cf` text NOT NULL,  `invoice_qty` double NOT NULL,  `delivered_qty` double NOT NULL,  `balance_qty` double NOT NULL,  `previous_delivery` double NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query28_1);
			}
			String query28_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_delivery_note' ";
			ResultSet resultSet28_2 = statement1.executeQuery(query28_2);
			if(!resultSet28_2.next())
			{
				String query28_3="ALTER TABLE "+dbname1+".mr_delivery_note ADD INDEX  `trans_indx` (`ref_inv_num`(100),`ref_do_num`(100),itemcode(100));";
				statement.executeUpdate(query28_3);
			}
			
			//29.	mr_delivery_note_history
			String query29="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_delivery_note_history' ; ";
			ResultSet resultSet29= statement1.executeQuery(query29);
			if(!resultSet29.next())
			{
				String query29_1="CREATE TABLE  "+dbname1+".mr_delivery_note_history (`id` int(255) NOT NULL AUTO_INCREMENT,  `ref_inv_num` text NOT NULL,  `ref_do_num` text NOT NULL,  `itemcode` text NOT NULL,  `barcode` text NOT NULL,  `item_name` text NOT NULL,  `unit` text NOT NULL,  `cf` text NOT NULL,  `invoice_qty` double NOT NULL,  `delivered_qty` double NOT NULL,  `balance_qty` double NOT NULL,  `previous_delivery` double NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query29_1);
			}
			String query29_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_delivery_note_history' ";
			ResultSet resultSet29_2 = statement1.executeQuery(query29_2);
			if(!resultSet29_2.next())
			{
				String query29_3="ALTER TABLE "+dbname1+".mr_delivery_note_history ADD INDEX  `trans_indx` (`ref_inv_num`(100),`ref_do_num`(100),itemcode(100));";
				statement.executeUpdate(query29_3);
			}
			
			//30.	mr_denomination
			String query30="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_denomination' ; ";
			ResultSet resultSet30= statement1.executeQuery(query30);
			if(!resultSet30.next())
			{
				String query30_1="CREATE TABLE  "+dbname1+".mr_denomination (`id` int(255) NOT NULL AUTO_INCREMENT,  `ref_reg_num` text NOT NULL,  `denomination` text NOT NULL,  `count` INT(11) NOT NULL DEFAULT '0',  `total_amt` double NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,`status` INT(11) NOT NULL DEFAULT '0',  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query30_1);
			}
			String query30_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_denomination' ";
			ResultSet resultSet30_2 = statement1.executeQuery(query30_2);
			if(!resultSet30_2.next())
			{
				String query30_3="ALTER TABLE "+dbname1+".mr_denomination ADD INDEX  `trans_indx` (`ref_reg_num`(100),`denomination`(100));";
				statement.executeUpdate(query30_3);
			}
			
			//31.	mr_department
			String query31="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_department' ";
			ResultSet resultSet31 = statement1.executeQuery(query31);
			if(!resultSet31.next())
			{
				String query31_1="ALTER TABLE "+dbname1+".mr_department ADD INDEX  `trans_indx` (`dept_name`(100));";
				statement.executeUpdate(query31_1);
			}
			
			String query31_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_department' and column_name='color_code'; ";
			ResultSet resultSet31_2 = statement1.executeQuery(query31_2);
			if(!resultSet31_2.next())
			{
				String query31_3="ALTER TABLE "+dbname1+".mr_department ADD COLUMN  `color_code`  TEXT NULL  ;";
				statement.executeUpdate(query31_3);
				String query31_4="update "+dbname1+".mr_department set `color_code` ='#000000'  ;";
				statement.executeUpdate(query31_4);
			}
			
			String query31_5="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_department' and column_name='fore_color_code'; ";
			ResultSet resultSet31_5 = statement1.executeQuery(query31_5);
			if(!resultSet31_5.next())
			{
				String query31_6="ALTER TABLE "+dbname1+".mr_department ADD COLUMN  `fore_color_code`  TEXT NULL  ;";
				statement.executeUpdate(query31_6);
				String query31_7="update "+dbname1+".mr_department set `fore_color_code` ='#ffffff'  ;";
				statement.executeUpdate(query31_7);
			}
			
			//32.	mr_inventory_config
			String query32="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_inventory_config' ";
			ResultSet resultSet32 = statement1.executeQuery(query32);
			if(!resultSet32.next())
			{
				String query32_1="ALTER TABLE "+dbname1+".mr_inventory_config ADD INDEX  `trans_indx` (`store_code`(100),`store_name`(100));";
				statement.executeUpdate(query32_1);
			}
			
			//33.	mr_inv_trans
			String query33="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_inv_trans' and column_name='total'; ";
			ResultSet resultSet33 = statement1.executeQuery(query33);
			if(resultSet33.next())
			{
				String query33_1="ALTER TABLE "+dbname1+".mr_inv_trans DROP COLUMN `total`  ;";
				statement.executeUpdate(query33_1);
			}
			String query33_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_inv_trans' and column_name='item_name'; ";
			ResultSet resultSet33_2 = statement1.executeQuery(query33_2);
			if(!resultSet33_2.next())
			{
				String query33_3="ALTER TABLE "+dbname1+".mr_inv_trans ADD COLUMN  `item_name`  TEXT NULL  ;";
				statement.executeUpdate(query33_3);
			}
			
			String query33_4="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_inv_trans' ";
			ResultSet resultSet33_4 = statement1.executeQuery(query33_4);
			if(!resultSet33_4.next())
			{
				String query33_5="ALTER TABLE "+dbname1+".mr_inv_trans  ADD INDEX  `trans_indx` (`ref_adj_num`(100), `item_code`(100), `barcode`(100));";
				statement.executeUpdate(query33_5);
			}
			
			//34.	mr_inv_trans_history
			String query34="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_inv_trans_history' ; ";
			ResultSet resultSet34= statement1.executeQuery(query34);
			if(!resultSet34.next())
			{
				String query34_1="CREATE TABLE  "+dbname1+".mr_inv_trans_history (`id` int(255) NOT NULL AUTO_INCREMENT,`ref_adj_num` text NOT NULL,`barcode` text NOT NULL,`item_code` text NOT NULL,`qtyOh` float NOT NULL,`unit` text NOT NULL,`cf` float NOT NULL, `unit_cost` float NOT NULL,`qty` float NOT NULL,`created_date` date NOT NULL,`modified_date` date NOT NULL,`item_name` text NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query34_1);
			}
			String query34_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_inv_trans_history' ";
			ResultSet resultSet34_2 = statement1.executeQuery(query34_2);
			if(!resultSet34_2.next())
			{
				String query34_3="ALTER TABLE "+dbname1+".mr_inv_trans_history  ADD INDEX  `trans_indx` (`ref_adj_num`(100), `item_code`(100), `barcode`(100));";
				statement.executeUpdate(query34_3);
			}
			
			//35.	mr_inv_trans_list
			String query35="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_inv_trans_list' and column_name='vendor_id'; ";
			ResultSet resultSet35 = statement1.executeQuery(query35);
			if(resultSet35.next())
			{
				String query35_1="ALTER TABLE "+dbname1+".mr_inv_trans_list DROP COLUMN `vendor_id`;";
				statement.executeUpdate(query35_1);
			}
			String query35_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_inv_trans_list' and column_name='promt_print'; ";
			ResultSet resultSet35_2 = statement1.executeQuery(query35_2);
			if(resultSet35_2.next())
			{
				String query35_3="ALTER TABLE "+dbname1+".mr_inv_trans_list DROP COLUMN  `promt_print`  ;";
				statement.executeUpdate(query35_3);
			}
			String query35_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_inv_trans_list' and column_name='remark'; ";
			ResultSet resultSet35_4 = statement1.executeQuery(query35_4);
			if(!resultSet35_4.next())
			{
				String query35_5="ALTER TABLE "+dbname1+".mr_inv_trans_list ADD COLUMN  `remark` TEXT NULL ;";
				statement.executeUpdate(query35_5);
			}
			
			String query35_6="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_inv_trans_list' ";
			ResultSet resultSet35_6 = statement1.executeQuery(query35_6);
			if(!resultSet35_6.next())
			{
				String query35_7="ALTER TABLE "+dbname1+".mr_inv_trans_list  ADD INDEX  `trans_indx` (`adj_no`(100), `invo_nu`(100));";
				statement.executeUpdate(query35_7);
			}
			
			//36.	mr_inv_trans_history
			String query36="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_inv_trans_list_history' ; ";
			ResultSet resultSet36= statement1.executeQuery(query36);
			if(!resultSet36.next())
			{
				String query36_1="CREATE TABLE  "+dbname1+".mr_inv_trans_list_history (`id` int(255) NOT NULL AUTO_INCREMENT,`com_date` date NOT NULL,`adj_date` date NOT NULL,`user_id` text NOT NULL,  `adj_no` text NOT NULL,  `invo_nu` text NOT NULL,  `ttl_items` int(11) NOT NULL,  `ttl_price` float NOT NULL,  `sub_ttl_amt` float NOT NULL,  `assume_ttl_cst` int(11) NOT NULL DEFAULT '0',  `status` text NOT NULL,  `store_name` text NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `remark` text NOT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query36_1);
			}
			String query36_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_inv_trans_list_history' ";
			ResultSet resultSet36_2 = statement1.executeQuery(query36_2);
			if(!resultSet36_2.next())
			{
				String query36_3="ALTER TABLE "+dbname1+".mr_inv_trans_list_history  ADD INDEX  `trans_indx` (`adj_no`(100), `invo_nu`(100));";
				statement.executeUpdate(query36_3);
			}
			
			//37.	mr_item_master
			String query37="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_item_master' and column_name='input_tax_rate'; ";
			ResultSet resultSet37 = statement1.executeQuery(query37);
			if(!resultSet37.next())
			{
				String query37_1="ALTER TABLE "+dbname1+".mr_item_master ADD COLUMN  `input_tax_rate` INT(11) NOT NULL DEFAULT '0'  ;";
				statement.executeUpdate(query37_1);
			}
			
			String query37_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_item_master' and column_name='output_tax_rate'; ";
			ResultSet resultSet37_2 = statement1.executeQuery(query37_2);
			if(!resultSet37_2.next())
			{
				String query37_3="ALTER TABLE "+dbname1+".mr_item_master ADD COLUMN  `output_tax_rate` INT(11) NOT NULL DEFAULT '0'  ;";
				statement.executeUpdate(query37_3);
			}
			String query37_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_item_master' and column_name='commited_qty'; ";
			ResultSet resultSet37_4 = statement1.executeQuery(query37_4);
			if(!resultSet37_4.next())
			{
				String query37_5="ALTER TABLE "+dbname1+".mr_item_master ADD COLUMN  `commited_qty` DOUBLE NOT NULL DEFAULT '0';";
				statement.executeUpdate(query37_5);
			}
			
			String query37_6="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_item_master' ";
			ResultSet resultSet37_6 = statement1.executeQuery(query37_6);
			if(!resultSet37_6.next())
			{
				String query37_7="ALTER TABLE "+dbname1+".mr_item_master  ADD INDEX  `trans_indx` (`item_name`(100), `item_code`(100),item_type(100));";
				statement.executeUpdate(query37_7);
			}
			
			String query37_8="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_item_master' and column_name='vendor_code'; ";
			ResultSet resultSet37_8 = statement1.executeQuery(query37_8);
			if(!resultSet37_8.next())
			{
				String query37_9="ALTER TABLE "+dbname1+".mr_item_master ADD COLUMN  `vendor_code` TEXT NULL  ;";
				statement.executeUpdate(query37_9);
				String query37_11="update "+dbname1+".mr_item_master set `vendor_code`='NIL' ;";
				statement.executeUpdate(query37_11);
			}
			
			String query37_9="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_item_master' and column_name='vendor_name'; ";
			ResultSet resultSet37_9 = statement1.executeQuery(query37_9);
			if(!resultSet37_9.next())
			{
				String query37_10="ALTER TABLE "+dbname1+".mr_item_master ADD COLUMN  `vendor_name` TEXT NULL  ;";
				statement.executeUpdate(query37_10);
				String query37_12="update "+dbname1+".mr_item_master set `vendor_name`='NIL' ;";
				statement.executeUpdate(query37_12);
			}
			
			String query37_10="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_item_master' and column_name='fast_item'; ";
			ResultSet resultSet37_10 = statement1.executeQuery(query37_10);
			if(!resultSet37_10.next())
			{
				String query37_11="ALTER TABLE "+dbname1+".mr_item_master ADD COLUMN  `fast_item` INT(11) NOT NULL DEFAULT '0' ;";
				statement.executeUpdate(query37_11);
			}
			String query37_13="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_item_master' and column_name='Open_Item'; ";
			ResultSet resultSet37_13 = statement1.executeQuery(query37_13);
			if(!resultSet37_13.next())
			{
				String query37_14="ALTER TABLE "+dbname1+".mr_item_master ADD COLUMN  `Open_Item` INT(11) NOT NULL DEFAULT '0' ;";
				statement.executeUpdate(query37_14);
			}
			String query37_15="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_item_master' and column_name='color_code'; ";
			ResultSet resultSet37_15 = statement1.executeQuery(query37_15);
			if(!resultSet37_15.next())
			{
				String query37_16="ALTER TABLE "+dbname1+".mr_item_master ADD COLUMN  `color_code`  TEXT NULL  ;";
				statement.executeUpdate(query37_16);
				String query37_17="update "+dbname1+".mr_item_master set `color_code` ='#000000'  ;";
				statement.executeUpdate(query37_17);
			}
			
			String query37_18="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_item_master' and column_name='fore_color_code'; ";
			ResultSet resultSet37_18 = statement1.executeQuery(query37_18);
			if(!resultSet37_18.next())
			{
				String query37_19="ALTER TABLE "+dbname1+".mr_item_master ADD COLUMN  `fore_color_code`  TEXT NULL  ;";
				statement.executeUpdate(query37_19);
				String query37_20="update "+dbname1+".mr_item_master set `fore_color_code` ='#ffffff'  ;";
				statement.executeUpdate(query37_20);
			}
			String query37_21="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_item_master' and column_name='item_vareint'; ";
			ResultSet resultSet37_21 = statement1.executeQuery(query37_21);
			if(!resultSet37_21.next())
			{
				String query37_22="ALTER TABLE "+dbname1+".mr_item_master ADD COLUMN  `item_vareint`  INT NOT NULL  ;";
				statement.executeUpdate(query37_22);
			}
			
			String query37_23="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_item_master' and column_name='openitem_qty'; ";
			ResultSet resultSet37_23 = statement1.executeQuery(query37_23);
			if(!resultSet37_23.next())
			{
				String query37_24="ALTER TABLE "+dbname1+".mr_item_master ADD COLUMN  `openitem_qty` INT(11) NOT NULL DEFAULT '0'  ;";
				statement.executeUpdate(query37_24);
			}
			
			//38.	mr_item_master_history
			String query38="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_item_master_history' and column_name='barcode'; ";
			ResultSet resultSet38 = statement1.executeQuery(query38);
			if(!resultSet38.next())
			{
				String query38_1="ALTER TABLE "+dbname1+".mr_item_master_history ADD COLUMN  `barcode`  TEXT NULL  ;";
				statement.executeUpdate(query38_1);
			}
			String query38_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_item_master_history' and column_name='cust_id'; ";
			ResultSet resultSet38_2 = statement1.executeQuery(query38_2);
			if(!resultSet38_2.next())
			{
				String query38_3="ALTER TABLE "+dbname1+".mr_item_master_history ADD COLUMN  `cust_id`  TEXT NULL  ;";
				statement.executeUpdate(query38_3);
			}
			String query38_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_item_master_history' and column_name='cust_name'; ";
			ResultSet resultSet38_4 = statement1.executeQuery(query38_4);
			if(!resultSet38_4.next())
			{
				String query38_5="ALTER TABLE "+dbname1+".mr_item_master_history ADD COLUMN  `cust_name`  TEXT NULL  ;";
				statement.executeUpdate(query38_5);
			}
			
			String query38_6="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_item_master_history' ";
			ResultSet resultSet38_6 = statement1.executeQuery(query38_6);
			if(!resultSet38_6.next())
			{
				String query38_7="ALTER TABLE "+dbname1+".mr_item_master_history  ADD INDEX  `trans_indx` (`itemcode`(100),trans_type(100),trans_code(100),barcode(100));";
				statement.executeUpdate(query38_7);
			}
			
			//39.	mr_item_open_qty
			String query39="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_item_open_qty' ";
			ResultSet resultSet39 = statement1.executeQuery(query39);
			if(!resultSet39.next())
			{
				String query39_1="ALTER TABLE "+dbname1+".mr_item_open_qty  ADD INDEX  `trans_indx` (`item_code`(100),item_name(100));";
				statement.executeUpdate(query39_1);
			}
			
			//40.	mr_jv_details
			String query40="ALTER TABLE "+dbname1+".mr_jv_details CHANGE `dr_amt` `dr_amt` DOUBLE NOT NULL;";
			statement.executeUpdate(query40);
			String query40_1="ALTER TABLE "+dbname1+".mr_jv_details CHANGE `cr_amt` `cr_amt` DOUBLE NOT NULL;";
			statement.executeUpdate(query40_1);
			
			String query40_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_jv_details' and column_name='jv_seq'; ";
			ResultSet resultSet40_2 = statement1.executeQuery(query40_2);
			if(!resultSet40_2.next())
			{
				String query40_3="ALTER TABLE  "+dbname1+".mr_jv_details ADD COLUMN  `jv_seq` TEXT NOT NULL AFTER `ref_jv_num`;";
				statement.executeUpdate(query40_3);
			}
			String query40_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_jv_details' and column_name='status'; ";
			ResultSet resultSet40_4 = statement1.executeQuery(query40_4);
			if(!resultSet40_4.next())
			{
				String query40_5="ALTER TABLE  "+dbname1+".mr_jv_details ADD COLUMN  `status` VARCHAR(10) NULL DEFAULT 'OPEN' ;";
				statement.executeUpdate(query40_5);
			}
			String query40_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_jv_details' and column_name='bank_name'; ";
			ResultSet resultSet40_6 = statement1.executeQuery(query40_6);
			if(!resultSet40_6.next())
			{
				String query40_7="ALTER TABLE "+dbname1+".mr_jv_details ADD COLUMN  `bank_name` TEXT NULL ;";
				statement.executeUpdate(query40_7);
			}
			String query40_8="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_jv_details' and column_name='paid_by'; ";
			ResultSet resultSet40_8 = statement1.executeQuery(query40_8);
			if(!resultSet40_8.next())
			{
				String query40_9="ALTER TABLE "+dbname1+".mr_jv_details ADD COLUMN  `paid_by` TEXT NULL  ;";
				statement.executeUpdate(query40_9);
			}
			String query40_10="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_jv_details' and column_name='cheque_no'; ";
			ResultSet resultSet40_10 = statement1.executeQuery(query40_10);
			if(!resultSet40_10.next())
			{
				String query40_11="ALTER TABLE "+dbname1+".mr_jv_details ADD COLUMN  `cheque_no` TEXT NULL  ;";
				statement.executeUpdate(query40_11);
			}
			String query40_12="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_jv_details' and column_name='cheque_date'; ";
			ResultSet resultSet40_12 = statement1.executeQuery(query40_12);
			if(!resultSet40_12.next())
			{
				String query40_13="ALTER TABLE "+dbname1+".mr_jv_details ADD COLUMN  `cheque_date` DATE NOT NULL ;";
				statement.executeUpdate(query40_13);
			}
			
			String query40_14="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_jv_details' ";
			ResultSet resultSet40_14 = statement1.executeQuery(query40_14);
			if(!resultSet40_14.next())
			{
				String query40_15="ALTER TABLE "+dbname1+".mr_jv_details  ADD INDEX  `trans_indx` (`ref_jv_num`(100),ac_code(100),ac_desc(100));";
				statement.executeUpdate(query40_15);
			}
			
			//41.	mr_jv_header
			String query41="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_jv_header' ";
			ResultSet resultSet41 = statement1.executeQuery(query41);
			if(!resultSet41.next())
			{
				String query41_1="ALTER TABLE "+dbname1+".mr_jv_header  ADD INDEX  `trans_indx` (`jv_num`(100),doc_num(100));";
				statement.executeUpdate(query41_1);
			}
			
			//42.	mr_kit_items
			String query42="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_kit_items' ; ";
			ResultSet resultSet42= statement1.executeQuery(query42);
			if(!resultSet42.next())
			{
				String query42_1="CREATE TABLE  "+dbname1+".mr_kit_items (`id` int(255) NOT NULL AUTO_INCREMENT,  `parent_itemcode` text NOT NULL,  `parent_itemname` text NOT NULL,  `child_itemcode` text NOT NULL,  `child_itemname` text NOT NULL,  `child_barcode` text NOT NULL,  `sp` double NOT NULL,  `cost` double NOT NULL,  `qty` double NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query42_1);
			}
			String query42_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_kit_items' ";
			ResultSet resultSet42_2 = statement1.executeQuery(query42_2);
			if(!resultSet42_2.next())
			{
				String query42_3="ALTER TABLE "+dbname1+".mr_kit_items  ADD INDEX  `trans_indx` (`parent_itemcode`(100),child_itemcode(100),child_barcode(100));";
				statement.executeUpdate(query42_3);
			}
			
			//43.	mr_kot_tables
			String query43="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_kot_tables' ; ";
			ResultSet resultSet43= statement1.executeQuery(query43);
			if(!resultSet43.next())
			{
				String query43_1="CREATE TABLE  "+dbname1+".mr_kot_tables (`id` int(255) NOT NULL AUTO_INCREMENT,  `store_code` text NOT NULL,  `table_num` text NOT NULL,  `chair_count` INT(11) NOT NULL DEFAULT '1',  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query43_1);
			}
			String query43_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_kot_tables' ";
			ResultSet resultSet43_2 = statement1.executeQuery(query43_2);
			if(!resultSet43_2.next())
			{
				String query43_3="ALTER TABLE "+dbname1+".mr_kot_tables  ADD INDEX  `trans_indx` (`store_code`(100),table_num(100));";
				statement.executeUpdate(query43_3);
			}
			
			//44.	mr_loggin_controls
			String query44="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_loggin_controls' ";
			ResultSet resultSet44 = statement1.executeQuery(query44);
			if(!resultSet44.next())
			{
				String query44_1="ALTER TABLE "+dbname1+".mr_loggin_controls  ADD INDEX  `trans_indx` (`username`(100),`device_name`(100));";
				statement.executeUpdate(query44_1);
			}
			
			String query44_2="SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '"+dbname1+"' and TABLE_NAME='mr_loggin_controls' and column_name='system'";
			ResultSet resultSet44_2 = statement1.executeQuery(query44_2);
			if(resultSet44_2.next())
			{
				String query44_3="ALTER TABLE "+dbname1+".mr_loggin_controls DROP COLUMN system; ";
				statement.executeUpdate(query44_3);
			}
			String query44_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_loggin_controls' and column_name='device_name';";
			ResultSet resultSet44_4 = statement1.executeQuery(query44_4);
			if(!resultSet44_4.next())
			{
				String query44_5="ALTER TABLE "+dbname1+".mr_loggin_controls ADD COLUMN  `device_name` TEXT NULL;";
				statement.executeUpdate(query44_5);
			}
			
			//45.	mr_paycode_settlement
			String query45="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_paycode_settlement' and column_name='pay_code_vat'; ";
			ResultSet resultSet45 = statement1.executeQuery(query45);
			if(!resultSet45.next())
			{
				String query45_1="ALTER TABLE "+dbname1+".mr_paycode_settlement ADD COLUMN  `pay_code_vat` DOUBLE NOT NULL DEFAULT '0';";
				statement.executeUpdate(query45_1);
			}
			String query45_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_paycode_settlement' and column_name='status'; ";
			ResultSet resultSet45_2 = statement1.executeQuery(query45_2);
			if(!resultSet45_2.next())
			{
				String query45_3="ALTER TABLE "+dbname1+".mr_paycode_settlement ADD COLUMN  `status` INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query45_3);
			}
			
			String query45_4="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_paycode_settlement' ";
			ResultSet resultSet45_4 = statement1.executeQuery(query45_4);
			if(!resultSet45_4.next())
			{
				String query45_5="ALTER TABLE "+dbname1+".mr_paycode_settlement  ADD INDEX  `trans_indx` (`ref_reg_num`(100), `ref_receipt_num`(100),paycode_name(100));";
				statement.executeUpdate(query45_5);
			}
			
			//46.	mr_paycode_settlement_history
			String query46="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_paycode_settlement_history' and column_name='pay_code_vat'; ";
			ResultSet resultSet46 = statement1.executeQuery(query46);
			if(!resultSet46.next())
			{
				String query46_1="ALTER TABLE "+dbname1+".mr_paycode_settlement_history ADD COLUMN  `pay_code_vat` DOUBLE NOT NULL DEFAULT '0';";
				statement.executeUpdate(query46_1);
			}
			String query46_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_paycode_settlement_history' ";
			ResultSet resultSet46_2 = statement1.executeQuery(query46_2);
			if(!resultSet46_2.next())
			{
				String query46_3="ALTER TABLE "+dbname1+".mr_paycode_settlement_history  ADD INDEX  `trans_indx` (`ref_reg_num`(100), `ref_receipt_num`(100),paycode_name(100));";
				statement.executeUpdate(query46_3);
			}
			
			//47.	mr_paylist
			String query47="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_paylist' ";
			ResultSet resultSet47 = statement1.executeQuery(query47);
			if(!resultSet47.next())
			{
				String query47_1="ALTER TABLE "+dbname1+".mr_paylist  ADD INDEX  `trans_indx` (`ref_reg_no`(100), `type`(100));";
				statement.executeUpdate(query47_1);
			}
			
			//48.	mr_pos_hold_invoice
			
			String query48="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice' and column_name='cust_code'; ";
			ResultSet resultSet48 = statement1.executeQuery(query48);
			if(!resultSet48.next())
			{
				String query48_1="ALTER TABLE "+dbname1+".mr_pos_hold_invoice ADD COLUMN  `cust_code` TEXT NOT NULL;";
				statement.executeUpdate(query48_1);
			}
			String query48_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice' and column_name='mode'; ";
			ResultSet resultSet48_2 = statement1.executeQuery(query48_2);
			if(!resultSet48_2.next())
			{
				String query48_3="ALTER TABLE "+dbname1+".mr_pos_hold_invoice ADD COLUMN  `mode`  TEXT  NULL;";
				statement.executeUpdate(query48_3);
			}
			String query48_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice' and column_name='table_num'; ";
			ResultSet resultSet48_4 = statement1.executeQuery(query48_4);
			if(!resultSet48_4.next())
			{
				String query48_5="ALTER TABLE "+dbname1+".mr_pos_hold_invoice ADD COLUMN  `table_num`  TEXT  NULL;";
				statement.executeUpdate(query48_5);
			}
			String query48_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice' and column_name='delivery_user'; ";
			ResultSet resultSet48_6 = statement1.executeQuery(query48_6);
			if(!resultSet48_6.next())
			{
				String query48_7="ALTER TABLE "+dbname1+".mr_pos_hold_invoice ADD COLUMN  `delivery_user`  TEXT NOT  NULL;";
				statement.executeUpdate(query48_7);
			}
			
			String query48_8="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_pos_hold_invoice' ";
			ResultSet resultSet48_8 = statement1.executeQuery(query48_8);
			if(!resultSet48_8.next())
			{
				String query48_9="ALTER TABLE "+dbname1+".mr_pos_hold_invoice  ADD INDEX  `trans_indx` (`reg_num`(100), `receipt_no`(100));";
				statement.executeUpdate(query48_9);
			}
			String query48_10="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice' and column_name='cust_ph'; ";
			ResultSet resultSet48_10 = statement1.executeQuery(query48_10);
			if(!resultSet48_10.next())
			{
				String query48_11="ALTER TABLE "+dbname1+".mr_pos_hold_invoice ADD COLUMN  `cust_ph` TEXT NOT NULL;";
				statement.executeUpdate(query48_11);
			}
			
			String query48_12="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice' and column_name='Chkbill_Sts'; ";
			ResultSet resultSet48_12 = statement1.executeQuery(query48_12);
			if(!resultSet48_12.next())
			{
				String query48_13="ALTER TABLE "+dbname1+".mr_pos_hold_invoice ADD COLUMN  `Chkbill_Sts` int(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query48_13);
			}
			
			//49.	mr_pos_hold_invoice_items
			String query49="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice_items' and column_name='cost'; ";
			ResultSet resultSet49 = statement1.executeQuery(query49);
			if(!resultSet49.next())
			{
				String query49_1="ALTER TABLE "+dbname1+".mr_pos_hold_invoice_items ADD COLUMN  `cost` TEXT  NULL;";
				statement.executeUpdate(query49_1);
			}
			String query49_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice_items' and column_name='sp'; ";
			ResultSet resultSet49_2 = statement1.executeQuery(query49_2);
			if(!resultSet49_2.next())
			{
				String query49_3="ALTER TABLE "+dbname1+".mr_pos_hold_invoice_items ADD COLUMN  `sp` DOUBLE NOT NULL DEFAULT '0';";
				statement.executeUpdate(query49_3);
			}
			String query49_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice_items' and column_name='disc_type'; ";
			ResultSet resultSet49_4 = statement1.executeQuery(query49_4);
			if(!resultSet49_4.next())
			{
				String query49_5="ALTER TABLE "+dbname1+".mr_pos_hold_invoice_items ADD COLUMN  `disc_type` TEXT  NULL;";
				statement.executeUpdate(query49_5);
			}
			String query49_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice_items' and column_name='commited_qty'; ";
			ResultSet resultSet49_6 = statement1.executeQuery(query49_6);
			if(!resultSet49_6.next())
			{
				String query49_7="ALTER TABLE "+dbname1+".mr_pos_hold_invoice_items ADD COLUMN  `commited_qty` TEXT  NULL;";
				statement.executeUpdate(query49_7);
			}
			String query49_8="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice_items' and column_name='vat_perc'; ";
			ResultSet resultSet49_8 = statement1.executeQuery(query49_8);
			if(!resultSet49_8.next())
			{
				String query49_9="ALTER TABLE "+dbname1+".mr_pos_hold_invoice_items ADD COLUMN  `vat_perc` DOUBLE NOT NULL DEFAULT '0';";
				statement.executeUpdate(query49_9);
			}
			String query49_10="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice_items' and column_name='department'; ";
			ResultSet resultSet49_10 = statement1.executeQuery(query49_10);
			if(!resultSet49_10.next())
			{
				String query49_11="ALTER TABLE "+dbname1+".mr_pos_hold_invoice_items ADD COLUMN  `department`  TEXT  NULL;";
				statement.executeUpdate(query49_11);
			}
			String query49_12="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice_items' and column_name='kot_number'; ";
			ResultSet resultSet49_12 = statement1.executeQuery(query49_12);
			if(!resultSet49_12.next())
			{
				String query49_13="ALTER TABLE "+dbname1+".mr_pos_hold_invoice_items ADD COLUMN  `kot_number`  INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query49_13);
			}
			String query49_14="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice_items' and column_name='comments_item'; ";
			ResultSet resultSet49_14 = statement1.executeQuery(query49_14);
			if(!resultSet49_14.next())
			{
				String query49_15="ALTER TABLE "+dbname1+".mr_pos_hold_invoice_items ADD COLUMN  `comments_item`  TEXT  NULL;";
				statement.executeUpdate(query49_15);
			}
			String query49_16="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice_items' and column_name='print_sts'; ";
			ResultSet resultSet49_16 = statement1.executeQuery(query49_16);
			if(!resultSet49_16.next())
			{
				String query49_17="ALTER TABLE "+dbname1+".mr_pos_hold_invoice_items ADD COLUMN  `print_sts` INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query49_17);
			}
			String query49_18="ALTER TABLE "+dbname1+".mr_pos_hold_invoice_items CHANGE `qty` `qty` DOUBLE NOT NULL;";
			statement.executeUpdate(query49_18);
			String query49_19="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_pos_hold_invoice_items' ";
			ResultSet resultSet49_19 = statement1.executeQuery(query49_19);
			if(!resultSet49_19.next())
			{
				String query49_20="ALTER TABLE "+dbname1+".mr_pos_hold_invoice_items  ADD INDEX  `trans_indx` (`ref_receipt_num`(100), `ref_reg_no`(100), `item_code`(100));";
				statement.executeUpdate(query49_20);
			}
			String query49_21="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice_items' and column_name='cf'; ";
			ResultSet resultSet49_21 = statement1.executeQuery(query49_21);
			if(!resultSet49_21.next())
			{
				String query49_22="ALTER TABLE "+dbname1+".mr_pos_hold_invoice_items ADD COLUMN `cf` DOUBLE NOT NULL DEFAULT '0';";
				statement.executeUpdate(query49_22);
			}
			String query49_23="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice_items' and column_name='print_cmd'; ";
			ResultSet resultSet49_23 = statement1.executeQuery(query49_23);
			if(!resultSet49_23.next())
			{
				String query49_24="ALTER TABLE "+dbname1+".mr_pos_hold_invoice_items ADD COLUMN  `print_cmd` INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query49_24);
			}
			String query49_25="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_hold_invoice_items' and column_name='scale_sts'; ";
			ResultSet resultSet49_25 = statement1.executeQuery(query49_25);
			if(!resultSet49_25.next())
			{
				String query49_26="ALTER TABLE "+dbname1+".mr_pos_hold_invoice_items ADD COLUMN  `scale_sts` INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query49_26);
			}
			
			//50.	mr_pos_settlement_cash
			
			String query50="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_cash' and column_name='grand_total'; ";
			ResultSet resultSet50 = statement1.executeQuery(query50);
			if(!resultSet50.next())
			{
				String query50_1="ALTER TABLE "+dbname1+".mr_pos_settlement_cash ADD COLUMN  `grand_total` DOUBLE NOT NULL AFTER `created_date`";
				statement.executeUpdate(query50_1);
			}
			String query50_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_cash' and column_name='cust_code'; ";
			ResultSet resultSet50_2 = statement1.executeQuery(query50_2);
			if(!resultSet50_2.next())
			{
				String query50_3="ALTER TABLE "+dbname1+".mr_pos_settlement_cash ADD COLUMN  `cust_code`  TEXT NULL  ;";
				statement.executeUpdate(query50_3);
			}
			String query50_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_cash' and column_name='cust_prev_balance'; ";
			ResultSet resultSet50_4 = statement1.executeQuery(query50_4);
			if(!resultSet50_4.next())
			{
				String query50_5="ALTER TABLE "+dbname1+".mr_pos_settlement_cash ADD COLUMN  `cust_prev_balance` DOUBLE NOT NULL DEFAULT '0' ;";
				statement.executeUpdate(query50_5);
			}
			String query50_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_cash' and column_name='delivery_user'; ";
			ResultSet resultSet50_6 = statement1.executeQuery(query50_6);
			if(!resultSet50_6.next())
			{
				String query50_7="ALTER TABLE "+dbname1+".mr_pos_settlement_cash ADD COLUMN  `delivery_user`  TEXT  NULL;";
				statement.executeUpdate(query50_7);
			}
			String query50_8="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_cash' and column_name='table_num'; ";
			ResultSet resultSet50_8 = statement1.executeQuery(query50_8);
			if(!resultSet50_8.next())
			{
				String query50_9="ALTER TABLE "+dbname1+".mr_pos_settlement_cash ADD COLUMN  `table_num`  TEXT  NULL;";
				statement.executeUpdate(query50_9);
			}
			String query50_10="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_cash' and column_name='mode'; ";
			ResultSet resultSet50_10 = statement1.executeQuery(query50_10);
			if(!resultSet50_10.next())
			{
				String query50_11="ALTER TABLE "+dbname1+".mr_pos_settlement_cash ADD COLUMN  `mode`  TEXT  NULL;";
				statement.executeUpdate(query50_11);
			}
			String query50_12="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_pos_settlement_cash' ";
			ResultSet resultSet50_12 = statement1.executeQuery(query50_12);
			if(!resultSet50_12.next())
			{
				String query50_13="ALTER TABLE "+dbname1+".mr_pos_settlement_cash  ADD INDEX  `trans_indx` (`reg_num`(100), `receipt_no`(100));";
				statement.executeUpdate(query50_13);
			}
			String query50_14="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_cash' and column_name='cust_ph'; ";
			ResultSet resultSet50_14 = statement1.executeQuery(query50_14);
			if(!resultSet50_14.next())
			{
				String query50_15="ALTER TABLE "+dbname1+".mr_pos_settlement_cash ADD COLUMN  `cust_ph`  TEXT  NULL;";
				statement.executeUpdate(query50_15);
			}
			
			//51.	mr_pos_settlement_cash_items
			String query51="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_cash_items' and column_name='vat_type'; ";
			ResultSet resultSet51 = statement1.executeQuery(query51);
			if(!resultSet51.next())
			{
				String query51_1="ALTER TABLE "+dbname1+".mr_pos_settlement_cash_items ADD COLUMN  `vat_type` TEXT  NULL;";
				statement.executeUpdate(query51_1);
			}
			String query51_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_cash_items' and column_name='vat_perc'; ";
			ResultSet resultSet51_2 = statement1.executeQuery(query51_2);
			if(!resultSet51_2.next())
			{
				String query51_3="ALTER TABLE "+dbname1+".mr_pos_settlement_cash_items ADD COLUMN  `vat_perc` DOUBLE NOT NULL DEFAULT '0';";
				statement.executeUpdate(query51_3);
			}
			String query51_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_cash_items' and column_name='disc_type'; ";
			ResultSet resultSet51_4 = statement1.executeQuery(query51_4);
			if(!resultSet51_4.next())
			{
				String query51_5="ALTER TABLE "+dbname1+".mr_pos_settlement_cash_items ADD COLUMN  `disc_type` TEXT  NULL;";
				statement.executeUpdate(query51_5);
			}
			String query51_6="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_pos_settlement_cash_items' ";
			ResultSet resultSet51_6 = statement1.executeQuery(query51_6);
			if(!resultSet51_6.next())
			{
				String query51_7="ALTER TABLE "+dbname1+".mr_pos_settlement_cash_items  ADD INDEX  `trans_indx` (`ref_receipt_num`(100), `ref_reg_num`(100), item_code(100));";
				statement.executeUpdate(query51_7);
			}
			String query51_8="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_cash_items' and column_name='cf'; ";
			ResultSet resultSet51_8 = statement1.executeQuery(query51_8);
			if(!resultSet51_8.next())
			{
				String query51_9="ALTER TABLE "+dbname1+".mr_pos_settlement_cash_items ADD COLUMN `cf` DOUBLE NOT NULL DEFAULT '0';";
				statement.executeUpdate(query51_9);
			}
			
			//52.	mr_pos_settlement_history
			
			String query52="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_history' and column_name='grand_total'; ";
			ResultSet resultSet52 = statement1.executeQuery(query52);
			if(!resultSet52.next())
			{
				String query52_1="ALTER TABLE "+dbname1+".mr_pos_settlement_history ADD COLUMN  `grand_total` DOUBLE NOT NULL AFTER `status`";
				statement.executeUpdate(query52_1);
			}
			String query52_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_history' and column_name='cust_code'; ";
			ResultSet resultSet52_2 = statement1.executeQuery(query52_2);
			if(!resultSet52_2.next())
			{
				String query52_3="ALTER TABLE "+dbname1+".mr_pos_settlement_history ADD COLUMN  `cust_code`  TEXT NULL  ;";
				statement.executeUpdate(query52_3);
			}
			String query52_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_history' and column_name='cust_prev_balance'; ";
			ResultSet resultSet52_4 = statement1.executeQuery(query52_4);
			if(!resultSet52_4.next())
			{
				String query52_5="ALTER TABLE "+dbname1+".mr_pos_settlement_history ADD COLUMN  `cust_prev_balance` DOUBLE NOT NULL DEFAULT '0' ;";
				statement.executeUpdate(query52_5);
			}
			String query52_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_history' and column_name='mode'; ";
			ResultSet resultSet52_6 = statement1.executeQuery(query52_6);
			if(!resultSet52_6.next())
			{
				String query52_7="ALTER TABLE "+dbname1+".mr_pos_settlement_history ADD COLUMN  `mode`  TEXT  NULL;";
				statement.executeUpdate(query52_7);
			}
			String query52_8="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_history' and column_name='delivery_user'; ";
			ResultSet resultSet52_8 = statement1.executeQuery(query52_8);
			if(!resultSet52_8.next())
			{
				String query52_9="ALTER TABLE "+dbname1+".mr_pos_settlement_history ADD COLUMN  `delivery_user`  TEXT  NULL;";
				statement.executeUpdate(query52_9);
			}
			String query52_10="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_history' and column_name='table_num'; ";
			ResultSet resultSet52_10 = statement1.executeQuery(query52_10);
			if(!resultSet52_10.next())
			{
				String query52_11="ALTER TABLE "+dbname1+".mr_pos_settlement_history ADD COLUMN  `table_num`  TEXT  NULL;";
				statement.executeUpdate(query52_11);
			}
			String query52_12="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_pos_settlement_history' ";
			ResultSet resultSet52_12 = statement1.executeQuery(query52_12);
			if(!resultSet52_12.next())
			{
				String query52_13="ALTER TABLE "+dbname1+".mr_pos_settlement_history  ADD INDEX  `trans_indx` (`reg_num`(100), `receipt_no`(100));";
				statement.executeUpdate(query52_13);
			}
			String query52_14="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_history' and column_name='close_date'; ";
			ResultSet resultSet52_14 = statement1.executeQuery(query52_14);
			if(!resultSet52_14.next())
			{
				String query52_15="ALTER TABLE "+dbname1+".mr_pos_settlement_history ADD COLUMN  `close_date` date NOT NULL ;";
				statement.executeUpdate(query52_15);
				String query52_16="Update "+dbname1+".mr_pos_settlement_history set close_date=created_date ";
				statement.executeUpdate(query52_16);
			}
			String query52_17="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_history' and column_name='cust_ph'; ";
			ResultSet resultSet52_17 = statement1.executeQuery(query52_17);
			if(!resultSet52_17.next())
			{
				String query52_18="ALTER TABLE "+dbname1+".mr_pos_settlement_history ADD COLUMN  `cust_ph`  TEXT  NULL;";
				statement.executeUpdate(query52_18);
			}
			String query52_19="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_history' and column_name='jv_num'; ";
			ResultSet resultSet52_19 = statement1.executeQuery(query52_19);
			if(!resultSet52_19.next())
			{
				String query52_20="ALTER TABLE "+dbname1+".mr_pos_settlement_history ADD COLUMN  `jv_num`  TEXT  NULL;";
				statement.executeUpdate(query52_20);
			}
			
			//53.	mr_pos_settlement_history_items
			String query53="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_history_items' and column_name='vat_type'; ";
			ResultSet resultSet53 = statement1.executeQuery(query53);
			if(!resultSet53.next())
			{
				String query53_1="ALTER TABLE "+dbname1+".mr_pos_settlement_history_items ADD COLUMN  `vat_type` TEXT  NULL;";
				statement.executeUpdate(query53_1);
			}
			String query53_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_history_items' and column_name='vat_perc'; ";
			ResultSet resultSet53_2 = statement1.executeQuery(query53_2);
			if(!resultSet53_2.next())
			{
				String query53_3="ALTER TABLE "+dbname1+".mr_pos_settlement_history_items ADD COLUMN  `vat_perc` DOUBLE NOT NULL DEFAULT '0';";
				statement.executeUpdate(query53_3);
			}
			String query53_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_history_items' and column_name='disc_type'; ";
			ResultSet resultSet53_4 = statement1.executeQuery(query53_4);
			if(!resultSet53_4.next())
			{
				String query53_5="ALTER TABLE "+dbname1+".mr_pos_settlement_history_items ADD COLUMN  `disc_type` TEXT  NULL;";
				statement.executeUpdate(query53_5);
			}
			String query53_6="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_pos_settlement_history_items' ";
			ResultSet resultSet53_6 = statement1.executeQuery(query53_6);
			if(!resultSet53_6.next())
			{
				String query53_7="ALTER TABLE "+dbname1+".mr_pos_settlement_history_items  ADD INDEX  `trans_indx` (`ref_receipt_num`(100), `ref_reg_num`(100), item_code(100));";
				statement.executeUpdate(query53_7);
			}
			String query53_8="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_settlement_history_items' and column_name='cf'; ";
			ResultSet resultSet53_8 = statement1.executeQuery(query53_8);
			if(!resultSet53_8.next())
			{
				String query53_9="ALTER TABLE "+dbname1+".mr_pos_settlement_history_items ADD COLUMN `cf` DOUBLE NOT NULL DEFAULT '0';";
				statement.executeUpdate(query53_9);
			}
			
			//54.	mr_po_terms
			String query54="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_po_terms' ";
			ResultSet resultSet54 = statement1.executeQuery(query54);
			if(!resultSet54.next())
			{
				String query54_1="ALTER TABLE "+dbname1+".mr_po_terms  ADD INDEX  `trans_indx` (`po_term_code`(100));";
				statement.executeUpdate(query54_1);
			}
			
					
			//55.	promotion
			String query55="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_promotion' ; ";
			ResultSet resultSet55= statement1.executeQuery(query55);
			if(!resultSet55.next())
			{
				String query55_1="CREATE TABLE  "+dbname1+".mr_promotion (`id` int(255) NOT NULL AUTO_INCREMENT,  `itemcode` text  NULL,  `barcode` text  NULL,  `itemname` text  NULL,  `dept` text NOT NULL,  `sub_dept` text NOT NULL,  `category` text NOT NULL,  `sub_cat` text NOT NULL,  `percentage` double NOT NULL,  `from_date` date NOT NULL,  `to_date` date NOT NULL,  `promo_type` text NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query55_1);
			}
			String query55_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_promotion' ";
			ResultSet resultSet55_2 = statement1.executeQuery(query55_2);
			if(!resultSet55_2.next())
			{
				String query55_3="ALTER TABLE "+dbname1+".mr_promotion  ADD INDEX  `trans_indx` (`itemcode`(100), `barcode`(100), promo_type(100));";
				statement.executeUpdate(query55_3);
			}
		
			//56.	mr_purchase_list
			String query56="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_purchase_list' and column_name='remark'; ";
			ResultSet resultSet56 = statement1.executeQuery(query56);
			if(!resultSet56.next())
			{
				String query56_1="ALTER TABLE "+dbname1+".mr_purchase_list ADD COLUMN  `remark` TEXT  NULL;";
				statement.executeUpdate(query56_1);
			}
			
			String query56_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_purchase_list' ";
			ResultSet resultSet56_2 = statement1.executeQuery(query56_2);
			if(!resultSet56_2.next())
			{
				String query56_3="ALTER TABLE "+dbname1+".mr_purchase_list  ADD INDEX  `trans_indx` (`po_no`(100));";
				statement.executeUpdate(query56_3);
			}
			String query56_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_purchase_list' and column_name='roundoff'; ";
			ResultSet resultSet56_4 = statement1.executeQuery(query56_4);
			if(!resultSet56_4.next())
			{
				String query56_5="ALTER TABLE "+dbname1+".mr_purchase_list ADD COLUMN  `roundoff` double NOT NULL;";
				statement.executeUpdate(query56_5);
			}
			
			//57.	mr_purchase_list_history
			String query57="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_promotion' ; ";
			ResultSet resultSet57= statement1.executeQuery(query57);
			if(!resultSet57.next())
			{
				String query57_1="CREATE TABLE  "+dbname1+".mr_purchase_list_history (`id` int(255) NOT NULL AUTO_INCREMENT,  `com_date` date NOT NULL,  `po_date` date NOT NULL,  `user_id` text NOT NULL,  `po_no` text NOT NULL,  `invo_nu` text NOT NULL,  `ttl_items` double NOT NULL,  `ttl_price` double NOT NULL,  `sub_ttl_amt` double NOT NULL DEFAULT '0',  `grand_disc` double NOT NULL DEFAULT '0',  `total_vat` double NOT NULL DEFAULT '0',  `grand_total` double NOT NULL DEFAULT '0',  `promt_print` int(11) NOT NULL DEFAULT '0',  `double_discont` int(11) NOT NULL DEFAULT '0',  `vendor_id` text NOT NULL,  `assume_ttl_cst` int(11) NOT NULL DEFAULT '0',  `currency_id` text NOT NULL,  `status` text NOT NULL,  `posted_date` date DEFAULT NULL,  `po_terms` text NOT NULL,  `store_name` text NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `referance` text,  `branch` text,  `vat_account` text,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query57_1);
			}
			
			String query57_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_purchase_list_history' and column_name='remark'; ";
			ResultSet resultSet57_2 = statement1.executeQuery(query57_2);
			if(!resultSet57_2.next())
			{
				String query57_3="ALTER TABLE "+dbname1+".mr_purchase_list_history ADD COLUMN  `remark` TEXT  NULL;";
				statement.executeUpdate(query57_3);
			}
			
			String query57_4="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_purchase_list' ";
			ResultSet resultSet57_4 = statement1.executeQuery(query57_4);
			if(!resultSet57_4.next())
			{
				String query57_5="ALTER TABLE "+dbname1+".mr_purchase_list_history ADD INDEX   trans_indx (`po_no`(100) );";
				statement.executeUpdate(query57_5);
			}
			String query57_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_purchase_list_history' and column_name='roundoff'; ";
			ResultSet resultSet57_6 = statement1.executeQuery(query57_6);
			if(!resultSet57_6.next())
			{
				String query57_7="ALTER TABLE "+dbname1+".mr_purchase_list_history ADD COLUMN  `roundoff` double NOT NULL;";
				statement.executeUpdate(query57_7);
			}
			
			//58.	mr_purchase_order
			String query58="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_purchase_order' and column_name='sp'; ";
			ResultSet resultSet58 = statement1.executeQuery(query58);
			if(!resultSet58.next())
			{
				String query58_1="ALTER TABLE "+dbname1+".mr_purchase_order ADD COLUMN  `sp` double NOT NULL";
				statement.executeUpdate(query58_1);
			}
			String query58_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_purchase_order' and column_name='web_ref'; ";
			ResultSet resultSet58_2 = statement1.executeQuery(query58_2);
			if(!resultSet58_2.next())
			{
				String query58_3="ALTER TABLE "+dbname1+".mr_purchase_order ADD COLUMN  `web_ref` TEXT  NULL;";
				statement.executeUpdate(query58_3);
			}
			String query58_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_purchase_order' and column_name='tax_type'; ";
			ResultSet resultSet58_4 = statement1.executeQuery(query58_4);
			if(!resultSet58_4.next())
			{
				String query58_5="ALTER TABLE "+dbname1+".mr_purchase_order ADD COLUMN  `tax_type` TEXT NULL";
				statement.executeUpdate(query58_5);
			}
			String query58_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_purchase_order' and column_name='item_name'; ";
			ResultSet resultSet58_6 = statement1.executeQuery(query58_6);
			if(!resultSet58_6.next())
			{
				String query58_7="ALTER TABLE "+dbname1+".mr_purchase_order ADD COLUMN  `item_name` TEXT NULL";
				statement.executeUpdate(query58_7);
			}
			String query58_8="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_purchase_order' and column_name='input_tax'; ";
			ResultSet resultSet58_8 = statement1.executeQuery(query58_8);
			if(!resultSet58_8.next())
			{
				String query58_9="ALTER TABLE "+dbname1+".mr_purchase_order ADD COLUMN  `input_tax` TEXT NULL";
				statement.executeUpdate(query58_9);
			}
			String query58_10="ALTER TABLE "+dbname1+".mr_purchase_order CHANGE `qty` `qty` DOUBLE NOT NULL;";
			statement.executeUpdate(query58_10);
			String query58_11="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_purchase_order' ";
			ResultSet resultSet58_11 = statement1.executeQuery(query58_11);
			if(!resultSet58_11.next())
			{
				String query58_12="ALTER TABLE "+dbname1+".mr_purchase_order ADD INDEX  trans_indx (`ref_po_num`(100),item_code(100) );";
				statement.executeUpdate(query58_12);
			}
			
			//59.	mr_purchase_order_history
			String query59="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_purchase_order_history' ; ";
			ResultSet resultSet59= statement1.executeQuery(query59);
			if(!resultSet59.next())
			{
				String query59_1="CREATE TABLE  "+dbname1+".mr_purchase_order_history (`id` int(255) NOT NULL AUTO_INCREMENT,  `ref_po_num` text NOT NULL,  `barcode` text NOT NULL,  `item_code` text NOT NULL,  `qty` double NOT NULL,  `unit` text NOT NULL,  `cf` int(11) NOT NULL,  `unit_cost` double NOT NULL,  `itm_disc` double NOT NULL,  `itm_cost_after_disc` double NOT NULL,  `sub_ttl_disc` double NOT NULL,  `sub_cost_after_sub_ttl_disc` double NOT NULL,  `tax_val` double NOT NULL,  `total` double NOT NULL,  `cost_after_sub_ttl_disc` double NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `sp` double NOT NULL,  `tax_type` text,  `item_name` text,  `input_tax` text,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query59_1);
			}
			
			String query59_2="ALTER TABLE "+dbname1+".mr_purchase_order_history CHANGE `qty` `qty` DOUBLE NOT NULL;";
			statement.executeUpdate(query59_2);
			
			String query59_3="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_purchase_order_history' ";
			ResultSet resultSet59_3 = statement1.executeQuery(query59_3);
			if(!resultSet59_3.next())
			{
				String query59_4="ALTER TABLE "+dbname1+".mr_purchase_order_history ADD INDEX  `trans_indx` (`ref_po_num`(100),item_code(100) );";
				statement.executeUpdate(query59_4);
			}
			
			//60.	mr_purchase_return
			
			String query60="ALTER TABLE "+dbname1+".mr_purchase_return CHANGE `qty` `qty` DOUBLE NOT NULL;";
			statement.executeUpdate(query60);
			String query60_1="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_purchase_return' ";
			ResultSet resultSet60_1 = statement1.executeQuery(query60_1);
			if(!resultSet60_1.next())
			{
				String query60_2="ALTER TABLE "+dbname1+".mr_purchase_return ADD INDEX   trans_indx (`ref_pr_num`(100),item_code(100) );";
				statement.executeUpdate(query60_2);
			}
			
			//61.	`mr_purchase_return_list`
			String query61="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_purchase_return_list' ";
			ResultSet resultSet61 = statement1.executeQuery(query61);
			if(!resultSet61.next())
			{
				String query61_1="ALTER TABLE "+dbname1+".mr_purchase_return_list ADD INDEX  trans_indx (`pr_no`(100) );";
				statement.executeUpdate(query61_1);
			}
			
			//62.	`mr_purchase_return_tax`
			String query62="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_purchase_return_tax' ";
			ResultSet resultSet62 = statement1.executeQuery(query62);
			if(!resultSet62.next())
			{
				String query62_1="ALTER TABLE "+dbname1+".mr_purchase_return_tax ADD INDEX  trans_indx (`ref_pr_no`(100) );";
				statement.executeUpdate(query62_1);
			}
			
			//63.	`mr_purchase_tax`
			String query63="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_purchase_tax' ";
			ResultSet resultSet63 = statement1.executeQuery(query63);
			if(!resultSet63.next())
			{
				String query63_1="ALTER TABLE "+dbname1+".mr_purchase_tax ADD INDEX  trans_indx (`ref_po_no`(100) );";
				statement.executeUpdate(query63_1);
			}
			
			//64.	mr_register_controls
			String query64="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_register_controls' and column_name='registerAsWaiter'; ";
			ResultSet resultSet64 = statement1.executeQuery(query64);
			if(!resultSet64.next())
			{
				String query64_1="ALTER TABLE "+dbname1+".mr_register_controls ADD COLUMN  `registerAsWaiter`  INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query64_1);
			}
			String query64_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_register_controls' and column_name='register_all_invoice'; ";
			ResultSet resultSet64_2 = statement1.executeQuery(query64_2);
			if(!resultSet64_2.next())
			{
				String query64_3="ALTER TABLE "+dbname1+".mr_register_controls ADD COLUMN  `register_all_invoice`  INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query64_3);
			}
			String query64_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_register_controls' and column_name='denominationPrint'; ";
			ResultSet resultSet64_4 = statement1.executeQuery(query64_4);
			if(!resultSet64_4.next())
			{
				String query64_5="ALTER TABLE "+dbname1+".mr_register_controls ADD COLUMN  `denominationPrint`  INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query64_5);
			}
			String query64_6="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_register_controls' ";
			ResultSet resultSet64_6 = statement1.executeQuery(query64_6);
			if(!resultSet64_6.next())
			{
				String query64_7="ALTER TABLE "+dbname1+".mr_register_controls ADD INDEX  trans_indx (`ref_register_num`(100) );";
				statement.executeUpdate(query64_7);
			}
			String query64_8="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_register_controls' and column_name='PauseHoldPrint'; ";
			ResultSet resultSet64_8 = statement1.executeQuery(query64_8);
			if(!resultSet64_8.next())
			{
				String query64_9="ALTER TABLE "+dbname1+".mr_register_controls ADD COLUMN  `PauseHoldPrint`  INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query64_9);
			}
			
			//65.	mr_register_openings
			String query65="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_register_openings' ";
			ResultSet resultSet65 = statement1.executeQuery(query65);
			if(!resultSet65.next())
			{
				String query65_1="ALTER TABLE "+dbname1+".mr_register_openings ADD INDEX  trans_indx (`reg_num`(100) );";
				statement.executeUpdate(query65_1);
			}
			
			String query65_4="SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '"+dbname1+"' and TABLE_NAME='mr_register_openings' and column_name='system'";
			ResultSet resultSet65_4 = statement1.executeQuery(query65_4);
			if(resultSet65_4.next())
			{
				String query44_5="ALTER TABLE "+dbname1+".mr_register_openings  CHANGE system device_name TEXT NOT NULL";
				statement.executeUpdate(query44_5);
			}
			
			String query65_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_register_openings' and column_name='device_name';";
			ResultSet resultSet65_2 = statement1.executeQuery(query65_2);
			if(!resultSet65_2.next())
			{
				String query65_3="ALTER TABLE "+dbname1+".mr_register_openings ADD COLUMN  `device_name` TEXT NULL;";
				statement.executeUpdate(query65_3);
			}
			
			
			
			//66.	mr_register_openings_history
			String query66="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_register_openings_history' ";
			ResultSet resultSet66 = statement1.executeQuery(query66);
			if(!resultSet66.next())
			{
				String query66_1="ALTER TABLE "+dbname1+".mr_register_openings_history ADD INDEX  trans_indx (`reg_num`(100) );";
				statement.executeUpdate(query66_1);
			}
			
			//67.	mr_register_open_items
			String query67="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_register_open_items' ";
			ResultSet resultSet67 = statement1.executeQuery(query67);
			if(!resultSet67.next())
			{
				String query67_1="ALTER TABLE "+dbname1+".mr_register_open_items ADD INDEX  trans_indx (`ref_reg_num`(100) );";
				statement.executeUpdate(query67_1);
			}
			
			//68.	mr_register_open_items_history
			String query68="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_register_open_items_history' ";
			ResultSet resultSet68 = statement1.executeQuery(query68);
			if(!resultSet68.next())
			{
				String query68_1="ALTER TABLE "+dbname1+".mr_register_open_items_history ADD INDEX  trans_indx (`ref_reg_num`(100) );";
				statement.executeUpdate(query68_1);
			}
			
			//69.	mr_register_setup
			String query69="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_register_setup' ";
			ResultSet resultSet69 = statement1.executeQuery(query69);
			if(!resultSet69.next())
			{
				String query69_1="ALTER TABLE "+dbname1+".mr_register_setup ADD INDEX  trans_indx (`register_number`(100) );";
				statement.executeUpdate(query69_1);
			}
			
			//70.	mr_sales_tax
			String query70="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_sales_tax' ";
			ResultSet resultSet70 = statement1.executeQuery(query70);
			if(!resultSet70.next())
			{
				String query70_1="ALTER TABLE "+dbname1+".mr_sales_tax ADD INDEX  trans_indx (`reg_num`(100) );";
				statement.executeUpdate(query70_1);
			}
			
			//71.	mr_sd_customer_payment
			String query71="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_customer_payment' ; ";
			ResultSet resultSet71= statement1.executeQuery(query71);
			if(!resultSet71.next())
			{
				String query71_1="CREATE TABLE  "+dbname1+".mr_sd_customer_payment (`id` int(255) NOT NULL AUTO_INCREMENT,  `ref_tran_num` text NOT NULL,  `ref_cust_id` text NOT NULL,  `invo_num` text NOT NULL,  `invo_date` date NOT NULL,  `invo_amt` double NOT NULL,  `paid_amt` double NOT NULL DEFAULT '0',  `bal_amt` double NOT NULL,  `store_id` text NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0-pending,1-post,2-return,3-after_post',`doc_num` text NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query71_1);
			}
			
			String query71_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_customer_payment' and column_name='type'; ";
			ResultSet resultSet71_2 = statement1.executeQuery(query71_2);
			if(!resultSet71_2.next())
			{
				String query71_3="ALTER TABLE "+dbname1+".mr_sd_customer_payment ADD COLUMN  `type` TEXT  NULL;";
				statement.executeUpdate(query71_3);
			}
			
			String query71_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_customer_payment' and column_name='doc_num'; ";
			ResultSet resultSet71_6 = statement1.executeQuery(query71_6);
			if(!resultSet71_6.next())
			{
				String query71_7="ALTER TABLE "+dbname1+".mr_sd_customer_payment ADD COLUMN  `doc_num` TEXT  NULL;";
				statement.executeUpdate(query71_7);
			}
			
			String query71_4="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_sd_customer_payment' ";
			ResultSet resultSet71_4 = statement1.executeQuery(query71_4);
			if(!resultSet71_4.next())
			{
				String query71_5="ALTER TABLE "+dbname1+".mr_sd_customer_payment ADD INDEX  `trans_indx` (`ref_tran_num`(100),ref_cust_id(100));";
				statement.executeUpdate(query71_5);
			}
			
			//72.	mr_sd_customer_payment_details
			String query72="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_customer_payment_details' ; ";
			ResultSet resultSet72= statement1.executeQuery(query72);
			if(!resultSet72.next())
			{
				String query72_1="CREATE TABLE  "+dbname1+".mr_sd_customer_payment_details (`id` int(255) NOT NULL AUTO_INCREMENT, `ref_tran_num` text NOT NULL,  `seq_num` text NOT NULL,  `ref_cust_id` text NOT NULL,  `cr_amt` float NOT NULL,  `dr_amt` float NOT NULL,  `disc_amt` float NOT NULL DEFAULT '0',  `issue_date` date NOT NULL,  `payment_method` text,  `paycode` text,  `bank_name` text,  `cheque_no` text,  `pdc_check` int(11) NOT NULL DEFAULT '0',  `ac_payee` int(11) NOT NULL DEFAULT '0',  `remark` text,  `pay_by` text,  `note` text,  `store_id` text NOT NULL,  `username` text NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query72_1);
			}
			String query72_3="ALTER TABLE "+dbname1+".mr_sd_customer_payment_details CHANGE `cr_amt` `cr_amt` DOUBLE NOT NULL;";
			statement.executeUpdate(query72_3);
			String query72_4="ALTER TABLE "+dbname1+".mr_sd_customer_payment_details CHANGE `dr_amt` `dr_amt` DOUBLE NOT NULL;";
			statement.executeUpdate(query72_4);
			String query72_5="ALTER TABLE "+dbname1+".mr_sd_customer_payment_details CHANGE `disc_amt` `disc_amt` DOUBLE NOT NULL;";
			statement.executeUpdate(query72_5);
			
			String query72_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_customer_payment_details' and column_name='status'; ";
			ResultSet resultSet72_4 = statement1.executeQuery(query72_6);
			if(!resultSet72_4.next())
			{
				String query72_7="ALTER TABLE  "+dbname1+".mr_sd_customer_payment_details ADD COLUMN `status` text NULL ;";
				statement.executeUpdate(query72_7);
				
			}
			String query72_9="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_customer_payment_details' and column_name='cust_bank'; ";
			ResultSet resultSet72_9 = statement1.executeQuery(query72_9);
			if(!resultSet72_9.next())
			{
				String query72_10="ALTER TABLE  "+dbname1+".mr_sd_customer_payment_details ADD COLUMN `cust_bank` text NULL ;";
				statement.executeUpdate(query72_10);
				
			}
			
			String query72_11="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_customer_payment_details' and column_name='roundoff'; ";
			ResultSet resultSet72_12 = statement1.executeQuery(query72_11);
			if(!resultSet72_12.next())
			{
				String query72_13="ALTER TABLE  "+dbname1+".mr_sd_customer_payment_details ADD COLUMN `roundoff` double NOT NULL DEFAULT '0' ;";
				statement.executeUpdate(query72_13);
				
			}
			
			
			String query72_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_sd_customer_payment_details' ";
			ResultSet resultSet72_2 = statement1.executeQuery(query72_2);
			if(!resultSet72_2.next())
			{
				String query72_8="ALTER TABLE "+dbname1+".mr_sd_customer_payment_details ADD INDEX  `trans_indx` (`ref_tran_num`(100),seq_num(100),ref_cust_id(100));";
				statement.executeUpdate(query72_8);
			}
			
			//73.	mr_sd_lines
			
			String query73="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_lines' ; ";
			ResultSet resultSet73= statement1.executeQuery(query73);
			if(!resultSet73.next())
			{
				String query73_1="CREATE TABLE  "+dbname1+".mr_sd_lines (`id` int(255) NOT NULL AUTO_INCREMENT,  `ref_inv_num` text NOT NULL,  `barcode` text NOT NULL,  `item_code` text NOT NULL,  `item_name` text NOT NULL,  `tax_type` text NOT NULL,  `output_tax` text NOT NULL,  `qty` double NOT NULL,  `unit` text NOT NULL,  `cf` int(11) NOT NULL,  `unit_cost` double NOT NULL,  `itm_disc` double NOT NULL,  `itm_cost_after_disc` double NOT NULL,  `sub_ttl_disc` double NOT NULL,  `sub_cost_after_sub_ttl_disc` double NOT NULL,  `tax_val` double NOT NULL,  `total` double NOT NULL,  `cost_after_sub_ttl_disc` double NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `remark` text NOT NULL,  `item_details` text,  `invoice_ref` text,  `vendor_id` text,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query73_1);
			}
			String query73_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_lines' and column_name='item_details'; ";
			ResultSet resultSet73_2 = statement1.executeQuery(query73_2);
			if(!resultSet73_2.next())
			{
				String query73_3="ALTER TABLE "+dbname1+".mr_sd_lines ADD COLUMN  `item_details` TEXT NULL ;";
				statement.executeUpdate(query73_3);
			}
			String query73_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_lines' and column_name='item_cost'; ";
			ResultSet resultSet73_4 = statement1.executeQuery(query73_4);
			if(!resultSet73_4.next())
			{
				String query73_5="ALTER TABLE "+dbname1+".mr_sd_lines ADD COLUMN  `item_cost` DOUBLE NOT NULL;";
				statement.executeUpdate(query73_5);
			}
			String query73_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_lines' and column_name='item_name'; ";
			ResultSet resultSet73_6 = statement1.executeQuery(query73_6);
			if(!resultSet73_6.next())
			{
				String query73_7="ALTER TABLE "+dbname1+".mr_sd_lines ADD COLUMN  `item_name` TEXT NULL";
				statement.executeUpdate(query73_7);
			}
			String query73_8="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_lines' and column_name='tax_type'; ";
			ResultSet resultSet73_8 = statement1.executeQuery(query73_8);
			if(!resultSet73_8.next())
			{
				String query73_9="ALTER TABLE "+dbname1+".mr_sd_lines ADD COLUMN  `tax_type` TEXT NOT NULL AFTER `item_name`;";
				statement.executeUpdate(query73_9);
			}
			String query73_10="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_lines' and column_name='output_tax'; ";
			ResultSet resultSet73_10 = statement1.executeQuery(query73_10);
			if(!resultSet73_10.next())
			{
				String query73_11="ALTER TABLE "+dbname1+".mr_sd_lines ADD COLUMN  `output_tax` TEXT NOT NULL AFTER `tax_type`;";
				statement.executeUpdate(query73_11);
			}
			String query73_12="ALTER TABLE "+dbname1+".mr_sd_lines CHANGE `qty` `qty` DOUBLE NOT NULL;";
			statement.executeUpdate(query73_12);
			String query73_13="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_sd_lines' ";
			ResultSet resultSet73_13 = statement1.executeQuery(query73_13);
			if(!resultSet73_13.next())
			{
				String query73_14="ALTER TABLE "+dbname1+".mr_sd_lines ADD INDEX  `trans_indx` (`ref_inv_num`(100),item_code(100));";
				statement.executeUpdate(query73_14);
			}
			
			//74.	mr_sd_lines_history
			String query74="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_lines_history' ; ";
			ResultSet resultSet74= statement1.executeQuery(query74);
			if(!resultSet74.next())
			{
				String query74_1="CREATE TABLE  "+dbname1+".mr_sd_lines_history (`id` int(255) NOT NULL AUTO_INCREMENT,  `ref_inv_num` text NOT NULL,  `barcode` text NOT NULL,  `item_code` text NOT NULL,  `item_name` text NOT NULL,  `tax_type` text NOT NULL,  `output_tax` text NOT NULL,  `qty` double NOT NULL,  `unit` text NOT NULL,  `cf` int(11) NOT NULL,  `unit_cost` double NOT NULL,  `itm_disc` double NOT NULL,  `itm_cost_after_disc` double NOT NULL,  `sub_ttl_disc` double NOT NULL,  `sub_cost_after_sub_ttl_disc` double NOT NULL,  `tax_val` double NOT NULL,  `total` double NOT NULL,  `cost_after_sub_ttl_disc` double NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `remark` text NOT NULL,  `item_details` text,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query74_1);
			}
			String query74_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_lines_history' and column_name='item_details'; ";
			ResultSet resultSet74_2 = statement1.executeQuery(query74_2);
			if(!resultSet74_2.next())
			{
				String query74_3="ALTER TABLE "+dbname1+".mr_sd_lines_history ADD COLUMN  `item_details` TEXT NULL ;";
				statement.executeUpdate(query74_3);
			}
			String query74_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_lines_history' and column_name='item_cost'; ";
			ResultSet resultSet74_4 = statement1.executeQuery(query74_4);
			if(!resultSet74_4.next())
			{
				String query74_5="ALTER TABLE "+dbname1+".mr_sd_lines_history ADD COLUMN  `item_cost` DOUBLE NOT NULL;";
				statement.executeUpdate(query74_5);
			}
			String query74_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_lines_history' and column_name='tax_type'; ";
			ResultSet resultSet74_6 = statement1.executeQuery(query74_6);
			if(!resultSet74_6.next())
			{
				String query74_7="ALTER TABLE "+dbname1+".mr_sd_lines_history ADD COLUMN  `tax_type` TEXT NOT NULL AFTER `item_name`;";
				statement.executeUpdate(query74_7);
			}
			String query74_8="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_lines_history' and column_name='output_tax'; ";
			ResultSet resultSet74_8 = statement1.executeQuery(query74_8);
			if(!resultSet74_8.next())
			{
				String query74_9="ALTER TABLE "+dbname1+".mr_sd_lines_history ADD COLUMN  `output_tax` TEXT NOT NULL AFTER `tax_type`;";
				statement.executeUpdate(query74_9);
			}
			String query74_10="ALTER TABLE "+dbname1+".mr_sd_lines_history CHANGE `qty` `qty` DOUBLE NOT NULL;";
			statement.executeUpdate(query74_10);
			String query74_11="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_sd_lines_history' ";
			ResultSet resultSet74_11 = statement1.executeQuery(query74_11);
			if(!resultSet74_11.next())
			{
				String query74_12="ALTER TABLE "+dbname1+".mr_sd_lines_history ADD INDEX   trans_indx (`ref_inv_num`(100) ,item_code(100));";
				statement.executeUpdate(query74_12);
			}
			
			//75.	mr_sd_list
			String query75="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_list' ; ";
			ResultSet resultSet75= statement1.executeQuery(query75);
			if(!resultSet75.next())
			{
				String query75_1="CREATE TABLE  "+dbname1+".mr_sd_list (`id` int(255) NOT NULL AUTO_INCREMENT,  `date` date NOT NULL,  `user_id` text NOT NULL,  `invoice_num` text NOT NULL,  `remark` text NOT NULL,  `total_amt` double DEFAULT '0',  `disc` double NOT NULL DEFAULT '0',  `total_vat` double NOT NULL DEFAULT '0',  `cust_id` text NOT NULL,  `cust_addr` text NOT NULL,  `double_disc` int(11) NOT NULL DEFAULT '0',  `status` text NOT NULL,  `referance` text NOT NULL,  `store_name` text NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `type` text,  `cust_name` text NOT NULL,  `invoice_ref` text,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query75_1);
			}
			String query75_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_list' and column_name='cust_name'; ";
			ResultSet resultSet75_2 = statement1.executeQuery(query75_2);
			if(!resultSet75_2.next())
			{
				String query75_3="ALTER TABLE "+dbname1+".mr_sd_list ADD COLUMN  `cust_name` TEXT NOT NULL ;";
				statement.executeUpdate(query75_3);
			}
			String query75_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_list' and column_name='invoice_ref'; ";
			ResultSet resultSet75_4 = statement1.executeQuery(query75_4);
			if(!resultSet75_4.next())
			{
				String query75_5="ALTER TABLE "+dbname1+".mr_sd_list ADD COLUMN  `invoice_ref` TEXT NULL ;";
				statement.executeUpdate(query75_5);
			}
			String query75_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_list' and column_name='web_ref'; ";
			ResultSet resultSet75_6 = statement1.executeQuery(query75_6);
			if(!resultSet75_6.next())
			{
				String query75_7="ALTER TABLE "+dbname1+".mr_sd_list ADD COLUMN  `web_ref` TEXT  NULL;";
				statement.executeUpdate(query75_7);
			}
			
			String query75_8="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_sd_list' ";
			ResultSet resultSet75_8 = statement1.executeQuery(query75_8);
			if(!resultSet75_8.next())
			{
				String query75_9="ALTER TABLE "+dbname1+".mr_sd_list ADD INDEX  `trans_indx` (`invoice_num`(100));";
				statement.executeUpdate(query75_9);
			}
			
			String query75_10="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_list' and column_name='salesman'; ";
			ResultSet resultSet75_10 = statement1.executeQuery(query75_10);
			if(!resultSet75_10.next())
			{
				String query75_11="ALTER TABLE "+dbname1+".mr_sd_list ADD COLUMN  `salesman` text NOT NULL ;";
				statement.executeUpdate(query75_11);
			}
			
			String query75_12="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_list' and column_name='paymentmethod'; ";
			ResultSet resultSet75_12 = statement1.executeQuery(query75_12);
			if(!resultSet75_12.next())
			{
				String query75_13="ALTER TABLE "+dbname1+".mr_sd_list ADD COLUMN  `paymentmethod` text NOT NULL ;";
				statement.executeUpdate(query75_13);
			}
			
			
			//76.	mr_sd_list_history
			String query76="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_list_history' ; ";
			ResultSet resultSet76= statement1.executeQuery(query76);
			if(!resultSet76.next())
			{
				String query76_1="CREATE TABLE  "+dbname1+".mr_sd_list_history (`id` int(255) NOT NULL AUTO_INCREMENT,  `date` date NOT NULL,  `user_id` text NOT NULL,  `invoice_num` text NOT NULL,  `remark` text NOT NULL,  `total_amt` double DEFAULT '0',  `disc` double NOT NULL DEFAULT '0',  `total_vat` double NOT NULL DEFAULT '0',  `cust_id` text NOT NULL,  `cust_addr` text NOT NULL,  `double_disc` int(11) NOT NULL DEFAULT '0',  `status` text NOT NULL,  `referance` text NOT NULL,  `store_name` text NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `type` text,  `cust_name` text NOT NULL,  `invoice_ref` text,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query76_1);
			}
			String query76_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_list_history' and column_name='cust_name'; ";
			ResultSet resultSet76_2 = statement1.executeQuery(query76_2);
			if(!resultSet76_2.next())
			{
				String query76_3="ALTER TABLE "+dbname1+".mr_sd_list_history ADD COLUMN  `cust_name` TEXT NOT NULL ;";
				statement.executeUpdate(query76_3);
			}
			String query76_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_list_history' and column_name='invoice_ref'; ";
			ResultSet resultSet76_4 = statement1.executeQuery(query76_4);
			if(!resultSet76_4.next())
			{
				String query76_5="ALTER TABLE "+dbname1+".mr_sd_list_history ADD COLUMN  `invoice_ref` TEXT NULL ;";
				statement.executeUpdate(query76_5);
			}
			String query76_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_list_history' and column_name='web_ref'; ";
			ResultSet resultSet76_6 = statement1.executeQuery(query76_6);
			if(!resultSet76_6.next())
			{
				String query76_7="ALTER TABLE "+dbname1+".mr_sd_list_history ADD COLUMN  `web_ref` TEXT  NULL;";
				statement.executeUpdate(query76_7);
			}
			
			String query76_8="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_sd_list_history' ";
			ResultSet resultSet76_8 = statement1.executeQuery(query76_8);
			if(!resultSet76_8.next())
			{
				String query76_9="ALTER TABLE "+dbname1+".mr_sd_list_history ADD INDEX  trans_indx (`invoice_num`(100) );";
				statement.executeUpdate(query76_9);
			}
			
			String query76_10="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_list_history' and column_name='salesman'; ";
			ResultSet resultSet76_10 = statement1.executeQuery(query76_10);
			if(!resultSet76_10.next())
			{
				String query76_11="ALTER TABLE "+dbname1+".mr_sd_list_history ADD COLUMN  `salesman` text NOT NULL ;";
				statement.executeUpdate(query76_11);
			}
			
			String query76_12="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_list_history' and column_name='paymentmethod'; ";
			ResultSet resultSet76_12 = statement1.executeQuery(query76_12);
			if(!resultSet76_12.next())
			{
				String query76_13="ALTER TABLE "+dbname1+".mr_sd_list_history ADD COLUMN  `paymentmethod` text NOT NULL ;";
				statement.executeUpdate(query76_13);
			}
			
			
			
			//77.	mr_sd_tax
			String query77="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_sd_tax' ; ";
			ResultSet resultSet77= statement1.executeQuery(query77);
			if(!resultSet77.next())
			{
				String query77_1="CREATE TABLE  "+dbname1+".mr_sd_tax (`id` int(255) NOT NULL AUTO_INCREMENT,  `inv_num` text NOT NULL,  `total_amt` text NOT NULL,  `tax_amount` text NOT NULL,  `tran_date` date NOT NULL, `remark` text NOT NULL,`created_date` date NOT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query77_1);
			}
			String query77_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_sd_tax' ";
			ResultSet resultSet77_2 = statement1.executeQuery(query77_2);
			if(!resultSet77_2.next())
			{
				String query77_3="ALTER TABLE "+dbname1+".mr_sd_tax ADD INDEX  trans_indx (`inv_num`(100) );";
				statement.executeUpdate(query77_3);
			}
			
			//78.	mr_stock_count
			String query78="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_stock_count' ; ";
			ResultSet resultSet78= statement1.executeQuery(query78);
			if(!resultSet78.next())
			{
				String query78_1="CREATE TABLE  "+dbname1+".mr_stock_count (`id` int(255) NOT NULL AUTO_INCREMENT,  `barcode` text NOT NULL,  `item_name` text NOT NULL,  `cost` text NOT NULL,  `qty` double NOT NULL,  `created_date` date NOT NULL,  `status` text NOT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query78_1);
			}
			String query78_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_stock_count' ";
			ResultSet resultSet78_2 = statement1.executeQuery(query78_2);
			if(!resultSet78_2.next())
			{
				String query78_3="ALTER TABLE "+dbname1+".mr_stock_count ADD INDEX  `trans_indx` (`barcode`(100));";
				statement.executeUpdate(query78_3);
			}
			
			
			//79.	mr_stock_count_history
			String query79="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_stock_count_history' ; ";
			ResultSet resultSet79= statement1.executeQuery(query79);
			if(!resultSet79.next())
			{
				String query79_1="CREATE TABLE  "+dbname1+".mr_stock_count_history (`id` int(255) NOT NULL AUTO_INCREMENT,  `barcode` text NOT NULL,  `item_name` text NOT NULL,  `cost` text NOT NULL,  `qty` double NOT NULL,  `created_date` date NOT NULL,  `status` text NOT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query79_1);
			}
			String query79_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_stock_count_history' ";
			ResultSet resultSet79_2 = statement1.executeQuery(query79_2);
			if(!resultSet79_2.next())
			{
				String query79_3="ALTER TABLE "+dbname1+".mr_stock_count_history ADD INDEX  `trans_indx` (`barcode`(100));";
				statement.executeUpdate(query79_3);
			}
			
			//80.	mr_store
			String query80="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_store' and column_name='acc_active'; ";
			ResultSet resultSet80 = statement1.executeQuery(query80);
			if(!resultSet80.next())
			{
				String query80_1="ALTER TABLE "+dbname1+".mr_store ADD COLUMN  `acc_active` int NOT NULL DEFAULT '0'";
				statement.executeUpdate(query80_1);
			}
			String query80_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_store' and column_name='sd_active'; ";
			ResultSet resultSet80_2 = statement1.executeQuery(query80_2);
			if(!resultSet80_2.next())
			{
				String query80_3="ALTER TABLE "+dbname1+".mr_store ADD COLUMN  `sd_active` int NOT NULL DEFAULT '0' AFTER `status`";
				statement.executeUpdate(query80_3);
			}
			String query80_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_store' and column_name='barcode_label'; ";
			ResultSet resultSet80_4 = statement1.executeQuery(query80_4);
			if(!resultSet80_4.next())
			{
				String query80_5="ALTER TABLE "+dbname1+".mr_store ADD COLUMN  `barcode_label` VARCHAR(10) NULL DEFAULT 'Qty';";
				statement.executeUpdate(query80_5);
			}
			String query80_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_store' and column_name='assembly_of_item'; ";
			ResultSet resultSet80_6 = statement1.executeQuery(query80_6);
			if(!resultSet80_6.next())
			{
				String query80_7="ALTER TABLE "+dbname1+".mr_store ADD COLUMN  `assembly_of_item` INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query80_7);
			}
			String query80_8="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_store' and column_name='allow_roundoff'; ";
			ResultSet resultSet80_8 = statement1.executeQuery(query80_8);
			if(!resultSet80_8.next())
			{
				String query80_9="ALTER TABLE "+dbname1+".mr_store ADD COLUMN  `allow_roundoff` INT(11) NOT NULL DEFAULT '1';";
				statement.executeUpdate(query80_9);
			}
			String query80_10="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_store' and column_name='change_hold_type'; ";
			ResultSet resultSet80_10 = statement1.executeQuery(query80_10);
			if(!resultSet80_10.next())
			{
				String query80_11="ALTER TABLE "+dbname1+".mr_store ADD COLUMN  `change_hold_type` INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query80_11);
			}
			
			String query80_12="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_store' ";
			ResultSet resultSet80_12 = statement1.executeQuery(query80_12);
			if(!resultSet80_12.next())
			{
				String query80_13="ALTER TABLE "+dbname1+".mr_store ADD INDEX  `trans_indx` (`store_id`(100));";
				statement.executeUpdate(query80_13);
			}
			
			String query80_14="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_store' and column_name='POcost'; ";
			ResultSet resultSet80_14 = statement1.executeQuery(query80_14);
			if(!resultSet80_14.next())
			{
				String query80_15="ALTER TABLE "+dbname1+".mr_store ADD COLUMN  `POcost` INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query80_15);
			}
			String query80_16="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_store' and column_name='SdPricelock'; ";
			ResultSet resultSet80_16 = statement1.executeQuery(query80_16);
			if(!resultSet80_16.next())
			{
				String query80_17="ALTER TABLE "+dbname1+".mr_store ADD COLUMN  `SdPricelock` INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query80_17);
			}
			String query80_18="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_store' and column_name='ItemCodeAllowChar'; ";
			ResultSet resultSet80_19 = statement1.executeQuery(query80_18);
			if(!resultSet80_19.next())
			{
				String query80_17="ALTER TABLE "+dbname1+".mr_store ADD COLUMN  `ItemCodeAllowChar` INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query80_17);
			}
			
			
			
			//81.	mr_store_accounts
			String query81="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_store_accounts' ";
			ResultSet resultSet81 = statement1.executeQuery(query81);
			if(!resultSet81.next())
			{
				String query81_1="ALTER TABLE "+dbname1+".mr_store_accounts ADD INDEX  `trans_indx` (`ref_store_id`(100));";
				statement.executeUpdate(query81_1);
			}
			
			//82.	mr_store_credit
			String query82="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_store_credit' ";
			ResultSet resultSet82 = statement1.executeQuery(query82);
			if(!resultSet82.next())
			{
				String query82_1="ALTER TABLE "+dbname1+".mr_store_credit ADD INDEX  `trans_indx` (`store_id`(100),ref_invoice_num(100));";
				statement.executeUpdate(query82_1);
			}
			
			//83.	mr_store_paycodes
			String query83="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_store_paycodes' ";
			ResultSet resultSet83 = statement1.executeQuery(query83);
			if(!resultSet83.next())
			{
				String query83_1="ALTER TABLE "+dbname1+".mr_store_paycodes ADD INDEX  `trans_indx` (id,ref_store_id(100));";
				statement.executeUpdate(query83_1);
			}
			
			//84.	mr_subcategory
			String query84="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_subcategory' ";
			ResultSet resultSet84 = statement1.executeQuery(query84);
			if(!resultSet84.next())
			{
				String query84_1="ALTER TABLE "+dbname1+".mr_subcategory ADD INDEX  `trans_indx` (sub_cat_name(100),ref_cat_name(100));";
				statement.executeUpdate(query84_1);
			}
			
			//85.	mr_sub_department
			String query85="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_sub_department' ";
			ResultSet resultSet85 = statement1.executeQuery(query85);
			if(!resultSet85.next())
			{
				String query85_1="ALTER TABLE "+dbname1+".mr_sub_department ADD INDEX  `trans_indx` (sub_dept_name(100),ref_dept_name(100));";
				statement.executeUpdate(query85_1);
			}
			
			//86.	mr_take_away_users
			String query86="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_take_away_users' ; ";
			ResultSet resultSet86= statement1.executeQuery(query86);
			if(!resultSet86.next())
			{
				String query86_1="CREATE TABLE  "+dbname1+".mr_take_away_users (`id` int(255) NOT NULL AUTO_INCREMENT,  `user_name` text NOT NULL,  `status` INT(11) NOT NULL DEFAULT '0',  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				statement.executeUpdate(query86_1);
			}
			String query86_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_take_away_users' ";
			ResultSet resultSet86_2 = statement1.executeQuery(query86_2);
			if(!resultSet86_2.next())
			{
				String query86_3="ALTER TABLE "+dbname1+".mr_take_away_users ADD INDEX  `trans_indx` (user_name(100));";
				statement.executeUpdate(query86_3);
			}
			
			//87.	mr_tax
			String query87="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_tax' ";
			ResultSet resultSet87 = statement1.executeQuery(query87);
			if(!resultSet87.next())
			{
				String query87_1="ALTER TABLE "+dbname1+".mr_tax ADD INDEX  `trans_indx` (tax_code(100));";
				statement.executeUpdate(query87_1);
			}
			
			//88.	mr_tmp_item_master
			String query88="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_tmp_item_master' and column_name='weighing_sts'; ";
			ResultSet resultSet88 = statement1.executeQuery(query88);
			if(!resultSet88.next())
			{
				String query88_1="ALTER TABLE "+dbname1+".mr_tmp_item_master ADD COLUMN  `weighing_sts` TEXT NOT NULL;";
				statement.executeUpdate(query88_1);
			}
			String query88_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_tmp_item_master' and column_name='input_tax_rate'; ";
			ResultSet resultSet88_2 = statement1.executeQuery(query88_2);
			if(!resultSet88_2.next())
			{
				String query88_3="ALTER TABLE "+dbname1+".mr_tmp_item_master ADD COLUMN  `input_tax_rate` INT(11) NOT NULL DEFAULT '0'  ;";
				statement.executeUpdate(query88_3);
			}
			
			String query88_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_tmp_item_master' and column_name='output_tax_rate'; ";
			ResultSet resultSet88_4 = statement1.executeQuery(query88_4);
			if(!resultSet88_4.next())
			{
				String query88_5="ALTER TABLE "+dbname1+".mr_tmp_item_master ADD COLUMN  `output_tax_rate` INT(11) NOT NULL DEFAULT '0'  ;";
				statement.executeUpdate(query88_5);
			}
			String query88_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_tmp_item_master' and column_name='sub_cat'; ";
			ResultSet resultSet88_6 = statement1.executeQuery(query88_6);
			if(!resultSet88_6.next())
			{
				String query88_7="ALTER TABLE "+dbname1+".mr_tmp_item_master ADD COLUMN  `sub_cat` TEXT  NULL;";
				statement.executeUpdate(query88_7);
			}
			String query88_8="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_tmp_item_master' and column_name='sub_dept'; ";
			ResultSet resultSet88_8 = statement1.executeQuery(query88_8);
			if(!resultSet88_8.next())
			{
				String query88_9="ALTER TABLE "+dbname1+".mr_tmp_item_master ADD COLUMN  `sub_dept` TEXT  NULL;";
				statement.executeUpdate(query88_9);
			}
			String query88_10="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_tmp_item_master' and column_name='commited_qty'; ";
			ResultSet resultSet88_10 = statement1.executeQuery(query88_10);
			if(!resultSet88_10.next())
			{
				String query88_11="ALTER TABLE "+dbname1+".mr_tmp_item_master ADD COLUMN  `commited_qty` DOUBLE NOT NULL DEFAULT '0';";
				statement.executeUpdate(query88_11);
			}
			
			String query88_12="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_tmp_item_master' ";
			ResultSet resultSet88_12 = statement1.executeQuery(query88_12);
			if(!resultSet88_12.next())
			{
				String query88_13="ALTER TABLE "+dbname1+".mr_tmp_item_master ADD INDEX  `trans_indx` (`item_code`(100),`type`(100),`barcode`(100));";
				statement.executeUpdate(query88_13);
			}
			String query88_14="ALTER TABLE "+dbname1+".mr_tmp_item_master CHANGE `cf` `cf` INT(20);";
			statement.executeUpdate(query88_14);
			

			String query88_15="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_tmp_item_master' and column_name='fast_item'; ";
			ResultSet resultSet88_16 = statement1.executeQuery(query88_15);
			if(!resultSet88_16.next())
			{
				String query88_17="ALTER TABLE "+dbname1+".mr_tmp_item_master ADD COLUMN  `fast_item` INT(11) NOT NULL DEFAULT '0' ;";
				statement.executeUpdate(query88_17);
			}
			String query88_18="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_tmp_item_master' and column_name='Open_Item'; ";
			ResultSet resultSet88_19 = statement1.executeQuery(query88_18);
			if(!resultSet88_19.next())
			{
				String query88_20="ALTER TABLE "+dbname1+".mr_tmp_item_master ADD COLUMN  `Open_Item` INT(11) NOT NULL DEFAULT '0' ;";
				statement.executeUpdate(query88_20);
			}
			
			String query88_21="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_tmp_item_master' and column_name='color_code'; ";
			ResultSet resultSet88_21 = statement1.executeQuery(query88_21);
			if(!resultSet88_21.next())
			{
				String query88_22="ALTER TABLE "+dbname1+".mr_tmp_item_master ADD COLUMN  `color_code`  TEXT NULL  ;";
				statement.executeUpdate(query88_22);
				String query88_23="update "+dbname1+".mr_tmp_item_master set `color_code` ='#000000'  ;";
				statement.executeUpdate(query88_23);
			}
			
			String query88_24="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_tmp_item_master' and column_name='fore_color_code'; ";
			ResultSet resultSet88_25 = statement1.executeQuery(query88_24);
			if(!resultSet88_25.next())
			{
				String query88_26="ALTER TABLE "+dbname1+".mr_tmp_item_master ADD COLUMN  `fore_color_code`  TEXT NULL  ;";
				statement.executeUpdate(query88_26);
				String query88_27="update "+dbname1+".mr_tmp_item_master set `fore_color_code` ='#ffffff'  ;";
				statement.executeUpdate(query88_27);
			}
			String query88_28="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_tmp_item_master' and column_name='item_vareint'; ";
			ResultSet resultSet88_28 = statement1.executeQuery(query88_28);
			if(!resultSet88_28.next())
			{
				String query88_29="ALTER TABLE "+dbname1+".mr_tmp_item_master ADD COLUMN  `item_vareint`  INT NOT NULL  ;";
				statement.executeUpdate(query88_29);
			}
			String query88_30="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_tmp_item_master' and column_name='openitem_qty'; ";
			ResultSet resultSet88_30 = statement1.executeQuery(query88_30);
			if(!resultSet88_30.next())
			{
				String query88_31="ALTER TABLE "+dbname1+".mr_tmp_item_master ADD COLUMN  `openitem_qty` INT(11) NOT NULL DEFAULT '0'  ;";
				statement.executeUpdate(query88_31);
			}
			
			//89.	mr_transaction_seq
			ResultSet resultSet89 = statement1.executeQuery("select * from  "+dbname1+".mr_transaction_seq where tran_orgin='IN' ");
			if(!resultSet89.next())
			{
				String query89_1="INSERT INTO "+dbname1+".mr_transaction_seq (`tran_name`, `tran_seq`, `tran_store`, `tran_orgin`, `created_date`, `modified_date`) VALUES ('sd_invoice','IN-20-01-000001','S01','IN','2019-01-01','2019-01-01')";
				statement.executeUpdate(query89_1);
			}
			ResultSet resultSet89_2 = statement1.executeQuery("select * from  "+dbname1+".mr_transaction_seq where tran_orgin='QU' ");
			if(!resultSet89_2.next())
			{
				String query89_3="INSERT INTO "+dbname1+".mr_transaction_seq (`tran_name`, `tran_seq`, `tran_store`, `tran_orgin`, `created_date`, `modified_date`) VALUES ('sd_quotation','QU-20-01-000001','S01','QU','2019-01-01','2019-01-01')";
				statement.executeUpdate(query89_3);
			}
			
			String query89_4="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_transaction_seq' ";
			ResultSet resultSet89_4 = statement1.executeQuery(query89_4);
			if(!resultSet89_4.next())
			{
				String query89_5="ALTER TABLE "+dbname1+".mr_transaction_seq ADD INDEX  `trans_indx` (`tran_name`(100),tran_orgin(100));";
				statement.executeUpdate(query89_5);
			}
			
			ResultSet resultSet89_6 = statement1.executeQuery("select * from  "+dbname1+".mr_transaction_seq where tran_orgin='APIN' ");
			if(!resultSet89_6.next())
			{
				String query89_6="INSERT INTO "+dbname1+".mr_transaction_seq (`tran_name`, `tran_seq`, `tran_store`, `tran_orgin`, `created_date`, `modified_date`) VALUES ('AP invoice','APIN-21-01-000001','S01','APIN','2019-01-01','2019-01-01')";
				statement.executeUpdate(query89_6);
			}
			ResultSet resultSet89_7 = statement1.executeQuery("select * from  "+dbname1+".mr_transaction_seq where tran_orgin='TR' ");
			if(!resultSet89_7.next())
			{
				String query89_8="INSERT INTO "+dbname1+".mr_transaction_seq (`tran_name`, `tran_seq`, `tran_store`, `tran_orgin`, `created_date`, `modified_date`) VALUES ('inventory receive','TR-21-01-000001','S01','TR','2019-01-01','2019-01-01')";
				statement.executeUpdate(query89_8);
			}
			ResultSet resultSet89_9 = statement1.executeQuery("select * from  "+dbname1+".mr_transaction_seq where tran_orgin='DN' ");
			if(!resultSet89_9.next())
			{
				String query89_9="INSERT INTO "+dbname1+".mr_transaction_seq (`tran_name`, `tran_seq`, `tran_store`, `tran_orgin`, `created_date`, `modified_date`) VALUES ('Debit Note','DN-21-01-000001','S01','DN','2019-01-01','2019-01-01')";
				statement.executeUpdate(query89_9);
			}
			ResultSet resultSet89_10 = statement1.executeQuery("select * from  "+dbname1+".mr_transaction_seq where tran_orgin='CN' ");
			if(!resultSet89_10.next())
			{
				String query89_10="INSERT INTO "+dbname1+".mr_transaction_seq (`tran_name`, `tran_seq`, `tran_store`, `tran_orgin`, `created_date`, `modified_date`) VALUES ('Credit Note','CN-21-01-000001','S01','CN','2019-01-01','2019-01-01')";
				statement.executeUpdate(query89_10);
			}
			
			
			//90.	mr_users
			String query90="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_users' ";
			ResultSet resultSet90 = statement1.executeQuery(query90);
			if(!resultSet90.next())
			{
				String query90_1="ALTER TABLE "+dbname1+".mr_users ADD INDEX  `trans_indx` (user_code(100),user_name(100));";
				statement.executeUpdate(query90_1);
			}
			String query90_1="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_users' and column_name='mobile'; ";
			ResultSet resultSet90_1 = statement1.executeQuery(query90_1);
			if(!resultSet90_1.next())
			{
				String query90_2="ALTER TABLE "+dbname1+".mr_users ADD COLUMN  `mobile` TEXT NULL ;";
				statement.executeUpdate(query90_2);
			}
			String query90_3="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_user_poscontrol' and column_name='seehistoryinvoice'; ";
			ResultSet resultSet90_3 = statement1.executeQuery(query90_3);
			if(!resultSet90_3.next())
			{
				String query90_4="ALTER TABLE "+dbname1+".mr_user_poscontrol ADD COLUMN  `seehistoryinvoice` INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query90_4);
			}
			String query90_5="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_user_poscontrol' and column_name='seedupinvoice'; ";
			ResultSet resultSet90_5 = statement1.executeQuery(query90_5);
			if(!resultSet90_5.next())
			{
				String query90_6="ALTER TABLE "+dbname1+".mr_user_poscontrol ADD COLUMN  `seedupinvoice` INT(11) NOT NULL DEFAULT '0' ;";
				statement.executeUpdate(query90_6);
			}
			
			//91.	mr_usershift
			String query91="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_usershift' ";
			ResultSet resultSet91 = statement1.executeQuery(query91);
			if(!resultSet91.next())
			{
				String query91_1="ALTER TABLE "+dbname1+".mr_usershift ADD INDEX  `trans_indx` (shift_code(100));";
				statement.executeUpdate(query91_1);
			}
			
			//92.	mr_usertypes
			String query92="update "+dbname1+".mr_usertypes set types='Sales' where types='Guest'";
			statement.executeUpdate(query92);
			ResultSet resultSet92 = statement1.executeQuery("select * from  "+dbname1+".mr_usertypes where types='CashUser' ");
			if(!resultSet92.next())
			{
				String query92_1="insert into  "+dbname1+".mr_usertypes (`types`) VALUES ('CashUser')";
				statement.executeUpdate(query92_1);
			}
			ResultSet resultSet92_2 = statement1.executeQuery("select * from  "+dbname1+".mr_usertypes where types='Delivery Boy' ");
			if(!resultSet92_2.next())
			{
				String query92_3="insert into  "+dbname1+".mr_usertypes (`types`) VALUES ('Delivery Boy')";
				statement.executeUpdate(query92_3);
			}
			String query92_4="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_usertypes' ";
			ResultSet resultSet92_4 = statement1.executeQuery(query92_4);
			if(!resultSet92_4.next())
			{
				String query92_5="ALTER TABLE "+dbname1+".mr_usertypes ADD INDEX  `trans_indx` (types(100));";
				statement.executeUpdate(query92_5);
			}
			
			//93.	mr_user_account_controls
			String query93="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_user_account_controls' ";
			ResultSet resultSet93 = statement1.executeQuery(query93);
			if(!resultSet93.next())
			{
				String query93_1="ALTER TABLE "+dbname1+".mr_user_account_controls ADD INDEX  `trans_indx` (ref_user_code(100));";
				statement.executeUpdate(query93_1);
			}
			
			//94.	mr_user_admincontrol
			String query94="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_user_admincontrol' ";
			ResultSet resultSet94 = statement1.executeQuery(query94);
			if(!resultSet94.next())
			{
				String query94_1="ALTER TABLE "+dbname1+".mr_user_admincontrol ADD INDEX  `trans_indx` (ref_user_code(100));";
				statement.executeUpdate(query94_1);
			}
			
			//95.	mr_user_inventorycontrol
			String query95="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_user_inventorycontrol' ";
			ResultSet resultSet95 = statement1.executeQuery(query95);
			if(!resultSet95.next())
			{
				String query95_1="ALTER TABLE "+dbname1+".mr_user_inventorycontrol ADD INDEX  `trans_indx` (ref_user_code(100));";
				statement.executeUpdate(query95_1);
			}
			
			//96.	mr_user_poscontrol
			String query96="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_user_poscontrol' ";
			ResultSet resultSet96 = statement1.executeQuery(query96);
			if(!resultSet96.next())
			{
				String query96_1="ALTER TABLE "+dbname1+".mr_user_poscontrol ADD INDEX  `trans_indx` (ref_user_code(100));";
				statement.executeUpdate(query96_1);
			}
			
			//97.	mr_user_purchasecontrol
			String query97="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_user_purchasecontrol' ";
			ResultSet resultSet97 = statement1.executeQuery(query97);
			if(!resultSet97.next())
			{
				String query97_1="ALTER TABLE "+dbname1+".mr_user_purchasecontrol ADD INDEX  `trans_indx` (ref_user_code(100));";
				statement.executeUpdate(query97_1);
			}
			
			//98.	mr_vendor
			String query98="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_vendor' and column_name='vendor_ac_name'; ";
			ResultSet resultSet98 = statement1.executeQuery(query98);
			if(!resultSet98.next())
			{
				String query98_1="ALTER TABLE "+dbname1+".mr_vendor ADD COLUMN  `vendor_ac_name`  TEXT  NULL;";
				statement.executeUpdate(query98_1);
			}
			String query98_2="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_vendor' and column_name='open_balance'; ";
			ResultSet resultSet98_2 = statement1.executeQuery(query98_2);
			if(!resultSet98_2.next())
			{
				String query98_3="ALTER TABLE "+dbname1+".mr_vendor ADD COLUMN  `open_balance` DOUBLE NOT NULL DEFAULT '0';";
				statement.executeUpdate(query98_3);
			}
			String query98_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_vendor' and column_name='open_bal_sts'; ";
			ResultSet resultSet98_4 = statement1.executeQuery(query98_4);
			if(!resultSet98_4.next())
			{
				String query98_5="ALTER TABLE "+dbname1+".mr_vendor ADD COLUMN  `open_bal_sts` INT(11) NOT NULL DEFAULT '0';";
				statement.executeUpdate(query98_5);
			}
			String query98_6="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_vendor' ";
			ResultSet resultSet98_6 = statement1.executeQuery(query98_6);
			if(!resultSet98_6.next())
			{
				String query98_7="ALTER TABLE "+dbname1+".mr_vendor ADD INDEX  `trans_indx` (`vendor_code`(100), `vendor_name`(100));";
				statement.executeUpdate(query98_7);
			}

			String query98_8="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_vendor' and column_name='email'; ";
			ResultSet resultSet98_8 = statement1.executeQuery(query98_8);
			if(!resultSet98_8.next())
			{
				String query98_9="ALTER TABLE "+dbname1+".mr_vendor ADD COLUMN  `email` TEXT  NOT NULL;";
				statement.executeUpdate(query98_9);
			}
			String query98_10="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_vendor' and column_name='fax'; ";
			ResultSet resultSet98_10 = statement1.executeQuery(query98_10);
			if(!resultSet98_10.next())
			{
				String query98_11="ALTER TABLE "+dbname1+".mr_vendor ADD COLUMN  `fax`  TEXT  NOT NULL;";
				statement.executeUpdate(query98_11);
			}
			
			//99.	mr_vendor_items
			String query99="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_vendor_items' ";
			ResultSet resultSet99 = statement1.executeQuery(query99);
			if(!resultSet99.next())
			{
				String query99_1="ALTER TABLE "+dbname1+".mr_vendor_items ADD INDEX  `trans_indx` (ref_vendor_id(100));";
				statement.executeUpdate(query99_1);
			}
			
			//100.	mr_vendor_payment
			String query100="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_vendor_payment' ";
			ResultSet resultSet100 = statement1.executeQuery(query100);
			if(!resultSet100.next())
			{
				String query100_1="ALTER TABLE "+dbname1+".mr_vendor_payment ADD INDEX  `trans_indx` (ref_vendor_id(100),ref_po_num(100));";
				statement.executeUpdate(query100_1);
			}
			
			//101.	mr_vendor_payment_details
			String query101="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_vendor_payment_details' and column_name='status'; ";
			ResultSet resultSet101 = statement1.executeQuery(query101);
			if(!resultSet101.next())
			{
				String query101_1="ALTER TABLE "+dbname1+".mr_vendor_payment_details ADD COLUMN  `status` TEXT NULL ;";
				statement.executeUpdate(query101_1);
			}
			String query101_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_vendor_payment_details' ";
			ResultSet resultSet101_2 = statement1.executeQuery(query101_2);
			if(!resultSet101_2.next())
			{
				String query101_1="ALTER TABLE "+dbname1+".mr_vendor_payment_details ADD INDEX  `trans_indx` (ref_vendor_id(100),ref_po_num(100),trans_num(100));";
				statement.executeUpdate(query101_1);
			}
			String query101_4="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_vendor_payment_details' and column_name='custbankname'; ";
			ResultSet resultSet101_4 = statement1.executeQuery(query101_4);
			if(!resultSet101_4.next())
			{
				String query101_5="ALTER TABLE "+dbname1+".mr_vendor_payment_details ADD COLUMN  `custbankname` TEXT NULL;";
				statement.executeUpdate(query101_5);
			}
			String query101_6="SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_vendor_payment_details' and column_name='invoicetype'; ";
			ResultSet resultSet101_6 = statement1.executeQuery(query101_6);
			if(!resultSet101_6.next())
			{
				String query101_7="ALTER TABLE "+dbname1+".mr_vendor_payment_details ADD COLUMN  `invoicetype` TEXT NULL;";
				statement.executeUpdate(query101_7);
			}
			
			//102.	mr_vendor_return_payment
			String query102="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_vendor_return_payment' ";
			ResultSet resultSet102 = statement1.executeQuery(query102);
			if(!resultSet102.next())
			{
				String query102_1="ALTER TABLE "+dbname1+".mr_vendor_return_payment ADD INDEX  `trans_indx` (ref_vendor_id(100),ref_pr_no(100));";
				statement.executeUpdate(query102_1);
			}
			
			//103.	mr_vendor_summary
			String query103="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_vendor_summary' ";
			ResultSet resultSet103 = statement1.executeQuery(query103);
			if(!resultSet103.next())
			{
				String query103_1="ALTER TABLE "+dbname1+".mr_vendor_summary ADD INDEX  `trans_indx` (ref_vendor_id(100),ref_po_num(100));";
				statement.executeUpdate(query103_1);
			}
			
			//104 mr_invoice
			String query104="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_invoice' ; ";
			ResultSet resultSet104= statement1.executeQuery(query104);
			if(!resultSet104.next())
			{
				String query104_1="CREATE TABLE "+dbname1+".mr_invoice (  `id` int(255) NOT NULL AUTO_INCREMENT,  `invoice_num` TEXT NULL,  `branch` VARCHAR(45) NULL,  `inv_date` DATE NULL,  `username` VARCHAR(45) NULL,  `doc_num` VARCHAR(45) NULL,  `remark` TEXT NULL,  `supp_code` VARCHAR(45) NULL,  `supp_name` VARCHAR(45) NULL,  `inv_amt` DOUBLE NULL,  `disc_amt` DOUBLE NULL,  `vat_type` VARCHAR(45) NULL,  `vat_amt` DOUBLE NULL,  `net_total` DOUBLE NULL,  `acc_no` VARCHAR(45) NULL,  `acc_desc` VARCHAR(45) NULL,  `created_date` DATE NULL,  `modified_date` DATE NULL,  `status` VARCHAR(45) NULL,   `type` VARCHAR(45) NULL,   `store` VARCHAR(45) NULL,  PRIMARY KEY (`id`))ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";
				statement.executeUpdate(query104_1);
			}
			
			String query104_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_invoice' ";
			ResultSet resultSet104_2 = statement1.executeQuery(query104_2);
			if(!resultSet104_2.next())
			{
				String query104_3="ALTER TABLE "+dbname1+".mr_invoice  ADD INDEX  `trans_indx` (invoice_num(100));";
				statement.executeUpdate(query104_3);
			}
			
			//105 mr_invoice_history
			String query105="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_invoice_history' ; ";
			ResultSet resultSet105= statement1.executeQuery(query105);
			if(!resultSet105.next())
			{
				String query105_1="CREATE TABLE "+dbname1+".mr_invoice_history (  `id` int(255) NOT NULL AUTO_INCREMENT,  `invoice_num` TEXT NULL,  `branch` VARCHAR(45) NULL,  `inv_date` DATE NULL,  `username` VARCHAR(45) NULL,  `doc_num` VARCHAR(45) NULL,  `remark` TEXT NULL,  `supp_code` VARCHAR(45) NULL,  `supp_name` VARCHAR(45) NULL,  `inv_amt` DOUBLE NULL,  `disc_amt` DOUBLE NULL,  `vat_type` VARCHAR(45) NULL,  `vat_amt` DOUBLE NULL,  `net_total` DOUBLE NULL,  `acc_no` VARCHAR(45) NULL,  `acc_desc` VARCHAR(45) NULL,  `created_date` DATE NULL,  `modified_date` DATE NULL,  `status` VARCHAR(45) NULL,   `type` VARCHAR(45) NULL,   `store` VARCHAR(45) NULL,  PRIMARY KEY (`id`))ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";
				statement.executeUpdate(query105_1);
			}
			
			String query105_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_invoice_history' ";
			ResultSet resultSet105_2 = statement1.executeQuery(query105_2);
			if(!resultSet105_2.next())
			{
				String query105_3="ALTER TABLE "+dbname1+".mr_invoice_history ADD INDEX  trans_indx (`invoice_num`(100) );";
				statement.executeUpdate(query105_3);
			}
			
			//106 mr_inventory_transfer_list
			String query106="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_inventory_transfer_list' ; ";
			ResultSet resultSet106= statement1.executeQuery(query106);
			if(!resultSet106.next())
			{
				String query106_1="CREATE TABLE "+dbname1+".mr_inventory_transfer_list (  `id` int(255) NOT NULL AUTO_INCREMENT,  `com_date` date NOT NULL, `store_from` text NOT NULL,`store_to` text NOT NULL,  `tran_date` date NOT NULL,  `user_id` text NOT NULL,  `tran_no` text NOT NULL,   `recv_no` text NULL,  `invo_nu` text NOT NULL,  `ttl_items` int(11) NOT NULL,  `ttl_price` double NOT NULL,  `sub_ttl_amt` double NOT NULL,  `assume_ttl_cst` int(11) NOT NULL DEFAULT '0',  `status` text NOT NULL,  `tran_type` text NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `remark` text,  PRIMARY KEY (`id`))ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";
				statement.executeUpdate(query106_1);
			}
			
			String query106_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_inventory_transfer_list' ";
			ResultSet resultSet106_2 = statement1.executeQuery(query106_2);
			if(!resultSet106_2.next())
			{
				String query106_3="ALTER TABLE "+dbname1+".mr_inventory_transfer_list ADD INDEX  trans_indx (`tran_no`(100),recv_no(100) );";
				statement.executeUpdate(query106_3);
			}
			
			//107 mr_inventory_transfer_details
			String query107="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_inventory_transfer_details' ; ";
			ResultSet resultSet107= statement1.executeQuery(query107);
			if(!resultSet107.next())
			{
				String query107_1="CREATE TABLE "+dbname1+".mr_inventory_transfer_details ( `id` int(255) NOT NULL AUTO_INCREMENT,  `ref_tran_num` text NOT NULL,  `barcode` text NOT NULL,  `item_code` text NOT NULL,  `qtyOh` double NOT NULL,  `unit` text NOT NULL,  `cf` double NOT NULL,  `unit_cost` double NOT NULL,  `qty` double NOT NULL,`total` double NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `item_name` text,  PRIMARY KEY (`id`))ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";
				statement.executeUpdate(query107_1);
			}
			
			String query107_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_inventory_transfer_details' ";
			ResultSet resultSet107_2 = statement1.executeQuery(query107_2);
			if(!resultSet107_2.next())
			{
				String query106_3="ALTER TABLE "+dbname1+".mr_inventory_transfer_details ADD INDEX  trans_indx (`ref_tran_num`(100) );";
				statement.executeUpdate(query106_3);
			}
			
			//108 mr_inventory_transfer_list_history
			String query108="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_inventory_transfer_list_history' ; ";
			ResultSet resultSet108= statement1.executeQuery(query108);
			if(!resultSet108.next())
			{
				String query108_1="CREATE TABLE "+dbname1+".mr_inventory_transfer_list_history (  `id` int(255) NOT NULL AUTO_INCREMENT,  `com_date` date NOT NULL, `store_from` text NOT NULL,`store_to` text NOT NULL,  `tran_date` date NOT NULL,  `user_id` text NOT NULL,  `tran_no` text NOT NULL,`recv_no` text NULL,  `invo_nu` text NOT NULL,  `ttl_items` int(11) NOT NULL,  `ttl_price` double NOT NULL,  `sub_ttl_amt` double NOT NULL,  `assume_ttl_cst` int(11) NOT NULL DEFAULT '0',  `status` text NOT NULL,  `tran_type` text NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `remark` text,  PRIMARY KEY (`id`))ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";
				statement.executeUpdate(query108_1);
			}
			
			String query108_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_inventory_transfer_list_history' ";
			ResultSet resultSet108_2 = statement1.executeQuery(query108_2);
			if(!resultSet108_2.next())
			{
				String query108_3="ALTER TABLE "+dbname1+".mr_inventory_transfer_list_history ADD INDEX  trans_indx (`tran_no`(100),recv_no(100) );";
				statement.executeUpdate(query108_3);
			}
			
			//109 mr_inventory_transfer_details_history
			String query109="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_inventory_transfer_details_history' ; ";
			ResultSet resultSet109= statement1.executeQuery(query109);
			if(!resultSet109.next())
			{
				String query109_1="CREATE TABLE "+dbname1+".mr_inventory_transfer_details_history ( `id` int(255) NOT NULL AUTO_INCREMENT,  `ref_tran_num` text NOT NULL,  `barcode` text NOT NULL,  `item_code` text NOT NULL,  `qtyOh` double NOT NULL,  `unit` text NOT NULL,  `cf` double NOT NULL,  `unit_cost` double NOT NULL,  `qty` double NOT NULL,`total` double NOT NULL,  `created_date` date NOT NULL,  `modified_date` date NOT NULL,  `item_name` text,  PRIMARY KEY (`id`))ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";
				statement.executeUpdate(query109_1);
			}
			
			String query109_2="SELECT TABLE_NAME, COLUMN_NAME, INDEX_NAME FROM INFORMATION_SCHEMA.STATISTICS WHERE TABLE_SCHEMA = '"+dbname1+"' and INDEX_NAME='trans_indx' and TABLE_NAME='mr_inventory_transfer_details_history' ";
			ResultSet resultSet109_2 = statement1.executeQuery(query109_2);
			if(!resultSet109_2.next())
			{
				String query109_3="ALTER TABLE "+dbname1+".mr_inventory_transfer_details_history ADD INDEX  trans_indx (`ref_tran_num`(100) );";
				statement.executeUpdate(query109_3);
			}
			
			String query110="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_comments' ; ";
			ResultSet resultSet110= statement1.executeQuery(query110);
			if(!resultSet110.next())
			{
				String query110_1="CREATE TABLE "+dbname1+".mr_pos_comments ( `id` int(255) NOT NULL AUTO_INCREMENT,  `comments` text NOT NULL,  PRIMARY KEY (`id`))ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";
				statement.executeUpdate(query110_1);
			}
			
			String query111="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.tables WHERE TABLE_SCHEMA='"+dbname1+"' AND TABLE_NAME='mr_pos_payreasons' ; ";
			ResultSet resultSet111= statement1.executeQuery(query111);
			if(!resultSet111.next())
			{
				String query111_1="CREATE TABLE "+dbname1+".mr_pos_payreasons ( `id` int(255) NOT NULL AUTO_INCREMENT,  `type` text NOT NULL,`reason` text NOT NULL,  PRIMARY KEY (`id`))ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";
				statement.executeUpdate(query111_1);
			}
			
			
		}
		catch(Exception e)
		{
			frame.setCursor(Cursor.getDefaultCursor());
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
	}

	private void submit_btn(String combo, String store,String posuser,String version,String server, String activeSD, String acc_active, String language)
	{
		try {
			String pass_string=hiddncard.getText();
			if(comboBox.getSelectedIndex()==-1)
			{
				JOptionPane.showMessageDialog(frame, "Select Company Name");
			}
			else if(pass_string.equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Please Enter swipe card ID");
			}
			else
			{
				 InetAddress addr = null;
				    try {
						addr = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    String sys_name = addr.getHostName();
				    LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
				    final String formattedDate = myDateObj.format(myFormatObj);
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				Statement statement1=con.createStatement();  
				ResultSet resultSet = statement.executeQuery("select db_name from config_db.company where company_name='"+combo+"' ");
				if(resultSet.next())
				{
					String db1=resultSet.getString("db_name");
					ResultSet resultSet1 = statement.executeQuery("select user_name,language from "+db1+".mr_users where swipe_id='"+pass_string+"' and status='1' ");
					if(resultSet1.next())
					{
						String username=resultSet1.getString("user_name").toUpperCase();
						String lang=resultSet1.getString("language");
						String qu="select * from "+db1+".mr_loggin_controls where username='"+resultSet1.getString("user_name")+"' and status='0' and not "+db1+".mr_loggin_controls.device_name='"+sys_name+"' ";
						ResultSet resultSet3 = statement1.executeQuery(qu);
						if(resultSet3.next())
						{
							JOptionPane.showMessageDialog(frame, "Already Loggined this user in "+resultSet3.getString("device_name"));
							passwordField.setText("");
							textField_1.setText("");
						}
						else
						{
							String qu2="select * from "+db1+".mr_loggin_controls where username='"+resultSet1.getString("user_name")+"' and status='0' and "+db1+".mr_loggin_controls.device_name='"+sys_name+"' ";
							ResultSet resultSet13 = statement1.executeQuery(qu2);
							if(resultSet13.next())
							{
								String qu1="update "+db1+".mr_loggin_controls set status='1',modified_date='"+formattedDate+"' where username='"+resultSet13.getString("username")+"' and status='0' and "+db1+".mr_loggin_controls.device_name='"+sys_name+"' ";
								statement1.executeUpdate(qu1);
								String query="insert into "+db1+".mr_loggin_controls (username,"+db1+".mr_loggin_controls.device_name,created_date,modified_date) values('"+resultSet1.getString("user_name")+"','"+sys_name+"','"+formattedDate+"','"+formattedDate+"')";
								statement1.executeUpdate(query);
								ResultSet resultSet2 = statement1.executeQuery("select decimal_count from "+db1+".mr_store where NoofDecimal='1' ");
								if(resultSet2.next())
								{
									decimal=resultSet2.getString("decimal_count");
								}
								frame.setVisible(false);
								new Mainpage(combo,server,username, store, db1, posuser, version,decimal,activeSD,acc_active,lang);
							}
							else
							{
								String query="insert into "+db1+".mr_loggin_controls (username,"+db1+".mr_loggin_controls.device_name,created_date,modified_date) values('"+resultSet1.getString("user_name")+"','"+sys_name+"','"+formattedDate+"','"+formattedDate+"')";
								statement1.executeUpdate(query);
								ResultSet resultSet2 = statement1.executeQuery("select decimal_count from "+db1+".mr_store where NoofDecimal='1' ");
								if(resultSet2.next())
								{
									decimal=resultSet2.getString("decimal_count");
								}
								frame.setVisible(false);
								new Mainpage(combo,server, username, store, db1, posuser, version,decimal,activeSD,acc_active,lang);
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Swipe ID not valid");
						hiddncard.setText("");
			        	textField_1.setText("");
					}
				}
				con.close();
			}
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
	}
	private void click_btn(String num)
	{
		try{
			String old_text=textField_1.getText();
			old_text+="*";
			textField_1.setText(old_text);
			String pass_string=hiddncard.getText();
			pass_string+=num;
			hiddncard.setText(pass_string);
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	public void validation(String userName, String password ,String combo, String store,String posuser,String version,String server, String activeSD, String acc_active, String language){		
		try {
			if(userName.length()>0)
			{
				if(password.isEmpty())
				{
					JOptionPane.showMessageDialog(textField, "Plaese, Enter the Password");
				}
				else
				{
					InetAddress addr = null;
				    try {
						addr = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    String sys_name = addr.getHostName();
				    LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
				    final String formattedDate = myDateObj.format(myFormatObj);
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement();  
					Statement statement1=con.createStatement();  
					ResultSet resultSet = statement.executeQuery("select db_name from config_db.company where company_name='"+combo+"' ");
					if(resultSet.next())
					{
						String db1=resultSet.getString("db_name");
						ResultSet resultSet1 = statement.executeQuery("select * from "+db1+".mr_users where user_name='"+userName+"'  and status='1' ");
						if(resultSet1.next())
						{
							if(resultSet1.getString("password").equals(password))
							{
								String username=resultSet1.getString("user_name").toUpperCase();
								String lang=resultSet1.getString("language").toUpperCase();
								String qu="select * from "+db1+".mr_loggin_controls where username='"+resultSet1.getString("user_name")+"' and status='0' and not "+db1+".mr_loggin_controls.device_name='"+sys_name+"' ";
								ResultSet resultSet3 = statement1.executeQuery(qu);
								if(resultSet3.next())
								{
									JOptionPane.showMessageDialog(frame, "Already Loggined this user in "+resultSet3.getString("device_name"));
									passwordField.setText("");
									textField_1.setText("");
								}
								else
								{
									String qu2="select * from "+db1+".mr_loggin_controls where username='"+resultSet1.getString("user_name")+"' and status='0' and "+db1+".mr_loggin_controls.device_name='"+sys_name+"' ";
									ResultSet resultSet13 = statement1.executeQuery(qu2);
									if(resultSet13.next())
									{
										String qu1="update "+db1+".mr_loggin_controls set status='1',modified_date='"+formattedDate+"' where username='"+resultSet13.getString("username")+"' and status='0' and "+db1+".mr_loggin_controls.device_name='"+sys_name+"' ";
										statement1.executeUpdate(qu1);
										String query="insert into "+db1+".mr_loggin_controls (username,"+db1+".mr_loggin_controls.device_name,created_date,modified_date) values('"+resultSet1.getString("user_name")+"','"+sys_name+"','"+formattedDate+"','"+formattedDate+"')";
										statement1.executeUpdate(query);
										ResultSet resultSet2 = statement1.executeQuery("select decimal_count from "+db1+".mr_store where NoofDecimal='1' ");
										if(resultSet2.next())
										{
											decimal=resultSet2.getString("decimal_count");
										}
										frame.setVisible(false);
										new Mainpage(combo,server, username, store, db1, posuser, version,decimal,activeSD,acc_active,lang);
									}
									else
									{
										String query="insert into "+db1+".mr_loggin_controls (username,"+db1+".mr_loggin_controls.device_name,created_date,modified_date) values('"+resultSet1.getString("user_name")+"','"+sys_name+"','"+formattedDate+"','"+formattedDate+"')";
										statement1.executeUpdate(query);
										ResultSet resultSet2 = statement1.executeQuery("select decimal_count from "+db1+".mr_store where NoofDecimal='1' ");
										if(resultSet2.next())
										{
											decimal=resultSet2.getString("decimal_count");
										}
										frame.setVisible(false);
										new Mainpage(combo,server, username, store, db1, posuser, version,decimal,activeSD,acc_active,lang);
									}
								}
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Password not match for this users");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Enter User Name And Password is not match");
							hiddncard.setText("");
				        	textField_1.setText("");
						}
					}
					con.close();
				}
			}
			else
			{
				String super_admin="MohdShabe815";
				if(password.equals(super_admin))
				{
					
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement();  
					Statement statement1=con.createStatement();  
					ResultSet resultSet = statement.executeQuery("select db_name from config_db.company where company_name='"+combo+"' ");
					if(resultSet.next())
					{
						String db1=resultSet.getString("db_name");
						ResultSet resultSet2 = statement1.executeQuery("select decimal_count from "+db1+".mr_store where NoofDecimal='1' ");
						if(resultSet2.next())
						{
							decimal=resultSet2.getString("decimal_count");
						}
						frame.setVisible(false);
						new Mainpage(combo, server, "SUPER ADMIN", store, db1, posuser, version,decimal,activeSD,acc_active,"English");
					}
					con.close();
				}
				else
				{
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
				    final String formattedDate = myDateObj.format(myFormatObj);
					String pass_string=password;
					if(comboBox.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(frame, "Select Company Name");
					}
					else if(pass_string.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Please Enter swipe card ID");
					}
					else
					{
						InetAddress addr = null;
					    try {
							addr = InetAddress.getLocalHost();
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    String sys_name = addr.getHostName();
					    Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						Statement statement1=con.createStatement();  
						ResultSet resultSet = statement.executeQuery("select db_name from config_db.company where company_name='"+combo+"' ");
						if(resultSet.next())
						{
							String db1=resultSet.getString("db_name");
							ResultSet resultSet1 = statement.executeQuery("select user_name,language from "+db1+".mr_users where swipe_id='"+pass_string+"' ");
							if(resultSet1.next())
							{
								String username=resultSet1.getString("user_name").toUpperCase();
								String lang=resultSet1.getString("language");
								String qu="select * from "+db1+".mr_loggin_controls where username='"+resultSet1.getString("user_name")+"' and status='0' and not "+db1+".mr_loggin_controls.device_name='"+sys_name+"' ";
								ResultSet resultSet3 = statement1.executeQuery(qu);
								if(resultSet3.next())
								{
									JOptionPane.showMessageDialog(frame, "Already Loggined this user in "+resultSet3.getString("device_name"));
									passwordField.setText("");
									textField_1.setText("");
								}
								else
								{
									String qu2="select * from "+db1+".mr_loggin_controls where username='"+resultSet1.getString("user_name")+"' and status='0' and "+db1+".mr_loggin_controls.device_name='"+sys_name+"' ";
									ResultSet resultSet13 = statement1.executeQuery(qu2);
									if(resultSet13.next())
									{
										String qu1="update "+db1+".mr_loggin_controls set status='1',modified_date='"+formattedDate+"' where username='"+resultSet13.getString("username")+"' and status='0' and "+db1+".mr_loggin_controls.device_name='"+sys_name+"' ";
										statement1.executeUpdate(qu1);
										String query="insert into "+db1+".mr_loggin_controls (username,"+db1+".mr_loggin_controls.device_name,created_date,modified_date) values('"+resultSet1.getString("user_name")+"','"+sys_name+"','"+formattedDate+"','"+formattedDate+"')";
										statement1.executeUpdate(query);
										ResultSet resultSet2 = statement1.executeQuery("select decimal_count from "+db1+".mr_store where NoofDecimal='1' ");
										if(resultSet2.next())
										{
											decimal=resultSet2.getString("decimal_count");
										}
										frame.setVisible(false);
										new Mainpage(combo,server, username, store, db1, posuser, version,decimal,activeSD,acc_active,lang);
									}
									else
									{
										String query="insert into "+db1+".mr_loggin_controls (username,"+db1+".mr_loggin_controls.device_name,created_date,modified_date) values('"+resultSet1.getString("user_name")+"','"+sys_name+"','"+formattedDate+"','"+formattedDate+"')";
										statement1.executeUpdate(query);
										ResultSet resultSet2 = statement1.executeQuery("select decimal_count from "+db1+".mr_store where NoofDecimal='1' ");
										if(resultSet2.next())
										{
											decimal=resultSet2.getString("decimal_count");
										}
										frame.setVisible(false);
										new Mainpage(combo,server, username, store, db1, posuser, version,decimal,activeSD,acc_active,lang);
									}
								}
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Swipe ID not valid");
								hiddncard.setText("");
					        	textField_1.setText("");
							}
						}
						con.close();
					}
				}
				
			}
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
	}
	private void clear_btn()
	{
		try{
			String old_text=textField_1.getText();
			int old_textln=old_text.length()-1;
			String old_text1 = "";
			for(int i=0;i<old_textln;i++)
			{
				old_text1+="*";
			}
			textField_1.setText(old_text1);
			hiddncard.setText(hiddncard.getText().substring(0, hiddncard.getText().length() - 1));
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	public static void get_register_name(String company,String register, String store_name,Connection con){
		try {
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select db_name from config_db.company where company_name='"+company+"' ");
			if(resultSet.next())
			{
				String db1=resultSet.getString("db_name");
				ResultSet resultSet2 = statement.executeQuery("select * from "+db1+".mr_register_setup where register_number='"+register+"' ");
				if(resultSet2.next())
				{
					display_port=resultSet2.getString("pole_display_port");
					logout_msg1=resultSet2.getString("logout_msg1");
					
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "REGISTER NOT FOUND");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "REGISTER NOT FOUND");
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}	
}
