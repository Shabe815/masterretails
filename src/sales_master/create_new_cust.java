package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.json.JSONException;

import CafePro.Mainpage;

import javax.swing.JTextField;

import java.awt.event.KeyAdapter;

public class create_new_cust {

	private static JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					create_new_cust window = new create_new_cust("mr_pro", null,"", null, null, null, null, null,null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param company 
	 * @param host_name 
	 * @param page_type 
	 * @param username 
	 * @param store_name 
	 * @param total 
	 * @param old_recipt 
	 * @param sts 
	 * @param page_type 
	 * @param string 
	 * @throws JSONException 
	 */
	public create_new_cust(String company,String version, String sts,String username,String store_name,String posreg,Connection con, String page_type, String value) throws JSONException {
		initialize(company, version,sts,username,store_name,posreg, con, page_type,value);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param company 
	 * @param host_name 
	 * @param page_type 
	 * @param username 
	 * @param store_name 
	 * @param sts 
	 * @param page_type 
	 * @throws JSONException 
	 */
	private void initialize(final String company,  final String version, final String sts,final String username,final String store_name,final String posreg,final Connection con,final String page_type, final String value) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 644, 513);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 626, 466);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblShifts = new JLabel("Delivery Customer");
		lblShifts.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblShifts.setBounds(10, 12, 228, 44);
		panel.add(lblShifts);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    String formattedDate = myDateObj.format(myFormatObj);  
				String code=textField.getText();
				String name=textField_1.getText();
				String addr1=textField_2.getText();
				String addr2=textField_3.getText();
				String ph1=textField.getText();
				String ph2=textField_5.getText();
				if(code.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Plaese Enter or Select the Customer Mobile Number");
				}
				else if(name.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Plaese Enter the Customer Name");
				}
				else
				{
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement = null;
					
					try {
						statement = con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_customer_master where cust_code='"+code+"' ");
						if(!resultSet.next())
						{
							String querty_code="select cust_code from "+company+".mr_customer_master order by cast(cust_code as UNSIGNED ) desc limit  1";
							statement = con.createStatement();
							ResultSet resultSet2 = statement.executeQuery(querty_code);
							if(resultSet2.next())
							{
								String code_old=resultSet2.getString("cust_code");
								code=Integer.toString(Integer.parseInt(code_old)+1);
							}
							
							String query="insert into "+company+".mr_customer_master(`cust_code`, `cust_name`, `type`, `category`, `sub_category`, `rating`, `terms_code`, `shipping_code`, `currency`, `sales_rep`, `disc`, `addr1`, `addr2`, `city`, `country`, `id_type`, `id_num`, `ph1`, `ph2`, `default_ar_acc`, `credit_limit`, `posted_bal`, `unposted_bal`, `avail_credit`, `last_sale_date`, `last_payment_date`, `last_paid_amt`, `created_date`, `modified_date`,TRN,status,open_bal,contact_person) "
									+ "values('"+code+"','"+name+"','CASH','NIL','INC','GOOD','CASH','BY ROAD','AED','ADMIN','0','"+addr1+"','"+addr2+"','','UAE','EMIRATES ID','','"+ph1+"','"+ph2+"','0101013001','0','0','0','0','"+formattedDate+"','"+formattedDate+"','0','"+formattedDate+"','"+formattedDate+"','','1','0','') ";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
							
							if(page_type.equals("hold_select"))
							{
								if(Mainpage.SoftLang.equals("Ar"))
								{
									sales_master.PosscreenArabic.btnNewButton.setText(name);
					    			sales_master.PosscreenArabic.textField_6.setText("0");
					    			if(PosscreenArabic.CustDisc.equals("1"))
					    			{
					    				sales_master.PosscreenArabic.textField_7.setText("0");
					    				sales_master.PosscreenArabic.lblNewLabel_2.setText("0%");
					    			}
					    			
					    			sales_master.PosscreenArabic.textField_9.setText(code);
					    			sales_master.PosscreenArabic.textField_12.setText(ph1);
					    			if(PosscreenArabic.table.getRowCount()!=0)
					    			{
					    				PosscreenArabic.disc_perc(company, username,store_name,PosscreenArabic.reg_nme, "0","CUST");	
					    			}
					    			frame.setVisible(false);
					    			PosscreenArabic.page_type="Kitchen KOT";
					    			PosscreenArabic.hold_inv=true;
					    			PosscreenArabic.hold_inv(company, username,store_name, posreg, con,"HOME DELIVERY",version,"","HOME DELIVERY",PosscreenArabic.textField_8.getText());
								}
								else
								{
									sales_master.posscreen.btnNewButton.setText(name);
					    			sales_master.posscreen.textField_6.setText("0");
					    			if(posscreen.CustDisc.equals("1"))
					    			{
					    				sales_master.posscreen.textField_7.setText("0");
					    				sales_master.posscreen.lblNewLabel_2.setText("0%");
					    			}
					    			
					    			sales_master.posscreen.textField_9.setText(code);
					    			sales_master.posscreen.textField_12.setText(ph1);
					    			if(posscreen.table.getRowCount()!=0)
					    			{
					    				posscreen.disc_perc(company, username,store_name,posscreen.reg_nme, "0","CUST");	
					    			}
					    			frame.setVisible(false);
					    			posscreen.page_type="Kitchen KOT";
									posscreen.hold_inv=true;
									posscreen.hold_inv(company, username,store_name, posreg, con,"HOME DELIVERY",version,"","HOME DELIVERY",posscreen.textField_8.getText(),"0");
								}
								
							}
							if(page_type.equals("cust_chooser"))
							{
								if(Mainpage.SoftLang.equals("Ar"))
								{
									sales_master.PosscreenArabic.btnNewButton.setText(name);
						    		if(PosscreenArabic.gs.length>1)
									{
						    		sales_master.SecondDisplay.btnNewButton.setText(name);
									}
									sales_master.PosscreenArabic.textField_6.setText("0");
									if(PosscreenArabic.CustDisc.equals("1"))
					    			{
					    				sales_master.PosscreenArabic.textField_7.setText("0");
					    				sales_master.PosscreenArabic.lblNewLabel_2.setText("0%");
					    			}
					    			
					    			sales_master.PosscreenArabic.textField_9.setText(code);
					    			sales_master.PosscreenArabic.textField_12.setText(ph1);
					    			if(PosscreenArabic.table.getRowCount()!=0)
					    			{
					    				PosscreenArabic.disc_perc(company, username,store_name,PosscreenArabic.reg_nme, "0","CUST");	
					    			}
								}
								else
								{
									sales_master.posscreen.btnNewButton.setText(name);
						    		if(posscreen.gs.length>1)
									{
						    		sales_master.SecondDisplay.btnNewButton.setText(name);
									}
									sales_master.posscreen.textField_6.setText("0");
									if(posscreen.CustDisc.equals("1"))
					    			{
					    				sales_master.posscreen.textField_7.setText("0");
					    				sales_master.posscreen.lblNewLabel_2.setText("0%");
					    			}
					    			
					    			sales_master.posscreen.textField_9.setText(code);
					    			sales_master.posscreen.textField_12.setText(ph1);
					    			if(posscreen.table.getRowCount()!=0)
					    			{
					    				posscreen.disc_perc(company, username,store_name,posscreen.reg_nme, "0","CUST");	
					    			}
								}
								
								
							}
							
							
							
							delivery_customer.frame.setVisible(false);
							if(Mainpage.SoftLang.equals("Ar"))
							{
								PosscreenArabic.btnNewButton.setEnabled(true);
							}
							else
							{
								posscreen.btnNewButton.setEnabled(true);
							}
							
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Customer Already Existed");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				}
			}
		});
		menuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem_2.setBorder(null);
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(533, 0, 138, 49);
		panel.add(menuItem_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(452, 0, 71, 49);
		panel.add(mntmNewMenuItem);
		
		
		    
		    textField = new JTextField(value);
		    textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    textField.addKeyListener(new KeyAdapter() {
		    	@Override
		    	public void keyPressed(KeyEvent e) {
		    		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
						textField_1.requestFocus();
			        }
		    	}
		    });
		    
		    
		    textField.setBounds(10, 100, 604, 35);
		    panel.add(textField);
		    textField.setColumns(10);
		    
