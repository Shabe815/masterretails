package accounts_master;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.json.JSONException;

public class posted_AP_list {

	static JFrame frame;
	private static DefaultTableModel model;
	private static JTable table;
	static JTextField textField;
	public static JMenuItem menuItem;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					posted_AP_list window = new posted_AP_list("","demo","admin","S01", null,"2","","");
					posted_AP_list.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param decimal 
	 * @param type 
	 */
	public posted_AP_list(String db_name,String company, String username,String store,String version, String decimal, String type,String language) throws JSONException {
		initialize(db_name,company,username,store, version,decimal,type,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 * @param page 
	 */
	private void initialize(final String db_name,final String company,final String username,final String store,final String version,final String decimal,String type,final String language) throws JSONException {
		
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 696);
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
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Vendor Invoice "+type+" List");
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
				//CafePro.Mainpage.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	CafePro.Mainpage.btnPurchaseOrder.setEnabled(true);
		    	//CafePro.Mainpage.btnNewButton_1.setEnabled(true);
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
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				get_po_disp( company, "POSTED", "");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 589);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 88, 908, 488);
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
		    	model.addColumn("—ﬁ„ «·›« Ê—…");
			    model.addColumn("«·ÊÀÌﬁ… —ﬁ„"); 
			    model.addColumn("«”„ «·»«∆⁄");
			    model.addColumn("„»·€ «·›« Ê—…"); 
			    model.addColumn("„»·€ «·÷—Ì»…"); 
			    model.addColumn("«·„»·€ «·≈Ã„«·Ì"); 
			    model.addColumn(" «—ÌŒ «·›« Ê—…");
			    model.addColumn("„·«ÕŸ…");
			}
		    else
		    {
		    	model.addColumn("Invoice Number");
			    model.addColumn("Doc No."); 
			    model.addColumn("vendor Name");
			    model.addColumn("Invoice Amount"); 
			    model.addColumn("Vat Amount"); 
			    model.addColumn("Total Amount"); 
			    model.addColumn("Invoice Date");
			    model.addColumn("Remark");
		    }
		    
		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(140);
			  if (i==1) column.setPreferredWidth(140);
			  if (i==2) column.setPreferredWidth(175);
			  if (i==3) column.setPreferredWidth(75);
			  if (i==4) column.setPreferredWidth(75);
			  if (i==5) column.setPreferredWidth(75);
			  if (i==6) column.setPreferredWidth(90);
			  if (i==7) column.setPreferredWidth(300);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 908, 52);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
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
				  get_po_disp( company,"POSTED",textField.getText());
			  }
			});
		textField.setBounds(12, 13, 493, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		if(language.equals("Arabic"))
		{
			btnSearch.setText("Ì»ÕÀ");
		}
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( company,"POSTED",textField.getText());
			}
		});
		btnSearch.setBounds(518, 13, 97, 25);
		panel_2.add(btnSearch);
		 
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 0;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	try{
            			
	 					frame.setAlwaysOnTop(false);
	 					new view_AP_invoice(company,version,store,username,value,decimal,"OPEN",language);
	 					//purchase_order.frame.setAlwaysOnTop(true);
                	}
                	catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	//frame.setVisible(false);
                }
            }
        });
        get_po_disp( company,type,textField.getText());
        
	}
	public static void  get_po_disp( String company, String type, String search)
	{
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="and (invoice_num LIKE '%"+search+"%' OR supp_name LIKE '%"+search+"%' OR doc_num LIKE '%"+search+"%' OR remark LIKE '%"+search+"%' )";
		}
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_invoice_history where status='"+type+"' "+serch_str+" "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+company+".mr_invoice_history where status='"+type+"' "+serch_str+" "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String vendor_name=resultSet1.getString("supp_name");
					String name=resultSet1.getString("invoice_num");
	                String inv_amt=resultSet1.getString("inv_amt");
	                String vat_amt=resultSet1.getString("vat_amt");
	                String net_total=resultSet1.getString("net_total");
	                String inv_date=resultSet1.getString("inv_date");
	                String remark=resultSet1.getString("remark");
	                String doc_num=resultSet1.getString("doc_num");
	               
	                model.addRow(new Object[]{name,doc_num,vendor_name,inv_amt,vat_amt,net_total,inv_date,remark});
				}
				resizeColumnWidth(table);
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Invoice List Found ·„ Ì „ «·⁄ÀÊ— ⁄·Ï ﬁ«∆„… «·›Ê« Ì—");
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
	private static void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();

	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 50; // Minimum width

	        // Header width
	        TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
	        Component headerComp = headerRenderer.getTableCellRendererComponent(
	                table,
	                table.getColumnName(column),
	                false,
	                false,
	                0,
	                column);

	        width = Math.max(width, headerComp.getPreferredSize().width);

	        // Cell width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(width, comp.getPreferredSize().width);
	        }

	        // Add some padding
	        width += 15;

	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
}
