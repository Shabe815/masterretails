package inventory_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import java.awt.Font;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;

public class kit_items {

	private JFrame frame;
	private static DefaultTableModel model;
	private static JTable table;
	public static JTextField textField;
	public static JTextField textField_1;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_4;
	private JButton btnAdd;
	private JTextField textField_5;
	private JTextField textField_6;
	public static JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kit_items window = new kit_items(null, null, null, null,"","1","2",2,"");
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
	public kit_items(String company,String dbname,String store,String username,String version,String itemcode,String itemname,int decimal,String language) {
		initialize(company,dbname,store,username,version,itemcode,itemname,decimal, language);
	}
	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store 
	 * @param dbname 
	 */
	private void initialize(final String company, final String dbname,final String store,final String username,final String version,final String itemcode,final String itemname,final int decimal,final String language) {
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
				Item_master.get_itemdtls_bsd_code(company, itemcode,decimal);
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
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_7.setText("");
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
				try
				{
//					double cost_ttl=0;
//	        		int count=table.getRowCount();
//	        		for(int i=0;i<count;i++)
//					{
//	        			cost_ttl+=Double.parseDouble(table.getValueAt(i, 6).toString());
//					}
//	        		BigDecimal be = new BigDecimal(cost_ttl);
//	        		be = be.setScale(2, BigDecimal.ROUND_HALF_UP);
//	        		cost_ttl = be.doubleValue();
//	        		Connection con=Dbsetup.dbconn.getConnection();
//					Statement statement=con.createStatement();  
//					String query="update "+company+".mr_item_master set item_cost='"+cost_ttl+"' where item_code='"+itemcode+"' ";
//					statement.execute(query);
					JOptionPane.showMessageDialog(frame, "Saved Successfully");
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(803, 13, 79, 30);
		menuItem_2.setIcon(new ImageIcon(save));
		frame.getContentPane().add(menuItem_2);
		
		JLabel lblKitItems = new JLabel("Kit Items");
		lblKitItems.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblKitItems.setBounds(12, 10, 126, 49);
		frame.getContentPane().add(lblKitItems);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.setBounds(12, 275, 857, 275);
		frame.getContentPane().add(scrollPane);
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
		model.addColumn("Item Code"); 
		model.addColumn("Item Name"); 
		model.addColumn("Barcode"); 
		model.addColumn("Sp");
		model.addColumn("Qty");
		model.addColumn("Cost");
		model.addColumn("Total Cost");
		table.setFont(new Font("Serif", Font.BOLD, 15));
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
		
		JLabel lblSelectItem = new JLabel("Select Child Items");
		lblSelectItem.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelectItem.setBounds(12, 148, 126, 22);
		frame.getContentPane().add(lblSelectItem);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(100, 180, 159, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Item Code List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					frame.setAlwaysOnTop(false);
					Connection con=Dbsetup.dbconn.getConnection();
					new item_list_master(dbname,company, "Item_kit", "", version,2,con,"",language);
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(271, 180, 35, 21);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblItemcode = new JLabel("Item Code");
		lblItemcode.setBounds(16, 180, 85, 22);
		frame.getContentPane().add(lblItemcode);
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setBounds(16, 212, 85, 22);
		frame.getContentPane().add(lblItemName);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(100, 211, 770, 22);
		frame.getContentPane().add(textField_1);
		
		JLabel lblSellingPrice = new JLabel("Selling Price");
		lblSellingPrice.setBounds(14, 244, 85, 22);
		frame.getContentPane().add(lblSellingPrice);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					textField_3.requestFocus();
		    	}
			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(100, 244, 172, 22);
		frame.getContentPane().add(textField_2);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setBounds(286, 244, 29, 22);
		frame.getContentPane().add(lblQty);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnAdd.requestFocus();
		    	}
			}
		});
		textField_3.setColumns(10);
		textField_3.setBounds(329, 244, 175, 22);
		frame.getContentPane().add(textField_3);
		
		JLabel lblCost = new JLabel("Cost");
		lblCost.setBounds(518, 244, 41, 22);
		frame.getContentPane().add(lblCost);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(573, 244, 126, 22);
		frame.getContentPane().add(textField_4);
		
		btnAdd = new JButton("ADD");
		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					add_items(company);
		    	}
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add_items(company);
			}
		});
		btnAdd.setBounds(713, 243, 63, 25);
		frame.getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Delete This Line","Warning",JOptionPane.YES_NO_OPTION);
			        if(cofrm_bx==0)
			        {
			        	int row_sel=table.getSelectedRow();
						String child_itemcode=table.getValueAt(row_sel, 0).toString();
						String parent_itemcode=textField_5.getText();
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_kit_items where parent_itemcode='"+parent_itemcode+"' and child_itemcode='"+child_itemcode+"' ");
						if(resultSet.next())
						{
							String query="delete from "+company+".mr_kit_items where parent_itemcode='"+parent_itemcode+"' and child_itemcode='"+child_itemcode+"' ";
							statement.execute(query);
							model.removeRow(row_sel);
							double cost_ttl=0;
			        		int count=table.getRowCount();
			        		for(int i=0;i<count;i++)
							{
			        			cost_ttl+=Double.parseDouble(table.getValueAt(i, 6).toString());
							}
			        		BigDecimal be = new BigDecimal(cost_ttl);
			        		be = be.setScale(2, BigDecimal.ROUND_HALF_UP);
			        		cost_ttl = be.doubleValue();
			    			textField_8.setText(Double.toString(cost_ttl));
						}
			        }
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		});
		btnDelete.setBounds(790, 243, 88, 25);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblParentItem = new JLabel("Parent Item");
		lblParentItem.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblParentItem.setBounds(12, 49, 126, 25);
		frame.getContentPane().add(lblParentItem);
		
		JLabel lblItemCode = new JLabel("Item Code");
		lblItemCode.setBounds(12, 84, 70, 22);
		frame.getContentPane().add(lblItemCode);
		
		JLabel lblItemName_1 = new JLabel("Item Name");
		lblItemName_1.setBounds(12, 116, 70, 22);
		frame.getContentPane().add(lblItemName_1);
		
		textField_5 = new JTextField(itemcode);
		textField_5.setEditable(false);
		textField_5.setBounds(100, 84, 159, 22);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField(itemname);
		textField_6.setEditable(false);
		textField_6.setBounds(100, 116, 769, 22);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblBarcode = new JLabel("Barcode");
		lblBarcode.setBounds(339, 180, 63, 22);
		frame.getContentPane().add(lblBarcode);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(400, 180, 182, 22);
		frame.getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setBounds(737, 561, 132, 20);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblTotalCost = new JLabel("Total Cost");
		lblTotalCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalCost.setBounds(655, 564, 72, 17);
		frame.getContentPane().add(lblTotalCost);
		

		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		 
	    frame.setVisible(true);
	
	    frame.setResizable(false);
		frame.setBounds(100, 100, 900, 619);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
		get_items(company,textField_5.getText());
	}
	private void add_items(String company) {
		try
		{
			String parent_itemcode=textField_5.getText();
			String parent_itemname=textField_6.getText();
			String child_itemcode=textField.getText();
			String child_itemname=textField_1.getText();
			String sp=textField_2.getText();
			String qty=textField_3.getText();
			String cost=textField_4.getText();
			String barcode=textField_7.getText();
			
			if(child_itemcode.equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Select one item");
			}
			else if(sp.equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Enter Selling Price");
			}
			else if(qty.equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Enter Quantity");
			}
			else
			{
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    String formattedDate = myDateObj.format(myFormatObj); 
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_kit_items where parent_itemcode='"+parent_itemcode+"' and child_itemcode='"+child_itemcode+"' ");
				if(!resultSet.next())
				{
					String query="INSERT INTO "+company+".mr_kit_items(`parent_itemcode`, `parent_itemname`, `child_itemcode`, `child_itemname`, `child_barcode`, `sp`, `cost`, `qty`, `created_date`, `modified_date`) "
							+ "VALUES('"+parent_itemcode+"','"+parent_itemname+"','"+child_itemcode+"','"+child_itemname+"','"+barcode+"','"+sp+"','"+cost+"','"+qty+"','"+formattedDate+"','"+formattedDate+"') ";
					statement.execute(query);
					double ttl_cost=Double.parseDouble(qty)*Double.parseDouble(cost);
					BigDecimal bd = new BigDecimal(ttl_cost);
	        		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
	        		ttl_cost = bd.doubleValue();
	        		model.addRow(new Object[]{child_itemcode,child_itemname,barcode,sp,qty,cost,ttl_cost});
	        		double cost_ttl=0;
	        		int count=table.getRowCount();
	        		for(int i=0;i<count;i++)
					{
	        			cost_ttl+=Double.parseDouble(table.getValueAt(i, 6).toString());
					}
	        		BigDecimal be = new BigDecimal(cost_ttl);
	        		be = be.setScale(2, BigDecimal.ROUND_HALF_UP);
	        		cost_ttl = be.doubleValue();
	    			textField_8.setText(Double.toString(cost_ttl));
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_7.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "This Item Already Exist");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_7.setText("");
				}
			}
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
	}
	private void get_items(String company, String parent_itemcode) {
		try
		{
			double cost_ttl=0;
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_kit_items where parent_itemcode='"+parent_itemcode+"' ");
			while(resultSet.next())
			{
				double ttl_cost=Double.parseDouble(resultSet.getString("qty"))*Double.parseDouble(resultSet.getString("cost"));
				BigDecimal bd = new BigDecimal(ttl_cost);
        		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        		ttl_cost = bd.doubleValue();
        		cost_ttl=cost_ttl+ttl_cost;
				model.addRow(new Object[]{resultSet.getString("child_itemcode"),resultSet.getString("child_itemname"),resultSet.getString("child_barcode"),resultSet.getString("sp"),resultSet.getString("qty"),resultSet.getString("cost"),ttl_cost});
			}
			BigDecimal bd = new BigDecimal(cost_ttl);
    		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
    		cost_ttl = bd.doubleValue();
			textField_8.setText(Double.toString(cost_ttl));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
		
		
	}
}
