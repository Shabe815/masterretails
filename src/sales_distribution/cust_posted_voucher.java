package sales_distribution;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class cust_posted_voucher {

	public static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	private JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					cust_posted_voucher window = new cust_posted_voucher("mr_pro","admin","S01", null,"2");
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
	public cust_posted_voucher(String db_name, String username,String store,String version,String decimal) throws JSONException {
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
		frame.setBounds(100, 100, 1023, 748);
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
		panel.setBounds(0, 0, 1017, 96);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image printer = new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		JLabel lblItemMasterList = new JLabel("Customer Payment Posted List");
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
		menuItem.setBounds(865, 11, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name, "3", "");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(925, 11, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Print");
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(795, 11, 60, 48);
		menuItem_2.setIcon(new ImageIcon(printer));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int row=table.getSelectedRow();
					if(row>-1)
					{
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						int rows = table.getSelectedRow();
	                	String inv_num = table.getModel().getValueAt(rows, 1).toString();
	                	String vendor_id = table.getModel().getValueAt(rows, 2).toString();
	                	Connection con=Dbsetup.dbconn.getConnection();
						report_master.sd_pay_voucher.report(con,inv_num,vendor_id,db_name);
						con.close();
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Select A Row from Table");
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, e.getMessage());
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
				
			}
		});
		panel.add(menuItem_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 95, 1005, 605);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 88, 981, 477);
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
		    
		    model.addColumn("Sl	 No.");
		    model.addColumn("Invoice No."); 
		    model.addColumn("Cust Code");
		    model.addColumn("Cust Name");
		    model.addColumn("Issue Date");
		    model.addColumn("Pay Type");
		    model.addColumn("Bank Name");
		    model.addColumn("Cheque No.");
		    model.addColumn("Cheque Date");
		    model.addColumn("Paid amount");
		    model.addColumn("Disc amount");
		    model.addColumn("Applied Credit");
		scrollPane.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(40);
			  if (i==1) column.setPreferredWidth(140);
			  if (i==2) column.setPreferredWidth(140);
			  if (i==3) column.setPreferredWidth(140);
			  if (i==4) column.setPreferredWidth(140);
			  if (i==5) column.setPreferredWidth(140);
			  if (i==6) column.setPreferredWidth(140);
			  if (i==7) column.setPreferredWidth(140);
			  if (i==8) column.setPreferredWidth(140);
			  if (i==9) column.setPreferredWidth(140);
			  if (i==10) column.setPreferredWidth(140);
			  if (i==11) column.setPreferredWidth(140);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 981, 52);
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
				  get_po_disp( db_name,"3",textField.getText());
			  }
			});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					get_po_disp( db_name,"3",textField.getText());
				}
			}
		});
		textField.setBounds(12, 13, 810, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name,"3",textField.getText());
			}
		});
		btnSearch.setBounds(861, 13, 97, 25);
		panel_2.add(btnSearch);
		
		JLabel lblTotalAmt = new JLabel("Total Amount : ");
		lblTotalAmt.setBounds(471, 573, 90, 16);
		panel_1.add(lblTotalAmt);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(573, 570, 192, 22);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(877, 570, 116, 22);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblTotalDisc = new JLabel("Total Disc");
		lblTotalDisc.setBounds(795, 573, 56, 16);
		panel_1.add(lblTotalDisc);
		//comboBox.addItem("DELIVERY");
		 
//        table.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent mouseEvent) {
//                JTable table =(JTable) mouseEvent.getSource();
//                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
//                	int column = 0;
//                	int rows = table.getSelectedRow();
//                	String value = table.getModel().getValueAt(rows, column).toString();
//                	try{
//            			
//	 					new sales_distribution.sd_lines(db_name, username, table.getModel().getValueAt(rows, 2).toString(), value, store, version,decimal);
//	 					//sd_lines.frame.setAlwaysOnTop(true);
//                	}
//                	catch (JSONException | SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//                }
//                
//            }
//        });
        
        get_po_disp( db_name,"3",textField.getText());
        
	}
	static void  get_po_disp( String company, String type, String search)
	{
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and (seq_num LIKE '%"+search+"%' OR ref_cust_id LIKE '%"+search+"%' or pay_by like '%"+search+"%') ";
		}
		
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_sd_customer_payment_details where status='"+type+"' "+serch_str+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				double ttl_amt=0;
				double ttl_disc=0;
				String query1="SELECT *,sum(dr_amt) as dramt,sum(disc_amt) as discamt,sum(cr_amt) as cramt FROM "+company+".mr_sd_customer_payment_details where status='"+type+"' "+serch_str+" group by seq_num "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String invoice_num=resultSet1.getString("seq_num");
	                String vend_id=resultSet1.getString("ref_cust_id");
					String vend_name=resultSet1.getString("pay_by");
					String issue_date=resultSet1.getString("created_date");
					String payment_method=resultSet1.getString("payment_method");
					String bank_name=resultSet1.getString("bank_name");
					String cheque_no=resultSet1.getString("cheque_no");
					String cheque_dt=resultSet1.getString("issue_date");
					String dr_amt=resultSet1.getString("dramt");
					String disc=resultSet1.getString("discamt");
					String cr_amt=resultSet1.getString("cramt");
	                model.addRow(new Object[]{model.getRowCount()+1,invoice_num,vend_id,vend_name,issue_date,payment_method,bank_name,cheque_no,cheque_dt,dr_amt,disc,cr_amt});
	                ttl_amt=ttl_amt+Double.parseDouble(dr_amt);
	                ttl_disc=ttl_disc+Double.parseDouble(disc);
				}
				
				BigDecimal ba = new BigDecimal(ttl_amt);
        		ba = ba.setScale(2, BigDecimal.ROUND_HALF_UP);
        		ttl_amt = ba.doubleValue();
        		textField_1.setText(Double.toString(ttl_amt));
        		
        		BigDecimal bp = new BigDecimal(ttl_disc);
        		bp = bp.setScale(2, BigDecimal.ROUND_HALF_UP);
        		ttl_disc = bp.doubleValue();
        		textField_2.setText(Double.toString(ttl_disc));
        		
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Voucher List Found");
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