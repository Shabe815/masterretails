package sales_master;

import inventory_master.Item_history;

import java.awt.Color;
import java.awt.Cursor;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Properties;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONException;

import CafePro.Mainpage;
import History_Reports.CustomerPaymentChange;
import History_Reports.HistoryInvoice_View;

import javax.swing.JTextField;

public class Cust_Chooser {

	private static JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private JTextField textField;
	public static String Active="0";
	private JTextField textField_1;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Cust_Chooser window = new Cust_Chooser("mr_pro","","","","","", null,"");
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
	 * @throws JSONException 
	 */
	public Cust_Chooser(String company, String page_type, String username, String store_name, String total, String old_recipt,String version, String sts) throws JSONException {
		initialize(company,page_type,username,store_name,total, old_recipt, version,sts);
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
	private void initialize(final String company, final String page_type, final String username,final String store_name,final String total,final String old_recipt,final String version, final String sts) throws JSONException {
		frame = new JFrame(version);
		frame.setResizable(false);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 551, 444);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(Active.equals("1"))
				{
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.txtDisc.requestFocus();
						Active="0";
						PosscreenArabic.btnNewButton.setEnabled(true);
					}
					else
					{
						posscreen.txtDisc.requestFocus();
						Active="0";
						posscreen.btnNewButton.setEnabled(true);
					}
					
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
		panel.setBounds(0, 0, 534, 363);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblShifts = new JLabel("Customers");
		lblShifts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShifts.setBounds(10, 11, 228, 44);
		panel.add(lblShifts);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				if(Active.equals("1"))
				{
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.txtDisc.requestFocus();
						Active="0";
						PosscreenArabic.btnNewButton.setEnabled(true);
					}
					else
					{
						posscreen.txtDisc.requestFocus();
						Active="0";
						posscreen.btnNewButton.setEnabled(true);
					}
				}
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(463, 0, 71, 48);
		panel.add(mntmNewMenuItem);
		
