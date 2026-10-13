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
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class stock_status {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					stock_status window = new stock_status("mr_pro", "admin","GMC", null,"");
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
	public stock_status(String company, String username,String store,String version,String search) throws JSONException {
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
				get_po_disp( company, store,qtyOHless,qtyOH,qtyOHgreat,comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),textField_1.getText() );
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
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					report_master.stock_sts.report(con,qtyOHless,qtyOH,qtyOHgreat,textField_1.getText(),comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),company);
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
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
		    model.addColumn("Barcode");
		    model.addColumn("Item Name");
		    
		    model.addColumn("Department");
		    model.addColumn("Category");
		    model.addColumn("SP"); 
		    model.addColumn("Cost");
		    model.addColumn("Qty");
		    model.addColumn("Total Value");
		scrollPane.setViewportView(table);
		
		
		
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(35);
			  if (i==1) column.setPreferredWidth(85);
			  if (i==2) column.setPreferredWidth(85);
			  if (i==3) column.setPreferredWidth(275);
			  if (i==4) column.setPreferredWidth(90);
			  if (i==5) column.setPreferredWidth(50);
			  if (i==6) column.setPreferredWidth(30);
			  if (i==7) column.setPreferredWidth(30);
			  if (i==8) column.setPreferredWidth(30);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 908, 116);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		chckbxQtyOhgreat = new JCheckBox("QTY OH > 0");
		chckbxQtyOhgreat.setBounds(430, 3, 102, 25);
		panel_2.add(chckbxQtyOhgreat);
		
		chckbxQtyOhless = new JCheckBox("QTY OH < 0");
		chckbxQtyOhless.setBounds(430, 59, 102, 25);
		panel_2.add(chckbxQtyOhless);
		
		JLabel lblCategory = new JLabel("Department");
		lblCategory.setBounds(12, 15, 73, 16);
		panel_2.add(lblCategory);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(120, 10, 248, 22);
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
		lblSubCategory.setBounds(12, 46, 85, 16);
		panel_2.add(lblSubCategory);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(120, 42, 248, 22);
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
				
				get_po_disp( company,store,qtyOHless,qtyOH,qtyOHgreat,comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),textField_1.getText() );
			}
		});
		btnNewButton.setBounds(744, 9, 121, 96);
		panel_2.add(btnNewButton);
		
		JLabel lblItemName = new JLabel("Item Search");
		lblItemName.setBounds(12, 81, 85, 16);
		panel_2.add(lblItemName);
		
		textField_1 = new JTextField();
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
				
				get_po_disp( company,store,qtyOHless,qtyOH,qtyOHgreat,comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),textField_1.getText() );
			}
		});
		textField_1.setBounds(120, 87, 578, 22);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		chckbxQtyOh = new JCheckBox("QTY OH = 0");
		chckbxQtyOh.setBounds(430, 31, 102, 25);
		panel_2.add(chckbxQtyOh);
		
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
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\stockwise-"+store+".csv");

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
	private void  get_po_disp( String company, String store, String qtyOHless , String qtyOH, String qtyOHgreat, String category, String department,String name)
	{
		String qtyOHlessstr = "";
		String qtyOHstr = "";
		String qtyOHgreatstr = "";
		String categorystr = "";
		String departmentstr = "";
		String itemname = "";
		if(!qtyOHless.equals(""))
		{
			qtyOHlessstr="qty < '0' ";
		}
		if(!qtyOH.equals(""))
		{
			qtyOHstr="qty = '0' ";
		}
		if(!qtyOHgreat.equals(""))
		{
			qtyOHgreatstr="qty > '0' ";
		}
		String qtybased="";
		if(!qtyOHless.equals(""))
		{
			if(!qtyOH.equals(""))
			{
				
				if(!qtyOHgreat.equals(""))
				{
					qtybased="and ("+qtyOHlessstr+" or "+qtyOHstr+" or "+qtyOHgreatstr+" )";
				}
				else
				{
					qtybased="and ("+qtyOHlessstr+" or "+qtyOHstr+" )";
				}
			}
			else
			{
				if(!qtyOHgreat.equals(""))
				{
					qtybased="and ("+qtyOHlessstr+" or "+qtyOHgreatstr+" )";
				}
				else
				{
					qtybased="and ("+qtyOHlessstr+" )";
				}
			}
		}
		else
		{
			if(!qtyOH.equals(""))
			{
				if(!qtyOHgreat.equals(""))
				{
					qtybased="and ("+qtyOHstr+" or "+qtyOHgreatstr+" )";
				}
				else
				{
					qtybased="and ("+qtyOHstr+" )";
				}
			}
			else
			{
				if(!qtyOHgreat.equals(""))
				{
					qtybased="and ("+qtyOHgreatstr+" )";
				}
			}
		}
		
		
		if(!category.equals("ALL"))
		{
			categorystr="and mr_tmp_item_master.category='"+category+"'";
		}
		if(!department.equals("ALL"))
		{
			departmentstr="and mr_tmp_item_master.department='"+department+"'";
		}
		if(!name.equals(""))
		{
			itemname=" and (mr_tmp_item_master.item_name like '%"+name+"%' or mr_tmp_item_master.item_code like '%"+name+"%' or mr_tmp_item_master.barcode='"+name+"') ";
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_tmp_item_master  WHERE unit!=''  "+qtybased+"  "+categorystr+" "+departmentstr+" "+itemname+" group by item_code order by item_code ASC "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				int decimalPlaces = 2;
				double amt=0;
				String query1="SELECT * FROM "+company+".mr_tmp_item_master  WHERE unit!=''  "+qtybased+" "+categorystr+" "+departmentstr+" "+itemname+" group by item_code order by item_code ASC "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String item_code=resultSet1.getString("item_code");
	                String item_name=resultSet1.getString("item_name");
	                String department1=resultSet1.getString("department");
	                String category1=resultSet1.getString("category");
	                String barcode=resultSet1.getString("barcode");
	                
	                String sp=resultSet1.getString("sp");
	                String cost=resultSet1.getString("cost");
	                String qty=resultSet1.getString("qty");
	                
	                double ttl=Float.parseFloat(cost)*Float.parseFloat(qty);
	                amt=amt+ttl;
	                BigDecimal bd = new BigDecimal(ttl);
	        		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
	        		ttl = bd.doubleValue();
	                model.addRow(new Object[]{model.getRowCount()+1,item_code,barcode,item_name,department1,category1,sp,cost,qty,ttl});
	               
				}
				BigDecimal bd = new BigDecimal(amt);
        		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		amt = bd.doubleValue();
				textField.setText(Double.toString(amt));
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Items Found");
	        	for (int i = model.getRowCount() - 1; i >= 0; i--) {
	        	    model.removeRow(i);
	        	}
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}