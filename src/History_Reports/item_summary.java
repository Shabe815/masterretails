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

import purchase_master.vendor_list;

import javax.swing.JMenuItem;

public class item_summary {

	public static JFrame frame;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;
	private DefaultTableModel model;
	private JTable table;
	private static DecimalFormat df2;
	private static int decimalPlaces;
	public static JTextField textField_2;
	public static  JTextField textField;
	//private String report_query;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					item_summary window = new item_summary("mr_pro","admin", "S01", null, dbconn.getConnection());
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
	public item_summary(String company, String username,  String store,String version,Connection con) throws ParseException {
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
		
		JLabel lblDateRange = new JLabel("ITEM SUMMARY");
		lblDateRange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateRange.setBounds(22, 0, 477, 39);
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
				get_data(date1,date2,company,textField.getText());
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
		
		//model.addColumn("Invoice Date");
	    model.addColumn("Itemcode");
	    model.addColumn("Item Name");
	    model.addColumn("PO Qty");
	    model.addColumn("PO Cost");
	    model.addColumn("PO Value");
	    model.addColumn("Sale Qty");
	    model.addColumn("Sale Price");
	    model.addColumn("Sale Value");
	    model.addColumn("Margin");
	    model.addColumn("Margin%");
	    model.addColumn("QtyOH");
	    model.addColumn("ItemCost");
	    model.addColumn("Total Value");
	    model.addColumn("Vendor Code");
	    model.addColumn("Vendor Name");
		    
	    scrollPane.setViewportView(table);
	    
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    
	    int colmns=model.getColumnCount();
		for (int i=0; i<colmns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(80);
		  if (i==1) column.setPreferredWidth(250);
		  if (i==2) column.setPreferredWidth(80);
		  if (i==3) column.setPreferredWidth(80);
		  if (i==4) column.setPreferredWidth(80);
		  if (i==5) column.setPreferredWidth(100);
		  if (i==6) column.setPreferredWidth(80);
		  if (i==7) column.setPreferredWidth(80);
		  if (i==8) column.setPreferredWidth(100);
		  if (i==9) column.setPreferredWidth(80);
		  if (i==10) column.setPreferredWidth(80);
		  if (i==11) column.setPreferredWidth(80);
		  if (i==12) column.setPreferredWidth(80);
		  if (i==13) column.setPreferredWidth(80);
		  if (i==14) column.setPreferredWidth(100);
		  if (i==15) column.setPreferredWidth(150);
		}
		table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
	    
		
		
		JButton btnExportToCsv = new JButton("Export To CSV");
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
		
		JLabel lblVendor = new JLabel("Vendor");
		lblVendor.setBounds(22, 81, 46, 14);
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
					new vendor_list(company, "item_analysis", version,"",username);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		button.setToolTipText("Customer List");
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
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=Dbsetup.dbconn.getConnection();
					String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					report_master.item_analysis.report(con,date1,date2, textField.getText(),company,textField_2.getText());
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
		 
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		onload(company, store,con);
		
	}
	private void export_as_csv(String company) throws IOException {
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\item_analysis.csv");

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
	private void get_data(String date1, String date2,String company,String vendorid) {
		try{
			
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();
			String vendorstr="";
			if(!vendorid.equals(""))
			{
				vendorstr="and cust_id='"+vendorid+"'";
			}
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
        	    model.removeRow(i);
        	}
			String query="SELECT h1.created_date,itemcode,item_name,SUM(CASE WHEN h1.trans_type = 'PO' THEN h1.qty*h1.cf ELSE 0 END) AS POqty,SUM(CASE WHEN h1.trans_type = 'PO' THEN h1.ttl_cost ELSE 0 END) AS POcost,SUM(CASE WHEN h1.trans_type = 'PR' THEN h1.qty*h1.cf ELSE 0 END) AS PRqty,SUM(CASE WHEN h1.trans_type = 'PR' THEN h1.ttl_cost ELSE 0 END) AS PRcost,SUM(CASE WHEN h1.trans_type = 'AD' THEN h1.qty*h1.cf ELSE 0 END) AS ADqty,SUM(CASE WHEN h1.trans_type = 'AD' THEN h1.ttl_cost ELSE 0 END) AS ADcost,SUM(CASE WHEN h1.trans_type = 'SA' THEN h1.qty*h1.cf ELSE 0 END) AS SAqty,SUM(CASE WHEN h1.trans_type = 'SA' THEN h1.ttl_cost ELSE 0 END) AS SAcost,SUM(CASE WHEN h1.trans_type = 'IN' THEN h1.qty*h1.cf ELSE 0 END) AS INqty,SUM(CASE WHEN h1.trans_type = 'IN' THEN h1.ttl_cost ELSE 0 END) AS INcost,t1.item_cost as item_cost ,t1.total_qty as qtyOh,(select cust_name from "+company+".mr_item_master_history where itemcode=h1.itemcode and cust_name!='' and trans_type = 'PO' group by itemcode order by cust_name asc) as cust_name,(select cust_id from "+company+".mr_item_master_history where itemcode=h1.itemcode and cust_id!='' and trans_type = 'PO' group by itemcode order by cust_id asc) as cust_id FROM "+company+".mr_item_master_history h1 join "+company+".mr_item_master t1 on t1.item_code=h1.itemcode where h1.created_date between '"+date1+"' and '"+date2+"' "+vendorstr+" GROUP BY h1.itemcode,item_code ORDER BY itemcode asc;";
//			String query="SELECT h1.created_date,itemcode,item_name,SUM(CASE WHEN h1.trans_type = 'PO' THEN h1.qty*h1.cf ELSE 0 END) AS POqty,SUM(CASE WHEN h1.trans_type = 'PO' THEN h1.ttl_cost ELSE 0 END) AS POcost,SUM(CASE WHEN h1.trans_type = 'PR' THEN h1.qty*h1.cf ELSE 0 END) AS PRqty,SUM(CASE WHEN h1.trans_type = 'PR' THEN h1.ttl_cost ELSE 0 END) AS PRcost,SUM(CASE WHEN h1.trans_type = 'AD' THEN h1.qty*h1.cf ELSE 0 END) AS ADqty,SUM(CASE WHEN h1.trans_type = 'AD' THEN h1.ttl_cost ELSE 0 END) AS ADcost,SUM(CASE WHEN h1.trans_type = 'SA' THEN h1.qty*h1.cf ELSE 0 END) AS SAqty,SUM(CASE WHEN h1.trans_type = 'SA' THEN h1.ttl_cost ELSE 0 END) AS SAcost,SUM(CASE WHEN h1.trans_type = 'IN' THEN h1.qty*h1.cf ELSE 0 END) AS INqty,SUM(CASE WHEN h1.trans_type = 'IN' THEN h1.ttl_cost ELSE 0 END) AS INcost,t1.item_cost as item_cost ,t1.total_qty as qtyOh,(CASE WHEN h1.trans_type = 'PO' THEN h1.cust_name ELSE '' END) AS cust_name,(CASE WHEN h1.trans_type = 'PO' THEN h1.cust_id ELSE '' END) AS cust_id FROM "+company+".mr_item_master_history h1 join "+company+".mr_item_master t1 on t1.item_code=h1.itemcode where h1.created_date between '"+date1+"' and '"+date2+"' "+vendorstr+" GROUP BY h1.itemcode,item_code ORDER BY itemcode asc;";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String code=resultSet.getString("itemcode");
				String item_name=resultSet.getString("item_name");
				//String created_date=resultSet.getString("created_date");
				String POqty=resultSet.getString("POqty");
				String POcost=resultSet.getString("POcost");
				String PRqty=resultSet.getString("PRqty");
				String PRcost=resultSet.getString("PRcost");
				String ADqty=resultSet.getString("ADqty");
				String ADcost=resultSet.getString("ADcost");
				String SAqty=resultSet.getString("SAqty");
				String SAcost=resultSet.getString("SAcost");
				String INqty=resultSet.getString("INqty");
				String INcost=resultSet.getString("INcost");
				String item_cost=resultSet.getString("item_cost");
				String qtyOh=resultSet.getString("qtyOh");
				String cust_name=resultSet.getString("cust_name");
				String cust_id=resultSet.getString("cust_id");
				if(cust_id==null)
				{
					cust_id="NIL";
				}
				if(cust_name==null)
				{
					cust_name="NIL";
				}
				
				double poqty=Double.parseDouble(POqty)+Double.parseDouble(ADqty)-Double.parseDouble(PRqty);
				double pottlcost=Double.parseDouble(POcost)+Double.parseDouble(ADcost)-Double.parseDouble(PRcost);
				double pocost=0;
				if(pottlcost > 0)
				{
					pocost=pottlcost/poqty;
				}
				
				double saqty=Double.parseDouble(SAqty)+Double.parseDouble(INqty);
				double sattlcost=Double.parseDouble(SAcost)+Double.parseDouble(INcost);
				double sacost=0;
				if(sattlcost > 0)
				{
					sacost=sattlcost/saqty;
				}
				
				double cuurent_value=Double.parseDouble(item_cost)*Double.parseDouble(qtyOh);
				
				double cost=saqty*Double.parseDouble(item_cost);
				
				double margin=sattlcost-cost;
				
				double marin_perc=0;
				if(margin > 0)
				{
					marin_perc=(margin/cost)*100;
				}
				
				
				model.addRow(new Object[]{code,item_name,df2.format(poqty),df2.format(pocost),df2.format(pottlcost),df2.format(saqty),df2.format(sacost),df2.format(sattlcost),margin,marin_perc,qtyOh,item_cost,df2.format(cuurent_value),cust_id,cust_name});
				
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
