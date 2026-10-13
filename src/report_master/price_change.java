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

public class price_change {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("2019-08-01",  "2019-08-31",Dbsetup.dbconn.getConnection(),"S0102","","detail","","","");
	}
	public static void report(String date1,  String date2,Connection con,String register,String customer,String report,String item,String user,String company) throws ClassNotFoundException, SQLException{
		try 
		{
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    String report_path = props.getProperty("report_path");
		    String format_name;
		    if(report.equals("detail"))
		    {
		    	format_name = props.getProperty("invoice_history");
		    }
		    else
		    {
		    	format_name = props.getProperty("invoice_history");
		    }
		    reader.close();
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
		    HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("date1",date1);
				param.put("date2",date2);
				param.put("register",register);
				param.put("customer",customer);
				param.put("user",user);
				param.put("item", item);
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

