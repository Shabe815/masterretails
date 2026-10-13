package inventory_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import com.toedter.calendar.JDateChooser;

public class Item_history {

	public static JFrame frame;
	private DefaultTableModel model;
	private JTable table;
	private String datein="";
	private String dateout="";
	private String po="";
	private String pr="";
	private String sa="";
	private String sr="";
	private String in="";
	private String stock="";
	private String AS="";
	
	private JCheckBox chckbxPo;
	private JCheckBox chckbxPr;
	private JCheckBox chckbxSa;
	private JCheckBox chckbxSr;
	private JCheckBox chckbxIn;
	private JCheckBox chckbxStock;
	private JCheckBox chckbxAs;
	
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	public static JTextField textField;
	public static JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Item_history window = new Item_history("mr_pro","S01", "1.0","2","11300013","Mobile Charger", "");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param kit 
	 */
	public Item_history(String company, String store_name,String version,String decimal,String itemcode,String itemname, String kit) throws JSONException {
		initialize(company,store_name, version,decimal,itemcode,itemname, kit);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company, final String store_name,final String version, final String decimal,final String itemcode,final String itemname,final String kit) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 696);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Item History");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(780, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int rowCount = model.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					if(chckbxPo.isSelected())
					{
						po="";
						chckbxPo.setSelected(false);
					}
					if(chckbxPr.isSelected())
					{
						chckbxPr.setSelected(false);
						pr="";
					}
					if(chckbxSr.isSelected())
					{
						chckbxSr.setSelected(false);
						sr="";
					}
					if(chckbxSa.isSelected())
					{
						chckbxSa.setSelected(false);
						sa="";
					}
					if(chckbxAs.isSelected())
					{
						chckbxAs.setSelected(false);
						AS="";
					}
					if(chckbxIn.isSelected())
					{
						chckbxIn.setSelected(false);
						in="";
					}
					if(chckbxStock.isSelected())
					{
						chckbxStock.setSelected(false);
						stock="";
					}
					textField.setText("");
					textField_1.setText("");
					get_itemdisp(company,itemcode,po,pr,sa,in,sr,AS,stock,datein,dateout,"", kit);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		final Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JMenuItem menuItem_3 = new JMenuItem("Print");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					if(dateout.equals(""))
					{
						dateout=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText(); 
					}
					if(datein.equals(""))
					{
						datein=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
						if(datein.equals(""))
						{
							datein=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();	
						} 
					}
					if(chckbxPo.isSelected())
					{
						po="PO";
					}
					else
					{
						po="";
					}
					if(chckbxPr.isSelected())
					{
						pr="PR";
						
					}
					else
					{
						pr="";
					}
					if(chckbxSr.isSelected())
					{
						sr="AD";
						
					}
					else
					{
						sr="";
					}
					if(chckbxSa.isSelected())
					{
						sa="SA";
						
					}
					else
					{
						sa="";
					}
					if(chckbxIn.isSelected())
					{
						in="IN";
						
					}
					else
					{
						in="";
					}
					if(chckbxAs.isSelected())
					{
						AS="AS";
						
					}
					else
					{
						AS="";
					}
					if(chckbxSr.isSelected())
					{
						stock="STOCK";
						
					}
					else
					{
						stock="";
					}
					if(sa.equals("") && sr.equals("") && po.equals("") && pr.equals("") && AS.equals("") && in.equals("") && stock.equals("") )
					{
						sa="SA";
						sr="AD";
						pr="PR";
						po="PO";
						AS="AS";
						in="IN";
						stock="STOCK";
					}
					String cust_code=textField.getText();
					ArrayList<String> querylist = new ArrayList<String>();
					if(cust_code.startsWith("V-"))
					{
						cust_code=cust_code.replace("V-", "");
					}
					String poquery="";
					if(!po.equals(""))
					{
						String vendor="";
						if(!cust_code.equals(""))
						{
							vendor=" and vendor_id='"+cust_code+"' ";
						}
						poquery="SELECT posted_date as created_date,item_code,po_no as trans_code,qty,unit,cf,cost_after_sub_ttl_disc as cost,cost_after_sub_ttl_disc*qty as ttl_cost,'0' as price,'0' as tranQty,'0' as ttl_price,po_no as remark,user_id as user,barcode,vendor_id as cust_id,vendor_name as cust_name,item_name as itemname FROM "+company+".mr_purchase_order_history inner join "+company+".mr_purchase_list_history on mr_purchase_order_history.ref_po_num=mr_purchase_list_history.po_no inner join "+company+".mr_vendor on mr_purchase_list_history.vendor_id=mr_vendor.vendor_code where item_code='"+itemcode+"' and ( posted_date BETWEEN '"+datein+"' AND '"+dateout+"' ) "+vendor+" ";
						querylist.add("PO");
					}
					String prquery="";
					if(!pr.equals(""))
					{
						String vendor="";
						if(!cust_code.equals(""))
						{
							vendor=" and vendor_id='"+cust_code+"' ";
						}
						prquery="SELECT pr_date as created_date,item_code,pr_no as trans_code,qty,unit,cf,cost_after_sub_ttl as cost,cost_after_sub_ttl*qty as ttl_cost,'0' as price,'0' as tranQty,'0' as ttl_price,pr_no as remark,user_id as user,barcode,vendor_id as cust_id,vendor_name as cust_name,'' as itemname  FROM "+company+".mr_purchase_return inner join "+company+".mr_purchase_return_list on mr_purchase_return.ref_pr_num=mr_purchase_return_list.pr_no inner join "+company+".mr_vendor on mr_purchase_return_list.vendor_id=mr_vendor.vendor_code where item_code='"+itemcode+"' and ( pr_date BETWEEN '"+datein+"' AND '"+dateout+"' ) "+vendor+" ";
						querylist.add("PR");
					}
					String saquery="";
					if(!sa.equals(""))
					{
						String vendor="";
						if(!cust_code.equals(""))
						{
							vendor=" and cust_code='"+cust_code+"' ";
						}
						saquery="select mr_pos_settlement_history.created_date,item_code,ref_receipt_num as trans_code,mr_pos_settlement_history_items.qty,unit,cf,item_cost as cost,item_cost*mr_pos_settlement_history_items.qty as ttl_cost,sp as price,mr_pos_settlement_history_items.qty as tranQty,mr_pos_settlement_history_items.qty*sp as ttl_price,ref_reg_num as remark,mr_pos_settlement_history.user,barcode,cust_code as cust_id,cust_name,item_desc as itemname  from "+company+".mr_pos_settlement_history_items inner join "+company+".mr_pos_settlement_history on mr_pos_settlement_history.reg_num=mr_pos_settlement_history_items.ref_reg_num and mr_pos_settlement_history.receipt_no=mr_pos_settlement_history_items.ref_receipt_num where item_code='"+itemcode+"' and ( mr_pos_settlement_history.created_date BETWEEN '"+datein+"' AND '"+dateout+"' ) "+vendor+" ";
						querylist.add("SA");
					}
					String srquery="";
					if(!sr.equals(""))
					{
						srquery="SELECT adj_date as created_date,item_code,adj_no as trans_code,qty,unit,cf,unit_cost as cost,unit_cost*qty as ttl_cost,'0' as price,'0' as tranQty,'0' as ttl_price,adj_no as remark,user_id as user,barcode,'' as cust_id,'' as cust_name,item_name as itemname FROM "+company+".mr_inv_trans_history inner join "+company+".mr_inv_trans_list_history on mr_inv_trans_list_history.adj_no=mr_inv_trans_history.ref_adj_num where item_code='"+itemcode+"' and ( adj_date BETWEEN '"+datein+"' AND '"+dateout+"' ) ";
						querylist.add("AD");
					}
					String inquery="";
					if(!in.equals(""))
					{
						String vendor="";
						if(!cust_code.equals(""))
						{
							vendor=" and cust_id='"+cust_code+"' ";
						}
						if(kit.equals("0"))
						{
							inquery="select mr_sd_list_history.date as created_date,item_code,ref_inv_num as trans_code,mr_sd_lines_history.qty,unit,cf,item_cost/(cf*qty) as cost,item_cost as ttl_cost,unit_cost as price,mr_sd_lines_history.qty as tranQty,(mr_sd_lines_history.qty*unit_cost) as ttl_price,mr_sd_lines_history.remark as remark,mr_sd_list_history.user_id,barcode,cust_id as cust_id,cust_name,item_name as itemname from "+company+".mr_sd_lines_history inner join "+company+".mr_sd_list_history on mr_sd_list_history.invoice_num=mr_sd_lines_history.ref_inv_num where item_code='"+itemcode+"' and ( mr_sd_list_history.created_date BETWEEN '"+datein+"' AND '"+dateout+"' ) "+vendor+" ";
						}
						else
						{
							inquery="select mr_sd_list_history.date as created_date,item_code,ref_inv_num as trans_code,mr_sd_lines_history.qty,unit,cf,item_cost/(cf*qty)  as cost,item_cost as ttl_cost,unit_cost as price,mr_sd_lines_history.qty as tranQty,(mr_sd_lines_history.qty*unit_cost) as ttl_price,mr_sd_lines_history.remark as remark,mr_sd_list_history.user_id,barcode,cust_id as cust_id,cust_name,item_name as itemname from "+company+".mr_sd_lines_history inner join "+company+".mr_sd_list_history on mr_sd_list_history.invoice_num=mr_sd_lines_history.ref_inv_num inner join "+company+".mr_kit_items on mr_kit_items.child_itemcode=mr_sd_lines_history.item_code where parent_itemcode='"+itemcode+"' and ( mr_sd_list_history.created_date BETWEEN '"+datein+"' AND '"+dateout+"' ) "+vendor+" ";
						}
						
						querylist.add("IN");
					}
					
					String union=" UNION ";
					if(cust_code.startsWith("V-"))
					{
						cust_code=cust_code.replace("V-", "");
					}
					
					
					
					
					String finalquery="";
					int a=querylist.size();
					if(querylist.size()>1)
					{
						for(int i=0;i<a;i++)
						{
							if(querylist.get(i).equals("PO"))
							{
								finalquery += poquery;
							}
							if(querylist.get(i).equals("PR"))
							{
								finalquery += prquery;
							}
							if(querylist.get(i).equals("SA"))
							{
								finalquery += saquery;
							}
							if(querylist.get(i).equals("AD"))
							{
								finalquery += srquery;
							}
							if(querylist.get(i).equals("IN"))
							{
								finalquery += inquery;
							}
							if(i<a-1)
							{
								finalquery += union;
							}
						}
					}
					else
					{
						if(querylist.get(0).equals("PO"))
						{
							finalquery=poquery;
						}
						if(querylist.get(0).equals("PR"))
						{
							finalquery=prquery;
						}
						if(querylist.get(0).equals("SA"))
						{
							finalquery=saquery;
						}
						if(querylist.get(0).equals("AD"))
						{
							finalquery = srquery;
						}
						if(querylist.get(0).equals("IN"))
						{
							finalquery += inquery;
						}
					}
					Item_master.frame.setAlwaysOnTop(false);
					frame.setAlwaysOnTop(false);
					report_master.item_history.report(itemcode,con, datein, dateout,po,pr,sa,sr,AS,in,stock,itemname,company,finalquery);
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		menuItem_3.setBorder(null);
		menuItem_3.setIcon(new ImageIcon(printer));
		menuItem_3.setBackground(new Color(243, 229, 216));
		menuItem_3.setBounds(701, 7, 80, 48);
		panel.add(menuItem_3);
		
		final Image doc=new ImageIcon(this.getClass().getResource("/doc.png")).getImage();
		JMenuItem menuItem_2 = new JMenuItem("Export");
		menuItem_2.setBorder(null);
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					export( company,itemname);

				} catch (Exception e) {
					System.err.println(e);
				}
			}
		});
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(605, 7, 80, 48);
		menuItem_2.setIcon(new ImageIcon(doc));
		panel.add(menuItem_2);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 589);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 116, 908, 425);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel_1.add(scrollPane);
		

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
		    table.setFont(new Font("Serif", Font.BOLD, 15));
		    table.setRowHeight(20);
		    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		    
		    
		    model.addColumn("S.No"); 
		    model.addColumn("Date");
		    model.addColumn("Barcode");
		    model.addColumn("Item Code"); 
		    model.addColumn("Trans No."); 
			model.addColumn("UnitQty"); 
			model.addColumn("Unit"); 
			model.addColumn("C.F");
			model.addColumn("Cost");
			model.addColumn("TotalCost"); 
			model.addColumn("Tranprice"); 
			model.addColumn("TranQty");
			model.addColumn("TranTotal");
			model.addColumn("Remark");
			model.addColumn("User");
			model.addColumn("Cust/Vendor Code");
			model.addColumn("Cust/Vendor Name");
			model.addColumn("Item Name");
			
			
		scrollPane.setViewportView(table);
		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(30);
			  if (i==1) column.setPreferredWidth(80);
			  if (i==2) column.setPreferredWidth(100);
			  if (i==3) column.setPreferredWidth(80);
			  if (i==4) column.setPreferredWidth(140);
			  if (i==5) column.setPreferredWidth(80);
			  if (i==6) column.setPreferredWidth(50);
			  if (i==7) column.setPreferredWidth(50);
			  if (i==8) column.setPreferredWidth(50);
			  if (i==9) column.setPreferredWidth(80);
			  if (i==10) column.setPreferredWidth(50);
			  if (i==11) column.setPreferredWidth(50);
			  if (i==12) column.setPreferredWidth(80);
			  if (i==13) column.setPreferredWidth(180);
			  if (i==14) column.setPreferredWidth(100);
			  if (i==15) column.setPreferredWidth(100);
			  if (i==16) column.setPreferredWidth(200);
			  if (i==17) column.setPreferredWidth(450);
			}
		
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 908, 90);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		Date date = new Date();
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(668, 33, 100, 22);
		dateChooser.setDate(date);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		panel_2.add(dateChooser);
		
		JLabel lblDateRange = new JLabel("Date Range");
		lblDateRange.setBounds(589, 37, 75, 16);
		panel_2.add(lblDateRange);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(800, 33, 100, 22);
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setDate(date);
		panel_2.add(dateChooser_1);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(780, 37, 18, 16);
		panel_2.add(lblTo);
		
		JLabel lblItemName = new JLabel("Item Name : ");
		lblItemName.setBounds(2, 1, 86, 25);
		panel_2.add(lblItemName);
		
		JLabel lblAquafina = new JLabel(itemname);
		lblAquafina.setBounds(90, 1, 422, 25);
		panel_2.add(lblAquafina);
		
		JLabel lblItemCode = new JLabel("Item Code :");
		lblItemCode.setBounds(10, 31, 75, 16);
		panel_2.add(lblItemCode);
		
		JLabel label = new JLabel(itemcode);
		label.setBounds(88, 31, 127, 16);
		panel_2.add(label);
		
		chckbxPo = new JCheckBox("PO");
		chckbxPo.setSelected(true);
		
		chckbxPo.setBounds(635, 1, 52, 25);
		panel_2.add(chckbxPo);
		
		
		chckbxPr = new JCheckBox("PR");
		chckbxPr.setSelected(true);
		
		chckbxPr.setBounds(689, 1, 52, 25);
		panel_2.add(chckbxPr);
		
		
		chckbxSa = new JCheckBox("SA");
		chckbxSa.setSelected(true);
		
		chckbxSa.setBounds(743, 1, 52, 25);
		panel_2.add(chckbxSa);
		
		
		chckbxSr = new JCheckBox("AD");
		
		chckbxSr.setBounds(797, 1, 52, 25);
		panel_2.add(chckbxSr);
		
		
		JButton btnGo = new JButton("GO");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datein=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if(datein.equals(""))
				{
					datein=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				}
				dateout=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				if(chckbxPo.isSelected())
				{
					po="PO";
				}
				else
				{
					po="";
				}
				if(chckbxPr.isSelected())
				{
					pr="PR";
					
				}
				else
				{
					pr="";
				}
				if(chckbxSr.isSelected())
				{
					sr="AD";
					
				}
				else
				{
					sr="";
				}
				if(chckbxSa.isSelected())
				{
					sa="SA";
					
				}
				else
				{
					sa="";
				}
				if(chckbxIn.isSelected())
				{
					in="IN";
					
				}
				else
				{
					in="";
				}
				if(chckbxAs.isSelected())
				{
					AS="AS";
					
				}
				else
				{
					AS="";
				}
				if(chckbxStock.isSelected())
				{
					stock="STOCK";
					
				}
				else
				{
					stock="";
				}
				get_itemdisp(company,itemcode,po,pr,sa,in,sr,AS,stock,datein,dateout,textField.getText(), kit);
			}
		});
		btnGo.setBounds(848, 59, 52, 25);
		panel_2.add(btnGo);
		
		JLabel lblCustvendor = new JLabel("Cust/Vendor : ");
		lblCustvendor.setBounds(10, 58, 86, 16);
		panel_2.add(lblCustvendor);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(94, 58, 75, 22);
		panel_2.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(182, 58, 304, 22);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Item Code List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Item_master.frame.setAlwaysOnTop(false);
					frame.setAlwaysOnTop(false);
					
					new sales_master.Cust_Chooser(company,"item_history", "", store_name,"","", version,"");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(498, 59, 25, 22);
		panel_2.add(btnNewButton);
		
		chckbxAs = new JCheckBox("AS");
		chckbxAs.setBounds(581, 0, 52, 25);
		panel_2.add(chckbxAs);
		
		chckbxStock = new JCheckBox("STOCK");
		chckbxStock.setBounds(514, 1, 65, 25);
		panel_2.add(chckbxStock);
		
		chckbxIn = new JCheckBox("IN");
		chckbxIn.setBounds(851, 0, 52, 25);
		panel_2.add(chckbxIn);
		
		JLabel lblQtyIn = new JLabel("Qty IN");
		lblQtyIn.setBounds(12, 550, 46, 14);
		panel_1.add(lblQtyIn);
		
		JLabel lblQtyOut = new JLabel("Qty OUT");
		lblQtyOut.setBounds(284, 552, 46, 14);
		panel_1.add(lblQtyOut);
		
		JLabel lblQtyBalance = new JLabel("Qty Balance");
		lblQtyBalance.setBounds(555, 552, 75, 14);
		panel_1.add(lblQtyBalance);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(53, 547, 125, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(340, 547, 125, 20);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(629, 547, 125, 20);
		panel_1.add(textField_4);
		
		//get_itemdisp(company,itemcode,po,pr,sa,in,sr,AS,stock,datein,dateout,textField.getText());
	}
	
	
	private void export( String company,String itemname) throws IOException
	{
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\ItemHistory-"+itemname+".csv");

			for(int x = 0; x < model.getColumnCount(); x++){
	            excel.write(model.getColumnName(x) );
	            excel.write(",");
	        }

	        excel.write("\n");

	        for(int x=0; x< model.getRowCount(); x++) {
	            for(int y=0; y < model.getColumnCount(); y++) {
	            	String value=model.getValueAt(x,y).toString();
	            	if(value.equals("") || value==null)
	            	{
	            		value="0";
	            	}
	                excel.write(value);
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
	}
	private void  get_itemdisp( String company,String item_code,String po,String pr,String sa,String in,String sr,String AS,String stock,String datein,String dateout,String cust_code, String kit)
	{
		ArrayList<String> querylist = new ArrayList<String>();
//		String dates = "";
//		if(!datein.equals(""))
//		{
//			dates=" and ( created_date BETWEEN '"+datein+"' AND '"+dateout+"' ) ";	
//		}
		if(cust_code.startsWith("V-"))
		{
			cust_code=cust_code.replace("V-", "");
		}
		String poquery="";
		if(!po.equals(""))
		{
			String vendor="";
			if(!cust_code.equals(""))
			{
				vendor=" and vendor_id='"+cust_code+"' ";
			}
			poquery="SELECT 'PO' as type,posted_date as created_date,item_code,po_no as trans_code,qty,unit,cf,cost_after_sub_ttl_disc as cost,cost_after_sub_ttl_disc*qty as ttl_cost,'0' as price,'0' as tranQty,'0' as ttl_price,po_no as remark,user_id as user,barcode,vendor_id as cust_id,vendor_name as cust_name,item_name as itemname FROM "+company+".mr_purchase_order_history inner join "+company+".mr_purchase_list_history on mr_purchase_order_history.ref_po_num=mr_purchase_list_history.po_no inner join "+company+".mr_vendor on mr_purchase_list_history.vendor_id=mr_vendor.vendor_code where item_code='"+item_code+"' and ( posted_date BETWEEN '"+datein+"' AND '"+dateout+"' ) "+vendor+" ";
			querylist.add("PO");
		}
		String prquery="";
		if(!pr.equals(""))
		{
			String vendor="";
			if(!cust_code.equals(""))
			{
				vendor=" and vendor_id='"+cust_code+"' ";
			}
			prquery="SELECT 'PR' as type,pr_date as created_date,item_code,pr_no as trans_code,qty,unit,cf,cost_after_sub_ttl as cost,cost_after_sub_ttl*qty as ttl_cost,'0' as price,'0' as tranQty,'0' as ttl_price,pr_no as remark,user_id as user,barcode,vendor_id as cust_id,vendor_name as cust_name,'' as itemname  FROM "+company+".mr_purchase_return inner join "+company+".mr_purchase_return_list on mr_purchase_return.ref_pr_num=mr_purchase_return_list.pr_no inner join "+company+".mr_vendor on mr_purchase_return_list.vendor_id=mr_vendor.vendor_code where item_code='"+item_code+"' and ( pr_date BETWEEN '"+datein+"' AND '"+dateout+"' ) "+vendor+" ";
			querylist.add("PR");
		}
		String saquery="";
		if(!sa.equals(""))
		{
			String vendor="";
			if(!cust_code.equals(""))
			{
				vendor=" and cust_code='"+cust_code+"' ";
			}
			saquery="select 'SA' as type,mr_pos_settlement_history.created_date,item_code,ref_receipt_num as trans_code,mr_pos_settlement_history_items.qty,unit,cf,item_cost as cost,item_cost*mr_pos_settlement_history_items.qty*cf as ttl_cost,sp as price,mr_pos_settlement_history_items.qty as tranQty,mr_pos_settlement_history_items.qty*sp as ttl_price,ref_reg_num as remark,mr_pos_settlement_history.user,barcode,cust_code as cust_id,cust_name,item_desc as itemname  from "+company+".mr_pos_settlement_history_items inner join "+company+".mr_pos_settlement_history on mr_pos_settlement_history.reg_num=mr_pos_settlement_history_items.ref_reg_num and mr_pos_settlement_history.receipt_no=mr_pos_settlement_history_items.ref_receipt_num where item_code='"+item_code+"' and type!='VOID' and ( mr_pos_settlement_history.created_date BETWEEN '"+datein+"' AND '"+dateout+"' ) "+vendor+" ";
			querylist.add("SA");
		}
		String inquery="";
		if(!in.equals(""))
		{
			String vendor="";
			if(!cust_code.equals(""))
			{
				vendor=" and cust_id='"+cust_code+"' ";
			}
			if(kit.equals("0"))
			{
				inquery="select 'IN' as type,mr_sd_list_history.date as created_date,item_code,ref_inv_num as trans_code,mr_sd_lines_history.qty,unit,cf,item_cost/(cf*qty) as cost,item_cost as ttl_cost,unit_cost as price,mr_sd_lines_history.qty as tranQty,(mr_sd_lines_history.qty*unit_cost) as ttl_price,mr_sd_lines_history.remark as remark,mr_sd_list_history.user_id as user,barcode,cust_id as cust_id,cust_name,item_name as itemname from "+company+".mr_sd_lines_history inner join "+company+".mr_sd_list_history on mr_sd_list_history.invoice_num=mr_sd_lines_history.ref_inv_num where mr_sd_list_history.type='sd_invoice' and item_code='"+item_code+"' and ( mr_sd_list_history.created_date BETWEEN '"+datein+"' AND '"+dateout+"' ) "+vendor+" ";
			}
			else
			{
				inquery="select 'IN' as type,mr_sd_list_history.date as created_date,item_code,ref_inv_num as trans_code,mr_sd_lines_history.qty,unit,cf,item_cost/(cf*qty) as cost,item_cost as ttl_cost,unit_cost as price,mr_sd_lines_history.qty as tranQty,(mr_sd_lines_history.qty*unit_cost) as ttl_price,mr_sd_lines_history.remark as remark,mr_sd_list_history.user_id as user,barcode,cust_id as cust_id,cust_name,item_name as itemname from "+company+".mr_sd_lines_history inner join "+company+".mr_sd_list_history on mr_sd_list_history.invoice_num=mr_sd_lines_history.ref_inv_num inner join "+company+".mr_kit_items on mr_kit_items.child_itemcode=mr_sd_lines_history.item_code where mr_sd_list_history.type='sd_invoice' and  parent_itemcode='"+item_code+"' and ( mr_sd_list_history.created_date BETWEEN '"+datein+"' AND '"+dateout+"' ) "+vendor+" ";
			}
			
			querylist.add("IN");
		}
		
		String srquery="";
		if(!sr.equals(""))
		{
			srquery="SELECT 'AD' as type,adj_date as created_date,item_code,adj_no as trans_code,qty,unit,cf,unit_cost as cost,unit_cost*qty*cf as ttl_cost,'0' as price,'0' as tranQty,'0' as ttl_price,adj_no as remark,user_id as user,barcode,'' as cust_id,'' as cust_name,item_name as itemname FROM "+company+".mr_inv_trans_history inner join "+company+".mr_inv_trans_list_history on mr_inv_trans_list_history.adj_no=mr_inv_trans_history.ref_adj_num where item_code='"+item_code+"' and ( adj_date BETWEEN '"+datein+"' AND '"+dateout+"' ) ";
			querylist.add("AD");
		}
		
		String stquery="";
		if(!stock.equals(""))
		{
			//String query="SELECT * FROM "+company+".mr_item_master_history where itemcode='"+item_code+"'
			stquery="SELECT 'ST' as type,mr_item_master_history.created_date as created_date,itemcode,trans_code as trans_code,qty,unit,cf,cost as cost,cost*qty*cf as ttl_cost,'0' as price,'0' as tranQty,'0' as ttl_price,trans_code as remark,user as user,barcode,cust_id,cust_name,item_name as itemname FROM "+company+".mr_item_master_history inner join "+company+".mr_item_master on itemcode=item_code where item_code='"+item_code+"' and ( mr_item_master_history.created_date BETWEEN '"+datein+"' AND '"+dateout+"' ) ";
			querylist.add("ST");
		}
		
		
		String union=" UNION ALL ";
		String finalquery="";
		int a=querylist.size();
		if(querylist.size()>1)
		{
			for(int i=0;i<a;i++)
			{
				if(querylist.get(i).equals("PO"))
				{
					finalquery += poquery;
				}
				if(querylist.get(i).equals("PR"))
				{
					finalquery += prquery;
				}
				if(querylist.get(i).equals("SA"))
				{
					finalquery += saquery;
				}
				if(querylist.get(i).equals("AD"))
				{
					finalquery += srquery;
				}
				if(querylist.get(i).equals("IN"))
				{
					finalquery += inquery;
				}
				if(querylist.get(i).equals("ST"))
				{
					finalquery += stquery;
				}
				if(i<a-1)
				{
					finalquery += union;
				}
			}
		}
		else
		{
			if(querylist.get(0).equals("PO"))
			{
				finalquery=poquery;
			}
			if(querylist.get(0).equals("PR"))
			{
				finalquery=prquery;
			}
			if(querylist.get(0).equals("SA"))
			{
				finalquery=saquery;
			}
			if(querylist.get(0).equals("AD"))
			{
				finalquery=srquery;
			}
			if(querylist.get(0).equals("IN"))
			{
				finalquery=inquery;
			}
			if(querylist.get(0).equals("ST"))
			{
				finalquery += stquery;
			}
		}
		
		
		
		
//		if(!sa.equals(""))
//		{
//			String vendor="";
//			if(!cust_code.equals(""))
//			{
//				vendor=" and cust_code='"+cust_code+"' ";
//			}
//			saquery="select mr_pos_settlement_history.created_date,item_code,ref_receipt_num,mr_pos_settlement_history_items.qty,unit,cf,item_cost,'0',sp,mr_pos_settlement_history_items.qty,'0',ref_reg_num,mr_pos_settlement_history.user,barcode,cust_code,cust_name from "+company+".mr_pos_settlement_history_items inner join "+company+".mr_pos_settlement_history on mr_pos_settlement_history.reg_num=mr_pos_settlement_history_items.ref_reg_num and mr_pos_settlement_history.receipt_no=mr_pos_settlement_history_items.ref_receipt_num where itemcode='"+item_code+"' and ( pr_date BETWEEN '"+datein+"' AND '"+dateout+"' ) "+vendor+" ";
//		}
//		if(!po.equals("") || !pr.equals("") || !sa.equals("") || !sr.equals("") || !AS.equals("") || !in.equals("") || !stock.equals(""))
//		{
//			filter = "and ( "+POs+" or "+PRs+" or "+SAs+" or "+INs +" or "+SRs +" or "+ASs +" or "+stocks +")";
//		}
//		String cust_code_str="";
//		if(!cust_code.equals(""))
//		{
//			cust_code_str=" and ( cust_id ='"+cust_code+"' ) ";	
//		}
		
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			//String query="SELECT * FROM "+company+".mr_item_master_history where itemcode='"+item_code+"' "+cust_code_str+" "+dates+" "+filter+"  ";//"+serch_str+" ORDER BY "+filter_name+" ASC ";// INNER join "+company+".mr_item_barcode on "+company+".mr_item_master.item_code="+company+".mr_item_barcode.ref_item_code "+serch_str+" GROUP BY "+company+".mr_item_master.item_code 
			ResultSet resultSet1 = statement.executeQuery(finalquery +" order by created_date");
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			double qtyin=0;
			double qtyout=0;
			if(resultSet1.next())
			{
				//String query1="SELECT * FROM "+company+".mr_item_master_history where itemcode='"+item_code+"' "+cust_code_str+" "+dates+" "+filter+"  ";//"+serch_str+" ORDER BY "+filter_name+" ASC ";// INNER join "+company+".mr_item_barcode on "+company+".mr_item_master.item_code="+company+".mr_item_barcode.ref_item_code "+serch_str+" GROUP BY "+company+".mr_item_master.item_code 
				ResultSet resultSet = statement.executeQuery(finalquery  +" order by created_date");
				while(resultSet.next())
				{
					String date=resultSet.getString("created_date");
					String code=resultSet.getString("item_code");
					String trans_code=resultSet.getString("trans_code");
					String qty=resultSet.getString("qty");
					String unit=resultSet.getString("unit");
					String cf=resultSet.getString("cf");
					String cost=resultSet.getString("cost");
					String ttl_cost=resultSet.getString("ttl_cost");
					String price=resultSet.getString("price");
					String tranQty=resultSet.getString("tranQty");
					String ttl_price=resultSet.getString("ttl_price");
					String remark=resultSet.getString("remark");
					String user=resultSet.getString("user");
					String barcode=resultSet.getString("barcode");
					String custcode=resultSet.getString("cust_id");
					String custname=resultSet.getString("cust_name");
					String itemname=resultSet.getString("itemname");
					
					if(resultSet.getString("type").equals("PO"))
					{
						qtyin += Double.parseDouble(qty)*Double.parseDouble(cf);
					}
					if(resultSet.getString("type").equals("ST"))
					{
						qtyin += Double.parseDouble(qty)*Double.parseDouble(cf);
					}
					if(resultSet.getString("type").equals("PR"))
					{
						qtyin -= Double.parseDouble(qty)*Double.parseDouble(cf);
					}
					if(resultSet.getString("type").equals("SA"))
					{
						qtyout+= Double.parseDouble(qty)*Double.parseDouble(cf);
					}
					if(resultSet.getString("type").equals("IN"))
					{
						qtyout+= Double.parseDouble(qty)*Double.parseDouble(cf);
					}
					if(resultSet.getString("type").equals("AD"))
					{
						if(Double.parseDouble(qty)<0)
						{
							qtyout+= Double.parseDouble(qty)*Double.parseDouble(cf);
						}
						else
						{
							qtyin+= Double.parseDouble(qty)*Double.parseDouble(cf);
						}
					}
					
	                model.addRow(new Object[]{model.getRowCount()+1,date ,barcode,code,trans_code,qty,unit,cf,cost,ttl_cost,price,tranQty,ttl_price,remark,user,custcode,custname,itemname });
	                
				}
			}
			
			else
			{
				JOptionPane.showMessageDialog(frame, "No Result Found");
			}
			textField_2.setText(Double.toString(qtyin));
			textField_3.setText(Double.toString(qtyout));
			double bal=qtyin-qtyout;
			textField_4.setText(Double.toString(bal));
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog( null, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}
}