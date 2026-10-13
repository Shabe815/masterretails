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

public class Inv_adjust_list {

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
					Inv_adjust_list window = new Inv_adjust_list("mr_pro","mr_pro","admin","S01", null,"2","");
					Inv_adjust_list.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param decimal 
	 * @param language 
	 */
	public Inv_adjust_list(String db_name,String company, String username,String store,String version, String decimal, String language) throws JSONException {
		initialize(db_name,company,username,store, version,decimal,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 * @param page 
	 */
	private void initialize(final String db_name,final String company,final String username,final String store,final String version,final String decimal, final String language) throws JSONException {
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
		
		
		JLabel lblItemMasterList = new JLabel("Inventory Adjustment List");
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
				try {
					get_po_disp( db_name, "OPEN", "");
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
				
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		btnProceed = new JButton("Post Adj");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int row=table.getSelectedRow();
					if(row>-1)
					{
						if(table.getValueAt(row, 2).toString().equals(table.getValueAt(row, 3).toString()))
						{
							new inventory_master.Post_adj(db_name,table.getValueAt(row, 0).toString(), table.getValueAt(row, 1).toString(), table.getValueAt(row, 2).toString(), table.getValueAt(row, 4).toString(), version,Integer.parseInt(decimal),username,store);
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
		btnProceed.setBounds(670, 24, 85, 25);
		panel.add(btnProceed);
		
		JButton btnNewButton = new JButton("New Adj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new inventory_master.Inv_adjustment(db_name,db_name, username, "", store, version,decimal,language);
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		btnNewButton.setBounds(561, 24, 97, 25);
		panel.add(btnNewButton);
		
		JButton btnVoid = new JButton("Void Adj");
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
						int a= JOptionPane.showConfirmDialog(frame, "Do You Want to Void This Adjustment","",JOptionPane.YES_NO_OPTION);
						if(a==0)
						{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="SELECT * FROM "+db_name+".mr_inv_trans_list where adj_no='"+model.getValueAt(row, 0).toString()+"'"; 
							ResultSet resultSet = statement.executeQuery(query);
							if(resultSet.next())
							{
								String update_pl="update "+db_name+".mr_inv_trans_list set status='VOID' where adj_no ='"+model.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(update_pl);
								
								String copy_inv_tran="INSERT INTO "+db_name+".mr_inv_trans_history(`ref_adj_num`, `barcode`, `item_code`, `qtyOh`, `unit`, `cf`, `unit_cost`, `qty`,  `created_date`, `modified_date`, `item_name`) SELECT `ref_adj_num`, `barcode`, `item_code`, `qtyOh`, `unit`, `cf`, `unit_cost`, `qty`, `created_date`, `modified_date`, `item_name` FROM "+db_name+".mr_inv_trans where ref_adj_num ='"+model.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(copy_inv_tran);
								
								String copy_inv_tran_list="INSERT INTO "+db_name+".mr_inv_trans_list_history( `com_date`, `adj_date`, `user_id`, `adj_no`, `invo_nu`, `ttl_items`, `ttl_price`, `sub_ttl_amt`, `assume_ttl_cst`, `status`, `store_name`, `created_date`, `modified_date`,remark) SELECT `com_date`, `adj_date`, `user_id`, `adj_no`, `invo_nu`, `ttl_items`, `ttl_price`, `sub_ttl_amt`, `assume_ttl_cst`, `status`, `store_name`, `created_date`, `modified_date`,remark FROM "+db_name+".mr_inv_trans_list where adj_no ='"+model.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(copy_inv_tran_list);
								
								String del_inv_tran_list="delete from "+db_name+".mr_inv_trans_list where adj_no ='"+model.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(del_inv_tran_list);
								
								String del_inv_tran="delete from "+db_name+".mr_inv_trans where ref_adj_num ='"+model.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(del_inv_tran);
								
								JOptionPane.showMessageDialog(frame, "Voided Successfully");
								inventory_master.Inv_adjust_list.get_po_disp(db_name, "OPEN", "");
							}
							con.close();
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		btnVoid.setBounds(469, 24, 80, 25);
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
		    
		    model.addColumn("Adjusment Number");
		    model.addColumn("Supp. Invoice No."); 
		    model.addColumn("Invoice Amount"); 
		    model.addColumn("Total Amount");
		    model.addColumn("ADJ Date");
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
				  get_po_disp( company,"OPEN",textField.getText());
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
		
		JButton btnNewButton_1 = new JButton("Posted Adj List");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Inv_adj_post_list(db_name, username,store, version,"POSTED");
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		btnNewButton_1.setBounds(753, 13, 143, 25);
		panel_2.add(btnNewButton_1);
		
		JButton btnVoidPoList = new JButton("Void Adj List");
		btnVoidPoList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Inv_adj_post_list(db_name, username,store, version,"VOID");
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		btnVoidPoList.setBounds(633, 13, 108, 25);
		panel_2.add(btnVoidPoList);
		 
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 0;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	try {
						new inventory_master.Inv_adjustment(db_name,db_name,username, value,store, version,decimal,language);
					} catch (Exception e) {
						JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
					}
                }
            }
        });
        get_po_disp( db_name,"OPEN",textField.getText());
        
	}
	static void  get_po_disp( String db_name, String type, String search)
	{
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and adj_no LIKE '%"+search+"%' OR invo_nu LIKE '%"+search+"%' ";
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+db_name+".mr_inv_trans_list where status='"+type+"' "+serch_str+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+db_name+".mr_inv_trans_list where status='"+type+"' "+serch_str+" "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String name=resultSet1.getString("adj_no");
	                String code=resultSet1.getString("invo_nu");
	                String cntct_name=resultSet1.getString("ttl_price");
	                String grand_ttl=resultSet1.getString("sub_ttl_amt");
	                String po_date=resultSet1.getString("adj_date");
	                
	                model.addRow(new Object[]{name,code,cntct_name,grand_ttl,po_date});
				}
	        	btnProceed.setEnabled(true);
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Adjustment List Found");
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
