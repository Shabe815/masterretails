package sales_distribution;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextArea;

public class delivery_post_view {

	static JFrame frame;
	//private String supp_name;
	private JTextField textField_2;
	public static JTextField textField_3;
	private static DefaultTableModel model;
	public static JTable table;
	private JDateChooser dateChooser ;
	private JLabel label_1;
	//private JButton btnProceed;
	private JMenuItem menuItem_1;
	private JMenuItem menuItem_3;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JTextArea textArea_1;
	//private boolean dot=false;
	@SuppressWarnings("unused")
	private boolean minus=false;
	private JMenuItem menuItem_2;
	private static int decimal;
	private JTextField textField_27;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					delivery_post_view window = new delivery_post_view("mr_pro","admin","001","IN-21-01-000002","S01", "","2","" );
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
	public delivery_post_view(String db_name, String username,String cust_id, String po_num, String store_name,String version,String decimal,String do_num) throws JSONException, SQLException {
		initialize(db_name,username,cust_id,po_num, store_name, version,decimal,do_num);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param db_name 
	 * @param host_name 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String db_name,final String username,final String cust_id,String po_num, final String store_name,final String version,final String decimals,String do_num) throws JSONException, SQLException {
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
		
		//Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		JLabel lblPurchaseOrder = new JLabel("Delivery");
		lblPurchaseOrder.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPurchaseOrder.setBounds(12, 6, 228, 30);
		panel.add(lblPurchaseOrder);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rows=model.getRowCount();
				if(rows>0)
				{
					int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Exit","Warning",JOptionPane.YES_NO_OPTION);
			        // 0=yes, 1=no, 2=cancel
			        if(cofrm_bx==0)
			        {
			        	frame.setVisible(false);
			        	sales_distribution.Delivery_list.get_po_disp(db_name, "OPEN", "");
			        }
			        
				}
				else
				{
					frame.setVisible(false);
					sales_distribution.Delivery_list.get_po_disp(db_name, "OPEN", "");
					
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
				textArea.setText("");
				textArea_1.setText("");
				textField_3.setText("");
				dateChooser.setDate(new Date());
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
					JComboBox <String> comboBox = new JComboBox <String> ();
					String invo_num=textField_2.getText();
					String[] invo_num_split=invo_num.split("-");
					String invo_type=invo_num_split[0];
					if(invo_type.equals("IN"))
					{
						comboBox.addItem("DO_his_invoice1");
						comboBox.addItem("DO_his_invoice2");
						comboBox.addItem("DO_his_invoice3");
					}
					else
					{
						comboBox.addItem("SDquotation1");
						comboBox.addItem("SDquotation2");
						comboBox.addItem("SDquotation3");
					}
					
					
					JOptionPane.showMessageDialog(frame, comboBox, "Select Report Format", JOptionPane.QUESTION_MESSAGE);
					
					report_master.delivery_note.report(textField_2.getText(),con, "POSTED",textField.getText(),comboBox.getSelectedItem().toString(),db_name);
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
		panel_2.setBounds(0, 0, 281, 109);
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
		panel_3.setBounds(282, 0, 357, 109);
		panel_1.add(panel_3);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(81, 28, 263, 50);
		panel_3.add(scrollPane_3);
		
		textArea = new JTextArea();
		scrollPane_3.setViewportView(textArea);
		
		JLabel lblDONum = new JLabel("D O num");
		lblDONum.setBounds(10, 6, 56, 16);
		panel_3.add(lblDONum);
		
		textField = new JTextField(do_num);
		textField.setEditable(false);
		textField.setBounds(81, 6, 263, 16);
		panel_3.add(textField);
		textField.setColumns(10);
		
		JLabel lblRemark = new JLabel("Location");
		lblRemark.setBounds(10, 26, 56, 50);
		panel_3.add(lblRemark);
		
		JLabel lblRemark_1 = new JLabel("Remark");
		lblRemark_1.setBounds(10, 84, 56, 16);
		panel_3.add(lblRemark_1);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(77, 84, 267, 16);
		panel_3.add(textField_4);
		textField_4.setColumns(10);
		
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(243, 229, 216));
		panel_4.setLayout(null);
		panel_4.setBounds(638, 0, 319, 109);
		panel_1.add(panel_4);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(71, 35, 236, 70);
		panel_4.add(scrollPane_2);
		
		textArea_1 = new JTextArea();
		scrollPane_2.setViewportView(textArea_1);
		textArea_1.setEditable(false);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		
		JLabel lblSuppDetails = new JLabel("Cust. Name");
		lblSuppDetails.setBounds(0, 6, 77, 16);
		panel_4.add(lblSuppDetails);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(0, 35, 56, 16);
		panel_4.add(lblAddress);
		
		textField_3 = new JTextField();
		textField_3.setBounds(71, 6, 236, 16);
		panel_4.add(textField_3);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.setBounds(12, 122, 933, 541);
		panel_1.add(scrollPane);
		
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		      
		       
		       if(column==7)
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
		model.addColumn("Invoice Qty");
		model.addColumn("Delivered Qty");
		model.addColumn("Balance Qty");
		model.addColumn("Previous Delivered Qty");
		scrollPane.setViewportView(table);
		
		table.setFocusable(false);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int colmns=model.getColumnCount();
		for (int i=0; i<colmns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(35);
		  if (i==1) column.setPreferredWidth(120);
		  if (i==2) column.setPreferredWidth(75);
		  if (i==3) column.setPreferredWidth(280);
		  if (i==4) column.setPreferredWidth(50);
		  if (i==5) column.setPreferredWidth(50);
		  if (i==6) column.setPreferredWidth(80);
		  if (i==7) column.setPreferredWidth(80);
		  if (i==8) column.setPreferredWidth(80);
		  if (i==9) column.setPreferredWidth(80);
		}
		
		 table.addFocusListener(new FocusAdapter() {
		        @Override
		        public void focusLost(FocusEvent e) {
		            JOptionPane.showMessageDialog(frame, "tt");
		        }           
		    });
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(75, 361, 76, 16);
		panel_1.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(22, 659, 116, 22);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 61, 957, 2);
		frame.getContentPane().add(separator);
		
