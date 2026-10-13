package accounts_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;



import javax.swing.KeyStroke;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

import org.json.JSONException;

import sales_distribution.numbertowords;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Label;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

public class JV_details {

	private JFrame frame;
	private JTextField txtNew;
	private JTextField textField_1;
	private JTextField textField_2;
	static JTextField textField_3;
	static JTextField textField_4;
	static JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane1;
	private static DefaultTableModel model;
	public static JTable table;
	private JButton btnEnter;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JComboBox<String> comboBox1;
	private JComboBox <String> comboBox_1;
	private JComboBox <String> comboBox_2;
	private JLabel lblNewLabel;
	private JTextArea textArea;
	private JButton button_1;
	private JButton btnStart;
	private JButton btnClear;
	private JButton btnDeleteRow;
	private JTextField textField;
	private JTextField textField_9;
	private JTextField textField_10;
	private boolean dot=false;
	private JTextField textField_11;
	private JMenuItem menuItem_2;
	private JMenuItem menuItem_1;
	private JButton btnNewButton;
	private JPanel panel_2;
	private JTextField textField_12;
	private Label label_1;
	private String usertype="";
	private JTextField textField_13;
	private JTextField textField_14;
	private String cash_acc="";
	private String cash_acc_name="";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JV_details window = new JV_details("demo","1.0","S01","ADMIN","JV-21-01-000031","2","Unapproved","");
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
	public JV_details(String company,String version,String store,String username,String jv_num,String decimal, String status, String language) throws JSONException {
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
	private void initialize(final String company,final String version,final String store, final String username, final String jv_num, final String decimal, final String status,final String language) throws JSONException {
		frame = new JFrame();
		frame.setBounds(0, 0, 1284, 700);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		JLabel lblShift = new JLabel("Accounts Journal Entry");
		if(language.equals("Arabic"))
		{
			lblShift.setText("ﬁÌœ œ› — «·ÌÊ„Ì… ··Õ”«»« ");
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
				JV_header.get_jv_disp( company,JV_header.comboBox.getSelectedItem().toString(), JV_header.textField.getText().trim());
			}
		});
		menuItem.setBorder(null);
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(1042, 3, 60, 48);
		panel.add(menuItem);
		
		JMenuItem menuItem_3 = new JMenuItem("Print");
		if(language.equals("Arabic"))
		{
			menuItem_3.setText("„ÿ»⁄…");
		}
		menuItem_3.setBounds(981, 3, 60, 48);
		panel.add(menuItem_3);
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					frame.setAlwaysOnTop(false);
					report_master.jv_details.report(txtNew.getText(),con,status,company,"","","","");
					//frame.setAlwaysOnTop(true);
					con.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
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
		menuItem_1.setBounds(1102, 3, 80, 48);
		panel.add(menuItem_1);
		
		menuItem_2 = new JMenuItem("Save");
		if(language.equals("Arabic"))
		{
			menuItem_2.setText("ÌÕ›Ÿ");
		}
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(model.getRowCount()==0)
				{
					JOptionPane.showMessageDialog(frame, "There is no entries in this voucher, Press Exit button to Exit or Do atleast one entry to save this voucher");
				}
				else
				{
					if(Float.parseFloat(textField_10.getText())==0)
					{
						try {
							LocalDateTime myDateObj = LocalDateTime.now();  
						    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
						    String formattedDate = myDateObj.format(myFormatObj);
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_jv_header where jv_num='"+txtNew.getText()+"' ");
							if(resultSet.next())
							{
								String jvdate = null;
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								String jv_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
								if(!jv_date.equals(""))
								{
									jvdate = sdf.format(dateChooser.getDate());
								}
								double jv_amt=Double.parseDouble(textField_10.getText());
								String save_po="update "+company+".mr_jv_header set date='"+jvdate+"',branch='"+comboBox1.getSelectedItem().toString()+"',doc_num='"+textField_1.getText()+"',remark='"+textField_2.getText()+"',notes='"+textArea.getText()+"',modified_date='"+formattedDate+"',jv_amt='"+jv_amt+"' where jv_num='"+txtNew.getText()+"' ";
								statement.executeUpdate(save_po);
								JOptionPane.showMessageDialog(frame, "Saved Successfully");
							}
							
							con.close();
						} catch (Exception e) {
							System.err.println(e);
						}
					}
					else
					{
						int a=JOptionPane.showConfirmDialog(frame, "Transaction Is Mis-balnced, Do you want to save","Warning", JOptionPane.YES_NO_OPTION);
						if(a==0)
						{
							try {
								LocalDateTime myDateObj = LocalDateTime.now();  
							    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
							    String formattedDate = myDateObj.format(myFormatObj);
								Connection con=Dbsetup.dbconn.getConnection();
								Statement statement=con.createStatement();  
								ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_jv_header where jv_num='"+txtNew.getText()+"' ");
								if(resultSet.next())
								{
									String jvdate = null;
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									String jv_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
									if(!jv_date.equals(""))
									{
										jvdate = sdf.format(dateChooser.getDate());
									}
									String save_po="update "+company+".mr_jv_header set date='"+jvdate+"',branch='"+comboBox1.getSelectedItem().toString()+"',doc_num='"+textField_1.getText()+"',remark='"+textField_2.getText()+"',notes='"+textArea.getText()+"',modified_date='"+formattedDate+"',jv_amt='"+textField_10.getText()+"' where jv_num='"+txtNew.getText()+"' ";
									statement.executeUpdate(save_po);
									JOptionPane.showMessageDialog(frame, "Saved Successfully");
								}
								
								con.close();
							} catch (Exception e) {
								System.err.println(e);
							}
						}
					}	
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(1184, 3, 70, 48);
		panel.add(menuItem_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 68, 1254, 115);
		panel_1.setBackground(new Color(243, 229, 216));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblJournalVoucher = new JLabel("Journal Voucher");
		if(language.equals("Arabic"))
		{
			menuItem_2.setText("ﬁ”Ì„… «·„Ã·…");
		}
		lblJournalVoucher.setBounds(12, 10, 103, 16);
		panel_1.add(lblJournalVoucher);
		
		txtNew = new JTextField(jv_num);
		txtNew.setEditable(false);
		txtNew.setBounds(114, 6, 168, 22);
		panel_1.add(txtNew);
		txtNew.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		if(language.equals("Arabic"))
		{
			lblDate.setText(" «—ÌŒ");
		}
		lblDate.setBounds(12, 36, 56, 16);
		panel_1.add(lblDate);
		
		dateChooser = new JDateChooser(new Date());
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(114, 34, 168, 22);
		panel_1.add(dateChooser);
		
		JLabel lblBranch = new JLabel("Branch");
		if(language.equals("Arabic"))
		{
			lblBranch.setText("›—⁄");
		}
		lblBranch.setBounds(12, 62, 56, 16);
		panel_1.add(lblBranch);
		
		comboBox1 = new JComboBox<String>();
		comboBox1.setBounds(114, 62, 168, 22);
		panel_1.add(comboBox1);
		get_acc_brach(company , store);
		
		JLabel lblUsername = new JLabel("Username");
		if(language.equals("Arabic"))
		{
			lblUsername.setText("«”„ «·„” Œœ„");
		}
		lblUsername.setBounds(12, 88, 70, 16);
		panel_1.add(lblUsername);
		
		lblNewLabel = new JLabel(username);
		lblNewLabel.setBounds(114, 90, 168, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblDoc = new JLabel("Doc Num");
		if(language.equals("Arabic"))
		{
			lblDoc.setText("—ﬁ„ «·„” ‰œ");
		}
		lblDoc.setBounds(656, 13, 70, 16);
		panel_1.add(lblDoc);
		
		JLabel lblRemark = new JLabel("Remark");
		if(language.equals("Arabic"))
		{
			lblRemark.setText("„·«ÕŸ…");
		}
		lblRemark.setBounds(656, 39, 56, 16);
		panel_1.add(lblRemark);
		
		JLabel lblNotes = new JLabel("Notes");
		if(language.equals("Arabic"))
		{
			lblNotes.setText("„·ÕÊŸ« ");
		}
		lblNotes.setBounds(656, 65, 56, 16);
		panel_1.add(lblNotes);
		
		textField_1 = new JTextField();
		textField_1.setBounds(747, 10, 482, 22);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(747, 36, 482, 22);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(747, 62, 482, 44);
		panel_1.add(scrollPane1);
		
		textArea = new JTextArea();
		scrollPane1.setViewportView(textArea);
		
		textField_11 = new JTextField();
		textField_11.setBounds(417, 56, 116, 22);
		panel_1.add(textField_11);
		textField_11.setEnabled(false);
		textField_11.setEditable(false);
		textField_11.setVisible(false);
		textField_11.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setEnabled(false);
		textField_14.setVisible(false);
		textField_14.setEditable(false);
		textField_14.setBounds(417, 25, 116, 22);
		panel_1.add(textField_14);
		textField_14.setColumns(10);
		textField_11.setVisible(true);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		panel_2 = new JPanel();
		panel_2.setBounds(0, 190, 1254, 115);
		panel_2.setBackground(new Color(243, 229, 216));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(201, 16, 229, 22);
		panel_2.add(textField_4);
		textField_4.setColumns(10);
		
		button_1 = new JButton("");
		button_1.setToolTipText("F9");
		button_1.setEnabled(false);
		button_1.setBounds(11, 17, 35, 22);
		panel_2.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectAccount(company, "JV_dt", 0, version,"", "",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setIcon(new ImageIcon(serch));
		
		textField_3 = new JTextField();
		
		textField_3.setEditable(false);
		textField_3.setBounds(57, 16, 133, 22);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		
		JLabel lblAcCode = new JLabel("Ac Code");
		if(language.equals("Arabic"))
		{
			lblAcCode.setText("—„“ «·Õ”«»");
		}
		lblAcCode.setBounds(57, 0, 133, 16);
		panel_2.add(lblAcCode);
		lblAcCode.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Ac Desc");
		if(language.equals("Arabic"))
		{
			lblAcCode.setText("≈”„ «·Õ”«»");
		}
		lblNewLabel_1.setBounds(202, 0, 229, 16);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField_5 = new JTextField();
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
			    textField_6.setEditable(false);
			    textField_6.setText("0");
			    
			    
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(textField_5.getText().length()==0)
			    {
			    	textField_6.setEditable(true);
				    textField_6.setText("0");
			    }
			}
		});
		textField_5.setEditable(false);
		textField_5.setBounds(441, 16, 93, 22);
		panel_2.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Dr. Amt");
		if(language.equals("Arabic"))
		{
			lblNewLabel_2.setText("„»·€ «·Œ’„");
		}
		lblNewLabel_2.setBounds(443, 0, 93, 16);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField_6 = new JTextField();
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
			    textField_5.setEditable(false);
			    textField_5.setText("0");
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(textField_6.getText().length()==0)
			    {
			    	textField_5.setEditable(true);
				    textField_5.setText("0");
			    }
			}
		});
		textField_6.setEditable(false);
		textField_6.setBounds(545, 16, 98, 22);
		panel_2.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Cr. Amt");
		if(language.equals("Arabic"))
		{
			lblNewLabel_3.setText("„»·€ «·«∆ „«‰");
		}
		lblNewLabel_3.setBounds(548, 0, 98, 16);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(654, 16, 298, 22);
		panel_2.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Narration");
		if(language.equals("Arabic"))
		{
			lblNewLabel_4.setText("«·”—œ");
		}
		lblNewLabel_4.setBounds(658, 0, 298, 16);
		panel_2.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField_8 = new JTextField();
