package inventory_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.json.JSONException;

public class Inventory_Reports {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory_Reports window = new Inventory_Reports(null, null, null, null,"","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param company_name 
	 */
	public Inventory_Reports(String version,String dbname,String store,String username, String company_name,String language) {
		initialize(version,dbname,store,username,company_name, language);
	}
	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store 
	 * @param dbname 
	 * @param company_name 
	 */
	private void initialize(final String version, final String dbname,final String store,final String username, final String company_name,final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
				frame.setIconImage(img);
				frame.getContentPane().setBackground(new Color(243, 229, 216));
				frame.getContentPane().setLayout(null);
				
				//Image settings=new ImageIcon(this.getClass().getResource("/Settings.png")).getImage();
				//Image product=new ImageIcon(this.getClass().getResource("/product.png")).getImage();
				//Image calculator=new ImageIcon(this.getClass().getResource("/calculator.png")).getImage();
				//Image report=new ImageIcon(this.getClass().getResource("/report.png")).getImage();
				//Image cafepro=new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
				
//				frame.setUndecorated(true);
//				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//				frame.setVisible(true);
//				frame.setResizable(false);
//				frame.setBounds(0, 0, 1024, 748);
//				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				JLabel lblNewLabel = new JLabel("4.INVENTORY REPORTS");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNewLabel.setForeground(Color.BLACK);
				lblNewLabel.setBounds(25, 61, 220, 26);
				
				frame.getContentPane().add(lblNewLabel);
				
				
				JButton btnNewButton = new JButton("1. ITEM LISTING");
				btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
				btnNewButton.setBackground(new Color(240, 240, 240));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Connection con=Dbsetup.dbconn.getConnection();
						try {
							frame.setAlwaysOnTop(false);
							report_master.otherinv_rpt.report(con,dbname);
							//frame.setAlwaysOnTop(true);
							con.close();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				btnNewButton.setIcon(new ImageIcon("C:\\Users\\abdullasuhail\\Desktop\\caferpro icons\\reports-icon.png"));
				btnNewButton.setBounds(46, 124, 230, 38);
				frame.getContentPane().add(btnNewButton);
				
				JButton btnInve = new JButton("2. STOCK STATUS");
				btnInve.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							new stock_status(dbname,username,store, version,"");
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnInve.setHorizontalAlignment(SwingConstants.LEFT);
				btnInve.setBackground(new Color(240, 240, 240));
				btnInve.setBounds(322, 124, 230, 38);
				frame.getContentPane().add(btnInve);
				
				JButton btnStockCount = new JButton("3. PRICE LIST");
				btnStockCount.setVisible(false);
				btnStockCount.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Connection con=Dbsetup.dbconn.getConnection();
						try {
							frame.setAlwaysOnTop(false);
							report_master.otherinv_rpt.report(con, dbname);
							//frame.setAlwaysOnTop(true);
							con.close();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				btnStockCount.setIcon(new ImageIcon("C:\\Users\\abdullasuhail\\Desktop\\caferpro icons\\reports-icon.png"));
				btnStockCount.setHorizontalAlignment(SwingConstants.LEFT);
				btnStockCount.setBackground(SystemColor.menu);
				btnStockCount.setBounds(623, 280, 230, 38);
				frame.getContentPane().add(btnStockCount);
				
				JButton btnInventoryTransfer = new JButton("5. MINIMUM ORDER");
				btnInventoryTransfer.setVisible(false);
				btnInventoryTransfer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
					}
				});
				btnInventoryTransfer.setIcon(new ImageIcon("C:\\Users\\abdullasuhail\\Desktop\\caferpro icons\\reports-icon.png"));
				btnInventoryTransfer.setHorizontalAlignment(SwingConstants.LEFT);
				btnInventoryTransfer.setBackground(SystemColor.menu);
				btnInventoryTransfer.setBounds(101, 280, 230, 38);
				frame.getContentPane().add(btnInventoryTransfer);
				
				JButton btnInventoryTransferReport = new JButton("5. INVENTORY ADJUSTMENT REPORT");
				btnInventoryTransferReport.setVisible(true);
				btnInventoryTransferReport.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					
						try {
							new InventoryAdjustmentReport(dbname,username,store, version,language);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				btnInventoryTransferReport.setIcon(new ImageIcon("C:\\Users\\abdullasuhail\\Desktop\\caferpro icons\\reports-icon.png"));
				btnInventoryTransferReport.setHorizontalAlignment(SwingConstants.LEFT);
				btnInventoryTransferReport.setBackground(SystemColor.menu);
				btnInventoryTransferReport.setBounds(322, 197, 259, 38);
				frame.getContentPane().add(btnInventoryTransferReport);

				
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				 
			    frame.setVisible(true);
			
			    frame.setResizable(false);
				frame.setBounds(100, 100, 900, 550);
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
				JSeparator separator = new JSeparator();
				separator.setBounds(0, 437, 1018, 2);
				frame.getContentPane().add(separator);
				
				JLabel lblNewLabel1 = new JLabel("");
				//lblNewLabel1.setIcon(new ImageIcon(cafepro));
				lblNewLabel1.setBounds(0, 683, 58, 50);
				frame.getContentPane().add(lblNewLabel1);
				
				JLabel lblCurrentUser = new JLabel("CURRENT USER ");
				lblCurrentUser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblCurrentUser.setBounds(31, 437, 161, 26);
				frame.getContentPane().add(lblCurrentUser);
				
				JLabel lblRegistredToCompany = new JLabel("REGISTERED TO ");
				lblRegistredToCompany.setHorizontalAlignment(SwingConstants.RIGHT);
				lblRegistredToCompany.setBounds(280, 456, 516, 14);
				frame.getContentPane().add(lblRegistredToCompany);
				
				JLabel lblNewLabel_1 = new JLabel(version);
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblNewLabel_1.setBounds(402, 481, 394, 16);
				frame.getContentPane().add(lblNewLabel_1);
				
				final JLabel timeLabel  = new JLabel("");
				timeLabel .setBounds(24, 474, 293, 16);
				frame.getContentPane().add(timeLabel );
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setBounds(0, 49, 1018, 2);
				frame.getContentPane().add(separator_1);
				
				Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
				
				JMenuItem menuItem = new JMenuItem("Exit");
				menuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frame.setVisible(false);
					}
				});
				menuItem.setBorder(null);
				menuItem.setBackground(new Color(243, 229, 216));
				menuItem.setIcon(new ImageIcon(exit));
				menuItem.setBounds(12, 0, 60, 48);
				frame.getContentPane().add(menuItem);
				
				JButton btnSubCategory = new JButton("3. STOCK AS ON DATE");
				btnSubCategory.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							//new stock_status(dbname,username,store, version,"");
							new Stock_as_date(dbname,username,store, version,"");
							
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnSubCategory.setHorizontalAlignment(SwingConstants.LEFT);
				btnSubCategory.setBackground(SystemColor.menu);
				btnSubCategory.setBounds(612, 124, 230, 38);
				frame.getContentPane().add(btnSubCategory);
				
				JButton btnItemTaxes = new JButton("4. ITEMS WITH KIT ITEMS");
				btnItemTaxes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							new item_kit_item(dbname,username,store, version);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				btnItemTaxes.setHorizontalAlignment(SwingConstants.LEFT);
				btnItemTaxes.setBackground(SystemColor.menu);
				btnItemTaxes.setBounds(46, 197, 230, 38);
				frame.getContentPane().add(btnItemTaxes);
				
				JButton btnItemBarcode = new JButton("4. ITEM BARCODE");
				btnItemBarcode.setVisible(false);
				btnItemBarcode.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Connection con=Dbsetup.dbconn.getConnection();
						try {
							frame.setAlwaysOnTop(false);
							report_master.otherinv_rpt.report(con, dbname);
							//frame.setAlwaysOnTop(true);
							con.close();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				btnItemBarcode.setIcon(new ImageIcon("C:\\Users\\abdullasuhail\\Desktop\\caferpro icons\\reports-icon.png"));
				btnItemBarcode.setHorizontalAlignment(SwingConstants.LEFT);
				btnItemBarcode.setBackground(SystemColor.menu);
				btnItemBarcode.setBounds(333, 280, 230, 38);
				frame.getContentPane().add(btnItemBarcode);
				final DateFormat timeFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
		        ActionListener timerListener = new ActionListener()
		        {
		            public void actionPerformed(ActionEvent e)
		            {
		                Date date = new Date();
		                String time = timeFormat.format(date);
		                timeLabel.setText(time);
		            }
		        };
		        Timer timer = new Timer(1000, timerListener);
		        timer.setInitialDelay(0);
		        timer.start();
	}

}
