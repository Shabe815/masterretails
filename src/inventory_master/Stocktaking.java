package inventory_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

public class Stocktaking {

	private static JFrame frame;
	private DefaultTableModel model;
	private JTable table;
	private JButton btnPostStockList;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private DefaultTableModel model1;
	private JTable table1;
	private DefaultTableModel model11;
	private JTable table11;
	//private JDateChooser dateChooser;
	//private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Stocktaking window = new Stocktaking("mr_pro", "admin","EGC", null);
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
	public Stocktaking(String company, String username,String store,String version) throws JSONException {
		initialize(company,username,store, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param page 
	 */
	private void initialize(final String company,final String username,final String store,final String version) throws JSONException {
		frame = new JFrame( version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 950, 696);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	    final String formattedDate = myDateObj.format(myFormatObj); 
	    
	    //LocalDateTime nowtime = LocalDateTime.now();  
	   // DateTimeFormatter nowformat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");  
	    //final String timedate = nowtime.format(nowformat); 
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 932, 60);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Stock Count List");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(768, 11, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_po_disp( company, store, "");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(840, 11, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		
		btnPostStockList = new JButton("POST STOCK LIST");
		btnPostStockList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int val=JOptionPane.showConfirmDialog(frame,"Are You Sure To Post Stock List","Message",JOptionPane.YES_NO_OPTION);
					if(val==0)
					{
						sync(e,company,formattedDate,username,store);
						
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(frame, e1.getMessage());
					frame.setCursor(Cursor.getDefaultCursor());
				}
			}
		});
		btnPostStockList.setBounds(493, 22, 145, 25);
		panel.add(btnPostStockList);
		
		final Image doc=new ImageIcon(this.getClass().getResource("/doc.png")).getImage();
		JMenuItem menuItem_3 = new JMenuItem("Export");
		menuItem_3.setBorder(null);
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					export( company, store );

				} catch (Exception e) {
					System.err.println(e);
				}
			}
		});
		menuItem_3.setBackground(new Color(243, 229, 216));
		menuItem_3.setBounds(672, 7, 80, 48);
		menuItem_3.setIcon(new ImageIcon(doc));
		panel.add(menuItem_3);
		
		
		Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		JMenuItem mntmPrint = new JMenuItem("Print");
		mntmPrint.setVisible(false);
		mntmPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//				Connection con=Dbsetup.dbconn.getConnection();
