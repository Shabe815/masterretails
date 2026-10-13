package purchase_master;


import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import java.awt.Font;

import javax.swing.JTextField;

import accounts_master.SelectAccount;

import com.toedter.calendar.JDateChooser;

import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

public class vendor_details {

	static JFrame frame;
	private JTable table;
	private JPanel panel_2; 
	static JTextField textField;
	static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;
	private static JTextField textField_5;
	private static JTextField txtUae;
	private static JTextField textField_7;
	private static JTextField textField_8;
	private static JTextField textField_10;
	private static JTextField textField_11;
	private static JTextField textField_12;
	private static JTextField textField_13;
	private static JTextField textField_14;
	private static JTextField textField_15;
	private static JTextField textField_18;
	private static JTextField hiddentxt;
	private static JComboBox<String> comboBox;
	private static JComboBox<String> comboBox_1 ;
	
	private static JComboBox<String> comboBox_4;
	private static JComboBox<String> comboBox_5 ;
	private static JDateChooser dateChooser;
	private static JDateChooser dateChooser_1;
	private JCheckBox chckbxNewCheckBox;
	private static JTextField textField_9;
	private static DefaultTableModel model_1;
	private static DefaultTableModel model;
	private static JTextField textField_16;
	private static JTextField textField_17;
	private static JCheckBox checkBox;
	private static JTextField textField_19;
	private boolean dot;
	public static JMenuItem mntmNewMenuItem;
	private JTextField textField_6;
	private JButton button_12;
	public static JTextField textField_20;
	public static JTextField textField_21;
	private static JTextField textField_22;
	private static JTextField textField_23;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					vendor_details window = new vendor_details("","mr_pro","admin", "","S01", null, null,"");
					vendor_details.frame.setVisible(true);
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
	 * @param username 
	 * @param page_type 
	 * @param decimal 
	 * @throws JSONException 
	 */
	public vendor_details(String db_name,String company, String username, String page_type,String store_name,String version, String decimal,String language) throws JSONException {
		initialize( db_name,company,username, page_type, store_name, version,decimal,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @param username 
	 * @param decimal 
	 * @throws JSONException 
	 */
	private void initialize(final String db_name,final String company,final String username ,final String page_type,final String store_name,final String version, final String decimal,final String language) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 1000, 700);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	if(page_type=="po_order")
		    	{
		    		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		    		purchase_master.purchase_order.frame.setAlwaysOnTop(true);
//					purchase_master.purchase_order.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//					purchase_master.purchase_order.menuItem.setEnabled(true);
//					purchase_master.purchase_order.btnNew.setEnabled(false);
		    	}
		    	else
		    	{
		    		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    	//CafePro.Mainpage.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    	CafePro.Mainpage.btnVendorMaster.setEnabled(true);
		    	}
		    	
		    }
		});
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		if(page_type=="po_order")
    	{
		frame.setAlwaysOnTop(true);
		}
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 61, 1082, 2);
		frame.getContentPane().add(separator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setBounds(0, 64, 982, 589);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(243, 229, 216));
		panel_2.setBounds(0, 0, 293, 240);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblVendorCode = new JLabel("* Vendor Code");
		lblVendorCode.setBounds(12, 3, 90, 18);
		panel_2.add(lblVendorCode);
		
		JLabel lblVendorName = new JLabel("* Vendor Name");
		lblVendorName.setBounds(12, 24, 90, 18);
		panel_2.add(lblVendorName);
		
		JLabel lblAddress = new JLabel("Address 1");
		lblAddress.setBounds(12, 45, 90, 18);
		panel_2.add(lblAddress);
		
		JLabel lblAddress_1 = new JLabel("Address 2");
		lblAddress_1.setBounds(12, 66, 90, 18);
		panel_2.add(lblAddress_1);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(12, 87, 90, 18);
		panel_2.add(lblCity);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(12, 108, 90, 18);
		panel_2.add(lblState);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(12, 129, 90, 18);
		panel_2.add(lblCountry);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_vndrdtls_bsd_code(company, textField.getText());
			}
		});
		textField.setBounds(112, 3, 117, 18);
		panel_2.add(textField);
		textField.requestFocus();
		
		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_vndrdtls_bsd_name(company,textField_1.getText());
			}
		});
		textField_1.setBounds(112, 24, 150, 18);
		panel_2.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(112, 45, 150, 18);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(112, 66, 150, 18);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(112, 87, 150, 18);
		panel_2.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(112, 108, 150, 18);
		panel_2.add(textField_5);
		textField_5.setColumns(10);
		
		txtUae = new JTextField();
		txtUae.setText("UAE");
		txtUae.setBounds(112, 129, 150, 18);
		panel_2.add(txtUae);
		txtUae.setColumns(10);
		
		JLabel lblAccountNo = new JLabel("Bank Account No.");
		lblAccountNo.setBounds(12, 192, 102, 18);
		panel_2.add(lblAccountNo);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(112, 192, 150, 18);
		panel_2.add(textField_9);
		textField_9.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_9.getText().equals(""))
			        dot = false;
			    if (dot == false){
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    } else {
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    }
			}
		});
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//frame.setAlwaysOnTop(false);
					new vendor_list(company,  "vendor_dt", version,decimal,username);
					//frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					//mntmNewMenuItem.setEnabled(false);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		button_1.setIcon(new ImageIcon(serch));
		button_1.setBounds(227, 0, 35, 18);
		panel_2.add(button_1);
		
		JLabel lblPhone = new JLabel("* Phone 1");
		lblPhone.setBounds(12, 213, 90, 18);
		panel_2.add(lblPhone);
		
		textField_7 = new JTextField();
		textField_7.setBounds(112, 213, 150, 18);
		panel_2.add(textField_7);
		textField_7.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_7.getText().equals(""))
			        dot = false;
			    if (dot == false){
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    } else {
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    }
			}
		});
		textField_7.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 150, 46, 18);
		panel_2.add(lblEmail);
		
		JLabel lblFax = new JLabel("Fax");
		lblFax.setBounds(10, 171, 46, 18);
		panel_2.add(lblFax);
		
		textField_22 = new JTextField();
		textField_22.setBounds(112, 150, 150, 18);
		panel_2.add(textField_22);
		textField_22.setColumns(10);
		
		textField_23 = new JTextField();
		textField_23.setBounds(112, 171, 150, 18);
		panel_2.add(textField_23);
		textField_23.setColumns(10);
		panel_2.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, textField_1, textField_2, textField_3, textField_4, textField_5, txtUae, textField_22, textField_23, textField_9, textField_7, lblVendorCode, lblVendorName, lblAddress, lblAddress_1, lblCity, lblState, lblCountry, lblAccountNo, button_1, lblPhone, lblEmail, lblFax}));
		
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(243, 229, 216));
		panel_3.setBounds(293, 0, 342, 240);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblPhone_1 = new JLabel("Phone 2");
		lblPhone_1.setBounds(12, 5, 150, 18);
		panel_3.add(lblPhone_1);
		
		JLabel lblContactName = new JLabel("* Contact Name");
		lblContactName.setBounds(12, 28, 150, 18);
		panel_3.add(lblContactName);
		
		JLabel lblContactNumber = new JLabel("* Contact Number");
		lblContactNumber.setBounds(12, 51, 150, 18);
		panel_3.add(lblContactNumber);
		
		JLabel lblTrnNo = new JLabel("TRN No");
		lblTrnNo.setBounds(12, 74, 150, 18);
		panel_3.add(lblTrnNo);
		
		JLabel lblStatus = new JLabel("Vendor Status");
		lblStatus.setBounds(12, 97, 150, 22);
		panel_3.add(lblStatus);
		
		JLabel lblTotalPurchaseNumber = new JLabel("Total Purchase Amount");
		lblTotalPurchaseNumber.setBounds(12, 124, 150, 18);
		panel_3.add(lblTotalPurchaseNumber);
		
		textField_8 = new JTextField("0");
		textField_8.setEditable(false);
		textField_8.setBounds(172, 124, 150, 18);
		panel_3.add(textField_8);
		textField_8.setColumns(10);
		textField_8.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_8.getText().equals(""))
			        dot = false;
			    if (dot == false){
			        if (vChar == '.') dot = true;
			        else if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    } else {
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    }
			}
		});
		
		textField_10 = new JTextField();
		textField_10.setBounds(172, 5, 150, 18);
		panel_3.add(textField_10);
		textField_10.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_10.getText().equals(""))
			        dot = false;
			    if (dot == false){
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    } else {
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    }
			}
		});
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setBounds(172, 28, 150, 18);
		panel_3.add(textField_11);
		textField_11.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setBounds(172, 74, 150, 18);
		panel_3.add(textField_12);
		textField_12.setColumns(10);
		textField_12.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_12.getText().equals(""))
			        dot = false;
			    if (dot == false){
			        if (vChar == '.') dot = true;
			        else if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    } else {
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    }
			}
		});
		
		textField_13 = new JTextField();
		textField_13.setBounds(172, 51, 150, 18);
		panel_3.add(textField_13);
		textField_13.setColumns(10);
		textField_13.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_13.getText().equals(""))
			        dot = false;
			    if (dot == false){
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    } else {
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    }
			}
		});
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("Active");
		comboBox.addItem("Inactive");
		comboBox.setBounds(172, 97, 150, 22);
		panel_3.add(comboBox);
		
		JLabel lblPaidAmount = new JLabel("Total Paid Amount");
		lblPaidAmount.setBounds(12, 147, 150, 18);
		panel_3.add(lblPaidAmount);
		
		textField_15 = new JTextField("0");
		textField_15.setEditable(false);
		textField_15.setBounds(172, 147, 150, 18);
		panel_3.add(textField_15);
		textField_15.setColumns(10);
		textField_15.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_15.getText().equals(""))
			        dot = false;
			    if (dot == false){
			        if (vChar == '.') dot = true;
			        else if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    } else {
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    }
			}
		});
		
		JLabel lblTotalPendingAmount = new JLabel("Total Pending Amount");
		lblTotalPendingAmount.setBounds(12, 170, 150, 18);
		panel_3.add(lblTotalPendingAmount);
		
		textField_14 = new JTextField("0");
		textField_14.setEditable(false);
		textField_14.setBounds(172, 170, 150, 18);
		panel_3.add(textField_14);
		textField_14.setColumns(10);
		textField_14.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_14.getText().equals(""))
			        dot = false;
			    if (dot == false){
			        if (vChar == '.') dot = true;
			        else if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    } else {
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    }
			}
		});
		
		textField_17 = new JTextField();
		textField_17.setEditable(false);
		textField_17.setBounds(172, 193, 150, 18);
		panel_3.add(textField_17);
		textField_17.setColumns(10);
		textField_17.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_17.getText().equals(""))
			        dot = false;
			    if (dot == false){
			        if (vChar == '.') dot = true;
			        else if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    } else {
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    }
			}
		});
		
		JLabel lblVendorPurchaseLimit = new JLabel("Opening Balance");
		lblVendorPurchaseLimit.setBounds(12, 193, 150, 18);
		panel_3.add(lblVendorPurchaseLimit);
		
		JLabel label = new JLabel("Vendor Purchase Limit");
		label.setBounds(12, 216, 150, 18);
		panel_3.add(label);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(172, 216, 150, 18);
		panel_3.add(textField_6);
		panel_3.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField_10, textField_11, textField_13, textField_12, comboBox, textField_6, lblPhone_1, lblContactName, lblContactNumber, lblTrnNo, lblStatus, lblTotalPurchaseNumber, textField_8, lblPaidAmount, textField_15, lblTotalPendingAmount, textField_14, textField_17, lblVendorPurchaseLimit, label}));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(243, 229, 216));
		panel_4.setBounds(635, 0, 347, 240);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblPromotionStatus = new JLabel("Promotion Status");
		lblPromotionStatus.setBounds(10, 72, 150, 20);
		panel_4.add(lblPromotionStatus);
		
		JLabel lblPromotionType = new JLabel("Promotion Type");
		lblPromotionType.setBounds(10, 95, 150, 20);
		panel_4.add(lblPromotionType);
		
		JLabel lblPromotionDiscount = new JLabel("Promotion Discount %");
		lblPromotionDiscount.setBounds(10, 118, 150, 20);
		panel_4.add(lblPromotionDiscount);
		
		JLabel lblStartingDate = new JLabel("Starting Date");
		lblStartingDate.setBounds(10, 141, 150, 20);
		panel_4.add(lblStartingDate);
		
		JLabel lblEndingDate = new JLabel("Ending Date");
		lblEndingDate.setBounds(10, 164, 150, 20);
		panel_4.add(lblEndingDate);
		
		textField_18 = new JTextField();
		textField_18.setEditable(false);
		textField_18.setColumns(10);
		textField_18.setBounds(170, 118, 150, 20);
		panel_4.add(textField_18);
		textField_18.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_18.getText().equals(""))
			        dot = false;
			    if (dot == false){
			        if (vChar == '.') dot = true;
			        else if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    } else {
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    }
			}
		});
		
		dateChooser = new JDateChooser();
		dateChooser.setEnabled(false);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(170, 141, 150, 20);
		dateChooser.setMinSelectableDate(new Date());
		panel_4.add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setEnabled(false);
		dateChooser_1.getCalendarButton().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Date date = dateChooser.getDate();
						dateChooser_1.getJCalendar().setMinSelectableDate(date);
						dateChooser_1.setDate(date);
					}
				});
		dateChooser_1.setDateFormatString("dd/MM/yyyy");
		dateChooser_1.setBounds(170, 164, 150, 20);
		panel_4.add(dateChooser_1);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setEnabled(false);
		comboBox_1.addItem("Discount");
		comboBox_1.addItem("Markup");
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setBounds(170, 95, 150, 20);
		panel_4.add(comboBox_1);
		
		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBackground(new Color(243, 229, 216));
		chckbxNewCheckBox.setBounds(170, 72, 25, 20);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxNewCheckBox.isSelected()) {
					comboBox_1.setEnabled(true);
					dateChooser.setEnabled(true);
					dateChooser_1.setEnabled(true);
					textField_18.setEditable(true);
				} else {
					comboBox_1.setEnabled(false);
					dateChooser.setEnabled(false);
					dateChooser_1.setEnabled(false);
					textField_18.setEditable(false);
				}
			}
		});
		panel_4.add(chckbxNewCheckBox);
		
		JLabel lblVendorAlsoA = new JLabel("Vendor also a Customer");
		lblVendorAlsoA.setBounds(10, 49, 150, 20);
		panel_4.add(lblVendorAlsoA);
		
		checkBox = new JCheckBox("");
		checkBox.setBackground(new Color(243, 229, 216));
		checkBox.setBounds(170, 49, 25, 20);
		panel_4.add(checkBox);
		
		JLabel lblVendorTerms = new JLabel("Vendor Terms");
		lblVendorTerms.setBounds(12, 3, 148, 20);
		panel_4.add(lblVendorTerms);
		
		comboBox_4 = new JComboBox<String>();
		comboBox_4.setBounds(170, 3, 150, 20);
		panel_4.add(comboBox_4);
		get_vendor_terms( company);
		
		JLabel lblShippingMode = new JLabel("Shipping Mode");
		lblShippingMode.setBounds(12, 26, 150, 20);
		panel_4.add(lblShippingMode);
		
		
		comboBox_5 = new JComboBox<String>();
		comboBox_5.setBounds(170, 26, 150, 20);
		panel_4.add(comboBox_5);
		
		JLabel lblNewLabel = new JLabel("Vendor Account");
		lblNewLabel.setBounds(10, 187, 108, 20);
		panel_4.add(lblNewLabel);
		
		
		
		
		button_12 = new JButton("");
		button_12.setBounds(112, 187, 35, 20);
		panel_4.add(button_12);
		button_12.setToolTipText("F9");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company, "Vendor_dt", 0, version,"", "", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_12.setIcon(new ImageIcon(serch));
		
		textField_20 = new JTextField("0201003001");
		textField_20.setEditable(false);
		textField_20.setBounds(170, 187, 150, 20);
		panel_4.add(textField_20);
		textField_20.setColumns(10);
		
		textField_21 = new JTextField("VENDOR PAYABLE  AT S01");
		textField_21.setEditable(false);
		textField_21.setFont(new Font("Tahoma", Font.PLAIN, 8));
		textField_21.setBounds(170, 209, 150, 20);
		panel_4.add(textField_21);
		textField_21.setColumns(10);
		get_ship_mode( company);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 241, 982, 2);
		panel_1.add(separator_1);
		model = new DefaultTableModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		model.addColumn("ID");
		model.addColumn("Vendor Code");
		model.addColumn("Vendor Name");
		model.addColumn("Contact Name");
		model.addColumn("Contact Number");
		model.addColumn("Status");
		get_details( company);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(243, 229, 216));
		panel_2.setBounds(0, 250, 982, 339);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblVendorItemList = new JLabel("Vendor Item List");
		lblVendorItemList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVendorItemList.setBounds(10, 10, 132, 22);
		panel_2.add(lblVendorItemList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.setBounds(10, 45, 960, 281);
		panel_2.add(scrollPane);
		
		model_1 = new DefaultTableModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		table = new JTable(model_1);
		model_1.addColumn("ID");
		model_1.addColumn("Vendor Name");
		model_1.addColumn("Item Name");
		model_1.addColumn("Item Code");
		model_1.addColumn("Status");
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("Print");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select A Vendor");
				}
				else
				{
					if(model_1.getRowCount()>0)
					{
						Connection con=Dbsetup.dbconn.getConnection();
						try {
							report_master.Vendor_items.report(textField.getText(),con,company);
							con.close();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "This Vendor Do Not Have Items");
					}
				}
			}
		});
		button.setBounds(873, 10, 97, 25);
		panel_2.add(button);
		
		JButton btnExport_1 = new JButton("Export");
		btnExport_1.setBounds(764, 10, 97, 25);
		panel_2.add(btnExport_1);
		
		JButton btnAddNewItem = new JButton("Link Item");
		btnAddNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String vendor_id=textField.getText();
				try {
					if(vendor_id.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Choose a Vendor First");
					}
					else if(vendor_id.equals("NEW"))
					{
						JOptionPane.showMessageDialog(frame, "Save the vendor first");
					}
					else
					{
						//frame.setAlwaysOnTop(false);
						new vendor_item_list(company,vendor_id,"", page_type, version);
						//frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
						//mntmNewMenuItem.setEnabled(false);
						
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		btnAddNewItem.setBounds(166, 10, 120, 25);
		panel_2.add(btnAddNewItem);
		panel_1.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, textField_1, textField_2, textField_3, textField_4, textField_5, txtUae, textField_22, textField_23, textField_9, textField_7, textField_10, textField_11, textField_13, textField_12, comboBox, textField_6, comboBox_4, comboBox_5, checkBox, chckbxNewCheckBox, comboBox_1, textField_18, dateChooser, dateChooser_1, textField_20, panel_2, lblVendorCode, lblVendorName, lblAddress, lblAddress_1, lblCity, lblState, lblCountry, lblAccountNo, button_1, lblPhone, lblEmail, lblFax, panel_3, lblPhone_1, lblContactName, lblContactNumber, lblTrnNo, lblStatus, lblTotalPurchaseNumber, textField_8, lblPaidAmount, textField_15, lblTotalPendingAmount, textField_14, textField_17, lblVendorPurchaseLimit, label, panel_4, lblPromotionStatus, lblPromotionType, lblPromotionDiscount, lblStartingDate, lblEndingDate, dateChooser.getCalendarButton(), dateChooser_1.getCalendarButton(), lblVendorAlsoA, lblVendorTerms, lblShippingMode, lblNewLabel, button_12, textField_21, separator_1, panel_2, lblVendorItemList, scrollPane, table, button, btnExport_1, btnAddNewItem}));
		
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 982, 60);
		frame.getContentPane().add(panel);
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JLabel lblVendorDetails = new JLabel("Vendor Details");
		lblVendorDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVendorDetails.setBounds(21, 11, 228, 44);
		panel.add(lblVendorDetails);
		
		mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				if(page_type=="po_order")
		    	{
//		    		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		    		purchase_master.purchase_order.frame.setAlwaysOnTop(true);
//					purchase_master.purchase_order.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//					purchase_master.purchase_order.menuItem.setEnabled(true);
//					purchase_master.purchase_order.btnNew.setEnabled(true);
		    	}
		    	else
		    	{
//		    		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//			    	CafePro.Mainpage.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    	CafePro.Mainpage.btnVendorMaster.setEnabled(true);
		    	}
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(770, 11, 60, 48);
		panel.add(mntmNewMenuItem);

		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				txtUae.setText("");
				textField_7.setText("");
				textField_8.setText("");
				textField_9.setText("");
				textField_10.setText("");
				textField_11.setText("");
				textField_12.setText("");
				textField_13.setText("");
				textField_14.setText("");
				textField_15.setText("");
				textField_17.setText("");
				textField_18.setText("");
				textField_20.setText("0201003001");
				textField_21.setText("VENDOR PAYABLE  AT S01");
				comboBox.setSelectedItem("");
				comboBox_1.setSelectedItem("");
				checkBox.setSelected(false);
				textField_16.setText("");
				textField_19.setText("");
				textField_22.setText("");
				textField_23.setText("");
				hiddentxt.setText("");
				String disc_status="0";
				if(disc_status.equals("1"))
				{
					chckbxNewCheckBox.setSelected(true);
					comboBox_1.setEnabled(true);
					dateChooser.setEnabled(true);
					dateChooser_1.setEnabled(true);
					textField_18.setEditable(true);
				}
				else
				{
					chckbxNewCheckBox.setSelected(false);
					comboBox_1.setEnabled(false);
					dateChooser.setEnabled(false);
					dateChooser_1.setEnabled(false);
					textField_18.setEditable(false);
				}
				int rows=table.getRowCount()-1;
				for (int i = rows ; i >= 0; i--) {
				    model_1.removeRow(i);
				}
				textField.requestFocus();
			}
		});
		mntmRefresh.setBackground(new Color(243, 229, 216));
		mntmRefresh.setIcon(new ImageIcon(refresh));
		mntmRefresh.setBounds(830, 11, 80, 48);
		mntmRefresh.setBorder(null);
		panel.add(mntmRefresh);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code=textField.getText();
				String name=textField_1.getText();
				String addr1=textField_2.getText();
				String addr2=textField_3.getText();
				String city=textField_4.getText();
				String state=textField_5.getText();
				String country=txtUae.getText();
				String ph1=textField_7.getText();
				String prch_amt=textField_8.getText();
				if(prch_amt.equals(""))
				{
					prch_amt="0";
				}
				String accnt_num=textField_9.getText();
				String ph2=textField_10.getText();
				String cnt_name=textField_11.getText();
				String trn=textField_12.getText();
				String cnt_num=textField_13.getText();
				String pend_amt=textField_14.getText();
				String email=textField_22.getText();
				String fax=textField_23.getText();
				if(pend_amt.equals(""))
				{
					pend_amt="0";
				}
				String paid_amt=textField_15.getText();
				if(paid_amt.equals(""))
				{
					paid_amt="0";
				}
				String purchase_limit=textField_17.getText();
				if(purchase_limit.equals(""))
				{
					purchase_limit="0";
				}
				String disc_perc=textField_18.getText();
				if(disc_perc.equals(""))
				{
					disc_perc="0";
				}
				Object vndr_sts=(String) comboBox.getSelectedItem();
				Object promo_type=(String) comboBox_1.getSelectedItem();
				Object vendor_term=(String) comboBox_4.getSelectedItem();
				Object ship_mode=(String) comboBox_5.getSelectedItem();
				String startdate = null;
				String enddate = null;
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    String formattedDate = myDateObj.format(myFormatObj);  
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String start_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if(!start_date.equals(""))
				{
					startdate = sdf.format(dateChooser.getDate());
				}
				else
				{
					startdate=formattedDate;
				}
				String end_date = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				if(!end_date.equals(""))
				{
					enddate = sdf.format(dateChooser_1.getDate());
				}
				else
				{
					enddate=formattedDate;
				}
				//String old_dept = hiddentxt.getText();
				boolean save_param1=false;
				boolean save_param2=false;
				String promo="";
				String vend_cust="0";
				
				
				
				if (code.equals("")) {
					JOptionPane.showMessageDialog(frame,"Please, Enter the Vendor Code");
				} 
				else if (name.equals("")) {
					JOptionPane.showMessageDialog(frame,"Please, Enter the Vendor Name");
				} 
				else if (ph1.equals("")) {
					JOptionPane.showMessageDialog(frame,"Please, Enter the Phone number 1");
				} 
				else if (cnt_name.equals("")) {
					JOptionPane.showMessageDialog(frame,"Please, Enter the contact name");
				} 
				else if (cnt_num.equals("")) {
					JOptionPane.showMessageDialog(frame,"Please, Enter the contact number");
				}
				else 
				{
					
					if(code.equals(textField_19.getText()))
					{
						if(checkBox.isSelected())
						{
							vend_cust="1";
						}
						if (chckbxNewCheckBox.isSelected()) {
							if (comboBox.getSelectedIndex() == -1){
								JOptionPane.showMessageDialog(frame,"Please, Select the Type");
							} 
							else if (disc_perc.equals("")) {
								JOptionPane.showMessageDialog(frame,"Please, Enter the Discount Percent");
							} else if (start_date.equals("")) {
								JOptionPane.showMessageDialog(frame,"Please, Select the Starting Date");
							} else if (enddate.equals("")) {
								JOptionPane.showMessageDialog(frame,"Please, Select the Ending Date");
							}  
							else 
							{
								promo="1";
								save_param2=true;
							}
						}
						else
						{
							promo="0";
							save_param2=true;
						}
					}
					else
					{
						if(textField_19.getText().equals(""))
						{
							if(checkBox.isSelected())
							{
								vend_cust="1";
							}
							if (chckbxNewCheckBox.isSelected()) {
								if (comboBox.getSelectedIndex() == -1){
									JOptionPane.showMessageDialog(frame,
											"Please, Select the Type");
								} 
								else if (disc_perc.equals("")) {
									JOptionPane.showMessageDialog(frame,
											"Please, Enter the Discount Percent");
								}  else if (start_date.equals("")) {
									JOptionPane.showMessageDialog(frame,
											"Please, Select the Starting Date");
								} else if (enddate.equals("")) {
									JOptionPane.showMessageDialog(frame,
											"Please, Select the Ending Date");
								}  
								
								else {
									promo="1";
									save_param1=true;
								}
							}
							else
							{
								promo="0";
								save_param1=true;
							}
						}
						else
						{
							int a= JOptionPane.showConfirmDialog(frame, "Do You want to add this new vendor","",JOptionPane.YES_NO_OPTION);
							if(a==0)
							{
								if(checkBox.isSelected())
								{
									vend_cust="1";
								}
								if (chckbxNewCheckBox.isSelected()) {
									if (comboBox.getSelectedIndex() == -1){
										JOptionPane.showMessageDialog(frame,
												"Please, Select the Type");
									} 
									else if (disc_perc.equals("")) {
										JOptionPane.showMessageDialog(frame,
												"Please, Enter the Discount Percent");
									} else if (!disc_perc.matches("\\d+(\\.\\d+)*")) {
										JOptionPane.showMessageDialog(frame,
												"Please, Enter the valid Discount Percent");
									} else if (start_date.equals("")) {
										JOptionPane.showMessageDialog(frame,
												"Please, Select the Starting Date");
									} else if (enddate.equals("")) {
										JOptionPane.showMessageDialog(frame,
												"Please, Select the Ending Date");
									}  
									
									else {
										promo="1";
										save_param1=true;
									}
								}
								else
								{
									promo="0";
									save_param1=true;
								}
							}
							else if(a==1)
							{
								JOptionPane.showMessageDialog(frame, "Vendor Code Not Matching");
							}
						}
						
					}
					
				}
				if(save_param1==true)
				{
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					try {
						statement = con.createStatement();
						String query="SELECT * FROM "+company+".mr_vendor where vendor_name='"+name+"' or vendor_code='"+code+"' "; 
						ResultSet resultSet = statement.executeQuery(query);
						if(!resultSet.next())
						{
							String cust_add ="insert into "+company+".mr_customer_master(`cust_code`, `cust_name`,  `addr1`, `addr2`, `city`, `country`, `ph1`, `ph2`, `created_date`, `modified_date`,status,credit_limit,avail_credit,default_ar_acc) values('V-"+code+"','"+name+"','"+addr1+"','"+addr2+"','"+city+"','"+country+"','"+ph1+"','"+ph2+"','"+formattedDate+"','"+formattedDate+"','"+vend_cust+"','1000','1000','0101013001') ";
							statement.executeUpdate(cust_add);
							String query1="insert into "+company+".mr_vendor( `vendor_code`, `vendor_name`, `vendor_addr1`, `vendor_addr2`, `vendor_city`, `state`, `vendor_country`,`vendor_ph1`, `vendor_ph2`, `vendor_cont_name`, `vendor_cont_mob`, `vendor_trn`, `vendor_sts`, `vendor_purch_amt`, `vendor_paid_amt`,`vendor_pending_amt`, `vendor_itm_promo`, `vendor_promo_type`, `vendor_start_date`, `vendor_end_date`, `disc_perc`, `vendor_accnt_cde`,`purchase_limit`, `vendor_custmer`, `vendor_terms`, `shipment_mode`, `created_date`, `modified_date`,store_id,vendor_payable_ac,vendor_ac_name,email,fax) VALUES ('"+code+"','"+name+"','"+addr1+"','"+addr2+"','"+city+"','"+state+"','"+country+"','"+ph1+"','"+ph2+"','"+cnt_name+"','"+cnt_num+"','"+trn+"','"+vndr_sts+"','"+prch_amt+"','"+paid_amt+"','"+pend_amt+"','"+promo+"','"+promo_type+"','"+startdate+"','"+enddate+"','"+disc_perc+"','"+accnt_num+"','"+purchase_limit+"','"+vend_cust+"','"+vendor_term+"','"+ship_mode+"','"+formattedDate+"','"+formattedDate+"','"+store_name+"','"+textField_20.getText()+"','"+textField_21.getText()+"','"+email+"','"+fax+"')";
							statement.executeUpdate(query1);
							textField_19.setText(code);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Vendor Name or Code Already Exist");
						}
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
					
				}
				if(save_param2==true)
				{
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					try {
						statement = con.createStatement();
						String query="SELECT * FROM "+company+".mr_vendor where vendor_name='"+name+"' or vendor_code='"+code+"' "; 
						ResultSet resultSet = statement.executeQuery(query);
						if(resultSet.next())
						{
							String cust_add ="update "+company+".mr_customer_master set status='"+vend_cust+"' where cust_code='"+code+"' ";
							statement.executeUpdate(cust_add);
							String query1="update "+company+".mr_vendor set vendor_ac_name='"+textField_21.getText()+"',vendor_payable_ac='"+textField_20.getText()+"',`vendor_name`='"+name+"', `vendor_addr1`='"+addr1+"', `vendor_addr2`='"+addr2+"', `vendor_city`='"+city+"', `state`='"+state+"', `vendor_country`='"+country+"',`vendor_ph1`='"+ph1+"', `vendor_ph2`='"+ph2+"', `vendor_cont_name`='"+cnt_name+"', `vendor_cont_mob`='"+cnt_num+"', `vendor_trn`='"+trn+"', `vendor_sts`='"+vndr_sts+"', `vendor_purch_amt`='"+prch_amt+"', `vendor_paid_amt`='"+paid_amt+"',`vendor_pending_amt`='"+pend_amt+"', `vendor_itm_promo`='"+promo+"', `vendor_promo_type`='"+promo_type+"', `vendor_start_date`='"+startdate+"', `vendor_end_date`='"+enddate+"', `disc_perc`='"+disc_perc+"', `vendor_accnt_cde`='"+accnt_num+"',`purchase_limit`='"+purchase_limit+"', `vendor_custmer`='"+vend_cust+"', `vendor_terms`='"+vendor_term+"', `shipment_mode`='"+ship_mode+"', `modified_date`='"+formattedDate+"', `store_id`='"+store_name+"',email='"+email+"',fax='"+fax+"' where vendor_code='"+code+"' ";
							statement.executeUpdate(query1);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
							
						}
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
					
				}
			}
		});
		mntmSave.setBackground(new Color(243, 229, 216));
		mntmSave.setIcon(new ImageIcon(save));
		mntmSave.setBounds(912, 11, 135, 48);
		mntmSave.setBorder(null);
		panel.add(mntmSave);
		
		textField_16 = new JTextField();
		textField_16.setBounds(165, 0, 116, 12);
		panel.add(textField_16);
		textField_16.setEnabled(false);
		textField_16.setEditable(false);
		textField_16.setVisible(false);
		textField_16.setColumns(10);
		
		hiddentxt = new JTextField();
		hiddentxt.setBounds(165, 43, 116, 12);
		panel.add(hiddentxt);
		hiddentxt.setEditable(false);
		hiddentxt.setVisible(false);
		hiddentxt.setColumns(10);
		
