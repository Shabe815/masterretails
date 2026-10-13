package sales_master;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import javax.swing.JComboBox;

public class dup_cust_vochr {

	private JFrame frame;
	private DefaultTableModel model;
	private JTable table;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JComboBox<String> comboBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dup_cust_vochr window = new dup_cust_vochr("mr_pro", "admin","S01", null,2);
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
	public dup_cust_vochr(String company, String username,String store,String version,int decimal) throws JSONException {
		initialize(company,username,store, version,decimal);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param page 
	 */
	private void initialize(final String company,final String username,final String store,final String version,final int decimal) throws JSONException {
		frame = new JFrame( version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 696);
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
		
		
		JLabel lblItemMasterList = new JLabel("Voucher List");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
				get_po_disp( company, "VOID", "",comboBox.getSelectedItem().toString());
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 589);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		//scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
		    
		    model.addColumn("Tran Date");
		    model.addColumn("Posted Date");
		    model.addColumn("Tran Number");
		    model.addColumn("Supp. Invoice No."); 
		    model.addColumn("Cust Code"); 
		    model.addColumn("Cust Name"); 
		    model.addColumn("Inv Amount");
		    model.addColumn("Disc Amt");
		    model.addColumn("Pay method"); 
		    model.addColumn("Cheque#");

		scrollPane.setViewportView(table);
		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(85);
			  if (i==1) column.setPreferredWidth(85);
			  if (i==2) column.setPreferredWidth(125);
			  if (i==3) column.setPreferredWidth(160);
			  if (i==4) column.setPreferredWidth(50);
			  if (i==5) column.setPreferredWidth(350);
			  if (i==6) column.setPreferredWidth(75);
			  if (i==7) column.setPreferredWidth(75);
			  if (i==8) column.setPreferredWidth(75);
			  if (i==9) column.setPreferredWidth(100);
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
				  int rowCount = model.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					get_po_disp( company,"VOID",textField.getText(),comboBox.getSelectedItem().toString());
			  }
			});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( company,"VOID",textField.getText(),comboBox.getSelectedItem().toString());
			}
		});
		textField.setBounds(12, 13, 493, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( company,"VOID",textField.getText(),comboBox.getSelectedItem().toString());
			}
		});
		btnSearch.setBounds(518, 13, 97, 25);
		panel_2.add(btnSearch);
		
		JLabel lblSortBy = new JLabel("Sort By");
		lblSortBy.setBounds(705, 4, 56, 16);
		panel_2.add(lblSortBy);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("Customer Code");
		comboBox.addItem("Invoice Number");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( company,"VOID",textField.getText(),comboBox.getSelectedItem().toString());
			}
		});
		comboBox.setBounds(705, 24, 191, 22);
		panel_2.add(comboBox);
		
		 
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                try {
                	JTable table =(JTable) mouseEvent.getSource();
                    if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    	int column = 2;
                    	int rows = table.getSelectedRow();
                    	String value = table.getModel().getValueAt(rows, column).toString();
                    	Connection con=Dbsetup.dbconn.getConnection();
                    	report_master.sd_pay_voucher.report(con,value, table.getModel().getValueAt(rows, 4).toString(), company);
                    	
                    }
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
            	
            }
        });
        get_po_disp( company,"VOID",textField.getText(),comboBox.getSelectedItem().toString());
        
	}
	private void  get_po_disp( String company, String type, String search, String sort)
	{
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and ref_cust_id LIKE '%"+search+"%' OR cust_name LIKE '%"+search+"%' OR seq_num LIKE '%"+search+"%' OR ref_tran_num LIKE '%"+search+"%' ";
		}
		String sort_str = "";
		if(sort.equals("Customer Code"))
		{
			sort_str="order by ref_cust_id ASC";
		}
		else
		{
			sort_str="order by ref_tran_num ASC";
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_sd_customer_payment_details inner join "+company+".mr_customer_master on mr_customer_master.cust_code=mr_sd_customer_payment_details.ref_cust_id where mr_sd_customer_payment_details.status='2' "+serch_str+" "+sort_str+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query1="SELECT *,mr_sd_customer_payment_details.created_date as date FROM "+company+".mr_sd_customer_payment_details inner join "+company+".mr_customer_master on mr_customer_master.cust_code=mr_sd_customer_payment_details.ref_cust_id where mr_sd_customer_payment_details.status='2' "+serch_str+"  "+sort_str+" "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String issue_date=resultSet1.getString("issue_date");
					String posted_date=resultSet1.getString("date");
	                String seq_num=resultSet1.getString("seq_num");
	                String ref_tran_num=resultSet1.getString("ref_tran_num");
	                String dr_amt=resultSet1.getString("dr_amt");
	                String disc_amt=resultSet1.getString("disc_amt");
	                String payment_method=resultSet1.getString("payment_method");
	                String cheque_no=resultSet1.getString("cheque_no");
	                String ref_cust_id=resultSet1.getString("ref_cust_id");
	                String ref_cust_name=resultSet1.getString("cust_name");
	                
	                
	                model.addRow(new Object[]{issue_date,posted_date,seq_num,ref_tran_num,ref_cust_id,ref_cust_name,dr_amt,disc_amt,payment_method,cheque_no});
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No PO List Found");
	        	for (int i = model.getRowCount() - 1; i >= 0; i--) {
	        	    model.removeRow(i);
	        	}
			}
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
