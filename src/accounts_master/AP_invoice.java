package accounts_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



import javax.swing.KeyStroke;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;

import org.json.JSONException;

import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Label;

import purchase_master.vendor_details;
import purchase_master.vendor_list;

public class AP_invoice {


	private JFrame frame;
	private JTextField txtNew;
	private JTextField textField_1;
	private JTextField textField_2;
	static JTextField textField_3;
	static JTextField textField_4;
	static JTextField textField_5;
	private JTextField textField_6;
	private JDateChooser dateChooser;
	private JComboBox <String> comboBox;
	private JComboBox<String> comboBox1;
	private JComboBox <String> comboBox_1;
	private JLabel lblNewLabel;
	private JButton button_1;
	private boolean dot=false;
	public static JTextField textField_11;
	private JMenuItem menuItem_2;
	private JMenuItem menuItem_1;
	private JTextField textField_12;
	public static JTextField textField_14;
	private JTextField textField_7;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AP_invoice window = new AP_invoice("demo","1.0","S01","ADMIN","APIN-21-01-000001","2","Unapproved","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param status 
	 * @throws JSONException 
	 */
	public AP_invoice(String company,String version,String store,String username,String jv_num,String decimal, String status,String language) throws JSONException {
		initialize(company,version,store,username,jv_num,decimal,status,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param version 
	 * @param company 
	 * @param username 
	 * @param jv_num 
	 * @param decimal 
	 * @param status 
	 * @param language 
	 * @throws JSONException 
	 */
	private void initialize(final String company,final String version,final String store, final String username, final String jv_num, final String decimal, final String status, final String language) throws JSONException {
		frame = new JFrame();
		frame.setBounds(100, 100, 597, 488);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		JLabel lblShift = new JLabel("Vendor Invoice");
		if(language.equals("Arabic"))
		{
			lblShift.setText("›« Ê—… «·»«∆⁄");
		}
		lblShift.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShift.setBounds(10, 11, 228, 44);
		panel.add(lblShift);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		final Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Exit");
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				AP_invoice_list.get_po_disp(company, "OPEN", "",language);
			}
		});
		menuItem.setBorder(null);
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(358, 7, 60, 48);
		panel.add(menuItem);
		
		JMenuItem menuItem_3 = new JMenuItem("Print");
		if(language.equals("Arabic"))
		{
			menuItem_3.setText("„ÿ»⁄…");
		}
		menuItem_3.setBounds(297, 7, 60, 48);
		panel.add(menuItem_3);
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					if(txtNew.getText().equals("NEW"))
					{
						
						if(language.equals("Arabic"))
						{
							JOptionPane.showMessageDialog(frame, "Save invoice first and try Again");
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "«Õ›Ÿ «·›« Ê—… √Ê·« ÊÕ«Ê· „—… √Œ—Ï");
						}
					}
					else
					{
						frame.setAlwaysOnTop(false);
						report_master.AP_invoice.report(con,txtNew.getText(),company);
					}
					
					//frame.setAlwaysOnTop(true);
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		menuItem_3.setBorder(null);
		menuItem_3.setIcon(new ImageIcon(printer));
		menuItem_3.setBackground(new Color(243, 229, 216));
		
		menuItem_1 = new JMenuItem("Refresh");
		if(language.equals("Arabic"))
		{
			menuItem_1.setText("Ì‰⁄‘");
		}
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(418, 7, 80, 48);
		panel.add(menuItem_1);
		
