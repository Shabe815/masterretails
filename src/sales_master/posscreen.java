package sales_master;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

import org.json.JSONException;

import CafePro.Mainpage;

import com.sun.glass.events.KeyEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;

public class posscreen {

	public static JFrame frmRegisterNoS;
	public static JTextField txtDisc;
	static JTable table;
	static JTextField textField_3;
	static JTextField textField_1;
	static JTextField textField_2;
	static JTextField textField_4;
	static JTextField textField_8;
	static JTextField textField_6;
	static JTextField textField_9;
	static String store_credit_validity;
	static DefaultTableModel model;
	static String reg_nme = null;
	static String current_register = null;
	static String old_recipt = "";
	public static String item_comment="0";
	static String promt_print="0";
	static String printer=null;
	public static String printer2=null;
	static String user_name;
	static String barcode_label;
	static String price;
	static String manager_name;
	static boolean ChangeHoldQty=false;
	static boolean checkprint_invoice_change=false;
	static JButton btnNewButton;
	private String time=null;
	static JLabel timeLabel;
	private static int receipt_num;
	static JTextField textField;
	static JTextField textField_5;
	//private boolean dot = false;
	static String returns="";
	private JButton btnMisc ;
	private static JButton btnNoSale;
	private static JButton btnPrice;
	static JButton btnQuickReturn ;
	static JButton btnPayIn;
	static JButton btnPayOut ;
	static JButton btnFastItems;
	private static JButton btnDeleteLine ;
	private static JButton btnHold ;
	static JButton btnUnhold ;
	private static JButton btnDisc ;
	private static JButton btnDiscAmt ;
	private static JButton btnVoid ;
	private static JButton btnX ;
	private static JButton btnAR;
	public static JButton btnPlu;
	private static JButton btnCash;
	static JButton btnchangeUnit;
	static String disc_amt="";
	static JButton btnNewCustomer ;
	static JButton btncustStatement ;
	static JButton btncustPay ;
	static JButton btnduplicateBill;
	public static JButton btnManager;
	static DecimalFormat df2;
	static int decimalPlaces;
	//private static int consildate_line=0;
	private static JButton btnStoreCredit;
	private static String AllowNegativeQuantity= "0";
	public static String hold_bill= "0";
	private static String flag = "0";
	static String allow_roundoff="0";
	public static String fastitem_Screen="0";
	public static String CustDisc="0";
	public static String ApplyItemDiscount="0";
	public static String change_hold_type="0";
	public static String register_all_invoice="0";
	public static String fastitem_mode="0";
	static String print_duplicate_invoice="0";
	
	
	static String pause_before_print="0";
	static String unhold_reg_hold_trans="0";
	static String restrict_delivery_boy="0";
	static String duplicate_holdinv="0";
	static String pause_kot_print="0";
	static String PauseHoldPrint="0";
	static JButton btnPayCode;
	public static String status;
	public static JPanel panel;
	public static JPanel panel_1;
	public static JPanel panel_7;
	public static JPanel panel_11;
	private static String ttl_item="0";
	static JButton btnQuickReturn_1;
	public static JButton button_4;
	public static JButton button_5;
	public static JButton button_6;
	public static JButton button_9;
	public static String display_port;
	public static String ready_msg1;
	public static String ready_msg2;
	public static String formattedDate;
	public static Calendar cal;
	public static SimpleDateFormat times;
	static JTextField textField_7;
	static JLabel lblNewLabel_1;
	private DefaultTableModel model1;
	private JTable table1;
	public static String VoidSettledinvo="0"; 
	public static String CashChange="0";
	public static String ManageSettledInvoices="0";
	private static JTextField textField_10;
	public static JLabel lblNewLabel_2;
	public static boolean hold_inv=false;
	public static String page_type="Delivery";
	static JTextField textField_11;
	static JLabel label;
	static JLabel label_1;
	static JLabel label_2;
	static int copy=1;
	
	static String ItemNotFound = null;
	static String ZeroPrice = null;
	static String LessThanCost = null;
	static String deleteLine=null;
	static String holdInv=null;
	static String store="";
	
	static String description="";
	static boolean stats=false;
	static String sellprice = "";
	
	static String cashdrawercode="";
	static byte[] open;
	
	static JButton btnhistInvoice;
	
	private static String allow_store_tax="";
	
	static String customer_index="";
	static String customer_variable="";
	
	static String checkbillstatus="0";
	
	
	static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    public static GraphicsDevice[] gs = ge.getScreenDevices();
    static JTextField textField_12;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					posscreen window = new posscreen("","mr_pro","ADMIN","S01","S0101", "","127.0.0.1","",Dbsetup.server.getConnection(),"");
					posscreen.frmRegisterNoS.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param posreg 
	 */
	public posscreen(String db_name,String company, String username, String store_name, String posreg,String version,String server,String decimal,Connection con,String language) {
		initialize(db_name,company,username,store_name,posreg, version,server,decimal,con, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 */
	private void initialize(final String db_name,final String company,final String username,final String store_name,final String posreg,final String version,final String server,final String decimal,final Connection con,final String language) {
		
		if(!username.equals(""))
		{
			reg_nme=posreg;
			current_register=posreg;
			store=store_name;
		}
		else
		{
		}
		
		user_name=username;
		manager_name=username;
		
		try {
			
			File configFile = new File("C:/MasterRetails/media.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    ItemNotFound = props.getProperty("ItemNotFound");
		    ZeroPrice = props.getProperty("ZeroPrice");
		    LessThanCost = props.getProperty("LessThanCost");
		    deleteLine=props.getProperty("deleteLine");
		    holdInv=props.getProperty("holdInv");
		    reader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
			Properties prop=new Properties();
			FileInputStream ip= new FileInputStream("C:/MasterRetails/config.properties");
			prop.load(ip);
			customer_index= prop.getProperty("customer_index");
			customer_variable=prop.getProperty("customer_variable");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	    formattedDate = myDateObj.format(myFormatObj);
	    
        times = new SimpleDateFormat("HH:mm:ss");
        
        Image remove = new ImageIcon(this.getClass().getResource("/Remove-item-icon.png")).getImage();
		
		frmRegisterNoS = new JFrame();
		frmRegisterNoS.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
		       int row_count=model.getRowCount();
		      // frmRegisterNoS.setVisible(false);
		       if(row_count>0)
		       {
		    	   Toolkit.getDefaultToolkit().beep();
		    	   JOptionPane.showMessageDialog(frmRegisterNoS, "Table Not Empty");
		    	   posscreen.txtDisc.requestFocus();
		    	  
		       }
		       else
		       {
		    	   frmRegisterNoS.setVisible(false);
		    	   if(gs.length>1)
					{
		    	   SecondDisplay.frmRegisterNoS.setVisible(false);
					}
		    	   Mainpage.btnSales.setEnabled(true);
		       }
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		Image serch = new ImageIcon(this.getClass().getResource("/search-icon.png")).getImage();
		frmRegisterNoS.setIconImage(img);
		
		if(gs.length>1)
		{
			new SecondDisplay(db_name, company, username, store_name, posreg, version, server, decimal, con);
		}
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		
		if(width==1024 && height==768)
		{
			frmRegisterNoS.setForeground(Color.DARK_GRAY);
			frmRegisterNoS.getContentPane().setForeground(Color.DARK_GRAY);
			frmRegisterNoS.getContentPane().setFont(new Font("Arial", Font.BOLD, 40));
			frmRegisterNoS.getContentPane().setBackground(Color.DARK_GRAY);
			frmRegisterNoS.setBounds(00, 00, width, height);
			frmRegisterNoS.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frmRegisterNoS.getContentPane().setLayout(null);
			frmRegisterNoS.setVisible(true);
			//frmRegisterNoS.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frmRegisterNoS.setResizable(false);
			
			panel = new JPanel();
			panel.setBackground(Color.DARK_GRAY);
			panel.setBounds(710, 187, 293, 530);
			panel.setBorder(new LineBorder(new Color(100, 149, 237), 3, true));
			frmRegisterNoS.getContentPane().add(panel);
			panel.setLayout(null);
			
			Image no7 = new ImageIcon(this.getClass().getResource("/Numbers-7-Black-icon.png")).getImage();
			
			
			JButton but_7 = new JButton("");
			but_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("7");
				}
			});
			but_7.setIcon(new ImageIcon(no7));
			but_7.setBounds(6, 72, 65, 52);
			panel.add(but_7);
			but_7.setForeground(Color.DARK_GRAY);
			but_7.setBorder(new LineBorder(SystemColor.control));
			but_7.setFont(new Font("Arial", Font.BOLD, 30));
			but_7.setBackground(SystemColor.control);
			
			Image no8 = new ImageIcon(this.getClass().getResource("/Numbers-8-Black-icon.png")).getImage();
			JButton but_8 = new JButton("");
			but_8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("8");
				}
			});
			but_8.setIcon(new ImageIcon(no8));
			but_8.setBorder(new LineBorder(SystemColor.control));
			but_8.setBounds(75, 72, 65, 52);
			panel.add(but_8);
			but_8.setForeground(Color.DARK_GRAY);
			but_8.setFont(new Font("Arial", Font.BOLD, 30));
			but_8.setBackground(SystemColor.control);
			
			Image no9 = new ImageIcon(this.getClass().getResource("/Numbers-9-Black-icon.png")).getImage();
			JButton but_9 = new JButton("");
			but_9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("9");
				}
			});
			but_9.setIcon(new ImageIcon(no9));
			but_9.setBorder(new LineBorder(SystemColor.control));
			but_9.setBounds(146, 72, 65, 52);
			panel.add(but_9);
			but_9.setForeground(Color.DARK_GRAY);
			but_9.setFont(new Font("Arial", Font.BOLD, 30));
			but_9.setBackground(SystemColor.control);
			
			JButton but_clear = new JButton("BKSP");
			but_clear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!txtDisc.getText().equals(""))
					{
						txtDisc.setText(txtDisc.getText().substring(0, txtDisc.getText().length() - 1));
						txtDisc.requestFocus();
					}
				}
			});
			but_clear.setHorizontalTextPosition(JButton.CENTER);
			but_clear.setVerticalTextPosition(JButton.BOTTOM);
			but_clear.setIcon(new ImageIcon("E:\\eclipseKEPLER\\ICONS\\back-icon.png"));
			but_clear.setBorder(new LineBorder(SystemColor.control));
			but_clear.setBounds(219, 72, 65, 52);
			panel.add(but_clear);
			but_clear.setForeground(Color.DARK_GRAY);
			but_clear.setFont(new Font("Arial", Font.BOLD, 15));
			but_clear.setBackground(SystemColor.control);
			
			Image no4 = new ImageIcon(this.getClass().getResource("/Numbers-4-Black-icon.png")).getImage();
			JButton but_4 = new JButton("");
			but_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("4");
				}
			});
			but_4.setIcon(new ImageIcon(no4));
			but_4.setBorder(new LineBorder(SystemColor.control));
			but_4.setBounds(6, 130, 65, 52);
			panel.add(but_4);
			but_4.setForeground(Color.DARK_GRAY);
			but_4.setFont(new Font("Arial", Font.BOLD, 30));
			but_4.setBackground(SystemColor.control);
			
			Image no5 = new ImageIcon(this.getClass().getResource("/Numbers-5-Black-icon.png")).getImage();
			JButton but_5 = new JButton("");
			but_5.setIcon(new ImageIcon(no5));
			but_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("5");
				}
			});
			but_5.setBorder(new LineBorder(SystemColor.control));
			but_5.setBounds(75, 130, 65, 52);
			panel.add(but_5);
			but_5.setForeground(Color.DARK_GRAY);
			but_5.setFont(new Font("Arial", Font.BOLD, 30));
			but_5.setBackground(SystemColor.control);
			
			Image no6 = new ImageIcon(this.getClass().getResource("/Numbers-6-Black-icon.png")).getImage();
			JButton but_6 = new JButton("");
			but_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("6");
				}
			});
			but_6.setIcon(new ImageIcon(no6));
			but_6.setBorder(new LineBorder(SystemColor.control));
			but_6.setBounds(146, 130, 65, 52);
			panel.add(but_6);
			but_6.setForeground(Color.DARK_GRAY);
			but_6.setFont(new Font("Arial", Font.BOLD, 30));
			but_6.setBackground(SystemColor.control);
			
			JButton button_3 = new JButton(".");
			button_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn(".");
				}
			});
			button_3.setBorder(new LineBorder(SystemColor.control));
			button_3.setBounds(148, 246, 65, 52);
			panel.add(button_3);
			button_3.setForeground(Color.DARK_GRAY);
			button_3.setFont(new Font("Arial", Font.BOLD, 30));
			button_3.setBackground(SystemColor.control);
			
			Image no1 = new ImageIcon(this.getClass().getResource("/Numbers-1-Black-icon.png")).getImage();
			JButton but_1 = new JButton("");
			but_1.setIcon(new ImageIcon(no1));
			but_1.setBorder(new LineBorder(SystemColor.control));
			but_1.setBounds(7, 188, 63, 52);
			panel.add(but_1);
			but_1.setForeground(Color.DARK_GRAY);
			but_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("1");
				}
			});
			but_1.setFont(new Font("Arial", Font.BOLD, 30));
			but_1.setBackground(SystemColor.control);
			
			Image no2 = new ImageIcon(this.getClass().getResource("/Numbers-2-Black-icon.png")).getImage();
			JButton but_2 = new JButton("");
			but_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("2");
				}
			});
			but_2.setIcon(new ImageIcon(no2));
			but_2.setBorder(new LineBorder(SystemColor.control));
			but_2.setBounds(75, 188, 65, 52);
			panel.add(but_2);
			but_2.setForeground(Color.DARK_GRAY);
			but_2.setFont(new Font("Arial", Font.BOLD, 30));
			but_2.setBackground(SystemColor.control);
			
			Image no3 = new ImageIcon(this.getClass().getResource("/Numbers-3-Black-icon.png")).getImage();
			JButton but_3 = new JButton("");
			but_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("3");
				}
			});
			but_3.setIcon(new ImageIcon(no3));
			but_3.setBorder(new LineBorder(SystemColor.control));
			but_3.setBounds(147, 188, 65, 52);
			panel.add(but_3);
			but_3.setForeground(Color.DARK_GRAY);
			but_3.setFont(new Font("Arial", Font.BOLD, 30));
			but_3.setBackground(SystemColor.control);
			
			Image no0 = new ImageIcon(this.getClass().getResource("/Numbers-0-Black-icon.png")).getImage();
			JButton button_7 = new JButton("");
			button_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("0");
				}
			});
			button_7.setIcon(new ImageIcon(no0));
			button_7.setBorder(new LineBorder(SystemColor.control));
			button_7.setBounds(77, 246, 65, 52);
			panel.add(button_7);
			button_7.setForeground(Color.DARK_GRAY);
			button_7.setFont(new Font("Arial", Font.BOLD, 30));
			button_7.setBackground(SystemColor.control);
			
			txtDisc = new JTextField();
			txtDisc.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(java.awt.event.KeyEvent e1) {
					int keycode=e1.getKeyCode();
					if(keycode==155)
					{
						table.requestFocus();
					}
				}
			});
			txtDisc.setHorizontalAlignment(SwingConstants.CENTER);
			txtDisc.setFont(new Font("Tahoma", Font.PLAIN, 25));
			txtDisc.setCaretColor(Color.WHITE);
			txtDisc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String value = txtDisc.getText();
					if(!value.equals("")){
						validate_barcode(company, value, username,store_name, returns,con);
					}
					txtDisc.setText("");
					//txtDisc.requestFocus();
					get_user_validate_dt(company, username,store_name,posreg);
					txtDisc.setBackground(Color.BLACK);
					txtDisc.setCaretColor(Color.WHITE);
					txtDisc.setForeground(Color.WHITE);
				}
			});
			txtDisc.setBackground(Color.BLACK);
			txtDisc.setForeground(Color.WHITE);
			txtDisc.setBounds(6, 6, 278, 60);
			panel.add(txtDisc);
			txtDisc.setColumns(10);
			
			button_4 = new JButton("100");
			button_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					cash_settle(company, username, store_name, posreg,version,"100");
					
				}
			});
			button_4.setForeground(Color.DARK_GRAY);
			button_4.setFont(new Font("Arial", Font.BOLD, 25));
			button_4.setBorder(new LineBorder(SystemColor.control));
			button_4.setBackground(SystemColor.menu);
			button_4.setBounds(6, 304, 65, 50);
			panel.add(button_4);
			
			button_5 = new JButton("200");
			button_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cash_settle(company, username, store_name, posreg,version,"200");
				}
			});
			button_5.setForeground(Color.DARK_GRAY);
			button_5.setFont(new Font("Arial", Font.BOLD, 25));
			button_5.setBorder(new LineBorder(SystemColor.control));
			button_5.setBackground(SystemColor.menu);
			button_5.setBounds(78, 304, 65, 50);
			panel.add(button_5);
			
			button_6 = new JButton("500");
			button_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cash_settle(company, username, store_name, posreg,version,"500");
				}
			});
			button_6.setForeground(Color.DARK_GRAY);
			button_6.setFont(new Font("Arial", Font.BOLD, 25));
			button_6.setBorder(new LineBorder(SystemColor.control));
			button_6.setBackground(SystemColor.menu);
			button_6.setBounds(147, 304, 65, 50);
			panel.add(button_6);
			
			btnPayCode = new JButton("PAY CODE (F12)");
			btnPayCode.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					paycode_settle(company, username, store_name, posreg,version);
					
		    	}
			});
			btnPayCode.setForeground(Color.DARK_GRAY);
			btnPayCode.setFont(new Font("Arial", Font.BOLD, 15));
			btnPayCode.setBorder(new LineBorder(SystemColor.control));
			btnPayCode.setBackground(new Color(100, 149, 237));
			btnPayCode.setBounds(9, 472, 132, 50);
			panel.add(btnPayCode);
			
			btnCash = new JButton("CASH(F10)");
			btnCash.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cash_settle(company, username, store_name, posreg,version,"");
					
				}
			});
			btnCash.setToolTipText("");
			btnCash.setForeground(Color.DARK_GRAY);
			btnCash.setFont(new Font("Arial", Font.BOLD, 20));
			btnCash.setBorder(new LineBorder(SystemColor.control));
			btnCash.setBackground(new Color(124, 252, 0));
			btnCash.setBounds(149, 472, 132, 50);
			panel.add(btnCash);
			
			btnAR = new JButton("A / R (F11)");
			btnAR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Cust_Chooser.Active="1";
					ar_settle(company, username, store_name, posreg,con,version);
					
				}
			});
			btnAR.setBorder(new LineBorder(new Color(128, 128, 128), 2));
			btnAR.setForeground(Color.DARK_GRAY);
			btnAR.setFont(new Font("Arial", Font.BOLD, 20));
			btnAR.setBorder(new LineBorder(SystemColor.control));
			btnAR.setBackground(new Color(255, 99, 71));
			btnAR.setBounds(151, 416, 130, 50);
			panel.add(btnAR);
			
			button_9 = new JButton("1000");
			button_9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cash_settle(company, username, store_name, posreg,version,"1000");
				}
			});
			button_9.setForeground(Color.DARK_GRAY);
			button_9.setFont(new Font("Arial", Font.BOLD, 20));
			button_9.setBorder(new LineBorder(SystemColor.control));
			button_9.setBackground(SystemColor.menu);
			button_9.setBounds(219, 304, 65, 50);
			panel.add(button_9);
			
			btnDisc = new JButton("<HTML>DISC %</HTML>");
			btnDisc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					disc_perc(company, username, store_name, posreg,txtDisc.getText().trim(),"");
					
				}
			});
			btnDisc.setForeground(Color.DARK_GRAY);
			btnDisc.setFont(new Font("Arial", Font.BOLD, 15));
			btnDisc.setBorder(new LineBorder(SystemColor.control));
			btnDisc.setBackground(new Color(255, 215, 0));
			btnDisc.setBounds(9, 416, 60, 50);
			panel.add(btnDisc);
			
			btnDiscAmt = new JButton("<HTML>DISC.   <BR>  \r\n AMT.</HTML>");
			btnDiscAmt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					disc_amt(company, username, store_name, posreg);
					
				}
			});
			btnDiscAmt.setForeground(Color.DARK_GRAY);
			btnDiscAmt.setFont(new Font("Arial", Font.BOLD, 13));
			btnDiscAmt.setBorder(new LineBorder(SystemColor.control));
			btnDiscAmt.setBackground(new Color(255, 215, 0));
			btnDiscAmt.setBounds(79, 416, 60, 50);
			panel.add(btnDiscAmt);
			
			JButton btnC = new JButton("<HTML>ENTER    <BR>  \r\n</HTML>");
			btnC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String value = txtDisc.getText();
					if(!value.equals("")){
						validate_barcode(company, value, username,store_name, returns,con);
					}
					txtDisc.setText("");
					txtDisc.requestFocus();
					get_user_validate_dt(company, username,store_name,posreg);
					txtDisc.setBackground(Color.BLACK);
					txtDisc.setCaretColor(Color.WHITE);
					txtDisc.setForeground(Color.WHITE);
				}
			});
			btnC.setIcon(null);
			btnC.setForeground(Color.WHITE);
			btnC.setFont(new Font("Arial", Font.BOLD, 14));
			btnC.setBorder(new LineBorder(SystemColor.control));
			btnC.setBackground(new Color(100, 149, 237));
			btnC.setBounds(219, 130, 65, 52);
			panel.add(btnC);
			
			//Image barcode=new ImageIcon(this.getClass().getResource("/Barcode.png")).getImage();
			btnPlu = new JButton("SEARCH(F9)");
			btnPlu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getitemlist(company, username,store_name,posreg,version,decimal,con,language);
					
				}
			});
			btnPlu.setBounds(219, 246, 65, 52);
			panel.add(btnPlu);
			btnPlu.setHorizontalTextPosition(JButton.CENTER);
			btnPlu.setVerticalTextPosition(JButton.BOTTOM);
			//btnPlu.setIcon(new ImageIcon(barcode));
			btnPlu.setIcon(new ImageIcon(serch));
			btnPlu.setForeground(Color.WHITE);
			btnPlu.setFont(new Font("Arial", Font.BOLD, 10));
			btnPlu.setBorder(new LineBorder(Color.WHITE));
			btnPlu.setBackground(Color.DARK_GRAY);
			
			Image qty = new ImageIcon(this.getClass().getResource("/Science-Multiply-icon.png")).getImage();
			btnX = new JButton("QTY.(F4)");
			btnX.setEnabled(false);
			btnX.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					qtyupdate(company, username,store_name,posreg,version);
				}
			});
			btnX.setBounds(219, 188, 65, 52);
			panel.add(btnX);
			btnX.setHorizontalTextPosition(JButton.CENTER);
			btnX.setVerticalTextPosition(JButton.BOTTOM);
			btnX.setIcon(new ImageIcon(qty));
			btnX.setForeground(Color.WHITE);
			btnX.setFont(new Font("Arial", Font.BOLD, 12));
			btnX.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnX.setBackground(Color.DARK_GRAY);
			
			Image void_img = new ImageIcon(this.getClass().getResource("/delete-file-icon (1).png")).getImage();
			btnVoid = new JButton("VOID (F6)");
			btnVoid.setEnabled(false);
			btnVoid.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getvoid( company,  username, store_name, posreg,con, version);
					
				}
			});
			btnVoid.setIcon(new ImageIcon(void_img));
			btnVoid.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnVoid.setHorizontalTextPosition(SwingConstants.CENTER);
			btnVoid.setForeground(Color.WHITE);
			btnVoid.setFont(new Font("Arial", Font.BOLD, 12));
			btnVoid.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnVoid.setBackground(Color.DARK_GRAY);
			btnVoid.setBounds(6, 246, 65, 52);
			panel.add(btnVoid);
			
			btnStoreCredit = new JButton("<HTML>STORE<BR>CREDIT</HTML>");
			btnStoreCredit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(Float.parseFloat(textField_3.getText())==0)
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "No Amount to Proceed");
						posscreen.txtDisc.requestFocus();
					}
					else
					{
						if(Float.parseFloat(textField_3.getText())>0)
						{
							//apply
							try {
								new sales_master.Store_credit_list(company, username, version, textField_3.getText(), store_name,"POS",decimal);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								txtDisc.requestFocus();
							}
						}
						else
						{
							//issue
							try {
								//company,username, version,reg_nme,amount,cust_name,cust_code,store,store_credit_validity,invoice_num
								new sales_master.Store_credit_issue(company, username, version,reg_nme, textField_3.getText(), btnNewButton.getText(), "", store_name, store_credit_validity, textField_8.getText(), language);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								txtDisc.requestFocus();
							}
						}	
					}
				}
			});
			btnStoreCredit.setEnabled(false);
			btnStoreCredit.setBounds(9, 360, 132, 50);
			btnStoreCredit.setBorder(new LineBorder(new Color(128, 128, 128), 2));
			btnStoreCredit.setForeground(Color.DARK_GRAY);
			btnStoreCredit.setFont(new Font("Arial", Font.BOLD, 20));
			btnStoreCredit.setBorder(new LineBorder(SystemColor.control));
			btnStoreCredit.setBackground(new Color(30, 144, 255));
			panel.add(btnStoreCredit);
			
			btnQuickReturn_1 = new JButton("QUICK RETURN (F8)");
			btnQuickReturn_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					return_list(company, username, store_name, posreg);
				}
			});
			btnQuickReturn_1.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnQuickReturn_1.setHorizontalTextPosition(SwingConstants.CENTER);
			btnQuickReturn_1.setForeground(Color.WHITE);
			btnQuickReturn_1.setFont(new Font("Arial", Font.BOLD, 12));
			btnQuickReturn_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnQuickReturn_1.setBackground(Color.DARK_GRAY);
			btnQuickReturn_1.setIcon(new ImageIcon(remove));
			btnQuickReturn_1.setBounds(149, 360, 132, 50);
			panel.add(btnQuickReturn_1);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportBorder(null);
			scrollPane.setBounds(5, 10, 700, 421);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBackground(Color.BLACK);
			frmRegisterNoS.getContentPane().add(scrollPane);
			
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
			table.setShowGrid(false);
			table.setForeground(Color.WHITE);
			table.setRowHeight(25);
			//table.setBorder(new LineBorder(Color.WHITE));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setFont(new Font("Arial", Font.BOLD, 15));
			table.setBackground(Color.DARK_GRAY);
			table.setSelectionBackground(Color.red);
			table.getTableHeader().setReorderingAllowed(false);
			//table.getTableHeader().setResizingAllowed(false);
			
			
			model.addColumn("S.No.");
		    model.addColumn("Barcode"); 
		    model.addColumn("Item Description"); 
		    model.addColumn("Unit"); 
		    model.addColumn("<html>Unit <br> Price</html>");
		    model.addColumn("Qty");
		    model.addColumn("<html>Disc<br>%</html>");
		    model.addColumn("Total");
		    model.addColumn("VAT");
		    model.addColumn("Net Total");
		    model.addColumn("<html>VAT<br>Type</html>");
		    model.addColumn("<html>cost</html>");
		    model.addColumn("<html>sp</html>");
		    model.addColumn("item_code");
		    model.addColumn("committed_qty");
		    model.addColumn("disc_type");
		    model.addColumn("vat_perc");
		    model.addColumn("status");
		    model.addColumn("dept");
		    model.addColumn("kot_number");
		    model.addColumn("reg_num");
		    model.addColumn("comments");
		    model.addColumn("cf");
		    model.addColumn("print_sts");
		    model.addColumn("scale_sts");
		    model.addColumn("chkbill_sts");
		    
		    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		    JTableHeader header=table.getTableHeader();
		    header.setBackground(Color.DARK_GRAY);
		    header.setForeground(Color.WHITE);
		    header.setPreferredSize(new Dimension(100,35));
		    int columns=model.getColumnCount();
			for (int i=0; i<columns; i++) {
				  TableColumn column = table.getColumnModel().getColumn(i);
				  if (i==0) column.setPreferredWidth(40);
				  if (i==1) column.setPreferredWidth(110);
				  if (i==2) column.setPreferredWidth(170);
				  if (i==3) column.setPreferredWidth(40);
				  if (i==4) column.setPreferredWidth(60);
				  if (i==5) column.setPreferredWidth(50);
				  if (i==6) column.setPreferredWidth(50);
				  if (i==7) column.setPreferredWidth(60);
				  if (i==8) column.setPreferredWidth(40);
				  if (i==9) column.setPreferredWidth(60);
				  if (i==10) column.setPreferredWidth(50);
				}
			
			scrollPane.setViewportView(table);
			
			table.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent mouseEvent) {
	                JTable table =(JTable) mouseEvent.getSource();
	                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
	                	int sel_table_column=table.getSelectedColumn();
	                	if(sel_table_column==0)
	                	{
	                		int column = 21;
	                    	int rows = table.getSelectedRow();
	                    	String value = table.getModel().getValueAt(rows, column).toString();
	                    	try {
								new comment_item(company,value.trim());
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                	}
	                	if(sel_table_column==6)
	                	{
	                		new sales_master.Item_disc( company, username, store_name, reg_nme, version);
	                	}
	                	
	                }
	            }
	        });
			
			table.setDefaultRenderer(Object.class, new TableCellRenderer(){
	            private DefaultTableCellRenderer DEFAULT_RENDERER =  new DefaultTableCellRenderer();
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	                if (row%2 == 0){
	                    c.setBackground(Color.WHITE);
	                    c.setForeground(Color.BLACK);
	                }
	                else {
	                    c.setBackground(Color.LIGHT_GRAY);
	                    c.setForeground(Color.BLACK);
	                }
	                if (isSelected) {
	                	c.setBackground(Color.YELLOW);
	                }
	                return c;
	            }
	        });
			
			
			panel_7 = new JPanel();
			panel_7.setBorder(new LineBorder(new Color(100, 149, 237), 3, true));
			panel_7.setBackground(Color.DARK_GRAY);
			panel_7.setBounds(10, 565, 688, 153);
			frmRegisterNoS.getContentPane().add(panel_7);
			panel_7.setLayout(null);
			
			Image receipt = new ImageIcon(this.getClass().getResource("/Receipt-icon (1).png")).getImage();
			btnHold = new JButton("HOLD\r\n INV.(F3)");
			btnHold.setEnabled(false);
			btnHold.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					hold_inv(company, username, store_name, posreg,con,"",version,"","",textField_8.getText(),"0");	
				}
			});
			btnHold.setIcon(new ImageIcon(receipt));
			btnHold.setHorizontalTextPosition(JButton.CENTER);
			btnHold.setVerticalTextPosition(JButton.BOTTOM);
			btnHold.setForeground(Color.WHITE);
			btnHold.setFont(new Font("Arial", Font.BOLD, 8));
			btnHold.setBorder(new LineBorder(Color.WHITE));
			btnHold.setBackground(Color.DARK_GRAY);
			btnHold.setBounds(586, 11, 90, 60);
			panel_7.add(btnHold);
			
			Image unhold_receipt = new ImageIcon(this.getClass().getResource("/unhold receipt.png")).getImage();
			btnUnhold = new JButton("UN-HOLD(F1)");
			btnUnhold.setEnabled(false);
			btnUnhold.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					unhold_inv(company, username, store_name, posreg,version);
					//btnUnhold.setEnabled(false);
				}
			});
			btnUnhold.setIcon(new ImageIcon(unhold_receipt));
			btnUnhold.setHorizontalTextPosition(JButton.CENTER);
			btnUnhold.setVerticalTextPosition(JButton.BOTTOM);
			btnUnhold.setForeground(Color.WHITE);
			btnUnhold.setFont(new Font("Arial", Font.BOLD, 8));
			btnUnhold.setBorder(new LineBorder(new Color(255, 255, 255)));
			btnUnhold.setBackground(Color.DARK_GRAY);
			btnUnhold.setBounds(586, 82, 90, 60);
			panel_7.add(btnUnhold);
			
			Image cash = new ImageIcon(this.getClass().getResource("/cash-icon.png")).getImage();
			btnPrice = new JButton("PRICE(F2)");
			btnPrice.setEnabled(false);
			btnPrice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getpricechange(company, username, store_name, posreg, version);
					
				}
			});
			btnPrice.setIcon(new ImageIcon(cash));
			btnPrice.setHorizontalTextPosition(JButton.CENTER);
			btnPrice.setVerticalTextPosition(JButton.BOTTOM);
			btnPrice.setForeground(Color.WHITE);
			btnPrice.setFont(new Font("Arial", Font.BOLD, 12));
			btnPrice.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnPrice.setBackground(Color.DARK_GRAY);
			btnPrice.setBounds(370, 82, 62, 60);
			panel_7.add(btnPrice);
			
			btnDeleteLine = new JButton("<HTML>DELETE    <BR>  \r\n LINE (INS+DEL)</HTML>");
			btnDeleteLine.setEnabled(false);
			btnDeleteLine.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					delete(company, username, store_name, posreg, version);
				}
			});
			btnDeleteLine.setIcon(new ImageIcon(void_img));
			btnDeleteLine.setHorizontalTextPosition(JButton.CENTER);
			btnDeleteLine.setVerticalTextPosition(JButton.BOTTOM);
			btnDeleteLine.setForeground(Color.WHITE);
			btnDeleteLine.setFont(new Font("Arial", Font.BOLD, 8));
			btnDeleteLine.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnDeleteLine.setBackground(Color.DARK_GRAY);
			btnDeleteLine.setBounds(154, 82, 62, 60);
			panel_7.add(btnDeleteLine);
			
			Image price = new ImageIcon(this.getClass().getResource("/price.png")).getImage();
			btnQuickReturn = new JButton("<HTML>PRICE <BR>ENQUIRY(F5)</HTML>");
			btnQuickReturn.setEnabled(false);
			btnQuickReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						btnQuickReturn.setEnabled(false);
						price_enqury(db_name,company,username,store_name,version, con, language);
					} catch (JSONException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						txtDisc.requestFocus();
					}
				}
			});
			btnQuickReturn.setIcon(new ImageIcon(price));
			btnQuickReturn.setHorizontalTextPosition(JButton.CENTER);
			btnQuickReturn.setVerticalTextPosition(JButton.BOTTOM);
			btnQuickReturn.setForeground(Color.WHITE);
			btnQuickReturn.setFont(new Font("Arial", Font.BOLD, 8));
			btnQuickReturn.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnQuickReturn.setBackground(Color.DARK_GRAY);
			btnQuickReturn.setBounds(154, 11, 62, 60);
			panel_7.add(btnQuickReturn);
			
			
			
			Image refresh = new ImageIcon(this.getClass().getResource("/invoice.png")).getImage();
			JButton btnSearchItem = new JButton("CHK BILL");
			btnSearchItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
//					item_list_master.get_symc_itms(company);
//					posscreen.txtDisc.requestFocus();
					
					posscreen.page_type="Hold Invoice";
					posscreen.hold_inv=true;
					if(change_hold_type.equals("1"))
					{
						String a=label.getText();
						String b=label_1.getText();
						String c=label_2.getText();
						if(label.getText().equals(""))
						{
							a="QUICK DINE IN";
						}
						if(label_1.getText().equals(""))
						{
							b="";
						}
						if(label_2.getText().equals(""))
						{
							c="QUICK DINE IN";
							posscreen.page_type="Kitchen KOT";
						}
						posscreen.hold_inv(company, username,store_name, posreg, con,a,version,b,c,textField_8.getText(),"1");
					}
					else
					{
						posscreen.hold_inv(company, username,store_name, posreg, con,label.getText(),version,label_1.getText(),label_2.getText(),textField_8.getText(),"1");	
					}
					
				}
			});
			btnSearchItem.setHorizontalTextPosition(JButton.CENTER);
			btnSearchItem.setVerticalTextPosition(JButton.BOTTOM);
			btnSearchItem.setIcon(new ImageIcon(refresh));
			btnSearchItem.setForeground(Color.WHITE);
			btnSearchItem.setFont(new Font("Arial", Font.BOLD, 12));
			btnSearchItem.setBorder(new LineBorder(Color.WHITE));
			btnSearchItem.setBackground(Color.DARK_GRAY);
			btnSearchItem.setBounds(442, 11, 62, 60);
			panel_7.add(btnSearchItem);
			
			Image Fast_forward = new ImageIcon(this.getClass().getResource("/Fast-forward-icon.png")).getImage();
			btnFastItems = new JButton("<HTML>FAST  <BR>  \r\n ITEMS</HTML>");
			btnFastItems.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						btnFastItems.setEnabled(false);
						if(fastitem_mode.equals("1"))
						{
							
							new sales_master.fastitems(company,store_name, version,username,returns);
						}
						else
						{
							new sales_master.fast_item_normal(company,store_name, version,username,returns);
						}
						//new sales_master.fastitems(company,store_name, version,username,returns);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						posscreen.txtDisc.requestFocus();
					}
				}
			});
			btnFastItems.setIcon(new ImageIcon(Fast_forward));
			btnFastItems.setHorizontalTextPosition(JButton.CENTER);
			btnFastItems.setVerticalTextPosition(JButton.BOTTOM);
			btnFastItems.setForeground(Color.WHITE);
			btnFastItems.setFont(new Font("Arial", Font.BOLD, 10));
			btnFastItems.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnFastItems.setBackground(Color.DARK_GRAY);
			btnFastItems.setBounds(370, 11, 62, 60);
			panel_7.add(btnFastItems);
			
			Image reports=new ImageIcon(this.getClass().getResource("/reports.png")).getImage();
			Image folder = new ImageIcon(this.getClass().getResource("/Folder-Options-icon.png")).getImage();
			btnMisc = new JButton("<HTML>INVOICE <BR> SPLIT</HTML>");
			//btnMisc.setEnabled(false);
			btnMisc.setIcon(new ImageIcon(reports));
			btnMisc.setHorizontalTextPosition(JButton.CENTER);
			btnMisc.setVerticalTextPosition(JButton.BOTTOM);
			btnMisc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					posscreen.page_type="Hold Invoice";
					posscreen.hold_inv=true;
					if(change_hold_type.equals("1"))
					{
						try {
							new SplitInvoice(company,  username, store_name,reg_nme, version);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
//						String a=label.getText();
//						String b=label_1.getText();
//						String c=label_2.getText();
//						if(label.getText().equals(""))
//						{
//							a="QUICK DINE IN";
//						}
//						if(label_1.getText().equals(""))
//						{
//							b="";
//						}
//						if(label_2.getText().equals(""))
//						{
//							c="QUICK DINE IN";
//							posscreen.page_type="Kitchen KOT";
//						}
//						posscreen.hold_inv(company, username,store_name, posreg, con,a,version,b,c,textField_8.getText());
					}
					else
					{
						posscreen.hold_inv(company, username,store_name, posreg, con,label.getText(),version,label_1.getText(),label_2.getText(),textField_8.getText(),"0");	
					}
					
				}
			});
			btnMisc.setForeground(Color.WHITE);
			btnMisc.setFont(new Font("Arial", Font.BOLD, 10));
			btnMisc.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnMisc.setBackground(Color.DARK_GRAY);
			btnMisc.setBounds(442, 82, 62, 60);
			panel_7.add(btnMisc);
			
			Image PayIn = new ImageIcon(this.getClass().getResource("/Pay In.png")).getImage();
			btnPayIn = new JButton("PAY IN");
			btnPayIn.setEnabled(false);
			btnPayIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table.getRowCount()==0)
					{
						manager_name=username;
						
						try {
							new sales_master.pay_in(company,  username, store_name, reg_nme, version,printer);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						get_user_validate_dt( company, username,store_name,posreg);
						btnPayIn.setEnabled(false);
					}
					else
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "Table Not Empty , First Settle Bill");
						posscreen.txtDisc.requestFocus();
					}
					
				}
			});
			btnPayIn.setIcon(new ImageIcon(PayIn));
			btnPayIn.setHorizontalTextPosition(JButton.CENTER);
			btnPayIn.setVerticalTextPosition(JButton.BOTTOM);
			btnPayIn.setForeground(Color.WHITE);
			btnPayIn.setFont(new Font("Arial", Font.BOLD, 12));
			btnPayIn.setBorder(new LineBorder(new Color(192, 192, 192)));
			btnPayIn.setBackground(Color.DARK_GRAY);
			btnPayIn.setBounds(298, 11, 62, 60);
			panel_7.add(btnPayIn);
			
			Image payout = new ImageIcon(this.getClass().getResource("/Apps-drawer-icon.png")).getImage();
			btnPayOut = new JButton("PAY OUT");
			btnPayOut.setEnabled(false);
			btnPayOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table.getRowCount()==0)
					{
						manager_name=username;
						
						try {
							new sales_master.pay_out(company,  username, store_name, reg_nme, version,printer);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						get_user_validate_dt( company, username,store_name,posreg);
						btnPayOut.setEnabled(false);
					}
					else
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "Table Not Empty , First Settle Bill");
						posscreen.txtDisc.requestFocus();
					}
				}
			});
			btnPayOut.setIcon(new ImageIcon(payout));
			btnPayOut.setHorizontalTextPosition(JButton.CENTER);
			btnPayOut.setVerticalTextPosition(JButton.BOTTOM);
			btnPayOut.setToolTipText("");
			btnPayOut.setForeground(Color.WHITE);
			btnPayOut.setFont(new Font("Arial", Font.BOLD, 12));
			btnPayOut.setBorder(new LineBorder(new Color(192, 192, 192)));
			btnPayOut.setBackground(Color.DARK_GRAY);
			btnPayOut.setBounds(298, 82, 62, 60);
			panel_7.add(btnPayOut);
			
			Image client = new ImageIcon(this.getClass().getResource("/Clients-icon.png")).getImage();
			btnNewCustomer = new JButton("<HTML>NEW    <BR>  \r\nCUST.</HTML>");
			btnNewCustomer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					manager_name=username;
					
					new sales_master.Customer_master(company, username,reg_nme,store_name, version,"POS",language);
					Customer_master.Active="1";
					get_user_validate_dt( company, username,store_name,posreg);
					btnNewCustomer.setEnabled(false);
				}
			});
			btnNewCustomer.setIcon(new ImageIcon(client));
			btnNewCustomer.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnNewCustomer.setHorizontalTextPosition(SwingConstants.CENTER);
			btnNewCustomer.setForeground(Color.WHITE);
			btnNewCustomer.setFont(new Font("Arial", Font.BOLD, 10));
			btnNewCustomer.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnNewCustomer.setBackground(Color.DARK_GRAY);
			btnNewCustomer.setBounds(82, 11, 62, 60);
			panel_7.add(btnNewCustomer);
			
			Image payment = new ImageIcon(this.getClass().getResource("/payment-icon.png")).getImage();
			btncustPay = new JButton("<HTML>CUST.    <BR>  \r\nPAYMENT</HTML>");
			btncustPay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table.getRowCount()==0)
					{
						manager_name=username;
						
						new customer_payment( company, username, store_name, reg_nme, version,printer,"");
						get_user_validate_dt( company, username,store_name,posreg);
						btncustPay.setEnabled(false);
					}
					else
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "Table Not Empty , First Settle Bill");
						posscreen.txtDisc.requestFocus();
					}
					
				}
			});
			btncustPay.setIcon(new ImageIcon(payment));
			btncustPay.setVerticalTextPosition(SwingConstants.BOTTOM);
			btncustPay.setHorizontalTextPosition(SwingConstants.CENTER);
			btncustPay.setForeground(Color.WHITE);
			btncustPay.setFont(new Font("Arial", Font.BOLD, 10));
			btncustPay.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btncustPay.setBackground(Color.DARK_GRAY);
			btncustPay.setBounds(82, 82, 62, 60);
			panel_7.add(btncustPay);
			
			Image nosale = new ImageIcon(this.getClass().getResource("/NO SALE.png")).getImage();
			btnNoSale = new JButton("NO SALE");
			btnNoSale.setEnabled(false);
			btnNoSale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//byte[] open = {27,112,48,55,121};
			        PrintService pservice = PrintServiceLookup.lookupDefaultPrintService(); 
			        DocPrintJob job = pservice.createPrintJob();
			        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
			        Doc doc = new SimpleDoc(open,flavor,null);
			        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
			        try {
			            job.print(doc, aset);
						manager_name=username;
			            get_user_validate_dt( company, username,store_name,posreg);
			            txtDisc.requestFocus();
			            
			        } catch (PrintException ex) {
			            System.out.println(ex.getMessage());
			            JOptionPane.showMessageDialog(frmRegisterNoS, ex.getMessage());
			            txtDisc.requestFocus();
			        }
				}
			});
			btnNoSale.setIcon(new ImageIcon(nosale));
			btnNoSale.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnNoSale.setHorizontalTextPosition(SwingConstants.CENTER);
			btnNoSale.setForeground(Color.WHITE);
			btnNoSale.setFont(new Font("Arial", Font.BOLD, 12));
			btnNoSale.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnNoSale.setBackground(Color.DARK_GRAY);
			btnNoSale.setBounds(10, 11, 62, 60);
			panel_7.add(btnNoSale);
			
			Image salesreport = new ImageIcon(this.getClass().getResource("/Prd_sle_rpt.png")).getImage();//sales-report-icon.png
			btncustStatement = new JButton("<HTML>CUST.    <BR>  \r\n STATE.</HTML>");
			btncustStatement.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table.getRowCount()==0)
					{
						manager_name=username;
						
						new customer_statement( company, username, store_name, reg_nme, version,"");
						get_user_validate_dt( company, username,store_name,posreg);
						btncustStatement.setEnabled(false);
					}
					else
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "Table Not Empty , First Settle Bill");
						posscreen.txtDisc.requestFocus();
					}
					
				}
			});
			btncustStatement.setIcon(new ImageIcon(salesreport));
			btncustStatement.setVerticalTextPosition(SwingConstants.BOTTOM);
			btncustStatement.setHorizontalTextPosition(SwingConstants.CENTER);
			btncustStatement.setForeground(Color.WHITE);
			btncustStatement.setFont(new Font("Arial", Font.BOLD, 10));
			btncustStatement.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btncustStatement.setBackground(Color.DARK_GRAY);
			btncustStatement.setBounds(10, 82, 62, 60);
			panel_7.add(btncustStatement);
			
			Image places_folder = new ImageIcon(this.getClass().getResource("/Places-folder-documents-icon.png")).getImage();
			btnduplicateBill = new JButton("<HTML>DUP.<BR>INVOICE(F7)</HTML>");
			btnduplicateBill.setEnabled(false);
			btnduplicateBill.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					dup_bill(company, username, store_name, posreg,version);
					
				}
			});
			btnduplicateBill.setIcon(new ImageIcon(places_folder));
			btnduplicateBill.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnduplicateBill.setHorizontalTextPosition(SwingConstants.CENTER);
			btnduplicateBill.setForeground(Color.WHITE);
			btnduplicateBill.setFont(new Font("Arial", Font.BOLD, 10));
			btnduplicateBill.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnduplicateBill.setBackground(Color.DARK_GRAY);
			btnduplicateBill.setBounds(226, 11, 62, 60);
			panel_7.add(btnduplicateBill);
			
			Image folder_inv = new ImageIcon(this.getClass().getResource("/folder-invoices-icon.png")).getImage();
			btnhistInvoice = new JButton("<HTML>HISTORICAL.    <BR>  \r\nINVOICE </HTML>");
			btnhistInvoice.setEnabled(false);
			btnhistInvoice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table.getRowCount()==0)
					{
						try {
							manager_name=username;
							
							new historical_invoice(company,username,store_name,reg_nme,printer, version,Integer.toString(decimalPlaces));
							get_user_validate_dt( company, username,store_name,posreg);
							btnhistInvoice.setEnabled(false);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							posscreen.txtDisc.requestFocus();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "Table Not Empty , First Settle Bill");
						posscreen.txtDisc.requestFocus();
					}
					
				}
			});
			btnhistInvoice.setIcon(new ImageIcon(folder_inv));
			btnhistInvoice.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnhistInvoice.setHorizontalTextPosition(SwingConstants.CENTER);
			btnhistInvoice.setForeground(Color.WHITE);
			btnhistInvoice.setFont(new Font("Arial", Font.BOLD, 10));
			btnhistInvoice.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnhistInvoice.setBackground(Color.DARK_GRAY);
			btnhistInvoice.setBounds(226, 82, 62, 60);
			panel_7.add(btnhistInvoice);
			
			Image Manager = new ImageIcon(this.getClass().getResource("/Manager-icon.png")).getImage();
			btnManager = new JButton("MANAGER");
			btnManager.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					btnManager.setEnabled(false);
					new sales_master.manager( company, username, store_name, reg_nme, version);
					txtDisc.requestFocus();
					
				}
			});
			btnManager.setIcon(new ImageIcon(Manager));
			btnManager.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnManager.setHorizontalTextPosition(SwingConstants.CENTER);
			btnManager.setForeground(Color.WHITE);
			btnManager.setFont(new Font("Arial", Font.BOLD, 10));
			btnManager.setBorder(new LineBorder(Color.WHITE));
			btnManager.setBackground(Color.DARK_GRAY);
			btnManager.setBounds(514, 11, 62, 60);
			panel_7.add(btnManager);
			
			btnchangeUnit = new JButton("<HTML>CHANGE    <BR>  \r\nUNIT</HTML>");
			btnchangeUnit.setEnabled(false);
			btnchangeUnit.setIcon(new ImageIcon(folder));
			btnchangeUnit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						
						if(model.getRowCount()==0)
						{
							JOptionPane.showMessageDialog(frmRegisterNoS, "Select one item to change unit");
							posscreen.txtDisc.requestFocus();
						}
						else
						{
							manager_name=username;
							String test_itemcode="";
							int rows = table.getSelectedRow();
							if(rows>=0)
							{
								test_itemcode=table.getModel().getValueAt(rows, 1).toString();
							}
							String qty=table.getModel().getValueAt(rows, 5).toString();
							btnchangeUnit.setEnabled(false);
							new change_unit(db_name,company,username,store_name, version,con,test_itemcode,qty,language);
							get_user_validate_dt( company, username,store_name,posreg);
						}
						
					} catch (JSONException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						posscreen.txtDisc.requestFocus();
					}
				}
			});
			btnchangeUnit.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnchangeUnit.setHorizontalTextPosition(SwingConstants.CENTER);
			btnchangeUnit.setForeground(Color.WHITE);
			btnchangeUnit.setFont(new Font("Arial", Font.BOLD, 10));
			btnchangeUnit.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnchangeUnit.setBackground(Color.DARK_GRAY);
			btnchangeUnit.setBounds(514, 82, 62, 60);
			panel_7.add(btnchangeUnit);
			
			 panel_11 = new JPanel();
			panel_11.setBorder(new LineBorder(new Color(100, 149, 237), 3, true));
			panel_11.setBackground(Color.DARK_GRAY);
			panel_11.setBounds(10, 436, 688, 120);
			frmRegisterNoS.getContentPane().add(panel_11);
			panel_11.setLayout(null);
			
			JLabel lblRoundOff = new JLabel("Round Off : ");
			lblRoundOff.setForeground(Color.WHITE);
			lblRoundOff.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblRoundOff.setBounds(560, 97, 70, 16);
			panel_11.add(lblRoundOff);
			
			textField_5 = new JTextField();
			textField_5.setEditable(false);
			textField_5.setHorizontalAlignment(SwingConstants.CENTER);
			textField_5.setForeground(Color.YELLOW);
			textField_5.setFont(new Font("Arial", Font.BOLD, 18));
			textField_5.setColumns(10);
			textField_5.setBorder(null);
			textField_5.setBackground(Color.DARK_GRAY);
			textField_5.setBounds(232, 43, 100, 27);
			panel_11.add(textField_5);
			textField_5.setColumns(10);
			
			textField_3 = new JTextField("0.00");
			textField_3.setEditable(false);
			textField_3.setBorder(null);
			textField_3.setFont(new Font("Arial", Font.BOLD, 30));
			textField_3.setHorizontalAlignment(SwingConstants.CENTER);
			textField_3.setForeground(Color.WHITE);
			textField_3.setBackground(Color.DARK_GRAY);
			textField_3.setBounds(555, 43, 128, 54);
			panel_11.add(textField_3);
			textField_3.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("GRAND TOTAL");
			lblNewLabel.setForeground(new Color(100, 149, 237));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel.setBounds(555, 5, 128, 35);
			panel_11.add(lblNewLabel);
			
			textField_1 = new JTextField("0.00");
			textField_1.setEditable(false);
			textField_1.setHorizontalAlignment(SwingConstants.CENTER);
			textField_1.setForeground(Color.WHITE);
			textField_1.setFont(new Font("Arial", Font.BOLD, 30));
			textField_1.setColumns(10);
			textField_1.setBorder(null);
			textField_1.setBackground(Color.DARK_GRAY);
			textField_1.setBounds(452, 43, 101, 54);
			panel_11.add(textField_1);
			
			JLabel lblTotalQty = new JLabel("VAT");
			lblTotalQty.setHorizontalAlignment(SwingConstants.CENTER);
			lblTotalQty.setForeground(new Color(100, 149, 237));
			lblTotalQty.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTotalQty.setBounds(452, 5, 101, 35);
			panel_11.add(lblTotalQty);
			
			textField_2 = new JTextField("0");
			textField_2.setEditable(false);
			textField_2.setHorizontalAlignment(SwingConstants.CENTER);
			textField_2.setForeground(Color.WHITE);
			textField_2.setFont(new Font("Arial", Font.BOLD, 30));
			textField_2.setColumns(10);
			textField_2.setBorder(null);
			textField_2.setBackground(Color.DARK_GRAY);
			textField_2.setBounds(232, 70, 100, 27);
			panel_11.add(textField_2);
			
			JLabel lblTotalItems = new JLabel("<html>TOTAL QTY.</html>");
			lblTotalItems.setHorizontalAlignment(SwingConstants.CENTER);
			lblTotalItems.setForeground(new Color(100, 149, 237));
			lblTotalItems.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTotalItems.setBounds(2, 5, 110, 35);
			panel_11.add(lblTotalItems);
			
			JLabel lblDiscount = new JLabel("DISC");
			lblDiscount.setHorizontalAlignment(SwingConstants.CENTER);
			lblDiscount.setForeground(new Color(100, 149, 237));
			lblDiscount.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblDiscount.setBounds(232, 5, 100, 35);
			panel_11.add(lblDiscount);
			
			JLabel lblTotal = new JLabel("SUB TOTAL");
			lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
			lblTotal.setForeground(new Color(100, 149, 237));
			lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTotal.setBounds(334, 5, 116, 35);
			panel_11.add(lblTotal);
			
			lblNewLabel_1 = new JLabel("0");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setBounds(640, 98, 48, 16);
			panel_11.add(lblNewLabel_1);
			
			textField_4 = new JTextField("0");
			textField_4.setEditable(false);
			textField_4.setHorizontalAlignment(SwingConstants.CENTER);
			textField_4.setForeground(Color.WHITE);
			textField_4.setFont(new Font("Arial", Font.BOLD, 30));
			textField_4.setColumns(10);
			textField_4.setBorder(null);
			textField_4.setBackground(Color.DARK_GRAY);
			textField_4.setBounds(2, 43, 110, 54);
			panel_11.add(textField_4);
			
			textField = new JTextField("0.00");
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setForeground(Color.WHITE);
			textField.setFont(new Font("Arial", Font.BOLD, 30));
			textField.setEditable(false);
			textField.setColumns(10);
			textField.setBorder(null);
			textField.setBackground(Color.DARK_GRAY);
			textField.setBounds(334, 43, 116, 54);
			panel_11.add(textField);
			
			JLabel lblBeforetotal = new JLabel("TOTAL");
			lblBeforetotal.setHorizontalAlignment(SwingConstants.CENTER);
			lblBeforetotal.setForeground(new Color(100, 149, 237));
			lblBeforetotal.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblBeforetotal.setBounds(114, 5, 116, 35);
			panel_11.add(lblBeforetotal);
			
			textField_11 = new JTextField("0.00");
			textField_11.setHorizontalAlignment(SwingConstants.CENTER);
			textField_11.setForeground(Color.WHITE);
			textField_11.setFont(new Font("Arial", Font.BOLD, 30));
			textField_11.setEditable(false);
			textField_11.setColumns(10);
			textField_11.setBorder(null);
			textField_11.setBackground(Color.DARK_GRAY);
			textField_11.setBounds(114, 43, 116, 54);
			panel_11.add(textField_11);
			
			panel_1 = new JPanel();
			panel_1.setBackground(Color.DARK_GRAY);
			panel_1.setBorder(new LineBorder(new Color(100, 149, 237), 3, true));
			panel_1.setBounds(710, 0, 293, 185);
			frmRegisterNoS.getContentPane().add(panel_1);
			panel_1.setLayout(null);
			
			
			JScrollPane scrollPane1 = new JScrollPane();
			scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane1.setBounds(12, 297, 908, 256);
			scrollPane1.setBackground(new Color(243, 229, 216));
			scrollPane1.setVisible(false);
			panel_1.add(scrollPane1);

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
			    
			    model1.addColumn("item_code");
			    model1.addColumn("item_name");
			    model1.addColumn("barcode"); 
			    model1.addColumn("category"); 
			    model1.addColumn("department");
			    model1.addColumn("unit");
			    model1.addColumn("sp");
			    model1.addColumn("output_tax");
			    model1.addColumn("sp_include_tax");
			    model1.addColumn("cost");
			    model1.addColumn("qty");
			    model1.addColumn("type"); 
			    model1.addColumn("input_tax"); 
			    model1.addColumn("po_inclusive_tax");
			    model1.addColumn("activeForSale"); 
			    model1.addColumn("activeForPurchase"); 
			    model1.addColumn("cf"); 
			    model1.addColumn("input_tax_rate"); 
			    model1.addColumn("output_tax_rate"); 
			    model1.addColumn("weighing_sts"); 
			    model1.addColumn("sub_cat");
			    model1.addColumn("sub_dept");
			    
			scrollPane1.setViewportView(table1);
			
			JLabel lblReceiptNo_1 = new JLabel("RECEIPT #:");
			lblReceiptNo_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblReceiptNo_1.setForeground(Color.WHITE);
			lblReceiptNo_1.setBounds(10, 5, 64, 30);
			panel_1.add(lblReceiptNo_1);
			
			JLabel lblCustomer = new JLabel("CUSTOMER:");
			lblCustomer.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblCustomer.setForeground(Color.WHITE);
			lblCustomer.setBounds(10, 40, 64, 30);
			panel_1.add(lblCustomer);
			
			textField_8 = new JTextField(Integer.toString(receipt_num));
			textField_8.setEditable(false);
			textField_8.setBackground(Color.DARK_GRAY);
			textField_8.setForeground(Color.GREEN);
			textField_8.setFont(new Font("Arial", Font.BOLD, 20));
			textField_8.setHorizontalAlignment(SwingConstants.CENTER);
			textField_8.setColumns(10);
			textField_8.setBounds(84, 4, 200, 30);
			panel_1.add(textField_8);
			
			textField_6 = new JTextField();
			textField_6.setEditable(false);
			textField_6.setBackground(Color.DARK_GRAY);
			textField_6.setForeground(Color.WHITE);
			textField_6.setFont(new Font("Arial", Font.BOLD, 20));
			textField_6.setHorizontalAlignment(SwingConstants.CENTER);
			textField_6.setColumns(10);
			textField_6.setBounds(84, 128, 200, 30);
			panel_1.add(textField_6);
			
			JLabel lblCustomerBalance = new JLabel("<HTML>CUSTOMER    <BR>  \r\n BALANCE:</HTML>");
			lblCustomerBalance.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblCustomerBalance.setVerticalTextPosition(SwingConstants.CENTER);
			lblCustomerBalance.setHorizontalTextPosition(SwingConstants.CENTER);
			lblCustomerBalance.setForeground(Color.WHITE);
			lblCustomerBalance.setBounds(12, 130, 64, 30);
			panel_1.add(lblCustomerBalance);
			
			timeLabel  = new JLabel("");
			timeLabel .setBounds(68, 700, 293, 16);
			panel_1.add(timeLabel );
			
			btnNewButton = new JButton("WALK IN");
			btnNewButton.setForeground(Color.WHITE);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					get_cust_chooser( company, version, username, store_name, posreg, con);
				}
			});
			btnNewButton.setBackground(Color.DARK_GRAY);
			btnNewButton.setBounds(84, 34, 200, 30);
			panel_1.add(btnNewButton);
			
			textField_9 = new JTextField("1");
			
//			textField_9.addMouseListener(new MouseAdapter() {
//				public void mouseClicked(MouseEvent arg0) {
//					get_cust_chooser( company, version, username, store_name, posreg, con);
//				}
//			});
			textField_9.setHorizontalAlignment(SwingConstants.CENTER);
			textField_9.setForeground(Color.WHITE);
			textField_9.setFont(new Font("Arial", Font.PLAIN, 13));
			textField_9.setEditable(false);
			textField_9.setColumns(10);
			textField_9.setVisible(false);
			textField_9.setBackground(Color.DARK_GRAY);
			textField_9.setBounds(84, 72, 200, 30);
			panel_1.add(textField_9);
			
			textField_10 = new JTextField("0");
			textField_10.setEditable(false);
			textField_10.setVisible(false);
	        textField_10.setBounds(10, 729, 86, 20);
	        frmRegisterNoS.getContentPane().add(textField_10);
	        textField_10.setColumns(10);
	        
	        textField_7 = new JTextField();
	        textField_7.setEditable(false);
	        textField_7.setBounds(106, 728, 116, 22);
	        frmRegisterNoS.getContentPane().add(textField_7);
	        textField_7.setText("0");
	        textField_7.setVisible(false);
	        textField_7.setColumns(10);
	        
	        
	        JLabel lblCustomerDiscApplied = new JLabel("Customer Disc Applied : ");
	        lblCustomerDiscApplied.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblCustomerDiscApplied.setBounds(84, 162, 146, 14);
			panel_1.add(lblCustomerDiscApplied);
			lblCustomerDiscApplied.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCustomerDiscApplied.setForeground(Color.DARK_GRAY);
	        
	        lblNewLabel_2 = new JLabel("0%");
			lblNewLabel_2.setForeground(Color.DARK_GRAY);
			lblNewLabel_2.setBounds(238, 162, 46, 14);
			panel_1.add(lblNewLabel_2);
			
			JLabel lblMode = new JLabel("MODE : ");
			lblMode.setHorizontalAlignment(SwingConstants.CENTER);
			lblMode.setForeground(Color.WHITE);
			lblMode.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblMode.setBounds(1, 104, 73, 20);
			panel_1.add(lblMode);
			
			label = new JLabel("");
			label.setForeground(Color.WHITE);
			label.setFont(new Font("Tahoma", Font.PLAIN, 10));
			label.setBounds(149, 104, 65, 20);
			panel_1.add(label);
			
			label_1 = new JLabel("");
			label_1.setForeground(Color.WHITE);
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
			label_1.setBounds(219, 104, 65, 20);
			panel_1.add(label_1);
			
			label_2 = new JLabel("");
			label_2.setForeground(Color.WHITE);
			label_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
			label_2.setBounds(79, 104, 65, 20);
			panel_1.add(label_2);
			
			JLabel lblCode = new JLabel("PHONE:");
			lblCode.setForeground(Color.WHITE);
			lblCode.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblCode.setBounds(12, 72, 64, 30);
			panel_1.add(lblCode);
			
			textField_12 = new JTextField("0");
			textField_12.setHorizontalAlignment(SwingConstants.CENTER);
			textField_12.setForeground(Color.WHITE);
			textField_12.setFont(new Font("Arial", Font.PLAIN, 13));
			textField_12.setEditable(false);
			textField_12.setColumns(10);
			textField_12.setBackground(Color.DARK_GRAY);
			textField_12.setBounds(84, 72, 200, 30);
			panel_1.add(textField_12);
			
			final DateFormat timeFormat = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss a");
	        ActionListener timerListener = new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	                Date date = new Date();
	                time = timeFormat.format(date);
	                timeLabel.setText(time);
	                frmRegisterNoS.setTitle("REGISTER NO:"+reg_nme+"  DATE:"+time+"  USER:"+manager_name+"   DATABASE:"+company+"   HOST:"+server+"  STATUS:"+status);
	            }
	        };
	        Timer timer = new Timer(1000, timerListener);
	        timer.setInitialDelay(0);
	        timer.start();
	        
	        get_user_validate_dt(company,username,store_name,posreg);
	        txtDisc.requestFocusInWindow();
	        //txtDisc.requestFocus();
	        get_register_name(company,username, store_name,con,posreg);
	        
	        if(print_duplicate_invoice.equals("1"))
			{
				copy=2;
			}
	        
	        if(CustDisc.equals("1"))
	        {
	        	
	    		lblCustomerDiscApplied.setForeground(Color.WHITE);
	    		
	    		lblNewLabel_2.setForeground(Color.WHITE);
	    		
	        }
	        
	        
	        try {
//	        	Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
//				Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO "+ready_msg1+""+ready_msg2+">"+display_port+"\""});
	        	Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+posscreen.ready_msg1+">"+display_port+"&&ECHO "+posscreen.ready_msg2+">"+display_port+"\"");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	       // get_items(company,con);
	        clickOnunhold( btnUnhold, "play", KeyEvent.VK_F1,user_name, company, store_name, posreg,version );
	        clickOnhold( btnHold, "play", KeyEvent.VK_F3,user_name, company, store_name, posreg,con,version );
	        clickOnprice( btnPrice, "play", KeyEvent.VK_F2,user_name, company, store_name, posreg , version);
	        clickOnqty( btnX, "play", KeyEvent.VK_F4,user_name, company, store_name, posreg,version );
	        clickOnprice_enq( btnQuickReturn, "play", KeyEvent.VK_F5,user_name, company, store_name, posreg,db_name,version,con,language );
	        clickOnvoid( btnVoid, "play", KeyEvent.VK_F6,user_name, company, store_name, posreg,con,version );
	        clickOndup( btnduplicateBill, "play", KeyEvent.VK_F7,user_name, company, store_name, posreg,version);
	        clickOnrtn( btnQuickReturn_1, "play", KeyEvent.VK_F8,user_name, company, store_name, posreg );
	        clickOnserch( btnPlu, "play", KeyEvent.VK_F9,user_name, company, store_name, posreg,version,decimal,con,language );
	        clickOnCash( btnCash, "play", KeyEvent.VK_F10,user_name, company, store_name, posreg,version,con );
	        clickOnar( btnAR, "play", KeyEvent.VK_F11,user_name, company, store_name, posreg,con,version );
	        clickOnpaycode( btnPayCode, "play", KeyEvent.VK_F12,user_name, company, store_name, posreg,db_name,version,con );
	        clickOndel( btnDeleteLine, "play", KeyEvent.VK_DELETE,user_name, company, store_name, posreg,version );
	        clickOnback( btnMisc, "play", KeyEvent.VK_ESCAPE );
		}
		else
		{
			frmRegisterNoS.setForeground(Color.DARK_GRAY);
			frmRegisterNoS.getContentPane().setForeground(Color.DARK_GRAY);
			frmRegisterNoS.getContentPane().setFont(new Font("Arial", Font.BOLD, 40));
			frmRegisterNoS.getContentPane().setBackground(Color.DARK_GRAY);
			frmRegisterNoS.setBounds(00, 00, width, height);
			frmRegisterNoS.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frmRegisterNoS.getContentPane().setLayout(null);
			frmRegisterNoS.setVisible(true);
			//frmRegisterNoS.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frmRegisterNoS.setResizable(false);
			
			panel = new JPanel();
			panel.setBackground(Color.DARK_GRAY);
			panel.setBounds((int)(width*0.79), (int)(height*0.20), (int)(width*0.21), (int)(height*0.72));
			panel.setBorder(new LineBorder(new Color(100, 149, 237), 3, true));
			frmRegisterNoS.getContentPane().add(panel);
			panel.setLayout(null);
			
			Image no7 = new ImageIcon(this.getClass().getResource("/Numbers-7-Black-icon.png")).getImage();
			
			
			JButton but_7 = new JButton("");
			but_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("7");
				}
			});
			but_7.setIcon(new ImageIcon(no7));
			but_7.setBounds((int)(width*0.01), (int)(height*0.06),(int)(width*0.04), (int)(height*0.07));
			panel.add(but_7);
			but_7.setForeground(Color.DARK_GRAY);
			but_7.setBorder(new LineBorder(SystemColor.control));
			but_7.setFont(new Font("Arial", Font.BOLD, (int)(width*0.02)));
			but_7.setBackground(SystemColor.control);
			
			Image no8 = new ImageIcon(this.getClass().getResource("/Numbers-8-Black-icon.png")).getImage();
			JButton but_8 = new JButton("");
			but_8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("8");
				}
			});
			but_8.setIcon(new ImageIcon(no8));
			but_8.setBorder(new LineBorder(SystemColor.control));
			but_8.setBounds((int)(width*0.06), (int)(height*0.06), (int)(width*0.04), (int)(height*0.07));
			panel.add(but_8);
			but_8.setForeground(Color.DARK_GRAY);
			but_8.setFont(new Font("Arial", Font.BOLD, (int)(width*0.02)));
			but_8.setBackground(SystemColor.control);
			
			Image no9 = new ImageIcon(this.getClass().getResource("/Numbers-9-Black-icon.png")).getImage();
			JButton but_9 = new JButton("");
			but_9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("9");
				}
			});
			but_9.setIcon(new ImageIcon(no9));
			but_9.setBorder(new LineBorder(SystemColor.control));
			but_9.setBounds((int)(width*0.11), (int)(height*0.06), (int)(width*0.04), (int)(height*0.07));
			panel.add(but_9);
			but_9.setForeground(Color.DARK_GRAY);
			but_9.setFont(new Font("Arial", Font.BOLD, (int)(width*0.02)));
			but_9.setBackground(SystemColor.control);
			
			JButton but_clear = new JButton("BKSP");
			but_clear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!txtDisc.getText().equals(""))
					{
						txtDisc.setText(txtDisc.getText().substring(0, txtDisc.getText().length() - 1));
						txtDisc.requestFocus();
					}
				}
			});
			but_clear.setHorizontalTextPosition(JButton.CENTER);
			but_clear.setVerticalTextPosition(JButton.BOTTOM);
			but_clear.setIcon(new ImageIcon("E:\\eclipseKEPLER\\ICONS\\back-icon.png"));
			but_clear.setBorder(new LineBorder(SystemColor.control));
			but_clear.setBounds((int)(width*0.16), (int)(height*0.06), (int)(width*0.04), (int)(height*0.07));
			panel.add(but_clear);
			but_clear.setForeground(Color.DARK_GRAY);
			but_clear.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			but_clear.setBackground(SystemColor.control);
			
			Image no4 = new ImageIcon(this.getClass().getResource("/Numbers-4-Black-icon.png")).getImage();
			JButton but_4 = new JButton("");
			but_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("4");
				}
			});
			but_4.setIcon(new ImageIcon(no4));
			but_4.setBorder(new LineBorder(SystemColor.control));
			but_4.setBounds((int)(width*0.01), (int)(height*0.14), (int)(width*0.04), (int)(height*0.07));
			panel.add(but_4);
			but_4.setForeground(Color.DARK_GRAY);
			but_4.setFont(new Font("Arial", Font.BOLD, (int)(width*0.02)));
			but_4.setBackground(SystemColor.control);
			
			Image no5 = new ImageIcon(this.getClass().getResource("/Numbers-5-Black-icon.png")).getImage();
			JButton but_5 = new JButton("");
			but_5.setIcon(new ImageIcon(no5));
			but_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("5");
				}
			});
			but_5.setBorder(new LineBorder(SystemColor.control));
			but_5.setBounds((int)(width*0.06), (int)(height*0.14), (int)(width*0.04), (int)(height*0.07));
			panel.add(but_5);
			but_5.setForeground(Color.DARK_GRAY);
			but_5.setFont(new Font("Arial", Font.BOLD, (int)(width*0.02)));
			but_5.setBackground(SystemColor.control);
			
			Image no6 = new ImageIcon(this.getClass().getResource("/Numbers-6-Black-icon.png")).getImage();
			JButton but_6 = new JButton("");
			but_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("6");
				}
			});
			but_6.setIcon(new ImageIcon(no6));
			but_6.setBorder(new LineBorder(SystemColor.control));
			but_6.setBounds((int)(width*0.11), (int)(height*0.14), (int)(width*0.04), (int)(height*0.07));
			panel.add(but_6);
			but_6.setForeground(Color.DARK_GRAY);
			but_6.setFont(new Font("Arial", Font.BOLD, (int)(width*0.02)));
			but_6.setBackground(SystemColor.control);
			
			JButton button_3 = new JButton(".");
			button_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn(".");
				}
			});
			button_3.setBorder(new LineBorder(SystemColor.control));
			button_3.setBounds((int)(width*0.11), (int)(height*0.30), (int)(width*0.04), (int)(height*0.07));
			panel.add(button_3);
			button_3.setForeground(Color.DARK_GRAY);
			button_3.setFont(new Font("Arial", Font.BOLD, (int)(width*0.02)));
			button_3.setBackground(SystemColor.control);
			
			Image no1 = new ImageIcon(this.getClass().getResource("/Numbers-1-Black-icon.png")).getImage();
			JButton but_1 = new JButton("");
			but_1.setIcon(new ImageIcon(no1));
			but_1.setBorder(new LineBorder(SystemColor.control));
			but_1.setBounds((int)(width*0.01), (int)(height*0.22), (int)(width*0.04), (int)(height*0.07));
			panel.add(but_1);
			but_1.setForeground(Color.DARK_GRAY);
			but_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("1");
				}
			});
			but_1.setFont(new Font("Arial", Font.BOLD, (int)(width*0.02)));
			but_1.setBackground(SystemColor.control);
			
			Image no2 = new ImageIcon(this.getClass().getResource("/Numbers-2-Black-icon.png")).getImage();
			JButton but_2 = new JButton("");
			but_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("2");
				}
			});
			but_2.setIcon(new ImageIcon(no2));
			but_2.setBorder(new LineBorder(SystemColor.control));
			but_2.setBounds((int)(width*0.06), (int)(height*0.22), (int)(width*0.04), (int)(height*0.07));
			panel.add(but_2);
			but_2.setForeground(Color.DARK_GRAY);
			but_2.setFont(new Font("Arial", Font.BOLD, (int)(width*0.02)));
			but_2.setBackground(SystemColor.control);
			
			Image no3 = new ImageIcon(this.getClass().getResource("/Numbers-3-Black-icon.png")).getImage();
			JButton but_3 = new JButton("");
			but_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("3");
				}
			});
			but_3.setIcon(new ImageIcon(no3));
			but_3.setBorder(new LineBorder(SystemColor.control));
			but_3.setBounds((int)(width*0.11), (int)(height*0.22), (int)(width*0.04), (int)(height*0.07));
			panel.add(but_3);
			but_3.setForeground(Color.DARK_GRAY);
			but_3.setFont(new Font("Arial", Font.BOLD, (int)(width*0.02)));
			but_3.setBackground(SystemColor.control);
			
			Image no0 = new ImageIcon(this.getClass().getResource("/Numbers-0-Black-icon.png")).getImage();
			JButton button_7 = new JButton("");
			button_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("0");
				}
			});
			button_7.setIcon(new ImageIcon(no0));
			button_7.setBorder(new LineBorder(SystemColor.control));
			button_7.setBounds((int)(width*0.06), (int)(height*0.30), (int)(width*0.04), (int)(height*0.07));
			panel.add(button_7);
			button_7.setForeground(Color.DARK_GRAY);
			button_7.setFont(new Font("Arial", Font.BOLD, (int)(width*0.02)));
			button_7.setBackground(SystemColor.control);
			
			txtDisc = new JTextField();
			txtDisc.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(java.awt.event.KeyEvent e1) {
					int keycode=e1.getKeyCode();
					if(keycode==155)
					{
						table.requestFocus();
					}
				}
			});
			txtDisc.setHorizontalAlignment(SwingConstants.CENTER);
			txtDisc.setFont(new Font("Tahoma", Font.PLAIN, (int)(width*0.02)));
			txtDisc.setCaretColor(Color.WHITE);
			txtDisc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String value = txtDisc.getText();
					if(!value.equals("")){
						validate_barcode(company, value, username,store_name, returns,con);
					}
					txtDisc.setText("");
					//txtDisc.requestFocus();
					get_user_validate_dt(company, username,store_name,posreg);
					txtDisc.setBackground(Color.BLACK);
					txtDisc.setCaretColor(Color.WHITE);
					txtDisc.setForeground(Color.WHITE);
				}
			});
			txtDisc.setBackground(Color.BLACK);
			txtDisc.setForeground(Color.WHITE);
			txtDisc.setBounds((int)(width*0.01), (int)(height*0.01), (int)(width*0.19), (int)(height*0.04));
			panel.add(txtDisc);
			txtDisc.setColumns(10);
			
			button_4 = new JButton("100");
			button_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					cash_settle(company, username, store_name, posreg,version,"100");
					
				}
			});
			button_4.setForeground(Color.DARK_GRAY);
			button_4.setFont(new Font("Arial", Font.BOLD, (int)(width*0.02)));
			button_4.setBorder(new LineBorder(SystemColor.control));
			button_4.setBackground(SystemColor.menu);
			button_4.setBounds((int)(width*0.01), (int)(height*0.38), (int)(width*0.04), (int)(height*0.07));
			panel.add(button_4);
			
			button_5 = new JButton("200");
			button_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cash_settle(company, username, store_name, posreg,version,"200");
				}
			});
			button_5.setForeground(Color.DARK_GRAY);
			button_5.setFont(new Font("Arial", Font.BOLD, (int)(width*0.02)));
			button_5.setBorder(new LineBorder(SystemColor.control));
			button_5.setBackground(SystemColor.menu);
			button_5.setBounds((int)(width*0.06), (int)(height*0.38), (int)(width*0.04), (int)(height*0.07));
			panel.add(button_5);
			
			button_6 = new JButton("500");
			button_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cash_settle(company, username, store_name, posreg,version,"500");
				}
			});
			button_6.setForeground(Color.DARK_GRAY);
			button_6.setFont(new Font("Arial", Font.BOLD, (int)(width*0.02)));
			button_6.setBorder(new LineBorder(SystemColor.control));
			button_6.setBackground(SystemColor.menu);
			button_6.setBounds((int)(width*0.11), (int)(height*0.38), (int)(width*0.04), (int)(height*0.07));
			panel.add(button_6);
			
			btnPayCode = new JButton("PAY CODE (F12)");
			btnPayCode.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					paycode_settle(company, username, store_name, posreg,version);
					
		    	}
			});
			btnPayCode.setForeground(Color.DARK_GRAY);
			btnPayCode.setFont(new Font("Arial", Font.BOLD, (int)(height*0.02)));
			btnPayCode.setBorder(new LineBorder(SystemColor.control));
			btnPayCode.setBackground(new Color(100, 149, 237));
			btnPayCode.setBounds((int)(width*0.01), (int)(height*0.62), (int)(width*0.09), (int)(height*0.09));
			panel.add(btnPayCode);
			
			btnCash = new JButton("CASH(F10)");
			btnCash.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cash_settle(company, username, store_name, posreg,version,"");
					
				}
			});
			btnCash.setToolTipText("");
			btnCash.setForeground(Color.DARK_GRAY);
			btnCash.setFont(new Font("Arial", Font.BOLD, (int)(height*0.025)));
			btnCash.setBorder(new LineBorder(SystemColor.control));
			btnCash.setBackground(new Color(124, 252, 0));
			btnCash.setBounds((int)(width*0.11),(int)( height*0.62),(int)( width*0.09), (int)(height*0.09));
			panel.add(btnCash);
			
			btnAR = new JButton("A / R (F11)");
			btnAR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Cust_Chooser.Active="1";
					ar_settle(company, username, store_name, posreg,con,version);
					
				}
			});
			btnAR.setBorder(new LineBorder(new Color(128, 128, 128), 2));
			btnAR.setForeground(Color.DARK_GRAY);
			btnAR.setFont(new Font("Arial", Font.BOLD, (int)(height*0.025)));
			btnAR.setBorder(new LineBorder(SystemColor.control));
			btnAR.setBackground(new Color(255, 99, 71));
			btnAR.setBounds((int)(width*0.11),(int)( height*0.54),(int)( width*0.09), (int)(height*0.07));
			panel.add(btnAR);
			
			button_9 = new JButton("1000");
			button_9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cash_settle(company, username, store_name, posreg,version,"1000");
				}
			});
			button_9.setForeground(Color.DARK_GRAY);
			button_9.setFont(new Font("Arial", Font.BOLD, (int)(width*0.015)));
			button_9.setBorder(new LineBorder(SystemColor.control));
			button_9.setBackground(SystemColor.menu);
			button_9.setBounds((int)(width*0.16), (int)(height*0.38), (int)(width*0.04), (int)(height*0.07));
			panel.add(button_9);
			
			btnDisc = new JButton("<HTML>DISC %</HTML>");
			btnDisc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					disc_perc(company, username, store_name, posreg,txtDisc.getText().trim(),"");
					
				}
			});
			btnDisc.setForeground(Color.DARK_GRAY);
			btnDisc.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnDisc.setBorder(new LineBorder(SystemColor.control));
			btnDisc.setBackground(new Color(255, 215, 0));
			btnDisc.setBounds((int)(width*0.01),(int)( height*0.54),(int)( width*0.04), (int)(height*0.07));
			panel.add(btnDisc);
			
			btnDiscAmt = new JButton("<HTML>DISC.   <BR>  \r\n AMT.</HTML>");
			btnDiscAmt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					disc_amt(company, username, store_name, posreg);
					
				}
			});
			btnDiscAmt.setForeground(Color.DARK_GRAY);
			btnDiscAmt.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnDiscAmt.setBorder(new LineBorder(SystemColor.control));
			btnDiscAmt.setBackground(new Color(255, 215, 0));
			btnDiscAmt.setBounds((int)(width*0.06), (int)(height*0.54), (int)(width*0.04), (int)(height*0.07));
			panel.add(btnDiscAmt);
			
			JButton btnC = new JButton("<HTML>ENTER    <BR>  \r\n</HTML>");
			btnC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String value = txtDisc.getText();
					if(!value.equals("")){
						validate_barcode(company, value, username,store_name, returns,con);
					}
					txtDisc.setText("");
					txtDisc.requestFocus();
					get_user_validate_dt(company, username,store_name,posreg);
					txtDisc.setBackground(Color.BLACK);
					txtDisc.setCaretColor(Color.WHITE);
					txtDisc.setForeground(Color.WHITE);
				}
			});
			btnC.setIcon(null);
			btnC.setForeground(Color.WHITE);
			btnC.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnC.setBorder(new LineBorder(SystemColor.control));
			btnC.setBackground(new Color(100, 149, 237));
			btnC.setBounds((int)(width*0.16), (int)(height*0.14), (int)(width*0.04), (int)(height*0.07));
			panel.add(btnC);
			
			//Image barcode=new ImageIcon(this.getClass().getResource("/Barcode.png")).getImage();
			btnPlu = new JButton("SEARCH(F9)");
			btnPlu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getitemlist(company, username,store_name,posreg,version,decimal,con, language);
					
				}
			});
			btnPlu.setBounds((int)(width*0.16), (int)(height*0.30), (int)(width*0.04), (int)(height*0.07));
			panel.add(btnPlu);
			btnPlu.setHorizontalTextPosition(JButton.CENTER);
			btnPlu.setVerticalTextPosition(JButton.BOTTOM);
			//btnPlu.setIcon(new ImageIcon(barcode));
			btnPlu.setIcon(new ImageIcon(serch));
			btnPlu.setForeground(Color.WHITE);
			btnPlu.setFont(new Font("Arial", Font.BOLD, (int)(height*0.012)));
			btnPlu.setBorder(new LineBorder(Color.WHITE));
			btnPlu.setBackground(Color.DARK_GRAY);
			
			Image qty = new ImageIcon(this.getClass().getResource("/Science-Multiply-icon.png")).getImage();
			btnX = new JButton("QTY.(F4)");
			btnX.setEnabled(false);
			btnX.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					qtyupdate(company, username,store_name,posreg,version);
				}
			});
			btnX.setBounds((int)(width*0.16),(int)( height*0.22), (int)(width*0.04), (int)(height*0.07));
			panel.add(btnX);
			btnX.setHorizontalTextPosition(JButton.CENTER);
			btnX.setVerticalTextPosition(JButton.BOTTOM);
			btnX.setIcon(new ImageIcon(qty));
			btnX.setForeground(Color.WHITE);
			btnX.setFont(new Font("Arial", Font.BOLD, (int)(height*0.015)));
			btnX.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnX.setBackground(Color.DARK_GRAY);
			
			Image void_img = new ImageIcon(this.getClass().getResource("/delete-file-icon (1).png")).getImage();
			btnVoid = new JButton("VOID (F6)");
			btnVoid.setEnabled(false);
			btnVoid.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getvoid( company,  username, store_name, posreg,con, version);
					
				}
			});
			btnVoid.setIcon(new ImageIcon(void_img));
			btnVoid.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnVoid.setHorizontalTextPosition(SwingConstants.CENTER);
			btnVoid.setForeground(Color.WHITE);
			btnVoid.setFont(new Font("Arial", Font.BOLD, (int)(height*0.015)));
			btnVoid.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnVoid.setBackground(Color.DARK_GRAY);
			btnVoid.setBounds((int)(width*0.01), (int)(height*0.30),(int)( width*0.04), (int)(height*0.07));
			panel.add(btnVoid);
			
			btnStoreCredit = new JButton("<HTML>STORE<BR>CREDIT</HTML>");
			btnStoreCredit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(Float.parseFloat(textField_3.getText())==0)
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "No Amount to Proceed");
						posscreen.txtDisc.requestFocus();
					}
					else
					{
						if(Float.parseFloat(textField_3.getText())>0)
						{
							//apply
							try {
								new sales_master.Store_credit_list(company, username, version, textField_3.getText(), store_name,"POS",decimal);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								txtDisc.requestFocus();
							}
						}
						else
						{
							//issue
							try {
								//company,username, version,reg_nme,amount,cust_name,cust_code,store,store_credit_validity,invoice_num
								new sales_master.Store_credit_issue(company, username, version,reg_nme, textField_3.getText(), btnNewButton.getText(), "", store_name, store_credit_validity, textField_8.getText(), language);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								txtDisc.requestFocus();
							}
						}	
					}
				}
			});
			btnStoreCredit.setEnabled(false);
			btnStoreCredit.setBounds((int)(width*0.01),(int)( height*0.46), (int)(width*0.09), (int)(height*0.07));
			btnStoreCredit.setBorder(new LineBorder(new Color(128, 128, 128), 2));
			btnStoreCredit.setForeground(Color.DARK_GRAY);
			btnStoreCredit.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnStoreCredit.setBorder(new LineBorder(SystemColor.control));
			btnStoreCredit.setBackground(new Color(30, 144, 255));
			panel.add(btnStoreCredit);
			
			btnQuickReturn_1 = new JButton("QUICK RETURN (F8)");
			btnQuickReturn_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					return_list(company, username, store_name, posreg);
				}
			});
			btnQuickReturn_1.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnQuickReturn_1.setHorizontalTextPosition(SwingConstants.CENTER);
			btnQuickReturn_1.setForeground(Color.WHITE);
			btnQuickReturn_1.setFont(new Font("Arial", Font.BOLD, (int)(height*0.017)));
			btnQuickReturn_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnQuickReturn_1.setBackground(Color.DARK_GRAY);
			btnQuickReturn_1.setIcon(new ImageIcon(remove));
			btnQuickReturn_1.setBounds((int)(width*0.11), (int)(height*0.46), (int)(width*0.09),(int)( height*0.07));
			panel.add(btnQuickReturn_1);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportBorder(null);
			scrollPane.setBounds(0, 0, (int)(width*0.79), (int)(height*0.60));
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBackground(Color.BLACK);
			frmRegisterNoS.getContentPane().add(scrollPane);
			
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
			table.setShowGrid(false);
			table.setForeground(Color.WHITE);
			table.setRowHeight(25);
			//table.setBorder(new LineBorder(Color.WHITE));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setFont(new Font("Arial", Font.BOLD, 15));
			table.setBackground(Color.DARK_GRAY);
			table.setSelectionBackground(Color.red);
			table.getTableHeader().setReorderingAllowed(false);
			//table.getTableHeader().setResizingAllowed(false);
			
			
			model.addColumn("S.No.");
		    model.addColumn("Barcode"); 
		    model.addColumn("Item Description"); 
		    model.addColumn("Unit"); 
		    model.addColumn("<html>Unit <br> Price</html>");
		    model.addColumn("Qty");
		    model.addColumn("<html>Disc<br>%</html>");
		    model.addColumn("Total");
		    model.addColumn("VAT");
		    model.addColumn("Net Total");
		    model.addColumn("<html>VAT<br>Type</html>");
		    model.addColumn("<html>cost</html>");
		    model.addColumn("<html>sp</html>");
		    model.addColumn("item_code");
		    model.addColumn("committed_qty");
		    model.addColumn("disc_type");
		    model.addColumn("vat_perc");
		    model.addColumn("status");
		    model.addColumn("dept");
		    model.addColumn("kot_number");
		    model.addColumn("reg_num");
		    model.addColumn("comments");
		    model.addColumn("cf");
		    model.addColumn("print_sts");
		    model.addColumn("scale_sts");
		    model.addColumn("chkbill_sts");
		    
		    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		    JTableHeader header=table.getTableHeader();
		    header.setBackground(Color.DARK_GRAY);
		    header.setForeground(Color.WHITE);
		    header.setPreferredSize(new Dimension(100,35));
		    int columns=model.getColumnCount();
			for (int i=0; i<columns; i++) {
				  TableColumn column = table.getColumnModel().getColumn(i);
				  if (i==0) column.setPreferredWidth((int)(width*0.04));
				  if (i==1) column.setPreferredWidth((int)(width*0.10));
				  if (i==2) column.setPreferredWidth((int)(width*0.19));
				  if (i==3) column.setPreferredWidth((int)(width*0.06));
				  if (i==4) column.setPreferredWidth((int)(width*0.06));
				  if (i==5) column.setPreferredWidth((int)(width*0.06));
				  if (i==6) column.setPreferredWidth((int)(width*0.06));
				  if (i==7) column.setPreferredWidth((int)(width*0.06));
				  if (i==8) column.setPreferredWidth((int)(width*0.06));
				  if (i==9) column.setPreferredWidth((int)(width*0.06));
				  if (i==10) column.setPreferredWidth((int)(width*0.06));
				}
			
			scrollPane.setViewportView(table);
			
			table.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent mouseEvent) {
	                JTable table =(JTable) mouseEvent.getSource();
	                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
	                	int sel_table_column=table.getSelectedColumn();
	                	if(sel_table_column==0)
	                	{
	                		int column = 21;
	                    	int rows = table.getSelectedRow();
	                    	String value = table.getModel().getValueAt(rows, column).toString();
	                    	try {
								new comment_item(company,value.trim());
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                	}
	                	if(sel_table_column==6)
	                	{
	                		new sales_master.Item_disc( company, username, store_name, reg_nme, version);
	                	}
	                	
	                }
	            }
	        });
			
			table.setDefaultRenderer(Object.class, new TableCellRenderer(){
	            private DefaultTableCellRenderer DEFAULT_RENDERER =  new DefaultTableCellRenderer();
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	                if (row%2 == 0){
	                    c.setBackground(Color.WHITE);
	                    c.setForeground(Color.BLACK);
	                }
	                else {
	                    c.setBackground(Color.LIGHT_GRAY);
	                    c.setForeground(Color.BLACK);
	                }
	                if (isSelected) {
	                	c.setBackground(Color.YELLOW);
	                }
	                return c;
	            }
	        });
			
			
			panel_7 = new JPanel();
			panel_7.setBorder(new LineBorder(new Color(100, 149, 237), 3, true));
			panel_7.setBackground(Color.DARK_GRAY);
			panel_7.setBounds(0, (int)(height*0.71), (int)(width*0.79), (int)(height*0.21));
			frmRegisterNoS.getContentPane().add(panel_7);
			panel_7.setLayout(null);
			
			Image receipt = new ImageIcon(this.getClass().getResource("/Receipt-icon (1).png")).getImage();
			btnHold = new JButton("HOLD\r\n INV.(F3)");
			btnHold.setEnabled(false);
			btnHold.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					hold_inv(company, username, store_name, posreg,con,"",version,"","",textField_8.getText(),"0");	
				}
			});
			btnHold.setIcon(new ImageIcon(receipt));
			btnHold.setHorizontalTextPosition(JButton.CENTER);
			btnHold.setVerticalTextPosition(JButton.BOTTOM);
			btnHold.setForeground(Color.WHITE);
			btnHold.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnHold.setBorder(new LineBorder(Color.WHITE));
			btnHold.setBackground(Color.DARK_GRAY);
			btnHold.setBounds((int)(width*0.70), (int)(height*0.01),(int)( width*0.08), (int)(height*0.09));
			panel_7.add(btnHold);
			
			Image unhold_receipt = new ImageIcon(this.getClass().getResource("/unhold receipt.png")).getImage();
			btnUnhold = new JButton("UN-HOLD(F1)");
			btnUnhold.setEnabled(false);
			btnUnhold.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					unhold_inv(company, username, store_name, posreg,version);
					//btnUnhold.setEnabled(false);
				}
			});
			btnUnhold.setIcon(new ImageIcon(unhold_receipt));
			btnUnhold.setHorizontalTextPosition(JButton.CENTER);
			btnUnhold.setVerticalTextPosition(JButton.BOTTOM);
			btnUnhold.setForeground(Color.WHITE);
			btnUnhold.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnUnhold.setBorder(new LineBorder(new Color(255, 255, 255)));
			btnUnhold.setBackground(Color.DARK_GRAY);
			btnUnhold.setBounds((int)(width*0.70), (int)(height*0.11),(int)( width*0.08), (int)(height*0.09));
			panel_7.add(btnUnhold);
			
			Image cash = new ImageIcon(this.getClass().getResource("/cash-icon.png")).getImage();
			btnPrice = new JButton("PRICE(F2)");
			btnPrice.setEnabled(false);
			btnPrice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getpricechange(company, username, store_name, posreg, version);
					
				}
			});
			btnPrice.setIcon(new ImageIcon(cash));
			btnPrice.setHorizontalTextPosition(JButton.CENTER);
			btnPrice.setVerticalTextPosition(JButton.BOTTOM);
			btnPrice.setForeground(Color.WHITE);
			btnPrice.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnPrice.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnPrice.setBackground(Color.DARK_GRAY);
			btnPrice.setBounds((int)(width*0.43),(int)( height*0.11),(int)( width*0.08),(int)( height*0.09));
			panel_7.add(btnPrice);
			
			btnDeleteLine = new JButton("<HTML>DELETE LINE<br> (INS+DEL)</HTML>");
			btnDeleteLine.setEnabled(false);
			btnDeleteLine.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					delete(company, username, store_name, posreg, version);
				}
			});
			btnDeleteLine.setIcon(new ImageIcon(void_img));
			btnDeleteLine.setHorizontalTextPosition(JButton.CENTER);
			btnDeleteLine.setVerticalTextPosition(JButton.BOTTOM);
			btnDeleteLine.setForeground(Color.WHITE);
			btnDeleteLine.setFont(new Font("Arial", Font.BOLD, (int)(height*0.015)));
			btnDeleteLine.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnDeleteLine.setBackground(Color.DARK_GRAY);
			btnDeleteLine.setBounds((int)(width*0.17), (int)(height*0.11),(int)( width*0.07),(int)( height*0.09));
			panel_7.add(btnDeleteLine);
			
			Image price = new ImageIcon(this.getClass().getResource("/price.png")).getImage();
			btnQuickReturn = new JButton("<HTML>PRICE <BR>ENQUIRY(F5)</HTML>");
			btnQuickReturn.setEnabled(false);
			btnQuickReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						btnQuickReturn.setEnabled(false);
						price_enqury(db_name,company,username,store_name,version, con, language);
					} catch (JSONException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						txtDisc.requestFocus();
					}
				}
			});
			btnQuickReturn.setIcon(new ImageIcon(price));
			btnQuickReturn.setHorizontalTextPosition(JButton.CENTER);
			btnQuickReturn.setVerticalTextPosition(JButton.BOTTOM);
			btnQuickReturn.setForeground(Color.WHITE);
			btnQuickReturn.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnQuickReturn.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnQuickReturn.setBackground(Color.DARK_GRAY);
			btnQuickReturn.setBounds((int)(width*0.17), (int)(height*0.01),(int)( width*0.07), (int)(height*0.09));
			panel_7.add(btnQuickReturn);
			
			
			
			Image refresh = new ImageIcon(this.getClass().getResource("/invoice.png")).getImage();
			JButton btnSearchItem = new JButton("CHK BILL");
			btnSearchItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
//					item_list_master.get_symc_itms(company);
//					posscreen.txtDisc.requestFocus();
					
					posscreen.page_type="Hold Invoice";
					posscreen.hold_inv=true;
					if(change_hold_type.equals("1"))
					{
						String a=label.getText();
						String b=label_1.getText();
						String c=label_2.getText();
						if(label.getText().equals(""))
						{
							a="QUICK DINE IN";
						}
						if(label_1.getText().equals(""))
						{
							b="";
						}
						if(label_2.getText().equals(""))
						{
							c="QUICK DINE IN";
							posscreen.page_type="Kitchen KOT";
						}
						posscreen.hold_inv(company, username,store_name, posreg, con,a,version,b,c,textField_8.getText(),"1");
					}
					else
					{
						posscreen.hold_inv(company, username,store_name, posreg, con,label.getText(),version,label_1.getText(),label_2.getText(),textField_8.getText(),"1");	
					}
					
				}
			});
			btnSearchItem.setHorizontalTextPosition(JButton.CENTER);
			btnSearchItem.setVerticalTextPosition(JButton.BOTTOM);
			btnSearchItem.setIcon(new ImageIcon(refresh));
			btnSearchItem.setForeground(Color.WHITE);
			btnSearchItem.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnSearchItem.setBorder(new LineBorder(Color.WHITE));
			btnSearchItem.setBackground(Color.DARK_GRAY);
			btnSearchItem.setBounds((int)(width*0.52), (int)(height*0.01), (int)(width*0.08), (int)(height*0.09));
			panel_7.add(btnSearchItem);
			
			Image Fast_forward = new ImageIcon(this.getClass().getResource("/Fast-forward-icon.png")).getImage();
			btnFastItems = new JButton("<HTML>FAST  <BR>  \r\n ITEMS</HTML>");
			btnFastItems.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						btnFastItems.setEnabled(false);
						if(fastitem_mode.equals("1"))
						{
							
							new sales_master.fastitems(company,store_name, version,username,returns);
						}
						else
						{
							new sales_master.fast_item_normal(company,store_name, version,username,returns);
						}
						//new sales_master.fastitems(company,store_name, version,username,returns);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						posscreen.txtDisc.requestFocus();
					}
				}
			});
			btnFastItems.setIcon(new ImageIcon(Fast_forward));
			btnFastItems.setHorizontalTextPosition(JButton.CENTER);
			btnFastItems.setVerticalTextPosition(JButton.BOTTOM);
			btnFastItems.setForeground(Color.WHITE);
			btnFastItems.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnFastItems.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnFastItems.setBackground(Color.DARK_GRAY);
			btnFastItems.setBounds((int)(width*0.43), (int)(height*0.01), (int)(width*0.08), (int)(height*0.09));
			panel_7.add(btnFastItems);
			
			Image reports=new ImageIcon(this.getClass().getResource("/reports.png")).getImage();
			Image folder = new ImageIcon(this.getClass().getResource("/Folder-Options-icon.png")).getImage();
			btnMisc = new JButton("<HTML>INVOICE <BR> SPLIT</HTML>");
			//btnMisc.setEnabled(false);
			btnMisc.setIcon(new ImageIcon(reports));
			btnMisc.setHorizontalTextPosition(JButton.CENTER);
			btnMisc.setVerticalTextPosition(JButton.BOTTOM);
			btnMisc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					posscreen.page_type="Hold Invoice";
					posscreen.hold_inv=true;
					if(change_hold_type.equals("1"))
					{
						try {
							new SplitInvoice(company,  username, store_name,reg_nme, version);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
//						String a=label.getText();
//						String b=label_1.getText();
//						String c=label_2.getText();
//						if(label.getText().equals(""))
//						{
//							a="QUICK DINE IN";
//						}
//						if(label_1.getText().equals(""))
//						{
//							b="";
//						}
//						if(label_2.getText().equals(""))
//						{
//							c="QUICK DINE IN";
//							posscreen.page_type="Kitchen KOT";
//						}
//						posscreen.hold_inv(company, username,store_name, posreg, con,a,version,b,c,textField_8.getText());
					}
					else
					{
						posscreen.hold_inv(company, username,store_name, posreg, con,label.getText(),version,label_1.getText(),label_2.getText(),textField_8.getText(),"0");	
					}
					
				}
			});
			btnMisc.setForeground(Color.WHITE);
			btnMisc.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnMisc.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnMisc.setBackground(Color.DARK_GRAY);
			btnMisc.setBounds((int)(width*0.52), (int)(height*0.11), (int)(width*0.08), (int)(height*0.09));
			panel_7.add(btnMisc);
			
			Image PayIn = new ImageIcon(this.getClass().getResource("/Pay In.png")).getImage();
			btnPayIn = new JButton("PAY IN");
			btnPayIn.setEnabled(false);
			btnPayIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table.getRowCount()==0)
					{
						manager_name=username;
						
						try {
							new sales_master.pay_in(company,  username, store_name, reg_nme, version,printer);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						get_user_validate_dt( company, username,store_name,posreg);
						btnPayIn.setEnabled(false);
					}
					else
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "Table Not Empty , First Settle Bill");
						posscreen.txtDisc.requestFocus();
					}
					
				}
			});
			btnPayIn.setIcon(new ImageIcon(PayIn));
			btnPayIn.setHorizontalTextPosition(JButton.CENTER);
			btnPayIn.setVerticalTextPosition(JButton.BOTTOM);
			btnPayIn.setForeground(Color.WHITE);
			btnPayIn.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnPayIn.setBorder(new LineBorder(new Color(192, 192, 192)));
			btnPayIn.setBackground(Color.DARK_GRAY);
			btnPayIn.setBounds((int)(width*0.34),(int)( height*0.01), (int)(width*0.08), (int)(height*0.09));
			panel_7.add(btnPayIn);
			
			Image payout = new ImageIcon(this.getClass().getResource("/Apps-drawer-icon.png")).getImage();
			btnPayOut = new JButton("PAY OUT");
			btnPayOut.setEnabled(false);
			btnPayOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table.getRowCount()==0)
					{
						manager_name=username;
						
						try {
							new sales_master.pay_out(company,  username, store_name, reg_nme, version,printer);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						get_user_validate_dt( company, username,store_name,posreg);
						btnPayOut.setEnabled(false);
					}
					else
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "Table Not Empty , First Settle Bill");
						posscreen.txtDisc.requestFocus();
					}
				}
			});
			btnPayOut.setIcon(new ImageIcon(payout));
			btnPayOut.setHorizontalTextPosition(JButton.CENTER);
			btnPayOut.setVerticalTextPosition(JButton.BOTTOM);
			btnPayOut.setToolTipText("");
			btnPayOut.setForeground(Color.WHITE);
			btnPayOut.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnPayOut.setBorder(new LineBorder(new Color(192, 192, 192)));
			btnPayOut.setBackground(Color.DARK_GRAY);
			btnPayOut.setBounds((int)(width*0.34), (int)(height*0.11), (int)(width*0.08),(int)( height*0.09));
			panel_7.add(btnPayOut);
			
			Image client = new ImageIcon(this.getClass().getResource("/Clients-icon.png")).getImage();
			btnNewCustomer = new JButton("<HTML>NEW    <BR>  \r\nCUST.</HTML>");
			btnNewCustomer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					manager_name=username;
					
					new sales_master.Customer_master(company, username,reg_nme,store_name, version,"POS", language);
					Customer_master.Active="1";
					get_user_validate_dt( company, username,store_name,posreg);
					btnNewCustomer.setEnabled(false);
				}
			});
			btnNewCustomer.setIcon(new ImageIcon(client));
			btnNewCustomer.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnNewCustomer.setHorizontalTextPosition(SwingConstants.CENTER);
			btnNewCustomer.setForeground(Color.WHITE);
			btnNewCustomer.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnNewCustomer.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnNewCustomer.setBackground(Color.DARK_GRAY);
			btnNewCustomer.setBounds((int)(width*0.09), (int)(height*0.01), (int)(width*0.07), (int)(height*0.09));
			panel_7.add(btnNewCustomer);
			
			Image payment = new ImageIcon(this.getClass().getResource("/payment-icon.png")).getImage();
			btncustPay = new JButton("<HTML>CUST.    <BR>  \r\nPAYMENT</HTML>");
			btncustPay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table.getRowCount()==0)
					{
						manager_name=username;
						
						new customer_payment( company, username, store_name, reg_nme, version,printer,"");
						get_user_validate_dt( company, username,store_name,posreg);
						btncustPay.setEnabled(false);
					}
					else
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "Table Not Empty , First Settle Bill");
						posscreen.txtDisc.requestFocus();
					}
					
				}
			});
			btncustPay.setIcon(new ImageIcon(payment));
			btncustPay.setVerticalTextPosition(SwingConstants.BOTTOM);
			btncustPay.setHorizontalTextPosition(SwingConstants.CENTER);
			btncustPay.setForeground(Color.WHITE);
			btncustPay.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btncustPay.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btncustPay.setBackground(Color.DARK_GRAY);
			btncustPay.setBounds((int)(width*0.09), (int)(height*0.11), (int)(width*0.07), (int)(height*0.09));
			panel_7.add(btncustPay);
			
			Image nosale = new ImageIcon(this.getClass().getResource("/NO SALE.png")).getImage();
			btnNoSale = new JButton("NO SALE");
			btnNoSale.setEnabled(false);
			btnNoSale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//byte[] open = {27,112,48,55,121};
			        PrintService pservice = PrintServiceLookup.lookupDefaultPrintService(); 
			        DocPrintJob job = pservice.createPrintJob();
			        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
			        Doc doc = new SimpleDoc(open,flavor,null);
			        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
			        try {
			            job.print(doc, aset);
						manager_name=username;
			            get_user_validate_dt( company, username,store_name,posreg);
			            txtDisc.requestFocus();
			            
			        } catch (PrintException ex) {
			            System.out.println(ex.getMessage());
			            JOptionPane.showMessageDialog(frmRegisterNoS, ex.getMessage());
			            txtDisc.requestFocus();
			        }
				}
			});
			btnNoSale.setIcon(new ImageIcon(nosale));
			btnNoSale.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnNoSale.setHorizontalTextPosition(SwingConstants.CENTER);
			btnNoSale.setForeground(Color.WHITE);
			btnNoSale.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnNoSale.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnNoSale.setBackground(Color.DARK_GRAY);
			btnNoSale.setBounds((int)(width*0.01), (int)(height*0.01), (int)(width*0.07), (int)(height*0.09));
			panel_7.add(btnNoSale);
			
			Image salesreport = new ImageIcon(this.getClass().getResource("/Prd_sle_rpt.png")).getImage();//sales-report-icon.png
			btncustStatement = new JButton("<HTML>CUST.    <BR>  \r\n STATE.</HTML>");
			btncustStatement.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table.getRowCount()==0)
					{
						manager_name=username;
						
						new customer_statement( company, username, store_name, reg_nme, version,"");
						get_user_validate_dt( company, username,store_name,posreg);
						btncustStatement.setEnabled(false);
					}
					else
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "Table Not Empty , First Settle Bill");
						posscreen.txtDisc.requestFocus();
					}
					
				}
			});
			btncustStatement.setIcon(new ImageIcon(salesreport));
			btncustStatement.setVerticalTextPosition(SwingConstants.BOTTOM);
			btncustStatement.setHorizontalTextPosition(SwingConstants.CENTER);
			btncustStatement.setForeground(Color.WHITE);
			btncustStatement.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btncustStatement.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btncustStatement.setBackground(Color.DARK_GRAY);
			btncustStatement.setBounds((int)(width*0.01), (int)(height*0.11), (int)(width*0.07), (int)(height*0.09));
			panel_7.add(btncustStatement);
			
			Image places_folder = new ImageIcon(this.getClass().getResource("/Places-folder-documents-icon.png")).getImage();
			btnduplicateBill = new JButton("<HTML>DUP.<BR>INVOICE(F7)</HTML>");
			btnduplicateBill.setEnabled(false);
			btnduplicateBill.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					dup_bill(company, username, store_name, posreg,version);
					
				}
			});
			btnduplicateBill.setIcon(new ImageIcon(places_folder));
			btnduplicateBill.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnduplicateBill.setHorizontalTextPosition(SwingConstants.CENTER);
			btnduplicateBill.setForeground(Color.WHITE);
			btnduplicateBill.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnduplicateBill.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnduplicateBill.setBackground(Color.DARK_GRAY);
			btnduplicateBill.setBounds((int)(width*0.25), (int)(height*0.01), (int)(width*0.08), (int)(height*0.09));
			panel_7.add(btnduplicateBill);
			
			Image folder_inv = new ImageIcon(this.getClass().getResource("/folder-invoices-icon.png")).getImage();
			btnhistInvoice = new JButton("<HTML>HISTORICAL.    <BR>  \r\nINVOICE </HTML>");
			btnhistInvoice.setEnabled(false);
			btnhistInvoice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(table.getRowCount()==0)
					{
						try {
							manager_name=username;
							
							new historical_invoice(company,username,store_name,reg_nme,printer, version,Integer.toString(decimalPlaces));
							get_user_validate_dt( company, username,store_name,posreg);
							btnhistInvoice.setEnabled(false);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							posscreen.txtDisc.requestFocus();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "Table Not Empty , First Settle Bill");
						posscreen.txtDisc.requestFocus();
					}
					
				}
			});
			btnhistInvoice.setIcon(new ImageIcon(folder_inv));
			btnhistInvoice.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnhistInvoice.setHorizontalTextPosition(SwingConstants.CENTER);
			btnhistInvoice.setForeground(Color.WHITE);
			btnhistInvoice.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnhistInvoice.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnhistInvoice.setBackground(Color.DARK_GRAY);
			btnhistInvoice.setBounds((int)(width*0.25), (int)(height*0.11), (int)(width*0.08),(int)(height*0.09));
			panel_7.add(btnhistInvoice);
			
			Image Manager = new ImageIcon(this.getClass().getResource("/Manager-icon.png")).getImage();
			btnManager = new JButton("MANAGER");
			btnManager.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					btnManager.setEnabled(false);
					new sales_master.manager( company, username, store_name, reg_nme, version);
					txtDisc.requestFocus();
					
				}
			});
			btnManager.setIcon(new ImageIcon(Manager));
			btnManager.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnManager.setHorizontalTextPosition(SwingConstants.CENTER);
			btnManager.setForeground(Color.WHITE);
			btnManager.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnManager.setBorder(new LineBorder(Color.WHITE));
			btnManager.setBackground(Color.DARK_GRAY);
			btnManager.setBounds((int)(width*0.61), (int)(height*0.01), (int)(width*0.08), (int)(height*0.09));
			panel_7.add(btnManager);
			
			btnchangeUnit = new JButton("<HTML>CHANGE    <BR>  \r\nUNIT</HTML>");
			btnchangeUnit.setEnabled(false);
			btnchangeUnit.setIcon(new ImageIcon(folder));
			btnchangeUnit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						
						if(model.getRowCount()==0)
						{
							JOptionPane.showMessageDialog(frmRegisterNoS, "Select one item to change unit");
							posscreen.txtDisc.requestFocus();
						}
						else
						{
							manager_name=username;
							String test_itemcode="";
							int rows = table.getSelectedRow();
							if(rows>=0)
							{
								test_itemcode=table.getModel().getValueAt(rows, 1).toString();
							}
							String qty=table.getModel().getValueAt(rows, 5).toString();
							btnchangeUnit.setEnabled(false);
							new change_unit(db_name,company,username,store_name, version,con,test_itemcode,qty, language);
							get_user_validate_dt( company, username,store_name,posreg);
						}
						
					} catch (JSONException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						posscreen.txtDisc.requestFocus();
					}
				}
			});
			btnchangeUnit.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnchangeUnit.setHorizontalTextPosition(SwingConstants.CENTER);
			btnchangeUnit.setForeground(Color.WHITE);
			btnchangeUnit.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			btnchangeUnit.setBorder(new LineBorder(Color.LIGHT_GRAY));
			btnchangeUnit.setBackground(Color.DARK_GRAY);
			btnchangeUnit.setBounds((int)(width*0.61), (int)(height*0.11), (int)(width*0.08), (int)(height*0.09));
			panel_7.add(btnchangeUnit);
			
			 panel_11 = new JPanel();
			panel_11.setBorder(new LineBorder(new Color(100, 149, 237), 3, true));
			panel_11.setBackground(Color.DARK_GRAY);
			panel_11.setBounds(0, (int)(height*0.61), (int)(width*0.79), (int)(height*0.10));
			frmRegisterNoS.getContentPane().add(panel_11);
			panel_11.setLayout(null);
			
			JLabel lblRoundOff = new JLabel("Round Off : ");
			lblRoundOff.setForeground(Color.WHITE);
			lblRoundOff.setFont(new Font("Tahoma", Font.PLAIN, (int)(width*0.01)));
			lblRoundOff.setBounds(560, 97, 70, 16);
			panel_11.add(lblRoundOff);
			
			textField_5 = new JTextField();
			textField_5.setEditable(false);
			textField_5.setHorizontalAlignment(SwingConstants.CENTER);
			textField_5.setForeground(Color.YELLOW);
			textField_5.setFont(new Font("Arial", Font.BOLD, (int)(height*0.02)));
			textField_5.setColumns(10);
			textField_5.setBorder(null);
			textField_5.setBackground(Color.DARK_GRAY);
			textField_5.setBounds((int)(width*0.29), (int)(height*0.05), (int)(width*0.07),(int)( height*0.02));
			panel_11.add(textField_5);
			textField_5.setColumns(10);
			
			textField_3 = new JTextField("0.00");
			textField_3.setEditable(false);
			textField_3.setBorder(null);
			textField_3.setFont(new Font("Arial", Font.BOLD, (int)(height*0.02)));
			textField_3.setHorizontalAlignment(SwingConstants.CENTER);
			textField_3.setForeground(Color.WHITE);
			textField_3.setBackground(Color.DARK_GRAY);
			textField_3.setBounds((int)(width*0.71), (int)(height*0.04), (int)(width*0.07), (int)(height*0.05));
			panel_11.add(textField_3);
			textField_3.setColumns(10);
			
			JLabel lblNewLabel =new JLabel("GRAND TOTAL");
			lblNewLabel.setForeground(new Color(100, 149, 237));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, (int)(height*0.015)));
			lblNewLabel.setBounds((int)(width*0.71), (int)(height*0.01),(int)( width*0.07), (int)(height*0.03));
			panel_11.add(lblNewLabel);
			
			textField_1 = new JTextField("0.00");
			textField_1.setEditable(false);
			textField_1.setHorizontalAlignment(SwingConstants.CENTER);
			textField_1.setForeground(Color.WHITE);
			textField_1.setFont(new Font("Arial", Font.BOLD, (int)(height*0.02)));
			textField_1.setColumns(10);
			textField_1.setBorder(null);
			textField_1.setBackground(Color.DARK_GRAY);
			textField_1.setBounds((int)(width*0.57),(int)( height*0.05), (int)(width*0.08), (int)(height*0.04));
			panel_11.add(textField_1);
			
			JLabel lblTotalQty = new JLabel("VAT");
			lblTotalQty.setHorizontalAlignment(SwingConstants.CENTER);
			lblTotalQty.setForeground(new Color(100, 149, 237));
			lblTotalQty.setFont(new Font("Tahoma", Font.BOLD, (int)(width*0.01)));
			lblTotalQty.setBounds((int)(width*0.57), (int)(height*0.01), (int)(width*0.08),(int)(height*0.03));
			panel_11.add(lblTotalQty);
			
			textField_2 = new JTextField("0");
			textField_2.setEditable(false);
			textField_2.setHorizontalAlignment(SwingConstants.CENTER);
			textField_2.setForeground(Color.WHITE);
			textField_2.setFont(new Font("Arial", Font.BOLD, (int)(height*0.02)));
			textField_2.setColumns(10);
			textField_2.setBorder(null);
			textField_2.setBackground(Color.DARK_GRAY);
			textField_2.setBounds((int)(width*0.29), (int)(height*0.07), (int)(width*0.07), (int)(height*0.02));
			panel_11.add(textField_2);
			
			JLabel lblTotalItems = new JLabel("<html>TOTAL QTY.</html>");
			lblTotalItems.setHorizontalAlignment(SwingConstants.CENTER);
			lblTotalItems.setForeground(new Color(100, 149, 237));
			lblTotalItems.setFont(new Font("Tahoma", Font.BOLD, (int)(width*0.01)));
			lblTotalItems.setBounds((int)(width*0.01), (int)(height*0.01), (int)(width*0.07), (int)(height*0.03));
			panel_11.add(lblTotalItems);
			
			JLabel lblDiscount = new JLabel("DISC");
			lblDiscount.setHorizontalAlignment(SwingConstants.CENTER);
			lblDiscount.setForeground(new Color(100, 149, 237));
			lblDiscount.setFont(new Font("Tahoma", Font.BOLD, (int)(width*0.01)));
			lblDiscount.setBounds((int)(width*0.29), (int)(height*0.01), (int)(width*0.07), (int)(height*0.03));
			panel_11.add(lblDiscount);
			
			JLabel lblTotal = new JLabel("SUB TOTAL");
			lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
			lblTotal.setForeground(new Color(100, 149, 237));
			lblTotal.setFont(new Font("Tahoma", Font.BOLD, (int)(width*0.01)));
			lblTotal.setBounds((int)(width*0.45), (int)(height*0.01),(int)( width*0.07), (int)(height*0.03));
			panel_11.add(lblTotal);
			
			lblNewLabel_1 = new JLabel("0");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setBounds(640, 98, 48, 16);
			panel_11.add(lblNewLabel_1);
			
			textField_4 = new JTextField("0");
			textField_4.setEditable(false);
			textField_4.setHorizontalAlignment(SwingConstants.CENTER);
			textField_4.setForeground(Color.WHITE);
			textField_4.setFont(new Font("Arial", Font.BOLD, (int)(height*0.02)));
			textField_4.setColumns(10);
			textField_4.setBorder(null);
			textField_4.setBackground(Color.DARK_GRAY);
			textField_4.setBounds((int)(width*0.01), (int)(height*0.05), (int)(width*0.07), (int)(height*0.03));
			panel_11.add(textField_4);
			
			textField = new JTextField("0.00");
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setForeground(Color.WHITE);
			textField.setFont(new Font("Arial", Font.BOLD, (int)(height*0.02)));
			textField.setEditable(false);
			textField.setColumns(10);
			textField.setBorder(null);
			textField.setBackground(Color.DARK_GRAY);
			textField.setBounds((int)(width*0.45), (int)(height*0.05), (int)(width*0.07), (int)(height*0.04));
			panel_11.add(textField);
			
			JLabel lblBeforetotal = new JLabel("TOTAL");
			lblBeforetotal.setHorizontalAlignment(SwingConstants.CENTER);
			lblBeforetotal.setForeground(new Color(100, 149, 237));
			lblBeforetotal.setFont(new Font("Tahoma", Font.BOLD, (int)(width*0.01)));
			lblBeforetotal.setBounds((int)(width*0.14), (int)(height*0.01), (int)(width*0.07), (int)(height*0.03));
			panel_11.add(lblBeforetotal);
			
			textField_11 = new JTextField("0.00");
			textField_11.setHorizontalAlignment(SwingConstants.CENTER);
			textField_11.setForeground(Color.WHITE);
			textField_11.setFont(new Font("Arial", Font.BOLD, (int)(height*0.02)));
			textField_11.setEditable(false);
			textField_11.setColumns(10);
			textField_11.setBorder(null);
			textField_11.setBackground(Color.DARK_GRAY);
			textField_11.setBounds((int)(width*0.14), (int)(height*0.05), (int)(width*0.07), (int)(height*0.04));
			panel_11.add(textField_11);
			
			panel_1 = new JPanel();
			panel_1.setBackground(Color.DARK_GRAY);
			panel_1.setBorder(new LineBorder(new Color(100, 149, 237), 3, true));
			panel_1.setBounds((int)(width*0.79),(int)( height*0), (int)(width*0.21), (int)(height*0.20));
			frmRegisterNoS.getContentPane().add(panel_1);
			panel_1.setLayout(null);
			
			
			JScrollPane scrollPane1 = new JScrollPane();
			scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane1.setBounds(12, 297, 908, 256);
			scrollPane1.setBackground(new Color(243, 229, 216));
			scrollPane1.setVisible(false);
			panel_1.add(scrollPane1);

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
			    
			    model1.addColumn("item_code");
			    model1.addColumn("item_name");
			    model1.addColumn("barcode"); 
			    model1.addColumn("category"); 
			    model1.addColumn("department");
			    model1.addColumn("unit");
			    model1.addColumn("sp");
			    model1.addColumn("output_tax");
			    model1.addColumn("sp_include_tax");
			    model1.addColumn("cost");
			    model1.addColumn("qty");
			    model1.addColumn("type"); 
			    model1.addColumn("input_tax"); 
			    model1.addColumn("po_inclusive_tax");
			    model1.addColumn("activeForSale"); 
			    model1.addColumn("activeForPurchase"); 
			    model1.addColumn("cf"); 
			    model1.addColumn("input_tax_rate"); 
			    model1.addColumn("output_tax_rate"); 
			    model1.addColumn("weighing_sts"); 
			    model1.addColumn("sub_cat");
			    model1.addColumn("sub_dept");
			    
			scrollPane1.setViewportView(table1);
			
			JLabel lblReceiptNo_1 = new JLabel("RECEIPT #:");
			lblReceiptNo_1.setFont(new Font("Tahoma", Font.PLAIN, (int)(width*0.01)));
			lblReceiptNo_1.setForeground(Color.WHITE);
			lblReceiptNo_1.setBounds((int)(width*0.01), (int)(height*0.01), (int)(width*0.06), (int)(height*0.03));
			panel_1.add(lblReceiptNo_1);
			
			JLabel lblCustomer = new JLabel("CUSTOMER:");
			lblCustomer.setFont(new Font("Tahoma", Font.PLAIN, (int)(width*0.01)));
			lblCustomer.setForeground(Color.WHITE);
			lblCustomer.setBounds((int)(width*0.01), (int)(height*0.04),(int)( width*0.06), (int)(height*0.03));
			panel_1.add(lblCustomer);
			
			textField_8 = new JTextField(Integer.toString(receipt_num));
			textField_8.setEditable(false);
			textField_8.setBackground(Color.DARK_GRAY);
			textField_8.setForeground(Color.GREEN);
			textField_8.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			textField_8.setHorizontalAlignment(SwingConstants.CENTER);
			textField_8.setColumns(10);
			textField_8.setBounds((int)(width*0.07), (int)(height*0.01), (int)(width*0.10),(int)( height*0.03));
			panel_1.add(textField_8);
			
			textField_6 = new JTextField();
			textField_6.setEditable(false);
			textField_6.setBackground(Color.DARK_GRAY);
			textField_6.setForeground(Color.WHITE);
			textField_6.setFont(new Font("Arial", Font.BOLD, (int)(width*0.01)));
			textField_6.setHorizontalAlignment(SwingConstants.CENTER);
			textField_6.setColumns(10);
			textField_6.setBounds((int)(width*0.07), (int)(height*0.16), (int)(width*0.10), (int)(height*0.03));
			panel_1.add(textField_6);
			
			JLabel lblCustomerBalance = new JLabel("<HTML>CUSTOMER    <BR>  \r\n BALANCE:</HTML>");
			lblCustomerBalance.setFont(new Font("Tahoma", Font.PLAIN, (int)( height*0.015)));
			lblCustomerBalance.setVerticalTextPosition(SwingConstants.CENTER);
			lblCustomerBalance.setHorizontalTextPosition(SwingConstants.CENTER);
			lblCustomerBalance.setForeground(Color.WHITE);
			lblCustomerBalance.setBounds((int)(width*0.01), (int)(height*0.16), (int)(width*0.06),(int)( height*0.04));
			panel_1.add(lblCustomerBalance);
			
			timeLabel  = new JLabel("");
			timeLabel .setBounds(68, 700, 293, 16);
			panel_1.add(timeLabel );
			
			btnNewButton = new JButton("WALK IN");
			btnNewButton.setForeground(Color.WHITE);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					get_cust_chooser( company, version, username, store_name, posreg, con);
				}
			});
			btnNewButton.setBackground(Color.DARK_GRAY);
			btnNewButton.setBounds((int)(width*0.07), (int)(height*0.04),(int)( width*0.10),(int)( height*0.03));
			panel_1.add(btnNewButton);
			
			textField_9 = new JTextField("1");
			
//			textField_9.addMouseListener(new MouseAdapter() {
//				public void mouseClicked(MouseEvent arg0) {
//					get_cust_chooser( company, version, username, store_name, posreg, con);
//				}
//			});
			textField_9.setHorizontalAlignment(SwingConstants.CENTER);
			textField_9.setForeground(Color.WHITE);
			textField_9.setFont(new Font("Arial", Font.PLAIN, (int)(width*0.01)));
			textField_9.setEditable(false);
			textField_9.setColumns(10);
			textField_9.setVisible(false);
			textField_9.setBackground(Color.DARK_GRAY);
			textField_9.setBounds((int)(width*0.07), (int)(height*0.08), (int)(width*0.10), (int)(height*0.03));
			panel_1.add(textField_9);
			
			textField_10 = new JTextField("0");
			textField_10.setEditable(false);
			textField_10.setVisible(false);
	        textField_10.setBounds(10, 729, 86, 20);
	        frmRegisterNoS.getContentPane().add(textField_10);
	        textField_10.setColumns(10);
	        
	        textField_7 = new JTextField();
	        textField_7.setEditable(false);
	        textField_7.setBounds(106, 728, 116, 22);
	        frmRegisterNoS.getContentPane().add(textField_7);
	        textField_7.setText("0");
	        textField_7.setVisible(false);
	        textField_7.setColumns(10);
	        
	        
	        JLabel lblCustomerDiscApplied = new JLabel("Customer Disc Applied : ");
	        lblCustomerDiscApplied.setFont(new Font("Tahoma", Font.PLAIN, (int)(width*0.0025)));
			lblCustomerDiscApplied.setBounds(84, 162, 146, 14);
			panel_1.add(lblCustomerDiscApplied);
			lblCustomerDiscApplied.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCustomerDiscApplied.setForeground(Color.DARK_GRAY);
	        
	        lblNewLabel_2 = new JLabel("0%");
			lblNewLabel_2.setForeground(Color.DARK_GRAY);
			lblNewLabel_2.setBounds(238, 162, 46, 14);
			panel_1.add(lblNewLabel_2);
			
			JLabel lblMode = new JLabel("MODE : ");
			lblMode.setForeground(Color.WHITE);
			lblMode.setFont(new Font("Tahoma", Font.PLAIN, (int)(width*0.01)));
			lblMode.setBounds((int)(width*0.01), (int)(height*0.11),(int)( width*0.05),(int)( height*0.04));
			panel_1.add(lblMode);
			
			label = new JLabel("");
			label.setForeground(Color.WHITE);
			label.setFont(new Font("Tahoma", Font.PLAIN, (int)(width*0.01)));
			label.setBounds((int)(width*0.11), (int)(height*0.12), (int)(width*0.05), (int)(height*0.03));
			panel_1.add(label);
			
			label_1 = new JLabel("");
			label_1.setForeground(Color.WHITE);
			label_1.setFont(new Font("Tahoma", Font.PLAIN, (int)(width*0.01)));
			label_1.setBounds((int)(width*0.16),(int)( height*0.12),(int)( width*0.05),(int)( height*0.03));
			panel_1.add(label_1);
			
			label_2 = new JLabel("");
			label_2.setForeground(Color.WHITE);
			label_2.setFont(new Font("Tahoma", Font.PLAIN, (int)(width*0.01)));
			label_2.setBounds((int)(width*0.06),(int)( height*0.12),(int)( width*0.05),(int)( height*0.03));
			panel_1.add(label_2);
			
			JLabel lblCode = new JLabel("PHONE:");
			lblCode.setForeground(Color.WHITE);
			lblCode.setFont(new Font("Tahoma", Font.PLAIN, (int)(width*0.01)));
			lblCode.setBounds((int)(width*0.01),(int)( height*0.08), (int)(width*0.06), (int)(height*0.03));
			panel_1.add(lblCode);
			
			textField_12 = new JTextField("0");
			textField_12.setHorizontalAlignment(SwingConstants.CENTER);
			textField_12.setForeground(Color.WHITE);
			textField_12.setFont(new Font("Arial", Font.PLAIN, (int)(width*0.01)));
			textField_12.setEditable(false);
			textField_12.setColumns(10);
			textField_12.setBackground(Color.DARK_GRAY);
			textField_12.setBounds((int)(width*0.07), (int)(height*0.08), (int)(width*0.10),(int)( height*0.03));
			panel_1.add(textField_12);
			
			final DateFormat timeFormat = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss a");
	        ActionListener timerListener = new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	                Date date = new Date();
	                time = timeFormat.format(date);
	                timeLabel.setText(time);
	                frmRegisterNoS.setTitle("REGISTER NO:"+reg_nme+"  DATE:"+time+"  USER:"+manager_name+"   DATABASE:"+company+"   HOST:"+server+"  STATUS:"+status);
	            }
	        };
	        Timer timer = new Timer(1000, timerListener);
	        timer.setInitialDelay(0);
	        timer.start();
	        
	        get_user_validate_dt(company,username,store_name,posreg);
	        txtDisc.requestFocusInWindow();
	        //txtDisc.requestFocus();
	        get_register_name(company,username, store_name,con,posreg);
	        
	        if(print_duplicate_invoice.equals("1"))
			{
				copy=2;
			}
	        
	        if(CustDisc.equals("1"))
	        {
	        	
	    		lblCustomerDiscApplied.setForeground(Color.WHITE);
	    		
	    		lblNewLabel_2.setForeground(Color.WHITE);
	    		
	        }
	        
	        
	        try {
//	        	Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
//				Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO "+ready_msg1+""+ready_msg2+">"+display_port+"\""});
	        	Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+posscreen.ready_msg1+">"+display_port+"&&ECHO "+posscreen.ready_msg2+">"+display_port+"\"");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	       // get_items(company,con);
	        clickOnunhold( btnUnhold, "play", KeyEvent.VK_F1,user_name, company, store_name, posreg,version );
	        clickOnhold( btnHold, "play", KeyEvent.VK_F3,user_name, company, store_name, posreg,con,version );
	        clickOnprice( btnPrice, "play", KeyEvent.VK_F2,user_name, company, store_name, posreg,version );
	        clickOnqty( btnX, "play", KeyEvent.VK_F4,user_name, company, store_name, posreg,version );
	        clickOnprice_enq( btnQuickReturn, "play", KeyEvent.VK_F5,user_name, company, store_name, posreg,db_name,version,con, language );
	        clickOnvoid( btnVoid, "play", KeyEvent.VK_F6,user_name, company, store_name, posreg,con,version );
	        clickOndup( btnduplicateBill, "play", KeyEvent.VK_F7,user_name, company, store_name, posreg,version);
	        clickOnrtn( btnQuickReturn_1, "play", KeyEvent.VK_F8,user_name, company, store_name, posreg );
	        clickOnserch( btnPlu, "play", KeyEvent.VK_F9,user_name, company, store_name, posreg,version,decimal,con, language );
	        clickOnCash( btnCash, "play", KeyEvent.VK_F10,user_name, company, store_name, posreg,version,con );
	        clickOnar( btnAR, "play", KeyEvent.VK_F11,user_name, company, store_name, posreg,con,version );
	        clickOnpaycode( btnPayCode, "play", KeyEvent.VK_F12,user_name, company, store_name, posreg,db_name,version,con );
	        clickOndel( btnDeleteLine, "play", KeyEvent.VK_DELETE,user_name, company, store_name, posreg,version );
	        clickOnback( btnMisc, "play", KeyEvent.VK_ESCAPE );
	        
		}
		
		
	}
	public static void clickOnunhold(final AbstractButton button, String actionName, int key,final String username ,final String company,final String store_name,final String posreg, final String version)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
				unhold_inv(company, username, store_name, posreg, version);
	        }
	    } );
	}
	public static void clickOnhold(final AbstractButton button, String actionName, int key,final String username ,final String company,final String store_name,final String posreg,final Connection con,final String version)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
				hold_inv(company, username, store_name, posreg, con,"", version,"","",textField_8.getText(),"0");
	        }
	    } );
	}
	public static void clickOnprice(final AbstractButton button, String actionName, int key,final String username ,final String company,final String store_name,final String posreg, final String version)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
				getpricechange(company, username, store_name, posreg, version);
	        }
	    } );
	}
	public static void clickOnqty(final AbstractButton button, String actionName, int key,final String username ,final String company,final String store_name,final String posreg,final String version)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
				qtyupdate(company, username, store_name, posreg,version);
	        }
	    } );
	}
	public static void clickOnprice_enq(final AbstractButton button, String actionName, int key,final String username ,final String company,final String store_name,final String posreg,final String db_name,final String version,final Connection con,final String language)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
				try {
					price_enqury(db_name,company,username,store_name, version,con,language);
				} catch (JSONException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    } );
	}
	public static void clickOnvoid(final AbstractButton button, String actionName, int key,final String username ,final String company,final String store_name,final String posreg,final Connection con, final String version)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
				getvoid( company,  username, store_name, posreg,con, version);
	        }
	    } );
	}
	public static void clickOndup(final AbstractButton button, String actionName, int key,final String username ,final String company,final String store_name,final String posreg,final String version)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
				dup_bill(company, username, store_name, posreg, version);
	        }
	    } );
	}
	public static void clickOnrtn(final AbstractButton button, String actionName, int key,final String username ,final String company,final String store_name,final String posreg)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW )
	        .put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
	            //button.doClick();
				if(btnQuickReturn_1.getText().equals("QUICK RETURN (F8)"))
				{
					btnQuickReturn_1.setText("CANCEL RETURN (F8)");
					txtDisc.requestFocus();
					txtDisc.setBackground(new Color(243, 229, 216));
					txtDisc.setForeground(Color.black);
					txtDisc.setCaretColor(Color.black);
					returns="return";
				}
				else
				{
					btnQuickReturn_1.setText("QUICK RETURN (F8)");
					txtDisc.setBackground(Color.BLACK);
					txtDisc.setCaretColor(Color.WHITE);
					txtDisc.setForeground(Color.WHITE);
					txtDisc.requestFocus();
					returns="";
				}
	        }
	    } );
	}
	public static void clickOnserch(final AbstractButton button, String actionName, int key,final String username ,final String company,final String store_name,final String posreg,final String version ,final String decimal,final Connection con,final String language)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
				getitemlist(company, username, store_name, posreg, version, decimal, con, language);
	        }
	    } );
	}
	public static void clickOnCash(final AbstractButton button, String actionName, int key,final String username ,final String company,final String store_name,final String posreg,final String version,final Connection con)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
				cash_settle(company, username, store_name, posreg,  version,"");
	        }
	    } );
	}
	public static void clickOnar(final AbstractButton button, String actionName, int key,final String username ,final String company,final String store_name,final String posreg,final Connection con,final String version)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
				ar_settle(company, username, store_name, posreg, con, version);
	        }
	    } );
	}
	public static void clickOnpaycode(final AbstractButton button, String actionName, int key,final String username ,final String company,final String store_name,final String posreg,final String db_name,final String version,final Connection con)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
				paycode_settle(company, username, store_name, posreg,version);
	        }
	    } );
	}
	public static void clickOndel(final AbstractButton button, String actionName, int key,final String username ,final String company,final String store_name,final String posreg, final String version)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
				delete(company, username, store_name, posreg,version);
	        }
	    } );
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
				posscreen.txtDisc.requestFocus();
	        }
	    } );
	}
	public static void get_cust_chooser(String company,String version,String username,String store_name,String posreg,Connection con)
	{
		try {
			Cust_Chooser.Active="1";
			btnNewButton.setEnabled(false);
			textField_9.setEnabled(false);
			new delivery_customer(company, version, "1", username, store_name, posreg, con, "cust_chooser");
			//new sales_master.Cust_Chooser( company,"pos1", username, store_name,"","", version,"1");
			txtDisc.requestFocus();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			posscreen.txtDisc.requestFocus();
		}
	}
	public static void get_register_name(String company,String username, String store_name,Connection con, String posreg){
		try {
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_users where user_name='"+username+"' ");
			if(resultSet.next())
			{
				
				ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_assign_register where ref_user_code='"+resultSet.getString("user_code")+"' and select_status='true' ");
				if(resultSet1.next())
				{
					String queryass="select * from "+company+".mr_register_setup where register_number='"+posreg+"' ";
					ResultSet resultSet2 = statement.executeQuery(queryass);
					if(resultSet2.next())
					{
						display_port=resultSet2.getString("pole_display_port");
						ready_msg1=resultSet2.getString("ready_msg1");
						ready_msg2=resultSet2.getString("ready_msg2");
						printer=resultSet2.getString("printer1");
						//printer2=resultSet2.getString("printer_port");
						if(old_recipt.equals(""))
						{
							receipt_num=Integer.parseInt(resultSet2.getString("invoice_number"))+Integer.parseInt(resultSet2.getString("next_num"));
							textField_8.setText(Integer.toString(receipt_num));
							if(gs.length>1)
							{
								SecondDisplay.textField_8.setText(Integer.toString(receipt_num));
							}
							
						}
						else
						{
							textField_8.setText(old_recipt);
							if(gs.length>1)
							{
							SecondDisplay.textField_8.setText(old_recipt);
							}
							old_recipt="";
						}
						cashdrawercode=resultSet2.getString("opening_codes");
						if(cashdrawercode.equals("Epson"))
						{
							open = new byte[]{27,112,48,55,121};
						}
						if(cashdrawercode.equals("Star"))
						{
							open = new byte[]{27,7,11,55,7};
						}
						if(cashdrawercode.equals("Citizen"))
						{
							open = new byte[]{27,112,48,55,121};
						}
						if(cashdrawercode.equals("Samsung"))
						{
							open = new byte[]{27,112,48,55,121};
						}
						if(cashdrawercode.equals("Tysso"))
						{
							open = new byte[]{27,112,0,50,(byte) 250};
						}
						
						ResultSet resultSet3 = statement.executeQuery("select * from "+company+".mr_register_controls where ref_register_num='"+resultSet2.getString("register_number")+"' ");
						if(resultSet3.next())
						{
							promt_print=resultSet3.getString("promt_print");
							table.setRowHeight(Integer.parseInt(resultSet3.getString("pos_screen_grid_hgt")));
						}
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frmRegisterNoS, "USER NOT FOUND");
				posscreen.txtDisc.requestFocus();
				
			}
			
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
		}
	}	
	public static void get_user_validate_dt(String company,String username, String store_name,String posreg)
	{
		try {
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_users where user_name='"+username+"' ");
			if(resultSet1.next())
			{
				ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_user_poscontrol where ref_user_code='"+resultSet1.getString("user_code")+"' ");
				if(resultSet.next())
				{
					if(resultSet.getString("UseradditemsnotfoundmakingPO").equals("1"))
					{
						ChangeHoldQty=true;
					}
					else
					{
						ChangeHoldQty=false;
					}
					
					
					
					if(resultSet.getString("OverrideGrossMargin").equals("1"))
					{
						checkprint_invoice_change=true;
					}
					else
					{
						checkprint_invoice_change=false;
					}
//					if(resultSet.getString("UseradditemsnotfoundmakingPO").equals("1"))
//					{
//						btnhistInvoice.setEnabled(true);
//					}
//					else
//					{
//						btnhistInvoice.setEnabled(false);
//					}
					
					if(resultSet.getString("ChangePrice").equals("1"))
					{
						btnPrice.setEnabled(true);
					}
					else
					{
						btnPrice.setEnabled(false);
					}
					if(resultSet.getString("AllowNoSale").equals("1"))
					{
						btnNoSale.setEnabled(true);
					}
					else
					{
						btnNoSale.setEnabled(false);
					}
					
					if(resultSet.getString("issue_store_credit").equals("1"))
					{
						btnStoreCredit.setEnabled(true);
					}
					else
					{
						btnStoreCredit.setEnabled(false);
					}
					
					if(resultSet.getString("userquick_retun").equals("1"))
					{
						btnQuickReturn.setEnabled(true);
					}
					else
					{
						btnQuickReturn.setEnabled(false);
					}
					if(resultSet.getString("usermake_sale_retrn").equals("1"))
					{
						btnQuickReturn_1.setEnabled(true);
					}
					else
					{
						btnQuickReturn_1.setEnabled(false);
					}
					if(resultSet.getString("AllowPayIn").equals("1"))
					{
						btnPayIn.setEnabled(true);
					}
					else
					{
						btnPayIn.setEnabled(false);
					}
					if(resultSet.getString("AllowPayOut").equals("1"))
					{
						btnPayOut.setEnabled(true);
					}
					else
					{
						btnPayOut.setEnabled(false);
					}
					if(resultSet.getString("DeleteLinespos").equals("1"))
					{
						btnDeleteLine.setEnabled(true);
					}
					else
					{
						btnDeleteLine.setEnabled(false);
					}
					if(resultSet.getString("userHoldinvoive_pos").equals("1"))
					{
						btnHold.setEnabled(true);
						btnUnhold.setEnabled(true);
					}
					else
					{
						btnHold.setEnabled(false);
						btnUnhold.setEnabled(false);
					}
					if(resultSet.getString("maxdisc_user_sale").equals("1"))
					{
						btnDisc.setEnabled(true);
						btnDiscAmt.setEnabled(true);
						disc_amt=resultSet.getString("discount_amount");
					}
					else
					{
						btnDisc.setEnabled(false);
						btnDiscAmt.setEnabled(false);
						disc_amt=resultSet.getString("discount_amount");
					}
					if(resultSet.getString("VoidCurrentTransation").equals("1"))
					{
						btnVoid.setEnabled(true);
					}
					else
					{
						btnVoid.setEnabled(false);
					}
					if(resultSet.getString("ManageSettledInvoices").equals("1"))
					{
						ManageSettledInvoices="1";
					}
					else
					{
						ManageSettledInvoices="0";
					}
					if(resultSet.getString("VoidSettledinvo").equals("1"))
					{
						VoidSettledinvo="1";
					}
					else
					{
						VoidSettledinvo="0";
					}
					if(resultSet.getString("User_Change_qnty_pos").equals("1"))
					{
						btnX.setEnabled(true);
					}
					else
					{
						btnX.setEnabled(false);
					}
					if(resultSet.getString("change_unit").equals("1"))
					{
						btnchangeUnit.setEnabled(true);
					}
					else
					{
						btnX.setEnabled(false);
					}
					if(resultSet.getString("seehistoryinvoice").equals("1"))
					{
						btnhistInvoice.setEnabled(true);
					}
					else
					{
						btnhistInvoice.setEnabled(false);
					}
					if(resultSet.getString("seedupinvoice").equals("1"))
					{
						btnduplicateBill.setEnabled(true);
					}
					else
					{
						btnduplicateBill.setEnabled(false);
					}
				}	
				get_store_validate_dt(company,store_name,username,posreg,con);
			}
			
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
	}
	public static void validate_barcode(String company, String input, String username, String store_name, String type,Connection con){
		try {
			Statement statement=con.createStatement();  
			if(input.equals(""))
			{
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(frmRegisterNoS, "Enter Some Data");
				posscreen.txtDisc.requestFocus();
			}
			else
			{
				if(input.length()==1)
				{
					String query2="SELECT * FROM "+company+".mr_tmp_item_master where activeForSale='1' and (item_code='"+input+"' or item_name='"+input+"' or barcode='"+input+"')";
					ResultSet resultSet2= statement.executeQuery(query2);
					if(resultSet2.next())
					{
						if(Float.parseFloat(resultSet2.getString("qty"))<=0 && AllowNegativeQuantity.equals("0"))
						{
							frmRegisterNoS.setAlwaysOnTop(true);
							Toolkit.getDefaultToolkit().beep();
							JOptionPane.showMessageDialog(frmRegisterNoS, "Item Not Available, Negative Qty Not Supported");
							posscreen.txtDisc.requestFocus();
						}
						else
						{
							String itemqty = resultSet2.getString("openitem_qty");
							String itemtype = resultSet2.getString("Open_Item");
							if(itemqty.equals("1") && (itemtype.equals("1")))
							{
								new open_item( company, resultSet2.getString("item_code"),type,input,"1","1");
								open_item.textField.requestFocus();
							}
							else if(itemqty.equals("1") && (itemtype.equals("0")))
							{
								new OpenQty(company, resultSet2.getString("item_code"),type,input,"1","");
								OpenQty.textField.requestFocus();
							}
							else if(itemqty.equals("0") && (itemtype.equals("1")))
							{
								new open_item( company, resultSet2.getString("item_code"),type,input,"1","0");
								open_item.textField.requestFocus();
							}
							else
							{
								item_result( company, resultSet2.getString("item_code"),type,input,"1",con,"",0,0);
							}
								
								
							
							
							
							
							
							
						}
					}
					else
					{
						
						//Toolkit.getDefaultToolkit().beep();
						if(Mainpage.mediacontrol.equals("Y"))
						{
							AudioInputStream audioInputStream;
		    				File file = new File("C:/MasterRetails/media/"+ItemNotFound);
		    				audioInputStream = AudioSystem.getAudioInputStream(file);
		    				Clip line;
		    				line = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioInputStream.getFormat()));
		    				line.open(audioInputStream);
		    				line.start();
		    				line.drain();
		    				audioInputStream.close();
								
						}
						JOptionPane.showMessageDialog(frmRegisterNoS, "Item Not Found");
						posscreen.txtDisc.requestFocus();
					}
				}
				else
				{
					String input_flag=input.substring(0, 2);
					if(input_flag.equals(flag))
					{
						String itemcode=input.substring(2, 7);
						//String chk_dgt=input.substring(7, 8);
						String weight=input.substring(7, 9);
						String weight2=input.substring(9,12);
						
						int sts=0;
						String cost="";
						String sp="";
						String qty="";
						String query2="SELECT weighing_sts,sp FROM "+company+".mr_tmp_item_master where item_code='"+itemcode+"' and activeForSale='1' ";
						ResultSet resultSet2= statement.executeQuery(query2);
						if(resultSet2.next())
						{
							
							if(barcode_label.equals("Qty"))
							{
								if(input.length()>13)
								{
									String totalprice1=input.substring(12,15);
									String totalprice2=input.substring(15,17);
									String ttlprice=totalprice1+"."+totalprice2;
									cost=ttlprice;
								}
								else
								{
									cost=resultSet2.getString("sp");
								}
								
								if(resultSet2.getString("weighing_sts").equals("N"))
								{
									int test=Integer.parseInt(weight)+Integer.parseInt(weight2);
									qty=Integer.toString(test);
								}
								else
								{
									if(weight.equals("00") && weight2.equals("000"))
									{
										qty="1.00";
									}
									else{
										qty=weight+"."+weight2;
									}
								}
							}
							else 
								
							{
								sp=resultSet2.getString("sp");
								//qty="1.00";
								String weight11=input.substring(7, 10);
								String weight12=input.substring(10,12);
								//cost=input.substring(7, 12);
								cost=weight11+"."+weight12;
								double qtytaken=Double.parseDouble(cost)/Double.parseDouble(sp);
								qty=Double.toString(qtytaken);
								cost=resultSet2.getString("sp");
								sts=1;
							}
							item_result( company, itemcode,type,input,qty,con,cost,sts,1);
						}
						else
						{
							if(Mainpage.mediacontrol.equals("Y"))
							{
								AudioInputStream audioInputStream;
			    				File file = new File("C:/MasterRetails/media/"+ItemNotFound);
			    				audioInputStream = AudioSystem.getAudioInputStream(file);
			    				Clip line;
			    				line = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioInputStream.getFormat()));
			    				line.open(audioInputStream);
			    				line.start();
			    				line.drain();
			    				audioInputStream.close();
								
							}
							JOptionPane.showMessageDialog(frmRegisterNoS, "Item Not Found");
							posscreen.txtDisc.requestFocus();	
							
						}
						
					}
					else
					{
						int cust_ind_count=2;
						if(!customer_index.equals(""))
						{
							cust_ind_count=Integer.parseInt(customer_index);
						}
						String input_flag1=input.substring(0, cust_ind_count);
						String test=input.substring(cust_ind_count);
						if(input_flag1.equals(customer_variable))
						{
							String cust_query="select * from "+company+".mr_customer_master where status='1' and cust_code='"+test+"' ";
							ResultSet resultSet10 = statement.executeQuery(cust_query);
//							int rowCount = model.getRowCount();
//							for (int i = rowCount - 1; i >= 0; i--) {
//								model.removeRow(i);
//							}
							if(resultSet10.next())
							{
								float bal= Float.parseFloat(resultSet10.getString("avail_credit"))+Float.parseFloat(resultSet10.getString("advance_amt"));
					            String name=resultSet10.getString("cust_name");
					            sales_master.posscreen.btnNewButton.setText(name);
	                			sales_master.posscreen.textField_6.setText(Float.toString(bal));
	                			sales_master.posscreen.textField_7.setText(resultSet10.getString("disc"));
	                			sales_master.posscreen.lblNewLabel_2.setText(resultSet10.getString("disc")+"%");
	                			if(gs.length>1)
								{
	                			sales_master.SecondDisplay.btnNewButton.setText(name);
								}
							}
						}
						else
						{
							
							String query2="SELECT * FROM "+company+".mr_tmp_item_master where activeForSale='1' and (item_code='"+input+"' or item_name='"+input+"' or barcode='"+input+"')";
							ResultSet resultSet2= statement.executeQuery(query2);
							if(resultSet2.next())
							{
								if(Float.parseFloat(resultSet2.getString("qty"))<=0 && AllowNegativeQuantity.equals("0"))
								{
									frmRegisterNoS.setAlwaysOnTop(true);
									Toolkit.getDefaultToolkit().beep();
									JOptionPane.showMessageDialog(frmRegisterNoS, "Item Not Available, Negative Qty Not Supported");
									posscreen.txtDisc.requestFocus();
								}
								else
								{
									String itemqty = resultSet2.getString("openitem_qty");
									String itemtype = resultSet2.getString("Open_Item");
									if(itemqty.equals("1") && (itemtype.equals("1")))
									{
										new open_item( company, resultSet2.getString("item_code"),type,input,"1","1");
										open_item.textField.requestFocus();
									}
									else if(itemqty.equals("1") && (itemtype.equals("0")))
									{
										new OpenQty(company, resultSet2.getString("item_code"),type,input,"1","");
										OpenQty.textField.requestFocus();
									}
									else if(itemqty.equals("0") && (itemtype.equals("1")))
									{
										new open_item( company, resultSet2.getString("item_code"),type,input,"1","0");
										open_item.textField.requestFocus();
									}
									else
									{
										item_result( company, resultSet2.getString("item_code"),type,input,"1",con,"",0,0);
									}
								}
							}
							else
							{
								
								//Toolkit.getDefaultToolkit().beep();
								if(Mainpage.mediacontrol.equals("Y"))
								{
									AudioInputStream audioInputStream;
				    				File file = new File("C:/MasterRetails/media/"+ItemNotFound);
				    				audioInputStream = AudioSystem.getAudioInputStream(file);
				    				Clip line;
				    				line = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioInputStream.getFormat()));
				    				line.open(audioInputStream);
				    				line.start();
				    				line.drain();
				    				audioInputStream.close();
										
								}
								JOptionPane.showMessageDialog(frmRegisterNoS, "Item Not Found");
								posscreen.txtDisc.requestFocus();
							}
						}
					}
				}
				
			}
		} catch (Exception e) {
			System.err.println(e);
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
	}
	public static void item_result(String company,String itemcode,String type,String barcode,String qty,Connection con,String cost,int sts, int scale_item) throws IOException{
		Statement statement;
		Statement statement1;
		try {
			double net_total=0;
			String disc_type="N";
			double item_disc=0;
			statement = con.createStatement();
			statement1 = con.createStatement();
			String commited_qty="";
			String query313="select commited_qty,item_name,item_vareint from "+company+".mr_tmp_item_master where item_code='"+itemcode+"' ";
			ResultSet resultSet321 = statement.executeQuery(query313);
			if(resultSet321.next())
			{
				commited_qty = resultSet321.getString("commited_qty");
				
				if(stats==false)
				{
					description=resultSet321.getString("item_name");
				}
				
				String item_vareint= resultSet321.getString("item_vareint");
				if(item_vareint.equals("1"))
				{
					if(stats==false)
					{
						new item_vareint(company, user_name, reg_nme, con, itemcode, description,type,store);
					}
				}
				else
				{
					stats=true;
				}
			}
			if(stats==true)
			{
				String query3="select * from "+company+".mr_tmp_item_master where barcode='"+barcode+"' ";
				ResultSet resultSet1 = statement.executeQuery(query3);
				if(resultSet1.next())
				{
					
					//description=resultSet1.getString("item_name");
					if(ApplyItemDiscount.equals("0"))
					{
						item_disc=0;
					}
					else
					{
						String query="SELECT percentage FROM "+company+".mr_promotion where to_date >= '"+formattedDate+"' and itemcode='"+itemcode+"'";
						ResultSet resultSet3= statement1.executeQuery(query);
						if(resultSet3.next())
						{
							item_disc=Double.parseDouble(resultSet3.getString("percentage"));
						}
					}
					
					String cust_tax="";
					String query="SELECT sub_category FROM "+company+".mr_customer_master where cust_name!='WALK IN' and cust_code='"+textField_9.getText()+"'";
					ResultSet resultSet3= statement1.executeQuery(query);
					if(resultSet3.next())
					{
						cust_tax=resultSet3.getString("sub_category");
					}
					
					String sp = "";
					String unit="";
					String department = resultSet1.getString("department");
					String vat_cst = resultSet1.getString("output_tax_rate");
					
					String inc_vat=resultSet1.getString("sp_include_tax");
					String output_tax=resultSet1.getString("output_tax");
					barcode=resultSet1.getString("barcode");
					String itemcost=resultSet1.getString("cost");
					String item_sp=resultSet1.getString("sp");
					double total=0;
					double vat_rate=0;
					double	cf=Double.parseDouble(resultSet1.getString("cf"));
					if(type.equals("return"))
					{
						if(qty.equals(""))
						{
							qty="-1";
						}
						else
						{
							qty="-"+qty;
						}
						
					}
					String itemtype = resultSet1.getString("Open_Item");
					if(itemtype.equals("1"))
					{
						sp=df2.format(Double.parseDouble(cost));
					}
					else
					{
						if(sts==1)
						{
							sp=df2.format(Double.parseDouble(cost));
						}
						else
						{
							sp=df2.format(Float.parseFloat(resultSet1.getString("sp")));
						}
					}
					
					if(sellprice.equals(""))
					{
						sellprice=sp;
					}
					
					if(Double.parseDouble(itemcost)>Double.parseDouble(sellprice))
					{
						if(Mainpage.mediacontrol.equals("Y"))
						{
							AudioInputStream audioInputStream;
		    				File file = new File("C:/MasterRetails/media/"+LessThanCost);
		    				audioInputStream = AudioSystem.getAudioInputStream(file);
		    				Clip line;
		    				line = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioInputStream.getFormat()));
		    				line.open(audioInputStream);
		    				line.start();
		    				line.drain();
		    				audioInputStream.close();
						}
						
	    				JOptionPane.showMessageDialog(frmRegisterNoS, "Cost is Greater Than Selling Price");
	    				
					}
					if(Double.parseDouble(sellprice)==0)
					{
						if(Mainpage.mediacontrol.equals("Y"))
						{
							AudioInputStream audioInputStream;
		    				File file = new File("C:/MasterRetails/media/"+ZeroPrice);
		    				audioInputStream = AudioSystem.getAudioInputStream(file);
		    				Clip line;
		    				line = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioInputStream.getFormat()));
		    				line.open(audioInputStream);
		    				line.start();
		    				line.drain();
		    				audioInputStream.close();
						}
						
	    				JOptionPane.showMessageDialog(frmRegisterNoS, "Cost is Greater Than Selling Price");
	    				
					}
					
					unit=resultSet1.getString("unit");
					//double disc_posamt=Double.parseDouble(textField_2.getText());
					double disc_cust=Double.parseDouble(textField_7.getText());
					
					double disc_perc=0;
					if(disc_perc==0)
					{
						disc_perc=item_disc;
						disc_type="I";
					}
					if(disc_perc==0)
					{
						disc_perc=disc_cust;
						disc_type="C";
					}
					if(disc_perc==0)
					{
						disc_type="N";
					}
					double disc_amt=0;
					double table_total=0;
					int row_count=table.getRowCount();
					
					if(!textField_2.getText().equals("0"))
					{
						for(int i=0;i<row_count;i++)
						{
							String unitprice=table.getValueAt(i, 4).toString();
							double ttl_tbl=Double.parseDouble(unitprice)*Double.parseDouble(table.getValueAt(i, 5).toString());
							table_total=table_total+ttl_tbl;
						}
						table_total=table_total+Double.parseDouble(sellprice);
						for(int i=0;i<row_count;i++)
						{
							String disctype=table.getValueAt(i, 15).toString();
							if(disctype.equals("N"))
							{
								String unit_price=table.getValueAt(i, 4).toString();
								String qtys=table.getValueAt(i, 5).toString();
								String types=table.getValueAt(i, 10).toString();
								String vat_table_itm=table.getValueAt(i, 16).toString();
								double discamt=0;
								double discperc=0;
								double new_ttl=0;
								double new_vat=0;
								double ttlprice=Double.parseDouble(unit_price)*Double.parseDouble(qtys);
								if(types.equals("EXC"))
								{
									discamt=((discperc)*(ttlprice))/100;
									new_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qtys))-discamt;
									new_vat=new_ttl*Double.parseDouble(vat_table_itm)/100;
								}
								if(types.equals("INC"))
								{
									discamt=((discperc)*(ttlprice))/100;
									double vat_amts=Double.parseDouble(qtys)*Double.parseDouble(unit_price)*Double.parseDouble(vat_table_itm)/105;
									new_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qtys))-vat_amts;
									new_vat=vat_amts;
								}
								if(types.equals("NON"))
								{
									discamt=((discperc)*(ttlprice))/100;
									new_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qtys))-discamt;
									new_vat=0.00;
								}
								table.setValueAt(df2.format(discperc), i, 6);
								table.setValueAt(df2.format(new_ttl), i, 7);
								table.setValueAt(df2.format(new_vat), i, 8);
								table.setValueAt(df2.format(new_ttl+new_vat), i, 9);
								if(gs.length>1)
								{
								SecondDisplay.table.setValueAt(df2.format(discperc), i, 6);
								SecondDisplay.table.setValueAt(df2.format(new_ttl), i, 7);
								SecondDisplay.table.setValueAt(df2.format(new_vat), i, 8);
								SecondDisplay.table.setValueAt(df2.format(new_ttl+new_vat), i, 9);
								}
							}
							
						}
					}

					disc_amt=((disc_perc)*(Double.parseDouble(sellprice)))/100;
					double new_unit=Double.parseDouble(sellprice)-disc_amt;
					
					if(allow_store_tax.equals("1"))
					{
						inc_vat="0";
						output_tax="";
					}
					if(!cust_tax.equals(""))
					{
						if(cust_tax.equals("INC"))
						{
							inc_vat="1";
							output_tax="";
						}
						if(cust_tax.equals("EXC"))
						{
							inc_vat="0";
							output_tax="1";
						}
						if(cust_tax.equals("NON"))
						{
							inc_vat="0";
							output_tax="";
						}
					}
					
					
//					if(posscreen.change_hold_type.equals("1"))
//					{
//						description=description+" "+unit;
//					}
					String vattype;
					if(inc_vat.equals("1"))
					{
						vattype="INC";
						double vat_amt=Double.parseDouble(qty)*new_unit*Double.parseDouble(vat_cst)/105;
						if(type.equals("return"))
						{
							new_unit=(new_unit*Double.parseDouble(qty))-vat_amt;
						}
						else
						{
							new_unit=(new_unit*Double.parseDouble(qty))-vat_amt;
						}
						total=new_unit;
		        		
		        		if(qty.equals("-1"))
		        		{
		        			vat_amt=Double.parseDouble(sellprice)+new_unit;
		        			vat_amt=-vat_amt;
		        		}
		        		vat_rate=vat_amt;
		        		net_total=total+vat_rate;
		        		model.addRow(new Object[]{table.getRowCount()+1,barcode,"<html>"+description+"</html>",unit,sellprice,qty,disc_perc,df2.format(total),df2.format(vat_amt),df2.format(net_total),vattype,itemcost,item_sp,itemcode,commited_qty,disc_type,vat_cst,'0',department,'0',reg_nme,"",cf,'0','0',checkbillstatus});
		        		if(gs.length>1)
						{
		        		SecondDisplay.model.addRow(new Object[]{SecondDisplay.table.getRowCount()+1,barcode,"<html>"+description+"</html>",unit,sellprice,qty,disc_perc,df2.format(total),df2.format(vat_amt),df2.format(net_total),vattype,itemcost,item_sp,itemcode,commited_qty,disc_type,vat_cst,'0',department,'0',reg_nme,"",cf,'0',checkbillstatus});
						}
						
					}	
					else
					{
						if(output_tax.equals(""))
						{
							vattype="NON";
							total=new_unit*Double.parseDouble(qty);
							vat_rate=0;
							net_total=total+vat_rate;
							model.addRow(new Object[]{table.getRowCount()+1,barcode,"<html>"+description+"</html>",unit,sellprice,qty,disc_perc,df2.format(total),df2.format(vat_rate),df2.format(net_total),vattype,itemcost,item_sp,itemcode,commited_qty,disc_type,vat_cst,'0',department,'0',reg_nme,"",cf,'0','0',checkbillstatus});
							if(gs.length>1)
							{
							SecondDisplay.model.addRow(new Object[]{SecondDisplay.table.getRowCount()+1,barcode,"<html>"+description+"</html>",unit,sellprice,qty,disc_perc,df2.format(total),df2.format(vat_rate),df2.format(net_total),vattype,itemcost,item_sp,itemcode,commited_qty,disc_type,vat_cst,'0',department,'0',reg_nme,"",cf,'0',checkbillstatus});
							}
			        		
						}
						else
						{
							vattype="EXC";
							total=new_unit*Double.parseDouble(qty);
							vat_rate=0;
							if(!vat_cst.equals("NULL"))
							{
								vat_rate=total*Double.parseDouble(vat_cst)/100;
							}
							net_total=total+vat_rate;
							model.addRow(new Object[]{table.getRowCount()+1,barcode,"<html>"+description+"</html>",unit,sellprice,qty,disc_perc,df2.format(total),df2.format(vat_rate),df2.format(net_total),vattype,itemcost,item_sp,itemcode,commited_qty,disc_type,vat_cst,'0',department,'0',reg_nme,"",cf,'0','0',checkbillstatus});
							if(gs.length>1)
							{
							SecondDisplay.model.addRow(new Object[]{SecondDisplay.table.getRowCount()+1,barcode,"<html>"+description+"</html>",unit,sellprice,qty,disc_perc,df2.format(total),df2.format(vat_rate),df2.format(net_total),vattype,itemcost,item_sp,itemcode,commited_qty,disc_type,vat_cst,'0',department,'0',reg_nme,"",cf,'0',checkbillstatus});
							}
			        		
						}
					}
					
					int count=table.getRowCount();
					double vat_ttl=0.0;
					double total_price=0.0;
					double ttl_qty=0;
					double totl=0;
					double vt=0;
					//double vatpric=0;
					//double tax_rate = 0;
					double ttl_bf_disc=0;
					double ttl_af_disc=0;
					for(int i=0;i<count;i++)
					{
						//model.setValueAt("0", i, 23);
						double unit_price=Double.parseDouble(table.getValueAt(i, 4).toString());
						double qty_ttl=Double.parseDouble(table.getValueAt(i, 5).toString());
						String applied_qty=table.getModel().getValueAt(i, 5).toString();
						ttl_qty+=Double.parseDouble(applied_qty);
						double total_val=0;
						String applied_ttl=table.getModel().getValueAt(i, 7).toString();
						if(applied_qty.equals("-1"))
						{
							total_val=(Double.parseDouble(applied_ttl));//*Double.parseDouble(applied_qty));
						}
						else
						{
							total_val=Double.parseDouble(applied_ttl);//*Double.parseDouble(applied_qty);
						}
						
						
						String vat_type=table.getModel().getValueAt(i, 10).toString();
						String ttl_vat=table.getModel().getValueAt(i, 8).toString();
						vat_ttl+= Double.parseDouble(ttl_vat);
						total_price+=total_val;
						totl=Double.parseDouble(table.getValueAt(i, 7).toString());
						vt=Double.parseDouble(table.getValueAt(i, 8).toString());
						if(vat_type.equals("INC"))
						{
							ttl_af_disc+=totl+vt;
						}
						else
						{
							ttl_af_disc+=totl;
						}
						
						
						String ttl_before=df2.format(unit_price*qty_ttl);
						
						ttl_bf_disc+=Double.parseDouble(ttl_before);
					}
					String before_disc=df2.format(ttl_bf_disc);
					
					textField_11.setText(before_disc);
					if(gs.length>1)
					{
					SecondDisplay.textField_11.setText(before_disc);
					}
					String after_disc=df2.format(ttl_af_disc);
					double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
					if(Double.isNaN(ttl_disc_perc))
					{
						ttl_disc_perc=0;
					}
					double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
					
//					if(disc_type.equals("N"))
//					{
//						ttl_disc_amt=0;
//						ttl_disc_perc=0;
//					}
	    			//String itm_disc=Double.toString(ttl_disc_perc);
					
	        		double grand_ttl=total_price+vat_ttl;
	        		
	        		double checksum=0;
	        		if(allow_roundoff.equals("1"))
	        		{
	        			String nettotal=df2.format(grand_ttl);
		        		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
		        		int number_decimal=Integer.parseInt(splitnumber);
	        		
		        		if(number_decimal>0 && number_decimal<13 )
		        		{
		        			checksum=0-number_decimal;
		        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
		        		}
		        		if(number_decimal>12 && number_decimal<26 )
		        		{
		        			checksum=25-number_decimal;
		        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
		        		}
		        		if(number_decimal>25 && number_decimal<38 )
		        		{
		        			checksum=25-number_decimal;
		        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
		        		}
		        		if(number_decimal>37 && number_decimal<51 )
		        		{
		        			checksum=50-number_decimal;
		        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
		        		}
		        		if(number_decimal>50 && number_decimal<63 )
		        		{
		        			checksum=50-number_decimal;
		        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
		        		}
		        		if(number_decimal>62 && number_decimal<76 )
		        		{
		        			checksum=75-number_decimal;
		        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
		        		}
		        		if(number_decimal>75 && number_decimal<88 )
		        		{
		        			checksum=75-number_decimal;
		        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
		        		}
		        		if(number_decimal>87 && number_decimal<101 )
		        		{
		        			checksum=100-number_decimal;
		        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
		        		}
	        		}
	        		lblNewLabel_1.setText(Double.toString(checksum/100));
	        		if(gs.length>1)
					{
	        		SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
					}
	        		double total_line=total+vat_rate;
	        		String[] tokens = null;
	        		int length = description.length();
	        		int price_space;
	        		int print_price;
	        		String get_name;
	        		String space="";
	        		if(length>12)
	        		{
	        			int size = 12;
	        			tokens = description.split("(?<=\\G.{" + size + "})");
	        			String ttl_st=df2.format(total_line);
	        			
	        			print_price=8-ttl_st.length();
	        			for(int i=0; i<print_price;i++)
	        			{
	        				space+=" ";
	        			}
	        			get_name=tokens[0];//+""+space+""+df2.format(total_line);
	        		}
	        		else
	        		{
	        			price_space=12-length;
	        			for(int i=0;i<price_space;i++)
	        			{
	        				space+=" ";
	        			}
	        			String ttl_st=df2.format(total_line);
	        			print_price=8-ttl_st.length();
	        			for(int i=0; i<print_price;i++)
	        			{
	        				space+=" ";
	        			}
	        			get_name=description;//+""+space+""+df2.format(total_line)
	        		}
	        		String gap="";
	        		String grnd_ttl=df2.format(grand_ttl);
	    			int grand_price=15-grnd_ttl.length();
	    			for(int i=0; i<grand_price;i++)
	    			{
	    				gap+=" ";
	    			}
	    			
	    			Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+get_name+""+space+""+df2.format(total_line)+">"+display_port+"&&ECHO TOTAL"+gap+""+df2.format(grand_ttl)+">"+display_port+"\"");
//	        		double discount=0;
//	    			if(disc_cust!=0)
//	        		{
//	        			 discount=disc_amt+disc_posamt;
//	        		}
//	        		else
//	        		{
//	        			 discount=disc_amt;
//	        		}
	    			
					textField.setText(df2.format(total_price));
					textField_1.setText(df2.format(vat_ttl));
					textField_4.setText(df2.format(ttl_qty));
					textField_3.setText(df2.format(grand_ttl));
					textField_5.setText(df2.format(ttl_disc_perc)+"%");
					textField_2.setText(df2.format(ttl_disc_amt));
					
					table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
					table.addRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
					
					
					if(gs.length>1)
					{
					SecondDisplay.textField.setText(df2.format(total_price));
					SecondDisplay.textField_1.setText(df2.format(vat_ttl));
					SecondDisplay.textField_4.setText(df2.format(ttl_qty));
					SecondDisplay.textField_3.setText(df2.format(grand_ttl));
					SecondDisplay.textField_5.setText(df2.format(ttl_disc_perc)+"%");
					SecondDisplay.textField_2.setText(df2.format(ttl_disc_amt));
					SecondDisplay.table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
					SecondDisplay.table.addRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
					
					}
					
					
					
					returns="";
					
					txtDisc.requestFocus();
				}
				else
				{
					String query113="select * from "+company+".mr_tmp_item_master where item_code='"+itemcode+"' ";
					ResultSet resultSet33 = statement.executeQuery(query113);
					if(resultSet33.next())
					{
						double total=0;
						double vat_rate=0;
						boolean add_item=false;
						if(scale_item==1)
						{
							int row_count=table.getRowCount();
							if(row_count!=0)
							{
								for(int i=0;i<row_count;i++)
								{
									if(itemcode.equals(table.getValueAt(i, 13).toString()))
									{
										if(pause_before_print.equals("1"))
										{
											if(table.getValueAt(i, 24).toString().equals("0"))
											{
												double amt_disc=0;
												double amt_ttl=0;
												double disc_perc = Double.parseDouble(table.getValueAt(i, 6).toString());
												double tax_rate = Double.parseDouble(table.getValueAt(i, 16).toString());
												String unit_price=table.getModel().getValueAt(i, 4).toString();
												String vattype=table.getModel().getValueAt(i, 10).toString();
												String amount=table.getModel().getValueAt(i, 7).toString();
												if(vattype.equals("EXC"))
												{
													if(Float.parseFloat(amount)<0)
													{
														amt_disc=(Double.parseDouble(unit_price)*Double.parseDouble(qty)*disc_perc)/100;
														amt_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qty))-amt_disc;
														total=amt_ttl;
														vat_rate=total*tax_rate/100;
													}
													else{
														amt_disc=(Double.parseDouble(unit_price)*Double.parseDouble(qty)*disc_perc)/100;
														amt_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qty))-amt_disc;
														total=amt_ttl;
														vat_rate=total*tax_rate/100;
													}
													
												}
												double unit_total;
												if(vattype.equals("INC"))
												{
													if(Float.parseFloat(amount)<0)
													{
														amt_disc=(Double.parseDouble(unit_price)*Double.parseDouble(qty)*disc_perc)/100;
														amt_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qty))-amt_disc;
														unit_total=-(amt_ttl);
														vat_rate=(unit_total*tax_rate)/105;
														total=unit_total-vat_rate;
													}
													else{
														amt_disc=(Double.parseDouble(unit_price)*Double.parseDouble(qty)*disc_perc)/100;
														amt_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qty))-amt_disc;
														unit_total=amt_ttl;
														//unit_total=Double.parseDouble(unit_price)*Double.parseDouble(qty);
														vat_rate=(unit_total*tax_rate)/105;
														total=unit_total-vat_rate;
													}
													
												}
												if(vattype.equals("NON"))
												{
													
													if(Float.parseFloat(amount)<0)
													{
														total=-(Double.parseDouble(unit_price)*Double.parseDouble(qty));
													}
													else{
														total=Double.parseDouble(unit_price)*Double.parseDouble(qty);
													}
													
												}
												if(total<0)
												{
													if(Double.parseDouble(qty) < 0)
													{
														model.setValueAt(qty,i, 5);
														if(gs.length>1)
														{
														SecondDisplay.model.setValueAt(qty,i, 5);
														}
													}
													else
													{
														model.setValueAt("-"+qty,i, 5);
														if(gs.length>1)
														{
														SecondDisplay.model.setValueAt("-"+qty,i, 5);
														}
													}
												}
												else{
													model.setValueAt(qty,i, 5);
													if(gs.length>1)
													{
													SecondDisplay.model.setValueAt(qty,i, 5);
													}
												}
												model.setValueAt("0", i, 23);
												model.setValueAt(df2.format(total),i, 7);
												model.setValueAt(df2.format(vat_rate),i, 8);
												model.setValueAt(df2.format(total+vat_rate),i, 9);
												model.setValueAt(1,i, 24);
												table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
												
												if(gs.length>1)
												{
												SecondDisplay.model.setValueAt(df2.format(total),i, 7);
												SecondDisplay.model.setValueAt(df2.format(vat_rate),i, 8);
												SecondDisplay.model.setValueAt(df2.format(total+vat_rate),i, 9);
												SecondDisplay.table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
												}
												add_item=false;
												i=row_count;
											}
											else
											{
												add_item=true;
											}
											
										}
										else
										{
											add_item=true;
										}
									}
									else
									{
										add_item=true;
									}
									
								}
							}
							else
							{
								add_item=true;
							}
							scale_item=0;
						}
						else
						{
							add_item=true;
						}
						if(add_item==true)
						{
							if(ApplyItemDiscount.equals("0"))
							{
								item_disc=0;
							}
							else
							{
								String query="SELECT percentage FROM "+company+".mr_promotion where to_date >= '"+formattedDate+"' and itemcode='"+itemcode+"'";
								ResultSet resultSet3= statement1.executeQuery(query);
								if(resultSet3.next())
								{
									item_disc=Double.parseDouble(resultSet3.getString("percentage"));
								}
							}
							
							
							String sp = "";
							String unit="";
							String department = resultSet33.getString("department");
							String vat_cst = resultSet33.getString("output_tax_rate");
							//description=resultSet33.getString("item_name");
							String inc_vat=resultSet33.getString("sp_include_tax");
							String output_tax=resultSet33.getString("output_tax");
							
							String itemcost=resultSet33.getString("cost");
							String item_sp=resultSet33.getString("sp");
							
							double	cf=Double.parseDouble(resultSet33.getString("cf"));
							
							if(type.equals("return"))
							{
								if(qty.equals(""))
								{
									qty="-1";
								}
								else
								{
									qty="-"+qty;
								}
								
							}
							String itemtype = resultSet33.getString("Open_Item");
							if(itemtype.equals("1"))
							{
								sp=df2.format(Double.parseDouble(cost));
							}
							else
							{
								if(sts==1)
								{
									sp=df2.format(Double.parseDouble(cost));
								}
								else
								{
									sp=df2.format(Float.parseFloat(resultSet33.getString("sp")));
									if(barcode_label.equals("qty"))
									{
										if(barcode.length()>13)
										{
											double costwouttax=Double.parseDouble(cost)-((Double.parseDouble(cost)*5)/105);
											double unitprice=costwouttax/Double.parseDouble(qty);
											sp=df2.format(unitprice);
										}
									}
									
									
								}
							}
							barcode=resultSet33.getString("barcode");
							if(sellprice.equals(""))
							{
								sellprice=sp;
							}
							
							unit=resultSet33.getString("unit");
							//double disc_posamt=Double.parseDouble(textField_2.getText());
							double disc_cust=Double.parseDouble(textField_7.getText());
							
							double disc_perc=0;
							if(disc_perc==0)
							{
								disc_perc=item_disc;
								disc_type="I";
							}
							if(disc_perc==0)
							{
								disc_perc=disc_cust;
								disc_type="C";
							}
							if(disc_perc==0)
							{
								disc_type="N";
							}
							double disc_amt=0;
							double table_total=0;
							int row_count=table.getRowCount();
							
							if(!textField_2.getText().equals("0"))
							{
								for(int i=0;i<row_count;i++)
								{
									String unitprice=table.getValueAt(i, 4).toString();
									double ttl_tbl=Double.parseDouble(unitprice)*Double.parseDouble(table.getValueAt(i, 5).toString());
									table_total=table_total+ttl_tbl;
								}
								table_total=table_total+Double.parseDouble(sellprice);
								for(int i=0;i<row_count;i++)
								{
									String vat_table_itm=table.getValueAt(i, 16).toString();
									String disctype=table.getValueAt(i, 15).toString();
									if(disctype.equals("N"))
									{
										String unit_price=table.getValueAt(i, 4).toString();
										String qtys=table.getValueAt(i, 5).toString();
										String types=table.getValueAt(i, 10).toString();
										double discamt=0;
										double discperc=0;
										double new_ttl=0;
										double new_vat=0;
										double ttlprice=Double.parseDouble(unit_price)*Double.parseDouble(qtys);
										if(types.equals("EXC"))
										{
											discamt=((discperc)*(ttlprice))/100;
											new_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qtys))-discamt;
											new_vat=new_ttl*Double.parseDouble(vat_table_itm)/100;
										}
										if(types.equals("INC"))
										{
											discamt=((discperc)*(ttlprice))/100;
											double vat_amts=Double.parseDouble(qtys)*Double.parseDouble(unit_price)*Double.parseDouble(vat_table_itm)/105;
											new_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qtys))-vat_amts;
											new_vat=vat_amts;
										}
										if(types.equals("NON"))
										{
											discamt=((discperc)*(ttlprice))/100;
											new_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qtys))-discamt;
											new_vat=0.00;
										}
										table.setValueAt(df2.format(discperc), i, 6);
										table.setValueAt(df2.format(new_ttl), i, 7);
										table.setValueAt(df2.format(new_vat), i, 8);
										table.setValueAt(df2.format(new_ttl+new_vat), i, 9);
										if(gs.length>1)
										{
										SecondDisplay.table.setValueAt(df2.format(discperc), i, 6);
										SecondDisplay.table.setValueAt(df2.format(new_ttl), i, 7);
										SecondDisplay.table.setValueAt(df2.format(new_vat), i, 8);
										SecondDisplay.table.setValueAt(df2.format(new_ttl+new_vat), i, 9);
										}
									}
									
								}
							}

							disc_amt=((disc_perc)*(Double.parseDouble(sellprice)))/100;
							double new_unit=Double.parseDouble(sellprice)-disc_amt;
//							if(posscreen.change_hold_type.equals("1"))
//							{
//								description=description+" "+unit;
//							}
							if(allow_store_tax.equals("1"))
							{
								inc_vat="0";
								output_tax="";
							}
							
							String vattype;
							if(inc_vat.equals("1"))
							{
								vattype="INC";
								double vat_amt=Double.parseDouble(qty)*new_unit*Double.parseDouble(vat_cst)/105;
								if(type.equals("return"))
								{
									new_unit=(new_unit*Double.parseDouble(qty))-vat_amt;
								}
								else
								{
									new_unit=(new_unit*Double.parseDouble(qty))-vat_amt;
								}
								total=new_unit;
				        		
//				        		if(qty.equals("-1"))
//				        		{
//				        			vat_amt=new_unit-Double.parseDouble(sellprice);
//				        		}
				        		vat_rate=vat_amt;
				        		net_total=total+vat_rate;
				        		model.addRow(new Object[]{table.getRowCount()+1,barcode,"<html>"+description+"</html>",unit,sellprice,qty,disc_perc,df2.format(total),df2.format(vat_amt),df2.format(net_total),vattype,itemcost,item_sp,itemcode,commited_qty,disc_type,vat_cst,'0',department,'0',reg_nme,"",cf,'0','0',checkbillstatus});
				        		if(gs.length>1)
								{
				        		SecondDisplay.model.addRow(new Object[]{SecondDisplay.table.getRowCount()+1,barcode,"<html>"+description+"</html>",unit,sellprice,qty,disc_perc,df2.format(total),df2.format(vat_amt),df2.format(net_total),vattype,itemcost,item_sp,itemcode,commited_qty,disc_type,vat_cst,'0',department,'0',reg_nme,"",cf,'0',checkbillstatus});
				        		}
								
							}	
							else
							{
								if(output_tax.equals(""))
								{
									vattype="NON";
									total=new_unit*Double.parseDouble(qty);
									vat_rate=0;
									net_total=total+vat_rate;
									model.addRow(new Object[]{table.getRowCount()+1,barcode,"<html>"+description+"</html>",unit,sellprice,qty,disc_perc,df2.format(total),df2.format(vat_rate),df2.format(net_total),vattype,itemcost,item_sp,itemcode,commited_qty,disc_type,vat_cst,'0',department,'0',reg_nme,"",cf,'0','0',checkbillstatus});
									if(gs.length>1)
									{
									SecondDisplay.model.addRow(new Object[]{SecondDisplay.table.getRowCount()+1,barcode,"<html>"+description+"</html>",unit,sellprice,qty,disc_perc,df2.format(total),df2.format(vat_rate),df2.format(net_total),vattype,itemcost,item_sp,itemcode,commited_qty,disc_type,vat_cst,'0',department,'0',reg_nme,"",cf,'0',checkbillstatus});
									}
					        		
								}
								else
								{
									vattype="EXC";
									total=new_unit*Double.parseDouble(qty);
									vat_rate=0;
									if(!vat_cst.equals("NULL"))
									{
										vat_rate=total*Double.parseDouble(vat_cst)/100;
									}
									net_total=total+vat_rate;
									model.addRow(new Object[]{table.getRowCount()+1,barcode,"<html>"+description+"</html>",unit,sellprice,qty,disc_perc,df2.format(total),df2.format(vat_rate),df2.format(net_total),vattype,itemcost,item_sp,itemcode,commited_qty,disc_type,vat_cst,'0',department,'0',reg_nme,"",cf,'0','0',checkbillstatus});
									if(gs.length>1)
									{
									SecondDisplay.model.addRow(new Object[]{SecondDisplay.table.getRowCount()+1,barcode,"<html>"+description+"</html>",unit,sellprice,qty,disc_perc,df2.format(total),df2.format(vat_rate),df2.format(net_total),vattype,itemcost,item_sp,itemcode,commited_qty,disc_type,vat_cst,'0',department,'0',reg_nme,"",cf,'0',checkbillstatus});
									}
					        		
								}
							}
						}
						
						
						
						int count=table.getRowCount();
						double vat_ttl=0.0;
						double total_price=0.0;
						double ttl_qty=0;
						double totl=0;
						double vt=0;
						//double vatpric=0;
						//double tax_rate = 0;
						double ttl_bf_disc=0;
						double ttl_af_disc=0;
						for(int i=0;i<count;i++)
						{
							//model.setValueAt("0", i, 23);
							double unit_price=Double.parseDouble(table.getValueAt(i, 4).toString());
							double qty_ttl=Double.parseDouble(table.getValueAt(i, 5).toString());
							String applied_qty=table.getModel().getValueAt(i, 5).toString();
							ttl_qty+=Double.parseDouble(applied_qty);
							double total_val=0;
							String applied_ttl=table.getModel().getValueAt(i, 7).toString();
							if(applied_qty.equals("-1"))
							{
								total_val=(Double.parseDouble(applied_ttl));//*Double.parseDouble(applied_qty));
							}
							else
							{
								total_val=Double.parseDouble(applied_ttl);//*Double.parseDouble(applied_qty);
							}
							
							
							String vat_type=table.getModel().getValueAt(i, 10).toString();
							String ttl_vat=table.getModel().getValueAt(i, 8).toString();
							vat_ttl+= Double.parseDouble(ttl_vat);
							total_price+=total_val;
							totl=Double.parseDouble(table.getValueAt(i, 7).toString());
							vt=Double.parseDouble(table.getValueAt(i, 8).toString());
							if(vat_type.equals("INC"))
							{
								ttl_af_disc+=totl+vt;
							}
							else
							{
								ttl_af_disc+=totl;
							}
							String ttl_before=df2.format(unit_price*qty_ttl);
							
							ttl_bf_disc+=Double.parseDouble(ttl_before);
						}
						String before_disc=df2.format(ttl_bf_disc);
						textField_11.setText(before_disc);
						if(gs.length>1)
						{
						SecondDisplay.textField_11.setText(before_disc);
						}
						String after_disc=df2.format(ttl_af_disc);
						//String before_disc="0";//df2.format(ttl_bf_disc);
						//textField_11.setText(df2.format(total_price));
						//String after_disc="0";//df2.format(ttl_af_disc);
						double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
						if(Double.isNaN(ttl_disc_perc))
						{
							ttl_disc_perc=0;
						}
						
						double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
						if(disc_type.equals("N"))
						{
							ttl_disc_amt=0;
							ttl_disc_perc=0;
						}
		    			//String itm_disc=Double.toString(ttl_disc_perc);
						
		        		double grand_ttl=total_price+vat_ttl;
		        		
		        		double checksum=0;
		        		if(allow_roundoff.equals("1"))
		        		{
		        			String nettotal=df2.format(grand_ttl);
			        		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
			        		int number_decimal=Integer.parseInt(splitnumber);
		        		
			        		if(number_decimal>0 && number_decimal<13 )
			        		{
			        			checksum=0-number_decimal;
			        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
			        		}
			        		if(number_decimal>12 && number_decimal<26 )
			        		{
			        			checksum=25-number_decimal;
			        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
			        		}
			        		if(number_decimal>25 && number_decimal<38 )
			        		{
			        			checksum=25-number_decimal;
			        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
			        		}
			        		if(number_decimal>37 && number_decimal<51 )
			        		{
			        			checksum=50-number_decimal;
			        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
			        		}
			        		if(number_decimal>50 && number_decimal<63 )
			        		{
			        			checksum=50-number_decimal;
			        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
			        		}
			        		if(number_decimal>62 && number_decimal<76 )
			        		{
			        			checksum=75-number_decimal;
			        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
			        		}
			        		if(number_decimal>75 && number_decimal<88 )
			        		{
			        			checksum=75-number_decimal;
			        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
			        		}
			        		if(number_decimal>87 && number_decimal<101 )
			        		{
			        			checksum=100-number_decimal;
			        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
			        		}
		        		}
		        		lblNewLabel_1.setText(Double.toString(checksum/100));
		        		if(gs.length>1)
						{
		        		SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
						}
		        		
		        		double total_line=total+vat_rate;
		        		String[] tokens = null;
		        		int length = description.length();
		        		int price_space;
		        		int print_price;
		        		String get_name;
		        		String space="";
		        		if(length>12)
		        		{
		        			int size = 12;
		        			tokens = description.split("(?<=\\G.{" + size + "})");
		        			String ttl_st=df2.format(total_line);
		        			
		        			print_price=8-ttl_st.length();
		        			for(int i=0; i<print_price;i++)
		        			{
		        				space+=" ";
		        			}
		        			get_name=tokens[0];//+""+space+""+df2.format(total_line);
		        		}
		        		else
		        		{
		        			price_space=12-length;
		        			for(int i=0;i<price_space;i++)
		        			{
		        				space+=" ";
		        			}
		        			String ttl_st=df2.format(total_line);
		        			print_price=8-ttl_st.length();
		        			for(int i=0; i<print_price;i++)
		        			{
		        				space+=" ";
		        			}
		        			get_name=description;//+""+space+""+df2.format(total_line)
		        		}
		        		String gap="";
		        		String grnd_ttl=df2.format(grand_ttl);
		    			int grand_price=15-grnd_ttl.length();
		    			for(int i=0; i<grand_price;i++)
		    			{
		    				gap+=" ";
		    			}
		    			
		    			Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+get_name+""+space+""+df2.format(total_line)+">"+display_port+"&&ECHO TOTAL"+gap+""+df2.format(grand_ttl)+">"+display_port+"\"");
//		        		double discount=0;
//		    			if(disc_cust!=0)
//		        		{
//		        			 discount=disc_amt+disc_posamt;
//		        		}
//		        		else
//		        		{
//		        			 discount=disc_amt;
//		        		}
		    			
						textField.setText(df2.format(total_price));
						textField_1.setText(df2.format(vat_ttl));
						textField_4.setText(df2.format(ttl_qty));
						textField_3.setText(df2.format(grand_ttl));
						textField_5.setText(df2.format(ttl_disc_perc)+"%");
						textField_2.setText(df2.format(ttl_disc_amt));
						
						table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
						table.addRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
						
						if(gs.length>1)
						{
						SecondDisplay.textField.setText(df2.format(total_price));
						SecondDisplay.textField_1.setText(df2.format(vat_ttl));
						SecondDisplay.textField_4.setText(df2.format(ttl_qty));
						SecondDisplay.textField_3.setText(df2.format(grand_ttl));
						SecondDisplay.textField_5.setText(df2.format(ttl_disc_perc)+"%");
						SecondDisplay.textField_2.setText(df2.format(ttl_disc_amt));
						
						SecondDisplay.table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
						SecondDisplay.table.addRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
						}
						returns="";
						
						txtDisc.requestFocus();
					}
				}
				
				//textField_10.setText("0");
				btnQuickReturn_1.setText("QUICK RETURN (F8)");
				if(posscreen.change_hold_type.equals("1"))
				{
					if(posscreen.item_comment.equals("1"))
					{
						new comment_item(company,"");
					}
				}
			}
			stats=false;
			sellprice="";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}  
		
	}
	public static void get_store_validate_dt(String company,String store_name,String username, String posreg,Connection con)
	{
		try {
			if(con!=null)
			{
				status="Connected";
			}
			else
			{
				status="Disconnected";
			}
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store where store_id='"+store_name+"' ");
			if(resultSet.next())
			{
				if(resultSet.getString("ChangeCustomerAt").equals("1"))
				{
					btnNewCustomer.setEnabled(true);
					btncustStatement.setEnabled(true);
					btncustPay.setEnabled(true);
					btnNewButton.setEnabled(true);
				}
				else
				{
					btnNewCustomer.setEnabled(false);
					btncustStatement.setEnabled(false);
					btncustPay.setEnabled(false);
					btnNewButton.setEnabled(false);
				}
				barcode_label=resultSet.getString("barcode_label");
				if(resultSet.getString("apply_store_credit").equals("1"))
				{
					btnStoreCredit.setEnabled(true);
				}
				decimalPlaces=Integer.parseInt(resultSet.getString("decimal_count"));
				if(decimalPlaces==2)
				{
					df2=new DecimalFormat("0.00");
				}
				else
				{
					df2=new DecimalFormat("0.000");
				}
				//consildate_line=Integer.parseInt(resultSet.getString("ConsolidatedPosLines"));
				AllowNegativeQuantity=resultSet.getString("AllowNegativeQuantity");
				flag=resultSet.getString("flag");
				allow_roundoff=resultSet.getString("allow_roundoff");
				fastitem_Screen=resultSet.getString("UseLoyalityProgram");
				CustDisc=resultSet.getString("ApplyCustomerDiscount");
				change_hold_type=resultSet.getString("change_hold_type");
				ApplyItemDiscount=resultSet.getString("ApplyItemDiscount");
			}
			get_register_validate_dt(company,username,posreg,con);
			get_store_accounts_dt(company,store_name,con);
			
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
	}
	private void click_btn(String num)
	{
		try{
			String pass_string=txtDisc.getText();
			pass_string+=num;
			txtDisc.setText(pass_string);
			txtDisc.requestFocus();
		}
		catch(Exception e){
			System.err.println(e);
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
	}
	public static void get_store_accounts_dt(String company,String store_name,Connection con)
	{
		try {
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store_accounts where ref_store_id='"+store_name+"' ");
			if(resultSet.next())
			{
				store_credit_validity=resultSet.getString("store_credit_validity");
			}
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
	}
	public static void get_register_validate_dt(String company,String username, String posreg,Connection con)
	{
		try {
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_register_controls where ref_register_num='"+posreg+"' ");
			if(resultSet.next())
			{
				hold_bill=resultSet.getString("actve_for_transaction");
				allow_store_tax=resultSet.getString("allow_store_disc");
				fastitem_mode=resultSet.getString("barcode_col_disp_pos_scrn");
				CashChange=resultSet.getString("retn_sale_mode_aftr_sale_rtn");
				print_duplicate_invoice=resultSet.getString("print_duplicate_invoice");
				pause_before_print=resultSet.getString("pause_before_print");
				unhold_reg_hold_trans=resultSet.getString("unhold_reg_hold_trans");
				restrict_delivery_boy=resultSet.getString("print_hold");
				duplicate_holdinv=resultSet.getString("print_duplicate_hold");
				PauseHoldPrint=resultSet.getString("PauseHoldPrint");
				pause_kot_print=resultSet.getString("pause_after_print");
				if(resultSet.getString("registerAsWaiter").equals("1"))
				{
					btnAR.setEnabled(false);
					btnCash.setEnabled(false);
					btnPayCode.setEnabled(false);
					button_4.setEnabled(false);
					button_5.setEnabled(false);
					button_6.setEnabled(false);
					button_9.setEnabled(false);
					
				}
				else
				{
					btnAR.setEnabled(true);
					btnCash.setEnabled(true);
					btnPayCode.setEnabled(true);
					button_4.setEnabled(true);
					button_5.setEnabled(true);
					button_6.setEnabled(true);
					button_9.setEnabled(true);
					
				}
				if(resultSet.getString("register_all_invoice").equals("1"))
				{
					register_all_invoice="1";
				}
				else
				{
					register_all_invoice="0";
				}
				if(resultSet.getString("itm_desc_arabic_pos_scrn").equals("1"))
				{
					item_comment="1";
				}
				else
				{
					item_comment="0";
				}
			}
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
	}
	public static void get_hold_invoice_dt(String company,String recipt_no,String reg_nme,Connection con)
	{
		try{
			//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_pos_hold_invoice where reg_num='"+reg_nme+"'and receipt_no='"+recipt_no+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				old_recipt=textField_8.getText();
				current_register=reg_nme;
				
				textField_8.setText(resultSet.getString("receipt_no"));
				
				label.setText(resultSet.getString("table_num"));
				label_1.setText(resultSet.getString("delivery_user"));
				label_2.setText(resultSet.getString("mode"));
				//textField_3.setText(df2.format(Double.parseDouble(resultSet.getString("total"))));
				
				textField_4.setText(df2.format(Double.parseDouble(resultSet.getString("total_qty"))));
				
				String Chkbill_Sts=resultSet.getString("Chkbill_Sts"); 
				checkbillstatus=Chkbill_Sts;
				
				//textField_2.setText(df2.format(Double.parseDouble(resultSet.getString("disc"))));
				double sub_ttl=Double.parseDouble(resultSet.getString("total"))-Double.parseDouble(resultSet.getString("vat"));
				double vat_ttl =Double.parseDouble(resultSet.getString("vat"));
				BigDecimal br = new BigDecimal(sub_ttl);
        		br = br.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		sub_ttl = br.doubleValue();
				textField.setText(df2.format(sub_ttl));
				textField_1.setText(df2.format(vat_ttl));
				btnNewButton.setText(resultSet.getString("cust_name"));
				textField_12.setText(resultSet.getString("cust_ph"));
				
				if(gs.length>1)
				{
				SecondDisplay.textField_8.setText(resultSet.getString("receipt_no"));
				SecondDisplay.textField_4.setText(df2.format(Double.parseDouble(resultSet.getString("total_qty"))));
				SecondDisplay.textField.setText(df2.format(sub_ttl));
				SecondDisplay.textField_1.setText(df2.format(vat_ttl));
				SecondDisplay.btnNewButton.setText(resultSet.getString("cust_name"));
				}
				
				textField_9.setText(resultSet.getString("cust_code"));
				
				String query2="SELECT * FROM "+company+".mr_customer_master where cust_code='"+resultSet.getString("cust_code")+"' "; 
				ResultSet resultSet2 = statement.executeQuery(query2);
				if(resultSet2.next())
				{
					float bal= Float.parseFloat(resultSet2.getString("avail_credit"))+Float.parseFloat(resultSet2.getString("advance_amt"));
					textField_6.setText(Float.toString(bal));
				}
				
				double ttl_bf_disc=0;
				double ttl_af_disc=0;
				String query1="SELECT * FROM "+company+".mr_pos_hold_invoice_items where ref_reg_no='"+reg_nme+"' and ref_receipt_num='"+recipt_no+"' "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String item_code=resultSet1.getString("item_code");
					String barcode=resultSet1.getString("barcode");
					String department=resultSet1.getString("department");
					String item_desc="<html>"+resultSet1.getString("item_desc")+"</html>";
					String unit=resultSet1.getString("unit");
					double unit_price=Double.parseDouble(resultSet1.getString("unit_price"));
					double qty=Double.parseDouble(resultSet1.getString("qty"));
					double total=Double.parseDouble(resultSet1.getString("total"));
					double vat=Double.parseDouble(resultSet1.getString("vat"));
					double discount=Double.parseDouble(resultSet1.getString("discount"));
					double itemcost=Double.parseDouble(resultSet1.getString("cost"));
					double sp=Double.parseDouble(resultSet1.getString("sp"));
					String vat_type=resultSet1.getString("vat_type");
					String disc_type=resultSet1.getString("disc_type");
					String commited_qty=resultSet1.getString("commited_qty");
					double vat_perc=Double.parseDouble(resultSet1.getString("vat_perc"));
					String print_cmd=resultSet1.getString("print_cmd");
					String kot_number=resultSet1.getString("kot_number");
					String comments_item=resultSet1.getString("comments_item");
					String cf=resultSet1.getString("cf");
					String print_sts=resultSet1.getString("print_sts");
					String scale_sts=resultSet1.getString("scale_sts");
					
					model.addRow(new Object[]{model.getRowCount()+1,barcode,item_desc,unit,unit_price,qty,discount,total,vat,total+vat,vat_type,itemcost,sp,item_code,commited_qty,disc_type,vat_perc,print_cmd,department,kot_number,reg_nme,comments_item,cf,print_sts,scale_sts,checkbillstatus});
					
					if(gs.length>1)
					{
						SecondDisplay.model.addRow(new Object[]{model.getRowCount()+1,barcode,item_desc,unit,unit_price,qty,discount,total,vat,total+vat,vat_type,itemcost,sp,item_code,commited_qty,disc_type,vat_perc,print_cmd,department,kot_number,reg_nme,comments_item,cf,print_sts,checkbillstatus});
					}
					
					
					if(vat_type.equals("INC"))
					{
						ttl_af_disc+=total+vat;
					}
					else
					{
						ttl_af_disc+=total;
					}
					
					String ttl_before=df2.format(unit_price*qty);
					
					ttl_bf_disc+=Double.parseDouble(ttl_before);
//					ttl_bf_disc+=unit_price*qty;
					
					
					
				}
				String before_disc=df2.format(ttl_bf_disc);
				sales_master.posscreen.textField_11.setText(before_disc);
				if(gs.length>1)
				{
				sales_master.SecondDisplay.textField_11.setText(before_disc);
				}
				String after_disc=df2.format(ttl_af_disc);
				double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
				if(Double.isNaN(ttl_disc_perc))
				{
					ttl_disc_perc=0;
				}
				double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
				
				textField_2.setText(df2.format(ttl_disc_amt));
				textField_5.setText(df2.format(ttl_disc_perc)+"%");
				if(gs.length>1)
				{
				SecondDisplay.textField_2.setText(df2.format(ttl_disc_amt));
				SecondDisplay.textField_5.setText(df2.format(ttl_disc_perc)+"%");
				}
				double grand_ttl=sub_ttl+vat_ttl;
        		
        		double checksum=0;
        		if(allow_roundoff.equals("1"))
        		{
        			String nettotal=df2.format(grand_ttl);
	        		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
	        		int number_decimal=Integer.parseInt(splitnumber);
        		
	        		if(number_decimal>0 && number_decimal<13 )
	        		{
	        			checksum=0-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
	        		if(number_decimal>12 && number_decimal<26 )
	        		{
	        			checksum=25-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
	        		if(number_decimal>25 && number_decimal<38 )
	        		{
	        			checksum=25-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
	        		if(number_decimal>37 && number_decimal<51 )
	        		{
	        			checksum=50-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
	        		if(number_decimal>50 && number_decimal<63 )
	        		{
	        			checksum=50-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
	        		if(number_decimal>62 && number_decimal<76 )
	        		{
	        			checksum=75-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
	        		if(number_decimal>75 && number_decimal<88 )
	        		{
	        			checksum=75-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
	        		if(number_decimal>87 && number_decimal<101 )
	        		{
	        			checksum=100-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
        		}
        		lblNewLabel_1.setText(Double.toString(checksum/100));
        		
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				table.addRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
				
				
				
				String space="";
				int length=Integer.toString(model.getRowCount()).length();
	    		int print_price;
	    		print_price=8-length;
    			for(int i=0; i<print_price;i++)
    			{
    				space+=" ";
    			}
    			String gap="";
    			//double grand_ttl=Double.parseDouble(textField_3.getText());
	    		String grnd_ttl=df2.format(grand_ttl);
	    		
	    		textField_3.setText(grnd_ttl);
	    		
	    		if(gs.length>1)
				{
					SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
					SecondDisplay.table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
					SecondDisplay.table.addRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);	
					SecondDisplay.textField_3.setText(grnd_ttl);
				}
				int grand_price=15-grnd_ttl.length();
				for(int i=0; i<grand_price;i++)
				{
					gap+=" ";
				}
				Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO TOTAL ITEMS "+space+""+model.getRowCount()+">"+display_port+"&&ECHO TOTAL"+gap+""+df2.format(grand_ttl)+">"+display_port+"\"");
				//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO TOTAL ITEMS "+space+""+model.getRowCount()+"TOTAL"+""+gap+""+df2.format(grand_ttl)+">"+display_port+"\""});
			}
			posscreen.txtDisc.requestFocus();
			int count=table.getRowCount();
			if(count==0)
			{
				if(!old_recipt.equals(""))
				{
					JOptionPane.showMessageDialog(frmRegisterNoS, "Invoice Number is Restored");
					posscreen.txtDisc.requestFocus();
					sales_master.posscreen.get_register_name(company,  posscreen.user_name, "S01",con,posscreen.reg_nme);
					sales_master.posscreen.txtDisc.setText("");
					sales_master.posscreen.btnNewButton.setText("WALK IN");
					sales_master.posscreen.textField_6.setText("");
					sales_master.posscreen.label.setText("");
					sales_master.posscreen.label_1.setText("");
					sales_master.posscreen.label_2.setText("");
					sales_master.posscreen.textField_4.setText("0");
					sales_master.posscreen.textField_2.setText("0");
					sales_master.posscreen.textField.setText("0.00");
					sales_master.posscreen.textField_1.setText("0.00");
					sales_master.posscreen.textField_11.setText("0.00");
					sales_master.posscreen.lblNewLabel_1.setText("0");
					sales_master.posscreen.textField_3.setText("0.00");
					sales_master.posscreen.textField_9.setText("1");
					sales_master.posscreen.textField_12.setText("0");
					sales_master.posscreen.textField_5.setText("");
					sales_master.posscreen.model.setRowCount(0);
					if(gs.length>1)
					{
					sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
					sales_master.SecondDisplay.textField_7.setText("0");
					sales_master.SecondDisplay.textField_4.setText("0");
					sales_master.SecondDisplay.textField_2.setText("0");
					sales_master.SecondDisplay.textField.setText("0.00");
					sales_master.SecondDisplay.textField_1.setText("0.00");
					sales_master.SecondDisplay.textField_11.setText("0.00");
					sales_master.SecondDisplay.lblNewLabel_1.setText("0");
					sales_master.SecondDisplay.textField_3.setText("0.00");
					sales_master.SecondDisplay.textField_5.setText("");
					sales_master.SecondDisplay.model.setRowCount(0);
					}
				}
			}
		}
		catch(Exception e){
			System.err.println(e);
			frmRegisterNoS.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
			sales_master.posscreen.get_register_name(company,  posscreen.user_name, "S01",con,posscreen.reg_nme);
			sales_master.posscreen.txtDisc.setText("");
			sales_master.posscreen.btnNewButton.setText("WALK IN");
			sales_master.posscreen.textField_6.setText("");
			sales_master.posscreen.label.setText("");
			sales_master.posscreen.label_1.setText("");
			sales_master.posscreen.label_2.setText("");
			sales_master.posscreen.textField_4.setText("0");
			sales_master.posscreen.textField_2.setText("0");
			sales_master.posscreen.textField.setText("0.00");
			sales_master.posscreen.textField_1.setText("0.00");
			sales_master.posscreen.textField_11.setText("0.00");
			sales_master.posscreen.lblNewLabel_1.setText("0");
			sales_master.posscreen.textField_3.setText("0.00");
			sales_master.posscreen.textField_9.setText("1");
			sales_master.posscreen.textField_12.setText("0");
			sales_master.posscreen.textField_5.setText("");
			sales_master.posscreen.model.setRowCount(0);
			if(gs.length>1)
			{
			sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
			sales_master.SecondDisplay.textField_7.setText("0");
			sales_master.SecondDisplay.textField_4.setText("0");
			sales_master.SecondDisplay.textField_2.setText("0");
			sales_master.SecondDisplay.textField.setText("0.00");
			sales_master.SecondDisplay.textField_1.setText("0.00");
			sales_master.SecondDisplay.textField_11.setText("0.00");
			sales_master.SecondDisplay.lblNewLabel_1.setText("0");
			sales_master.SecondDisplay.textField_3.setText("0.00");
			sales_master.SecondDisplay.textField_5.setText("");
			sales_master.SecondDisplay.model.setRowCount(0);
			}
		}
	}
	
	public static void get_settled_invoice_dt(String company,String recipt_no,String reg_nme,Connection con, String paycode, String amt)
	{
		try{
			//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
			Statement statement=con.createStatement();
			Statement statement1=con.createStatement();
			Statement statement2=con.createStatement();
			String query="SELECT * FROM "+company+".mr_pos_settlement_cash where reg_num='"+reg_nme+"'and receipt_no='"+recipt_no+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				//old_recipt=textField_8.getText();
				current_register=reg_nme;
				
				textField_8.setText(resultSet.getString("receipt_no"));
				
				label.setText(resultSet.getString("table_num"));
				label_1.setText(resultSet.getString("delivery_user"));
				label_2.setText(resultSet.getString("mode"));
				//textField_3.setText(df2.format(Double.parseDouble(resultSet.getString("total"))));
				
				textField_4.setText(df2.format(Double.parseDouble(resultSet.getString("qty"))));
				
				
				
				//textField_2.setText(df2.format(Double.parseDouble(resultSet.getString("disc"))));
				double sub_ttl=Double.parseDouble(resultSet.getString("total"));
				double vat_ttl =Double.parseDouble(resultSet.getString("vat"));
				BigDecimal br = new BigDecimal(sub_ttl);
        		br = br.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		sub_ttl = br.doubleValue();
				textField.setText(df2.format(sub_ttl));
				textField_1.setText(df2.format(vat_ttl));
				btnNewButton.setText(resultSet.getString("cust_name"));
				
				if(gs.length>1)
				{
				SecondDisplay.textField_8.setText(resultSet.getString("receipt_no"));
				SecondDisplay.textField_4.setText(df2.format(Double.parseDouble(resultSet.getString("qty"))));
				SecondDisplay.textField.setText(df2.format(sub_ttl));
				SecondDisplay.textField_1.setText(df2.format(vat_ttl));
				SecondDisplay.btnNewButton.setText(resultSet.getString("cust_name"));
				}
				
				textField_9.setText(resultSet.getString("cust_code"));
				
				String query2="SELECT * FROM "+company+".mr_customer_master where cust_code='"+resultSet.getString("cust_code")+"' "; 
				ResultSet resultSet2 = statement1.executeQuery(query2);
				if(resultSet2.next())
				{
					double bal=0;
					if(paycode.equals("A/R"))
					{
						bal= Double.parseDouble(resultSet2.getString("avail_credit"))+Double.parseDouble(resultSet2.getString("advance_amt"))+Double.parseDouble(amt);
						String update_cust="update "+company+".mr_customer_master set avail_credit='"+bal+"' where cust_code='"+resultSet.getString("cust_code")+"'";
						statement1.execute(update_cust);
						
					}
					if(paycode.equals("PAYCODE"))
					{
						String query88="SELECT * FROM "+company+".mr_paycode_settlement where ref_receipt_num='"+resultSet.getString("receipt_no")+"' and ref_reg_num='"+reg_nme+"'  and paycode_name='A/R' "; 
						ResultSet resultSet88 = statement2.executeQuery(query88);
						if(resultSet88.next())
						{
							String paycode_amt=resultSet88.getString("paycode_amt");
							bal= Double.parseDouble(resultSet2.getString("avail_credit"))+Double.parseDouble(resultSet2.getString("advance_amt"))+Double.parseDouble(paycode_amt);
							String update_cust="update "+company+".mr_customer_master set avail_credit='"+bal+"' where cust_code='"+resultSet.getString("cust_code")+"'";
							statement1.execute(update_cust);
							
						}
					}
					
					textField_6.setText(Double.toString(bal));
				}
				
				double ttl_bf_disc=0;
				double ttl_af_disc=0;
				String query1="SELECT * FROM "+company+".mr_pos_settlement_cash_items inner join "+company+".mr_tmp_item_master on mr_pos_settlement_cash_items.barcode=mr_tmp_item_master.barcode where ref_reg_num='"+reg_nme+"' and ref_receipt_num='"+recipt_no+"' "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String item_code=resultSet1.getString("item_code");
					String barcode=resultSet1.getString("barcode");
					String department=resultSet1.getString("department");
					String item_desc=resultSet1.getString("item_desc");
					String unit=resultSet1.getString("unit");
					double unit_price=Double.parseDouble(resultSet1.getString("unit_price"));
					double qty=Double.parseDouble(resultSet1.getString("qty"));
					double total=Double.parseDouble(resultSet1.getString("total"));
					double vat=Double.parseDouble(resultSet1.getString("vat"));
					double discount=Double.parseDouble(resultSet1.getString("discount"));
					double itemcost=Double.parseDouble(resultSet1.getString("item_cost"));
					double sp=Double.parseDouble(resultSet1.getString("sp"));
					String vat_type=resultSet1.getString("vat_type");
					String disc_type=resultSet1.getString("disc_type");
					double commited_qty=0;
					double vat_perc=Double.parseDouble(resultSet1.getString("vat_perc"));
					String print_sts="1";
					String print_cmd="1";
					String scale_sts="0";
					String kot_number="0";
					String comments_item="";
					String cf=resultSet1.getString("cf");
					
					model.addRow(new Object[]{model.getRowCount()+1,barcode,item_desc,unit,unit_price,qty,discount,total,vat,total+vat,vat_type,itemcost,sp,item_code,commited_qty,disc_type,vat_perc,print_cmd,department,kot_number,reg_nme,comments_item,cf,print_sts,scale_sts,"1"});
					//model.addRow(new Object[]{model.getRowCount()+1,barcode,item_desc,unit,unit_price,qty,discount,total,vat,total+vat,vat_type,itemcost,sp,item_code,"",disc_type,vat_perc,"","","",reg_nme,"",cf});
					
					if(gs.length>1)
					{
						//SecondDisplay.model.addRow(new Object[]{model.getRowCount()+1,barcode,item_desc,unit,unit_price,qty,discount,total,vat,vat_type,itemcost,sp,item_code,commited_qty,disc_type,vat_perc,print_sts,department,kot_number,reg_nme,comments_item,cf});
					}
					
					
					if(vat_type.equals("INC"))
					{
						ttl_af_disc+=total+vat;
					}
					else
					{
						ttl_af_disc+=total;
					}
					
					String ttl_before=df2.format(unit_price*qty);
					
					ttl_bf_disc+=Double.parseDouble(ttl_before);
//					ttl_bf_disc+=unit_price*qty;
					
					
					
				}
				String before_disc=df2.format(ttl_bf_disc);
				sales_master.posscreen.textField_11.setText(before_disc);
				if(gs.length>1)
				{
				sales_master.SecondDisplay.textField_11.setText(before_disc);
				}
				String after_disc=df2.format(ttl_af_disc);
				double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
				if(Double.isNaN(ttl_disc_perc))
				{
					ttl_disc_perc=0;
				}
				double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
				
				textField_2.setText(df2.format(ttl_disc_amt));
				textField_5.setText(df2.format(ttl_disc_perc)+"%");
				if(gs.length>1)
				{
				SecondDisplay.textField_2.setText(df2.format(ttl_disc_amt));
				SecondDisplay.textField_5.setText(df2.format(ttl_disc_perc)+"%");
				}
				double grand_ttl=sub_ttl+vat_ttl;
        		
        		double checksum=0;
        		if(allow_roundoff.equals("1"))
        		{
        			String nettotal=df2.format(grand_ttl);
	        		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
	        		int number_decimal=Integer.parseInt(splitnumber);
        		
	        		if(number_decimal>0 && number_decimal<13 )
	        		{
	        			checksum=0-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
	        		if(number_decimal>12 && number_decimal<26 )
	        		{
	        			checksum=25-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
	        		if(number_decimal>25 && number_decimal<38 )
	        		{
	        			checksum=25-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
	        		if(number_decimal>37 && number_decimal<51 )
	        		{
	        			checksum=50-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
	        		if(number_decimal>50 && number_decimal<63 )
	        		{
	        			checksum=50-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
	        		if(number_decimal>62 && number_decimal<76 )
	        		{
	        			checksum=75-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
	        		if(number_decimal>75 && number_decimal<88 )
	        		{
	        			checksum=75-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
	        		if(number_decimal>87 && number_decimal<101 )
	        		{
	        			checksum=100-number_decimal;
	        			grand_ttl=Double.parseDouble(nettotal)+(checksum/100);
	        		}
        		}
        		lblNewLabel_1.setText(Double.toString(checksum/100));
        		
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				table.addRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
				
				
				
				String space="";
				int length=Integer.toString(model.getRowCount()).length();
	    		int print_price;
	    		print_price=8-length;
    			for(int i=0; i<print_price;i++)
    			{
    				space+=" ";
    			}
    			String gap="";
    			//double grand_ttl=Double.parseDouble(textField_3.getText());
	    		String grnd_ttl=df2.format(grand_ttl);
	    		
	    		textField_3.setText(grnd_ttl);
	    		
	    		if(gs.length>1)
				{
					SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
					SecondDisplay.table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
					SecondDisplay.table.addRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);	
					SecondDisplay.textField_3.setText(grnd_ttl);
				}
				int grand_price=15-grnd_ttl.length();
				for(int i=0; i<grand_price;i++)
				{
					gap+=" ";
				}
				int count=0;
				String query_return="select count(*) as ttl_line from  "+company+".mr_pos_settlement_cash  where reg_num='"+reg_nme+"'and receipt_no like '"+recipt_no+"%'";
				ResultSet resultSet12 = statement.executeQuery(query_return);
				if(resultSet12.next())
				{
					count=Integer.parseInt(resultSet12.getString("ttl_line"));
				}
				Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO TOTAL ITEMS "+space+""+model.getRowCount()+">"+display_port+"&&ECHO TOTAL"+gap+""+df2.format(grand_ttl)+">"+display_port+"\"");
				String Query_update="update "+company+".mr_pos_settlement_cash set type='VOID',receipt_no='"+recipt_no+"_"+count+"' where reg_num='"+reg_nme+"'and receipt_no='"+recipt_no+"' ";
				statement1.execute(Query_update);
				String Query_update1="update "+company+".mr_pos_settlement_cash_items set ref_receipt_num='"+recipt_no+"_"+count+"' where ref_reg_num='"+reg_nme+"'and ref_receipt_num='"+recipt_no+"' ";
				statement1.execute(Query_update1);
				String Query_update2="update "+company+".mr_paycode_settlement set ref_receipt_num='"+recipt_no+"_"+count+"' where ref_reg_num='"+reg_nme+"'and ref_receipt_num='"+recipt_no+"' ";
				statement1.execute(Query_update2);
				//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO TOTAL ITEMS "+space+""+model.getRowCount()+"TOTAL"+""+gap+""+df2.format(grand_ttl)+">"+display_port+"\""});
			}
			int count=table.getRowCount();
			if(count==0)
			{
				if(!old_recipt.equals(""))
				{
					JOptionPane.showMessageDialog(frmRegisterNoS, "Invoice Number is Restored");
					posscreen.txtDisc.requestFocus();
					sales_master.posscreen.get_register_name(company,  posscreen.user_name, "S01",con,posscreen.reg_nme);
					sales_master.posscreen.txtDisc.setText("");
					sales_master.posscreen.btnNewButton.setText("WALK IN");
					sales_master.posscreen.textField_6.setText("");
					sales_master.posscreen.label.setText("");
					sales_master.posscreen.label_1.setText("");
					sales_master.posscreen.label_2.setText("");
					sales_master.posscreen.textField_4.setText("0");
					sales_master.posscreen.textField_2.setText("0");
					sales_master.posscreen.textField.setText("0.00");
					sales_master.posscreen.textField_1.setText("0.00");
					sales_master.posscreen.textField_11.setText("0.00");
					sales_master.posscreen.lblNewLabel_1.setText("0");
					sales_master.posscreen.textField_3.setText("0.00");
					sales_master.posscreen.textField_9.setText("1");
					sales_master.posscreen.textField_12.setText("0");
					sales_master.posscreen.textField_5.setText("");
					sales_master.posscreen.model.setRowCount(0);
					if(gs.length>1)
					{
					sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
					sales_master.SecondDisplay.textField_7.setText("0");
					sales_master.SecondDisplay.textField_4.setText("0");
					sales_master.SecondDisplay.textField_2.setText("0");
					sales_master.SecondDisplay.textField.setText("0.00");
					sales_master.SecondDisplay.textField_1.setText("0.00");
					sales_master.SecondDisplay.textField_11.setText("0.00");
					sales_master.SecondDisplay.lblNewLabel_1.setText("0");
					sales_master.SecondDisplay.textField_3.setText("0.00");
					sales_master.SecondDisplay.textField_5.setText("");
					sales_master.SecondDisplay.model.setRowCount(0);
					}
				}
			}
		}
		catch(Exception e){
			System.err.println(e);
			frmRegisterNoS.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
			sales_master.posscreen.get_register_name(company,  posscreen.user_name, "S01",con,posscreen.reg_nme);
			sales_master.posscreen.txtDisc.setText("");
			sales_master.posscreen.btnNewButton.setText("WALK IN");
			sales_master.posscreen.textField_6.setText("");
			sales_master.posscreen.label.setText("");
			sales_master.posscreen.label_1.setText("");
			sales_master.posscreen.label_2.setText("");
			sales_master.posscreen.textField_4.setText("0");
			sales_master.posscreen.textField_2.setText("0");
			sales_master.posscreen.textField.setText("0.00");
			sales_master.posscreen.textField_1.setText("0.00");
			sales_master.posscreen.textField_11.setText("0.00");
			sales_master.posscreen.lblNewLabel_1.setText("0");
			sales_master.posscreen.textField_3.setText("0.00");
			sales_master.posscreen.textField_9.setText("1");
			sales_master.posscreen.textField_12.setText("0");
			sales_master.posscreen.textField_5.setText("");
			sales_master.posscreen.model.setRowCount(0);
			if(gs.length>1)
			{
			sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
			sales_master.SecondDisplay.textField_7.setText("0");
			sales_master.SecondDisplay.textField_4.setText("0");
			sales_master.SecondDisplay.textField_2.setText("0");
			sales_master.SecondDisplay.textField.setText("0.00");
			sales_master.SecondDisplay.textField_1.setText("0.00");
			sales_master.SecondDisplay.textField_11.setText("0.00");
			sales_master.SecondDisplay.lblNewLabel_1.setText("0");
			sales_master.SecondDisplay.textField_3.setText("0.00");
			sales_master.SecondDisplay.textField_5.setText("");
			sales_master.SecondDisplay.model.setRowCount(0);
			}
		}
	}
	
	private static void qtyupdate(String company, String username,String store_name,String posreg,String version)
	{
		try
		{
			boolean status=false;
			if(checkbillstatus.equals("1"))
			{
				if(checkprint_invoice_change==false && old_recipt!="")
				{
					status=false;
					new sales_master.manager( company, username, store_name, reg_nme, version);
					txtDisc.requestFocus();
				}
				else
				{
					status=true;
				}
			}
			else
			{
				
				if(ChangeHoldQty==false && old_recipt!="")
				{
					status=false;
					new sales_master.manager( company, username, store_name, reg_nme, version);
					txtDisc.requestFocus();
				}
				else
				{
					status=true;
				}
			}
			if(status==true)
			{
				String qty=df2.format(Double.parseDouble(txtDisc.getText()));
				int tbl_sel=table.getSelectedRow();
				if(qty.equals(""))
				{
					JOptionPane.showMessageDialog(frmRegisterNoS, "Enter Quatity First");
					posscreen.txtDisc.requestFocus();
				}
				else if(tbl_sel== -1)
				{
					JOptionPane.showMessageDialog(frmRegisterNoS, "Choose the row do you want to change");
					posscreen.txtDisc.requestFocus();
				}
				else
				{
					//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
					double total=0;
					double vat_rate=0;
					double amt_disc=0;
					double amt_ttl=0;
					double disc_perc = Double.parseDouble(table.getValueAt(tbl_sel, 6).toString());
					double tax_rate = Double.parseDouble(table.getValueAt(tbl_sel, 16).toString());
					String unit_price=table.getModel().getValueAt(tbl_sel, 4).toString();
					String vattype=table.getModel().getValueAt(tbl_sel, 10).toString();
					String amount=table.getModel().getValueAt(tbl_sel, 7).toString();
					if(vattype.equals("EXC"))
					{
						if(Float.parseFloat(amount)<0)
						{
							amt_disc=(Double.parseDouble(unit_price)*Double.parseDouble(qty)*disc_perc)/100;
							amt_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qty))-amt_disc;
							total=amt_ttl;
							vat_rate=total*tax_rate/100;
						}
						else{
							amt_disc=(Double.parseDouble(unit_price)*Double.parseDouble(qty)*disc_perc)/100;
							amt_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qty))-amt_disc;
							total=amt_ttl;
							vat_rate=total*tax_rate/100;
						}
						
					}
					double unit_total;
					if(vattype.equals("INC"))
					{
						if(Float.parseFloat(amount)<0)
						{
							amt_disc=(Double.parseDouble(unit_price)*Double.parseDouble(qty)*disc_perc)/100;
							amt_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qty))-amt_disc;
							unit_total=-(amt_ttl);
							vat_rate=(unit_total*tax_rate)/105;
							total=unit_total-vat_rate;
						}
						else{
							amt_disc=(Double.parseDouble(unit_price)*Double.parseDouble(qty)*disc_perc)/100;
							amt_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qty))-amt_disc;
							unit_total=amt_ttl;
							//unit_total=Double.parseDouble(unit_price)*Double.parseDouble(qty);
							vat_rate=(unit_total*tax_rate)/105;
							total=unit_total-vat_rate;
						}
						
					}
					if(vattype.equals("NON"))
					{
						
						if(Float.parseFloat(amount)<0)
						{
							total=-(Double.parseDouble(unit_price)*Double.parseDouble(qty));
						}
						else{
							total=Double.parseDouble(unit_price)*Double.parseDouble(qty);
						}
						
					}
					if(total<0)
					{
						if(Double.parseDouble(qty) < 0)
						{
							model.setValueAt(qty,tbl_sel, 5);
							if(gs.length>1)
							{
							SecondDisplay.model.setValueAt(qty,tbl_sel, 5);
							}
						}
						else
						{
							model.setValueAt("-"+qty,tbl_sel, 5);
							if(gs.length>1)
							{
							SecondDisplay.model.setValueAt("-"+qty,tbl_sel, 5);
							}
						}
					}
					else{
						model.setValueAt(qty,tbl_sel, 5);
						if(gs.length>1)
						{
						SecondDisplay.model.setValueAt(qty,tbl_sel, 5);
						}
					}
					model.setValueAt("0", tbl_sel, 23);
					model.setValueAt("0", tbl_sel, 23);
					model.setValueAt(df2.format(total),tbl_sel, 7);
					model.setValueAt(df2.format(vat_rate),tbl_sel, 8);
					model.setValueAt(df2.format(total+vat_rate),tbl_sel, 9);
					table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
					
					if(gs.length>1)
					{
					SecondDisplay.model.setValueAt(df2.format(total),tbl_sel, 7);
					SecondDisplay.model.setValueAt(df2.format(vat_rate),tbl_sel, 8);
					SecondDisplay.model.setValueAt(df2.format(total+vat_rate),tbl_sel, 9);
					SecondDisplay.table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
					}
					
					
					int count=table.getRowCount();
					double vat_ttl=0.0;
					double total_price=0.0;
					double ttl_qty=0;
					double ttl_amt=0;
					double ttl_bf_disc=0;
					double ttl_af_disc=0;
					for(int i=0;i<count;i++)
					{
						//model.setValueAt("0", i, 23);
						String vat_type=table.getModel().getValueAt(i, 10).toString();
						double unitprice=Double.parseDouble(table.getValueAt(i, 4).toString());
						double qty_line=Double.parseDouble(table.getValueAt(i, 5).toString());
						ttl_qty+=Double.parseDouble(table.getModel().getValueAt(i, 5).toString());
						String total_val=table.getModel().getValueAt(i, 7).toString();
						ttl_amt+=Double.parseDouble(total_val);
						String ttl_vat=table.getModel().getValueAt(i, 8).toString();
						vat_ttl+=Double.parseDouble(ttl_vat);
						total_price+= Double.parseDouble(total_val)+Double.parseDouble(ttl_vat);
						double totl=Double.parseDouble(table.getValueAt(i, 7).toString());
						double vt=Double.parseDouble(table.getValueAt(i, 8).toString());
						
						if(vat_type.equals("INC"))
						{
							ttl_af_disc+=totl+vt;
						}
						else
						{
							ttl_af_disc+=totl;
						}
						
						String ttl_before=df2.format(unitprice*qty_line);
						
						ttl_bf_disc+=Double.parseDouble(ttl_before);
						//ttl_bf_disc+=unitprice*qty_line;
					}
					
					//double ttl_disc_perc=0;//((ttl_bf_disc-ttl_af_disc)*100/ttl_bf_disc);
					//double ttl_disc_amt=0;//ttl_bf_disc-ttl_af_disc;
					
					String before_disc=df2.format(ttl_bf_disc);
					sales_master.posscreen.textField_11.setText(before_disc);
					if(gs.length>1)
					{
					sales_master.SecondDisplay.textField_11.setText(before_disc);
					}
					String after_disc=df2.format(ttl_af_disc);
					double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
					if(Double.isNaN(ttl_disc_perc))
					{
						ttl_disc_perc=0;
					}
					double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
					
					double checksum=0;
					if(allow_roundoff.equals("1"))
					{
						String nettotal=df2.format(total_price);
			    		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
			    		int number_decimal=Integer.parseInt(splitnumber);
			    		
			    		if(number_decimal>0 && number_decimal<13 )
			    		{
			    			checksum=0-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>12 && number_decimal<26 )
			    		{
			    			checksum=25-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>25 && number_decimal<38 )
			    		{
			    			checksum=25-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>37 && number_decimal<51 )
			    		{
			    			checksum=50-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>50 && number_decimal<63 )
			    		{
			    			checksum=50-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>62 && number_decimal<76 )
			    		{
			    			checksum=75-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>75 && number_decimal<88 )
			    		{
			    			checksum=75-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>87 && number_decimal<101 )
			    		{
			    			checksum=100-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
					}
					
		    		lblNewLabel_1.setText(Double.toString(checksum/100));
		    		if(gs.length>1)
					{
		    		SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
					}
		    		String[] tokens = null;
		    		int length = table.getModel().getValueAt(tbl_sel, 2).toString().length();
		    		int price_space;
		    		int print_price;
		    		String get_name;
		    		double line_ttl=total+vat_rate;
		    		if(length>12)
		    		{
		    			int size = 12;
		    			tokens = table.getModel().getValueAt(tbl_sel, 2).toString().split("(?<=\\G.{" + size + "})");
		    			String ttl_st=df2.format(line_ttl);
		    			String space="";
		    			print_price=8-ttl_st.length();
		    			for(int i=0; i<print_price;i++)
		    			{
		    				space+=" ";
		    			}
		    			get_name=tokens[0]+""+space+""+df2.format(line_ttl);
		    		}
		    		else
		    		{
		    			price_space=12-length;
		    			String space="";
		    			for(int i=0;i<price_space;i++)
		    			{
		    				space+=" ";
		    			}
		    			String ttl_st=df2.format(line_ttl);
		    			print_price=8-ttl_st.length();
		    			for(int i=0; i<print_price;i++)
		    			{
		    				space+=" ";
		    			}
		    			get_name=table.getModel().getValueAt(tbl_sel, 2).toString()+""+space+""+df2.format(line_ttl);
		    		}
		    		String gap="";
		    		String grnd_ttl=df2.format(total_price);
		    		
					int grand_price=15-grnd_ttl.length();
					for(int i=0; i<grand_price;i++)
					{
						gap+=" ";
					}
					Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+get_name+">"+display_port+"&&ECHO TOTAL"+gap+""+df2.format(total_price)+">"+display_port+"\"");
					//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO "+get_name+"TOTAL"+""+gap+""+df2.format(total_price)+">"+display_port+"\""});
					
					
		    		textField.setText(df2.format(ttl_amt));
					textField_3.setText(df2.format(total_price));
					textField_1.setText(df2.format(vat_ttl));
					textField_4.setText(df2.format(ttl_qty));
					textField_2.setText(df2.format(ttl_disc_amt));
					textField_5.setText(df2.format(ttl_disc_perc)+"%");
					if(gs.length>1)
					{
					SecondDisplay.textField.setText(df2.format(ttl_amt));
					SecondDisplay.textField_3.setText(df2.format(total_price));
					SecondDisplay.textField_1.setText(df2.format(vat_ttl));
					SecondDisplay.textField_4.setText(df2.format(ttl_qty));
					SecondDisplay.textField_2.setText(df2.format(ttl_disc_amt));
					SecondDisplay.textField_5.setText(df2.format(ttl_disc_perc)+"%");
					}
					txtDisc.setText("");
					txtDisc.requestFocus();
					manager_name=username;
					get_user_validate_dt( company, username,store_name,posreg);
				}
			}
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
		
	}
	
	private static void getvoid(String company, String username,String store_name,String posreg,Connection con, String version)
	{
		try
		{
			boolean status=false;
			if(checkbillstatus.equals("1"))
			{
				if(checkprint_invoice_change==false && old_recipt!="")
				{
					status=false;
					new sales_master.manager( company, username, store_name, reg_nme, version);
					txtDisc.requestFocus();
				}
				else
				{
					status=true;
				}
			}
			else
			{
				
				if(ChangeHoldQty==false && old_recipt!="")
				{
					status=false;
					new sales_master.manager( company, username, store_name, reg_nme, version);
					txtDisc.requestFocus();
				}
				else
				{
					status=true;
				}
			}
			if(status==true)
			{
				String total=textField_3.getText();
				if(total.equals("0.00"))
				{
					JOptionPane.showMessageDialog(frmRegisterNoS, "No Items Found");
					txtDisc.requestFocus();
				}
				else
				{
					int a=JOptionPane.showConfirmDialog(frmRegisterNoS, "Do you Want To Void This Transaction","WARNING",JOptionPane.YES_NO_OPTION);
					if(a==0)
					{
						Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO INVOICE VOIDED..>"+display_port+"\"");
						//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO INVOICE VOIDED. >"+display_port+"\""});
						
						String type="VOID";
						String reg_num=current_register;
						String user=username;
						String receipt_no=textField_8.getText();
						String cash="0";
						String cust_name=btnNewButton.getText();
						String vat=textField_1.getText();
						String disc=textField_2.getText();
						String qty=textField_4.getText();
						Statement statement= con.createStatement();
						Statement statement1= con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ");
						if(!resultSet.next())
						{
							cal = Calendar.getInstance();
							String query="insert into "+company+".mr_pos_settlement_cash (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, `cash_paid`,type,store_id, `created_date`,grand_total,cust_code,table_num,delivery_user,mode,cust_ph) values('"+reg_num+"','"+receipt_no+"','"+user+"','"+times.format(cal.getTime())+"','"+textField.getText()+"','"+cust_name+"','"+vat+"','"+disc+"','"+qty+"','"+cash+"','"+type+"','"+store_name+"','"+formattedDate+"','"+textField_3.getText()+"','"+textField_9.getText()+"','"+label.getText()+"','"+label_1.getText()+"','"+label_2.getText()+"','"+posscreen.textField_12.getText()+"') ";
							statement.executeUpdate(query);
							if(old_recipt.equals(""))
							{
								ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_num+"' ");
								if(resultSet1.next())
								{
									int nxt_num=Integer.parseInt(resultSet1.getString("next_num"))+1;
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
								//ps.setString(4,posscreen.model.getValueAt(i, 2).toString().trim());
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
								String printer_kot="";
								String dept="select department,kot_number from "+company+".mr_pos_hold_invoice_items where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"' group by department ";
								ResultSet resultSet1 = statement.executeQuery(dept);
								while(resultSet1.next())
								{
									
									String printer_dept="select printer from "+company+".mr_assign_kot_printer where reg_nme='"+current_register+"' and dept_name='"+resultSet1.getString("department")+"' ";
									ResultSet resultSet2 = statement1.executeQuery(printer_dept);
									if(resultSet2.next())
									{
										printer_kot=resultSet2.getString("printer");
											
									}
								}
								//report_master.holded_invoice.report(textField_8.getText(), con, printer,current_register,"",company,"Hold Invoice","","");
								report_master.holded_invoice.report(textField_8.getText(), con, printer_kot,current_register,"",company,"void Invoice","","");
								//report_master.holded_invoice.report(textField_8.getText(), con, printer,current_register,"",company,"Kitchen KOT","","");
								
								String query6="delete FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ";
								statement.executeUpdate(query6);
								String query7="delete FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+receipt_no+"' and ref_reg_no='"+reg_num+"' ";
								statement.executeUpdate(query7);
							}
							
							if(change_hold_type.equals("0"))
							{
								report_master.Bill_template.report(textField_8.getText(), con, printer,reg_nme,"",company,copy);	
							}
							
							
							JOptionPane.showMessageDialog(frmRegisterNoS, "Transaction complete");
							posscreen.txtDisc.requestFocus();
							sales_master.posscreen.get_register_name(company,  username, store_name,con,posreg);
							sales_master.posscreen.txtDisc.setText("");
							sales_master.posscreen.btnNewButton.setText("WALK IN");
							sales_master.posscreen.textField_6.setText("");
							sales_master.posscreen.textField_7.setText("0");
							sales_master.posscreen.lblNewLabel_2.setText("0%");
							sales_master.posscreen.textField_4.setText("0");
							sales_master.posscreen.textField_2.setText("0");
							sales_master.posscreen.textField.setText("0.00");
							sales_master.posscreen.textField_1.setText("0.00");
							sales_master.posscreen.textField_11.setText("0.00");
							sales_master.posscreen.lblNewLabel_1.setText("0");
							sales_master.posscreen.textField_9.setText("1");
							sales_master.posscreen.textField_12.setText("0");
							sales_master.posscreen.textField_3.setText("0.00");
							sales_master.posscreen.textField_5.setText("");
							sales_master.posscreen.model.setRowCount(0);
							sales_master.posscreen.label.setText("");
							sales_master.posscreen.label_1.setText("");
							sales_master.posscreen.label_2.setText("");
							checkbillstatus="0";
							sales_master.posscreen.txtDisc.requestFocus();
							if(gs.length>1)
							{
							sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
							sales_master.SecondDisplay.textField_7.setText("0");
							sales_master.SecondDisplay.textField_4.setText("0");
							sales_master.SecondDisplay.textField_2.setText("0");
							sales_master.SecondDisplay.textField.setText("0.00");
							sales_master.SecondDisplay.textField_1.setText("0.00");
							sales_master.SecondDisplay.textField_11.setText("0.00");
							sales_master.SecondDisplay.lblNewLabel_1.setText("0");
							sales_master.SecondDisplay.textField_3.setText("0.00");
							sales_master.SecondDisplay.textField_5.setText("");
							sales_master.SecondDisplay.model.setRowCount(0);
							}
							//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
						}
						else
						{
							JOptionPane.showMessageDialog(frmRegisterNoS, "Receipt number Already Saved");
							posscreen.txtDisc.requestFocus();
						}
						manager_name=username;
			    		get_user_validate_dt( company, username,store_name,posreg);
			    		//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO "+ready_msg1+""+ready_msg2+">"+display_port+"\""});
			    		Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+posscreen.ready_msg1+">"+display_port+"&&ECHO "+posscreen.ready_msg2+">"+display_port+"\"");
			    		page_type="Delivery";
						current_register=reg_nme;
			    		txtDisc.requestFocus();	
					}
				}
			}
			//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
		
	}
	
	private static void getitemlist(String company, String username,String store_name,String posreg,String version,String decimal,Connection con,String language)
	{
		try
		{
			if(txtDisc.getText().equals(""))
			{
				if(!returns.equals("return"))
				{
					returns="posscreen";
				}
				btnPlu.setEnabled(false);
				new inventory_master.item_list_master(company,company,returns,store_name, version,Integer.parseInt(decimal),con,ttl_item, language);
				txtDisc.setText("");
				//txtDisc.requestFocus();
				manager_name=username;
				get_user_validate_dt( company, username,store_name,posreg);
				txtDisc.setBackground(Color.BLACK);
				txtDisc.setCaretColor(Color.WHITE);
				txtDisc.setForeground(Color.WHITE);
			}
			else
			{
				validate_barcode(company, txtDisc.getText(), username,store_name, returns,con);
				txtDisc.setText("");
				txtDisc.requestFocus();
				manager_name=username;
				get_user_validate_dt( company, username,store_name,posreg);
				txtDisc.setBackground(Color.BLACK);
				txtDisc.setCaretColor(Color.WHITE);
				txtDisc.setForeground(Color.WHITE);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
		
	}
	
	private static void getpricechange(String company, String username,String store_name,String posreg, String version)
	{
		try
		{
			boolean status=false;
			if(checkbillstatus.equals("1"))
			{
				if(checkprint_invoice_change==false && old_recipt!="")
				{
					status=false;
					new sales_master.manager( company, username, store_name, reg_nme, version);
					txtDisc.requestFocus();
				}
				else
				{
					status=true;
				}
			}
			else
			{
				
				if(ChangeHoldQty==false && old_recipt!="")
				{
					status=false;
					new sales_master.manager( company, username, store_name, reg_nme, version);
					txtDisc.requestFocus();
				}
				else
				{
					status=true;
				}
			}
			if(status==true)
			{
				String unit_price=df2.format(Double.parseDouble(txtDisc.getText()));
				int tbl_sel=table.getSelectedRow();
				if(unit_price.equals(""))
				{
					JOptionPane.showMessageDialog(frmRegisterNoS, "Enter Price First");
					posscreen.txtDisc.requestFocus();
				}
				else if(tbl_sel== -1)
				{
					JOptionPane.showMessageDialog(frmRegisterNoS, "Choose the row do you want to change");
					posscreen.txtDisc.requestFocus();
				}
				else
				{
					//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
					
					String qty=table.getModel().getValueAt(tbl_sel, 5).toString();
					String vat_type=table.getModel().getValueAt(tbl_sel, 10).toString();
					double total=0;
					double vat_rate=0;
					double amt_disc=0;
					double amt_ttl=0;
					double disc_perc = Double.parseDouble(table.getValueAt(tbl_sel, 6).toString());
					double tax_rate = Double.parseDouble(table.getValueAt(tbl_sel, 16).toString());
					if(vat_type.equals("NON"))
					{						
						total=Double.parseDouble(unit_price)*Double.parseDouble(qty);
					}
					if(vat_type.equals("INC"))
					{
						amt_disc=(Double.parseDouble(unit_price)*Double.parseDouble(qty)*disc_perc)/100;
						amt_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qty))-amt_disc;
						vat_rate=(amt_ttl*tax_rate)/105;
						total=(amt_ttl)-(vat_rate);	
					}
					if(vat_type.equals("EXC"))
					{
						amt_disc=(Double.parseDouble(unit_price)*Double.parseDouble(qty)*disc_perc)/100;
						amt_ttl=(Double.parseDouble(unit_price)*Double.parseDouble(qty))-amt_disc;
						total=amt_ttl;
						vat_rate=total*tax_rate/100;
					}
					model.setValueAt("0", tbl_sel, 23);
					model.setValueAt(unit_price,tbl_sel, 4);
					model.setValueAt(df2.format(total),tbl_sel, 7);
					model.setValueAt(df2.format(vat_rate),tbl_sel, 8);
					model.setValueAt(df2.format(total+vat_rate),tbl_sel, 9);
					
					table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
					if(gs.length>1)
					{
					SecondDisplay.model.setValueAt(unit_price,tbl_sel, 4);
					SecondDisplay.model.setValueAt(df2.format(total),tbl_sel, 7);
					SecondDisplay.model.setValueAt(df2.format(vat_rate),tbl_sel, 8);
					SecondDisplay.model.setValueAt(df2.format(total+vat_rate),tbl_sel, 9);
					SecondDisplay.table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
					}
					int count=table.getRowCount();
					double vat_ttl=0.0;
					double total_price=0.0;
					double ttl_qty=0;
					double ttl_amt=0;
					double ttl_bf_disc=0;
					double ttl_af_disc=0;
					for(int i=0;i<count;i++)
					{
						//model.setValueAt("0", i, 23);
						String vattype=table.getModel().getValueAt(i, 10).toString();
						double unitprice=Double.parseDouble(table.getValueAt(i, 4).toString());
						double qty_line=Double.parseDouble(table.getValueAt(i, 5).toString());
						ttl_qty+=Double.parseDouble(table.getModel().getValueAt(i, 5).toString());
						String total_val=table.getModel().getValueAt(i, 7).toString();
						ttl_amt+=Double.parseDouble(total_val);
						String ttl_vat=table.getModel().getValueAt(i, 8).toString();
						vat_ttl+=Double.parseDouble(ttl_vat);
						total_price+= Double.parseDouble(total_val)+Double.parseDouble(ttl_vat);
						double totl=Double.parseDouble(table.getValueAt(i, 7).toString());
						double vt=Double.parseDouble(table.getValueAt(i, 8).toString());
						
						if(vattype.equals("INC"))
						{
							ttl_af_disc+=totl+vt;
						}
						else
						{
							ttl_af_disc+=totl;
						}
						
						String ttl_before=df2.format(unitprice*qty_line);
						
						ttl_bf_disc+=Double.parseDouble(ttl_before);
						//ttl_bf_disc+=unitprice*qty_line;
					}
					
					String before_disc=df2.format(ttl_bf_disc);
					sales_master.posscreen.textField_11.setText(before_disc);
					if(gs.length>1)
					{
					sales_master.SecondDisplay.textField_11.setText(before_disc);
					}
					String after_disc=df2.format(ttl_af_disc);
					double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
					if(Double.isNaN(ttl_disc_perc))
					{
						ttl_disc_perc=0;
					}
					double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
					
					String[] tokens = null;
		    		int length = table.getModel().getValueAt(tbl_sel, 2).toString().length();
		    		int price_space;
		    		int print_price;
		    		String get_name;
		    		double line_ttl=total+vat_rate;
		    		if(length>12)
		    		{
		    			int size = 12;
		    			tokens = table.getModel().getValueAt(tbl_sel, 2).toString().split("(?<=\\G.{" + size + "})");
		    			String ttl_st=df2.format(line_ttl);
		    			String space="";
		    			print_price=8-ttl_st.length();
		    			for(int i=0; i<print_price;i++)
		    			{
		    				space+=" ";
		    			}
		    			get_name=tokens[0]+""+space+""+df2.format(line_ttl);
		    		}
		    		else
		    		{
		    			price_space=12-length;
		    			String space="";
		    			for(int i=0;i<price_space;i++)
		    			{
		    				space+=" ";
		    			}
		    			String ttl_st=df2.format(line_ttl);
		    			print_price=8-ttl_st.length();
		    			for(int i=0; i<print_price;i++)
		    			{
		    				space+=" ";
		    			}
		    			get_name=table.getModel().getValueAt(tbl_sel, 2).toString()+""+space+""+df2.format(line_ttl);
		    		}
		    		String gap="";
		    		String grnd_ttl=df2.format(total_price);
		    		
		    		double checksum=0;
		    		if(allow_roundoff.equals("1"))
		    		{
		    			String nettotal=df2.format(total_price);
			    		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
			    		int number_decimal=Integer.parseInt(splitnumber);
			    		
			    		if(number_decimal>0 && number_decimal<13 )
			    		{
			    			checksum=0-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>12 && number_decimal<26 )
			    		{
			    			checksum=25-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>25 && number_decimal<38 )
			    		{
			    			checksum=25-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>37 && number_decimal<51 )
			    		{
			    			checksum=50-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>50 && number_decimal<63 )
			    		{
			    			checksum=50-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>62 && number_decimal<76 )
			    		{
			    			checksum=75-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>75 && number_decimal<88 )
			    		{
			    			checksum=75-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>87 && number_decimal<101 )
			    		{
			    			checksum=100-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
		    		}
		    		lblNewLabel_1.setText(Double.toString(checksum/100));
		    		if(gs.length>1)
					{
		    		SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
					}
					int grand_price=15-grnd_ttl.length();
					for(int i=0; i<grand_price;i++)
					{
						gap+=" ";
					}
					Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+get_name+">"+display_port+"&&ECHO TOTAL"+""+gap+""+df2.format(total_price)+">"+display_port+"\"");
					
					//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO "+get_name+"TOTAL"+""+gap+""+df2.format(total_price)+">"+display_port+"\""});
		    		textField.setText(df2.format(ttl_amt));
					textField_3.setText(df2.format(total_price));
					textField_1.setText(df2.format(vat_ttl));
					textField_4.setText(df2.format(ttl_qty));
					textField_2.setText(df2.format(ttl_disc_amt));
					textField_5.setText(df2.format(ttl_disc_perc)+"%");
					if(gs.length>1)
					{
					SecondDisplay.textField.setText(df2.format(ttl_amt));
					SecondDisplay.textField_3.setText(df2.format(total_price));
					SecondDisplay.textField_1.setText(df2.format(vat_ttl));
					SecondDisplay.textField_4.setText(df2.format(ttl_qty));
					SecondDisplay.textField_2.setText(df2.format(ttl_disc_amt));
					SecondDisplay.textField_5.setText(df2.format(ttl_disc_perc)+"%");
					}
					txtDisc.setText("");
					txtDisc.requestFocus();
					//table.clearSelection();
					manager_name=username;
					get_user_validate_dt( company, username,store_name,posreg);
				}
			}
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
		
	}
	
	private static void ar_settle(String company, String username,String store_name,String posreg,Connection con,String version)
	{
		try
		{
			String cust_name=btnNewButton.getText();
			if(Double.parseDouble(textField_3.getText())==0)
			{
				JOptionPane.showMessageDialog(frmRegisterNoS, "No Amount to Settle");
				posscreen.txtDisc.requestFocus();
			}
			else
			{
				if(cust_name.equals("WALK IN"))
				{
					new sales_master.Cust_Chooser(company,"pos",username,store_name,textField_3.getText(),old_recipt, version,"1");
				}
				else
				{
					if(Double.parseDouble(textField_6.getText())>=Double.parseDouble(textField_3.getText()))
					{
						String type="A/R";
						String cash="0";
						String reg_num=current_register;
						String user=username;
						String receipt_no=textField_8.getText();
						String total=textField_3.getText();
						String vat=textField_1.getText();
						String disc=textField_2.getText();
						String qty=textField_4.getText();
						Statement statement;
						
						
						String kotsts="0";
						for(int i=0;i<posscreen.table.getRowCount();i++)
						{
							String kot=model.getValueAt(i, 19).toString();
							if(kot.equals("1"))
							{
								kotsts="1";
							}
						}
						
						if(kotsts.equals("0"))
						{
							label_2.setText("HOME DELIVERY");
							label.setText("HOME DELIVERY");
							//hold_inv(company, username, store_name, posreg,con,"",version,"","",textField_8.getText(),"0");	
							posscreen.Kot_hold_inv(company, username,store_name, posreg, con,"HOME DELIVERY",version,"","HOME DELIVERY",posscreen.textField_8.getText(),"0","");
						}
						
						statement = con.createStatement();
						ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ");
						if(!resultSet1.next())
						{
							ResultSet resultSet11 = statement.executeQuery("select * from "+company+".mr_customer_master where cust_code='"+textField_9.getText()+"' ");
							if(resultSet11.next())
							{
								float bal_amt=0;
								float avail_crdt=0;
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
								String update_customer_master="update "+company+".mr_customer_master set avail_credit='"+avail_crdt+"',advance_amt='"+adv_amt+"',last_sale_date='"+formattedDate+"' where cust_code='"+textField_9.getText()+"' ";
								statement.executeUpdate(update_customer_master);
								cal = Calendar.getInstance();
								String query="insert into "+company+".mr_pos_settlement_cash (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, `cash_paid`,type,store_id, `created_date`,grand_total,cust_code,cust_prev_balance,table_num,delivery_user,mode,cust_ph) values('"+reg_num+"','"+receipt_no+"','"+user+"','"+times.format(cal.getTime())+"','"+textField.getText()+"','"+cust_name+"','"+vat+"','"+disc+"','"+qty+"','"+cash+"','"+type+"','"+store_name+"','"+formattedDate+"','"+textField_3.getText()+"','"+textField_9.getText()+"','"+textField_6.getText()+"','"+table_num+"','"+delivery_user+"','"+mode+"','"+posscreen.textField_12.getText()+"') ";
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
//								for(int i=0;i<posscreen.table.getRowCount();i++)
//								{
//									String item_qty="update "+company+".mr_tmp_item_master set commited_qty=commited_qty+"+Double.parseDouble(posscreen.model.getValueAt(i, 5).toString().trim())+" where barcode="+posscreen.model.getValueAt(i, 1).toString().trim()+"";
//									statement.executeUpdate(item_qty);
//									String item_qty1="update "+company+".mr_item_master set commited_qty=commited_qty+"+Double.parseDouble(posscreen.model.getValueAt(i, 5).toString().trim())+" where item_code="+posscreen.model.getValueAt(i, 12).toString().trim()+"";
//									statement.executeUpdate(item_qty1);
//								}
								con.setAutoCommit(false);
								PreparedStatement ps = con.prepareStatement("insert into "+company+".mr_pos_settlement_cash_items ( `ref_receipt_num`,ref_reg_num, `barcode`, `item_desc`,item_code,item_cost, `unit`, `unit_price`, `qty`, `total`, `vat`,discount, `created_date`,sp, `vat_type`, `vat_perc`, `disc_type`,cf) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
								for(int i=0;i<posscreen.table.getRowCount();i++)
								{
									ps.setString(1,receipt_no);
									ps.setString(2,reg_num);
									ps.setString(3,posscreen.model.getValueAt(i, 1).toString().trim());
									//ps.setString(4,posscreen.model.getValueAt(i, 2).toString().trim());
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
								
								if(promt_print.equals("0"))
								{
									
									report_master.Bill_template.report(textField_8.getText(), con, printer,current_register,"",company,copy);
								}
								else
								{
									int a=JOptionPane.showConfirmDialog(frmRegisterNoS, "DO YOU WANT TO PRINT RECEIPT","",JOptionPane.YES_NO_OPTION);
									if(a==0)
									{
										report_master.Bill_template.report(textField_8.getText(), con, printer,current_register,"",company,copy);
									}
								}
								
								if(posscreen.CashChange.equals("1"))
								{
									new sales_master.cash_change(company, username, store_name, total, cash, decimalPlaces,"AR");
								}
								sales_master.posscreen.get_register_name(company,  username, store_name,con,posreg);
								sales_master.posscreen.current_register=posscreen.reg_nme;
								sales_master.posscreen.txtDisc.setText("");
								sales_master.posscreen.btnNewButton.setText("WALK IN");
								sales_master.posscreen.textField_6.setText("");
								sales_master.posscreen.textField_7.setText("0");
								sales_master.posscreen.lblNewLabel_2.setText("0%");
								sales_master.posscreen.textField_4.setText("0");
								sales_master.posscreen.textField_2.setText("0");
								sales_master.posscreen.textField.setText("0.00");
								sales_master.posscreen.textField_1.setText("0.00");
								sales_master.posscreen.textField_11.setText("0.00");
								sales_master.posscreen.textField_3.setText("0.00");
								sales_master.posscreen.lblNewLabel_1.setText("0");
								sales_master.posscreen.textField_9.setText("1");
								sales_master.posscreen.textField_12.setText("0");
								sales_master.posscreen.textField_5.setText("");
								sales_master.posscreen.textField_7.setText("0");
								sales_master.posscreen.lblNewLabel_2.setText("0");
								sales_master.posscreen.label.setText("");
								sales_master.posscreen.label_1.setText("");
								sales_master.posscreen.label_2.setText("");
								checkbillstatus="0";
								sales_master.posscreen.model.setRowCount(0);
								sales_master.posscreen.txtDisc.requestFocus();
								
								if(gs.length>1)
								{
								sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
								sales_master.SecondDisplay.textField_7.setText("0");
								sales_master.SecondDisplay.textField_4.setText("0");
								sales_master.SecondDisplay.textField_2.setText("0");
								sales_master.SecondDisplay.textField.setText("0.00");
								sales_master.SecondDisplay.textField_1.setText("0.00");
								sales_master.SecondDisplay.textField_11.setText("0.00");
								sales_master.SecondDisplay.lblNewLabel_1.setText("0");
								sales_master.SecondDisplay.textField_3.setText("0.00");
								sales_master.SecondDisplay.textField_5.setText("");
								sales_master.SecondDisplay.model.setRowCount(0);
								}
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "Do Not Have Balance To Purchase This Invoice");
						posscreen.txtDisc.requestFocus();
					}
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
		
	}
	
	private static void disc_amt(String company, String username,String store_name,String posreg)
	{
		try
		{
			String amount=txtDisc.getText();
			if(table.getRowCount()<=0)
			{
				JOptionPane.showMessageDialog(frmRegisterNoS, "Please Add Some items");
				posscreen.txtDisc.requestFocus();
			}
			else
			{
				//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
				if(amount.equals(""))
				{
					amount="0";
				}
				float total_sub=0;
				for(int i=0;i<model.getRowCount();i++)
				{
					String disc_type=table.getValueAt(i, 15).toString();
					String disc_item=table.getValueAt(i, 14).toString();
					if(disc_type.equals("N"))
					{
						if(disc_item.equals("0"))
						{
							double unit_price=Double.parseDouble(table.getValueAt(i, 4).toString());
							double qty=Double.parseDouble(table.getValueAt(i, 5).toString());
							String vattype=table.getModel().getValueAt(i, 10).toString();
							if(vattype.equals("EXC"))
							{
								total_sub+=(unit_price*qty);
							}
							if(vattype.equals("INC"))
							{
								total_sub+=((unit_price)*qty);
							}
							if(vattype.equals("NON"))
							{
								total_sub+=((unit_price)*qty);
							}
						}
					}
					
				}
				double disc_perc=(Double.parseDouble(amount)*100)/total_sub;
				if(Double.parseDouble(disc_amt)>=disc_perc)
				{
					double desc=0;
					double desc_amt=0;
					double grand_total=0;
					double total=0;
					double vatpric=0;
					double tax_rate = 0;
					double ttl_bf_disc=0;
					double ttl_af_disc=0;
					
					for(int i=0;i<model.getRowCount();i++)
					{
						double unit_price=Double.parseDouble(table.getValueAt(i, 4).toString());
						double qty=Double.parseDouble(table.getValueAt(i, 5).toString());
						String vattype=table.getModel().getValueAt(i, 10).toString();
						String disc_type=table.getValueAt(i, 15).toString();
						String disc_item=table.getValueAt(i, 14).toString();
						if(disc_type.equals("N"))
						{
							if(disc_item.equals("0"))
							{
								tax_rate=Double.parseDouble(table.getValueAt(i, 16).toString());
								table.setValueAt(df2.format(disc_perc), i, 6);
								if(gs.length>1)
								{
								SecondDisplay.table.setValueAt(df2.format(disc_perc), i, 6);
								}
								if(vattype.equals("EXC"))
								{
									desc=((unit_price*qty)*(disc_perc))/100;	
									desc_amt+=desc;
									total+=(unit_price*qty)-desc;
									vatpric+=((unit_price*qty)-desc)*tax_rate/100;
									double total_line=(unit_price*qty)-desc;
									double line_vat=((unit_price*qty)-desc)*tax_rate/100;
									
									table.setValueAt(df2.format(total_line), i, 7);
									table.setValueAt(df2.format(line_vat), i, 8);
									table.setValueAt(df2.format(total_line+line_vat), i, 9);
									if(gs.length>1)
									{
									SecondDisplay.table.setValueAt(df2.format(total_line), i, 7);
									SecondDisplay.table.setValueAt(df2.format(line_vat), i, 8);
									SecondDisplay.table.setValueAt(df2.format(total_line+line_vat), i, 9);
									}
								}
								if(vattype.equals("INC"))
								{
									desc=(((unit_price*qty)*100/105)*(disc_perc))/100;
									desc_amt+=desc;
									total+=((unit_price*100/105)*qty)-desc;
									vatpric+=(((unit_price*100/105)*qty)-desc)*tax_rate/100;
									double total_line=((unit_price*qty)*100/105)-desc;
									double line_vat=(((unit_price)*qty*100/105)-desc)*tax_rate/100;
									
									table.setValueAt(df2.format(total_line), i, 7);
									table.setValueAt(df2.format(line_vat), i, 8);
									table.setValueAt(df2.format(total_line+line_vat), i, 9);
									if(gs.length>1)
									{
									SecondDisplay.table.setValueAt(df2.format(total_line), i, 7);
									SecondDisplay.table.setValueAt(df2.format(line_vat), i, 8);
									SecondDisplay.table.setValueAt(df2.format(total_line+line_vat), i, 9);
									}
								}
								if(vattype.equals("NON"))
								{
									desc=((unit_price*qty)*(disc_perc))/100;	
									desc_amt+=desc;
									total+=((unit_price)*qty)-desc;
									vatpric+=0;
									table.setValueAt(df2.format(((unit_price)*qty)-desc), i, 7);
									table.setValueAt(df2.format(((unit_price)*qty)-desc), i, 9);
									if(gs.length>1)
									{
									SecondDisplay.table.setValueAt(df2.format(((unit_price)*qty)-desc), i, 7);
									SecondDisplay.table.setValueAt(df2.format(((unit_price)*qty)-desc), i, 9);
									}
								}
							}
							else
							{
								total+=Double.parseDouble(table.getValueAt(i, 7).toString());
								vatpric+=Double.parseDouble(table.getValueAt(i, 8).toString());
							}
						}
						else
						{
							total+=Double.parseDouble(table.getValueAt(i, 7).toString());
							vatpric+=Double.parseDouble(table.getValueAt(i, 8).toString());
						}
						
						double totl=Double.parseDouble(table.getValueAt(i, 7).toString());
						double vt=Double.parseDouble(table.getValueAt(i, 8).toString());
						
						if(vattype.equals("INC"))
						{
							ttl_af_disc+=totl+vt;
						}
						else
						{
							ttl_af_disc+=totl;
						}
						
						String ttl_before=df2.format(unit_price*qty);
						
						ttl_bf_disc+=Double.parseDouble(ttl_before);
						//ttl_bf_disc+=unit_price*qty;
					}
					String before_disc=df2.format(ttl_bf_disc);
					String after_disc=df2.format(ttl_af_disc);
					double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
					if(Double.isNaN(ttl_disc_perc))
					{
						ttl_disc_perc=0;
					}
					double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
					
	    			//String itm_disc=Double.toString(ttl_disc_perc);
	    			String itm_disc=df2.format(ttl_disc_perc);
	    			if(!itm_disc.equals(""))
					{
	    				grand_total=total+vatpric;
	    				
	    				double checksum=0;
	    				if(allow_roundoff.equals("1"))
	    				{
	    					String nettotal=df2.format(grand_total);
				    		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
				    		int number_decimal=Integer.parseInt(splitnumber);
				    		
				    		if(number_decimal>0 && number_decimal<13 )
				    		{
				    			checksum=0-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>12 && number_decimal<26 )
				    		{
				    			checksum=25-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>25 && number_decimal<38 )
				    		{
				    			checksum=25-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>37 && number_decimal<51 )
				    		{
				    			checksum=50-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>50 && number_decimal<63 )
				    		{
				    			checksum=50-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>62 && number_decimal<76 )
				    		{
				    			checksum=75-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>75 && number_decimal<88 )
				    		{
				    			checksum=75-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>87 && number_decimal<101 )
				    		{
				    			checksum=100-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
	    				}
						
			    		lblNewLabel_1.setText(Double.toString(checksum/100));
			    		
			    		
			    		textField.setText(df2.format(total));
						textField_3.setText(df2.format(grand_total));
						textField_1.setText(df2.format(vatpric));
						textField_2.setText(df2.format(ttl_disc_amt));
						textField_5.setText(itm_disc+"%");
						if(gs.length>1)
						{
						SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
						SecondDisplay.textField.setText(df2.format(total));
						SecondDisplay.textField_3.setText(df2.format(grand_total));
						SecondDisplay.textField_1.setText(df2.format(vatpric));
						SecondDisplay.textField_2.setText(df2.format(ttl_disc_amt));
						SecondDisplay.textField_5.setText(itm_disc+"%");
						}
						
						String space="";
			    		int print_price;
			    		print_price=8-textField_2.getText().length();
		    			for(int i=0; i<print_price;i++)
		    			{
		    				space+=" ";
		    			}
		    			String gap="";
		    			double grand_ttl=grand_total;
			    		String grnd_ttl=df2.format(grand_ttl);
			    		
						int grand_price=15-grnd_ttl.length();
						for(int i=0; i<grand_price;i++)
						{
							gap+=" ";
						}
						Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO DISC AMOUNT "+space+""+df2.format(desc_amt)+">"+display_port+"&&ECHO TOTAL"+""+gap+""+df2.format(grand_total)+">"+display_port+"\"");
			    		//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO DISC AMOUNT "+space+""+df2.format(desc_amt)+"TOTAL"+""+gap+""+df2.format(grand_total)+">"+display_port+"\""});
						txtDisc.setText("");
						txtDisc.requestFocus();
					}
	    			get_user_validate_dt(company, username,store_name,posreg);
				}
				else
				{
					frmRegisterNoS.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(frmRegisterNoS, "Maximum Sale Discount is "+disc_amt+" %");
					txtDisc.requestFocus();
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
		
	}
	
	public static void disc_perc(String company, String username,String store_name,String posreg, String input,String ptype)
	{
		try
		{
			String amount=input;
			if(table.getRowCount()<=0)
			{
				JOptionPane.showMessageDialog(frmRegisterNoS, "Please Add Some items");
				posscreen.txtDisc.requestFocus();
			}
			else
			{
				//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
				if(amount.equals(""))
				{
					amount="0";
				}
				if(Double.parseDouble(disc_amt)>=Double.parseDouble(amount))
				{
					double desc=0;
					//double desc_amt=0;
					double grand_total=0;
					double total=0;
					double vatpric=0;
					double tax_rate = 0;
					double ttl_bf_disc=0;
					double ttl_af_disc=0;
					
					for(int i=0;i<model.getRowCount();i++)
					{
						if(ptype.equals("CA"))
						{
							table.setValueAt("N", i, 15);
							if(gs.length>1)
							{
							SecondDisplay.table.setValueAt("N", i, 15);
							}
						}
						
						String vattype=table.getModel().getValueAt(i, 10).toString();
						double unit_price=Double.parseDouble(table.getValueAt(i, 4).toString());
						double qty=Double.parseDouble(table.getValueAt(i, 5).toString());
						String disc_type=table.getValueAt(i, 15).toString();
						String disc_item=table.getValueAt(i, 14).toString();
						if(disc_type.equals("N"))
						{
							if(disc_item.equals("0"))
							{
								tax_rate=Double.parseDouble(table.getValueAt(i, 16).toString());
								table.setValueAt(df2.format(Double.parseDouble(amount)), i, 6);
								if(vattype.equals("EXC"))
								{
									desc=((unit_price*qty)*(Double.parseDouble(amount)))/100;
									//desc_amt+=desc;
									total+=(unit_price*qty)-desc;
									vatpric+=((unit_price*qty)-desc)*tax_rate/100;
									double total_line=(unit_price*qty)-desc;
									double line_vat=((unit_price*qty)-desc)*tax_rate/100;
									table.setValueAt(df2.format(total_line), i, 7);
									table.setValueAt(df2.format(line_vat), i, 8);
									table.setValueAt(df2.format(total_line+line_vat), i, 9);
									if(gs.length>1)
									{
									SecondDisplay.table.setValueAt(df2.format(total_line), i, 7);
									SecondDisplay.table.setValueAt(df2.format(line_vat), i, 8);
									SecondDisplay.table.setValueAt(df2.format(total_line+line_vat), i, 9);
									}
								}
								if(vattype.equals("INC"))
								{
									desc=(((unit_price*qty)*100/105)*(Double.parseDouble(amount)))/100;
									//desc_amt+=desc;
									total+=((unit_price*100/105)*qty)-desc;
									vatpric+=(((unit_price*100/105)*qty)-desc)*tax_rate/100;
									double total_line=((unit_price)*qty*100/105)-desc;
									double line_vat=(((unit_price)*qty*100/105)-desc)*tax_rate/100;
									
									table.setValueAt(df2.format(total_line), i, 7);
									table.setValueAt(df2.format(line_vat), i, 8);
									table.setValueAt(df2.format(total_line+line_vat), i, 9);
									if(gs.length>1)
									{
									SecondDisplay.table.setValueAt(df2.format(total_line), i, 7);
									SecondDisplay.table.setValueAt(df2.format(line_vat), i, 8);
									SecondDisplay.table.setValueAt(df2.format(total_line+line_vat), i, 9);
									}
								}
								if(vattype.equals("NON"))
								{
									desc=((unit_price*qty)*(Double.parseDouble(amount)))/100;
									//desc_amt+=desc;
									total+=((unit_price)*qty)-desc;
									vatpric+=0;
									table.setValueAt(df2.format(((unit_price)*qty)-desc), i, 7);
									table.setValueAt(df2.format(((unit_price)*qty)-desc), i, 9);
									if(gs.length>1)
									{
									SecondDisplay.table.setValueAt(df2.format(((unit_price)*qty)-desc), i, 7);
									SecondDisplay.table.setValueAt(df2.format(((unit_price)*qty)-desc), i, 9);
									}
								}
								if(ptype.equals("CUST"))
								{
									if(!amount.equals("0"))
									{
										table.setValueAt("C", i, 15);
										if(gs.length>1)
										{
										SecondDisplay.table.setValueAt("C", i, 15);
										}
									}
									
								}
							}
							else
							{
								total+=Double.parseDouble(table.getValueAt(i, 7).toString());
								vatpric+=Double.parseDouble(table.getValueAt(i, 8).toString());
							}
							
						}
						else
						{
							total+=Double.parseDouble(table.getValueAt(i, 7).toString());
							vatpric+=Double.parseDouble(table.getValueAt(i, 8).toString());
						}
						
						
						double totl=Double.parseDouble(table.getValueAt(i, 7).toString());
						double vt=Double.parseDouble(table.getValueAt(i, 8).toString());
						
						if(vattype.equals("INC"))
						{
							ttl_af_disc+=totl+vt;
						}
						else
						{
							ttl_af_disc+=totl;
						}
						
						String ttl_before=df2.format(unit_price*qty);
						
						ttl_bf_disc+=Double.parseDouble(ttl_before);
						//ttl_bf_disc+=unit_price*qty;
						
						
					}
					
					String before_disc=df2.format(ttl_bf_disc);
					String after_disc=df2.format(ttl_af_disc);
					double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
					if(Double.isNaN(ttl_disc_perc))
					{
						ttl_disc_perc=0;
					}
					double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
					
	    			String itm_disc=Double.toString(ttl_disc_perc);
	    			if(!itm_disc.equals(""))
					{
	    				grand_total=total+vatpric;
	    				
	    				double checksum=0;
	    				if(allow_roundoff.equals("1"))
	    				{
	    					String nettotal=df2.format(grand_total);
				    		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
				    		int number_decimal=Integer.parseInt(splitnumber);
				    		
				    		if(number_decimal>0 && number_decimal<13 )
				    		{
				    			checksum=0-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>12 && number_decimal<26 )
				    		{
				    			checksum=25-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>25 && number_decimal<38 )
				    		{
				    			checksum=25-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>37 && number_decimal<51 )
				    		{
				    			checksum=50-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>50 && number_decimal<63 )
				    		{
				    			checksum=50-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>62 && number_decimal<76 )
				    		{
				    			checksum=75-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>75 && number_decimal<88 )
				    		{
				    			checksum=75-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>87 && number_decimal<101 )
				    		{
				    			checksum=100-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
	    				}
	    				lblNewLabel_1.setText(Double.toString(checksum/100));
	    				if(gs.length>1)
						{
	    				SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
						}
			    		
		        		textField.setText(df2.format(total));
						textField_3.setText(df2.format(grand_total));
						textField_1.setText(df2.format(vatpric));
						textField_2.setText(df2.format(ttl_disc_amt));
						textField_5.setText(df2.format(Double.parseDouble(itm_disc))+"%");
						if(gs.length>1)
						{
						SecondDisplay.textField.setText(df2.format(total));
						SecondDisplay.textField_3.setText(df2.format(grand_total));
						SecondDisplay.textField_1.setText(df2.format(vatpric));
						SecondDisplay.textField_2.setText(df2.format(ttl_disc_amt));
						SecondDisplay.textField_5.setText(df2.format(Double.parseDouble(itm_disc))+"%");
						}
						String space="";
			    		int print_price;
			    		String perc_disc=df2.format(Double.parseDouble(itm_disc));
			    		print_price=10-perc_disc.length();
		    			for(int i=0; i<print_price;i++)
		    			{
		    				space+=" ";
		    			}
		    			String gap="";
		    			double grand_ttl=grand_total;
			    		String grnd_ttl=df2.format(grand_ttl);
			    		
						int grand_price=15-grnd_ttl.length();
						for(int i=0; i<grand_price;i++)
						{
							gap+=" ";
						}
						Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO DISC PERC "+space+""+df2.format(Double.parseDouble(itm_disc))+">"+display_port+"&&ECHO TOTAL"+""+gap+""+df2.format(grand_total)+">"+display_port+"\"");
			    		txtDisc.setText("");
						txtDisc.requestFocus();
					}
	    			get_user_validate_dt(company, username,store_name,posreg);
				}
				else
				{
					frmRegisterNoS.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(frmRegisterNoS, "Maximum Sale Discount is "+disc_amt+" %");
					txtDisc.requestFocus();
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
		
	}

	private static void return_list(String company, String username,String store_name,String posreg)
	{
		try
		{
			if(btnQuickReturn_1.getText().equals("QUICK RETURN (F8)"))
			{
				btnQuickReturn_1.setText("CANCEL RETURN (F8)");
				txtDisc.requestFocus();
				txtDisc.setBackground(new Color(243, 229, 216));
				txtDisc.setForeground(Color.black);
				txtDisc.setCaretColor(Color.black);
				returns="return";
			}
			else
			{
				btnQuickReturn_1.setText("QUICK RETURN (F8)");
				txtDisc.setBackground(Color.BLACK);
				txtDisc.setCaretColor(Color.WHITE);
				txtDisc.setForeground(Color.WHITE);
				txtDisc.requestFocus();
				returns="";
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
		
	}
	private static void paycode_settle(String company, String username,String store_name,String posreg,String version)
	{
		try
		{
			String type="PAYCODE";
			String reg_num=current_register;
			String receipt_no=textField_8.getText();
			String total=textField_3.getText();
			String cust_name=btnNewButton.getText();
			String qty=textField_4.getText();
			if(Double.parseDouble(total)==0)
			{
				JOptionPane.showMessageDialog(frmRegisterNoS, "Scan Some Items");
				posscreen.txtDisc.requestFocus();
			}
			else if(Double.parseDouble(total)<0)
			{
				JOptionPane.showMessageDialog(frmRegisterNoS, "Settle Amount is Less than Zero, If it is a cash refund choose cash button to proceed otherwise check the amount");
				posscreen.txtDisc.requestFocus();
			}
			else
			{
				
				new paycode(company,username,store_name,type,reg_num,receipt_no,cust_name,disc_amt, version,qty,decimalPlaces,"","",textField_9.getText());
				btnPayCode.setEnabled(false);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
		
	}
	private static void cash_settle(String company, String username,String store_name,String posreg,String version,String amount)
	{
		try
		{
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement= con.createStatement();
			//Statement statement21= con.createStatement();
			String receipt_no=textField_8.getText();
			String reg_num=current_register;
			
//			String query21="SELECT * FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ";
//			ResultSet resultSet21= statement21.executeQuery(query21);
//			if(!resultSet21.next())
//			{
//				posscreen.page_type="Kitchen KOT";
//				posscreen.hold_inv=true;
//				posscreen.hold_inv(company, username,store_name, posreg, con,"QUICK DINE IN",version,"QUICK DINE IN","QUICK DINE IN");
//			}
			
			String kotsts="0";
			for(int i=0;i<posscreen.table.getRowCount();i++)
			{
				String kot=model.getValueAt(i, 19).toString();
				if(kot.equals("1"))
				{
					kotsts="1";
				}
			}
			
			if(kotsts.equals("0"))
			{
				label_2.setText("HOME DELIVERY");
				label.setText("HOME DELIVERY");
				//hold_inv(company, username, store_name, posreg,con,"",version,"","",textField_8.getText(),"0");	
				posscreen.Kot_hold_inv(company, username,store_name, posreg, con,"HOME DELIVERY",version,"","HOME DELIVERY",posscreen.textField_8.getText(),"0","");
			}
			
			
			
			
			String cash=textField_3.getText();
			if(model.getRowCount()==0)
			{
				if(cash_change.Active=="1")
				{
					cash_change.frmCashTenderedAnd.setAlwaysOnTop(false);
				}
				
				JOptionPane.showMessageDialog(frmRegisterNoS, "No Items Found");
				if(cash_change.Active=="1")
				{
					cash_change.frmCashTenderedAnd.setAlwaysOnTop(true);
				}
				
				txtDisc.requestFocus();
			}
			else
			{
				if(txtDisc.getText().equals(""))
				{
					if(amount.equals(""))
					{
						cash=textField_3.getText();
					}
					else
					{
						cash=amount;
					}
					String type="";
					if(Double.parseDouble(cash)<0)
					{
						type="REFUND";
					}
					else
					{
						type="CASH";
					}
					String user=username;
					String total=textField_3.getText();
					String cust_name=btnNewButton.getText();
					String vat=textField_1.getText();
					String disc=textField_2.getText();
					String qty=textField_4.getText();
					String table_num=label.getText();
					String delivery_user=label_1.getText();
					String mode=label_2.getText();
					if(posscreen.change_hold_type.equals("1"))
					{
						if(table_num.equals(""))
						{
							table_num="CASH SALE";
						}
						
						if(delivery_user.equals(""))
						{
							delivery_user="CASH SALE";
						}
						
						if(mode.equals(""))
						{
							mode="CASH SALE";
						}
					}
					
					
					
					
					cal = Calendar.getInstance();
					
//					ResultSet resultSet122 = statement.executeQuery("select * from "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ");
//					if(resultSet122.next())
//					{
//						JOptionPane.showMessageDialog(frmRegisterNoS, "RECEIPT NUMBER ALREADY EXIST");
//						return;
//					}
					
					
					ResultSet resultSet11 = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ");
					if(!resultSet11.next())
					{
						String query="insert into "+company+".mr_pos_settlement_cash (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, `cash_paid`,type,store_id, `created_date`,grand_total,cust_code,table_num,delivery_user,mode,cust_ph) values('"+reg_num+"','"+receipt_no+"','"+user+"','"+times.format(cal.getTime())+"','"+textField.getText()+"','"+cust_name+"','"+vat+"','"+disc+"','"+qty+"','"+cash+"','"+type+"','"+store_name+"','"+formattedDate+"','"+total+"','"+textField_9.getText()+"','"+table_num+"','"+delivery_user+"','"+mode+"','"+posscreen.textField_12.getText()+"') ";
						statement.executeUpdate(query);
						if(old_recipt.equals(""))
						{
							ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_num+"' ");
							if(resultSet1.next())
							{
								int nxt_num=Integer.parseInt(resultSet1.getString("next_num"))+1;
								String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+reg_num+"' ";
								statement.executeUpdate(update_receipt_num);
							}
						}
						String query5="SELECT * FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ";
						ResultSet resultSet5= statement.executeQuery(query5);
						if(resultSet5.next())
						{
							String query6="delete FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ";
							statement.executeUpdate(query6);
							String query7="delete FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+receipt_no+"' and ref_reg_no='"+reg_num+"' ";
							statement.executeUpdate(query7);
						}
						
//						for(int i=0;i<posscreen.table.getRowCount();i++)
//						{
//							String item_qty="update "+company+".mr_tmp_item_master set commited_qty=commited_qty+"+Double.parseDouble(posscreen.model.getValueAt(i, 5).toString().trim())+" where barcode="+posscreen.model.getValueAt(i, 1).toString().trim()+"";
//							statement.executeUpdate(item_qty);
//							String item_qty1="update "+company+".mr_item_master set commited_qty=commited_qty+"+Double.parseDouble(posscreen.model.getValueAt(i, 5).toString().trim())+" where item_code="+posscreen.model.getValueAt(i, 12).toString().trim()+"";
//							statement.executeUpdate(item_qty1);
//						}
						con.setAutoCommit(false);
						PreparedStatement ps = con.prepareStatement("insert into "+company+".mr_pos_settlement_cash_items ( `ref_receipt_num`,ref_reg_num, `barcode`, `item_desc`,item_code,item_cost, `unit`, `unit_price`, `qty`, `total`, `vat`,discount, `created_date`,sp, `vat_type`, `vat_perc`, `disc_type`,cf) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						for(int i=0;i<posscreen.table.getRowCount();i++)
						{
							ps.setString(1,receipt_no);
							ps.setString(2,reg_num);
							ps.setString(3,posscreen.model.getValueAt(i, 1).toString().trim());
							//ps.setString(4,posscreen.model.getValueAt(i, 2).toString().trim());
							ps.setString(4,posscreen.model.getValueAt(i, 2).toString().trim().replaceAll("<html>", "").replaceAll("<html>", "").replaceAll("</html>", ""));
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
						
						
						
						if(promt_print.equals("0"))
						{
							//byte[] open = {27,112,48,55,121};
					        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
					        DocPrintJob job = pservice.createPrintJob();
					        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
					        Doc doc = new SimpleDoc(open,flavor,null);
					        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
					        job.print(doc, aset);
							report_master.Bill_template.report(textField_8.getText(), con, printer,current_register,"",company,copy);
						}
						else
						{
							int a=JOptionPane.showConfirmDialog(frmRegisterNoS, "DO YOU WANT TO PRINT RECEIPT","",JOptionPane.YES_NO_OPTION);
							if(a==0)
							{
								//byte[] open = {27,112,48,55,121};
						        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
						        DocPrintJob job = pservice.createPrintJob();
						        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
						        Doc doc = new SimpleDoc(open,flavor,null);
						        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
						        job.print(doc, aset);
								report_master.Bill_template.report(textField_8.getText(), con, printer,current_register,"",company,copy);
							}
							else
							{
								//byte[] open = {27,112,48,55,121};
						        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
						        DocPrintJob job = pservice.createPrintJob();
						        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
						        Doc doc = new SimpleDoc(open,flavor,null);
						        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
						        job.print(doc, aset);
								manager_name=username;
						        get_user_validate_dt( company, username,store_name,posreg);
						        
							}
						}
						if(posscreen.CashChange.equals("1"))
						{
							new sales_master.cash_change(company, username, store_name, total, cash, decimalPlaces,"CASH");
						}
						
						sales_master.posscreen.get_register_name(company,  username, store_name,con,posreg);
						sales_master.posscreen.txtDisc.setText("");
						sales_master.posscreen.btnNewButton.setText("WALK IN");
						sales_master.posscreen.textField_6.setText("");
						sales_master.posscreen.textField_7.setText("0");
						sales_master.posscreen.lblNewLabel_2.setText("0%");
						sales_master.posscreen.textField_4.setText("0");
						sales_master.posscreen.textField_2.setText("0");
						sales_master.posscreen.textField.setText("0.00");
						sales_master.posscreen.textField_1.setText("0.00");
						sales_master.posscreen.textField_11.setText("0.00");
						sales_master.posscreen.lblNewLabel_1.setText("0");
						sales_master.posscreen.textField_9.setText("1");
						sales_master.posscreen.textField_12.setText("0");
						sales_master.posscreen.textField_3.setText("0.00");
						sales_master.posscreen.textField_7.setText("0");
						sales_master.posscreen.lblNewLabel_2.setText("0");
						sales_master.posscreen.textField_5.setText("");
						sales_master.posscreen.label.setText("");
						sales_master.posscreen.label_1.setText("");
						sales_master.posscreen.label_2.setText("");
						sales_master.posscreen.model.setRowCount(0);
						if(gs.length>1)
						{
						sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
						sales_master.SecondDisplay.textField_7.setText("0");
						sales_master.SecondDisplay.textField_4.setText("0");
						sales_master.SecondDisplay.textField_2.setText("0");
						sales_master.SecondDisplay.textField.setText("0.00");
						sales_master.SecondDisplay.textField_1.setText("0.00");
						sales_master.SecondDisplay.textField_11.setText("0.00");
						sales_master.SecondDisplay.lblNewLabel_1.setText("0");
						sales_master.SecondDisplay.textField_3.setText("0.00");
						sales_master.SecondDisplay.textField_5.setText("");
						sales_master.SecondDisplay.model.setRowCount(0);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "RECEIPT NUMBER ALREADY EXIST");
					}
				}
				else
				{
					cash=txtDisc.getText();
					if(!cash.matches("-?\\d+(\\.\\d+)?"))
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "Enter Some Valid Amount");
						txtDisc.requestFocus();
					}
					else if(Double.parseDouble(cash)<Double.parseDouble(textField_3.getText()))
					{
						JOptionPane.showMessageDialog(frmRegisterNoS, "Settle Amount is Less than Total Amount");
						txtDisc.requestFocus();
					}
					else
					{
						String type="CASH";
						String user=username;
						String total=textField_3.getText();
						String cust_name=btnNewButton.getText();
						String vat=textField_1.getText();
						String disc=textField_2.getText();
						String qty=textField_4.getText();
						
						cal = Calendar.getInstance();
						
						ResultSet resultSet11 = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ");
						if(!resultSet11.next())
						{
							String query="insert into "+company+".mr_pos_settlement_cash (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, `cash_paid`,type,store_id, `created_date`,grand_total,cust_code,table_num,delivery_user,mode,cust_ph) values('"+reg_num+"','"+receipt_no+"','"+user+"','"+times.format(cal.getTime())+"','"+textField.getText()+"','"+cust_name+"','"+vat+"','"+disc+"','"+qty+"','"+cash+"','"+type+"','"+store_name+"','"+formattedDate+"','"+total+"','"+textField_9.getText()+"','"+label.getText()+"','"+label_1.getText()+"','"+label_2.getText()+"','"+posscreen.textField_12.getText()+"') ";
							statement.executeUpdate(query);
							if(old_recipt.equals(""))
							{
								ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_num+"' ");
								if(resultSet1.next())
								{
									int nxt_num=Integer.parseInt(resultSet1.getString("next_num"))+1;
									String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+reg_num+"' ";
									statement.executeUpdate(update_receipt_num);
								}
							}
							String query5="SELECT * FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ";
							ResultSet resultSet5= statement.executeQuery(query5);
							if(resultSet5.next())
							{
								String query6="delete FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ";
								statement.executeUpdate(query6);
								String query7="delete FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+receipt_no+"' and ref_reg_no='"+reg_num+"' ";
								statement.executeUpdate(query7);
							}
							con.setAutoCommit(false);
							PreparedStatement ps = con.prepareStatement("insert into "+company+".mr_pos_settlement_cash_items ( `ref_receipt_num`,ref_reg_num, `barcode`, `item_desc`,item_code,item_cost, `unit`, `unit_price`, `qty`, `total`, `vat`,discount, `created_date`,sp, `vat_type`, `vat_perc`, `disc_type`,cf) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
							for(int i=0;i<posscreen.table.getRowCount();i++)
							{
								ps.setString(1,receipt_no);
								ps.setString(2,reg_num);
								ps.setString(3,posscreen.model.getValueAt(i, 1).toString().trim());
								//ps.setString(4,posscreen.model.getValueAt(i, 2).toString().trim());
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
							
							
							
							
							
							if(promt_print.equals("0"))
							{
								//byte[] open = {27,112,48,55,121};
						        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
						        DocPrintJob job = pservice.createPrintJob();
						        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
						        Doc doc = new SimpleDoc(open,flavor,null);
						        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
						        job.print(doc, aset);
								
								report_master.Bill_template.report(textField_8.getText(), con, printer,current_register,"",company,copy);
							}
							else
							{
								int a=JOptionPane.showConfirmDialog(frmRegisterNoS, "DO YOU WANT TO PRINT RECEIPT","",JOptionPane.YES_NO_OPTION);
								if(a==0)
								{
									//byte[] open = {27,112,48,55,121};
							        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
							        DocPrintJob job = pservice.createPrintJob();
							        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
							        Doc doc = new SimpleDoc(open,flavor,null);
							        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
							        job.print(doc, aset);
									report_master.Bill_template.report(textField_8.getText(), con, printer,current_register,"",company,copy);
								}
								else
								{
									//byte[] open = {27,112,48,55,121};
							        PrintService pservice =  PrintServiceLookup.lookupDefaultPrintService(); 
							        DocPrintJob job = pservice.createPrintJob();
							        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
							        Doc doc = new SimpleDoc(open,flavor,null);
							        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
							        job.print(doc, aset);
										manager_name=username;
							            get_user_validate_dt( company, username,store_name,posreg);
							            
								}
							}
							if(posscreen.CashChange.equals("1"))
							{
								new sales_master.cash_change(company, username, store_name, total, cash, decimalPlaces,"CASH");
							}
							current_register=posreg;
							sales_master.posscreen.get_register_name(company,  username, store_name,con,posreg);
							sales_master.posscreen.txtDisc.setText("");
							sales_master.posscreen.btnNewButton.setText("WALK IN");
							sales_master.posscreen.textField_6.setText("");
							sales_master.posscreen.textField_7.setText("0");
							sales_master.posscreen.lblNewLabel_2.setText("0%");
							sales_master.posscreen.textField_4.setText("0");
							sales_master.posscreen.textField_2.setText("0");
							sales_master.posscreen.textField.setText("0.00");
							sales_master.posscreen.textField_1.setText("0.00");
							sales_master.posscreen.textField_11.setText("0.00");
							sales_master.posscreen.lblNewLabel_1.setText("0");
							sales_master.posscreen.textField_3.setText("0.00");
							sales_master.posscreen.textField_9.setText("1");
							sales_master.posscreen.textField_12.setText("0");
							sales_master.posscreen.textField_5.setText("");
							sales_master.posscreen.label.setText("");
							sales_master.posscreen.label_1.setText("");
							sales_master.posscreen.label_2.setText("");
							checkbillstatus="0";
							sales_master.posscreen.model.setRowCount(0);
							if(gs.length>1)
							{
							sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
							sales_master.SecondDisplay.textField_7.setText("0");
							sales_master.SecondDisplay.textField_4.setText("0");
							sales_master.SecondDisplay.textField_2.setText("0");
							sales_master.SecondDisplay.textField.setText("0.00");
							sales_master.SecondDisplay.textField_1.setText("0.00");
							sales_master.SecondDisplay.textField_11.setText("0.00");
							sales_master.SecondDisplay.lblNewLabel_1.setText("0");
							sales_master.SecondDisplay.textField_3.setText("0.00");
							sales_master.SecondDisplay.textField_5.setText("");
							sales_master.SecondDisplay.model.setRowCount(0);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(frmRegisterNoS, "Receipt Number Already Exist");
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
		
	}
	static void hold_inv(String company, String username,String store_name,String posreg,Connection con, String table_num, String version, String delivery_user, String mode, String holdreceipt, String actiontype)
	{
		try
		{
			Statement statement1 = con.createStatement();
			if(table.getRowCount()>0)
			{
				String chkbillsts="0";
				if(actiontype.equals("1"))
				{
					chkbillsts="1";
				}
				else
				{
					chkbillsts=checkbillstatus;
				}
				
				if(change_hold_type.equals("1"))
				{
					if(hold_inv==true)
					{
						
					}
					else
					{
						if(old_recipt.equals(""))
						{
							ResultSet resultSet11 = statement1.executeQuery("select * from "+company+".mr_pos_settlement_cash where receipt_no='"+holdreceipt+"' and reg_num='"+current_register+"' ");
							if(resultSet11.next())
							{
								ResultSet resultSet1 = statement1.executeQuery("select * from "+company+".mr_register_setup where register_number='"+current_register+"' ");
								if(resultSet1.next())
								{
									int nxt_num=Integer.parseInt(resultSet1.getString("next_num"))+1;
									int invnum=Integer.parseInt(resultSet1.getString("invoice_number"))+ nxt_num;
									String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+current_register+"' ";
									statement1.executeUpdate(update_receipt_num);
									
									textField_8.setText(Integer.toString(invnum));
								}
							}
							else
							{
								new sales_master.hold_select(company, username, store_name, current_register, con,version);
							}
							
						}
						else
						{
							String query5="SELECT * FROM "+company+".mr_pos_hold_invoice where receipt_no='"+holdreceipt+"' and reg_num='"+current_register+"' ";
							ResultSet resultSet5= statement1.executeQuery(query5);
							if(resultSet5.next())
							{
								table_num=resultSet5.getString("table_num");
								delivery_user=resultSet5.getString("delivery_user");
								if(table_num.equals("QUICK DINE IN") || table_num.equals("HOME DELIVERY") || table_num.equals("TAKE AWAY"))
								{
									mode=resultSet5.getString("table_num");
								}
								else
								{
									mode="DINE IN";
								}
								
								page_type="Kitchen KOT";
								hold_inv=true;
							}
						}
						
					}
					
				}
				else
				{
//					JLabel label = new JLabel("Do You Want To Hold This Invoice");
//					label.setFont(new Font("Arial", Font.BOLD, 18));
//					
					 
					int a=JOptionPane.showConfirmDialog(frmRegisterNoS, "Do You Want To Hold This Invoice","WARNING",JOptionPane.YES_NO_OPTION);
					if(a==0)
					{
						hold_inv=true;
					}
				}
				
				if(hold_inv==true)
				{
					if(pause_kot_print.equals("1"))
					{
						page_type="Hold Invoice";
					}
					if(mode.equals(""))
					{
						mode="HOME DELIVERY";
					}
					String round=lblNewLabel_1.getText();
					String user=username;
					String receipt_no=holdreceipt;
					String total=textField_3.getText();
					String cust_name=btnNewButton.getText();
					String vat=textField_1.getText();
					String disc=textField_2.getText();
					String qty=textField_4.getText();
					double ttl=Double.parseDouble(total)-Double.parseDouble(round);
					
					Statement statement = con.createStatement();
					Statement statement2 = con.createStatement();
					Statement statement3 = con.createStatement();
		    		
					boolean stshold=false;
					if(old_recipt.equals(""))
					{
						ResultSet resultSet11 = statement1.executeQuery("select * from "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+current_register+"' ");
						if(resultSet11.next())
						{
							ResultSet resultSet1 = statement1.executeQuery("select * from "+company+".mr_register_setup where register_number='"+current_register+"' ");
							if(resultSet1.next())
							{
								int nxt_num=Integer.parseInt(resultSet1.getString("next_num"))+1;
								int invnum=Integer.parseInt(resultSet1.getString("invoice_number"))+ nxt_num;
								String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+current_register+"' ";
								statement1.executeUpdate(update_receipt_num);
								
								textField_8.setText(Integer.toString(invnum));
								receipt_no=Integer.toString(invnum);
								stshold=true;
							
							}
							
						}
						else
						{
							stshold=true;
						}
					}
					else
					{
						stshold=true;
					}
					if(stshold==true)
					{
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+current_register+"' ");
						if(!resultSet.next())
						{
							cal = Calendar.getInstance();
							String query="insert into "+company+".mr_pos_hold_invoice (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `total_qty`, store_id, `created_date`,cust_code,table_num,delivery_user,mode,cust_ph,Chkbill_Sts) values('"+current_register+"','"+receipt_no+"','"+user+"','"+times.format(cal.getTime())+"','"+ttl+"','"+cust_name+"','"+vat+"','"+disc+"','"+qty+"','"+store_name+"','"+formattedDate+"','"+textField_9.getText()+"','"+table_num+"','"+delivery_user+"','"+mode+"','"+posscreen.textField_12.getText()+"','"+chkbillsts+"') ";
							statement.executeUpdate(query);
							ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+current_register+"' ");
							if(resultSet1.next())
							{
								int nxt_num=Integer.parseInt(resultSet1.getString("next_num"))+1;
								String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+current_register+"' ";
								statement.executeUpdate(update_receipt_num);
							}
							for(int i=0;i<model.getRowCount();i++)
							{
								String itemname=model.getValueAt(i, 2).toString().replaceAll("<html>", "").replaceAll("</html>", "");
								String query4="insert into "+company+".mr_pos_hold_invoice_items ( `ref_receipt_num`,ref_reg_no, `barcode`, `item_desc`,item_code, `unit`, `unit_price`, `qty`, `total`, `vat`,discount, `created_date`,vat_type, `cost`, `sp`, `disc_type`, `commited_qty`, `vat_perc`,print_cmd,department,kot_number,comments_item,cf,print_sts,scale_sts) "
										+ "values ('"+receipt_no+"','"+current_register+"','"+model.getValueAt(i, 1).toString()+"','"+itemname+"','"+model.getValueAt(i, 13).toString()+"','"+model.getValueAt(i, 3).toString()+"','"+model.getValueAt(i,4).toString()+"','"+model.getValueAt(i, 5).toString()+"','"+model.getValueAt(i, 7).toString()+"','"+model.getValueAt(i, 8).toString()+"','"+model.getValueAt(i, 6).toString()+"','"+formattedDate+"','"+model.getValueAt(i, 10).toString()+"','"+model.getValueAt(i, 11).toString()+"','"+model.getValueAt(i, 12).toString()+"','"+model.getValueAt(i, 15).toString()+"','"+model.getValueAt(i, 14).toString()+"','"+model.getValueAt(i, 16).toString()+"','"+model.getValueAt(i, 17).toString()+"','"+model.getValueAt(i, 18).toString()+"','"+model.getValueAt(i, 19).toString()+"','"+model.getValueAt(i, 21).toString()+"','"+model.getValueAt(i, 22).toString()+"','"+model.getValueAt(i, 23).toString()+"','"+model.getValueAt(i, 24).toString()+"') ";
								statement.executeUpdate(query4);
							}
						}
						else
						{
							String query6="delete FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+current_register+"' ";
							statement.executeUpdate(query6);
							String query7="delete FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+receipt_no+"' and ref_reg_no='"+current_register+"' ";
							statement.executeUpdate(query7);
							cal = Calendar.getInstance();
							String query="insert into "+company+".mr_pos_hold_invoice (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `total_qty`, store_id, `created_date`,cust_code,table_num,delivery_user,mode,cust_ph,Chkbill_Sts) values('"+current_register+"','"+receipt_no+"','"+user+"','"+times.format(cal.getTime())+"','"+total+"','"+cust_name+"','"+vat+"','"+disc+"','"+qty+"','"+store_name+"','"+formattedDate+"','"+textField_9.getText()+"','"+table_num+"','"+delivery_user+"','"+mode+"','"+posscreen.textField_12.getText()+"','"+chkbillsts+"') ";
							statement.executeUpdate(query);
							
							for(int i=0;i<model.getRowCount();i++)
							{
								String itemname=model.getValueAt(i, 2).toString().replaceAll("<html>", "").replaceAll("</html>", "");
								String query4="insert into "+company+".mr_pos_hold_invoice_items ( `ref_receipt_num`,ref_reg_no, `barcode`, `item_desc`,item_code, `unit`, `unit_price`, `qty`, `total`, `vat`,discount, `created_date`,vat_type, `cost`, `sp`, `disc_type`, `commited_qty`, `vat_perc`,print_cmd,department,kot_number,comments_item,cf,print_sts,scale_sts) "
										+ "values ('"+receipt_no+"','"+current_register+"','"+model.getValueAt(i, 1).toString()+"','"+itemname+"','"+model.getValueAt(i, 13).toString()+"','"+model.getValueAt(i, 3).toString()+"','"+model.getValueAt(i,4).toString()+"','"+model.getValueAt(i, 5).toString()+"','"+model.getValueAt(i, 7).toString()+"','"+model.getValueAt(i, 8).toString()+"','"+model.getValueAt(i, 6).toString()+"','"+formattedDate+"','"+model.getValueAt(i, 10).toString()+"','"+model.getValueAt(i, 11).toString()+"','"+model.getValueAt(i, 12).toString()+"','"+model.getValueAt(i, 15).toString()+"','"+model.getValueAt(i, 14).toString()+"','"+model.getValueAt(i, 16).toString()+"','"+model.getValueAt(i, 17).toString()+"','"+model.getValueAt(i, 18).toString()+"','"+model.getValueAt(i, 19).toString()+"','"+model.getValueAt(i, 21).toString()+"','"+model.getValueAt(i, 22).toString()+"','"+model.getValueAt(i, 23).toString()+"','"+model.getValueAt(i, 24).toString()+"') ";
								statement.executeUpdate(query4);
							}
						}
						Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO INVOICE HOLDED..>"+display_port+"\"");
						//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO INVOICE HOLDED..>"+display_port+"\""});
						
						if(Mainpage.mediacontrol.equals("Y"))
						{
							AudioInputStream audioInputStream;
		    				File file = new File("C:/MasterRetails/media/"+holdInv);
		    				audioInputStream = AudioSystem.getAudioInputStream(file);
		    				Clip line;
		    				line = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioInputStream.getFormat()));
		    				line.open(audioInputStream);
		    				line.start();
		    				line.drain();
		    				audioInputStream.close();
						}
						
						
						
						if(change_hold_type.equals("1"))
						{
							if(page_type.equals("Hold Invoice"))
							{
								if(PauseHoldPrint.equals("0")){
									report_master.holded_invoice.report(textField_8.getText(), con, printer,current_register,"",company,page_type,"","");	
								}
								
							}
							else
							{
								String print_commad="0";
								if(hold_bill.equals("1"))
								{
									int kot_num=0;
									String sel_kot="select kot_number,print_cmd from "+company+".mr_pos_hold_invoice_items where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"'  order by kot_number desc limit 1 ";
									ResultSet resultSet3 = statement.executeQuery(sel_kot);
									if(resultSet3.next())
									{
										kot_num=Integer.parseInt(resultSet3.getString("kot_number"));
										print_commad=resultSet3.getString("print_cmd");
									}
									String dept="select department from "+company+".mr_pos_hold_invoice_items where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"' and print_sts='0'  group by department ";
									ResultSet resultSet1 = statement.executeQuery(dept);
									while(resultSet1.next())
									{
										kot_num=kot_num+1;
										String query7="update "+company+".mr_pos_hold_invoice_items set kot_number='"+kot_num+"' where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"' and department='"+resultSet1.getString("department")+"'  and print_sts='0' ";
										statement1.executeUpdate(query7);
										
										String printer_dept="select printer from "+company+".mr_assign_kot_printer where reg_nme='"+current_register+"' and dept_name='"+resultSet1.getString("department")+"' ";
										ResultSet resultSet2 = statement1.executeQuery(printer_dept);
										if(resultSet2.next())
										{
											printer2=resultSet2.getString("printer");
											int b=JOptionPane.showConfirmDialog(frmRegisterNoS, "DO YOU WANT TO PRINT KOT RECEIPT","",JOptionPane.YES_NO_OPTION);
											if(b==0)
											{
												report_master.holded_invoice.report(textField_8.getText(), con, printer2,current_register,"",company,page_type,resultSet1.getString("department"),"");
											}
												
										}
										else
										{
											frmRegisterNoS.setAlwaysOnTop(true);
											JOptionPane.showMessageDialog(frmRegisterNoS, "Printer Not Assigned For This Department");
											posscreen.txtDisc.requestFocus();
										}
										
									}
								}
								else
								{
									int kot_num=0;
									String sel_kot="select kot_number,print_cmd from "+company+".mr_pos_hold_invoice_items where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"'  order by kot_number desc limit 1 ";
									ResultSet resultSet3 = statement.executeQuery(sel_kot);
									if(resultSet3.next())
									{
										kot_num=Integer.parseInt(resultSet3.getString("kot_number"));
										print_commad=resultSet3.getString("print_cmd");
									}
									String printer3="";
									String get_printer="select printer from "+company+".mr_assign_kot_printer inner join "+company+".mr_pos_hold_invoice_items on mr_assign_kot_printer.dept_name=mr_pos_hold_invoice_items.department and ref_reg_no=reg_nme where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"' and print_sts='0' group by printer ";
									ResultSet resultSet1 = statement.executeQuery(get_printer);
									while(resultSet1.next())
									{
										printer3=resultSet1.getString("printer");
										kot_num=kot_num+1;
										String printer1="select dept_name from "+company+".mr_assign_kot_printer where printer='"+resultSet1.getString("printer")+"' ";
										ResultSet resultSet11 = statement1.executeQuery(printer1);
										while(resultSet11.next())
										{

											String dept="select * from "+company+".mr_pos_hold_invoice_items where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"' and department='"+resultSet11.getString("dept_name")+"' and print_sts='0' ";
											ResultSet resultSet12 = statement2.executeQuery(dept);
											if(resultSet12.next())
											{
												String query7="update "+company+".mr_pos_hold_invoice_items set kot_number='"+kot_num+"' where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"' and department='"+resultSet11.getString("dept_name")+"' and print_sts='0' ";
												statement3.executeUpdate(query7);
											}
										}
										
										int b=JOptionPane.showConfirmDialog(frmRegisterNoS, "DO YOU WANT TO PRINT KOT RECEIPT","",JOptionPane.YES_NO_OPTION);
										if(b==0)
										{
											report_master.holded_invoice.report(textField_8.getText(), con, printer3,current_register,"",company,page_type,"",Integer.toString(kot_num));
										}
										
										
									}
								}
								
								if(actiontype.equals("1"))
								{
									if(print_commad.equals("0"))
									{
//										if(mode.equals("QUICK DINE IN"))
//										{
//											page_type="Hold Invoice";
//											report_master.holded_invoice.report(textField_8.getText(), con, printer,current_register,table_num,company,page_type,delivery_user,mode);
//										}
										page_type="Hold Invoice";
										int b=JOptionPane.showConfirmDialog(frmRegisterNoS, "DO YOU WANT TO PRINT HOLD RECEIPT","",JOptionPane.YES_NO_OPTION);
										if(b==0)
										{
											report_master.holded_invoice.report(textField_8.getText(), con, printer,current_register,"",company,page_type,"","");
											if(duplicate_holdinv.equals("1"))
											{
												report_master.holded_invoice.report(textField_8.getText(), con, printer,current_register,table_num,company,page_type,delivery_user,mode);
											}
										}
										
										
									}
									else
									{
										int b=JOptionPane.showConfirmDialog(frmRegisterNoS, "DO YOU WANT TO PRINT HOLD RECEIPT","",JOptionPane.YES_NO_OPTION);
										if(b==0)
										{
											report_master.holded_invoice.report(textField_8.getText(), con, printer,current_register,"",company,"Hold Invoice","","");
										}
									}
								}
								
								
							}
							
						}
						else
						{
							int b=JOptionPane.showConfirmDialog(frmRegisterNoS, "DO YOU WANT TO PRINT RECEIPT","",JOptionPane.YES_NO_OPTION);
							if(b==0)
							{
								report_master.holded_invoice.report(textField_8.getText(), con, printer,current_register,"",company,page_type,"","");
							}
						}
						
						String query7="update "+company+".mr_pos_hold_invoice_items set print_sts='1',print_cmd='1' where ref_receipt_num='"+receipt_no+"' and ref_reg_no='"+current_register+"' ";
						statement.executeUpdate(query7);
						
						hold_inv=false;
						page_type="Delivery";
						current_register=reg_nme;
						if(!change_hold_type.equals("1"))
						{
							JOptionPane.showMessageDialog(frmRegisterNoS, "Invoice Holded");
							posscreen.txtDisc.requestFocus();
						}
						
						sales_master.posscreen.get_register_name(company,  username, store_name,con,posreg);
						sales_master.posscreen.txtDisc.setText("");
						sales_master.posscreen.btnNewButton.setText("WALK IN");
						sales_master.posscreen.textField_6.setText("");
						sales_master.posscreen.textField_7.setText("0");
						sales_master.posscreen.lblNewLabel_2.setText("0%");
						sales_master.posscreen.label.setText("");
						sales_master.posscreen.label_1.setText("");
						sales_master.posscreen.label_2.setText("");
						sales_master.posscreen.textField_4.setText("0");
						sales_master.posscreen.textField_2.setText("0");
						sales_master.posscreen.textField.setText("0.00");
						sales_master.posscreen.textField_1.setText("0.00");
						sales_master.posscreen.textField_11.setText("0.00");
						sales_master.posscreen.lblNewLabel_1.setText("0");
						sales_master.posscreen.textField_3.setText("0.00");
						sales_master.posscreen.textField_9.setText("1");
						sales_master.posscreen.textField_12.setText("0");
						sales_master.posscreen.textField_5.setText("");
						sales_master.posscreen.model.setRowCount(0);
						if(gs.length>1)
						{
						sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
						sales_master.SecondDisplay.textField_7.setText("0");
						sales_master.SecondDisplay.textField_4.setText("0");
						sales_master.SecondDisplay.textField_2.setText("0");
						sales_master.SecondDisplay.textField.setText("0.00");
						sales_master.SecondDisplay.textField_1.setText("0.00");
						sales_master.SecondDisplay.textField_11.setText("0.00");
						sales_master.SecondDisplay.lblNewLabel_1.setText("0");
						sales_master.SecondDisplay.textField_3.setText("0.00");
						sales_master.SecondDisplay.textField_5.setText("");
						sales_master.SecondDisplay.model.setRowCount(0);
						}
						
						Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+posscreen.ready_msg1+">"+display_port+"&&ECHO "+posscreen.ready_msg2+">"+display_port+"\"");
						sales_master.posscreen.txtDisc.requestFocus();
					}
				}
				manager_name=username;
				get_user_validate_dt( company, username,store_name,current_register);
				txtDisc.requestFocus();
			}
			else
			{
				JOptionPane.showMessageDialog(frmRegisterNoS, "No Data Found on Table");
				txtDisc.requestFocus();
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
		
	}
	
	static void Kot_hold_inv(String company, String username,String store_name,String posreg,Connection con, String table_num, String version, String delivery_user, String mode, String holdreceipt, String actiontype, String page)
	{
		JFrame frame = frmRegisterNoS;

		if (page.equals("PAYCODE")) {
		    frame = paycode.frmMultiplePayCode;
		}
		
		
		try
		{
			Statement statement1 = con.createStatement();
			if(table.getRowCount()>0)
			{
				
				
				
				String chkbillsts="0";
				if(actiontype.equals("1"))
				{
					chkbillsts="1";
				}
				else
				{
					chkbillsts=checkbillstatus;
				}
				
				if(change_hold_type.equals("1"))
				{
					if(hold_inv==true)
					{
						
					}
					else
					{
						if(old_recipt.equals(""))
						{
							ResultSet resultSet11 = statement1.executeQuery("select * from "+company+".mr_pos_settlement_cash where receipt_no='"+holdreceipt+"' and reg_num='"+current_register+"' ");
							if(resultSet11.next())
							{
								ResultSet resultSet1 = statement1.executeQuery("select * from "+company+".mr_register_setup where register_number='"+current_register+"' ");
								if(resultSet1.next())
								{
									int nxt_num=Integer.parseInt(resultSet1.getString("next_num"))+1;
									int invnum=Integer.parseInt(resultSet1.getString("invoice_number"))+ nxt_num;
									String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+current_register+"' ";
									statement1.executeUpdate(update_receipt_num);
									
									textField_8.setText(Integer.toString(invnum));
								}
							}
							else
							{
								page_type="Kitchen KOT";
								hold_inv=true;
							}
						}
						
					}
					
				}
				else
				{
//					JLabel label = new JLabel("Do You Want To Hold This Invoice");
//					label.setFont(new Font("Arial", Font.BOLD, 18));
//					
					 
					int a=JOptionPane.showConfirmDialog(frame, "Do You Want To Hold This Invoice","WARNING",JOptionPane.YES_NO_OPTION);
					if(a==0)
					{
						hold_inv=true;
					}
				}
				
				if(hold_inv==true)
				{
					if(pause_kot_print.equals("1"))
					{
						page_type="Hold Invoice";
					}
					if(mode.equals(""))
					{
						mode="HOME DELIVERY";
					}
					String round=lblNewLabel_1.getText();
					String user=username;
					String receipt_no=holdreceipt;
					String total=textField_3.getText();
					String cust_name=btnNewButton.getText();
					String vat=textField_1.getText();
					String disc=textField_2.getText();
					String qty=textField_4.getText();
					double ttl=Double.parseDouble(total)-Double.parseDouble(round);
					
					Statement statement = con.createStatement();
					Statement statement2 = con.createStatement();
					Statement statement3 = con.createStatement();
		    		
					boolean stshold=false;
					if(old_recipt.equals(""))
					{
						ResultSet resultSet11 = statement1.executeQuery("select * from "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+current_register+"' ");
						if(resultSet11.next())
						{
							ResultSet resultSet1 = statement1.executeQuery("select * from "+company+".mr_register_setup where register_number='"+current_register+"' ");
							if(resultSet1.next())
							{
								int nxt_num=Integer.parseInt(resultSet1.getString("next_num"))+1;
								int invnum=Integer.parseInt(resultSet1.getString("invoice_number"))+ nxt_num;
								String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+current_register+"' ";
								statement1.executeUpdate(update_receipt_num);
								
								textField_8.setText(Integer.toString(invnum));
								receipt_no=Integer.toString(invnum);
								stshold=true;
							
							}
							
						}
						else
						{
							stshold=true;
						}
					}
					else
					{
						stshold=true;
					}
					if(stshold==true)
					{
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+current_register+"' ");
						if(!resultSet.next())
						{
							cal = Calendar.getInstance();
							String query="insert into "+company+".mr_pos_hold_invoice (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `total_qty`, store_id, `created_date`,cust_code,table_num,delivery_user,mode,cust_ph,Chkbill_Sts) values('"+current_register+"','"+receipt_no+"','"+user+"','"+times.format(cal.getTime())+"','"+ttl+"','"+cust_name+"','"+vat+"','"+disc+"','"+qty+"','"+store_name+"','"+formattedDate+"','"+textField_9.getText()+"','"+table_num+"','"+delivery_user+"','"+mode+"','"+posscreen.textField_12.getText()+"','"+chkbillsts+"') ";
							statement.executeUpdate(query);
							ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+current_register+"' ");
							if(resultSet1.next())
							{
								int nxt_num=Integer.parseInt(resultSet1.getString("next_num"))+1;
								String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+current_register+"' ";
								statement.executeUpdate(update_receipt_num);
							}
							for(int i=0;i<model.getRowCount();i++)
							{
								String itemname=model.getValueAt(i, 2).toString().replaceAll("<html>", "").replaceAll("</html>", "");
								String query4="insert into "+company+".mr_pos_hold_invoice_items ( `ref_receipt_num`,ref_reg_no, `barcode`, `item_desc`,item_code, `unit`, `unit_price`, `qty`, `total`, `vat`,discount, `created_date`,vat_type, `cost`, `sp`, `disc_type`, `commited_qty`, `vat_perc`,print_cmd,department,kot_number,comments_item,cf,print_sts,scale_sts) "
										+ "values ('"+receipt_no+"','"+current_register+"','"+model.getValueAt(i, 1).toString()+"','"+itemname+"','"+model.getValueAt(i, 13).toString()+"','"+model.getValueAt(i, 3).toString()+"','"+model.getValueAt(i,4).toString()+"','"+model.getValueAt(i, 5).toString()+"','"+model.getValueAt(i, 7).toString()+"','"+model.getValueAt(i, 8).toString()+"','"+model.getValueAt(i, 6).toString()+"','"+formattedDate+"','"+model.getValueAt(i, 10).toString()+"','"+model.getValueAt(i, 11).toString()+"','"+model.getValueAt(i, 12).toString()+"','"+model.getValueAt(i, 15).toString()+"','"+model.getValueAt(i, 14).toString()+"','"+model.getValueAt(i, 16).toString()+"','"+model.getValueAt(i, 17).toString()+"','"+model.getValueAt(i, 18).toString()+"','"+model.getValueAt(i, 19).toString()+"','"+model.getValueAt(i, 21).toString()+"','"+model.getValueAt(i, 22).toString()+"','"+model.getValueAt(i, 23).toString()+"','"+model.getValueAt(i, 24).toString()+"') ";
								statement.executeUpdate(query4);
							}
						}
						else
						{
							String query6="delete FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+current_register+"' ";
							statement.executeUpdate(query6);
							String query7="delete FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+receipt_no+"' and ref_reg_no='"+current_register+"' ";
							statement.executeUpdate(query7);
							cal = Calendar.getInstance();
							String query="insert into "+company+".mr_pos_hold_invoice (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `total_qty`, store_id, `created_date`,cust_code,table_num,delivery_user,mode,cust_ph,Chkbill_Sts) values('"+current_register+"','"+receipt_no+"','"+user+"','"+times.format(cal.getTime())+"','"+total+"','"+cust_name+"','"+vat+"','"+disc+"','"+qty+"','"+store_name+"','"+formattedDate+"','"+textField_9.getText()+"','"+table_num+"','"+delivery_user+"','"+mode+"','"+posscreen.textField_12.getText()+"','"+chkbillsts+"') ";
							statement.executeUpdate(query);
							
							for(int i=0;i<model.getRowCount();i++)
							{
								String itemname=model.getValueAt(i, 2).toString().replaceAll("<html>", "").replaceAll("</html>", "");
								String query4="insert into "+company+".mr_pos_hold_invoice_items ( `ref_receipt_num`,ref_reg_no, `barcode`, `item_desc`,item_code, `unit`, `unit_price`, `qty`, `total`, `vat`,discount, `created_date`,vat_type, `cost`, `sp`, `disc_type`, `commited_qty`, `vat_perc`,print_cmd,department,kot_number,comments_item,cf,print_sts,scale_sts) "
										+ "values ('"+receipt_no+"','"+current_register+"','"+model.getValueAt(i, 1).toString()+"','"+itemname+"','"+model.getValueAt(i, 13).toString()+"','"+model.getValueAt(i, 3).toString()+"','"+model.getValueAt(i,4).toString()+"','"+model.getValueAt(i, 5).toString()+"','"+model.getValueAt(i, 7).toString()+"','"+model.getValueAt(i, 8).toString()+"','"+model.getValueAt(i, 6).toString()+"','"+formattedDate+"','"+model.getValueAt(i, 10).toString()+"','"+model.getValueAt(i, 11).toString()+"','"+model.getValueAt(i, 12).toString()+"','"+model.getValueAt(i, 15).toString()+"','"+model.getValueAt(i, 14).toString()+"','"+model.getValueAt(i, 16).toString()+"','"+model.getValueAt(i, 17).toString()+"','"+model.getValueAt(i, 18).toString()+"','"+model.getValueAt(i, 19).toString()+"','"+model.getValueAt(i, 21).toString()+"','"+model.getValueAt(i, 22).toString()+"','"+model.getValueAt(i, 23).toString()+"','"+model.getValueAt(i, 24).toString()+"') ";
								statement.executeUpdate(query4);
							}
						}
						Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO INVOICE HOLDED..>"+display_port+"\"");
						//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO INVOICE HOLDED..>"+display_port+"\""});
						
						if(Mainpage.mediacontrol.equals("Y"))
						{
							AudioInputStream audioInputStream;
		    				File file = new File("C:/MasterRetails/media/"+holdInv);
		    				audioInputStream = AudioSystem.getAudioInputStream(file);
		    				Clip line;
		    				line = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioInputStream.getFormat()));
		    				line.open(audioInputStream);
		    				line.start();
		    				line.drain();
		    				audioInputStream.close();
						}
						
						
						
						if(change_hold_type.equals("1"))
						{
							if(page_type.equals("Hold Invoice"))
							{
								if(PauseHoldPrint.equals("0")){
									report_master.holded_invoice.report(textField_8.getText(), con, printer,current_register,"",company,page_type,"","");	
								}
								
							}
							else
							{
								String print_commad="0";
								if(hold_bill.equals("1"))
								{
									int kot_num=0;
									String sel_kot="select kot_number,print_cmd from "+company+".mr_pos_hold_invoice_items where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"'  order by kot_number desc limit 1 ";
									ResultSet resultSet3 = statement.executeQuery(sel_kot);
									if(resultSet3.next())
									{
										kot_num=Integer.parseInt(resultSet3.getString("kot_number"));
										print_commad=resultSet3.getString("print_cmd");
									}
									String dept="select department from "+company+".mr_pos_hold_invoice_items where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"' and print_sts='0'  group by department ";
									ResultSet resultSet1 = statement.executeQuery(dept);
									while(resultSet1.next())
									{
										kot_num=kot_num+1;
										String query7="update "+company+".mr_pos_hold_invoice_items set kot_number='"+kot_num+"' where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"' and department='"+resultSet1.getString("department")+"'  and print_sts='0' ";
										statement1.executeUpdate(query7);
										
										String printer_dept="select printer from "+company+".mr_assign_kot_printer where reg_nme='"+current_register+"' and dept_name='"+resultSet1.getString("department")+"' ";
										ResultSet resultSet2 = statement1.executeQuery(printer_dept);
										if(resultSet2.next())
										{
											printer2=resultSet2.getString("printer");
											int b=JOptionPane.showConfirmDialog(frame, "DO YOU WANT TO PRINT KOT RECEIPT","",JOptionPane.YES_NO_OPTION);
											if(b==0)
											{
												report_master.holded_invoice.report(textField_8.getText(), con, printer2,current_register,"",company,page_type,resultSet1.getString("department"),"");
											}
												
										}
										else
										{
											frmRegisterNoS.setAlwaysOnTop(true);
											JOptionPane.showMessageDialog(frame, "Printer Not Assigned For This Department");
											posscreen.txtDisc.requestFocus();
										}
										
									}
								}
								else
								{
									int kot_num=0;
									String sel_kot="select kot_number,print_cmd from "+company+".mr_pos_hold_invoice_items where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"'  order by kot_number desc limit 1 ";
									ResultSet resultSet3 = statement.executeQuery(sel_kot);
									if(resultSet3.next())
									{
										kot_num=Integer.parseInt(resultSet3.getString("kot_number"));
										print_commad=resultSet3.getString("print_cmd");
									}
									String printer3="";
									String get_printer="select printer from "+company+".mr_assign_kot_printer inner join "+company+".mr_pos_hold_invoice_items on mr_assign_kot_printer.dept_name=mr_pos_hold_invoice_items.department and ref_reg_no=reg_nme where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"' and print_sts='0' group by printer ";
									ResultSet resultSet1 = statement.executeQuery(get_printer);
									while(resultSet1.next())
									{
										printer3=resultSet1.getString("printer");
										kot_num=kot_num+1;
										String printer1="select dept_name from "+company+".mr_assign_kot_printer where printer='"+resultSet1.getString("printer")+"' ";
										ResultSet resultSet11 = statement1.executeQuery(printer1);
										while(resultSet11.next())
										{

											String dept="select * from "+company+".mr_pos_hold_invoice_items where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"' and department='"+resultSet11.getString("dept_name")+"' and print_sts='0' ";
											ResultSet resultSet12 = statement2.executeQuery(dept);
											if(resultSet12.next())
											{
												String query7="update "+company+".mr_pos_hold_invoice_items set kot_number='"+kot_num+"' where ref_reg_no='"+current_register+"' and  ref_receipt_num='"+receipt_no+"' and department='"+resultSet11.getString("dept_name")+"' and print_sts='0' ";
												statement3.executeUpdate(query7);
											}
										}
										
										int b=JOptionPane.showConfirmDialog(frame, "DO YOU WANT TO PRINT KOT RECEIPT","",JOptionPane.YES_NO_OPTION);
										if(b==0)
										{
											report_master.holded_invoice.report(textField_8.getText(), con, printer3,current_register,"",company,page_type,"",Integer.toString(kot_num));
										}
										
										
									}
								}
								
								if(actiontype.equals("1"))
								{
									if(print_commad.equals("0"))
									{
//										if(mode.equals("QUICK DINE IN"))
//										{
//											page_type="Hold Invoice";
//											report_master.holded_invoice.report(textField_8.getText(), con, printer,current_register,table_num,company,page_type,delivery_user,mode);
//										}
										page_type="Hold Invoice";
										int b=JOptionPane.showConfirmDialog(frame, "DO YOU WANT TO PRINT HOLD RECEIPT","",JOptionPane.YES_NO_OPTION);
										if(b==0)
										{
											report_master.holded_invoice.report(textField_8.getText(), con, printer,current_register,"",company,page_type,"","");
											if(duplicate_holdinv.equals("1"))
											{
												report_master.holded_invoice.report(textField_8.getText(), con, printer,current_register,table_num,company,page_type,delivery_user,mode);
											}
										}
										
										
									}
									else
									{
										int b=JOptionPane.showConfirmDialog(frame, "DO YOU WANT TO PRINT HOLD RECEIPT","",JOptionPane.YES_NO_OPTION);
										if(b==0)
										{
											report_master.holded_invoice.report(textField_8.getText(), con, printer,current_register,"",company,"Hold Invoice","","");
										}
									}
								}
								
								
							}
							
						}
						else
						{
							int b=JOptionPane.showConfirmDialog(frame, "DO YOU WANT TO PRINT RECEIPT","",JOptionPane.YES_NO_OPTION);
							if(b==0)
							{
								report_master.holded_invoice.report(textField_8.getText(), con, printer,current_register,"",company,page_type,"","");
							}
						}
						
						String query7="update "+company+".mr_pos_hold_invoice_items set print_sts='1',print_cmd='1' where ref_receipt_num='"+receipt_no+"' and ref_reg_no='"+current_register+"' ";
						statement.executeUpdate(query7);
						
						hold_inv=false;
						page_type="Delivery";
						current_register=reg_nme;
						if(!change_hold_type.equals("1"))
						{
							JOptionPane.showMessageDialog(frame, "Invoice Holded");
							posscreen.txtDisc.requestFocus();
						}
						
					}
				}
				manager_name=username;
				get_user_validate_dt( company, username,store_name,current_register);
				txtDisc.requestFocus();
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Data Found on Table");
				txtDisc.requestFocus();
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frame, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
		
	}
	
	
	private static void unhold_inv(String company, String username,String store_name,String posreg,String version)
	{
		if(table.getRowCount()==0)
		{
			try {
				//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
				new unhold(frmRegisterNoS,company,username,store_name,reg_nme, version);
				manager_name=username;
				Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+posscreen.ready_msg1+">"+display_port+"&&ECHO "+posscreen.ready_msg2+">"+display_port+"\"");
				//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO "+posscreen.ready_msg1+""+posscreen.ready_msg2+">"+display_port+"\""});
				get_user_validate_dt( company, username,store_name,posreg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(frmRegisterNoS, "Table Not Empty , Settle Bill First");
			posscreen.txtDisc.requestFocus();
		}
	}
	private static void dup_bill(String company, String username,String store_name,String posreg,String version)
	{
		if(table.getRowCount()==0)
		{
			try {
				btnduplicateBill.setEnabled(false);
				//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
				manager_name=username;
				//new duplicate_invoice(company,username,store_name,reg_nme,printer, version,Integer.toString(decimalPlaces));
				//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO "+posscreen.ready_msg1+""+posscreen.ready_msg2+">"+display_port+"\""});
				Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+posscreen.ready_msg1+">"+display_port+"&&ECHO "+posscreen.ready_msg2+">"+display_port+"\"");
				//get_user_validate_dt( company, username,store_name,posreg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(frmRegisterNoS, "Table Not Empty , First Settle Bill");
			posscreen.txtDisc.requestFocus();
			btnduplicateBill.setEnabled(true);
			//get_user_validate_dt( company, username,store_name,posreg);
			
		}
		
	}
	private static void delete(String company, String username,String store_name,String posreg, String version)
	{
		try
		{
			//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
			boolean status=false;
			int line=table.getSelectedRow();
			if(checkbillstatus.equals("1"))
			{
				if(checkprint_invoice_change==false && old_recipt!="")
				{
					status=false;
					new sales_master.manager( company, username, store_name, reg_nme, version);
					txtDisc.requestFocus();
				}
				else
				{
					status=true;
				}
			}
			else
			{
				
				if(ChangeHoldQty==false && old_recipt!="")
				{
					status=false;
					new sales_master.manager( company, username, store_name, reg_nme, version);
					txtDisc.requestFocus();
				}
				else
				{
					status=true;
				}
			}
			if(status==true)
			{
				if(line==-1)
				{
					JOptionPane.showMessageDialog(frmRegisterNoS, "Select the row, to delete ");
					posscreen.txtDisc.requestFocus();
					Connection con=Dbsetup.local_server.getConnection();
					sales_master.posscreen.get_register_name(company,  username, store_name,con,posreg);
					sales_master.posscreen.txtDisc.setText("");
					sales_master.posscreen.btnNewButton.setText("WALK IN");
					sales_master.posscreen.textField_6.setText("");
					sales_master.posscreen.textField_7.setText("0");
					sales_master.posscreen.lblNewLabel_2.setText("0%");
					sales_master.posscreen.label.setText("");
					sales_master.posscreen.label_1.setText("");
					sales_master.posscreen.label_2.setText("");
					sales_master.posscreen.textField_4.setText("0");
					sales_master.posscreen.textField_2.setText("0");
					sales_master.posscreen.textField.setText("0.00");
					sales_master.posscreen.textField_1.setText("0.00");
					sales_master.posscreen.textField_11.setText("0.00");
					sales_master.posscreen.lblNewLabel_1.setText("0");
					sales_master.posscreen.textField_3.setText("0.00");
					sales_master.posscreen.textField_9.setText("1");
					sales_master.posscreen.textField_12.setText("0");
					sales_master.posscreen.textField_5.setText("");
					sales_master.posscreen.model.setRowCount(0);
					if(gs.length>1)
					{
					sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
					sales_master.SecondDisplay.textField_7.setText("0");
					sales_master.SecondDisplay.textField_4.setText("0");
					sales_master.SecondDisplay.textField_2.setText("0");
					sales_master.SecondDisplay.textField.setText("0.00");
					sales_master.SecondDisplay.textField_1.setText("0.00");
					sales_master.SecondDisplay.textField_11.setText("0.00");
					sales_master.SecondDisplay.lblNewLabel_1.setText("0");
					sales_master.SecondDisplay.textField_3.setText("0.00");
					sales_master.SecondDisplay.textField_5.setText("");
					sales_master.SecondDisplay.model.setRowCount(0);
					}
				}
				else
				{
					model.removeRow(line);
					if(gs.length>1)
					{
					SecondDisplay.model.removeRow(line);
					}
					if(table.getRowCount()!=0)
					{
						table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
						table.addRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
						if(gs.length>1)
						{
						SecondDisplay.table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
						SecondDisplay.table.addRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
						}
					}
					int count=table.getRowCount();
					if(count==0)
					{
						if(!old_recipt.equals(""))
						{
							JOptionPane.showMessageDialog(frmRegisterNoS, "Invoice Number is Restored");
							posscreen.txtDisc.requestFocus();
							Connection con=Dbsetup.local_server.getConnection();
							sales_master.posscreen.get_register_name(company,  username, store_name,con,posreg);
							sales_master.posscreen.txtDisc.setText("");
							sales_master.posscreen.btnNewButton.setText("WALK IN");
							sales_master.posscreen.textField_6.setText("");
							sales_master.posscreen.textField_7.setText("0");
							sales_master.posscreen.lblNewLabel_2.setText("0%");
							sales_master.posscreen.textField_4.setText("0");
							sales_master.posscreen.textField_2.setText("0");
							sales_master.posscreen.textField.setText("0.00");
							sales_master.posscreen.textField_1.setText("0.00");
							sales_master.posscreen.textField_11.setText("0.00");
							sales_master.posscreen.lblNewLabel_1.setText("0");
							sales_master.posscreen.textField_3.setText("0.00");
							sales_master.posscreen.textField_9.setText("1");
							sales_master.posscreen.textField_12.setText("0");
							sales_master.posscreen.textField_5.setText("");
							sales_master.posscreen.label.setText("");
							sales_master.posscreen.label_1.setText("");
							sales_master.posscreen.label_2.setText("");
							sales_master.posscreen.model.setRowCount(0);
							if(gs.length>1)
							{
							sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
							sales_master.SecondDisplay.textField_7.setText("0");
							sales_master.SecondDisplay.textField_4.setText("0");
							sales_master.SecondDisplay.textField_2.setText("0");
							sales_master.SecondDisplay.textField.setText("0.00");
							sales_master.SecondDisplay.textField_1.setText("0.00");
							sales_master.SecondDisplay.textField_11.setText("0.00");
							sales_master.SecondDisplay.lblNewLabel_1.setText("0");
							sales_master.SecondDisplay.textField_3.setText("0.00");
							sales_master.SecondDisplay.textField_5.setText("");
							sales_master.SecondDisplay.model.setRowCount(0);
							}
						}
						else
						{
							Connection con=Dbsetup.local_server.getConnection();
							sales_master.posscreen.get_register_name(company,  username, store_name,con,posreg);
							sales_master.posscreen.txtDisc.setText("");
							sales_master.posscreen.btnNewButton.setText("WALK IN");
							sales_master.posscreen.textField_6.setText("");
							sales_master.posscreen.textField_7.setText("0");
							sales_master.posscreen.lblNewLabel_2.setText("0%");
							sales_master.posscreen.textField_4.setText("0");
							sales_master.posscreen.textField_2.setText("0");
							sales_master.posscreen.textField.setText("0.00");
							sales_master.posscreen.textField_1.setText("0.00");
							sales_master.posscreen.textField_11.setText("0.00");
							sales_master.posscreen.lblNewLabel_1.setText("0");
							sales_master.posscreen.textField_3.setText("0.00");
							sales_master.posscreen.textField_9.setText("1");
							sales_master.posscreen.textField_12.setText("0");
							sales_master.posscreen.textField_5.setText("");
							sales_master.posscreen.label.setText("");
							sales_master.posscreen.label_1.setText("");
							sales_master.posscreen.label_2.setText("");
							sales_master.posscreen.model.setRowCount(0);
							if(gs.length>1)
							{
							sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
							sales_master.SecondDisplay.textField_7.setText("0");
							sales_master.SecondDisplay.textField_4.setText("0");
							sales_master.SecondDisplay.textField_2.setText("0");
							sales_master.SecondDisplay.textField.setText("0.00");
							sales_master.SecondDisplay.textField_1.setText("0.00");
							sales_master.SecondDisplay.textField_11.setText("0.00");
							sales_master.SecondDisplay.lblNewLabel_1.setText("0");
							sales_master.SecondDisplay.textField_3.setText("0.00");
							sales_master.SecondDisplay.textField_5.setText("");
							sales_master.SecondDisplay.model.setRowCount(0);
							}
						}
					}
					double amount=0.0;
					double vat_ttl=0.0;
					double total_price=0.0;
					double ttl_qty=0;
					//double disc_perc=0.0;
					//double disc_amt=0;
					double ttlamt=0;
					double ttl_bf_disc=0;
					double ttl_af_disc=0;
					for(int i=0;i<count;i++)
					{
						table.setValueAt(i+1, i, 0);
						//model.setValueAt("0", i, 23);
						String vattype=table.getModel().getValueAt(i, 10).toString();
						double unitprice=Double.parseDouble(table.getValueAt(i, 4).toString());
						double qty_line=Double.parseDouble(table.getValueAt(i, 5).toString());
						ttl_qty+=Double.parseDouble(table.getModel().getValueAt(i, 5).toString());
						String total_val=table.getModel().getValueAt(i, 7).toString();
						ttlamt+=Double.parseDouble(total_val);
						String ttl_vat=table.getModel().getValueAt(i, 8).toString();
						vat_ttl+=Double.parseDouble(ttl_vat);
						total_price+= Double.parseDouble(total_val)+Double.parseDouble(ttl_vat);
						double totl=Double.parseDouble(table.getValueAt(i, 7).toString());
						double vt=Double.parseDouble(table.getValueAt(i, 8).toString());
						
						if(vattype.equals("INC"))
						{
							ttl_af_disc+=totl+vt;
						}
						else
						{
							ttl_af_disc+=totl;
						}
						
						String ttl_before=df2.format(unitprice*qty_line);
						
						ttl_bf_disc+=Double.parseDouble(ttl_before);
					}
					String ttl_after=df2.format(ttl_af_disc);
					String ttl_disc_bf=df2.format(ttl_bf_disc);
					sales_master.posscreen.textField_11.setText(ttl_disc_bf);
					if(gs.length>1)
					{
					sales_master.SecondDisplay.textField_11.setText(ttl_disc_bf);
					}
					double ttl_disc_perc=((Double.parseDouble(ttl_disc_bf)-Double.parseDouble(ttl_after))*100/Double.parseDouble(ttl_disc_bf));
					if(Double.isNaN(ttl_disc_perc))
					{
						ttl_disc_perc=0;
					}
					double ttl_disc_amt=Double.parseDouble(ttl_disc_bf)-Double.parseDouble(ttl_after);
					amount=ttlamt;
					
					double checksum=0;
		    		if(allow_roundoff.equals("1"))
		    		{
		    			String nettotal=df2.format(total_price);
			    		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
			    		int number_decimal=Integer.parseInt(splitnumber);
			    		
			    		if(number_decimal>0 && number_decimal<13 )
			    		{
			    			checksum=0-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>12 && number_decimal<26 )
			    		{
			    			checksum=25-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>25 && number_decimal<38 )
			    		{
			    			checksum=25-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>37 && number_decimal<51 )
			    		{
			    			checksum=50-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>50 && number_decimal<63 )
			    		{
			    			checksum=50-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>62 && number_decimal<76 )
			    		{
			    			checksum=75-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>75 && number_decimal<88 )
			    		{
			    			checksum=75-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
			    		if(number_decimal>87 && number_decimal<101 )
			    		{
			    			checksum=100-number_decimal;
			    			total_price=Double.parseDouble(nettotal)+(checksum/100);
			    		}
		    		}
					lblNewLabel_1.setText(Double.toString(checksum/100));
					if(gs.length>1)
					{
					SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
					}
		    		String[] tokens = null;
		    		int row =table.getSelectedRow();
		    		int price_space;
		    		int print_price;
		    		String get_name;
		    		String name;
		    		double price;
		    		double vat;
		    		int length =0;
		    		double ttl_amt=0;
		    		if(count==0)
		    		{
		    			name=".";
		    			price=0;
		    			length = 1;
		    			vat=0;
		    		}
		    		else
		    		{
		    			length = table.getModel().getValueAt(row, 2).toString().length();
		    			name=table.getModel().getValueAt(row, 2).toString();
		    			price=Double.parseDouble(table.getModel().getValueAt(row, 7).toString());
		    			vat=Double.parseDouble(table.getModel().getValueAt(row, 8).toString());
		    			ttl_amt=price+vat;
		    		}
		    		if(length>12)
		    		{
		    			int size = 12;
		    			tokens = name.split("(?<=\\G.{" + size + "})");
		    			String ttl_st=df2.format(ttl_amt);
		    			String space="";
		    			print_price=8-ttl_st.length();
		    			for(int i=0; i<print_price;i++)
		    			{
		    				space+=" ";
		    			}
		    			get_name=tokens[0]+""+space+""+df2.format(ttl_amt);
		    		}
		    		else
		    		{
		    			price_space=12-length;
		    			String space="";
		    			for(int i=0;i<price_space;i++)
		    			{
		    				space+=" ";
		    			}
		    			String ttl_st=df2.format(ttl_amt);
		    			print_price=8-ttl_st.length();
		    			for(int i=0; i<print_price;i++)
		    			{
		    				space+=" ";
		    			}
		    			get_name=name+""+space+""+df2.format(ttl_amt);
		    		}
		    		
		    		if(Mainpage.mediacontrol.equals("Y"))
					{
		    			AudioInputStream audioInputStream;
						File file = new File("C:/MasterRetails/media/"+deleteLine);
						audioInputStream = AudioSystem.getAudioInputStream(file);
						Clip line1;
						line1 = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioInputStream.getFormat()));
						line1.open(audioInputStream);
						line1.start();
						line1.drain();
						audioInputStream.close();
					}
		    		
		    		
		    		String gap="";
		    		String grnd_ttl=df2.format(total_price);
					int grand_price=15-grnd_ttl.length();
					for(int i=0; i<grand_price;i++)
					{
						gap+=" ";
					}
					
					if(count==0)
	    			{
						//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
	    				Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+posscreen.ready_msg1+">"+display_port+"&&ECHO "+posscreen.ready_msg2+">"+display_port+"\"");
	    			}
	    			else
	    			{
	    				//String display_name=get_name+"TOTAL"+gap+""+df2.format(total_price)+"";
	    				//Runtime.getRuntime().exec(new String[] {"cmd","/c", "echo "+display_name+">"+display_port+"\""});
	    				
	    				Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+get_name+">"+display_port+"&&ECHO TOTAL"+gap+""+df2.format(total_price)+">"+display_port+"\"");
	    				//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\" && cmd","/c", "echo "+display_name+">"+display_port+"\""} );
	    			}
		    		
		    		
		    		textField.setText(df2.format(amount));
					textField_3.setText(df2.format(total_price));
					textField_1.setText(df2.format(vat_ttl));
					textField_4.setText(df2.format(ttl_qty));
					textField_2.setText(df2.format(ttl_disc_amt));
					textField_5.setText(df2.format(ttl_disc_perc)+"%");
					if(gs.length>1)
					{
					SecondDisplay.textField.setText(df2.format(amount));
					SecondDisplay.textField_3.setText(df2.format(total_price));
					SecondDisplay.textField_1.setText(df2.format(vat_ttl));
					SecondDisplay.textField_4.setText(df2.format(ttl_qty));
					SecondDisplay.textField_2.setText(df2.format(ttl_disc_amt));
					SecondDisplay.textField_5.setText(df2.format(ttl_disc_perc)+"%");
					}
					txtDisc.setText("");
					txtDisc.requestFocus();
					
					manager_name=username;
					get_user_validate_dt( company, username,store_name,posreg);
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frmRegisterNoS, e.getMessage());
			posscreen.txtDisc.requestFocus();
		}
		
	}
	private static void price_enqury(String db_name,String company, String username, String store_name,String version,Connection con,String language) throws JSONException, SQLException
	{
		new price_enquiry(db_name,company,username,store_name, version,con,language);
	}
}