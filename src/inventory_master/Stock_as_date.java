package inventory_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class Stock_as_date {

	private static JFrame frame;
	public static DefaultTableModel model;
	public static JTable table;
	private JCheckBox chckbxQtyOhgreat;
	private JCheckBox chckbxQtyOhless;
	private String qtyOHless="";
	private String qtyOH="";
	private String qtyOHgreat="";
	private JCheckBox chckbxQtyOh;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JTextField textField;
	private JTextField textField_1;
	private JDateChooser dateChooser;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Stock_as_date window = new Stock_as_date("mr_pro", "admin","GMC", null,"");
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
	public Stock_as_date(String company, String username,String store,String version,String search) throws JSONException {
		initialize(company,username,store, version,search);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company,final String username,final String store,final String version,final String search_name) throws JSONException {
		frame = new JFrame( version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 721);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("STOCK STATUS REPORT");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(780, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String stdate =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				stdate = sdf.format(dateChooser.getDate());
				get_po_disp( company, store,qtyOHless,qtyOH,qtyOHgreat,comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),textField_1.getText(),stdate );
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		final Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JMenuItem menuItem_2 = new JMenuItem("Print");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxQtyOhgreat.isSelected())
				{
					qtyOHgreat="qtyOHg";
					
				}
				else
				{
					qtyOHgreat="";
				}
				if(chckbxQtyOh.isSelected())
				{
					qtyOH="qtyOH";
					
				}
				else
				{
					qtyOH="";
				}
				if(chckbxQtyOhless.isSelected())
				{
					qtyOHless="qtyOHl";
					
				}
				else
				{
					qtyOHless="";
				}
				String stdate =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				stdate = sdf.format(dateChooser.getDate());
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					report_master.stock_as_date.report(con,qtyOHless,qtyOH,qtyOHgreat,textField_1.getText(),comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),stdate,company);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.setCursor(Cursor.getDefaultCursor());
				
				
				//get_po_disp( company,store,qtyOHless,qtyOH,comboBox_2.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString() );
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(697, 7, 80, 48);
		menuItem_2.setIcon(new ImageIcon(printer));
		panel.add(menuItem_2);
		
		final Image doc=new ImageIcon(this.getClass().getResource("/doc.png")).getImage();
		JMenuItem menuItem_3 = new JMenuItem("Export");
		menuItem_3.setBorder(null);
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					export( company, store,qtyOHless,qtyOH,qtyOHgreat,comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString() );

				} catch (Exception e) {
					System.err.println(e);
				}
			}
		});
		menuItem_3.setBackground(new Color(243, 229, 216));
		menuItem_3.setBounds(605, 7, 80, 48);
		menuItem_3.setIcon(new ImageIcon(doc));
		panel.add(menuItem_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 613);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 130, 908, 446);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel_1.add(scrollPane);

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
		    
		    model.addColumn("Sl. No."); 
		    model.addColumn("Item Code"); 
		    model.addColumn("Item Name");
		    model.addColumn("Stock");
		    model.addColumn("Open");
		    model.addColumn("PO");
		    model.addColumn("AD");
		    model.addColumn("SA");
		    model.addColumn("IN");
		    model.addColumn("PR");
		    model.addColumn("Cost");
		    model.addColumn("Qty");
		    model.addColumn("Total Value");
		scrollPane.setViewportView(table);
		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(35);
			  if (i==1) column.setPreferredWidth(65);
			  if (i==2) column.setPreferredWidth(275);
			  if (i==3) column.setPreferredWidth(90);
			  if (i==4) column.setPreferredWidth(90);
			  if (i==5) column.setPreferredWidth(90);
			  if (i==6) column.setPreferredWidth(70);
			  if (i==7) column.setPreferredWidth(70);
			  if (i==8) column.setPreferredWidth(70);
			  if (i==9) column.setPreferredWidth(90);
			  if (i==10) column.setPreferredWidth(90);
			  if (i==11) column.setPreferredWidth(70);
			  if (i==12) column.setPreferredWidth(70);
			  if (i==13) column.setPreferredWidth(70);
			  if (i==14) column.setPreferredWidth(100);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 908, 116);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		chckbxQtyOhgreat = new JCheckBox("QTY OH > 0");
		chckbxQtyOhgreat.setBounds(430, 5, 102, 22);
		panel_2.add(chckbxQtyOhgreat);
		
		chckbxQtyOhless = new JCheckBox("QTY OH < 0");
		chckbxQtyOhless.setBounds(430, 59, 102, 22);
		panel_2.add(chckbxQtyOhless);
		
		JLabel lblCategory = new JLabel("Department");
		lblCategory.setBounds(12, 12, 73, 22);
		panel_2.add(lblCategory);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(120, 12, 248, 22);
		panel_2.add(comboBox);
		comboBox.addItem("ALL");
		try{
			
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_department"; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				comboBox.addItem(resultSet.getString("dept_name"));
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		JLabel lblSubCategory = new JLabel("Category");
		lblSubCategory.setBounds(12, 46, 85, 22);
		panel_2.add(lblSubCategory);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(120, 46, 248, 22);
		panel_2.add(comboBox_1);
		comboBox_1.addItem("ALL");
		try{
			
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_category"; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				comboBox_1.addItem(resultSet.getString("cat_name"));
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String stdate =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				stdate = sdf.format(dateChooser.getDate());
				if(chckbxQtyOhgreat.isSelected())
				{
					qtyOHgreat="qtyOHg";
					
				}
				else
				{
					qtyOHgreat="";
				}
				if(chckbxQtyOh.isSelected())
				{
					qtyOH="qtyOH";
					
				}
				else
				{
					qtyOH="";
				}
				if(chckbxQtyOhless.isSelected())
				{
					qtyOHless="qtyOHl";
					
				}
				else
				{
					qtyOHless="";
				}
				
				get_po_disp( company,store,qtyOHless,qtyOH,qtyOHgreat,comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),textField_1.getText(),stdate );
			}
		});
		btnNewButton.setBounds(744, 9, 121, 96);
		panel_2.add(btnNewButton);
		
		JLabel lblItemName = new JLabel("Item Search");
		lblItemName.setBounds(12, 83, 85, 22);
		panel_2.add(lblItemName);
		
		textField_1 = new JTextField();
