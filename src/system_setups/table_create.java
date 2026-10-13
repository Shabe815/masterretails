package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import org.json.JSONException;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;







import javax.swing.JButton;

public class table_create {

	private static JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private JMenuItem mntmSave;
	
    private String company="";
    private String store_num="";
    
    private JTextField textField;
    private JComboBox <String> comboBox;
    private JComboBox <String> comboBox_1;
	
    LocalDateTime myDateObj = LocalDateTime.now();  
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    String formattedDate = myDateObj.format(myFormatObj);  
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					table_create window = new table_create("mr_pro", null,"S01");
					table_create.frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws JSONException
	 * @throws SQLException 
	 */
	public table_create(String db_name,String version,String store) throws JSONException, SQLException {
		initialize(db_name, version,store);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param page 
	 * 
	 * @throws JSONException
	 * @throws SQLException 
	 */
	private void initialize(final String db_name,final String version,final String store)
			throws JSONException, SQLException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.setBounds(0, 0, 750, 500);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		
		company=db_name;
		store_num=store;
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 59, 732, 2);
		frame.getContentPane().add(separator);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 732, 58);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		frame.setVisible(true);

		Image dept=new ImageIcon(this.getClass().getResource("/department.png")).getImage();
		JLabel lblCompanySetup = new JLabel("Table Creation");
		lblCompanySetup.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCompanySetup.setIcon(new ImageIcon(dept));
		lblCompanySetup.setBounds(21, 11, 228, 44);
		panel.add(lblCompanySetup);

		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();

		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(514, 0, 60, 48);
		panel.add(mntmNewMenuItem);

		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_dept_list(company);
				
			}
		});
		mntmRefresh.setBackground(new Color(243, 229, 216));
		mntmRefresh.setIcon(new ImageIcon(refresh));
		mntmRefresh.setBounds(574, 0, 80, 48);
		mntmRefresh.setBorder(null);
		panel.add(mntmRefresh);

		mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sync(e);
			}

		});
		mntmSave.setBackground(new Color(243, 229, 216));
		mntmSave.setIcon(new ImageIcon(save));
		mntmSave.setBounds(656, 0, 152, 48);
		mntmSave.setBorder(null);
		panel.add(mntmSave);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setBounds(0, 62, 732, 391);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblDepartmentList = new JLabel("Table List");
		lblDepartmentList.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDepartmentList.setBounds(12, 110, 119, 16);
		panel_1.add(lblDepartmentList);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 137, 708, 254);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel_1.add(scrollPane);

		model = new DefaultTableModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		table = new JTable(model);
		model.addColumn("ID");
		model.addColumn("Store");
		model.addColumn("Table No.");
		model.addColumn("Chair No.");
		scrollPane.setViewportView(table);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(243, 229, 216));
		panel_2.setBounds(12, 0, 271, 91);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewDepartment = new JLabel("Table Count");
		lblNewDepartment.setBounds(12, 5, 119, 20);
		panel_2.add(lblNewDepartment);
		lblNewDepartment.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblSelectNoOf = new JLabel("Select No. of Tables");
		lblSelectNoOf.setBounds(12, 30, 119, 20);
		panel_2.add(lblSelectNoOf);
		
		comboBox = new JComboBox <String>();
		comboBox.setBounds(158, 30, 56, 20);
		panel_2.add(comboBox);
		
		JButton btnCreateTables = new JButton("Create Tables");
		btnCreateTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String table_count= comboBox.getSelectedItem().toString();
					String query="select count(*) as ttl_cnt from "+company+".mr_kot_tables where store_code='"+store+"'";
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement = con.createStatement();
					ResultSet resultSet = statement.executeQuery(query);
					if(resultSet.next())
					{
						int count=Integer.parseInt(resultSet.getString("ttl_cnt"));
						int tb_count=Integer.parseInt(table_count);
						int ttl_count=tb_count+count;
						for(int i=count+1; i<=ttl_count;i++)
						{
							String query1="insert into "+company+".mr_kot_tables (store_code,table_num,created_date,modified_date) values('"+store+"','TABLE-"+i+"','"+formattedDate+"','"+formattedDate+"')";
							statement.executeUpdate(query1); 
						}
						
						JOptionPane.showMessageDialog(frame, "Created Successfully");
						get_dept_list(company);
					}
					else
					{
						for(int i=1; i<=Integer.parseInt(table_count);i++)
						{
							String query1="insert into "+company+".mr_kot_tables (store_code,table_num,created_date,modified_date) values('"+store+"','TABLE"+i+"','"+formattedDate+"','"+formattedDate+"')";
							statement.executeUpdate(query1); 
						}
						
						JOptionPane.showMessageDialog(frame, "Created Successfully");
						get_dept_list(company);
					}
					
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(frame, e1.getMessage());
				}
			}
		});
		btnCreateTables.setBounds(59, 62, 119, 23);
		panel_2.add(btnCreateTables);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(243, 229, 216));
		panel_3.setBounds(282, 0, 312, 91);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblSelectNoOf_1 = new JLabel("Select No. of Chairs in Each Table");
		lblSelectNoOf_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectNoOf_1.setBounds(12, 11, 222, 20);
		panel_3.add(lblSelectNoOf_1);
		
		JLabel lblTableNo = new JLabel("Table No.");
		lblTableNo.setBounds(12, 56, 56, 20);
		panel_3.add(lblTableNo);
		
		textField = new JTextField();
		textField.setBounds(66, 56, 95, 20);
		panel_3.add(textField);
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel lblNoOfChairs = new JLabel("No of Chairs");
		lblNoOfChairs.setBounds(171, 56, 81, 20);
		panel_3.add(lblNoOfChairs);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(256, 56, 46, 20);
		panel_3.add(comboBox_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 100, 730, 2);
		panel_1.add(separator_1);
		
		for(int i=1; i<=25;i++)
		{
			comboBox.addItem(Integer.toString(i));
		}
		for(int i=1; i<=6;i++)
		{
			comboBox_1.addItem(Integer.toString(i));
		}

		get_dept_list(company);
		
		

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					int rows = table.getSelectedRow();
					textField.setText(table.getModel().getValueAt(rows, 2).toString());
					}
				}
		});
	}
	private void sync(ActionEvent e)
	{
		try
		{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement = con.createStatement();
			String sel_table=textField.getText().trim();
			String chair_count=comboBox_1.getSelectedItem().toString();
			String query1="update "+company+".mr_kot_tables set chair_count='"+chair_count+"',modified_date='"+formattedDate+"' where table_num='"+sel_table+"' and store_code='"+store_num+"'";
			statement.executeUpdate(query1); 
			JOptionPane.showMessageDialog(frame, "Saved Successfully");
			get_dept_list(company);
			
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(frame, e1.getMessage());
		}
		
	}
	private void get_dept_list( String company) {
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_kot_tables ");
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			while(resultSet.next())
			{
				
				String store_code = resultSet.getString("store_code");
				String table_num = resultSet.getString("table_num");
				String chair_count = resultSet.getString("chair_count");
				model.addRow(new Object[] { model.getRowCount()+1, store_code, table_num, chair_count });
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
