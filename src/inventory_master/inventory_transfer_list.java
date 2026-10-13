package inventory_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
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

public class inventory_transfer_list {

	private static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	private JTextField textField;
	private static JButton btnProceed;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					inventory_transfer_list window = new inventory_transfer_list("mr_pro","mr_pro","admin","S01", null,"2", "ISSUE","");
					inventory_transfer_list.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param decimal 
	 * @param page_type 
	 */
	public inventory_transfer_list(String db_name,String company, String username,String store,String version, String decimal, String page_type,String language) throws JSONException {
		initialize(db_name,company,username,store, version,decimal,page_type, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param page 
	 */
	private void initialize(final String db_name,final String company,final String username,final String store,final String version,final String decimal, final String page_type,final String language) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 696);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("INVENTORY TRANSFER "+page_type+" LIST");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(7, 11, 387, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(772, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					get_po_disp( db_name, "OPEN", "",page_type,store);
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
				
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(839, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		btnProceed = new JButton("Post "+page_type+"");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int row=table.getSelectedRow();
					if(row>-1)
					{
						if(table.getValueAt(row, 4).toString().equals(table.getValueAt(row, 5).toString()))
						{
							new inventory_master.Post_transfer(db_name,table.getValueAt(row, 0).toString(), table.getValueAt(row, 1).toString(), table.getValueAt(row, 4).toString(), table.getValueAt(row, 6).toString(), version,Integer.parseInt(decimal),username,store, page_type,table.getValueAt(row, 3).toString());
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Invoice Amount is Not Matching Total Amount");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Select Any Row from Table");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		btnProceed.setBounds(635, 22, 130, 25);
		panel.add(btnProceed);
		
		if(page_type.equals("ISSUE"))
		{
			JButton btnNewButton = new JButton("New "+page_type+"");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new inventory_master.inventory_transfer(db_name,company, username, page_type,"", store, version,decimal,language);
					} catch (Exception e) {
						JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
					}
				}
			});
			btnNewButton.setBounds(401, 22, 110, 25);
			panel.add(btnNewButton);
		}
		
		
		JButton btnVoid = new JButton("Void "+page_type+"");
		btnVoid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int row=table.getSelectedRow();
					if(row==-1)
					{
						JOptionPane.showMessageDialog(frame, "Select A Row");
					}
					else
					{
						int a= JOptionPane.showConfirmDialog(frame, "Do You Want to Void This Transfer","",JOptionPane.YES_NO_OPTION);
						if(a==0)
						{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="SELECT * FROM "+db_name+".mr_inventory_transfer_list where tran_no='"+model.getValueAt(row, 0).toString()+"'"; 
							ResultSet resultSet = statement.executeQuery(query);
							if(resultSet.next())
							{
								String update_pl="update "+db_name+".mr_inventory_transfer_list set status='VOID' where tran_no ='"+model.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(update_pl);
								
								String copy_inv_tran="INSERT INTO "+db_name+".mr_inventory_transfer_details_history(`ref_tran_num`, `barcode`, `item_code`, `qtyOh`, `unit`, `cf`, `unit_cost`, `qty`, `total`, `created_date`, `modified_date`, `item_name`) SELECT `ref_tran_num`, `barcode`, `item_code`, `qtyOh`, `unit`, `cf`, `unit_cost`, `qty`, `total`, `created_date`, `modified_date`, `item_name` FROM "+db_name+".mr_inventory_transfer_details where ref_tran_num ='"+model.getValueAt(row, 0).toString()+"'  ";
								statement.executeUpdate(copy_inv_tran);
								
								String copy_inv_tran_list="INSERT INTO "+db_name+".mr_inventory_transfer_list_history( `com_date`, `store_from`, `store_to`, `tran_date`, `user_id`, `tran_no`, `invo_nu`, `ttl_items`, `ttl_price`, `sub_ttl_amt`, `assume_ttl_cst`, `status`, `tran_type`, `created_date`, `modified_date`, `remark`) SELECT `com_date`, `store_from`, `store_to`, `tran_date`, `user_id`, `tran_no`, `invo_nu`, `ttl_items`, `ttl_price`, `sub_ttl_amt`, `assume_ttl_cst`, `status`, `tran_type`, `created_date`, `modified_date`, `remark` FROM "+db_name+".mr_inventory_transfer_list where where tran_no ='"+model.getValueAt(row, 0).toString()+"'";
								statement.executeUpdate(copy_inv_tran_list);
								
								String del_inv_tran_list="delete from "+db_name+".mr_inventory_transfer_details where ref_tran_num ='"+model.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(del_inv_tran_list);
								
								String del_inv_tran="delete from "+db_name+".mr_inventory_transfer_list where tran_no ='"+model.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(del_inv_tran);
								
								JOptionPane.showMessageDialog(frame, "Voided Successfully");
								inventory_master.inventory_transfer_list.get_po_disp(db_name, "OPEN", "",page_type,store);
							}
							con.close();
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		btnVoid.setBounds(518, 22, 110, 25);
		panel.add(btnVoid);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 589);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 88, 908, 488);
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
		    
		    model.addColumn("Transfer Number");
		    model.addColumn("Invoice No.");
		    model.addColumn("From Store");
		    model.addColumn("To Store"); 
		    model.addColumn("Invoice Amount"); 
		    model.addColumn("Total Amount");
		    model.addColumn("Tran Date");
		scrollPane.setViewportView(table);
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
				  get_po_disp( company,"OPEN",textField.getText(),page_type,store);
			  }
			});
		textField.setBounds(5, 13, 493, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name,"OPEN",textField.getText(),page_type,store);
			}
		});
		btnSearch.setBounds(503, 13, 97, 25);
		panel_2.add(btnSearch);
		
		JButton btnNewButton_1 = new JButton("Posted "+page_type+" List");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Inv_adj_post_list(db_name, username,store, version,"POSTED");
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		btnNewButton_1.setBounds(755, 13, 145, 25);
		panel_2.add(btnNewButton_1);
		
		JButton btnVoidPoList = new JButton("Void "+page_type+" List");
		btnVoidPoList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Inv_adj_post_list(db_name, username,store, version,"VOID");
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		btnVoidPoList.setBounds(605, 13, 145, 25);
		panel_2.add(btnVoidPoList);
		 
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 0;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	try {
                												//db_name,company,username,vendor_id,po_num, store_name, version,decimal
						new inventory_master.inventory_transfer(db_name,company,username,page_type, value,store, version,decimal,language);
					} catch (Exception e) {
						JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
					}
                }
            }
        });
        get_po_disp( db_name,"OPEN",textField.getText(),page_type,store);
        
	}
	static void  get_po_disp( String db_name, String type, String search, String page_type, String store_name)
	{
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and tran_no LIKE '%"+search+"%' OR invo_nu LIKE '%"+search+"%' ";
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+db_name+".mr_inventory_transfer_list where status='"+type+"' and tran_type='"+page_type+"' "+serch_str+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+db_name+".mr_inventory_transfer_list where status='"+type+"'  and tran_type='"+page_type+"' "+serch_str+" "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String name=resultSet1.getString("tran_no");
	                String code=resultSet1.getString("invo_nu");
	                String tostore=resultSet1.getString("store_to");
	                String fromstore=resultSet1.getString("store_from");
	                String cntct_name=resultSet1.getString("ttl_price");
	                String grand_ttl=resultSet1.getString("sub_ttl_amt");
	                String po_date=resultSet1.getString("tran_date");
	                
	                model.addRow(new Object[]{name,code,fromstore,tostore,cntct_name,grand_ttl,po_date});
				}
	        	btnProceed.setEnabled(true);
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Transfer "+page_type+" List Found");
	        	for (int i = model.getRowCount() - 1; i >= 0; i--) {
	        	    model.removeRow(i);
	        	}
			}
			con.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}
}
