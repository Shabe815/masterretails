package sales_master;

import java.awt.EventQueue;

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
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;



import CafePro.Mainpage;

public class pay_in {

	private JFrame frmPayInScreen;
	private JTextField textField;
	private JTextField textField_2;
	private boolean dot = false;
	private JTextArea textArea;
	private JComboBox<String> comboBox_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pay_in window = new pay_in("mr_pro","admin","S01","S01", null,"\\\\Accounts\\epson");
					window.frmPayInScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public pay_in(String company,  String username, String store_name,String reg_nme,String version,String printer) throws SQLException {
		initialize(company,username,store_name,reg_nme, version,printer);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @throws SQLException 
	 */
	private void initialize(final String company,final String username,final String store_name,final String reg_nme,String version,final String printer) throws SQLException {
		frmPayInScreen = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frmPayInScreen.setIconImage(img);
		frmPayInScreen.getContentPane().setBackground(Color.DARK_GRAY);
		frmPayInScreen.setTitle("PAY IN SCREEN");
		frmPayInScreen.setBounds(100, 100, 500, 608);
		frmPayInScreen.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.btnPayIn.setEnabled(true);
					PosscreenArabic.txtDisc.requestFocus();
				}
				else
				{
					posscreen.btnPayIn.setEnabled(true);
					posscreen.txtDisc.requestFocus();
				}
			}
			@Override
		      public void windowIconified(WindowEvent we) {
				frmPayInScreen.setState(JFrame.NORMAL);
		      }
		});
		frmPayInScreen.getContentPane().setLayout(null);
		frmPayInScreen.setAlwaysOnTop(true);
		frmPayInScreen.setVisible(true);
		
		JLabel lblReasonCode = new JLabel("Reason Code:");
		lblReasonCode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReasonCode.setForeground(new Color(100, 149, 237));
		lblReasonCode.setFont(new Font("Arial", Font.BOLD, 15));
		lblReasonCode.setBounds(10, 11, 100, 25);
		frmPayInScreen.getContentPane().add(lblReasonCode);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(10, 40, 464, 25);
		frmPayInScreen.getContentPane().add(textField);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(10, 41, 464, 25);
		frmPayInScreen.getContentPane().add(comboBox_1);
		
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField.setText(null);
				frmPayInScreen.getContentPane().add(comboBox_1);
				if(comboBox_1.getItemCount()>0)
				{
					comboBox_1.showPopup();
				}
		   	}
			
			});
		textField.addKeyListener(new KeyAdapter() {
			@Override
				public void keyPressed(KeyEvent e) {
			    	if (e.getKeyCode() == 38) {
			    		int x = comboBox_1.getSelectedIndex();
			    		if (x > 0) {
			    			comboBox_1.setSelectedIndex(x - 1);
			    		}
			    		frmPayInScreen.getContentPane().add(comboBox_1);
			    		if(comboBox_1.getItemCount()>0)
						{
							comboBox_1.showPopup();
						}
			    	} 
			    	else if (e.getKeyCode() == 40) {
			    		int x = comboBox_1.getSelectedIndex();
			    		int y = comboBox_1.getItemCount();
			    		if (x + 1 < y)
			    			comboBox_1.setSelectedIndex(x + 1);
			    		frmPayInScreen.getContentPane().add(comboBox_1);
			    		if(comboBox_1.getItemCount()>0)
						{
							comboBox_1.showPopup();
						}
			    	}
			   	}
			});
			textField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if(textField.getText().equals("")){
							textField.setText(comboBox_1.getSelectedItem().toString());
							
			    		}
						textField.setText(comboBox_1.getSelectedItem().toString());
						comboBox_1.removeAllItems();
						comboBox_1.hidePopup();
						frmPayInScreen.getContentPane().remove(comboBox_1);
						
					} catch (Exception e) {
					}
				}
			});
			textField.addCaretListener(new TextFieldCaretListener(company,"POS"));
			textField.setColumns(10);
		
		JLabel lblRemarks = new JLabel("Remarks:");
		lblRemarks.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRemarks.setForeground(new Color(100, 149, 237));
		lblRemarks.setFont(new Font("Arial", Font.BOLD, 15));
		lblRemarks.setBounds(10, 98, 73, 25);
		frmPayInScreen.getContentPane().add(lblRemarks);
		
		JCheckBox checkBox = new JCheckBox("On Screen Keyboard");
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ad) {
				try {
					textField.requestFocus();
					if(ad.getStateChange() == ItemEvent.SELECTED) {
						//lblNewLabel.
						Runtime.getRuntime().exec("cmd /c osk");
					} 
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		checkBox.setBackground(new Color(243, 229, 216));
		checkBox.setBounds(10, 68, 145, 23);
		frmPayInScreen.getContentPane().add(checkBox);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(252, 178, 222, 296);
		frmPayInScreen.getContentPane().add(panel);
		
		JButton button_1 = new JButton("1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("1");
			}
		});
		button_1.setFont(new Font("Arial", Font.BOLD, 30));
		button_1.setBounds(10, 11, 60, 60);
		panel.add(button_1);
		
		JButton button_2 = new JButton("2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("2");
			}
		});
		button_2.setFont(new Font("Arial", Font.BOLD, 30));
		button_2.setBounds(80, 11, 60, 60);
		panel.add(button_2);
		
		JButton button_3 = new JButton("3");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("3");
			}
		});
		button_3.setFont(new Font("Arial", Font.BOLD, 30));
		button_3.setBounds(150, 11, 60, 60);
		panel.add(button_3);
		
		JButton button_4 = new JButton("4");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("4");
			}
		});
		button_4.setFont(new Font("Arial", Font.BOLD, 30));
		button_4.setBounds(10, 82, 60, 60);
		panel.add(button_4);
		
		JButton button_5 = new JButton("5");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("5");
			}
		});
		button_5.setFont(new Font("Arial", Font.BOLD, 30));
		button_5.setBounds(80, 82, 60, 60);
		panel.add(button_5);
		
		JButton button_6 = new JButton("6");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("6");
			}
		});
		button_6.setFont(new Font("Arial", Font.BOLD, 30));
		button_6.setBounds(150, 82, 60, 60);
		panel.add(button_6);
		
		JButton button_7 = new JButton("7");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("7");
			}
		});
		button_7.setFont(new Font("Arial", Font.BOLD, 30));
		button_7.setBounds(10, 153, 60, 60);
		panel.add(button_7);
		
		JButton button_8 = new JButton("8");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("8");
			}
		});
		button_8.setFont(new Font("Arial", Font.BOLD, 30));
		button_8.setBounds(80, 153, 60, 60);
		panel.add(button_8);
		
		JButton button_9 = new JButton("9");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("9");
			}
		});
		button_9.setFont(new Font("Arial", Font.BOLD, 30));
		button_9.setBounds(150, 153, 60, 60);
		panel.add(button_9);
		
		JButton button_10 = new JButton("0");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("0");
			}
		});
		button_10.setFont(new Font("Arial", Font.BOLD, 30));
		button_10.setBounds(80, 223, 60, 60);
		panel.add(button_10);
		
		JButton button_11 = new JButton("<HTML>BACK<BR>  \r\nSPACE</HTML>");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField_2.getText().equals(""))
				{
					textField_2.setText(textField_2.getText().substring(0, textField_2.getText().length() - 1));
				}
			}
		});
		button_11.setHorizontalAlignment(SwingConstants.LEFT);
		button_11.setFont(new Font("Arial", Font.BOLD, 12));
		button_11.setBounds(150, 224, 60, 60);
		panel.add(button_11);
		
		JButton button_12 = new JButton(".");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn(".");
			}
		});
		button_12.setFont(new Font("Arial", Font.BOLD, 30));
		button_12.setBounds(10, 224, 60, 60);
		panel.add(button_12);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_2.getText().equals(""))
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
		textField_2.setBounds(252, 120, 222, 47);
		frmPayInScreen.getContentPane().add(textField_2);
		
		JLabel lblAmountReceived = new JLabel("Amount received:");
		lblAmountReceived.setHorizontalAlignment(SwingConstants.LEFT);
		lblAmountReceived.setForeground(new Color(100, 149, 237));
		lblAmountReceived.setFont(new Font("Arial", Font.BOLD, 15));
		lblAmountReceived.setBounds(252, 84, 143, 25);
		frmPayInScreen.getContentPane().add(lblAmountReceived);
		
		JButton button_13 = new JButton("EXIT");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPayInScreen.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.btnPayIn.setEnabled(true);
					PosscreenArabic.txtDisc.requestFocus();
				}
				else
				{
					posscreen.btnPayIn.setEnabled(true);
					posscreen.txtDisc.requestFocus();
				}
			}
		});
		button_13.setBounds(339, 512, 135, 38);
		frmPayInScreen.getContentPane().add(button_13);
		
		JButton btnSavePrint = new JButton("SAVE & PRINT");
		btnSavePrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frmPayInScreen, "Please Enter The Reason code");
				}
				else if(textField_2.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frmPayInScreen, "Please Enter The Amount");
				}
				else
				{
					
					String paylist="select * from "+company+".mr_pos_payreasons where type='PAYIN' and reason='"+textField.getText().trim()+"'";
					Connection con=Dbsetup.dbconn.getConnection();
					  
					ResultSet resultSet;
					try {
						Statement statement1=con.createStatement();
						resultSet = statement1.executeQuery(paylist);
						if(!resultSet.next())
						{
							String query="insert into "+company+".mr_pos_payreasons (`type`, `reason`) VALUES ('PAYIN','"+textField.getText()+"') ";
							statement1.executeUpdate(query);
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    final Calendar cal = Calendar.getInstance();
				    final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
				    Statement statement;
					try {
						statement = con.createStatement();
						String query="insert into "+company+".mr_paylist (`type`, `reason_code`, `reason`, `amount`, `ref_reg_no`, `user`, `created_date`,time,store_id) VALUES ('payin','"+textField.getText()+"','"+textArea.getText()+"','"+textField_2.getText()+"','"+reg_nme+"','"+username+"','"+formattedDate+"','"+times.format(cal.getTime())+"','"+store_name+"') ";
						statement.executeUpdate(query);
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
						report_master.payinout.report("payin", con, printer,company);
						JOptionPane.showMessageDialog(frmPayInScreen, "Saved Successfully");
						
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					frmPayInScreen.setVisible(false);
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.btnPayIn.setEnabled(true);
						PosscreenArabic.txtDisc.requestFocus();
					}
					else
					{
						posscreen.btnPayIn.setEnabled(true);
						posscreen.txtDisc.requestFocus();
					}
					
				}
				
			}
		});
		btnSavePrint.setBounds(173, 512, 135, 38);
		frmPayInScreen.getContentPane().add(btnSavePrint);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				textField_2.setText("");
				textField.setText("");
			}
		});
		btnClear.setBounds(10, 512, 135, 38);
		frmPayInScreen.getContentPane().add(btnClear);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textArea.setBounds(10, 121, 230, 353);
		frmPayInScreen.getContentPane().add(textArea);
	}
	private void click_btn(String num)
	{
		try{
			String pass_string=textField_2.getText();
			pass_string+=num;
			textField_2.setText(pass_string);
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	private class TextFieldCaretListener implements CaretListener {
		List<String> listcode=null;
		public TextFieldCaretListener(String company,String page) throws SQLException{
			listcode = new ArrayList<String>();
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select reason from "+company+".mr_pos_payreasons where type='PAYIN'");
			while(resultSet.next())
			{
				listcode.add(resultSet.getString("reason"));
			}
		}
		public void caretUpdate(CaretEvent e) {
			try {
				comboBox_1.removeAllItems();
				comboBox_1.hidePopup();
				frmPayInScreen.getContentPane().remove(comboBox_1);
				if (e.getMark() >=0) {
					for (String string : listcode) {
						if (string.toLowerCase().contains(textField.getText().toLowerCase())) {
							frmPayInScreen.getContentPane().add(comboBox_1);
							comboBox_1.addItem(string);
							if(comboBox_1.getItemCount()>0)
							{
								comboBox_1.showPopup();
							}
						}
					}
				}
			} 
			catch (Exception e1) {
			}
			if (e.getMark() < 1) {
				frmPayInScreen.getContentPane().remove(comboBox_1);
			}
		}
	}
}
