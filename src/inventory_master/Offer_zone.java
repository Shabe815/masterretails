package inventory_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTextField;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;

import org.json.JSONException;

import system_setups.cat_list;
import system_setups.department_list;
import system_setups.sub_cat_list;
import system_setups.sub_dept_list;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Offer_zone {

	private JFrame frame;
	private static DefaultTableModel model;
	private static JTable table;
	public static JTextField textField;
	public static  JTextField textField_1;
	private JTextField textField_2;
	private JComboBox<String> comboBox;
	private JPanel panel;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	public static JTextField textField_3;
	private JProgressBar progressBar;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Offer_zone window = new Offer_zone("mr_pro", null, null, null,"","");
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
	public Offer_zone(String company,String dbname,String store,String username,String version,String language) {
		initialize(company,dbname,store,username,version, language);
	}
	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store 
	 * @param dbname 
	 */
	private void initialize(final String company, final String dbname,final String store,final String username,final String version,final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Exit", KeyEvent.VK_F4);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(623, 13, 79, 30);
		menuItem.setIcon(new ImageIcon(exit));
		frame.getContentPane().add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh", KeyEvent.VK_F5);
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(707, 13, 95, 30);
		menuItem_1.setIcon(new ImageIcon(refresh));
		frame.getContentPane().add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame, "Saved Successfull");
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(803, 13, 79, 30);
		menuItem_2.setIcon(new ImageIcon(save));
		frame.getContentPane().add(menuItem_2);
		
		JLabel lblKitItems = new JLabel("PROMOTION");
		lblKitItems.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblKitItems.setBounds(12, 10, 364, 49);
		frame.getContentPane().add(lblKitItems);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
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
		model.addColumn("Sl.No."); 
		model.addColumn("Item Code"); 
		model.addColumn("Item Name"); 
		model.addColumn("Barcode");
		model.addColumn("Department");
		model.addColumn("Sub-Depart");
		model.addColumn("Category");
		model.addColumn("Sub-Cat");
		model.addColumn("Percentage");
		model.addColumn("From Date");
		model.addColumn("To Date");
		
		panel = new JPanel();
		panel.setBounds(12, 231, 870, 330);
		frame.getContentPane().add(panel);
		//panel.setVisible(false);
		panel.setBackground(new Color(243, 229, 216));
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.setBounds(9, 0, 857, 319);
		panel.add(scrollPane);
		table = new JTable(model);
		table.setFont(new Font("Serif", Font.BOLD, 15));
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		int colmns=model.getColumnCount();
		for (int i=0; i<colmns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(35);
		  if (i==1) column.setPreferredWidth(100);
		  if (i==2) column.setPreferredWidth(250);
		  if (i==3) column.setPreferredWidth(100);
		  if (i==4) column.setPreferredWidth(100);
		  if (i==5) column.setPreferredWidth(100);
		  if (i==6) column.setPreferredWidth(100);
		  if (i==7) column.setPreferredWidth(180);
		  if (i==8) column.setPreferredWidth(180);
		  if (i==9) column.setPreferredWidth(100);
		  if (i==10) column.setPreferredWidth(100);
		  if (i==11) column.setPreferredWidth(100);
		  if (i==12) column.setPreferredWidth(100);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setBounds(12, 54, 870, 175);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Code");
		label.setBounds(10, 62, 45, 22);
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setBounds(53, 62, 200, 22);
		panel_1.add(textField);
		textField.setEditable(false);
		textField.setColumns(10);
		JButton button = new JButton("");
		button.setBounds(265, 62, 35, 21);
		panel_1.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setAlwaysOnTop(false);
					if(comboBox.getSelectedItem().equals("Item Wise"))
					{
						Connection con=Dbsetup.dbconn.getConnection();
						new item_list_master(dbname,company, "promo", "", version,2,con,"",language);
					}
					if(comboBox.getSelectedItem().equals("Department Wise"))
					{
						new department_list(company,version,"offer");
					}
					if(comboBox.getSelectedItem().equals("Sub Department Wise"))
					{
						new sub_dept_list(company,version,"offer","");
					}
					if(comboBox.getSelectedItem().equals("Category Wise"))
					{
						new cat_list(company,version,"offer");
					}
					if(comboBox.getSelectedItem().equals("Sub Category Wise"))
					{
						new sub_cat_list(company,version,"offer","");
					}
				} catch (JSONException e) {
//					frame.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
//					frame.setAlwaysOnTop(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(serch));
		button.setToolTipText("Item Code List");
		
		JLabel label_1 = new JLabel("Name");
		label_1.setBounds(10, 90, 45, 22);
		panel_1.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(53, 88, 811, 22);
		panel_1.add(textField_1);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		JLabel lblFrom = new JLabel("From Date");
		lblFrom.setBounds(10, 118, 72, 22);
		panel_1.add(lblFrom);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(107, 118, 100, 22);
		panel_1.add(dateChooser);
		
		JLabel lblToDate = new JLabel("To Date");
		lblToDate.setBounds(232, 118, 56, 22);
		panel_1.add(lblToDate);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(313, 118, 100, 22);
		panel_1.add(dateChooser_1);
		
		JLabel lblDiscountPercentage = new JLabel("Discount percentage");
		lblDiscountPercentage.setBounds(438, 118, 125, 22);
		panel_1.add(lblDiscountPercentage);
		
		textField_2 = new JTextField();
		textField_2.setBounds(588, 118, 116, 22);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnActivate = new JButton("Activate Offer");
		btnActivate.setBounds(729, 116, 121, 25);
		panel_1.add(btnActivate);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setVisible(false);
		textField_3.setBounds(310, 62, 245, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 34, 320, 22);
		panel_1.add(comboBox);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(344, 11, 516, 31);
		panel_1.add(progressBar);
		progressBar.setForeground(new Color(0, 0, 205));
		progressBar.setBackground(new Color(192, 192, 192));
		progressBar.setStringPainted(true);
		
		JLabel lblSearchItemsFrom = new JLabel("Search Items from Grid");
		lblSearchItemsFrom.setBounds(10, 146, 137, 22);
		panel_1.add(lblSearchItemsFrom);
		
		textField_4 = new JTextField();
		textField_4.getDocument().addDocumentListener(new DocumentListener() {
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
					get_items( company,  comboBox.getSelectedItem().toString(),textField_4.getText());
			  }
			});
		textField_4.setBounds(146, 147, 718, 22);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblSelectOneType = new JLabel("Select One Type");
		lblSelectOneType.setBounds(10, 6, 116, 22);
		panel_1.add(lblSelectOneType);
		lblSelectOneType.setFont(new Font("Tahoma", Font.BOLD, 13));
		progressBar.setVisible(false);
		
		
		comboBox.addItem("Item Wise");
		comboBox.addItem("Department Wise");
		comboBox.addItem("Sub Department Wise");
		comboBox.addItem("Category Wise");
		comboBox.addItem("Sub Category Wise");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				get_items(company, comboBox.getSelectedItem().toString(),textField_4.getText());
			}
		});
		btnActivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add_items( company);
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	try
    				{
    					int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Delete This Line","Warning",JOptionPane.YES_NO_OPTION);
    			        if(cofrm_bx==0)
    			        {
    			        	int row_sel=table.getSelectedRow();
    						String itemcode=table.getValueAt(row_sel, 1).toString();
    						Connection con=Dbsetup.dbconn.getConnection();
    						Statement statement=con.createStatement();  
    						if(comboBox.getSelectedItem().equals("Item Wise"))
    						{
    							String query11="select * from "+company+".mr_promotion where itemcode='"+itemcode+"' and promo_type='"+comboBox.getSelectedItem().toString()+"' ";
    							ResultSet resultSet = statement.executeQuery(query11);
        						if(resultSet.next())
        						{
        							String query1="UPDATE "+company+".mr_item_master set Onpromo='0',allowDiscount='0',disc_perc='0' where item_code='"+itemcode+"'";
        							statement.execute(query1);
        							String query="delete from "+company+".mr_promotion where itemcode='"+itemcode+"' and promo_type='"+comboBox.getSelectedItem().toString()+"' ";
        							statement.execute(query);
        							
        							model.removeRow(row_sel);
        						}
    						}
    						
    			        }
    				}
    				catch(Exception e)
    				{
    					JOptionPane.showMessageDialog(frame, e.getMessage());
    				}
                }
            }
        });
		
		
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 906, 600);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		get_items(company, comboBox.getSelectedItem().toString(),textField_4.getText());
	}
	private void add_items(String company) {
		try
		{
			progressBar.setVisible(true);
			double number_progress=0;
			int b=1;
			String head_name=textField_3.getText();
			String type=comboBox.getSelectedItem().toString();
			String itemcode=textField.getText();
			String itemname=textField_1.getText();
			String disc=textField_2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startdate = null;
			String enddate = null;
			String start_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
			if(!start_date.equals(""))
			{
				startdate = sdf.format(dateChooser.getDate());
			}
			String end_date = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
			if(!end_date.equals(""))
			{
				enddate = sdf.format(dateChooser_1.getDate());
			}
			
			if(itemcode.equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Select one item");
			}
			else if(disc.equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Enter Discount Percentage	");
			}
			else
			{
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    String formattedDate = myDateObj.format(myFormatObj); 
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				Statement statement1=con.createStatement();  
				if(type.equals("Item Wise"))
				{
					ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_promotion where itemcode='"+itemcode+"' and promo_type='Item Wise' ");
					if(resultSet.next())
					{
						JOptionPane.showMessageDialog(frame, "This Item Already Exist.");
					}
					else
					{
						ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_tmp_item_master where item_code='"+itemcode+"' ");
						if(resultSet1.next())
						{
							String remove_dup="delete from "+company+".mr_promotion where  itemcode='"+itemcode+"'";
							statement1.executeUpdate(remove_dup);
							
							String query="INSERT INTO "+company+".mr_promotion(`itemcode`, `barcode`, `itemname`, `dept`, `sub_dept`, `category`, `sub_cat`, `percentage`, `from_date`, `to_date`, `promo_type`, `created_date`, `modified_date`) "
									+ "VALUES('"+itemcode+"','"+resultSet1.getString("barcode")+"','"+itemname+"','"+resultSet1.getString("department")+"','"+resultSet1.getString("sub_dept")+"','"+resultSet1.getString("category")+"','"+resultSet1.getString("sub_cat")+"','"+disc+"','"+startdate+"','"+enddate+"','"+type+"','"+formattedDate+"','"+formattedDate+"') ";
							statement.execute(query);
							
							String query1="UPDATE "+company+".mr_item_master set Onpromo='1',start_date='"+startdate+"',enddate='"+enddate+"',allowDiscount='1',disc_perc='"+disc+"',modified_date='"+formattedDate+"' where item_code='"+itemcode+"'";
							statement.execute(query1);
						}
					}
					progressBar.setVisible(false);
					JOptionPane.showMessageDialog(frame, "Offer Generated Successfully");
				}
				else if(type.equals("Department Wise"))
				{
					ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_promotion where dept='"+itemname+"' ");
					if(resultSet.next())
					{
						String remove_dup="delete from "+company+".mr_promotion where  dept='"+itemname+"'";
						statement.executeUpdate(remove_dup);
					}
					String qury="select count(*) as ttl from "+company+".mr_tmp_item_master where department='"+itemname+"' ";
					ResultSet resultSet121 = statement.executeQuery(qury);
					if(resultSet121.next())
					{
						double count=Double.parseDouble(resultSet121.getString("ttl"));
						number_progress=100/count;
					}
					
					ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_tmp_item_master where department='"+itemname+"' ");
					while(resultSet1.next())
					{
						String update_item_master="update "+company+".mr_item_master set allowDiscount='0', Onpromo='0' where item_code='"+resultSet1.getString("item_code")+"' ";
						 statement1.execute(update_item_master);
						 String query1="INSERT INTO "+company+".mr_promotion( `itemcode`, `barcode`, `itemname`, `dept`, `sub_dept`, `category`, `sub_cat`, `percentage`, `from_date`, `to_date`, `promo_type`, `created_date`, `modified_date`) "
									+ "VALUES('"+resultSet1.getString("item_code")+"','"+resultSet1.getString("barcode")+"','"+resultSet1.getString("item_name")+"','"+resultSet1.getString("department")+"','"+resultSet1.getString("sub_dept")+"','"+resultSet1.getString("category")+"','"+resultSet1.getString("sub_cat")+"','"+disc+"','"+startdate+"','"+enddate+"','Department Wise','"+formattedDate+"','"+formattedDate+"' ) ";
						 statement1.execute(query1);
						 double ttl_count=b*number_progress;
						 Thread.sleep(50);
	                	 progressBar.paintImmediately(0, 0, 854, 31);
	                	 progressBar.setValue((int)ttl_count);
	                	 b++;
					}
					progressBar.setVisible(false);
					JOptionPane.showMessageDialog(frame, "Offer Generated Successfully");
				}
				else if(type.equals("Sub Department Wise"))
				{
					ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_promotion where dept='"+head_name+"' and sub_dept='"+itemname+"' ");
					if(resultSet.next())
					{
						String remove_dup="delete from "+company+".mr_promotion where  dept='"+head_name+"' and sub_dept='"+itemname+"' ";
						statement.executeUpdate(remove_dup);
					}
					String qury="select count(*) as ttl from "+company+".mr_tmp_item_master where department='"+head_name+"' and sub_dept='"+itemname+"' ";
					ResultSet resultSet121 = statement1.executeQuery(qury);
					if(resultSet121.next())
					{
						double count=Double.parseDouble(resultSet121.getString("ttl"));
						number_progress=100/count;
					}
							
					String query_item="select * from "+company+".mr_tmp_item_master where department='"+head_name+"' and sub_dept='"+itemname+"' ";
					ResultSet resultSet1 = statement.executeQuery(query_item);
					while(resultSet1.next())
					{
						String update_item_master="update "+company+".mr_item_master set allowDiscount='0', Onpromo='0' where item_code='"+resultSet1.getString("item_code")+"' ";
						statement1.execute(update_item_master);
						String query1="INSERT INTO "+company+".mr_promotion( `itemcode`, `barcode`, `itemname`, `dept`, `sub_dept`, `category`, `sub_cat`, `percentage`, `from_date`, `to_date`, `promo_type`, `created_date`, `modified_date`) VALUES('"+resultSet1.getString("item_code")+"','"+resultSet1.getString("barcode")+"','"+resultSet1.getString("item_name")+"','"+resultSet1.getString("department")+"','"+resultSet1.getString("sub_dept")+"','"+resultSet1.getString("category")+"','"+resultSet1.getString("sub_cat")+"','"+disc+"','"+startdate+"','"+enddate+"','Sub Department Wise','"+formattedDate+"','"+formattedDate+"' ) ";
						statement1.execute(query1);
						double ttl_count=b*number_progress;
						Thread.sleep(50);
	                	progressBar.paintImmediately(0, 0, 854, 31);
	                	progressBar.setValue((int)ttl_count);
	                	b++;
					}
					progressBar.setVisible(false);
					JOptionPane.showMessageDialog(frame, "Offer Generated Successfully");
				}
				else if(type.equals("Category Wise"))
				{
					ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_promotion where category='"+itemname+"' ");
					if(resultSet.next())
					{
						String remove_dup="delete from "+company+".mr_promotion where  category='"+itemname+"'";
						statement.executeUpdate(remove_dup);
					}
					String qury="select count(*) as ttl from "+company+".mr_tmp_item_master where category='"+itemname+"' ";
					ResultSet resultSet121 = statement1.executeQuery(qury);
					if(resultSet121.next())
					{
						double count=Double.parseDouble(resultSet121.getString("ttl"));
						number_progress=100/count;
					}
						
					String query_item="select * from "+company+".mr_tmp_item_master where category='"+itemname+"' ";
					ResultSet resultSet1 = statement.executeQuery(query_item);
					while(resultSet1.next())
					{
						String update_item_master="update "+company+".mr_item_master set allowDiscount='0', Onpromo='0' where item_code='"+resultSet1.getString("item_code")+"' ";
						 statement1.execute(update_item_master);
						String query1="INSERT INTO "+company+".mr_promotion( `itemcode`, `barcode`, `itemname`, `dept`, `sub_dept`, `category`, `sub_cat`, `percentage`, `from_date`, `to_date`, `promo_type`, `created_date`, `modified_date`) VALUES('"+resultSet1.getString("item_code")+"','"+resultSet1.getString("barcode")+"','"+resultSet1.getString("item_name")+"','"+resultSet1.getString("department")+"','"+resultSet1.getString("sub_dept")+"','"+resultSet1.getString("category")+"','"+resultSet1.getString("sub_cat")+"','"+disc+"','"+startdate+"','"+enddate+"','Category Wise','"+formattedDate+"','"+formattedDate+"' ) ";
						statement1.execute(query1);
						double ttl_count=b*number_progress;
						Thread.sleep(50);
	                	progressBar.paintImmediately(0, 0, 854, 31);
	                	progressBar.setValue((int)ttl_count);
	                	b++;
					}
					progressBar.setVisible(false);
					JOptionPane.showMessageDialog(frame, "Offer Generated Successfully");
				}
				else if(type.equals("Sub Category Wise"))
				{
					ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_promotion where category='"+head_name+"' and sub_cat='"+itemname+"' ");
					if(resultSet.next())
					{
						String remove_dup="delete from "+company+".mr_promotion where category='"+head_name+"' and sub_cat='"+itemname+"' ";
						statement.executeUpdate(remove_dup);
					}
					
					String qury="select count(*) as ttl from "+company+".mr_tmp_item_master where category='"+head_name+"' and sub_cat='"+itemname+"' ";
					ResultSet resultSet121 = statement1.executeQuery(qury);
					if(resultSet121.next())
					{
						double count=Double.parseDouble(resultSet121.getString("ttl"));
						number_progress=100/count;
					}
						
					String query_item="select * from "+company+".mr_tmp_item_master where category='"+head_name+"' and sub_cat='"+itemname+"' ";
					ResultSet resultSet1 = statement.executeQuery(query_item);
					while(resultSet1.next())
					{
						String update_item_master="update "+company+".mr_item_master set allowDiscount='0', Onpromo='0' where item_code='"+resultSet1.getString("item_code")+"' ";
						 statement1.execute(update_item_master);
						String query1="INSERT INTO "+company+".mr_promotion( `itemcode`, `barcode`, `itemname`, `dept`, `sub_dept`, `category`, `sub_cat`, `percentage`, `from_date`, `to_date`, `promo_type`, `created_date`, `modified_date`) VALUES('"+resultSet1.getString("item_code")+"','"+resultSet1.getString("barcode")+"','"+resultSet1.getString("item_name")+"','"+resultSet1.getString("department")+"','"+resultSet1.getString("sub_dept")+"','"+resultSet1.getString("category")+"','"+resultSet1.getString("sub_cat")+"','"+disc+"','"+startdate+"','"+enddate+"','Sub Category Wise','"+formattedDate+"','"+formattedDate+"' ) ";
						statement1.execute(query1);
						double ttl_count=b*number_progress;
						Thread.sleep(50);
	                	progressBar.paintImmediately(0, 0, 854, 31);
	                	progressBar.setValue((int)ttl_count);
	                	b++;
					}
					progressBar.setVisible(false);
					JOptionPane.showMessageDialog(frame, "Offer Generated Successfully");
				}
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
			}
			
			get_items(company, comboBox.getSelectedItem().toString(),textField_4.getText());
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
	}
	private void get_items(String company, String type, String search) {
		try
		{
			String serch="";
			if(!search.equals(""))
			{
				serch="and (itemcode LIKE '%"+search+"%' or barcode LIKE '%"+search+"%' or itemname LIKE '%"+search+"%' or dept LIKE '%"+search+"%' or sub_dept LIKE '%"+search+"%' or category LIKE '%"+search+"%' or sub_cat LIKE '%"+search+"%')";
			}
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_promotion where promo_type='"+type+"' "+serch+" ");
			while(resultSet.next())
			{
				model.addRow(new Object[]{model.getRowCount()+1,resultSet.getString("itemcode"),resultSet.getString("itemname"),resultSet.getString("barcode"),resultSet.getString("dept"),resultSet.getString("sub_dept"),resultSet.getString("category"),resultSet.getString("sub_cat"),resultSet.getString("percentage"),resultSet.getString("from_date"),resultSet.getString("to_date")});
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
		
		
	}
}
