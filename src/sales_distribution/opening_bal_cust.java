package sales_distribution;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class opening_bal_cust {

	static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	private JTextField textField;
	private static JButton btnProceed;
	private static DecimalFormat df2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					opening_bal_cust window = new opening_bal_cust("mr_pro","admin","S01", null,"2");
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
	public opening_bal_cust(String db_name, String username,String store,String version,String decimal) throws JSONException {
		initialize(db_name,username,store, version,decimal);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param decimal 
	 * @param page 
	 */
	private void initialize(final String db_name,final String username,final String store,final String version, final String decimal) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 789, 675);
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
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	    final String formattedDate = myDateObj.format(myFormatObj); 
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 771, 73);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Customer Opening Balance");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
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
		menuItem.setBounds(538, 13, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name, "");
				textField.setText("");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(598, 13, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		btnProceed = new JButton("Post Opening Balance");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setAlwaysOnTop(true);
				int row=table.getSelectedRow();
				if(row>-1)
				{
					int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Post Opening Balance for Customer ","Warning",JOptionPane.YES_NO_OPTION);
			        if(cofrm_bx==0)
			        {
			        	LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj); 
					    frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			        	Connection con=Dbsetup.dbconn.getConnection();
						Statement statement;
						Statement statement1;
						try {
							statement = con.createStatement();
							statement1 = con.createStatement();
				        	ResultSet resultSet = statement.executeQuery("select * from "+db_name+".mr_customer_master where cust_code='"+table.getValueAt(row, 0).toString().trim()+"' ");
							if(resultSet.next())
							{
								String cust_code=resultSet.getString("cust_code");
				                String credit_limit=resultSet.getString("credit_limit");
				                String avail_credit=resultSet.getString("avail_credit");
				                String open_bal=resultSet.getString("open_bal");
				                double new_bal=Double.parseDouble(credit_limit)-(Double.parseDouble(credit_limit)-(Double.parseDouble(avail_credit))+Double.parseDouble(open_bal));
				                String query2="update "+db_name+".mr_customer_master set avail_credit='"+new_bal+"',open_bal_status='1' where cust_code='"+table.getValueAt(row, 0).toString().trim()+"' ";
								statement1.executeUpdate(query2);
					        	String query="insert into "+db_name+".mr_sd_customer_payment(`ref_tran_num`, `ref_cust_id`, `invo_num`, `invo_date`, `invo_amt`, `paid_amt`, `bal_amt`, `store_id`, `created_date`, `modified_date`, `status`,type)values('OPENING BALANCE','"+cust_code+"','OPENING BALANCE','"+formattedDate+"','"+open_bal+"','0','"+open_bal+"','"+store+"','"+formattedDate+"','"+formattedDate+"','0','IN') ";
								statement1.executeUpdate(query);
								String query1="insert into "+db_name+".mr_sd_customer_payment_details(`ref_tran_num`, `seq_num`, `ref_cust_id`, `cr_amt`, `dr_amt`, `disc_amt`, `issue_date`, `store_id`, `username`, `created_date`, `modified_date`,`status`)values('OPENING BALANCE','OPENING BALANCE','"+cust_code+"','0','"+open_bal+"','0','"+formattedDate+"','"+store+"','"+username+"','"+formattedDate+"','"+formattedDate+"','0') ";
								statement1.executeUpdate(query1);
							}
							JOptionPane.showMessageDialog(frame, "Posted Succesfully");
							frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
							get_po_disp( db_name, "");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						} 
			        }
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Select A Row from Table");
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		btnProceed.setBounds(345, 22, 181, 25);
		panel.add(btnProceed);
		
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					
					Connection con=Dbsetup.dbconn.getConnection();
					con.setAutoCommit(false);
					PreparedStatement ps = con.prepareStatement("update "+db_name+".mr_customer_master set open_bal=?,avail_credit=?,modified_date=? where cust_code=? ");
					for(int i=0;i<table.getRowCount();i++)
					{
						String crdit=table.getValueAt(i, 2).toString().trim();
						String old_bal=table.getValueAt(i, 4).toString().trim();
						String nw_bal=table.getValueAt(i, 5).toString().trim();
						if(crdit.equals(""))
						{
							crdit="0";
						}
						if(nw_bal.equals(""))
						{
							nw_bal="0";
						}
						double availcredit=Double.parseDouble(crdit)-Double.parseDouble(old_bal);//-(Double.parseDouble(nw_bal)+Double.parseDouble(old_bal));
						ps.setString(1,nw_bal);
						ps.setString(2,Double.toString(availcredit));
						ps.setString(3,formattedDate);
						ps.setString(4,table.getValueAt(i, 0).toString().trim());
						ps.addBatch();
						ps.clearParameters();
					}
					ps.executeBatch();
					con.commit();
					frame.setCursor(Cursor.getDefaultCursor());
					JOptionPane.showMessageDialog(frame, "Saved Successfully");
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
		        	    model.removeRow(i);
		        	}
					textField.setText("");
					get_po_disp( db_name, "");
				} catch (SQLException e) {
					frame.setAlwaysOnTop(false);
					frame.setCursor(Cursor.getDefaultCursor());
					JOptionPane.showMessageDialog( frame, e.getMessage(),"SQLException error", JOptionPane.ERROR_MESSAGE );
					//frame.setAlwaysOnTop(true);
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(679, 13, 80, 48);
		menuItem_2.setIcon(new ImageIcon(save));
		panel.add(menuItem_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 77, 771, 556);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 88, 747, 468);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel_1.add(scrollPane);

		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		      if( column==5)
		      {
		    	  return true;
		      }
				
		       return false;
		    }
		};
		    table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
		    table.setRowHeight(20);
		    
		    model.addColumn("Customer Code"); 
		    model.addColumn("Customer Name");
		    model.addColumn("Credit Limit");
		    model.addColumn("Avail Credit");
		    model.addColumn("Current Balance");
		    model.addColumn("Opening Balance");
		scrollPane.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(100);
			  if (i==1) column.setPreferredWidth(210);
			  if (i==2) column.setPreferredWidth(100);
			  if (i==3) column.setPreferredWidth(100);
			  if (i==4) column.setPreferredWidth(100);
			  if (i==5) column.setPreferredWidth(100);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 747, 52);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
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
				  get_po_disp( db_name,textField.getText());
			  }
			});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					get_po_disp( db_name,textField.getText());
				}
			}
		});
		textField.setBounds(12, 13, 493, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( db_name,textField.getText());
			}
		});
		btnSearch.setBounds(518, 13, 97, 25);
		panel_2.add(btnSearch);
		
        get_po_disp( db_name, "");
	}
	static void  get_po_disp( String company, String search)
	{
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and (cust_code LIKE '%"+search+"%' OR cust_name LIKE '%"+search+"%') ";
		}
		
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_customer_master where visible = '1' and open_bal_status='0' "+serch_str+" ";
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				df2=new DecimalFormat("0.00");
				String query1="SELECT * FROM "+company+".mr_customer_master where visible = '1' and open_bal_status='0' "+serch_str+" "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String cust_code=resultSet1.getString("cust_code");
	                String cust_name=resultSet1.getString("cust_name");
	                String credit_limit=resultSet1.getString("credit_limit");
	                String avail_credit=resultSet1.getString("avail_credit");
	                String open_bal=resultSet1.getString("open_bal");
	                double new_bal=Double.parseDouble(credit_limit)-Double.parseDouble(avail_credit);
	                model.addRow(new Object[]{cust_code,cust_name,df2.format(Double.parseDouble(credit_limit)),df2.format(Double.parseDouble(avail_credit)),df2.format(new_bal),open_bal});
				}
	        	btnProceed.setEnabled(true);
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Customer List Found");
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