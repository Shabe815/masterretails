package accounts_master;

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

import purchase_master.vendor_list;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javax.swing.JCheckBox;

public class Accountpayable {

	private static JFrame frame;
	private JTable table;
	private JTable table1;
	private static DefaultTableModel model;
	private static DefaultTableModel model1;
	public static JTextField textField;
	public static JTextField textField_1;
	private static JTextField textField_2;
	private static JButton btnNewButton_1;
	private static JCheckBox chckbxSelectAll ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					Accountpayable window = new Accountpayable("mr_pro", "admin", "S01","1.0","2","");
					Accountpayable.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Accountpayable(String company, String username, String store_name,String version,String decimal, String language) throws JSONException {
		initialize(company,username, store_name, version,decimal,language);
	}


	/**
	 * Initialize the contents of the frame.
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param language 
	 */
	private void initialize(final String company, final String username,final String store_name,final String version,final String decimal, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		JLabel lblCompanyName = new JLabel("Vendor Code:");
		if(language.equals("Arabic"))
		{
			lblCompanyName.setText("—„“ «·»«∆⁄:");
		}
		lblCompanyName.setBounds(21, 77, 102, 21);
		lblCompanyName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblCompanyName);
		
		Image Billing=new ImageIcon(this.getClass().getResource("/Billing-icon.png")).getImage();
		
		JLabel lblCompanySetup = new JLabel("Account Payable");
		if(language.equals("Arabic"))
		{
			lblCompanyName.setText("«·Õ”«» «·„” Õﬁ «·œ›⁄");
		}
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
	    if(language.equals("Arabic"))
		{
	    	menuItem_1.setText("Ì‰⁄‘");
		}
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
	    if(language.equals("Arabic"))
		{
	    	menuItem_2.setText("„Œ—Ã");
		}
	    menuItem_2.setAccelerator(KeyStroke.getKeyStroke("F4"));
	    menuItem_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		frame.setVisible(false);
	    		ap_voucher_list.get_po_disp( company, "OPEN", "");
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
			if(language.equals("Arabic"))
			{
				model.addColumn("«·—ﬁ„ «· ”·”·Ì");
			    model.addColumn("—ﬁ„ √„— «·‘—«¡"); 
			    model.addColumn("—ﬁ„ ›« Ê—… «·„Ê—œ"); 
				model.addColumn(" «—ÌŒ ›« Ê—… «·„Ê—œ"); 
				model.addColumn("„»·€ ›« Ê—… «·„Ê—œ"); 
				model.addColumn("«·„»·€ «·„œ›Ê⁄"); 
				model.addColumn("»”»» «·œ›⁄"); 
				model.addColumn("‘—Êÿ «·œ›⁄"); 
				model.addColumn("√Ì«„ «·«” Õﬁ«ﬁ"); 
				model.addColumn("‰Ê⁄ «·›« Ê—…"); 
			}
			else
			{
				model.addColumn("Sl. No.");
			    model.addColumn("PO Number"); 
			    model.addColumn("Supp.Inv Number"); 
				model.addColumn("Supp. Inv Date"); 
				model.addColumn("Supp. Inv Amt"); 
				model.addColumn("Paid Amt"); 
				model.addColumn("Due to Pay"); 
				model.addColumn("Pay Terms"); 
				model.addColumn("Due Days"); 
				model.addColumn("Inv. Type"); 
			}
			 
			
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
			
		    JLabel lblAccountType = new JLabel("Vendor Name:");
		    if(language.equals("Arabic"))
			{
		    	lblAccountType.setText("«”„ «·»«∆⁄:");
			}
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
						new vendor_list(company, "vendor_pay", version,decimal,username);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			button_1.setIcon(new ImageIcon(serch));
			button_1.setBounds(321, 77, 41, 21);
			frame.getContentPane().add(button_1);
		    
		    
		    
		    JLabel lblOutstandingBalance = new JLabel("Outstanding Balance");
		    if(language.equals("Arabic"))
			{
		    	lblOutstandingBalance.setText("«·—’Ìœ «·„ „Ì“");
			}
		    lblOutstandingBalance.setFont(new Font("Tahoma", Font.BOLD, 13));
		    lblOutstandingBalance.setBounds(784, 72, 152, 23);
		    frame.getContentPane().add(lblOutstandingBalance);
		    
