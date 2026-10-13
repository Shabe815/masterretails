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
import javax.swing.JTextField;
import javax.swing.JCheckBox;
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

public class Shipment_Codes {

	private JFrame frame;
	static JTextField textField;
	static JTextField textField_1;
	static JCheckBox chckbxActiveForTransaction;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shipment_Codes window = new Shipment_Codes("mr_pro", null);
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
	public Shipment_Codes(String company,String version) throws JSONException {
		initialize(company,  version);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company,final String version) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 639, 400);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 634, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblVendorCategory = new JLabel("Shipment Modes");
		lblVendorCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVendorCategory.setBounds(10, 9, 228, 44);
		panel.add(lblVendorCategory);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 76, 634, 2);
		panel.add(separator);
		
		JLabel lblVendorCategory_1 = new JLabel("Shipment Code :");
		lblVendorCategory_1.setBounds(30, 114, 102, 14);
		panel.add(lblVendorCategory_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(186, 111, 118, 20);
		panel.add(textField);
		
		Image search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new system_setups.ShipmentMode_Chooser(company, version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_3.setBounds(329, 110, 45, 23);
		button_3.setIcon(new ImageIcon(search));
		panel.add(button_3);
		
		JLabel lblCategoryDescription = new JLabel("Description :");
		lblCategoryDescription.setBounds(30, 165, 135, 14);
		panel.add(lblCategoryDescription);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(186, 162, 254, 20);
		panel.add(textField_1);
		
		chckbxActiveForTransaction = new JCheckBox("Active For Transaction");
		chckbxActiveForTransaction.setBackground(new Color(243, 229, 216));
		chckbxActiveForTransaction.setBounds(186, 217, 178, 23);
		panel.add(chckbxActiveForTransaction);
		
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
		menuItem.setBounds(410, 9, 60, 48);
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
		menuItem_1.setBounds(470, 9, 80, 48);
		panel.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ship_code=textField.getText();
				String ship_name=textField_1.getText();
				String status="0";
				if(ship_code.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Select or Enter the Category Code");
				}
				else
				{
					if(chckbxActiveForTransaction.isSelected())
					{
						status="1";
					}
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					try {
						statement = con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_shipment_mode where shipment_code='"+ship_code+"' ");
						if(resultSet.next())
						{
							String query="update "+company+".mr_shipment_mode set shipment_name='"+ship_name+"',status='"+status+"'  where shipment_code='"+ship_code+"' ";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
						else
						{
							String query="insert into "+company+".mr_shipment_mode (shipment_code,shipment_name,status,created_date) values('"+ship_code+"','"+ship_name+"','"+status+"','"+formattedDate+"')";
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
		menuItem_2.setBounds(552, 9, 70, 48);
		panel.add(menuItem_2);
	}
}
