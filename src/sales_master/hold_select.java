package sales_master;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.json.JSONException;

import CafePro.Mainpage;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class hold_select {

	static JFrame frmHoldInvoice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					hold_select window = new hold_select(null, null, null, null, null, null);
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
	public hold_select(String company, String username,String store_name,String posreg,Connection con, String version) {
		initialize(company, username,store_name, posreg, con, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 * @param posreg 
	 * @param store_name 
	 * @param username 
	 * @param company 
	 */
	private void initialize(final String company, final String username, final String store_name, final String posreg, final Connection con, final String version) {
		frmHoldInvoice = new JFrame();
		frmHoldInvoice.setTitle("Hold Invoice");
		frmHoldInvoice.setResizable(false);
		frmHoldInvoice.setAlwaysOnTop(true);
		frmHoldInvoice.getContentPane().setForeground(new Color(0, 0, 0));
		frmHoldInvoice.getContentPane().setBackground(Color.DARK_GRAY);
		frmHoldInvoice.setBounds(100, 100, 550, 350);
		frmHoldInvoice.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmHoldInvoice.getContentPane().setLayout(null);
		frmHoldInvoice.setLocationRelativeTo(null);
		frmHoldInvoice.setVisible(true);
		
		JButton btnNewButton = new JButton("<html>QUICK DINE IN<br>\u062A\u0646\u0627\u0648\u0644 \u0627\u0644\u0639\u0634\u0627\u0621 \u0628\u0633\u0631\u0639\u0629</html>");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setVisible(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmHoldInvoice.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.page_type="Kitchen KOT";
					PosscreenArabic.hold_inv=true;
					PosscreenArabic.hold_inv(company, username,store_name, posreg, con,"QUICK DINE IN",version,"","QUICK DINE IN",PosscreenArabic.textField_8.getText());
				}
				else
				{
					posscreen.page_type="Kitchen KOT";
					posscreen.hold_inv=true;
					posscreen.hold_inv(company, username,store_name, posreg, con,"QUICK DINE IN",version,"","QUICK DINE IN",posscreen.textField_8.getText(),"0");
				}
				
				
				
			}
		});
		btnNewButton.setBounds(29, 209, 150, 85);
		btnNewButton.setBackground(new Color(255, 255, 153));
		frmHoldInvoice.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("<html>HOME DELIVERY<br>\u062A\u0648\u0635\u064A\u0644 \u0645\u0646\u0632\u0644\u064A</html>");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setVisible(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
//				posscreen.page_type="Delivery";
//				posscreen.hold_inv=true;
//				posscreen.hold_inv(company, username,store_name, posreg, con,"");
//				frmHoldInvoice.setVisible(false);
				
				try {
					if(Mainpage.SoftLang.equals("Ar"))
					{
						if(!PosscreenArabic.btnNewButton.getText().equals("WALK IN"))
						{
							PosscreenArabic.page_type="Kitchen KOT";
							PosscreenArabic.hold_inv=true;
							PosscreenArabic.hold_inv(company, username,store_name, posreg, con,"HOME DELIVERY",version,"","HOME DELIVERY",PosscreenArabic.textField_8.getText());
						}
						else
						{
							new delivery_customer(company,version,"1", username, store_name, posreg, con,"hold_select");
						}
					}
					else
					{
						if(!posscreen.btnNewButton.getText().equals("WALK IN"))
						{
							posscreen.page_type="Kitchen KOT";
							posscreen.hold_inv=true;
							posscreen.hold_inv(company, username,store_name, posreg, con,"HOME DELIVERY",version,"","HOME DELIVERY",posscreen.textField_8.getText(),"0");
						}
						else
						{
							new delivery_customer(company,version,"1", username, store_name, posreg, con,"hold_select");
						}
					}
					
					
					frmHoldInvoice.setVisible(false);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(209, 201, 150, 85);
		btnNewButton_1.setBackground(new Color(255, 99, 71));
		frmHoldInvoice.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("<html>DINE IN<br>\u062A\u0646\u0627\u0648\u0644 \u0627\u0644\u0637\u0639\u0627\u0645 \u0641\u064A</html>");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(Mainpage.SoftLang.equals("Ar"))
					{
						if(PosscreenArabic.old_recipt.equals(""))
						{
							new dine_in(company, store_name, version, username, PosscreenArabic.reg_nme,"");
							frmHoldInvoice.setVisible(false);
						}
						else
						{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="select * from "+company+".mr_pos_hold_invoice where receipt_no='"+PosscreenArabic.textField_8.getText()+"' and reg_num='"+posreg+"' ";
							ResultSet resultSet = statement.executeQuery(query);
							String table_num = "";
							if(resultSet.next())
							{
								table_num=resultSet.getString("table_num");
							}
							frmHoldInvoice.setVisible(false);
							PosscreenArabic.page_type="Kitchen KOT";
							PosscreenArabic.hold_inv=true;
							PosscreenArabic.hold_inv(company, username,store_name, posreg, con,table_num,version,"","DINE IN",PosscreenArabic.textField_8.getText());
							
						}
					}
					else
					{
						if(posscreen.old_recipt.equals(""))
						{
							new dine_in(company, store_name, version, username, posscreen.reg_nme,"");
							frmHoldInvoice.setVisible(false);
						}
						else
						{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							String query="select * from "+company+".mr_pos_hold_invoice where receipt_no='"+posscreen.textField_8.getText()+"' and reg_num='"+posreg+"' ";
							ResultSet resultSet = statement.executeQuery(query);
							String table_num = "";
							if(resultSet.next())
							{
								table_num=resultSet.getString("table_num");
							}
							posscreen.page_type="Kitchen KOT";
							posscreen.hold_inv=true;
							frmHoldInvoice.setVisible(false);
							posscreen.hold_inv(company, username,store_name, posreg, con,table_num,version,"","DINE IN",posscreen.textField_8.getText(),"0");
						
						}
					}
					
					
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(54, 97, 150, 85);
		btnNewButton_2.setBackground(new Color(124, 252, 0));
		frmHoldInvoice.getContentPane().add(btnNewButton_2);
		
		JLabel lblSelectAType = new JLabel("Select a type");
		lblSelectAType.setForeground(Color.WHITE);
		lblSelectAType.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelectAType.setBounds(10, 12, 132, 50);
		frmHoldInvoice.getContentPane().add(lblSelectAType);
		
		JButton btnTakeAway = new JButton("<html>TAKE AWAY<br>\u064A\u0628\u0639\u062F</html>");
		btnTakeAway.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTakeAway.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				posscreen.page_type="Hold Invoice";
//				posscreen.hold_inv=true;
//				posscreen.hold_inv(company, username,store_name, posreg, con,"TA",version);
				
				try {
					new take_away(company,store_name, version,username,"");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				frmHoldInvoice.setVisible(false);
			}
		});
		btnTakeAway.setBackground(Color.ORANGE);
		btnTakeAway.setBounds(311, 97, 150, 85);
		frmHoldInvoice.getContentPane().add(btnTakeAway);
		
//		JButton btnPrintBill = new JButton("CHECK PRINT");
//		btnPrintBill.setFont(new Font("Tahoma", Font.BOLD, 13));
//		btnPrintBill.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				
//			}
//		});
//		btnPrintBill.setForeground(Color.WHITE);
//		btnPrintBill.setBackground(Color.BLUE);
//		btnPrintBill.setBounds(284, 226, 165, 90);
//		frmHoldInvoice.getContentPane().add(btnPrintBill);
	}
}
