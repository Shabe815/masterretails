package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuItem;

import org.json.JSONException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Assign {

	private static JFrame frame;
	public static JTextField textField;
	public static JTextField textField_1;
	private JTable table;
	private static DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					Assign window = new Assign("mr_pro", null);
					Assign.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Assign(String company, String version) throws JSONException {
		initialize(company, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @throws JSONException 
	 */
	private void initialize(final String company,final String version) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 650, 400);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 634, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblShift = new JLabel("Assign Register");
		lblShift.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShift.setBounds(10, 11, 228, 44);
		panel.add(lblShift);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 72, 684, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("User Code :");
		lblNewLabel.setBounds(21, 98, 102, 14);
		panel.add(lblNewLabel);
		
		JLabel lblShiftName = new JLabel("User Name :");
		lblShiftName.setBounds(21, 130, 108, 14);
		panel.add(lblShiftName);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(133, 95, 118, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(133, 127, 254, 20);
		panel.add(textField_1);
		
		Image search=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new user_list(company,"assign_register", version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_3.setIcon(new ImageIcon(search));
		button_3.setBounds(261, 94, 45, 23);
		panel.add(button_3);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 169, 684, 2);
		panel.add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 169, 634, 193);
		panel.add(scrollPane);
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable (int row,int column){
				if (column == 1)
				{
					return true;
				}
				return false;
			}
			public Class<?> getColumnClass (int column){
			   switch (column){
			   case 1: 
				   return Boolean.class;
			   default: 
				   return String.class;
			   }
		   }
		};
		table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(20);
		    model.addColumn("Register Code"); 
		    model.addColumn(""); 
			scrollPane.setViewportView(table);
		    
			try {
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				ResultSet resultSet = statement.executeQuery("select register_number from "+company+".mr_register_setup ");
				int rowCount = model.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
					model.removeRow(i);
				}
				while(resultSet.next())
				{
					String id=resultSet.getString("register_number");
		            model.addRow(new Object[]{id, new Boolean(false)});
				}
				con.close();
			} catch (Exception e) {
				System.err.println(e);
			}
			
		    Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
			Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
			Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		    
		    JMenuItem menuItem = new JMenuItem("Exit");
		    menuItem.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		frame.setVisible(false);
		    	}
		    });
		    menuItem.setBorder(null);
		    menuItem.setIcon(new ImageIcon(exit));
		    menuItem.setBackground(new Color(243, 229, 216));
		    menuItem.setBounds(410, 11, 60, 48);
		    panel.add(menuItem);
		    
		    JMenuItem menuItem_1 = new JMenuItem("Refresh");
		    menuItem_1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		textField.setText("");
		    		textField_1.setText("");
		    		for(int x=0;x<model.getRowCount();x++)
		    		{
		    			model.setValueAt(new Boolean(false), x, 1);
		    		}
		    		
		    	}
		    });
		    menuItem_1.setBorder(null);
		    menuItem_1.setIcon(new ImageIcon(refresh));
		    menuItem_1.setBackground(new Color(243, 229, 216));
		    menuItem_1.setBounds(471, 11, 80, 48);
		    panel.add(menuItem_1);
		    
		    JMenuItem menuItem_2 = new JMenuItem("Save");
		    menuItem_2.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		String user_code=textField.getText();
		    		if(user_code.equals(""))
		    		{
		    			JOptionPane.showMessageDialog(frame, "Select a User from list");
		    		}
		    		else
		    		{
		    			LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);  
					    Connection con=Dbsetup.dbconn.getConnection();
						Statement statement;
						try {
							statement = con.createStatement();
							ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_assign_register where ref_user_code='"+user_code+"' ");
							if(resultSet.next())
							{
								String query1="delete from "+company+".mr_assign_register where ref_user_code='"+user_code+"'";
								statement.executeUpdate(query1);
								for(int i=0;i<model.getRowCount();i++)
								{
									String query="insert into "+company+".mr_assign_register (ref_user_code,ref_register_num,select_status,created_date,modified_date) values ('"+user_code+"','"+model.getValueAt(i, 0)+"','"+model.getValueAt(i, 1)+"','"+formattedDate+"','"+formattedDate+"')";
									statement.executeUpdate(query);
								}
								
								JOptionPane.showMessageDialog(frame, "Saved Successfully");
							}
							else
							{
								for(int i=0;i<model.getRowCount();i++)
								{
									String query="insert into "+company+".mr_assign_register (ref_user_code,ref_register_num,select_status,created_date,modified_date) values ('"+user_code+"','"+model.getValueAt(i, 0)+"','"+model.getValueAt(i, 1)+"','"+formattedDate+"','"+formattedDate+"')";
									statement.executeUpdate(query);
								}
								
								JOptionPane.showMessageDialog(frame, "Saved Successfully");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}  
		    		}
		    	}
		    });
		    menuItem_2.setBorder(null);
		    menuItem_2.setIcon(new ImageIcon(save));
		    menuItem_2.setBackground(new Color(243, 229, 216));
		    menuItem_2.setBounds(550, 11, 70, 48);
		    panel.add(menuItem_2);
	}
	public static void get_registeredisp(String company, String reg_num)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="select * from "+company+".mr_assign_register where ref_user_code='"+reg_num+"' ";
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			while(resultSet.next())
			{
				String reg_no=resultSet.getString("ref_register_num");
				for(int x=0;x<rowCount;x++)
				{
					String Reg=model.getValueAt(x, 0).toString();
					if(Reg.equals(reg_no))
					{
						String sts=resultSet.getString("select_status");
						model.setValueAt(new Boolean(sts), x, 1);
					}
				}
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		
	}
}
