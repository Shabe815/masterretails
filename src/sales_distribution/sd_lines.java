package sales_distribution;

import inventory_master.item_list_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import CafePro.Mainpage;

import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JDateChooser;

import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class sd_lines {

	static JFrame frame;
	private String supp_name;
	private JTextField textField_2;
	public static JTextField textField_3;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private static DefaultTableModel model;
	private static DefaultTableModel model_1;
	public static JTable table;
	private static JTable table_1;
	private JDateChooser dateChooser ;
	private JLabel label_1;
	private JButton btnSearch;
	private JButton btnProceed;
	private JMenuItem menuItem_1;
	private JMenuItem menuItem_3;
	private JButton button;
	private JButton btnNewButton;
	private JButton btnNew;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane;
	private JTextField textField_12;
	public static JTextField textField_4;
	private static JTextField textField_14;
	private static JTextField textField_15;
	private static JTextField textField_16;
	private static JTextField textField_18;
	static JTextField textField_19;
	private static JTextField textField_20;
	private static JTextField textField_21;
	private static JTextField textField_22;
	private static JTextField textField_23;
	private static JTextField textField_24;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private static JComboBox<String> comboBox_2;
	private static JComboBox<String> comboBox ;
	private boolean dot=false;
	@SuppressWarnings("unused")
	private boolean minus=false;
	private JButton btnprint;
	private JButton btnDelete;
	private static JTextField textField_17;
	private JMenuItem menuItem_2;
	//private JButton btnNewButton;
	private JButton btnClear;
	private static int decimal;
	private static JTextField textField_25;
	private JTextField textField_27;
	private static JTextField textField_28;
	private JLabel lblRemark_1;
	private JTextField textField_5;
	public static JTextField textField_6;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JButton btnCustomerItemHistory;
	private static JComboBox<String> comboBox_1;
	private static JComboBox<String> comboBox_3;
	private JCheckBox chckbxAssumePriceAs;
	public static JTextField textField_7;
	private JLabel lblNewLabel;
	private static JTextField textField_13;
	private JComboBox <String> comboBox_4;
	static String sellingprice="0"; 
	private static JTextField textField_26;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					sd_lines window = new sd_lines("mr_pro","admin","001","IN-20-01-000003","S01", "","2","" );
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public sd_lines(String db_name, String username,String cust_id, String po_num, String store_name,String version,String decimal,String language) throws JSONException, SQLException {
		initialize(db_name,username,cust_id,po_num, store_name, version,decimal,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param db_name 
	 * @param language 
	 * @param host_name 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String db_name,final String username,final String cust_id,String po_num, final String store_name,final String version,final String decimals,final String language) throws JSONException, SQLException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(10, 10, 975, 768);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		if(!decimals.equals(""))
		{
			decimal=Integer.parseInt(decimals);
		}
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 968, 38);
		frame.getContentPane().add(panel);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		JLabel lblPurchaseOrder = new JLabel("Sales And Distribution");
		lblPurchaseOrder.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPurchaseOrder.setBounds(12, 6, 228, 30);
		panel.add(lblPurchaseOrder);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sd_list.btnNewButton.setEnabled(true);
				//purchase_return_list.frame.setAlwaysOnTop(true);
				int rows=model.getRowCount();
				if(rows>0)
				{
					int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Exit","Warning",JOptionPane.YES_NO_OPTION);
			        // 0=yes, 1=no, 2=cancel
			        if(cofrm_bx==0)
			        {
			        	try{
							String ref=textField_27.getText();
							String po_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
							String cust_name=textField_3.getText();
							String custaddr=textArea_1.getText();
							String remark=textArea.getText();
							LocalDateTime myDateObj = LocalDateTime.now();  
						    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
						    String formattedDate = myDateObj.format(myFormatObj);  
						    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						    String podate = null;
						    if(!po_date.equals(""))
							{
								podate = sdf.format(dateChooser.getDate());
							}
						    Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="SELECT * FROM "+db_name+".mr_sd_list where invoice_num='"+textField_2.getText()+"' "; 
							ResultSet resultSet = statement.executeQuery(query);
							if(resultSet.next())
							{
								String query3="update "+db_name+".mr_sd_list set paymentmethod='"+comboBox.getSelectedItem().toString()+"',remark='"+remark+"',date='"+podate+"',total_amt='"+textField.getText()+"',disc='"+textField_12.getText()+"',total_vat='"+textField_9.getText()+"',cust_id='"+textField_6.getText().trim()+"',cust_addr='"+custaddr+"',cust_name='"+cust_name+"', status='OPEN',store_name='"+store_name+"',referance='"+ref+"',modified_date='"+formattedDate+"' where invoice_num='"+textField_2.getText()+"'";
								statement.executeUpdate(query3);
							}
							con.close();
						}
						catch(Exception e)
						{
							JOptionPane.showMessageDialog(frame, e.getMessage());
						}
			        	frame.setVisible(false);
			        	sales_distribution.sd_list.get_po_disp(db_name, "OPEN", "",sd_list.comboBox.getSelectedItem().toString());
			        }
			        
				}
				else
				{
					frame.setVisible(false);
					sales_distribution.sd_list.get_po_disp(db_name, "OPEN", "",sd_list.comboBox.getSelectedItem().toString());
					
				}
				
			}
		});
		menuItem.setBorder(null);
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(733, 6, 60, 30);
		panel.add(menuItem);
		
		menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//((JTextField) dateChooser.getDateEditor().getUiComponent()).setText("");
				//textArea.setText("");
				//textArea_1.setText("");
				//textField_3.setText("");
				//dateChooser.setDate(new Date());
				getcostupdate();
				get_item_dt(db_name,textField_2.getText(),decimal);
			}

			private void getcostupdate() {

				try {
				    Connection con = Dbsetup.dbconn.getConnection();

				    String query =
				        "UPDATE " + db_name + ".mr_sd_lines s " +
				        "JOIN " + db_name + ".mr_tmp_item_master i " +
				        "ON s.item_code = i.item_code " +
				        "SET s.item_cost = s.qty * s.cf * i.cost " +
				        "WHERE s.ref_inv_num = ?";

				    java.sql.PreparedStatement ps = con.prepareStatement(query);

				    ps.setString(1, textField_2.getText());

				    ps.executeUpdate();

				    ps.close();
				    con.close();

				} catch (Exception e) {
				    JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(794, 6, 80, 30);
		panel.add(menuItem_1);
		
		menuItem_3 = new JMenuItem("Print");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					frame.setAlwaysOnTop(false);
					try{
						String ref=textField_27.getText();
						String po_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
						String cust_name=textField_3.getText();
						String custaddr=textArea_1.getText();
						String remark=textArea.getText();
						LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);  
					    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					    String podate = null;
					    if(!po_date.equals(""))
						{
							podate = sdf.format(dateChooser.getDate());
						}
					    Statement statement=con.createStatement();  
						String query="SELECT * FROM "+db_name+".mr_sd_list where invoice_num='"+textField_2.getText()+"' "; 
						ResultSet resultSet = statement.executeQuery(query);
						if(resultSet.next())
						{
							String query3="update "+db_name+".mr_sd_list set paymentmethod='"+comboBox.getSelectedItem().toString()+"',remark='"+remark+"',date='"+podate+"',total_amt='"+textField.getText()+"',disc='"+textField_12.getText()+"',total_vat='"+textField_9.getText()+"',cust_id='"+textField_6.getText().trim()+"',cust_addr='"+custaddr+"',cust_name='"+cust_name+"', status='OPEN',store_name='"+store_name+"',referance='"+ref+"',modified_date='"+formattedDate+"' where invoice_num='"+textField_2.getText()+"'";
							statement.executeUpdate(query3);
						}
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(frame, e.getMessage());
					}
					double ttlamtword=Double.parseDouble(textField_10.getText());
					Float num = new Float( ttlamtword ) ;
					int dollars = (int)Math.floor( num ) ;
					//floor cent = (int)Math.floor( ( num - dollars ) * 100.00 ) ;
					
					double cent=(num-dollars)*100;
					BigDecimal bd = new BigDecimal(cent);
	        		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	        		cent = bd.doubleValue();
					String amountinwords = numbertowords.convert( dollars ) + " and " +numbertowords.convert( cent ) + " Fils Only" ;
					JComboBox <String> comboBox = new JComboBox <String> ();
					comboBox.setFont(new Font("Arial", Font.PLAIN, 24));
					String invo_num=textField_2.getText();
					String[] invo_num_split=invo_num.split("-");
					String invo_type=invo_num_split[0];
					if(invo_type.equals("IN"))
					{
//						comboBox.addItem("SampleInvoice");
//						comboBox.addItem("Invoice");
//						comboBox.addItem("TaxInvoice");
//						comboBox.addItem("GRVInvoice");
//						comboBox.addItem("DotSampleInvoice");
//						comboBox.addItem("DotInvoice");
//						comboBox.addItem("DotTaxInvoice");
						
						comboBox.addItem("SDinvoice1");
						comboBox.addItem("SDinvoice2");
						comboBox.addItem("SDinvoice3");
					}
					else
					{
						comboBox.addItem("SDquotation1");
						comboBox.addItem("SDquotation2");
						comboBox.addItem("SDquotation3");
					}
					
					
					JOptionPane.showMessageDialog(frame, comboBox, "Select Report Format", JOptionPane.QUESTION_MESSAGE);
					
					report_master.sd_report.report(textField_2.getText(),con, "OPEN",amountinwords,comboBox.getSelectedItem().toString(),db_name);
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
		menuItem_3.setBorder(null);
		menuItem_3.setIcon(new ImageIcon(printer));
		menuItem_3.setBackground(new Color(243, 229, 216));
		menuItem_3.setBounds(652, 6, 80, 30);
		panel.add(menuItem_3);
		
		menuItem_2 = new JMenuItem("Save");
		menuItem_2.setEnabled(false);
		menuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String ref=textField_27.getText();
					String po_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String cust_name=textField_3.getText();
					String custaddr=textArea_1.getText();
					String remark=textArea.getText();
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				    String podate = null;
				    if(!po_date.equals(""))
					{
						podate = sdf.format(dateChooser.getDate());
					}
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement();  
					String query="SELECT * FROM "+db_name+".mr_sd_list where invoice_num='"+textField_2.getText()+"' "; 
					ResultSet resultSet = statement.executeQuery(query);
					if(resultSet.next())
					{
						String query3="update "+db_name+".mr_sd_list set paymentmethod='"+comboBox.getSelectedItem().toString()+"', remark='"+remark+"',date='"+podate+"',total_amt='"+textField.getText()+"',disc='"+textField_12.getText()+"',total_vat='"+textField_9.getText()+"',cust_id='"+textField_6.getText().trim()+"',cust_addr='"+custaddr+"',cust_name='"+cust_name+"', status='OPEN',store_name='"+store_name+"',referance='"+ref+"',modified_date='"+formattedDate+"',salesman='"+comboBox_4.getSelectedItem().toString()+"' where invoice_num='"+textField_2.getText()+"'";
						statement.executeUpdate(query3);
						JOptionPane.showMessageDialog(frame, "Saved Successfully");
					}
					con.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(873, 6, 138, 30);
		panel.add(menuItem_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setBounds(0, 39, 969, 694);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(243, 229, 216));
		panel_2.setBounds(0, 0, 281, 84);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 4, 70, 16);
		panel_2.add(lblDate);
		
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(10, 24, 70, 16);
		panel_2.add(lblUserName);
		
		JLabel lblPoNo = new JLabel("Invoice Number");
		lblPoNo.setBounds(10, 44, 92, 16);
		panel_2.add(lblPoNo);
		
		textField_2 = new JTextField("NEW");
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(114, 44, 164, 16);
		panel_2.add(textField_2);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(90, 4, 188, 16);
		//dateChooser.setMinSelectableDate(new Date());
		dateChooser.setDate(new Date());
		panel_2.add(dateChooser);
		
		label_1 = new JLabel(username);
		label_1.setBounds(100, 24, 188, 16);
		panel_2.add(label_1);
		
		JLabel lblReferance = new JLabel("Referance");
		lblReferance.setBounds(10, 64, 70, 16);
		panel_2.add(lblReferance);
		
		textField_27 = new JTextField();
		textField_27.setBounds(95, 64, 174, 16);
		panel_2.add(textField_27);
		textField_27.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(243, 229, 216));
		panel_3.setLayout(null);
		panel_3.setBounds(282, 0, 357, 84);
		panel_1.add(panel_3);
		
		JLabel lblSuppDetails = new JLabel("Cust. Name");
		lblSuppDetails.setBounds(12, 9, 77, 16);
		panel_3.add(lblSuppDetails);
		
		textField_3 = new JTextField();
		textField_3.setBounds(81, 7, 168, 16);
		panel_3.add(textField_3);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		button = new JButton("");
		button.setBounds(249, 3, 35, 22);
		panel_3.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setAlwaysOnTop(false);
					new sales_master.Cust_Chooser( db_name,"s&d", store_name, store_name,"","", version,"1");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(serch));
		
		
		btnNew = new JButton("New");
		btnNew.setBounds(284, 5, 60, 18);
		panel_3.add(btnNew);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setAlwaysOnTop(false);
				new sales_master.Customer_master(db_name, username,"",store_name, version,"", language);
			}
		});
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(12, 26, 56, 16);
		panel_3.add(lblAddress);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(81, 26, 263, 34);
		panel_3.add(scrollPane_2);
		
		textArea_1 = new JTextArea();
		scrollPane_2.setViewportView(textArea_1);
		textArea_1.setEditable(false);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		
		JLabel lblPaymentMethod = new JLabel("Payment Method");
		lblPaymentMethod.setBounds(0, 68, 95, 16);
		panel_3.add(lblPaymentMethod);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(107, 65, 226, 22);
		comboBox.addItem("CASH");
		comboBox.addItem("CREDIT");
		panel_3.add(comboBox);
		
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(243, 229, 216));
		panel_4.setLayout(null);
		panel_4.setBounds(638, 0, 319, 84);
		panel_1.add(panel_4);
		
		JLabel lblRemark = new JLabel("Remark");
		lblRemark.setBounds(12, 4, 56, 16);
		panel_4.add(lblRemark);
		
		JButton btnItemMaster = new JButton("ITEM MASTER");
		btnItemMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new inventory_master.Item_master(db_name,db_name,  "", username, "","","", version,store_name,2,language);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//"mr_pro", "","admin","","","", null
			}
		});
		btnItemMaster.setBounds(454, 5, 116, 25);
		panel.add(btnItemMaster);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(80, 1, 227, 50);
		panel_4.add(scrollPane_3);
		
		textArea = new JTextArea();
		scrollPane_3.setViewportView(textArea);
		
		JLabel lblSalesman = new JLabel("Salesman");
		lblSalesman.setBounds(12, 59, 69, 14);
		panel_4.add(lblSalesman);
		
		comboBox_4 = new JComboBox<String>();
		comboBox_4.setBounds(80, 56, 227, 20);
		panel_4.add(comboBox_4);
		
		get_user(db_name);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.setBounds(12, 221, 933, 171);
		panel_1.add(scrollPane);
		
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		      
		       return false;
		    }
			
		};
		table = new JTable(model);
	    table.setFont(new Font("Serif", Font.BOLD, 15));
		table.setRowHeight(20);
		table.setEnabled(true);
	    model.addColumn("S.NO"); 
	    model.addColumn("Barcode"); 
		model.addColumn("Item Code"); 
		model.addColumn("Item Details"); 
		model.addColumn("Unit"); 
		model.addColumn("cf"); 
		model.addColumn("Qty"); 
		model.addColumn("Selling Price"); 
		model.addColumn("Item Discount(%)"); 
		model.addColumn("Total");
		model.addColumn("Tax Value");
		model.addColumn("Net Amt");
		model.addColumn("Tax Rate"); 
		model.addColumn("Tax Type"); 
		model.addColumn("Remark");
		model.addColumn("Details");
		model.addColumn("Total Cost");
		model.addColumn("id");
		model.addColumn("Margin");
		model.addColumn("Margin%");
		scrollPane.setViewportView(table);
		
		table.setFocusable(false);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int colmns=model.getColumnCount();
		for (int i=0; i<colmns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(35);
		  if (i==1) column.setPreferredWidth(110);
		  if (i==2) column.setPreferredWidth(75);
		  if (i==3) column.setPreferredWidth(170);
		  if (i==4) column.setPreferredWidth(30);
		  if (i==5) column.setPreferredWidth(50);
		  if (i==6) column.setPreferredWidth(30);
		  if (i==7) column.setPreferredWidth(70);
		  if (i==8) column.setPreferredWidth(75);
		  if (i==9) column.setPreferredWidth(80);
		  if (i==10) column.setPreferredWidth(70);
		  if (i==11) column.setPreferredWidth(55);
		  if (i==12) column.setPreferredWidth(75);
		  if (i==13) column.setPreferredWidth(75);
		  if (i==14) column.setPreferredWidth(75);
		}
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table_2 = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table_2.getSelectedRow() != -1) {
					int rows = table_2.getSelectedRow();
					String val=table_2.getModel().getValueAt(rows, 4).toString();
					get_item_barcode_list(db_name,table_2.getModel().getValueAt(rows, 2).toString(),val);
					
					textField_17.setText(table_2.getModel().getValueAt(rows, 1).toString());
					textField_15.setText(table_2.getModel().getValueAt(rows, 2).toString());
					textField_16.setText(table_2.getModel().getValueAt(rows, 3).toString());
					
					comboBox_2.setSelectedItem(table_2.getModel().getValueAt(rows, 4));
					textField_18.setText(table_2.getModel().getValueAt(rows, 5).toString());
					textField_14.setText(table_2.getModel().getValueAt(rows, 6).toString());
					textField_19.setText(table_2.getModel().getValueAt(rows, 7).toString());
					sellingprice=table_2.getModel().getValueAt(rows, 7).toString();
					textField_20.setText(table_2.getModel().getValueAt(rows, 8).toString());
					textField_21.setText(table_2.getModel().getValueAt(rows, 9).toString());
					textField_22.setText(table_2.getModel().getValueAt(rows, 12).toString());
					textField_23.setText(table_2.getModel().getValueAt(rows, 17).toString());
					comboBox_1.setSelectedItem(table_2.getModel().getValueAt(rows, 13).toString());
					textField_24.setText(table_2.getModel().getValueAt(rows, 10).toString());
					textField_5.setText(table_2.getModel().getValueAt(rows, 14).toString());
					textField_7.setText(table_2.getModel().getValueAt(rows, 15).toString());
					double cost=Double.parseDouble(table_2.getModel().getValueAt(rows, 16).toString());
					double qty=Double.parseDouble(table_2.getModel().getValueAt(rows, 6).toString());
					double cf=Double.parseDouble(table_2.getModel().getValueAt(rows, 5).toString());
					double unitcost=cost/(qty*cf);
					textField_13.setText(Double.toString(unitcost));
					textField_4.setText(table_2.getModel().getValueAt(rows, 1).toString());
					btnDelete.setEnabled(false);
				}
			}
		});
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(709, 391, 236, 139);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 10, 70, 22);
		panel_5.add(lblTotal);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setText("0.0");
		textField.setEditable(false);
		textField.setBounds(82, 10, 134, 22);
		panel_5.add(textField);
		textField.setColumns(10);
		
		JLabel lblDiscount = new JLabel("Discount %");
		lblDiscount.setBounds(10, 37, 70, 22);
		panel_5.add(lblDiscount);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10)
		        {	
					String total=textField.getText();
                	String disc_per=textField_1.getText();
                	double txval = 0;
                	for(int i=0; i<model.getRowCount();i++)
                	{
                		txval+=Double.parseDouble(model.getValueAt(i, 10).toString());
                	}
                	if(!disc_per.equals(""))
                	{
                		double ttl_per=(Double.parseDouble(total)*Double.parseDouble(disc_per))/100;
                		if(ttl_per>Double.parseDouble(total))
                		{
                			JOptionPane.showMessageDialog(frame, "Enter Amount is Greater than Total Amount");
                			
            				textField_12.setText("");
                			textField_1.setText("0");
                			textField_12.setText("");
                			textField_1.setText("0");
                			textField_8.setText(total);
                			BigDecimal bv = new BigDecimal(txval);
                    		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
                    		txval = bv.doubleValue();
                			textField_9.setText(Double.toString(txval));
                			textField_10.setText(Double.toString(Double.parseDouble(total)+txval));
                			
                		}
                		else
                		{
                			BigDecimal bd = new BigDecimal(ttl_per);
                    		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
                    		ttl_per = bd.doubleValue();
                    		textField_12.setText(Double.toString(ttl_per));
                    		
                    		double subttl=Double.parseDouble(total) - ttl_per;
                    		BigDecimal bs = new BigDecimal(subttl);
                    		bs = bs.setScale(decimal, BigDecimal.ROUND_HALF_UP);
                    		subttl = bs.doubleValue();
                    		textField_8.setText(Double.toString(subttl));
                    		
                    		double grand_vat=(txval-((txval*Double.parseDouble(disc_per))/100)) ;
                    		BigDecimal bv = new BigDecimal(grand_vat);
                    		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
                    		grand_vat = bv.doubleValue();
                    		textField_9.setText(Double.toString(grand_vat));
                    		
                    		double grnd_ttl=subttl + grand_vat;
                    		BigDecimal bg = new BigDecimal(grnd_ttl);
                    		bg = bg.setScale(decimal, BigDecimal.ROUND_HALF_UP);
                    		grnd_ttl = bg.doubleValue();
                    		textField_10.setText(Double.toString(grnd_ttl));
                		}
                		
                		
                	}
                }
			}
		});
		textField_1.setEditable(false);
		textField_1.setBounds(82, 37, 62, 22);
		panel_5.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSubTotal = new JLabel("Sub Total");
		lblSubTotal.setBounds(10, 64, 70, 22);
		panel_5.add(lblSubTotal);
		
		textField_8 = new JTextField();
		textField_8.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_8.setText("0.0");
		textField_8.setEditable(false);
		textField_8.setBounds(82, 64, 134, 22);
		panel_5.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblVat = new JLabel("Vat");
		lblVat.setBounds(10, 91, 70, 22);
		panel_5.add(lblVat);
		
		textField_9 = new JTextField();
		textField_9.setText("0.0");
		textField_9.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_9.setEditable(false);
		textField_9.setBounds(82, 91, 134, 22);
		panel_5.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblGrandTotal = new JLabel("Grand Total");
		lblGrandTotal.setBounds(10, 118, 70, 22);
		panel_5.add(lblGrandTotal);
		
		textField_10 = new JTextField();
		textField_10.setText("0.0");
		textField_10.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_10.setEditable(false);
		textField_10.setBounds(82, 118, 135, 22);
		panel_5.add(textField_10);
		textField_10.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setText("0");
		textField_12.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_12.setEditable(false);
		textField_12.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10)
		        {
					String total=textField.getText();
                	String disc_per=textField_12.getText();
                	double txval = 0;
                	for(int i=0; i<model.getRowCount();i++)
                	{
                		txval+=Double.parseDouble(model.getValueAt(i, 10).toString());
                	}
                	if(!disc_per.equals(""))
                	{
                		if(Double.parseDouble(disc_per)>Double.parseDouble(total))
                		{
                			JOptionPane.showMessageDialog(frame, "Enter Amount is Greater than Total Amount");
                			textField_12.setText("");
                			textField_1.setText("0");
                			textField_8.setText(total);
                			textField_9.setText(Double.toString(txval));
                			textField_10.setText(Double.toString(Double.parseDouble(total)+txval));
                		}
                		else
                		{
                			double ttl_per=(Double.parseDouble(disc_per)*100)/Double.parseDouble(total);
                    		BigDecimal bd = new BigDecimal(ttl_per);
                    		bd = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
                    		ttl_per = bd.doubleValue();
                    		textField_1.setText(Double.toString(ttl_per));
                    		
                    		double subttl=Double.parseDouble(total) - Double.parseDouble(disc_per);
                    		BigDecimal bs = new BigDecimal(subttl);
                    		bs = bs.setScale(decimal, BigDecimal.ROUND_HALF_UP);
                    		subttl = bs.doubleValue();
                    		textField_8.setText(Double.toString(subttl));
                    		
                    		double grand_vat=(txval-((txval*ttl_per)/100)) ;
                    		BigDecimal bv = new BigDecimal(grand_vat);
                    		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
                    		grand_vat = bv.doubleValue();
                    		textField_9.setText(Double.toString(grand_vat));
                    		
                    		double grnd_ttl=subttl + grand_vat;
                    		BigDecimal bg = new BigDecimal(grnd_ttl);
                    		bg = bg.setScale(decimal, BigDecimal.ROUND_HALF_UP);
                    		grnd_ttl = bg.doubleValue();
                    		textField_10.setText(Double.toString(grnd_ttl));
                		}
                		
                		
                	}
		        }
			}
		});
		textField_12.setBounds(146, 37, 70, 22);
		panel_5.add(textField_12);
		textField_12.setColumns(10);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBackground(new Color(243, 229, 216));
		scrollPane_1.setBounds(12, 532, 933, 151);
		panel_1.add(scrollPane_1);
		
		model_1= new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		   
		};
		table_1 = new JTable(model_1);
		table_1.setFont(new Font("Serif", Font.BOLD, 15));
		table_1.setRowHeight(20);
		table_1.setEnabled(true);
			model_1.addColumn("S.NO"); 
			model_1.addColumn("Item Code"); 
			model_1.addColumn("Item Name"); 
			model_1.addColumn("Barcode");
			model_1.addColumn("Dept"); 
			model_1.addColumn("Category"); 
			model_1.addColumn("Item Type"); 
			model_1.addColumn("Item Cost");
			model_1.addColumn("Selling price");
			model_1.addColumn("QtyOH"); 
			
		scrollPane_1.setViewportView(table_1);
		
		int columns=model_1.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table_1.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(10);
			  if (i==1) column.setPreferredWidth(50);
			  if (i==2) column.setPreferredWidth(150);
			  if (i==3) column.setPreferredWidth(50);
			  if (i==4) column.setPreferredWidth(50);
			  if (i==5) column.setPreferredWidth(50);
			  if (i==6) column.setPreferredWidth(10);
			  if (i==7) column.setPreferredWidth(10);
			  if (i==8) column.setPreferredWidth(10);
			  if (i==9) column.setPreferredWidth(10);
			}
		
		
		
		table_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table_2 = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table_2.getSelectedRow() != -1) {
					int column = 3;
					int rows = table_2.getSelectedRow();
					String value = table_2.getModel().getValueAt(rows, column).toString();
					if(table_2.getModel().getValueAt(rows, 3).toString().equals(""))
					{
						JOptionPane.showMessageDialog(frame, "This Item Doesnt Have A Barcode");
					}
					else
					{
						validate_barcode(db_name, value, supp_name, username, version,store_name,language);
					}
				}
			}
		});
		
		textField_11 = new JTextField();
		textField_11.getDocument().addDocumentListener(new DocumentListener() {
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
				 
					get_ven_items(db_name,textField_6.getText(),textField_11.getText(), "");
			  }
			});
		textField_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_ven_items(db_name,textField_6.getText(),textField_11.getText(), "");
			}
		});
		textField_11.setEditable(false);
		textField_11.setBounds(12, 500, 407, 25);
		panel_1.add(textField_11);
		textField_11.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setEnabled(false);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_ven_items(db_name,textField_6.getText(),textField_11.getText(), "");
			}
		});
		btnSearch.setBounds(421, 499, 97, 25);
		panel_1.add(btnSearch);
		
		JLabel lblVendorItemList = new JLabel("CUSTOMER ITEMS LIST");
		lblVendorItemList.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVendorItemList.setBounds(12, 436, 184, 22);
		panel_1.add(lblVendorItemList);
		
		
		
		btnNewButton = new JButton("");
		btnNewButton.setEnabled(false);
		btnNewButton.setToolTipText("Item Code List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Connection con=Dbsetup.dbconn.getConnection();
					new item_list_master(db_name,db_name, "sd", "", version,decimal,con,"",language);
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(8, 112, 28, 21);
		panel_1.add(btnNewButton);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	if (e.getKeyCode() == KeyEvent.VK_TAB) {
                	JOptionPane.showMessageDialog(frame, "Please Enter Barcode or Itemcode values");
					textField_4.requestFocus();;
                }
            }
        });
		textField_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.getSelectionModel().clearSelection();
				validate_barcode(db_name, textField_4.getText(),textField_3.getText(), username, version,store_name,language);
			}
		});
		textField_4.setBounds(44, 111, 168, 22);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
