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
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("deprecation")
public class order_summary {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("S0102", Dbsetup.dbconn.getConnection(), "XRPT","\\\\Accounts\\epson","","","","","");
	}
	public static void report(String reg_num, Connection con, String type, String printer, String pagetype,String invoice_type, String company, String waiter_type, String reg_list) throws ClassNotFoundException, SQLException{
		try 
		{
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    String report_path = props.getProperty("report_path");
		    String format_name =props.getProperty("order_summary");
		    reader.close();
		    
		    String query="";
		    if(invoice_type.equals("1"))
		    {
		    	if(waiter_type.equals("1"))
		    	{
		    		query="select  "+company+".mr_store.store_name, "+company+".mr_store.addr1, "+company+".mr_store.addr2, "+company+".mr_store.city, "+company+".mr_store.country, "+company+".mr_store.ph, "+company+".mr_store.trn, "+company+".mr_close_pay_list.username, "+company+".mr_close_pay_list.store, "+company+".mr_close_pay_list.ref_reg_num, "+company+".mr_close_pay_list.computer, "+company+".mr_close_pay_list.remarks, "+company+".mr_close_pay_list.invoice_num, "+company+".mr_close_pay_list.created_date, "+company+".mr_close_pay_list.date_time,sum(`total`) as sale_amt,sum(`vat`) as vat_amt,mode from  "+company+".mr_close_pay_list inner join  "+company+".mr_store ON  "+company+".mr_close_pay_list.store= "+company+".mr_store.store_id inner join "+company+".mr_pos_settlement_cash on mr_pos_settlement_cash.store_id=mr_store.store_id where mr_pos_settlement_cash.reg_num IN ('"+reg_list+"')  and mr_close_pay_list.ref_reg_num='"+reg_num+"' and type!='VOID'  group by `mode`";
		    	}
		    	else
		    	{
		    		query="select  "+company+".mr_store.store_name, "+company+".mr_store.addr1, "+company+".mr_store.addr2, "+company+".mr_store.city, "+company+".mr_store.country, "+company+".mr_store.ph, "+company+".mr_store.trn, "+company+".mr_close_pay_list.username, "+company+".mr_close_pay_list.store, "+company+".mr_close_pay_list.ref_reg_num, "+company+".mr_close_pay_list.computer, "+company+".mr_close_pay_list.remarks, "+company+".mr_close_pay_list.invoice_num, "+company+".mr_close_pay_list.created_date, "+company+".mr_close_pay_list.date_time,sum(`total`) as sale_amt,sum(`vat`) as vat_amt,mode from  "+company+".mr_close_pay_list inner join  "+company+".mr_store ON  "+company+".mr_close_pay_list.store= "+company+".mr_store.store_id inner join "+company+".mr_pos_settlement_cash on mr_pos_settlement_cash.store_id=mr_store.store_id where mr_pos_settlement_cash.reg_num='"+reg_num+"'  and mr_close_pay_list.ref_reg_num='"+reg_num+"' and type!='VOID'  group by `mode`";
		    	}
		    	
		    }
		    else
		    {
		    	query="select  "+company+".mr_store.store_name, "+company+".mr_store.addr1, "+company+".mr_store.addr2, "+company+".mr_store.city, "+company+".mr_store.country, "+company+".mr_store.ph, "+company+".mr_store.trn, "+company+".mr_close_pay_list.username, "+company+".mr_close_pay_list.store, "+company+".mr_close_pay_list.ref_reg_num, "+company+".mr_close_pay_list.computer, "+company+".mr_close_pay_list.remarks, "+company+".mr_close_pay_list.invoice_num, "+company+".mr_close_pay_list.created_date, "+company+".mr_close_pay_list.date_time,sum(`total`) as sale_amt,sum(`vat`) as vat_amt,mode from  "+company+".mr_close_pay_list inner join  "+company+".mr_store ON  "+company+".mr_close_pay_list.store= "+company+".mr_store.store_id inner join "+company+".mr_pos_settlement_cash on mr_pos_settlement_cash.store_id=mr_store.store_id where mr_pos_settlement_cash.reg_num='"+reg_num+"'  and mr_close_pay_list.ref_reg_num='"+reg_num+"' and type!='VOID'   group by `mode`";
		    }
		    
		    
		   
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
		    JRDesignQuery jq=new JRDesignQuery();
			jq.setText(query);
			jd.setQuery(jq);
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("reg_no",reg_num);
				param.put("mr_pro",company);
				JasperReport jr= JasperCompileManager.compileReport(jd);
				JasperPrint jp=JasperFillManager.fillReport(jr, param,con);
				if(pagetype.equals("View"))
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

