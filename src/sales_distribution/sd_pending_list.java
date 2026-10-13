package sales_distribution;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import sales_master.Cust_Chooser;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class sd_pending_list {

	public static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	public static JTextField textField;
	public static JTextField textField_1;
	public static JTextField textField_2;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					sd_pending_list window = new sd_pending_list("mr_pro","admin","S01", null,"2");
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
	public sd_pending_list(String db_name, String username,String store,String version,String decimal) throws JSONException {
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
		frame.setBounds(100, 100, 950, 675);
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
		panel.setBounds(0, 0, 932, 96);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("SD PENDING LIST");
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
		
		final Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JMenuItem menuItem_3 = new JMenuItem("Print");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					frame.setAlwaysOnTop(false);
					report_master.sd_pending_list.report(con,textField.getText().trim(),textField_1.getText().trim(),comboBox.getSelectedItem().toString(),db_name);
					//frame.setAlwaysOnTop(true);
					con.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		menuItem_3.setBorder(null);
		menuItem_3.setIcon(new ImageIcon(printer));
		menuItem_3.setBackground(new Color(243, 229, 216));
		menuItem_3.setBounds(688, 13, 80, 30);
		panel.add(menuItem_3);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name, "", "",comboBox.getSelectedItem().toString());
				textField.setText("");
				textField_1.setText("");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 95, 932, 538);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 88, 908, 422);
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
		    
		    model.addColumn("Date");
		    model.addColumn("Invoice No."); 
		    model.addColumn("Invoice Amount");
		    model.addColumn("Customer Code");
		    model.addColumn("Customer Name");
		    model.addColumn("Balance Amt");
		scrollPane.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(100);
			  if (i==1) column.setPreferredWidth(160);
			  if (i==2) column.setPreferredWidth(100);
			  if (i==3) column.setPreferredWidth(120);
			  if (i==4) column.setPreferredWidth(300);
			  if (i==5) column.setPreferredWidth(100);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 908, 62);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					get_po_disp( db_name,textField.getText(),textField_1.getText(),comboBox.getSelectedItem().toString());
				}
			}
		});
		textField.setBounds(168, 4, 337, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Cust_Chooser(db_name, "cust_pending", username, store, "","", version,"1");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(518, 4, 97, 54);
		panel_2.add(btnSearch);
		
		JLabel lblCustomerCode = new JLabel("Customer Code");
		lblCustomerCode.setBounds(12, 10, 97, 16);
		panel_2.add(lblCustomerCode);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(12, 36, 97, 16);
		panel_2.add(lblCustomerName);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(168, 33, 337, 25);
		panel_2.add(textField_1);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("Customer Code");
		comboBox.addItem("Invoice Number");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name,textField.getText(),textField_1.getText(),comboBox.getSelectedItem().toString());
				//get_po_disp( db_name, "",  "",comboBox.getSelectedItem().toString());
			}
		});
		
		comboBox.setBounds(698, 32, 198, 22);
		panel_2.add(comboBox);
		
		
		JLabel lblSortBy = new JLabel("Sort By");
		lblSortBy.setBounds(698, 8, 56, 16);
		panel_2.add(lblSortBy);
		
		JLabel lblTotalBalance = new JLabel("Total Balance : ");
		lblTotalBalance.setBounds(609, 509, 97, 16);
		panel_1.add(lblTotalBalance);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_2.setEditable(false);
		textField_2.setBounds(720, 510, 181, 22);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		//comboBox.addItem("DELIVERY");
		 
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 1;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	try{
                										
	 					new sales_distribution.sd_view(db_name, username, table.getModel().getValueAt(rows, 4).toString(), value, store, version,decimal,table.getModel().getValueAt(rows, 2).toString(),"POSTED");
	 					//sd_lines.frame.setAlwaysOnTop(true);
                	}
                	catch (JSONException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                
            }
        });
        get_po_disp( db_name, "",  "",comboBox.getSelectedItem().toString());
	}
	public static void  get_po_disp( String company, String custcode, String custname,String sort)
	{
		String serch_str = "";
		if(!custcode.equals(""))
		{
			serch_str="and ref_cust_id = '"+custcode+"' ";
		}
		String sort_str = "";
		if(sort.equals("Customer Code"))
		{
			sort_str="order by ref_cust_id ASC";
		}
		else
		{
			sort_str="order by invo_num ASC";
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement(); 
			Statement statement1=con.createStatement(); 
			String query="SELECT * FROM "+company+".mr_sd_customer_payment where bal_amt!='0'  "+serch_str+" "+sort_str+""; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			double ttl_bal=0;
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+company+".mr_sd_customer_payment where bal_amt!='0'  "+serch_str+" "+sort_str+" "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String invoice_num=resultSet1.getString("invo_num");
	                double total_amt=Double.parseDouble(resultSet1.getString("invo_amt"));
	                String cust_name="";
	                String cust_id=resultSet1.getString("ref_cust_id");
	                String query2="select cust_name from "+company+".mr_customer_master where cust_code='"+cust_id+"'";
	                ResultSet resultSet2 = statement1.executeQuery(query2);
	                if(resultSet2.next())
	                {
	                	cust_name=resultSet2.getString("cust_name");
	                }
	                String date=resultSet1.getString("invo_date");
	                double bal_amt=Double.parseDouble(resultSet1.getString("bal_amt"));
	                
	                BigDecimal bw = new BigDecimal(bal_amt);
	        		bw = bw.setScale(2, BigDecimal.ROUND_HALF_UP);
	        		bal_amt = bw.doubleValue();
	        		
	        		BigDecimal ba = new BigDecimal(total_amt);
	        		ba = ba.setScale(2, BigDecimal.ROUND_HALF_UP);
	        		total_amt = ba.doubleValue();
	                model.addRow(new Object[]{date,invoice_num,total_amt,cust_id,cust_name,bal_amt});
	                ttl_bal=ttl_bal+bal_amt;
				}
				BigDecimal bw = new BigDecimal(ttl_bal);
        		bw = bw.setScale(2, BigDecimal.ROUND_HALF_UP);
        		ttl_bal = bw.doubleValue();
				textField_2.setText(Double.toString(ttl_bal));
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Pending invoice List Found");
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