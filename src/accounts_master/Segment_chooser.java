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

import org.json.JSONException;

public class Segment_chooser {

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
					Segment_chooser window = new Segment_chooser("mr_pro","1.0","","S01","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param store 
	 */
	public Segment_chooser(String company, String version,String choose_type, String store,String language) throws JSONException {
		initialize(company,  version,choose_type, store,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @param language 
	 */
	private void initialize(final String company, String version,String choose_type,String store, String language) {
		frame = new JFrame("Segment Chooser");
		frame.setBounds(0, 0, 543, 400);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 537, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblShifts = new JLabel("Segment Chooser");
		if(language.equals("Arabic"))
		{
			lblShifts.setText("„Õœœ «·ﬁÿ«⁄");
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
		mntmNewMenuItem.setBounds(438, 11, 84, 44);
		panel.add(mntmNewMenuItem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 74, 534, 288);
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
				model.addColumn("‘›—…"); 
			    model.addColumn("Ê’›"); 
			    model.addColumn("‘—ÌÕ…");
			}
			else
			{
				model.addColumn("Code"); 
			    model.addColumn("Description"); 
			    model.addColumn("Segment");
			}
		    
		    scrollPane.setViewportView(table);
		    
		    
		    try {
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_acc_segment where segment='"+choose_type+"' and store_id='"+store+"'");
				while(resultSet.next())
				{
					 String code=resultSet.getString("code");
			            String desc=resultSet.getString("name");
			            String status=resultSet.getString("segment");
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
	                	
	                	accounts_master.Account_Segments.get_segment_dt(company, value, table.getModel().getValueAt(rows, 2).toString());
	                    frame.setVisible(false);
	                }
	            }
	        });
		    
	}

}
