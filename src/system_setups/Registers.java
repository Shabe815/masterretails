package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JPanel;

import org.json.JSONException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Registers<GUI> {

	static JFrame frame;
//	private DefaultTableModel model;
	static JTextField textField;
	static JTextField textField_8;
	private static JTextField textField_18;
	private static JTextField textField_20;
	private static JTextField textField_21;
	private static JTextField textField_22;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;
	private static JTextField textField_5;
	private static JTextField textField_6;
	private static JTextField textField_7;
	private static JTextField textField_9;
	private static JTextField textField_10;
	private static JTextField textField_11;
	private static JTextField textField_12;
	private static JTextField textField_13;
	private static JTextField textField_15;
	private static JTextArea txtrthankyouVisitAgain;
	private static JComboBox<String> comboBox;
	private static JComboBox<String> comboBox_1;
	private static JComboBox<String> comboBox_2;
	private static JComboBox<String> comboBox_3;
	private static JComboBox<String> comboBox_4;
	private static JComboBox<String> comboBox_5;
	private static JComboBox<String> comboBox_6;
	private static JComboBox<String> comboBox_7;
	private static JComboBox<String> comboBox_8;
	private static JComboBox<String> comboBox_9;
	private static JComboBox<String> comboBox_10;
	private static JCheckBox checkBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					Registers<?> window = new Registers<Object>("mr_pro","admin", null);
					Registers.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws JSONException 
	 */
	public Registers(String company,String username,String version) throws JSONException {
		initialize(company,username, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @throws JSONException 
	 */
	private void initialize(final String company,final String username,final String version) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBounds(570, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		frame.getContentPane().add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_8.setText("");
				comboBox.setSelectedItem("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				comboBox_1.setSelectedItem("");
				comboBox_8.setSelectedItem("");
				textField_5.setText("");
				textField_7.setText("");
				textField_6.setText("");
				textField_9.setText("");
				textField_10.setText("");
				checkBox.setSelected(false);
				textField_11.setText("0");
				textField_12.setText("0");
				textField_13.setText("0");
				textField_15.setText("0");
				comboBox_10.setSelectedItem("");
				comboBox_9.setSelectedItem("");
				comboBox_2.setSelectedItem("");
				comboBox_3.setSelectedItem("");
				comboBox_4.setSelectedItem("");
				comboBox_5.setSelectedItem("");
				textField_18.setText("");
				textField_20.setText("");
				textField_21.setText("");
				textField_22.setText("");
				txtrthankyouVisitAgain.setText("");
				comboBox_7.setSelectedItem("");
				comboBox_6.setSelectedItem("");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBounds(630, 7, 80, 48);
		frame.getContentPane().add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String reg_no=textField.getText();
				String reg_name=textField_8.getText();
				String store=comboBox.getSelectedItem().toString();
				String invoice_num=textField_1.getText();
				String next_num=textField_2.getText();
				String store_credit=textField_3.getText();
				String float_amt=textField_4.getText();
				String pole=comboBox_1.getSelectedItem().toString();
				String initl_code=comboBox_8.getSelectedItem().toString();
				String ready_msg1=textField_5.getText();
				String ready_msg2=textField_7.getText();
				String non_ready_msg1=textField_6.getText();
				String non_ready_msg2=textField_9.getText();
				String logout_msg=textField_10.getText();
				String disble_pole="0";
				if(checkBox.isSelected())
				{
					disble_pole="1";
				}
				String buand_rate_pole1=textField_11.getText();
				if(buand_rate_pole1.equals(""))
				{
					buand_rate_pole1="0";
				}
				String buand_rate_pole2=textField_12.getText();
				if(buand_rate_pole2.equals(""))
				{
					buand_rate_pole2="0";
				}
				String buand_rate_pole3=textField_13.getText();
				if(buand_rate_pole3.equals(""))
				{
					buand_rate_pole3="0";
				}
				String buand_rate_pole4=textField_15.getText();
				if(buand_rate_pole4.equals(""))
				{
					buand_rate_pole4="0";
				}
				String buand_rate_pole=buand_rate_pole1+"-"+buand_rate_pole2+"-"+buand_rate_pole3+"-"+buand_rate_pole4;
				String printer1=comboBox_10.getSelectedItem().toString();
				String printer2=comboBox_9.getSelectedItem().toString();
				String printer_port=comboBox_2.getSelectedItem().toString();
				String printerlink = Base64.encode(printer_port.getBytes());
				String cutting_code=comboBox_3.getSelectedItem().toString();
				String receipt_format=comboBox_4.getSelectedItem().toString();
				String printer_method=comboBox_5.getSelectedItem().toString();
				String buand_rate_printer1=textField_18.getText();
				if(buand_rate_printer1.equals(""))
				{
					buand_rate_printer1="0";
				}
				String buand_rate_printer2=textField_20.getText();
				if(buand_rate_printer2.equals(""))
				{
					buand_rate_printer2="0";
				}
				String buand_rate_printer3=textField_21.getText();
				if(buand_rate_printer3.equals(""))
				{
					buand_rate_printer3="0";
				}
				String buand_rate_printer4=textField_22.getText();
				if(buand_rate_printer4.equals(""))
				{
					buand_rate_printer4="0";
				}
				String buand_rate_printer=buand_rate_printer1+"-"+buand_rate_printer2+"-"+buand_rate_printer3+"-"+buand_rate_printer4;
				String msg=txtrthankyouVisitAgain.getText();
				String cash_drawer_opening_code=comboBox_7.getSelectedItem().toString();
				String cash_drawer_port=comboBox_6.getSelectedItem().toString();
				
				if(reg_no.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter or Select Register Number");
				}
				else if(reg_name.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter Register Name");
				}
				else
				{
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					try {
						statement = con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_no+"' ");
						if(resultSet.next())
						{
							String query="update  "+company+".mr_register_setup set register_name='"+reg_name+"',store_number='"+store+"',invoice_number='"+invoice_num+"',next_num='"+next_num+"',store_credit_num='"+store_credit+"',float_amount='"+float_amt+"',pole_display_port='"+pole+"',initial_code='"+initl_code+"',ready_msg1='"+ready_msg1+"',ready_msg2='"+ready_msg2+"',not_ready_msg1='"+non_ready_msg1+"',not_ready_msg2='"+non_ready_msg2+"',logout_msg1='"+logout_msg+"',pole_disable='"+disble_pole+"',baund_rate_pole='"+buand_rate_pole+"',printer1='"+printer1+"',printer2='"+printer2+"',printer_port='"+printerlink+"',cutting_code='"+cutting_code+"',receipt_format='"+receipt_format+"',printer_method='"+printer_method+"',baund_rate_printer='"+buand_rate_printer+"',register_msg='"+msg+"',cash_drawer_port='"+cash_drawer_port+"',opening_codes='"+cash_drawer_opening_code+"',modified_date='"+formattedDate+"' where register_number='"+reg_no+"' ";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
						else
						{
							String query="INSERT INTO "+company+".mr_register_setup(`register_number`, `register_name`, `store_number`, `invoice_number`, `next_num`, `store_credit_num`, `float_amount`, `pole_display_port`, `initial_code`, `ready_msg1`, `ready_msg2`, `not_ready_msg1`, `not_ready_msg2`, `logout_msg1`, `pole_disable`, `baund_rate_pole`, `printer1`, `printer2`, `printer_port`, `cutting_code`, `receipt_format`, `printer_method`, `baund_rate_printer`, `register_msg`, `cash_drawer_port`, `opening_codes`, `created_date`, `modified_date`) VALUES('"+reg_no+"','"+reg_name+"','"+store+"','"+invoice_num+"','"+next_num+"','"+store_credit+"','"+float_amt+"','"+pole+"','"+initl_code+"','"+ready_msg1+"','"+ready_msg2+"','"+non_ready_msg1+"','"+non_ready_msg2+"','"+logout_msg+"','"+disble_pole+"','"+buand_rate_pole+"','"+printer1+"','"+printer2+"','"+printerlink+"','"+cutting_code+"','"+receipt_format+"','"+printer_method+"','"+buand_rate_printer+"','"+msg+"','"+cash_drawer_port+"','"+cash_drawer_opening_code+"',,'"+formattedDate+"','"+formattedDate+"') ";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBounds(712, 7, 70, 48);
		frame.getContentPane().add(menuItem_2);
		
		
		JLabel lblCompanyName = new JLabel("Register Number:");
		lblCompanyName.setBounds(65, 81, 108, 21);
		lblCompanyName.setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.getContentPane().add(lblCompanyName);
		
		Image register = new ImageIcon(this.getClass().getResource("/Cash-register.png")).getImage();
		JLabel lblCompanySetup = new JLabel("Register");
		lblCompanySetup.setBounds(21, 11, 228, 44);
		lblCompanySetup.setIcon(new ImageIcon(register));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 800, 2);
		frame.getContentPane().add(separator);
		    
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.setBackground(Color.WHITE);
		textField.setBounds(193, 79, 73, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		    
		    JLabel lblNewLabel = new JLabel("New label");
		    lblNewLabel.setBounds(478, 206, 4, -6);
		    frame.getContentPane().add(lblNewLabel);
		    
		    JLabel lblPoNumber = new JLabel("Register Name:");
		    lblPoNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		    lblPoNumber.setBounds(65, 110, 113, 21);
		    frame.getContentPane().add(lblPoNumber);
		    
		    textField_8 = new JTextField();
		    textField_8.setBounds(193, 108, 195, 25);
		    frame.getContentPane().add(textField_8);
		    textField_8.setColumns(10);
		    
		    JSeparator separator_1 = new JSeparator();
		    separator_1.setBounds(0, 142, 795, 2);
		    frame.getContentPane().add(separator_1);
		    
		    JSeparator separator_2 = new JSeparator();
		    separator_2.setOrientation(SwingConstants.VERTICAL);
		    separator_2.setBounds(356, 142, 2, 480);
		    frame.getContentPane().add(separator_2);
		    
		    Image search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		    JButton btnNewButton = new JButton("");
		    btnNewButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		try {
						new register_list( company, version);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    });
		    btnNewButton.setIcon(new ImageIcon(search));
		    btnNewButton.setBounds(276, 77, 50, 25);
		    frame.getContentPane().add(btnNewButton);
		    
		    JButton btnTestDrawer = new JButton("Test Drawer");
		    btnTestDrawer.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		byte[] open = {27,112,48,55,121};
			        PrintService pservice = 
			        PrintServiceLookup.lookupDefaultPrintService(); 
			        DocPrintJob job = pservice.createPrintJob();
			        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
			        Doc doc = new SimpleDoc(open,flavor,null);
			        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
			        try {
			            job.print(doc, aset);
			            
			        } catch (PrintException ex) {
			            System.out.println(ex.getMessage());
			            JOptionPane.showMessageDialog(frame, ex.getMessage());
			        }
		    	}
		    });
		    btnTestDrawer.setBounds(411, 81, 108, 38);
		    frame.getContentPane().add(btnTestDrawer);
		    
		    JButton btnTestPrinter = new JButton("Test Printer");
		    btnTestPrinter.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(textField.getText().equals(""))
		    		{
		    			JOptionPane.showMessageDialog(frame, "Select a Register from the list");
		    		}
		    		else
		    		{
		    			
		    			String printer1=comboBox_10.getSelectedItem().toString();
		    			try {
							test_print(company,printer1);
						} catch (PrintException | UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
		    		
		    	}
		    });
		    btnTestPrinter.setBounds(529, 81, 108, 38);
		    frame.getContentPane().add(btnTestPrinter);
		    
		    JButton btnTestPoleDisplay = new JButton("Test Pole Display");
		    btnTestPoleDisplay.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(textField.getText().equals(""))
		    		{
		    			JOptionPane.showMessageDialog(frame, "Select a Register from the list");
		    		}
		    		else
		    		{
			    		try {
			    			String display_port=comboBox_1.getSelectedItem().toString().trim();
			    			String print_val="cmd /c \"CLS>"+display_port+"&&ECHO MASTERRETAILS>"+display_port+"&&ECHO WELCOMES YOU>"+display_port+"";
			    			Runtime.getRuntime().exec(print_val);
			    			
//			    			SerialPort serialPort = new SerialPort(display_port);
//			    			serialPort.openPort();//Open serial port
//			    	        serialPort.setParams(SerialPort.BAUDRATE_9600, 
//			    	                             SerialPort.DATABITS_8,
//			    	                             SerialPort.STOPBITS_1,
//			    	                             SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
//			    	        serialPort.writeBytes("MASTERRETAILS       ".getBytes());//Write data to port
//			    	        serialPort.writeBytes("WELCOMES YOU        ".getBytes());//Write data to port
//			    	        //String clear ="0C";
//			    	       
//			    	        		//new byte[1] { 0x0C }; // send hex code 0C to clear screen
//			    	        //serialPort.writeBytes(clear.getBytes());
//			    	        //serialPort.writeBytes("This is a test string".getBytes());
//			    	        serialPort.closePort();//Close serial port
			    			
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
		    		}
		    	}
		    		
		    });
		    btnTestPoleDisplay.setBounds(648, 81, 137, 38);
		    frame.getContentPane().add(btnTestPoleDisplay);
		    
		    Image printer = new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		    
		    
		    Image invoice = new ImageIcon(this.getClass().getResource("/invoice-icon1.png")).getImage();
		    
		    JSeparator separator_5 = new JSeparator();
		    separator_5.setBounds(356, 531, 440, 2);
		    frame.getContentPane().add(separator_5);
		    
		    JLabel lblCashDrawerPort = new JLabel("Cash Drawer Port :");
		    lblCashDrawerPort.setBounds(380, 555, 113, 14);
		    frame.getContentPane().add(lblCashDrawerPort);
		    
		    JLabel lblOpeningCodes = new JLabel("Opening Codes :");
		    lblOpeningCodes.setBounds(380, 593, 102, 14);
		    frame.getContentPane().add(lblOpeningCodes);
		    
		    comboBox_6 = new JComboBox<String>();
		    comboBox_6.setModel(new DefaultComboBoxModel<String>(new String[] {"LPT1", "LPT2", "LPT3", "LPT4"}));
		    comboBox_6.setBounds(503, 552, 207, 20);
		    frame.getContentPane().add(comboBox_6);
		    
		    comboBox_7 = new JComboBox <String>();
		    comboBox_7.setBounds(503, 590, 207, 20);
		    comboBox_7.setModel(new DefaultComboBoxModel<String>(new String[] {"Epson", "Star", "Citizen", "Samsung", "Tysso"}));
		    frame.getContentPane().add(comboBox_7);
		    
		    JSeparator separator_6 = new JSeparator();
		    separator_6.setBounds(0, 622, 795, 2);
		    frame.getContentPane().add(separator_6);
		    
		    JButton btnNewButton_2 = new JButton("SET CONTROLS");
		    btnNewButton_2.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(textField.getText().equals(""))
		    		{
		    			JOptionPane.showMessageDialog(frame, "Please Enter or Select Register Number");
		    		}
		    		else
		    		{
		    			new Register_Controls(company,textField.getText(),textField_8.getText(), version);
		    		}
		    		
		    	}
		    });
		    btnNewButton_2.setBounds(539, 640, 140, 21);
		    frame.getContentPane().add(btnNewButton_2);
		    
		    JPanel panel = new JPanel();
		    panel.setBounds(0, 144, 358, 478);
		    frame.getContentPane().add(panel);
		    panel.setBackground(new Color(243, 229, 216));
		    panel.setLayout(null);
		    
		    JSeparator separator_3 = new JSeparator();
		    separator_3.setBounds(0, 167, 354, 2);
		    panel.add(separator_3);
		    
		    JSeparator separator_4 = new JSeparator();
		    separator_4.setBounds(0, 434, 354, 2);
		    panel.add(separator_4);
		    
		    comboBox = new JComboBox<String>();
		    comboBox.setBounds(137, 13, 207, 20);
		    Connection con=Dbsetup.dbconn.getConnection();
			Statement statement;
		    try {
				statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store");
				if(resultSet.next())
				{
					 String id=resultSet.getString("store_id");
			         comboBox.addItem(id);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        panel.add(comboBox);
		    
		    JLabel label = new JLabel("Store Number :");
		    label.setBounds(21, 13, 106, 21);
		    panel.add(label);
		    
		    JLabel label_1 = new JLabel("Invoice Number :");
		    label_1.setBounds(21, 45, 106, 21);
		    panel.add(label_1);
		    
		    textField_1 = new JTextField();
		    textField_1.setColumns(10);
		    textField_1.setBounds(137, 45, 207, 20);
		    panel.add(textField_1);
		    
		    textField_2 = new JTextField();
		    textField_2.setColumns(10);
		    textField_2.setBounds(137, 74, 207, 20);
		    panel.add(textField_2);
		    
		    JLabel lblNextNumber = new JLabel("Next Number :");
		    lblNextNumber.setBounds(21, 77, 106, 14);
		    panel.add(lblNextNumber);
		    
		    JLabel label_3 = new JLabel("Store Credit No:");
		    label_3.setBounds(21, 108, 106, 14);
		    panel.add(label_3);
		    
		    textField_3 = new JTextField();
		    textField_3.setColumns(10);
		    textField_3.setBounds(137, 105, 207, 20);
		    panel.add(textField_3);
		    
		    textField_4 = new JTextField();
		    textField_4.setColumns(10);
		    textField_4.setBounds(137, 136, 207, 20);
		    panel.add(textField_4);
		    
		    JLabel label_4 = new JLabel("Default Float Amt:");
		    label_4.setBounds(21, 139, 113, 14);
		    panel.add(label_4);
		    
		    comboBox_1 = new JComboBox<String>();
		    comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"LPT1", "LPT2", "LPT3", "LPT4", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM10", "COM11", "COM12", "COM13", "COM14", "COM15"}));
		    comboBox_1.setBounds(137, 179, 207, 20);
		    panel.add(comboBox_1);
		    
		    JLabel label_5 = new JLabel("Pole Display Port:");
		    label_5.setBounds(21, 182, 106, 14);
		    panel.add(label_5);
		    
		    JLabel label_6 = new JLabel("Initialization Code :");
		    label_6.setBounds(21, 213, 106, 14);
		    panel.add(label_6);
		    
		    comboBox_8 = new JComboBox<String>();
		    comboBox_8.setBounds(137, 210, 207, 20);
		    comboBox_8.addItem("DIGI");
		    panel.add(comboBox_8);
		    
		    JLabel label_7 = new JLabel("Message On Pole Display");
		    label_7.setBounds(137, 242, 207, 14);
		    panel.add(label_7);
		    
		    textField_5 = new JTextField();
		    textField_5.setText("Welcome");
		    textField_5.setColumns(10);
		    textField_5.setBounds(137, 262, 207, 20);
		    panel.add(textField_5);
		    
		    JLabel label_8 = new JLabel("Ready :");
		    label_8.setBounds(21, 265, 73, 14);
		    panel.add(label_8);
		    
		    JLabel label_9 = new JLabel("Not Ready :");
		    label_9.setBounds(21, 327, 73, 14);
		    panel.add(label_9);
		    
		    textField_6 = new JTextField();
		    textField_6.setText("Register Closed");
		    textField_6.setColumns(10);
		    textField_6.setBounds(137, 324, 207, 20);
		    panel.add(textField_6);
		    
		    textField_7 = new JTextField();
		    textField_7.setColumns(10);
		    textField_7.setBounds(137, 293, 207, 20);
		    panel.add(textField_7);
		    
		    textField_9 = new JTextField();
		    textField_9.setColumns(10);
		    textField_9.setBounds(137, 355, 207, 20);
		    panel.add(textField_9);
		    
		    textField_10 = new JTextField();
		    textField_10.setText("Logged Out");
		    textField_10.setColumns(10);
		    textField_10.setBounds(137, 386, 207, 20);
		    panel.add(textField_10);
		    
		    JLabel label_10 = new JLabel("Logged Out :");
		    label_10.setBounds(21, 389, 73, 14);
		    panel.add(label_10);
		    
		    checkBox = new JCheckBox("Disable Pole Display");
		    checkBox.setBackground(new Color(243, 229, 216));
		    checkBox.setBounds(137, 408, 148, 21);
		    panel.add(checkBox);
		    
		    JLabel label_11 = new JLabel("Baud Rate  P D:");
		    label_11.setBounds(21, 447, 86, 22);
		    panel.add(label_11);
		    
		    textField_11 = new JTextField();
		    textField_11.setColumns(10);
		    textField_11.setBounds(112, 447, 73, 20);
		    panel.add(textField_11);
		    
		    textField_12 = new JTextField();
		    textField_12.setColumns(10);
		    textField_12.setBounds(195, 447, 31, 20);
		    panel.add(textField_12);
		    
		    textField_13 = new JTextField();
		    textField_13.setColumns(10);
		    textField_13.setBounds(236, 447, 31, 20);
		    panel.add(textField_13);
		    
		    textField_15 = new JTextField();
		    textField_15.setColumns(10);
		    textField_15.setBounds(277, 448, 31, 20);
		    panel.add(textField_15);
		    
		   
		    
		    JPanel panel_1 = new JPanel();
		    panel_1.setBounds(360, 150, 424, 383);
		    panel_1.setBackground(new Color(243, 229, 216));
		    frame.getContentPane().add(panel_1);
		    panel_1.setLayout(null);
		    
		    txtrthankyouVisitAgain = new JTextArea();
		    txtrthankyouVisitAgain.setBounds(142, 263, 207, 112);
		    panel_1.add(txtrthankyouVisitAgain);
		    txtrthankyouVisitAgain.setText("**Thankyou Visit Again");
		    
		    JLabel lblRegisterMessage = new JLabel("Register Message :");
		    lblRegisterMessage.setBounds(19, 263, 113, 14);
		    panel_1.add(lblRegisterMessage);
		    
		    JLabel lblBaudRate_1 = new JLabel("Baud Rate :");
		    lblBaudRate_1.setBounds(139, 233, 67, 22);
		    panel_1.add(lblBaudRate_1);
		    
		    textField_18 = new JTextField();
		    textField_18.setBounds(214, 231, 67, 20);
		    panel_1.add(textField_18);
		    textField_18.setColumns(10);
		    
		    textField_20 = new JTextField();
		    textField_20.setBounds(284, 231, 31, 20);
		    panel_1.add(textField_20);
		    textField_20.setColumns(10);
		    
		    textField_21 = new JTextField();
		    textField_21.setBounds(316, 231, 31, 20);
		    panel_1.add(textField_21);
		    textField_21.setColumns(10);
		    
		    textField_22 = new JTextField();
		    textField_22.setBounds(349, 231, 31, 20);
		    panel_1.add(textField_22);
		    textField_22.setColumns(10);
		    
		    comboBox_5 = new JComboBox<String>();
		    comboBox_5.setBounds(142, 205, 228, 20);
		    panel_1.add(comboBox_5);
		    comboBox_5.setModel(new DefaultComboBoxModel<String>(new String[] {"WINDOWS PRINTER", "NORMAL PRINTER"}));
		    
		    JLabel lblPrinterMethod = new JLabel("Printer Method:");
		    lblPrinterMethod.setBounds(19, 208, 102, 14);
		    panel_1.add(lblPrinterMethod);
		    
		    JLabel lblRecieptFormat = new JLabel("Reciept Format :");
		    lblRecieptFormat.setBounds(19, 180, 102, 14);
		    panel_1.add(lblRecieptFormat);
		    
		    comboBox_4 = new JComboBox<String>();
		    comboBox_4.setBounds(142, 177, 228, 20);
		    panel_1.add(comboBox_4);
		    JButton button_2 = new JButton("");
		    button_2.setBounds(371, 171, 50, 21);
		    panel_1.add(button_2);
		    button_2.setIcon(new ImageIcon(invoice));
		    
		    comboBox_3 = new JComboBox<String>();
		    comboBox_3.setBounds(142, 149, 228, 20);
		    panel_1.add(comboBox_3);
		    comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"Epson", "Star", "Citizen", "Samsung", "Tysso"}));
		    
		    JLabel lblCuttingCodes = new JLabel("Cutting Codes :");
		    lblCuttingCodes.setBounds(19, 155, 86, 14);
		    panel_1.add(lblCuttingCodes);
		    
		    JButton btnAssignPrinters = new JButton("Assign Printers");
		    btnAssignPrinters.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		try
		    		{
		    			if(textField.getText().equals(""))
			    		{
			    			JOptionPane.showMessageDialog(frame, "Select a Register from the list");
			    		}
			    		else
			    		{
			    			new assign_kot_printer(company, version,textField.getText());
			    		}
		    		}
		    		catch(Exception e)
		    		{
		    			JOptionPane.showConfirmDialog(frame, e.getMessage());
		    		}
		    	}
		    });
		    btnAssignPrinters.setBounds(142, 118, 139, 23);
		    panel_1.add(btnAssignPrinters);
		    
		    JLabel lblprinterPort = new JLabel("3. Printer (Network)");
		    lblprinterPort.setBounds(19, 93, 126, 14);
		    panel_1.add(lblprinterPort);
		    
		    
		    comboBox_2 = new JComboBox<String>();
		    comboBox_2.setBounds(142, 90, 228, 20);
		    panel_1.add(comboBox_2);
		    
		    JButton button_1 = new JButton("");
		    button_1.setBounds(374, 90, 50, 21);
		    panel_1.add(button_1);
		    button_1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(textField.getText().equals(""))
		    		{
		    			JOptionPane.showMessageDialog(frame, "Select a Register from the list");
		    		}
		    		else
		    		{
		    			
		    			String printer1=comboBox_2.getSelectedItem().toString();
		    			try {
							test_print(company,printer1);
						} catch (PrintException | UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
		    	}
		    });
		    button_1.setIcon(new ImageIcon(printer));
		    
		   
	        
	        	
		    
		    comboBox_9 = new JComboBox<String>();
		    comboBox_9.setBounds(142, 60, 228, 22);
		    panel_1.add(comboBox_9);
		    JButton button = new JButton("");
		    button.setBounds(374, 39, 50, 21);
		    panel_1.add(button);
		    button.setIcon(new ImageIcon(printer));
		    
		    comboBox_10 = new JComboBox<String>();
		    comboBox_10.setBounds(142, 30, 228, 22);
		    panel_1.add(comboBox_10);
		    
		    JLabel lblprinter_1 = new JLabel("2.Printer 2(A4):");
		    lblprinter_1.setBounds(19, 64, 86, 14);
		    panel_1.add(lblprinter_1);
		    
		    JLabel lblprinter = new JLabel("1.Printer 1(Reciept):");
		    lblprinter.setBounds(19, 34, 113, 14);
		    panel_1.add(lblprinter);
		    
		    JLabel lblNewLabel_2 = new JLabel("Printer Integration :");
		    lblNewLabel_2.setBounds(19, 8, 139, 14);
		    panel_1.add(lblNewLabel_2);
		    button.setVisible(false);
		    button_2.setVisible(false);
		    comboBox_4.addItem("Format 1");
		    
		    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		    for (PrintService printer1 : printServices)
		    {
		    	comboBox_2.addItem(printer1.getName());
		    }
		    
		    
		    for (PrintService printer1 : printServices)
		    {
		    	comboBox_9.addItem(printer1.getName());
		    }
		    for (PrintService printer1 : printServices)
		    {
		    	comboBox_10.addItem(printer1.getName());
		    }
		    
		    
		//frame.getContentPane().add(table);
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(0, 0, 800, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	static void get_registeredisp(String company, String reg_num)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_num +"' ");
			if(resultSet.next())
			{
				textField.setText(resultSet.getString("register_number"));
				textField_8.setText(resultSet.getString("register_name"));
				comboBox.setSelectedItem(resultSet.getString("store_number"));
				textField_1.setText(resultSet.getString("invoice_number"));
				textField_2.setText(resultSet.getString("next_num"));
				textField_3.setText(resultSet.getString("store_credit_num"));
				textField_4.setText(resultSet.getString("float_amount"));
				comboBox_1.setSelectedItem(resultSet.getString("pole_display_port"));
				comboBox_8.setSelectedItem(resultSet.getString("initial_code"));
				textField_5.setText(resultSet.getString("ready_msg1"));
				textField_7.setText(resultSet.getString("ready_msg2"));
				textField_6.setText(resultSet.getString("not_ready_msg1"));
				textField_9.setText(resultSet.getString("not_ready_msg2"));
				textField_10.setText(resultSet.getString("logout_msg1"));
				if(resultSet.getString("pole_disable").equals("1"))
				{
					checkBox.setSelected(true);
				}
				else
				{
					checkBox.setSelected(false);
				}
				String baund_rate_pole=resultSet.getString("baund_rate_pole");
				String[] rate = baund_rate_pole.split("-");
				textField_11.setText(rate[0]);
				textField_12.setText(rate[1]);
				textField_13.setText(rate[2]);
				textField_15.setText(rate[3]);
				comboBox_10.setSelectedItem(resultSet.getString("printer1"));
				comboBox_9.setSelectedItem(resultSet.getString("printer2"));
				byte[] printers=Base64.decode(resultSet.getString("printer_port").toString()); //resultSet.getString("printer");
				String printer = new String(printers, StandardCharsets.UTF_8);
				comboBox_2.setSelectedItem(printer);
				comboBox_3.setSelectedItem(resultSet.getString("cutting_code"));
				comboBox_4.setSelectedItem(resultSet.getString("receipt_format"));
				comboBox_5.setSelectedItem(resultSet.getString("printer_method"));
				String buand_rate_printer=resultSet.getString("baund_rate_printer");
				String[] parts = buand_rate_printer.split("-");
				textField_18.setText(parts[0]);
				textField_20.setText(parts[1]);
				textField_21.setText(parts[2]);
				textField_22.setText(parts[3]);
				txtrthankyouVisitAgain.setText(resultSet.getString("register_msg"));
				comboBox_7.setSelectedItem(resultSet.getString("opening_codes"));
				comboBox_6.setSelectedItem(resultSet.getString("cash_drawer_port"));
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	public void test_print(String company, String printer) throws PrintException, UnsupportedEncodingException
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			report_master.Test_print.report(con, printer,company); 
	        
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
