package accounts_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JProgressBar;

public class JV_header {

	static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	public static JTextField textField;
	public static JMenuItem menuItem;
	public static JComboBox<String> comboBox;
	private JButton btnVoid;
	private JButton btnPost;
	private JButton btnApprove;
	private JButton btnNew;
	private JLabel lblType;
	private JMenuItem menuItem_3;
	private JButton btnChequeList;
	private JProgressBar progressBar;
	private String company="";
    private String user="";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					JV_header window = new JV_header("mr_pro","admin","S01", null,"2","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JV_header(String db_name, String username,String store,String version, String decimal,String language) throws JSONException {
		initialize(db_name,username,store, version,decimal,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 * @param page 
	 */
	private void initialize(final String db_name,final String username,final String store,final String version,final String decimal,final String language) throws JSONException {
		
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 696);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    	//CafePro.Mainpage.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	CafePro.Mainpage.btnPurchaseOrder.setEnabled(true);
		    	//CafePro.Mainpage.btnNewButton_1.setEnabled(true);
		    }
		});
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		//frame.setAlwaysOnTop(true);
		
		company=db_name;
		user=username;
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		final Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		JLabel lblItemMasterList = new JLabel("Journal Voucher List");
		if(language.equals("Arabic"))
		{
			lblItemMasterList.setText("ﬁ«∆„… ﬁ”«∆„ «·„Ã·…");
		}
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(7, 11, 171, 44);
		panel.add(lblItemMasterList);
		
		menuItem = new JMenuItem("Exit");
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(772, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		if(language.equals("Arabic"))
		{
			menuItem_1.setText("Ì‰⁄‘");
		}
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				get_jv_disp( company,comboBox.getSelectedItem().toString(), textField.getText());
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(839, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		btnApprove = new JButton("Approve");
		if(language.equals("Arabic"))
		{
			btnApprove.setText("Ì⁄ „œ");
		}
		btnApprove.setEnabled(false);
		btnApprove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(frame, "Select a Row to Continue");
				}
				else
				{
					int a= JOptionPane.showConfirmDialog(frame, "Do you want to approve",null,JOptionPane.YES_NO_OPTION);
					if(a==0)
					{
						try 
						{
							if(table.getValueAt(row, 4).equals("0"))
							{
								LocalDateTime myDateObj = LocalDateTime.now();  
							    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
							    String formattedDate = myDateObj.format(myFormatObj);
								Connection con=Dbsetup.dbconn.getConnection();
								Statement statement=con.createStatement();  
								ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_jv_header where jv_num='"+model.getValueAt(row, 0).toString()+"' ");
								if(resultSet.next())
								{
									ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_jv_details where ref_jv_num='"+model.getValueAt(row, 0).toString()+"' and status!='VOID' ");
									if(resultSet1.next())
									{
										String save_po="update "+company+".mr_jv_header set is_approved='1',approved_by='"+username+"',approved_date='"+formattedDate+"',modified_date='"+formattedDate+"',status='Approved' where jv_num='"+model.getValueAt(row, 0).toString()+"' ";
										statement.executeUpdate(save_po);
										JOptionPane.showMessageDialog(frame, "Approved  Successfully");
									}
									else
									{
										JOptionPane.showMessageDialog(frame, "There is no entries in this voucher");
									}
									
								}
								
								con.close();
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "This voucher is Mis-balanced, Try again later");
							}
							
						} catch (Exception e) {
							System.err.println(e);
						}
					}
					get_jv_disp( company,comboBox.getSelectedItem().toString(), textField.getText());
				}
			}
		});
		btnApprove.setBounds(497, 22, 97, 25);
		panel.add(btnApprove);
		
		btnPost = new JButton("Post");
		if(language.equals("Arabic"))
		{
			btnPost.setText("»—Ìœ");
		}
		btnPost.setEnabled(false);
		btnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 sync();
			}
		});
		btnPost.setBounds(601, 22, 97, 25);
		panel.add(btnPost);
		
		btnVoid = new JButton("Void");
		if(language.equals("Arabic"))
		{
			btnVoid.setText("Ì·€Ì");
		}
		btnVoid.setEnabled(false);
		btnVoid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(frame, "Select a Row to Continue");
				}
				else
				{
					int a= JOptionPane.showConfirmDialog(frame, "Do you want to Void this voucher",null,JOptionPane.YES_NO_OPTION);
					if(a==0)
					{
						try {
							LocalDateTime myDateObj = LocalDateTime.now();  
						    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
						    String formattedDate = myDateObj.format(myFormatObj);
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement();  
							ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_jv_header where jv_num='"+model.getValueAt(row, 0).toString()+"' ");
							if(resultSet.next())
							{
								String save_po="update "+company+".mr_jv_header set modified_date='"+formattedDate+"',status='Void' where jv_num='"+model.getValueAt(row, 0).toString()+"' ";
								statement.executeUpdate(save_po);
								JOptionPane.showMessageDialog(frame, "Voided Successfully");
								
							}
							
							con.close();
						} catch (Exception e) {
							System.err.println(e);
						}
					}
					get_jv_disp( company,comboBox.getSelectedItem().toString(), textField.getText());
				}
			}
		});
		btnVoid.setBounds(313, 22, 85, 25);
		panel.add(btnVoid);
		
		btnNew = new JButton("New");
		if(language.equals("Arabic"))
		{
			btnNew.setText("ÃœÌœ");
		}
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new JV_details(company, version, store, username, "NEW",decimal,"UnApproved",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNew.setBounds(405, 22, 85, 25);
		panel.add(btnNew);
		menuItem_3 = new JMenuItem("Print");
		if(language.equals("Arabic"))
		{
			menuItem_3.setText("„ÿ»⁄…");
		}
		menuItem_3.setBounds(705, 7, 60, 48);
		panel.add(menuItem_3);
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					frame.setAlwaysOnTop(false);
					report_master.jv_list.report(comboBox.getSelectedItem().toString().trim(),con,company);
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
		
		btnChequeList = new JButton("Cheque List");
		if(language.equals("Arabic"))
		{
			btnChequeList.setText("ﬁ«∆„… «· Õﬁﬁ");
		}
		btnChequeList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new accounts_master.cheque_list (company,version,username,store, language);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnChequeList.setBounds(185, 22, 121, 25);
		panel.add(btnChequeList);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 589);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 88, 908, 488);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel_1.add(scrollPane);

		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
				
		       return false;
		    }
		};
		table = new JTable(model);
	    table.setFont(new Font("Serif", Font.BOLD, 15));
	    table.setRowHeight(20);
		    
	    if(language.equals("Arabic"))
		{
	    	 model.addColumn("—ﬁ„ ﬁ”Ì„… «·ÌÊ„Ì…");
	 	    model.addColumn("›—⁄"); 
	 	    model.addColumn(" «—ÌŒ");
	 	    model.addColumn("—ﬁ„ «·ÊÀÌﬁ…");
	 	    model.addColumn("„»·€ ﬁ”Ì„… «·ÌÊ„Ì…"); 
	 	     
	 	    model.addColumn("„·«ÕŸ…"); 
	 	    model.addColumn("Õ«·…");
		}
	    else
	    {
	    	 model.addColumn("JV Number");
	 	    model.addColumn("Branch"); 
	 	    model.addColumn("Date");
	 	    model.addColumn("Doc Number");
	 	    model.addColumn("JV Amount"); 
	 	     
	 	    model.addColumn("Remark"); 
	 	    model.addColumn("Status");
	    }
	   
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(150);
			  if (i==1) column.setPreferredWidth(120);
			  if (i==2) column.setPreferredWidth(250);
			  if (i==3) column.setPreferredWidth(100);
			  if (i==4) column.setPreferredWidth(140);
			  if (i==5) column.setPreferredWidth(400);
			  if (i==6) column.setPreferredWidth(100);
			}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 908, 71);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
	
		
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
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
				  get_jv_disp( company, comboBox.getSelectedItem().toString(), textField.getText());
			  }
			});
		textField.setBounds(12, 13, 493, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		if(language.equals("Arabic"))
		{
			btnSearch.setText("Ì»ÕÀ");
		}
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 get_jv_disp( company, comboBox.getSelectedItem().toString(), textField.getText());
			}
		});
		btnSearch.setBounds(518, 13, 97, 25);
		panel_2.add(btnSearch);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(741, 14, 155, 22);
		comboBox.addItem("All");
		comboBox.addItem("Approved");
		comboBox.addItem("Unapproved");
		comboBox.addItem("Posted");
		comboBox.addItem("Unposted");
		comboBox.addItem("Void");
		comboBox.setSelectedItem("Unapproved");
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(comboBox.getSelectedItem().toString().equals("Unapproved"))
				{
					btnApprove.setEnabled(true);
					btnVoid.setEnabled(true);
					btnPost.setEnabled(false);
				}
				else if(comboBox.getSelectedItem().toString().equals("Approved"))
				{
					btnPost.setEnabled(true);
					btnVoid.setEnabled(true);
					btnApprove.setEnabled(false);
				}
				else if(comboBox.getSelectedItem().toString().equals("Unposted"))
				{
					btnPost.setEnabled(true);
					btnVoid.setEnabled(true);
					btnApprove.setEnabled(false);
				}
				else
				{
					btnPost.setEnabled(false);
					btnVoid.setEnabled(false);
					btnApprove.setEnabled(false);
				}
				get_jv_disp( company, comboBox.getSelectedItem().toString(), textField.getText());
			}
		});
		
		panel_2.add(comboBox);
		
		lblType = new JLabel("Type :");
		if(language.equals("Arabic"))
		{
			lblType.setText("Ìﬂ » :");
		}
		lblType.setBounds(683, 17, 46, 16);
		panel_2.add(lblType);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 0, 205));
		progressBar.setBackground(new Color(192, 192, 192));
		progressBar.setStringPainted(true);
		//progressBar.setBounds(12, 198, 622, 31);
		//frame.getContentPane().add(progressBar);
		progressBar.setBounds(0, 40, 908, 31);
		progressBar.setVisible(false);
		panel_2.add(progressBar);
		 
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 0;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	try {
						new JV_details(company, version, store, username,value,decimal,table.getModel().getValueAt(rows, 6).toString(),language);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                }
            }
        });
        if(comboBox.getSelectedItem().toString().equals("Unapproved"))
		{
			btnApprove.setEnabled(true);
			btnVoid.setEnabled(true);
			btnPost.setEnabled(false);
		}
        get_jv_disp( company,comboBox.getSelectedItem().toString(), textField.getText());
	}
	
	private void sync()
	{
		int row=table.getSelectedRow();
		if(row==-1)
		{
			JOptionPane.showMessageDialog(frame, "Select a Row to Continue");
		}
		else
		{
			int a= JOptionPane.showConfirmDialog(frame, "Do you want to Post",null,JOptionPane.YES_NO_OPTION);
			if(a==0)
			{
				try {
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement();  
					Statement statement1=con.createStatement(); 
					Statement statement2=con.createStatement(); 
					Statement statement3=con.createStatement(); 
					Statement statement4=con.createStatement(); 
					
					 progressBar.setVisible(true);
					 btnPost.setEnabled(false);
					 btnApprove.setEnabled(false);
					 btnChequeList.setEnabled(false);
					 btnNew.setEnabled(false);
					 btnVoid.setEnabled(false);
					
					ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_jv_header where jv_num='"+model.getValueAt(row, 0).toString()+"' ");
					if(resultSet.next())
					{
						String cheque_list="select * from "+company+".mr_cheque_details where tran_num='"+resultSet.getString("jv_num")+"' and status='OPEN' " ;
						ResultSet resultSet22 = statement2.executeQuery(cheque_list);
						if(!resultSet22.next())
						{
							double number_progress=0;
							String qury="select count(*) as ttl from "+company+".mr_jv_details where ref_jv_num='"+resultSet.getString("jv_num")+"' and status!='VOID'";
							ResultSet resultSet121 = statement4.executeQuery(qury);
							if(resultSet121.next())
							{
								double count=Double.parseDouble(resultSet121.getString("ttl"));
								number_progress=100/count;
							}
							int b=1;
							ResultSet resultSet1 = statement1.executeQuery("select * from "+company+".mr_jv_details where ref_jv_num='"+resultSet.getString("jv_num")+"' and status!='VOID'");
							while(resultSet1.next())
							{
								float baldr=0;
								float balcr=0;
								ResultSet resultSet2 = statement2.executeQuery("select * from "+company+".mr_acc_ledger where ac_code='"+resultSet1.getString("ac_code")+"' ");
								while(resultSet2.next())
								{
									baldr+=Float.parseFloat(resultSet2.getString("dr_amt"));
									balcr+=Float.parseFloat(resultSet2.getString("cr_amt"));
								}
								float balance=baldr-balcr;
								if(!resultSet1.getString("dr_amt").equals("0"))
								{
									balance=balance+Float.parseFloat(resultSet1.getString("dr_amt"));
								}
								if(!resultSet1.getString("cr_amt").equals("0"))
								{
									balance=balance-Float.parseFloat(resultSet1.getString("cr_amt"));
								}
								float bal=balance;
								String save_po="insert into "+company+".mr_acc_ledger (`jv_num`, `tran_date`, `post_date`, `ac_code`, `dr_amt`, `cr_amt`, `balance`, `notes`, `remark`, `source`, `created_date`, `modified_date`,status) "
										+ "values ('"+resultSet1.getString("ref_jv_num")+"','"+resultSet1.getString("cheque_date")+"','"+formattedDate+"','"+resultSet1.getString("ac_code")+"','"+resultSet1.getString("dr_amt")+"','"+resultSet1.getString("cr_amt")+"','"+bal+"','"+resultSet1.getString("note")+"','"+resultSet1.getString("remark")+"','JV','"+formattedDate+"','"+formattedDate+"','OPEN' )";
								statement3.executeUpdate(save_po);
								double ttl_count=b*number_progress;
								Thread.sleep(50);
			                	 progressBar.paintImmediately(0, 0, 854, 31);
								progressBar.setValue((int)ttl_count);
								b++;
							}
							String save="update "+company+".mr_jv_header set status='Posted',is_posted='1',posted_by='"+user+"',posted_date='"+formattedDate+"' where jv_num='"+model.getValueAt(row, 0).toString()+"' ";
							statement3.executeUpdate(save);
							JOptionPane.showMessageDialog(frame, "Posted Successfully");
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "First, you need to post the cheque and try again");
						}
						
					}
					progressBar.setVisible(false);
					btnPost.setEnabled(true);
					btnApprove.setEnabled(false);
					btnChequeList.setEnabled(true);
					btnNew.setEnabled(true);
					btnVoid.setEnabled(true);
					con.close();
				} catch (Exception e) {
					System.err.println(e);
				}
			}
			get_jv_disp( company,comboBox.getSelectedItem().toString(), textField.getText());
		}
	}
	
	public static void  get_jv_disp( String company, String type, String search)
	{
		String serch_str = "";
		
		String sort="";
		if(!type.equals("All"))
		{
			sort="where status='"+type+"'";
			if(!search.equals(""))
			{
				serch_str="and jv_num LIKE '%"+search+"%' OR doc_num LIKE '%"+search+"%' OR jv_amt LIKE '%"+search+"%' OR remark LIKE '%"+search+"%' ";
			}
		}
		else
		{
			if(!search.equals(""))
			{
				serch_str="where jv_num LIKE '%"+search+"%' OR doc_num LIKE '%"+search+"%' OR jv_amt LIKE '%"+search+"%' OR remark LIKE '%"+search+"%' ";
			}
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_jv_header  "+sort+" "+serch_str+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+company+".mr_jv_header "+sort+" "+serch_str+" "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String jv_num=resultSet1.getString("jv_num");
	                String branch=resultSet1.getString("branch");
	                String date=resultSet1.getString("date");
	                String doc_num=resultSet1.getString("doc_num");
	                String jv_amt=resultSet1.getString("jv_amt");
	                String status=resultSet1.getString("status");
	                //String vendor_cust_id=resultSet1.getString("vendor_cust_id");
	                String remark=resultSet1.getString("remark");
	               // Statement statement1=con.createStatement();  
//	    			String query11="SELECT * FROM "+company+".mr_vendor where vendor_code='"+vendor_cust_id+"'"; 
//	    			ResultSet resultSet2 = statement1.executeQuery(query11);
//	    			String name="";
//	    			if(resultSet2.next())
//	                {
//	                	name=resultSet2.getString("vendor_name");
//	                }
	                model.addRow(new Object[]{jv_num,branch,date,doc_num,jv_amt,remark,status});
	                
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Jouranl Voucher List Found");
	        	for (int i = model.getRowCount() - 1; i >= 0; i--) {
	        	    model.removeRow(i);
	        	}
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