		menuItem_2 = new JMenuItem("Save");
		if(language.equals("Arabic"))
		{
			menuItem_2.setText("ÌÕ›Ÿ");
		}
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String inv_num=txtNew.getText();
					String branch=comboBox1.getSelectedItem().toString();
					String inv_date = null;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String invdate = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					if(!invdate.equals(""))
					{
						inv_date = sdf.format(dateChooser.getDate());
					}
					String user=lblNewLabel.getText();
					String doc_num=textField_1.getText();
					String remark=textField_2.getText();
					String supp=textField_14.getText();
					String supp_code=textField_11.getText();
					String inv_amt=textField_5.getText();
					String disc=textField_6.getText();
					String vat_type=comboBox_1.getSelectedItem().toString();
					String vat_amt=textField_7.getText();
					String net_total=textField_12.getText();
					String acc_no=textField_3.getText();
					String acc_desc=textField_4.getText();
					String type=comboBox.getSelectedItem().toString();
					if(doc_num.equals(""))
					{
						
						if(language.equals("Arabic"))
						{
							JOptionPane.showMessageDialog(frame, "√œŒ· —ﬁ„ «·ÊÀÌﬁ…");
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Enter Document Number");
						}
					}
					else if(supp.equals(""))
					{
						if(language.equals("Arabic"))
						{
							JOptionPane.showMessageDialog(frame, "Õœœ «”„ «·„Ê—œ");
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Select Supplier Name");
						}
						
					}
					else if(Float.parseFloat(inv_amt)==0)
					{
						
						if(language.equals("Arabic"))
						{
							JOptionPane.showMessageDialog(frame, "√œŒ· „»·€ «·›« Ê—…");
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Enter Invoice Amount");
						}
					}
					else
					{
						LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();
						String seq_num = null;
						if(txtNew.getText().equals("NEW"))
						{
							String trans_seq = "select tran_seq from "+company+".mr_transaction_seq where tran_orgin='APIN' ";
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
						    	String save_po="update "+company+".mr_transaction_seq set tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_orgin='APIN' ";
								statement.executeUpdate(save_po);
						    }
						    String insert="insert into "+company+".mr_invoice(invoice_num, branch, inv_date, username, doc_num, remark, supp_name, inv_amt, disc_amt, vat_type, vat_amt, net_total, acc_no, acc_desc, created_date, modified_date, status,type,supp_code,store) values('"+seq_num+"','"+branch+"','"+inv_date+"','"+user+"','"+doc_num+"','"+remark+"','"+supp+"','"+inv_amt+"','"+disc+"','"+vat_type+"','"+vat_amt+"','"+net_total+"','"+acc_no+"','"+acc_desc+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+type+"','"+supp_code+"','"+store+"')";
						    statement.executeUpdate(insert);
						    txtNew.setText(seq_num);
						}
						else
						{
							String update="update "+company+".mr_invoice set supp_code='"+supp_code+"',type='"+type+"',branch='"+branch+"', inv_date='"+inv_date+"', username='"+user+"', doc_num='"+doc_num+"', remark='"+remark+"', supp_name='"+supp+"', inv_amt='"+inv_amt+"', disc_amt='"+disc+"', vat_type='"+vat_type+"', vat_amt='"+vat_amt+"', net_total='"+net_total+"', acc_no='"+acc_no+"', acc_desc='"+acc_desc+"',  modified_date='"+formattedDate+"',store='"+store+"'  where  invoice_num='"+inv_num+"'";
						    statement.executeUpdate(update);
						}
						
