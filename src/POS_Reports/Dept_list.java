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
import java.text.DecimalFormat;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTextField;

public class Dept_list {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private String printer;
	private static int decimal;
	private static DecimalFormat df2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dept_list window = new Dept_list("mr_pro","S01","SHABEER", null, "S0101","2");
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
	public Dept_list(String company, String store_name, String username,String version,String reg_nme,String decimal) {
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
		    model.addColumn("Dept Name"); 
			model.addColumn("Net Sales"); 
			model.addColumn("Sales Amt"); 
			model.addColumn("Qty"); 
		
	
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
			
			
			
			JLabel lblCloseSales = new JLabel("Department Sales Summary");
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
						report_master.Xreports.report(reg_nme, con, "DEPT",printer,"",company,"");
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
			lblTotal.setBounds(229, 630, 56, 25);
			panel.add(lblTotal);
			
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.BOLD, 13));
			textField.setEditable(false);
			textField.setBounds(338, 630, 116, 25);
			panel.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			textField_1.setEditable(false);
			textField_1.setBounds(503, 630, 116, 25);
			panel.add(textField_1);
			textField_1.setColumns(10);
			
			textField_2 = new JTextField();
			textField_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			textField_2.setEditable(false);
			textField_2.setBounds(658, 630, 116, 25);
			panel.add(textField_2);
			textField_2.setColumns(10);
			
			get_register_name(company,reg_nme);
			get_store_validate_dt(company,store_name,username, reg_nme);
			get_full_paycode(company, reg_nme,username,store_name);
	}
	private void get_full_paycode(String company,String reg_nme,String username,String store_name)
	{
		Connection con=Dbsetup.local_server.getConnection();
		Statement statement;
		try {
		statement = con.createStatement();
		//String query="SELECT mr_pro.mr_tmp_item_master.department,mr_pro.mr_tmp_item_master.item_code,mr_pro.mr_tmp_item_master.item_name,sum(mr_pro.mr_pos_settlement_cash_items.qty) as qty,sum(mr_pro.mr_pos_settlement_cash_items.unit_price*mr_pro.mr_pos_settlement_cash_items.qty) as net_sale,sum(mr_pro.mr_pos_settlement_cash_items.total+mr_pro.mr_pos_settlement_cash_items.vat) as sale_amt  FROM mr_pro.mr_tmp_item_master INNER JOIN mr_pro.mr_pos_settlement_cash_items on mr_pro.mr_pos_settlement_cash_items.item_code=mr_pro.mr_tmp_item_master.item_code INNER JOIN mr_pro.mr_pos_settlement_cash on mr_pro.mr_pos_settlement_cash.reg_num=mr_pro.mr_pos_settlement_cash_items.ref_reg_num and mr_pro.mr_pos_settlement_cash.receipt_no=mr_pro.mr_pos_settlement_cash_items.ref_receipt_num where mr_pro.mr_pos_settlement_cash_items.ref_reg_num='"+reg_nme+"' and (mr_pos_settlement_cash.type='PAYCODE' or mr_pos_settlement_cash.type='CASH') GROUP BY mr_pro.mr_tmp_item_master.department";
		String query="SELECT "+company+".mr_tmp_item_master.department,"+company+".mr_tmp_item_master.item_code,"+company+".mr_tmp_item_master.item_name,sum("+company+".mr_pos_settlement_cash_items.qty) as qty,sum("+company+".mr_pos_settlement_cash_items.unit_price*"+company+".mr_pos_settlement_cash_items.qty) as net_sale,sum("+company+".mr_pos_settlement_cash_items.total+"+company+".mr_pos_settlement_cash_items.vat) as sale_amt  FROM "+company+".mr_tmp_item_master INNER JOIN "+company+".mr_pos_settlement_cash_items on "+company+".mr_pos_settlement_cash_items.barcode="+company+".mr_tmp_item_master.barcode INNER JOIN "+company+".mr_pos_settlement_cash on "+company+".mr_pos_settlement_cash.reg_num="+company+".mr_pos_settlement_cash_items.ref_reg_num and "+company+".mr_pos_settlement_cash.receipt_no="+company+".mr_pos_settlement_cash_items.ref_receipt_num where "+company+".mr_pos_settlement_cash_items.ref_reg_num='"+reg_nme+"' and (mr_pos_settlement_cash.type!='RETURN' and mr_pos_settlement_cash.type!='VOID') GROUP BY "+company+".mr_tmp_item_master.department";
		ResultSet resultSet0 = statement.executeQuery(query);
		float net_sale=0;
		float amt_sale=0;
		float qty_sale=0;
		while(resultSet0.next())
		{
			String dept=resultSet0.getString("department");
			String net=df2.format(Float.parseFloat(resultSet0.getString("net_sale")));
			String sale=df2.format(Float.parseFloat(resultSet0.getString("sale_amt")));
			String qty=df2.format(Float.parseFloat(resultSet0.getString("qty")));
			net_sale+=Float.parseFloat(net);
			amt_sale+=Float.parseFloat(sale);
			qty_sale+=Float.parseFloat(qty);
			model.addRow(new Object[]{model.getRowCount()+1,dept,net,sale,qty});
		}
		textField.setText(Float.toString(net_sale));
		textField_1.setText(Float.toString(amt_sale));
		textField_2.setText(Float.toString(qty_sale));
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
