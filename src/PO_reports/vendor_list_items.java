package PO_reports;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Button;

import javax.swing.JButton;

import org.json.JSONException;

import purchase_master.vendor_list;

public class vendor_list_items {

	private JFrame frame;
	public static JTextField textField;
	private JButton btnNewButton;
	public static JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vendor_list_items window = new vendor_list_items("mr_pro","admin", "S01", null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public vendor_list_items(String company, String username,  String store,String version) throws ParseException {
		initialize(company,username,store, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param balance 
	 * @param po_num 
	 * @param company 
	 * @param host_name 
	 * @param store 
	 * @param username 
	 * @param vendor_name 
	 * @param vendor_name2 
	 * @throws ParseException 
	 */
	private void initialize(final String company,final String username,final String store,final String version) throws ParseException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.requestFocus();
		
		JLabel lblDateRange = new JLabel("Vendor Items Reports\r\n");
		lblDateRange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateRange.setBounds(12, 16, 256, 39);
		frame.getContentPane().add(lblDateRange);
		
		Button button = new Button("Print");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String vendor=textField_1.getText();
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					report_master.Vendor_items.report(vendor,con,company);
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
		button.setBounds(133, 261, 235, 24);
		frame.getContentPane().add(button);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(12, 154, 356, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Customer List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new vendor_list(company, "vendor_items", version,"",username);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(366, 155, 28, 21);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblVendorList = new JLabel("Vendor List :");
		lblVendorList.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVendorList.setBounds(12, 111, 113, 16);
		frame.getContentPane().add(lblVendorList);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setVisible(false);
		textField_1.setBounds(252, 119, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 476, 369);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
	}
}
