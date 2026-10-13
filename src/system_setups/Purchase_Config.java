package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

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


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Purchase_Config {

	private JFrame frame;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JCheckBox chckbxNewCheckBox ;
	private JCheckBox chckbxPauseBefore;
	private JCheckBox chckbxPrintHold;
	private JCheckBox chckbxPrintDuplicate;
	private JCheckBox chckbxPrintDuplicate_2;
	private JCheckBox chckbxReturnEr;
	private JCheckBox chckbxAllowStore;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Purchase_Config window = new Purchase_Config("mr_pro", null);
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
	public Purchase_Config(String company, String version) throws JSONException {
		initialize(company,  version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 */
	private void initialize(final String company,String version) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 750, 500);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		Image contrl_pnl=new ImageIcon(this.getClass().getResource("/control-panel.png")).getImage();
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 734, 462);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblRegisterControl = new JLabel("Purchase Configuration");
		lblRegisterControl.setIcon(new ImageIcon(contrl_pnl));
		lblRegisterControl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRegisterControl.setBounds(10, 11, 228, 44);
		panel.add(lblRegisterControl);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 77, 734, 2);
		panel.add(separator);
		
		chckbxNewCheckBox = new JCheckBox("1. Incude TAX amount in Inventory Cost");
		chckbxNewCheckBox.setBackground(new Color(243, 229, 216));
		chckbxNewCheckBox.setBounds(27, 100, 288, 23);
		panel.add(chckbxNewCheckBox);
		
		chckbxPauseBefore = new JCheckBox("2. Allow PO to be Changed after Confirmation");
		chckbxPauseBefore.setBackground(new Color(243, 229, 216));
		chckbxPauseBefore.setBounds(27, 137, 325, 23);
		panel.add(chckbxPauseBefore);
		
		chckbxPrintHold = new JCheckBox("3. Disable Over Purchasing");
		chckbxPrintHold.setBackground(new Color(243, 229, 216));
		chckbxPrintHold.setBounds(27, 175, 254, 23);
		panel.add(chckbxPrintHold);
		
		chckbxPrintDuplicate = new JCheckBox("4. Centralize Purchasing Only");
		chckbxPrintDuplicate.setBackground(new Color(243, 229, 216));
		chckbxPrintDuplicate.setBounds(27, 213, 254, 23);
		panel.add(chckbxPrintDuplicate);
		
		chckbxPrintDuplicate_2 = new JCheckBox("6. Disable Purchase of Non-Vendor item");
		chckbxPrintDuplicate_2.setEnabled(false);
		chckbxPrintDuplicate_2.setSelected(true);
		chckbxPrintDuplicate_2.setBackground(new Color(243, 229, 216));
		chckbxPrintDuplicate_2.setBounds(27, 310, 325, 23);
		panel.add(chckbxPrintDuplicate_2);
		
		chckbxReturnEr = new JCheckBox("7. Budgeted Purchasing Only\r\n");
		chckbxReturnEr.setBackground(new Color(243, 229, 216));
		chckbxReturnEr.setBounds(27, 352, 309, 23);
		panel.add(chckbxReturnEr);
		
		chckbxAllowStore = new JCheckBox("9. Us Default AP Account for Vendor");
		chckbxAllowStore.setBackground(new Color(243, 229, 216));
		chckbxAllowStore.setSelected(true);
		chckbxAllowStore.setBounds(373, 137, 312, 23);
		panel.add(chckbxAllowStore);
		
		textField = new JTextField("WH01");
		textField.setEditable(false);
		textField.setBounds(27, 274, 172, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblLocationChooser = new JLabel("5. Location :");
		lblLocationChooser.setBounds(27, 246, 115, 16);
		panel.add(lblLocationChooser);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(209, 273, 56, 23);
		panel.add(btnNewButton);
		
		JLabel lblDefaultPo = new JLabel("8. Default PO Currency :");
		lblDefaultPo.setBounds(373, 104, 155, 14);
		panel.add(lblDefaultPo);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(547, 101, 122, 20);
		comboBox.addItem("AED");
		panel.add(comboBox);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Save");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String loc=textField.getText();
				String currecy=comboBox.getSelectedItem().toString();
				String include_tax_invnty="";
				String allow_po_change="";
				String disable_purchasing="";
				String centralize_purchse="";
				String disable_puchse_nonvendor_itm="";
				String budgeted_purchse_only="";
				String default_ap_accnt="";
				if(loc.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please select a location");
				}
				else
				{
					if(chckbxNewCheckBox.isSelected())
					{
						include_tax_invnty="1";
					}
					else
					{
						include_tax_invnty="0";
					}
					if(chckbxPauseBefore.isSelected())
					{
						allow_po_change="1";
					}
					else
					{
						allow_po_change="0";
					}
					if(chckbxPrintHold.isSelected())
					{
						disable_purchasing="1";
					}
					else
					{
						disable_purchasing="0";	
					}
					if(chckbxPrintDuplicate.isSelected())
					{
						centralize_purchse="1";
					}
					else
					{
						centralize_purchse="0";
					}
					if(chckbxPrintDuplicate_2.isSelected())
					{
						disable_puchse_nonvendor_itm="1";
					}
					else
					{
						disable_puchse_nonvendor_itm="0";
					}
					if(chckbxReturnEr.isSelected())
					{
						budgeted_purchse_only="1";
					}
					else
					{
						budgeted_purchse_only="0";
					}
					if(chckbxAllowStore.isSelected())
					{
						default_ap_accnt="1";
					}
					else
					{
						default_ap_accnt="0";
					}
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					try {
						statement = con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_purchase_config where location='"+loc+"' ");
						if(resultSet.next())
						{
							String query="update "+company+".mr_purchase_config set include_tax_inventry_cst='"+include_tax_invnty+"',allow_po_change='"+allow_po_change+"',disable_purchasing='"+disable_purchasing+"',centralize_purchse='"+centralize_purchse+"',disable_puchse_nonvendor_itm='"+disable_puchse_nonvendor_itm+"',budgeted_purchse_only='"+budgeted_purchse_only+"',po_currency='"+currecy+"',default_ap_accnt='"+default_ap_accnt+"',modified_date='"+formattedDate+"'where location='"+loc+"' ";
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
		menuItem.setBorder(null);
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(652, 11, 70, 48);
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_registeredisp(company);
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(570, 11, 80, 48);
		panel.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Exit");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(510, 11, 60, 48);
		panel.add(menuItem_2);
		get_registeredisp(company);
	}
	public void get_registeredisp(String company)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_purchase_config");
			if(resultSet.next())
			{
				textField.setText(resultSet.getString("location"));
				comboBox.setSelectedItem(resultSet.getString("po_currency"));
				if(resultSet.getString("include_tax_inventry_cst").equals("1"))
				{
					chckbxNewCheckBox.setSelected(true);
				}
				else
				{
					chckbxNewCheckBox.setSelected(false);
				}
				if(resultSet.getString("allow_po_change").equals("1"))
				{
					chckbxPauseBefore.setSelected(true);
				}
				else
				{
					chckbxPauseBefore.setSelected(false);
				}
				if(resultSet.getString("disable_purchasing").equals("1"))
				{
					chckbxPrintHold.setSelected(true);
				}
				else
				{
					chckbxPrintHold.setSelected(false);
				}
				if(resultSet.getString("centralize_purchse").equals("1"))
				{
					chckbxPrintDuplicate.setSelected(true);
				}
				else
				{
					chckbxPrintDuplicate.setSelected(false);
				}
				if(resultSet.getString("disable_puchse_nonvendor_itm").equals("1"))
				{
					chckbxPrintDuplicate_2.setSelected(true);
				}
				else
				{
					chckbxPrintDuplicate_2.setSelected(false);
				}
				if(resultSet.getString("default_ap_accnt").equals("1"))
				{
					chckbxAllowStore.setSelected(true);
				}
				else
				{
					chckbxAllowStore.setSelected(false);
				}
				if(resultSet.getString("budgeted_purchse_only").equals("1"))
				{
					chckbxReturnEr.setSelected(true);
				}
				else
				{
					chckbxReturnEr.setSelected(false);
				}
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
