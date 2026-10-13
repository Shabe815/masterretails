package POS_Reports;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTextField;
import javax.swing.JButton;

public class item_sale_list {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private String printer;
	private static int decimal;
	private static DecimalFormat df2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					item_sale_list window = new item_sale_list("mr_pro","S01","SHABEER", null, "S0101","2","");
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
	public item_sale_list(String company, String store_name, String username,String version,String reg_nme,String decimal,String page_type) {
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
		    model.addColumn("Iten Code"); 
		    model.addColumn("Item Name"); 
		    model.addColumn("Qty");
			model.addColumn("Net Sales"); 
			model.addColumn("Discount");
			model.addColumn("Sales Amt"); 
			model.addColumn("Avg Sale Amt"); 
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
			
			
			
			JLabel lblCloseSales = new JLabel("Item Sales Summary");
			lblCloseSales.setFont(new Font("Monospaced", Font.BOLD, 25));
			lblCloseSales.setBounds(12, 11, 468, 53);
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
						report_master.Xreports.report(reg_nme, con, "item_sale",printer,"",company,"");
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
			lblTotal.setBounds(234, 630, 56, 25);
			panel.add(lblTotal);
			
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.BOLD, 13));
			textField.setEditable(false);
			textField.setBounds(302, 630, 116, 25);
			panel.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			textField_1.setEditable(false);
			textField_1.setBounds(420, 630, 116, 25);
			panel.add(textField_1);
			textField_1.setColumns(10);
			
			textField_2 = new JTextField();
			textField_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			textField_2.setEditable(false);
			textField_2.setBounds(542, 630, 116, 25);
			panel.add(textField_2);
			textField_2.setColumns(10);
			
			textField_3 = new JTextField();
			textField_3.setText((String) null);
			textField_3.setFont(new Font("Tahoma", Font.BOLD, 13));
			textField_3.setEditable(false);
			textField_3.setColumns(10);
			textField_3.setBounds(670, 630, 116, 25);
			panel.add(textField_3);
			
			JButton btnNewButton = new JButton("Export To CSV");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
						LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);
						//FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\itemsalelist-"+formattedDate+".csv");
						Writer excel = new OutputStreamWriter(new FileOutputStream("C:\\MasterRetails\\Export\\itemsalelist-"+formattedDate+".csv"), StandardCharsets.UTF_8);
					
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
					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, "Some Error Occured, Please Try After Sometimes");
						JOptionPane.showMessageDialog(frame, e.getMessage());
					}
				}
			});
			btnNewButton.setBounds(542, 11, 140, 25);
			panel.add(btnNewButton);
			
			get_register_name(company,reg_nme);
			get_store_validate_dt(company,store_name,username, reg_nme);
			get_full_paycode_history(company, reg_nme,username,store_name);
			
			
	}
	private void get_full_paycode_history(String company,String reg_nme,String username,String store_name)
	{
		Connection con=Dbsetup.local_server.getConnection();
		Statement statement;
		try {
		statement = con.createStatement();
		String query="SELECT item_desc,item_code,sum("+company+".mr_pos_settlement_cash_items.qty) as qty,sum("+company+".mr_pos_settlement_cash_items.unit_price*"+company+".mr_pos_settlement_cash_items.qty) as net_sale,sum("+company+".mr_pos_settlement_cash_items.total+"+company+".mr_pos_settlement_cash_items.vat) as sale_amt  FROM "+company+".mr_pos_settlement_cash_items inner join "+company+".mr_pos_settlement_cash on mr_pos_settlement_cash.receipt_no=mr_pos_settlement_cash_items.ref_receipt_num and mr_pos_settlement_cash.reg_num=mr_pos_settlement_cash_items.ref_reg_num where "+company+".mr_pos_settlement_cash_items.ref_reg_num='"+reg_nme+"' and type!='void' GROUP BY item_code";
		ResultSet resultSet = statement.executeQuery(query);
		float net_sale=0;
		float amt_sale=0;
		float qty_sale=0;
		float disc_sale=0;
		float avg_sale=0;
		while(resultSet.next())
		{
			String net=df2.format(Float.parseFloat(resultSet.getString("net_sale")));
			String item_code=resultSet.getString("item_code");
			String item_name=resultSet.getString("item_desc");
			String sale=df2.format(Float.parseFloat(resultSet.getString("sale_amt")));
			String qty=df2.format(Float.parseFloat(resultSet.getString("qty")));
			String disc=df2.format(Float.parseFloat(net)-Float.parseFloat(sale));
			net_sale+=Float.parseFloat(net);
			amt_sale+=Float.parseFloat(sale);
			qty_sale+=Float.parseFloat(qty);
			disc_sale+=Float.parseFloat(disc);
			avg_sale=Float.parseFloat(sale)/Float.parseFloat(qty);
			
			model.addRow(new Object[]{item_code,item_name,qty,net,disc,sale,df2.format(avg_sale)});
		}
		textField.setText(df2.format(qty_sale));
		textField_1.setText(df2.format(net_sale));
		textField_2.setText(df2.format(disc_sale));
		textField_3.setText(df2.format(amt_sale));
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
