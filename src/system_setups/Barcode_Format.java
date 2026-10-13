package system_setups;

import java.awt.EventQueue;
import java.awt.Image;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import javax.swing.ScrollPaneConstants;

import org.json.JSONException;




import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Barcode_Format {

	private static JFrame frame;
	static JTextField textField;
	private static JTextField textField_1;
	private static JComboBox<String> comboBox; 
	private static JTextArea textArea ;
	private static JCheckBox chckbxNewCheckBox;
	private static JComboBox<String> comboBox_1;
	private static DefaultTableModel model;
	private JTable table;
	private static JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					Barcode_Format window = new Barcode_Format("mr_pro", null);
					Barcode_Format.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Barcode_Format(String company, String version) throws JSONException {
		initialize(company, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 */
	private void initialize(final String company, final String version) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 700, 600);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 684, 551);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		Image Barcode = new ImageIcon(this.getClass().getResource("/Barcode.png")).getImage();
		JLabel lblBarcodeFormat = new JLabel("Barcode Format");
		lblBarcodeFormat.setIcon(new ImageIcon(Barcode));
		lblBarcodeFormat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBarcodeFormat.setBounds(10, 11, 228, 44);
		panel.add(lblBarcodeFormat);
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 78, 684, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Barcode Format :");
		lblNewLabel.setBounds(28, 91, 108, 14);
		panel.add(lblNewLabel);
		
		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setBounds(28, 116, 97, 14);
		panel.add(lblDescription);
		
		JLabel lblPort = new JLabel("Type :");
		lblPort.setBounds(389, 91, 45, 14);
		panel.add(lblPort);
		
		textField = new JTextField();
		textField.setBounds(144, 88, 162, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		Image search = new ImageIcon(this.getClass().getResource("/search.png")).getImage();	
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Select_barcode(company, version);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(search));
		btnNewButton.setBounds(316, 87, 45, 23);
		panel.add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(144, 113, 278, 23);
		panel.add(textField_1);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("Label");
		comboBox.addItem("Label2");
		comboBox.addItem("Label3");
		comboBox.addItem("Label4");
		comboBox.addItem("Shelf Tag");
		comboBox.addItem("Shelf2 Tag");
		comboBox.addItem("Shelf3 Tag");
		comboBox.addItem("Shelf4 Tag");
		comboBox.setBounds(464, 88, 138, 20);
		panel.add(comboBox);
		
		JLabel lblRawCode = new JLabel("Code :");
		lblRawCode.setBounds(28, 165, 97, 14);
		panel.add(lblRawCode);
		
		textArea = new JTextArea();
		textArea.setBounds(1, 1, 340, 219);
		panel.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(144, 165, 530, 380);
		panel.add(scrollPane);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBounds(462, 11, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				textField.setText("");
				textField_1.setText("");
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBounds(522, 11, 80, 48);
		panel.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String format=textField.getText();
				String desc_barcode=textField_1.getText();
				
				String port=comboBox.getSelectedItem().toString();
				String printer=comboBox_1.getSelectedItem().toString();
				String printerlink = Base64.encode(printer.getBytes());
				String code=textArea.getText();
				String status="0";
				if(format.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter or Select Barcode Format");
				}
				else if(code.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Please Enter Barcode Format Code");
				}
				else
				{
					if(chckbxNewCheckBox.isSelected())
					{
						status="1";
					}
					
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement;
					try {
						statement = con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_barcode where barcode_format='"+format+"' and port='"+port+"' ");
						if(resultSet.next())
						{
							if(status.equals("1"))
							{
								String query="update "+company+".mr_barcode set status='0' ";
								statement.executeUpdate(query);
							}
							String query="update "+company+".mr_barcode set barcode_desc='"+desc_barcode+"',port='"+port+"',printer='"+printerlink+"',code='"+code+"',status='"+status+"',modified_date='"+formattedDate+"' where barcode_format='"+format+"' and port='"+port+"' ";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
						else
						{
							if(status.equals("1"))
							{
								String query="update "+company+".mr_barcode set status='0' ";
								statement.executeUpdate(query);
							}
							String query="insert into "+company+".mr_barcode (barcode_desc,port,printer,code,status,modified_date,created_date,barcode_format) values('"+desc_barcode+"','"+port+"','"+printerlink+"','"+code+"','"+status+"','"+formattedDate+"','"+formattedDate+"','"+format+"')";
							statement.executeUpdate(query);
							JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(frame,e1.getMessage());
					}  
				}
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBounds(604, 11, 70, 48);
		panel.add(menuItem_2);
		
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setBounds(10, 203, 126, 335);
		scrollPane1.setBackground(new Color(243, 229, 216));
		panel.add(scrollPane1);

		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		    
		};
		    table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
		    table.setRowHeight(20);
		    
		    model.addColumn("Variable list"); 
		    scrollPane1.setViewportView(table);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(120);
			}
		
		get_barcodevariablelist( company);
		
		chckbxNewCheckBox = new JCheckBox("Set as Default");
		chckbxNewCheckBox.setBounds(144, 138, 113, 25);
		chckbxNewCheckBox.setBackground(new Color(243, 229, 216));
		panel.add(chckbxNewCheckBox);
		
		JLabel lblPrinter = new JLabel("Printer : ");
		lblPrinter.setBounds(434, 115, 56, 22);
		panel.add(lblPrinter);
		
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(488, 113, 149, 22);
		for (PrintService printer1 : printServices)
	    {
	    	comboBox_1.addItem(printer1.getName());
	    }
		panel.add(comboBox_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(575, 140, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
	}
	public static void get_barcodevariablelist(String company)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="select * from "+company+".mr_barcode_variable ";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				//String barcode_variable_for=resultSet.getString("barcode_variable_for");
                String barcode_variable_list=resultSet.getString("barcode_variable_list");
                model.addRow(new Object[]{barcode_variable_list});
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	public static void get_storedisp(String company, String format, String port)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="select * from "+company+".mr_barcode where barcode_format='"+format+"' and port='"+port+"' ";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				textField.setText(resultSet.getString("barcode_format"));
				textField_1.setText(resultSet.getString("barcode_desc"));
				textField_2.setText(resultSet.getString("port"));
				textArea.setText(resultSet.getString("code"));
				comboBox.setSelectedItem(resultSet.getString("port"));
				
				byte[] printers=Base64.decode(resultSet.getString("printer").toString()); //resultSet.getString("printer");
				String printer = new String(printers, StandardCharsets.UTF_8);
				comboBox_1.setSelectedItem(printer);
				if(resultSet.getString("status").equals("1"))
				{
					chckbxNewCheckBox.setSelected(true);
				}
				else
				{
					chckbxNewCheckBox.setSelected(false);
				}
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
	}
}
