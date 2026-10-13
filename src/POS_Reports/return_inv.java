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
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTextField;

public class return_inv {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private String printer;
	private static int decimal;
	private static DecimalFormat df2;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					return_inv window = new return_inv("mr_pro","S01","SHABEER", null, "S0101","2","HISTORY");
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
	public return_inv(String company, String store_name, String username,String version,String reg_nme,String decimal,String page_type) {
		initialize(company,store_name,username, version,reg_nme,decimal,page_type);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store_name 
	 * @param host_name 
	 * @param company 
	 * @param page_type 
	 */
	private void initialize(final String company,final String store_name,final String username,String version,final String reg_nme,final String decimal, String page_type) {
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
		scrollPane.setBounds(0, 77, 786, 535);
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
		    model.addColumn("Receipt Number"); 
		    model.addColumn("User Name");
		    model.addColumn("Item Name"); 
		    model.addColumn("Qty"); 
		    model.addColumn("Price"); 
		    model.addColumn("Total"); 
		
	
			int a=table.getColumnCount();
			for(int i=0;i<a;i++)
			{
				TableColumn column=table.getColumnModel().getColumn(i);
				if(i==0){
					column.setPreferredWidth(15);
				}
				if(i==3){
					column.setPreferredWidth(25);
				}
				if(i==4){
					column.setPreferredWidth(10);
				}
				if(i==5){
					column.setPreferredWidth(10);
				}
			}
		    scrollPane.setViewportView(table);
			
			
			
			JLabel lblCloseSales = new JLabel("RETURN INVOICES LIST");
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
						report_master.return_inv_list.report(reg_nme, con, printer,company);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			menuItem_1.setBorder(null);
			menuItem_1.setBackground(new Color(255, 228, 196));
			menuItem_1.setIcon(new ImageIcon(printer1));
			
			textField = new JTextField();
			textField.setText("0.0");
			textField.setEditable(false);
			textField.setColumns(10);
			textField.setBounds(658, 625, 116, 22);
			panel.add(textField);
			
			JLabel label = new JLabel("Total");
			label.setFont(new Font("Tahoma", Font.BOLD, 15));
			label.setBounds(590, 627, 56, 16);
			panel.add(label);
			
			get_register_name(company,reg_nme);
			get_store_validate_dt(company,store_name,username, reg_nme);
			get_full_paycode(company, reg_nme,username,store_name);
			
	}
	private void get_full_paycode(String company,String reg_nme,String username,String store_name)
	{
		Connection con=Dbsetup.local_server.getConnection();
		Statement statement;
		try {
			double ttl=0;
			statement = con.createStatement();
			String query="SELECT *,mr_pos_settlement_cash_items.qty as itemqty FROM "+company+".mr_pos_settlement_cash_items inner join "+company+".mr_pos_settlement_cash  on "+company+".mr_pos_settlement_cash.reg_num="+company+".mr_pos_settlement_cash_items.ref_reg_num and mr_pos_settlement_cash_items.ref_receipt_num=mr_pos_settlement_cash.receipt_no WHERE mr_pos_settlement_cash_items.qty < '0' and ref_reg_num='"+reg_nme+"'";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String receipt_no=resultSet.getString("ref_receipt_num");
				String user=resultSet.getString("user");
				String item_name=resultSet.getString("item_desc");
				String price=resultSet.getString("unit_price");
				String total=resultSet.getString("total");
				String qty=df2.format(Float.parseFloat(resultSet.getString("itemqty")));
				model.addRow(new Object[]{receipt_no,user,item_name,qty,price,total});
				ttl=ttl+Double.parseDouble(total);
			}
			BigDecimal bd = new BigDecimal(ttl);
    		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
    		ttl = bd.doubleValue();
    		textField.setText(Double.toString(ttl));
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
	
	public static void get_store_validate_dt(String company,String store_name,String username, String posreg)
	{
		try {
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store where store_id='"+store_name+"' ");
			if(resultSet.next())
			{
				
				decimal=Integer.parseInt(resultSet.getString("decimal_count"));
				if(decimal==2)
				{
					df2=new DecimalFormat("0.00");
				}
				else
				{
					df2=new DecimalFormat("0.000");
				}
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