//		if(!page_type.equals("po_order"))
//		{
//			JButton btnPoOrder = new JButton("PO Order");
//			btnPoOrder.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent arg0) {
//					try {
//						new purchase_master.purchase_order_list(db_name,company, username,store_name, version,decimal,language);
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//					
//				}
//			});
//			btnPoOrder.setBounds(291, 20, 97, 25);
//			panel.add(btnPoOrder);
//		}
		
		
		
		textField_19 = new JTextField();
		textField_19.setEnabled(false);
		textField_19.setVisible(false);
		textField_19.setEditable(false);
		textField_19.setBounds(165, 23, 116, 12);
		panel.add(textField_19);
		textField_19.setColumns(10);
		
		JButton btnNewButton = new JButton("OPENING BALANCE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new opening_bal_vend(company,username,store_name, version,"2");
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(398, 20, 158, 25);
		panel.add(btnNewButton);
		
		JButton button_2 = new JButton("Print Statement");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new purchase_master.Date_range(company, username, store_name,textField.getText(), version, decimal);
			}
		});
		button_2.setBounds(564, 20, 184, 25);
		panel.add(button_2);
		
		JButton btnPoHistory = new JButton("PO HISTORY");
		btnPoHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
   	    			frame.setAlwaysOnTop(false);
   	    			if(!textField.getText().equals(""))
   	    			{
   	    				new purchase_master.vendor_po_list( company, username,store_name,version,"",textField_1.getText().toString(), language,"");	
   	    			}
   	    			else
   	    			{
   	    				JOptionPane.showMessageDialog(frame, "Select any Vendor");
   	    			}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
		btnPoHistory.setBounds(275, 20, 116, 25);
		panel.add(btnPoHistory);
		frame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, textField_1, textField_2, textField_3, textField_4, textField_5, txtUae, textField_22, textField_23, textField_9, textField_7, textField_10, textField_11, textField_13, textField_12, comboBox, textField_6, comboBox_4, comboBox_5, checkBox, chckbxNewCheckBox, button_12, frame.getContentPane(), separator, panel_1, panel_2, lblVendorCode, lblVendorName, lblAddress, lblAddress_1, lblCity, lblState, lblCountry, lblAccountNo, button_1, lblPhone, lblEmail, lblFax, panel_3, lblPhone_1, lblContactName, lblContactNumber, lblTrnNo, lblStatus, lblTotalPurchaseNumber, textField_8, lblPaidAmount, textField_15, lblTotalPendingAmount, textField_14, textField_17, lblVendorPurchaseLimit, label, panel_4, lblPromotionStatus, lblPromotionType, lblPromotionDiscount, lblStartingDate, lblEndingDate, textField_18, dateChooser, dateChooser.getCalendarButton(), dateChooser_1, dateChooser_1.getCalendarButton(), comboBox_1, lblVendorAlsoA, lblVendorTerms, lblShippingMode, lblNewLabel, textField_20, textField_21, separator_1, panel_2, lblVendorItemList, scrollPane, table, button, btnExport_1, btnAddNewItem, panel, lblVendorDetails, mntmNewMenuItem, mntmRefresh, mntmSave, textField_16, hiddentxt, textField_19, btnNewButton, button_2}));

	}
	public static void get_vndrdtls_bsd_code(String company, String input) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_vendor where vendor_code='"+input+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				textField.setText(resultSet.getString("vendor_code"));
				textField_1.setText(resultSet.getString("vendor_name"));
				textField_2.setText(resultSet.getString("vendor_addr1"));
				textField_3.setText(resultSet.getString("vendor_addr2"));
				textField_4.setText(resultSet.getString("vendor_city"));
				textField_5.setText(resultSet.getString("state"));
				txtUae.setText(resultSet.getString("vendor_country"));
				textField_7.setText(resultSet.getString("vendor_ph1"));
				textField_8.setText(resultSet.getString("vendor_purch_amt"));
				textField_9.setText(resultSet.getString("vendor_accnt_cde"));
				textField_10.setText(resultSet.getString("vendor_ph2"));
				textField_11.setText(resultSet.getString("vendor_cont_name"));
				textField_12.setText(resultSet.getString("vendor_trn"));
				textField_13.setText(resultSet.getString("vendor_cont_mob"));
				textField_14.setText(resultSet.getString("vendor_pending_amt"));
				textField_15.setText(resultSet.getString("vendor_paid_amt"));
				textField_17.setText(resultSet.getString("open_balance"));
				textField_18.setText(resultSet.getString("disc_perc"));
				textField_19.setText(resultSet.getString("vendor_code"));
				textField_22.setText(resultSet.getString("email"));
				textField_23.setText(resultSet.getString("fax"));
				if(resultSet.getString("vendor_custmer").equals("1"))
				{
					checkBox.setSelected(true);
				}
				comboBox.setSelectedItem(resultSet.getString("vendor_sts"));
				comboBox_1.setSelectedItem(resultSet.getString("vendor_promo_type"));
				textField_20.setText(resultSet.getString("vendor_payable_ac"));
				textField_21.setText(resultSet.getString("vendor_ac_name"));
				comboBox_4.setSelectedItem(resultSet.getString("vendor_terms"));
				comboBox_5.setSelectedItem(resultSet.getString("shipment_mode"));
				java.util.Date date = null;
				if(resultSet.getString("vendor_start_date")!="")
				{
					date = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("vendor_start_date"));
				}
				
				dateChooser.setDate(date);
				java.util.Date date_end = null;
				if(resultSet.getString("vendor_end_date")!="")
				{
					date_end = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("vendor_end_date"));
				}
				dateChooser_1.setDate(date_end);
				textField_16.setText(resultSet.getString("id"));
				if(resultSet.getString("vendor_custmer").equals("1"))
				{
					checkBox.setSelected(true);
				}
				get_vend_itm_list(company,input);
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
	public static void get_vndrdtls_bsd_name(String company, String input) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_vendor where vendor_name='"+input+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				textField.setText(resultSet.getString("vendor_code"));
				textField_1.setText(resultSet.getString("vendor_name"));
				textField_2.setText(resultSet.getString("vendor_addr1"));
				textField_3.setText(resultSet.getString("vendor_addr2"));
				textField_4.setText(resultSet.getString("vendor_city"));
				textField_5.setText(resultSet.getString("state"));
				txtUae.setText(resultSet.getString("vendor_country"));
				textField_7.setText(resultSet.getString("vendor_ph1"));
				textField_8.setText(resultSet.getString("vendor_purch_amt"));
				textField_9.setText(resultSet.getString("vendor_accnt_cde"));
				textField_10.setText(resultSet.getString("vendor_ph2"));
				textField_11.setText(resultSet.getString("vendor_cont_name"));
				textField_12.setText(resultSet.getString("vendor_trn"));
				textField_13.setText(resultSet.getString("vendor_cont_mob"));
				textField_14.setText(resultSet.getString("vendor_pending_amt"));
				textField_15.setText(resultSet.getString("vendor_paid_amt"));
				textField_17.setText(resultSet.getString("open_balance"));
				textField_18.setText(resultSet.getString("disc_perc"));
				textField_19.setText(resultSet.getString("vendor_code"));
				textField_22.setText(resultSet.getString("email"));
				textField_23.setText(resultSet.getString("fax"));
				if(resultSet.getString("vendor_custmer").equals("1"))
				{
					checkBox.setSelected(true);
				}
				comboBox.setSelectedItem(resultSet.getString("vendor_sts"));
				comboBox_1.setSelectedItem(resultSet.getString("vendor_promo_type"));
				textField_20.setText(resultSet.getString("vendor_payable_ac"));
				textField_21.setText(resultSet.getString("vendor_ac_name"));
				comboBox_4.setSelectedItem(resultSet.getString("vendor_terms"));
				comboBox_5.setSelectedItem(resultSet.getString("shipment_mode"));
				java.util.Date date = null;
				if(resultSet.getString("vendor_start_date")!="")
				{
					date = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("vendor_start_date"));
				}
				
				dateChooser.setDate(date);
				java.util.Date date_end = null;
				if(resultSet.getString("vendor_end_date")!="")
				{
					date_end = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("vendor_end_date"));
				}
				dateChooser_1.setDate(date_end);
				textField_16.setText(resultSet.getString("id"));
				if(resultSet.getString("vendor_custmer").equals("1"))
				{
					checkBox.setSelected(true);
				}
			}
			con.close();
			get_vend_itm_list(company,textField.getText());
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	
	
	private void  get_details(String company)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_vendor"; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			int id=1;
			while(resultSet.next())
			{
				String code = resultSet.getString("vendor_code");
				String name = resultSet.getString("vendor_name");
				String cont_nme = resultSet.getString("vendor_cont_name");
				String cont_num = resultSet.getString("vendor_cont_mob");
				String sts= resultSet.getString("vendor_sts");
				model.addRow(new Object[] { id, code, name, cont_nme,cont_num,sts});
				id++;
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	public static void get_vend_itm_list(String company,String input)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement(); 
			String query="SELECT i.item_code,i.item_name,i.ActiveForPurchase FROM "+company+".mr_vendor_items v JOIN "+company+".mr_item_master i ON v.ref_item_id = i.item_code WHERE v.ref_vendor_id = '"+input+"';";
			//String query="SELECT * FROM "+company+".mr_vendor_items where ref_vendor_id='"+input+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model_1.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model_1.removeRow(i);
			}
			int id=1;
			while(resultSet.next())
			{
				String vname=textField_1.getText();
				String itmname="";
				String itemcode="";
				String status="";
				
				itmname = resultSet.getString("item_name");
				itemcode = resultSet.getString("item_code");
				status= resultSet.getString("activeForPurchase");
				
				
//				statement = con.createStatement();
//				ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_item_master where item_code='"+resultSet.getString("ref_item_id")+"' ");
//				if(resultSet2.next())
//				{
//					itmname = resultSet2.getString("item_name");
//					itemcode = resultSet2.getString("item_code");
//					status= resultSet2.getString("activeForPurchase");
//				}
				
				model_1.addRow(new Object[] { id,  vname, itmname,itemcode,status});
				id++;
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	private void get_vendor_terms(String company)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_po_terms "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String name=resultSet.getString("po_term_code");
				comboBox_4.addItem(name);
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	private void get_ship_mode(String company)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_shipment_mode where status='1' "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String name=resultSet.getString("shipment_code");
				comboBox_5.addItem(name);
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
