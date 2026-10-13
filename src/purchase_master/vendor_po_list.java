package purchase_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

public class vendor_po_list {

	static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	private JTextField textField;
	private int decimal;
	public static JMenuItem menuItem;
	private static JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					vendor_po_list window = new vendor_po_list("mr_pro","admin","S01", null,"10200001","C001","","");
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
	public vendor_po_list(String company, String username,String store,String version,String itemcode,String vendor,String language,String pagetype) throws JSONException {
		initialize(company,username,store, version,itemcode,vendor,language,pagetype);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param itemcode 
	 * @param language 
	 * @param pagetype 
	 * @param page 
	 */
	private void initialize(final String company,final String username,final String store,final String version, final String itemcode,final String vendor, final String language, final String pagetype) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 696);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    	if(pagetype.equals("item"))
		    	{
		    		inventory_master.Item_master.frame.setAlwaysOnTop(true);
					inventory_master.Item_master.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					inventory_master.Item_master.menuItem.setEnabled(true);
		    	}
		    	
		    }
		});
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Vendor PO List");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		menuItem = new JMenuItem("Exit");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				if(pagetype.equals("item"))
		    	{
		    		inventory_master.Item_master.frame.setAlwaysOnTop(true);
					inventory_master.Item_master.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					inventory_master.Item_master.menuItem.setEnabled(true);
		    	}
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
				get_po_disp( company, itemcode,"",vendor);
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 589);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 88, 908, 452);
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
		    model.addColumn("PO NUmber"); 
		    model.addColumn("Purchased Cost"); 
		    model.addColumn("Purchased Quantity"); 
		    model.addColumn("Mobile Number"); 
		    model.addColumn("Purchase Dates"); 
		    model.addColumn("Status"); 
		scrollPane.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(190);
			  if (i==1) column.setPreferredWidth(140);
			  if (i==2) column.setPreferredWidth(100);
			  if (i==3) column.setPreferredWidth(100);
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
				  get_po_disp( company,itemcode,textField.getText(),vendor);
			  }
			});
		textField.setBounds(12, 13, 493, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( company,itemcode,textField.getText(),vendor);
			}
		});
		btnSearch.setBounds(518, 13, 97, 25);
		panel_2.add(btnSearch);
		
		JLabel lblTotalPurchasedQty = new JLabel("Total Purchased Qty");
		lblTotalPurchasedQty.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalPurchasedQty.setBounds(12, 549, 143, 27);
		panel_1.add(lblTotalPurchasedQty);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(167, 549, 194, 27);
		panel_1.add(lblNewLabel);
		 
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 1;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	try {
                		frame.setAlwaysOnTop(false);
                		new purchase_master.po_view(company,  username, vendor,value,store, version,"POSTED","Item",decimal,"0", language);
                		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        });
        get_po_disp( company,itemcode,"",vendor);
        
	}
	private static void get_po_disp(String company,String Itemcode,String serch,String vendor)
	{
		try{
			String search="";
			if(!serch.equals("")){
				search=" and "+company+".mr_purchase_order_history.ref_po_num like '%"+serch+"'";
			}
			String itemsearch="";
			if(!Itemcode.equals(""))
			{
				itemsearch=" and "+company+".mr_purchase_order_history.item_code='"+Itemcode+"'";
			}
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_purchase_order_history inner join "+company+".mr_purchase_list_history on "+company+".mr_purchase_list_history.po_no="+company+".mr_purchase_order_history.ref_po_num inner join "+company+".mr_vendor ON "+company+".mr_purchase_list_history.vendor_id="+company+".mr_vendor.vendor_code where "+company+".mr_vendor.vendor_name='"+vendor+"' "+itemsearch+"  and "+company+".mr_purchase_list_history.status='POSTED' "+search+" ORDER by "+company+".mr_purchase_list_history.po_date DESC";
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			int ttlqty = 0;
			while(resultSet.next())
			{
				String name=resultSet.getString("vendor_name");
				String mobile=resultSet.getString("vendor_ph1");
				String date=resultSet.getString("po_date");
				String sts=resultSet.getString("status");
				String po_num=resultSet.getString("ref_po_num");
				String price=resultSet.getString("cost_after_sub_ttl_disc");
				double qty=Double.parseDouble(resultSet.getString("cf")) * Double.parseDouble(resultSet.getString("qty"));
				ttlqty+=qty;
				
				model.addRow(new Object[]{name,po_num,price,qty,mobile,date,sts});
			}
			lblNewLabel.setText(Integer.toString(ttlqty));
			con.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}
}
