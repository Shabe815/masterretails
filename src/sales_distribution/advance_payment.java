package sales_distribution;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextArea;

import java.awt.ScrollPane;

import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;

public class advance_payment {

	
	private JFrame frame;
	private DefaultTableModel model;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JDateChooser dateChooser;
	private JTextArea textArea;
	private boolean dot=false;
	private JComboBox<String> comboBox_2;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					advance_payment window = new advance_payment( "mr_pro","admin", "S01","1","1","1.0","2");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param object2 
	 * @param object 
	 * @param company 
	 * @param host_name 
	 * @param store 
	 * @param username 
	 * @param vendor_name 
	 * @param vendor_name 
	 */
	public advance_payment( String company, String username,  String store, String vendor_id, String vendor_name,String version,String decimal) {
		initialize(company,username,store,vendor_id,vendor_name,version,decimal);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param balance 
	 * @param item_list 
	 * @param company 
	 * @param host_name 
	 * @param store 
	 * @param username 
	 * @param vendor_name 
	 * @param version 
	 * @param decimal 
	 * @param vendor_name2 
	 */
	private void initialize( final String company,final String username,final String store,final String vendor_id, final String vendor_name, String version, final String decimal) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		
		
		Image Billing=new ImageIcon(this.getClass().getResource("/Billing-icon.png")).getImage();
		
		
		JLabel lblCompanySetup = new JLabel("Advance Payment Voucher");
		lblCompanySetup.setBounds(21, 11, 269, 44);
		lblCompanySetup.setIcon(new ImageIcon(Billing));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Save");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String amt=textField_1.getText();
					String inv=textField.getText();
					String start_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					if(amt.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Please Enter The Amount");
					}
					else if(start_date.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Please choose a Date");
					}
					else
					{
						boolean type=false;
						if(!comboBox.getSelectedItem().toString().equals("CASH"))
						{
							if(comboBox_2.getSelectedItem().toString().equals(""))
							{
								JOptionPane.showMessageDialog(frame, "please select Bank Name");
							}
							else if(textField_4.getText().equals(""))
							{
								JOptionPane.showMessageDialog(frame, "please enter Cheque No.");
								textField_4.requestFocus();
							}
							else
							{
								type=true;
							}
						}
						else
						{
							type=true;
						}
						if(type==true)
						{
							String paycode="";
							String bank_name="";
							String cheque="";
							String pdc="0";
							String ac_payee="0";
							String remark="";
							String pay_to="";
							String pay_note="";
							String po_num="";
							String disc="0";
							String payment_mode=comboBox.getSelectedItem().toString();
							String tranname="";
							if(payment_mode.equals("CASH"))
							{
								paycode=comboBox_1.getSelectedItem().toString();
								tranname="AR";
							}
							else
							{
								tranname="AR";
								bank_name=comboBox_2.getSelectedItem().toString();
								cheque=textField_4.getText();
							}
							remark=textField_5.getText();
							pay_to=textField_6.getText();
							pay_note=textArea.getText();
							LocalDateTime myDateObj = LocalDateTime.now();  
						    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
						    String formattedDate = myDateObj.format(myFormatObj);
						    Connection con=Dbsetup.dbconn.getConnection();
					        String seq_num = null;
					        Statement statement = null;
					        Statement statement1 = null;
					        statement = con.createStatement();
				        	String check_qty="select * from "+company+".mr_cheque_details where cheque_no='"+cheque+"' and bank_name='"+bank_name+"' ";
							ResultSet resultSet25 = statement.executeQuery(check_qty);
							if (!resultSet25.next())
						    {
								statement = con.createStatement();
								statement1 = con.createStatement();
								String trans_seq = "select tran_seq from "+company+".mr_transaction_seq where tran_orgin='"+tranname+"' ";
								ResultSet resultSet2 = statement.executeQuery(trans_seq);
							    if (resultSet2.next())
							    {
							    	seq_num=resultSet2.getString("tran_seq");
							    	String[] seq=seq_num.split("-");
							    	DateFormat df = new SimpleDateFormat("yy");
							    	String formatdDate = df.format(Calendar.getInstance().getTime());
							    	if(!seq[1].equals(formattedDate))
							    	{
							    		seq[1]=formatdDate;
							    	}
							    	seq_num=seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+seq[3];
							    	String sqnum=Integer.toString(Integer.parseInt(seq[3])+1);
							    	StringBuilder sb = new StringBuilder();
									for (int toPrepend=6-sqnum.length(); toPrepend>0; toPrepend--) {
									    sb.append('0');
									}
									sb.append(sqnum);
							    	String save_po="update "+company+".mr_transaction_seq set modified_date='"+formattedDate+"',tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_orgin='"+tranname+"' ";
									statement.executeUpdate(save_po);
									double new_amt_lt=Double.parseDouble(amt);
									BigDecimal ba = new BigDecimal(new_amt_lt);
			                		ba = ba.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
			                		new_amt_lt = ba.doubleValue();
			                		String select_vendor="select * from "+company+".mr_customer_master where cust_code='"+vendor_id+"' ";
									ResultSet resultSet1 = statement.executeQuery(select_vendor);
									double new_paid=0;
									if(resultSet1.next())
									{
										String avail_credit=resultSet1.getString("advance_amt");
										new_paid=Double.parseDouble(avail_credit)+(Double.parseDouble(amt)+Double.parseDouble(disc));
										BigDecimal bp = new BigDecimal(new_paid);
				                		bp = bp.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
				                		new_paid = bp.doubleValue();
				                		
									}
									double last_paid_amt=Double.parseDouble(amt)+Double.parseDouble(disc);
									String updates="update "+company+".mr_customer_master set advance_amt='"+new_paid+"',last_payment_date='"+formattedDate+"',last_paid_amt='"+last_paid_amt+"' where cust_code = '"+vendor_id+"' ";
									statement.executeUpdate(updates);
									
									String save_file="insert into "+company+".mr_sd_customer_payment_details(`ref_tran_num`,seq_num, `ref_cust_id`, `dr_amt`,cr_amt, `payment_method`, `paycode`, `bank_name`, `cheque_no`, `pdc_check`, `ac_payee`, `remark`, `pay_by`, `note`, `store_id`, `username`, `created_date`, `modified_date`,disc_amt,issue_date,status) "
											+ "VALUES('"+seq_num+"','"+seq_num+"','"+vendor_id+"','"+amt+"','0','"+payment_mode+"','"+paycode+"','"+bank_name+"','"+cheque+"','"+pdc+"','"+ac_payee+"','"+remark+"','"+pay_to+"','"+pay_note+"','"+store+"','"+username+"','"+formattedDate+"','"+formattedDate+"','"+disc+"','"+start_date+"','2') ";
									statement.executeUpdate(save_file);
									
									String query6="insert into "+company+".mr_sd_customer_payment(`ref_tran_num`, `ref_cust_id`, `invo_num`, `invo_date`, `invo_amt`, `paid_amt`, `bal_amt`, `store_id`, `created_date`, `modified_date`, `status`,type,doc_num) values "
											+ "('"+seq_num+"','"+vendor_id+"','"+seq_num+"','"+start_date+"','"+amt+"','0','"+amt+"','"+store+"','"+formattedDate+"','"+formattedDate+"','2','AD','"+inv+"')";
									statement.executeUpdate(query6);
									
									
									
									String query="SELECT * FROM "+company+".mr_sd_customer_payment where ref_tran_num='"+po_num+"' and ref_cust_id='"+vendor_id+"' "; 
									ResultSet resultSet = statement1.executeQuery(query);
									if(resultSet.next())
									{
										String paid=resultSet.getString("paid_amt");
										String bal=resultSet.getString("bal_amt");
										double new_paid_bal=Double.parseDouble(paid)+Double.parseDouble(amt)+Double.parseDouble(disc);
										double new_bal=Double.parseDouble(bal)-(Double.parseDouble(amt)+Double.parseDouble(disc));
										BigDecimal bd = new BigDecimal(new_bal);
				                		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
				                		new_bal = bd.doubleValue();
				                		String update="update "+company+".mr_sd_customer_payment set paid_amt='"+new_paid_bal+"',bal_amt='"+new_bal+"' where ref_tran_num='"+po_num+"' and ref_cust_id='"+vendor_id+"' ";
										statement.executeUpdate(update);
										 
										
									}
									
									
									if(!comboBox.getSelectedItem().toString().equals("CASH"))
									{
										String query21="insert into "+company+".mr_cheque_details( `tran_num`, `pay_by`,`pay_id`, `cr`, `dr`, `issue_date`, `cheque_no`, `cheque_date`,  `printing_user`,  `handover_user`, `posted_user`, `remark`, `note`,bank_name,status,page_type,cheque_handover,cheque_printed) "
												+ "VALUES ('"+seq_num+"','"+vendor_name+"','"+vendor_id+"','"+new_amt_lt+"','0','"+formattedDate+"','"+cheque+"','"+start_date+"','"+username+"','"+username+"','"+username+"','"+textField_5.getText()+"','"+textArea.getText()+"','"+bank_name+"','OPEN','AR','1','1')";
										statement.executeUpdate(query21);
									}
									
									report_master.sd_pay_voucher.report(con,seq_num,vendor_id,company);
									con.close();
									JOptionPane.showMessageDialog(frame, "Saved Successfully");	
									frame.setVisible(false);
									sales_master.cust_account_payable.get_po_term_disp(company);
							    }
						    }
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}  
			}
		});
	    menuItem.setBorder(null);
	    menuItem.setIcon(new ImageIcon(save));
	    menuItem.setBackground(new Color(243, 229, 216));
	    menuItem.setBounds(457, 11, 135, 48);
	    frame.getContentPane().add(menuItem);
	    
	    JMenuItem menuItem_1 = new JMenuItem("Refresh");
	    menuItem_1 = new JMenuItem("Refresh",KeyEvent.VK_F5);
	    menuItem_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		textField_1.setText("");
	    		comboBox_2.setSelectedIndex(-1);
	    		textField_4.setText("");
	    		textField_5.setText("");
	    		textField_6.setText("");
	    		textArea.setText("");
	    	}
	    });
	    menuItem_1.setBorder(null);
	    menuItem_1.setIcon(new ImageIcon(refresh));
	    menuItem_1.setBackground(new Color(243, 229, 216));
	    menuItem_1.setBounds(377, 11, 80, 48);
	    frame.getContentPane().add(menuItem_1);
	    
	    JMenuItem menuItem_2 = new JMenuItem("Exit");
	    menuItem_2.setAccelerator(KeyStroke.getKeyStroke("F4"));
	    menuItem_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		frame.setVisible(false);
	    		accounts_master.Accountpayable.get_po_term_disp(company, vendor_id, decimal);
	    	}
	    });
	    menuItem_2.setBorder(null);
	    menuItem_2.setIcon(new ImageIcon(exit));
	    menuItem_2.setBackground(new Color(243, 229, 216));
	    menuItem_2.setBounds(317, 11, 60, 48);
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
		    
		    
		    
		    JLabel lblAmount = new JLabel("Paying Amount :");
		    lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    lblAmount.setBounds(21, 73, 95, 21);
		    frame.getContentPane().add(lblAmount);
		    
		    textField_1 = new JTextField();
		    textField_1.addKeyListener(new KeyAdapter() {
		    	@Override
		    	public void keyTyped(KeyEvent e) {
		    		char vChar = e.getKeyChar();
				    if (textField_1.getText().equals(""))
				        dot = false;
				    if (dot == false){
				        if (vChar == '.') dot = true;
				        else if (!(Character.isDigit(vChar)
				                || (vChar == KeyEvent.VK_BACK_SPACE)
				                || (vChar == KeyEvent.VK_DELETE))) {
				                e.consume();
				        }
				    } else {
				        if (!(Character.isDigit(vChar)
				                || (vChar == KeyEvent.VK_BACK_SPACE)
				                || (vChar == KeyEvent.VK_DELETE))) {
				                e.consume();
				        }
				    }
		    	}
		    });
		    textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		    textField_1.setBackground(Color.WHITE);
		    textField_1.setColumns(10);
		    textField_1.setBounds(20, 95, 246, 37);
		    frame.getContentPane().add(textField_1);
		    textField_1.requestFocus();
		    
		    comboBox_2 = new JComboBox<String>();
		    comboBox_2.setEditable(true);
		    comboBox_2.setEnabled(false);
		    comboBox_2.setBounds(124, 218, 395, 22);
		    frame.getContentPane().add(comboBox_2);
		    
		    
		    
		    JLabel lblPaymentMode = new JLabel("Payment Mode: ");
		    lblPaymentMode.setFont(new Font("Tahoma", Font.BOLD, 11));
		    lblPaymentMode.setBounds(21, 143, 113, 21);
		    frame.getContentPane().add(lblPaymentMode);
		    
		    JLabel lblBankName = new JLabel("Bank Name:");
		    lblBankName.setBounds(18, 219, 73, 21);
		    frame.getContentPane().add(lblBankName);
		    
		    
		    final JLabel lblDate = new JLabel("Date :");
		    lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		    lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		    lblDate.setBounds(384, 142, 135, 22);
		    frame.getContentPane().add(lblDate);
		    
		    JLabel lblCheque = new JLabel("Cheque# :");
		    lblCheque.setBounds(18, 261, 73, 14);
		    frame.getContentPane().add(lblCheque);
		    
		    textField_4 = new JTextField();
		    textField_4.setEnabled(false);
		    textField_4.setColumns(10);
		    textField_4.setBounds(124, 258, 395, 20);
		    frame.getContentPane().add(textField_4);
		    
		    JLabel lblRemarks = new JLabel("Remarks:");
		    lblRemarks.setBounds(18, 294, 73, 14);
		    frame.getContentPane().add(lblRemarks);
		    
		    textField_5 = new JTextField();
		    textField_5.setColumns(10);
		    textField_5.setBounds(124, 291, 395, 20);
		    frame.getContentPane().add(textField_5);
		    
		    JLabel lblPayTo = new JLabel("Received From: ");
		    lblPayTo.setBounds(18, 332, 98, 14);
		    frame.getContentPane().add(lblPayTo);
		    
		    textField_6 = new JTextField(vendor_name);
		    textField_6.setColumns(10);
		    textField_6.setBounds(124, 329, 395, 20);
		    frame.getContentPane().add(textField_6);
		    
		    JLabel lblNote = new JLabel("Note:");
		    lblNote.setBounds(17, 362, 73, 14);
		    frame.getContentPane().add(lblNote);
		    
		    
		    
		    JLabel lblPaycode = new JLabel("Paycode:");
		    lblPaycode.setHorizontalAlignment(SwingConstants.CENTER);
		    lblPaycode.setFont(new Font("Tahoma", Font.BOLD, 12));
		    lblPaycode.setBounds(197, 142, 119, 21);
		    frame.getContentPane().add(lblPaycode);
		    
		    comboBox_1 = new JComboBox<String>();
		    comboBox_1.setEnabled(false);
		    comboBox_1.addItem("CASH");
		    comboBox_1.addItem("OMANI RIYAL");
		    comboBox_1.addItem("USD");
		    comboBox_1.addItem("SAUDI RIYAL");
		    comboBox_1.setBounds(197, 176, 119, 29);
		    frame.getContentPane().add(comboBox_1);
		    
		    
		  	dateChooser = new JDateChooser();
		    dateChooser.setBounds(384, 174, 135, 29);
		    frame.getContentPane().add(dateChooser);
		    dateChooser.setDateFormatString("yyyy-MM-dd");
		    dateChooser.setVisible(false);
		    
		    
		    comboBox = new JComboBox<String>();
		    comboBox.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		if(!comboBox.getSelectedItem().toString().equals("CASH"))
		    		{
		    			comboBox_1.setEnabled(false);
		    			comboBox_2.setEnabled(true);
		    			textField_4.setEnabled(true);
		    			dateChooser.setVisible(true);
		    			lblDate.setText("Cheque Date :");
		    		}
		    		else
		    		{
		    			comboBox_1.setEnabled(true);
		    			comboBox_2.setSelectedIndex(-1);
		    			comboBox_2.setEnabled(false);
		    			textField_4.setEnabled(false);
		    			lblDate.setText("Date :");
		    			dateChooser.setVisible(true);
		    		}
		    	}
		    });
		    comboBox.addItem("CASH");
		    comboBox.addItem("BANK");
		    comboBox.setBounds(21, 175, 113, 29);
		    frame.getContentPane().add(comboBox);
			
		    textArea = new JTextArea();
		    frame.getContentPane().add(textArea);
		    textArea.setBounds(127, 358, 393, 65);
		    
		    
		    
		    ScrollPane scrollPane = new ScrollPane();
		    scrollPane.setBounds(124, 355, 397, 69);
		    frame.getContentPane().add(scrollPane);
		    
		    JLabel lblInvoiceNumber = new JLabel("Invoice Number : ");
		    lblInvoiceNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    lblInvoiceNumber.setBounds(288, 75, 130, 21);
		    frame.getContentPane().add(lblInvoiceNumber);
		    
		    textField = new JTextField();
		    textField.setBounds(288, 95, 240, 37);
		    frame.getContentPane().add(textField);
		    textField.setColumns(10);
		    
		   
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 544, 475);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
