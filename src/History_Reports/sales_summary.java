package History_Reports;

import inventory_master.item_list_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import CafePro.Mainpage;

import com.toedter.calendar.JDateChooser;

import java.awt.Button;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JButton;

import org.json.JSONException;

import sales_master.Cust_Chooser;
import system_setups.department_list;

import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class sales_summary {

	public static JFrame frame;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;
	private JComboBox<String> comboBox ;
	private JComboBox<String> comboBox_1 ;
	public static JTextField textField;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	public static JTextField textField_1;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_4;
	private JCheckBox chckbxPrintSummary;
	public static JTextField textField_5;
	public static JTextField textField_6;
	public static JTextField textField_7;
	public static JTextField textField_8;
	public static JTextField textField_9;
	private JTable table;
	private DefaultTableModel model;
	private static DefaultTableModel model1;
	private static JTable table1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					sales_summary window = new sales_summary("","mr_pro","admin", "S01", null,"");
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
	public sales_summary(String db_name,String company, String username,  String store,String version,String language) throws ParseException {
		initialize(db_name,company,username,store, version, language);
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
		
		JLabel lblDateRange = new JLabel("Summary Reports");
		lblDateRange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateRange.setBounds(12, 13, 138, 32);
		frame.getContentPane().add(lblDateRange);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(105, 69, 43, 22);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(302, 69, 27, 22);
		frame.getContentPane().add(lblTo);
		
		Date date = new Date();
		dateChooser = new JDateChooser(date);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(156, 69, 138, 22);
		frame.getContentPane().add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(337, 69, 125, 22);
		
		dateChooser_1.setDate(date);
		frame.getContentPane().add(dateChooser_1);
		
		Button button = new Button("Print Report");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Object[] item_list = new String[model.getRowCount()];
					for (int i = 0; i < model.getRowCount(); i++) {
						Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
						String dataCol1 = model.getValueAt(i, 1).toString();
						if (chked) {
							item_list[i]=dataCol1;
						}
					}
					String reg_list="";
					for(int i=0;i<item_list.length;i++)
					{
						if(item_list[i]!=null)
						{
							if(!reg_list.equals(""))
							{
								reg_list+="',";
							}
							if(!reg_list.equals(""))
							{
								reg_list+="'";
							}
							reg_list+=item_list[i];
						}
					}
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
					else if(comboBox.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(frame, "Select a Report Type");
					}
					else if(reg_list.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Select Any register");
					}
					else
					{
						String serch="";
						String type=comboBox.getSelectedItem().toString();
						if(type.equals("Customer Summary"))
						{
							serch=textField_5.getText();
						}
						if(type.equals("Sub Department Summary"))
						{
							serch=textField_1.getText();
						}
						if(type.equals("Department Summary"))
						{
							serch=textField_6.getText();
						}
						if(type.equals("Department Wise Item Summary"))
						{
							serch=textField_2.getText();
						}
						if(type.equals("Item Summary"))
						{
							serch=textField_3.getText();
						}
						if(type.equals("Vendor Summary"))
						{
							serch=textField_4.getText();
						}
						
						if(type.equals("Category Summary"))
						{
							serch=textField_7.getText();
						}
						if(type.equals("Sub Category Summary"))
						{
							serch=textField_9.getText();
						}
						if(type.equals("Category Wise Item Summary"))
						{
							serch=textField_8.getText();
						}
						
						String report="summary";
						if(chckbxPrintSummary.isSelected())
						{
							report="detail";
						}
						String analys=comboBox_1.getSelectedItem().toString();
//						if(analys.equals("None"))
//						{
//							analys="";
//						}
//						else
//						{
//							analys=comboBox_1.getSelectedItem().toString();
//						}
						
						
						
						Connection con=Dbsetup.dbconn.getConnection();
						try {
							frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							report_master.Sales_summary.report(date1,date2, con ,type,serch,report,analys,company,reg_list);
							frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		});
		button.setBounds(59, 437, 153, 24);
		frame.getContentPane().add(button);
		
		final JCheckBox chckbxAll = new JCheckBox("ALL");
	    chckbxAll.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		if(chckbxAll.isSelected())
				{
	    			for(int i=0;i<model.getRowCount();i++)
	    			{
	    				model.setValueAt(true , i, 0);
	    			}
	    			
				}
				else
				{
					for(int i=0;i<model.getRowCount();i++)
	    			{
	    				model.setValueAt(false , i, 0);
	    			}
				}
	    	}
	    });
	    
	    chckbxAll.setBounds(140, 100, 125, 25);
	    frame.getContentPane().add(chckbxAll);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(140, 129, 125, 105);
		scrollPane.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(scrollPane);
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			/**
			 * 
			 */
			public boolean isCellEditable(int row, int column){  
		        if(column==0)
		        {
		        	return true;  
		        }
				return false;  
		      }
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return String.class;
				
				default:
					return String.class;
				}
			}
			
		};
		table = new JTable(model);
		
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(20);
			model.addColumn(""); 
			model.addColumn(""); 
			
			table.setTableHeader(null);
			table.setShowGrid(false);
			
			int a=table.getColumnCount();
			for(int i=0;i<a;i++)
			{
				TableColumn column=table.getColumnModel().getColumn(i);
				if(i==0){
					column.setPreferredWidth(15);
				}
				if(i==3){
					column.setPreferredWidth(25);
				}
				if(i==4){
					column.setPreferredWidth(10);
				}
				if(i==5){
					column.setPreferredWidth(10);
				}
			}
		    scrollPane.setViewportView(table);
		
		JLabel lblDateRange_1 = new JLabel("Date Range : ");
		lblDateRange_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateRange_1.setBounds(8, 69, 89, 22);
		frame.getContentPane().add(lblDateRange_1);
		
		JLabel lblReportTypes = new JLabel("Report Types : ");
		lblReportTypes.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblReportTypes.setBounds(12, 262, 116, 16);
		frame.getContentPane().add(lblReportTypes);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(140, 259, 256, 22);
		comboBox.addItem("Customer Summary");
		comboBox.addItem("Item Summary");
		comboBox.addItem("Vendor Summary");
		comboBox.addItem("Department Summary");
		comboBox.addItem("Sub Department Summary");
		comboBox.addItem("Department Wise Item Summary");
		comboBox.addItem("Category Summary");
		comboBox.addItem("Sub Category Summary");
		comboBox.addItem("Category Wise Item Summary");
		
		comboBox.setSelectedIndex(-1);
        comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");

				textField_8.setText("");
				textField_9.setText("");
				textField_7.setText("");
				if(comboBox.getSelectedItem().equals("Customer Summary"))
				{
					panel.setVisible(true);
				}
				else
				{
					panel.setVisible(false);
				}
				if(comboBox.getSelectedItem().equals("Department Summary"))
				{
					panel_5.setVisible(true);
				}
				else
				{
					panel_5.setVisible(false);
				}
				if(comboBox.getSelectedItem().equals("Department Wise Item Summary"))
				{
					panel_2.setVisible(true);
				}
				else
				{
					panel_2.setVisible(false);
				}
				if(comboBox.getSelectedItem().equals("Item Summary"))
				{
					panel_3.setVisible(true);
				}
				else
				{
					panel_3.setVisible(false);
				}
				if(comboBox.getSelectedItem().equals("Sub Department Summary"))
				{
					panel_1.setVisible(true);
				}
				else
				{
					panel_1.setVisible(false);
				}
				if(comboBox.getSelectedItem().equals("Vendor Summary"))
				{
					panel_4.setVisible(true);
				}
				else
				{
					panel_4.setVisible(false);
				}
				
				if(comboBox.getSelectedItem().equals("Category Summary"))
				{
					panel_7.setVisible(true);
				}
				else
				{
					panel_7.setVisible(false);
				}
				
				if(comboBox.getSelectedItem().equals("Sub Category Summary"))
				{
					panel_6.setVisible(true);
				}
				else
				{
					panel_6.setVisible(false);
				}
				
				if(comboBox.getSelectedItem().equals("Category Wise Item Summary"))
				{
					panel_8.setVisible(true);
				}
				else
				{
					panel_8.setVisible(false);
				}
				
				
				
			}
		});
		frame.getContentPane().add(comboBox);
		//comboBox.setSelectedItem("Department Summary");
		
		panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(12, 307, 446, 29);
		frame.getContentPane().add(panel);
		panel.setVisible(false);
		panel.setLayout(null);
		
		JLabel lblSelectACustomer = new JLabel("Select a customer");
		lblSelectACustomer.setBounds(2, 3, 110, 22);
		panel.add(lblSelectACustomer);
		
		textField = new JTextField();
		textField.setBounds(111, 3, 303, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnNewButton = new JButton("");
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
		btnNewButton.setBounds(416, 3, 28, 21);
		panel.add(btnNewButton);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 307, 446, 29);
		panel_1.setVisible(false);
		panel_1.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(153, 3, 262, 22);
		panel_1.add(textField_1);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new system_setups.sub_dept_list(company, version,"sale_summary","");
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setToolTipText("Sub Department List");
		button_1.setBounds(416, 3, 28, 21);
		button_1.setIcon(new ImageIcon(serch));
		panel_1.add(button_1);
		
		JLabel lblSelectADepartment = new JLabel("Select a Sub Department");
		lblSelectADepartment.setBounds(2, 3, 143, 16);
		panel_1.add(lblSelectADepartment);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setVisible(false);
		panel_2.setBackground(new Color(243, 229, 216));
		panel_2.setBounds(12, 307, 446, 29);
		frame.getContentPane().add(panel_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(132, 3, 283, 22);
		panel_2.add(textField_2);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new department_list(company,version,"deptwise");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_2.setToolTipText("Department List");
		button_2.setBounds(416, 3, 28, 21);
		button_2.setIcon(new ImageIcon(serch));
		panel_2.add(button_2);
		
		JLabel label = new JLabel("Select a Department");
		label.setBounds(2, 3, 128, 16);
		panel_2.add(label);
		
		panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setVisible(false);
		panel_5.setBackground(new Color(243, 229, 216));
		panel_5.setBounds(12, 307, 446, 29);
		frame.getContentPane().add(panel_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(132, 3, 283, 22);
		panel_5.add(textField_6);
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new department_list(company,version,"dept");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_5.setToolTipText("Department List");
		button_5.setBounds(416, 3, 28, 21);
		button_5.setIcon(new ImageIcon(serch));
		panel_5.add(button_5);
		
		JLabel label6 = new JLabel("Select a Department");
		label6.setBounds(2, 3, 128, 16);
		panel_5.add(label6);
		
		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setVisible(false);
		panel_3.setBackground(new Color(243, 229, 216));
		panel_3.setBounds(12, 307, 446, 29);
		frame.getContentPane().add(panel_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(92, 3, 324, 22);
		panel_3.add(textField_3);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=Dbsetup.dbconn.getConnection();
					new item_list_master(db_name,company, "sales_summary", store, version, 0,con,"", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_3.setToolTipText("Item List");
		button_3.setBounds(416, 3, 28, 21);
		button_3.setIcon(new ImageIcon(serch));
		panel_3.add(button_3);
		
		JLabel lblSelectAItem = new JLabel("Select a Item");
		lblSelectAItem.setBounds(2, 3, 90, 16);
		panel_3.add(lblSelectAItem);
		
		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(243, 229, 216));
		panel_4.setBounds(12, 307, 446, 29);
		panel_4.setVisible(false);
		frame.getContentPane().add(panel_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(101, 3, 315, 22);
		panel_4.add(textField_4);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new purchase_master.vendor_list(company, "sales_summary", version, "",username);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_4.setToolTipText("Vendor List");
		button_4.setBounds(416, 3, 28, 21);
		button_4.setIcon(new ImageIcon(serch));
		panel_4.add(button_4);
		
		JLabel lblSelectAVendor = new JLabel("Select a Vendor");
		lblSelectAVendor.setBounds(2, 3, 101, 16);
		panel_4.add(lblSelectAVendor);
		
		chckbxPrintSummary = new JCheckBox("Print Detail Report");
		chckbxPrintSummary.setBounds(12, 345, 138, 25);
		chckbxPrintSummary.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(chckbxPrintSummary);
		
		JLabel lblAnalysis = new JLabel("Analysis Order");
		lblAnalysis.setBounds(12, 379, 89, 22);
		frame.getContentPane().add(lblAnalysis);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(116, 379, 235, 22);
		comboBox_1.addItem("None");
		comboBox_1.addItem("Quantity");
		comboBox_1.addItem("Sales");
		comboBox_1.addItem("Gross Margin");
		frame.getContentPane().add(comboBox_1);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setVisible(false);
		textField_5.setBounds(342, 346, 116, 22);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setBounds(370, 2, 100, 32);
		scrollPane1.setBackground(new Color(243, 229, 216));
		scrollPane1.setVisible(false);
		frame.getContentPane().add(scrollPane1);

		model1 = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
				return false;
		    }
		};
		    table1 = new JTable(model1);
		    table1.setFont(new Font("Serif", Font.BOLD, 15));
		    table1.setRowHeight(20);
		    
		    panel_6 = new JPanel();
			panel_6.setBounds(12, 307, 446, 29);
			panel_6.setVisible(false);
			panel_6.setBackground(new Color(243, 229, 216));
			frame.getContentPane().add(panel_6);
			panel_6.setLayout(null);
			
			textField_9 = new JTextField();
			textField_9.setColumns(10);
			textField_9.setBounds(153, 3, 262, 22);
			panel_6.add(textField_9);
			
			JButton button_6 = new JButton("");
			button_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new system_setups.sub_cat_list(company, version,"sale_summary","");
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			button_6.setToolTipText("Sub Category List");
			button_6.setBounds(416, 3, 28, 21);
			button_6.setIcon(new ImageIcon(serch));
			panel_6.add(button_6); 
			
			JLabel lblSelectAsubcat = new JLabel("Select a Sub Category");
			lblSelectAsubcat.setBounds(2, 3, 101, 16);
			panel_6.add(lblSelectAsubcat);
			
			panel_7 = new JPanel();
			panel_7.setBounds(12, 307, 446, 29);
			panel_7.setVisible(false);
			panel_7.setBackground(new Color(243, 229, 216));
			frame.getContentPane().add(panel_7);
			panel_7.setLayout(null);
			
			textField_7 = new JTextField();
			textField_7.setColumns(10);
			textField_7.setBounds(153, 3, 262, 22);
			panel_7.add(textField_7);
			
			JButton button_7 = new JButton("");
			button_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new system_setups.cat_list(company, version,"sale_summary");
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			button_7.setToolTipText("Category List");
			button_7.setBounds(416, 3, 28, 21);
			button_7.setIcon(new ImageIcon(serch));
			panel_7.add(button_7); 
			
			JLabel lblSelectAcat = new JLabel("Select a Category");
			lblSelectAcat.setBounds(2, 3, 101, 16);
			panel_7.add(lblSelectAcat);
			
			panel_8 = new JPanel();
			panel_8.setBounds(12, 307, 446, 29);
			panel_8.setVisible(false);
			panel_8.setBackground(new Color(243, 229, 216));
			frame.getContentPane().add(panel_8);
			panel_8.setLayout(null);
			
			textField_8 = new JTextField();
			textField_8.setColumns(10);
			textField_8.setBounds(153, 3, 262, 22);
			panel_8.add(textField_8);
			
			JButton button_8 = new JButton("");
			button_8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new system_setups.cat_list(company, version,"sale_item_summary");
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			button_8.setToolTipText("Category List");
			button_8.setBounds(416, 3, 28, 21);
			button_8.setIcon(new ImageIcon(serch));
			panel_8.add(button_8); 
			
			JLabel lblSelectAcate = new JLabel("Select a Category");
			lblSelectAcate.setBounds(2, 3, 101, 16);
			panel_8.add(lblSelectAcate);
		   
		scrollPane1.setViewportView(table1);
		
		Button btnExportToCsv = new Button("Export to Excel");
		btnExportToCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
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
					else if(comboBox.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(frame, "Select a Report Type");
					}
					else
					{
						String query="";
						String serch="";
						String serch_data1="";
						String serch_data2="";
						String analys_data="";
						String analys=comboBox_1.getSelectedItem().toString();
						
						String report="0";
						if(chckbxPrintSummary.isSelected())
						{
							report="1";
						}
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();
						String type=comboBox.getSelectedItem().toString();
						if(type.equals("Customer Summary"))
						{
							serch=textField_5.getText();
							if(!serch.equals(""))
							{
								serch_data1="and "+company+".mr_pos_settlement_history.cust_code='"+serch+"'";
								serch_data2="and "+company+".mr_sd_list_history.cust_id='"+serch+"'";
							}
							if(analys.equals("None"))
							{
								analys_data=" ORDER BY cust_name ASC";
							}
							else if(analys.equals("Gross Margin"))
							{
								analys_data=" ORDER BY margin ASC";
							}
							else
							{
								analys_data=" ORDER BY "+analys+" ASC";
							}
							if(report.equals("0"))
							{
								query="(SELECT "+company+".mr_pos_settlement_history.cust_code,"+company+".mr_pos_settlement_history.cust_name,sum("+company+".mr_pos_settlement_history_items.qty) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num inner join "+company+".mr_tmp_item_master on  "+company+".mr_tmp_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code and mr_tmp_item_master.barcode=mr_pos_settlement_history_items.barcode where  "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and '"+date2+"' and "+company+".mr_pos_settlement_history.type!='VOID' "+serch_data1+" GROUP BY  "+company+".mr_pos_settlement_history.cust_name ) union (select "+company+".mr_sd_list_history.cust_id,"+company+".mr_sd_list_history.cust_name,sum("+company+".mr_sd_lines_history.qty) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as margin,sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as cost FROM  "+company+".mr_sd_lines_history INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and '"+date2+"' "+serch_data2+" group by "+company+".mr_sd_list_history.invoice_num ) "+analys_data+" ";
								
								ResultSet resultSet1 = statement.executeQuery(query);
								if(resultSet1.next())
								{
									int rowCount = model1.getRowCount();
									for (int i = rowCount - 1; i >= 0; i--) {
										model1.removeRow(i);
									}
									model1.addColumn("cust code");
									model1.addColumn("cust name");
									model1.addColumn("qty");
									model1.addColumn("sale");
									model1.addColumn("cost");
									model1.addColumn("vat");
									model1.addColumn("margin");
									model1.addColumn("margin %");
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										String code=resultSet.getString("cust_code");
						                String name=resultSet.getString("cust_name");
						                String qty=resultSet.getString("qty");
						                String sale=resultSet.getString("total");
						                String cost=resultSet.getString("cost");
						                String vat=resultSet.getString("vat");
						                double margin=Double.parseDouble(sale)-Double.parseDouble(cost);
						                double margin_perc=((Double.parseDouble(sale)-Double.parseDouble(cost))/Double.parseDouble(sale))*100;
						                model1.addRow(new Object[]{code, name,qty,sale,cost,vat,margin,margin_perc});
						                
									}
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Item Not Found");
								}
							}
							else
							{
								query="(SELECT "+company+".mr_pos_settlement_history.created_date as date,"+company+".mr_pos_settlement_history.cust_code,"+company+".mr_pos_settlement_history.cust_name,sum("+company+".mr_pos_settlement_history_items.qty) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num inner join "+company+".mr_tmp_item_master on  "+company+".mr_tmp_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code and mr_tmp_item_master.barcode=mr_pos_settlement_history_items.barcode where  "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and  '"+date2+"' and "+company+".mr_pos_settlement_history.type!='VOID' "+serch_data1+" GROUP BY "+company+".mr_pos_settlement_history.created_date,"+company+".mr_pos_settlement_history.cust_name,date ) union (select "+company+".mr_sd_list_history.date  as date,"+company+".mr_sd_list_history.cust_id,"+company+".mr_sd_list_history.cust_name,sum("+company+".mr_sd_lines_history.qty) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as margin,sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as cost FROM  "+company+".mr_sd_lines_history INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and  '"+date2+"' "+serch_data2+" GROUP BY "+company+".mr_sd_list_history.date,"+company+".mr_sd_list_history.invoice_num ) "+analys_data+"";
								
								ResultSet resultSet1 = statement.executeQuery(query);
								if(resultSet1.next())
								{
									int rowCount = model1.getRowCount();
									for (int i = rowCount - 1; i >= 0; i--) {
										model1.removeRow(i);
									}
									model1.addColumn("date");
									model1.addColumn("cust_code");
									model1.addColumn("cust_name");
									model1.addColumn("qty");
									model1.addColumn("sale");
									model1.addColumn("cost");
									model1.addColumn("vat");
									model1.addColumn("margin");
									model1.addColumn("margin %");
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										
										String date=resultSet.getString("date");
										String code=resultSet.getString("cust_code");
						                String name=resultSet.getString("cust_name");
						                String qty=resultSet.getString("qty");
						                String sale=resultSet.getString("total");
						                String cost=resultSet.getString("cost");
						                String vat=resultSet.getString("vat");
						                double margin=Double.parseDouble(sale)-Double.parseDouble(cost);
						                double margin_perc=((Double.parseDouble(sale)-Double.parseDouble(cost))/Double.parseDouble(sale))*100;
						                model1.addRow(new Object[]{date,code, name,qty,sale,cost,vat,margin,margin_perc});
									}
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Item Not Found");
								}
							}
						}
						if(type.equals("Sub Department Summary"))
						{
							serch=textField_1.getText();
							if(!serch.equals(""))
							{
								serch_data1="and "+company+".mr_item_master.sub_department='"+serch+"'";
								serch_data2="and "+company+".mr_item_master.sub_department='"+serch+"'";
							}
							if(analys.equals("None"))
							{
								analys_data=" ORDER BY department ASC";
							}
							else if(analys.equals("Gross Margin"))
							{
								analys_data=" ORDER BY margin ASC";
							}
							else
							{
								analys_data=" ORDER BY "+analys+" ASC";
							}
							if(report.equals("0"))
							{
								query="(SELECT "+company+".mr_item_master.department,"+company+".mr_item_master.sub_department,sum("+company+".mr_pos_settlement_history_items.qty) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num inner join "+company+".mr_tmp_item_master on  "+company+".mr_tmp_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code and mr_tmp_item_master.barcode=mr_pos_settlement_history_items.barcode inner join "+company+".mr_item_master on  "+company+".mr_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code where  "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and  '"+date2+"' and mr_pos_settlement_history.type!='VOID' "+serch_data1+" GROUP BY  "+company+".mr_item_master.department ) union (select "+company+".mr_item_master.department, "+company+".mr_item_master.sub_department,sum("+company+".mr_sd_lines_history.qty) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as margin,sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as cost FROM  "+company+".mr_sd_lines_history inner join "+company+".mr_item_master on mr_item_master.item_code=mr_sd_lines_history.item_code INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num inner join "+company+".mr_tmp_item_master on mr_tmp_item_master.item_code=mr_sd_lines_history.item_code and mr_tmp_item_master.barcode=mr_sd_lines_history.barcode where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and  '"+date2+"' "+serch_data2+" GROUP BY  "+company+".mr_tmp_item_master.department ) "+analys_data+"";
								
								ResultSet resultSet1 = statement.executeQuery(query);
								if(resultSet1.next())
								{
									int rowCount = model1.getRowCount();
									for (int i = rowCount - 1; i >= 0; i--) {
										model1.removeRow(i);
									}
									model1.addColumn("Department");
									model1.addColumn("Sub Dept");
									model1.addColumn("qty");
									model1.addColumn("sale");
									model1.addColumn("cost");
									model1.addColumn("vat");
									model1.addColumn("margin");
									model1.addColumn("margin %");
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										String code=resultSet.getString("department");
						                String name=resultSet.getString("sub_department");
						                String qty=resultSet.getString("qty");
						                String sale=resultSet.getString("total");
						                String cost=resultSet.getString("cost");
						                String vat=resultSet.getString("vat");
						                double margin=Double.parseDouble(sale)-Double.parseDouble(cost);
						                double margin_perc=((Double.parseDouble(sale)-Double.parseDouble(cost))/Double.parseDouble(sale))*100;
						                model1.addRow(new Object[]{code, name,qty,sale,cost,vat,margin,margin_perc});
						                
									}
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Item Not Found");
								}
							}
							else
							{
								query="(SELECT "+company+".mr_pos_settlement_history_items.created_date as date,"+company+".mr_item_master.department,"+company+".mr_item_master.sub_department,sum("+company+".mr_pos_settlement_history_items.qty) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num inner join "+company+".mr_tmp_item_master on  "+company+".mr_tmp_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code and mr_tmp_item_master.barcode=mr_pos_settlement_history_items.barcode inner join "+company+".mr_item_master on  "+company+".mr_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code where  "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and  '"+date2+"' and mr_pos_settlement_history.type!='VOID' "+serch_data1+" GROUP BY  "+company+".mr_item_master.department,date ) union (select "+company+".mr_sd_list_history.date as date,"+company+".mr_item_master.department,"+company+".mr_item_master.sub_department,sum("+company+".mr_sd_lines_history.qty) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as margin,sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as cost FROM  "+company+".mr_sd_lines_history inner join "+company+".mr_item_master on mr_item_master.item_code=mr_sd_lines_history.item_code INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num inner join "+company+".mr_tmp_item_master on mr_tmp_item_master.item_code=mr_sd_lines_history.item_code and mr_tmp_item_master.barcode=mr_sd_lines_history.barcode where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and  '"+date2+"' "+serch_data2+" GROUP BY  "+company+".mr_item_master.department,date ) "+analys_data+"";
								
								ResultSet resultSet1 = statement.executeQuery(query);
								if(resultSet1.next())
								{
									int rowCount = model1.getRowCount();
									for (int i = rowCount - 1; i >= 0; i--) {
										model1.removeRow(i);
									}
									model1.addColumn("date");
									model1.addColumn("department");
									model1.addColumn("sub_department");
									model1.addColumn("qty");
									model1.addColumn("sale");
									model1.addColumn("cost");
									model1.addColumn("vat");
									model1.addColumn("margin");
									model1.addColumn("margin %");
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										
										String date=resultSet.getString("date");
										String code=resultSet.getString("department");
						                String name=resultSet.getString("sub_department");
						                String qty=resultSet.getString("qty");
						                String sale=resultSet.getString("total");
						                String cost=resultSet.getString("cost");
						                String vat=resultSet.getString("vat");
						                double margin=Double.parseDouble(sale)-Double.parseDouble(cost);
						                double margin_perc=((Double.parseDouble(sale)-Double.parseDouble(cost))/Double.parseDouble(sale))*100;
						                model1.addRow(new Object[]{date,code, name,qty,sale,cost,vat,margin,margin_perc});
									}
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Item Not Found");
								}
							}
						}
						if(type.equals("Department Summary"))
						{
							serch=textField_6.getText();
							if(!serch.equals(""))
							{
								serch_data1="and "+company+".mr_tmp_item_master.department='"+serch+"'";
								serch_data2="and "+company+".mr_tmp_item_master.department='"+serch+"'";
							}
							if(analys.equals("None"))
							{
								analys_data=" ORDER BY department ASC";
							}
							else if(analys.equals("Gross Margin"))
							{
								analys_data=" ORDER BY margin ASC";
							}
							else
							{
								analys_data=" ORDER BY "+analys+" ASC";
							}
							if(report.equals("0"))
							{
								query="(SELECT "+company+".mr_tmp_item_master.department,sum("+company+".mr_pos_settlement_history_items.qty) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num inner join "+company+".mr_tmp_item_master on "+company+".mr_tmp_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code and  mr_tmp_item_master.barcode=mr_pos_settlement_history_items.barcode where  "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and  '"+date2+"' and mr_pos_settlement_history.type!='VOID' "+serch_data2+" GROUP BY  "+company+".mr_tmp_item_master.department ) union (select "+company+".mr_tmp_item_master.department,sum("+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as margin,sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as cost FROM  "+company+".mr_sd_lines_history INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num inner join "+company+".mr_tmp_item_master on mr_tmp_item_master.item_code=mr_sd_lines_history.item_code and mr_tmp_item_master.barcode=mr_sd_lines_history.barcode where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and  '"+date2+"' "+serch_data2+" GROUP BY  "+company+".mr_tmp_item_master.department ) "+analys_data+"";
								
								ResultSet resultSet1 = statement.executeQuery(query);
								if(resultSet1.next())
								{
									int rowCount = model1.getRowCount();
									for (int i = rowCount - 1; i >= 0; i--) {
										model1.removeRow(i);
									}
									model1.addColumn("Department");
									model1.addColumn("qty");
									model1.addColumn("sale");
									model1.addColumn("cost");
									model1.addColumn("vat");
									model1.addColumn("margin");
									model1.addColumn("margin %");
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										String code=resultSet.getString("department");
						                String qty=resultSet.getString("qty");
						                String sale=resultSet.getString("total");
						                String cost=resultSet.getString("cost");
						                String vat=resultSet.getString("vat");
						                double margin=Double.parseDouble(sale)-Double.parseDouble(cost);
						                double margin_perc=((Double.parseDouble(sale)-Double.parseDouble(cost))/Double.parseDouble(sale))*100;
						                model1.addRow(new Object[]{code, qty,sale,cost,vat,margin,margin_perc});
						                
									}
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Item Not Found");
								}
							}
							else
							{
								query="(SELECT "+company+".mr_tmp_item_master.department,"+company+".mr_pos_settlement_history_items.created_date as date,sum("+company+".mr_pos_settlement_history_items.qty) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num inner join "+company+".mr_tmp_item_master on  "+company+".mr_tmp_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code and mr_tmp_item_master.barcode=mr_pos_settlement_history_items.barcode where  "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and  '"+date2+"'  and mr_pos_settlement_history.type!='VOID' "+serch_data1+" GROUP BY  department,"+company+".mr_pos_settlement_history_items.created_date ) union (select "+company+".mr_tmp_item_master.department, "+company+".mr_sd_list_history.date as date, sum("+company+".mr_sd_lines_history.qty) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_tmp_item_master.cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as margin,sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as cost FROM  "+company+".mr_sd_lines_history inner join "+company+".mr_tmp_item_master on mr_tmp_item_master.item_code=mr_sd_lines_history.item_code and mr_tmp_item_master.barcode=mr_sd_lines_history.barcode INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and  '"+date2+"'  "+serch_data2+" GROUP BY department,date) "+analys_data+"";
								
								ResultSet resultSet1 = statement.executeQuery(query);
								if(resultSet1.next())
								{
									int rowCount = model1.getRowCount();
									for (int i = rowCount - 1; i >= 0; i--) {
										model1.removeRow(i);
									}
									model1.addColumn("date");
									model1.addColumn("department");
									model1.addColumn("qty");
									model1.addColumn("sale");
									model1.addColumn("cost");
									model1.addColumn("vat");
									model1.addColumn("margin");
									model1.addColumn("margin %");
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										
										String date=resultSet.getString("date");
										String code=resultSet.getString("department");
						                String qty=resultSet.getString("qty");
						                String sale=resultSet.getString("total");
						                String cost=resultSet.getString("cost");
						                String vat=resultSet.getString("vat");
						                double margin=Double.parseDouble(sale)-Double.parseDouble(cost);
						                double margin_perc=((Double.parseDouble(sale)-Double.parseDouble(cost))/Double.parseDouble(sale))*100;
						                model1.addRow(new Object[]{date,code, qty,sale,cost,vat,margin,margin_perc});
									}
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Item Not Found");
								}
							}
						}
						if(type.equals("Department Wise Item Summary"))
						{
							serch=textField_2.getText();
							if(!serch.equals(""))
							{
								serch_data1="and "+company+".mr_tmp_item_master.department='"+serch+"'";
								serch_data2="and "+company+".mr_tmp_item_master.department='"+serch+"'";
							}
							if(analys.equals("None"))
							{
								analys_data=" ORDER BY department ASC";
							}
							else if(analys.equals("Gross Margin"))
							{
								analys_data=" ORDER BY margin ASC";
							}
							else
							{
								analys_data=" ORDER BY "+analys+" ASC";
							}
							if(report.equals("0"))
							{
								query="(SELECT "+company+".mr_tmp_item_master.department,"+company+".mr_pos_settlement_history_items.item_desc as name,sum("+company+".mr_pos_settlement_history_items.qty) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num inner join "+company+".mr_tmp_item_master on  "+company+".mr_tmp_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code and mr_tmp_item_master.barcode=mr_pos_settlement_history_items.barcode where  "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and  '"+date2+"' and mr_pos_settlement_history.type!='VOID' "+serch_data1+" GROUP BY name ) union (select "+company+".mr_tmp_item_master.department,"+company+".mr_sd_lines_history.item_name as name,sum("+company+".mr_sd_lines_history.qty) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_tmp_item_master.cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as margin,sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as cost FROM  "+company+".mr_sd_lines_history INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num inner join "+company+".mr_tmp_item_master on mr_tmp_item_master.item_code=mr_sd_lines_history.item_code and mr_tmp_item_master.barcode=mr_sd_lines_history.barcode where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and  '"+date2+"' "+serch_data1+" GROUP BY name ) "+analys_data+"";
								
								ResultSet resultSet1 = statement.executeQuery(query);
								if(resultSet1.next())
								{
									int rowCount = model1.getRowCount();
									for (int i = rowCount - 1; i >= 0; i--) {
										model1.removeRow(i);
									}
									model1.addColumn("Department");
									model1.addColumn("Item Name");
									model1.addColumn("qty");
									model1.addColumn("sale");
									model1.addColumn("cost");
									model1.addColumn("vat");
									model1.addColumn("margin");
									model1.addColumn("margin %");
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										String code=resultSet.getString("department");
						                String name=resultSet.getString("name");
						                String qty=resultSet.getString("qty");
						                String sale=resultSet.getString("total");
						                String cost=resultSet.getString("cost");
						                String vat=resultSet.getString("vat");
						                double margin=Double.parseDouble(sale)-Double.parseDouble(cost);
						                double margin_perc=((Double.parseDouble(sale)-Double.parseDouble(cost))/Double.parseDouble(sale))*100;
						                model1.addRow(new Object[]{code, name,qty,sale,cost,vat,margin,margin_perc});
						                
									}
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Item Not Found");
								}
							}
							else
							{
								query="(SELECT "+company+".mr_tmp_item_master.department,mr_pos_settlement_history_items.barcode as barcode,mr_pos_settlement_history_items.unit as unit,"+company+".mr_pos_settlement_history_items.item_desc as item_name,"+company+".mr_pos_settlement_history_items.created_date as date,sum("+company+".mr_pos_settlement_history_items.qty) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num inner join "+company+".mr_tmp_item_master on  "+company+".mr_tmp_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code and mr_tmp_item_master.barcode=mr_pos_settlement_history_items.barcode where  "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and  '"+date2+"' and mr_pos_settlement_history.type!='VOID' "+serch_data1+" GROUP BY  date,mr_pos_settlement_history_items.barcode ) union (select "+company+".mr_tmp_item_master.department, "+company+".mr_sd_lines_history.barcode as barcode, "+company+".mr_sd_lines_history.unit as unit, "+company+".mr_sd_lines_history.item_name as item_name,"+company+".mr_sd_list_history.date as date,sum("+company+".mr_sd_lines_history.qty) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_tmp_item_master.cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as margin,sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as cost FROM  "+company+".mr_sd_lines_history inner join "+company+".mr_tmp_item_master on mr_tmp_item_master.item_code=mr_sd_lines_history.item_code and mr_tmp_item_master.barcode=mr_sd_lines_history.barcode INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and  '"+date2+"' "+serch_data2+" GROUP BY date) "+analys_data+"";
								
								ResultSet resultSet1 = statement.executeQuery(query);
								if(resultSet1.next())
								{
									int rowCount = model1.getRowCount();
									for (int i = rowCount - 1; i >= 0; i--) {
										model1.removeRow(i);
									}
									model1.addColumn("date");
									model1.addColumn("department");
									model1.addColumn("Barcode");
									model1.addColumn("item name");
									model1.addColumn("unit");
									model1.addColumn("qty");
									model1.addColumn("sale");
									model1.addColumn("cost");
									model1.addColumn("vat");
									model1.addColumn("margin");
									model1.addColumn("margin %");
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										
										String date=resultSet.getString("date");
										String code=resultSet.getString("department");
						                String name=resultSet.getString("barcode");
						                String item=resultSet.getString("item_name");
						                String unit=resultSet.getString("unit");
						                String qty=resultSet.getString("qty");
						                String sale=resultSet.getString("total");
						                String cost=resultSet.getString("cost");
						                String vat=resultSet.getString("vat");
						                double margin=Double.parseDouble(sale)-Double.parseDouble(cost);
						                double margin_perc=((Double.parseDouble(sale)-Double.parseDouble(cost))/Double.parseDouble(sale))*100;
						                model1.addRow(new Object[]{date,code, name,item,unit,qty,sale,cost,vat,margin,margin_perc});
									}
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Item Not Found");
								}
							}
						}
						if(type.equals("Item Summary"))
						{
							serch=textField_3.getText();
							if(!serch.equals(""))
							{
								serch_data1="and "+company+".mr_pos_settlement_history_items.item_desc='"+serch+"'";
								serch_data2="and "+company+".mr_sd_lines_history.item_name='"+serch+"'";
							}
							if(analys.equals("None"))
							{
								analys_data=" ORDER BY item_name ASC";
							}
							else if(analys.equals("Gross Margin"))
							{
								analys_data=" ORDER BY margin ASC";
							}
							else
							{
								analys_data=" ORDER BY "+analys+" ASC";
							}
							if(report.equals("0"))
							{
								query="SELECT "+company+".mr_pos_settlement_history_items.item_code,"+company+".mr_pos_settlement_history_items.item_desc as item_name,sum("+company+".mr_pos_settlement_history_items.qty) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as cost FROM "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num inner join "+company+".mr_tmp_item_master on  "+company+".mr_tmp_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code and mr_tmp_item_master.barcode=mr_pos_settlement_history_items.barcode where "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and '"+date2+"' and "+company+".mr_pos_settlement_history.type!='VOID' "+serch_data1+" GROUP BY "+company+".mr_pos_settlement_history_items.item_desc  union (select "+company+".mr_sd_lines_history.item_code,"+company+".mr_sd_lines_history.item_name,sum("+company+".mr_sd_lines_history.qty) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_tmp_item_master.cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as margin,sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as cost FROM  "+company+".mr_sd_lines_history inner join "+company+".mr_tmp_item_master on mr_tmp_item_master.item_code=mr_sd_lines_history.item_code and mr_tmp_item_master.barcode=mr_sd_lines_history.barcode INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and  '"+date2+"' "+serch_data2+" GROUP BY "+company+".mr_sd_lines_history.item_name ) "+analys_data+" ";
								
								ResultSet resultSet1 = statement.executeQuery(query);
								if(resultSet1.next())
								{
									int rowCount = model1.getRowCount();
									for (int i = rowCount - 1; i >= 0; i--) {
										model1.removeRow(i);
									}
									model1.addColumn("item_code");
									model1.addColumn("item_name");
									model1.addColumn("qty");
									model1.addColumn("sale");
									model1.addColumn("cost");
									model1.addColumn("vat");
									model1.addColumn("margin");
									model1.addColumn("margin %");
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										String code=resultSet.getString("item_code");
						                String name=resultSet.getString("item_name");
						                String qty=resultSet.getString("qty");
						                String sale=resultSet.getString("total");
						                String cost=resultSet.getString("cost");
						                String vat=resultSet.getString("vat");
						                double margin=Double.parseDouble(sale)-Double.parseDouble(cost);
						                double margin_perc=((Double.parseDouble(sale)-Double.parseDouble(cost))/Double.parseDouble(sale))*100;
						                model1.addRow(new Object[]{code, name,qty,sale,cost,vat,margin,margin_perc});
						                
									}
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Item Not Found");
								}
							}
							else
							{
								query="(SELECT "+company+".mr_pos_settlement_history_items.created_date as date,"+company+".mr_pos_settlement_history_items.item_code,"+company+".mr_pos_settlement_history_items.unit,"+company+".mr_pos_settlement_history_items.item_desc as item_name,sum("+company+".mr_pos_settlement_history_items.qty) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_tmp_item_master.cf) as cost FROM "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num inner join "+company+".mr_tmp_item_master on  "+company+".mr_tmp_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code and mr_tmp_item_master.barcode=mr_pos_settlement_history_items.barcode where "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and  '"+date2+"' and mr_pos_settlement_history.type!='VOID' "+serch_data1+" GROUP BY item_name,date ) union (select "+company+".mr_sd_list_history.date as date,"+company+".mr_sd_lines_history.item_code,"+company+".mr_sd_lines_history.unit,"+company+".mr_sd_lines_history.item_name,sum("+company+".mr_sd_lines_history.qty) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_tmp_item_master.cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as margin,sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as cost FROM  "+company+".mr_sd_lines_history inner join "+company+".mr_tmp_item_master on mr_tmp_item_master.item_code=mr_sd_lines_history.item_code and mr_tmp_item_master.barcode=mr_sd_lines_history.barcode INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num  where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and  '"+date2+"' "+serch_data2+" GROUP BY item_name ) "+analys_data+" ";
								
								ResultSet resultSet1 = statement.executeQuery(query);
								if(resultSet1.next())
								{
									int rowCount = model1.getRowCount();
									for (int i = rowCount - 1; i >= 0; i--) {
										model1.removeRow(i);
									}
									model1.addColumn("date");
									model1.addColumn("item_code");
									model1.addColumn("item_name");
									model1.addColumn("unit");
									model1.addColumn("qty");
									model1.addColumn("sale");
									model1.addColumn("cost");
									model1.addColumn("vat");
									model1.addColumn("margin");
									model1.addColumn("margin %");
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										String date=resultSet.getString("date");
										String code=resultSet.getString("item_code");
						                String name=resultSet.getString("item_name");
						                String unit=resultSet.getString("unit");
						                String qty=resultSet.getString("qty");
						                String sale=resultSet.getString("total");
						                String cost=resultSet.getString("cost");
						                String vat=resultSet.getString("vat");
						                double margin=Double.parseDouble(sale)-Double.parseDouble(cost);
						                double margin_perc=((Double.parseDouble(sale)-Double.parseDouble(cost))/Double.parseDouble(sale))*100;
						                model1.addRow(new Object[]{date,code, name,unit,qty,sale,cost,vat,margin,margin_perc});
						                
									}
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Item Not Found");
								}
							}
							
						}
						if(type.equals("Vendor Summary"))
						{
							serch=textField_4.getText();
							if(!serch.equals(""))
							{
								serch_data1="and "+company+".mr_vendor.vendor_name='"+serch+"'";
								serch_data2="and "+company+".mr_vendor.vendor_name='"+serch+"'";
							}
							if(analys.equals("None"))
							{
								analys_data=" ORDER BY vendor_code ASC";
							}
							else if(analys.equals("Gross Margin"))
							{
								analys_data=" ORDER BY margin ASC";
							}
							else
							{
								analys_data=" ORDER BY "+analys+" ASC";
							}
							if(report.equals("0"))
							{
								query="(SELECT "+company+".mr_vendor.vendor_code,"+company+".mr_vendor.vendor_name,sum("+company+".mr_pos_settlement_history_items.qty) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty) as cost,sum("+company+".mr_pos_settlement_history_items.total)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty) as margin FROM "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num inner join "+company+".mr_tmp_item_master on  "+company+".mr_tmp_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code INNER JOIN "+company+".mr_vendor_items on "+company+".mr_pos_settlement_history_items.item_code= "+company+".mr_vendor_items.ref_item_id INNER JOIN "+company+".mr_vendor on "+company+".mr_vendor.vendor_code= "+company+".mr_vendor_items.ref_vendor_id where "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and  '"+date2+"' and  "+company+".mr_pos_settlement_history.type!='VOID' "+serch_data1+" GROUP BY "+company+".mr_vendor.vendor_code) union (select "+company+".mr_vendor.vendor_code,"+company+".mr_vendor.vendor_name,sum("+company+".mr_sd_lines_history.qty) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_tmp_item_master.cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as margin,sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as cost FROM  "+company+".mr_sd_lines_history inner join "+company+".mr_tmp_item_master on mr_tmp_item_master.item_code=mr_sd_lines_history.item_code  and mr_tmp_item_master.barcode=mr_sd_lines_history.barcode INNER JOIN "+company+".mr_vendor_items on "+company+".mr_sd_lines_history.item_code= "+company+".mr_vendor_items.ref_item_id INNER JOIN "+company+".mr_vendor on "+company+".mr_vendor.vendor_code= "+company+".mr_vendor_items.ref_vendor_id INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and  '"+date2+"' "+serch_data2+" GROUP BY   "+company+".mr_vendor.vendor_code ) "+analys_data+"";
								
								ResultSet resultSet1 = statement.executeQuery(query);
								if(resultSet1.next())
								{
									int rowCount = model1.getRowCount();
									for (int i = rowCount - 1; i >= 0; i--) {
										model1.removeRow(i);
									}
									model1.addColumn("Vendor code");
									model1.addColumn("Vendor name");
									model1.addColumn("qty");
									model1.addColumn("sale");
									model1.addColumn("cost");
									model1.addColumn("vat");
									model1.addColumn("margin");
									model1.addColumn("margin %");
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										String code=resultSet.getString("vendor_code");
						                String name=resultSet.getString("vendor_name");
						                String qty=resultSet.getString("qty");
						                String sale=resultSet.getString("total");
						                String cost=resultSet.getString("cost");
						                String vat=resultSet.getString("vat");
						                double margin=Double.parseDouble(sale)-Double.parseDouble(cost);
						                double margin_perc=((Double.parseDouble(sale)-Double.parseDouble(cost))/Double.parseDouble(sale))*100;
						                model1.addRow(new Object[]{code, name,qty,sale,cost,vat,margin,margin_perc});
						                
									}
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Item Not Found");
								}
							}
							else
							{
								query="(SELECT "+company+".mr_pos_settlement_history_items.created_date as date,"+company+".mr_vendor.vendor_code,"+company+".mr_vendor.vendor_name,sum("+company+".mr_pos_settlement_history_items.qty) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty) as cost,sum("+company+".mr_pos_settlement_history_items.total)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty) as margin FROM "+company+".mr_pos_settlement_history_items INNER JOIN "+company+".mr_vendor_items on "+company+".mr_pos_settlement_history_items.item_code= "+company+".mr_vendor_items.ref_item_id INNER JOIN "+company+".mr_vendor on "+company+".mr_vendor.vendor_code= "+company+".mr_vendor_items.ref_vendor_id where "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and  '"+date2+"' "+serch_data1+" GROUP BY "+company+".mr_vendor.vendor_code) union (select "+company+".mr_sd_list_history.date as date,"+company+".mr_vendor.vendor_code,"+company+".mr_vendor.vendor_name,sum("+company+".mr_sd_lines_history.qty) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_tmp_item_master.cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as margin,sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as cost FROM  "+company+".mr_sd_lines_history INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num  inner join "+company+".mr_tmp_item_master on mr_tmp_item_master.item_code=mr_sd_lines_history.item_code and mr_tmp_item_master.barcode=mr_sd_lines_history.barcode INNER JOIN "+company+".mr_vendor_items on "+company+".mr_sd_lines_history.item_code= "+company+".mr_vendor_items.ref_item_id INNER JOIN "+company+".mr_vendor on "+company+".mr_vendor.vendor_code= "+company+".mr_vendor_items.ref_vendor_id where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and  '"+date2+"' "+serch_data2+" GROUP BY   "+company+".mr_vendor.vendor_code ) "+analys_data+"";
								
								ResultSet resultSet1 = statement.executeQuery(query);
								if(resultSet1.next())
								{
									int rowCount = model1.getRowCount();
									for (int i = rowCount - 1; i >= 0; i--) {
										model1.removeRow(i);
									}
									model1.addColumn("date");
									model1.addColumn("vendor_code");
									model1.addColumn("vendor_name");
									model1.addColumn("qty");
									model1.addColumn("sale");
									model1.addColumn("cost");
									model1.addColumn("vat");
									model1.addColumn("margin");
									model1.addColumn("margin %");
									ResultSet resultSet = statement.executeQuery(query);
									while(resultSet.next())
									{
										
										String date=resultSet.getString("date");
										String code=resultSet.getString("vendor_code");
						                String name=resultSet.getString("vendor_name");
						                String qty=resultSet.getString("qty");
						                String sale=resultSet.getString("total");
						                String cost=resultSet.getString("cost");
						                String vat=resultSet.getString("vat");
						                double margin=Double.parseDouble(sale)-Double.parseDouble(cost);
						                double margin_perc=((Double.parseDouble(sale)-Double.parseDouble(cost))/Double.parseDouble(sale))*100;
						                model1.addRow(new Object[]{date,code, name,qty,sale,cost,vat,margin,margin_perc});
									}
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Item Not Found");
								}
							}
							
						}
						export_as_csv(company,type,report);
						
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(frame, e);
					e.printStackTrace();
				}
				
			}
		});
		btnExportToCsv.setBounds(271, 436, 138, 25);
		frame.getContentPane().add(btnExportToCsv);
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 476, 506);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		get_itemsdisp(company);
		
	}
	private void get_itemsdisp( String company)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			
			boolean str=false;
			String query="SELECT * FROM "+company+".mr_register_setup"; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String code=resultSet.getString("register_number");
                model.addRow(new Object[]{new Boolean( str ), code});
            }
			if(Mainpage.activeSaD==true)
			{
				model.addRow(new Object[]{new Boolean( str ), "SND"});
			}
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
	public static void  export_as_csv( String company, String type, String report) throws IOException, SQLException
	{
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		table1.scrollRectToVisible(table1.getCellRect(table1.getRowCount()-1, 0, true));
		String ext="";
		if(report.equals("1"))
		{
			ext="-details";
		}
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\sale_report-"+type+""+ext+".csv");

			for(int x = 0; x < model1.getColumnCount(); x++){
	            excel.write(model1.getColumnName(x) );
	            excel.write(",");
	        }

	        excel.write("\n");

	        for(int x=0; x< model1.getRowCount(); x++) {
	            for(int y=0; y < model1.getColumnCount(); y++) {
	                excel.write(model1.getValueAt(x,y).toString());
	                if(y < model1.getColumnCount()-1)
	                {
	                	excel.write(",");
	                }
	                else
	                {
	                	excel.write("\r\n");
	                }
	            }
	        }

	        excel.close();
	        frame.setCursor(Cursor.getDefaultCursor());
	        JOptionPane.showMessageDialog(frame, "Export Completed");
	        frame.setVisible(false);
		
	}
}
