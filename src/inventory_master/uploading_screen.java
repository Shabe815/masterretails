package inventory_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class uploading_screen {

	private static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					uploading_screen window = new uploading_screen(null,"");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param host_name 
	 * @param selectedFile 
	 * @throws Exception 
	 * @wbp.parser.entryPoint
	 */
	public uploading_screen( File selectedFile,String company) throws Exception {
		initialize(selectedFile,company);
	}

	
	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param selectedFile 
	 * @param company 
	 * @throws Exception 
	 */
	private void initialize(File selectedFile, String company) throws Exception {
		frame = new JFrame();
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(373, 184, 121, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(193, 72, 487, 126);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(177, 46, 46, 14);
		panel.add(label);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 546);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	    String formattedDate = myDateObj.format(myFormatObj);  
		String line="";
		int row=1;
		Connection con=Dbsetup.dbconn.getConnection();
		Statement statement = con.createStatement();
		//Statement statement2 = con.createStatement();
		BufferedReader br = new BufferedReader(new FileReader(selectedFile));
		while ((line = br.readLine()) != null) {
			if(row!=1)
			{
				String[] cols = line.split(",");
				String code="NEW";
				String itemcode = null;
				ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_item_master where item_name='"+cols[0]+"'");
				if(!resultSet.next())
				{
					if(code.equals("NEW"))
					{
						ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_department where dept_name='"+cols[4]+"'");
						if(resultSet1.next())
						{
							String dept_code=resultSet1.getString("dept_code");
							String seq_num=resultSet1.getString("seq_num");
							StringBuilder sb = new StringBuilder();
							for (int toPrepend=3-dept_code.length(); toPrepend>0; toPrepend--) {
							    sb.append('0');
							}
							sb.append(dept_code);
							StringBuilder sq = new StringBuilder();
							for (int toPrepend=5-seq_num.length(); toPrepend>0; toPrepend--) {
							    sq.append('0');
							}
							sq.append(seq_num);
							itemcode = sb.toString()+""+sq.toString();
							int seq=Integer.parseInt(resultSet1.getString("seq_num"))+1;
							String query1="update "+company+".mr_department set seq_num="+seq+" where dept_name='"+cols[4]+"'";
							statement.execute(query1);
						}
						
					}
					String query="insert into "+company+".mr_item_master(  `item_code`, `item_name`, `description1`, `description2`, `item_details`, `department`, `sub_department`, `category`, `sub_category`, `item_type`, `item_cost`, `item_lastcost`, `item_stdcost`, `total_qty`,  `input_tax`, `output_tax`, `sp_include_tax`, `po_inclusive_tax`, `deliItemsweighing`, `exp_days`, `weighing_sts`, `parentKitItem`, `allowDiscount`, `disc_perc`, `activeForSale`, `activeForPurchase`, `Onpromo`, `start_date`, `enddate`, `min_order`, `acc_grp`, `open_qty`, `unit1`, `cf1`, `sp1`, `barcode1`, `barcode2`, `barcode3`, `unit2`, `cf2`, `sp2`, `barcode4`, `barcode5`, `barcode6`, `unit3`, `cf3`, `sp3`, `barcode7`, `barcode8`, `barcode9`, `unit4`, `cf4`, `sp4`, `barcode10`, `barcode11`, `barcode12`, `unit5`, `cf5`, `sp5`, `barcode13`, `barcode14`, `barcode15`, `unit6`, `cf6`, `sp6`, `barcode16`, `barcode17`, `barcode18`, `created_date`, `modified_date`) VALUES ('"+itemcode+"','"+cols[0]+"','"+cols[1]+"','"+cols[2]+"','"+cols[3]+"','"+cols[4]+"','"+cols[5]+"','"+cols[6]+"','"+cols[7]+"','"+cols[8]+"','"+cols[9]+"','"+cols[10]+"','"+cols[11]+"','"+cols[12]+"','"+cols[14]+"','"+cols[15]+"','"+cols[16]+"','"+cols[25]+"','"+cols[17]+"','"+cols[30]+"','"+cols[29]+"','"+cols[18]+"','"+cols[19]+"','"+cols[20]+"','"+cols[21]+"','"+cols[22]+"','"+cols[23]+"','"+formattedDate+"','"+formattedDate+"','"+cols[24]+"','STOCK  AT S01','"+cols[28]+"','Each','"+cols[27]+"','"+cols[26]+"','"+cols[13]+"','','','Box','','','','','','Pack1','','','','','','Pack2','','','','','','Pack3','','','','','','Pack4','','','','','','"+formattedDate+"','"+formattedDate+"')";
					statement.execute(query);
					//String query1="INSERT INTO `mr_item_barcode`(`ref_item_code`, `unit`, `cf`, `sp`, `barcode`, `barcode2`, `barcode3`, `created_date`, `modified_date`) VALUES('"+itemcode+"','Each','"+cols[27]+"','"+cols[26]+"','"+cols[13]+"','','','"+formattedDate+"','"+formattedDate+"'),('"+itemcode+"','Box','0','0','0','','','"+formattedDate+"','"+formattedDate+"'),('"+itemcode+"','Pack1','0','0','0','','','"+formattedDate+"','"+formattedDate+"'),('"+itemcode+"','Pack2','0','0','0','','','"+formattedDate+"','"+formattedDate+"'),('"+itemcode+"','Pack3','0','0','0','','','"+formattedDate+"','"+formattedDate+"'),('"+itemcode+"','Pack4','0','0','0','','','"+formattedDate+"','"+formattedDate+"')";
					//statement2.execute(query1);
				}
			}
			row++;
		}
		
		statement.close();
		//statement2.close();
		con.close();
		br.close();
		
	}
}
