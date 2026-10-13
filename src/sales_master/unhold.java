package sales_master;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JDialog;
import org.json.JSONException;

import CafePro.Mainpage;

import javax.swing.JComboBox;

public class unhold {

	private static JDialog frame;
	private static DefaultTableModel model;
	private JTable table;
	static JTextField textField;
	static JComboBox<String> comboBox;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					JFrame testParent = new JFrame();
					unhold window = new unhold(testParent, "mr_pro", "admin","S01","S0101", null);
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
	public unhold(JFrame parent,String company, String username, String store_name, String reg_nme,String version) throws JSONException {
		initialize(parent,company,  username, store_name,reg_nme, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param reg_nme 
	 * @param store_name2 
	 */
	private void initialize(final JFrame parent,final String company,final String username,final String store_name, final String reg_nme,final String version) throws JSONException {
		frame = new JDialog(parent, version, true);
		frame.setResizable(false);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 909, 696);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btnUnhold.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btnUnhold.setEnabled(true);
				}
				
			}
//			@Override
//		      public void windowIconified(WindowEvent we) {
//		        frame.setState(JFrame.NORMAL);
//		      }
		});
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(parent);
		//frame.setAlwaysOnTop(true);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 893, 88);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Hold Invoice List");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(8, 7, 228, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btnUnhold.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btnUnhold.setEnabled(true);
				}
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(737, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				comboBox.setSelectedItem("ALL");
				get_itemdisp(company,textField.getText(), reg_nme,comboBox.getSelectedItem().toString(),store_name);
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(805, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 87, 893, 562);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 25));
		scrollPane.setBounds(12, 52, 881, 510);
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
		    table.setRowHeight(40);
		    
		    if(Mainpage.SoftLang.equals("Ar"))
			{
		    	if(PosscreenArabic.change_hold_type.equals("1"))
		    	{
		    		model.addColumn("S.No"); 
				    model.addColumn("Register No."); 
				    model.addColumn("Receipt No."); 
				    model.addColumn("Delivery Boy");
					model.addColumn("Mode"); 
					model.addColumn("Table"); 
					model.addColumn("User"); 
					model.addColumn("Date Time"); 
					model.addColumn("Invoice Total");
				    model.addColumn("Customer Code");
				    model.addColumn("Customer Name");
				    model.addColumn("Customer Ph");
					
		    	}
		    	else
		    	{
		    		model.addColumn("S.No"); 
				    model.addColumn("Register No."); 
				    model.addColumn("Receipt No."); 
				    model.addColumn("Customer Code");
				    model.addColumn("Customer Name");
				    model.addColumn("Customer Ph");
					model.addColumn("Invoice Total"); 
					model.addColumn("Date Time"); 
					model.addColumn("Delivery Boy");
					model.addColumn("Mode"); 
					model.addColumn("Table"); 
					model.addColumn("User"); 
		    	}
			}
			else
			{
				if(posscreen.change_hold_type.equals("1"))
		    	{
					model.addColumn("S.No"); 
				    model.addColumn("Register No."); 
				    model.addColumn("Receipt No."); 
				    model.addColumn("Delivery Boy");
					model.addColumn("Mode"); 
					model.addColumn("Table"); 
					model.addColumn("User"); 
					model.addColumn("Date Time");
					model.addColumn("Invoice Total");
				    model.addColumn("Customer Code");
				    model.addColumn("Customer Name");
				    model.addColumn("Customer Ph");
					
		    	}
		    	else
		    	{
		    		model.addColumn("S.No"); 
				    model.addColumn("Register No."); 
				    model.addColumn("Receipt No."); 
				    model.addColumn("Customer Code");
				    model.addColumn("Customer Name");
				    model.addColumn("Customer Ph");
					model.addColumn("Invoice Total"); 
					model.addColumn("Date Time"); 
					model.addColumn("Delivery Boy");
					model.addColumn("Mode"); 
					model.addColumn("Table"); 
					model.addColumn("User"); 
		    	}
			}
		    
		    
		    
		    
			
			scrollPane.setViewportView(table);
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        int colmns=model.getColumnCount();
			for (int i=0; i<colmns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(40);
			  if (i==1) column.setPreferredWidth(80);
			  if (i==2) column.setPreferredWidth(100);
			  if (i==3) column.setPreferredWidth(100);
			  if(Mainpage.SoftLang.equals("Ar"))
				{
			    	if(PosscreenArabic.change_hold_type.equals("1"))
			    	{
			    		if (i==4) column.setPreferredWidth(100);
			    	}
			    	else
			    	{
			    		if (i==4) column.setPreferredWidth(240);
			    	}
				}
			  else
			  {
				  if(posscreen.change_hold_type.equals("1"))
			    	{
			    		if (i==4) column.setPreferredWidth(100);
			    	}
			    	else
			    	{
			    		if (i==4) column.setPreferredWidth(240);
			    	}
			  }
			  
			  if (i==5) column.setPreferredWidth(100);
			  if (i==6) column.setPreferredWidth(100);
			  if (i==7) column.setPreferredWidth(150);
			  if (i==8) column.setPreferredWidth(120);
			  if (i==9) column.setPreferredWidth(100);
			  if(Mainpage.SoftLang.equals("Ar"))
				{
			    	if(PosscreenArabic.change_hold_type.equals("1"))
			    	{
			    		if (i==10) column.setPreferredWidth(240);
			    	}
			    	else
			    	{
			    		if (i==10) column.setPreferredWidth(90);
			    	}
				}
			  else
			  {
				  if(posscreen.change_hold_type.equals("1"))
				  	{
			    		if (i==10) column.setPreferredWidth(240);
			    	}
			    	else
			    	{
			    		if (i==10) column.setPreferredWidth(90);
			    	}
			  }
			  
			  if (i==11) column.setPreferredWidth(100);
			}
		
		
		table.setDefaultRenderer(Object.class, new TableCellRenderer(){
            private DefaultTableCellRenderer DEFAULT_RENDERER =  new DefaultTableCellRenderer();
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if(Mainpage.SoftLang.equals("Ar"))
				{
                	if(PosscreenArabic.change_hold_type.equals("1"))
                    {
                 	   if (table.getValueAt(row, 4).equals("HOME DELIVERY") && !table.getValueAt(row, 5).equals("")){
                        	c.setBackground(Color.green);
                            c.setForeground(Color.BLACK);
                        }
                        else {
                            
                            c.setBackground(Color.WHITE);
                            c.setForeground(Color.BLACK);
                        }
                        if (isSelected) {
                        	c.setBackground(Color.YELLOW);
                        } 
                    }
				}
				else
				{
					if(posscreen.change_hold_type.equals("1"))
		               {
		            	   if (table.getValueAt(row, 4).equals("HOME DELIVERY") && !table.getValueAt(row, 5).equals("")){
		                   	c.setBackground(Color.green);
		                       c.setForeground(Color.BLACK);
		                   }
		                   else {
		                       
		                       c.setBackground(Color.WHITE);
		                       c.setForeground(Color.BLACK);
		                   }
		                   if (isSelected) {
		                   	c.setBackground(Color.YELLOW);
		                   } 
		               }
				}
                
			return c;
                
            }
        });
		
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		int columns=model.getColumnCount();
//		for (int i=0; i<columns; i++) {
//			  TableColumn column = table.getColumnModel().getColumn(i);
//			  if (i==0) column.setPreferredWidth(40);
//			  if (i==1) column.setPreferredWidth(80);
//			  if (i==2) column.setPreferredWidth(100);
//			  if (i==3) column.setPreferredWidth(100);
//			  if (i==4) column.setPreferredWidth(240);
//			  if (i==5) column.setPreferredWidth(100);
//			  if (i==6) column.setPreferredWidth(100);
//			  if (i==7) column.setPreferredWidth(100);
//			  if (i==8) column.setPreferredWidth(120);
//			  if (i==9) column.setPreferredWidth(100);
//			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 0, 881, 52);
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
					get_itemdisp( company,textField.getText(), reg_nme,comboBox.getSelectedItem().toString(),store_name);
			  }
			});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemdisp( company,textField.getText(), reg_nme,comboBox.getSelectedItem().toString(),store_name);
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
		textField.setBounds(171, 13, 593, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemdisp( company,textField.getText(), reg_nme,comboBox.getSelectedItem().toString(),store_name);
			}
		});
		btnSearch.setBounds(774, 13, 97, 25);
		panel_2.add(btnSearch);
		textField.requestFocus();
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemdisp( company,textField.getText(), reg_nme,comboBox.getSelectedItem().toString(),store_name);
			}
		});
		comboBox.setBounds(10, 15, 150, 20);
		panel_2.add(comboBox);
		comboBox.addItem("ALL");
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="select mode from "+company+".mr_pos_hold_invoice group by mode ";
			ResultSet resultSet = statement.executeQuery(query);
			String table_num = "";
			while(resultSet.next())
			{
				table_num=resultSet.getString("mode");
				comboBox.addItem(table_num);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		get_itemdisp( company,"", reg_nme,comboBox.getSelectedItem().toString(),store_name);
		
		
		
		table.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e1) {
	    		if (e1.getKeyChar() == KeyEvent.VK_ENTER) {
	    			int column = 2;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	Connection con=Dbsetup.local_server.getConnection();
                	if(Mainpage.SoftLang.equals("Ar"))
    				{
                		sales_master.PosscreenArabic.get_hold_invoice_dt(company,value,table.getModel().getValueAt(rows, 1).toString(),con);
                        PosscreenArabic.btnUnhold.setEnabled(true);
    				}
    				else
    				{
    					sales_master.posscreen.get_hold_invoice_dt(company,value,table.getModel().getValueAt(rows, 1).toString(),con);
                        posscreen.btnUnhold.setEnabled(true);
    				}
                    
                	frame.setVisible(false);
	    		}
	    	}
	    });
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 2;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	Connection con=Dbsetup.local_server.getConnection();
                	if(Mainpage.SoftLang.equals("Ar"))
    				{
                		sales_master.PosscreenArabic.get_hold_invoice_dt(company,value,table.getModel().getValueAt(rows, 1).toString(),con);
                        PosscreenArabic.btnUnhold.setEnabled(true);
    				}
    				else
    				{
    					sales_master.posscreen.get_hold_invoice_dt(company,value,table.getModel().getValueAt(rows, 1).toString(),con);
                        posscreen.btnUnhold.setEnabled(true);
    				}
                	
                	frame.setVisible(false);
                }
            }
        });
        clickOnback( menuItem, "play", KeyEvent.VK_ESCAPE );
