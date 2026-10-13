package report_master;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Sales_summary {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("2020-07-01",  "2020-07-11",Dbsetup.dbconn.getConnection(),"Customer Summary","","summary","","","");
	}
	public static void report(String date1,  String date2,Connection con,String type,String search,String report,String analys,String company, String reg_list) throws ClassNotFoundException, SQLException{
		try 
		{
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    String report_path = props.getProperty("report_path");
		    String format_name;
		    String query="";
		    if(report.equals("detail"))
		    {
		    	if(analys.equals("None"))
			    {
			    	analys="";
			    }
		    	format_name = props.getProperty("Sales_summary_details");
		    }
		    else
		    {
		    	format_name = props.getProperty("Sales_summary");
		    	
			    String analys_dept_item_str="";
			    String analys_cust_str="";
			    String analys_item_str="";
			    String analys_vendor_str="";
			    if(analys.equals("Quantity"))
			    {
			    	analys="qty";
			    	analys_dept_item_str="ORDER BY qty ASC";
			    	analys_cust_str="ORDER BY qty ASC";
			    	analys_item_str="ORDER BY qty ASC";
			    	analys_vendor_str="ORDER BY qty ASC";
			    }
			    if(analys.equals("Sales"))
			    {
			    	analys="total";
			    	analys_dept_item_str="ORDER BY total ASC";
			    	analys_cust_str="ORDER BY total ASC";
			    	analys_item_str="ORDER BY total ASC";
			    	analys_vendor_str="ORDER BY total ASC";
			    }
			    if(analys.equals("Gross Margin"))
			    {
			    	analys="margin";
			    	analys_dept_item_str="ORDER BY margin ASC";
			    	analys_cust_str="ORDER BY margin ASC";
			    	analys_item_str="ORDER BY margin ASC";
			    	analys_vendor_str="ORDER BY margin ASC";
			    }
			    if(analys.equals("None"))
			    {
			    	analys="None";
			    	analys_dept_item_str="ORDER BY department ASC";
			    	analys_cust_str="ORDER BY cust_name ASC";
			    	analys_item_str="ORDER BY item_name ASC";
			    	analys_vendor_str="ORDER BY vendor_code ASC";
			    	
			    }
			    String cat_item_str="";
			    String dept_item_str="";
			    String cust_pos_str="";
			    String cust_sd_str="";
			    String item_pos_str="";
			    String item_sd_str="";
			    String subdept_item_str="";
			    String subcat_item_str="";
			    String vendor_str="";
			    String dept="";
			    if(type.equals("Department Wise Item Summary"))
			    {
			    	dept=" "+company+".mr_item_master.department ";
			    }
			    if(type.equals("Department Summary"))
			    {
			    	dept=" "+company+".mr_item_master.department ";
			    }
			    if(type.equals("Sub Department Summary"))
			    {
			    	dept=" "+company+".mr_item_master.department ";
			    }
			    
			    if(type.equals("Category Wise Item Summary"))
			    {
			    	dept=" "+company+".mr_item_master.category";
			    }
			    if(type.equals("Category Summary"))
			    {
			    	dept=" "+company+".mr_item_master.category";
			    }
			    if(type.equals("Sub Category Summary"))
			    {
			    	dept=" "+company+".mr_item_master.category";
			    }
			    
			    if(!search.equals(""))
			    {
			    	cat_item_str=" and "+company+".mr_item_master.category='"+search+"' ";
			    	dept_item_str=" and "+company+".mr_item_master.department='"+search+"' ";
			    	subdept_item_str=" and "+company+".mr_item_master.sub_dept='"+search+"' ";
			    	subcat_item_str=" and "+company+".mr_item_master.sub_cat='"+search+"' ";
			    	
			    	cust_pos_str=" and "+company+".mr_pos_settlement_history.cust_code='"+search+"' ";
			    	cust_sd_str=" and "+company+".mr_sd_list_history.cust_id='"+search+"' ";
			    	item_pos_str=" and "+company+".mr_pos_settlement_history_items.item_desc='"+search+"' ";
			    	item_sd_str=" and "+company+".mr_sd_lines_history.item_name='"+search+"' ";
			    	
			    	vendor_str="and mr_pro.mr_vendor.vendor_name='"+search+"' ";
			    }
			    if(type.equals("Department Wise Item Summary"))
			    {
			    	query="(SELECT ref_reg_num,"+dept+" as department ,"+company+".mr_pos_settlement_history_items.item_desc as name,sum("+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total+"+company+".mr_pos_settlement_history_items.vat)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num left join "+company+".mr_item_master on  "+company+".mr_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code  where  ("+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and '"+date2+"') and mr_pos_settlement_history.type!='VOID' and ref_reg_num IN ('"+reg_list+"') "+dept_item_str+" GROUP BY name,ref_reg_num ) union  (select 'SND',"+dept+" as department ,"+company+".mr_sd_lines_history.item_name as name,sum("+company+".mr_sd_lines_history.qty) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_sd_lines_history.item_cost) as margin,sum( "+company+".mr_sd_lines_history.item_cost) as cost FROM  "+company+".mr_sd_lines_history INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num inner join "+company+".mr_item_master on mr_item_master.item_code=mr_sd_lines_history.item_code  where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and '"+date2+"' and mr_sd_list_history.type='sd_invoice' and status='POSTED' "+dept_item_str+"  GROUP BY name ) "+analys_dept_item_str+"";	
			    }
			    if(type.equals("Category Wise Item Summary"))
			    {
			    	query="(SELECT ref_reg_num,"+dept+" as department,"+company+".mr_pos_settlement_history_items.item_desc as name,sum("+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total+"+company+".mr_pos_settlement_history_items.vat)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num  left join "+company+".mr_item_master on  "+company+".mr_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code where  ("+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and '"+date2+"') and mr_pos_settlement_history.type!='VOID' and ref_reg_num IN ('"+reg_list+"') "+cat_item_str+" GROUP BY name,ref_reg_num ) union  (select 'SND',"+dept+" as department ,"+company+".mr_sd_lines_history.item_name as name,sum("+company+".mr_sd_lines_history.qty) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_sd_lines_history.item_cost) as margin,sum( "+company+".mr_sd_lines_history.item_cost) as cost FROM  "+company+".mr_sd_lines_history INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num inner join "+company+".mr_item_master on mr_item_master.item_code=mr_sd_lines_history.item_code  where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and '"+date2+"' and mr_sd_list_history.type='sd_invoice' and status='POSTED' "+cat_item_str+"  GROUP BY name ) ";	
			    }
			    if(type.equals("Category Summary"))
				{
			    	query="(SELECT ref_reg_num,"+dept+" as department ,sum("+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total+"+company+".mr_pos_settlement_history_items.vat)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num left join "+company+".mr_item_master on "+company+".mr_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code where  ("+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and '"+date2+"') and mr_pos_settlement_history.type!='VOID' and ref_reg_num IN ('"+reg_list+"') "+cat_item_str+" GROUP BY  "+dept+",ref_reg_num ) union (select 'SND',"+dept+" as department ,sum("+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_sd_lines_history.item_cost) as margin,sum( "+company+".mr_sd_lines_history.item_cost) as cost FROM  "+company+".mr_sd_lines_history INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num inner join "+company+".mr_item_master on mr_item_master.item_code=mr_sd_lines_history.item_code  where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and '"+date2+"' and mr_sd_list_history.type='sd_invoice' and status='POSTED' "+cat_item_str+" GROUP BY  "+dept+" ) "+analys_dept_item_str+" ";
				}
			    if(type.equals("Department Summary"))
				{
			    	query="(SELECT ref_reg_num,"+dept+" as department ,sum("+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total+"+company+".mr_pos_settlement_history_items.vat)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num INNER JOIN "+company+".mr_item_master on "+company+".mr_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code  where  ("+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and '"+date2+"') and mr_pos_settlement_history.type!='VOID' and ref_reg_num IN ('"+reg_list+"') "+dept_item_str+" GROUP BY  "+dept+",ref_reg_num ) union (select 'SND',"+dept+" as department ,sum("+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_sd_lines_history.item_cost) as margin,sum( "+company+".mr_sd_lines_history.item_cost*"+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as cost FROM  "+company+".mr_sd_lines_history INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num inner join "+company+".mr_item_master on mr_item_master.item_code=mr_sd_lines_history.item_code where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and '"+date2+"' and mr_sd_list_history.type='sd_invoice' and status='POSTED' "+dept_item_str+" GROUP BY  "+dept+" ) "+analys_dept_item_str+" ";
				}
			    if(type.equals("Sub Department Summary"))
				{
			    	query="(SELECT "+dept+" as department ,"+company+".mr_item_master.sub_department as sub_dept,sum("+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total+"+company+".mr_pos_settlement_history_items.vat)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num left join "+company+".mr_item_master on  "+company+".mr_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code  where  "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and '"+date2+"' and mr_pos_settlement_history.type!='VOID' and ref_reg_num IN ('"+reg_list+"') "+subdept_item_str+" GROUP BY  "+dept+" ) union (select "+dept+" as department ,"+company+".mr_item_master.sub_department  as sub_dept,sum("+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_sd_lines_history.item_cost) as margin,sum( "+company+".mr_sd_lines_history.item_cost) as cost FROM  "+company+".mr_sd_lines_history INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num inner join "+company+".mr_item_master on mr_item_master.item_code=mr_sd_lines_history.item_code  where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and '"+date2+"' and mr_sd_list_history.type='sd_invoice' and status='POSTED' "+subdept_item_str+" GROUP BY  "+dept+" ) "+analys_dept_item_str+"";
				}
			    if(type.equals("Sub Category Summary"))
				{
			    	query="(SELECT "+dept+" as department ,"+company+".mr_item_master.sub_category as sub_cat,sum("+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total+"+company+".mr_pos_settlement_history_items.vat)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num left join "+company+".mr_item_master on  "+company+".mr_item_master.item_code= "+company+".mr_pos_settlement_history_items.item_code  where  "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and '"+date2+"' and mr_pos_settlement_history.type!='VOID' and ref_reg_num IN ('"+reg_list+"') "+subcat_item_str+" GROUP BY  "+dept+" ) union (select "+dept+" as department ,"+company+".mr_item_master.sub_category as sub_cat,sum("+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_sd_lines_history.item_cost) as margin,sum( "+company+".mr_sd_lines_history.item_cost) as cost FROM  "+company+".mr_sd_lines_history  INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num inner join "+company+".mr_item_master on mr_item_master.item_code=mr_sd_lines_history.item_code where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and '"+date2+"'  and mr_sd_list_history.type='sd_invoice' and status='POSTED' "+subcat_item_str+" GROUP BY  "+dept+" ) "+analys_dept_item_str+"";
				}
			    if(type.equals("Item Summary"))
				{
			    	query="(SELECT ref_reg_num,"+company+".mr_pos_settlement_history_items.item_code,"+company+".mr_pos_settlement_history_items.item_desc as item_name,sum("+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total+"+company+".mr_pos_settlement_history_items.vat)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as cost FROM "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num where "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and '"+date2+"' and "+company+".mr_pos_settlement_history.type!='VOID' and ref_reg_num IN ('"+reg_list+"') "+item_pos_str+" GROUP BY "+company+".mr_pos_settlement_history_items.item_desc,ref_reg_num) union (select 'SND',"+company+".mr_sd_lines_history.item_code,"+company+".mr_sd_lines_history.item_name,sum("+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_sd_lines_history.item_cost) as margin,sum( "+company+".mr_sd_lines_history.item_cost) as cost FROM  "+company+".mr_sd_lines_history inner join "+company+".mr_item_master on mr_item_master.item_code=mr_sd_lines_history.item_code  INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and '"+date2+"' and mr_sd_list_history.type='sd_invoice' and status='POSTED' "+item_sd_str+" GROUP BY "+company+".mr_sd_lines_history.item_name ) "+analys_item_str+" ";
				}
			    if(type.equals("Customer Summary"))
				{
			    	query="(SELECT "+company+".mr_pos_settlement_history.cust_code,"+company+".mr_pos_settlement_history.cust_name,	sum("+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum("+company+".mr_pos_settlement_history_items.total+"+company+".mr_pos_settlement_history_items.vat)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as margin,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty*"+company+".mr_pos_settlement_history_items.cf) as cost FROM  "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num  where  ("+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and '"+date2+"') and "+company+".mr_pos_settlement_history.type!='VOID' and ref_reg_num IN ('"+reg_list+"') "+cust_pos_str+" GROUP BY  "+company+".mr_pos_settlement_history.cust_name ) union (select "+company+".mr_sd_list_history.cust_id,"+company+".mr_sd_list_history.cust_name,sum("+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_sd_lines_history.item_cost) as margin,sum( "+company+".mr_sd_lines_history.item_cost) as cost FROM  "+company+".mr_sd_lines_history INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and '"+date2+"' and type='sd_invoice' and status='POSTED' "+cust_sd_str+" group by "+company+".mr_sd_list_history.invoice_num ) "+analys_cust_str+"";
				}
			    
			    if(type.equals("Vendor Summary"))
				{
			    	query="(SELECT "+company+".mr_vendor.vendor_code,"+company+".mr_vendor.vendor_name,sum("+company+".mr_pos_settlement_history_items.qty) as qty,sum("+company+".mr_pos_settlement_history_items.total) as total,sum("+company+".mr_pos_settlement_history_items.vat) as vat,sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty) as cost,sum("+company+".mr_pos_settlement_history_items.total+"+company+".mr_pos_settlement_history_items.vat)-sum( "+company+".mr_pos_settlement_history_items.item_cost*"+company+".mr_pos_settlement_history_items.qty) as margin FROM "+company+".mr_pos_settlement_history_items INNER JOIN  "+company+".mr_pos_settlement_history on  "+company+".mr_pos_settlement_history.reg_num= "+company+".mr_pos_settlement_history_items.ref_reg_num AND  "+company+".mr_pos_settlement_history.receipt_no= "+company+".mr_pos_settlement_history_items.ref_receipt_num  INNER JOIN "+company+".mr_vendor_items on "+company+".mr_pos_settlement_history_items.item_code= "+company+".mr_vendor_items.ref_item_id INNER JOIN "+company+".mr_vendor on "+company+".mr_vendor.vendor_code= "+company+".mr_vendor_items.ref_vendor_id where "+company+".mr_pos_settlement_history_items.created_date BETWEEN '"+date1+"' and '"+date2+"' and  "+company+".mr_pos_settlement_history.type!='VOID' and ref_reg_num IN ('"+reg_list+"') "+vendor_str+"  GROUP BY "+company+".mr_vendor.vendor_code) union (select "+company+".mr_vendor.vendor_code,"+company+".mr_vendor.vendor_name,sum("+company+".mr_sd_lines_history.qty*"+company+".mr_sd_lines_history.cf) as qty,sum("+company+".mr_sd_lines_history.total) as total,sum("+company+".mr_sd_lines_history.tax_val) as vat,sum("+company+".mr_sd_lines_history.total)-sum( "+company+".mr_sd_lines_history.item_cost) as margin,sum( "+company+".mr_sd_lines_history.item_cost) as cost FROM  "+company+".mr_sd_lines_history inner join "+company+".mr_item_master on mr_item_master.item_code=mr_sd_lines_history.item_code  INNER JOIN "+company+".mr_vendor_items on "+company+".mr_sd_lines_history.item_code= "+company+".mr_vendor_items.ref_item_id INNER JOIN "+company+".mr_vendor on "+company+".mr_vendor.vendor_code= "+company+".mr_vendor_items.ref_vendor_id INNER JOIN  "+company+".mr_sd_list_history on  "+company+".mr_sd_list_history.invoice_num = "+company+".mr_sd_lines_history.ref_inv_num where  "+company+".mr_sd_list_history.date BETWEEN '"+date1+"' and '"+date2+"' and mr_sd_list_history.type='sd_invoice' and status='POSTED' "+vendor_str+" GROUP BY   "+company+".mr_vendor.vendor_code ) "+analys_vendor_str+"";
				}
		    }
		    reader.close();
		    
		    
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
		    HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("date1",date1);
				param.put("date2",date2);
				param.put("type",type);
				param.put("search",search);
				param.put("analys", analys);
				param.put("mr_pro",company);
				param.put("reg_list", reg_list);
				param.put("query", query);
				JasperReport jr= JasperCompileManager.compileReport(jd);
				JasperPrint jp=JasperFillManager.fillReport(jr, param,con);
				JasperViewer.viewReport(jp,false);
		    } catch (Exception e) {
		        System.err.println("Cannot connect to database server");
		        System.err.println(e.getMessage());
		        e.printStackTrace();
		    }
	}
}

