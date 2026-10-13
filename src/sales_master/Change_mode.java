package sales_master;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.json.JSONException;

import CafePro.Mainpage;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Change_mode {

	static JFrame frmHoldInvoice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Change_mode window = new Change_mode(null, null, null, null, null, null,"");
					window.frmHoldInvoice.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Change_mode(String company, String username,String store_name,String posreg,Connection con, String version,String receipt) {
		initialize(company, username,store_name, posreg, con, version,receipt);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 * @param posreg 
	 * @param store_name 
	 * @param username 
	 * @param company 
	 */
	private void initialize(final String company, final String username, final String store_name, final String posreg, final Connection con, final String version, final String receipt) {
		frmHoldInvoice = new JFrame();
		frmHoldInvoice.setTitle("Hold Invoice");
		frmHoldInvoice.setResizable(false);
		
		frmHoldInvoice.getContentPane().setForeground(new Color(0, 0, 0));
		frmHoldInvoice.getContentPane().setBackground(Color.DARK_GRAY);
		frmHoldInvoice.setBounds(100, 100, 712, 350);
		frmHoldInvoice.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmHoldInvoice.getContentPane().setLayout(null);
		frmHoldInvoice.setLocationRelativeTo(null);
		frmHoldInvoice.setVisible(true);
		
		JButton btnNewButton = new JButton("QUICK DINE IN");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				change_mode(company,receipt,posreg,"QUICK DINE IN","QUICK DINE IN",store_name);
			}
		});
		btnNewButton.setBounds(22, 97, 150, 85);
		btnNewButton.setBackground(new Color(255, 255, 153));
		frmHoldInvoice.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("HOME DELIVERY");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				change_mode(company,receipt,posreg,"HOME DELIVERY","HOME DELIVERY",store_name);
			}
		});
		btnNewButton_1.setBounds(196, 97, 150, 85);
		btnNewButton_1.setBackground(new Color(255, 99, 71));
		frmHoldInvoice.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("DINE IN");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//change_mode(company,receipt,posreg,"DINE IN","DINE IN");
				try {
					new dine_in(company, store_name, "", username, receipt,"");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(370, 97, 150, 85);
		btnNewButton_2.setBackground(new Color(124, 252, 0));
		frmHoldInvoice.getContentPane().add(btnNewButton_2);
		
		JLabel lblSelectAType = new JLabel("Select a type");
		lblSelectAType.setForeground(Color.WHITE);
		lblSelectAType.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelectAType.setBounds(10, 12, 132, 50);
		frmHoldInvoice.getContentPane().add(lblSelectAType);
		
		JButton btnTakeAway = new JButton("TAKE AWAY");
		btnTakeAway.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTakeAway.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				change_mode(company,receipt,posreg,"TAKE AWAY","TAKE AWAY",store_name);
			}
		});
		btnTakeAway.setBackground(Color.ORANGE);
		btnTakeAway.setBounds(544, 97, 150, 85);
		frmHoldInvoice.getContentPane().add(btnTakeAway);
		
		frmHoldInvoice.setAlwaysOnTop(true);
	}
	
	private void change_mode(String company,String receipt,String reg_num,String mode,String table_num,String store_name)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="select * from "+company+".mr_pos_hold_invoice where receipt_no='"+receipt+"' and reg_num='"+reg_num+"'";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				String update="update "+company+".mr_pos_hold_invoice set mode='"+mode+"',table_num='"+table_num+"',delivery_user=''  where receipt_no='"+receipt+"' and reg_num='"+reg_num+"'";
				statement.executeUpdate(update);
				JOptionPane.showMessageDialog(frmHoldInvoice, "Saved Succesfully");
				frmHoldInvoice.setVisible(false);
				unhold.textField.setText("");
				unhold.comboBox.setSelectedItem("ALL");
				if(Mainpage.SoftLang.equals("Ar"))
				{
					unhold.get_itemdisp(company,unhold.textField.getText(), PosscreenArabic.reg_nme,unhold.comboBox.getSelectedItem().toString(),store_name);
				}
				else
				{
					unhold.get_itemdisp(company,unhold.textField.getText(), posscreen.reg_nme,unhold.comboBox.getSelectedItem().toString(),store_name);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
