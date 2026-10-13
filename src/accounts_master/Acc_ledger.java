package accounts_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import com.toedter.calendar.JDateChooser;

public class Acc_ledger {


	static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	public static JMenuItem menuItem;
	static JTextField textField;
	private JDateChooser dateChooser ;
	private JDateChooser dateChooser_1 ;
	static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Acc_ledger window = new Acc_ledger("mr_pro","1.0", "2","S01","ADMIN","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Acc_ledger(String company, String version, String decimal,String store,String username,String language) throws JSONException {
		initialize(company, version,decimal,store,username,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store 
	 * @param language 
	 * @param page 
	 */
	private void initialize(final String company,final String version,final String decimal, final String store,final String username,final String language) throws JSONException {
		
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 1000, 736);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    	//CafePro.Mainpage.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	CafePro.Mainpage.btnPurchaseOrder.setEnabled(true);
		    	//CafePro.Mainpage.btnNewButton_1.setEnabled(true);
		    }
		});
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		//frame.setAlwaysOnTop(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 982, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("View Ledger");
		if(language.equals("Arabic"))
		{
			lblItemMasterList.setText("⁄—÷ œ› — «·√” «–");
		}
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		menuItem = new JMenuItem("Exit");
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(830, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		if(language.equals("Arabic"))
		{
			menuItem_1.setText("Ì‰⁄‘");
		}
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(890, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		final Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JMenuItem menuItem_3 = new JMenuItem("Print");
		if(language.equals("Arabic"))
		{
			menuItem_3.setText("„ÿ»⁄…");
		}
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				try {
					frame.setAlwaysOnTop(false);
					String jvdate = null;
					String jvdate2 = null;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String jv_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					if(!jv_date.equals(""))
					{
						jvdate = sdf.format(dateChooser.getDate());
					}
					String jv_date2 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					if(!jv_date2.equals(""))
					{
						jvdate2 = sdf.format(dateChooser_1.getDate());
					}
					report_master.ledger_listing.report(textField.getText(),con,textField_1.getText(),jvdate,jvdate2,company);
					//frame.setAlwaysOnTop(true);
					con.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		menuItem_3.setBorder(null);
		menuItem_3.setIcon(new ImageIcon(printer));
		menuItem_3.setBackground(new Color(243, 229, 216));
		menuItem_3.setBounds(750, 7, 80, 48);
		panel.add(menuItem_3);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 982, 629);
		panel_1.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 88, 958, 488);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel_1.add(scrollPane);

		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
				
		       return false;
		    }
		};
		    table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
		    table.setRowHeight(20);
		    if(language.equals("Arabic"))
			{
		    	model.addColumn("—ﬁ„ ﬁ”Ì„… «·ÌÊ„Ì…");
			    model.addColumn(" ”·”· ﬁ”«∆„ «·ÌÊ„Ì…");
			    model.addColumn(" «—ÌŒ «·„⁄«„·…"); 
			    model.addColumn(" «—ÌŒ «·‰‘—"); 
			    //model.addColumn("Ac. Code"); 
			    model.addColumn("„»·€ «·Œ’„");
			    model.addColumn("„»·€ «·«∆ „«‰"); 
			   // model.addColumn("Balance"); 
			    model.addColumn("«·—Ê«Ì« "); 
			    model.addColumn("„·«ÕŸ« ");
			}
		    else
		    {
		    	model.addColumn("JV Number");
			    model.addColumn("JV Seq");
			    model.addColumn("Tran Date"); 
			    model.addColumn("Post Date"); 
			    //model.addColumn("Ac. Code"); 
			    model.addColumn("Dr.Amount");
			    model.addColumn("Cr.Amount"); 
			   // model.addColumn("Balance"); 
			    model.addColumn("Narrations"); 
			    model.addColumn("Remarks");
		    }
		    
		    //model.addColumn("Source"); 
		scrollPane.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		int colmns=model.getColumnCount();
		for (int i=0; i<colmns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(150);
		  if (i==1) column.setPreferredWidth(150);
		  if (i==2) column.setPreferredWidth(100);
		  if (i==3) column.setPreferredWidth(100);
		  if (i==4) column.setPreferredWidth(80);
		  if (i==5) column.setPreferredWidth(80);
		  if (i==6) column.setPreferredWidth(250);
		  if (i==7) column.setPreferredWidth(300);
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 958, 73);
		panel_1.add(panel_2);
		panel_2.setBackground(new Color(243, 229, 216));
		panel_2.setLayout(null);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String jvdate = null;
					String jvdate2 = null;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String jv_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					if(!jv_date.equals(""))
					{
						jvdate = sdf.format(dateChooser.getDate());
					}
					String jv_date2 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					if(!jv_date2.equals(""))
					{
						jvdate2 = sdf.format(dateChooser_1.getDate());
					}
					new SelectAccount(company, "ledger", 0, version,jvdate,jvdate2, language);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(serch));
		button.setBounds(436, 13, 40, 25);
		panel_2.add(button);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(0, 13, 438, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		
		dateChooser = new JDateChooser(new Date());
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(601, 13, 122, 22);
		panel_2.add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(767, 13, 122, 22);
		Date date = new Date();
		dateChooser_1.setDate(date);
		panel_2.add(dateChooser_1);
		
		JButton btnGo = new JButton("GO");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jvdate = null;
				String jvdate2 = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String jv_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if(!jv_date.equals(""))
				{
					jvdate = sdf.format(dateChooser.getDate());
				}
				String jv_date2 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				if(!jv_date2.equals(""))
				{
					jvdate2 = sdf.format(dateChooser_1.getDate());
				}
				
				get_ledger_disp( company, textField.getText(),jvdate,jvdate2,decimal,language);
			}
		});
		btnGo.setBounds(892, 13, 58, 25);
		panel_2.add(btnGo);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(735, 13, 20, 22);
		panel_2.add(lblTo);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(551, 13, 49, 22);
		panel_2.add(lblFrom);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(0, 42, 543, 22);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCr = new JLabel("Cr");
		lblCr.setBounds(570, 575, 56, 16);
		panel_1.add(lblCr);
		
		JLabel lblDr = new JLabel("Dr");
		lblDr.setBounds(425, 575, 56, 16);
		panel_1.add(lblDr);
		
		JLabel lblVareince = new JLabel("Vareince");
		if(language.equals("Arabic"))
		{
			lblVareince.setText("›«—Ì‰”");
		}
		lblVareince.setBounds(707, 575, 56, 16);
		panel_1.add(lblVareince);
		
		JLabel lblTotal = new JLabel("Total");
		if(language.equals("Arabic"))
		{
			lblTotal.setText("«·„Ã„Ê⁄");
		}
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal.setBounds(314, 596, 56, 16);
		panel_1.add(lblTotal);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(403, 594, 116, 22);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(530, 594, 116, 22);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(679, 594, 116, 22);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
