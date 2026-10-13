package sales_master;

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
import POS_Reports.cust_payment;
import POS_Reports.delivery_list;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Xreports {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Xreports window = new Xreports("mr_pro", "V1.01", "S01", "S0101", "SHABEER","2","","");
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
	 * @param usertype 
	 * @param language 
	 */
	public Xreports(String company,String version,String store, String posreg,String username,String decimal, String usertype, String language) {
		initialize(company,  version,store,posreg,username,decimal,usertype,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @param posreg 
	 * @param decimal 
	 * @param usertype 
	 * @param language 
	 */
	private void initialize(final String company,final String version,final String store, final String posreg,final String username, final String decimal, final String usertype, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 900, 578);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 94, 884, 2);
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
	    menuItem.setBounds(0, 23, 80, 48);
	    panel.add(menuItem);
		
		JLabel lblpurchaseSetup = new JLabel("5. REPORTS");
		lblpurchaseSetup.setForeground(Color.BLACK);
		lblpurchaseSetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblpurchaseSetup.setBounds(10, 119, 220, 26);
		panel.add(lblpurchaseSetup);
		
		JButton btnconfi = new JButton("1. DEPARTMENT LISTING");
		btnconfi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new POS_Reports.Dept_list(company,store,username, version,posreg,decimal);
			}
		});
		btnconfi.setHorizontalAlignment(SwingConstants.LEFT);
		btnconfi.setBackground(SystemColor.menu);
		btnconfi.setBounds(59, 168, 230, 38);
		panel.add(btnconfi);
		
		JButton btnpurchaseTerms = new JButton("2. XREPORTS");
		btnpurchaseTerms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new POS_Reports.xreport(company,store,username, version,posreg,decimal,language);
			}
		});
		btnpurchaseTerms.setHorizontalAlignment(SwingConstants.LEFT);
		btnpurchaseTerms.setBackground(SystemColor.menu);
		btnpurchaseTerms.setBounds(343, 168, 230, 38);
		panel.add(btnpurchaseTerms);
		
		JButton btnVendorCategories = new JButton("3. DEPT ITEMS LIST");
		btnVendorCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new POS_Reports.Dept_itemlist(company,store,username, version,posreg,decimal,"");
			}
		});
		btnVendorCategories.setHorizontalAlignment(SwingConstants.LEFT);
		btnVendorCategories.setBackground(SystemColor.menu);
		btnVendorCategories.setBounds(623, 168, 230, 38);
		panel.add(btnVendorCategories);
		
		JButton btnstore = new JButton("4. VOID INVOICES");
		btnstore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new POS_Reports.void_list(company,store,username, version,posreg,decimal,"");
			}
		});
		btnstore.setHorizontalAlignment(SwingConstants.LEFT);
		btnstore.setBackground(SystemColor.menu);
		btnstore.setBounds(59, 229, 230, 38);
		panel.add(btnstore);
		
		JButton btnpoOverheadCharges = new JButton("5. REFUND INVOICES");
		btnpoOverheadCharges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new POS_Reports.refund(company,store,username, version,posreg,decimal,"");
			}
		});
		btnpoOverheadCharges.setHorizontalAlignment(SwingConstants.LEFT);
		btnpoOverheadCharges.setBackground(SystemColor.menu);
		btnpoOverheadCharges.setBounds(343, 229, 230, 38);
		panel.add(btnpoOverheadCharges);
		
		JButton btnReturnInvoices = new JButton("6. RETURN INVOICES");
		btnReturnInvoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new POS_Reports.return_inv(company,store,username, version,posreg,decimal,"");
			}
		});
		btnReturnInvoices.setHorizontalAlignment(SwingConstants.LEFT);
		btnReturnInvoices.setBackground(SystemColor.menu);
		btnReturnInvoices.setBounds(623, 229, 230, 38);
		panel.add(btnReturnInvoices);
		
		JButton btnPriceChange = new JButton("7. PRICE CHANGE INVOICES");
		btnPriceChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new POS_Reports.price_change(company,store,username, version,posreg,decimal,"");
			}
		});
		btnPriceChange.setHorizontalAlignment(SwingConstants.LEFT);
		btnPriceChange.setBackground(SystemColor.menu);
		btnPriceChange.setBounds(59, 290, 230, 38);
		panel.add(btnPriceChange);
		
		JButton btnDiscountInvoices = new JButton("8. DISCOUNT INVOICES");
		btnDiscountInvoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new POS_Reports.discount_inv(company,store,username, version,posreg,decimal,"");
			}
		});
		btnDiscountInvoices.setHorizontalAlignment(SwingConstants.LEFT);
		btnDiscountInvoices.setBackground(SystemColor.menu);
		btnDiscountInvoices.setBounds(343, 290, 230, 38);
		panel.add(btnDiscountInvoices);
		
		JButton btnPayinpayout = new JButton("9. PAYIN/PAYOUT");
		btnPayinpayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new POS_Reports.payinout(company,store,username, version,posreg,decimal,"");
			}
		});
		btnPayinpayout.setHorizontalAlignment(SwingConstants.LEFT);
		btnPayinpayout.setBackground(SystemColor.menu);
		btnPayinpayout.setBounds(623, 290, 230, 38);
		panel.add(btnPayinpayout);
		
		JButton btnCurrentSales = new JButton("10. CURRENT SALES IN ALL COUNTER");
		btnCurrentSales.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCurrentSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(usertype.equals("Administrator"))
				{
					new POS_Reports.current_sales(company,store,username, version,posreg,decimal);
				}
			}
		});
		btnCurrentSales.setHorizontalAlignment(SwingConstants.LEFT);
		btnCurrentSales.setBackground(SystemColor.menu);
		btnCurrentSales.setBounds(59, 351, 230, 38);
		panel.add(btnCurrentSales);
		
		JButton btnCustomerPayment = new JButton("11. CUSTOMER PAYMENT LIST");
		btnCustomerPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new cust_payment(company, store, username, version, posreg, decimal, language);
			}
		});
		btnCustomerPayment.setHorizontalAlignment(SwingConstants.LEFT);
		btnCustomerPayment.setBackground(SystemColor.menu);
		btnCustomerPayment.setBounds(343, 351, 230, 38);
		panel.add(btnCustomerPayment);
		
		JButton btnItemSale = new JButton("12. ITEM SALE LIST");
		btnItemSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new POS_Reports.item_sale_list(company,store,username, version,posreg,decimal,"");
			}
		});
		btnItemSale.setHorizontalAlignment(SwingConstants.LEFT);
		btnItemSale.setBackground(SystemColor.menu);
		btnItemSale.setBounds(623, 351, 230, 38);
		panel.add(btnItemSale);
		
		JButton btnDeliveryList = new JButton("13. DELIVERY INVOICE LIST");
		btnDeliveryList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new delivery_list(company,store,username, version,posreg,"2");
			}
		});
		btnDeliveryList.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeliveryList.setBackground(SystemColor.menu);
		btnDeliveryList.setBounds(59, 412, 230, 38);
		panel.add(btnDeliveryList);
		
		JButton btnPosInvoice = new JButton("14. POS INVOICE LIST");
		btnPosInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new POS_Reports.POSInvoiceList(company,username,store,posreg, Mainpage.printer, version,"2");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPosInvoice.setHorizontalAlignment(SwingConstants.LEFT);
		btnPosInvoice.setBackground(SystemColor.menu);
		btnPosInvoice.setBounds(343, 412, 230, 38);
		panel.add(btnPosInvoice);
		
		JButton btnPosHistory = new JButton("15. POS HISTORY INVOICE");
		btnPosHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new historical_invoice(company,username,store,posreg,Mainpage.printer, version,"2");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPosHistory.setHorizontalAlignment(SwingConstants.LEFT);
		btnPosHistory.setBackground(SystemColor.menu);
		btnPosHistory.setBounds(623, 412, 230, 38);
		panel.add(btnPosHistory);
		
		JButton btnCustomerMaster = new JButton("16. CUSTOMER MASTER");
		btnCustomerMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new sales_master.Customer_master(company,username,posreg,store, version,"",language);
			}
		});
		btnCustomerMaster.setHorizontalAlignment(SwingConstants.LEFT);
		btnCustomerMaster.setBackground(SystemColor.menu);
		btnCustomerMaster.setBounds(59, 473, 230, 38);
		panel.add(btnCustomerMaster);
	}
}

