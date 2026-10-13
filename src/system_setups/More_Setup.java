package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.json.JSONException;

import CafePro.Mainpage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class More_Setup {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					More_Setup window = new More_Setup("mr_pro", null,"S01","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param host_name 
	 * @param company 
	 */
	public More_Setup(String company,String version,String store,String username) {
		initialize(company,  version,store,username);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 */
	private void initialize(final String company,final String version,final String store,final String username) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 900, 550);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 61, 884, 2);
		panel.add(separator);
		
Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
	    menuItem.setBorder(null);
	    menuItem.setIcon(new ImageIcon(exit));
	    menuItem.setBackground(new Color(243, 229, 216));
	    menuItem.setBounds(0, 0, 80, 48);
	    panel.add(menuItem);
		
		JLabel lblpurchaseSetup = new JLabel("5. MORE SETUP");
		lblpurchaseSetup.setForeground(Color.BLACK);
		lblpurchaseSetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblpurchaseSetup.setBounds(10, 74, 220, 26);
		panel.add(lblpurchaseSetup);
		
		JButton btnconfi = new JButton("1. ACCOUNT SETUP");
		btnconfi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new accounts_master.Account_Setup(company, version,store,username);
			}
		});
		btnconfi.setHorizontalAlignment(SwingConstants.LEFT);
		btnconfi.setBackground(SystemColor.menu);
		btnconfi.setBounds(59, 131, 230, 38);
		panel.add(btnconfi);
		
		if(Mainpage.change_hold_type.equals("1"))
		{
			JButton btnpurchaseTerms = new JButton("2. TAKE AWAY TYPES");
			btnpurchaseTerms.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new take_away_users(company,version,store);
					} catch (JSONException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnpurchaseTerms.setHorizontalAlignment(SwingConstants.LEFT);
			btnpurchaseTerms.setBackground(SystemColor.menu);
			btnpurchaseTerms.setBounds(343, 131, 230, 38);
			panel.add(btnpurchaseTerms);
			
			JButton btnVendorCategories = new JButton("3. TABLE CREATION FOR POS");
			btnVendorCategories.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new table_create(company, version,store);
					} catch (JSONException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnVendorCategories.setHorizontalAlignment(SwingConstants.LEFT);
			btnVendorCategories.setBackground(SystemColor.menu);
			btnVendorCategories.setBounds(623, 131, 230, 38);
			panel.add(btnVendorCategories);
		}
		
		
		
	}

}
