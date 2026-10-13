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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import org.json.JSONException;

import sales_distribution.Cust_voucher_list;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JCheckBox;

public class cust_account_payable {

	private static JFrame frame;
	private JTable table;
	private JTable table1;
	private static DefaultTableModel model;
	private static DefaultTableModel model1;
	public static JTextField textField;
	public static JTextField textField_1;
	static JTextField textField_2;
	private static JButton btnNewButton_1;
	private static JButton btnNewButton;
	private static JCheckBox chckbxSelectAll ;
	private static JTextField textField_3;
	private static JTextField textField_4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					cust_account_payable window = new cust_account_payable("mr_pro", "admin", "S01","1.0","2");
					cust_account_payable.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public cust_account_payable(String company, String username, String store_name,String version,String decimal) throws JSONException {
		initialize(company,username, store_name, version,decimal);
	}


	/**
	 * Initialize the contents of the frame.
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 */
	private void initialize(final String company, final String username,final String store_name,final String version,final String decimal) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.setAlwaysOnTop(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCompanyName = new JLabel("Customer Code:");
		lblCompanyName.setBounds(21, 77, 102, 21);
		lblCompanyName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblCompanyName);
		
		Image Billing=new ImageIcon(this.getClass().getResource("/Billing-icon.png")).getImage();
		
		JLabel lblCompanySetup = new JLabel("Customer Payable");
		lblCompanySetup.setBounds(21, 11, 228, 44);
		lblCompanySetup.setIcon(new ImageIcon(Billing));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 1018, 2);
		frame.getContentPane().add(separator);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
	    JMenuItem menuItem_1 = new JMenuItem("Refresh");
	    menuItem_1 = new JMenuItem("Refresh",KeyEvent.VK_F5);
	    menuItem_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		textField.setText("");
	    		textField_1.setText("");
	    		textField_2.setText("0.00");
	    		int row=model.getRowCount();
				for (int i = row - 1; i >= 0; i--) {
					model.removeRow(i);
	        	}
				int rows=model1.getRowCount();
				for (int i = rows - 1; i >= 0; i--) {
					model1.removeRow(i);
	        	}
	    	}
	    });
	    menuItem_1.setBorder(null);
	    menuItem_1.setIcon(new ImageIcon(refresh));
	    menuItem_1.setBackground(new Color(243, 229, 216));
	    menuItem_1.setBounds(933, 11, 80, 48);
	    frame.getContentPane().add(menuItem_1);
	    
	    JMenuItem menuItem_2 = new JMenuItem("Exit");
	    menuItem_2.setAccelerator(KeyStroke.getKeyStroke("F4"));
	    menuItem_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		frame.setVisible(false);
	    		Cust_voucher_list.get_po_disp( company,"2","");
	    	}
	    });
	    menuItem_2.setBorder(null);
	    menuItem_2.setIcon(new ImageIcon(exit));
	    menuItem_2.setBackground(new Color(243, 229, 216));
	    menuItem_2.setBounds(873, 11, 60, 48);
	    frame.getContentPane().add(menuItem_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 164, 1018, 2);
		frame.getContentPane().add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 239, 1008, 197);
		frame.getContentPane().add(scrollPane);
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				  if(column==0)
			        {
			        	return true;  
			        }
					return false;  
			}
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				
				default:
					return String.class;
				}
			}
		};
		table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(20);
			model.addColumn(""); 
			 model.addColumn("Sl. No.");
		    model.addColumn("Invoice Number"); 
		    model.addColumn("Invoice Number"); 
			model.addColumn("Invoice Date"); 
			model.addColumn("Invoice Amt"); 
			model.addColumn("Paid Amt"); 
			model.addColumn("Due to Pay"); 
			//model.addColumn("Pay Terms"); 
			//model.addColumn("Due Days"); 
			
			scrollPane.setViewportView(table);
			table.setFocusable(false);
			
