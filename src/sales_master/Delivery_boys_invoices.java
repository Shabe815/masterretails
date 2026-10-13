package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import CafePro.Mainpage;

import javax.swing.JComboBox;

public class Delivery_boys_invoices {

	private static JFrame frame;
	private DefaultTableModel model;
	private JTable table;
	private JTextField textField;
	private JComboBox<String> comboBox;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Delivery_boys_invoices window = new Delivery_boys_invoices("mr_pro", "admin","S01","S0101", null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param reg_nme 
	 * @throws JSONException 
	 */
	public Delivery_boys_invoices(String company, String username, String store_name, String reg_nme,String version) throws JSONException {
		initialize(company,  username, store_name,reg_nme, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param reg_nme 
	 * @param store_name2 
	 */
	private void initialize(final String company,final String username,final String store_name, final String reg_nme,final String version) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 909, 696);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 893, 60);
		frame.getContentPane().add(panel);
		
		//Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		JLabel lblItemMasterList = new JLabel("Invoice List");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem_2 = new JMenuItem("Exit");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(737, 7, 60, 48);
		menuItem_2.setIcon(new ImageIcon(exit));
		panel.add(menuItem_2);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				get_itemdisp(company,textField.getText(), reg_nme,comboBox.getSelectedItem().toString());
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(801, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JButton btnVisaCard = new JButton("VISA CARD");
		btnVisaCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Object[] item_list = new String[model.getRowCount()];
		    		boolean status=false;
		    		Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement(); 
					for (int i = 0; i < model.getRowCount(); i++) {
						Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
						String dataCol1 = model.getValueAt(i, 1).toString();
						if (chked) {
							item_list[i]=dataCol1;
							status=true;
							
							String insert_cash="insert into "+company+".mr_pos_settlement_cash (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, `cash_paid`, `type`, `paycode_disc`, `store_id`, `created_date`, `grand_total`, `cust_code`, `cust_prev_balance`, `delivery_user`, `table_num`, `mode`, `cust_ph`) SELECT `reg_num`, `receipt_no`, `user`, `time`, total-vat, `cust_name`, `vat`, `disc`, `total_qty`,total,'PAYCODE','0', `store_id`, `created_date`, total,`cust_code`,'0', `delivery_user`, `table_num`,`mode`,  `cust_ph` FROM "+company+".mr_pos_hold_invoice where receipt_no='"+dataCol1+"'";
							statement.executeUpdate(insert_cash);
							
							String insert_cash_items="insert into "+company+".mr_pos_settlement_cash_items (`ref_receipt_num`, `ref_reg_num`, `barcode`, `item_desc`, `item_code`, `item_cost`, `unit`, `unit_price`, `sp`, `qty`, `total`, `vat`, `discount`, `created_date`, `vat_type`, `vat_perc`, `disc_type`, `cf`) SELECT `ref_receipt_num`, `ref_reg_no`, `barcode`, `item_desc`, `item_code`, cost,`unit`, `unit_price`, `sp`,`qty`, `total`, `vat`, `discount`,  `created_date`,`vat_type`,  `vat_perc`, `disc_type`, `cf` FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+dataCol1+"'";
							statement.executeUpdate(insert_cash_items);
							
							String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`, `pay_code_vat`) SELECT `reg_num`, `receipt_no`, 'VISA', '1', total,total,  `created_date`, if(vat=0,'0',(total*5)/100) FROM "+company+".mr_pos_hold_invoice where receipt_no='"+dataCol1+"' ";
							statement.executeUpdate(query);
							
							String query5="SELECT * FROM "+company+".mr_pos_hold_invoice where receipt_no='"+dataCol1+"' and delivery_user='"+model.getValueAt(i, 8).toString()+"' ";
							ResultSet resultSet5= statement.executeQuery(query5);
							if(resultSet5.next())
							{
								String query6="delete FROM "+company+".mr_pos_hold_invoice where receipt_no='"+dataCol1+"' and delivery_user='"+model.getValueAt(i, 8).toString()+"' ";
								statement.executeUpdate(query6);
								String query7="delete FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+dataCol1+"'  ";
								statement.executeUpdate(query7);
							}
						}
					}
					if(status==true)
					{
						String invoicelist="";
						for(int i=0;i<item_list.length;i++)
						{
							if(item_list[i]!=null)
							{
								if(!invoicelist.equals(""))
								{
									invoicelist+="',";
								}
								if(!invoicelist.equals(""))
								{
									invoicelist+="'";
								}
								invoicelist+=item_list[i];
							}
						}
						if(Mainpage.SoftLang.equals("Ar"))
						{
							if(PosscreenArabic.promt_print.equals("0"))
							{
								byte[] open = {27,112,48,55,121};
						        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
						        DocPrintJob job = pservice.createPrintJob();
						        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
						        Doc doc = new SimpleDoc(open,flavor,null);
						        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
						        job.print(doc, aset);
						        report_master.Delivery_invoice_settle.report(con, PosscreenArabic.printer, PosscreenArabic.reg_nme, comboBox.getSelectedItem().toString(), company,invoicelist);
							}
							else
							{
								int a=JOptionPane.showConfirmDialog(frame, "DO YOU WANT TO PRINT RECEIPT","",JOptionPane.YES_NO_OPTION);
								if(a==0)
								{
									byte[] open = {27,112,48,55,121};
							        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
							        DocPrintJob job = pservice.createPrintJob();
							        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
							        Doc doc = new SimpleDoc(open,flavor,null);
							        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
							        job.print(doc, aset);
									report_master.Delivery_invoice_settle.report(con, PosscreenArabic.printer, PosscreenArabic.reg_nme, comboBox.getSelectedItem().toString(), company,invoicelist);
								}
								else
								{
									byte[] open = {27,112,48,55,121};
							        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
							        DocPrintJob job = pservice.createPrintJob();
							        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
							        Doc doc = new SimpleDoc(open,flavor,null);
							        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
							        job.print(doc, aset);
							        
								}
							}
						}
						else
						{
							if(posscreen.promt_print.equals("0"))
							{
								byte[] open = {27,112,48,55,121};
						        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
						        DocPrintJob job = pservice.createPrintJob();
						        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
						        Doc doc = new SimpleDoc(open,flavor,null);
						        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
						        job.print(doc, aset);
						        report_master.Delivery_invoice_settle.report(con, posscreen.printer, posscreen.reg_nme, comboBox.getSelectedItem().toString(), company,invoicelist);
							}
							else
							{
								int a=JOptionPane.showConfirmDialog(frame, "DO YOU WANT TO PRINT RECEIPT","",JOptionPane.YES_NO_OPTION);
								if(a==0)
								{
									byte[] open = {27,112,48,55,121};
							        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
							        DocPrintJob job = pservice.createPrintJob();
							        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
							        Doc doc = new SimpleDoc(open,flavor,null);
							        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
							        job.print(doc, aset);
									report_master.Delivery_invoice_settle.report(con, posscreen.printer, posscreen.reg_nme, comboBox.getSelectedItem().toString(), company,invoicelist);
								}
								else
								{
									byte[] open = {27,112,48,55,121};
							        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
							        DocPrintJob job = pservice.createPrintJob();
							        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
							        Doc doc = new SimpleDoc(open,flavor,null);
							        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
							        job.print(doc, aset);
							        
								}
							}
						}
						
						
						
						JOptionPane.showMessageDialog(frame, "Saved Successfully");
						frame.setVisible(false);
						unhold.get_itemdisp(company, "", reg_nme,"ALL",store_name);
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Choose Invoice from Below table");
					}
					
					
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(frame, e);
				}
			}
		});
		btnVisaCard.setBounds(247, 22, 97, 25);
		panel.add(btnVisaCard);
		
		JButton btnCash = new JButton("CASH");
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Object[] item_list = new String[model.getRowCount()];
		    		boolean status=false;
		    		Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement(); 
					for (int i = 0; i < model.getRowCount(); i++) {
						Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
						String dataCol1 = model.getValueAt(i, 1).toString();
						if (chked) {
							item_list[i]=dataCol1;
							status=true;
							
							String insert_cash="insert into "+company+".mr_pos_settlement_cash (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, `cash_paid`, `type`, `paycode_disc`, `store_id`, `created_date`, `grand_total`, `cust_code`, `cust_prev_balance`, `delivery_user`, `table_num`, `mode`, `cust_ph`) SELECT `reg_num`, `receipt_no`, `user`, `time`, total-vat, `cust_name`, `vat`, `disc`, `total_qty`,total,'CASH','0', `store_id`, `created_date`, total,`cust_code`,'0', `delivery_user`, `table_num`,`mode`,  `cust_ph` FROM "+company+".mr_pos_hold_invoice where receipt_no='"+dataCol1+"'";
							statement.executeUpdate(insert_cash);
							
							String insert_cash_items="insert into "+company+".mr_pos_settlement_cash_items (`ref_receipt_num`, `ref_reg_num`, `barcode`, `item_desc`, `item_code`, `item_cost`, `unit`, `unit_price`, `sp`, `qty`, `total`, `vat`, `discount`, `created_date`, `vat_type`, `vat_perc`, `disc_type`, `cf`) SELECT `ref_receipt_num`, `ref_reg_no`, `barcode`, `item_desc`, `item_code`, cost,`unit`, `unit_price`, `sp`,`qty`, `total`, `vat`, `discount`,  `created_date`,`vat_type`,  `vat_perc`, `disc_type`, `cf` FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+dataCol1+"'";
							statement.executeUpdate(insert_cash_items);
							
							String query5="SELECT * FROM "+company+".mr_pos_hold_invoice where receipt_no='"+dataCol1+"' and delivery_user='"+model.getValueAt(i, 8).toString()+"' ";
							ResultSet resultSet5= statement.executeQuery(query5);
							if(resultSet5.next())
							{
								String query6="delete FROM "+company+".mr_pos_hold_invoice where receipt_no='"+dataCol1+"' and delivery_user='"+model.getValueAt(i, 8).toString()+"' ";
								statement.executeUpdate(query6);
								String query7="delete FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+dataCol1+"'  ";
								statement.executeUpdate(query7);
							}
						}
					}
					if(status==true)
					{
						String invoicelist="";
						for(int i=0;i<item_list.length;i++)
						{
							if(item_list[i]!=null)
							{
								if(!invoicelist.equals(""))
								{
									invoicelist+="',";
								}
								if(!invoicelist.equals(""))
								{
									invoicelist+="'";
								}
								invoicelist+=item_list[i];
							}
						}
						if(Mainpage.SoftLang.equals("Ar"))
						{
							if(PosscreenArabic.promt_print.equals("0"))
							{
								byte[] open = {27,112,48,55,121};
						        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
						        DocPrintJob job = pservice.createPrintJob();
						        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
						        Doc doc = new SimpleDoc(open,flavor,null);
						        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
						        job.print(doc, aset);
						        report_master.Delivery_invoice_settle.report(con, PosscreenArabic.printer, PosscreenArabic.reg_nme, comboBox.getSelectedItem().toString(), company,invoicelist);
							}
							else
							{
								int a=JOptionPane.showConfirmDialog(frame, "DO YOU WANT TO PRINT RECEIPT","",JOptionPane.YES_NO_OPTION);
								if(a==0)
								{
									byte[] open = {27,112,48,55,121};
							        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
							        DocPrintJob job = pservice.createPrintJob();
							        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
							        Doc doc = new SimpleDoc(open,flavor,null);
							        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
							        job.print(doc, aset);
									report_master.Delivery_invoice_settle.report(con, PosscreenArabic.printer, PosscreenArabic.reg_nme, comboBox.getSelectedItem().toString(), company,invoicelist);
								}
								else
								{
									byte[] open = {27,112,48,55,121};
							        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
							        DocPrintJob job = pservice.createPrintJob();
							        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
							        Doc doc = new SimpleDoc(open,flavor,null);
							        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
							        job.print(doc, aset);
							        
								}
							}
						}
						else
						{
							if(posscreen.promt_print.equals("0"))
							{
								byte[] open = {27,112,48,55,121};
						        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
						        DocPrintJob job = pservice.createPrintJob();
						        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
						        Doc doc = new SimpleDoc(open,flavor,null);
						        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
						        job.print(doc, aset);
						        report_master.Delivery_invoice_settle.report(con, posscreen.printer, posscreen.reg_nme, comboBox.getSelectedItem().toString(), company,invoicelist);
							}
							else
							{
								int a=JOptionPane.showConfirmDialog(frame, "DO YOU WANT TO PRINT RECEIPT","",JOptionPane.YES_NO_OPTION);
								if(a==0)
								{
									byte[] open = {27,112,48,55,121};
							        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
							        DocPrintJob job = pservice.createPrintJob();
							        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
							        Doc doc = new SimpleDoc(open,flavor,null);
							        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
							        job.print(doc, aset);
									report_master.Delivery_invoice_settle.report(con, posscreen.printer, posscreen.reg_nme, comboBox.getSelectedItem().toString(), company,invoicelist);
								}
								else
								{
									byte[] open = {27,112,48,55,121};
							        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
							        DocPrintJob job = pservice.createPrintJob();
							        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
							        Doc doc = new SimpleDoc(open,flavor,null);
							        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
							        job.print(doc, aset);
							        
								}
							}
						}
						
						
						
						JOptionPane.showMessageDialog(frame, "Saved Successfully");
						frame.setVisible(false);
						unhold.get_itemdisp(company, "", reg_nme,"ALL",store_name);
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Choose Invoice from Below table");
					}
					
					
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(frame, e);
				}
			}
		});
		btnCash.setBounds(356, 22, 97, 25);
		panel.add(btnCash);
		
		comboBox = new JComboBox<String> ();
		comboBox.setBounds(465, 23, 248, 22);
		panel.add(comboBox);
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();
			String query="select user_name from "+company+".mr_users where user_type='Delivery Boy' ";
			ResultSet resultSet1 = statement.executeQuery(query);
			while(resultSet1.next())
			{
				comboBox.addItem(resultSet1.getString("user_name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemdisp( company,textField.getText(), reg_nme,comboBox.getSelectedItem().toString());
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 893, 589);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 64, 881, 512);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel_1.add(scrollPane);

		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
				if(column==0)
		        {
		        	return true;  
		        }
				return false;  
		    }
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				
				default:
					return String.class;
				}
			}
		};
		    table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
		    table.setRowHeight(40);
		    
		    model.addColumn("S.No"); 
		    model.addColumn("Receipt No."); 
		    model.addColumn("Customer Code");
		    model.addColumn("Customer Name"); 
			model.addColumn("Invoice Total"); 
			model.addColumn("Date Time"); 
			model.addColumn("Mode"); 
			model.addColumn("Table"); 
			model.addColumn("Delivery Boy"); 
		scrollPane.setViewportView(table);
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(40);
			  if (i==1) column.setPreferredWidth(80);
			  if (i==2) column.setPreferredWidth(100);
			  if (i==3) column.setPreferredWidth(240);
			  if (i==4) column.setPreferredWidth(100);
			  if (i==5) column.setPreferredWidth(100);
			  if (i==6) column.setPreferredWidth(120);
			  if (i==7) column.setPreferredWidth(120);
			  if (i==8) column.setPreferredWidth(120);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 881, 52);
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
				  int rowCount = model.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					get_itemdisp( company,textField.getText(), reg_nme,comboBox.getSelectedItem().toString());
			  }
			});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemdisp( company,textField.getText(), reg_nme,comboBox.getSelectedItem().toString());
			}
		});
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
		textField.setBounds(10, 13, 754, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemdisp( company,textField.getText(), reg_nme,comboBox.getSelectedItem().toString());
			}
		});
		btnSearch.setBounds(774, 13, 97, 25);
		panel_2.add(btnSearch);
		
		get_itemdisp( company,  "",  reg_nme,comboBox.getSelectedItem().toString());
		
        
	}
	private void  get_itemdisp(String company, String search, String reg_num, String combo)
	{
		String comboserch="";
		if(!combo.equals("ALL"))
		{
			comboserch="and delivery_user='"+combo+"'";
		}
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and (receipt_no LIKE '%"+search+"%' OR cust_code LIKE '%"+search+"%' OR cust_name LIKE '%"+search+"%' OR total LIKE '%"+search+"%') ";
		}
		try{
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_pos_hold_invoice where reg_num='"+reg_num+"' and mode='HOME DELIVERY' "+comboserch+"  "+serch_str+"  "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+company+".mr_pos_hold_invoice where reg_num='"+reg_num+"' and mode='HOME DELIVERY' "+comboserch+"  "+serch_str+"  "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String receipt_no=resultSet1.getString("receipt_no");
	                String cust_code=resultSet1.getString("cust_code");
	                String cust_name=resultSet1.getString("cust_name");
	                String total=resultSet1.getString("total");
	                String time=resultSet1.getString("time");
	                String table_num=resultSet1.getString("table_num");
	                String delivery_user=resultSet1.getString("delivery_user");
	                String mode=resultSet1.getString("mode");
	                model.addRow(new Object[]{new Boolean( false ), receipt_no,cust_code, cust_name,total,time,mode,table_num,delivery_user});
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Delivery List Found");
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