//        if(posscreen.change_hold_type.equals("1"))
//        {
        JButton btnNewButton = new JButton("Assign Delivery");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
					new delivery_guy(company,  store_name, version, username,  "");
					frame.setVisible(false);
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.btnUnhold.setEnabled(true);
					}
					else
					{
						posscreen.btnUnhold.setEnabled(true);
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        btnNewButton.setBounds(596, 7, 133, 36);
        panel.add(btnNewButton);
 //       }
//       if(posscreen.change_hold_type.equals("1"))
//        {
    	   JButton btnNewButton1 = new JButton("Print Duplicate KOT");
           btnNewButton1.addActionListener(new ActionListener() {
           	public void actionPerformed(ActionEvent arg0) {
           		try {
           			int tbl_sel=table.getSelectedRow();
           			if(tbl_sel== -1)
           			{
           				JOptionPane.showMessageDialog(frame, "Choose the row do you want to print duplicate KOT");
           			}
           			else
           			{
           				Connection con=Dbsetup.local_server.getConnection();
           				Statement statement = con.createStatement();
           				Statement statement1 = con.createStatement();
           				String reg = table.getModel().getValueAt(tbl_sel, 1).toString();
           				String receipt = table.getModel().getValueAt(tbl_sel, 2).toString();
           				
           				String dept="select department from "+company+".mr_pos_hold_invoice_items where ref_reg_no='"+reg+"' and  ref_receipt_num='"+receipt+"'  and print_sts='1'  group by department ";
   						ResultSet resultSet1 = statement.executeQuery(dept);
   						if(resultSet1.next())
   						{
   							String printer_dept="select printer from "+company+".mr_assign_kot_printer where reg_nme='"+reg+"' and dept_name='"+resultSet1.getString("department")+"' ";
   							ResultSet resultSet2 = statement1.executeQuery(printer_dept);
   							if(resultSet2.next())
   							{
   								String printer=resultSet2.getString("printer");
   								report_master.holded_invoice.report(receipt, con, printer,reg,"",company,"dup_kot_invoice",resultSet1.getString("department"),"");	
   							}
   							else
   							{
   								JOptionPane.showMessageDialog(frame, "Printer Not Assigned For This Department");
   							}
   						}
           				frame.setVisible(false);
           				if(Mainpage.SoftLang.equals("Ar"))
        				{
           					PosscreenArabic.btnUnhold.setEnabled(true);
        				}
        				else
        				{
        					posscreen.btnUnhold.setEnabled(true);
        				}
       					
           			}
   				} catch (Exception e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
   				}
           	}
           });
           btnNewButton1.setBounds(244, 7, 164, 36);
           panel.add(btnNewButton1);
//        }
        
        
        JButton btnNewButton_1 = new JButton("Settle Delivery Invoices");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
        			if(Mainpage.SoftLang.equals("Ar"))
    				{
        				new Delivery_boys_invoices(company, username, store_name,PosscreenArabic.reg_nme ,version);
    				}
    				else
    				{
    					new Delivery_boys_invoices(company, username, store_name,posscreen.reg_nme ,version);
    				}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
        	}
        });
        btnNewButton_1.setBounds(416, 7, 172, 36);
        panel.add(btnNewButton_1);
        
        
