package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JLabel;

import org.json.JSONException;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Purchase_Setup {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Purchase_Setup window = new Purchase_Setup("mr_pro", "admin", null,"");
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
	public Purchase_Setup(String company, String username,String version, String language) {
		initialize(company, username, version,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param language 
	 */
	private void initialize(final String company,String username,final String version, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 900, 550);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 924, 512);
		frame.getContentPane().add(panel);
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
		
		JLabel lblpurchaseSetup = new JLabel("3. PURCHASE SETUP");
		lblpurchaseSetup.setForeground(Color.BLACK);
		lblpurchaseSetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblpurchaseSetup.setBounds(10, 74, 220, 26);
		panel.add(lblpurchaseSetup);
		
		Image contrl_pnl=new ImageIcon(this.getClass().getResource("/control-panel.png")).getImage();
		JButton btnconfi = new JButton("1. CONFIGURATION");
		btnconfi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new system_setups.Purchase_Config(company, version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnconfi.setHorizontalAlignment(SwingConstants.LEFT);
		btnconfi.setBackground(SystemColor.menu);
		btnconfi.setIcon(new ImageIcon(contrl_pnl));
		btnconfi.setBounds(59, 131, 230, 38);
		panel.add(btnconfi);
		
		JButton btnpurchaseTerms = new JButton("2. PURCHASE TERMS");
		btnpurchaseTerms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new system_setups.Po_Terms(company, version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnpurchaseTerms.setHorizontalAlignment(SwingConstants.LEFT);
		btnpurchaseTerms.setBackground(SystemColor.menu);
		btnpurchaseTerms.setBounds(343, 131, 230, 38);
		panel.add(btnpurchaseTerms);
		
		JButton btnVendorCategories = new JButton("3. VENDOR CATEGORIES");
		btnVendorCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new system_setups.Vendor_Master(version);
			}
		});
		btnVendorCategories.setHorizontalAlignment(SwingConstants.LEFT);
		btnVendorCategories.setBackground(SystemColor.menu);
		btnVendorCategories.setBounds(623, 131, 230, 38);
		btnVendorCategories.setEnabled(false);
		panel.add(btnVendorCategories);
		
		JButton btnstore = new JButton("4. SHIPMENT CODES");
		btnstore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new system_setups.Shipment_Codes(company,version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnstore.setHorizontalAlignment(SwingConstants.LEFT);
		btnstore.setBackground(SystemColor.menu);
		btnstore.setBounds(59, 222, 230, 38);
		panel.add(btnstore);
		
		JButton btnpoOverheadCharges = new JButton("5. PO OVERHEAD CHARGES");
		btnpoOverheadCharges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new system_setups.Po_Overhead(company, version, language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnpoOverheadCharges.setHorizontalAlignment(SwingConstants.LEFT);
		btnpoOverheadCharges.setBackground(SystemColor.menu);
		btnpoOverheadCharges.setBounds(343, 222, 230, 38);
		panel.add(btnpoOverheadCharges);
	}
}
