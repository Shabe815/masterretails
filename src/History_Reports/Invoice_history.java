package History_Reports;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.Button;

import javax.swing.JComboBox;
import javax.swing.JButton;

import org.json.JSONException;

import sales_master.Cust_Chooser;
import javax.swing.JCheckBox;

public class Invoice_history {

	public static JFrame frame;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;
	private JComboBox<String> comboBox ;
	private JComboBox<String> comboBox_1 ;
	private JComboBox<String> comboBox_2 ;
	private JCheckBox chckbxPrintSummary;
	private JCheckBox chckbxAllRegisters; 
	private JCheckBox chckbxAllCustomers;
	public static JTextField textField;
	private JCheckBox chckbxAllPaycodes;
	private JButton btnNewButton;
	public static JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Invoice_history window = new Invoice_history("mr_pro","admin", "S01", null);
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
	public Invoice_history(String company, String username,  String store,String version) throws ParseException {
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
		
		JLabel lblDateRange = new JLabel("Historical Sale Receipts");
		lblDateRange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateRange.setBounds(12, 16, 256, 39);
		frame.getContentPane().add(lblDateRange);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(12, 109, 43, 22);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(213, 109, 27, 22);
		frame.getContentPane().add(lblTo);
		
		Date date = new Date();
		dateChooser = new JDateChooser(date);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(52, 109, 138, 22);
		frame.getContentPane().add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(271, 109, 125, 22);
		
		dateChooser_1.setDate(date);
		frame.getContentPane().add(dateChooser_1);
		
		Button button = new Button("Print");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				if(date1.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select a Date");
				}
				else if(date2.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select a Date");
				}
				else
				{
					String register="";
					if(chckbxAllRegisters.isSelected())
					{
						register="";
					}
					else
					{
						register=comboBox_2.getSelectedItem().toString();
					}
					String recipt=comboBox.getSelectedItem().toString();
					String cust_name="";
					if(chckbxAllCustomers.isSelected())
					{
						cust_name="";
					}
					else
					{
						cust_name=textField.getText();
					}
					String paycode="";
					if(chckbxAllPaycodes.isSelected())
					{
						paycode="";
					}
					else
					{
						paycode=comboBox_1.getSelectedItem().toString();
					}
					String department=textField_1.getText();
					String report="summary";
					if(chckbxPrintSummary.isSelected())
					{
						report="detail";
					}
					Connection con=Dbsetup.dbconn.getConnection();
					try {
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						report_master.Historical_invoice.report(date1,date2, con ,register,cust_name,report,paycode,recipt,company,department);
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		button.setBounds(12, 393, 194, 24);
		frame.getContentPane().add(button);
		
		JLabel lblDateRange_1 = new JLabel("Date Range : ");
		lblDateRange_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateRange_1.setBounds(12, 71, 89, 22);
		frame.getContentPane().add(lblDateRange_1);
		
		JLabel lblReportTypes = new JLabel("Receipt Types : ");
		lblReportTypes.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblReportTypes.setBounds(12, 188, 116, 25);
		frame.getContentPane().add(lblReportTypes);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(140, 189, 256, 22);
		comboBox.addItem("ALL");
		comboBox.addItem("Receipt");
		comboBox.addItem("Void");
		comboBox.addItem("Refund");
		comboBox.addItem("Discount");
		comboBox.addItem("Price Change");
		frame.getContentPane().add(comboBox);
		
		chckbxPrintSummary = new JCheckBox("Print Detail Report");
		//chckbxPrintSummary.setVisible(false);
		chckbxPrintSummary.setBounds(12, 348, 138, 25);
		chckbxPrintSummary.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(chckbxPrintSummary);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setEnabled(false);
		comboBox_1.setBounds(140, 272, 256, 22);
		frame.getContentPane().add(comboBox_1);
		
		chckbxAllRegisters = new JCheckBox("All Registers");
		chckbxAllRegisters.setSelected(true);
		chckbxAllRegisters.setBounds(12, 147, 113, 25);
		chckbxAllRegisters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxAllRegisters.isSelected())
				{
					comboBox_2.setSelectedIndex(-1);
					comboBox_2.setEnabled(false);
				}
				else
				{
					comboBox_2.setEnabled(true);
				}
			}
		});
		
		chckbxAllRegisters.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(chckbxAllRegisters);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setEnabled(false);
		comboBox_2.setBounds(140, 147, 256, 22);
		frame.getContentPane().add(comboBox_2);
		
		chckbxAllCustomers = new JCheckBox("All Customers");
		chckbxAllCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxAllCustomers.isSelected())
				{
					btnNewButton.setEnabled(false);
					textField.setText("");
				}
				else
				{
					btnNewButton.setEnabled(true);
					textField.setText("");
				}
			}
		});
		chckbxAllCustomers.setBounds(12, 229, 113, 25);
		chckbxAllCustomers.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(chckbxAllCustomers);
		chckbxAllCustomers.setSelected(true);
		
		chckbxAllPaycodes = new JCheckBox("All Paycodes");
		chckbxAllPaycodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxAllPaycodes.isSelected())
				{
					comboBox_1.setEnabled(false);
					comboBox_1.setSelectedIndex(-1);
				}
				else
				{
					comboBox_1.setEnabled(true);
				}
			}
		});
		chckbxAllPaycodes.setBounds(12, 270, 113, 25);
		chckbxAllPaycodes.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(chckbxAllPaycodes);
		chckbxAllPaycodes.setSelected(true);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(140, 232, 224, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		btnNewButton = new JButton("");
		btnNewButton.setEnabled(false);
		btnNewButton.setToolTipText("Customer List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Cust_Chooser(company,"invoice_hist",username,store,"", "", "","0");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(368, 232, 28, 21);
		frame.getContentPane().add(btnNewButton);
		
//		JLabel lblDepartment = new JLabel("Department");
//		lblDepartment.setBounds(12, 315, 116, 16);
//		frame.getContentPane().add(lblDepartment);
		
		textField_1 = new JTextField();
		textField_1.setBounds(140, 307, 224, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setVisible(false);
		
//		JButton button_1 = new JButton("");
//		button_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					new department_list(company,version,"invoicehistory");
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
//		button_1.setToolTipText("Department List");
//		button_1.setBounds(368, 307, 28, 21);
//		button_1.setIcon(new ImageIcon(serch));
//		frame.getContentPane().add(button_1);
		
		JButton btnPrintCsv = new JButton("Print Summary Report");
		btnPrintCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				if(date1.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select a Date");
				}
				else if(date2.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select a Date");
				}
				else
				{
					String register="";
					if(chckbxAllRegisters.isSelected())
					{
						register="";
					}
					else
					{
						register=comboBox_2.getSelectedItem().toString();
					}
					String recipt=comboBox.getSelectedItem().toString();
					String cust_name="";
					if(chckbxAllCustomers.isSelected())
					{
						cust_name="";
					}
					else
					{
						cust_name=textField.getText();
					}
					String paycode="";
					if(chckbxAllPaycodes.isSelected())
					{
						paycode="";
					}
					else
					{
						paycode=comboBox_1.getSelectedItem().toString();
					}
					String department=textField_1.getText();
					
					Connection con=Dbsetup.dbconn.getConnection();
					try {
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						report_master.Historical_invoice.report(date1,date2, con ,register,cust_name,"detailsummary",paycode,recipt,company,department);
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnPrintCsv.setBounds(227, 393, 169, 25);
		frame.getContentPane().add(btnPrintCsv);
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 476, 462);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		get_regdisp( company);
		get_paycodedisp(  company);
		
	}
	private void get_regdisp( String company)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_register_setup"; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String code=resultSet.getString("register_number");
                comboBox_2.addItem(code);
            }
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
	private void get_paycodedisp( String company)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_store_paycodes"; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String code=resultSet.getString("paycode");
				if(!code.equals(""))
				{
					if(!code.equals("STORE CREDIT"))
					{
						comboBox_1.addItem(code);	
					}
				}
                
            }
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
}
