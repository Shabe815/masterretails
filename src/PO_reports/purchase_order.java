package PO_reports;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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

import purchase_master.vendor_list;
import javax.swing.JCheckBox;

public class purchase_order {

	private JFrame frame;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;
	private JComboBox<String> comboBox_1 ;
	private JCheckBox chckbxAllCustomers;
	public static JTextField textField;
	private JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					purchase_order window = new purchase_order("mr_pro","admin", "S01", null);
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
	public purchase_order(String company, String username,  String store,String version) throws ParseException {
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
		
		JLabel lblDateRange = new JLabel("PURCHASE ORDER REPORTS");
		lblDateRange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateRange.setBounds(12, 17, 256, 39);
		frame.getContentPane().add(lblDateRange);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(12, 112, 43, 22);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(254, 112, 27, 22);
		frame.getContentPane().add(lblTo);
		
		Date date = new Date();
		dateChooser = new JDateChooser(date);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(140, 112, 102, 22);
		frame.getContentPane().add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(281, 112, 95, 22);
		
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
						String report_type=comboBox_1.getSelectedItem().toString();
						
						Connection con=Dbsetup.dbconn.getConnection();
						PO_reports.po_vendor_rpt.report(date1,date2, con ,vendor,report_type,company);
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
		button.setBounds(115, 236, 290, 30);
		frame.getContentPane().add(button);
		
		JLabel lblDateRange_1 = new JLabel("Date Range : ");
		lblDateRange_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateRange_1.setBounds(12, 73, 89, 22);
		frame.getContentPane().add(lblDateRange_1);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(140, 194, 236, 25);
		comboBox_1.addItem("POSTED");
		comboBox_1.addItem("OPEN");
		
		frame.getContentPane().add(comboBox_1);
		
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
		chckbxAllCustomers.setBounds(12, 151, 113, 25);
		chckbxAllCustomers.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(chckbxAllCustomers);
		chckbxAllCustomers.setSelected(true);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(140, 151, 236, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		btnNewButton = new JButton("");
		btnNewButton.setEnabled(false);
		btnNewButton.setToolTipText("Vendor List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new vendor_list(company, "po_history", version,"",username);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(377, 151, 28, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblReportType = new JLabel("Report Type");
		lblReportType.setBounds(12, 194, 116, 25);
		frame.getContentPane().add(lblReportType);
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 476, 349);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
	}
}
