package sales_master;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import CafePro.Mainpage;

public class Open_Cash {

	private JFrame frame;
	private JTextField textField_12;
	private boolean dot = false;
	private JTable table;
	private DefaultTableModel model;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JComboBox<String> comboBox;
	private String printer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Open_Cash window = new Open_Cash("","mr_pro", "S01","SHABEER","S0101", "","","127.0.0.1", null,"");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param con1 
	 * @param language 
	 */
	public Open_Cash(String db_name,String company, String store_name, String username,String posreg,String version,String decimal,String server, Connection con1, String language) {
		initialize(db_name,company,store_name,username,posreg, version,decimal,server,con1,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store_name 
	 * @param host_name 
	 * @param company 
	 * @param decimal 
	 * @param server 
	 * @param con1 
	 * @param language 
	 */
	private void initialize(final String db_name,final String company,final String store_name,final String username,final String posreg,final String version, final String decimal,final String server, final Connection con1, final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 792, 720);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = new Date();
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 776, 682);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(397, 11, 1, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(415, 77, 1, 605);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 77, 776, 2);
		panel.add(separator_2);
		
		JButton btnDrawer = new JButton("DRAWER");
		if(language.equals("Arabic"))
		{
			btnDrawer.setText("œ—Ã «·‰ﬁÊœ");
		}
		btnDrawer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
			}
		});
		btnDrawer.setBounds(10, 23, 105, 43);
		panel.add(btnDrawer);
		
		JButton btnOk = new JButton("OK");
		if(language.equals("Arabic"))
		{
			btnOk.setText("‰⁄„");
		}
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					File configFile = new File("C:/MasterRetails/config.properties");
					FileReader reader = new FileReader(configFile);
				    Properties props = new Properties();
				    props.load(reader);
				    String open_register = props.getProperty("open_register");
				    String multi_register = props.getProperty("multi_register");
				    String[] open_reg=open_register.split(",");
				    reader.close();
				    
				    int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Open Register","WARNING",JOptionPane.YES_NO_OPTION);
			        // 0=yes, 1=no, 2=cancel
			        if(cofrm_bx==0)
			        {
			        	LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    final Calendar cal = Calendar.getInstance();
					    final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
					    String formattedDate = myDateObj.format(myFormatObj);  
					    Statement statement;
						try {
							InetAddress addr = InetAddress.getLocalHost();
						    String sys_name = addr.getHostName();
							statement = con1.createStatement();
							ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_register_openings where reg_num='"+comboBox.getSelectedItem().toString()+"' ");
							if(resultSet.next())
							{
								JOptionPane.showMessageDialog(frame, "This Register Is Already Opened");
								frame.setVisible(false);
							}
							else
							{
								if(multi_register.equals("1"))
					        	{
									for(int i=0;i<open_reg.length;i++)
									{
										int receipt=0;
										ResultSet resultSet1111 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+open_reg[i]+"' ");
										if(resultSet1111.next())
										{
											receipt=Integer.parseInt(resultSet1111.getString("invoice_number"))+Integer.parseInt(resultSet1111.getString("next_num"));
										}
										
										ResultSet resultSet1101 = statement.executeQuery("select * from "+company+".mr_register_openings where reg_num='"+open_reg[i]+"' ");
										if(resultSet1101.next())
										{
											String query="delete from "+company+".mr_register_openings where reg_num='"+open_reg[i]+"' ";
											statement.executeUpdate(query);
										}
										
										String query="insert into "+company+".mr_register_openings (`reg_num`, `user_name`, `open_receipt`, `remarks`, `date_time`, `created_date`,store,`device_name`) values('"+open_reg[i]+"','"+username+"','"+receipt+"','"+textField_5.getText()+"','"+times.format(cal.getTime())+"','"+formattedDate+"','"+store_name+"','"+sys_name+"') ";
										statement.executeUpdate(query);
										ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+open_reg[i]+"' ");
										if(!resultSet1.next())
										{
											for(int x=0;x<model.getRowCount();x++)
											{
												String query1="insert into "+company+".mr_register_open_items ( `ref_reg_num`, `date_time`, `paycode`, `pay_desc`, `amount`, `created_date`) values('"+open_reg[i]+"','"+formattedDate+"','"+model.getValueAt(x, 1).toString()+"','"+model.getValueAt(x, 2).toString()+"','"+model.getValueAt(x, 3).toString()+"','"+formattedDate+"') ";
												statement.executeUpdate(query1);
											}
										}
										
										String query123="INSERT INTO "+company+".mr_denomination( `ref_reg_num`, `denomination`, `count`, `total_amt`, `created_date`, `modified_date`) VALUES"
												+ "('"+open_reg[i]+"','1000','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
												+ "('"+open_reg[i]+"','500','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
												+ "('"+open_reg[i]+"','200','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
												+ "('"+open_reg[i]+"','100','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
												+ "('"+open_reg[i]+"','50','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
												+ "('"+open_reg[i]+"','20','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
												+ "('"+open_reg[i]+"','10','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
												+ "('"+open_reg[i]+"','5','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
												+ "('"+open_reg[i]+"','1','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
												+ "('"+open_reg[i]+"','0.50','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
												+ "('"+open_reg[i]+"','0.25','0' ,'0','"+formattedDate+"','"+formattedDate+"') ";
										statement.execute(query123);
									}
					        		
					        	}
								String query="insert into "+company+".mr_register_openings (`reg_num`, `user_name`, `open_receipt`, `remarks`, `date_time`, `created_date`,store,`device_name`) values('"+comboBox.getSelectedItem().toString()+"','"+username+"','"+textField_3.getText()+"','"+textField_5.getText()+"','"+times.format(cal.getTime())+"','"+formattedDate+"','"+store_name+"','"+sys_name+"') ";
								statement.executeUpdate(query);
								ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+comboBox.getSelectedItem().toString()+"' ");
								if(!resultSet1.next())
								{
									for(int i=0;i<model.getRowCount();i++)
									{
										String query1="insert into "+company+".mr_register_open_items ( `ref_reg_num`, `date_time`, `paycode`, `pay_desc`, `amount`, `created_date`) values('"+comboBox.getSelectedItem().toString()+"','"+formattedDate+"','"+model.getValueAt(i, 1).toString()+"','"+model.getValueAt(i, 2).toString()+"','"+model.getValueAt(i, 3).toString()+"','"+formattedDate+"') ";
										statement.executeUpdate(query1);
									}
								}
								
								String query123="INSERT INTO "+company+".mr_denomination( `ref_reg_num`, `denomination`, `count`, `total_amt`, `created_date`, `modified_date`) VALUES"
										+ "('"+comboBox.getSelectedItem().toString()+"','1000','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
										+ "('"+comboBox.getSelectedItem().toString()+"','500','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
										+ "('"+comboBox.getSelectedItem().toString()+"','200','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
										+ "('"+comboBox.getSelectedItem().toString()+"','100','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
										+ "('"+comboBox.getSelectedItem().toString()+"','50','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
										+ "('"+comboBox.getSelectedItem().toString()+"','20','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
										+ "('"+comboBox.getSelectedItem().toString()+"','10','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
										+ "('"+comboBox.getSelectedItem().toString()+"','5','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
										+ "('"+comboBox.getSelectedItem().toString()+"','1','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
										+ "('"+comboBox.getSelectedItem().toString()+"','0.50','0' ,'0','"+formattedDate+"','"+formattedDate+"'),"
										+ "('"+comboBox.getSelectedItem().toString()+"','0.25','0' ,'0','"+formattedDate+"','"+formattedDate+"') ";
								statement.execute(query123);
								
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
								report_master.opening_reg.report(comboBox.getSelectedItem().toString(), con1, printer,company);
								JOptionPane.showMessageDialog(frame, "This Register Is Opened");
								//new sales_master.posscreen(db_name,company,username,store_name,posreg, version,server,decimal,con1);
								
								if(language.equals("Arabic"))
								{
									new sales_master.PosscreenArabic(db_name,company, username,store_name,posreg, version,server,decimal,con1,language);
								}
								else
								{
									new sales_master.posscreen(db_name,company, username,store_name,posreg, version,server,decimal,con1,language);	
								}
								
								Mainpage.btnSales.setEnabled(false);
								frame.setVisible(false);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
				    
				    
				    
				    
				    
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				
				
			}
		});
		btnOk.setBounds(135, 23, 105, 43);
		panel.add(btnOk);
		
		JButton btnExit = new JButton("EXIT");
		if(language.equals("Arabic"))
		{
			btnExit.setText("„Œ—Ã");
		}
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mainpage.btnSales.setEnabled(true);
				frame.setVisible(false);
				
			}
		});
		btnExit.setBounds(265, 23, 105, 43);
		panel.add(btnExit);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(415, 299, 361, 2);
		panel.add(separator_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 77, 416, 605);
		panel.add(scrollPane);
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
				if(column==1)
		        {
		        	return true;  
		        }
				if(column==3)
		        {
		        	return true;  
		        }
				
		       return false;
		    }
			public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 3:
                        return Double.class;
                    default:
                        return String.class;
                }
            }
			
		};
		table = new JTable(model);
		
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(40);
		    
			if(language.equals("Arabic"))
			{
				model.addColumn("«·—ﬁ„ «· ”·”·Ì:"); 
			    model.addColumn("—„“ «·œ›⁄"); 
				model.addColumn("Ê’›"); 
				model.addColumn("«·„»·€/ «·ﬂ„Ì…"); 
			}
			else
			{
				model.addColumn("Sl No:"); 
			    model.addColumn("Paycode"); 
				model.addColumn("Description"); 
				model.addColumn("Amount/ Qty"); 
			}
		
	
			int a=table.getColumnCount();
			for(int i=0;i<a;i++)
			{
				TableColumn column=table.getColumnModel().getColumn(i);
				if(i==0){
					column.setPreferredWidth(15);
				}
				if(i==3){
					column.setPreferredWidth(25);
				}
				if(i==4){
					column.setPreferredWidth(10);
				}
				if(i==5){
					column.setPreferredWidth(10);
				}
			}
			table.getColumnModel().getColumn(1).setCellRenderer(new ClientsTableButtonRenderer());
			table.getColumnModel().getColumn(1).setCellEditor(new ClientsTableRenderer(new JCheckBox()));
			    
			
		    scrollPane.setViewportView(table);
		    
		    JPanel panel_1 = new JPanel();
		    panel_1.setLayout(null);
		    panel_1.setBackground(Color.GRAY);
		    panel_1.setBounds(477, 312, 196, 370);
		    panel.add(panel_1);
		    
		    JButton button_12 = new JButton("1");
			button_12.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("1");
				}
			});
			button_12.setFont(new Font("Arial", Font.BOLD, 30));
			button_12.setBounds(3, 53, 60, 60);
			panel_1.add(button_12);
			
			JButton button_13 = new JButton("2");
			button_13.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("2");
				}
			});
			button_13.setFont(new Font("Arial", Font.BOLD, 30));
			button_13.setBounds(66, 53, 60, 60);
			panel_1.add(button_13);
			
			JButton button_14 = new JButton("3");
			button_14.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("3");
				}
			});
			button_14.setFont(new Font("Arial", Font.BOLD, 30));
			button_14.setBounds(129, 53, 60, 60);
			panel_1.add(button_14);
			
			JButton button_15 = new JButton("4");
			button_15.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("4");
				}
			});
			button_15.setFont(new Font("Arial", Font.BOLD, 30));
			button_15.setBounds(3, 116, 60, 60);
			panel_1.add(button_15);
			
			JButton button_16 = new JButton("5");
			button_16.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("5");
				}
			});
			button_16.setFont(new Font("Arial", Font.BOLD, 30));
			button_16.setBounds(66, 116, 60, 60);
			panel_1.add(button_16);
			
			JButton button_17 = new JButton("6");
			button_17.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("6");
				}
			});
			button_17.setFont(new Font("Arial", Font.BOLD, 30));
			button_17.setBounds(129, 116, 60, 60);
			panel_1.add(button_17);
			
			JButton button_18 = new JButton("7");
			button_18.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("7");
				}
			});
			button_18.setFont(new Font("Arial", Font.BOLD, 30));
			button_18.setBounds(3, 179, 60, 60);
			panel_1.add(button_18);
			
			JButton button_19 = new JButton("8");
			button_19.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("8");
				}
			});
			button_19.setFont(new Font("Arial", Font.BOLD, 30));
			button_19.setBounds(66, 179, 60, 60);
			panel_1.add(button_19);
			
			JButton button_20 = new JButton("9");
			button_20.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("9");
				}
			});
			button_20.setFont(new Font("Arial", Font.BOLD, 30));
			button_20.setBounds(129, 179, 60, 60);
			panel_1.add(button_20);
			
			JButton button_21 = new JButton("0");
			button_21.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("0");
				}
			});
			button_21.setFont(new Font("Arial", Font.BOLD, 30));
			button_21.setBounds(3, 242, 60, 60);
			panel_1.add(button_21);
			
			JButton button_22 = new JButton("<<");
			button_22.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!textField_12.getText().equals(""))
					{
						textField_12.setText(textField_12.getText().substring(0, textField_12.getText().length() - 1));
					}
				}
			});
			button_22.setFont(new Font("Arial", Font.BOLD, 20));
			button_22.setBounds(66, 305, 60, 60);
			panel_1.add(button_22);
			
			JButton button_23 = new JButton("C");
			button_23.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					textField_12.setText("");
				}
			});
			button_23.setFont(new Font("Arial", Font.BOLD, 30));
			button_23.setBounds(3, 305, 60, 60);
			panel_1.add(button_23);
			
			
			textField_12 = new JTextField("");
			textField_12.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char vChar = e.getKeyChar();
				    if (textField_12.getText().equals(""))
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
			textField_12.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLUE, null, null, null));
			textField_12.setHorizontalAlignment(SwingConstants.RIGHT);
			textField_12.setForeground(Color.DARK_GRAY);
			textField_12.setFont(new Font("Tahoma", Font.PLAIN, 40));
			textField_12.setColumns(10);
			textField_12.setBackground(new Color(176, 224, 230));
			textField_12.setBounds(0, 0, 189, 50);
			panel_1.add(textField_12);
			
			JButton button = new JButton("00");
			button.setFont(new Font("Arial", Font.BOLD, 24));
			button.setBounds(66, 242, 60, 60);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("00");
				}
			});
			panel_1.add(button);
			
			JButton button_1 = new JButton("000");
			button_1.setFont(new Font("Arial", Font.BOLD, 15));
			button_1.setBounds(129, 242, 60, 60);
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn("000");
				}
			});
			panel_1.add(button_1);
			
			JButton button_2 = new JButton(".");
			button_2.setFont(new Font("Arial", Font.BOLD, 30));
			button_2.setBounds(129, 305, 60, 60);
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					click_btn(".");
				}
			});
			panel_1.add(button_2);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(425, 81, 341, 211);
			panel_2.setBackground(new Color(243, 229, 216));
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel label = new JLabel("Remarks :");
			if(language.equals("Arabic"))
			{
				label.setText("„·«ÕŸ«  :");
			}
			label.setBounds(2, 181, 113, 22);
			panel_2.add(label);
			
			JLabel label_1 = new JLabel("Close Rept no. :");
			if(language.equals("Arabic"))
			{
				label_1.setText("≈€·«ﬁ —ﬁ„ «·≈Ì’«· :");
			}
			label_1.setBounds(2, 152, 113, 22);
			panel_2.add(label_1);
			
			JLabel label_2 = new JLabel("Open Rept no. :");
			if(language.equals("Arabic"))
			{
				label_2.setText("—ﬁ„ «·≈Ì’«· «·„› ÊÕ:");
			}
			label_2.setBounds(2, 123, 113, 22);
			panel_2.add(label_2);
			
			JLabel label_3 = new JLabel("Shift :");
			if(language.equals("Arabic"))
			{
				label_3.setText("ÌÕÊ·");
			}
			label_3.setBounds(2, 94, 113, 22);
			panel_2.add(label_3);
			
			JLabel label_4 = new JLabel("User/Cashier :");
			if(language.equals("Arabic"))
			{
				label_4.setText("«·„” Œœ„/√„Ì‰ «·’‰œÊﬁ :");
			}
			label_4.setBounds(2, 65, 113, 22);
			panel_2.add(label_4);
			
			JLabel label_5 = new JLabel("Open Date :");
			if(language.equals("Arabic"))
			{
				label_5.setText(" «—ÌŒ «·› Õ :");
			}
			label_5.setBounds(2, 36, 113, 22);
			panel_2.add(label_5);
			
			JLabel label_6 = new JLabel("Register Number :");
			if(language.equals("Arabic"))
			{
				label_6.setText("—ﬁ„ «· ”ÃÌ· :");
			}
			label_6.setBounds(2, 7, 113, 22);
			panel_2.add(label_6);
			
			comboBox = new JComboBox<String>();
			comboBox.setToolTipText("");
			comboBox.setBounds(130, 8, 201, 22);
			panel_2.add(comboBox);
			
			textField = new JTextField(dateFormat.format(date1));
			textField.setEditable(false);
			textField.setColumns(10);
			textField.setBounds(130, 38, 201, 20);
			panel_2.add(textField);
			
			textField_1 = new JTextField(username);
			textField_1.setEditable(false);
			textField_1.setColumns(10);
			textField_1.setBounds(130, 66, 201, 20);
			panel_2.add(textField_1);
			
			textField_2 = new JTextField();
			textField_2.setEditable(false);
			textField_2.setColumns(10);
			textField_2.setBounds(130, 94, 201, 20);
			panel_2.add(textField_2);
			
			textField_3 = new JTextField();
			textField_3.setEditable(false);
			textField_3.setColumns(10);
			textField_3.setBounds(130, 122, 201, 20);
			panel_2.add(textField_3);
			
			textField_4 = new JTextField();
			textField_4.setEditable(false);
			textField_4.setColumns(10);
			textField_4.setBounds(130, 150, 201, 20);
			panel_2.add(textField_4);
			
			textField_5 = new JTextField();
			textField_5.setColumns(10);
			textField_5.setBounds(130, 178, 201, 20);
			panel_2.add(textField_5);
			
			get_register_name(company,username,store_name,posreg,con1);
			get_full_paycode(company,con1,posreg);
			//int count=model.getRowCount();
			//model.addRow(new Object[]{count+2,"GIFT VOUCHER","GIFT VOUCHER",0});
			//model.addRow(new Object[]{count+3,"LOYALITY","LOYALITY",0});
			//model.addRow(new Object[]{count+2,"CUSTOMERPAYMENTS","CUSTOMERPAYMENTS",0});	
			
			get_recharge_cards(company,con1);
	}
	private void click_btn(String num)
	{
		try{
			String pass_string=textField_12.getText();
			pass_string+=num;
			textField_12.setText(pass_string);
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	public void get_register_name(String company,String username, String store_name,String posreg, Connection con1){
		try {
			Statement statement=con1.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_users where user_name='"+username+"' ");
			if(resultSet.next())
			{
				textField_2.setText(resultSet.getString("shift_code"));
				ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_assign_register where ref_user_code='"+resultSet.getString("user_code")+"' and select_status='true' ");
				if(resultSet1.next())
				{
					ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+posreg+"' ");
					if(resultSet2.next())
					{
						printer=resultSet2.getString("printer1");	
						int recpt=Integer.parseInt(resultSet2.getString("invoice_number"))+Integer.parseInt(resultSet2.getString("next_num"));
						textField_3.setText(Integer.toString(recpt));
						textField_4.setText(Integer.toString(recpt));
					}
				}
			}
			comboBox.addItem(posreg);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	private void get_full_paycode(String company, Connection con1,String posreg)
	{
		try {
			Statement statement=con1.createStatement();
			Statement statement1=con1.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store_paycodes");
			while(resultSet.next())
			{
				String name=resultSet.getString("paycode");
				String pay_desc=resultSet.getString("pay_desc");
				double pay_amt=0;
				if(!name.equals(""))
				{
					if(name.equals("CASH"))
					{
						ResultSet resultSet1 = statement1.executeQuery("select * from "+company+".mr_register_setup where register_number='"+posreg+"' ");
						if(resultSet1.next())
						{
							pay_amt=Double.parseDouble(resultSet1.getString("float_amount"));
						}
					}
					model.addRow(new Object[]{model.getRowCount()+1,name,pay_desc,pay_amt});
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	private void get_recharge_cards(String company, Connection con1)
	{
		try {
			Statement statement=con1.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_item_open_qty");
			while(resultSet.next())
			{
				String name=resultSet.getString("item_name");
				String pay_desc=resultSet.getString("item_name");
				if(!name.equals(""))
				{
					int count=model.getRowCount();
					model.addRow(new Object[]{count+1,name,pay_desc,0});
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	class ClientsTableButtonRenderer extends JButton implements TableCellRenderer
	  {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ClientsTableButtonRenderer()
	    {
	      setOpaque(true);
	    }

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	    {
	      setForeground(Color.black);
	      setBackground(UIManager.getColor("Button.background"));
	      setText((value == null) ? "" : value.toString());
	      return this;
	    }
	  }
	  public class ClientsTableRenderer extends DefaultCellEditor
	  {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JButton button;
	    private String label;
	    private boolean clicked;
	    @SuppressWarnings("unused")
		private int row, col;
	    private JTable table;

	    public ClientsTableRenderer(JCheckBox checkBox)
	    {
	      super(checkBox);
	      button = new JButton();
	      button.setOpaque(true);
	      button.addActionListener(new ActionListener()
	      {
	        public void actionPerformed(ActionEvent e)
	        {
	          fireEditingStopped();
	        }
	      });
	    }
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	    {
	      this.table = table;
	      this.row = row;
	      this.col = column;

	      button.setForeground(Color.black);
	      button.setBackground(UIManager.getColor("Button.background"));
	      label = (value == null) ? "" : value.toString();
	      button.setText(label);
	      clicked = true;
	      return button;
	    }
	    public Object getCellEditorValue()
	    {
	      if (clicked)
	      {
	        String val=textField_12.getText();
	        if(!val.equals(""))
	        {
	        	table.setValueAt(Integer.parseInt(val), row, 3);
	        	textField_12.setText("");
	        }
	        else
	        {
	        	JOptionPane.showMessageDialog(frame, "Please Enter Some Values First");
	        }
	      }
	      clicked = false;
	      return new String(label);
	    }

	    public boolean stopCellEditing()
	    {
	      clicked = false;
	      return super.stopCellEditing();
	    }

	    protected void fireEditingStopped()
	    {
	      super.fireEditingStopped();
	    }
	  }
}
