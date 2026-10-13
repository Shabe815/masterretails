package accounts_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import javax.swing.JTextField;



import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

import org.json.JSONException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class accounts {

	private JFrame frame;
	private JTextField txtDubai;
	private JTable table;
	private DefaultTableModel model;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	private JTextField hiddentxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accounts window = new accounts("mr_pro",  null,"");
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
	public accounts(String company,String version, String language) throws JSONException {
		initialize(company,version,language);
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @param language 
	 * @throws JSONException 
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
	    
	    JLabel lblAccountingGroups = new JLabel("Accounting Groups");
	    if(language.equals("Arabic"))
		{
	    	lblAccountingGroups.setText("«·„Ã„Ê⁄«  «·„Õ«”»Ì…");
		}
	    lblAccountingGroups.setFont(new Font("Tahoma", Font.BOLD, 15));
	    lblAccountingGroups.setBounds(21, 11, 228, 44);
	    panel.add(lblAccountingGroups);
	    
		JMenuItem menuItem = new JMenuItem("Exit");
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
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
	    menuItem_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		txtDubai.setText("");
	    		get_acct_grp(company,"");
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
	    		String acc_type = (String) comboBox.getSelectedItem();
	    		String acc_grp = txtDubai.getText().toUpperCase();
	    		String old=hiddentxt.getText();
	    		if(acc_grp.equals(""))
	    		{
	    			JOptionPane.showMessageDialog(frame, "Please Enter The Account Group «·—Ã«¡ ≈œŒ«· „Ã„Ê⁄… «·Õ”«»");
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
						if(old.equals(""))
						{
							String code = null;
							ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_account_group_master where acc_grp_code = '"+old+"' and acc_grp_name ='"+acc_grp+"' and acc_grp_type='"+acc_type+"'  ");
							if(!resultSet.next()) 
							{
								String trans_seq ="select * from "+company+".mr_account_group_master where acc_grp_type='"+acc_type+"' ORDER BY acc_grp_code DESC";
								ResultSet resultSet1 = statement.executeQuery(trans_seq);
								if (resultSet1.next())
							    {
									String tran_seq=resultSet1.getString("acc_grp_code");
									String seq1=tran_seq.substring(4);
									
									String get_firstseq ="select * from "+company+".mr_account_type where acc_type_name='"+acc_type+"'";
							        ResultSet resultSet2 = statement.executeQuery(get_firstseq);
							        if(resultSet2.next())
							        {
							        	String seq=resultSet2.getString("acc_type_code");
							        	StringBuilder sb = new StringBuilder();
										for (int toPrepend=2-seq.length(); toPrepend>0; toPrepend--) {
										    sb.append('0');
										}
										String code1 = sb.toString()+seq;
										seq1=Integer.toString(Integer.parseInt(seq1)+1);
										StringBuilder sb1 = new StringBuilder();
										for (int toPrepend=3-seq1.length(); toPrepend>0; toPrepend--) {
										    sb1.append('0');
										}
										String code2 = sb1.toString()+seq1;
										String store=comboBox_2.getSelectedItem().toString();
										store=store.substring(1);
							        	code=code1+store+code2;
							        }
							    }
							    else
							    {
							        String get_firstseq ="select * from "+company+".mr_account_type where acc_type_name='"+acc_type+"'";
							        ResultSet resultSet2 = statement.executeQuery(get_firstseq);
							        if(resultSet2.next())
							        {
							        	String seq=resultSet2.getString("acc_type_code");
							        	StringBuilder sb = new StringBuilder();
										for (int toPrepend=2-seq.length(); toPrepend>0; toPrepend--) {
										    sb.append('0');
										}
										String code1 = sb.toString()+seq;
										String store=comboBox_2.getSelectedItem().toString();
										store=store.substring(1);//.replace("S", "");
							        	code=code1+store+"001";
							        }
							    }
								String query="insert into "+company+".mr_account_group_master (acc_grp_code,acc_grp_name,acc_grp_type,created_date,acc_grp_cat,store_id) values('"+code+"','"+acc_grp+"','"+acc_type+"','"+formattedDate+"','"+comboBox_1.getSelectedItem().toString()+"','"+comboBox_2.getSelectedItem().toString()+"') ";
								statement.executeUpdate(query);
								JOptionPane.showMessageDialog(frame, "Saved Successfully  „ «·Õ›Ÿ »‰Ã«Õ");
								txtDubai.setText("");
								String query2="where acc_grp_cat='"+comboBox_1.getSelectedItem().toString()+"' and acc_grp_type='"+comboBox.getSelectedItem().toString()+"'";
								get_acct_grp(company,query2);
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Account Group Already assigned for another Account „Ã„Ê⁄… «·Õ”«»«   „  ⁄ÌÌ‰Â« »«·›⁄· ·Õ”«» ¬Œ—");
							}
						}
						else
						{
							String query12="select * from "+company+".mr_account_group_master where acc_grp_code = '"+old+"' and acc_grp_name ='"+acc_grp+"' and acc_grp_type='"+acc_type+"'  ";
							ResultSet resultSet = statement.executeQuery(query12);
							if(resultSet.next()) 
							{
								String query="update "+company+".mr_account_group_master set acc_grp_name='"+acc_grp+"',acc_grp_type='"+acc_type+"',acc_grp_cat='"+comboBox_1.getSelectedItem().toString()+"',store_id='"+comboBox_2.getSelectedItem().toString()+"' where acc_grp_code = '"+old+"' ";
								statement.executeUpdate(query);
								JOptionPane.showMessageDialog(frame, "Saved Successfully  „ «·Õ›Ÿ »‰Ã«Õ");
								txtDubai.setText("");
								String query2="where acc_grp_cat='"+comboBox_1.getSelectedItem().toString()+"' and acc_grp_type='"+comboBox.getSelectedItem().toString()+"'";
								get_acct_grp(company,query2);
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Account Group Already assigned for another Accounts „Ã„Ê⁄… «·Õ”«»«   „  ⁄ÌÌ‰Â« »«·›⁄· ·Õ”«» ¬Œ—");
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
	    menuItem_2.setBounds(624, 0, 115, 48);
	    panel.add(menuItem_2);
		
		
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		JLabel lblCompanyName = new JLabel("Type of Account :");
		if(language.equals("Arabic"))
		{
			lblCompanyName.setText("‰Ê⁄ «·Õ”«» :");
		}
		lblCompanyName.setBounds(35, 84, 102, 21);
		lblCompanyName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblCompanyName);
		
		JLabel lblAddress = new JLabel("Account Group:");
		if(language.equals("Arabic"))
		{
			lblAddress.setText("„Ã„Ê⁄… «·Õ”«»:");
		}
		lblAddress.setBounds(35, 177, 102, 21);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblAddress);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 59, 694, 2);
		frame.getContentPane().add(separator);
		
		txtDubai = new JTextField();
		txtDubai.setBounds(149, 177, 479, 20);
		frame.getContentPane().add(txtDubai);
		txtDubai.setColumns(10);
	
		comboBox = new JComboBox<String>();
		
		comboBox.setBounds(151, 84, 221, 20);
		
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_account_type ");
			while(resultSet.next())
			{
				String name=resultSet.getString("acc_type_name");
	            comboBox.addItem(name);
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		comboBox.setSelectedIndex(-1);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				get_acct_cat(company,comboBox.getSelectedItem().toString());
			}
		});
		frame.getContentPane().add(comboBox);
		
		
		hiddentxt = new JTextField();
		hiddentxt.setEditable(false);
		hiddentxt.setBounds(568, 69, 116, 22);
		frame.getContentPane().add(hiddentxt);
		
		hiddentxt.setVisible(false);
		hiddentxt.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 222, 694, 2);
		frame.getContentPane().add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 237, 674, 229);
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
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(20);
			if(language.equals("Arabic"))
			{
				model.addColumn("‘›—…"); 
			    model.addColumn("≈”„ «·Õ”«»"); 
				model.addColumn("‰Ê⁄ «·Õ”«»"); 
				model.addColumn("›∆… «·Õ”«»");
			}
			else
			{
				model.addColumn("Code"); 
			    model.addColumn("Acccount Name"); 
				model.addColumn("Account Type"); 
				model.addColumn("Account Category");
			} 
			scrollPane.setViewportView(table);
			
			JLabel lblAccountCategory = new JLabel("Account Category : ");
			if(language.equals("Arabic"))
			{
				lblAddress.setText("›∆… «·Õ”«» :");
			}
			lblAccountCategory.setBounds(35, 132, 116, 16);
			frame.getContentPane().add(lblAccountCategory);
			
			comboBox_1 = new JComboBox<String>();
			comboBox_1.setBounds(149, 129, 328, 22);
			comboBox_1.setSelectedIndex(-1);
			comboBox_1.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if(!comboBox_1.getSelectedItem().toString().equals(""))
					{
						String query="where acc_grp_cat='"+comboBox_1.getSelectedItem().toString()+"' and acc_grp_type='"+comboBox.getSelectedItem().toString()+"'";
						get_acct_grp(company,query);
					}
				}
			});
			frame.getContentPane().add(comboBox_1);
			
			JLabel lblStore = new JLabel("Store");
			if(language.equals("Arabic"))
			{
				lblStore.setText("„Õ·");
			}
			lblStore.setBounds(516, 129, 56, 19);
			frame.getContentPane().add(lblStore);
			
			comboBox_2 = new JComboBox<String>();
			comboBox_2.setBounds(568, 129, 85, 22);
			frame.getContentPane().add(comboBox_2);
			try {
				Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store");
				while(resultSet.next())
				{
					comboBox_2.addItem(resultSet.getString("store_id"));
				}
				con.close();
			} catch (Exception e) {
				System.err.println(e);
			}
			
		    
			get_acct_grp(company,"");
			table.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent mouseEvent) {
					JTable table = (JTable) mouseEvent.getSource();
					if (mouseEvent.getClickCount() == 2&& table.getSelectedRow() != -1) {
						int rows = table.getSelectedRow();
						txtDubai.setText(table.getModel().getValueAt(rows, 1).toString());
						comboBox.setSelectedItem(table.getModel().getValueAt(rows, 2).toString());
						hiddentxt.setText(table.getModel().getValueAt(rows, 0).toString());
					}
				}
			});   
		    
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 514);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	private void get_acct_cat(String company,String sel_type)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String sq="select * from "+company+".mr_accounts_category where acc_type='"+sel_type+"' ";
			ResultSet resultSet = statement.executeQuery(sq);
//			if(resultSet.next())
//			{
//				comboBox_1.removeAllItems();
//			}
			comboBox_1.removeAllItems();
			while(resultSet.next())
			{
				String name=resultSet.getString("acc_category");
	            comboBox_1.addItem(name);
			}
			String query="where acc_grp_type='"+sel_type+"'";
			get_acct_grp(company,query);
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		 
	}
	
	private void get_acct_grp(String company,String query)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String sb="select * from "+company+".mr_account_group_master "+query+" ";
			ResultSet resultSet = statement.executeQuery(sb);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			while(resultSet.next())
			{
				String acc_grp_code=resultSet.getString("acc_grp_code");
	            String acc_grp_nme=resultSet.getString("acc_grp_name");
	            String acc_typ=resultSet.getString("acc_grp_type");
	            String acc_cat=resultSet.getString("acc_grp_cat");
				model.addRow(new Object[] {  acc_grp_code, acc_grp_nme, acc_typ,acc_cat });
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		 
	}
}