		if(po_num.equals(""))
		{
			
		}
		else
		{
			try{
				
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				String query="SELECT * FROM "+db_name+".mr_delivery_list_history where invoice_num ='"+po_num+"' and do_num='"+do_num+"' "; 
				ResultSet resultSet1 = statement.executeQuery(query);
				if(resultSet1.next())
				{
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
						textArea.setText(resultSet.getString("location"));
						textField_4.setText(resultSet.getString("remark"));
						textField_3.setText(resultSet.getString("cust_name"));
						textField_27.setText(resultSet.getString("referance"));
						textArea_1.setText(resultSet.getString("cust_addr"));
						
						textField_3.setEditable(false);
						textField_2.setEditable(false);
						textField_3.setEditable(false);
						textArea_1.setEditable(false);
						//textArea.setEditable(false);
						textField_27.setEditable(false);
						
			        	scrollPane.setEnabled(true);
			        	table.setEnabled(true);
			        	menuItem_1.setEnabled(false);
			        	menuItem_2.setEnabled(true);
			        	
			        	
			        	get_item_dt(db_name,po_num,decimal,do_num);
					}
				}
				else
				{
					String query1="SELECT * FROM "+db_name+".mr_sd_list where invoice_num ='"+po_num+"' "; 
					ResultSet resultSet = statement.executeQuery(query1);
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
						textField_4.setText(resultSet.getString("remark"));
						textField_3.setText(resultSet.getString("cust_name"));
						textField_27.setText(resultSet.getString("referance"));
						textArea_1.setText(resultSet.getString("cust_addr"));
						
						textField_2.setEditable(false);
						textField_3.setEditable(false);
						textField_4.setEditable(false);
						textArea_1.setEditable(false);
						//textArea.setEditable(false);
						textField_27.setEditable(false);
						
			        	scrollPane.setEnabled(true);
			        	table.setEnabled(true);
			        	menuItem_1.setEnabled(false);
			        	menuItem_2.setEnabled(true);
			        	
			        	
			        	get_sd_item_dt(db_name,po_num,decimal);
					}
				}
				
				con.close();
			}
			catch(Exception e){
				System.err.println(e);
			}
		}
	}
	
	
	
	public void get_sd_item_dt(String db_name, String po_num,int decimal) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			
			String query="SELECT * FROM "+db_name+".mr_sd_lines where ref_inv_num ='"+po_num+"'"; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String itemcode =resultSet.getString("item_code");
				String barcode =resultSet.getString("barcode");
				String item_name =resultSet.getString("item_name");
				String unit =resultSet.getString("unit");
				String cf =resultSet.getString("cf");
				String invoice_qty =resultSet.getString("qty");
				String delivered_qty =resultSet.getString("qty");
				String balance_qty ="0";
				String previous_delivery ="0";
				model.insertRow(model.getRowCount(), new Object[] { model.getRowCount()+1,  barcode, itemcode,item_name,unit,cf,invoice_qty,delivered_qty,balance_qty,previous_delivery});
			}
			statement.close();
			con.close();
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	
	public void get_item_dt(String db_name, String po_num,int decimal,String do_num) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			
			String query="SELECT * FROM "+db_name+".mr_delivery_note_history where ref_inv_num ='"+po_num+"' and ref_do_num='"+do_num+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String itemcode =resultSet.getString("itemcode");
				String barcode =resultSet.getString("barcode");
				String item_name =resultSet.getString("item_name");
				String unit =resultSet.getString("unit");
				String cf =resultSet.getString("cf");
				String invoice_qty =resultSet.getString("invoice_qty");
				String delivered_qty =resultSet.getString("delivered_qty");
				String balance_qty =resultSet.getString("balance_qty");
				String previous_delivery =resultSet.getString("previous_delivery");
				model.insertRow(model.getRowCount(), new Object[] { model.getRowCount()+1,  barcode, itemcode,item_name,unit,cf,invoice_qty,delivered_qty,balance_qty,previous_delivery});
			}
			statement.close();
			con.close();
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}