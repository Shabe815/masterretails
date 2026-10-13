package inventory_master;

import java.awt.Cursor;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class import_customer {
	static void load(String company) throws Exception{
		Inv_More.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Connection con = Dbsetup.dbconn.getConnection();
		String loadQuery1 = "LOAD DATA LOCAL INFILE '" + "C:/MasterRetails/Export/customer_list.csv" + "' INTO TABLE "+company+".mr_customer_master FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' (`cust_code`, `cust_name`, `type`, `category`, `sub_category`, `rating`, `terms_code`, `shipping_code`, `currency`, `sales_rep`, `disc`, `addr1`, `addr2`, `city`, `country`, `id_type`, `id_num`, `ph1`, `ph2`, `default_ar_acc`, `credit_limit`, `posted_bal`, `unposted_bal`, `avail_credit`, `advance_amt`, `last_sale_date`, `last_payment_date`, `last_paid_amt`, `status`, `created_date`, `modified_date`, `TRN`, `visible`) ";
		Statement stmt1 = con.createStatement();
		stmt1.execute(loadQuery1);
		
		Inv_More.frame.setCursor(Cursor.getDefaultCursor());
		JOptionPane.showMessageDialog(Inv_More.frame, "Upload Successfull");
		
		}
}