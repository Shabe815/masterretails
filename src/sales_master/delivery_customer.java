package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import CafePro.Mainpage;

import javax.swing.JTextField;

public class delivery_customer {

	static JFrame frame;
	private static JTable table;
	private DefaultTableModel model;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					delivery_customer window = new delivery_customer("mr_pro", null,"", null, null, null, null, null);
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
	 * @throws JSONException 
	 */
	public delivery_customer(String company,String version, String sts,String username,String store_name,String posreg,Connection con, String page_type) throws JSONException {
		initialize(company, version,sts,username,store_name,posreg, con,  page_type);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param company 
	 * @param host_name 
	 * @param page_type 
	 * @param username 
	 * @param store_name 
	 * @param sts 
	 * @throws JSONException 
	 */
	private void initialize(final String company,  final String version, final String sts,final String username,final String store_name,final String posreg,final Connection con,final String page_type) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 800, 500);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btnNewButton.setEnabled(true);
					PosscreenArabic.textField_9.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btnNewButton.setEnabled(true);
					posscreen.textField_9.setEnabled(true);
				}
				
			}
			@Override
		      public void windowIconified(WindowEvent we) {
		        frame.setState(JFrame.NORMAL);
		      }
		});
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 784, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblShifts = new JLabel("Delivery Customer");
		lblShifts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShifts.setBounds(10, 11, 228, 44);
		panel.add(lblShifts);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.btnNewButton.setEnabled(true);
					PosscreenArabic.textField_9.setEnabled(true);
				}
				else
				{
					posscreen.btnNewButton.setEnabled(true);
					posscreen.textField_9.setEnabled(true);
				}
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(703, 0, 71, 48);
		panel.add(mntmNewMenuItem);
		
		JCheckBox checkBox = new JCheckBox("On Screen Keyboard");
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ad) {
				try {
					textField.requestFocus();
					if(ad.getStateChange() == ItemEvent.SELECTED) {
						//lblNewLabel.
						Runtime.getRuntime().exec("cmd /c osk");
					} 
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		checkBox.setBackground(new Color(243, 229, 216));
		checkBox.setBounds(10, 78, 145, 23);
		panel.add(checkBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 108, 774, 342);
		panel.add(scrollPane);
		
		scrollPane.setEnabled(false);
		scrollPane.setBackground(new Color(243, 229, 216));
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
			table.setRowHeight(30);
		    model.addColumn("Cust Code"); 
		    model.addColumn("Cust Name"); 
			model.addColumn("Phone Number"); 
			model.addColumn("Alternate Number"); 
			model.addColumn("Customer Balance"); 
			model.addColumn("Discount"); 
			model.addColumn("Last Sale Date"); 
		    scrollPane.setViewportView(table);
		    
		    
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        int colmns=model.getColumnCount();
			for (int i=0; i<colmns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(100);
			  if (i==1) column.setPreferredWidth(220);
			  if (i==2) column.setPreferredWidth(100);
			  if (i==3) column.setPreferredWidth(100);
			  if (i==4) column.setPreferredWidth(100);
			  if (i==5) column.setPreferredWidth(100);
			  if (i==6) column.setPreferredWidth(130);
			}
		    
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
					  get_userdisp(company,textField.getText(),sts);
				  }
				});
		    textField.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		try {
		    			if(model.getRowCount()==0)
			    		{
			    			new create_new_cust(company, version,sts,username,store_name,posreg, con,page_type,textField.getText());
			    		}
		    			else
		    			{
		    				get_userdisp(company,textField.getText(),sts);
		    			}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    });
		    textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e1) {
					int keycode=e1.getKeyCode();
					if(keycode==40)
					{
						table.requestFocus();
					}
					if(e1.getKeyCode() == KeyEvent.VK_ENTER)
	                {
						
	                }
				}
			});
		    textField.setBounds(10, 54, 764, 22);
		    panel.add(textField);
		    textField.setColumns(10);
		    
		    get_userdisp( company, "",sts);
		    clickOnback( mntmNewMenuItem, "play", KeyEvent.VK_ESCAPE );
		    
		    JButton btnCreateNew = new JButton("Create New");
		    btnCreateNew.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		try {
						new create_new_cust(company, version,sts,username,store_name,posreg, con,page_type,textField.getText());
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    });
		    btnCreateNew.setBounds(672, 78, 102, 23);
		    panel.add(btnCreateNew);
			textField.requestFocus();
	        
			table.addKeyListener(new KeyAdapter() {
		    	@Override
		    	public void keyPressed(KeyEvent e1) {
		    		if (e1.getKeyChar() == KeyEvent.VK_ENTER) {
		    			int rows = table.getSelectedRow();
		    			print( company,username,store_name,posreg,version,con,rows, page_type);
		    		}
		    	}
		    });
			
	        table.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent mouseEvent) {
	                JTable table =(JTable) mouseEvent.getSource();
	                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
	                	int rows = table.getSelectedRow();
	                	print( company,username,store_name,posreg,version,con,rows,page_type);
	                }
	            }
	        });
	}
	public static void print( String company,String username,String store_name,String posreg,String version,Connection con,int rows, String page_type)
	{
		frame.setVisible(false);
		if(Mainpage.SoftLang.equals("Ar"))
		{
			sales_master.PosscreenArabic.btnNewButton.setText(table.getModel().getValueAt(rows, 1).toString());
			sales_master.PosscreenArabic.textField_6.setText(table.getModel().getValueAt(rows,4).toString());
			if(PosscreenArabic.CustDisc.equals("1"))
			{
				sales_master.PosscreenArabic.textField_7.setText(table.getModel().getValueAt(rows,5).toString());
				sales_master.PosscreenArabic.lblNewLabel_2.setText(table.getModel().getValueAt(rows,5).toString()+"%");
			}
			sales_master.PosscreenArabic.textField_12.setText(table.getModel().getValueAt(rows,2).toString());
			sales_master.PosscreenArabic.textField_9.setText(table.getModel().getValueAt(rows,0).toString());
			if(PosscreenArabic.table.getRowCount()!=0)
			{
				PosscreenArabic.disc_perc(company, username,store_name,PosscreenArabic.reg_nme, table.getModel().getValueAt(rows,5).toString(),"CUST");	
			}
			
			
			if(page_type.equals("hold_select"))
			{
				PosscreenArabic.page_type="Kitchen KOT";
				PosscreenArabic.hold_inv=true;
				PosscreenArabic.hold_inv(company, username,store_name, posreg, con,"HOME DELIVERY",version,"","HOME DELIVERY",PosscreenArabic.textField_8.getText());
				
			}
			
			PosscreenArabic.btnNewButton.setEnabled(true);
			PosscreenArabic.textField_9.setEnabled(true);
		}
		else
		{
			sales_master.posscreen.btnNewButton.setText(table.getModel().getValueAt(rows, 1).toString());
			sales_master.posscreen.textField_6.setText(table.getModel().getValueAt(rows,4).toString());
			if(posscreen.CustDisc.equals("1"))
			{
				sales_master.posscreen.textField_7.setText(table.getModel().getValueAt(rows,5).toString());
				sales_master.posscreen.lblNewLabel_2.setText(table.getModel().getValueAt(rows,5).toString()+"%");
			}
			sales_master.posscreen.textField_12.setText(table.getModel().getValueAt(rows,2).toString());
			sales_master.posscreen.textField_9.setText(table.getModel().getValueAt(rows,0).toString());
			if(posscreen.table.getRowCount()!=0)
			{
				posscreen.disc_perc(company, username,store_name,posscreen.reg_nme, table.getModel().getValueAt(rows,5).toString(),"CUST");	
			}
			
			
			if(page_type.equals("hold_select"))
			{
				posscreen.page_type="Kitchen KOT";
				posscreen.hold_inv=true;
				posscreen.hold_inv(company, username,store_name, posreg, con,"HOME DELIVERY",version,"","HOME DELIVERY",posscreen.textField_8.getText(),"0");
				
			}
			
	    	posscreen.btnNewButton.setEnabled(true);
	    	posscreen.textField_9.setEnabled(true);
		}
		
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
	private void  get_userdisp(String company, String serch, String sts)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement(); 
			String serch_term="";
			if(!serch.equals(""))
			{
				serch_term="and (`cust_name` LIKE '%"+serch+"%' OR `cust_code` LIKE '"+serch+"%' OR `ph1` LIKE '"+serch+"%')";
			}
			String sts_term="";
			if(!sts.equals(""))
			{
				sts_term="where `status`=1 and visible=1";
			}
			else
			{
				sts_term="where visible=1  ";
			}
			String query="select * from "+company+".mr_customer_master "+sts_term+" "+serch_term+" order by cust_name asc ";
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			double amount_payable=0;
			while(resultSet.next())
			{
				String id=resultSet.getString("cust_code");
	            String name=resultSet.getString("cust_name");
	            String ph1=resultSet.getString("ph1");
	            String ph2=resultSet.getString("ph2");
	           String crdit_limit=resultSet.getString("credit_limit");
	           String last_sale_date=resultSet.getString("last_sale_date");
	           double bal= Float.parseFloat(resultSet.getString("avail_credit"))+Float.parseFloat(resultSet.getString("advance_amt"));
	            String rating=resultSet.getString("disc");
	            double balance=Double.parseDouble(crdit_limit)-bal;
	            int decimalPlaces = 2;
        		BigDecimal bd = new BigDecimal(bal);
        		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		bal = bd.doubleValue();
        		BigDecimal bc = new BigDecimal(balance);
        		bc = bc.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		balance = bc.doubleValue();
	            model.addRow(new Object[]{id, name,ph1,ph2,bal,rating,last_sale_date});
	            amount_payable+=balance;
			}
			BigDecimal bc = new BigDecimal(amount_payable);
    		bc = bc.setScale(2, BigDecimal.ROUND_HALF_UP);
    		amount_payable = bc.doubleValue();
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
