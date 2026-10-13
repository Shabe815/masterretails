package purchase_master;

import inventory_master.item_list_master;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
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

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import purchase_master.vendor_details;
import purchase_master.vendor_list;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class purchase_return {

	static JFrame frame;
	private String supp_name;
	private JTextField textField_2;
	public static JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
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
	private JCheckBox checkBox;
	private JLabel lblNewLabel ;
	private JLabel label_1;
	private JComboBox <String> comboBox;
	private JButton btnSearch;
	private JButton btnProceed;
	private JMenuItem menuItem_1;
	private JMenuItem menuItem_3;
	private JButton button;
	private JButton btnNew;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane;
	private JTextField textField_12;
	public static JTextField textField_13;
	public static JTextField textField_4;
	private static JTextField textField_14;
	private static JTextField textField_15;
	private static JTextField textField_16;
	private static JTextField textField_18;
	private static JTextField textField_19;
	private static JTextField textField_20;
	private static JTextField textField_21;
	private static JTextField textField_22;
	private static JTextField textField_23;
	private static JTextField textField_24;
	private static JComboBox<String> comboBox_2;
	private JLabel lblDel;
	private boolean dot=false;
	private JButton btnprint;
	private static JTextField textField_17;
	private JMenuItem menuItem_2;
	//private JButton btnNewButton;
	private JButton btnClear;
	private static int decimal;
	private static JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_27;
	private static JTextField textField_28;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					purchase_return window = new purchase_return("mr_pro","mr_pro","admin","P0001","PR-19-01-000022","S01", "","2","" );
					purchase_return.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param username 
	 * @param company 
	 * @param host_name 
	 * @param po_num 
	 * @throws JSONException 
	 * @throws SQLException 
	 * @wbp.parser.entryPoint
	 */
	public purchase_return(String db_name,String company, String username,String vendor_id, String po_num, String store_name,String version,String decimal,String language) throws JSONException, SQLException {
		initialize(db_name,company,username,vendor_id,po_num, store_name, version,decimal, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param company 
	 * @param host_name 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String db_name,final String company,final String username,final String vendor_id,final String po_num, final String store_name,final String version,final String decimals,final String language) throws JSONException, SQLException {
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
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		JLabel lblPurchaseOrder = new JLabel("Purchase Return");
		lblPurchaseOrder.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPurchaseOrder.setBounds(12, 6, 228, 30);
		panel.add(lblPurchaseOrder);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				purchase_return_list.btnNewButton.setEnabled(true);
				//purchase_return_list.frame.setAlwaysOnTop(true);
				int rows=model.getRowCount();
				if(rows>0)
				{
					int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Exit","Warning",JOptionPane.YES_NO_OPTION);
			        // 0=yes, 1=no, 2=cancel
			        if(cofrm_bx==0)
			        {
			        	frame.setVisible(false);
			        	purchase_master.purchase_return_list.get_po_disp(company, "OPEN", "");
			        }
			        
				}
				else
				{
					frame.setVisible(false);
					purchase_master.purchase_return_list.get_po_disp(company, "OPEN", "");
					
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
				((JTextField) dateChooser.getDateEditor().getUiComponent()).setText("");
				textField_6.setText("");
				textField_5.setText("");
				textField_7.setText("");
				textField_3.setText("");
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
					report_master.Pr_report.report(textField_2.getText(),con, "OPEN",company);
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
				try
				{
					double grnd_ttl_old=Double.parseDouble(textField_7.getText());
					double grnd_ttl_new=Double.parseDouble(textField.getText());
					String disc_perc=textField_1.getText();
					if(disc_perc.equals(""))
					{
						disc_perc="0";
					}
					else
					{
						String disc_amt=textField_12.getText();
						if(disc_amt.equals(""))
						{
							disc_amt="0";
						}
						double percent=(Double.parseDouble(disc_amt)*100)/grnd_ttl_new;
						disc_perc=Double.toString(percent);
					}
					String num_itm_invo=textField_5.getText();
					String currncy = (String) comboBox.getSelectedItem();
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    String remark=textField_26.getText();
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				    String podate = null;
				    String po_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					if(!po_date.equals(""))
					{
						podate = sdf.format(dateChooser.getDate());
					}
					int ttl_cst=0;
					if (checkBox.isSelected()) {
						ttl_cst=1;
					}
					if(grnd_ttl_old==grnd_ttl_new)
					{
						double row=model.getRowCount();
						if(row!=Double.parseDouble(textField_5.getText()))
						{
							JOptionPane.showMessageDialog(frame, "Row Count Not matching","Warning",JOptionPane.YES_NO_OPTION);
						}
						else
						{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="SELECT * FROM "+company+".mr_purchase_return where ref_pr_num='"+textField_2.getText()+"' "; 
							ResultSet resultSet = statement.executeQuery(query);
							if(resultSet.next())
							{
								String query1="delete FROM "+company+".mr_purchase_return where ref_pr_num='"+textField_2.getText()+"' ";
								statement.executeUpdate(query1);
								for(int i=0;i<model.getRowCount();i++)
								{
									double unitcost=Double.parseDouble(table.getValueAt(i, 9).toString())/Double.parseDouble(table.getValueAt(i, 4).toString());
									String query2="insert into "+company+".mr_purchase_return(`ref_pr_num`, `barcode`, `item_code`, `qty`, `unit`, `cf`, `unit_cost`,`itm_disc`, `itm_cost_after_disc`, `sub_ttl_disc`, `sub_cost_after_sub_ttl`, `tax_val`, `total`, `cost_after_sub_ttl`, `created_date`, `modified_date`) VALUES"
											+ " ('"+textField_2.getText()+"','"+table.getValueAt(i, 1).toString()+"','"+table.getValueAt(i, 2).toString()+"','"+table.getValueAt(i, 4).toString()+"','"+table.getValueAt(i, 5).toString()+"','"+table.getValueAt(i, 6).toString()+"','"+table.getValueAt(i, 7).toString()+"','"+table.getValueAt(i, 8).toString()+"','"+table.getValueAt(i, 9).toString()+"','0','"+table.getValueAt(i, 9).toString()+"','"+table.getValueAt(i, 12).toString()+"','"+table.getValueAt(i, 9).toString()+"','"+unitcost+"','"+formattedDate+"','"+formattedDate+"')";
									statement.executeUpdate(query2);
								}
								String query3="update "+company+".mr_purchase_return_list set remark='"+remark+"',pr_date='"+podate+"',sub_ttl_amt='"+grnd_ttl_new+"',grand_disc='"+disc_perc+"',total_vat='"+textField_9.getText()+"',grand_total='"+textField_10.getText()+"', status='OPEN',store_name='"+store_name+"',ttl_price='"+grnd_ttl_old+"',ttl_items='"+num_itm_invo+"',referance='"+textField_27.getText()+"',currency_id='"+currncy+"',assume_ttl_cst='"+ttl_cst+"' where pr_no='"+textField_2.getText()+"'";
								statement.executeUpdate(query3);
								JOptionPane.showMessageDialog(frame, "Saved Successfully");
							}
							con.close();
						}
					}
					else
					{
						double row=model.getRowCount();
						if(row!=Double.parseDouble(textField_5.getText()))
						{
							JOptionPane.showMessageDialog(frame, "Row Count Not matching");
						}
						else
						{
							int val=JOptionPane.showConfirmDialog(frame, "Total Amount not Matching, Do you want to save","Warning",JOptionPane.YES_NO_OPTION);
							if(val==0)
							{
								Connection con=Dbsetup.dbconn.getConnection();
								Statement statement=con.createStatement();  
								String query="SELECT * FROM "+company+".mr_purchase_return where ref_pr_num='"+textField_2.getText()+"'"; 
								ResultSet resultSet = statement.executeQuery(query);
								if(resultSet.next())
								{
									String query1="delete FROM "+company+".mr_purchase_return where ref_pr_num='"+textField_2.getText()+"'  ";
									statement.executeUpdate(query1);
									for(int i=0;i<model.getRowCount();i++)
									{
										double unitcost=Double.parseDouble(table.getValueAt(i, 9).toString())/Double.parseDouble(table.getValueAt(i, 4).toString());
										String query2="insert into "+company+".mr_purchase_return(`ref_pr_num`, `barcode`, `item_code`, `qty`, `unit`, `cf`, `unit_cost`,`itm_disc`, `itm_cost_after_disc`, `sub_ttl_disc`, `sub_cost_after_sub_ttl`, `tax_val`, `total`, `cost_after_sub_ttl`, `created_date`, `modified_date`) VALUES"
												+ " ('"+textField_2.getText()+"','"+table.getValueAt(i, 1).toString()+"','"+table.getValueAt(i, 2).toString()+"','"+table.getValueAt(i, 4).toString()+"','"+table.getValueAt(i, 5).toString()+"','"+table.getValueAt(i, 6).toString()+"','"+table.getValueAt(i, 7).toString()+"','"+table.getValueAt(i, 8).toString()+"','"+table.getValueAt(i, 9).toString()+"','0','"+table.getValueAt(i, 9).toString()+"','"+table.getValueAt(i, 12).toString()+"','"+table.getValueAt(i, 9).toString()+"','"+unitcost+"','"+formattedDate+"','"+formattedDate+"')";
										statement.executeUpdate(query2);
									}
									String query3="update "+company+".mr_purchase_return_list set remark='"+remark+"',pr_date='"+podate+"',sub_ttl_amt='"+grnd_ttl_new+"',grand_disc='"+disc_perc+"',total_vat='"+textField_9.getText()+"',grand_total='"+textField_10.getText()+"', status='OPEN',store_name='"+store_name+"',ttl_price='"+grnd_ttl_old+"',ttl_items='"+num_itm_invo+"',referance='"+textField_27.getText()+"',currency_id='"+currncy+"',assume_ttl_cst='"+ttl_cst+"' where pr_no='"+textField_2.getText()+"'";
									statement.executeUpdate(query3);
									JOptionPane.showMessageDialog(frame, "Saved Successfully");
									purchase_return_list.btnNewButton.setEnabled(true);
									purchase_return_list.frame.setAlwaysOnTop(true);
								}
								con.close();
							}
						}
					}
				}
				catch (Exception e)
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
		lblDate.setBounds(10, 4, 70, 16);
		panel_2.add(lblDate);
		
		Calendar dateAndTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		lblNewLabel = new JLabel(sdf.format(dateAndTime.getTime()));
		lblNewLabel.setBounds(90, 4, 188, 16);
		panel_2.add(lblNewLabel);
		
		JLabel lblPoDate = new JLabel("PO Date");
		lblPoDate.setBounds(10, 24, 70, 16);
		panel_2.add(lblPoDate);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(10, 44, 70, 16);
		panel_2.add(lblUserName);
		
		JLabel lblPoNo = new JLabel("PO No.");
		lblPoNo.setBounds(10, 64, 70, 16);
		panel_2.add(lblPoNo);
		
		textField_2 = new JTextField("NEW");
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(90, 64, 188, 16);
		panel_2.add(textField_2);
		
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					textField_6.requestFocus();
                }
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_6.requestFocus();
                }
			}
		});
		dateChooser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				textField_6.requestFocus();
			}
		});
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(90, 24, 188, 16);
		//dateChooser.setMinSelectableDate(new Date());
		panel_2.add(dateChooser);
		
		label_1 = new JLabel(username);
		label_1.setBounds(90, 44, 188, 16);
		panel_2.add(label_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(243, 229, 216));
		panel_3.setLayout(null);
		panel_3.setBounds(282, 0, 357, 84);
		panel_1.add(panel_3);
		
		JLabel lblTotalAmountOf = new JLabel("Enter Total Amount of invoice");
		lblTotalAmountOf.setBounds(12, 44, 171, 16);
		panel_3.add(lblTotalAmountOf);
		
		JLabel lblInvoiceNo = new JLabel("Supp. Invoice No.");
		lblInvoiceNo.setBounds(12, 4, 100, 16);
		panel_3.add(lblInvoiceNo);
		
		JLabel lblNoOfItems = new JLabel("No. of Lines in PO");
		lblNoOfItems.setBounds(12, 24, 140, 16);
		panel_3.add(lblNoOfItems);
		
		textField_5 = new JTextField();
		textField_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_7.requestFocus();
			}
		});
		textField_5.setColumns(10);
		textField_5.setBounds(195, 24, 131, 16);
		panel_3.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_5.requestFocus();
			}
		});
		textField_6.setColumns(10);
		textField_6.setBounds(195, 4, 131, 16);
		panel_3.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				button.requestFocus();
			}
		});
		textField_7.setColumns(10);
		textField_7.setBounds(195, 44, 131, 16);
		panel_3.add(textField_7);
		
		JLabel lblAssumeTotalCost = new JLabel("Assume cost as Total cost");
		lblAssumeTotalCost.setBounds(10, 61, 159, 16);
		panel_3.add(lblAssumeTotalCost);
		
		checkBox = new JCheckBox("");
		checkBox.setBounds(164, 61, 25, 16);
		panel_3.add(checkBox);
		checkBox.setBackground(new Color(243, 229, 216));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(243, 229, 216));
		panel_4.setLayout(null);
		panel_4.setBounds(638, 0, 319, 84);
		panel_1.add(panel_4);
		
		JLabel lblSuppDetails = new JLabel("Supp Name");
		lblSuppDetails.setBounds(3, 4, 77, 16);
		panel_4.add(lblSuppDetails);
		
		JLabel lblCurrency = new JLabel("Currency");
		lblCurrency.setBounds(3, 24, 70, 16);
		panel_4.add(lblCurrency);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(72, 4, 125, 16);
		panel_4.add(textField_3);
		textField_3.setColumns(10);
		get_vendor_dt(company,vendor_id);
		
		comboBox = new JComboBox <String>();
		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField_26.requestFocus();
                }
			}
		});
		comboBox.setBounds(72, 24, 157, 16);
		panel_4.add(comboBox);
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_currency where status='1' "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String name=resultSet.getString("code");
				comboBox.addItem(name);
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		
		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new vendor_details(db_name,company,  username,"po_order",store_name, version,Integer.toString(decimal),language);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		btnNew.setBounds(247, 3, 60, 18);
		panel_4.add(btnNew);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		button = new JButton("");
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboBox.requestFocus();
                }
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new vendor_list(company, "pr", version,decimals,username);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(serch));
		button.setBounds(200, -2, 35, 22);
		panel_4.add(button);
		
		JLabel lblRemark = new JLabel("Remark");
		lblRemark.setBounds(13, 44, 56, 16);
		panel_4.add(lblRemark);
		
		textField_26 = new JTextField();
		textField_26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_27.requestFocus();
			}
		});
		textField_26.setBounds(72, 44, 247, 16);
		panel_4.add(textField_26);
		textField_26.setColumns(10);
		
		JLabel lblReferance = new JLabel("Referance");
		lblReferance.setBounds(3, 64, 70, 16);
		panel_4.add(lblReferance);
		
		textField_27 = new JTextField();
		textField_27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(po_num.equals(""))
				{
					btnProceed.requestFocus();
				}
			}
		});
		textField_27.setBounds(72, 64, 247, 16);
		panel_4.add(textField_27);
		textField_27.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.setBounds(12, 159, 933, 168);
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
		model.addColumn("Qty"); 
		model.addColumn("Unit"); 
		model.addColumn("cf"); 
		model.addColumn("Unit Cost"); 
		model.addColumn("Item Discount(%)"); 
		model.addColumn("Total");
		model.addColumn("Tax Rate"); 
		model.addColumn("Tax Type"); 
		model.addColumn("Tax Value"); 
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
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table_2 = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table_2.getSelectedRow() != -1) {
					int rows = table_2.getSelectedRow();
					textField_4.setText(table_2.getModel().getValueAt(rows, 1).toString());
					textField_15.setText(table_2.getModel().getValueAt(rows, 2).toString());
					textField_16.setText(table_2.getModel().getValueAt(rows, 3).toString());
					textField_14.setText(table_2.getModel().getValueAt(rows, 4).toString());
					String qty=get_itm_qty(company,table_2.getModel().getValueAt(rows, 2).toString());
					
					
					textField_25.setText(qty);
					String val=table_2.getModel().getValueAt(rows, 5).toString();
					get_item_barcode_list(company,table_2.getModel().getValueAt(rows, 2).toString(),val);
					textField_4.setText(table_2.getModel().getValueAt(rows, 1).toString());
					textField_18.setText(table_2.getModel().getValueAt(rows, 6).toString());
					comboBox_2.setSelectedItem(table_2.getModel().getValueAt(rows, 5));
					textField_19.setText(table_2.getModel().getValueAt(rows, 7).toString());
					textField_20.setText(table_2.getModel().getValueAt(rows, 8).toString());
					textField_21.setText(table_2.getModel().getValueAt(rows, 9).toString());
					textField_22.setText(table_2.getModel().getValueAt(rows, 10).toString());
					textField_23.setText(table_2.getModel().getValueAt(rows, 11).toString());
					textField_24.setText(table_2.getModel().getValueAt(rows, 12).toString());
					textField_17.setText(table_2.getModel().getValueAt(rows, 1).toString());
					get_itemdtls_bsd_code(company, table_2.getModel().getValueAt(rows, 2).toString(), table_2.getModel().getValueAt(rows, 1).toString());
				}
			}
		});
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(709, 330, 236, 147);
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
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBackground(new Color(243, 229, 216));
		scrollPane_1.setBounds(12, 478, 933, 210);
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
			model_1.addColumn("Item Cost");
			model_1.addColumn("Selling price");
			model_1.addColumn("Qty"); 
			
			model_1.addRow(new Object[]{"","","","","","","","","",""});
		scrollPane_1.setViewportView(table_1);
		
		int columns=model_1.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table_1.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(10);
			  if (i==1) column.setPreferredWidth(50);
			  if (i==2) column.setPreferredWidth(50);
			  if (i==3) column.setPreferredWidth(50);
			  if (i==4) column.setPreferredWidth(50);
			  if (i==5) column.setPreferredWidth(100);
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
						//\validate_barcode(company, value, vendor_id, username, version);
						//get_itemdtls_bsd_code(company, value,table_2.getModel().getValueAt(rows, 3).toString());
						validate_barcode(company, value, textField_13.getText().trim(),  version);
