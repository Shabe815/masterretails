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

public class Payinout_summary {
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("", Dbsetup.dbconn.getConnection(),"2019-08-01", "2019-08-29","payin","","","","","");
	}
	public static void report(String reg_list, Connection con, String start_date,String end_date,String type,String user_list,String company, String reason, String detail,String pagetype) throws ClassNotFoundException, SQLException{
		try {
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String report_path = props.getProperty("report_path");
		    String format_name = "";
		    if(pagetype.equals("A4"))
		    {
		    	if(detail.equals("0"))
			    {
			    	format_name = props.getProperty("payinout_summary");
			    }
			    else
			    {
			    	format_name = props.getProperty("payinout_summaryDetails");
			    }
		    }
		    else
		    {
		    	if(detail.equals("0"))
			    {
			    	format_name = props.getProperty("POSpayinout_summary");
			    }
			    else
			    {
			    	format_name = props.getProperty("POSpayinout_summaryDetails");
			    }
		    }
		    
		    
		    reader.close();
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
			
			jd.setPageHeight(842);
				jd.setPageWidth(595);
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("date2",end_date);
				param.put("date1",start_date);
				param.put("reg_list",reg_list);
				param.put("user_list", user_list);
				param.put("reason_list", reason);
				param.put("type", type);
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
