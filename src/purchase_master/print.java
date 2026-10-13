package purchase_master;

import java.awt.EventQueue;
import java.awt.Image;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.json.JSONException;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

public class print {

	private static JFrame frame;
	private JTextField textField;
	private JComboBox<String> comboBox ;
	static double price=0;
	static String cf="";
	static String item_name="";
	static String item_desc1="";
	static String item_desc2="";
	
	private static DecimalFormat df2=new DecimalFormat("0.00");
	private static int decimalPlaces;
	private static JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					print window = new print("mr_pro", "6291105794398","TESTING ITEM","GC420t", "Ver 1.0","TEST","S01");
					print.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param item_code 
	 * @param barcode 
	 * @param printer 
	 * @param desc 
	 */
	public print(String company, String barcode, String item_code, String printer,String version,String pagetype,String storename) throws JSONException {
		initialize(company,barcode, item_code,printer, version,pagetype,storename);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @param pagetype 
	 * @param desc 
	 * @throws JSONException 
	 */
	private void initialize(final String company, final String barcode,final String item_code,final String printer,String version, final String pagetype,String storename) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 625, 440);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 607, 551);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		Image Barcode = new ImageIcon(this.getClass().getResource("/Barcode.png")).getImage();
		JLabel lblBarcodeFormat = new JLabel("Print Barcode");
		lblBarcodeFormat.setIcon(new ImageIcon(Barcode));
		lblBarcodeFormat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBarcodeFormat.setBounds(10, 11, 228, 44);
		panel.add(lblBarcodeFormat);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				if(pagetype=="item")
				{
					inventory_master.Item_master.frame.setAlwaysOnTop(true);
					inventory_master.Item_master.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					inventory_master.Item_master.menuItem.setEnabled(true);
				}
				if(pagetype=="PO")
				{
					purchase_master.purchase_order.frame.setAlwaysOnTop(true);
					purchase_master.purchase_order.menuItem.setEnabled(true);
					purchase_master.purchase_order.textField_4.requestFocus();
				}
			}
		});
		menuItem.setBounds(541, 11, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		get_barcode_price(company,barcode);
		getdesc(company,item_code);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 48, 601, 337);
		panel_1.setBackground(new Color(243, 229, 216));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBarcodeFormat_1 = new JLabel("Label Type");
		lblBarcodeFormat_1.setBounds(92, 174, 101, 22);
		panel_1.add(lblBarcodeFormat_1);
		
		comboBox= new JComboBox<String>();
		comboBox.setBounds(218, 174, 192, 22);
		panel_1.add(comboBox);
		
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_barcode group by port order by port"; 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String id=resultSet.getString("port");
	            comboBox.addItem(id);
			}
			con.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog( null, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
		
		JLabel lblQuantity = new JLabel("Label Quantity");
		lblQuantity.setBounds(92, 217, 101, 22);
		panel_1.add(lblQuantity);
		
		textField = new JTextField();
		textField.setBounds(218, 217, 116, 22);
		panel_1.add(textField);
		textField.setColumns(10);
		textField.requestFocus();
		JLabel lblItemCode = new JLabel("Item code");
		lblItemCode.setBounds(92, 21, 72, 22);
		panel_1.add(lblItemCode);
		
		JLabel lblBarcode = new JLabel("Barcode");
		lblBarcode.setBounds(92, 64, 56, 22);
		panel_1.add(lblBarcode);
		
		JLabel lblNewLabel = new JLabel(item_code);
		lblNewLabel.setBounds(218, 21, 192, 22);
		panel_1.add(lblNewLabel);
		
		JLabel label = new JLabel(barcode);
		label.setBounds(218, 64, 192, 22);
		panel_1.add(label);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 if (e.getKeyCode()==KeyEvent.VK_ENTER){
					if(textField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Enter the Quantity");
						textField.requestFocus();
					}
					else
					{
						try {
							print_items(company, barcode, item_name,comboBox.getSelectedItem().toString(),"",textField.getText(), printer,pagetype);
						} catch (PrintException | IOException | PrinterException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				 }
			}
		});
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Enter the Quantity");
					textField.requestFocus();
				}
				else
				{
					try {
						print_items(company, barcode, item_name,comboBox.getSelectedItem().toString(),"",textField.getText(), printer,pagetype);
					} catch (PrintException | IOException | PrinterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		btnPrint.setBounds(92, 267, 172, 57);
		panel_1.add(btnPrint);
		
		JLabel lblNewLabel_1 = new JLabel("Item Name");
		lblNewLabel_1.setBounds(92, 107, 72, 22);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(item_name);
		lblNewLabel_2.setBounds(218, 107, 360, 22);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblPcsbox = new JLabel("PCS/Box");
		lblPcsbox.setBounds(92, 139, 101, 22);
		panel_1.add(lblPcsbox);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setColumns(10);
		textField_1.setBounds(218, 139, 116, 22);
		panel_1.add(textField_1);
		
		get_printer_name(company);
		
		onload(company, storename);
	}
	public String get_printer_name(String company){
		String printer = null;
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_barcode where status='1' ");
			if(resultSet.next())
			{
				byte[] printers=Base64.decode(resultSet.getString("printer").toString()); //resultSet.getString("printer");
				printer = new String(printers, StandardCharsets.UTF_8);
				comboBox.setSelectedItem(resultSet.getString("barcode_desc"));
			}
			
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog( null, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
		
		return printer;
	}
	public static void print_items(String company, String barcode, String item_code, String format,String type, String qty, String printer, String pagetype) throws PrintException, IOException, PrinterException{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String commands;
			if(printer.equals(""))
			{
				
				ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_barcode where  port='"+format+"' ");
				if(resultSet1.next())
				{
					byte[] printers=Base64.decode(resultSet1.getString("printer").toString()); //resultSet.getString("printer");
					printer = new String(printers, StandardCharsets.UTF_8);
				}
			}
			String barcodedesc=""; 
			String querybarcode="";
			
			if(barcode.length()>12)
			{
				if(price < 10)
				{
					barcodedesc=printer+"EAN10";
				}
				else
				{
					if(price < 100)
					{
						barcodedesc=printer+"EAN100";
					}
					else if(price < 1000)
					{
						barcodedesc=printer+"EAN1000";
					}
					else
					{
					
						barcodedesc=printer+"EAN10000";
					}
				}
				querybarcode="select * from "+company+".mr_barcode where barcode_desc='"+barcodedesc+"' and port='"+format+"'  ";
			}
			else
			{
				if(price < 10)
				{
					barcodedesc=printer+"10";
				}
				else
				{
					if(price < 100)
					{
						barcodedesc=printer+"100";
					}
					else if(price < 1000)
					{
						barcodedesc=printer+"1000";
					}
					else
					{
						barcodedesc=printer+"10000";
					}
				}
				querybarcode="select * from "+company+".mr_barcode where barcode_desc='"+barcodedesc+"' and port='"+format+"' ";
			}
			ResultSet resultSet = statement.executeQuery(querybarcode);
			if(resultSet.next())
			{
				String price_tag=df2.format(price);
				String[] amount=price_tag.split("[.]");
				
				double  ttlqty=Double.parseDouble(cf)*Double.parseDouble(textField_1.getText());
				
				commands =resultSet.getString("code");
				commands= commands.replace("itm_code",item_name);
				commands= commands.replace("itm_desc1",item_desc1);
				commands= commands.replace("itm_desc2",item_desc2);
				commands= commands.replace("bar_code",barcode);
				commands= commands.replace("cf",cf);
				commands= commands.replace("ttlitemqty",Double.toString(ttlqty));
				commands= commands.replace("itemqty",textField_1.getText());
				
				commands= commands.replace("qu_ty",qty);
				commands= commands.replace("am_tl",amount[0]);
				commands= commands.replace("decimal",amount[1]);
				
				final File parentDir = new File("C:/MasterRetails");
				final String fileName = "print.txt";
				final File file = new File(parentDir, fileName);
				file.createNewFile();
				File log=new File("C:/MasterRetails/print.txt");
				PrintWriter writer = new PrintWriter(log, "UTF-8");
				writer.println(commands);
				writer.close();
				
				for (PrintService printService : PrinterJob.lookupPrintServices()) {
					if (printer.equals(printService.getName())) {
						DocPrintJob job = printService.createPrintJob();  
						DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
						Doc doc = new SimpleDoc(commands.getBytes(), flavor, null);
						job.print(doc, null);
			            break;
					}
				}
			}
			con.close();
			if(pagetype=="item")
			{
				inventory_master.Item_master.frame.setAlwaysOnTop(true);
				inventory_master.Item_master.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				inventory_master.Item_master.menuItem.setEnabled(true);
			}
			if(pagetype=="PO")
			{
				purchase_master.purchase_order.textField_4.requestFocus();
			}
			
			frame.setVisible(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog( null, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}
//	public static void print_default(String company, String barcode, String item_code, String format,String type, String qty, String printer) throws PrintException, IOException, PrinterException{
//		try {
//			get_barcode_price(company,barcode);
//			Connection con=Dbsetup.dbconn.getConnection();
//			Statement statement=con.createStatement();  
//			String commands;
//			if(printer.equals(""))
//			{
//				ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_barcode where status='1' ");
//				if(resultSet1.next())
//				{
//					byte[] printers=Base64.decode(resultSet1.getString("printer").toString()); //resultSet.getString("printer");
//					printer = new String(printers, StandardCharsets.UTF_8);
//				}
//			}
//			if(price < 10)
//			{
//				format=printer+"10";
//			}
//			else
//			{
//				if(price < 100)
//				{
//					format=printer+"100";
//				}
//				else
//				{
//					format=printer+"1000";
//				}
//			}
//			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_barcode where status='1' ");
//			if(resultSet.next())
//			{
//				String[] amount=df2.format(price).split(".");
//				commands =resultSet.getString("code");
//				commands= commands.replace("itm_code",item_name);
//				commands= commands.replace("bar_code",barcode);
//				commands= commands.replace("qu_ty",qty);
//				commands= commands.replace("am_tl",amount[0]);
//				commands= commands.replace("decimal",amount[1]);
//				
//				for (PrintService printService : PrinterJob.lookupPrintServices()) {
//					if (printer.equals(printService.getName())) {
//						DocPrintJob job = printService.createPrintJob();  
//						DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
//						Doc doc = new SimpleDoc(commands.getBytes(), flavor, null);
//						job.print(doc, null);
//			            break;
//					}
//				}
//			}
//			con.close();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog( null, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
//		}
//	}
	public static void get_barcode_price(String company,String barcode){
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_tmp_item_master  where barcode='"+barcode+"' ";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				cf =resultSet.getString("cf");//(Double.parseDouble(),decimalPlaces);
				price =Double.parseDouble(resultSet.getString("sp"));//(Double.parseDouble(),decimalPlaces);
				item_name=resultSet.getString("item_name");
			}
			con.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog( null, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}
	private void getdesc(String company, String item_code)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_item_master  where item_code='"+item_code+"' ";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				item_desc1=resultSet.getString("description1");
				item_desc2=resultSet.getString("description2");
			}
			con.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog( null, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}
	private void onload(String company, String store_name)
	{
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store where store_id='"+store_name+"' ");
			if(resultSet.next())
			{
				decimalPlaces=Integer.parseInt(resultSet.getString("decimal_count"));
				if(decimalPlaces==2)
				{
					df2=new DecimalFormat("0.00");
				}
				else
				{
					df2=new DecimalFormat("0.000");
				}
			}
		}
		catch(Exception e){
			System.err.println(e);
			JOptionPane.showMessageDialog(frame, e);
		}
	}
}