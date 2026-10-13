package inventory_master;

import java.awt.Color;
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
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JDateChooser;

import javax.swing.JTextArea;

public class Inv_adjustment {

	private static JFrame frame;
	private JTextField textField_2;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField;
	private static DefaultTableModel model;
	public static JTable table;
	private JDateChooser dateChooser ;
	private JLabel lblNewLabel ;
	private JLabel label_1;
	private JButton btnProceed;
	private JMenuItem menuItem_1;
	private JMenuItem menuItem_3;
	private JScrollPane scrollPane;
	public static JTextField textField_13;
	public static JTextField textField_4;
	private static JTextField textField_14;
	private static JTextField textField_15;
	private static JTextField textField_16;
	private static JTextField textField_18;
	private static JTextField textField_19;
	private static JTextField textField_20;
	private static JComboBox<String> comboBox_2;
	private JLabel lblDel;
	private boolean dot=false;
	private JButton btnprint;
	private static JTextField textField_17;
	private JMenuItem menuItem_2;
	private JButton btnNewButton;
	private JButton btnClear;
	private int decimal;
	private JTextArea textArea ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Inv_adjustment window = new Inv_adjustment("mr_pro","mr_pro","admin", "AD-20-01-000016","S01", "","","" );
					window.frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog( null, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws JSONException 
	 */
	public Inv_adjustment(String db_name,String company, String username,String po_num, String store_name,String version,String decimals,String language) throws JSONException{
		initialize(db_name,company,username,po_num, store_name, version,decimals,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param company 
	 * @param language 
	 * @param host_name 
	 * @throws JSONException 
	 */
	private void initialize(final String db_name,final String company,final String username,String po_num, final String store_name,final String version,final String decimals, final String language) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(10, 10, 975, 768);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		if(!decimals.equals("")){
			decimal=Integer.parseInt(decimals);
		}
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 968, 38);
		frame.getContentPane().add(panel);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		JLabel lblPurchaseOrder = new JLabel("Inventory Adjustment");
		lblPurchaseOrder.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPurchaseOrder.setBounds(12, 6, 228, 30);
		panel.add(lblPurchaseOrder);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int rows=model.getRowCount();
					if(rows>0)
					{
						int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Exit","Message",JOptionPane.YES_NO_OPTION);
				        // 0=yes, 1=no, 2=cancel
				        if(cofrm_bx==0)
				        {
				        	frame.setVisible(false);
				        	inventory_master.Inv_adjust_list.get_po_disp( company, "OPEN", "");
				        }
				        
					}
					else
					{
						frame.setVisible(false);
						inventory_master.Inv_adjust_list.get_po_disp( company, "OPEN", "");
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		});
		menuItem.setBorder(null);
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(733, 6, 60, 30);
		panel.add(menuItem);
		
		menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((JTextField) dateChooser.getDateEditor().getUiComponent()).setText("");
				textField_6.setText("");
				textField_5.setText("");
				textField_7.setText("");
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
					report_master.inv_adj.report(textField_2.getText(),con, "OPEN",company);
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage() );
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
				try
				{
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement(); 
					double grnd_ttl_old=Double.parseDouble(textField_7.getText());
					double grnd_ttl_new=Double.parseDouble(textField.getText());
					textField_5.getText();
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    if(grnd_ttl_old==grnd_ttl_new)
					{
						int row=model.getRowCount();
						if(row!=Integer.parseInt(textField_5.getText()))
						{
							JOptionPane.showMessageDialog(frame, "Row Count Not matching");
						}
						else
						{
							String update="update "+company+".mr_inv_trans_list set ttl_items='"+textField_5.getText()+"',ttl_price='"+textField_7.getText()+"',sub_ttl_amt='"+textField.getText()+"',modified_date='"+formattedDate+"',remark='"+textArea.getText()+"' where adj_no='"+textField_2.getText()+"'";
							statement.executeUpdate(update);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
					}
				    else
				    {
				    	int row=model.getRowCount();
						if(row!=Integer.parseInt(textField_5.getText()))
						{
							JOptionPane.showMessageDialog(frame, "Row Count Not matching");
						}
						else
						{
							int val=JOptionPane.showConfirmDialog(frame, "Grand Total not Matching, Do you want to save");
							if(val==0)
							{
								String update="update "+company+".mr_inv_trans_list set ttl_items='"+textField_5.getText()+"',ttl_price='"+textField_7.getText()+"',sub_ttl_amt='"+textField.getText()+"',modified_date='"+formattedDate+"',remark='"+textArea.getText()+"' where adj_no='"+textField_2.getText()+"'";
								statement.executeUpdate(update);
								JOptionPane.showMessageDialog(frame, "Saved Successfully");
							}
						}
				    }
				    con.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(frame,e.getMessage());
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(873, 6, 138, 30);
		panel.add(menuItem_2);
		
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setEnabled(false);
		textField_13.setVisible(false);
		textField_13.setBounds(250, 6, 116, 22);
		panel.add(textField_13);
		textField_13.setColumns(10);
		
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
		lblDate.setBounds(10, 9, 70, 16);
		panel_2.add(lblDate);
		
		Calendar dateAndTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		lblNewLabel = new JLabel(sdf.format(dateAndTime.getTime()));
		lblNewLabel.setBounds(90, 9, 188, 16);
		panel_2.add(lblNewLabel);
		
		JLabel lblPoDate = new JLabel("Order Date");
		lblPoDate.setBounds(10, 34, 70, 16);
		panel_2.add(lblPoDate);
		
		JLabel lblPoNo = new JLabel("Order No.");
		lblPoNo.setBounds(10, 59, 70, 16);
		panel_2.add(lblPoNo);
		
		textField_2 = new JTextField("NEW");
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(90, 59, 188, 16);
		panel_2.add(textField_2);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(90, 34, 188, 16);
		//dateChooser.setMinSelectableDate(new Date());
		panel_2.add(dateChooser);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(243, 229, 216));
		panel_3.setLayout(null);
		panel_3.setBounds(282, 0, 357, 84);
		panel_1.add(panel_3);
		
		JLabel lblTotalAmountOf = new JLabel("Enter Total Amount of invoice");
		lblTotalAmountOf.setBounds(12, 59, 171, 16);
		panel_3.add(lblTotalAmountOf);
		
		JLabel lblInvoiceNo = new JLabel("Document No.");
		lblInvoiceNo.setBounds(12, 9, 100, 16);
		panel_3.add(lblInvoiceNo);
		
		JLabel lblNoOfItems = new JLabel("No. of Items in Doc");
		lblNoOfItems.setBounds(12, 34, 140, 16);
		panel_3.add(lblNoOfItems);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(195, 34, 131, 16);
		panel_3.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(195, 9, 131, 16);
		panel_3.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(195, 59, 131, 16);
		panel_3.add(textField_7);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(243, 229, 216));
		panel_4.setLayout(null);
		panel_4.setBounds(638, 0, 319, 84);
		panel_1.add(panel_4);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		
		label_1 = new JLabel(username);
		label_1.setBounds(119, 0, 188, 16);
		panel_4.add(label_1);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(32, 0, 70, 16);
		panel_4.add(lblUserName);
		
