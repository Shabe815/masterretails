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

import sales_master.posscreen;
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
public class holded_invoice {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("19000018", Dbsetup.dbconn.getConnection(), "\\\\Accounts\\epson","S0101","DUP","","","","");
	}
	public static void report(String reciept, Connection con, String printer, String reg_num, String type,String company, String page_type,String dept,String kot) throws ClassNotFoundException, SQLException{
		try 
		{
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			HashMap<String, Object> param = new HashMap<String, Object>();
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    String report_path = props.getProperty("report_path");
		    String format_name ="";
			if(page_type.equals("Kitchen KOT"))
			{
				if(posscreen.hold_bill.equals("0"))
				{
					format_name = props.getProperty("kot_invoice");
				}
				else
				{
					format_name = props.getProperty("kot_invoice_dept");
				}
				
//				printer=posscreen.printer2;
//				byte[] printers=Base64.decode(printer); //resultSet.getString("printer");
//				printer = new String(printers, StandardCharsets.UTF_8);
			}
			if(page_type.equals("dup_kot_invoice"))
			{
				format_name = props.getProperty("dup_kot_invoice");
			}
			if(page_type.equals("changetable_kot_invoice"))
			{
				param.put("OldTable",type);
				format_name = props.getProperty("kot_changetable_invoice");
			}
			if(page_type.equals("Delivery"))
			{
				format_name = props.getProperty("hold_invoice");
			}
			if(page_type.equals("Hold Invoice"))
			{
				format_name = props.getProperty("hold_invoice1");
			}
			if(page_type.equals("void Invoice"))
			{
				format_name = props.getProperty("void_kot");
			}
		    reader.close();
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
				
				param.put("reciept",reciept);
				param.put("reg_num",reg_num);
				param.put("dept",dept);
				param.put("kot",kot);
				param.put("mr_pro",company);
				JasperReport jr= JasperCompileManager.compileReport(jd);
				JasperPrint jp=JasperFillManager.fillReport(jr, param,con);
				
				if(type.equals(""))//DUP
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

