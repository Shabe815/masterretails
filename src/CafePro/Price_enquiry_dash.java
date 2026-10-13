package CafePro;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import org.json.JSONException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JSeparator;

public class Price_enquiry_dash {

	private static JFrame frmChangeUnitScreen;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;
	private static JTextField textField_5;
	public static String display_port;
	private static DecimalFormat df2;
	private static int decimalPlaces;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					Price_enquiry_dash window = new Price_enquiry_dash("mr_pro","mr_pro","admin","S01", "1.0",Dbsetup.dbconn.getConnection(), "S0101","");
					Price_enquiry_dash.frmChangeUnitScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param posreg 
	 */
	public Price_enquiry_dash(String db_name,String company, String username, String store_name,String version,Connection con, String posreg,String language) throws JSONException, SQLException {
		initialize(db_name,company,username,store_name, version,con,posreg, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param posreg 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String db_name,final String company,final String username,final String store_name,final String version,final Connection con, String posreg,final String language) throws JSONException, SQLException {
		frmChangeUnitScreen = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frmChangeUnitScreen.setIconImage(img);
		frmChangeUnitScreen.setVisible(true);
		frmChangeUnitScreen.getContentPane().setBackground(Color.DARK_GRAY);
		frmChangeUnitScreen.setForeground(Color.RED);
		frmChangeUnitScreen.setTitle("PRICE ENQUIRY");
		frmChangeUnitScreen.setBounds(100, 100, 847, 479);
		frmChangeUnitScreen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmChangeUnitScreen.setAlwaysOnTop(true);
		frmChangeUnitScreen.getContentPane().setLayout(null);
		
		JLabel lblScanBarcode = new JLabel("Scan Barcode / Type Name:");
		if(language.equals("Arabic"))
		{
			lblScanBarcode.setText("„”Õ «·»«—ﬂÊœ / «”„ «·‰Ê⁄:");
		}
		lblScanBarcode.setBounds(10, 28, 194, 25);
		lblScanBarcode.setForeground(Color.YELLOW);
		lblScanBarcode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmChangeUnitScreen.getContentPane().add(lblScanBarcode);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_item_bsd_name(db_name,textField.getText());
			}
		});
		
		textField.setBounds(222, 26, 554, 36);
		frmChangeUnitScreen.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel lblItemName = new JLabel("Item Name:");
		if(language.equals("Arabic"))
		{
			lblItemName.setText("«”„ «·⁄‰’—:");
		}
		lblItemName.setBounds(10, 111, 131, 36);
		lblItemName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemName.setForeground(Color.YELLOW);
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmChangeUnitScreen.getContentPane().add(lblItemName);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_1.setEditable(false);
		textField_1.setBounds(153, 116, 664, 36);
		textField_1.setColumns(10);
		frmChangeUnitScreen.getContentPane().add(textField_1);
		
		JLabel lblItemQtyAvailable = new JLabel("Qty OH :");
		if(language.equals("Arabic"))
		{
			lblItemQtyAvailable.setText("«·ﬂ„Ì… √ÊÂ«ÌÊ :");
		}
		lblItemQtyAvailable.setBounds(10, 367, 131, 36);
		lblItemQtyAvailable.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemQtyAvailable.setForeground(Color.YELLOW);
		lblItemQtyAvailable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmChangeUnitScreen.getContentPane().add(lblItemQtyAvailable);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_2.setEditable(false);
		textField_2.setBounds(153, 364, 664, 36);
		textField_2.setColumns(10);
		frmChangeUnitScreen.getContentPane().add(textField_2);
		
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnF = new JButton("");
		btnF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new inventory_master.item_list_master(company,db_name,"dashboard_enquiry",store_name, version,0,con,"10",language);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnF.setBounds(780, 26, 37, 36);
		btnF.setIcon(new ImageIcon(serch));
		frmChangeUnitScreen.getContentPane().add(btnF);
		
		JLabel lblItemSellingPrice = new JLabel("Selling Price :");
		if(language.equals("Arabic"))
		{
			lblItemSellingPrice.setText("”⁄— «·»Ì⁄ :");
		}
		lblItemSellingPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemSellingPrice.setForeground(Color.YELLOW);
		lblItemSellingPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblItemSellingPrice.setBounds(10, 239, 131, 36);
		frmChangeUnitScreen.getContentPane().add(lblItemSellingPrice);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(153, 240, 664, 36);
		frmChangeUnitScreen.getContentPane().add(textField_3);
		
		JLabel lblItemCostPrice = new JLabel("Cost Price :");
		if(language.equals("Arabic"))
		{
			lblItemCostPrice.setText("”⁄— «· ﬂ·›… :");
		}
		lblItemCostPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemCostPrice.setForeground(Color.YELLOW);
		lblItemCostPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblItemCostPrice.setBounds(10, 303, 131, 36);
		frmChangeUnitScreen.getContentPane().add(lblItemCostPrice);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(153, 302, 664, 36);
		frmChangeUnitScreen.getContentPane().add(textField_4);
		
		JLabel lblBarcode = new JLabel("Barcode : ");
		if(language.equals("Arabic"))
		{
			lblBarcode.setText("«·»«—ﬂÊœ :");
		}
		lblBarcode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBarcode.setForeground(Color.YELLOW);
		lblBarcode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBarcode.setBounds(10, 175, 131, 36);
		frmChangeUnitScreen.getContentPane().add(lblBarcode);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(153, 178, 664, 36);
		frmChangeUnitScreen.getContentPane().add(textField_5);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 88, 829, 2);
		frmChangeUnitScreen.getContentPane().add(separator);
		
		get_store_validate_dt(db_name,store_name,username, posreg,con);
		get_register_name(db_name,username, store_name,con, posreg);
	}
	public static void  get_item_bsd_name(String company,String search)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_tmp_item_master where item_code='"+search+"' or item_name='"+search+"' or barcode='"+search+"'";
			ResultSet resultSet11= statement.executeQuery(query);
			if(resultSet11.next())
			{
				String item_desc=resultSet11.getString("item_name");
	        	String barcode=resultSet11.getString("barcode");
	        	double sp_ttl=Double.parseDouble(resultSet11.getString("sp"));
				String sp=df2.format(sp_ttl);
				textField_5.setText(barcode);
				textField_1.setText(item_desc);
	        	textField_3.setText(sp);
	        	textField_4.setText(resultSet11.getString("cost"));
	        	textField_2.setText(resultSet11.getString("qty"));
	        	textField.setText("");
	        	textField.requestFocus();
	        	
	        	String[] tokens = null;
        		int length = item_desc.length();
        		int price_space;
        		String get_name;
        		String sp_amt;
        		String space="";
        		if(length>20)
        		{
        			int size = 19;
        			tokens = item_desc.split("(?<=\\G.{" + size + "})");
        			get_name=tokens[0];
        		}
        		else
        		{
        			price_space=20-length;
        			for(int i=0;i<price_space;i++)
        			{
        				space+=" ";
        			}
        			get_name=item_desc;
        		}
        		String gap="";
        		int barcode_len=13-barcode.length();
    			for(int i=0; i<barcode_len;i++)
    			{
    				gap+=" ";
    			}
    			int sp_len=sp.length();
    			if(sp_len > 6)
    			{
    				int size = 6;
    				tokens = item_desc.split("(?<=\\G.{" + size + "})");
        			sp_amt=tokens[0];
    			}
    			else
    			{
    				sp_amt=sp;
    			}
    			//System.out.println("CLS>"+display_port+"&&ECHO "+get_name+""+space+">"+display_port+"&&ECHO "+barcode+""+gap+""+sp_amt+">"+display_port+"\"");
    			Runtime.getRuntime().exec("cmd /c \"CLS>"+display_port+"&&ECHO "+get_name+""+space+">"+display_port+"&&ECHO "+barcode+""+gap+""+sp_amt+">"+display_port+"\"");
	        	
			}
			else
			{
				JOptionPane.showMessageDialog(frmChangeUnitScreen, "NO ITEMS FOUND ·„ Ì „ «·⁄ÀÊ— ⁄·Ï √Ì ⁄‰«’—");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField.requestFocus();
			}
			
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
	public static void get_register_name(String company,String username, String store_name,Connection con, String posreg){
		try {
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_users where user_name='"+username+"' ");
			if(resultSet.next())
			{
				
				ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_assign_register where ref_user_code='"+resultSet.getString("user_code")+"' and select_status='true' ");
				if(resultSet1.next())
				{
					String queryass="select * from "+company+".mr_register_setup where register_number='"+posreg+"' ";
					ResultSet resultSet2 = statement.executeQuery(queryass);
					if(resultSet2.next())
					{
						display_port=resultSet2.getString("pole_display_port");
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frmChangeUnitScreen, "USER NOT FOUND ·„ Ì „ «·⁄ÀÊ— ⁄·Ï «·„” Œœ„");
			}
			
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frmChangeUnitScreen, e.getMessage());
		}
	}
	public static void get_store_validate_dt(String company,String store_name,String username, String posreg,Connection con)
	{
		try {
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store where store_id='"+store_name+"' ");
			if(resultSet.next())
			{
				
				decimalPlaces=Integer.parseInt(resultSet.getString("decimal_count"));
				if(decimalPlaces==2)
				{
					df2=new DecimalFormat("0.00");
				}
				else
				{
					df2=new DecimalFormat("0.000");
				}
			}
			
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frmChangeUnitScreen, e.getMessage());
		}
	}
}