//		textField_4.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyTyped(KeyEvent e) {
//				char vChar = e.getKeyChar();
//			    if (textField_4.getText().equals(""))
//			        dot = false;
//			    if (dot == false){
//			        if (vChar == '.') dot = true;
//			        else if (!(Character.isDigit(vChar)
//			                || (vChar == KeyEvent.VK_BACK_SPACE)
//			                || (vChar == KeyEvent.VK_DELETE))) {
//			                e.consume();
//			        }
//			    } else {
//			        if (!(Character.isDigit(vChar)
//			                || (vChar == KeyEvent.VK_BACK_SPACE)
//			                || (vChar == KeyEvent.VK_DELETE))) {
//			                e.consume();
//			        }
//			    }
//			}
//		});
		
		JLabel lblBarcodeitemcode = new JLabel("Barcode/Itemcode");
		lblBarcodeitemcode.setBounds(10, 86, 202, 22);
		panel_1.add(lblBarcodeitemcode);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setBounds(246, 136, 70, 22);
		panel_1.add(lblQty);
		
		textField_14 = new JTextField();
		textField_14.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	textField_19.requestFocus();
				}
			}
		});
		
		textField_14.setEditable(false);
		textField_14.setBounds(246, 157, 70, 22);
		panel_1.add(textField_14);
		textField_14.setColumns(10);
		textField_14.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_14.getText().equals(""))
			        dot = false;
			    	minus = false;
			    if (dot == false){
			        if (vChar == '.') dot = true;
			        else if (vChar == '-') minus = true;
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
		
		JLabel lblItemcode = new JLabel("Itemcode");
		lblItemcode.setBounds(222, 86, 116, 22);
		panel_1.add(lblItemcode);
		
		textField_15 = new JTextField();
		textField_15.setEditable(false);
		textField_15.setBounds(220, 112, 116, 22);
		panel_1.add(textField_15);
		textField_15.setColumns(10);
		
		JLabel lblItemDetails = new JLabel("Item Details");
		lblItemDetails.setBounds(348, 86, 84, 22);
		panel_1.add(lblItemDetails);
		
		JLabel lblUnit = new JLabel("unit");
		lblUnit.setBounds(816, 86, 95, 22);
		panel_1.add(lblUnit);
		
		JLabel lblCf = new JLabel("CF");
		lblCf.setBounds(921, 86, 38, 22);
		panel_1.add(lblCf);
		
		JLabel lblUnitCost = new JLabel("Selling Price");
		lblUnitCost.setBounds(327, 136, 74, 22);
		panel_1.add(lblUnitCost);
		
		JLabel lblItemDisc = new JLabel("Item Disc%");
		lblItemDisc.setBounds(412, 136, 74, 22);
		panel_1.add(lblItemDisc);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setBounds(497, 136, 101, 22);
		panel_1.add(lblTotal_1);
		
		JLabel lblTaxRate = new JLabel("Tax Rate");
		lblTaxRate.setBounds(11, 136, 71, 22);
		panel_1.add(lblTaxRate);
		
		JLabel lblTotalTax = new JLabel("Tax Type");
		lblTotalTax.setBounds(93, 136, 61, 22);
		panel_1.add(lblTotalTax);
		
		textField_16 = new JTextField();
		textField_16.setEditable(false);
		textField_16.setBounds(376, 112, 423, 22);
		panel_1.add(textField_16);
		textField_16.setColumns(10);
		
		JLabel lblTaxVal = new JLabel("Tax Val");
		lblTaxVal.setBounds(609, 136, 81, 22);
		panel_1.add(lblTaxVal);
		
		textField_18 = new JTextField();
		textField_18.setEditable(false);
		textField_18.setColumns(10);
		textField_18.setBounds(915, 111, 41, 22);
		panel_1.add(textField_18);
		
		textField_19 = new JTextField();
		textField_19.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(chckbxAssumePriceAs.isSelected()){
					String qty=textField_14.getText();
					double unit_cost=Double.parseDouble(textField_19.getText());
    				if(unit_cost==0)
    				{
    					unit_cost=0;
    				}
    				
    				double itm_cost=unit_cost/Double.parseDouble(qty);
    				BigDecimal bd = new BigDecimal(itm_cost);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		itm_cost = bd.doubleValue();
    				textField_19.setText(Double.toString(itm_cost));
//    				if(Double.parseDouble(sellingprice)<= Double.parseDouble(textField_19.getText().trim()) )
//			    	{
//			    		textField_20.requestFocus();	
//			    	}
//			    	else
//			    	{
//			    		JOptionPane.showMessageDialog(frame, "Check Selling Price");
//			    		return;
//			    	}
    			}
			}
		});
		textField_19.setEditable(false);
		textField_19.setColumns(10);
		textField_19.setBounds(327, 157, 74, 22);
		panel_1.add(textField_19);
		textField_19.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_19.getText().equals(""))
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
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	textField_20.requestFocus();
//			    	if(Double.parseDouble(sellingprice)<= Double.parseDouble(textField_19.getText().trim()) )
//			    	{
//			    		textField_20.requestFocus();	
//			    	}
//			    	else
//			    	{
//			    		JOptionPane.showMessageDialog(frame, "Check Selling Price");
//			    		return;
//			    	}
				}
			}
		});
		
		textField_20 = new JTextField();
		textField_20.setEditable(false);
		textField_20.setText("0");
		textField_20.setColumns(10);
		textField_20.setBounds(412, 157, 74, 22);
		panel_1.add(textField_20);
		textField_20.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_20.getText().equals(""))
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
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	btnprint.requestFocus();
				}
			}
		});
		textField_20.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				//String new_qty=textField_25.getText();
				if(Double.parseDouble(sellingprice)<= Double.parseDouble(textField_19.getText().trim()) )
		    	{
					String qty=textField_14.getText();
					String cost=textField_19.getText();
	    			String itm_disc=textField_20.getText();
	    			String tax=comboBox_1.getSelectedItem().toString();
	    			String tax_rate=textField_28.getText();
	    			double taxval=0;
	    			double tax_amt=0;
	    			double desc=0;
	    			if(!qty.equals(""))
	    			{
	    				if(!cost.equals(""))
	    				{
	    					double ttl= (Double.parseDouble(qty) * Double.parseDouble(cost));
	    					if(!itm_disc.equals(""))
	    					{
	    						desc=((Double.parseDouble(qty) * Double.parseDouble(cost))/100)*(Double.parseDouble(itm_disc));	
	    					}
	    					else
	    					{
	    						textField_20.setText(itm_disc);
	    					}
	    					double grnd=ttl-desc;
	    					if(tax.equals("INC"))
			    			{
			    				tax_amt=((grnd * Integer.parseInt(tax_rate))/105);
			    				taxval=grnd-tax_amt;
			    				
			    			}
			    			else if(tax.equals("EXC"))
			    			{
			    				tax_amt=((grnd * Integer.parseInt(tax_rate))/100);
			    				taxval=grnd;
			    			}
			    			else
			    			{
			    				tax_amt=0;
			    				taxval=grnd-tax_amt;
			    			}
	    					
	    					BigDecimal bd = new BigDecimal(tax_amt);
	                		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	                		tax_amt = bd.doubleValue();
	                		
	                		int decimal = 2;
	                		BigDecimal ba = new BigDecimal(taxval);
	                		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	                		taxval = ba.doubleValue();
	                		
	                		textField_21.setText(Double.toString(taxval));
	                		textField_24.setText(Double.toString(tax_amt));
	    				}
	    			}
	    			btnprint.requestFocus();
					Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
					btnprint.setBorder(border);
		    	}
				
				
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				if(Mainpage.SDPriceLock.equals("1"))
				{
					if(Double.parseDouble(sellingprice)<= Double.parseDouble(textField_19.getText().trim()) )
			    	{
						textField_20.requestFocus();
			    	}
			    	else
			    	{
			    		new sd_manager(db_name, username, store_name, textField_19.getText().trim(), version);
			    		
			    		
			    	}
				}
				else
				{
					String qty=textField_14.getText();
					String cost=textField_19.getText();
	    			String itm_disc=textField_20.getText();
	    			String tax=comboBox_1.getSelectedItem().toString();
	    			String tax_rate=textField_28.getText();
	    			double taxval=0;
	    			double tax_amt=0;
	    			double desc=0;
	    			if(!qty.equals(""))
	    			{
	    				if(!cost.equals(""))
	    				{
	    					double ttl= (Double.parseDouble(qty) * Double.parseDouble(cost));
	    					if(!itm_disc.equals(""))
	    					{
	    						desc=((Double.parseDouble(qty) * Double.parseDouble(cost))/100)*(Double.parseDouble(itm_disc));	
	    					}
	    					else
	    					{
	    						textField_20.setText(itm_disc);
	    					}
	    					double grnd=ttl-desc;
	    					if(tax.equals("INC"))
			    			{
			    				tax_amt=((grnd * Integer.parseInt(tax_rate))/105);
			    				taxval=grnd-tax_amt;
			    				
			    			}
			    			else if(tax.equals("EXC"))
			    			{
			    				tax_amt=((grnd * Integer.parseInt(tax_rate))/100);
			    				taxval=grnd;
			    			}
			    			else
			    			{
			    				tax_amt=0;
			    				taxval=grnd-tax_amt;
			    			}
	    					
	    					BigDecimal bd = new BigDecimal(tax_amt);
	                		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	                		tax_amt = bd.doubleValue();
	                		
	                		int decimal = 2;
	                		BigDecimal ba = new BigDecimal(taxval);
	                		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	                		taxval = ba.doubleValue();
	                		
	                		textField_21.setText(Double.toString(taxval));
	                		textField_24.setText(Double.toString(tax_amt));
	    				}
	    			}
	    			btnprint.requestFocus();
					Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
					btnprint.setBorder(border);
				}
				
			}
		});
		
		
		textField_21 = new JTextField();
		textField_21.setEditable(false);
		textField_21.setColumns(10);
		textField_21.setBounds(497, 157, 101, 22);
		panel_1.add(textField_21);
		
		textField_22 = new JTextField();
		textField_22.setEditable(false);
		textField_22.setColumns(10);
		textField_22.setBounds(11, 157, 71, 22);
		panel_1.add(textField_22);
		
		textField_23 = new JTextField();
		textField_23.setVisible(false);
		textField_23.setEditable(false);
		textField_23.setColumns(10);
		textField_23.setBounds(643, 501, 56, 22);
		panel_1.add(textField_23);
		
		textField_24 = new JTextField();
		textField_24.setEditable(false);
		textField_24.setColumns(10);
		textField_24.setBounds(609, 157, 81, 22);
		panel_1.add(textField_24);
		
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setEnabled(false);
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_2.getItemCount()>0)
				{
					validate_barcode_unit(db_name, textField_15.getText(),comboBox_2.getSelectedItem().toString());
				}
			}
		});
		comboBox_2.setBounds(810, 111, 97, 22);
		panel_1.add(comboBox_2);
		
		btnprint = new JButton("Enter");
		btnprint.setEnabled(false);
		btnprint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	enterbtn_func(db_name);
				}

			}
		});
		btnprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enterbtn_func(db_name);
			}
		});
		btnprint.setBounds(881, 136, 69, 43);
		panel_1.add(btnprint);
		
		textField_17 = new JTextField();
		textField_17.setEditable(false);
		textField_17.setEnabled(false);
		textField_17.setVisible(false);
		textField_17.setBounds(136, 467, 116, 22);
		panel_1.add(textField_17);
		textField_17.setColumns(10);
		
		btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		btnClear.setBounds(881, 188, 69, 32);
		panel_1.add(btnClear);
		
		textField_25 = new JTextField();
		textField_25.setEnabled(false);
		textField_25.setVisible(false);
		textField_25.setEditable(false);
		textField_25.setBounds(387, 467, 116, 22);
		panel_1.add(textField_25);
		textField_25.setColumns(10);
		
		textField_28 = new JTextField();
		textField_28.setEnabled(false);
		textField_28.setVisible(false);
		textField_28.setEditable(false);
		textField_28.setBounds(513, 467, 116, 22);
		panel_1.add(textField_28);
		textField_28.setColumns(10);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				int rows=table.getRowCount()-1;
				
				try{
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement();  
					String query="SELECT * FROM "+db_name+".mr_sd_lines where ref_inv_num='"+textField_2.getText()+"' and id='"+table.getValueAt(row, 17).toString()+"' "; 
					ResultSet resultSet = statement.executeQuery(query);
					if(resultSet.next())
					{
						String query1="delete from "+db_name+".mr_sd_lines  where ref_inv_num='"+textField_2.getText()+"' and id='"+table.getValueAt(row, 17).toString()+"' ";
						statement.executeUpdate(query1);
						model.removeRow(row);
						for(int i=0;i<model.getRowCount();i++)
						{
							model.setValueAt(i+1, i, 0);
						}
					}
					con.close();
					
				}
				catch(Exception e){
					System.err.println(e);
				}
				double vatpric=0;
    			double total=0; 
    			for(int a=0;a<rows;a++)
    			{
    				String ttl=model.getValueAt(a, 9).toString();
    				String vat=model.getValueAt(a, 10).toString();
    				total=total+Double.parseDouble(ttl);
    				BigDecimal bd = new BigDecimal(total);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		total = bd.doubleValue();
    				vatpric=vatpric+Double.parseDouble(vat);
    				BigDecimal bv = new BigDecimal(vatpric);
            		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		vatpric = bv.doubleValue();
    				
    			}
    			textField.setText(Double.toString(total));
    			textField_8.setText(Double.toString(total));
    			textField_9.setText(Double.toString(vatpric));
    			textField_10.setText(Double.toString(total+vatpric));
    			
    			for(int i=0;i<rows;i++)
    			{
    				model.setValueAt(i+1, i, 0);
    				String value = model.getValueAt(i, 1).toString();
    				if(value=="")
    				{
    					model.removeRow(i);
    				}
    			}
    			textField_13.setText("");
				textField_4.setText("");
				textField_7.setText("");
				textField_5.setText("");
				textField_15.setText("");
				textField_16.setText("");
				textField_14.setText("");
				textField_18.setText("");
				textField_19.setText("");
				textField_21.setText("");
				textField_22.setText("");
				textField_23.setText("");
				textField_24.setText("");
				textField_25.setText("");
				textField_26.setText("");
				sellingprice="0";
				comboBox_2.removeAllItems();
				textField_4.requestFocus();
				
				
				
    			get_ven_items(db_name,textField_6.getText(),"","");
			}
		});
		btnDelete.setBounds(12, 188, 70, 32);
		panel_1.add(btnDelete);
		
		lblRemark_1 = new JLabel("Remark");
		lblRemark_1.setBounds(701, 136, 169, 22);
		panel_1.add(lblRemark_1);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(701, 157, 169, 22);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setEnabled(false);
		textField_6.setEditable(false);
		textField_6.setVisible(false);
		textField_6.setBounds(10, 469, 116, 22);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		btnCustomerItemHistory = new JButton("Customer Item History");
		btnCustomerItemHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cust_name=textField_6.getText();
				if(!cust_name.equals(""))
				{
					try {
						frame.setAlwaysOnTop(false);
						new cust_item_history(db_name,username,store_name, version,decimal,cust_name);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnCustomerItemHistory.setBounds(334, 437, 184, 25);
		panel_1.add(btnCustomerItemHistory);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_1.getSelectedItem().toString().equals("NON"))
				{
					textField_22.setText("");
					textField_28.setText("0");
				}
				else
				{
					textField_22.setText("VAT 5%");
					textField_28.setText("5");
				}
			}
		});
		comboBox_1.setBounds(93, 157, 61, 22);
		panel_1.add(comboBox_1);
		
		comboBox_3 = new JComboBox<String>();
		comboBox_3.setVisible(false);
		comboBox_3.setBounds(552, 501, 81, 22);
		panel_1.add(comboBox_3);
		
		JButton button_1 = new JButton("...");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setAlwaysOnTop(false);
					new sales_distribution.itemdetails(textField_7.getText());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setBounds(348, 108, 28, 25);
		panel_1.add(button_1);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(597, 438, 116, 22);
		panel_1.add(textField_7);
		textField_7.setVisible(false);
		textField_7.setColumns(10);
		
		JLabel lblTotalQty = new JLabel("Total Qty : ");
		lblTotalQty.setBounds(12, 403, 65, 16);
		panel_1.add(lblTotalQty);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(73, 403, 76, 16);
		panel_1.add(lblNewLabel);
		
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setBounds(262, 467, 116, 22);
		panel_1.add(textField_13);
		textField_13.setColumns(10);
		textField_13.setVisible(false);
		
		
		comboBox_1.addItem("EXC");
		comboBox_1.addItem("INC");
		comboBox_1.addItem("NON");
		
		
		textField_6.getDocument().addDocumentListener(new DocumentListener() {
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
				  if(!textField_6.getText().equals(""))
				  {
					  try{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="SELECT * FROM "+db_name+".mr_customer_master where cust_code='"+textField_6.getText()+"'"; 
							ResultSet resultSet = statement.executeQuery(query);
							if(resultSet.next())
							{
								 String cust_name=resultSet.getString("cust_name");
								 String addr1=resultSet.getString("addr1");
								 String addr2=resultSet.getString("addr2");
								 String city=resultSet.getString("city");
								 String country=resultSet.getString("country");
								 String ph1=resultSet.getString("ph1");
								 String trn=resultSet.getString("trn");
								 //String avail_credit=resultSet.getString("avail_credit");
								 textArea_1.setText("");
								 textArea_1.append("TRN: "+trn+"\n"+addr1+"\n"+addr2+"\n"+city+"\n"+country+"\nPh: "+ph1+"");
								 textField_3.setText(cust_name);
							}
							con.close();
						}
						catch(Exception e){
							System.err.println(e);
						}
				  }
			  }
			});
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_4.setText("");
				textField_7.setText("");
				textField_13.setText("");
    			textField_15.setText("");
				textField_16.setText("");
				textField_14.setText("");
				textField_18.setText("");
				textField_19.setText("");
				textField_20.setText("");
				textField_21.setText("");
				textField_22.setText("");
				textField_23.setText("");
				textField_24.setText("");
				textField_25.setText("");
				textField_26.setText("");
				textField_4.requestFocus();
				btnDelete.setEnabled(true);
				comboBox_2.removeAllItems();
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 61, 957, 2);
		frame.getContentPane().add(separator);
		
		if(po_num.equals(""))
		{
			btnProceed = new JButton("Start");
			btnProceed.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					frame.setAlwaysOnTop(false);
					//JComboBox <String> comboBox = new JComboBox <String> ();
					//comboBox.addItem("Invoice");
					//comboBox.addItem("Quotation");
					String po_name="";
					Object[] colours = {"Invoice", "Quotation","Cancel"};
					int n = JOptionPane.showOptionDialog(null,"Select One Type","Select Type",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,colours,colours[0]);
					if(n==0)
					{
						po_name="sd_invoice";
					}
					if(n==1)
					{
						po_name="sd_quotation";
					}
					if(n==2)
					{
						return;
					}
					
					
					
					//JOptionPane.showMessageDialog(frame, comboBox, "Select Type", JOptionPane.QUESTION_MESSAGE);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date= ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					if(!date.equals(""))
					{
						date = sdf.format(dateChooser.getDate());
					}
					String user=label_1.getText();
					final String inv_num=textField_2.getText();
					String reference=textField_27.getText();
					
					String cust_name=textField_3.getText();
					String cust_addr=textArea_1.getText();
					
					String remark = textArea.getText();
					
//					if(comboBox.getSelectedItem().toString().equals("Invoice"))
//					{
//						po_name="sd_invoice";
//					}
//					else
//					{
//						po_name="sd_quotation";
//					}
					
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);
					
				    if(date.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Select date from date chooser");
						dateChooser.requestFocus();
					}
					else if(cust_name.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Please select the Customer");
						textField_3.requestFocus();
					}
					else
					{
						try{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="SELECT * FROM "+db_name+".mr_sd_list where invoice_num ='"+inv_num+"' and status!='VOID' "; 
							ResultSet resultSet = statement.executeQuery(query);
							if(!resultSet.next())
							{
								String seq_num = null;
								if(inv_num.equals("NEW"))
								{
									String trans_seq = "select tran_seq from "+db_name+".mr_transaction_seq where tran_name='"+po_name+"' ";
									ResultSet resultSet1 = statement.executeQuery(trans_seq);
								    if (resultSet1.next())
								    {
								    	seq_num=resultSet1.getString("tran_seq");
								    	String[] seq=seq_num.split("-");
								    	DateFormat df = new SimpleDateFormat("yy");
								    	String formatdDate = df.format(Calendar.getInstance().getTime());
								    	if(!seq[1].equals(formattedDate))
								    	{
								    		seq[1]=formatdDate;
								    	}
								    	seq_num=seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+seq[3];
								    	String sqnum=Integer.toString(Integer.parseInt(seq[3])+1);
								    	StringBuilder sb = new StringBuilder();
										for (int toPrepend=6-sqnum.length(); toPrepend>0; toPrepend--) {
										    sb.append('0');
										}
										sb.append(sqnum);
								    	String save_po="update "+db_name+".mr_transaction_seq set tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_name='"+po_name+"' ";
										statement.executeUpdate(save_po);
								    }
								}
								
							    String save_po="insert into "+db_name+".mr_sd_list(`date`, `user_id`, `invoice_num`, `remark`,  `cust_id`, `cust_addr`, `status`, `referance`, `store_name`, `created_date`, `modified_date`,type,cust_name,salesman,paymentmethod) values('"+date+"','"+user+"','"+seq_num+"','"+remark+"','"+textField_6.getText().trim()+"','"+cust_addr+"','OPEN','"+reference+"','"+store_name+"','"+formattedDate+"','"+formattedDate+"','"+po_name+"','"+cust_name+"','"+comboBox_4.getSelectedItem().toString()+"','"+comboBox.getSelectedItem().toString()+"')";
							    statement.executeUpdate(save_po);
								textField_2.setText(seq_num);
					        	
								btnProceed.setEnabled(false);
					        	btnProceed.setVisible(false);
								textField_2.setEditable(false);
								textField_3.setEditable(false);
								textArea_1.setEditable(false);
								btnNewButton.setEnabled(true);
								textField_4.setEditable(true);
								comboBox_2.setEnabled(true);
								textField_14.setEditable(true);
								textField_19.setEditable(true);
								textField_20.setEditable(true);
								textField_5.setEditable(true);
								btnprint.setEnabled(true);
								btnClear.setEnabled(true);
								btnDelete.setEnabled(true);
								textField.setEditable(true);
					        	textField_1.setEditable(true);
					        	textField_12.setEditable(true);
					        	textField_8.setEditable(true);
					        	textField_9.setEditable(true);
					        	textField_10.setEditable(true);
					        	textField_11.setEditable(true);
					        	btnSearch.setEnabled(true);
					        	scrollPane.setEnabled(true);
					        	scrollPane_1.setEnabled(true);
					        	table.setEnabled(true);
					        	table_1.setEnabled(true);
					        	//menuItem_1.setEnabled(false);
					        	menuItem_2.setEnabled(true);
								//btnNew.setEnabled(false);
								//button.setEnabled(false);
					        	
					        	get_ven_items(db_name,textField_6.getText(),"",username);
					        	textField_4.requestFocus();
							}
							con.close();
						}
						catch(Exception e){
							System.err.println(e);
						}
					}
				}
			});
			btnProceed.setBounds(575, 6, 70, 25);
			panel.add(btnProceed);
			dateChooser.requestFocus();
		}
		else
		{
			//edit_po(po_num,db_name,vendor_id);
			try{
				
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				String query="SELECT * FROM "+db_name+".mr_sd_list where invoice_num ='"+po_num+"' "; 
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next())
				{
					java.util.Date date =null;
					if(resultSet.getString("date")!="")
					{
						date = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("date"));
					}
					dateChooser.setDate(date);
					label_1.setText(resultSet.getString("user_id"));
					textField_2.setText(resultSet.getString("invoice_num"));
					textArea.setText(resultSet.getString("remark"));
					textField_3.setText(resultSet.getString("cust_name"));
					textField_6.setText(resultSet.getString("cust_id"));
					textField_27.setText(resultSet.getString("referance"));
					textArea_1.setText(resultSet.getString("cust_addr"));
					comboBox_4.setSelectedItem(resultSet.getString("salesman"));
					comboBox.setSelectedItem(resultSet.getString("paymentmethod"));
					
					textField_2.setEditable(false);
					textField_3.setEditable(false);
					textArea_1.setEditable(false);
					btnNewButton.setEnabled(true);
					textField_4.setEditable(true);
					comboBox_2.setEnabled(true);
					textField_14.setEditable(true);
					textField_19.setEditable(true);
					textField_20.setEditable(true);
					textField_5.setEditable(true);
					btnprint.setEnabled(true);
					btnClear.setEnabled(true);
					btnDelete.setEnabled(true);
					textField.setEditable(true);
		        	textField_1.setEditable(true);
		        	textField_12.setEditable(true);
		        	textField_8.setEditable(true);
		        	textField_9.setEditable(true);
		        	textField_10.setEditable(true);
		        	textField_11.setEditable(true);
		        	btnSearch.setEnabled(true);
		        	scrollPane.setEnabled(true);
		        	scrollPane_1.setEnabled(true);
		        	table.setEnabled(true);
		        	table_1.setEnabled(true);
		        	//menuItem_1.setEnabled(false);
		        	menuItem_2.setEnabled(true);
		        	//btnNew.setEnabled(false);
					//button.setEnabled(false);
		        	get_item_dt(db_name,po_num,decimal);
		        	get_ven_items(db_name,resultSet.getString("cust_id"),"","");
				}
				con.close();
			}
			catch(Exception e){
				System.err.println(e);
			}
		}
		
		clickOnserch( btnNewButton, "play", KeyEvent.VK_F9, db_name, store_name, "",version,Integer.toString(decimal),language );
		
		chckbxAssumePriceAs = new JCheckBox("Assume Price as Selling Price");
		chckbxAssumePriceAs.setBounds(558, 85, 202, 25);
		panel_1.add(chckbxAssumePriceAs);
		chckbxAssumePriceAs.setBackground(new Color(243, 229, 216));
		
		JLabel lblQtyOh = new JLabel("Qty OH");
		lblQtyOh.setBounds(165, 136, 70, 22);
		panel_1.add(lblQtyOh);
		
		textField_26 = new JTextField();
		textField_26.setEnabled(false);
		textField_26.setEditable(false);
		textField_26.setColumns(10);
		textField_26.setBounds(165, 157, 70, 22);
		panel_1.add(textField_26);
	}
	
	private void get_user(String db_name) {
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+db_name+".mr_users where user_type='Sales' ";
			ResultSet resultSet1 = statement.executeQuery(query);
			while(resultSet1.next())
			{
				comboBox_4.addItem(resultSet1.getString("user_name"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
			
	}

	public static void clickOnserch(final AbstractButton button, String actionName, int key,final String db_name,final String store_name,final String posreg,final String version ,final String decimal,final String language)
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
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					new item_list_master(db_name,db_name, "sd", "", version,Integer.parseInt(decimal),con,"",language);
				} catch (NumberFormatException | JSONException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    } );
	}
	
	public static void validate_barcode(String company, String input,String custid, String username,String version,String store, String language){
		
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_tmp_item_master where item_code='"+input+"' or barcode='"+input+"' ";
			ResultSet resultSet1 = statement.executeQuery(query);
			if(resultSet1.next())
			{
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				if(resultSet1.getString("activeForSale").equals("1"))
				{
					ResultSet resultSet = statement.executeQuery(query);
					String barcode = null;
					if(resultSet.next())
					{
						barcode=resultSet.getString("barcode");
						get_itemdtls_bsd_code(company,resultSet.getString("item_code"),barcode);
					}	
				}
				else
				{
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					int a=JOptionPane.showConfirmDialog(frame, "Item Not Activated For Sales , Do You Want Activate This Item","Warning",JOptionPane.YES_NO_OPTION);
					if(a==0)
					{
						query="update "+company+".mr_item_master set activeForSale='1' where item_code='"+input+"'";
						statement.executeUpdate(query);
					}
					else
					{
						textField_4.setText("");
					}
				}
			}
			else
			{
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				int a=JOptionPane.showConfirmDialog(frame, "Item Not Found , Do You Want Add This Item","Warning",JOptionPane.YES_NO_OPTION);
				if(a==0)
				{
					new inventory_master.Item_master(company,company, "NEW", username,custid,"SD","", version,store,decimal,language);
				}
				else
				{
					textField_4.setText("");
				}
			}
			con.close();
		}
		catch(Exception e){
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			System.err.println(e);
			frame.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(frame, e.getMessage());
			frame.setAlwaysOnTop(true);
		}
	}
	public static void get_itemdtls_bsd_code(String db_name, String input,String scan_barcode) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+db_name+".mr_tmp_item_master where item_code='"+input+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				textField_15.setText("");
				textField_16.setText("");
				textField_14.setText("");
				textField_18.setText("");
				textField_19.setText("");
				textField_20.setText("0");
				textField_21.setText("");
				textField_22.setText("");
				textField_23.setText("");
				textField_24.setText("");
				textField_25.setText("");
				textField_26.setText("");
				textField_13.setText("");
				comboBox_2.removeAllItems();
				String barcode =scan_barcode;//resultSet.getString("barcode").replaceAll("\\s+","");
				String tax = resultSet.getString("output_tax");
				
				String inclu_tax=resultSet.getString("sp_include_tax");
				String ttl_qty=resultSet.getString("qty");
				String tax_type="";
				float cost=Float.parseFloat(resultSet.getString("sp"));
				double item_cost = 0;
				if(!tax.equals(""))
				{
					if(inclu_tax.equals("1"))
					{
						tax_type="INC" ;
					}
					else
					{
						tax_type="EXC" ;
					}
					item_cost=cost;
					BigDecimal bv = new BigDecimal(item_cost);
	        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	        		item_cost = bv.doubleValue();
				}
				else
				{
					tax_type="NON" ;
					item_cost=cost;
				}
				textField_13.setText(resultSet.getString("cost"));
				textField_15.setText(resultSet.getString("item_code"));
				textField_16.setText(resultSet.getString("item_name"));
				textField_14.requestFocus();
				//textField_19.setText(Double.toString(item_cost));
				
				double ttlqty=Double.parseDouble(ttl_qty);
				Statement statement505=con.createStatement();  
				String query505="SELECT COALESCE(SUM(qty * cf), 0) AS soldqty FROM "+db_name+".mr_sd_lines inner join "+db_name+".mr_sd_list on ref_inv_num=invoice_num where item_code='"+resultSet.getString("item_code")+"' and status='OPEN' and type='sd_invoice' "; 
				ResultSet resultSet505 = statement505.executeQuery(query505);
				if(resultSet505.next())
				{
					
					ttlqty=ttlqty-Double.parseDouble(resultSet505.getString("soldqty"));
				}
				textField_25.setText(Double.toString(ttlqty));
				textField_26.setText(Double.toString(ttlqty));
				
				
				
				
				
				Statement statement50=con.createStatement();  
				String query50="SELECT * FROM "+db_name+".mr_tax where tax_code='"+tax+"' "; 
				ResultSet resultSet50 = statement50.executeQuery(query50);
				if(resultSet50.next())
				{
					textField_28.setText(resultSet50.getString("tax_rate"));
				}
				
				Statement statement1=con.createStatement();  
				String query1="SELECT * FROM "+db_name+".mr_item_master where item_code='"+input+"' "; 
				ResultSet resultSet1 = statement1.executeQuery(query1);
				if(resultSet1.next())
				{
					textField_7.setText(resultSet1.getString("item_details"));
					if(!resultSet1.getString("barcode1").equals("") || !resultSet1.getString("barcode2").equals("") || !resultSet1.getString("barcode3").equals(""))
					{
						comboBox_2.addItem(resultSet1.getString("unit1"));
					}
					if(!resultSet1.getString("barcode4").equals("") || !resultSet1.getString("barcode5").equals("") || !resultSet1.getString("barcode6").equals(""))
					{
						comboBox_2.addItem(resultSet1.getString("unit2"));
					}
					if(!resultSet1.getString("barcode7").equals("") || !resultSet1.getString("barcode8").equals("") || !resultSet1.getString("barcode9").equals(""))
					{
						comboBox_2.addItem(resultSet1.getString("unit3"));
					}
					if(!resultSet1.getString("barcode10").equals("") || !resultSet1.getString("barcode11").equals("") || !resultSet1.getString("barcode12").equals(""))
					{
						comboBox_2.addItem(resultSet1.getString("unit4"));
					}
					if(!resultSet1.getString("barcode13").equals("") || !resultSet1.getString("barcode14").equals("") || !resultSet1.getString("barcode15").equals(""))
					{
						comboBox_2.addItem(resultSet1.getString("unit5"));
					}
					if(!resultSet1.getString("barcode16").equals("") || !resultSet1.getString("barcode17").equals("") || !resultSet1.getString("barcode18").equals(""))
					{
						comboBox_2.addItem(resultSet1.getString("unit6"));
					}
					
					if(resultSet1.getString("barcode1").equals(scan_barcode) || resultSet1.getString("barcode2").equals(scan_barcode) || resultSet1.getString("barcode3").equals(scan_barcode))
					{
						comboBox_2.setSelectedItem(resultSet1.getString("unit1"));
					}
					if(resultSet1.getString("barcode4").equals(scan_barcode) || resultSet1.getString("barcode5").equals(scan_barcode) || resultSet1.getString("barcode6").equals(scan_barcode))
					{
						comboBox_2.setSelectedItem(resultSet1.getString("unit2"));
					}
					if(resultSet1.getString("barcode7").equals(scan_barcode) || resultSet1.getString("barcode8").equals(scan_barcode) || resultSet1.getString("barcode9").equals(scan_barcode))
					{
						comboBox_2.setSelectedItem(resultSet1.getString("unit3"));
					}
					if(resultSet1.getString("barcode10").equals(scan_barcode) || resultSet1.getString("barcode11").equals(scan_barcode) || resultSet1.getString("barcode12").equals(scan_barcode))
					{
						comboBox_2.setSelectedItem(resultSet1.getString("unit4"));
					}
					if(resultSet1.getString("barcode13").equals(scan_barcode) || resultSet1.getString("barcode14").equals(scan_barcode) || resultSet1.getString("barcode15").equals(scan_barcode))
					{
						comboBox_2.setSelectedItem(resultSet1.getString("unit5"));
					}
					if(resultSet1.getString("barcode16").equals(scan_barcode) || resultSet1.getString("barcode17").equals(scan_barcode) || resultSet1.getString("barcode18").equals(scan_barcode))
					{
						comboBox_2.setSelectedItem(resultSet1.getString("unit6"));
					}
				}
				Statement statement55=con.createStatement();  
				String query55="SELECT * FROM "+db_name+".mr_customer_items where barcode='"+scan_barcode+"' and ref_cust_id='"+textField_6.getText()+"' and status='0' "; 
				ResultSet resultSet55 = statement55.executeQuery(query55);
				if(resultSet55.next())
				{
					textField_19.setText(resultSet55.getString("price"));
				}
				textField_4.setText(barcode);
				//textField_18.setText(resultSet.getString("cf1"));
				//textField_22.setText(resultSet.getString("output_tax"));
				comboBox_3.setSelectedItem(tax);
				//textField_23.setText(tax_type);
				comboBox_1.setSelectedItem(tax_type);
				
			    frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	public static void get_ven_items(String db_name,String cust_id,String serch, String username)
	{
		String serch_str = "";
		if(!serch.equals(""))
		{
			serch_str="and ("+db_name+".mr_item_master.item_name LIKE '%"+serch+"%' OR "+db_name+".mr_item_master.department LIKE '%"+serch+"%' OR "+db_name+".mr_item_master.item_code LIKE '%"+serch+"%' OR "+db_name+".mr_item_master.category LIKE '%"+serch+"%' OR "+db_name+".mr_item_master.barcode1 LIKE '%"+serch+"%') ";
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			int rowCount = model_1.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model_1.removeRow(i);
			}
			int a=0;
			String query="SELECT * FROM "+db_name+".mr_customer_items INNER JOIN "+db_name+".mr_item_master ON "+db_name+".mr_customer_items.ref_itemcode="+db_name+".mr_item_master.item_code inner join "+db_name+".mr_customer_master on "+db_name+".mr_customer_master.cust_code="+db_name+".mr_customer_items.ref_cust_id where "+db_name+".mr_customer_items.status='0' and "+db_name+".mr_customer_items.ref_cust_id='"+cust_id+"'   "+serch_str+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String code=resultSet.getString("item_code");
                String name=resultSet.getString("item_name");
                String barcode=resultSet.getString("barcode");
                String category=resultSet.getString("category");
                String department=resultSet.getString("department");
                String item_type=resultSet.getString("item_type");
                String tax = resultSet.getString("input_tax");
				float cost=Float.parseFloat(resultSet.getString("item_cost"));
				double item_cost = 0;
				if(!tax.equals(""))
				{
					item_cost=cost;
					BigDecimal bv = new BigDecimal(item_cost);
	        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	        		item_cost = bv.doubleValue();
				}
				else
				{
					item_cost=cost;
				}
                //textField_19.setText(Double.toString(item_cost));
                String selling_price=resultSet.getString("price");
                double qty=0;
                double cf=0;
                if(resultSet.getString("barcode1").equals(barcode) || resultSet.getString("barcode2").equals(barcode) || resultSet.getString("barcode3").equals(barcode))
				{
					cf=Double.parseDouble(resultSet.getString("cf1"));
				}
				if(resultSet.getString("barcode4").equals(barcode) || resultSet.getString("barcode5").equals(barcode) || resultSet.getString("barcode6").equals(barcode))
				{
					cf=Double.parseDouble(resultSet.getString("cf2"));
				}
				if(resultSet.getString("barcode7").equals(barcode) || resultSet.getString("barcode8").equals(barcode) || resultSet.getString("barcode9").equals(barcode))
				{
					cf=Double.parseDouble(resultSet.getString("cf3"));
				}
				if(resultSet.getString("barcode10").equals(barcode) || resultSet.getString("barcode11").equals(barcode) || resultSet.getString("barcode12").equals(barcode))
				{
					cf=Double.parseDouble(resultSet.getString("cf4"));
				}
				if(resultSet.getString("barcode13").equals(barcode) || resultSet.getString("barcode14").equals(barcode) || resultSet.getString("barcode15").equals(barcode))
				{
					cf=Double.parseDouble(resultSet.getString("cf5"));
				}
				if(resultSet.getString("barcode16").equals(barcode) || resultSet.getString("barcode17").equals(barcode) || resultSet.getString("barcode18").equals(barcode))
				{
					cf=Double.parseDouble(resultSet.getString("cf6"));
				}
				double qtyoh=Double.parseDouble(resultSet.getString("total_qty"));
				if(qtyoh==0)
				{
					qty=qtyoh;
				}
				else
				{
					qty=qtyoh/cf;
				}
				if(a==200)
				{
					System.err.println("250");
				}
				BigDecimal bv = new BigDecimal(qty);
        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
        		qty = bv.doubleValue();
                model_1.addRow(new Object[]{model_1.getRowCount()+1, code, name,barcode,department,category,item_type,item_cost,selling_price,qty});
                a++;
            }
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	public void get_item_dt(String db_name, String po_num,int decimal) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query1="SELECT disc FROM "+db_name+".mr_sd_list where invoice_num ='"+po_num+"' "; 
			ResultSet resultSet1 = statement.executeQuery(query1);
			double grand_disc=0;
			if(resultSet1.next())
			{
				grand_disc=Double.parseDouble(resultSet1.getString("disc"));
			}
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			
			String query="SELECT * FROM "+db_name+".mr_sd_lines where ref_inv_num ='"+po_num+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String id =resultSet.getString("id");
				String barcode =resultSet.getString("barcode");
				String itm_cde =resultSet.getString("item_code");
				double qty =Double.parseDouble(resultSet.getString("qty")) ;
				String unit =resultSet.getString("unit");
				int cf =Integer.parseInt(resultSet.getString("cf")) ;
				double unit_cost =Double.parseDouble(resultSet.getString("unit_cost"));
				double itm_disc =Double.parseDouble(resultSet.getString("itm_disc"));
				double tax_val =Double.parseDouble(resultSet.getString("tax_val"));
				String rmk =resultSet.getString("remark");
				String itm_dtls =resultSet.getString("item_name");
				String tax_type =resultSet.getString("tax_type");
				String tax =resultSet.getString("output_tax");
				String item_details =resultSet.getString("item_details");
				double total =Double.parseDouble(resultSet.getString("total"));
				double cost =Double.parseDouble(resultSet.getString("item_cost"));
				double netttl=total+tax_val;
				double margin=0;
				double marginperc=0;
				if(netttl>0)
				{
					margin=netttl-cost;
		    		marginperc=(netttl-(cost))*100/netttl;
				}
				
	    		
				BigDecimal bz = new BigDecimal(netttl);
	    		bz = bz.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	    		netttl = bz.doubleValue();
	    		
	    		BigDecimal ba = new BigDecimal(margin);
	    		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	    		margin = ba.doubleValue();
	    		
	    		BigDecimal bsz = new BigDecimal(marginperc);
	    		bsz = bsz.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	    		marginperc = bsz.doubleValue();
	    		
	    		
				
				model.insertRow(model.getRowCount(), new Object[] { model.getRowCount()+1,  barcode, itm_cde,itm_dtls,unit,cf,qty,unit_cost,itm_disc,total,tax_val,netttl,tax,tax_type,rmk,item_details,cost,id,margin,marginperc});
			}
			double total=0;
			double vatpric=0;
			double grnd_ttl=0;
			double ttlqty=0;
			for(int a=0;a<model.getRowCount();a++)
			{
				String ttl_qty=model.getValueAt(a, 6).toString();
				String ttl=model.getValueAt(a, 9).toString();
				String vat=model.getValueAt(a, 10).toString();
				total=total+Double.parseDouble(ttl);
				BigDecimal bd = new BigDecimal(total);
        		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
        		total = bd.doubleValue();
				
        		vatpric=vatpric+Double.parseDouble(vat);
				BigDecimal bv = new BigDecimal(vatpric);
        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
        		vatpric = bv.doubleValue();
        		String cf_item=model.getValueAt(a, 5).toString();
        		ttlqty=ttlqty+(Double.parseDouble(ttl_qty)*Double.parseDouble(cf_item));
        		//ttlqty=ttlqty+Double.parseDouble(ttl_qty);
        		BigDecimal bw = new BigDecimal(ttlqty);
        		bw = bw.setScale(decimal, BigDecimal.ROUND_HALF_UP);
        		ttlqty = bw.doubleValue();
			}
			grnd_ttl=total+vatpric;
			BigDecimal bw = new BigDecimal(grnd_ttl);
    		bw = bw.setScale(decimal, BigDecimal.ROUND_HALF_UP);
    		grnd_ttl = bw.doubleValue();
    		lblNewLabel.setText(Double.toString(ttlqty));
			textField_12.setText(Double.toString(grand_disc));
			textField.setText(Double.toString(total));
			textField_9.setText(Double.toString(vatpric));
			textField_10.setText(Double.toString(grnd_ttl));
			textField_8.setText(Double.toString(total));
			if(grand_disc!=0)
			{
				double disc_per=grand_disc;
            	String txval=textField_9.getText();
            	if(disc_per!=0)
            	{
            		double ttl_per=(100*disc_per)/total;
            		BigDecimal bd = new BigDecimal(ttl_per);
            		bd = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
            		ttl_per = bd.doubleValue();
            		textField_1.setText(Double.toString(ttl_per));
            		//double ttl_per=(Double.parseDouble(disc_per)*100)/total;
            		BigDecimal bf = new BigDecimal(disc_per);
            		bf = bf.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		disc_per = bf.doubleValue();
            		textField_12.setText(Double.toString(disc_per));
            		
            		double subttl=total - disc_per;
            		BigDecimal bs = new BigDecimal(subttl);
            		bs = bs.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		subttl = bs.doubleValue();
            		textField_8.setText(Double.toString(subttl));
            		
            		double grand_vat=(Double.parseDouble(txval)-((Double.parseDouble(txval)*ttl_per)/100)) ;
            		BigDecimal bv = new BigDecimal(grand_vat);
            		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		grand_vat = bv.doubleValue();
            		textField_9.setText(Double.toString(grand_vat));
            		
            		grnd_ttl=subttl + grand_vat;
            		BigDecimal bg = new BigDecimal(grnd_ttl);
            		bg = bg.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		grnd_ttl = bg.doubleValue();
            		textField_10.setText(Double.toString(grnd_ttl));
            		textField_4.requestFocus();
            	}
			}
			statement.close();
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	private void enterbtn_func(String db_name)
	{
		String barcode =textField_4.getText();
		if(barcode.equals(""))
		{
			JOptionPane.showMessageDialog(frame, "Select or Enter A Barcode or Itemcode");
			textField_4.requestFocus();
		}
		else
		{
			String itm_cde =textField_15.getText();
			String itm_dtls =textField_16.getText();
			String unit =comboBox_2.getSelectedItem().toString();
			String cf =textField_18.getText();
			String qty =textField_14.getText();
			String unit_cost =textField_19.getText();
			String disc=textField_20.getText();
			String total =textField_21.getText();
			String tax =textField_22.getText();
			String tax_type =comboBox_1.getSelectedItem().toString();//textField_23.getText();
			String tax_val =textField_24.getText();
			String remark =textField_5.getText();
			String details =textField_7.getText();
			String itm_cost =textField_13.getText();
			double cost=Double.parseDouble(itm_cost)*Double.parseDouble(cf)*Double.parseDouble(qty);
			double netttl=Double.parseDouble(total)+Double.parseDouble(tax_val);
			BigDecimal bz = new BigDecimal(netttl);
    		bz = bz.setScale(decimal, BigDecimal.ROUND_HALF_UP);
    		netttl = bz.doubleValue();
			LocalDateTime myDateObj = LocalDateTime.now();  
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		    String formattedDate = myDateObj.format(myFormatObj);  
			if(textField_17.getText().equals(textField_4.getText()))
			{
				if(!textField_23.getText().equals(""))
				{
					int sel_row=table.getSelectedRow();
					double margin=0;
					double marginperc=0;
					if(netttl>0)
					{
						margin=netttl-(Double.parseDouble(itm_cost) *Double.parseDouble(cf)*Double.parseDouble(qty));
						marginperc=(netttl-(Double.parseDouble(itm_cost) *Double.parseDouble(cf)*Double.parseDouble(qty)))*100/netttl;
					}
					BigDecimal ba = new BigDecimal(margin);
		    		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
		    		margin = ba.doubleValue();
		    		
		    		BigDecimal bsz = new BigDecimal(marginperc);
		    		bsz = bsz.setScale(decimal, BigDecimal.ROUND_HALF_UP);
		    		marginperc = bsz.doubleValue();
					
					model.setValueAt(unit, sel_row, 4);
					model.setValueAt(cf, sel_row, 5);
					model.setValueAt(qty, sel_row, 6);
					model.setValueAt(unit_cost, sel_row, 7);
					model.setValueAt(disc, sel_row, 8);
					model.setValueAt(total, sel_row, 9);
					model.setValueAt(tax_val, sel_row, 10);
					model.setValueAt(netttl, sel_row, 11);
					model.setValueAt(tax, sel_row, 12);
					model.setValueAt(tax_type, sel_row, 13);
					model.setValueAt(remark, sel_row, 14);
					model.setValueAt(details, sel_row, 15);
					model.setValueAt(cost, sel_row, 16);
					model.setValueAt(margin, sel_row, 18);
					model.setValueAt(marginperc, sel_row, 19);
					
					try{
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						String query="SELECT * FROM "+db_name+".mr_sd_lines where ref_inv_num='"+textField_2.getText()+"' and barcode='"+textField_4.getText()+"' and id='"+textField_23.getText()+"' "; 
						ResultSet resultSet = statement.executeQuery(query);
						if(resultSet.next())
						{
							double unitcost=Double.parseDouble(textField_21.getText())/Double.parseDouble(textField_14.getText());
							BigDecimal bv = new BigDecimal(unitcost);
			        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
			        		unitcost = bv.doubleValue();
			        		String query1="update "+db_name+".mr_sd_lines set `item_code`='"+textField_15.getText()+"',item_cost='"+cost+"',item_name='"+itm_dtls+"', `qty`='"+textField_14.getText()+"',`unit`='"+comboBox_2.getSelectedItem().toString()+"', `cf`='"+textField_18.getText()+"', `unit_cost`='"+textField_19.getText()+"',`itm_disc`='"+textField_20.getText()+"',`itm_cost_after_disc`='"+textField_21.getText()+"', `sub_ttl_disc`='0', `sub_cost_after_sub_ttl_disc`='"+textField_21.getText()+"',`tax_val`='"+textField_24.getText()+"', `total`='"+textField_21.getText()+"', `cost_after_sub_ttl_disc`='"+unitcost+"',`remark`='"+remark+"', `modified_date`='"+formattedDate+"',output_tax='"+tax+"',tax_type='"+tax_type+"',item_details='"+details+"' where `ref_inv_num`='"+textField_2.getText()+"' and `barcode`='"+textField_4.getText()+"'  and id='"+textField_23.getText()+"'  ";
							statement.executeUpdate(query1);
						}
						con.close();
					}
					catch(Exception e1){
						System.err.println(e1);
						JOptionPane.showMessageDialog(frame, e1.getMessage());
					}
					textField_17.setText("");
					textField_23.setText(""); 
				}
				
			}
			else
			{
				
				
				try{
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement();  
					double unitcost=Double.parseDouble(textField_21.getText())/Double.parseDouble(textField_14.getText());
					BigDecimal bv = new BigDecimal(unitcost);
	        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	        		unitcost = bv.doubleValue();
	        		String query1="insert into "+db_name+".mr_sd_lines(`ref_inv_num`, `barcode`, `item_code`, item_name,`qty`, `unit`, `cf`, `unit_cost`,`itm_disc`, `itm_cost_after_disc`, `sub_ttl_disc`, `sub_cost_after_sub_ttl_disc`, `tax_val`, `total`, `cost_after_sub_ttl_disc`, `created_date`, `modified_date`,remark,output_tax,tax_type,item_details,item_cost) VALUES ('"+textField_2.getText()+"','"+textField_4.getText()+"','"+textField_15.getText()+"','"+textField_16.getText()+"','"+textField_14.getText()+"','"+comboBox_2.getSelectedItem().toString()+"','"+textField_18.getText()+"','"+textField_19.getText()+"','"+textField_20.getText()+"','"+textField_21.getText()+"','0','"+textField_21.getText()+"','"+textField_24.getText()+"','"+textField_21.getText()+"','"+unitcost+"','"+formattedDate+"','"+formattedDate+"','"+remark+"','"+tax+"','"+tax_type+"','"+details+"','"+cost+"')";
					statement.executeUpdate(query1);
					String insert_id="";
					String query="SELECT MAX(id) as insert_id FROM "+db_name+".mr_sd_lines  "; 
					ResultSet resultSet = statement.executeQuery(query);
					if(resultSet.next())
					{
						insert_id=resultSet.getString("insert_id");
					}
					con.close();
					double margin=0;
					double marginperc=0;
					if(netttl>0)
					{
						margin=netttl-(Double.parseDouble(itm_cost) *Double.parseDouble(cf)*Double.parseDouble(qty));
			    		marginperc=(netttl-(Double.parseDouble(itm_cost) *Double.parseDouble(cf)*Double.parseDouble(qty)))*100/netttl;
					}
					
		    		
					BigDecimal ba = new BigDecimal(margin);
		    		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
		    		margin = ba.doubleValue();
		    		
		    		BigDecimal bsz = new BigDecimal(marginperc);
		    		bsz = bsz.setScale(decimal, BigDecimal.ROUND_HALF_UP);
		    		marginperc = bsz.doubleValue();
					model.addRow(new Object[]{model.getRowCount()+1, barcode, itm_cde,itm_dtls,unit,cf,qty,unit_cost,disc,total,tax_val,netttl,tax,tax_type,remark,details,cost,insert_id,margin,marginperc});
				}
				catch(Exception e1){
					System.err.println(e1);
					JOptionPane.showMessageDialog(frame, e1.getMessage());
				}
				
			}
			
			double vatpric=0;
			double total_amt=0;
			double ttlqty=0;
			int rows=model.getRowCount();
			
			for(int a=0;a<rows;a++)
			{
				
				String ttl_qty=model.getValueAt(a, 6).toString();
				String ttl=model.getValueAt(a, 9).toString();
				String vat=model.getValueAt(a, 10).toString();
				if(ttl.equals(""))
				{
					ttl="0";
				}

				if(vat.equals(""))
				{
					vat="0";
				}
				total_amt=total_amt+Double.parseDouble(ttl);
				BigDecimal bd = new BigDecimal(total_amt);
        		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
        		total_amt = bd.doubleValue();
				vatpric=vatpric+Double.parseDouble(vat);
				BigDecimal bv = new BigDecimal(vatpric);
        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
        		vatpric = bv.doubleValue();
        		String cf_item=model.getValueAt(a, 5).toString();
        		ttlqty=ttlqty+(Double.parseDouble(ttl_qty)*Double.parseDouble(cf_item));
        		BigDecimal bw = new BigDecimal(ttlqty);
        		bw = bw.setScale(decimal, BigDecimal.ROUND_HALF_UP);
        		ttlqty = bw.doubleValue();
				
			}
			lblNewLabel.setText(Double.toString(ttlqty));
			textField.setText(Double.toString(total_amt));
			textField_8.setText(Double.toString(total_amt));
			textField_9.setText(Double.toString(vatpric));
			textField_10.setText(Double.toString(total_amt+vatpric));
			table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
			//table.addRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);

			textField_13.setText("");
			textField_4.setText("");
			textField_7.setText("");
			textField_5.setText("");
			textField_15.setText("");
			textField_16.setText("");
			textField_14.setText("");
			textField_18.setText("");
			textField_19.setText("");
			textField_21.setText("");
			textField_22.setText("");
			textField_23.setText("");
			textField_24.setText("");
			textField_25.setText("");
			textField_26.setText("");
			sellingprice="0";
			comboBox_2.removeAllItems();
			btnDelete.setEnabled(true);
			textField_4.requestFocus();
			
		}
	}
	public void validate_barcode_unit(String db_name,  String itemcode, String unitname) {
		try{
			String barcode = null;
			String cf=null;
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+db_name+".mr_item_master where item_code='"+itemcode+"' and '"+unitname+"' IN (unit1,unit2,unit3,unit4,unit5,unit6) "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				if(unitname.equals(resultSet.getString("unit1")))
				{
					textField_19.setText(resultSet.getString("sp1"));
					sellingprice=resultSet.getString("sp1");
					cf=resultSet.getString("cf1");
					barcode=resultSet.getString("barcode1");
					if(barcode.equals(""))
					{
						barcode=resultSet.getString("barcode2");
						if(barcode.equals(""))
						{
							barcode=resultSet.getString("barcode3");
						}
					}
				}
				if(unitname.equals(resultSet.getString("unit2")))
				{
					textField_19.setText(resultSet.getString("sp2"));
					sellingprice=resultSet.getString("sp2");
					cf=resultSet.getString("cf2");
					barcode=resultSet.getString("barcode4");
					if(barcode.equals(""))
					{
						barcode=resultSet.getString("barcode5");
						if(barcode.equals(""))
						{
							barcode=resultSet.getString("barcode6");
						}
					}
				}
				if(unitname.equals(resultSet.getString("unit3")))
				{
					cf=resultSet.getString("cf3");
					textField_19.setText(resultSet.getString("sp3"));
					sellingprice=resultSet.getString("sp3");
					barcode=resultSet.getString("barcode7");
					if(barcode.equals(""))
					{
						barcode=resultSet.getString("barcode8");
						if(barcode.equals(""))
						{
							barcode=resultSet.getString("barcode9");
						}
					}
				}
				if(unitname.equals(resultSet.getString("unit4")))
				{
					cf=resultSet.getString("cf4");
					textField_19.setText(resultSet.getString("sp4"));
					sellingprice=resultSet.getString("sp4");
					barcode=resultSet.getString("barcode10");
					if(barcode.equals(""))
					{
						barcode=resultSet.getString("barcode11");
						if(barcode.equals(""))
						{
							barcode=resultSet.getString("barcode12");
						}
					}
				}
				if(unitname.equals(resultSet.getString("unit5")))
				{
					cf=resultSet.getString("cf5");
					textField_19.setText(resultSet.getString("sp5"));
					sellingprice=resultSet.getString("sp5");
					barcode=resultSet.getString("barcode13");
					if(barcode.equals(""))
					{
						barcode=resultSet.getString("barcode14");
						if(barcode.equals(""))
						{
							barcode=resultSet.getString("barcode15");
						}
					}
				}
				if(unitname.equals(resultSet.getString("unit6")))
				{
					cf=resultSet.getString("cf6");
					textField_19.setText(resultSet.getString("sp6"));
					sellingprice=resultSet.getString("sp6");
					barcode=resultSet.getString("barcode16");
					if(barcode.equals(""))
					{
						barcode=resultSet.getString("barcode17");
						if(barcode.equals(""))
						{
							barcode=resultSet.getString("barcode18");
						}
					}
				}
				textField_4.setText(barcode);
	        	textField_18.setText(cf);
	        	Statement statement55=con.createStatement();  
				String query55="SELECT * FROM "+db_name+".mr_customer_items where ref_itemcode='"+resultSet.getString("item_code")+"' and ref_cust_id='"+textField_3.getText()+"' and status='0' and unit='"+unitname+"' "; 
				ResultSet resultSet55 = statement55.executeQuery(query55);
				if(resultSet55.next())
				{
					textField_19.setText(resultSet55.getString("price"));
				}
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	public void get_item_barcode_list(String db_name,String itemcode,String unit){
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement1=con.createStatement();  
			String query1="SELECT * FROM "+db_name+".mr_item_master where item_code='"+itemcode+"' "; 
			ResultSet resultSet1 = statement1.executeQuery(query1);
			if(resultSet1.next())
			{
				if(!resultSet1.getString("barcode1").equals("") || !resultSet1.getString("barcode2").equals("") || !resultSet1.getString("barcode3").equals(""))
				{
					comboBox_2.addItem(resultSet1.getString("unit1"));
				}
				if(!resultSet1.getString("barcode4").equals("") || !resultSet1.getString("barcode5").equals("") || !resultSet1.getString("barcode6").equals(""))
				{
					comboBox_2.addItem(resultSet1.getString("unit2"));
				}
				if(!resultSet1.getString("barcode7").equals("") || !resultSet1.getString("barcode8").equals("") || !resultSet1.getString("barcode9").equals(""))
				{
					comboBox_2.addItem(resultSet1.getString("unit3"));
				}
				if(!resultSet1.getString("barcode10").equals("") || !resultSet1.getString("barcode11").equals("") || !resultSet1.getString("barcode12").equals(""))
				{
					comboBox_2.addItem(resultSet1.getString("unit4"));
				}
				if(!resultSet1.getString("barcode13").equals("") || !resultSet1.getString("barcode14").equals("") || !resultSet1.getString("barcode15").equals(""))
				{
					comboBox_2.addItem(resultSet1.getString("unit5"));
				}
				if(!resultSet1.getString("barcode16").equals("") || !resultSet1.getString("barcode17").equals("") || !resultSet1.getString("barcode18").equals(""))
				{
					comboBox_2.addItem(resultSet1.getString("unit6"));
				}
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}