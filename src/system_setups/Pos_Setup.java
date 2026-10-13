package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JMenuItem;

import org.json.JSONException;

import system_setups.Store;

public class Pos_Setup {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pos_Setup window = new Pos_Setup("mr_pro","admin", null, null,"");
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
	public Pos_Setup(String company, String username,String version,String company_name,String language) {
		initialize(company,  username, version, company_name,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param language 
	 */
	private void initialize(final String company,final String username,final String version,final String company_name, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("2.POS SETUP");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(25, 61, 220, 26);
		
		frame.getContentPane().add(lblNewLabel);
		
		
		Image shop = new ImageIcon(this.getClass().getResource("/shop1.png")).getImage();
		JButton btnNewButton = new JButton("1.STORE");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Store(company, username, version,company_name, language);
			}
		});
		btnNewButton.setIcon(new ImageIcon(shop));
		btnNewButton.setBounds(46, 124, 230, 38);
		frame.getContentPane().add(btnNewButton);
		
		Image pos = new ImageIcon(this.getClass().getResource("/Cash-register.png")).getImage();
		JButton btnInve = new JButton("2. POS REGISTER");
		btnInve.addActionListener(new ActionListener() {
			@SuppressWarnings("rawtypes")
			public void actionPerformed(ActionEvent arg0) {
				try {
					new system_setups.Registers(company, username, version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnInve.setHorizontalAlignment(SwingConstants.LEFT);
		btnInve.setIcon(new ImageIcon(pos));
		btnInve.setBackground(new Color(240, 240, 240));
		btnInve.setBounds(319, 124, 230, 38);
		frame.getContentPane().add(btnInve);

		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		 
	    frame.setVisible(true);
	
	    frame.setResizable(false);
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 437, 1018, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel1 = new JLabel("");
		//lblNewLabel1.setIcon(new ImageIcon(cafepro));
		lblNewLabel1.setBounds(0, 683, 58, 50);
		frame.getContentPane().add(lblNewLabel1);
		
		JLabel lblCurrentUser = new JLabel("CURRENT USER ");
		lblCurrentUser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCurrentUser.setBounds(31, 437, 161, 26);
		frame.getContentPane().add(lblCurrentUser);
		
		JLabel lblRegistredToCompany = new JLabel("REGISTERED TO ");
		lblRegistredToCompany.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegistredToCompany.setBounds(280, 456, 516, 14);
		frame.getContentPane().add(lblRegistredToCompany);
		
		JLabel lblNewLabel_1 = new JLabel(" Cafepro Version 1.1.0");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(402, 481, 394, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		final JLabel timeLabel  = new JLabel("");
		timeLabel .setBounds(24, 474, 293, 16);
		frame.getContentPane().add(timeLabel );
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 49, 1018, 2);
		frame.getContentPane().add(separator_1);
		
		Image Settings = new ImageIcon(this.getClass().getResource("/Settings.png")).getImage();
		JButton btnSubCategory = new JButton("4. ASSIGN REGISTER");
		btnSubCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Assign(company,version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSubCategory.setIcon(new ImageIcon(Settings));
		btnSubCategory.setHorizontalAlignment(SwingConstants.LEFT);
		btnSubCategory.setBackground(SystemColor.menu);
		btnSubCategory.setBounds(46, 199, 230, 38);
		frame.getContentPane().add(btnSubCategory);
		
		Image shift = new ImageIcon(this.getClass().getResource("/shift.png")).getImage();
		JButton btnWorkingShifts = new JButton("3. WORKING SHIFTS");
		btnWorkingShifts.setIcon(new ImageIcon(shift));
		btnWorkingShifts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new system_setups.Working_Shift(company, username, version);
			}
		});
		btnWorkingShifts.setHorizontalAlignment(SwingConstants.LEFT);
		btnWorkingShifts.setBackground(SystemColor.menu);
		btnWorkingShifts.setBounds(598, 124, 230, 38);
		frame.getContentPane().add(btnWorkingShifts);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(0, 0, 60, 48);
		frame.getContentPane().add(menuItem);
		
		final DateFormat timeFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
        ActionListener timerListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Date date = new Date();
                String time = timeFormat.format(date);
                timeLabel.setText(time);
            }
        };
        Timer timer = new Timer(1000, timerListener);
        timer.setInitialDelay(0);
        timer.start();
	}
	}

