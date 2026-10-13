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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Delivery_list {

	public static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	private JTextField textField;
	private static JButton btnProceed;
	static JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Delivery_list window = new Delivery_list("mr_pro","admin","S01", null,"2");
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
	public Delivery_list(String db_name, String username,String store,String version,String decimal) throws JSONException {
		initialize(db_name,username,store, version,decimal);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param decimal 
	 * @param page 
	 */
	private void initialize(final String db_name,final String username,final String store,final String version, final String decimal) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 675);
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
		panel.setBounds(0, 0, 932, 96);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Delivery Note List");
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
				get_po_disp( db_name, "OPEN", "");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		btnProceed = new JButton("Post Delivery Note");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int row=table.getSelectedRow();
					if(row>-1)
					{
						frame.setAlwaysOnTop(false);
						int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Post This Delivery Note","Warning",JOptionPane.YES_NO_OPTION);
				        // 0=yes, 1=no, 2=cancel
				        if(cofrm_bx==0)
				        {
				        	frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				        	
				        	String do_num=table.getValueAt(row, 1).toString();
				        	String po_num=table.getValueAt(row, 2).toString();
				        	
				        	Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="SELECT * FROM "+db_name+".mr_delivery_note where ref_inv_num ='"+po_num+"' and ref_do_num='"+do_num+"' "; 
							ResultSet resultSet = statement.executeQuery(query);
							if(resultSet.next())
							{
								String query3="update "+db_name+".mr_delivery_list set status='POSTED' where invoice_num ='"+po_num+"' and do_num='"+do_num+"' ";
								statement.executeUpdate(query3);
								
								String dohistory="INSERT INTO "+db_name+".mr_delivery_note_history( `ref_inv_num`, `ref_do_num`, `itemcode`, `barcode`, `item_name`, `unit`, `cf`, `invoice_qty`, `delivered_qty`, `balance_qty`, `previous_delivery`, `created_date`, `modified_date`) SELECT  `ref_inv_num`, `ref_do_num`, `itemcode`, `barcode`, `item_name`, `unit`, `cf`, `invoice_qty`, `delivered_qty`, `balance_qty`, `previous_delivery`, `created_date`, `modified_date` FROM "+db_name+".mr_delivery_note where ref_inv_num ='"+po_num+"' and ref_do_num='"+do_num+"' ";
								statement.executeUpdate(dohistory);
								
								String pohistory="INSERT INTO "+db_name+".mr_delivery_list_history( `date`, `user_id`, `invoice_num`, `do_num`, `remark`, `cust_id`, `cust_addr`, `status`, `referance`, `store_name`, `created_date`, `modified_date`, `cust_name`,location) SELECT  `date`, `user_id`, `invoice_num`, `do_num`, `remark`, `cust_id`, `cust_addr`, `status`, `referance`, `store_name`, `created_date`, `modified_date`, `cust_name`,location FROM "+db_name+".mr_delivery_list where invoice_num ='"+po_num+"' and do_num='"+do_num+"' ";
								statement.executeUpdate(pohistory);
								
//								String delete_pl="delete from "+db_name+".mr_delivery_list where invoice_num ='"+po_num+"' and do_num='"+do_num+"' ";
//								statement.executeUpdate(delete_pl);
								
//								String delete_po="delete from "+db_name+".mr_delivery_note where ref_inv_num ='"+po_num+"' and ref_do_num='"+do_num+"' ";
//								statement.executeUpdate(delete_po);
								
								
								String poUPDATE="UPDATE "+db_name+".mr_delivery_note set ref_do_num='',previous_delivery=previous_delivery+delivered_qty,delivered_qty=balance_qty,balance_qty='0' where ref_inv_num ='"+po_num+"' and ref_do_num='"+do_num+"' ";
								statement.executeUpdate(poUPDATE);
								String doUPDATE="UPDATE "+db_name+".mr_delivery_list set do_num='',status='OPEN' where invoice_num ='"+po_num+"' ";
								statement.executeUpdate(doUPDATE);
							}
							statement.close();
							con.close();
				        	
				        	
				        	frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				        	JOptionPane.showMessageDialog(frame, "DO Posted Successfully");
			                
			                sales_distribution.Delivery_list.get_po_disp(db_name, "OPEN", "");
				        	
				        }
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Select A Row from Table");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}
				catch(Exception e){
					System.err.println(e);
				}
			}
		});
		btnProceed.setBounds(608, 22, 160, 25);
		panel.add(btnProceed);
		
		btnNewButton = new JButton("New Delivery Note");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new new_dn_inv_list(db_name,username,store, version,decimal);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(448, 22, 148, 25);
		panel.add(btnNewButton);
		
		JButton btnVoid = new JButton("Void Delivery Note");
		btnVoid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int row=table.getSelectedRow();
					if(row>-1)
					{
						frame.setAlwaysOnTop(false);
						int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Post This Delivery Note","Warning",JOptionPane.YES_NO_OPTION);
				        // 0=yes, 1=no, 2=cancel
				        if(cofrm_bx==0)
				        {
				        	frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				        	
				        	String do_num=table.getValueAt(row, 1).toString();
				        	String po_num=table.getValueAt(row, 2).toString();
				        	
				        	Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="SELECT * FROM "+db_name+".mr_delivery_note where ref_inv_num ='"+po_num+"' and ref_do_num='"+do_num+"' "; 
							ResultSet resultSet = statement.executeQuery(query);
							if(resultSet.next())
							{
								String query3="update "+db_name+".mr_delivery_list set status='VOID' where invoice_num ='"+po_num+"' and do_num='"+do_num+"' ";
								statement.executeUpdate(query3);
								
								String dohistory="INSERT INTO "+db_name+".mr_delivery_note_history( `ref_inv_num`, `ref_do_num`, `itemcode`, `barcode`, `item_name`, `unit`, `cf`, `invoice_qty`, `delivered_qty`, `balance_qty`, `previous_delivery`, `created_date`, `modified_date`) SELECT  `ref_inv_num`, `ref_do_num`, `itemcode`, `barcode`, `item_name`, `unit`, `cf`, `invoice_qty`, `delivered_qty`, `balance_qty`, `previous_delivery`, `created_date`, `modified_date` FROM "+db_name+".mr_delivery_note where ref_inv_num ='"+po_num+"' and ref_do_num='"+do_num+"' ";
								statement.executeUpdate(dohistory);
								
								String pohistory="INSERT INTO "+db_name+".mr_delivery_list_history( `date`, `user_id`, `invoice_num`, `do_num`, `remark`, `cust_id`, `cust_addr`, `status`, `referance`, `store_name`, `created_date`, `modified_date`, `cust_name`,location) SELECT  `date`, `user_id`, `invoice_num`, `do_num`, `remark`, `cust_id`, `cust_addr`, `status`, `referance`, `store_name`, `created_date`, `modified_date`, `cust_name`,location FROM "+db_name+".mr_delivery_list where invoice_num ='"+po_num+"' and do_num='"+do_num+"' ";
								statement.executeUpdate(pohistory);
								
								
								String poUPDATE="UPDATE "+db_name+".mr_delivery_note set ref_do_num='' where ref_inv_num ='"+po_num+"' and ref_do_num='"+do_num+"' ";
								statement.executeUpdate(poUPDATE);
								
								String doUPDATE="UPDATE "+db_name+".mr_delivery_list set do_num='',status='OPEN' where invoice_num ='"+po_num+"' ";
								statement.executeUpdate(doUPDATE);
							}
							statement.close();
							con.close();
				        	
				        	
				        	frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				        	JOptionPane.showMessageDialog(frame, "DO Posted Successfully");
			                
			                sales_distribution.Delivery_list.get_po_disp(db_name, "OPEN", "");
				        	
				        }
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Select A Row from Table");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}
				catch(Exception e){
					System.err.println(e);
				}
			}
		});
		btnVoid.setBounds(292, 22, 144, 25);
		panel.add(btnVoid);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 95, 932, 538);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
		    
		    model.addColumn("Date");
		    model.addColumn("DO No."); 
		    model.addColumn("Invoice No.");
		    model.addColumn("Customer Code");
		    model.addColumn("Customer Name");
		    
		scrollPane.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(190);
			  if (i==1) column.setPreferredWidth(140);
			  if (i==2) column.setPreferredWidth(140);
			  if (i==3) column.setPreferredWidth(140);
			  if (i==4) column.setPreferredWidth(290);
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
				  get_po_disp( db_name,"OPEN",textField.getText());
			  }
			});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					get_po_disp( db_name,"OPEN",textField.getText());
				}
			}
		});
		textField.setBounds(12, 13, 493, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name,"OPEN",textField.getText());
			}
		});
		btnSearch.setBounds(518, 13, 97, 25);
		panel_2.add(btnSearch);
		
		JButton btnNewButton_1 = new JButton("D O Posted List");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new sales_distribution.delivery_post_list(db_name,username,store, version,decimal);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(753, 13, 143, 25);
		panel_2.add(btnNewButton_1);
		
		JButton btnVoidPoList = new JButton("Void D O List");
		btnVoidPoList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new sales_distribution.delivery_void_list(db_name,username,store, version,decimal);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnVoidPoList.setBounds(633, 13, 117, 25);
		panel_2.add(btnVoidPoList);
		//comboBox.addItem("DELIVERY");
		 
		get_po_disp( db_name, "OPEN", "");
		
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 2;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	try{
            			new sales_distribution.delivery_note(db_name, username, table.getModel().getValueAt(rows, 3).toString(), value, store, version,decimal,table.getModel().getValueAt(rows, 1).toString());
	 					
                	}
                	catch (JSONException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                
            }
        });
        
	}
	static void  get_po_disp( String company, String type, String search)
	{
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and invoice_num LIKE '%"+search+"%' OR cust_id LIKE '%"+search+"%' ";
		}
		
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_delivery_list where status='"+type+"' "+serch_str+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+company+".mr_delivery_list where status='"+type+"' "+serch_str+" "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String invoice_num=resultSet1.getString("invoice_num");
	                String cust_id=resultSet1.getString("cust_id");
	                String cust_name=resultSet1.getString("cust_name");
	                String date=resultSet1.getString("date");
	                String donum=resultSet1.getString("do_num");
	                model.addRow(new Object[]{date,donum,invoice_num,cust_id,cust_name});
				}
	        	btnProceed.setEnabled(true);
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Delivery Note List Found");
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