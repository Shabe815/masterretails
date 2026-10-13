package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.JSONException;

import CafePro.Mainpage;

import javax.swing.border.LineBorder;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

import java.awt.Font;

public class close_progress {

	private JFrame frame;
	private static int count=0;
    private JProgressBar progressBar;
    private JProgressBar progressBar_1;
    private String Version = "MR Ver_1.1.0";
    
   
    public String company;
    public String reg_name;
    public String username;
    public String store_name;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					close_progress window = new close_progress("mr_pro","S0102", "SHABEER", "S01");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws JSONException
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public close_progress(String company, String reg_nme,String username,String store_name) throws JSONException, IOException, SQLException, ClassNotFoundException {
		initialize(company, reg_nme, username, store_name);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws JSONException
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize(String companyname, String reg_nme,String user,String store)throws JSONException, IOException, SQLException, ClassNotFoundException {
		frame = new JFrame(Version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.setBounds(100, 100, 500, 150);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		
		company=companyname;
	    reg_name=reg_nme;
	    username=user;
	    store_name=store;
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(102, 51, 204));
        progressBar.setBounds(0, 125, 500, 25);
        frame.getContentPane().add(progressBar);
        progressBar.setEnabled(false);
        progressBar.setBackground(new Color(255, 255, 255));
        progressBar.setMaximum(100);
        //progressBar.setForeground(new Color(160, 82, 45));
        progressBar.setBorder(new LineBorder(Color.WHITE));
        
        
        progressBar_1 = new JProgressBar();
        progressBar_1.setBounds(0, 91, 500, 26);
        frame.getContentPane().add(progressBar_1);
        
        progressBar_1.setStringPainted(true);
        progressBar_1.setForeground(new Color(102, 51, 204));
        progressBar_1.setEnabled(false);
        progressBar_1.setBackground(new Color(255, 255, 255));
        progressBar_1.setMaximum(100);
        //progressBar.setForeground(new Color(160, 82, 45));
        progressBar_1.setBorder(new LineBorder(Color.WHITE));
        
        
        JLabel lblUploadingData = new JLabel("Updating Data To Server .....");
        lblUploadingData.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblUploadingData.setBounds(10, 8, 267, 16);
        frame.getContentPane().add(lblUploadingData);
        
        JLabel lblItWillTake = new JLabel("Processing ......");
        lblItWillTake.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblItWillTake.setBounds(10, 32, 194, 16);
        frame.getContentPane().add(lblItWillTake);
        
        JLabel lblPleaseWait = new JLabel("Please Wait ....");
        lblPleaseWait.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblPleaseWait.setBounds(10, 56, 194, 16);
        frame.getContentPane().add(lblPleaseWait);
        
        
        //loadProgressBar(company, reg_nme, username, store_name);
        //upload_data(company, reg_nme, username, store_name);
        upload_data();
	}
	
	
	
	private void upload_data() throws SQLException, ClassNotFoundException, JSONException, IOException
	{
		try 
		{
			double ttl_count = 0;
			progressBar.setVisible(true);
    		double number_progress=1;
    		
			String server_type="";
			File configFile = new File("C:/MasterRetails/config.properties");
		
			FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    String counter_type = props.getProperty("counter");
		    reader.close();
		    if(counter_type.equals("BO"))
		    {
		    	server_type="BO";
		    }
		    
		    if(Close_sales.register_all_invoice.equals("1"))
		    {
		    	Connection con=Dbsetup.local_server.getConnection();
				Connection con1=Dbsetup.server.getConnection();
				Statement statement = con.createStatement();
				Statement statement1 = con.createStatement();
				
				Statement statement3 = con1.createStatement();
				Statement statement4 = con1.createStatement();
				
				Statement statement99 = con1.createStatement();
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    final Calendar cal = Calendar.getInstance();
			    final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
			    String formattedDate = myDateObj.format(myFormatObj);  
				
			    /*
				transaction tables
				*/
			    String qury="select count(DISTINCT(reg_num)) as ttl from "+company+".mr_pos_settlement_history where type!='VOID' and mr_pos_settlement_history.status='0' ";
				ResultSet resultSet121 = statement1.executeQuery(qury);
				if(resultSet121.next())
				{
					double count=Double.parseDouble(resultSet121.getString("ttl"));
					number_progress=count;
				}
				ResultSet resultSet99= statement99.executeQuery("select reg_num from "+company+".mr_pos_settlement_history where type!='VOID' and mr_pos_settlement_history.status='0' group by reg_num ");
				while(resultSet99.next())
				{
					String current_reg=resultSet99.getString("reg_num");
					
					String get_ttl_amt= "select  sum(`sales_amt`*`ex_rate`) as ttl,sum( `sale_amt`) as sale_ttl,sum( `vat_amt`) as vat_ttl from "+company+".mr_close_pay_list_history_items where `ref_reg_num`='"+current_reg+"' and status='0' group by created_date,paycode ";
					ResultSet resultSet = statement.executeQuery(get_ttl_amt);
					double net_sale = 0;
					double sale_ttl = 0;
					double vat_ttl = 0;
					if(resultSet.next())
					{
						String ttl_amt=resultSet.getString("ttl");
						net_sale=Double.parseDouble(ttl_amt);
						vat_ttl=Double.parseDouble(resultSet.getString("vat_ttl"));
					}
					
					String seq_num = null;
					String trans_seq= "select tran_seq from "+company+".mr_transaction_seq where `tran_name`='sales invoice' ";
					ResultSet resultSet2 = statement3.executeQuery(trans_seq);
					if(resultSet2.next())
					{
						seq_num=resultSet2.getString("tran_seq");
				    	String[] seq=seq_num.split("-");
				    	DateFormat df = new SimpleDateFormat("yy");
				    	String formatdDate = df.format(Calendar.getInstance().getTime());
				    	if(!seq[1].equals(formattedDate))
				    	{
				    		seq[1]=formatdDate;
				    	}
				    	seq_num=seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+seq[3];
				    	String sqnum=Integer.toString(Integer.parseInt(seq[3])+1);
				    	StringBuilder sb = new StringBuilder();
						for (int toPrepend=6-sqnum.length(); toPrepend>0; toPrepend--) {
						    sb.append('0');
						}
						sb.append(sqnum);
				    	String save_po="update "+company+".mr_transaction_seq set tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_name='sales invoice' ";
				    	statement3.executeUpdate(save_po);
					}
					String get_user_dta= "select id from "+company+".mr_users where user_name='"+username+"' ";
					ResultSet resultSet3 = statement.executeQuery(get_user_dta);
					String user_id = null;
					if(resultSet3.next())
					{
						user_id=resultSet3.getString("id");
					}
					String insert_acc_header="insert into "+company+".mr_accounts_trans_header (tran_seqencecode,tran_date,tran_time,tran_remark,tran_debit_amt,tran_credit_amt,tran_status,tran_user,tran_store,tran_orgin,created_date,modified_date) values ('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','POS SALES CLOSING "+current_reg+" ON "+formattedDate+"','"+net_sale+"','"+net_sale+"','OPEN','"+user_id+"','"+store_name+"','SA','"+formattedDate+"','"+formattedDate+"') ";
					statement3.executeUpdate(insert_acc_header);
					
					/*
					close pay hist
					close pay items
					*/
					
					float new_cost=0;
					String store_sale_acc="";
					
			        String query19="SELECT * FROM "+company+".mr_store_accounts where ref_store_id='"+store_name+"'"; 
					Statement statement19=con1.createStatement();  
			        ResultSet resultSet19 = statement19.executeQuery(query19);
			        if(resultSet19.next())
			        {
			        	store_sale_acc=resultSet19.getString("store_sale_acc");
			        	
			        }
			        
			        String pay_code1="";
			        String pay_code2="";
			        String pay_code3="";
			        String pay_code4="";
			        String pay_code5="";
			        String pay_code6="";
			        String pay_code7="";
			        String pay_code8="";
			        String pay_code9="";
			        String pay_code10="";
			        String pay_code11="";
			        String pay_code12="";
			        String pay_code13="";
			        String pay_code14="";
			        String pay_code15="";
			        String get_paycode= "select  * from "+company+".mr_close_pay_list_history where `ref_reg_num`='"+current_reg+"' and status='0' ";
					ResultSet resultSet1a = statement.executeQuery(get_paycode);
					if(resultSet1a.next())
			        {
			        	pay_code1=resultSet1a.getString("pay_code1");
			        	pay_code2=resultSet1a.getString("pay_code2");
			        	pay_code3=resultSet1a.getString("pay_code3");
			        	pay_code4=resultSet1a.getString("pay_code4");
			        	pay_code5=resultSet1a.getString("pay_code5");
			        	pay_code6=resultSet1a.getString("pay_code6");
			        	pay_code7=resultSet1a.getString("pay_code7");
			        	pay_code8=resultSet1a.getString("pay_code8");
			        	pay_code9=resultSet1a.getString("pay_code9");
			        	pay_code10=resultSet1a.getString("pay_code10");
			        	pay_code11=resultSet1a.getString("pay_code11");
			        	pay_code12=resultSet1a.getString("pay_code12");
			        	pay_code13=resultSet1a.getString("pay_code13");
			        	pay_code14=resultSet1a.getString("pay_code14");
			        	pay_code15=resultSet1a.getString("pay_code15");
			        }
					
			        String pay1="";
			        String pay2="";
			        String pay3="";
			        String pay4="";
			        String pay5="";
			        String pay6="";
			        String pay7="";
			        String pay8="";
			        String pay9="";
			        String pay10="";
			        String pay11="";
			        String pay12="";
			        String pay13="";
			        String pay14="";
			        String pay15="";
			        String query119="SELECT * FROM "+company+".mr_store_paycodes where ref_store_id='"+store_name+"'"; 
					Statement statement119=con1.createStatement();  
			        ResultSet resultSet119 = statement119.executeQuery(query119);
			        while(resultSet119.next())
			        {
			        	if(!resultSet119.getString("paycode").equals(""))
			        	{
			        		if(resultSet119.getString("paycode").equals(pay_code1))
				        	{
				        		pay1=resultSet119.getString("gl_account");
				        	}
				        	if(resultSet119.getString("paycode").equals(pay_code2))
				        	{
				        		pay2=resultSet119.getString("gl_account");
				        	}
				        	if(resultSet119.getString("paycode").equals(pay_code3))
				        	{
				        		pay3=resultSet119.getString("gl_account");
				        	}
				        	if(resultSet119.getString("paycode").equals(pay_code4))
				        	{
				        		pay4=resultSet119.getString("gl_account");
				        	}
				        	if(resultSet119.getString("paycode").equals(pay_code5))
				        	{
				        		pay5=resultSet119.getString("gl_account");
				        	}
				        	if(resultSet119.getString("paycode").equals(pay_code6))
				        	{
				        		pay6=resultSet119.getString("gl_account");
				        	}
				        	if(resultSet119.getString("paycode").equals(pay_code7))
				        	{
				        		pay7=resultSet119.getString("gl_account");
				        	}
				        	if(resultSet119.getString("paycode").equals(pay_code8))
				        	{
				        		pay8=resultSet119.getString("gl_account");
				        	}
				        	if(resultSet119.getString("paycode").equals(pay_code9))
				        	{
				        		pay9=resultSet119.getString("gl_account");
				        	}
				        	if(resultSet119.getString("paycode").equals(pay_code10))
				        	{
				        		pay10=resultSet119.getString("gl_account");
				        	}
				        	if(resultSet119.getString("paycode").equals(pay_code11))
				        	{
				        		pay11=resultSet119.getString("gl_account");
				        	}
				        	if(resultSet119.getString("paycode").equals(pay_code12))
				        	{
				        		pay12=resultSet119.getString("gl_account");
				        	}
				        	if(resultSet119.getString("paycode").equals(pay_code13))
				        	{
				        		pay13=resultSet119.getString("gl_account");
				        	}
				        	if(resultSet119.getString("paycode").equals(pay_code14))
				        	{
				        		pay14=resultSet119.getString("gl_account");
				        	}
				        	if(resultSet119.getString("paycode").equals(pay_code15))
				        	{
				        		pay15=resultSet119.getString("gl_account");
				        	}
			        	}
			        	
			        	
			        }
			        
			        String pos_item_validate="select *,mr_pos_settlement_history_items.qty as pos_qty from "+company+".mr_pos_settlement_history inner join "+company+".mr_pos_settlement_history_items on mr_pos_settlement_history.reg_num=mr_pos_settlement_history_items.ref_reg_num and mr_pos_settlement_history.receipt_no=mr_pos_settlement_history_items.ref_receipt_num where ref_reg_num='"+current_reg+"' AND type!='VOID' and mr_pos_settlement_history_items.status='0'  ";
					ResultSet resultSet7 = statement.executeQuery(pos_item_validate);
					while(resultSet7.next())
					{
						String item_cost=resultSet7.getString("item_cost");
						String pos_qty=resultSet7.getString("pos_qty");
						new_cost+=Float.parseFloat(item_cost)*Float.parseFloat(pos_qty);
					}
					String pos_item="select * from "+company+".mr_pos_settlement_history where reg_num='"+current_reg+"' and status='0' and type!='VOID' ";
					ResultSet resultSet8 = statement.executeQuery(pos_item);
					float vat_val=0;
					while(resultSet8.next())
					{
						sale_ttl+=Float.parseFloat(resultSet8.getString("total"));
						vat_val+=Float.parseFloat(resultSet8.getString("vat"));
					}
					String tax_output="";
			        String cost_sale_acc="";
			        String inv_acc="";
			        String query28="SELECT * FROM "+company+".mr_assign_account_group where acc_store='"+store_name+"' and status='1' "; 
					Statement statement28=con1.createStatement();  
			        ResultSet resultSet28 = statement28.executeQuery(query28);
			        if(resultSet28.next())
			        {
			        	tax_output=resultSet28.getString("tax_output");
			        	cost_sale_acc=resultSet28.getString("cost_sale_acc");
			        	inv_acc=resultSet28.getString("inv_acc");
			        }
			        
			        String pay1_name="";
			        String pay2_name="";
			        String pay3_name="";
			        String pay4_name="";
			        String pay5_name="";
			        String pay6_name="";
			        String pay7_name="";
			        String pay8_name="";
			        String pay9_name="";
			        String pay10_name="";
			        String pay11_name="";
			        String pay12_name="";
			        String pay13_name="";
			        String pay14_name="";
			        String pay15_name="";
			        
			        String sale_acc="";
			        String output_tax="";
			        String cost_sale="";
			        String inv_stock="";
			        
			        ttl_count+=20/number_progress;
					Thread.sleep(50);
					progressBar.paintImmediately(0, 0,  500, 26);
               	 	progressBar.setValue((int)ttl_count);
               	 	
               	 	String query29="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_store='"+store_name+"'"; 
	 				Statement statement29=con1.createStatement();  
	 		        ResultSet resultSet29= statement29.executeQuery(query29);
	 		        while(resultSet29.next())
	 		        {
	 		        	if(!pay1.equals(""))
	 		        	{
	 		        		if(pay1.equals(resultSet29.getString("acc_code")))
		 		        	{
		 		        		pay1_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	
	 		        	if(!pay2.equals(""))
	 		        	{
	 		        		if(pay2.equals(resultSet29.getString("acc_code")))
		 		        	{
	 		        			pay2_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	if(!pay3.equals(""))
	 		        	{
	 		        		if(pay3.equals(resultSet29.getString("acc_code")))
		 		        	{
	 		        			pay3_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	if(!pay4.equals(""))
	 		        	{
	 		        		if(pay4.equals(resultSet29.getString("acc_code")))
		 		        	{
	 		        			pay4_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	if(!pay5.equals(""))
	 		        	{
	 		        		if(pay5.equals(resultSet29.getString("acc_code")))
		 		        	{
	 		        			pay5_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	if(!pay6.equals(""))
	 		        	{
	 		        		if(pay6.equals(resultSet29.getString("acc_code")))
		 		        	{
	 		        			pay6_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	if(!pay7.equals(""))
	 		        	{
	 		        		if(pay7.equals(resultSet29.getString("acc_code")))
		 		        	{
	 		        			pay7_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	if(!pay8.equals(""))
	 		        	{
	 		        		if(pay8.equals(resultSet29.getString("acc_code")))
		 		        	{
	 		        			pay8_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	if(!pay9.equals(""))
	 		        	{
	 		        		if(pay9.equals(resultSet29.getString("acc_code")))
		 		        	{
	 		        			pay9_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	if(!pay10.equals(""))
	 		        	{
	 		        		if(pay10.equals(resultSet29.getString("acc_code")))
		 		        	{
	 		        			pay10_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	if(!pay11.equals(""))
	 		        	{
	 		        		if(pay11.equals(resultSet29.getString("acc_code")))
		 		        	{
	 		        			pay11_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	if(!pay12.equals(""))
	 		        	{
	 		        		if(pay12.equals(resultSet29.getString("acc_code")))
		 		        	{
	 		        			pay12_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	if(!pay13.equals(""))
	 		        	{
	 		        		if(pay13.equals(resultSet29.getString("acc_code")))
		 		        	{
	 		        			pay13_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	if(!pay14.equals(""))
	 		        	{
	 		        		if(pay14.equals(resultSet29.getString("acc_code")))
		 		        	{
	 		        			pay14_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	if(!pay15.equals(""))
	 		        	{
	 		        		if(pay15.equals(resultSet29.getString("acc_code")))
		 		        	{
	 		        			pay15_name=resultSet29.getString("acc_name");
		 		        	}
	 		        	}
	 		        	
	 		        	if(store_sale_acc.equals(resultSet29.getString("acc_code")))
	 		        	{
	 		        		sale_acc=resultSet29.getString("acc_name");
	 		        	}
	 		        	if(tax_output.equals(resultSet29.getString("acc_code")))
	 		        	{
	 		        		output_tax=resultSet29.getString("acc_name");
	 		        	}
	 		        	if(cost_sale_acc.equals(resultSet29.getString("acc_code")))
	 		        	{
	 		        		cost_sale=resultSet29.getString("acc_name");
	 		        	}
	 		        	if(inv_acc.equals(resultSet29.getString("acc_code")))
	 		        	{
	 		        		inv_stock=resultSet29.getString("acc_name");
	 		        	}
	 		        }
               	 	
	 		       String branch="";
			        String branch_query = "select * from "+company+".mr_acc_segment where store_id='"+store_name+"' ";
					Statement statement33=con1.createStatement();  
					ResultSet resultSet33 = statement33.executeQuery(branch_query);
				    if (resultSet33.next())
				    {
				    	if(resultSet33.getString("segment").equals("Branch"))
				    	{
				    		branch=resultSet33.getString("name");
				    	}
				    }
			    
				    String seqnum=null;
					String transseq = "select tran_seq from "+company+".mr_transaction_seq where tran_orgin='JV' ";
					Statement statement30=con1.createStatement();  
					ResultSet resultSet30 = statement30.executeQuery(transseq);
				    if (resultSet30.next())
				    {
				    	seqnum=resultSet30.getString("tran_seq");
				    	String[] seq=seqnum.split("-");
				    	DateFormat df = new SimpleDateFormat("yy");
				    	String formatdDate = df.format(Calendar.getInstance().getTime());
				    	if(!seq[1].equals(formattedDate))
				    	{
				    		seq[1]=formatdDate;
				    	}
				    	seqnum=seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+seq[3];
				    	String sqnum=Integer.toString(Integer.parseInt(seq[3])+1);
				    	StringBuilder sb = new StringBuilder();
						for (int toPrepend=6-sqnum.length(); toPrepend>0; toPrepend--) {
						    sb.append('0');
						}
						sb.append(sqnum);
				    	String save_po="update "+company+".mr_transaction_seq set modified_date='"+formattedDate+"',tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_orgin='JV' ";
						statement3.executeUpdate(save_po);
				    }
					boolean sts=false;
				    String close_item_validate="select * from "+company+".mr_close_pay_list_history_items where ref_reg_num='"+current_reg+"' and status='0' ";
					String accs_dt = "";
					String accs_jv="";
					String ar_leger="";
					float cash_acc=0;
					String acc_nme="";
					String acc_nme1="";
					ResultSet resultSet5 = statement.executeQuery(close_item_validate);
					while(resultSet5.next())
					{
						if(Double.parseDouble(resultSet5.getString("sales_amt"))!=0)	
						{
							String sales_amt=resultSet5.getString("sales_amt");
							String ex_rate=resultSet5.getString("ex_rate");
							String paycode=resultSet5.getString("paycode");
							
							if(paycode.equals("CUSTOMERPAYMENTS"))
							{
								acc_nme=pay2_name;//"CUSTOMER ACCOUNT RECEIVABLE AT "+store_name;
							}
							else if(paycode.equals(pay_code5))
							{
								acc_nme=pay5_name;
							}
							else if(paycode.equals(pay_code6))
							{
								acc_nme=pay6_name;
							}
							else if(paycode.equals(pay_code7))
							{
								acc_nme=pay7_name;
							}
							else if(paycode.equals(pay_code8))
							{
								acc_nme=pay8_name;
							}
							else if(paycode.equals(pay_code9))
							{
								acc_nme=pay9_name;
							}
							else if(paycode.equals(pay_code10))
							{
								acc_nme=pay10_name;
							}
							else if(paycode.equals(pay_code11))
							{
								acc_nme=pay11_name;
							}
							else if(paycode.equals(pay_code12))
							{
								acc_nme=pay12_name;
							}
							else if(paycode.equals(pay_code13))
							{
								acc_nme=pay13_name;
							}
							else if(paycode.equals(pay_code14))
							{
								acc_nme=pay14_name;
							}
							else if(paycode.equals(pay_code15))
							{
								acc_nme=pay15_name;
							}
							else if(paycode.equals(pay_code4))
							{
								acc_nme=pay4_name;
							}
							else if(paycode.equals(pay_code3))
							{
								acc_nme=pay3_name;
							}
							else if(paycode.equals(pay_code2))
							{
								acc_nme=pay2_name;
							}
							else
							{
								acc_nme=pay1_name;
								acc_nme1=pay1_name;
							}
							String chrt_act_mstr = "select * from "+company+".mr_chart_accnt_mstr where acc_name='"+acc_nme+"' and acc_store='"+store_name+"' ";
							ResultSet resultSet6 = statement3.executeQuery(chrt_act_mstr);
							if (resultSet6.next())
						    {
								
								if(paycode.equals("A/R"))
								{
									float totl=0;
									String default_ar_acc="";
									String acc_type=resultSet6.getString("acc_type");
									String ar_master = "select * from "+company+".mr_pos_settlement_history where type='A/R' and store_id='"+store_name+"' and reg_num='"+reg_name+"' and status='0'  ";
									ResultSet resultSet56 = statement1.executeQuery(ar_master);
									while(resultSet56.next())
									{
										String customer_master = "select * from "+company+".mr_customer_master where status='1' and cust_code='"+resultSet56.getString("cust_code")+"' ";
										ResultSet resultSet46 = statement3.executeQuery(customer_master);
										if(resultSet46.next())
										{
											String default_ar_acc_name="";
											default_ar_acc=resultSet46.getString("default_ar_acc");
											if(!default_ar_acc.equals(""))
											{
												String query39="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_store='"+store_name+"' and acc_code='"+default_ar_acc+"' "; 
												Statement statement39=con1.createStatement();  
								                ResultSet resultSet39= statement39.executeQuery(query39);
								                if(resultSet39.next())
								                {
								                	default_ar_acc_name=resultSet39.getString("acc_name");
								                }
											}
											totl+=Float.parseFloat(resultSet56.getString("total"))+Float.parseFloat(resultSet56.getString("vat"));
											if(totl!=0)
											{
												accs_dt+=",('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','"+acc_type+"','"+default_ar_acc+"','"+default_ar_acc_name+"','"+totl+"','0','"+paycode+" COLLECTED AT "+reg_name+" ON  "+formattedDate+"','"+formattedDate+"','"+formattedDate+"')";
										    	accs_jv+=",('"+seqnum+"','"+seqnum+"','"+default_ar_acc+"','"+default_ar_acc_name+"','"+totl+"','0','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"')";
										    	
										    	String query37="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+default_ar_acc+"'"; 
												Statement statement37=con1.createStatement();  
								                ResultSet resultSet37= statement37.executeQuery(query37);
								                float cr=0;
								                float dr=0;
								                while(resultSet37.next())
								                {
								                	cr+=Float.parseFloat(resultSet37.getString("cr_amt"));
								                	dr+=Float.parseFloat(resultSet37.getString("dr_amt"));
								                }
								                float bal_asset=dr-cr;
												double bal=bal_asset+totl;
												ar_leger+=",('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+default_ar_acc+"','"+totl+"','0','"+bal+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' )";
												
											}
										}
									}
								}
								else if(paycode.equals("CUSTOMERPAYMENTS"))
								{
									
								}
								else if(paycode.equals("VISA"))
								{
									String ct_mstr = "select * from "+company+".mr_chart_accnt_mstr where acc_name='"+acc_nme+"' and acc_store='"+store_name+"' ";
									ResultSet resultSet66 = statement3.executeQuery(ct_mstr);
									if (resultSet66.next())
								    {
										accs_dt+=",('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','"+resultSet66.getString("acc_type")+"','"+resultSet66.getString("acc_code")+"','"+resultSet66.getString("acc_name")+"','"+sales_amt+"','0','CASH COLLECTED AT "+reg_name+" ON  "+formattedDate+"','"+formattedDate+"','"+formattedDate+"')";
								    	accs_jv+=",('"+seqnum+"','"+seqnum+"','"+resultSet66.getString("acc_code")+"','"+resultSet66.getString("acc_name")+"','"+sales_amt+"','0','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"')";
								    	String query37="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+resultSet66.getString("acc_code")+"'"; 
										Statement statement37=con1.createStatement();  
						                ResultSet resultSet37= statement37.executeQuery(query37);
						                float cr=0;
						                float dr=0;
						                while(resultSet37.next())
						                {
						                	cr+=Float.parseFloat(resultSet37.getString("cr_amt"));
						                	dr+=Float.parseFloat(resultSet37.getString("dr_amt"));
						                }
						                float bal_asset=dr-cr;
										double bal_bank=bal_asset+Float.parseFloat(sales_amt);
										ar_leger+=",('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+resultSet66.getString("acc_code")+"','"+sales_amt+"','0','"+bal_bank+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' )";
								    }
								}
								else
								{
									sts=true;
									cash_acc+=Float.parseFloat(sales_amt)*Float.parseFloat(ex_rate);
								}
						    }
						}
					}
					if(sts==true)
					{
						String chrt_act_mstr = "select * from "+company+".mr_chart_accnt_mstr where acc_name='"+acc_nme1+"'  and acc_store='"+store_name+"' ";
						ResultSet resultSet6 = statement3.executeQuery(chrt_act_mstr);
						if (resultSet6.next())
					    {
							accs_dt+=",('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','"+resultSet6.getString("acc_type")+"','"+resultSet6.getString("acc_code")+"','"+resultSet6.getString("acc_name")+"','"+cash_acc+"','0','CASH COLLECTED AT "+current_reg+" ON  "+formattedDate+"','"+formattedDate+"','"+formattedDate+"')";
					    	accs_jv+=",('"+seqnum+"','"+seqnum+"','"+resultSet6.getString("acc_code")+"','"+resultSet6.getString("acc_name")+"','"+cash_acc+"','0','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"')";
					    	String query37="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+resultSet6.getString("acc_code")+"'"; 
							Statement statement37=con1.createStatement();  
			                ResultSet resultSet37= statement37.executeQuery(query37);
			                float cr=0;
			                float dr=0;
			                while(resultSet37.next())
			                {
			                	cr+=Float.parseFloat(resultSet37.getString("cr_amt"));
			                	dr+=Float.parseFloat(resultSet37.getString("dr_amt"));
			                }
			                float bal_asset=dr-cr;
							double bal=bal_asset+cash_acc;
							ar_leger+=",('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+resultSet6.getString("acc_code")+"','"+cash_acc+"','0','"+bal+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' )";
					    }
						
					}
					ttl_count+=20/number_progress;
					Thread.sleep(50);
					progressBar.paintImmediately(0, 0, 500, 26);
               	 	progressBar.setValue((int)ttl_count);
               	 	
					double remove_vat=sale_ttl;
					String insert_acc_detls="insert into "+company+".mr_accounts_tran_detls (tran_seqence_code,tran_date,tran_time,tran_type,tran_accode,tran_acname,tran_debit_amt,tran_credit_amt,tran_remark,created_date,modified_date) values"
							+ "('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','ASSET','"+inv_acc+"','"+inv_stock+"','0','"+new_cost+"','POS SALES CLOSING AT "+current_reg+" ON "+formattedDate+"','"+formattedDate+"','"+formattedDate+"'),"
							+ "('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','EXPENSE','"+cost_sale_acc+"','"+cost_sale+"','"+new_cost+"','0','SOLD INVENTORY AT "+current_reg+"','"+formattedDate+"','"+formattedDate+"'),"
							+ "('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','INCOME','"+store_sale_acc+"','"+sale_acc+"','0','"+remove_vat+"','SALES AT "+current_reg+" ON "+formattedDate+"','"+formattedDate+"','"+formattedDate+"'),"
							+ "('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','LIABILITY','"+tax_output+"','"+output_tax+"','0','"+vat_val+"','VAT COLLECTED AT "+current_reg+" ON "+formattedDate+"','"+formattedDate+"','"+formattedDate+"') "
							+ ""+accs_dt+" ";
					statement3.executeUpdate(insert_acc_detls);
					
					String save_jv="insert into "+company+".mr_jv_header( `jv_num`, `date`, `branch`, `username`, `doc_num`, `remark`, `notes`, `store_id`, `created_date`, `modified_date`, `status`,`is_approved`, `approved_by`, `is_posted`, `posted_by`, `posted_date`, `approved_date`,jv_amt,vendor_cust_id) "
							+ "values('"+seqnum+"','"+formattedDate+"','"+branch+"','"+username+"','"+seq_num+"','"+seq_num+"','"+seq_num+"','"+store_name+"','"+formattedDate+"','"+formattedDate+"','Posted','1','"+username+"','1','"+username+"','"+formattedDate+"','"+formattedDate+"','"+sale_ttl+"','"+Close_sales.textField.getText()+"' )";
					statement3.executeUpdate(save_jv);
					
					String queryjd="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,cheque_date) VALUES "
							+ "('"+seqnum+"','"+seqnum+"','"+inv_acc+"','"+inv_stock+"','0','"+new_cost+"','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"'),"
							+ "('"+seqnum+"','"+seqnum+"','"+cost_sale_acc+"','"+cost_sale+"','"+new_cost+"','0','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"'),"
							+ "('"+seqnum+"','"+seqnum+"','"+store_sale_acc+"','"+sale_acc+"','0','"+remove_vat+"','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"'),"
							+ "('"+seqnum+"','"+seqnum+"','"+tax_output+"','"+output_tax+"','0','"+vat_val+"','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"')"
							+ " "+accs_jv+" ";
					statement3.executeUpdate(queryjd);
					
					String query38="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+inv_acc+"'"; 
					Statement statement38=con1.createStatement();  
			        ResultSet resultSet38= statement38.executeQuery(query38);
			        float cr_pay=0;
			        float dr_pay=0;
			        while(resultSet38.next())
			        {
			        	cr_pay+=Float.parseFloat(resultSet38.getString("cr_amt"));
			        	dr_pay+=Float.parseFloat(resultSet38.getString("dr_amt"));
			        }
					float bal_liable=dr_pay-cr_pay;
					double bal_lia=bal_liable-new_cost;
					
					String query39="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+cost_sale_acc+"'"; 
					Statement statement39=con1.createStatement();  
			        ResultSet resultSet39= statement39.executeQuery(query39);
			        float cr_pay9=0;
			        float dr_pay9=0;
			        while(resultSet39.next())
			        {
			        	cr_pay9+=Float.parseFloat(resultSet39.getString("cr_amt"));
			        	dr_pay9+=Float.parseFloat(resultSet39.getString("dr_amt"));
			        }
					float bal_cost=dr_pay9-cr_pay9;
					double bal_cost_ttl=bal_cost-new_cost;
					
					
					String query37="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+store_sale_acc+"'"; 
					Statement statement37=con1.createStatement();  
			        ResultSet resultSet37= statement37.executeQuery(query37);
			        float cr=0;
			        float dr=0;
			        while(resultSet37.next())
			        {
			        	cr+=Float.parseFloat(resultSet37.getString("cr_amt"));
			        	dr+=Float.parseFloat(resultSet37.getString("dr_amt"));
			        }
					float bal_asset=dr-cr;
					double bal=bal_asset-remove_vat;
					
					String query41="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+tax_output+"'"; 
					Statement statement41=con1.createStatement();  
			        ResultSet resultSet41= statement41.executeQuery(query41);
			        float cr_pay41=0;
			        float dr_pay41=0;
			        while(resultSet41.next())
			        {
			        	cr_pay41+=Float.parseFloat(resultSet41.getString("cr_amt"));
			        	dr_pay41+=Float.parseFloat(resultSet41.getString("dr_amt"));
			        }
					float baltax=dr_pay41-cr_pay41;
					double baltax_ttl=baltax-vat_val;
					
					String save_ledger="insert into "+company+".mr_acc_ledger (`jv_num`, `tran_date`, `post_date`, `ac_code`, `dr_amt`, `cr_amt`, `balance`, `notes`, `remark`, `source`, `created_date`, `modified_date`,status) values "
							+ "('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+cost_sale_acc+"','"+new_cost+"','0','"+bal_cost_ttl+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' ),"
							+ "('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+store_sale_acc+"','0','"+remove_vat+"','"+bal+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' ),"
							+ "('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+inv_acc+"','0','"+new_cost+"','"+bal_lia+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' ),"
							+ "('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+tax_output+"','0','"+vat_val+"','"+baltax_ttl+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' ) "
							+ ""+ar_leger+" ";
					statement3.executeUpdate(save_ledger);
					
					
			//		String closed_validate="update "+company+".mr_close_pay_list_items set status='1' where ref_reg_num='"+current_reg+"' ";
			//		statement.executeUpdate(closed_validate);
					
					String his_close_validate="select * from "+company+".mr_close_pay_list_history where ref_reg_num='"+current_reg+"' and status='0' ";
					ResultSet resultSet17 = statement.executeQuery(his_close_validate);
					while(resultSet17.next())
					{
						if(!server_type.equals("BO"))
						{
							String hist_reg="insert into "+company+".mr_close_pay_list_history( `ref_id`, `ref_reg_num`, `username`, `store`, `computer`, `date_time`, `invoice_num`, `remarks`, `total_sale_amt`, `total_vat`, `total_disc`, `total_cost`, `created_date`, `pay_code1`, `open_amt1`, `sales_amt1`, `total_amt1`, `ex_rate1`, `varience1`, `pay_code2`, `open_amt2`, `sales_amt2`, `total_amt2`, `ex_rate2`, `varience2`, `pay_code3`, `open_amt3`, `sales_amt3`, `total_amt3`, `ex_rate3`, `varience3`, `pay_code4`, `open_amt4`, `sales_amt4`, `total_amt4`, `ex_rate4`, `varience4`, `pay_code5`, `open_amt5`, `sales_amt5`, `total_amt5`, `ex_rate5`, `varience5`, `pay_code6`, `open_amt6`, `sales_amt6`, `total_amt6`, `ex_rate6`, `varience6`, `pay_code7`, `open_amt7`, `sales_amt7`, `total_amt7`, `ex_rate7`, `varience7`, `pay_code8`, `open_amt8`, `sales_amt8`, `total_amt8`, `ex_rate8`, `varience8`, `pay_code9`, `open_amt9`, `sales_amt9`, `total_amt9`, `ex_rate9`, `varience9`, `pay_code10`, `open_amt10`, `sales_amt10`, `total_amt10`, `ex_rate10`, `varience10`, `pay_code11`, `open_amt11`, `sales_amt11`, `total_amt11`, `ex_rate11`, `varience11`, `pay_code12`, `open_amt12`, `sales_amt12`, `total_amt12`, `ex_rate12`, `varience12`, `pay_code13`, `open_amt13`, `sales_amt13`, `total_amt13`, `ex_rate13`, `varience13`, `pay_code14`, `open_amt14`, `sales_amt14`, `total_amt14`, `ex_rate14`, `varience14`, `pay_code15`, `open_amt15`, `sales_amt15`, `total_amt15`, `ex_rate15`, `varience15`, `status`,close_date) values ('"+resultSet17.getString("ref_id")+"','"+resultSet17.getString("ref_reg_num")+"','"+resultSet17.getString("username")+"','"+resultSet17.getString("store")+"','"+resultSet17.getString("computer")+"','"+resultSet17.getString("date_time")+"','"+resultSet17.getString("invoice_num")+"','"+seqnum+"','"+resultSet17.getString("total_sale_amt")+"','"+resultSet17.getString("total_vat")+"','"+resultSet17.getString("total_disc")+"','"+resultSet17.getString("total_cost")+"','"+resultSet17.getString("created_date")+"','"+resultSet17.getString("pay_code1")+"','"+resultSet17.getString("open_amt1")+"','"+resultSet17.getString("sales_amt1")+"','"+resultSet17.getString("total_amt1")+"','"+resultSet17.getString("ex_rate1")+"','"+resultSet17.getString("varience1")+"','"+resultSet17.getString("pay_code2")+"','"+resultSet17.getString("open_amt2")+"','"+resultSet17.getString("sales_amt2")+"','"+resultSet17.getString("total_amt2")+"','"+resultSet17.getString("ex_rate2")+"','"+resultSet17.getString("varience2")+"','"+resultSet17.getString("pay_code3")+"','"+resultSet17.getString("open_amt3")+"','"+resultSet17.getString("sales_amt3")+"','"+resultSet17.getString("total_amt3")+"','"+resultSet17.getString("ex_rate3")+"','"+resultSet17.getString("varience3")+"','"+resultSet17.getString("pay_code4")+"','"+resultSet17.getString("open_amt4")+"','"+resultSet17.getString("sales_amt4")+"','"+resultSet17.getString("total_amt4")+"','"+resultSet17.getString("ex_rate4")+"','"+resultSet17.getString("varience4")+"','"+resultSet17.getString("pay_code5")+"','"+resultSet17.getString("open_amt5")+"','"+resultSet17.getString("sales_amt5")+"','"+resultSet17.getString("total_amt5")+"','"+resultSet17.getString("ex_rate5")+"','"+resultSet17.getString("varience5")+"','"+resultSet17.getString("pay_code6")+"','"+resultSet17.getString("open_amt6")+"','"+resultSet17.getString("sales_amt6")+"','"+resultSet17.getString("total_amt6")+"','"+resultSet17.getString("ex_rate6")+"','"+resultSet17.getString("varience6")+"','"+resultSet17.getString("pay_code7")+"','"+resultSet17.getString("open_amt7")+"','"+resultSet17.getString("sales_amt7")+"','"+resultSet17.getString("total_amt7")+"','"+resultSet17.getString("ex_rate7")+"','"+resultSet17.getString("varience7")+"','"+resultSet17.getString("pay_code8")+"','"+resultSet17.getString("open_amt8")+"','"+resultSet17.getString("sales_amt8")+"','"+resultSet17.getString("total_amt8")+"','"+resultSet17.getString("ex_rate8")+"','"+resultSet17.getString("varience8")+"','"+resultSet17.getString("pay_code9")+"','"+resultSet17.getString("open_amt9")+"','"+resultSet17.getString("sales_amt9")+"','"+resultSet17.getString("total_amt9")+"','"+resultSet17.getString("ex_rate9")+"','"+resultSet17.getString("varience9")+"','"+resultSet17.getString("pay_code10")+"','"+resultSet17.getString("open_amt10")+"','"+resultSet17.getString("sales_amt10")+"','"+resultSet17.getString("total_amt10")+"','"+resultSet17.getString("ex_rate10")+"','"+resultSet17.getString("varience10")+"','"+resultSet17.getString("pay_code11")+"','"+resultSet17.getString("open_amt11")+"','"+resultSet17.getString("sales_amt11")+"','"+resultSet17.getString("total_amt11")+"','"+resultSet17.getString("ex_rate11")+"','"+resultSet17.getString("varience11")+"','"+resultSet17.getString("pay_code12")+"','"+resultSet17.getString("open_amt12")+"','"+resultSet17.getString("sales_amt12")+"','"+resultSet17.getString("total_amt12")+"','"+resultSet17.getString("ex_rate12")+"','"+resultSet17.getString("varience12")+"','"+resultSet17.getString("pay_code13")+"','"+resultSet17.getString("open_amt13")+"','"+resultSet17.getString("sales_amt13")+"','"+resultSet17.getString("total_amt13")+"','"+resultSet17.getString("ex_rate13")+"','"+resultSet17.getString("varience13")+"','"+resultSet17.getString("pay_code14")+"','"+resultSet17.getString("open_amt14")+"','"+resultSet17.getString("sales_amt14")+"','"+resultSet17.getString("total_amt14")+"','"+resultSet17.getString("ex_rate14")+"','"+resultSet17.getString("varience14")+"','"+resultSet17.getString("pay_code15")+"','"+resultSet17.getString("open_amt15")+"','"+resultSet17.getString("sales_amt15")+"','"+resultSet17.getString("total_amt15")+"','"+resultSet17.getString("ex_rate15")+"','"+resultSet17.getString("varience15")+"','1','"+resultSet17.getString("close_date")+"')";
							statement3.executeUpdate(hist_reg);
						}
						String up_reg="update "+company+".mr_close_pay_list_history set status='1',remarks='"+seqnum+"' where ref_id='"+resultSet17.getString("ref_id")+"' ";
						statement1.executeUpdate(up_reg);
					}
					ttl_count+=20/number_progress;
					Thread.sleep(50);
					progressBar.paintImmediately(0, 0,  500, 26);
               	 	progressBar.setValue((int)ttl_count);
			        
					
					String his_close_itm_validate="select * from "+company+".mr_close_pay_list_history_items where ref_reg_num='"+current_reg+"' and status='0' ";
					ResultSet resultSet18 = statement.executeQuery(his_close_itm_validate);
					while(resultSet18.next())
					{
						if(!server_type.equals("BO"))
						{
							String hist_reg="insert into "+company+".mr_close_pay_list_history_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `ex_rate`, `settle_amt`, `varience`, `sale_amt`, `vat_amt`, `created_date`, `status`,jv_num) values('"+resultSet18.getString("ref_reg_num")+"','"+resultSet18.getString("date_time")+"','"+resultSet18.getString("paycode")+"','"+resultSet18.getString("openings")+"','"+resultSet18.getString("sales_amt")+"','"+resultSet18.getString("total_amt")+"','"+resultSet18.getString("ex_rate")+"','"+resultSet18.getString("settle_amt")+"','"+resultSet18.getString("varience")+"','"+resultSet18.getString("sale_amt")+"','"+resultSet18.getString("vat_amt")+"','"+resultSet18.getString("created_date")+"','1','"+seqnum+"')";
							statement3.executeUpdate(hist_reg);
						}
						
						String up_reg="update "+company+".mr_close_pay_list_history_items set status='1',jv_num='"+seqnum+"' where id='"+resultSet18.getString("id")+"' ";
						statement1.executeUpdate(up_reg);
					}
					
					/*
					 * History table trans
					 * 
					 */
					String card_validate="select * from "+company+".mr_register_openings_history where reg_num='"+current_reg+"' and status='0' ";
					ResultSet resultSet9 = statement.executeQuery(card_validate);
					while(resultSet9.next())
					{
						if(!server_type.equals("BO"))
						{
							String hist_reg="insert into "+company+".mr_register_openings_history(`reg_num`, `store_name`, `user_name`, `open_receipt`, `remarks`, `date_time`, `created_date`, `status`)values('"+resultSet9.getString("reg_num")+"','"+resultSet9.getString("store_name")+"','"+resultSet9.getString("user_name")+"','"+resultSet9.getString("open_receipt")+"','"+resultSet9.getString("remarks")+"','"+resultSet9.getString("date_time")+"','"+resultSet9.getString("created_date")+"','1')";
							statement3.executeUpdate(hist_reg);
						}
						
						String up_reg="update "+company+".mr_register_openings_history set status='1' where id='"+resultSet9.getString("id")+"' ";
						statement1.executeUpdate(up_reg);
					}
					String reg_validate="select * from "+company+".mr_register_open_items_history where ref_reg_num='"+current_reg+"' and status='0' ";
					ResultSet resultSet10 = statement.executeQuery(reg_validate);
					while(resultSet10.next())
					{
						if(!server_type.equals("BO"))
						{
						String hist_reg="insert into "+company+".mr_register_open_items_history(`ref_reg_num`, `date_time`, `paycode`, `pay_desc`, `amount`, `created_date`, `status`) values('"+resultSet10.getString("ref_reg_num")+"','"+resultSet10.getString("date_time")+"','"+resultSet10.getString("paycode")+"','"+resultSet10.getString("pay_desc")+"','"+resultSet10.getString("amount")+"','"+resultSet10.getString("created_date")+"','1')";
						statement3.executeUpdate(hist_reg);
						}
						String up_reg="update "+company+".mr_register_open_items_history set status='1' where id='"+resultSet10.getString("id")+"' ";
						statement3.executeUpdate(up_reg);
						String upd_reg="update "+company+".mr_register_open_items_history set status='1' where id='"+resultSet10.getString("id")+"' ";
						statement1.executeUpdate(upd_reg);
					}
					/*
					pay in & out
					customer
					*/
					String pay_validate="select * from "+company+".mr_paylist where status='1' and ref_reg_no='"+current_reg+"' ";
					ResultSet resultSet11 = statement.executeQuery(pay_validate);
					while(resultSet11.next())
					{
						if(!server_type.equals("BO"))
						{
						String hist_reg="insert into "+company+".mr_paylist( `type`, `reason_code`, `reason`, `amount`, `ref_reg_no`, `user`, `status`, `created_date`, `time`, `store_id`) values('"+resultSet11.getString("type")+"','"+resultSet11.getString("reason_code")+"','"+resultSet11.getString("reason")+"','"+resultSet11.getString("amount")+"','"+resultSet11.getString("ref_reg_no")+"','"+resultSet11.getString("user")+"','"+resultSet11.getString("status")+"','"+resultSet11.getString("created_date")+"','"+resultSet11.getString("time")+"','"+resultSet11.getString("store_id")+"')";
						statement3.executeUpdate(hist_reg);
						}
						String pay_reg="update "+company+".mr_paylist set status='2' where id='"+resultSet11.getString("id")+"'";
						statement1.executeUpdate(pay_reg);
					}
					String cust_validate="select * from "+company+".mr_customer_payment where status='1' and ref_reg_num='"+current_reg+"' ";
					ResultSet resultSet12 = statement.executeQuery(cust_validate);
					while(resultSet12.next())
					{
						if(!server_type.equals("BO"))
						{
						String cust_inst="insert into "+company+".mr_customer_payment(`seq_num`, `cust_code`, `amount`, `discount`, `paycode`, `remarks`, `ref_reg_num`, `status`, `store_id`, `username`, `created_date`, `time`) values('"+resultSet12.getString("seq_num")+"','"+resultSet12.getString("cust_code")+"','"+resultSet12.getString("amount")+"','"+resultSet12.getString("discount")+"','"+resultSet12.getString("paycode")+"','"+resultSet12.getString("remarks")+"','"+resultSet12.getString("ref_reg_num")+"','"+resultSet12.getString("status")+"','"+resultSet12.getString("store_id")+"','"+resultSet12.getString("username")+"','"+resultSet12.getString("created_date")+"','"+resultSet12.getString("time")+"')";
						statement3.executeUpdate(cust_inst);
						}
						String hist_reg="update "+company+".mr_customer_payment set status='2' where id='"+resultSet12.getString("id")+"'";
						statement1.executeUpdate(hist_reg);
					}
					/*
					pos table
					pos items table
					paycode
					update qty
					tax update
					*/
					Statement statement50=con.createStatement();
//					String pos_validate="select * from "+company+".mr_pos_settlement_history where reg_num='"+current_reg+"' and status='0' and type!='VOID' ";
//					ResultSet resultSet13 = statement50.executeQuery(pos_validate);
//					while(resultSet13.next())
//					{
//						if(!server_type.equals("BO"))
//						{
//						String hist_reg="insert into "+company+".mr_pos_settlement_history(`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, `cash_paid`, `type`, `paycode_disc`, `store_id`, `created_date`, `status`,grand_total,cust_code,cust_prev_balance) values('"+resultSet13.getString("reg_num")+"','"+resultSet13.getString("receipt_no")+"','"+resultSet13.getString("user")+"','"+resultSet13.getString("time")+"','"+resultSet13.getString("total")+"','"+resultSet13.getString("cust_name")+"','"+resultSet13.getString("vat")+"','"+resultSet13.getString("disc")+"','"+resultSet13.getString("qty")+"','"+resultSet13.getString("cash_paid")+"','"+resultSet13.getString("type")+"','"+resultSet13.getString("paycode_disc")+"','"+resultSet13.getString("store_id")+"','"+resultSet13.getString("created_date")+"','1','"+resultSet13.getString("grand_total")+"','"+resultSet13.getString("cust_code")+"','"+resultSet13.getString("cust_prev_balance")+"')";
//						statement3.executeUpdate(hist_reg);
//						}
//						
//					}
					
					String up_reg2="update "+company+".mr_pos_settlement_history set status='1',jv_num='"+seqnum+"' where reg_num='"+current_reg+"' and status='0' ";
					statement1.executeUpdate(up_reg2);
					
					Statement statement550=con.createStatement();
					String pos_count="select count(*) as total from "+company+".mr_pos_settlement_history_items inner join "+company+".mr_pos_settlement_history on mr_pos_settlement_history.reg_num=mr_pos_settlement_history_items.ref_reg_num and mr_pos_settlement_history.receipt_no=mr_pos_settlement_history_items.ref_receipt_num where ref_reg_num='"+current_reg+"' and mr_pos_settlement_history_items.status='0' group by item_code";
					ResultSet resultSet113 = statement550.executeQuery(pos_count);
					if(resultSet113.next())
					{
						int a=1;
						double pamt=0;
						//double progamt=0;
						int ttl_count1=Integer.parseInt(resultSet113.getString("total"));
						double counts=(ttl_count1/100)/number_progress;
						//double pcount=(ttl_count1/20)/number_progress;
						String pos_itm_validate="select mr_pos_settlement_history_items.id,cust_name,cust_code,item_code,barcode,sum(mr_pos_settlement_history_items.qty) as qty_item,unit,item_cost,unit_price,sum(mr_pos_settlement_history_items.total) as total,mr_pos_settlement_history_items.cf from "+company+".mr_pos_settlement_history_items inner join "+company+".mr_pos_settlement_history on mr_pos_settlement_history.reg_num=mr_pos_settlement_history_items.ref_reg_num and mr_pos_settlement_history.receipt_no=mr_pos_settlement_history_items.ref_receipt_num where ref_reg_num='"+current_reg+"' and mr_pos_settlement_history_items.status='0' group by item_code,unit ";
						ResultSet resultSet14 = statement50.executeQuery(pos_itm_validate);
						while(resultSet14.next())
						{
							a++;
							
//							ttl_count+=pcount;
//							Thread.sleep(50);
//							progressBar.paintImmediately(0, 0, 500, 26);
//		               	 	progressBar.setValue((int)ttl_count);
							
							//progamt=ttl_count+(a/pcount);
							//progressBar.setValue((int)progamt);
							
							pamt=a/counts;
							Thread.sleep(50);
							progressBar_1.paintImmediately(0, 0, 500, 26);
			    			progressBar_1.setValue((int)pamt);
			    			
			    			String cust_name=resultSet14.getString("cust_name");
							String cust_code=resultSet14.getString("cust_code");
			    			
							String itemcode=resultSet14.getString("item_code");
							String qty=resultSet14.getString("qty_item");
							String unit=resultSet14.getString("unit");
							String item_cost=resultSet14.getString("item_cost");
							String unit_price=resultSet14.getString("unit_price");
							//String total=resultSet14.getString("total");
							String cf=resultSet14.getString("cf");
//							if(!server_type.equals("BO"))
//							{
//							String hist_reg="insert into "+company+".mr_pos_settlement_history_items (`ref_receipt_num`, `ref_reg_num`, `barcode`, `item_desc`, `item_code`, `item_cost`, `unit`, `unit_price`, `sp`, `qty`, `total`, `vat`, `discount`, `created_date`, `status`,`vat_type`, `vat_perc`, `disc_type`) values ('"+resultSet14.getString("ref_receipt_num")+"','"+resultSet14.getString("ref_reg_num")+"','"+resultSet14.getString("barcode")+"','"+resultSet14.getString("item_desc")+"','"+resultSet14.getString("item_code")+"','"+resultSet14.getString("item_cost")+"','"+resultSet14.getString("unit")+"','"+resultSet14.getString("unit_price")+"','"+resultSet14.getString("sp")+"','"+resultSet14.getString("qty")+"','"+resultSet14.getString("total")+"','"+resultSet14.getString("vat")+"','"+resultSet14.getString("discount")+"','"+resultSet14.getString("created_date")+"','1','"+resultSet14.getString("vat_type")+"','"+resultSet14.getString("vat_perc")+"','"+resultSet14.getString("disc_type")+"')";
//							statement3.executeUpdate(hist_reg);
//							}
							if(Close_sales.assembly_of_item.equals("0"))
							{
								String kititm_validate="select * from "+company+".mr_kit_items where parent_itemcode='"+itemcode+"' ";
								Statement statement125=con1.createStatement();
								ResultSet resultSet125 = statement125.executeQuery(kititm_validate);
								if(resultSet125.next())
								{
									Statement statement150=con1.createStatement();
									ResultSet resultSet150 = statement150.executeQuery(kititm_validate);
									while(resultSet150.next())
									{
										
										String sp=resultSet150.getString("sp");
										String child_barcode=resultSet150.getString("child_barcode");
										String cost=resultSet150.getString("cost");
										String kit_qty=resultSet150.getString("qty");
										double ttl_price=Double.parseDouble(kit_qty)*Double.parseDouble(sp);
										
										String itmvalidate="select * from "+company+".mr_tmp_item_master where item_code='"+resultSet150.getString("child_itemcode")+"' ";
										Statement statement151=con1.createStatement();
										ResultSet resultSet151 = statement151.executeQuery(itmvalidate);
										if(resultSet151.next())
										{
											String total_qty=resultSet151.getString("qty"); 
											float newqty=Float.parseFloat(total_qty)-Float.parseFloat(qty);
											String update_qty="update "+company+".mr_item_master set total_qty='"+newqty+"',modified_date='"+formattedDate+"' where item_code='"+resultSet150.getString("child_itemcode")+"'  ";
											statement4.executeUpdate(update_qty);
											String update_temp="update "+company+".mr_tmp_item_master set qty='"+newqty+"' where item_code='"+resultSet150.getString("child_itemcode")+"'  ";
											statement4.executeUpdate(update_temp);
											if(server_type.equals("BO"))
											{
												String query11="insert into "+company+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) "
														+ "values('"+resultSet150.getString("child_itemcode")+"','SA','"+seq_num+"','"+kit_qty+"','"+unit+"','1','"+cost+"','"+(Double.parseDouble(cost)*Float.parseFloat(kit_qty))+"','"+qty+"','"+sp+"','"+ttl_price+"','KIT SALE','"+username+"','"+formattedDate+"','"+child_barcode+"','"+cust_code+"','"+cust_name+"')";
												statement3.executeUpdate(query11);
											}
											
										}
									}
									String update_qty="update "+company+".mr_item_master set total_qty='0',modified_date='"+formattedDate+"' where item_code='"+itemcode+"'  ";
									statement4.executeUpdate(update_qty);
									String update_temp="update "+company+".mr_tmp_item_master set qty='0' where item_code='"+itemcode+"'  ";
									statement4.executeUpdate(update_temp);
								}
							}
							
							if(server_type.equals("BO"))
							{
								//String query11="insert into "+company+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) 
								//		values('"+itemcode+"','SA','"+seq_num+"','"+qty+"','"+unit+"','"+cf+"','"+item_cost+"','"+(Double.parseDouble(item_cost)*Float.parseFloat(qty))+"','"+qty+"','"+unit_price+"','"+Double.parseDouble(total)+"','Recieved "+seq_num+"','"+username+"','"+formattedDate+"','"+resultSet14.getString("barcode")+"','"+cust_code+"','"+cust_name+"')";
								String query11="insert into "+company+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) "
										+ "values('"+itemcode+"','SA','"+seq_num+"','"+qty+"','"+unit+"','"+cf+"','"+item_cost+"','"+(Double.parseDouble(item_cost)*Float.parseFloat(qty)*Float.parseFloat(cf))+"','"+qty+"','"+unit_price+"','"+(Double.parseDouble(unit_price)*Float.parseFloat(qty))+"','Recieved "+seq_num+"','"+username+"','"+formattedDate+"','"+resultSet14.getString("barcode")+"','"+cust_code+"','"+cust_name+"')";
								
								statement3.executeUpdate(query11);
							}
						}
						String up_reg1="update "+company+".mr_pos_settlement_history_items set status='1' where ref_reg_num='"+current_reg+"' and status='0'";
						statement1.executeUpdate(up_reg1);
					}
					
					
					ttl_count+=20/number_progress;
					Thread.sleep(50);
					progressBar.paintImmediately(0, 0,  500, 26);
               	 	progressBar.setValue((int)ttl_count);
			       
					String sale_tax="insert into "+company+".mr_sales_tax (`reg_num`, `sales_total`, `tax_amount`, `sale_date`, `remark`, `created_date`) values('"+current_reg+"','"+net_sale+"','"+vat_ttl+"','"+formattedDate+"','sales closed by "+username+"','"+formattedDate+"')";
					statement3.executeUpdate(sale_tax);
					
					
					String paycode_validate="select * from "+company+".mr_paycode_settlement_history where ref_reg_num='"+current_reg+"' and status='0' ";
					ResultSet resultSet16 = statement.executeQuery(paycode_validate);
					while(resultSet16.next())
					{
//						if(!server_type.equals("BO"))
//						{
//						String hist_reg="insert into "+company+".mr_paycode_settlement_history ( `ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`, `status`) values ('"+resultSet16.getString("ref_reg_num")+"','"+resultSet16.getString("ref_receipt_num")+"','"+resultSet16.getString("paycode_name")+"','"+resultSet16.getString("paycode_rate")+"','"+resultSet16.getString("paycode_amt")+"','"+resultSet16.getString("paid_amt")+"','"+resultSet16.getString("created_date")+"','1') ";
//						statement3.executeUpdate(hist_reg);
//						}
						
						String up_reg="update "+company+".mr_paycode_settlement_history set status='1' where id='"+resultSet16.getString("id")+"'";
						statement1.executeUpdate(up_reg);
					}
					String register_validate="select * from "+company+".mr_register_setup where register_number='"+current_reg+"'";
					ResultSet resultSet69 = statement.executeQuery(register_validate);
					if(resultSet69.next())
					{
						String reg_num="update "+company+".mr_register_setup set next_num='"+resultSet69.getString("next_num")+"' where register_number='"+current_reg+"'";
						statement3.executeUpdate(reg_num);
					}
					
					ttl_count+=20/number_progress;
					Thread.sleep(50);
					progressBar.paintImmediately(0, 0,  500, 26);
               	 	progressBar.setValue((int)ttl_count);
               	 	
               	 if(ttl_count>=100)
     			{
     	        	//Runtime.getRuntime().exec("cmd /c start \"\" C:\\MasterRetails\\backup\\backupbatch.bat");
     	    		JOptionPane.showMessageDialog(frame, "<html>Data Uploaded Successfully<br> „  Õ„Ì· «·»Ì«‰«  »‰Ã«Õ</html>");
     	        	JOptionPane.showMessageDialog(frame, "<html>Counter Closed Successfully<br> „ ≈€·«ﬁ «·⁄œ«œ »‰Ã«Õ</html>");
     	        	frame.dispose();
     	        	Close_sales.frame.setVisible(false);
     	        	InetAddress addr = InetAddress.getLocalHost();
     			    String sys_name = addr.getHostName();
     			    ResultSet resultSettest = statement.executeQuery("select * from "+company+".mr_loggin_controls where username='"+username+"' and "+company+".mr_loggin_controls.device_name='"+sys_name+"' ");
     				if(resultSettest.next())
     				{
     					String del="update "+company+".mr_loggin_controls set status='1',modified_date='"+formattedDate+"' where username='"+username+"' and status='0' and  "+company+".mr_loggin_controls.device_name='"+sys_name+"' ";
     					statement1.executeUpdate(del);
     				}
     				con.close();
     				frame.dispose();
     				//Runtime.getRuntime().exec(new String[] {"cmd","/c","taskkill /im  javaw.exe /f"});
     	        	Runtime.getRuntime().exec("cmd /c start \"\" C:\\MasterRetails\\MasterRetails.jar");
     	        	System.exit(0);
     			}
				
			/*loop end*/
				}
/*loop end*/
		    }
		    else
		    {
		    	Connection con=Dbsetup.local_server.getConnection();
				Connection con1=Dbsetup.server.getConnection();
				Statement statement = con.createStatement();
				Statement statement1 = con.createStatement();
				Statement statement3 = con1.createStatement();
				Statement statement4 = con1.createStatement();
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    final Calendar cal = Calendar.getInstance();
			    final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
			    String formattedDate = myDateObj.format(myFormatObj);  
				
			    /*
				transaction tables
				*/
		    
		    
			    String get_ttl_amt= "select  sum(`sales_amt`*`ex_rate`) as ttl,sum( `sale_amt`) as sale_ttl,sum( `vat_amt`) as vat_ttl from "+company+".mr_close_pay_list_history_items where `ref_reg_num`='"+reg_name+"' and status='0' group by created_date,paycode ";
				ResultSet resultSet = statement.executeQuery(get_ttl_amt);
				double net_sale = 0;
				double sale_ttl = 0;
				double vat_ttl = 0;
				if(resultSet.next())
				{
					String ttl_amt=resultSet.getString("ttl");
					net_sale=Double.parseDouble(ttl_amt);
					vat_ttl=Double.parseDouble(resultSet.getString("vat_ttl"));
				}
				String seq_num = null;
				String trans_seq= "select tran_seq from "+company+".mr_transaction_seq where `tran_name`='sales invoice' ";
				ResultSet resultSet2 = statement3.executeQuery(trans_seq);
				if(resultSet2.next())
				{
					seq_num=resultSet2.getString("tran_seq");
			    	String[] seq=seq_num.split("-");
			    	DateFormat df = new SimpleDateFormat("yy");
			    	String formatdDate = df.format(Calendar.getInstance().getTime());
			    	if(!seq[1].equals(formattedDate))
			    	{
			    		seq[1]=formatdDate;
			    	}
			    	seq_num=seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+seq[3];
			    	String sqnum=Integer.toString(Integer.parseInt(seq[3])+1);
			    	StringBuilder sb = new StringBuilder();
					for (int toPrepend=6-sqnum.length(); toPrepend>0; toPrepend--) {
					    sb.append('0');
					}
					sb.append(sqnum);
			    	String save_po="update "+company+".mr_transaction_seq set tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_name='sales invoice' ";
			    	statement3.executeUpdate(save_po);
				}
				String get_user_dta= "select id from "+company+".mr_users where user_name='"+username+"' ";
				ResultSet resultSet3 = statement.executeQuery(get_user_dta);
				String user_id = null;
				if(resultSet3.next())
				{
					user_id=resultSet3.getString("id");
				}
				String insert_acc_header="insert into "+company+".mr_accounts_trans_header (tran_seqencecode,tran_date,tran_time,tran_remark,tran_debit_amt,tran_credit_amt,tran_status,tran_user,tran_store,tran_orgin,created_date,modified_date) values ('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','POS SALES CLOSING "+reg_name+" ON "+formattedDate+"','"+net_sale+"','"+net_sale+"','OPEN','"+user_id+"','"+store_name+"','SA','"+formattedDate+"','"+formattedDate+"') ";
				statement3.executeUpdate(insert_acc_header);
				
				/*
				close pay hist
				close pay items
				*/
				
				float new_cost=0;
				String store_sale_acc="";
				
		        String query19="SELECT * FROM "+company+".mr_store_accounts where ref_store_id='"+store_name+"'"; 
				Statement statement19=con1.createStatement();  
		        ResultSet resultSet19 = statement19.executeQuery(query19);
		        if(resultSet19.next())
		        {
		        	store_sale_acc=resultSet19.getString("store_sale_acc");
		        	
		        }
		        
		        String pay_code1="";
		        String pay_code2="";
		        String pay_code3="";
		        String pay_code4="";
		        String pay_code5="";
		        String pay_code6="";
		        String pay_code7="";
		        String pay_code8="";
		        String pay_code9="";
		        String pay_code10="";
		        String pay_code11="";
		        String pay_code12="";
		        String pay_code13="";
		        String pay_code14="";
		        String pay_code15="";
		        String get_paycode= "select  * from "+company+".mr_close_pay_list_history where `ref_reg_num`='"+reg_name+"' and status='0' ";
				ResultSet resultSet1a = statement.executeQuery(get_paycode);
				if(resultSet1a.next())
		        {
		        	pay_code1=resultSet1a.getString("pay_code1");
		        	pay_code2=resultSet1a.getString("pay_code2");
		        	pay_code3=resultSet1a.getString("pay_code3");
		        	pay_code4=resultSet1a.getString("pay_code4");
		        	pay_code5=resultSet1a.getString("pay_code5");
		        	pay_code6=resultSet1a.getString("pay_code6");
		        	pay_code7=resultSet1a.getString("pay_code7");
		        	pay_code8=resultSet1a.getString("pay_code8");
		        	pay_code9=resultSet1a.getString("pay_code9");
		        	pay_code10=resultSet1a.getString("pay_code10");
		        	pay_code11=resultSet1a.getString("pay_code11");
		        	pay_code12=resultSet1a.getString("pay_code12");
		        	pay_code13=resultSet1a.getString("pay_code13");
		        	pay_code14=resultSet1a.getString("pay_code14");
		        	pay_code15=resultSet1a.getString("pay_code15");
		        }
		        
				String pay1="";
		        String pay2="";
		        String pay3="";
		        String pay4="";
		        String pay5="";
		        String pay6="";
		        String pay7="";
		        String pay8="";
		        String pay9="";
		        String pay10="";
		        String pay11="";
		        String pay12="";
		        String pay13="";
		        String pay14="";
		        String pay15="";
		        String query119="SELECT * FROM "+company+".mr_store_paycodes where ref_store_id='"+store_name+"'"; 
				Statement statement119=con1.createStatement();  
		        ResultSet resultSet119 = statement119.executeQuery(query119);
		        while(resultSet119.next())
		        {
		        	if(!resultSet119.getString("paycode").equals(""))
		        	{
		        		if(resultSet119.getString("paycode").equals(pay_code1))
			        	{
			        		pay1=resultSet119.getString("gl_account");
			        	}
			        	if(resultSet119.getString("paycode").equals(pay_code2))
			        	{
			        		pay2=resultSet119.getString("gl_account");
			        	}
			        	if(resultSet119.getString("paycode").equals(pay_code3))
			        	{
			        		pay3=resultSet119.getString("gl_account");
			        	}
			        	if(resultSet119.getString("paycode").equals(pay_code4))
			        	{
			        		pay4=resultSet119.getString("gl_account");
			        	}
			        	if(resultSet119.getString("paycode").equals(pay_code5))
			        	{
			        		pay5=resultSet119.getString("gl_account");
			        	}
			        	if(resultSet119.getString("paycode").equals(pay_code6))
			        	{
			        		pay6=resultSet119.getString("gl_account");
			        	}
			        	if(resultSet119.getString("paycode").equals(pay_code7))
			        	{
			        		pay7=resultSet119.getString("gl_account");
			        	}
			        	if(resultSet119.getString("paycode").equals(pay_code8))
			        	{
			        		pay8=resultSet119.getString("gl_account");
			        	}
			        	if(resultSet119.getString("paycode").equals(pay_code9))
			        	{
			        		pay9=resultSet119.getString("gl_account");
			        	}
			        	if(resultSet119.getString("paycode").equals(pay_code10))
			        	{
			        		pay10=resultSet119.getString("gl_account");
			        	}
			        	if(resultSet119.getString("paycode").equals(pay_code11))
			        	{
			        		pay11=resultSet119.getString("gl_account");
			        	}
			        	if(resultSet119.getString("paycode").equals(pay_code12))
			        	{
			        		pay12=resultSet119.getString("gl_account");
			        	}
			        	if(resultSet119.getString("paycode").equals(pay_code13))
			        	{
			        		pay13=resultSet119.getString("gl_account");
			        	}
			        	if(resultSet119.getString("paycode").equals(pay_code14))
			        	{
			        		pay14=resultSet119.getString("gl_account");
			        	}
			        	if(resultSet119.getString("paycode").equals(pay_code15))
			        	{
			        		pay15=resultSet119.getString("gl_account");
			        	}
		        	}
		        	
		        	
		        }
		        
		        String pos_item_validate="select *,mr_pos_settlement_history_items.qty as pos_qty from "+company+".mr_pos_settlement_history inner join "+company+".mr_pos_settlement_history_items on mr_pos_settlement_history.reg_num=mr_pos_settlement_history_items.ref_reg_num and mr_pos_settlement_history.receipt_no=mr_pos_settlement_history_items.ref_receipt_num where ref_reg_num='"+reg_name+"' AND type!='VOID' and mr_pos_settlement_history_items.status='0'  ";
				ResultSet resultSet7 = statement.executeQuery(pos_item_validate);
				while(resultSet7.next())
				{
					String item_cost=resultSet7.getString("item_cost");
					String pos_qty=resultSet7.getString("pos_qty");
					new_cost+=Float.parseFloat(item_cost)*Float.parseFloat(pos_qty);
				}
				String pos_item="select * from "+company+".mr_pos_settlement_history where reg_num='"+reg_name+"' and status='0' and type!='VOID' ";
				ResultSet resultSet8 = statement.executeQuery(pos_item);
				float vat_val=0;
				while(resultSet8.next())
				{
					sale_ttl+=Float.parseFloat(resultSet8.getString("total"));
					vat_val+=Float.parseFloat(resultSet8.getString("vat"));
				}
			
	        
		        String tax_output="";
		        String cost_sale_acc="";
		        String inv_acc="";
		        String query28="SELECT * FROM "+company+".mr_assign_account_group where acc_store='"+store_name+"' and status='1' "; 
				Statement statement28=con1.createStatement();  
		        ResultSet resultSet28 = statement28.executeQuery(query28);
		        if(resultSet28.next())
		        {
		        	tax_output=resultSet28.getString("tax_output");
		        	cost_sale_acc=resultSet28.getString("cost_sale_acc");
		        	inv_acc=resultSet28.getString("inv_acc");
		        }
		        String pay1_name="";
		        String pay2_name="";
		        String pay3_name="";
		        String pay4_name="";
		        String pay5_name="";
		        String pay6_name="";
		        String pay7_name="";
		        String pay8_name="";
		        String pay9_name="";
		        String pay10_name="";
		        String pay11_name="";
		        String pay12_name="";
		        String pay13_name="";
		        String pay14_name="";
		        String pay15_name="";
		        
		        String sale_acc="";
		        String output_tax="";
		        String cost_sale="";
		        String inv_stock="";
		        
		        count=count+20;
		        progressBar.setValue(count);
	        
	        
		        String query29="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_store='"+store_name+"'"; 
				Statement statement29=con1.createStatement();  
		        ResultSet resultSet29= statement29.executeQuery(query29);
		        while(resultSet29.next())
		        {
		        	if(!pay1.equals(""))
 		        	{
 		        		if(pay1.equals(resultSet29.getString("acc_code")))
	 		        	{
	 		        		pay1_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	
 		        	if(!pay2.equals(""))
 		        	{
 		        		if(pay2.equals(resultSet29.getString("acc_code")))
	 		        	{
 		        			pay2_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	if(!pay3.equals(""))
 		        	{
 		        		if(pay3.equals(resultSet29.getString("acc_code")))
	 		        	{
 		        			pay3_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	if(!pay4.equals(""))
 		        	{
 		        		if(pay4.equals(resultSet29.getString("acc_code")))
	 		        	{
 		        			pay4_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	if(!pay5.equals(""))
 		        	{
 		        		if(pay5.equals(resultSet29.getString("acc_code")))
	 		        	{
 		        			pay5_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	if(!pay6.equals(""))
 		        	{
 		        		if(pay6.equals(resultSet29.getString("acc_code")))
	 		        	{
 		        			pay6_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	if(!pay7.equals(""))
 		        	{
 		        		if(pay7.equals(resultSet29.getString("acc_code")))
	 		        	{
 		        			pay7_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	if(!pay8.equals(""))
 		        	{
 		        		if(pay8.equals(resultSet29.getString("acc_code")))
	 		        	{
 		        			pay8_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	if(!pay9.equals(""))
 		        	{
 		        		if(pay9.equals(resultSet29.getString("acc_code")))
	 		        	{
 		        			pay9_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	if(!pay10.equals(""))
 		        	{
 		        		if(pay10.equals(resultSet29.getString("acc_code")))
	 		        	{
 		        			pay10_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	if(!pay11.equals(""))
 		        	{
 		        		if(pay11.equals(resultSet29.getString("acc_code")))
	 		        	{
 		        			pay11_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	if(!pay12.equals(""))
 		        	{
 		        		if(pay12.equals(resultSet29.getString("acc_code")))
	 		        	{
 		        			pay12_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	if(!pay13.equals(""))
 		        	{
 		        		if(pay13.equals(resultSet29.getString("acc_code")))
	 		        	{
 		        			pay13_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	if(!pay14.equals(""))
 		        	{
 		        		if(pay14.equals(resultSet29.getString("acc_code")))
	 		        	{
 		        			pay14_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	if(!pay15.equals(""))
 		        	{
 		        		if(pay15.equals(resultSet29.getString("acc_code")))
	 		        	{
 		        			pay15_name=resultSet29.getString("acc_name");
	 		        	}
 		        	}
 		        	
 		        	if(store_sale_acc.equals(resultSet29.getString("acc_code")))
 		        	{
 		        		sale_acc=resultSet29.getString("acc_name");
 		        	}
 		        	if(tax_output.equals(resultSet29.getString("acc_code")))
 		        	{
 		        		output_tax=resultSet29.getString("acc_name");
 		        	}
 		        	if(cost_sale_acc.equals(resultSet29.getString("acc_code")))
 		        	{
 		        		cost_sale=resultSet29.getString("acc_name");
 		        	}
 		        	if(inv_acc.equals(resultSet29.getString("acc_code")))
 		        	{
 		        		inv_stock=resultSet29.getString("acc_name");
 		        	}
		        }
	        
		        String branch="";
		        String branch_query = "select * from "+company+".mr_acc_segment where store_id='"+store_name+"' ";
				Statement statement33=con1.createStatement();  
				ResultSet resultSet33 = statement33.executeQuery(branch_query);
			    if (resultSet33.next())
			    {
			    	if(resultSet33.getString("segment").equals("Branch"))
			    	{
			    		branch=resultSet33.getString("name");
			    	}
			    }
		    
			    String seqnum=null;
				String transseq = "select tran_seq from "+company+".mr_transaction_seq where tran_orgin='JV' ";
				Statement statement30=con1.createStatement();  
				ResultSet resultSet30 = statement30.executeQuery(transseq);
			    if (resultSet30.next())
			    {
			    	seqnum=resultSet30.getString("tran_seq");
			    	String[] seq=seqnum.split("-");
			    	DateFormat df = new SimpleDateFormat("yy");
			    	String formatdDate = df.format(Calendar.getInstance().getTime());
			    	if(!seq[1].equals(formattedDate))
			    	{
			    		seq[1]=formatdDate;
			    	}
			    	seqnum=seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+seq[3];
			    	String sqnum=Integer.toString(Integer.parseInt(seq[3])+1);
			    	StringBuilder sb = new StringBuilder();
					for (int toPrepend=6-sqnum.length(); toPrepend>0; toPrepend--) {
					    sb.append('0');
					}
					sb.append(sqnum);
			    	String save_po="update "+company+".mr_transaction_seq set modified_date='"+formattedDate+"',tran_seq='"+seq[0]+"-"+seq[1]+"-"+seq[2]+"-"+sb.toString()+"' where tran_orgin='JV' ";
					statement3.executeUpdate(save_po);
			    }
				boolean sts=false;
				String acc_nme="";
				String acc_nme1="";
			    String close_item_validate="select * from "+company+".mr_close_pay_list_history_items where ref_reg_num='"+reg_name+"' and status='0' ";
				String accs_dt = "";
				String accs_jv="";
				String ar_leger="";
				float cash_acc=0;
				ResultSet resultSet5 = statement.executeQuery(close_item_validate);
				while(resultSet5.next())
				{
					if(Double.parseDouble(resultSet5.getString("sales_amt"))!=0)	
					{
						String sales_amt=resultSet5.getString("sales_amt");
						String ex_rate=resultSet5.getString("ex_rate");
						String paycode=resultSet5.getString("paycode");
						
						if(paycode.equals("CUSTOMERPAYMENTS"))
						{
							acc_nme=pay2_name;//"CUSTOMER ACCOUNT RECEIVABLE AT "+store_name;
						}
						else if(paycode.equals(pay_code5))
						{
							acc_nme=pay5_name;
						}
						else if(paycode.equals(pay_code6))
						{
							acc_nme=pay6_name;
						}
						else if(paycode.equals(pay_code7))
						{
							acc_nme=pay7_name;
						}
						else if(paycode.equals(pay_code8))
						{
							acc_nme=pay8_name;
						}
						else if(paycode.equals(pay_code9))
						{
							acc_nme=pay9_name;
						}
						else if(paycode.equals(pay_code10))
						{
							acc_nme=pay10_name;
						}
						else if(paycode.equals(pay_code11))
						{
							acc_nme=pay11_name;
						}
						else if(paycode.equals(pay_code12))
						{
							acc_nme=pay12_name;
						}
						else if(paycode.equals(pay_code13))
						{
							acc_nme=pay13_name;
						}
						else if(paycode.equals(pay_code14))
						{
							acc_nme=pay14_name;
						}
						else if(paycode.equals(pay_code15))
						{
							acc_nme=pay15_name;
						}
						else if(paycode.equals(pay_code4))
						{
							acc_nme=pay4_name;
						}
						else if(paycode.equals(pay_code3))
						{
							acc_nme=pay3_name;
						}
						else if(paycode.equals(pay_code2))
						{
							acc_nme=pay2_name;
						}
						else
						{
							acc_nme=pay1_name;
							acc_nme1=pay1_name;
						}
						String chrt_act_mstr = "select * from "+company+".mr_chart_accnt_mstr where acc_name='"+acc_nme+"' and acc_store='"+store_name+"' ";
						ResultSet resultSet6 = statement3.executeQuery(chrt_act_mstr);
						if (resultSet6.next())
					    {
							
							if(paycode.equals("A/R"))
							{
								float totl=0;
								String default_ar_acc="";
								String acc_type=resultSet6.getString("acc_type");
								String ar_master = "select * from "+company+".mr_pos_settlement_history where type='A/R' and store_id='"+store_name+"' and reg_num='"+reg_name+"' and status='0'  ";
								ResultSet resultSet56 = statement1.executeQuery(ar_master);
								while(resultSet56.next())
								{
									String customer_master = "select * from "+company+".mr_customer_master where status='1' and cust_code='"+resultSet56.getString("cust_code")+"' ";
									ResultSet resultSet46 = statement3.executeQuery(customer_master);
									if(resultSet46.next())
									{
										String default_ar_acc_name="";
										default_ar_acc=resultSet46.getString("default_ar_acc");
										if(!default_ar_acc.equals(""))
										{
											String query39="SELECT * FROM "+company+".mr_chart_accnt_mstr where acc_store='"+store_name+"' and acc_code='"+default_ar_acc+"' "; 
											Statement statement39=con1.createStatement();  
							                ResultSet resultSet39= statement39.executeQuery(query39);
							                if(resultSet39.next())
							                {
							                	default_ar_acc_name=resultSet39.getString("acc_name");
							                }
										}
										totl=Float.parseFloat(resultSet56.getString("total"))+Float.parseFloat(resultSet56.getString("vat"));
										if(totl!=0)
										{
											accs_dt+=",('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','"+acc_type+"','"+default_ar_acc+"','"+default_ar_acc_name+"','"+totl+"','0','"+paycode+" COLLECTED AT "+reg_name+" ON  "+formattedDate+"','"+formattedDate+"','"+formattedDate+"')";
									    	accs_jv+=",('"+seqnum+"','"+seqnum+"','"+default_ar_acc+"','"+default_ar_acc_name+"','"+totl+"','0','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"')";
									    	
									    	String query37="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+default_ar_acc+"'"; 
											Statement statement37=con1.createStatement();  
							                ResultSet resultSet37= statement37.executeQuery(query37);
							                float cr=0;
							                float dr=0;
							                while(resultSet37.next())
							                {
							                	cr+=Float.parseFloat(resultSet37.getString("cr_amt"));
							                	dr+=Float.parseFloat(resultSet37.getString("dr_amt"));
							                }
							                float bal_asset=dr-cr;
											double bal=bal_asset+totl;
											ar_leger+=",('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+default_ar_acc+"','"+totl+"','0','"+bal+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' )";
											
										}
									}
								}
							}
							else if(paycode.equals("CUSTOMERPAYMENTS"))
							{
								
							}
							else if(paycode.equals("VISA"))
							{
								String ct_mstr = "select * from "+company+".mr_chart_accnt_mstr where acc_name='"+acc_nme+"' and acc_store='"+store_name+"' ";
								ResultSet resultSet66 = statement3.executeQuery(ct_mstr);
								if (resultSet66.next())
							    {
									accs_dt+=",('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','"+resultSet66.getString("acc_type")+"','"+resultSet66.getString("acc_code")+"','"+resultSet66.getString("acc_name")+"','"+sales_amt+"','0','CASH COLLECTED AT "+reg_name+" ON  "+formattedDate+"','"+formattedDate+"','"+formattedDate+"')";
							    	accs_jv+=",('"+seqnum+"','"+seqnum+"','"+resultSet66.getString("acc_code")+"','"+resultSet66.getString("acc_name")+"','"+sales_amt+"','0','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"')";
							    	String query37="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+resultSet66.getString("acc_code")+"'"; 
									Statement statement37=con1.createStatement();  
					                ResultSet resultSet37= statement37.executeQuery(query37);
					                float cr=0;
					                float dr=0;
					                while(resultSet37.next())
					                {
					                	cr+=Float.parseFloat(resultSet37.getString("cr_amt"));
					                	dr+=Float.parseFloat(resultSet37.getString("dr_amt"));
					                }
					                float bal_asset=dr-cr;
									double bal_bank=bal_asset+Float.parseFloat(sales_amt);
									ar_leger+=",('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+resultSet66.getString("acc_code")+"','"+sales_amt+"','0','"+bal_bank+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' )";
							    }
							}
							else
							{
								sts=true;
								cash_acc+=Float.parseFloat(sales_amt)*Float.parseFloat(ex_rate);
							}
					    }
					}
				}
				if(sts==true)
				{
					String chrt_act_mstr = "select * from "+company+".mr_chart_accnt_mstr where acc_name='"+acc_nme1+"' and acc_store='"+store_name+"' ";
					ResultSet resultSet6 = statement3.executeQuery(chrt_act_mstr);
					if (resultSet6.next())
				    {
						accs_dt+=",('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','"+resultSet6.getString("acc_type")+"','"+resultSet6.getString("acc_code")+"','"+resultSet6.getString("acc_name")+"','"+cash_acc+"','0','CASH COLLECTED AT "+reg_name+" ON  "+formattedDate+"','"+formattedDate+"','"+formattedDate+"')";
				    	accs_jv+=",('"+seqnum+"','"+seqnum+"','"+resultSet6.getString("acc_code")+"','"+resultSet6.getString("acc_name")+"','"+cash_acc+"','0','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"')";
				    	String query37="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+resultSet6.getString("acc_code")+"'"; 
						Statement statement37=con1.createStatement();  
		                ResultSet resultSet37= statement37.executeQuery(query37);
		                float cr=0;
		                float dr=0;
		                while(resultSet37.next())
		                {
		                	cr+=Float.parseFloat(resultSet37.getString("cr_amt"));
		                	dr+=Float.parseFloat(resultSet37.getString("dr_amt"));
		                }
		                float bal_asset=dr-cr;
						double bal=bal_asset+cash_acc;
						ar_leger+=",('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+resultSet6.getString("acc_code")+"','"+cash_acc+"','0','"+bal+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' )";
				    }
					
				}
				count=count+20;
				progressBar.setValue(count);
				double remove_vat=sale_ttl;
				String insert_acc_detls="insert into "+company+".mr_accounts_tran_detls (tran_seqence_code,tran_date,tran_time,tran_type,tran_accode,tran_acname,tran_debit_amt,tran_credit_amt,tran_remark,created_date,modified_date) values"
						+ "('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','ASSET','"+inv_acc+"','"+inv_stock+"','0','"+new_cost+"','POS SALES CLOSING AT "+reg_name+" ON "+formattedDate+"','"+formattedDate+"','"+formattedDate+"'),"
						+ "('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','EXPENSE','"+cost_sale_acc+"','"+cost_sale+"','"+new_cost+"','0','SOLD INVENTORY AT "+reg_name+"','"+formattedDate+"','"+formattedDate+"'),"
						+ "('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','INCOME','"+store_sale_acc+"','"+sale_acc+"','0','"+remove_vat+"','SALES AT "+reg_name+" ON "+formattedDate+"','"+formattedDate+"','"+formattedDate+"'),"
						+ "('"+seq_num+"','"+formattedDate+"','"+times.format(cal.getTime())+"','LIABILITY','"+tax_output+"','"+output_tax+"','0','"+vat_val+"','VAT COLLECTED AT "+reg_name+" ON "+formattedDate+"','"+formattedDate+"','"+formattedDate+"') "
						+ ""+accs_dt+" ";
				statement3.executeUpdate(insert_acc_detls);
				
				String save_jv="insert into "+company+".mr_jv_header( `jv_num`, `date`, `branch`, `username`, `doc_num`, `remark`, `notes`, `store_id`, `created_date`, `modified_date`, `status`,`is_approved`, `approved_by`, `is_posted`, `posted_by`, `posted_date`, `approved_date`,jv_amt,vendor_cust_id) "
						+ "values('"+seqnum+"','"+formattedDate+"','"+branch+"','"+username+"','"+seq_num+"','"+seq_num+"','"+seq_num+"','"+store_name+"','"+formattedDate+"','"+formattedDate+"','Posted','1','"+username+"','1','"+username+"','"+formattedDate+"','"+formattedDate+"','"+sale_ttl+"','"+Close_sales.textField.getText()+"' )";
				statement3.executeUpdate(save_jv);
				
				String queryjd="insert into "+company+".mr_jv_details( jv_seq,`ref_jv_num`, `ac_code`, `ac_desc`, `dr_amt`, `cr_amt`, `note`, `remark`, `created_date`, `modified_date`,status,cheque_date) VALUES "
						+ "('"+seqnum+"','"+seqnum+"','"+inv_acc+"','"+inv_stock+"','0','"+new_cost+"','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"'),"
						+ "('"+seqnum+"','"+seqnum+"','"+cost_sale_acc+"','"+cost_sale+"','"+new_cost+"','0','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"'),"
						+ "('"+seqnum+"','"+seqnum+"','"+store_sale_acc+"','"+sale_acc+"','0','"+remove_vat+"','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"'),"
						+ "('"+seqnum+"','"+seqnum+"','"+tax_output+"','"+output_tax+"','0','"+vat_val+"','"+seq_num+"','"+seq_num+"','"+formattedDate+"','"+formattedDate+"','OPEN','"+formattedDate+"')"
						+ " "+accs_jv+" ";
				statement3.executeUpdate(queryjd);
				
				String query38="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+inv_acc+"'"; 
				Statement statement38=con1.createStatement();  
		        ResultSet resultSet38= statement38.executeQuery(query38);
		        float cr_pay=0;
		        float dr_pay=0;
		        while(resultSet38.next())
		        {
		        	cr_pay+=Float.parseFloat(resultSet38.getString("cr_amt"));
		        	dr_pay+=Float.parseFloat(resultSet38.getString("dr_amt"));
		        }
				float bal_liable=dr_pay-cr_pay;
				double bal_lia=bal_liable-new_cost;
				
				String query39="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+cost_sale_acc+"'"; 
				Statement statement39=con1.createStatement();  
		        ResultSet resultSet39= statement39.executeQuery(query39);
		        float cr_pay9=0;
		        float dr_pay9=0;
		        while(resultSet39.next())
		        {
		        	cr_pay9+=Float.parseFloat(resultSet39.getString("cr_amt"));
		        	dr_pay9+=Float.parseFloat(resultSet39.getString("dr_amt"));
		        }
				float bal_cost=dr_pay9-cr_pay9;
				double bal_cost_ttl=bal_cost-new_cost;
				
				
				String query37="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+store_sale_acc+"'"; 
				Statement statement37=con1.createStatement();  
		        ResultSet resultSet37= statement37.executeQuery(query37);
		        float cr=0;
		        float dr=0;
		        while(resultSet37.next())
		        {
		        	cr+=Float.parseFloat(resultSet37.getString("cr_amt"));
		        	dr+=Float.parseFloat(resultSet37.getString("dr_amt"));
		        }
				float bal_asset=dr-cr;
				double bal=bal_asset-remove_vat;
				
				String query41="SELECT * FROM "+company+".mr_acc_ledger where ac_code='"+tax_output+"'"; 
				Statement statement41=con1.createStatement();  
		        ResultSet resultSet41= statement41.executeQuery(query41);
		        float cr_pay41=0;
		        float dr_pay41=0;
		        while(resultSet41.next())
		        {
		        	cr_pay41+=Float.parseFloat(resultSet41.getString("cr_amt"));
		        	dr_pay41+=Float.parseFloat(resultSet41.getString("dr_amt"));
		        }
				float baltax=dr_pay41-cr_pay41;
				double baltax_ttl=baltax-vat_val;
				
				String save_ledger="insert into "+company+".mr_acc_ledger (`jv_num`, `tran_date`, `post_date`, `ac_code`, `dr_amt`, `cr_amt`, `balance`, `notes`, `remark`, `source`, `created_date`, `modified_date`,status) values "
						+ "('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+cost_sale_acc+"','"+new_cost+"','0','"+bal_cost_ttl+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' ),"
						+ "('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+store_sale_acc+"','0','"+remove_vat+"','"+bal+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' ),"
						+ "('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+inv_acc+"','0','"+new_cost+"','"+bal_lia+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' ),"
						+ "('"+seqnum+"','"+formattedDate+"','"+formattedDate+"','"+tax_output+"','0','"+vat_val+"','"+baltax_ttl+"','"+seq_num+"','"+seq_num+"','SA','"+formattedDate+"','"+formattedDate+"','OPEN' ) "
						+ ""+ar_leger+" ";
				statement3.executeUpdate(save_ledger);
				
				
		//		String closed_validate="update "+company+".mr_close_pay_list_items set status='1' where ref_reg_num='"+reg_name+"' ";
		//		statement.executeUpdate(closed_validate);
				
				String his_close_validate="select * from "+company+".mr_close_pay_list_history where ref_reg_num='"+reg_name+"' and status='0' ";
				ResultSet resultSet17 = statement.executeQuery(his_close_validate);
				while(resultSet17.next())
				{
					if(!server_type.equals("BO"))
					{
						String hist_reg="insert into "+company+".mr_close_pay_list_history( `ref_id`, `ref_reg_num`, `username`, `store`, `computer`, `date_time`, `invoice_num`, `remarks`, `total_sale_amt`, `total_vat`, `total_disc`, `total_cost`, `created_date`, `pay_code1`, `open_amt1`, `sales_amt1`, `total_amt1`, `ex_rate1`, `varience1`, `pay_code2`, `open_amt2`, `sales_amt2`, `total_amt2`, `ex_rate2`, `varience2`, `pay_code3`, `open_amt3`, `sales_amt3`, `total_amt3`, `ex_rate3`, `varience3`, `pay_code4`, `open_amt4`, `sales_amt4`, `total_amt4`, `ex_rate4`, `varience4`, `pay_code5`, `open_amt5`, `sales_amt5`, `total_amt5`, `ex_rate5`, `varience5`, `pay_code6`, `open_amt6`, `sales_amt6`, `total_amt6`, `ex_rate6`, `varience6`, `pay_code7`, `open_amt7`, `sales_amt7`, `total_amt7`, `ex_rate7`, `varience7`, `pay_code8`, `open_amt8`, `sales_amt8`, `total_amt8`, `ex_rate8`, `varience8`, `pay_code9`, `open_amt9`, `sales_amt9`, `total_amt9`, `ex_rate9`, `varience9`, `pay_code10`, `open_amt10`, `sales_amt10`, `total_amt10`, `ex_rate10`, `varience10`, `pay_code11`, `open_amt11`, `sales_amt11`, `total_amt11`, `ex_rate11`, `varience11`, `pay_code12`, `open_amt12`, `sales_amt12`, `total_amt12`, `ex_rate12`, `varience12`, `pay_code13`, `open_amt13`, `sales_amt13`, `total_amt13`, `ex_rate13`, `varience13`, `pay_code14`, `open_amt14`, `sales_amt14`, `total_amt14`, `ex_rate14`, `varience14`, `pay_code15`, `open_amt15`, `sales_amt15`, `total_amt15`, `ex_rate15`, `varience15`, `status`,close_date) values ('"+resultSet17.getString("ref_id")+"','"+resultSet17.getString("ref_reg_num")+"','"+resultSet17.getString("username")+"','"+resultSet17.getString("store")+"','"+resultSet17.getString("computer")+"','"+resultSet17.getString("date_time")+"','"+resultSet17.getString("invoice_num")+"','"+seqnum+"','"+resultSet17.getString("total_sale_amt")+"','"+resultSet17.getString("total_vat")+"','"+resultSet17.getString("total_disc")+"','"+resultSet17.getString("total_cost")+"','"+resultSet17.getString("created_date")+"','"+resultSet17.getString("pay_code1")+"','"+resultSet17.getString("open_amt1")+"','"+resultSet17.getString("sales_amt1")+"','"+resultSet17.getString("total_amt1")+"','"+resultSet17.getString("ex_rate1")+"','"+resultSet17.getString("varience1")+"','"+resultSet17.getString("pay_code2")+"','"+resultSet17.getString("open_amt2")+"','"+resultSet17.getString("sales_amt2")+"','"+resultSet17.getString("total_amt2")+"','"+resultSet17.getString("ex_rate2")+"','"+resultSet17.getString("varience2")+"','"+resultSet17.getString("pay_code3")+"','"+resultSet17.getString("open_amt3")+"','"+resultSet17.getString("sales_amt3")+"','"+resultSet17.getString("total_amt3")+"','"+resultSet17.getString("ex_rate3")+"','"+resultSet17.getString("varience3")+"','"+resultSet17.getString("pay_code4")+"','"+resultSet17.getString("open_amt4")+"','"+resultSet17.getString("sales_amt4")+"','"+resultSet17.getString("total_amt4")+"','"+resultSet17.getString("ex_rate4")+"','"+resultSet17.getString("varience4")+"','"+resultSet17.getString("pay_code5")+"','"+resultSet17.getString("open_amt5")+"','"+resultSet17.getString("sales_amt5")+"','"+resultSet17.getString("total_amt5")+"','"+resultSet17.getString("ex_rate5")+"','"+resultSet17.getString("varience5")+"','"+resultSet17.getString("pay_code6")+"','"+resultSet17.getString("open_amt6")+"','"+resultSet17.getString("sales_amt6")+"','"+resultSet17.getString("total_amt6")+"','"+resultSet17.getString("ex_rate6")+"','"+resultSet17.getString("varience6")+"','"+resultSet17.getString("pay_code7")+"','"+resultSet17.getString("open_amt7")+"','"+resultSet17.getString("sales_amt7")+"','"+resultSet17.getString("total_amt7")+"','"+resultSet17.getString("ex_rate7")+"','"+resultSet17.getString("varience7")+"','"+resultSet17.getString("pay_code8")+"','"+resultSet17.getString("open_amt8")+"','"+resultSet17.getString("sales_amt8")+"','"+resultSet17.getString("total_amt8")+"','"+resultSet17.getString("ex_rate8")+"','"+resultSet17.getString("varience8")+"','"+resultSet17.getString("pay_code9")+"','"+resultSet17.getString("open_amt9")+"','"+resultSet17.getString("sales_amt9")+"','"+resultSet17.getString("total_amt9")+"','"+resultSet17.getString("ex_rate9")+"','"+resultSet17.getString("varience9")+"','"+resultSet17.getString("pay_code10")+"','"+resultSet17.getString("open_amt10")+"','"+resultSet17.getString("sales_amt10")+"','"+resultSet17.getString("total_amt10")+"','"+resultSet17.getString("ex_rate10")+"','"+resultSet17.getString("varience10")+"','"+resultSet17.getString("pay_code11")+"','"+resultSet17.getString("open_amt11")+"','"+resultSet17.getString("sales_amt11")+"','"+resultSet17.getString("total_amt11")+"','"+resultSet17.getString("ex_rate11")+"','"+resultSet17.getString("varience11")+"','"+resultSet17.getString("pay_code12")+"','"+resultSet17.getString("open_amt12")+"','"+resultSet17.getString("sales_amt12")+"','"+resultSet17.getString("total_amt12")+"','"+resultSet17.getString("ex_rate12")+"','"+resultSet17.getString("varience12")+"','"+resultSet17.getString("pay_code13")+"','"+resultSet17.getString("open_amt13")+"','"+resultSet17.getString("sales_amt13")+"','"+resultSet17.getString("total_amt13")+"','"+resultSet17.getString("ex_rate13")+"','"+resultSet17.getString("varience13")+"','"+resultSet17.getString("pay_code14")+"','"+resultSet17.getString("open_amt14")+"','"+resultSet17.getString("sales_amt14")+"','"+resultSet17.getString("total_amt14")+"','"+resultSet17.getString("ex_rate14")+"','"+resultSet17.getString("varience14")+"','"+resultSet17.getString("pay_code15")+"','"+resultSet17.getString("open_amt15")+"','"+resultSet17.getString("sales_amt15")+"','"+resultSet17.getString("total_amt15")+"','"+resultSet17.getString("ex_rate15")+"','"+resultSet17.getString("varience15")+"','1','"+resultSet17.getString("close_date")+"')";
						statement3.executeUpdate(hist_reg);
					}
					String up_reg="update "+company+".mr_close_pay_list_history set status='1',remarks='"+seqnum+"' where ref_id='"+resultSet17.getString("ref_id")+"' ";
					statement1.executeUpdate(up_reg);
				}
				count=count+20;
		        progressBar.setValue(count);
		        
		        
				
				String his_close_itm_validate="select * from "+company+".mr_close_pay_list_history_items where ref_reg_num='"+reg_name+"' and status='0' ";
				ResultSet resultSet18 = statement.executeQuery(his_close_itm_validate);
				while(resultSet18.next())
				{
					if(!server_type.equals("BO"))
					{
						String hist_reg="insert into "+company+".mr_close_pay_list_history_items (`ref_reg_num`, `date_time`, `paycode`, `openings`, `sales_amt`, `total_amt`, `ex_rate`, `settle_amt`, `varience`, `sale_amt`, `vat_amt`, `created_date`, `status`,jv_num) values('"+resultSet18.getString("ref_reg_num")+"','"+resultSet18.getString("date_time")+"','"+resultSet18.getString("paycode")+"','"+resultSet18.getString("openings")+"','"+resultSet18.getString("sales_amt")+"','"+resultSet18.getString("total_amt")+"','"+resultSet18.getString("ex_rate")+"','"+resultSet18.getString("settle_amt")+"','"+resultSet18.getString("varience")+"','"+resultSet18.getString("sale_amt")+"','"+resultSet18.getString("vat_amt")+"','"+resultSet18.getString("created_date")+"','1','"+seqnum+"')";
						statement3.executeUpdate(hist_reg);
					}
					
					String up_reg="update "+company+".mr_close_pay_list_history_items set status='1',jv_num='"+seqnum+"' where id='"+resultSet18.getString("id")+"' ";
					statement1.executeUpdate(up_reg);
				}
				
				/*
				 * History table trans
				 * 
				 */
				String card_validate="select * from "+company+".mr_register_openings_history where reg_num='"+reg_name+"' and status='0' ";
				ResultSet resultSet9 = statement.executeQuery(card_validate);
				while(resultSet9.next())
				{
					if(!server_type.equals("BO"))
					{
						String hist_reg="insert into "+company+".mr_register_openings_history(`reg_num`, `store_name`, `user_name`, `open_receipt`, `remarks`, `date_time`, `created_date`, `status`)values('"+resultSet9.getString("reg_num")+"','"+resultSet9.getString("store_name")+"','"+resultSet9.getString("user_name")+"','"+resultSet9.getString("open_receipt")+"','"+resultSet9.getString("remarks")+"','"+resultSet9.getString("date_time")+"','"+resultSet9.getString("created_date")+"','1')";
						statement3.executeUpdate(hist_reg);
					}
					
					String up_reg="update "+company+".mr_register_openings_history set status='1' where id='"+resultSet9.getString("id")+"' ";
					statement1.executeUpdate(up_reg);
				}
				String reg_validate="select * from "+company+".mr_register_open_items_history where ref_reg_num='"+reg_name+"' and status='0' ";
				ResultSet resultSet10 = statement.executeQuery(reg_validate);
				while(resultSet10.next())
				{
					if(!server_type.equals("BO"))
					{
					String hist_reg="insert into "+company+".mr_register_open_items_history(`ref_reg_num`, `date_time`, `paycode`, `pay_desc`, `amount`, `created_date`, `status`) values('"+resultSet10.getString("ref_reg_num")+"','"+resultSet10.getString("date_time")+"','"+resultSet10.getString("paycode")+"','"+resultSet10.getString("pay_desc")+"','"+resultSet10.getString("amount")+"','"+resultSet10.getString("created_date")+"','1')";
					statement3.executeUpdate(hist_reg);
					}
					String up_reg="update "+company+".mr_register_open_items_history set status='1' where id='"+resultSet10.getString("id")+"' ";
					statement3.executeUpdate(up_reg);
					String upd_reg="update "+company+".mr_register_open_items_history set status='1' where id='"+resultSet10.getString("id")+"' ";
					statement1.executeUpdate(upd_reg);
				}
				/*
				pay in & out
				customer
				*/
				String pay_validate="select * from "+company+".mr_paylist where status='1' and ref_reg_no='"+reg_name+"' ";
				ResultSet resultSet11 = statement.executeQuery(pay_validate);
				while(resultSet11.next())
				{
					if(!server_type.equals("BO"))
					{
					String hist_reg="insert into "+company+".mr_paylist( `type`, `reason_code`, `reason`, `amount`, `ref_reg_no`, `user`, `status`, `created_date`, `time`, `store_id`) values('"+resultSet11.getString("type")+"','"+resultSet11.getString("reason_code")+"','"+resultSet11.getString("reason")+"','"+resultSet11.getString("amount")+"','"+resultSet11.getString("ref_reg_no")+"','"+resultSet11.getString("user")+"','"+resultSet11.getString("status")+"','"+resultSet11.getString("created_date")+"','"+resultSet11.getString("time")+"','"+resultSet11.getString("store_id")+"')";
					statement3.executeUpdate(hist_reg);
					}
					String pay_reg="update "+company+".mr_paylist set status='2' where id='"+resultSet11.getString("id")+"'";
					statement1.executeUpdate(pay_reg);
				}
				String cust_validate="select * from "+company+".mr_customer_payment where status='1' and ref_reg_num='"+reg_name+"' ";
				ResultSet resultSet12 = statement.executeQuery(cust_validate);
				while(resultSet12.next())
				{
					if(!server_type.equals("BO"))
					{
					String cust_inst="insert into "+company+".mr_customer_payment(`seq_num`, `cust_code`, `amount`, `discount`, `paycode`, `remarks`, `ref_reg_num`, `status`, `store_id`, `username`, `created_date`, `time`) values('"+resultSet12.getString("seq_num")+"','"+resultSet12.getString("cust_code")+"','"+resultSet12.getString("amount")+"','"+resultSet12.getString("discount")+"','"+resultSet12.getString("paycode")+"','"+resultSet12.getString("remarks")+"','"+resultSet12.getString("ref_reg_num")+"','"+resultSet12.getString("status")+"','"+resultSet12.getString("store_id")+"','"+resultSet12.getString("username")+"','"+resultSet12.getString("created_date")+"','"+resultSet12.getString("time")+"')";
					statement3.executeUpdate(cust_inst);
					}
					String hist_reg="update "+company+".mr_customer_payment set status='2' where id='"+resultSet12.getString("id")+"'";
					statement1.executeUpdate(hist_reg);
				}
				/*
				pos table
				pos items table
				paycode
				update qty
				tax update
				*/
				Statement statement50=con.createStatement();
//				String pos_validate="select * from "+company+".mr_pos_settlement_history where reg_num='"+reg_name+"' and status='0' and type!='VOID' ";
//				ResultSet resultSet13 = statement50.executeQuery(pos_validate);
//				while(resultSet13.next())
//				{
//					if(!server_type.equals("BO"))
//					{
//					String hist_reg="insert into "+company+".mr_pos_settlement_history(`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, `cash_paid`, `type`, `paycode_disc`, `store_id`, `created_date`, `status`,grand_total,cust_code,cust_prev_balance) values('"+resultSet13.getString("reg_num")+"','"+resultSet13.getString("receipt_no")+"','"+resultSet13.getString("user")+"','"+resultSet13.getString("time")+"','"+resultSet13.getString("total")+"','"+resultSet13.getString("cust_name")+"','"+resultSet13.getString("vat")+"','"+resultSet13.getString("disc")+"','"+resultSet13.getString("qty")+"','"+resultSet13.getString("cash_paid")+"','"+resultSet13.getString("type")+"','"+resultSet13.getString("paycode_disc")+"','"+resultSet13.getString("store_id")+"','"+resultSet13.getString("created_date")+"','1','"+resultSet13.getString("grand_total")+"','"+resultSet13.getString("cust_code")+"','"+resultSet13.getString("cust_prev_balance")+"')";
//					statement3.executeUpdate(hist_reg);
//					}
//					
//				}
				
				String up_reg2="update "+company+".mr_pos_settlement_history set status='1',jv_num='"+seqnum+"' where reg_num='"+reg_name+"' and status='0' ";
				statement1.executeUpdate(up_reg2);
				
				Statement statement550=con.createStatement();
				String pos_count="select count(*) as total from "+company+".mr_pos_settlement_history_items inner join "+company+".mr_pos_settlement_history on mr_pos_settlement_history.reg_num=mr_pos_settlement_history_items.ref_reg_num and mr_pos_settlement_history.receipt_no=mr_pos_settlement_history_items.ref_receipt_num where ref_reg_num='"+reg_name+"' and mr_pos_settlement_history_items.status='0' group by item_code";
				ResultSet resultSet113 = statement550.executeQuery(pos_count);
				if(resultSet113.next())
				{
					int a=1;
					double pamt=0;
					double progamt=0;
					int ttl_count1=Integer.parseInt(resultSet113.getString("total"));
					double counts=ttl_count1/100;
					double pcount=ttl_count1/20;
					String pos_itm_validate="select mr_pos_settlement_history_items.id,cust_name,cust_code,item_code,barcode,sum(mr_pos_settlement_history_items.qty) as qty_item,unit,item_cost,unit_price,sum(mr_pos_settlement_history_items.total) as total,mr_pos_settlement_history_items.cf from "+company+".mr_pos_settlement_history_items inner join "+company+".mr_pos_settlement_history on mr_pos_settlement_history.reg_num=mr_pos_settlement_history_items.ref_reg_num and mr_pos_settlement_history.receipt_no=mr_pos_settlement_history_items.ref_receipt_num where ref_reg_num='"+reg_name+"' and mr_pos_settlement_history_items.status='0' group by item_code,unit ";
					ResultSet resultSet14 = statement50.executeQuery(pos_itm_validate);
					while(resultSet14.next())
					{
						a++;
						progamt=60+(a/pcount);
						progressBar.setValue((int)progamt);
						
						pamt=a/counts;
		    			progressBar_1.setValue((int)pamt);
		    			
		    			String cust_name=resultSet14.getString("cust_name");
						String cust_code=resultSet14.getString("cust_code");
		    			
						String itemcode=resultSet14.getString("item_code");
						String qty=resultSet14.getString("qty_item");
						String unit=resultSet14.getString("unit");
						String item_cost=resultSet14.getString("item_cost");
						String unit_price=resultSet14.getString("unit_price");
						//String total=resultSet14.getString("total");
						String cf=resultSet14.getString("cf");
//						if(!server_type.equals("BO"))
//						{
//						String hist_reg="insert into "+company+".mr_pos_settlement_history_items (`ref_receipt_num`, `ref_reg_num`, `barcode`, `item_desc`, `item_code`, `item_cost`, `unit`, `unit_price`, `sp`, `qty`, `total`, `vat`, `discount`, `created_date`, `status`,`vat_type`, `vat_perc`, `disc_type`) values ('"+resultSet14.getString("ref_receipt_num")+"','"+resultSet14.getString("ref_reg_num")+"','"+resultSet14.getString("barcode")+"','"+resultSet14.getString("item_desc")+"','"+resultSet14.getString("item_code")+"','"+resultSet14.getString("item_cost")+"','"+resultSet14.getString("unit")+"','"+resultSet14.getString("unit_price")+"','"+resultSet14.getString("sp")+"','"+resultSet14.getString("qty")+"','"+resultSet14.getString("total")+"','"+resultSet14.getString("vat")+"','"+resultSet14.getString("discount")+"','"+resultSet14.getString("created_date")+"','1','"+resultSet14.getString("vat_type")+"','"+resultSet14.getString("vat_perc")+"','"+resultSet14.getString("disc_type")+"')";
//						statement3.executeUpdate(hist_reg);
//						}
						if(Close_sales.assembly_of_item.equals("0"))
						{
							String kititm_validate="select * from "+company+".mr_kit_items where parent_itemcode='"+itemcode+"' ";
							Statement statement125=con1.createStatement();
							ResultSet resultSet125 = statement125.executeQuery(kititm_validate);
							if(resultSet125.next())
							{
								Statement statement150=con1.createStatement();
								ResultSet resultSet150 = statement150.executeQuery(kititm_validate);
								while(resultSet150.next())
								{
									
									String sp=resultSet150.getString("sp");
									String child_barcode=resultSet150.getString("child_barcode");
									String cost=resultSet150.getString("cost");
									String kit_qty=resultSet150.getString("qty");
									double ttl_price=Double.parseDouble(kit_qty)*Double.parseDouble(sp);
									
									String itmvalidate="select * from "+company+".mr_tmp_item_master where item_code='"+resultSet150.getString("child_itemcode")+"' ";
									Statement statement151=con1.createStatement();
									ResultSet resultSet151 = statement151.executeQuery(itmvalidate);
									if(resultSet151.next())
									{
										String total_qty=resultSet151.getString("qty"); 
										float newqty=Float.parseFloat(total_qty)-Float.parseFloat(qty);
										String update_qty="update "+company+".mr_item_master set total_qty='"+newqty+"',modified_date='"+formattedDate+"' where item_code='"+resultSet150.getString("child_itemcode")+"'  ";
										statement4.executeUpdate(update_qty);
										String update_temp="update "+company+".mr_tmp_item_master set qty='"+newqty+"' where item_code='"+resultSet150.getString("child_itemcode")+"'  ";
										statement4.executeUpdate(update_temp);
										if(server_type.equals("BO"))
										{
											String query11="insert into "+company+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) "
													+ "values('"+resultSet150.getString("child_itemcode")+"','SA','"+seq_num+"','"+kit_qty+"','"+unit+"','1','"+cost+"','"+(Double.parseDouble(cost)*Float.parseFloat(kit_qty))+"','"+qty+"','"+sp+"','"+ttl_price+"','KIT SALE','"+username+"','"+formattedDate+"','"+child_barcode+"','"+cust_code+"','"+cust_name+"')";
											statement3.executeUpdate(query11);
										}
										
									}
								}
								String update_qty="update "+company+".mr_item_master set total_qty='0',modified_date='"+formattedDate+"' where item_code='"+itemcode+"'  ";
								statement4.executeUpdate(update_qty);
								String update_temp="update "+company+".mr_tmp_item_master set qty='0' where item_code='"+itemcode+"'  ";
								statement4.executeUpdate(update_temp);
							}
						}
						
						if(server_type.equals("BO"))
						{
							//String query11="insert into "+company+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) 
							//		values('"+itemcode+"','SA','"+seq_num+"','"+qty+"','"+unit+"','"+cf+"','"+item_cost+"','"+(Double.parseDouble(item_cost)*Float.parseFloat(qty))+"','"+qty+"','"+unit_price+"','"+Double.parseDouble(total)+"','Recieved "+seq_num+"','"+username+"','"+formattedDate+"','"+resultSet14.getString("barcode")+"','"+cust_code+"','"+cust_name+"')";
							String query11="insert into "+company+".mr_item_master_history(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`,barcode,cust_id,cust_name) "
									+ "values('"+itemcode+"','SA','"+seq_num+"','"+qty+"','"+unit+"','"+cf+"','"+item_cost+"','"+(Double.parseDouble(item_cost)*Float.parseFloat(qty)*Float.parseFloat(cf))+"','"+qty+"','"+unit_price+"','"+(Double.parseDouble(unit_price)*Float.parseFloat(qty))+"','Recieved "+seq_num+"','"+username+"','"+formattedDate+"','"+resultSet14.getString("barcode")+"','"+cust_code+"','"+cust_name+"')";
							
							statement3.executeUpdate(query11);
						}
					}
					String up_reg1="update "+company+".mr_pos_settlement_history_items set status='1' where ref_reg_num='"+reg_name+"' and status='0'";
					statement1.executeUpdate(up_reg1);
				}
				
				
				count=count+20;
		        progressBar.setValue(count);
		       
				String sale_tax="insert into "+company+".mr_sales_tax (`reg_num`, `sales_total`, `tax_amount`, `sale_date`, `remark`, `created_date`) values('"+reg_name+"','"+net_sale+"','"+vat_ttl+"','"+formattedDate+"','sales closed by "+username+"','"+formattedDate+"')";
				statement3.executeUpdate(sale_tax);
				
				
				String paycode_validate="select * from "+company+".mr_paycode_settlement_history where ref_reg_num='"+reg_name+"' and status='0' ";
				ResultSet resultSet16 = statement.executeQuery(paycode_validate);
				while(resultSet16.next())
				{
//					if(!server_type.equals("BO"))
//					{
//					String hist_reg="insert into "+company+".mr_paycode_settlement_history ( `ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`, `status`) values ('"+resultSet16.getString("ref_reg_num")+"','"+resultSet16.getString("ref_receipt_num")+"','"+resultSet16.getString("paycode_name")+"','"+resultSet16.getString("paycode_rate")+"','"+resultSet16.getString("paycode_amt")+"','"+resultSet16.getString("paid_amt")+"','"+resultSet16.getString("created_date")+"','1') ";
//					statement3.executeUpdate(hist_reg);
//					}
					
					String up_reg="update "+company+".mr_paycode_settlement_history set status='1' where id='"+resultSet16.getString("id")+"'";
					statement1.executeUpdate(up_reg);
				}
				String register_validate="select * from "+company+".mr_register_setup where register_number='"+reg_name+"'";
				ResultSet resultSet69 = statement.executeQuery(register_validate);
				if(resultSet69.next())
				{
					String reg_num="update "+company+".mr_register_setup set next_num='"+resultSet69.getString("next_num")+"' where register_number='"+reg_name+"'";
					statement3.executeUpdate(reg_num);
				}
				
				count=count+20;
		        progressBar.setValue(count);
		    }
		
			
	        
	        if(count>=100)
			{
	        	//Runtime.getRuntime().exec("cmd /c start \"\" C:\\MasterRetails\\backup\\backupbatch.bat");
	    		JOptionPane.showMessageDialog(frame, "<html>Data Uploaded Successfully<br> „  Õ„Ì· «·»Ì«‰«  »‰Ã«Õ</html>");
 	        	JOptionPane.showMessageDialog(frame, "<html>Counter Closed Successfully<br> „ ≈€·«ﬁ «·⁄œ«œ »‰Ã«Õ</html>");
	        	frame.dispose();
	        	Close_sales.frame.setVisible(false);
	        	InetAddress addr = InetAddress.getLocalHost();
			    String sys_name = addr.getHostName();
			    Connection con=Dbsetup.dbconn.getConnection();
				Statement statement=con.createStatement();  
				Statement statement1=con.createStatement();  
				ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_loggin_controls where username='"+username+"' and "+company+".mr_loggin_controls.device_name='"+sys_name+"' ");
				if(resultSet.next())
				{
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
				    final String formattedDate = myDateObj.format(myFormatObj);
					String del="update "+company+".mr_loggin_controls set status='1',modified_date='"+formattedDate+"' where username='"+username+"' and status='0' and  "+company+".mr_loggin_controls.device_name='"+sys_name+"' ";
					statement1.executeUpdate(del);
				}
				con.close();
				frame.dispose();
				//Runtime.getRuntime().exec(new String[] {"cmd","/c","taskkill /im  javaw.exe /f"});
	        	Runtime.getRuntime().exec("cmd /c start \"\" C:\\MasterRetails\\MasterRetails.jar");
	        	System.exit(0);
			}
        
		}
		catch(Exception e)
		{
			System.err.println(e);
			JOptionPane.showMessageDialog( Mainpage.frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}
}