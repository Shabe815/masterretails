package sales_master;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.LineBorder;
import javax.swing.JComboBox;

import org.json.JSONException;

import CafePro.Mainpage;

import com.toedter.calendar.JDateChooser;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class historical_invoice {

	private JFrame frmDuplicateInvoice;
	private JTextField textField;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;
	private JSpinner timeSpinner;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	static DefaultTableModel model;
	private JDateChooser dateChooser ;
	static DecimalFormat decimal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					historical_invoice window = new historical_invoice("mr_pro", "admin","S01","S0102", "", null,"2");
					window.frmDuplicateInvoice.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param reg_nme 
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param printer 
	 * @throws JSONException 
	 * @wbp.parser.entryPoint
	 */
	public historical_invoice(String company, String username, String store_name, String reg_nme, String printer,String version,String decimalPlaces) throws JSONException {
		initialize(company,username,store_name,reg_nme, printer, version, decimalPlaces);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param reg_nme 
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @throws JSONException 
	 */
	private void initialize(final String company,final String username,final String store_name,final String reg_nme, final String printer,String version,final String decimalPlaces) throws JSONException {
		frmDuplicateInvoice = new JFrame(version);
		frmDuplicateInvoice.setResizable(false);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frmDuplicateInvoice.setIconImage(img);
		frmDuplicateInvoice.setVisible(true);
		frmDuplicateInvoice.getContentPane().setBackground(Color.DARK_GRAY);
		frmDuplicateInvoice.setTitle("HISTORICAL INVOICE");
		frmDuplicateInvoice.setBounds(100, 100, 1012, 564);
		frmDuplicateInvoice.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btnhistInvoice.setEnabled(true);
				}
				else
				{
					//posscreen.txtDisc.requestFocus();
					//posscreen.btnhistInvoice.setEnabled(true);
				}
			}
			@Override
		      public void windowIconified(WindowEvent we) {
				frmDuplicateInvoice.setState(JFrame.NORMAL);
		      }
		});
		frmDuplicateInvoice.getContentPane().setLayout(null);
		frmDuplicateInvoice.setAlwaysOnTop(true);
		frmDuplicateInvoice.setLocationRelativeTo(null);
		
		JLabel lblEnterInvoiceNo = new JLabel("<html>Invoice No. <br>\u0631\u0642\u0645 \u0627\u0644\u0641\u0627\u062A\u0648\u0631\u0629</html>");
		lblEnterInvoiceNo.setForeground(new Color(100, 149, 237));
		lblEnterInvoiceNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterInvoiceNo.setBounds(10, 41, 125, 39);
		frmDuplicateInvoice.getContentPane().add(lblEnterInvoiceNo);
		
		Image reports=new ImageIcon(this.getClass().getResource("/reports.png")).getImage();
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(129, 46, 175, 25);
		frmDuplicateInvoice.getContentPane().add(textField);
		DocumentFilter df = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {

                if (isDigit(string)) {
                    super.insertString(fb, i, string, as);
                    calcAndSetTotal();
                }
            }

            @Override
            public void remove(FilterBypass fb, int i, int i1) throws BadLocationException {
                super.remove(fb, i, i1);
                calcAndSetTotal();
            }

            @Override
            public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
                if (isDigit(string)) {
                    super.replace(fb, i, i1, string, as);
                    calcAndSetTotal();

                }
            }

            private boolean isDigit(String string) {
                for (int n = 0; n < string.length(); n++) {
                    char c = string.charAt(n);
                    if(c!='.')
                    {
                    	if (!Character.isDigit(c)) {
                            return false;
                        }
                    }
                    else
                    {
                    	return true;
                    }
                    
                }
                return true;
            }

            void calcAndSetTotal() {
            	Object value = timeSpinner.getValue();
				String time = null;
				if (value instanceof Date) {
		            Date date = (Date)value;
		            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		            time = format.format(date);
		        }
				String invoice=textField.getText();
				if(invoice.equals(""))
				{
					invoice="";
				}
				String date=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if(date.equals(""))
				{
					date="";
				}
				String cust=textField_4.getText();
				if(cust.equals(""))
				{
					cust="";
				}
				String paycode=comboBox.getSelectedItem().toString();
				if(paycode.equals("ALL"))
				{
					paycode="";
				}
				if(paycode.equals("MULTIPLE PAYCODES"))
				{
					paycode="PAYCODE";
				}
				String user=comboBox_1.getSelectedItem().toString();
				if(user.equals("ALL"))
				{
					user="";
				}
				String itemname=textField_5.getText();
				if(itemname.equals(""))
				{
					itemname="";
				}
		    	check_invoice(company,username,store_name,reg_nme,invoice,date,time,cust,paycode,user,itemname, decimalPlaces);
            }
        };
		((AbstractDocument) (textField.getDocument())).setDocumentFilter(df);
		
		JLabel lblPaycode = new JLabel("<html>Paycode <br> \u0631\u0645\u0632 \u0627\u0644\u062F\u0641\u0639:</html>");
		lblPaycode.setForeground(new Color(100, 149, 237));
		lblPaycode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPaycode.setBounds(326, 45, 74, 35);
		frmDuplicateInvoice.getContentPane().add(lblPaycode);
		
		JLabel lblFilterBy = new JLabel("Filter by:");
		lblFilterBy.setForeground(Color.GREEN);
		lblFilterBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFilterBy.setBounds(10, 11, 125, 25);
		frmDuplicateInvoice.getContentPane().add(lblFilterBy);
		
		JLabel lblTime = new JLabel("<html>Date & Time <br> \u0627\u0644\u062A\u0627\u0631\u064A\u062E \u0648\u0627\u0644\u0648\u0642\u062A </html>");
		lblTime.setForeground(new Color(100, 149, 237));
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTime.setBounds(10, 82, 114, 39);
		frmDuplicateInvoice.getContentPane().add(lblTime);
		
		JLabel lblUser = new JLabel("User \u0627\u0644\u0645\u0633\u062A\u0639\u0645\u0644:");
		lblUser.setForeground(new Color(100, 149, 237));
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUser.setBounds(326, 81, 100, 25);
		frmDuplicateInvoice.getContentPane().add(lblUser);
		
		JLabel lblCustomer = new JLabel("Customer \u0632\u0628\u0648\u0646:");
		lblCustomer.setToolTipText("Click or Touch on the box to invoke ON SCREEN Keyboard");
		lblCustomer.setForeground(new Color(100, 149, 237));
		lblCustomer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCustomer.setBounds(10, 118, 114, 25);
		frmDuplicateInvoice.getContentPane().add(lblCustomer);
		
		textField_4 = new JTextField();
		textField_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Runtime.getRuntime().exec("cmd /c osk");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		textField_4.setToolTipText("Click or Touch on the box to invoke ON SCREEN Keyboard");
		textField_4.setColumns(10);
		textField_4.setBounds(129, 118, 175, 25);
		frmDuplicateInvoice.getContentPane().add(textField_4);
		
		DocumentFilter da = new DocumentFilter() {
			@Override
            public void insertString(FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {

                if (isDigit(string)) {
                    super.insertString(fb, i, string, as);
                    calcAndSetTotal();
                }
            }

            @Override
            public void remove(FilterBypass fb, int i, int i1) throws BadLocationException {
                super.remove(fb, i, i1);
                calcAndSetTotal();
            }

            @Override
            public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
                if (isDigit(string)) {
                    super.replace(fb, i, i1, string, as);
                    calcAndSetTotal();

                }
            }

            private boolean isDigit(String string) {
                for (int n = 0; n < string.length(); n++) {
                    char c = string.charAt(n);
                    if(c!='.')
                    {
                    	if (!Character.isDigit(c)) {
                            return true;
                        }
                    }
                    else
                    {
                    	return true;
                    }
                    
                }
                return true;
            }
			void calcAndSetTotal() {
				Object value = timeSpinner.getValue();
				String time = null;
				if (value instanceof Date) {
		            Date date = (Date)value;
		            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		            time = format.format(date);
		        }
				String invoice=textField.getText();
				if(invoice.equals(""))
				{
					invoice="";
				}
				String date=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if(date.equals(""))
				{
					date="";
				}
				String cust=textField_4.getText();
				if(cust.equals(""))
				{
					cust="";
				}
				String paycode=comboBox.getSelectedItem().toString();
				if(paycode.equals("ALL"))
				{
					paycode="";
				}
				if(paycode.equals("MULTIPLE PAYCODES"))
				{
					paycode="PAYCODE";
				}
				String user=comboBox_1.getSelectedItem().toString();
				if(user.equals("ALL"))
				{
					user="";
				}
				String itemname=textField_5.getText();
				if(itemname.equals(""))
				{
					itemname="";
				}
		    	check_invoice(company,username,store_name,reg_nme,invoice,date,time,cust,paycode,user,itemname,decimalPlaces);
            }
        };
		((AbstractDocument) (textField_4.getDocument())).setDocumentFilter(da);
		
		JLabel lblItem = new JLabel("Item \u063A\u0631\u0636 :");
		lblItem.setForeground(new Color(100, 149, 237));
		lblItem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblItem.setBounds(326, 117, 100, 25);
		frmDuplicateInvoice.getContentPane().add(lblItem);
		
		textField_5 = new JTextField();
		textField_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Runtime.getRuntime().exec("cmd /c osk");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		textField_5.setColumns(10);
		textField_5.setBounds(427, 117, 207, 25);
		frmDuplicateInvoice.getContentPane().add(textField_5);
		DocumentFilter db = new DocumentFilter() {
			@Override
            public void insertString(FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {

                if (isDigit(string)) {
                    super.insertString(fb, i, string, as);
                    calcAndSetTotal();
                }
            }

            @Override
            public void remove(FilterBypass fb, int i, int i1) throws BadLocationException {
                super.remove(fb, i, i1);
                calcAndSetTotal();
            }

            @Override
            public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
                if (isDigit(string)) {
                    super.replace(fb, i, i1, string, as);
                    calcAndSetTotal();

                }
            }

            private boolean isDigit(String string) {
                for (int n = 0; n < string.length(); n++) {
                    char c = string.charAt(n);
                    if(c!='.')
                    {
                    	if (!Character.isDigit(c)) {
                            return true;
                        }
                    }
                    else
                    {
                    	return true;
                    }
                    
                }
                return true;
            }
            void calcAndSetTotal() {
            	Object value = timeSpinner.getValue();
				String time = null;
				if (value instanceof Date) {
		            Date date = (Date)value;
		            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		            time = format.format(date);
		        }
				String invoice=textField.getText();
				if(invoice.equals(""))
				{
					invoice="";
				}
				String date=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if(date.equals(""))
				{
					date="";
				}
				String cust=textField_4.getText();
				if(cust.equals(""))
				{
					cust="";
				}
				String paycode=comboBox.getSelectedItem().toString();
				if(paycode.equals("ALL"))
				{
					paycode="";
				}
				if(paycode.equals("MULTIPLE PAYCODES"))
				{
					paycode="PAYCODE";
				}
				String user=comboBox_1.getSelectedItem().toString();
				if(user.equals("ALL"))
				{
					user="";
				}
				String itemname=textField_5.getText();
				if(itemname.equals(""))
				{
					itemname="";
				}
		    	check_invoice(company,username,store_name,reg_nme,invoice,date,time,cust,paycode,user,itemname,decimalPlaces);
            }
        };
		((AbstractDocument) (textField_5.getDocument())).setDocumentFilter(db);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 156, 1019, 2);
		frmDuplicateInvoice.getContentPane().add(separator);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(3, 160, 785, 354);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(Color.BLACK);
		frmDuplicateInvoice.getContentPane().add(scrollPane);
		
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
		table.setShowGrid(false);
		table.setForeground(Color.WHITE);
		table.setRowHeight(25);
		//table.setBorder(new LineBorder(Color.WHITE));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Arial", Font.BOLD, 15));
		table.setBackground(Color.DARK_GRAY);
		
		table.setDefaultRenderer(Object.class, new TableCellRenderer(){
            private DefaultTableCellRenderer DEFAULT_RENDERER =  new DefaultTableCellRenderer();
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row%2 == 0){
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                else {
                    c.setBackground(Color.LIGHT_GRAY);
                    c.setForeground(Color.BLACK);
                }
                if (isSelected) {
                	c.setBackground(Color.YELLOW);
                }
                return c;
            }
            

        });
		
		
		JTableHeader header=table.getTableHeader();
		header.setPreferredSize(new Dimension(100,35));
		
		model.addColumn("S.No.");
		model.addColumn("Reg. No"); 
		model.addColumn("Invoice No"); 
	    model.addColumn("Invoice Date"); 
	    model.addColumn("Inv Time"); 
	    model.addColumn("<html>Cust <br> Name</html>");
	    model.addColumn("<html>No Of <br>Items</html>");
	    model.addColumn("Total");
	    model.addColumn("Disc");
	    model.addColumn("Sub Total");
	    model.addColumn("Tax");
	    model.addColumn("Grand Total");
	    model.addColumn("Paycode");
	    int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(35);
			  if (i==1) column.setPreferredWidth(60);
			  if (i==2) column.setPreferredWidth(81);
			  if (i==3) column.setPreferredWidth(90);
			  if (i==4) column.setPreferredWidth(70);
			  if (i==5) column.setPreferredWidth(100);
			  if (i==6) column.setPreferredWidth(60);
			  if (i==7) column.setPreferredWidth(60);
			  if (i==8) column.setPreferredWidth(50);
			  if (i==9) column.setPreferredWidth(50);
			  if (i==10) column.setPreferredWidth(75);
			  if (i==11) column.setPreferredWidth(80);
			}
		
	    scrollPane.setViewportView(table);
		
