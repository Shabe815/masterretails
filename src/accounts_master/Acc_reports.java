package accounts_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.json.JSONException;


public class Acc_reports {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acc_reports window = new Acc_reports("mr_pro", null,"S01","","");
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
	public Acc_reports(String company,String version,String store,String username, String language) {
		initialize(company, version,store,username, language);
	}


	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @param store 
	 */
	private void initialize(final String company,final String version, final String store,final String username, final String language) {
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
		
		JLabel lblpurchaseSetup = new JLabel("ACCOUNTS REPORTS");
		if(language.equals("Arabic"))
		{
			lblpurchaseSetup.setText(" ﬁ«—Ì— «·Õ”«»« ");
		}
		lblpurchaseSetup.setBounds(42, 52, 220, 44);
		panel_1.add(lblpurchaseSetup);
		lblpurchaseSetup.setForeground(Color.BLACK);
		lblpurchaseSetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnconfi = new JButton("4. AP INVOICE REPORT");
		if(language.equals("Arabic"))
		{
			btnconfi.setText("4.  ﬁ—Ì— ›« Ê—… «·Õ”«»«  «·œ«∆‰…");
		}
		btnconfi.setBounds(42, 249, 230, 38);
		panel_1.add(btnconfi);
		btnconfi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new AP_report(company,username,store, version, language);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnconfi.setHorizontalAlignment(SwingConstants.LEFT);
		btnconfi.setBackground(SystemColor.menu);
		
		JButton btnpurchaseTerms = new JButton("5. ACCOUNT LEDGER");
		if(language.equals("Arabic"))
		{
			btnpurchaseTerms.setText("5. œ› — «·Õ”«»« ");
		}
		btnpurchaseTerms.setBounds(314, 249, 230, 38);
		panel_1.add(btnpurchaseTerms);
		btnpurchaseTerms.setVisible(false);
		btnpurchaseTerms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnpurchaseTerms.setHorizontalAlignment(SwingConstants.LEFT);
		btnpurchaseTerms.setBackground(SystemColor.menu);
		
		JButton btnVendorCategories = new JButton("1. TRAIL BALANCE");
		if(language.equals("Arabic"))
		{
			btnVendorCategories.setText("1.  Ê«“‰ «·„”«—");
		}
		btnVendorCategories.setBounds(42, 153, 230, 38);
		panel_1.add(btnVendorCategories);
		btnVendorCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new trail_balance(company, username,store,version,"2",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnVendorCategories.setHorizontalAlignment(SwingConstants.LEFT);
		btnVendorCategories.setBackground(SystemColor.menu);
		
		JButton btnstore = new JButton("2. BALANCE SHEET");
		if(language.equals("Arabic"))
		{
			btnstore.setText("2. «·„Ì“«‰Ì… «·⁄„Ê„Ì…");
		}
		btnstore.setBounds(314, 153, 230, 38);
		panel_1.add(btnstore);
		btnstore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new balance_sheet(company, username,store,version,"2", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnstore.setHorizontalAlignment(SwingConstants.LEFT);
		btnstore.setBackground(SystemColor.menu);
		
		JButton btnpoOverheadCharges = new JButton("3. PROFIT/LOSS ACCOUNTS");
		if(language.equals("Arabic"))
		{
			btnpoOverheadCharges.setText("3. Õ”«»«  «·√—»«Õ Ê«·Œ”«∆—");
		}
		btnpoOverheadCharges.setBounds(586, 153, 230, 38);
		panel_1.add(btnpoOverheadCharges);
		btnpoOverheadCharges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Profit_loss(company, username,store,version,"2",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnpoOverheadCharges.setHorizontalAlignment(SwingConstants.LEFT);
		btnpoOverheadCharges.setBackground(SystemColor.menu);
	}
}
