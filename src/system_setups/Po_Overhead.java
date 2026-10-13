package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;

import org.json.JSONException;

public class Po_Overhead {

	private static JFrame frame;
	private static JTextField textField;
	private static JTextField textField_1;
	public static JTextField textField_2;
	private static JCheckBox chckbxIncludeInVendor;
	private static JComboBox<String> comboBox ;
	private static JCheckBox chckbxActiveForTransaction ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					Po_Overhead window = new Po_Overhead("mr_pro", null,"");
					Po_Overhead.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Po_Overhead(String company,String version, String language) throws JSONException {
		initialize(company,version,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @param language 
	 */
	private void initialize(final String company,final String version, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 650, 410);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		Image search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();	
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 634, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblVendorCategory = new JLabel("PO Overhead Charges");
		lblVendorCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVendorCategory.setBounds(10, 9, 228, 44);
		panel.add(lblVendorCategory);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 76, 634, 2);
		panel.add(separator);
		
		JLabel lblVendorCategory_1 = new JLabel("OverHead Charge Code :");
		lblVendorCategory_1.setBounds(30, 114, 146, 14);
		panel.add(lblVendorCategory_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(186, 111, 118, 20);
		panel.add(textField);
		
		JButton button_3 = new JButton("");
		button_3.setIcon(new ImageIcon(search));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new system_setups.PoOver_Chooser(company, version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_3.setBounds(329, 110, 45, 23);
		panel.add(button_3);
		
		JLabel lblCategoryDescription = new JLabel("OverHead Charge Name :");
		lblCategoryDescription.setBounds(30, 165, 157, 14);
		panel.add(lblCategoryDescription);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(186, 162, 254, 20);
		panel.add(textField_1);
		
		chckbxActiveForTransaction = new JCheckBox("Active For Transaction");
		chckbxActiveForTransaction.setBackground(new Color(243, 229, 216));
		chckbxActiveForTransaction.setBounds(30, 294, 178, 23);
		panel.add(chckbxActiveForTransaction);
		
		JLabel lblOverheadType = new JLabel("OverHead Type :");
		lblOverheadType.setBounds(30, 204, 135, 14);
		panel.add(lblOverheadType);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(186, 201, 184, 20);
		comboBox.addItem("Expense");
		comboBox.addItem("Payable");
		panel.add(comboBox);
		
		JLabel lblDistributionAc = new JLabel("Distribution A/C :");
		lblDistributionAc.setBounds(30, 239, 135, 14);
		panel.add(lblDistributionAc);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(186, 236, 163, 20);
		panel.add(textField_2);
		
		JButton button_4 = new JButton("");
		button_4.setIcon(new ImageIcon(search));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new accounts_master.SelectAccount( company, "po_overhead", 0, version,"", "", language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_4.setBounds(368, 235, 45, 23);
		panel.add(button_4);
		
		chckbxIncludeInVendor  = new JCheckBox("Include in Vendor PO Amount( Payable=GRN+PO overhead)");
		chckbxIncludeInVendor.setBackground(new Color(243, 229, 216));
		chckbxIncludeInVendor.setBounds(30, 332, 381, 23);
		panel.add(chckbxIncludeInVendor);
		
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
		menuItem.setBounds(410, 5, 60, 48);
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.setBorder(null);
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(470, 5, 80, 48);
		panel.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String oh_chrge_code=textField.getText();
				String oh_chrge_name=textField_1.getText();
				String distribution_acc=textField_2.getText();
				String oh_type= comboBox.getSelectedItem().toString();
				String status="false";
				String inc_vendor_amt="false";
				if(oh_chrge_code.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Select or Enter the Over Head Charge Code");
				}
				else
				{
					if(chckbxActiveForTransaction.isSelected())
					{
						status="true";
					}
					if(chckbxIncludeInVendor.isSelected())
					{
						inc_vendor_amt="true";
					}
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					try {
						statement = con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_po_overhead where oh_charge_code='"+oh_chrge_code+"' ");
						if(resultSet.next())
						{
							String query="update "+company+".mr_po_overhead set oh_charge_name='"+oh_chrge_name+"',oh_type='"+oh_type+"',distribution_acc='"+distribution_acc+"',status='"+status+"',include_vendor_po_amt='"+inc_vendor_amt+"'  where oh_charge_code='"+oh_chrge_code+"' ";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
						else
						{
							String query="insert into "+company+".mr_po_overhead (oh_charge_name,oh_type,distribution_acc,status,include_vendor_po_amt,oh_charge_code,created_date) values ('"+oh_chrge_name+"','"+oh_type+"','"+distribution_acc+"','"+status+"','"+inc_vendor_amt+"','"+oh_chrge_code+"','"+formattedDate+"')";
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
		menuItem_2.setBounds(552, 5, 70, 48);
		panel.add(menuItem_2);
	}
	static void  get_po_oh_disp(String company, String value)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_po_overhead where oh_charge_code='"+value+"' ");
			if(resultSet.next())
			{
				textField.setText(resultSet.getString("oh_charge_code"));
				textField_1.setText(resultSet.getString("oh_charge_name"));
				comboBox.setSelectedItem(resultSet.getString("oh_type"));
				textField_2.setText(resultSet.getString("distribution_acc"));
				if(resultSet.getString("status").equals("true"))
				{
					chckbxActiveForTransaction.setSelected(true);
				}
				else
				{
					chckbxActiveForTransaction.setSelected(false);
				}
				if(resultSet.getString("include_vendor_po_amt").equals("true"))
				{
					chckbxIncludeInVendor.setSelected(true);
				}
				else
				{
					chckbxIncludeInVendor.setSelected(false);
				}
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
