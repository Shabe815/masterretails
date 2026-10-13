package sales_master;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import org.json.JSONException;

import CafePro.Mainpage;

import com.sun.glass.events.KeyEvent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class fast_item_normal {

	public static JFrame frame;
	private JButton btnNewButton;
	private static int button_count=1;
	private static  JPanel panel = null;
	private static JPanel panel_1;
	private static JPanel panel_2;
	private static JPanel panel_3;
	private static JPanel panel_4;
	private static JPanel panel_5;
	private static JPanel panel_6;
	private static  JButton btnNewButton_2;
	private  JButton btnNewButton_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					fast_item_normal window = new fast_item_normal("mr_pro","S01", null,"","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param store_name 
	 * @param host_name 
	 * @param company 
	 * @param type 
	 * @throws JSONException 
	 */
	public fast_item_normal(String company,  String store_name,String version,String username, String type) throws JSONException {
		initialize(company,store_name, version,username,type);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param store_name 
	 * @param host_name 
	 * @param company 
	 * @param username 
	 * @param type 
	 * @throws JSONException 
	 */
	private void initialize(final String company,final String store_name,String version, final String username, final String type) throws JSONException {
		frame = new JFrame(version);
		frame.setResizable(false);
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setTitle("FAST ITEMS SCREEN");
		frame.setBounds(100, 100, 800, 725);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btnFastItems.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btnFastItems.setEnabled(true);
				}
				
			}
			@Override
		      public void windowIconified(WindowEvent we) {
		        frame.setState(JFrame.NORMAL);
		      }
		});
		frame.getContentPane().setLayout(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(23, 50, 738, 100);
		panel_1.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBounds(23, 155, 738, 100);
		panel_2.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		panel_3 = new JPanel();
		panel_3.setBounds(23, 260, 738, 100);
		panel_3.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		panel_4 = new JPanel();
		panel_4.setBounds(23, 365, 738, 100);
		panel_4.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		panel_5 = new JPanel();
		panel_5.setBounds(23, 470, 738, 100);
		panel_5.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		panel_6 = new JPanel();
		panel_6.setBounds(23, 575, 738, 100);
		panel_6.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel_6);
		panel_6.setLayout(null);
		
		btnNewButton = new JButton(".");
		btnNewButton.setBounds(0, 0, 1, 1);
		frame.getContentPane().add(btnNewButton);
		
	    btnNewButton_1 = new JButton("Previous");
	    btnNewButton_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		if(button_count!=1)
	    		{
	    			int limit_start=30;
	    			button_count=button_count-1;
	    			int limit_end=button_count*30;
	    			action(company, username, store_name, type,limit_end,limit_start,5);
	    		}
	    		else
	    		{
	    			int limit_start=30;
	    			//button_count=button_count-1;
	    			int limit_end=0;
	    			action(company, username, store_name, type,limit_end,limit_start,5);
	    		}
				
	    		
	    	}
	    });
	    btnNewButton_1.setBounds(10, 5, 89, 40);
	    frame.getContentPane().add(btnNewButton_1);
	    
	    btnNewButton_2 = new JButton("Next");
	    btnNewButton_2.setEnabled(false);
	    btnNewButton_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		int limit_start=button_count*30;
				button_count=button_count+1;
				int limit_end=30;
	    		action(company, username, store_name, type,limit_start,limit_end,5);
	    	}
	    });
	    btnNewButton_2.setBounds(695, 5, 89, 40);
	    frame.getContentPane().add(btnNewButton_2);
	    
	    int limit_start=0;
		int limit_end=30;
		action(company, username, store_name, type,limit_start,limit_end,5);
	    clickOnback( btnNewButton, "play", KeyEvent.VK_ESCAPE );
	}
	private static void action(final String company,final String username,final String store_name,final String type,int limit_start, int limit_end, int count)
	{
		int i=0;
		int x=5, y=5, width=140, height=90;
	    int itm_count=count;
	    int z=0;
	    int a=0;
	   
	    String[] item_name = null;
	    String item ="";
	    String item_disp="";
	    String item_tl="";
	    
	    SwingUtilities.updateComponentTreeUI(frame);
	    frame.invalidate();
	    frame.validate();
	    frame.repaint();
	    
	    Component[] componentList6 = panel_6.getComponents();
		for(Component c : componentList6){
			if(c instanceof JButton){

		        panel_6.remove(c);
		    }
		}
		panel_6.revalidate();
	    panel_6.repaint();
	    
	    Component[] componentList5 = panel_5.getComponents();
		for(Component c : componentList5){
			if(c instanceof JButton){

		        panel_5.remove(c);
		    }
		}
		panel_5.revalidate();
	    panel_5.repaint();
	    
	    Component[] componentList4 = panel_4.getComponents();
		for(Component c : componentList4){
			if(c instanceof JButton){

		        panel_4.remove(c);
		    }
		}
		panel_4.revalidate();
	    panel_4.repaint();
	    
	    Component[] componentList3 = panel_3.getComponents();
		for(Component c : componentList3){
			if(c instanceof JButton){

		        panel_3.remove(c);
		    }
		}
		panel_3.revalidate();
	    panel_3.repaint();
	    
	    Component[] componentList2 = panel_2.getComponents();
		for(Component c : componentList2){
			if(c instanceof JButton){

		        panel_2.remove(c);
		    }
		}
		panel_2.revalidate();
	    panel_2.repaint();
	    
	    Component[] componentList1 = panel_1.getComponents();
		for(Component c : componentList1){
			if(c instanceof JButton){

		        panel_1.remove(c);
		    }
		}
		panel_1.revalidate();
	    panel_1.repaint();
	    
	    try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="select * from "+company+".mr_tmp_item_master where fast_item='1'  group by item_code order by item_code limit "+limit_start+","+limit_end+" ";
			ResultSet resultSet1 = statement.executeQuery(query);
			if(resultSet1.next())
			{
				btnNewButton_2.setEnabled(true);
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next())
				{
					String sp=resultSet.getString("sp");
					String selling="";
					String shrt_nme=resultSet.getString("item_name");
					if(shrt_nme.length()>1)
					{
						item_name=shrt_nme.split("(?<=\\G.{15})");
						for(int v=0;v<item_name.length;v++)
						{
							if(v==0)
							{
								item_tl = item_name[v];
							}
							else
							{
								item +="</br>"+item_name[v];
							}
							selling="</br>"+sp;
							item_disp="<html>"+item_tl+item+"-"+selling+"Dhs</html>";
						}
						
						
					}
			    	if(itm_count*z==i)
			    	{
			    		if(z==0)
			    		{
			    			panel=panel_1;
			    		}
			    		else if(z==1)
			    		{
			    			panel=panel_2;
			    		}
			    		else if(z==2)
			    		{
			    			panel=panel_3;
			    		}
			    		else if(z==3)
			    		{
			    			panel=panel_4;
			    		}
			    		else if(z==4)
			    		{
			    			panel=panel_5;
			    		}
			    		else if(z==5)
			    		{
			    			panel=panel_6;
			    		}
			    		z++;
			    		x=5; y=5;
			    	}
			    	final JButton fast_btn = new JButton(item_disp);
			    	fast_btn.setBounds(x, y, width, height);
			    	fast_btn.setFont(new Font("Arial", Font.BOLD, 12));
			    	
			    	a++;
					int b=a%2;
					if(b==0)
					{
						fast_btn.setBackground(Color.lightGray);
						fast_btn.setForeground(new Color(0, 0, 0));
					}
					else
					{
						fast_btn.setBackground(Color.gray);
						fast_btn.setForeground(new Color(255, 255, 255));
					}
			    	final JLabel jlabel = new JLabel(shrt_nme);
			    	jlabel.setBounds(x, y, width, height);
			    	fast_btn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							try {
								Connection con=Dbsetup.dbconn.getConnection();
								Statement statement=con.createStatement(); 
								ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_tmp_item_master where item_name='"+jlabel.getText()+"' ");
								String barcode = "";
								if(resultSet.next())
								{
									barcode=resultSet.getString("barcode");
									if(!barcode.equals("")){
										if(Mainpage.SoftLang.equals("Ar"))
										{
											sales_master.PosscreenArabic.validate_barcode(company,barcode, username,store_name,type,con);
											if(type.equals("return"))
											{
												PosscreenArabic.btnQuickReturn_1.setText("QUICK RETURN (F8)");
												PosscreenArabic.txtDisc.setBackground(Color.BLACK);
												PosscreenArabic.txtDisc.setCaretColor(Color.WHITE);
												PosscreenArabic.txtDisc.setForeground(Color.WHITE);
												PosscreenArabic.txtDisc.requestFocus();
												PosscreenArabic.returns="";
											}
											if(PosscreenArabic.fastitem_Screen.equals("1"))
											{
												frame.setVisible(false);
												PosscreenArabic.btnFastItems.setEnabled(true);
												button_count=1;
											}
										}
										else
										{
											sales_master.posscreen.validate_barcode(company,barcode, username,store_name,type,con);
											if(type.equals("return"))
											{
												posscreen.btnQuickReturn_1.setText("QUICK RETURN (F8)");
												posscreen.txtDisc.setBackground(Color.BLACK);
												posscreen.txtDisc.setCaretColor(Color.WHITE);
												posscreen.txtDisc.setForeground(Color.WHITE);
												posscreen.txtDisc.requestFocus();
												posscreen.returns="";
											}
											if(posscreen.fastitem_Screen.equals("1"))
											{
												frame.setVisible(false);
												posscreen.btnFastItems.setEnabled(true);
												button_count=1;
											}
										}
									}
								}
								
							} catch (Exception e) {
								System.err.println(e);
							}
							
						}
					});
					panel.add(fast_btn);
			        item ="";
					i++;
					x+=145;
				}
			}
			else
			{
				button_count=button_count-1;
				btnNewButton_2.setEnabled(false);
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	public static void clickOnback(final AbstractButton button, String actionName, int key)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6694624852420723982L;

			/**
			 * 
			 */
			public void actionPerformed( ActionEvent e )
	        {
				frame.setVisible(false);
				button_count=1;
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
				}
				else
				{
					posscreen.txtDisc.requestFocus();
				}
				
	        }
	    } );
	}
}