		  clickOnback( mntmNewMenuItem, "play", KeyEvent.VK_ESCAPE );
		    
		    JLabel lblCustCode = new JLabel("Mobile No.");
		    lblCustCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    lblCustCode.setBounds(10, 68, 145, 20);
		    panel.add(lblCustCode);
		    
		    JLabel lblCustomerName = new JLabel("Customer Name");
		    lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    lblCustomerName.setBounds(10, 147, 145, 20);
		    panel.add(lblCustomerName);
		    
		    textField_1 = new JTextField();
		    textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    textField_1.addKeyListener(new KeyAdapter() {
		    	@Override
		    	public void keyPressed(KeyEvent e) {
		    		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
						textField_2.requestFocus();
			        }
		    	}
		    	@Override
		    	public void keyTyped(KeyEvent e) {
		    		char vChar = e.getKeyChar();
					
					if ((vChar == KeyEvent.VK_COMMA))
			        {
			                e.consume();
			        }
		    	}
		    });
		    textField_1.setColumns(10);
		    textField_1.setBounds(10, 179, 604, 35);
		    panel.add(textField_1);
		    
		    JLabel lblAddress = new JLabel("Address 1");
		    lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    lblAddress.setBounds(10, 226, 145, 20);
		    panel.add(lblAddress);
		    
		    textField_2 = new JTextField();
		    textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    textField_2.addKeyListener(new KeyAdapter() {
		    	@Override
		    	public void keyPressed(KeyEvent e) {
		    		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
						textField_3.requestFocus();
			        }
		    	}
		    });
		    textField_2.setColumns(10);
		    textField_2.setBounds(10, 258, 604, 35);
		    panel.add(textField_2);
		    
		    JLabel lblAddress_1 = new JLabel("Address 2");
		    lblAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    lblAddress_1.setBounds(10, 305, 145, 20);
		    panel.add(lblAddress_1);
		    
		    textField_3 = new JTextField();
		    textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    textField_3.addKeyListener(new KeyAdapter() {
		    	@Override
		    	public void keyPressed(KeyEvent e) {
		    		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
						textField_5.requestFocus();
			        }
		    	}
		    });
		    textField_3.setColumns(10);
		    textField_3.setBounds(10, 337, 604, 35);
		    panel.add(textField_3);
		    
		    JLabel lblAlternateMobileNo = new JLabel("Alternate Mobile No.");
		    lblAlternateMobileNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    lblAlternateMobileNo.setBounds(10, 384, 145, 20);
		    panel.add(lblAlternateMobileNo);
		    
		    textField_5 = new JTextField();
		    textField_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    textField_5.setColumns(10);
		    textField_5.setBounds(10, 416, 604, 35);
		    panel.add(textField_5);
			textField.requestFocus();
	}

	public static void clickOnback(final AbstractButton button, String actionName, int key)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6694624852420723982L;

			/**
			 * 
			 */
			public void actionPerformed( ActionEvent e )
	        {
				frame.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
				}
				else
				{
					posscreen.txtDisc.requestFocus();
				}
				
	        }
	    } );
	}
}
