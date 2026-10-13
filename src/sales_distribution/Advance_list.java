package sales_distribution;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

public class Advance_list {

	private JFrame frame;
	public static JTable table;
	private DefaultTableModel model;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Advance_list window = new Advance_list("mr_pro","","","","");
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
	public Advance_list(String company, String username, String store_name, String version, String cust_id) throws JSONException {
		initialize(company,username,store_name,version, cust_id);
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
	private void initialize(final String company, final String username,final String store_name,final String version, final String cust_id) throws JSONException {
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
		panel.setBounds(0, 0, 534, 406);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblShifts = new JLabel("Advance Invoice List");
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
		mntmNewMenuItem.setBounds(348, 7, 71, 48);
		panel.add(mntmNewMenuItem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 64, 534, 331);
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
		    model.addColumn("Sl. No"); 
		    model.addColumn("Advance List"); 
			model.addColumn("Amount"); 
			model.addColumn("Balance Amount"); 
			model.addColumn("Applied Amount"); 
			scrollPane.setViewportView(table);
			
			Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
			JMenuItem menuItem = new JMenuItem("Save");
			menuItem.setBorder(null);
			menuItem.setBackground(new Color(243, 229, 216));
			menuItem.setBounds(429, 7, 135, 48);
			menuItem.setIcon(new ImageIcon(save));
			panel.add(menuItem);
		    
			table.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent mouseEvent) {
	                JTable table =(JTable) mouseEvent.getSource();
	                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
	                	int column = 1;
	                	int rows = table.getSelectedRow();
	                	String value = table.getModel().getValueAt(rows, column).toString();
	                	try{
	            			
	                		new Adv_inv_list(company, username,  store_name,  version,  cust_id,rows,value);
	                	}
	                	catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }
	                
	            }
	        });
		    
		    get_list( company,cust_id);
			
	        
	        
	}
	private void  get_list(String company, String cust_id)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement(); 
			String query="select * from "+company+".mr_sd_customer_payment where ref_cust_id='"+cust_id+"' and type='AD' and bal_amt!=0 ";
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			double amount_payable=0;
			double ttl_amount=0;
			while(resultSet.next())
			{
				model.addRow(new Object[]{model.getRowCount()+1,resultSet.getString("ref_tran_num"),resultSet.getString("invo_amt"),resultSet.getString("bal_amt")});
				amount_payable+=Double.parseDouble(resultSet.getString("bal_amt"));
				ttl_amount+=Double.parseDouble(resultSet.getString("invo_amt"));
			}
			int decimalPlaces = 2;
    		BigDecimal bd = new BigDecimal(amount_payable);
    		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
    		amount_payable = bd.doubleValue();
    		BigDecimal bc = new BigDecimal(ttl_amount);
    		bc = bc.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
    		ttl_amount = bc.doubleValue();
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
