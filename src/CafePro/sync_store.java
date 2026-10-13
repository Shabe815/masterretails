package CafePro;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

import org.json.JSONException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Properties;

public class sync_store {

	private static JFrame frmChangeUnitScreen;
	public static String display_port;
	private static DecimalFormat df2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					sync_store window = new sync_store("mr_pro","mr_pro","admin","S01", "1.0",Dbsetup.dbconn.getConnection(), "S0101");
					sync_store.frmChangeUnitScreen.setVisible(true);
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
	public sync_store(String db_name,String company, String username, String store_name,String version,Connection con, String posreg) throws JSONException, SQLException {
		initialize(db_name,company,username,store_name, version,con,posreg);
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
	private void initialize(final String db_name,final String company,final String username,final String store_name,final String version,final Connection con, final String posreg) throws JSONException, SQLException {
		frmChangeUnitScreen = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frmChangeUnitScreen.setIconImage(img);
		frmChangeUnitScreen.setVisible(true);
		frmChangeUnitScreen.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmChangeUnitScreen.setForeground(Color.RED);
		frmChangeUnitScreen.setTitle("SYNC BETWEEN STORES");
		frmChangeUnitScreen.setBounds(100, 100, 847, 479);
		frmChangeUnitScreen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmChangeUnitScreen.setAlwaysOnTop(true);
		frmChangeUnitScreen.getContentPane().setLayout(null);
		
		JLabel lblSyncBetweenStores = new JLabel("Sync Between Stores");
		lblSyncBetweenStores.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSyncBetweenStores.setBounds(12, 0, 377, 51);
		frmChangeUnitScreen.getContentPane().add(lblSyncBetweenStores);
		
		JButton btnSyncItemMaster = new JButton("Sync Item Master From HO");
		btnSyncItemMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				server_store_itemmaster( company, store_name, username,  posreg);
			}
		});
		btnSyncItemMaster.setBounds(12, 99, 240, 51);
		frmChangeUnitScreen.getContentPane().add(btnSyncItemMaster);
		
		
		
	}
	
	public static void server_store_itemmaster(String company,String store_name,String username, String posreg)
	{
		try {
			Properties prop=new Properties();
			FileInputStream ip= new FileInputStream("C:/MasterRetails/config.properties");
			prop.load(ip);
			String system_url= prop.getProperty("db_store2");
			
			String server_url= prop.getProperty("db_store1");
			String db_user = prop.getProperty("user_name");
			String db_pass = prop.getProperty("password");//"Ali0559634355";
			ip.close();
			Connection conn=null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(server_url,db_user,db_pass);
			conn.getMetaData();
			
			Connection con=null;
			con = DriverManager.getConnection(system_url,db_user,db_pass);
			con.getMetaData();
			
			Statement stat=con.createStatement();
			
			Statement statement=conn.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_item_master");
			while(resultSet.next())
			{
//				String del_line="Delete from "+company+".mr_item_master where item_code='"+resultSet.getString("item_code")+"'";
//				stat.executeUpdate(del_line);
//	//			String insertquery="INSERT INTO "+company+".mr_item_master(`item_code`, `item_name`, `description1`, `description2`, `item_details`, `department`, `sub_department`, `category`, `sub_category`, `item_type`, `item_cost`, `item_lastcost`, `item_stdcost`, `total_qty`, `item_image`, `input_tax`, `output_tax`, `sp_include_tax`, `po_inclusive_tax`, `deliItemsweighing`, `exp_days`, `weighing_sts`, `parentKitItem`, `allowDiscount`, `disc_perc`, `activeForSale`, `activeForPurchase`, `Onpromo`, `start_date`, `enddate`, `min_order`, `acc_grp`, `acc_code`, `open_qty`, `unit1`, `cf1`, `sp1`, `barcode1`, `barcode2`, `barcode3`, `unit2`, `cf2`, `sp2`, `barcode4`, `barcode5`, `barcode6`, `unit3`, `cf3`, `sp3`, `barcode7`, `barcode8`, `barcode9`, `unit4`, `cf4`, `sp4`, `barcode10`, `barcode11`, `barcode12`, `unit5`, `cf5`, `sp5`, `barcode13`, `barcode14`, `barcode15`, `unit6`, `cf6`, `sp6`, `barcode16`, `barcode17`, `barcode18`, `created_date`, `modified_date`, `input_tax_rate`, `output_tax_rate`, `commited_qty`, `vendor_code`, `vendor_name`, `fast_item`, `Open_Item`, `color_code`, `fore_color_code`, `item_vareint`) values('"+resultSet.getString("item_code")+"', '"+resultSet.getString("item_name")+"', '"+resultSet.getString("description1")+"', '"+resultSet.getString("description2")+"', '"+resultSet.getString("item_details")+"', '"+resultSet.getString("department")+"', '"+resultSet.getString("sub_department")+"', '"+resultSet.getString("category")+"', '"+resultSet.getString("sub_category")+"', '"+resultSet.getString("item_type")+"', '"+resultSet.getString("item_cost")+"', '"+resultSet.getString("item_lastcost")+"', '"+resultSet.getString("item_stdcost")+"', '0', '"+resultSet.getString("item_image")+"', '"+resultSet.getString("input_tax")+"', '"+resultSet.getString("output_tax")+"', '"+resultSet.getString("sp_include_tax")+"', '"+resultSet.getString("po_inclusive_tax")+"', '"+resultSet.getString("deliItemsweighing")+"', '"+resultSet.getString("exp_days")+"', '"+resultSet.getString("weighing_sts")+"', '"+resultSet.getString("parentKitItem")+"', '"+resultSet.getString("allowDiscount")+"', '"+resultSet.getString("disc_perc")+"', '"+resultSet.getString("activeForSale")+"', '"+resultSet.getString("activeForPurchase")+"', '"+resultSet.getString("Onpromo")+"', '"+resultSet.getString("start_date")+"', '"+resultSet.getString("enddate")+"', '"+resultSet.getString("min_order")+"', '"+resultSet.getString("acc_grp")+"', '"+resultSet.getString("acc_code")+"', '"+resultSet.getString("open_qty")+"', '"+resultSet.getString("unit1")+"', '"+resultSet.getString("cf1")+"', '"+resultSet.getString("sp1")+"', '"+resultSet.getString("barcode1")+"', '"+resultSet.getString("barcode2")+"', '"+resultSet.getString("barcode3")+"', '"+resultSet.getString("unit2")+"', '"+resultSet.getString("cf2")+"', '"+resultSet.getString("sp2")+"', '"+resultSet.getString("barcode4")+"', '"+resultSet.getString("barcode5")+"', '"+resultSet.getString("barcode6")+"', '"+resultSet.getString("unit3")+"', '"+resultSet.getString("cf3")+"', '"+resultSet.getString("sp3")+"', '"+resultSet.getString("barcode7")+"', '"+resultSet.getString("barcode8")+"', '"+resultSet.getString("barcode9")+"', '"+resultSet.getString("unit4")+"', '"+resultSet.getString("cf4")+"', '"+resultSet.getString("sp4")+"', '"+resultSet.getString("barcode10")+"', '"+resultSet.getString("barcode11")+"', '"+resultSet.getString("barcode12")+"', '"+resultSet.getString("unit5")+"', '"+resultSet.getString("cf5")+"', '"+resultSet.getString("sp5")+"', '"+resultSet.getString("barcode13")+"', '"+resultSet.getString("barcode14")+"', '"+resultSet.getString("barcode15")+"', '"+resultSet.getString("unit6")+"', '"+resultSet.getString("cf6")+"', '"+resultSet.getString("sp6")+"', '"+resultSet.getString("barcode16")+"', '"+resultSet.getString("barcode17")+"', '"+resultSet.getString("barcode18")+"', '"+resultSet.getString("created_date")+"', '"+resultSet.getString("modified_date")+"', '"+resultSet.getString("input_tax_rate")+"', '"+resultSet.getString("output_tax_rate")+"', '"+resultSet.getString("commited_qty")+"', '"+resultSet.getString("vendor_code")+"', '"+resultSet.getString("vendor_name")+"', '"+resultSet.getString("fast_item")+"', '"+resultSet.getString("Open_Item")+"', '"+resultSet.getString("color_code")+"', '"+resultSet.getString("fore_color_code")+"', '"+resultSet.getString("item_vareint")+"')";
//				stat.executeUpdate(insertquery);
			}
			
			JOptionPane.showMessageDialog(frmChangeUnitScreen, "completed");
			
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frmChangeUnitScreen, e.getMessage());
		}
	}
}