//        if(posscreen.change_hold_type.equals("1"))
//        {
        	JButton btnChangeMode = new JButton("Change Mode");
            btnChangeMode.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent arg0) {
            		try {
            			int tbl_sel=table.getSelectedRow();
            			if(tbl_sel== -1)
            			{
            				JOptionPane.showMessageDialog(frame, "Choose the row do you want to Change the Mode");
            			}
            			else
            			{
            				
            					Connection con=Dbsetup.dbconn.getConnection();
                				String reg = table.getModel().getValueAt(tbl_sel, 1).toString();
                				String receipt = table.getModel().getValueAt(tbl_sel, 2).toString();
                				frame.setVisible(false);
                				new Change_mode(company, username,store_name,reg,con, version,receipt);
            		        
            				
            			}
    				} catch (Exception e) {
    					// TODO: handle exception
    				}
            	}
            });
            btnChangeMode.setBounds(244, 46, 164, 36);
            panel.add(btnChangeMode);
            
            
//        }
        JButton btnChangeTable = new JButton("Change Table");
        btnChangeTable.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
        			int tbl_sel=table.getSelectedRow();
        			if(tbl_sel== -1)
        			{
        				JOptionPane.showMessageDialog(frame, "Choose the row do you want to Change the Mode");
        			}
        			else
        			{
	        			String holdreg = table.getModel().getValueAt(tbl_sel, 1).toString();
	        			String oldtable = table.getModel().getValueAt(tbl_sel, 5).toString();
	        			int rows = table.getSelectedRow();
	                	String value = table.getModel().getValueAt(rows, 2).toString();
	                	Connection con=Dbsetup.local_server.getConnection();
	                	if(Mainpage.SoftLang.equals("Ar"))
	    				{
	                		sales_master.PosscreenArabic.get_hold_invoice_dt(company,value,table.getModel().getValueAt(rows, 1).toString(),con);
		                    PosscreenArabic.btnUnhold.setEnabled(true);
	    				}
	    				else
	    				{
	    					sales_master.posscreen.get_hold_invoice_dt(company,value,table.getModel().getValueAt(rows, 1).toString(),con);
		                    posscreen.btnUnhold.setEnabled(true);
	    				}
	                	
						new dine_in(company, store_name, "", username, holdreg,oldtable);
						
           				frame.setVisible(false);
        			}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        btnChangeTable.setBounds(596, 46, 133, 36);
        panel.add(btnChangeTable);
        
        JButton btnSplitTable_1 = new JButton("Split Table");
        btnSplitTable_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
					new SplitTable(company,  username, store_name,reg_nme, version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        btnSplitTable_1.setBounds(416, 46, 172, 36);
        panel.add(btnSplitTable_1);
        
        frame.setVisible(true);
        
    }
