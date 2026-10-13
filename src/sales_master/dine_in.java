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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import org.json.JSONException;

import CafePro.Mainpage;

import com.sun.glass.events.KeyEvent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class dine_in {

	private static JFrame frame;
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
					dine_in window = new dine_in("mr_pro","S01", null,"","","");
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
	public dine_in(String company,  String store_name,String version,String username, String receipt ,String oldtable) throws JSONException {
		initialize(company,store_name, version,username, receipt,oldtable);
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
	private void initialize(final String company,final String store_name,final String version, final String username, final String receipt,final String oldtable) throws JSONException {
		frame = new JFrame(version);
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setTitle("TABLE");
		frame.setBounds(100, 100, 800, 725);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		
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
	    			action(company, username, store_name, limit_end,limit_start,5, version, receipt,oldtable);
	    		}
	    		else
	    		{
	    			int limit_start=30;
	    			//button_count=button_count-1;
	    			int limit_end=0;
	    			action(company, username, store_name, limit_end,limit_start,5, version, receipt,oldtable);
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
	    		action(company, username, store_name, limit_start,limit_end,5, version, receipt,oldtable);
	    	}
	    });
	    btnNewButton_2.setBounds(695, 5, 89, 40);
	    frame.getContentPane().add(btnNewButton_2);
	    
	    int limit_start=0;
		int limit_end=30;
		action(company, username, store_name, limit_start,limit_end,5, version, receipt,oldtable);
	    clickOnback( btnNewButton, "play", KeyEvent.VK_ESCAPE );
	}
	private static void action(final String company,final String username,final String store_name,int limit_start, int limit_end, int count, final String version,final String dineregister, final String oldtable )
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
			String query="select mr_kot_tables.table_num as orginal,mr_pos_hold_invoice.table_num as selected from "+company+".mr_kot_tables left join "+company+".mr_pos_hold_invoice on mr_pos_hold_invoice.table_num=mr_kot_tables.table_num where store_code='S01'  order by mr_kot_tables.id";
			ResultSet resultSet1 = statement.executeQuery(query);
			if(resultSet1.next())
			{
				btnNewButton_2.setEnabled(true);
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next())
				{
					String shrt_nme=resultSet.getString("orginal");
					
					
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
							item_disp="<html>"+item_tl+item+"</html>";
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
					
					String selected=resultSet.getString("selected");
					if(shrt_nme.equals(selected))
					{
						fast_btn.setEnabled(false);
						fast_btn.setBackground(Color.red);
					}
					else
					{
						fast_btn.setEnabled(true);
					}
					
			    	final JLabel jlabel = new JLabel(shrt_nme);
			    	jlabel.setBounds(x, y, width, height);
			    	fast_btn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							try {
								frame.setVisible(false);
								
								Connection con=Dbsetup.dbconn.getConnection();
								Statement statement=con.createStatement();  
								ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_kot_tables where table_num='"+jlabel.getText()+"' ");
								String table_num = "";
								if(resultSet.next())
								{
									if(Mainpage.SoftLang.equals("Ar"))
									{
										String newinvnum="";
										PosscreenArabic.page_type="Kitchen KOT";
										table_num=resultSet.getString("table_num");
										Statement statement1 = con.createStatement();
										String query5="SELECT * FROM "+company+".mr_pos_hold_invoice where receipt_no='"+PosscreenArabic.textField_8.getText().trim()+"' and reg_num='"+dineregister+"' ";
										ResultSet resultSet5= statement1.executeQuery(query5);
										if(resultSet5.next())
										{
											String update="update "+company+".mr_pos_hold_invoice set table_num='"+table_num+"' where receipt_no='"+PosscreenArabic.textField_8.getText().trim()+"' and reg_num='"+dineregister+"' ";
											statement.executeUpdate(update);
											PosscreenArabic.hold_inv=true;
											PosscreenArabic.old_recipt=PosscreenArabic.textField_8.getText().trim();
											newinvnum=PosscreenArabic.textField_8.getText().trim();
											
											PosscreenArabic.hold_inv(company, username,store_name, dineregister, con,table_num,version,"","DINE IN",newinvnum);
											
											String dept="select department from "+company+".mr_pos_hold_invoice_items where ref_reg_no='"+dineregister+"' and  ref_receipt_num='"+newinvnum+"'  and print_sts='1'  group by department ";
					   						ResultSet resultSet1 = statement.executeQuery(dept);
					   						if(resultSet1.next())
					   						{
					   							String printer_dept="select printer from "+company+".mr_assign_kot_printer where reg_nme='"+dineregister+"' and dept_name='"+resultSet1.getString("department")+"' ";
					   							ResultSet resultSet2 = statement1.executeQuery(printer_dept);
					   							if(resultSet2.next())
					   							{
					   								String printer=resultSet2.getString("printer");
					   								report_master.holded_invoice.report(PosscreenArabic.textField_8.getText().trim(), con, printer,dineregister,oldtable,company,"changetable_kot_invoice",resultSet1.getString("department"),"");	
					   							}
					   							else
					   							{
					   								JOptionPane.showMessageDialog(frame, "Printer Not Assigned For This Department");
					   							}
					   						}
										}
										else
										{
//											int nxt_num=0;
//											ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+dineregister+"' ");
//											if(resultSet2.next())
//											{
//												nxt_num=Integer.parseInt(resultSet2.getString("next_num"))+1;
//												String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+dineregister+"' ";
//												statement.executeUpdate(update_receipt_num);
//											}
											//newinvnum=posscreen.textField_8.getText().trim();
											//posscreen.hold_inv=true;
//											int posinvnum=Integer.parseInt(posscreen.textField_8.getText().trim());
//											int newposnum=posinvnum+1;
											PosscreenArabic.hold_inv=true;
											//posscreen.old_recipt=posscreen.textField_8.getText().trim();
											newinvnum=PosscreenArabic.textField_8.getText().trim();
											
											PosscreenArabic.hold_inv(company, username,store_name, dineregister, con,table_num,version,"","DINE IN",newinvnum);
										}
									}
									else
									{
										String newinvnum="";
										posscreen.page_type="Kitchen KOT";
										table_num=resultSet.getString("table_num");
										Statement statement1 = con.createStatement();
										String query5="SELECT * FROM "+company+".mr_pos_hold_invoice where receipt_no='"+posscreen.textField_8.getText().trim()+"' and reg_num='"+dineregister+"' ";
										ResultSet resultSet5= statement1.executeQuery(query5);
										if(resultSet5.next())
										{
											String update="update "+company+".mr_pos_hold_invoice set table_num='"+table_num+"' where receipt_no='"+posscreen.textField_8.getText().trim()+"' and reg_num='"+dineregister+"' ";
											statement.executeUpdate(update);
											posscreen.hold_inv=true;
											posscreen.old_recipt=posscreen.textField_8.getText().trim();
											newinvnum=posscreen.textField_8.getText().trim();
											
											posscreen.hold_inv(company, username,store_name, dineregister, con,table_num,version,"","DINE IN",newinvnum,"0");
											
											String dept="select department from "+company+".mr_pos_hold_invoice_items where ref_reg_no='"+dineregister+"' and  ref_receipt_num='"+newinvnum+"'  and print_sts='1'  group by department ";
					   						ResultSet resultSet1 = statement.executeQuery(dept);
					   						if(resultSet1.next())
					   						{
					   							String printer_dept="select printer from "+company+".mr_assign_kot_printer where reg_nme='"+dineregister+"' and dept_name='"+resultSet1.getString("department")+"' ";
					   							ResultSet resultSet2 = statement1.executeQuery(printer_dept);
					   							if(resultSet2.next())
					   							{
					   								String printer=resultSet2.getString("printer");
					   								report_master.holded_invoice.report(posscreen.textField_8.getText().trim(), con, printer,dineregister,oldtable,company,"changetable_kot_invoice",resultSet1.getString("department"),"");	
					   							}
					   							else
					   							{
					   								JOptionPane.showMessageDialog(frame, "Printer Not Assigned For This Department");
					   							}
					   						}
										}
										else
										{
//											int nxt_num=0;
//											ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+dineregister+"' ");
//											if(resultSet2.next())
//											{
//												nxt_num=Integer.parseInt(resultSet2.getString("next_num"))+1;
//												String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+dineregister+"' ";
//												statement.executeUpdate(update_receipt_num);
//											}
											//newinvnum=posscreen.textField_8.getText().trim();
											//posscreen.hold_inv=true;
//											int posinvnum=Integer.parseInt(posscreen.textField_8.getText().trim());
//											int newposnum=posinvnum+1;
											posscreen.hold_inv=true;
											//posscreen.old_recipt=posscreen.textField_8.getText().trim();
											newinvnum=posscreen.textField_8.getText().trim();
											
											posscreen.hold_inv(company, username,store_name, dineregister, con,table_num,version,"","DINE IN",newinvnum,"0");
										}
									}
									
									
									
									
									
									
									
									button_count=1;
									
									
									
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
