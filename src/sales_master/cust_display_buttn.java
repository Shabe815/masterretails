package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
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

import sales_distribution.cust_balance;
import sales_distribution.sd_pending_list;

public class cust_display_buttn {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cust_display_buttn window = new cust_display_buttn(null, null, null, null, null,"");
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
	public cust_display_buttn(String company, String username, String reg_nme, String store_name,String version,String language) {
		initialize(company,username,reg_nme,store_name, version, language);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company,final String username,final String reg_nme,final String store_name,final String version,final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("2. CUSTOMER");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(25, 61, 220, 26);
		
		frame.getContentPane().add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("1. NEW CUSTOMER");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new Itemmaster();
				new sales_master.Customer_master(company,username,reg_nme,store_name, version,"",language);
			}
		});
		btnNewButton.setBounds(46, 124, 230, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnInve = new JButton("2. SD CUSTOMER PAYMENT");
		btnInve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new sales_master.customer_payment(company, username, store_name, "", version,"","SD");
				try {
					new sales_distribution.Cust_voucher_list(company,username,store_name, version,"2", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnInve.setHorizontalAlignment(SwingConstants.LEFT);
		btnInve.setBackground(new Color(240, 240, 240));
		btnInve.setBounds(322, 124, 230, 38);
		frame.getContentPane().add(btnInve);

		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		 
	    frame.setVisible(true);
	
	    frame.setResizable(false);
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JLabel lblNewLabel1 = new JLabel("");
		//lblNewLabel1.setIcon(new ImageIcon(cafepro));
		lblNewLabel1.setBounds(0, 683, 58, 50);
		frame.getContentPane().add(lblNewLabel1);
		
		final JLabel timeLabel  = new JLabel("");
		timeLabel .setBounds(24, 474, 293, 16);
		frame.getContentPane().add(timeLabel );
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 49, 1018, 2);
		frame.getContentPane().add(separator_1);
		
		JButton btnDepartment = new JButton("3. SD CUSTOMER SUMMARY");
		btnDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new customer_statement(company, username, store_name,  reg_nme, version,"");
			}
		});
		btnDepartment.setHorizontalAlignment(SwingConstants.LEFT);
		btnDepartment.setBackground(SystemColor.menu);
		btnDepartment.setBounds(604, 124, 230, 38);
		frame.getContentPane().add(btnDepartment);
		
		JButton btnSubDepartment = new JButton("4. CUSTOMER BALANCE SHEET");
		btnSubDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new cust_balance(company,username,store_name, version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSubDepartment.setHorizontalAlignment(SwingConstants.LEFT);
		btnSubDepartment.setBackground(SystemColor.menu);
		btnSubDepartment.setBounds(46, 206, 230, 38);
		frame.getContentPane().add(btnSubDepartment);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(822, 13, 60, 30);
		menuItem.setIcon(new ImageIcon(exit));
		frame.getContentPane().add(menuItem);
		
		JButton button = new JButton("5. CUSTOMER BALANCE SUMMARY");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					frame.setAlwaysOnTop(false);
					report_master.customer_list.report(con,company,username,"A4");
					//frame.setAlwaysOnTop(true);
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBackground(SystemColor.menu);
		button.setBounds(322, 206, 242, 38);
		frame.getContentPane().add(button);
		
		JButton btnSdPending = new JButton("6. SD PENDING INVOICE LIST");
		btnSdPending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new sd_pending_list(company,username,store_name, version,"2");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSdPending.setHorizontalAlignment(SwingConstants.LEFT);
		btnSdPending.setBackground(SystemColor.menu);
		btnSdPending.setBounds(604, 206, 230, 38);
		frame.getContentPane().add(btnSdPending);
		
		JButton btnDupCustomer = new JButton("7. DUP CUSTOMER VOUCHER ");
		btnDupCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new dup_cust_vochr(company, username,store_name,version,2);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDupCustomer.setHorizontalAlignment(SwingConstants.LEFT);
		btnDupCustomer.setBackground(SystemColor.menu);
		btnDupCustomer.setVisible(false);
		btnDupCustomer.setBounds(46, 289, 230, 38);
		frame.getContentPane().add(btnDupCustomer);
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
