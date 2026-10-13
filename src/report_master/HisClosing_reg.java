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
public class HisClosing_reg {
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("S0101", Dbsetup.dbconn.getConnection(), "POS-80C","mr_pro", "2024-02-18", "2024-02-20","RAFAT");
	}
	
	public static void report(String ref_reg, Connection con, String printer, String company, String date1, String date2,String user) throws ClassNotFoundException, SQLException{
		try 
		{
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String report_path = props.getProperty("report_path");
		    String format_name = props.getProperty("His_close_report");
		    reader.close();
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("uq_id",ref_reg);
				param.put("mr_pro",company);
				param.put("date1",date1);
				param.put("date2",date2);
				param.put("user",user);
				param.put("regdata", "SELECT "+company+".mr_close_pay_list_history_items.`paycode`, "+company+".mr_close_pay_list_history_items.`sale_amt`,  "+company+".mr_close_pay_list_history_items.`total_amt`,"+company+".mr_close_pay_list_history_items.`ex_rate`,  "+company+".mr_close_pay_list_history_items.`vat_amt` FROM "+company+".mr_close_pay_list_history_items INNER JOIN "+company+".mr_store_paycodes on "+company+".mr_store_paycodes.paycode="+company+".mr_close_pay_list_history_items.paycode inner join "+company+".mr_close_pay_list_history on mr_close_pay_list_history_items.ref_reg_num=mr_close_pay_list_history.ref_reg_num and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time where  "+company+".mr_close_pay_list_history_items.ref_reg_num in ('"+ref_reg+"') and mr_close_pay_list_history.close_date between '"+date1+"' and  '"+date2+"' and username IN ("+user+")");
				param.put("cashdata", "SELECT * FROM  "+company+".mr_close_pay_list_history_items inner join "+company+".mr_close_pay_list_history on mr_close_pay_list_history_items.ref_reg_num=mr_close_pay_list_history.ref_reg_num and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time  WHERE ( "+company+".mr_close_pay_list_history_items.paycode='CASH' or  "+company+".mr_close_pay_list_history_items.paycode='PAYIN' or  "+company+".mr_close_pay_list_history_items.paycode='PAYOUT' or  "+company+".mr_close_pay_list_history_items.paycode='CUSTOMERPAYMENTS')  and  "+company+".mr_close_pay_list_history_items.ref_reg_num in ('"+ref_reg+"') and mr_close_pay_list_history.close_date between '"+date1+"' and  '"+date2+"' and username IN ("+user+")");
				param.put("variencedata", "SELECT openings,paycode,`total_amt`,`settle_amt`,`varience`,vat_amt FROM  "+company+".mr_close_pay_list_history_items inner join "+company+".mr_close_pay_list_history on mr_close_pay_list_history_items.ref_reg_num=mr_close_pay_list_history.ref_reg_num and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time WHERE paycode NOT IN (select paycode from "+company+".mr_close_pay_list_history_items where ( "+company+".mr_close_pay_list_history_items.paycode='CASH' or "+company+".mr_close_pay_list_history_items.paycode='PAYIN' or  "+company+".mr_close_pay_list_history_items.paycode='PAYOUT' or "+company+".mr_close_pay_list_history_items.paycode='CUSTOMERPAYMENTS')) and "+company+".mr_close_pay_list_history_items.ref_reg_num  in ('"+ref_reg+"') and mr_close_pay_list_history.close_date between '"+date1+"' and  '"+date2+"' and username IN ("+user+")");
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
