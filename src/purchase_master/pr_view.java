package purchase_master;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class pr_view {

	private static JFrame frame;
	private JTextField textField_2;
	public static JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private static DefaultTableModel model;
	public static JTable table;
	private JDateChooser dateChooser ;
	private JCheckBox checkBox;
	private JLabel lblNewLabel ;
	private JLabel label_1;
	private JMenuItem menuItem_3;
	private JScrollPane scrollPane;
	private JTextField textField_12;
	private JTextField textField_4;
	private JTextField textField_11;
	private JTextField textField_13;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					pr_view window = new pr_view("mr_pro","admin","1","PR-19-01-000001","S01", "","",2 );
					pr_view.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param username 
	 * @param company 
	 * @param host_name 
	 * @param po_num 
	 * @throws JSONException 
	 * @throws SQLException 
	 * @wbp.parser.entryPoint
	 */
	public pr_view(String company, String username,String vendor_id, String po_num, String store_name,String version,String type,int decimal) throws JSONException, SQLException {
		initialize(company,username,vendor_id,po_num, store_name, version,type,decimal);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param company 
	 * @param decimal 
	 * @param host_name 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String company,final String username,final String vendor_id,String po_num, final String store_name,final String version,final String type, int decimal) throws JSONException, SQLException {
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
		Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		JLabel lblPurchaseOrder = new JLabel("Purchase Return");
		lblPurchaseOrder.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPurchaseOrder.setBounds(12, 6, 228, 30);
		panel.add(lblPurchaseOrder);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(898, 6, 60, 30);
		panel.add(menuItem);
		
		menuItem_3 = new JMenuItem("Print");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					report_master.Pr_report.report(textField_2.getText(),con, type, company);
					con.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		menuItem_3.setBorder(null);
		menuItem_3.setIcon(new ImageIcon(printer));
		menuItem_3.setBackground(new Color(243, 229, 216));
		menuItem_3.setBounds(817, 6, 80, 30);
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
		lblDate.setBounds(10, 4, 70, 16);
		panel_2.add(lblDate);
		
		Calendar dateAndTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		lblNewLabel = new JLabel(sdf.format(dateAndTime.getTime()));
		lblNewLabel.setBounds(90, 4, 188, 16);
		panel_2.add(lblNewLabel);
		
		JLabel lblPoDate = new JLabel("PO Date");
		lblPoDate.setBounds(10, 24, 70, 16);
		panel_2.add(lblPoDate);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(10, 44, 70, 16);
		panel_2.add(lblUserName);
		
		JLabel lblPoNo = new JLabel("PO No.");
		lblPoNo.setBounds(10, 64, 70, 16);
		panel_2.add(lblPoNo);
		
		textField_2 = new JTextField("NEW");
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(90, 64, 188, 16);
		panel_2.add(textField_2);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(90, 24, 188, 16);
		//dateChooser.setMinSelectableDate(new Date());
		panel_2.add(dateChooser);
		
		label_1 = new JLabel(username);
		label_1.setBounds(90, 44, 188, 16);
		panel_2.add(label_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(243, 229, 216));
		panel_3.setLayout(null);
		panel_3.setBounds(282, 0, 357, 84);
		panel_1.add(panel_3);
		
		JLabel lblTotalAmountOf = new JLabel("Enter Total Amount of invoice");
		lblTotalAmountOf.setBounds(12, 44, 171, 16);
		panel_3.add(lblTotalAmountOf);
		
		JLabel lblInvoiceNo = new JLabel("Supp. Invoice No.");
		lblInvoiceNo.setBounds(12, 4, 100, 16);
		panel_3.add(lblInvoiceNo);
		
		JLabel lblNoOfItems = new JLabel("No. of Items in PO");
		lblNoOfItems.setBounds(12, 24, 140, 16);
		panel_3.add(lblNoOfItems);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(195, 24, 131, 16);
		panel_3.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(195, 4, 131, 16);
		panel_3.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(195, 44, 131, 16);
		panel_3.add(textField_7);
		
		JLabel lblAssumeTotalCost = new JLabel("Assume cost as Total cost");
		lblAssumeTotalCost.setBounds(10, 61, 140, 16);
		panel_3.add(lblAssumeTotalCost);
		
		checkBox = new JCheckBox("");
		checkBox.setBounds(144, 61, 25, 16);
		panel_3.add(checkBox);
		checkBox.setBackground(new Color(243, 229, 216));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(243, 229, 216));
		panel_4.setLayout(null);
		panel_4.setBounds(638, 0, 319, 84);
		panel_1.add(panel_4);
		
		JLabel lblSuppDetails = new JLabel("Supp Name");
		lblSuppDetails.setBounds(3, 4, 77, 16);
		panel_4.add(lblSuppDetails);
		
		JLabel lblCurrency = new JLabel("Currency");
		lblCurrency.setBounds(3, 64, 70, 16);
		panel_4.add(lblCurrency);
		
		JLabel lblSuppCont = new JLabel("referance");
		lblSuppCont.setBounds(3, 24, 128, 16);
		panel_4.add(lblSuppCont);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(138, 4, 148, 16);
		panel_4.add(textField_3);
		textField_3.setColumns(10);
		get_vendor_dt(company,vendor_id);
		
		JLabel lblPurchaseOrder_1 = new JLabel("Remark");
		lblPurchaseOrder_1.setBounds(3, 44, 58, 16);
		panel_4.add(lblPurchaseOrder_1);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(138, 24, 148, 16);
		panel_4.add(textField_4);
		textField_4.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBounds(55, 44, 231, 16);
		panel_4.add(textField_11);
		
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setColumns(10);
		textField_13.setBounds(138, 64, 148, 16);
		panel_4.add(textField_13);
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.setBounds(12, 95, 933, 441);
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
		model.addColumn("Qty"); 
		model.addColumn("Unit"); 
		model.addColumn("cf"); 
		model.addColumn("Unit Cost"); 
		model.addColumn("Item Discount(%)"); 
		model.addColumn("Total");
		model.addColumn("Tax Rate"); 
		model.addColumn("Tax Type"); 
		model.addColumn("Tax Value"); 
		scrollPane.setViewportView(table);
		
		table.setFocusable(false);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
		  if (i==10) column.setPreferredWidth(70);
		  if (i==11) column.setPreferredWidth(55);
		  if (i==12) column.setPreferredWidth(75);
		}
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(708, 536, 236, 147);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 10, 70, 22);
		panel_5.add(lblTotal);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setText("0.0");
		textField.setEditable(false);
		textField.setBounds(79, 10, 137, 22);
		panel_5.add(textField);
		textField.setColumns(10);
		
		JLabel lblDiscount = new JLabel("Discount %");
		lblDiscount.setBounds(10, 37, 70, 22);
		panel_5.add(lblDiscount);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10)
		        {	
					String total=textField.getText();
                	String disc_per=textField_1.getText();
                	String txval=textField_9.getText();
                	if(!disc_per.equals(""))
                	{
                		double ttl_per=(Double.parseDouble(total)*Double.parseDouble(disc_per))/100;
                		int decimalPlaces = 2;
                		BigDecimal bd = new BigDecimal(ttl_per);
                		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                		ttl_per = bd.doubleValue();
                		textField_12.setText(Double.toString(ttl_per));
                		
                		double subttl=Double.parseDouble(total) - ttl_per;
                		BigDecimal bs = new BigDecimal(subttl);
                		bs = bs.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                		subttl = bs.doubleValue();
                		textField_8.setText(Double.toString(subttl));
                		
                		double grand_vat=(Double.parseDouble(txval)-((Double.parseDouble(txval)*Double.parseDouble(disc_per))/100)) ;
                		BigDecimal bv = new BigDecimal(grand_vat);
                		bv = bv.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                		grand_vat = bv.doubleValue();
                		textField_9.setText(Double.toString(grand_vat));
                		
                		double grnd_ttl=subttl + grand_vat;
                		BigDecimal bg = new BigDecimal(grnd_ttl);
                		bg = bg.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                		grnd_ttl = bg.doubleValue();
                		textField_10.setText(Double.toString(grnd_ttl));
                		
                	}
                }
			}
		});
		textField_1.setEditable(false);
		textField_1.setBounds(79, 37, 56, 22);
		panel_5.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSubTotal = new JLabel("Sub Total");
		lblSubTotal.setBounds(10, 64, 70, 22);
		panel_5.add(lblSubTotal);
		
		textField_8 = new JTextField();
		textField_8.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_8.setText("0.0");
		textField_8.setEditable(false);
		textField_8.setBounds(79, 64, 137, 22);
		panel_5.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblVat = new JLabel("Vat");
		lblVat.setBounds(10, 91, 70, 22);
		panel_5.add(lblVat);
		
		textField_9 = new JTextField();
		textField_9.setText("0.0");
		textField_9.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_9.setEditable(false);
		textField_9.setBounds(79, 91, 137, 22);
		panel_5.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblGrandTotal = new JLabel("Grand Total");
		lblGrandTotal.setBounds(10, 118, 70, 22);
		panel_5.add(lblGrandTotal);
		
		textField_10 = new JTextField();
		textField_10.setText("0.0");
		textField_10.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_10.setEditable(false);
		textField_10.setBounds(79, 118, 137, 22);
		panel_5.add(textField_10);
		textField_10.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_12.setEditable(false);
		textField_12.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10)
		        {
					String total=textField.getText();
                	String disc_per=textField_12.getText();
                	String txval=textField_9.getText();
                	if(!disc_per.equals(""))
                	{
                		double ttl_per=(Double.parseDouble(disc_per)*100)/Double.parseDouble(total);
                		int decimalPlaces = 2;
                		BigDecimal bd = new BigDecimal(ttl_per);
                		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                		ttl_per = bd.doubleValue();
                		textField_1.setText(Double.toString(ttl_per));
                		
                		double subttl=Double.parseDouble(total) - Double.parseDouble(disc_per);
                		BigDecimal bs = new BigDecimal(subttl);
                		bs = bs.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                		subttl = bs.doubleValue();
                		textField_8.setText(Double.toString(subttl));
                		
                		double grand_vat=(Double.parseDouble(txval)-((Double.parseDouble(txval)*ttl_per)/100)) ;
                		BigDecimal bv = new BigDecimal(grand_vat);
                		bv = bv.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                		grand_vat = bv.doubleValue();
                		textField_9.setText(Double.toString(grand_vat));
                		
                		double grnd_ttl=subttl + grand_vat;
                		BigDecimal bg = new BigDecimal(grnd_ttl);
                		bg = bg.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
                		grnd_ttl = bg.doubleValue();
                		textField_10.setText(Double.toString(grnd_ttl));
                		
                	}
		        }
			}
		});
		textField_12.setBounds(139, 37, 77, 22);
		panel_5.add(textField_12);
		textField_12.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 61, 957, 2);
		frame.getContentPane().add(separator);
		
		if(po_num.equals(""))
		{
			
		}
		else
		{
			//edit_po(po_num,company,vendor_id);
			try{
				
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				String query="SELECT * FROM "+company+".mr_purchase_return_list where pr_no ='"+po_num+"' "; 
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next())
				{
					lblNewLabel.setText(resultSet.getString("com_date"));
					java.util.Date date =null;
					if(resultSet.getString("pr_date")!="")
					{
						date = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("pr_date"));
					}
					dateChooser.setDate(date);
					label_1.setText(resultSet.getString("user_id"));
					textField_2.setText(resultSet.getString("pr_no"));
					textField_6.setText(resultSet.getString("invo_nu"));
					textField_5.setText(resultSet.getString("ttl_items"));
					textField_7.setText(resultSet.getString("ttl_price"));
					textField_3.setText(resultSet.getString("vendor_id"));
					textField_4.setText(resultSet.getString("referance"));
					textField_11.setText(resultSet.getString("remark"));
					textField_13.setText(resultSet.getString("currency_id"));
					if(resultSet.getString("assume_ttl_cst").equals("1"))
					{
						checkBox.setSelected(true);
					}
					else
					{
						checkBox.setSelected(false);
					}
					textField_2.setEditable(false);
					textField_5.setEditable(false);
					textField_7.setEditable(false);
					textField_3.setEditable(false);
		        	textField_6.setEditable(false);
		        	dateChooser.setEnabled(false);
		        	scrollPane.setEnabled(true);
		        	get_item_dt(company,po_num,decimal);
				}
				con.close();
			}
			catch(Exception e){
				System.err.println(e);
			}
		}
	}
	private void  get_vendor_dt(String company,String vendor_id)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_vendor where vendor_code='"+vendor_id+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				textField_3.setText(resultSet.getString("vendor_name"));
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	public void get_item_dt(String company, String po_num,int decimal) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query1="SELECT grand_disc FROM "+company+".mr_purchase_return_list where pr_no ='"+po_num+"' "; 
			ResultSet resultSet1 = statement.executeQuery(query1);
			String grand_disc="0";
			if(resultSet1.next())
			{
				grand_disc=resultSet1.getString("grand_disc");
			}
			String query="SELECT * FROM "+company+".mr_purchase_return where ref_pr_num ='"+po_num+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String barcode =resultSet.getString("barcode");
				String itm_cde =resultSet.getString("item_code");
				double qty =Double.parseDouble(resultSet.getString("qty")) ;
				String unit =resultSet.getString("unit");
				double unit_cost =Double.parseDouble(resultSet.getString("unit_cost"));
				double itm_disc =Double.parseDouble(resultSet.getString("itm_disc"));
				double tax_val =Double.parseDouble(resultSet.getString("tax_val"));
				int cf =Integer.parseInt(resultSet.getString("cf")) ;
				double total =Double.parseDouble(resultSet.getString("sub_cost_after_sub_ttl"));
				Statement statement2=con.createStatement();
				String query2="SELECT * FROM "+company+".mr_item_master where item_code ='"+itm_cde+"' "; 
				ResultSet resultSet2 = statement2.executeQuery(query2);
				String itm_dtls = null ;
				String tax = null;
				String tax_type = null ;
				if(resultSet2.next())
				{
					itm_dtls=resultSet2.getString("description1");
					tax =resultSet2.getString("input_tax");
					String taxtype =resultSet2.getString("po_inclusive_tax");
					if(!tax.equals(""))
					{
						if(taxtype.equals("1"))
						{
							tax_type="INC" ;
						}
						else
						{
							tax_type="EXC" ;
						}
					}
					else
					{
						tax_type="NON" ;
					}
				}
				
				model.insertRow(model.getRowCount(), new Object[] { model.getRowCount()+1,  barcode, itm_cde,itm_dtls,qty,unit,cf,unit_cost,itm_disc,total,tax,tax_type,tax_val,"Print"});
			}
			double total=0;
			double vatpric=0;
			double grnd_ttl=0;
			for(int a=0;a<model.getRowCount();a++)
			{
				String ttl=model.getValueAt(a, 9).toString();
				String vat=model.getValueAt(a, 12).toString();
				total=total+Double.parseDouble(ttl);
				int decimalPlaces = 2;
        		BigDecimal bd = new BigDecimal(total);
        		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		total = bd.doubleValue();
				
        		vatpric=vatpric+Double.parseDouble(vat);
				BigDecimal bv = new BigDecimal(vatpric);
        		bv = bv.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		vatpric = bv.doubleValue();
				
			}
			if(!grand_disc.equals("0"))
			{
				String disc_per=grand_disc;
            	String txval=textField_9.getText();
            	if(!disc_per.equals(""))
            	{
            		double ttl_per=(total*Double.parseDouble(disc_per))/100;//(Double.parseDouble(disc_per)*100)/total;
            		BigDecimal bd = new BigDecimal(ttl_per);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		ttl_per = bd.doubleValue();
            		textField_12.setText(Double.toString(ttl_per));
            		double perc_dic=Double.parseDouble(disc_per);
            		BigDecimal be = new BigDecimal(perc_dic);
            		be = be.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		perc_dic = be.doubleValue();
            		textField_1.setText(Double.toString(perc_dic));
            		
            		double subttl=total - Double.parseDouble(disc_per);
            		BigDecimal bs = new BigDecimal(subttl);
            		bs = bs.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		subttl = bs.doubleValue();
            		textField_8.setText(Double.toString(subttl));
            		
            		double grand_vat=(Double.parseDouble(txval)-((Double.parseDouble(txval)*ttl_per)/100)) ;
            		BigDecimal bv = new BigDecimal(grand_vat);
            		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		grand_vat = bv.doubleValue();
            		textField_9.setText(Double.toString(grand_vat));
            		
            		grnd_ttl=subttl + grand_vat;
            		BigDecimal bg = new BigDecimal(grnd_ttl);
            		bg = bg.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		grnd_ttl = bg.doubleValue();
            		textField_10.setText(Double.toString(grnd_ttl));
            	}
			}
			else
			{
				grnd_ttl=total+vatpric;
				BigDecimal be = new BigDecimal(total);
	    		be = be.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	    		total = be.doubleValue();
				textField.setText(Double.toString(total));
				BigDecimal bv = new BigDecimal(Double.toString(vatpric));
	    		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	    		vatpric = bv.doubleValue();
				textField_9.setText(Double.toString(vatpric));
				BigDecimal bg = new BigDecimal(grnd_ttl);
	    		bg = bg.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	    		grnd_ttl = bg.doubleValue();
				textField_10.setText(Double.toString(grnd_ttl));
				BigDecimal bs = new BigDecimal(total);
	    		bs = bs.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	    		total = bs.doubleValue();
				textField_8.setText(Double.toString(total));
				textField_1.setText("0");
				textField_12.setText("0");
			}
			
			
			statement.close();
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	public void get_item_barcode_list(String company,String itemcode,String unit){
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement1=con.createStatement();  
			String query1="SELECT * FROM "+company+".mr_item_master where item_code='"+itemcode+"' "; 
			ResultSet resultSet1 = statement1.executeQuery(query1);
			if(resultSet1.next())
			{
				
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}