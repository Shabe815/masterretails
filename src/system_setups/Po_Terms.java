package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import org.json.JSONException;


public class Po_Terms {

	private static JFrame frame;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;
	private static JTextField textField_5;
	private static JCheckBox chckbxNewCheckBox ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					Po_Terms window = new Po_Terms("mr_pro",  null);
					Po_Terms.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Po_Terms(String company, String version) throws JSONException {
		initialize(company, version);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company,final String version) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 651, 408);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 634, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Save");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String po_term_code=textField.getText();
				String term_desc=textField_1.getText();
				String due_date=textField_2.getText();
				String disc=textField_3.getText();
				String cash_advce=textField_4.getText();
				String remark=textField_5.getText();
				String actve="";
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    String formattedDate = myDateObj.format(myFormatObj);  
				if(po_term_code.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please select or Enter PO Term Code");
				}
				else
				{
					if(chckbxNewCheckBox.isSelected())
					{
						actve="1";
					}
					else
					{
						actve="0";
					}
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					try {
						statement = con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_po_terms where po_term_code='"+po_term_code+"' ");
						if(!resultSet.next())
						{
							String query="insert "+company+".mr_po_terms(po_term_code,term_desc,due_days,term_disc,cash_advce,remark,status,created_date,modified_date)values('"+po_term_code+"','"+term_desc+"','"+due_date+"','"+disc+"','"+cash_advce+"','"+remark+"','"+actve+"','"+formattedDate+"','"+formattedDate+"')";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
						else
						{
							String query="update "+company+".mr_po_terms set term_desc='"+term_desc+"',due_days='"+due_date+"',term_disc='"+disc+"',cash_advce='"+cash_advce+"',remark='"+remark+"',status='"+actve+"',modified_date='"+formattedDate+"' where po_term_code='"+po_term_code+"' ";
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
		menuItem.setBounds(560, 11, 70, 48);
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(478, 11, 80, 48);
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
		menuItem_2.setBounds(418, 11, 60, 48);
		panel.add(menuItem_2);
		
		
		
		JLabel lblPurchaseTerms = new JLabel("Purchase Terms");
		lblPurchaseTerms.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPurchaseTerms.setBounds(10, 11, 230, 44);
		panel.add(lblPurchaseTerms);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 83, 634, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("PO Term Code :");
		lblNewLabel.setBounds(46, 107, 108, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(164, 104, 223, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new system_setups.Term_Chooser(company, version);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(418, 103, 57, 23);
		panel.add(btnNewButton);
		
		JLabel lblTermDescription = new JLabel("Term Description :");
		lblTermDescription.setBounds(46, 148, 108, 14);
		panel.add(lblTermDescription);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(164, 145, 223, 20);
		panel.add(textField_1);
		
		JLabel lblDueDays = new JLabel("Due Days :");
		lblDueDays.setBounds(46, 191, 108, 14);
		panel.add(lblDueDays);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(164, 188, 95, 20);
		panel.add(textField_2);
		
		JLabel lblDiscount = new JLabel("Discount %  :");
		lblDiscount.setBounds(46, 230, 108, 14);
		panel.add(lblDiscount);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(164, 227, 95, 20);
		panel.add(textField_3);
		
		JLabel lblCashAdvance = new JLabel("Cash Advance %  :");
		lblCashAdvance.setBounds(46, 269, 119, 14);
		panel.add(lblCashAdvance);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(164, 266, 95, 20);
		panel.add(textField_4);
		
		JLabel lblRemarks = new JLabel("Remarks  :");
		lblRemarks.setBounds(46, 309, 108, 14);
		panel.add(lblRemarks);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(164, 306, 300, 20);
		panel.add(textField_5);
		
		chckbxNewCheckBox = new JCheckBox("Active Term Code");
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setBackground(new Color(243, 229, 216));
		chckbxNewCheckBox.setBounds(164, 332, 178, 23);
		panel.add(chckbxNewCheckBox);
		
	}
	public static void get_po_term_edit(String company,String po_code) throws SQLException
	{
		Connection con=Dbsetup.dbconn.getConnection();
		Statement statement=con.createStatement();  
		ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_po_terms where po_term_code='"+po_code+"' ");
		if(resultSet.next())
		{
			textField.setText(resultSet.getString("po_term_code"));
			textField_1.setText(resultSet.getString("term_desc"));
			textField_2.setText(resultSet.getString("due_days"));
			textField_3.setText(resultSet.getString("term_disc"));
			textField_4.setText(resultSet.getString("cash_advce"));
			textField_5.setText(resultSet.getString("remark"));
			if(resultSet.getString("status").equals("1"))
			{
				chckbxNewCheckBox.setSelected(true);
			}
			else
			{
				chckbxNewCheckBox.setSelected(false);
			}
		}
		
	}
}
