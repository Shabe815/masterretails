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

public class item_history {
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("11300013", Dbsetup.dbconn.getConnection(),"2019-01-01", "2021-08-29","PO","PR","SA","SR","AS","IN","STOCK","Aqua","mr_pro","");
	}
	public static void report(String itemcode, Connection con, String datein,String dateout,String po,String pr,String sa,String sr,String AS, String in, String stock, String itemname, String company, String query) throws ClassNotFoundException, SQLException{
		try {
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String report_path = props.getProperty("report_path");
		    String format_name = props.getProperty("item_history");
		    reader.close();
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
		    jd.setPageHeight(842);
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("date1",datein);
				param.put("date2",dateout);
				param.put("itemcode",itemcode);
				param.put("itemname",itemname);
				param.put("po",po);
				param.put("pr",pr);
				param.put("sa",sa);
				param.put("sr",sr);
				param.put("ref_AS",AS);
				param.put("stock",stock);
				param.put("ref_in",in);
				param.put("mr_pro",company);
				param.put("query1",query);
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