//						model_1.removeRow(rows);
//						for(int i=0;i<model_1.getRowCount();i++)
//						{
//							model_1.setValueAt(i+1, i, 0);
//						}
						
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
				 
				  get_ven_items(company,textField_13.getText(),textField_11.getText(), "");
			  }
			});
		textField_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_ven_items(company,textField_13.getText(),textField_11.getText(), "");
			}
		});
		textField_11.setEditable(false);
		textField_11.setBounds(12, 406, 407, 25);
		panel_1.add(textField_11);
		textField_11.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setEnabled(false);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_ven_items(company,textField_13.getText(),textField_11.getText(), "");
			}
		});
		btnSearch.setBounds(421, 405, 97, 25);
		panel_1.add(btnSearch);
		
		JLabel lblVendorItemList = new JLabel("Purchase Order Item List");
		lblVendorItemList.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVendorItemList.setBounds(12, 358, 184, 22);
		panel_1.add(lblVendorItemList);
		
		Image del=new ImageIcon(this.getClass().getResource("/del.png")).getImage();
		lblDel= new JLabel("");
		lblDel.setEnabled(false);
		lblDel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row=table.getSelectedRow();
				int rows=table.getRowCount()-1;
				if(rows>1)
				{
					try{
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						String query="SELECT * FROM "+company+".mr_purchase_return where ref_pr_num='"+textField_2.getText()+"' and barcode='"+table.getValueAt(row, 1).toString()+"' "; 
						ResultSet resultSet = statement.executeQuery(query);
						while(resultSet.next())
						{
							 String query1="delete from "+company+".mr_purchase_return  where ref_pr_num='"+textField_2.getText()+"' and barcode='"+table.getValueAt(row, 1).toString()+"' ";
							 statement.executeUpdate(query1);
						}
						con.close();
					}
					catch(Exception e){
						System.err.println(e);
					}
					model.removeRow(row);
					for(int i=0;i<model.getRowCount();i++)
					{
						model.setValueAt(i+1, i, 0);
					}
					
				}
				else
				{
					try{
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						String query="SELECT * FROM "+company+".mr_purchase_return where ref_pr_num='"+textField_2.getText()+"' and barcode='"+table.getValueAt(row, 1).toString()+"' "; 
						ResultSet resultSet = statement.executeQuery(query);
						if(resultSet.next())
						{
							 String query1="delete from "+company+".mr_purchase_return  where ref_pr_num='"+textField_2.getText()+"' and barcode='"+table.getValueAt(row, 1).toString()+"' ";
							 statement.executeUpdate(query1);
						}
						con.close();
					}
					catch(Exception e){
						System.err.println(e);
					}
					model.removeRow(row);
					for(int i=0;i<model.getRowCount();i++)
					{
						model.setValueAt(i+1, i, 0);
					}
				}
				
				double vatpric=0;
    			double total=0; 
    			for(int a=0;a<rows;a++)
    			{
    				String ttl=model.getValueAt(a, 9).toString();
    				String vat=model.getValueAt(a, 12).toString();
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
    			textField_4.setText("");
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
				textField_4.requestFocus();
				comboBox_2.removeAllItems();
    			get_ven_items(company,textField_13.getText(),"","");
    			
			}
		});
		lblDel.setIcon(new ImageIcon(del));
		lblDel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				lblDel.setBorder(null);
			}
		});
		lblDel.setBounds(920, 133, 22, 25);
		panel_1.add(lblDel);
		
