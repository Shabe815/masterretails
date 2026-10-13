package accounts_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

import purchase_master.vendor_details;
import system_setups.Store_acc_sel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SelectAccount {

	private static JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private JTextField textField;
	private  JComboBox<String> comboBox ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					SelectAccount window = new SelectAccount("mr_pro","",0, null, null, null,"");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param page_type 
	 * @param row 
	 * @param jvdate2 
	 * @param jvdate 
	 * @throws JSONException 
	 */
	public SelectAccount(String company, String page_type, int row,String version, String datepicker, String datepicker2,String language) throws JSONException {
		initialize(company,page_type,row, version,datepicker,datepicker2,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param datepicker2 
	 * @param datepicker 
	 * @param language 
	 * @throws JSONException 
	 */
	private void initialize(final String company,final String page_type,final int row_selected,String version,final String datepicker, final String datepicker2, final String language) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setAlwaysOnTop(true);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image Billing_icon = new ImageIcon(this.getClass().getResource("/Billing-icon.png")).getImage();
		JLabel lblCompanySetup = new JLabel("Chart of Accounts");
		if(language.equals("Arabic"))
		{
			lblCompanySetup.setText("„Œÿÿ «·Õ”«»« ");
		}
		lblCompanySetup.setBounds(21, 11, 228, 44);
		lblCompanySetup.setIcon(new ImageIcon(Billing_icon));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblCompanySetup);
	    
	    JMenuItem menuItem_1 = new JMenuItem("Exit");
	    if(language.equals("Arabic"))
		{
	    	menuItem_1.setText("„Œ—Ã");
		}
	    menuItem_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		frame.setVisible(false);
	    	}
	    });
	    menuItem_1.setAccelerator(KeyStroke.getKeyStroke("F4"));
	    menuItem_1.setBorder(null);
	    menuItem_1.setBackground(new Color(243, 229, 216));
	    menuItem_1.setIcon(new ImageIcon(exit));
	    menuItem_1.setBounds(689, 7, 60, 48);
	    frame.getContentPane().add(menuItem_1);
		
	    JMenuItem menuItem_2 = new JMenuItem("Refresh");
	    if(language.equals("Arabic"))
		{
	    	menuItem_2.setText("Ì‰⁄‘");
		}
	    menuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
	    menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				get_chart_acc(company,textField.getText(),comboBox.getSelectedItem().toString());
			}
		});
	    menuItem_2.setBorder(null);
	    menuItem_2.setBackground(new Color(243, 229, 216));
	    menuItem_2.setBounds(609, 7, 80, 48);
	    menuItem_2.setIcon(new ImageIcon(refresh));
		frame.getContentPane().add(menuItem_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 761, 2);
		frame.getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 107, 744, 437);
		frame.getContentPane().add(scrollPane);
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable (int row,int column){
				return false;
			}
		   
		};
		table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(20);
			if(language.equals("Arabic"))
			{
				model.addColumn("‘›—…"); 
			    model.addColumn("≈”„ «·Õ”«»"); 
				model.addColumn("„Ã„Ê⁄… «·Õ”«»"); 
				model.addColumn("‰Ê⁄ «·Õ”«»"); 
			}
			else
			{
				model.addColumn("Code"); 
			    model.addColumn("Acccount Name"); 
				model.addColumn("Account Group"); 
				model.addColumn("Account Type"); 
			}
		    
			//model.addRow(new Object[]{"","","",""});
		    scrollPane.setViewportView(table);
		    
		    textField = new JTextField();
		    textField.getDocument().addDocumentListener(new DocumentListener() {
				  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
					  int rowCount = model.getRowCount();
						for (int i = rowCount - 1; i >= 0; i--) {
							model.removeRow(i);
						}
						get_chart_acc(company,textField.getText(),comboBox.getSelectedItem().toString());
				  }
				});
		    textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e1) {
					int keycode=e1.getKeyCode();
					if(keycode==40)
					{
						table.requestFocus();
					}
				}
			});
		    textField.setBounds(10, 72, 401, 22);
		    frame.getContentPane().add(textField);
		    textField.setColumns(10);
		    
		    JButton btnNewButton = new JButton("Search");
		    if(language.equals("Arabic"))
			{
		    	btnNewButton.setText("Ì»ÕÀ");
			}
		    btnNewButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		get_chart_acc(company,textField.getText(),comboBox.getSelectedItem().toString());
		    	}
		    });
		    btnNewButton.setBounds(423, 72, 81, 22);
		    frame.getContentPane().add(btnNewButton);
		    
		    comboBox = new JComboBox<String>();
		    comboBox.addItem("ALL");
		    comboBox.addItem("ASSET");
		    comboBox.addItem("LIABILITY");
		    comboBox.addItem("INCOME");
		    comboBox.addItem("EXPENSE");
		    comboBox.addItemListener(new ItemListener() {
		    	public void itemStateChanged(ItemEvent arg0) {
		    		get_chart_acc(company, textField.getText(), comboBox.getSelectedItem().toString());
		    	}
		    });
		    comboBox.setBounds(630, 72, 119, 22);
		    frame.getContentPane().add(comboBox);
		    
		    JLabel lblSortBy = new JLabel("Sort by");
		    if(language.equals("Arabic"))
			{
		    	lblSortBy.setText("«· — Ì» Õ”»");
			}
		    lblSortBy.setBounds(562, 66, 56, 28);
		    frame.getContentPane().add(lblSortBy);
		    
		    get_chart_acc(company, textField.getText(), comboBox.getSelectedItem().toString());
	        
		    table.addKeyListener(new KeyAdapter() {
		    	@Override
		    	public void keyPressed(KeyEvent e1) {
		    		if (e1.getKeyChar() == KeyEvent.VK_ENTER) {
		    			get_codes( company,page_type,row_selected,datepicker,datepicker2, language);
		    		}
		    	}
		    });
	        table.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent mouseEvent) {
	                JTable table =(JTable) mouseEvent.getSource();
	                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
	                	get_codes( company,page_type,row_selected,datepicker,datepicker2, language);
	                }
	            }
	        });
		//frame.getContentPane().add(table);
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 767, 592);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		clickOnback( menuItem_1, "play", KeyEvent.VK_ESCAPE );
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
	        }
	    } );
	}
	private void get_codes(String company,String page_type,int row_selected,String datepicker,String datepicker2,String language ) {
		int rows = table.getSelectedRow();
    	if(page_type.equals("inventory_cntrl"))
    	{
    		inventory_master.Inventory_control.txtacc_code.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="store_paycode")
    	{
    		system_setups.Paycodes.model.setValueAt(table.getModel().getValueAt(rows, 0).toString(),row_selected,6);
    	}
    	else if(page_type=="po_overhead")
    	{
    		system_setups.Po_Overhead.textField_2.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="acc_group_1")
    	{
    		accounts_master.Assign_account.txtacc_code.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="acc_group_2")
    	{
    		accounts_master.Assign_account.textField_1.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="acc_group_3")
    	{
    		accounts_master.Assign_account.textField_2.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="acc_group_4")
    	{
    		accounts_master.Assign_account.textField_3.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="acc_group_5")
    	{
    		accounts_master.Assign_account.textField_4.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="acc_group_6")
    	{
    		accounts_master.Assign_account.textField_5.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="acc_group_7")
    	{
    		accounts_master.Assign_account.textField_6.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="acc_group_8")
    	{
    		accounts_master.Assign_account.textField_7.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="acc_group_9")
    	{
    		accounts_master.Assign_account.textField_8.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="acc_group_10")
    	{
    		accounts_master.Assign_account.textField_9.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="acc_group_11")
    	{
    		accounts_master.Assign_account.textField_10.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="acc_group_12")
    	{
    		accounts_master.Assign_account.textField_11.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="store_acc_1")
    	{
    		Store_acc_sel.txtacc_code.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="store_acc_2")
    	{
    		Store_acc_sel.textField_1.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="store_acc_3")
    	{
    		Store_acc_sel.textField_2.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="store_acc_4")
    	{
    		Store_acc_sel.textField_3.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="store_acc_5")
    	{
    		Store_acc_sel.textField_4.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="store_acc_6")
    	{
    		Store_acc_sel.textField_5.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="store_acc_7")
    	{
    		Store_acc_sel.textField_6.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="acc_paycode1")
    	{
    		Acc_Paycodes.textField_3.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="acc_paycode2")
    	{
    		Acc_Paycodes.textField_4.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type.equals("cust_mstr"))
    	{
    		sales_master.Customer_master.textField_11.setText(table.getModel().getValueAt(rows, 0).toString());
    	}
    	else if(page_type=="trail")
    	{
    		trail_balance.textField_1.setText(table.getModel().getValueAt(rows, 0).toString());
    		trail_balance.textField.setText(table.getModel().getValueAt(rows, 1).toString());
    	}
    	else if(page_type=="JV_dt")
    	{
    		JV_details.textField_3.setText(table.getModel().getValueAt(rows, 0).toString());
    		JV_details.textField_4.setText(table.getModel().getValueAt(rows, 1).toString());
    		JV_details.textField_5.requestFocus();
    	}
    	else if(page_type=="Vendor_dt")
    	{
    		vendor_details.textField_20.setText(table.getModel().getValueAt(rows, 0).toString());
    		vendor_details.textField_21.setText(table.getModel().getValueAt(rows, 1).toString());
    	}
    	else if(page_type=="AP_inv")
    	{
    		AP_invoice.textField_3.setText(table.getModel().getValueAt(rows, 0).toString());
    		AP_invoice.textField_4.setText(table.getModel().getValueAt(rows, 1).toString());
    	}
    	else if(page_type=="ledger")
    	{
    		String date=datepicker;
    		Acc_ledger.textField.setText(table.getModel().getValueAt(rows, 0).toString());
    		Acc_ledger.textField_1.setText(table.getModel().getValueAt(rows, 1).toString());
    		Acc_ledger.get_ledger_disp( company, table.getModel().getValueAt(rows, 0).toString(),date,datepicker2,"2",language);
    	}
    	else
    	{
    		system_setups.Item_Tax.textField_9.setText(table.getModel().getValueAt(rows, 0).toString());	
    	}
    	
        frame.setVisible(false);
	}
	
	public void get_chart_acc(String company, String serch, String sort)
	{
		String search="";
		if(!serch.equals(""))
		{
			if(!sort.equals("ALL"))
			{
				search=" and acc_name LIKE '%"+serch+"%' OR acc_type LIKE '%"+serch+"%' OR acc_branch LIKE '%"+serch+"%' OR acc_code LIKE '%"+serch+"%' OR acc_category LIKE '%"+serch+"%'  ";
			}
			else
			{
				search=" where acc_name LIKE '%"+serch+"%' OR acc_type LIKE '%"+serch+"%' OR acc_branch LIKE '%"+serch+"%' OR acc_code LIKE '%"+serch+"%' OR acc_category LIKE '%"+serch+"%'  ";
			}
		}
		String type="";
		if(!sort.equals("ALL"))
		{
			type=" where acc_type='"+sort+"'";
		}
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_chart_accnt_mstr "+type+" "+search+" order by acc_type ASC  ");
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			while(resultSet.next())
			{
				String id=resultSet.getString("acc_code");
	            String name=resultSet.getString("acc_name");
	            String account_code=resultSet.getString("acc_grp_name");
	            String tax_rate=resultSet.getString("acc_type");
	            model.addRow(new Object[]{id, name,tax_rate,account_code});
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
