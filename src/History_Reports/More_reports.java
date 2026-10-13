package History_Reports;

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

import CafePro.Mainpage;
import Dbsetup.dbconn;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;

public class More_reports {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					More_reports window = new More_reports("mr_pro", "V1.01", "S01", "S0102", "AYYUB","2","","");
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
	 * @param printer 
	 */
	public More_reports(String company,String version,String store, String posreg,String username,String decimal, String printer,String language) {
		initialize(company,  version,store,posreg,username,decimal,printer, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @param posreg 
	 * @param decimal 
	 * @param printer 
	 */
	private void initialize(final String company,final String version,final String store, final String posreg,final String username, final String decimal,final String printer,final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 900, 574);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 62, 884, 2);
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
		lblpurchaseSetup.setBounds(10, 79, 220, 26);
		panel.add(lblpurchaseSetup);
		
		JButton btnconfi = new JButton("1. PAY IN");
		btnconfi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new History_Reports.Date_range(company, username, store, "PAYIN", version, language);
			}
		});
		btnconfi.setHorizontalAlignment(SwingConstants.LEFT);
		btnconfi.setBackground(SystemColor.menu);
		btnconfi.setBounds(61, 140, 230, 38);
		panel.add(btnconfi);
		
		JButton btnpurchaseTerms = new JButton("2. PAYOUT");
		btnpurchaseTerms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new History_Reports.Date_range(company, username, store, "PAYOUT", version, language);
			}
		});
		btnpurchaseTerms.setHorizontalAlignment(SwingConstants.LEFT);
		btnpurchaseTerms.setBackground(SystemColor.menu);
		btnpurchaseTerms.setBounds(345, 140, 230, 38);
		panel.add(btnpurchaseTerms);
		
//		if(Mainpage.change_hold_type.equals("1"))
//		{
			JButton btnVendorCategories = new JButton("5. ORDER TRANSACTION");
			btnVendorCategories.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//new History_Reports.Date_range(company, username, store, "VOID", version);
					try {
						new historical_order_summary(company,username,store, version, language);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnVendorCategories.setHorizontalAlignment(SwingConstants.LEFT);
			btnVendorCategories.setBackground(SystemColor.menu);
			btnVendorCategories.setBounds(345,212, 230, 38);
			panel.add(btnVendorCategories);
//		}
		
		
		
		
		JButton btnstore = new JButton("6. ITEM SALE SUMMARY");
		btnstore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new History_Reports.Item_sale(company,company, username, store, version,language);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnstore.setHorizontalAlignment(SwingConstants.LEFT);
		btnstore.setBackground(SystemColor.menu);
		btnstore.setBounds(622, 212, 230, 38);
		//btnstore.setVisible(false);
		panel.add(btnstore);
		
		JButton btnpoOverheadCharges = new JButton("7. RETURN ITEMS");
		btnpoOverheadCharges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=dbconn.getConnection();
					new HistoryReturnitems( company,  username,   store, version, con,printer, language);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnpoOverheadCharges.setHorizontalAlignment(SwingConstants.LEFT);
		btnpoOverheadCharges.setBackground(SystemColor.menu);
		btnpoOverheadCharges.setBounds(61, 284, 230, 38);
		panel.add(btnpoOverheadCharges);
		
		JButton btnPriceChange = new JButton("3. ITEM ANALYSIS");
		btnPriceChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Connection con=dbconn.getConnection();
					new item_analysis( company,  username,   store, version, con);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPriceChange.setHorizontalAlignment(SwingConstants.LEFT);
		btnPriceChange.setBackground(SystemColor.menu);
		btnPriceChange.setBounds(622, 140, 230, 38);
		panel.add(btnPriceChange);
		
		JButton btnItemSummary = new JButton("4. CUSTOMER PAYMENT SUMMARY");
		btnItemSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new cust_payment_history(company, username, store, version, language);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnItemSummary.setHorizontalAlignment(SwingConstants.LEFT);
		btnItemSummary.setBackground(SystemColor.menu);
		btnItemSummary.setBounds(61, 212, 242, 38);
		panel.add(btnItemSummary);
		
		JButton button = new JButton("10. VOID ITEMS");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=dbconn.getConnection();
					new historyvoiditems( company,  username,   store, version, con,printer, language);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBackground(SystemColor.menu);
		button.setBounds(61, 356, 230, 38);
		panel.add(button);
		
		
		JButton btnCustomerPayment = new JButton("8. CUSTOMER PAYMENT HISTORY");
		btnCustomerPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Mainpage.customerpaymenthist.equals("1"))
				{
					new CustomerPaymentHistory(company,store,username, version,posreg,decimal, language);
				}
			}
		});
		btnCustomerPayment.setHorizontalAlignment(SwingConstants.LEFT);
		btnCustomerPayment.setBackground(SystemColor.menu);
		btnCustomerPayment.setBounds(345, 284, 230, 38);
		panel.add(btnCustomerPayment);
		
		
		
		JButton btnInvoiceHistory = new JButton("9. INVOICE HISTORY");
		btnInvoiceHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Mainpage.posinvoicehistory.equals("1"))
				{
					new InvoiceHistoryPaycode(company,store,username, version,posreg,decimal, language);
				}
			}
		});
		btnInvoiceHistory.setHorizontalAlignment(SwingConstants.LEFT);
		btnInvoiceHistory.setBackground(SystemColor.menu);
		btnInvoiceHistory.setBounds(622, 284, 230, 38);
		panel.add(btnInvoiceHistory);
		
		JButton btnDepartmentSales = new JButton("11. DEPARTMENT SALES");
		btnDepartmentSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=dbconn.getConnection();
					new HistoryDepartmentSales( company,  username,   store, version, con,printer, language);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDepartmentSales.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDepartmentSales.setHorizontalAlignment(SwingConstants.LEFT);
		btnDepartmentSales.setBackground(SystemColor.menu);
		btnDepartmentSales.setBounds(345, 356, 230, 38);
		panel.add(btnDepartmentSales);
		
		JButton btnDepartmentItem = new JButton("12. DEPARTMENT ITEM SALES");
		btnDepartmentItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=dbconn.getConnection();
					new HistoryDepartmentItems( company,  username,   store, version, con,printer, language);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDepartmentItem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDepartmentItem.setHorizontalAlignment(SwingConstants.LEFT);
		btnDepartmentItem.setBackground(SystemColor.menu);
		btnDepartmentItem.setBounds(622, 356, 230, 38);
		panel.add(btnDepartmentItem);
		
		JButton btnCustomerSales = new JButton("13. CUSTOMER SALES ANALYSIS");
		btnCustomerSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=dbconn.getConnection();
					new Customer_Sale_Analysis( company,  username,   store, version, con, language);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCustomerSales.setHorizontalAlignment(SwingConstants.LEFT);
		btnCustomerSales.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCustomerSales.setBackground(SystemColor.menu);
		btnCustomerSales.setBounds(61, 426, 230, 38);
		panel.add(btnCustomerSales);
		
		JButton btnLoginControl = new JButton("14. LOGIN CONTROL REPORT");
		btnLoginControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=dbconn.getConnection();
					new LoginControlReport( company,  username,   store, version, con, language);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLoginControl.setHorizontalAlignment(SwingConstants.LEFT);
		btnLoginControl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLoginControl.setBackground(SystemColor.menu);
		btnLoginControl.setBounds(345, 426, 230, 38);
		panel.add(btnLoginControl);
		
	}
}

