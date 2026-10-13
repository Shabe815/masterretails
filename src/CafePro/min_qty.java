package CafePro;

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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.json.JSONException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class min_qty {

	JFrame frame;
	private JScrollPane scrollPane;
	public static JTable table;
	private static DefaultTableModel model;
	private JScrollPane scrollPane_1;
	public static JTable table1;
	private static DefaultTableModel model1;
	private JTextField textField;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					min_qty window = new min_qty("mr_pro", null,null,"","");
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
	public min_qty(String company, String version,String dbname, String username, String language) throws JSONException {
		initialize(company, version,dbname,username,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param language 
	 */
	private void initialize(final String company,final String version,final String dbname, String username, String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 904, 700);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(453, 104, 15, 568);
		frame.getContentPane().add(separator_2);
		
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
		if(language.equals("Arabic"))
		{
			lblNewLabel.setText("«·Õœ «·√œ‰Ï ··ÿ·»");
		}
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(125, 0, 220, 48);
		
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel("");
		//lblNewLabel1.setIcon(new ImageIcon(cafepro));
		lblNewLabel1.setBounds(287, 813, 58, 50);
		frame.getContentPane().add(lblNewLabel1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 104, 895, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel lblExpiringItems = new JLabel("BELOW MIN ORDER");
		if(language.equals("Arabic"))
		{
			lblExpiringItems.setText("√ﬁ· „‰ «·Õœ «·√œ‰Ï ··ÿ·»");
		}
		lblExpiringItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpiringItems.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExpiringItems.setBounds(0, 104, 358, 31);
		frame.getContentPane().add(lblExpiringItems);
		
		JLabel lblExpiredItems = new JLabel("NEAR MIN ORDER");
		if(language.equals("Arabic"))
		{
			lblExpiredItems.setText("ÿ·» ﬁ—Ì» „‰ «·Õœ «·√œ‰Ï");
		}
		lblExpiredItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpiredItems.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExpiredItems.setBounds(453, 104, 324, 31);
		frame.getContentPane().add(lblExpiredItems);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBackground(new Color(243, 229, 216));
		scrollPane.setBounds(5, 146, 443, 515);
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
		if(language.equals("Arabic"))
		{
			model.addColumn("«·—ﬁ„ «· ”·”·Ì"); 
			model.addColumn("—„“ «·⁄‰’—"); 
			model.addColumn("«·»«—ﬂÊœ"); 
			model.addColumn(" ›«’Ì· «·”·⁄…"); 
			
			model.addColumn("QtyOH"); 
		}
		else
		{
	    model.addColumn("S.NO"); 
		model.addColumn("Item Code"); 
		model.addColumn("Barcode"); 
		model.addColumn("Item Details"); 
		
		model.addColumn("QtyOH");
		}
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
		scrollPane_1.setBounds(460, 146, 422, 515);
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
		if(language.equals("Arabic"))
		{
			model1.addColumn("«·—ﬁ„ «· ”·”·Ì"); 
			model1.addColumn("—„“ «·⁄‰’—"); 
			model1.addColumn("«·»«—ﬂÊœ"); 
			model1.addColumn(" ›«’Ì· «·”·⁄…"); 
			
			model1.addColumn("QtyOH"); 
		}
		else
		{
	    model1.addColumn("S.NO"); 
		model1.addColumn("Item Code"); 
		model1.addColumn("Barcode"); 
		model1.addColumn("Item Details"); 
		
		model1.addColumn("QtyOH");
		}
		scrollPane_1.setViewportView(table1);
		
		JButton btnExport = new JButton("Export");
		if(language.equals("Arabic"))
		{
			btnExport.setText("Ì’œ¯—");
		}
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
		btnExport.setBounds(354, 110, 89, 23);
		frame.getContentPane().add(btnExport);
		
		JButton btnExport_1 = new JButton("Export");
		if(language.equals("Arabic"))
		{
			btnExport_1.setText("Ì’œ¯—");
		}
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
				        JOptionPane.showMessageDialog(frame, "Export Completed «ﬂ „· «· ’œÌ—");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		btnExport_1.setBounds(793, 110, 89, 23);
		frame.getContentPane().add(btnExport_1);
		
		JLabel lblNearMinOrder = new JLabel("NEAR MIN ORDER= MIN ORDER +");
		if(language.equals("Arabic"))
		{
			lblNearMinOrder.setText("»«·ﬁ—» „‰ «·Õœ «·√œ‰Ï ··ÿ·» = «·Õœ «·√œ‰Ï ··ÿ·» +");
		}
		lblNearMinOrder.setBounds(585, 21, 192, 14);
		frame.getContentPane().add(lblNearMinOrder);
		
		textField = new JTextField();
		textField.setText("10");
		textField.setBounds(774, 18, 46, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Go");
		if(language.equals("Arabic"))
		{
			btnNewButton.setText("Ì–Â»");
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_disp(dbname,  textField.getText(),comboBox.getSelectedItem().toString());
			}
		});
		btnNewButton.setBounds(823, 70, 72, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblDepartment = new JLabel("DEPARTMENT");
		if(language.equals("Arabic"))
		{
			lblDepartment.setText("ﬁ”„");
		}
		lblDepartment.setBounds(585, 49, 110, 14);
		frame.getContentPane().add(lblDepartment);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("");
		comboBox.setBounds(705, 46, 190, 20);
		frame.getContentPane().add(comboBox);
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
		
		get_disp(dbname,  textField.getText(),comboBox.getSelectedItem().toString());
		get_dept(dbname);
		
		
	}
	private void get_dept(String dbname) {
		ResultSet resultSet = null;
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			resultSet = statement.executeQuery("select * from "+dbname+".mr_department ");
			while(resultSet.next())
			{
				comboBox.addItem(resultSet.getString("dept_name"));
			}
			con.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog( frame,e.getMessage() );
		}
	}

	private void  get_disp(String dbname, String enterqty, String dept)
	{
		try{
			String dept_str="";
			if(!dept.equals(""))
			{
				dept_str="and department='"+dept+"'";
			}
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement1=con.createStatement();  
			String query1="SELECT * FROM "+dbname+".mr_item_master  WHERE  total_qty > min_order and total_qty < min_order+"+enterqty+" "+dept_str+" "; 
			ResultSet resultSet1 = statement1.executeQuery(query1);
			int rowCount1 = model1.getRowCount();
			for (int i = rowCount1 - 1; i >= 0; i--) {
				model1.removeRow(i);
			}
			if(resultSet1.next())
			{
				String query2="SELECT * FROM "+dbname+".mr_item_master WHERE  total_qty > min_order and total_qty < min_order+"+enterqty+" "+dept_str+" ";  
				ResultSet resultSet2 = statement1.executeQuery(query2);
				while(resultSet2.next())
				{
					String item_code=resultSet2.getString("item_code");
	                String item_name=resultSet2.getString("item_name");
	                String barcode=resultSet2.getString("barcode1");
	                String min_order=resultSet2.getString("total_qty");
	                model1.addRow(new Object[]{model1.getRowCount()+1,item_code,barcode,item_name,min_order});
				}
			}
			
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+dbname+".mr_item_master WHERE  total_qty <= min_order "+dept_str+" ";  
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query3="SELECT * FROM "+dbname+".mr_item_master WHERE  total_qty <= min_order "+dept_str+" ";  
				ResultSet resultSet3 = statement.executeQuery(query3);
				while(resultSet3.next())
				{
					String item_code=resultSet3.getString("item_code");
	                String item_name=resultSet3.getString("item_name");
	                String barcode=resultSet3.getString("barcode1");
	                String min_order=resultSet3.getString("total_qty");
	                model.addRow(new Object[]{model.getRowCount()+1,item_code,barcode,item_name,min_order});
				}
			}
			con.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
