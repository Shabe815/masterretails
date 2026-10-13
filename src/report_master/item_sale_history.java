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

public class item_sale_history {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("2020-07-01",  "2020-07-11",Dbsetup.dbconn.getConnection(),"Customer Summary","","","","","","","");
	}
	public static void report(String date1,  String date2,Connection con,String type,String cust,String dept,String item,String sub_dept,String vendor,String analys,String company) throws ClassNotFoundException, SQLException{
		try 
		{
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    String report_path = props.getProperty("report_path");
		    String format_name = props.getProperty("Sales_summary");
		    
		    reader.close();
		    if(analys.equals("Quantity"))
		    {
		    	analys="qty";
		    }
		    if(analys.equals("Sales"))
		    {
		    	analys="total";
		    }
		    if(analys.equals("Gross Margin"))
		    {
		    	analys="margin";
		    }
		    
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
		    HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("date1",date1);
				param.put("date2",date2);
				param.put("type",type);
				param.put("cust",cust);
				param.put("dept",dept);
				param.put("item",item);
				param.put("sub_dept",sub_dept);
				param.put("vendor",vendor);
				param.put("analys", analys);
				param.put("mr_pro",company);
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

