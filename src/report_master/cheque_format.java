package report_master;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import sales_distribution.numbertowords;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class cheque_format {
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report(Dbsetup.dbconn.getConnection(),"rak_cheque","AP-21-01-000001","100","");
	}
	public static void report(Connection con, String format, String inv_num, String inv_amt, String db_name) throws ClassNotFoundException, SQLException{
		try 
		{
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String report_path = props.getProperty("report_path");
		    String format_name = props.getProperty(format);
		    reader.close();
		    
		    double ttlamtword=Double.parseDouble(inv_amt);
			Float num = new Float( ttlamtword ) ;
			int dollars = (int)Math.floor( num ) ;
			//floor cent = (int)Math.floor( ( num - dollars ) * 100.00 ) ;
			
			double cent=(num-dollars)*100;
			BigDecimal bd = new BigDecimal(cent);
    		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
    		cent = bd.doubleValue();
			String amountinwords = numbertowords.convert( dollars ) + " and " +numbertowords.convert( cent ) + " Fils Only" ;
		    
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("amountinwords",amountinwords);
			param.put("AP_Voucher",inv_num);
			param.put("inv_amt",inv_amt);
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
