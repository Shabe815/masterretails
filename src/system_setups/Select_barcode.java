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
import java.sql.SQLException;
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

public class Select_barcode {

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
					Select_barcode window = new Select_barcode("mr_pro", null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Select_barcode(String company,String version) throws JSONException, SQLException {
		initialize(company,version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String company,String version) throws JSONException, SQLException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
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
		
		Image Barcode = new ImageIcon(this.getClass().getResource("/Barcode.png")).getImage();
		JLabel lblCompanySetup = new JLabel("Barcode Format");
		lblCompanySetup.setBounds(21, 11, 228, 44);
		lblCompanySetup.setIcon(new ImageIcon(Barcode));
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
	    model.addColumn("Format Code"); 
	    model.addColumn("Format Name"); 
	    model.addColumn("Type"); 
		scrollPane.setViewportView(table);
	
		Connection con=Dbsetup.dbconn.getConnection();
		Statement statement=con.createStatement();  
		ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_barcode ");
		while(resultSet.next())
		{
			String id=resultSet.getString("barcode_format");
            String name=resultSet.getString("code");
            String port=resultSet.getString("port");
            model.addRow(new Object[]{id, name,port});
		}
		
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 0;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	Barcode_Format.get_storedisp(company, value,table.getModel().getValueAt(rows, 2).toString());
                    frame.setVisible(false);
                }
            }
        });   
		    
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
