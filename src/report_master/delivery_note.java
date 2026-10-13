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

public class delivery_note {
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("QU-21-01-000014", Dbsetup.dbconn.getConnection(), "OPEN","","","");
	}
	public static void report(String invoice_num, Connection con, String status, String do_num, String reportformat, String company) throws ClassNotFoundException, SQLException{
		try {
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String report_path = props.getProperty("report_path");
		    String format_name = props.getProperty(reportformat);
		    reader.close();
		    File config = new File("C:/MasterRetails/config.properties");
			FileReader reader1 = new FileReader(config);
		    Properties props1 = new Properties();
		    props1.load(reader1);
		 
		    String image_url = props1.getProperty("image");
		    reader1.close();
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("invoice_num",invoice_num);
				param.put("status",status);
				param.put("do_num",do_num);
				param.put("image_url",image_url);
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