//	    table.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent mouseEvent) {
//				JTable table = (JTable) mouseEvent.getSource();
//				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
//					int column = 1;
//					int rows = table.getSelectedRow();
//					String value = table.getModel().getValueAt(rows, column).toString();
//						duplicate_print(company,host_name,store_name,value);
//					}
//				
//				}
//		});
	    
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image printer1=new ImageIcon(this.getClass().getResource("/printer.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuItem.setForeground(Color.WHITE);
		menuItem.setBorder(null);
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmDuplicateInvoice.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btnhistInvoice.setEnabled(true);
				}
				else
				{
					//posscreen.txtDisc.requestFocus();
					//posscreen.btnhistInvoice.setEnabled(true);
				}
				
			}
		});
		menuItem.setBackground(Color.DARK_GRAY);
		menuItem.setBounds(897, 7, 84, 30);
		frmDuplicateInvoice.getContentPane().add(menuItem);
		
		JMenuItem mntmprint = new JMenuItem("<html>Print<br>\u0645\u0637\u0628\u0639\u0629</html>");
		mntmprint.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mntmprint.setForeground(new Color(255, 255, 204));
		mntmprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int column = 1;
				int rows = table.getSelectedRow();
				if(rows!=-1)
				{
					String value = table.getModel().getValueAt(rows, 2).toString();
					String register = table.getModel().getValueAt(rows, column).toString();
					frmDuplicateInvoice.setAlwaysOnTop(false);
					duplicate_print(company,store_name,value,printer,register,"print");
					frmDuplicateInvoice.setAlwaysOnTop(true);
				}
				else
				{
					JOptionPane.showMessageDialog(frmDuplicateInvoice, "<html>Select any row from the table<br>حدد أي صف من الجدول</html>");
				}
				
			}
		});
		mntmprint.setBorder(null);
		mntmprint.setBackground(Color.DARK_GRAY);
		mntmprint.setIcon(new ImageIcon(printer1));
		mntmprint.setBounds(838, 40, 80, 40);
		frmDuplicateInvoice.getContentPane().add(mntmprint);
		
		
		JButton button = new JButton("1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("1");
			}
		});
		button.setForeground(Color.DARK_GRAY);
		button.setFont(new Font("Arial", Font.BOLD, 30));
		button.setBorder(new LineBorder(SystemColor.control));
		button.setBackground(SystemColor.menu);
		button.setBounds(792, 169, 60, 60);
		frmDuplicateInvoice.getContentPane().add(button);
		
		JButton button_2 = new JButton("2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("2");
			}
		});
		button_2.setForeground(Color.DARK_GRAY);
		button_2.setFont(new Font("Arial", Font.BOLD, 30));
		button_2.setBorder(new LineBorder(SystemColor.control));
		button_2.setBackground(SystemColor.menu);
		button_2.setBounds(862, 169, 60, 60);
		frmDuplicateInvoice.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("3");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("3");
			}
		});
		button_3.setForeground(Color.DARK_GRAY);
		button_3.setFont(new Font("Arial", Font.BOLD, 30));
		button_3.setBorder(new LineBorder(SystemColor.control));
		button_3.setBackground(SystemColor.menu);
		button_3.setBounds(932, 169, 60, 60);
		frmDuplicateInvoice.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("4");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("4");
			}
		});
		button_4.setForeground(Color.DARK_GRAY);
		button_4.setFont(new Font("Arial", Font.BOLD, 30));
		button_4.setBorder(new LineBorder(SystemColor.control));
		button_4.setBackground(SystemColor.menu);
		button_4.setBounds(792, 240, 60, 60);
		frmDuplicateInvoice.getContentPane().add(button_4);
		
		JButton button_5 = new JButton("5");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("5");
			}
		});
		button_5.setForeground(Color.DARK_GRAY);
		button_5.setFont(new Font("Arial", Font.BOLD, 30));
		button_5.setBorder(new LineBorder(SystemColor.control));
		button_5.setBackground(SystemColor.menu);
		button_5.setBounds(862, 240, 60, 60);
		frmDuplicateInvoice.getContentPane().add(button_5);
		
		JButton button_6 = new JButton("6");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("6");
			}
		});
		button_6.setForeground(Color.DARK_GRAY);
		button_6.setFont(new Font("Arial", Font.BOLD, 30));
		button_6.setBorder(new LineBorder(SystemColor.control));
		button_6.setBackground(SystemColor.menu);
		button_6.setBounds(932, 240, 60, 60);
		frmDuplicateInvoice.getContentPane().add(button_6);
		
		JButton button_7 = new JButton("7");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("7");
			}
		});
		button_7.setForeground(Color.DARK_GRAY);
		button_7.setFont(new Font("Arial", Font.BOLD, 30));
		button_7.setBorder(new LineBorder(SystemColor.control));
		button_7.setBackground(SystemColor.menu);
		button_7.setBounds(792, 311, 60, 60);
		frmDuplicateInvoice.getContentPane().add(button_7);
		
		JButton button_8 = new JButton("8");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("8");
			}
		});
		button_8.setForeground(Color.DARK_GRAY);
		button_8.setFont(new Font("Arial", Font.BOLD, 30));
		button_8.setBorder(new LineBorder(SystemColor.control));
		button_8.setBackground(SystemColor.menu);
		button_8.setBounds(862, 311, 60, 60);
		frmDuplicateInvoice.getContentPane().add(button_8);
		
		JButton button_9 = new JButton("9");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("9");
			}
		});
		button_9.setForeground(Color.DARK_GRAY);
		button_9.setFont(new Font("Arial", Font.BOLD, 30));
		button_9.setBorder(new LineBorder(SystemColor.control));
		button_9.setBackground(SystemColor.menu);
		button_9.setBounds(932, 311, 60, 60);
		frmDuplicateInvoice.getContentPane().add(button_9);
		
		JButton button_10 = new JButton("0");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("0");
			}
		});
		button_10.setForeground(Color.DARK_GRAY);
		button_10.setFont(new Font("Arial", Font.BOLD, 30));
		button_10.setBorder(new LineBorder(SystemColor.control));
		button_10.setBackground(SystemColor.menu);
		button_10.setBounds(862, 382, 60, 60);
		frmDuplicateInvoice.getContentPane().add(button_10);
		
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
			}
		});
		btnC.setForeground(Color.DARK_GRAY);
		btnC.setFont(new Font("Arial", Font.BOLD, 30));
		btnC.setBorder(new LineBorder(SystemColor.control));
		btnC.setBackground(SystemColor.menu);
		btnC.setBounds(792, 382, 60, 60);
		frmDuplicateInvoice.getContentPane().add(btnC);
		
		JButton btnBksp = new JButton("<<");
		btnBksp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField.getText().equals(""))
				{
					textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
				}
			}
		});
		btnBksp.setForeground(Color.DARK_GRAY);
		btnBksp.setFont(new Font("Arial", Font.BOLD, 15));
		btnBksp.setBorder(new LineBorder(SystemColor.control));
		btnBksp.setBackground(SystemColor.menu);
		btnBksp.setBounds(932, 382, 60, 60);
		frmDuplicateInvoice.getContentPane().add(btnBksp);
		
		JButton btnEnter = new JButton("ENTER \u064A\u062F\u062E\u0644");
		btnEnter.setForeground(Color.DARK_GRAY);
		btnEnter.setFont(new Font("Arial", Font.BOLD, 30));
		btnEnter.setBorder(new LineBorder(SystemColor.control));
		btnEnter.setBackground(SystemColor.menu);
		btnEnter.setBounds(792, 454, 200, 60);
		frmDuplicateInvoice.getContentPane().add(btnEnter);
		
		comboBox = new JComboBox<String>();
		
		comboBox.setBounds(427, 45, 207, 25);
		frmDuplicateInvoice.getContentPane().add(comboBox);
		comboBox.addItem("ALL");
		get_paycodes( company);
		//comboBox.addItem("MULTIPLE PAYCODES");
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	Object value = timeSpinner.getValue();
				String time = null;
				if (value instanceof Date) {
		            Date date = (Date)value;
		            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		            time = format.format(date);
		        }
				String invoice=textField.getText();
				if(invoice.equals(""))
				{
					invoice="";
				}
				String date=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if(date.equals(""))
				{
					date="";
				}
				String cust=textField_4.getText();
				if(cust.equals(""))
				{
					cust="";
				}
				String paycode=comboBox.getSelectedItem().toString();
				if(paycode.equals("ALL"))
				{
					paycode="";
				}
				if(paycode.equals("MULTIPLE PAYCODES"))
				{
					paycode="PAYCODE";
				}
				String user=comboBox_1.getSelectedItem().toString();
				if(user.equals("ALL"))
				{
					user="";
				}
				String itemname=textField_5.getText();
				if(itemname.equals(""))
				{
					itemname="";
				}
		    	check_invoice(company,username,store_name,reg_nme,invoice,date,time,cust,paycode,user,itemname,decimalPlaces);
		    }
		});
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(427, 81, 207, 25);
		
		frmDuplicateInvoice.getContentPane().add(comboBox_1);
		comboBox_1.addItem("ALL");
		get_reg_userlist(company);
		comboBox_1.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	Object value = timeSpinner.getValue();
				String time = null;
				if (value instanceof Date) {
		            Date date = (Date)value;
		            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		            time = format.format(date);
		        }
				String invoice=textField.getText();
				if(invoice.equals(""))
				{
					invoice="";
				}
				String date=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if(date.equals(""))
				{
					date="";
				}
				String cust=textField_4.getText();
				if(cust.equals(""))
				{
					cust="";
				}
				String paycode=comboBox.getSelectedItem().toString();
				if(paycode.equals("ALL"))
				{
					paycode="";
				}
				if(paycode.equals("MULTIPLE PAYCODES"))
				{
					paycode="PAYCODE";
				}
				String user=comboBox_1.getSelectedItem().toString();
				if(user.equals("ALL"))
				{
					user="";
				}
				String itemname=textField_5.getText();
				if(itemname.equals(""))
				{
					itemname="";
				}
		    	check_invoice(company,username,store_name,reg_nme,invoice,date,time,cust,paycode,user,itemname,decimalPlaces);
		    }
		});
		
		JLabel label = new JLabel("Print A4 Invoice");
		label.setForeground(new Color(153, 255, 0));
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(709, 75, 187, 25);
		frmDuplicateInvoice.getContentPane().add(label);
		
		
		JMenuItem mntmPrint = new JMenuItem("<html>Print<br>\u0645\u0637\u0628\u0639\u0629</html>");
		mntmPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int column = 2;
				int rows = table.getSelectedRow();
				if(rows!=-1)
				{
					String register = table.getModel().getValueAt(rows, 1).toString();
					String value = table.getModel().getValueAt(rows, column).toString();
					frmDuplicateInvoice.setAlwaysOnTop(false);
					duplicate_printA4(company,store_name,value,register,"print");
					frmDuplicateInvoice.setAlwaysOnTop(true);
				}
				else
				{
					JOptionPane.showMessageDialog(frmDuplicateInvoice, "<html>Select any row from the table<br>حدد أي صف من الجدول</html>");
				}
			}
		});
		mntmPrint.setForeground(new Color(255, 255, 204));
		mntmPrint.setFont(new Font("Arial", Font.BOLD, 15));
		mntmPrint.setBorder(null);
		mntmPrint.setBackground(Color.DARK_GRAY);
		mntmPrint.setIcon(new ImageIcon(printer1));
		mntmPrint.setBounds(838, 108, 84, 40);
		frmDuplicateInvoice.getContentPane().add(mntmPrint);
		
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Object value = timeSpinner.getValue();
				String time = null;
				if (value instanceof Date) {
		            Date date = (Date)value;
		            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		            time = format.format(date);
		        }
				String invoice=textField.getText();
				if(invoice.equals(""))
				{
					invoice="";
				}
				String date=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if(date.equals(""))
				{
					date="";
				}
				String cust=textField_4.getText();
				if(cust.equals(""))
				{
					cust="";
				}
				String paycode=comboBox.getSelectedItem().toString();
				if(paycode.equals("ALL"))
				{
					paycode="";
				}
				if(paycode.equals("MULTIPLE PAYCODES"))
				{
					paycode="PAYCODE";
				}
				String user=comboBox_1.getSelectedItem().toString();
				if(user.equals("ALL"))
				{
					user="";
				}
				String itemname=textField_5.getText();
				if(itemname.equals(""))
				{
					itemname="";
				}
		    	check_invoice(company,username,store_name,reg_nme,invoice,date,time,cust,paycode,user,itemname,decimalPlaces);
			}
		});
		dateChooser.setDateFormatString("yyyy-MM-dd");
		//dateChooser.setDate(new Date());
		dateChooser.setBounds(135, 10, 154, 22);
		dateChooser.setBounds(129, 85, 100, 22);
		frmDuplicateInvoice.getContentPane().add(dateChooser);
		
		timeSpinner = new JSpinner( new SpinnerDateModel() );
		timeSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Object value = timeSpinner.getValue();
				String time = null;
				if (value instanceof Date) {
		            Date date = (Date)value;
		            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		            time = format.format(date);
		        }
				String invoice=textField.getText();
				if(invoice.equals(""))
				{
					invoice="";
				}
				String date=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if(date.equals(""))
				{
					date="";
				}
				String cust=textField_4.getText();
				if(cust.equals(""))
				{
					cust="";
				}
				String paycode=comboBox.getSelectedItem().toString();
				if(paycode.equals("ALL"))
				{
					paycode="";
				}
				if(paycode.equals("MULTIPLE PAYCODES"))
				{
					paycode="PAYCODE";
				}
				String user=comboBox_1.getSelectedItem().toString();
				if(user.equals("ALL"))
				{
					user="";
				}
				String itemname=textField_5.getText();
				if(itemname.equals(""))
				{
					itemname="";
				}
		    	check_invoice(company,username,store_name,reg_nme,invoice,date,time,cust,paycode,user,itemname,decimalPlaces);
			}
		});
		timeSpinner.setSize(70, 23);
		timeSpinner.setLocation(234, 84);
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		
		
		Date def = null;
		SimpleDateFormat formatterDefault = new SimpleDateFormat("HH:mm:ss");
        try {
            def = formatterDefault.parse("23:59:59");
        } catch (Exception ex) {
             
        }
		timeSpinner.setValue(def); 
        
        
        
		frmDuplicateInvoice.getContentPane().add(timeSpinner);
		
		JButton btnResetFilters = new JButton("Reset Filters");
		btnResetFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				dateChooser.setDate(null);
				textField_4.setText("");
				comboBox.setSelectedItem("ALL");
				textField.setText("");
				comboBox_1.setSelectedItem("ALL");
			}
		});
		btnResetFilters.setBounds(509, 7, 125, 25);
		frmDuplicateInvoice.getContentPane().add(btnResetFilters);
		
		JLabel lblThermalPrinter = new JLabel("Thermal Printer");
		lblThermalPrinter.setForeground(new Color(255, 102, 153));
		lblThermalPrinter.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThermalPrinter.setBounds(709, 11, 141, 25);
		frmDuplicateInvoice.getContentPane().add(lblThermalPrinter);
		
		JMenuItem mntmPreview_1 = new JMenuItem("<html>Preview <br> \u0645\u0639\u0627\u064A\u0646\u0629</html>");
		mntmPreview_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int column = 2;
				int rows = table.getSelectedRow();
				if(rows!=-1)
				{
					String register = table.getModel().getValueAt(rows, 1).toString();
					String value = table.getModel().getValueAt(rows, column).toString();
					frmDuplicateInvoice.setAlwaysOnTop(false);
					duplicate_printA4(company,store_name,value,register,"");
					frmDuplicateInvoice.setAlwaysOnTop(true);
				}
				else
				{
					JOptionPane.showMessageDialog(frmDuplicateInvoice, "<html>Select any row from the table<br>حدد أي صف من الجدول</html>");
				}
			}
		});
		mntmPreview_1.setForeground(new Color(255, 255, 204));
		mntmPreview_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mntmPreview_1.setBorder(null);
		mntmPreview_1.setBackground(Color.DARK_GRAY);
		mntmPreview_1.setBounds(709, 103, 107, 40);
		mntmPreview_1.setIcon(new ImageIcon(reports));
		frmDuplicateInvoice.getContentPane().add(mntmPreview_1);
		
		JMenuItem mntmPreview = new JMenuItem("<html>Preview <br> \u0645\u0639\u0627\u064A\u0646\u0629</html>");
		mntmPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int column = 1;
				int rows = table.getSelectedRow();
				if(rows!=-1)
				{
					String value = table.getModel().getValueAt(rows, 2).toString();
					String register = table.getModel().getValueAt(rows, column).toString();
					frmDuplicateInvoice.setAlwaysOnTop(false);
					duplicate_print(company,store_name,value,printer,register,"");
					frmDuplicateInvoice.setAlwaysOnTop(true);
				}
				else
				{
					JOptionPane.showMessageDialog(frmDuplicateInvoice, "<html>Select any row from the table<br>حدد أي صف من الجدول</html>");
				}
			}
		});
		mntmPreview.setForeground(new Color(255, 255, 204));
		mntmPreview.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mntmPreview.setBorder(null);
		mntmPreview.setBackground(Color.DARK_GRAY);
		mntmPreview.setBounds(708, 40, 108, 40);
		mntmPreview.setIcon(new ImageIcon(reports));
		frmDuplicateInvoice.getContentPane().add(mntmPreview);
		
		check_invoice(company,username,store_name,reg_nme,"","","","","","","",decimalPlaces);
	}
	
	public void duplicate_printA4(String company,String store_name, String invoice_num,String reg_nme, String type) {
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			String printer2="";
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_nme+"' ");
			if(resultSet.next())
			{
				printer2=resultSet.getString("printer2");
			}
			report_master.bill_reciptHistoryA4.report(invoice_num, con, printer2,reg_nme,"DUP",type,company);
			JOptionPane.showMessageDialog(frmDuplicateInvoice,"<html>Transaction complete<br>اكتملت الصفقة</html>");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private void get_paycodes(String company)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store_paycodes ");
			while(resultSet.next())
			{
				String paycode=resultSet.getString("paycode");
	            if(!paycode.equals(""))
	            {
	            	if(!paycode.equals("STORE CREDIT"))
	            	{
	            		comboBox.addItem(paycode);
	            	}
	            }
			}
			comboBox.addItem("VOID");
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	private void get_reg_userlist(String company)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_users ");
			while(resultSet.next())
			{
				comboBox_1.addItem(resultSet.getString("user_name"));
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	private void check_invoice(String company,String username,String store_name,String reg_nme,String invo_num, String date, Object time, String cust, String paycode, String user, String item_name,String decimalPlaces)
	{
		try {
			String query="";
			String query1="";
			if(!invo_num.equals(""))
			{
				query+="and receipt_no like '%"+invo_num+"%' ";
				query1+="and receipt_no like '%"+invo_num+"%' ";
			}
			if(!cust.equals(""))
			{
				query+="and cust_name='"+cust+"' ";
				query1+="and cust_name='"+cust+"' ";
			}
			if(!time.equals(""))
			{
				query+="and time<='"+time+"' ";
				query1+="and time<='"+time+"' ";
			}
			if(!date.equals(""))
			{
				query+="and "+company+".mr_pos_settlement_history.created_date='"+date+"' ";
				query1+="and "+company+".mr_pos_settlement_history.created_date='"+date+"' ";
			}
			String type="";
			if(!paycode.equals(""))
			{
				if(paycode.equals("CASH"))
				{
					type=paycode;
					query+="and type='"+paycode+"' ";
					query1+="and type='PAYCODE' ";
				}
				else if(paycode.equals("A/R"))
				{
					type=paycode;
					query+="and type='"+paycode+"' ";
				}
				else if(paycode.equals("VOID"))
				{
					paycode="VOID";
					query+="and type='"+paycode+"' ";
				}
				else
				{
					type="PAYCODE";
					query+="and type='PAYCODE' ";
				}
			}
			else
			{
				//query+="and type NOT IN ('REFUND','RETURN')";
			}
			if(!user.equals(""))
			{
				query+="and user='"+user+"' ";
				query1+="and user='"+user+"' ";
			}
			if(!item_name.equals(""))
			{
				query+="and item_code='"+item_name+"' or item_desc='"+item_name+"' ";
				query1+="and item_code='"+item_name+"' or item_desc='"+item_name+"' ";
			}
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();
			Statement statement1=con.createStatement();
			String result;
			String result1 = "";
			if(item_name.equals(""))
			{
				if(type.equals("PAYCODE"))
				{
					result="select * from "+company+".mr_pos_settlement_history inner join "+company+".mr_paycode_settlement_history on "+company+".mr_paycode_settlement_history.ref_receipt_num="+company+".mr_pos_settlement_history.receipt_no where store_id='"+store_name+"' "+query+" and mr_paycode_settlement_history.paycode_name='"+paycode+"' limit 0,1000 "; 
				}
				else
				{
					if(type.equals("CASH"))
					{
						result="select * from "+company+".mr_pos_settlement_history where store_id='"+store_name+"' "+query+"  limit 0,1000";
						result1="select * from "+company+".mr_pos_settlement_history inner join "+company+".mr_paycode_settlement_history on "+company+".mr_paycode_settlement_history.ref_receipt_num="+company+".mr_pos_settlement_history.receipt_no where store_id='"+store_name+"' "+query1+" and mr_paycode_settlement_history.paycode_name='"+paycode+"'  limit 0,1000";
					}
					else
					{
						result="select * from "+company+".mr_pos_settlement_history where store_id='"+store_name+"' "+query+"  limit 0,1000";
					}
					
					
				}
					
			}
			else
			{
				result="select * from "+company+".mr_pos_settlement_history inner join "+company+".mr_pos_settlement_history_items on mr_pos_settlement_history.receipt_no=mr_pos_settlement_history_items.ref_receipt_num where store_id='"+store_name+"' "+query+"   limit 0,1000";	
			}
			ResultSet resultSet = statement.executeQuery(result);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			while(resultSet.next())
			{
				String reg_num=resultSet.getString("reg_num");
				String inv_num=resultSet.getString("receipt_no");
				String inv_date=resultSet.getString("created_date");
				String inv_time=resultSet.getString("time");
				String cust_name=resultSet.getString("cust_name");
				String qty=resultSet.getString("qty");
				String discount=resultSet.getString("disc");
				String vat=resultSet.getString("vat");
				String total=resultSet.getString("total");
				String types=resultSet.getString("type");
				double sub_ttl= Double.parseDouble(total)+Double.parseDouble(vat);
				double sub_disc= Double.parseDouble(total)+Double.parseDouble(discount);
				BigDecimal br = new BigDecimal(sub_disc);
        		br = br.setScale(Integer.parseInt(decimalPlaces), BigDecimal.ROUND_HALF_UP);
        		sub_disc = br.doubleValue();
				model.addRow(new Object[]{model.getRowCount()+1,reg_num,inv_num,inv_date,inv_time,cust_name,qty,sub_disc,discount,total,vat,sub_ttl,types});
			}
			if(!result1.equals(""))
			{
				ResultSet resultSet1 = statement1.executeQuery(result1);
				while(resultSet1.next())
				{
					String reg_num=resultSet.getString("reg_num");
					String inv_num=resultSet1.getString("receipt_no");
					String inv_date=resultSet1.getString("created_date");
					String inv_time=resultSet1.getString("time");
					String cust_name=resultSet1.getString("cust_name");
					String qty=resultSet1.getString("qty");
					String discount=resultSet1.getString("disc");
					String vat=resultSet1.getString("vat");
					String total=resultSet1.getString("total");
					String types=resultSet1.getString("type");
					double sub_ttl= Double.parseDouble(total)+Double.parseDouble(vat);
					double sub_disc= Double.parseDouble(total)+Double.parseDouble(discount);
					BigDecimal br = new BigDecimal(sub_disc);
	        		br = br.setScale(Integer.parseInt(decimalPlaces), BigDecimal.ROUND_HALF_UP);
	        		sub_disc = br.doubleValue();
					model.addRow(new Object[]{model.getRowCount()+1,reg_num,inv_num,inv_date,inv_time,cust_name,qty,sub_disc,discount,total,vat,sub_ttl,types});
				}
			}
			
			
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	public void duplicate_print(String company,String store_name, String invoice_num,String printer,String reg_nme, String type) {
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			report_master.bill_reciptHistory.report(invoice_num, con, printer,reg_nme,"DUP",type,company);
			JOptionPane.showMessageDialog(frmDuplicateInvoice,"<html>Transaction complete<br>اكتملت الصفقة</html>");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private void click_btn(String num)
	{
		try{
			String pass_string=textField.getText();
			pass_string+=num;
			textField.setText(pass_string);
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