//		btnNewButton = new JButton("");
//		btnNewButton.setEnabled(false);
//		btnNewButton.setToolTipText("Item Code List");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				
//				try {
//					
//					new item_list_master(company, "pr", "", version);
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		btnNewButton.setIcon(new ImageIcon(serch));
//		btnNewButton.setBounds(0, 112, 28, 21);
//		panel_1.add(btnNewButton);
		
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
				validate_barcode(company, textField_4.getText(),textField_13.getText(), version);
			}
		});
		textField_4.setBounds(38, 111, 97, 22);
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
		lblBarcodeitemcode.setBounds(10, 86, 107, 22);
		panel_1.add(lblBarcodeitemcode);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setBounds(338, 86, 56, 22);
		panel_1.add(lblQty);
		
		textField_14 = new JTextField();
		textField_14.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String new_qty=textField_25.getText();
				String qty=textField_14.getText();
				if(Float.parseFloat(qty)>Float.parseFloat(new_qty))
				{
					JOptionPane.showMessageDialog(frame, "Available Qty is Lesser Than Entered Qty");
					textField_14.requestFocus();
				}
			}
		});
		textField_14.setEditable(false);
		textField_14.setBounds(338, 111, 56, 22);
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
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	String new_qty=textField_25.getText();
					String qty=textField_14.getText();
					if(Float.parseFloat(qty)>Float.parseFloat(new_qty))
					{
						JOptionPane.showMessageDialog(frame, "Available Qty is Lesser Than Entered Qty");
						textField_14.requestFocus();
					}
					else
					{
						comboBox_2.requestFocus();
					}
				}
			}
		});
		
		JLabel lblItemcode = new JLabel("Itemcode");
		lblItemcode.setBounds(140, 86, 68, 22);
		panel_1.add(lblItemcode);
		
		textField_15 = new JTextField();
		textField_15.setEditable(false);
		textField_15.setBounds(140, 111, 72, 22);
		panel_1.add(textField_15);
		textField_15.setColumns(10);
		
		JLabel lblItemDetails = new JLabel("Item Details");
		lblItemDetails.setBounds(216, 86, 116, 22);
		panel_1.add(lblItemDetails);
		
		JLabel lblUnit = new JLabel("unit");
		lblUnit.setBounds(399, 86, 54, 22);
		panel_1.add(lblUnit);
		
		JLabel lblCf = new JLabel("CF");
		lblCf.setBounds(511, 86, 41, 22);
		panel_1.add(lblCf);
		
		JLabel lblUnitCost = new JLabel("Unit cost");
		lblUnitCost.setBounds(545, 86, 56, 22);
		panel_1.add(lblUnitCost);
		
		JLabel lblItemDisc = new JLabel("Item Disc%");
		lblItemDisc.setBounds(606, 86, 56, 22);
		panel_1.add(lblItemDisc);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setBounds(675, 86, 56, 22);
		panel_1.add(lblTotal_1);
		
		JLabel lblTaxRate = new JLabel("Tax Rate");
		lblTaxRate.setBounds(725, 86, 56, 22);
		panel_1.add(lblTaxRate);
		
		JLabel lblTotalTax = new JLabel("Tax Type");
		lblTotalTax.setBounds(789, 86, 56, 22);
		panel_1.add(lblTotalTax);
		
		textField_16 = new JTextField();
		textField_16.setEditable(false);
		textField_16.setBounds(217, 111, 116, 22);
		panel_1.add(textField_16);
		textField_16.setColumns(10);
		
		JLabel lblTaxVal = new JLabel("Tax Val");
		lblTaxVal.setBounds(850, 86, 56, 22);
		panel_1.add(lblTaxVal);
		
		textField_18 = new JTextField();
		textField_18.setEditable(false);
		textField_18.setColumns(10);
		textField_18.setBounds(499, 111, 41, 22);
		panel_1.add(textField_18);
		
		textField_19 = new JTextField();
		textField_19.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(checkBox.isSelected()){
    				String unit_cost=textField_19.getText();
    				String qty=textField_14.getText();
    				double itm_cost=Double.parseDouble(unit_cost)/Double.parseDouble(qty);
    				BigDecimal bd = new BigDecimal(itm_cost);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		itm_cost = bd.doubleValue();
    				textField_19.setText(Double.toString(itm_cost));
    			}
			}
		});
		textField_19.setEditable(false);
		textField_19.setColumns(10);
		textField_19.setBounds(545, 111, 56, 22);
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
				}
			}
			
		});
		
		textField_20 = new JTextField();
		textField_20.setEditable(false);
		textField_20.setText("0");
		textField_20.setColumns(10);
		textField_20.setBounds(606, 111, 56, 22);
		panel_1.add(textField_20);
		textField_20.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	btnprint.requestFocus();
				}
			}
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
		});
		textField_20.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String new_qty=textField_25.getText();
				String qty=textField_14.getText();
				if(Float.parseFloat(qty)>Float.parseFloat(new_qty))
				{
					JOptionPane.showMessageDialog(frame, "Available Qty is Lesser Than Entered Qty");
					textField_14.requestFocus();
				}
				else
				{
					String cost=textField_19.getText();
	    			String itm_disc=textField_20.getText();
	    			String tax=textField_23.getText();
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
			    				tax_amt=((grnd * Double.parseDouble(tax_rate))/105);
			    				taxval=grnd-tax_amt;
			    				
			    			}
			    			else if(tax.equals("EXC"))
			    			{
			    				tax_amt=((grnd * Double.parseDouble(tax_rate))/100);
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
		textField_21.setBounds(667, 111, 56, 22);
		panel_1.add(textField_21);
		
		textField_22 = new JTextField();
		textField_22.setEditable(false);
		textField_22.setColumns(10);
		textField_22.setBounds(728, 111, 56, 22);
		panel_1.add(textField_22);
		
		textField_23 = new JTextField();
		textField_23.setEditable(false);
		textField_23.setColumns(10);
		textField_23.setBounds(789, 111, 56, 22);
		panel_1.add(textField_23);
		
		textField_24 = new JTextField();
		textField_24.setEditable(false);
		textField_24.setColumns(10);
		textField_24.setBounds(850, 111, 56, 22);
		panel_1.add(textField_24);
		
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					textField_19.requestFocus();	
					textField_19.selectAll();
				}
				
			}
		});
		comboBox_2.setEnabled(false);
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_2.getItemCount()>0)
				{
					validate_barcode_unit(company, textField_15.getText(),comboBox_2.getSelectedItem().toString());
				}
				
			}
		});
		comboBox_2.setBounds(397, 111, 97, 22);
		panel_1.add(comboBox_2);
		
		btnprint = new JButton("");
		btnprint.setEnabled(false);
		btnprint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
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
						String qty =textField_14.getText();
						String unit =comboBox_2.getSelectedItem().toString();
						String unit_cost =textField_19.getText();
						String tax_val =textField_24.getText();
						String tax =textField_22.getText();
						String tax_type =textField_23.getText();
						String cf =textField_18.getText();
						double total =Double.parseDouble(textField_21.getText());
						
						String disc=textField_20.getText();
						int row=model.getRowCount();
						String value;
						String Value;
						value=barcode;
						LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);  
						boolean sts = false;
						boolean[] stslist=new boolean[row];
						String[] list=new String[row];
						
						if(textField_17.getText().equals(textField_4.getText()))
						{
							int sel_row=table.getSelectedRow();
							model.setValueAt(qty, sel_row, 4);
							model.setValueAt(unit, sel_row, 5);
							model.setValueAt(cf, sel_row, 6);
							model.setValueAt(unit_cost, sel_row, 7);
							model.setValueAt(disc, sel_row, 8);
							model.setValueAt(total, sel_row, 9);
							model.setValueAt(tax, sel_row, 10);
							model.setValueAt(tax_type, sel_row, 11);
							model.setValueAt(tax_val, sel_row, 12);
							
							try{
								Connection con=Dbsetup.dbconn.getConnection();
								Statement statement=con.createStatement();  
								String query="SELECT * FROM "+company+".mr_purchase_return where ref_pr_num='"+textField_2.getText()+"' and barcode='"+textField_4.getText()+"' "; 
								ResultSet resultSet = statement.executeQuery(query);
								if(!resultSet.next())
								{
									double unitcost=Double.parseDouble(textField_21.getText())/Double.parseDouble(textField_14.getText());
									BigDecimal bv = new BigDecimal(unitcost);
					        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
					        		unitcost = bv.doubleValue();
					        		BigDecimal ba = new BigDecimal(total);
					        		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
					        		total = ba.doubleValue();
									String query1="update "+company+".mr_purchase_return set  `item_code`='"+textField_15.getText()+"', `qty`='"+textField_14.getText()+"',`unit`='"+comboBox_2.getSelectedItem().toString()+"', `cf`='"+textField_18.getText()+"', `unit_cost`='"+textField_19.getText()+"',`tax_val`='"+textField_24.getText()+"', `total`='"+total+"', `cost_after_sub_ttl`='"+unitcost+"', `modified_date`='"+formattedDate+"' where `ref_pr_num`='"+textField_2.getText()+"' and `barcode`='"+textField_4.getText()+"' ";
									statement.executeUpdate(query1);
								}
								con.close();
							}
							catch(Exception e1){
								System.err.println(e1);
							}
							textField_17.setText("");
						}
						else
						{
							if(row==0)
							{
								model.addRow(new Object[]{model.getRowCount()+1, barcode, itm_cde,itm_dtls,qty,unit,cf,unit_cost,disc,total,tax,tax_type,tax_val});
								try{
									Connection con=Dbsetup.dbconn.getConnection();
									Statement statement=con.createStatement();  
									String query="SELECT * FROM "+company+".mr_purchase_return where ref_pr_num='"+textField_2.getText()+"' and barcode='"+textField_4.getText()+"' "; 
									ResultSet resultSet = statement.executeQuery(query);
									if(!resultSet.next())
									{
										double unitcost=Double.parseDouble(textField_21.getText())/Double.parseDouble(textField_14.getText());
										BigDecimal bv = new BigDecimal(unitcost);
						        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
						        		unitcost = bv.doubleValue();
						        		BigDecimal ba = new BigDecimal(total);
						        		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
						        		total = ba.doubleValue();
										String query1="insert into "+company+".mr_purchase_return(`ref_pr_num`, `barcode`, `item_code`, `qty`, `unit`, `cf`, `unit_cost`, `tax_val`, `total`, `cost_after_sub_ttl`, `created_date`, `modified_date`) VALUES ('"+textField_2.getText()+"','"+barcode+"','"+itm_cde+"','"+qty+"','"+unit+"','"+cf+"','"+unit_cost+"','"+tax_val+"','"+total+"','"+unitcost+"','"+formattedDate+"','"+formattedDate+"')";
										statement.executeUpdate(query1);
									}
									con.close();
								}
								catch(Exception e1){
									System.err.println(e1);
								}
							}
							else
							{
								for(int z=0;z<row;z++)
								{
									Value=table.getModel().getValueAt(z, 1).toString();
									list[z]=Value;
								}
								for (int a=0;a<list.length;a++) 
								{
									if(!list[a].equals(value))
									{
										sts=true;
									}
									else
									{
										sts=false;
									}
									if(textField_17.getText().equals(list[a]))
									{
										sts=true;
									}
									stslist[a]=sts;
								}
								int q=1;
								for(int b=0;b<stslist.length;b++)
								{
									if(stslist[b]==true)
									{
										if(stslist.length==q)
										{				
											model.addRow(new Object[]{model.getRowCount()+1, barcode, itm_cde,itm_dtls,qty,unit,cf,unit_cost,disc,total,tax,tax_type,tax_val});
											try{
												Connection con=Dbsetup.dbconn.getConnection();
												Statement statement=con.createStatement();  
												String query="SELECT * FROM "+company+".mr_purchase_return where ref_pr_num='"+textField_2.getText()+"' and barcode='"+textField_4.getText()+"' "; 
												ResultSet resultSet = statement.executeQuery(query);
												if(!resultSet.next())
												{
													double unitcost=Double.parseDouble(textField_21.getText())/Double.parseDouble(textField_14.getText());
													BigDecimal bv = new BigDecimal(unitcost);
									        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
									        		unitcost = bv.doubleValue();
													String query1="insert into "+company+".mr_purchase_return(`ref_pr_num`, `barcode`, `item_code`, `qty`, `unit`, `cf`, `unit_cost`, `tax_val`, `total`, `cost_after_sub_ttl`, `created_date`, `modified_date`) VALUES ('"+textField_2.getText()+"','"+barcode+"','"+itm_cde+"','"+qty+"','"+unit+"','"+cf+"','"+unit_cost+"','"+tax_val+"','"+total+"','"+unitcost+"','"+formattedDate+"','"+formattedDate+"')";
													statement.executeUpdate(query1);
												}
												con.close();
											}
											catch(Exception e1){
												System.err.println(e1);
											}
										}
										q++;
									}
									else{
										JOptionPane.showMessageDialog(frame, "Item Already Exist's in the PR");
										textField_4.setText("");
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
										textField_17.setText("");
										comboBox_2.removeAllItems();
										textField_4.requestFocus();
									}
								}
							}
						}
						
						double vatpric=0;
		    			double total_amt=0; 
		    			int rows=model.getRowCount();
		    			
						for(int a=0;a<rows;a++)
		    			{
		    				String ttl=model.getValueAt(a, 9).toString();
		    				String vat=model.getValueAt(a, 12).toString();
		    				if(ttl.equals(""))
		    				{
		    					ttl="0";
		    				}

		    				if(vat.equals(""))
		    				{
		    					vat="0";
		    				}
		    				total_amt=total_amt+Double.parseDouble(ttl);
		    				
		    				vatpric=vatpric+Double.parseDouble(vat);
		    				
		    				
		    			}
						BigDecimal bd = new BigDecimal(total_amt);
	            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	            		total_amt = bd.doubleValue();
	            		
	            		BigDecimal bv = new BigDecimal(vatpric);
	            		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	            		vatpric = bv.doubleValue();
	            		
						double subtotl=total_amt+vatpric;
	            		BigDecimal bx = new BigDecimal(subtotl);
	            		bx = bx.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	            		subtotl = bx.doubleValue();
						
		    			textField.setText(Double.toString(total_amt));
		    			textField_8.setText(Double.toString(total_amt));
		    			textField_9.setText(Double.toString(vatpric));
		    			textField_10.setText(Double.toString(subtotl));
						

		    			textField_4.setText("");
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
						comboBox_2.removeAllItems();
						textField_4.requestFocus();
					}
				}

			}
		});
		btnprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
					String qty =textField_14.getText();
					String unit =comboBox_2.getSelectedItem().toString();
					String unit_cost =textField_19.getText();
					String tax_val =textField_24.getText();
					String tax =textField_22.getText();
					String tax_type =textField_23.getText();
					String cf =textField_18.getText();
					String total =textField_21.getText();
					String disc =textField_20.getText();
					int row=model.getRowCount();
					String value;
					String Value;
					value=barcode;
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
					boolean sts = false;
					boolean[] stslist=new boolean[row];
					String[] list=new String[row];
					
					if(textField_17.getText().equals(textField_4.getText()))
					{
						int sel_row=table.getSelectedRow();
						model.setValueAt(qty, sel_row, 4);
						model.setValueAt(unit, sel_row, 5);
						model.setValueAt(cf, sel_row, 6);
						model.setValueAt(unit_cost, sel_row, 7);
						model.setValueAt(disc, sel_row, 8);
						model.setValueAt(total, sel_row, 9);
						model.setValueAt(tax, sel_row, 10);
						model.setValueAt(tax_type, sel_row, 11);
						model.setValueAt(tax_val, sel_row, 12);
						try{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="SELECT * FROM "+company+".mr_purchase_return where ref_pr_num='"+textField_2.getText()+"' and barcode='"+textField_4.getText()+"' "; 
							ResultSet resultSet = statement.executeQuery(query);
							if(!resultSet.next())
							{
								double unitcost=Double.parseDouble(textField_21.getText())/Double.parseDouble(textField_14.getText());
								BigDecimal bv = new BigDecimal(unitcost);
				        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
				        		unitcost = bv.doubleValue();
								String query1="update "+company+".mr_purchase_return set , `item_code`='"+textField_15.getText()+"', `qty`='"+textField_14.getText()+"',`unit`='"+comboBox_2.getSelectedItem().toString()+"', `cf`='"+textField_18.getText()+"', `unit_cost`='"+textField_19.getText()+"',`tax_val`='"+textField_24.getText()+"', `total`='"+textField_21.getText()+"', `cost_after_sub_ttl`='"+unitcost+"', `modified_date`='"+formattedDate+"' where `ref_pr_num`='"+textField_2.getText()+"' and `barcode`='"+textField_4.getText()+"' ";
								statement.executeUpdate(query1);
							}
							con.close();
						}
						catch(Exception e1){
							System.err.println(e1);
						}
						textField_17.setText("");
					}
					else
					{
						if(row==0)
						{
							model.addRow(new Object[]{model.getRowCount()+1, barcode, itm_cde,itm_dtls,qty,unit,cf,unit_cost,disc,total,tax,tax_type,tax_val});
							try{
								Connection con=Dbsetup.dbconn.getConnection();
								Statement statement=con.createStatement();  
								String query="SELECT * FROM "+company+".mr_purchase_return where ref_pr_num='"+textField_2.getText()+"' and barcode='"+textField_4.getText()+"' "; 
								ResultSet resultSet = statement.executeQuery(query);
								if(!resultSet.next())
								{
									double unitcost=Double.parseDouble(textField_21.getText())/Double.parseDouble(textField_14.getText());
									BigDecimal bv = new BigDecimal(unitcost);
					        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
					        		unitcost = bv.doubleValue();
									String query1="insert into "+company+".mr_purchase_return(`ref_pr_num`, `barcode`, `item_code`, `qty`, `unit`, `cf`, `unit_cost`, `tax_val`, `total`, `cost_after_sub_ttl`, `created_date`, `modified_date`) VALUES ('"+textField_2.getText()+"','"+barcode+"','"+itm_cde+"','"+qty+"','"+unit+"','"+cf+"','"+unit_cost+"','"+tax_val+"','"+total+"','"+unitcost+"','"+formattedDate+"','"+formattedDate+"')";
									statement.executeUpdate(query1);
								}
								con.close();
							}
							catch(Exception e1){
								System.err.println(e1);
							}
						}
						else
						{
							for(int z=0;z<row;z++)
							{
								Value=table.getModel().getValueAt(z, 1).toString();
								list[z]=Value;
							}
							for (int a=0;a<list.length;a++) 
							{
								if(!list[a].equals(value))
								{
									sts=true;
								}
								else
								{
									sts=false;
								}
								if(textField_17.getText().equals(list[a]))
								{
									sts=true;
								}
								stslist[a]=sts;
							}
							int q=1;
							for(int b=0;b<stslist.length;b++)
							{
								if(stslist[b]==true)
								{
									if(stslist.length==q)
									{				
										model.addRow(new Object[]{model.getRowCount()+1, barcode, itm_cde,itm_dtls,qty,unit,cf,unit_cost,disc,total,tax,tax_type,tax_val});
										try{
											Connection con=Dbsetup.dbconn.getConnection();
											Statement statement=con.createStatement();  
											String query="SELECT * FROM "+company+".mr_purchase_return where ref_pr_num='"+textField_2.getText()+"' and barcode='"+textField_4.getText()+"' "; 
											ResultSet resultSet = statement.executeQuery(query);
											if(!resultSet.next())
											{
												double unitcost=Double.parseDouble(textField_21.getText())/Double.parseDouble(textField_14.getText());
												BigDecimal bv = new BigDecimal(unitcost);
								        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
								        		unitcost = bv.doubleValue();
												String query1="insert into "+company+".mr_purchase_return(`ref_pr_num`, `barcode`, `item_code`, `qty`, `unit`, `cf`, `unit_cost`, `tax_val`, `total`, `cost_after_sub_ttl`, `created_date`, `modified_date`) VALUES ('"+textField_2.getText()+"','"+barcode+"','"+itm_cde+"','"+qty+"','"+unit+"','"+cf+"','"+unit_cost+"','"+tax_val+"','"+total+"','"+unitcost+"','"+formattedDate+"','"+formattedDate+"')";
												statement.executeUpdate(query1);
											}
											con.close();
										}
										catch(Exception e1){
											System.err.println(e1);
										}
									}
									q++;
								}
								else{
									JOptionPane.showMessageDialog(frame, "Item Already Exist's in the PR");
									textField_4.setText("");
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
									textField_17.setText("");
									comboBox_2.removeAllItems();
									textField_4.requestFocus();
								}
							}
						}
					}
					
					double vatpric=0;
	    			double total_amt=0; 
	    			int rows=model.getRowCount();
	    			
					for(int a=0;a<rows;a++)
	    			{
	    				String ttl=model.getValueAt(a, 9).toString();
	    				String vat=model.getValueAt(a, 12).toString();
	    				if(ttl.equals(""))
	    				{
	    					ttl="0";
	    				}

	    				if(vat.equals(""))
	    				{
	    					vat="0";
	    				}
	    				total_amt=total_amt+Double.parseDouble(ttl);
	    				
	    				vatpric=vatpric+Double.parseDouble(vat);
	    				
	    				
	    			}
					
					BigDecimal bd = new BigDecimal(total_amt);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		total_amt = bd.doubleValue();
					
					BigDecimal bv = new BigDecimal(vatpric);
            		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		vatpric = bv.doubleValue();
            		
            		double subtotl=total_amt+vatpric;
            		BigDecimal bx = new BigDecimal(subtotl);
            		bx = bx.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		subtotl = bx.doubleValue();
					
	    			textField.setText(Double.toString(total_amt));
	    			textField_9.setText(Double.toString(vatpric));
	    			textField_10.setText(Double.toString(subtotl));
	    			textField_8.setText(Double.toString(total_amt));

	    			textField_4.setText("");
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
					comboBox_2.removeAllItems();
					textField_4.requestFocus();
				}
			}
		});
		btnprint.setBounds(910, 110, 40, 25);
		btnprint.setIcon(new ImageIcon(printer));
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
		btnClear.setBounds(827, 137, 75, 20);
		panel_1.add(btnClear);
		
		textField_25 = new JTextField();
		textField_25.setEnabled(false);
		textField_25.setVisible(false);
		textField_25.setEditable(false);
		textField_25.setBounds(307, 136, 116, 22);
		panel_1.add(textField_25);
		textField_25.setColumns(10);
		
		textField_28 = new JTextField();
		textField_28.setEnabled(false);
		textField_28.setVisible(false);
		textField_28.setEditable(false);
		textField_28.setBounds(473, 136, 116, 22);
		panel_1.add(textField_28);
		textField_28.setColumns(10);
		
		JButton btnNewButton = new JButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//frame.setAlwaysOnTop(false);
					Connection con=Dbsetup.dbconn.getConnection();
					new item_list_master(company,company, "pr", textField_13.getText().trim(), version,decimal,con,"",language);
					//frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					//menuItem.setEnabled(false);
					
				} catch (JSONException e) {
					e.printStackTrace();
					frame.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(frame, e.getMessage());
					frame.setAlwaysOnTop(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 111, 27, 23);
		btnNewButton.setIcon(new ImageIcon(serch));
		panel_1.add(btnNewButton);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_4.setText("");
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
				textField_4.requestFocus();
				comboBox_2.removeAllItems();
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 61, 957, 2);
		frame.getContentPane().add(separator);
		
		clickOnserch( btnNewButton, "play", KeyEvent.VK_F9,company,version,company,language );
		
		if(po_num.equals(""))
		{
			btnProceed = new JButton("Start");
			btnProceed.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						String com_date=lblNewLabel.getText();
						String user=label_1.getText();
						String po_num=textField_2.getText();
						String po_name="purchase return";
						String podate = null;
						String supp_invo=textField_6.getText();
						String num_itm_invo=textField_5.getText();
						String grnd_ttl=textField_7.getText();
						supp_name=textField_13.getText();
						LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);
					    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					    String po_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
						if(!po_date.equals(""))
						{
							podate = sdf.format(dateChooser.getDate());
						}
						String currncy = (String) comboBox.getSelectedItem();
						int ttl_cst=0;
						if(supp_name.equals(""))
			        	{
							if(!vendor_id.equals(""))
							{
								supp_name=vendor_id;
							}
			        	}
						if (checkBox.isSelected()) {
							ttl_cst=1;
						}
						if(po_date.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Select date from date chooser");
							dateChooser.requestFocus();
						}
						else if(supp_invo.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Please enter the supplier invoice number");
							textField_6.requestFocus();
						}
						else if(num_itm_invo.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Please enter the Number of items in PO");
							textField_5.requestFocus();
						}
						else if(grnd_ttl.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Please enter the Grand total in PO");
							textField_7.requestFocus();
						}
						else if(supp_name.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Please Choose a Supplier");
						}
						else
						{
							try{
								Connection con=Dbsetup.dbconn.getConnection();
								Statement statement=con.createStatement();  
								String query="SELECT * FROM "+company+".mr_purchase_return_list where pr_no ='"+po_num+"' and status!='VOID' "; 
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
									
								    String save_po="insert into "+company+".mr_purchase_return_list(`com_date`, `pr_date`, `user_id`, `pr_no`, `invo_nu`, `ttl_items`, `ttl_price`, `vendor_id`, `assume_ttl_cst`, `currency_id`,status,referance, `created_date`, `modified_date`,store_name,remark) values('"+com_date+"','"+podate+"','"+user+"','"+seq_num+"','"+supp_invo+"','"+num_itm_invo+"','"+grnd_ttl+"','"+supp_name+"','"+ttl_cst+"','"+currncy+"','OPEN','"+textField_27.getText()+"','"+formattedDate+"','"+formattedDate+"','"+store_name+"','"+textField_26.getText()+"')";
								    statement.executeUpdate(save_po);
									textField_2.setText(seq_num);
						        	textField_3.setEditable(false);
						        	textField_6.setEditable(false);
						        	//dateChooser.setEnabled(false);
						        	btnProceed.setEnabled(false);
						        	menuItem_1.setEnabled(false);
						        	menuItem_2.setEnabled(true);
						        	button.setEnabled(false);
						        	btnNew.setEnabled(false);
						        	scrollPane.setEnabled(true);
						        	scrollPane_1.setEnabled(true);
						        	textField_1.setEditable(true);
						        	textField_4.setEditable(true);
						        	textField_14.setEditable(true);
						        	comboBox_2.setEnabled(true);
						        	btnprint.setEnabled(true);
						        	lblDel.setEnabled(true);
						        	textField_19.setEditable(true);
						        	textField_20.setEditable(true);
						        	textField_11.setEditable(true);
						        	textField_12.setEditable(true);
						        	btnSearch.setEnabled(true);
						        	table.setEnabled(true);
						        	table_1.setEnabled(true);
						        	//btnNewButton.setEnabled(true);
						        	btnClear.setEnabled(true);
						        	
						        	get_ven_items(company,supp_name,"",username);
						        	textField_4.requestFocus();
								}
								con.close();
							}
							catch(Exception e1){
								System.err.println(e1);
							}
						}
	                }
				}
			});
			btnProceed.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String com_date=lblNewLabel.getText();
					String user=label_1.getText();
					String po_num=textField_2.getText();
					String po_name="purchase return";
					String podate = null;
					String supp_invo=textField_6.getText();
					String num_itm_invo=textField_5.getText();
					String grnd_ttl=textField_7.getText();
					supp_name=textField_13.getText();
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				    String po_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					if(!po_date.equals(""))
					{
						podate = sdf.format(dateChooser.getDate());
					}
					String currncy = (String) comboBox.getSelectedItem();
					int ttl_cst=0;
					if(supp_name.equals(""))
		        	{
						if(!vendor_id.equals(""))
						{
							supp_name=vendor_id;
						}
		        	}
					if (checkBox.isSelected()) {
						ttl_cst=1;
					}
					if(po_date.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Select date from date chooser");
						dateChooser.requestFocus();
					}
					else if(supp_invo.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Please enter the supplier invoice number");
						textField_6.requestFocus();
					}
					else if(num_itm_invo.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Please enter the Number of items in PO");
						textField_5.requestFocus();
					}
					else if(grnd_ttl.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Please enter the Grand total in PO");
						textField_7.requestFocus();
					}
					else if(supp_name.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Please Choose a Supplier");
					}
					else
					{
						try{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="SELECT * FROM "+company+".mr_purchase_return_list where pr_no ='"+po_num+"' and status!='VOID' "; 
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
								
							    String save_po="insert into "+company+".mr_purchase_return_list(`com_date`, `pr_date`, `user_id`, `pr_no`, `invo_nu`, `ttl_items`, `ttl_price`, `vendor_id`, `assume_ttl_cst`, `currency_id`,status,referance, `created_date`, `modified_date`,store_name,remark) values('"+com_date+"','"+podate+"','"+user+"','"+seq_num+"','"+supp_invo+"','"+num_itm_invo+"','"+grnd_ttl+"','"+supp_name+"','"+ttl_cst+"','"+currncy+"','OPEN','"+textField_27.getText()+"','"+formattedDate+"','"+formattedDate+"','"+store_name+"','"+textField_26.getText()+"')";
							    statement.executeUpdate(save_po);
								textField_2.setText(seq_num);
					        	textField_3.setEditable(false);
					        	textField_6.setEditable(false);
					        	//dateChooser.setEnabled(false);
					        	btnProceed.setEnabled(false);
					        	menuItem_1.setEnabled(false);
					        	menuItem_2.setEnabled(true);
					        	button.setEnabled(false);
					        	btnNew.setEnabled(false);
					        	scrollPane.setEnabled(true);
					        	scrollPane_1.setEnabled(true);
					        	textField_1.setEditable(true);
					        	textField_4.setEditable(true);
					        	textField_14.setEditable(true);
					        	comboBox_2.setEnabled(true);
					        	btnprint.setEnabled(true);
					        	lblDel.setEnabled(true);
					        	textField_19.setEditable(true);
					        	textField_20.setEditable(true);
					        	textField_11.setEditable(true);
					        	textField_12.setEditable(true);
					        	btnSearch.setEnabled(true);
					        	table.setEnabled(true);
					        	table_1.setEnabled(true);
					        	//btnNewButton.setEnabled(true);
					        	btnClear.setEnabled(true);
					        	
					        	get_ven_items(company,supp_name,"",username);
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
			//edit_po(po_num,company,vendor_id);
			try{
				
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				String query="SELECT * FROM "+company+".mr_purchase_return_list where pr_no ='"+po_num+"' "; 
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next())
				{
					lblNewLabel.setText(resultSet.getString("com_date"));
					java.util.Date date =null;
					if(resultSet.getString("pr_date")!="")
					{
						date = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("pr_date"));
					}
					dateChooser.setDate(date);
					label_1.setText(resultSet.getString("user_id"));
					textField_2.setText(resultSet.getString("pr_no"));
					textField_6.setText(resultSet.getString("invo_nu"));
					textField_5.setText(resultSet.getString("ttl_items"));
					textField_7.setText(resultSet.getString("ttl_price"));
					textField_3.setText(resultSet.getString("vendor_id"));
					get_vendor_dt(company,resultSet.getString("vendor_id"));
					textField_27.setText(resultSet.getString("referance"));
					textField_26.setText(resultSet.getString("remark"));
					if(resultSet.getString("assume_ttl_cst").equals("1"))
					{
						checkBox.setSelected(true);
					}
					else
					{
						checkBox.setSelected(false);
					}
					comboBox.setSelectedItem(resultSet.getString("currency_id"));
					textField_13.setText(resultSet.getString("vendor_id"));
					textField_2.setEditable(false);
		        	textField_3.setEditable(false);
		        	textField_1.setEditable(true);
		        	textField_12.setEditable(true);
		        	textField_4.setEditable(true);
		        	textField_14.setEditable(true);
		        	comboBox_2.setEnabled(true);
		        	btnprint.setEnabled(true);
		        	lblDel.setEnabled(true);
		        	textField_19.setEditable(true);
		        	textField_20.setEditable(true);
		        	textField_6.setEditable(false);
		        	//dateChooser.setEnabled(false);
		        	button.setEnabled(false);
		        	btnNew.setEnabled(false);
		        	menuItem_1.setEnabled(false);
		        	scrollPane.setEnabled(true);
		        	scrollPane_1.setEnabled(true);
		        	textField_11.setEditable(true);
		        	btnSearch.setEnabled(true);
		        	table.setEnabled(true);
		        	table_1.setEnabled(true);
		        	menuItem_2.setEnabled(true);
		        	//btnNewButton.setEnabled(true);
		        	btnClear.setEnabled(true);
		        	get_item_dt(company,po_num,Integer.toString(decimal));
		        	get_ven_items(company,resultSet.getString("vendor_id"),"","");
				}
				con.close();
			}
			catch(Exception e){
				System.err.println(e);
			}
		}
	}
	public static void clickOnserch(final AbstractButton button, String actionName, int key,final String company, final String version, final String db_name,final String language)
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
					Connection con=Dbsetup.dbconn.getConnection();
					new item_list_master(company,company, "pr", textField_13.getText().trim(), version,decimal,con,"",language);
				} catch (JSONException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    } );
	}
	private String get_itm_qty(String company, String item_code) {
		String qty="";
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_item_master where item_code='"+item_code+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				qty=resultSet.getString("total_qty");
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		return qty;
	}
	
	private void  get_vendor_dt(String company,String vendor_id)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_vendor where vendor_code='"+vendor_id+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				textField_3.setText(resultSet.getString("vendor_name"));
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	public static void validate_barcode(String company, String input,String vendor_id, String version){
		
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query;
			
			query="SELECT * FROM "+company+".mr_purchase_order_history INNER join  "+company+".mr_purchase_list_history on "+company+".mr_purchase_list_history.po_no="+company+".mr_purchase_order_history.ref_po_num  where (barcode='"+input+"' or item_code='"+input+"') and "+company+".mr_purchase_list_history.vendor_id='"+vendor_id+"' ";
			
//			if(input.length()>8)
//			{
//				query="SELECT * FROM "+company+".mr_purchase_order_history INNER join  "+company+".mr_purchase_list_history on "+company+".mr_purchase_list_history.po_no="+company+".mr_purchase_order_history.ref_po_num  where barcode='"+input+"' and "+company+".mr_purchase_list_history.vendor_id='"+vendor_id+"' ";
//			}
//			else
//			{
//				query="SELECT * FROM "+company+".mr_purchase_order_history INNER join  "+company+".mr_purchase_list_history on "+company+".mr_purchase_list_history.po_no="+company+".mr_purchase_order_history.ref_po_num  where item_code='"+input+"' and "+company+".mr_purchase_list_history.vendor_id='"+vendor_id+"' ";
//			}
			ResultSet resultSet1 = statement.executeQuery(query);
			if(resultSet1.next())
			{
				ResultSet resultSet = statement.executeQuery(query);
				String barcode = null;
				if(resultSet.next())
				{
					if(input.length()>8)
					{
						barcode=input;
					}
					else
					{
						barcode=resultSet.getString("barcode");
					}
					int rcount=model.getRowCount();
					boolean[] stslist=new boolean[rcount];
					boolean sts = false;
					String Value;
					String[] list=new String[rcount];
					String value=barcode;
					int row = 0;
					if(rcount>0)
					{	
						 for(int z=0;z<rcount;z++)
						{
							Value=table.getModel().getValueAt(z, 1).toString();
							list[z]=Value;
						}
						for (int a=0;a<list.length;a++) 
						{
							if(!list[a].equals(value))
							{
								sts=true;
							}
							else
							{
								sts=false;
								row=a;
							}
							stslist[a]=sts;
						}
						int q=1;
						for(int b=0;b<stslist.length;b++)
						{
							if(stslist[b]==true)
							{
								if(stslist.length==q)
								{				
									get_itemdtls_bsd_code(company,resultSet.getString("item_code"),barcode);
								}
								q++;
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Item Already Exist's in the PR");
								table.setRowSelectionInterval(row, row);
								textField_4.setText("");
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
								textField_17.setText("");
								textField_25.setText("");
								comboBox_2.removeAllItems();
								textField_4.requestFocus();
							}
						}
					}
					else
					{
//						validate_ven_items(host_name,company,vendor_id,jsonObject.getString("item_code"),jsonObject.getString("barcode"));
//						val = "1";
						get_itemdtls_bsd_code(company,resultSet.getString("item_code"),barcode);
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "This item is not provided by this vendor");
				textField_4.setText("");
				textField_4.requestFocus();
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	public static void get_itemdtls_bsd_code(String company, String input,String scan_barcode) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_item_master where item_code='"+input+"' "; 
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
				comboBox_2.removeAllItems();
				String tax = resultSet.getString("input_tax");
				Statement statement50=con.createStatement();  
				String query50="SELECT * FROM "+company+".mr_tax where tax_code='"+tax+"' "; 
				ResultSet resultSet50 = statement50.executeQuery(query50);
				if(resultSet50.next())
				{
					textField_28.setText(resultSet50.getString("tax_rate"));
				}
				String barcode =scan_barcode;//resultSet.getString("barcode").replaceAll("\\s+","");
				
				String inclu_tax=resultSet.getString("po_inclusive_tax");
				String tax_rate=resultSet.getString("input_tax_rate");
				String ttl_qty=resultSet.getString("total_qty");
				String tax_type="";
				float cost=Float.parseFloat(resultSet.getString("item_lastcost"));
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
				}
				else
				{
					tax_type="NON" ;
				}
				item_cost=cost-((cost*Double.parseDouble(tax_rate))/105);
				BigDecimal bv = new BigDecimal(item_cost);
        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
        		item_cost = bv.doubleValue();
				textField_15.setText(resultSet.getString("item_code"));
				textField_16.setText(resultSet.getString("description1"));
				textField_14.requestFocus();
				textField_19.setText(Double.toString(item_cost));
				textField_25.setText(ttl_qty);
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
				}
				
				textField_4.setText(barcode);
				textField_18.setText(resultSet.getString("cf1"));
				textField_22.setText(resultSet.getString("input_tax"));
				textField_23.setText(tax_type);
				int column;
			    int rows = table.getSelectedRow();
			    String value;
			    String Value;
			    int rcount=model.getRowCount();
			    boolean[] stslist=new boolean[rcount];
			    boolean sts = false;
			    if(table_1.getSelectedRow()==-1)
			    {
			    	column = 1;
			    	value=input;
			    	String[] list=new String[rcount];
					for(int z=0;z<rcount;z++)
					{
						Value=table.getModel().getValueAt(z, 1).toString();
						list[z]=Value;
					}
					for (int a=0;a<list.length;a++) 
					{
						if(!list[a].equals(value))
						{
							sts=true;
						}
						else
						{
							sts=false;
						}
						stslist[a]=sts;
					}
					int q=1;
					String barcode_stng;
					for(int b=0;b<stslist.length;b++)
					{
						if(stslist[b]==true)
						{
							if(stslist.length==q)
							{		
								for(int y=0;y<model_1.getRowCount();y++)
								{
									barcode_stng=table_1.getValueAt(y, 5).toString();
									if(barcode_stng.equals(value))
									{
										model_1.removeRow(rows);
										for(int i=0;i<model_1.getRowCount();i++)
										{
											model_1.setValueAt(i+1, i, 0);
										}
									}
								}
								
							}
							q++;
						}
						else{
							JOptionPane.showMessageDialog(frame, "Item Already Exist's in the PR");
							textField_4.setText("");
			    			textField_15.setText("");
							textField_16.setText("");
							textField_14.setText("");
							textField_18.setText("");
							textField_19.setText("");
							textField_20.setText("0");
							textField_21.setText("");
							textField_22.setText("");
							textField_25.setText("");
							textField_23.setText("");
							textField_24.setText("");
							textField_17.setText("");
							comboBox_2.removeAllItems();
							textField_4.requestFocus();
							model_1.removeRow(rows);
							for(int i=0;i<model_1.getRowCount();i++)
							{
								model_1.setValueAt(i+1, i, 0);
							}
						}
					}
			    }
			    else
			    {
			    	column = 1;
			    	rows = table_1.getSelectedRow();
			    	value = table_1.getModel().getValueAt(rows, column).toString();
			    	String[] list=new String[rcount];
					for(int z=0;z<rcount;z++)
					{
						Value=table.getModel().getValueAt(z, 1).toString();
						list[z]=Value;
					}
					for (int a=0;a<list.length;a++) 
					{
						if(!list[a].equals(value))
						{
							sts=true;
						}
						else
						{
							sts=false;
						}
						stslist[a]=sts;
					}
					int q=1;
					for(int b=0;b<stslist.length;b++)
					{
						if(stslist[b]==true)
						{
							if(stslist.length==q)
							{	
								if(model_1.getRowCount()>0)
								{
									model_1.removeRow(rows);
									for(int i=0;i<model_1.getRowCount();i++)
									{
										model_1.setValueAt(i+1, i, 0);
									}
								}
							}
							q++;
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Item Already Exist's in the PR");
							textField_4.setText("");
			    			textField_15.setText("");
							textField_16.setText("");
							textField_14.setText("");
							textField_18.setText("");
							textField_19.setText("");
							textField_20.setText("0");
							textField_21.setText("");
							textField_25.setText("");
							textField_22.setText("");
							textField_23.setText("");
							textField_24.setText("");
							textField_17.setText("");
							comboBox_2.removeAllItems();
							textField_4.requestFocus();
							model_1.removeRow(rows);
							for(int i=0;i<model_1.getRowCount();i++)
							{
								model_1.setValueAt(i+1, i, 0);
							}
						}
					}	
			    }
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	public static void get_ven_items(String company,String vendor_id,String serch, String username)
	{
		String serch_str = "";
		if(!serch.equals(""))
		{
			serch_str="and ("+company+".mr_purchase_order_history.item_name LIKE '%"+serch+"%' OR "+company+".mr_purchase_order_history.item_code LIKE '%"+serch+"%' OR  "+company+".mr_purchase_order_history.barcode LIKE '%"+serch+"%') ";
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			int rowCount = model_1.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model_1.removeRow(i);
			}
			String query="SELECT mr_purchase_order_history.item_code,mr_purchase_order_history.barcode,mr_purchase_order_history.item_name,mr_purchase_order_history.sp,`tax_type`,mr_tmp_item_master.qty,mr_tmp_item_master.cf,cost FROM "+company+".mr_purchase_order_history INNER join  "+company+".mr_purchase_list_history on "+company+".mr_purchase_list_history.po_no="+company+".mr_purchase_order_history.ref_po_num inner join "+company+".mr_tmp_item_master on mr_tmp_item_master.item_code=mr_purchase_order_history.item_code  where "+company+".mr_purchase_list_history.vendor_id='"+vendor_id+"' "+serch_str+" group by "+company+".mr_purchase_order_history.item_code order by "+company+".mr_purchase_order_history.id desc "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String code=resultSet.getString("item_code");
                String name=resultSet.getString("item_name");
                String barcode=resultSet.getString("barcode");
                String tax = resultSet.getString("tax_type");
				float cost=Float.parseFloat(resultSet.getString("cost"));
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
                String selling_price=resultSet.getString("sp");
                String qty=resultSet.getString("qty");
                model_1.addRow(new Object[]{model_1.getRowCount()+1, code, name,barcode,item_cost,selling_price,qty});
            }
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	public void get_item_dt(String company, String po_num,String decimal) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query1="SELECT grand_disc FROM "+company+".mr_purchase_return_list where pr_no ='"+po_num+"' "; 
			ResultSet resultSet1 = statement.executeQuery(query1);
			double grand_disc=0;
			if(resultSet1.next())
			{
				grand_disc=Double.parseDouble(resultSet1.getString("grand_disc"));
			}
			String query="SELECT * FROM "+company+".mr_purchase_return where ref_pr_num ='"+po_num+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String barcode =resultSet.getString("barcode");
				String itm_cde =resultSet.getString("item_code");
				double qty =Double.parseDouble(resultSet.getString("qty")) ;
				String unit =resultSet.getString("unit");
				double unit_cost =Double.parseDouble(resultSet.getString("unit_cost"));
				double itm_disc =Double.parseDouble(resultSet.getString("itm_disc"));
				double tax_val =Double.parseDouble(resultSet.getString("tax_val"));
				int cf =Integer.parseInt(resultSet.getString("cf")) ;
				double total =Double.parseDouble(resultSet.getString("total"));
				Statement statement2=con.createStatement();
				String query2="SELECT * FROM "+company+".mr_item_master where item_code ='"+itm_cde+"' "; 
				ResultSet resultSet2 = statement2.executeQuery(query2);
				String itm_dtls = null ;
				String tax = null;
				String tax_type = null ;
				if(resultSet2.next())
				{
					itm_dtls=resultSet2.getString("description1");
					tax =resultSet2.getString("input_tax");
					String taxtype =resultSet2.getString("po_inclusive_tax");
					if(!tax.equals(""))
					{
						if(taxtype.equals("1"))
						{
							tax_type="INC" ;
						}
						else
						{
							tax_type="EXC" ;
						}
					}
					else
					{
						tax_type="NON" ;
					}
				}
				
				model.insertRow(model.getRowCount(), new Object[] { model.getRowCount()+1,  barcode, itm_cde,itm_dtls,qty,unit,cf,unit_cost,itm_disc,total,tax,tax_type,tax_val,"Print"});
			}
			double total=0;
			double vatpric=0;
			double grnd_ttl=0;
			for(int a=0;a<model.getRowCount();a++)
			{
				String ttl=model.getValueAt(a, 9).toString();
				String vat=model.getValueAt(a, 12).toString();
				total=total+Double.parseDouble(ttl);
				vatpric=vatpric+Double.parseDouble(vat);
			}
			
			BigDecimal bz = new BigDecimal(total);
    		bz = bz.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
    		total = bz.doubleValue();
    		
    		BigDecimal bv = new BigDecimal(vatpric);
    		bv = bv.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
    		vatpric = bv.doubleValue();
			
			grnd_ttl=total+vatpric;
			BigDecimal bh = new BigDecimal(grnd_ttl);
    		bh = bh.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
    		grnd_ttl = bh.doubleValue();
    		
			textField_1.setText(Double.toString(grand_disc));
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
            		double ttl_per=(total*disc_per)/100;
            		BigDecimal bd = new BigDecimal(disc_per);
            		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
            		disc_per = bd.doubleValue();
            		textField_1.setText(Double.toString(disc_per));
            		//double ttl_per=(Double.parseDouble(disc_per)*100)/total;
            		BigDecimal bf = new BigDecimal(ttl_per);
            		bf = bf.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
            		ttl_per = bf.doubleValue();
            		textField_12.setText(Double.toString(ttl_per));
            		
            		double subttl=total - ttl_per;
            		BigDecimal bs = new BigDecimal(subttl);
            		bs = bs.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
            		subttl = bs.doubleValue();
            		textField_8.setText(Double.toString(subttl));
            		
            		double grand_vat=(Double.parseDouble(txval)-((Double.parseDouble(txval)*disc_per)/100)) ;
            		BigDecimal bx = new BigDecimal(grand_vat);
            		bx = bx.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
            		grand_vat = bx.doubleValue();
            		textField_9.setText(Double.toString(grand_vat));
            		
            		grnd_ttl=subttl + grand_vat;
            		BigDecimal bg = new BigDecimal(grnd_ttl);
            		bg = bg.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
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
	public void validate_barcode_unit(String company,  String itemcode, String unitname) {
		try{
			String barcode = null;
			String cf=null;
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_item_master where item_code='"+itemcode+"' and '"+unitname+"' IN (unit1,unit2,unit3,unit4,unit5,unit6) "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
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
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
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
			System.err.println(e);
		}
	}
}