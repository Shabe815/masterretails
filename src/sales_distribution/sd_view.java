package sales_distribution;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import sales_master.cust_payment_voucher;

import com.toedter.calendar.JDateChooser;

public class sd_view {

	public static JFrame frame;
	private JTextField textField_2;
	public static JTextField textField_3;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private static DefaultTableModel model;
	private static DefaultTableModel model_1;
	public static JTable table;
	private JDateChooser dateChooser ;
	private JLabel label_1;
	private JButton btnProceed;
	private JMenuItem menuItem_3;
	private JScrollPane scrollPane;
	private JTextField textField_12;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private static JTextField textField_17;
	private static int decimal;
	private static JTextField textField_25;
	private JTextField textField_27;
	private static JTextField textField_28;
	public static JTextField textField_6;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JLabel lblSalesman;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					sd_view window = new sd_view("mr_pro","admin","001","IN-22-01-000002","S01", "","2","", null );
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param bal_amt 
	 * @param page_type 
	 */
	public sd_view(String db_name, String username,String cust_id, String po_num, String store_name,String version,String decimal, String bal_amt, String page_type) throws JSONException, SQLException {
		initialize(db_name,username,cust_id,po_num, store_name, version,decimal,bal_amt,page_type);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param db_name 
	 * @param page_type 
	 * @param host_name 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String db_name,final String username,final String cust_id,String po_num, final String store_name,final String version,final String decimals,final String bal_amt,final String page_type) throws JSONException, SQLException {
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
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		JLabel lblPurchaseOrder = new JLabel("Sales And Distribution");
		lblPurchaseOrder.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPurchaseOrder.setBounds(12, 6, 228, 30);
		panel.add(lblPurchaseOrder);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//sd_list.btnNewButton.setEnabled(true);
				//purchase_return_list.frame.setAlwaysOnTop(true);
				int rows=model.getRowCount();
				if(rows>0)
				{
					int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Exit","Warning",JOptionPane.YES_NO_OPTION);
			        // 0=yes, 1=no, 2=cancel
			        if(cofrm_bx==0)
			        {
			        	frame.setVisible(false);
			        	//sales_distribution.sd_list.get_po_disp(db_name, "OPEN", "","ALL");
			        }
			        
				}
				else
				{
					frame.setVisible(false);
					//sales_distribution.sd_list.get_po_disp(db_name, "OPEN", "","ALL");
					
				}
				
			}
		});
		menuItem.setBorder(null);
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(896, 6, 60, 30);
		panel.add(menuItem);
		
		menuItem_3 = new JMenuItem("Print");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					frame.setAlwaysOnTop(false);
					double ttlamtword=Double.parseDouble(textField_10.getText());
					Float num = new Float( ttlamtword ) ;
					int dollars = (int)Math.floor( num ) ;
					int cent = (int)Math.floor( ( num - dollars ) * 100.0f ) ;

					String amountinwords = numbertowords.convert( dollars ) + " and " +numbertowords.convert( cent ) + " Fils Only" ;
					JComboBox <String> comboBox = new JComboBox <String> ();
					comboBox.addItem("SDinvoiceHistory1");
					comboBox.addItem("SDinvoiceHistory2");
					comboBox.addItem("SDinvoiceHistory3");
					JOptionPane.showMessageDialog(frame, comboBox, "Select Report Format", JOptionPane.QUESTION_MESSAGE);
					
					
					report_master.sd_report.report(textField_2.getText(),con, page_type,amountinwords,comboBox.getSelectedItem().toString(),db_name);
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
		menuItem_3.setBounds(815, 6, 80, 30);
		panel.add(menuItem_3);
		
		if(Double.parseDouble(bal_amt) > 0)
		{
			btnNewButton = new JButton("Payment");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						Object[] item_list = new String[1];
						item_list[0]=textField_2.getText();
						new cust_payment_voucher(db_name,username,store_name,item_list,Double.parseDouble(bal_amt),cust_id,textField_3.getText(), version,Integer.toString(decimal),"sv");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
			btnNewButton.setBounds(658, 6, 97, 25);
			panel.add(btnNewButton);
		}
		
		
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
		textField_3.setBounds(81, 7, 263, 16);
		panel_3.add(textField_3);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(12, 34, 56, 16);
		panel_3.add(lblAddress);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(81, 38, 263, 46);
		panel_3.add(scrollPane_2);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		scrollPane_2.setViewportView(textArea_1);
		
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(243, 229, 216));
		panel_4.setLayout(null);
		panel_4.setBounds(638, 0, 319, 84);
		panel_1.add(panel_4);
		
		JLabel lblRemark = new JLabel("Remark");
		lblRemark.setBounds(12, 4, 56, 16);
		panel_4.add(lblRemark);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(80, 1, 227, 59);
		panel_4.add(scrollPane_3);
		
		textArea = new JTextArea();
		scrollPane_3.setViewportView(textArea);
		
		lblSalesman = new JLabel("Salesman");
		lblSalesman.setBounds(10, 59, 71, 14);
		panel_4.add(lblSalesman);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(80, 59, 227, 14);
		panel_4.add(lblNewLabel);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.setBounds(12, 97, 933, 438);
		panel_1.add(scrollPane);
		
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
				if(column==13)
				{
					return true;
				}
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
		}
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(709, 534, 236, 147);
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
                		txval+=Double.parseDouble(model.getValueAt(i, 12).toString());
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
                		txval+=Double.parseDouble(model.getValueAt(i, 12).toString());
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
		
		textField_17 = new JTextField();
		textField_17.setEditable(false);
		textField_17.setEnabled(false);
		textField_17.setVisible(false);
		textField_17.setBounds(186, 609, 116, 22);
		panel_1.add(textField_17);
		textField_17.setColumns(10);
		
		textField_25 = new JTextField();
		textField_25.setEnabled(false);
		textField_25.setVisible(false);
		textField_25.setEditable(false);
		textField_25.setBounds(300, 609, 116, 22);
		panel_1.add(textField_25);
		textField_25.setColumns(10);
		
		textField_28 = new JTextField();
		textField_28.setEnabled(false);
		textField_28.setVisible(false);
		textField_28.setEditable(false);
		textField_28.setBounds(416, 609, 116, 22);
		panel_1.add(textField_28);
		textField_28.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setEnabled(false);
		textField_6.setEditable(false);
		textField_6.setVisible(false);
		textField_6.setBounds(65, 609, 116, 22);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
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
								 textArea_1.append(""+addr1+"\n"+addr2+"\n"+city+"\n"+country+"\nPh: "+ph1+"\nTRN: "+trn+"");
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
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 61, 957, 2);
		frame.getContentPane().add(separator);
		
		if(po_num.equals(""))
		{
			btnProceed = new JButton("Start");
			btnProceed.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
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
				String query="SELECT * FROM "+db_name+".mr_sd_list_history where invoice_num ='"+po_num+"' "; 
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
					textField_27.setText(resultSet.getString("referance"));
					textArea_1.setText(resultSet.getString("cust_addr"));
					
					lblNewLabel.setText(resultSet.getString("salesman"));
					
					textField_2.setEditable(false);
					textField_3.setEditable(false);
					textArea_1.setEditable(false);
					//textField.setEditable(true);
		        	//textField_1.setEditable(true);
		        	//textField_12.setEditable(true);
		        	//textField_8.setEditable(true);
		        	//textField_9.setEditable(true);
		        	//textField_10.setEditable(true);
		        	scrollPane.setEnabled(true);
		        	table.setEnabled(true);
		        	
		        	get_item_dt(db_name,po_num,decimal);
		        	get_ven_items(db_name,resultSet.getString("cust_id"),"","");
				}
				con.close();
			}
			catch(Exception e){
				System.err.println(e);
			}
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
			String query="SELECT * FROM "+db_name+".mr_customer_items INNER JOIN "+db_name+".mr_item_master ON "+db_name+".mr_customer_items.ref_itemcode="+db_name+".mr_item_master.item_code inner join "+db_name+".mr_customer_master on "+db_name+".mr_customer_master.cust_name="+db_name+".mr_customer_items.ref_cust_id where "+db_name+".mr_customer_items.status='0' and "+db_name+".mr_customer_items.ref_cust_id='"+cust_id+"'   "+serch_str+"  group by "+db_name+".mr_item_master.item_code "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String code=resultSet.getString("item_code");
                String name=resultSet.getString("item_name");
                String barcode=resultSet.getString("barcode1");
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
                String qty=resultSet.getString("total_qty");
                model_1.addRow(new Object[]{model_1.getRowCount()+1, code, name,barcode,department,category,item_type,item_cost,selling_price,qty});
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
			String query1="SELECT disc FROM "+db_name+".mr_sd_list_history where invoice_num ='"+po_num+"' "; 
			ResultSet resultSet1 = statement.executeQuery(query1);
			double grand_disc=0;
			if(resultSet1.next())
			{
				grand_disc=Double.parseDouble(resultSet1.getString("disc"));
			}
			String query="SELECT * FROM "+db_name+".mr_sd_lines_history where ref_inv_num ='"+po_num+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
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
				
				model.insertRow(model.getRowCount(), new Object[] { model.getRowCount()+1,  barcode, itm_cde,itm_dtls,unit,cf,qty,unit_cost,itm_disc,total,tax_val,netttl,tax,tax_type,rmk,item_details,cost,margin,marginperc});
			}
			double total=0;
			double vatpric=0;
			double grnd_ttl=0;
			for(int a=0;a<model.getRowCount();a++)
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
			grnd_ttl=total+vatpric;
			BigDecimal bw = new BigDecimal(grnd_ttl);
    		bw = bw.setScale(decimal, BigDecimal.ROUND_HALF_UP);
    		grnd_ttl = bw.doubleValue();
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
            	}
			}
			statement.close();
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	
	
	
}