//		textField_1.getDocument().addDocumentListener(new DocumentListener() {
//			  public void changedUpdate(DocumentEvent e) {
//			    warn();
//			  }
//			  public void removeUpdate(DocumentEvent e) {
//			    warn();
//			  }
//			  public void insertUpdate(DocumentEvent e) {
//			    warn();
//			  }
//
//			  public void warn() {
//				  if(chckbxQtyOhgreat.isSelected())
//					{
//						qtyOH="qtyOH";
//						
//					}
//					else
//					{
//						qtyOH="";
//					}
//					if(chckbxQtyOhless.isSelected())
//					{
//						qtyOHless="qtyOH";
//						
//					}
//					else
//					{
//						qtyOHless="";
//					}
//					String stdate =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
//					stdate = sdf.format(dateChooser.getDate());
//					get_po_disp( company,store,qtyOHless,qtyOH,qtyOHgreat,comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),textField_1.getText(),stdate );
//			  }
//		});
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxQtyOhgreat.isSelected())
				{
					qtyOH="qtyOH";
					
				}
				else
				{
					qtyOH="";
				}
				if(chckbxQtyOhless.isSelected())
				{
					qtyOHless="qtyOH";
					
				}
				else
				{
					qtyOHless="";
				}
				String stdate =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				stdate = sdf.format(dateChooser.getDate());
				get_po_disp( company,store,qtyOHless,qtyOH,qtyOHgreat,comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),textField_1.getText(),stdate );
			}
		});
		textField_1.setBounds(120, 86, 578, 22);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		chckbxQtyOh = new JCheckBox("QTY OH = 0");
		chckbxQtyOh.setBounds(430, 32, 102, 22);
		panel_2.add(chckbxQtyOh);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(596, 50, 102, 20);
		dateChooser.setDateFormatString("dd-MM-yyyy");
		panel_2.add(dateChooser);
		dateChooser.setDate(new Date());
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(596, 36, 46, 14);
		panel_2.add(lblDate);
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setBounds(673, 589, 87, 16);
		panel_1.add(lblTotalAmount);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setEditable(false);
		textField.setBounds(760, 586, 160, 22);
		panel_1.add(textField);
		textField.setColumns(10);
		
		
		if(chckbxQtyOhgreat.isSelected())
		{
			qtyOHgreat="qtyOHg";
			
		}
		else
		{
			qtyOHgreat="";
		}
		if(chckbxQtyOh.isSelected())
		{
			qtyOH="qtyOH";
			
		}
		else
		{
			qtyOH="";
		}
		if(chckbxQtyOhless.isSelected())
		{
			qtyOHless="qtyOHl";
			
		}
		else
		{
			qtyOHless="";
		}
	}
	private void export( String company, String store, String qtyOHless , String qtyOH, String qtyOHgreat, String sub_category, String category) throws IOException
	{
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\stockasdate-"+store+".csv");

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
	private void  get_po_disp( String company, String store, String qtyOHless , String qtyOH, String qtyOHgreat, String category, String department,String name, String stdate)
	{
		String qtyOHlessstr = "";
		String qtyOHstr = "";
		String qtyOHgreatstr = "";
		String categorystr = "";
		String departmentstr = "";
		String itemname = "";
		if(!qtyOHless.equals(""))
		{
			qtyOHlessstr=" ttl < '0' ";
		}
		if(!qtyOH.equals(""))
		{
			qtyOHstr="ttl = '0' ";
		}
		if(!qtyOHgreat.equals(""))
		{
			qtyOHgreatstr="ttl > '0' ";
		}
		String qtybased="";
		if(!qtyOHless.equals(""))
		{
			if(!qtyOH.equals(""))
			{
				
				if(!qtyOHgreat.equals(""))
				{
					qtybased="HAVING ("+qtyOHlessstr+" or "+qtyOHstr+" or "+qtyOHgreatstr+" )";
				}
				else
				{
					qtybased="HAVING ("+qtyOHlessstr+" or "+qtyOHstr+" )";
				}
			}
			else
			{
				if(!qtyOHgreat.equals(""))
				{
					qtybased="HAVING ("+qtyOHlessstr+" or "+qtyOHgreatstr+" )";
				}
				else
				{
					qtybased="HAVING ("+qtyOHlessstr+" )";
				}
			}
		}
		else
		{
			if(!qtyOH.equals(""))
			{
				if(!qtyOHgreat.equals(""))
				{
					qtybased="HAVING ("+qtyOHstr+" or "+qtyOHgreatstr+" )";
				}
				else
				{
					qtybased="HAVING ("+qtyOHstr+" )";
				}
			}
			else
			{
				if(!qtyOHgreat.equals(""))
				{
					qtybased="HAVING ("+qtyOHgreatstr+" )";
				}
			}
		}
		
		int a=0;
		if(!category.equals("ALL"))
		{
			if(a==0)
			{
				categorystr=" where t.category='"+category+"'";	
				a=1;
			}
			else
			{
				categorystr=" and t.category='"+category+"'";
			}
			
		}
		if(!department.equals("ALL"))
		{
			if(a==0)
			{
				departmentstr=" where t.department='"+department+"'";
				a=1;
			}
			else
			{
				departmentstr=" and t.department='"+department+"'";
			}
			
		}
		if(!name.equals(""))
		{
			if(a==0)
			{
				itemname=" where (t.item_name like '%"+name+"%' or COALESCE(t.itemcode, '') like '%"+name+"%'  ) ";	
				a=1;
			}
			else
			{
				itemname=" and (t.item_name like '%"+name+"%' or COALESCE(t.itemcode, '') like '%"+name+"%'  ) ";
			}
			
		}
		
		
		try{
			String stock_date="";
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement(); 
			//String get_date="SELECT created_date from "+company+".mr_item_master_history where trans_type='STOCK' group by created_date order by created_date desc LIMIT 1";
			
//			String get_date="SELECT MAX(created_date) AS created_date FROM mr_pro.mr_item_master_history WHERE trans_type = 'STOCK';";
//			ResultSet resultSet12 = statement.executeQuery(get_date);
//			if (resultSet12.next()) {
//		        String temp = resultSet12.getString("created_date");
//		        if (temp != null) {         // check for null from DB
//		            stock_date = temp;
//		        }
//		    }
			
			
//			if(resultSet12.next())
//			{
//				stock_date=resultSet12.getString("created_date");
//			}
			String query="";
			if(stock_date.equals(""))
			{
				//query="SELECT *,t1.barcode as bar_code,itemcode,item_name,item_code,t1.cost as cost_ttl ,SUM(CASE WHEN h1.trans_type = 'STOCK' THEN h1.qty*h1.cf ELSE 0 END) AS Stock,SUM(CASE WHEN h1.trans_type = 'OPEN' THEN h1.qty*h1.cf ELSE 0 END) AS OPEN,SUM(CASE WHEN h1.trans_type = 'PO' THEN h1.qty*h1.cf ELSE 0 END) AS PO,SUM(CASE WHEN h1.trans_type = 'AD' THEN h1.qty*h1.cf ELSE 0 END) AS AD,SUM(CASE WHEN h1.trans_type = 'SA' THEN h1.qty*h1.cf ELSE 0 END) AS SA,SUM(CASE WHEN h1.trans_type = 'PR' THEN h1.qty*h1.cf ELSE 0 END) AS PR,SUM(CASE WHEN h1.trans_type = 'IN' THEN h1.qty*h1.cf ELSE 0 END) AS INs,t1.qty,(SUM(CASE WHEN h1.trans_type = 'STOCK' or h1.trans_type = 'OPEN' or h1.trans_type = 'PO' or h1.trans_type = 'AD' THEN h1.qty*h1.cf ELSE 0 END)-SUM(CASE WHEN h1.trans_type = 'SA' or h1.trans_type = 'PR' or h1.trans_type = 'IN' THEN h1.qty*h1.cf ELSE 0 END)) as ttl FROM "+company+".mr_item_master_history h1 join "+company+".mr_tmp_item_master t1 on t1.item_code=h1.itemcode and h1.barcode=t1.barcode where created_date <= '"+stdate+"' "+categorystr+" "+departmentstr+" "+itemname+"  GROUP BY h1.itemcode "+qtybased+" ORDER BY h1.itemcode  ASC";
				//query="SELECT t1.item_code, t1.item_name, t1.item_cost AS cost_ttl,IFNULL(h2.Stock,0)   AS Stock, IFNULL(h2.Opening,0) AS OPEN, IFNULL(h2.PO,0)      AS PO, IFNULL(h2.AD,0)      AS AD,IFNULL(h2.SA,0)      AS SA, IFNULL(h2.PR,0)      AS PR,IFNULL(h2.INs,0)     AS INs,    t1.total_qty,(IFNULL(h2.Stock,0) + IFNULL(h2.Opening,0) + IFNULL(h2.PO,0) + IFNULL(h2.AD,0)) - (IFNULL(h2.SA,0) + IFNULL(h2.PR,0) + IFNULL(h2.INs,0)) AS ttl FROM mr_pro.mr_item_master t1 LEFT JOIN ( SELECT itemcode, SUM(Stock)   AS Stock, SUM(Opening) AS Opening, SUM(PO)      AS PO, SUM(AD)      AS AD, SUM(SA)      AS SA,SUM(PR)      AS PR,SUM(INs)     AS INs  FROM (SELECT itemcode,barcode, SUM(CASE WHEN trans_type = 'STOCK' THEN qty*cf ELSE 0 END) AS Stock,SUM(CASE WHEN trans_type = 'OPEN'  THEN qty*cf ELSE 0 END) AS Opening,SUM(CASE WHEN trans_type = 'PO'    THEN qty*cf ELSE 0 END) AS PO,  SUM(CASE WHEN trans_type = 'AD'    THEN qty*cf ELSE 0 END) AS AD,SUM(CASE WHEN trans_type = 'SA'    THEN qty*cf ELSE 0 END) AS SA,SUM(CASE WHEN trans_type = 'PR'    THEN qty*cf ELSE 0 END) AS PR,SUM(CASE WHEN trans_type = 'IN'    THEN qty*cf ELSE 0 END) AS INs FROM mr_pro.mr_item_master_history WHERE created_date <= '"+stdate+"'  GROUP BY itemcode, barcode,trans_code  ) x GROUP BY itemcode "+qtybased+") h2   ON t1.item_code = h2.itemcode "+categorystr+" "+departmentstr+" "+itemname+"  ORDER BY t1.item_code;";
				query="SELECT t.item_code, t.item_name, t.barcode, t.cost, SUM(CASE WHEN t.type = 'STOCK' THEN t.qty ELSE 0 END) AS Stock, SUM(CASE WHEN t.type = 'OPEN' THEN t.qty ELSE 0 END) AS OPEN, SUM(CASE WHEN t.type = 'PO' THEN t.qty ELSE 0 END) AS PO, SUM(CASE WHEN t.type = 'AD' THEN t.qty ELSE 0 END) AS AD, SUM(CASE WHEN t.type = 'SA' THEN t.qty ELSE 0 END) AS SA, SUM(CASE WHEN t.type = 'PR' THEN t.qty ELSE 0 END) AS PR, SUM(CASE WHEN t.type = 'IN' THEN t.qty ELSE 0 END) AS INs, SUM(CASE WHEN t.type IN ('STOCK' , 'OPEN', 'PO', 'AD') THEN t.qty ELSE 0 END) - SUM(CASE WHEN t.type IN ('SA' , 'PR', 'IN') THEN t.qty ELSE 0 END) AS ttl FROM (SELECT 'PO' AS type, poh.item_code, SUM(poh.qty * poh.cf) AS qty, poh.barcode, poh.item_name, im.cost, department,category FROM mr_pro.mr_purchase_order_history poh JOIN mr_pro.mr_purchase_list_history pol ON poh.ref_po_num = pol.po_no INNER JOIN mr_pro.mr_tmp_item_master im ON im.item_code = poh.item_code AND poh.barcode = im.barcode WHERE poh.created_date <= '"+stdate+"' GROUP BY poh.item_code UNION ALL SELECT 'PR' AS type, pr.item_code, (- 1 * pr.qty * pr.cf) AS qty, pr.barcode, item_name AS item_name, im.cost, department,category FROM mr_pro.mr_purchase_return pr JOIN mr_pro.mr_purchase_return_list prl ON pr.ref_pr_num = prl.pr_no INNER JOIN mr_pro.mr_tmp_item_master im ON im.item_code = pr.item_code AND pr.barcode = im.barcode WHERE pr_date <= '"+stdate+"' GROUP BY pr.item_code UNION ALL SELECT 'SA' AS type, psi.item_code, SUM(psi.qty * psi.cf) AS qty, psi.barcode, item_desc AS item_name, im.cost, department,category FROM mr_pro.mr_pos_settlement_history_items psi JOIN mr_pro.mr_pos_settlement_history ps ON ps.reg_num = psi.ref_reg_num AND ps.receipt_no = psi.ref_receipt_num INNER JOIN mr_pro.mr_tmp_item_master im ON im.item_code = psi.item_code AND psi.barcode = im.barcode WHERE ps.type != 'VOID' AND ps.created_date <= '"+stdate+"' GROUP BY psi.item_code UNION ALL SELECT 'IN' AS type, sdl.item_code, (sdl.qty * sdl.cf) AS qty, sdl.barcode, sdl.item_name, im.cost, department,category FROM mr_pro.mr_sd_lines_history sdl JOIN mr_pro.mr_sd_list_history sdh ON sdh.invoice_num = sdl.ref_inv_num INNER JOIN mr_pro.mr_tmp_item_master im ON im.item_code = sdl.item_code AND sdl.barcode = im.barcode WHERE sdh.created_date <= '"+stdate+"' GROUP BY sdl.item_code UNION ALL SELECT 'AD' AS type, ith.item_code, (ith.qty * ith.cf) AS qty, ith.barcode, ith.item_name, im.cost, department,category FROM mr_pro.mr_inv_trans_history ith JOIN mr_pro.mr_inv_trans_list_history itl ON itl.adj_no = ith.ref_adj_num JOIN mr_pro.mr_tmp_item_master im ON im.item_code = ith.item_code AND ith.barcode = im.barcode WHERE adj_date <= '"+stdate+"' GROUP BY ith.item_code) t "+categorystr+" "+departmentstr+" "+itemname+"  GROUP BY t.item_code "+qtybased+" ORDER BY t.item_code ASC;";
			}
			else
			{
				query="SELECT t1.item_code, t1.item_name, t1.item_cost AS cost_ttl,IFNULL(h2.Stock,0)   AS Stock, IFNULL(h2.Opening,0) AS OPEN, IFNULL(h2.PO,0)      AS PO, IFNULL(h2.AD,0)      AS AD,IFNULL(h2.SA,0)      AS SA, IFNULL(h2.PR,0)      AS PR,IFNULL(h2.INs,0)     AS INs,    t1.total_qty,(IFNULL(h2.Stock,0) + IFNULL(h2.Opening,0) + IFNULL(h2.PO,0) + IFNULL(h2.AD,0)) - (IFNULL(h2.SA,0) + IFNULL(h2.PR,0) + IFNULL(h2.INs,0)) AS ttl FROM mr_pro.mr_item_master t1 LEFT JOIN ( SELECT itemcode, SUM(Stock)   AS Stock, SUM(Opening) AS Opening, SUM(PO)      AS PO, SUM(AD)      AS AD, SUM(SA)      AS SA,SUM(PR)      AS PR,SUM(INs)     AS INs  FROM (SELECT itemcode,barcode, SUM(CASE WHEN trans_type = 'STOCK' THEN qty*cf ELSE 0 END) AS Stock,SUM(CASE WHEN trans_type = 'OPEN'  THEN qty*cf ELSE 0 END) AS Opening,SUM(CASE WHEN trans_type = 'PO'    THEN qty*cf ELSE 0 END) AS PO,  SUM(CASE WHEN trans_type = 'AD'    THEN qty*cf ELSE 0 END) AS AD,SUM(CASE WHEN trans_type = 'SA'    THEN qty*cf ELSE 0 END) AS SA,SUM(CASE WHEN trans_type = 'PR'    THEN qty*cf ELSE 0 END) AS PR,SUM(CASE WHEN trans_type = 'IN'    THEN qty*cf ELSE 0 END) AS INs FROM mr_pro.mr_item_master_history WHERE created_date '"+stock_date+"' and '"+stdate+"'  GROUP BY itemcode, barcode,trans_code ) x GROUP BY itemcode "+qtybased+") h2   ON t1.item_code = h2.itemcode "+categorystr+" "+departmentstr+" "+itemname+"  ORDER BY t1.item_code;";
				//query="SELECT *,itemcode,item_name,item_code,t1.cost as cost_ttl ,SUM(CASE WHEN h1.trans_type = 'STOCK' THEN h1.qty*h1.cf ELSE 0 END) AS Stock,SUM(CASE WHEN h1.trans_type = 'OPEN' THEN h1.qty*h1.cf ELSE 0 END) AS OPEN,SUM(CASE WHEN h1.trans_type = 'PO' THEN h1.qty*h1.cf ELSE 0 END) AS PO,SUM(CASE WHEN h1.trans_type = 'AD' THEN h1.qty*h1.cf ELSE 0 END) AS AD,SUM(CASE WHEN h1.trans_type = 'SA' THEN h1.qty*h1.cf ELSE 0 END) AS SA,SUM(CASE WHEN h1.trans_type = 'PR' THEN h1.qty*h1.cf ELSE 0 END) AS PR,SUM(CASE WHEN h1.trans_type = 'IN' THEN h1.qty*h1.cf ELSE 0 END) AS INs,t1.qty,(SUM(CASE WHEN h1.trans_type = 'STOCK' or h1.trans_type = 'OPEN' or h1.trans_type = 'PO' or h1.trans_type = 'AD' THEN h1.qty*h1.cf ELSE 0 END)-SUM(CASE WHEN h1.trans_type = 'SA' or h1.trans_type = 'PR' or h1.trans_type = 'IN' THEN h1.qty*h1.cf ELSE 0 END)) as ttl FROM "+company+".mr_item_master_history h1 join "+company+".mr_tmp_item_master t1 on t1.item_code=h1.itemcode and h1.barcode=t1.barcode where created_date between '"+stock_date+"' and '"+stdate+"' "+categorystr+" "+departmentstr+" "+itemname+"  GROUP BY h1.itemcode "+qtybased+" ORDER BY h1.itemcode  ASC";
			}
			
			
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			int decimalPlaces = 2;
			double amt=0;
			double amount=0;
			//double closing_stk=0;
			ResultSet resultSet1 = statement.executeQuery(query);
			while(resultSet1.next())
			{
				String item_code=resultSet1.getString("item_code");
                String item_name=resultSet1.getString("item_name");
                //String barcode=resultSet1.getString("bar_code");
                
                String Stock=resultSet1.getString("Stock");
                String OPEN=resultSet1.getString("OPEN");
                String PO=resultSet1.getString("PO");
                String AD=resultSet1.getString("AD");
                String PR=resultSet1.getString("PR");
                String SA=resultSet1.getString("SA");
                String IN=resultSet1.getString("INs");
                
                
//                double ttl_cost=Double.parseDouble(resultSet1.getString("ttl_cost"));
                double qty=Double.parseDouble(resultSet1.getString("ttl"));
                double cost=Double.parseDouble(resultSet1.getString("cost"));
//                if(qty > 0)
//                {
//                	cost=ttl_cost/qty;
//                }
//                else
//                {
//                	cost=ttl_cost;
//                }
                
                amount=qty*cost;
        		
        		amt=amt+amount;
				
				BigDecimal bd = new BigDecimal(qty);
        		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		qty = bd.doubleValue();
        		
        		
        		BigDecimal ba = new BigDecimal(amount);
        		ba = ba.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		amount = ba.doubleValue();
        		
        		BigDecimal be = new BigDecimal(cost);
        		be = be.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		cost = be.doubleValue();
        		
                model.addRow(new Object[]{model.getRowCount()+1,item_code,item_name,Stock,OPEN,PO,AD,SA,IN,PR,cost,qty,amount});
                
			}
			BigDecimal bd = new BigDecimal(amt);
    		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
    		amt = bd.doubleValue();
			textField.setText(Double.toString(amt));
			
//			ResultSet resultSet = statement.executeQuery(query);
//			if(resultSet.next())
//			{
//				
//			}
//			else
//			{
//				JOptionPane.showMessageDialog(frame, "No Items Found");
//	        	for (int i = model.getRowCount() - 1; i >= 0; i--) {
//	        	    model.removeRow(i);
//	        	}
//			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}