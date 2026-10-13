package inventory_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONException;

import sales_master.posscreen;

public class Scale_items {

	private JFrame frame;
	private JTable table;
	static DecimalFormat df2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scale_items window = new Scale_items("mr_pro",  null,"");
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
	public Scale_items(String company,String version,String store_name) throws JSONException, IOException {
		initialize(company, version, store_name);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws JSONException
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	private void initialize(final String company,String version, String store_name) throws JSONException, IOException{
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 782, 508);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 740, 435);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 55, 740, 380);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel.add(scrollPane);

		DefaultTableModel model = new DefaultTableModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		table = new JTable(model);
		model.addColumn("Sl no");
		model.addColumn("Item Code");
		model.addColumn("Item Name");
		model.addColumn("Selling Price");
		model.addColumn("Deli/weight");
		model.addColumn("Expiry Days");
		model.addColumn("Name2");
		model.addColumn("WithoutVat");
		model.addColumn("Vat");
		scrollPane.setViewportView(table);
		
		try {
			Properties prop=new Properties();
			FileInputStream ip= new FileInputStream("C:/MasterRetails/config.properties");
			prop.load(ip);
			String weighting_sts= prop.getProperty("weighting_sts");
			String scale_extension= prop.getProperty("scale_extension");
			String encoding= prop.getProperty("encoding");
			ip.close();
			Connection con=Dbsetup.dbconn.getConnection();
			get_store_validate_dt(company,store_name,con);
			Statement statement=con.createStatement();  
			String query="select * from "+company+".mr_item_master where deliItemsweighing='1' ";
			ResultSet resultSet1 = statement.executeQuery(query);
			if(resultSet1.next())
			{
				String[] wgt_sts=weighting_sts.split(",");
				ResultSet resultSet = statement.executeQuery("select item_code,item_code,item_name,weighing_sts,sp1,exp_days,item_name,output_tax_rate,sp_include_tax from "+company+".mr_item_master where deliItemsweighing='1' ");
				//int id =0;
				
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet("Excel Sheet");
				HSSFRow rowhead = sheet.createRow((short)0);
				rowhead.createCell((short) 0).setCellValue("PLU");
				rowhead.createCell((short) 1).setCellValue("Itemcode");
				rowhead.createCell((short) 2).setCellValue("Name");
				rowhead.createCell((short) 3).setCellValue("Price");
				rowhead.createCell((short) 4).setCellValue("Weighing");
				rowhead.createCell((short) 5).setCellValue("Expiry Days");
				rowhead.createCell((short) 6).setCellValue("Name2");
				rowhead.createCell((short) 7).setCellValue("WithoutVat");
				rowhead.createCell((short) 8).setCellValue("Vat");
				int index=1;
				while(resultSet.next())
				{
					String name[] = resultSet.getString("item_name").split("-");
					String name1=name[0];
					String name2="";
					if(name.length>1 && name[1]!=null)
					{
						name2=name[1];
					}
					
					
					String id = resultSet.getString("item_code");
					String code = resultSet.getString("item_code");
					
					String stats = resultSet.getString("weighing_sts");
					if(weighting_sts.equals("0"))
					{
						if(stats.equals("Y"))
						{
							stats="0";
						}
						else
						{
							stats="1";
						}
					}
					
					if(stats.equals("Y"))
					{
						stats=wgt_sts[0];
					}
					else
					{
						stats=wgt_sts[1];
					}
					
					String sp = resultSet.getString("sp1");
					String exp_day = resultSet.getString("exp_days");
					//String desc = resultSet.getString("description2");
					double vat=0;
					String vatamt="0";
					String withoutvatamt="0";
					double withoutvat=0;
					if(resultSet.getString("output_tax_rate").equals("0"))
					{
						if(resultSet.getString("sp_include_tax").equals("0"))
						{
							vat=0;
							withoutvat=Double.parseDouble(resultSet.getString("sp1"));
							vatamt="0";
							withoutvatamt=Double.toString(withoutvat);
						}
						else
						{
							vat=0;
							withoutvat=Double.parseDouble(resultSet.getString("sp1"));
							vatamt="0";
							withoutvatamt=Double.toString(withoutvat);
						}
					}
					else
					{
						if(resultSet.getString("sp_include_tax").equals("0"))
						{
							double withvat=Double.parseDouble(resultSet.getString("sp1"));
							vat=withvat*Double.parseDouble(resultSet.getString("output_tax_rate"))/100;
							vatamt=df2.format(vat);
							withoutvat=Double.parseDouble(resultSet.getString("sp1"));
							withoutvatamt=df2.format(withoutvat);
							
						}
						else
						{
							double withvat=Double.parseDouble(resultSet.getString("sp1"));
							vat=withvat*Double.parseDouble(resultSet.getString("output_tax_rate"))/105;
							vatamt=df2.format(vat);
							withoutvat=Double.parseDouble(resultSet.getString("sp1"))-Double.parseDouble(vatamt);
							withoutvatamt=df2.format(withoutvat);
						}
					}
					
					
					model.addRow(new Object[] { id, code, name1,sp, stats,exp_day,name2,withoutvatamt,vatamt});
					table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
//					FileWriter excel = new FileWriter("C:\\MasterRetails\\scale.csv");
//					for(int x=0; x< model.getRowCount(); x++) {
//			            for(int y=0; y < model.getColumnCount(); y++) {
//			                excel.write(model.getValueAt(x,y).toString());
//			                if(y < model.getColumnCount()-1)
//			                {
//			                	excel.write(",");
//			                }
//			                else
//			                {
//			                	excel.write("\r\n");	
//			                }
//			                
//			            }
//			        }
//					excel.close();
					
					HSSFRow row = sheet.createRow((short)index);
					row.createCell((short) 0).setCellValue(resultSet.getString(1));
					row.createCell((short) 1).setCellValue(resultSet.getString(2));
					row.createCell((short) 2).setCellValue(resultSet.getString(3));
					row.createCell((short) 3).setCellValue(resultSet.getString(5));
					row.createCell((short) 4).setCellValue(resultSet.getString(4));
					row.createCell((short) 5).setCellValue(resultSet.getString(6));
					row.createCell((short) 6).setCellValue(name2);
					row.createCell((short) 7).setCellValue(withoutvatamt);
					row.createCell((short) 8).setCellValue(vatamt);
					index++;
					
					if(scale_extension.equals(""))
					{
						scale_extension="csv";
					}
					Writer excel;
					if(encoding.equals("ANSI"))
					{
						Charset ansiCharset = Charset.forName("windows-1256");
						excel =new OutputStreamWriter(new FileOutputStream("C:\\MasterRetails\\scale."+scale_extension),ansiCharset);
					}
					else
					{
						excel =new OutputStreamWriter(new FileOutputStream("C:\\MasterRetails\\scale."+scale_extension),StandardCharsets.UTF_8);
					}
					
					
					
					
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
					
				}
				FileOutputStream fileOut = new FileOutputStream("c:\\MasterRetails\\scale.xls");
				wb.write(fileOut);
				fileOut.close();
				JOptionPane.showMessageDialog(frame, "<html>Export Completed<br>«ﬂ „· «· ’œÌ—</html>");
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "<html>No Items Found to export<br>·„ Ì „ «·⁄ÀÊ— ⁄·Ï √Ì ⁄‰«’— ·· ’œÌ—</html>");
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	public static void get_store_validate_dt(String company,String store_name,Connection con)
	{
		try {
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store where store_id='"+store_name+"' ");
			if(resultSet.next())
			{
				
				int decimalPlaces=Integer.parseInt(resultSet.getString("decimal_count"));
				if(decimalPlaces==2)
				{
					df2=new DecimalFormat("0.00");
				}
				else
				{
					df2=new DecimalFormat("0.000");
				}
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
