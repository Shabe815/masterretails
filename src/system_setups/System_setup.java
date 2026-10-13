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
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.json.JSONException;

import system_setups.Store;
import system_setups.company_setup;
import system_setups.user_setup;

public class System_setup {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System_setup window = new System_setup("mr_pro", "admin", null, null,"");
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
	public System_setup(String company,String username,String version,String company_name,String language) {
		initialize(company,  username, version, company_name,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 */
	private void initialize(final String company,final String username,final String version,final String company_name, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("1.SYSTEM SETUP");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(25, 61, 220, 26);
		
		frame.getContentPane().add(lblNewLabel);
		
		Image user = new ImageIcon(this.getClass().getResource("/user1.png")).getImage();		
		JButton btnNewButton = new JButton("1. USERS");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new user_setup(company, version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(user));
		btnNewButton.setBounds(46, 124, 230, 38);
		frame.getContentPane().add(btnNewButton);
		
		Image store = new ImageIcon(this.getClass().getResource("/shop1.png")).getImage();		
		JButton btnInve = new JButton("3. COMAPNY SETUP");
		btnInve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new company_setup(version, company_name);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnInve.setHorizontalAlignment(SwingConstants.LEFT);
		btnInve.setIcon(new ImageIcon(store));
		btnInve.setBackground(new Color(240, 240, 240));
		btnInve.setBounds(594, 124, 230, 38);
		frame.getContentPane().add(btnInve);
		
		Image settings = new ImageIcon(this.getClass().getResource("/Settings1.png")).getImage();		
		JButton btnStockCount = new JButton("2. USER AUTHORIZATION");
		btnStockCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new Itemmaster();
			}
		});
		btnStockCount.setIcon(new ImageIcon(settings));
		btnStockCount.setHorizontalAlignment(SwingConstants.LEFT);
		btnStockCount.setBackground(SystemColor.menu);
		btnStockCount.setBounds(319, 124, 230, 38);
		frame.getContentPane().add(btnStockCount);

		
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
		
		JLabel lblCurrentUser = new JLabel("CURRENT USER "+username);
		lblCurrentUser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCurrentUser.setBounds(31, 437, 161, 26);
		frame.getContentPane().add(lblCurrentUser);
		
		JLabel lblRegistredToCompany = new JLabel("REGISTERED TO "+company);
		lblRegistredToCompany.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegistredToCompany.setBounds(366, 452, 516, 14);
		frame.getContentPane().add(lblRegistredToCompany);
		
		JLabel lblNewLabel_1 = new JLabel(" Cafepro Version 1.1.0");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(697, 479, 185, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		final JLabel timeLabel  = new JLabel("");
		timeLabel .setBounds(24, 474, 293, 16);
		frame.getContentPane().add(timeLabel );
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 49, 1018, 2);
		frame.getContentPane().add(separator_1);
		
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
		
		JButton btnSubCategory = new JButton("4. CHNAGE MY PASSWORD");
		btnSubCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Change_Pwd(company,username, version);
			}
		});
		btnSubCategory.setIcon(new ImageIcon(settings));
		btnSubCategory.setHorizontalAlignment(SwingConstants.LEFT);
		btnSubCategory.setBackground(SystemColor.menu);
		btnSubCategory.setBounds(46, 199, 230, 38);
		frame.getContentPane().add(btnSubCategory);
		
		Image store1 = new ImageIcon(this.getClass().getResource("/store.png")).getImage();		
		JButton btnStoreSetup = new JButton("5. STORE SETUP");
		btnStoreSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Store(company,username, version, company_name, language);
			}
		});
		btnStoreSetup.setHorizontalAlignment(SwingConstants.LEFT);
		btnStoreSetup.setBackground(SystemColor.menu);
		btnStoreSetup.setIcon(new ImageIcon(store1));
		btnStoreSetup.setBounds(319, 199, 230, 38);
		frame.getContentPane().add(btnStoreSetup);
		
		
		final DateFormat timeFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss a");
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


