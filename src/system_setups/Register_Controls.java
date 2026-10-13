package system_setups;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import java.awt.Color;
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

public class Register_Controls {

	private JFrame frame;
	private JLabel lblNewLabel_2;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JCheckBox chckbxNewCheckBox;
	private static JCheckBox chckbxPauseBefore;
	private static JCheckBox chckbxPrintHold;
	private static JCheckBox chckbxPrintDuplicate;
	private static JCheckBox chckbxPrintDuplicate_1;
	private static JCheckBox chckbxPrintDuplicate_2;
	private static JCheckBox chckbxReturnEr;
	private static JCheckBox chckbxUnholdOther;
	private static JCheckBox chckbxAllowStore;
	private static JCheckBox chckbxItemDescripton;
	private static JCheckBox chckbxUseBarcode;
	private static JCheckBox chckbxReopenAfter;
	private static JCheckBox chckbxPromptPrint;
	private static JCheckBox chckbxPrintDenomination;
	private static JCheckBox chckbxRegisterAsWaiter;
	private static JCheckBox chckbxRegisterCan;
	private static JCheckBox chckbxPauseHold;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register_Controls window = new Register_Controls("mr_pro", "S0101","aa", null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param string2 
	 * @param string 
	 * @param host_name 
	 * @param company 
	 */
	public Register_Controls(String company,  String resiter_code, String resiter_name,String version) {
		initialize(company, resiter_code, resiter_name, version);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company, final String resiter_code, String resiter_name,String version) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 755, 473);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 749, 438);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		Image control = new ImageIcon(this.getClass().getResource("/control-panel.png")).getImage();
		JLabel lblRegisterControl = new JLabel("Register Control");
		lblRegisterControl.setIcon(new ImageIcon(control));
		lblRegisterControl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRegisterControl.setBounds(10, 11, 228, 44);
		panel.add(lblRegisterControl);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBounds(513, 0, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Register_Controls.get_registeredisp(company, resiter_code);
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBounds(573, 0, 80, 48);
		panel.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pause_after_print="0";
				if(chckbxNewCheckBox.isSelected())
				{
					pause_after_print="1";
				}
				
				String registerAsWaiter="0";
				if(chckbxRegisterAsWaiter.isSelected())
				{
					registerAsWaiter="1";
				}
				String register_all_invoice="0";
				if(chckbxRegisterCan.isSelected())
				{
					register_all_invoice="1";
				}
				String denominationPrint="0";
				if(chckbxPrintDenomination.isSelected())
				{
					denominationPrint="1";
				}
				String PromptPrint="0";
				if(chckbxPromptPrint.isSelected())
				{
					PromptPrint="1";
				}
				String pause_before_print="0";
				if(chckbxPauseBefore.isSelected())
				{
					pause_before_print="1";
				}
				String print_hold="0";
				if(chckbxPrintHold.isSelected())
				{
					print_hold="1";
				}
				String print_duplicate_hold="0";
				if(chckbxPrintDuplicate.isSelected())
				{
					print_duplicate_hold="1";
				}
				String print_duplicate_invoice="0";
				if(chckbxPrintDuplicate_1.isSelected())
				{
					print_duplicate_invoice="1";
				}
				String actve_for_transaction="0";
				if(chckbxPrintDuplicate_2.isSelected())
				{
					actve_for_transaction="1";
				}
				String retn_sale_mode_aftr_sale_rtn="0";
				if(chckbxReturnEr.isSelected())
				{
					retn_sale_mode_aftr_sale_rtn="1";
				}
				String unhold_reg_hold_trans="0";
				if(chckbxUnholdOther.isSelected())
				{
					unhold_reg_hold_trans="1";
				}
				String allow_store_disc="0";
				if(chckbxAllowStore.isSelected())
				{
					allow_store_disc="1";
				}
				String itm_desc_arabic_pos_scrn="0";
				if(chckbxItemDescripton.isSelected())
				{
					itm_desc_arabic_pos_scrn="1";
				}
				String barcode_col_disp_pos_scrn="0";
				if(chckbxUseBarcode.isSelected())
				{
					barcode_col_disp_pos_scrn="1";
				}
				String chckbxPauseHold_="0";
				if(chckbxPauseHold.isSelected())
				{
					chckbxPauseHold_="1";
				}
				
				String cut_of_hrs="0";
				String reopen_after_eod="0";
				if(chckbxReopenAfter.isSelected())
				{
					reopen_after_eod="1";
					cut_of_hrs=textField_2.getText();
				}
				String data_grid_height=textField_3.getText();
				
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    String formattedDate = myDateObj.format(myFormatObj);  
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement;
				try {
					statement = con.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_register_controls where ref_register_num='"+lblNewLabel_2.getText()+"' ");
					if(resultSet.next())
					{
						 String query="update "+company+".mr_register_controls set registerAsWaiter='"+registerAsWaiter+"',register_all_invoice='"+register_all_invoice+"',denominationPrint='"+denominationPrint+"',pause_after_print='"+pause_after_print+"',pause_before_print='"+pause_before_print+"',print_hold='"+print_hold+"',print_duplicate_hold='"+print_duplicate_hold+"',print_duplicate_invoice='"+print_duplicate_invoice+"',actve_for_transaction='"+actve_for_transaction+"', `retn_sale_mode_aftr_sale_rtn`='"+retn_sale_mode_aftr_sale_rtn+"',unhold_reg_hold_trans='"+unhold_reg_hold_trans+"',allow_store_disc='"+allow_store_disc+"',itm_desc_arabic_pos_scrn='"+itm_desc_arabic_pos_scrn+"',barcode_col_disp_pos_scrn='"+barcode_col_disp_pos_scrn+"',reopen_after_eod='"+reopen_after_eod+"',cutt_off_hrs='"+cut_of_hrs+"', `pos_screen_grid_hgt`='"+data_grid_height+"',modified_date='"+formattedDate+"',promt_print='"+PromptPrint+"',PauseHoldPrint='"+chckbxPauseHold_+"' WHERE id='"+resultSet.getString("id")+"' ";
						 statement.executeUpdate(query);
						 JOptionPane.showMessageDialog(frame, "Saved Successfully");
					}
					else
					{
						 String query="insert into "+company+".mr_register_controls(registerAsWaiter,register_all_invoice,pause_after_print,pause_before_print,print_hold,print_duplicate_hold,print_duplicate_invoice,actve_for_transaction,retn_sale_mode_aftr_sale_rtn,unhold_reg_hold_trans,allow_store_disc,itm_desc_arabic_pos_scrn,barcode_col_disp_pos_scrn,reopen_after_eod,cutt_off_hrs,pos_screen_grid_hgt,created_date,modified_date,ref_register_num,promt_print,denominationPrint,PauseHoldPrint) values ('"+registerAsWaiter+"','"+register_all_invoice+"','"+pause_after_print+"','"+pause_before_print+"','"+print_hold+"','"+print_duplicate_hold+"','"+print_duplicate_invoice+"','"+actve_for_transaction+"','"+retn_sale_mode_aftr_sale_rtn+"','"+unhold_reg_hold_trans+"','"+allow_store_disc+"','"+itm_desc_arabic_pos_scrn+"','"+barcode_col_disp_pos_scrn+"','"+reopen_after_eod+"','"+cut_of_hrs+"','"+data_grid_height+"','"+formattedDate+"','"+formattedDate+"','"+lblNewLabel_2.getText()+"','"+PromptPrint+"','"+denominationPrint+"','"+chckbxPauseHold_+"')";
						 statement.executeUpdate(query);
						 JOptionPane.showMessageDialog(frame, "Saved Successfully");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBounds(655, 0, 70, 48);
		panel.add(menuItem_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 77, 750, 2);
		panel.add(separator);
		
		JLabel label_1 = new JLabel("Register Number:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(67, 90, 108, 21);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Register Name:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(67, 122, 113, 21);
		panel.add(label_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 154, 750, 2);
		panel.add(separator_1);
		
		lblNewLabel_2 = new JLabel(resiter_code);
		lblNewLabel_2.setBounds(187, 92, 334, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(resiter_name);
		lblNewLabel_3.setBounds(187, 124, 319, 16);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 159, 715, 279);
		panel_1.setBackground(new Color(243, 229, 216));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		chckbxNewCheckBox = new JCheckBox("1. Pause KOT Printing");
		chckbxNewCheckBox.setBounds(6, 7, 254, 23);
		panel_1.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setBackground(new Color(243, 229, 216));
		
		chckbxPauseBefore = new JCheckBox("2. Update Qty on Scale Items From Hold");
		chckbxPauseBefore.setBounds(6, 37, 309, 23);
		panel_1.add(chckbxPauseBefore);
		chckbxPauseBefore.setBackground(new Color(243, 229, 216));
		
		chckbxPrintHold = new JCheckBox("3. Restrict Multiple Order For Delivery Boy");
		chckbxPrintHold.setBounds(6, 67, 254, 23);
		panel_1.add(chckbxPrintHold);
		chckbxPrintHold.setBackground(new Color(243, 229, 216));
		
		 chckbxPrintDuplicate = new JCheckBox("4. Print Hold Invoice");
		 chckbxPrintDuplicate.setBounds(6, 97, 254, 23);
		 panel_1.add(chckbxPrintDuplicate);
		 chckbxPrintDuplicate.setBackground(new Color(243, 229, 216));
		 
		  chckbxPrintDuplicate_1 = new JCheckBox("5. Print Duplicate Invoice");
		  chckbxPrintDuplicate_1.setBounds(6, 127, 254, 23);
		  panel_1.add(chckbxPrintDuplicate_1);
		  chckbxPrintDuplicate_1.setBackground(new Color(243, 229, 216));
		  
		   chckbxPrintDuplicate_2 = new JCheckBox("6. KOT Bill print Department based");
		   chckbxPrintDuplicate_2.setBounds(6, 157, 254, 23);
		   panel_1.add(chckbxPrintDuplicate_2);
		   chckbxPrintDuplicate_2.setBackground(new Color(243, 229, 216));
		   
		    chckbxReturnEr = new JCheckBox("7. Display Cash Change Screen");
		    chckbxReturnEr.setBounds(6, 187, 309, 23);
		    panel_1.add(chckbxReturnEr);
		    chckbxReturnEr.setBackground(new Color(243, 229, 216));
		    
		     chckbxUnholdOther = new JCheckBox("8. Unhold other register hold transaction");
		     chckbxUnholdOther.setBounds(6, 217, 334, 23);
		     panel_1.add(chckbxUnholdOther);
		     chckbxUnholdOther.setBackground(new Color(243, 229, 216));
		     
		      chckbxAllowStore = new JCheckBox("9. Allow Register to Sale without TAX");
		      chckbxAllowStore.setBounds(6, 247, 312, 23);
		      panel_1.add(chckbxAllowStore);
		      chckbxAllowStore.setBackground(new Color(243, 229, 216));
		      
		       chckbxItemDescripton = new JCheckBox("10. Item Comment screen on POS Screen");
		       chckbxItemDescripton.setBounds(357, 7, 312, 23);
		       panel_1.add(chckbxItemDescripton);
		       chckbxItemDescripton.setBackground(new Color(243, 229, 216));
		       
		        chckbxUseBarcode = new JCheckBox("11. Fast Item Screen based on Department");
		        chckbxUseBarcode.setBounds(357, 37, 377, 23);
		        panel_1.add(chckbxUseBarcode);
		        chckbxUseBarcode.setBackground(new Color(243, 229, 216));
		        
		         chckbxReopenAfter = new JCheckBox("12. Reopen  After EOD");
		         chckbxReopenAfter.setBounds(357, 67, 172, 23);
		         panel_1.add(chckbxReopenAfter);
		         chckbxReopenAfter.setBackground(new Color(243, 229, 216));
		         
		         JLabel lblNewLabel_1 = new JLabel("13. POS screen Datagrid row Height :");
		         lblNewLabel_1.setBounds(357, 97, 212, 14);
		         panel_1.add(lblNewLabel_1);
		         
		         chckbxPromptPrint = new JCheckBox("14. Prompt Print Bill in POS Screen");
		         chckbxPromptPrint.setBounds(357, 118, 334, 25);
		         panel_1.add(chckbxPromptPrint);
		         chckbxPromptPrint.setBackground(new Color(243, 229, 216));
		         
		         chckbxPrintDenomination = new JCheckBox("15. Print Denomination Receipt");
		         chckbxPrintDenomination.setBounds(357, 150, 334, 25);
		         panel_1.add(chckbxPrintDenomination);
		         chckbxPrintDenomination.setBackground(new Color(243, 229, 216));
		         
		         JLabel lblNewLabel = new JLabel("Cut off Hrs:");
		         lblNewLabel.setBounds(535, 70, 71, 14);
		         panel_1.add(lblNewLabel);
		         lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		         
		         textField_2 = new JTextField();
		         textField_2.setBounds(605, 67, 34, 20);
		         panel_1.add(textField_2);
		         textField_2.setColumns(10);
		         
		         textField_3 = new JTextField();
		         textField_3.setBounds(545, 94, 34, 20);
		         panel_1.add(textField_3);
		         textField_3.setColumns(10);
		         
		         chckbxRegisterAsWaiter = new JCheckBox("16. Register as Waiter module");
		         chckbxRegisterAsWaiter.setBounds(357, 182, 232, 23);
		         chckbxRegisterAsWaiter.setBackground(new Color(243, 229, 216));
		         panel_1.add(chckbxRegisterAsWaiter);
		         
		         chckbxRegisterCan = new JCheckBox("17. Register Can see All Invoices");
		         chckbxRegisterCan.setBackground(new Color(243, 229, 216));
		         chckbxRegisterCan.setBounds(357, 212, 232, 23);
		         panel_1.add(chckbxRegisterCan);
		         
		         chckbxPauseHold = new JCheckBox("18. Pause Hold Print");
		         chckbxPauseHold.setBackground(new Color(243, 229, 216));
		         chckbxPauseHold.setBounds(357, 242, 232, 23);
		         panel_1.add(chckbxPauseHold);
		
		get_registeredisp(company, resiter_code);
	}

	public static void get_registeredisp(String company, String reg_num)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_register_controls where ref_register_num='"+reg_num+"' ");
			while(resultSet.next())
			{
				textField_2.setText(resultSet.getString("cutt_off_hrs"));
				textField_3.setText(resultSet.getString("pos_screen_grid_hgt"));
				if(resultSet.getString("pause_after_print").equals("1"))
				{
					chckbxNewCheckBox.setSelected(true);
				}
				else
				{
					chckbxNewCheckBox.setSelected(false);
				}
				if(resultSet.getString("pause_before_print").equals("1"))
				{
					chckbxPauseBefore.setSelected(true);
				}
				else
				{
					chckbxPauseBefore.setSelected(false);
				}
				if(resultSet.getString("print_hold").equals("1"))
				{
					chckbxPrintHold.setSelected(true);
				}
				else
				{
					chckbxPrintHold.setSelected(false);
				}
				if(resultSet.getString("print_duplicate_hold").equals("1"))
				{
					chckbxPrintDuplicate.setSelected(true);
				}
				else
				{
					chckbxPrintDuplicate.setSelected(false);
				}
				if(resultSet.getString("print_duplicate_invoice").equals("1"))
				{
					chckbxPrintDuplicate_1.setSelected(true);
				}
				else
				{
					chckbxPrintDuplicate_1.setSelected(false);
				}
				if(resultSet.getString("actve_for_transaction").equals("1"))
				{
					chckbxPrintDuplicate_2.setSelected(true);
				}
				else
				{
					chckbxPrintDuplicate_2.setSelected(false);
				}
				if(resultSet.getString("retn_sale_mode_aftr_sale_rtn").equals("1"))
				{
					chckbxReturnEr.setSelected(true);
				}
				else
				{
					chckbxReturnEr.setSelected(false);
				}
				if(resultSet.getString("unhold_reg_hold_trans").equals("1"))
				{
					chckbxUnholdOther.setSelected(true);
				}
				else
				{
					chckbxUnholdOther.setSelected(false);
				}
				if(resultSet.getString("allow_store_disc").equals("1"))
				{
					chckbxAllowStore.setSelected(true);
				}
				else
				{
					chckbxAllowStore.setSelected(false);
				}
				if(resultSet.getString("itm_desc_arabic_pos_scrn").equals("1"))
				{
					chckbxItemDescripton.setSelected(true);
				}
				else
				{
					chckbxItemDescripton.setSelected(false);
				}
				if(resultSet.getString("barcode_col_disp_pos_scrn").equals("1"))
				{
					chckbxUseBarcode.setSelected(true);
				}
				else
				{
					chckbxUseBarcode.setSelected(false);
				}
				if(resultSet.getString("reopen_after_eod").equals("1"))
				{
					chckbxReopenAfter.setSelected(true);
				}
				else
				{
					chckbxReopenAfter.setSelected(false);
				}
				if(resultSet.getString("promt_print").equals("0"))
				{
					chckbxPromptPrint.setSelected(false);
				}
				else
				{
					chckbxPromptPrint.setSelected(true);
				}
				if(resultSet.getString("denominationPrint").equals("0"))
				{
					chckbxPrintDenomination.setSelected(false);
				}
				else
				{
					chckbxPrintDenomination.setSelected(true);
				}
				if(resultSet.getString("registerAsWaiter").equals("0"))
				{
					chckbxRegisterAsWaiter.setSelected(false);
				}
				else
				{
					chckbxRegisterAsWaiter.setSelected(true);
				}
				if(resultSet.getString("register_all_invoice").equals("0"))
				{
					chckbxRegisterCan.setSelected(false);
				}
				else
				{
					chckbxRegisterCan.setSelected(true);
				}
				if(resultSet.getString("PauseHoldPrint").equals("0"))
				{
					chckbxPauseHold.setSelected(false);
				}
				else
				{
					chckbxPauseHold.setSelected(true);
				}
				
				textField_2.setText(resultSet.getString("cutt_off_hrs"));
				textField_3.setText(resultSet.getString("pos_screen_grid_hgt"));
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
