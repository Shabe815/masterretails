package PO_reports;

import inventory_master.item_list_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.Button;

import javax.swing.JButton;

import org.json.JSONException;

import purchase_master.vendor_list;
import system_setups.cat_list;
import system_setups.department_list;
import system_setups.sub_dept_list;

import javax.swing.JCheckBox;

public class vendor_items_purchase {

	private JFrame frame;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;
	private JCheckBox chckbxAllCustomers;
	private JCheckBox chckbxAllItems;
	private JCheckBox chckbxAllDepartments;
	private JCheckBox chckbxAllSubdepartments;
	private JCheckBox chckbxAllCategory ;
	private JCheckBox chckbxAllSubcategory;
	public static JTextField textField;
	private JButton btnNewButton;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	public static JTextField textField_1;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_4;
	public static JTextField textField_5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vendor_items_purchase window = new vendor_items_purchase("mr_pro","admin", "S01", null,"");
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
	public vendor_items_purchase(String company, String username,  String store,String version,String language) throws ParseException {
		initialize(company,username,store, version, language);
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
	private void initialize(final String company,final String username,final String store,final String version,final String language) throws ParseException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.requestFocus();
		
		JLabel lblDateRange = new JLabel("ITEM PURCHASED ANALYSIS");
		lblDateRange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateRange.setBounds(12, 13, 256, 39);
		frame.getContentPane().add(lblDateRange);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(12, 100, 43, 22);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(319, 100, 27, 22);
		frame.getContentPane().add(lblTo);
		
		Date date = new Date();
		dateChooser = new JDateChooser(date);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(175, 100, 125, 22);
		frame.getContentPane().add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(350, 100, 113, 22);
		
		dateChooser_1.setDate(date);
		frame.getContentPane().add(dateChooser_1);
		
		Button button = new Button("Print");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
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
						
						String vendor="";
						if(chckbxAllCustomers.isSelected())
						{
							vendor="";
						}
						else
						{
							vendor=textField.getText();
						}
						String item="";
						if(chckbxAllItems.isSelected())
						{
							item="";
						}
						else
						{
							item=textField_1.getText();
						}
						
						String dept="";
						if(chckbxAllDepartments.isSelected())
						{
							dept="";
						}
						else
						{
							dept=textField_2.getText();
						}
						
						String subdept="";
						if(chckbxAllSubdepartments.isSelected())
						{
							subdept="";
						}
						else
						{
							subdept=textField_3.getText();
						}
						
						String cat="";
						if(chckbxAllCategory.isSelected())
						{
							cat="";
						}
						else
						{
							cat=textField_4.getText();
						}
						
						String subcat="";
						if(chckbxAllSubcategory.isSelected())
						{
							subcat="";
						}
						else
						{
							subcat=textField_5.getText();
						}
						
						
						Connection con=Dbsetup.dbconn.getConnection();
						PO_reports.vendor_items_rpt.report(date1,date2, con ,vendor,item,dept,company,subdept,cat,subcat);
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}
				catch(Exception e)
				{
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					JOptionPane.showMessageDialog(frame, e);
					
				}
			}
		});
		button.setBounds(111, 363, 235, 30);
		frame.getContentPane().add(button);
		
		JLabel lblDateRange_1 = new JLabel("Date Range : ");
		lblDateRange_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateRange_1.setBounds(12, 65, 89, 22);
		frame.getContentPane().add(lblDateRange_1);
		
		chckbxAllCustomers = new JCheckBox("All Vendors");
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
				}
			}
		});
		chckbxAllCustomers.setBounds(12, 135, 113, 25);
		chckbxAllCustomers.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(chckbxAllCustomers);
		chckbxAllCustomers.setSelected(true);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(175, 135, 256, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		btnNewButton = new JButton("");
		btnNewButton.setEnabled(false);
		btnNewButton.setToolTipText("Vendor List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new vendor_list(company, "po_items", version,"",username);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(435, 135, 28, 25);
		frame.getContentPane().add(btnNewButton);
		
		chckbxAllItems = new JCheckBox("All Items");
		chckbxAllItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxAllItems.isSelected())
				{
					button_1.setEnabled(false);
					textField_1.setText("");
				}
				else
				{
					button_1.setEnabled(true);
				}
			}
		});
		chckbxAllItems.setSelected(true);
		chckbxAllItems.setBackground(new Color(243, 229, 216));
		chckbxAllItems.setBounds(12, 173, 113, 25);
		frame.getContentPane().add(chckbxAllItems);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(175, 173, 256, 25);
		frame.getContentPane().add(textField_1);
		
		button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=Dbsetup.dbconn.getConnection();
					new item_list_master(company,company, "po_report", "", version,2,con,"",language);
					
				} catch (JSONException e) {
					e.printStackTrace();
					frame.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(frame, e.getMessage());
					frame.setAlwaysOnTop(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setToolTipText("Item List");
		button_1.setEnabled(false);
		button_1.setBounds(435, 173, 28, 25);
		button_1.setIcon(new ImageIcon(serch));
		frame.getContentPane().add(button_1);
		
		chckbxAllDepartments = new JCheckBox("All Departments");
		chckbxAllDepartments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxAllDepartments.isSelected())
				{
					button_2.setEnabled(false);
					textField_2.setText("");
				}
				else
				{
					button_2.setEnabled(true);
				}
			}
		});
		chckbxAllDepartments.setSelected(true);
		chckbxAllDepartments.setBackground(new Color(243, 229, 216));
		chckbxAllDepartments.setBounds(12, 211, 120, 25);
		frame.getContentPane().add(chckbxAllDepartments);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(175, 211, 256, 25);
		frame.getContentPane().add(textField_2);
		
		button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new department_list(company,version, "po_history");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_2.setToolTipText("Department List");
		button_2.setEnabled(false);
		button_2.setBounds(435, 211, 28, 25);
		button_2.setIcon(new ImageIcon(serch));
		frame.getContentPane().add(button_2);
		
		chckbxAllSubdepartments = new JCheckBox("All SubDepartments");
		chckbxAllSubdepartments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxAllSubdepartments.isSelected())
				{
					button_3.setEnabled(false);
					textField_3.setText("");
				}
				else
				{
					button_3.setEnabled(true);
				}
			}
		});
		chckbxAllSubdepartments.setSelected(true);
		chckbxAllSubdepartments.setBackground(new Color(243, 229, 216));
		chckbxAllSubdepartments.setBounds(12, 249, 155, 25);
		frame.getContentPane().add(chckbxAllSubdepartments);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(175, 249, 256, 25);
		frame.getContentPane().add(textField_3);
		
		button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new sub_dept_list(company,version, "po_history",textField_2.getText());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_3.setToolTipText("Sub department List");
		button_3.setEnabled(false);

		button_3.setIcon(new ImageIcon(serch));
		button_3.setBounds(435, 249, 28, 25);
		frame.getContentPane().add(button_3);
		
		chckbxAllCategory = new JCheckBox("All Category");
		chckbxAllCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxAllCategory.isSelected())
				{
					button_4.setEnabled(false);
					textField_4.setText("");
				}
				else
				{
					button_4.setEnabled(true);
				}
			}
		});
		chckbxAllCategory.setSelected(true);
		chckbxAllCategory.setBackground(new Color(243, 229, 216));
		chckbxAllCategory.setBounds(12, 287, 120, 25);
		frame.getContentPane().add(chckbxAllCategory);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(175, 287, 256, 25);
		frame.getContentPane().add(textField_4);
		
		button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new cat_list(company,version, "po_history");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_4.setToolTipText("Category List");
		button_4.setEnabled(false);
		button_4.setBounds(435, 287, 28, 25);

		button_4.setIcon(new ImageIcon(serch));
		frame.getContentPane().add(button_4);
		
		chckbxAllSubcategory = new JCheckBox("All Subcategory");
		chckbxAllSubcategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxAllSubcategory.isSelected())
				{
					button_5.setEnabled(false);
					textField_5.setText("");
				}
				else
				{
					button_5.setEnabled(true);
				}
			}
		});
		chckbxAllSubcategory.setSelected(true);
		chckbxAllSubcategory.setBackground(new Color(243, 229, 216));
		chckbxAllSubcategory.setBounds(12, 325, 120, 25);
		frame.getContentPane().add(chckbxAllSubcategory);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(175, 325, 256, 25);
		frame.getContentPane().add(textField_5);
		
		button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new system_setups.sub_cat_list(company,version, "po_history",textField_4.getText());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_5.setToolTipText("Customer List");
		button_5.setEnabled(false);
		button_5.setBounds(435, 325, 28, 25);

		button_5.setIcon(new ImageIcon(serch));
		frame.getContentPane().add(button_5);
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 476, 451);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
	}
}
