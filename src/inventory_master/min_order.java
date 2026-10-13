package inventory_master;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.json.JSONException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;

public class min_order {

	JFrame frame;
	static JLabel lblloader;
	private JScrollPane scrollPane;
	public static JTable table;
	private static DefaultTableModel model;
	private JScrollPane scrollPane_1;
	public static JTable table1;
	private static DefaultTableModel model1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					min_order window = new min_order("mr_pro", null,"mr_pro","S01","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param username 
	 */
	public min_order(String company, String version,String dbname,String store, String username) throws JSONException {
		initialize(company, version,dbname,store,username);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 */
	private void initialize(final String company,final String version,final String dbname,final String store, String username) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 546);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		
		Image loader=new ImageIcon(getClass().getResource("/loader.gif")).getImage();
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(453, 49, 15, 390);
		frame.getContentPane().add(separator_2);
		
		
		lblloader = new JLabel("");
		lblloader.setBounds(277, 361, 38, 38);
		frame.getContentPane().add(lblloader);
		lblloader.setIcon(new ImageIcon(loader));
		lblloader.setVisible(false);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBounds(12, 0, 60, 48);
		frame.getContentPane().add(menuItem);
		
		
		JLabel lblNewLabel = new JLabel("MINIMUM ORDER ITEMS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(125, 0, 220, 48);
		
		frame.getContentPane().add(lblNewLabel);

		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 437, 895, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel1 = new JLabel("");
		//lblNewLabel1.setIcon(new ImageIcon(cafepro));
		lblNewLabel1.setBounds(0, 683, 58, 50);
		frame.getContentPane().add(lblNewLabel1);
		
		JLabel lblCurrentUser = new JLabel("CURRENT USER "+username);
		lblCurrentUser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCurrentUser.setBounds(31, 437, 265, 26);
		frame.getContentPane().add(lblCurrentUser);
		
		JLabel lblRegistredToCompany = new JLabel("REGISTERED TO  "+dbname);
		lblRegistredToCompany.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegistredToCompany.setBounds(366, 454, 516, 14);
		frame.getContentPane().add(lblRegistredToCompany);
		
		JLabel lblNewLabel_1 = new JLabel(version);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(488, 486, 394, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		final JLabel timeLabel  = new JLabel("");
		timeLabel .setBounds(24, 474, 293, 16);
		frame.getContentPane().add(timeLabel );
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 49, 895, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel lblExpiringItems = new JLabel("BELOW MIN ORDER");
		lblExpiringItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpiringItems.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExpiringItems.setBounds(0, 49, 453, 31);
		frame.getContentPane().add(lblExpiringItems);
		
		JLabel lblExpiredItems = new JLabel("NEAR MIN ORDER");
		lblExpiredItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpiredItems.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExpiredItems.setBounds(453, 49, 324, 31);
		frame.getContentPane().add(lblExpiredItems);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.setBounds(5, 87, 443, 337);
		frame.getContentPane().add(scrollPane);
		
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
				if(column==13)
				{
					return true;
				}
		       return false;
		    }
			
		};
		table = new JTable(model);
	    table.setFont(new Font("Serif", Font.BOLD, 15));
		table.setRowHeight(20);
		table.setEnabled(true);
	    model.addColumn("S.NO"); 
		model.addColumn("Item Code"); 
		model.addColumn("Barcode"); 
		model.addColumn("Item Details"); 
		
		model.addColumn("Min Order"); 
		model.addColumn("QtyOH"); 
		scrollPane.setViewportView(table);
		
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(45);
		  if (i==1) column.setPreferredWidth(80);
		  if (i==2) column.setPreferredWidth(75);
		  if (i==3) column.setPreferredWidth(150);
		  if (i==4) column.setPreferredWidth(75);
		  if (i==5) column.setPreferredWidth(75);
		}
		
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBackground(new Color(243, 229, 216));
		scrollPane_1.setBounds(460, 87, 422, 337);
		frame.getContentPane().add(scrollPane_1);
		
