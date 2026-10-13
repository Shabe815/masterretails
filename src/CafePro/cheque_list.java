package CafePro;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.ScrollPaneConstants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;

public class cheque_list {

	public static JFrame frame;
	private static DefaultTableModel model;
	private static JTable table;
	private JTextField textField;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					cheque_list window = new cheque_list("mr_pro","");
					cheque_list.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param store_name 
	 * @param con 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	public cheque_list(String db_name,String version) throws JSONException, SQLException {
		initialize(db_name,version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param store_name 
	 * @param decimal 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String db_name,String version) throws JSONException, SQLException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 1024, 734);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				frame.setVisible(false);
			}
		});
		frame.getContentPane().setLayout(null);
		
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		//frame.setUndecorated(true);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 994, 60);
		frame.getContentPane().add(panel);
		
		Image doc = new ImageIcon(this.getClass().getResource("/doc.png")).getImage();
		
		JButton btnExportToCsv = new JButton("Export To CSV");
		btnExportToCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					export_as_csv();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
		});
		btnExportToCsv.setBounds(743, 15, 157, 33);
		btnExportToCsv.setIcon(new ImageIcon(doc));
		panel.add(btnExportToCsv);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Cheque List");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(17, 0, 124, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(917, 15, 60, 33);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 994, 768);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 118, 970, 497);
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
		    
		    model.addColumn("S.No"); 
		    model.addColumn("Issue Date"); 
		    model.addColumn("Invoice Number");
		    model.addColumn("Customer Name");
		    model.addColumn("Cheque No."); 
		    model.addColumn("Cheque Date"); 
		    model.addColumn("Cr");
		    model.addColumn("Dr");
		scrollPane.setViewportView(table);
		
		int columns=model.getColumnCount();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(0,50));
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(40);
			  if (i==1) column.setPreferredWidth(100);
			  if (i==2) column.setPreferredWidth(150);
			  if (i==3) column.setPreferredWidth(300);
			  if (i==4) column.setPreferredWidth(100);
			  if (i==5) column.setPreferredWidth(100);
			  if (i==6) column.setPreferredWidth(80);
			  if (i==7) column.setPreferredWidth(80);
			}
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 970, 92);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblDateRange = new JLabel("Date Range");
		lblDateRange.setBounds(12, 16, 87, 22);
		panel_2.add(lblDateRange);
		
		textField = new JTextField();
		textField.setBounds(12, 54, 806, 22);
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
					Connection con=Dbsetup.dbconn.getConnection();
					String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					String search=textField.getText();
					get_itemdisp( db_name,  con,date1,date2,search);
			  }
			});
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(229, 16, 38, 22);
		panel_2.add(lblFrom);
		
		Date date = new Date();
		
		dateChooser = new JDateChooser();
		dateChooser = new JDateChooser(date);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(338, 16, 100, 22);
		panel_2.add(dateChooser);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(509, 16, 25, 22);
		panel_2.add(lblTo);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setDate(date);
		dateChooser_1.setBounds(605, 16, 100, 22);
		panel_2.add(dateChooser_1);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=Dbsetup.dbconn.getConnection();
				String date1=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				String date2=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
				String search=textField.getText();
				get_itemdisp( db_name,  con,date1,date2,search);
			}
		});
		btnNewButton.setBounds(841, 16, 117, 60);
		panel_2.add(btnNewButton);
		
		
		
		Connection con=Dbsetup.dbconn.getConnection();
        get_itemdisp( db_name,  con,"","","");
        
	}
	
	private void export_as_csv( ) throws IOException {
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		FileWriter excel = new FileWriter("C:\\MasterRetails\\Export\\cheque_list.csv");

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
	private void  get_itemdisp( String company,  Connection con,String date1,String date2,String search)
	{
		try
		{
			String searchStr1="";
			if(!search.equals(""))
			{
				searchStr1="and ( pay_by like '%"+search+"%' or tran_num like '%"+search+"%') ";
			}
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			Statement statement=con.createStatement();
			String query1="SELECT * FROM "+company+".mr_cheque_details where cheque_date between '"+date1+"' and  '"+date2+"' "+searchStr1+" ";
					 
			ResultSet resultSet2 = statement.executeQuery(query1);
			if(resultSet2.next())
			{
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					double dr=Double.parseDouble(resultSet1.getString("dr"));
					double cr=Double.parseDouble(resultSet1.getString("cr"));
					BigDecimal bz = new BigDecimal(dr);
		    		bz = bz.setScale(2, BigDecimal.ROUND_HALF_UP);
		    		dr = bz.doubleValue();
		    		BigDecimal by = new BigDecimal(cr);
		    		by = by.setScale(2, BigDecimal.ROUND_HALF_UP);
		    		cr = by.doubleValue();
					model.addRow(new Object[]{model.getRowCount()+1,resultSet1.getString("issue_date"),resultSet1.getString("tran_num"),resultSet1.getString("pay_by"),resultSet1.getString("cheque_no"),resultSet1.getString("cheque_date"),cr,dr });
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "No Cheque Founded");
			}
			
			
			
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
