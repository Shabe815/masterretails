package PO_reports;

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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class po_report {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					po_report window = new po_report("mr_pro", "V1.01", "S01", "S0101", "SHABEER","2","");
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
	 * @param posreg 
	 */
	public po_report(String company,String version,String store, String posreg,String username,String decimal,String language) {
		initialize(company,  version,store,posreg,username,decimal, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @param posreg 
	 * @param decimal 
	 */
	private void initialize(final String company,final String version,final String store, final String posreg,final String username, final String decimal,final String language) {
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
		
		JLabel lblpurchaseSetup = new JLabel("5. REPORTS");
		lblpurchaseSetup.setForeground(Color.BLACK);
		lblpurchaseSetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblpurchaseSetup.setBounds(10, 74, 220, 26);
		panel.add(lblpurchaseSetup);
		
		JButton btnconfi = new JButton("1. PURCHASE ORDERS");
		btnconfi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new PO_reports.purchase_order(company, username, store, version);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnconfi.setHorizontalAlignment(SwingConstants.LEFT);
		btnconfi.setBackground(SystemColor.menu);
		btnconfi.setBounds(59, 131, 230, 38);
		panel.add(btnconfi);
		
		JButton btnpurchaseTerms = new JButton("2. VENDOR ITEMS");
		btnpurchaseTerms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new PO_reports.vendor_list_items(company, username, store, version);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnpurchaseTerms.setHorizontalAlignment(SwingConstants.LEFT);
		btnpurchaseTerms.setBackground(SystemColor.menu);
		btnpurchaseTerms.setBounds(343, 131, 230, 38);
		panel.add(btnpurchaseTerms);
		
		JButton btnVendorCategories = new JButton("VENDOR BALANCE SUMMARY");
		btnVendorCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					frame.setAlwaysOnTop(false);
					report_master.vendor_list.report(con,company,username,"A4");
					//frame.setAlwaysOnTop(true);
					con.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnVendorCategories.setHorizontalAlignment(SwingConstants.LEFT);
		btnVendorCategories.setBackground(SystemColor.menu);
		btnVendorCategories.setBounds(623, 131, 230, 38);
		panel.add(btnVendorCategories);
		
		JButton btnstore = new JButton("4. PURCHASE RETURNS");
		btnstore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new PO_reports.PR_report(company, username, store, version);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnstore.setHorizontalAlignment(SwingConstants.LEFT);
		btnstore.setBackground(SystemColor.menu);
		btnstore.setBounds(59, 222, 230, 38);
		panel.add(btnstore);
		
		JButton btnpoOverheadCharges = new JButton("5. ITEM PURCHASED ANALYSIS");
		btnpoOverheadCharges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new vendor_items_purchase(company,username,store, version,language);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnpoOverheadCharges.setHorizontalAlignment(SwingConstants.LEFT);
		btnpoOverheadCharges.setBackground(SystemColor.menu);
		btnpoOverheadCharges.setBounds(343, 222, 230, 38);
		panel.add(btnpoOverheadCharges);
		
		JButton btnVendorSummary = new JButton("VENDOR  SUMMARY");
		btnVendorSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new PO_reports.vendor_summary_report(company, username, store, version);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnVendorSummary.setHorizontalAlignment(SwingConstants.LEFT);
		btnVendorSummary.setBackground(SystemColor.menu);
		btnVendorSummary.setBounds(623, 222, 230, 38);
		panel.add(btnVendorSummary);
	}
}

