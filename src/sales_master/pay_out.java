package sales_master;

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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JCheckBox;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import CafePro.Mainpage;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.IOException;

public class pay_out {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private JComboBox<String> comboBox_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pay_out window = new pay_out("mr_pro", "admin","S01","S01", null,"\\\\Accounts\\epson");
					window.frame.setVisible(true);
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
	public pay_out(String company, String username, String store_name,String reg_nme,String version,String printer) throws SQLException {
		initialize(company,username,store_name,reg_nme, version,printer);
	}


	/**
	 * Initialize the contents of the frame.
	 * @param reg_nme 
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param printer 
	 * @throws SQLException 
	 */
	private void initialize(final String company,final String username,final String store_name,final String reg_nme,String version, final String printer) throws SQLException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 500, 600);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btnPayOut.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btnPayOut.setEnabled(true);
				}
				
			}
			@Override
		      public void windowIconified(WindowEvent we) {
				frame.setState(JFrame.NORMAL);
		      }
		});
		frame.setTitle("PAY OUT SCREEN");
		frame.getContentPane().setLayout(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		
		JLabel label = new JLabel("Reason Code:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(new Color(100, 149, 237));
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setBounds(10, 11, 100, 25);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(10, 40, 464, 25);
		frame.getContentPane().add(textField);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(10, 41, 464, 25);
		frame.getContentPane().add(comboBox_1);
		
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField.setText(null);
				frame.getContentPane().add(comboBox_1);
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
			    		frame.getContentPane().add(comboBox_1);
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
			    		frame.getContentPane().add(comboBox_1);
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
						frame.getContentPane().remove(comboBox_1);
						
					} catch (Exception e) {
					}
				}
			});
			textField.addCaretListener(new TextFieldCaretListener(company,"POS"));
			textField.setColumns(10);
		
		
		JLabel label_1 = new JLabel("Remarks:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(new Color(100, 149, 237));
		label_1.setFont(new Font("Arial", Font.BOLD, 15));
		label_1.setBounds(10, 95, 73, 25);
		frame.getContentPane().add(label_1);
		
		JLabel lblAmountPaid = new JLabel("Amount paid:");
		lblAmountPaid.setHorizontalAlignment(SwingConstants.LEFT);
		lblAmountPaid.setForeground(new Color(100, 149, 237));
		lblAmountPaid.setFont(new Font("Arial", Font.BOLD, 15));
		lblAmountPaid.setBounds(221, 83, 143, 25);
		frame.getContentPane().add(lblAmountPaid);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(252, 119, 222, 47);
		frame.getContentPane().add(textField_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(252, 177, 222, 296);
		frame.getContentPane().add(panel);
		
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
				if(!textField_1.getText().equals(""))
				{
					textField_1.setText(textField_1.getText().substring(0, textField_1.getText().length() - 1));
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
		
		JButton button_13 = new JButton("CLEAR");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				textField.setText("");
				textField_1.setText("");
			}
		});
		button_13.setBounds(10, 512, 135, 38);
		frame.getContentPane().add(button_13);
		
		JButton button_14 = new JButton("SAVE & PRINT");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter The Reason code");
				}
				else if(textField_1.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter The Amount");
				}
				
				else
				{
					
					String paylist="select * from "+company+".mr_pos_payreasons where type='PAYOUT' and reason='"+textField.getText().trim()+"'";
					Connection con=Dbsetup.dbconn.getConnection();
					  
					ResultSet resultSet;
					try {
						Statement statement1=con.createStatement();
						resultSet = statement1.executeQuery(paylist);
						if(!resultSet.next())
						{
							String query="insert into "+company+".mr_pos_payreasons (`type`, `reason`) VALUES ('PAYOUT','"+textField.getText()+"') ";
							statement1.executeUpdate(query);
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
					String amount="-"+textField_1.getText();
					
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    final Calendar cal = Calendar.getInstance();
				    final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
				    
					Statement statement;
					try {
						statement = con.createStatement();
						String query="insert into "+company+".mr_paylist (`type`, `reason_code`, `reason`, `amount`, `ref_reg_no`, `user`, `created_date`,time,store_id) VALUES ('payout','"+textField.getText()+"','"+textArea.getText()+"','"+amount+"','"+reg_nme+"','"+username+"','"+formattedDate+"','"+times.format(cal.getTime())+"','"+store_name+"') ";
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
						report_master.payinout.report("payout", con, printer, company);
						JOptionPane.showMessageDialog(frame, "Saved Successfully");
						
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.setVisible(false);
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.txtDisc.requestFocus();
						PosscreenArabic.btnPayOut.setEnabled(true);
					}
					else
					{
						posscreen.txtDisc.requestFocus();
						posscreen.btnPayOut.setEnabled(true);
					}
				}
			}
		});
		button_14.setBounds(176, 512, 135, 38);
		frame.getContentPane().add(button_14);
		
		JButton button_15 = new JButton("EXIT");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btnPayOut.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btnPayOut.setEnabled(true);
				}
			}
		});
		button_15.setBounds(339, 512, 135, 38);
		frame.getContentPane().add(button_15);
		
		textArea = new JTextArea();
		textArea.setBounds(12, 131, 228, 342);
		frame.getContentPane().add(textArea);
		
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
		checkBox.setBounds(10, 73, 145, 23);
		frame.getContentPane().add(checkBox);
	}
	private void click_btn(String num)
	{
		try{
			String pass_string=textField_1.getText();
			pass_string+=num;
			textField_1.setText(pass_string);
			
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
			ResultSet resultSet = statement.executeQuery("select reason from "+company+".mr_pos_payreasons where type='PAYOUT' ");
			while(resultSet.next())
			{
				listcode.add(resultSet.getString("reason"));
			}
		}
		public void caretUpdate(CaretEvent e) {
			try {
				comboBox_1.removeAllItems();
				comboBox_1.hidePopup();
				frame.getContentPane().remove(comboBox_1);
				if (e.getMark() >=0) {
					for (String string : listcode) {
						if (string.toLowerCase().contains(textField.getText().toLowerCase())) {
							frame.getContentPane().add(comboBox_1);
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
//			if (e.getMark() < 1) {
//				frame.getContentPane().remove(comboBox_1);
//			}
		}
	}
}
