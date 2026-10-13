package PO_reports;

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

public class vendor_items_rpt {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("2019-08-01","2019-09-17", Dbsetup.dbconn.getConnection(),"","","","", null, null, null);
	}
	public static void report(String date1,String date2,Connection  con ,String vendor,String item,String dept, String company, String subdept, String cat, String subcat) throws ClassNotFoundException, SQLException{
		try {
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String report_path = props.getProperty("report_path");
		    String format_name = props.getProperty("item_purchase");
		    reader.close();
		    String vendor_str="";
		    if(!vendor.equals(""))
		    {
		    	vendor_str=" and vendor_name='"+vendor+"' ";
		    }
		    String item_str="";
		    if(!item.equals(""))
		    {
		    	item_str=" and mr_purchase_order_history.item_name='"+item+"'";
		    }
		    String dept_str="";
		    if(!dept.equals(""))
		    {
		    	dept_str=" and department='"+dept+"'";
		    }
		    String subdept_str="";
		    if(!subdept.equals(""))
		    {
		    	subdept_str=" and sub_dept='"+subdept+"'";
		    }
		    String cat_str="";
		    if(!cat.equals(""))
		    {
		    	cat_str=" and category='"+cat+"'";
		    }
		    String subcat_str="";
		    if(!subcat.equals(""))
		    {
		    	subcat_str=" and sub_cat='"+subcat+"'";
		    }
		    
		    String query="select department,sub_dept,category,sub_cat,po_date,vendor_name,po_no,mr_purchase_order_history.item_code,mr_purchase_order_history.item_name,cost_after_sub_ttl_disc,mr_purchase_order_history.unit,mr_purchase_order_history.cf,mr_purchase_order_history.qty,total from "+company+".mr_purchase_list_history inner join "+company+".mr_purchase_order_history on "+company+".mr_purchase_order_history.ref_po_num="+company+".mr_purchase_list_history.po_no inner join "+company+".mr_vendor on "+company+".mr_vendor.vendor_code="+company+".mr_purchase_list_history.vendor_id inner join "+company+".mr_tmp_item_master on "+company+".mr_tmp_item_master.barcode="+company+".mr_purchase_order_history.barcode where "+company+".mr_purchase_list_history.po_date between '"+date1+"' and '"+date2+"' "+vendor_str+" "+item_str+" "+dept_str+" "+subdept_str+" "+cat_str+" "+subcat_str+" ";
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
			   
		    
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("vendor",vendor);
				param.put("date1",date1);
				param.put("date2",date2);
				param.put("itemname",item);
				param.put("mr_pro",company);
				param.put("dept",dept);
				param.put("subdept",subdept);
				param.put("cat",cat);
				param.put("subcat",subcat);
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
