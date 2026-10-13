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

public class PR_report {

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
					PR_report window = new PR_report("mr_pro","admin", "S01", null);
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
	public PR_report(String company, String username,  String store,String version) throws ParseException {
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
		
		JLabel lblDateRange = new JLabel("PURCHASE RETURN REPORTS");
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
					try {
						PO_reports.pr_vendor_rpt.report(date1,date2, con ,vendor,report_type,company);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		button.setBounds(115, 316, 235, 24);
		frame.getContentPane().add(button);
		
		JLabel lblDateRange_1 = new JLabel("Date Range : ");
		lblDateRange_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateRange_1.setBounds(12, 71, 89, 22);
		frame.getContentPane().add(lblDateRange_1);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(140, 233, 256, 22);
		comboBox_1.addItem("POSTED");
		comboBox_1.addItem("OPEN");
		comboBox_1.addItem("VOID");
		
		frame.getContentPane().add(comboBox_1);
		
		chckbxAllCustomers = new JCheckBox("All Vendors");
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
		chckbxAllCustomers.setBounds(12, 176, 113, 25);
		chckbxAllCustomers.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(chckbxAllCustomers);
		chckbxAllCustomers.setSelected(true);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(140, 179, 224, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		btnNewButton = new JButton("");
		btnNewButton.setEnabled(false);
		btnNewButton.setToolTipText("Customer List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new vendor_list(company, "pr_history", version,"",username);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(368, 179, 28, 21);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblReportType = new JLabel("Report Type");
		lblReportType.setBounds(12, 236, 116, 16);
		frame.getContentPane().add(lblReportType);
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 476, 432);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
	}
}
