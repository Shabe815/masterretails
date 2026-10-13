package accounts_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import com.toedter.calendar.JDateChooser;

public class Profit_loss {

	static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Profit_loss window = new Profit_loss("mr_pro","admin","S01", null,"2","");
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
	public Profit_loss(String db_name, String username,String store,String version,String decimal,String language) throws JSONException {
		initialize(db_name,username,store, version,decimal, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param decimal 
	 * @param page 
	 */
	private void initialize(final String db_name,final String username,final String store,final String version, final String decimal,String language) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 696);
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
		
		
		JLabel lblItemMasterList = new JLabel("Profit / Loss Account");
		if(language.equals("Arabic"))
		{
			lblItemMasterList.setText("Õ”«» «·—»Õ Ê«·Œ”«—…");
		}
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 368, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
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
		if(language.equals("Arabic"))
		{
			menuItem_1.setText("Ì‰⁄‘");
		}
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowCount = model.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
					model.removeRow(i);
				}
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		final Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JMenuItem menuItem_2 = new JMenuItem("Print");
		if(language.equals("Arabic"))
		{
			menuItem_2.setText("„ÿ»⁄…");
		}
		menuItem_2.setBorder(null);
		menuItem_2.setVisible(false);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(708, 7, 60, 48);
		menuItem_2.setIcon(new ImageIcon(printer));
		panel.add(menuItem_2);
		
		JButton btnExportCsv = new JButton("Export CSV");
		if(language.equals("Arabic"))
		{
			btnExportCsv.setText(" ’œÌ— CSV");
		}
		btnExportCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
					FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\Profit_loss.csv");

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
				        JOptionPane.showMessageDialog(frame, "Export Completed «ﬂ „· «· ’œÌ—");
				}
				catch(Exception e){
					System.err.println(e);
				}
			}
		});
		btnExportCsv.setBounds(552, 22, 126, 25);
		panel.add(btnExportCsv);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 589);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 76, 908, 500);
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
		    if(language.equals("Arabic"))
			{
		    	model.addColumn("—„“ «·Õ”«»"); 
			    model.addColumn("Ê’› «·Õ”«»");
			    model.addColumn("Ìﬂ »");
			    model.addColumn("›∆…");
			    model.addColumn("≈Ã„«·Ì «·œÌ‰");
			    model.addColumn("≈Ã„«·Ì «·«∆ „«‰");
			    model.addColumn("—’Ìœ „œÌ‰");
			    model.addColumn("—’Ìœ «·«∆ „«‰");
			}
		    else
		    {
		    	model.addColumn("Acc Code"); 
			    model.addColumn("Acc Desc");
			    model.addColumn("Type");
			    model.addColumn("Category");
			    model.addColumn("Total Dr");
			    model.addColumn("Total Cr");
			    model.addColumn("Dr Balance");
			    model.addColumn("Cr Balance");
		    }
		scrollPane.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(100);
			  if (i==1) column.setPreferredWidth(220);
			  if (i==2) column.setPreferredWidth(100);
			  if (i==3) column.setPreferredWidth(180);
			  if (i==4) column.setPreferredWidth(120);
			  if (i==5) column.setPreferredWidth(120);
			  if (i==6) column.setPreferredWidth(120);
			  if (i==7) column.setPreferredWidth(120);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 0, 908, 63);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnSearch = new JButton("Search");
		if(language.equals("Arabic"))
		{
			btnSearch.setText("Ì»ÕÀ");
		}
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
				get_po_disp( db_name,stdate,eddate);
		        
			}
		});
		btnSearch.setBounds(799, 13, 97, 40);
		panel_2.add(btnSearch);
		
		JLabel lblDateRange = new JLabel("DATE RANGE");
		if(language.equals("Arabic"))
		{
			lblDateRange.setText("‰ÿ«ﬁ «· «—ÌŒ");
		}
		lblDateRange.setBounds(12, 15, 97, 22);
		panel_2.add(lblDateRange);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(158, 15, 100, 22);
		dateChooser.setDate(new Date());
		panel_2.add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("dd/MM/yyyy");
		dateChooser_1.setBounds(362, 15, 100, 22);
		dateChooser_1.setDate(new Date());
		panel_2.add(dateChooser_1);
		
		JLabel lblTo = new JLabel("To");
		if(language.equals("Arabic"))
		{
			lblTo.setText("·");
		}
		lblTo.setBounds(304, 15, 56, 22);
		panel_2.add(lblTo);
		
		JLabel lblFrom = new JLabel("From");
		if(language.equals("Arabic"))
		{
			lblFrom.setText("„‰");
		}
		lblFrom.setBounds(100, 15, 56, 22);
		panel_2.add(lblFrom);
		
		//Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		//comboBox.addItem("DELIVERY");
		 
