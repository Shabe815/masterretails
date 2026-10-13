package sales_distribution;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import sales_master.Customer_master;
import sales_master.cust_payment_voucher;

import javax.swing.JComboBox;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class sd_list {

	public static JFrame frame;
	private static DefaultTableModel model;
	private static JTable table;
	private JTextField textField;
	private static JButton btnProceed;
	static JButton btnNewButton;
	private static DecimalFormat df2;
	public static JComboBox <String> comboBox;
	private JButton btnconvertToInvoice;
	private static JTextField textField_1;
	private static String sd_active="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					sd_list window = new sd_list("mr_pro","admin","S01", null,"2","");
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
	public sd_list(String db_name, String username,String store,String version,String decimal,String language) throws JSONException {
		initialize(db_name,username,store, version,decimal, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param decimal 
	 * @param page 
	 */
	private void initialize(final String db_name,final String username,final String store,final String version, final String decimal,final String language) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 710);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    	//CafePro.Mainpage.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	CafePro.Mainpage.btnPurchaseReturn.setEnabled(true);
		    	//CafePro.Mainpage.btnNewButton_1.setEnabled(true);
		    }
		});
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	    final String formattedDate = myDateObj.format(myFormatObj); 
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 96);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Sales & Distribution List");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				CafePro.Mainpage.btnPurchaseReturn.setEnabled(true);
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
				textField.setText("");
				get_po_disp( db_name, "OPEN", "",comboBox.getSelectedItem().toString());
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
					report_master.sd_list.report(con,textField.getText().trim(),comboBox.getSelectedItem().toString(),db_name);
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
		menuItem_3.setBounds(697, 7, 80, 48);
		panel.add(menuItem_3);
		
		
		
		btnProceed = new JButton("Post S&D");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row>-1)
				{
					frame.setAlwaysOnTop(false);
					int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Post This Invoice","Warning",JOptionPane.YES_NO_OPTION);
			        // 0=yes, 1=no, 2=cancel
			        if(cofrm_bx==0)
			        {
			        	post_sd_invoice( db_name,  username,  store,  version, decimal);
			        }
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Select A Row from Table");
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
				
			}
		});
		btnProceed.setBounds(589, 15, 98, 25);
		panel.add(btnProceed);
		
		btnNewButton = new JButton("New S&D");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					new sd_lines (db_name, username,"", "",store, version,decimal,language);
					btnNewButton.setEnabled(false);
					sd_lines.frame.setAlwaysOnTop(true);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(480, 15, 97, 25);
		panel.add(btnNewButton);
		
		JButton btnVoid = new JButton("Void S&D");
		btnVoid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(frame, "Select A Row");
				}
				else
				{
					int a= JOptionPane.showConfirmDialog(frame, "Do You Want to Void This Invoice","",JOptionPane.YES_NO_OPTION);
					if(a==0)
					{
						try{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="SELECT * FROM "+db_name+".mr_sd_list where invoice_num='"+model.getValueAt(row, 0).toString()+"'"; 
							ResultSet resultSet = statement.executeQuery(query);
							if(resultSet.next())
							{
								String update_pl="update "+db_name+".mr_sd_list set status='VOID' where invoice_num ='"+model.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(update_pl);
								
								String history="INSERT INTO "+db_name+".mr_sd_lines_history(`ref_inv_num`, `barcode`, `item_code`, `item_name`, `tax_type`, `output_tax`, `qty`, `unit`, `cf`, `unit_cost`, `itm_disc`, `itm_cost_after_disc`, `sub_ttl_disc`, `sub_cost_after_sub_ttl_disc`, `tax_val`, `total`, `cost_after_sub_ttl_disc`, `created_date`, `modified_date`, `remark`, `item_details`, `item_cost`) SELECT `ref_inv_num`, `barcode`, `item_code`, `item_name`, `tax_type`, `output_tax`, `qty`, `unit`, `cf`, `unit_cost`, `itm_disc`, `itm_cost_after_disc`, `sub_ttl_disc`, `sub_cost_after_sub_ttl_disc`, `tax_val`, `total`, `cost_after_sub_ttl_disc`, `created_date`, `modified_date`, `remark`, `item_details`, `item_cost` FROM "+db_name+".mr_sd_lines WHERE ref_inv_num ='"+table.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(history);
								
								String pohistory="INSERT INTO "+db_name+".mr_sd_list_history(  `date`, `user_id`, `invoice_num`, `remark`, `total_amt`, `disc`, `total_vat`, `cust_id`, `cust_addr`, `double_disc`, `status`, `referance`, `store_name`, `created_date`, `modified_date`, `type`,cust_name,salesman,paymentmethod) SELECT  `date`, `user_id`, `invoice_num`, `remark`, `total_amt`, `disc`, `total_vat`, `cust_id`, `cust_addr`, `double_disc`, `status`, `referance`, `store_name`, `created_date`, `modified_date`, `type`,cust_name,salesman,paymentmethod FROM "+db_name+".mr_sd_list WHERE invoice_num ='"+table.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(pohistory);
								
								String delete_do_list="delete from "+db_name+".mr_delivery_list where invoice_num ='"+table.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(delete_do_list);
								
								String delete_do_note="delete from "+db_name+".mr_delivery_note where ref_inv_num ='"+table.getValueAt(row, 0).toString()+"'  ";
								statement.executeUpdate(delete_do_note);
								
								String delete_pl="delete from "+db_name+".mr_sd_list where invoice_num ='"+table.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(delete_pl);
								
								String delete_po="delete from "+db_name+".mr_sd_lines where ref_inv_num ='"+table.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(delete_po);
								
								JOptionPane.showMessageDialog(frame, "Voided Successfully");
								sales_distribution.sd_list.get_po_disp(db_name, "OPEN", "",comboBox.getSelectedItem().toString());
								
								
							}
							con.close();
						}
						catch(Exception e){
							System.err.println(e);
						}
					}
				}
				
			}
		});
		btnVoid.setBounds(381, 15, 87, 25);
		panel.add(btnVoid);
		
		JButton btnCustomerMaster = new JButton("Customer Master");
		btnCustomerMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Customer_master(db_name, username, "", store, version,"", language);
			}
		});
		btnCustomerMaster.setBounds(21, 55, 159, 25);
		panel.add(btnCustomerMaster);
		
		btnconvertToInvoice = new JButton("Convert to Invoice");
		btnconvertToInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row>-1)
				{
					if(table.getValueAt(row, 7).toString().equals("sd_invoice"))
					{
		        		JOptionPane.showMessageDialog(frame, "Selected Row Item is Not a Quotation , Select A Quotaion");
		        		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
					else
					{
						frame.setAlwaysOnTop(false);
						int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Convert This Quotation","Warning",JOptionPane.YES_NO_OPTION);
				        // 0=yes, 1=no, 2=cancel
				        if(cofrm_bx==0)
				        {
				        	frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				        	if(table.getValueAt(row, 1).toString().equals("0"))
							{
				        		JOptionPane.showMessageDialog(frame, "Total Amount of this invoice is Zero");
				        		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
							}
				        	else
				        	{
				        		Connection con=Dbsetup.dbconn.getConnection();
								Statement statement;
								try {
									statement = con.createStatement();
									String seq_num = null;
									String trans_seq = "select tran_seq from "+db_name+".mr_transaction_seq where tran_name='sd_invoice' ";
									ResultSet resultSet1 = statement.executeQuery(trans_seq);
								    if (resultSet1.next())
								    {
								    	seq_num=resultSet1.getString("tran_seq");
								    	String[] seq=seq_num.split("-");
								    	DateFormat df = new SimpleDateFormat("yy");
								    	String formatdDate = df.format(Calendar.getInstance().getTime());
								    	if(!seq[1].equals(formattedDate))
								    	{
								    		seq[1]=formatdDate;
								    	}
								    	seq_num=seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+seq[3];
								    	String sqnum=Integer.toString(Integer.parseInt(seq[3])+1);
								    	StringBuilder sb = new StringBuilder();
										for (int toPrepend=6-sqnum.length(); toPrepend>0; toPrepend--) {
										    sb.append('0');
										}
										sb.append(sqnum);
								    	String save_po="update "+db_name+".mr_transaction_seq set tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_name='sd_invoice' ";
										statement.executeUpdate(save_po);
								    }
									
								    String insertnewinvo="insert into "+db_name+".mr_sd_list(`date`, `user_id`, `invoice_num`, `remark`, `total_amt`, `disc`, `total_vat`, `cust_id`, `cust_addr`, `double_disc`, `status`, `referance`, `store_name`, `created_date`, `modified_date`, `type`, `cust_name`, `invoice_ref`,salesman,paymentmethod) select  `date`, `user_id`, '"+seq_num+"', `remark`, `total_amt`, `disc`, `total_vat`, `cust_id`, `cust_addr`, `double_disc`, `status`, `referance`, `store_name`, `created_date`, `modified_date`, 'sd_invoice', `cust_name`, '"+table.getValueAt(row, 0).toString()+"',salesman,paymentmethod FROM "+db_name+".mr_sd_list WHERE invoice_num ='"+table.getValueAt(row, 0).toString()+"' ";
								    statement.executeUpdate(insertnewinvo);
								    
								    String insertnewinvo1="insert into "+db_name+".mr_sd_lines(`ref_inv_num`, `barcode`, `item_code`, `item_name`, `tax_type`, `output_tax`, `qty`, `unit`, `cf`, `unit_cost`, `itm_disc`, `itm_cost_after_disc`, `sub_ttl_disc`, `sub_cost_after_sub_ttl_disc`, `tax_val`, `total`, `cost_after_sub_ttl_disc`, `created_date`, `modified_date`, `remark`, `item_details`, `item_cost`) select  '"+seq_num+"', `barcode`, `item_code`, `item_name`, `tax_type`, `output_tax`, `qty`, `unit`, `cf`, `unit_cost`, `itm_disc`, `itm_cost_after_disc`, `sub_ttl_disc`, `sub_cost_after_sub_ttl_disc`, `tax_val`, `total`, `cost_after_sub_ttl_disc`, `created_date`, `modified_date`, `remark`, `item_details`, `item_cost` FROM "+db_name+".mr_sd_lines WHERE ref_inv_num ='"+table.getValueAt(row, 0).toString()+"' ";
								    statement.executeUpdate(insertnewinvo1);
									
									con.close();
									post_sd_invoice( db_name,  username,  store,  version, decimal);
									
									
									frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					                JOptionPane.showMessageDialog(frame, "Quotaion Converted Successfully");
					                sales_distribution.sd_list.get_po_disp(db_name, "OPEN", "",comboBox.getSelectedItem().toString());
					                for(int z=0;z<table.getRowCount();z++)
									{
										if(table.getValueAt(z, 0).toString().equals(seq_num))
										{
											table.setRowSelectionInterval(z,z);
											int b = JOptionPane.showConfirmDialog(frame, "Do you Want to Post This Converted Invoice","Warning",JOptionPane.YES_NO_OPTION);
									        // 0=yes, 1=no, 2=cancel
									        if(b==0)
									        {
									        	post_sd_invoice( db_name,  username,  store,  version, decimal);
									        }
										}
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
									JOptionPane.showMessageDialog(frame, e1.getMessage());
									frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								}
				        	}
				        }	
					}
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Select A Row from Table");
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		btnconvertToInvoice.setBounds(634, 55, 143, 25);
		panel.add(btnconvertToInvoice);
		
		JButton btnCustomerBalanceSummary = new JButton("Customer Balance summary");
		btnCustomerBalanceSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					frame.setAlwaysOnTop(false);
					report_master.customer_list.report(con,db_name,username,"A4");
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
		btnCustomerBalanceSummary.setBounds(416, 55, 206, 25);
		panel.add(btnCustomerBalanceSummary);
		
		JButton btnDeliveryNote = new JButton("Delivery Note");
		btnDeliveryNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Delivery_list(db_name,username,store, version,decimal);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDeliveryNote.setBounds(790, 55, 119, 25);
		panel.add(btnDeliveryNote);
		
		JButton btnCustomerPayment = new JButton("Customer Payment");
		btnCustomerPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new sales_distribution.Cust_voucher_list(db_name,username,store, version,"2", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCustomerPayment.setBounds(202, 55, 202, 25);
		panel.add(btnCustomerPayment);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 95, 932, 562);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 88, 908, 437);
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
		    
		    model.addColumn("Invoice No."); 
		    model.addColumn("Invoice Amount");
		    model.addColumn("Customer Name");
		    model.addColumn("Date");
		    model.addColumn("Salesman");
		    model.addColumn("Referance");
		    model.addColumn("Remark");
		    model.addColumn("Type");
		    model.addColumn("Customer Code");
		    model.addColumn("Payment Mode");
		    
		scrollPane.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(150);
			  if (i==1) column.setPreferredWidth(120);
			  if (i==2) column.setPreferredWidth(250);
			  if (i==3) column.setPreferredWidth(100);
			  if (i==4) column.setPreferredWidth(140);
			  if (i==5) column.setPreferredWidth(200);
			  if (i==6) column.setPreferredWidth(200);
			  if (i==7) column.setPreferredWidth(100);
			  if (i==8) column.setPreferredWidth(100);
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
				  get_po_disp( db_name,"OPEN",textField.getText(),comboBox.getSelectedItem().toString());
			  }
			});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					get_po_disp( db_name,"OPEN",textField.getText(),comboBox.getSelectedItem().toString());
				}
			}
		});
		textField.setBounds(168, 13, 337, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name,"OPEN",textField.getText(),comboBox.getSelectedItem().toString());
			}
		});
		btnSearch.setBounds(518, 13, 97, 25);
		panel_2.add(btnSearch);
		
		JButton btnNewButton_1 = new JButton("S&D Posted List");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new sales_distribution.sd_post_list(db_name,username,store, version,decimal,language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(753, 13, 143, 25);
		panel_2.add(btnNewButton_1);
		
		JButton btnVoidPoList = new JButton("Void S&D List");
		btnVoidPoList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new sales_distribution.sd_voidlist(db_name,username,store, version,decimal);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnVoidPoList.setBounds(633, 13, 117, 25);
		panel_2.add(btnVoidPoList);
		
		comboBox = new JComboBox <String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name,"OPEN",textField.getText(),comboBox.getSelectedItem().toString());
			}
		});
		comboBox.setBounds(12, 14, 144, 22);
		panel_2.add(comboBox);
		
		JLabel lblTotalAmount = new JLabel("TOTAL AMOUNT");
		lblTotalAmount.setBounds(63, 538, 102, 16);
		panel_1.add(lblTotalAmount);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(177, 532, 180, 22);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		comboBox.addItem("ALL");
		comboBox.addItem("INVOICE");
		comboBox.addItem("QUOTATION");
		//comboBox.addItem("DELIVERY");
		 
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 0;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	try{
            			
	 					new sales_distribution.sd_lines(db_name, username, table.getModel().getValueAt(rows, 2).toString(), value, store, version,decimal,language);
	 					sd_lines.frame.setAlwaysOnTop(true);
                	}
                	catch (JSONException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                
            }
        });
        //get_po_disp( db_name,"OPEN",textField.getText(),comboBox.getSelectedItem().toString());
        try {
			Connection con=Dbsetup.dbconn.getConnection();
			String query191="SELECT * FROM "+db_name+".mr_store where store_id='"+store+"'"; 
			Statement statement191=con.createStatement();  
	        ResultSet resultSet191 = statement191.executeQuery(query191);
	        if(resultSet191.next())
	        {
	        	sd_active=resultSet191.getString("sd_active");
	        	if(sd_active.equals("0"))
	        	{
	        		btnDeliveryNote.setEnabled(false);
	        	}
	        	else
	        	{
	        		btnDeliveryNote.setEnabled(true);
	        	}
	        	
	        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void post_sd_invoice(String db_name, String username, String store, String version,String decimal)
	{
		try {
			int row=table.getSelectedRow();
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        	if(table.getValueAt(row, 1).toString().equals("0"))
			{
        		JOptionPane.showMessageDialog(frame, "Total Amount of this invoice is Zero");
        		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
        	else
        	{
        		if(table.getValueAt(row, 7).toString().equals("sd_invoice"))
				{
        			LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    final Calendar cal = Calendar.getInstance();
				    final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
				    String formattedDate = myDateObj.format(myFormatObj);  
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					Statement statement111;
					try {
						statement = con.createStatement();
						statement111 = con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+db_name+".mr_sd_list where invoice_num='"+table.getValueAt(row, 0).toString()+"' ");
						if(resultSet.next())
						{
							String jv_date=resultSet.getString("date");
							String inv_no=table.getValueAt(row, 0).toString();
							
							if(sd_active.equals("1"))
							{
								if(Double.parseDouble(table.getValueAt(row, 1).toString())>0)
								{
									String del_dt= "select  if(sum(delivered_qty) >= 0,delivered_qty,-1) as qty_ttl from "+db_name+".mr_delivery_note where `ref_inv_num`='"+table.getValueAt(row, 0).toString()+"' ";
									ResultSet resultSet01 = statement111.executeQuery(del_dt);
									if(resultSet01.next())
									{
										if(Double.parseDouble(resultSet01.getString("qty_ttl"))>0)
										{
											JOptionPane.showMessageDialog(frame, "Some Items are not Delivered yet, Create DO and Post DO, Before Posting S&D");
											frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
											return;
										}
										else if(Double.parseDouble(resultSet01.getString("qty_ttl"))<0)
										{
											JOptionPane.showMessageDialog(frame, "Some Items are not Delivered yet, Create DO and Post DO, Before Posting S&D");
											frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
											return;
										}
									}
								}
							}
							if(resultSet.getString("status").equals("OPEN"))
							{
								double ttl_price=Double.parseDouble(resultSet.getString("total_amt"));
								double disc=Double.parseDouble(resultSet.getString("disc"));
								double total_vat=Double.parseDouble(resultSet.getString("total_vat"));
								double ttl_amt=ttl_price+total_vat-disc;
								String cust_name=resultSet.getString("cust_id");
								String query44="SELECT * FROM "+db_name+".mr_customer_master  where cust_code='"+cust_name+"'"; 
								Statement statement11=con.createStatement();  
								ResultSet resultSet112 = statement11.executeQuery(query44);
								//double ttl_credit_limit=0;
								double ttl_avail_credit=0;
								double ttl_new_credit=0;
								if(resultSet112.next())
								{
									//ttl_credit_limit=Double.parseDouble(resultSet112.getString("credit_limit"));
									ttl_avail_credit=Double.parseDouble(resultSet112.getString("avail_credit"))+Double.parseDouble(resultSet112.getString("advance_amt"));
									ttl_new_credit=(ttl_price+total_vat);
									
								}
								
								if(ttl_new_credit>ttl_avail_credit)
								{
									JOptionPane.showMessageDialog(frame, "Credit Limit Exceeded");
									frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								}
								else
								{
								
								double grandttl=Double.parseDouble(resultSet.getString("total_amt"))+Double.parseDouble(resultSet.getString("total_vat"))-Double.parseDouble(resultSet.getString("disc"));
								String query6="insert into "+db_name+".mr_sd_customer_payment(`ref_tran_num`, `ref_cust_id`, `invo_num`, `invo_date`, `invo_amt`, `paid_amt`, `bal_amt`, `store_id`, `created_date`, `modified_date`, `status`,type) values "
										+ "('"+table.getValueAt(row, 0).toString()+"','"+resultSet.getString("cust_id")+"','"+table.getValueAt(row, 0).toString()+"','"+resultSet.getString("date")+"','"+grandttl+"','0','"+grandttl+"','"+store+"','"+formattedDate+"','"+formattedDate+"','0','IN')";
								statement111.executeUpdate(query6);
								String vendor_payment_dt="insert INTO "+db_name+".mr_sd_customer_payment_details(`ref_tran_num`, `seq_num`, `ref_cust_id`, `cr_amt`, `dr_amt`,disc_amt,  `store_id`, `username`, `created_date`, `modified_date`,issue_date,status) VALUES"
										+ "('"+table.getValueAt(row, 0).toString()+"','"+table.getValueAt(row, 0).toString()+"','"+resultSet.getString("cust_id")+"','"+grandttl+"','0','0','"+store+"','"+username+"','"+formattedDate+"','"+formattedDate+"','"+resultSet.getString("date")+"','0' )";
								statement111.executeUpdate(vendor_payment_dt);
								
								String seq_num = null;
								String trans_seq= "select tran_seq from "+db_name+".mr_transaction_seq where `tran_name`='sales invoice' ";
								ResultSet resultSet25 = statement111.executeQuery(trans_seq);
								if(resultSet25.next())
								{
									seq_num=resultSet25.getString("tran_seq");
									String[] seq=seq_num.split("-");
									DateFormat df = new SimpleDateFormat("yy");
									String formatdDate = df.format(Calendar.getInstance().getTime());
									if(!seq[1].equals(formattedDate))
									{
										seq[1]=formatdDate;
									}
									seq_num=seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+seq[3];
									String sqnum=Integer.toString(Integer.parseInt(seq[3])+1);
									StringBuilder sb = new StringBuilder();
									for (int toPrepend=6-sqnum.length(); toPrepend>0; toPrepend--) {
										sb.append('0');
									}
									sb.append(sqnum);
									String save_po="update "+db_name+".mr_transaction_seq set tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_name='sales invoice' ";
									statement111.executeUpdate(save_po);
								}
								String insert_acc_header="insert into "+db_name+".mr_accounts_trans_header (tran_seqencecode,tran_date,tran_time,tran_remark,tran_debit_amt,tran_credit_amt,tran_status,tran_user,tran_store,tran_orgin,created_date,modified_date) values ('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','S&D INVOICE POSTING ON "+formattedDate+"','"+grandttl+"','"+grandttl+"','OPEN','"+username+"','"+store+"','SA','"+formattedDate+"','"+formattedDate+"') ";
								statement111.executeUpdate(insert_acc_header);
								
								String store_sale_acc="";
								String query19="SELECT * FROM "+db_name+".mr_store_accounts where ref_store_id='"+store+"'"; 
								Statement statement19=con.createStatement();  
						        ResultSet resultSet19 = statement19.executeQuery(query19);
						        if(resultSet19.next())
						        {
						        	store_sale_acc=resultSet19.getString("store_sale_acc");
						        	
						        }
								
						        String tax_output="";
						        String cost_sale_acc="";
						        String inv_acc="";
						        String query28="SELECT * FROM "+db_name+".mr_assign_account_group where acc_store='"+store+"' and status='1' "; 
								Statement statement28=con.createStatement();  
						        ResultSet resultSet28 = statement28.executeQuery(query28);
						        if(resultSet28.next())
						        {
						        	tax_output=resultSet28.getString("tax_output");
						        	cost_sale_acc=resultSet28.getString("cost_sale_acc");
						        	inv_acc=resultSet28.getString("inv_acc");
						        }
						      //  String ar_name="";
						        String sale_acc="";
						        String output_tax="";
						        String cost_sale="";
						        String inv_stock="";
						        
						        String query29="SELECT * FROM "+db_name+".mr_chart_accnt_mstr where acc_store='"+store+"'"; 
								Statement statement29=con.createStatement();  
						        ResultSet resultSet29= statement29.executeQuery(query29);
						        while(resultSet29.next())
						        {
//						        	if(store_ar_acc.equals(resultSet29.getString("acc_code")))
//						        	{
//						        		ar_name=resultSet29.getString("acc_name");
//						        	}
						        	if(store_sale_acc.equals(resultSet29.getString("acc_code")))
						        	{
						        		sale_acc=resultSet29.getString("acc_name");
						        	}
						        	if(tax_output.equals(resultSet29.getString("acc_code")))
						        	{
						        		output_tax=resultSet29.getString("acc_name");
						        	}
						        	if(cost_sale_acc.equals(resultSet29.getString("acc_code")))
						        	{
						        		cost_sale=resultSet29.getString("acc_name");
						        	}
						        	if(inv_acc.equals(resultSet29.getString("acc_code")))
						        	{
						        		inv_stock=resultSet29.getString("acc_name");
						        	}
						        }
						        
						        String branch="";
						        String branch_query = "select * from "+db_name+".mr_acc_segment where store_id='"+store+"' ";
								Statement statement33=con.createStatement();  
								ResultSet resultSet33 = statement33.executeQuery(branch_query);
							    if (resultSet33.next())
							    {
							    	if(resultSet33.getString("segment").equals("Branch"))
							    	{
							    		branch=resultSet33.getString("name");
							    	}
							    }
							    
							    String seqnum=null;
								String transseq = "select tran_seq from "+db_name+".mr_transaction_seq where tran_orgin='JV' ";
								Statement statement30=con.createStatement();  
								ResultSet resultSet30 = statement30.executeQuery(transseq);
							    if (resultSet30.next())
							    {
							    	seqnum=resultSet30.getString("tran_seq");
							    	String[] seq=seqnum.split("-");
							    	DateFormat df = new SimpleDateFormat("yy");
							    	String formatdDate = df.format(Calendar.getInstance().getTime());
							    	if(!seq[1].equals(formattedDate))
							    	{
							    		seq[1]=formatdDate;
							    	}
							    	seqnum=seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+seq[3];
							    	String sqnum=Integer.toString(Integer.parseInt(seq[3])+1);
							    	StringBuilder sb = new StringBuilder();
									for (int toPrepend=6-sqnum.length(); toPrepend>0; toPrepend--) {
									    sb.append('0');
									}
									sb.append(sqnum);
							    	String save_po="update "+db_name+".mr_transaction_seq set modified_date='"+formattedDate+"',tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_orgin='JV' ";
									statement111.executeUpdate(save_po);
							    }
								
							    String default_ar_acc="";
								//String acc_type=resultSet.getString("acc_type");
								String customer_master = "select * from "+db_name+".mr_customer_master where status='1' and cust_code='"+table.getValueAt(row, 8).toString()+"'";
								ResultSet resultSet46 = statement111.executeQuery(customer_master);
								String default_ar_acc_name="";
								if(resultSet46.next())
								{
									default_ar_acc=resultSet46.getString("default_ar_acc");
									if(!default_ar_acc.equals(""))
									{
										String query39="SELECT * FROM "+db_name+".mr_chart_accnt_mstr where acc_store='"+store+"' and acc_code='"+default_ar_acc+"' "; 
										Statement statement39=con.createStatement();  
						                ResultSet resultSet39= statement39.executeQuery(query39);
						                if(resultSet39.next())
						                {
						                	default_ar_acc_name=resultSet39.getString("acc_name");
						                }
									}
								}
								String accs_dt = "";
								String accs_jv="";
								String ar_leger="";
								
								
									df2=new DecimalFormat("0.00");
									//String tax_amt="5";//resultSet.getString("vat_perc");
									
									String trandate=resultSet.getString("date");
									String cust_id=resultSet.getString("cust_id");
									String custname=resultSet.getString("cust_name");
									int double_discont=Integer.parseInt(resultSet.getString("double_disc"));
									double ttl_cost=0;
//									double total_item=0;
//									String query404="SELECT count(*) as total_item FROM "+db_name+".mr_sd_lines where ref_inv_num='"+table.getValueAt(row, 0).toString()+"'"; 
//									Statement statement101=con.createStatement();  
//									ResultSet resultSet1020 = statement101.executeQuery(query404);
//									if(resultSet1020.next())
//									{
//										total_item=Double.parseDouble(resultSet1020.getString("total_item"));
//									}
									double disc_amt=0;
									
									String query2="SELECT * FROM "+db_name+".mr_sd_lines where ref_inv_num='"+table.getValueAt(row, 0).toString()+"' "; 
									Statement statement2=con.createStatement();  
									ResultSet resultSet2 = statement2.executeQuery(query2);
									while(resultSet2.next())
									{
										String total=resultSet2.getString("total");
										String barcode=resultSet2.getString("barcode");
										String unit_price="0";
										String tax=resultSet2.getString("output_tax");
										String taxrate = "0";
										String itm_code=resultSet2.getString("item_code");
										String total_qty = "0";
										String item_cost = "0";
										
										if(!tax.equals(""))
										{
											Statement statement1=con.createStatement();
											String query4="SELECT * FROM "+db_name+".mr_tax where tax_code='"+tax+"'"; 
											ResultSet resultSet4 = statement1.executeQuery(query4);
											if(resultSet4.next())
											{
												taxrate=resultSet4.getString("tax_rate");
											}
										}
										
										String query1="SELECT * FROM "+db_name+".mr_tmp_item_master where item_code='"+itm_code+"'"; 
						                Statement statement1=con.createStatement();  
						                ResultSet resultSet1 = statement1.executeQuery(query1);
						                if(resultSet1.next())
						                {
						                	item_cost=resultSet1.getString("cost");
						                	total_qty=resultSet1.getString("qty");
						                	
						                }
										String itm_cost_after_disc=resultSet2.getString("itm_cost_after_disc");
										if(double_discont==0)
										{
											/* Double discount not valid*/
											
											double unitdisc=Double.parseDouble(resultSet2.getString("itm_disc"));
											if(unitdisc==0)
											{
												String qty=resultSet2.getString("qty");
												String cf=resultSet2.getString("cf");
												//disc_perc=grand_disc;//df2.format((grand_disc/total)*100);
												String unit_total=df2.format(Double.parseDouble(itm_cost_after_disc)-disc_amt);
												String vat_val=df2.format((Double.parseDouble(unit_total)*Double.parseDouble(taxrate))/100);
												double after_subttl_disc=Double.parseDouble(unit_total);//+Double.parseDouble(vat_val);
												//unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
												unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
												String query5="update "+db_name+".mr_sd_lines set sub_ttl_disc='"+disc_amt+"',sub_cost_after_sub_ttl_disc='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl_disc='"+unit_price+"' where ref_inv_num='"+table.getValueAt(row, 0).toString()+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' and id='"+resultSet2.getString("id")+"' ";
												statement.executeUpdate(query5);
											}
											else
											{
												String qty=resultSet2.getString("qty");
												String cf=resultSet2.getString("cf");
												String unit_total=df2.format(Double.parseDouble(itm_cost_after_disc)-disc_amt);
												String vat_val=df2.format((Double.parseDouble(unit_total)*Double.parseDouble(taxrate))/100);
												double after_subttl_disc=Double.parseDouble(unit_total);//+Double.parseDouble(vat_val);
												//unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
												unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
												String query5="update "+db_name+".mr_sd_lines set sub_ttl_disc='"+disc_amt+"',sub_cost_after_sub_ttl_disc='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl_disc='"+unit_price+"' where ref_inv_num='"+table.getValueAt(row, 0).toString()+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' and id='"+resultSet2.getString("id")+"' ";
												statement.executeUpdate(query5);
											}
										}
										else
										{
											String qty=resultSet2.getString("qty");
											String cf=resultSet2.getString("cf");
											String unit_total=df2.format(Double.parseDouble(itm_cost_after_disc)-disc_amt);
											String vat_val=df2.format((Double.parseDouble(unit_total)*Double.parseDouble(taxrate))/100);
											double after_subttl_disc=Double.parseDouble(unit_total);//+Double.parseDouble(vat_val);
											//unit_price=df2.format((after_subttl_disc)/(Double.parseDouble(qty)*Double.parseDouble(cf)));
											unit_price=df2.format((after_subttl_disc+Double.parseDouble(vat_val))/(Double.parseDouble(qty)*Double.parseDouble(cf)));
											String query5="update "+db_name+".mr_sd_lines set sub_ttl_disc='"+disc_amt+"',sub_cost_after_sub_ttl_disc='"+unit_total+"',tax_val='"+vat_val+"',total='"+after_subttl_disc+"', cost_after_sub_ttl_disc='"+unit_price+"' where ref_inv_num='"+table.getValueAt(row, 0).toString()+"' and item_code='"+itm_code+"' and barcode='"+resultSet2.getString("barcode")+"' and id='"+resultSet2.getString("id")+"' ";
											statement.executeUpdate(query5);
										}
										
										
										//String avg_cst=item_cost;
										String avg_qty=total_qty;
										//$total=$itm_get->total;
										String qty=resultSet2.getString("qty");
										String cf=resultSet2.getString("cf");
										String unit=resultSet2.getString("unit");
										String sp=resultSet2.getString("sub_cost_after_sub_ttl_disc");
										String txvalue=resultSet2.getString("tax_val");
										ttl_cost+=Double.parseDouble(item_cost)*Float.parseFloat(qty)*Float.parseFloat(cf);
										float ttl_qty=Float.parseFloat(qty)*Integer.parseInt(cf);
										String new_qty=df2.format(Float.parseFloat(avg_qty)-ttl_qty);
										//double new_avg=((Double.parseDouble(avg_cst)*Float.parseFloat(avg_qty))+(Double.parseDouble(unit_price)*ttl_qty))/(Float.parseFloat(new_qty));
										//String suhail=df2.format(new_avg);
										/*
										 item master
										 item_cost=new_avg
										 qty= new_qty
										 last cost= unit_price
										 */
										String kititm_validate="select * from "+db_name+".mr_kit_items where parent_itemcode='"+itm_code+"' ";
										Statement statement125=con.createStatement();
										ResultSet resultSet125 = statement125.executeQuery(kititm_validate);
										if(resultSet125.next())
										{
											String kititm_validate1="select * from "+db_name+".mr_kit_items where parent_itemcode='"+itm_code+"' ";
											Statement statement12=con.createStatement();
											ResultSet resultSet12 = statement12.executeQuery(kititm_validate1);
											while(resultSet12.next())
											{
												String childitem=resultSet12.getString("child_itemcode");
												String query5="update "+db_name+".mr_item_master set total_qty=total_qty-'"+ttl_qty+"',modified_date='"+formattedDate+"' where item_code='"+childitem+"' ";
												statement.executeUpdate(query5);
												String query55="update "+db_name+".mr_tmp_item_master set qty=qty-'"+ttl_qty+"' where item_code='"+childitem+"' ";
												statement.executeUpdate(query55);
												/* item history */
												String query11="insert into "+db_name+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) "
														+ "values('"+itm_code+"','IN','"+table.getValueAt(row, 0).toString()+"','"+qty+"','"+unit+"','"+cf+"','"+(Double.parseDouble(item_cost)*Float.parseFloat(cf))+"','"+(Double.parseDouble(item_cost)*Float.parseFloat(qty)*Float.parseFloat(cf))+"','"+qty+"','"+(Double.parseDouble(sp)+Float.parseFloat(txvalue))+"','"+(Double.parseDouble(total)+Float.parseFloat(txvalue))+"','Sold "+table.getValueAt(row, 0).toString()+"','"+username+"','"+jv_date+"','"+barcode+"','"+cust_id+"','"+custname+"')";
												statement.executeUpdate(query11);
											}
											/* item history */
											String query11="insert into "+db_name+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) "
													+ "values('"+itm_code+"','IN','"+table.getValueAt(row, 0).toString()+"','"+qty+"','"+unit+"','"+cf+"','"+(Double.parseDouble(item_cost)*Float.parseFloat(cf))+"','"+(Double.parseDouble(item_cost)*Float.parseFloat(qty)*Float.parseFloat(cf))+"','"+qty+"','"+(Double.parseDouble(sp)+Float.parseFloat(txvalue))+"','"+(Double.parseDouble(total)+Float.parseFloat(txvalue))+"','Sold "+table.getValueAt(row, 0).toString()+"','"+username+"','"+jv_date+"','"+barcode+"','"+cust_id+"','"+custname+"')";
											statement.executeUpdate(query11);
										}
										else
										{
											String query5="update "+db_name+".mr_item_master set total_qty='"+new_qty+"',modified_date='"+formattedDate+"' where item_code='"+itm_code+"' ";
											statement.executeUpdate(query5);
											String query55="update "+db_name+".mr_tmp_item_master set qty='"+new_qty+"' where item_code='"+itm_code+"' ";
											statement.executeUpdate(query55);
											/* item history */
											String query11="insert into "+db_name+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) "
													+ "values('"+itm_code+"','IN','"+table.getValueAt(row, 0).toString()+"','"+qty+"','"+unit+"','"+cf+"','"+(Double.parseDouble(item_cost)*Float.parseFloat(cf))+"','"+(Double.parseDouble(item_cost)*Float.parseFloat(qty)*Float.parseFloat(cf))+"','"+qty+"','"+(Double.parseDouble(sp)+Float.parseFloat(txvalue))+"','"+(Double.parseDouble(total)+Float.parseFloat(txvalue))+"','Sold "+table.getValueAt(row, 0).toString()+"','"+username+"','"+jv_date+"','"+barcode+"','"+cust_id+"','"+custname+"')";
											statement.executeUpdate(query11);
										}
										
										
										
										
										
										
										
										
										/*
										link items to customer
										*/
										String query10="SELECT * FROM "+db_name+".mr_customer_items where ref_itemcode='"+itm_code+"' and ref_cust_id='"+cust_name+"' and status='0' and unit='"+resultSet2.getString("unit")+"' "; 
										ResultSet resultSet15 = statement.executeQuery(query10);
										if(!resultSet15.next())
										{
											String query13="insert into "+db_name+".mr_customer_items(`ref_cust_id`, `ref_itemcode`,price, `created_date`,modified_date,status,unit,barcode,invoice_num) VALUES ('"+cust_name+"','"+itm_code+"','"+resultSet2.getString("unit_cost")+"','"+formattedDate+"','"+formattedDate+"','0','"+resultSet2.getString("unit")+"','"+resultSet2.getString("barcode")+"','"+table.getValueAt(row, 0).toString()+"')";
											statement.executeUpdate(query13);
										}
										else
										{
											String query13="update "+db_name+".mr_customer_items set status='1',modified_date='"+formattedDate+"' where ref_cust_id='"+cust_name+"' and ref_itemcode='"+itm_code+"' and unit='"+resultSet2.getString("unit")+"'";
											statement.executeUpdate(query13);
											String query14="insert into "+db_name+".mr_customer_items(`ref_cust_id`, `ref_itemcode`,price, `created_date`,modified_date,status,unit,barcode,invoice_num) VALUES ('"+cust_name+"','"+itm_code+"','"+resultSet2.getString("unit_cost")+"','"+formattedDate+"','"+formattedDate+"','0','"+resultSet2.getString("unit")+"','"+resultSet2.getString("barcode")+"','"+table.getValueAt(row, 0).toString()+"')";
											statement.executeUpdate(query14);
										}
									}
									String query5="insert into "+db_name+".mr_sd_tax(`inv_num`, `total_amt`, `tax_amount`, `tran_date`, `remark`, `created_date`) values ('"+table.getValueAt(row, 0).toString()+"','"+ttl_price+"','"+total_vat+"','"+trandate+"','POSTED BY "+username+"','"+formattedDate+"')";
									statement.executeUpdate(query5);
									
									String query4="SELECT * FROM "+db_name+".mr_customer_master  where cust_code='"+cust_name+"'"; 
									Statement statement1=con.createStatement();  
									ResultSet resultSet1 = statement1.executeQuery(query4);
									if(resultSet1.next())
									{
										double avail_credit=Double.parseDouble(resultSet1.getString("avail_credit"));
										double advance_amt=Double.parseDouble(resultSet1.getString("advance_amt"));
										double adv_bal=0;
										double inv_ttl=ttl_amt;
										if(advance_amt>0)
										{
											if(advance_amt>= inv_ttl)
											{
												adv_bal=advance_amt-inv_ttl;
												inv_ttl=0;
											}
											else
											{
												inv_ttl=inv_ttl-advance_amt;
												adv_bal=0;
											}
											
										}
										
										double new_credit=avail_credit-(inv_ttl);
										BigDecimal bd = new BigDecimal(new_credit);
			                    		bd = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
			                    		new_credit = bd.doubleValue();
										String query16="update "+db_name+".mr_customer_master set advance_amt='"+adv_bal+"',avail_credit='"+new_credit+"',last_sale_date='"+formattedDate+"' where cust_code='"+cust_name+"'"; 
										statement.executeUpdate(query16);
									}
									
									
								    InetAddress addr = null;
								    try {
										addr = InetAddress.getLocalHost();
									} catch (UnknownHostException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								    String sys_name = addr.getHostName();
								    
								    BigDecimal bd = new BigDecimal(ttl_cost);
		                    		bd = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
		                    		ttl_cost = bd.doubleValue();
								    
		                    		accs_dt+=",('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','','"+default_ar_acc+"','"+default_ar_acc_name+"','"+ttl_amt+"','0','A/R COLLECTED AT ON  "+formattedDate+"','"+formattedDate+"','"+formattedDate+"')";
							    	accs_jv+=",('"+seqnum+"','"+seqnum+"','"+default_ar_acc+"','"+default_ar_acc_name+"','"+ttl_amt+"','0','"+seq_num+"','"+inv_no+"-"+cust_id+"-"+custname+"','"+jv_date+"','"+formattedDate+"','OPEN','"+formattedDate+"')";
							    	
							    	String disc_applied="";
					                String disc_jv="";
					                String disc_leger="";
								    if(disc!=0)
								    {
								    	
						                
						                String query18="SELECT * FROM "+db_name+".mr_chart_accnt_mstr where acc_grp_name='DISCOUNT ON SALES' and acc_store='"+store+"'"; 
										Statement statement18=con.createStatement();  
						                ResultSet resultSet18= statement18.executeQuery(query18);
						                String disc_name="";
						                String disc_code="";
						                if(resultSet18.next())
						                {
						                	disc_name=resultSet18.getString("acc_name");
						                	disc_code=resultSet18.getString("acc_code");
						                }
						                disc_applied=",('"+seqnum+"','"+formattedDate+"','"+times.format(cal.getTime())+"','EXPENSE','"+disc_code+"','"+disc_name+"','"+disc+"','0','DISCOUNT APPLIED ON CUSTOMER PAYMENT ','"+formattedDate+"','"+formattedDate+"')";
						                disc_jv=",('"+seqnum+"','"+seqnum+"','"+disc_code+"','"+disc_name+"','"+disc+"','0','"+seq_num+"','"+inv_no+"-"+cust_id+"-"+custname+"','"+jv_date+"','"+formattedDate+"','OPEN','"+formattedDate+"')";
						                
						                String query219="SELECT * FROM "+db_name+".mr_acc_ledger where ac_code='"+disc_code+"'"; 
										Statement statement9=con.createStatement();  
						                ResultSet resultSet9= statement9.executeQuery(query219);
						                float cr_disc=0;
						                float dr_disc=0;
						                while(resultSet9.next())
						                {
						                	cr_disc+=Float.parseFloat(resultSet9.getString("cr_amt"));
						                	dr_disc+=Float.parseFloat(resultSet9.getString("dr_amt"));
						                }
										float bal_disc=dr_disc-cr_disc;
										double bal_discount=bal_disc+disc;
										disc_leger=",('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+disc_code+"','"+disc+"','0','"+bal_discount+"','"+seq_num+"','"+seq_num+"','AR','"+formattedDate+"','"+formattedDate+"','OPEN' )";
						                
								    }
							    	
							    	String chrt_act_mstr = "select * from "+db_name+".mr_chart_accnt_mstr where acc_code='"+default_ar_acc+"' and acc_store='"+store+"' ";
									ResultSet resultSet6 = statement111.executeQuery(chrt_act_mstr);
									if (resultSet6.next())
								    {
										String query37="SELECT * FROM "+db_name+".mr_acc_ledger where ac_code='"+resultSet6.getString("acc_code")+"'"; 
										Statement statement37=con.createStatement();  
						                ResultSet resultSet37= statement37.executeQuery(query37);
						                float cr=0;
						                float dr=0;
						                while(resultSet37.next())
						                {
						                	cr+=Float.parseFloat(resultSet37.getString("cr_amt"));
						                	dr+=Float.parseFloat(resultSet37.getString("dr_amt"));
						                }
						                float bal_asset=dr-cr;
										double bal=bal_asset+ttl_amt;
										ar_leger+=",('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+resultSet6.getString("acc_code")+"','"+ttl_amt+"','0','"+bal+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' )";
								    }
		                    		
								    String insert_acc_detls="insert into "+db_name+".mr_accounts_tran_detls (tran_seqence_code,tran_date,tran_time,tran_type,tran_accode,tran_acname,tran_debit_amt,tran_credit_amt,tran_remark,created_date,modified_date) values"
											+ "('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','ASSET','"+inv_acc+"','"+inv_stock+"','0','"+ttl_cost+"','POS SALES CLOSING ON "+formattedDate+"','"+formattedDate+"','"+formattedDate+"'),"
											+ "('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','EXPENSE','"+cost_sale_acc+"','"+cost_sale+"','"+ttl_cost+"','0','SOLD INVENTORY' ,'"+formattedDate+"','"+formattedDate+"'),"
											+ "('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','INCOME','"+store_sale_acc+"','"+sale_acc+"','0','"+ttl_price+"','SALES  ON "+formattedDate+"','"+formattedDate+"','"+formattedDate+"'),"
											+ "('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','LIABILITY','"+tax_output+"','"+output_tax+"','0','"+total_vat+"','VAT COLLECTED  ON "+formattedDate+"','"+formattedDate+"','"+formattedDate+"') "
											+ ""+accs_dt+" "
											+ ""+disc_applied+"";
									statement111.executeUpdate(insert_acc_detls);
								    
									String save_jv="insert into "+db_name+".mr_jv_header( `jv_num`, `date`, `branch`, `username`, `doc_num`, `remark`, `notes`, `store_id`, `created_date`, `modified_date`, `status`,`is_approved`, `approved_by`, `is_posted`, `posted_by`, `posted_date`, `approved_date`,jv_amt,vendor_cust_id) "
											+ "values('"+seqnum+"','"+formattedDate+"','"+branch+"','"+username+"','"+seq_num+"','"+inv_no+"-"+cust_id+"-"+custname+"','"+seq_num+"','"+store+"','"+formattedDate+"','"+formattedDate+"','Posted','1','"+username+"','1','"+username+"','"+formattedDate+"','"+formattedDate+"','"+grandttl+"','"+cust_id+"' )";
									statement.executeUpdate(save_jv);
									
									String queryjd="insert into "+db_name+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,cheque_date) VALUES "
											+ "('"+seqnum+"','"+seqnum+"','"+inv_acc+"','"+inv_stock+"','0','"+ttl_cost+"','"+seq_num+"','"+inv_no+"-"+cust_id+"-"+custname+"','"+jv_date+"','"+formattedDate+"','OPEN','"+formattedDate+"'),"
											+ "('"+seqnum+"','"+seqnum+"','"+cost_sale_acc+"','"+cost_sale+"','"+ttl_cost+"','0','"+seq_num+"','"+inv_no+"-"+cust_id+"-"+custname+"','"+jv_date+"','"+formattedDate+"','OPEN','"+formattedDate+"'),"
											+ "('"+seqnum+"','"+seqnum+"','"+store_sale_acc+"','"+sale_acc+"','0','"+ttl_price+"','"+seq_num+"','"+inv_no+"-"+cust_id+"-"+custname+"','"+jv_date+"','"+formattedDate+"','OPEN','"+formattedDate+"'),"
											+ "('"+seqnum+"','"+seqnum+"','"+tax_output+"','"+output_tax+"','0','"+total_vat+"','"+seq_num+"','"+inv_no+"-"+cust_id+"-"+custname+"','"+jv_date+"','"+formattedDate+"','OPEN','"+formattedDate+"')"
											+ " "+accs_jv+""
											+ ""+disc_jv+"";
									statement.executeUpdate(queryjd);
									
									String query38="SELECT * FROM "+db_name+".mr_acc_ledger where ac_code='"+inv_acc+"'"; 
									Statement statement38=con.createStatement();  
							        ResultSet resultSet38= statement38.executeQuery(query38);
							        float cr_pay=0;
							        float dr_pay=0;
							        while(resultSet38.next())
							        {
							        	cr_pay+=Float.parseFloat(resultSet38.getString("cr_amt"));
							        	dr_pay+=Float.parseFloat(resultSet38.getString("dr_amt"));
							        }
									float bal_liable=dr_pay-cr_pay;
									double bal_lia=bal_liable-ttl_cost;
									
									String query39="SELECT * FROM "+db_name+".mr_acc_ledger where ac_code='"+cost_sale_acc+"'"; 
									Statement statement39=con.createStatement();  
							        ResultSet resultSet39= statement39.executeQuery(query39);
							        float cr_pay9=0;
							        float dr_pay9=0;
							        while(resultSet39.next())
							        {
							        	cr_pay9+=Float.parseFloat(resultSet39.getString("cr_amt"));
							        	dr_pay9+=Float.parseFloat(resultSet39.getString("dr_amt"));
							        }
									float bal_cost=dr_pay9-cr_pay9;
									double bal_cost_ttl=bal_cost+ttl_cost;
									
									
									String query37="SELECT * FROM "+db_name+".mr_acc_ledger where ac_code='"+store_sale_acc+"'"; 
									Statement statement37=con.createStatement();  
							        ResultSet resultSet37= statement37.executeQuery(query37);
							        float cr=0;
							        float dr=0;
							        while(resultSet37.next())
							        {
							        	cr+=Float.parseFloat(resultSet37.getString("cr_amt"));
							        	dr+=Float.parseFloat(resultSet37.getString("dr_amt"));
							        }
									float bal_asset=dr-cr;
									double bal=bal_asset-ttl_price;
									
									String query41="SELECT * FROM "+db_name+".mr_acc_ledger where ac_code='"+tax_output+"'"; 
									Statement statement41=con.createStatement();  
							        ResultSet resultSet41= statement41.executeQuery(query41);
							        float cr_pay41=0;
							        float dr_pay41=0;
							        while(resultSet41.next())
							        {
							        	cr_pay41+=Float.parseFloat(resultSet41.getString("cr_amt"));
							        	dr_pay41+=Float.parseFloat(resultSet41.getString("dr_amt"));
							        }
									float baltax=dr_pay41-cr_pay41;
									double baltax_ttl=baltax-total_vat;
									
									String save_ledger="insert into "+db_name+".mr_acc_ledger (`jv_num`, `tran_date`, `post_date`, `ac_code`, `dr_amt`, `cr_amt`, `balance`, `notes`, `remark`, `source`, `created_date`, `modified_date`,status) values "
											+ "('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+cost_sale_acc+"','"+ttl_cost+"','0','"+bal_cost_ttl+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' ),"
											+ "('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+store_sale_acc+"','0','"+ttl_price+"','"+bal+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' ),"
											+ "('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+inv_acc+"','0','"+ttl_cost+"','"+bal_lia+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' ),"
											+ "('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+tax_output+"','0','"+total_vat+"','"+baltax_ttl+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' ) "
											+ ""+ar_leger+""
											+ ""+disc_leger+"";
									statement.executeUpdate(save_ledger);
								    
									String update_pl="update "+db_name+".mr_sd_list set status='POSTED' where invoice_num ='"+table.getValueAt(row, 0).toString()+"' ";
									statement.executeUpdate(update_pl);
								    
									String closepay="INSERT INTO "+db_name+".mr_close_pay_list_history(`ref_id`, `ref_reg_num`, `username`, `store`, `computer`, `date_time`, `invoice_num`, `remarks`, `total_sale_amt`, `total_vat`, `total_disc`, `total_cost`, `created_date`,sales_amt2,close_date,pay_code1,pay_code2,pay_code3,pay_code4,pay_code5,pay_code6,pay_code7,status) SELECT `id`, 'SND', `user_id`,store_name, '"+sys_name+"', '"+formattedDate+" "+times.format(cal.getTime())+"', '1', 'S&D INVOICE - "+table.getValueAt(row, 0).toString()+"', `total_amt`, `total_vat`, `disc`,'"+ttl_cost+"' ,'"+formattedDate+"',((total_amt-disc)+total_vat), '"+jv_date+"','CASH','A/R','','VISA','OMANI RIYAL','SAUDI RIYAL','US$','1'  FROM "+db_name+".mr_sd_list WHERE invoice_num ='"+table.getValueAt(row, 0).toString()+"' ";
									statement.executeUpdate(closepay);
									
									String closepayitems="INSERT INTO "+db_name+".mr_close_pay_list_history_items(`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `ex_rate`, `settle_amt`, `varience`, `sale_amt`, `vat_amt`, `created_date`, `status`) SELECT 'SND','"+formattedDate+" "+times.format(cal.getTime())+"','A/R','0',((total_amt-disc)+total_vat),((total_amt-disc)+total_vat),'1','0',((total_amt-disc)+total_vat),total_amt,total_vat,created_date,'1'  FROM "+db_name+".mr_sd_list WHERE invoice_num ='"+table.getValueAt(row, 0).toString()+"' ";
									statement.executeUpdate(closepayitems);
									
									String history="INSERT INTO "+db_name+".mr_sd_lines_history(`ref_inv_num`, `barcode`, `item_code`, `item_name`, `tax_type`, `output_tax`, `qty`, `unit`, `cf`, `unit_cost`, `itm_disc`, `itm_cost_after_disc`, `sub_ttl_disc`, `sub_cost_after_sub_ttl_disc`, `tax_val`, `total`, `cost_after_sub_ttl_disc`, `created_date`, `modified_date`, `remark`, `item_details`, `item_cost`) SELECT `ref_inv_num`, `barcode`, `item_code`, `item_name`, `tax_type`, `output_tax`, `qty`, `unit`, `cf`, `unit_cost`, `itm_disc`, `itm_cost_after_disc`, `sub_ttl_disc`, `sub_cost_after_sub_ttl_disc`, `tax_val`, `total`, `cost_after_sub_ttl_disc`, `created_date`, `modified_date`, `remark`, `item_details`, `item_cost` FROM "+db_name+".mr_sd_lines WHERE ref_inv_num ='"+table.getValueAt(row, 0).toString()+"' ";
									statement.executeUpdate(history);
									
									String pohistory="INSERT INTO "+db_name+".mr_sd_list_history(  `date`, `user_id`, `invoice_num`, `remark`, `total_amt`, `disc`, `total_vat`, `cust_id`, `cust_addr`, `double_disc`, `status`, `referance`, `store_name`, `created_date`, `modified_date`, `type`,cust_name,salesman,paymentmethod) SELECT  `date`, `user_id`, `invoice_num`, `remark`, `total_amt`, `disc`, `total_vat`, `cust_id`, `cust_addr`, `double_disc`, `status`, `referance`, `store_name`, `created_date`, `modified_date`, `type`,cust_name,salesman,paymentmethod FROM "+db_name+".mr_sd_list WHERE invoice_num ='"+table.getValueAt(row, 0).toString()+"' ";
									statement.executeUpdate(pohistory);
									
									String delete_do_list="delete from "+db_name+".mr_delivery_list where invoice_num ='"+table.getValueAt(row, 0).toString()+"' ";
									statement.executeUpdate(delete_do_list);
									
									String delete_do_note="delete from "+db_name+".mr_delivery_note where ref_inv_num ='"+table.getValueAt(row, 0).toString()+"'  ";
									statement.executeUpdate(delete_do_note);
									
									String delete_pl="delete from "+db_name+".mr_sd_list where invoice_num ='"+table.getValueAt(row, 0).toString()+"' ";
									statement.executeUpdate(delete_pl);
									
									String delete_po="delete from "+db_name+".mr_sd_lines where ref_inv_num ='"+table.getValueAt(row, 0).toString()+"' ";
									statement.executeUpdate(delete_po);
									frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					                //JOptionPane.showMessageDialog(frame, "S&D Posted Successfully");
									
									if(table.getValueAt(row, 9).toString().equals("CASH"))
									{
										int cofrm = JOptionPane.showConfirmDialog(frame, "Do you want payment for this invoice?","Warning",JOptionPane.YES_NO_OPTION);
								        // 0=yes, 1=no, 2=cancel
								        if(cofrm==0)
								        {
								        	Object[] item_list = new String[1];
											item_list[0]=table.getValueAt(row, 0).toString();
											new cust_payment_voucher(db_name,username,store,item_list,Double.parseDouble(table.getValueAt(row, 1).toString()),table.getValueAt(row, 8).toString(),table.getValueAt(row, 2).toString(), version,decimal,"sd");
								        }
								        else
								        {
								        	JOptionPane.showMessageDialog(frame, "S&D Invoice Posted Successfully");
								        	sales_distribution.sd_list.get_po_disp(db_name, "OPEN", "",comboBox.getSelectedItem().toString());	
								        }
									}
									else
							        {
							        	JOptionPane.showMessageDialog(frame, "S&D Invoice Posted Successfully");
							        	sales_distribution.sd_list.get_po_disp(db_name, "OPEN", "",comboBox.getSelectedItem().toString());	
							        }
									
								}
							}
								
						}
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}
				else
				{
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					try {
						statement = con.createStatement();
						
					    String update_pl="update "+db_name+".mr_sd_list set status='POSTED' where invoice_num ='"+table.getValueAt(row, 0).toString()+"' ";
						statement.executeUpdate(update_pl);
						
						String history="INSERT INTO "+db_name+".mr_sd_lines_history( `ref_inv_num`, `barcode`, `item_code`, `item_name`, `tax_type`, `output_tax`, `qty`, `unit`, `cf`, `unit_cost`, `itm_disc`, `itm_cost_after_disc`, `sub_ttl_disc`, `sub_cost_after_sub_ttl_disc`, `tax_val`, `total`, `cost_after_sub_ttl_disc`, `created_date`, `modified_date`, `remark`, `item_details`, `item_cost`) SELECT  `ref_inv_num`, `barcode`, `item_code`, `item_name`, `tax_type`, `output_tax`, `qty`, `unit`, `cf`, `unit_cost`, `itm_disc`, `itm_cost_after_disc`, `sub_ttl_disc`, `sub_cost_after_sub_ttl_disc`, `tax_val`, `total`, `cost_after_sub_ttl_disc`, `created_date`, `modified_date`, `remark`, `item_details`, `item_cost` FROM "+db_name+".mr_sd_lines WHERE ref_inv_num ='"+table.getValueAt(row, 0).toString()+"' ";
						statement.executeUpdate(history);
						
						String pohistory="INSERT INTO "+db_name+".mr_sd_list_history(  `date`, `user_id`, `invoice_num`, `remark`, `total_amt`, `disc`, `total_vat`, `cust_id`, `cust_addr`, `double_disc`, `status`, `referance`, `store_name`, `created_date`, `modified_date`, `type`,cust_name, `invoice_ref`,salesman,paymentmethod) SELECT  `date`, `user_id`, `invoice_num`, `remark`, `total_amt`, `disc`, `total_vat`, `cust_id`, `cust_addr`, `double_disc`, `status`, `referance`, `store_name`, `created_date`, `modified_date`, `type`,cust_name, `invoice_ref`,salesman,paymentmethod FROM "+db_name+".mr_sd_list WHERE invoice_num ='"+table.getValueAt(row, 0).toString()+"' ";
						statement.executeUpdate(pohistory);
						
						String delete_pl="delete from "+db_name+".mr_sd_list where invoice_num ='"+table.getValueAt(row, 0).toString()+"' ";
						statement.executeUpdate(delete_pl);
						
						String delete_po="delete from "+db_name+".mr_sd_lines where ref_inv_num ='"+table.getValueAt(row, 0).toString()+"' ";
						statement.executeUpdate(delete_po);
						
						con.close();
						
						
						JOptionPane.showMessageDialog(frame, "S&D Quotaion Posted Successfully");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						
		                
		                
		                sales_distribution.sd_list.get_po_disp(db_name, "OPEN", "",comboBox.getSelectedItem().toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
					
				}
        	}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void  get_po_disp( String company, String type, String search,String sdtypes)
	{
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and (invoice_num LIKE '%"+search+"%' OR cust_id LIKE '%"+search+"%' OR cust_name LIKE '%"+search+"%' OR salesman LIKE '%"+search+"%') ";
		}
		String sdtype="";
		if(sdtypes.equals("INVOICE"))
		{
			sdtype="and type='sd_invoice'";
		}
		if(sdtypes.equals("QUOTATION"))
		{
			sdtype="and type='sd_quotation'";
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_sd_list where status='"+type+"'  "+serch_str+" "+sdtype+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			double ttl_amt=0;
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+company+".mr_sd_list where status='"+type+"' "+serch_str+"  "+sdtype+" order by id desc "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String invoice_num=resultSet1.getString("invoice_num");
	                String total_amt=resultSet1.getString("total_amt");
	                String cust_id=resultSet1.getString("cust_id");
	                String cust_name=resultSet1.getString("cust_name");
	                String date=resultSet1.getString("date");
	                String types=resultSet1.getString("type");
	                String salesman=resultSet1.getString("salesman");
	                String remark=resultSet1.getString("remark");
	                String referance=resultSet1.getString("referance");
	                String total_vat=resultSet1.getString("total_vat");
	                String disc=resultSet1.getString("disc");
	                String paymentmethod=resultSet1.getString("paymentmethod");
	                double ttl=Double.parseDouble(total_amt)+Double.parseDouble(total_vat)-Double.parseDouble(disc);
	                BigDecimal bz = new BigDecimal(ttl);
		    		bz = bz.setScale(2, BigDecimal.ROUND_HALF_UP);
		    		ttl = bz.doubleValue();
		    		ttl_amt=ttl_amt+ttl;
	                model.addRow(new Object[]{invoice_num,ttl,cust_name,date,salesman,referance,remark,types,cust_id,paymentmethod});
				}
	        	btnProceed.setEnabled(true);
	        	BigDecimal bz = new BigDecimal(ttl_amt);
	    		bz = bz.setScale(2, BigDecimal.ROUND_HALF_UP);
	    		ttl_amt = bz.doubleValue();
	        	textField_1.setText(Double.toString(ttl_amt));
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Sales and Distribution List Found");
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