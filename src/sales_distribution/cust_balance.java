package sales_distribution;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

import javax.swing.JTextField;

public class cust_balance {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private JTextField textField_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cust_balance window = new cust_balance("mr_pro","","","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param company 
	 * @param host_name 
	 * @param page_type 
	 * @param username 
	 * @param store_name 
	 * @param total 
	 * @param old_recipt 
	 * @param sts 
	 * @throws JSONException 
	 */
	public cust_balance(String company, String username, String store_name, String version) throws JSONException {
		initialize(company,username,store_name,version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param company 
	 * @param host_name 
	 * @param page_type 
	 * @param username 
	 * @param store_name 
	 * @param sts 
	 * @throws JSONException 
	 */
	private void initialize(final String company, final String username,final String store_name,String version) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 551, 444);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 534, 363);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblShifts = new JLabel("Customers Balance Sheet");
		lblShifts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShifts.setBounds(10, 11, 228, 44);
		panel.add(lblShifts);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(463, 0, 71, 48);
		panel.add(mntmNewMenuItem);
		
		final Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JMenuItem menuItem_3 = new JMenuItem("Print");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					frame.setAlwaysOnTop(false);
					report_master.customer_list.report(con,company,username,"A4");
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
		menuItem_3.setBounds(371, 6, 80, 30);
		panel.add(menuItem_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 64, 534, 299);
		panel.add(scrollPane);
		
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
		    model.addColumn("Cust Code"); 
		    model.addColumn("Cust Name"); 
			model.addColumn("Phone1"); 
			model.addColumn("Credit Limit"); 
			model.addColumn("Credit Balance"); 
			model.addColumn("Balance Pay"); 
			scrollPane.setViewportView(table);
		    
		    //Image serch = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		    
		    JButton btnExportToCsv = new JButton("Export to CSV");
		    btnExportToCsv.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		try {
		    			LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyy");  
					    String formattedDate = myDateObj.format(myFormatObj); 
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			    		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
			    		FileWriter excel;
						excel = new FileWriter("C:\\MasterRetails\\Export\\Customer Balance sheet -"+formattedDate+".csv");
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
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		    			
		    	}
		    });
		    btnExportToCsv.setBounds(220, 11, 129, 25);
		    panel.add(btnExportToCsv);
		    
		    JLabel lblTotalPayableAmount = new JLabel("Total Receivable Amount : ");
		    lblTotalPayableAmount.setBounds(231, 368, 174, 16);
		    frame.getContentPane().add(lblTotalPayableAmount);
		    
		    textField_1 = new JTextField();
		    textField_1.setEditable(false);
		    textField_1.setBounds(405, 365, 116, 22);
		    frame.getContentPane().add(textField_1);
		    textField_1.setColumns(10);
		    
		    get_userdisp( company,"1");
			
	        
	        
	}
	private void  get_userdisp(String company, String sts)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement(); 
			
			String sts_term="";
			if(!sts.equals(""))
			{
				sts_term="where visible=1 and avail_credit<credit_limit";
			}
			else
			{
				sts_term="where visible=1 and avail_credit<credit_limit ";
			}
			String query="select * from "+company+".mr_customer_master "+sts_term+" order by cust_name asc ";
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			double amount_payable=0;
			while(resultSet.next())
			{
				String id=resultSet.getString("cust_code");
	            String name=resultSet.getString("cust_name");
	            String type=resultSet.getString("type");
	            if(type==null)
	            {
	            	type="NULL";
	            }
	            String ph=resultSet.getString("ph1");
	           String crdit_limit=resultSet.getString("credit_limit");
	           double bal= Float.parseFloat(resultSet.getString("avail_credit"))+Float.parseFloat(resultSet.getString("advance_amt"));
	            double balance=Double.parseDouble(crdit_limit)-bal;
	            int decimalPlaces = 2;
        		BigDecimal bd = new BigDecimal(bal);
        		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		bal = bd.doubleValue();
        		BigDecimal bc = new BigDecimal(balance);
        		bc = bc.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		balance = bc.doubleValue();
	            model.addRow(new Object[]{id, name,ph,crdit_limit,bal,balance});
	            amount_payable+=balance;
			}
			BigDecimal be = new BigDecimal(amount_payable);
    		be = be.setScale(2, BigDecimal.ROUND_HALF_UP);
    		amount_payable = be.doubleValue();
			textField_1.setText(Double.toString(amount_payable));
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
