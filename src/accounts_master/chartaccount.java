package accounts_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import javax.swing.JTextField;



import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class chartaccount {

	private JFrame frame;
	private JTextField txtDubai;
	private JTable table;
	private DefaultTableModel model;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	private JTextField hiddentxt;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chartaccount window = new chartaccount("mr_pro", null,"");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws JSONException 
	 */
	public chartaccount(String company,String version, String language) throws JSONException {
		initialize(company,version,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 */
	private void initialize(final String company,final String version, String language) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();

		JPanel panel = new JPanel();
	    panel.setLayout(null);
	    panel.setBackground(new Color(243, 229, 216));
	    panel.setBounds(0, 0, 694, 58);
	    frame.getContentPane().add(panel);
	    
	    JLabel lblAccountingGroups = new JLabel("Chart of Accounts");
	    if(language.equals("Arabic"))
		{
	    	lblAccountingGroups.setText("„Œÿÿ «·Õ”«»« ");
		}
	    lblAccountingGroups.setFont(new Font("Tahoma", Font.BOLD, 15));
	    lblAccountingGroups.setBounds(21, 11, 228, 44);
	    panel.add(lblAccountingGroups);
	    
		JMenuItem menuItem = new JMenuItem("Exit");
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
	    menuItem.setIcon(new ImageIcon(exit));
	    menuItem.setBackground(new Color(243, 229, 216));
	    menuItem.setBounds(482, 0, 60, 48);
	    panel.add(menuItem);
	    
	    JMenuItem menuItem_1 = new JMenuItem("Refresh");
	    if(language.equals("Arabic"))
		{
	    	menuItem_1.setText("Ì‰⁄‘");
		}
	    menuItem_1 = new JMenuItem("Refresh",KeyEvent.VK_F5);
	    menuItem_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		txtDubai.setText("");
	    		hiddentxt.setText("");
	    		get_chrt_acc_list(company);
	    	}
	    });
	    menuItem_1.setBorder(null);
	    menuItem_1.setIcon(new ImageIcon(refresh));
	    menuItem_1.setBackground(new Color(243, 229, 216));
	    menuItem_1.setBounds(542, 0, 80, 48);
	    panel.add(menuItem_1);
	    
	    JMenuItem menuItem_2 = new JMenuItem("Save");
	    if(language.equals("Arabic"))
		{
	    	menuItem_2.setText("ÌÕ›Ÿ");
		}
	    menuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
	    menuItem_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		String acc_grp = (String) comboBox.getSelectedItem();
	    		String[] selected_item=acc_grp.split("-");
	    		String acc_name = txtDubai.getText().toUpperCase();
	    		String acc_type=lblNewLabel.getText();
	    		String old=hiddentxt.getText();
	    		if(acc_name.equals(""))
	    		{
	    			JOptionPane.showMessageDialog(frame, "Please Enter The Account Desc «·—Ã«¡ ≈œŒ«· Ê’› «·Õ”«»");
	    			txtDubai.requestFocus();
	    		}
	    		else
	    		{
	    			
	    			LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					try {
						statement = con.createStatement();
						ResultSet resultSet5 = statement.executeQuery("select * from "+company+".mr_chart_accnt_mstr where acc_name='"+acc_name+"' and acc_type='"+acc_type+"' ");
						if(resultSet5.next())
						{
							JOptionPane.showMessageDialog(frame, "Already Exist „ÊÃÊœ »«·›⁄·");
						}
						else
						{
							ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_chart_accnt_mstr where acc_code='"+old+"' ");
							if(resultSet.next())
							{
								String query="update "+company+".mr_chart_accnt_mstr set acc_name='"+acc_name+"',acc_type='"+acc_type+"',acc_grp_name='"+selected_item[0]+"',acc_store='"+comboBox_1.getSelectedItem().toString()+"',acc_category='"+lblNewLabel_1.getText()+"' where acc_code='"+old+"' ";
								statement.executeUpdate(query);
								JOptionPane.showMessageDialog(frame, "Saved Successfully  „ «·Õ›Ÿ »‰Ã«Õ");
								get_chrt_acc_list(company);
							}
							else
							{
								String code = null;
								String trans_seq ="select * from "+company+".mr_chart_accnt_mstr where acc_grp_name='"+selected_item[0]+"' and acc_type='"+acc_type+"' ORDER BY acc_code DESC";
								ResultSet resultSet1 = statement.executeQuery(trans_seq);
								if (resultSet1.next())
							    {
									String tran_seq=resultSet1.getString("acc_code");
							        int new_seq=Integer.parseInt(tran_seq)+1;
							        String code1 = Integer.toString(new_seq);
							        StringBuilder sb = new StringBuilder();
									for (int toPrepend=10-code1.length(); toPrepend>0; toPrepend--) {
									    sb.append('0');
									}
									code=sb.toString()+""+code1;
							    }
							    else
							    {
							        String get_firstseq ="select * from "+company+".mr_account_group_master where acc_grp_name='"+selected_item[0]+"' and acc_grp_type='"+acc_type+"' ";
							        ResultSet resultSet2 = statement.executeQuery(get_firstseq);
							        if(resultSet2.next())
							        {
							        	String seq=resultSet2.getString("acc_grp_code");
							        	StringBuilder sb = new StringBuilder();
										for (int toPrepend=7-seq.length(); toPrepend>0; toPrepend--) {
										    sb.append('0');
										}
										String code1 = seq;
										
							        	code=code1+"001";
							        }
							    }
								String query="insert into "+company+".mr_chart_accnt_mstr (acc_code,acc_name,acc_type,acc_grp_name,acc_store,created_date,acc_branch,acc_category) values('"+code+"','"+acc_name+"','"+acc_type+"','"+selected_item[0]+"','"+comboBox_1.getSelectedItem().toString()+"','"+formattedDate+"','"+comboBox_2.getSelectedItem().toString()+"','"+lblNewLabel_1.getText()+"') ";
								statement.executeUpdate(query);
								JOptionPane.showMessageDialog(frame, "Saved Successfully  „ «·Õ›Ÿ »‰Ã«Õ");
								get_chrt_acc_list(company);
							}
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
	    		}
	    	}
	    });
	    menuItem_2.setBorder(null);
	    menuItem_2.setIcon(new ImageIcon(save));
	    menuItem_2.setBackground(new Color(243, 229, 216));
	    menuItem_2.setBounds(624, 0, 70, 48);
	    panel.add(menuItem_2);
		
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		JLabel lblCompanyName = new JLabel("Account Group :");
		if(language.equals("Arabic"))
		{
			lblCompanyName.setText("„Ã„Ê⁄… «·Õ”«» :");
		}
		lblCompanyName.setBounds(30, 105, 102, 21);
		lblCompanyName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblCompanyName);
		
		JLabel lblAddress = new JLabel("Account Desc :");
		if(language.equals("Arabic"))
		{
			lblAddress.setText("Ê’› «·Õ”«» :");
		}
		lblAddress.setBounds(31, 172, 102, 21);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblAddress);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 59, 694, 2);
		frame.getContentPane().add(separator);
		
		txtDubai = new JTextField();
		txtDubai.setBounds(135, 173, 479, 20);
		frame.getContentPane().add(txtDubai);
		txtDubai.setColumns(10);
		
		
		comboBox_1= new JComboBox<String>();
		get_store_id(company);
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				try {
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement();  
					ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_account_group_master where store_id='"+comboBox_1.getSelectedItem().toString()+"' ");
					comboBox.removeAllItems();
					while(resultSet.next())
					{
						String name=resultSet.getString("acc_grp_name");
						String code=resultSet.getString("acc_grp_code");
			            comboBox.addItem(name+"-"+code);
					}
					if(!txtDubai.getText().equals(""))
					{
						txtDubai.setText("");
					}
					String qu="select * from "+company+".mr_acc_segment where segment='Branch' and store_id='"+comboBox_1.getSelectedItem().toString()+"' ";
					ResultSet resultSet1 = statement.executeQuery(qu);
					comboBox_2.removeAllItems();
					
					while(resultSet1.next())
					{
						 String name=resultSet1.getString("name");
						 comboBox_2.addItem(name);
					}
					get_acc_type(company,comboBox.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString());
					con.close();
				} catch (Exception e) {
					System.err.println(e);
				}
				
			}
		});
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setBounds(135, 73, 124, 20);
		frame.getContentPane().add(comboBox_1);
		
	    
	
		comboBox = new JComboBox<String>();
		comboBox.setBounds(135, 106, 221, 20);
		comboBox.setSelectedIndex(-1);
        comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_acc_type(company,comboBox.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString());
			}
		});
		frame.getContentPane().add(comboBox);
		
		hiddentxt = new JTextField();
		hiddentxt.setBounds(568, 69, 116, 22);
		frame.getContentPane().add(hiddentxt);
		
		hiddentxt.setVisible(false);
		hiddentxt.setEditable(false);
		hiddentxt.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 206, 694, 2);
		frame.getContentPane().add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 221, 684, 333);
		frame.getContentPane().add(scrollPane);
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		   
		};
		table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 14));
			table.setRowHeight(20);
			if(language.equals("Arabic"))
			{
				model.addColumn("‘›—…"); 
			    model.addColumn("≈”„ «·Õ”«»"); 
			    model.addColumn("„Ã„Ê⁄… «·Õ”«»"); 
				model.addColumn("‰Ê⁄ «·Õ”«»"); 
				model.addColumn("„⁄—› «·„ Ã—"); 
			}
			else
			{
				model.addColumn("Code"); 
			    model.addColumn("Acccount Name"); 
			    model.addColumn("Acccount Group"); 
				model.addColumn("Account Type"); 
				model.addColumn("Store ID"); 
			}
		    
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			int columns=model.getColumnCount();
			for (int i=0; i<columns; i++) {
				  TableColumn column = table.getColumnModel().getColumn(i);
				  if (i==0) column.setPreferredWidth(90);
				  if (i==1) column.setPreferredWidth(225);
				  if (i==2) column.setPreferredWidth(205);
				  if (i==3) column.setPreferredWidth(80);
				  if (i==4) column.setPreferredWidth(80);
				}
			scrollPane.setViewportView(table);
			
			JLabel lblAccountType = new JLabel("Account Type :");
			if(language.equals("Arabic"))
			{
				lblAccountType.setText("‰Ê⁄ «·Õ”«» :");
			}
			lblAccountType.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblAccountType.setBounds(31, 140, 102, 21);
			frame.getContentPane().add(lblAccountType);
			
			lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(135, 140, 221, 21);
			frame.getContentPane().add(lblNewLabel);
			
			JLabel lblStoreNo = new JLabel("Store No. :");
			if(language.equals("Arabic"))
			{
				lblStoreNo.setText("—ﬁ„ «·„ Ã— :");
			}
			lblStoreNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblStoreNo.setBounds(31, 71, 66, 21);
			frame.getContentPane().add(lblStoreNo);
			
			JLabel lblBranch = new JLabel("Branch : ");
			if(language.equals("Arabic"))
			{
				lblBranch.setText("›—⁄ :");
			}
			lblBranch.setBounds(387, 107, 56, 16);
			frame.getContentPane().add(lblBranch);
			
			comboBox_2 = new JComboBox<String>();
			comboBox_2.setBounds(449, 104, 160, 22);
			frame.getContentPane().add(comboBox_2);
			
			JLabel lblAccountCategory = new JLabel("Account Category : ");
			if(language.equals("Arabic"))
			{
				lblAccountCategory.setText("›∆… «·Õ”«» :");
			}
			lblAccountCategory.setBounds(387, 140, 116, 21);
			frame.getContentPane().add(lblAccountCategory);
			
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setBounds(499, 140, 185, 21);
			frame.getContentPane().add(lblNewLabel_1);
			
			get_chrt_acc_list(company);
			
			table.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent mouseEvent) {
					JTable table = (JTable) mouseEvent.getSource();
					if (mouseEvent.getClickCount() == 2&& table.getSelectedRow() != -1) {
						int column = 1;
						int rows = table.getSelectedRow();
						String value = table.getModel().getValueAt(rows, column).toString();
						
						lblNewLabel.setText(table.getModel().getValueAt(rows, 3).toString());
						comboBox.setSelectedItem(table.getModel().getValueAt(rows, 2).toString());
						comboBox_1.setSelectedItem(table.getModel().getValueAt(rows, 4).toString());
						hiddentxt.setText( table.getModel().getValueAt(rows, 0).toString());
						txtDubai.setText(value);
					}
				}
			});  
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 593);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	private void get_chrt_acc_list(String company)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_chart_accnt_mstr ");
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			while(resultSet.next())
			{
				String acc_grp_code=resultSet.getString("acc_code");
	            String acc_nme=resultSet.getString("acc_name");
	            String acc_grp_nme=resultSet.getString("acc_grp_name");
	            String acc_typ=resultSet.getString("acc_type");
	            String acc_store=resultSet.getString("acc_store");
				model.addRow(new Object[] {  acc_grp_code,acc_nme, acc_grp_nme, acc_typ,acc_store });
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	private void  get_acc_type(String company ,String sel_val, String store)
	{
		if(!sel_val.equals(""))
		{
			try {
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement(); 
				String[] selected_item=sel_val.split("-");
				ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_account_group_master where acc_grp_name='"+selected_item[0]+"'and acc_grp_code='"+selected_item[1]+"' ");
				while(resultSet.next())
				{
					lblNewLabel.setText(resultSet.getString("acc_grp_type"));
		        	txtDubai.setText(selected_item[0]+" AT "+store);
		        	lblNewLabel_1.setText(resultSet.getString("acc_grp_cat"));
				}
				
				con.close();
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		
	}
	private void  get_store_id(String company)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store");
			while(resultSet.next())
			{
				comboBox_1.addItem(resultSet.getString("store_id"));
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
