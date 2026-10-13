package CafePro;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ScrollPaneConstants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class login_controls {

	public static JFrame frame;
	private static DefaultTableModel model;
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					login_controls window = new login_controls("mr_pro","","");
					login_controls.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param store_name 
	 * @param con 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	public login_controls(String db_name,String version, String language) throws JSONException, SQLException {
		initialize(db_name,version,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 * @param store_name 
	 * @param decimal 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String db_name,String version, String language) throws JSONException, SQLException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 639, 734);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				frame.setVisible(false);
			}
		});
		frame.getContentPane().setLayout(null);
		
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		//frame.setUndecorated(true);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JLabel lblItemMasterList = new JLabel("User Login List");
		if(language.equals("Arabic"))
		{
			lblItemMasterList.setText("ﬁ«∆„…  ”ÃÌ· œŒÊ· «·„” Œœ„");
		}
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(12, 0, 260, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(553, 0, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 627);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 13, 611, 563);
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
		    	model.addColumn("«·—ﬁ„ «· ”·”·Ì"); 
			    model.addColumn("«”„ «·„” Œœ„"); 
			    model.addColumn("«”„ «·‰Ÿ«„"); 
			    model.addColumn("Êﬁ   ”ÃÌ· «·œŒÊ·"); 
			}
		    else
		    {
		    	
		    }
		    
		scrollPane.setViewportView(table);
		
		int columns=model.getColumnCount();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 JTableHeader header=table.getTableHeader();
		  
		    header.setPreferredSize(new Dimension(0,50));
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(40);
			  if (i==1) column.setPreferredWidth(150);
			  if (i==2) column.setPreferredWidth(200);
			  if (i==3) column.setPreferredWidth(200);
			}
		
		
		 
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
            	try {
	            	JTable table =(JTable) mouseEvent.getSource();
	                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
	                	int rows = table.getSelectedRow();
	                	int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Logout This User Â·  —Ìœ  ”ÃÌ· Œ—ÊÃ Â–« «·„” Œœ„","Warning",JOptionPane.YES_NO_OPTION);
	                	// 0=yes, 1=no, 2=cancel
				        if(cofrm_bx==0)
				        {
				        	String user = table.getModel().getValueAt(rows, 1).toString();
		                	String sys_name = table.getModel().getValueAt(rows, 2).toString();
		                	Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							Statement statement1=con.createStatement();  
							ResultSet resultSet = statement.executeQuery("select * from "+db_name+".mr_loggin_controls where username='"+user+"' and "+db_name+".mr_loggin_controls.device_name='"+sys_name+"' ");
							if(resultSet.next())
							{
								LocalDateTime myDateObj = LocalDateTime.now();  
							    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
							    final String formattedDate = myDateObj.format(myFormatObj);
								String del="update "+db_name+".mr_loggin_controls set status='1',modified_date='"+formattedDate+"' where username='"+user+"' and status='0' and  "+db_name+".mr_loggin_controls.device_name='"+sys_name+"' ";
								statement1.executeUpdate(del);
								 get_itemdisp( db_name,  con);
							}
							con.close();
				        }
	                }
            	} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        Connection con=Dbsetup.dbconn.getConnection();
        get_itemdisp( db_name,  con);
        
	}
	
	
	private void  get_itemdisp( String company,  Connection con)
	{
		try
		{
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			Statement statement=con.createStatement();
			String query1="SELECT * FROM "+company+".mr_loggin_controls where status='0'"; 
			ResultSet resultSet1 = statement.executeQuery(query1);
			while(resultSet1.next())
			{
				model.addRow(new Object[]{model.getRowCount()+1,resultSet1.getString("username"),resultSet1.getString("device_name"),resultSet1.getString("created_date") });
			}
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
