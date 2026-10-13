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
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import javax.swing.JComboBox;

public class po_posted_list {

	private static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	private JTextField textField;
	private static JTextField textField_1;
	JComboBox<String> comboBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					po_posted_list window = new po_posted_list("mr_pro", "admin","S01", null,2,"");
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
	public po_posted_list(String company, String username,String store,String version,int decimal,String language) throws JSONException {
		initialize(company,username,store, version,decimal, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param page 
	 */
	private void initialize(final String company,final String username,final String store,final String version,final int decimal,final String language) throws JSONException {
		frame = new JFrame( version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 730);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("PO Posted List");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setAlwaysOnTop(false);
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(780, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( company, "POSTED", "",comboBox.getSelectedItem().toString());
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 623);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 88, 908, 488);
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
		    
		    model.addColumn("Purchase Number");
		    model.addColumn("Supp. Invoice No."); 
		    model.addColumn("Total Amt");
		    model.addColumn("Paid Amt");
		    model.addColumn("Balance Amount");
		    model.addColumn("vendor Code");
		    model.addColumn("vendor Name");
		    model.addColumn("PO Date");
		    model.addColumn("Remarks");
		scrollPane.setViewportView(table);
		
		
		//table.setAutoCreateRowSorter(true);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(100);
			  if (i==1) column.setPreferredWidth(50);
			  if (i==2) column.setPreferredWidth(50);
			  if (i==3) column.setPreferredWidth(50);
			  if (i==4) column.setPreferredWidth(60);
			  if (i==5) column.setPreferredWidth(60);
			  if (i==6) column.setPreferredWidth(150);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 908, 52);
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
				  get_po_disp( company,"POSTED",textField.getText(),comboBox.getSelectedItem().toString());
			  }
			});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( company,"POSTED",textField.getText(),comboBox.getSelectedItem().toString());
			}
		});
		textField.setBounds(12, 13, 493, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( company,"POSTED",textField.getText(),comboBox.getSelectedItem().toString());
			}
		});
		btnSearch.setBounds(518, 13, 97, 25);
		panel_2.add(btnSearch);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( company,"POSTED",textField.getText(),comboBox.getSelectedItem().toString());
			}
		});
		comboBox.setBounds(678, 23, 219, 22);
		comboBox.addItem("ALL");
		comboBox.addItem("Full Paid");
		comboBox.addItem("Partial Paid");
		comboBox.addItem("Unpaid");
		panel_2.add(comboBox);
		
		JLabel lblInvoicePaymentType = new JLabel("Invoice Payment type");
		lblInvoicePaymentType.setBounds(678, 0, 218, 22);
		panel_2.add(lblInvoicePaymentType);
		
		JLabel lblTotalAmount = new JLabel("TOTAL AMOUNT");
		lblTotalAmount.setBounds(88, 589, 105, 16);
		panel_1.add(lblTotalAmount);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(205, 586, 169, 22);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		 
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int rows = table.getSelectedRow();
                	try{
            			
	 					new po_view(company,username, table.getModel().getValueAt(rows, 5).toString(),table.getModel().getValueAt(rows, 0).toString(),store, version,"POSTED","PO_post",decimal,table.getModel().getValueAt(rows, 4).toString(), language);
                	}
                	catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                	
                	//frame.setVisible(false);
                }
            }
        });
        get_po_disp( company,"POSTED",textField.getText(),comboBox.getSelectedItem().toString());
	}
	public static void  get_po_disp( String company, String type, String search,String mode)
	{
		String mode_str = "";
		if(mode.equals("Full Paid"))
		{
			mode_str=" and  bal_amt=0";
		}
		if(mode.equals("Partial Paid"))
		{
			mode_str=" and  bal_amt < grand_total and  bal_amt!=0";
		}
		if(mode.equals("Unpaid"))
		{
			mode_str=" and  bal_amt=grand_total";
		}
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and po_no LIKE '%"+search+"%' OR invo_nu LIKE '%"+search+"%' or vendor_name like '%"+search+"%' or item_name like '%"+search+"%'  ";
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT po_no,invo_nu,grand_total,paid_amt,bal_amt,vendor_id,vendor_name,po_date,remark FROM "+company+".mr_purchase_list_history inner join "+company+".mr_purchase_order_history on mr_purchase_order_history.ref_po_num=po_no inner join "+company+".mr_vendor_payment on mr_vendor_payment.ref_po_num=po_no inner join "+company+".mr_vendor on mr_vendor.vendor_code=mr_purchase_list_history.vendor_id where mr_purchase_list_history.status='"+type+"' "+serch_str+" "+mode_str+" group by po_no order by po_no"; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			double ttl_amt=0;
			if(resultSet.next())
			{
				//String query1="SELECT * FROM "+company+".mr_purchase_list_history inner join "+company+".mr_purchase_order_history on ref_po_num=po_no inner join "+company+".mr_vendor on mr_vendor.vendor_code=mr_purchase_list_history.vendor_id where status='"+type+"' "+serch_str+" group by po_no order by po_no";
				String query1="SELECT po_no,invo_nu,grand_total,paid_amt,bal_amt,vendor_id,vendor_name,po_date,remark FROM "+company+".mr_purchase_list_history inner join "+company+".mr_purchase_order_history on mr_purchase_order_history.ref_po_num=po_no inner join "+company+".mr_vendor_payment on mr_vendor_payment.ref_po_num=po_no inner join "+company+".mr_vendor on mr_vendor.vendor_code=mr_purchase_list_history.vendor_id where mr_purchase_list_history.status='"+type+"' "+serch_str+" "+mode_str+" group by po_no order by po_no"; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String po_no=resultSet1.getString("po_no");
					String invo_nu=resultSet1.getString("invo_nu");
					String grand_ttl=resultSet1.getString("grand_total");
					String paid_amt=resultSet1.getString("paid_amt");
					String bal_amt=resultSet1.getString("bal_amt");
					String vendor_id = resultSet1.getString("vendor_id");
					String vendor_name = resultSet1.getString("vendor_name");
					String po_date=resultSet1.getString("po_date");
	                String remark=resultSet1.getString("remark");
	                
	                
	                model.addRow(new Object[]{po_no,invo_nu,grand_ttl,paid_amt,bal_amt,vendor_id,vendor_name,po_date,remark});
	                ttl_amt=ttl_amt+Double.parseDouble(grand_ttl);
				}
				BigDecimal bz = new BigDecimal(ttl_amt);
	    		bz = bz.setScale(2, BigDecimal.ROUND_HALF_UP);
	    		ttl_amt = bz.doubleValue();
	    		textField_1.setText(Double.toString(ttl_amt));
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No PO List Found");
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
