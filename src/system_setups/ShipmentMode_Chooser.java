package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

public class ShipmentMode_Chooser {

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
					ShipmentMode_Chooser window = new ShipmentMode_Chooser("mr_pro", null);
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
	public ShipmentMode_Chooser(String company, String version) throws JSONException {
		initialize(company,  version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
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
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Refresh");
	    menuItem.setBorder(null);
	    menuItem.setIcon(new ImageIcon(refresh));
	    menuItem.setBackground(new Color(243, 229, 216));
	    menuItem.setBounds(442, 11, 80, 48);
	    panel.add(menuItem);
	    
	    JMenuItem menuItem_1 = new JMenuItem("Exit");
	    menuItem_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		frame.setVisible(false);
	    	}
	    });
	    menuItem_1.setBorder(null);
	    menuItem_1.setIcon(new ImageIcon(exit));
	    menuItem_1.setBackground(new Color(243, 229, 216));
	    menuItem_1.setBounds(382, 11, 60, 48);
	    panel.add(menuItem_1);
		
		JLabel lblShifts = new JLabel("Shipment Modes");
		lblShifts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShifts.setBounds(10, 11, 228, 44);
		panel.add(lblShifts);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 72, 684, 2);
		panel.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 73, 534, 289);
		panel.add(scrollPane);
		
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
		   
		};
		table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(20);
		    model.addColumn("PO Ship Code"); 
		    model.addColumn("PO Ship Descr"); 
		    model.addColumn("Status"); 
			scrollPane.setViewportView(table);
		    
			try {
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_shipment_mode ");
				while(resultSet.next())
				{
					 String code=resultSet.getString("shipment_code");
			            String desc=resultSet.getString("shipment_name");
			            String status=resultSet.getString("status");
			            model.addRow(new Object[]{code, desc,status});
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
	                	boolean sts=false;
	                	if(table.getModel().getValueAt(rows, 2).toString().equals("1"))
            			{
	                		sts=true;
            			}
	                	String value = table.getModel().getValueAt(rows, column).toString();
	                	system_setups.Shipment_Codes.textField.setText(value);
	                	system_setups.Shipment_Codes.textField_1.setText(table.getModel().getValueAt(rows, 1).toString());
	                	system_setups.Shipment_Codes.chckbxActiveForTransaction.setSelected(sts);
	                    frame.setVisible(false);
	                }
	            }
	        });
	}
}
