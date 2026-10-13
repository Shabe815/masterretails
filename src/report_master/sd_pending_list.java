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

public class sd_pending_list {
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report( Dbsetup.dbconn.getConnection(), "","","","");
	}
	public static void report(Connection con, String custcode, String custname, String sort, String db_name) throws ClassNotFoundException, SQLException{
		try {
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String report_path = props.getProperty("report_path");
		    String format_name = props.getProperty("sd_pending_list");
		    reader.close();
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
				jd.setPageHeight(842);
				jd.setPageWidth(595);
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("custcode",custcode);
				param.put("custname",custname);
				param.put("sort",sort);
				param.put("mr_pro",db_name);
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