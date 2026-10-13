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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

public class register_list {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register_list window = new register_list("mr_pro", null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws JSONException 
	 */
	public register_list(String company,String version) throws JSONException {
		initialize(company, version);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company,String version) throws JSONException {
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
		
		
		
		JLabel lblCompanySetup = new JLabel("Store List");
		lblCompanySetup.setBounds(21, 11, 228, 44);
		Image shop=new ImageIcon(this.getClass().getResource("/shop.png")).getImage();
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
		model.addColumn("Register Number"); 
		model.addColumn("Register Name"); 
		scrollPane.setViewportView(table);
		
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_register_setup ");
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			while(resultSet.next())
			{
				 String id=resultSet.getString("register_number");
		         String name=resultSet.getString("register_name");
		         model.addRow(new Object[]{id, name});
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
                	
                	Registers.get_registeredisp(company, value);
                	Registers.textField.setText(value);
                	Registers.textField_8.setText(table.getModel().getValueAt(rows, 1).toString());
                		
                	frame.setVisible(false);
                }
            }
        });
	}
}