		model1 = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
				if(column==13)
				{
					return true;
				}
		       return false;
		    }
			
		};
		table1 = new JTable(model1);
	    table1.setFont(new Font("Serif", Font.BOLD, 15));
		table1.setRowHeight(20);
		table1.setEnabled(true);
	    model1.addColumn("S.NO"); 
		model1.addColumn("Item Code");
		model1.addColumn("Barcode"); 
		model1.addColumn("Item Details"); 
		
		model1.addColumn("Min Order"); 
		model1.addColumn("QtyOH"); 
		scrollPane_1.setViewportView(table1);
		
		
		JLabel lblNewLabel_2 = new JLabel(company);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(558, 21, 324, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnExport = new JButton("Export");
		btnExport.setToolTipText("C:\\\\MasterRetails\\\\Export\\\\below_minorder.csv");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\below_minorder.csv");
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
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

					
			}
		});
		btnExport.setBounds(354, 62, 89, 23);
		frame.getContentPane().add(btnExport);
		
		JButton btnExport_1 = new JButton("Export");
		btnExport_1.setToolTipText("C:\\\\MasterRetails\\\\Export\\\\near_minorder.csv");
		btnExport_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					table1.scrollRectToVisible(table1.getCellRect(table1.getRowCount()-1, 0, true));
					FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\near_minorder.csv");
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
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		btnExport_1.setBounds(795, 57, 89, 23);
		frame.getContentPane().add(btnExport_1);
		int colmns=model1.getColumnCount();
		for (int i=0; i<colmns; i++) {
		  TableColumn column = table1.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(45);
		  if (i==1) column.setPreferredWidth(80);
		  if (i==2) column.setPreferredWidth(75);
		  if (i==3) column.setPreferredWidth(150);
		  if (i==4) column.setPreferredWidth(75);
		  if (i==5) column.setPreferredWidth(75);
		}
		
		
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement1=con.createStatement();  
			String query1="SELECT * FROM "+dbname+".mr_item_master WHERE  (min_order<total_qty and min_order+25>total_qty)"; 
			ResultSet resultSet1 = statement1.executeQuery(query1);
			int rowCount1 = model1.getRowCount();
			for (int i = rowCount1 - 1; i >= 0; i--) {
				model1.removeRow(i);
			}
			if(resultSet1.next())
			{
				String query2="SELECT * FROM "+dbname+".mr_item_master WHERE  (min_order<total_qty and min_order+25>total_qty)"; 
				ResultSet resultSet2 = statement1.executeQuery(query2);
				while(resultSet2.next())
				{
					String item_code=resultSet2.getString("item_code");
	                String item_name=resultSet2.getString("item_name");
	                String barcode=resultSet2.getString("barcode1");
	                String min_order=resultSet2.getString("min_order");
	                String qty=resultSet2.getString("total_qty");
	                model1.addRow(new Object[]{model1.getRowCount()+1,item_code,barcode,item_name,min_order,qty});
				}
			}
			
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+dbname+".mr_item_master WHERE  min_order >= total_qty"; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query3="SELECT * FROM "+dbname+".mr_item_master WHERE  min_order >= total_qty"; 
				ResultSet resultSet3 = statement.executeQuery(query3);
				while(resultSet3.next())
				{
					String item_code=resultSet3.getString("item_code");
	                String item_name=resultSet3.getString("item_name");
	                String barcode=resultSet3.getString("barcode1");
	                String min_order=resultSet3.getString("min_order");
	                String qty=resultSet3.getString("total_qty");
	                model.addRow(new Object[]{model.getRowCount()+1,item_code,barcode,item_name,min_order,qty});
				}
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		
		final DateFormat timeFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
        ActionListener timerListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Date date = new Date();
                String time = timeFormat.format(date);
                timeLabel.setText(time);
            }
        };
        Timer timer = new Timer(1000, timerListener);
        timer.setInitialDelay(0);
        timer.start();
	}
}
