package inventory_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;

import org.json.JSONException;

public class Inv_Trnsfer {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inv_Trnsfer window = new Inv_Trnsfer("", null, null, null, null, null,"");
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
	public Inv_Trnsfer(String db_name,String company,String username,String store,String version,String decimal,String language) {
		initialize(db_name,company,username,store, version,decimal, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param decimal 
	 * @param version2 
	 * @param store 
	 * @param username 
	 * @param company 
	 */
	private void initialize(final String db_name, final String company, final String username, final String store, final String version, final String decimal,final String language) {
		frame = new JFrame(version);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("4. INVENTORY TRANSACTION");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(25, 61, 220, 26);
		
		frame.getContentPane().add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("1. TRANSFER ISSUE");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new Itemmaster();
				try {
					new inventory_transfer_list(db_name,company,username,store, version,decimal,"ISSUE",language);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(46, 124, 230, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnInve = new JButton("2. TRANSFER RECIEVE");
		btnInve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new inventory_transfer_list(db_name,company,username,store, version,decimal,"RECEIVE", language);
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
		frame.setBounds(100, 100, 760, 502);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 49, 750, 2);
		frame.getContentPane().add(separator_1);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(12, 6, 60, 30);
		menuItem.setIcon(new ImageIcon(exit));
		frame.getContentPane().add(menuItem);
	}
}