//		textField_8.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent arg0) {
//				comboBox_1.requestFocus();
//			}
//		});
		textField_8.setEditable(false);
		textField_8.setBounds(5, 54, 506, 22);
		panel_2.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Remarks");
		if(language.equals("Arabic"))
		{
			lblNewLabel_5.setText("„·«ÕŸ« ");
		}
		lblNewLabel_5.setBounds(13, 38, 251, 16);
		panel_2.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("ACCOUNT");
		comboBox_1.addItem("CASH");
		comboBox_1.addItem("BANK");
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_1.getSelectedItem().toString().equals("BANK"))
				{
					textField_12.setEditable(true);
					comboBox_2.setEnabled(true);
					label_1.setText("Cheque Date");
				}
				else
				{
					textField_12.setEditable(false);
					comboBox_2.setEnabled(false);
					label_1.setText("Date");
				}
			}
		});
		comboBox_1.setBounds(544, 54, 84, 22);
		
		panel_2.add(comboBox_1);
//		comboBox_1.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent arg0) {
//				if(comboBox_1.getSelectedItem().toString().equals("BANK"))
//				{
//					comboBox_2.requestFocus();
//				}
//				else
//				{
//					dateChooser_1.requestFocus();
//				}
//			}
//		});
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setEnabled(false);
		comboBox_2.setBounds(633, 54, 93, 22);
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_acc_paycode where store_id='"+store+"' and paycode_type='BANK' "; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				comboBox_2.addItem(resultSet.getString("paycode_name"));
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
//		comboBox_2.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent arg0) {
//				textField_12.requestFocus();
//			}
//		});
		panel_2.add(comboBox_2);
		comboBox_2.setSelectedIndex(-1);
		
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setBounds(731, 54, 116, 22);
		panel_2.add(textField_12);
