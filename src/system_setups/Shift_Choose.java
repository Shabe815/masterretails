package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuItem;

import org.json.JSONException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.Statement;

public class Shift_Choose {

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
					Shift_Choose window = new Shift_Choose("mr_pro", null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param company 
	 * @param host_name 
	 * @throws JSONException 
	 */
	public Shift_Choose(String company,String version) throws JSONException {
		initialize(company, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws JSONException 
	 */
	private void initialize(final String company,String version) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 556, 400);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 538, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblShiftChoose = new JLabel("Shift Choose");
		lblShiftChoose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShiftChoose.setBounds(10, 11, 228, 44);
		panel.add(lblShiftChoose);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 72, 684, 2);
		panel.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 73, 528, 289);
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
		    model.addColumn("Shift Code"); 
		    model.addColumn("Shift Name"); 
			model.addColumn("Shift Schedule"); 
		    scrollPane.setViewportView(table);
		    get_userdisp(company);
		    table.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent mouseEvent) {
	                JTable table =(JTable) mouseEvent.getSource();
	                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
	                	int column = 0;
	                	int rows = table.getSelectedRow();
	                	String value = table.getModel().getValueAt(rows, column).toString();
	                	Working_Shift.shift_disp( company, value);
	                	frame.setVisible(false);
	                }
	            }
	        });
		    
		    Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
			Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
			
			JMenuItem menuItem = new JMenuItem("Exit");
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					frame.setVisible(false);
				}
			});
			menuItem.setBorder(null);
			menuItem.setIcon(new ImageIcon(exit));
			menuItem.setBackground(new Color(243, 229, 216));
			menuItem.setBounds(382, 11, 60, 48);
			panel.add(menuItem);
			
			JMenuItem menuItem_1 = new JMenuItem("Refresh");
			menuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					get_userdisp( company);
				}
			});
			menuItem_1.setBorder(null);
			menuItem_1.setIcon(new ImageIcon(refresh));
			menuItem_1.setBackground(new Color(243, 229, 216));
			menuItem_1.setBounds(442, 11, 80, 48);
			panel.add(menuItem_1);
	}
	private void  get_userdisp(String company)
	{
		try {
			java.sql.Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_usershift ");
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			while(resultSet.next())
			{
				 String id=resultSet.getString("shift_code");
		            String name=resultSet.getString("shift_name");
		            String stats=resultSet.getString("shift_schedule");
		            model.addRow(new Object[]{id, name, stats});
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
