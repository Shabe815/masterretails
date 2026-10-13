package inventory_master;

import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class Import_csv {
	static void load(String company) throws Exception{
		try {
			Inv_More.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Connection con = Dbsetup.dbconn.getConnection();
			
			
			String csvFilePath = "C:/MasterRetails/Export/itemmaster.csv";
			con.setAutoCommit(false);
			 
            String sql = "INSERT INTO "+company+".mr_item_master (item_code,item_name,description1,description2,item_details,department,sub_department,category,sub_category,item_type,item_cost,item_lastcost,item_stdcost,total_qty,item_image,input_tax,output_tax,sp_include_tax,po_inclusive_tax,deliItemsweighing,exp_days,weighing_sts,parentKitItem,allowDiscount,disc_perc,activeForSale,activeForPurchase,Onpromo,start_date,enddate,min_order,acc_grp,acc_code,open_qty,unit1,cf1,sp1,barcode1,barcode2,barcode3,unit2,cf2,sp2,barcode4,barcode5,barcode6,unit3,cf3,sp3,barcode7,barcode8,barcode9,unit4,cf4,sp4,barcode10,barcode11,barcode12,unit5,cf5,sp5,barcode13,barcode14,barcode15,unit6,cf6,sp6,barcode16,barcode17,barcode18,created_date,modified_date,input_tax_rate,output_tax_rate,commited_qty,vendor_code,vendor_name,fast_item,Open_Item,color_code,fore_color_code,item_vareint,openitem_qty) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
 
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;
 
            //int count = 0;
 
            lineReader.readLine(); // skip header line
 
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                for(int i=0;i<83;i++)
                {
                	ps.setString(i+1,data[i]);
                }
				ps.addBatch();
				ps.clearParameters();
            }
            lineReader.close();
            ps.executeBatch();
			con.commit();
			con.setAutoCommit(true);
//			String loadQuery = "LOAD DATA LOCAL INFILE '" + "C:/MasterRetails/Export/itemmaster.csv" + "' INTO TABLE "+company+".mr_item_master FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' (`item_code`, `item_name`, `description1`, `description2`, `item_details`, `department`, `sub_department`, `category`, `sub_category`, `item_type`, `item_cost`, `item_lastcost`, `item_stdcost`, `total_qty`, `item_image`, `input_tax`, `output_tax`, `sp_include_tax`, `po_inclusive_tax`, `deliItemsweighing`, `exp_days`, `weighing_sts`, `parentKitItem`, `allowDiscount`, `disc_perc`, `activeForSale`, `activeForPurchase`, `Onpromo`, `start_date`, `enddate`, `min_order`, `acc_grp`, `acc_code`, `open_qty`, `unit1`, `cf1`, `sp1`, `barcode1`, `barcode2`, `barcode3`, `unit2`, `cf2`, `sp2`, `barcode4`, `barcode5`, `barcode6`, `unit3`, `cf3`, `sp3`, `barcode7`, `barcode8`, `barcode9`, `unit4`, `cf4`, `sp4`, `barcode10`, `barcode11`, `barcode12`, `unit5`, `cf5`, `sp5`, `barcode13`, `barcode14`, `barcode15`, `unit6`, `cf6`, `sp6`, `barcode16`, `barcode17`, `barcode18`, `created_date`, `modified_date`, `input_tax_rate`, `output_tax_rate`) ";
//			Statement stmt = con.createStatement();
//			stmt.execute(loadQuery);
			
			Inv_More.frame.setCursor(Cursor.getDefaultCursor());
			JOptionPane.showMessageDialog(Inv_More.frame, "Upload Successfull");
		} catch (Exception e) {
			Inv_More.frame.setCursor(Cursor.getDefaultCursor());
			JOptionPane.showMessageDialog(Inv_More.frame, e.getMessage());
		}
		
		
		}
}