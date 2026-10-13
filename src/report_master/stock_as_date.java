package report_master;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class stock_as_date {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report(Dbsetup.dbconn.getConnection(),"0","0","0","","ALL","ALL","","");
	}
	public static void report(Connection con,String qtyless,String qtyOH,String qtygreat,String name,String cat,String dept, String stdate, String company) throws ClassNotFoundException, SQLException{
		try {
			Statement stm = con.createStatement();
			String qtyOHlessstr = "";
			String qtyOHstr = "";
			String qtyOHgreatstr = "";
			String categorystr = "";
			String departmentstr = "";
			String itemname = "";
			if(!qtyless.equals(""))
			{
				qtyOHlessstr="qty < '0' ";
			}
			if(!qtyOH.equals(""))
			{
				qtyOHstr="qty = '0' ";
			}
			if(!qtygreat.equals(""))
			{
				qtyOHgreatstr="qty > '0' ";
			}
			String qtybased="";
			if(!qtyless.equals(""))
			{
				if(!qtyOH.equals(""))
				{
					
					if(!qtygreat.equals(""))
					{
						qtybased="and ("+qtyOHlessstr+" or "+qtyOHstr+" or "+qtyOHgreatstr+" )";
					}
					else
					{
						qtybased="and ("+qtyOHlessstr+" or "+qtyOHstr+" )";
					}
				}
				else
				{
					if(!qtygreat.equals(""))
					{
						qtybased="and ("+qtyOHlessstr+" or "+qtyOHgreatstr+" )";
					}
					else
					{
						qtybased="and ("+qtyOHlessstr+" )";
					}
				}
			}
			else
			{
				if(!qtyOH.equals(""))
				{
					if(!qtygreat.equals(""))
					{
						qtybased="and ("+qtyOHstr+" or "+qtyOHgreatstr+" )";
					}
					else
					{
						qtybased="and ("+qtyOHstr+" )";
					}
				}
				else
				{
					if(!qtygreat.equals(""))
					{
						qtybased="and ("+qtyOHgreatstr+" )";
					}
				}
			}
			
			
			if(!cat.equals("ALL"))
			{
				categorystr="and t1.category='"+cat+"'";
			}
			if(!dept.equals("ALL"))
			{
				departmentstr="and t1.department='"+dept+"'";
			}
			if(!name.equals(""))
			{
				itemname=" and (t1.item_name like '%"+name+"%' or h1.itemcode like '%"+name+"%'  or h1.barcode = '"+name+"') ";
			}
			String stock_id="1";
			Statement statement=con.createStatement(); 
			//String get_date="SELECT id FROM "+company+".mr_item_master_history where `trans_type`='STOCK'  group by created_date order by created_date desc LIMIT 1";
			String get_date="SELECT created_date from "+company+".mr_item_master_history where trans_type='STOCK' group by created_date order by created_date desc LIMIT 1";
			ResultSet resultSet12 = statement.executeQuery(get_date);
			if(resultSet12.next())
			{
				stock_id=resultSet12.getString("created_date");
			}
			String query="";
			if(stock_id.equals(""))
			{
				query="SELECT *,t1.barcode as bar_code,itemcode,item_name,item_code,t1.cost as cost_ttl ,SUM(CASE WHEN h1.trans_type = 'STOCK' THEN h1.qty*h1.cf ELSE 0 END) AS Stock,SUM(CASE WHEN h1.trans_type = 'OPEN' THEN h1.qty*h1.cf ELSE 0 END) AS OPEN,SUM(CASE WHEN h1.trans_type = 'PO' THEN h1.qty*h1.cf ELSE 0 END) AS PO,SUM(CASE WHEN h1.trans_type = 'AD' THEN h1.qty*h1.cf ELSE 0 END) AS AD,SUM(CASE WHEN h1.trans_type = 'SA' THEN h1.qty*h1.cf ELSE 0 END) AS SA,SUM(CASE WHEN h1.trans_type = 'PR' THEN h1.qty*h1.cf ELSE 0 END) AS PR,SUM(CASE WHEN h1.trans_type = 'IN' THEN h1.qty*h1.cf ELSE 0 END) AS INs,t1.qty,(SUM(CASE WHEN h1.trans_type = 'STOCK' or h1.trans_type = 'OPEN' or h1.trans_type = 'PO' or h1.trans_type = 'AD' THEN h1.qty*h1.cf ELSE 0 END)-SUM(CASE WHEN h1.trans_type = 'SA' or h1.trans_type = 'PR' or h1.trans_type = 'IN' THEN h1.qty*h1.cf ELSE 0 END)) as ttl FROM "+company+".mr_item_master_history h1 join "+company+".mr_tmp_item_master t1 on t1.item_code=h1.itemcode and h1.barcode=t1.barcode join "+company+".mr_store where h1.created_date <= '"+stdate+"' "+categorystr+" "+departmentstr+" "+itemname+"  GROUP BY h1.itemcode "+qtybased+" ORDER BY h1.itemcode  ASC";
			}
			else
			{
				query="SELECT *,t1.barcode as bar_code,itemcode,item_name,item_code,t1.cost as cost_ttl ,SUM(CASE WHEN h1.trans_type = 'STOCK' THEN h1.qty*h1.cf ELSE 0 END) AS Stock,SUM(CASE WHEN h1.trans_type = 'OPEN' THEN h1.qty*h1.cf ELSE 0 END) AS OPEN,SUM(CASE WHEN h1.trans_type = 'PO' THEN h1.qty*h1.cf ELSE 0 END) AS PO,SUM(CASE WHEN h1.trans_type = 'AD' THEN h1.qty*h1.cf ELSE 0 END) AS AD,SUM(CASE WHEN h1.trans_type = 'SA' THEN h1.qty*h1.cf ELSE 0 END) AS SA,SUM(CASE WHEN h1.trans_type = 'PR' THEN h1.qty*h1.cf ELSE 0 END) AS PR,SUM(CASE WHEN h1.trans_type = 'IN' THEN h1.qty*h1.cf ELSE 0 END) AS INs,t1.qty,(SUM(CASE WHEN h1.trans_type = 'STOCK' or h1.trans_type = 'OPEN' or h1.trans_type = 'PO' or h1.trans_type = 'AD' THEN h1.qty*h1.cf ELSE 0 END)-SUM(CASE WHEN h1.trans_type = 'SA' or h1.trans_type = 'PR' or h1.trans_type = 'IN' THEN h1.qty*h1.cf ELSE 0 END)) as ttl FROM "+company+".mr_item_master_history h1 join "+company+".mr_tmp_item_master t1 on t1.item_code=h1.itemcode and h1.barcode=t1.barcode join "+company+".mr_store where h1.created_date between '"+stock_id+"' and '"+stdate+"' "+categorystr+" "+departmentstr+" "+itemname+"  GROUP BY h1.itemcode "+qtybased+" ORDER BY h1.itemcode  ASC";
			}
			ResultSet rs = stm.executeQuery(query);

	        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
			
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String report_path = props.getProperty("report_path");
		    String format_name = props.getProperty("stock_as_date");
		    reader.close();
		    
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("qtyless",qtyless);
			param.put("qtyOH",qtyOH);
			param.put("qtygreat",qtygreat);
			param.put("name",name);
			param.put("cat",cat);
			param.put("dept",dept);
			param.put("stdate",stdate);
		    JasperReport jasperReport = JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,param, jrRS);
            JasperViewer.viewReport(jasperPrint, false);
		    } catch (Exception e) {
		        System.err.println("Cannot connect to database server");
		        System.err.println(e.getMessage());
		        e.printStackTrace();
		    }
	}

}
