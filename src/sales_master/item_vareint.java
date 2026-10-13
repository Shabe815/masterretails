package sales_master;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import CafePro.Mainpage;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class item_vareint {

	static JFrame frmHoldInvoice;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	
	
	private String sp1="0";
	private String sp2="0";
	private String sp3="0";
	private String sp4="0";
	private String sp5="0";
	private String sp6="0";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					item_vareint window = new item_vareint(null, null,  null, null,null,null,null,null);
					window.frmHoldInvoice.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param store 
	 * @param type 
	 */
	public item_vareint(String company, String username,String posreg,Connection con,String itemcode,String itemname, String type, String store) {
		initialize(company, username, posreg, con,itemcode,itemname, type, store);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 * @param posreg 
	 * @param store_name 
	 * @param username 
	 * @param company 
	 */
	private void initialize(final String company, final String username,final String posreg, final Connection con,final String itemcode,final String itemname, final String type,final String store) {
		frmHoldInvoice = new JFrame();
		frmHoldInvoice.setTitle("Item Vareint");
		frmHoldInvoice.setResizable(false);
		frmHoldInvoice.setAlwaysOnTop(true);
		frmHoldInvoice.getContentPane().setForeground(new Color(0, 0, 0));
		frmHoldInvoice.getContentPane().setBackground(Color.DARK_GRAY);
		frmHoldInvoice.setBounds(100, 100, 712, 350);
		frmHoldInvoice.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmHoldInvoice.getContentPane().setLayout(null);
		frmHoldInvoice.setLocationRelativeTo(null);
		frmHoldInvoice.setVisible(true);
		
		btnNewButton = new JButton("");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String[] buttonvalue= btnNewButton.getText().split("- Dhs");
					
					frmHoldInvoice.setVisible(false);
					Statement statement=con.createStatement(); 
					String barcode="";
					String button_unit="SELECT * FROM "+company+".mr_tmp_item_master where activeForSale='1' and item_code='"+itemcode+"' and unit='"+buttonvalue[0]+"' ";
					ResultSet resultSet2= statement.executeQuery(button_unit);
					if(resultSet2.next())
					{
						barcode=resultSet2.getString("barcode");
					}
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.description=itemname+"-"+buttonvalue[0];
						PosscreenArabic.stats=true;
						PosscreenArabic.sellprice=sp1;
						PosscreenArabic.validate_barcode(company, barcode, username, store, type,con);
					}
					else
					{
						//posscreen.description=itemname+"-"+buttonvalue[0];
						posscreen.description=itemname;
						posscreen.stats=true;
						posscreen.sellprice=sp1;
						posscreen.validate_barcode(company, barcode, username, store, type,con);
					}
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmHoldInvoice, e.getMessage());
				}
			}
		});
		btnNewButton.setBounds(26, 100, 200, 85);
		btnNewButton.setBackground(new Color(255, 255, 153));
		frmHoldInvoice.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String[] buttonvalue= btnNewButton_1.getText().split("- Dhs");
					
					frmHoldInvoice.setVisible(false);
					String barcode="";
					Statement statement=con.createStatement(); 
					String button_unit="SELECT * FROM "+company+".mr_tmp_item_master where activeForSale='1' and item_code='"+itemcode+"' and unit='"+buttonvalue[0]+"' ";
					ResultSet resultSet2= statement.executeQuery(button_unit);
					if(resultSet2.next())
					{
						barcode=resultSet2.getString("barcode");
					}
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.description=itemname+"-"+buttonvalue[0];
						//posscreen.description=itemname+"-"+btnNewButton_1.getText();
						PosscreenArabic.stats=true;
						PosscreenArabic.sellprice=sp2;
						PosscreenArabic.validate_barcode(company, barcode, username, store, type,con);
					}
					else
					{
						//posscreen.description=itemname+"-"+buttonvalue[0];
						posscreen.description=itemname;
						//posscreen.description=itemname+"-"+btnNewButton_1.getText();
						posscreen.stats=true;
						posscreen.sellprice=sp2;
						posscreen.validate_barcode(company, barcode, username, store, type,con);
					}
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmHoldInvoice, e.getMessage());
				}
			}
		});
		btnNewButton_1.setBounds(252, 100, 200, 85);
		btnNewButton_1.setBackground(new Color(255, 99, 71));
		frmHoldInvoice.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String[] buttonvalue= btnNewButton_2.getText().split("- Dhs");
					
					frmHoldInvoice.setVisible(false);
					Statement statement=con.createStatement(); 
					String barcode="";
					String button_unit="SELECT * FROM "+company+".mr_tmp_item_master where activeForSale='1' and item_code='"+itemcode+"' and unit='"+buttonvalue[0]+"' ";
					ResultSet resultSet2= statement.executeQuery(button_unit);
					if(resultSet2.next())
					{
						barcode=resultSet2.getString("barcode");
					}
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.description=itemname+"-"+buttonvalue[0];
						//posscreen.description=itemname+"-"+btnNewButton_2.getText();
						PosscreenArabic.stats=true;
						PosscreenArabic.sellprice=sp3;
						PosscreenArabic.validate_barcode(company, barcode, username, store, type,con);
					}
					else
					{
						//posscreen.description=itemname+"-"+buttonvalue[0];
						posscreen.description=itemname;
						//posscreen.description=itemname+"-"+btnNewButton_2.getText();
						posscreen.stats=true;
						posscreen.sellprice=sp3;
						posscreen.validate_barcode(company, barcode, username, store, type,con);
					}
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmHoldInvoice, e.getMessage());
				}
			}
		});
		btnNewButton_2.setBounds(478, 100, 200, 85);
		btnNewButton_2.setBackground(new Color(124, 252, 0));
		frmHoldInvoice.getContentPane().add(btnNewButton_2);
		
		JLabel lblSelectAType = new JLabel(itemname);
		lblSelectAType.setForeground(Color.WHITE);
		lblSelectAType.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelectAType.setBounds(10, 25, 686, 50);
		frmHoldInvoice.getContentPane().add(lblSelectAType);
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String[] buttonvalue= button.getText().split("- Dhs");
					
					frmHoldInvoice.setVisible(false);
					String barcode="";
					Statement statement=con.createStatement(); 
					String button_unit="SELECT * FROM "+company+".mr_tmp_item_master where activeForSale='1' and item_code='"+itemcode+"' and unit='"+buttonvalue[0]+"' ";
					ResultSet resultSet2= statement.executeQuery(button_unit);
					if(resultSet2.next())
					{
						barcode=resultSet2.getString("barcode");
					}
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.description=itemname+"-"+buttonvalue[0];
						//posscreen.description=itemname+"-"+btnNewButton_2.getText();
						PosscreenArabic.stats=true;
						PosscreenArabic.sellprice=sp4;
						PosscreenArabic.validate_barcode(company, barcode, username, store, type,con);
					}
					else
					{
						//posscreen.description=itemname+"-"+buttonvalue[0];
						posscreen.description=itemname;
						//posscreen.description=itemname+"-"+btnNewButton_2.getText();
						posscreen.stats=true;
						posscreen.sellprice=sp4;
						posscreen.validate_barcode(company, barcode, username, store, type,con);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmHoldInvoice, e.getMessage());
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBackground(new Color(255, 255, 153));
		button.setBounds(26, 210, 200, 85);
		frmHoldInvoice.getContentPane().add(button);
		
		button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String[] buttonvalue= button_1.getText().split("- Dhs");
					
					frmHoldInvoice.setVisible(false);
					String barcode="";
					Statement statement=con.createStatement(); 
					String button_unit="SELECT * FROM "+company+".mr_tmp_item_master where activeForSale='1' and item_code='"+itemcode+"' and unit='"+buttonvalue[0]+"' ";
					ResultSet resultSet2= statement.executeQuery(button_unit);
					if(resultSet2.next())
					{
						barcode=resultSet2.getString("barcode");
					}
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.description=itemname+"-"+buttonvalue[0];
						//posscreen.description=itemname+"-"+btnNewButton_2.getText();
						PosscreenArabic.stats=true;
						PosscreenArabic.sellprice=sp5;
						PosscreenArabic.validate_barcode(company, barcode, username, store, type,con);
					}
					else
					{
						//posscreen.description=itemname+"-"+buttonvalue[0];
						posscreen.description=itemname;
						//posscreen.description=itemname+"-"+btnNewButton_2.getText();
						posscreen.stats=true;
						posscreen.sellprice=sp5;
						posscreen.validate_barcode(company, barcode, username, store, type,con);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmHoldInvoice, e.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_1.setBackground(new Color(255, 99, 71));
		button_1.setBounds(252, 210, 200, 85);
		frmHoldInvoice.getContentPane().add(button_1);
		
		button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String[] buttonvalue= button_2.getText().split("- Dhs");
					
					frmHoldInvoice.setVisible(false);
					Statement statement=con.createStatement(); 
					String barcode="";
					String button_unit="SELECT * FROM "+company+".mr_tmp_item_master where activeForSale='1' and item_code='"+itemcode+"' and unit='"+buttonvalue[0]+"' ";
					ResultSet resultSet2= statement.executeQuery(button_unit);
					if(resultSet2.next())
					{
						barcode=resultSet2.getString("barcode");
					}
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.description=itemname+"-"+buttonvalue[0];
						//posscreen.description=itemname+"-"+btnNewButton_2.getText();
						PosscreenArabic.stats=true;
						PosscreenArabic.sellprice=sp6;
						PosscreenArabic.validate_barcode(company, barcode, username, store, type,con);
					}
					else
					{
						//posscreen.description=itemname+"-"+buttonvalue[0];
						//posscreen.description=itemname+"-"+btnNewButton_2.getText();
						posscreen.description=itemname;
						posscreen.stats=true;
						posscreen.sellprice=sp6;
						posscreen.validate_barcode(company, barcode, username, store, type,con);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmHoldInvoice, e.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_2.setBackground(new Color(124, 252, 0));
		button_2.setBounds(478, 210, 200, 85);
		frmHoldInvoice.getContentPane().add(button_2);
		
		try {
			int a=1;
			Statement statement1=con.createStatement();  
			ResultSet resultSet = statement1.executeQuery("select * from "+company+".mr_tmp_item_master where item_code='"+itemcode+"' ");
			while(resultSet.next())
			{
				if(a==1)
				{
					String unit1=resultSet.getString("unit");
					sp1=resultSet.getString("sp");
					btnNewButton.setText(unit1+"- Dhs "+sp1);
					btnNewButton_1.setVisible(false);
					btnNewButton_2.setVisible(false);
					button.setVisible(false);
					button_1.setVisible(false);
					button_2.setVisible(false);
				}
				if(a==2)
				{
					String unit2=resultSet.getString("unit");
					sp2=resultSet.getString("sp");
					btnNewButton_1.setText(unit2+"- Dhs "+sp2);
					btnNewButton_1.setVisible(true);
					btnNewButton_2.setVisible(false);
					button.setVisible(false);
					button_1.setVisible(false);
					button_2.setVisible(false);
				}
				if(a==3)
				{
					String unit3=resultSet.getString("unit");
					sp3=resultSet.getString("sp");
					btnNewButton_2.setText(unit3+"- Dhs "+sp3);
					btnNewButton_1.setVisible(true);
					btnNewButton_2.setVisible(true);
					button.setVisible(false);
					button_1.setVisible(false);
					button_2.setVisible(false);
				}
				if(a==4)
				{
					String unit4=resultSet.getString("unit");
					sp4=resultSet.getString("sp");
					button.setText(unit4+"- Dhs "+sp4);
					btnNewButton_1.setVisible(true);
					btnNewButton_2.setVisible(true);
					button.setVisible(true);
					button_1.setVisible(false);
					button_2.setVisible(false);
				}
				if(a==5)
				{
					String unit5=resultSet.getString("unit");
					sp5=resultSet.getString("sp");
					button_1.setText(unit5+"- Dhs "+sp5);
					btnNewButton_1.setVisible(true);
					btnNewButton_2.setVisible(true);
					button.setVisible(true);
					button_1.setVisible(true);
					button_2.setVisible(false);
				}
				if(a==6)
				{
					String unit6=resultSet.getString("unit");
					sp6=resultSet.getString("sp"+"- Dhs "+sp6);
					button_2.setText(unit6);
					btnNewButton_1.setVisible(true);
					btnNewButton_2.setVisible(true);
					button.setVisible(true);
					button_1.setVisible(true);
					button_2.setVisible(true);
				}
				a++;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmHoldInvoice, e.getMessage());
		}
		
	}
}
