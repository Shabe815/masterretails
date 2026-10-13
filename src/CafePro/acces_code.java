package CafePro;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import org.json.JSONException;

public class acces_code {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					acces_code window = new acces_code(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param version 
	 * @throws Exception 
	 */
	public acces_code(String version) throws Exception {
		//initialize(version);
		
		try {
			Properties prop=new Properties();
			FileInputStream ip= new FileInputStream("C:/MasterRetails/register/reg.properties");
			prop.load(ip);
			String Regkey= prop.getProperty("Regkey");
			String Rd= prop.getProperty("Rd");
			ip.close();
			
			byte[] decodedBytes = Base64.getDecoder().decode(Rd);
			String decodeddate = new String(decodedBytes);
			
			Properties prop1=new Properties();
			FileInputStream ip1= new FileInputStream("C:/MasterRetails/config.properties");
			prop1.load(ip1);
			String server= prop1.getProperty("server");
			//String store="S04";//prop1.getProperty("store");
			String store=prop1.getProperty("store");
			String posuser= prop1.getProperty("register");
			String language= prop1.getProperty("language");
			ip.close();
			
			String company_name="";
			Connection con1=Dbsetup.dbconn.getConnection();
			Statement statement1=con1.createStatement();  
			ResultSet resultSet1 = statement1.executeQuery("select * from config_db.company");
			if(resultSet1.next())
			{
				company_name=resultSet1.getString("company_name");
			}
			con1.close();
			boolean sts=false;
			Process p = Runtime.getRuntime().exec("getmac /fo csv /nh");
		    java.io.BufferedReader in = new java.io.BufferedReader(new  java.io.InputStreamReader(p.getInputStream()));
		    String line;
		    while((line = in.readLine()) != null) {
		    	String[] result = line.split(",");
		    	String input1=getmd5data("00-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-0");
			    String input2=getmd5data("00-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-0");
			    String input3=getmd5data("00-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-1");
			    String input4=getmd5data("00-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-1");
			    String input25=getmd5data("02-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-0");
			    String input26=getmd5data("02-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-0");
			    String input27=getmd5data("02-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-1");
			    String input28=getmd5data("02-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-1");
			    String input29=getmd5data("05-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-0");
			    String input30=getmd5data("05-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-0");
			    String input31=getmd5data("05-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-1");
			    String input32=getmd5data("05-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-1");
			    String input33=getmd5data("07-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-0");
			    String input34=getmd5data("07-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-0");
			    String input35=getmd5data("07-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-1");
			    String input36=getmd5data("07-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-1");
			    String input37=getmd5data("10-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-0");
			    String input38=getmd5data("10-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-0");
			    String input39=getmd5data("10-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-1");
			    String input40=getmd5data("10-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-1");
			    String input5=getmd5data("15-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-0");
			    String input6=getmd5data("15-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-0");
			    String input7=getmd5data("15-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-1");
			    String input8=getmd5data("15-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-1");
			    String input9=getmd5data("30-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-0");
			    String input10=getmd5data("30-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-0");
			    String input11=getmd5data("30-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-1");
			    String input12=getmd5data("30-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-1");
			    String input13=getmd5data("45-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-0");
			    String input14=getmd5data("45-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-0");
			    String input15=getmd5data("45-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-1");
			    String input16=getmd5data("45-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-1");
			    String input17=getmd5data("60-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-0");
			    String input18=getmd5data("60-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-0");
			    String input19=getmd5data("60-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-1");
			    String input20=getmd5data("60-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-1");
			    String input21=getmd5data("90-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-0");
			    String input22=getmd5data("90-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-0");
			    String input23=getmd5data("90-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-0-1");
			    String input24=getmd5data("90-"+result[0].replace('"', ' ').trim()+"-"+company_name+"-1-1");
			    
			    if(Regkey.equals(input1))
	    		{
			    	String activeSD="0";
					String acc_active="0";
			    	new loginscreen(store,posuser,server, version,company_name,activeSD,acc_active,language);
			    	sts=true;
	    		}
			    
			    else if(Regkey.equals(input2))
	    		{
			    	String activeSD="1";
					String acc_active="0";
			    	new loginscreen(store,posuser,server, version,company_name,activeSD,acc_active,language);
			    	sts=true;
	    		}
			    else if(Regkey.equals(input3))
	    		{
			    	String activeSD="0";
					String acc_active="1";
			    	new loginscreen(store,posuser,server, version,company_name,activeSD,acc_active,language);
			    	sts=true;
	    		}
			    else if(Regkey.equals(input4))
	    		{
			    	String activeSD="1";
					String acc_active="1";
			    	new loginscreen(store,posuser,server, version,company_name,activeSD,acc_active,language);
			    	sts=true;
	    		}
			    else if(Regkey.equals(input25))
	    		{
			    	String activeSD="0";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,2,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input26))
	    		{
			    	String activeSD="1";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,2,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input27))
	    		{
			    	String activeSD="0";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,2,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input28))
	    		{
			    	String activeSD="1";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,2,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input29))
	    		{
			    	String activeSD="0";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,5,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input30))
	    		{
			    	String activeSD="1";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,5,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input31))
	    		{
			    	String activeSD="0";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,5,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input32))
	    		{
			    	String activeSD="1";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,5,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input33))
	    		{
			    	String activeSD="0";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,7,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input34))
	    		{
			    	String activeSD="1";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,7,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input35))
	    		{
			    	String activeSD="0";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,7,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input36))
	    		{
			    	String activeSD="1";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,7,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input37))
	    		{
			    	String activeSD="0";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,10,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input38))
	    		{
			    	String activeSD="1";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,10,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input39))
	    		{
			    	String activeSD="0";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,10,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input40))
	    		{
			    	String activeSD="1";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,10,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input5))
	    		{
			    	String activeSD="0";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,15,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input6))
	    		{
			    	String activeSD="1";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,15,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input7))
	    		{
			    	String activeSD="0";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,15,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input8))
	    		{
			    	String activeSD="1";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,15,language);
					sts=true;
	    		}
			    else  if(Regkey.equals(input9))
	    		{
			    	String activeSD="0";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,30,language);
					sts=true;
	    		}
			    else  if(Regkey.equals(input10))
	    		{
			    	String activeSD="1";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,30,language);
					sts=true;
	    		}
			    else  if(Regkey.equals(input11))
	    		{
			    	String activeSD="0";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,30,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input12))
	    		{
			    	String activeSD="1";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,30,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input13))
	    		{
			    	String activeSD="0";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,45,language);
					sts=true;
	    		}
			    else  if(Regkey.equals(input14))
	    		{
			    	String activeSD="1";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,45,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input15))
	    		{
			    	String activeSD="0";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,45,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input16))
	    		{
			    	String activeSD="1";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,45,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input17))
	    		{
			    	String activeSD="0";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,60,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input18))
	    		{
			    	String activeSD="1";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,60,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input19))
	    		{
			    	String activeSD="0";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,60,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input20))
	    		{
			    	String activeSD="1";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,60,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input21))
	    		{
			    	String activeSD="0";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,90,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input22))
	    		{
			    	String activeSD="1";
					String acc_active="0";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,90,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input23))
	    		{
			    	String activeSD="0";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,90,language);
					sts=true;
	    		}
			    else if(Regkey.equals(input24))
	    		{
			    	String activeSD="1";
					String acc_active="1";
					
					getexpiry(decodeddate,version,store,posuser,server,company_name, activeSD,acc_active,90,language);
					sts=true;
	    		}
		    }
		    //line = in.readLine(); 
		    
		    if(sts==false)
		    {
		    	JOptionPane.showMessageDialog(cafepro.frame, "Reg or Config file not found");
				initialize(version);
		    }
		    
		    
		    
		    
                
		} 
		 
		catch (Exception e) {
			JOptionPane.showMessageDialog(cafepro.frame,  "Reg / config File not found" );
			initialize(version);
		}
	}

	

	

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 */
	private void initialize(String version) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(20, 20, 980, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		textField = new JTextField(10);
		textField.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				try {
					btn_actn();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(216, 218, 723, 50);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					btn_actn();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					btn_actn();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(696, 327, 172, 49);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblCompanyName = new JLabel("Registration Key");
		lblCompanyName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCompanyName.setBounds(24, 236, 156, 43);
		frame.getContentPane().add(lblCompanyName);
		
		JLabel label = new JLabel("Company Name");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(34, 106, 148, 43);
		frame.getContentPane().add(label);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_1.setColumns(10);
		textField_1.setBounds(216, 102, 723, 49);
		frame.getContentPane().add(textField_1);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(textField_1.getText().equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Enter Shop Name");
					}
					else
					{
						Process p = Runtime.getRuntime().exec("getmac /fo csv /nh");
					    java.io.BufferedReader in = new java.io.BufferedReader(new  java.io.InputStreamReader(p.getInputStream()));
					    String line;
					    line = in.readLine();        
					    String[] result = line.split(",");
					    FileWriter excel = new FileWriter("C:\\MasterRetails\\register\\reg.properties");
						String input=result[0].replace('"', ' ').trim()+"-"+textField_1.getText().trim();
						String encodedString = Base64.getEncoder().encodeToString(input.getBytes());
						excel.write(encodedString);
						excel.close();
						JOptionPane.showMessageDialog(frame, "Registration File Generated");
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(frame, e);
				}
			}
		});
		btnGenerate.setBounds(24, 186, 109, 32);
		frame.getContentPane().add(btnGenerate);
		
		JLabel lblRegistrationPage = new JLabel("Registration Page");
		lblRegistrationPage.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblRegistrationPage.setBounds(35, 24, 251, 49);
		frame.getContentPane().add(lblRegistrationPage);
		comp_validate();
		frame.setVisible(true);
	}
	private void btn_actn() throws UnsupportedEncodingException
	{
		try {
			if(textField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Enter Licience Code");
			}
			else if(textField_1.getText().equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Enter Shop Name");
			}
			else
			{
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    String formattedDate = myDateObj.format(myFormatObj); 
				if(textField_1.isEditable()==true)
				{
					Connection con1=Dbsetup.dbconn.getConnection();
					Statement statement1=con1.createStatement();  
					ResultSet resultSet1 = statement1.executeQuery("select * from config_db.company where company_name='"+textField_1.getText()+"' ");
					if(resultSet1.next())
					{
						String query="update config_db.company set modified_date='"+formattedDate+"' where id='"+resultSet1.getString("id")+"' ";
						statement1.executeUpdate(query);
						JOptionPane.showMessageDialog(frame, "Validation Completed, Restart the Application");
					}
					else
					{
						String query="insert into config_db.company (company_name,db_name,created_date,modified_date) values('"+textField_1.getText()+"','mr_pro','"+formattedDate+"','"+formattedDate+"')";
						statement1.executeUpdate(query);
						String query1="INSERT INTO mr_pro.mr_store(`store_id`, `store_short_name`, `store_name`,city,country,land_line,ph,AllowNegativeQuantity,created_date,modified_date,barcode_label) values('S01','mr_pro','"+textField_1.getText()+"','','','','','0','"+formattedDate+"','"+formattedDate+"','Qty');";
						statement1.executeUpdate(query1);
						JOptionPane.showMessageDialog(frame, "Validation Completed, Restart the Application");
					}
					con1.close();
				}
				else
				{
					Connection con1=Dbsetup.dbconn.getConnection();
					Statement statement1=con1.createStatement();  
					ResultSet resultSet1 = statement1.executeQuery("select * from config_db.company where company_name='"+textField_1.getText()+"' ");
					if(resultSet1.next())
					{
						String query="update config_db.company set modified_date='"+formattedDate+"' where id='"+resultSet1.getString("id")+"' ";
						statement1.executeUpdate(query);
						JOptionPane.showMessageDialog(frame, "Validation Completed, Restart the Application");
					}
					else
					{
						String query="update config_db.company set modified_date='"+formattedDate+"' where id='"+resultSet1.getString("id")+"' ";
						statement1.executeUpdate(query);
						JOptionPane.showMessageDialog(frame, "Validation Completed, Restart the Application");
						
					}
					con1.close();
				}
				FileWriter excel = new FileWriter("C:\\MasterRetails\\register\\reg.properties");
				String input="Regkey="+textField.getText().trim();
				excel.write(input);
				excel.write("\r\n");
				String encodedString = Base64.getEncoder().encodeToString(formattedDate.getBytes());
				excel.write("Rd="+encodedString);
				excel.close();
				JOptionPane.showMessageDialog(frame, "Registration Completed, Restart the Application");
				System.exit(0);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, e);
		}
	}
	private void comp_validate()
	{
		try {
			Connection con1=Dbsetup.dbconn.getConnection();
			Statement statement1=con1.createStatement();  
			ResultSet resultSet1 = statement1.executeQuery("select company_name,db_name from config_db.company");
			if(resultSet1.next())
			{
				String new_obj =resultSet1.getString("company_name");
		        textField_1.setText(new_obj);
		        textField_1.setEditable(false);
			}
			con1.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private String getmd5data(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest5 = md.digest(input.getBytes()); 
		BigInteger no5 = new BigInteger(1, messageDigest5); 
		String hashtext = no5.toString(16); 
        while (hashtext.length() < 32) { 
            hashtext = "0" + hashtext; 
        } 
		return hashtext;
	}
	
	private void getexpiry(String decodeddate,String version,String store,String posuser,String server,String company_name,String  activeSD,String acc_active,int count_date,String language) throws ParseException, JSONException, SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(decodeddate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		cal.add(Calendar.DAY_OF_MONTH, count_date);  
		String newDate = sdf.format(cal.getTime());  
		Date exp_date=new SimpleDateFormat("yyyy-MM-dd").parse(newDate);
		Date date = new Date();  
		int day= exp_date.compareTo(date);
		if(day == -1)
		{
			initialize(version);
			//new loginscreen(store,posuser,server, version,company_name,  activeSD, acc_active);
		}
		else
		{
			new loginscreen(store,posuser,server, version,company_name,  activeSD, acc_active,language);
		}
	}
}
