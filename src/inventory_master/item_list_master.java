package inventory_master;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import CafePro.Mainpage;
import sales_master.Cust_item_price;
import sales_master.PosscreenArabic;
import sales_master.posscreen;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;

public class item_list_master {

	public static JFrame frame;
	private static DefaultTableModel model;
	private static JTable table;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private int limit_end=0;
	
	private JLabel label;
	private String ttl_items;
	private JComboBox<String> comboBox_2;
	private JLabel last_page;
	private JLabel first_page;
	private static DefaultTableModel model1;
	private static JTable table1;
	private JCheckBox chckbxAutoFilterItems;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					item_list_master window = new item_list_master("","mr_pro","","", null,0,Dbsetup.dbconn.getConnection(),"37200","");
					item_list_master.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param store_name 
	 * @param con 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	public item_list_master(String db_name,String company,String page_type, String store_name,String version,int decimal, Connection con,String ttl_item,String language) throws JSONException, SQLException {
		initialize(db_name,company,page_type,store_name, version,decimal,con,ttl_item,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param store_name 
	 * @param decimal 
	 * @param language 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String db_name,final String company,final String page_type, final String store_name,final String version, final int decimal, final Connection con,final String ttl_item,final String language) throws JSONException, SQLException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 734);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				inventory_master.item_list_master.frame.setVisible(false);
				if(page_type=="Item_master")
				{
					inventory_master.Item_master.frame.setAlwaysOnTop(true);
					inventory_master.Item_master.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					inventory_master.Item_master.menuItem.setEnabled(true);
				}
				else if(page_type=="posscreen")
				{
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.btnPlu.setEnabled(true);
						PosscreenArabic.txtDisc.requestFocus();
					}
					else
					{
						posscreen.btnPlu.setEnabled(true);
						posscreen.txtDisc.requestFocus();
					}
					
				}
				else if(page_type=="return")
				{
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.btnPlu.setEnabled(true);
					}
					else
					{
						posscreen.btnPlu.setEnabled(true);
					}
					
				}
				else
				{
					frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				}
			}
		});
		frame.getContentPane().setLayout(null);
		
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		//frame.setUndecorated(true);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		if(Mainpage.userauth.equals("Administrator"))
		{
			JButton btnExportToCsv = new JButton("Export Items To CSV");
			btnExportToCsv.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						export_as_csv(company);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				
			});
			btnExportToCsv.setBounds(544, 22, 179, 25);
			panel.add(btnExportToCsv);
		}
		
		
		
		
		JMenuItem menuItem_1 = new JMenuItem("");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_symc_itms(company);
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(0, 0, 32, 30);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JLabel lblItemMasterList = new JLabel("Item Master List");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(44, 0, 228, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inventory_master.item_list_master.frame.setVisible(false);
				if(page_type=="Item_master")
				{
					inventory_master.Item_master.frame.setAlwaysOnTop(true);
					inventory_master.Item_master.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					inventory_master.Item_master.menuItem.setEnabled(true);
				}
				else if(page_type=="posscreen")
				{
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.btnPlu.setEnabled(true);
						PosscreenArabic.txtDisc.requestFocus();
					}
					else
					{
						posscreen.btnPlu.setEnabled(true);
						posscreen.txtDisc.requestFocus();
					}
					
				}
				else if(page_type=="return")
				{
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.btnPlu.setEnabled(true);
					}
					else
					{
						posscreen.btnPlu.setEnabled(true);
					}
				}
				else
				{
					frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				}
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(780, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_2 = new JMenuItem("Refresh");
		menuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				int rowCount = model.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
					model.removeRow(i);
				}
				first_page.setText("0");
				get_itemdisp( company,comboBox.getSelectedItem().toString(),textField.getText(),page_type,0,200,con,comboBox_2.getSelectedItem().toString());
				textField.requestFocus();
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(840, 7, 80, 48);
		menuItem_2.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_2);
		
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setBounds(12, 297, 908, 256);
		scrollPane1.setBackground(new Color(243, 229, 216));
		scrollPane1.setVisible(false);
		panel.add(scrollPane1);

		model1 = new DefaultTableModel(){

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
		    table1 = new JTable(model1);
		    table1.setFont(new Font("Serif", Font.BOLD, 15));
		    table1.setRowHeight(20);
		    
		    model1.addColumn("item_code");
		    model1.addColumn("item_name");
		    model1.addColumn("barcode"); 
		    model1.addColumn("category"); 
		    model1.addColumn("department");
		    model1.addColumn("unit");
		    model1.addColumn("sp");
		    model1.addColumn("output_tax");
		    model1.addColumn("sp_include_tax");
		    model1.addColumn("cost");
		    model1.addColumn("qty");
		    model1.addColumn("type"); 
		    model1.addColumn("input_tax"); 
		    model1.addColumn("po_inclusive_tax");
		    model1.addColumn("activeForSale"); 
		    model1.addColumn("activeForPurchase"); 
		    model1.addColumn("cf"); 
		    model1.addColumn("input_tax_rate"); 
		    model1.addColumn("output_tax_rate"); 
		    model1.addColumn("weighing_sts"); 
		    model1.addColumn("sub_cat");
		    model1.addColumn("sub_dept");
		   
		scrollPane1.setViewportView(table1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 627);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 87, 908, 489);
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(25, 0));
		scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 25));
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
		    
		    model.addColumn("S.No"); 
		    model.addColumn("Item Code"); 
		    model.addColumn("Item Name"); 
		    model.addColumn("Selling Price"); 
		    model.addColumn("Department"); 
			model.addColumn("Category"); 
			model.addColumn("Item Cost");
			model.addColumn("Qty"); 
			model.addColumn("Unit"); 
			model.addColumn("CF"); 
			model.addColumn("Barcode"); 
			
			//TableColumnModel tcm = table.getColumnModel();
			//tcm.removeColumn( tcm.getColumn(5) );
			
			
		scrollPane.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//table.getTableHeader().setReorderingAllowed(false);
		 //table.getTableHeader().setResizingAllowed(false);
		
		int cost_width=0;
		if(Mainpage.see_cost.equals("1"))
		{
			cost_width=100;
		}
		int qty_width=0;
		if(Mainpage.see_qty.equals("1"))
		{
			qty_width=80;
		}
		 
		 
        int colmns=model.getColumnCount();
