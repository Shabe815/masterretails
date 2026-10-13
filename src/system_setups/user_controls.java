package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
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

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;

import org.json.JSONException;

public class user_controls {

	private static JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					user_controls window = new user_controls("mr_pro", "USER02", null);//"http://127.0.0.1/cafepro","cafe_pro", "shabe01"
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param company 
	 * @param host_name 
	 * @param user_code 
	 * @throws JSONException 
	 */
	

	
	public user_controls(String company, String user_code,String version) throws JSONException {
		initialize(company,user_code, version);
	}

	private JTextField commision;
	private JTextField refund;
	private JPanel p1;
	private JPanel p2;  
	private JPanel p3;
	private JPanel p4;
	private JPanel p5;
	private JCheckBox chckbxPosUser;
	private JCheckBox chckbxUserReopencloseregi;
	private JCheckBox User_add_items_not_found_making_PO;
	private JCheckBox chckbxUserHoldinvoive_pos ;
	private JCheckBox user_change_sale_invo_save_reopen; 
	private JCheckBox chckbxCredit_lmt_override;
	private JCheckBox chckbxDeliveryAgent;
	private JCheckBox chckbxUser_Change_qnty_pos;
	private JCheckBox user_quick_retun;
	private JCheckBox user_salerep;
	private JCheckBox user_make_sale_order;
	private JCheckBox chckbxManageSettledInvoices;
	private JCheckBox chckbxMaximumRefundAmount;
	private JCheckBox max_disc_user_sale;
	private JCheckBox chckbxChangePrice;
	private JCheckBox chckbxVoidCurrentTransation;
	private JCheckBox chckbxVoidSettledinvo;
	private JCheckBox chckbxOverrideGrossMargin;
	private JCheckBox chckbxDeleteLinespos;
	private JCheckBox user_view_cost;
	private JCheckBox chckbxAllowNoSale;
	private JCheckBox chckbxAllowPayIn;
	private JCheckBox chckbxAllowPayOut;
	private JCheckBox user_make_sale_retrn;
	private JTextField discount;
	private JCheckBox chckbxChangeUnit;
	private JCheckBox chckbxUserCanSee;
	private JCheckBox chckbxUserCanSee_1;
	
	
	private JPanel panel_4;
	private JCheckBox chckbxCanCreateNew;
	private JCheckBox chckbxEditSellingprice;
	private JCheckBox chckbxEditCostprice;
	private JCheckBox chckbxViewCostitm;
	private JCheckBox chckbxViewHistoryitem;
	private JCheckBox chckbxChangestatusitem;
	private JPanel panel_5;
	private JCheckBox chckbxCreateNewuser;
	private JCheckBox edit_crdtls_exist_user;
	private JCheckBox chckbxEditPoscontrol;
	private JCheckBox chckbxEditPurchasecntrl;
	private JCheckBox chckbxEditInventorycntrl;
	private JCheckBox chckbxEditAdmincntrl;
	
	private JPanel panel_3;
	private JCheckBox chckbxCanPostPaymentvochervendor;
	private JCheckBox chckbxCanEditHeaderpr;
	private JCheckBox chckbxCanEditHeaderpo;
	private JCheckBox chckbxaginganalysrptvendor;
	private JCheckBox chckbxPrepareDebitnotevendor;
	private JCheckBox chckbxPrepareCreditvendor;
	private JCheckBox chckbxvendoradvncpayvoucher;
	private JCheckBox chckbxvendorpayvoucher;
	private JCheckBox chckbxViewvendorstmt;
	private JCheckBox chckbxReversepostpr;
	private JCheckBox chckbxReversepostpo;
	private JCheckBox chckbxCanPostPr;
	private JCheckBox chckbxCanPostPo;
	private JCheckBox chckbxCanMakePr;
	private JCheckBox chckbxCanmakepo;
	
	private JPanel panel_6;
	private JCheckBox chckbxbnk_vchr;
	private JCheckBox crete_csh_vchr;
	private JCheckBox chckbx_journl_vchr;
	private JCheckBox chckbxgenrte_acc_rpt;
	
	/* pos */
	String chckbxUserReopencloseregist="0";
	String UseradditemsnotfoundmakingPO ="0";
	String userHoldinvoive_pos="0";
	String userchangesaleinvosave_reopen="0";
	String Credit_lmt_override="0";
	String DeliveryAgent="0";
	String User_Change_qnty_pos="0";
	String userquick_retun="0";
	String usersalerep="0";
	String usermake_sale_order="0";
	String ManageSettledInvoices="0";
	String MaximumRefundAmount="0";
	String maxdisc_user_sale="0";
	String ChangePrice="0";
	String PosUser="0";
	String VoidCurrentTransation="0";
	String VoidSettledinvo="0";
	String OverrideGrossMargin="0";
	String DeleteLinespos="0";
	String userview_cost="0";
	String AllowNoSale="0";
	String AllowPayIn="0";
	String AllowPayOut="0";
	String usermake_sale_retrn="0";
	String refund_amount="0";
	String discount_amount="0";
	String commission_dt="0";
	String change_unit="0";
	String issue_store_credit="0";
	String seedupinv="0";
	String seehistinv="0";
	
	/* purchase */
	String PostPaymentvochervendor="0";
	String EditHeaderpr="0";
	String EditHeaderpo="0";
	String aging_analys_rpt_vendor="0";
	String PrepareDebitnotevendor="0";
	String PrepareCreditvendor="0";
	String vendoradvncpayvoucher="0";
	String vendorpayvoucher="0";
	String Viewvendorstmt="0";
	String Reversepostpr="0";
	String Reversepostpo="0";
	String CanPostPr="0";
	String CanPostPo="0";
	String CanMakePr="0";
	String CanMakePo="0";
	
	/* inventoy */
	String CanCreateNew="0";
	String EditSellingprice="0";
	String EditCostprice="0";
	String ViewCostitm="0";
	String ViewHistoryitem="0";
	String Changestatusitem="0";
	/* admin */
	String CreateNewuser="0";
	String editcrdtls_exist_user="0";
	String EditPoscontrol="0";
	String EditPurchasecntrl="0";
	String EditInventorycntrl="0";
	String EditAdmincntrl="0";
	/* account */
	String bank_voucher="0";
	String cash_voucher="0";
	String journal_voucher="0";
	String report_generate="0";
	private JCheckBox chckbxIssueStore;
	
