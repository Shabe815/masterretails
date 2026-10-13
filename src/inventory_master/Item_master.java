package inventory_master;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import org.json.JSONException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import com.toedter.calendar.JDateChooser;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Item_master {

	public static JFrame frame;
	private JPanel panel_5;
	private JPanel panel_6;
	static JTextField txtItemcode;
	private static JTextField txtshrt_nme;
	private JTable vtable;
	private static JTextField textField_13;
	private static JTextField textField;
	private static JTextField textField_3;
	private static JTextField textField_6;
	private static JTextField txtHidenimagepath;
	private JButton lblUploadImage;
	private static JComboBox<String> comboBox;
	private static JComboBox<String> comboBox_1;
	public static JComboBox<String> comboBox_2;
	public static JComboBox<String> comboBox_3;
	public static JComboBox<String> comboBox_4;
	public static JComboBox<String> comboBox_5;
	private static JComboBox<String> comboBox_6;
	private static JComboBox<String> comboBox_7;
	private static JComboBox<String> comboBox_8;
	private static JComboBox<String> comboBox_9;
	private static JComboBox<String> comboBox_10;
	
	public static JMenuItem menuItem;
	private static JMenuItem menuItem_1;
	private static JMenuItem menuItem_2;
	
	private static JTextField txt_desc1;
	private static JTextField textArea_1;
	private static JTextArea item_dtls;
	private static JLabel lblitemimage;
	public static ArrayList<String> listdept;
	public static ArrayList<String> listcat;
	public static ArrayList<String> listsubdept = new ArrayList<String>();
	public static ArrayList<String> listsubcat = new ArrayList<String>();
	//public static ArrayList<String> listname;
	private static DefaultTableModel model;
	private static JLabel label_2;
	private static JTextField textField_7;
	private static JDateChooser dateChooser_1;
	private static JDateChooser dateChooser;
	private static JTable table;
	private static JTextField textField_8;
	private static JCheckBox chckbxSalesPriceInclusive;
	private static JCheckBox chckbxpoPriceInclusive;
	private static JCheckBox chckbxAllowDiscount;
	private static JCheckBox chckbxActiveForSale;
	private static JCheckBox chckbxActiveForPurchase;
	private static JCheckBox chckbxOn;
	private static JCheckBox chckbxDoNot;
	private JTextField textField_10;
	private static DefaultTableModel modellist;
	private static JLabel label_3;
	private static JLabel label_4;
	private static JCheckBox chckbxParentKit;
	private static JCheckBox chckbxDeliItemsweighing;
	private static JCheckBox chckbxOpeningQty;
	private static JCheckBox chckbxOpenQty;
	
	private boolean dot=false;
	private static JTextField textField_9;
	private static JTextField textField_11;
	private static JTextField textField_1;
	private static JComboBox <String> comboBox_11;
	private static JTextField textField_2;
	private String web_config="0";
	
	private String create_item="0";
	private String edit_sp="0";
	private String edit_cost="0";
	private static String view_cost="0";
	private String view_hist="0";
	private String edit_qty="0";
	private static JButton btnNewButton_2;
	
	private int CodeAllowChar=0;
	
	public static String itemlist_filter="Item Code";
	public static JTextField textField_4;
	public static JTextField textField_5;
	
	public static JCheckBox chckbxOpenItem;
	public static JCheckBox chckbxFastItem;
	private JLabel label_5;
	private static JButton button_3;
	private static JButton button_5;
	private JLabel label_6;
	
	private static String sel_color="#000000";
	private static String fore_sel_color="#ffffff";
	
	private static JCheckBox chckbxUnitUsedAs;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					Item_master window = new Item_master( "MASTER RETAILS","mr_pro", "","admin","","","", null,"S01",0,"");
					Item_master.frame.setVisible(true);
				} catch (Exception e) {
					frame.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
					frame.setAlwaysOnTop(true);
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param host_name 
	 * @param username 
	 * @param server 
	 * @param company 
	 * @param barcode 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	public Item_master(String db_name,String company,String item_code,String username,String vendor_id,String pagetype, String barcode,String version,String store,int decimal,String language) throws JSONException, SQLException {
		initialize(db_name,company,item_code,username,vendor_id,pagetype,barcode, version,store,decimal,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @param item_code 
	 * @param vendor_id 
	 * @param language 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String db_name,final String company,  final String item_code,final String username, final String vendor_id,final String pagetype,String barcode_num,final String version,final String store,final int decimal, final String language) throws JSONException, SQLException {
		frame = new JFrame("[DATABASE SERVER="+company+"] [COMPANY "+db_name+"] [CURRENT USER = "+username+"]");
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		//frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    	CafePro.Mainpage.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	CafePro.Mainpage.btnNewButton.setEnabled(true);
		    	CafePro.Mainpage.btnNewButton_1.setEnabled(true);
		    }
		});
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(0, 0, 1024, 768);
		frame.setLocationRelativeTo(null);
		
		
		try {
			Properties prop=new Properties();
			FileInputStream ip= new FileInputStream("C:/MasterRetails/config.properties");
			prop.load(ip);
			web_config= prop.getProperty("web_config");
			ip.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
		
		get_store_dt(company);
		
		final String Printer=get_printer_name(company,username);
		Image printer=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 63, 1018, 148);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		panel_5 = new JPanel();
		panel_5.setBackground(new Color(243, 229, 216));
		panel_5.setBounds(0, 0, 401, 149);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblItemCode = new JLabel("1. Item Code");
		lblItemCode.setFont(new Font("Tahoma", Font.PLAIN, 13));
		if(language.equals("Arabic"))
		{
			lblItemCode.setText("1.—„“ «·⁄‰’—");
		}
		lblItemCode.setBounds(10, 5, 93, 16);
		panel_5.add(lblItemCode);
		
		txtItemcode = new JTextField(item_code);
		txtItemcode.setBounds(103, 5, 256, 16);
		panel_5.add(txtItemcode);
		txtItemcode.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtItemcode.setFocusTraversalKeysEnabled(false);
		txtItemcode.addKeyListener(new KeyAdapter() {
			@Override
				public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_TAB) {
					if(txtItemcode.getText().equals(""))
		    		{
		    			txtItemcode.requestFocus();
		    		}
		    		else
		    		{
		    			txtshrt_nme.requestFocus();
		    		}
		    	}
			}
