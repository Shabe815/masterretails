package History_Reports;

import inventory_master.item_list_master;

import java.awt.Color;
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

public class price_change {

	private JFrame frame;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;
	private JComboBox<String> comboBox_1 ;
	private JComboBox<String> comboBox_2 ;
	private JCheckBox chckbxPrintSummary;
	private JCheckBox chckbxAllRegisters; 
	private JCheckBox chckbxAllCustomers;
	private JTextField textField;
	private JCheckBox chckbxAllPaycodes;
	private JButton btnNewButton;
	public static JTextField textField_1;
	private JButton button_1;

	private JCheckBox chckbxAllItems;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					price_change window = new price_change("","mr_pro","admin", "S01", null,"");
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
	public price_change(String db_name,String company, String username,  String store,String version,String language) throws ParseException {
		initialize(db_name,company,username,store, version,language);
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
	private void initialize(final String db_name,final String company,final String username,final String store,final String version,final String language) throws ParseException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.requestFocus();
		
		JLabel lblDateRange = new JLabel("Price Change Reports");
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
					String item="";
					if(chckbxAllItems.isSelected())
					{
						item="";
					}
					else
					{
						item=textField_1.getText();
					}
					String cust="";
					if(chckbxAllCustomers.isSelected())
					{
						cust="";
					}
					else
					{
						cust=textField.getText();
					}
					String register="";
					if(chckbxAllRegisters.isSelected())
					{
						register="";
					}
					else
					{
						register=comboBox_2.getSelectedItem().toString();
					}
					String user="";
					if(chckbxAllPaycodes.isSelected())
					{
						user="";
					}
					else
					{
						user=comboBox_1.getSelectedItem().toString();
					}
					String report="summary";
					if(chckbxPrintSummary.isSelected())
					{
						report="detail";
					}
					Connection con=Dbsetup.dbconn.getConnection();
					try {
						report_master.price_change.report(date1,date2, con ,register,cust,report,item,user,company);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		button.setBounds(116, 352, 235, 24);
		frame.getContentPane().add(button);
		
		JLabel lblDateRange_1 = new JLabel("Date Range : ");
		lblDateRange_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateRange_1.setBounds(12, 71, 89, 22);
		frame.getContentPane().add(lblDateRange_1);
		
		chckbxPrintSummary = new JCheckBox("Print Detail Report");
		chckbxPrintSummary.setVisible(false);
		chckbxPrintSummary.setBounds(12, 311, 138, 25);
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
				}
				else
				{
					btnNewButton.setEnabled(true);
				}
			}
		});
		chckbxAllCustomers.setBounds(12, 229, 113, 25);
		chckbxAllCustomers.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(chckbxAllCustomers);
		chckbxAllCustomers.setSelected(true);
		
		chckbxAllPaycodes = new JCheckBox("All Users");
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
					new Cust_Chooser(company,"sales_summary",username,store,"", "", "","1");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(368, 232, 28, 21);
		frame.getContentPane().add(btnNewButton);
		
		chckbxAllItems = new JCheckBox("All  Items");
		chckbxAllItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxAllItems.isSelected())
				{
					button_1.setEnabled(false);
				}
				else
				{
					button_1.setEnabled(true);
				}
			}
		});
		chckbxAllItems.setSelected(true);
		chckbxAllItems.setBackground(new Color(243, 229, 216));
		chckbxAllItems.setBounds(12, 181, 113, 25);
		frame.getContentPane().add(chckbxAllItems);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(140, 184, 224, 22);
		frame.getContentPane().add(textField_1);
		
		button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=Dbsetup.dbconn.getConnection();
					new item_list_master(db_name,company, "price", store, version,0,con,"",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setToolTipText("Customer List");
		button_1.setEnabled(false);
		button_1.setBounds(368, 184, 28, 21);
		button_1.setIcon(new ImageIcon(serch));
		frame.getContentPane().add(button_1);
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 476, 432);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		get_regdisp( company);
		 get_userdisp( company);
		
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
	private void get_userdisp( String company)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_users"; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String code=resultSet.getString("user_name");
				if(!code.equals(""))
				{
					
						comboBox_1.addItem(code);	
				}
                
            }
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
}
