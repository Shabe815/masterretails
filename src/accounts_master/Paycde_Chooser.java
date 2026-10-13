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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Paycde_Chooser {

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
					Paycde_Chooser window = new Paycde_Chooser("mr_pro","","");
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
	public Paycde_Chooser(String company, String version,String language) {
		initialize(company,version,language);
	}
	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @param language 
	 */
	private void initialize(final String company, String version, String language) {
		frame = new JFrame();
		frame.setBounds(0, 0, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 544, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblShifts = new JLabel("Account Paycode List");
		if(language.equals("Arabic"))
		{
			lblShifts.setText("ﬁ«∆„… —„“ «·œ›⁄ ··Õ”«»");
		}
		lblShifts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShifts.setBounds(10, 11, 228, 44);
		panel.add(lblShifts);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 72, 684, 2);
		panel.add(separator);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		if(language.equals("Arabic"))
		{
			mntmNewMenuItem.setText("„Œ—Ã");
		}
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(460, 11, 84, 44);
		panel.add(mntmNewMenuItem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 74, 544, 288);
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
			if(language.equals("Arabic"))
			{
				model.addColumn("—„“ «·œ›⁄"); 
			    model.addColumn("«”„ —„“ «·œ›⁄"); 
			    model.addColumn("›—⁄");
			}
			else
			{
				model.addColumn("Paycode"); 
			    model.addColumn("Paycode Name"); 
			    model.addColumn("Branch");
			}
		    
		    scrollPane.setViewportView(table);
		    
		    
		    try {
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_acc_paycode ");
				while(resultSet.next())
				{
					 String code=resultSet.getString("paycode");
			            String desc=resultSet.getString("paycode_name");
			            String status=resultSet.getString("branch");
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
	                	String value = table.getModel().getValueAt(rows, column).toString();
	                	
	                	accounts_master.Acc_Paycodes.get_paycode_dt(company,value);
	                    frame.setVisible(false);
	                }
	            }
	        });
		    
	}

}
