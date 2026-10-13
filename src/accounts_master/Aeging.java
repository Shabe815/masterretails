package accounts_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import javax.swing.JTextField;

public class Aeging {

	private static JFrame frame;
	public static DefaultTableModel model;
	public static JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Aeging window = new Aeging("mr_pro", "admin","GMC", null,"");
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
	public Aeging(String company, String username,String store,String version, String language) throws JSONException {
		initialize(company,username,store, version,language);
	}


	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 */
	private void initialize(final String company,final String username,final String store,final String version, String language) throws JSONException {
		frame = new JFrame( version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 721);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("AEGING REPORT");
		if(language.equals("Arabic"))
		{
			lblItemMasterList.setText(" ﬁ—Ì— «·‘ÌŒÊŒ…");
		}
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(780, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		if(language.equals("Arabic"))
		{
			menuItem_1.setText("Ì‰⁄‘");
		}
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//get_po_disp( company, store,qtyOHless,qtyOH,qtyOHgreat,comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(),textField_1.getText() );
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		final Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JMenuItem menuItem_2 = new JMenuItem("Print");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(697, 7, 80, 48);
		menuItem_2.setIcon(new ImageIcon(printer));
		panel.add(menuItem_2);
		
		final Image doc=new ImageIcon(this.getClass().getResource("/doc.png")).getImage();
		JMenuItem menuItem_3 = new JMenuItem("Export");
		if(language.equals("Arabic"))
		{
			menuItem_3.setText("Ì’œ¯—");
		}
		menuItem_3.setBorder(null);
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					//export( company, store,qtyOHless,qtyOH,qtyOHgreat,comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString() );

				} catch (Exception e) {
					System.err.println(e);
				}
			}
		});
		menuItem_3.setBackground(new Color(243, 229, 216));
		menuItem_3.setBounds(605, 7, 80, 48);
		menuItem_3.setIcon(new ImageIcon(doc));
		panel.add(menuItem_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 613);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 11, 908, 565);
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
		    	model.addColumn("«·—ﬁ„ «· ”·”·Ì"); 
			    model.addColumn("Ìﬂ »"); 
			    model.addColumn("‘›—…");
			    model.addColumn("«”„");
			    
			    model.addColumn("Õ«÷ˆ—");
			    model.addColumn("30 ÌÊ„«");
			    model.addColumn("45 ÌÊ„«"); 
			    model.addColumn("60 ÌÊ„«");
			    model.addColumn("90 ÌÊ„«");
			    model.addColumn("√ﬂÀ— „‰ 90 ÌÊ„«");
			    model.addColumn("«·„»·€ «·≈Ã„«·Ì");
			}
		    else
		    {
		    	model.addColumn("Sl. No."); 
			    model.addColumn("Type"); 
			    model.addColumn("Code");
			    model.addColumn("Name");
			    
			    model.addColumn("Current");
			    model.addColumn("30 Days");
			    model.addColumn("45 Days"); 
			    model.addColumn("60 Days");
			    model.addColumn("90 Days");
			    model.addColumn("Above 90 Days");
			    model.addColumn("Total Amount");
		    }
		    
		scrollPane.setViewportView(table);
		
		
		
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(35);
			  if (i==1) column.setPreferredWidth(50);
			  if (i==2) column.setPreferredWidth(85);
			  if (i==3) column.setPreferredWidth(275);
			  if (i==4) column.setPreferredWidth(50);
			  if (i==5) column.setPreferredWidth(50);
			  if (i==6) column.setPreferredWidth(50);
			  if (i==7) column.setPreferredWidth(50);
			  if (i==8) column.setPreferredWidth(50);
			  if (i==9) column.setPreferredWidth(90);
			  if (i==10) column.setPreferredWidth(100);
			}
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		if(language.equals("Arabic"))
		{
			menuItem.setText("«·„»·€ «·≈Ã„«·Ì");
		}
		lblTotalAmount.setBounds(673, 589, 87, 16);
		panel_1.add(lblTotalAmount);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setEditable(false);
		textField.setBounds(760, 586, 160, 22);
		panel_1.add(textField);
		textField.setColumns(10);
		
		get_po_disp( company, store, "ALL");
		
	}
	private void  get_po_disp( String company, String store, String types)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_vendor_payment  WHERE pay_terms!='CASH' and bal_amt!='0' "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				double ttl_amt=0;
				int decimalPlaces = 2;
				//String query1="SELECT 'PO' as type,`ref_vendor_id` as code,vendor_name as name,`invo_date` as inv_date,`pay_terms` as pay_terms,`due_days` as days,`bal_amt` as amount,DATEDIFF( now(), invo_date ) as daysdiff FROM "+company+".mr_vendor_payment inner join "+company+".mr_vendor on `ref_vendor_id`= vendor_code WHERE pay_terms!='CASH' and bal_amt!='0' ";
				String query1="SELECT now() as datet,'PO' as type,`ref_vendor_id` as code,vendor_name as name,`invo_date` as inv_date,`pay_terms` as pay_terms,`due_days` as days,sum(bal_amt) as amount,DATEDIFF( now(), invo_date ) as daysdiff,(select coalesce(sum(bal_amt),0) from "+company+".mr_vendor_payment where (DATEDIFF( now(), invo_date )>0 && DATEDIFF( now(), invo_date )<=7) and pay_terms!='CASH' and bal_amt!='0' and ref_vendor_id = code) as days7,(select coalesce(sum(bal_amt),0) from "+company+".mr_vendor_payment where (DATEDIFF( now(), invo_date )>7 && DATEDIFF( now(), invo_date )<=30) and pay_terms!='CASH' and bal_amt!='0' and ref_vendor_id = code) as days30,(select coalesce(sum(bal_amt),0) from "+company+".mr_vendor_payment where (DATEDIFF( now(), invo_date )>30 && DATEDIFF( now(), invo_date )<=45) and pay_terms!='CASH' and bal_amt!='0' and ref_vendor_id = code) as days45,(select coalesce(sum(bal_amt),0) from "+company+".mr_vendor_payment where (DATEDIFF( now(), invo_date )>45 && DATEDIFF( now(), invo_date )<=60) and pay_terms!='CASH' and bal_amt!='0' and ref_vendor_id = code) as days60,(select coalesce(sum(bal_amt),0) from "+company+".mr_vendor_payment where (DATEDIFF( now(), invo_date )>60 && DATEDIFF( now(), invo_date )<=90) and pay_terms!='CASH' and bal_amt!='0' and ref_vendor_id = code) as days90,(select coalesce(sum(bal_amt),0) from "+company+".mr_vendor_payment where (DATEDIFF( now(), invo_date )>90) and pay_terms!='CASH' and bal_amt!='0' and ref_vendor_id = code) as days91 FROM "+company+".mr_vendor_payment inner join "+company+".mr_vendor on `ref_vendor_id`= vendor_code WHERE pay_terms!='CASH' and bal_amt!='0' group by ref_vendor_id";
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String type=resultSet1.getString("type");
					String code=resultSet1.getString("code");
					String name=resultSet1.getString("name");
					double days7=Double.parseDouble(resultSet1.getString("days7"));
					double days30=Double.parseDouble(resultSet1.getString("days30"));
					double days45=Double.parseDouble(resultSet1.getString("days45"));
					double days60=Double.parseDouble(resultSet1.getString("days60"));
					double days90=Double.parseDouble(resultSet1.getString("days90"));
					double days91=Double.parseDouble(resultSet1.getString("days91"));
					double amount=Double.parseDouble(resultSet1.getString("amount"));
	                
	                model.addRow(new Object[]{model.getRowCount()+1,type,code,name,days7,days30,days45,days60,days90,days91,amount});
	                ttl_amt=ttl_amt+amount;
				}
				BigDecimal bd = new BigDecimal(ttl_amt);
        		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		ttl_amt = bd.doubleValue();
				textField.setText(Double.toString(ttl_amt));
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Items Found ·„ Ì „ «·⁄ÀÊ— ⁄·Ï √Ì ⁄‰«’—");
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
	
	
//	SELECT now() as datet,"PO" as type,`ref_vendor_id` as code,vendor_name as name,`invo_date` as inv_date,`pay_terms` as pay_terms,`due_days` as days,`bal_amt` as amount,DATEDIFF( now(), invo_date ) as daysdiff,
//	if(DATEDIFF( now(), invo_date )>0 && DATEDIFF( now(), invo_date )<=7,sum(bal_amt),0) as daysdiff7,
//	if(DATEDIFF( now(), invo_date )>7 && DATEDIFF( now(), invo_date )<=30,sum(bal_amt),0) as daysdiff30,
//	if(DATEDIFF( now(), invo_date )>30 && DATEDIFF( now(), invo_date )<=45,sum(bal_amt),0) as daysdiff45,
//	if(DATEDIFF( now(), invo_date )>45 && DATEDIFF( now(), invo_date )<=60,sum(bal_amt),0) as daysdiff60,
//	if(DATEDIFF( now(), invo_date )>60 && DATEDIFF( now(), invo_date )<=90,sum(bal_amt),0) as daysdiff90,
//	if(DATEDIFF( now(), invo_date )>90,(bal_amt),0) as daysdiff91
//	FROM mr_pro.mr_vendor_payment inner join mr_vendor on `ref_vendor_id`= vendor_code WHERE pay_terms!='CASH' and bal_amt!='0'

	
	
}