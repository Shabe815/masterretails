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

public class jv_details {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("JV-19-01-000031", Dbsetup.dbconn.getConnection(),"OPEN","","","",null,"");
	}
	public static void report(String jv_num, Connection con,String status, String company, String start, String end, String list,String type) throws ClassNotFoundException, SQLException{
		try {
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    String liststr="";
		    String format_name ="";
		    String query="";
		    if(!list.equals(""))
		    {
		    	liststr=" and "+company+".mr_jv_details.id IN ("+list+")";
		    }
		    
		    if(type.equals(""))
		    {
		    	query="select *,"+company+".mr_jv_details.remark as remarks,"+company+".mr_jv_header.created_date as jv_date from "+company+".mr_store inner join "+company+".mr_jv_header on "+company+".mr_jv_header.store_id="+company+".mr_store.store_id inner join "+company+".mr_jv_details on "+company+".mr_jv_details.ref_jv_num="+company+".mr_jv_header.jv_num where "+company+".mr_jv_header.jv_num='"+jv_num+"' "+liststr+" order by mr_jv_details.id asc ";
		    	format_name = props.getProperty("jv_details");
		    }
		    else
		    {
		    	query="select *,sum(cr_amt)as cr_amt_ttl,sum(dr_amt) as dr_amt_ttl,mr_pro.mr_jv_header.created_date as jv_date from "+company+".mr_store inner join "+company+".mr_jv_header on "+company+".mr_jv_header.store_id="+company+".mr_store.store_id inner join "+company+".mr_jv_details on "+company+".mr_jv_details.ref_jv_num="+company+".mr_jv_header.jv_num where "+company+".mr_jv_header.jv_num='"+jv_num+"'  and mr_jv_details.status!='VOID' group by ac_code order by mr_jv_details.id asc ";
		    	format_name = props.getProperty("jv_details_summary");
		    }
		    
		    String report_path = props.getProperty("report_path");
		    reader.close();
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
		    JRDesignQuery jq=new JRDesignQuery();
			jq.setText(query);
			jd.setQuery(jq);
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("jv_num",jv_num);
				param.put("status",status);
				param.put("mr_pro",company);
				param.put("itemlist",list);
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
