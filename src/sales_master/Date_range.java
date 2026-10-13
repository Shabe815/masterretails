package sales_master;

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
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import java.io.FileWriter;

public class Date_range {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;

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
	public Date_range(String company, String username,  String store,String cust_id,String version,String cust_name) {
		initialize(company,username,store,cust_id, version,cust_name);
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
	private void initialize(final String company,final String username,final String store,final String cust_id,String version,final String cust_name) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setAlwaysOnTop(true);
		frame.setLocationRelativeTo(null);
		
		frame.requestFocus();
		
		JLabel lblDateRange = new JLabel("Date Range");
		lblDateRange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateRange.setBounds(26, 63, 138, 32);
		frame.getContentPane().add(lblDateRange);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(26, 126, 56, 16);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(263, 126, 56, 16);
		frame.getContentPane().add(lblTo);
		
		Date date = new Date();
		dateChooser = new JDateChooser(date);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(26, 163, 138, 22);
		frame.getContentPane().add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(263, 163, 125, 22);
		dateChooser_1.setDate(date);
		frame.getContentPane().add(dateChooser_1);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(453, 309, 7, 16);
		frame.getContentPane().add(scrollPane);
		
		scrollPane.setEnabled(false);
		scrollPane.setBackground(new Color(243, 229, 216));
		
		frame.getContentPane().add(scrollPane);
		
		model = new DefaultTableModel(){

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
		table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(20);
		    model.addColumn("Date"); 
		    model.addColumn("Invoice Num"); 
			model.addColumn("Method"); 
			model.addColumn("Cr"); 
			model.addColumn("Dr");; 
		    scrollPane.setViewportView(table);
		    
		    
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        int colmns=model.getColumnCount();
			for (int i=0; i<colmns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(100);
			  if (i==1) column.setPreferredWidth(200);
			  if (i==2) column.setPreferredWidth(70);
			  if (i==3) column.setPreferredWidth(100);
			  if (i==4) column.setPreferredWidth(70);
			}
		
		
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
					Statement statement=con.createStatement(); 
					
					Date date = dateChooser.getDateEditor().getDate();
					Date oneDayFromSelectedDate = new Date(date.getTime() - Duration.ofDays(1).toMillis());
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					String myDateString = sf.format(oneDayFromSelectedDate);
					
					double opencr=0;
					double opendr=0;
					//String as="select sum(grand_total) from "+company+".mr_pos_settlement_cash where "+company+".mr_pos_settlement_cash.cust_code='"+cust_name+"' and tran_date between '"+date1+"' and '"+date2+"'";
					String custsumary="select "+company+".mr_pos_settlement_cash.created_date as tran_date,"+company+".mr_pos_settlement_cash.created_date as post_date,"+company+".mr_pos_settlement_cash.receipt_no as tran_num,	'Invoice' as type,	'' as cheque,	if(type='A/R',0,"+company+".mr_pos_settlement_cash.grand_total) as cramt,	"+company+".mr_pos_settlement_cash.grand_total as dramt,	'0' as disc,	'' as inv_num,	type as method from "+company+".mr_pos_settlement_cash where "+company+".mr_pos_settlement_cash.cust_code='"+cust_id+"' and "+company+".mr_pos_settlement_cash.created_date between '1900-01-01' AND '"+myDateString+" 23:59:59' UNION select	"+company+".mr_pos_settlement_history.created_date as tran_date,	"+company+".mr_pos_settlement_history.created_date as post_date,	"+company+".mr_pos_settlement_history.receipt_no as tran_num,	'Invoice' as type,	'' as cheque,	if(type='A/R',0,"+company+".mr_pos_settlement_history.total+"+company+".mr_pos_settlement_history.vat) as cramt,	"+company+".mr_pos_settlement_history.total+"+company+".mr_pos_settlement_history.vat as dramt,	'0' as disc,	'' as inv_num,	type as method from "+company+".mr_pos_settlement_history where "+company+".mr_pos_settlement_history.cust_code='"+cust_id+"' and "+company+".mr_pos_settlement_history.created_date between '1900-01-01' AND '"+myDateString+" 23:59:59' union select "+company+".mr_customer_payment.created_date as tran_date,	"+company+".mr_customer_payment.created_date as post_date,	"+company+".mr_customer_payment.seq_num as tran_num,	'Payment' as type,	'' as cheque,	if("+company+".mr_customer_payment.amount='','0',"+company+".mr_customer_payment.amount) as cramt,	'0' as dramt,	"+company+".mr_customer_payment.discount as disc,	'' as inv_num,	if(paycode='CASH',paycode,'A/R') as method from "+company+".mr_customer_payment where  "+company+".mr_customer_payment.cust_code='"+cust_id+"' and "+company+".mr_customer_payment.created_date between '1900-01-01' AND '"+myDateString+" 23:59:59' union select	"+company+".mr_sd_customer_payment_details.issue_date as tran_date,	"+company+".mr_sd_customer_payment_details.created_date as post_date,	"+company+".mr_sd_customer_payment_details.seq_num as tran_num,	if("+company+".mr_sd_customer_payment_details.status='2','Payment','Invoice') as type,	cheque_no as cheque,	"+company+".mr_sd_customer_payment_details.dr_amt as cramt,	"+company+".mr_sd_customer_payment_details.cr_amt as dramt,	"+company+".mr_sd_customer_payment_details.disc_amt as disc,	"+company+".mr_sd_customer_payment_details.ref_tran_num as inv_num,	if(paycode='CASH',paycode,'A/R') as method from "+company+".mr_sd_customer_payment_details where "+company+".mr_sd_customer_payment_details.ref_cust_id='"+cust_id+"' and "+company+".mr_sd_customer_payment_details.created_date between '1900-01-01' AND '"+myDateString+" 23:59:59' and  "+company+".mr_sd_customer_payment_details.status!='4' order by tran_date";
					ResultSet resultSet = statement.executeQuery(custsumary);
					while(resultSet.next())
					{
						opencr += Double.parseDouble(resultSet.getString("cramt"));
						opendr += Double.parseDouble(resultSet.getString("dramt"));
					}
					
					if(Mainpage.SoftLang.equals("Ar"))
					{
						report_master.customer_summary.report(cust_id,con ,date1, date2,cust_name,"",PosscreenArabic.printer,company,opencr,opendr);
					}
					else
					{
						report_master.customer_summary.report(cust_id,con ,date1, date2,cust_name,"",posscreen.printer,company,opencr,opendr);
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		button.setBounds(26, 241, 149, 24);
		frame.getContentPane().add(button);
		
		Button button_1 = new Button("Print A4");
		button_1.addActionListener(new ActionListener() {
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
					
					Date date = dateChooser.getDateEditor().getDate();
					Date oneDayFromSelectedDate = new Date(date.getTime() - Duration.ofDays(1).toMillis());
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					String myDateString = sf.format(oneDayFromSelectedDate);
					
					
					Statement statement=con.createStatement(); 
					double opencr=0;
					double opendr=0;
					//String as="select sum(grand_total) from "+company+".mr_pos_settlement_cash where "+company+".mr_pos_settlement_cash.cust_code='"+cust_name+"' and tran_date between '"+date1+"' and '"+date2+"'";
					String custsumary="select "+company+".mr_pos_settlement_cash.created_date as tran_date,"+company+".mr_pos_settlement_cash.created_date as post_date,"+company+".mr_pos_settlement_cash.receipt_no as tran_num,	'Invoice' as type,	'' as cheque,	if(type='A/R',0,"+company+".mr_pos_settlement_cash.grand_total) as cramt,	"+company+".mr_pos_settlement_cash.grand_total as dramt,	'0' as disc,	'' as inv_num,	type as method from "+company+".mr_pos_settlement_cash where "+company+".mr_pos_settlement_cash.cust_code='"+cust_id+"' and "+company+".mr_pos_settlement_cash.created_date between '1900-01-01' AND '"+myDateString+" 23:59:59' UNION select	"+company+".mr_pos_settlement_history.created_date as tran_date,	"+company+".mr_pos_settlement_history.created_date as post_date,	"+company+".mr_pos_settlement_history.receipt_no as tran_num,	'Invoice' as type,	'' as cheque,	if(type='A/R',0,"+company+".mr_pos_settlement_history.total+"+company+".mr_pos_settlement_history.vat) as cramt,	"+company+".mr_pos_settlement_history.total+"+company+".mr_pos_settlement_history.vat as dramt,	'0' as disc,	'' as inv_num,	type as method from "+company+".mr_pos_settlement_history where "+company+".mr_pos_settlement_history.cust_code='"+cust_id+"' and "+company+".mr_pos_settlement_history.created_date between '1900-01-01' AND '"+myDateString+" 23:59:59' union select "+company+".mr_customer_payment.created_date as tran_date,	"+company+".mr_customer_payment.created_date as post_date,	"+company+".mr_customer_payment.seq_num as tran_num,	'Payment' as type,	'' as cheque,	if("+company+".mr_customer_payment.amount='','0',"+company+".mr_customer_payment.amount) as cramt,	'0' as dramt,	"+company+".mr_customer_payment.discount as disc,	'' as inv_num,	if(paycode='CASH',paycode,'A/R') as method from "+company+".mr_customer_payment where  "+company+".mr_customer_payment.cust_code='"+cust_id+"' and "+company+".mr_customer_payment.created_date between '1900-01-01' AND '"+myDateString+" 23:59:59' union select	"+company+".mr_sd_customer_payment_details.issue_date as tran_date,	"+company+".mr_sd_customer_payment_details.created_date as post_date,	"+company+".mr_sd_customer_payment_details.seq_num as tran_num,	if("+company+".mr_sd_customer_payment_details.status='2','Payment','Invoice') as type,	cheque_no as cheque,	"+company+".mr_sd_customer_payment_details.dr_amt as cramt,	"+company+".mr_sd_customer_payment_details.cr_amt as dramt,	"+company+".mr_sd_customer_payment_details.disc_amt as disc,	"+company+".mr_sd_customer_payment_details.ref_tran_num as inv_num,	if(paycode='CASH',paycode,'A/R') as method from "+company+".mr_sd_customer_payment_details where "+company+".mr_sd_customer_payment_details.ref_cust_id='"+cust_id+"' and "+company+".mr_sd_customer_payment_details.created_date between '1900-01-01' AND '"+myDateString+" 23:59:59' and  "+company+".mr_sd_customer_payment_details.status!='4' order by tran_date";
					ResultSet resultSet = statement.executeQuery(custsumary);
					while(resultSet.next())
					{
						opencr += Double.parseDouble(resultSet.getString("cramt"));
						opendr += Double.parseDouble(resultSet.getString("dramt"));
					}
					
					report_master.customer_summary.report(cust_id,con ,date1, date2,cust_name,"A4","", company,opencr,opendr);
					frame.setVisible(false);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		button_1.setBounds(246, 241, 149, 24);
		frame.getContentPane().add(button_1);
		
		Button button_2 = new Button("Export To CSV");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					if(date1.equals(""))
					{
						date1=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					}
					String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					Connection con=Dbsetup.dbconn.getConnection();
					
					Date date = dateChooser.getDateEditor().getDate();
					Date oneDayFromSelectedDate = new Date(date.getTime() - Duration.ofDays(1).toMillis());
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					String myDateString = sf.format(oneDayFromSelectedDate);
					
					
					Statement statement=con.createStatement(); 
					double opencr=0;
					double opendr=0;
					//String as="select sum(grand_total) from "+company+".mr_pos_settlement_cash where "+company+".mr_pos_settlement_cash.cust_code='"+cust_name+"' and tran_date between '"+date1+"' and '"+date2+"'";
					String custsumary="select "+company+".mr_pos_settlement_cash.created_date as tran_date,"+company+".mr_pos_settlement_cash.created_date as post_date,"+company+".mr_pos_settlement_cash.receipt_no as tran_num,	'Invoice' as type,	'' as cheque,	if(type='A/R',0,"+company+".mr_pos_settlement_cash.grand_total) as cramt,	"+company+".mr_pos_settlement_cash.grand_total as dramt,	'0' as disc,	'' as inv_num,	type as method from "+company+".mr_pos_settlement_cash where "+company+".mr_pos_settlement_cash.cust_code='"+cust_id+"' and "+company+".mr_pos_settlement_cash.created_date between '1900-01-01' AND '"+myDateString+" 23:59:59' UNION select	"+company+".mr_pos_settlement_history.created_date as tran_date,	"+company+".mr_pos_settlement_history.created_date as post_date,	"+company+".mr_pos_settlement_history.receipt_no as tran_num,	'Invoice' as type,	'' as cheque,	if(type='A/R',0,"+company+".mr_pos_settlement_history.total+"+company+".mr_pos_settlement_history.vat) as cramt,	"+company+".mr_pos_settlement_history.total+"+company+".mr_pos_settlement_history.vat as dramt,	'0' as disc,	'' as inv_num,	type as method from "+company+".mr_pos_settlement_history where "+company+".mr_pos_settlement_history.cust_code='"+cust_id+"' and "+company+".mr_pos_settlement_history.created_date between '1900-01-01' AND '"+myDateString+" 23:59:59' union select "+company+".mr_customer_payment.created_date as tran_date,	"+company+".mr_customer_payment.created_date as post_date,	"+company+".mr_customer_payment.seq_num as tran_num,	'Payment' as type,	'' as cheque,	if("+company+".mr_customer_payment.amount='','0',"+company+".mr_customer_payment.amount) as cramt,	'0' as dramt,	"+company+".mr_customer_payment.discount as disc,	'' as inv_num,	if(paycode='CASH',paycode,'A/R') as method from "+company+".mr_customer_payment where  "+company+".mr_customer_payment.cust_code='"+cust_id+"' and "+company+".mr_customer_payment.created_date between '1900-01-01' AND '"+myDateString+" 23:59:59' union select	"+company+".mr_sd_customer_payment_details.issue_date as tran_date,	"+company+".mr_sd_customer_payment_details.created_date as post_date,	"+company+".mr_sd_customer_payment_details.seq_num as tran_num,	if("+company+".mr_sd_customer_payment_details.status='2','Payment','Invoice') as type,	cheque_no as cheque,	"+company+".mr_sd_customer_payment_details.dr_amt as cramt,	"+company+".mr_sd_customer_payment_details.cr_amt as dramt,	"+company+".mr_sd_customer_payment_details.disc_amt as disc,	"+company+".mr_sd_customer_payment_details.ref_tran_num as inv_num,	if(paycode='CASH',paycode,'A/R') as method from "+company+".mr_sd_customer_payment_details where "+company+".mr_sd_customer_payment_details.ref_cust_id='"+cust_id+"' and "+company+".mr_sd_customer_payment_details.created_date between '1900-01-01' AND '"+myDateString+" 23:59:59' and  "+company+".mr_sd_customer_payment_details.status!='4' order by tran_date";
					ResultSet resultSet = statement.executeQuery(custsumary);
					while(resultSet.next())
					{
						opencr += Double.parseDouble(resultSet.getString("cramt"));
						opendr += Double.parseDouble(resultSet.getString("dramt"));
					}
					
					
					String query="select "+company+".mr_pos_settlement_cash.created_date as tran_date,"+company+".mr_pos_settlement_cash.created_date as post_date,"+company+".mr_pos_settlement_cash.receipt_no as tran_num,	'Invoice' as type,	'' as cheque,	if(type='A/R',0,"+company+".mr_pos_settlement_cash.grand_total) as cramt,	"+company+".mr_pos_settlement_cash.grand_total as dramt,	'0' as disc,	'' as inv_num,	type as method from "+company+".mr_pos_settlement_cash where "+company+".mr_pos_settlement_cash.cust_code='"+cust_id+"' and "+company+".mr_pos_settlement_cash.created_date between '"+date1+"' AND '"+date2+" 23:59:59' UNION select	"+company+".mr_pos_settlement_history.created_date as tran_date,	"+company+".mr_pos_settlement_history.created_date as post_date,	"+company+".mr_pos_settlement_history.receipt_no as tran_num,	'Invoice' as type,	'' as cheque,	if(type='A/R',0,"+company+".mr_pos_settlement_history.total+"+company+".mr_pos_settlement_history.vat) as cramt,	"+company+".mr_pos_settlement_history.total+"+company+".mr_pos_settlement_history.vat as dramt,	'0' as disc,	'' as inv_num,	type as method from "+company+".mr_pos_settlement_history where "+company+".mr_pos_settlement_history.cust_code='"+cust_id+"' and "+company+".mr_pos_settlement_history.created_date between '"+date1+"' AND '"+date2+" 23:59:59' union select "+company+".mr_customer_payment.created_date as tran_date,	"+company+".mr_customer_payment.created_date as post_date,	"+company+".mr_customer_payment.seq_num as tran_num,	'Payment' as type,	'' as cheque,	if("+company+".mr_customer_payment.amount='','0',"+company+".mr_customer_payment.amount) as cramt,	'0' as dramt,	"+company+".mr_customer_payment.discount as disc,	'' as inv_num,	if(paycode='CASH',paycode,'A/R') as method from "+company+".mr_customer_payment where  "+company+".mr_customer_payment.cust_code='"+cust_id+"' and "+company+".mr_customer_payment.created_date between '"+date1+"' AND '"+date2+" 23:59:59' union select	"+company+".mr_sd_customer_payment_details.issue_date as tran_date,	"+company+".mr_sd_customer_payment_details.created_date as post_date,	"+company+".mr_sd_customer_payment_details.seq_num as tran_num,	if("+company+".mr_sd_customer_payment_details.status='2','Payment','Invoice') as type,	cheque_no as cheque,	"+company+".mr_sd_customer_payment_details.dr_amt as cramt,	"+company+".mr_sd_customer_payment_details.cr_amt as dramt,	"+company+".mr_sd_customer_payment_details.disc_amt as disc,	"+company+".mr_sd_customer_payment_details.ref_tran_num as inv_num,	if(paycode='CASH',paycode,'A/R') as method from "+company+".mr_sd_customer_payment_details where "+company+".mr_sd_customer_payment_details.ref_cust_id='"+cust_id+"' and "+company+".mr_sd_customer_payment_details.created_date between '"+date1+"' AND '"+date2+" 23:59:59' and  "+company+".mr_sd_customer_payment_details.status!='4' order by tran_date";
					ResultSet resultSet1 = statement.executeQuery(query);
					int rowCount = model.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					while(resultSet1.next())
					{
						String tran_date=resultSet1.getString("tran_date");
			            String tran_num=resultSet1.getString("tran_num");
			            String method=resultSet1.getString("method");
			            
			            String cramt=resultSet1.getString("cramt");
			            String dramt=resultSet1.getString("dramt");
			            model.addRow(new Object[]{tran_date,tran_num,method,cramt,dramt});
					}
					
					
					
	    			LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyy");  
				    String formattedDate = myDateObj.format(myFormatObj); 
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		    		FileWriter excel;
					excel = new FileWriter("C:\\MasterRetails\\Export\\Customersummary-"+formattedDate+".csv");
					for(int x = 0; x < model.getColumnCount(); x++){
	    	            excel.write(model.getColumnName(x) );
	    	            excel.write(",");
	    	        }

	    	        excel.write("\n");
	    	        
	    	        excel.write(",");
	    	        excel.write("Opening Balance,");
	    	        excel.write(",");
	    	        excel.write(Double.toString(opencr)+",");
	    	        excel.write(Double.toString(opendr));
	    	        excel.write("\n");
	    	        
	    	        for(int x=0; x< model.getRowCount(); x++) {
	    	            for(int y=0; y < model.getColumnCount(); y++) {
	    	                excel.write(model.getValueAt(x,y).toString());
	    	                if(y < model.getColumnCount()-1)
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
				} catch (Exception e) {
					frame.setCursor(Cursor.getDefaultCursor());
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_2.setBounds(141, 287, 149, 24);
		frame.getContentPane().add(button_2);
		frame.setVisible(true);
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 476, 365);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