//				try {
//					REPORT.validationreport.report(con,store);
//				} catch (ClassNotFoundException | SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				frame.setCursor(Cursor.getDefaultCursor());
			}
		});
		mntmPrint.setBorder(null);
		mntmPrint.setBackground(new Color(243, 229, 216));
		mntmPrint.setBounds(416, 7, 80, 48);
		mntmPrint.setIcon(new ImageIcon(printer));
		panel.add(mntmPrint);
		
		JButton button = new JButton("Create Stock Item List");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement();  
					int rowCount = model1.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model1.removeRow(i);
					}
					
					String query1="SELECT * FROM "+company+".mr_tmp_item_master"; 
					ResultSet resultSet1 = statement.executeQuery(query1);
					while(resultSet1.next())
					{
						String item_code=resultSet1.getString("item_code");
						String barcode=resultSet1.getString("barcode");
		                String item_name=resultSet1.getString("item_name");
		                String sp1=resultSet1.getString("sp");
		                String item_cost=resultSet1.getString("cost");
		                model1.addRow(new Object[]{item_code,barcode,item_name,sp1,item_cost});
					}
					table1.scrollRectToVisible(table1.getCellRect(table1.getRowCount()-1, 0, true));
					FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\itemlist.csv");

						for(int x = 0; x < model1.getColumnCount(); x++){
				            excel.write(model1.getColumnName(x) );
				            excel.write(",");
				        }

				        excel.write("\n");

				        for(int x=0; x< model1.getRowCount(); x++) {
				            for(int y=0; y < model1.getColumnCount(); y++) {
				                excel.write(model1.getValueAt(x,y).toString());
				                if(y < model1.getColumnCount()-1)
				                {
				                	excel.write(",");
				                }
				                else
				                {
				                	excel.write("\r\n");
				                }
				            }
				        }

				        excel.close();
				        frame.setCursor(Cursor.getDefaultCursor());
				        JOptionPane.showMessageDialog(frame, "Export Completed");
					con.close();
				}
				catch(Exception e){
					System.err.println(e);
					frame.setCursor(Cursor.getDefaultCursor());
				}
			}
		});
		button.setBounds(276, 22, 181, 25);
		panel.add(button);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 932, 589);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 88, 908, 465);
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
//				if(column==5 || column==6 || column==7 || column==8)
//				{
//					return true;
//				}
		       return false;
		    }
		};
		    table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
		    table.setRowHeight(20);
		    model.addColumn("Item Code");
		    model.addColumn("Barcode");
		    model.addColumn("Item Name"); 
		    model.addColumn("Cost");
		    model.addColumn("System Qty"); 
		    model.addColumn("Enter Qty");
		    model.addColumn("Vareince");
		    model.addColumn("Total Value");
		scrollPane.setViewportView(table);
		
		
		
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(50);
			  if (i==1) column.setPreferredWidth(20);
			  if (i==2) column.setPreferredWidth(200);
			  if (i==3) column.setPreferredWidth(20);
			  if (i==4) column.setPreferredWidth(10);
			  if (i==5) column.setPreferredWidth(10);
			  if (i==6) column.setPreferredWidth(10);
			  if (i==7) column.setPreferredWidth(10);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 908, 74);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 0, 205));
		progressBar.setBackground(new Color(192, 192, 192));
		progressBar.setStringPainted(true);
		//progressBar.setBounds(12, 198, 622, 31);
		//frame.getContentPane().add(progressBar);
		progressBar.setBounds(0, 40, 908, 31);
		progressBar.setVisible(false);
		panel_2.add(progressBar);
		
		
		JLabel lblStore = new JLabel("Store "+store);
		lblStore.setBounds(12, 13, 269, 16);
		panel_2.add(lblStore);
		
		JButton btnNewButton = new JButton("Delete All Stock List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int val=JOptionPane.showConfirmDialog(frame, "Are You Sure, Do You want to Delete Stock List","",JOptionPane.YES_NO_OPTION);
					if(val==0)
					{
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement=con.createStatement();  
						String query="SELECT * FROM "+company+".mr_stock_count where status='OPEN'"; 
						ResultSet resultSet = statement.executeQuery(query);
						if(resultSet.next())
						{
							String del="delete FROM "+company+".mr_stock_count where status='OPEN'";
							statement.executeUpdate(del);
						}
						JOptionPane.showMessageDialog(frame, "Deleted Successfully");
						get_po_disp( company, store, "");
						frame.setCursor(Cursor.getDefaultCursor());
					}
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(frame, e.getMessage());
					frame.setCursor(Cursor.getDefaultCursor());
				}
			}
		});
		btnNewButton.setBounds(661, 13, 211, 25);
		panel_2.add(btnNewButton);
		
		JButton btnImportStockList = new JButton("Import Stock List");
		btnImportStockList.setToolTipText("Import file should have barcode,itemname,cost,qty columns");
		btnImportStockList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LocalDateTime myDateObj = LocalDateTime.now();  
					DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					String formattedDate = myDateObj.format(myFormatObj);  
					Connection con = Dbsetup.dbconn.getConnection();
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					jfc.setDialogTitle("Select file to upload: ");
					jfc.setAcceptAllFileFilterUsed(false);
					FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV or TXT Files", "csv", "txt");
					jfc.addChoosableFileFilter(filter);
					File selectedFile = null;
					String line="";
					int returnValue = jfc.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION)
					{
						selectedFile = jfc.getSelectedFile();
						int row=1;
						final File parentDir = new File("C:/MasterRetails");
						parentDir.mkdir();
						final String hash = "CSV log";
						final String fileName = hash + ".txt";
						final String filestock = "Stock.txt";
						final File file = new File(parentDir, fileName);
						final File stocklist = new File(parentDir, filestock);
						final File stocklist2 = new File(parentDir, "Stock2.txt");
						file.createNewFile();
						stocklist.createNewFile();
						stocklist2.createNewFile();
						HashMap<String,String> qty_itemcode=new HashMap<String,String>();
						ArrayList<String> itemcode=new ArrayList<String>();
						Statement statement=con.createStatement();  
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						
						File log=new File("C:/MasterRetails/CSV log.txt");
						File stock=new File("C:/MasterRetails/stock.txt");
						File stock2=new File("C:/MasterRetails/stock2.txt");
						BufferedReader br = new BufferedReader(new FileReader(selectedFile));
						PrintWriter writer = new PrintWriter(log, "UTF-8");
						PrintWriter writeinv_stock = new PrintWriter(stock, "UTF-8");
						PrintWriter writeinv_stock2 = new PrintWriter(stock2, "UTF-8");
						while ((line = br.readLine()) != null) {
							String[] cols = line.split(",");
							if(row!=1)
							{
								
								if(cols.length!=4)
								{
									writer.println("Columns Count not matching. Ref in CSV row is "+row);
								}
								if(cols[0].trim()==null)
								{
									writer.println(" Barcode is null in row "+row);
								}
								else
								{
									String Storeqty="";
							    	Storeqty=cols[3].trim();
						    		if(Storeqty.equals("") || Storeqty.equals("-"))
						    		{
						    			Storeqty="0";
						    		}
						    		double ttlqty=0;
						    		String cf="0";
					    			String item_code="";
					    			String item_name="";
					    			//String cost="0";
					    			String item_cost=cols[2].trim();
					    			if(item_cost.equals("") || item_cost.equals("-"))
						    		{
					    				item_cost="0";
						    		}
									String query11="SELECT * FROM "+company+".mr_tmp_item_master where barcode='"+cols[0].trim()+"' or item_code='"+cols[0].trim()+"'"; 
					    			ResultSet resultSet11 = statement.executeQuery(query11);
					    			if(resultSet11.next())
					    			{
					    				item_code=resultSet11.getString("item_code");
					    				item_name=resultSet11.getString("item_name");
					    				//item_cost=resultSet11.getString("cost");
					    				cf=resultSet11.getString("cf");
					    				ttlqty=Double.parseDouble(Storeqty)*Double.parseDouble(cf);
					    			}
					    			
					    			BigDecimal bd = new BigDecimal(ttlqty);
					        		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
					        		ttlqty = bd.doubleValue();
						    		if(itemcode.contains(item_code))
						    		{
						    			String oldqty=qty_itemcode.get(item_code);
						    			double newqty=Double.parseDouble(Storeqty)+Double.parseDouble(oldqty);
						    			qty_itemcode.remove(item_code);
						    			qty_itemcode.put(item_code, Double.toString(newqty));
						    			//writeinv_stock.println(cols[0].trim()+","+cols[1].trim()+","+cols[2].trim()+","+newqty+","+formattedDate+",OPEN,");
						    			//writeinv_stock2.println(item_code+","+cols[1].trim()+","+cols[2].trim()+","+newqty+","+formattedDate+",OPEN,");
						    		}
						    		else
						    		{
						    			itemcode.add(item_code);
						    			qty_itemcode.put(item_code, Double.toString(ttlqty));
						    			writeinv_stock.println(item_code+","+item_name+","+item_cost+","+ttlqty+","+formattedDate+",OPEN,");
						    		}
								}
							}
							row++;
						}
						
						br.close();
						
						writer.close();
						writeinv_stock.close();
						writeinv_stock2.close();
						
						PrintWriter pw = new PrintWriter("C:\\MasterRetails\\output.txt"); 
				        BufferedReader br1 = new BufferedReader(new FileReader("C:\\MasterRetails\\Stock.txt")); 
				          
				        String line1 = br1.readLine(); 
				        while(line1 != null) 
				        { 
				        	String[] line1_items=line1.split(",");
				            
				            pw.println(line1_items[0].trim()+","+line1_items[1].trim()+","+line1_items[2].trim()+","+qty_itemcode.get(line1_items[0])+","+formattedDate+",OPEN,");
				            
				            line1 = br1.readLine(); 
				        } 
				        
				        
				        pw.flush(); 
				          
				        br1.close(); 
				        pw.close(); 
				        
						if (log.length() == 0) 
						{ 
							int b=JOptionPane.showConfirmDialog(frame, "File is ready to Upload, Do you want to upload now","",JOptionPane.YES_NO_OPTION);
							if(b==0){
								String csvFilePath = "C:/MasterRetails/output.txt";
								con.setAutoCommit(false);
								 
					            String sql = "INSERT INTO "+company+".mr_stock_count (`barcode`, `item_name`, `cost`, `qty`, `created_date`,status) VALUES (?, ?, ?, ?, ?, ?)";
					            PreparedStatement ps = con.prepareStatement(sql);
					 
					            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
					            String lineText = null;
					 
					            //int count = 0;
					 
					            //lineReader.readLine(); // skip header line
					 
					            while ((lineText = lineReader.readLine()) != null) {
					                String[] data = lineText.split(",");
					                String barcode = data[0];
					                String item_name = data[1];
					                String cost = data[2];
					                String qty = data[3];
					                String created_date = data[4];
					                String status = data[5];
					                
					                ps.setString(1,barcode);
									ps.setString(2,item_name);
									ps.setString(3,cost);
									ps.setString(4,qty);
									ps.setString(5,created_date);
									ps.setString(6,status);
									ps.addBatch();
									ps.clearParameters();
					            }
					            lineReader.close();
					            ps.executeBatch();
								con.commit();
								con.setAutoCommit(true);
								
								JOptionPane.showMessageDialog(frame,"Imported Successfully");
								
								get_po_disp( company, store, "");
								
								Path fileToDeletePath1 = Paths.get("C:/MasterRetails/stock.txt");
								Files.delete(fileToDeletePath1);
								
								Path fileToDeletePath3 = Paths.get("C:/MasterRetails/output.txt");
								Files.delete(fileToDeletePath3);
								Path fileToDeletePath4 = Paths.get("C:/MasterRetails/CSV log.txt");
								Files.delete(fileToDeletePath4);
								Path fileToDeletePath2 = Paths.get("C:/MasterRetails/stock2.txt");
								Files.delete(fileToDeletePath2);
								
								frame.setCursor(Cursor.getDefaultCursor());
							}
							else
							{
								frame.setCursor(Cursor.getDefaultCursor());
							}
						} 
						else 
						{ 
							JOptionPane.showMessageDialog(frame,"Some Errors Found Check the log file");
							frame.setCursor(Cursor.getDefaultCursor());
							if (Desktop.isDesktopSupported()) {
								Desktop.getDesktop().edit(log);
							} else {
								// dunno, up to you to handle this
							}
						}
						
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(frame,e.getMessage());
					System.err.println(e);
					e.printStackTrace();
					frame.setCursor(Cursor.getDefaultCursor());
				}
			}
		});
		btnImportStockList.setBounds(433, 13, 181, 25);
		panel_2.add(btnImportStockList);
		
		JLabel lblTotalStoreValue = new JLabel("Total Store Value");
		lblTotalStoreValue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalStoreValue.setBounds(683, 560, 124, 16);
		panel_1.add(lblTotalStoreValue);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(813, 560, 107, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblNoOfItems = new JLabel("No. of Items");
		lblNoOfItems.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNoOfItems.setBounds(12, 561, 86, 16);
		panel_1.add(lblNoOfItems);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(100, 561, 77, 16);
		panel_1.add(lblNewLabel_1);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setBounds(12, 297, 908, 256);
		scrollPane1.setBackground(new Color(243, 229, 216));
		scrollPane1.setVisible(false);
		panel_1.add(scrollPane1);

		model1 = new DefaultTableModel(){

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
		    table1 = new JTable(model1);
		    table1.setFont(new Font("Serif", Font.BOLD, 15));
		    table1.setRowHeight(20);
		    
		    model1.addColumn("Item Code");
		    model1.addColumn("Barcode");
		    model1.addColumn("Item Name"); 
		    model1.addColumn("Selling Price"); 
		    model1.addColumn("Cost");
		    
		scrollPane1.setViewportView(table1);
		int columna=model1.getColumnCount();
		for (int i=0; i<columna; i++) {
			  TableColumn column = table1.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(30);
			  if (i==1) column.setPreferredWidth(30);
			  if (i==2) column.setPreferredWidth(30);
			  if (i==3) column.setPreferredWidth(150);
			  if (i==4) column.setPreferredWidth(10);
			}
		
		JScrollPane scrollPane11 = new JScrollPane();
		scrollPane11.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane11.setBounds(12, 297, 908, 256);
		scrollPane11.setBackground(new Color(243, 229, 216));
		scrollPane11.setVisible(true);
		panel_1.add(scrollPane11);

		model11 = new DefaultTableModel(){

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
		    table11 = new JTable(model11);
		    table11.setFont(new Font("Serif", Font.BOLD, 15));
		    table11.setRowHeight(20);
		    
		    model11.addColumn("Item Code");
		    model11.addColumn("Barcode");
		    model11.addColumn("Item Name"); 
		    model11.addColumn("System Qty"); 
		    model11.addColumn("Cost");
		    model11.addColumn("Batch"); 
		    model11.addColumn("Prod Date"); 
		    model11.addColumn("Exp. Date"); 
		    model11.addColumn("Enter Qty");
		    model11.addColumn("Vareince");
		    
		    scrollPane11.setViewportView(table11);
		int columnas=model11.getColumnCount();
		for (int i=0; i<columnas; i++) {
			  TableColumn column = table11.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(30);
			  if (i==1) column.setPreferredWidth(30);
			  if (i==2) column.setPreferredWidth(30);
			  if (i==3) column.setPreferredWidth(150);
			  if (i==4) column.setPreferredWidth(10);
			  if (i==5) column.setPreferredWidth(10);
			  if (i==6) column.setPreferredWidth(10);
			  if (i==7) column.setPreferredWidth(10);
			  if (i==8) column.setPreferredWidth(10);
			  if (i==9) column.setPreferredWidth(10);
			}
		
		
		get_po_disp( company,store,"");
		
        
	}
	
	private void export( String company, String store) throws IOException
	{
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\stock validation-"+store+".csv");

			for(int x = 0; x < model.getColumnCount(); x++){
	            excel.write(model.getColumnName(x) );
	            excel.write(",");
	        }

	        excel.write("\n");
	        

	        for(int x=0; x< model.getRowCount(); x++) {
	            for(int y=0; y < model.getColumnCount(); y++) {
	                excel.write(model.getValueAt(x,y).toString());
	                if(y < model.getColumnCount()-1)
	                {
	                	excel.write(",");
	                }
	                else
	                {
	                	excel.write("\r\n");
	                }
	            }
	        }

	        excel.close();
	        frame.setCursor(Cursor.getDefaultCursor());
	        JOptionPane.showMessageDialog(frame, "Export Completed");
	}
	private void sync(ActionEvent e,String company,String formattedDate,String username, String store) throws SQLException, InterruptedException
	{
		try {
			progressBar.setVisible(true);
			double number_progress=0;
			int b=1;
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();
			Statement statement1=con.createStatement();
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			String update_qry="update "+company+".mr_item_master set total_qty='0',modified_date='"+formattedDate+"'";
			statement1.executeUpdate(update_qry);
			String query34="SELECT count(*) as ttl FROM "+company+".mr_stock_count where status='OPEN'"; 
			ResultSet resultSet13= statement.executeQuery(query34);
			if(resultSet13.next())
			{
				double count=Double.parseDouble(resultSet13.getString("ttl"));
				number_progress=100/count;
			}
			String query33="SELECT * FROM "+company+".mr_stock_count where status='OPEN'"; 
			ResultSet resultSet3= statement.executeQuery(query33);
			while(resultSet3.next())
			{
				String id=resultSet3.getString("id");
				String item_itemcode="";
				String barcode="";
				String query="SELECT * FROM "+company+".mr_item_master where item_code='"+resultSet3.getString("barcode")+"'"; //in (`barcode1`, `barcode2`, `barcode3`,  `barcode4`, `barcode5`, `barcode6`, `barcode7`, `barcode8`, `barcode9`, `barcode10`, `barcode11`, `barcode12`,  `barcode13`, `barcode14`, `barcode15`,  `barcode16`, `barcode17`, `barcode18`) "; 
				ResultSet resultSet = statement1.executeQuery(query);
				if(resultSet.next())
				{
					item_itemcode=resultSet.getString("item_code");
					barcode=resultSet.getString("barcode1");
				}
				String cost=resultSet3.getString("cost");
				String qty=resultSet3.getString("qty");
				//item_cost='"+cost+"',item_lastcost='"+cost+"',
				String update="update "+company+".mr_item_master set total_qty='"+qty+"',item_cost='"+cost+"',item_lastcost='"+cost+"',modified_date='"+formattedDate+"' where item_code='"+resultSet3.getString("barcode")+"'";
				statement1.executeUpdate(update);
				String insert_his="insert into "+company+".mr_item_master_history "
						+ "(`itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`, `barcode`, `cust_id`, `cust_name`) values"
						+ "('"+item_itemcode+"','STOCK','','"+qty+"','Each','1','"+cost+"','"+cost+"','0','0','0','STOCK UPDATE','"+username+"','"+formattedDate+"','"+barcode+"','','')";
				statement1.executeUpdate(insert_his);
				String insert1="update "+company+".mr_stock_count set status='POSTED'  where id='"+id+"' ";
				statement1.executeUpdate(insert1);
				double ttl_count=b*number_progress;
				Thread.sleep(50);
	        	 progressBar.paintImmediately(0, 0, 908, 31);
				progressBar.setValue((int)ttl_count);
				b++;
			}
			String insert1="INSERT INTO "+company+".mr_stock_count_history(`barcode`, `item_name`, `cost`, `qty`, `created_date`, `status`) SELECT  `barcode`, `item_name`, `cost`, `qty`, `created_date`, `status` FROM "+company+".mr_stock_count WHERE status='POSTED' ";
			statement1.executeUpdate(insert1);
			String delete="delete FROM "+company+".mr_stock_count WHERE status='POSTED' ";
			statement1.executeUpdate(delete);
			
			item_list_master.get_symc_itms(company);
			
			JOptionPane.showMessageDialog(frame, "POSTED SUCCESSFULLY");
			get_po_disp( company, store, "");
			frame.setCursor(Cursor.getDefaultCursor());
			progressBar.setVisible(false);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(frame, e1.getMessage());
		}
		
		
	}
	private void  get_po_disp( String company, String store, String search )
	{
		try{
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
        	    model.removeRow(i);
        	}
			double ttl=0;
			int decimalPlaces = 2;
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query1="SELECT *,mr_tmp_item_master.qty as ttl_qty FROM "+company+".mr_stock_count inner join "+company+".mr_tmp_item_master on "+company+".mr_stock_count.barcode= "+company+".mr_tmp_item_master.item_code where status='OPEN' group by item_code"; 
			ResultSet resultSet1 = statement.executeQuery(query1);
			int q=0;
			ArrayList<String> itemcodes=new ArrayList<String>();
			ArrayList<String> itemcode_dup=new ArrayList<String>();
			while(resultSet1.next())
			{
				String barcode=resultSet1.getString("barcode");
                String itemname=resultSet1.getString("item_name");
                String cost=resultSet1.getString("cost");
                String enter_qty=resultSet1.getString("qty");
                
    			String cf=resultSet1.getString("cf");
    			String item_code=resultSet1.getString("item_code");
    			String qty=resultSet1.getString("ttl_qty");
    			double system_qty=Double.parseDouble(qty)*Double.parseDouble(cf);
                
    			if(itemcodes.contains(item_code))
    			{
    				itemcode_dup.add(item_code);
    			}
    			itemcodes.add(item_code);
                double varience=system_qty-Double.parseDouble(enter_qty);
                double value=Double.parseDouble(cost)*Double.parseDouble(enter_qty);
				
				BigDecimal bd = new BigDecimal(varience);
        		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		varience = bd.doubleValue();
        		BigDecimal bc = new BigDecimal(value);
        		bc = bc.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        		value = bc.doubleValue();
        		ttl=value+ttl;
                model.addRow(new Object[]{item_code,barcode,itemname,cost,system_qty,enter_qty,varience,value});
               q++;
			}
			lblNewLabel_1.setText(Integer.toString(q));
			 btnPostStockList.setEnabled(true);
			 BigDecimal bc = new BigDecimal(ttl);
     		bc = bc.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
     		ttl = bc.doubleValue();
			 lblNewLabel.setText(Double.toString(ttl));
			int a=model.getRowCount();
			if(a==0)
			{
				JOptionPane.showMessageDialog(frame, "No Items Found");
	        	for (int i = model.getRowCount() - 1; i >= 0; i--) {
	        	    model.removeRow(i);
	        	}
	        	btnPostStockList.setEnabled(false);
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
