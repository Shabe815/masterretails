package Dbsetup;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import sales_master.posscreen;

public class dbconn {
	 
    public static void main(String[] args)throws Exception 
	{
		getConnection();
	}
	@SuppressWarnings("unused")
	public static Connection getConnection() {
		Connection conn=null;
		try
		{
			String db_url = null;
			String db_url_local = null;
			String db_user = null;
			String db_pass = null;
			File configFile = new File("C:/MasterRetails/config.properties");
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);
			db_url = props.getProperty("db_url");
			db_url_local = props.getProperty("db_url_local");
			db_user = props.getProperty("user_name");
			db_pass = props.getProperty("password");//"Ali0559634355";
			reader.close();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(db_url,db_user,db_pass);
			conn.getMetaData();
			if(conn!=null)
			{
				posscreen.status="Connected";
			}
			
			if(conn==null)
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
		    	conn = DriverManager.getConnection(db_url_local,db_user,db_pass);
		    	if(conn!=null)
				{
		    		posscreen.status="Connected";
				} 
			}
		}
		catch(Exception e)
		{
			//JOptionPane.showMessageDialog(Mainpage.frame,e.getMessage());
			
			posscreen.status="Disconnected";
			//JOptionPane.showMessageDialog(Mainpage.frame,"Cannot connect to database server");
			ImageIcon icon = new ImageIcon(dbconn.class.getResource("/connection.png"));
            JOptionPane.showMessageDialog(null,"","Connection Issue", JOptionPane.INFORMATION_MESSAGE,icon);
			//JOptionPane.showMessageDialog( Mainpage.frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
		return conn;
    }

}