		final Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JMenuItem menuItem_3 = new JMenuItem("Print");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					frame.setAlwaysOnTop(false);
					report_master.customer_list.report(con,company,username,"");
					//frame.setAlwaysOnTop(true);
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		menuItem_3.setBorder(null);
		menuItem_3.setIcon(new ImageIcon(printer));
		menuItem_3.setBackground(new Color(243, 229, 216));
		menuItem_3.setBounds(371, 6, 80, 30);
		panel.add(menuItem_3);
		
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
		checkBox.setBounds(20, 78, 145, 23);
		panel.add(checkBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 108, 534, 255);
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
			table.setRowHeight(20);
		    model.addColumn("Cust Code"); 
		    model.addColumn("Cust Name"); 
			model.addColumn("Cust Type"); 
			model.addColumn("Phone1"); 
			model.addColumn("Credit Limit"); 
			model.addColumn("Credit Balance"); 
			model.addColumn("Balance Pay"); 
			model.addColumn("Discount"); 
			model.addColumn("Address1"); 
			model.addColumn("Address2"); 
			model.addColumn("Last Sale Date"); 
		    scrollPane.setViewportView(table);
		    
		    
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        int colmns=model.getColumnCount();
			for (int i=0; i<colmns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(100);
			  if (i==1) column.setPreferredWidth(200);
			  if (i==2) column.setPreferredWidth(70);
			  if (i==3) column.setPreferredWidth(100);
			  if (i==4) column.setPreferredWidth(70);
			  if (i==5) column.setPreferredWidth(70);
			  if (i==6) column.setPreferredWidth(70);
			  if (i==7) column.setPreferredWidth(70);
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
					  get_userdisp(company,textField.getText(),sts,version,username,store_name,Mainpage.pos_register);
				  }
				});
		    textField.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		get_userdisp(company,textField.getText(),sts,version,username,store_name,Mainpage.pos_register);
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
				}
			});
		    textField.setBounds(10, 54, 242, 22);
		    panel.add(textField);
		    textField.setColumns(10);
		    
		    Image serch = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
			JButton btnNewButton = new JButton("");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					get_userdisp(company,textField.getText(),sts,version,username,store_name,Mainpage.pos_register);
				}
			});
			btnNewButton.setIcon(new ImageIcon(serch));
		    btnNewButton.setBounds(258, 54, 40, 24);
		    panel.add(btnNewButton);
		    
		    JButton btnExportToCsv = new JButton("Export to CSV");
		    btnExportToCsv.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		try {
		    			
		    			LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyy");  
					    String formattedDate = myDateObj.format(myFormatObj); 
		    			
		    			Properties prop=new Properties();
		    			FileInputStream ip= new FileInputStream("C:/MasterRetails/config.properties");
		    			prop.load(ip);
		    			String encoding= prop.getProperty("encoding");
		    			ip.close();
		    			
		    			
		    			HSSFWorkbook wb = new HSSFWorkbook();
						HSSFSheet sheet = wb.createSheet("Excel Sheet");
						HSSFRow rowhead = sheet.createRow((short)0);
						rowhead.createCell((short) 0).setCellValue("Cust Code");
						rowhead.createCell((short) 1).setCellValue("Cust Name");
						rowhead.createCell((short) 2).setCellValue("Cust Type");
						rowhead.createCell((short) 3).setCellValue("Phone1");
						rowhead.createCell((short) 4).setCellValue("Credit Limit");
						rowhead.createCell((short) 5).setCellValue("Credit Balance");
						rowhead.createCell((short) 6).setCellValue("Balance Pay");
						rowhead.createCell((short) 7).setCellValue("Discount");
						rowhead.createCell((short) 8).setCellValue("Address1");
						rowhead.createCell((short) 9).setCellValue("Address2");
						rowhead.createCell((short) 10).setCellValue("last_sale_date");
		    			
		    			
						Writer excel;
						if(encoding.equals("ANSI"))
						{
							Charset ansiCharset = Charset.forName("windows-1256");
							excel =new OutputStreamWriter(new FileOutputStream("C:\\MasterRetails\\Export\\Customer-"+formattedDate+".csv"),ansiCharset);
						}
						else
						{
							excel =new OutputStreamWriter(new FileOutputStream("C:\\MasterRetails\\Export\\Customer-"+formattedDate+".csv"),StandardCharsets.UTF_8);
						}
						
						
						excel.write("Cust Code,Cust Name,Cust Type,Phone1,Credit Limit,Credit Balance,Balance Pay,Discount,Address1,Address2,last_sale_date\r\n");
						
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
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
//		    			LocalDateTime myDateObj = LocalDateTime.now();  
//					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyy");  
//					    String formattedDate = myDateObj.format(myFormatObj); 
//						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//			    		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
//			    		File file = new File("C:\\MasterRetails\\Export\\Customer-"+formattedDate+".csv");
//			    		BufferedWriter excel = Files.newBufferedWriter(
//			    			    file.toPath(),
//			    			    StandardCharsets.UTF_8
//			    			);
//			    		
//			    		for (int x = 0; x < model.getColumnCount(); x++) {
//			    		    excel.write(model.getColumnName(x));
//			    		    if (x < model.getColumnCount() - 1)
//			    		        excel.write(",");
//			    		}
//			    		excel.write("\n");
//
//			    		for (int x = 0; x < model.getRowCount(); x++) {
//			    		    for (int y = 0; y < model.getColumnCount(); y++) {
//			    		        Object value = model.getValueAt(x, y);
//			    		        excel.write(value != null ? value.toString() : "");
//			    		        if (y < model.getColumnCount() - 1)
//			    		            excel.write(",");
//			    		        else
//			    		            excel.write("\r\n");
//			    		    }
//			    		}
//		    	        excel.close();
//		    	        frame.setCursor(Cursor.getDefaultCursor());
//		    	        JOptionPane.showMessageDialog(frame, "Export Completed");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		    			
		    	}
		    });
		    btnExportToCsv.setBounds(220, 11, 129, 25);
		    panel.add(btnExportToCsv);
		    
		    JLabel lblTotalPayableAmount = new JLabel("Total Receivable Amount : ");
		    lblTotalPayableAmount.setBounds(231, 368, 174, 16);
		    frame.getContentPane().add(lblTotalPayableAmount);
		    
		    textField_1 = new JTextField();
		    textField_1.setEditable(false);
		    textField_1.setBounds(405, 365, 116, 22);
		    frame.getContentPane().add(textField_1);
		    textField_1.setColumns(10);
		    
		    get_userdisp(company,textField.getText(),sts,version,username,store_name,Mainpage.pos_register);
		    clickOnback( mntmNewMenuItem, "play", KeyEvent.VK_ESCAPE );
			textField.requestFocus();
	        
			table.addKeyListener(new KeyAdapter() {
		    	@Override
		    	public void keyPressed(KeyEvent e1) {
		    		if (e1.getKeyChar() == KeyEvent.VK_ENTER) {
		    			Connection con=Dbsetup.dbconn.getConnection();
		    			get_codes( con, company,  page_type, store_name, total,  username,  old_recipt);
		    		}
		    	}
		    });
			
	        table.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent mouseEvent) {
	                JTable table =(JTable) mouseEvent.getSource();
	                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
	                	Connection con=Dbsetup.dbconn.getConnection();
		    			get_codes( con, company,  page_type, store_name, total,  username,  old_recipt);
	                }
	            }
	        });
	}
	//
	private void get_codes(Connection con, String company, String page_type, String store_name, String total, String username, String old_recipt)
	{
		int column = 0;
    	int rows = table.getSelectedRow();
    	String value = table.getModel().getValueAt(rows, column).toString();
    	if(page_type.equals("pos"))
    	{
    		if(Mainpage.SoftLang.equals("Ar"))
			{
    			if(Double.parseDouble(PosscreenArabic.textField_3.getText())==0)
        		{
        			JOptionPane.showMessageDialog(frame, "No Amount To Settle");
        			frame.setVisible(false);
        		}
        		else
        		{
        			if(table.getModel().getValueAt(rows,5).toString().equals("0"))
        			{
        				JOptionPane.showMessageDialog(frame, "Credit Balance is Empty, Please Use Another Paycodes");
        			}
            		else if(Double.parseDouble(total)>Double.parseDouble(table.getModel().getValueAt(rows, 5).toString()))
            		{
            			JOptionPane.showMessageDialog(frame, "Do Not Have Balance To Purchase This Invoice");
            		}
        			else
        			{
        				String type="A/R";
    					String cash="0";
    					String reg_num=sales_master.PosscreenArabic.current_register;
    					String user=username;
    					String receipt_no=sales_master.PosscreenArabic.textField_8.getText();
    					String vat=sales_master.PosscreenArabic.textField_1.getText();
    					String disc=sales_master.PosscreenArabic.textField_2.getText();
    					String qty=sales_master.PosscreenArabic.textField_4.getText();
    					LocalDateTime myDateObj = LocalDateTime.now();  
    				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    				    String formattedDate = myDateObj.format(myFormatObj);
    				    Calendar cal = Calendar.getInstance();
    			        SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
    					Statement statement;
    					try {
    						statement = con.createStatement();
    						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ");
    						if(!resultSet.next())
    						{
    							String cust_dt="select * from "+company+".mr_customer_master where cust_name='"+table.getModel().getValueAt(rows, 1).toString()+"' ";
    							ResultSet resultSet11 = statement.executeQuery(cust_dt);
    							if(resultSet11.next())
    							{
    								float bal_amt=0;
    								double avail_crdt=0;
    								float adv_amt=0;
    								if(!resultSet11.getString("advance_amt").equals("0"))
    								{
    									bal_amt=Float.parseFloat(resultSet11.getString("advance_amt"))-Float.parseFloat(total);
    									if(bal_amt>=0)
    									{
    										adv_amt=bal_amt;
    										avail_crdt=Float.parseFloat(resultSet11.getString("avail_credit"));
    									}
    									else{
    										avail_crdt=bal_amt+Float.parseFloat(resultSet11.getString("avail_credit"));
    									}
    								}
    								else{
    									avail_crdt=Float.parseFloat(resultSet11.getString("avail_credit"))-Float.parseFloat(total);
    								}
    								BigDecimal bd = new BigDecimal(avail_crdt);
    			            		bd = bd.setScale(PosscreenArabic.decimalPlaces, BigDecimal.ROUND_HALF_UP);
    			            		avail_crdt = bd.doubleValue();
    								String update_customer_master="update "+company+".mr_customer_master set avail_credit='"+avail_crdt+"',advance_amt='"+adv_amt+"',last_sale_date='"+formattedDate+"' where cust_name='"+table.getModel().getValueAt(rows, 1).toString()+"' ";
    								statement.executeUpdate(update_customer_master);
    								
    								String table_num=PosscreenArabic.label.getText();
    								String delivery_user=PosscreenArabic.label_1.getText();
    								String mode=PosscreenArabic.label_2.getText();
    								if(PosscreenArabic.change_hold_type.equals("1"))
    								{
    									if(table_num.equals(""))
    									{
    										table_num="CREDIT SALE";
    									}
    									
    									if(delivery_user.equals(""))
    									{
    										delivery_user="CREDIT SALE";
    									}
    									
    									if(mode.equals(""))
    									{
    										mode="CREDIT SALE";
    									}
    								}
    								String query="insert into "+company+".mr_pos_settlement_cash (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, `cash_paid`,type,store_id, `created_date`,grand_total,cust_code,cust_prev_balance,table_num,delivery_user,mode,cust_ph) values('"+reg_num+"','"+receipt_no+"','"+user+"','"+times.format(cal.getTime())+"','"+PosscreenArabic.textField.getText()+"','"+table.getModel().getValueAt(rows, 1).toString()+"','"+vat+"','"+disc+"','"+qty+"','"+cash+"','"+type+"','"+store_name+"','"+formattedDate+"','"+PosscreenArabic.textField_3.getText()+"','"+table.getModel().getValueAt(rows, 0).toString()+"','"+table.getModel().getValueAt(rows, 5).toString()+"','"+table_num+"','"+delivery_user+"','"+mode+"','"+PosscreenArabic.textField_12.getText()+"') ";
    								statement.executeUpdate(query);
    								if(old_recipt.equals(""))
    								{
    									ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_num+"' ");
    									if(resultSet2.next())
    									{
    										int nxt_num=Integer.parseInt(resultSet2.getString("next_num"))+1;
    										String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+reg_num+"' ";
    										statement.executeUpdate(update_receipt_num);
    									}
    								}
    								con.setAutoCommit(false);
    								PreparedStatement ps = con.prepareStatement("insert into "+company+".mr_pos_settlement_cash_items ( `ref_receipt_num`,ref_reg_num, `barcode`, `item_desc`,item_code,item_cost, `unit`, `unit_price`, `qty`, `total`, `vat`,discount, `created_date`,sp, `vat_type`, `vat_perc`, `disc_type`,cf) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    								for(int i=0;i<PosscreenArabic.table.getRowCount();i++)
    								{
    									ps.setString(1,receipt_no);
    									ps.setString(2,reg_num);
    									ps.setString(3,PosscreenArabic.model.getValueAt(i, 1).toString().trim());
    									ps.setString(4,PosscreenArabic.model.getValueAt(i, 2).toString().trim().replaceAll("<html>", "").replaceAll("</html>", ""));
    									ps.setString(5,PosscreenArabic.model.getValueAt(i, 13).toString().trim());
    									ps.setString(6,PosscreenArabic.model.getValueAt(i, 11).toString().trim());
    									ps.setString(7,PosscreenArabic.model.getValueAt(i, 3).toString().trim());
    									ps.setString(8,PosscreenArabic.model.getValueAt(i,4).toString().trim());
    									ps.setString(9,PosscreenArabic.model.getValueAt(i, 5).toString().trim());
    									ps.setString(10,PosscreenArabic.model.getValueAt(i, 7).toString().trim());
    									ps.setString(11,PosscreenArabic.model.getValueAt(i, 8).toString().trim());
    									ps.setString(12,PosscreenArabic.model.getValueAt(i, 6).toString().trim());
    									ps.setString(13,formattedDate);
    									ps.setString(14,PosscreenArabic.model.getValueAt(i, 12).toString().trim());
    									ps.setString(15,PosscreenArabic.model.getValueAt(i, 10).toString().trim());
    									ps.setString(16,PosscreenArabic.model.getValueAt(i, 16).toString().trim());
    									ps.setString(17,PosscreenArabic.model.getValueAt(i, 15).toString().trim());
    									ps.setString(18,PosscreenArabic.model.getValueAt(i, 22).toString().trim());
    									ps.addBatch();
    									ps.clearParameters();
    								}
    								ps.executeBatch();
    								con.commit();
    								con.setAutoCommit(true);
    								
    								String query5="SELECT * FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ";
    								ResultSet resultSet5= statement.executeQuery(query5);
    								if(resultSet5.next())
    								{
    									String query6="delete FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ";
    									statement.executeUpdate(query6);
    									String query7="delete FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+receipt_no+"' and ref_reg_no='"+reg_num+"' ";
    									statement.executeUpdate(query7);
    								}
    								int copy=1;
    								if(PosscreenArabic.print_duplicate_invoice.equals("1"))
    								{
    									copy=2;
    								}
    								
    								if(PosscreenArabic.promt_print.equals("0"))
    								{
    									//byte[] open = {27,112,48,55,121};
    							        PrintService pservice = 
    							        PrintServiceLookup.lookupDefaultPrintService(); 
    							        DocPrintJob job = pservice.createPrintJob();
    							        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
    							        Doc doc = new SimpleDoc(PosscreenArabic.open,flavor,null);
    							        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
    							        try {
    							            job.print(doc, aset);
    										
    							        } catch (PrintException ex) {
    							            System.out.println(ex.getMessage());
    							        }
    									report_master.Bill_template.report(receipt_no, con, PosscreenArabic.printer,PosscreenArabic.current_register,"",company,copy);
    								}
    								else
    								{
    									int a=JOptionPane.showConfirmDialog(frame, "DO YOU WANT TO PRINT RECEIPT","",JOptionPane.YES_NO_OPTION);
    									if(a==0)
    									{
    										//byte[] open = {27,112,48,55,121};
    								        PrintService pservice = 
    								        PrintServiceLookup.lookupDefaultPrintService(); 
    								        DocPrintJob job = pservice.createPrintJob();
    								        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
    								        Doc doc = new SimpleDoc(PosscreenArabic.open,flavor,null);
    								        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
    								        try {
    								            job.print(doc, aset);
    											
    								        } catch (PrintException ex) {
    								            System.out.println(ex.getMessage());
    								        }
    										
    										report_master.Bill_template.report(receipt_no, con, PosscreenArabic.printer,PosscreenArabic.current_register,"",company,copy);
    									}
    								}
    								
    								if(PosscreenArabic.CashChange.equals("1"))
    								{
    									new sales_master.cash_change(company, username, store_name, total, cash, PosscreenArabic.decimalPlaces,"AR");
    								}
    								sales_master.PosscreenArabic.get_register_name(company,  username, store_name,con,reg_num);
    								sales_master.PosscreenArabic.txtDisc.setText("");
    								sales_master.PosscreenArabic.btnNewButton.setText("WALK IN");
    								sales_master.PosscreenArabic.textField_6.setText("");
    								sales_master.PosscreenArabic.textField_9.setText("1");
    								sales_master.PosscreenArabic.textField_12.setText("0");
    								sales_master.PosscreenArabic.textField_7.setText("0");
    								sales_master.PosscreenArabic.lblNewLabel_2.setText("0%");
    								sales_master.PosscreenArabic.textField_4.setText("0");
    								sales_master.PosscreenArabic.textField_2.setText("0");
    								sales_master.PosscreenArabic.textField.setText("0.00");
    								sales_master.PosscreenArabic.textField_1.setText("0.00");
    								sales_master.PosscreenArabic.textField_11.setText("0.00");
    								sales_master.PosscreenArabic.textField_3.setText("0.00");
    								sales_master.PosscreenArabic.textField_5.setText("");
    								sales_master.PosscreenArabic.textField_7.setText("0");
    								sales_master.PosscreenArabic.lblNewLabel_2.setText("0");
    								sales_master.PosscreenArabic.label.setText("");
    								sales_master.PosscreenArabic.label_1.setText("");
    								sales_master.PosscreenArabic.label_2.setText("");
    								sales_master.PosscreenArabic.lblNewLabel_1.setText("0");
    								if(PosscreenArabic.gs.length>1)
    								{
    								sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
    								sales_master.SecondDisplay.textField_7.setText("0");
    								sales_master.SecondDisplay.textField_4.setText("0");
    								sales_master.SecondDisplay.textField_2.setText("0");
    								sales_master.SecondDisplay.textField.setText("0.00");
    								sales_master.SecondDisplay.textField_1.setText("0.00");
    								sales_master.SecondDisplay.textField_11.setText("0.00");
    								sales_master.SecondDisplay.textField_3.setText("0.00");
    								sales_master.SecondDisplay.textField_5.setText("");
    								sales_master.SecondDisplay.textField_7.setText("0");
    								sales_master.SecondDisplay.model.setRowCount(0);
    								}
    								Active="0";
    								sales_master.PosscreenArabic.current_register=PosscreenArabic.reg_nme;
    							}	
    						}
    						else
    						{
    							JOptionPane.showMessageDialog(PosscreenArabic.frmRegisterNoS, "Receipt number Already Saved");
    						}
    					} catch (SQLException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					} catch (ClassNotFoundException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}  
        			}
        		}
			}
			else
			{
				if(Double.parseDouble(posscreen.textField_3.getText())==0)
	    		{
	    			JOptionPane.showMessageDialog(frame, "No Amount To Settle");
	    			frame.setVisible(false);
	    		}
	    		else
	    		{
	    			if(table.getModel().getValueAt(rows,5).toString().equals("0"))
	    			{
	    				JOptionPane.showMessageDialog(frame, "Credit Balance is Empty, Please Use Another Paycodes");
	    			}
	        		else if(Double.parseDouble(total)>Double.parseDouble(table.getModel().getValueAt(rows, 5).toString()))
	        		{
	        			JOptionPane.showMessageDialog(frame, "Do Not Have Balance To Purchase This Invoice");
	        		}
	    			else
	    			{
	    				String type="A/R";
						String cash="0";
						String reg_num=sales_master.posscreen.current_register;
						String user=username;
						String receipt_no=sales_master.posscreen.textField_8.getText();
						String vat=sales_master.posscreen.textField_1.getText();
						String disc=sales_master.posscreen.textField_2.getText();
						String qty=sales_master.posscreen.textField_4.getText();
						LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);
					    Calendar cal = Calendar.getInstance();
				        SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
						Statement statement;
						try {
							statement = con.createStatement();
							ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ");
							if(!resultSet.next())
							{
								String cust_dt="select * from "+company+".mr_customer_master where cust_name='"+table.getModel().getValueAt(rows, 1).toString()+"' ";
								ResultSet resultSet11 = statement.executeQuery(cust_dt);
								if(resultSet11.next())
								{
									float bal_amt=0;
									double avail_crdt=0;
									float adv_amt=0;
									if(!resultSet11.getString("advance_amt").equals("0"))
									{
										bal_amt=Float.parseFloat(resultSet11.getString("advance_amt"))-Float.parseFloat(total);
										if(bal_amt>=0)
										{
											adv_amt=bal_amt;
											avail_crdt=Float.parseFloat(resultSet11.getString("avail_credit"));
										}
										else{
											avail_crdt=bal_amt+Float.parseFloat(resultSet11.getString("avail_credit"));
										}
									}
									else{
										avail_crdt=Float.parseFloat(resultSet11.getString("avail_credit"))-Float.parseFloat(total);
									}
									BigDecimal bd = new BigDecimal(avail_crdt);
				            		bd = bd.setScale(posscreen.decimalPlaces, BigDecimal.ROUND_HALF_UP);
				            		avail_crdt = bd.doubleValue();
									String update_customer_master="update "+company+".mr_customer_master set avail_credit='"+avail_crdt+"',advance_amt='"+adv_amt+"',last_sale_date='"+formattedDate+"' where cust_name='"+table.getModel().getValueAt(rows, 1).toString()+"' ";
									statement.executeUpdate(update_customer_master);
									
									String table_num=posscreen.label.getText();
									String delivery_user=posscreen.label_1.getText();
									String mode=posscreen.label_2.getText();
									if(posscreen.change_hold_type.equals("1"))
									{
										if(table_num.equals(""))
										{
											table_num="CREDIT SALE";
										}
										
										if(delivery_user.equals(""))
										{
											delivery_user="CREDIT SALE";
										}
										
										if(mode.equals(""))
										{
											mode="CREDIT SALE";
										}
									}
									String query="insert into "+company+".mr_pos_settlement_cash (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, `cash_paid`,type,store_id, `created_date`,grand_total,cust_code,cust_prev_balance,table_num,delivery_user,mode,cust_ph) values('"+reg_num+"','"+receipt_no+"','"+user+"','"+times.format(cal.getTime())+"','"+posscreen.textField.getText()+"','"+table.getModel().getValueAt(rows, 1).toString()+"','"+vat+"','"+disc+"','"+qty+"','"+cash+"','"+type+"','"+store_name+"','"+formattedDate+"','"+posscreen.textField_3.getText()+"','"+table.getModel().getValueAt(rows, 0).toString()+"','"+table.getModel().getValueAt(rows, 5).toString()+"','"+table_num+"','"+delivery_user+"','"+mode+"','"+posscreen.textField_12.getText()+"') ";
									statement.executeUpdate(query);
									if(old_recipt.equals(""))
									{
										ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_num+"' ");
										if(resultSet2.next())
										{
											int nxt_num=Integer.parseInt(resultSet2.getString("next_num"))+1;
											String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+reg_num+"' ";
											statement.executeUpdate(update_receipt_num);
										}
									}
									con.setAutoCommit(false);
									PreparedStatement ps = con.prepareStatement("insert into "+company+".mr_pos_settlement_cash_items ( `ref_receipt_num`,ref_reg_num, `barcode`, `item_desc`,item_code,item_cost, `unit`, `unit_price`, `qty`, `total`, `vat`,discount, `created_date`,sp, `vat_type`, `vat_perc`, `disc_type`,cf) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
									for(int i=0;i<posscreen.table.getRowCount();i++)
									{
										ps.setString(1,receipt_no);
										ps.setString(2,reg_num);
										ps.setString(3,posscreen.model.getValueAt(i, 1).toString().trim());
										ps.setString(4,posscreen.model.getValueAt(i, 2).toString().trim().replaceAll("<html>", "").replaceAll("</html>", ""));
										ps.setString(5,posscreen.model.getValueAt(i, 13).toString().trim());
										ps.setString(6,posscreen.model.getValueAt(i, 11).toString().trim());
										ps.setString(7,posscreen.model.getValueAt(i, 3).toString().trim());
										ps.setString(8,posscreen.model.getValueAt(i,4).toString().trim());
										ps.setString(9,posscreen.model.getValueAt(i, 5).toString().trim());
										ps.setString(10,posscreen.model.getValueAt(i, 7).toString().trim());
										ps.setString(11,posscreen.model.getValueAt(i, 8).toString().trim());
										ps.setString(12,posscreen.model.getValueAt(i, 6).toString().trim());
										ps.setString(13,formattedDate);
										ps.setString(14,posscreen.model.getValueAt(i, 12).toString().trim());
										ps.setString(15,posscreen.model.getValueAt(i, 10).toString().trim());
										ps.setString(16,posscreen.model.getValueAt(i, 16).toString().trim());
										ps.setString(17,posscreen.model.getValueAt(i, 15).toString().trim());
										ps.setString(18,posscreen.model.getValueAt(i, 22).toString().trim());
										ps.addBatch();
										ps.clearParameters();
									}
									ps.executeBatch();
									con.commit();
									con.setAutoCommit(true);
									
									String query5="SELECT * FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ";
									ResultSet resultSet5= statement.executeQuery(query5);
									if(resultSet5.next())
									{
										String query6="delete FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ";
										statement.executeUpdate(query6);
										String query7="delete FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+receipt_no+"' and ref_reg_no='"+reg_num+"' ";
										statement.executeUpdate(query7);
									}
									int copy=1;
									if(posscreen.print_duplicate_invoice.equals("1"))
									{
										copy=2;
									}
									
									if(posscreen.promt_print.equals("0"))
									{
										//byte[] open = {27,112,48,55,121};
								        PrintService pservice = 
								        PrintServiceLookup.lookupDefaultPrintService(); 
								        DocPrintJob job = pservice.createPrintJob();
								        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
								        Doc doc = new SimpleDoc(posscreen.open,flavor,null);
								        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
								        try {
								            job.print(doc, aset);
											
								        } catch (PrintException ex) {
								            System.out.println(ex.getMessage());
								        }
										report_master.Bill_template.report(receipt_no, con, posscreen.printer,posscreen.current_register,"",company,copy);
									}
									else
									{
										int a=JOptionPane.showConfirmDialog(frame, "DO YOU WANT TO PRINT RECEIPT","",JOptionPane.YES_NO_OPTION);
										if(a==0)
										{
											//byte[] open = {27,112,48,55,121};
									        PrintService pservice = 
									        PrintServiceLookup.lookupDefaultPrintService(); 
									        DocPrintJob job = pservice.createPrintJob();
									        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
									        Doc doc = new SimpleDoc(posscreen.open,flavor,null);
									        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
									        try {
									            job.print(doc, aset);
												
									        } catch (PrintException ex) {
									            System.out.println(ex.getMessage());
									        }
											
											report_master.Bill_template.report(receipt_no, con, posscreen.printer,posscreen.current_register,"",company,copy);
										}
									}
									
									if(posscreen.CashChange.equals("1"))
									{
										new sales_master.cash_change(company, username, store_name, total, cash, posscreen.decimalPlaces,"AR");
									}
									sales_master.posscreen.get_register_name(company,  username, store_name,con,reg_num);
									sales_master.posscreen.txtDisc.setText("");
									sales_master.posscreen.btnNewButton.setText("WALK IN");
									sales_master.posscreen.textField_6.setText("");
									sales_master.posscreen.textField_9.setText("1");
									sales_master.posscreen.textField_12.setText("0");
									sales_master.posscreen.textField_7.setText("0");
									sales_master.posscreen.lblNewLabel_2.setText("0%");
									sales_master.posscreen.textField_4.setText("0");
									sales_master.posscreen.textField_2.setText("0");
									sales_master.posscreen.textField.setText("0.00");
									sales_master.posscreen.textField_1.setText("0.00");
									sales_master.posscreen.textField_11.setText("0.00");
									sales_master.posscreen.textField_3.setText("0.00");
									sales_master.posscreen.textField_5.setText("");
									sales_master.posscreen.textField_7.setText("0");
									sales_master.posscreen.lblNewLabel_2.setText("0");
									sales_master.posscreen.label.setText("");
									sales_master.posscreen.label_1.setText("");
									sales_master.posscreen.label_2.setText("");
									sales_master.posscreen.lblNewLabel_1.setText("0");
									if(posscreen.gs.length>1)
									{
									sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
									sales_master.SecondDisplay.textField_7.setText("0");
									sales_master.SecondDisplay.textField_4.setText("0");
									sales_master.SecondDisplay.textField_2.setText("0");
									sales_master.SecondDisplay.textField.setText("0.00");
									sales_master.SecondDisplay.textField_1.setText("0.00");
									sales_master.SecondDisplay.textField_11.setText("0.00");
									sales_master.SecondDisplay.textField_3.setText("0.00");
									sales_master.SecondDisplay.textField_5.setText("");
									sales_master.SecondDisplay.textField_7.setText("0");
									sales_master.SecondDisplay.model.setRowCount(0);
									}
									Active="0";
									sales_master.posscreen.current_register=posscreen.reg_nme;
								}	
							}
							else
							{
								JOptionPane.showMessageDialog(posscreen.frmRegisterNoS, "Receipt number Already Saved");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
	    			}
	    		}
			}
    		
    	}
    	else if(page_type.equals("pos1"))
    	{
    		if(Mainpage.SoftLang.equals("Ar"))
			{
    			sales_master.PosscreenArabic.btnNewButton.setText(table.getModel().getValueAt(rows, 1).toString());
        		if(PosscreenArabic.gs.length>1)
    			{
        		sales_master.SecondDisplay.btnNewButton.setText(table.getModel().getValueAt(rows, 1).toString());
    			}
    			sales_master.PosscreenArabic.textField_6.setText(table.getModel().getValueAt(rows,5).toString());
    			if(PosscreenArabic.CustDisc.equals("1"))
    			{
    				sales_master.PosscreenArabic.textField_7.setText(table.getModel().getValueAt(rows,7).toString());
    				sales_master.PosscreenArabic.lblNewLabel_2.setText(table.getModel().getValueAt(rows,7).toString()+"%");
    			}
    			
    			sales_master.PosscreenArabic.textField_9.setText(table.getModel().getValueAt(rows,0).toString());
    			sales_master.PosscreenArabic.textField_12.setText(table.getModel().getValueAt(rows,3).toString());
    			
    			if(PosscreenArabic.table.getRowCount()!=0)
    			{
    				PosscreenArabic.disc_perc(company, username,store_name,PosscreenArabic.reg_nme, table.getModel().getValueAt(rows,7).toString(),"CUST");	
    			}
    			PosscreenArabic.btnNewButton.setEnabled(true);
			}
			else
			{
				sales_master.posscreen.btnNewButton.setText(table.getModel().getValueAt(rows, 1).toString());
	    		if(posscreen.gs.length>1)
				{
	    		sales_master.SecondDisplay.btnNewButton.setText(table.getModel().getValueAt(rows, 1).toString());
				}
				sales_master.posscreen.textField_6.setText(table.getModel().getValueAt(rows,5).toString());
				if(posscreen.CustDisc.equals("1"))
				{
					sales_master.posscreen.textField_7.setText(table.getModel().getValueAt(rows,7).toString());
					sales_master.posscreen.lblNewLabel_2.setText(table.getModel().getValueAt(rows,7).toString()+"%");
				}
				
				sales_master.posscreen.textField_9.setText(table.getModel().getValueAt(rows,0).toString());
				sales_master.posscreen.textField_12.setText(table.getModel().getValueAt(rows,3).toString());
				
				if(posscreen.table.getRowCount()!=0)
				{
					posscreen.disc_perc(company, username,store_name,posscreen.reg_nme, table.getModel().getValueAt(rows,7).toString(),"CUST");	
				}
		    	posscreen.btnNewButton.setEnabled(true);
			}
    		
			Active="0";
    	}
    	else if(page_type.equals("s&d"))
    	{
    		sales_distribution.sd_lines.textField_6.setText(table.getModel().getValueAt(rows,0).toString());
    	}
    	else if(page_type.equals("s&dpost"))
    	{
    		sales_distribution.sd_post_list.textField_3.setText(table.getModel().getValueAt(rows,0).toString());
    		sales_distribution.sd_post_list.textField_2.setText(table.getModel().getValueAt(rows,1).toString());
    	}
    	else if(page_type.equals("s&dQupost"))
    	{
    		sales_distribution.sd_quotation_posted_list.textField_3.setText(table.getModel().getValueAt(rows,0).toString());
    		sales_distribution.sd_quotation_posted_list.textField_2.setText(table.getModel().getValueAt(rows,1).toString());
    	}
    	else if(page_type.equals("invoice_summary"))
    	{
    		History_Reports.historical_order_summary.textField.setText(table.getModel().getValueAt(rows, 1).toString());
    	}
    	else if(page_type.equals("invoice_hist"))
    	{
    		History_Reports.Invoice_history.textField.setText(table.getModel().getValueAt(rows, 1).toString());
    	}
    	else if(page_type.equals("cust_payment_hist"))
    	{
    		History_Reports.cust_payment_history.textField.setText(table.getModel().getValueAt(rows, 1).toString());
    	}
    	else if(page_type.equals("HistoryPaycode"))
    	{
    		HistoryInvoice_View.lblCust.setText(table.getModel().getValueAt(rows, 0).toString());
    		HistoryInvoice_View.lblcustname.setText(table.getModel().getValueAt(rows, 1).toString());
    	}
    	else if(page_type.equals("CustomerPaymentHistory"))
    	{
    		CustomerPaymentChange.textField.setText(table.getModel().getValueAt(rows, 0).toString());
    		CustomerPaymentChange.textField_1.setText(table.getModel().getValueAt(rows, 1).toString());
    	}
    	else if(page_type.equals("cust_pay"))
    	{
    		sales_master.customer_payment.textField.setText(table.getModel().getValueAt(rows, 1).toString());
			sales_master.customer_payment.textField_2.setText(table.getModel().getValueAt(rows,5).toString());
			sales_master.customer_payment.textField_5.setText(table.getModel().getValueAt(rows,7).toString());
			double bal=Double.parseDouble(table.getModel().getValueAt(rows,4).toString())-Double.parseDouble(table.getModel().getValueAt(rows,5).toString());
			sales_master.customer_payment.textField_1.setText(Double.toString(bal));
			sales_master.customer_payment.textField_4.setText(table.getModel().getValueAt(rows, 0).toString());
			//sales_master.customer_payment.textField_6.setText(resultSet.getString("advance_amt"));
			//sales_master.customer_payment.get_userdisp(company,table.getModel().getValueAt(rows, 0).toString());
			
    	}
    	else if(page_type.equals("cust_payable"))
    	{
    		sales_master.cust_account_payable.textField_1.setText(table.getModel().getValueAt(rows, 1).toString());
			//sales_master.customer_payment.textField_2.setText(table.getModel().getValueAt(rows,5).toString());
			//sales_master.customer_payment.textField_5.setText(table.getModel().getValueAt(rows,7).toString());
			double bal=Double.parseDouble(table.getModel().getValueAt(rows,4).toString())-Double.parseDouble(table.getModel().getValueAt(rows,5).toString());
			sales_master.cust_account_payable.textField_2.setText(Double.toString(bal));
			sales_master.cust_account_payable.textField.setText(table.getModel().getValueAt(rows, 0).toString());
			//sales_master.customer_payment.textField_6.setText(resultSet.getString("advance_amt"));
			sales_master.cust_account_payable.get_po_term_disp(company);
			
    	}
    	else if(page_type.equals("cust_state"))
    	{
    		sales_master.customer_statement.textField.setText(table.getModel().getValueAt(rows, 1).toString());
			sales_master.customer_statement.textField_3.setText(table.getModel().getValueAt(rows,5).toString());
			sales_master.customer_statement.textField_2.setText(table.getModel().getValueAt(rows,7).toString());
			double avail_adv=Double.parseDouble(table.getModel().getValueAt(rows,5).toString());
			double credit_bal=Double.parseDouble(table.getModel().getValueAt(rows,4).toString());
			double bal=0;
			if(avail_adv>=credit_bal)
			{
				double adv=avail_adv-credit_bal;
				bal=(credit_bal+adv)-(avail_adv);
			}
			else
			{
				bal=credit_bal-avail_adv;
			}
			BigDecimal bd = new BigDecimal(bal);
    		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
    		bal = bd.doubleValue();
			sales_master.customer_statement.textField_1.setText(Double.toString(bal));
			sales_master.customer_statement.textField_4.setText(table.getModel().getValueAt(rows, 0).toString());
			sales_master.customer_statement.get_cust_invo_list( company, table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type.equals("cust_pending"))
    	{
    		sales_distribution.sd_pending_list.textField.setText(table.getModel().getValueAt(rows, 0).toString());
    		sales_distribution.sd_pending_list.textField_1.setText(table.getModel().getValueAt(rows, 1).toString());
    		frame.setAlwaysOnTop(false);
    		sales_distribution.sd_pending_list.get_po_disp(company,table.getModel().getValueAt(rows, 0).toString(),table.getModel().getValueAt(rows, 1).toString(),"");
    		frame.setAlwaysOnTop(true);
    	}
    	else if(page_type.equals("store_credit"))
    	{
    		sales_master.Store_credit_issue.textField_13.setText(table.getModel().getValueAt(rows, 0).toString());
    		sales_master.Store_credit_issue.textField_7.setText(table.getModel().getValueAt(rows, 1).toString());
    	}
    	else if(page_type.equals("sales_summary"))
    	{
    		History_Reports.sales_summary.textField.setText(table.getModel().getValueAt(rows, 1).toString());
    		History_Reports.sales_summary.textField_5.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type.equals("item_analysis"))
    	{
    		History_Reports.Customer_Sale_Analysis.textField_2.setText(table.getModel().getValueAt(rows, 1).toString());
    		History_Reports.Customer_Sale_Analysis.textField.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	
    	else if(page_type.equals("item_history"))
    	{
    		Item_history.textField.setText(table.getModel().getValueAt(rows, 0).toString());
    		Item_history.textField_1.setText(table.getModel().getValueAt(rows, 1).toString());
    		Item_history.frame.setAlwaysOnTop(false);
    	}
    	else if(page_type.equals("cust_chooser"))
    	{
    		RestaurantPOS.lblCustCode=table.getModel().getValueAt(rows, 0).toString();
    		RestaurantPOS.lblCustName.setText(table.getModel().getValueAt(rows, 1).toString());
    		RestaurantPOS.lblCustPhone.setText(table.getModel().getValueAt(rows, 3).toString());
    		RestaurantPOS.lblCustBalance.setText(table.getModel().getValueAt(rows, 6).toString());
    		
    	}
    	else
    	{
    		sales_master.Customer_master.cust_disp( company, value);	
    	}
    	frame.setVisible(false);

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
	private void  get_userdisp(String company, String serch, String sts, String version, String username, String store_name, String pos_register)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement(); 
			String serch_term="";
			if(!serch.equals(""))
			{
				serch_term="and (`cust_name` LIKE '"+serch+"%' OR `cust_code` LIKE '"+serch+"%')";
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
			String query="select * from "+company+".mr_customer_master "+sts_term+" "+serch_term+" ORDER BY CASE WHEN cust_code REGEXP '^[0-9]+$' THEN 0 ELSE 1 END, CASE WHEN cust_code REGEXP '^[0-9]+$' THEN CAST(cust_code AS UNSIGNED) END, cust_code; ";
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
	            String type=resultSet.getString("type");
	            if(type==null)
	            {
	            	type="NULL";
	            }
	            String ph=resultSet.getString("ph1");
	           String crdit_limit=resultSet.getString("credit_limit");
	           String address1=resultSet.getString("addr1");
	           String address2=resultSet.getString("addr2");
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
	            model.addRow(new Object[]{id, name,type,ph,crdit_limit,bal,balance,rating,address1,address2,last_sale_date});
	            amount_payable+=balance;
			}
			BigDecimal bc = new BigDecimal(amount_payable);
    		bc = bc.setScale(2, BigDecimal.ROUND_HALF_UP);
    		amount_payable = bc.doubleValue();
			textField_1.setText(Double.toString(amount_payable));
//			if(resultSet.next())
//			{
//				
//			}
//			else
//			{
//				int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Customer Not Found, Do You Want To Create A New Customer","WARNING",JOptionPane.YES_NO_OPTION);
//		        if(cofrm_bx==0)
//		        {
//		        	new create_new_cust(company, version,sts,username,store_name,pos_register, con, "cust_chooser");
//		        }
//		        else
//		        {
//		        	textField.setText("");
//		        	get_userdisp(company,textField.getText(),sts,version,username,store_name,Mainpage.pos_register);
//		        }
//			}
			
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
