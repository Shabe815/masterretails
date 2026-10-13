package sales_master;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.json.JSONException;

import CafePro.Mainpage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.swing.JComboBox;

public class customer_payment {

	private JFrame frmCustomerPayment;
	static JTextField textField;
	static JTextField textField_1;
	static JTextField textField_2;
	private JTextField textField_3;
	static JTextField textField_5;
	static JTextField textField_4;
	private boolean dot = false;
	private JTextArea txtrMultiLineRemarks;
	private static JTextField textField_6;
	private JComboBox<String> comboBox;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customer_payment window = new customer_payment( "mr_pro","admin","S01","S0101", null,"\\\\Accounts\\epson","");
					window.frmCustomerPayment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public customer_payment(String company, String username, String store_name, String reg_nme,String version,String printer,String pagetype) {
		initialize(company, username, store_name, reg_nme, version,printer,pagetype);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company,final String username ,final String store_name ,final String reg_nme,final String version,final String printer,final String pagetype) {
		frmCustomerPayment = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frmCustomerPayment.setIconImage(img);
		frmCustomerPayment.setVisible(true);
		frmCustomerPayment.getContentPane().setBackground(Color.DARK_GRAY);
		frmCustomerPayment.setForeground(new Color(154, 205, 50));
		frmCustomerPayment.setTitle("CUSTOMER PAYMENT:");
		frmCustomerPayment.setBounds(100, 100, 749, 496);
		frmCustomerPayment.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btncustPay.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btncustPay.setEnabled(true);
				}
				
			}
			@Override
		      public void windowIconified(WindowEvent we) {
				frmCustomerPayment.setState(JFrame.NORMAL);
		      }
		});
		frmCustomerPayment.setAlwaysOnTop(true);
		frmCustomerPayment.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer:");
		lblNewLabel.setForeground(new Color(100, 149, 237));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 13, 160, 25);
		frmCustomerPayment.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_userdisp(company, textField.getText());
			}
		});
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setBounds(180, 13, 397, 25);
		frmCustomerPayment.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblBalanceOutstanding = new JLabel("Balance Outstanding:");
		lblBalanceOutstanding.setForeground(new Color(100, 149, 237));
		lblBalanceOutstanding.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBalanceOutstanding.setFont(new Font("Arial", Font.BOLD, 15));
		lblBalanceOutstanding.setBounds(10, 49, 160, 25);
		frmCustomerPayment.getContentPane().add(lblBalanceOutstanding);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(180, 50, 100, 25);
		frmCustomerPayment.getContentPane().add(textField_1);
		
		JLabel lblBalanceCredit = new JLabel("Balance credit:");
		lblBalanceCredit.setForeground(new Color(100, 149, 237));
		lblBalanceCredit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBalanceCredit.setFont(new Font("Arial", Font.BOLD, 15));
		lblBalanceCredit.setBounds(445, 49, 115, 25);
		frmCustomerPayment.getContentPane().add(lblBalanceCredit);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(587, 49, 100, 25);
		frmCustomerPayment.getContentPane().add(textField_2);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Cust_Chooser(company, "cust_pay", username, store_name, "","", version,"1");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(587, 13, 115, 27);
		btnNewButton.setIcon(new ImageIcon(serch));
		frmCustomerPayment.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(465, 85, 222, 296);
		frmCustomerPayment.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("1");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("1");
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 30));
		btnNewButton_1.setBounds(10, 11, 60, 60);
		panel.add(btnNewButton_1);
		
		JButton button = new JButton("2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("2");
			}
		});
		button.setFont(new Font("Arial", Font.BOLD, 30));
		button.setBounds(80, 11, 60, 60);
		panel.add(button);
		
		JButton button_1 = new JButton("3");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("3");
			}
		});
		button_1.setFont(new Font("Arial", Font.BOLD, 30));
		button_1.setBounds(150, 11, 60, 60);
		panel.add(button_1);
		
		JButton button_2 = new JButton("4");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("4");
			}
		});
		button_2.setFont(new Font("Arial", Font.BOLD, 30));
		button_2.setBounds(10, 82, 60, 60);
		panel.add(button_2);
		
		JButton button_3 = new JButton("5");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("5");
			}
		});
		button_3.setFont(new Font("Arial", Font.BOLD, 30));
		button_3.setBounds(80, 82, 60, 60);
		panel.add(button_3);
		
		JButton button_4 = new JButton("6");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("6");
			}
		});
		button_4.setFont(new Font("Arial", Font.BOLD, 30));
		button_4.setBounds(150, 82, 60, 60);
		panel.add(button_4);
		
		JButton button_5 = new JButton("7");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("7");
			}
		});
		button_5.setFont(new Font("Arial", Font.BOLD, 30));
		button_5.setBounds(10, 153, 60, 60);
		panel.add(button_5);
		
		JButton button_6 = new JButton("8");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("8");
			}
		});
		button_6.setFont(new Font("Arial", Font.BOLD, 30));
		button_6.setBounds(80, 153, 60, 60);
		panel.add(button_6);
		
		JButton button_7 = new JButton("9");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("9");
			}
		});
		button_7.setFont(new Font("Arial", Font.BOLD, 30));
		button_7.setBounds(150, 153, 60, 60);
		panel.add(button_7);
		
		JButton button_8 = new JButton("0");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("0");
			}
		});
		button_8.setFont(new Font("Arial", Font.BOLD, 30));
		button_8.setBounds(80, 223, 60, 60);
		panel.add(button_8);
		
		JButton btnBackSpace = new JButton("<HTML>BACK<BR>SPACE</HTML>");
		btnBackSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField_3.getText().equals(""))
				{
					textField_3.setText(textField_3.getText().substring(0, textField_3.getText().length() - 1));
				}
			}
		});
		btnBackSpace.setHorizontalAlignment(SwingConstants.LEFT);
		btnBackSpace.setFont(new Font("Arial", Font.BOLD, 12));
		btnBackSpace.setBounds(150, 224, 60, 60);
		panel.add(btnBackSpace);
		
		JButton button_10 = new JButton(".");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn(".");
			}
		});
		button_10.setFont(new Font("Arial", Font.BOLD, 30));
		button_10.setBounds(10, 224, 60, 60);
		panel.add(button_10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		textField_3.setBounds(185, 197, 268, 38);
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_3.getText().equals(""))
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
		frmCustomerPayment.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblAmountReceived = new JLabel("Amount received :");
		lblAmountReceived.setForeground(new Color(100, 149, 237));
		lblAmountReceived.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmountReceived.setFont(new Font("Arial", Font.BOLD, 15));
		lblAmountReceived.setBounds(10, 206, 160, 25);
		frmCustomerPayment.getContentPane().add(lblAmountReceived);
		
		JLabel lblRemarks = new JLabel("Remarks:");
		lblRemarks.setForeground(new Color(100, 149, 237));
		lblRemarks.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRemarks.setFont(new Font("Arial", Font.BOLD, 15));
		lblRemarks.setBounds(10, 257, 160, 27);
		frmCustomerPayment.getContentPane().add(lblRemarks);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setForeground(new Color(100, 149, 237));
		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 15));
		lblStatus.setBounds(290, 49, 58, 25);
		frmCustomerPayment.getContentPane().add(lblStatus);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(358, 49, 79, 25);
		frmCustomerPayment.getContentPane().add(textField_5);
		
		txtrMultiLineRemarks = new JTextArea();
		txtrMultiLineRemarks.setBounds(180, 259, 268, 122);
		frmCustomerPayment.getContentPane().add(txtrMultiLineRemarks);
		
		JButton btnNewButton_2 = new JButton("CLEAR");
		btnNewButton_2.setBounds(38, 392, 135, 38);
		frmCustomerPayment.getContentPane().add(btnNewButton_2);
		
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		JButton btnSave = new JButton("SAVE & PRINT");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCustomerPayment.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				String amount= textField_3.getText();
				String bal= textField_1.getText();
				double advance=0;
				double disc=0;
				if(textField_3.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frmCustomerPayment, "Please Enter The Amount");
					frmCustomerPayment.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					
				}
				else
				{
					if(!textField_7.getText().equals(""))
					{
						disc=Double.parseDouble(textField_7.getText());
					}
					if(Double.parseDouble(amount) <= Double.parseDouble(bal))
					{
						advance=0;
					}
					else
					{
						advance=(disc+Double.parseDouble(amount)) - Double.parseDouble(bal);
					}
						
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj); 
				    final Calendar cal = Calendar.getInstance();
			        final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					String seq_num = null;
					try {
						statement = con.createStatement();
						
						String trans_seq = "select tran_seq from "+company+".mr_transaction_seq where tran_orgin='AR' ";
						ResultSet resultSet1 = statement.executeQuery(trans_seq);
					    if (resultSet1.next())
					    {
					    	seq_num=resultSet1.getString("tran_seq");
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
					    	String save_po="update "+company+".mr_transaction_seq set modified_date='"+formattedDate+"',tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_orgin='AR' ";
							statement.executeUpdate(save_po);
					    }
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_customer_master where cust_code='"+textField_4.getText()+"' ");
						if(resultSet.next())
						{
							//String credit_limit=resultSet.getString("credit_limit");
							String credit_bal=resultSet.getString("avail_credit");
							String advance_amt=resultSet.getString("advance_amt");
							String default_ar_acc=resultSet.getString("default_ar_acc");
							String sts="0";
							if(pagetype.equals("SD"))
							{
								sts="1";
							}
							String query="insert into "+company+".mr_customer_payment(seq_num,`cust_code`, `amount`,remarks,ref_reg_num, `created_date`,paycode,discount,store_id,username,time,status) values('"+seq_num+"','"+textField_4.getText()+"','"+amount+"','"+txtrMultiLineRemarks.getText()+"','"+reg_nme+"','"+formattedDate+"','"+comboBox.getSelectedItem().toString()+"','"+disc+"','"+store_name+"','"+username+"','"+times.format(cal.getTime())+"','"+sts+"') ";
							statement.executeUpdate(query);
							double new_advance=0;
							double new_credit_bal=0;
							if(!advance_amt.equals("0"))
							{
								new_advance=Float.parseFloat(advance_amt)+advance;
								BigDecimal be = new BigDecimal(new_advance);
			            		be = be.setScale(2, BigDecimal.ROUND_HALF_UP);
			            		new_advance = be.doubleValue();
								new_credit_bal=Float.parseFloat(credit_bal);
							}
							else
							{
								if(advance>0)
								{
									new_advance=Float.parseFloat(advance_amt)+advance;
									new_credit_bal=(Float.parseFloat(amount)-advance);
									BigDecimal bd = new BigDecimal(new_credit_bal);
				            		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				            		new_credit_bal = bd.doubleValue();
									if(new_credit_bal==0)
									{
										new_credit_bal=Float.parseFloat(credit_bal)+disc;
									}
									else
									{
										new_credit_bal=Float.parseFloat(credit_bal)+new_credit_bal;
										BigDecimal be = new BigDecimal(new_credit_bal);
					            		be = be.setScale(2, BigDecimal.ROUND_HALF_UP);
					            		new_credit_bal = be.doubleValue();
									}
								}
								else
								{
									new_advance=0;
									new_credit_bal=disc+Float.parseFloat(amount)+Float.parseFloat(credit_bal);
								}
								
							}
							String query1="update "+company+".mr_customer_master set  `avail_credit`='"+new_credit_bal+"',advance_amt='"+new_advance+"',`last_payment_date`='"+formattedDate+"',`last_paid_amt`='"+amount+"' where cust_code='"+textField_4.getText()+"'  ";
							statement.executeUpdate(query1);
							
							
							
							String seqnum=null;
							String transseq = "select tran_seq from "+company+".mr_transaction_seq where tran_orgin='JV' ";
							Statement statement30=con.createStatement();  
							ResultSet resultSet10 = statement30.executeQuery(transseq);
						    if (resultSet10.next())
						    {
						    	seqnum=resultSet10.getString("tran_seq");
						    	String[] seq=seqnum.split("-");
						    	DateFormat df = new SimpleDateFormat("yy");
						    	String formatdDate = df.format(Calendar.getInstance().getTime());
						    	if(!seq[1].equals(formattedDate))
						    	{
						    		seq[1]=formatdDate;
						    	}
						    	seqnum=seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+seq[3];
						    	String sqnum=Integer.toString(Integer.parseInt(seq[3])+1);
						    	StringBuilder sb = new StringBuilder();
								for (int toPrepend=6-sqnum.length(); toPrepend>0; toPrepend--) {
								    sb.append('0');
								}
								sb.append(sqnum);
						    	String save_po="update "+company+".mr_transaction_seq set modified_date='"+formattedDate+"',tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_orgin='JV' ";
								statement.executeUpdate(save_po);
						    }
			                
						    String ar_payable=default_ar_acc;
						    String cash_acc="";
			                String query15="SELECT * FROM "+company+".mr_store_accounts where  ref_store_id='"+store_name+"' "; 
							Statement statement5=con.createStatement();  
			                ResultSet resultSet5 = statement5.executeQuery(query15);
			                if(resultSet5.next())
			                {
			                	//ar_payable=default_ar_acc;
			                	cash_acc=resultSet5.getString("cash_acc");
			                }
			                String bank_acc="";
			                String query115="SELECT * FROM "+company+".mr_acc_paycode where status='1' "; 
							Statement statement115=con.createStatement();  
			                ResultSet resultSet115 = statement115.executeQuery(query115);
			                if(resultSet115.next())
			                {
			                	bank_acc=resultSet115.getString("default_ac");
			                }
			                String query25="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_store='"+store_name+"'"; 
							Statement statement25=con.createStatement();  
			                ResultSet resultSet25 = statement25.executeQuery(query25);
			                String ar_name="";
			                String bankname="";
			                String cash_name="";
			                while(resultSet25.next())
			                {
			                	if(resultSet25.getString("acc_code").equals(ar_payable))
			                	{
			                		ar_name=resultSet25.getString("acc_name");
			                	}
			                	if(resultSet25.getString("acc_code").equals(cash_acc))
			                	{
			                		cash_name=resultSet25.getString("acc_name");
			                	}
			                	if(resultSet25.getString("acc_code").equals(bank_acc))
			                	{
			                		bankname=resultSet25.getString("acc_name");
			                	}
			                }
			                
			                String branch="";
			                String branch_query = "select * from "+company+".mr_acc_segment where store_id='"+store_name+"' ";
							Statement statement3=con.createStatement();  
							ResultSet resultSet3 = statement3.executeQuery(branch_query);
						    if (resultSet3.next())
						    {
						    	if(resultSet3.getString("segment").equals("Branch"))
						    	{
						    		branch=resultSet3.getString("name");
						    	}
						    
						    }
						    double balance=0;
						    String acc_name="";
						    String acc_code="";
						    if(comboBox.getSelectedItem().toString().equals("BANK"))
						    {
						    	acc_name=bankname;
						    	acc_code=bank_acc;
						    	String query39="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+bank_acc+"'"; 
								Statement statement39=con.createStatement();  
				                ResultSet resultSet39= statement39.executeQuery(query39);
				                float cr=0;
				                float dr=0;
				                while(resultSet39.next())
				                {
				                	cr+=Float.parseFloat(resultSet39.getString("cr_amt"));
				                	dr+=Float.parseFloat(resultSet39.getString("dr_amt"));
				                }
				                float bal_asset=dr-cr;
								balance=bal_asset+Double.parseDouble(amount);
						    }
						    else
						    {
						    	String query37="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+cash_acc+"'"; 
								Statement statement37=con.createStatement();  
				                ResultSet resultSet37= statement37.executeQuery(query37);
				                float cr=0;
				                float dr=0;
				                while(resultSet37.next())
				                {
				                	cr+=Float.parseFloat(resultSet37.getString("cr_amt"));
				                	dr+=Float.parseFloat(resultSet37.getString("dr_amt"));
				                }
								float bal_asset=dr-cr;
								balance=bal_asset+Double.parseDouble(amount);
								acc_name=cash_name;
								acc_code=cash_acc;
						    }
						    
							
							String query38="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+ar_payable+"'"; 
							Statement statement38=con.createStatement();  
			                ResultSet resultSet38= statement38.executeQuery(query38);
			                float cr_pay=0;
			                float dr_pay=0;
			                while(resultSet38.next())
			                {
			                	cr_pay+=Float.parseFloat(resultSet38.getString("cr_amt"));
			                	dr_pay+=Float.parseFloat(resultSet38.getString("dr_amt"));
			                }
							float bal_liable=dr_pay-cr_pay;
							double bal_lia=bal_liable-Float.parseFloat(amount);
						    
						    String disc_applied="";
			                String disc_jv="";
			                String disc_leger="";
						    if(disc!=0)
						    {
						    	
				                
				                String query18="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_grp_name='DISCOUNT ON SALES' and acc_store='"+store_name+"'"; 
								Statement statement18=con.createStatement();  
				                ResultSet resultSet18= statement18.executeQuery(query18);
				                String disc_name="";
				                String disc_code="";
				                if(resultSet18.next())
				                {
				                	disc_name=resultSet18.getString("acc_name");
				                	disc_code=resultSet18.getString("acc_code");
				                }
				                disc_applied=",('"+seqnum+"','"+formattedDate+"','"+times.format(cal.getTime())+"','EXPENSE','"+disc_code+"','"+disc_name+"','0','"+disc+"','DISCOUNT APPLIED ON CUSTOMER PAYMENT ','"+formattedDate+"','"+formattedDate+"')";
				                disc_jv=",('"+seqnum+"','"+seqnum+"','"+disc_code+"','"+disc_name+"','"+disc+"','0','"+seq_num+"','"+seq_num+"-"+textField_4.getText()+"-"+textField.getText()+"','"+formattedDate+"','"+formattedDate+"','OPEN'),('"+seqnum+"','"+seqnum+"','"+ar_payable+"','"+ar_name+"','0','"+disc+"','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN')";
				                
				                String query29="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+disc_code+"'"; 
								Statement statement9=con.createStatement();  
				                ResultSet resultSet9= statement9.executeQuery(query29);
				                float cr_disc=0;
				                float dr_disc=0;
				                while(resultSet9.next())
				                {
				                	cr_disc+=Float.parseFloat(resultSet9.getString("cr_amt"));
				                	dr_disc+=Float.parseFloat(resultSet9.getString("dr_amt"));
				                }
								float bal_disc=dr_disc-cr_disc;
								double bal_discount=bal_disc+disc;
								
								double new_disc_amt=bal_lia-disc;
								
								disc_leger=",('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+disc_code+"','"+disc+"','0','"+bal_discount+"','"+seq_num+"','"+seq_num+"','PO','"+formattedDate+"','"+formattedDate+"','OPEN' ),('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+ar_payable+"','0','"+disc+"','"+new_disc_amt+"','"+seq_num+"','"+seq_num+"','AR','"+formattedDate+"','"+formattedDate+"','OPEN' )";
				                
						    }
						    
						    if(advance>0)
						    {
						    	
						    }
						    else
						    {
						    	
						    	
						    }
						    String insert_acc_header="insert into "+company+".mr_accounts_trans_header (tran_seqencecode,tran_date,tran_time,tran_remark,tran_debit_amt,tran_credit_amt,tran_status,tran_user,tran_store,tran_orgin,created_date,modified_date) values('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','Amount Payed by Customer','"+amount+"','"+amount+"','POSTED','"+username+"','"+store_name+"','AR','"+formattedDate+"','"+formattedDate+"' )";
			                statement.executeUpdate(insert_acc_header);
			                
			                String insert_acc_detls="insert into "+company+".mr_accounts_tran_detls (tran_seqence_code,tran_date,tran_time,tran_type,tran_accode,tran_acname,tran_debit_amt,tran_credit_amt,tran_remark,created_date,modified_date) values('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','ASSET','"+acc_code+"','"+acc_name+"','0','"+amount+"','Customer Payment','"+formattedDate+"','"+formattedDate+"'),('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','ASSET','"+ar_payable+"','"+ar_name+"','"+amount+"','0','Customer Payment','"+formattedDate+"','"+formattedDate+"') "+disc_applied+" ";
							statement.executeUpdate(insert_acc_detls);
							
							String save_jv="insert into "+company+".mr_jv_header( `jv_num`, `date`, `branch`, `username`, `doc_num`, `remark`, `notes`, `store_id`, `created_date`, `modified_date`, `status`,`is_approved`, `approved_by`, `is_posted`, `posted_by`, `posted_date`, `approved_date`,jv_amt,vendor_cust_id) "
									+ "values('"+seqnum+"','"+formattedDate+"','"+branch+"','"+username+"','"+seq_num+"','"+seq_num+"-"+textField_4.getText()+"-"+textField.getText()+"','"+seq_num+"','"+store_name+"','"+formattedDate+"','"+formattedDate+"','Posted','1','"+username+"','1','"+username+"','"+formattedDate+"','"+formattedDate+"','0','"+textField_4.getText()+"' )";
							statement.executeUpdate(save_jv);
							
							String queryjd="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,cheque_date) "
									+ "VALUES ('"+seqnum+"','"+seqnum+"','"+acc_code+"','"+acc_name+"','"+amount+"','0','"+seq_num+"','"+seq_num+"-"+textField_4.getText()+"-"+textField.getText()+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"'),"
											+ "('"+seqnum+"','"+seqnum+"','"+ar_payable+"','"+ar_name+"','0','"+amount+"','"+seq_num+"','"+seq_num+"-"+textField_4.getText()+"-"+textField.getText()+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"') "
											+ ""+disc_jv+" ";
							statement.executeUpdate(queryjd);
							
							
							
							String save_ledger="insert into "+company+".mr_acc_ledger (`jv_num`, `tran_date`, `post_date`, `ac_code`, `dr_amt`, `cr_amt`, `balance`, `notes`, `remark`, `source`, `created_date`, `modified_date`,status) values ('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+acc_code+"','"+amount+"','0','"+balance+"','"+seq_num+"','"+seq_num+"','AR','"+formattedDate+"','"+formattedDate+"','OPEN' ),('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+ar_payable+"','0','"+amount+"','"+bal_lia+"','"+seq_num+"','"+seq_num+"','AR','"+formattedDate+"','"+formattedDate+"','OPEN' ) "+disc_leger+" ";
							statement.executeUpdate(save_ledger);
							
						}
						else
						{
							JOptionPane.showMessageDialog(frmCustomerPayment, "Some Error Occur, Please Try Again");
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					try {
						byte[] open = {27,112,48,55,121};
				        PrintService pservice = 
				        PrintServiceLookup.lookupDefaultPrintService(); 
				        DocPrintJob job = pservice.createPrintJob();
				        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
				        Doc doc = new SimpleDoc(open,flavor,null);
				        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
				        try {
				            job.print(doc, aset);
							
				        } catch (PrintException ex) {
				            System.out.println(ex.getMessage());
				        }
						report_master.customer_voucher.report(seq_num,con, printer,textField_1.getText(),company,pagetype);
						frmCustomerPayment.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						JOptionPane.showMessageDialog(frmCustomerPayment, "Saved Successfully");
						
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						frmCustomerPayment.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
					
					frmCustomerPayment.setVisible(false);
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.btncustPay.setEnabled(true);
						PosscreenArabic.txtDisc.requestFocus();
					}
					else
					{
						posscreen.btncustPay.setEnabled(true);
						posscreen.txtDisc.requestFocus();
					}
					
				}
			}
		});
		btnSave.setBounds(211, 392, 148, 38);
		btnSave.setIcon(new ImageIcon(save));
		frmCustomerPayment.getContentPane().add(btnSave);
		
		Image printer1=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JButton btnPrintStatement = new JButton("PRINT STATEMENT");
		btnPrintStatement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField_4.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frmCustomerPayment, "Select One Customer");
				}
				else
				{
					new Date_range(company, username, store_name, textField_4.getText(), version,textField.getText());
				}
				
			}
		});
		btnPrintStatement.setBounds(371, 392, 174, 38);
		btnPrintStatement.setIcon(new ImageIcon(printer1));
		frmCustomerPayment.getContentPane().add(btnPrintStatement);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCustomerPayment.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btncustPay.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btncustPay.setEnabled(true);
				}
				
			}
		});
		btnExit.setBounds(557, 392, 135, 38);
		btnExit.setIcon(new ImageIcon(exit));
		frmCustomerPayment.getContentPane().add(btnExit);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setVisible(false);
		textField_4.setEditable(false);
		textField_4.setBounds(10, 339, 116, 22);
		frmCustomerPayment.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(180, 88, 100, 25);
		frmCustomerPayment.getContentPane().add(textField_6);
		
		JLabel lblAdvanceAmount = new JLabel("Advance Amount");
		lblAdvanceAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdvanceAmount.setForeground(new Color(100, 149, 237));
		lblAdvanceAmount.setFont(new Font("Arial", Font.BOLD, 15));
		lblAdvanceAmount.setBounds(10, 87, 160, 25);
		frmCustomerPayment.getContentPane().add(lblAdvanceAmount);
		
		JLabel lblPaycode = new JLabel("Paycode :");
		lblPaycode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaycode.setForeground(new Color(100, 149, 237));
		lblPaycode.setFont(new Font("Arial", Font.BOLD, 15));
		lblPaycode.setBounds(10, 125, 160, 25);
		frmCustomerPayment.getContentPane().add(lblPaycode);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(180, 126, 268, 22);
		frmCustomerPayment.getContentPane().add(comboBox);
		
		JLabel lblDiscountApplied = new JLabel("Discount Applied : ");
		lblDiscountApplied.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiscountApplied.setForeground(new Color(100, 149, 237));
		lblDiscountApplied.setFont(new Font("Arial", Font.BOLD, 15));
		lblDiscountApplied.setBounds(10, 155, 160, 25);
		frmCustomerPayment.getContentPane().add(lblDiscountApplied);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.BOLD, 20));
		textField_7.setColumns(10);
		textField_7.setBounds(180, 155, 268, 38);
		frmCustomerPayment.getContentPane().add(textField_7);
		//get_paycodelist(company);
		
		comboBox.addItem("CASH");
		comboBox.addItem("BANK");
	}
	private void click_btn(String num)
	{
		try{
			String pass_string=textField_3.getText();
			pass_string+=num;
			textField_3.setText(pass_string);
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	static void  get_userdisp(String company, String serch)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement(); 
			String serch_term="";
			if(!serch.equals(""))
			{
				serch_term="and (`cust_name` LIKE '"+serch+"%' OR `cust_code` LIKE '"+serch+"%')";
			}
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_customer_master where status='1' "+serch_term+" ");
			if(resultSet.next())
			{
				String id=resultSet.getString("cust_code");
	            String name=resultSet.getString("cust_name");
	            String avail_limit=resultSet.getString("avail_credit");
	            String crdit_limit=resultSet.getString("credit_limit");
	            String rating=resultSet.getString("rating");
	            
	            textField.setText(name);
    			textField_2.setText(avail_limit);
    			textField_5.setText(rating);
    			double bal=Double.parseDouble(crdit_limit)-Double.parseDouble(avail_limit);
    			BigDecimal bd = new BigDecimal(bal);
        		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        		bal = bd.doubleValue();
    			textField_1.setText(Double.toString(bal));
    			textField_4.setText(id);
    			textField_6.setText(resultSet.getString("advance_amt"));
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
//	private void  get_paycodelist(String company)
//	{
//		try {
//			Connection con=Dbsetup.dbconn.getConnection();
//			Statement statement=con.createStatement();  
//			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store_paycodes ");
//			while(resultSet.next())
//			{
//				String paycode=resultSet.getString("paycode");
//	            if(!paycode.equals(""))
//	            {
//	            	comboBox.addItem(paycode);
//	            }
//			}
//			
//		} catch (Exception e) {
//			System.err.println(e);
//		}
//	}
}