//		String jvdate = null;
//		String jvdate2 = null;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String jv_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
//		if(!jv_date.equals(""))
//		{
//			jvdate = sdf.format(dateChooser.getDate());
//		}
//		String jv_date2 = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
//		if(!jv_date2.equals(""))
//		{
//			jvdate2 = sdf.format(dateChooser_1.getDate());
//		}
		
		//get_ledger_disp( company, textField.getText(),jvdate,jvdate2,decimal);
		 
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 0;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	try {
						new JV_details(company, version, store, username, value,decimal,"Posted",language);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                }
            }
        });
	}
	public static void  get_ledger_disp( String company, String search,String date1,String date2,String decimal,String language)
	{
//		String serch_str = "";
//		if(!search.equals(""))
//		{
//			serch_str="and (jv_num LIKE '%"+search+"%' OR ac_code LIKE '%"+search+"%' OR source LIKE '%"+search+"%' )";
//		}
//		String date_based="";
//		if(!date1.equals(""))
//		{
//			date_based="where tran_date between '"+date1+"' and '"+date2+"' ";
//		}
		try{
			
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_jv_details inner join "+company+".mr_jv_header on mr_jv_details.ref_jv_num=mr_jv_header.jv_num where cheque_date between '"+date1+"' and '"+date2+"' and ac_code ='"+search+"' and mr_jv_header.status='Posted'  GROUP BY jv_seq"; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				double cr=0;
				double dr=0;
				String query1="SELECT *,mr_jv_details.remark as remarks_1 FROM "+company+".mr_jv_details inner join "+company+".mr_jv_header on mr_jv_details.ref_jv_num=mr_jv_header.jv_num where cheque_date between '"+date1+"' and '"+date2+"' and ac_code ='"+search+"' and mr_jv_header.status='Posted'  GROUP BY jv_seq order by mr_jv_details.id"; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String jv_num=resultSet1.getString("jv_num");
					String jv_seq=resultSet1.getString("jv_seq");
	                String tran_date=resultSet1.getString("cheque_date");
	                String post_date=resultSet1.getString("posted_date");
	                //String ac_code=resultSet1.getString("ac_code");
	                String dr_amt=resultSet1.getString("dr_amt");
	                String cr_amt=resultSet1.getString("cr_amt");
	                String notes=resultSet1.getString("note");
	                String remark=resultSet1.getString("remarks_1");
	                if(!resultSet1.getString("status").equals("VOID"))
	                {
	                	cr+=Double.parseDouble(cr_amt);
	                	dr+=Double.parseDouble(dr_amt);
	                	model.addRow(new Object[]{jv_num,jv_seq,tran_date,post_date,dr_amt,cr_amt,notes,remark});
	                }
				}
				double bal=dr-cr;
				BigDecimal bd = new BigDecimal(dr);
	    		bd = bd.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
	    		dr = bd.doubleValue();
	    		
	    		BigDecimal be = new BigDecimal(cr);
	    		be = be.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
	    		cr = be.doubleValue();
	    		
	    		BigDecimal ba = new BigDecimal(bal);
	    		ba = ba.setScale(Integer.parseInt(decimal), BigDecimal.ROUND_HALF_UP);
	    		bal = ba.doubleValue();
				
				textField_2.setText(Double.toString(dr));
				textField_3.setText(Double.toString(cr));
				textField_4.setText(Double.toString(bal));
			}
			else
			{
				frame.setAlwaysOnTop(true);
				
				if(language.equals("Arabic"))
				{
					JOptionPane.showMessageDialog(frame, "·„ Ì „ «·⁄ÀÊ— ⁄·Ï œ› — «·√” «–");
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "No Ledger Found");
				}
	        	for (int i = model.getRowCount() - 1; i >= 0; i--) {
	        	    model.removeRow(i);
	        	}
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}