package report_master;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("deprecation")
public class customer_list {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report( Dbsetup.dbconn.getConnection(),"mr_pro","admin001","");
	}
	public static void report(Connection con,String company,String username,String pagetype) throws ClassNotFoundException, SQLException{
		try 
		{
			String printer=get_register_printer( con, company,username,pagetype);
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    String report_path = props.getProperty("report_path");
		    String format_name ="";
		    if(pagetype.equals("A4"))
		    {
		    	format_name = props.getProperty("customer_listA4");
		    } 
		    else
		    {
		    	format_name = props.getProperty("customer_list");
		    }
		    reader.close();
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("mr_pro", company);
				JasperReport jr= JasperCompileManager.compileReport(jd);
				JasperPrint jp=JasperFillManager.fillReport(jr, param,con);
				
				if(pagetype.equals("A4"))
				{
					JasperViewer.viewReport(jp,false);
				}
				else
				{
					PrintService myPrintService = findPrintService(printer);
					PrinterJob job = PrinterJob.getPrinterJob();
					job.setPrintService(myPrintService);
			        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
					MediaSizeName mediaSizeName = MediaSize.findMedia(4,4,MediaPrintableArea.INCH);
					printRequestAttributeSet.add(mediaSizeName);
					printRequestAttributeSet.add(new Copies(1));
					JRPrintServiceExporter exporter;
					exporter = new JRPrintServiceExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
					exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET,myPrintService.getAttributes());
					exporter.exportReport();
				}
				
		    } catch (Exception e) {
		        System.err.println("Cannot connect to database server");
		        System.err.println(e.getMessage());
		        e.printStackTrace();
		    }
	}
	public static String get_register_printer(Connection con,String company,String username,String pagetype){
		String printer="";
		try {
			
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_users where user_name='"+username+"' ");
			if(resultSet.next())
			{
				
				ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_assign_register where ref_user_code='"+resultSet.getString("user_code")+"' and select_status='true' ");
				if(resultSet1.next())
				{
					String queryass="select * from "+company+".mr_register_setup where register_number='"+resultSet1.getString("ref_register_num")+"' ";
					ResultSet resultSet2 = statement.executeQuery(queryass);
					if(resultSet2.next())
					{
						printer=resultSet2.getString("printer1");
						
					}
				}
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
		return printer;
	}	
	private static PrintService findPrintService(String printerName) {
    	PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
    	PrintService myPrinter = null;
    	for (int i = 0; i < services.length; i++){
	          String svcName = services[i].toString();           
	          if (svcName.contains(printerName)){
	              myPrinter = services[i];
	              break;
	          }
    	}
 	   return myPrinter;
    }
}

