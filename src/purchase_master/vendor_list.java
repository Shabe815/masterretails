package purchase_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import accounts_master.AP_invoice;
import accounts_master.AP_report;
import purchase_master.purchase_order;
import purchase_master.purchase_return;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class vendor_list {

	private JFrame frame;
	private DefaultTableModel model;
	private JTable table;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JTextField textField_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vendor_list window = new vendor_list("mr_pro","", null,"2","");
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
	public vendor_list(String company, String page,String version,String decimal,String username) throws JSONException {
		initialize(company,page, version,decimal,username);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param page 
	 * @param decimal 
	 */
	private void initialize(final String company, final String page,String version, final String decimal,final String username) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 696);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Vendor List");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
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
				get_vend_disp( company, "", "");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		final Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JMenuItem menuItem_3 = new JMenuItem("Print");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					frame.setAlwaysOnTop(false);
					report_master.vendor_list.report(con,company,username,"");
					//frame.setAlwaysOnTop(true);
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
		menuItem_3.setBounds(688, 11, 80, 44);
		panel.add(menuItem_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 601);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(741, 557, 179, 22);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 78, 908, 469);
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
		    
		    model.addColumn("Vendor Name");
		    model.addColumn("Vendor Code"); 
		    model.addColumn("Contact Name"); 
		    model.addColumn("Contact Number"); 
		    model.addColumn("Outstanding Balance");  
		scrollPane.setViewportView(table);
		
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(10);
			  if (i==1) column.setPreferredWidth(50);
			  if (i==2) column.setPreferredWidth(50);
			  if (i==3) column.setPreferredWidth(50);
			  if (i==4) column.setPreferredWidth(50);
			  if (i==5) column.setPreferredWidth(100);
			  if (i==6) column.setPreferredWidth(10);
			  if (i==7) column.setPreferredWidth(10);
			  if (i==8) column.setPreferredWidth(10);
			  if (i==9) column.setPreferredWidth(10);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 908, 52);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
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
				  get_vend_disp( company,comboBox.getSelectedItem().toString(),textField.getText());
			  }
			});