//			@Override
//			public void keyTyped(KeyEvent e) {
//				char vChar = e.getKeyChar();
//				
//				if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE)))
//		        {
//		                e.consume();
//		        }
//				
//				int max = 15;
//				if(txtItemcode.getText().length() > max) {
//			        e.consume();
//			    }
//			}
			
			@Override
			public void keyTyped(KeyEvent e) {
			    char vChar = e.getKeyChar();
			    int max = 13;

			    if (txtItemcode.getText().length() >= max) {
			        e.consume();
			        return;
			    }

			    if (CodeAllowChar == 1) {

			        if (!(Character.isLetterOrDigit(vChar)
			                || vChar == KeyEvent.VK_BACK_SPACE
			                || vChar == KeyEvent.VK_DELETE)) {
			            e.consume();
			        }

			    } 
			    else {

			        if (!(Character.isDigit(vChar)
			                || vChar == KeyEvent.VK_BACK_SPACE
			                || vChar == KeyEvent.VK_DELETE)) {
			            e.consume();
			        }

			    }
			}

			
		});
			
		txtItemcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(txtItemcode.getText().equals("")){
		    			txtItemcode.setText("NEW");
		    			txtshrt_nme.requestFocus();
		    		}
					else
					{
						get_itemdtls_bsd_code(company, txtItemcode.getText(),decimal );
						comboBox.removeAllItems();
						comboBox.hidePopup();
						panel_5.remove(comboBox);
					}
				} catch (Exception e) {
				}
			}

			
		});
		txtItemcode.setColumns(10);
		
		
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(103, 5, 256, 16);
		panel_5.add(comboBox);
		comboBox.setFocusCycleRoot(true);
		comboBox.setFocusTraversalPolicyProvider(true);
		comboBox.setAutoscrolls(true);
		comboBox.setBorder(null);
		comboBox.setOpaque(false);
		
		JLabel lblShortName = new JLabel("2. Short Name");
		lblShortName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblShortName.setBounds(10, 26, 93, 16);
		if(language.equals("Arabic"))
		{
			lblShortName.setText("2.«·«”„ «·„Œ ’—");
		}
		panel_5.add(lblShortName);
		
		txtshrt_nme = new JTextField();
		txtshrt_nme.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					txtshrt_nme.setText(txtshrt_nme.getText().toUpperCase());
					String shrt_nme=txtshrt_nme.getText();
					if(!shrt_nme.equals("")){
						txt_desc1.setText(shrt_nme);
						//textArea_1.setText(shrt_nme);
						//item_dtls.setText(shrt_nme);
						comboBox_2.requestFocus();
					}
		        }
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				
				if ((vChar == KeyEvent.VK_COMMA))
		        {
		                e.consume();
		        }
				
			}
		});
			txtshrt_nme.setColumns(10);
			txtshrt_nme.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int max = 50;
				if(txtshrt_nme.getText().length() > max+1) {
			        e.consume();
			        String shortened = txtshrt_nme.getText().substring(0, max);
			        txtshrt_nme.setText(shortened);
			    }else if(txtshrt_nme.getText().length() > max) {
			        e.consume();
			    }
			}
		});
		txtshrt_nme.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtshrt_nme.setColumns(10);
		txtshrt_nme.setBounds(103, 26, 286, 16);
		panel_5.add(txtshrt_nme);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(103, 26, 286, 16);
		panel_5.add(comboBox_1);
		comboBox_1.setFocusCycleRoot(true);
		comboBox_1.setFocusTraversalPolicyProvider(true);
		comboBox_1.setAutoscrolls(true);
		comboBox_1.setBorder(null);
		comboBox_1.setOpaque(false);
		
		if(pagetype=="PO")
		{
			txtItemcode.setText("NEW");
			txtshrt_nme.requestFocus();
			//listdept.clear();
		}
		
		JLabel lblDescription = new JLabel("3. Description 1");
		if(language.equals("Arabic"))
		{
			lblDescription.setText("3. «·Ê’› 1");
		}
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescription.setBounds(10, 47, 93, 16);
		panel_5.add(lblDescription);
		
		txt_desc1 = new JTextField();
		txt_desc1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int max = 50;
				if(txt_desc1.getText().length() > max+1) {
			        e.consume();
			        String shortened = txt_desc1.getText().substring(0, max);
			        txt_desc1.setText(shortened);
			    }else if(txt_desc1.getText().length() > max) {
			        e.consume();
			    }
			}
		});
		txt_desc1.setBounds(103, 47, 294, 16);
		panel_5.add(txt_desc1);
		
		JLabel lblDescription_1 = new JLabel("4. Description 2");
		if(language.equals("Arabic"))
		{
			lblDescription_1.setText("4. «·Ê’› 2");
		}
		lblDescription_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescription_1.setBounds(10, 68, 93, 16);
		panel_5.add(lblDescription_1);
		
		textArea_1 = new JTextField();
		textArea_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int max = 50;
				if(textArea_1.getText().length() > max+1) {
			        e.consume();
			        String shortened = textArea_1.getText().substring(0, max);
			        textArea_1.setText(shortened);
			    }else if(textArea_1.getText().length() > max) {
			        e.consume();
			    }
			}
		});
		textArea_1.setBounds(103, 68, 294, 16);
		panel_5.add(textArea_1);
		
		JLabel lblItemDetails = new JLabel("5. Item Details");
		if(language.equals("Arabic"))
		{
			lblItemDetails.setText("5.  ›«’Ì· «·⁄‰’—");
		}
		lblItemDetails.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItemDetails.setBounds(10, 95, 93, 16);
		panel_5.add(lblItemDetails);
		
		item_dtls = new JTextArea();
		item_dtls.setLineWrap(true);
		item_dtls.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int max = 500;
				if(item_dtls.getText().length() > max+1) {
			        e.consume();
			        String shortened = item_dtls.getText().substring(0, max);
			        item_dtls.setText(shortened);
			    }else if(item_dtls.getText().length() > max) {
			        e.consume();
			    }
			}
		});
		item_dtls.setBounds(1, 1, 379, 208);
		panel_5.add(item_dtls);
		
		JScrollPane sp = new JScrollPane(item_dtls);
		sp.setBounds(103, 89, 294, 59);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_5.add(sp);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Item Code List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					frame.setAlwaysOnTop(false);
					Connection con=Dbsetup.dbconn.getConnection();
					new item_list_master(db_name,company, "Item_master", "", version,decimal,con,"",language);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					menuItem.setEnabled(false);
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(serch));
		btnNewButton.setBounds(362, 3, 35, 21);
		panel_5.add(btnNewButton);
		
		JLabel lblArabic = new JLabel("( Arabic )");
		if(language.equals("Arabic"))
		{
			lblArabic.setText("⁄—»Ì");
		}
		lblArabic.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblArabic.setBounds(57, 80, 46, 14);
		panel_5.add(lblArabic);
		
		panel_6 = new JPanel();
		panel_6.setBackground(new Color(243, 229, 216));
		panel_6.setBounds(401, 0, 372, 149);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		
		JLabel label = new JLabel("6. Department");
		if(language.equals("Arabic"))
		{
			label.setText("6. «·ﬁ”„");
		}
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(10, 5, 115, 16);
		panel_6.add(label);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					comboBox_3.requestFocus();
		            }
			}
		});
		
		
		comboBox_2.setBounds(130, 5, 169, 16);
		panel_6.add(comboBox_2);
		comboBox_2.setFocusCycleRoot(true);
		comboBox_2.setFocusTraversalPolicyProvider(true);
		comboBox_2.setAutoscrolls(true);
		comboBox_2.setBorder(null);
		comboBox_2.setOpaque(false);
		//comboBox_2.setSelectedIndex(-1);
		get_item_dept(company);
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent item) {
				if(comboBox_2.getSelectedIndex()!=-1)
				{
					if(item.getStateChange()==ItemEvent.SELECTED)
					{
						get_item_subdept(company, comboBox_2.getSelectedItem().toString());
						comboBox_4.setSelectedItem("NIL");
					}
				}
			}
		});
		comboBox_2.setSelectedIndex(-1);
		
		
		JButton deptbutton = new JButton("New");
		if(language.equals("Arabic"))
		{
			deptbutton.setText("ÃœÌœ");
		}
		deptbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					frame.setAlwaysOnTop(false);
					new system_setups.department(company, "item", version);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					menuItem.setEnabled(false);
				} catch (JSONException | SQLException e) {
//					frame.setAlwaysOnTop(false);
//					JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
//					frame.setAlwaysOnTop(true);
				}
				
			}
		});
		deptbutton.setFont(new Font("Tahoma", Font.BOLD, 10));
		deptbutton.setBounds(311, 5, 57, 16);
		panel_6.add(deptbutton);
		
		JLabel label_1 = new JLabel("7. Sub-Department");
		if(language.equals("Arabic"))
		{
			label_1.setText("7. «·ﬁ”„ «·›—⁄Ì");
		}
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(10, 26, 115, 16);
		panel_6.add(label_1);
		
		comboBox_3 = new JComboBox<String>();
		comboBox_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					comboBox_4.requestFocus();
		            }
			}
		});
		comboBox_3.setBounds(130, 26, 169, 16);
		panel_6.add(comboBox_3);
		comboBox_3.setFocusCycleRoot(true);
		comboBox_3.setFocusTraversalPolicyProvider(true);
		comboBox_3.setAutoscrolls(true);
		comboBox_3.setBorder(null);
		comboBox_3.setOpaque(false);
		if(comboBox_2.getSelectedIndex()!=-1)
		{
			String dept=comboBox_2.getSelectedItem().toString();
			get_item_subdept(company, dept);
		}
		
		
		JButton button_4 = new JButton("New");
		if(language.equals("Arabic"))
		{
			button_4.setText("ÃœÌœ");
		}
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setAlwaysOnTop(false);
					new system_setups.subdepartment(company, "item","", version);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					menuItem.setEnabled(false);
					
				} catch (JSONException | SQLException e) {
//					frame.setAlwaysOnTop(false);
//					JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
//					frame.setAlwaysOnTop(true);
				}
			}
		});
		button_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_4.setBounds(311, 26, 57, 16);
		panel_6.add(button_4);
		
		
		
		JLabel lblCategory = new JLabel("8. Category");
		if(language.equals("Arabic"))
		{
			lblCategory.setText("8. «·›∆…");
		}
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategory.setBounds(10, 47, 84, 16);
		panel_6.add(lblCategory);
		
		comboBox_4 = new JComboBox<String>();
		comboBox_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					comboBox_5.requestFocus();
		            }
			}
		});
		
		
		comboBox_4.setBounds(130, 47, 169, 16);
		panel_6.add(comboBox_4);
		comboBox_4.setFocusCycleRoot(true);
		comboBox_4.setFocusTraversalPolicyProvider(true);
		comboBox_4.setAutoscrolls(true);
		comboBox_4.setBorder(null);
		comboBox_4.setOpaque(false);
		get_item_cat(company);
		comboBox_4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent item) {
				if(item.getStateChange()==ItemEvent.SELECTED)
				{
					get_item_subcat( company,  comboBox_4.getSelectedItem().toString());
				}
			}
		});
		comboBox_4.setSelectedIndex(-1);
		
		JLabel lblSubcategory = new JLabel("9. Sub-Category");
		if(language.equals("Arabic"))
		{
			lblSubcategory.setText("9. «·›∆… «·›—⁄Ì…");
		}
		lblSubcategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSubcategory.setBounds(10, 68, 102, 16);
		panel_6.add(lblSubcategory);
		
		comboBox_5 = new JComboBox<String>();
		comboBox_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					comboBox_6.requestFocus();
		            }
			}
		});
		comboBox_5.setBounds(130, 68, 169, 16);
		panel_6.add(comboBox_5);
		comboBox_5.setFocusCycleRoot(true);
		comboBox_5.setFocusTraversalPolicyProvider(true);
		comboBox_5.setAutoscrolls(true);
		comboBox_5.setBorder(null);
		comboBox_5.setOpaque(false);
		if(comboBox_4.getSelectedIndex()!=-1)
		{
			get_item_subcat( company,  comboBox_4.getSelectedItem().toString());
		}
		
		
		JButton button = new JButton("New");
		if(language.equals("Arabic"))
		{
			button.setText("ÃœÌœ");
		}
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setAlwaysOnTop(false);
					new system_setups.sub_category(company,"item","", version);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					menuItem.setEnabled(false);
					
				} catch (JSONException | SQLException e) {
//					frame.setAlwaysOnTop(false);
//					JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
//					frame.setAlwaysOnTop(true);
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 10));
		button.setBounds(311, 68, 57, 16);
		panel_6.add(button);
		
		JButton button_1 = new JButton("New");
		if(language.equals("Arabic"))
		{
			button_1.setText("ÃœÌœ");
		}
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.setAlwaysOnTop(false);
					new system_setups.category(company, "item", version);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					menuItem.setEnabled(false);
					
				} catch (JSONException | SQLException e) {
//					frame.setAlwaysOnTop(false);
//					JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
//					frame.setAlwaysOnTop(true);
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_1.setBounds(311, 47, 57, 16);
		panel_6.add(button_1);
		
		JLabel lblType = new JLabel("10. Type");
		if(language.equals("Arabic"))
		{
			lblType.setText("10. «·‰Ê⁄");
		}
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblType.setBounds(10, 89, 56, 16);
		panel_6.add(lblType);
		
		comboBox_6= new JComboBox<String>();
		comboBox_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
	               //lblUploadImage.requestFocus();
					//table.requestFocus();
					//table.changeSelection(0, 2, false, false);
					textField.requestFocus();
	            }
			}
		});
		comboBox_6.setBounds(130, 89, 159, 16);
		comboBox_6.addItem("Inventory");
		comboBox_6.addItem("Non Inventory");
		//comboBox_6.addItem("Open Item");
		//comboBox_6.addItem("Fast Items");
		
		panel_6.add(comboBox_6);
		comboBox_6.setFocusTraversalKeysEnabled(false);
		
		JLabel lblItemCost = new JLabel("11. Item Cost");
		if(language.equals("Arabic"))
		{
			lblItemCost.setText("11. ﬂ·›… «·⁄‰’—");
			lblItemCost.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		else
		{
			lblItemCost.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		
		lblItemCost.setBounds(10, 110, 84, 16);
		panel_6.add(lblItemCost);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					textField_8.requestFocus();
		        }
			}
		});
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				textField_8.requestFocus();
			}
		});
		textField.setBounds(116, 110, 77, 16);
		panel_6.add(textField);
		textField.setColumns(10);
		
		JLabel lblLastCost = new JLabel("12. Last Cost");
		if(language.equals("Arabic"))
		{
			lblLastCost.setText("12.«· ﬂ·›… «·√ŒÌ—…");
			lblLastCost.setFont(new Font("Tahoma", Font.PLAIN, 11));
		}
		else
		{
			lblLastCost.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		
		lblLastCost.setBounds(195, 110, 84, 16);
		panel_6.add(lblLastCost);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(291, 110, 77, 16);
		panel_6.add(textField_3);
		
		JLabel lblStandardCost = new JLabel("13. Standard Cost");
		if(language.equals("Arabic"))
		{
			lblStandardCost.setText("13.«· ﬂ·›… «·ﬁÌ«”Ì…");
		}
		lblStandardCost.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStandardCost.setBounds(10, 131, 115, 16);
		panel_6.add(lblStandardCost);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(116, 131, 77, 16);
		panel_6.add(textField_6);
		
		JLabel lblTotalQty = new JLabel("14. Qty on Hand");
		if(language.equals("Arabic"))
		{
			lblTotalQty.setText("14. «·ﬂ„Ì… «·„ Ê›—…");
		}
		lblTotalQty.setForeground(Color.BLUE);
		lblTotalQty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setAlwaysOnTop(false);
				Connection con=Dbsetup.dbconn.getConnection();
				new QtyOHList(db_name,company,con,txtItemcode.getText());
			}
		});
		lblTotalQty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalQty.setBounds(195, 131, 102, 16);
		panel_6.add(lblTotalQty);
		
		textField_8 = new JTextField();
		textField_8.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					table.requestFocus();
					table.changeSelection(0, 2, false, false);
		        }
			}
		});
		textField_8.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				table.requestFocus();
				table.changeSelection(0, 2, false, false);
			}
		});
		//textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(301, 131, 67, 16);
		panel_6.add(textField_8);
		
		
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(243, 229, 216));
		panel_7.setBounds(775, 0, 244, 149);
		panel.add(panel_7);
		panel_7.setLayout(null);
		
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		lblUploadImage = new JButton("Upload Image");
		if(language.equals("Arabic"))
		{
			lblUploadImage.setText(" Õ„Ì· «·’Ê—…");
		}
		lblUploadImage.setFocusTraversalKeysEnabled(false);
		lblUploadImage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					table.requestFocus();
					table.changeSelection(0, 2, false, false);
		        }
			}
		});
		Border emptyBorder = BorderFactory.createEmptyBorder();
		lblUploadImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setAlwaysOnTop(false);
				JFileChooser file = new JFileChooser();
		          file.setCurrentDirectory(new File(System.getProperty("user.home")));
		          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
		          file.addChoosableFileFilter(filter);
		          file.setFileFilter(filter);
		          file.setMultiSelectionEnabled(false);
		          int result = file.showSaveDialog(null);
		          if(result == JFileChooser.APPROVE_OPTION){
		        	  File selectedFile = file.getSelectedFile();
		              String path = selectedFile.getAbsolutePath();
		              lblitemimage.setIcon(ResizeImage(path));
		              txtHidenimagepath.setText(path);
		              textField_10.setText("1");
		              frame.setAlwaysOnTop(true);
		          }
			}
		});
		lblUploadImage.setBackground(new Color(243, 229, 216));
		lblUploadImage.setForeground(Color.BLUE);
		lblUploadImage.setBorder(emptyBorder);
		lblUploadImage.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUploadImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblUploadImage.setBounds(81, 118, 126, 33);
		panel_7.add(lblUploadImage);
		lblitemimage = new JLabel("");
		//Border border = BorderFactory.createLineBorder(Color.BLACK);
		lblitemimage.setBorder(border);
		lblitemimage.setBounds(43, 5, 189, 112);
		panel_7.add(lblitemimage);

		
		txtHidenimagepath = new JTextField();
		txtHidenimagepath.setEditable(false);
		txtHidenimagepath.setVisible(false);
		txtHidenimagepath.setBounds(12, 0, 116, 20);
		panel_7.add(txtHidenimagepath);
		txtHidenimagepath.setColumns(10);
		
		textField_10 = new JTextField("0");
		textField_10.setEnabled(false);
		textField_10.setEditable(false);
		textField_10.setVisible(false);
		textField_10.setBounds(23, 123, 36, 22);
		panel_7.add(textField_10);
		textField_10.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(12, 44, 116, 22);
		panel_7.add(textField_2);
		textField_2.setVisible(false);
		textField_2.setColumns(10);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setBounds(0, 212, 1018, 179);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		scrollPane.setBounds(0, 30, 968, 146);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel_1.add(scrollPane);

		model = new DefaultTableModel() {
		      /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
			       //all cells false
				if (column == 2 && edit_sp.equals("0")) {
					Object value = getValueAt(row, column);
					if (value == "")
					{
						return true;
					}
					else
					{
						return false;
					}
					
		        }
				 return true;
			    }
			public Class<?> getColumnClass(int columnIndex) {
		        Object o = getValueAt(0, columnIndex);
		        if (o == null) {
		          return Object.class;
		        } else {
		          return o.getClass();
		        }
		      }
		    };
		    table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 18));
			table.setRowHeight(20);
		    
			if(language.equals("Arabic"))
			{
				model.addColumn("ÊÕœ…"); 
				model.addColumn(" ÕÊÌ· «·⁄«„·"); 
				model.addColumn("”⁄— «·»Ì⁄"); 
				model.addColumn("«·»«—ﬂÊœ 1"); 
				model.addColumn("«·»«—ﬂÊœ 2"); 
				model.addColumn("«·»«—ﬂÊœ 3"); 
			}
			else
			{
				model.addColumn("Unit"); 
				model.addColumn("Convert Factor"); 
				model.addColumn("Selling Price"); 
				model.addColumn("Barcode 1"); 
				model.addColumn("Barcode 2"); 
				model.addColumn("Barcode 3"); 
			}
			
			
			String cf = "";
			String sellprice="";
			String bc="";
			if(!barcode_num.equals(""))
			{
				bc=barcode_num;
			}
			String bc1="";
			String bc2="";
			String bc3="";
			
			
			String unit1="";
			String unit2="";
			String unit3="";
			String unit4="";
			String unit5="";
			String unit6="";
			
			try (InputStream input = new FileInputStream("C:/MasterRetails/itembarcode_field.properties")) {

	            Properties prop = new Properties();

	            // load a properties file
	            prop.load(input);

	            unit1=prop.getProperty("Each");
	            unit2=prop.getProperty("Box");
	            unit3=prop.getProperty("Pack1");
	            unit4=prop.getProperty("Pack2");
	            unit5=prop.getProperty("Pack3");
	            unit6=prop.getProperty("Pack4");
	            
	            

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
			
			model.addRow(new Object[]{unit1,"1",sellprice,bc, bc2,bc3});
			model.addRow(new Object[]{unit2,cf,sellprice,bc1, bc2,bc3});
			model.addRow(new Object[]{unit3,cf,sellprice,bc1, bc2,bc3});
			model.addRow(new Object[]{unit4,cf,sellprice,bc1, bc2,bc3});
			model.addRow(new Object[]{unit5,cf,sellprice,bc1, bc2,bc3});
			model.addRow(new Object[]{unit6,cf,sellprice,bc1, bc2,bc3});
			
			
			
			Enumeration<?> e = table.getColumnModel().getColumns();
		    TableColumn col = (TableColumn) e.nextElement();
		    col.setCellRenderer(table.getDefaultRenderer(Object.class));
		    col.setCellEditor(table.getDefaultEditor(Object.class));
		    
			table.setCellSelectionEnabled(true);
			
		    table.addKeyListener(new KeyAdapter() {
		    	
		    	@Override
				public void keyTyped(KeyEvent e) {
		    		int column = table.getSelectedColumn();
	            	char vChar = e.getKeyChar();
				   if(column!=0)
				   {
					   dot = false;
				    	if (dot == false){
					        if (vChar == '.') dot = true;
					        else if (!(Character.isDigit(vChar)
					                || (vChar == KeyEvent.VK_BACK_SPACE)
					                || (vChar == KeyEvent.VK_DELETE))) {
					                e.consume();
					        }
				    	}
				    	else 
				    	{
				    		if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE)))
				    		{
				                e.consume();
				    		}
				    	}
				   }
				        
				}
		    	@Override
				public void keyPressed(KeyEvent e) {
		    		int column = table.getSelectedColumn();
	            	int rows = table.getSelectedRow();
	            	if (e.getKeyCode() == 114) {
			    		if(column==3 || column==4 ||column==5)
	                	{
			    			String code=txtItemcode.getText();
		               	    if(code.equals("NEW"))
		               	    {
		               	    	 JOptionPane.showMessageDialog(frame, "<html>Save the item First and Add details on barcode <br> «Õ›Ÿ «·⁄‰’— √Ê·« Ê√÷› «· ›«’Ì· ⁄·Ï «·»«—ﬂÊœ</html>");
		               	    }
		               	    else
		               	    {
		               	    	
		               	    	autogenerate(rows,column);
		               	    }
		               	 if(!table.getValueAt(rows, column).toString().equals(""))
		 					{
			            		Connection con=Dbsetup.dbconn.getConnection();
			        			Statement statement;
								try {
									statement = con.createStatement();
									String query="SELECT item_code,item_name FROM "+company+".mr_item_master where '"+table.getValueAt(rows, column).toString()+"' IN ("+company+".mr_item_master.barcode1,"+company+".mr_item_master.barcode2,"+company+".mr_item_master.barcode3,"+company+".mr_item_master.barcode4,"+company+".mr_item_master.barcode5,"+company+".mr_item_master.barcode6,"+company+".mr_item_master.barcode7,"+company+".mr_item_master.barcode8,"+company+".mr_item_master.barcode9,"+company+".mr_item_master.barcode10,"+company+".mr_item_master.barcode11,"+company+".mr_item_master.barcode12,"+company+".mr_item_master.barcode13,"+company+".mr_item_master.barcode14,"+company+".mr_item_master.barcode15,"+company+".mr_item_master.barcode16,"+company+".mr_item_master.barcode17,"+company+".mr_item_master.barcode18) and item_code!='"+txtItemcode.getText()+"' ";
									ResultSet resultSet3;
									resultSet3 = statement.executeQuery(query);
									if(resultSet3.next())
									{
										JOptionPane.showMessageDialog(frame, "Barcode Already Exist in " + resultSet3.getString("item_name")+" «·»«—ﬂÊœ „ÊÃÊœ »«·›⁄· ›Ì");
									}
								} catch (SQLException e2) {
//									frame.setAlwaysOnTop(false);
//									JOptionPane.showMessageDialog( frame, e2.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE );
//									frame.setAlwaysOnTop(true);
								}
								
		 					}
	                	}
			    	}
	            	
				}
			});
		scrollPane.setViewportView(table);
		
		
		
		JLabel lblItemBarcode = new JLabel("Item Barcode");
		if(language.equals("Arabic"))
		{
			lblItemBarcode.setText("«·»«—ﬂÊœ «·Œ«’ »«·⁄‰’—");
		}
		lblItemBarcode.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblItemBarcode.setBounds(10, 0, 205, 25);
		panel_1.add(lblItemBarcode);
		
		JLabel lblfForAuto = new JLabel("(Press F3 for Auto Generated Barcode)");
		if(language.equals("Arabic"))
		{
			lblfForAuto.setText("(«÷€ÿ ⁄·Ï F3 ·≈‰‘«¡ —„“ ‘—ÌÿÌ  ·ﬁ«∆Ì«)");
		}
		lblfForAuto.setForeground(Color.RED);
		lblfForAuto.setBounds(163, 5, 232, 16);
		panel_1.add(lblfForAuto);
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(975, 30, 38, 146);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String barcode="";
				int row=table.getSelectedRow();
				int col=table.getSelectedColumn();
				if(col==3 || col==4 || col==5)
				{
					barcode=table.getValueAt(row, col).toString();
					if(barcode.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "No Values In Barcode ·«  ÊÃœ ﬁÌ„ ›Ì «·»«—ﬂÊœ");
					}
					else
					{
						if(txtItemcode.getText().equals(""))
						{
							JOptionPane.showMessageDialog(frame, "<html>Enter Or Select An Item from the List <br>√œŒ· √Ê Õœœ ⁄‰’—« „‰ «·ﬁ«∆„…</html>");
						}
						else
						{
							try {
								frame.setAlwaysOnTop(false);
								new purchase_master.print(company, barcode,txtItemcode.getText(),Printer, version,"item",store);
								frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
								menuItem.setEnabled(false);

							} catch (JSONException e) {
								frame.setAlwaysOnTop(false);
								JOptionPane.showMessageDialog( frame, e.getMessage(),"JSONException error", JOptionPane.ERROR_MESSAGE );
								frame.setAlwaysOnTop(true);
							}
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "<html>Choose one Barcode from the table<br>«Œ — —„“« ‘—ÌÿÌ« Ê«Õœ« „‰ «·ÃœÊ·</html>");
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(printer));
		
		label_5 = new JLabel("Background Color");
		if(language.equals("Arabic"))
		{
			label_5.setText("·Ê‰ «·Œ·›Ì…");
		}
		label_5.setBounds(575, 3, 111, 22);
		panel_1.add(label_5);
		
		button_3 = new JButton("Choose");
		if(language.equals("Arabic"))
		{
			button_3.setText("ÌŒ «—");
		}
		button_3.setBackground(null);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setAlwaysOnTop(false);
				Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);	
				sel_color="#"+Integer.toHexString(newColor.getRGB() & 0x00ffffff);
				button_3.setBackground(newColor);
				frame.setAlwaysOnTop(true);
			}
		});
		button_3.setBounds(698, 3, 81, 22);
		panel_1.add(button_3);
		
		button_5 = new JButton("Choose");
		if(language.equals("Arabic"))
		{
			button_5.setText("ÌŒ «—");
		}
		button_5.setBackground(null);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setAlwaysOnTop(false);
				Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);	
				fore_sel_color="#"+Integer.toHexString(newColor.getRGB() & 0x00ffffff);
				button_5.setBackground(newColor);
				frame.setAlwaysOnTop(true);
			}
		});
		button_5.setBounds(932, 0, 81, 22);
		panel_1.add(button_5);
		
		label_6 = new JLabel("Fore Ground Color");
		if(language.equals("Arabic"))
		{
			label_6.setText("«··Ê‰ «·√„«„Ì");
		}
		label_6.setBounds(809, 0, 111, 22);
		panel_1.add(label_6);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(243, 229, 216));
		panel_2.setBounds(0, 570, 1018, 148);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(243, 229, 216));
		panel_12.setBounds(0, 25, 300, 125);
		panel_2.add(panel_12);
		panel_12.setLayout(null);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(243, 229, 216));
		panel_10.setBounds(312, 25, 325, 125);
		panel_2.add(panel_10);
		panel_10.setLayout(null);
		
		
		
		JLabel lblItemControls = new JLabel("Item Controls");
		if(language.equals("Arabic"))
		{
			lblItemControls.setText("÷Ê«»ÿ «·⁄‰’—");
		}
		lblItemControls.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblItemControls.setBounds(10, 5, 205, 25);
		panel_2.add(lblItemControls);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(243, 229, 216));
		panel_11.setBounds(636, 25, 370, 125);
		panel_2.add(panel_11);
		panel_11.setLayout(null);
		
		JLabel lblInputTax = new JLabel("1. Input Tax - PO");
		if(language.equals("Arabic"))
		{
			lblInputTax.setText("1. ÷—Ì»… «·„œŒ·«  - √„— «·‘—«¡");
		}
		lblInputTax.setBounds(12, 13, 109, 16);
		panel_12.add(lblInputTax);
		
		JLabel lblOutputTax = new JLabel("2. Output Tax Sales");
		if(language.equals("Arabic"))
		{
			lblOutputTax.setText("2. ÷—Ì»… «·≈‰ «Ã ⁄·Ï «·„»Ì⁄« ");
		}
		lblOutputTax.setBounds(12, 34, 128, 16);
		panel_12.add(lblOutputTax);
		
		chckbxSalesPriceInclusive = new JCheckBox("5. Sales price inclusive of tax");
		if(language.equals("Arabic"))
		{
			chckbxSalesPriceInclusive.setText("5. ”⁄— «·»Ì⁄ ‘«„· «·÷—Ì»…");
		}
		chckbxSalesPriceInclusive.setSelected(true);
		chckbxSalesPriceInclusive.setBackground(new Color(243, 229, 216));
		chckbxSalesPriceInclusive.setBounds(8, 100, 253, 16);
		panel_12.add(chckbxSalesPriceInclusive);
		
		chckbxpoPriceInclusive = new JCheckBox("4. PO price inclusive of tax");
		if(language.equals("Arabic"))
		{
			chckbxpoPriceInclusive.setText("4. ”⁄— ÿ·» «·‘—«¡ ‘«„· «·÷—Ì»…");
		}
		chckbxpoPriceInclusive.setSelected(true);
		chckbxpoPriceInclusive.setBackground(new Color(243, 229, 216));
		chckbxpoPriceInclusive.setBounds(8, 76, 253, 16);
		panel_12.add(chckbxpoPriceInclusive);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(223, 13, 15, 16);
		panel_10.add(lblTo);
		
		chckbxAllowDiscount = new JCheckBox("7. Allow Discount");
		if(language.equals("Arabic"))
		{
			chckbxAllowDiscount.setText("7. «·”„«Õ »«·Œ’„");
		}
		chckbxAllowDiscount.setBackground(new Color(243, 229, 216));
		chckbxAllowDiscount.setBounds(8, 34, 133, 16);
		panel_10.add(chckbxAllowDiscount);
		
		chckbxActiveForSale = new JCheckBox("10. Active for Sale");
		if(language.equals("Arabic"))
		{
			chckbxActiveForSale.setText("10. ‰‘ÿ ··»Ì⁄");
		}
		chckbxActiveForSale.setBackground(new Color(243, 229, 216));
		chckbxActiveForSale.setBounds(8, 76, 133, 16);
		chckbxActiveForSale.setSelected(true);
		panel_10.add(chckbxActiveForSale);
		
		dateChooser = new JDateChooser(new Date());
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(127, 13, 87, 16);
		dateChooser.setEnabled(false);
		dateChooser.setMinSelectableDate(new Date());
		//dateChooser.getJCalendar().setPreferredSize(new Dimension(200, 200));
		panel_10.add(dateChooser);
		
		chckbxActiveForPurchase = new JCheckBox("8. Active for Purchase");
		if(language.equals("Arabic"))
		{
			chckbxActiveForPurchase.setText("8. ‰‘ÿ ··‘—«¡");
		}
		chckbxActiveForPurchase.setBackground(new Color(243, 229, 216));
		chckbxActiveForPurchase.setBounds(8, 55, 169, 16);
		chckbxActiveForPurchase.setSelected(true);
		panel_10.add(chckbxActiveForPurchase);
		
		JLabel lblMinimumOrderLevel = new JLabel("3. Minimum Order Level");
		if(language.equals("Arabic"))
		{
			lblMinimumOrderLevel.setText("3. «·Õœ «·√œ‰Ï ·„” ÊÏ «·ÿ·»");
		}
		lblMinimumOrderLevel.setBounds(12, 55, 143, 16);
		panel_12.add(lblMinimumOrderLevel);
		
		comboBox_7 = new JComboBox<String>();
		comboBox_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_7.getSelectedIndex()==-1){
					chckbxpoPriceInclusive.setEnabled(false);
				}
				else{
					if(comboBox_7.getSelectedIndex()==1)
					{
						chckbxpoPriceInclusive.setEnabled(false);
						chckbxpoPriceInclusive.setSelected(false);
					}
					else
					{
						chckbxpoPriceInclusive.setEnabled(true);
						
					}
				}
			}
		});
		comboBox_7.setBounds(168, 13, 121, 16);
		Connection con=Dbsetup.dbconn.getConnection();
		try
		{
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_tax");
			while(resultSet.next())
			{
				comboBox_7.addItem(resultSet.getString("tax_code"));
			}
		}
		catch(SQLException e1)
		{
			
		}
		comboBox_7.addItem("");
		panel_12.add(comboBox_7);
		
		comboBox_8= new JComboBox<String>();
		comboBox_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_8.getSelectedIndex()==-1)
				{
					chckbxSalesPriceInclusive.setEnabled(false);
				}
				else
				{
					if(comboBox_8.getSelectedIndex()==1)
					{
						chckbxSalesPriceInclusive.setEnabled(false);
						chckbxSalesPriceInclusive.setSelected(false);
					}
					else
					{
						chckbxSalesPriceInclusive.setEnabled(true);
						
					}
				}
			}
		});
		comboBox_8.setBounds(168, 34, 121, 16);
		Statement statement1=con.createStatement();  
		ResultSet resultSet1 = statement1.executeQuery("select * from "+company+".mr_tax");
		while(resultSet1.next())
		{
			comboBox_8.addItem(resultSet1.getString("tax_code"));
		}
		con.close();
		comboBox_8.addItem("");
		panel_12.add(comboBox_8);
		
		textField_13 = new JTextField();
		textField_13.setBounds(167, 55, 48, 16);
		panel_12.add(textField_13);
		textField_13.setColumns(10);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.getCalendarButton().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Date date = dateChooser.getDate();
						dateChooser_1.getJCalendar().setMinSelectableDate(date);
						dateChooser_1.setDate(date);
					}
				});
		dateChooser_1.setDateFormatString("dd/MM/yyyy");
		dateChooser_1.setBounds(238, 13, 87, 16);
		dateChooser_1.setEnabled(false);
		panel_10.add(dateChooser_1);
		
		
		
		textField_7 = new JTextField("0");
		textField_7.setBounds(148, 34, 48, 16);
		panel_10.add(textField_7);
		textField_7.setEnabled(true);
		
		textField_7.setColumns(10);
		
		chckbxParentKit = new JCheckBox("12. Parent Kit Item");
		if(language.equals("Arabic"))
		{
			chckbxParentKit.setText("12. ⁄‰’— „Ã„Ê⁄… «·Ê«·œÌ‰");
		}
		chckbxParentKit.setBackground(new Color(243, 229, 216));
		chckbxParentKit.setBounds(8, 97, 169, 16);
		panel_10.add(chckbxParentKit);
		
		chckbxOn = new JCheckBox("6. On Promotion");
		if(language.equals("Arabic"))
		{
			chckbxOn.setText("6. ÕÊ· «· —ÊÌÃ");
		}
		chckbxOn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxOn.isSelected())
				{
					dateChooser.setEnabled(true);
					dateChooser_1.setEnabled(true);
				}
				else
				{
					dateChooser.setEnabled(false);
					dateChooser_1.setEnabled(false);
				}
			}
		});
		chckbxOn.setBackground(new Color(243, 229, 216));
		chckbxOn.setBounds(8, 13, 117, 16);
		panel_10.add(chckbxOn);
		