		textArea = new JTextArea();
		textArea.setBounds(119, 20, 188, 58);
		panel_4.add(textArea);
		
		JLabel lblRemark = new JLabel("Remark");
		lblRemark.setBounds(29, 29, 56, 16);
		panel_4.add(lblRemark);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.setBounds(12, 159, 933, 467);
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
	    model.addColumn("Ref. ID"); 
	    model.addColumn("Barcode"); 
		model.addColumn("Item Code"); 
		model.addColumn("Item Details"); 
		model.addColumn("QtyOH"); 
		model.addColumn("Unit"); 
		model.addColumn("cf"); 
		model.addColumn("Unit Cost"); 
		model.addColumn("Qty"); 
		
		scrollPane.setViewportView(table);
		
		table.setFocusable(false);
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int colmns=model.getColumnCount();
		for (int i=0; i<colmns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(35);
		  if (i==1) column.setPreferredWidth(10);
		  if (i==2) column.setPreferredWidth(110);
		  if (i==3) column.setPreferredWidth(75);
		  if (i==4) column.setPreferredWidth(250);
		  if (i==5) column.setPreferredWidth(30);
		  if (i==6) column.setPreferredWidth(50);
		  if (i==7) column.setPreferredWidth(30);
		  if (i==8) column.setPreferredWidth(70);
		  if (i==9) column.setPreferredWidth(75);
		  if (i==10) column.setPreferredWidth(80);
		}
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table_2 = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table_2.getSelectedRow() != -1) {
					int rows = table_2.getSelectedRow();
					textField_4.setText(table_2.getModel().getValueAt(rows, 2).toString());
					textField_15.setText(table_2.getModel().getValueAt(rows, 3).toString());
					textField_16.setText(table_2.getModel().getValueAt(rows, 4).toString());
					textField_14.setText(table_2.getModel().getValueAt(rows, 5).toString());
					String val=table_2.getModel().getValueAt(rows, 6).toString();
					get_item_barcode_list(company,table_2.getModel().getValueAt(rows, 3).toString(),val);
					textField_4.setText(table_2.getModel().getValueAt(rows, 2).toString());
					textField_18.setText(table_2.getModel().getValueAt(rows, 7).toString());
					textField_19.setText(table_2.getModel().getValueAt(rows, 9).toString());
					textField_20.setText(table_2.getModel().getValueAt(rows, 8).toString());
					textField_17.setText(table_2.getModel().getValueAt(rows, 2).toString());
				}
			}
		});
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(709, 626, 236, 42);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 10, 70, 22);
		panel_5.add(lblTotal);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setText("0.0");
		textField.setEditable(false);
		textField.setBounds(100, 10, 116, 22);
		panel_5.add(textField);
		textField.setColumns(10);
		
		
		Image del=new ImageIcon(this.getClass().getResource("/del.png")).getImage();
		lblDel= new JLabel("");
		lblDel.setEnabled(false);
		lblDel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try
				{
					int row=table.getSelectedRow();
					if(row>-1)
					{
						int cfm= JOptionPane.showConfirmDialog(frame, "Do You Want to Delete This Line","" ,JOptionPane.YES_NO_OPTION);
						if(cfm==0)
						{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							Statement statement1=con.createStatement();  
							int rows=table.getRowCount()-1;
							if(rows>1)
							{
								String query="SELECT * FROM "+company+".mr_inv_trans where ref_adj_num='"+textField_2.getText()+"' and id='"+table.getValueAt(row, 1).toString()+"' "; 
								ResultSet resultSet = statement.executeQuery(query);
								if(resultSet.next())
								{
									 String query1="delete from "+company+".mr_inv_trans  where ref_adj_num='"+textField_2.getText()+"' and id='"+table.getValueAt(row, 1).toString()+"' ";
									 statement1.executeUpdate(query1);
								}
							}
							else
							{
								String query="SELECT * FROM "+company+".mr_inv_trans where ref_adj_num='"+textField_2.getText()+"' and id='"+table.getValueAt(row, 1).toString()+"' "; 
								ResultSet resultSet = statement.executeQuery(query);
								if(resultSet.next())
								{
									 String query1="delete from "+company+".mr_inv_trans  where ref_adj_num='"+textField_2.getText()+"' and id='"+table.getValueAt(row, 1).toString()+"' ";
									 statement1.executeUpdate(query1);
								}
							}
							con.close();
							model.removeRow(row);
							for(int i=0;i<model.getRowCount();i++)
							{
								model.setValueAt(i+1, i, 0);
							}
							double total=0; 
			    			for(int a=0;a<rows;a++)
			    			{
			    				String cost=model.getValueAt(a, 8).toString();
			    				String qtyOH=model.getValueAt(a, 9).toString();
			    				if(cost.equals(""))
			    				{
			    					cost="0";
			    				}
			    				if(qtyOH.equals(""))
			    				{
			    					qtyOH="0";
			    				}
			    				double ttl=Double.parseDouble(cost)*Double.parseDouble(qtyOH);
			    				total=total+ttl;
			    				int decimalPlaces = 2;
			            		BigDecimal bd = new BigDecimal(total);
			            		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
			            		total = bd.doubleValue();
			    			}
			    			textField.setText(Double.toString(total));
			    			
			    			
			    			for(int i=0;i<rows;i++)
			    			{
			    				model.setValueAt(i+1, i, 0);
			    				String value = model.getValueAt(i, 1).toString();
			    				if(value=="")
			    				{
			    					model.removeRow(i);
			    				}
			    			}
			    			textField_4.setText("");
			    			textField_15.setText("");
							textField_16.setText("");
							textField_14.setText("");
							textField_18.setText("");
							textField_19.setText("");
							textField_20.setText("");
							textField_4.requestFocus();
							comboBox_2.removeAllItems();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Select One Row from Table");
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		});
		lblDel.setIcon(new ImageIcon(del));
		lblDel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				lblDel.setBorder(null);
			}
		});
		lblDel.setBounds(935, 108, 22, 25);
		panel_1.add(lblDel);
		
		btnNewButton = new JButton("");
		btnNewButton.setEnabled(false);
		btnNewButton.setToolTipText("Item Code List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Connection con=Dbsetup.dbconn.getConnection();
					new item_list_master(company,company, "Inv_adj", "", version,decimal,con,"",language);
										
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage());
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(8, 108, 35, 25);
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
				validate_barcode(company,company, textField_4.getText(),textField_4.getText(),textField_13.getText(), username, version,store_name,decimal);
			}
		});
		textField_4.setBounds(51, 111, 107, 22);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_4.getText().equals(""))
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
		
		JLabel lblBarcodeitemcode = new JLabel("Barcode/Itemcode");
		lblBarcodeitemcode.setBounds(15, 86, 142, 22);
		panel_1.add(lblBarcodeitemcode);
		
		JLabel lblQty = new JLabel("Qty OH");
		lblQty.setBounds(413, 86, 56, 22);
		panel_1.add(lblQty);
		
		textField_14 = new JTextField();
		textField_14.setEditable(false);
		textField_14.setBounds(410, 111, 56, 22);
		panel_1.add(textField_14);
		textField_14.setColumns(10);
		textField_14.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_14.getText().equals(""))
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
		
		JLabel lblItemcode = new JLabel("Itemcode");
		lblItemcode.setBounds(169, 86, 72, 22);
		panel_1.add(lblItemcode);
		
		textField_15 = new JTextField();
		textField_15.setEditable(false);
		textField_15.setBounds(166, 111, 72, 22);
		panel_1.add(textField_15);
		textField_15.setColumns(10);
		
		JLabel lblItemDetails = new JLabel("Item Details");
		lblItemDetails.setBounds(250, 86, 156, 22);
		panel_1.add(lblItemDetails);
		
		JLabel lblUnit = new JLabel("unit");
		lblUnit.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnit.setBounds(481, 86, 114, 22);
		panel_1.add(lblUnit);
		
		JLabel lblCf = new JLabel("CF");
		lblCf.setHorizontalAlignment(SwingConstants.CENTER);
		lblCf.setBounds(607, 86, 35, 22);
		panel_1.add(lblCf);
		
		JLabel lblUnitCost = new JLabel("Qty");
		lblUnitCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnitCost.setBounds(716, 86, 56, 22);
		panel_1.add(lblUnitCost);
		
		JLabel lblItemDisc = new JLabel("Unit Cost");
		lblItemDisc.setBounds(648, 86, 56, 22);
		panel_1.add(lblItemDisc);
		
		textField_16 = new JTextField();
		textField_16.setEditable(false);
		textField_16.setBounds(246, 111, 156, 22);
		panel_1.add(textField_16);
		textField_16.setColumns(10);
		
		textField_18 = new JTextField();
		textField_18.setEditable(false);
		textField_18.setColumns(10);
		textField_18.setBounds(595, 111, 41, 22);
		panel_1.add(textField_18);
		
		textField_19 = new JTextField();
		textField_19.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					btnprint.requestFocus();
				}
			}
		});
		textField_19.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				btnprint.requestFocus();
			}
		});
		textField_19.setEditable(false);
		textField_19.setColumns(10);
		textField_19.setBounds(708, 111, 56, 22);
		panel_1.add(textField_19);
		
		textField_20 = new JTextField();
		textField_20.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					textField_19.requestFocus();
				}
			}
		});
		textField_20.setEditable(false);
		textField_20.setColumns(10);
		textField_20.setBounds(644, 111, 56, 22);
		panel_1.add(textField_20);
		
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setEnabled(false);
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_2.getItemCount()>0)
				{
					validate_barcode_unit(company, textField_15.getText(),comboBox_2.getSelectedItem().toString());
				}
			}
		});
		comboBox_2.setBounds(474, 111, 113, 22);
		panel_1.add(comboBox_2);
		
		btnprint = new JButton("Enter");
		btnprint.setEnabled(false);
		btnprint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	enterbtn(company, version);
				}

			}
		});
		btnprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enterbtn(company, version);
			}
		});
		btnprint.setBounds(772, 97, 72, 36);
		
		panel_1.add(btnprint);
		
		textField_17 = new JTextField();
		textField_17.setEditable(false);
		textField_17.setEnabled(false);
		textField_17.setVisible(false);
		textField_17.setBounds(12, 136, 116, 22);
		panel_1.add(textField_17);
		textField_17.setColumns(10);
		
		btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		btnClear.setBounds(852, 97, 75, 36);
		panel_1.add(btnClear);
		
		new DecimalFormat("#");
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_4.setText("");
    			textField_15.setText("");
				textField_16.setText("");
				textField_14.setText("");
				textField_18.setText("");
				textField_19.setText("");
				textField_20.setText("");
				textField_4.requestFocus();
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
					try
					{
						String com_date=lblNewLabel.getText();
						String user=label_1.getText();
						String po_num=textField_2.getText();
						String po_name="inventory adjust";
						String podate = null;
						LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);
					    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					    String po_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
						if(!po_date.equals(""))
						{
							podate = sdf.format(dateChooser.getDate());
						}
						String supp_invo=textField_6.getText();
						String num_itm_invo=textField_5.getText();
						String grnd_ttl=textField_7.getText();
						//int print=0;
						int ttl_cst=0;
						
						
						if(po_date.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Select date from date chooser");
							dateChooser.requestFocus();
						}
						else if(supp_invo.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Please enter the Document number");
							textField_6.requestFocus();
						}
						else if(num_itm_invo.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Please enter the Number of items in Document");
							textField_5.requestFocus();
						}
						else if(grnd_ttl.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Please enter the Grand total in Document");
							textField_7.requestFocus();
						}
						else
						{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="SELECT * FROM "+company+".mr_inv_trans_list where adj_no ='"+po_num+"' and status!='VOID' "; 
							ResultSet resultSet = statement.executeQuery(query);
							if(!resultSet.next())
							{
								String seq_num = null;
								if(po_num.equals("NEW"))
								{
									String trans_seq = "select tran_seq from "+company+".mr_transaction_seq where tran_name='"+po_name+"' ";
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
								    	String save_po="update "+company+".mr_transaction_seq set tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_name='"+po_name+"' ";
										statement.executeUpdate(save_po);
								    }
								}
								String user_id = user;
								String save_po="insert into "+company+".mr_inv_trans_list(`com_date`, `adj_date`, `user_id`, `adj_no`, `invo_nu`, `ttl_items`, `ttl_price`,sub_ttl_amt, `assume_ttl_cst`, status,store_name, `created_date`, `modified_date`,remark) values('"+com_date+"','"+podate+"','"+user_id+"','"+seq_num+"','"+supp_invo+"','"+num_itm_invo+"','"+grnd_ttl+"',0,'"+ttl_cst+"','OPEN','"+store_name+"','"+formattedDate+"','"+formattedDate+"','"+textArea.getText()+"' )";
								statement.executeUpdate(save_po);
								textField_2.setText(seq_num);
					        	textField_6.setEditable(false);
					        	dateChooser.setEnabled(false);
					        	btnProceed.setEnabled(false);
					        	menuItem_1.setEnabled(false);
					        	menuItem_2.setEnabled(true);
					        	scrollPane.setEnabled(true);
					        	textField_4.setEditable(true);
					        	comboBox_2.setEnabled(true);
					        	btnprint.setEnabled(true);
					        	lblDel.setEnabled(true);
					        	textField_19.setEditable(true);
					        	textField_20.setEditable(true);
					        	table.setEnabled(true);
					        	btnNewButton.setEnabled(true);
					        	btnClear.setEnabled(true);
					        	textField_4.requestFocus();
							}
							con.close();
								
						}
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
					}
				}
			});
			btnProceed.setBounds(575, 6, 70, 25);
			panel.add(btnProceed);
			dateChooser.requestFocus();
		}
		else
		{
			try{
				
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				String query="SELECT * FROM "+company+".mr_inv_trans_list where adj_no ='"+po_num+"' "; 
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next())
				{
					lblNewLabel.setText(resultSet.getString("com_date"));
					java.util.Date date =null;
					if(resultSet.getString("adj_date")!="")
					{
						date = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("adj_date"));
					}
					dateChooser.setDate(date);
					label_1.setText(resultSet.getString("user_id"));
					textField_2.setText(resultSet.getString("adj_no"));
					textField_6.setText(resultSet.getString("invo_nu"));
					textField_5.setText(resultSet.getString("ttl_items"));
					textField_7.setText(resultSet.getString("ttl_price"));
					
					textField_2.setEditable(false);
		        	textField_4.setEditable(true);
		        	comboBox_2.setEnabled(true);
		        	btnprint.setEnabled(true);
		        	lblDel.setEnabled(true);
		        	textField_19.setEditable(true);
		        	textField_20.setEditable(true);
		        	textField_6.setEditable(false);
		        	dateChooser.setEnabled(false);
		        	menuItem_1.setEnabled(false);
		        	scrollPane.setEnabled(true);
		        	table.setEnabled(true);
		        	menuItem_2.setEnabled(true);
		        	btnNewButton.setEnabled(true);
		        	btnClear.setEnabled(true);
		        	get_item_dt(company, po_num);
				}
				con.close();
			}
			catch(Exception e){
				JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
			}
		}
	}
	private void enterbtn(String company, String version)
	{
		try {
			String barcode =textField_4.getText();
			String itm_cde =textField_15.getText();
			String itm_dtls =textField_16.getText();
			String qtyOh =textField_14.getText();
			String unit =comboBox_2.getSelectedItem().toString();
			String unit_cost =textField_20.getText();
			String qty =textField_19.getText();
			if(qty.equals(""))
			{
				qty="0";
			}
			if(unit_cost.equals(""))
			{
				unit_cost="0";
			}
			String cf =textField_18.getText();
			LocalDateTime myDateObj = LocalDateTime.now();  
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		    String formattedDate = myDateObj.format(myFormatObj);  
			if(textField_17.getText().equals(textField_4.getText()))
			{
				int sel_row=table.getSelectedRow();
				String idvalue=table.getModel().getValueAt(sel_row, 1).toString();
				model.setValueAt(qtyOh, sel_row, 5);
				model.setValueAt(unit, sel_row, 6);
				model.setValueAt(cf, sel_row, 7);
				model.setValueAt(unit_cost, sel_row, 8);
				model.setValueAt(qty, sel_row, 9);
				textField_17.setText("");
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				String query="SELECT * FROM "+company+".mr_inv_trans where ref_adj_num='"+textField_2.getText()+"' and id='"+idvalue+"' "; 
				ResultSet resultSet = statement.executeQuery(query);
				if(resultSet.next())
				{
					String update="update "+company+".mr_inv_trans set unit_cost='"+textField_20.getText()+"',qty='"+textField_19.getText()+"',item_name='"+itm_dtls+"',modified_date='"+formattedDate+"' where ref_adj_num='"+textField_2.getText()+"' and id='"+idvalue+"' ";
					statement.executeUpdate(update);
				}
				con.close();
			}
			else
			{
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement(); 
				String insert_id="";
				
				String insert="insert into "+company+".mr_inv_trans (ref_adj_num,barcode,item_code,qtyOh,unit,cf,unit_cost,qty,created_date,modified_date,item_name) values('"+textField_2.getText()+"','"+textField_4.getText()+"','"+textField_15.getText()+"','"+textField_14.getText()+"','"+comboBox_2.getSelectedItem().toString()+"','"+textField_18.getText()+"','"+textField_20.getText()+"','"+textField_19.getText()+"','"+formattedDate+"','"+formattedDate+"','"+itm_dtls+"')";
				statement.executeUpdate(insert);
				String query="SELECT MAX(id) as insert_id FROM "+company+".mr_inv_trans  "; 
				ResultSet resultSet = statement.executeQuery(query);
				if(resultSet.next())
				{
					insert_id=resultSet.getString("insert_id");
				}
				con.close();
				model.addRow(new Object[]{model.getRowCount()+1,insert_id, barcode, itm_cde,itm_dtls,qtyOh,unit,cf,unit_cost,qty});
			}
			double total_amt=0; 
			int rows=model.getRowCount();
			
			for(int a=0;a<rows;a++)
			{
				String cost=model.getValueAt(a, 8).toString();
				String qtyOH=model.getValueAt(a, 9).toString();
				if(cost.equals(""))
				{
					cost="0";
				}
				if(qtyOH.equals(""))
				{
					qtyOH="0";
				}
				double ttl=Double.parseDouble(cost)*Double.parseDouble(qtyOH);
				total_amt=total_amt+ttl;
				int decimalPlaces = 2;
	    		BigDecimal bd = new BigDecimal(total_amt);
	    		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
	    		total_amt = bd.doubleValue();
			}
			textField.setText(Double.toString(total_amt));
			textField_4.setText("");
			textField_15.setText("");
			textField_16.setText("");
			textField_14.setText("");
			textField_18.setText("");
			textField_19.setText("");
			textField_20.setText("");
			textField_4.requestFocus();
			comboBox_2.removeAllItems();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
		
	}
	
	public void get_item_barcode_list(String company,String itemcode,String unit){
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement1=con.createStatement();  
			String query1="SELECT * FROM "+company+".mr_item_master where item_code='"+itemcode+"' "; 
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
			JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}
	public static void validate_barcode(String db_name,String company, String input,String barcode,String vendor_id, String username,String version,String store,int decimal){
		
		try{
//			Connection con=Dbsetup.dbconn.getConnection();
//			Statement statement=con.createStatement(); 
//			String query="";
//			if(barcode.length()>8)
//			{
//				query="SELECT * FROM "+company+".mr_tmp_item_master where  barcode='"+barcode+"' ";
//			}
//			else
//			{
//				query="SELECT * FROM "+company+".mr_tmp_item_master where item_code='"+input+"' ";
//			}
//			
//			ResultSet resultSet1 = statement.executeQuery(query);
//			if(resultSet1.next())
//			{
//				ResultSet resultSet = statement.executeQuery(query);
//				if(resultSet.next())
//				{
//					get_itemdtls_bsd_code(company,resultSet.getString("item_code"),resultSet.getString("barcode"));
//				}
//			}
//			else
//			{
//				int a=JOptionPane.showConfirmDialog(frame, "No Item Found , Do You Want Add This Item");
//				if(a==0)
//				{
//					new inventory_master.Item_master(db_name,company, "NEW", "",vendor_id,"PO","", version,store,decimal,"");
//				}
//				else
//				{
//					textField_4.setText("");
//				}
//			}
//			con.close();
			
			Connection con = Dbsetup.dbconn.getConnection();

			String query = "SELECT * FROM " + company + ".mr_tmp_item_master WHERE barcode = ? OR item_code = ?";

			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ps.setString(1, input);
			ps.setString(2, input);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
			    get_itemdtls_bsd_code(company,rs.getString("item_code"),rs.getString("barcode"));
			} else {
			    int a = JOptionPane.showConfirmDialog( frame,"No Item Found , Do You Want Add This Item" );

			    if (a == JOptionPane.YES_OPTION) {new inventory_master.Item_master(db_name, company, "NEW", "",vendor_id, "PO", "",version, store, decimal, "" );
			    } else {
			        textField_4.setText("");
			    }
			}

			con.close();

			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}
	public void validate_barcode_unit(String company,  String itemcode, String unitname) {
	try{
		String barcode = null;
		String cf=null;
		double qty=0;
		Connection con=Dbsetup.dbconn.getConnection();
		Statement statement=con.createStatement();  
		String query="SELECT * FROM "+company+".mr_item_master where item_code='"+itemcode+"' and '"+unitname+"' IN (unit1,unit2,unit3,unit4,unit5,unit6) "; 
		ResultSet resultSet = statement.executeQuery(query);
		while(resultSet.next())
		{
			qty=Double.parseDouble(resultSet.getString("total_qty"));
			if(unitname.equals(resultSet.getString("unit1")))
			{
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
        	qty=qty/Double.parseDouble(cf);
        	BigDecimal bv = new BigDecimal(qty);
    		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
    		qty = bv.doubleValue();
        	textField_14.setText(Double.toString(qty));
		}
		con.close();
	}
	catch(Exception e){
		JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
	}
}
	public void get_barcode_price(String company,String barcode){
		try
		{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_item_master where '"+barcode+"' IN ("+company+".mr_item_master.barcode1,"+company+".mr_item_master.barcode2,"+company+".mr_item_master.barcode3,"+company+".mr_item_master.barcode4,"+company+".mr_item_master.barcode5,"+company+".mr_item_master.barcode6,"+company+".mr_item_master.barcode7,"+company+".mr_item_master.barcode8,"+company+".mr_item_master.barcode9,"+company+".mr_item_master.barcode10,"+company+".mr_item_master.barcode11,"+company+".mr_item_master.barcode12,"+company+".mr_item_master.barcode13,"+company+".mr_item_master.barcode14,"+company+".mr_item_master.barcode15,"+company+".mr_item_master.barcode16,"+company+".mr_item_master.barcode17,"+company+".mr_item_master.barcode18)";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				if(!resultSet.getString("barcode1").equals("") || !resultSet.getString("barcode2").equals("") || !resultSet.getString("barcode3").equals(""))
				{
					resultSet.getString("sp1");
				}
				if(!resultSet.getString("barcode4").equals("") || !resultSet.getString("barcode5").equals("") || !resultSet.getString("barcode6").equals(""))
				{
					resultSet.getString("sp2");
				}
				if(!resultSet.getString("barcode7").equals("") || !resultSet.getString("barcode8").equals("") || !resultSet.getString("barcode9").equals(""))
				{
					resultSet.getString("sp3");
				}
				if(!resultSet.getString("barcode10").equals("") || !resultSet.getString("barcode11").equals("") || !resultSet.getString("barcode12").equals(""))
				{
					resultSet.getString("sp4");
				}
				if(!resultSet.getString("barcode13").equals("") || !resultSet.getString("barcode14").equals("") || !resultSet.getString("barcode15").equals(""))
				{
					resultSet.getString("sp5");
				}
				if(!resultSet.getString("barcode16").equals("") || !resultSet.getString("barcode17").equals("") || !resultSet.getString("barcode18").equals(""))
				{
					resultSet.getString("sp6");
				}
			}
			con.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}	
	
	public static void get_itemdtls_bsd_code(String company, String input,String scan_barcode) {
	try{
		Connection con=Dbsetup.dbconn.getConnection();
		Statement statement=con.createStatement();  
		String query="SELECT * FROM "+company+".mr_tmp_item_master where item_code='"+input+"' ";  
		ResultSet resultSet = statement.executeQuery(query);
		if(resultSet.next())
		{
			textField_15.setText("");
			textField_16.setText("");
			textField_14.setText("");
			textField_18.setText("");
			textField_19.setText("");
			textField_20.setText("0");
			comboBox_2.removeAllItems();
			String barcode =scan_barcode;
			textField_15.setText(resultSet.getString("item_code"));
			textField_16.setText(resultSet.getString("item_name"));
			textField_14.setText(resultSet.getString("qty"));
			textField_20.setText(resultSet.getString("cost"));
			
			textField_18.setText(resultSet.getString("cf"));
			textField_19.requestFocus();
			Statement statement1=con.createStatement();  
			String query1="SELECT * FROM "+company+".mr_item_master where item_code='"+input+"' "; 
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
			textField_4.setText(barcode);
		}
		con.close();
	}
	catch(Exception e){
		JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
	}
}	
	public void get_item_dt(String company, String po_num) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_inv_trans where ref_adj_num ='"+po_num+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String barcode =resultSet.getString("barcode");
				String itm_cde =resultSet.getString("item_code");
				float qtyOH =Float.parseFloat(resultSet.getString("qtyOh")) ;
				String unit =resultSet.getString("unit");
				float cf =Float.parseFloat(resultSet.getString("cf")) ;
				double unit_cost =Double.parseDouble(resultSet.getString("unit_cost"));
				float qty =Float.parseFloat(resultSet.getString("qty")) ;
				String itm_dtls = resultSet.getString("item_name");
				String id = resultSet.getString("id");
				model.insertRow(model.getRowCount(), new Object[] { model.getRowCount()+1, id, barcode, itm_cde,itm_dtls,qtyOH,unit,cf,unit_cost,qty});
			}
			double total=0;
			for(int a=0;a<model.getRowCount();a++)
			{
				String cost=model.getValueAt(a, 8).toString();
				String qtyOH=model.getValueAt(a, 9).toString();
				if(cost.equals(""))
				{
					cost="0";
				}
				if(qtyOH.equals(""))
				{
					qtyOH="0";
				}
				double ttl=Double.parseDouble(cost)*Double.parseDouble(qtyOH);
				total=total+ttl;
				int decimalPlaces = 2;
        		BigDecimal bd = new BigDecimal(total);
        		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		total = bd.doubleValue();
			}
			textField.setText(Double.toString(total));
			textField_4.requestFocus();
			statement.close();
			con.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}
}