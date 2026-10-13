package purchase_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

public class purchase_return_list {

	static JFrame frame;
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
			@SuppressWarnings("unused")
			public void run() {
				try {
					purchase_return_list window = new purchase_return_list("mr_pro","mr_pro","admin","S01", null,"2","");
					purchase_return_list.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public purchase_return_list(String db_name,String company, String username,String store,String version,String decimal,String language) throws JSONException {
		initialize(db_name,company,username,store, version,decimal, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param decimal 
	 * @param page 
	 */
	private void initialize(final String db_name,final String company,final String username,final String store,final String version, final String decimal,final String language) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 696);
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
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Purchase Return List");
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
				get_po_disp( company, "OPEN", "");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		btnProceed = new JButton("Post PR");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row>-1)
				{
					if(table.getValueAt(row, 2).toString().equals(table.getValueAt(row, 3).toString()))
					{
						frame.setAlwaysOnTop(false);
						new purchase_master.Post_pr(company,table.getValueAt(row, 0).toString(),table.getValueAt(row, 1).toString(),table.getValueAt(row, 2).toString(),table.getValueAt(row, 5).toString(),version,Integer.parseInt(decimal),username,store);
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
				
				
			}
		});
		btnProceed.setBounds(670, 24, 85, 25);
		panel.add(btnProceed);
		
		btnNewButton = new JButton("New PR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setAlwaysOnTop(false);
					new purchase_return (db_name,company, username,"", "",store, version,decimal,language);
					btnNewButton.setEnabled(false);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(561, 24, 97, 25);
		panel.add(btnNewButton);
		
		JButton btnVoid = new JButton("Void PR");
		btnVoid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(frame, "Select A Row");
				}
				else
				{
					int a= JOptionPane.showConfirmDialog(frame, "Do You Want to Void This PO","",JOptionPane.YES_NO_OPTION);
					if(a==0)
					{
						try{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="SELECT * FROM "+company+".mr_purchase_return_list where pr_no='"+model.getValueAt(row, 0).toString()+"'"; 
							ResultSet resultSet = statement.executeQuery(query);
							if(resultSet.next())
							{
								String update_pl="update "+company+".mr_purchase_return_list set status='VOID' where pr_no ='"+model.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(update_pl);
								JOptionPane.showMessageDialog(frame, "PR Voided Successfully");
								
								purchase_master.purchase_return_list.get_po_disp(company, "OPEN", "");
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
		    
		    model.addColumn("Purchase Number");
		    model.addColumn("Supp. Invoice No."); 
		    model.addColumn("Invoice Amount"); 
		    model.addColumn("Total Amount"); 
		    model.addColumn("vendor Name");
		    model.addColumn("PR Date");
		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(190);
			  if (i==1) column.setPreferredWidth(140);
			  if (i==2) column.setPreferredWidth(140);
			  if (i==3) column.setPreferredWidth(140);
			  if (i==4) column.setPreferredWidth(140);
			  if (i==5) column.setPreferredWidth(140);
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
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( company,"OPEN",textField.getText());
			}
		});
		btnSearch.setBounds(518, 13, 97, 25);
		panel_2.add(btnSearch);
		
		JButton btnNewButton_1 = new JButton("PR Posted List");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new pr_posted_list(company, username,store, version,decimal);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(753, 13, 143, 25);
		panel_2.add(btnNewButton_1);
		
		JButton btnVoidPoList = new JButton("Void PR List");
		btnVoidPoList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new purchase_master.Pr_void_list(company,  username, store, version,decimal);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnVoidPoList.setBounds(633, 13, 108, 25);
		panel_2.add(btnVoidPoList);
		table.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e1) {
	    		if (e1.getKeyChar() == KeyEvent.VK_ENTER) {
	    			int column = 0;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	try{
            			Connection con=Dbsetup.dbconn.getConnection();
            			Statement statement=con.createStatement();  
            			String vendor_code = null;
            			String query2="SELECT vendor_name FROM "+company+".mr_vendor where vendor_name='"+table.getModel().getValueAt(rows, 4).toString()+"'"; 
            			ResultSet resultSet = statement.executeQuery(query2);
	 					if(resultSet.next())
	 					{
	 						vendor_code=resultSet.getString("vendor_name");
	 					}
	 					new purchase_master.purchase_return(db_name,company, username, vendor_code, value, store, version,decimal,language);
	 					purchase_return.frame.setAlwaysOnTop(true);
                	}
                	catch (JSONException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
                	try{
            			Connection con=Dbsetup.dbconn.getConnection();
            			Statement statement=con.createStatement();  
            			String vendor_code = null;
            			String query2="SELECT vendor_name FROM "+company+".mr_vendor where vendor_name='"+table.getModel().getValueAt(rows, 4).toString()+"'"; 
            			ResultSet resultSet = statement.executeQuery(query2);
	 					if(resultSet.next())
	 					{
	 						vendor_code=resultSet.getString("vendor_name");
	 					}
	 					new purchase_master.purchase_return(db_name,company, username, vendor_code, value, store, version,decimal,language);
	 					purchase_return.frame.setAlwaysOnTop(true);
                	}
                	catch (JSONException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	//frame.setVisible(false);
                }
            }
        });
        get_po_disp( company,"OPEN",textField.getText());
        textField.requestFocus();
	}
	static void  get_po_disp( String company, String type, String search)
	{
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and pr_no LIKE '%"+search+"%' OR invo_nu LIKE '%"+search+"%' or vendor_name like '%"+search+"%' ";
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_purchase_return_list inner join "+company+".mr_vendor on mr_vendor.vendor_code=mr_purchase_return_list.vendor_id where status='"+type+"' "+serch_str+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+company+".mr_purchase_return_list  inner join "+company+".mr_vendor on mr_vendor.vendor_code=mr_purchase_return_list.vendor_id where status='"+type+"' "+serch_str+" "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String vendor_name=resultSet1.getString("vendor_name");;
					String name=resultSet1.getString("pr_no");
	                String code=resultSet1.getString("invo_nu");
	                String cntct_name=resultSet1.getString("ttl_price");
	                String grand_ttl=resultSet1.getString("sub_ttl_amt");
	                //String vendor_id=resultSet1.getString("vendor_id");
	                String po_date=resultSet1.getString("pr_date");
	                
	                model.addRow(new Object[]{name,code,cntct_name,grand_ttl,vendor_name,po_date});
				}
	        	btnProceed.setEnabled(true);
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Purchase Return List Found");
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
