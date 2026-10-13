package accounts_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Notelist {

	public static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Notelist window = new Notelist("mr_pro","admin","S01", null,"2","","","","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param vendorname 
	 * @param vendorcode 
	 */
	public Notelist(String db_name, String username,String store,String version,String decimal, String pagetype, String vendorcode, String vendorname,String language) throws JSONException {
		initialize(db_name,username,store, version,decimal, pagetype,vendorcode,vendorname,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param decimal 
	 * @param vendorname 
	 * @param vendorcode 
	 * @param language 
	 * @param page 
	 */
	private void initialize(final String db_name,final String username,final String store,final String version, final String decimal, final String pagetype, final String vendorcode, final String vendorname, final String language) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 1023, 748);
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
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 1017, 96);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image printer = new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		JLabel lblItemMasterList = new JLabel(pagetype+" List");
//		if(language.equals("Arabic"))
//		{
//			lblItemMasterList.setText("„Œ—Ã");
//		}
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(10, 11, 199, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				CafePro.Mainpage.btnPurchaseReturn.setEnabled(true);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(857, 11, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		if(language.equals("Arabic"))
		{
			menuItem_1.setText("Ì‰⁄‘");
		}
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name, "OPEN", "",pagetype);
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(927, 11, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JButton button = new JButton("New Voucher");
		if(language.equals("Arabic"))
		{
			button.setText("ﬁ”Ì„… ÃœÌœ…");
		}
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new accounts_master.Debitnote(db_name,username,store,vendorcode,vendorname, version,decimal,pagetype, language);
			}
		});
		button.setBounds(645, 24, 132, 25);
		panel.add(button);
		
		JButton button_1 = new JButton("Void Voucher");
		if(language.equals("Arabic"))
		{
			button_1.setText("ﬁ”Ì„… »«ÿ·…");
		}
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(frame, "Select A Row Õœœ ’›");
				}
				else
				{
					int a= JOptionPane.showConfirmDialog(frame, "Do You Want to Void This Voucher Â·  —Ìœ ≈·€«¡ Â–Â «·ﬁ”Ì„…ø","Warning",JOptionPane.YES_NO_OPTION);
					if(a==0)
					{
						try{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();
							Statement statement1=con.createStatement();
							
							String invoice="";
							String[] invoicelist=null;
							String query234="SELECT * FROM "+db_name+".mr_vendor_payment_details where trans_num!='"+model.getValueAt(row, 1).toString()+"'"; 
							ResultSet resultSet234 = statement.executeQuery(query234);
							while(resultSet234.next())
							{
								if(!(resultSet234.getString("note")==null))
								{
									invoice += resultSet234.getString("note");
								}
								
								
							}
							invoicelist = invoice.split(",");
							
							if(Arrays.asList(invoicelist).contains(model.getValueAt(row, 1).toString()))
							{
								JOptionPane.showMessageDialog(frame, "This Voucher already in Payment Â–Â «·ﬁ”Ì„… ﬁÌœ «·œ›⁄ »«·›⁄·");
							}
							else
							{
								String query="SELECT * FROM "+db_name+".mr_vendor_payment_details where trans_num='"+model.getValueAt(row, 1).toString()+"'"; 
								ResultSet resultSet = statement.executeQuery(query);
								if(resultSet.next())
								{
									
									//String cr=resultSet.getString("cr_amt");
									
											
									String update_vendor="update "+db_name+".mr_vendor_payment_details set status='VOID' where trans_num ='"+model.getValueAt(row, 1).toString()+"' ";
									statement1.executeUpdate(update_vendor);
									if(resultSet.getString("payment_method").equals("BANK"))
									{
										String update_chque="update "+db_name+".mr_cheque_details set status='VOID' where tran_num ='"+model.getValueAt(row, 1).toString()+"' ";
										statement1.executeUpdate(update_chque);
									}
									
									String select_vendor="select * from "+db_name+".mr_vendor where vendor_code='"+model.getValueAt(row, 2).toString()+"' ";
									ResultSet resultSet1 = statement.executeQuery(select_vendor);
									double new_paid=0;
									double new_pending=0;
									if(resultSet1.next())
									{
										String old_paid=resultSet1.getString("vendor_paid_amt");
										String old_pending=resultSet1.getString("vendor_pending_amt");
										new_paid=Double.parseDouble(old_paid)-(Double.parseDouble(model.getValueAt(row, 4).toString()));
										new_pending=Double.parseDouble(old_pending)+(Double.parseDouble(model.getValueAt(row, 4).toString()));
										
										BigDecimal bp = new BigDecimal(new_paid);
				                		bp = bp.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
				                		new_paid = bp.doubleValue();
				                		
				                		BigDecimal bl = new BigDecimal(new_pending);
				                		bl= bl.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
				                		new_pending = bl.doubleValue();
									}
									
									String updates="update "+db_name+".mr_vendor set vendor_pending_amt='"+new_pending+"',vendor_paid_amt='"+new_paid+"' where vendor_code = '"+model.getValueAt(row, 2).toString()+"' ";
									statement1.executeUpdate(updates);
									
									String querys="SELECT * FROM "+db_name+".mr_vendor_payment_details  where trans_num ='"+model.getValueAt(row, 1).toString()+"' "; 
									ResultSet resultSet12 = statement.executeQuery(querys);
									while(resultSet12.next())
									{
										String po_num=resultSet12.getString("ref_po_num");
										String query11="SELECT * FROM "+db_name+".mr_vendor_payment  where ref_po_num ='"+po_num+"' "; 
										ResultSet resultSet11 = statement1.executeQuery(query11);
										if(resultSet11.next())
										{
					                		String update="update "+db_name+".mr_vendor_payment set status='4' where ref_po_num='"+po_num+"' ";
											statement1.executeUpdate(update);
											
											
										}
									}
									JOptionPane.showMessageDialog(frame, "Void Successfully  „ «·›—«€ »‰Ã«Õ");
									get_po_disp( db_name,"OPEN",textField.getText(),pagetype);
								}
							}
							
							
							
							
						}
						catch(Exception e){
							System.err.println(e);
						}
					}
				}
			}
		});
		button_1.setBounds(503, 24, 132, 25);
		panel.add(button_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Print");
		if(language.equals("Arabic"))
		{
			menuItem_2.setText("„ÿ»⁄…");
		}
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(787, 11, 60, 48);
		menuItem_2.setIcon(new ImageIcon(printer));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row>-1)
				{
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					int rows = table.getSelectedRow();
                	String inv_num = table.getModel().getValueAt(rows, 1).toString();
					try {
						Connection con=Dbsetup.dbconn.getConnection();
						report_master.DebitVoucher.report(con,inv_num,db_name,pagetype);
						con.close();
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Select A Row from Table Õœœ ’›« „‰ «·ÃœÊ·");
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		panel.add(menuItem_2);
		
		JButton btnChequeList = new JButton("Cheque List");
		if(language.equals("Arabic"))
		{
			btnChequeList.setText("ﬁ«∆„… «· Õﬁﬁ");
		}
		btnChequeList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new accounts_master.cheque_list (db_name,version,username,store, language);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnChequeList.setBounds(361, 24, 132, 25);
		panel.add(btnChequeList);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 95, 1005, 605);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 88, 981, 504);
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
		    if(language.equals("Arabic"))
			{
		    	model.addColumn("«·—ﬁ„ «· ”·”·Ì");
			    model.addColumn("—ﬁ„ «·›« Ê—…"); 
			    model.addColumn("—„“ «·»«∆⁄");
			    model.addColumn("«”„ «·»«∆⁄");
			    model.addColumn("ﬂ„Ì…");
			    model.addColumn(" «—ÌŒ «·≈’œ«—");
			    model.addColumn("‰Ê⁄ «·œ›⁄");
			    model.addColumn("«”„ «·»‰ﬂ");
			    model.addColumn("—ﬁ„ «·‘Ìﬂ");
			    model.addColumn("«· Õﬁﬁ „‰ «· «—ÌŒ");
			}
		    else
		    {
		    	model.addColumn("Sl	 No.");
			    model.addColumn("Invoice No."); 
			    model.addColumn("Vendor Code");
			    model.addColumn("Vendor Name");
			    model.addColumn("amount");
			    model.addColumn("Issue Date");
			    model.addColumn("Pay Type");
			    model.addColumn("Bank Name");
			    model.addColumn("Cheque No.");
			    model.addColumn("Cheque Date");
		    }
		    
		scrollPane.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(40);
			  if (i==1) column.setPreferredWidth(140);
			  if (i==2) column.setPreferredWidth(140);
			  if (i==3) column.setPreferredWidth(140);
			  if (i==4) column.setPreferredWidth(140);
			  if (i==5) column.setPreferredWidth(140);
			  if (i==6) column.setPreferredWidth(140);
			  if (i==7) column.setPreferredWidth(140);
			  if (i==8) column.setPreferredWidth(140);
			  if (i==9) column.setPreferredWidth(140);
			  if (i==10) column.setPreferredWidth(140);
			  if (i==11) column.setPreferredWidth(140);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 981, 52);
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
				  get_po_disp( db_name,"OPEN",textField.getText(),pagetype);
			  }
			});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					get_po_disp( db_name,"OPEN",textField.getText(),pagetype);
				}
			}
		});
		textField.setBounds(12, 13, 719, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		if(language.equals("Arabic"))
		{
			btnSearch.setText("Ì»ÕÀ");
		}
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name,"OPEN",textField.getText(),pagetype);
			}
		});
		btnSearch.setBounds(743, 13, 97, 25);
		panel_2.add(btnSearch);
		
		JButton btnVoidPoList = new JButton("Voided List");
		if(language.equals("Arabic"))
		{
			btnVoidPoList.setText("ﬁ«∆„… »«ÿ·…");
		}
		btnVoidPoList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new accounts_master.ap_void_list(db_name,username,store, version,decimal, language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnVoidPoList.setBounds(852, 13, 117, 25);
		panel_2.add(btnVoidPoList);
		//comboBox.addItem("DELIVERY");
		 
//        table.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent mouseEvent) {
//                JTable table =(JTable) mouseEvent.getSource();
//                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
//                	int column = 0;
//                	int rows = table.getSelectedRow();
//                	String value = table.getModel().getValueAt(rows, column).toString();
//                	try{
//            			
//	 					new sales_distribution.sd_lines(db_name, username, table.getModel().getValueAt(rows, 2).toString(), value, store, version,decimal);
//	 					//sd_lines.frame.setAlwaysOnTop(true);
//                	}
//                	catch (JSONException | SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//                }
//                
//            }
//        });
        
        get_po_disp( db_name,"OPEN",textField.getText(),pagetype);
        
	}
	static void  get_po_disp( String company, String type, String search, String Pagetype)
	{
		String inv_type="";
		if(Pagetype.equals("Debit Note"))
		{
			inv_type="DN";
		}
		else
		{
			inv_type="CN";
		}
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and trans_num LIKE '%"+search+"%' OR ref_vendor_id LIKE '%"+search+"%' or pay_to like '%"+search+"%' ";
		}
		
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_vendor_payment_details where invoicetype='"+inv_type+"' and status='"+type+"' "+serch_str+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+company+".mr_vendor_payment_details where invoicetype='"+inv_type+"' and status='"+type+"' "+serch_str+" "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String invoice_num=resultSet1.getString("trans_num");
	                String vend_id=resultSet1.getString("ref_vendor_id");
					String vend_name=resultSet1.getString("pay_to");
					String issue_date=resultSet1.getString("created_date");
					String payment_method=resultSet1.getString("payment_method");
					String bank_name=resultSet1.getString("bank_name");
					String cheque_no=resultSet1.getString("cheque_no");
					String cheque_dt=resultSet1.getString("issue_date");
					String amt="";
					if(Pagetype.equals("Debit Note"))
					{
						amt=resultSet1.getString("dr_amt");
					}
					else
					{
						amt=resultSet1.getString("cr_amt");
					}
	                model.addRow(new Object[]{model.getRowCount()+1,invoice_num,vend_id,vend_name,amt,issue_date,payment_method,bank_name,cheque_no,cheque_dt});
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Voucher List Found ·„ Ì „ «·⁄ÀÊ— ⁄·Ï ﬁ«∆„… «·ﬁ”«∆„");
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