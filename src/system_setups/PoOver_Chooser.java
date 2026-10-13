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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import javax.swing.JMenuItem;

public class PoOver_Chooser {

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
					PoOver_Chooser window = new PoOver_Chooser("mr_pro", null);
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
	public PoOver_Chooser(String company, String version) throws JSONException {
		initialize(company, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @throws JSONException 
	 */
	private void initialize(final String company, String version) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 550, 400);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 534, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblShifts = new JLabel("OH Charges");
		lblShifts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShifts.setBounds(10, 11, 228, 44);
		panel.add(lblShifts);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 72, 684, 2);
		panel.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 72, 534, 290);
		panel.add(scrollPane);
		
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
		    model.addColumn("OHCharge Code"); 
		    model.addColumn("OHCharge Name"); 
		    model.addColumn("OHCharge Type"); 
		    model.addColumn("IncludeinPO Cost"); 
		    scrollPane.setViewportView(table);
		    
		    try {
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_po_overhead ");
				while(resultSet.next())
				{
					String id=resultSet.getString("oh_charge_code");
		            String name=resultSet.getString("oh_charge_name");
		            String type=resultSet.getString("oh_type");
		            String vendor_po=resultSet.getString("include_vendor_po_amt");
		            model.addRow(new Object[]{id, name,type,new Boolean(vendor_po)});
				}
				con.close();
			} catch (Exception e) {
				System.err.println(e);
			}
	        table.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent mouseEvent) {
	                JTable table =(JTable) mouseEvent.getSource();
	                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
	                	int column = 0;
	                	int rows = table.getSelectedRow();
	                	String value = table.getModel().getValueAt(rows, column).toString();
	                	system_setups.Po_Overhead.get_po_oh_disp( company, value);
	                    frame.setVisible(false);
	                }
	            }
	        });   
			    
		    
		    Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		    JMenuItem menuItem = new JMenuItem("Exit");
		    menuItem.setBorder(null);
		    menuItem.setIcon(new ImageIcon(exit));
		    menuItem.setBackground(new Color(243, 229, 216));
		    menuItem.setBounds(439, 11, 83, 48);
		    panel.add(menuItem);
	}
}
