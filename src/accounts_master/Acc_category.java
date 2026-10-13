package accounts_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
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

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

public class Acc_category {

	private JFrame frame;
	private JTextField txtDubai;
	private JTable table;
	private DefaultTableModel model;
	private JComboBox<String> comboBox;
	private JTextField hiddentxt;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acc_category window = new Acc_category("mr_pro",  null,"");
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
	public Acc_category(String company,String version, String language) throws JSONException {
		initialize(company,version,language);
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @param language 
	 * @throws JSONException 
	 */
	private void initialize(final String company,final String version, final String language) throws JSONException {
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
	    
	    JLabel lblAccountingGroups = new JLabel("Accounting Category");
	    if(language.equals("Arabic"))
		{
	    	lblAccountingGroups.setText("›∆… «·„Õ«”»…");
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
			menuItem.setText("Ì‰⁄‘");
		}
	    menuItem_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		txtDubai.setText("");
	    		get_acct_grp(company);
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
	    		if(acc_grp.equals(""))
	    		{
	    			
	    			if(language.equals("Arabic"))
	    			{
	    				JOptionPane.showMessageDialog(frame, "«·—Ã«¡ ≈œŒ«· ›∆… «·Õ”«»");
	    			}
	    			else
	    			{
	    				JOptionPane.showMessageDialog(frame, "Please Enter The Account Category");
	    			}
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
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_accounts_category where acc_category = '"+acc_grp+"' and acc_type='"+acc_type+"'  ");
						if(!resultSet.next()) 
						{
							String query="insert into "+company+".mr_accounts_category (`acc_type`, `acc_category`, `created_date`, `modified_date`) values('"+acc_type+"','"+acc_grp+"','"+formattedDate+"','"+formattedDate+"') ";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully  „ «·Õ›Ÿ »‰Ã«Õ");
							get_acct_grp(company);
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
		
		JLabel lblAddress = new JLabel("Account Category:");
		if(language.equals("Arabic"))
		{
			lblAddress.setText("›∆… «·Õ”«»:");
		}
		lblAddress.setBounds(35, 130, 108, 21);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().add(lblAddress);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 59, 694, 2);
		frame.getContentPane().add(separator);
		
		txtDubai = new JTextField();
		txtDubai.setBounds(150, 130, 479, 20);
		frame.getContentPane().add(txtDubai);
		txtDubai.setColumns(10);
	
		comboBox = new JComboBox<String>();
		comboBox.setBounds(149, 84, 221, 20);
		
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
		frame.getContentPane().add(comboBox);
		
		hiddentxt = new JTextField();
		hiddentxt.setBounds(568, 69, 116, 22);
		frame.getContentPane().add(hiddentxt);
		
		hiddentxt.setVisible(false);
		hiddentxt.setEditable(false);
		hiddentxt.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 168, 694, 2);
		frame.getContentPane().add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 181, 674, 229);
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
				 model.addColumn("«·—ﬁ„ «· ”·”·Ì"); 
				    model.addColumn("‰Ê⁄ «·Õ”«»"); 
					model.addColumn("›∆… «·Õ”«»"); 
			}
			else
			{
				 model.addColumn("Sl No."); 
				    model.addColumn("Acccount Type"); 
					model.addColumn("Account Category"); 
					
			}
		   
			scrollPane.setViewportView(table);
			get_acct_grp(company);
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
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	private void get_acct_grp(String company)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_accounts_category");
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			while(resultSet.next())
			{
				model.addRow(new Object[] { model.getRowCount()+1, resultSet.getString("acc_type"), resultSet.getString("acc_category")});
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		 
	}
}