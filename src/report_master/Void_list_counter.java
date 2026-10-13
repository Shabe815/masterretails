package report_master;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
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

@SuppressWarnings("deprecation")
public class Void_list_counter {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("S0101", Dbsetup.dbconn.getConnection(), "","");
	}
	public static void report(String reg_num, Connection con, String printer,String company) throws ClassNotFoundException, SQLException{
		try 
		{
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    String report_path = props.getProperty("report_path");
		    String format_name = props.getProperty("void_list");
		    reader.close();
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("reg_no",reg_num);
				param.put("mr_pro",company);
				JasperReport jr= JasperCompileManager.compileReport(jd);
				JasperPrint jp=JasperFillManager.fillReport(jr, param,con);
				
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
				
				
		    } catch (Exception e) {
		        System.err.println("Cannot connect to database server");
		        System.err.println(e.getMessage());
		        e.printStackTrace();
		    }
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

