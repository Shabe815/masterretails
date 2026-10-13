package system_setups;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;

public class Working_Shift {

	private static JFrame frame;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JComboBox<String> comboBox;
	private static JComboBox<String> comboBox_1;
	private static JComboBox<String> comboBox_2;
	private static JComboBox<String> comboBox_3;
	private static JComboBox<String> comboBox_4;
	private static JCheckBox chckbxNewCheckBox ;
	private static JCheckBox chckbxShift;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					Working_Shift window = new Working_Shift("mr_pro", "admin", null);
					Working_Shift.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Working_Shift(String company, String username,String version) {
		initialize(company,  username, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param host_name 
	 * @param company 
	 */
	private void initialize(final String company,final String username,final String version) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 652, 407);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 634, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblShift = new JLabel("Working Shift");
		lblShift.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShift.setBounds(10, 11, 228, 44);
		panel.add(lblShift);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 72, 684, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Shift Code :");
		lblNewLabel.setBounds(21, 98, 102, 14);
		panel.add(lblNewLabel);
		
		JLabel lblShiftName = new JLabel("Shift Name :");
		lblShiftName.setBounds(21, 130, 108, 14);
		panel.add(lblShiftName);
		
		textField = new JTextField();
		textField.setBounds(133, 95, 118, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(133, 127, 254, 20);
		panel.add(textField_1);
		
		Image search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton button_3 = new JButton("");
		button_3.setIcon(new ImageIcon(search));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Shift_Choose(company, version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_3.setBounds(261, 94, 45, 23);
		panel.add(button_3);
		
		JLabel lblShiftSchedule = new JLabel("Shift Schedule :");
		lblShiftSchedule.setBounds(21, 173, 102, 14);
		panel.add(lblShiftSchedule);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Single", "Double"}));
		comboBox.setBounds(133, 170, 128, 20);
		panel.add(comboBox);
		
		JLabel lblTimeSchedule = new JLabel("Time Schedule :");
		lblTimeSchedule.setBounds(21, 239, 102, 14);
		panel.add(lblTimeSchedule);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_1.setBounds(133, 236, 45, 20);
		panel.add(comboBox_1);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}));
		comboBox_2.setBounds(216, 236, 45, 20);
		panel.add(comboBox_2);
		
		JLabel lblNewLabel_1 = new JLabel(":");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(193, 238, 13, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("To");
		lblNewLabel_2.setBounds(283, 239, 24, 14);
		panel.add(lblNewLabel_2);
		
		comboBox_3 = new JComboBox<String>();
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_3.setBounds(315, 236, 45, 20);
		panel.add(comboBox_3);
		
		JLabel label_1 = new JLabel(":");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(374, 239, 13, 14);
		panel.add(label_1);
		
		comboBox_4 = new JComboBox<String>();
		comboBox_4.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}));
		comboBox_4.setBounds(402, 236, 45, 20);
		panel.add(comboBox_4);
		
		chckbxNewCheckBox = new JCheckBox("This Shift is Active");
		chckbxNewCheckBox.setBackground(new Color(243, 229, 216));
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setBounds(21, 291, 254, 23);
		panel.add(chckbxNewCheckBox);
		
		chckbxShift = new JCheckBox("Shift Belongs To POS at Retail Store");
		chckbxShift.setBackground(new Color(243, 229, 216));
		chckbxShift.setSelected(true);
		chckbxShift.setToolTipText("");
		chckbxShift.setBounds(21, 332, 254, 23);
		panel.add(chckbxShift);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(410, 7, 60, 48);
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(470, 7, 80, 48);
		panel.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String code=textField.getText();
				String name=textField_1.getText();
				String schedule=comboBox.getSelectedItem().toString();
				String from_hr=comboBox_1.getSelectedItem().toString();
				String from_mm=comboBox_2.getSelectedItem().toString();
				String to_hr=comboBox_3.getSelectedItem().toString();
				String to_mm=comboBox_4.getSelectedItem().toString();
				String active="0";
				String shift_based_pos="0";
				if(code.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter or Choose Shift Code");
				}
				else
				{
					if(chckbxNewCheckBox.isSelected())
					{
						active="1";
					}
					if(chckbxShift.isSelected())
					{
						shift_based_pos="1";
					}
					
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					try {
						statement = con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_usershift where shift_code='"+code+"' ");
						if(resultSet.next())
						{
							String query="update "+company+".mr_usershift set shift_name='"+name+"',shift_schedule='"+schedule+"',time_from='"+from_hr+":"+from_mm+"',time_to='"+to_hr+":"+to_mm+"',active_shift='"+active+"',shift_based_pos='"+shift_based_pos+"',modified_date='"+formattedDate+"' where shift_code='"+code+"' ";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
						else
						{
							String query="insert into "+company+".mr_usershift (shift_code,shift_name,shift_schedule,time_from,time_to,active_shift,shift_based_pos,created_date,modified_date) values ('"+code+"','"+name+"','"+schedule+"','"+from_hr+":"+from_mm+"','"+to_hr+":"+to_mm+"','"+active+"','"+shift_based_pos+"','"+formattedDate+"','"+formattedDate+"')";
							statement.executeUpdate(query);
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
		menuItem_2.setBounds(552, 7, 70, 48);
		panel.add(menuItem_2);
	}
	
	static void shift_disp(String company, String shift_id)
	{
		try {
			java.sql.Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=(Statement) con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_usershift where shift_code='"+shift_id+"' ");
			while(resultSet.next())
			{
				textField.setText(resultSet.getString("shift_code"));
				textField_1.setText(resultSet.getString("shift_name"));
				comboBox.setSelectedItem(resultSet.getString("shift_schedule"));
				String time_from=resultSet.getString("time_from");
				String[] from_tym=time_from.split("\\:");
				String time_to=resultSet.getString("time_to");
				String[] to_tym=time_to.split("\\:");
				comboBox_1.setSelectedItem(from_tym[0]);
				comboBox_2.setSelectedItem(from_tym[1]);
				comboBox_3.setSelectedItem(to_tym[0]);
				comboBox_4.setSelectedItem(to_tym[1]);
				
				String active_shift=resultSet.getString("active_shift");
				String shift_based_pos=resultSet.getString("shift_based_pos");
				if(active_shift.equals("1"))
				{
					chckbxNewCheckBox.setSelected(true);
				}
				else
				{
					chckbxNewCheckBox.setSelected(false);
				}
				if(shift_based_pos.equals("1"))
				{
					chckbxShift.setSelected(true);
				}
				else
				{
					chckbxShift.setSelected(false);
				}
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
}
