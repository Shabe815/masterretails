package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

import CafePro.Mainpage;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.KeyAdapter;

public class Store_credit_list {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private DefaultTableModel model1;
	private JTextField textField;
	private JTextField textField_1;
	static JTextField textField_2;
	private JTable table_1;
	private JTextField textField_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Store_credit_list window = new Store_credit_list("mr_pro", "admin", "","85", null,"","2");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param  
	 * @param vendor_id 
	 */
	public Store_credit_list(String company,String username,String version,String Amount,String store,String page_type, String decimal) throws JSONException {
		initialize(company, username, version,Amount,store,page_type,decimal);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param amount 
	 * @param page_type 
	 * @param vendor_id 
	 */
	private void initialize(final String company,final String username,final String version, String amount, final String store, final String page_type, final String decimal) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
	    frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 572);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		
		frame.setLocationRelativeTo(null);
		
		JLabel lblCompanySetup = new JLabel("Store Credit List");
		lblCompanySetup.setBounds(21, 11, 228, 44);
		Image user=new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		frame.getContentPane().setLayout(null);
		lblCompanySetup.setIcon(new ImageIcon(user));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.setBounds(485, 7, 60, 48);
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				if(page_type.equals("POS"))
				{
					if(Mainpage.SoftLang.equals("Ar"))
					{
						new paycode(company, username, store, "PAYCODE",  PosscreenArabic.reg_nme, PosscreenArabic.textField_8.getText(), PosscreenArabic.btnNewButton.getText(), PosscreenArabic.textField_2.getText(), version, PosscreenArabic.textField_4.getText(), PosscreenArabic.decimalPlaces,"","",PosscreenArabic.textField_9.getText());
					}
					else
					{
						new paycode(company, username, store, "PAYCODE",  posscreen.reg_nme, posscreen.textField_8.getText(), posscreen.btnNewButton.getText(), posscreen.textField_2.getText(), version, posscreen.textField_4.getText(), posscreen.decimalPlaces,"","",posscreen.textField_9.getText());
					}
						
				}
				
//				purchase_master.vendor_details.frame.setAlwaysOnTop(true);
//				purchase_master.vendor_details.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				purchase_master.vendor_details.mntmNewMenuItem.setEnabled(true);
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		frame.getContentPane().add(mntmNewMenuItem);

		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.setBounds(544, 7, 80, 48);
		mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemsdisp(company,"");
				textField.setText("");
			}
		});
		mntmRefresh.setBackground(new Color(243, 229, 216));
		mntmRefresh.setIcon(new ImageIcon(refresh));
		mntmRefresh.setBorder(null);
		frame.getContentPane().add(mntmRefresh);
		
		JMenuItem mntmSave = new JMenuItem("Issue");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try {
//					LocalDateTime myDateObj = LocalDateTime.now();  
//				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
//				    String formattedDate = myDateObj.format(myFormatObj);
//					
//				    Connection con=Dbsetup.dbconn.getConnection();
//					Statement statement=con.createStatement(); 
//				    for(int i=0;i<model1.getRowCount();i++)
//				    {
//				    	ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store_credit where ref_invoice_num='"+model1.getValueAt(i, 1).toString()+"' ");
//				    	if(resultSet.next())
//				    	{
//				    		//String query="update "+company+".mr_store_credit set `modified_date`='"+formattedDate+"', `status`='1', `used_date`='"+formattedDate+"', `used_receipt`=='"+posscreen.textField_8.getText()+"', `used_reg_num`='"+posscreen.reg_nme+"', `used_store`='"+store+"' where ref_invoice_num='"+model1.getValueAt(i, 1).toString()+"' ";
//				    		//statement.executeUpdate(query);
//				    	}
//				    }
//					
//				} catch (Exception e1) {
//					System.err.println(e1);
//				}
				String Applied_credit="";
				for(int i=0;i<model1.getRowCount();i++)
				{
					String Invoice_num=model1.getValueAt(i, 1).toString();
					String Store_reg=model1.getValueAt(i,2).toString();
					Applied_credit+=Invoice_num+"@"+Store_reg+",";
				}
				if(page_type.equals("POS"))
				{
					if(Mainpage.SoftLang.equals("Ar"))
					{
						new paycode(company, username, store,"PAYCODE",  PosscreenArabic.reg_nme, PosscreenArabic.textField_8.getText(), PosscreenArabic.btnNewButton.getText(), PosscreenArabic.textField_2.getText(), version, PosscreenArabic.textField_4.getText(), PosscreenArabic.decimalPlaces,textField_2.getText(),Applied_credit,PosscreenArabic.textField_9.getText());
						paycode.textField_29.setText(textField_2.getText());
						double bal=Float.parseFloat(textField_1.getText())-Float.parseFloat(textField_2.getText());
						BigDecimal bd = new BigDecimal(bal);
	            		bd = bd.setScale(PosscreenArabic.decimalPlaces, BigDecimal.ROUND_HALF_UP);
	            		bal = bd.doubleValue();
						paycode.textField_7.setText(Double.toString(bal));
						paycode.textField_30.setText(Applied_credit);
					}
					else
					{
						new paycode(company, username, store,"PAYCODE",  posscreen.reg_nme, posscreen.textField_8.getText(), posscreen.btnNewButton.getText(), posscreen.textField_2.getText(), version, posscreen.textField_4.getText(), posscreen.decimalPlaces,textField_2.getText(),Applied_credit,posscreen.textField_9.getText());
						paycode.textField_29.setText(textField_2.getText());
						double bal=Float.parseFloat(textField_1.getText())-Float.parseFloat(textField_2.getText());
						BigDecimal bd = new BigDecimal(bal);
	            		bd = bd.setScale(posscreen.decimalPlaces, BigDecimal.ROUND_HALF_UP);
	            		bal = bd.doubleValue();
						paycode.textField_7.setText(Double.toString(bal));
						paycode.textField_30.setText(Applied_credit);
					}
					
				}
				else
				{
					paycode.textField_29.setText(textField_2.getText());
					double bal=Float.parseFloat(paycode.textField_7.getText())-Float.parseFloat(textField_2.getText());
					BigDecimal bd = new BigDecimal(bal);
            		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
            		bal = bd.doubleValue();
					paycode.textField_7.setText(Double.toString(bal));
					paycode.textField_30.setText(Applied_credit);
				}
				frame.setVisible(false);
				
				
			}

		});
		mntmSave.setBackground(new Color(243, 229, 216));
		mntmSave.setIcon(new ImageIcon(save));
		mntmSave.setBounds(624, 7, 115, 48);
		mntmSave.setBorder(null);
		frame.getContentPane().add(mntmSave);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 118, 685, 194);
		scrollPane.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(scrollPane);
		
		
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
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				
				
				default:
					return String.class;
				}
			}
		};
		table = new JTable(model);
		model.addColumn("Store Credit #"); 
	    model.addColumn("Receipt"); 
	    model.addColumn("Register"); 
		model.addColumn("Credit Date"); 
		model.addColumn("Amount");  
		model.addColumn("Customer Name");
		model.addColumn("Phone");
		model.addColumn("Validity Date"); 
		model.addColumn("Remark"); 
		scrollPane.setViewportView(table);
		
		//model.getColumnClass(0).
		/*button.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
			}
		});
		*/
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int rows = table.getSelectedRow();
                	String ref_invoice_num=table.getModel().getValueAt(rows, 1).toString();
                    String register=table.getModel().getValueAt(rows, 2).toString();
                    String issue_date=table.getModel().getValueAt(rows, 3).toString();
                    String amount=table.getModel().getValueAt(rows, 4).toString();
                    String cust_name=table.getModel().getValueAt(rows, 5).toString();
                    String mobile=table.getModel().getValueAt(rows, 6).toString();
                    String valid_upto=table.getModel().getValueAt(rows, 7).toString();
                    String remark=table.getModel().getValueAt(rows, 8).toString();
                    if(Float.parseFloat(textField_1.getText())<(Float.parseFloat(textField_2.getText())+Float.parseFloat(amount)))
                    {
                    	JOptionPane.showMessageDialog(frame, "The Due Amount is Lesser than Applied Amount");
                    	textField_2.setText(textField_2.getText());
                    }
                    else
                    {
                    	model1.addRow(new Object[]{model1.getRowCount()+1, ref_invoice_num, register,issue_date,amount,cust_name,mobile,valid_upto,remark});
                        model.removeRow(rows);
                        for(int i=0;i<model.getRowCount();i++)
                        {
                        	model.setValueAt(i+1, i, 0);
                        }
                        float ttl=0;
                        for(int i=0;i<model1.getRowCount();i++)
                        {
                        	String ttl_applied=model1.getValueAt(i, 4).toString();
                        	ttl+=Float.parseFloat(ttl_applied);
                        	textField_2.setText(Float.toString(ttl));
                        }
                        
                        textField_3.setText(Float.toString((Float.parseFloat(textField_1.getText())-Float.parseFloat(textField_2.getText()))));
                    }
                    
                    
                }
				
			}
		});
		
		
		
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 68, 694, 52);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					select_dt(company,textField.getText());
				}
			}
		});
		textField.setColumns(10);
		textField.setBounds(12, 13, 247, 25);
		panel.add(textField);
		
		JButton button = new JButton("Search");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemsdisp(company,textField.getText());
			}
		});
		button.setBounds(271, 13, 97, 25);
		panel.add(button);
		
		JLabel lblNewLabel = new JLabel("POS Amount");
		lblNewLabel.setBounds(465, 2, 84, 22);
		panel.add(lblNewLabel);
		
		textField_1 = new JTextField(amount);
		textField_1.setEditable(false);
		textField_1.setBounds(548, 2, 146, 22);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAppliedAmt = new JLabel("Applied Amt");
		lblAppliedAmt.setBounds(465, 26, 84, 22);
		panel.add(lblAppliedAmt);
		
		textField_2 = new JTextField();
		textField_2.setText("0");
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(548, 26, 146, 22);
		panel.add(textField_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(5, 330, 685, 194);
		scrollPane_1.setBackground(new Color(243, 229, 216));
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
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				
				
				default:
					return String.class;
				}
			}
		};
		table_1 = new JTable(model1);
		model1.addColumn("Store Credit #"); 
	    model1.addColumn("Receipt"); 
	    model1.addColumn("Register"); 
		model1.addColumn("Credit Date"); 
		model1.addColumn("Amount");  
		model1.addColumn("Customer Name");
		model1.addColumn("Phone");
		model1.addColumn("Validity Date"); 
		model1.addColumn("Remark"); 
		scrollPane_1.setViewportView(table_1);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setEditable(false);
		textField_3.setVisible(false);
		textField_3.setBounds(346, 46, 116, 22);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		get_itemsdisp(company,"");
		
	}
	protected void select_dt(String company, String serch) {
		try{
			LocalDateTime myDateObj = LocalDateTime.now();  
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		    String formattedDate = myDateObj.format(myFormatObj);
		    
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			String search="";
			if(!serch.equals("")){
				search=" and (ref_invoice_num = '"+serch+"' OR id = '"+serch+"' )";
			}
			String query="SELECT * FROM "+company+".mr_store_credit where status=0 and valid_upto>='"+formattedDate+"' "+search+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			String ref_invoice_num="";
			if(resultSet.next())
			{
				String id=resultSet.getString("id");
				ref_invoice_num=resultSet.getString("ref_invoice_num");
                String register=resultSet.getString("register");
                String issue_date=resultSet.getString("issue_date");
                String amount=resultSet.getString("amount");
                String cust_name=resultSet.getString("cust_name");
                String mobile=resultSet.getString("mobile");
                String remark=resultSet.getString("remark");
                String valid_upto=resultSet.getString("valid_upto");
                model1.addRow(new Object[]{id, ref_invoice_num, register,issue_date,amount,cust_name,mobile,valid_upto,remark});
                String query1="SELECT * FROM "+company+".mr_store_credit where status=0 and valid_upto>='"+formattedDate+"' ";
    			ResultSet resultSet1 = statement.executeQuery(query1);
    			while(resultSet1.next())
    			{
    				if(!resultSet1.getString("ref_invoice_num").equals(ref_invoice_num))
    				{
    					String id1=resultSet1.getString("id");
    					String invoice_num=resultSet1.getString("ref_invoice_num");
    	                String register1=resultSet1.getString("register");
    	                String issue_date1=resultSet1.getString("issue_date");
    	                String amount1=resultSet1.getString("amount");
    	                String cust_name1=resultSet1.getString("cust_name");
    	                String mobile1=resultSet1.getString("mobile");
    	                String remark1=resultSet1.getString("remark");
    	                String valid_upto1=resultSet1.getString("valid_upto");
    	                model.addRow(new Object[]{id1, invoice_num, register1,issue_date1,amount1,cust_name1,mobile1,valid_upto1,remark1});
    				}
                }
            }
			else
			{
				JOptionPane.showMessageDialog(frame, "No Credits Found ");
				textField.setText("");
				get_itemsdisp(company, "");
			}
			
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}

	private void get_itemsdisp( String company,String serch)
	{
		try{
			LocalDateTime myDateObj = LocalDateTime.now();  
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		    String formattedDate = myDateObj.format(myFormatObj);
		    
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			String search="";
			if(!serch.equals("")){
				search=" and ref_invoice_num LIKE '%"+serch+"%' OR cust_name LIKE '%"+serch+"%' OR id LIKE '%"+serch+"%' ";
			}
			String query="SELECT * FROM "+company+".mr_store_credit where status=0 and valid_upto>='"+formattedDate+"' "+search+" ";// INNER join "+company+".mr_item_barcode on "+company+".mr_item_master.item_code="+company+".mr_item_barcode.ref_item_code "+serch_str+" GROUP BY "+company+".mr_item_master.item_code 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String ref_invoice_num=resultSet.getString("ref_invoice_num");
                String register=resultSet.getString("register");
                String issue_date=resultSet.getString("issue_date");
                String amount=resultSet.getString("amount");
                String cust_name=resultSet.getString("cust_name");
                String mobile=resultSet.getString("mobile");
                String remark=resultSet.getString("remark");
                String valid_upto=resultSet.getString("valid_upto");
                String id=resultSet.getString("id");
                model.addRow(new Object[]{id, ref_invoice_num, register,issue_date,amount,cust_name,mobile,valid_upto,remark});
               
            }
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
