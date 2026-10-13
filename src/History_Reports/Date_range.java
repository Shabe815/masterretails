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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class Date_range {

	private JFrame frame;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;
	private JComboBox<String> comboBox_1 ;
	private JComboBox<String> comboBox_2 ;
	private JCheckBox chckbxAllRegisters;
	private JComboBox<String> comboBox ;
	private JCheckBox chckbxAllReason;
	private JCheckBox checkBox;
	private JCheckBox chckbxDetailReport;
	private String pagetype;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Date_range window = new Date_range("mr_pro","admin", "S01","F0001", null,"");
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
	public Date_range(String company, String username,  String store,String page_type,String version,String language) {
		initialize(company,username,store,page_type, version,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param balance 
	 * @param po_num 
	 * @param company 
	 * @param host_name 
	 * @param store 
	 * @param username 
	 * @param language 
	 * @param vendor_name 
	 * @param vendor_name2 
	 */
	private void initialize(final String company,final String username,final String store,final String page_type,String version, String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.requestFocus();
		
		pagetype=page_type;
		
		JLabel lblDateRange = new JLabel("Date Range");
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·‰ÿ«ﬁ «·“„‰Ì");
		}
		lblDateRange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateRange.setBounds(26, 27, 138, 32);
		frame.getContentPane().add(lblDateRange);
		
		JLabel lblFrom = new JLabel("From");
		if(language.equals("Arabic"))
		{
			lblFrom.setText("„‰");
		}
		lblFrom.setBounds(26, 83, 56, 16);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		if(language.equals("Arabic"))
		{
			lblTo.setText("·");
		}
		lblTo.setBounds(261, 83, 56, 16);
		frame.getContentPane().add(lblTo);
		
		Date date = new Date();
		dateChooser = new JDateChooser(date);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(26, 120, 138, 22);
		frame.getContentPane().add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(261, 120, 125, 22);
		dateChooser_1.setDate(date);
		frame.getContentPane().add(dateChooser_1);
		
		JButton button = new JButton("Print");
		if(language.equals("Arabic"))
		{
			button.setText("„ÿ»⁄…");
		}
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				if(date1.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select a Date Õœœ «· «—ÌŒ");
				}
				else if(date2.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select a Date Õœœ «· «—ÌŒ");
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
						register=" and "+company+".mr_paylist.ref_reg_no= '"+comboBox_2.getSelectedItem().toString()+"'";
					}
					String users="";
					if(checkBox.isSelected())
					{
						users="";
					}
					else
					{
						users= " and "+company+".mr_paylist.user= '"+comboBox_1.getSelectedItem().toString()+"'";
					}
					String reason="";
					if(chckbxAllReason.isSelected())
					{
						reason="";
					}
					else
					{
						reason=" and "+company+".mr_paylist.reason_code= '"+comboBox.getSelectedItem().toString()+"'";
					}
					String detail="0";
					if(chckbxDetailReport.isSelected())
					{
						detail="1";
					}
					Connection con=Dbsetup.dbconn.getConnection();
					try {
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						report_master.Payinout_summary.report(register,con ,date1, date2,pagetype,users,company,reason,detail,"");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			}
		});
		button.setBounds(26, 327, 161, 24);
		frame.getContentPane().add(button);
		frame.setVisible(true);
		
		chckbxAllRegisters = new JCheckBox("All Registers");
		if(language.equals("Arabic"))
		{
			chckbxAllRegisters.setText("Ã„Ì⁄ «·„”Ã·Ì‰");
		}
		chckbxAllRegisters.setSelected(true);
		chckbxAllRegisters.setBounds(26, 173, 113, 25);
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
		comboBox_2.setBounds(154, 173, 256, 22);
		frame.getContentPane().add(comboBox_2);
		
		chckbxAllReason = new JCheckBox("All Reason");
		if(language.equals("Arabic"))
		{
			chckbxAllReason.setText("ﬂ· «·√”»«»");
		}
		chckbxAllReason.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxAllReason.isSelected())
				{
					comboBox.setSelectedIndex(-1);
					comboBox.setEnabled(false);
				}
				else
				{
					comboBox.setEnabled(true);
				}
			}
		});
		chckbxAllReason.setSelected(true);
		chckbxAllReason.setBackground(new Color(243, 229, 216));
		chckbxAllReason.setBounds(26, 239, 113, 25);
		frame.getContentPane().add(chckbxAllReason);
		
		comboBox = new JComboBox<String>();
		comboBox.setEnabled(false);
		comboBox.setBounds(154, 242, 256, 22);
		frame.getContentPane().add(comboBox);
		
		checkBox = new JCheckBox("All Users");
		if(language.equals("Arabic"))
		{
			checkBox.setText("ﬂ«›… «·„” Œœ„Ì‰");
		}
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkBox.isSelected())
				{
					comboBox_1.setSelectedIndex(-1);
					comboBox_1.setEnabled(false);
				}
				else
				{
					comboBox_1.setEnabled(true);
				}
			}
		});
		checkBox.setSelected(true);
		checkBox.setBackground(new Color(243, 229, 216));
		checkBox.setBounds(26, 209, 113, 25);
		frame.getContentPane().add(checkBox);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setEnabled(false);
		comboBox_1.setBounds(154, 209, 256, 22);
		frame.getContentPane().add(comboBox_1);
		
		JButton button_1 = new JButton("Print A4");
		if(language.equals("Arabic"))
		{
			button_1.setText("ÿ»«⁄… A4");
		}
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				if(date1.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select a Date Õœœ «· «—ÌŒ");
				}
				else if(date2.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select a Date Õœœ «· «—ÌŒ");
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
						register=" and "+company+".mr_paylist.ref_reg_no= '"+comboBox_2.getSelectedItem().toString()+"'";
					}
					String users="";
					if(checkBox.isSelected())
					{
						users="";
					}
					else
					{
						users= " and "+company+".mr_paylist.user= '"+comboBox_1.getSelectedItem().toString()+"'";
					}
					String reason="";
					if(chckbxAllReason.isSelected())
					{
						reason="";
					}
					else
					{
						reason=" and "+company+".mr_paylist.reason_code= '"+comboBox.getSelectedItem().toString()+"'";
					}
					String detail="0";
					if(chckbxDetailReport.isSelected())
					{
						detail="1";
					}
					
					Connection con=Dbsetup.dbconn.getConnection();
					try {
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						report_master.Payinout_summary.report(register,con ,date1, date2,pagetype,users,company,reason,detail,"A4");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			}
		});
		button_1.setBounds(249, 327, 161, 24);
		frame.getContentPane().add(button_1);
		
		chckbxDetailReport = new JCheckBox("Detail Report");
		if(language.equals("Arabic"))
		{
			chckbxDetailReport.setText(" ﬁ—Ì— «· ›«’Ì·");
		}
		chckbxDetailReport.setBackground(new Color(243, 229, 216));
		chckbxDetailReport.setBounds(26, 283, 113, 25);
		frame.getContentPane().add(chckbxDetailReport);
		
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 476, 406);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		get_regdisp( company);
		get_userdisp(  company);
		get_reasondisp(company,pagetype);
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
                comboBox_1.addItem(code);
            }
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
	private void get_reasondisp( String company,String pagetype)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_pos_payreasons where type='"+pagetype+"'"; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String code=resultSet.getString("reason");
                comboBox.addItem(code);
            }
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
}