//			table.addMouseListener(new MouseAdapter() {
//				public void mousePressed(MouseEvent mouseEvent) {
//					JTable table_2 = (JTable) mouseEvent.getSource();
//					if (mouseEvent.getClickCount() == 2 && table_2.getSelectedRow() != -1) {
//						int rows = table_2.getSelectedRow();
//						new accounts_master.Paymentvouch(company,username,store_name,model.getValueAt(rows, 1).toString(),Double.parseDouble(model.getValueAt(rows, 6).toString()),textField.getText(),textField_1.getText(), version,decimal);
//					}
//				}
//			});
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
			
		    JLabel lblAccountType = new JLabel("Customer Name:");
		    lblAccountType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    lblAccountType.setBounds(21, 110, 102, 21);
		    frame.getContentPane().add(lblAccountType);
		    
		    textField = new JTextField();
		    textField.setEditable(false);
		    textField.setBounds(133, 78, 184, 20);
		    frame.getContentPane().add(textField);
		    textField.setColumns(10);
		    
		    textField_1 = new JTextField();
		    textField_1.setEditable(false);
		    textField_1.setColumns(10);
		    textField_1.setBounds(133, 111, 329, 20);
		    frame.getContentPane().add(textField_1);
		    
		    Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
			JButton button_1 = new JButton("");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new Cust_Chooser(company, "cust_payable", username, store_name, "","", version,"1");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			button_1.setIcon(new ImageIcon(serch));
			button_1.setBounds(321, 77, 41, 21);
			frame.getContentPane().add(button_1);
		    
		    
		    
		    JLabel lblOutstandingBalance = new JLabel("Outstanding Balance");
		    lblOutstandingBalance.setFont(new Font("Tahoma", Font.BOLD, 13));
		    lblOutstandingBalance.setBounds(856, 66, 152, 23);
		    frame.getContentPane().add(lblOutstandingBalance);
		    
		    textField_2 = new JTextField();
		    textField_2.setToolTipText("Outstanding Balance");
		    textField_2.setEditable(false);
		    textField_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		    textField_2.setBackground(new Color(255, 255, 153));
		    textField_2.setBounds(856, 96, 136, 52);
		    frame.getContentPane().add(textField_2);
		    textField_2.setColumns(10);
		    
		    
		    Image printer = new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		    btnNewButton_1 = new JButton("Print Statement");
		    btnNewButton_1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(textField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Select One Customer");
					}
					else
					{
						new Date_range(company, username, store_name, textField.getText(), version,textField_1.getText());
					}
		    	}
		    });
		    btnNewButton_1.setEnabled(false);
		    btnNewButton_1.setIcon(new ImageIcon(printer));
		    btnNewButton_1.setBounds(633, 12, 184, 44);
		    frame.getContentPane().add(btnNewButton_1);
		    
		    JScrollPane scrollPane_1 = new JScrollPane();
		    scrollPane_1.setBounds(5, 478, 1008, 249);
		    frame.getContentPane().add(scrollPane_1);
		    model1 = new DefaultTableModel(){

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
			table1 = new JTable(model1);
			    table1.setFont(new Font("Serif", Font.BOLD, 15));
				table1.setRowHeight(20);
				model1.addColumn("Sl.No."); 
			    model1.addColumn("Tran Number"); 
			    model1.addColumn("Invoice Number"); 
				model1.addColumn("Invoice Date"); 
				model1.addColumn("Invoice Amt"); 
				model1.addColumn("Paid Amt"); 
				model1.addColumn("Due to Pay"); 
				//model1.addColumn("Pay Terms");
				scrollPane_1.setViewportView(table1);
			    
		    JLabel lblPaidAmounts = new JLabel("Paid Invoices:-");
		    lblPaidAmounts.setFont(new Font("Tahoma", Font.BOLD, 15));
		    lblPaidAmounts.setBounds(10, 446, 150, 31);
		    frame.getContentPane().add(lblPaidAmounts);
		    
		    JSeparator separator_2 = new JSeparator();
		    separator_2.setBounds(-43, 478, 1061, 2);
		    frame.getContentPane().add(separator_2);
		    
		    JLabel lblPendingAmounts = new JLabel("Pending Invoices :-");
		    lblPendingAmounts.setFont(new Font("Tahoma", Font.BOLD, 15));
		    lblPendingAmounts.setBounds(10, 172, 145, 31);
		    frame.getContentPane().add(lblPendingAmounts);
		    
		    chckbxSelectAll = new JCheckBox("Select All");
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
		    chckbxSelectAll.setBounds(0, 212, 113, 25);
		    frame.getContentPane().add(chckbxSelectAll);
		    
		    JButton btnPay = new JButton("Pay");
		    btnPay.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		double amt_ttl = 0;
		    		Object[] item_list = new String[model.getRowCount()];
		    		boolean status=false;
					//String list="";
					for (int i = 0; i < model.getRowCount(); i++) {
						Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
						String dataCol1 = model.getValueAt(i, 2).toString();
						if (chked) {
							item_list[i]=dataCol1;
							amt_ttl+=Double.parseDouble(model.getValueAt(i, 7).toString());
							status=true;
						}
					}
					if(status==true)
					{
						new cust_payment_voucher(company,username,store_name,item_list,amt_ttl,textField.getText(),textField_1.getText(), version,decimal,"");	
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Choose PO from Below table");
					}
					
		    	}
		    });
		    btnPay.setBounds(916, 212, 97, 25);
		    frame.getContentPane().add(btnPay);
		    
		    btnNewButton = new JButton("Advance Invoice");
		    btnNewButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		//company,username,store,balance,vendor_id,vendor_name,version,decimal
		    		String vendor_id=textField.getText();
		    		String vendor_name=textField_1.getText();
		    		new sales_distribution.advance_payment(company,username,store_name,vendor_id,vendor_name,version,decimal);
		    	}
		    });
		    btnNewButton.setBounds(458, 12, 151, 44);
		    btnNewButton.setEnabled(false);
		    frame.getContentPane().add(btnNewButton);
		    
		    textField_3 = new JTextField();
		    textField_3.setToolTipText("Advance Amount");
		    textField_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		    textField_3.setEditable(false);
		    textField_3.setColumns(10);
		    textField_3.setBackground(new Color(255, 255, 153));
		    textField_3.setBounds(665, 99, 136, 52);
		    frame.getContentPane().add(textField_3);
		    
		    JLabel lblAdvanceAmount = new JLabel("Advance Amount");
		    lblAdvanceAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		    lblAdvanceAmount.setBounds(665, 69, 136, 23);
		    frame.getContentPane().add(lblAdvanceAmount);
		    
		    textField_4 = new JTextField();
		    textField_4.setToolTipText("Invoice Amount");
		    textField_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		    textField_4.setEditable(false);
		    textField_4.setColumns(10);
		    textField_4.setBackground(new Color(255, 255, 153));
		    textField_4.setBounds(474, 96, 136, 52);
		    frame.getContentPane().add(textField_4);
		    
		    JLabel lblInvoiceAmount = new JLabel("Invoice Amount");
		    lblInvoiceAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		    lblInvoiceAmount.setBounds(474, 66, 136, 23);
		    frame.getContentPane().add(lblInvoiceAmount);
		    
		    JLabel label_2 = new JLabel("+");
		    label_2.setFont(new Font("Tahoma", Font.PLAIN, 41));
		    label_2.setBounds(622, 96, 42, 55);
		    frame.getContentPane().add(label_2);
		    
		    JLabel label_3 = new JLabel("=");
		    label_3.setFont(new Font("Tahoma", Font.PLAIN, 41));
		    label_3.setBounds(813, 96, 42, 55);
		    frame.getContentPane().add(label_3);
		
		    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		 
		    frame.setVisible(true);
		
		frame.setResizable(false);
		frame.setBounds(0, 0, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	public static void  get_po_term_disp(String company)
	{
		
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_sd_customer_payment where ref_cust_id='"+textField.getText()+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model1.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model1.removeRow(i);
			}
			int row = model.getRowCount();
			for (int i = row - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			chckbxSelectAll.setSelected(false);
			double ttl_bal=0;
			double ttl_bal_ad=0;
			while(resultSet.next())
			{
				if(resultSet.getString("bal_amt").equals("0"))
				{
					
					if(resultSet.getString("status").equals("1"))
					{
						double bal=Double.parseDouble(resultSet.getString("bal_amt"));
						BigDecimal bc = new BigDecimal(bal);
		        		bc = bc.setScale(2, BigDecimal.ROUND_HALF_UP);
		        		bal = bc.doubleValue();
						double paid=Double.parseDouble(resultSet.getString("paid_amt"));
						BigDecimal bd = new BigDecimal(paid);
		        		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		        		paid = bd.doubleValue();
		        		double inv=Double.parseDouble(resultSet.getString("invo_amt"));
						BigDecimal be = new BigDecimal(inv);
		        		be = be.setScale(2, BigDecimal.ROUND_HALF_UP);
		        		inv = be.doubleValue();
						model1.addRow(new Object[]{model1.getRowCount()+1,resultSet.getString("ref_tran_num"),resultSet.getString("invo_num"),resultSet.getString("invo_date"),inv,paid,bal});
					}
				}
				else
				{
					if(resultSet.getString("type").equals("AD")  )
					{
						if(resultSet.getString("status").equals("1"))
						{
							double bal=Double.parseDouble(resultSet.getString("bal_amt"));
							BigDecimal bc = new BigDecimal(bal);
			        		bc = bc.setScale(2, BigDecimal.ROUND_HALF_UP);
			        		bal = bc.doubleValue();
							double paid=Double.parseDouble(resultSet.getString("paid_amt"));
							BigDecimal bd = new BigDecimal(paid);
			        		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			        		paid = bd.doubleValue();
			        		double inv=Double.parseDouble(resultSet.getString("invo_amt"));
							BigDecimal be = new BigDecimal(inv);
			        		be = be.setScale(2, BigDecimal.ROUND_HALF_UP);
			        		inv = be.doubleValue();
							model1.addRow(new Object[]{model1.getRowCount()+1,resultSet.getString("ref_tran_num"),resultSet.getString("invo_num"),resultSet.getString("invo_date"),inv,paid,bal});
							ttl_bal_ad+=(Double.parseDouble(resultSet.getString("bal_amt")));
						}
						
					}
					else
					{
						double bal=Double.parseDouble(resultSet.getString("bal_amt"));
						BigDecimal bc = new BigDecimal(bal);
		        		bc = bc.setScale(2, BigDecimal.ROUND_HALF_UP);
		        		bal = bc.doubleValue();
						double paid=Double.parseDouble(resultSet.getString("paid_amt"));
						BigDecimal bd = new BigDecimal(paid);
		        		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		        		paid = bd.doubleValue();
		        		double inv=Double.parseDouble(resultSet.getString("invo_amt"));
						BigDecimal be = new BigDecimal(inv);
		        		be = be.setScale(2, BigDecimal.ROUND_HALF_UP);
		        		inv = be.doubleValue();
						model.addRow(new Object[]{new Boolean( false ),model.getRowCount()+1,resultSet.getString("ref_tran_num"),resultSet.getString("invo_num"),resultSet.getString("invo_date"),inv,paid,bal});
						ttl_bal+=Double.parseDouble(resultSet.getString("bal_amt"));
					}
					
				}
				
			}
			BigDecimal bd = new BigDecimal(ttl_bal_ad);
    		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
    		ttl_bal_ad = bd.doubleValue();
    		BigDecimal bc = new BigDecimal(ttl_bal);
    		bc = bc.setScale(2, BigDecimal.ROUND_HALF_UP);
    		ttl_bal = bc.doubleValue();
    		double ttl=ttl_bal-ttl_bal_ad;
			textField_2.setText(Double.toString(ttl));
			textField_3.setText(Double.toString(ttl_bal_ad));
			textField_4.setText(Double.toString(ttl_bal));
			btnNewButton_1.setEnabled(true);
			btnNewButton.setEnabled(true);
			
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
}
