package sales_master;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

import org.json.JSONException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SecondDisplay {

	public static JFrame frmRegisterNoS;
	static JTable table;
	static JTextField textField_3;
	static JTextField textField_1;
	static JTextField textField_2;
	static JTextField textField_4;
	static JTextField textField_8;
	static String store_credit_validity;
	static DefaultTableModel model;
	static String reg_nme = null;
	static String current_register = null;
	static String old_recipt = "";
	static String promt_print="0";
	static String printer=null;
	public static String printer2=null;
	static String user_name;
	static String barcode_label;
	static String price;
	static String manager_name;
	static JButton btnNewButton;
	private String time=null;
	private static int receipt_num;
	static JTextField textField;
	static JTextField textField_5;
	//private boolean dot = false;
	static String returns="";
	static DecimalFormat df2;
	static int decimalPlaces;
	public static String hold_bill= "0";
	public static String fastitem_Screen="0";
	public static String CustDisc="0";
	public static String ApplyItemDiscount="0";
	public static String change_hold_type="0";
	public static String register_all_invoice="0";
	public static String status;
	public static JPanel panel_11;
	public static String display_port;
	public static String ready_msg1;
	public static String ready_msg2;
	public static String formattedDate;
	public static Calendar cal;
	public static SimpleDateFormat times;
	static JTextField textField_7;
	static JLabel lblNewLabel_1;
	private DefaultTableModel model1;
	public static String VoidSettledinvo="0"; 
	private static JTextField textField_10;
	public static boolean hold_inv=false;
	public static String page_type="Delivery";
	static JTextField textField_11;
	
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice[] gs = ge.getScreenDevices();
    private JPanel panel_1;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new SecondDisplay("","mr_pro","ADMIN","S01","S0101", "","127.0.0.1","",Dbsetup.server.getConnection());
					SecondDisplay.frmRegisterNoS.setVisible(true);
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
	public SecondDisplay(String db_name,String company, String username, String store_name, String posreg,String version,String server,String decimal,Connection con) {
		initialize(db_name,company,username,store_name,posreg, version,server,decimal,con);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 */
	private void initialize(final String db_name,final String company,final String username,final String store_name,final String posreg,final String version,final String server,final String decimal,final Connection con) {
		if(!username.equals(""))
		{
			reg_nme=posreg;
			current_register=posreg;
		}
		else
		{
		}
		
		user_name=username;
		manager_name=username;
		
		
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	    formattedDate = myDateObj.format(myFormatObj);
	    
        times = new SimpleDateFormat("HH:mm:ss");
        
        
        GraphicsDevice gd = gs[1];
		int x = gd.getDefaultConfiguration().getBounds().x;
	    int y = gd.getDefaultConfiguration().getBounds().y;
	    int w = gd.getDefaultConfiguration().getBounds().width;
	    int h = gd.getDefaultConfiguration().getBounds().height;
	    int w_perc = w/100;
		int h_perc = h/100;
		int x_perc = x/100;
		//int y_perc = y/100;
		
	  
	    frmRegisterNoS = new JFrame();
		frmRegisterNoS.setLocation(x, y);
	    frmRegisterNoS.setSize(w, h);
    
	    Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		
		
		new ImageIcon(this.getClass().getResource("/search-icon.png")).getImage();
		frmRegisterNoS.setIconImage(img);
		
		//GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		//int width = gd.getDisplayMode().getWidth();
		//int height = gd.getDisplayMode().getHeight();
		
		
		
		frmRegisterNoS.setForeground(Color.DARK_GRAY);
		frmRegisterNoS.getContentPane().setForeground(Color.DARK_GRAY);
		frmRegisterNoS.getContentPane().setFont(new Font("Arial", Font.BOLD, 40));
		frmRegisterNoS.getContentPane().setBackground(Color.DARK_GRAY);
		//frmRegisterNoS.setBounds(00, 00, width, height);
		frmRegisterNoS.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmRegisterNoS.getContentPane().setLayout(null);
		frmRegisterNoS.setVisible(true);
		//frmRegisterNoS.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frmRegisterNoS.setResizable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(5, h_perc*6, w_perc*62, h_perc*57);
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
		table.setRowHeight( h_perc*3);
		//table.setBorder(new LineBorder(Color.WHITE));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Arial", Font.BOLD, h_perc*2));
		table.setBackground(Color.DARK_GRAY);
		table.setSelectionBackground(Color.red);
		
		
		
		model.addColumn("S.No.");
	    model.addColumn("Barcode"); 
	    model.addColumn("Item Description"); 
	    model.addColumn("Unit"); 
	    model.addColumn("<html>Unit <br> Price</html>");
	    model.addColumn("Qty");
	    model.addColumn("<html>Disc<br>%</html>");
	    model.addColumn("Total");
	    model.addColumn("VAT");
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
	    
	    table.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 10));
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    JTableHeader header=table.getTableHeader();
	    header.setBackground(Color.DARK_GRAY);
	    header.setForeground(Color.WHITE);
	    header.setPreferredSize(new Dimension(100,30));
	    int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(w_perc*4);
			  if (i==1) column.setPreferredWidth(w_perc*9);
			  if (i==2) column.setPreferredWidth(w_perc*13);
			  if (i==3) column.setPreferredWidth(w_perc*5);
			  if (i==4) column.setPreferredWidth(w_perc*4);
			  if (i==5) column.setPreferredWidth(w_perc*5);
			  if (i==6) column.setPreferredWidth(w_perc*5);
			  if (i==7) column.setPreferredWidth(w_perc*6);
			  if (i==8) column.setPreferredWidth(w_perc*5);
			  if (i==9) column.setPreferredWidth(w_perc*6);
			}
		
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 20;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	
                	try {
						new comment_item(company,value);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
		
		
		panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(100, 149, 237), 3, true));
		panel_11.setBackground(Color.DARK_GRAY);
		panel_11.setBounds(5, h_perc*63, w_perc*62, h_perc*32);
		frmRegisterNoS.getContentPane().add(panel_11);
		panel_11.setLayout(null);
		
		JLabel lblRoundOff = new JLabel("Round Off : ");
		lblRoundOff.setForeground(Color.WHITE);
		lblRoundOff.setFont(new Font("Tahoma", Font.PLAIN, (panel_11.getHeight()/100)*8));
		lblRoundOff.setBounds(x_perc*1, h_perc*25, w_perc*9, h_perc*5);
		panel_11.add(lblRoundOff);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setForeground(Color.YELLOW);
		textField_5.setFont(new Font("Arial", Font.BOLD, (panel_11.getHeight()/100)*12));
		textField_5.setColumns(10);
		textField_5.setBorder(null);
		textField_5.setBackground(Color.DARK_GRAY);
		textField_5.setBounds((panel_11.getWidth()/100)*73, h_perc*11, (panel_11.getWidth()/100)*15, h_perc*5);
		panel_11.add(textField_5);
		textField_5.setColumns(10);
		
		textField_3 = new JTextField("0.00");
		textField_3.setEditable(false);
		textField_3.setBorder(null);
		textField_3.setFont(new Font("Arial", Font.BOLD, (panel_11.getHeight()/100)*12));
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setForeground(Color.WHITE);
		textField_3.setBackground(Color.DARK_GRAY);
		textField_3.setBounds((panel_11.getWidth()/100)*73, h_perc*26, (panel_11.getWidth()/100)*30, h_perc*5);
		panel_11.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("GRAND TOTAL");
		lblNewLabel.setForeground(new Color(100, 149, 237));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, (panel_11.getHeight()/100)*8));
		lblNewLabel.setBounds((panel_11.getWidth()/100)*50, h_perc*26, (panel_11.getWidth()/100)*23, h_perc*5);
		panel_11.add(lblNewLabel);
		
		textField_1 = new JTextField("0.00");
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setForeground(Color.WHITE);
		textField_1.setFont(new Font("Arial", Font.BOLD, (panel_11.getHeight()/100)*12));
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBackground(Color.DARK_GRAY);
		textField_1.setBounds((panel_11.getWidth()/100)*73, h_perc*21, (panel_11.getWidth()/100)*30, h_perc*5);
		panel_11.add(textField_1);
		
		JLabel lblTotalQty = new JLabel("VAT");
		lblTotalQty.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalQty.setForeground(new Color(100, 149, 237));
		lblTotalQty.setFont(new Font("Tahoma", Font.BOLD, (panel_11.getHeight()/100)*8));
		lblTotalQty.setBounds((panel_11.getWidth()/100)*50, h_perc*21, (panel_11.getWidth()/100)*23, h_perc*5);
		panel_11.add(lblTotalQty);
		
		textField_2 = new JTextField("0");
		textField_2.setEditable(false);
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setForeground(Color.WHITE);
		textField_2.setFont(new Font("Arial", Font.BOLD, (panel_11.getHeight()/100)*12));
		textField_2.setColumns(10);
		textField_2.setBorder(null);
		textField_2.setBackground(Color.DARK_GRAY);
		textField_2.setBounds((panel_11.getWidth()/100)*88, h_perc*11, (panel_11.getWidth()/100)*15, h_perc*5);
		panel_11.add(textField_2);
		
		JLabel lblTotalItems = new JLabel("<html>TOTAL QTY.</html>");
		lblTotalItems.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalItems.setForeground(new Color(100, 149, 237));
		lblTotalItems.setFont(new Font("Tahoma", Font.BOLD, (panel_11.getHeight()/100)*8));
		lblTotalItems.setBounds((panel_11.getWidth()/100)*50, h_perc, (panel_11.getWidth()/100)*23, h_perc*5);
		panel_11.add(lblTotalItems);
		
		JLabel lblDiscount = new JLabel("DISC");
		lblDiscount.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiscount.setForeground(new Color(100, 149, 237));
		lblDiscount.setFont(new Font("Tahoma", Font.BOLD, (panel_11.getHeight()/100)*8));
		lblDiscount.setBounds((panel_11.getWidth()/100)*50, h_perc*11, (panel_11.getWidth()/100)*23, h_perc*5);
		panel_11.add(lblDiscount);
		
		JLabel lblTotal = new JLabel("SUB TOTAL");
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setForeground(new Color(100, 149, 237));
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, (panel_11.getHeight()/100)*8));
		lblTotal.setBounds((panel_11.getWidth()/100)*50, h_perc*16, (panel_11.getWidth()/100)*23, h_perc*5);
		panel_11.add(lblTotal);
		
		lblNewLabel_1 = new JLabel("0");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(x_perc*7, h_perc*25, w_perc*15, h_perc*5);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, (panel_11.getHeight()/100)*8));
		panel_11.add(lblNewLabel_1);
		
		textField_4 = new JTextField("0");
		textField_4.setEditable(false);
		textField_4.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_4.setForeground(Color.WHITE);
		textField_4.setFont(new Font("Arial", Font.BOLD , (panel_11.getHeight()/100)*12));
		textField_4.setColumns(10);
		textField_4.setBorder(null);
		textField_4.setBackground(Color.DARK_GRAY);
		textField_4.setBounds((panel_11.getWidth()/100)*73, h_perc, (panel_11.getWidth()/100)*30, h_perc*5);
		panel_11.add(textField_4);
		
		textField = new JTextField("0.00");
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Arial", Font.BOLD, (panel_11.getHeight()/100)*12));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(Color.DARK_GRAY);
		textField.setBounds((panel_11.getWidth()/100)*73, h_perc*16, (panel_11.getWidth()/100)*30, h_perc*5);
		panel_11.add(textField);
		
		JLabel lblBeforetotal = new JLabel("TOTAL");
		lblBeforetotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblBeforetotal.setForeground(new Color(100, 149, 237));
		lblBeforetotal.setFont(new Font("Tahoma", Font.BOLD, (panel_11.getHeight()/100)*8));
		lblBeforetotal.setBounds((panel_11.getWidth()/100)*50, h_perc*6, (panel_11.getWidth()/100)*23, h_perc*5);
		panel_11.add(lblBeforetotal);
		
		textField_11 = new JTextField("0.00");
		textField_11.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_11.setForeground(Color.WHITE);
		textField_11.setFont(new Font("Arial", Font.BOLD, (panel_11.getHeight()/100)*12));
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBorder(null);
		textField_11.setBackground(Color.DARK_GRAY);
		textField_11.setBounds((panel_11.getWidth()/100)*73, h_perc*6, (panel_11.getWidth()/100)*30, h_perc*5);
		panel_11.add(textField_11);

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
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBounds(w_perc*63, 0, w_perc*39, h_perc*95);
		frmRegisterNoS.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		
		
		JLabel lblTest = new JLabel("");
		lblTest.setBounds(0, 0, panel.getWidth(), panel.getHeight()/2);
		panel.add(lblTest);
		lblTest.setIcon(new ImageIcon(new ImageIcon("C:\\MasterRetails\\image\\logo.jpg").getImage().getScaledInstance(lblTest.getWidth(), lblTest.getHeight(), Image.SCALE_DEFAULT)));
		
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(w_perc*0, 0, w_perc*63, h_perc*6);
		frmRegisterNoS.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textField_8 = new JTextField(Integer.toString(receipt_num));
		textField_8.setBounds((panel_1.getWidth()/100)*12, (panel_1.getHeight()/100)*100, (panel_1.getWidth()/100)*30, h_perc*5);
		panel_1.add(textField_8);
		textField_8.setEditable(false);
		textField_8.setBackground(Color.DARK_GRAY);
		textField_8.setForeground(Color.GREEN);
		textField_8.setFont(new Font("Arial", Font.BOLD, (panel_1.getWidth()/100)*3));
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setColumns(10);
		
		JLabel lblReceiptNo_1 = new JLabel("RECEIPT #:");
		lblReceiptNo_1.setBounds(0, (panel_1.getHeight()/100)*100, (panel_1.getWidth()/100)*12, h_perc*5);
		panel_1.add(lblReceiptNo_1);
		lblReceiptNo_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblReceiptNo_1.setForeground(Color.WHITE);
		
		JLabel lblCustomer = new JLabel("CUSTOMER:");
		lblCustomer.setBounds((panel_1.getWidth()/100)*55, (panel_1.getHeight()/100)*100, (panel_1.getWidth()/100)*15, h_perc*5);
		panel_1.add(lblCustomer);
		lblCustomer.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCustomer.setForeground(Color.WHITE);
		
		btnNewButton = new JButton("WALK IN");
		btnNewButton.setBounds((panel_1.getWidth()/100)*68, (panel_1.getHeight()/100)*100, (panel_1.getWidth()/100)*32, h_perc*5);
		panel_1.add(btnNewButton);
		btnNewButton.setForeground(new Color(100, 149, 237));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					new sales_master.Cust_Chooser( company,"pos1", username, store_name,"","", version,"1");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(Color.DARK_GRAY);
		
		final DateFormat timeFormat = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss a");
        ActionListener timerListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Date date = new Date();
                time = timeFormat.format(date);
                frmRegisterNoS.setTitle("REGISTER NO:"+reg_nme+"  DATE:"+time+"  USER:"+manager_name+"   DATABASE:"+company+"   HOST:"+server+"  STATUS:"+status);
            }
        };
        Timer timer = new Timer(1000, timerListener);
        timer.setInitialDelay(0);
        timer.start();
        
        try {
        	Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+SecondDisplay.ready_msg1+">"+display_port+"&&ECHO "+SecondDisplay.ready_msg2+">"+display_port+"\"");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
	}
}