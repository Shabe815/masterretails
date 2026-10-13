package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

public class Select_Tax {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Select_Tax window = new Select_Tax("mr_pro","", null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param pagetype 
	 * @throws JSONException 
	 */
	public Select_Tax(String company, String page_type,String version) throws JSONException {
		initialize(company,page_type, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws JSONException 
	 */
	private void initialize(final String company,final String page_type,String version) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		Image percentage=new ImageIcon(this.getClass().getResource("/percentage.png")).getImage();
		JLabel lblCompanySetup = new JLabel("Tax List");
		lblCompanySetup.setBounds(21, 11, 228, 44);
		lblCompanySetup.setIcon(new ImageIcon(percentage));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 66, 694, 376);
		frame.getContentPane().add(scrollPane);
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
            public boolean isCellEditable (int row,int column){
				return false;
			}
		   
		};
		table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(20);
		    model.addColumn("Tax Code"); 
		    model.addColumn("Tax Description"); 
		    model.addColumn("Tax Rate"); 
		    model.addColumn("Tax Account"); 
			
			scrollPane.setViewportView(table);
			try {
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_tax ");
				while(resultSet.next())
				{
					String id=resultSet.getString("tax_code");
		            String name=resultSet.getString("tax_desc");
		            String tax_rate=resultSet.getString("tax_rate");
		            String account_code=resultSet.getString("account_code");
		            model.addRow(new Object[]{id, name,tax_rate,account_code});
				}	
				con.close();
			} catch (Exception e) {
				System.err.println(e);
			}
	        
	        table.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent mouseEvent) {
	                JTable table =(JTable) mouseEvent.getSource();
	                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
	                	int rows = table.getSelectedRow();
	                	if(page_type.equals("inventory_cntrl"))
	                	{
	                		inventory_master.Inventory_control.textField_9.setText(table.getModel().getValueAt(rows, 0).toString());
	                	}
	                	else
	                	{
	                		system_setups.Item_Tax.get_tax_val(company, table.getModel().getValueAt(rows, 0).toString());
	                	}
	                	
	                    frame.setVisible(false);
	                }
	            }
	        });
		    
		    Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
			Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
			
		    JMenuItem menuItem = new JMenuItem("Refresh");
		    menuItem.setBorder(null);
		    menuItem.setBackground(new Color(243, 229, 216));
		    menuItem.setIcon(new ImageIcon(refresh));
		    menuItem.setBounds(602, 11, 80, 48);
		    frame.getContentPane().add(menuItem);
		    
		    JMenuItem menuItem_1 = new JMenuItem("Exit");
		    menuItem_1.setBorder(null);
		    menuItem_1.setBackground(new Color(243, 229, 216));
		    menuItem_1.setIcon(new ImageIcon(exit));
		    menuItem_1.setBounds(542, 11, 60, 48);
		    frame.getContentPane().add(menuItem_1);
		    
		//frame.getContentPane().add(table);
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
