package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuItem;

import org.json.JSONException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class assign_kot_printer {

	private static JFrame frame;
	public static JTextField textField;
	private JTable table;
	private static DefaultTableModel model;
	private JComboBox<String> comboBox;
	static JTextField textField_1;
	private JCheckBox chckbxNewCheckBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					assign_kot_printer window = new assign_kot_printer("mr_pro", null, null);
					assign_kot_printer.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public assign_kot_printer(String company, String version, String reg_name) throws JSONException {
		initialize(company, version,reg_name);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @throws JSONException 
	 */
	private void initialize(final String company,final String version, final String reg_name) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 650, 500);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 634, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblShift = new JLabel("Assign Printer");
		lblShift.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShift.setBounds(10, 11, 228, 44);
		panel.add(lblShift);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 72, 684, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Department");
		lblNewLabel.setBounds(21, 98, 102, 14);
		panel.add(lblNewLabel);
		
		JLabel lblShiftName = new JLabel("Printer");
		lblShiftName.setBounds(21, 130, 108, 14);
		panel.add(lblShiftName);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(133, 95, 45, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		Image search=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new department_list(company,version,"printer");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_3.setIcon(new ImageIcon(search));
		button_3.setBounds(427, 92, 45, 23);
		panel.add(button_3);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 169, 684, 2);
		panel.add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 169, 634, 293);
		panel.add(scrollPane);
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
			model.addColumn("Department Code"); 
			model.addColumn("Department Name"); 
		    model.addColumn("Printer"); 
			scrollPane.setViewportView(table);
		    
		    Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
			Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
			Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		    
		    JMenuItem menuItem = new JMenuItem("Exit");
		    menuItem.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		frame.setVisible(false);
		    	}
		    });
		    menuItem.setBorder(null);
		    menuItem.setIcon(new ImageIcon(exit));
		    menuItem.setBackground(new Color(243, 229, 216));
		    menuItem.setBounds(410, 11, 60, 48);
		    panel.add(menuItem);
		    
		    JMenuItem menuItem_1 = new JMenuItem("Refresh");
		    menuItem_1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		textField.setText("");
		    		textField_1.setText("");
		    	}
		    });
		    menuItem_1.setBorder(null);
		    menuItem_1.setIcon(new ImageIcon(refresh));
		    menuItem_1.setBackground(new Color(243, 229, 216));
		    menuItem_1.setBounds(471, 11, 80, 48);
		    panel.add(menuItem_1);
		    
		    JMenuItem menuItem_2 = new JMenuItem("Save");
		    menuItem_2.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		try {
		    			LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);  
					    String printer=comboBox.getSelectedItem().toString();
					    Connection con=Dbsetup.dbconn.getConnection();
						Statement statement= con.createStatement();
						Statement statement1= con.createStatement();
		    			if(chckbxNewCheckBox.isSelected())
		    			{
		    				String del_dept="delete from "+company+".mr_assign_kot_printer where reg_nme='"+reg_name+"' ";
							statement.executeUpdate(del_dept);
		    				ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_department");
							while(resultSet.next())
							{
								String query1="insert into "+company+".mr_assign_kot_printer (`reg_nme`, `dept_code`, `dept_name`, `printer`, `created_date`, `modified_date`) "
										+ "values('"+reg_name+"','"+resultSet.getString("dept_code")+"','"+resultSet.getString("dept_name")+"','"+printer+"','"+formattedDate+"','"+formattedDate+"')";
								statement1.executeUpdate(query1);
								
							}
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
							textField.setText("");
				    		textField_1.setText("");
							 get_registeredisp(company, reg_name);
		    			}
		    			else
		    			{
		    				String dept_code=textField.getText();
				    		if(dept_code.equals(""))
				    		{
				    			JOptionPane.showMessageDialog(frame, "Select a Department from list");
				    		}
				    		else
				    		{
				    			
								ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_assign_kot_printer where reg_nme='"+reg_name+"' and dept_code='"+dept_code+"' ");
								if(resultSet.next())
								{
									String query1="update "+company+".mr_assign_kot_printer set printer='"+printer+"' where reg_nme='"+reg_name+"' and dept_code='"+dept_code+"' ";
									statement.executeUpdate(query1);
									JOptionPane.showMessageDialog(frame, "Saved Successfully");
									textField.setText("");
						    		textField_1.setText("");
									 get_registeredisp(company, reg_name);
								}
								else
								{
									String query1="insert into "+company+".mr_assign_kot_printer (`reg_nme`, `dept_code`, `dept_name`, `printer`, `created_date`, `modified_date`) values('"+reg_name+"','"+dept_code+"','"+textField_1.getText()+"','"+printer+"','"+formattedDate+"','"+formattedDate+"')";
									statement.executeUpdate(query1);
									JOptionPane.showMessageDialog(frame, "Saved Successfully");
									textField.setText("");
						    		textField_1.setText("");
									 get_registeredisp(company, reg_name);
								}
				    		}	
		    			}
		    		} 
		    		catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
		    	}
		    });
		    menuItem_2.setBorder(null);
		    menuItem_2.setIcon(new ImageIcon(save));
		    menuItem_2.setBackground(new Color(243, 229, 216));
		    menuItem_2.setBounds(550, 11, 70, 48);
		    panel.add(menuItem_2);
		    
		    comboBox = new JComboBox<String>();
		    comboBox.setBounds(133, 127, 337, 20);
		    panel.add(comboBox);
		    
		    textField_1 = new JTextField();
		    textField_1.setEditable(false);
		    textField_1.setBounds(181, 95, 247, 20);
		    panel.add(textField_1);
		    textField_1.setColumns(10);
		    
		    chckbxNewCheckBox = new JCheckBox("<html>Apply All Departments<br>\r\n to default Printer<html>");
		    chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		    chckbxNewCheckBox.setVerticalAlignment(SwingConstants.TOP);
		    chckbxNewCheckBox.setBounds(478, 94, 150, 68);
		    chckbxNewCheckBox.setBackground(new Color(243, 229, 216));
		    panel.add(chckbxNewCheckBox);
		    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		    for (PrintService printer1 : printServices)
		    {
		    	comboBox.addItem(printer1.getName());
		    }
		    
		    table.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent mouseEvent) {
	                JTable table =(JTable) mouseEvent.getSource();
	                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
	                	int rows = table.getSelectedRow();
	                	String code = table.getModel().getValueAt(rows, 0).toString();
	                	String name = table.getModel().getValueAt(rows, 1).toString();
	                	String printer = table.getModel().getValueAt(rows, 2).toString();
	                	textField.setText(code);
	                	textField_1.setText(name);
	                	comboBox.setSelectedItem(printer);
	                }
	            }
	        });
		    
		    get_registeredisp(company, reg_name);
	}
	public static void get_registeredisp(String company, String reg_num)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			String query="select * from "+company+".mr_assign_kot_printer where reg_nme='"+reg_num+"' ";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String dept_code=resultSet.getString("dept_code");
				String dept_name=resultSet.getString("dept_name");
				String printer=resultSet.getString("printer");
				model.addRow(new Object[]{dept_code, dept_name,printer});
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		
	}
}
