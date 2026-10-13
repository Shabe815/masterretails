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

public class Register_summary {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("2020-05-01",  "2020-07-10",Dbsetup.dbconn.getConnection(),"","ADMIN001","","");
	}
	public static void report(String date1,  String date2,Connection con, String reg_list, String user_list,String company, String type) throws ClassNotFoundException, SQLException{
		try 
		{
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    String report_path = props.getProperty("report_path");
		    String format_name ="";
		    String query="";
		    if(type.equals("0"))
		    {
		    	format_name = props.getProperty("Register_Summary");
		    	query="select *,sum(case when paycode='CASH' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (sales_amt)  else 0 end) as cash,sum(case when paycode='CASH' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (`settle_amt`)  else 0 end) as cash_close,sum(case when paycode='CASH' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (`varience`)  else 0 end) as cash_varnce,sum(case when paycode='A/R' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then sales_amt  else 0 end) as ar,sum(case when paycode='A/R' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then settle_amt  else 0 end) as ar_close,sum(case when paycode='A/R' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then varience  else 0 end) as ar_varnce,sum(case when paycode='VISA' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (sales_amt)  else 0 end) as visa,sum(case when paycode='VISA' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (settle_amt)  else 0 end) as visa_close,sum(case when paycode='VISA' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (varience)  else 0 end) as visa_varnce,sum(case when paycode='OMANI RIYAL' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (sales_amt)  else 0 end) as omani,sum(case when paycode='OMANI RIYAL' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (settle_amt)  else 0 end) as omani_close,sum(case when paycode='OMANI RIYAL' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (varience)  else 0 end) as omani_varnce,sum(case when paycode='SAUDI RIYAL' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (sales_amt)  else 0 end) as saudi,sum(case when paycode='SAUDI RIYAL' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (settle_amt)  else 0 end) as saudi_close,sum(case when paycode='SAUDI RIYAL' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (varience)  else 0 end) as saudi_varnce,sum(case when paycode='US$' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (sales_amt)  else 0 end) as us_dol,sum(case when paycode='US$' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (settle_amt)  else 0 end) as us_close,sum(case when paycode='US$' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (varience)  else 0 end) as us_varnce,sum(case when paycode='TALABAT' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then sales_amt  else 0 end) as talabat,sum(case when paycode='TALABAT' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then settle_amt  else 0 end) as talabat_close,sum(case when paycode='TALABAT' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then varience  else 0 end) as talabat_varnce  from "+company+".mr_store  inner join "+company+".mr_close_pay_list_history on "+company+".mr_close_pay_list_history.store="+company+".mr_store.store_id  inner join "+company+".mr_close_pay_list_history_items on mr_close_pay_list_history.ref_reg_num=mr_close_pay_list_history_items.ref_reg_num  where "+company+".mr_close_pay_list_history.ref_reg_num IN ('"+reg_list+"') and "+company+".mr_close_pay_list_history.username IN ("+user_list+") and (mr_close_pay_list_history.date_time between '"+date1+"' and '"+date2+"') group by mr_close_pay_list_history.date_time,mr_close_pay_list_history.ref_reg_num order by mr_close_pay_list_history.close_date asc";
		    }
		    else
		    {
		    	format_name = props.getProperty("Register_Summary_receipt");
		    	query="SELECT store_name,addr1,addr2,country,city,ph,trn,store_id,i.paycode,SUM(i.openings) AS opening,SUM(i.sales_amt) AS sale,SUM(i.settle_amt ) AS sale_close,SUM(i.varience) AS sale_varnce,sum(h.total_vat) as vat FROM "+company+".mr_close_pay_list_history h JOIN "+company+".mr_close_pay_list_history_items i ON h.ref_reg_num = i.ref_reg_num AND h.date_time = i.date_time JOIN "+company+".mr_store s ON h.store = s.store_id WHERE h.ref_reg_num IN ('"+reg_list+"') AND h.username IN ("+user_list+") AND h.date_time BETWEEN '"+date1+"' and '"+date2+"' GROUP BY i.paycode ORDER BY i.paycode ASC;";
		    }
		    reader.close();
		    //String query="select *,sum(case when paycode='CASH' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (sales_amt)  else 0 end) as cash,sum(case when paycode='CASH' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (`settle_amt`)  else 0 end) as cash_close,sum(case when paycode='CASH' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (`varience`)  else 0 end) as cash_varnce,sum(case when paycode='A/R' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then sales_amt  else 0 end) as ar,sum(case when paycode='A/R' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then settle_amt  else 0 end) as ar_close,sum(case when paycode='A/R' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then varience  else 0 end) as ar_varnce,sum(case when paycode='VISA' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (sales_amt)  else 0 end) as visa,sum(case when paycode='VISA' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (settle_amt)  else 0 end) as visa_close,sum(case when paycode='VISA' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (varience)  else 0 end) as visa_varnce,sum(case when paycode='OMANI RIYAL' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (sales_amt)  else 0 end) as omani,sum(case when paycode='OMANI RIYAL' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (settle_amt)  else 0 end) as omani_close,sum(case when paycode='OMANI RIYAL' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (varience)  else 0 end) as omani_varnce,sum(case when paycode='SAUDI RIYAL' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (sales_amt)  else 0 end) as saudi,sum(case when paycode='SAUDI RIYAL' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (settle_amt)  else 0 end) as saudi_close,sum(case when paycode='SAUDI RIYAL' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (varience)  else 0 end) as saudi_varnce,sum(case when paycode='US$' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (sales_amt)  else 0 end) as us_dol,sum(case when paycode='US$' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (settle_amt)  else 0 end) as us_close,sum(case when paycode='US$' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then (varience)  else 0 end) as us_varnce,sum(case when paycode='TALABAT' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then sales_amt  else 0 end) as talabat,sum(case when paycode='TALABAT' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then settle_amt  else 0 end) as talabat_close,sum(case when paycode='TALABAT' and mr_close_pay_list_history_items.date_time=mr_close_pay_list_history.date_time then varience  else 0 end) as talabat_varnce  from "+company+".mr_store  inner join "+company+".mr_close_pay_list_history on "+company+".mr_close_pay_list_history.store="+company+".mr_store.store_id  inner join "+company+".mr_close_pay_list_history_items on mr_close_pay_list_history.ref_reg_num=mr_close_pay_list_history_items.ref_reg_num  where "+company+".mr_close_pay_list_history.ref_reg_num IN ('"+reg_list+"') and "+company+".mr_close_pay_list_history.username IN ("+user_list+") and (mr_close_pay_list_history.close_date between '"+date1+"' and '"+date2+"') group by mr_close_pay_list_history.ref_id,username order by close_date asc";
		    
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
		   JRDesignQuery jq=new JRDesignQuery();
			jq.setText(query);
			jd.setQuery(jq);
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("date1",date1);
				param.put("date2",date2);
				param.put("reg_list",reg_list);
				param.put("user_list",user_list);
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

