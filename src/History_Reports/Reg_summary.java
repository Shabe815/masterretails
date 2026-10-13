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
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import CafePro.Mainpage;

import com.toedter.calendar.JDateChooser;

import java.awt.Button;

import javax.swing.JCheckBox;

public class Reg_summary {

	public static JFrame frame;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;
	private JTable table;
	private DefaultTableModel model;
	private JTable table1;
	private DefaultTableModel model1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Reg_summary window = new Reg_summary("mr_pro","admin", "S01", null, null, null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param posreg 
	 * @param printer 
	 */
	public Reg_summary(String company, String username,  String store,String version, String posreg, String printer) {
		initialize(company,username,store, version, posreg, printer);
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
	 */
	private void initialize(final String company,final String username,final String store,String version, final String posreg, final String printer) {
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
		lblFrom.setBounds(12, 93, 43, 22);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(218, 93, 27, 22);
		frame.getContentPane().add(lblTo);
		
		Date date = new Date();
		dateChooser = new JDateChooser(date);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(53, 93, 138, 22);
		frame.getContentPane().add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(271, 93, 125, 22);
		
		dateChooser_1.setDate(date);
		frame.getContentPane().add(dateChooser_1);
		
		Button button = new Button("Print A4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if(date1.equals(""))
				{
					date1=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				}
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				Connection con=Dbsetup.dbconn.getConnection();
				Object[] user_list = new String[model1.getRowCount()];
				for (int i = 0; i < model1.getRowCount(); i++) {
					Boolean chked = Boolean.valueOf(table1.getValueAt(i, 0).toString());
					String dataCol1 = model1.getValueAt(i, 1).toString();
					if (chked) {
						user_list[i]=dataCol1;
					}
				}
				String users_list="";
				for(int i=0;i<user_list.length;i++)
				{
					if(user_list[i]!=null)
					{
						
						if(!users_list.equals(""))
						{
							users_list+=",";
						}
						if(!users_list.equals(""))
						{
							users_list+="'";
						}
						if(users_list.equals(""))
						{
							users_list+="'";
						}
						users_list+=user_list[i];
						if(!users_list.equals(""))
						{
							users_list+="'";
						}
					}
				}
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
				if(users_list.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select Any user");
				}
				else if(reg_list.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select Any register");
				}
				else
				{
					try {
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						//2021-05-27 23:59:59.000000
						report_master.Register_summary.report(date1+" 00:00:00.000000",date2+" 23:59:59.000000", con ,reg_list,users_list,company,"0");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		button.setBounds(251, 260, 175, 24);
		frame.getContentPane().add(button);
		
		JLabel lblRegisters = new JLabel("Registers : ");
		lblRegisters.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegisters.setBounds(12, 128, 89, 16);
		frame.getContentPane().add(lblRegisters);
		
		JLabel lblDateRange_1 = new JLabel("Date Range");
		lblDateRange_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateRange_1.setBounds(12, 58, 89, 22);
		frame.getContentPane().add(lblDateRange_1);
		frame.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 149, 125, 105);
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
		    
		    chckbxAll.setBounds(84, 124, 125, 25);
		    frame.getContentPane().add(chckbxAll);
		    
		    JLabel lblUsers = new JLabel("Users : ");
		    lblUsers.setFont(new Font("Tahoma", Font.BOLD, 13));
		    lblUsers.setBounds(251, 128, 56, 16);
		    frame.getContentPane().add(lblUsers);
		    
		    final JCheckBox checkBox = new JCheckBox("ALL");
		    checkBox.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(checkBox.isSelected())
					{
		    			for(int i=0;i<model1.getRowCount();i++)
		    			{
		    				model1.setValueAt(true , i, 0);
		    			}
		    			
					}
					else
					{
						for(int i=0;i<model1.getRowCount();i++)
		    			{
		    				model1.setValueAt(false , i, 0);
		    			}
					}
		    	}
		    });
		    checkBox.setBounds(301, 124, 125, 25);
		    frame.getContentPane().add(checkBox);
		    
		    JScrollPane scrollPane_1 = new JScrollPane();
		    scrollPane_1.setBackground(new Color(243, 229, 216));
		    scrollPane_1.setBounds(301, 149, 125, 105);
		    frame.getContentPane().add(scrollPane_1);
		    model1 = new DefaultTableModel(){

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
			table1 = new JTable(model1);
			
			    table1.setFont(new Font("Serif", Font.BOLD, 15));
				table1.setRowHeight(20);
				model1.addColumn(""); 
				model1.addColumn(""); 
				
				table1.setTableHeader(null);
				table1.setShowGrid(false);
				
				int a1=table1.getColumnCount();
				for(int i=0;i<a1;i++)
				{
					TableColumn column=table1.getColumnModel().getColumn(i);
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
				scrollPane_1.setViewportView(table1);
				
				Button button_1 = new Button("Opening Register");
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Connection con=Dbsetup.dbconn.getConnection();
						String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
						if(date1.equals(""))
						{
							date1=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
						}
						String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
						Object[] user_list = new String[model1.getRowCount()];
						for (int i = 0; i < model1.getRowCount(); i++) {
							Boolean chked = Boolean.valueOf(table1.getValueAt(i, 0).toString());
							String dataCol1 = model1.getValueAt(i, 1).toString();
							if (chked) {
								user_list[i]=dataCol1;
							}
						}
						String users_list="";
						for(int i=0;i<user_list.length;i++)
						{
							if(user_list[i]!=null)
							{
								
								if(!users_list.equals(""))
								{
									users_list+=",";
								}
								if(!users_list.equals(""))
								{
									users_list+="'";
								}
								if(users_list.equals(""))
								{
									users_list+="'";
								}
								users_list+=user_list[i];
								if(!users_list.equals(""))
								{
									users_list+="'";
								}
							}
						}
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
						if(users_list.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Select Any user");
						}
						else if(reg_list.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Select Any register");
						}
						else
						{
							try {
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
								//2021-05-27 23:59:59.000000
								//report_master.Register_summary.report(date1+" 00:00:00.000000",date2+" 23:59:59.000000", con ,reg_list,users_list,company);
								report_master.His_open_reg.report(reg_list, con, printer,company,date1+" 00:00:00.000000",date2+" 23:59:59.000000");
								//report_master.HisClosing_reg.report(reg_list, con, printer,company,date1+" 00:00:00.000000",date2+" 23:59:59.000000");
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
				});
				button_1.setBounds(322, 10, 138, 24);
				frame.getContentPane().add(button_1);
				
				Button button_2 = new Button("Print Closing Receipt");
				button_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Connection con=Dbsetup.dbconn.getConnection();
						String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
						if(date1.equals(""))
						{
							date1=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
						}
						String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
						Object[] user_list = new String[model1.getRowCount()];
						for (int i = 0; i < model1.getRowCount(); i++) {
							Boolean chked = Boolean.valueOf(table1.getValueAt(i, 0).toString());
							String dataCol1 = model1.getValueAt(i, 1).toString();
							if (chked) {
								user_list[i]=dataCol1;
							}
						}
						String users_list="";
						for(int i=0;i<user_list.length;i++)
						{
							if(user_list[i]!=null)
							{
								
								if(!users_list.equals(""))
								{
									users_list+=",";
								}
								if(!users_list.equals(""))
								{
									users_list+="'";
								}
								if(users_list.equals(""))
								{
									users_list+="'";
								}
								users_list+=user_list[i];
								if(!users_list.equals(""))
								{
									users_list+="'";
								}
							}
						}
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
						if(users_list.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Select Any user");
						}
						else if(reg_list.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Select Any register");
						}
						else
						{
							try {
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
								//2021-05-27 23:59:59.000000
								//report_master.Register_summary.report(date1+" 00:00:00.000000",date2+" 23:59:59.000000", con ,reg_list,users_list,company);
								report_master.HisClosing_reg.report(reg_list, con, printer,company,date1+" 00:00:00.000000",date2+" 23:59:59.000000",users_list);
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
				button_2.setBounds(322, 40, 138, 24);
				frame.getContentPane().add(button_2);
				
				Button button_3 = new Button("Print Consolidate Receipt");
				button_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
						if(date1.equals(""))
						{
							date1=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
						}
						String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
						Connection con=Dbsetup.dbconn.getConnection();
						Object[] user_list = new String[model1.getRowCount()];
						for (int i = 0; i < model1.getRowCount(); i++) {
							Boolean chked = Boolean.valueOf(table1.getValueAt(i, 0).toString());
							String dataCol1 = model1.getValueAt(i, 1).toString();
							if (chked) {
								user_list[i]=dataCol1;
							}
						}
						String users_list="";
						for(int i=0;i<user_list.length;i++)
						{
							if(user_list[i]!=null)
							{
								
								if(!users_list.equals(""))
								{
									users_list+=",";
								}
								if(!users_list.equals(""))
								{
									users_list+="'";
								}
								if(users_list.equals(""))
								{
									users_list+="'";
								}
								users_list+=user_list[i];
								if(!users_list.equals(""))
								{
									users_list+="'";
								}
							}
						}
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
						if(users_list.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Select Any user");
						}
						else if(reg_list.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Select Any register");
						}
						else
						{
							try {
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
								//2021-05-27 23:59:59.000000
								report_master.Register_summary.report(date1+" 00:00:00.000000",date2+" 23:59:59.000000", con ,reg_list,users_list,company,"1");
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				});
				button_3.setBounds(39, 260, 175, 24);
				frame.getContentPane().add(button_3);
		    
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 476, 365);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		get_itemsdisp(company);
		get_usersdisp( company);
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
	private void get_usersdisp( String company)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			
			boolean str=false;
			String query="SELECT * FROM "+company+".mr_users"; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String code=resultSet.getString("user_name");
                model1.addRow(new Object[]{new Boolean( str ), code});
            }
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
}
