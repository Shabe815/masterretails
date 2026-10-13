package History_Reports;

import inventory_master.item_list_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.toedter.calendar.JDateChooser;

import java.awt.Button;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

import system_setups.department_list;

public class Item_sale {

	private static JFrame frame;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_4;
	private static DefaultTableModel model;
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					Item_sale window = new Item_sale("","mr_pro","admin", "S01", null,"");
					Item_sale.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public Item_sale(String db_name,String company, String username,  String store,String version,String language) throws ParseException {
		initialize(db_name,company,username,store, version, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param balance 
	 * @param po_num 
	 * @param company 
	 * @param host_name 
	 * @param store 
	 * @param username 
	 * @param vendor_name 
	 * @param vendor_name2 
	 * @throws ParseException 
	 */
	private void initialize(final String db_name,final String company,final String username,final String store,final String version,final String language) throws ParseException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.requestFocus();
		
		JLabel lblDateRange = new JLabel("Historical Item Sale Summary");
		lblDateRange.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateRange.setBounds(12, 24, 247, 32);
		frame.getContentPane().add(lblDateRange);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(12, 126, 43, 22);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(218, 126, 27, 22);
		frame.getContentPane().add(lblTo);
		
		Date date = new Date();
		dateChooser = new JDateChooser(date);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(53, 126, 138, 22);
		frame.getContentPane().add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(271, 126, 125, 22);
		
		dateChooser_1.setDate(date);
		frame.getContentPane().add(dateChooser_1);
		
		Button button = new Button("Print");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				if(date1.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select a Date");
				}
				else if(date2.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select a Date");
				}
				else
				{
					String dept=textField_2.getText().trim();
					String item=textField_3.getText().trim();
					String item_code=textField_4.getText().trim();
					Connection con=Dbsetup.dbconn.getConnection();
					try {
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						report_master.history_item_sale_summary.report(date1,date2, con ,dept,item,item_code,company);
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		button.setBounds(24, 310, 190, 24);
		frame.getContentPane().add(button);
		
		JLabel lblDateRange_1 = new JLabel("Date Range : ");
		lblDateRange_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateRange_1.setBounds(12, 80, 89, 22);
		frame.getContentPane().add(lblDateRange_1);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		
		JLabel label = new JLabel("Select a Department");
		label.setBounds(12, 174, 128, 22);
		frame.getContentPane().add(label);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(146, 174, 283, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton button_2 = new JButton("");
		button_2.setBounds(430, 174, 28, 22);
		frame.getContentPane().add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new department_list(company,version,"history_item_sales_summary");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_2.setToolTipText("Department List");
		button_2.setIcon(new ImageIcon(serch));
		
		JLabel lblSelectAItem = new JLabel("Select a Item");
		lblSelectAItem.setBounds(12, 229, 90, 22);
		frame.getContentPane().add(lblSelectAItem);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(105, 242, 324, 22);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton button_3 = new JButton("");
		button_3.setBounds(430, 229, 28, 22);
		frame.getContentPane().add(button_3);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con=Dbsetup.dbconn.getConnection();
					new item_list_master(db_name,company, "history_item_sales_summary", store, version, 0,con,"",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_3.setToolTipText("Item List");
		button_3.setIcon(new ImageIcon(serch));
		
		Button button_1 = new Button("Export To CSV");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					if(date1.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Select a Date");
					}
					else if(date2.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Select a Date");
					}
					else
					{
						String dept=textField_2.getText();
						String itemcode=textField_4.getText();
						//String itemname=textField_3.getText();
						String itemcode_str="";
						String dept_str="";
						if(!itemcode.equals(""))
						{
							itemcode_str=" and mr_pos_settlement_history_items.item_code='"+itemcode+"'";
						}
						if(!dept.equals(""))
						{
							dept_str=" and mr_item_master.department= '"+dept+"'";
						}
						String query="SELECT item_desc,department,sum(mr_pro.mr_pos_settlement_history_items.qty) as qty_item,sum(mr_pro.mr_pos_settlement_history_items.unit_price*mr_pro.mr_pos_settlement_history_items.qty) as net_sale,sum(mr_pro.mr_pos_settlement_history_items.total+mr_pro.mr_pos_settlement_history_items.vat) as sale_amt	FROM mr_pro.mr_pos_settlement_history inner JOIN mr_pro.mr_pos_settlement_history_items on mr_pro.mr_pos_settlement_history.reg_num=mr_pro.mr_pos_settlement_history_items.ref_reg_num and  mr_pro.mr_pos_settlement_history.receipt_no=mr_pro.mr_pos_settlement_history_items.ref_receipt_num	inner join mr_pro.mr_item_master on mr_pro.mr_item_master.item_code=mr_pro.mr_pos_settlement_history_items.item_code WHERE ( mr_pro.mr_pos_settlement_history.created_date BETWEEN '"+date1+"' and '"+date2+"')	 "+itemcode_str+" "+dept_str+"	group by mr_pos_settlement_history.reg_num,item_desc";
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();
						ResultSet resultSet1 = statement.executeQuery(query);
						if(resultSet1.next())
						{
							int rowCount = model.getRowCount();
							for (int i = rowCount - 1; i >= 0; i--) {
								model.removeRow(i);
							}
							ResultSet resultSet = statement.executeQuery(query);
							while(resultSet.next())
							{
								String code=resultSet.getString("item_desc");
				                String name=resultSet.getString("department");
				                String qty=resultSet.getString("qty_item");
				                String net_sale=resultSet.getString("net_sale");
				                String sale_amt=resultSet.getString("sale_amt");
				                double disc=Double.parseDouble(net_sale)-Double.parseDouble(sale_amt);
				                double avg=Double.parseDouble(sale_amt)/Double.parseDouble(qty);
				                model.addRow(new Object[]{code, name,qty,net_sale,disc,sale_amt,avg});
				                
							}
						}
						export_as_csv(company,itemcode);
						
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(frame, e);
					e.printStackTrace();
				}
			}
		});
		button_1.setBounds(257, 310, 190, 24);
		frame.getContentPane().add(button_1);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(105, 219, 324, 22);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnRefreash = new JButton("Clear");
		btnRefreash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		btnRefreash.setBounds(361, 77, 97, 25);
		frame.getContentPane().add(btnRefreash);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(370, 2, 100, 100);
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.setVisible(false);
		frame.getContentPane().add(scrollPane);

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
		
		model.addColumn("item name");
		model.addColumn("dept");
		model.addColumn("qty");
		model.addColumn("net sale");
		model.addColumn("disc");
		model.addColumn("sale amt");
		model.addColumn("avg sale");
		
		    table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
		    table.setRowHeight(20);
		    
		    
		   
		scrollPane.setViewportView(table);
		
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 476, 397);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	public static void  export_as_csv( String company, String itemcode) throws IOException, SQLException
	{
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		String ext="";
		if(!itemcode.equals(""))
		{
			ext="-"+itemcode;
		}
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\item_sale_report"+ext+".csv");

			for(int x = 0; x < model.getColumnCount(); x++){
	            excel.write(model.getColumnName(x) );
	            excel.write(",");
	        }

	        excel.write("\n");

	        for(int x=0; x< model.getRowCount(); x++) {
	            for(int y=0; y < model.getColumnCount(); y++) {
	                excel.write(model.getValueAt(x,y).toString());
	                if(y < model.getColumnCount()-1)
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
	        frame.setCursor(Cursor.getDefaultCursor());
	        JOptionPane.showMessageDialog(frame, "Export Completed");
	        frame.setVisible(false);
		
	}
}
