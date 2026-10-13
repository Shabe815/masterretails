package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import CafePro.Mainpage;

public class invoice_list {

	private static JFrame frame;
	private DefaultTableModel model;
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
					invoice_list window = new invoice_list("mr_pro", "admin","S01","S0101", null,"","");
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
	public invoice_list(String company, String username, String store_name, String reg_nme,String version,String delivery_boy_code,String delivery_boy) throws JSONException {
		initialize(company,  username, store_name,reg_nme, version,delivery_boy_code,delivery_boy);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param reg_nme 
	 * @param store_name2 
	 */
	private void initialize(final String company,final String username,final String store_name, final String reg_nme,final String version,String delivery_boy_code,final String delivery_boy) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 909, 696);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 893, 60);
		frame.getContentPane().add(panel);
		
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Save");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Object[] item_list = new String[model.getRowCount()];
		    		boolean status=false;
		    		Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement(); 
					for (int i = 0; i < model.getRowCount(); i++) {
						Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
						String dataCol1 = model.getValueAt(i, 1).toString();
						if (chked) {
							item_list[i]=dataCol1;
							status=true;
							if(Mainpage.SoftLang.equals("Ar"))
							{
								String query="update "+company+".mr_pos_hold_invoice set delivery_user='"+delivery_boy+"' where reg_num='"+PosscreenArabic.reg_nme+"' and receipt_no='"+item_list[i].toString()+"' ";
								statement.executeUpdate(query);
								report_master.holded_invoice.report(item_list[i].toString(), con, PosscreenArabic.printer,reg_nme,"",company,"Delivery","","");
							}
							else
							{
								String query="update "+company+".mr_pos_hold_invoice set delivery_user='"+delivery_boy+"' where reg_num='"+posscreen.reg_nme+"' and receipt_no='"+item_list[i].toString()+"' ";
								statement.executeUpdate(query);
								report_master.holded_invoice.report(item_list[i].toString(), con, posscreen.printer,reg_nme,"",company,"Delivery","","");
							}
							
						}
					}
					if(status==true)
					{
						JOptionPane.showMessageDialog(frame, "Saved Successfully");
						frame.setVisible(false);
						String invoicelist="";
						for(int i=0;i<item_list.length;i++)
						{
							if(item_list[i]!=null)
							{
								if(!invoicelist.equals(""))
								{
									invoicelist+="',";
								}
								if(!invoicelist.equals(""))
								{
									invoicelist+="'";
								}
								invoicelist+=item_list[i];
							}
						}
						for(int i=0;i<2;i++)
						{
							if(Mainpage.SoftLang.equals("Ar"))
							{
								report_master.delivery_invoice_list.report( con, PosscreenArabic.printer,reg_nme,delivery_boy,company,invoicelist);
							}
							else
							{
								report_master.delivery_invoice_list.report( con, posscreen.printer,reg_nme,delivery_boy,company,invoicelist);
							}
							
						}
						
//						posscreen.page_type="Hold Invoice";
//						posscreen.hold_inv=true;
//						posscreen.hold_inv(company, username,store_name, reg_nme, con,posscreen.label.getText(),version,posscreen.label_1.getText(),"CHECK PRINT");
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Choose Invoice from Below table");
					}
					
					
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(frame, e);
				}
			}
		});
	    menuItem.setBorder(null);
	    menuItem.setIcon(new ImageIcon(save));
	    menuItem.setBackground(new Color(243, 229, 216));
	    menuItem.setBounds(811, 7, 135, 48);
	    panel.add(menuItem);
		
		JLabel lblItemMasterList = new JLabel("Invoice List");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem_2 = new JMenuItem("Exit");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(661, 7, 60, 48);
		menuItem_2.setIcon(new ImageIcon(exit));
		panel.add(menuItem_2);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				get_itemdisp(company,textField.getText(), reg_nme);
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(725, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 893, 589);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 64, 881, 512);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel_1.add(scrollPane);

		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
				if(column==0)
		        {
		        	return true;  
		        }
				return false;  
		    }
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				
				default:
					return String.class;
				}
			}
		};
		    table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
		    table.setRowHeight(40);
		    
		    model.addColumn("S.No"); 
		    model.addColumn("Receipt No."); 
		    model.addColumn("Customer Code");
		    model.addColumn("Customer Name"); 
			model.addColumn("Invoice Total"); 
			model.addColumn("Date Time"); 
			model.addColumn("Mode"); 
			model.addColumn("Table"); 
			model.addColumn("Type"); 
		scrollPane.setViewportView(table);
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(40);
			  if (i==1) column.setPreferredWidth(80);
			  if (i==2) column.setPreferredWidth(100);
			  if (i==3) column.setPreferredWidth(240);
			  if (i==4) column.setPreferredWidth(100);
			  if (i==5) column.setPreferredWidth(100);
			  if (i==6) column.setPreferredWidth(120);
			  if (i==7) column.setPreferredWidth(120);
			  if (i==8) column.setPreferredWidth(120);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 881, 52);
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
					get_itemdisp( company,textField.getText(), reg_nme);
			  }
			});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemdisp( company,textField.getText(), reg_nme);
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
		textField.setBounds(10, 13, 754, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemdisp( company,textField.getText(), reg_nme);
			}
		});
		btnSearch.setBounds(774, 13, 97, 25);
		panel_2.add(btnSearch);
		
		get_itemdisp( company,  "",  reg_nme);
		
        //clickOnback( menuItem, "play", KeyEvent.VK_ESCAPE );
	}
//	public static void clickOnback(final AbstractButton button, String actionName, int key)
//	{
//	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );
//
//	    button.getActionMap().put( actionName, new AbstractAction()
//	    {
//	        /**
//			 * 
//			 */
//			private static final long serialVersionUID = -6694624852420723982L;
//
//			/**
//			 * 
//			 */
//			public void actionPerformed( ActionEvent e )
//	        {
//				frame.setVisible(false);
//				posscreen.txtDisc.requestFocus();
//	        }
//	    } );
//	}
	private void  get_itemdisp(String company, String search, String reg_num)
	{
		
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and (receipt_no LIKE '%"+search+"%' OR cust_code LIKE '%"+search+"%' OR cust_name LIKE '%"+search+"%' OR total LIKE '%"+search+"%') ";
		}
		try{
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
//			if(posscreen.change_hold_type.equals("1"))
//			{
//				
//			}
			String query="SELECT * FROM "+company+".mr_pos_hold_invoice where reg_num='"+reg_num+"' and mode='HOME DELIVERY' and delivery_user=''  "+serch_str+"  "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+company+".mr_pos_hold_invoice where reg_num='"+reg_num+"' and mode='HOME DELIVERY'  and delivery_user='' "+serch_str+"  "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String receipt_no=resultSet1.getString("receipt_no");
	                String cust_code=resultSet1.getString("cust_code");
	                String cust_name=resultSet1.getString("cust_name");
	                String total=resultSet1.getString("total");
	                String time=resultSet1.getString("time");
	                String table_num=resultSet1.getString("table_num");
	                String delivery_user=resultSet1.getString("delivery_user");
	                String mode=resultSet1.getString("mode");
	                model.addRow(new Object[]{new Boolean( false ), receipt_no,cust_code, cust_name,total,time,mode,table_num,delivery_user});
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Invoives List Found");
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

