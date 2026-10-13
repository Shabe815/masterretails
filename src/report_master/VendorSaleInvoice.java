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
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class VendorSaleInvoice {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("2020-05-01",  "2020-07-01",Dbsetup.dbconn.getConnection(),"","FLAT 905 SAME BUILDING","summary","","ALL","","");
	}
	public static void report(String date1,  String date2,Connection con,String register,String custcode,String report,String paycode,String receipt_type,String company, String custname) throws ClassNotFoundException, SQLException{
		try 
		{
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    String report_path = props.getProperty("report_path");
		    String format_name= props.getProperty("invoice_history_detail");
		    
		    reader.close();
		    String query="";
		    if(report.equals("detail"))
		    {
		    	//include po 
		    	query="";
		    }
		    else
		    {
		    	query="";	
		    }
		    
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
		   JRDesignQuery jq=new JRDesignQuery();
			jq.setText(query);
			jd.setQuery(jq);
		    
		    HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("date1",date1);
				param.put("date2",date2);
				param.put("register",register);
				param.put("customer",custcode);
				param.put("paycode",paycode);
				param.put("receipt_type", receipt_type);
				param.put("custname", custname);
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

