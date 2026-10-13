package sales_master;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import CafePro.Mainpage;

import javax.swing.JComboBox;

public class SplitInvoice {

	private static JFrame frame;
	private static DefaultTableModel model;
	private JTable table;
	private static DefaultTableModel model_1;
	private static JTable table_1;
	static JComboBox<String> comboBox;
	private JButton btnGetItems;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					SplitInvoice window = new SplitInvoice("mr_pro", "admin","S01","S0101", null);
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
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param reg_nme 
	 * @throws JSONException 
	 */
	public SplitInvoice(String company, String username, String store_name, String reg_nme,String version) throws JSONException {
		initialize(company,  username, store_name,reg_nme, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param reg_nme 
	 * @param store_name2 
	 */
	private void initialize(final String company,final String username,final String store_name, final String reg_nme,final String version) throws JSONException {
		frame = new JFrame(version);
		frame.setResizable(false);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 909, 696);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btnUnhold.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btnUnhold.setEnabled(true);
				}
				
			}
			@Override
		      public void windowIconified(WindowEvent we) {
		        frame.setState(JFrame.NORMAL);
		      }
		});
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 893, 88);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Split Invoice");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(8, 7, 228, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btnUnhold.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btnUnhold.setEnabled(true);
				}
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(651, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				get_itemdisp( company, reg_nme,comboBox.getSelectedItem().toString());
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(719, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement = con.createStatement();
					
					
					String fromtable=comboBox.getSelectedItem().toString();
					
					String frominvnum="";
					String fromregnum="";
					String querty_code="select * from "+company+".mr_pos_hold_invoice where table_num='"+fromtable+"'";
					ResultSet resultSet = statement.executeQuery(querty_code);
					if(resultSet.next())
					{
						frominvnum=resultSet.getString("receipt_no");
						fromregnum=resultSet.getString("reg_num");
					}
					String toinvnum="";
					String toregnum="";
					if(table.getRowCount()==0)
					{
						toinvnum=frominvnum;
						toregnum=fromregnum;
					}
					else
					{
						int seq_num=0;
						int nxt_num=0;
						ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_nme+"' ");
						if(resultSet2.next())
						{
							seq_num=Integer.parseInt(resultSet2.getString("invoice_number"));
							nxt_num=Integer.parseInt(resultSet2.getString("next_num"))+1;
							String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+reg_nme+"' ";
							statement.executeUpdate(update_receipt_num);
						}
						int newposnum=0;
						if(Mainpage.SoftLang.equals("Ar"))
						{
							int posinvnum=seq_num+nxt_num;
							newposnum=posinvnum+1;
							PosscreenArabic.textField_8.setText(Integer.toString(newposnum));
						}
						else
						{
							int posinvnum=seq_num+nxt_num;
							newposnum=posinvnum+1;
							posscreen.textField_8.setText(Integer.toString(newposnum));
						}
						
						
						toinvnum=Integer.toString(newposnum);
						toregnum=reg_nme;
						
						double fromtotal=0;
						double fromvat=0;
						double total=0;
						double vat=0;
						
						for(int i=0;i<table_1.getRowCount();i++)
						{
							total+=Double.parseDouble(model_1.getValueAt(i, 5).toString().trim());
							vat+=Double.parseDouble(model_1.getValueAt(i, 6).toString().trim());
						}
						LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");;
						String query="insert into "+company+".mr_pos_hold_invoice (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `total_qty`, store_id, `created_date`,cust_code,table_num,delivery_user,mode,cust_ph) "
								+ "values('"+reg_nme+"','"+Integer.toString(newposnum)+"','"+username+"','"+times.format(cal.getTime())+"','"+total+"','WALK IN','"+vat+"','0','"+table_1.getRowCount()+"','"+store_name+"','"+formattedDate+"','1','QUICK DINE IN','','QUICK DINE IN','0') ";
						statement.executeUpdate(query);
						for(int i=0;i<table_1.getRowCount();i++)
						{
							if(model_1.getValueAt(i, 4).toString().trim().equals("0"))
							{
								fromtotal+=Double.parseDouble(model_1.getValueAt(i, 2).toString().trim());
								fromvat+=Double.parseDouble(model_1.getValueAt(i, 6).toString().trim());
								
								String updatequery="update "+company+".mr_pos_hold_invoice_items set ref_receipt_num='"+Integer.toString(newposnum)+"',ref_reg_no='"+reg_nme+"' where ref_receipt_num='"+frominvnum+"' and ref_reg_no='"+fromregnum+"' and id='"+model_1.getValueAt(i, 3).toString().trim()+"' ";
								statement.executeUpdate(updatequery);
							}
						}
						String updateholdquery1="update "+company+".mr_pos_hold_invoice set total=total-'"+fromtotal+"',vat=vat-'"+fromvat+"' where receipt_no='"+frominvnum+"' and reg_num='"+fromregnum+"' ";
						statement.executeUpdate(updateholdquery1);
						
						String updateholdquery="update "+company+".mr_pos_hold_invoice set total='"+total+"',vat='"+vat+"' where receipt_no='"+Integer.toString(newposnum)+"' and reg_num='"+reg_nme+"' ";
						statement.executeUpdate(updateholdquery);
						if(Mainpage.SoftLang.equals("Ar"))
						{
							report_master.holded_invoice.report(toinvnum, con, PosscreenArabic.printer,toregnum,"QUICK DINE IN",company,"Hold Invoice","","QUICK DINE IN");
						}
						else
						{
							report_master.holded_invoice.report(toinvnum, con, posscreen.printer,toregnum,"QUICK DINE IN",company,"Hold Invoice","","QUICK DINE IN");
						}
						
					}
					frame.setVisible(false);
					JOptionPane.showMessageDialog(frame, "Saved Successfully");
					frame.setVisible(false);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(811, 7, 70, 48);
		menuItem_2.setIcon(new ImageIcon(save));
		
		panel.add(menuItem_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 87, 893, 562);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(25, 0));
		scrollPane.setBounds(12, 52, 400, 510);
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
		    table.setRowHeight(40);
		    
		    model.addColumn("ItemName"); 
		    model.addColumn("Qty"); 
		    model.addColumn("Total"); 
		    model.addColumn("ID"); 
		    model.addColumn("amount"); 
		    model.addColumn("vat"); 
			
			scrollPane.setViewportView(table);
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        int colmns=model.getColumnCount();
			for (int i=0; i<colmns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(300);
			  if (i==1) column.setPreferredWidth(40);
			  if (i==2) column.setPreferredWidth(60);
			  if (i==3) column.setPreferredWidth(0);
			  if (i==4) column.setPreferredWidth(0);
			  if (i==5) column.setPreferredWidth(0);
			}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 0, 400, 52);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					int rowCount = model_1.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model_1.removeRow(i);
					}
					btnGetItems.setEnabled(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		comboBox.setBounds(92, 13, 150, 20);
		panel_2.add(comboBox);
		
		JLabel lblTable = new JLabel("Table");
		lblTable.setBounds(24, 15, 56, 16);
		panel_2.add(lblTable);
		
		btnGetItems = new JButton("Get Items");
		btnGetItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemdisp( company, reg_nme,comboBox.getSelectedItem().toString());
				btnGetItems.setEnabled(false);
			}
		});
		btnGetItems.setBounds(251, 11, 97, 25);
		panel_2.add(btnGetItems);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(489, 0, 400, 52);
		panel_1.add(panel_3);
		
		JLabel lblNewInvoice = new JLabel("New Invoice");
		lblNewInvoice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewInvoice.setBounds(39, 13, 109, 16);
		panel_3.add(lblNewInvoice);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.getVerticalScrollBar().setPreferredSize(new Dimension(25, 0));
		scrollPane_1.setBackground(new Color(243, 229, 216));
		scrollPane_1.setBounds(490, 52, 400, 510);
		panel_1.add(scrollPane_1);
		
		model_1 = new DefaultTableModel(){

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
		    table_1 = new JTable(model_1);
		    table_1.setFont(new Font("Serif", Font.BOLD, 15));
		    table_1.setRowHeight(40);
		    
		    model_1.addColumn("ItemName"); 
		    model_1.addColumn("Qty"); 
		    model_1.addColumn("Total");
		    model_1.addColumn("ID"); 
		    model_1.addColumn("sts");
		    model_1.addColumn("amount"); 
		    model_1.addColumn("vat"); 
			
			scrollPane_1.setViewportView(table_1);
			
			table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        int colns=model_1.getColumnCount();
			for (int i=0; i<colns; i++) {
			  TableColumn column = table_1.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(300);
			  if (i==1) column.setPreferredWidth(40);
			  if (i==2) column.setPreferredWidth(60);
			  if (i==3) column.setPreferredWidth(0);
			  if (i==4) column.setPreferredWidth(0);
			  if (i==5) column.setPreferredWidth(0);
			  if (i==6) column.setPreferredWidth(0);
			}
		
		
		
		JButton button_2 = new JButton(">>");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					for(int i=0;i<table.getRowCount();i++)
					{
						model_1.addRow(new Object[]{model.getValueAt(i, 0).toString().trim(),model.getValueAt(i, 1).toString().trim(),model.getValueAt(i, 2).toString().trim(),model.getValueAt(i, 3).toString().trim(),"0",model.getValueAt(i, 4).toString().trim(),model.getValueAt(i, 5).toString().trim()});
						
					}
					int rowCount = model.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		button_2.setBounds(424, 81, 52, 34);
		panel_1.add(button_2);
		
		JButton button = new JButton(">");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int i=table.getSelectedRow();
					model_1.addRow(new Object[]{model.getValueAt(i, 0).toString().trim(),model.getValueAt(i, 1).toString().trim(),model.getValueAt(i, 2).toString().trim(),model.getValueAt(i, 3).toString().trim(),"0",model.getValueAt(i, 4).toString().trim(),model.getValueAt(i, 5).toString().trim()});
					model.removeRow(i);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		button.setBounds(424, 197, 52, 34);
		panel_1.add(button);
		
		JButton button_1 = new JButton("<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int i=table_1.getSelectedRow();
					if(model_1.getValueAt(i, 4).toString().trim().equals("0"))
					{
						model.addRow(new Object[]{model_1.getValueAt(i, 0).toString().trim(),model_1.getValueAt(i, 1).toString().trim(),model_1.getValueAt(i, 2).toString().trim(),model_1.getValueAt(i, 3).toString().trim(),model_1.getValueAt(i, 5).toString().trim(),model_1.getValueAt(i, 6).toString().trim()});
						model_1.removeRow(i);
					}
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		button_1.setBounds(424, 342, 52, 34);
		panel_1.add(button_1);
		
		JButton button_3 = new JButton("<<");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					for(int i=0;i<table_1.getRowCount();i++)
					{
						if(model_1.getValueAt(i, 4).toString().trim().equals("0"))
						{
							model.addRow(new Object[]{model_1.getValueAt(i, 0).toString().trim(),model_1.getValueAt(i, 1).toString().trim(),model_1.getValueAt(i, 2).toString().trim(),model_1.getValueAt(i, 3).toString().trim(),model_1.getValueAt(i, 5).toString().trim(),model_1.getValueAt(i, 6).toString().trim()});
							
						}
					}
					int rowCount = model_1.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						if(model_1.getValueAt(i, 4).toString().trim().equals("0"))
						{
							model_1.removeRow(i);
						}
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		button_3.setBounds(424, 479, 52, 34);
		panel_1.add(button_3);
		
		JLabel lblTo = new JLabel("TO");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTo.setBounds(435, 13, 42, 16);
		panel_1.add(lblTo);
		
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="select mr_kot_tables.table_num as orginal,mr_pos_hold_invoice.table_num as selected from "+company+".mr_kot_tables left join "+company+".mr_pos_hold_invoice on mr_pos_hold_invoice.table_num=mr_kot_tables.table_num where store_code='S01'  order by mr_kot_tables.id";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				if(resultSet.getString("orginal").equals(resultSet.getString("selected")))
				{
					comboBox.addItem(resultSet.getString("orginal"));
				}
				else
				{
					//comboBox_1.addItem(resultSet.getString("orginal"));
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
        //clickOnback( menuItem, "play", KeyEvent.VK_ESCAPE );
        
    }
//	}
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
	static void  get_itemdisp(String company,  String reg_num, String table)
	{
		try{
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT mr_pos_hold_invoice_items.id,item_desc,qty,mr_pos_hold_invoice_items.total+mr_pos_hold_invoice_items.vat as ttl,mr_pos_hold_invoice_items.total as amount,mr_pos_hold_invoice_items.vat as tax FROM "+company+".mr_pos_hold_invoice inner join "+company+".mr_pos_hold_invoice_items on mr_pos_hold_invoice.receipt_no=mr_pos_hold_invoice_items.ref_receipt_num and mr_pos_hold_invoice.reg_num=mr_pos_hold_invoice_items.ref_reg_no where reg_num='"+reg_num+"' and table_num='"+table+"'";
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				ResultSet resultSet1 = statement.executeQuery(query);
				while(resultSet1.next())
				{
					String item_desc=resultSet1.getString("item_desc");
					String qty=resultSet1.getString("qty");
					String total=resultSet1.getString("ttl");
					String id=resultSet1.getString("id");
					String amount=resultSet1.getString("amount");
					String tax=resultSet1.getString("tax");
	                model.addRow(new Object[]{item_desc,qty,total,id,amount,tax});
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Items Found");
	        	for (int i = model.getRowCount() - 1; i >= 0; i--) {
	        	    model.removeRow(i);
	        	}
			}
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	
}
	

