package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
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

public class Vendor_Category {

	private JFrame frame;
	static JTextField textField;
	static JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vendor_Category window = new Vendor_Category("mr_pro",  null);
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
	public Vendor_Category(String company,String version) throws JSONException {
		initialize(company, version);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company,final String version) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 650, 400);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 634, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblVendorCategory = new JLabel("Vendor Category");
		lblVendorCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVendorCategory.setBounds(10, 9, 228, 44);
		panel.add(lblVendorCategory);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 76, 634, 2);
		panel.add(separator);
		
		JLabel lblVendorCategory_1 = new JLabel("Vendor Category Code :");
		lblVendorCategory_1.setBounds(30, 114, 144, 14);
		panel.add(lblVendorCategory_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(186, 111, 118, 20);
		panel.add(textField);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new system_setups.VendorCat_Chooser(company, version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_3.setIcon(new ImageIcon(serch));
		button_3.setBounds(329, 110, 45, 23);
		panel.add(button_3);
		
		JLabel lblCategoryDescription = new JLabel("Category Description :");
		lblCategoryDescription.setBounds(30, 165, 135, 14);
		panel.add(lblCategoryDescription);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(186, 162, 254, 20);
		panel.add(textField_1);
		
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
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(470, 5, 80, 48);
		panel.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String vend_cat_code=textField.getText();
				String vend_cat_name=textField_1.getText();
				if(vend_cat_code.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Select or Enter the Category Code");
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
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_vendor_category where vendor_cat_code='"+vend_cat_code+"' ");
						if(resultSet.next())
						{
							String query="update "+company+".mr_vendor_category set vendor_cat_name='"+vend_cat_name+"' where vendor_cat_code='"+vend_cat_code+"' ";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
						else
						{
							String query="insert into "+company+".mr_vendor_category (vendor_cat_code,vendor_cat_name,created_date) values('"+vend_cat_code+"','"+vend_cat_name+"','"+formattedDate+"')";
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
}