//        JTableHeader header=table.getTableHeader();
//        header.setPreferredSize(new Dimension(0,50));
		for (int i=0; i<colmns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(40);
		  if (i==1) column.setPreferredWidth(80);
		  if (i==2) column.setPreferredWidth(450);
		  if (i==3) column.setPreferredWidth(100);
		  if (i==4) column.setPreferredWidth(100);
		  if (i==5) column.setPreferredWidth(90);
		  if(i==5) column.setResizable(false);
		  if (i==6) column.setPreferredWidth(cost_width);
		  //if (i==7) column.setPreferredWidth(40);
		  if (i==7) column.setPreferredWidth(qty_width);
		  if(i==7) column.setResizable(false);
		  if (i==8) column.setPreferredWidth(60);
		  if (i==9) column.setPreferredWidth(40);
		  if (i==10) column.setPreferredWidth(120);
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 908, 73);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JCheckBox checkBox = new JCheckBox("On Screen Keyboard");
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ad) {
				try {
					textField.requestFocus();
					if(ad.getStateChange() == ItemEvent.SELECTED) {
						//lblNewLabel.
						Runtime.getRuntime().exec("cmd /c osk");
					} 
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		checkBox.setBackground(new Color(243, 229, 216));
		checkBox.setBounds(8, 41, 145, 23);
		panel_2.add(checkBox);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(8, 14, 121, 22);
		comboBox_2.addItem("Item Name");
		comboBox_2.addItem("Item Code"); 
		comboBox_2.addItem("Barcode");
		comboBox_2.addItem("Selling Price");
		comboBox_2.addItem("Category"); 
		comboBox_2.addItem("Department");
		comboBox_2.addItem("Quantity");
		panel_2.add(comboBox_2);
		
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
				  
					if(chckbxAutoFilterItems.isSelected())
					{
						int rowCount = model.getRowCount();
						for (int i = rowCount - 1; i >= 0; i--) {
							model.removeRow(i);
						}
						first_page.setText("0");
						get_itemdisp( company,comboBox.getSelectedItem().toString(),textField.getText(),page_type,0,200,con,comboBox_2.getSelectedItem().toString());	
					}
					
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
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowCount = model.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
					model.removeRow(i);
				}
				first_page.setText("0");
				get_itemdisp( company,comboBox.getSelectedItem().toString(),textField.getText(),page_type,0,200,con,comboBox_2.getSelectedItem().toString());
			}
		});
		textField.setBounds(141, 13, 364, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		textField.requestFocus();
		
		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(135, 594, 77, 25);
		panel_1.add(label);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowCount = model.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
					model.removeRow(i);
				}
				first_page.setText("0");
				get_itemdisp( company,comboBox.getSelectedItem().toString(),textField.getText(),page_type,0,200,con,comboBox_2.getSelectedItem().toString());
			}
		});
		btnSearch.setBounds(518, 13, 97, 25);
		panel_2.add(btnSearch);
		
		
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("Item Code");
		comboBox.addItem("Item Name");
		comboBox.addItem("Category"); 
		comboBox.addItem("Department"); 
		comboBox.addItem("Selling Price");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!comboBox.getSelectedItem().toString().equals(""))
				{
					get_itemdisp( company,comboBox.getSelectedItem().toString(),textField.getText(),page_type,limit_end,200,con,comboBox_2.getSelectedItem().toString());	
				}
				
				
			}
		});
		comboBox.setBounds(769, 14, 127, 22);
		if(page_type.equals("Item_master"))
		{
			comboBox.setSelectedItem(Item_master.itemlist_filter);
		}
		 
		panel_2.add(comboBox);
		
		JLabel lblFilter = new JLabel("Sort By");
		lblFilter.setBounds(703, 17, 54, 16);
		panel_2.add(lblFilter);
		
		
		
		JLabel lblTotalItemsFound = new JLabel("Total Items  found");
		lblTotalItemsFound.setBounds(22, 598, 114, 16);
		panel_1.add(lblTotalItemsFound);
		
		
		
		
		JButton button = new JButton(">");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Integer.parseInt(first_page.getText())==Integer.parseInt(last_page.getText()))
				{
					
				}
				else
				{
					int f_txt=Integer.parseInt(first_page.getText())+1;
					int cnt=f_txt*200;
					first_page.setText(Integer.toString(f_txt));
					get_itemdisp( company,comboBox.getSelectedItem().toString(),textField.getText(),page_type,cnt,200,con,comboBox_2.getSelectedItem().toString());
				}
			}
		});
		button.setBounds(875, 589, 45, 30);
		panel_1.add(button);
		
		JButton button_1 = new JButton("<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Integer.parseInt(first_page.getText())==1)
				{
					int f_txt=Integer.parseInt(first_page.getText())-1;
					int cnt=f_txt*200;
					first_page.setText(Integer.toString(f_txt));
					get_itemdisp( company,comboBox.getSelectedItem().toString(),textField.getText(),page_type,cnt,200,con,comboBox_2.getSelectedItem().toString());
				}
				
				else
				{
					int f_txt=Integer.parseInt(first_page.getText())-1;
					int cnt=f_txt*200;
					first_page.setText(Integer.toString(f_txt));
					get_itemdisp( company,comboBox.getSelectedItem().toString(),textField.getText(),page_type,cnt,200,con,comboBox_2.getSelectedItem().toString());
				}
			}
		});
		button_1.setBounds(657, 589, 45, 30);
		panel_1.add(button_1);
		
		JLabel lblPage = new JLabel("Page");
		lblPage.setBounds(782, 572, 36, 25);
		panel_1.add(lblPage);
		
		first_page = new JLabel("0");
		first_page.setHorizontalAlignment(SwingConstants.RIGHT);
		first_page.setBounds(714, 589, 56, 25);
		panel_1.add(first_page);
		
		JLabel lblOf = new JLabel("of");
		lblOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblOf.setBounds(782, 589, 22, 25);
		panel_1.add(lblOf);
		
		last_page = new JLabel("New label");
		last_page.setHorizontalAlignment(SwingConstants.LEFT);
		last_page.setBounds(816, 589, 56, 25);
		panel_1.add(last_page);
		
		Statement statement=con.createStatement();
		String query="SELECT count(*) as ttl_item FROM "+company+".mr_tmp_item_master "; 
		ResultSet resultSet = statement.executeQuery(query);
		if(resultSet.next())
		{
			ttl_items=resultSet.getString("ttl_item");
			label.setText(ttl_items);
			if(!ttl_items.equals(""))
			{
				int sub_div=0;
				if(Integer.parseInt(ttl_items) % 2 == 0)
				{
					sub_div=Integer.parseInt(ttl_items)/200;
				}
				else
				{
					sub_div=(Integer.parseInt(ttl_items)/200);
				}
				last_page.setText(Integer.toString(sub_div));
				
			}
		}
		table.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e1) {
	    		if (e1.getKeyChar() == KeyEvent.VK_ENTER) {
	    			get_codes( con, company, db_name,  page_type, store_name, version,decimal,language);
	    		}
	    	}
	    });
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	
                	get_codes( con, company, db_name,  page_type, store_name, version,decimal,language);
                }
            }

			
        });
        
        textField.setText("");
        
        chckbxAutoFilterItems = new JCheckBox("Auto Filter Items");
        chckbxAutoFilterItems.setSelected(true);
        chckbxAutoFilterItems.setBounds(518, 40, 152, 25);
        panel_2.add(chckbxAutoFilterItems);
		int rowCount = model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		get_itemdisp( company,comboBox.getSelectedItem().toString(),textField.getText(),page_type,0,200,con,comboBox_2.getSelectedItem().toString());
		clickOnback( menuItem, "play", KeyEvent.VK_ESCAPE );
	}
	public static void clickOnback(final AbstractButton button, String actionName, int key)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6694624852420723982L;

			/**
			 * 
			 */
			public void actionPerformed( ActionEvent e )
	        {
				frame.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.btnPlu.setEnabled(true);
					PosscreenArabic.txtDisc.requestFocus();
				}
				else
				{
					posscreen.btnPlu.setEnabled(true);
					posscreen.txtDisc.requestFocus();
				}
				
	        }
	    } );
	}
	private void get_codes(Connection con,String company,String db_name, String page_type,String store_name,String version,int decimal, String language ) {
		int column = 1;
    	int rows = table.getSelectedRow();
    	String value = table.getModel().getValueAt(rows, column).toString();
    	if(page_type.equals("posscreen"))
    	{
    		String code="";
    		try {
				Statement statement=con.createStatement();
				String query11="SELECT * FROM "+company+".mr_tmp_item_master where item_code='"+table.getModel().getValueAt(rows, 1).toString()+"' and unit='"+table.getModel().getValueAt(rows, 8).toString()+"' "; 
				ResultSet resultSet = statement.executeQuery(query11);
				while(resultSet.next())
				{
					code=resultSet.getString("barcode");
					if(code.equals(""))
					{
						code=resultSet.getString("item_code");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		inventory_master.item_list_master.frame.setVisible(false);
    		//sales_master.posscreen.validate_barcode(company, table.getModel().getValueAt(rows, 1).toString(), "",store_name,"posscreen",con);
    		if(Mainpage.SoftLang.equals("Ar"))
			{
    			sales_master.PosscreenArabic.validate_barcode(company, code, "",store_name,"posscreen",con);
			}
			else
			{
				sales_master.posscreen.validate_barcode(company, code, "",store_name,"posscreen",con);
			}
    		
    		//posscreen.txtDisc.requestFocus();
    		if(Mainpage.SoftLang.equals("Ar"))
			{
    			PosscreenArabic.btnPlu.setEnabled(true);
			}
			else
			{
				posscreen.btnPlu.setEnabled(true);
			}
    		
    	}
    	else if(page_type.equals("return"))
    	{
    		String code="";
    		try {
				Statement statement=con.createStatement();
				String query11="SELECT * FROM "+company+".mr_tmp_item_master where item_code='"+table.getModel().getValueAt(rows, 1).toString()+"' and unit='"+table.getModel().getValueAt(rows, 8).toString()+"' "; 
				ResultSet resultSet = statement.executeQuery(query11);
				while(resultSet.next())
				{
					code=resultSet.getString("barcode");
					if(code.equals(""))
					{
						code=resultSet.getString("item_code");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(Mainpage.SoftLang.equals("Ar"))
			{
    			sales_master.PosscreenArabic.validate_barcode(company, code, "",store_name,"return",con);
        		PosscreenArabic.txtDisc.requestFocus();
        		PosscreenArabic.btnPlu.setEnabled(true);
			}
			else
			{
				sales_master.posscreen.validate_barcode(company, code, "",store_name,"return",con);
	    		posscreen.txtDisc.requestFocus();
	    		posscreen.btnPlu.setEnabled(true);
			}
    		
    	}
    	else if(page_type.equals("Inv_adj"))
    	{
    		inventory_master.Inv_adjustment.validate_barcode(db_name, company, table.getModel().getValueAt(rows, 1).toString(),table.getModel().getValueAt(rows, 10).toString(), "", "", version,store_name,decimal);
    	}
    	else if(page_type.equals("po"))
    	{
    		frame.setAlwaysOnTop(false);
    		purchase_master.purchase_order.validate_barcode(db_name, company, table.getModel().getValueAt(rows, 10).toString(), "", "", version,store_name,decimal,language);
    		
    	}
    	else if(page_type.equals("Inv_transfer"))
    	{
    		inventory_master.inventory_transfer.validate_barcode(db_name, company, table.getModel().getValueAt(rows, 1).toString(), "", version,store_name,Integer.toString(decimal));
    	}
    	else if(page_type.equals("po_report"))
    	{
    		frame.setAlwaysOnTop(false);
    		PO_reports.vendor_items_purchase.textField_1.setText( table.getModel().getValueAt(rows, 2).toString());
    		
    	}
    	else if(page_type.equals("adj_report"))
    	{
    		frame.setAlwaysOnTop(false);
    		InventoryAdjustmentReport.textField_1.setText( table.getModel().getValueAt(rows, 2).toString());
    		
    	}
    	else if(page_type.equals("pr"))
    	{
    		frame.setAlwaysOnTop(false);
    		purchase_master.purchase_return.validate_barcode(db_name, table.getModel().getValueAt(rows, 10).toString(), store_name,  version);
    		
    	}
    	else if(page_type.equals("sd"))
    	{
    		frame.setAlwaysOnTop(false);
    		sales_distribution.sd_lines.validate_barcode(db_name, table.getModel().getValueAt(rows, 10).toString(), "","",version,store_name,language);
    	}
    	else if(page_type.equals("SDpost"))
    	{
    		sales_distribution.sd_post_list.textField.setText(table.getModel().getValueAt(rows, 1).toString());
    		sales_distribution.sd_post_list.textField_1.setText(table.getModel().getValueAt(rows, 2).toString());
    	}
    	else if(page_type.equals("SDQupost"))
    	{
    		sales_distribution.sd_quotation_posted_list.textField.setText(table.getModel().getValueAt(rows, 1).toString());
    		sales_distribution.sd_quotation_posted_list.textField_1.setText(table.getModel().getValueAt(rows, 2).toString());
    	}
    	
    	else if(page_type.equals("history_item_sales_summary"))
    	{
    		History_Reports.Item_sale.textField_3.setText(table.getModel().getValueAt(rows, 2).toString());
    		History_Reports.Item_sale.textField_4.setText(table.getModel().getValueAt(rows, 1).toString());
    	}
    	else if(page_type.equals("sales_summary"))
    	{
    		History_Reports.sales_summary.textField_3.setText(table.getModel().getValueAt(rows, 2).toString());
    	}
    	else if(page_type.equals("Item_price"))
    	{
    		Cust_item_price.textField.setText(table.getModel().getValueAt(rows, 2).toString());
    		Cust_item_price.textField_1.setText(table.getModel().getValueAt(rows, 1).toString());
    		Cust_item_price.menuItem.setEnabled(true);
    	}
    	else if(page_type.equals("price"))
    	{
    		History_Reports.price_change.textField_1.setText(table.getModel().getValueAt(rows, 2).toString());
    	}
    	else if(page_type.equals("unit_change"))
    	{
    		sales_master.change_unit.get_item_bsd_name(company,table.getModel().getValueAt(rows, 1).toString());
    		sales_master.price_enquiry.get_item_bsd_name(company,table.getModel().getValueAt(rows, 1).toString());
    	}
    	else if(page_type.equals("dashboard_enquiry"))
    	{
    		CafePro.Price_enquiry_dash.get_item_bsd_name(company,table.getModel().getValueAt(rows, 1).toString());
    	}
    	else if(page_type.equals("Item_kit"))
    	{
    		inventory_master.kit_items.textField.setText(table.getModel().getValueAt(rows, 1).toString());
    		inventory_master.kit_items.textField_1.setText(table.getModel().getValueAt(rows, 2).toString());
    		inventory_master.kit_items.textField_2.setText(table.getModel().getValueAt(rows, 3).toString());
    		inventory_master.kit_items.textField_4.setText(table.getModel().getValueAt(rows, 6).toString());
    		inventory_master.kit_items.textField_7.setText(table.getModel().getValueAt(rows, 10).toString());
    		inventory_master.kit_items.textField_2.requestFocus();
    	}
    	else if(page_type.equals("promo"))
    	{
    		inventory_master.Offer_zone.textField.setText(table.getModel().getValueAt(rows, 1).toString());
    		inventory_master.Offer_zone.textField_1.setText(table.getModel().getValueAt(rows, 2).toString());
    	}
    	else
    	{
    		Item_master.itemlist_filter=comboBox.getSelectedItem().toString();

    		inventory_master.Item_master.get_itemdtls_bsd_code(company, value,decimal);
    		inventory_master.Item_master.frame.setAlwaysOnTop(true);
			inventory_master.Item_master.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			inventory_master.Item_master.menuItem.setEnabled(true);
    	}
    	inventory_master.item_list_master.frame.setVisible(false);
	}
	public static void  export_as_csv( String company) throws IOException, SQLException
	{
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\Itemmaster_export.csv");

			for(int x = 0; x < model.getColumnCount(); x++){
	            excel.write(model.getColumnName(x) );
	            excel.write(",");
	        }

	        excel.write("\n");

	        for(int x=0; x< model.getRowCount(); x++) {
	            for(int y=0; y < model.getColumnCount(); y++) {
	                excel.write(model.getValueAt(x,y).toString());
	                if(y < model.getColumnCount()-1)
	                {
	                	excel.write(",");
	                }
	                else
	                {
	                	excel.write("\r\n");
	                }
	            }
	        }

	        excel.close();
	        frame.setCursor(Cursor.getDefaultCursor());
	        JOptionPane.showMessageDialog(frame, "Export Completed");
		
	}
	public static void get_symc_itms(String company)
	{
		try 
		{
			Connection con1=Dbsetup.dbconn.getConnection();
			Statement statement3 = con1.createStatement();
			
			ResultSet resultSet13 = statement3.executeQuery("select * from "+company+".mr_tmp_item_master ");
			if(resultSet13.next())
			{
				String query="TRUNCATE TABLE "+company+".mr_tmp_item_master ";
				statement3.executeUpdate(query);
			}
			ResultSet resultSet2 = statement3.executeQuery("select * from "+company+".mr_item_master ");
			if(resultSet2.next())
			{
				String query1="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode1,category,department,unit1,sp1,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf1,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode1!='' and sp1!='' and  cf1!='' ";
				statement3.executeUpdate(query1);
				
				String query2="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode2,category,department,unit1,sp1,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf1,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode2!='' and sp1!='' and  cf1!='' ";
				statement3.executeUpdate(query2);
				
				String query3="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode3,category,department,unit1,sp1,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf1,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode3!='' and sp1!='' and  cf1!='' ";
				statement3.executeUpdate(query3);
			
				String query4="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode4,category,department,unit2,sp2,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf2,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode4!='' and sp2!='' and  cf2!=''  ";
				statement3.executeUpdate(query4);
				
				String query5="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode5,category,department,unit2,sp2,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf2,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode5!='' and sp2!='' and  cf2!='' ";
				statement3.executeUpdate(query5);
				
				String query6="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode6,category,department,unit2,sp2,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf2,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode6!=''  and sp2!='' and  cf2!='' ";
				statement3.executeUpdate(query6);
				
				String query7="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode7,category,department,unit3,sp3,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf3,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode7!='' and sp3!='' and  cf3!='' ";
				statement3.executeUpdate(query7);
				
				String query8="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode8,category,department,unit3,sp3,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf3,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode8!='' and sp3!='' and  cf3!='' ";
				statement3.executeUpdate(query8);
				
				String query9="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode9,category,department,unit3,sp3,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf3,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode9!='' and sp3!='' and  cf3!='' ";
				statement3.executeUpdate(query9);
				
				String query10="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode10,category,department,unit4,sp4,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf4,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode10!='' and sp4!='' and  cf4!='' ";
				statement3.executeUpdate(query10);
				
				String query11="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode11,category,department,unit4,sp4,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf4,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode11!='' and sp4!='' and  cf4!='' ";
				statement3.executeUpdate(query11);
				
				String query12="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode12,category,department,unit4,sp4,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf4,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode12!='' and sp4!='' and  cf4!='' ";
				statement3.executeUpdate(query12);
				
				String query13="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode13,category,department,unit5,sp5,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf5,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode13!='' and sp5!='' and  cf5!='' ";
				statement3.executeUpdate(query13);
				
				String query14="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode14,category,department,unit5,sp5,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf5,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode14!='' and sp5!='' and  cf5!='' ";
				statement3.executeUpdate(query14);
				
				String query15="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode15,category,department,unit5,sp5,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf5,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode15!='' and sp5!='' and  cf5!='' ";
				statement3.executeUpdate(query15);
				
				String query16="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode16,category,department,unit6,sp6,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf6,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode16!='' and sp6!='' and  cf6!='' ";
				statement3.executeUpdate(query16);
				
				String query17="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode17,category,department,unit6,sp6,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf6,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode17!='' and sp6!='' and  cf6!='' ";
				statement3.executeUpdate(query17);
				
				String query18="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`,type, `input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) select item_code,item_name,barcode18,category,department,unit6,sp6,output_tax,sp_include_tax,item_cost,total_qty,item_type,`input_tax`,`po_inclusive_tax`,`activeForSale`, `activeForPurchase`,cf6,input_tax_rate,output_tax_rate,weighing_sts,sub_category,sub_department,commited_qty,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty from "+company+".mr_item_master where barcode18!='' and sp6!='' and  cf6!='' ";
				statement3.executeUpdate(query18);
				
				JOptionPane.showMessageDialog(frame, "Sync Completed");
			}
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frame, e);
		}
	}
	private void  get_itemdisp( String company, String filter, String search, String page_type,int page_st, int page_end, Connection con,String serch_type)
	{
		
		String filter_name = "";
		String serch_str = "";
		if(filter=="Item Name")
		{
			filter_name="ORDER BY "+company+".mr_tmp_item_master.item_name ASC";
		}
		else if(filter=="Item Code")
		{
			filter_name="ORDER BY "+company+".mr_tmp_item_master.item_code ASC";
		}
		else if(filter=="Category")
		{
			filter_name="ORDER BY "+company+".mr_tmp_item_master.category ASC";
		}
		else if(filter=="Department")
		{
			filter_name="ORDER BY "+company+".mr_tmp_item_master.department ASC";
		}
		else if(filter=="Selling Price")
		{
			filter_name="";
		}
		
		
		if(!search.equals(""))
		{
			if(serch_type.equals("Item Name"))
			{
				serch_str="WHERE "+company+".mr_tmp_item_master.item_name LIKE '%"+search+"%'";
			}
			else if(serch_type.equals("Barcode"))
			{
				serch_str="WHERE "+company+".mr_tmp_item_master.barcode like '%"+search+"%' ";
			}
			else if(serch_type.equals("Item Code"))
			{
				serch_str="WHERE "+company+".mr_tmp_item_master.item_code like '%"+search+"%'  ";
			}
			else if(serch_type.equals("Selling Price"))
			{
				serch_str="WHERE "+company+".mr_tmp_item_master.sp like '"+search+"' ";
			}
			else if(serch_type.equals("Category"))
			{
				serch_str="WHERE "+company+".mr_tmp_item_master.category LIKE '%"+search+"%' ";
			}
			else if(serch_type.equals("Department"))
			{
				serch_str="WHERE "+company+".mr_tmp_item_master.department LIKE '%"+search+"%' ";
			}
			else if(serch_type.equals("Quantity"))
			{
				serch_str="WHERE "+company+".mr_tmp_item_master.qty = '"+search+"' ";
			}
			
		}
		
		try
		{
			Statement statement=con.createStatement();
			String query1="SELECT count(DISTINCT item_code) as ttl_item FROM "+company+".mr_tmp_item_master "+serch_str+" "; 
			ResultSet resultSet1 = statement.executeQuery(query1);
			if(resultSet1.next())
			{
				ttl_items=resultSet1.getString("ttl_item");

				label.setText(ttl_items);
				if(!ttl_items.equals(""))
				{
					int sub_div=0;
					if(Integer.parseInt(ttl_items) % 2 == 0)
					{
						sub_div=Integer.parseInt(ttl_items)/200;
					}
					else
					{
						sub_div=(Integer.parseInt(ttl_items)/200);
					}
					last_page.setText(Integer.toString(sub_div));
					
				}
			}
			//
			String query="SELECT * FROM "+company+".mr_tmp_item_master "+serch_str+" group by item_code,item_vareint "+filter_name+" limit "+page_st+","+page_end+"  "; 
			ResultSet resultSet13 = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet13.next())
			{
				String query11="SELECT * FROM "+company+".mr_tmp_item_master "+serch_str+" group by item_code,item_vareint "+filter_name+" limit "+page_st+","+page_end+"  "; 
				ResultSet resultSet = statement.executeQuery(query11);
				while(resultSet.next())
				{
					String code=resultSet.getString("item_code");
	                String name=resultSet.getString("item_name");
	                String category=resultSet.getString("category");
	                String department=resultSet.getString("department");
	                String item_cost=resultSet.getString("cost");
	                String selling_price=resultSet.getString("sp");
	                String cf=resultSet.getString("cf");
	                double qty=0;
	                if(!cf.equals("0"))
	                {
	                	qty=Double.parseDouble(resultSet.getString("qty"))/Double.parseDouble(cf);
		                BigDecimal bv = new BigDecimal(qty);
		        		bv = bv.setScale(2, BigDecimal.ROUND_HALF_UP);
		        		qty = bv.doubleValue();	
	                }
	                String unit=resultSet.getString("unit");
	                String barcode=resultSet.getString("barcode");
	                if(page_type=="po")
					{
	                	String activeForPurchase=resultSet.getString("activeForPurchase");
	                	if(activeForPurchase.equals("1"))
	                	{
	                		model.addRow(new Object[]{model.getRowCount()+1+(Integer.parseInt(first_page.getText())*200), code, name,selling_price,department,category,item_cost,qty,unit,cf,barcode});
	                	}
					}
	                else if(page_type=="return")
	                {
	                	String activeForSale=resultSet.getString("activeForSale");
	                	if(activeForSale.equals("1"))
	                	{
	                		model.addRow(new Object[]{model.getRowCount()+1+((Integer.parseInt(first_page.getText())*200)), code, name,selling_price,department,category,item_cost,qty,unit,cf,barcode});
	                	}
	                }
	                else if(page_type=="posscreen")
	                {
	                	String activeForSale=resultSet.getString("activeForSale");
	                	if(activeForSale.equals("1"))
	                	{
	                		int cnt=Integer.parseInt(first_page.getText());
	                		model.addRow(new Object[]{model.getRowCount()+1+((cnt)*200), code, name,selling_price,department,category,item_cost,qty,unit,cf,barcode});
	                	}
	                	//model.addRow(new Object[]{model.getRowCount()+1+((Integer.parseInt(comboBox_1.getSelectedItem().toString()))*200), code, name,department,category,item_cost,selling_price,qty,unit});	
	                }
	                else
	                {
	                	int cnt=Integer.parseInt(first_page.getText());
                		model.addRow(new Object[]{model.getRowCount()+1+((cnt)*200), code, name,selling_price,department,category,item_cost,qty,unit,cf,barcode});	
	                }
	                
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "Item Not Found");
				textField.setText("");
				first_page.setText("0");
				//get_itemdisp( company,comboBox.getSelectedItem().toString(),textField.getText(),page_type,Integer.parseInt(comboBox_1.getSelectedItem().toString())*200,200,con,comboBox_2.getSelectedItem().toString());
				
			}
		}
		catch(Exception e){
			//frame.setAlwaysOnTop(true);
			//JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
			//frame.setAlwaysOnTop(false);
		}
	}
}
