package accounts_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.json.JSONException;

public class Account_Setup {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Account_Setup window = new Account_Setup("mr_pro", null,"S01","","");
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
	 * @param language 
	 */
	public Account_Setup(String company,String version,String store,String username, String language) {
		initialize(company, version,store,username,language);
	}


	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @param store 
	 * @param language 
	 */
	private void initialize(final String company,final String version, final String store,final String username,final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 900, 550);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 882, 503);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 61, 884, 2);
		panel.add(separator);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Exit");
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 65, 860, 429);
		panel.add(panel_1);
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setLayout(null);
		
		JLabel lblpurchaseSetup = new JLabel("ACCOUNTS SETUP");
		if(language.equals("Arabic"))
		{
			lblpurchaseSetup.setText("≈⁄œ«œ «·Õ”«»« ");
		}
		lblpurchaseSetup.setBounds(10, 11, 220, 44);
		panel_1.add(lblpurchaseSetup);
		lblpurchaseSetup.setForeground(Color.BLACK);
		lblpurchaseSetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnconfi = new JButton("1. CONFIGURATION");
		if(language.equals("Arabic"))
		{
			btnconfi.setText("1. «· ﬂÊÌ‰");
		}
		btnconfi.setBounds(20, 55, 230, 38);
		panel_1.add(btnconfi);
		btnconfi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new accounts_master.Acc_Config(company,  version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnconfi.setHorizontalAlignment(SwingConstants.LEFT);
		btnconfi.setBackground(SystemColor.menu);
		
		JButton btnpurchaseTerms = new JButton("2. ACCOUNT GROUP");
		if(language.equals("Arabic"))
		{
			btnpurchaseTerms.setText("2. „Ã„Ê⁄… «·Õ”«»« ");
		}
		btnpurchaseTerms.setBounds(292, 55, 230, 38);
		panel_1.add(btnpurchaseTerms);
		btnpurchaseTerms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new accounts_master.accounts(company, version, language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnpurchaseTerms.setHorizontalAlignment(SwingConstants.LEFT);
		btnpurchaseTerms.setBackground(SystemColor.menu);
		
		JButton btnVendorCategories = new JButton("3. ACCOUNT SEGMENT");
		if(language.equals("Arabic"))
		{
			btnVendorCategories.setText("3. ‘—ÌÕ… «·Õ”«»");
		}
		btnVendorCategories.setBounds(564, 55, 230, 38);
		panel_1.add(btnVendorCategories);
		btnVendorCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Account_Segments(company, version, language);
			}
		});
		btnVendorCategories.setHorizontalAlignment(SwingConstants.LEFT);
		btnVendorCategories.setBackground(SystemColor.menu);
		
		JButton btnstore = new JButton("4. CHART OF ACCOUNTS");
		if(language.equals("Arabic"))
		{
			btnstore.setText("4. „Œÿÿ «·Õ”«»« ");
		}
		btnstore.setBounds(20, 147, 230, 38);
		panel_1.add(btnstore);
		btnstore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new accounts_master.chartaccount(company,  version, language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnstore.setHorizontalAlignment(SwingConstants.LEFT);
		btnstore.setBackground(SystemColor.menu);
		
		JButton btnpoOverheadCharges = new JButton("5. ACCOUNTING PAYCODES");
		if(language.equals("Arabic"))
		{
			btnpoOverheadCharges.setText("5. —„Ê“ «·œ›⁄ «·„Õ«”»Ì…");
		}
		btnpoOverheadCharges.setBounds(292, 147, 230, 38);
		panel_1.add(btnpoOverheadCharges);
		btnpoOverheadCharges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Acc_Paycodes(company,version,store, language);
			}
		});
		btnpoOverheadCharges.setHorizontalAlignment(SwingConstants.LEFT);
		btnpoOverheadCharges.setBackground(SystemColor.menu);
		
		JButton btnTransactionCurrencies = new JButton("6. TRANSACTION CURRENCIES");
		if(language.equals("Arabic"))
		{
			btnTransactionCurrencies.setText("6. ⁄„·«  «·„⁄«„·« ");
		}
		btnTransactionCurrencies.setBounds(564, 147, 230, 38);
		panel_1.add(btnTransactionCurrencies);
		btnTransactionCurrencies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Txn_Currnc(company, version, language);
			}
		});
		btnTransactionCurrencies.setHorizontalAlignment(SwingConstants.LEFT);
		btnTransactionCurrencies.setBackground(SystemColor.menu);
		
		JButton btnAccountCategory = new JButton("7. ACCOUNT CATEGORY");
		if(language.equals("Arabic"))
		{
			btnAccountCategory.setText("7. ›∆… «·Õ”«»");
		}
		btnAccountCategory.setBounds(20, 239, 230, 38);
		panel_1.add(btnAccountCategory);
		btnAccountCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Acc_category(company, version,language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAccountCategory.setHorizontalAlignment(SwingConstants.LEFT);
		btnAccountCategory.setBackground(SystemColor.menu);
		
		JButton btnLoginAuthority = new JButton("8. REPORTS");
		if(language.equals("Arabic"))
		{
			btnLoginAuthority.setText("8. «· ﬁ«—Ì—");
		}
		btnLoginAuthority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Acc_reports(company,version,store,username, language);
			}
		});
		btnLoginAuthority.setBounds(292, 239, 230, 38);
		panel_1.add(btnLoginAuthority);
		btnLoginAuthority.setHorizontalAlignment(SwingConstants.LEFT);
		btnLoginAuthority.setBackground(SystemColor.menu);
		
		JButton btnNewButton = new JButton("9. AEGING");
		if(language.equals("Arabic"))
		{
			btnNewButton.setText("9. ≈ÌÃÌ‰€");
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Aeging(company,username,store, version, language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(564, 239, 230, 38);
		panel_1.add(btnNewButton);
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBackground(SystemColor.menu);
		
		JButton btnExpenceInvoice = new JButton("10. EXPENCE INVOICE");
		if(language.equals("Arabic"))
		{
			btnExpenceInvoice.setText("10. ›« Ê—… «·„’—Ê›« ");
		}
		btnExpenceInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new AP_invoice_list(company, company, username, store, version, "3",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnExpenceInvoice.setHorizontalAlignment(SwingConstants.LEFT);
		btnExpenceInvoice.setBackground(SystemColor.menu);
		btnExpenceInvoice.setBounds(20, 332, 230, 38);
		panel_1.add(btnExpenceInvoice);
	}
}