//	}
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
					PosscreenArabic.txtDisc.requestFocus();
				}
				else
				{
					posscreen.txtDisc.requestFocus();
				}
				
	        }
	    } );
	}
	static void  get_itemdisp(String company, String search, String reg_num, String sort, String store_name )
	{
		String sort_str="";
		if(!sort.equals("ALL"))
		{
			sort_str="and mode='"+sort+"'";
		}
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and (reg_num LIKE '%"+search+"%' OR receipt_no LIKE '%"+search+"%' OR cust_code LIKE '%"+search+"%' OR cust_name LIKE '%"+search+"%' OR cust_ph LIKE '%"+search+"%' OR total LIKE '%"+search+"%') ";
		}
		try{
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			String query="";
			if(Mainpage.SoftLang.equals("Ar"))
			{
				if(PosscreenArabic.unhold_reg_hold_trans.equals("1"))
				{
					query="SELECT * FROM "+company+".mr_pos_hold_invoice where store_id='"+store_name+"' "+serch_str+" "+sort_str+" order by receipt_no desc  ";
				}
				else
				{
					query="SELECT * FROM "+company+".mr_pos_hold_invoice where reg_num='"+reg_num+"' "+serch_str+" "+sort_str+" order by receipt_no desc  ";
				}
			}
			else
			{
				if(posscreen.unhold_reg_hold_trans.equals("1"))
				{
					query="SELECT * FROM "+company+".mr_pos_hold_invoice where store_id='"+store_name+"' "+serch_str+" "+sort_str+" order by receipt_no desc  ";
				}
				else
				{
					query="SELECT * FROM "+company+".mr_pos_hold_invoice where reg_num='"+reg_num+"' "+serch_str+" "+sort_str+" order by receipt_no desc  ";
				}
			}
			
			 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				ResultSet resultSet1 = statement.executeQuery(query);
				while(resultSet1.next())
				{
					String ref_reg_no=resultSet1.getString("reg_num");
					String receipt_no=resultSet1.getString("receipt_no");
	                String cust_code=resultSet1.getString("cust_code");
	                String cust_name=resultSet1.getString("cust_name");
	                String cust_ph=resultSet1.getString("cust_ph");
	                String total=resultSet1.getString("total");
	                String created_date=resultSet1.getString("created_date");
	                String time=resultSet1.getString("time");
	                String table_num=resultSet1.getString("table_num");
	                String delivery_user=resultSet1.getString("delivery_user");
	                String mode=resultSet1.getString("mode");
	                String user=resultSet1.getString("user");
	                
	                
	                if(Mainpage.SoftLang.equals("Ar"))
	    			{
	    		    	if(PosscreenArabic.change_hold_type.equals("1"))
	    		    	{
	    		    		model.addRow(new Object[]{model.getRowCount()+1, ref_reg_no,receipt_no,delivery_user,mode,table_num,user,created_date+" "+time,total,cust_code, cust_name,cust_ph});
	    		    	}
	    		    	else
	    		    	{
	    		    		model.addRow(new Object[]{model.getRowCount()+1, ref_reg_no,receipt_no,cust_code, cust_name,cust_ph,total,created_date+" "+time,delivery_user,mode,table_num,user});
	    		    	}
	    			}
	                else
	                {
	                	if(posscreen.change_hold_type.equals("1"))
	    		    	{
	                		model.addRow(new Object[]{model.getRowCount()+1, ref_reg_no,receipt_no,delivery_user,mode,table_num,user,created_date+" "+time,total,cust_code, cust_name,cust_ph});
	    		    	}
	    		    	else
	    		    	{
	    		    		model.addRow(new Object[]{model.getRowCount()+1, ref_reg_no,receipt_no,cust_code, cust_name,cust_ph,total,created_date+" "+time,delivery_user,mode,table_num,user});
	    		    	}
	                }
	                
	                
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No UNHOLD List Found");
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
	

