package sales_distribution;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import com.toedter.calendar.JDateChooser;

public class invoice_list {

	static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static JTextField textField_5;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					invoice_list window = new invoice_list("mr_pro","admin","S01", null,"2","");
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
	public invoice_list(String db_name, String username,String store,String version,String decimal, String cust_id) throws JSONException {
		initialize(db_name,username,store, version,decimal,cust_id);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param decimal 
	 * @param page 
	 */
	private void initialize(final String db_name,final String username,final String store,final String version, final String decimal, final String cust_id) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 723);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    	//CafePro.Mainpage.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	CafePro.Mainpage.btnPurchaseReturn.setEnabled(true);
		    	//CafePro.Mainpage.btnNewButton_1.setEnabled(true);
		    }
		});
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Posted Invoice List");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 368, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				CafePro.Mainpage.btnPurchaseReturn.setEnabled(true);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(780, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String stdate =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String eddate =((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				if(!stdate.equals("")){
					stdate = sdf.format(dateChooser.getDate());
				}
				if(!eddate.equals("")){
					eddate = sdf.format(dateChooser_1.getDate());
				}
		        get_po_disp( db_name,stdate,eddate,textField.getText(),cust_id);
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JButton btnExportAsCsv = new JButton("Export as CSV");
		btnExportAsCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
					FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\sd_post_list.csv");

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
				}
				catch( Exception e)
				{
					JOptionPane.showMessageDialog(frame,e.getMessage());
				}
			}
		});
		btnExportAsCsv.setBounds(623, 22, 125, 25);
		panel.add(btnExportAsCsv);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 617);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 88, 908, 432);
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
		    
		    model.addColumn("Invoice No."); 
		    model.addColumn("Invoice Amount");
		    model.addColumn("Customer Name");
		    model.addColumn("Invoice Date");
		    model.addColumn("Type");
		    model.addColumn("Posted Date");
		    model.addColumn("Remark");
		    model.addColumn("Referance");
		scrollPane.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(190);
			  if (i==1) column.setPreferredWidth(140);
			  if (i==2) column.setPreferredWidth(140);
			  if (i==3) column.setPreferredWidth(140);
			  if (i==4) column.setPreferredWidth(140);
			  if (i==5) column.setPreferredWidth(140);
			  if (i==6) column.setPreferredWidth(140);
			  if (i==7) column.setPreferredWidth(140);
			  if (i==8) column.setPreferredWidth(140);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 0, 908, 75);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String stdate =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String eddate =((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				if(!stdate.equals("")){
					stdate = sdf.format(dateChooser.getDate());
				}
				if(!eddate.equals("")){
					eddate = sdf.format(dateChooser_1.getDate());
				}
		        get_po_disp( db_name,stdate,eddate,textField.getText(),cust_id);
			}
		});
		btnSearch.setBounds(711, 0, 97, 63);
		panel_2.add(btnSearch);
		
		JLabel lblDateRange = new JLabel("DATE RANGE");
		lblDateRange.setBounds(12, 5, 97, 16);
		panel_2.add(lblDateRange);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(70, 23, 100, 16);
		dateChooser.setDate(new Date());
		panel_2.add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("dd/MM/yyyy");
		dateChooser_1.setBounds(274, 23, 100, 16);
		dateChooser_1.setDate(new Date());
		panel_2.add(dateChooser_1);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(216, 23, 56, 16);
		panel_2.add(lblTo);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(12, 26, 56, 16);
		panel_2.add(lblFrom);
		
		textField = new JTextField();
		textField.setBounds(12, 47, 668, 22);
		panel_2.add(textField);
		textField.setColumns(10);
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
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
				  String stdate =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String eddate =((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					if(!stdate.equals("")){
						stdate = sdf.format(dateChooser.getDate());
					}
					if(!eddate.equals("")){
						eddate = sdf.format(dateChooser_1.getDate());
					}
			        get_po_disp( db_name,stdate,eddate,textField.getText(),cust_id);
			  }
			});
		
		//Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		
		JLabel lblTotalAmount = new JLabel("TOTAL AMOUNT");
		lblTotalAmount.setBounds(75, 585, 109, 16);
		panel_1.add(lblTotalAmount);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(184, 582, 200, 22);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		//comboBox.addItem("DELIVERY");
		 
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 0;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	try{
            			
	 					new sales_distribution.sd_view(db_name, username, table.getModel().getValueAt(rows, 2).toString(), value, store, version,decimal,"0","POSTED");
                	}
                	catch (JSONException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        });
        String stdate =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		String eddate =((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
		if(!stdate.equals("")){
			stdate = sdf.format(dateChooser.getDate());
		}
		if(!eddate.equals("")){
			eddate = sdf.format(dateChooser_1.getDate());
		}
        
	}
	public void get_po_disp( String company, String date1,String date2, String searchtype,String custid)
	{
		String invoice_str="";
		if(!searchtype.equals(""))
		{
			invoice_str="and (invoice_num like '%"+searchtype+"%' or mr_sd_list_history.remark like '%"+searchtype+"%' or mr_sd_list_history.referance like '%"+searchtype+"%') ";
		}
		
		
		String date="and date between '"+date1+"' and '"+date2+"' ";
		
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_sd_list_history inner join "+company+".mr_sd_lines_history on mr_sd_lines_history.ref_inv_num=mr_sd_list_history.invoice_num where status='POSTED' and cust_id='"+custid+"' "+invoice_str+"  "+date+" group by mr_sd_list_history.invoice_num"; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			double ttl_amt=0;
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+company+".mr_sd_list_history inner join "+company+".mr_sd_lines_history on mr_sd_lines_history.ref_inv_num=mr_sd_list_history.invoice_num where status='POSTED' and cust_id='"+custid+"' "+invoice_str+"  "+date+" group by mr_sd_list_history.invoice_num"; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String invoice_num=resultSet1.getString("invoice_num");
	                String total_amt=resultSet1.getString("total_amt");
	                String cust_id=resultSet1.getString("cust_name");
	                String date3=resultSet1.getString("date");
	                String types=resultSet1.getString("type");
	                String modified_date=resultSet1.getString("modified_date");
	                String total_vat=resultSet1.getString("total_vat");
	                String remark=resultSet1.getString("remark");
	                String referance=resultSet1.getString("referance");
	                double ttl=Double.parseDouble(total_amt)+Double.parseDouble(total_vat);
	                BigDecimal bz = new BigDecimal(ttl);
		    		bz = bz.setScale(2, BigDecimal.ROUND_HALF_UP);
		    		ttl = bz.doubleValue();
		    		ttl_amt=ttl_amt+ttl;
	                model.addRow(new Object[]{invoice_num,ttl,cust_id,date3,types,modified_date,remark,referance});
				}
				BigDecimal bz = new BigDecimal(ttl_amt);
	    		bz = bz.setScale(2, BigDecimal.ROUND_HALF_UP);
	    		ttl_amt = bz.doubleValue();
	        	textField_5.setText(Double.toString(ttl_amt));
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Invoice List Found");
	        	for (int i = model.getRowCount() - 1; i >= 0; i--) {
	        	    model.removeRow(i);
	        	}
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}