	/**
	 * Initialize the contents of the frame.
	 * @param company 
	 * @param host_name 
	 * @param user_code 
	 * @throws JSONException 
	 */
	private void initialize(final String company, final String user_code,final String version) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		user_pos_cntrl_disp(company, user_code);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(476, 7, 60, 48);
		frame.getContentPane().add(mntmNewMenuItem);
		
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				user_pos_cntrl_disp(company, user_code);
			}
		});
		mntmRefresh.setBackground(new Color(243, 229, 216));
		mntmRefresh.setIcon(new ImageIcon(refresh));
		mntmRefresh.setBounds(536, 7, 80, 48);
		mntmRefresh.setBorder(null);
		frame.getContentPane().add(mntmRefresh);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String userReopencloseregi="";
				String UseradditemsnotfoundmakingPO="";
				String userHoldinvoive_pos ="";
				
				String userchangesaleinvosave_reopen=""; 
				String Credit_lmt_override="";
				String DeliveryAgent="";
				String User_Change_qnty_pos="";
				String userquick_retun="";
				String usersalerep="";
				String usermake_sale_order="";
				
				String ManageSettledInvoices="";
				String MaximumRefundAmount="";
				String maxdisc_user_sale="";
				String ChangePrice="";
				String PosUser="";
				String VoidCurrentTransation="";
				String VoidSettledinvo="";
				String OverrideGrossMargin="";
				
				String DeleteLinespos="";
				String userview_cost="";
				String AllowNoSale="";
				String AllowPayIn="";
				String AllowPayOut="";
				String usermake_sale_retrn="";
				String userseedupinv="";
				String userseehistinv="";
				
				String CanCreateNew="";
				String EditSellingprice="";
				String EditCostprice="";
				String ViewCostitm="";
				String ViewHistoryitem="";
				String Changestatusitem="";
				
				String CreateNewuser="";
				String editcrdtls_exist_user="";
				String EditPoscontrol="";
				String EditPurchasecntrl="";
				String EditInventorycntrl="";
				String EditAdmincntrl="";
				
				String PostPaymentvochervendor="";
				String EditHeaderpr="";
				String EditHeaderpo="";
				String aging_analys_rpt_vendor="";
				String PrepareDebitnotevendor="";
				String PrepareCreditvendor="";
				String vendoradvncpayvoucher="";
				
				String vendorpayvoucher="";
				String Viewvendorstmt="";
				String Reversepostpr="";
				String Reversepostpo="";
				String CanPostPr="";
				String CanPostPo="";
				String CanMakePr="";
				String Canmakepo="";
				String refund_amount="";
				String commission="";
				String discount_amt="";
				
				String bank_voucher="";
				String cash_voucher="";
				String journal_voucher="";
				String report_generate="";
				String change_unit="";
				String IssueStorecredit="";
				
				if (chckbxIssueStore.isSelected()) {
					IssueStorecredit="1";
                }
				else{
					IssueStorecredit="0";
				}
				if (chckbxUserReopencloseregi.isSelected()) {
					userReopencloseregi="1";
                }
				else{
					userReopencloseregi="0";
				}
				if (User_add_items_not_found_making_PO.isSelected()) {
					UseradditemsnotfoundmakingPO="1";
                }
				else{
					UseradditemsnotfoundmakingPO="0";
				}
				if (chckbxUserHoldinvoive_pos.isSelected()) {
					userHoldinvoive_pos="1";
                }
				else{
					userHoldinvoive_pos="0";
				}
				if (chckbxUserHoldinvoive_pos.isSelected()) {
					userHoldinvoive_pos="1";
                }
				else{
					userHoldinvoive_pos="0";
				}
				if (user_change_sale_invo_save_reopen.isSelected()) {
					userchangesaleinvosave_reopen="1";
                }
				else{
					userchangesaleinvosave_reopen="0";
				}
				if (chckbxCredit_lmt_override.isSelected()) {
					Credit_lmt_override="1";
                }
				else{
					Credit_lmt_override="0";
				}
				if (chckbxDeliveryAgent.isSelected()) {
					DeliveryAgent="1";
                }
				else{
					DeliveryAgent="0";
				}
				if (chckbxUser_Change_qnty_pos.isSelected()) {
					User_Change_qnty_pos="1";
                }
				else{
					User_Change_qnty_pos="0";
				}
				if (user_quick_retun.isSelected()) {
					userquick_retun="1";
                }
				else{
					userquick_retun="0";
				}
				if (user_salerep.isSelected()) {
					usersalerep="1";
					float commsion = Float.parseFloat(commision.getText());
					float value_com;
					if(commsion >= 0)
					{
						value_com= commsion;
					}
					else
					{
						value_com=0;
					}
					commission=Float.toString(value_com);
                }
				else{
					usersalerep="0";
					commission="0";
				}
				if (user_make_sale_order.isSelected()) {
					usermake_sale_order="1";
                }
				else{
					usermake_sale_order="0";
				}
				if (chckbxManageSettledInvoices.isSelected()) {
					ManageSettledInvoices="1";
                }
				else{
					ManageSettledInvoices="0";
				}
				if (chckbxMaximumRefundAmount.isSelected()) {
					MaximumRefundAmount="1";
					float refnd = Float.parseFloat(refund.getText());
					float value;
					if(refnd > 0)
					{
						value= refnd;
					}
					else
					{
						value=0;
					}
					refund_amount=Float.toString(value);
                }
				else{
					MaximumRefundAmount="0";
					refund_amount="0";
				}
				if (max_disc_user_sale.isSelected()) {
					maxdisc_user_sale="1";
					float discunt = Float.parseFloat(discount.getText());
					float val;
					if(discunt > 0)
					{
						val= discunt;
					}
					else
					{
						val=0;
					}
					discount_amt=Float.toString(val);
                }
				else{
					maxdisc_user_sale="0";
					discount_amt="0";
				}
				if (chckbxChangePrice.isSelected()) {
					ChangePrice="1";
                }
				else{
					ChangePrice="0";
				}
				if (chckbxPosUser.isSelected()) {
					PosUser="1";
                }
				else{
					PosUser="0";
				}
				if (chckbxVoidCurrentTransation.isSelected()) {
					VoidCurrentTransation="1";
                }
				else{
					VoidCurrentTransation="0";
				}
				if (chckbxVoidSettledinvo.isSelected()) {
					VoidSettledinvo="1";
                }
				else{
					VoidSettledinvo="0";
				}
				if (chckbxOverrideGrossMargin.isSelected()) {
					OverrideGrossMargin="1";
                }
				else{
					OverrideGrossMargin="0";
				}
				if (chckbxDeleteLinespos.isSelected()) {
					DeleteLinespos="1";
                }
				else{
					DeleteLinespos="0";
				}
				if (user_view_cost.isSelected()) {
					userview_cost="1";
                }
				else{
					userview_cost="0";
				}
				if (chckbxAllowNoSale.isSelected()) {
					AllowNoSale="1";
                }
				else{
					AllowNoSale="0";
				}
				if (chckbxAllowPayIn.isSelected()) {
					AllowPayIn="1";
                }
				else{
					AllowPayIn="0";
				}
				if (chckbxAllowPayOut.isSelected()) {
					AllowPayOut="1";
                }
				else{
					AllowPayOut="0";
				}
				if (user_make_sale_retrn.isSelected()) {
					usermake_sale_retrn="1";
                }
				else{
					usermake_sale_retrn="0";
				}
				if (chckbxUserCanSee.isSelected()) {
					userseedupinv="1";
                }
				else{
					userseedupinv="0";
				}
				if (chckbxUserCanSee_1.isSelected()) {
					userseehistinv="1";
                }
				else{
					userseehistinv="0";
				}
				
				
				
				if (chckbxCanCreateNew.isSelected()) {
					CanCreateNew="1";
                }
				else{
					CanCreateNew="0";
				}
				if (chckbxEditSellingprice.isSelected()) {
					EditSellingprice="1";
                }
				else{
					EditSellingprice="0";
				}
				if (chckbxEditCostprice.isSelected()) {
					EditCostprice="1";
                }
				else{
					EditCostprice="0";
				}
				if (chckbxViewCostitm.isSelected()) {
					ViewCostitm="1";
                }
				else{
					ViewCostitm="0";
				}
				if (chckbxViewHistoryitem.isSelected()) {
					ViewHistoryitem="1";
                }
				else{
					ViewHistoryitem="0";
				}
				if (chckbxChangestatusitem.isSelected()) {
					Changestatusitem="1";
                }
				else{
					Changestatusitem="0";
				}
				if (chckbxCreateNewuser.isSelected()) {
					CreateNewuser="1";
                }
				else{
					CreateNewuser="0";
				}
				if (edit_crdtls_exist_user.isSelected()) {
					editcrdtls_exist_user="1";
                }
				else{
					editcrdtls_exist_user="0";
				}
				if (chckbxEditPoscontrol.isSelected()) {
					EditPoscontrol="1";
                }
				else{
					EditPoscontrol="0";
				}
				if (chckbxEditPurchasecntrl.isSelected()) {
					EditPurchasecntrl="1";
                }
				else{
					EditPurchasecntrl="0";
				}
				if (chckbxEditInventorycntrl.isSelected()) {
					EditInventorycntrl="1";
                }
				else{
					EditInventorycntrl="0";
				}
				if (chckbxEditAdmincntrl.isSelected()) {
					EditAdmincntrl="1";
                }
				else{
					EditAdmincntrl="0";
				}
				if (chckbxCanPostPaymentvochervendor.isSelected()) {
					PostPaymentvochervendor="1";
                }
				else{
					PostPaymentvochervendor="0";
				}
				if (chckbxCanEditHeaderpr.isSelected()) {
					EditHeaderpr="1";
                }
				else{
					EditHeaderpr="0";
				}
				if (chckbxCanEditHeaderpo.isSelected()) {
					EditHeaderpo="1";
                }
				else{
					EditHeaderpo="0";
				}
				if (chckbxaginganalysrptvendor.isSelected()) {
					aging_analys_rpt_vendor="1";
                }
				else{
					aging_analys_rpt_vendor="0";
				}
				if (chckbxPrepareDebitnotevendor.isSelected()) {
					PrepareDebitnotevendor="1";
                }
				else{
					PrepareDebitnotevendor="0";
				}
				if (chckbxPrepareCreditvendor.isSelected()) {
					PrepareCreditvendor="1";
                }
				else{
					PrepareCreditvendor="0";
				}
				if (chckbxvendoradvncpayvoucher.isSelected()) {
					vendoradvncpayvoucher="1";
                }
				else{
					vendoradvncpayvoucher="0";
				}
				if (chckbxvendorpayvoucher.isSelected()) {
					vendorpayvoucher="1";
                }
				else{
					vendorpayvoucher="0";
				}
				if (chckbxViewvendorstmt.isSelected()) {
					Viewvendorstmt="1";
                }
				else{
					Viewvendorstmt="0";
				}
				if (chckbxReversepostpr.isSelected()) {
					Reversepostpr="1";
                }
				else{
					Reversepostpr="0";
				}
				if (chckbxReversepostpo.isSelected()) {
					Reversepostpo="1";
                }
				else{
					Reversepostpo="0";
				}
				if (chckbxCanPostPr.isSelected()) {
					CanPostPr="1";
                }
				else{
					CanPostPr="0";
				}
				if (chckbxCanPostPo.isSelected()) {
					CanPostPo="1";
                }
				else{
					CanPostPo="0";
				}
				if (chckbxCanMakePr.isSelected()) {
					CanMakePr="1";
                }
				else{
					CanMakePr="0";
				}
				if (chckbxCanmakepo.isSelected()) {
					Canmakepo="1";
                }
				else{
					Canmakepo="0";
				}
				if (chckbxbnk_vchr.isSelected()) {
					bank_voucher="1";
                }
				else{
					bank_voucher="0";
				}
				if (crete_csh_vchr.isSelected()) {
					cash_voucher="1";
                }
				else{
					cash_voucher="0";
				}
				if (chckbx_journl_vchr.isSelected()) {
					journal_voucher="1";
                }
				else{
					journal_voucher="0";
				}
				if (chckbxgenrte_acc_rpt.isSelected()) {
					report_generate="1";
                }
				else{
					report_generate="0";
				}
				if (chckbxChangeUnit.isSelected()) {
					change_unit="1";
                }
				else{
					change_unit="0";
				}
				//params="company_name="+company+"&user_code="+user_code+userReopencloseregi+UseradditemsnotfoundmakingPO+userHoldinvoive_pos+userchangesaleinvosave_reopen+Credit_lmt_override+DeliveryAgent+User_Change_qnty_pos+userquick_retun+usersalerep+usermake_sale_order+ManageSettledInvoices+MaximumRefundAmount+maxdisc_user_sale+ChangePrice+PosUser+VoidCurrentTransation+VoidSettledinvo+OverrideGrossMargin+DeleteLinespos+userview_cost+AllowNoSale+AllowPayIn+AllowPayOut+usermake_sale_retrn+CanCreateNew+EditSellingprice+EditCostprice+ViewCostitm+ViewHistoryitem+Changestatusitem+CreateNewuser+editcrdtls_exist_user+EditPoscontrol+EditPurchasecntrl+EditInventorycntrl+EditAdmincntrl+PostPaymentvochervendor+EditHeaderpr+EditHeaderpo+aging_analys_rpt_vendor+PrepareDebitnotevendor+PrepareCreditvendor+vendoradvncpayvoucher+vendorpayvoucher+Viewvendorstmt+Reversepostpr+Reversepostpo+CanPostPr+CanPostPo+CanMakePr+Canmakepo+discount_amt+refund_amount+commission+bank_voucher+cash_voucher+journal_voucher+report_generate+change_unit;
				//get_pos_contl_save(params);
				
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    String formattedDate = myDateObj.format(myFormatObj);  
			    Connection con=Dbsetup.dbconn.getConnection();
				Statement statement;
				try {
					statement = con.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_user_poscontrol where ref_user_code='"+user_code+"' ");
					if(resultSet.next())
					{
						String query="update "+company+".mr_user_poscontrol set seehistoryinvoice='"+userseehistinv+"',seedupinvoice='"+userseedupinv+"',issue_store_credit='"+IssueStorecredit+"',chckbxUserReopencloseregi='"+userReopencloseregi+"', UseradditemsnotfoundmakingPO='"+UseradditemsnotfoundmakingPO+"',userHoldinvoive_pos='"+userHoldinvoive_pos+"', userchangesaleinvosave_reopen='"+userchangesaleinvosave_reopen+"', Credit_lmt_override='"+Credit_lmt_override+"', DeliveryAgent='"+DeliveryAgent+"', User_Change_qnty_pos='"+User_Change_qnty_pos+"', userquick_retun='"+userquick_retun+"', usersalerep='"+usersalerep+"', `usermake_sale_order`='"+usermake_sale_order+"', `ManageSettledInvoices`='"+ManageSettledInvoices+"', `MaximumRefundAmount`='"+MaximumRefundAmount+"', `maxdisc_user_sale`='"+maxdisc_user_sale+"', `ChangePrice`='"+ChangePrice+"', `PosUser`='"+PosUser+"', `VoidCurrentTransation`='"+VoidCurrentTransation+"', `VoidSettledinvo`='"+VoidSettledinvo+"', `OverrideGrossMargin`='"+OverrideGrossMargin+"', `DeleteLinespos`='"+DeleteLinespos+"', `userview_cost`='"+userview_cost+"', `AllowNoSale`='"+AllowNoSale+"', `AllowPayIn`='"+AllowPayIn+"', `AllowPayOut`='"+AllowPayOut+"', `usermake_sale_retrn`='"+usermake_sale_retrn+"', `refund_amount`='"+refund_amount+"', `discount_amount`='"+discount_amt+"', `commission_dt`='"+commission+"', `change_unit`='"+change_unit+"', `modified_date`='"+formattedDate+"' WHERE ref_user_code='"+user_code+"' ";
						statement.executeUpdate(query);
					}
					else
					{
						String query="INSERT INTO "+company+".mr_user_poscontrol(`ref_user_code`, seehistoryinvoice,seedupinvoice,`chckbxUserReopencloseregi`, `UseradditemsnotfoundmakingPO`, `userHoldinvoive_pos`, `userchangesaleinvosave_reopen`, `Credit_lmt_override`, `DeliveryAgent`, `User_Change_qnty_pos`, `userquick_retun`, `usersalerep`, `usermake_sale_order`, `ManageSettledInvoices`, `MaximumRefundAmount`, `maxdisc_user_sale`, `ChangePrice`, `PosUser`, `VoidCurrentTransation`, `VoidSettledinvo`, `OverrideGrossMargin`, `DeleteLinespos`, `userview_cost`, `AllowNoSale`, `AllowPayIn`, `AllowPayOut`, `usermake_sale_retrn`, `refund_amount`, `discount_amount`, `commission_dt`, `change_unit`, `created_date`, `modified_date`,issue_store_credit)"
								+ "values('"+user_code+"','"+userseehistinv+"','"+userseedupinv+"','"+userReopencloseregi+"','"+UseradditemsnotfoundmakingPO+"','"+userHoldinvoive_pos+"','"+userchangesaleinvosave_reopen+"','"+Credit_lmt_override+"','"+DeliveryAgent+"','"+User_Change_qnty_pos+"','"+userquick_retun+"','"+usersalerep+"','"+usermake_sale_order+"','"+ManageSettledInvoices+"','"+MaximumRefundAmount+"','"+maxdisc_user_sale+"','"+ChangePrice+"','"+PosUser+"','"+VoidCurrentTransation+"','"+VoidSettledinvo+"','"+OverrideGrossMargin+"','"+DeleteLinespos+"','"+userview_cost+"','"+AllowPayIn+"','"+AllowPayOut+"','"+usermake_sale_retrn+"','"+refund_amount+"','"+discount_amt+"','"+MaximumRefundAmount+"','"+commission+"','"+change_unit+"','"+formattedDate+"','"+formattedDate+"','"+IssueStorecredit+"')";
						statement.executeUpdate(query);
					}
					ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_user_purchasecontrol where ref_user_code='"+user_code+"' ");
					if(resultSet1.next())
					{
						String query="update "+company+".mr_user_purchasecontrol set `PostPaymentvochervendor`='"+PostPaymentvochervendor+"', EditHeaderpr='"+EditHeaderpr+"', `EditHeaderpo`='"+EditHeaderpo+"', `aging_analys_rpt_vendor`='"+aging_analys_rpt_vendor+"', `PrepareDebitnotevendor`='"+PrepareDebitnotevendor+"', `PrepareCreditvendor`='"+PrepareCreditvendor+"', `vendoradvncpayvoucher`='"+vendoradvncpayvoucher+"', `vendorpayvoucher`='"+vendorpayvoucher+"', `Viewvendorstmt`='"+Viewvendorstmt+"', `Reversepostpr`='"+Reversepostpr+"', `Reversepostpo`='"+Reversepostpo+"', `CanPostPr`='"+CanPostPr+"', `CanPostPo`='"+CanPostPo+"', `CanMakePr`='"+CanMakePr+"', `CanMakePo`='"+Canmakepo+"', `modified_date`='"+formattedDate+"' WHERE ref_user_code='"+user_code+"' ";
						statement.executeUpdate(query);
					}
					else
					{
						String query="INSERT INTO "+company+".mr_user_purchasecontrol( `ref_user_code`, `created_date`, `modified_date`, `PostPaymentvochervendor`, `EditHeaderpr`, `EditHeaderpo`, `aging_analys_rpt_vendor`, `PrepareDebitnotevendor`, `PrepareCreditvendor`, `vendoradvncpayvoucher`, `vendorpayvoucher`, `Viewvendorstmt`, `Reversepostpr`, `Reversepostpo`, `CanPostPr`, `CanPostPo`, `CanMakePr`, `Canmakepo`)"
								+ "values('"+user_code+"','"+formattedDate+"','"+formattedDate+"','"+PostPaymentvochervendor+"','"+EditHeaderpr+"','"+EditHeaderpo+"','"+aging_analys_rpt_vendor+"','"+PrepareDebitnotevendor+"','"+PrepareCreditvendor+"','"+vendoradvncpayvoucher+"','"+vendorpayvoucher+"','"+Viewvendorstmt+"','"+Reversepostpr+"','"+Reversepostpo+"','"+CanPostPr+"','"+CanPostPo+"','"+CanMakePr+"','"+Canmakepo+"')";
						statement.executeUpdate(query);
					}
					ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_user_inventorycontrol where ref_user_code='"+user_code+"' ");
					if(resultSet2.next())
					{
						String query="update "+company+".mr_user_inventorycontrol set `CanCreateNew`='"+CanCreateNew+"', EditSellingprice='"+EditSellingprice+"', `EditCostprice`='"+EditCostprice+"', `ViewCostitm`='"+ViewCostitm+"', `ViewHistoryitem`='"+ViewHistoryitem+"', `Changestatusitem`='"+Changestatusitem+"', `modified_date`='"+formattedDate+"' WHERE ref_user_code='"+user_code+"' ";
						statement.executeUpdate(query);
					}
					else
					{
						String query="INSERT INTO "+company+".mr_user_inventorycontrol(`ref_user_code`, `created_date`, `modified_date`, `CanCreateNew`, `EditSellingprice`, `EditCostprice`, `ViewCostitm`, `ViewHistoryitem`, `Changestatusitem`)"
								+ "values('"+user_code+"','"+formattedDate+"','"+formattedDate+"','"+CanCreateNew+"','"+EditSellingprice+"','"+EditCostprice+"','"+ViewCostitm+"','"+ViewHistoryitem+"','"+Changestatusitem+"')";
						statement.executeUpdate(query);
					}
					ResultSet resultSet3 = statement.executeQuery("select * from "+company+".mr_user_admincontrol where ref_user_code='"+user_code+"' ");
					if(resultSet3.next())
					{
						String query="update "+company+".mr_user_admincontrol set `CreateNewuser`='"+CreateNewuser+"', editcrdtls_exist_user='"+editcrdtls_exist_user+"', `EditPoscontrol`='"+EditPoscontrol+"', `EditPurchasecntrl`='"+EditPurchasecntrl+"', `EditInventorycntrl`='"+EditInventorycntrl+"', `EditAdmincntrl`='"+EditAdmincntrl+"', `modified_date`='"+formattedDate+"' WHERE ref_user_code='"+user_code+"' ";
						statement.executeUpdate(query);
					}
					else
					{
						String query="INSERT INTO "+company+".mr_user_admincontrol(`ref_user_code`, `created_date`, `modified_date`, `CreateNewuser`, `editcrdtls_exist_user`, `EditPoscontrol`, `EditPurchasecntrl`, `EditInventorycntrl`, `EditAdmincntrl`)"
								+ "values('"+user_code+"','"+formattedDate+"','"+formattedDate+"','"+CreateNewuser+"','"+editcrdtls_exist_user+"','"+EditPoscontrol+"','"+EditPurchasecntrl+"','"+EditInventorycntrl+"','"+EditAdmincntrl+"')";
						statement.executeUpdate(query);
					}
					ResultSet resultSet4 = statement.executeQuery("select * from "+company+".mr_user_account_controls where ref_user_code='"+user_code+"' ");
					if(resultSet4.next())
					{
						String query="update "+company+".mr_user_account_controls set `bank_voucher`='"+bank_voucher+"', cash_voucher='"+cash_voucher+"', `journal_voucher`='"+journal_voucher+"', `report_generate`='"+report_generate+"', `modified_date`='"+formattedDate+"' WHERE ref_user_code='"+user_code+"' ";
						statement.executeUpdate(query);
					}
					else
					{
						String query="INSERT INTO "+company+".mr_user_account_controls( `ref_user_code`, `created_date`, `modified_date`, `bank_voucher`, `cash_voucher`, `journal_voucher`, `report_generate`)"
								+ "values('"+user_code+"','"+formattedDate+"','"+formattedDate+"','"+bank_voucher+"','"+cash_voucher+"','"+journal_voucher+"','"+report_generate+"')";
						statement.executeUpdate(query);
					}
					JOptionPane.showMessageDialog(frame, "Saved Successfully");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
			}
		});
		mntmSave.setBackground(new Color(243, 229, 216));
		mntmSave.setIcon(new ImageIcon(save));
		mntmSave.setBounds(618, 7, 70, 48);
		mntmSave.setBorder(null);
		frame.getContentPane().add(mntmSave);
		
		
		JLabel lblCompanySetup = new JLabel("Users Controls");
		lblCompanySetup.setBounds(21, 11, 228, 44);
		Image user=new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		lblCompanySetup.setIcon(new ImageIcon(user));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 60, 700, 2);
		frame.getContentPane().add(separator);
		
		p1=new JPanel();
		p2=new JPanel();  
		p3=new JPanel();
	    p4=new JPanel();
	    p5=new JPanel();
	    
	    JTabbedPane tp=new JTabbedPane();  
	    tp.setBounds(2,70,690,500);  
	    tp.add("POS CONTROLS",p1); 
	    tp.add("PURCHASE CONTROLS",p2);  
	    tp.add("INVENTORY CONTROLS",p3); 
	    tp.add("ADMIN CONTROLS",p4);  
	    tp.add("ACCOUNT CONTROLS",p5);  
	    tp.setBackgroundAt(0, Color.RED);
        tp.setBackgroundAt(1, Color.GREEN);
        tp.setBackgroundAt(2, Color.YELLOW);
        tp.setBackgroundAt(3, Color.ORANGE);
        frame.getContentPane().add(tp);  
	    
        p1.setLayout(null);
        p2.setLayout(null);
	    p3.setLayout(null);
	    p4.setLayout(null);
	    p5.setLayout(null);
	    
	    JPanel panel = new JPanel();
	    panel.setBounds(315, 13, 358, 444);
	    p1.add(panel);
	    panel.setLayout(null);
		
		
		chckbxUserHoldinvoive_pos = new JCheckBox("15. USER CAN HOLD INVOICE AT POS");
		chckbxUserHoldinvoive_pos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxUserHoldinvoive_pos.setBounds(8, 6, 273, 25);
		if(userHoldinvoive_pos.equals("1"))
	    {
			chckbxUserHoldinvoive_pos.setSelected(true);
	    }
		panel.add(chckbxUserHoldinvoive_pos);
		
		user_change_sale_invo_save_reopen = new JCheckBox("18.USER CAN CHANGE SALES INVOICE AFTER SAVE AND REOPEN");
		user_change_sale_invo_save_reopen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		user_change_sale_invo_save_reopen.setBounds(8, 99, 350, 25);
		if(userchangesaleinvosave_reopen.equals("1"))
	    {
			user_change_sale_invo_save_reopen.setSelected(true);
	    }
		panel.add(user_change_sale_invo_save_reopen);
		
		chckbxCredit_lmt_override = new JCheckBox("17. OVERRIDE CREDIT LIMIT WHILE MAKING INVOICE");
		chckbxCredit_lmt_override.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxCredit_lmt_override.setBounds(8, 68, 342, 25);
		if(Credit_lmt_override.equals("1"))
	    {
			chckbxCredit_lmt_override.setSelected(true);
	    }
		panel.add(chckbxCredit_lmt_override);
		
		chckbxDeliveryAgent = new JCheckBox("16. SEE ITEM QTY OH");
		chckbxDeliveryAgent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxDeliveryAgent.setBounds(8, 37, 186, 25);
		if(DeliveryAgent.equals("1"))
	    {
			chckbxDeliveryAgent.setSelected(true);
	    }
		panel.add(chckbxDeliveryAgent);
		
		chckbxUser_Change_qnty_pos = new JCheckBox("19. USER CAN CHANGE QUANTITY AT POS");
		chckbxUser_Change_qnty_pos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxUser_Change_qnty_pos.setBounds(8, 130, 248, 25);
		if(User_Change_qnty_pos.equals("1"))
	    {
			chckbxUser_Change_qnty_pos.setSelected(true);
	    }
		panel.add(chckbxUser_Change_qnty_pos);
		
		user_quick_retun = new JCheckBox("20. USER CAN VIEW PRICE ENQUIRY");
		user_quick_retun.setFont(new Font("Tahoma", Font.PLAIN, 11));
		user_quick_retun.setBounds(8, 161, 273, 25);
		if(userquick_retun.equals("1"))
	    {
			user_quick_retun.setSelected(true);
	    }
		panel.add(user_quick_retun);
		
		user_salerep = new JCheckBox("21. USER IS A SALES REP          COMMISSSION%");
		user_salerep.setFont(new Font("Tahoma", Font.PLAIN, 11));
		user_salerep.setBounds(8, 192, 265, 25);
		if(usersalerep.equals("1"))
	    {
			user_salerep.setSelected(true);
	    }
		panel.add(user_salerep);
		
		max_disc_user_sale = new JCheckBox("22. MAXIMUM DISCOUNT USER CAN GIVE IN SALES %");
		max_disc_user_sale.setFont(new Font("Tahoma", Font.PLAIN, 11));
		max_disc_user_sale.setBounds(8, 223, 290, 25);
		if(maxdisc_user_sale.equals("1"))
	    {
			max_disc_user_sale.setSelected(true);
	    }
		panel.add(max_disc_user_sale);
		
		chckbxManageSettledInvoices = new JCheckBox("23. MANAGED SETTLED INVOICES");
		chckbxManageSettledInvoices.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxManageSettledInvoices.setBounds(8, 254, 248, 25);
		if(ManageSettledInvoices.equals("1"))
	    {
			chckbxManageSettledInvoices.setSelected(true);
	    }
		panel.add(chckbxManageSettledInvoices);
		
		chckbxMaximumRefundAmount = new JCheckBox("24. MAXIMUM REFUND AMOUNT");
		chckbxMaximumRefundAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxMaximumRefundAmount.setBounds(8, 285, 200, 25);
		if(MaximumRefundAmount.equals("1"))
	    {
			chckbxMaximumRefundAmount.setSelected(true);
	    }
		panel.add(chckbxMaximumRefundAmount);
		
		commision = new JTextField(commission_dt);
		commision.setBounds(280, 195, 50, 22);
		panel.add(commision);
		commision.setColumns(10);
		
		refund = new JTextField(refund_amount);
		refund.setColumns(10);
		refund.setBounds(216, 288, 50, 22);
		panel.add(refund);
		
		discount = new JTextField(discount_amount);
		discount.setColumns(10);
		discount.setBounds(306, 223, 50, 22);
		panel.add(discount);
		
		chckbxChangeUnit = new JCheckBox("25. CHANGE UNIT");
		chckbxChangeUnit.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxChangeUnit.setBounds(8, 316, 210, 25);
		panel.add(chckbxChangeUnit);
		if(change_unit.equals("1"))
	    {
			chckbxChangeUnit.setSelected(true);
	    }
		
		
		chckbxIssueStore = new JCheckBox("26. ISSUE STORE CREDIT");
		chckbxIssueStore.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxIssueStore.setBounds(8, 347, 210, 25);
		panel.add(chckbxIssueStore);
		if(issue_store_credit.equals("1"))
		{
			chckbxIssueStore.setSelected(true);
		}
		
		chckbxUserCanSee = new JCheckBox("27. USER CAN SEE DUPLICATE INVOICE");
		chckbxUserCanSee.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxUserCanSee.setBounds(8, 378, 290, 25);
		panel.add(chckbxUserCanSee);
		
		if(seedupinv.equals("1"))
		{
			chckbxUserCanSee.setSelected(true);
		}
		
		chckbxUserCanSee_1 = new JCheckBox("28. USER CAN SEE HISTORICAL INVOICE");
		chckbxUserCanSee_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxUserCanSee_1.setBounds(8, 409, 248, 25);
		panel.add(chckbxUserCanSee_1);
		
		if(seehistinv.equals("1"))
		{
			chckbxUserCanSee_1.setSelected(true);
		}
		
	    
		JPanel panel_1 = new JPanel();
	    panel_1.setBounds(12, 13, 295, 444);
	    p1.add(panel_1);
	    panel_1.setLayout(null);
	    
	    chckbxPosUser = new JCheckBox("1. POS USER");
		chckbxPosUser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxPosUser.setBounds(8, 9, 113, 25);
		if(PosUser.equals("1"))
	    {
			chckbxPosUser.setSelected(true);
	    }
		panel_1.add(chckbxPosUser);
		
		user_make_sale_retrn = new JCheckBox("2. USER CAN MAKE SALES RETURN");
		user_make_sale_retrn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		user_make_sale_retrn.setBounds(8, 39, 279, 25);
		if(usermake_sale_retrn.equals("1"))
	    {
			user_make_sale_retrn.setSelected(true);
	    }
		panel_1.add(user_make_sale_retrn);
		
		chckbxChangePrice = new JCheckBox("3. CHANGE PRICE");
		chckbxChangePrice.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxChangePrice.setBounds(8, 69, 261, 25);
		if(ChangePrice.equals("1"))
	    {
			chckbxChangePrice.setSelected(true);
	    }
		panel_1.add(chckbxChangePrice);
		
		chckbxVoidCurrentTransation = new JCheckBox("6. VOID CURRENT TRANSATION");
		chckbxVoidCurrentTransation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxVoidCurrentTransation.setBounds(8, 161, 186, 25);
		if(VoidCurrentTransation.equals("1"))
	    {
			chckbxVoidCurrentTransation.setSelected(true);
	    }
		panel_1.add(chckbxVoidCurrentTransation);
		
		chckbxVoidSettledinvo = new JCheckBox("5. VOID SETTLED INVOICE");
		chckbxVoidSettledinvo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxVoidSettledinvo.setBounds(8, 131, 186, 25);
		if(VoidSettledinvo.equals("1"))
	    {
			chckbxVoidSettledinvo.setSelected(true);
	    }
		panel_1.add(chckbxVoidSettledinvo);
		
		chckbxOverrideGrossMargin = new JCheckBox("4. USER CAN CHANGE CHECK PRINT INVOICE");
		chckbxOverrideGrossMargin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxOverrideGrossMargin.setBounds(8, 101, 279, 25);
		if(OverrideGrossMargin.equals("1"))
	    {
			chckbxOverrideGrossMargin.setSelected(true);
	    }
		panel_1.add(chckbxOverrideGrossMargin);
		
		chckbxDeleteLinespos = new JCheckBox("7. DELETE LINES FROM POS TRANSACTIONS");
		chckbxDeleteLinespos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxDeleteLinespos.setBounds(8, 191, 248, 25);
		if(DeleteLinespos.equals("1"))
	    {
			chckbxDeleteLinespos.setSelected(true);
	    }
		panel_1.add(chckbxDeleteLinespos);
		
		user_view_cost = new JCheckBox("8. USER CAN VIEW COST");
		user_view_cost.setFont(new Font("Tahoma", Font.PLAIN, 11));
		user_view_cost.setBounds(8, 221, 186, 25);
		if(userview_cost.equals("1"))
	    {
			user_view_cost.setSelected(true);
	    }
		panel_1.add(user_view_cost);
		
		chckbxAllowNoSale = new JCheckBox("9. ALLOW NO SALE");
		chckbxAllowNoSale.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxAllowNoSale.setBounds(8, 251, 237, 25);
		if(AllowNoSale.equals("1"))
	    {
			chckbxAllowNoSale.setSelected(true);
	    }
		panel_1.add(chckbxAllowNoSale);
		
		chckbxAllowPayIn = new JCheckBox("10. ALLOW PAY IN");
		chckbxAllowPayIn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxAllowPayIn.setBounds(8, 280, 203, 25);
		if(AllowPayIn.equals("1"))
	    {
			chckbxAllowPayIn.setSelected(true);
	    }
		panel_1.add(chckbxAllowPayIn);
		
		chckbxAllowPayOut = new JCheckBox("11. ALLOW PAY OUT");
		chckbxAllowPayOut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxAllowPayOut.setBounds(8, 310, 161, 25);
		if(AllowPayOut.equals("1"))
	    {
			chckbxAllowPayOut.setSelected(true);
	    }
		panel_1.add(chckbxAllowPayOut);
		
		user_make_sale_order = new JCheckBox("12. USER CAN SEE REPORTS");
		user_make_sale_order.setFont(new Font("Tahoma", Font.PLAIN, 11));
		user_make_sale_order.setBounds(8, 340, 279, 25);
		if(usermake_sale_order.equals("1"))
	    {
			user_make_sale_order.setSelected(true);
	    }
		panel_1.add(user_make_sale_order);
		
		chckbxUserReopencloseregi = new JCheckBox("13. USER CAN CLOSE REGISTER");
		chckbxUserReopencloseregi.setBounds(8, 370, 279, 25);
		panel_1.add(chckbxUserReopencloseregi);
		chckbxUserReopencloseregi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		if(chckbxUserReopencloseregist.equals("1"))
	    {
	    	chckbxUserReopencloseregi.setSelected(true);
	    }
		
		
		User_add_items_not_found_making_PO = new JCheckBox("14. USER CAN CHANGE UNHOLDED INVOICE DATA");
		User_add_items_not_found_making_PO.setBounds(8, 400, 279, 25);
		panel_1.add(User_add_items_not_found_making_PO);
		User_add_items_not_found_making_PO.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		if(UseradditemsnotfoundmakingPO.equals("1"))
	    {
			User_add_items_not_found_making_PO.setSelected(true);
	    }
		
		JPanel panel_2 = new JPanel();
	    panel_2.setBounds(12, 13, 338, 369);
	    p2.add(panel_2);
	    panel_2.setLayout(null);
	    
	     chckbxCanmakepo = new JCheckBox("1. CAN MAKE PO");
	    chckbxCanmakepo.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxCanmakepo.setBounds(8, 10, 113, 25);
	    if(CanMakePo.equals("1"))
	    {
	    	chckbxCanmakepo.setSelected(true);
	    }
	    panel_2.add(chckbxCanmakepo);
	    
	     chckbxCanMakePr = new JCheckBox("2. CAN MAKE PR");
	    chckbxCanMakePr.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxCanMakePr.setBounds(8, 40, 113, 25);
	    if(CanMakePr.equals("1"))
	    {
	    	chckbxCanMakePr.setSelected(true);
	    }
	    panel_2.add(chckbxCanMakePr);
	    
	     chckbxCanPostPo = new JCheckBox("3. CAN POST PO");
	    chckbxCanPostPo.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    if(CanPostPo.equals("1"))
	    {
	    	chckbxCanPostPo.setSelected(true);
	    }
	    chckbxCanPostPo.setBounds(8, 70, 113, 25);
	    panel_2.add(chckbxCanPostPo);
	    
	     chckbxCanPostPr = new JCheckBox("4. CAN POST PR");
	    chckbxCanPostPr.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    if(CanPostPr.equals("1"))
	    {
	    	chckbxCanPostPr.setSelected(true);
	    }
	    chckbxCanPostPr.setBounds(8, 100, 113, 25);
	    panel_2.add(chckbxCanPostPr);
	    
	     chckbxReversepostpo = new JCheckBox("5. CAN REVERSE A POSTED PO");
	    chckbxReversepostpo.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    if(Reversepostpo.equals("1"))
	    {
	    	chckbxReversepostpo.setSelected(true);
	    }
	    chckbxReversepostpo.setBounds(8, 130, 285, 25);
	    panel_2.add(chckbxReversepostpo);
	    
	     chckbxReversepostpr = new JCheckBox("6. CAN REVERSE A POSTED PR");
	    chckbxReversepostpr.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    if(Reversepostpr.equals("1"))
	    {
	    	chckbxReversepostpr.setSelected(true);
	    }
	    chckbxReversepostpr.setBounds(8, 160, 285, 25);
	    panel_2.add(chckbxReversepostpr);
	    
	     chckbxViewvendorstmt = new JCheckBox("7. CAN VIEW AND PRINT THE VENDOR STATEMENT");
	    chckbxViewvendorstmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxViewvendorstmt.setBounds(8, 190, 322, 25);
	    if(Viewvendorstmt.equals("1"))
	    {
	    	chckbxViewvendorstmt.setSelected(true);
	    }
	    panel_2.add(chckbxViewvendorstmt);
	    
	    
	     chckbxvendorpayvoucher = new JCheckBox("8. CAN PREPARE PAYMENT VOUCHER FOR VENDOR");
	    chckbxvendorpayvoucher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxvendorpayvoucher.setBounds(8, 220, 322, 25);
	    if(vendorpayvoucher.equals("1"))
	    {
	    	chckbxvendorpayvoucher.setSelected(true);
	    }
	    panel_2.add(chckbxvendorpayvoucher);
	    
	     chckbxvendoradvncpayvoucher = new JCheckBox("9. CAN PREPARE ADVANCE PAYMENT VOUCHER FOR VENDOR");
	    chckbxvendoradvncpayvoucher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxvendoradvncpayvoucher.setBounds(8, 250, 322, 25);
	    if(vendoradvncpayvoucher.equals("1"))
	    {
	    	chckbxvendoradvncpayvoucher.setSelected(true);
	    }
	    panel_2.add(chckbxvendoradvncpayvoucher);
	    
	     chckbxPrepareCreditvendor = new JCheckBox("10. CAN PREPARE CREDIT NOTE FOR VENDOR");
	    chckbxPrepareCreditvendor.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxPrepareCreditvendor.setBounds(8, 280, 285, 25);
	    if(PrepareCreditvendor.equals("1"))
	    {
	    	chckbxPrepareCreditvendor.setSelected(true);
	    }
	    panel_2.add(chckbxPrepareCreditvendor);
	    
	     chckbxPrepareDebitnotevendor = new JCheckBox("11. CAN PREPARE DEBIT NOTE FOR VENDOR");
	    chckbxPrepareDebitnotevendor.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxPrepareDebitnotevendor.setBounds(8, 310, 285, 25);
	    if(PrepareDebitnotevendor.equals("1"))
	    {
	    	chckbxPrepareDebitnotevendor.setSelected(true);
	    }
	    panel_2.add(chckbxPrepareDebitnotevendor);
	    
	    panel_3 = new JPanel();
	    panel_3.setBounds(348, 13, 325, 369);
	    p2.add(panel_3);
	    panel_3.setLayout(null);
	    
	     chckbxaginganalysrptvendor = new JCheckBox("12. CAN PRINT THE AGING ANALYSIS REPORT FOR VENDOR");
	    chckbxaginganalysrptvendor.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxaginganalysrptvendor.setBounds(8, 10, 309, 25);
	    if(aging_analys_rpt_vendor.equals("1"))
	    {
	    	chckbxaginganalysrptvendor.setSelected(true);
	    }
	    panel_3.add(chckbxaginganalysrptvendor);
	    
	     chckbxCanEditHeaderpo = new JCheckBox("13. CAN EDIT HEADER FIELD OF A PREPARED PO");
	    chckbxCanEditHeaderpo.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxCanEditHeaderpo.setBounds(8, 40, 309, 25);
	    if(EditHeaderpo.equals("1"))
	    {
	    	chckbxCanEditHeaderpo.setSelected(true);
	    }
	    panel_3.add(chckbxCanEditHeaderpo);
	    
	     chckbxCanEditHeaderpr = new JCheckBox("14. CAN EDIT HEADER FIELD OF A PREPARED PR");
	    chckbxCanEditHeaderpr.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxCanEditHeaderpr.setBounds(8, 70, 309, 25);
	    if(EditHeaderpr.equals("1"))
	    {
	    	chckbxCanEditHeaderpr.setSelected(true);
	    }
	    panel_3.add(chckbxCanEditHeaderpr);
	    
	     chckbxCanPostPaymentvochervendor = new JCheckBox("15. CAN POST PAYMENT VOUCHER FOR THE VENDOR");
	    chckbxCanPostPaymentvochervendor.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxCanPostPaymentvochervendor.setBounds(8, 100, 309, 25);
	    if(PostPaymentvochervendor.equals("1"))
	    {
	    	chckbxCanPostPaymentvochervendor.setSelected(true);
	    }
	    panel_3.add(chckbxCanPostPaymentvochervendor);
	    
	    
	    
	    
	    panel_4 = new JPanel();
	    panel_4.setBounds(12, 13, 405, 369);
	    p3.add(panel_4);
	    panel_4.setLayout(null);
	    
	    chckbxCanCreateNew = new JCheckBox("1. CAN CREATE NEW ITEM");
	    chckbxCanCreateNew.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxCanCreateNew.setBounds(8, 10, 249, 25);
	    if(CanCreateNew.equals("1"))
	    {
	    	chckbxCanCreateNew.setSelected(true);
	    }
	    panel_4.add(chckbxCanCreateNew);
	    
	    chckbxEditSellingprice = new JCheckBox("2. CAN EDIT SELLING PRICE");
	    chckbxEditSellingprice.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxEditSellingprice.setBounds(8, 40, 308, 25);
	    if(EditSellingprice.equals("1"))
	    {
	    	chckbxEditSellingprice.setSelected(true);
	    }
	    panel_4.add(chckbxEditSellingprice);
	    
	    chckbxEditCostprice = new JCheckBox("3. CAN EDIT COST PRICE");
	    chckbxEditCostprice.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxEditCostprice.setBounds(8, 70, 308, 25);
	    if(EditCostprice.equals("1"))
	    {
	    	chckbxEditCostprice.setSelected(true);
	    }
	    panel_4.add(chckbxEditCostprice);
	    
	    chckbxViewCostitm = new JCheckBox("4. CAN VIEW COST OF ITEM");
	    chckbxViewCostitm.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxViewCostitm.setBounds(8, 100, 308, 25);
	    if(ViewCostitm.equals("1"))
	    {
	    	chckbxViewCostitm.setSelected(true);
	    }
	    panel_4.add(chckbxViewCostitm);
	    
	    chckbxViewHistoryitem = new JCheckBox("5. CAN VIEW HISTORY OF AN ITEM");
	    chckbxViewHistoryitem.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxViewHistoryitem.setBounds(8, 130, 308, 25);
	    if(ViewHistoryitem.equals("1"))
	    {
	    	chckbxViewHistoryitem.setSelected(true);
	    }
	    panel_4.add(chckbxViewHistoryitem);
	    
	    chckbxChangestatusitem = new JCheckBox("6. CAN CHANGE THE QTY OF AN ITEM ");
	    chckbxChangestatusitem.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxChangestatusitem.setBounds(8, 160, 405, 25);
	    if(Changestatusitem.equals("1"))
	    {
	    	chckbxChangestatusitem.setSelected(true);
	    }
	    panel_4.add(chckbxChangestatusitem);
	    
	    
	    
	    panel_5 = new JPanel();
	    panel_5.setBounds(12, 13, 346, 358);
	    p4.add(panel_5);
	    panel_5.setLayout(null);
	    
	    chckbxCreateNewuser = new JCheckBox("1. CHANGE CUSTOMER PAYMENT HISTORY");
	    chckbxCreateNewuser.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxCreateNewuser.setBounds(8, 10, 330, 25);
	    if(CreateNewuser.equals("1"))
	    {
	    	chckbxCreateNewuser.setSelected(true);
	    }
	    panel_5.add(chckbxCreateNewuser);
	    
	    edit_crdtls_exist_user = new JCheckBox("2. CHANGE INVOICE PAYMENT HISTORY");
	    edit_crdtls_exist_user.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    edit_crdtls_exist_user.setBounds(8, 40, 330, 25);
	    if(editcrdtls_exist_user.equals("1"))
	    {
	    	edit_crdtls_exist_user.setSelected(true);
	    }
	    panel_5.add(edit_crdtls_exist_user);
	    
	    chckbxEditPoscontrol = new JCheckBox("");
	    chckbxEditPoscontrol.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxEditPoscontrol.setBounds(8, 70, 330, 25);
	    if(EditPoscontrol.equals("1"))
	    {
	    	chckbxEditPoscontrol.setSelected(true);
	    }
	    panel_5.add(chckbxEditPoscontrol);
	    
	    chckbxEditPurchasecntrl = new JCheckBox("");
	    chckbxEditPurchasecntrl.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxEditPurchasecntrl.setBounds(8, 100, 330, 25);
	    if(EditPurchasecntrl.equals("1"))
	    {
	    	chckbxEditPurchasecntrl.setSelected(true);
	    }
	    panel_5.add(chckbxEditPurchasecntrl);
	    
	    chckbxEditInventorycntrl = new JCheckBox("");
	    chckbxEditInventorycntrl.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxEditInventorycntrl.setBounds(8, 130, 330, 25);
	    if(EditInventorycntrl.equals("1"))
	    {
	    	chckbxEditInventorycntrl.setSelected(true);
	    }
	    panel_5.add(chckbxEditInventorycntrl);
	    
	    chckbxEditAdmincntrl = new JCheckBox("");
	    chckbxEditAdmincntrl.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxEditAdmincntrl.setBounds(8, 160, 330, 25);
	    if(EditAdmincntrl.equals("1"))
	    {
	    	chckbxEditAdmincntrl.setSelected(true);
	    }
	    panel_5.add(chckbxEditAdmincntrl);
	    
	    panel_6 = new JPanel();
	    panel_6.setBounds(12, 13, 405, 369);
	    p5.add(panel_6);
	    panel_6.setLayout(null);
	    
	    chckbxbnk_vchr = new JCheckBox("1. CAN CREATE BANK VOUCHER");
	    chckbxbnk_vchr.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxbnk_vchr.setBounds(8, 10, 330, 25);
	    if(bank_voucher.equals("1"))
	    {
	    	chckbxbnk_vchr.setSelected(true);
	    }
	    panel_6.add(chckbxbnk_vchr);
	    
	    crete_csh_vchr = new JCheckBox("2. CAN CREATE CASH VOUCHER");
	    crete_csh_vchr.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    crete_csh_vchr.setBounds(8, 40, 330, 25);
	    if(cash_voucher.equals("1"))
	    {
	    	crete_csh_vchr.setSelected(true);
	    }
	    panel_6.add(crete_csh_vchr);
	    
	    chckbx_journl_vchr = new JCheckBox("3. CAN CREATE JOURNAL VOUCHER");
	    chckbx_journl_vchr.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbx_journl_vchr.setBounds(8, 70, 330, 25);
	    if(journal_voucher.equals("1"))
	    {
	    	chckbx_journl_vchr.setSelected(true);
	    }
	    panel_6.add(chckbx_journl_vchr);
	    
	    chckbxgenrte_acc_rpt = new JCheckBox("4. CAN GENERATE ACCOUNT REPORTS");
	    chckbxgenrte_acc_rpt.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    chckbxgenrte_acc_rpt.setBounds(8, 100, 330, 25);
	    if(report_generate.equals("1"))
	    {
	    	chckbxgenrte_acc_rpt.setSelected(true);
	    }
	    panel_6.add(chckbxgenrte_acc_rpt);
	    
	    frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 609);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	private void user_pos_cntrl_disp(String company, String user_code)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_user_poscontrol where ref_user_code='"+user_code+"' ");
			if(resultSet.next())
			{
				chckbxUserReopencloseregist=resultSet.getString("chckbxUserReopencloseregi");
				UseradditemsnotfoundmakingPO=resultSet.getString("UseradditemsnotfoundmakingPO");
				userHoldinvoive_pos=resultSet.getString("userHoldinvoive_pos");
				userchangesaleinvosave_reopen=resultSet.getString("userchangesaleinvosave_reopen");
				Credit_lmt_override=resultSet.getString("Credit_lmt_override");
				DeliveryAgent=resultSet.getString("DeliveryAgent");
				User_Change_qnty_pos=resultSet.getString("User_Change_qnty_pos");
				userquick_retun=resultSet.getString("userquick_retun");
				usersalerep=resultSet.getString("usersalerep");
				usermake_sale_order=resultSet.getString("usermake_sale_order");
				ManageSettledInvoices=resultSet.getString("ManageSettledInvoices");
				MaximumRefundAmount=resultSet.getString("MaximumRefundAmount");
				maxdisc_user_sale=resultSet.getString("maxdisc_user_sale");
				ChangePrice=resultSet.getString("ChangePrice");
				PosUser=resultSet.getString("PosUser");
				VoidCurrentTransation=resultSet.getString("VoidCurrentTransation");
				VoidSettledinvo=resultSet.getString("VoidSettledinvo");
				OverrideGrossMargin=resultSet.getString("OverrideGrossMargin");
				DeleteLinespos=resultSet.getString("DeleteLinespos");
				userview_cost=resultSet.getString("userview_cost");
				AllowNoSale=resultSet.getString("AllowNoSale");
				AllowPayIn=resultSet.getString("AllowPayIn");
				AllowPayOut=resultSet.getString("AllowPayOut");
				usermake_sale_retrn=resultSet.getString("usermake_sale_retrn");
				refund_amount=resultSet.getString("refund_amount");
				discount_amount=resultSet.getString("discount_amount");
				commission_dt=resultSet.getString("commission_dt");
				change_unit=resultSet.getString("change_unit");
				issue_store_credit=resultSet.getString("issue_store_credit");
				seedupinv=resultSet.getString("seedupinvoice");
				seehistinv=resultSet.getString("seehistoryinvoice");
				
			}
			ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_user_purchasecontrol where ref_user_code='"+user_code+"' ");
			if(resultSet1.next())
			{
				PostPaymentvochervendor=resultSet1.getString("PostPaymentvochervendor");
				EditHeaderpr=resultSet1.getString("EditHeaderpr");
				EditHeaderpo=resultSet1.getString("EditHeaderpo");
				aging_analys_rpt_vendor=resultSet1.getString("aging_analys_rpt_vendor");
				PrepareDebitnotevendor=resultSet1.getString("PrepareDebitnotevendor");
				PrepareCreditvendor=resultSet1.getString("PrepareCreditvendor");
				vendoradvncpayvoucher=resultSet1.getString("vendoradvncpayvoucher");
				vendorpayvoucher=resultSet1.getString("vendorpayvoucher");
				Viewvendorstmt=resultSet1.getString("Viewvendorstmt");
				Reversepostpr=resultSet1.getString("Reversepostpr");
				Reversepostpo=resultSet1.getString("Reversepostpo");
				CanPostPr=resultSet1.getString("CanPostPr");
				CanPostPo=resultSet1.getString("CanPostPo");
				CanMakePr=resultSet1.getString("CanMakePr");
				CanMakePo=resultSet1.getString("CanMakePo");
			}
			ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_user_inventorycontrol where ref_user_code='"+user_code+"' ");
			if(resultSet2.next())
			{
				CanCreateNew=resultSet2.getString("CanCreateNew");
				EditSellingprice=resultSet2.getString("EditSellingprice");
				EditCostprice=resultSet2.getString("EditCostprice");
				ViewCostitm=resultSet2.getString("ViewCostitm");
				ViewHistoryitem=resultSet2.getString("ViewHistoryitem");
				Changestatusitem=resultSet2.getString("Changestatusitem");
			}
			ResultSet resultSet3 = statement.executeQuery("select * from "+company+".mr_user_admincontrol where ref_user_code='"+user_code+"' ");
			if(resultSet3.next())
			{
				CreateNewuser=resultSet3.getString("CreateNewuser");
				editcrdtls_exist_user=resultSet3.getString("editcrdtls_exist_user");
				EditPoscontrol=resultSet3.getString("EditPoscontrol");
				EditPurchasecntrl=resultSet3.getString("EditPurchasecntrl");
				EditInventorycntrl=resultSet3.getString("EditInventorycntrl");
				EditAdmincntrl=resultSet3.getString("EditAdmincntrl");
			}
			ResultSet resultSet4 = statement.executeQuery("select * from "+company+".mr_user_account_controls where ref_user_code='"+user_code+"' ");
			if(resultSet4.next())
			{
				bank_voucher=resultSet4.getString("bank_voucher");
				cash_voucher=resultSet4.getString("cash_voucher");
				journal_voucher=resultSet4.getString("journal_voucher");
				report_generate=resultSet4.getString("report_generate");
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
