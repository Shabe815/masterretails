package accounts_master;

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

public class Acc_group_list {

	private JFrame frame;
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acc_group_list window = new Acc_group_list("mr_pro", null, null,"","");
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
	public Acc_group_list(String company,String page_type,String version,String query, String language) throws JSONException {
		initialize(company,page_type, version,query,language);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company,final String page_type,String version,String query, String language) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
	    frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 450);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		
		JLabel lblCompanySetup = new JLabel("Group List");
		if(language.equals("Arabic"))
		{
			lblCompanySetup.setText("ÞÇÆãÉ ÇáãÌãæÚÉ");
		}
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
		if(language.equals("Arabic"))
		{
			mntmNewMenuItem.setText("ãÎÑÌ");
		}
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(601, 11, 83, 48);
		frame.getContentPane().add(mntmNewMenuItem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 694, 347);
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
		if(language.equals("Arabic"))
		{
			model.addColumn("ãÚÑÝ ÇáãÊÌÑ"); 
			model.addColumn("ÇÓã ÇáãÍá"); 
			model.addColumn("ÍÇáÉ"); 
		}
		else
		{
			model.addColumn("Shop ID"); 
			model.addColumn("Shop Name"); 
			model.addColumn("Status"); 
		}
		scrollPane.setViewportView(table);
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_assign_account_group "+query+" ");
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			while(resultSet.next())
			{
				
				String store_id=resultSet.getString("group_code");
	            String name=resultSet.getString("group_name");
	            String stats=resultSet.getString("status");
	            model.addRow(new Object[]{store_id, name, stats});
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
                	if(page_type.equals("assign_acc"))
                	{
                		accounts_master.Assign_account.get_group_disp(company, value);
                	}
                	else if(page_type.equals("inventory_cntrl"))
                	{
                		inventory_master.Inventory_control.txtacc_code.setText(value);
                	}
                	else if(page_type.equals("cust_mstr"))
                	{
                		sales_master.Customer_master.textField_11.setText(value);
                	}
                	else if(page_type.equals("item_tax"))
                	{
                		system_setups.Item_Tax.textField_9.setText(value);
                	}
                	else
                	{
                		//Store.txtS.setText(value);
                	}
                	frame.setVisible(false);
                }
            }
        });
	}
}
