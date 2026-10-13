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
import javax.swing.table.TableColumn;
import javax.swing.JCheckBox;

public class take_away_users {

	private static JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private JMenuItem mntmSave;
	private JCheckBox chckbxStatus;
	
    private String company="";
	
    LocalDateTime myDateObj = LocalDateTime.now();  
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    String formattedDate = myDateObj.format(myFormatObj);  
    private JTextField textField;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					take_away_users window = new take_away_users("mr_pro", null,"S01");
					take_away_users.frame.setVisible(true);
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
	public take_away_users(String db_name,String version,String store) throws JSONException, SQLException {
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
		JLabel lblCompanySetup = new JLabel("Take Away Types");
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
		model.addColumn("Type");
		model.addColumn("Status");
		scrollPane.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(243, 229, 216));
		panel_3.setBounds(12, 0, 708, 91);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblSelectNoOf_1 = new JLabel("Create New Type");
		lblSelectNoOf_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectNoOf_1.setBounds(12, 11, 222, 20);
		panel_3.add(lblSelectNoOf_1);
		
		JLabel lblUserCode = new JLabel("Type");
		lblUserCode.setBounds(12, 42, 68, 25);
		panel_3.add(lblUserCode);
		
		textField = new JTextField();
		textField.setBounds(90, 42, 509, 25);
		panel_3.add(textField);
		textField.setColumns(10);
		
		chckbxStatus = new JCheckBox("Status");
		chckbxStatus.setBounds(605, 42, 97, 25);
		chckbxStatus.setSelected(true);
		chckbxStatus.setBackground(new Color(243, 229, 216));
		panel_3.add(chckbxStatus);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 100, 730, 2);
		panel_1.add(separator_1);
		
		
		get_dept_list(company);
		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int colmns=model.getColumnCount();
		for (int i=0; i<colmns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(150);
		  if (i==1) column.setPreferredWidth(400);
		  if (i==2) column.setPreferredWidth(155);
		}
		

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					int rows = table.getSelectedRow();
					textField.setText(table.getModel().getValueAt(rows, 1).toString());
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
			String user=textField.getText().trim();
			String sts="0";
			if(chckbxStatus.isSelected())
			{
				sts="1";
			}
			String query="Select * from "+company+".mr_take_away_users where user_name='"+user+"' ";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				String query1="update "+company+".mr_take_away_users set status='"+sts+"',modified_date='"+formattedDate+"' where user_name='"+user+"' ";
				statement.executeUpdate(query1); 
				JOptionPane.showMessageDialog(frame, "Saved Successfully");
			}
			else
			{
				String query1="insert into "+company+".mr_take_away_users (user_name,status,created_date,modified_date) values('"+user+"','"+sts+"','"+formattedDate+"','"+formattedDate+"')";
				statement.executeUpdate(query1); 
				JOptionPane.showMessageDialog(frame, "Saved Successfully");
			}
			textField.setText("");
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
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_take_away_users ");
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			while(resultSet.next())
			{
				
				String user_name = resultSet.getString("user_name");
				String status = resultSet.getString("status");
				model.addRow(new Object[] { model.getRowCount()+1, user_name, status });
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
