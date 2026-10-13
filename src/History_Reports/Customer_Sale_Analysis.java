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
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
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

import Dbsetup.dbconn;

import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.io.FileWriter;
import java.io.IOException;








import org.json.JSONException;

import sales_master.Cust_Chooser;

import javax.swing.JMenuItem;
import javax.swing.JComboBox;

public class Customer_Sale_Analysis {

	public static JFrame frame;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;
	private DefaultTableModel model;
	private JTable table;
	private static DecimalFormat df2;
	private static int decimalPlaces;
	public static JTextField textField_2;
	public static  JTextField textField;
	private JComboBox<String> comboBox;
	//private String report_query;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Customer_Sale_Analysis window = new Customer_Sale_Analysis("mr_pro","admin", "S01", null, dbconn.getConnection(),"");
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
	public Customer_Sale_Analysis(String company, String username,  String store,String version,Connection con, String language) throws ParseException {
		initialize(company,username,store, version,con,language);
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
	 * @param language 
	 * @param vendor_name 
	 * @param vendor_name2 
	 * @throws ParseException 
	 */
	private void initialize(final String company,final String username,final String store,final String version, Connection con, String language) throws ParseException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.requestFocus();
		
		JLabel lblDateRange = new JLabel("CUSTOMER SALE SUMMARY");
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·œ›⁄ «· «—ÌŒÌ ··⁄„·«¡");
		}
		lblDateRange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateRange.setBounds(22, 0, 477, 39);
		frame.getContentPane().add(lblDateRange);
		
