package sales_master;

import inventory_master.item_list_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import com.toedter.calendar.JDateChooser;

import java.awt.Label;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JComboBox;

public class Cust_item_price {

	static JFrame frame;
	private static DefaultTableModel model;
	private static JTable table;
	public static JTextField textField;
	public static JMenuItem menuItem;
	public static JTextField textField_1;
	private JComboBox<String> comboBox;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					Cust_item_price window = new Cust_item_price("mr_pro","A069","Ak Mart Hypermarket L.L.C ","");
					Cust_item_price.frame.setVisible(true);
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
	public Cust_item_price(String db_name,String Cust_code, String Cust_name,String language) throws JSONException {
		initialize(db_name,Cust_code,Cust_name, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param page 
	 */
	private void initialize(final String db_name,final String Cust_code,final String Cust_name,final String language) throws JSONException {
		
		frame = new JFrame();
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 696);
		
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		//frame.setAlwaysOnTop(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		final Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JMenuItem menuItem_3 = new JMenuItem("Print");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				try {
					report_master.cust_price_list.report(con, date1, date2,textField_1.getText(),textField.getText(),comboBox.getSelectedItem().toString(),Cust_code,Cust_name,db_name);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		menuItem_3.setBorder(null);
		menuItem_3.setIcon(new ImageIcon(printer));
		menuItem_3.setBackground(new Color(243, 229, 216));
		menuItem_3.setBounds(701, 7, 80, 48);
		panel.add(menuItem_3);
		
		JLabel lblItemMasterList = new JLabel("Customer Item Price List");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		menuItem = new JMenuItem("Exit");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
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
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name, Cust_code,"",comboBox.getSelectedItem().toString(),"","");
				textField.setText("");
				textField_1.setText("");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 589);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 75, 908, 501);
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
		    
		    model.addColumn("Date");
		    model.addColumn("Barcode"); 
		    model.addColumn("Item Code"); 
		    model.addColumn("Item Name"); 
		    model.addColumn("Invoice Num");
		    model.addColumn("Unit");
		    model.addColumn("Price");
		scrollPane.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(100);
			  if (i==1) column.setPreferredWidth(100);
			  if (i==2) column.setPreferredWidth(100);
			  if (i==3) column.setPreferredWidth(250);
			  if (i==4) column.setPreferredWidth(150);
			  if (i==5) column.setPreferredWidth(100);
			  if (i==6) column.setPreferredWidth(100);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 908, 60);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(214, 4, 380, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				get_po_disp( db_name,Cust_code,textField_1.getText(),comboBox.getSelectedItem().toString(),date1,date2);
			}
		});
		btnSearch.setBounds(653, 2, 97, 51);
		panel_2.add(btnSearch);
		
		Date date = new Date();
		
		dateChooser = new JDateChooser();
		dateChooser = new JDateChooser(date);
		dateChooser.setBounds(91, 31, 116, 22);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		panel_2.add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1 = new JDateChooser(date);
		dateChooser_1.setBounds(248, 33, 116, 22);
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		panel_2.add(dateChooser_1);
		
		Label label = new Label("From");
		label.setBounds(10, 28, 70, 24);
		panel_2.add(label);
		
		Label label_1 = new Label("To");
		label_1.setBounds(214, 33, 28, 22);
		panel_2.add(label_1);
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setBounds(12, 6, 70, 16);
		panel_2.add(lblItemName);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(91, 5, 116, 22);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Item Code List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					Connection con=Dbsetup.dbconn.getConnection();
					new item_list_master(db_name,db_name, "Item_price", "", "",2,con,"",language);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					
					
				} catch (JSONException e) {
//					frame.setAlwaysOnTop(false);
//					JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
//					frame.setAlwaysOnTop(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(606, 4, 35, 21);
		panel_2.add(btnNewButton);
		
		JLabel lblSortBy = new JLabel("Sort by");
		lblSortBy.setBounds(819, 7, 56, 16);
		panel_2.add(lblSortBy);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("Date");
		comboBox.addItem("Price");
		comboBox.addItem("Name");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getItemCount()>0)
				{
					if(textField_1.getText().equals(""))
					{
						get_po_disp( db_name, Cust_code,"",comboBox.getSelectedItem().toString(),"","");
					}
					else
					{
						String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
						String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
						get_po_disp( db_name,Cust_code,textField_1.getText(),comboBox.getSelectedItem().toString(),date1,date2);
					}
					
				}
				
			}
		});
		comboBox.setBounds(787, 30, 109, 22);
		panel_2.add(comboBox);
		
		JButton btnExportToCsv = new JButton("Export To CSV");
		btnExportToCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				try {
					export_as_csv( db_name,Cust_code,textField_1.getText(),comboBox.getSelectedItem().toString(),date1,date2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		});
		btnExportToCsv.setBounds(435, 22, 131, 25);
		panel.add(btnExportToCsv);
		 
        get_po_disp( db_name,Cust_code,"",comboBox.getSelectedItem().toString(),"","");
        
	}
	public static void  export_as_csv( String company, String cust_code, String item_code, String sort,String date1,String date2) throws IOException
	{
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\pricelist for-"+cust_code+" from "+date1+" to "+date2+".csv");

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
	public static void  get_po_disp( String company, String cust_code, String item_code, String sort,String date1,String date2)
	{
		String sort_str = "";
		if(sort.equals("Date"))
		{
			sort_str=" order by created_date";
		}
		else if(sort.equals("Name"))
		{
			sort_str=" order by item_name";
		}
		else
		{
			sort_str=" order by "+sort+"";
		}
		String item_code_str = "";
		if(!item_code.equals(""))
		{
			item_code_str="and ref_itemcode='"+item_code+"' ";
		}
		String date_str = "";
		if(!date1.equals("") && !date2.equals(""))
		{
			date_str="and created_date between '"+date1+"' and '"+date2+"' ";
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_customer_items inner join "+company+".mr_tmp_item_master on "+company+".mr_customer_items.ref_itemcode="+company+".mr_tmp_item_master.item_code where status='0' and ref_cust_id='"+cust_code+"' "+date_str+" "+item_code_str+" group by created_date, ref_itemcode "+sort_str+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+company+".mr_customer_items inner join "+company+".mr_tmp_item_master on "+company+".mr_customer_items.ref_itemcode="+company+".mr_tmp_item_master.item_code where status='0' and ref_cust_id='"+cust_code+"' "+date_str+" "+item_code_str+" group by created_date, ref_itemcode "+sort_str+" "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String date=resultSet1.getString("created_date");
					String barcode=resultSet1.getString("barcode");
					String itemcode=resultSet1.getString("ref_itemcode");
					String item_name =resultSet1.getString("item_name");
					String invoice_num=resultSet1.getString("invoice_num");
					String unit=resultSet1.getString("unit");
					String price=resultSet1.getString("price");
					
	                model.addRow(new Object[]{date,barcode,itemcode,item_name,invoice_num,unit,price});
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Customer Price List Found");
	        	for (int i = model.getRowCount() - 1; i >= 0; i--) {
	        	    model.removeRow(i);
	        	}
			}
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
