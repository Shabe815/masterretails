package POS_Reports;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class delivery_list {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private String printer;
	private JTextField textField;
	private JComboBox<String> comboBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					delivery_list window = new delivery_list("mr_pro","S01","SHABEER", null, "S0101","2");
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
	public delivery_list(String company, String store_name, String username,String version,String reg_nme,String decimal) {
		initialize(company,store_name,username, version,reg_nme,decimal);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store_name 
	 * @param host_name 
	 * @param company 
	 */
	private void initialize(final String company,final String store_name,final String username,String version,final String reg_nme,final String decimal) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 792, 720);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		Image printer1=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 13, 786, 672);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 77, 786, 548);
		panel.add(scrollPane);
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
		    model.addColumn("Sl No:"); 
		    model.addColumn("Receipt No"); 
			model.addColumn("Customer Details"); 
			model.addColumn("Sales Amt"); 
			model.addColumn("DateTime"); 
			model.addColumn("Status"); 
		
	
			int a=table.getColumnCount();
			for(int i=0;i<a;i++)
			{
				TableColumn column=table.getColumnModel().getColumn(i);
				if(i==0){
					column.setPreferredWidth(10);
				}
				if(i==1){
					column.setPreferredWidth(25);
				}
				if(i==2){
					column.setPreferredWidth(150);
				}
				if(i==3){
					column.setPreferredWidth(25);
				}
				if(i==4){
					column.setPreferredWidth(70);
				}
				if(i==5){
					column.setPreferredWidth(50);
				}
			}
		    scrollPane.setViewportView(table);
			
			
			
			JLabel lblCloseSales = new JLabel("Delivery Order Summary");
			lblCloseSales.setFont(new Font("Monospaced", Font.BOLD, 25));
			lblCloseSales.setBounds(12, 11, 373, 53);
			panel.add(lblCloseSales);
			
			JMenuItem menuItem_1 = new JMenuItem("Print");
			menuItem_1.setBounds(694, 13, 80, 30);
			panel.add(menuItem_1);
			menuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
			menuItem_1.setForeground(Color.GRAY);
			menuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Connection con=Dbsetup.local_server.getConnection();
					try {
						report_master.delivery_grouped_invoice.report(con, printer, reg_nme,comboBox.getSelectedItem().toString(), company);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			menuItem_1.setBorder(null);
			menuItem_1.setBackground(new Color(255, 228, 196));
			menuItem_1.setIcon(new ImageIcon(printer1));
			
			JLabel lblTotal = new JLabel("Total");
			lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblTotal.setBounds(574, 634, 56, 25);
			panel.add(lblTotal);
			
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.BOLD, 13));
			textField.setEditable(false);
			textField.setBounds(658, 634, 116, 25);
			panel.add(textField);
			textField.setColumns(10);
			
			comboBox = new JComboBox<String> ();
			comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
			
			comboBox.setBounds(371, 29, 294, 43);
			panel.add(comboBox);
			try {
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();
				String query="select user_name from "+company+".mr_users where user_type='Delivery Boy' ";
				ResultSet resultSet1 = statement.executeQuery(query);
				while(resultSet1.next())
				{
					comboBox.addItem(resultSet1.getString("user_name"));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					get_full_paycode(company, reg_nme,username,store_name,comboBox.getSelectedItem().toString());
				}
			});
			
			JLabel lblSelectDeliveryBoy = new JLabel("Select Delivery Boy");
			lblSelectDeliveryBoy.setBounds(400, 0, 240, 16);
			panel.add(lblSelectDeliveryBoy);
			
			get_register_name(company,reg_nme);
			get_full_paycode(company, reg_nme,username,store_name,comboBox.getSelectedItem().toString());
	}
	private void get_full_paycode(String company,String reg_nme,String username,String store_name,String delivery_boy)
	{
		Connection con=Dbsetup.local_server.getConnection();
		Statement statement;
		try {
		statement = con.createStatement();
		String query="SELECT * from "+company+".mr_pos_settlement_cash where mode='HOME DELIVERY' and delivery_user='"+delivery_boy+"'";
		ResultSet resultSet0 = statement.executeQuery(query);
		int rowCount = model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		while(resultSet0.next())
		{
			String receipt_no=resultSet0.getString("receipt_no");
			String cust_code=resultSet0.getString("cust_code");
			String cust_name=resultSet0.getString("cust_name");
			String cust_ph=resultSet0.getString("cust_ph");
			String grand_total=resultSet0.getString("grand_total");
			String time=resultSet0.getString("time");
			String created_date=resultSet0.getString("created_date");
			
			model.addRow(new Object[]{model.getRowCount()+1,receipt_no,cust_code+" - "+cust_name+" - "+cust_ph,grand_total,created_date+" "+time,"Settled"});
		}
		
		String query1="SELECT * from "+company+".mr_pos_hold_invoice where mode='HOME DELIVERY' and delivery_user='"+delivery_boy+"'";
		ResultSet resultSet1 = statement.executeQuery(query1);
		while(resultSet1.next())
		{
			String receipt_no=resultSet1.getString("receipt_no");
			String cust_code=resultSet1.getString("cust_code");
			String cust_name=resultSet1.getString("cust_name");
			String cust_ph=resultSet1.getString("cust_ph");
			String grand_total=resultSet1.getString("total");
			String time=resultSet1.getString("time");
			String created_date=resultSet1.getString("created_date");
			
			model.addRow(new Object[]{model.getRowCount()+1,receipt_no,cust_code+" - "+cust_name+" - "+cust_ph,grand_total,created_date+" "+time,"UnSettled"});
		}
		
		con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	public void get_register_name(String company, String reg_nme){
		try {
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_nme+"' ");
			if(resultSet2.next())
			{
				printer=resultSet2.getString("printer1");
			}
			
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	
}
