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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class vendor_item_list {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private JTextField textField;
	private JComboBox<String> comboBox; 
	private ArrayList<String> listitem= new ArrayList<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vendor_item_list window = new vendor_item_list("mr_pro", "1000","","admin", null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param vendor_id 
	 */
	public vendor_item_list(String company, String vendor_id,String page_type,String username,String version) throws JSONException {
		initialize(company,vendor_id,page_type, page_type, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param vendor_id 
	 */
	private void initialize(final String company, final String vendor_id,final String page_type ,final String username,final String version) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
	    frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 540);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		
		frame.setLocationRelativeTo(null);
		
		JLabel lblCompanySetup = new JLabel("Item List");
		lblCompanySetup.setBounds(21, 11, 228, 44);
		Image user=new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		lblCompanySetup.setIcon(new ImageIcon(user));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
//				purchase_master.vendor_details.frame.setAlwaysOnTop(true);
//				purchase_master.vendor_details.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				purchase_master.vendor_details.mntmNewMenuItem.setEnabled(true);
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(482, 7, 60, 48);
		frame.getContentPane().add(mntmNewMenuItem);

		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				get_itemsdisp( company,textField.getText(),comboBox.getSelectedItem().toString(), vendor_id);
			}
		});
		mntmRefresh.setBackground(new Color(243, 229, 216));
		mntmRefresh.setIcon(new ImageIcon(refresh));
		mntmRefresh.setBounds(542, 7, 80, 48);
		mntmRefresh.setBorder(null);
		frame.getContentPane().add(mntmRefresh);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    String formattedDate = myDateObj.format(myFormatObj);  
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement;
				boolean sts=false;
				Object[] item_list = new String[model.getRowCount()];
				for (int i = 0; i < model.getRowCount(); i++) {
					Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					String dataCol1 = model.getValueAt(i, 2).toString();
					if (chked) {
						item_list[i]=dataCol1;
					}
				}
				String query="SELECT * FROM "+company+".mr_vendor_items where ref_vendor_id='"+vendor_id+"' "; 
				ResultSet resultSet;
				try {
					statement = con.createStatement();
					resultSet = statement.executeQuery(query);
					if(resultSet.next())
					{
						String query1="delete FROM "+company+".mr_vendor_items where ref_vendor_id='"+vendor_id+"' "; 
						statement.executeUpdate(query1);
						sts=true;
					}
					else
					{
						sts=true;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(sts==true)
				{
					for(int i=0;i<item_list.length;i++)
					{
						if(item_list[i]!=null)
						{
							try {
								statement = con.createStatement();
								String query2="insert into "+company+".mr_vendor_items(ref_vendor_id,ref_item_id,created_date)  VALUES('"+vendor_id+"','"+item_list[i]+"','"+formattedDate+"')";
								statement.executeUpdate(query2);
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
					}
					JOptionPane.showMessageDialog(frame, "Saved Successfully");
				}
			}

		});
		mntmSave.setBackground(new Color(243, 229, 216));
		mntmSave.setIcon(new ImageIcon(save));
		mntmSave.setBounds(624, 7, 70, 48);
		mntmSave.setBorder(null);
		frame.getContentPane().add(mntmSave);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 153, 685, 352);
		scrollPane.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(scrollPane);
		
		
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){  
		        if(column==0)
		        {
		        	return true;  
		        }
				return false;  
		      }
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				
				default:
					return String.class;
				}
			}
		};
		table = new JTable(model);
		model.addColumn(""); 
		model.addColumn("S.No"); 
	    model.addColumn("Item Code"); 
	    model.addColumn("Item Name"); 
		model.addColumn("Category"); 
		model.addColumn("Department"); 
		//model.addColumn("Barcode"); 
		model.addColumn("Item Type");
		model.addColumn("Item Cost");
		model.addColumn("Selling Price"); 
		model.addColumn("Qty on hand"); 
		scrollPane.setViewportView(table);
		
		//model.getColumnClass(0).
		/*button.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
			}
		});
		*/
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				Object[] item_list = new String[model.getRowCount()];
				//String list="";
				for (int i = 0; i < model.getRowCount(); i++) {
					Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					String dataCol1 = model.getValueAt(i, 2).toString();
					if (chked) {
						item_list[i]=dataCol1;
					}
				}
				
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 68, 694, 52);
		frame.getContentPane().add(panel);
		
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
				  get_itemsdisp( company,textField.getText(),comboBox.getSelectedItem().toString(), vendor_id);
			  }
			});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemsdisp( company,textField.getText(),comboBox.getSelectedItem().toString(), vendor_id);
			}
		});
		textField.setColumns(10);
		textField.setBounds(12, 13, 240, 25);
		panel.add(textField);
		
		JButton button = new JButton("Search");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemsdisp( company,textField.getText(),comboBox.getSelectedItem().toString(), vendor_id);
			}
		});
		button.setBounds(264, 13, 97, 25);
		panel.add(button);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemsdisp( company,textField.getText(),comboBox.getSelectedItem().toString(), vendor_id);
			}
		});
		comboBox.setBounds(550, 14, 127, 22);
		comboBox.addItem("Item Name");
		comboBox.addItem("Item Code"); 
		comboBox.addItem("Category"); 
		comboBox.addItem("Department"); 
		panel.add(comboBox);
		
		JLabel lblSortBy = new JLabel("Sort By");
		lblSortBy.setBounds(500, 17, 54, 16);
		panel.add(lblSortBy);
		
		final JCheckBox chckbxSelectAll = new JCheckBox("Select All");
		chckbxSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxSelectAll.isSelected()){

	                for(int i=0;i<table.getRowCount();i++)
	                table.getModel().setValueAt(true, i, 0);

	            }else{

	                for(int i=0;i<table.getRowCount();i++)
	                    table.getModel().setValueAt(false, i, 0);                   
	            }    
			}
		});
		chckbxSelectAll.setBounds(5, 125, 113, 25);
		frame.getContentPane().add(chckbxSelectAll);
		
	}
	private void get_itemsdisp( String company,String search, String filter, String vendor_id)
	{
		String filter_name = "";
		String serch_str = "";
		if(filter=="Item Name")
		{
			filter_name=company+".mr_item_master.item_name";
		}
		else if(filter=="Item Code")
		{
			filter_name=company+".mr_item_master.item_code";
		}
		else if(filter=="Category")
		{
			filter_name=company+".mr_item_master.category";
		}
		else if(filter=="Department")
		{
			filter_name=company+".mr_item_master.department";
		}
		if(!search.equals(""))
		{
			serch_str="WHERE "+company+".mr_item_master.item_name LIKE '%"+search+"%' OR "+company+".mr_item_master.department LIKE '%"+search+"%' OR "+company+".mr_item_master.item_code LIKE '%"+search+"%' OR "+company+".mr_item_master.category LIKE '%"+search+"%' ";
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			String query1="SELECT ref_item_id FROM "+company+".mr_vendor_items where ref_vendor_id='"+vendor_id+"' ";// INNER join "+company+".mr_item_barcode on "+company+".mr_item_master.item_code="+company+".mr_item_barcode.ref_item_code "+serch_str+" GROUP BY "+company+".mr_item_master.item_code 
			ResultSet resultSet1 = statement.executeQuery(query1);
			while(resultSet1.next())
			{
				listitem.add(resultSet1.getString("ref_item_id"));
			}
			boolean str=false;
			String query="SELECT * FROM "+company+".mr_item_master "+serch_str+" ORDER BY "+filter_name+" ASC ";// INNER join "+company+".mr_item_barcode on "+company+".mr_item_master.item_code="+company+".mr_item_barcode.ref_item_code "+serch_str+" GROUP BY "+company+".mr_item_master.item_code 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String code=resultSet.getString("item_code");
                String name=resultSet.getString("item_name");
               // String barcode=resultSet.getString("barcode");
                String category=resultSet.getString("category");
                String department=resultSet.getString("department");
                String item_type=resultSet.getString("item_type");
                String item_cost=resultSet.getString("item_cost");
                String selling_price=resultSet.getString("sp1");
                String qty=resultSet.getString("total_qty");
                for (int i=0; i<listitem.size(); i++){
                	if(code.equals(listitem.get(i)))
                    {
                		str=true;
                    }
                	
                }
                model.addRow(new Object[]{new Boolean( str ),model.getRowCount()+1, code, name,/*barcode,*/department,category,item_type,item_cost,selling_price,qty});
                str=false;
            }
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
