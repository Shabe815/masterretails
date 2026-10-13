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

public class stock_sts {

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report(Dbsetup.dbconn.getConnection(),"0","0","0","","ALL","ALL","");
	}
	public static void report(Connection con,String qtyless,String qtyOH,String qtygreat,String name,String cat,String dept, String company) throws ClassNotFoundException, SQLException{
		try {
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
				categorystr="and mr_tmp_item_master.category='"+cat+"'";
			}
			if(!dept.equals("ALL"))
			{
				departmentstr="and mr_tmp_item_master.department='"+dept+"'";
			}
			if(!name.equals(""))
			{
				itemname="and (mr_tmp_item_master.item_name like '%"+name+"%' or mr_tmp_item_master.item_code like '%"+name+"%' or mr_tmp_item_master.barcode='"+name+"') ";
			}
			String query="select * from "+company+".mr_store inner join config_db.company on config_db.company.company_name="+company+".mr_store.store_name inner join  "+company+".mr_tmp_item_master  WHERE unit!='' "+qtybased+"  "+categorystr+" "+departmentstr+" "+itemname+" group by item_code order by item_code ASC ";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);

	        JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
			
			File configFile = new File("C:/MasterRetails/report_assign.properties");
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    String report_path = props.getProperty("report_path");
		    String format_name = props.getProperty("stock_sts");
		    reader.close();
		    
		    JasperDesign jd= JRXmlLoader.load(report_path+""+format_name);
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("qtyless",qtyless);
			param.put("qtyOH",qtyOH);
			param.put("qtygreat",qtygreat);
			param.put("name",name);
			param.put("cat",cat);
			param.put("dept",dept);
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
