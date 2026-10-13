package system_setups;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import accounts_master.SelectAccount;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Paycodes {

	private JFrame frame;
	private JTable table;
	public static DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Paycodes window = new Paycodes("mr_pro", "S01", null,"");
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
	public Paycodes(String company, String store_code,String version, String language) throws JSONException {
		initialize(company,store_code, version,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 */
	private void initialize(final String company, final String store_code,final String version, final String language) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		Image bill = new ImageIcon(this.getClass().getResource("/Billing-icon.png")).getImage();
		JLabel lblCompanySetup = new JLabel("Paycodes");
		lblCompanySetup.setBounds(21, 11, 228, 44);
		lblCompanySetup.setIcon(new ImageIcon(bill));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 66, 685, 356);
		frame.getContentPane().add(scrollPane);
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable (int row,int column){
				
				if (column == 3)
				{
					return true;
				}
				if(row > 3)
				{
					return true;
				}
				if(column == 6)
				{
					return false;
				}
				return false;
			}
		   public Class<?> getColumnClass (int column){
			   
			   switch (column){
			   
			   case 3: 
				   return Boolean.class;
			   
			   
			   default: 
				   return String.class;
			   }
			   
		   }
		};
		table = new JTable(model);
		
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(20);
			table.getTableHeader().setPreferredSize(new Dimension(40,40));
		    model.addColumn("Sl No:"); 
		    model.addColumn("Paycode"); 
			model.addColumn("Description"); 
			model.addColumn("<html>Open<br>Draw</html>"); 
			model.addColumn("EX Rate"); 
			model.addColumn(""); 
			model.addColumn("G/L Account"); 
			model.addRow(new Object[]{"1","CASH","CASH",new Boolean(false),"1","","0101001"});
			model.addRow(new Object[]{"2","A/R","A/R",new Boolean(false),"1","","0101001"});
			model.addRow(new Object[]{"3","VISA","VISA",new Boolean(false),"1","","0101001"});
			model.addRow(new Object[]{"4","MASTER","MASTER",new Boolean(false),"1","","0101001"});
			model.addRow(new Object[]{"5","OMANI RIYAL","OMANI RIYAL",new Boolean(false),"9.95","","0101001"});
			model.addRow(new Object[]{"6","SAUDI RIYAL","SAUDI RIYAL",new Boolean(false),"0.9","","0101001"});
			model.addRow(new Object[]{"7","US $","US $",new Boolean(false),"3.65","","0101001"});
			model.addRow(new Object[]{"8","","",new Boolean(false),"","",""});
			model.addRow(new Object[]{"9","","",new Boolean(false),"","",""});
			model.addRow(new Object[]{"10","","",new Boolean(false),"","",""});
			model.addRow(new Object[]{"11","","",new Boolean(false),"","",""});
			model.addRow(new Object[]{"12","","",new Boolean(false),"","",""});
			model.addRow(new Object[]{"13","","",new Boolean(false),"","",""});
			model.addRow(new Object[]{"14","","",new Boolean(false),"","",""});
			model.addRow(new Object[]{"15","","",new Boolean(false),"","",""});
			
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			int a=table.getColumnCount();
			for(int i=0;i<a;i++)
			{
				TableColumn column=table.getColumnModel().getColumn(i);
				if(i==0){
					column.setPreferredWidth(35);
				}
				if(i==1){
					column.setPreferredWidth(200);
				}
				if(i==2){
					column.setPreferredWidth(180);
				}
				if(i==3){
					column.setPreferredWidth(50);
				}
				if(i==4){
					column.setPreferredWidth(60);
				}
				if(i==5){
					column.setPreferredWidth(20);
				}
				if(i==6){
					column.setPreferredWidth(135);
				}
			}
			table.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent mouseEvent) {
	                JTable table =(JTable) mouseEvent.getSource();
	                if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1) {
	                	if(table.getSelectedColumn()==5)
	                	{
	                		try {
								new SelectAccount(company, "store_paycode",table.getSelectedRow(), version,"", "", language);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                	}
	                }
	            }
	        });
		    scrollPane.setViewportView(table);
		    
		    Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
			Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
			Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		    
		    JMenuItem menuItem = new JMenuItem("Exit");
		    menuItem.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		frame.setVisible(false);
		    	}
		    });
		    menuItem.setBorder(null);
		    menuItem.setIcon(new ImageIcon(exit));
		    menuItem.setBackground(new Color(243, 229, 216));
		    menuItem.setBounds(472, 7, 60, 48);
		    frame.getContentPane().add(menuItem);
		    
		    JMenuItem menuItem_1 = new JMenuItem("Refresh");
		    menuItem_1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		Paycodes.store_paycodes_disp(company, store_code);
		    	}
		    });
		    menuItem_1.setBorder(null);
		    menuItem_1.setIcon(new ImageIcon(refresh));
		    menuItem_1.setBackground(new Color(243, 229, 216));
		    menuItem_1.setBounds(533, 7, 80, 48);
		    frame.getContentPane().add(menuItem_1);
		    
		    JMenuItem menuItem_2 = new JMenuItem("Save");
		    menuItem_2.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					try {
						statement = con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store_paycodes where ref_store_id='"+store_code+"' ");
						if(resultSet.next())
						{
							String delet="delete from "+company+".mr_store_paycodes where ref_store_id='"+store_code+"' ";
							statement.executeUpdate(delet);
							
							for(int i=0;i<model.getRowCount();i++)
							{
								String query="insert into "+company+".mr_store_paycodes (ref_store_id,paycode,pay_desc,draw_open,ex_rate,gl_account,created_date) values ('"+store_code+"','"+model.getValueAt(i, 1)+"','"+model.getValueAt(i, 2)+"','"+model.getValueAt(i, 3)+"','"+model.getValueAt(i, 4)+"','"+model.getValueAt(i, 6)+"','"+formattedDate+"')";
								statement.executeUpdate(query);
							}
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
						else
						{
							for(int i=0;i<model.getRowCount();i++)
							{
								String query="insert into "+company+".mr_store_paycodes (ref_store_id,paycode,pay_desc,draw_open,ex_rate,gl_account,created_date) values ('"+store_code+"','"+model.getValueAt(i, 1)+"','"+model.getValueAt(i, 2)+"','"+model.getValueAt(i, 3)+"','"+model.getValueAt(i, 4)+"','"+model.getValueAt(i, 6)+"','"+formattedDate+"')";
								statement.executeUpdate(query);
							}
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
		    	}
		    });
		    menuItem_2.setBorder(null);
		    menuItem_2.setIcon(new ImageIcon(save));
		    menuItem_2.setBackground(new Color(243, 229, 216));
		    menuItem_2.setBounds(612, 7, 70, 48);
		    frame.getContentPane().add(menuItem_2);
		//frame.getContentPane().add(table);
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		store_paycodes_disp(company, store_code);
	}
	static void store_paycodes_disp(String company, String store_code)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_store_paycodes where ref_store_id='"+store_code+"' ");
			if(resultSet1.next())
			{
				int id=0;
				int rowCount = model.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
					model.removeRow(i);
				}
				ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store_paycodes where ref_store_id='"+store_code+"'");
				while(resultSet.next())
				{
					id=id+1;
	                String paycode=resultSet.getString("paycode");
	                String pay_desc=resultSet.getString("pay_desc");
	                String draw_open=resultSet.getString("draw_open");
	                String ex_rate=resultSet.getString("ex_rate");
	                String gl_account=resultSet.getString("gl_account");
	                model.addRow(new Object[]{id, paycode, pay_desc,new Boolean(draw_open),ex_rate,"",gl_account});
				}	
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
