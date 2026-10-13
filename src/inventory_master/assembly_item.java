package inventory_master;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class assembly_item {

	static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	private JTextField textField;
	private static DecimalFormat df2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					assembly_item window = new assembly_item("mr_pro","admin","S01", null,"2");
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
	public assembly_item(String db_name, String username,String store,String version,String decimal) throws JSONException {
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
		frame.setBounds(100, 100, 789, 694);
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
		panel.setBounds(0, 0, 771, 73);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Assembly of Items");
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
		menuItem.setBounds(538, 13, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name, "");
				textField.setText("");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(598, 13, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					Connection con=Dbsetup.dbconn.getConnection();
					Statement st=con.createStatement();
					Statement st1=con.createStatement();
					for(int i=0;i<table.getRowCount();i++)
					{
						String sts = table.getValueAt(i, 1).toString().trim();
						String itemcode=table.getValueAt(i, 2).toString().trim();
						String barcode=table.getValueAt(i, 3).toString().trim();
						String itemname=table.getValueAt(i, 4).toString().trim();
						String qtyoh=table.getValueAt(i, 5).toString().trim();
						String nw_qty = table.getValueAt(i, 6).toString().trim();
						if(nw_qty.equals(""))
						{
							nw_qty="0";
						}
						String sale_qty=table.getValueAt(i, 7).toString().trim();
						String bal_qty=table.getValueAt(i, 8).toString().trim();
						String cost=table.getValueAt(i, 9).toString().trim();
						if(sts.equals("1") && !nw_qty.equals("0"))
						{
							String insert="insert into "+db_name+".mr_assembly_item ( `itemcode`, `barcode`, `itemname`, `qtyoh`, `new_qty`, `sale_qty`, `bal_qty`, `created_date`, `modified_date`) values('"+itemcode+"','"+barcode+"','"+itemname+"','"+qtyoh+"','"+nw_qty+"','"+sale_qty+"','"+bal_qty+"','"+formattedDate+"','"+formattedDate+"')";
							st.executeUpdate(insert);
							String item_update="update "+db_name+".mr_item_master set total_qty=(total_qty+'"+nw_qty+"'),modified_date='"+formattedDate+"' where item_code='"+itemcode+"' ";
							st.executeUpdate(item_update);
							String tmp_item_update="update "+db_name+".mr_tmp_item_master set qty=(qty+'"+nw_qty+"') where item_code='"+itemcode+"' ";
							st.executeUpdate(tmp_item_update);
							String query11="insert into "+db_name+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) "
									+ "values('"+itemcode+"','AS','INV-ASSEMBLE','"+nw_qty+"','EACH','1','"+cost+"','"+(Double.parseDouble(cost)*Float.parseFloat(nw_qty))+"','0','0','0','Inventory Assemble','"+username+"','"+formattedDate+"','"+barcode+"','','')";
							st.executeUpdate(query11);
							
							
							if(sts.equals("1") && !nw_qty.equals("0"))
							{
								String ent_qty=table.getValueAt(i, 6).toString().trim();
								String kit_item="select * from "+db_name+".mr_kit_items where parent_itemcode='"+table.getValueAt(i, 2).toString().trim()+"' ";
								ResultSet rs=st.executeQuery(kit_item);
								while(rs.next())
								{
									String kit_qty=rs.getString("qty"); 
									String itmvalidate="select * from "+db_name+".mr_tmp_item_master where item_code='"+rs.getString("child_itemcode")+"' ";
									Statement statement151=con.createStatement();
									ResultSet resultSet151 = statement151.executeQuery(itmvalidate);
									if(resultSet151.next())
									{
										String total_qty=resultSet151.getString("qty"); 
										float newqty=Float.parseFloat(total_qty)-(Float.parseFloat(ent_qty)*Float.parseFloat(kit_qty));
										String update_qty="update "+db_name+".mr_item_master set total_qty='"+df2.format(newqty)+"',modified_date='"+formattedDate+"' where item_code='"+rs.getString("child_itemcode")+"'  ";
										st1.executeUpdate(update_qty);
										String update_temp="update "+db_name+".mr_tmp_item_master set qty='"+df2.format(newqty)+"' where item_code='"+rs.getString("child_itemcode")+"'  ";
										st1.executeUpdate(update_temp);
										String query12="insert into "+db_name+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) "
												+ "values('"+itemcode+"','AS','INV-ASSEMBLE','"+newqty+"','EACH','1','"+cost+"','"+(Double.parseDouble(cost)*newqty)+"','0','0','0','Inventory Assemble','"+username+"','"+formattedDate+"','"+barcode+"','','')";
										st.executeUpdate(query12);
										
									}
								}
							}
						}
					}
					
					frame.setCursor(Cursor.getDefaultCursor());
					JOptionPane.showMessageDialog(frame, "Saved Successfully");
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
		        	    model.removeRow(i);
		        	}
					textField.setText("");
					get_po_disp( db_name, "");
				} catch (SQLException e) {
					frame.setAlwaysOnTop(false);
					frame.setCursor(Cursor.getDefaultCursor());
					JOptionPane.showMessageDialog( frame, e.getMessage(),"SQLException error", JOptionPane.ERROR_MESSAGE );
					//frame.setAlwaysOnTop(true);
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(679, 13, 80, 48);
		menuItem_2.setIcon(new ImageIcon(save));
		panel.add(menuItem_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 77, 771, 574);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 88, 747, 468);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel_1.add(scrollPane);

		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		      if( column==6)
		      {
		    	  return true;
		      }
				
		       return false;
		    }
		};
		    table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
		    table.setRowHeight(20);
		    
		    model.addColumn("Sl. No."); 
		    model.addColumn("Parent");
		    model.addColumn("Item Code"); 
		    model.addColumn("Barcode");
		    model.addColumn("Item Name");
		    model.addColumn("QtyOH");
		    model.addColumn("New Qty");
		    model.addColumn("Sale Qty");
		    model.addColumn("Balance Qty");
		    model.addColumn("Cost");
		   
		    
		scrollPane.setViewportView(table);
		
		
		table.setDefaultRenderer(Object.class, new TableCellRenderer(){
            private DefaultTableCellRenderer DEFAULT_RENDERER =  new DefaultTableCellRenderer();
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (table.getValueAt(row, 1).equals("1"))
                {
                   	c.setBackground(Color.lightGray);
                    c.setForeground(Color.BLACK);   
                }
                else
                {
                    c.setBackground(Color.white);
                	c.setForeground(Color.BLACK);
                	
                }
                if (isSelected) 
                {
                	c.setBackground(Color.YELLOW);
                } 
                return c;
                
            }
        });
		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(40);
			  if (i==1) column.setPreferredWidth(40);
			  if (i==2) column.setPreferredWidth(100);
			  if (i==3) column.setPreferredWidth(100);
			  if (i==4) column.setPreferredWidth(350);
			  if (i==5) column.setPreferredWidth(100);
			  if (i==6) column.setPreferredWidth(100);
			  if (i==7) column.setPreferredWidth(100);
			  if (i==8) column.setPreferredWidth(100);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 747, 52);
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
				  get_po_disp( db_name,textField.getText());
			  }
			});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					get_po_disp( db_name,textField.getText());
				}
			}
		});
		textField.setBounds(12, 13, 618, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name,textField.getText());
			}
		});
		btnSearch.setBounds(640, 13, 97, 25);
		panel_2.add(btnSearch);
		
        get_po_disp( db_name, "");
	}
	static void  get_po_disp( String company, String search)
	{
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="where (item_code LIKE '%"+search+"%' OR item_name LIKE '%"+search+"%') ";
		}
		
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement(); 
			Statement statement1=con.createStatement(); 
			String query="SELECT * FROM "+company+".mr_item_master "+serch_str+" ";
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				df2=new DecimalFormat("0.00");
				String query1="SELECT * FROM "+company+".mr_item_master "+serch_str+" order by parentKitItem desc";
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String qty_sale="0";
					String item_code=resultSet1.getString("item_code");
					String cost=resultSet1.getString("item_cost");
					String parentKitItem=resultSet1.getString("parentKitItem");
					String query11="select sum(qty) as ttl_qty from "+company+".mr_pos_settlement_cash_items where item_code='"+item_code+"'";
					ResultSet resultSet2 = statement1.executeQuery(query11);
					if(resultSet2.next())
					{
						qty_sale=resultSet2.getString("ttl_qty");
						if(qty_sale==null)
						{
							qty_sale="0";
						}
					}
					String sts="0";
					if(parentKitItem.equals("1"))
					{
						sts="1";
					}
							
					String item_name=resultSet1.getString("item_name");
	                String total_qty=resultSet1.getString("total_qty");
	                String barcode1=resultSet1.getString("barcode1");
	                double bal_qty=Double.parseDouble(total_qty)-Double.parseDouble(qty_sale);
	                model.addRow(new Object[]{model.getRowCount()+1,sts,item_code,barcode1,item_name,total_qty,"",qty_sale,bal_qty,cost});
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Item List Found");
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