						if(language.equals("Arabic"))
						{
							JOptionPane.showMessageDialog(frame, " „ «·Õ›Ÿ »‰Ã«Õ");
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
						
					}
				} catch (Exception e) {
					System.err.println(e);
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(500, 7, 70, 48);
		panel.add(menuItem_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 68, 570, 371);
		panel_1.setBackground(new Color(243, 229, 216));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblJournalVoucher = new JLabel("Invoice No.");
		if(language.equals("Arabic"))
		{
			lblJournalVoucher.setText("—ﬁ„ «·›« Ê—…");
		}
		lblJournalVoucher.setBounds(12, 10, 103, 16);
		panel_1.add(lblJournalVoucher);
		
		txtNew = new JTextField("NEW");
		txtNew.setEditable(false);
		txtNew.setBounds(103, 6, 179, 22);
		panel_1.add(txtNew);
		txtNew.setColumns(10);
		
		JLabel lblDate = new JLabel("Inv Date");
		if(language.equals("Arabic"))
		{
			lblDate.setText(" «—ÌŒ «·›« Ê—…");
		}
		lblDate.setBounds(292, 8, 56, 16);
		panel_1.add(lblDate);
		
		dateChooser = new JDateChooser(new Date());
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(394, 6, 168, 22);
		panel_1.add(dateChooser);
		
		JLabel lblBranch = new JLabel("Branch");
		if(language.equals("Arabic"))
		{
			lblBranch.setText("›—⁄");
		}
		lblBranch.setBounds(12, 41, 56, 16);
		panel_1.add(lblBranch);
		
		comboBox1 = new JComboBox<String>();
		comboBox1.setBounds(103, 41, 179, 22);
		panel_1.add(comboBox1);
		get_acc_brach(company , store);
		
		JLabel lblUsername = new JLabel("Username");
		if(language.equals("Arabic"))
		{
			lblUsername.setText("«”„ «·„” Œœ„");
		}
		lblUsername.setBounds(292, 39, 70, 16);
		panel_1.add(lblUsername);
		
		lblNewLabel = new JLabel(username);
		lblNewLabel.setBounds(394, 41, 168, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblDoc = new JLabel("Doc Num");
		if(language.equals("Arabic"))
		{
			lblDoc.setText("—ﬁ„ «·„” ‰œ");
		}
		lblDoc.setBounds(12, 70, 75, 22);
		panel_1.add(lblDoc);
		
		JLabel lblRemark = new JLabel("Remark");
		if(language.equals("Arabic"))
		{
			lblRemark.setText("„·«ÕŸ…");
		}
		lblRemark.setBounds(12, 103, 56, 22);
		panel_1.add(lblRemark);
		
		textField_1 = new JTextField();
		textField_1.setBounds(103, 70, 459, 22);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(103, 103, 459, 22);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblSuppName = new JLabel("Supp. Name");
		if(language.equals("Arabic"))
		{
			lblSuppName.setText("«”„ «·„Ê—œ");
		}
		lblSuppName.setBounds(6, 136, 70, 22);
		panel_1.add(lblSuppName);
		
		textField_14 = new JTextField();
		textField_14.setEditable(false);
		textField_14.setBounds(82, 136, 362, 22);
		panel_1.add(textField_14);
		textField_14.setColumns(10);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new vendor_list(company,  "APinvoice", version,decimal,username);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		button.setBounds(450, 136, 35, 22);
		button.setIcon(new ImageIcon(serch));
		panel_1.add(button);
		
		JButton button_2 = new JButton("New");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setAlwaysOnTop(false);
					new vendor_details(company,company,  username,"po_order",store, version, decimal,language);
					//frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					//menuItem.setEnabled(false);
					//btnNew.setEnabled(false);
					
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		button_2.setBounds(491, 136, 70, 23);
		panel_1.add(button_2);
		
		JLabel lblNewLabel_2 = new JLabel("Inv. Amt");
		if(language.equals("Arabic"))
		{
			lblNewLabel_2.setText("„»·€ «·›« Ê—…");
		}
		lblNewLabel_2.setBounds(12, 202, 100, 20);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField_5 = new JTextField();
		textField_5.setBounds(12, 224, 100, 22);
		panel_1.add(textField_5);
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_5.getText().equals(""))
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
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Disc. Amt");
		if(language.equals("Arabic"))
		{
			lblNewLabel_3.setText("„»·€ «·Œ’„");
		}
		lblNewLabel_3.setBounds(124, 202, 100, 20);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField_6 = new JTextField();
		textField_6.setText("0");
		textField_6.setBounds(124, 224, 100, 22);
		panel_1.add(textField_6);
		textField_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_6.getText().equals(""))
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
		textField_6.setColumns(10);
		
		JLabel lblPaidBy = new JLabel("VAT type");
		if(language.equals("Arabic"))
		{
			lblPaidBy.setText("‰Ê⁄ «·÷—Ì»…");
		}
		lblPaidBy.setBounds(236, 203, 75, 19);
		panel_1.add(lblPaidBy);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(236, 225, 75, 22);
		panel_1.add(comboBox_1);
		
		Label label_1 = new Label("VAT Amt.");
		if(language.equals("Arabic"))
		{
			label_1.setText("„»·€ «·÷—Ì»…");
		}
		label_1.setBounds(323, 202, 100, 20);
		panel_1.add(label_1);
		
		textField_7 = new JTextField();
		textField_7.setBounds(323, 224, 100, 22);
		panel_1.add(textField_7);
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		
		Label label = new Label("Net Total");
		if(language.equals("Arabic"))
		{
			label.setText("’«›Ì «·„Ã„Ê⁄");
		}
		label.setBounds(435, 202, 120, 20);
		panel_1.add(label);
		
		textField_12 = new JTextField();
		textField_12.setBounds(435, 224, 120, 22);
		panel_1.add(textField_12);
		textField_12.setEditable(false);
		
		textField_12.setColumns(10);
		
		button_1 = new JButton("");
		button_1.setBounds(206, 281, 35, 25);
		panel_1.add(button_1);
		button_1.setToolTipText("F9");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company, "AP_inv", 0, version,"", "",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setIcon(new ImageIcon(serch));
		
		clickOnserch( button_1, "play", KeyEvent.VK_F9,company,version ,language);
		
		textField_3 = new JTextField();
		textField_3.setBounds(12, 284, 184, 22);
		panel_1.add(textField_3);
		
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		
		JButton btnCreateNewAc = new JButton("New");
		btnCreateNewAc.setBounds(251, 281, 60, 25);
		panel_1.add(btnCreateNewAc);
		
		textField_4 = new JTextField();
		textField_4.setBounds(12, 317, 548, 22);
		panel_1.add(textField_4);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		
		JLabel lblExpenceAc = new JLabel("Purchase Expence A/C");
		if(language.equals("Arabic"))
		{
			lblExpenceAc.setText("Õ”«» „’«—Ì› «·‘—«¡");
		}
		lblExpenceAc.setBounds(12, 257, 299, 22);
		panel_1.add(lblExpenceAc);
		
		textField_11 = new JTextField();
		textField_11.setBounds(435, 257, 116, 22);
		panel_1.add(textField_11);
		textField_11.setEnabled(false);
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		
		JLabel lblTransactionType = new JLabel("Transaction Type");
		if(language.equals("Arabic"))
		{
			lblTransactionType.setText("‰Ê⁄ «·„⁄«„·…");
		}
		lblTransactionType.setBounds(10, 169, 105, 22);
		panel_1.add(lblTransactionType);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(124, 169, 224, 22);
		panel_1.add(comboBox);
		comboBox.addItem("PAYMENT");
		comboBox.addItem("RECEIPT");
		textField_11.setVisible(false);
		btnCreateNewAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new accounts_master.chartaccount(company,  version, language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		comboBox_1.addItem("INC");
		comboBox_1.addItem("EXC");
		comboBox_1.addItem("NON");
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					double vat=0;
					double ttl=0;
					double amount=Double.parseDouble(textField_5.getText())-Double.parseDouble(textField_6.getText());
					
					if(comboBox_1.getSelectedItem().toString().equals("INC"))
					{
						vat=(amount*5)/105;
						ttl=amount;
					}
					else if(comboBox_1.getSelectedItem().toString().equals("EXC"))
					{
						vat=(amount*5)/100;
						ttl=amount+vat;
					}
					else
					{
						vat=0;
						ttl=amount;
					}
					BigDecimal bd = new BigDecimal(ttl);
		    		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
		    		ttl = bd.doubleValue();
					textField_12.setText(Double.toString(ttl));
					BigDecimal bc = new BigDecimal(vat);
		    		bc = bc.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
		    		vat = bc.doubleValue();
					textField_7.setText(Double.toString(vat));
				} catch (Exception e) {
					System.err.println(e);
				}
				
				
				
			}
		});
		comboBox_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					double vat=0;
					double ttl=0;
					double amount=Double.parseDouble(textField_5.getText())-Double.parseDouble(textField_6.getText());
					
					if(comboBox_1.getSelectedItem().toString().equals("INC"))
					{
						vat=(amount*5)/105;
						ttl=amount;
					}
					else if(comboBox_1.getSelectedItem().toString().equals("EXC"))
					{
						vat=(amount*5)/100;
						ttl=amount+vat;
					}
					else
					{
						vat=0;
						ttl=amount;
					}
					BigDecimal bd = new BigDecimal(ttl);
		    		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
		    		ttl = bd.doubleValue();
					textField_12.setText(Double.toString(ttl));
					BigDecimal bc = new BigDecimal(vat);
		    		bc = bc.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
		    		vat = bc.doubleValue();
					textField_7.setText(Double.toString(vat));
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		});
		
		
		
		
		if(!jv_num.equals("NEW"))
		{
			pre_load(company,jv_num,decimal);
		}
	}
	public static void clickOnserch(final AbstractButton button, String actionName, int key,final String company, final String version ,final String language)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
				try {
					new SelectAccount(company, "JV_dt", 0, version,"", "",language);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    } );
	}
	
	
	private void pre_load(String company, String invoice_num,String decimal) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_invoice where invoice_num='"+invoice_num+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				txtNew.setText(resultSet.getString("invoice_num"));
				comboBox1.setSelectedItem(resultSet.getString("branch"));
				java.util.Date date =null;
				if(resultSet.getString("inv_date")!="")
				{
					date = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("inv_date"));
				}
				dateChooser.setDate(date);
				
				textField_1.setText(resultSet.getString("doc_num"));
				textField_2.setText(resultSet.getString("remark"));
				textField_14.setText(resultSet.getString("supp_name"));
				textField_11.setText(resultSet.getString("supp_code"));
				textField_5.setText(resultSet.getString("inv_amt"));
				textField_6.setText(resultSet.getString("disc_amt"));
				comboBox_1.setSelectedItem(resultSet.getString("vat_type"));
				textField_7.setText(resultSet.getString("vat_amt"));
				textField_12.setText(resultSet.getString("net_total"));
				textField_3.setText(resultSet.getString("acc_no"));
				textField_4.setText(resultSet.getString("acc_desc"));
				comboBox.setSelectedItem(resultSet.getString("type"));
			}
			
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
	
	
	private void  get_acc_brach(String company , String store)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_acc_segment where store_id='"+store+"' ");
			while(resultSet.next())
			{
				comboBox1.addItem(resultSet.getString("name"));
			}
			
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
}
