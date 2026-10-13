package History_Reports;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Dbsetup.dbconn;

import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JComboBox;

public class Tax_reporting {

	public static JFrame frame;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;
	private DefaultTableModel model;
	private JTable table;
	private static DecimalFormat df2;
	private static int decimalPlaces;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	//private String report_query;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Tax_reporting window = new Tax_reporting("mr_pro","admin", "S01", null, dbconn.getConnection());
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
	public Tax_reporting(String company, String username,  String store,String version,Connection con) throws ParseException {
		initialize(company,username,store, version,con);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param balance 
	 * @param po_num 
	 * @param company 
	 * @param host_name 
	 * @param store 
	 * @param username 
	 * @param con 
	 * @param vendor_name 
	 * @param vendor_name2 
	 * @throws ParseException 
	 */
	private void initialize(final String company,final String username,final String store,final String version, Connection con) throws ParseException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.requestFocus();
		
		JLabel lblDateRange = new JLabel("TAX REPORT");
		lblDateRange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateRange.setBounds(22, 0, 256, 39);
		frame.getContentPane().add(lblDateRange);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(114, 38, 43, 22);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(320, 38, 27, 22);
		frame.getContentPane().add(lblTo);
		
		Date date = new Date();
		dateChooser = new JDateChooser(date);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(159, 38, 138, 22);
		frame.getContentPane().add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(374, 38, 125, 22);
		
		dateChooser_1.setDate(date);
		frame.getContentPane().add(dateChooser_1);
		
		JLabel lblDateRange_1 = new JLabel("Date Range : ");
		lblDateRange_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateRange_1.setBounds(22, 38, 89, 22);
		frame.getContentPane().add(lblDateRange_1);
		
		//Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				get_date_based_tax(date1,date2,company,comboBox.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),comboBox_2.getSelectedItem().toString());
			}

			
		});
		btnProceed.setBounds(511, 38, 97, 55);
		frame.getContentPane().add(btnProceed);
		
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setEnabled(false);
		scrollPane.setBounds(5, 106, 745, 503);
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
		    
		    
		scrollPane.setViewportView(table);
		
		int columns=model.getColumnCount();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader header=table.getTableHeader();
		  
	    header.setPreferredSize(new Dimension(0,50));
	    for (int i=0; i<columns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(80);
		  if (i==1) column.setPreferredWidth(80);
		  if (i==2) column.setPreferredWidth(160);
		  if (i==3) column.setPreferredWidth(100);
		  if (i==4) column.setPreferredWidth(100);
		  if (i==5) column.setPreferredWidth(150);
		  if (i==6) column.setPreferredWidth(150);
		  if (i==7) column.setPreferredWidth(150);
		  if (i==8) column.setPreferredWidth(150);
		  if (i==9) column.setPreferredWidth(150);
		}
		
		JButton btnExportToCsv = new JButton("Export To CSV");
		btnExportToCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				try {
				export_as_csv(date1,date2,company,comboBox.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		});
		btnExportToCsv.setBounds(453, 8, 131, 25);
		frame.getContentPane().add(btnExportToCsv);
		
		JLabel lblTotalSale = new JLabel("Total Amount : ");
		lblTotalSale.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalSale.setBounds(5, 626, 106, 26);
		frame.getContentPane().add(lblTotalSale);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setEditable(false);
		textField.setBounds(126, 617, 256, 43);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblTotalVat = new JLabel("Total Vat : ");
		lblTotalVat.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalVat.setBounds(427, 630, 72, 16);
		frame.getContentPane().add(lblTotalVat);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_1.setEditable(false);
		textField_1.setBounds(499, 617, 247, 43);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("INPUT TAX");
		comboBox.addItem("OUTPUT TAX");
		
		
		comboBox.setBounds(92, 70, 143, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel lblTaxType = new JLabel("TAX TYPE");
		lblTaxType.setBounds(22, 73, 65, 16);
		frame.getContentPane().add(lblTaxType);
		
		JLabel lblReportType = new JLabel("Report Type");
		lblReportType.setBounds(289, 73, 93, 16);
		frame.getContentPane().add(lblReportType);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("DATE WISE");
		comboBox_1.addItem("DOCUMENT WISE");
		comboBox_1.addItem("MONTH WISE");
		
		comboBox_1.setBounds(377, 70, 125, 22);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblSortBy = new JLabel("Sort By");
		lblSortBy.setBounds(620, 41, 56, 16);
		frame.getContentPane().add(lblSortBy);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.addItem("Date");
		comboBox_2.addItem("Tran Date");
		comboBox_2.addItem("Name");
		comboBox_2.addItem("City");
		comboBox_2.addItem("Invoice");
		comboBox_2.addItem("Month");
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				get_date_based_tax(date1,date2,company,comboBox.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),comboBox_2.getSelectedItem().toString());
			}
		});

		
		comboBox_2.setBounds(620, 71, 126, 22);
		frame.getContentPane().add(comboBox_2);
		 
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 764, 708);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		onload(company, store,con);
		
	}
	private void export_as_csv(String date1, String date2, String company,String taxtype,String report_type ) throws IOException {
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\tax-"+taxtype+"-"+report_type+".csv");

			for(int x = 0; x < model.getColumnCount(); x++){
	            excel.write(model.getColumnName(x) );
	            excel.write(",");
	        }

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
		
	}
	private void get_date_based_tax(String date1, String date2,String company,String taxtype,String report_type,String sort) {
		try{
			
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();
			double ttlvat=0;
			double ttlamt=0;
			if(taxtype.equals("INPUT TAX"))
			{
				
				
				if(report_type.equals("DATE WISE"))
				{
					String sortstr="";
					if(sort.equals("Date"))
					{
						sortstr="order by inv_date";
					}
					else if(sort.equals("Tran Date"))
					{
						sortstr="order by inv_date";
					}
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
		        	    model.removeRow(i);
		        	}
					model.setColumnCount(0);
					String query="(select  p1.po_date as inv_date, sum(p1.sub_ttl_amt) as subttl, sum(p1.total_vat) as subvat, SUM(CASE WHEN p1.total_vat = 0 THEN p1.grand_total ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN p1.total_vat = 0 THEN p1.total_vat ELSE 0 END) AS Nil_vat, SUM(CASE WHEN p1.total_vat != 0 THEN p1.sub_ttl_amt ELSE 0 END) AS vat_sale, SUM(CASE WHEN p1.total_vat != 0 THEN p1.total_vat ELSE 0 END) AS vat_amt from "+company+".mr_purchase_list_history p1  where p1.po_date between '"+date1+"' and '"+date2+"' and p1.status!='VOID' GROUP BY inv_date ) union (select  p1.pr_date as inv_date, -sum(p1.sub_ttl_amt) as subttl, -sum(p1.total_vat) as subvat, -SUM(CASE WHEN p1.total_vat = 0 THEN p1.grand_total ELSE 0 END) AS Nil_ttl, -SUM(CASE WHEN p1.total_vat = 0 THEN p1.total_vat ELSE 0 END) AS Nil_vat, -SUM(CASE WHEN p1.total_vat != 0 THEN p1.sub_ttl_amt ELSE 0 END) AS vat_sale, -SUM(CASE WHEN p1.total_vat != 0 THEN p1.total_vat ELSE 0 END) AS vat_amt from "+company+".mr_purchase_return_list p1  where p1.pr_date between '"+date1+"' and '"+date2+"' and p1.status='POSTED' GROUP BY inv_date ) union (select  p1.inv_date as inv_date, sum(p1.net_total) as subttl, sum(p1.vat_amt) as subvat, SUM(CASE WHEN p1.vat_amt = 0 THEN p1.net_total ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN p1.vat_amt = 0 THEN p1.vat_amt ELSE 0 END) AS Nil_vat, SUM(CASE WHEN p1.vat_amt != 0 THEN p1.net_total ELSE 0 END) AS vat_sale, SUM(CASE WHEN p1.vat_amt != 0 THEN p1.vat_amt ELSE 0 END) AS vat_amt from "+company+".mr_invoice_history p1  where p1.inv_date between '"+date1+"' and '"+date2+"' and p1.status!='VOID' GROUP BY inv_date ) "+sortstr+" ";
					//String query="select  p1.po_date as inv_date, sum(p1.grand_total) as subttl, sum(p1.total_vat) as subvat, SUM(CASE WHEN p1.total_vat = 0 THEN p1.grand_total ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN p1.total_vat = 0 THEN p1.total_vat ELSE 0 END) AS Nil_vat, SUM(CASE WHEN p1.total_vat != 0 THEN p1.grand_total ELSE 0 END) AS vat_sale, SUM(CASE WHEN p1.total_vat != 0 THEN p1.total_vat ELSE 0 END) AS vat_amt from "+company+".mr_purchase_list_history p1  where p1.po_date between '"+date1+"' and '"+date2+"' and p1.status!='VOID' GROUP BY inv_date "+sortstr+" ";
					model.addColumn("Invoice Date");
				    model.addColumn("Total PO Amt"); 
				    model.addColumn("Total PO TaxAmt"); 
					model.addColumn("Nil_PO AMT"); 
					model.addColumn("Nil PO Tax");
					model.addColumn("VAT5% PO AMT"); 
					model.addColumn("VAT5% PO Tax"); 
					ResultSet resultSet = statement.executeQuery(query);
					while(resultSet.next())
					{
						
						String inv_date=resultSet.getString("inv_date");
						String total=df2.format(Double.parseDouble(resultSet.getString("subttl")));
						String vat=df2.format(Double.parseDouble(resultSet.getString("subvat")));
						String Nil_ttl=df2.format(Double.parseDouble(resultSet.getString("Nil_ttl")));
						String Nil_vat=df2.format(Double.parseDouble(resultSet.getString("Nil_vat")));
						String vat_sale=df2.format(Double.parseDouble(resultSet.getString("vat_sale")));
						String vat_amt=df2.format(Double.parseDouble(resultSet.getString("vat_amt")));
						model.addRow(new Object[]{inv_date,total,vat,Nil_ttl,Nil_vat,vat_sale,vat_amt});
						ttlvat=ttlvat+Double.parseDouble(vat);
						ttlamt=ttlamt+Double.parseDouble(total);
					}
					textField.setText(df2.format(ttlamt));
					textField_1.setText(df2.format(ttlvat));
				}
				else if(report_type.equals("MONTH WISE"))
				{
					String sortstr="";
					if(sort.equals("Date"))
					{
						sortstr="order by inv_date";
					}
					else if(sort.equals("Tran Date"))
					{
						sortstr="order by inv_date";
					}
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
		        	    model.removeRow(i);
		        	}
					model.setColumnCount(0);
					String query="SELECT DATE_FORMAT(inv_date,'%M %Y') AS month,SUM(purchase) AS purchase,SUM(vat) AS vat,SUM(purchase+vat) AS total_purchase FROM (SELECT p1.po_date AS inv_date,SUM(p1.sub_ttl_amt) AS purchase,SUM(p1.total_vat) AS vat FROM "+company+".mr_purchase_list_history p1 WHERE p1.po_date BETWEEN '"+date1+"' AND '"+date2+"' AND p1.status!='VOID' GROUP BY p1.po_date UNION ALL SELECT p1.pr_date AS inv_date,-SUM(p1.sub_ttl_amt) AS purchase,-SUM(p1.total_vat) AS vat FROM "+company+".mr_purchase_return_list p1 WHERE p1.pr_date BETWEEN '"+date1+"' AND '"+date2+"' AND p1.status='POSTED' GROUP BY p1.pr_date UNION ALL SELECT p1.inv_date AS inv_date,SUM(p1.net_total) AS purchase,SUM(p1.vat_amt) AS vat FROM "+company+".mr_invoice_history p1 WHERE p1.inv_date BETWEEN '"+date1+"' AND '"+date2+"' AND p1.status!='VOID' GROUP BY p1.inv_date) AS temp GROUP BY YEAR(inv_date),MONTH(inv_date) ORDER BY YEAR(inv_date),MONTH(inv_date)   ";
					//String query="(select  p1.po_date as inv_date, sum(p1.sub_ttl_amt) as subttl, sum(p1.total_vat) as subvat, SUM(CASE WHEN p1.total_vat = 0 THEN p1.grand_total ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN p1.total_vat = 0 THEN p1.total_vat ELSE 0 END) AS Nil_vat, SUM(CASE WHEN p1.total_vat != 0 THEN p1.sub_ttl_amt ELSE 0 END) AS vat_sale, SUM(CASE WHEN p1.total_vat != 0 THEN p1.total_vat ELSE 0 END) AS vat_amt from "+company+".mr_purchase_list_history p1  where p1.po_date between '"+date1+"' and '"+date2+"' and p1.status!='VOID' GROUP BY inv_date ) union (select  p1.pr_date as inv_date, -sum(p1.sub_ttl_amt) as subttl, -sum(p1.total_vat) as subvat, -SUM(CASE WHEN p1.total_vat = 0 THEN p1.grand_total ELSE 0 END) AS Nil_ttl, -SUM(CASE WHEN p1.total_vat = 0 THEN p1.total_vat ELSE 0 END) AS Nil_vat, -SUM(CASE WHEN p1.total_vat != 0 THEN p1.sub_ttl_amt ELSE 0 END) AS vat_sale, -SUM(CASE WHEN p1.total_vat != 0 THEN p1.total_vat ELSE 0 END) AS vat_amt from "+company+".mr_purchase_return_list p1  where p1.pr_date between '"+date1+"' and '"+date2+"' and p1.status='POSTED' GROUP BY inv_date ) union (select  p1.inv_date as inv_date, sum(p1.net_total) as subttl, sum(p1.vat_amt) as subvat, SUM(CASE WHEN p1.vat_amt = 0 THEN p1.net_total ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN p1.vat_amt = 0 THEN p1.vat_amt ELSE 0 END) AS Nil_vat, SUM(CASE WHEN p1.vat_amt != 0 THEN p1.net_total ELSE 0 END) AS vat_sale, SUM(CASE WHEN p1.vat_amt != 0 THEN p1.vat_amt ELSE 0 END) AS vat_amt from "+company+".mr_invoice_history p1  where p1.inv_date between '"+date1+"' and '"+date2+"' and p1.status!='VOID' GROUP BY inv_date ) "+sortstr+" ";
					//String query="select  p1.po_date as inv_date, sum(p1.grand_total) as subttl, sum(p1.total_vat) as subvat, SUM(CASE WHEN p1.total_vat = 0 THEN p1.grand_total ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN p1.total_vat = 0 THEN p1.total_vat ELSE 0 END) AS Nil_vat, SUM(CASE WHEN p1.total_vat != 0 THEN p1.grand_total ELSE 0 END) AS vat_sale, SUM(CASE WHEN p1.total_vat != 0 THEN p1.total_vat ELSE 0 END) AS vat_amt from "+company+".mr_purchase_list_history p1  where p1.po_date between '"+date1+"' and '"+date2+"' and p1.status!='VOID' GROUP BY inv_date "+sortstr+" ";
					model.addColumn("Month");
				    model.addColumn("Purchase"); 
				    model.addColumn("Vat"); 
					model.addColumn("Total Purchase with Vat");
					ResultSet resultSet = statement.executeQuery(query);
					while(resultSet.next())
					{
						
						String inv_date=resultSet.getString("month");
						String total=df2.format(Double.parseDouble(resultSet.getString("purchase")));
						String vat=df2.format(Double.parseDouble(resultSet.getString("vat")));
						String Nil_ttl=df2.format(Double.parseDouble(resultSet.getString("total_purchase")));
						model.addRow(new Object[]{inv_date,total,vat,Nil_ttl});
						ttlvat=ttlvat+Double.parseDouble(vat);
						ttlamt=ttlamt+Double.parseDouble(total);
					}
					textField.setText(df2.format(ttlamt));
					textField_1.setText(df2.format(ttlvat));
				}
				else
				{
					String sortstr="";
					if(sort.equals("Date"))
					{
						sortstr="order by inv_date";
					}
					else if(sort.equals("Tran Date"))
					{
						sortstr="order by inv_date";
					}
					else if(sort.equals("Name"))
					{
						sortstr="order by vendor_name";
					}
					else if(sort.equals("City"))
					{
						sortstr="order by state";
					}
					else
					{
						sortstr="order by invoice";
					}
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
		        	    model.removeRow(i);
		        	}
					model.setColumnCount(0);
					//String query="(select  p1.inv_date as inv_date, sum(p1.net_total) as subttl, sum(p1.vat_amt) as subvat, SUM(CASE WHEN p1.vat_amt = 0 THEN p1.net_total ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN p1.vat_amt = 0 THEN p1.vat_amt ELSE 0 END) AS Nil_vat, SUM(CASE WHEN p1.vat_amt != 0 THEN p1.net_total ELSE 0 END) AS vat_sale, SUM(CASE WHEN p1.vat_amt != 0 THEN p1.vat_amt ELSE 0 END) AS vat_amt, vendor_name as cust_name, invoice_num as invoice, (CASE WHEN vendor_city != '' THEN vendor_city ELSE 'NIL' END) as city, (CASE WHEN vendor_trn != '' THEN vendor_trn ELSE 'NIL' END) as trn from "+company+".mr_invoice_history p1 inner join "+company+".mr_vendor on mr_vendor.vendor_code=p1.supp_code  where p1.inv_date between '"+date1+"' and '"+date2+"' and p1.status!='VOID' GROUP BY invoice_num ) union (select  p1.po_date as inv_date, sum(p1.sub_ttl_amt) as subttl, sum(p1.total_vat) as subvat, SUM(CASE WHEN p1.total_vat = 0 THEN p1.grand_total ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN p1.total_vat = 0 THEN p1.total_vat ELSE 0 END) AS Nil_vat, SUM(CASE WHEN p1.total_vat != 0 THEN p1.sub_ttl_amt ELSE 0 END) AS vat_sale, SUM(CASE WHEN p1.total_vat != 0 THEN p1.total_vat ELSE 0 END) AS vat_amt, vendor_name as cust_name, po_no as invoice, (CASE WHEN vendor_city != '' THEN vendor_city ELSE 'NIL' END) as city, (CASE WHEN vendor_trn != '' THEN vendor_trn ELSE 'NIL' END) as trn from "+company+".mr_purchase_list_history p1 inner join "+company+".mr_vendor on mr_vendor.vendor_code=p1.vendor_id  where p1.po_date between '"+date1+"' and '"+date2+"' and p1.status!='VOID' GROUP BY po_no ) "+sortstr+"  ";
					String query="(select  p1.po_date as inv_date, sum(p1.sub_ttl_amt) as subttl, sum(p1.total_vat) as subvat, SUM(CASE WHEN p1.total_vat = 0 THEN p1.grand_total ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN p1.total_vat = 0 THEN p1.total_vat ELSE 0 END) AS Nil_vat, SUM(CASE WHEN p1.total_vat != 0 THEN p1.grand_total ELSE 0 END) AS vat_sale, SUM(CASE WHEN p1.total_vat != 0 THEN p1.total_vat ELSE 0 END) AS vat_amt, vendor_name as cust_name, po_no as invoice, (CASE WHEN vendor_city != '' THEN vendor_city ELSE 'NIL' END) as city, (CASE WHEN vendor_trn != '' THEN vendor_trn ELSE 'NIL' END) as trn from "+company+".mr_purchase_list_history p1 inner join "+company+".mr_vendor on mr_vendor.vendor_code=p1.vendor_id where p1.po_date between '"+date1+"' and '"+date2+"' and p1.status!='VOID' GROUP BY po_no) union "
							+ "(select  p1.pr_date as inv_date, -sum(p1.sub_ttl_amt) as subttl, -sum(p1.total_vat) as subvat, -SUM(CASE WHEN p1.total_vat = 0 THEN p1.grand_total ELSE 0 END) AS Nil_ttl, -SUM(CASE WHEN p1.total_vat = 0 THEN p1.total_vat ELSE 0 END) AS Nil_vat, -SUM(CASE WHEN p1.total_vat != 0 THEN p1.grand_total ELSE 0 END) AS vat_sale, -SUM(CASE WHEN p1.total_vat != 0 THEN p1.total_vat ELSE 0 END) AS vat_amt, vendor_name as cust_name, pr_no as invoice, (CASE WHEN vendor_city != '' THEN vendor_city ELSE 'NIL' END) as city, (CASE WHEN vendor_trn != '' THEN vendor_trn ELSE 'NIL' END) as trn from "+company+".mr_purchase_return_list p1 inner join "+company+".mr_vendor on mr_vendor.vendor_code=p1.vendor_id where p1.pr_date between '"+date1+"' and '"+date2+"' and p1.status='POSTED' GROUP BY pr_no)"
									+ "union (select  p1.inv_date as inv_date, sum(p1.net_total) as subttl, sum(p1.vat_amt) as subvat, SUM(CASE WHEN p1.vat_amt = 0 THEN p1.net_total ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN p1.vat_amt = 0 THEN p1.vat_amt ELSE 0 END) AS Nil_vat, SUM(CASE WHEN p1.vat_amt != 0 THEN p1.net_total ELSE 0 END) AS vat_sale, SUM(CASE WHEN p1.vat_amt != 0 THEN p1.vat_amt ELSE 0 END) AS vat_amt,vendor_name as cust_name, invoice_num as invoice,(CASE WHEN vendor_city != '' THEN vendor_city ELSE 'NIL' END) as city, (CASE WHEN vendor_trn != '' THEN vendor_trn ELSE 'NIL' END) as trn from "+company+".mr_invoice_history p1 inner join "+company+".mr_vendor on mr_vendor.vendor_code=p1.supp_code where p1.inv_date between '"+date1+"' and '"+date2+"' and p1.status!='VOID' GROUP BY inv_date )  "+sortstr+" ";
					model.addColumn("Invoice Date");
					model.addColumn("PO Number");
					model.addColumn("Vendor Name");
					model.addColumn("City");
					model.addColumn("TRN");
				    model.addColumn("Total PO Amt"); 
				    model.addColumn("Total PO TaxAmt"); 
					model.addColumn("Nil_PO AMT"); 
					model.addColumn("Nil PO Tax");
					model.addColumn("VAT5% PO AMT"); 
					model.addColumn("VAT5% PO Tax"); 
					ResultSet resultSet = statement.executeQuery(query);
					while(resultSet.next())
					{
						String inv_date=resultSet.getString("inv_date");
						String invoice=resultSet.getString("invoice");
						String cust_name=resultSet.getString("cust_name");
						String city=resultSet.getString("city");
						String trn=resultSet.getString("trn");
						String total=df2.format(Double.parseDouble(resultSet.getString("subttl")));
						String vat=df2.format(Double.parseDouble(resultSet.getString("subvat")));
						String Nil_ttl=df2.format(Double.parseDouble(resultSet.getString("Nil_ttl")));
						String Nil_vat=df2.format(Double.parseDouble(resultSet.getString("Nil_vat")));
						String vat_sale=df2.format(Double.parseDouble(resultSet.getString("vat_sale")));
						String vat_amt=df2.format(Double.parseDouble(resultSet.getString("vat_amt")));
						model.addRow(new Object[]{inv_date,invoice,cust_name,city,trn,total,vat,Nil_ttl,Nil_vat,vat_sale,vat_amt});
						ttlvat=ttlvat+Double.parseDouble(vat);
						ttlamt=ttlamt+Double.parseDouble(total);
					}
					textField.setText(df2.format(ttlamt));
					textField_1.setText(df2.format(ttlvat));
				}
			}
			else
			{
				if(report_type.equals("DATE WISE"))
				{
					String sortstr="";
					if(sort.equals("Date"))
					{
						sortstr="order by inv_date";
					}
					else if(sort.equals("Tran Date"))
					{
						sortstr="order by inv_date";
					}
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
		        	    model.removeRow(i);
		        	}
					model.setColumnCount(0);
					String query="(select p1.created_date as inv_date, sum(p1.total) as subttl, sum(p1.vat) as subvat, SUM(CASE WHEN p1.vat = 0 THEN p1.total ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN p1.vat = 0 THEN p1.vat ELSE 0 END) AS Nil_vat, SUM(CASE WHEN p1.vat != 0 THEN p1.total ELSE 0 END) AS vat_sale, SUM(CASE WHEN p1.vat != 0 THEN p1.vat ELSE 0 END) AS vat_amt	 from "+company+".mr_pos_settlement_history p1  where p1.created_date between '"+date1+"' and '"+date2+"' and type!='VOID' GROUP BY p1.created_date)  UNION (select s1.date as inv_date, sum(s1.total_amt-s1.disc) as subttl, sum(s1.total_vat) as subvat, SUM(CASE WHEN s1.total_vat = 0 THEN (s1.total_amt-s1.disc) ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN s1.total_vat = 0 THEN s1.total_vat ELSE 0 END) AS Nil_vat,	 SUM(CASE WHEN s1.total_vat != 0 THEN (s1.total_amt-s1.disc) ELSE 0 END) AS vat_sale, SUM(CASE WHEN s1.total_vat != 0 THEN s1.total_vat ELSE 0 END) AS vat_amt from "+company+".mr_sd_list_history s1 where s1.date between '"+date1+"' and '"+date2+"' and type='sd_invoice' and status='POSTED' GROUP BY s1.date)  "+sortstr+" ";
					ResultSet resultSet = statement.executeQuery(query);
					model.addColumn("Invoice Date"); 
				    model.addColumn("TotSalesAmt"); 
				    model.addColumn("TotTaxAmt"); 
					model.addColumn("Nil_Sales"); 
					model.addColumn("Nil Tax");
					model.addColumn("VAT5%_Sales"); 
					model.addColumn("VAT5%_Tax"); 
					while(resultSet.next())
					{
						String inv_date=resultSet.getString("inv_date");
						String total=df2.format(Double.parseDouble(resultSet.getString("subttl")));
						String vat=df2.format(Double.parseDouble(resultSet.getString("subvat")));
						String Nil_ttl=df2.format(Double.parseDouble(resultSet.getString("Nil_ttl")));
						String Nil_vat=df2.format(Double.parseDouble(resultSet.getString("Nil_vat")));
						String vat_sale=df2.format(Double.parseDouble(resultSet.getString("vat_sale")));
						String vat_amt=df2.format(Double.parseDouble(resultSet.getString("vat_amt")));
						model.addRow(new Object[]{inv_date,total,vat,Nil_ttl,Nil_vat,vat_sale,vat_amt});
						ttlvat=ttlvat+Double.parseDouble(vat);
						ttlamt=ttlamt+Double.parseDouble(total);
						
					}
					textField.setText(df2.format(ttlamt));
					textField_1.setText(df2.format(ttlvat));
					
				}
				else if(report_type.equals("MONTH WISE"))
				{
					String sortstr="";
					if(sort.equals("Date"))
					{
						sortstr="order by inv_date";
					}
					else if(sort.equals("Tran Date"))
					{
						sortstr="order by inv_date";
					}
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
		        	    model.removeRow(i);
		        	}
					model.setColumnCount(0);
					//String query="(select p1.created_date as inv_date, sum(p1.total) as subttl, sum(p1.vat) as subvat, SUM(CASE WHEN p1.vat = 0 THEN p1.total ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN p1.vat = 0 THEN p1.vat ELSE 0 END) AS Nil_vat, SUM(CASE WHEN p1.vat != 0 THEN p1.total ELSE 0 END) AS vat_sale, SUM(CASE WHEN p1.vat != 0 THEN p1.vat ELSE 0 END) AS vat_amt	 from "+company+".mr_pos_settlement_history p1  where p1.created_date between '"+date1+"' and '"+date2+"' and type!='VOID' GROUP BY p1.created_date)  UNION (select s1.date as inv_date, sum(s1.total_amt-s1.disc) as subttl, sum(s1.total_vat) as subvat, SUM(CASE WHEN s1.total_vat = 0 THEN (s1.total_amt-s1.disc) ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN s1.total_vat = 0 THEN s1.total_vat ELSE 0 END) AS Nil_vat,	 SUM(CASE WHEN s1.total_vat != 0 THEN (s1.total_amt-s1.disc) ELSE 0 END) AS vat_sale, SUM(CASE WHEN s1.total_vat != 0 THEN s1.total_vat ELSE 0 END) AS vat_amt from "+company+".mr_sd_list_history s1 where s1.date between '"+date1+"' and '"+date2+"' and type='sd_invoice' and status='POSTED' GROUP BY s1.date)  "+sortstr+" ";
					String query="SELECT DATE_FORMAT(inv_date,'%M %Y') AS month,SUM(sales) AS SalesAmt,SUM(tax) AS TaxAmt,SUM(sales+tax) AS total_sale FROM ((SELECT p1.created_date AS inv_date,SUM(p1.total) AS sales,SUM(p1.vat) AS tax FROM "+company+".mr_pos_settlement_history p1 WHERE p1.created_date BETWEEN '"+date1+"' AND '"+date2+"' AND type!='VOID' GROUP BY p1.created_date) UNION ALL (SELECT s1.date AS inv_date,SUM(s1.total_amt-s1.disc) AS sales,SUM(s1.total_vat) AS tax FROM "+company+".mr_sd_list_history s1 WHERE s1.date BETWEEN '"+date1+"' AND '"+date2+"' AND type='sd_invoice' AND status='POSTED' GROUP BY s1.date)) AS temp GROUP BY YEAR(inv_date),MONTH(inv_date) ORDER BY YEAR(inv_date),MONTH(inv_date)  ";
					ResultSet resultSet = statement.executeQuery(query);
					model.addColumn("Month");
					model.addColumn("Sales Amt");
					model.addColumn("Tax Amt");
					model.addColumn("Total Sale with Vat"); 
					while(resultSet.next())
					{
						String inv_date=resultSet.getString("month");
						String total=df2.format(Double.parseDouble(resultSet.getString("SalesAmt")));
						String vat=df2.format(Double.parseDouble(resultSet.getString("TaxAmt")));
						String Nil_ttl=df2.format(Double.parseDouble(resultSet.getString("total_sale")));
						model.addRow(new Object[]{inv_date,total,vat,Nil_ttl});
						ttlvat=ttlvat+Double.parseDouble(vat);
						ttlamt=ttlamt+Double.parseDouble(total);
						
					}
					textField.setText(df2.format(ttlamt));
					textField_1.setText(df2.format(ttlvat));
				}
				else
				{
					String sortstr="";
					if(sort.equals("Date"))
					{
						sortstr="order by inv_date";
					}
					else if(sort.equals("Tran Date"))
					{
						sortstr="order by inv_date";
					}
					else if(sort.equals("Name"))
					{
						sortstr="order by cust_name";
					}
					else if(sort.equals("City"))
					{
						sortstr="order by city";
					}
					else
					{
						sortstr="order by invoice";
					}
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
		        	    model.removeRow(i);
		        	}
					model.setColumnCount(0);
					model.addColumn("Invoice Date");
					model.addColumn("Receipt Num");
					model.addColumn("Cust Name"); 
					model.addColumn("City");
					model.addColumn("TRN");
				    model.addColumn("TotSalesAmt"); 
				    model.addColumn("TotTaxAmt"); 
					model.addColumn("Nil_Sales"); 
					model.addColumn("Nil Tax");
					model.addColumn("VAT5%_Sales"); 
					model.addColumn("VAT5%_Tax");
					String query1="(select p1.created_date as inv_date, sum(p1.total) as subttl, sum(p1.vat) as subvat, SUM(CASE WHEN p1.vat = 0 THEN p1.total ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN p1.vat = 0 THEN p1.vat ELSE 0 END) AS Nil_vat, SUM(CASE WHEN p1.vat != 0 THEN p1.total ELSE 0 END) AS vat_sale, SUM(CASE WHEN p1.vat != 0 THEN p1.vat ELSE 0 END) AS vat_amt, p1.cust_name, receipt_no as invoice, (CASE WHEN city != '' THEN city ELSE 'NIL' END) as city, (CASE WHEN trn != '' THEN trn ELSE 'NIL' END) as trn	 from "+company+".mr_pos_settlement_history p1 inner join "+company+".mr_customer_master on mr_customer_master.cust_name=p1.cust_name  where p1.created_date between '"+date1+"' and '"+date2+"' and p1.type!='VOID' GROUP BY p1.receipt_no)  UNION (select s1.date as inv_date, sum(s1.total_amt-s1.disc) as subttl, sum(s1.total_vat) as subvat, SUM(CASE WHEN s1.total_vat = 0 THEN (s1.total_amt-s1.disc) ELSE 0 END) AS Nil_ttl, SUM(CASE WHEN s1.total_vat = 0 THEN s1.total_vat ELSE 0 END) AS Nil_vat,	 SUM(CASE WHEN s1.total_vat != 0 THEN (s1.total_amt-s1.disc) ELSE 0 END) AS vat_sale, SUM(CASE WHEN s1.total_vat != 0 THEN s1.total_vat ELSE 0 END) AS vat_amt, s1.cust_name, invoice_num as invoice,(CASE WHEN city != '' THEN city ELSE 'NIL' END) as city, (CASE WHEN trn != '' THEN trn ELSE 'NIL' END) as trn from "+company+".mr_sd_list_history s1 inner join "+company+".mr_customer_master on mr_customer_master.cust_code=s1.cust_id where s1.date between '"+date1+"' and '"+date2+"' and s1.type='sd_invoice' and s1.status='POSTED' GROUP BY s1.invoice_num)  "+sortstr+" ";
					
					ResultSet resultSet1 = statement.executeQuery(query1);
					while(resultSet1.next())
					{
						String inv_date=resultSet1.getString("inv_date");
						String invoice=resultSet1.getString("invoice");
						String cust_name=resultSet1.getString("cust_name");
						String city=resultSet1.getString("city");
						String trn=resultSet1.getString("trn");
						String total=df2.format(Double.parseDouble(resultSet1.getString("subttl")));
						String vat=df2.format(Double.parseDouble(resultSet1.getString("subvat")));
						String Nil_ttl=df2.format(Double.parseDouble(resultSet1.getString("Nil_ttl")));
						String Nil_vat=df2.format(Double.parseDouble(resultSet1.getString("Nil_vat")));
						String vat_sale=df2.format(Double.parseDouble(resultSet1.getString("vat_sale")));
						String vat_amt=df2.format(Double.parseDouble(resultSet1.getString("vat_amt")));
						model.addRow(new Object[]{inv_date,invoice,cust_name,city,trn,total,vat,Nil_ttl,Nil_vat,vat_sale,vat_amt});
						ttlvat=ttlvat+Double.parseDouble(vat);
						ttlamt=ttlamt+Double.parseDouble(total);
					}
					textField.setText(df2.format(ttlamt));
					textField_1.setText(df2.format(ttlvat));
					
				}
			}
			
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		catch(Exception e){
			System.err.println(e);
			JOptionPane.showMessageDialog(frame, e);
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	private void onload(String company, String store_name,Connection con)
	{
		try{
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store where store_id='"+store_name+"' ");
			if(resultSet.next())
			{
				decimalPlaces=Integer.parseInt(resultSet.getString("decimal_count"));
				if(decimalPlaces==2)
				{
					df2=new DecimalFormat("0.00");
				}
				else
				{
					df2=new DecimalFormat("0.000");
				}
			}
		}
		catch(Exception e){
			System.err.println(e);
			JOptionPane.showMessageDialog(frame, e);
		}
	}
}