//		textField.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent e) {
//				if (e.getKeyCode()==KeyEvent.VK_ENTER){
//					get_vend_disp( company,comboBox.getSelectedItem().toString(),textField.getText());
//				}
//			}
//		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e1) {
				int keycode=e1.getKeyCode();
				if(keycode==40)
				{
					table.requestFocus();
				}
			}
		});
		textField.setBounds(12, 13, 493, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_vend_disp( company,comboBox.getSelectedItem().toString(),textField.getText());
			}
		});
		btnSearch.setBounds(518, 13, 97, 25);
		panel_2.add(btnSearch);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_vend_disp( company,comboBox.getSelectedItem().toString(),textField.getText());
			}
		});
		comboBox.setBounds(769, 14, 127, 22);
		comboBox.addItem("Vendor Name");
		comboBox.addItem("Vendor Code"); 
		comboBox.addItem("Contact Name"); 
		comboBox.addItem("Contact Number"); 
		comboBox.addItem("Outstanding Balance");  
		panel_2.add(comboBox);
		
		JLabel lblFilter = new JLabel("Sort By");
		lblFilter.setBounds(713, 17, 54, 16);
		panel_2.add(lblFilter);
		
		JLabel lblOutstandingVendorPayables = new JLabel("Outstanding Vendor Payables");
		lblOutstandingVendorPayables.setBounds(527, 560, 185, 16);
		panel_1.add(lblOutstandingVendorPayables);
		table.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e1) {
	    		if (e1.getKeyChar() == KeyEvent.VK_ENTER) {
	    			int column = 0;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	String code = table.getModel().getValueAt(rows, 1).toString();
                	if(page.equals("po"))
                	{
                		purchase_order.textField_3.setText(value);
                		purchase_order.textField_13.setText(table.getModel().getValueAt(rows, 1).toString());
                		purchase_order.frame.setAlwaysOnTop(true);
                		purchase_order.menuItem.setEnabled(true);
                	}
                	else if(page.equals("pr"))
                	{
                		purchase_return.textField_3.setText(value);
                		purchase_return.textField_13.setText(table.getModel().getValueAt(rows, 1).toString());
                		//purchase_return.get_po_list(company,table.getModel().getValueAt(rows, 1).toString());
                	}
                	else if(page.equals("APinvoice"))
                	{
                		AP_invoice.textField_14.setText(table.getModel().getValueAt(rows, 0).toString());
                		AP_invoice.textField_11.setText(table.getModel().getValueAt(rows, 1).toString());
                	}
                	else if(page.equals("sales_summary"))
                	{
                		History_Reports.sales_summary.textField_4.setText(value);
                	}
                	else if(page.equals("itemmaster"))
                	{
                		inventory_master.Item_master.textField_4.setText(value);
                		inventory_master.Item_master.textField_5.setText(table.getModel().getValueAt(rows, 1).toString());
                	}
                	else if(page.equals("vendor_dt"))
                	{
                		purchase_master.vendor_details.get_vndrdtls_bsd_name( company, value);
                		//purchase_master.vendor_details.get_vend_itm_list( company, purchase_master.vendor_details.textField.getText());
                		//purchase_master.vendor_details.frame.setAlwaysOnTop(true);
        				//purchase_master.vendor_details.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        				//purchase_master.vendor_details.mntmNewMenuItem.setEnabled(true);
                	}
                	else if(page.equals("po_items"))
                	{
                		PO_reports.vendor_items_purchase.textField.setText(value);
                	}
                	else if(page.equals("po_history"))
                	{
                		PO_reports.purchase_order.textField.setText(value);
                	}
                	else if(page.equals("pr_history"))
                	{
                		PO_reports.PR_report.textField.setText(value);
                	}
                	else if(page.equals("vendor_history"))
                	{
                		PO_reports.vendor_summary_report.textField.setText(value);
                		PO_reports.vendor_summary_report.textField_1.setText(code);
                		
                	}
                	else if(page.equals("AP_report"))
                	{
                		AP_report.textField.setText(value);
                	}
                	else if(page.equals("item_analysis"))
                	{
                		History_Reports.item_analysis.textField.setText(table.getModel().getValueAt(rows, 1).toString());
                		History_Reports.item_analysis.textField_2.setText(value);
                	}
                	else if(page.equals("vendorinvoice"))
                	{
                		History_Reports.VendorInvoices.textField.setText(table.getModel().getValueAt(rows, 1).toString());
                		History_Reports.VendorInvoices.textField_1.setText(value);
                	}
                	else if(page.equals("vendor_items"))
                	{
                		PO_reports.vendor_list_items.textField.setText(value);
                		PO_reports.vendor_list_items.textField_1.setText(table.getModel().getValueAt(rows, 1).toString());
                	}
                	
                	else if(page.equals("vendor_pay"))
                	{
                		accounts_master.Accountpayable.textField_1.setText(value);
                		accounts_master.Accountpayable.textField.setText(table.getModel().getValueAt(rows, 1).toString());
                		accounts_master.Accountpayable.get_po_term_disp( company,table.getModel().getValueAt(rows, 1).toString(),decimal);
                	}
                	else
                	{
                		
                	}
                	
                	
                	frame.setVisible(false);
	    		}
	    	}
	    });
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 0;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	String code = table.getModel().getValueAt(rows, 1).toString();
                	if(page.equals("po"))
                	{
                		purchase_order.textField_3.setText(value);
                		purchase_order.textField_13.setText(table.getModel().getValueAt(rows, 1).toString());
                		purchase_order.frame.setAlwaysOnTop(true);
                		purchase_order.menuItem.setEnabled(true);
                	}
                	else if(page.equals("pr"))
                	{
                		purchase_return.textField_3.setText(value);
                		purchase_return.textField_13.setText(table.getModel().getValueAt(rows, 1).toString());
                		//purchase_return.get_po_list(company,table.getModel().getValueAt(rows, 1).toString());
                	}
                	else if(page.equals("APinvoice"))
                	{
                		AP_invoice.textField_14.setText(table.getModel().getValueAt(rows, 0).toString());
                		AP_invoice.textField_11.setText(table.getModel().getValueAt(rows, 1).toString());
                	}
                	else if(page.equals("sales_summary"))
                	{
                		History_Reports.sales_summary.textField_4.setText(value);
                	}
                	else if(page.equals("itemmaster"))
                	{
                		inventory_master.Item_master.textField_4.setText(value);
                		inventory_master.Item_master.textField_5.setText(table.getModel().getValueAt(rows, 1).toString());
                	}
                	else if(page.equals("vendor_dt"))
                	{
                		purchase_master.vendor_details.get_vndrdtls_bsd_name( company, value);
                		//purchase_master.vendor_details.get_vend_itm_list( company, purchase_master.vendor_details.textField.getText());
                		//purchase_master.vendor_details.frame.setAlwaysOnTop(true);
        				//purchase_master.vendor_details.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        				//purchase_master.vendor_details.mntmNewMenuItem.setEnabled(true);
                	}
                	else if(page.equals("po_items"))
                	{
                		PO_reports.vendor_items_purchase.textField.setText(value);
                	}
                	else if(page.equals("po_history"))
                	{
                		PO_reports.purchase_order.textField.setText(value);
                	}
                	else if(page.equals("pr_history"))
                	{
                		PO_reports.PR_report.textField.setText(value);
                	}
                	else if(page.equals("vendor_history"))
                	{
                		PO_reports.vendor_summary_report.textField.setText(value);
                		PO_reports.vendor_summary_report.textField_1.setText(code);
                		
                	}
                	else if(page.equals("AP_report"))
                	{
                		AP_report.textField.setText(value);
                	}
                	else if(page.equals("item_analysis"))
                	{
                		History_Reports.item_analysis.textField.setText(table.getModel().getValueAt(rows, 1).toString());
                		History_Reports.item_analysis.textField_2.setText(value);
                	}
                	else if(page.equals("vendor_items"))
                	{
                		PO_reports.vendor_list_items.textField.setText(value);
                		PO_reports.vendor_list_items.textField_1.setText(table.getModel().getValueAt(rows, 1).toString());
                	}
                	
                	else if(page.equals("vendor_pay"))
                	{
                		accounts_master.Accountpayable.textField_1.setText(value);
                		accounts_master.Accountpayable.textField.setText(table.getModel().getValueAt(rows, 1).toString());
                		accounts_master.Accountpayable.get_po_term_disp( company,table.getModel().getValueAt(rows, 1).toString(),decimal);
                	}
                	else
                	{
                		
                	}
                	
                	
                	frame.setVisible(false);
                }
            }
        });
		textField.requestFocus();
	}
	private void  get_vend_disp(String company, String filter, String search)
	{
		comboBox.addItem("");
		comboBox.addItem(""); 
		comboBox.addItem(""); 
		comboBox.addItem(""); 
		comboBox.addItem("");  
		
		
		String filter_name = "";
		String serch_str = "";
		if(filter=="Vendor Name")
		{
			filter_name=company+".mr_vendor.vendor_name";
		}
		else if(filter=="Vendor Code")
		{
			filter_name=company+".mr_vendor.vendor_code";
		}
		else if(filter=="Contact Name")
		{
			filter_name=company+".mr_vendor.vendor_cont_name";
		}
		else if(filter=="Contact Number")
		{
			filter_name=company+".mr_vendor.vendor_cont_mob";
		}
		else if(filter=="Outstanding Balance")
		{
			filter_name=company+".mr_vendor.vendor_pending_amt";
		}
		if(!search.equals(""))
		{
			serch_str="WHERE "+company+".mr_vendor.vendor_name LIKE '%"+search+"%' OR "+company+".mr_vendor.vendor_code LIKE '%"+search+"%' OR "+company+".mr_vendor.vendor_cont_name LIKE '%"+search+"%' OR "+company+".mr_vendor.vendor_cont_mob LIKE '%"+search+"%' ";
		}
		double ttlbal = 0;
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_vendor "+serch_str+" ORDER BY "+filter_name+" ASC  "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			
			while(resultSet.next())
			{
				String name=resultSet.getString("vendor_name");
                String code=resultSet.getString("vendor_code");
                String cntct_name=resultSet.getString("vendor_cont_name");
                String cntct_num=resultSet.getString("vendor_cont_mob");
                String bal_amt=resultSet.getString("vendor_pending_amt");
                ttlbal+=Double.parseDouble(bal_amt);
                model.addRow(new Object[]{name,code,cntct_name,cntct_num,bal_amt});
              
			}
			
			int decimalPlaces = 2;
			BigDecimal bd = new BigDecimal(ttlbal);
			bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
			ttlbal = bd.doubleValue();
			textField_1.setText(Double.toString(ttlbal));
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
}
