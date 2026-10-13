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

import javax.swing.JMenuItem;
import javax.swing.JComboBox;

public class HistoryDepartmentSales {

	public static JFrame frame;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;
	private DefaultTableModel model;
	private JTable table;
	private static DecimalFormat df2;
	private static int decimalPlaces;
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
					HistoryDepartmentSales window = new HistoryDepartmentSales("mr_pro","admin", "S01", null, dbconn.getConnection(),"","");
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
	public HistoryDepartmentSales(String company, String username,  String store,String version,Connection con,String printer,String language) throws ParseException {
		initialize(company,username,store, version,con, printer,language);
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
	private void initialize(final String company,final String username,final String store,final String version, Connection con,final String printer, String language) throws ParseException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.requestFocus();
		
		JLabel lblDateRange = new JLabel("DEPARTMENT SALES SUMMARY");
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("„·Œ’ „»Ì⁄«  «·ﬁ”„");
		}
		lblDateRange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateRange.setBounds(22, 0, 256, 39);
		frame.getContentPane().add(lblDateRange);
		
		JLabel lblFrom = new JLabel("From");
		if(language.equals("Arabic"))
		{
			lblFrom.setText("„‰");
		}
		lblFrom.setBounds(124, 54, 43, 22);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		if(language.equals("Arabic"))
		{
			lblTo.setText("·");
		}
		lblTo.setBounds(330, 54, 27, 22);
		frame.getContentPane().add(lblTo);
		
		Date date = new Date();
		dateChooser = new JDateChooser(date);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(169, 54, 138, 22);
		frame.getContentPane().add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(384, 54, 125, 22);
		
		dateChooser_1.setDate(date);
		frame.getContentPane().add(dateChooser_1);
		
		JLabel lblDateRange_1 = new JLabel("Date Range : ");
		if(language.equals("Arabic"))
		{
			lblDateRange_1.setText("«·‰ÿ«ﬁ «·“„‰Ì :");
		}
		lblDateRange_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateRange_1.setBounds(32, 54, 89, 22);
		frame.getContentPane().add(lblDateRange_1);
		
		//Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		
		JButton btnProceed = new JButton("Proceed");
		if(language.equals("Arabic"))
		{
			btnProceed.setText("Ì «»⁄");
		}
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				String register=comboBox.getSelectedItem().toString();
				get_data(date1,date2,company,register);
			}

			
		});
		btnProceed.setBounds(887, 48, 97, 55);
		frame.getContentPane().add(btnProceed);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 106, 979, 546);
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
		if(language.equals("Arabic"))
		{
			model.addColumn(" «—ÌŒ «·›« Ê—…");
		    model.addColumn("Ì”Ã·");
		    model.addColumn("ﬁ”„");
		    model.addColumn("«·ﬂ„Ì… «·≈Ã„«·Ì…");
		    model.addColumn("≈Ã„«·Ì «·»Ì⁄");
		}
		else
		{
			model.addColumn("Invoice Date");
		    model.addColumn("Register");
		    model.addColumn("Department");
		    model.addColumn("Total Qty");
		    model.addColumn("Total sale");
		}
		
		    
	    scrollPane.setViewportView(table);
	    
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    
	    int colmns=model.getColumnCount();
		for (int i=0; i<colmns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(179);
		  if (i==1) column.setPreferredWidth(100);
		  if (i==2) column.setPreferredWidth(500);
		  if (i==3) column.setPreferredWidth(100);
		  if (i==4) column.setPreferredWidth(100);
		}
		table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
	    
		
		
		JButton btnExportToCsv = new JButton("Export To CSV");
		if(language.equals("Arabic"))
		{
			btnExportToCsv.setText(" ’œÌ— ≈·Ï CSV");
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
		btnExportToCsv.setBounds(501, 8, 131, 25);
		frame.getContentPane().add(btnExportToCsv);
		
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Refresh");
		if(language.equals("Arabic"))
		{
			menuItem.setText("Ì‰⁄‘");
		}
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
			menuItem_1.setText("„Œ—Ã");
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
		
		final Image printer1=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JMenuItem menuItem_2 = new JMenuItem("Print");
		if(language.equals("Arabic"))
		{
			menuItem_2.setText("„ÿ»⁄…");
		}
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=Dbsetup.dbconn.getConnection();
					String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					String register=comboBox.getSelectedItem().toString();
					report_master.Hist_dept_sale.report(register,con,printer,company,	date1,date2, "TT");
					con.close();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frame, e2.getMessage());
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(765, 9, 80, 30);
		menuItem_2.setIcon(new ImageIcon(printer1));
		frame.getContentPane().add(menuItem_2);
		
		JMenuItem mntmPrinta = new JMenuItem("Print A4");
		if(language.equals("Arabic"))
		{
			mntmPrinta.setText("ÿ»«⁄… A4");
		}
		mntmPrinta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=Dbsetup.dbconn.getConnection();
					String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					String register=comboBox.getSelectedItem().toString();
					report_master.Hist_dept_sale.report(register,con,printer,company,	date1,date2, "A4");
					con.close();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frame, e2.getMessage());
				}
			}
		});
		mntmPrinta.setBorder(null);
		mntmPrinta.setBackground(new Color(243, 229, 216));
		mntmPrinta.setBounds(656, 9, 97, 30);
		mntmPrinta.setIcon(new ImageIcon(printer1));
		frame.getContentPane().add(mntmPrinta);
		
		JLabel lblRegister = new JLabel("Register");
		if(language.equals("Arabic"))
		{
			lblRegister.setText("Ì”Ã·");
		}
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRegister.setBounds(529, 57, 80, 16);
		frame.getContentPane().add(lblRegister);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(600, 54, 151, 22);
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
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\historicaldeptsale.csv");

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
	private void get_data(String date1, String date2,String company,String registerlist) {
		try{
			
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();
			String reglist="";
			if(!registerlist.equals("ALL"))
			{
				reglist="  and  reg_num='"+registerlist+"'";
			}
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
        	    model.removeRow(i);
        	}
			String query="SELECT department,sum(H2.total+H2.vat) as saletotal,sum(H2.qty) as saleqty,H1.created_date,H1.time,reg_num FROM "+company+".mr_pos_settlement_history H1 inner join  "+company+".mr_pos_settlement_history_items H2 on H1.reg_num=H2.ref_reg_num and H1.receipt_no=H2.ref_receipt_num inner join "+company+".mr_tmp_item_master T1 on T1.barcode=H2.barcode where H1.created_date between '"+date1+"' and '"+date2+"'  and H1.type!='VOID' "+reglist+" group by T1.department ORDER BY H1.created_date,department  ASC;";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String created_date=resultSet.getString("created_date")+" "+resultSet.getString("time");
				String reg_num=resultSet.getString("reg_num");
				String department=resultSet.getString("department");
				String saletotal=resultSet.getString("saletotal");
				String saleqty=resultSet.getString("saleqty");
				model.addRow(new Object[]{created_date,reg_num,department,df2.format(Double.parseDouble(saleqty)),df2.format(Double.parseDouble(saletotal))});
				
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
			get_regdisp(company);
		}
		catch(Exception e){
			System.err.println(e);
			JOptionPane.showMessageDialog(frame, e);
		}
	}
	private void get_regdisp( String company)
	{
		try{
			comboBox.addItem("ALL");
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_register_setup"; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String code=resultSet.getString("register_number");
                comboBox.addItem(code);
            }
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
}
