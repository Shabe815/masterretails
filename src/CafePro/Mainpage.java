package CafePro;

import inventory_master.Inv_Trnsfer;
import inventory_master.Stocktaking;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import javax.swing.SwingConstants;

import org.json.JSONException;

import sales_master.cust_display_buttn;
import accounts_master.Acc_ledger;
import accounts_master.JV_header;
import accounts_master.ap_voucher_list;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Mainpage {

	public static JFrame frame;
	private String posuser;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private String postype="0";
	public static String userauth;
	private String user_see_report="0";
	public static String see_cost="0";
	public static String see_qty="0";
	public static JButton btnNewButton;
	public static JButton btnVendorMaster;
	public static JButton btnPurchaseReturn;
	public static JButton btnNewButton_1 ;
	public static JButton btnSales;
	public static JButton btnXReports ;
	public static JButton btnCloseSales;
	public static JButton btnPosControl;
	public static JButton btnHistoricalReports;
	public static JButton btnStockCount;
	public static String Usercancloseregister="0";
	public static JButton btnJournalVoucher;
	public static JButton btnReports;
	public static JButton btnAccountsControl;
	
	public static JButton btnPurchaseOrder;
	//private String printer;
	private static Connection con=Dbsetup.dbconn.getConnection();
	private static Connection con1=Dbsetup.local_server.getConnection();
	public static JLabel lbloffline;
	public static String display_port;
	public static String ready_msg1;
	public static String ready_msg2;
	public static String pos_register;
	public static String printer;
	public static String SoftLang;
	
	public static String customerpaymenthist;
	public static String posinvoicehistory;
	
	private JButton btnCashVoucher;
	
	private DefaultTableModel model1;
	private JTable table1;
	private JButton button_1;
	public static String change_hold_type="0";
	public static String SDPriceLock="0";
	public static boolean activeSaD=false;
	
	public static String mediacontrol="";
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					Mainpage window = new Mainpage("AL SAFI GENERAL TRADING LLC","127.0.0.1","admin","S01","mr_pro","S0101","1.01","2","1","1","English");
					Mainpage.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Mainpage(String company, String server, String username,String store_name,String db_name,String posuser, String version, String decimal, String activeSD, String acc_active, String language) throws JSONException, SQLException {
		initialize(company,server,username,store_name,db_name,posuser,version,decimal,  activeSD, acc_active,language);
	}
	private void initialize(final String company, final String server, final String username, final String store_name,final String db_name, final String posreg, final String version, final String decimal, final String activeSD, final String acc_active, final String language) throws JSONException, SQLException {
		UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,35)));
		UIManager.put("OptionPane.minimumSize",new Dimension(500,300));
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,24)));
		String lang="";
		if(language.equals("Arabic"))
		{
			lang="Ar";
		}
		else
		{
			lang="En";
		}
		SoftLang=lang;
		if(username.equals(""))
		{
			new loginscreen( store_name,posreg,server, version, company,  activeSD, acc_active,language);
		}
		else
		{
			frame = new JFrame("[DATABASE SERVER="+server+"] [COMPANY = "+company+"] [CURRENT USER = "+username+"] [HOST = "+server+"]");
			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent arg0) {
					try {
						InetAddress addr = InetAddress.getLocalHost();
					    String sys_name = addr.getHostName();
					    Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						Statement statement1=con.createStatement();  
						ResultSet resultSet = statement.executeQuery("select * from "+db_name+".mr_loggin_controls where username='"+username+"' and "+db_name+".mr_loggin_controls.device_name='"+sys_name+"' ");
						if(resultSet.next())
						{
							LocalDateTime myDateObj = LocalDateTime.now();  
						    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
						    final String formattedDate = myDateObj.format(myFormatObj);
							String del="update "+db_name+".mr_loggin_controls set status='1',modified_date='"+formattedDate+"' where username='"+username+"' and status='0' and  "+db_name+".mr_loggin_controls.device_name='"+sys_name+"' ";
							statement1.executeUpdate(del);
						}
						con.close();
						frame.dispose();
						Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
						Runtime.getRuntime().exec(new String[] {"cmd","/c","taskkill /im  javaw.exe /f"});
						
						//new loginscreen( store_name,posreg,server, version, company);
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(frame, e.getMessage());
					}
				}
			});
			Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
			frame.setIconImage(img);
			frame.getContentPane().setBackground(new Color(243, 229, 216));
			frame.getContentPane().setLayout(null);
			
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			 
		    frame.setVisible(true);
		    frame.setAlwaysOnTop(false);
		    frame.setResizable(false);
			frame.setBounds(0, 0, 1024, 768);
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			pos_register=posuser;
			try {
				
				File configFile = new File("C:/MasterRetails/config.properties");
				FileReader reader = new FileReader(configFile);
			    Properties props = new Properties();
			    props.load(reader);
			    mediacontrol = props.getProperty("mediacontrol");
			    reader.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			Image barcode=new ImageIcon(this.getClass().getResource("/Barcode.png")).getImage();
			
			Image store=new ImageIcon(this.getClass().getResource("/store.png")).getImage();
			
			Image Store=new ImageIcon(this.getClass().getResource("/store.png")).getImage();
			
			Image transfer=new ImageIcon(this.getClass().getResource("/transfer.png")).getImage();
			
			Image Setting=new ImageIcon(this.getClass().getResource("/Setting.png")).getImage();
			
			Image Users=new ImageIcon(this.getClass().getResource("/Users.png")).getImage();
			
			Image invoice_icon=new ImageIcon(this.getClass().getResource("/invoice-icon.png")).getImage();
			
			Image invoice=new ImageIcon(this.getClass().getResource("/invoice.png")).getImage();
			
			Image settings_icon=new ImageIcon(this.getClass().getResource("/settings-icon.png")).getImage();
			
			Image bank_chk=new ImageIcon(this.getClass().getResource("/Bank-Check.png")).getImage();
			
			Image dollar=new ImageIcon(this.getClass().getResource("/dollar.png")).getImage();
			
			Image Prd_sle_rpt=new ImageIcon(this.getClass().getResource("/Prd_sle_rpt.png")).getImage();
			
			Image SEO=new ImageIcon(this.getClass().getResource("/SEO.png")).getImage();
			
			Image calculator_1=new ImageIcon(this.getClass().getResource("/calculator_1.png")).getImage();
			
			Image lock=new ImageIcon(this.getClass().getResource("/lock.png")).getImage();
			
			Image Cash_register=new ImageIcon(this.getClass().getResource("/Cash-register.png")).getImage();
			
			Image Sales_Payment_Method=new ImageIcon(this.getClass().getResource("/Sales-Payment-Method.png")).getImage();
			
			Image reports=new ImageIcon(this.getClass().getResource("/reports.png")).getImage();
			
			Image percentage=new ImageIcon(this.getClass().getResource("/percentage.png")).getImage();
			
			Image Gift=new ImageIcon(this.getClass().getResource("/invoice.png")).getImage();
			
			JSeparator separator = new JSeparator();
			separator.setBounds(0, 670, 1018, 2);
			frame.getContentPane().add(separator);
			
			Image cafepro=new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
			JLabel lblNewLabel1 = new JLabel("");
			lblNewLabel1.setIcon(new ImageIcon(cafepro));
			lblNewLabel1.setBounds(0, 670, 58, 50);
			frame.getContentPane().add(lblNewLabel1);
			
			JLabel lblCurrentUser = new JLabel("CURRENT USER "+username);
			lblCurrentUser.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblCurrentUser.setBounds(68, 675, 161, 26);
			frame.getContentPane().add(lblCurrentUser);

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
			    
			    model1.addColumn("item_code");model1.addColumn("item_name");model1.addColumn("description1");model1.addColumn("description2");model1.addColumn("item_details");model1.addColumn("department");model1.addColumn("sub_department");model1.addColumn("category");model1.addColumn("sub_category");model1.addColumn("item_type");model1.addColumn("item_cost");model1.addColumn("item_lastcost");model1.addColumn("item_stdcost");model1.addColumn("total_qty");model1.addColumn("item_image");model1.addColumn("input_tax");model1.addColumn("output_tax");model1.addColumn("sp_include_tax");model1.addColumn("po_inclusive_tax");model1.addColumn("deliItemsweighing");model1.addColumn("exp_days");model1.addColumn("weighing_sts");model1.addColumn("parentKitItem");model1.addColumn("allowDiscount");model1.addColumn("disc_perc");model1.addColumn("activeForSale");model1.addColumn("activeForPurchase");model1.addColumn("Onpromo");model1.addColumn("start_date");model1.addColumn("enddate");model1.addColumn("min_order");model1.addColumn("acc_grp");model1.addColumn("acc_code");model1.addColumn("open_qty");model1.addColumn("unit1");model1.addColumn("cf1");model1.addColumn("sp1");model1.addColumn("barcode1");model1.addColumn("barcode2");model1.addColumn("barcode3");model1.addColumn("unit2");model1.addColumn("cf2");model1.addColumn("sp2");model1.addColumn("barcode4");model1.addColumn("barcode5");model1.addColumn("barcode6");model1.addColumn("unit3");model1.addColumn("cf3");model1.addColumn("sp3");model1.addColumn("barcode7");model1.addColumn("barcode8");model1.addColumn("barcode9");model1.addColumn("unit4");model1.addColumn("cf4");model1.addColumn("sp4");model1.addColumn("barcode10");model1.addColumn("barcode11");model1.addColumn("barcode12");model1.addColumn("unit5");model1.addColumn("cf5");model1.addColumn("sp5");model1.addColumn("barcode13");model1.addColumn("barcode14");model1.addColumn("barcode15");model1.addColumn("unit6");model1.addColumn("cf6");model1.addColumn("sp6");model1.addColumn("barcode16");model1.addColumn("barcode17");model1.addColumn("barcode18");model1.addColumn("created_date");model1.addColumn("modified_date");model1.addColumn("input_tax_rate");model1.addColumn("output_tax_rate");
			
			
			JLabel lblRegistredToCompany = new JLabel("REGISTERED TO "+company);
			lblRegistredToCompany.setHorizontalAlignment(SwingConstants.RIGHT);
			lblRegistredToCompany.setBounds(480, 680, 516, 14);
			frame.getContentPane().add(lblRegistredToCompany);
			
			JLabel lblNewLabel_1 = new JLabel(version);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNewLabel_1.setBounds(612, 700, 394, 16);
			frame.getContentPane().add(lblNewLabel_1);
			
			final JLabel timeLabel  = new JLabel("");
			timeLabel .setBounds(68, 700, 293, 16);
			frame.getContentPane().add(timeLabel );
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(0, 49, 1018, 2);
			frame.getContentPane().add(separator_1);
			
			Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
			btnNewButton_1 = new JButton("<html>EXIT<br>مخرج</html>");
			if(language.equals("Arabic"))
			{
				btnNewButton_1.setText("مخرج");
			}
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						InetAddress addr = InetAddress.getLocalHost();
					    String sys_name = addr.getHostName();
					    Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						Statement statement1=con.createStatement();  
						ResultSet resultSet = statement.executeQuery("select * from "+db_name+".mr_loggin_controls where username='"+username+"' and "+db_name+".mr_loggin_controls.device_name='"+sys_name+"' ");
						if(resultSet.next())
						{
							LocalDateTime myDateObj = LocalDateTime.now();  
						    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
						    final String formattedDate = myDateObj.format(myFormatObj);
							String del="update "+db_name+".mr_loggin_controls set status='1',modified_date='"+formattedDate+"' where username='"+username+"' and status='0' and  "+db_name+".mr_loggin_controls.device_name='"+sys_name+"' ";
							statement1.executeUpdate(del);
						}
						con.close();
						frame.dispose();
						Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
						Runtime.getRuntime().exec(new String[] {"cmd","/c","taskkill /im  javaw.exe /f"});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnNewButton_1.setIcon(new ImageIcon(exit));
			btnNewButton_1.setBounds(10, 11, 93, 33);
			frame.getContentPane().add(btnNewButton_1);
			
			Image dashboard=new ImageIcon(this.getClass().getResource("/dashboard.png")).getImage();
			JButton btnDashboard = new JButton("<html>DASHBOARD<br> لوحة القيادة </html>");
			if(language.equals("Arabic"))
			{
				btnDashboard.setText("لوحة القيادة");
			}
			btnDashboard.setHorizontalAlignment(SwingConstants.LEFT);
			btnDashboard.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnDashboard.setIcon(new ImageIcon(dashboard));
			//btnDashboard.setBounds(219, 11, 131, 33);
			btnDashboard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new dashboard(company, version,db_name,username,store_name,con,posreg,language);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			btnDashboard.setBounds(105, 11, 131, 33);
			frame.getContentPane().add(btnDashboard);
			
			button_1 = new JButton("<html>Sync Itemmaster<br>مزامنة مدير العنصر</html>");
			button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
			if(language.equals("Arabic"))
			{
				button_1.setText("مزامنة مدير العنصر");
			}
			button_1.setBounds(240, 11, 131, 33);
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					File configFile = new File("C:/MasterRetails/config.properties");
					try {
						FileReader reader = new FileReader(configFile);
					    Properties props = new Properties();
					    props.load(reader);
					    String counter_type = props.getProperty("counter");
					    reader.close();
					    if(!counter_type.equals("BO"))
					    {
					    	Connection con1=Dbsetup.local_server.getConnection();
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement;
							statement = con.createStatement();
							Statement statement1 = con1.createStatement();
							ResultSet resultSet123 = statement.executeQuery("select * from "+db_name+".mr_item_master ");
							if(resultSet123.next())
							{
								frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
								int rowCount = model1.getRowCount();
								for (int i = rowCount - 1; i >= 0; i--) {
									model1.removeRow(i);
								}
								
								String query1="SELECT * FROM "+db_name+".mr_item_master"; 
								ResultSet resultSet12 = statement.executeQuery(query1);
								while(resultSet12.next())
								{
									model1.addRow(new Object[]{resultSet12.getString("item_code"), resultSet12.getString("item_name"), resultSet12.getString("description1"), resultSet12.getString("description2"), resultSet12.getString("item_details"), resultSet12.getString("department"), resultSet12.getString("sub_department"), resultSet12.getString("category"), resultSet12.getString("sub_category"), resultSet12.getString("item_type"), resultSet12.getString("item_cost"), resultSet12.getString("item_lastcost"), resultSet12.getString("item_stdcost"), resultSet12.getString("total_qty"), resultSet12.getString("item_image"), resultSet12.getString("input_tax"), resultSet12.getString("output_tax"), resultSet12.getString("sp_include_tax"), resultSet12.getString("po_inclusive_tax"), resultSet12.getString("deliItemsweighing"), resultSet12.getString("exp_days"), resultSet12.getString("weighing_sts"), resultSet12.getString("parentKitItem"), resultSet12.getString("allowDiscount"), resultSet12.getString("disc_perc"), resultSet12.getString("activeForSale"), resultSet12.getString("activeForPurchase"), resultSet12.getString("Onpromo"), resultSet12.getString("start_date"), resultSet12.getString("enddate"), resultSet12.getString("min_order"), resultSet12.getString("acc_grp"), resultSet12.getString("acc_code"), resultSet12.getString("open_qty"), resultSet12.getString("unit1"), resultSet12.getString("cf1"), resultSet12.getString("sp1"), resultSet12.getString("barcode1"), resultSet12.getString("barcode2"), resultSet12.getString("barcode3"), resultSet12.getString("unit2"), resultSet12.getString("cf2"), resultSet12.getString("sp2"), resultSet12.getString("barcode4"), resultSet12.getString("barcode5"), resultSet12.getString("barcode6"), resultSet12.getString("unit3"), resultSet12.getString("cf3"), resultSet12.getString("sp3"), resultSet12.getString("barcode7"), resultSet12.getString("barcode8"), resultSet12.getString("barcode9"), resultSet12.getString("unit4"), resultSet12.getString("cf4"), resultSet12.getString("sp4"), resultSet12.getString("barcode10"), resultSet12.getString("barcode11"), resultSet12.getString("barcode12"), resultSet12.getString("unit5"), resultSet12.getString("cf5"), resultSet12.getString("sp5"), resultSet12.getString("barcode13"), resultSet12.getString("barcode14"), resultSet12.getString("barcode15"), resultSet12.getString("unit6"), resultSet12.getString("cf6"), resultSet12.getString("sp6"), resultSet12.getString("barcode16"), resultSet12.getString("barcode17"), resultSet12.getString("barcode18"), resultSet12.getString("created_date"), resultSet12.getString("modified_date"), resultSet12.getString("input_tax_rate"), resultSet12.getString("output_tax_rate")});
								}
								table1.scrollRectToVisible(table1.getCellRect(table1.getRowCount()-1, 0, true));
								FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\itemlist.txt");
								for(int x=0; x< model1.getRowCount(); x++) {
						            for(int y=0; y < model1.getColumnCount(); y++) {
						            	excel.write(model1.getValueAt(x,y).toString());
						                if(y < model1.getColumnCount()-1)
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
						        
						        String query11="TRUNCATE TABLE "+db_name+".mr_item_master ";
								statement1.executeUpdate(query11);
						        
						        String loadQuery = "LOAD DATA LOCAL INFILE '" + "C:/MasterRetails/Export/itemlist.txt" + "' INTO TABLE "+db_name+".mr_item_master FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' (`item_code`, `item_name`, `description1`, `description2`, `item_details`, `department`, `sub_department`, `category`, `sub_category`, `item_type`, `item_cost`, `item_lastcost`, `item_stdcost`, `total_qty`, `item_image`, `input_tax`, `output_tax`, `sp_include_tax`, `po_inclusive_tax`, `deliItemsweighing`, `exp_days`, `weighing_sts`, `parentKitItem`, `allowDiscount`, `disc_perc`, `activeForSale`, `activeForPurchase`, `Onpromo`, `start_date`, `enddate`, `min_order`, `acc_grp`, `acc_code`, `open_qty`, `unit1`, `cf1`, `sp1`, `barcode1`, `barcode2`, `barcode3`, `unit2`, `cf2`, `sp2`, `barcode4`, `barcode5`, `barcode6`, `unit3`, `cf3`, `sp3`, `barcode7`, `barcode8`, `barcode9`, `unit4`, `cf4`, `sp4`, `barcode10`, `barcode11`, `barcode12`, `unit5`, `cf5`, `sp5`, `barcode13`, `barcode14`, `barcode15`, `unit6`, `cf6`, `sp6`, `barcode16`, `barcode17`, `barcode18`, `created_date`, `modified_date`, `input_tax_rate`, `output_tax_rate`) ";
								Statement stmt = con1.createStatement();
								stmt.execute(loadQuery);
								
								Path fileToDeletePath1 = Paths.get("C:/MasterRetails/Export/itemlist.txt");
								Files.delete(fileToDeletePath1);
								
							    frame.setCursor(Cursor.getDefaultCursor());
							    JOptionPane.showMessageDialog(frame, "Sync Completed");
							    con.close();
								
							}
					    }
					} catch (Exception e) {
						System.err.println(e);
						frame.setCursor(Cursor.getDefaultCursor());
					} 
				}
			});
			frame.getContentPane().add(button_1);
			
			lbloffline = new JLabel("OFFLINE MODE");
			lbloffline.setHorizontalAlignment(SwingConstants.CENTER);
			lbloffline.setBounds(801, 25, 230, 26);
			frame.getContentPane().add(lbloffline);
			lbloffline.setVisible(false);
			lbloffline.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lbloffline.setForeground(Color.BLACK);
			
			
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(25, 63, 268, 298);
			frame.getContentPane().add(panel);
			panel.setBackground(new Color(243, 229, 216));
			panel.setLayout(null);
			
			//frame.setUndecorated(true);
			JLabel lblNewLabel = new JLabel("1.INVENTORY جرد");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(19, 11, 230, 26);
			panel.add(lblNewLabel);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel.setForeground(Color.BLACK);
			
			btnNewButton = new JButton("<html>1. ITEM MASTER<br>العنصر الرئيسي</html>");
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnNewButton.setBounds(19, 48, 230, 38);
			panel.add(btnNewButton);
			btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
			btnNewButton.setBackground(new Color(240, 240, 240));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						frame.setAlwaysOnTop(false);
						new inventory_master.Item_master(company,db_name,  "", username, "","","", version,store_name,Integer.parseInt(decimal),language);//"mr_pro", "","admin","","","", null
						//frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
						btnNewButton.setEnabled(false);
						//btnNewButton_1.setEnabled(false); 
					} catch (JSONException | SQLException e) {
						e.printStackTrace();
					}
				}
			});
			btnNewButton.setIcon(new ImageIcon(barcode));
			JButton btnInve = new JButton("<html>2. INVENTORY ADJUSTMENT <br>تعديل المخزون</html>");
			btnInve.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnInve.setBounds(19, 97, 230, 38);
			panel.add(btnInve);
			btnInve.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new inventory_master.Inv_adjust_list(db_name,company,username,store_name, version,decimal,language);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			btnInve.setHorizontalAlignment(SwingConstants.LEFT);
			btnInve.setIcon(new ImageIcon(store));
			btnInve.setBackground(new Color(240, 240, 240));
			JButton btnStockCount = new JButton("<html> 3. STOCK COUNT <br> عدد المخزون\r\n</html>\r\n");
			btnStockCount.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnStockCount.setBounds(19, 146, 230, 38);
			panel.add(btnStockCount);
			btnStockCount.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new Stocktaking(db_name, username, store_name, version);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			btnStockCount.setIcon(new ImageIcon(Store));
			btnStockCount.setHorizontalAlignment(SwingConstants.LEFT);
			btnStockCount.setBackground(SystemColor.menu);
			JButton btnInventoryTransfer = new JButton("<html>4. INVENTORY TRANSFER<br>نقل المخزون</html>");
			btnInventoryTransfer.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnInventoryTransfer.setBounds(19, 195, 230, 38);
			panel.add(btnInventoryTransfer);
			btnInventoryTransfer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Inv_Trnsfer(db_name,company,username,store_name, version,decimal,language);
				}
			});
			btnInventoryTransfer.setIcon(new ImageIcon(transfer));
			btnInventoryTransfer.setHorizontalAlignment(SwingConstants.LEFT);
			btnInventoryTransfer.setBackground(SystemColor.menu);
			JButton btnInventoryControl = new JButton("<html>5. MORE UTILITIES<br>المزيد من المرافق</html>");
			btnInventoryControl.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnInventoryControl.setBounds(19, 244, 230, 38);
			panel.add(btnInventoryControl);
			btnInventoryControl.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new inventory_master.Inv_More(db_name,  version,store_name,username,company, language);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			btnInventoryControl.setIcon(new ImageIcon(Setting));
			btnInventoryControl.setHorizontalAlignment(SwingConstants.LEFT);
			btnInventoryControl.setBackground(SystemColor.menu);
			
			panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.setBounds(354, 61, 268, 298);
			frame.getContentPane().add(panel_1);
			panel_1.setBackground(new Color(243, 229, 216));
			panel_1.setLayout(null);
			
			JLabel lblpurchasing = new JLabel("2.PURCHASING شراء");
			lblpurchasing.setHorizontalAlignment(SwingConstants.CENTER);
			lblpurchasing.setBounds(19, 11, 230, 26);
			panel_1.add(lblpurchasing);
			lblpurchasing.setForeground(Color.BLACK);
			lblpurchasing.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			btnVendorMaster = new JButton("<html>1. VENDOR MASTER <br>البائع الرئيسي</html>");
			btnVendorMaster.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnVendorMaster.setBounds(19, 48, 230, 38);
			panel_1.add(btnVendorMaster);
			btnVendorMaster.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						
						frame.setAlwaysOnTop(false);
						new purchase_master.vendor_details(company,db_name,username,"",store_name, version,decimal,language);
						//frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
						btnVendorMaster.setEnabled(false);
						//btnNewButton_1.setEnabled(false);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			btnVendorMaster.setIcon(new ImageIcon(Users));
			btnVendorMaster.setHorizontalAlignment(SwingConstants.LEFT);
			btnVendorMaster.setBackground(SystemColor.menu);
			
			btnPurchaseOrder = new JButton("<html>2. PURCHASE INVOICE<br>فاتورة الشراء</html>");
			btnPurchaseOrder.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnPurchaseOrder.setBounds(19, 97, 230, 38);
			panel_1.add(btnPurchaseOrder);
			btnPurchaseOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						frame.setAlwaysOnTop(false);
						new purchase_master.purchase_order_list(company,db_name, username,store_name, version,decimal,language);
						//frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
						btnPurchaseOrder.setEnabled(false);
						//btnNewButton_1.setEnabled(false);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			btnPurchaseOrder.setIcon(new ImageIcon(invoice_icon));
			btnPurchaseOrder.setHorizontalAlignment(SwingConstants.LEFT);
			btnPurchaseOrder.setBackground(SystemColor.menu);
			
			btnPurchaseReturn = new JButton("<html>3. PURCHASE RETURN<br>إرجاع المشتريات</html>");
			btnPurchaseReturn.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnPurchaseReturn.setBounds(19, 146, 230, 38);
			panel_1.add(btnPurchaseReturn);
			btnPurchaseReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						frame.setAlwaysOnTop(false);
						new purchase_master.purchase_return_list(company,db_name,   username,store_name, version,decimal, language);
						//frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
						btnPurchaseReturn.setEnabled(false);
						//btnNewButton_1.setEnabled(false);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			btnPurchaseReturn.setIcon(new ImageIcon(invoice));
			btnPurchaseReturn.setHorizontalAlignment(SwingConstants.LEFT);
			btnPurchaseReturn.setBackground(SystemColor.menu);
			
			JButton btnPoTerms = new JButton("<html>4. PO REPORTS<br>تقارير طلبات الشراء</html>");
			btnPoTerms.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnPoTerms.setBounds(19, 195, 230, 38);
			panel_1.add(btnPoTerms);
			btnPoTerms.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new PO_reports.po_report(db_name, version, store_name,posreg,username,decimal,language);
				}
			});
			btnPoTerms.setIcon(new ImageIcon(settings_icon));
			btnPoTerms.setHorizontalAlignment(SwingConstants.LEFT);
			btnPoTerms.setBackground(SystemColor.menu);
			
			JButton btnPurchaseControl = new JButton("<html>5. PURCHASE CONTROL<br>مراقبة المشتريات</html>");
			btnPurchaseControl.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnPurchaseControl.setBounds(19, 244, 230, 38);
			panel_1.add(btnPurchaseControl);
			btnPurchaseControl.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//new Itemmaster();
				}
			});
			btnPurchaseControl.setIcon(new ImageIcon(Setting));
			btnPurchaseControl.setHorizontalAlignment(SwingConstants.LEFT);
			btnPurchaseControl.setBackground(SystemColor.menu);
			
			panel_2 = new JPanel();
			panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_2.setBounds(678, 61, 268, 298);
			frame.getContentPane().add(panel_2);
			panel_2.setLayout(null);
			panel_2.setBackground(new Color(243, 229, 216));
			
			
			JLabel lblaccounts = new JLabel("3.ACCOUNTS الحسابات");
			lblaccounts.setHorizontalAlignment(SwingConstants.CENTER);
			lblaccounts.setBounds(19, 11, 230, 26);
			panel_2.add(lblaccounts);
			lblaccounts.setForeground(Color.BLACK);
			lblaccounts.setFont(new Font("Tahoma", Font.PLAIN, 15));
			JButton btnBankvoucher = new JButton("<html>1. VENDOR PAYMENT<br>دفع البائع</html>");
			btnBankvoucher.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnBankvoucher.setBounds(19, 48, 230, 38);
			panel_2.add(btnBankvoucher);
			btnBankvoucher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
						try {
							new ap_voucher_list(db_name, username,store_name,version,decimal,language);
							//new accounts_master.Accountpayable(db_name,  username, store_name,version,decimal);
						} catch (JSONException e) {
							e.printStackTrace();
						}
				}
			});
			btnBankvoucher.setIcon(new ImageIcon(bank_chk));
			btnBankvoucher.setHorizontalAlignment(SwingConstants.LEFT);
			btnBankvoucher.setBackground(SystemColor.menu);
			
			
			btnCashVoucher = new JButton("<html>2. CUSTOMER<br>عميل</html>");
			btnCashVoucher.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnCashVoucher.setBounds(19, 97, 230, 38);
			panel_2.add(btnCashVoucher);
			btnCashVoucher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new cust_display_buttn(db_name,username,posuser,store_name, version,language);
				}
			});
			btnCashVoucher.setIcon(new ImageIcon(dollar));
			btnCashVoucher.setHorizontalAlignment(SwingConstants.LEFT);
			btnCashVoucher.setBackground(SystemColor.menu);
			
			
			btnJournalVoucher = new JButton("<html>3. JOURNAL VOUCHER<br>قسيمة المجلة</html>");
			btnJournalVoucher.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnJournalVoucher.setBounds(19, 146, 230, 38);
			panel_2.add(btnJournalVoucher);
			btnJournalVoucher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new JV_header(db_name, username, store_name, version, decimal,language);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			btnJournalVoucher.setIcon(new ImageIcon(Prd_sle_rpt));
			
			btnJournalVoucher.setHorizontalAlignment(SwingConstants.LEFT);
			btnJournalVoucher.setBackground(SystemColor.menu);
			
			btnReports = new JButton("<html>4. VIEW LEDGER<br>عرض دفتر الأستاذ</html>");
			btnReports.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnReports.setBounds(19, 195, 230, 38);
			panel_2.add(btnReports);
			btnReports.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new Acc_ledger(db_name, version, decimal, store_name, username,language);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			btnReports.setIcon(new ImageIcon(SEO));
			btnReports.setHorizontalAlignment(SwingConstants.LEFT);
			btnReports.setBackground(SystemColor.menu);
			
			btnAccountsControl = new JButton("<html>5. ACCOUNTS CONTROL<br>مراقبة الحسابات</html>");
			btnAccountsControl.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnAccountsControl.setBounds(19, 244, 230, 38);
			panel_2.add(btnAccountsControl);
			btnAccountsControl.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					new accounts_master.Account_Setup(db_name, version,store_name,username,language);
				}
			});
			btnAccountsControl.setIcon(new ImageIcon(Setting));
			
			btnAccountsControl.setHorizontalAlignment(SwingConstants.LEFT);
			btnAccountsControl.setBackground(SystemColor.menu);
			
			panel_3 = new JPanel();
			panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_3.setBounds(25, 374, 268, 285);
			frame.getContentPane().add(panel_3);
			panel_3.setLayout(null);
			panel_3.setBackground(new Color(243, 229, 216));
			
			
			JLabel lblpos = new JLabel("<html>4.POS<br>\u0646\u0642\u0637\u0629 \u0627\u0644\u0628\u064A\u0639</html>");
			lblpos.setHorizontalAlignment(SwingConstants.CENTER);
			lblpos.setBounds(19, 10, 230, 38);
			panel_3.add(lblpos);
			lblpos.setForeground(Color.BLACK);
			lblpos.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnSales = new JButton("<html>1. SALES<br>\u0645\u0628\u064A\u0639\u0627\u062A</html>");
			btnSales.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnSales.setBounds(19, 55, 230, 35);
			panel_3.add(btnSales);
			btnSales.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						InetAddress addr = InetAddress.getLocalHost();
					    String sys_name = addr.getHostName();
						Statement statement=con1.createStatement();  
						Connection con=Dbsetup.dbconn.getConnection();
						postype=get_assign_reg( db_name,  username,  store_name,  posreg);
						if(postype.equals("0"))
						{
							frame.setVisible(false);
							JOptionPane.showMessageDialog(frame, "<html>Sorry, You Do not Have Permission to Enter, Contact Administrator<br>عذرًا، ليس لديك إذن بالدخول، اتصل بالمسؤول</html>");
							Statement statement1=con.createStatement();  
							ResultSet resultSet = statement.executeQuery("select * from "+db_name+".mr_loggin_controls where username='"+username+"' and "+db_name+".mr_loggin_controls.device_name='"+sys_name+"' ");
							if(resultSet.next())
							{
								LocalDateTime myDateObj = LocalDateTime.now();  
							    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
							    final String formattedDate = myDateObj.format(myFormatObj);
								String del="update "+db_name+".mr_loggin_controls set status='1',modified_date='"+formattedDate+"' where username='"+username+"' and status='0' and  "+db_name+".mr_loggin_controls.device_name='"+sys_name+"' ";
								statement1.executeUpdate(del);
							}
							con.close();
							frame.dispose();
							Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
							Runtime.getRuntime().exec(new String[] {"cmd","/c","taskkill /im  javaw.exe /f"});
							new loginscreen( store_name, posreg,server, version, decimal,  activeSD, acc_active,language);
							
						}
						else
						{
							
							ResultSet resultSet1 = statement.executeQuery("select * from "+db_name+".mr_register_openings where reg_num='"+posreg+"' ");
							if(resultSet1.next())
							{
								if(change_hold_type.equals("1"))
								{
									if(language.equals("Arabic"))
									{
										new sales_master.PosscreenArabic(company,db_name, username,store_name,posreg, version,server,decimal,con1,language);
									}
									else
									{
										new sales_master.posscreen(company,db_name, username,store_name,posreg, version,server,decimal,con1,language);
									}
									
									btnSales.setEnabled(false);
								}
								else
								{
									if(resultSet1.getString("device_name").equals(sys_name))
									{
										if(language.equals("Arabic"))
										{
											new sales_master.PosscreenArabic(company,db_name, username,store_name,posreg, version,server,decimal,con1, language);
										}
										else
										{
											new sales_master.posscreen(company,db_name, username,store_name,posreg, version,server,decimal,con1, language);	
										}
										
										btnSales.setEnabled(false);
									}
									else
									{
										JOptionPane.showMessageDialog(frame, "<html>Already Logged in another System<br>تم تسجيل الدخول بالفعل إلى نظام آخر</html>");
									}
								}
								//new sales_master.posscreen(company,db_name, username,store_name,posreg, version,server,decimal,con1);
								
							}
							else
							{
								new sales_master.Open_Cash(company,db_name,store_name, username,posreg,version,decimal,server,con1,language);
								btnSales.setEnabled(false);
							}
							
						}
					} catch (Exception e) {
						System.err.println(e);
						JOptionPane.showMessageDialog(frame, e.getMessage());
					}
					
				}
			});
			btnSales.setIcon(new ImageIcon(calculator_1));
			btnSales.setHorizontalAlignment(SwingConstants.LEFT);
			btnSales.setBackground(SystemColor.menu);
			
			btnXReports = new JButton("<html>2. X REPORTS<br>\u062A\u0642\u0631\u064A\u0631 X</html>");
			btnXReports.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnXReports.setBounds(19, 100, 230, 35);
			panel_3.add(btnXReports);
			btnXReports.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new POS_Reports.xreport(db_name, store_name, username, version, posreg, decimal,language);
					btnXReports.setEnabled(false);
				}
			});
			btnXReports.setIcon(new ImageIcon(Prd_sle_rpt));
			btnXReports.setHorizontalAlignment(SwingConstants.LEFT);
			btnXReports.setBackground(SystemColor.menu);
			btnCloseSales = new JButton("<html>3. CLOSE SALES<br> \u0625\u063A\u0644\u0627\u0642 \u0627\u0644\u0645\u0628\u064A\u0639\u0627\u062A</html>");
			btnCloseSales.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnCloseSales.setBounds(19, 145, 230, 35);
			panel_3.add(btnCloseSales);
			btnCloseSales.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new sales_master.Close_sales(db_name,  store_name, username, version,posreg,decimal,language);
					btnCloseSales.setEnabled(false);
				}
			});
			btnCloseSales.setIcon(new ImageIcon(lock));
			btnCloseSales.setHorizontalAlignment(SwingConstants.LEFT);
			btnCloseSales.setBackground(SystemColor.menu);
			
			btnHistoricalReports = new JButton("<html>4. OTHER REPORTS<br>\u062A\u0642\u0627\u0631\u064A\u0631 \u0623\u062E\u0631\u0649</html>");
			btnHistoricalReports.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnHistoricalReports.setBounds(19, 190, 230, 35);
			panel_3.add(btnHistoricalReports);
			btnHistoricalReports.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new sales_master.Xreports(db_name, version, store_name,posreg,username,decimal,userauth,language);
				}
			});
			btnHistoricalReports.setIcon(new ImageIcon(SEO));
			btnHistoricalReports.setHorizontalAlignment(SwingConstants.LEFT);
			btnHistoricalReports.setBackground(SystemColor.menu);
			
			
			btnPosControl = new JButton("<html>5. SALES&DISTRIBUTION<br>المبيعات والتوزيع</html>");
			btnPosControl.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnPosControl.setBounds(19, 235, 230, 35);
			//btnPosControl.setVisible(false);
			panel_3.add(btnPosControl);
			btnPosControl.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//new Itemmaster();
					try {
						new sales_distribution.sd_list(db_name,username,store_name, version,decimal,language);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
				}
			});
			btnPosControl.setIcon(new ImageIcon(Setting));
			btnPosControl.setHorizontalAlignment(SwingConstants.LEFT);
			btnPosControl.setBackground(SystemColor.menu);
			btnPosControl.setEnabled(false);			
			
			panel_4 = new JPanel();
			panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_4.setBounds(354, 372, 268, 285);
			frame.getContentPane().add(panel_4);
			panel_4.setLayout(null);
			panel_4.setBackground(new Color(243, 229, 216));
			
				
				JLabel lblReports_1 = new JLabel("5. REPORTS  التقارير");
				lblReports_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblReports_1.setBounds(19, 9, 230, 26);
				panel_4.add(lblReports_1);
				lblReports_1.setForeground(Color.BLACK);
				lblReports_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				JButton btnRegisterSummary = new JButton("<html>1. REGISTER SUMMARY<br>ملخص التسجيل</html>");
				btnRegisterSummary.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnRegisterSummary.setBounds(19, 44, 230, 38);
				panel_4.add(btnRegisterSummary);
				btnRegisterSummary.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new History_Reports.Reg_summary(db_name, username, store_name, version,posreg,printer);
					}
				});
				btnRegisterSummary.setIcon(new ImageIcon(Cash_register));
				btnRegisterSummary.setHorizontalAlignment(SwingConstants.LEFT);
				btnRegisterSummary.setBackground(SystemColor.menu);
				JButton btnSalesSummary = new JButton("<html>2. SALES SUMMARY<br>ملخص المبيعات</html>");
				btnSalesSummary.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnSalesSummary.setBounds(19, 91, 230, 38);
				panel_4.add(btnSalesSummary);
				btnSalesSummary.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							new History_Reports.sales_summary(company,db_name, username, store_name, version,language);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				});
				btnSalesSummary.setIcon(new ImageIcon(Sales_Payment_Method));
				btnSalesSummary.setHorizontalAlignment(SwingConstants.LEFT);
				btnSalesSummary.setBackground(SystemColor.menu);
				JButton btnItemSales = new JButton("<html>3. INVOICE HISTORY<br>سجل الفاتورة</html>");
				btnItemSales.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnItemSales.setBounds(19, 138, 230, 38);
				panel_4.add(btnItemSales);
				btnItemSales.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							new History_Reports.Invoice_history(db_name, username, store_name, version);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				});
				btnItemSales.setIcon(new ImageIcon(reports));
				btnItemSales.setHorizontalAlignment(SwingConstants.LEFT);
				btnItemSales.setBackground(SystemColor.menu);
				JButton btnTaxReporting = new JButton("<html>4. TAX REPORTING<br>الإبلاغ الضريبي</html>");
				btnTaxReporting.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnTaxReporting.setBounds(19, 185, 230, 38);
				panel_4.add(btnTaxReporting);
				btnTaxReporting.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							new History_Reports.Tax_reporting(db_name, username,  store_name, version,con);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				});
				btnTaxReporting.setIcon(new ImageIcon(percentage));
				btnTaxReporting.setHorizontalAlignment(SwingConstants.LEFT);
				btnTaxReporting.setBackground(SystemColor.menu);
				
				JButton btnMoreReports = new JButton("<html>5. MORE REPORTS<br>المزيد من التقارير</html>");
				btnMoreReports.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnMoreReports.setBounds(19, 232, 230, 38);
				panel_4.add(btnMoreReports);
				btnMoreReports.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new History_Reports.More_reports(db_name, version, store_name, posreg, username, decimal,printer, language);
					}
				});
				btnMoreReports.setIcon(new ImageIcon(SEO));
				btnMoreReports.setHorizontalAlignment(SwingConstants.LEFT);
				btnMoreReports.setBackground(SystemColor.menu);
				
				panel_5 = new JPanel();
				panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_5.setBounds(678, 372, 268, 285);
				frame.getContentPane().add(panel_5);
				panel_5.setLayout(null);
				panel_5.setBackground(new Color(243, 229, 216));
				
				JButton btnSystemSetup = new JButton("<html>1. SYSTEM SETUP<br>إعداد النظام</html>");
				btnSystemSetup.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnSystemSetup.setBounds(19, 44, 230, 38);
				panel_5.add(btnSystemSetup);
				btnSystemSetup.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new system_setups.System_setup(db_name,  username, version,company, language);
					}
				});
				btnSystemSetup.setIcon(new ImageIcon(Users));
				btnSystemSetup.setHorizontalAlignment(SwingConstants.LEFT);
				btnSystemSetup.setBackground(SystemColor.menu);
				
				JButton btnPosSetup = new JButton("<html>2. POS SETUP<br>إعداد نقطة البيع</html>");
				btnPosSetup.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnPosSetup.setBounds(19, 91, 230, 38);
				panel_5.add(btnPosSetup);
				btnPosSetup.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new system_setups.Pos_Setup(db_name,username, version,company, language);
					}
				});
				btnPosSetup.setIcon(new ImageIcon(calculator_1));
				btnPosSetup.setHorizontalAlignment(SwingConstants.LEFT);
				btnPosSetup.setBackground(SystemColor.menu);
				JButton btnSales_1 = new JButton("<html>3. PURCHASE SETUP<br>إعداد الشراء</html>");
				btnSales_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnSales_1.setBounds(19, 138, 230, 38);
				panel_5.add(btnSales_1);
				btnSales_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frame.setAlwaysOnTop(false);
						new system_setups.Purchase_Setup(db_name, username, version, language);
						frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
						//btnNewButton.setEnabled(false);
						
					}
				});
				btnSales_1.setIcon(new ImageIcon(Gift));
				btnSales_1.setHorizontalAlignment(SwingConstants.LEFT);
				btnSales_1.setBackground(SystemColor.menu);
				
				JButton btnInventorySetup = new JButton("<html>4. INVENTORY SETUP<br>إعداد المخزون</html>");
				btnInventorySetup.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnInventorySetup.setBounds(19, 185, 230, 38);
				panel_5.add(btnInventorySetup);
				btnInventorySetup.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new inventory_master.Inventory_Config(company,db_name,  version,store_name,language);;
					}
				});
				btnInventorySetup.setIcon(new ImageIcon(Prd_sle_rpt));
				btnInventorySetup.setHorizontalAlignment(SwingConstants.LEFT);
				btnInventorySetup.setBackground(SystemColor.menu);
				
				JButton btnMoreSetup = new JButton("<html>5. MORE SETUP<br>مزيد من الإعدادات</html>");
				btnMoreSetup.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnMoreSetup.setBounds(19, 232, 230, 38);
				panel_5.add(btnMoreSetup);
				btnMoreSetup.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new system_setups.More_Setup(db_name,  version,store_name,username);
					}
				});
				btnMoreSetup.setIcon(new ImageIcon(Setting));
				btnMoreSetup.setHorizontalAlignment(SwingConstants.LEFT);
				btnMoreSetup.setBackground(SystemColor.menu);
					
				
				JLabel lblSystemSetup = new JLabel("<html>6. SYSTEM SETUP<br>إعداد النظام</html>");
				lblSystemSetup.setHorizontalAlignment(SwingConstants.CENTER);
				lblSystemSetup.setBounds(19, 9, 230, 26);
				panel_5.add(lblSystemSetup);
				lblSystemSetup.setForeground(Color.BLACK);
				lblSystemSetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
				
				final JLabel lblNewSales = new JLabel("");
				lblNewSales.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewSales.setBounds(745, 1, 251, 35);
				frame.getContentPane().add(lblNewSales);
				lblNewSales.setForeground(Color.RED);
				lblNewSales.setFont(new Font("Monospaced", Font.BOLD, 20));
				
				JScrollPane scrollPane1 = new JScrollPane();
				scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane1.setBounds(0, 110, 908, 256);
				scrollPane1.setBackground(new Color(243, 229, 216));
				scrollPane1.setVisible(false);
				frame.getContentPane().add(scrollPane1);
				table1 = new JTable(model1);
				table1.setFont(new Font("Serif", Font.BOLD, 15));
				table1.setRowHeight(20);
				
				scrollPane1.setViewportView(table1);
				
				get_store_validate_dt(db_name,store_name,username, posreg);
				get_register_name(db_name,username, store_name,posreg);
				try {
					Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+ready_msg1+">"+display_port+"&&ECHO "+ready_msg2+">"+display_port+"\"");
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
				
			
			final DateFormat timeFormat = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss aa");
	        ActionListener timerListener = new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	                try {
						
						Date date = new Date();
		                String time = timeFormat.format(date);
		                timeLabel.setText(time);
		                Properties prop1=new Properties();
		        		FileInputStream ip1;
						ip1 = new FileInputStream("C:/MasterRetails/config.properties");
						prop1.load(ip1);
		        		String web_config= prop1.getProperty("web_config");
		        		ip1.close();
		        		
		        		if(web_config.equals("1"))
		        		{
		        			String id_str="";
		        			Connection con=Dbsetup.dbconn.getConnection();
		        			Statement statement = con.createStatement();
		        			String get_sd="select web_ref from "+db_name+".mr_sd_list order by web_ref desc limit 1";
		        			ResultSet resultSet = statement.executeQuery(get_sd);
		        			if(resultSet.next())
		        			{
		        				String id=resultSet.getString("web_ref");
		        				if(id!=null)
		        				{
		        					id_str="where [Order].Id > '"+id+"'";
		        				}
		        			}
		        			Properties prop=new Properties();
		        			FileInputStream ip = new FileInputStream("C:/MasterRetails/web_config.properties");
		        			prop.load(ip);
		        			String web_url= prop.getProperty("web_url");
		        			ip.close();
		        			
		        			Connection con2 = DriverManager.getConnection(web_url);
		        			String SQL = "select count(*) as ttl from [Order] "+id_str+" ";
		        			Statement stmt = con2.createStatement();
		            		ResultSet rs = stmt.executeQuery(SQL);
		            		if(rs.next()) {
		            			int count=Integer.parseInt(rs.getString("ttl"));
		            			if(count!=0)
		            			{
		            				lblNewSales.setText(rs.getString("ttl")+" NEW SALES");
		            			}
		            			else
		            			{
		            				lblNewSales.setText("");
		            			}
		            		}
		        		}
					} 
	                catch (Exception e1)
					{
							JOptionPane.showMessageDialog(frame, e1.getMessage());
					}
	            }
	        };
	        Timer timer = new Timer(1000, timerListener);
	        timer.setInitialDelay(0);
	        timer.start();
	        
	        get_user_type(db_name,username, store_name,posreg);
	        
	        
	        if(userauth.equals("Administrator"))
	        {
	        	enableComponents(panel,true);
				enableComponents(panel_1,true);
				enableComponents(panel_2,true);
				enableComponents(panel_3,true);
				enableComponents(panel_4,true);
				enableComponents(panel_5,true);
				if(activeSD.equals("1"))
				{
					activeSaD=true;
					btnPosControl.setEnabled(true);
					btnCashVoucher.setEnabled(true);
				}
				else
				{
					activeSaD=false;
					btnPosControl.setEnabled(false);
					btnCashVoucher.setEnabled(false);
				}
				if(acc_active.equals("1"))
				{
					btnJournalVoucher.setEnabled(true);
					btnAccountsControl.setEnabled(true);
					btnReports.setEnabled(true);
				}
				else
				{
					btnJournalVoucher.setEnabled(false);
					btnAccountsControl.setEnabled(false);
					btnReports.setEnabled(false);
				}
	        }
	        else if(userauth.equals("Manager"))
	        {
	        	enableComponents(panel,true);
				enableComponents(panel_1,true);
				enableComponents(panel_2,true);
				enableComponents(panel_3,true);
				enableComponents(panel_4,true);
				enableComponents(panel_5,false);
				btnStockCount.setEnabled(false);
				if(activeSD.equals("1"))
				{
					activeSaD=true;
					btnPosControl.setEnabled(true);
					btnCashVoucher.setEnabled(true);
				}
				else
				{
					activeSaD=false;
					btnPosControl.setEnabled(false);
					btnCashVoucher.setEnabled(false);
				}
				if(acc_active.equals("1"))
				{
					btnJournalVoucher.setEnabled(true);
					btnAccountsControl.setEnabled(true);
					btnReports.setEnabled(true);
				}
				else
				{
					btnJournalVoucher.setEnabled(false);
					btnAccountsControl.setEnabled(false);
					btnReports.setEnabled(false);
				}
	        }
	        else if(userauth.equals("User"))
	        {
	        	enableComponents(panel,true);
				enableComponents(panel_1,true);
				enableComponents(panel_2,false);
				enableComponents(panel_3,false);
				enableComponents(panel_4,false);
				enableComponents(panel_5,false);
				btnStockCount.setEnabled(false);
				if(activeSD.equals("1"))
				{
					activeSaD=true;
					btnPosControl.setEnabled(true);
					btnCashVoucher.setEnabled(true);
				}
				else
				{
					activeSaD=false;
					btnPosControl.setEnabled(false);
					btnCashVoucher.setEnabled(false);
				}
				if(acc_active.equals("1"))
				{
					btnJournalVoucher.setEnabled(true);
					btnAccountsControl.setEnabled(true);
					btnReports.setEnabled(true);
				}
				else
				{
					btnJournalVoucher.setEnabled(false);
					btnAccountsControl.setEnabled(false);
					btnReports.setEnabled(false);
				}
	        }
	        else if(userauth.equals("Supervisor"))
	        {
	        	enableComponents(panel,true);
				enableComponents(panel_1,true);
				enableComponents(panel_2,false);
				enableComponents(panel_3,true);
				enableComponents(panel_4,false);
				enableComponents(panel_5,false);
				btnStockCount.setEnabled(false);
				btnJournalVoucher.setEnabled(false);
				btnAccountsControl.setEnabled(false);
				btnReports.setEnabled(false);
				btnPosControl.setEnabled(false);
	        }
	        else if(userauth.equals("CashUser"))
	        {
	        	enableComponents(panel,false);
				enableComponents(panel_1,false);
				enableComponents(panel_2,false);
				enableComponents(panel_3,true);
				enableComponents(panel_4,false);
				enableComponents(panel_5,false);
				btnStockCount.setEnabled(false);
				btnJournalVoucher.setEnabled(false);
				btnAccountsControl.setEnabled(false);
				btnReports.setEnabled(false);
				btnPosControl.setEnabled(false);
	        }
	        else if(userauth.equals("Sales"))
	        {
	        	enableComponents(panel,false);
				enableComponents(panel_1,false);
				enableComponents(panel_2,false);
				enableComponents(panel_3,true);
				enableComponents(panel_4,false);
				enableComponents(panel_5,false);
				btnStockCount.setEnabled(false);
				btnJournalVoucher.setEnabled(false);
				btnAccountsControl.setEnabled(false);
				btnReports.setEnabled(false);
				btnPosControl.setEnabled(false);
	        }
	        else if(userauth.equals("Delivery Boy"))
	        {
	        	enableComponents(panel,false);
				enableComponents(panel_1,false);
				enableComponents(panel_2,false);
				enableComponents(panel_3,false);
				enableComponents(panel_4,false);
				enableComponents(panel_5,false);
				btnStockCount.setEnabled(false);
				btnJournalVoucher.setEnabled(false);
				btnAccountsControl.setEnabled(false);
				btnReports.setEnabled(false);
				btnPosControl.setEnabled(false);
	        }
	        else if(!userauth.equals("Administrator"))
	        {
	        	if(posreg.equals(""))
				{
					frame.setVisible(false);
					JOptionPane.showMessageDialog(frame, "Register Not Set for this PC");
					try {
						InetAddress addr = InetAddress.getLocalHost();
					    String sys_name = addr.getHostName();
					    Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						Statement statement1=con.createStatement();  
						ResultSet resultSet = statement.executeQuery("select * from "+db_name+".mr_loggin_controls where username='"+username+"' and "+db_name+".mr_loggin_controls.device_name='"+sys_name+"' ");
						if(resultSet.next())
						{
							LocalDateTime myDateObj = LocalDateTime.now();  
						    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
						    final String formattedDate = myDateObj.format(myFormatObj);
							String del="update "+db_name+".mr_loggin_controls set status='1',modified_date='"+formattedDate+"' where username='"+username+"' and status='0' and  "+db_name+".mr_loggin_controls.device_name='"+sys_name+"' ";
							statement1.executeUpdate(del);
						}
						con.close();
						frame.dispose();
						Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
						Runtime.getRuntime().exec(new String[] {"cmd","/c","taskkill /im  javaw.exe /f"});
						
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(frame, e.getMessage());
					}
					new loginscreen( store_name, posreg,server, version, decimal,  activeSD, acc_active,language);
				}
				else
				{
					postype=get_assign_reg( db_name,  username,  store_name,  posreg);
					if(postype.equals("0"))
					{
						frame.setVisible(false);
						JOptionPane.showMessageDialog(frame, "Sorry, You Do not Have Permission to Enter ");
						try {
							InetAddress addr = InetAddress.getLocalHost();
						    
						    String sys_name = addr.getHostName();
						    Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							Statement statement1=con.createStatement();  
							ResultSet resultSet = statement.executeQuery("select * from "+db_name+".mr_loggin_controls where username='"+username+"' and "+db_name+".mr_loggin_controls.device_name='"+sys_name+"' ");
							if(resultSet.next())
							{
								LocalDateTime myDateObj = LocalDateTime.now();  
							    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
							    final String formattedDate = myDateObj.format(myFormatObj);
								String del="update "+db_name+".mr_loggin_controls set status='1',modified_date='"+formattedDate+"' where username='"+username+"' and status='0' and  "+db_name+".mr_loggin_controls.device_name='"+sys_name+"' ";
								statement1.executeUpdate(del);
							}
							con.close();
							frame.dispose();
							Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
							Runtime.getRuntime().exec(new String[] {"cmd","/c","taskkill /im  javaw.exe /f"});
							//new loginscreen( store_name,posreg,server, version, company);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(frame, e.getMessage());
						}
						new loginscreen( store_name, posreg,server, version, decimal,  activeSD, acc_active,language);
						
					}
					else
					{
						enableComponents(panel,false);
						enableComponents(panel_1,false);
						enableComponents(panel_2,false);
						enableComponents(panel_4,false);
						enableComponents(panel_5,false);
					}
				}
	        	enableComponents(panel,false);
				enableComponents(panel_1,false);
				enableComponents(panel_2,false);
				enableComponents(panel_3,true);
				enableComponents(panel_4,false);
				enableComponents(panel_5,false);
	        }
	        
	        if(Usercancloseregister.equals("0"))
	        {
	        	btnCloseSales.setEnabled(false);
	        }
	        else
	        {
	        	btnCloseSales.setEnabled(true);
	        }
	        if(user_see_report.equals("0"))
	        {
	        	btnXReports.setEnabled(false);
	        	btnHistoricalReports.setEnabled(false);
	        }
	        else
	        {
	        	btnXReports.setEnabled(true);
	        	btnHistoricalReports.setEnabled(true);
	        }
			
	        
			
		}
	}
			
	public void get_register_name(String db_name,String username, String store_name,String posreg){
		try {
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+db_name+".mr_users where user_name='"+username+"' ");
			if(resultSet.next())
			{
				String reg="select * from "+db_name+".mr_assign_register where ref_user_code='"+resultSet.getString("user_code")+"' and select_status='true' ";
				ResultSet resultSet1 = statement.executeQuery(reg);
				if(resultSet1.next())
				{
					ResultSet resultSet2 = statement.executeQuery("select * from "+db_name+".mr_register_setup where register_number='"+posreg+"' ");
					if(resultSet2.next())
					{
						
						ready_msg1=resultSet2.getString("ready_msg1");
						ready_msg2=resultSet2.getString("ready_msg2");
						display_port=resultSet2.getString("pole_display_port");
						printer=resultSet2.getString("printer1");	
						
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
	}
		
	
	public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container)component, enable);
            }
        }
    }
	public String get_user_type(String db_name,String username, String store_name, String posreg){
		try {
			Statement statement=con.createStatement();
			Statement statement1=con.createStatement();
			Statement statement2=con.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from "+db_name+".mr_users where user_name='"+username+"' ");
			if(resultSet.next())
			{
				userauth=resultSet.getString("user_type");
				String query="select * from "+db_name+".mr_user_poscontrol where ref_user_code='"+resultSet.getString("user_code")+"' ";
				ResultSet resultSet1 = statement1.executeQuery(query);
				if(resultSet1.next())
				{
					posuser=resultSet1.getString("PosUser");
					user_see_report=resultSet1.getString("usermake_sale_order");
					see_cost=resultSet1.getString("userview_cost");
					see_qty=resultSet1.getString("DeliveryAgent");
					Usercancloseregister=resultSet1.getString("chckbxUserReopencloseregi");
				}
				String query1="select * from "+db_name+".mr_user_admincontrol where ref_user_code='"+resultSet.getString("user_code")+"' ";
				ResultSet resultSet11 = statement2.executeQuery(query1);
				if(resultSet11.next())
				{
					
					customerpaymenthist=resultSet11.getString("CreateNewuser");
					posinvoicehistory=resultSet11.getString("editcrdtls_exist_user");
					
				}
			}
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
		return posuser;
	}
	public String get_assign_reg(String db_name,String username, String store_name, String posreg){
		try {
			Statement statement=con1.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+db_name+".mr_users where user_name='"+username+"' ");
			if(resultSet.next())
			{
				ResultSet resultSet1 = statement.executeQuery("select * from "+db_name+".mr_assign_register where ref_register_num='"+posreg+"' and ref_user_code='"+resultSet.getString("user_code")+"' ");
				if(resultSet1.next())
				{
					postype=resultSet1.getString("select_status");
					if(postype.equals("true"))
					{
						postype="1";
					}
					else
					{
						postype="0";
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
		return postype;
	}
	public static void get_store_validate_dt(String company,String store_name,String username, String posreg)
	{
		try {
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store where store_id='"+store_name+"' ");
			if(resultSet.next())
			{
				
				change_hold_type=resultSet.getString("change_hold_type");
				SDPriceLock=resultSet.getString("SdPricelock");
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
