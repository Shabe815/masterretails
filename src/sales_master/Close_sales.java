package sales_master;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import CafePro.Mainpage;

import com.toedter.calendar.JDateChooser;

public class Close_sales {

	static JFrame frame;
	private JTextField textField_12;
	private boolean dot = false;
	private JTable table;
	static DefaultTableModel model;
	static JTextField textField;
	private JLabel reg_num;
	private JLabel label_3;
	private JLabel label_5;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_2;
	private String printer;
	private JDateChooser dateChooser;
	private JProgressBar progressBar;
	private boolean endofsale=false;
	public static String display_port;
	public static String denominationPrint;
	public static String not_ready_msg1;
	public static String not_ready_msg2;
	private static String change_hold_type="0";
	public static String assembly_of_item="0";
	public static String register_all_invoice="0";
	public static String registerAsWaiter="0";
	public static String DonotPerformEod="0";
	public static JLabel lblNewLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Close_sales window = new Close_sales("mr_pro","S01","SHABEER", null, "S0101","2","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param language 
	 */
	public Close_sales(String company, String store_name, String username,String version,String reg_nme,String decimal, String language) {
		initialize(company,store_name,username, version,reg_nme,decimal,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store_name 
	 * @param host_name 
	 * @param company 
	 * @param language 
	 */
	private void initialize(final String company,final String store_name,final String username,final String version,final String reg_nme,final String decimal, String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 792, 720);
		//frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				Mainpage.btnCloseSales.setEnabled(true);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		Date date1 = new Date();
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 776, 682);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 0, 205));
		progressBar.setBackground(new Color(192, 192, 192));
		progressBar.setStringPainted(true);
		//progressBar.setBounds(12, 198, 622, 31);
		//frame.getContentPane().add(progressBar);
		progressBar.setBounds(0, 46, 416, 31);
		progressBar.setVisible(false);
		panel.add(progressBar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(397, 11, 1, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(415, 77, 1, 605);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 77, 776, 2);
		panel.add(separator_2);
		
		JButton btnDrawer = new JButton("DRAWER");
		if(language.equals("Arabic"))
		{
			btnDrawer.setText("œ—Ã «·‰ﬁÊœ");
		}
		btnDrawer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				byte[] open = {27,112,48,55,121};
		        PrintService pservice = 
		        PrintServiceLookup.lookupDefaultPrintService(); 
		        DocPrintJob job = pservice.createPrintJob();
		        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		        Doc doc = new SimpleDoc(open,flavor,null);
		        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		        try {
		            job.print(doc, aset);
		        } catch (PrintException ex) {
		            System.out.println(ex.getMessage());
		        }
			}
		});
		btnDrawer.setBounds(415, 11, 105, 43);
		panel.add(btnDrawer);
		
		JButton btnOk = new JButton("OK");
		if(language.equals("Arabic"))
		{
			btnOk.setText("‰⁄„");
		}
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				button_ok_click(e1,company,  reg_nme,  username,  store_name,  decimal);
			}
		});
		btnOk.setBounds(540, 11, 105, 43);
		panel.add(btnOk);
		
		JButton btnExit = new JButton("EXIT");
		if(language.equals("Arabic"))
		{
			btnExit.setText("„Œ—Ã");
		}
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				Mainpage.btnCloseSales.setEnabled(true);
			}
		});
		btnExit.setBounds(670, 11, 105, 43);
		panel.add(btnExit);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(415, 299, 361, 2);
		panel.add(separator_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 77, 416, 605);
		panel.add(scrollPane);
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
				if(column==1)
		        {
		        	return true;  
		        }
				if(column==3)
		        {
		        	return true;  
		        }
				
		       return false;
		    }
			public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 3:
                        return Double.class;
                    default:
                        return String.class;
                }
            }
			
		};
		table = new JTable(model);
		
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(40);
			if(language.equals("Arabic"))
			{
				model.addColumn("«·—ﬁ„ «· ”·”·Ì:"); 
			    model.addColumn("—„“ «·œ›⁄"); 
				model.addColumn("Ê’›"); 
				model.addColumn("«·„»·€/ «·ﬂ„Ì…"); 
			}
			else
			{
				model.addColumn("Sl No:"); 
			    model.addColumn("Paycode"); 
				model.addColumn("Description"); 
				model.addColumn("Amount/ Qty"); 
			}
		    
		
	
			int a=table.getColumnCount();
			for(int i=0;i<a;i++)
			{
				TableColumn column=table.getColumnModel().getColumn(i);
				if(i==0){
					column.setPreferredWidth(15);
				}
				if(i==3){
					column.setPreferredWidth(25);
				}
				if(i==4){
					column.setPreferredWidth(10);
				}
				if(i==5){
					column.setPreferredWidth(10);
				}
			}
			table.getColumnModel().getColumn(1).setCellRenderer(new ClientsTableButtonRenderer());
			table.getColumnModel().getColumn(1).setCellEditor(new ClientsTableRenderer(new JCheckBox()));
			    
			
		    scrollPane.setViewportView(table);
		    
		    JPanel panel_1 = new JPanel();
		    panel_1.setLayout(null);
		    panel_1.setBackground(Color.GRAY);
		    panel_1.setBounds(477, 312, 196, 370);
		    panel.add(panel_1);
		    
		    JButton button_12 = new JButton("1");
			button_12.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("1");
				}
			});
			button_12.setFont(new Font("Arial", Font.BOLD, 30));
			button_12.setBounds(3, 53, 60, 60);
			panel_1.add(button_12);
			
			JButton button_13 = new JButton("2");
			button_13.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("2");
				}
			});
			button_13.setFont(new Font("Arial", Font.BOLD, 30));
			button_13.setBounds(66, 53, 60, 60);
			panel_1.add(button_13);
			
			JButton button_14 = new JButton("3");
			button_14.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("3");
				}
			});
			button_14.setFont(new Font("Arial", Font.BOLD, 30));
			button_14.setBounds(129, 53, 60, 60);
			panel_1.add(button_14);
			
			JButton button_15 = new JButton("4");
			button_15.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("4");
				}
			});
			button_15.setFont(new Font("Arial", Font.BOLD, 30));
			button_15.setBounds(3, 116, 60, 60);
			panel_1.add(button_15);
			
			JButton button_16 = new JButton("5");
			button_16.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("5");
				}
			});
			button_16.setFont(new Font("Arial", Font.BOLD, 30));
			button_16.setBounds(66, 116, 60, 60);
			panel_1.add(button_16);
			
			JButton button_17 = new JButton("6");
			button_17.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("6");
				}
			});
			button_17.setFont(new Font("Arial", Font.BOLD, 30));
			button_17.setBounds(129, 116, 60, 60);
			panel_1.add(button_17);
			
			JButton button_18 = new JButton("7");
			button_18.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("7");
				}
			});
			button_18.setFont(new Font("Arial", Font.BOLD, 30));
			button_18.setBounds(3, 179, 60, 60);
			panel_1.add(button_18);
			
			JButton button_19 = new JButton("8");
			button_19.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("8");
				}
			});
			button_19.setFont(new Font("Arial", Font.BOLD, 30));
			button_19.setBounds(66, 179, 60, 60);
			panel_1.add(button_19);
			
			JButton button_20 = new JButton("9");
			button_20.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("9");
				}
			});
			button_20.setFont(new Font("Arial", Font.BOLD, 30));
			button_20.setBounds(129, 179, 60, 60);
			panel_1.add(button_20);
			
			JButton button_21 = new JButton("0");
			button_21.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("0");
				}
			});
			button_21.setFont(new Font("Arial", Font.BOLD, 30));
			button_21.setBounds(3, 242, 60, 60);
			panel_1.add(button_21);
			
			JButton button_22 = new JButton("<<");
			button_22.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!textField_12.getText().equals(""))
					{
						textField_12.setText(textField_12.getText().substring(0, textField_12.getText().length() - 1));
					}
				}
			});
			button_22.setFont(new Font("Arial", Font.BOLD, 20));
			button_22.setBounds(66, 305, 60, 60);
			panel_1.add(button_22);
			
			JButton button_23 = new JButton("C");
			button_23.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					textField_12.setText("");
				}
			});
			button_23.setFont(new Font("Arial", Font.BOLD, 30));
			button_23.setBounds(3, 305, 60, 60);
			panel_1.add(button_23);
			
			
			textField_12 = new JTextField("");
			textField_12.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char vChar = e.getKeyChar();
				    if (textField_12.getText().equals(""))
				        dot = false;
				    if (dot == false){
				        if (vChar == '.') dot = true;
				        else if (!(Character.isDigit(vChar)
				                || (vChar == KeyEvent.VK_BACK_SPACE)
				                || (vChar == KeyEvent.VK_DELETE))) {
				                e.consume();
				        }
				    } else {
				        if (!(Character.isDigit(vChar)
				                || (vChar == KeyEvent.VK_BACK_SPACE)
				                || (vChar == KeyEvent.VK_DELETE))) {
				                e.consume();
				        }
				    }
				}
			});
			textField_12.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLUE, null, null, null));
			textField_12.setHorizontalAlignment(SwingConstants.RIGHT);
			textField_12.setForeground(Color.DARK_GRAY);
			textField_12.setFont(new Font("Tahoma", Font.PLAIN, 40));
			textField_12.setColumns(10);
			textField_12.setBackground(new Color(176, 224, 230));
			textField_12.setBounds(0, 0, 189, 50);
			panel_1.add(textField_12);
			
			JButton button = new JButton("00");
			button.setFont(new Font("Arial", Font.BOLD, 24));
			button.setBounds(66, 242, 60, 60);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("00");
				}
			});
			panel_1.add(button);
			
			JButton button_1 = new JButton("000");
			button_1.setFont(new Font("Arial", Font.BOLD, 15));
			button_1.setBounds(129, 242, 60, 60);
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("000");
				}
			});
			panel_1.add(button_1);
			
			JButton button_2 = new JButton(".");
			button_2.setFont(new Font("Arial", Font.BOLD, 30));
			button_2.setBounds(129, 305, 60, 60);
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn(".");
				}
			});
			panel_1.add(button_2);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(425, 81, 341, 211);
			panel_2.setBackground(new Color(243, 229, 216));
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel label = new JLabel("Remarks :");
			if(language.equals("Arabic"))
			{
				label.setText("„·«ÕŸ«  :");
			}
			label.setBounds(0, 181, 113, 22);
			panel_2.add(label);
			
			JLabel label_1 = new JLabel("Close Rept no. :");
			if(language.equals("Arabic"))
			{
				label_1.setText("≈€·«ﬁ —ﬁ„ «·≈Ì’«· :");
			}
			label_1.setBounds(0, 94, 113, 22);
			panel_2.add(label_1);
			
			JLabel label_2 = new JLabel("Open Rept no. :");
			if(language.equals("Arabic"))
			{
				label_2.setText("—ﬁ„ «·≈Ì’«· «·„› ÊÕ:");
			}
			label_2.setBounds(0, 65, 113, 22);
			panel_2.add(label_2);
			
			JLabel lblStore = new JLabel("Store :");
			if(language.equals("Arabic"))
			{
				lblStore.setText("„Õ· :");
			}
			lblStore.setBounds(201, 39, 48, 22);
			panel_2.add(lblStore);
			
			JLabel label_4 = new JLabel("User/Cashier :");
			if(language.equals("Arabic"))
			{
				label_4.setText("«·„” Œœ„/√„Ì‰ «·’‰œÊﬁ :");
			}
			label_4.setBounds(0, 36, 90, 22);
			panel_2.add(label_4);
			
			JLabel lblDate = new JLabel("Date :");
			if(language.equals("Arabic"))
			{
				lblDate.setText(" «—ÌŒ :");
			}
			lblDate.setBounds(201, 7, 40, 22);
			panel_2.add(lblDate);
			
			JLabel label_6 = new JLabel("Register Number :");
			if(language.equals("Arabic"))
			{
				label_6.setText("—ﬁ„ «· ”ÃÌ· :");
			}
			label_6.setBounds(0, 7, 113, 22);
			panel_2.add(label_6);
			
			reg_num = new JLabel(reg_nme);
			reg_num.setBounds(110, 7, 79, 22);
			panel_2.add(reg_num);
			
			JLabel lblAdmin = new JLabel(username);
			lblAdmin.setBounds(110, 36, 79, 22);
			panel_2.add(lblAdmin);
			
			JLabel lblS = new JLabel(store_name);
			lblS.setBounds(246, 42, 68, 16);
			panel_2.add(lblS);
			
			JLabel lblStatus = new JLabel("status");
			if(language.equals("Arabic"))
			{
				lblStatus.setText("Õ«·…");
			}
			lblStatus.setBounds(0, 152, 56, 22);
			panel_2.add(lblStatus);
			
			JLabel lblComputer = new JLabel("computer");
			if(language.equals("Arabic"))
			{
				lblComputer.setText("Õ«”Ê»");
			}
			lblComputer.setBounds(0, 123, 56, 22);
			panel_2.add(lblComputer);
			
			JLabel lblnoofInvoice = new JLabel("#of invoice : ");
			if(language.equals("Arabic"))
			{
				lblnoofInvoice.setText("⁄œœ «·›Ê« Ì— :");
			}
			lblnoofInvoice.setBounds(201, 152, 77, 22);
			panel_2.add(lblnoofInvoice);
			
			label_3 = new JLabel();
			label_3.setBounds(110, 65, 212, 22);
			panel_2.add(label_3);
			
			label_5 = new JLabel();
			label_5.setBounds(110, 94, 212, 22);
			panel_2.add(label_5);
			
			InetAddress addr = null;
		    try {
				addr = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    String sys_name = addr.getHostName();
			
			lblNewLabel_2 = new JLabel(sys_name);
			lblNewLabel_2.setBounds(110, 123, 204, 22);
			panel_2.add(lblNewLabel_2);
			
			textField = new JTextField();
			textField.setBounds(110, 181, 221, 22);
			panel_2.add(textField);
			textField.setColumns(10);
			
			JLabel lblNewLabel_3 = new JLabel("OPEN");
			lblNewLabel_3.setBounds(110, 152, 56, 22);
			panel_2.add(lblNewLabel_3);
			
			lblNewLabel_4 = new JLabel();
			lblNewLabel_4.setBounds(276, 152, 41, 22);
			panel_2.add(lblNewLabel_4);
			
			dateChooser = new JDateChooser();
			dateChooser.setDateFormatString("dd/MM/yyyy");
			dateChooser.setDate(date1);
			dateChooser.setBounds(90, 24, 188, 16);
			dateChooser.getCalendarButton().addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {
								
								Date date = new Date();
								dateChooser.getJCalendar().setMaxSelectableDate(date);
							
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Calendar cal = Calendar.getInstance();
								cal.add(Calendar.DAY_OF_MONTH, -1);  
								String newDate = sdf.format(cal.getTime());  
								Date exp_date=new SimpleDateFormat("yyyy-MM-dd").parse(newDate);
								
								dateChooser.getJCalendar().setMinSelectableDate(exp_date);
								dateChooser.setDate(date);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
			dateChooser.setBounds(237, 7, 100, 22);
			panel_2.add(dateChooser);
			
			JLabel lblCloseSales = new JLabel("Close Sales");
			if(language.equals("Arabic"))
			{
				lblCloseSales.setText("≈€·«ﬁ «·„»Ì⁄« ");
			}
			lblCloseSales.setFont(new Font("Monospaced", Font.BOLD, 25));
			lblCloseSales.setBounds(0, 0, 229, 43);
			panel.add(lblCloseSales);
			
			lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(425, 53, 341, 24);
			panel.add(lblNewLabel);
			
			
			
			get_register_name(company,reg_nme);
			get_store_validate_dt(company,store_name,username, reg_nme);
			get_full_paycode(company, reg_nme);
			
			if(denominationPrint.equals("1"))
			{
				JButton btnNewButton = new JButton("Denomination");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new denomination(company, reg_nme, version);
					}
				});
				btnNewButton.setBounds(262, 11, 125, 43);
				panel.add(btnNewButton);
			}
			
			
			//get_register_name(company,host_name,username, store_name);
		    //get_full_paycode_with_sales(host_name, company,reg_num.getText() );
		   // get_customer_payment_list(company,host_name,username, store_name,reg_num.getText());
		    //get_payinout_list(company,host_name,username, store_name,reg_num.getText());
	}
	
	protected void button_ok_click(ActionEvent e1,String company, String reg_nme, String username, String store_name, String decimal) {
		try {
			int cofrm_bx = JOptionPane.showConfirmDialog(frame, "<html>Do you Want to Close Register<br>Â·  —Ìœ ≈€·«ﬁ «· ”ÃÌ·ø</html>","WARNING",JOptionPane.YES_NO_OPTION);
	        // 0=yes, 1=no, 2=cancel
	        if(cofrm_bx==0)
	        {
	        	double ttl_count = 0;
	        	progressBar.setVisible(true);
	    		double number_progress=1;
	        	Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+not_ready_msg1+">"+display_port+"&&ECHO "+not_ready_msg2+">"+display_port+"\"");
				//Runtime.getRuntime().exec("cmd /c start \"\" C:\\MasterRetails\\backup\\backupbatch.bat");
	        	lblNewLabel.setText("Starting Backup");
	        	String[] command = {"C:\\MasterRetails\\backup\\backupbatch.bat","start"};
			    ProcessBuilder pb = new ProcessBuilder(command);
			    pb.redirectErrorStream(true);
			    Process exec = pb.start();

			    BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream()));
			    String text = null;
			    while ((text = br.readLine()) != null) {
			        System.out.println(text);
			    }
			    Thread.sleep(50);
			    lblNewLabel.setText("Backup Completed");
			    LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    String formattedDate = myDateObj.format(myFormatObj);  
			    final SimpleDateFormat times = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    String date_time=times.format(dateChooser.getDate());
			    Connection con=Dbsetup.local_server.getConnection();
				Statement statement;
				Statement statement1;
				Statement statement2;
				Statement statement99;
				HashMap<String, Integer> cardqty = new HashMap<String, Integer>();
				List<String> cardlist = new ArrayList<String>();
			
				statement = con.createStatement();
				statement1 = con.createStatement();
				statement2 = con.createStatement();
				statement99 = con.createStatement();
				
				double sales_today=0;
				double cash_today=0;
				boolean EODsale=false;
				if(DonotPerformEod.equals("1"))
				{
					ResultSet resultSet0 = statement.executeQuery("select * from "+company+".mr_pos_hold_invoice ");
					if(resultSet0.next())
					{
						JOptionPane.showMessageDialog(frame, "<html>You Need To Settle Hold Invoices Before Closing<br>ÌÃ» ⁄·Ìﬂ  ”ÊÌ… ›Ê« Ì— «·ÕÃ“ ﬁ»· «·≈€·«ﬁ</html>");
						EODsale=false;
						return;
					}
					else
					{
						EODsale=true;
					}
				}
				else
				{
					EODsale=true;
				}
				if(EODsale==true)
				{
					if(register_all_invoice.equals("1"))
					{
						File configFile = new File("C:/MasterRetails/config.properties");
						FileReader reader = new FileReader(configFile);
					    Properties props = new Properties();
					    props.load(reader);
					    String open_register = props.getProperty("open_register");
					    String multi_register = props.getProperty("multi_register");
					    String[] open_reg=open_register.split(",");
					    reader.close();
					    String reg_list="";
					    for(int s=0;s<open_reg.length;s++)
					    {
					    	reg_list+=open_reg[s].concat("','");
					    }
					    String register_list=reg_list+""+reg_nme;
					    
					    String qury="select count(DISTINCT(reg_num)) as ttl from "+company+".mr_pos_settlement_cash";
						ResultSet resultSet121 = statement1.executeQuery(qury);
						if(resultSet121.next())
						{
							double count=Double.parseDouble(resultSet121.getString("ttl"));
							number_progress=count;
						}
					    
					    if(multi_register.equals("1"))
			        	{
					    	ResultSet resultSet99= statement99.executeQuery("select reg_num from "+company+".mr_pos_settlement_cash group by reg_num");
							if(resultSet99.next())
							{
								sales_today=0;
								String current_reg=resultSet99.getString("reg_num");
								ResultSet resultSet111 = statement1.executeQuery("select item_code from "+company+".mr_item_open_qty");
								if(resultSet111.next())
								{
									ResultSet resultSet0 = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash_items where  item_code='"+resultSet111.getString("item_code")+"' and ref_reg_num IN ('"+register_list+"') ");
									while(resultSet0.next())
									{
										for (String str : cardlist) {
										    if (resultSet0.getString("item_code").equals(str)) {
										    	if(cardqty.containsKey(str))
										    	{
										    		cardqty.put(str, cardqty.get(str) + 1);
										    	}
										    	else
										    	{
										    		cardqty.put(str, 1);
										    	}
										    } 
										}
									}
								}
								//5
								ttl_count+=5;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	
								String query1="delete from "+company+".mr_close_pay_list where  status='0' and ref_reg_num IN ('"+register_list+"')  ";
								statement.executeUpdate(query1);
								String query3="delete from "+company+".mr_close_pay_list_items where status='0' and ref_reg_num IN ('"+register_list+"') ";
								statement.executeUpdate(query3);
								String query="insert into "+company+".mr_close_pay_list (`ref_reg_num`, `username`, `store`, `computer`, `date_time`,remarks, `created_date`,invoice_num) values('"+current_reg+"','"+username+"','"+store_name+"','"+lblNewLabel_2.getText()+"','"+date_time+"','"+textField.getText()+"','"+formattedDate+"','"+lblNewLabel_4.getText()+"') ";
								statement.executeUpdate(query);
								Thread.sleep(50);
							    lblNewLabel.setText("Creating Register Closing Items");
							    
							    //15
							    ttl_count+=10;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
							    
							    
							    String get_paylist="select * from "+company+".mr_paylist where ref_reg_no IN ('"+register_list+"') and  status='0'  ";
								ResultSet resultSet6 = statement.executeQuery(get_paylist);
								float pay_in=0;
								float pay_out=0;
								while(resultSet6.next())
								{
									if(resultSet6.getString("type").equals("payin"))
									{
										pay_in+=Float.parseFloat(resultSet6.getString("amount")) ;
									}
									else{
										pay_out+=Float.parseFloat(resultSet6.getString("amount")) ;
									}
								}
								String get_cust_paylist= "select * from "+company+".mr_customer_payment where ref_reg_num IN ('"+register_list+"') and  status='0'  ";
								ResultSet resultSet72 = statement.executeQuery(get_cust_paylist);
								float cust_amt=0;
								float cust_amt_bnk=0;
								while(resultSet72.next())
								{
									if(resultSet72.getString("paycode").equals("CASH"))
									{
										cust_amt+=Float.parseFloat(resultSet72.getString("amount")) ;
									}
									else
									{
										cust_amt_bnk+=Float.parseFloat(resultSet72.getString("amount")) ;
									}
								}
								
							    
								ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_close_pay_list_items where status='0' and ref_reg_num='"+current_reg+"' ");
								if(!resultSet1.next())
								{
									for(int i=0;i<model.getRowCount();i++)
									{
										if(!model.getValueAt(i, 1).toString().equals("STORE CREDIT"))
										{
											float sales_amt=0;
											float vat=0;
											float ex_rate=0;
											double diff=0;
											String get_ttl_cash= "select * from "+company+".mr_pos_settlement_cash where type='PAYCODE'  and paycode_disc!='0' and reg_num IN ('"+register_list+"') ";
											ResultSet resultSet37 = statement1.executeQuery(get_ttl_cash);
											while(resultSet37.next())
											{
												if(model.getValueAt(i, 1).toString().equals("CASH"))
												{
													sales_amt+=Float.parseFloat(resultSet37.getString("paycode_disc"));
													sales_today+=Float.parseFloat(resultSet37.getString("paycode_disc"));
													cash_today+=Float.parseFloat(resultSet37.getString("paycode_disc"));
												}
											}
											String get_ttl_amt="";
											if(model.getValueAt(i, 1).toString().equals("CASH"))
											{
												get_ttl_amt= "select * from "+company+".mr_pos_settlement_cash where reg_num IN ('"+register_list+"') and  (type!='PAYCODE' AND type!='RETURN' AND type!='A/R' AND type!='VOID') ";
											}
											else
											{
												get_ttl_amt= "select * from "+company+".mr_pos_settlement_cash where reg_num IN ('"+register_list+"') and type='"+model.getValueAt(i, 1).toString()+"' AND type!='VOID'";
											}
											ResultSet resultSet3 = statement.executeQuery(get_ttl_amt);
											while(resultSet3.next())
											{
												sales_amt+=Float.parseFloat(resultSet3.getString("grand_total"));
												sales_amt+=Float.parseFloat(resultSet3.getString("paycode_disc"));
												vat+=Float.parseFloat(resultSet3.getString("vat"));
												if(model.getValueAt(i, 1).toString().equals("CASH"))
												{
													cash_today+=Float.parseFloat(resultSet3.getString("grand_total"));
													cash_today+=Float.parseFloat(resultSet3.getString("paycode_disc"));
												}
												sales_today+=Float.parseFloat(resultSet3.getString("grand_total"));
												sales_today+=Float.parseFloat(resultSet3.getString("paycode_disc"));
											}
											String get_paycode_list= "select pay_code_vat,paycode_amt from "+company+".mr_paycode_settlement inner join "+company+".mr_pos_settlement_cash on mr_pos_settlement_cash.reg_num=mr_paycode_settlement.ref_reg_num and mr_pos_settlement_cash.receipt_no=mr_paycode_settlement.ref_receipt_num where paycode_name='"+model.getValueAt(i, 1).toString()+"'  and status!='1' and type!='VOID' and mr_pos_settlement_cash.reg_num IN ('"+register_list+"') ";
											ResultSet resultSet4 = statement1.executeQuery(get_paycode_list);
											while(resultSet4.next())
											{
												sales_amt+=Float.parseFloat(resultSet4.getString("paycode_amt"));
												sales_today+=Float.parseFloat(resultSet4.getString("paycode_amt"));
												vat+=Float.parseFloat(resultSet4.getString("pay_code_vat"));
											}
											String get_store_paycode_list= "select * from "+company+".mr_store_paycodes where paycode='"+model.getValueAt(i, 1).toString()+"' ";
											ResultSet resultSet5 = statement.executeQuery(get_store_paycode_list);
											if(resultSet5.next())
											{
												ex_rate=Float.parseFloat(resultSet5.getString("ex_rate"));
											}
											String open_amt = "";
											ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num IN ('"+register_list+"') and   paycode='"+model.getValueAt(i, 1).toString()+"' ");
											if(resultSet2.next())
											{
												open_amt=resultSet2.getString("amount");
											}
											String get_card_list= "select * from "+company+".mr_tmp_item_master where item_name='"+model.getValueAt(i, 1).toString()+"'";
											ResultSet resultSet13 = statement.executeQuery(get_card_list);
											if(resultSet13.next())
											{
												String code=resultSet13.getString("item_code");
												if(cardqty.containsKey(code))
										    	{
										    		sales_amt=cardqty.get(code);
										    	}
											}
											String enter_regamt = "0";
											if(!model.getValueAt(i, 3).toString().equals(""))
											{
												enter_regamt = model.getValueAt(i, 3).toString();
											}
											
//											if(model.getValueAt(i, 1).toString().equals("VISA"))
//											{
//												sales_amt=sales_amt+cust_amt_bnk;
//											}
											
											diff=(Float.parseFloat(enter_regamt)-(Float.parseFloat(open_amt)+sales_amt));
											BigDecimal bd = new BigDecimal(diff);
						            		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
						            		diff = bd.doubleValue();
						            		double ttl_sale=(sales_amt);
						            		BigDecimal be = new BigDecimal(ttl_sale);
						            		be = be.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
						            		ttl_sale = be.doubleValue();
						            		
						            		
						            		
											String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt,vat_amt) values('"+current_reg+"','"+date_time+"','"+model.getValueAt(i, 1).toString()+"','"+open_amt+"','"+ttl_sale+"','"+(Float.parseFloat(open_amt)+sales_amt)+"','"+model.getValueAt(i, 3).toString()+"','"+ex_rate+"','"+diff+"','"+formattedDate+"','"+((sales_amt)*ex_rate-vat)+"','"+vat+"') ";
											statement.executeUpdate(add_open_itm);
										}
									}
								}
								String add_list_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt) values('"+current_reg+"','"+date_time+"','CUSTOMERPAYMENTS','0','"+cust_amt+"','"+cust_amt+"','0','1','"+(0-cust_amt)+"','"+formattedDate+"','"+cust_amt+"'),('"+current_reg+"','"+date_time+"','AR PAYMENT BANK','0','"+cust_amt_bnk+"','"+cust_amt_bnk+"','0','1','"+(0-cust_amt_bnk)+"','"+formattedDate+"','"+cust_amt_bnk+"'),('"+current_reg+"','"+date_time+"','PAYIN','0','"+pay_in+"','"+pay_in+"','0','1','"+(0-pay_in)+"','"+formattedDate+"','"+pay_in+"'),('"+current_reg+"','"+date_time+"','PAYOUT','0','"+pay_out+"','"+pay_out+"','0','1','"+(0-pay_out)+"','"+formattedDate+"','"+pay_out+"') ";
								statement.executeUpdate(add_list_itm);
								
								//30
								ttl_count+=15;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
								
								report_master.Closing_reg.report(current_reg, con, printer,company);
								if(denominationPrint.equals("1"))
								{
									report_master.denomination.report(current_reg, con, printer,company);
								}
								if(change_hold_type.equals("1"))
								{
									report_master.order_summary.report(current_reg, con, "XRPT",printer,"",register_all_invoice,company,multi_register,register_list);
								}
							}
			        	}
					    else
					    {
					    	ResultSet resultSet99= statement99.executeQuery("select reg_num from "+company+".mr_pos_settlement_cash group by reg_num");
							while(resultSet99.next())
							{
								sales_today=0;
								String current_reg=resultSet99.getString("reg_num");
								ResultSet resultSet111 = statement1.executeQuery("select item_code from "+company+".mr_item_open_qty");
								if(resultSet111.next())
								{
									ResultSet resultSet0 = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash_items where  item_code='"+resultSet111.getString("item_code")+"' and ref_reg_num='"+current_reg+"' ");
									while(resultSet0.next())
									{
										for (String str : cardlist) {
										    if (resultSet0.getString("item_code").equals(str)) {
										    	if(cardqty.containsKey(str))
										    	{
										    		cardqty.put(str, cardqty.get(str) + 1);
										    	}
										    	else
										    	{
										    		cardqty.put(str, 1);
										    	}
										    } 
										}
									}
								}
								//5
								ttl_count+=5;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
								String query1="delete from "+company+".mr_close_pay_list where  status='0' and ref_reg_num='"+current_reg+"'  ";
								statement.executeUpdate(query1);
								String query3="delete from "+company+".mr_close_pay_list_items where status='0' and ref_reg_num='"+current_reg+"' ";
								statement.executeUpdate(query3);
								String query="insert into "+company+".mr_close_pay_list (`ref_reg_num`, `username`, `store`, `computer`, `date_time`,remarks, `created_date`,invoice_num) values('"+current_reg+"','"+username+"','"+store_name+"','"+lblNewLabel_2.getText()+"','"+date_time+"','"+textField.getText()+"','"+formattedDate+"','"+lblNewLabel_4.getText()+"') ";
								statement.executeUpdate(query);
								Thread.sleep(50);
							    lblNewLabel.setText("Creating Register Closing Items");
							    
							    //15
							    ttl_count+=10;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
								String get_paylist="select * from "+company+".mr_paylist where ref_reg_no='"+current_reg+"' and  status='0'  ";
								ResultSet resultSet6 = statement.executeQuery(get_paylist);
								float pay_in=0;
								float pay_out=0;
								while(resultSet6.next())
								{
									if(resultSet6.getString("type").equals("payin"))
									{
										pay_in+=Float.parseFloat(resultSet6.getString("amount")) ;
									}
									else{
										pay_out+=Float.parseFloat(resultSet6.getString("amount")) ;
									}
								}
								String get_cust_paylist= "select * from "+company+".mr_customer_payment where ref_reg_num='"+current_reg+"' and  status='0'  ";
								ResultSet resultSet72 = statement.executeQuery(get_cust_paylist);
								float cust_amt=0;
								float cust_amt_bnk=0;
								while(resultSet72.next())
								{
									if(resultSet72.getString("paycode").equals("CASH"))
									{
										cust_amt+=Float.parseFloat(resultSet72.getString("amount")) ;
									}
									else
									{
										cust_amt_bnk+=Float.parseFloat(resultSet72.getString("amount")) ;
									}
								}
								
							    
							    
								ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_close_pay_list_items where status='0' and ref_reg_num='"+current_reg+"' ");
								if(!resultSet1.next())
								{
									for(int i=0;i<model.getRowCount();i++)
									{
										if(!model.getValueAt(i, 1).toString().equals("STORE CREDIT"))
										{
											float sales_amt=0;
											float vat=0;
											float ex_rate=0;
											double diff=0;
											String get_ttl_cash= "select * from "+company+".mr_pos_settlement_cash where type='PAYCODE'  and paycode_disc!='0' and reg_num='"+current_reg+"' ";
											ResultSet resultSet37 = statement1.executeQuery(get_ttl_cash);
											while(resultSet37.next())
											{
												if(model.getValueAt(i, 1).toString().equals("CASH"))
												{
													sales_amt+=Float.parseFloat(resultSet37.getString("paycode_disc"));
													sales_today+=Float.parseFloat(resultSet37.getString("paycode_disc"));
													cash_today+=Float.parseFloat(resultSet37.getString("paycode_disc"));
												}
											}
											String get_ttl_amt="";
											if(model.getValueAt(i, 1).toString().equals("CASH"))
											{
												get_ttl_amt= "select * from "+company+".mr_pos_settlement_cash where reg_num='"+current_reg+"' and  (type!='PAYCODE' AND type!='RETURN' AND type!='A/R' AND type!='VOID') ";
											}
											else
											{
												get_ttl_amt= "select * from "+company+".mr_pos_settlement_cash where reg_num='"+current_reg+"' and type='"+model.getValueAt(i, 1).toString()+"' AND type!='VOID'";
											}
											ResultSet resultSet3 = statement.executeQuery(get_ttl_amt);
											while(resultSet3.next())
											{
												sales_amt+=Float.parseFloat(resultSet3.getString("grand_total"));
												sales_amt+=Float.parseFloat(resultSet3.getString("paycode_disc"));
												vat+=Float.parseFloat(resultSet3.getString("vat"));
												if(model.getValueAt(i, 1).toString().equals("CASH"))
												{
													cash_today+=Float.parseFloat(resultSet3.getString("grand_total"));
													cash_today+=Float.parseFloat(resultSet3.getString("paycode_disc"));
												}
												sales_today+=Float.parseFloat(resultSet3.getString("grand_total"));
												sales_today+=Float.parseFloat(resultSet3.getString("paycode_disc"));
											}
											String get_paycode_list= "select pay_code_vat,paycode_amt from "+company+".mr_paycode_settlement inner join "+company+".mr_pos_settlement_cash on mr_pos_settlement_cash.reg_num=mr_paycode_settlement.ref_reg_num and mr_pos_settlement_cash.receipt_no=mr_paycode_settlement.ref_receipt_num where paycode_name='"+model.getValueAt(i, 1).toString()+"'  and status!='1' and type!='VOID' and mr_pos_settlement_cash.reg_num='"+current_reg+"' ";
											ResultSet resultSet4 = statement1.executeQuery(get_paycode_list);
											while(resultSet4.next())
											{
												sales_amt+=Float.parseFloat(resultSet4.getString("paycode_amt"));
												sales_today+=Float.parseFloat(resultSet4.getString("paycode_amt"));
												vat+=Float.parseFloat(resultSet4.getString("pay_code_vat"));
											}
											String get_store_paycode_list= "select * from "+company+".mr_store_paycodes where paycode='"+model.getValueAt(i, 1).toString()+"' ";
											ResultSet resultSet5 = statement.executeQuery(get_store_paycode_list);
											if(resultSet5.next())
											{
												ex_rate=Float.parseFloat(resultSet5.getString("ex_rate"));
											}
											String open_amt = "";
											ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+current_reg+"' and   paycode='"+model.getValueAt(i, 1).toString()+"' ");
											if(resultSet2.next())
											{
												open_amt=resultSet2.getString("amount");
											}
											String get_card_list= "select * from "+company+".mr_tmp_item_master where item_name='"+model.getValueAt(i, 1).toString()+"'";
											ResultSet resultSet13 = statement.executeQuery(get_card_list);
											if(resultSet13.next())
											{
												String code=resultSet13.getString("item_code");
												if(cardqty.containsKey(code))
										    	{
										    		sales_amt=cardqty.get(code);
										    	}
											}
											String enter_regamt = "0";
											if(!model.getValueAt(i, 3).toString().equals(""))
											{
												enter_regamt = model.getValueAt(i, 3).toString();
											}
//											if(model.getValueAt(i, 1).toString().equals("VISA"))
//											{
//												sales_amt=sales_amt+cust_amt_bnk;
//											}
											
											diff=(Float.parseFloat(enter_regamt)-(Float.parseFloat(open_amt)+sales_amt));
											BigDecimal bd = new BigDecimal(diff);
						            		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
						            		diff = bd.doubleValue();
						            		double ttl_sale=(sales_amt);
						            		BigDecimal be = new BigDecimal(ttl_sale);
						            		be = be.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
						            		ttl_sale = be.doubleValue();
//											String add_open_itm1="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt,vat_amt) values('"+current_reg+"','"+date_time+"','"+model.getValueAt(i, 1).toString()+"','"+open_amt+"','"+ttl_sale+"','"+(Float.parseFloat(open_amt)+sales_amt)+"','"+model.getValueAt(i, 3).toString()+"','"+ex_rate+"','"+diff+"','"+formattedDate+"','"+((sales_amt)*ex_rate-vat)+"','"+vat+"') ";
//											statement.executeUpdate(add_open_itm1);
						            		
						            		String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt,vat_amt) values('"+current_reg+"','"+date_time+"','"+model.getValueAt(i, 1).toString()+"','"+open_amt+"','"+ttl_sale+"','"+(Float.parseFloat(open_amt)+sales_amt)+"','"+model.getValueAt(i, 3).toString()+"','"+ex_rate+"','"+diff+"','"+formattedDate+"','"+((sales_amt)*ex_rate-vat)+"','"+vat+"') ";
											statement.executeUpdate(add_open_itm);
										}
									}
									
								}
								String add_list_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt) values('"+current_reg+"','"+date_time+"','CUSTOMERPAYMENTS','0','"+cust_amt+"','"+cust_amt+"','0','1','"+(0-cust_amt)+"','"+formattedDate+"','"+cust_amt+"'),('"+current_reg+"','"+date_time+"','AR PAYMENT BANK','0','"+cust_amt_bnk+"','"+cust_amt_bnk+"','0','1','"+(0-cust_amt_bnk)+"','"+formattedDate+"','"+cust_amt_bnk+"'),('"+current_reg+"','"+date_time+"','PAYIN','0','"+pay_in+"','"+pay_in+"','0','1','"+(0-pay_in)+"','"+formattedDate+"','"+pay_in+"'),('"+current_reg+"','"+date_time+"','PAYOUT','0','"+pay_out+"','"+pay_out+"','0','1','"+(0-pay_out)+"','"+formattedDate+"','"+pay_out+"') ";
								statement.executeUpdate(add_list_itm);
								//30
								ttl_count+=15;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	
			               	 	if(multi_register.equals("0"))
					        	{
				               	 	report_master.Closing_reg.report(current_reg, con, printer,company);
									if(denominationPrint.equals("1"))
									{
										report_master.denomination.report(current_reg, con, printer,company);
									}
									if(change_hold_type.equals("1"))
									{
										report_master.order_summary.report(current_reg, con, "XRPT",printer,"",register_all_invoice,company,multi_register,register_list);
									}
					        	}
							}
					    	
					    }
						
						ResultSet resultSet99= statement99.executeQuery("select reg_num from "+company+".mr_pos_settlement_cash group by reg_num");
						while(resultSet99.next())
						{
							String current_reg=resultSet99.getString("reg_num");
							
							
							String closed_validate="update "+company+".mr_close_pay_list set status='1' where ref_reg_num='"+current_reg+"' ";
							statement.executeUpdate(closed_validate);
							String close_validate="update "+company+".mr_close_pay_list_items set status='1' where ref_reg_num='"+current_reg+"'  ";
							statement.executeUpdate(close_validate);
							String denomination="update "+company+".mr_denomination set status='1' where ref_reg_num='"+current_reg+"' ";
							statement.executeUpdate(denomination);
							
							//35
							ttl_count+=5/number_progress;
							Thread.sleep(50);
							progressBar.paintImmediately(0, 0, 854, 31);
		               	 	progressBar.setValue((int)ttl_count);
		               	 	Thread.sleep(50);
		               	 	lblNewLabel.setText("Close Register Setup Completed");
							endofsale=true;
							
							if(endofsale==true)
							{
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Starting Register Closing");
								String close_date="";
								String get_close_date= "select created_date from "+company+".mr_register_openings where `reg_num`='"+current_reg+"'";
								ResultSet resultSet122 = statement.executeQuery(get_close_date);
								if(resultSet122.next())
								{
									close_date=resultSet122.getString("created_date");
								}
								
								String get_ttl_amt= "select  sum(`sales_amt`*`ex_rate`) as ttl,sum( `sale_amt`) as sale_ttl,sum( `vat_amt`) as vat_ttl from "+company+".mr_close_pay_list_items where `ref_reg_num`='"+current_reg+"' and status='1' ";
								ResultSet resultSet = statement.executeQuery(get_ttl_amt);
								float new_cost=0;
								if(resultSet.next())
								{
									String add_pos="update "+company+".mr_close_pay_list set  total_sale_amt='"+sales_today+"' where `ref_reg_num`='"+current_reg+"' ";
									statement.executeUpdate(add_pos);
								}
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Getting All Invoices");
								float vat_val=0;
								float disc=0;
								float paycode_disc=0;
								String pos_item_validate="select *,mr_pos_settlement_cash_items.qty as pos_qty from "+company+".mr_pos_settlement_cash inner join "+company+".mr_pos_settlement_cash_items on mr_pos_settlement_cash.reg_num=mr_pos_settlement_cash_items.ref_reg_num and mr_pos_settlement_cash.receipt_no=mr_pos_settlement_cash_items.ref_receipt_num where ref_reg_num='"+current_reg+"' AND type!='VOID' ";
								ResultSet resultSet7 = statement.executeQuery(pos_item_validate);
								while(resultSet7.next())
								{
									String item_cost=resultSet7.getString("item_cost");
									String pos_qty=resultSet7.getString("pos_qty");
									String cf=resultSet7.getString("cf");
									new_cost+=Float.parseFloat(item_cost)*Float.parseFloat(pos_qty)*Float.parseFloat(cf);
									
									
								}
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Calculating Vat and Discount");
								String pos_item="select * from "+company+".mr_pos_settlement_cash where reg_num='"+current_reg+"' AND type!='VOID'";
								ResultSet resultSet8 = statement.executeQuery(pos_item);
								
								while(resultSet8.next())
								{
									vat_val+=Float.parseFloat(resultSet8.getString("vat"));
									disc+=Float.parseFloat(resultSet8.getString("disc"));
									paycode_disc+=Float.parseFloat(resultSet8.getString("paycode_disc"));
								}
								//40
								ttl_count+=5/number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	
								
								float total_disc=disc+paycode_disc;
								String values_update="update "+company+".mr_close_pay_list set total_disc='"+total_disc+"',total_vat='"+vat_val+"',total_cost='"+new_cost+"' where ref_reg_num='"+current_reg+"' ";
								statement.executeUpdate(values_update);
								
								String his_close_validate="select * from "+company+".mr_close_pay_list where ref_reg_num='"+current_reg+"' and status='1' ";
								ResultSet resultSet17 = statement.executeQuery(his_close_validate);
								int id=0;
								if(resultSet17.next())
								{
									String hist_reg="insert into "+company+".mr_close_pay_list_history ( ref_id,`ref_reg_num`, `username`, `store`, `computer`, `date_time`, `remarks`,total_sale_amt, `created_date`,invoice_num,total_vat,total_disc,total_cost,close_date) select id,`ref_reg_num`, `username`, `store`, `computer`, `date_time`, `remarks`,total_sale_amt, `created_date`,invoice_num,total_vat,total_disc,total_cost,'"+close_date+"' from "+company+".mr_close_pay_list where ref_reg_num='"+current_reg+"' ";
									statement1.executeUpdate(hist_reg);
									id=Integer.parseInt(resultSet17.getString("id"));
									
									String del_reg="delete from "+company+".mr_close_pay_list where ref_reg_num='"+current_reg+"' ";
									statement1.executeUpdate(del_reg);
									
								}
								
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Checking Register Open Items in Close Register");
			               	 	
								String card_validates="select * from "+company+".mr_register_open_items where ref_reg_num='"+current_reg+"' ";
								ResultSet resultSet49 = statement.executeQuery(card_validates);
								while(resultSet49.next())
								{
									String paycode=resultSet49.getString("paycode");
									if(paycode.equals("CASH"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+current_reg+"' and status='1' and (paycode='"+paycode+"' or paycode='PAYIN' or paycode='PAYOUT' or paycode='CUSTOMERPAYMENTS') ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode1="";
										float open_amt1=0;
										float total_amt1=0;
										float ex_rate1=0;
										float varience1=0;
										while(resultSet18.next())
										{
											paycode1="CASH";
											open_amt1+=Float.parseFloat(resultSet18.getString("openings"));
											total_amt1+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate1=1;
											varience1+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code1='"+paycode1+"',open_amt1='"+open_amt1+"',sales_amt1='"+cash_today+"',total_amt1='"+total_amt1+"',ex_rate1='"+ex_rate1+"',varience1='"+varience1+"' where ref_reg_num='"+current_reg+"' and ref_id='"+id+"' ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("A/R"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+current_reg+"' and status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode2="";
										float open_amt2=0;
										float sales_amt2=0;
										float total_amt2=0;
										float ex_rate2=0;
										float varience2=0;
										while(resultSet18.next())
										{
											paycode2=resultSet18.getString("paycode");
											open_amt2+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt2+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt2+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate2+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience2+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code2='"+paycode2+"',open_amt2='"+open_amt2+"',sales_amt2='"+sales_amt2+"',total_amt2='"+total_amt2+"',ex_rate2='"+ex_rate2+"',varience2='"+varience2+"' where ref_reg_num='"+current_reg+"' and ref_id='"+id+"'  "; 
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("STORE CREDIT"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+current_reg+"' and status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode3="";
										float open_amt3=0;
										float sales_amt3=0;
										float total_amt3=0;
										float ex_rate3=0;
										float varience3=0;
										while(resultSet18.next())
										{
											paycode3=resultSet18.getString("paycode");
											open_amt3+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt3+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt3+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate3+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience3+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code3='"+paycode3+"',open_amt3='"+open_amt3+"',sales_amt3='"+sales_amt3+"',total_amt3='"+total_amt3+"',ex_rate3='"+ex_rate3+"',varience3='"+varience3+"' where ref_reg_num='"+current_reg+"' and ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("VISA"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+current_reg+"' and status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode4="";
										float open_amt4=0;
										float sales_amt4=0;
										float total_amt4=0;
										float ex_rate4=0;
										float varience4=0;
										while(resultSet18.next())
										{
											paycode4=resultSet18.getString("paycode");
											open_amt4+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt4+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt4+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate4+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience4+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code4='"+paycode4+"',open_amt4='"+open_amt4+"',sales_amt4='"+sales_amt4+"',total_amt4='"+total_amt4+"',ex_rate4='"+ex_rate4+"',varience4='"+varience4+"' where ref_reg_num='"+current_reg+"' and ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("OMANI RIYAL"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+current_reg+"' and status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode5="";
										float open_amt5=0;
										float sales_amt5=0;
										float total_amt5=0;
										float ex_rate5=0;
										float varience5=0;
										while(resultSet18.next())
										{
											paycode5=resultSet18.getString("paycode");
											open_amt5+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt5+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt5+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate5+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience5+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code5='"+paycode5+"',open_amt5='"+open_amt5+"',sales_amt5='"+sales_amt5+"',total_amt5='"+total_amt5+"',ex_rate5='"+ex_rate5+"',varience5='"+varience5+"' where ref_reg_num='"+current_reg+"' and ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("SAUDI RIYAL"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+current_reg+"' and status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode6="";
										float open_amt6=0;
										float sales_amt6=0;
										float total_amt6=0;
										float ex_rate6=0;
										float varience6=0;
										while(resultSet18.next())
										{
											paycode6=resultSet18.getString("paycode");
											open_amt6+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt6+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt6+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate6+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience6+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code6='"+paycode6+"',open_amt6='"+open_amt6+"',sales_amt6='"+sales_amt6+"',total_amt6='"+total_amt6+"',ex_rate6='"+ex_rate6+"',varience6='"+varience6+"' where ref_reg_num='"+current_reg+"' and ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("US$"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+current_reg+"' and status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode7="";
										float open_amt7=0;
										float sales_amt7=0;
										float total_amt7=0;
										float ex_rate7=0;
										float varience7=0;
										while(resultSet18.next())
										{
											paycode7=resultSet18.getString("paycode");
											open_amt7+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt7+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt7+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate7+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience7+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code7='"+paycode7+"',open_amt7='"+open_amt7+"',sales_amt7='"+sales_amt7+"',total_amt7='"+total_amt7+"',ex_rate7='"+ex_rate7+"',varience7='"+varience7+"' where ref_reg_num='"+current_reg+"' and ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("TALABAT"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+current_reg+"' and status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode8="";
										float open_amt8=0;
										float sales_amt8=0;
										float total_amt8=0;
										float ex_rate8=0;
										float varience8=0;
										while(resultSet18.next())
										{
											paycode8=resultSet18.getString("paycode");
											open_amt8+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt8+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt8+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate8+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience8+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code8='"+paycode8+"',open_amt8='"+open_amt8+"',sales_amt8='"+sales_amt8+"',total_amt8='"+total_amt8+"',ex_rate8='"+ex_rate8+"',varience8='"+varience8+"' where ref_reg_num='"+current_reg+"' and ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
								}
								
								//52
								ttl_count+=12/number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	Thread.sleep(50);
			               	 	lblNewLabel.setText("Created Register Open Items based Close Register");
								
								String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+current_reg+"' and status='1' ";
								ResultSet resultSet18 = statement.executeQuery(his_close_itm_validate);
								if(resultSet18.next())
								{
									String hist_reg="insert into "+company+".mr_close_pay_list_history_items ( ref_reg_num, date_time, paycode, openings, sales_amt, total_amt, ex_rate, settle_amt, varience, created_date,sale_amt,vat_amt) select ref_reg_num, date_time, paycode, openings, sales_amt, total_amt, ex_rate,settle_amt, varience, created_date,sale_amt,vat_amt from "+company+".mr_close_pay_list_items where ref_reg_num='"+current_reg+"' ";
									statement1.executeUpdate(hist_reg);
									String del_reg="delete from "+company+".mr_close_pay_list_items where ref_reg_num='"+current_reg+"' ";
									statement1.executeUpdate(del_reg);
								}
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating History Register");
								String card_validate="select * from "+company+".mr_register_openings where reg_num='"+current_reg+"' ";
								ResultSet resultSet9 = statement.executeQuery(card_validate);
								if(resultSet9.next())
								{
									String hist_reg="insert into "+company+".mr_register_openings_history (`reg_num`, `store_name`, `user_name`, `open_receipt`, `remarks`, `date_time`, `created_date`) select `reg_num`, `store`, `user_name`, `open_receipt`, `remarks`, `date_time`, `created_date` from "+company+".mr_register_openings where reg_num='"+current_reg+"' ";
									statement.executeUpdate(hist_reg);
									String del_reg="delete from "+company+".mr_register_openings where reg_num='"+current_reg+"' ";
									statement.executeUpdate(del_reg);
								}
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating History Register Items");
								String reg_validate="select * from "+company+".mr_register_open_items where ref_reg_num='"+current_reg+"' ";
								ResultSet resultSet10 = statement.executeQuery(reg_validate);
								if(resultSet10.next())
								{
									String hist_reg="insert into "+company+".mr_register_open_items_history (  `ref_reg_num`, `date_time`, `paycode`, `pay_desc`, `amount`, `created_date`) select `ref_reg_num`, `date_time`, `paycode`, `pay_desc`, `amount`, `created_date` from "+company+".mr_register_open_items where ref_reg_num='"+current_reg+"' ";
									statement.executeUpdate(hist_reg);
									String del_reg="delete from "+company+".mr_register_open_items where ref_reg_num='"+current_reg+"' ";
									statement.executeUpdate(del_reg);
								}
								//65
								ttl_count+=13/number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	
								/*
								pay in & out
								customer
								*/
			               	 	Thread.sleep(50);
			               	 	lblNewLabel.setText("Updating Customer Payment Status");
								String pay_validate="select * from "+company+".mr_paylist where status='0' and ref_reg_no='"+current_reg+"' ";
								ResultSet resultSet11 = statement.executeQuery(pay_validate);
								if(resultSet11.next())
								{
									String hist_reg="update "+company+".mr_paylist set status='1' where ref_reg_no='"+current_reg+"' ";
									statement.executeUpdate(hist_reg);
								}
								String cust_validate="select * from "+company+".mr_customer_payment where status='0' and ref_reg_num='"+current_reg+"' ";
								ResultSet resultSet12 = statement.executeQuery(cust_validate);
								if(resultSet12.next())
								{
									String hist_reg="update "+company+".mr_customer_payment set status='1' where ref_reg_num='"+current_reg+"' ";
									statement.executeUpdate(hist_reg);
								}
								//70
								ttl_count+=5/number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	
								/*
								pos table
								pos items table
								paycode
								update qty
								tax update
								 */
			               	 	Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating Invoice History Header");
								String pos_validate="select * from "+company+".mr_pos_settlement_cash where reg_num='"+current_reg+"' ";
								ResultSet resultSet13 = statement.executeQuery(pos_validate);
								while(resultSet13.next())
								{
									String hist_reg="insert into "+company+".mr_pos_settlement_history ( `reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, `cash_paid`, `type`, `paycode_disc`, `store_id`, `created_date`,grand_total,cust_code,cust_prev_balance,close_date,`mode`, `delivery_user`, `table_num`) values('"+resultSet13.getString("reg_num")+"','"+resultSet13.getString("receipt_no")+"','"+resultSet13.getString("user")+"','"+resultSet13.getString("time")+"','"+resultSet13.getString("total")+"','"+resultSet13.getString("cust_name")+"','"+resultSet13.getString("vat")+"','"+resultSet13.getString("disc")+"',"+ "'"+resultSet13.getString("qty")+"',"+ "'"+resultSet13.getString("cash_paid")+"','"+resultSet13.getString("type")+"','"+resultSet13.getString("paycode_disc")+"','"+resultSet13.getString("store_id")+"','"+resultSet13.getString("created_date")+"','"+resultSet13.getString("grand_total")+"','"+resultSet13.getString("cust_code")+"','"+resultSet13.getString("cust_prev_balance")+"','"+close_date+"','"+resultSet13.getString("mode")+"', '"+resultSet13.getString("delivery_user")+"','"+resultSet13.getString("table_num")+"' )";
									statement1.executeUpdate(hist_reg);
								}
								
								String pos_validate1="select * from "+company+".mr_pos_settlement_cash where reg_num='"+current_reg+"' ";
								ResultSet resultSet131 = statement.executeQuery(pos_validate1);
								if(resultSet131.next())
								{
									String del_reg="delete from "+company+".mr_pos_settlement_cash where reg_num='"+current_reg+"' ";
									statement.executeUpdate(del_reg);
								}
								
								//80
								ttl_count+=10/number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	Thread.sleep(50);
			               	 	lblNewLabel.setText("Updating Item Master");
								String pos_itm_update="select barcode,item_code,sum(qty*cf) as ttl_qty from "+company+".mr_pos_settlement_cash_items where ref_reg_num='"+current_reg+"' group by item_code ";
								ResultSet resultSet114 = statement.executeQuery(pos_itm_update);
								while(resultSet114.next())
								{
									String itemcode=resultSet114.getString("item_code");
									String qty=resultSet114.getString("ttl_qty");
									String itm_validate="select * from "+company+".mr_tmp_item_master where barcode='"+resultSet114.getString("barcode")+"' ";
									ResultSet resultSet15 = statement2.executeQuery(itm_validate);
									if(resultSet15.next())
									{
										String total_qty=resultSet15.getString("qty"); 
										float newqty=Float.parseFloat(total_qty)-(Float.parseFloat(qty));
										String update_temp="update "+company+".mr_tmp_item_master set qty='"+newqty+"' where item_code='"+itemcode+"'  ";
										statement2.executeUpdate(update_temp);
										
										String update_qty="update "+company+".mr_item_master set total_qty='"+newqty+"',modified_date='"+formattedDate+"' where item_code='"+itemcode+"'  ";
										statement2.executeUpdate(update_qty);
									}
								}
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating Invoice History Details");
								String pos_itm_validate="select * from "+company+".mr_pos_settlement_cash_items where ref_reg_num='"+current_reg+"' ";
								ResultSet resultSet14 = statement.executeQuery(pos_itm_validate);
								while(resultSet14.next())
								{
									String hist_reg="insert into "+company+".mr_pos_settlement_history_items (`ref_receipt_num`, `ref_reg_num`, `barcode`, `item_desc`, `item_code`,item_cost, `unit`, `unit_price`, `qty`, `total`, `vat`, `discount`, `created_date`,sp, `vat_type`, `vat_perc`, `disc_type`,cf)	values('"+resultSet14.getString("ref_receipt_num")+"','"+resultSet14.getString("ref_reg_num")+"','"+resultSet14.getString("barcode")+"','"+resultSet14.getString("item_desc")+"','"+resultSet14.getString("item_code")+"','"+resultSet14.getString("item_cost")+"','"+resultSet14.getString("unit")+"','"+resultSet14.getString("unit_price")+"','"+resultSet14.getString("qty")+"','"+resultSet14.getString("total")+"','"+resultSet14.getString("vat")+"','"+resultSet14.getString("discount")+"','"+resultSet14.getString("created_date")+"','"+resultSet14.getString("sp")+"','"+resultSet14.getString("vat_type")+"',	'"+resultSet14.getString("vat_perc")+"','"+resultSet14.getString("disc_type")+"','"+resultSet14.getString("cf")+"')";
									statement1.executeUpdate(hist_reg);
								}
								String pos_itm_validate1="select * from "+company+".mr_pos_settlement_cash_items where ref_reg_num='"+current_reg+"' ";
								ResultSet resultSet141 = statement.executeQuery(pos_itm_validate1);
								if(resultSet141.next())
								{
									String del_reg="delete from "+company+".mr_pos_settlement_cash_items where ref_reg_num='"+current_reg+"' ";
									statement2.executeUpdate(del_reg);
								}
								//90
								ttl_count+=10/number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	
			               	 	Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating Paycode Settlement History");
								String paycode_validate="select * from "+company+".mr_paycode_settlement where ref_reg_num='"+current_reg+"' ";
								ResultSet resultSet16 = statement.executeQuery(paycode_validate);
								if(resultSet16.next())
								{
									String hist_reg="insert into "+company+".mr_paycode_settlement_history ( `ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`) select `ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date` from "+company+".mr_paycode_settlement where ref_reg_num='"+current_reg+"' ";
									statement1.executeUpdate(hist_reg);
									
								}
								String paycode_validate1="select * from "+company+".mr_paycode_settlement where ref_reg_num='"+current_reg+"' ";
								ResultSet resultSet161 = statement.executeQuery(paycode_validate1);
								if(resultSet161.next())
								{
									String del_reg="delete from "+company+".mr_paycode_settlement where ref_reg_num='"+current_reg+"' ";
									statement1.executeUpdate(del_reg);
								}
								//100
								ttl_count+=10/number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	if(ttl_count==100)
			               	 	{
				               	 	Connection con2=Dbsetup.server.getConnection();
									if(con2!=null)
									{
										new sales_master.close_progress(company, current_reg, username, store_name);
									}
									 else
									{
										JOptionPane.showMessageDialog(frame, "<html>Daily Sale Closed Successfully<br> „ ≈€·«ﬁ «·»Ì⁄ «·ÌÊ„Ì »‰Ã«Õ</html>");
										frame.setVisible(false);
									}
			               	 	}
								
								
							}
							Mainpage.btnCloseSales.setEnabled(true);
						/*register while end*/
						}
					/*all register while end*/
					}
//register if end
					else
					{
						ResultSet resultSet111 = statement1.executeQuery("select item_code from "+company+".mr_item_open_qty");
						if(resultSet111.next())
						{
							ResultSet resultSet0 = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash_items where ref_reg_num='"+reg_nme+"' and item_code='"+resultSet111.getString("item_code")+"' ");
							while(resultSet0.next())
							{
								for (String str : cardlist) {
								    if (resultSet0.getString("item_code").equals(str)) {
								    	if(cardqty.containsKey(str))
								    	{
								    		cardqty.put(str, cardqty.get(str) + 1);
								    	}
								    	else
								    	{
								    		cardqty.put(str, 1);
								    	}
								    } 
								}
							}
						}
						String query1="delete from "+company+".mr_close_pay_list where ref_reg_num='"+reg_num.getText()+"' and status='0'  ";
						statement.executeUpdate(query1);
						String query3="delete from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' and status='0' ";
						statement.executeUpdate(query3);
						String query="insert into "+company+".mr_close_pay_list (`ref_reg_num`, `username`, `store`, `computer`, `date_time`,remarks, `created_date`,invoice_num) values('"+reg_nme+"','"+username+"','"+store_name+"','"+lblNewLabel_2.getText()+"','"+date_time+"','"+textField.getText()+"','"+formattedDate+"','"+lblNewLabel_4.getText()+"') ";
						statement.executeUpdate(query);
						ttl_count=5*number_progress;
						Thread.sleep(50);
						progressBar.paintImmediately(0, 0, 854, 31);
	               	 	progressBar.setValue((int)ttl_count);
	               	 	Thread.sleep(50);
					    lblNewLabel.setText("Creating Register Closing Items");
					    ttl_count=15*number_progress;
						Thread.sleep(50);
						progressBar.paintImmediately(0, 0, 854, 31);
	               	 	progressBar.setValue((int)ttl_count);
	               	 	String get_paylist="select * from "+company+".mr_paylist where ref_reg_no='"+reg_nme+"' and status='0'  ";
						ResultSet resultSet6 = statement.executeQuery(get_paylist);
						float pay_in=0;
						float pay_out=0;
						while(resultSet6.next())
						{
							if(resultSet6.getString("type").equals("payin"))
							{
								pay_in+=Float.parseFloat(resultSet6.getString("amount")) ;
							}
							else{
								pay_out+=Float.parseFloat(resultSet6.getString("amount")) ;
							}
						}
						String get_cust_paylist= "select * from "+company+".mr_customer_payment where ref_reg_num='"+reg_nme+"'  and status='0'  ";
						ResultSet resultSet72 = statement.executeQuery(get_cust_paylist);
						float cust_amt=0;
						float cust_amt_bnk=0;
						while(resultSet72.next())
						{
							if(resultSet72.getString("paycode").equals("CASH"))
							{
								cust_amt+=Float.parseFloat(resultSet72.getString("amount")) ;
							}
							else
							{
								cust_amt_bnk+=Float.parseFloat(resultSet72.getString("amount")) ;
							}
						}
						
						
						ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_num.getText()+"' and status='0' ");
						if(!resultSet1.next())
						{
							for(int i=0;i<model.getRowCount();i++)
							{
								if(!model.getValueAt(i, 1).toString().equals("STORE CREDIT"))
								{
									float sales_amt=0;
									float vat=0;
									float ex_rate=0;
									double diff=0;
									String get_ttl_cash= "select * from "+company+".mr_pos_settlement_cash where type='PAYCODE' AND reg_num='"+reg_nme+"' and paycode_disc!='0' ";
									ResultSet resultSet37 = statement1.executeQuery(get_ttl_cash);
									while(resultSet37.next())
									{
										if(model.getValueAt(i, 1).toString().equals("CASH"))
										{
											sales_amt+=Float.parseFloat(resultSet37.getString("paycode_disc"));
											sales_today+=Float.parseFloat(resultSet37.getString("paycode_disc"));
											cash_today+=Float.parseFloat(resultSet37.getString("paycode_disc"));
										}
									}
									String get_ttl_amt="";
									if(model.getValueAt(i, 1).toString().equals("CASH"))
									{
										get_ttl_amt= "select * from "+company+".mr_pos_settlement_cash where reg_num='"+reg_nme+"' AND (type!='PAYCODE' AND type!='RETURN' AND type!='A/R' AND type!='VOID') ";
									}
									else
									{
										get_ttl_amt= "select * from "+company+".mr_pos_settlement_cash where type='"+model.getValueAt(i, 1).toString()+"' AND reg_num='"+reg_nme+"' AND type!='VOID'";
									}
									ResultSet resultSet3 = statement.executeQuery(get_ttl_amt);
									while(resultSet3.next())
									{
										sales_amt+=Float.parseFloat(resultSet3.getString("grand_total"));
										sales_amt+=Float.parseFloat(resultSet3.getString("paycode_disc"));
										vat+=Float.parseFloat(resultSet3.getString("vat"));
										if(model.getValueAt(i, 1).toString().equals("CASH"))
										{
											cash_today+=Float.parseFloat(resultSet3.getString("grand_total"));
											cash_today+=Float.parseFloat(resultSet3.getString("paycode_disc"));
										}
										sales_today+=Float.parseFloat(resultSet3.getString("grand_total"));
										sales_today+=Float.parseFloat(resultSet3.getString("paycode_disc"));
									}
//										String get_paycode_list= "select * from "+company+".mr_paycode_settlement where paycode_name='"+model.getValueAt(i, 1).toString()+"' AND ref_reg_num='"+reg_num.getText()+"' and status!='1' ";
//										ResultSet resultSet4 = statement.executeQuery(get_paycode_list);
//										while(resultSet4.next())
//										{
//											sales_amt+=Float.parseFloat(resultSet4.getString("paycode_amt"));
//											sales_today+=Float.parseFloat(resultSet4.getString("paycode_amt"));
//										}
									String get_paycode_list= "select pay_code_vat,paycode_amt from "+company+".mr_paycode_settlement inner join "+company+".mr_pos_settlement_cash on mr_pos_settlement_cash.reg_num=mr_paycode_settlement.ref_reg_num and mr_pos_settlement_cash.receipt_no=mr_paycode_settlement.ref_receipt_num where paycode_name='"+model.getValueAt(i, 1).toString()+"' AND ref_reg_num='"+reg_num.getText()+"' and status!='1' and type!='VOID' ";
									ResultSet resultSet4 = statement1.executeQuery(get_paycode_list);
									while(resultSet4.next())
									{
										sales_amt+=Float.parseFloat(resultSet4.getString("paycode_amt"));
										//sales_amt+=Float.parseFloat(resultSet4.getString("total"));
										sales_today+=Float.parseFloat(resultSet4.getString("paycode_amt"));
										vat+=Float.parseFloat(resultSet4.getString("pay_code_vat"));
									}
									String get_store_paycode_list= "select * from "+company+".mr_store_paycodes where paycode='"+model.getValueAt(i, 1).toString()+"' ";
									ResultSet resultSet5 = statement.executeQuery(get_store_paycode_list);
									if(resultSet5.next())
									{
										ex_rate=Float.parseFloat(resultSet5.getString("ex_rate"));
									}
									String open_amt = "";
									ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"' and paycode='"+model.getValueAt(i, 1).toString()+"' ");
									if(resultSet2.next())
									{
										open_amt=resultSet2.getString("amount");
									}
									String get_card_list= "select * from "+company+".mr_tmp_item_master where item_name='"+model.getValueAt(i, 1).toString()+"'";
									ResultSet resultSet13 = statement.executeQuery(get_card_list);
									if(resultSet13.next())
									{
										String code=resultSet13.getString("item_code");
										if(cardqty.containsKey(code))
								    	{
								    		sales_amt=cardqty.get(code);
								    	}
									}
//									if(model.getValueAt(i, 1).toString().equals("VISA"))
//									{
//										sales_amt=sales_amt+cust_amt_bnk;
//									}
									diff=(Float.parseFloat(model.getValueAt(i, 3).toString())-(Float.parseFloat(open_amt)+sales_amt));
									BigDecimal bd = new BigDecimal(diff);
				            		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
				            		diff = bd.doubleValue();
				            		double ttl_sale=(sales_amt);
				            		BigDecimal be = new BigDecimal(ttl_sale);
				            		be = be.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
				            		ttl_sale = be.doubleValue();
				            		
				            		
									String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt,vat_amt) values('"+reg_nme+"','"+date_time+"','"+model.getValueAt(i, 1).toString()+"','"+open_amt+"','"+ttl_sale+"','"+(Float.parseFloat(open_amt)+sales_amt)+"','"+model.getValueAt(i, 3).toString()+"','"+ex_rate+"','"+diff+"','"+formattedDate+"','"+((sales_amt)*ex_rate-vat)+"','"+vat+"') ";
									statement.executeUpdate(add_open_itm);
								}
								
								
							}
							
						}
//						String add_custpay_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt) values('"+reg_nme+"','"+date_time+"','CUSTOMERPAYMENTS','0','"+cust_amt+"','"+cust_amt+"','0','1','"+(0-cust_amt)+"','"+formattedDate+"','"+cust_amt+"'),('"+reg_nme+"','"+date_time+"','PAYIN','0','"+pay_in+"','"+pay_in+"','0','1','"+(0-pay_in)+"','"+formattedDate+"','"+pay_in+"'),('"+reg_nme+"','"+date_time+"','PAYOUT','0','"+pay_out+"','"+pay_out+"','0','1','"+(0-pay_out)+"','"+formattedDate+"','"+pay_out+"') ";
//						statement.executeUpdate(add_custpay_itm);
						
						String add_list_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt) values('"+reg_nme+"','"+date_time+"','CUSTOMERPAYMENTS','0','"+cust_amt+"','"+cust_amt+"','0','1','"+(0-cust_amt)+"','"+formattedDate+"','"+cust_amt+"'),('"+reg_nme+"','"+date_time+"','AR PAYMENT BANK','0','"+cust_amt_bnk+"','"+cust_amt_bnk+"','0','1','"+(0-cust_amt_bnk)+"','"+formattedDate+"','"+cust_amt_bnk+"'),('"+reg_nme+"','"+date_time+"','PAYIN','0','"+pay_in+"','"+pay_in+"','0','1','"+(0-pay_in)+"','"+formattedDate+"','"+pay_in+"'),('"+reg_nme+"','"+date_time+"','PAYOUT','0','"+pay_out+"','"+pay_out+"','0','1','"+(0-pay_out)+"','"+formattedDate+"','"+pay_out+"') ";
						statement.executeUpdate(add_list_itm);
						
						ttl_count=30*number_progress;
						Thread.sleep(50);
						progressBar.paintImmediately(0, 0, 854, 31);
	               	 	progressBar.setValue((int)ttl_count);
	               	 	
						report_master.Closing_reg.report(reg_nme, con, printer,company);
						if(denominationPrint.equals("1"))
						{
							report_master.denomination.report(reg_nme, con, printer,company);
						}
						if(change_hold_type.equals("1"))
						{
							report_master.order_summary.report(reg_nme, con, "XRPT",printer,"",register_all_invoice,company,"","");
						}
						
						String closed_validate="update "+company+".mr_close_pay_list set status='1' where ref_reg_num='"+reg_nme+"' ";
						statement.executeUpdate(closed_validate);
						String close_validate="update "+company+".mr_close_pay_list_items set status='1' where ref_reg_num='"+reg_nme+"' ";
						statement.executeUpdate(close_validate);
						String denomination="update "+company+".mr_denomination set status='1' where ref_reg_num='"+reg_nme+"' ";
						statement.executeUpdate(denomination);
						ttl_count=35*number_progress;
						Thread.sleep(50);
						progressBar.paintImmediately(0, 0, 854, 31);
	               	 	progressBar.setValue((int)ttl_count);
	               	 	
	               	 	endofsale=true;
	               	 	lblNewLabel.setText("Close Register Setup Completed");
						endofsale=true;
						if(endofsale==true)
						{
							Thread.sleep(50);
		               	 	lblNewLabel.setText("Starting Register Closing");
							if(register_all_invoice.equals("1"))
							{
								String close_date="";
								String get_close_date= "select created_date from "+company+".mr_register_openings ";
								ResultSet resultSet122 = statement.executeQuery(get_close_date);
								if(resultSet122.next())
								{
									close_date=resultSet122.getString("created_date");
								}
								
								String get_ttl_amt= "select  sum(`sales_amt`*`ex_rate`) as ttl,sum( `sale_amt`) as sale_ttl,sum( `vat_amt`) as vat_ttl from "+company+".mr_close_pay_list_items where status='1' ";
								ResultSet resultSet = statement.executeQuery(get_ttl_amt);
								float new_cost=0;
								if(resultSet.next())
								{
									String add_pos="update "+company+".mr_close_pay_list set  total_sale_amt='"+sales_today+"' ";
									statement.executeUpdate(add_pos);
								}
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Getting All Invoices");
								float vat_val=0;
								float disc=0;
								float paycode_disc=0;
								String pos_item_validate="select *,mr_pos_settlement_cash_items.qty as pos_qty from "+company+".mr_pos_settlement_cash inner join "+company+".mr_pos_settlement_cash_items on mr_pos_settlement_cash.reg_num=mr_pos_settlement_cash_items.ref_reg_num and mr_pos_settlement_cash.receipt_no=mr_pos_settlement_cash_items.ref_receipt_num where  type!='VOID' ";
								ResultSet resultSet7 = statement.executeQuery(pos_item_validate);
								while(resultSet7.next())
								{
									String item_cost=resultSet7.getString("item_cost");
									String pos_qty=resultSet7.getString("pos_qty");
									String cf=resultSet7.getString("cf");
									new_cost+=Float.parseFloat(item_cost)*Float.parseFloat(pos_qty)*Float.parseFloat(cf);
									
									
								}
								String pos_item="select * from "+company+".mr_pos_settlement_cash where  type!='VOID'";
								ResultSet resultSet8 = statement.executeQuery(pos_item);
								
								while(resultSet8.next())
								{
									vat_val+=Float.parseFloat(resultSet8.getString("vat"));
									disc+=Float.parseFloat(resultSet8.getString("disc"));
									paycode_disc+=Float.parseFloat(resultSet8.getString("paycode_disc"));
								}
								
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Calculating Vat and Discount");
								float total_disc=disc+paycode_disc;
								String values_update="update "+company+".mr_close_pay_list set total_disc='"+total_disc+"',total_vat='"+vat_val+"',total_cost='"+new_cost+"'";
								statement.executeUpdate(values_update);
								
								String his_close_validate="select * from "+company+".mr_close_pay_list where status='1' ";
								ResultSet resultSet17 = statement.executeQuery(his_close_validate);
								int id=0;
								if(resultSet17.next())
								{
									String hist_reg="insert into "+company+".mr_close_pay_list_history ( ref_id,`ref_reg_num`, `username`, `store`, `computer`, `date_time`, `remarks`,total_sale_amt, `created_date`,invoice_num,total_vat,total_disc,total_cost,close_date) select id,`ref_reg_num`, `username`, `store`, `computer`, `date_time`, `remarks`,total_sale_amt, `created_date`,invoice_num,total_vat,total_disc,total_cost,'"+close_date+"' from "+company+".mr_close_pay_list  ";
									statement1.executeUpdate(hist_reg);
									id=Integer.parseInt(resultSet17.getString("id"));
									
									String del_reg="delete from "+company+".mr_close_pay_list ";
									statement1.executeUpdate(del_reg);
									
								}
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Checking Register Open Items in Close Register");
								
								String card_validates="select * from "+company+".mr_register_open_items  ";
								ResultSet resultSet49 = statement.executeQuery(card_validates);
								while(resultSet49.next())
								{
									String paycode=resultSet49.getString("paycode");
									if(paycode.equals("CASH"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where status='1' and (paycode='"+paycode+"' or paycode='PAYIN' or paycode='PAYOUT' or paycode='CUSTOMERPAYMENTS') ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode1="";
										float open_amt1=0;
										float total_amt1=0;
										float ex_rate1=0;
										float varience1=0;
										while(resultSet18.next())
										{
											paycode1="CASH";
											open_amt1+=Float.parseFloat(resultSet18.getString("openings"));
											total_amt1+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate1=1;
											varience1+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code1='"+paycode1+"',open_amt1='"+open_amt1+"',sales_amt1='"+cash_today+"',total_amt1='"+total_amt1+"',ex_rate1='"+ex_rate1+"',varience1='"+varience1+"' where ref_id='"+id+"' ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("A/R"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where  status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode2="";
										float open_amt2=0;
										float sales_amt2=0;
										float total_amt2=0;
										float ex_rate2=0;
										float varience2=0;
										while(resultSet18.next())
										{
											paycode2=resultSet18.getString("paycode");
											open_amt2+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt2+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt2+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate2+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience2+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code2='"+paycode2+"',open_amt2='"+open_amt2+"',sales_amt2='"+sales_amt2+"',total_amt2='"+total_amt2+"',ex_rate2='"+ex_rate2+"',varience2='"+varience2+"' where ref_id='"+id+"'  "; 
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("STORE CREDIT"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where  status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode3="";
										float open_amt3=0;
										float sales_amt3=0;
										float total_amt3=0;
										float ex_rate3=0;
										float varience3=0;
										while(resultSet18.next())
										{
											paycode3=resultSet18.getString("paycode");
											open_amt3+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt3+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt3+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate3+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience3+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code3='"+paycode3+"',open_amt3='"+open_amt3+"',sales_amt3='"+sales_amt3+"',total_amt3='"+total_amt3+"',ex_rate3='"+ex_rate3+"',varience3='"+varience3+"' where  ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("VISA"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where  status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode4="";
										float open_amt4=0;
										float sales_amt4=0;
										float total_amt4=0;
										float ex_rate4=0;
										float varience4=0;
										while(resultSet18.next())
										{
											paycode4=resultSet18.getString("paycode");
											open_amt4+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt4+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt4+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate4+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience4+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code4='"+paycode4+"',open_amt4='"+open_amt4+"',sales_amt4='"+sales_amt4+"',total_amt4='"+total_amt4+"',ex_rate4='"+ex_rate4+"',varience4='"+varience4+"' where ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("OMANI RIYAL"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where  status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode5="";
										float open_amt5=0;
										float sales_amt5=0;
										float total_amt5=0;
										float ex_rate5=0;
										float varience5=0;
										while(resultSet18.next())
										{
											paycode5=resultSet18.getString("paycode");
											open_amt5+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt5+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt5+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate5+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience5+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code5='"+paycode5+"',open_amt5='"+open_amt5+"',sales_amt5='"+sales_amt5+"',total_amt5='"+total_amt5+"',ex_rate5='"+ex_rate5+"',varience5='"+varience5+"' where  ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("SAUDI RIYAL"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where  status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode6="";
										float open_amt6=0;
										float sales_amt6=0;
										float total_amt6=0;
										float ex_rate6=0;
										float varience6=0;
										while(resultSet18.next())
										{
											paycode6=resultSet18.getString("paycode");
											open_amt6+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt6+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt6+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate6+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience6+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code6='"+paycode6+"',open_amt6='"+open_amt6+"',sales_amt6='"+sales_amt6+"',total_amt6='"+total_amt6+"',ex_rate6='"+ex_rate6+"',varience6='"+varience6+"' where  ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("US$"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where  status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode7="";
										float open_amt7=0;
										float sales_amt7=0;
										float total_amt7=0;
										float ex_rate7=0;
										float varience7=0;
										while(resultSet18.next())
										{
											paycode7=resultSet18.getString("paycode");
											open_amt7+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt7+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt7+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate7+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience7+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code7='"+paycode7+"',open_amt7='"+open_amt7+"',sales_amt7='"+sales_amt7+"',total_amt7='"+total_amt7+"',ex_rate7='"+ex_rate7+"',varience7='"+varience7+"' where  ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("TALABAT"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where  status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode8="";
										float open_amt8=0;
										float sales_amt8=0;
										float total_amt8=0;
										float ex_rate8=0;
										float varience8=0;
										while(resultSet18.next())
										{
											paycode8=resultSet18.getString("paycode");
											open_amt8+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt8+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt8+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate8+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience8+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code8='"+paycode8+"',open_amt8='"+open_amt8+"',sales_amt8='"+sales_amt8+"',total_amt8='"+total_amt8+"',ex_rate8='"+ex_rate8+"',varience8='"+varience8+"' where  ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
								}
								
								ttl_count=47*number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	Thread.sleep(50);
			               	 	lblNewLabel.setText("Created Register Open Items based Close Register");
			               	 	
								String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where status='1' ";
								ResultSet resultSet18 = statement.executeQuery(his_close_itm_validate);
								if(resultSet18.next())
								{
									String hist_reg="insert into "+company+".mr_close_pay_list_history_items ( ref_reg_num, date_time, paycode, openings, sales_amt, total_amt, ex_rate, settle_amt, varience, created_date,sale_amt,vat_amt) select ref_reg_num, date_time, paycode, openings, sales_amt, total_amt, ex_rate,settle_amt, varience, created_date,sale_amt,vat_amt from "+company+".mr_close_pay_list_items ";
									statement1.executeUpdate(hist_reg);
									String del_reg="delete from "+company+".mr_close_pay_list_items  ";
									statement1.executeUpdate(del_reg);
								}
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating History Register");
								String card_validate="select * from "+company+".mr_register_openings ";
								ResultSet resultSet9 = statement.executeQuery(card_validate);
								if(resultSet9.next())
								{
									String hist_reg="insert into "+company+".mr_register_openings_history (`reg_num`, `store_name`, `user_name`, `open_receipt`, `remarks`, `date_time`, `created_date`) select `reg_num`, `store`, `user_name`, `open_receipt`, `remarks`, `date_time`, `created_date` from "+company+".mr_register_openings  ";
									statement.executeUpdate(hist_reg);
									String del_reg="delete from "+company+".mr_register_openings  ";
									statement.executeUpdate(del_reg);
								}
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating History Register Items");
								String reg_validate="select * from "+company+".mr_register_open_items  ";
								ResultSet resultSet10 = statement.executeQuery(reg_validate);
								if(resultSet10.next())
								{
									String hist_reg="insert into "+company+".mr_register_open_items_history (  `ref_reg_num`, `date_time`, `paycode`, `pay_desc`, `amount`, `created_date`) select `ref_reg_num`, `date_time`, `paycode`, `pay_desc`, `amount`, `created_date` from "+company+".mr_register_open_items ";
									statement.executeUpdate(hist_reg);
									String del_reg="delete from "+company+".mr_register_open_items";
									statement.executeUpdate(del_reg);
								}
								ttl_count=54*number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	
								/*
								pay in & out
								customer
								*/
			               	 	Thread.sleep(50);
			               	 	lblNewLabel.setText("Updating Customer Payment Status");
								String pay_validate="select * from "+company+".mr_paylist where status='0' ";
								ResultSet resultSet11 = statement.executeQuery(pay_validate);
								if(resultSet11.next())
								{
									String hist_reg="update "+company+".mr_paylist set status='1'  ";
									statement.executeUpdate(hist_reg);
								}
								String cust_validate="select * from "+company+".mr_customer_payment where status='0' ";
								ResultSet resultSet12 = statement.executeQuery(cust_validate);
								if(resultSet12.next())
								{
									String hist_reg="update "+company+".mr_customer_payment set status='1'  ";
									statement.executeUpdate(hist_reg);
								}
								/*
								pos table
								pos items table
								paycode
								update qty
								tax update
								*/
								ttl_count=59*number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating Invoice History Header");
								String pos_validate="select * from "+company+".mr_pos_settlement_cash ";
								ResultSet resultSet13 = statement.executeQuery(pos_validate);
								while(resultSet13.next())
								{
									String hist_reg="insert into "+company+".mr_pos_settlement_history ( `reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, `cash_paid`, `type`, `paycode_disc`, `store_id`, `created_date`,grand_total,cust_code,cust_prev_balance,close_date,`mode`, `delivery_user`, `table_num`) values('"+resultSet13.getString("reg_num")+"','"+resultSet13.getString("receipt_no")+"','"+resultSet13.getString("user")+"','"+resultSet13.getString("time")+"','"+resultSet13.getString("total")+"','"+resultSet13.getString("cust_name")+"','"+resultSet13.getString("vat")+"','"+resultSet13.getString("disc")+"',"+ "'"+resultSet13.getString("qty")+"',"+ "'"+resultSet13.getString("cash_paid")+"','"+resultSet13.getString("type")+"','"+resultSet13.getString("paycode_disc")+"','"+resultSet13.getString("store_id")+"','"+resultSet13.getString("created_date")+"','"+resultSet13.getString("grand_total")+"','"+resultSet13.getString("cust_code")+"','"+resultSet13.getString("cust_prev_balance")+"','"+close_date+"','"+resultSet13.getString("mode")+"', '"+resultSet13.getString("delivery_user")+"','"+resultSet13.getString("table_num")+"' )";
									statement1.executeUpdate(hist_reg);
								}
								String pos_validate1="select * from "+company+".mr_pos_settlement_cash ";
								ResultSet resultSet131 = statement.executeQuery(pos_validate1);
								if(resultSet131.next())
								{
									String del_reg="delete from "+company+".mr_pos_settlement_cash  ";
									statement.executeUpdate(del_reg);
								}
								ttl_count=70*number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	Thread.sleep(50);
			               	 	lblNewLabel.setText("Updating Item Master");
								String pos_itm_update="select barcode,item_code,sum(qty*cf) as ttl_qty from "+company+".mr_pos_settlement_cash_items group by item_code ";
								ResultSet resultSet114 = statement.executeQuery(pos_itm_update);
								while(resultSet114.next())
								{
									String itemcode=resultSet114.getString("item_code");
									String qty=resultSet114.getString("ttl_qty");
									String itm_validate="select * from "+company+".mr_tmp_item_master where barcode='"+resultSet114.getString("barcode")+"' ";
									ResultSet resultSet15 = statement2.executeQuery(itm_validate);
									if(resultSet15.next())
									{
										String total_qty=resultSet15.getString("qty"); 
										float newqty=Float.parseFloat(total_qty)-(Float.parseFloat(qty));
										String update_temp="update "+company+".mr_tmp_item_master set qty='"+newqty+"' where item_code='"+itemcode+"'  ";
										statement2.executeUpdate(update_temp);
										
										String update_qty="update "+company+".mr_item_master set total_qty='"+newqty+"',modified_date='"+formattedDate+"' where item_code='"+itemcode+"'  ";
										statement2.executeUpdate(update_qty);
									}
								}
								ttl_count=80*number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating Invoice History Details");
								String pos_itm_validate="select * from "+company+".mr_pos_settlement_cash_items";
								ResultSet resultSet14 = statement.executeQuery(pos_itm_validate);
								while(resultSet14.next())
								{
									String hist_reg="insert into "+company+".mr_pos_settlement_history_items (`ref_receipt_num`, `ref_reg_num`, `barcode`, `item_desc`, `item_code`,item_cost, `unit`, `unit_price`, `qty`, `total`, `vat`, `discount`, `created_date`,sp, `vat_type`, `vat_perc`, `disc_type`,cf)	values('"+resultSet14.getString("ref_receipt_num")+"','"+resultSet14.getString("ref_reg_num")+"','"+resultSet14.getString("barcode")+"','"+resultSet14.getString("item_desc")+"','"+resultSet14.getString("item_code")+"','"+resultSet14.getString("item_cost")+"','"+resultSet14.getString("unit")+"','"+resultSet14.getString("unit_price")+"','"+resultSet14.getString("qty")+"','"+resultSet14.getString("total")+"','"+resultSet14.getString("vat")+"','"+resultSet14.getString("discount")+"','"+resultSet14.getString("created_date")+"','"+resultSet14.getString("sp")+"','"+resultSet14.getString("vat_type")+"',	'"+resultSet14.getString("vat_perc")+"','"+resultSet14.getString("disc_type")+"','"+resultSet14.getString("cf")+"')";
									statement1.executeUpdate(hist_reg);
								}
								String pos_itm_validate1="select * from "+company+".mr_pos_settlement_cash_items ";
								ResultSet resultSet141 = statement.executeQuery(pos_itm_validate1);
								if(resultSet141.next())
								{
									String del_reg="delete from "+company+".mr_pos_settlement_cash_items  ";
									statement2.executeUpdate(del_reg);
								}
								ttl_count=90*number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating Paycode Settlement History");
								String paycode_validate="select * from "+company+".mr_paycode_settlement ";
								ResultSet resultSet16 = statement.executeQuery(paycode_validate);
								if(resultSet16.next())
								{
									String hist_reg="insert into "+company+".mr_paycode_settlement_history ( `ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`) select `ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date` from "+company+".mr_paycode_settlement ";
									statement1.executeUpdate(hist_reg);
									
								}
								String paycode_validate1="select * from "+company+".mr_paycode_settlement ";
								ResultSet resultSet161 = statement.executeQuery(paycode_validate1);
								if(resultSet161.next())
								{
									String del_reg="delete from "+company+".mr_paycode_settlement ";
									statement1.executeUpdate(del_reg);
								}
								ttl_count=100*number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	
								Connection con2=Dbsetup.server.getConnection();
								if(con2!=null)
								{
									new sales_master.close_progress(company, reg_nme, username, store_name);
								}
								 else
								{
									JOptionPane.showMessageDialog(frame, "<html>Daily Sale Closed Successfully<br> „ ≈€·«ﬁ «·»Ì⁄ «·ÌÊ„Ì »‰Ã«Õ</html>");
									frame.setVisible(false);
								}
							    
							}
							else
							{
								String close_date="";
								String get_close_date= "select created_date from "+company+".mr_register_openings where `reg_num`='"+reg_nme+"'";
								ResultSet resultSet122 = statement.executeQuery(get_close_date);
								if(resultSet122.next())
								{
									close_date=resultSet122.getString("created_date");
								}
								
								String get_ttl_amt= "select  sum(`sales_amt`*`ex_rate`) as ttl,sum( `sale_amt`) as sale_ttl,sum( `vat_amt`) as vat_ttl from "+company+".mr_close_pay_list_items where `ref_reg_num`='"+reg_nme+"' and status='1' ";
								ResultSet resultSet = statement.executeQuery(get_ttl_amt);
								float new_cost=0;
								if(resultSet.next())
								{
									String add_pos="update "+company+".mr_close_pay_list set  total_sale_amt='"+sales_today+"' where `ref_reg_num`='"+reg_nme+"' ";
									statement.executeUpdate(add_pos);
								}
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Getting All Invoices");
								float vat_val=0;
								float disc=0;
								float paycode_disc=0;
								String pos_item_validate="select *,mr_pos_settlement_cash_items.qty as pos_qty from "+company+".mr_pos_settlement_cash inner join "+company+".mr_pos_settlement_cash_items on mr_pos_settlement_cash.reg_num=mr_pos_settlement_cash_items.ref_reg_num and mr_pos_settlement_cash.receipt_no=mr_pos_settlement_cash_items.ref_receipt_num where ref_reg_num='"+reg_nme+"' AND type!='VOID' ";
								ResultSet resultSet7 = statement.executeQuery(pos_item_validate);
								while(resultSet7.next())
								{
									String item_cost=resultSet7.getString("item_cost");
									String pos_qty=resultSet7.getString("pos_qty");
									String cf=resultSet7.getString("cf");
									new_cost+=Float.parseFloat(item_cost)*Float.parseFloat(pos_qty)*Float.parseFloat(cf);
									
									
								}
								String pos_item="select * from "+company+".mr_pos_settlement_cash where reg_num='"+reg_nme+"' AND type!='VOID'";
								ResultSet resultSet8 = statement.executeQuery(pos_item);
								
								while(resultSet8.next())
								{
									vat_val+=Float.parseFloat(resultSet8.getString("vat"));
									disc+=Float.parseFloat(resultSet8.getString("disc"));
									paycode_disc+=Float.parseFloat(resultSet8.getString("paycode_disc"));
								}
								ttl_count=40*number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	
			               	 Thread.sleep(50);
			               	 	lblNewLabel.setText("Calculating Vat and Discount");
								float total_disc=disc+paycode_disc;
								String values_update="update "+company+".mr_close_pay_list set total_disc='"+total_disc+"',total_vat='"+vat_val+"',total_cost='"+new_cost+"' where ref_reg_num='"+reg_nme+"' ";
								statement.executeUpdate(values_update);
								
								String his_close_validate="select * from "+company+".mr_close_pay_list where ref_reg_num='"+reg_nme+"' and status='1' ";
								ResultSet resultSet17 = statement.executeQuery(his_close_validate);
								int id=0;
								if(resultSet17.next())
								{
									String hist_reg="insert into "+company+".mr_close_pay_list_history ( ref_id,`ref_reg_num`, `username`, `store`, `computer`, `date_time`, `remarks`,total_sale_amt, `created_date`,invoice_num,total_vat,total_disc,total_cost,close_date) select id,`ref_reg_num`, `username`, `store`, `computer`, `date_time`, `remarks`,total_sale_amt, `created_date`,invoice_num,total_vat,total_disc,total_cost,'"+close_date+"' from "+company+".mr_close_pay_list where ref_reg_num='"+reg_nme+"' ";
									statement1.executeUpdate(hist_reg);
									id=Integer.parseInt(resultSet17.getString("id"));
									
									String del_reg="delete from "+company+".mr_close_pay_list where ref_reg_num='"+reg_nme+"' ";
									statement1.executeUpdate(del_reg);
									
								}
								
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Checking Register Open Items in Close Register");
								String card_validates="select * from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"' ";
								ResultSet resultSet49 = statement.executeQuery(card_validates);
								while(resultSet49.next())
								{
									String paycode=resultSet49.getString("paycode");
									if(paycode.equals("CASH"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' and status='1' and (paycode='"+paycode+"' or paycode='PAYIN' or paycode='PAYOUT' or paycode='CUSTOMERPAYMENTS') ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode1="";
										float open_amt1=0;
										float total_amt1=0;
										float ex_rate1=0;
										float varience1=0;
										while(resultSet18.next())
										{
											paycode1="CASH";
											open_amt1+=Float.parseFloat(resultSet18.getString("openings"));
											total_amt1+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate1=1;
											varience1+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code1='"+paycode1+"',open_amt1='"+open_amt1+"',sales_amt1='"+cash_today+"',total_amt1='"+total_amt1+"',ex_rate1='"+ex_rate1+"',varience1='"+varience1+"' where ref_reg_num='"+reg_nme+"' and ref_id='"+id+"' ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("A/R"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' and status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode2="";
										float open_amt2=0;
										float sales_amt2=0;
										float total_amt2=0;
										float ex_rate2=0;
										float varience2=0;
										while(resultSet18.next())
										{
											paycode2=resultSet18.getString("paycode");
											open_amt2+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt2+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt2+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate2+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience2+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code2='"+paycode2+"',open_amt2='"+open_amt2+"',sales_amt2='"+sales_amt2+"',total_amt2='"+total_amt2+"',ex_rate2='"+ex_rate2+"',varience2='"+varience2+"' where ref_reg_num='"+reg_nme+"' and ref_id='"+id+"'  "; 
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("STORE CREDIT"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' and status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode3="";
										float open_amt3=0;
										float sales_amt3=0;
										float total_amt3=0;
										float ex_rate3=0;
										float varience3=0;
										while(resultSet18.next())
										{
											paycode3=resultSet18.getString("paycode");
											open_amt3+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt3+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt3+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate3+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience3+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code3='"+paycode3+"',open_amt3='"+open_amt3+"',sales_amt3='"+sales_amt3+"',total_amt3='"+total_amt3+"',ex_rate3='"+ex_rate3+"',varience3='"+varience3+"' where ref_reg_num='"+reg_nme+"' and ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("VISA"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' and status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode4="";
										float open_amt4=0;
										float sales_amt4=0;
										float total_amt4=0;
										float ex_rate4=0;
										float varience4=0;
										while(resultSet18.next())
										{
											paycode4=resultSet18.getString("paycode");
											open_amt4+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt4+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt4+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate4+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience4+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code4='"+paycode4+"',open_amt4='"+open_amt4+"',sales_amt4='"+sales_amt4+"',total_amt4='"+total_amt4+"',ex_rate4='"+ex_rate4+"',varience4='"+varience4+"' where ref_reg_num='"+reg_nme+"' and ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("OMANI RIYAL"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' and status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode5="";
										float open_amt5=0;
										float sales_amt5=0;
										float total_amt5=0;
										float ex_rate5=0;
										float varience5=0;
										while(resultSet18.next())
										{
											paycode5=resultSet18.getString("paycode");
											open_amt5+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt5+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt5+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate5+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience5+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code5='"+paycode5+"',open_amt5='"+open_amt5+"',sales_amt5='"+sales_amt5+"',total_amt5='"+total_amt5+"',ex_rate5='"+ex_rate5+"',varience5='"+varience5+"' where ref_reg_num='"+reg_nme+"' and ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("SAUDI RIYAL"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' and status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode6="";
										float open_amt6=0;
										float sales_amt6=0;
										float total_amt6=0;
										float ex_rate6=0;
										float varience6=0;
										while(resultSet18.next())
										{
											paycode6=resultSet18.getString("paycode");
											open_amt6+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt6+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt6+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate6+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience6+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code6='"+paycode6+"',open_amt6='"+open_amt6+"',sales_amt6='"+sales_amt6+"',total_amt6='"+total_amt6+"',ex_rate6='"+ex_rate6+"',varience6='"+varience6+"' where ref_reg_num='"+reg_nme+"' and ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("US$"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' and status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode7="";
										float open_amt7=0;
										float sales_amt7=0;
										float total_amt7=0;
										float ex_rate7=0;
										float varience7=0;
										while(resultSet18.next())
										{
											paycode7=resultSet18.getString("paycode");
											open_amt7+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt7+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt7+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate7+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience7+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code7='"+paycode7+"',open_amt7='"+open_amt7+"',sales_amt7='"+sales_amt7+"',total_amt7='"+total_amt7+"',ex_rate7='"+ex_rate7+"',varience7='"+varience7+"' where ref_reg_num='"+reg_nme+"' and ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
									else if(paycode.equals("TALABAT"))
									{
										String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' and status='1' and paycode='"+paycode+"'  ";
										ResultSet resultSet18 = statement1.executeQuery(his_close_itm_validate);
										String paycode8="";
										float open_amt8=0;
										float sales_amt8=0;
										float total_amt8=0;
										float ex_rate8=0;
										float varience8=0;
										while(resultSet18.next())
										{
											paycode8=resultSet18.getString("paycode");
											open_amt8+=Float.parseFloat(resultSet18.getString("openings"));
											sales_amt8+=Float.parseFloat(resultSet18.getString("sales_amt"));
											total_amt8+=Float.parseFloat(resultSet18.getString("total_amt"));
											ex_rate8+=Float.parseFloat(resultSet18.getString("ex_rate"));
											varience8+=Float.parseFloat(resultSet18.getString("varience"));
										}
										String hist_reg="update "+company+".mr_close_pay_list_history set pay_code8='"+paycode8+"',open_amt8='"+open_amt8+"',sales_amt8='"+sales_amt8+"',total_amt8='"+total_amt8+"',ex_rate8='"+ex_rate8+"',varience8='"+varience8+"' where ref_reg_num='"+reg_nme+"' and ref_id='"+id+"'  ";
										statement1.executeUpdate(hist_reg);
									}
								}
								ttl_count=52*number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 Thread.sleep(50);
			               	 	lblNewLabel.setText("Created Register Open Items based Close Register");
			               	 	
								
								String his_close_itm_validate="select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' and status='1' ";
								ResultSet resultSet18 = statement.executeQuery(his_close_itm_validate);
								if(resultSet18.next())
								{
									String hist_reg="insert into "+company+".mr_close_pay_list_history_items ( ref_reg_num, date_time, paycode, openings, sales_amt, total_amt, ex_rate, settle_amt, varience, created_date,sale_amt,vat_amt) select ref_reg_num, date_time, paycode, openings, sales_amt, total_amt, ex_rate,settle_amt, varience, created_date,sale_amt,vat_amt from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' ";
									statement1.executeUpdate(hist_reg);
									String del_reg="delete from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' ";
									statement1.executeUpdate(del_reg);
								}
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating History Register");
								String card_validate="select * from "+company+".mr_register_openings where reg_num='"+reg_nme+"' ";
								ResultSet resultSet9 = statement.executeQuery(card_validate);
								if(resultSet9.next())
								{
									String hist_reg="insert into "+company+".mr_register_openings_history (`reg_num`, `store_name`, `user_name`, `open_receipt`, `remarks`, `date_time`, `created_date`) select `reg_num`, `store`, `user_name`, `open_receipt`, `remarks`, `date_time`, `created_date` from "+company+".mr_register_openings where reg_num='"+reg_nme+"' ";
									statement.executeUpdate(hist_reg);
									String del_reg="delete from "+company+".mr_register_openings where reg_num='"+reg_nme+"' ";
									statement.executeUpdate(del_reg);
								}
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating History Register Items");
								String reg_validate="select * from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"' ";
								ResultSet resultSet10 = statement.executeQuery(reg_validate);
								if(resultSet10.next())
								{
									String hist_reg="insert into "+company+".mr_register_open_items_history (  `ref_reg_num`, `date_time`, `paycode`, `pay_desc`, `amount`, `created_date`) select `ref_reg_num`, `date_time`, `paycode`, `pay_desc`, `amount`, `created_date` from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"' ";
									statement.executeUpdate(hist_reg);
									String del_reg="delete from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"' ";
									statement.executeUpdate(del_reg);
								}
								ttl_count=65*number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	
								/*
								pay in & out
								customer
								*/
			               	 Thread.sleep(50);
			               	 	lblNewLabel.setText("Updating Customer Payment Status");
								String pay_validate="select * from "+company+".mr_paylist where status='0' and ref_reg_no='"+reg_nme+"' ";
								ResultSet resultSet11 = statement.executeQuery(pay_validate);
								if(resultSet11.next())
								{
									String hist_reg="update "+company+".mr_paylist set status='1' where ref_reg_no='"+reg_nme+"' ";
									statement.executeUpdate(hist_reg);
								}
								String cust_validate="select * from "+company+".mr_customer_payment where status='0' and ref_reg_num='"+reg_nme+"' ";
								ResultSet resultSet12 = statement.executeQuery(cust_validate);
								if(resultSet12.next())
								{
									String hist_reg="update "+company+".mr_customer_payment set status='1' where ref_reg_num='"+reg_nme+"' ";
									statement.executeUpdate(hist_reg);
								}
								ttl_count=70*number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	
								/*
								pos table
								pos items table
								paycode
								update qty
								tax update
								*/
			               	 Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating Invoice History Header");
								String pos_validate="select * from "+company+".mr_pos_settlement_cash where reg_num='"+reg_nme+"' ";
								ResultSet resultSet13 = statement.executeQuery(pos_validate);
								while(resultSet13.next())
								{
									String hist_reg="insert into "+company+".mr_pos_settlement_history ( `reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, `cash_paid`, `type`, `paycode_disc`, `store_id`, `created_date`,grand_total,cust_code,cust_prev_balance,close_date,`mode`, `delivery_user`, `table_num`) values('"+resultSet13.getString("reg_num")+"','"+resultSet13.getString("receipt_no")+"','"+resultSet13.getString("user")+"','"+resultSet13.getString("time")+"','"+resultSet13.getString("total")+"','"+resultSet13.getString("cust_name")+"','"+resultSet13.getString("vat")+"','"+resultSet13.getString("disc")+"',"+ "'"+resultSet13.getString("qty")+"',"+ "'"+resultSet13.getString("cash_paid")+"','"+resultSet13.getString("type")+"','"+resultSet13.getString("paycode_disc")+"','"+resultSet13.getString("store_id")+"','"+resultSet13.getString("created_date")+"','"+resultSet13.getString("grand_total")+"','"+resultSet13.getString("cust_code")+"','"+resultSet13.getString("cust_prev_balance")+"','"+close_date+"','"+resultSet13.getString("mode")+"', '"+resultSet13.getString("delivery_user")+"','"+resultSet13.getString("table_num")+"' )";
									statement1.executeUpdate(hist_reg);
								}
								String pos_validate1="select * from "+company+".mr_pos_settlement_cash where reg_num='"+reg_nme+"' ";
								ResultSet resultSet131 = statement.executeQuery(pos_validate1);
								if(resultSet131.next())
								{
									String del_reg="delete from "+company+".mr_pos_settlement_cash where reg_num='"+reg_nme+"' ";
									statement.executeUpdate(del_reg);
								}
								ttl_count=80*number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 Thread.sleep(50);
			               	 	lblNewLabel.setText("Updating Item Master");
								String pos_itm_update="select barcode,item_code,sum(qty*cf) as ttl_qty from "+company+".mr_pos_settlement_cash_items where ref_reg_num='"+reg_nme+"' group by item_code ";
								ResultSet resultSet114 = statement.executeQuery(pos_itm_update);
								while(resultSet114.next())
								{
									String itemcode=resultSet114.getString("item_code");
									String qty=resultSet114.getString("ttl_qty");
									String itm_validate="select * from "+company+".mr_tmp_item_master where barcode='"+resultSet114.getString("barcode")+"' ";
									ResultSet resultSet15 = statement2.executeQuery(itm_validate);
									if(resultSet15.next())
									{
										String total_qty=resultSet15.getString("qty"); 
										float newqty=Float.parseFloat(total_qty)-(Float.parseFloat(qty));
										String update_temp="update "+company+".mr_tmp_item_master set qty='"+newqty+"' where item_code='"+itemcode+"'  ";
										statement2.executeUpdate(update_temp);
										
										String update_qty="update "+company+".mr_item_master set total_qty='"+newqty+"',modified_date='"+formattedDate+"' where item_code='"+itemcode+"'  ";
										statement2.executeUpdate(update_qty);
									}
								}
								Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating Invoice History Details");
								String pos_itm_validate="select * from "+company+".mr_pos_settlement_cash_items where ref_reg_num='"+reg_nme+"' ";
								ResultSet resultSet14 = statement.executeQuery(pos_itm_validate);
								while(resultSet14.next())
								{
									String hist_reg="insert into "+company+".mr_pos_settlement_history_items (`ref_receipt_num`, `ref_reg_num`, `barcode`, `item_desc`, `item_code`,item_cost, `unit`, `unit_price`, `qty`, `total`, `vat`, `discount`, `created_date`,sp, `vat_type`, `vat_perc`, `disc_type`,cf)	values('"+resultSet14.getString("ref_receipt_num")+"','"+resultSet14.getString("ref_reg_num")+"','"+resultSet14.getString("barcode")+"','"+resultSet14.getString("item_desc")+"','"+resultSet14.getString("item_code")+"','"+resultSet14.getString("item_cost")+"','"+resultSet14.getString("unit")+"','"+resultSet14.getString("unit_price")+"','"+resultSet14.getString("qty")+"','"+resultSet14.getString("total")+"','"+resultSet14.getString("vat")+"','"+resultSet14.getString("discount")+"','"+resultSet14.getString("created_date")+"','"+resultSet14.getString("sp")+"','"+resultSet14.getString("vat_type")+"',	'"+resultSet14.getString("vat_perc")+"','"+resultSet14.getString("disc_type")+"','"+resultSet14.getString("cf")+"')";
									statement1.executeUpdate(hist_reg);
								}
								String pos_itm_validate1="select * from "+company+".mr_pos_settlement_cash_items where ref_reg_num='"+reg_nme+"' ";
								ResultSet resultSet141 = statement.executeQuery(pos_itm_validate1);
								if(resultSet141.next())
								{
									String del_reg="delete from "+company+".mr_pos_settlement_cash_items where ref_reg_num='"+reg_nme+"' ";
									statement2.executeUpdate(del_reg);
								}
								ttl_count=90*number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	Thread.sleep(50);
			               	 	lblNewLabel.setText("Creating Paycode Settlement History");
								String paycode_validate="select * from "+company+".mr_paycode_settlement where ref_reg_num='"+reg_nme+"' ";
								ResultSet resultSet16 = statement.executeQuery(paycode_validate);
								if(resultSet16.next())
								{
									String hist_reg="insert into "+company+".mr_paycode_settlement_history ( `ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`) select `ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date` from "+company+".mr_paycode_settlement where ref_reg_num='"+reg_nme+"' ";
									statement1.executeUpdate(hist_reg);
									
								}
								String paycode_validate1="select * from "+company+".mr_paycode_settlement where ref_reg_num='"+reg_nme+"' ";
								ResultSet resultSet161 = statement.executeQuery(paycode_validate1);
								if(resultSet161.next())
								{
									String del_reg="delete from "+company+".mr_paycode_settlement where ref_reg_num='"+reg_nme+"' ";
									statement1.executeUpdate(del_reg);
								}
								ttl_count=100*number_progress;
								Thread.sleep(50);
								progressBar.paintImmediately(0, 0, 854, 31);
			               	 	progressBar.setValue((int)ttl_count);
			               	 	
								Connection con2=Dbsetup.server.getConnection();
								if(con2!=null)
								{
									new sales_master.close_progress(company, reg_nme, username, store_name);
								}
								 else
								{
									JOptionPane.showMessageDialog(frame, "<html>Daily Sale Closed Successfully<br> „ ≈€·«ﬁ «·»Ì⁄ «·ÌÊ„Ì »‰Ã«Õ</html>");
									frame.setVisible(false);
								}
							    
							}
						    Mainpage.btnCloseSales.setEnabled(true);
						}
					}
				}
	        
	        }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void click_btn(String num)
	{
		try{
			String pass_string=textField_12.getText();
			pass_string+=num;
			textField_12.setText(pass_string);
		}
		catch(Exception e){
			System.err.println(e);
		}
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
				assembly_of_item=resultSet.getString("assembly_of_item");
				DonotPerformEod=resultSet.getString("DonotPerformEod");
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	public void get_register_name(String company, String reg_nme){
		try {
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_nme+"' ");
			String recpt = "";
			int close = 0;
			if(resultSet2.next())
			{
				display_port=resultSet2.getString("pole_display_port");
				not_ready_msg1=resultSet2.getString("ready_msg1");
				not_ready_msg2=resultSet2.getString("ready_msg2");
				printer=resultSet2.getString("printer1");
				close=Integer.parseInt(resultSet2.getString("invoice_number"))+Integer.parseInt(resultSet2.getString("next_num"));
			}
			ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash where reg_num='"+reg_nme+"' order by receipt_no ASC limit 0,1");
			if(resultSet1.next())
			{
				recpt=resultSet1.getString("receipt_no");
			}
			
			
			ResultSet resultSet3 = statement.executeQuery("select * from "+company+".mr_register_controls where ref_register_num='"+reg_nme+"' ");
			if(resultSet3.next())
			{
				denominationPrint=resultSet3.getString("denominationPrint");
				register_all_invoice=resultSet3.getString("register_all_invoice");
				registerAsWaiter=resultSet3.getString("registerAsWaiter");
			}
			
			if(register_all_invoice.equals("1"))
			{
				ResultSet resultSet = statement.executeQuery("select count(*) as ttl_cnt from "+company+".mr_pos_settlement_cash where type!='VOID' ");
				if(resultSet.next())
				{
					lblNewLabel_4.setText(resultSet.getString("ttl_cnt"));
				}
			}
			else
			{
				ResultSet resultSet = statement.executeQuery("select count(*) as ttl_cnt from "+company+".mr_pos_settlement_cash where reg_num='"+reg_nme+"' and type!='VOID' ");
				if(resultSet.next())
				{
					lblNewLabel_4.setText(resultSet.getString("ttl_cnt"));
				}
			}
			label_3.setText(recpt);
			label_5.setText(Integer.toString(close));
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	private void get_full_paycode(String company,String reg_nme)
	{
		try {
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"' ");
			while(resultSet.next())
			{
				String name=resultSet.getString("paycode");
				String pay_desc=resultSet.getString("pay_desc");
				if(!name.equals(""))
				{
					model.addRow(new Object[]{model.getRowCount()+1,name,pay_desc,0});
				}
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	
	public class ClientsTableButtonRenderer extends JButton implements TableCellRenderer
	  {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ClientsTableButtonRenderer()
	    {
	      setOpaque(true);
	    }

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	    {
	      setForeground(Color.black);
	      setBackground(UIManager.getColor("Button.background"));
	      setText((value == null) ? "" : value.toString());
	      return this;
	    }
	  }
	public class ClientsTableRenderer extends DefaultCellEditor
	  {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JButton button;
	    private String label;
	    private boolean clicked;
	    @SuppressWarnings("unused")
		private int row, col;
	    private JTable table;

	    public ClientsTableRenderer(JCheckBox checkBox)
	    {
	      super(checkBox);
	      button = new JButton();
	      button.setOpaque(true);
	      button.addActionListener(new ActionListener()
	      {
	        public void actionPerformed(ActionEvent e)
	        {
	          fireEditingStopped();
	        }
	      });
	    }
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	    {
	      this.table = table;
	      this.row = row;
	      this.col = column;

	      button.setForeground(Color.black);
	      button.setBackground(UIManager.getColor("Button.background"));
	      label = (value == null) ? "" : value.toString();
	      button.setText(label);
	      clicked = true;
	      return button;
	    }
	    public Object getCellEditorValue()
	    {
	      if (clicked)
	      {
	        String val=textField_12.getText();
	        if(!val.equals(""))
	        {
	        	table.setValueAt(Double.parseDouble(val), row, 3);
	        	textField_12.setText("");
	        }
	        else
	        {
	        	JOptionPane.showMessageDialog(frame, "<html>Please Enter Some Values First<br>«·—Ã«¡ ≈œŒ«· »⁄÷ «·ﬁÌ„ √Ê·«</html>");
	        }
	      }
	      clicked = false;
	      return new String(label);
	    }

	    public boolean stopCellEditing()
	    {
	      clicked = false;
	      return super.stopCellEditing();
	    }

	    protected void fireEditingStopped()
	    {
	      super.fireEditingStopped();
	    }
	  }
}
