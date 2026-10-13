package report_master;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Xclose_sale {
	//private static String printer;
	private static DecimalFormat df2;
	private static int decimal;
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		report("mr_pro","S01","SHABEER", null, "S0101","2");
	}
	public static void report(String company, String store_name, String username,String version,String reg_nme,String decimal) throws ClassNotFoundException, SQLException{
		get_store_validate_dt(company,store_name,username, reg_nme);
		Connection con=(Connection) Dbsetup.dbconn.getConnection();
		Statement statement;
		Statement statement1;
		Statement statement2;
		HashMap<String, Integer> cardqty = new HashMap<String, Integer>();
		List<String> cardlist = new ArrayList<String>();
		try {
			InetAddress addr = null;
		    try {
				addr = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    String sys_name = addr.getHostName();
			
		    LocalDateTime myDateObj = LocalDateTime.now();  
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		    final Calendar cal = Calendar.getInstance();
		    final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
		    String formattedDate = myDateObj.format(myFormatObj);  
		   // get_register_name(company, reg_nme);
			statement = con.createStatement();
			statement1 = con.createStatement();
			statement2 = con.createStatement();
			ResultSet resultSet00 = statement.executeQuery("select * from "+company+".mr_item_master where open_qty='1'");
			while(resultSet00.next())
			{
				cardlist.add(resultSet00.getString("item_code"));
				
			}
			ResultSet resultSet0 = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash_items where ref_reg_num='"+reg_nme+"'");
			while(resultSet0.next())
			{
				ResultSet resultSet1 = statement1.executeQuery("select * from "+company+".mr_item_master where item_code='"+resultSet0.getString("item_code")+"' and open_qty='1' ");
				if(resultSet1.next())
				{
					for (String str : cardlist) {
					    if (resultSet1.getString("item_code").equals(str)) {
					    	if(cardqty.containsKey(str))
					    	{
					    		cardqty.put(str, cardqty.get(str) + 1);
					    	}
					    	else
					    	{
					    		cardqty.put(str, 1);
					    	}
					    } 
					}
				}
			}
			String no_invo="";
			ResultSet resultSet21 = statement.executeQuery("select count(*) as ttl_cnt from "+company+".mr_pos_settlement_cash where reg_num='"+reg_nme+"' ");
			if(resultSet21.next())
			{
				no_invo=resultSet21.getString("ttl_cnt");
			}
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_close_pay_list where ref_reg_num='"+reg_nme+"' and status='0' ");
			if(resultSet.next())
			{
				String querydel="delete from "+company+".mr_close_pay_list where ref_reg_num='"+reg_nme+"' and status='0' ";
				statement1.executeUpdate(querydel);
				String query="insert into "+company+".mr_close_pay_list (`ref_reg_num`, `username`, `store`, `computer`, `date_time`,remarks, `created_date`,invoice_num) values('"+reg_nme+"','"+username+"','"+store_name+"','"+sys_name+"','"+times.format(cal.getTime())+"','"+times.format(cal.getTime())+"','"+formattedDate+"','"+no_invo+"') ";
				statement.executeUpdate(query);
				ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' and status='0' ");
				if(resultSet1.next())
				{
					String querydel1="delete from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' and status='0' ";
					statement1.executeUpdate(querydel1);
					ResultSet resultSet31 = statement.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"'");
					while(resultSet31.next())
					{
						String paycode=resultSet31.getString("paycode");
						float sales_amt=0;
						float vat=0;
						float ex_rate=0;
						float diff=0;
						String get_ttl_amt= "select * from "+company+".mr_pos_settlement_cash where type='"+paycode+"' AND reg_num='"+reg_nme+"' ";
						ResultSet resultSet3 = statement1.executeQuery(get_ttl_amt);
						while(resultSet3.next())
						{
							sales_amt+=Float.parseFloat(resultSet3.getString("total"));
							vat+=Float.parseFloat(resultSet3.getString("vat"));
						}
						String get_paycode_list= "select * from "+company+".mr_paycode_settlement where paycode_name='"+paycode+"' ";
						ResultSet resultSet4 = statement1.executeQuery(get_paycode_list);
						while(resultSet4.next())
						{
							sales_amt+=Float.parseFloat(resultSet4.getString("paycode_amt"));
						}
						String get_store_paycode_list= "select * from "+company+".mr_store_paycodes where paycode='"+paycode+"' ";
						ResultSet resultSet5 = statement1.executeQuery(get_store_paycode_list);
						if(resultSet5.next())
						{
							ex_rate=Float.parseFloat(resultSet5.getString("ex_rate"));
							
						}
						String open_amt = "";
						ResultSet resultSet2 = statement1.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"' and paycode='"+paycode+"' ");
						if(resultSet2.next())
						{
							open_amt=resultSet2.getString("amount");
							
						}
						String get_card_list= "select * from "+company+".mr_item_master where item_name='"+paycode+"'";
						ResultSet resultSet13 = statement1.executeQuery(get_card_list);
						if(resultSet13.next())
						{
							String code=resultSet13.getString("item_code");
							if(cardqty.containsKey(code))
					    	{
					    		sales_amt=cardqty.get(code);
					    	}
						}
						diff=0;
						String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt,vat_amt) values('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','"+paycode+"','"+open_amt+"','"+df2.format(sales_amt)+"','"+df2.format(Float.parseFloat(open_amt)+sales_amt)+"','0','"+ex_rate+"','"+diff+"','"+formattedDate+"','"+((sales_amt)-(vat))*ex_rate+"','"+vat+"') ";
						statement2.executeUpdate(add_open_itm);
					}
					String get_paylist="select * from "+company+".mr_paylist where ref_reg_no='"+reg_nme+"' and status='0'  ";
					ResultSet resultSet6 = statement1.executeQuery(get_paylist);
					float pay_in=0;
					float pay_out=0;
					while(resultSet6.next())
					{
						if(resultSet6.getString("type").equals("payin"))
						{
							pay_in+=Float.parseFloat(resultSet6.getString("amount")) ;
						}
						else{
							pay_out+=Float.parseFloat(resultSet6.getString("amount")) ;
						}
					}
					String get_cust_paylist= "select * from "+company+".mr_customer_payment where ref_reg_num='"+reg_nme+"'  and status='0'  ";
					ResultSet resultSet7 = statement1.executeQuery(get_cust_paylist);
					float cust_amt=0;
					while(resultSet7.next())
					{
						cust_amt+=Float.parseFloat(resultSet7.getString("amount")) ;
					}
					String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`) values('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','CUSTOMERPAYMENTS','0','"+df2.format(cust_amt)+"','"+df2.format(cust_amt)+"','0','1','"+df2.format(0-cust_amt)+"','"+formattedDate+"'),('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','PAYIN','0','"+df2.format(pay_in)+"','"+df2.format(pay_in)+"','0','1','"+df2.format(0-pay_in)+"','"+formattedDate+"'),('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','PAYOUT','0','"+df2.format(pay_out)+"','"+df2.format(pay_out)+"','0','1','"+df2.format(0-pay_out)+"','"+formattedDate+"') ";
					statement1.executeUpdate(add_open_itm);
				}
				else
				{
					ResultSet resultSet31 = statement.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"'");
					while(resultSet31.next())
					{
						String paycode=resultSet31.getString("paycode");
						float sales_amt=0;
						float vat=0;
						float ex_rate=0;
						float diff=0;
						String get_ttl_amt= "select * from "+company+".mr_pos_settlement_cash where type='"+paycode+"' AND reg_num='"+reg_nme+"' ";
						ResultSet resultSet3 = statement.executeQuery(get_ttl_amt);
						while(resultSet3.next())
						{
							sales_amt+=Float.parseFloat(resultSet3.getString("total"));
							vat+=Float.parseFloat(resultSet3.getString("vat"));
						}
						String get_paycode_list= "select * from "+company+".mr_paycode_settlement where paycode_name='"+paycode+"' ";
						ResultSet resultSet4 = statement.executeQuery(get_paycode_list);
						while(resultSet4.next())
						{
							sales_amt+=Float.parseFloat(resultSet4.getString("paycode_amt"));
						}
						String get_store_paycode_list= "select * from "+company+".mr_store_paycodes where paycode='"+paycode+"' ";
						ResultSet resultSet5 = statement.executeQuery(get_store_paycode_list);
						if(resultSet5.next())
						{
							ex_rate=Float.parseFloat(resultSet5.getString("ex_rate"));
							
						}
						String open_amt = "";
						ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"' and paycode='"+paycode+"' ");
						if(resultSet2.next())
						{
							open_amt=resultSet2.getString("amount");
							
						}
						String get_card_list= "select * from "+company+".mr_item_master where item_name='"+paycode+"'";
						ResultSet resultSet13 = statement.executeQuery(get_card_list);
						if(resultSet13.next())
						{
							String code=resultSet13.getString("item_code");
							if(cardqty.containsKey(code))
					    	{
					    		sales_amt=cardqty.get(code);
					    	}
						}
						diff=0;
						String time=times.format(cal.getTime());
						String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt,vat_amt) values('"+reg_nme+"','"+formattedDate+" "+time+"','"+paycode+"','"+open_amt+"','"+df2.format(sales_amt)+"','"+df2.format(Float.parseFloat(open_amt)+sales_amt)+"','0','"+ex_rate+"','"+diff+"','"+formattedDate+"','"+((sales_amt)-(vat))*ex_rate+"','"+vat+"') ";
						statement2.executeUpdate(add_open_itm);
					}
					String get_paylist="select * from "+company+".mr_paylist where ref_reg_no='"+reg_nme+"' and status='0'  ";
					ResultSet resultSet6 = statement.executeQuery(get_paylist);
					float pay_in=0;
					float pay_out=0;
					while(resultSet6.next())
					{
						if(resultSet6.getString("type").equals("payin"))
						{
							pay_in+=Float.parseFloat(resultSet6.getString("amount")) ;
						}
						else{
							pay_out+=Float.parseFloat(resultSet6.getString("amount")) ;
						}
					}
					String get_cust_paylist= "select * from "+company+".mr_customer_payment where ref_reg_num='"+reg_nme+"'  and status='0'  ";
					ResultSet resultSet7 = statement.executeQuery(get_cust_paylist);
					float cust_amt=0;
					while(resultSet7.next())
					{
						cust_amt+=Float.parseFloat(resultSet7.getString("amount")) ;
					}
					String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`) values('"+reg_nme+"','"+formattedDate+" ,"+times.format(cal.getTime())+"','CUSTOMERPAYMENTS','0','"+df2.format(cust_amt)+"','"+df2.format(cust_amt)+"','0','1','"+df2.format(0-cust_amt)+"','"+formattedDate+"'),('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','PAYIN','0','"+df2.format(pay_in)+"','"+df2.format(pay_in)+"','0','1','"+df2.format(0-pay_in)+"','"+formattedDate+"'),('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','PAYOUT','0','"+df2.format(pay_out)+"','"+df2.format(pay_out)+"','0','1','"+df2.format(0-pay_out)+"','"+formattedDate+"') ";
					statement.executeUpdate(add_open_itm);
				}
				//report_master.Xreports.report(reg_nme, con, "XRPT",printer,"");
			}
			else
			{
				String query="insert into "+company+".mr_close_pay_list (`ref_reg_num`, `username`, `store`, `computer`, `date_time`,remarks, `created_date`,invoice_num) values('"+reg_nme+"','"+username+"','"+store_name+"','"+sys_name+"','"+times.format(cal.getTime())+"','"+times.format(cal.getTime())+"','"+formattedDate+"','"+no_invo+"') ";
				statement.executeUpdate(query);
				ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' and status='0' ");
				if(resultSet1.next())
				{
					String querydel1="delete from "+company+".mr_close_pay_list_items where ref_reg_num='"+reg_nme+"' and status='0' ";
					statement1.executeUpdate(querydel1);
					ResultSet resultSet31 = statement.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"'");
					while(resultSet31.next())
					{
						String paycode=resultSet31.getString("paycode");
						float sales_amt=0;
						float vat=0;
						float ex_rate=0;
						float diff=0;
						String get_ttl_amt= "select * from "+company+".mr_pos_settlement_cash where type='"+paycode+"' AND reg_num='"+reg_nme+"' ";
						ResultSet resultSet3 = statement.executeQuery(get_ttl_amt);
						while(resultSet3.next())
						{
							sales_amt+=Float.parseFloat(resultSet3.getString("total"));
							vat+=Float.parseFloat(resultSet3.getString("vat"));
						}
						String get_paycode_list= "select * from "+company+".mr_paycode_settlement where paycode_name='"+paycode+"' ";
						ResultSet resultSet4 = statement.executeQuery(get_paycode_list);
						while(resultSet4.next())
						{
							sales_amt+=Float.parseFloat(resultSet4.getString("paycode_amt"));
						}
						String get_store_paycode_list= "select * from "+company+".mr_store_paycodes where paycode='"+paycode+"' ";
						ResultSet resultSet5 = statement.executeQuery(get_store_paycode_list);
						if(resultSet5.next())
						{
							ex_rate=Float.parseFloat(resultSet5.getString("ex_rate"));
							
						}
						String open_amt = "";
						ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"' and paycode='"+paycode+"' ");
						if(resultSet2.next())
						{
							open_amt=resultSet2.getString("amount");
							
						}
						String get_card_list= "select * from "+company+".mr_item_master where item_name='"+paycode+"'";
						ResultSet resultSet13 = statement.executeQuery(get_card_list);
						if(resultSet13.next())
						{
							String code=resultSet13.getString("item_code");
							if(cardqty.containsKey(code))
					    	{
					    		sales_amt=cardqty.get(code);
					    	}
						}
						diff=0;
						String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt,vat_amt) values('"+reg_nme+"','"+formattedDate+", "+times.format(cal.getTime())+"','"+paycode+"','"+open_amt+"','"+df2.format(sales_amt)+"','"+df2.format(Float.parseFloat(open_amt)+sales_amt)+"','0','"+ex_rate+"','"+diff+"','"+formattedDate+"','"+((sales_amt)-(vat))*ex_rate+"','"+vat+"') ";
						statement1.executeUpdate(add_open_itm);
					}
					String get_paylist="select * from "+company+".mr_paylist where ref_reg_no='"+reg_nme+"' and status='0'  ";
					ResultSet resultSet6 = statement.executeQuery(get_paylist);
					float pay_in=0;
					float pay_out=0;
					while(resultSet6.next())
					{
						if(resultSet6.getString("type").equals("payin"))
						{
							pay_in+=Float.parseFloat(resultSet6.getString("amount")) ;
						}
						else{
							pay_out+=Float.parseFloat(resultSet6.getString("amount")) ;
						}
					}
					String get_cust_paylist= "select * from "+company+".mr_customer_payment where ref_reg_num='"+reg_nme+"'  and status='0'  ";
					ResultSet resultSet7 = statement.executeQuery(get_cust_paylist);
					float cust_amt=0;
					while(resultSet7.next())
					{
						cust_amt+=Float.parseFloat(resultSet7.getString("amount")) ;
					}
					String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`) values('"+reg_nme+"','"+formattedDate+", "+times.format(cal.getTime())+"','CUSTOMERPAYMENTS','0','"+df2.format(cust_amt)+"','"+df2.format(cust_amt)+"','0','1','"+df2.format(0-cust_amt)+"','"+formattedDate+"'),('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','PAYIN','0','"+df2.format(pay_in)+"','"+df2.format(pay_in)+"','0','1','"+df2.format(0-pay_in)+"','"+formattedDate+"'),('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','PAYOUT','0','"+df2.format(pay_out)+"','"+df2.format(pay_out)+"','0','1','"+df2.format(0-pay_out)+"','"+formattedDate+"') ";
					statement.executeUpdate(add_open_itm);
				}
				else
				{
					
						ResultSet resultSet31 = statement.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"'");
						while(resultSet31.next())
						{
							String paycode=resultSet31.getString("paycode");
							float sales_amt=0;
							float vat=0;
							float ex_rate=0;
							float diff=0;
							String get_ttl_amt= "select * from "+company+".mr_pos_settlement_cash where type='"+paycode+"' AND reg_num='"+reg_nme+"' ";
							ResultSet resultSet3 = statement.executeQuery(get_ttl_amt);
							while(resultSet3.next())
							{
								sales_amt+=Float.parseFloat(resultSet3.getString("total"));
								vat+=Float.parseFloat(resultSet3.getString("vat"));
							}
							String get_paycode_list= "select * from "+company+".mr_paycode_settlement where paycode_name='"+paycode+"' ";
							ResultSet resultSet4 = statement.executeQuery(get_paycode_list);
							while(resultSet4.next())
							{
								sales_amt+=Float.parseFloat(resultSet4.getString("paycode_amt"));
							}
							String get_store_paycode_list= "select * from "+company+".mr_store_paycodes where paycode='"+paycode+"' ";
							ResultSet resultSet5 = statement.executeQuery(get_store_paycode_list);
							if(resultSet5.next())
							{
								ex_rate=Float.parseFloat(resultSet5.getString("ex_rate"));
								
							}
							String open_amt = "";
							ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_open_items where ref_reg_num='"+reg_nme+"' and paycode='"+paycode+"' ");
							if(resultSet2.next())
							{
								open_amt=resultSet2.getString("amount");
								
							}
							String get_card_list= "select * from "+company+".mr_item_master where item_name='"+paycode+"'";
							ResultSet resultSet13 = statement.executeQuery(get_card_list);
							if(resultSet13.next())
							{
								String code=resultSet13.getString("item_code");
								if(cardqty.containsKey(code))
						    	{
						    		sales_amt=cardqty.get(code);
						    	}
							}
							diff=0;
							String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`,sale_amt,vat_amt) values('"+reg_nme+"','"+formattedDate+" ,"+times.format(cal.getTime())+"','"+paycode+"','"+open_amt+"','"+df2.format(sales_amt)+"','"+df2.format(Float.parseFloat(open_amt)+sales_amt)+"','0','"+ex_rate+"','"+diff+"','"+formattedDate+"','"+((sales_amt)-(vat))*ex_rate+"','"+vat+"') ";
							statement.executeUpdate(add_open_itm);
						}
						String get_paylist="select * from "+company+".mr_paylist where ref_reg_no='"+reg_nme+"' and status='0'  ";
						ResultSet resultSet6 = statement.executeQuery(get_paylist);
						float pay_in=0;
						float pay_out=0;
						while(resultSet6.next())
						{
							if(resultSet6.getString("type").equals("payin"))
							{
								pay_in+=Float.parseFloat(resultSet6.getString("amount")) ;
							}
							else{
								pay_out+=Float.parseFloat(resultSet6.getString("amount")) ;
							}
						}
						String get_cust_paylist= "select * from "+company+".mr_customer_payment where ref_reg_num='"+reg_nme+"'  and status='0'  ";
						ResultSet resultSet7 = statement.executeQuery(get_cust_paylist);
						float cust_amt=0;
						while(resultSet7.next())
						{
							cust_amt+=Float.parseFloat(resultSet7.getString("amount")) ;
						}
						String add_open_itm="insert into "+company+".mr_close_pay_list_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `settle_amt`, `ex_rate`, `varience`, `created_date`) values('"+reg_nme+"','"+formattedDate+" ,"+times.format(cal.getTime())+"','CUSTOMERPAYMENTS','0','"+df2.format(cust_amt)+"','"+df2.format(cust_amt)+"','0','1','"+df2.format(0-cust_amt)+"','"+formattedDate+"'),('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','PAYIN','0','"+df2.format(pay_in)+"','"+df2.format(pay_in)+"','0','1','"+df2.format(0-pay_in)+"','"+formattedDate+"'),('"+reg_nme+"','"+formattedDate+" "+times.format(cal.getTime())+"','PAYOUT','0','"+df2.format(pay_out)+"','"+df2.format(pay_out)+"','0','1','"+df2.format(0-pay_out)+"','"+formattedDate+"') ";
						statement.executeUpdate(add_open_itm);
					
				}
				//report_master.Xreports.report(reg_nme, con, "XRPT",printer,"");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
//	public static void get_register_name(String company, String reg_nme){
//		try {
//			Connection con=Dbsetup.dbconn.getConnection();
//			Statement statement=con.createStatement();  
//			ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_nme+"' ");
//			if(resultSet2.next())
//			{
//				printer=resultSet2.getString("printer1");
//			}
//			con.close();
//		} catch (Exception e) {
//			System.err.println(e);
//		}
//	}
	public static void get_store_validate_dt(String company,String store_name,String username, String posreg)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store where store_id='"+store_name+"' ");
			if(resultSet.next())
			{
				
				decimal=Integer.parseInt(resultSet.getString("decimal_count"));
				if(decimal==2)
				{
					df2=new DecimalFormat("0.00");
				}
				else
				{
					df2=new DecimalFormat("0.000");
				}
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
