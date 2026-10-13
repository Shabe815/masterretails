package system_setups;

import inventory_master.InventoryAdjustmentReport;
import inventory_master.Offer_zone;
import inventory_master.create_item_utility;

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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

public class department_list {

	private JFrame frame;
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					department_list window = new department_list("mr_pro","","");
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
	public department_list(String company,String version,String type) throws JSONException {
		initialize(company,version,type);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param type 
	 */
	private void initialize(final String company,String version, final String Reporttype) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
	    frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 492, 466);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		
		JLabel lblCompanySetup = new JLabel("Department List");
		lblCompanySetup.setBounds(21, 11, 228, 44);
		Image shop=new ImageIcon(this.getClass().getResource("/control-panel.png")).getImage();
		lblCompanySetup.setIcon(new ImageIcon(shop));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(400, 7, 83, 48);
		frame.getContentPane().add(mntmNewMenuItem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 68, 476, 347);
		scrollPane.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(scrollPane);
		
		
		DefaultTableModel model = new DefaultTableModel(){

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
		model.addColumn("Dept Code"); 
		model.addColumn("Dept Name"); 
		scrollPane.setViewportView(table);
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_department");
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			while(resultSet.next())
			{
				
				String dept_code=resultSet.getString("dept_code");
	            String dept_name=resultSet.getString("dept_name");
	            model.addRow(new Object[]{dept_code, dept_name});
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
        
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 1;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	if(Reporttype.equals("deptwise"))
                	{
                		History_Reports.sales_summary.textField_2.setText(value);
                	}
                	else if(Reporttype.equals("invoicehistory"))
                	{
                		History_Reports.Invoice_history.textField_1.setText(value);
                	}
                	
                	else if(Reporttype.equals("history_item_sales_summary"))
                	{
                		History_Reports.Item_sale.textField_2.setText(value);
                	}
                	else if(Reporttype.equals("offer"))
                	{
                		Offer_zone.textField.setText(table.getModel().getValueAt(rows, 0).toString());
                		Offer_zone.textField_1.setText(value);
                	}
                	else if(Reporttype.equals("printer"))
                	{
                		assign_kot_printer.textField.setText(table.getModel().getValueAt(rows, 0).toString());
                		assign_kot_printer.textField_1.setText(table.getModel().getValueAt(rows, 1).toString());
                	}
                	else if(Reporttype.equals("item_utlity"))
                	{
                		create_item_utility.textField_1.setText(table.getModel().getValueAt(rows, 0).toString());
                		create_item_utility.textField.setText(table.getModel().getValueAt(rows, 1).toString());
                	}
                	else if(Reporttype.equals("po_history"))
                	{
                		PO_reports.vendor_items_purchase.textField_2.setText(table.getModel().getValueAt(rows, 1).toString());
                	}
                	else if(Reporttype.equals("adj_report"))
                	{
                		InventoryAdjustmentReport.textField_2.setText(table.getModel().getValueAt(rows, 1).toString());
                	}
                	
                	else
                	{
                		History_Reports.sales_summary.textField_6.setText(value);
                	}
                	
                	frame.setVisible(false);
                }
            }
        });
	}
}
