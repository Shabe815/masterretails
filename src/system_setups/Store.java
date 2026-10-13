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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import org.json.JSONException;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Store {

	private JFrame frame;
	private DefaultTableModel model;
	static JTextField txtS;
	private static JTextField textField_2;
	private static JTextField textField_4;
	private static JTextField textField_5;
	private static JTextField textField_6;
	private static JTextField textField_8;
	private static JTextField textField_1;
	private static JTextField textField_3;
	private static JTextField textField_7;
	private static JTextField textField_9;
	private static JTextField textField_10;
	private static JTextField textField_11;
	private static JTextField textField_12;
	private static JTextField textField_13;
	private static JTextField textField_14;
	private static JTextField textField_15;
	private static JCheckBox chckbxNewCheckBox;
	private static JCheckBox chckbxApplyItemDiscount;
	private static JCheckBox chckbxChangeCustomerAt;
	private static JCheckBox chckbxApplyCustomerDiscount;
	private static  JCheckBox chckbxDonotPerformEod;
	private static JCheckBox chckbxUseLoyalityProgram;
	private static JCheckBox chckbxConsolidatedPosLines;
	private static JCheckBox chckbxAllowNegativeQuantity;
	private static JCheckBox chckbxNoofDecimalTo;
	private static JCheckBox chckbxAllowRoundoff;
	private static JButton btnNewButton_1;
	private static JTextField textField;
	private static JCheckBox chckbxApplyStoreCredit;
	private static JComboBox<String> comboBox;
	private static JCheckBox chckbxApplyStore;
	private static JCheckBox chckbxAssemblyOf;
	private static JCheckBox chckbxEnableDelivery;
	private static JCheckBox chckbxPoCost;
	private static JCheckBox chckbxItemCost;
	private static JCheckBox chckbxSdPrice;
	private static JCheckBox chckbxCodeAllowChar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Store window = new Store("mr_pro","admin", "","Master Retails","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param string3 
	 * @param string2 
	 * @param string 
	 */
	public Store(String company,String username,String version,String company_name, String language) {
		initialize(company, username, version,company_name,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 */
	private void initialize(final String company,final String username,final String version,String company_name,final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JLabel lblCompanyName = new JLabel("Store Code :");
		lblCompanyName.setBounds(91, 79, 108, 21);
		lblCompanyName.setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.getContentPane().add(lblCompanyName);
		
		Image shop = new ImageIcon(this.getClass().getResource("/shop.png")).getImage();
		JLabel lblCompanySetup = new JLabel("Store");
		lblCompanySetup.setBounds(21, 11, 228, 44);
		lblCompanySetup.setIcon(new ImageIcon(shop));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 980, 2);
		frame.getContentPane().add(separator);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
	    menuItem.setBorder(null);
	    menuItem.setIcon(new ImageIcon(exit));
	    menuItem.setBackground(new Color(243, 229, 216));
	    menuItem.setBounds(838, 7, 60, 48);
	    frame.getContentPane().add(menuItem);
	    
	    JMenuItem menuItem_2 = new JMenuItem("Save");
	    menuItem_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		String code=txtS.getText();
	    		String short_name=company;
	    		String addr1=textField_4.getText();
				String addr2=textField_3.getText();
				String addr3=textField_5.getText();
				String addr4=textField_6.getText();
				String city=textField_7.getText();
				String country=textField_9.getText();
				String web=textField_13.getText();
				String email=textField_12.getText();
				String trn=textField_2.getText();
				String ph=textField_11.getText();
				String lline=textField_10.getText();
				String barcode_flag="";
				String ApplyItemDiscount="";
				String ChangeCustomerAt="";
				String ApplyCustomerDiscount="";
				String DonotPerformEod="";
				String UseLoyalityProgram="";
				String ConsolidatedPosLines="";
				String NoofDecimal="";
				String AllowNegativeQuantity="";
				String flag="";
				String decimal="";
				String ApplyStoreCredit="";
				String AllowRoundoff;
				String ApplychckbxApplyStore="";
				String ApplychckbxAssemblyOf="";
				String sd_active="";
				String acc_active="";
				String PoCost="";
				String SdPricelock="";
				String CodeAllowChar="";
				if(chckbxNewCheckBox.isSelected())
				{
					barcode_flag="1";
					flag=textField_14.getText();
				}
				else
				{
					barcode_flag="0";
					flag="0";
				}
				if(chckbxPoCost.isSelected())
				{
					PoCost="1";
				}
				else
				{
					PoCost="0";
				}
				if(chckbxApplyItemDiscount.isSelected())
				{
					ApplyItemDiscount="1";
				}
				else
				{
					ApplyItemDiscount="0";
				}
				if(chckbxChangeCustomerAt.isSelected())
				{
					ChangeCustomerAt="1";
				}
				else
				{
					ChangeCustomerAt="0";
				}
				if(chckbxApplyCustomerDiscount.isSelected())
				{
					ApplyCustomerDiscount="1";
				}
				else
				{
					ApplyCustomerDiscount="0";
				}
				if(chckbxAssemblyOf.isSelected())
				{
					ApplychckbxAssemblyOf="1";
				}
				else
				{
					ApplychckbxAssemblyOf="0";
				}
				
				if(chckbxApplyStore.isSelected())
				{
					ApplychckbxApplyStore="1";
				}
				else
				{
					ApplychckbxApplyStore="0";
				}
				
				if(chckbxDonotPerformEod.isSelected())
				{
					DonotPerformEod="1";
				}
				else
				{
					DonotPerformEod="0";
				}
				if(chckbxUseLoyalityProgram.isSelected())
				{
					UseLoyalityProgram="1";
				}
				else
				{
					UseLoyalityProgram="0";
				}
				if(chckbxConsolidatedPosLines.isSelected())
				{
					ConsolidatedPosLines="1";
				}
				else
				{
					ConsolidatedPosLines="0";
				}
				if(chckbxAllowNegativeQuantity.isSelected())
				{
					AllowNegativeQuantity="1";
				}
				else
				{
					AllowNegativeQuantity="0";
				}
				if(chckbxApplyStoreCredit.isSelected())
				{
					ApplyStoreCredit="1";
				}
				else
				{
					ApplyStoreCredit="0";
				}
				if(chckbxAllowRoundoff.isSelected())
				{
					AllowRoundoff="1";
				}
				else
				{
					AllowRoundoff="0";
				}
				if(chckbxNoofDecimalTo.isSelected())
				{
					NoofDecimal="1";
					decimal=textField_15.getText();
				}
				else
				{
					NoofDecimal="0";
					decimal="0";
				}
				if(chckbxEnableDelivery.isSelected())
				{
					sd_active="1";
				}
				else
				{
					sd_active="0";
				}
				if(chckbxSdPrice.isSelected())
				{
					SdPricelock="1";
				}
				else
				{
					SdPricelock="0";
				}
				
				if(chckbxCodeAllowChar.isSelected())
				{
					CodeAllowChar="1";
				}
				else
				{
					CodeAllowChar="0";
				}
				
				
				if(chckbxItemCost.isSelected())
				{
					acc_active="1";
				}
				else
				{
					acc_active="0";
				}
				
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    String formattedDate = myDateObj.format(myFormatObj);  
			    Connection con=Dbsetup.dbconn.getConnection();
				Statement statement;
				try {
					statement = con.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store where store_id='"+code+"' ");
					if(resultSet.next())
					{
						String query="update "+company+".mr_store set acc_active='"+acc_active+"',sd_active='"+sd_active+"',assembly_of_item='"+ApplychckbxAssemblyOf+"',change_hold_type='"+ApplychckbxApplyStore+"',barcode_label='"+comboBox.getSelectedItem().toString()+"',apply_store_credit='"+ApplyStoreCredit+"',store_short_name='"+short_name+"',addr1='"+addr1+"',addr2='"+addr2+"',addr3='"+addr3+"',addr4='"+addr4+"',city='"+city+"',country='"+country+"',web='"+web+"',ph='"+ph+"',email='"+email+"',trn='"+trn+"',land_line='"+lline+"',`modified_date`='"+formattedDate+"',barcode_flag='"+barcode_flag+"',flag='"+flag+"',ApplyItemDiscount='"+ApplyItemDiscount+"',ChangeCustomerAt='"+ChangeCustomerAt+"',ApplyCustomerDiscount='"+ApplyCustomerDiscount+"',DonotPerformEod='"+DonotPerformEod+"',UseLoyalityProgram='"+UseLoyalityProgram+"',ConsolidatedPosLines='"+ConsolidatedPosLines+"',NoofDecimal='"+NoofDecimal+"',decimal_count='"+decimal+"',AllowNegativeQuantity='"+AllowNegativeQuantity+"',modified_date='"+formattedDate+"',allow_roundoff='"+AllowRoundoff+"',POcost='"+PoCost+"',SdPricelock='"+SdPricelock+"',ItemCodeAllowChar='"+CodeAllowChar+"' WHERE id='"+resultSet.getString("id")+"' ";
						statement.executeUpdate(query);
						JOptionPane.showMessageDialog(frame, "Saved Successfully");
					}
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
	    	}
	    });
	    
	    menuItem_2.setBorder(null);
	    menuItem_2.setIcon(new ImageIcon(save));
	    menuItem_2.setBackground(new Color(243, 229, 216));
	    menuItem_2.setBounds(902, 7, 70, 48);
	    frame.getContentPane().add(menuItem_2);
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
		   
		};
		    model.addColumn("Code"); 
		    model.addColumn("Acccount Name"); 
			model.addColumn("Account Group"); 
			model.addColumn("Account Type"); 
			model.addRow(new Object[]{"","","",""});
		    
		    txtS = new JTextField();
		    txtS.setEditable(false);
		    txtS.setText("S01");
		    txtS.setFont(new Font("Tahoma", Font.BOLD, 12));
		    txtS.setBackground(Color.WHITE);
		    txtS.setBounds(193, 79, 113, 25);
		    frame.getContentPane().add(txtS);
		    txtS.setColumns(10);
		    
		    JLabel lblNewLabel = new JLabel("New label");
		    lblNewLabel.setBounds(478, 206, 4, -6);
		    frame.getContentPane().add(lblNewLabel);
		    
		    JLabel lblPoNumber = new JLabel("Store Short Name:");
		    lblPoNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		    lblPoNumber.setBounds(416, 80, 113, 21);
		    frame.getContentPane().add(lblPoNumber);
		    
		    textField_8 = new JTextField(company_name);
		    textField_8.setEditable(false);
		    textField_8.setBounds(550, 78, 295, 25);
		    frame.getContentPane().add(textField_8);
		    textField_8.setColumns(10);
		    
		    JSeparator separator_1 = new JSeparator();
		    separator_1.setBounds(0, 142, 914, 2);
		    frame.getContentPane().add(separator_1);
		    
		    JSeparator separator_2 = new JSeparator();
		    separator_2.setOrientation(SwingConstants.VERTICAL);
		    separator_2.setBounds(480, 142, 4, 450);
		    frame.getContentPane().add(separator_2);
		    
		    Image binoculars = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		    JButton btnNewButton = new JButton("");
		    btnNewButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		try {
						new store_list( company,"store", version);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    });
		    btnNewButton.setIcon(new ImageIcon(binoculars));
		    btnNewButton.setBounds(317, 79, 50, 25);
		    frame.getContentPane().add(btnNewButton);
		    
		    btnNewButton_1 = new JButton("Add Paycodes");
		    btnNewButton_1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(!textField.getText().equals(""))
		    		{
		    			try {
							new Paycodes(company, txtS.getText(), version, language);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
		    		
		    	}
		    });
		    btnNewButton_1.setBounds(553, 541, 128, 38);
		    frame.getContentPane().add(btnNewButton_1);
		    
		    JButton btnLinkAccounts = new JButton("Link Accounts");
		    btnLinkAccounts.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		new Store_acc_sel(company, version,txtS.getText(), language);
		    	}
		    });
		    btnLinkAccounts.setBounds(714, 541, 131, 38);
		    frame.getContentPane().add(btnLinkAccounts);
		    
		    JPanel panel = new JPanel();
		    panel.setBounds(500, 142, 472, 403);
		    frame.getContentPane().add(panel);
		    panel.setBackground(new Color(243, 229, 216));
		    panel.setLayout(null);
		    
		    chckbxNewCheckBox= new JCheckBox("1. Random Barcode Flag");
		    chckbxNewCheckBox.setBounds(12, 9, 157, 23);
		    panel.add(chckbxNewCheckBox);
		    chckbxNewCheckBox.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(chckbxNewCheckBox.isSelected())
				    {
				    	textField_14.setEnabled(true);
				    }
				    else
				    {
				    	textField_14.setEnabled(false);
				    }
		    	}
		    });
		    chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxNewCheckBox.setBackground(new Color(243, 229, 216));
		    
		    textField_14 = new JTextField();
		    textField_14.setBounds(177, 10, 31, 20);
		    panel.add(textField_14);
		    textField_14.setEnabled(false);
		    textField_14.setText("99");
		    
		    textField_14.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    textField_14.setColumns(10);
		    
		    chckbxApplyItemDiscount = new JCheckBox("2. Apply Item Discount");
		    chckbxApplyItemDiscount.setBounds(266, 9, 206, 23);
		    panel.add(chckbxApplyItemDiscount);
		    chckbxApplyItemDiscount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxApplyItemDiscount.setBackground(new Color(243, 229, 216));
		    
		    chckbxChangeCustomerAt = new JCheckBox("3. Change Customer at POS");
		    chckbxChangeCustomerAt.setBounds(12, 41, 218, 23);
		    panel.add(chckbxChangeCustomerAt);
		    chckbxChangeCustomerAt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxChangeCustomerAt.setBackground(new Color(243, 229, 216));
		    
		    chckbxApplyCustomerDiscount = new JCheckBox("4. Apply Customer Discount");
		    chckbxApplyCustomerDiscount.setBounds(266, 41, 206, 23);
		    panel.add(chckbxApplyCustomerDiscount);
		    chckbxApplyCustomerDiscount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxApplyCustomerDiscount.setBackground(new Color(243, 229, 216));
		    
		    chckbxDonotPerformEod = new JCheckBox("5. Donot Perform EOD if HOLDs are Pending");
		    chckbxDonotPerformEod.setBounds(12, 73, 282, 23);
		    panel.add(chckbxDonotPerformEod);
		    chckbxDonotPerformEod.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxDonotPerformEod.setBackground(new Color(243, 229, 216));
		    
		    chckbxUseLoyalityProgram = new JCheckBox("6. Fast Item Screen will close after each Transaction");
		    chckbxUseLoyalityProgram.setBounds(12, 105, 383, 23);
		    panel.add(chckbxUseLoyalityProgram);
		    chckbxUseLoyalityProgram.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxUseLoyalityProgram.setBackground(new Color(243, 229, 216));
		    
		    chckbxConsolidatedPosLines = new JCheckBox("8. Consolidated POS lines for Normal Items");
		    chckbxConsolidatedPosLines.setBounds(12, 169, 282, 23);
		    panel.add(chckbxConsolidatedPosLines);
		    chckbxConsolidatedPosLines.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxConsolidatedPosLines.setBackground(new Color(243, 229, 216));
		    
		    chckbxNoofDecimalTo= new JCheckBox("9. No.of Decimal to be Used");
		    chckbxNoofDecimalTo.setBounds(12, 201, 199, 23);
		    panel.add(chckbxNoofDecimalTo);
		    chckbxNoofDecimalTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxNoofDecimalTo.setBackground(new Color(243, 229, 216));
		    
		    textField_15 = new JTextField();
		    textField_15.setBounds(218, 194, 31, 22);
		    panel.add(textField_15);
		    textField_15.setColumns(10);
		    
		    chckbxAllowNegativeQuantity = new JCheckBox("10. Allow Negative Quantity Sale");
		    chckbxAllowNegativeQuantity.setBounds(12, 233, 282, 23);
		    panel.add(chckbxAllowNegativeQuantity);
		    chckbxAllowNegativeQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxAllowNegativeQuantity.setBackground(new Color(243, 229, 216));
		    
		    chckbxApplyStoreCredit = new JCheckBox("11. Apply Store Credit");
		    chckbxApplyStoreCredit.setBounds(12, 265, 199, 23);
		    panel.add(chckbxApplyStoreCredit);
		    chckbxApplyStoreCredit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxApplyStoreCredit.setBackground(new Color(243, 229, 216));
		    
		    JLabel lblScaleBarcodeType = new JLabel("13. Scale Barcode Type");
		    lblScaleBarcodeType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    lblScaleBarcodeType.setBounds(12, 297, 136, 23);
		    panel.add(lblScaleBarcodeType);
		    
		    comboBox = new JComboBox<String>();
		    comboBox.setBounds(158, 294, 176, 22);
		    panel.add(comboBox);
		    comboBox.addItem("Qty");
		    comboBox.addItem("Price");
		    
		    chckbxAllowRoundoff = new JCheckBox("7. Allow Roundoff");
		    chckbxAllowRoundoff.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxAllowRoundoff.setBounds(12, 137, 282, 23);
		    panel.add(chckbxAllowRoundoff);
		    chckbxAllowRoundoff.setBackground(new Color(243, 229, 216));
		    
		    chckbxApplyStore = new JCheckBox("12. Change Hold Type in POS");
		    chckbxApplyStore.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxApplyStore.setBackground(new Color(243, 229, 216));
		    chckbxApplyStore.setBounds(258, 265, 206, 23);
		    panel.add(chckbxApplyStore);
		    
		    chckbxAssemblyOf = new JCheckBox("14. Assembly of Item");
		    chckbxAssemblyOf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxAssemblyOf.setBounds(12, 329, 168, 23);
		    chckbxAssemblyOf.setBackground(new Color(243, 229, 216));
		    panel.add(chckbxAssemblyOf);
		    
		    chckbxEnableDelivery = new JCheckBox("16. Enable Delivery Note");
		    chckbxEnableDelivery.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxEnableDelivery.setBackground(new Color(243, 229, 216));
		    chckbxEnableDelivery.setBounds(12, 361, 168, 23);
		    panel.add(chckbxEnableDelivery);
		    
		    chckbxPoCost = new JCheckBox("15. PO Cost update in History");
		    chckbxPoCost.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxPoCost.setBackground(new Color(243, 229, 216));
		    chckbxPoCost.setBounds(258, 329, 213, 23);
		    panel.add(chckbxPoCost);
		    
		    chckbxItemCost = new JCheckBox("17. Item Cost With Tax");
		    chckbxItemCost.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxItemCost.setBackground(new Color(243, 229, 216));
		    chckbxItemCost.setBounds(258, 361, 168, 23);
		    panel.add(chckbxItemCost);
		    
		    chckbxSdPrice = new JCheckBox("18. SD Price Lock");
		    chckbxSdPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxSdPrice.setBackground(new Color(243, 229, 216));
		    chckbxSdPrice.setBounds(298, 232, 168, 23);
		    panel.add(chckbxSdPrice);
		    
		    chckbxCodeAllowChar = new JCheckBox("19. Allow char in itemcode");
		    chckbxCodeAllowChar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    chckbxCodeAllowChar.setBackground(new Color(243, 229, 216));
		    chckbxCodeAllowChar.setBounds(273, 202, 199, 23);
		    panel.add(chckbxCodeAllowChar);
		    
		    JPanel panel_1 = new JPanel();
		    panel_1.setBounds(10, 142, 463, 403);
		    frame.getContentPane().add(panel_1);
		    panel_1.setBackground(new Color(243, 229, 216));
		    panel_1.setLayout(null);
		    
		    textField_1 = new JTextField(company_name);
		    textField_1.setBounds(108, 13, 351, 20);
		    panel_1.add(textField_1);
		    textField_1.setEditable(false);
		    textField_1.setColumns(10);
		    
		    textField_2 = new JTextField();
		    textField_2.setBounds(108, 45, 277, 20);
		    panel_1.add(textField_2);
		    textField_2.setColumns(10);
		    
		    textField_4 = new JTextField();
		    textField_4.setBounds(108, 76, 277, 20);
		    panel_1.add(textField_4);
		    textField_4.setColumns(10);
		    
		    textField_3 = new JTextField();
		    textField_3.setBounds(108, 107, 277, 20);
		    panel_1.add(textField_3);
		    textField_3.setColumns(10);
		    
		    textField_5 = new JTextField();
		    textField_5.setBounds(108, 136, 277, 20);
		    panel_1.add(textField_5);
		    textField_5.setColumns(10);
		    
		    textField_6 = new JTextField();
		    textField_6.setBounds(108, 167, 277, 20);
		    panel_1.add(textField_6);
		    textField_6.setColumns(10);
		    
		    textField_7 = new JTextField();
		    textField_7.setBounds(108, 198, 277, 20);
		    panel_1.add(textField_7);
		    textField_7.setColumns(10);
		    
		    textField_9 = new JTextField();
		    textField_9.setBounds(108, 229, 277, 20);
		    panel_1.add(textField_9);
		    textField_9.setColumns(10);
		    
		    textField_10 = new JTextField();
		    textField_10.setBounds(108, 260, 277, 20);
		    panel_1.add(textField_10);
		    textField_10.setColumns(10);
		    
		    textField_11 = new JTextField();
		    textField_11.setBounds(108, 293, 277, 20);
		    panel_1.add(textField_11);
		    textField_11.setColumns(10);
		    
		    textField_12 = new JTextField();
		    textField_12.setBounds(108, 324, 277, 20);
		    panel_1.add(textField_12);
		    textField_12.setColumns(10);
		    
		    textField_13 = new JTextField();
		    textField_13.setBounds(108, 355, 277, 20);
		    panel_1.add(textField_13);
		    textField_13.setColumns(10);
		    
		    JLabel lblWebsite = new JLabel("Website :");
		    lblWebsite.setBounds(12, 358, 73, 14);
		    panel_1.add(lblWebsite);
		    
		    JLabel lblEmail = new JLabel("Email :");
		    lblEmail.setBounds(12, 327, 73, 14);
		    panel_1.add(lblEmail);
		    
		    JLabel lblMobilePhone = new JLabel("Mobile Phone :");
		    lblMobilePhone.setBounds(12, 296, 86, 14);
		    panel_1.add(lblMobilePhone);
		    
		    JLabel lblLandLine = new JLabel("Land Line :");
		    lblLandLine.setBounds(12, 265, 73, 14);
		    panel_1.add(lblLandLine);
		    
		    JLabel lblCountry = new JLabel("Country :");
		    lblCountry.setBounds(12, 233, 73, 14);
		    panel_1.add(lblCountry);
		    
		    JLabel lblNote = new JLabel("City :");
		    lblNote.setBounds(12, 201, 73, 14);
		    panel_1.add(lblNote);
		    
		    JLabel lblPayTo = new JLabel("Address 4:");
		    lblPayTo.setBounds(12, 170, 73, 14);
		    panel_1.add(lblPayTo);
		    
		    JLabel lblAddress = new JLabel("Address 3:");
		    lblAddress.setBounds(12, 139, 73, 14);
		    panel_1.add(lblAddress);
		    
		    JLabel lblRemarks = new JLabel("Address 2:");
		    lblRemarks.setBounds(12, 110, 73, 14);
		    panel_1.add(lblRemarks);
		    
		    JLabel lblCheque = new JLabel("Address 1:");
		    lblCheque.setBounds(12, 77, 73, 14);
		    panel_1.add(lblCheque);
		    
		    JLabel lblBankName = new JLabel("TRN :");
		    lblBankName.setBounds(12, 45, 73, 21);
		    panel_1.add(lblBankName);
		    
		    JLabel lblStoreName = new JLabel("Store Name :");
		    lblStoreName.setBounds(12, 13, 84, 21);
		    panel_1.add(lblStoreName);
		    
		    textField = new JTextField();
		    textField.setBounds(251, 549, 116, 22);
		    frame.getContentPane().add(textField);
		    textField.setEnabled(false);
		    textField.setVisible(false);
		    textField.setEditable(false);
		    textField.setColumns(10);
		    
		    
		//frame.getContentPane().add(table);
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(10, 10, 990, 627);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		get_storedisp(company,txtS.getText());
	}
	static void get_storedisp(String company,String store_name)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store where store_id='"+store_name+"' ");
			if(resultSet.next())
			{
				textField_8.setText(resultSet.getString("store_name"));
				textField_1.setText(resultSet.getString("store_name"));
				textField.setText(resultSet.getString("store_id"));
				textField_2.setText(resultSet.getString("trn"));
				textField_4.setText(resultSet.getString("addr1"));
				textField_3.setText(resultSet.getString("addr2"));
				textField_5.setText(resultSet.getString("addr3"));
				textField_6.setText(resultSet.getString("addr4"));
				textField_7.setText(resultSet.getString("city"));
				textField_9.setText(resultSet.getString("country"));
				textField_13.setText(resultSet.getString("web"));
				textField_12.setText(resultSet.getString("email"));
				textField_11.setText(resultSet.getString("ph"));
				textField_10.setText(resultSet.getString("land_line"));
				String barcode_flag=resultSet.getString("barcode_flag");
				comboBox.setSelectedItem(resultSet.getString("barcode_label"));
				String flag=resultSet.getString("flag");
				if(barcode_flag.equals("1"))
				{
					chckbxNewCheckBox.setSelected(true);
					textField_14.setEnabled(true);
					textField_14.setText(flag);
				}
				String ApplyItemDiscount=resultSet.getString("ApplyItemDiscount");
				if(ApplyItemDiscount.equals("1"))
				{
					chckbxApplyItemDiscount.setSelected(true);
				}
				String ChangeCustomerAt=resultSet.getString("ChangeCustomerAt");
				if(ChangeCustomerAt.equals("1"))
				{
					chckbxChangeCustomerAt.setSelected(true);
				}
				String ApplyCustomerDiscount=resultSet.getString("ApplyCustomerDiscount");
				if(ApplyCustomerDiscount.equals("1"))
				{
					chckbxApplyCustomerDiscount.setSelected(true);
				}
				String DonotPerformEod=resultSet.getString("DonotPerformEod");
				if(DonotPerformEod.equals("1"))
				{
					chckbxDonotPerformEod.setSelected(true);
				}
				String UseLoyalityProgram=resultSet.getString("UseLoyalityProgram");
				if(UseLoyalityProgram.equals("1"))
				{
					chckbxUseLoyalityProgram.setSelected(true);
				}
				String POcot=resultSet.getString("POcost");
				if(POcot.equals("1"))
				{
					chckbxPoCost.setSelected(true);
				}
				String ConsolidatedPosLines=resultSet.getString("ConsolidatedPosLines");
				if(ConsolidatedPosLines.equals("1"))
				{
					chckbxConsolidatedPosLines.setSelected(true);
				}
				String change_hold_type=resultSet.getString("change_hold_type");
				if(change_hold_type.equals("1"))
				{
					chckbxApplyStore.setSelected(true);
				}
				
				String NoofDecimal=resultSet.getString("NoofDecimal");
				String decimal_count=resultSet.getString("decimal_count");
				if(NoofDecimal.equals("1"))
				{
					chckbxNoofDecimalTo.setSelected(true);
					textField_15.setText(decimal_count);
				}
				String AllowNegativeQuantity=resultSet.getString("AllowNegativeQuantity");
				if(AllowNegativeQuantity.equals("1"))
				{
					chckbxAllowNegativeQuantity.setSelected(true);
				}
				if(resultSet.getString("apply_store_credit").equals("1"))
				{
					chckbxApplyStoreCredit.setSelected(true);
				}
				String allow_roundoff=resultSet.getString("allow_roundoff");
				if(allow_roundoff.equals("1"))
				{
					chckbxAllowRoundoff.setSelected(true);
				}
				
				String assembly_of_item=resultSet.getString("assembly_of_item");
				if(assembly_of_item.equals("1"))
				{
					chckbxAssemblyOf.setSelected(true);
				}
				String sd_active=resultSet.getString("sd_active");
				if(sd_active.equals("1"))
				{
					chckbxEnableDelivery.setSelected(true);
				}
				String acc_active=resultSet.getString("acc_active");
				if(acc_active.equals("1"))
				{
					chckbxItemCost.setSelected(true);
				}
				String SdPricelock=resultSet.getString("SdPricelock");
				if(SdPricelock.equals("1"))
				{
					chckbxSdPrice.setSelected(true);
				}
				
				String ItemCodeAllowChar=resultSet.getString("ItemCodeAllowChar");
				if(ItemCodeAllowChar.equals("1"))
				{
					chckbxCodeAllowChar.setSelected(true);
				}
				
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