//		JLabel lblComputeQuantity = new JLabel("13. Compute Quantity");
//		lblComputeQuantity.setBounds(12, 87, 144, 22);
//		panel_11.add(lblComputeQuantity);
//		
//		JRadioButton rdbtnY = new JRadioButton("Y",true);
//		rdbtnY.setBounds(164, 86, 45, 25);
//		panel_11.add(rdbtnY);
//		
//		JRadioButton rdbtnN = new JRadioButton("N");
//		rdbtnN.setBounds(248, 86, 45, 25);
//		panel_11.add(rdbtnN);
//		
//		ButtonGroup group = new ButtonGroup();
//        group.add(rdbtnY);
//        group.add(rdbtnN);
//		
//		JLabel lblRightSide = new JLabel("14. Right Side Barcode Data");
//		lblRightSide.setBounds(12, 61, 168, 16);
//		panel_11.add(lblRightSide);
//		
//		JComboBox<String> comboBox_10 = new JComboBox<String>();
//		comboBox_10.setSelectedIndex(-1);
//		comboBox_10.setBounds(192, 61, 171, 22);
//		comboBox_10.addItem("Weighing Pieces");
//		comboBox_10.addItem("Pieces");
//		comboBox_10.addItem("Prices");
//		panel_11.add(comboBox_10);
		
		chckbxOpeningQty = new JCheckBox("9. Opening Qty");
		if(language.equals("Arabic"))
		{
			chckbxOpeningQty.setText("9. «·ﬂ„Ì… «·«›  «ÕÌ…");
		}
		chckbxOpeningQty.setBounds(179, 55, 133, 16);
		panel_10.add(chckbxOpeningQty);
		chckbxOpeningQty.setBackground(new Color(243, 229, 216));
		
		chckbxOpenItem = new JCheckBox("11. Open Price");
		if(language.equals("Arabic"))
		{
			chckbxOpenItem.setText("11. ”⁄— «·«›  «Õ");
		}
		chckbxOpenItem.setBounds(179, 76, 133, 16);
		panel_10.add(chckbxOpenItem);
		chckbxOpenItem.setBackground(new Color(243, 229, 216));
		
		chckbxFastItem = new JCheckBox("13. Fast Item");
		if(language.equals("Arabic"))
		{
			chckbxFastItem.setText("13. ⁄‰’— ”—Ì⁄");
		}
		chckbxFastItem.setBounds(179, 97, 133, 16);
		panel_10.add(chckbxFastItem);
		chckbxFastItem.setBackground(new Color(243, 229, 216));
		
		JLabel lblAccountGroup = new JLabel("14. Account  Group");
		if(language.equals("Arabic"))
		{
			lblAccountGroup.setText("14. „Ã„Ê⁄… «·Õ”«»« ");
		}
		lblAccountGroup.setBounds(12, 7, 121, 16);
		panel_11.add(lblAccountGroup);
		
		comboBox_9 = new JComboBox<String>();
		comboBox_9.setBounds(139, 5, 224, 16);
		panel_11.add(comboBox_9);
		
		get_chartofaccount(company,store);
		
		
		chckbxDeliItemsweighing = new JCheckBox("16. Deli items/Weighing");
		if(language.equals("Arabic"))
		{
			chckbxDeliItemsweighing.setText("16. √’‰«› «·√ÿ⁄„… «·Ã«Â“…/«·Ê“‰");
		}
		chckbxDeliItemsweighing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxDeliItemsweighing.isSelected())
				{
					textField_11.setEditable(true);
					comboBox_10.setEnabled(true);
				}
				else
				{
					textField_11.setEditable(false);
					comboBox_10.setEnabled(false);
				}
			}
		});
		chckbxDeliItemsweighing.setBackground(new Color(243, 229, 216));
		chckbxDeliItemsweighing.setBounds(8, 53, 179, 16);
		panel_11.add(chckbxDeliItemsweighing);
		
		JLabel lblExpirydays = new JLabel("Expiry_days");
		if(language.equals("Arabic"))
		{
			lblExpirydays.setText("√Ì«„ «‰ Â«¡ «·’·«ÕÌ…");
		}
		lblExpirydays.setBounds(187, 50, 73, 16);
		panel_11.add(lblExpirydays);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setBounds(272, 47, 38, 22);
		panel_11.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblWeighingStatus = new JLabel("17. Weighing Status");
		if(language.equals("Arabic"))
		{
			lblWeighingStatus.setText("17. Õ«·… «·Ê“‰");
		}
		lblWeighingStatus.setBounds(8, 76, 121, 16);
		panel_11.add(lblWeighingStatus);
		
		comboBox_10 = new JComboBox<String>();
		comboBox_10.setEnabled(false);
		comboBox_10.addItem("Y");
		comboBox_10.addItem("N");
		comboBox_10.setBounds(135, 74, 52, 22);
		panel_11.add(comboBox_10);
		
		JLabel lblInventoryAc = new JLabel("15. Inventory A/c");
		if(language.equals("Arabic"))
		{
			lblInventoryAc.setText("15. Õ”«» «·„Œ“Ê‰");
		}
		lblInventoryAc.setBounds(12, 30, 110, 16);
		panel_11.add(lblInventoryAc);
		
		comboBox_11 = new JComboBox<String>();
		comboBox_11.setBounds(139, 26, 224, 16);
		panel_11.add(comboBox_11);
		
		chckbxUnitUsedAs = new JCheckBox("19. Unit Used as Item Vareint");
		if(language.equals("Arabic"))
		{
			chckbxUnitUsedAs.setText("19. «·ÊÕœ… «·„” Œœ„… ﬂ’‰›");
		}
		chckbxUnitUsedAs.setBackground(new Color(243, 229, 216));
		chckbxUnitUsedAs.setBounds(0, 99, 207, 16);
		panel_11.add(chckbxUnitUsedAs);
		
		chckbxDoNot = new JCheckBox("18. Not Add in discount");
		if(language.equals("Arabic"))
		{
			chckbxDoNot.setText("18. ·« Ì÷«› ≈·Ï «·Œ’„");
		}
		chckbxDoNot.setBackground(new Color(243, 229, 216));
		chckbxDoNot.setBounds(187, 76, 176, 16);
		panel_11.add(chckbxDoNot);
		
		chckbxOpenQty = new JCheckBox("20. Open Qty");
		if(language.equals("Arabic"))
		{
			chckbxOpenQty.setText("20. «·ﬂ„Ì… «·„› ÊÕ…");
		}
		chckbxOpenQty.setBackground(new Color(243, 229, 216));
		chckbxOpenQty.setBounds(212, 99, 133, 16);
		panel_11.add(chckbxOpenQty);
		
		get_acc_name(company);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setBounds(448, 1, 67, 23);
		panel_2.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblTotalPurchsedQty = new JLabel("Total Purchsed Qty :");
		if(language.equals("Arabic"))
		{
			lblTotalPurchsedQty.setText("≈Ã„«·Ì «·ﬂ„Ì… «·„‘ —«… :");
		}
		lblTotalPurchsedQty.setBounds(323, 2, 124, 16);
		panel_2.add(lblTotalPurchsedQty);
		
		JLabel lblTotalPurreturnQty = new JLabel("Total Pur.Return Qty : ");
		if(language.equals("Arabic"))
		{
			lblTotalPurreturnQty.setText("≈Ã„«·Ì ﬂ„Ì… ≈—Ã«⁄ «·„‘ —Ì« :");
		}
		lblTotalPurreturnQty.setBounds(580, 5, 135, 16);
		panel_2.add(lblTotalPurreturnQty);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(714, 1, 67, 22);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(243, 229, 216));
		panel_3.setBounds(0, 0, 1018, 35);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		Image barcode=new ImageIcon(this.getClass().getResource("/Barcode.png")).getImage();
		JLabel lblItemMaster = new JLabel("Item Master");
		if(language.equals("Arabic"))
		{
			lblItemMaster.setText("”Ìœ «·»‰œ");
		}
		lblItemMaster.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMaster.setIcon(new ImageIcon(barcode));
		lblItemMaster.setBounds(0, 2, 146, 30);
		panel_3.add(lblItemMaster);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		
		menuItem = new JMenuItem("Exit",KeyEvent.VK_F4);
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
		menuItem.setBorder(null);
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				CafePro.Mainpage.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	CafePro.Mainpage.btnNewButton.setEnabled(true);
		    	CafePro.Mainpage.btnNewButton_1.setEnabled(true);
			}
		});
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(700, 2, 95, 30);
		panel_3.add(menuItem);
		
		menuItem_1 = new JMenuItem("Refresh",KeyEvent.VK_F5);
		if(language.equals("Arabic"))
		{
			menuItem_1.setText("Ì‰⁄‘");
		}
		menuItem_1.setBorder(null);
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//listname.clear();
				txtshrt_nme.setText("");
				txtItemcode.setText("");
				txt_desc1.setText("");
				textArea_1.setText("");
				item_dtls.setText("");
				textField.setText("");
				textField_3.setText("");
				textField_2.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				txtHidenimagepath.setText("");
				textField_13.setText("");
				textField_7.setText("");
				textField_11.setText("");
				btnNewButton_2.setVisible(false);
				chckbxParentKit.setSelected(false);
				chckbxOpeningQty.setSelected(false);
				((JTextField) dateChooser.getDateEditor().getUiComponent()).setText("");
				((JTextField) dateChooser_1.getDateEditor().getUiComponent()).setText("");
				textField_8.setText("");
				textField_10.setText("");
				lblitemimage.setIcon(null);
				chckbxDeliItemsweighing.setSelected(false);
				chckbxFastItem.setSelected(false);
				chckbxOpenItem.setSelected(false);
				chckbxUnitUsedAs.setSelected(false);
				chckbxDoNot.setSelected(false);
				comboBox_10.setEnabled(false);
				button_3.setBackground(null);
				button_5.setBackground(null);
				int row=modellist.getRowCount();
				for (int i = row - 1; i >= 0; i--) {
					modellist.removeRow(i);
	        	}
				if (table.isEditing())
				     table.getCellEditor().stopCellEditing();
				int rows=model.getRowCount();
				for (int i = rows - 1; i >= 0; i--) {
					model.removeRow(i);
	        	}
				String cf = "";
				String sellprice="";
				String bc1="";
				String bc2="";
				String bc3="";
				
				String unit1="";
				String unit2="";
				String unit3="";
				String unit4="";
				String unit5="";
				String unit6="";
				
				try (InputStream input = new FileInputStream("C:/MasterRetails/itembarcode_field.properties")) {

		            Properties prop = new Properties();

		            // load a properties file
		            prop.load(input);

		            unit1=prop.getProperty("Each");
		            unit2=prop.getProperty("Box");
		            unit3=prop.getProperty("Pack1");
		            unit4=prop.getProperty("Pack2");
		            unit5=prop.getProperty("Pack3");
		            unit6=prop.getProperty("Pack4");
		            
		            

		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
				
				
				
				model.addRow(new Object[]{unit1,"1",sellprice,bc1, bc2,bc3});
				model.addRow(new Object[]{unit2,cf,sellprice,bc1, bc2,bc3});
				model.addRow(new Object[]{unit3,cf,sellprice,bc1, bc2,bc3});
				model.addRow(new Object[]{unit4,cf,sellprice,bc1, bc2,bc3});
				model.addRow(new Object[]{unit5,cf,sellprice,bc1, bc2,bc3});
				model.addRow(new Object[]{unit6,cf,sellprice,bc1, bc2,bc3});
				
				label_2.setText("0");
				label_3.setText("0");
				label_4.setText("0");
				
				comboBox_2.setSelectedIndex(-1);
				comboBox_3.setSelectedIndex(-1);
				comboBox_4.setSelectedIndex(-1);
				comboBox_5.setSelectedIndex(-1);
				txtItemcode.requestFocus();
			}
		});
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setIcon(new ImageIcon(refresh));
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(795, 2, 116, 30);
		panel_3.add(menuItem_1);
		
		menuItem_2 = new JMenuItem("Save");
		if(language.equals("Arabic"))
		{
			menuItem_2.setText("ÌÕ›Ÿ");
		}
		//menuItem_2.setMnemonic(KeyEvent.VK_S);
		menuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String code=txtItemcode.getText();
					String name=txtshrt_nme.getText();
					String desc1=txt_desc1.getText();
					String desc2=textArea_1.getText();
					String itm_dtls=item_dtls.getText();
					String type=(String) comboBox_6.getSelectedItem();
					String itm_cst=textField.getText();
					if(itm_cst.equals(""))
					{
						itm_cst="0";
					}
					String qty=textField_8.getText();
					if(qty.equals(""))
					{
						qty="0";
					}
					String lst_cst=textField_3.getText();
					if(lst_cst.equals(""))
					{
						lst_cst="0";
					}
					String std_cst=textField_6.getText();
					if(std_cst.equals(""))
					{
						std_cst="0";
					}
					Connection con=Dbsetup.dbconn.getConnection();
					if (table.isEditing())
					     table.getCellEditor().stopCellEditing();
					String image=txtHidenimagepath.getText();
					String input_tax=comboBox_7.getSelectedItem().toString();
					String output_tax=comboBox_8.getSelectedItem().toString();
					String input_tax_rate="0";
					String output_tax_rate="0";
					if(!input_tax.equals(""))
					{
						String query="SELECT * FROM "+company+".mr_tax where tax_code='"+input_tax+"' ";
						Statement statement11 = null;
						ResultSet resultSet3;
						statement11 = con.createStatement();
						resultSet3 = statement11.executeQuery(query);
						if(resultSet3.next())
						{
							input_tax_rate=resultSet3.getString("tax_rate");
						}
					}
					if(!output_tax.equals(""))
					{
						String query="SELECT * FROM "+company+".mr_tax where tax_code='"+output_tax+"' ";
						Statement statement11 = null;
						ResultSet resultSet3;
						statement11 = con.createStatement();
						resultSet3 = statement11.executeQuery(query);
						if(resultSet3.next())
						{
							output_tax_rate=resultSet3.getString("tax_rate");
						}
					}
					String min_ordr=textField_13.getText();
					String sp_include_tax="";
					String item_vareint="";
					String Open_Qty="";
					String po_include_tax="";
					String deliItemsweighing="";
					String not_disc="";
					String openqty="";
					String parentKitItem="";
					String allowDiscount="";
					String disc_perc=textField_7.getText();
					String activeForPurchase="";
					String activeForSale="";
					String Onpromo="";
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String startdate = null;
					String enddate = null;
					String start_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					if(!start_date.equals(""))
					{
						startdate = sdf.format(dateChooser.getDate());
					}
					String end_date = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					if(!end_date.equals(""))
					{
						enddate = sdf.format(dateChooser_1.getDate());
					}
					String starting_date ="";
					String endingdate ="";
					String acc_grp = "";
					String acc_code = "";
					String disc_perct="";
					String exp_days="";
					String weight_stats="";
					String openitem="";
					String fast_item="";
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj); 
					if(code.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "<html>Enter The Item Code<br>√œŒ· —„“ «·”·⁄…</html>");
						txtItemcode.requestFocus();
					}
					else if(code.equals(" "))
					{
						JOptionPane.showMessageDialog(frame, "<html>Enter The Item Code<br>√œŒ· —„“ «·”·⁄…</html>");
						txtItemcode.requestFocus();
					}
					else if(name.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "<html>Enter The Item Name<br>√œŒ· «”„ «·⁄‰’—</html>");
						txtshrt_nme.requestFocus();
					}
					else if(desc1.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "<html>Enter The Item Description1<br>√œŒ· Ê’› «·⁄‰’—1</html>");
						txt_desc1.requestFocus();
					}
					else if(comboBox_2.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(frame, "<html>Select The Item Department<br>Õœœ ﬁ”„ «·⁄‰«’—</html>");
						comboBox_2.requestFocus();
					}
					else if(comboBox_3.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(frame, "<html>Select The Item Sub Department<br>Õœœ «·ﬁ”„ «·›—⁄Ì ··⁄‰’—</html>");
						comboBox_3.requestFocus();
					}
					else if(comboBox_4.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(frame, "<html>Select The Item Category<br>Õœœ ›∆… «·⁄‰’—</html>");
						comboBox_4.requestFocus();
					}
					else if(comboBox_5.getSelectedIndex()==-1)
					{
						JOptionPane.showMessageDialog(frame, "<html>Select The Item Sub Category<br>Õœœ «·›∆… «·›—⁄Ì… ··⁄‰’—</html>");
						comboBox_5.requestFocus();
					}
					else if (comboBox_6.getSelectedIndex() == -1)
					{
						JOptionPane.showMessageDialog(frame,"<html>Please, Select the Type<br>«·—Ã«¡  ÕœÌœ «·‰Ê⁄</html>");
						comboBox_6.requestFocus();
					}
					else
					{
						String dept=comboBox_2.getSelectedItem().toString();
						String sub_dept=comboBox_3.getSelectedItem().toString();
						String cat=comboBox_4.getSelectedItem().toString();
						String sub_cat=comboBox_5.getSelectedItem().toString();
						
						if(comboBox_9.getSelectedItem()!=null)
						{
							acc_grp=comboBox_9.getSelectedItem().toString();
						}
						if(comboBox_11.getSelectedItem()!=null)
						{
							acc_code=comboBox_11.getSelectedItem().toString();
						}
						
						if(chckbxUnitUsedAs.isSelected())
						{
							item_vareint="1";
						}
						else
						{
							item_vareint="0";
						}
						if(chckbxOpenQty.isSelected())
						{
							Open_Qty="1";
						}
						else
						{
							Open_Qty="0";
						}
						
						if(chckbxSalesPriceInclusive.isSelected())
						{
							sp_include_tax="1";
						}
						else
						{
							sp_include_tax="0";
						}
						if(chckbxpoPriceInclusive.isSelected())
						{
							po_include_tax="1";
						}
						else
						{
							po_include_tax="0";
						}
						if(chckbxFastItem.isSelected())
						{
							fast_item="1";
						}
						else
						{
							fast_item="0";
						}
						if(chckbxOpenItem.isSelected())
						{
							openitem="1";
						}
						else
						{
							openitem="0";
						}
						if(chckbxDoNot.isSelected())
						{
							not_disc="1";
						}
						else
						{
							not_disc="0";
						}
						if(chckbxDeliItemsweighing.isSelected())
						{
							deliItemsweighing="1";
							String expiry="0";
							if(!textField_11.getText().equals(""))
							{
								expiry=textField_11.getText();
							}
							exp_days=expiry;
							weight_stats=comboBox_10.getSelectedItem().toString();
						}
						else
						{
							deliItemsweighing="0";
							exp_days="";
							weight_stats="";
						}
						if(chckbxOpeningQty.isSelected())
						{
							openqty="1";
						}
						else
						{
							openqty="0";
						}
						
						if(chckbxParentKit.isSelected())
						{
							parentKitItem="1";
						}
						else
						{
							parentKitItem="0";
						}
						if(chckbxAllowDiscount.isSelected())
						{
							allowDiscount="1";
							if(disc_perc.equals(""))
							{
								disc_perct="0";
							}
							else
							{
								disc_perct=disc_perc;
							}
						}
						else
						{
							allowDiscount="0";
							disc_perct="0";
						}
						if(chckbxActiveForSale.isSelected())
						{
							activeForSale="1";
						}
						else
						{
							activeForSale="0";
						}
						if(chckbxActiveForPurchase.isSelected())
						{
							activeForPurchase="1";
						}
						else
						{
							activeForPurchase="0";
						}
						if(chckbxOn.isSelected())
						{
							Onpromo="1";
							starting_date=startdate;
							endingdate=enddate;
						}
						else
						{
							Onpromo="0";
							starting_date=formattedDate;
							endingdate=formattedDate;
						}
						if(!min_ordr.equals(""))
						{
							
						}
						else
						{
							min_ordr="0";
						}
						
						
						
						Statement statement = null;
						ResultSet resultSet;
						String query23 ="";
						
						for(int i=0;i<table.getRowCount();i++)
						{
							for(int j=0;j<table.getColumnCount();j++)
							{
								if(j==3 || j==4 || j==5)
								{
									if(!table.getValueAt(i, j).toString().equals(""))
				 					{
										if(table.getValueAt(i, 1).toString().length()>4)
				            			{
				            				JOptionPane.showMessageDialog(frame, "<html>CHECK CONVERT FACTOR<br>«· Õﬁﬁ „‰ ⁄«„· «· ÕÊÌ·</html>" );
				            				return;
				            			}
										if(table.getValueAt(i, j).toString().equals(txtItemcode.getText()))
				            			{
				            				JOptionPane.showMessageDialog(frame,"<html>"+ table.getValueAt(i, j).toString()+" Barcode Already Exist as Item code <br>«·—„“ «·‘—ÌÿÌ „ÊÃÊœ »«·›⁄· ﬂ—„“ ··⁄‰’— "+ table.getValueAt(i, j).toString()+"</html>" );
				            				return;
				            			}
				            			else
				            			{
				            				statement = con.createStatement();
											String query="SELECT item_name FROM "+company+".mr_tmp_item_master where (item_code='"+table.getValueAt(i, j).toString()+"' or barcode='"+table.getValueAt(i, j).toString()+"')  and item_code!='"+txtItemcode.getText()+"' ";
											ResultSet resultSet3;
											resultSet3 = statement.executeQuery(query);
											if(resultSet3.next())
											{
												JOptionPane.showMessageDialog(frame, "<html>Barcode Already Exist in " + resultSet3.getString("item_name")+"<br>«·»«—ﬂÊœ „ÊÃÊœ »«·›⁄· ›Ì" + resultSet3.getString("item_name")+"</html>");
												return;
											}
				            			}
										
										
				 					}
								}
							}
							
						}
						if(code.equals("NEW"))
						{
							String myArray[] = new String[18];
							for(int x=0;x<table.getRowCount();x++)
							{
								for(int y=0;y<table.getColumnCount();y++)
								{
									if(y==3 || y==4 || y==5)
									{
										for (int i = 0; i < 18; i++)  
								        { 
								            for (int j = i + 1; j < 18; j++)  
								            { 
								            	if (myArray[i] == myArray[j])  
								                {
								            		if(myArray[j]!=null)
								            		{
								            			JOptionPane.showMessageDialog(frame, "<html>Duplicate Barcode Found <br> „ «·⁄ÀÊ— ⁄·Ï —„“ ‘—ÌÿÌ „ﬂ——</html>");
								            		}
								                } 
								            } 
								        } 
									}
								}
							}
						}
						if(code.length()>8)
						{
							JOptionPane.showMessageDialog(frame, "<html>Itemcode look like Barcode<br>Ì»œÊ —„“ «·⁄‰’— „À· «·—„“ «·‘—ÌÿÌ</html>");
							txtItemcode.requestFocus();
						}
						else
						{
							String itemcode = null ;
							statement = con.createStatement();
							Statement statement2 = con.createStatement();
							if(code.equals("NEW"))
							{
								/*insert*/
								String queryhd="";
								ResultSet resultSet2 = statement2.executeQuery("select item_name from "+company+".mr_item_master where item_name='"+name+"'");
								if(!resultSet2.next())
								{
									Statement statement1 = con.createStatement();
									ResultSet resultSet1 = statement1.executeQuery("select * from "+company+".mr_department where dept_name='"+dept+"'");
									if(resultSet1.next())
									{
										String dept_code=resultSet1.getString("dept_code");
										String seq_num=resultSet1.getString("seq_num");
										StringBuilder sb = new StringBuilder();
										for (int toPrepend=3-dept_code.length(); toPrepend>0; toPrepend--) {
										    sb.append('0');
										}
										sb.append(dept_code);
										StringBuilder sq = new StringBuilder();
										for (int toPrepend=5-seq_num.length(); toPrepend>0; toPrepend--) {
										    sq.append('0');
										}
										sq.append(seq_num);
										itemcode = sb.toString()+""+sq.toString();
										String seq=Integer.toString(Integer.parseInt(seq_num)+1);
										String updte_dept="update "+company+".mr_department set seq_num='"+seq+"' where dept_name='"+dept+"' ";
										statement.executeUpdate(updte_dept);
									}
									ResultSet resultSet212 = statement2.executeQuery("select item_code from "+company+".mr_item_master where item_code='"+itemcode+"'");
									if(resultSet212.next())
									{
										JOptionPane.showMessageDialog(frame, "<html>Duplicate Dept code, Contact Administrator to Save the File<br>—„“ «·ﬁ”„ „ﬂ——° « ’· »«·„”ƒÊ· ·Õ›Ÿ «·„·›</html>");
										return;
									}
									String bcde1="";
									for (int i = 0; i < table.getRowCount(); i++) {
										if(i==0)
										 {
											if(table.getValueAt(i, 3).toString().equals(""))
											{
												bcde1=itemcode+"03";
											}
											else
											{
												bcde1=table.getValueAt(i, 3).toString();
											}
											String sp1="";
											if(table.getValueAt(i, 2).toString().equals(""))
											{
												sp1="0";
											}
											else
											{
												sp1=table.getValueAt(i, 2).toString();
											}
											if(table.getValueAt(i, 1).toString().equals(""))
											{
												if(!sp1.equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
												{
													JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
													return;
												}
											}
											 queryhd+=",`unit1`, `cf1`, `sp1`, `barcode1`, `barcode2`, `barcode3`";
											 query23+=",'"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 1)+"','"+sp1+"','"+bcde1+"','"+table.getValueAt(i, 4)+"','"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==1)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 queryhd+=",`unit2`, `cf2`, `sp2`, `barcode4`, `barcode5`, `barcode6`";
											 query23+=",'"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 1)+"','"+table.getValueAt(i, 2)+"','"+table.getValueAt(i, 3)+"','"+table.getValueAt(i, 4)+"','"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==2)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 queryhd+=",`unit3`, `cf3`, `sp3`, `barcode7`, `barcode8`, `barcode9`";
											 query23+=",'"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 1)+"','"+table.getValueAt(i, 2)+"','"+table.getValueAt(i, 3)+"','"+table.getValueAt(i, 4)+"','"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==3)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 queryhd+=",`unit4`, `cf4`, `sp4`, `barcode10`,`barcode11`, `barcode12`";
											 query23+=",'"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 1)+"','"+table.getValueAt(i, 2)+"','"+table.getValueAt(i, 3)+"','"+table.getValueAt(i, 4)+"','"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==4)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 queryhd+=",`unit5`, `cf5`, `sp5`, `barcode13`, `barcode14`, `barcode15`";
											 query23+=",'"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 1)+"','"+table.getValueAt(i, 2)+"','"+table.getValueAt(i, 3)+"','"+table.getValueAt(i, 4)+"','"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==5)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 queryhd+=",`unit6`,`cf6`, `sp6`, `barcode16`, `barcode17`, `barcode18`";
											 query23+=",'"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 1)+"','"+table.getValueAt(i, 2)+"','"+table.getValueAt(i, 3)+"','"+table.getValueAt(i, 4)+"','"+table.getValueAt(i, 5)+"'";
										 }
									}
									if(!image.equals(""))
									{
										File configFile = new File("C:/MasterRetails/config.properties");
										FileReader reader = new FileReader(configFile);
									    Properties props = new Properties();
									    props.load(reader);
									    String imageurl = props.getProperty("image");
									    reader.close();
									    Path temp = Files.copy(Paths.get(image),Paths.get("\\"+imageurl+"\\"+itemcode+".jpg") , StandardCopyOption.REPLACE_EXISTING);
									    if(temp == null) 
								        { 
								             JOptionPane.showMessageDialog(frame, "<html>Image Uploading Failed, Please Try Again<br>›‘·  Õ„Ì· «·’Ê—…° Ì—ÃÏ «·„Õ«Ê·… „—… √Œ—Ï</html>","Image Uploading",JOptionPane.ERROR_MESSAGE);
								        } 
									    else
									    {
									    	String query="insert into "+company+".mr_item_master( `item_code`, `item_name`, `description1`, `description2`, `item_details`, `department`, `sub_department`, `category`, `sub_category`, `item_type`, `item_cost`, `item_lastcost`, `item_stdcost`, `input_tax`, `output_tax`, `sp_include_tax`, `po_inclusive_tax`,`deliItemsweighing`, `exp_days`, `weighing_sts`, `parentKitItem`, `allowDiscount`, `disc_perc`, `activeForSale`, `activeForPurchase`, `Onpromo`, `start_date`,`enddate`, `min_order`, `acc_grp`,acc_code, `open_qty`, `created_date`, `modified_date`,total_qty,input_tax_rate,output_tax_rate,item_image,vendor_code,vendor_name,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty "+queryhd+") VALUES('"+itemcode+"','"+name+"','"+desc1+"','"+desc2+"','"+itm_dtls+"','"+dept+"','"+sub_dept+"','"+cat+"','"+sub_cat+"','"+type+"','"+itm_cst+"','"+lst_cst+"','"+std_cst+"','"+input_tax+"','"+output_tax+"','"+sp_include_tax+"','"+po_include_tax+"','"+deliItemsweighing+"','"+exp_days+"','"+weight_stats+"','"+parentKitItem+"','"+allowDiscount+"','"+disc_perct+"','"+activeForSale+"','"+activeForPurchase+"','"+Onpromo+"','"+starting_date+"','"+endingdate+"','"+min_ordr+"','"+acc_grp+"','"+acc_code+"','"+openqty+"','"+formattedDate+"','"+formattedDate+"','"+qty+"','"+input_tax_rate+"','"+output_tax_rate+"','\\"+imageurl+"\\"+itemcode+".jpg','"+textField_5.getText()+"','"+textField_4.getText()+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"' "+query23+")";
											statement.execute(query);
											String query1="select ref_item_id from "+company+".mr_vendor_items where ref_vendor_id='"+textField_5.getText()+"' and ref_item_id='"+itemcode+"' ";
											Statement statement55=con.createStatement();  
											ResultSet resultSet55 = statement55.executeQuery(query1);
											if(!resultSet55.next())
											{
												String del="insert into "+company+".mr_vendor_items (`ref_vendor_id`, `ref_item_id`, `created_date`) values('"+textField_5.getText()+"','"+itemcode+"','"+formattedDate+"') ";
												statement55.executeUpdate(del);
											}
									    }
									}
									else {
										String query="insert into "+company+".mr_item_master( `item_code`, `item_name`, `description1`, `description2`, `item_details`, `department`, `sub_department`, `category`, `sub_category`, `item_type`, `item_cost`, `item_lastcost`, `item_stdcost`, `input_tax`, `output_tax`, `sp_include_tax`, `po_inclusive_tax`,`deliItemsweighing`, `exp_days`, `weighing_sts`, `parentKitItem`, `allowDiscount`, `disc_perc`, `activeForSale`, `activeForPurchase`, `Onpromo`, `start_date`,`enddate`, `min_order`, `acc_grp`,acc_code, `open_qty`, `created_date`, `modified_date`,total_qty,input_tax_rate,output_tax_rate,item_image,vendor_code,vendor_name,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty "+queryhd+") VALUES('"+itemcode+"','"+name+"','"+desc1+"','"+desc2+"','"+itm_dtls+"','"+dept+"','"+sub_dept+"','"+cat+"','"+sub_cat+"','"+type+"','"+itm_cst+"','"+lst_cst+"','"+std_cst+"','"+input_tax+"','"+output_tax+"','"+sp_include_tax+"','"+po_include_tax+"','"+deliItemsweighing+"','"+exp_days+"','"+weight_stats+"','"+parentKitItem+"','"+allowDiscount+"','"+disc_perct+"','"+activeForSale+"','"+activeForPurchase+"','"+Onpromo+"','"+starting_date+"','"+endingdate+"','"+min_ordr+"','"+acc_grp+"','"+acc_code+"','"+openqty+"','"+formattedDate+"','"+formattedDate+"','"+qty+"','"+input_tax_rate+"','"+output_tax_rate+"','"+itemcode+".jpg','"+textField_5.getText()+"','"+textField_4.getText()+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"'  "+query23+")";
										statement.execute(query);
										
										String query1="select ref_item_id from "+company+".mr_vendor_items where ref_vendor_id='"+textField_5.getText()+"' and ref_item_id='"+itemcode+"' ";
										Statement statement55=con.createStatement();  
										ResultSet resultSet55 = statement55.executeQuery(query1);
										if(!resultSet55.next())
										{
											String del="insert into "+company+".mr_vendor_items (`ref_vendor_id`, `ref_item_id`, `created_date`) values('"+textField_5.getText()+"','"+itemcode+"','"+formattedDate+"') ";
											statement55.executeUpdate(del);
										}
										
									}
									for (int i = 0; i < table.getRowCount(); i++)
									{
										for (int x = 0; x < table.getColumnCount(); x++)
										{
											if(!table.getValueAt(i, x).equals(""))
											{
												if(x==3)
												{
													String chkbrcde=table.getValueAt(i, x).toString();
													if(chkbrcde.equals(""))
													{
														chkbrcde=itemcode+"03";
													}
													String sp1="";
													if(table.getValueAt(i, 2).toString().equals(""))
													{
														sp1="0";
													}
													else
													{
														sp1=table.getValueAt(i, 2).toString();
													}
													String query="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`, `type`,`input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty) "
															+ "VALUES('"+itemcode+"','"+name+"','"+chkbrcde+"','"+cat+"','"+dept+"','"+table.getValueAt(i, 0)+"','"+sp1+"','"+output_tax+"','"+sp_include_tax+"','"+itm_cst+"','"+qty+"','"+type+"','"+input_tax+"','"+po_include_tax+"','"+activeForSale+"','"+activeForPurchase+"','"+table.getValueAt(i, 1)+"','"+input_tax_rate+"','"+output_tax_rate+"','"+weight_stats+"','"+sub_cat+"','"+sub_dept+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"')";
													statement.execute(query);
												}
												if(x==4)
												{
													String chkbrcde=table.getValueAt(i, x).toString();
													if(chkbrcde.equals(""))
													{
														chkbrcde=itemcode;
													}
													String query="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`, `type`,`input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty) "
															+ "VALUES('"+itemcode+"','"+name+"','"+chkbrcde+"','"+cat+"','"+dept+"','"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 2)+"','"+output_tax+"','"+sp_include_tax+"','"+itm_cst+"','"+qty+"','"+type+"','"+input_tax+"','"+po_include_tax+"','"+activeForSale+"','"+activeForPurchase+"','"+table.getValueAt(i, 1)+"','"+input_tax_rate+"','"+output_tax_rate+"','"+weight_stats+"','"+sub_cat+"','"+sub_dept+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"')";
													statement.execute(query);
												}
												if(x==5)
												{
													String chkbrcde=table.getValueAt(i, x).toString();
													if(chkbrcde.equals(""))
													{
														chkbrcde=itemcode;
													}
													String query="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`, `type`,`input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty) "
															+ "VALUES('"+itemcode+"','"+name+"','"+chkbrcde+"','"+cat+"','"+dept+"','"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 2)+"','"+output_tax+"','"+sp_include_tax+"','"+itm_cst+"','"+qty+"','"+type+"','"+input_tax+"','"+po_include_tax+"','"+activeForSale+"','"+activeForPurchase+"','"+table.getValueAt(i, 1)+"','"+input_tax_rate+"','"+output_tax_rate+"','"+weight_stats+"','"+sub_cat+"','"+sub_dept+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"')";
													statement.execute(query);
												}
												
											}
											else
											{
												if(code.equals("NEW"))
												{
													if(x==3 && i==0)
													{
														String chkbrcde=itemcode+"03";
														String sp1="";
														if(table.getValueAt(i, 2).toString().equals(""))
														{
															sp1="0";
														}
														else
														{
															sp1=table.getValueAt(i, 2).toString();
														}
														String query="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`, `type`,`input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty) "
																+ "VALUES('"+itemcode+"','"+name+"','"+chkbrcde+"','"+cat+"','"+dept+"','"+table.getValueAt(i, 0)+"','"+sp1+"','"+output_tax+"','"+sp_include_tax+"','"+itm_cst+"','"+qty+"','"+type+"','"+input_tax+"','"+po_include_tax+"','"+activeForSale+"','"+activeForPurchase+"','"+table.getValueAt(i, 1)+"','"+input_tax_rate+"','"+output_tax_rate+"','"+weight_stats+"','"+sub_cat+"','"+sub_dept+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"')";
														statement.execute(query);
													}
												}
												
											}
										}
									}
									double ttl_cost=Double.parseDouble(itm_cst)*Double.parseDouble(qty);
									BigDecimal bx = new BigDecimal(ttl_cost);
						    		bx = bx.setScale(decimal, BigDecimal.ROUND_HALF_UP);
						    		ttl_cost = bx.doubleValue();
									String query="insert into "+company+".mr_item_master_history( `itemcode`, `trans_type`, `trans_code`, `qty`, `unit`, `cf`, `cost`, `ttl_cost`, `tranQty`, `price`, `ttl_price`, `remark`, `user`, `created_date`, `barcode`, `cust_id`, `cust_name`) VALUES('"+itemcode+"','OPEN','"+itemcode+"','"+qty+"','"+table.getValueAt(0, 0)+"','"+table.getValueAt(0, 1)+"','"+itm_cst+"','"+ttl_cost+"','0','0','0','OPENING','"+username+"','"+formattedDate+"','"+bcde1+"','','')";
									statement.execute(query);
									if(chckbxOn.isSelected() && chckbxAllowDiscount.isSelected())
									{
										String remove_dup="delete from "+company+".mr_promotion where  itemcode='"+itemcode+"'";
										statement.executeUpdate(remove_dup);
										String query22="INSERT INTO "+company+".mr_promotion(`itemcode`, `barcode`, `itemname`, `dept`, `sub_dept`, `category`, `sub_cat`, `percentage`, `from_date`, `to_date`, `promo_type`, `created_date`, `modified_date`) "
												+ "VALUES('"+itemcode+"','"+table.getValueAt(0, 3)+"','"+name+"','"+dept+"','"+sub_dept+"','"+cat+"','"+sub_cat+"','"+disc_perct+"','"+startdate+"','"+enddate+"','Item Wise','"+formattedDate+"','"+formattedDate+"') ";
										statement.execute(query22);
									}
									JOptionPane.showMessageDialog(frame, "<html>Saved Successfully<br> „ «·Õ›Ÿ »‰Ã«Õ</html>");
									get_itemdtls_bsd_code(company, itemcode,decimal);
									//txtItemcode.setText(itemcode);
									txtItemcode.requestFocus();
									table.requestFocus();
									table.changeSelection(7, 2, false, false);
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "<html>Item Name Already Exist<br>«”„ «·⁄‰’— „ÊÃÊœ »«·›⁄·</html>");
								}
							}
							else
							{
								String queryhd="";
								resultSet = statement.executeQuery("select * from "+company+".mr_item_master where item_code='"+code+"' ");
								if(resultSet.next())
								{
									for (int i = 0; i < table.getRowCount(); i++) {
										 if(i==0)
										 {
											String bcde1="";
											if(table.getValueAt(i, 3).toString().equals(""))
											{
												bcde1=code+"03";
											}
											else
											{
												bcde1=table.getValueAt(i, 3).toString();
											}
											String sp1="";
											if(table.getValueAt(i, 2).toString().equals(""))
											{
												sp1="0";
											}
											else
											{
												sp1=table.getValueAt(i, 2).toString();
											}
											
											if(table.getValueAt(i, 1).toString().equals(""))
											{
												if(!sp1.equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
												{
													JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
													return;
												}
											}
											 query23+=",`unit1`='"+table.getValueAt(i, 0)+"',`cf1`='"+table.getValueAt(i, 1)+"',`sp1`='"+sp1+"',`barcode1`='"+bcde1+"',`barcode2`='"+table.getValueAt(i, 4)+"',`barcode3`='"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==1)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 query23+=",`unit2`='"+table.getValueAt(i, 0)+"',`cf2`='"+table.getValueAt(i, 1)+"',`sp2`='"+table.getValueAt(i, 2)+"',`barcode4`='"+table.getValueAt(i, 3)+"',`barcode5`='"+table.getValueAt(i, 4)+"',`barcode6`='"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==2)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 query23+=",`unit3`='"+table.getValueAt(i, 0)+"',`cf3`='"+table.getValueAt(i, 1)+"',`sp3`='"+table.getValueAt(i, 2)+"',`barcode7`='"+table.getValueAt(i, 3)+"',`barcode8`='"+table.getValueAt(i, 4)+"',`barcode9`='"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==3)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 query23+=",`unit4`='"+table.getValueAt(i, 0)+"',`cf4`='"+table.getValueAt(i, 1)+"',`sp4`='"+table.getValueAt(i, 2)+"',`barcode10`='"+table.getValueAt(i, 3)+"',`barcode11`='"+table.getValueAt(i, 4)+"',`barcode12`='"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==4)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 query23+=",`unit5`='"+table.getValueAt(i, 0)+"',`cf5`='"+table.getValueAt(i, 1)+"',`sp5`='"+table.getValueAt(i, 2)+"',`barcode13`='"+table.getValueAt(i, 3)+"',`barcode14`='"+table.getValueAt(i, 4)+"',`barcode15`='"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==5)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 query23+=",`unit6`='"+table.getValueAt(i, 0)+"',`cf6`='"+table.getValueAt(i, 1)+"',`sp6`='"+table.getValueAt(i, 2)+"',`barcode16`='"+table.getValueAt(i, 3)+"',`barcode17`='"+table.getValueAt(i, 4)+"',`barcode18`='"+table.getValueAt(i, 5)+"'";
										 }
										}
									 if(!image.equals(""))
									 {
										 File configFile = new File("C:/MasterRetails/config.properties");
										FileReader reader = new FileReader(configFile);
									    Properties props = new Properties();
									    props.load(reader);
									    String imageurl = props.getProperty("image");
									    reader.close();
										Path temp = Files.copy(Paths.get(image),Paths.get("\\"+imageurl+"\\"+code+".jpg") , StandardCopyOption.REPLACE_EXISTING);
									    if(temp != null) 
								        { 
									    	String query="update "+company+".mr_item_master set openitem_qty='"+Open_Qty+"',commited_qty='"+not_disc+"',item_vareint='"+item_vareint+"',fore_color_code='"+fore_sel_color+"',color_code='"+sel_color+"',fast_item='"+fast_item+"',Open_Item='"+openitem+"',vendor_code='"+textField_5.getText()+"',vendor_name='"+textField_4.getText()+"',item_name='"+name+"',total_qty='"+qty+"',description1='"+desc1+"',description2='"+desc2+"',item_details='"+itm_dtls+"',department='"+dept+"',sub_department='"+sub_dept+"',category='"+cat+"',sub_category='"+sub_cat+"',item_type='"+type+"',item_cost='"+itm_cst+"',item_lastcost='"+lst_cst+"',item_stdcost='"+std_cst+"',modified_date='"+formattedDate+"',sp_include_tax='"+sp_include_tax+"',po_inclusive_tax='"+po_include_tax+"',deliItemsweighing='"+deliItemsweighing+"',parentKitItem='"+parentKitItem+"',allowDiscount='"+allowDiscount+"',disc_perc='"+disc_perct+"',activeForSale='"+activeForSale+"',activeForPurchase='"+activeForPurchase+"',Onpromo='"+Onpromo+"',exp_days='"+exp_days+"',weighing_sts='"+weight_stats+"',start_date='"+starting_date+"',enddate='"+endingdate+"',min_order='"+min_ordr+"',acc_grp='"+acc_grp+"',acc_code='"+acc_code+"',open_qty='"+openqty+"',item_image='\\"+imageurl+"\\"+code+".jpg',input_tax='"+input_tax+"',output_tax='"+output_tax+"',input_tax_rate='"+input_tax_rate+"',output_tax_rate='"+output_tax_rate+"' "+query23+" where item_code='"+code+"' ";
											statement.executeUpdate(query);
											String query1="select ref_item_id from "+company+".mr_vendor_items where ref_vendor_id='"+textField_5.getText()+"' and ref_item_id='"+code+"' ";
											Statement statement55=con.createStatement();  
											ResultSet resultSet55 = statement55.executeQuery(query1);
											if(!resultSet55.next())
											{
												String del="insert into "+company+".mr_vendor_items (`ref_vendor_id`, `ref_item_id`, `created_date`) values('"+textField_5.getText()+"','"+code+"','"+formattedDate+"') ";
												statement55.executeUpdate(del);
											}
								        } 
								        else
								        { 
								        	JOptionPane.showMessageDialog(frame, "<html>Image Uploading Failed, Please Try Again<br>›‘·  Õ„Ì· «·’Ê—…° Ì—ÃÏ «·„Õ«Ê·… „—… √Œ—Ï</html>","Image Uploading",JOptionPane.ERROR_MESSAGE);
								        }  
									 }
									 else
									 {
										 String query="update "+company+".mr_item_master set openitem_qty='"+Open_Qty+"',commited_qty='"+not_disc+"',item_vareint='"+item_vareint+"',fore_color_code='"+fore_sel_color+"',color_code='"+sel_color+"',fast_item='"+fast_item+"',Open_Item='"+openitem+"',vendor_code='"+textField_5.getText()+"',vendor_name='"+textField_4.getText()+"',item_name='"+name+"',description1='"+desc1+"',total_qty='"+qty+"',description2='"+desc2+"',item_details='"+itm_dtls+"',department='"+dept+"',sub_department='"+sub_dept+"',category='"+cat+"',sub_category='"+sub_cat+"',item_type='"+type+"',item_cost='"+itm_cst+"',item_lastcost='"+lst_cst+"',item_stdcost='"+std_cst+"',modified_date='"+formattedDate+"',sp_include_tax='"+sp_include_tax+"',po_inclusive_tax='"+po_include_tax+"',deliItemsweighing='"+deliItemsweighing+"',parentKitItem='"+parentKitItem+"',allowDiscount='"+allowDiscount+"',disc_perc='"+disc_perct+"',activeForSale='"+activeForSale+"',activeForPurchase='"+activeForPurchase+"',Onpromo='"+Onpromo+"',exp_days='"+exp_days+"',weighing_sts='"+weight_stats+"',start_date='"+starting_date+"',enddate='"+endingdate+"',min_order='"+min_ordr+"',acc_grp='"+acc_grp+"',acc_code='"+acc_code+"',open_qty='"+openqty+"',item_image='"+textField_2.getText()+"',input_tax='"+input_tax+"',output_tax='"+output_tax+"',input_tax_rate='"+input_tax_rate+"',output_tax_rate='"+output_tax_rate+"' "+query23+" where item_code='"+code+"' ";
										 statement.executeUpdate(query);
										 String query1="select ref_item_id from "+company+".mr_vendor_items where ref_vendor_id='"+textField_5.getText()+"' and ref_item_id='"+code+"' ";
										 Statement statement55=con.createStatement();  
										 ResultSet resultSet55 = statement55.executeQuery(query1);
										 if(!resultSet55.next())
										 {
											String del="insert into "+company+".mr_vendor_items (`ref_vendor_id`, `ref_item_id`, `created_date`) values('"+textField_5.getText()+"','"+code+"','"+formattedDate+"') ";
											statement55.executeUpdate(del);
										 }
									 }
									 
									 	
									resultSet = statement.executeQuery("select * from "+company+".mr_tmp_item_master where item_code='"+code+"' ");
									if(resultSet.next())
									{
										String query="delete from "+company+".mr_tmp_item_master where item_code='"+code+"' ";
										 statement.executeUpdate(query);
									}
									 for (int i = 0; i < table.getRowCount(); i++)
										{
											for (int x = 0; x < table.getColumnCount(); x++)
											{
												if(!table.getValueAt(i, x).equals(""))
												{
													if(x==3)
													{
														String chkbrcde=table.getValueAt(i, x).toString();
														if(chkbrcde.equals(""))
														{
															chkbrcde=code+"03";;
														}
														String sp1="";
														if(table.getValueAt(i, 2).toString().equals(""))
														{
															sp1="0";
														}
														else
														{
															sp1=table.getValueAt(i, 2).toString();
														}
														String query="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`, `type`,`input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty) "
																+ "VALUES('"+code+"','"+name+"','"+chkbrcde+"','"+cat+"','"+dept+"','"+table.getValueAt(i, 0)+"','"+sp1+"','"+output_tax+"','"+sp_include_tax+"','"+itm_cst+"','"+qty+"','"+type+"','"+input_tax+"','"+po_include_tax+"','"+activeForSale+"','"+activeForPurchase+"','"+table.getValueAt(i, 1)+"','"+input_tax_rate+"','"+output_tax_rate+"','"+weight_stats+"','"+sub_cat+"','"+sub_dept+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"')";
														statement.execute(query);
													}
													if(x==4)
													{
														String chkbrcde=table.getValueAt(i, x).toString();
														if(chkbrcde.equals(""))
														{
															chkbrcde=code;
														}
														String query="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`, `type`,`input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty) "
																+ "VALUES('"+code+"','"+name+"','"+chkbrcde+"','"+cat+"','"+dept+"','"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 2)+"','"+output_tax+"','"+sp_include_tax+"','"+itm_cst+"','"+qty+"','"+type+"','"+input_tax+"','"+po_include_tax+"','"+activeForSale+"','"+activeForPurchase+"','"+table.getValueAt(i, 1)+"','"+input_tax_rate+"','"+output_tax_rate+"','"+weight_stats+"','"+sub_cat+"','"+sub_dept+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"')";
														statement.execute(query);
													}
													if(x==5)
													{
														String chkbrcde=table.getValueAt(i, x).toString();
														if(chkbrcde.equals(""))
														{
															chkbrcde=code;
														}
														String query="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`, `type`,`input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty) "
																+ "VALUES('"+code+"','"+name+"','"+chkbrcde+"','"+cat+"','"+dept+"','"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 2)+"','"+output_tax+"','"+sp_include_tax+"','"+itm_cst+"','"+qty+"','"+type+"','"+input_tax+"','"+po_include_tax+"','"+activeForSale+"','"+activeForPurchase+"','"+table.getValueAt(i, 1)+"','"+input_tax_rate+"','"+output_tax_rate+"','"+weight_stats+"','"+sub_cat+"','"+sub_dept+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"')";
														statement.execute(query);
													}
												}
												else
												{
													if(!code.equals("NEW"))
													{
														if(x==3 && i==0)
														{
															String chkbrcde=code+"03";
															String query="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`, `type`,`input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty) "
																	+ "VALUES('"+code+"','"+name+"','"+chkbrcde+"','"+cat+"','"+dept+"','"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 2)+"','"+output_tax+"','"+sp_include_tax+"','"+itm_cst+"','"+qty+"','"+type+"','"+input_tax+"','"+po_include_tax+"','"+activeForSale+"','"+activeForPurchase+"','"+table.getValueAt(i, 1)+"','"+input_tax_rate+"','"+output_tax_rate+"','"+weight_stats+"','"+sub_cat+"','"+sub_dept+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"')";
															statement.execute(query);
														}
													}
												}
											}
										}
									 	if(chckbxOn.isSelected() && chckbxAllowDiscount.isSelected())
										{
									 		String remove_dup="delete from "+company+".mr_promotion where  itemcode='"+code+"'";
											statement.executeUpdate(remove_dup);
											String query="INSERT INTO "+company+".mr_promotion(`itemcode`, `barcode`, `itemname`, `dept`, `sub_dept`, `category`, `sub_cat`, `percentage`, `from_date`, `to_date`, `promo_type`, `created_date`, `modified_date`) "
													+ "VALUES('"+code+"','"+table.getValueAt(0, 3)+"','"+name+"','"+dept+"','"+sub_dept+"','"+cat+"','"+sub_cat+"','"+disc_perct+"','"+startdate+"','"+enddate+"','Item Wise','"+formattedDate+"','"+formattedDate+"') ";
											statement.execute(query);
										}
									 	JOptionPane.showMessageDialog(frame, "<html>Saved Successfully<br> „ «·Õ›Ÿ »‰Ã«Õ</html>");
										get_itemdtls_bsd_code(company, itemcode,decimal);
										txtItemcode.requestFocus();
										table.requestFocus();
										table.changeSelection(7, 2, false, false);
								}
								else
								{
									for (int i = 0; i < table.getRowCount(); i++) {
										if(i==0)
										 {
											String bcde1="";
											if(table.getValueAt(i, 3).toString().equals(""))
											{
												bcde1=code+"03";
											}
											else
											{
												bcde1=table.getValueAt(i, 3).toString();
											}
											String sp1="";
											if(table.getValueAt(i, 2).toString().equals(""))
											{
												sp1="0";
											}
											else
											{
												sp1=table.getValueAt(i, 2).toString();
											}
											if(table.getValueAt(i, 1).toString().equals(""))
											{
												if(!sp1.equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
												{
													JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
													return;
												}
											}
											 queryhd+=",`unit1`, `cf1`, `sp1`, `barcode1`, `barcode2`, `barcode3`";
											 query23+=",'"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 1)+"','"+sp1+"','"+bcde1+"','"+table.getValueAt(i, 4)+"','"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==1)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 queryhd+=",`unit2`, `cf2`, `sp2`, `barcode4`, `barcode5`, `barcode6`";
											 query23+=",'"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 1)+"','"+table.getValueAt(i, 2)+"','"+table.getValueAt(i, 3)+"','"+table.getValueAt(i, 4)+"','"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==2)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 queryhd+=",`unit3`, `cf3`, `sp3`, `barcode7`, `barcode8`, `barcode9`";
											 query23+=",'"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 1)+"','"+table.getValueAt(i, 2)+"','"+table.getValueAt(i, 3)+"','"+table.getValueAt(i, 4)+"','"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==3)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 queryhd+=",`unit4`, `cf4`, `sp4`, `barcode10`,`barcode11`, `barcode12`";
											 query23+=",'"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 1)+"','"+table.getValueAt(i, 2)+"','"+table.getValueAt(i, 3)+"','"+table.getValueAt(i, 4)+"','"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==4)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 queryhd+=",`unit5`, `cf5`, `sp5`, `barcode13`, `barcode14`, `barcode15`";
											 query23+=",'"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 1)+"','"+table.getValueAt(i, 2)+"','"+table.getValueAt(i, 3)+"','"+table.getValueAt(i, 4)+"','"+table.getValueAt(i, 5)+"'";
										 }
										 else if(i==5)
										 {
											 if(table.getValueAt(i, 1).toString().equals(""))
												{
													if(!table.getValueAt(i, 2).toString().equals("") || !table.getValueAt(i, 3).toString().equals("") || !table.getValueAt(i, 4).toString().equals("") || !table.getValueAt(i, 5).toString().equals(""))
													{
														JOptionPane.showMessageDialog(frame, "<html>CF is empty, Enter Some Values<br>⁄«„· «· ÕÊÌ· ›«—€° √œŒ· »⁄÷ «·ﬁÌ„</html>");
														return;
													}
												}
											 queryhd+=",`unit6`,`cf6`, `sp6`, `barcode16`, `barcode17`, `barcode18`";
											 query23+=",'"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 1)+"','"+table.getValueAt(i, 2)+"','"+table.getValueAt(i, 3)+"','"+table.getValueAt(i, 4)+"','"+table.getValueAt(i, 5)+"'";
										 }
									}
									if(!image.equals(""))
									{
										File configFile = new File("C:/MasterRetails/config.properties");
										FileReader reader = new FileReader(configFile);
									    Properties props = new Properties();
									    props.load(reader);
									    String imageurl = props.getProperty("image");
									    reader.close();
									    Path temp = Files.copy(Paths.get(image),Paths.get("\\"+imageurl+"\\"+code+".jpg") , StandardCopyOption.REPLACE_EXISTING);
									    if(temp == null) 
								        { 
									    	JOptionPane.showMessageDialog(frame, "<html>Image Uploading Failed, Please Try Again<br>›‘·  Õ„Ì· «·’Ê—…° Ì—ÃÏ «·„Õ«Ê·… „—… √Œ—Ï</html>","Image Uploading",JOptionPane.ERROR_MESSAGE);
								        } 
									    else
									    {
									    	String query="insert into "+company+".mr_item_master( `item_code`, `item_name`, `description1`, `description2`, `item_details`, `department`, `sub_department`, `category`, `sub_category`, `item_type`, `item_cost`, `item_lastcost`, `item_stdcost`, `input_tax`, `output_tax`, `sp_include_tax`, `po_inclusive_tax`,`deliItemsweighing`, `exp_days`, `weighing_sts`, `parentKitItem`, `allowDiscount`, `disc_perc`, `activeForSale`, `activeForPurchase`, `Onpromo`, `start_date`,`enddate`, `min_order`, `acc_grp`,acc_code, `open_qty`, `created_date`, `modified_date`,total_qty,input_tax_rate,output_tax_rate,item_image,vendor_code,vendor_name,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty "+queryhd+") VALUES('"+code+"','"+name+"','"+desc1+"','"+desc2+"','"+itm_dtls+"','"+dept+"','"+sub_dept+"','"+cat+"','"+sub_cat+"','"+type+"','"+itm_cst+"','"+lst_cst+"','"+std_cst+"','"+input_tax+"','"+output_tax+"','"+sp_include_tax+"','"+po_include_tax+"','"+deliItemsweighing+"','"+exp_days+"','"+weight_stats+"','"+parentKitItem+"','"+allowDiscount+"','"+disc_perct+"','"+activeForSale+"','"+activeForPurchase+"','"+Onpromo+"','"+starting_date+"','"+endingdate+"','"+min_ordr+"','"+acc_grp+"','"+acc_code+"','"+openqty+"','"+formattedDate+"','"+formattedDate+"','"+qty+"','"+input_tax_rate+"','"+output_tax_rate+"','\\"+imageurl+"\\"+code+".jpg','"+textField_5.getText()+"','"+textField_4.getText()+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"'  "+query23+")";
											statement.execute(query);
											
											String query1="select ref_item_id from "+company+".mr_vendor_items where ref_vendor_id='"+textField_5.getText()+"' and ref_item_id='"+code+"' ";
											Statement statement55=con.createStatement();  
											ResultSet resultSet55 = statement55.executeQuery(query1);
											if(!resultSet55.next())
											{
												String del="insert into "+company+".mr_vendor_items (`ref_vendor_id`, `ref_item_id`, `created_date`) values('"+textField_5.getText()+"','"+code+"','"+formattedDate+"') ";
												statement55.executeUpdate(del);
											}
									    }
									}
									else {
										String query="insert into "+company+".mr_item_master( `item_code`, `item_name`, `description1`, `description2`, `item_details`, `department`, `sub_department`, `category`, `sub_category`, `item_type`, `item_cost`, `item_lastcost`, `item_stdcost`, `input_tax`, `output_tax`, `sp_include_tax`, `po_inclusive_tax`,`deliItemsweighing`, `exp_days`, `weighing_sts`, `parentKitItem`, `allowDiscount`, `disc_perc`, `activeForSale`, `activeForPurchase`, `Onpromo`, `start_date`,`enddate`, `min_order`, `acc_grp`,acc_code, `open_qty`, `created_date`, `modified_date`,total_qty,input_tax_rate,output_tax_rate,item_image,vendor_code,vendor_name,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty "+queryhd+") VALUES('"+code+"','"+name+"','"+desc1+"','"+desc2+"','"+itm_dtls+"','"+dept+"','"+sub_dept+"','"+cat+"','"+sub_cat+"','"+type+"','"+itm_cst+"','"+lst_cst+"','"+std_cst+"','"+input_tax+"','"+output_tax+"','"+sp_include_tax+"','"+po_include_tax+"','"+deliItemsweighing+"','"+exp_days+"','"+weight_stats+"','"+parentKitItem+"','"+allowDiscount+"','"+disc_perct+"','"+activeForSale+"','"+activeForPurchase+"','"+Onpromo+"','"+starting_date+"','"+endingdate+"','"+min_ordr+"','"+acc_grp+"','"+acc_code+"','"+openqty+"','"+formattedDate+"','"+formattedDate+"','"+qty+"','"+input_tax_rate+"','"+output_tax_rate+"','"+textField_2.getText()+"','"+textField_5.getText()+"','"+textField_4.getText()+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"'  "+query23+")";
										statement.execute(query);
										String query1="select ref_item_id from "+company+".mr_vendor_items where ref_vendor_id='"+textField_5.getText()+"' and ref_item_id='"+code+"' ";
										Statement statement55=con.createStatement();  
										ResultSet resultSet55 = statement55.executeQuery(query1);
										if(!resultSet55.next())
										{
											String del="insert into "+company+".mr_vendor_items (`ref_vendor_id`, `ref_item_id`, `created_date`) values('"+textField_5.getText()+"','"+code+"','"+formattedDate+"') ";
											statement55.executeUpdate(del);
										}
									}
									for (int i = 0; i < table.getRowCount(); i++)
									{
										for (int x = 0; x < table.getColumnCount(); x++)
										{
											if(!table.getValueAt(i, x).equals(""))
											{
												if(x==3)
												{
													String chkbrcde=table.getValueAt(i, x).toString();
													if(chkbrcde.equals(""))
													{
														chkbrcde=code+"03";;
													}
													String sp1="";
													if(table.getValueAt(i, 2).toString().equals(""))
													{
														sp1="0";
													}
													else
													{
														sp1=table.getValueAt(i, 2).toString();
													}
													String query="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`, `type`,`input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty) "
															+ "VALUES('"+code+"','"+name+"','"+chkbrcde+"','"+cat+"','"+dept+"','"+table.getValueAt(i, 0)+"','"+sp1+"','"+output_tax+"','"+sp_include_tax+"','"+itm_cst+"','"+qty+"','"+type+"','"+input_tax+"','"+po_include_tax+"','"+activeForSale+"','"+activeForPurchase+"','"+table.getValueAt(i, 1)+"','"+input_tax_rate+"','"+output_tax_rate+"','"+weight_stats+"','"+sub_cat+"','"+sub_dept+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"')";
													statement.execute(query);
												}
												if(x==4)
												{
													String chkbrcde=table.getValueAt(i, x).toString();
													if(chkbrcde.equals(""))
													{
														chkbrcde=code;
													}
													String query="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`, `type`,`input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty) "
															+ "VALUES('"+code+"','"+name+"','"+chkbrcde+"','"+cat+"','"+dept+"','"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 2)+"','"+output_tax+"','"+sp_include_tax+"','"+itm_cst+"','"+qty+"','"+type+"','"+input_tax+"','"+po_include_tax+"','"+activeForSale+"','"+activeForPurchase+"','"+table.getValueAt(i, 1)+"','"+input_tax_rate+"','"+output_tax_rate+"','"+weight_stats+"','"+sub_cat+"','"+sub_dept+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"')";
													statement.execute(query);
												}
												if(x==5)
												{
													String chkbrcde=table.getValueAt(i, x).toString();
													if(chkbrcde.equals(""))
													{
														chkbrcde=code;
													}
													String query="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`, `type`,`input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty) "
															+ "VALUES('"+code+"','"+name+"','"+chkbrcde+"','"+cat+"','"+dept+"','"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 2)+"','"+output_tax+"','"+sp_include_tax+"','"+itm_cst+"','"+qty+"','"+type+"','"+input_tax+"','"+po_include_tax+"','"+activeForSale+"','"+activeForPurchase+"','"+table.getValueAt(i, 1)+"','"+input_tax_rate+"','"+output_tax_rate+"','"+weight_stats+"','"+sub_cat+"','"+sub_dept+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"')";
													statement.execute(query);
												}
											}
											else
											{
												if(!code.equals("NEW"))
												{
													if(x==3 && i==0)
													{
														String query="insert into "+company+".mr_tmp_item_master ( `item_code`, `item_name`, `barcode`, `category`, `department`, `unit`, `sp`, `output_tax`, `sp_include_tax`, `cost`, `qty`, `type`,`input_tax`, `po_inclusive_tax`, `activeForSale`, `activeForPurchase`,cf,input_tax_rate,output_tax_rate,weighing_sts,sub_cat,sub_dept,fast_item,Open_Item,color_code,fore_color_code,item_vareint,commited_qty,openitem_qty) "
																+ "VALUES('"+code+"','"+name+"','"+code+"','"+cat+"','"+dept+"','"+table.getValueAt(i, 0)+"','"+table.getValueAt(i, 2)+"','"+output_tax+"','"+sp_include_tax+"','"+itm_cst+"','"+qty+"','"+type+"','"+input_tax+"','"+po_include_tax+"','"+activeForSale+"','"+activeForPurchase+"','"+table.getValueAt(i, 1)+"','"+input_tax_rate+"','"+output_tax_rate+"','"+weight_stats+"','"+sub_cat+"','"+sub_dept+"','"+fast_item+"','"+openitem+"','"+sel_color+"','"+fore_sel_color+"','"+item_vareint+"','"+not_disc+"','"+Open_Qty+"')";
														statement.execute(query);
													}
												}
											}
										}
									}
									if(chckbxOn.isSelected() && chckbxAllowDiscount.isSelected())
									{
										String remove_dup="delete from "+company+".mr_promotion where  itemcode='"+code+"'";
										statement.executeUpdate(remove_dup);
										String query="INSERT INTO "+company+".mr_promotion(`itemcode`, `barcode`, `itemname`, `dept`, `sub_dept`, `category`, `sub_cat`, `percentage`, `from_date`, `to_date`, `promo_type`, `created_date`, `modified_date`) "
												+ "VALUES('"+code+"','"+table.getValueAt(0, 3)+"','"+name+"','"+dept+"','"+sub_dept+"','"+cat+"','"+sub_cat+"','"+disc_perct+"','"+startdate+"','"+enddate+"','Item Wise','"+formattedDate+"','"+formattedDate+"') ";
										statement.execute(query);
									}
									JOptionPane.showMessageDialog(frame, "<html>Saved Successfully<br> „ «·Õ›Ÿ »‰Ã«Õ</html>");
									get_itemdtls_bsd_code(company, code,decimal);
									txtItemcode.requestFocus();
								}
							}
							if(openqty.equals("1"))
							 {
								 resultSet = statement.executeQuery("select * from "+company+".mr_item_open_qty where item_code='"+code+"' ");
									if(!resultSet.next())
									{
										String query="insert into "+company+".mr_item_open_qty (item_code,created_date,item_name) values('"+code+"','"+formattedDate+"','"+name+"')  ";
										 statement.executeUpdate(query);
									}
							 }
							 else
							 {
								 ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_item_open_qty where item_code='"+code+"' ");
									if(resultSet2.next())
									{
										String query="delete from "+company+".mr_item_open_qty where item_code='"+code+"' ";
										 statement.executeUpdate(query);
									}
							 }
						}
						con.close();
					}
				} catch (Exception e) {
					frame.setAlwaysOnTop(false);
					JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
					frame.setAlwaysOnTop(true);
				}
				
				
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setIcon(new ImageIcon(save));
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(911, 2, 100, 30);
		panel_3.add(menuItem_2);
		
		JButton btnItemHistory = new JButton("Item History");
		if(language.equals("Arabic"))
		{
			btnItemHistory.setText(" «—ÌŒ «·⁄‰’—");
		}
		btnItemHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtItemcode.getText().equals("") || txtItemcode.getText().equals("NEW"))
				{
					JOptionPane.showMessageDialog(frame, "<html>Select or Choose One item<br>Õœœ √Ê «Œ — ⁄‰’—« Ê«Õœ«</html>");
				}
				else
				{
					try {
						String kit="0";
						if(chckbxParentKit.isSelected())
						{
							kit="1";
						}
						new Item_history(company, store, version, Integer.toString(decimal),txtItemcode.getText(),txtshrt_nme.getText(),kit);
					} catch (JSONException e) {
						JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
					}
				}
				
			}
		});
		btnItemHistory.setBounds(543, 7, 116, 25);
		panel_3.add(btnItemHistory);
		
		btnNewButton_2 = new JButton("Kit Items");
		if(language.equals("Arabic"))
		{
			btnNewButton_2.setText("⁄‰«’— «·„Ã„Ê⁄…");
		}
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setAlwaysOnTop(false);
				new inventory_master.kit_items(company,db_name,store,username,version,txtItemcode.getText(),txtshrt_nme.getText(),decimal, language);
			}
		});
		btnNewButton_2.setBounds(436, 7, 97, 25);
		btnNewButton_2.setVisible(false);
		panel_3.add(btnNewButton_2);
		
		JButton btnDeleteItem = new JButton("Delete Item");
		if(language.equals("Arabic"))
		{
			btnDeleteItem.setText("Õ–› «·⁄‰’—");
		}
		btnDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int a= JOptionPane.showConfirmDialog(frame, "<html>Do You Want to Delete This ITEM?<br> Â·  —Ìœ Õ–› Â–« «·⁄‰’—ø</html>","",JOptionPane.YES_NO_OPTION);
					if(a==0)
					{
						frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						Connection con=Dbsetup.dbconn.getConnection();
						Statement statement = con.createStatement();
						//po history
						String pohistory="select * from "+company+".mr_purchase_order_history where item_code='"+txtItemcode.getText()+"'";
						ResultSet rs = statement.executeQuery(pohistory);
			    		if(!rs.next()) {
			    			String po="select * from "+company+".mr_purchase_order where item_code='"+txtItemcode.getText()+"'";
							ResultSet rs1 = statement.executeQuery(po);
				    		if(!rs1.next()) {
				    			String saleshistory="select * from "+company+".mr_pos_settlement_history_items where item_code='"+txtItemcode.getText()+"'";
								ResultSet rs2 = statement.executeQuery(saleshistory);
					    		if(!rs2.next()) {
					    			String sales="select * from "+company+".mr_pos_settlement_cash_items where item_code='"+txtItemcode.getText()+"'";
									ResultSet rs3 = statement.executeQuery(sales);
						    		if(!rs3.next()) {
						    			String ad="select * from "+company+".mr_inventory_transfer_details where item_code='"+txtItemcode.getText()+"'";
										ResultSet rs4 = statement.executeQuery(ad);
							    		if(!rs4.next()) {
							    			String adhistory="select * from "+company+".mr_inventory_transfer_details_history where item_code='"+txtItemcode.getText()+"'";
											ResultSet rs5 = statement.executeQuery(adhistory);
								    		if(!rs5.next()) {
								    			String transferhist="select * from "+company+".mr_inv_trans_history where item_code='"+txtItemcode.getText()+"'";
												ResultSet rs6 = statement.executeQuery(transferhist);
									    		if(!rs6.next()) {
									    			String transfer="select * from "+company+".mr_inv_trans where item_code='"+txtItemcode.getText()+"'";
													ResultSet rs7 = statement.executeQuery(transfer);
										    		if(!rs7.next()) {
										    			String sd="select * from "+company+".mr_sd_lines where item_code='"+txtItemcode.getText()+"'";
														ResultSet rs8 = statement.executeQuery(sd);
											    		if(!rs8.next()) {
											    			String sdhistory="select * from "+company+".mr_sd_lines_history where item_code='"+txtItemcode.getText()+"'";
															ResultSet rs9 = statement.executeQuery(sdhistory);
												    		if(!rs9.next()) {
												    			String itemtmpdelete="delete from "+company+".mr_tmp_item_master where item_code='"+txtItemcode.getText()+"'";
												    			statement.executeUpdate(itemtmpdelete);
												    			String itemdelete="delete from "+company+".mr_item_master where item_code='"+txtItemcode.getText()+"'";
												    			statement.executeUpdate(itemdelete);
												    			txtItemcode.setText("");
												    			JOptionPane.showMessageDialog(frame, "<html>Deleted Succesfully<br> „ «·Õ–› »‰Ã«Õ</html>");
												    			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
												    		}	
												    		else
												    		{
												    			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
												    			JOptionPane.showMessageDialog(frame, "<html>Item Already in Transaction<br>«·⁄‰’— „ÊÃÊœ »«·›⁄· ›Ì «·„⁄«„·…</html>");
												    		}
											    		}
											    		else
											    		{
											    			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
											    			JOptionPane.showMessageDialog(frame, "<html>Item Already in Transaction<br>«·⁄‰’— „ÊÃÊœ »«·›⁄· ›Ì «·„⁄«„·…</html>");
											    		}
										    		}
										    		else
										    		{
										    			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
										    			JOptionPane.showMessageDialog(frame, "<html>Item Already in Transaction<br>«·⁄‰’— „ÊÃÊœ »«·›⁄· ›Ì «·„⁄«„·…</html>");
										    		}
									    		}
									    		else
									    		{
									    			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
									    			JOptionPane.showMessageDialog(frame, "<html>Item Already in Transaction<br>«·⁄‰’— „ÊÃÊœ »«·›⁄· ›Ì «·„⁄«„·…</html>");
									    		}
								    		}
								    		else
								    		{
								    			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								    			JOptionPane.showMessageDialog(frame, "<html>Item Already in Transaction<br>«·⁄‰’— „ÊÃÊœ »«·›⁄· ›Ì «·„⁄«„·…</html>");
								    		}
							    		}
							    		else
							    		{
							    			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
							    			JOptionPane.showMessageDialog(frame, "<html>Item Already in Transaction<br>«·⁄‰’— „ÊÃÊœ »«·›⁄· ›Ì «·„⁄«„·…</html>");
							    		}
						    		}
						    		else
						    		{
						    			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						    			JOptionPane.showMessageDialog(frame, "<html>Item Already in Transaction<br>«·⁄‰’— „ÊÃÊœ »«·›⁄· ›Ì «·„⁄«„·…</html>");
						    		}
					    		}
					    		else
					    		{
					    			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					    			JOptionPane.showMessageDialog(frame, "<html>Item Already in Transaction<br>«·⁄‰’— „ÊÃÊœ »«·›⁄· ›Ì «·„⁄«„·…</html>");
					    		}
				    		}
				    		else
				    		{
				    			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				    			JOptionPane.showMessageDialog(frame, "<html>Item Already in Transaction<br>«·⁄‰’— „ÊÃÊœ »«·›⁄· ›Ì «·„⁄«„·…</html>");
				    		}
			    		}
			    		else
			    		{
			    			frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			    			JOptionPane.showMessageDialog(frame, "<html>Item Already in Transaction<br>«·⁄‰’— „ÊÃÊœ »«·›⁄· ›Ì «·„⁄«„·…</html>");
			    		}	
					}
					
					
		    	}
				catch(Exception e)
				{
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		});
		btnDeleteItem.setBounds(308, 6, 116, 25);
		panel_3.add(btnDeleteItem);
		
		if(web_config.equals("1"))
		{
			JButton btnSyncItem = new JButton("Sync ITEM to Webserver");
			btnSyncItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if(txtItemcode.getText().equals(""))
						{
							JOptionPane.showMessageDialog(frame, "No Item Selected ·„ Ì „  ÕœÌœ √Ì ⁄‰’—");
						}
						else
						{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement = con.createStatement();
							
							Properties prop=new Properties();
							FileInputStream ip= new FileInputStream("C:/MasterRetails/web_config.properties");
							prop.load(ip);
							String web_url= prop.getProperty("web_url");
							String xerts_url= prop.getProperty("xerts_url");
							String web_image= prop.getProperty("web_image");
							ip.close();
							
							Connection con1 = DriverManager.getConnection(xerts_url);
							String SQL1 = "select ItemCost,ItemQtyOH,ItemQtyCommit from ItemBarcode inner join ItemMaster on ItemMaster.ItemCode=ItemBarcode.BarItemCode where BarItemBarcode='"+table.getValueAt(0, 3).toString()+"' ";
				    		Statement stmt1 = con1.createStatement();
				    		ResultSet rs1 = stmt1.executeQuery(SQL1);
				    		if (rs1.next()) {
				    			double qty=Double.parseDouble(rs1.getString("ItemQtyOH")); 
				    			double ItemQtyCommit=Double.parseDouble(rs1.getString("ItemQtyCommit"));
				    			double ttl_qty=qty-ItemQtyCommit;
				    			long qtyOH=Math.round(ttl_qty);
				    			String update="update "+company+".mr_item_master set fore_color_code='"+fore_sel_color+"',color_code='"+sel_color+"',vendor_code='"+textField_5.getText()+"',vendor_name='"+textField_4.getText()+"',item_cost='"+rs1.getString("ItemCost")+"',total_qty='"+qtyOH+"' where item_code='"+txtItemcode.getText()+"' ";
				    			statement.executeUpdate(update);
				    			
				    			String update_item="update "+company+".mr_tmp_item_master set fore_color_code='"+fore_sel_color+"',color_code='"+sel_color+"',cost='"+rs1.getString("ItemCost")+"',qty='"+qtyOH+"' where item_code='"+txtItemcode.getText()+"' ";
				    			statement.executeUpdate(update_item);
				    			
				    			LocalDateTime myDateObj = LocalDateTime.now();  
							    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");  
							    String formattedDate = myDateObj.format(myFormatObj); 
				    			
				    			Connection con2 = DriverManager.getConnection(web_url);
								String SQL3 = "select * from Product where Sku='"+table.getValueAt(0, 3).toString()+"' ";
					    		Statement stmt3 = con2.createStatement();
					    		String id="";
					    		ResultSet rs3 = stmt3.executeQuery(SQL3);
					    		if(rs3.next()) {
					    			id=rs3.getString("id");
					    			String ins_web="update Product set Name='"+txtshrt_nme.getText()+"',ShortDescription='"+txtshrt_nme.getText()+"',FullDescription='"+item_dtls.getText()+"',StockQuantity='"+qtyOH+"',Price='"+table.getValueAt(0, 2).toString()+"',ProductCost='"+rs1.getString("ItemCost")+"',UpdatedOnUtc='"+formattedDate+"' where Sku='"+table.getValueAt(0, 3).toString()+"' ";
					    			stmt3.executeUpdate(ins_web);
					    			
					    			
					    			String SQL5 = "select Id from Category where Name='"+comboBox_2.getSelectedItem().toString()+"' ";
					    			ResultSet rs5 = stmt3.executeQuery(SQL5);
						    		if(rs5.next()) {
						    			String ins_cat="insert into Product_Category_Mapping(ProductId,CategoryId,IsFeaturedProduct,DisplayOrder) values('"+id+"','"+rs5.getString("Id")+"','0','1'); ";
						    			stmt3.executeUpdate(ins_cat);
						    		}
						    		String SQL6 = "select Id from Category where Name='"+comboBox_3.getSelectedItem().toString()+"' ";
					    			ResultSet rs6 = stmt3.executeQuery(SQL6);
						    		if(rs6.next()) {
						    			String ins_cat="insert into Product_Category_Mapping(ProductId,CategoryId,IsFeaturedProduct,DisplayOrder) values('"+id+"','"+rs6.getString("Id")+"','0','1'); ";
						    			stmt3.executeUpdate(ins_cat);
						    		}
						    		String SQL7 = "select Id from Category where Name='"+comboBox_4.getSelectedItem().toString()+"' ";
					    			ResultSet rs7 = stmt3.executeQuery(SQL7);
						    		if(rs7.next()) {
						    			String ins_cat="insert into Product_Category_Mapping(ProductId,CategoryId,IsFeaturedProduct,DisplayOrder) values('"+id+"','"+rs7.getString("Id")+"','0','1'); ";
						    			stmt3.executeUpdate(ins_cat);
						    		}
						    		String SQL8 = "select Id from Category where Name='"+comboBox_5.getSelectedItem().toString()+"' ";
					    			ResultSet rs8 = stmt3.executeQuery(SQL8);
						    		if(rs8.next()) {
						    			String ins_cat="insert into Product_Category_Mapping(ProductId,CategoryId,IsFeaturedProduct,DisplayOrder) values('"+id+"','"+rs8.getString("Id")+"','0','1'); ";
						    			stmt3.executeUpdate(ins_cat);
						    		}
					    		}
					    		else
					    		{
					    			String ins_web="insert into Product(ProductTypeId,ParentGroupedProductId,VisibleIndividually,Name,ShortDescription,FullDescription,ProductTemplateId,VendorId,ShowOnHomepage,AllowCustomerReviews,ApprovedRatingSum,NotApprovedRatingSum,ApprovedTotalReviews,NotApprovedTotalReviews,SubjectToAcl,LimitedToStores,Sku,IsGiftCard,GiftCardTypeId,RequireOtherProducts,AutomaticallyAddRequiredProducts,IsDownload,DownloadId,UnlimitedDownloads,MaxNumberOfDownloads,DownloadActivationTypeId,HasSampleDownload,SampleDownloadId,HasUserAgreement,IsRecurring,RecurringCycleLength,RecurringCyclePeriodId,RecurringTotalCycles,IsRental,RentalPriceLength,RentalPricePeriodId,IsShipEnabled,IsFreeShipping,ShipSeparately,AdditionalShippingCharge,DeliveryDateId,IsTaxExempt,TaxCategoryId,IsTelecommunicationsOrBroadcastingOrElectronicServices,ManageInventoryMethodId,ProductAvailabilityRangeId,UseMultipleWarehouses,WarehouseId,StockQuantity,DisplayStockAvailability,DisplayStockQuantity,MinStockQuantity,LowStockActivityId,NotifyAdminForQuantityBelow,BackorderModeId,AllowBackInStockSubscriptions,OrderMinimumQuantity,OrderMaximumQuantity,AllowAddingOnlyExistingAttributeCombinations,NotReturnable,DisableBuyButton,DisableWishlistButton,AvailableForPreOrder,CallForPrice,Price,OldPrice,ProductCost,CustomerEntersPrice,MinimumCustomerEnteredPrice,MaximumCustomerEnteredPrice,BasepriceEnabled,BasepriceAmount,BasepriceUnitId,BasepriceBaseAmount,BasepriceBaseUnitId,MarkAsNew,HasTierPrices,HasDiscountsApplied,Weight,Length,Width,Height,DisplayOrder,Published,Deleted,CreatedOnUtc,UpdatedOnUtc,ShowOnHomepageClient)values('5','0','1','"+txtshrt_nme.getText()+"','"+txtshrt_nme.getText()+"','"+item_dtls.getText()+"','1','0','0','1','0','0','0','0','0','0','"+table.getValueAt(0, 3).toString()+"','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','0','0','0','0','0','0','0','0','0','0','0','"+qtyOH+"','0','0','0','0','0','0','0','1','10','0','0','0','0','0','0','"+table.getValueAt(0, 2).toString()+"','0','"+rs1.getString("ItemCost")+"','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','0','"+formattedDate+"','"+formattedDate+"','1' ) ";
					    			stmt3.executeUpdate(ins_web);
					    			
						    		String SQL4 = "select id from Product where Sku='"+table.getValueAt(0, 3).toString()+"' ";
						    		Statement stmt4 = con2.createStatement();
						    		ResultSet rs4 = stmt4.executeQuery(SQL4);
						    		if(rs4.next()) {
						    			id=rs4.getString("id");
						    			String SQL5 = "select Id from Category where Name='"+comboBox_2.getSelectedItem().toString()+"' ";
						    			ResultSet rs5 = stmt4.executeQuery(SQL5);
							    		if(rs5.next()) {
							    			String ins_cat="insert into Product_Category_Mapping(ProductId,CategoryId,IsFeaturedProduct,DisplayOrder) values('"+id+"','"+rs5.getString("Id")+"','0','1'); ";
							    			stmt3.executeUpdate(ins_cat);
							    		}
							    		String SQL6 = "select Id from Category where Name='"+comboBox_3.getSelectedItem().toString()+"' ";
						    			ResultSet rs6 = stmt4.executeQuery(SQL6);
							    		if(rs6.next()) {
							    			String ins_cat="insert into Product_Category_Mapping(ProductId,CategoryId,IsFeaturedProduct,DisplayOrder) values('"+id+"','"+rs6.getString("Id")+"','0','1'); ";
							    			stmt3.executeUpdate(ins_cat);
							    		}
							    		String SQL7 = "select Id from Category where Name='"+comboBox_4.getSelectedItem().toString()+"' ";
						    			ResultSet rs7 = stmt4.executeQuery(SQL7);
							    		if(rs7.next()) {
							    			String ins_cat="insert into Product_Category_Mapping(ProductId,CategoryId,IsFeaturedProduct,DisplayOrder) values('"+id+"','"+rs7.getString("Id")+"','0','1'); ";
							    			stmt3.executeUpdate(ins_cat);
							    		}
							    		String SQL8 = "select Id from Category where Name='"+comboBox_5.getSelectedItem().toString()+"' ";
						    			ResultSet rs8 = stmt4.executeQuery(SQL8);
							    		if(rs8.next()) {
							    			String ins_cat="insert into Product_Category_Mapping(ProductId,CategoryId,IsFeaturedProduct,DisplayOrder) values('"+id+"','"+rs8.getString("Id")+"','0','1'); ";
							    			stmt3.executeUpdate(ins_cat);
							    		}
						    			
						    			
						    		}
					    		}
					    		
					    		
				    			
				    			textField.setText(rs1.getString("ItemCost"));
				    			textField_8.setText(rs1.getString("ItemQtyOH"));
				    			
				    			
				    			int cofrm_bx = JOptionPane.showConfirmDialog(frame, "Do you Want to Add images to Webserver","Warning",JOptionPane.YES_NO_OPTION);
						        if(cofrm_bx==0)
						        {
						        	java.awt.Desktop.getDesktop().browse(java.net.URI.create(web_image+"Edit/"+id));
						        }
						        JOptionPane.showMessageDialog(frame, "Sync Successfull");
				    		}
				    		else
				    		{
				    			JOptionPane.showMessageDialog(frame, "Item Not Found In Xerts Database");
				    		}
						}
						btnNewButton_2.setVisible(false);
						chckbxParentKit.setSelected(false);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, e.getMessage());
					}
				}
			});
			btnSyncItem.setBounds(250, 7, 176, 25);
			panel_3.add(btnSyncItem);
		}
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 36, 1018, 2);
		frame.getContentPane().add(separator);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(243, 229, 216));
		panel_4.setBounds(0, 391, 1018, 179);
		frame.getContentPane().add(panel_4);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(new Color(243, 229, 216));
		scrollPane_1.setBounds(5, 28, 1003, 146);
		panel_4.add(scrollPane_1);
		modellist = new DefaultTableModel(){

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
		vtable = new JTable(modellist);
		if(language.equals("Arabic"))
		{
			modellist.addColumn(" «—ÌŒ √„— «·‘—«¡"); 
			modellist.addColumn("«”„ «·»«∆⁄"); 
			modellist.addColumn("—ﬁ„ √„— «·‘—«¡");
			modellist.addColumn("—ﬁ„ «·›« Ê—…"); 
			modellist.addColumn("—ﬁ„ «·Â« › «·„Õ„Ê·");
			modellist.addColumn("ÊÕœ…");
			modellist.addColumn("ﬂ„Ì… ÿ·» «·‘—«¡");
			modellist.addColumn(" ﬂ·›… √„— «·‘—«¡");
			modellist.addColumn("÷—Ì»… √„— «·‘—«¡");
			modellist.addColumn("≈Ã„«·Ì √„— «·‘—«¡");
		}
		else
		{
			modellist.addColumn("PO Date"); 
			modellist.addColumn("Vendor Name"); 
			modellist.addColumn("PO Number");
			modellist.addColumn("Invoice Number"); 
			modellist.addColumn("Mobile Number");
			modellist.addColumn("Unit");
			modellist.addColumn("PO Qty");
			modellist.addColumn("PO Cost");
			modellist.addColumn("PO VAT");
			modellist.addColumn("PO Total");
		}
		
		//modellist.addColumn("Total PO's");
		vtable.setFont(new Font("Serif", Font.BOLD, 15));
		vtable.setRowHeight(20);
		scrollPane_1.setViewportView(vtable);
		
		 int colmns=modellist.getColumnCount();
//       JTableHeader header=table.getTableHeader();
//       header.setPreferredSize(new Dimension(0,50));
		for (int i=0; i<colmns; i++) {
		  TableColumn column = vtable.getColumnModel().getColumn(i);
		  if (i==0) column.setPreferredWidth(70);
		  if (i==1) column.setPreferredWidth(250);
		  if (i==2) column.setPreferredWidth(100);
		  if (i==3) column.setPreferredWidth(80);
		  if (i==4) column.setPreferredWidth(60);
		  if (i==5) column.setPreferredWidth(50);
		  if (i==6) column.setPreferredWidth(40);
		  if (i==7) column.setPreferredWidth(40);
		  if (i==8) column.setPreferredWidth(40);
		  if (i==9) column.setPreferredWidth(40);
		}
		
		vtable.addMouseListener(new MouseAdapter()
	    {
			public void mouseClicked(MouseEvent e){
				String po=modellist.getValueAt(vtable.getSelectedRow(), 1).toString();
           	    if(!po.equals(""))
           	    {
           	    	 try {
           	    		frame.setAlwaysOnTop(false);
           	    		new purchase_master.vendor_po_list( company, username,store,version,txtItemcode.getText(),vtable.getValueAt(vtable.getSelectedRow(), 1).toString(), language,"item");
    					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    					menuItem.setEnabled(false);
           	    		
						
					} catch (JSONException e1) {
						frame.setAlwaysOnTop(false);
						JOptionPane.showMessageDialog( frame, e1.getMessage(),"JSONException error", JOptionPane.ERROR_MESSAGE );
						frame.setAlwaysOnTop(true);
					}
           	    }
			}
        });
		
		JLabel lblVendorDetails = new JLabel("Vendor Details");
		if(language.equals("Arabic"))
		{
			lblVendorDetails.setText(" ›«’Ì· «·»«∆⁄");
		}
		lblVendorDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVendorDetails.setBounds(10, 0, 126, 25);
		panel_4.add(lblVendorDetails);
		
		JLabel lblVendor = new JLabel("Vendor : ");
		if(language.equals("Arabic"))
		{
			lblVendor.setText("»«∆⁄ :");
		}
		lblVendor.setBounds(146, 6, 58, 14);
		panel_4.add(lblVendor);
		
		textField_4 = new JTextField("NIL");
		textField_4.setEditable(false);
		textField_4.setBounds(374, 3, 589, 20);
		panel_4.add(textField_4);
		textField_4.setColumns(10);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new purchase_master.vendor_list(company, "itemmaster", version,"2",username);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		button_2.setToolTipText("Item Code List");
		button_2.setBounds(973, 0, 35, 25);
		button_2.setIcon(new ImageIcon(serch));
		panel_4.add(button_2);
		
		textField_5 = new JTextField("NIL");
		textField_5.setEditable(false);
		textField_5.setBounds(209, 3, 155, 20);
		panel_4.add(textField_5);
		textField_5.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.PINK);
		panel_9.setBounds(0, 38, 1018, 25);
		frame.getContentPane().add(panel_9);
		panel_9.setLayout(null);
		
		JLabel lblQtyOnHand = new JLabel("Qty on Hand :");
		lblQtyOnHand.setHorizontalAlignment(SwingConstants.RIGHT);
		if(language.equals("Arabic"))
		{
			lblQtyOnHand.setText("«·ﬂ„Ì… «·„ Ê›—… :");
		}
		lblQtyOnHand.setBounds(10, 0, 137, 25);
		panel_9.add(lblQtyOnHand);
		lblQtyOnHand.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblQtySoldToday = new JLabel("Qty Sold Today :");
		lblQtySoldToday.setHorizontalAlignment(SwingConstants.RIGHT);
		if(language.equals("Arabic"))
		{
			lblQtySoldToday.setText("«·ﬂ„Ì… «·„»«⁄… «·ÌÊ„ :");
		}
		lblQtySoldToday.setBounds(339, 0, 206, 25);
		panel_9.add(lblQtySoldToday);
		lblQtySoldToday.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNetQty = new JLabel("Net Qty :");
		lblNetQty.setHorizontalAlignment(SwingConstants.RIGHT);
		if(language.equals("Arabic"))
		{
			lblNetQty.setText("’«›Ì «·ﬂ„Ì…:");
		}
		lblNetQty.setBounds(770, 0, 156, 25);
		panel_9.add(lblNetQty);
		lblNetQty.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		label_2 = new JLabel("0");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(157, 0, 93, 25);
		panel_9.add(label_2);
		
		label_3= new JLabel("0");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(557, 0, 93, 25);
		panel_9.add(label_3);
		
		label_4= new JLabel("0");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(925, 0, 93, 25);
		panel_9.add(label_4);
		
		get_inventory_controls(company,username);
		
		if(create_item.equals("0"))
		{
			txtItemcode.setEditable(false);
		}
		else
		{
			txtItemcode.setEditable(true);
		}
		if(edit_cost.equals("0"))
		{
			textField.setEditable(false);
		}
		else
		{
			textField.setEditable(true);
		}
		if(view_cost.equals("0"))
		{
			textField.setEditable(false);
			textField.setText("0");
		}
		else
		{
			txtItemcode.setEditable(true);
		}
		if(view_hist.equals("0"))
		{
			btnItemHistory.setEnabled(false);
		}
		else
		{
			btnItemHistory.setEnabled(true);
		}
		if(edit_qty.equals("0"))
		{
			textField_8.setEditable(false);
			textField_8.setText("0");
		}
		else
		{
			textField_8.setEditable(true);
		}
		txtItemcode.requestFocus();
		if(pagetype=="PO")
		{
			txtItemcode.setText("NEW");
			txtshrt_nme.requestFocus();
			//listdept.clear();
		}
		
		frame.setAlwaysOnTop(true);
		
		clickOnserch( btnNewButton, "play", KeyEvent.VK_F9,company,version,db_name,decimal,language );
	}
	public static void clickOnserch(final AbstractButton button, String actionName, int key,final String company, final String version, final String db_name, final int decimal,final String language)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6584888066678545910L;

			@Override
	        public void actionPerformed( ActionEvent e )
	        {
				try {
					Connection con=Dbsetup.dbconn.getConnection();
					new item_list_master(db_name,company, "Item_master", "", version,decimal,con,"",language);
				} catch (JSONException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    } );
	}
	private void get_inventory_controls(String company, String username) {
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			Statement statement1=con.createStatement(); 
			String query="select * from "+company+".mr_users where user_name='"+username+"' ";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next())
			{
				String query1="select * from "+company+".mr_user_inventorycontrol where ref_user_code='"+resultSet.getString("user_code")+"' ";
				ResultSet resultSet1 = statement1.executeQuery(query1);
				if(resultSet1.next())
				{
					create_item=resultSet1.getString("CanCreateNew");
					edit_sp=resultSet1.getString("EditSellingprice");
					edit_cost=resultSet1.getString("EditCostprice");
					view_cost=resultSet1.getString("ViewCostitm");
					view_hist=resultSet1.getString("ViewHistoryitem");
					edit_qty=resultSet1.getString("Changestatusitem");
				}
			}
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
		
	}

	private void get_chartofaccount(String company, String store) {
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			String query="select * from "+company+".mr_chart_accnt_mstr where acc_grp_name='STOCK' and acc_store='"+store+"' ";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				String a=resultSet.getString("acc_name");
				comboBox_9.addItem(a);
			}
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
		
	}

	public static ResultSet  get_item_dept( String company)
	{
		ResultSet resultSet = null;
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			resultSet = statement.executeQuery("select * from "+company+".mr_department ");
			while(resultSet.next())
			{
				comboBox_2.addItem(resultSet.getString("dept_name"));
			}
			con.close();
		}
		catch(Exception e){
			frame.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
			frame.setAlwaysOnTop(true);
		}
		
		return resultSet;
	}
	
	public static void  get_item_subdept(String company, String dept)
	{
		try{
			comboBox_3.removeAllItems();
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_sub_department where ref_dept_name='"+dept+"' ");
			while(resultSet.next())
			{
				comboBox_3.addItem(resultSet.getString("sub_dept_name"));
			}
			con.close();
		}
		catch(Exception e){
			frame.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
			frame.setAlwaysOnTop(true);
		}
	}
	public static void get_item_cat(String company)
	{
		ResultSet resultSet = null;
		try{
			
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			resultSet = statement.executeQuery("select * from "+company+".mr_category ");
			while(resultSet.next())
			{
				comboBox_4.addItem(resultSet.getString("cat_name"));
			}
			con.close();
			
		}
		catch(Exception e){
			frame.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
			frame.setAlwaysOnTop(true);
		}
		
	}
	public static void  get_item_subcat(String company, String cat)
	{
		try{
			comboBox_5.removeAllItems();
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_subcategory where ref_cat_name='"+cat+"' ");
			while(resultSet.next())
			{
				listsubcat.add(resultSet.getString("sub_cat_name"));
				comboBox_5.addItem(resultSet.getString("sub_cat_name"));
			}
			con.close();
		}
		catch(Exception e){
			frame.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
			frame.setAlwaysOnTop(true);
		}
	}
	public static void get_itemdtls_bsd_code(String company, String input,int decimal) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement4=con.createStatement();
			Statement statement44=con.createStatement();
			String qry4="select * from "+company+".mr_item_master where item_code='"+input+"' ";
			ResultSet resultSet4 = statement4.executeQuery(qry4);
			float sold_qty = 0;
			Statement statement1=con.createStatement();  
			Statement statement5=con.createStatement();  
			String test_item_code="";
    		String qry5="select * from "+company+".mr_tmp_item_master where item_code='"+input+"' or barcode='"+input+"' ";
			ResultSet resultSet55 = statement44.executeQuery(qry5);
			if(resultSet55.next())
			{
				test_item_code=resultSet55.getString("item_code");
			}
    		String query="select * from "+company+".mr_pos_settlement_cash_items where item_code='"+test_item_code+"' ";
        	ResultSet resultSet1 = statement1.executeQuery(query);
        	while(resultSet1.next())
        	{
        		sold_qty+=Double.parseDouble(resultSet1.getString("qty"))*Double.parseDouble(resultSet1.getString("cf"));
        	}
        	String query5="select * from "+company+".mr_sd_lines inner join "+company+".mr_sd_list on mr_sd_list.invoice_num=mr_sd_lines.ref_inv_num where mr_sd_list.status='POSTED' and item_code='"+test_item_code+"'";
        	ResultSet resultSet5 = statement5.executeQuery(query5);
        	while(resultSet5.next())
        	{
        		sold_qty+=Double.parseDouble(resultSet5.getString("qty"))*Double.parseDouble(resultSet5.getString("cf"));
        	}
        	boolean res=resultSet4.next();
        	if(res==true)
        	{
        		
        		Statement statement=con.createStatement();  
    			String qry="select * from "+company+".mr_item_master where item_code='"+input+"' ";
    			ResultSet resultSet = statement.executeQuery(qry);
        		while(resultSet.next())
    			{
    				txtItemcode.setText(resultSet.getString("item_code"));
    				txtshrt_nme.setText(resultSet.getString("item_name"));
    	        	txt_desc1.setText(resultSet.getString("description1"));
    	        	textArea_1.setText(resultSet.getString("description2"));
    	        	item_dtls.setText(resultSet.getString("item_details"));
    	        	
    	        	comboBox_2.setSelectedItem(resultSet.getString("department"));
    	        	comboBox_3.setSelectedItem(resultSet.getString("sub_department"));
    	        	comboBox_4.setSelectedItem(resultSet.getString("category"));
    	        	comboBox_5.setSelectedItem(resultSet.getString("sub_category"));
    	        	
    	        	comboBox_6.setSelectedItem(resultSet.getString("item_type"));
//    	        	if(resultSet.getString("item_type").equals("Kit Item") )
//    	        	{
//    	        		btnNewButton_2.setVisible(true);
//    	        	}
    	        	if(view_cost.equals("0"))
    	    		{
    	    			textField.setEditable(false);
    	    			textField.setText("0");
    	    			textField_3.setText("0");
        	        	textField_6.setText("0");
    	    		}
    	    		else
    	    		{
    	    			txtItemcode.setEditable(true);
    	    			textField.setText(resultSet.getString("item_cost"));
    	    			textField_3.setText(resultSet.getString("item_lastcost"));
        	        	textField_6.setText(resultSet.getString("item_stdcost"));
    	    		}
    	        	
    	        	textField_5.setText(resultSet.getString("vendor_code"));
    	        	textField_4.setText(resultSet.getString("vendor_name"));
    	        	
    	        	//int new_qty=Integer.parseInt(Integer.toString(sold_qty))+Integer.parseInt(resultSet.getString("total_qty"));
    	        	float ttl_qty=Float.parseFloat(resultSet.getString("total_qty"));
    	        	float net_qty=ttl_qty-Float.parseFloat(Float.toString(sold_qty));
    	        	
    	        	label_2.setText(resultSet.getString("total_qty"));
    	        	label_3.setText(Float.toString(sold_qty));
    	        	label_4.setText(Float.toString(net_qty));
    	        	
    	        	textField_8.setText(resultSet.getString("total_qty"));
    	        	textField_13.setText(resultSet.getString("min_order"));
    	        	
    	        	String dbimg=resultSet.getString("item_image");
    	        	if(dbimg!="null")
    	        	{
    	        		lblitemimage.setIcon(ResizeImage(dbimg));
    	        	}
    	        	else
    	        	{
    	        		lblitemimage.setIcon(ResizeImage(""));
    	        	}
    	        	textField_2.setText(dbimg);
    	        	
    	        	comboBox_7.setSelectedItem(resultSet.getString("input_tax"));
    	        	comboBox_8.setSelectedItem(resultSet.getString("output_tax"));
    	        	
    	        	button_3.setBackground(Color.decode(resultSet.getString("color_code")));
    	        	sel_color=resultSet.getString("color_code");
					button_5.setBackground(Color.decode(resultSet.getString("fore_color_code")));
					fore_sel_color=resultSet.getString("fore_color_code");
    	        	comboBox_7.setSelectedItem(resultSet.getString("input_tax"));
    	        	comboBox_8.setSelectedItem(resultSet.getString("output_tax"));
    	        	
    	        	if(resultSet.getString("item_vareint").equals("1"))
    	        	{
    	        		chckbxUnitUsedAs.setSelected(true);
    	        	}
    	        	else
    	        	{
    	        		chckbxUnitUsedAs.setSelected(false);
    	        	}
    	        	if(resultSet.getString("commited_qty").equals("1"))
    	        	{
    	        		chckbxDoNot.setSelected(true);
    	        	}
    	        	else
    	        	{
    	        		chckbxDoNot.setSelected(false);
    	        	}
    	        	if(resultSet.getString("sp_include_tax").equals("1"))
    	        	{
    	        		chckbxSalesPriceInclusive.setSelected(true);
    	        	}
    	        	else
    	        	{
    	        		chckbxSalesPriceInclusive.setSelected(false);
    	        	}
    	        	if(resultSet.getString("fast_item").equals("1"))
    	        	{
    	        		chckbxFastItem.setSelected(true);
    	        	}
    	        	else
    	        	{
    	        		chckbxFastItem.setSelected(false);
    	        	}
    	        	
    	        	if(resultSet.getString("Open_Item").equals("1"))
    	        	{
    	        		chckbxOpenItem.setSelected(true);
    	        	}
    	        	else
    	        	{
    	        		chckbxOpenItem.setSelected(false);
    	        	}
    	        	if(resultSet.getString("openitem_qty").equals("1"))
    	        	{
    	        		chckbxOpenQty.setSelected(true);
    	        	}
    	        	else
    	        	{
    	        		chckbxOpenQty.setSelected(false);
    	        	}
    	        	
    	        	if(resultSet.getString("po_inclusive_tax").equals("1"))
    	        	{
    	        		chckbxpoPriceInclusive.setSelected(true);
    	        	}
    	        	else
    	        	{
    	        		chckbxpoPriceInclusive.setSelected(false);
    	        	}
    	        	
    	        	if(resultSet.getString("deliItemsweighing").equals("1"))
    	        	{
    	        		chckbxDeliItemsweighing.setSelected(true);
    	        		textField_11.setText(resultSet.getString("exp_days"));
    	        		textField_11.setEditable(true);
    	        		comboBox_10.setSelectedItem(resultSet.getString("weighing_sts"));
    	        		comboBox_10.setEnabled(true);
    	        	}
    	        	else
    	        	{
    	        		chckbxDeliItemsweighing.setSelected(false);
    	        		textField_11.setEditable(false);
    	        		textField_11.setText("");
    	        		comboBox_10.setEnabled(false);
    	        	}
    	        	if(resultSet.getString("open_qty").equals("1"))
    	        	{
    	        		chckbxOpeningQty.setSelected(true);
    	        	}
    	        	else
    	        	{
    	        		chckbxOpeningQty.setSelected(false);
    	        	}
    	        	if(resultSet.getString("parentKitItem").equals("1"))
    	        	{
    	        		chckbxParentKit.setSelected(true);
    	        		btnNewButton_2.setVisible(true);
    	        	}
    	        	else
    	        	{
    	        		chckbxParentKit.setSelected(false);
    	        		btnNewButton_2.setVisible(false);
    	        	}
    	        	
    	        	if(resultSet.getString("activeForSale").equals("1"))
    	        	{
    	        		chckbxActiveForSale.setSelected(true);
    	        	}
    	        	else
    	        	{
    	        		chckbxActiveForSale.setSelected(false);
    	        	}
    	        	if(!resultSet.getString("acc_grp").equals(""))
    	        	{
    	        		comboBox_9.setSelectedItem(resultSet.getString("acc_grp"));
    	        	}
    	        	else
    	        	{
    	        		comboBox_9.setSelectedIndex(-1);
    	        	}
    	        	if(!resultSet.getString("acc_code").equals(""))
    	        	{
    	        		comboBox_11.setSelectedItem(resultSet.getString("acc_code"));
    	        	}
    	        	else
    	        	{
    	        		comboBox_11.setSelectedIndex(-1);
    	        	}
    	        	if(resultSet.getString("activeForPurchase").equals("1"))
    	        	{
    	        		chckbxActiveForPurchase.setSelected(true);
    	        	}
    	        	else
    	        	{
    	        		chckbxActiveForPurchase.setSelected(false);
    	        	}
    	        	if(resultSet.getString("Onpromo").equals("1"))
    	        	{
    	        		
    	        		java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("start_date"));
    					dateChooser.setDate(date1);
    					
    					java.util.Date date_end = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("enddate"));
    					dateChooser_1.setDate(date_end);
    					
    					
    					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    					Calendar cal = Calendar.getInstance();
    					cal.setTime(date1);
    					cal.add(Calendar.DAY_OF_MONTH, 1);  
    					String newDate = sdf.format(cal.getTime());
    					
    					Date exp_date=new SimpleDateFormat("yyyy-MM-dd").parse(newDate);
    					Date date = new Date();  
    					int day= date.compareTo(exp_date);
    					if(day == -1)
    					{
    						chckbxOn.setSelected(true);
    						dateChooser.setEnabled(true);
    						dateChooser_1.setEnabled(true);
    						chckbxAllowDiscount.setSelected(true);
    					}
    					else
    					{
    						chckbxOn.setSelected(false);
        	        		dateChooser.setEnabled(false);
        	        		dateChooser_1.setEnabled(false);
        	        		chckbxAllowDiscount.setSelected(false);
    					}
    				    
    				    
    				    
    				    
    					
    	        	}
    	        	else
    	        	{
    	        		chckbxOn.setSelected(false);
    	        		dateChooser.setEnabled(false);
    	        		dateChooser_1.setEnabled(false);
    	        	}
    	        	if(resultSet.getString("allowDiscount").equals("1"))
    	        	{
    	        		
    	        		textField_7.setText(resultSet.getString("disc_perc"));
    	        	}
    	        	else
    	        	{
    	        		
    	        		textField_7.setText("");
    	        	}
    	        	
    	        	int rowCount = model.getRowCount();
    	        	for (int i = rowCount - 1; i >= 0; i--) {
    					model.removeRow(i);
    				}
    	        	model.addRow(new Object[]{resultSet.getString("unit1"),resultSet.getString("cf1"), resultSet.getString("sp1"), resultSet.getString("barcode1"), resultSet.getString("barcode2"), resultSet.getString("barcode3")});
    	        	model.addRow(new Object[]{resultSet.getString("unit2"),resultSet.getString("cf2"), resultSet.getString("sp2"), resultSet.getString("barcode4"), resultSet.getString("barcode5"), resultSet.getString("barcode6")});
    	        	model.addRow(new Object[]{resultSet.getString("unit3"),resultSet.getString("cf3"), resultSet.getString("sp3"), resultSet.getString("barcode7"), resultSet.getString("barcode8"), resultSet.getString("barcode9")});
    	        	model.addRow(new Object[]{resultSet.getString("unit4"),resultSet.getString("cf4"), resultSet.getString("sp4"), resultSet.getString("barcode10"), resultSet.getString("barcode11"), resultSet.getString("barcode12")});
    	        	model.addRow(new Object[]{resultSet.getString("unit5"),resultSet.getString("cf5"), resultSet.getString("sp5"), resultSet.getString("barcode13"), resultSet.getString("barcode14"), resultSet.getString("barcode15")});
    	        	model.addRow(new Object[]{resultSet.getString("unit6"),resultSet.getString("cf6"), resultSet.getString("sp6"), resultSet.getString("barcode16"), resultSet.getString("barcode17"), resultSet.getString("barcode18")});
    	        	
    			}
        	}
        	else
        	{
        		Statement statement2=con.createStatement(); 
        		String query3="select item_code from "+company+".mr_item_master where '"+input+"' in (`barcode1`, `barcode2`, `barcode3`,  `barcode4`, `barcode5`, `barcode6`, `barcode7`, `barcode8`, `barcode9`, `barcode10`, `barcode11`, `barcode12`,  `barcode13`, `barcode14`, `barcode15`,  `barcode16`, `barcode17`, `barcode18`) ";
    			ResultSet resultSet2 = statement2.executeQuery(query3);
    			while(resultSet2.next())
    			{
    				Statement statement3=con.createStatement();  
        			ResultSet resultSet3 = statement3.executeQuery("select * from "+company+".mr_item_master where item_code='"+resultSet2.getString("item_code")+"' ");
        			while(resultSet3.next())
        			{
        				txtItemcode.setText(resultSet3.getString("item_code"));
        				txtshrt_nme.setText(resultSet3.getString("item_name"));
        	        	txt_desc1.setText(resultSet3.getString("description1"));
        	        	textArea_1.setText(resultSet3.getString("description2"));
        	        	item_dtls.setText(resultSet3.getString("item_details"));
        	        	
        	        	comboBox_2.setSelectedItem(resultSet3.getString("department"));
        	        	comboBox_3.setSelectedItem(resultSet3.getString("sub_department"));
        	        	comboBox_4.setSelectedItem(resultSet3.getString("category"));
        	        	comboBox_5.setSelectedItem(resultSet3.getString("sub_category"));
        	        	
        	        	
        	        	comboBox_6.setSelectedItem(resultSet3.getString("item_type"));
        	        	textField.setText(resultSet3.getString("item_cost"));
        	        	textField_3.setText(resultSet3.getString("item_lastcost"));
        	        	textField_6.setText(resultSet3.getString("item_stdcost"));
        	        	
        	        	textField_5.setText(resultSet3.getString("vendor_code"));
        	        	textField_4.setText(resultSet3.getString("vendor_name"));
        	        	
        	        	label_3.setText(Float.toString(sold_qty));
        	        	double new_qty=Double.parseDouble(resultSet3.getString("total_qty"));//Double.parseDouble(Float.toString(sold_qty))+Double.parseDouble(resultSet3.getString("total_qty"));
        	        	double net_qty=new_qty-Double.parseDouble(Float.toString(sold_qty));
        	        	label_4.setText(Double.toString(net_qty));
        	        	label_2.setText(Double.toString(new_qty));
        	        	textField_8.setText(resultSet3.getString("total_qty"));
        	        	textField_13.setText(resultSet3.getString("min_order"));
        	        	
        	        	String dbimg=resultSet3.getString("item_image");
        	        	if(dbimg!="null")
        	        	{
        	        		File configFile = new File("C:/MasterRetails/config.properties");
							FileReader reader = new FileReader(configFile);
						    Properties props = new Properties();
						    props.load(reader);
						    String image = props.getProperty("image");
						    reader.close();
							lblitemimage.setIcon(ResizeImage("\\"+image+"\\"+dbimg));
        	        	}
        	        	else
        	        	{
        	        		lblitemimage.setIcon(ResizeImage(""));
        	        	}
        	        	textField_2.setText(dbimg);
        	        	comboBox_7.setSelectedItem(resultSet3.getString("input_tax"));
        	        	comboBox_8.setSelectedItem(resultSet3.getString("output_tax"));
        	        	if(resultSet3.getString("sp_include_tax").equals("1"))
        	        	{
        	        		chckbxSalesPriceInclusive.setSelected(true);
        	        	}
        	        	else
        	        	{
        	        		chckbxSalesPriceInclusive.setSelected(false);
        	        	}
        	        	if(resultSet3.getString("po_inclusive_tax").equals("1"))
        	        	{
        	        		chckbxpoPriceInclusive.setSelected(true);
        	        	}
        	        	else
        	        	{
        	        		chckbxpoPriceInclusive.setSelected(false);
        	        	}
        	        	if(resultSet3.getString("deliItemsweighing").equals("1"))
        	        	{
        	        		chckbxDeliItemsweighing.setSelected(true);
        	        		textField_11.setText(resultSet3.getString("exp_days"));
        	        		textField_11.setEditable(true);
        	        		comboBox_10.setSelectedItem(resultSet3.getString("weighing_sts"));
        	        		comboBox_10.setEnabled(true);
        	        	}
        	        	else
        	        	{
        	        		chckbxDeliItemsweighing.setSelected(false);
        	        		textField_11.setEditable(false);
        	        		textField_11.setText("");
        	        		comboBox_10.setEnabled(false);
        	        	}
        	        	if(resultSet3.getString("open_qty").equals("1"))
        	        	{
        	        		chckbxOpeningQty.setSelected(true);
        	        	}
        	        	else
        	        	{
        	        		chckbxOpeningQty.setSelected(false);
        	        	}
        	        	
        	        	if(resultSet3.getString("openitem_qty").equals("1"))
        	        	{
        	        		chckbxOpenQty.setSelected(true);
        	        	}
        	        	else
        	        	{
        	        		chckbxOpenQty.setSelected(false);
        	        	}
        	        	if(resultSet3.getString("Open_Item").equals("1"))
        	        	{
        	        		chckbxOpenItem.setSelected(true);
        	        	}
        	        	else
        	        	{
        	        		chckbxOpenItem.setSelected(false);
        	        	}
        	        	if(resultSet3.getString("parentKitItem").equals("1"))
        	        	{
        	        		chckbxParentKit.setSelected(true);
        	        	}
        	        	else
        	        	{
        	        		chckbxParentKit.setSelected(false);
        	        	}
        	        	
        	        	if(resultSet3.getString("activeForSale").equals("1"))
        	        	{
        	        		chckbxActiveForSale.setSelected(true);
        	        	}
        	        	else
        	        	{
        	        		chckbxActiveForSale.setSelected(false);
        	        	}
        	        	if(!resultSet3.getString("acc_grp").equals(""))
        	        	{
        	        		comboBox_9.setSelectedItem(resultSet3.getString("acc_grp"));
        	        	}
        	        	else
        	        	{
        	        		comboBox_9.setSelectedIndex(-1);
        	        	}
        	        	if(!resultSet3.getString("acc_code").equals(""))
        	        	{
        	        		comboBox_11.setSelectedItem(resultSet3.getString("acc_code"));
        	        	}
        	        	else
        	        	{
        	        		comboBox_11.setSelectedIndex(-1);
        	        	}
        	        	if(resultSet3.getString("activeForPurchase").equals("1"))
        	        	{
        	        		chckbxActiveForPurchase.setSelected(true);
        	        	}
        	        	else
        	        	{
        	        		chckbxActiveForPurchase.setSelected(false);
        	        	}
        	        	if(resultSet3.getString("Onpromo").equals("1"))
        	        	{
        	        		
        	        		java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet3.getString("start_date"));
        					dateChooser.setDate(date1);
        					
        					java.util.Date date_end = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet3.getString("enddate"));
        					dateChooser_1.setDate(date_end);
        					
        					
        					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        					Calendar cal = Calendar.getInstance();
        					cal.setTime(date1);
        					cal.add(Calendar.DAY_OF_MONTH, 1);  
        					String newDate = sdf.format(cal.getTime());
        					
        					Date exp_date=new SimpleDateFormat("yyyy-MM-dd").parse(newDate);
        					Date date = new Date();  
        					int day= date.compareTo(exp_date);
        					if(day == -1)
        					{
        						chckbxOn.setSelected(true);
        						dateChooser.setEnabled(true);
        						dateChooser_1.setEnabled(true);
        						chckbxAllowDiscount.setSelected(true);
        					}
        					else
        					{
        						chckbxOn.setSelected(false);
            	        		dateChooser.setEnabled(false);
            	        		dateChooser_1.setEnabled(false);
            	        		chckbxAllowDiscount.setSelected(false);
        					}
        				    
        				    
        				    
        				    
        					
        	        	}
        	        	else
        	        	{
        	        		chckbxOn.setSelected(false);
        	        		dateChooser.setEnabled(false);
        	        		dateChooser_1.setEnabled(false);
        	        	}
        	        	if(resultSet3.getString("allowDiscount").equals("1"))
        	        	{
        	        		
        	        		textField_7.setText(resultSet3.getString("disc_perc"));
        	        	}
        	        	else
        	        	{
        	        		
        	        		textField_7.setText("");
        	        	}
        	        	
        	        	
        	        	int rowCount = model.getRowCount();
        				for (int i = rowCount - 1; i >= 0; i--) {
        					model.removeRow(i);
        				}
            	        	
        	        	model.addRow(new Object[]{resultSet3.getString("unit1"),resultSet3.getString("cf1"), resultSet3.getString("sp1"), resultSet3.getString("barcode1"), resultSet3.getString("barcode2"), resultSet3.getString("barcode3")});
        	        	model.addRow(new Object[]{resultSet3.getString("unit2"),resultSet3.getString("cf2"), resultSet3.getString("sp2"), resultSet3.getString("barcode4"), resultSet3.getString("barcode5"), resultSet3.getString("barcode6")});
        	        	model.addRow(new Object[]{resultSet3.getString("unit3"),resultSet3.getString("cf3"), resultSet3.getString("sp3"), resultSet3.getString("barcode7"), resultSet3.getString("barcode8"), resultSet3.getString("barcode9")});
        	        	model.addRow(new Object[]{resultSet3.getString("unit4"),resultSet3.getString("cf4"), resultSet3.getString("sp4"), resultSet3.getString("barcode10"), resultSet3.getString("barcode11"), resultSet3.getString("barcode12")});
        	        	model.addRow(new Object[]{resultSet3.getString("unit5"),resultSet3.getString("cf5"), resultSet3.getString("sp5"), resultSet3.getString("barcode13"), resultSet3.getString("barcode14"), resultSet3.getString("barcode15")});
        	        	model.addRow(new Object[]{resultSet3.getString("unit6"),resultSet3.getString("cf6"), resultSet3.getString("sp6"), resultSet3.getString("barcode16"), resultSet3.getString("barcode17"), resultSet3.getString("barcode18")});
        			}
    			}
        	}
        	con.close();
			get_vendor_purchase_dt(company,txtItemcode.getText(),decimal);
			txtshrt_nme.grabFocus();
		}
		catch(Exception e){
			frame.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
			frame.setAlwaysOnTop(true);
		}
		
	}
	
	public void get_itemdtls_bsd_nme(String company, String input,int decimal) {
		try{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_item_master where item_name='"+input+"' ");
			int sold_qty = 0;
			while(resultSet.next())
			{
				txtItemcode.setText(resultSet.getString("item_code"));
				txtshrt_nme.setText(resultSet.getString("item_name"));
	        	txt_desc1.setText(resultSet.getString("description1"));
	        	textArea_1.setText(resultSet.getString("description2"));
	        	item_dtls.setText(resultSet.getString("item_details"));
	        	
	        	comboBox_2.setSelectedItem(resultSet.getString("department"));
	        	comboBox_3.setSelectedItem(resultSet.getString("sub_department"));
	        	comboBox_4.setSelectedItem(resultSet.getString("category"));
	        	comboBox_5.setSelectedItem(resultSet.getString("sub_category"));
	        	
	        	textField_5.setText(resultSet.getString("vendor_code"));
	        	textField_4.setText(resultSet.getString("vendor_name"));
	        	
	        	comboBox_6.setSelectedItem(resultSet.getString("item_type"));
	        	textField.setText(resultSet.getString("item_cost"));
	        	textField_3.setText(resultSet.getString("item_lastcost"));
	        	textField_6.setText(resultSet.getString("item_stdcost"));
	        	
	        	Statement statement1=con.createStatement();  
				String query="select * from "+company+".mr_pos_settlement_cash_items where item_code='"+resultSet.getString("item_code")+"' ";
	        	ResultSet resultSet1 = statement1.executeQuery(query);
	        	while(resultSet1.next())
	        	{
	        		sold_qty+=Integer.parseInt(resultSet1.getString("qty"));
	        	}
	        	label_3.setText(Integer.toString(sold_qty));
	        	int new_qty=Integer.parseInt(Integer.toString(sold_qty))+Integer.parseInt(resultSet.getString("total_qty"));
	        	int net_qty=new_qty-Integer.parseInt(Integer.toString(sold_qty));
	        	label_4.setText(Integer.toString(net_qty));
	        	label_2.setText(Integer.toString(new_qty));
	        	textField_8.setText(resultSet.getString("total_qty"));
	        	textField_13.setText(resultSet.getString("min_order"));
	        	
	        	String dbimg=resultSet.getString("item_image");
	        	if(dbimg!="null")
	        	{
	        		lblitemimage.setIcon(ResizeImage(dbimg));
	        	}
	        	else
	        	{
	        		lblitemimage.setIcon(ResizeImage(""));
	        	}
	        	comboBox_7.setSelectedItem(resultSet.getString("input_tax"));
	        	comboBox_8.setSelectedItem(resultSet.getString("output_tax"));
	        	if(resultSet.getString("sp_include_tax").equals("1"))
	        	{
	        		chckbxSalesPriceInclusive.setSelected(true);
	        	}
	        	else
	        	{
	        		chckbxSalesPriceInclusive.setSelected(false);
	        	}
	        	if(resultSet.getString("po_inclusive_tax").equals("1"))
	        	{
	        		chckbxpoPriceInclusive.setSelected(true);
	        	}
	        	else
	        	{
	        		chckbxpoPriceInclusive.setSelected(false);
	        	}
	        	if(resultSet.getString("fast_item").equals("1"))
	        	{
	        		chckbxFastItem.setSelected(true);
	        	}
	        	else
	        	{
	        		chckbxFastItem.setSelected(false);
	        	}
	        	
	        	if(resultSet.getString("Open_Item").equals("1"))
	        	{
	        		chckbxOpenItem.setSelected(true);
	        	}
	        	else
	        	{
	        		chckbxOpenItem.setSelected(false);
	        	}
	        	if(resultSet.getString("deliItemsweighing").equals("1"))
	        	{
	        		chckbxDeliItemsweighing.setSelected(true);
	        		textField_11.setText(resultSet.getString("exp_days"));
	        		textField_11.setEditable(true);
	        		comboBox_10.setSelectedItem(resultSet.getString("weighing_sts"));
	        		comboBox_10.setEnabled(true);
	        	}
	        	else
	        	{
	        		chckbxDeliItemsweighing.setSelected(false);
	        		textField_11.setEditable(false);
	        		textField_11.setText("");
	        		comboBox_10.setEnabled(false);
	        	}
	        	if(resultSet.getString("open_qty").equals("1"))
	        	{
	        		chckbxOpeningQty.setSelected(true);
	        	}
	        	else
	        	{
	        		chckbxOpeningQty.setSelected(false);
	        	}
	        	if(resultSet.getString("parentKitItem").equals("1"))
	        	{
	        		chckbxParentKit.setSelected(true);
	        	}
	        	else
	        	{
	        		chckbxParentKit.setSelected(false);
	        	}
	        	if(resultSet.getString("allowDiscount").equals("1"))
	        	{
	        		chckbxAllowDiscount.setSelected(true);
	        		textField_7.setText(resultSet.getString("disc_perc"));
	        	}
	        	else
	        	{
	        		chckbxAllowDiscount.setSelected(false);
	        		textField_7.setText("");
	        	}
	        	if(resultSet.getString("activeForSale").equals("1"))
	        	{
	        		chckbxActiveForSale.setSelected(true);
	        	}
	        	else
	        	{
	        		chckbxActiveForSale.setSelected(false);
	        	}
	        	if(!resultSet.getString("acc_grp").equals(""))
	        	{
	        		comboBox_9.setSelectedItem(resultSet.getString("acc_grp"));
	        	}
	        	else
	        	{
	        		comboBox_9.setSelectedIndex(-1);
	        	}
	        	if(!resultSet.getString("acc_code").equals(""))
	        	{
	        		comboBox_11.setSelectedItem(resultSet.getString("acc_code"));
	        	}
	        	else
	        	{
	        		comboBox_11.setSelectedIndex(-1);
	        	}
	        	if(resultSet.getString("activeForPurchase").equals("1"))
	        	{
	        		chckbxActiveForPurchase.setSelected(true);
	        	}
	        	else
	        	{
	        		chckbxActiveForPurchase.setSelected(false);
	        	}
	        	if(resultSet.getString("Onpromo").equals("1"))
	        	{
	        		chckbxOn.setSelected(true);
	        		java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(resultSet.getString("start_date"));
					dateChooser.setDate(date);
					dateChooser.setEnabled(true);
					java.util.Date date_end = new SimpleDateFormat("dd/MM/yyyy").parse(resultSet.getString("enddate"));
					dateChooser_1.setDate(date_end);
					dateChooser_1.setEnabled(true);
	        	}
	        	else
	        	{
	        		chckbxOn.setSelected(false);
	        		dateChooser.setEnabled(false);
	        		dateChooser_1.setEnabled(false);
	        	}
	        	int rowCount = model.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
					model.removeRow(i);
				}
	        	model.addRow(new Object[]{resultSet.getString("unit1"),resultSet.getString("cf1"), resultSet.getString("sp1"), resultSet.getString("barcode1"), resultSet.getString("barcode2"), resultSet.getString("barcode3")});
	        	model.addRow(new Object[]{resultSet.getString("unit2"),resultSet.getString("cf2"), resultSet.getString("sp2"), resultSet.getString("barcode4"), resultSet.getString("barcode5"), resultSet.getString("barcode6")});
	        	model.addRow(new Object[]{resultSet.getString("unit3"),resultSet.getString("cf3"), resultSet.getString("sp3"), resultSet.getString("barcode7"), resultSet.getString("barcode8"), resultSet.getString("barcode9")});
	        	model.addRow(new Object[]{resultSet.getString("unit4"),resultSet.getString("cf4"), resultSet.getString("sp4"), resultSet.getString("barcode10"), resultSet.getString("barcode11"), resultSet.getString("barcode12")});
	        	model.addRow(new Object[]{resultSet.getString("unit5"),resultSet.getString("cf5"), resultSet.getString("sp5"), resultSet.getString("barcode13"), resultSet.getString("barcode14"), resultSet.getString("barcode15")});
	        	model.addRow(new Object[]{resultSet.getString("unit6"),resultSet.getString("cf6"), resultSet.getString("sp6"), resultSet.getString("barcode16"), resultSet.getString("barcode17"), resultSet.getString("barcode18")});
			}
			con.close();
			get_vendor_purchase_dt(company,txtItemcode.getText(),decimal);
			txtshrt_nme.grabFocus();
		}
		catch(Exception e){
			frame.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
			frame.setAlwaysOnTop(true);
		}
		
	}
	public static String encoder(File file) {
		String base64Image = "";
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a Image file from file system
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			base64Image = Base64.getEncoder().encodeToString(imageData);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(frame, "Image not found ·„ Ì „ «·⁄ÀÊ— ⁄·Ï «·’Ê—…" + e);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog( frame,"Exception while reading the Image " + ioe);
		}
		return base64Image;
	}
	 public static ImageIcon ResizeImage(String ImagePath)
	    {
	        ImageIcon MyImage = new ImageIcon(ImagePath);
	        Image img = MyImage.getImage();
	        Image newImg = img.getScaledInstance(lblitemimage.getWidth(), lblitemimage.getHeight(), Image.SCALE_SMOOTH);
	        ImageIcon image = new ImageIcon(newImg);
	        return image;
	    }
	 
	 private void autogenerate(int row,int column) {
		 try {
			 String code=txtItemcode.getText();
		     if(code.equals("NEW"))
		     {
		    	 JOptionPane.showMessageDialog(frame, "<html>Save the item First and choose the barcode<br>«Õ›Ÿ «·⁄‰’— √Ê·« À„ «Œ — «·»«—ﬂÊœ</html>");
		     }
		     else
		     {
		    	 String new_code=code+""+row+""+column;
				 table.setValueAt(new_code, row, column);
		     }
		} catch (Exception e) {
			JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
		 
	     
	}
	private static void get_vendor_purchase_dt(String company,String Itemcode, int decimal)
	{
		try{
			
			Connection con=Dbsetup.dbconn.getConnection();
			
			int rowCount = modellist.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				modellist.removeRow(i);
			}
			int ttlqty = 0;
			String po_date = null;
			String name = null;
			String mobile=null;
			String po_no=null;
			String tax_val=null;
			//String total=null;
			//String ttl_sum = null;
			double poqty = 0;
			double po_qty = 0;
			String unit_cost = null;
			String unit = null;
			String invo_nu="";
			Statement statement1=con.createStatement(); 
			Statement statement2=con.createStatement(); 
			String query= "SELECT * FROM "+company+".mr_purchase_order_history inner join "+company+".mr_purchase_list_history on "+company+".mr_purchase_list_history.po_no="+company+".mr_purchase_order_history.ref_po_num inner join "+company+".mr_vendor ON "+company+".mr_purchase_list_history.vendor_id="+company+".mr_vendor.vendor_code where "+company+".mr_purchase_order_history.item_code='"+Itemcode+"' and "+company+".mr_purchase_list_history.status='POSTED' order by posted_date desc";
			ResultSet resultSet1 = statement1.executeQuery(query);
			while(resultSet1.next())
			{
				po_date=resultSet1.getString("po_date");
				name=resultSet1.getString("vendor_name");
				po_no=resultSet1.getString("po_no");
				invo_nu=resultSet1.getString("invo_nu");
				mobile=resultSet1.getString("vendor_ph1");
				unit=resultSet1.getString("unit");
				//ttl_sum=resultSet1.getString("ttl_sum");
				poqty=Double.parseDouble(resultSet1.getString("qty"))*Double.parseDouble(resultSet1.getString("cf"));
				po_qty=Double.parseDouble(resultSet1.getString("qty"));//*Double.parseDouble(resultSet1.getString("cf"));
				//unit_cost=resultSet1.getString("unit_cost");//cost_after_sub_ttl_disc
				unit_cost=resultSet1.getString("sub_cost_after_sub_ttl_disc");
				double cost_before=Double.parseDouble(unit_cost)/poqty;
				BigDecimal ba = new BigDecimal(cost_before);
        		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
        		cost_before = ba.doubleValue();
				tax_val=resultSet1.getString("tax_val");
				double vat=Double.parseDouble(tax_val)/poqty;
				BigDecimal bv = new BigDecimal(vat);
        		bv = bv.setScale(decimal, BigDecimal.ROUND_HALF_UP);
        		vat = bv.doubleValue();
        		
        		double totalamt=cost_before+vat;
        		BigDecimal bw = new BigDecimal(totalamt);
        		bw = bw.setScale(decimal, BigDecimal.ROUND_HALF_UP);
        		totalamt = bw.doubleValue();
        		
				//total=resultSet1.getString("cost_after_sub_ttl_disc");
				modellist.addRow(new Object[]{po_date,name,po_no,invo_nu,mobile,unit,po_qty,cost_before,vat,totalamt});
				double qty=po_qty*Double.parseDouble(resultSet1.getString("cf"));
				ttlqty+=qty;
			}
			textField_9.setText(Integer.toString(ttlqty));
			String query1= "SELECT sum(qty*cf) as ttl_rtn FROM "+company+".mr_purchase_return inner join "+company+".mr_purchase_return_list on "+company+".mr_purchase_return_list.pr_no="+company+".mr_purchase_return.ref_pr_num inner join "+company+".mr_vendor ON "+company+".mr_purchase_return_list.vendor_id="+company+".mr_vendor.vendor_code where "+company+".mr_purchase_return.item_code='"+Itemcode+"' and "+company+".mr_purchase_return_list.status='POSTED' GROUP BY "+company+".mr_vendor.vendor_code ";
			ResultSet resultSet2 = statement2.executeQuery(query1);
			double ttl_rtn=0;
			while(resultSet2.next())
			{
				double qty1=Double.parseDouble(resultSet2.getString("ttl_rtn"));
				ttl_rtn+=qty1;
			}
			textField_1.setText(Double.toString(ttl_rtn));
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
			frame.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog( frame,e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
			frame.setAlwaysOnTop(true);
		}
	}
	public String get_printer_name(String company,String username){
		String printer="";
		Connection con=Dbsetup.dbconn.getConnection();
		try {
			
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_barcode where status='1' ");
			while(resultSet.next())
			{
				byte[] printers=Base64.getDecoder().decode(resultSet.getString("printer").toString()); //resultSet.getString("printer");
				printer = new String(printers, StandardCharsets.UTF_8);
			}
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
		return printer;
	}
	
	public void get_store_dt(String company){
		Connection con=Dbsetup.dbconn.getConnection();
		try {
			
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store where store_short_name='"+company+"' ");
			if(resultSet.next())
			{
				CodeAllowChar=Integer.parseInt(resultSet.getString("ItemCodeAllowChar").toString());
			}
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}
	
	public void get_acc_name(String company){
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_assign_account_group where status='1' ");
			while(resultSet.next())
			{
				comboBox_11.addItem(resultSet.getString("group_code"));
			}
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog( frame, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
		}
	}
}
