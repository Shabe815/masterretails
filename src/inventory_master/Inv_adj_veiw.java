package inventory_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import com.toedter.calendar.JDateChooser;

public class Inv_adj_veiw {

	private static JFrame frame;
	private JTextField textField_2;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField;
	private static DefaultTableModel model;
	public static JTable table;
	private JDateChooser dateChooser ;
	private JLabel lblNewLabel ;
	private JLabel label_1;
	private JMenuItem menuItem_3;
	private JScrollPane scrollPane;
	//private String price="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Inv_adj_veiw window = new Inv_adj_veiw("mr_pro","admin","AD-19-01-000003","S01", "","" );
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws JSONException 
	 */
	public Inv_adj_veiw(String company, String username, String po_num, String store_name,String version,String pagetype) throws JSONException{
		initialize(company,username,po_num, store_name, version,pagetype);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param company 
	 * @param pagetype 
	 * @param host_name 
	 * @throws JSONException 
	 */
	private void initialize(final String company,final String username,String po_num, final String store_name,final String version, final String pagetype) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(10, 10, 975, 768);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 968, 38);
		frame.getContentPane().add(panel);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		JLabel lblPurchaseOrder = new JLabel("Inventory Transactions");
		lblPurchaseOrder.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPurchaseOrder.setBounds(12, 6, 228, 30);
		panel.add(lblPurchaseOrder);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rows=model.getRowCount();
				if(rows>0)
				{
					int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Exit","",JOptionPane.YES_NO_OPTION);
			        // 0=yes, 1=no, 2=cancel
			        if(cofrm_bx==0)
			        {
			        	frame.setVisible(false);
			        }
			        
				}
				else
				{
					frame.setVisible(false);
					
				}
				
			}
		});
		menuItem.setBorder(null);
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(908, 6, 60, 30);
		panel.add(menuItem);
		
		menuItem_3 = new JMenuItem("Print");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					report_master.inv_adj_posted.report(textField_2.getText(),con, pagetype,company);
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				} 
			}
		});
		menuItem_3.setBorder(null);
		menuItem_3.setIcon(new ImageIcon(printer));
		menuItem_3.setBackground(new Color(243, 229, 216));
		menuItem_3.setBounds(827, 6, 80, 30);
		panel.add(menuItem_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setBounds(0, 39, 969, 694);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(243, 229, 216));
		panel_2.setBounds(0, 0, 281, 84);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 9, 70, 16);
		panel_2.add(lblDate);
		
		Calendar dateAndTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		lblNewLabel = new JLabel(sdf.format(dateAndTime.getTime()));
		lblNewLabel.setBounds(90, 9, 188, 16);
		panel_2.add(lblNewLabel);
		
		JLabel lblPoDate = new JLabel("Order Date");
		lblPoDate.setBounds(10, 34, 70, 16);
		panel_2.add(lblPoDate);
		
		JLabel lblPoNo = new JLabel("Order No.");
		lblPoNo.setBounds(10, 59, 70, 16);
		panel_2.add(lblPoNo);
		
		textField_2 = new JTextField("NEW");
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(90, 59, 188, 16);
		panel_2.add(textField_2);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(90, 34, 188, 16);
		//dateChooser.setMinSelectableDate(new Date());
		panel_2.add(dateChooser);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(243, 229, 216));
		panel_3.setLayout(null);
		panel_3.setBounds(282, 0, 357, 84);
		panel_1.add(panel_3);
		
		JLabel lblTotalAmountOf = new JLabel("Enter Total Amount of invoice");
		lblTotalAmountOf.setBounds(12, 59, 171, 16);
		panel_3.add(lblTotalAmountOf);
		
		JLabel lblInvoiceNo = new JLabel("Document No.");
		lblInvoiceNo.setBounds(12, 9, 100, 16);
		panel_3.add(lblInvoiceNo);
		
		JLabel lblNoOfItems = new JLabel("No. of Items in Doc");
		lblNoOfItems.setBounds(12, 34, 140, 16);
		panel_3.add(lblNoOfItems);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(195, 34, 131, 16);
		panel_3.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(195, 9, 131, 16);
		panel_3.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(195, 59, 131, 16);
		panel_3.add(textField_7);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(243, 229, 216));
		panel_4.setLayout(null);
		panel_4.setBounds(638, 0, 319, 84);
		panel_1.add(panel_4);
		
		new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		
		label_1 = new JLabel(username);
		label_1.setBounds(87, 13, 188, 16);
		panel_4.add(label_1);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(0, 13, 70, 16);
		panel_4.add(lblUserName);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.setBounds(12, 88, 933, 538);
		panel_1.add(scrollPane);
		
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
				if(column==13)
				{
					return true;
				}
		       return false;
		    }
			
		};
		table = new JTable(model);
	    table.setFont(new Font("Serif", Font.BOLD, 15));
		table.setRowHeight(20);
		table.setEnabled(true);
	    model.addColumn("S.NO"); 
	    model.addColumn("Barcode"); 
		model.addColumn("Item Code"); 
		model.addColumn("Item Details"); 
		model.addColumn("QtyOH"); 
		model.addColumn("Unit"); 
		model.addColumn("cf"); 
		model.addColumn("Unit Cost"); 
		model.addColumn("Qty");
		scrollPane.setViewportView(table);
		
		table.setFocusable(false);
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int colmns=model.getColumnCount();
		for (int i=0; i<colmns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(35);
		  if (i==1) column.setPreferredWidth(110);
		  if (i==2) column.setPreferredWidth(75);
		  if (i==3) column.setPreferredWidth(170);
		  if (i==4) column.setPreferredWidth(30);
		  if (i==5) column.setPreferredWidth(50);
		  if (i==6) column.setPreferredWidth(30);
		  if (i==7) column.setPreferredWidth(70);
		  if (i==8) column.setPreferredWidth(75);
		  if (i==9) column.setPreferredWidth(80);
		}
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(709, 626, 236, 42);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 10, 70, 22);
		panel_5.add(lblTotal);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setText("0.0");
		textField.setEditable(false);
		textField.setBounds(100, 10, 116, 22);
		panel_5.add(textField);
		textField.setColumns(10);
		
		
		new ImageIcon(this.getClass().getResource("/del.png")).getImage();
		
		new DecimalFormat("#");
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 61, 957, 2);
		frame.getContentPane().add(separator);
		
		if(!po_num.equals(""))
		{
			try{
				
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				String query="SELECT * FROM "+company+".mr_inv_trans_list_history where adj_no ='"+po_num+"' "; 
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next())
				{
					lblNewLabel.setText(resultSet.getString("com_date"));
					java.util.Date date =null;
					if(resultSet.getString("adj_date")!="")
					{
						date = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("adj_date"));
					}
					dateChooser.setDate(date);
					label_1.setText(resultSet.getString("user_id"));
					textField_2.setText(resultSet.getString("adj_no"));
					textField_6.setText(resultSet.getString("invo_nu"));
					textField_5.setText(resultSet.getString("ttl_items"));
					textField_7.setText(resultSet.getString("ttl_price"));
					
					textField_2.setEditable(false);
		        	//textField_20.setEditable(true);
		        	textField_6.setEditable(false);
		        	textField_5.setEditable(false);
		        	textField_7.setEditable(false);
		        	dateChooser.setEnabled(false);
		        	scrollPane.setEnabled(true);
		        	table.setEnabled(true);
		        	get_item_dt(company, po_num);
				}
				con.close();
			}
			catch(Exception e){
				JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
			}
		}
	}
	
	
	public void validate_barcode_unit(String company,  String itemcode, String unitname) {
	try{
		String barcode = null;
		Connection con=Dbsetup.dbconn.getConnection();
		Statement statement=con.createStatement();  
		String query="SELECT * FROM "+company+".mr_item_master where item_code='"+itemcode+"' and '"+unitname+"' IN (unit1,unit2,unit3,unit4,unit5,unit6) "; 
		ResultSet resultSet = statement.executeQuery(query);
		while(resultSet.next())
		{
			if(unitname.equals(resultSet.getString("unit1")))
			{
				resultSet.getString("cf1");
				barcode=resultSet.getString("barcode1");
				if(barcode.equals(""))
				{
					barcode=resultSet.getString("barcode2");
					if(barcode.equals(""))
					{
						barcode=resultSet.getString("barcode3");
					}
				}
			}
			if(unitname.equals(resultSet.getString("unit2")))
			{
				resultSet.getString("cf2");
				barcode=resultSet.getString("barcode4");
				if(barcode.equals(""))
				{
					barcode=resultSet.getString("barcode5");
					if(barcode.equals(""))
					{
						barcode=resultSet.getString("barcode6");
					}
				}
			}
			if(unitname.equals(resultSet.getString("unit3")))
			{
				resultSet.getString("cf3");
				barcode=resultSet.getString("barcode7");
				if(barcode.equals(""))
				{
					barcode=resultSet.getString("barcode8");
					if(barcode.equals(""))
					{
						barcode=resultSet.getString("barcode9");
					}
				}
			}
			if(unitname.equals(resultSet.getString("unit4")))
			{
				resultSet.getString("cf4");
				barcode=resultSet.getString("barcode10");
				if(barcode.equals(""))
				{
					barcode=resultSet.getString("barcode11");
					if(barcode.equals(""))
					{
						barcode=resultSet.getString("barcode12");
					}
				}
			}
			if(unitname.equals(resultSet.getString("unit5")))
			{
				resultSet.getString("cf5");
				barcode=resultSet.getString("barcode13");
				if(barcode.equals(""))
				{
					barcode=resultSet.getString("barcode14");
					if(barcode.equals(""))
					{
						barcode=resultSet.getString("barcode15");
					}
				}
			}
			if(unitname.equals(resultSet.getString("unit6")))
			{
				resultSet.getString("cf6");
				barcode=resultSet.getString("barcode16");
				if(barcode.equals(""))
				{
					barcode=resultSet.getString("barcode17");
					if(barcode.equals(""))
					{
						barcode=resultSet.getString("barcode18");
					}
				}
			}
		}
		con.close();
	}
	catch(Exception e){
		JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
	}
}
	public void get_item_dt(String company, String po_num) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_inv_trans_history where ref_adj_num ='"+po_num+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String barcode =resultSet.getString("barcode");
				String itm_cde =resultSet.getString("item_code");
				float qtyOH =Float.parseFloat(resultSet.getString("qtyOh")) ;
				String unit =resultSet.getString("unit");
				float cf =Float.parseFloat(resultSet.getString("cf")) ;
				double unit_cost =Double.parseDouble(resultSet.getString("unit_cost"));
				float qty =Float.parseFloat(resultSet.getString("qty")) ;
				String itm_dtls = resultSet.getString("item_name");
				
				model.insertRow(model.getRowCount(), new Object[] { model.getRowCount()+1,  barcode, itm_cde,itm_dtls,qtyOH,unit,cf,unit_cost,qty});
			}
			double total=0;
			for(int a=0;a<model.getRowCount();a++)
			{
				String cost=model.getValueAt(a, 7).toString();
				String qtyOH=model.getValueAt(a, 8).toString();
				if(cost.equals(""))
				{
					cost="0";
				}
				if(qtyOH.equals(""))
				{
					qtyOH="0";
				}
				double ttl=Double.parseDouble(cost)*Double.parseDouble(qtyOH);
				total=total+ttl;
				int decimalPlaces = 2;
        		BigDecimal bd = new BigDecimal(total);
        		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		total = bd.doubleValue();
			}
			textField.setText(Double.toString(total));
			statement.close();
			con.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}
	
}