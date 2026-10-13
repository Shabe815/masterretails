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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import javax.swing.JTextField;

public class item_kit_item {

	private static JFrame frame;
	public static DefaultTableModel model;
	public static JTable table;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	private JComboBox<String> comboBox_3;
	private JTextField textField;
	private JTextField textField_1;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					item_kit_item window = new item_kit_item("mr_pro", "admin","GMC", null);
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
	public item_kit_item(String company, String username,String store,String version) throws JSONException {
		initialize(company,username,store, version);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company,final String username,final String store,final String version) throws JSONException {
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
		
		
		JLabel lblItemMasterList = new JLabel("ITEMS WITH KIT ITEMS");
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
				get_po_disp( company, store,comboBox.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),comboBox_2.getSelectedItem().toString(),comboBox_3.getSelectedItem().toString(),textField_1.getText() );
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
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					report_master.kit_items.report(con,comboBox.getSelectedItem().toString(),comboBox_2.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),comboBox_3.getSelectedItem().toString(),textField_1.getText(),company );
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
					
					export( company, store,comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString() );

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
		
		scrollPane = new JScrollPane();
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
		    
		    model.addColumn("Sl.No."); 
		    model.addColumn("Parent Item Code"); 
		    model.addColumn("Parent Item Name");
		    
		    model.addColumn("Child Item code");
		    model.addColumn("Child Item Name");
		    model.addColumn("Department");
		    model.addColumn("SP"); 
		    model.addColumn("Cost");
		    model.addColumn("Qty");
		    model.addColumn("Total Value");
		scrollPane.setViewportView(table);
		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int colmns=model.getColumnCount();
		for (int i=0; i<colmns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(35);
			  if (i==1) column.setPreferredWidth(100);
			  if (i==2) column.setPreferredWidth(200);
			  if (i==3) column.setPreferredWidth(100);
			  if (i==4) column.setPreferredWidth(200);
			  if (i==5) column.setPreferredWidth(70);
			  if (i==6) column.setPreferredWidth(70);
			  if (i==7) column.setPreferredWidth(70);
			  if (i==8) column.setPreferredWidth(70);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 908, 116);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
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
				
				get_po_disp( company, store,comboBox.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),comboBox_2.getSelectedItem().toString(),comboBox_3.getSelectedItem().toString(),textField_1.getText() );
			}
		});
		btnNewButton.setBounds(744, 9, 121, 96);
		panel_2.add(btnNewButton);
		
		JLabel lblItemName = new JLabel("Item Search");
		lblItemName.setBounds(12, 83, 85, 22);
		panel_2.add(lblItemName);
		
		textField_1 = new JTextField();
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }

			  public void warn() {
				  
				  get_po_disp( company, store,comboBox.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),comboBox_2.getSelectedItem().toString(),comboBox_3.getSelectedItem().toString(),textField_1.getText() );
			  }
		});
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( company, store,comboBox.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),comboBox_2.getSelectedItem().toString(),comboBox_3.getSelectedItem().toString(),textField_1.getText() );
			}
		});
		textField_1.setBounds(120, 86, 614, 22);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(486, 12, 248, 22);
		panel_2.add(comboBox_2);
		comboBox_2.addItem("ALL");
		try{
			
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_sub_department group by sub_dept_name"; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				comboBox_2.addItem(resultSet.getString("sub_dept_name"));
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		JLabel lblSubDepartment = new JLabel("Sub Department");
		lblSubDepartment.setBounds(378, 12, 110, 22);
		panel_2.add(lblSubDepartment);
		
		JLabel lblSubCategory_1 = new JLabel("Sub Category");
		lblSubCategory_1.setBounds(378, 46, 110, 22);
		panel_2.add(lblSubCategory_1);
		
		comboBox_3 = new JComboBox<String>();
		comboBox_3.setBounds(486, 46, 248, 22);
		panel_2.add(comboBox_3);
		comboBox_3.addItem("ALL");
		try{
			
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_subcategory group by sub_cat_name"; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				comboBox_3.addItem(resultSet.getString("sub_cat_name"));
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setBounds(673, 589, 87, 16);
		panel_1.add(lblTotalAmount);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setEditable(false);
		textField.setBounds(760, 586, 160, 22);
		panel_1.add(textField);
		textField.setColumns(10);	
	}
	private void export( String company, String store, String sub_category, String category) throws IOException
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
	private void  get_po_disp( String company, String store, String department , String sub_department, String category, String sub_category,String name)
	{
		try
		{
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			String dept_str="";
			if(!department.equals("ALL"))
			{
				dept_str=" and department='"+department+"'";
			}
			String sub_dept_str="";
			if(!sub_department.equals("ALL"))
			{
				sub_dept_str=" and  sub_dept='"+sub_department+"'";
			}
			String cat_str="";
			if(!category.equals("ALL"))
			{
				cat_str=" and  category='"+category+"'";
			}
			String sub_cat_str="";
			if(!sub_category.equals("ALL"))
			{
				sub_cat_str=" and  sub_cat='"+sub_category+"'";
			}
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement(); 
			String get_item="SELECT * FROM "+company+".mr_tmp_item_master inner join  "+company+".mr_kit_items on mr_kit_items.parent_itemcode=item_code where (child_itemname like '%"+name+"%' or parent_itemname like '%"+name+"%' )  "+dept_str+" "+sub_dept_str+" "+cat_str+"  "+sub_cat_str+" ";
			ResultSet resultSet12 = statement.executeQuery(get_item);
			if(resultSet12.next())
			{
				ResultSet resultSet1 = statement.executeQuery(get_item);
				while(resultSet1.next())
				{
					double ttl=Double.parseDouble(resultSet1.getString("cost"))+Double.parseDouble(resultSet1.getString("qty"));
					BigDecimal be = new BigDecimal(ttl);
	        		be = be.setScale(2, BigDecimal.ROUND_HALF_UP);
	        		ttl = be.doubleValue();
					model.addRow(new Object[]{model.getRowCount()+1,resultSet1.getString("parent_itemcode"),resultSet1.getString("parent_itemname"),resultSet1.getString("child_itemcode"),resultSet1.getString("child_itemname"),resultSet1.getString("department"),resultSet1.getString("sp"),resultSet1.getString("cost"),resultSet1.getString("qty"),ttl});
				}
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