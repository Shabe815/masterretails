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

public class tax_report {
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report(Dbsetup.dbconn.getConnection(),"2019-01-01", "2019-07-29","PO","PR","SA","","");
	}
	public static void report(Connection con, String datein,String dateout,String tax_type,String report_type,String sort_by,String report_query,String company) throws ClassNotFoundException, SQLException{
		try {
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String report_path = props.getProperty("report_path");
		    String format_name = props.getProperty("tax_report");
		    reader.close();
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
			
			jd.setPageHeight(842);
				jd.setPageWidth(595);
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("date1",datein);
				param.put("date2",dateout);
				param.put("tax_type",tax_type);
				param.put("report_type",report_type);
				param.put("sort_by",sort_by);
				param.put("report_query",report_query);
				param.put("mr_pro",company);
				JasperReport jr= JasperCompileManager.compileReport(jd);
				JasperPrint jp=JasperFillManager.fillReport(jr, param,con);
				JasperViewer.viewReport(jp,false);
				//JasperExportManager.exportReportToPdfFile(jp,"C:\\cafe\\report format\\po_report.pdf");    
		    } catch (Exception e) {
		        System.err.println("Cannot connect to database server");
		        System.err.println(e.getMessage());
		        e.printStackTrace();
		    }
	}

}