//        table.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent mouseEvent) {
//                JTable table =(JTable) mouseEvent.getSource();
//                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
//                	int column = 0;
//                	int rows = table.getSelectedRow();
//                	String value = table.getModel().getValueAt(rows, column).toString();
//                	try{
//            			
//	 					new sales_distribution.sd_view(db_name, username, table.getModel().getValueAt(rows, 2).toString(), value, store, version,decimal);
//                	}
//                	catch (JSONException | SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//                }
//            }
//        });
        String stdate =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		String eddate =((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
		if(!stdate.equals("")){
			stdate = sdf.format(dateChooser.getDate());
		}
		if(!eddate.equals("")){
			eddate = sdf.format(dateChooser_1.getDate());
		}
        
	}
	public void get_po_disp( String company, String date1,String date2)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();
			Statement statement1=con.createStatement();
			Statement statement2=con.createStatement();
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			double sum_dr_balance=0;
			double sum_cr_balance=0;
			String query="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_type='INCOME' or acc_type='EXPENSE' ";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				double dr=0;
				double dr_balance=0;
				double cr=0;
				double cr_balance=0;
				String acc_code=resultSet.getString("acc_code");
				String acc_type=resultSet.getString("acc_type");
				String acc_category=resultSet.getString("acc_category");
				String ac_code="";
				String ac_desc="";
				String query2="SELECT * FROM "+company+".mr_jv_details  where ac_code='"+acc_code+"' and  cheque_date between '"+date1+"' and '"+date2+"' and ac_code='"+acc_code+"' and status!='VOID' "; 
				ResultSet resultSet2 = statement2.executeQuery(query2);
				if(resultSet2.next())
				{
					String query1="SELECT * FROM "+company+".mr_jv_details  where ac_code='"+acc_code+"' and  cheque_date between '"+date1+"' and '"+date2+"' and ac_code='"+acc_code+"' and status!='VOID' "; 
					ResultSet resultSet1 = statement1.executeQuery(query1);
					while(resultSet1.next())
					{
						ac_code=resultSet1.getString("ac_code");
		                ac_desc=resultSet1.getString("ac_desc");
		                String dr_amt=resultSet1.getString("dr_amt");
		                String cr_amt=resultSet1.getString("cr_amt");
		                dr=dr+Double.parseDouble(dr_amt);
		                cr=cr+Double.parseDouble(cr_amt);
					}
					BigDecimal bx = new BigDecimal(dr);
		    		bx = bx.setScale(2, BigDecimal.ROUND_HALF_UP);
		    		dr = bx.doubleValue();
		    		BigDecimal bz = new BigDecimal(cr);
		    		bz = bz.setScale(2, BigDecimal.ROUND_HALF_UP);
		    		cr = bz.doubleValue();
		    		if(acc_type.equals("ASSET"))
		    		{
		    			dr_balance=dr-cr;
		    		}
		    		if(acc_type.equals("LIABILITY"))
		    		{
		    			cr_balance=cr-dr;
		    		}
		    		if(acc_type.equals("INCOME"))
		    		{
		    			cr_balance=cr-dr;
		    		}
		    		if(acc_type.equals("EXPENSE"))
		    		{
		    			dr_balance=dr-cr;
		    		}
		    		
		    		BigDecimal ba = new BigDecimal(dr_balance);
		    		ba = ba.setScale(2, BigDecimal.ROUND_HALF_UP);
		    		dr_balance = ba.doubleValue();
		    		BigDecimal bs = new BigDecimal(cr_balance);
		    		bs = bs.setScale(2, BigDecimal.ROUND_HALF_UP);
		    		cr_balance = bs.doubleValue();
		    		
		    		model.addRow(new Object[]{ac_code,ac_desc,acc_type,acc_category,dr,cr,dr_balance,cr_balance});
		    		sum_dr_balance=sum_dr_balance+dr_balance;
		    		sum_cr_balance=sum_cr_balance+cr_balance;
				}
			}
			BigDecimal bx = new BigDecimal(sum_dr_balance);
    		bx = bx.setScale(2, BigDecimal.ROUND_HALF_UP);
    		sum_dr_balance = bx.doubleValue();
    		BigDecimal bz = new BigDecimal(sum_cr_balance);
    		bz = bz.setScale(2, BigDecimal.ROUND_HALF_UP);
    		sum_cr_balance = bz.doubleValue();
    		
    		
    		
			model.addRow(new Object[]{"","","","","","TOTAL",sum_dr_balance,sum_cr_balance});
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}