package purchase_master;

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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.Button;

import javax.swing.JComboBox;

public class Date_range {

	private JFrame frame;
	private JDateChooser dateChooser ;
	private static DecimalFormat df2;
	private int decimal_count;
	private JDateChooser dateChooser_1 ;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Date_range window = new Date_range("mr_pro","admin", "S01","v001", null, null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param decimal 
	 */
	public Date_range(String company, String username,  String store,String vendor_id,String version, String decimal) {
		initialize(company,username,store,vendor_id, version,decimal);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param balance 
	 * @param po_num 
	 * @param company 
	 * @param host_name 
	 * @param store 
	 * @param username 
	 * @param decimal 
	 * @param vendor_name 
	 * @param vendor_name2 
	 */
	private void initialize(final String company,final String username,final String store,final String vendor_id,String version, String decimal) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.requestFocus();
		
		decimal_count=Integer.parseInt(decimal);
		if(decimal_count==3)
		{
			df2=new DecimalFormat("0.000");
		}
		else
		{
			df2=new DecimalFormat("0.00");
		}
		
		JLabel lblDateRange = new JLabel("Date Range");
		lblDateRange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateRange.setBounds(26, 24, 138, 32);
		frame.getContentPane().add(lblDateRange);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(26, 69, 56, 16);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(263, 69, 56, 16);
		frame.getContentPane().add(lblTo);
		
		Date date = new Date();
		dateChooser = new JDateChooser(date);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(26, 106, 138, 22);
		frame.getContentPane().add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(263, 106, 125, 22);
		dateChooser_1.setDate(date);
		frame.getContentPane().add(dateChooser_1);
		
		Button button = new Button("Print");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if(date1.equals(""))
				{
					date1=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				}
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					double cramt=0;
					double dramt=0;
					String query="";
					Statement statement=con.createStatement();
					
					Date date = dateChooser.getDateEditor().getDate();
					Date oneDayFromSelectedDate = new Date(date.getTime() - Duration.ofDays(1).toMillis());
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					String myDateString = sf.format(oneDayFromSelectedDate);
					
					if(comboBox.getSelectedItem().toString().equals("ALL"))
					{
						query="SELECT sum("+company+".mr_vendor_payment_details.cr_amt) as cr_amt,sum("+company+".mr_vendor_payment_details.dr_amt) as dr_amt,sum("+company+".mr_vendor_payment_details.disc_amt) as disc_amt FROM "+company+".mr_vendor_payment_details	INNER JOIN "+company+".mr_vendor_payment ON "+company+".mr_vendor_payment.ref_po_num="+company+".mr_vendor_payment_details.ref_po_num where ( "+company+".mr_vendor_payment_details.created_date < '"+myDateString+"' ) and  "+company+".mr_vendor_payment_details.ref_vendor_id='"+vendor_id+"' and mr_vendor_payment_details.status!='VOID' group by mr_vendor_payment_details.ref_vendor_id union select sum(0) as cr_amt,sum(grand_total) as dr_amt,sum(0) as disc_amt from "+company+".mr_pos_settlement_history where cust_code='V-"+vendor_id+"' and (created_date < '"+myDateString+" 23:59:59') and type='A/R' group by cust_code union select sum(0) as cr_amt,sum(grand_total) as dr_amt,sum(0) as disc_amt from "+company+".mr_pos_settlement_cash where cust_code='V-"+vendor_id+"' and (created_date < '"+myDateString+" 23:59:59') and type='A/R' group by cust_code union select sum(dr_amt) as cr_amt,sum(cr_amt) as dr_amt,sum(disc_amt) as disc_amt from "+company+".mr_sd_customer_payment_details where ref_cust_id='V-"+vendor_id+"' and (issue_date < '"+myDateString+" 23:59:59') and status!='4' group by ref_cust_id union select sum(amount) as cr_amt,sum(0) as dr_amt,sum(discount) as disc_amt from "+company+".mr_customer_payment where cust_code='V-"+vendor_id+"' and (created_date < '"+myDateString+" 23:59:59') group by cust_code ";
					}
					if(comboBox.getSelectedItem().toString().equals("Purchase"))
					{
						query="SELECT sum("+company+".mr_vendor_payment_details.cr_amt) as cr_amt,sum("+company+".mr_vendor_payment_details.dr_amt) as dr_amt,sum("+company+".mr_vendor_payment_details.disc_amt) as disc_amt FROM "+company+".mr_vendor_payment_details	INNER JOIN "+company+".mr_vendor_payment ON "+company+".mr_vendor_payment.ref_po_num="+company+".mr_vendor_payment_details.ref_po_num where ( "+company+".mr_vendor_payment_details.created_date < '"+myDateString+"' ) and  "+company+".mr_vendor_payment_details.ref_vendor_id='"+vendor_id+"' and mr_vendor_payment_details.status!='VOID'  ";
					}
					if(comboBox.getSelectedItem().toString().equals("Sales"))
					{
						query="select sum(0) as cr_amt,sum(grand_total) as dr_amt,sum(0) as disc_amt from "+company+".mr_pos_settlement_history where cust_code='V-"+vendor_id+"' and (created_date < '"+myDateString+" 23:59:59') and type='A/R' group by cust_code union select sum(0) as cr_amt,sum(grand_total) as dr_amt,sum(0) as disc_amt from "+company+".mr_pos_settlement_cash where cust_code='V-"+vendor_id+"' and (created_date < '"+myDateString+" 23:59:59') and type='A/R' group by cust_code union select sum(dr_amt) as cr_amt,sum(cr_amt) as dr_amt,sum(disc_amt) as disc_amt from "+company+".mr_sd_customer_payment_details where ref_cust_id='V-"+vendor_id+"' and (issue_date < '"+myDateString+" 23:59:59') and status!='4' group by ref_cust_id  union select sum(amount) as cr_amt,sum(0) as dr_amt,sum(discount) as disc_amt from "+company+".mr_customer_payment where cust_code='V-"+vendor_id+"' and (created_date < '"+myDateString+" 23:59:59') group by cust_code";
					}
					
					ResultSet resultSet = statement.executeQuery(query);
					while(resultSet.next())
					{
						if(resultSet.getString("cr_amt")!=null)
						{
							cramt += Double.parseDouble(resultSet.getString("cr_amt"));
						}
						if(resultSet.getString("dr_amt")!=null)
						{
							dramt += Double.parseDouble(resultSet.getString("dr_amt"));
						}
						
					}
					
					String new_cramt=df2.format(cramt);
					String new_dramt=df2.format(dramt);
					report_master.Vendor_summary.report(date1, con ,date2,vendor_id,company,new_cramt,new_dramt,comboBox.getSelectedItem().toString());
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		button.setBounds(104, 240, 227, 46);
		frame.getContentPane().add(button);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(26, 165, 362, 22);
		frame.getContentPane().add(comboBox);
		frame.setVisible(true);
		comboBox.addItem("Purchase");
		comboBox.addItem("Sales");
		comboBox.addItem("ALL");
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 476, 365);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
