package History_Reports;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import Dbsetup.dbconn;

import com.toedter.calendar.JDateChooser;

public class LoginControlReport {

	public static JFrame frame;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;
	private DefaultTableModel model;
	private JTable table;
	public static JTextField textField_2;
	public static  JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginControlReport window = new LoginControlReport("mr_pro","admin", "S01", null, dbconn.getConnection(),"");
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
	public LoginControlReport(String company, String username,  String store,String version,Connection con, String language) throws ParseException {
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
		
		JLabel lblDateRange = new JLabel("LOGIN CONTROL REPORT");
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
				get_data(date1,date2,company,textField_2.getText());
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
		btnExportToCsv.setBounds(693, 9, 131, 25);
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
					
					new system_setups.user_list (company, "Login",version);
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
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·œ›⁄ «· «—ÌŒÌ ··⁄„·«¡");
		}
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("«·œ›⁄ «· «—ÌŒÌ ··⁄„·«¡");
		}
		 
		
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
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\LoginReport_"+formattedDate+".csv");

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
	private void get_data(String date1, String date2,String company,String vendorid) {
		try{
			
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();
			String vendorstr="";
			if(!vendorid.equals(""))
			{
				vendorstr="and mr_loggin_controls.username='"+vendorid+"'";
			}
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
        	    model.removeRow(i);
        	}
			model.setColumnCount(0);
			String query="SELECT *  FROM "+company+".mr_loggin_controls where created_date between '"+date1+"' and '"+date2+"' "+vendorstr+";";
			model.addColumn("User Name");
			model.addColumn("Device Name");
			model.addColumn("Login");
		    model.addColumn("Logout");
		    model.addColumn("Status");
		    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//		    int colmns=model.getColumnCount();
//			for (int i=0; i<colmns; i++) {
//			  TableColumn column = table.getColumnModel().getColumn(i);
//			  if (i==0) column.setPreferredWidth(100);
//			  if (i==1) column.setPreferredWidth(100);
//			  if (i==2) column.setPreferredWidth(100);
//			  if (i==3) column.setPreferredWidth(100);
//			  if (i==4) column.setPreferredWidth(100);
//			}
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String username=resultSet.getString("username");
				String device_name=resultSet.getString("device_name");
				String created_date=resultSet.getString("created_date");
				String modified_date=resultSet.getString("modified_date");
				String status=resultSet.getString("status");
				model.addRow(new Object[]{username,device_name,created_date,modified_date,status});
				
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
		
	}
}