		JLabel lblFrom = new JLabel("From");
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·œ›⁄ «· «—ÌŒÌ ··⁄„·«¡");
		}
		lblFrom.setBounds(114, 38, 43, 22);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·œ›⁄ «· «—ÌŒÌ ··⁄„·«¡");
		}
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
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·œ›⁄ «· «—ÌŒÌ ··⁄„·«¡");
		}
		lblDateRange_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateRange_1.setBounds(22, 38, 89, 22);
		frame.getContentPane().add(lblDateRange_1);
		
		//Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		
		JButton btnProceed = new JButton("Proceed");if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·œ›⁄ «· «—ÌŒÌ ··⁄„·«¡");
		}
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				get_data(date1,date2,company,textField.getText(),comboBox.getSelectedItem().toString());
			}

			
		});
		btnProceed.setBounds(887, 48, 97, 55);
		frame.getContentPane().add(btnProceed);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 106, 979, 563);
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
	    
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    
	    int colmns=model.getColumnCount();
		for (int i=0; i<colmns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(100);
		  if (i==1) column.setPreferredWidth(100);
		  if (i==2) column.setPreferredWidth(100);
		  if (i==3) column.setPreferredWidth(100);
		  if (i==4) column.setPreferredWidth(100);
		  if (i==5) column.setPreferredWidth(100);
		  if (i==6) column.setPreferredWidth(100);
		  if (i==7) column.setPreferredWidth(100);
		  if (i==8) column.setPreferredWidth(100);
		  if (i==9) column.setPreferredWidth(250);
		}
		table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
	    
		
		
		JButton btnExportToCsv = new JButton("Export To CSV");
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·œ›⁄ «· «—ÌŒÌ ··⁄„·«¡");
		}
		btnExportToCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					export_as_csv(company);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			
		});
		btnExportToCsv.setBounds(610, 9, 131, 25);
		frame.getContentPane().add(btnExportToCsv);
		
		JLabel lblVendor = new JLabel("Customer");
		lblVendor.setBounds(22, 81, 60, 14);
		frame.getContentPane().add(lblVendor);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(208, 81, 616, 22);
		frame.getContentPane().add(textField_2);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					new Cust_Chooser (company, "item_analysis",username,store,"0","", version,"1");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		button.setToolTipText("Customer List");
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·œ›⁄ «· «—ÌŒÌ ··⁄„·«¡");
		}
		button.setBounds(834, 81, 28, 21);
		button.setIcon(new ImageIcon(serch));
		frame.getContentPane().add(button);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(78, 82, 113, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Refresh");
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·œ›⁄ «· «—ÌŒÌ ··⁄„·«¡");
		}
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_2.setText("");
				for (int i = model.getRowCount() - 1; i >= 0; i--) {
	        	    model.removeRow(i);
	        	}
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(904, 0, 80, 48);
		menuItem.setIcon(new ImageIcon(refresh));
		frame.getContentPane().add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Exit");
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·œ›⁄ «· «—ÌŒÌ ··⁄„·«¡");
		}
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(844, 0, 60, 48);
		menuItem_1.setIcon(new ImageIcon(exit));
		frame.getContentPane().add(menuItem_1);
		
		final Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JMenuItem menuItem_2 = new JMenuItem("Print");
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·œ›⁄ «· «—ÌŒÌ ··⁄„·«¡");
		}
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=Dbsetup.dbconn.getConnection();
					String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					report_master.Cust_Sale_analysis.report(con,date1,date2, textField.getText(),company,textField_2.getText(),comboBox.getSelectedItem().toString());
					con.close();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frame, e2.getMessage());
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(765, 9, 80, 30);
		menuItem_2.setIcon(new ImageIcon(printer));
		frame.getContentPane().add(menuItem_2);
		
		JLabel lblListBy = new JLabel("List By");
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·œ›⁄ «· «—ÌŒÌ ··⁄„·«¡");
		}
		lblListBy.setBounds(521, 41, 56, 16);
		frame.getContentPane().add(lblListBy);
		
		comboBox = new JComboBox<String>();
		
		comboBox.setBounds(573, 38, 113, 22);
		comboBox.addItem("Date Wise");
		comboBox.addItem("Monthly Wise");
		comboBox.addItem("Yearly Wise");
		comboBox.setSelectedIndex(0);
		frame.getContentPane().add(comboBox);
		 
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		onload(company, store,con);
		
	}
	private void export_as_csv(String company) throws IOException {
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	    final String formattedDate = myDateObj.format(myFormatObj); 
		
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\Customer_sale_analysis_"+comboBox.getSelectedItem().toString()+"_"+formattedDate+".csv");

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
	        JOptionPane.showMessageDialog(frame, "Export Completed «ﬂ „· «· ’œÌ—");
		
	}
	private void get_data(String date1, String date2,String company,String vendorid, String list) {
		try{
			
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();
			String vendorstr="";
			if(!vendorid.equals(""))
			{
				vendorstr="and mr_pos_settlement_history.cust_code='"+vendorid+"'";
			}
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
        	    model.removeRow(i);
        	}
			model.setColumnCount(0);
			String query="";
			if(list.equals("Date Wise"))
			{
				query="SELECT mr_pos_settlement_history.created_date as invdate,count(receipt_no) as receiptcount,sum(mr_pos_settlement_history.total) as ttl,sum(mr_pos_settlement_history.vat) as tax,sum(grand_total) as ttlamt,sum(item_cost) as ttlcost,mr_pos_settlement_history.cust_code,mr_pos_settlement_history.cust_name,addr1,addr2,ph1,ph2  FROM "+company+".mr_pos_settlement_history inner join "+company+".mr_pos_settlement_history_items on mr_pos_settlement_history.receipt_no=mr_pos_settlement_history_items.ref_receipt_num and mr_pos_settlement_history.reg_num=mr_pos_settlement_history_items.ref_reg_num inner join "+company+".mr_customer_master on mr_pos_settlement_history.cust_code=mr_customer_master.cust_code where mr_pos_settlement_history.created_date between '"+date1+"' and '"+date2+"' "+vendorstr+" GROUP BY mr_pos_settlement_history.created_date,cust_code ORDER BY mr_pos_settlement_history.created_date asc;";
				model.addColumn("Invoice Date");
				model.addColumn("Invoice Count");
				model.addColumn("Total Invoice");
			    model.addColumn("Total Vat");
			    model.addColumn("Grand Total");
			    model.addColumn("Total Cost");
			    model.addColumn("Margin");
			    model.addColumn("Margin%");
			    model.addColumn("Customer Code");
			    model.addColumn("Customer Name");
			    model.addColumn("Customer Address1");
			    model.addColumn("Customer Address2");
			    model.addColumn("Customer Mobile1");
			    model.addColumn("Customer Mobile2");
			    
			    int colmns=model.getColumnCount();
				for (int i=0; i<colmns; i++) {
				  TableColumn column = table.getColumnModel().getColumn(i);
				  if (i==0) column.setPreferredWidth(100);
				  if (i==1) column.setPreferredWidth(100);
				  if (i==2) column.setPreferredWidth(100);
				  if (i==3) column.setPreferredWidth(100);
				  if (i==4) column.setPreferredWidth(100);
				  if (i==5) column.setPreferredWidth(100);
				  if (i==6) column.setPreferredWidth(100);
				  if (i==7) column.setPreferredWidth(100);
				  if (i==8) column.setPreferredWidth(100);
				  if (i==9) column.setPreferredWidth(250);
				  if (i==10) column.setPreferredWidth(100);
				  if (i==11) column.setPreferredWidth(100);
				  if (i==12) column.setPreferredWidth(100);
				  if (i==13) column.setPreferredWidth(100);
				}
			}
			if(list.equals("Monthly Wise"))
			{
				query="SELECT MONTH(mr_pos_settlement_history.created_date) as invdate,count(receipt_no) as receiptcount,sum(mr_pos_settlement_history.total) as ttl,sum(mr_pos_settlement_history.vat) as tax,sum(grand_total) as ttlamt,sum(item_cost) as ttlcost,mr_pos_settlement_history.cust_code,mr_pos_settlement_history.cust_name,addr1,addr2,ph1,ph2  FROM "+company+".mr_pos_settlement_history inner join "+company+".mr_pos_settlement_history_items on mr_pos_settlement_history.receipt_no=mr_pos_settlement_history_items.ref_receipt_num and mr_pos_settlement_history.reg_num=mr_pos_settlement_history_items.ref_reg_num inner join "+company+".mr_customer_master on mr_pos_settlement_history.cust_code=mr_customer_master.cust_code where mr_pos_settlement_history.created_date between '"+date1+"' and '"+date2+"' "+vendorstr+" GROUP BY MONTH(mr_pos_settlement_history.created_date),cust_code ORDER BY MONTH(mr_pos_settlement_history.created_date) asc;";
				model.addColumn("Invoice Month");
				model.addColumn("Invoice Count");
				model.addColumn("Total Invoice");
			    model.addColumn("Total Vat");
			    model.addColumn("Grand Total");
			    model.addColumn("Total Cost");
			    model.addColumn("Margin");
			    model.addColumn("Margin%");
			    model.addColumn("Customer Code");
			    model.addColumn("Customer Name");
			    model.addColumn("Customer Address1");
			    model.addColumn("Customer Address2");
			    model.addColumn("Customer Mobile1");
			    model.addColumn("Customer Mobile2");
			    
			    
			    int colmns=model.getColumnCount();
				for (int i=0; i<colmns; i++) {
				  TableColumn column = table.getColumnModel().getColumn(i);
				  if (i==0) column.setPreferredWidth(100);
				  if (i==1) column.setPreferredWidth(100);
				  if (i==2) column.setPreferredWidth(100);
				  if (i==3) column.setPreferredWidth(100);
				  if (i==4) column.setPreferredWidth(100);
				  if (i==5) column.setPreferredWidth(100);
				  if (i==6) column.setPreferredWidth(100);
				  if (i==7) column.setPreferredWidth(100);
				  if (i==8) column.setPreferredWidth(100);
				  if (i==9) column.setPreferredWidth(250);
				  if (i==10) column.setPreferredWidth(100);
				  if (i==11) column.setPreferredWidth(100);
				  if (i==12) column.setPreferredWidth(100);
				  if (i==13) column.setPreferredWidth(100);
				}
			}
			if(list.equals("Yearly Wise"))
			{
				query="SELECT YEAR(mr_pos_settlement_history.created_date) as invdate,count(receipt_no) as receiptcount,sum(mr_pos_settlement_history.total) as ttl,sum(mr_pos_settlement_history.vat) as tax,sum(grand_total) as ttlamt,sum(item_cost) as ttlcost,mr_pos_settlement_history.cust_code,mr_pos_settlement_history.cust_name,addr1,addr2,ph1,ph2  FROM "+company+".mr_pos_settlement_history inner join "+company+".mr_pos_settlement_history_items on mr_pos_settlement_history.receipt_no=mr_pos_settlement_history_items.ref_receipt_num and mr_pos_settlement_history.reg_num=mr_pos_settlement_history_items.ref_reg_num inner join "+company+".mr_customer_master on mr_pos_settlement_history.cust_code=mr_customer_master.cust_code where mr_pos_settlement_history.created_date between '"+date1+"' and '"+date2+"' "+vendorstr+" GROUP BY YEAR(mr_pos_settlement_history.created_date),cust_code ORDER BY YEAR(mr_pos_settlement_history.created_date) asc;";
				model.addColumn("Invoice Year");
				model.addColumn("Invoice Count");
				model.addColumn("Total Invoice");
			    model.addColumn("Total Vat");
			    model.addColumn("Grand Total");
			    model.addColumn("Total Cost");
			    model.addColumn("Margin");
			    model.addColumn("Margin%");
			    model.addColumn("Customer Code");
			    model.addColumn("Customer Name");
			    model.addColumn("Customer Address1");
			    model.addColumn("Customer Address2");
			    model.addColumn("Customer Mobile1");
			    model.addColumn("Customer Mobile2");
			    
			    
			    int colmns=model.getColumnCount();
				for (int i=0; i<colmns; i++) {
				  TableColumn column = table.getColumnModel().getColumn(i);
				  if (i==0) column.setPreferredWidth(100);
				  if (i==1) column.setPreferredWidth(100);
				  if (i==2) column.setPreferredWidth(100);
				  if (i==3) column.setPreferredWidth(100);
				  if (i==4) column.setPreferredWidth(100);
				  if (i==5) column.setPreferredWidth(100);
				  if (i==6) column.setPreferredWidth(100);
				  if (i==7) column.setPreferredWidth(100);
				  if (i==8) column.setPreferredWidth(100);
				  if (i==9) column.setPreferredWidth(250);
				  if (i==10) column.setPreferredWidth(100);
				  if (i==11) column.setPreferredWidth(100);
				  if (i==12) column.setPreferredWidth(100);
				  if (i==13) column.setPreferredWidth(100);
				}
			}
				
			
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String created_date=resultSet.getString("invdate");
				String receiptcount=resultSet.getString("receiptcount");
				String ttl=resultSet.getString("ttl");
				String tax=resultSet.getString("tax");
				String ttlamt=resultSet.getString("ttlamt");
				String cust_code=resultSet.getString("cust_code");
				String cust_name=resultSet.getString("cust_name");
				String ttlcost=resultSet.getString("ttlcost");
				String addr1=resultSet.getString("addr1");
				String addr2=resultSet.getString("addr2");
				String ph1=resultSet.getString("ph1");
				String ph2=resultSet.getString("ph2");
				
				double margin=Double.parseDouble(ttlamt)-Double.parseDouble(ttlcost);
				
				double marin_perc=0;
				if(margin > 0)
				{
					marin_perc=(margin/Double.parseDouble(ttlamt))*100;
				}
				
				model.addRow(new Object[]{created_date,receiptcount,df2.format(Double.parseDouble(ttl)),df2.format(Double.parseDouble(tax)),df2.format(Double.parseDouble(ttlamt)),df2.format(Double.parseDouble(ttlcost)),df2.format(margin),df2.format(marin_perc),cust_code,cust_name,addr1,addr2,ph1,ph2});//df2.format(ttl),df2.format(tax),df2.format(ttlamt)
				
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
