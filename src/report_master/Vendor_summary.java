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

public class Vendor_summary {
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("2019-01-01", Dbsetup.dbconn.getConnection(), "2019-07-29","A0001","", null, null,null);
	}
	public static void report(String start_date, Connection con, String end_date,String vendor_id, String company, String newcramt, String newdramt, String reporttype) throws ClassNotFoundException, SQLException{
		try {
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String report_path = props.getProperty("report_path");
		    String format_name = props.getProperty("vendor_summary");
		    reader.close();
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
				jd.setPageHeight(842);
				jd.setPageWidth(595);
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("start_date",start_date);
				param.put("end_date",end_date);
				param.put("vendor_id",vendor_id);
				param.put("customer_id","V-"+vendor_id);
				param.put("mr_pro",company);
				param.put("newcramt",newcramt);
				param.put("newdramt",newdramt);
				param.put("reporttype",reporttype);
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