//		textField_12.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent arg0) {
//				dateChooser_1.requestFocus();
//			}
//		});
		textField_12.setColumns(10);
		
		dateChooser_1 = new JDateChooser(new Date());
		dateChooser_1.setDateFormatString("dd/MM/yyyy");
		dateChooser_1.setBounds(852, 54, 100, 22);
		dateChooser_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				btnEnter.requestFocus();
			}
		});
		panel_2.add(dateChooser_1);
		
		btnEnter = new JButton("ENTER");
		if(language.equals("Arabic"))
		{
			btnEnter.setText("ÌœŒ·");
		}
		btnEnter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode()==KeyEvent.VK_ENTER){
					 save_button(company,decimal,username);
				 }
			}
		});
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save_button(company,decimal,username);
			}
		});
		btnEnter.setEnabled(false);
		btnEnter.setBounds(955, 17, 105, 87);
		panel_2.add(btnEnter);
		
		
		
		btnDeleteRow = new JButton("VOID ROW");
		if(language.equals("Arabic"))
		{
			btnDeleteRow.setText("Œÿ »«ÿ·");
		}
		btnDeleteRow.setEnabled(false);
		btnDeleteRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(frame, "Select a row to Void");
				}
				else
				{
					int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Void This Entry","Warning",JOptionPane.YES_NO_OPTION);
			        if(cofrm_bx==0)
			        {
			        	int rows=table.getRowCount()-1;
						if(rows>0)
						{
							try{
								Connection con=Dbsetup.dbconn.getConnection();
								Statement statement=con.createStatement(); 
								Statement statement1=con.createStatement(); 
								String query="SELECT * FROM "+company+".mr_jv_details where ref_jv_num='"+txtNew.getText()+"' and ac_code='"+table.getValueAt(row, 2).toString()+"' and jv_seq='"+table.getValueAt(row, 1).toString()+"' "; 
								ResultSet resultSet = statement.executeQuery(query);
								if(resultSet.next())
								{
									 String query1="update "+company+".mr_jv_details set status='VOID' where ref_jv_num='"+txtNew.getText()+"' and ac_code='"+table.getValueAt(row, 2).toString()+"' and jv_seq='"+table.getValueAt(row, 1).toString()+"' ";
									 statement1.executeUpdate(query1);
									 if(resultSet.getString("paid_by").equals("CASH"))
									 {
										 String query2="update "+company+".mr_jv_details set status='VOID' where ref_jv_num='"+txtNew.getText()+"' and jv_seq='"+table.getValueAt(row, 1).toString()+"-1' ";
										 statement1.executeUpdate(query2);
									 }
									 if(resultSet.getString("paid_by").equals("BANK"))
									 {
										String query22="SELECT * FROM "+company+".mr_cheque_details where tran_num='"+txtNew.getText()+"' and bank_name='"+table.getValueAt(row, 9).toString()+"' and cheque_no='"+table.getValueAt(row, 10).toString()+"' "; 
										ResultSet resultSet22 = statement.executeQuery(query22);
										if(resultSet22.next())
										{
											String query2="update "+company+".mr_cheque_details set status='VOID' where tran_num='"+txtNew.getText()+"' and bank_name='"+table.getValueAt(row, 9).toString()+"' and cheque_no='"+table.getValueAt(row, 10).toString()+"' ";
											 statement1.executeUpdate(query2);
										}
										 
										 String query2="update "+company+".mr_jv_details set status='VOID' where ref_jv_num='"+txtNew.getText()+"' and jv_seq='"+table.getValueAt(row, 1).toString()+"-1' ";
										 statement1.executeUpdate(query2);
									 }
									 
								}
								textField_13.setText("");
								pre_load(company,jv_num,decimal);
								con.close();
							}
							catch(Exception e){
								System.err.println(e);
							}
							
							
						}
						else
						{
							try{
								Connection con=Dbsetup.dbconn.getConnection();
								Statement statement=con.createStatement();  
								Statement statement1=con.createStatement(); 
								String query="SELECT * FROM "+company+".mr_jv_details where ref_jv_num='"+txtNew.getText()+"' and ac_code='"+table.getValueAt(row, 2).toString()+"' and jv_seq='"+table.getValueAt(row, 1).toString()+"'"; 
								ResultSet resultSet = statement.executeQuery(query);
								if(resultSet.next())
								{
									 String query1="update "+company+".mr_jv_details set status='VOID' where ref_jv_num='"+txtNew.getText()+"' and ac_code='"+table.getValueAt(row, 2).toString()+"' and jv_seq='"+table.getValueAt(row, 1).toString()+"'";
									 statement1.executeUpdate(query1);
									 if(resultSet.getString("paid_by").equals("CASH"))
									 {
										 String query2="update "+company+".mr_jv_details set status='VOID' where ref_jv_num='"+txtNew.getText()+"' and jv_seq='"+table.getValueAt(row, 1).toString()+"-1' ";
										 statement1.executeUpdate(query2);
									 }
								}
								textField_13.setText("");
								pre_load(company,jv_num,decimal);
								con.close();
							}
							catch(Exception e){
								System.err.println(e);
							}
							
						}
			        }
					textField_3.requestFocus();
				}
				
			}
		});
		btnDeleteRow.setBounds(1065, 17, 100, 87);
		panel_2.add(btnDeleteRow);
		
		btnClear = new JButton("CLEAR");
		if(language.equals("Arabic"))
		{
			btnClear.setText("Ê«÷Õ");
		}
		btnClear.setEnabled(false);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_14.setText("");
				textField_11.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
				textField_5.setEditable(true);
				textField_6.setEditable(true);
				textField_3.requestFocus();
			}
		});
		btnClear.setBounds(1170, 17, 72, 87);
		panel_2.add(btnClear);
		
		JButton btnCreateNewAc = new JButton("Create New Ac code");
		btnCreateNewAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setAlwaysOnTop(false);
					new accounts_master.chartaccount(company,  version, language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCreateNewAc.setBounds(12, 79, 160, 25);
		panel_2.add(btnCreateNewAc);
		
		
		
		JLabel lblPaidBy = new JLabel("Paid By");
		if(language.equals("Arabic"))
		{
			lblPaidBy.setText("«·„œ›Ê⁄… »Ê«”ÿ…");
		}
		lblPaidBy.setBounds(544, 38, 84, 16);
		panel_2.add(lblPaidBy);
		
		
		
		
		
		Label label = new Label("Cheque No.");
		if(language.equals("Arabic"))
		{
			label.setText("—ﬁ„ «·‘Ìﬂ");
		}
		label.setBounds(734, 38, 116, 16);
		panel_2.add(label);
		
		label_1 = new Label("Date");
		if(language.equals("Arabic"))
		{
			label_1.setText(" «—ÌŒ");
		}
		label_1.setBounds(851, 38, 100, 16);
		panel_2.add(label_1);
		
		JLabel lblBankName = new JLabel("Bank Name");
		if(language.equals("Arabic"))
		{
			lblBankName.setText("«”„ «·»‰ﬂ");
		}
		lblBankName.setBounds(633, 38, 80, 16);
		panel_2.add(lblBankName);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.setBounds(10, 310, 1185, 294);
		panel.add(scrollPane);
		
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       
		       return false;
		    }
			
		};
		table = new JTable(model);
	    table.setFont(new Font("Serif", Font.BOLD, 15));
		table.setRowHeight(20);
		
		table.setAutoCreateRowSorter(true);
		//table.getTableHeader().setPreferredSize(new Dimension(10,30));
		table.setEnabled(true);
		
		if(language.equals("Arabic"))
		{
			model.addColumn("«·„”·”·"); 
		    model.addColumn("—ﬁ„ «·„‘—Ê⁄ «·„‘ —ﬂ");
		    model.addColumn("—„“ «·Õ”«»"); 
			model.addColumn("≈”„ «·Õ”«»"); 
			model.addColumn("„»·€ «·Œ’„"); 
			model.addColumn("„»·€ «·«∆ „«‰"); 
			model.addColumn(" «—ÌŒ");
			model.addColumn("«·”—œ"); 
			model.addColumn("„·«ÕŸ…");
			model.addColumn(" œ›⁄ »Ê«”ÿ…");
			model.addColumn("«”„ «·»‰ﬂ");
			model.addColumn(" Õﬁﬁ „‰ —ﬁ„");
			
			model.addColumn("Õ«·…"); 
			model.addColumn("ID"); 
		}
		else
		{
			model.addColumn("S.NO"); 
		    model.addColumn("JV Num");
		    model.addColumn("Ac Code"); 
			model.addColumn("Ac Desc"); 
			model.addColumn("Dr. Amt"); 
			model.addColumn("Cr. Amt"); 
			model.addColumn("Date");
			model.addColumn("Narration"); 
			model.addColumn("Remark");
			model.addColumn("paid_by");
			model.addColumn("bank_name");
			model.addColumn("cheque_no");
			
			model.addColumn("Status"); 
			model.addColumn("ID"); 
		}
		
	    
		
		scrollPane.setViewportView(table);
		
		//table.setFocusable(false);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		int colmns=model.getColumnCount();
		for (int i=0; i<colmns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(35);
		  if (i==1) column.setPreferredWidth(200);
		  if (i==2) column.setPreferredWidth(120);
		  if (i==3) column.setPreferredWidth(250);
		  if (i==4) column.setPreferredWidth(80);
		  if (i==5) column.setPreferredWidth(80);
		  if (i==6) column.setPreferredWidth(100);
		  if (i==7) column.setPreferredWidth(300);
		  if (i==8) column.setPreferredWidth(250);
		  if (i==9) column.setPreferredWidth(100);
		  if (i==10) column.setPreferredWidth(100);
		  if (i==11) column.setPreferredWidth(100);
		  if (i==12) column.setPreferredWidth(100);
		}
		table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
		
		
		table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if(usertype.equals("Administrator"))
        		{
                	if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    	try {
                    		int rows = table.getSelectedRow();
                    		String jv=table.getModel().getValueAt(rows, 1).toString();
                    		String[] jv_arr=jv.split("-");
                    		if(jv_arr.length>5)
                    		{
                    			JOptionPane.showMessageDialog(frame, "Select Main Row To Change");
                    		}
                    		else
                    		{
                    			textField_11.setText(table.getModel().getValueAt(rows, 1).toString());
                            	textField_3.setText(table.getModel().getValueAt(rows, 2).toString());
                            	textField_4.setText(table.getModel().getValueAt(rows, 3).toString());
                            	textField_5.setText(table.getModel().getValueAt(rows, 4).toString());
                            	textField_6.setText(table.getModel().getValueAt(rows, 5).toString());
                            	textField_7.setText(table.getModel().getValueAt(rows, 7).toString());
                            	textField_8.setText(table.getModel().getValueAt(rows, 8).toString());
                            	comboBox_1.setSelectedItem(table.getModel().getValueAt(rows, 9).toString());
                            	comboBox_2.setSelectedItem(table.getModel().getValueAt(rows, 10).toString());
                            	textField_12.setText(table.getModel().getValueAt(rows, 11).toString());
                            	java.util.Date date;
        						date = new SimpleDateFormat("yyyy-MM-dd").parse(table.getModel().getValueAt(rows, 6).toString());
        						dateChooser_1.setDate(date);
        						textField_14.setText(table.getModel().getValueAt(rows, 13).toString());
                    		}
                    		
                        	
    					} catch (Exception e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
                    }	
        		}
            }
        });
		
		
		
		btnStart = new JButton("Start");
		if(language.equals("Arabic"))
		{
			btnStart.setText("Ì»œ√");
		}
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    String formattedDate = myDateObj.format(myFormatObj);
			    try{
			    	Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement();  
					String query="SELECT * FROM "+company+".mr_jv_header where jv_num ='"+txtNew.getText()+"' and status!='VOID' "; 
					ResultSet resultSet = statement.executeQuery(query);
					if(!resultSet.next())
					{
						String seq_num = null;
						if(txtNew.getText().equals("NEW"))
						{
							String trans_seq = "select tran_seq from "+company+".mr_transaction_seq where tran_orgin='JV' ";
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
						    	String save_po="update "+company+".mr_transaction_seq set tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_orgin='JV' ";
								statement.executeUpdate(save_po);
						    }
						}
						String jvdate = null;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String jv_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
						if(!jv_date.equals(""))
						{
							jvdate = sdf.format(dateChooser.getDate());
						}
						String save_po="insert into "+company+".mr_jv_header( `jv_num`, `date`, `branch`, `username`, `doc_num`, `remark`, `notes`, `store_id`, `created_date`, `modified_date`, `status`) values('"+seq_num+"','"+jvdate+"','"+comboBox1.getSelectedItem().toString()+"','"+lblNewLabel.getText()+"','"+textField_1.getText()+"','"+textField_2.getText()+"','"+textArea.getText()+"','"+store+"','"+formattedDate+"','"+formattedDate+"','Unapproved')";
						statement.executeUpdate(save_po);
						txtNew.setText(seq_num);
						//textField_3.setEditable(true);
						//textField_4.setEditable(true);
						textField_5.setEditable(true);
						textField_6.setEditable(true);
						textField_7.setEditable(true);
						textField_8.setEditable(true);
						button_1.setEnabled(true);
						btnEnter.setEnabled(true);
						btnStart.setEnabled(false);
						btnStart.setVisible(false);
						btnDeleteRow.setEnabled(true);
						btnClear.setEnabled(true);
						textField_3.requestFocus();
						btnNewButton.setEnabled(true);
					}
			    	con.close();
				}
				catch(Exception e){
					System.err.println(e);
				}
			    
			}
		});
		btnStart.setBounds(573, 10, 84, 48);
		panel.add(btnStart);
		
		JLabel lblTotal = new JLabel("Total");
		if(language.equals("Arabic"))
		{
			lblTotal.setText("«·„Ã„Ê⁄");
		}
		lblTotal.setBounds(257, 617, 56, 16);
		panel.add(lblTotal);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(337, 614, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setBounds(465, 614, 116, 22);
		panel.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblBalance = new JLabel("Balance");
		if(language.equals("Arabic"))
		{
			lblBalance.setText(" Ê«“‰");
		}
		lblBalance.setBounds(601, 617, 56, 16);
		panel.add(lblBalance);
		
		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setBounds(666, 614, 116, 22);
		panel.add(textField_10);
		textField_10.setColumns(10);
		
		btnNewButton = new JButton("<html>P<br>R<br>I<br>N<br>T</html>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					int row=table.getSelectedRow();
					if(row>-1)
					{
						String jv_seq=table.getValueAt(row, 1).toString();
						double ttlamtword=0;
						if(!table.getValueAt(row, 4).toString().equals("0"))
						{
							ttlamtword=Double.parseDouble(table.getValueAt(row, 4).toString());
						}
						else
						{
							ttlamtword=Double.parseDouble(table.getValueAt(row, 5).toString());
						}
						
						Float num = new Float( ttlamtword ) ;
						int dollars = (int)Math.floor( num ) ;
						//floor cent = (int)Math.floor( ( num - dollars ) * 100.00 ) ;
						
						double cent=(num-dollars)*100;
						BigDecimal bd = new BigDecimal(cent);
		        		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
		        		cent = bd.doubleValue();
						String amountinwords = numbertowords.convert( dollars ) + " and " +numbertowords.convert( cent ) + " Fils Only" ;
						frame.setAlwaysOnTop(false);
						report_master.jv_voucher.report(txtNew.getText(),con,"OPEN",jv_seq,amountinwords,company);
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Select Any Row from Table");
					}
					
					//frame.setAlwaysOnTop(true);
					con.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(1194, 310, 60, 294);
		panel.add(btnNewButton);
		
		
		get_usertype(company,username);
		if(!jv_num.equals("NEW"))
		{
			pre_load(company,jv_num,decimal);
		}
		
		clickOnserch( button_1, "play", KeyEvent.VK_F9,company,version,language );
		
		textField_13 = new JTextField();
		textField_13.setToolTipText("SEARCH DATA FROM GRID");
		textField_13.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }

			  public void warn() {
				  int rowCount = model.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					serch_load(company,jv_num,decimal,textField_13.getText().trim());
			  }
			});
		textField_13.setBounds(182, 90, 461, 20);
		panel_2.add(textField_13);
		textField_13.setColumns(10);
		
		JLabel lblSearchDataFrom = new JLabel("Search Data From Grid");
		lblSearchDataFrom.setBounds(182, 75, 214, 14);
		panel_2.add(lblSearchDataFrom);
		panel_2.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField_5, textField_6, textField_7, textField_8, comboBox_1, comboBox_2, textField_12, dateChooser_1, btnEnter, btnDeleteRow, btnClear, btnCreateNewAc, textField_13, textField_4, button_1, textField_3, lblAcCode, lblNewLabel_1, lblNewLabel_2, lblNewLabel_3, lblNewLabel_4, lblNewLabel_5, lblPaidBy, dateChooser_1.getCalendarButton(), label, label_1, lblBankName, lblSearchDataFrom}));
		
		JMenuItem mntmPrintPartialJv = new JMenuItem("Print Partial JV");
		if(language.equals("Arabic"))
		{
			mntmPrintPartialJv.setText("ÿ»«⁄… Ã“∆Ì…");
		}
		mntmPrintPartialJv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					int count=table.getSelectedRowCount();
					if(count==0)
					{
						JOptionPane.showMessageDialog(frame, "Select atleast one row to proceed");
					}
					else
					{
						int[] selection = table.getSelectedRows();
						String list="";
						for(int i=0;i<selection.length;i++)
						{
							list+=table.getValueAt(selection[i], 13).toString();
							if(i<selection.length-1)
							{
								list+=",";
							}
							
						}
						frame.setAlwaysOnTop(false);
						report_master.jv_details.report(txtNew.getText(),con,status,company,textField.getText(),textField_1.getText(),list,"");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		mntmPrintPartialJv.setBorder(null);
		mntmPrintPartialJv.setBackground(new Color(243, 229, 216));
		mntmPrintPartialJv.setBounds(852, 3, 119, 48);
		mntmPrintPartialJv.setIcon(new ImageIcon(printer));
		panel.add(mntmPrintPartialJv);
		
		JMenuItem mntmPrintJvSummary = new JMenuItem("Print JV Summary");
		if(language.equals("Arabic"))
		{
			mntmPrintJvSummary.setText("„·Œ’ «·ÿ»«⁄…");
		}
		mntmPrintJvSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					frame.setAlwaysOnTop(false);
					report_master.jv_details.report(txtNew.getText(),con,status,company,"","","","SUMMARY");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mntmPrintJvSummary.setBorder(null);
		mntmPrintJvSummary.setBackground(new Color(243, 229, 216));
		mntmPrintJvSummary.setBounds(675, 3, 165, 48);
		mntmPrintJvSummary.setIcon(new ImageIcon(printer));
		panel.add(mntmPrintJvSummary);
		frame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField_3, textField_4, textField_5, textField_6, textField_7, textField_8, comboBox_1, comboBox_2, textField_12, dateChooser_1, btnEnter, btnDeleteRow, btnClear, btnCreateNewAc, textField_13, panel, frame.getContentPane(), lblShift, menuItem, menuItem_3, menuItem_1, menuItem_2, panel_1, lblJournalVoucher, txtNew, lblDate, dateChooser, dateChooser.getCalendarButton(), lblBranch, comboBox1, lblUsername, lblNewLabel, lblDoc, lblRemark, lblNotes, textField_1, textField_2, scrollPane1, textArea, textField_11, textField_14, panel_2, button_1, lblAcCode, lblNewLabel_1, lblNewLabel_2, lblNewLabel_3, lblNewLabel_4, lblNewLabel_5, dateChooser_1.getCalendarButton(), lblPaidBy, label, label_1, lblBankName, lblSearchDataFrom, scrollPane, table, btnStart, lblTotal, textField, textField_9, lblBalance, textField_10, btnNewButton, mntmPrintPartialJv, mntmPrintJvSummary}));
		
		load_cash_acc(company,store);
	}
	private void load_cash_acc(String company, String store_name) {
		try
		{
			
			Connection con1=Dbsetup.server.getConnection();
			String query19="SELECT * FROM "+company+".mr_store_accounts where ref_store_id='"+store_name+"'"; 
			Statement statement19=con1.createStatement();  
	        ResultSet resultSet19 = statement19.executeQuery(query19);
	        if(resultSet19.next())
	        {
	        	cash_acc=resultSet19.getString("cash_acc");
	        	
	        }
	        
	        String query29="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_store='"+store_name+"' and acc_code='"+cash_acc+"' "; 
			Statement statement29=con1.createStatement();  
	        ResultSet resultSet29= statement29.executeQuery(query29);
	        if(resultSet29.next())
	        {
	        	cash_acc_name=resultSet29.getString("acc_name");
	        }
	        
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(frame, ex.getMessage());
		}
		
	}

	public static void clickOnserch(final AbstractButton button, String actionName, int key,final String company, final String version, final String language)
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
	
	private void get_usertype(String company, String username)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_users where user_name='"+username+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				usertype=resultSet.getString("user_type");
			}
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	private void pre_load(String company, String jv_num,String decimal) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_jv_header where jv_num='"+jv_num+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				java.util.Date date =null;
				if(resultSet.getString("date")!="")
				{
					date = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("date"));
				}
				dateChooser.setDate(date);
				comboBox1.setSelectedItem(resultSet.getString("branch"));
				textField_1.setText(resultSet.getString("doc_num"));
				textField_2.setText(resultSet.getString("remark"));
				textArea.setText(resultSet.getString("notes"));
				if(!resultSet.getString("status").equals("Unapproved"))
				{
					if(usertype.equals("Administrator") && resultSet.getString("status").equals("Approved"))
					{
						btnDeleteRow.setEnabled(true);
					}
					else
					{
						btnDeleteRow.setEnabled(false);
					}
					//table.setEnabled(false);
					comboBox1.setEnabled(false);
					menuItem_2.setEnabled(false);
					menuItem_1.setEnabled(false);
					dateChooser.setEnabled(false);
					btnEnter.setEnabled(false);
					button_1.setEnabled(false);
					
					btnClear.setEnabled(false);
					btnStart.setEnabled(false);
					btnNewButton.setEnabled(true);
					
				}
				else
				{
					menuItem_1.setEnabled(false);
					txtNew.setText(jv_num);
					//textField_3.setEditable(true);
					//textField_4.setEditable(true);
					textField_5.setEditable(true);
					textField_6.setEditable(true);
					textField_7.setEditable(true);
					textField_8.setEditable(true);
					button_1.setEnabled(true);
					btnEnter.setEnabled(true);
					btnStart.setEnabled(false);
					btnStart.setVisible(false);
					btnDeleteRow.setEnabled(true);
					btnClear.setEnabled(true);
					textField_3.requestFocus();
					btnNewButton.setEnabled(true);
				}
			}
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			double dr=0;
			double cr=0;
			String query1="SELECT * FROM "+company+".mr_jv_details where ref_jv_num='"+jv_num+"' order by cheque_date,jv_seq"; 
			ResultSet resultSet1 = statement.executeQuery(query1);
			while(resultSet1.next())
			{
				String id=resultSet1.getString("id");
				String jv_code=resultSet1.getString("jv_seq");
				String ac_code=resultSet1.getString("ac_code");
				String ac_desc=resultSet1.getString("ac_desc");
				String dr_amt=resultSet1.getString("dr_amt");
				String cr_amt=resultSet1.getString("cr_amt");
				String note=resultSet1.getString("note");
				String remark=resultSet1.getString("remark");
				String cheque_no=resultSet1.getString("cheque_no");
				String cheque_date=resultSet1.getString("cheque_date");
				String bank_name=resultSet1.getString("bank_name");
				String paid_by=resultSet1.getString("paid_by");
				String status1=resultSet1.getString("status");
				model.insertRow(model.getRowCount(), new Object[] { model.getRowCount()+1, jv_code, ac_code, ac_desc,dr_amt,cr_amt,cheque_date,note,remark,paid_by,bank_name,cheque_no,status1,id});
				if(!status1.equals("VOID"))
				{
					dr+=Double.parseDouble(dr_amt);
					cr+=Double.parseDouble(cr_amt);
				}
			}
			double bal=dr-cr;
			
			BigDecimal bd = new BigDecimal(dr);
    		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
    		dr = bd.doubleValue();
    		
    		BigDecimal be = new BigDecimal(cr);
    		be = be.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
    		cr = be.doubleValue();
    		
    		BigDecimal ba = new BigDecimal(bal);
    		ba = ba.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
    		bal = ba.doubleValue();
			
			textField.setText(Double.toString(dr));
			textField_9.setText(Double.toString(cr));
			textField_10.setText(Double.toString(bal));
			
			table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
			//table.addRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
			
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
	private void serch_load(String company, String jv_num,String decimal,String search) {
		try{
			String serch="";
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			if(search!="")
			{
				serch=" and (jv_seq LIKE '%"+search+"%' or ac_code LIKE '%"+search+"%' or ac_desc LIKE '%"+search+"%' or dr_amt LIKE '%"+search+"%' or cr_amt LIKE '%"+search+"%' or remark LIKE '%"+search+"%' or note LIKE '%"+search+"%')";
			}
			String query="SELECT * FROM "+company+".mr_jv_header where jv_num='"+jv_num+"' "; 
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				java.util.Date date =null;
				if(resultSet.getString("date")!="")
				{
					date = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("date"));
				}
				dateChooser.setDate(date);
				comboBox1.setSelectedItem(resultSet.getString("branch"));
				textField_1.setText(resultSet.getString("doc_num"));
				textField_2.setText(resultSet.getString("remark"));
				textArea.setText(resultSet.getString("notes"));
				if(!resultSet.getString("status").equals("Unapproved"))
				{
					if(usertype.equals("Administrator"))
					{
						btnDeleteRow.setEnabled(true);
					}
					else
					{
						btnDeleteRow.setEnabled(false);
					}
					//table.setEnabled(false);
					comboBox1.setEnabled(false);
					menuItem_2.setEnabled(false);
					menuItem_1.setEnabled(false);
					dateChooser.setEnabled(false);
					btnEnter.setEnabled(false);
					button_1.setEnabled(false);
					
					btnClear.setEnabled(false);
					btnStart.setEnabled(false);
					btnNewButton.setEnabled(true);
					
				}
				else
				{
					menuItem_1.setEnabled(false);
					txtNew.setText(jv_num);
					//textField_3.setEditable(true);
					//textField_4.setEditable(true);
					textField_5.setEditable(true);
					textField_6.setEditable(true);
					textField_7.setEditable(true);
					textField_8.setEditable(true);
					button_1.setEnabled(true);
					btnEnter.setEnabled(true);
					btnStart.setEnabled(false);
					btnStart.setVisible(false);
					btnDeleteRow.setEnabled(true);
					btnClear.setEnabled(true);
					textField_3.requestFocus();
					btnNewButton.setEnabled(true);
				}
			}
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			double dr=0;
			double cr=0;
			String query1="SELECT * FROM "+company+".mr_jv_details where ref_jv_num='"+jv_num+"' "+serch+" order by cheque_date,jv_seq"; 
			ResultSet resultSet1 = statement.executeQuery(query1);
			while(resultSet1.next())
			{
				String id=resultSet1.getString("id");
				String jv_code=resultSet1.getString("jv_seq");
				String ac_code=resultSet1.getString("ac_code");
				String ac_desc=resultSet1.getString("ac_desc");
				String dr_amt=resultSet1.getString("dr_amt");
				String cr_amt=resultSet1.getString("cr_amt");
				String note=resultSet1.getString("note");
				String remark=resultSet1.getString("remark");
				String cheque_no=resultSet1.getString("cheque_no");
				String cheque_date=resultSet1.getString("cheque_date");
				String bank_name=resultSet1.getString("bank_name");
				String paid_by=resultSet1.getString("paid_by");
				String status1=resultSet1.getString("status");
				model.insertRow(model.getRowCount(), new Object[] { model.getRowCount()+1, jv_code, ac_code, ac_desc,dr_amt,cr_amt,cheque_date,note,remark,paid_by,bank_name,cheque_no,status1,id});
				if(!status1.equals("VOID"))
				{
					dr+=Double.parseDouble(dr_amt);
					cr+=Double.parseDouble(cr_amt);
				}
			}
			double bal=dr-cr;
			
			BigDecimal bd = new BigDecimal(dr);
    		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
    		dr = bd.doubleValue();
    		
    		BigDecimal be = new BigDecimal(cr);
    		be = be.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
    		cr = be.doubleValue();
    		
    		BigDecimal ba = new BigDecimal(bal);
    		ba = ba.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
    		bal = ba.doubleValue();
			
			textField.setText(Double.toString(dr));
			textField_9.setText(Double.toString(cr));
			textField_10.setText(Double.toString(bal));
			
			textField_13.requestFocus();
			
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}

	private void save_button(String company,String decimal,String username)
	{
		try {
			if(textField_11.getText().equals(""))
			{
				String accode =textField_3.getText();
				if(accode.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select or Enter An Ac code");
					textField_3.requestFocus();
				}
				else
				{
					//String ac_desc=textField_4.getText();
					String dr_amt=textField_5.getText();
					if(dr_amt.equals(""))
					{
						dr_amt="0.0";
					}
					String cr_amt=textField_6.getText();
					if(cr_amt.equals(""))
					{
						cr_amt="0.0";
					}
					//String note=textField_7.getText();
					//String remark=textField_8.getText();
					String payment_method=comboBox_1.getSelectedItem().toString();
					String bank_name="";
					String chque_no=textField_12.getText();
					if(payment_method.equals("BANK"))
					{
						bank_name=comboBox_2.getSelectedItem().toString();
						if(chque_no.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Enter Cheque Number");
							return;
						}
					}
					String cheque_date = null;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String jv_date = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					if(!jv_date.equals(""))
					{
						cheque_date = sdf.format(dateChooser_1.getDate());
					}
					try{
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						String cheque="SELECT * FROM "+company+".mr_cheque_details where cheque_no='"+chque_no+"' and bank_name='"+bank_name+"' "; 
						ResultSet resultSet22 = statement.executeQuery(cheque);
						if(resultSet22.next())
						{
							JOptionPane.showMessageDialog(frame, "Cheque Number Already Approved For Another Invoice");
						}
						else
						{
							int row=model.getRowCount();
							if(row==0)
							{
								int rowcount=model.getRowCount()+1;
								LocalDateTime myDateObj = LocalDateTime.now();  
							    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
							    String formattedDate = myDateObj.format(myFormatObj);  
							    String query="SELECT * FROM "+company+".mr_jv_details where ref_jv_num='"+txtNew.getText()+"'"; 
								ResultSet resultSet = statement.executeQuery(query);
								if(!resultSet.next())
								{
									String query1="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,paid_by,cheque_no,cheque_date,bank_name) "
											+ "VALUES ('"+txtNew.getText()+"-"+rowcount+"','"+txtNew.getText()+"','"+textField_3.getText()+"','"+textField_4.getText()+"','"+dr_amt+"','"+cr_amt+"','"+textField_7.getText()+"','"+textField_8.getText()+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+payment_method+"','"+chque_no+"','"+cheque_date+"','"+bank_name+"')";
									statement.executeUpdate(query1);
								}
								//model.addRow(new Object[]{model.getRowCount()+1,txtNew.getText()+"-"+rowcount, accode, ac_desc,dr_amt,cr_amt,cheque_date,note,remark,payment_method,bank_name,chque_no,"OPEN"});
								
								
								
								if(payment_method.equals("BANK"))
								{
									String query1="insert into "+company+".mr_cheque_details( `tran_num`, `pay_by`,pay_id, `dr`, `cr`, `issue_date`, `cheque_no`, `cheque_date`,  `printing_user`,  `handover_user`, `posted_user`, `remark`, `note`,status,bank_name,page_type) "
											+ "VALUES ('"+txtNew.getText()+"','"+textField_4.getText()+"','"+textField_3.getText()+"','"+cr_amt+"','"+dr_amt+"','"+formattedDate+"','"+chque_no+"','"+cheque_date+"','"+username+"','"+username+"','"+username+"','"+textField_8.getText()+"','"+textField_7.getText()+"','OPEN','"+bank_name+"','JV')";
									statement.executeUpdate(query1);
									
									
									String sel_bank="select * from "+company+".mr_acc_paycode where paycode_name='"+bank_name+"' ";
									ResultSet resultSet2 = statement.executeQuery(sel_bank);
									if(resultSet2.next())
									{
										String acc_num=resultSet2.getString("default_ac");
										String sel_acc="select * from "+company+".mr_chart_accnt_mstr where acc_code='"+acc_num+"' ";
										ResultSet resultSet1 = statement.executeQuery(sel_acc);
//										if(resultSet1.next())
//										{
//											model.addRow(new Object[]{model.getRowCount()+1,txtNew.getText()+"-"+rowcount+"-1", acc_num, resultSet1.getString("acc_name"),cr_amt,dr_amt,cheque_date,note,remark,payment_method,bank_name,chque_no,"OPEN"});	
//										}
										String query11="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,paid_by,cheque_no,cheque_date,bank_name) "
												+ "VALUES ('"+txtNew.getText()+"-"+rowcount+"-1','"+txtNew.getText()+"','"+acc_num+"','"+resultSet1.getString("acc_name")+"','"+cr_amt+"','"+dr_amt+"','"+textField_7.getText()+"','"+textField_8.getText()+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+payment_method+"','"+chque_no+"','"+cheque_date+"','"+bank_name+"')";
										statement.executeUpdate(query11);
									}
									
									
								}
								else
								{
									if(payment_method.equals("CASH"))
									{
									//model.addRow(new Object[]{model.getRowCount()+1,txtNew.getText()+"-"+rowcount+"-1", "0101003001", "MAIN CASH ACCOUNT AT S01",cr_amt,dr_amt,cheque_date,note,remark,payment_method,bank_name,chque_no,"OPEN"});
									String query11="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,paid_by,cheque_no,cheque_date,bank_name) "
											+ "VALUES ('"+txtNew.getText()+"-"+rowcount+"-1','"+txtNew.getText()+"','"+cash_acc+"','"+cash_acc_name+"','"+cr_amt+"','"+dr_amt+"','"+textField_7.getText()+"','"+textField_8.getText()+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+payment_method+"','"+chque_no+"','"+cheque_date+"','"+bank_name+"')";
									statement.executeUpdate(query11);
									}
								}
								textField_3.setText("");
								textField_4.setText("");
								textField_5.setText("");
								textField_6.setText("");
								textField_7.setText("");
								textField_8.setText("");
								textField_11.setText("");
								textField_14.setText("");
								double cramt=0;
								double dramt=0;
								for(int x=0;x<model.getRowCount();x++)
								{
									if(table.getValueAt(x, 12).toString().equals("VOID"))
									{
										
									}
									else
									{
										double cr=Double.parseDouble(table.getValueAt(x, 5).toString());
										double dr=Double.parseDouble(table.getValueAt(x, 4).toString());
										cramt=cramt+cr;
										dramt=dramt+dr;
									}
									
								}
								textField.setText(Double.toString(dramt));
								textField_9.setText(Double.toString(cramt));
								textField_10.setText(Double.toString(dramt-cramt));
								textField_5.setEditable(true);
								textField_6.setEditable(true);
								textField_3.requestFocus();
								con.close();
							}
							else
							{
								int a=0;
								int b=0;
								for(int i=0;i<model.getRowCount();i++)
								{
									
									String type=table.getValueAt(i, 9).toString();
									if(type.equals("ACCOUNT"))
									{
										a++;
									}
									else
									{
										b++;
									}
									
								}
								
								int rowcounter=(b/2)+a;
								
								int rowcount=rowcounter+1;
							//model.addRow(new Object[]{model.getRowCount()+1,txtNew.getText()+"-"+rowcount, accode, ac_desc,dr_amt,cr_amt,cheque_date,note,remark,payment_method,bank_name,chque_no,"OPEN"});
								//model.addRow(new Object[]{model.getRowCount()+1,txtNew.getText()+"-"+rowcount, accode, ac_desc,dr_amt,cr_amt,note,remark,"OPEN"});
								LocalDateTime myDateObj = LocalDateTime.now();  
							    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
							    String formattedDate = myDateObj.format(myFormatObj);  
							    String query1="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,paid_by,cheque_no,cheque_date,bank_name) "
										+ "VALUES ('"+txtNew.getText()+"-"+rowcount+"','"+txtNew.getText()+"','"+textField_3.getText()+"','"+textField_4.getText()+"','"+dr_amt+"','"+cr_amt+"','"+textField_7.getText()+"','"+textField_8.getText()+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+payment_method+"','"+chque_no+"','"+cheque_date+"','"+bank_name+"')";
								statement.executeUpdate(query1);
								if(payment_method.equals("BANK"))
								{
									String query21="insert into "+company+".mr_cheque_details( `tran_num`, `pay_by`,pay_id, `dr`, `cr`, `issue_date`, `cheque_no`, `cheque_date`,  `printing_user`,  `handover_user`, `posted_user`, `remark`, `note`,status,bank_name,page_type) "
											+ "VALUES ('"+txtNew.getText()+"','"+textField_4.getText()+"','"+textField_3.getText()+"','"+cr_amt+"','"+dr_amt+"','"+formattedDate+"','"+chque_no+"','"+cheque_date+"','"+username+"','"+username+"','"+username+"','"+textField_8.getText()+"','"+textField_7.getText()+"','OPEN','"+bank_name+"','JV')";
									statement.executeUpdate(query21);
									String sel_bank="select * from "+company+".mr_acc_paycode where paycode_name='"+bank_name+"' ";
									ResultSet resultSet = statement.executeQuery(sel_bank);
									if(resultSet.next())
									{
										String acc_num=resultSet.getString("default_ac");
										String sel_acc="select * from "+company+".mr_chart_accnt_mstr where acc_code='"+acc_num+"' ";
										ResultSet resultSet1 = statement.executeQuery(sel_acc);
										if(resultSet1.next())
										{
											//model.addRow(new Object[]{model.getRowCount()+1,txtNew.getText()+"-"+rowcount+"-1", acc_num, resultSet1.getString("acc_name"),cr_amt,dr_amt,cheque_date,note,remark,payment_method,bank_name,chque_no,"OPEN"});	
										}
										String query11="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,paid_by,cheque_no,cheque_date,bank_name) "
												+ "VALUES ('"+txtNew.getText()+"-"+rowcount+"-1','"+txtNew.getText()+"','"+acc_num+"','"+resultSet1.getString("acc_name")+"','"+cr_amt+"','"+dr_amt+"','"+textField_7.getText()+"','"+textField_8.getText()+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+payment_method+"','"+chque_no+"','"+cheque_date+"','"+bank_name+"')";
										statement.executeUpdate(query11);
									}
								}
								else
								{
									if(payment_method.equals("CASH"))
									{
									//model.addRow(new Object[]{model.getRowCount()+1,txtNew.getText()+"-"+rowcount+"-1", "0101003001", "MAIN CASH ACCOUNT AT S01",cr_amt,dr_amt,cheque_date,note,remark,payment_method,bank_name,chque_no,"OPEN"});
									String query11="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,paid_by,cheque_no,cheque_date,bank_name) "
											+ "VALUES ('"+txtNew.getText()+"-"+rowcount+"-1','"+txtNew.getText()+"','"+cash_acc+"','"+cash_acc_name+"','"+cr_amt+"','"+dr_amt+"','"+textField_7.getText()+"','"+textField_8.getText()+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+payment_method+"','"+chque_no+"','"+cheque_date+"','"+bank_name+"')";
									statement.executeUpdate(query11);
									}
								}
								textField_3.setText("");
								textField_4.setText("");
								textField_5.setText("");
								textField_6.setText("");
								textField_7.setText("");
								textField_8.setText("");
								textField_11.setText("");
								textField_14.setText("");
								//pre_load(company,txtNew.getText(),decimal);
								double cramt=0;
								double dramt=0;
								for(int x=0;x<model.getRowCount();x++)
								{
									if(table.getValueAt(x, 12).toString().equals("VOID"))
									{
										
									}
									else
									{
									double cr=Double.parseDouble(table.getValueAt(x, 5).toString());
									double dr=Double.parseDouble(table.getValueAt(x, 4).toString());
									cramt=cramt+cr;
									dramt=dramt+dr;
									}
								}
								textField.setText(Double.toString(dramt));
								textField_9.setText(Double.toString(cramt));
								textField_10.setText(Double.toString(dramt-cramt));
								
								textField_5.setEditable(true);
								textField_6.setEditable(true);
								
								textField_3.requestFocus();
								con.close();
							}
							table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
							//table.addRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
							
							pre_load(company, txtNew.getText(), decimal);
						}
					}
					catch(Exception e1){
						System.err.println(e1);
					}
					
				}
			}
			else
			{
				String accode =textField_3.getText();
				if(accode.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Select or Enter An Ac code");
					textField_3.requestFocus();
				}
				else
				{
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement();
					String sel_query="select * from "+company+".mr_jv_details where jv_seq='"+textField_11.getText()+"' ";
					ResultSet resultSet18= statement.executeQuery(sel_query);
					if(resultSet18.next())
					{
						String del_cheque="delete from "+company+".mr_cheque_details where tran_num= '"+txtNew.getText()+"' and  cheque_no='"+resultSet18.getString("cheque_no")+"' and bank_name='"+resultSet18.getString("bank_name")+"' ";
						statement.executeUpdate(del_cheque);
						String del_query="delete from "+company+".mr_jv_details where jv_seq  like '"+textField_11.getText()+"%'";
						statement.executeUpdate(del_query);
					}
					
					
					//String ac_desc=textField_4.getText();
					String dr_amt=textField_5.getText();
					if(dr_amt.equals(""))
					{
						dr_amt="0.0";
					}
					String cr_amt=textField_6.getText();
					if(cr_amt.equals(""))
					{
						cr_amt="0.0";
					}
					//String note=textField_7.getText();
					//String remark=textField_8.getText();
					String payment_method=comboBox_1.getSelectedItem().toString();
					String bank_name="";
					String chque_no=textField_12.getText();
					if(payment_method.equals("BANK"))
					{
						bank_name=comboBox_2.getSelectedItem().toString();
						if(chque_no.equals(""))
						{
							JOptionPane.showMessageDialog(frame, "Enter Cheque Number");
							return;
						}
					}
					String cheque_date = null;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String jv_date = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					if(!jv_date.equals(""))
					{
						cheque_date = sdf.format(dateChooser_1.getDate());
					}
					try{
						
						String cheque="SELECT * FROM "+company+".mr_cheque_details where cheque_no='"+chque_no+"' and bank_name='"+bank_name+"' and tran_num!='"+txtNew.getText()+"' "; 
						ResultSet resultSet22 = statement.executeQuery(cheque);
						if(resultSet22.next())
						{
							JOptionPane.showMessageDialog(frame, "Cheque Number Already Approved For Another Invoice");
						}
						else
						{
							int row=model.getRowCount();
							if(row==0)
							{
								int rowcount=model.getRowCount()+1;
								LocalDateTime myDateObj = LocalDateTime.now();  
							    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
							    String formattedDate = myDateObj.format(myFormatObj);  
							    String query="SELECT * FROM "+company+".mr_jv_details where jv_seq='"+textField_11.getText()+"' and id='"+textField_14.getText()+"' ";
								ResultSet resultSet = statement.executeQuery(query);
								if(resultSet.next())
								{
									String query1="update "+company+".mr_jv_details set `ac_code`='"+textField_3.getText()+"', `ac_desc`='"+textField_4.getText()+"', `dr_amt`'"+dr_amt+"', `cr_amt`='"+cr_amt+"', `note`='"+textField_7.getText()+"',"
											+ " `remark`='"+textField_8.getText()+"', `modified_date`='"+formattedDate+"',paid_by='"+payment_method+"',cheque_no='"+chque_no+"',cheque_date='"+cheque_date+"',bank_name='"+bank_name+"' where jv_seq='"+textField_11.getText()+"' and id='"+textField_14.getText()+"' ";
									statement.executeUpdate(query1);
								}
								
								
								
								if(payment_method.equals("BANK"))
								{
									String query1="insert into "+company+".mr_cheque_details( `tran_num`, `pay_by`,pay_id, `dr`, `cr`, `issue_date`, `cheque_no`, `cheque_date`,  `printing_user`,  `handover_user`, `posted_user`, `remark`, `note`,status,bank_name,page_type) "
											+ "VALUES ('"+txtNew.getText()+"','"+textField_4.getText()+"','"+textField_3.getText()+"','"+cr_amt+"','"+dr_amt+"','"+formattedDate+"','"+chque_no+"','"+cheque_date+"','"+username+"','"+username+"','"+username+"','"+textField_8.getText()+"','"+textField_7.getText()+"','OPEN','"+bank_name+"','JV')";
									statement.executeUpdate(query1);
									
									
									String sel_bank="select * from "+company+".mr_acc_paycode where paycode_name='"+bank_name+"' ";
									ResultSet resultSet2 = statement.executeQuery(sel_bank);
									if(resultSet2.next())
									{
										String acc_num=resultSet2.getString("default_ac");
										String sel_acc="select * from "+company+".mr_chart_accnt_mstr where acc_code='"+acc_num+"' ";
										ResultSet resultSet1 = statement.executeQuery(sel_acc);
//										if(resultSet1.next())
//										{
//											model.addRow(new Object[]{model.getRowCount()+1,txtNew.getText()+"-"+rowcount+"-1", acc_num, resultSet1.getString("acc_name"),cr_amt,dr_amt,cheque_date,note,remark,payment_method,bank_name,chque_no,"OPEN"});	
//										}
										String query11="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,paid_by,cheque_no,cheque_date,bank_name) "
												+ "VALUES ('"+txtNew.getText()+"-"+rowcount+"-1','"+txtNew.getText()+"','"+acc_num+"','"+resultSet1.getString("acc_name")+"','"+cr_amt+"','"+dr_amt+"','"+textField_7.getText()+"','"+textField_8.getText()+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+payment_method+"','"+chque_no+"','"+cheque_date+"','"+bank_name+"')";
										statement.executeUpdate(query11);
									}
									
									
								}
								else
								{
									if(payment_method.equals("CASH"))
									{
									//model.addRow(new Object[]{model.getRowCount()+1,txtNew.getText()+"-"+rowcount+"-1", "0101003001", "MAIN CASH ACCOUNT AT S01",cr_amt,dr_amt,cheque_date,note,remark,payment_method,bank_name,chque_no,"OPEN"});
									String query11="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,paid_by,cheque_no,cheque_date,bank_name) "
											+ "VALUES ('"+txtNew.getText()+"-"+rowcount+"-1','"+txtNew.getText()+"','0101003001','MAIN CASH ACCOUNT AT S01','"+cr_amt+"','"+dr_amt+"','"+textField_7.getText()+"','"+textField_8.getText()+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+payment_method+"','"+chque_no+"','"+cheque_date+"','"+bank_name+"')";
									statement.executeUpdate(query11);
									}
								}
								textField_3.setText("");
								textField_4.setText("");
								textField_5.setText("");
								textField_6.setText("");
								textField_7.setText("");
								textField_8.setText("");
								textField_11.setText("");
								textField_14.setText("");
								double cramt=0;
								double dramt=0;
								for(int x=0;x<model.getRowCount();x++)
								{
									if(table.getValueAt(x, 12).toString().equals("VOID"))
									{
										
									}
									else
									{
										double cr=Double.parseDouble(table.getValueAt(x, 5).toString());
										double dr=Double.parseDouble(table.getValueAt(x, 4).toString());
										cramt=cramt+cr;
										dramt=dramt+dr;
									}
									
								}
								textField.setText(Double.toString(dramt));
								textField_9.setText(Double.toString(cramt));
								textField_10.setText(Double.toString(dramt-cramt));
								textField_5.setEditable(true);
								textField_6.setEditable(true);
								textField_3.requestFocus();
								con.close();
							}
							else
							{
//								int a=0;
//								int b=0;
//								for(int i=0;i<model.getRowCount();i++)
//								{
//									
//									String type=table.getValueAt(i, 9).toString();
//									if(type.equals("ACCOUNT"))
//									{
//										a++;
//									}
//									else
//									{
//										b++;
//									}
//									
//								}
								
								//int rowcounter=(b/2)+a;
								
								//int rowcount=rowcounter+1;
							//model.addRow(new Object[]{model.getRowCount()+1,txtNew.getText()+"-"+rowcount, accode, ac_desc,dr_amt,cr_amt,cheque_date,note,remark,payment_method,bank_name,chque_no,"OPEN"});
								//model.addRow(new Object[]{model.getRowCount()+1,txtNew.getText()+"-"+rowcount, accode, ac_desc,dr_amt,cr_amt,note,remark,"OPEN"});
								LocalDateTime myDateObj = LocalDateTime.now();  
							    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
							    String formattedDate = myDateObj.format(myFormatObj);  
							    String query1="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,paid_by,cheque_no,cheque_date,bank_name) "
										+ "VALUES ('"+textField_11.getText()+"','"+txtNew.getText()+"','"+textField_3.getText()+"','"+textField_4.getText()+"','"+dr_amt+"','"+cr_amt+"','"+textField_7.getText()+"','"+textField_8.getText()+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+payment_method+"','"+chque_no+"','"+cheque_date+"','"+bank_name+"')";
								statement.executeUpdate(query1);
								if(payment_method.equals("BANK"))
								{
									String query21="insert into "+company+".mr_cheque_details( `tran_num`, `pay_by`,pay_id, `dr`, `cr`, `issue_date`, `cheque_no`, `cheque_date`,  `printing_user`,  `handover_user`, `posted_user`, `remark`, `note`,status,bank_name,page_type) "
											+ "VALUES ('"+txtNew.getText()+"','"+textField_4.getText()+"','"+textField_3.getText()+"','"+cr_amt+"','"+dr_amt+"','"+formattedDate+"','"+chque_no+"','"+cheque_date+"','"+username+"','"+username+"','"+username+"','"+textField_8.getText()+"','"+textField_7.getText()+"','OPEN','"+bank_name+"','JV')";
									statement.executeUpdate(query21);
									String sel_bank="select * from "+company+".mr_acc_paycode where paycode_name='"+bank_name+"' ";
									ResultSet resultSet = statement.executeQuery(sel_bank);
									if(resultSet.next())
									{
										String acc_num=resultSet.getString("default_ac");
										String sel_acc="select * from "+company+".mr_chart_accnt_mstr where acc_code='"+acc_num+"' ";
										ResultSet resultSet1 = statement.executeQuery(sel_acc);
										if(resultSet1.next())
										{
											//model.addRow(new Object[]{model.getRowCount()+1,txtNew.getText()+"-"+rowcount+"-1", acc_num, resultSet1.getString("acc_name"),cr_amt,dr_amt,cheque_date,note,remark,payment_method,bank_name,chque_no,"OPEN"});	
										}
										String query11="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,paid_by,cheque_no,cheque_date,bank_name) "
												+ "VALUES ('"+textField_11.getText()+"-1','"+txtNew.getText()+"','"+acc_num+"','"+resultSet1.getString("acc_name")+"','"+cr_amt+"','"+dr_amt+"','"+textField_7.getText()+"','"+textField_8.getText()+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+payment_method+"','"+chque_no+"','"+cheque_date+"','"+bank_name+"')";
										statement.executeUpdate(query11);
									}
								}
								else
								{
									if(payment_method.equals("CASH"))
									{
									//model.addRow(new Object[]{model.getRowCount()+1,txtNew.getText()+"-"+rowcount+"-1", "0101003001", "MAIN CASH ACCOUNT AT S01",cr_amt,dr_amt,cheque_date,note,remark,payment_method,bank_name,chque_no,"OPEN"});
									String query11="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,paid_by,cheque_no,cheque_date,bank_name) "
											+ "VALUES ('"+textField_11.getText()+"-1','"+txtNew.getText()+"','0101003001','MAIN CASH ACCOUNT AT S01','"+cr_amt+"','"+dr_amt+"','"+textField_7.getText()+"','"+textField_8.getText()+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+payment_method+"','"+chque_no+"','"+cheque_date+"','"+bank_name+"')";
									statement.executeUpdate(query11);
									}
								}
								textField_3.setText("");
								textField_4.setText("");
								textField_5.setText("");
								textField_6.setText("");
								textField_7.setText("");
								textField_8.setText("");
								textField_11.setText("");
								textField_14.setText("");
								//pre_load(company,txtNew.getText(),decimal);
								double cramt=0;
								double dramt=0;
								for(int x=0;x<model.getRowCount();x++)
								{
									if(table.getValueAt(x, 12).toString().equals("VOID"))
									{
										
									}
									else
									{
									double cr=Double.parseDouble(table.getValueAt(x, 5).toString());
									double dr=Double.parseDouble(table.getValueAt(x, 4).toString());
									cramt=cramt+cr;
									dramt=dramt+dr;
									}
								}
								textField.setText(Double.toString(dramt));
								textField_9.setText(Double.toString(cramt));
								textField_10.setText(Double.toString(dramt-cramt));
								
								textField_5.setEditable(true);
								textField_6.setEditable(true);
								
								textField_3.requestFocus();
								con.close();
							}
							table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
							//table.addRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
							
							//pre_load(company, txtNew.getText(), decimal);
							serch_load(company,txtNew.getText(),decimal,textField_13.getText().trim());
						}
					}
					catch(Exception e1){
						System.err.println(e1);
					}
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
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
