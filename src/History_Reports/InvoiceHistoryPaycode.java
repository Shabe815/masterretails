package History_Reports;

import java.awt.Color;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class InvoiceHistoryPaycode {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private String printer;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvoiceHistoryPaycode window = new InvoiceHistoryPaycode("mr_pro","S01","SHABEER", null, "S0101","2","");
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
	public InvoiceHistoryPaycode(String company, String store_name, String username,String version,String reg_nme,String decimal,String language) {
		initialize(company,store_name,username, version,reg_nme,decimal,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store_name 
	 * @param host_name 
	 * @param company 
	 * @param language 
	 */
	private void initialize(final String company,final String store_name,final String username,final String version,final String reg_nme,final String decimal, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 1015, 720);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		Image printer1=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1009, 685);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 109, 985, 563);
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
		    model.addColumn("Register");
		    model.addColumn("Date");
		    model.addColumn("Invoice No:"); 
		    model.addColumn("Cust Code"); 
			model.addColumn("Cust Name"); 
			model.addColumn("Paid Amt"); 
			model.addColumn("Paycode"); 
		
	
			//int a=table.getColumnCount();
//			for(int i=0;i<a;i++)
//			{
//				TableColumn column=table.getColumnModel().getColumn(i);
//				if(i==0){
//					column.setPreferredWidth(15);
//				}
//				if(i==3){
//					column.setPreferredWidth(25);
//				}
//				if(i==4){
//					column.setPreferredWidth(10);
//				}
//				if(i==5){
//					column.setPreferredWidth(10);
//				}
//			}
		    scrollPane.setViewportView(table);
			
		    table.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent mouseEvent) {
	                JTable table =(JTable) mouseEvent.getSource();
	                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
	                	int rows = table.getSelectedRow();
	                	String reg = table.getModel().getValueAt(rows, 1).toString();
	                	String inv = table.getModel().getValueAt(rows, 3).toString();
	                	new HistoryInvoice_View(company, store_name, username, version, reg, decimal,inv, language);
	                }
	                
	            }
	        });
			
			JLabel lblCloseSales = new JLabel("Invoice History");
			lblCloseSales.setFont(new Font("Monospaced", Font.BOLD, 25));
			lblCloseSales.setBounds(12, 11, 373, 53);
			panel.add(lblCloseSales);
			
			JMenuItem mntmPrintVoucher = new JMenuItem("Print");
			mntmPrintVoucher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int rows = table.getSelectedRow();
					if(rows!=-1)
					{
						String value = table.getModel().getValueAt(rows, 3).toString();
						String register = table.getModel().getValueAt(rows, 1).toString();
						duplicate_print(company,store_name,value,printer,register,"print");
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Select any row from table");
					}
				}
			});
			mntmPrintVoucher.setForeground(Color.GRAY);
			mntmPrintVoucher.setFont(new Font("Segoe UI", Font.BOLD, 15));
			mntmPrintVoucher.setBorder(null);
			mntmPrintVoucher.setBackground(new Color(255, 228, 196));
			mntmPrintVoucher.setBounds(894, 11, 103, 30);
			mntmPrintVoucher.setIcon(new ImageIcon(printer1));
			panel.add(mntmPrintVoucher);
			
			textField = new JTextField();
			textField.setBounds(80, 74, 386, 22);
			panel.add(textField);
			textField.setColumns(10);
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
					  get_full_paycode(company,reg_nme,username,store_name,textField.getText());
				  }
				});
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode()==KeyEvent.VK_ENTER){
						get_full_paycode(company,reg_nme,username,store_name,textField.getText());
					}
				}
			});
			
			JLabel lblSearch = new JLabel("Search");
			lblSearch.setBounds(12, 77, 56, 16);
			panel.add(lblSearch);
			
			JMenuItem mntmPrintA = new JMenuItem("Print A4");
			mntmPrintA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int rows = table.getSelectedRow();
					if(rows!=-1)
					{
						String register = table.getModel().getValueAt(rows, 1).toString();
						String invoice = table.getModel().getValueAt(rows, 3).toString();
						duplicate_printA4(company,store_name,invoice,register,"");
					}
					
					
				}
			});
			mntmPrintA.setForeground(Color.GRAY);
			mntmPrintA.setFont(new Font("Segoe UI", Font.BOLD, 15));
			mntmPrintA.setBorder(null);
			mntmPrintA.setBackground(new Color(255, 228, 196));
			mntmPrintA.setBounds(779, 11, 103, 30);
			mntmPrintA.setIcon(new ImageIcon(printer1));
			panel.add(mntmPrintA);
			
			get_register_name(company,reg_nme);
			get_full_paycode(company, reg_nme,username,store_name,"");
	}
	public void duplicate_print(String company,String store_name, String invoice_num,String printer,String reg_nme, String type) {
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			report_master.bill_reciptHistory.report(invoice_num, con, printer,reg_nme,"DUP",type,company);
			JOptionPane.showMessageDialog(frame,"Transaction Completed");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	public void duplicate_printA4(String company,String store_name, String invoice_num,String reg_nme, String type) {
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			String printer2="";
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_nme+"' ");
			if(resultSet.next())
			{
				printer2=resultSet.getString("printer2");
			}
			report_master.bill_reciptHistoryA4.report(invoice_num, con, printer2,reg_nme,"DUP",type,company);
			JOptionPane.showMessageDialog(frame,"Transaction Completed");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	private void get_full_paycode(String company,String reg_nme,String username,String store_name, String search)
	{
		Connection con=Dbsetup.local_server.getConnection();
		Statement statement;
		try {
			String searchstr="";
			if(!search.equals(""))
			{
				searchstr=" and (receipt_no like '%"+search+"%' or cust_name like '%"+search+"%' or type like '%"+search+"%' or grand_total like '%"+search+"%')";
			}
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		statement = con.createStatement();
		//String query="SELECT mr_pro.mr_tmp_item_master.department,mr_pro.mr_tmp_item_master.item_code,mr_pro.mr_tmp_item_master.item_name,sum(mr_pro.mr_pos_settlement_cash_items.qty) as qty,sum(mr_pro.mr_pos_settlement_cash_items.unit_price*mr_pro.mr_pos_settlement_cash_items.qty) as net_sale,sum(mr_pro.mr_pos_settlement_cash_items.total+mr_pro.mr_pos_settlement_cash_items.vat) as sale_amt  FROM mr_pro.mr_tmp_item_master INNER JOIN mr_pro.mr_pos_settlement_cash_items on mr_pro.mr_pos_settlement_cash_items.item_code=mr_pro.mr_tmp_item_master.item_code INNER JOIN mr_pro.mr_pos_settlement_cash on mr_pro.mr_pos_settlement_cash.reg_num=mr_pro.mr_pos_settlement_cash_items.ref_reg_num and mr_pro.mr_pos_settlement_cash.receipt_no=mr_pro.mr_pos_settlement_cash_items.ref_receipt_num where mr_pro.mr_pos_settlement_cash_items.ref_reg_num='"+reg_nme+"' and (mr_pos_settlement_cash.type='PAYCODE' or mr_pos_settlement_cash.type='CASH') GROUP BY mr_pro.mr_tmp_item_master.department";
		String query="SELECT * from "+company+".mr_pos_settlement_history where status='1' "+searchstr+" ";
		ResultSet resultSet0 = statement.executeQuery(query);
		while(resultSet0.next())
		{
			String reg_num=resultSet0.getString("reg_num");
			String created_date=resultSet0.getString("created_date");
			String time=resultSet0.getString("time");
			String inv_date=created_date+" "+time;
			String seq_num=resultSet0.getString("receipt_no");
			String code=resultSet0.getString("cust_code");
			String name=resultSet0.getString("cust_name");
			String amt=resultSet0.getString("grand_total");
			String paycode=resultSet0.getString("type");
			model.addRow(new Object[]{model.getRowCount()+1,reg_num,inv_date,seq_num,code,name,amt,paycode});
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
