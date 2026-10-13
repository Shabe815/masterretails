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

@SuppressWarnings("deprecation")
public class delivery_invoice_list {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report( Dbsetup.dbconn.getConnection(), "\\\\Accounts\\epson","S0101","DUP","","");
	}
	public static void report(Connection con, String printer, String reg_num,String delivery_boy, String company, String invoicelist) throws ClassNotFoundException, SQLException{
		try 
		{
			File configFile = new File("C:/MasterRetails/report_assign.properties");
						
			
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    String report_path = props.getProperty("report_path");
		    String format_name = props.getProperty("delivery_invoice_list");
			
		    reader.close();
		    
		    String query="SELECT  "+company+".mr_store.store_name, "+company+".mr_store.addr1, "+company+".mr_store.addr2, "+company+".mr_store.city, "+company+".mr_store.country, "+company+".mr_store.ph,"+company+".mr_store.trn, "+company+".mr_pos_hold_invoice.receipt_no, "+company+".mr_pos_hold_invoice.reg_num, "+company+".mr_pos_hold_invoice.created_date as date, "+company+".mr_pos_hold_invoice.time, "+company+".mr_pos_hold_invoice.user, "+company+".mr_pos_hold_invoice.store_id, "+company+".mr_pos_hold_invoice.cust_code,"+company+".mr_pos_hold_invoice.cust_name, "+company+".mr_customer_master.addr1 as cust_addr, "+company+".mr_CUSTOMER_MASTER.ADDR2 AS CUST_ADDR2, "+company+".mr_pos_hold_invoice_items.qty as item_qty,  "+company+".mr_pos_hold_invoice.total as item_ttl, "+company+".mr_pos_hold_invoice_items.vat as itm_vat, "+company+".mr_pos_hold_invoice_items.total as total_price, "+company+".mr_pos_hold_invoice_items.qty, "+company+".mr_pos_hold_invoice_items.unit_price, "+company+".mr_pos_hold_invoice_items.item_desc,"+company+".mr_item_master.description2 as arabic, "+company+".mr_pos_hold_invoice_items.item_code, "+company+".mr_pos_hold_invoice_items.kot_number,"+company+".mr_pos_hold_invoice.table_num,delivery_user,receipt_no,  "+company+".mr_pos_hold_invoice.disc,"+company+".mr_pos_hold_invoice.vat as ttl_vat FROM  "+company+".mr_store inner join  "+company+".mr_pos_hold_invoice on  "+company+".mr_pos_hold_invoice.store_id= "+company+".mr_store.store_id inner join  "+company+".mr_pos_hold_invoice_items on  "+company+".mr_pos_hold_invoice.receipt_no= "+company+".mr_pos_hold_invoice_items.ref_receipt_num AND "+company+".mr_pos_hold_invoice.reg_num= "+company+".mr_pos_hold_invoice_items.ref_reg_no inner join "+company+".mr_item_master on "+company+".mr_item_master.item_code="+company+".mr_pos_hold_invoice_items.item_code INNER JOIN  "+company+".mr_CUSTOMER_MASTER ON  "+company+".mr_CUSTOMER_MASTER.cust_name= "+company+".mr_pos_hold_invoice.cust_name WHERE  "+company+".mr_pos_hold_invoice.reg_num='"+reg_num+"' and delivery_user='"+delivery_boy+"' and mr_pos_hold_invoice.receipt_no IN ('"+invoicelist+"') group by receipt_no";
		   
		    
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
		    JRDesignQuery jq=new JRDesignQuery();
			jq.setText(query);
			jd.setQuery(jq);
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("reg_num",reg_num);
				param.put("delivery_boy",delivery_boy);
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