		    textField_2 = new JTextField();
		    textField_2.setToolTipText("Outstanding Balance");
		    textField_2.setEditable(false);
		    textField_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		    textField_2.setBackground(new Color(255, 255, 153));
		    textField_2.setBounds(784, 102, 136, 52);
		    frame.getContentPane().add(textField_2);
		    textField_2.setColumns(10);
		    
		    
		    Image printer = new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		    btnNewButton_1 = new JButton("Print Statement");
		    btnNewButton_1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(textField.getText().equals(""))
		    		{
		    			JOptionPane.showMessageDialog(frame, "Select a Vendor «Œ — «·»«∆⁄");
		    		}
		    		else
		    		{
		    			new purchase_master.Date_range(company, username, store_name,textField.getText(), version,decimal);	
		    		}
		    		
		    	}
		    });
		    btnNewButton_1.setEnabled(false);
		    btnNewButton_1.setIcon(new ImageIcon(printer));
		    btnNewButton_1.setBounds(567, 87, 184, 44);
		    frame.getContentPane().add(btnNewButton_1);
		    
		    JScrollPane scrollPane_1 = new JScrollPane();
		    scrollPane_1.setBounds(5, 480, 1008, 249);
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
				
				if(language.equals("Arabic"))
				{
					model1.addColumn("«·—ﬁ„ «· ”·”·Ì");
					model1.addColumn("—ﬁ„ √„— «·‘—«¡"); 
					model1.addColumn("—ﬁ„ ›« Ê—… «·„Ê—œ"); 
					model1.addColumn(" «—ÌŒ ›« Ê—… «·„Ê—œ"); 
					model1.addColumn("„»·€ ›« Ê—… «·„Ê—œ"); 
					model1.addColumn("«·„»·€ «·„œ›Ê⁄"); 
					model1.addColumn("»”»» «·œ›⁄"); 
					model1.addColumn("‘—Êÿ «·œ›⁄"); 
					model1.addColumn("√Ì«„ «·«” Õﬁ«ﬁ"); 
				}
				else
				{
					model1.addColumn("Sl.No."); 
				    model1.addColumn("PO Number"); 
				    model1.addColumn("Supp.Inv Number"); 
					model1.addColumn("Supp. Inv Date"); 
					model1.addColumn("Supp. Inv Amt"); 
					model1.addColumn("Paid Amt"); 
					model1.addColumn("Due to Pay"); 
					model1.addColumn("Pay Terms"); 
					model1.addColumn("Due Days"); 
				}
				
				scrollPane_1.setViewportView(table1);
			    
		    JLabel lblPaidAmounts = new JLabel("Paid Invoices:-");
		    if(language.equals("Arabic"))
			{
		    	lblPaidAmounts.setText("«·›Ê« Ì— «·„œ›Ê⁄…:-");
			}
		    lblPaidAmounts.setFont(new Font("Tahoma", Font.BOLD, 15));
		    lblPaidAmounts.setBounds(10, 446, 150, 31);
		    frame.getContentPane().add(lblPaidAmounts);
		    
		    JSeparator separator_2 = new JSeparator();
		    separator_2.setBounds(-43, 478, 1061, 2);
		    frame.getContentPane().add(separator_2);
		    
		    JLabel lblPendingAmounts = new JLabel("Pending Invoices :-");
		    if(language.equals("Arabic"))
			{
		    	lblPendingAmounts.setText("«·›Ê« Ì— «·„⁄·ﬁ… :-");
			}
		    lblPendingAmounts.setFont(new Font("Tahoma", Font.BOLD, 15));
		    lblPendingAmounts.setBounds(10, 172, 145, 31);
		    frame.getContentPane().add(lblPendingAmounts);
		    
		    chckbxSelectAll = new JCheckBox("Select All");
		    if(language.equals("Arabic"))
			{
		    	chckbxSelectAll.setText("Õœœ «·ﬂ·");
			}
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
		    if(language.equals("Arabic"))
			{
		    	btnPay.setText("Ìœ›⁄");
			}
		    btnPay.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		double amt_ttl = 0;
		    		double amt_rtn = 0;
		    		Object[] item_list = new String[model.getRowCount()];
		    		Object[] invoice_list = new String[model.getRowCount()];
		    		Object[] return_list = new String[model.getRowCount()];
		    		boolean status=false;
					//String list="";
					for (int i = 0; i < model.getRowCount(); i++) {
						Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
						String dataCol1 = model.getValueAt(i, 2).toString();
						if (chked) {
							item_list[i]=dataCol1;
							if(model.getValueAt(i, 10).toString().equals("invoice"))
							{
								amt_ttl+=Double.parseDouble(model.getValueAt(i, 7).toString());
								invoice_list[i]=dataCol1;
							}
							else
							{
								amt_rtn+=Double.parseDouble(model.getValueAt(i, 7).toString());
								return_list[i]=dataCol1;
							}
							
							status=true;
						}
					}
					if(status==true)
					{
						new accounts_master.Paymentvouch(company,username,store_name,item_list,amt_ttl,textField.getText(),textField_1.getText(), version,decimal,amt_rtn,invoice_list,return_list,"", language);	
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Choose PO from Below table");
					}
					
		    	}
		    });
		    btnPay.setBounds(916, 212, 97, 25);
		    frame.getContentPane().add(btnPay);
		    
		    JButton btnDebitNote = new JButton("Debit Note");
		    if(language.equals("Arabic"))
			{
		    	btnDebitNote.setText("„–ﬂ—… «·Œ’„");
			}
		    btnDebitNote.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(textField.getText().equals(""))
		    		{
		    			JOptionPane.showMessageDialog(frame, "Select a Vendor «Œ — «·»«∆⁄");
		    		}
		    		else
		    		{
		    			try {
							new accounts_master.Notelist(company,username,store_name, version,decimal, "Debit Note",textField.getText(),textField_1.getText(), language);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    			//new accounts_master.Debitnote(company,username,store_name,textField.getText(),textField_1.getText(), version,decimal,"Debit");	
		    		}
		    		
		    	}
		    });
		    btnDebitNote.setBounds(567, 11, 184, 44);
		    frame.getContentPane().add(btnDebitNote);
		    
		    JButton btnCreditNote = new JButton("Credit Note");
		    if(language.equals("Arabic"))
			{
		    	btnCreditNote.setText("„–ﬂ—… «·«∆ „«‰");
			}
		    btnCreditNote.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(textField.getText().equals(""))
		    		{
		    			JOptionPane.showMessageDialog(frame, "Select a Vendor «Œ — «·»«∆⁄");
		    		}
		    		else
		    		{
		    			try {
							new accounts_master.Notelist(company,username,store_name, version,decimal, "Credit Note",textField.getText(),textField_1.getText(), language);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    			//new accounts_master.Debitnote(company,username,store_name,textField.getText(),textField_1.getText(), version,decimal,"Credit");	
		    		}
		    	}
		    });
		    btnCreditNote.setBounds(371, 11, 184, 44);
		    frame.getContentPane().add(btnCreditNote);
		
		    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		 
		    frame.setVisible(true);
		
		frame.setResizable(false);
		frame.setBounds(0, 0, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	public static void  get_po_term_disp(String company, String vendor, String decimal)
	{
		
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_vendor_payment where ref_vendor_id='"+textField.getText()+"' and status!='4' "; 
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
			while(resultSet.next())
			{
				Calendar c=new GregorianCalendar();
				c.add(Calendar.DATE, Integer.parseInt(resultSet.getString("due_days")));
				Date d=c.getTime();
				Date d1=new Date();
				long diffInMillies = Math.abs(d1.getTime() - d.getTime());
			    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				
				if(resultSet.getString("bal_amt").equals("0"))
				{
					
					double bal=Double.parseDouble(resultSet.getString("bal_amt"));
					BigDecimal bd = new BigDecimal(bal);
	        		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
	        		bal = bd.doubleValue();
					model1.addRow(new Object[]{model1.getRowCount()+1,resultSet.getString("ref_po_num"),resultSet.getString("invo_num"),resultSet.getString("invo_date"),resultSet.getString("invo_amt"),resultSet.getString("paid_amt"),bal,resultSet.getString("pay_terms")});
				}
				else
				{
					String type="";
					if(resultSet.getString("status").equals("1"))
					{
						type="invoice";
					}
					if(resultSet.getString("status").equals("2"))
					{
						type="return";
					}
					double bal=Double.parseDouble(resultSet.getString("bal_amt"));
					BigDecimal bd = new BigDecimal(bal);
	        		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
	        		bal = bd.doubleValue();
					model.addRow(new Object[]{new Boolean( false ),model.getRowCount()+1,resultSet.getString("ref_po_num"),resultSet.getString("invo_num"),resultSet.getString("invo_date"),resultSet.getString("invo_amt"),resultSet.getString("paid_amt"),bal,resultSet.getString("pay_terms"),diff,type});
					if(resultSet.getString("status").equals("2"))
					{
						ttl_bal-=Double.parseDouble(resultSet.getString("bal_amt"));
					}
					else
					{
						ttl_bal+=Double.parseDouble(resultSet.getString("bal_amt"));
					}
					
				}
				
			}
			BigDecimal bc = new BigDecimal(ttl_bal);
    		bc = bc.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
    		ttl_bal = bc.doubleValue();
			textField_2.setText(Double.toString(ttl_bal));
			btnNewButton_1.setEnabled(true);
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
}
