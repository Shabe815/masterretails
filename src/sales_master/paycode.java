package sales_master;

import java.awt.EventQueue;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import org.json.JSONException;

import CafePro.Mainpage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class paycode {

	//private static final String button_ = null;
	static JFrame frmMultiplePayCode;
	private static JPanel panel_1;
	static JTextField textField_7;
	private static  JTextField textField_19;
	private static  JTextField textField_12;
	private static  JTextField textField;
	private static JTextField textField_1;
	private static  JTextField textField_2;
	private static  JTextField textField_4;
	private static  JTextField textField_5;
	private static JTextField textField_6;
	private static  JTextField textField_8;
	private static  JTextField textField_9;
	private static JTextField textField_10;
	private static  JTextField textField_11;
	private static  JTextField textField_13;
	private static  JTextField textField_14;
	private static  JTextField textField_15;
	private static  JTextField textField_16;
	private static  JTextField textField_17;
	private static  JTextField textField_18;
	private static  JTextField textField_20;
	private static  JTextField textField_21;
	private static  JTextField textField_22;
	private static  JTextField textField_23;
	private static  JTextField textField_24;
	private static  JTextField textField_25;
	private static  JTextField textField_26;
	private static  JTextField textField_27;
	private static  JTextField textField_32;
	private static  JTextField textField_33;
	private static  JTextField textField_34;
	
	private JButton btnSettleF;
	
	private static  JButton btncashf;
	private static  JButton btnAr;
	private static  JButton btnVisa;
	//private JButton btnMastercard;
	private static  JButton button_1;
	private static  JButton button_2;
	private static JButton button_3;
	private static  JButton button_4;
	private static  JButton button_5;
	private static  JButton button_6;
	private static  JButton button_7;
	private static  JButton button_8;
	private static  JButton button_9;
	private static  JButton button_10;
	private static  JButton button_11;
//	private JButton button_x;
	private boolean dot = false;
	private static JTextField textField_28;
	private static String printer;
	static JButton btnStoreCredit;
	static JTextField textField_29;
	static JTextField textField_30;
	private static  JTextField textField_3;
	private static  JTextField textField_31;
	private static  JTextField textField_35;
	private static  JTextField textField_36;
	private static  JButton btnBackToPos;
	
	private static String balancetxt="";
	private static String discperc="";
	private static String discamt="";
	private static String ttlamt="";
	private static String ttlvat="";
	private static String roundoff="";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					paycode window = new paycode("mr_pro","admin","S01","","","","Shabeer","50", null,"",2,"","","");
					window.frmMultiplePayCode.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param total_amt 
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param params 
	 * @param type 
	 * @param receipt_no 
	 * @param reg_num 
	 * @param cust_name 
	 * @param disc_amt 
	 * @param disc_applied 
	 * @param perc 
	 */
	public paycode(String company, String username, String store_name, String type,  String reg_num, String receipt_no, String cust_name, String disc_amt,String version,String qty,int decimal,String store_credit_amt,String Credit_list,String cust_code) {
		initialize(company,username,store_name,type,reg_num,receipt_no,cust_name,disc_amt, version,qty,decimal,store_credit_amt,Credit_list,cust_code);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param total_amt 
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param params 
	 * @param type 
	 * @param receipt_no 
	 * @param reg_num 
	 * @param disc_amt 
	 * @param store_credit_amt 
	 * @param disc_applied 
	 * @param perc 
	 */
	private void initialize(final String company,final String username,final String store_name, final String type,final String reg_num,final String receipt_no,final String cust_name, final String disc_amt,final String version,final String qty,final int decimal,final String store_credit_amt,final String Credit_list,final String cust_code) {
		frmMultiplePayCode = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frmMultiplePayCode.setIconImage(img);
		frmMultiplePayCode.getContentPane().setBackground(Color.DARK_GRAY);
		frmMultiplePayCode.setTitle("MULTIPLE PAY CODE SCREEN:");
		frmMultiplePayCode.setBounds(100, 100, 859, 782);
		frmMultiplePayCode.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmMultiplePayCode.getContentPane().setLayout(null);
		frmMultiplePayCode.setVisible(true);
		frmMultiplePayCode.setLocationRelativeTo(null);
		frmMultiplePayCode.setAlwaysOnTop(true);
		frmMultiplePayCode.setResizable(false);
		
		
		if(Mainpage.SoftLang.equals("Ar"))
		{
			balancetxt=PosscreenArabic.textField_3.getText();
			discperc=PosscreenArabic.textField_5.getText();
			discamt=PosscreenArabic.textField_2.getText();
			ttlamt=PosscreenArabic.textField.getText();
			ttlvat=PosscreenArabic.textField_1.getText();
			roundoff=PosscreenArabic.lblNewLabel_1.getText();
			
		}
		else
		{
			balancetxt=posscreen.textField_3.getText();
			discperc=posscreen.textField_5.getText();
			discamt=posscreen.textField_2.getText();
			ttlamt=posscreen.textField.getText();
			ttlvat=posscreen.textField_1.getText();
			roundoff=posscreen.lblNewLabel_1.getText();
		}
		
		
		textField_7 = new JTextField(balancetxt);
		textField_7.setEditable(false);
		textField_7.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_7.setForeground(Color.WHITE);
		textField_7.setFont(new Font("Tahoma", Font.BOLD, 25));
		textField_7.setColumns(10);
		textField_7.setBackground(Color.RED);
		textField_7.setBounds(639, 192, 190, 30);
		frmMultiplePayCode.getContentPane().add(textField_7);
		
		textField_19 = new JTextField();
		textField_19.setEditable(false);
		textField_19.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_19.setForeground(Color.WHITE);
		textField_19.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField_19.setColumns(10);
		textField_19.setBackground(Color.BLACK);
		textField_19.setBounds(639, 155, 190, 30);
		frmMultiplePayCode.getContentPane().add(textField_19);
		
		JLabel lblBalance = new JLabel("<html>BALANCE<br>\u062A\u0648\u0627\u0632\u0646</html>");
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalance.setForeground(Color.LIGHT_GRAY);
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBalance.setBackground(Color.RED);
		lblBalance.setBounds(508, 196, 131, 35);
		frmMultiplePayCode.getContentPane().add(lblBalance);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);
		panel.setBounds(639, 229, 190, 313);
		frmMultiplePayCode.getContentPane().add(panel);
		
		JButton button_12 = new JButton("1");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("1");
			}
		});
		button_12.setFont(new Font("Arial", Font.BOLD, 30));
		button_12.setBounds(0, 53, 60, 60);
		panel.add(button_12);
		
		JButton button_13 = new JButton("2");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("2");
			}
		});
		button_13.setFont(new Font("Arial", Font.BOLD, 30));
		button_13.setBounds(65, 53, 60, 60);
		panel.add(button_13);
		
		JButton button_14 = new JButton("3");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("3");
			}
		});
		button_14.setFont(new Font("Arial", Font.BOLD, 30));
		button_14.setBounds(130, 53, 60, 60);
		panel.add(button_14);
		
		JButton button_15 = new JButton("4");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("4");
			}
		});
		button_15.setFont(new Font("Arial", Font.BOLD, 30));
		button_15.setBounds(0, 118, 60, 60);
		panel.add(button_15);
		
		JButton button_16 = new JButton("5");
		button_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("5");
			}
		});
		button_16.setFont(new Font("Arial", Font.BOLD, 30));
		button_16.setBounds(65, 118, 60, 60);
		panel.add(button_16);
		
		JButton button_17 = new JButton("6");
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("6");
			}
		});
		button_17.setFont(new Font("Arial", Font.BOLD, 30));
		button_17.setBounds(130, 118, 60, 60);
		panel.add(button_17);
		
		JButton button_18 = new JButton("7");
		button_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("7");
			}
		});
		button_18.setFont(new Font("Arial", Font.BOLD, 30));
		button_18.setBounds(0, 183, 60, 60);
		panel.add(button_18);
		
		JButton button_19 = new JButton("8");
		button_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("8");
			}
		});
		button_19.setFont(new Font("Arial", Font.BOLD, 30));
		button_19.setBounds(65, 183, 60, 60);
		panel.add(button_19);
		
		JButton button_20 = new JButton("9");
		button_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("9");
			}
		});
		button_20.setFont(new Font("Arial", Font.BOLD, 30));
		button_20.setBounds(130, 183, 60, 60);
		panel.add(button_20);
		
		JButton button_21 = new JButton("0");
		button_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("0");
			}
		});
		button_21.setFont(new Font("Arial", Font.BOLD, 30));
		button_21.setBounds(65, 248, 60, 60);
		panel.add(button_21);
		
		JButton button_22 = new JButton("<<");
		button_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField_12.getText().equals(""))
				{
					textField_12.setText(textField_12.getText().substring(0, textField_12.getText().length() - 1));
				}
			}
		});
		button_22.setFont(new Font("Arial", Font.BOLD, 20));
		button_22.setBounds(130, 248, 60, 60);
		panel.add(button_22);
		
		JButton button_23 = new JButton(".");
		button_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn(".");
			}
		});
		button_23.setFont(new Font("Arial", Font.BOLD, 30));
		button_23.setBounds(0, 248, 60, 60);
		panel.add(button_23);
		
		
		textField_12 = new JTextField("");
		textField_12.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_12.getText().equals(""))
			        dot = false;
			    if (dot == false){
			        if (vChar == '.') dot = true;
			        else if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    } else {
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    }
			}
		});
		textField_12.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLUE, null, null, null));
		textField_12.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_12.setForeground(Color.DARK_GRAY);
		textField_12.setFont(new Font("Tahoma", Font.PLAIN, 40));
		textField_12.setColumns(10);
		textField_12.setBackground(new Color(176, 224, 230));
		textField_12.setBounds(0, 0, 190, 50);
		panel.add(textField_12);
		
		
		JLabel lblTotalAmtPaid = new JLabel("<html>TOTAL AMT. PAID<br>\u0627\u0644\u0645\u0628\u0644\u063A \u0627\u0644\u0625\u062C\u0645\u0627\u0644\u064A \u0627\u0644\u0645\u062F\u0641\u0648\u0639</html>");
		lblTotalAmtPaid.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAmtPaid.setForeground(Color.LIGHT_GRAY);
		lblTotalAmtPaid.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalAmtPaid.setBackground(Color.RED);
		lblTotalAmtPaid.setBounds(508, 155, 131, 35);
		frmMultiplePayCode.getContentPane().add(lblTotalAmtPaid);
		
		JButton btnClear = new JButton("<html>CLEAR<br> \u0642\u064A\u0645 \u0648\u0627\u0636\u062D\u0629</html>");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_19.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_29.setText("");
				textField_4.setText("");
				textField_32.setText("");
				textField_8.setText("");
				textField_27.setText("");
				textField_6.setText("");
				textField_26.setText("");
				textField_5.setText("");
				textField_25.setText("");
				textField_16.setText("");
				textField_17.setText("");
				textField_9.setText("");
				textField_18.setText("");
				textField_15.setText("");
				textField_20.setText("");
				textField_10.setText("");
				textField_21.setText("");
				textField_11.setText("");
				textField_22.setText("");
				textField_13.setText("");
				textField_24.setText("");
				textField_14.setText("");
				textField_23.setText("");
				textField_34.setText("");
				textField_33.setText("");
				textField_7.setText(balancetxt);
			}
		});
		btnClear.setToolTipText("F12");
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Arial", Font.BOLD, 20));
		btnClear.setBorder(new LineBorder(SystemColor.control));
		btnClear.setBackground(new Color(51, 102, 204));
		btnClear.setBounds(639, 549, 190, 50);
		frmMultiplePayCode.getContentPane().add(btnClear);
		
		btnBackToPos = new JButton("<html>BACK TO POS (ESC)<br>&nbsp; &nbsp; &nbsp; \u0627\u0644\u0639\u0648\u062F\u0629 \u0625\u0644\u0649 \u0646\u0642\u0637\u0629 \u0627\u0644\u0628\u064A\u0639  </html>");
		btnBackToPos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMultiplePayCode.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.btnPayCode.setEnabled(true);
					PosscreenArabic.txtDisc.requestFocus();
				}
				else
				{
					posscreen.btnPayCode.setEnabled(true);
					posscreen.txtDisc.requestFocus();
				}
				
			}
		});
		btnBackToPos.setToolTipText("ESC");
		btnBackToPos.setForeground(Color.DARK_GRAY);
		btnBackToPos.setFont(new Font("Arial", Font.BOLD, 15));
		btnBackToPos.setBorder(new LineBorder(SystemColor.control));
		btnBackToPos.setBackground(Color.PINK);
		btnBackToPos.setBounds(639, 606, 190, 50);
		frmMultiplePayCode.getContentPane().add(btnBackToPos);
		
		btnSettleF = new JButton("<html>SETTLE (F10)<br> &nbsp; &nbsp; &nbsp; &nbsp; \u064A\u0633\u062A\u0642\u0631</html>");
		btnSettleF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				getsettled(company, receipt_no, reg_num, cust_name, store_name, username, qty, type, cust_code, decimal, version);
			}
		});
		btnSettleF.setToolTipText("F12");
		btnSettleF.setForeground(Color.GRAY);
		btnSettleF.setFont(new Font("Arial", Font.BOLD, 20));
		btnSettleF.setBorder(new LineBorder(SystemColor.control));
		btnSettleF.setBackground(new Color(204, 255, 153));
		btnSettleF.setBounds(639, 663, 190, 77);
		frmMultiplePayCode.getContentPane().add(btnSettleF);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 504, 647);
		frmMultiplePayCode.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		btncashf = new JButton("<html>CASH (F1)<br>\u0646\u0642\u062F\u064A</html>");
		btncashf.setMnemonic('C');
		btncashf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_12.getText();
				if(amt.equals(""))
				{
					amt=textField_7.getText();
				}
				textField.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
			}
		});
		btncashf.setForeground(Color.DARK_GRAY);
		btncashf.setFont(new Font("Arial", Font.BOLD, 15));
		btncashf.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		btncashf.setBackground(Color.LIGHT_GRAY);
		btncashf.setBounds(12, 9, 142, 35);
		panel_1.add(btncashf);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setForeground(new Color(224, 255, 255));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField.setColumns(10);
		textField.setBackground(Color.BLACK);
		textField.setBounds(154, 9, 140, 35);
		panel_1.add(textField);
		DocumentFilter dl = new DocumentFilter() {
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
               if (!textField.getText().isEmpty()) {
                	String amt1="0";
                    String amt2="0";
                    String amt3="0";
                    String amt4="0";
                    String amt5="0";
                    String amt6="0";
                    String amt7="0";
                    String amt8="0";
                    String amt9="0";
                    String amt10="0";
                    String amt11="0";
                    String amt12="0";
                    String amt13="0";
                    String amt14="0";
                    String amt16="0";
                    if(!textField_32.getText().equals(""))
                    {
                    	amt1=textField_32.getText();
                    }
                    if(!textField_27.getText().equals(""))
                    {
                    	amt2=textField_27.getText();
                    }
                    if(!textField_26.getText().equals(""))
                    {
                    	amt3=textField_26.getText();
                    }
                    if(!textField_25.getText().equals(""))
                    {
                    	amt4=textField_25.getText();
                    }
                    if(!textField_17.getText().equals(""))
                    {
                    	amt5=textField_17.getText();
                    }
                    if(!textField_18.getText().equals(""))
                    {
                    	amt6=textField_18.getText();
                    }
                    if(!textField_20.getText().equals(""))
                    {
                    	amt7=textField_20.getText();
                    }
                    if(!textField_21.getText().equals(""))
                    {
                    	amt8=textField_21.getText();
                    }
                    if(!textField_22.getText().equals(""))
                    {
                    	amt9=textField_22.getText();
                    }
                    if(!textField_23.getText().equals(""))
                    {
                    	amt10=textField_23.getText();
                    }
                    if(!textField_24.getText().equals(""))
                    {
                    	amt11=textField_24.getText();
                    }
                    if(!textField.getText().equals(""))
                    {
                    	amt12=textField.getText();
                    }
                    if(!textField_1.getText().equals(""))
                    {
                    	amt13=textField_1.getText();
                    }
                    if(!textField_2.getText().equals(""))
                    {
                    	amt14=textField_2.getText();
                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
                    if(!textField_29.getText().equals(""))
                    {
                    	amt16=textField_29.getText();
                    }
                    
                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
                    		Double.parseDouble(amt14)+/*Double.parseDouble(amt15)*/+Double.parseDouble(amt16);
                    BigDecimal bc = new BigDecimal(sum);
            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		sum = bc.doubleValue();
                    textField_19.setText(Double.toString(sum));
                    
                    double bal=Double.parseDouble(balancetxt)-sum;
                    BigDecimal ba = new BigDecimal(bal);
            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		bal = ba.doubleValue();
                    textField_7.setText(Double.toString(bal));
                }
            }
        };
		((AbstractDocument) (textField.getDocument())).setDocumentFilter(dl);
		
		btnAr = new JButton("<html>A/R (F2)<br>\u0645\u0642\u062F\u0627\u0631 \u0627\u0644\u062E\u0635\u0645 </html>");
		btnAr.setMnemonic('A');
		btnAr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_12.getText();
				if(amt.equals(""))
				{
					amt=textField_7.getText();
				}
				if(Float.parseFloat(textField_28.getText()) >= Float.parseFloat(amt))
				{
					textField_1.setText(amt);
					textField_12.setText("");
					textField_12.requestFocus();
				}
				else
				{
					JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>Do not have sufficient Amount / Choose A Customer<br>ليس لديك المبلغ الكافي / اختر عميلاً</html>");
				}
				
			}
		});
		btnAr.setForeground(Color.DARK_GRAY);
		btnAr.setFont(new Font("Arial", Font.BOLD, 15));
		btnAr.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		btnAr.setBackground(Color.LIGHT_GRAY);
		btnAr.setBounds(12, 48, 142, 35);
		panel_1.add(btnAr);
		
		btnVisa = new JButton("<html>VISA (F3)<br>\u0628\u0637\u0627\u0642\u0629 \u0641\u064A\u0632\u0627</html>");
		btnVisa.setMnemonic('V');
		btnVisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_12.getText();
				if(amt.equals(""))
				{
					amt=textField_7.getText();
				}
				else if(Double.parseDouble(amt)==Double.parseDouble(textField_7.getText()))
				{
					amt=textField_7.getText();
				}
				else if(Double.parseDouble(amt)<Double.parseDouble(textField_7.getText()))
				{
					amt=textField_12.getText();
					
				}
				else
				{
					JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>Settling Amount Not Equals To Sub Total Amount<br>مبلغ التسوية لا يساوي المبلغ الإجمالي الفرعي</html>");
					return;
				}
				textField_2.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
			}
		});
		btnVisa.setForeground(Color.DARK_GRAY);
		btnVisa.setFont(new Font("Arial", Font.BOLD, 15));
		btnVisa.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		btnVisa.setBackground(Color.LIGHT_GRAY);
		btnVisa.setBounds(12, 87, 142, 35);
		panel_1.add(btnVisa);
		
		
//		btnMastercard = new JButton("MASTER CARD");
//		btnMastercard.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				String amt=textField_12.getText();
//				if(amt.equals(""))
//				{
//					amt=textField_7.getText();
//				}
//				textField_3.setText(amt);
//				textField_12.setText("");
//			}
//		});
//		btnMastercard.setToolTipText("F12");
//		btnMastercard.setForeground(Color.DARK_GRAY);
//		btnMastercard.setFont(new Font("Arial", Font.BOLD, 15));
//		btnMastercard.setBorder(new LineBorder(SystemColor.control));
//		btnMastercard.setBackground(Color.LIGHT_GRAY);
//		btnMastercard.setBounds(12, 118, 142, 30);
//		panel_1.add(btnMastercard);
		
		
		button_4 = new JButton();
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_12.getText();
				double multiply1 = 0;
               if(amt.equals(""))
				{
					String val= button_4.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_7.getText())/Double.parseDouble(split[1]);
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    
					amt=String.valueOf(multiply1);
				}
				textField_5.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
			}
		});
		button_4.setEnabled(false);
		button_4.setToolTipText("F12");
		button_4.setForeground(Color.DARK_GRAY);
		button_4.setFont(new Font("Arial", Font.BOLD, 13));
		button_4.setBorder(new LineBorder(Color.GRAY, 2, true));
		button_4.setBackground(Color.LIGHT_GRAY);
		button_4.setBounds(12, 321, 142, 30);
		panel_1.add(button_4);
		
		button_5 = new JButton();
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_12.getText();
				double multiply1 = 0;
				if(amt.equals(""))
				{
					String val= button_5.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_7.getText())/Double.parseDouble(split[1]);
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    
					amt=String.valueOf(multiply1);
				}
				textField_16.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
			}
		});
		button_5.setEnabled(false);
		button_5.setToolTipText("F12");
		button_5.setForeground(Color.DARK_GRAY);
		button_5.setFont(new Font("Arial", Font.BOLD, 13));
		button_5.setBorder(new LineBorder(Color.GRAY, 2, true));
		button_5.setBackground(Color.LIGHT_GRAY);
		button_5.setBounds(12, 360, 142, 30);
		panel_1.add(button_5);
		
		button_6 = new JButton();
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_12.getText();
				double multiply1 = 0;
                if(amt.equals(""))
				{
					String val= button_6.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_7.getText())/Double.parseDouble(split[1]);
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    
					amt=String.valueOf(multiply1);
				}
				textField_9.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
			}
		});
		button_6.setEnabled(false);
		button_6.setToolTipText("F12");
		button_6.setForeground(Color.DARK_GRAY);
		button_6.setFont(new Font("Arial", Font.BOLD, 13));
		button_6.setBorder(new LineBorder(Color.GRAY, 2, true));
		button_6.setBackground(Color.LIGHT_GRAY);
		button_6.setBounds(12, 399, 142, 30);
		panel_1.add(button_6);
		
		button_7 = new JButton();
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_12.getText();
				double multiply1 = 0;
				if(amt.equals(""))
				{
					String val= button_7.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_7.getText())/Double.parseDouble(split[1]);
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    
					amt=String.valueOf(multiply1);
				}
				textField_15.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
			}
		});
		button_7.setEnabled(false);
		button_7.setToolTipText("F12");
		button_7.setForeground(Color.DARK_GRAY);
		button_7.setFont(new Font("Arial", Font.BOLD, 13));
		button_7.setBorder(new LineBorder(Color.GRAY, 2, true));
		button_7.setBackground(Color.LIGHT_GRAY);
		button_7.setBounds(12, 438, 142, 30);
		panel_1.add(button_7);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setForeground(new Color(224, 255, 255));
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.BLACK);
		textField_1.setBounds(154, 48, 140, 35);
		panel_1.add(textField_1);
		DocumentFilter dm = new DocumentFilter() {
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
            	if (!textField_1.getText().isEmpty()) {
                	String amt1="0";
                    String amt2="0";
                    String amt3="0";
                    String amt4="0";
                    String amt5="0";
                    String amt6="0";
                    String amt7="0";
                    String amt8="0";
                    String amt9="0";
                    String amt10="0";
                    String amt11="0";
                    String amt12="0";
                    String amt13="0";
                    String amt14="0";
                    String amt16="0";
                    if(!textField_32.getText().equals(""))
                    {
                    	amt1=textField_32.getText();
                    }
                    if(!textField_27.getText().equals(""))
                    {
                    	amt2=textField_27.getText();
                    }
                    if(!textField_26.getText().equals(""))
                    {
                    	amt3=textField_26.getText();
                    }
                    if(!textField_25.getText().equals(""))
                    {
                    	amt4=textField_25.getText();
                    }
                    if(!textField_17.getText().equals(""))
                    {
                    	amt5=textField_17.getText();
                    }
                    if(!textField_18.getText().equals(""))
                    {
                    	amt6=textField_18.getText();
                    }
                    if(!textField_20.getText().equals(""))
                    {
                    	amt7=textField_20.getText();
                    }
                    if(!textField_21.getText().equals(""))
                    {
                    	amt8=textField_21.getText();
                    }
                    if(!textField_22.getText().equals(""))
                    {
                    	amt9=textField_22.getText();
                    }
                    if(!textField_23.getText().equals(""))
                    {
                    	amt10=textField_23.getText();
                    }
                    if(!textField_24.getText().equals(""))
                    {
                    	amt11=textField_24.getText();
                    }
                    if(!textField.getText().equals(""))
                    {
                    	amt12=textField.getText();
                    }
                    if(!textField_1.getText().equals(""))
                    {
                    	amt13=textField_1.getText();
                    }
                    if(!textField_2.getText().equals(""))
                    {
                    	amt14=textField_2.getText();
                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
                    if(!textField_29.getText().equals(""))
                    {
                    	amt16=textField_29.getText();
                    }
                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
                    		Double.parseDouble(amt14)+/*Double.parseDouble(amt15)*/+Double.parseDouble(amt16);
                    BigDecimal bc = new BigDecimal(sum);
            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		sum = bc.doubleValue();
                    textField_19.setText(Double.toString(sum));
                    
                    double bal=Double.parseDouble(balancetxt)-sum;
                    BigDecimal ba = new BigDecimal(bal);
            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		bal = ba.doubleValue();
                    textField_7.setText(Double.toString(bal));
                }
            }
        };
		((AbstractDocument) (textField_1.getDocument())).setDocumentFilter(dm);
		
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setForeground(new Color(224, 255, 255));
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_2.setColumns(10);
		textField_2.setBackground(Color.BLACK);
		textField_2.setBounds(154, 87, 140, 35);
		panel_1.add(textField_2);
		DocumentFilter dn = new DocumentFilter() {
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
            	if (!textField_2.getText().isEmpty()) {
                	String amt1="0";
                    String amt2="0";
                    String amt3="0";
                    String amt4="0";
                    String amt5="0";
                    String amt6="0";
                    String amt7="0";
                    String amt8="0";
                    String amt9="0";
                    String amt10="0";
                    String amt11="0";
                    String amt12="0";
                    String amt13="0";
                    String amt14="0";
                    String amt16="0";
                    if(!textField_32.getText().equals(""))
                    {
                    	amt1=textField_32.getText();
                    }
                    if(!textField_27.getText().equals(""))
                    {
                    	amt2=textField_27.getText();
                    }
                    if(!textField_26.getText().equals(""))
                    {
                    	amt3=textField_26.getText();
                    }
                    if(!textField_25.getText().equals(""))
                    {
                    	amt4=textField_25.getText();
                    }
                    if(!textField_17.getText().equals(""))
                    {
                    	amt5=textField_17.getText();
                    }
                    if(!textField_18.getText().equals(""))
                    {
                    	amt6=textField_18.getText();
                    }
                    if(!textField_20.getText().equals(""))
                    {
                    	amt7=textField_20.getText();
                    }
                    if(!textField_21.getText().equals(""))
                    {
                    	amt8=textField_21.getText();
                    }
                    if(!textField_22.getText().equals(""))
                    {
                    	amt9=textField_22.getText();
                    }
                    if(!textField_23.getText().equals(""))
                    {
                    	amt10=textField_23.getText();
                    }
                    if(!textField_24.getText().equals(""))
                    {
                    	amt11=textField_24.getText();
                    }
                    if(!textField.getText().equals(""))
                    {
                    	amt12=textField.getText();
                    }
                    if(!textField_1.getText().equals(""))
                    {
                    	amt13=textField_1.getText();
                    }
                    if(!textField_2.getText().equals(""))
                    {
                    	amt14=textField_2.getText();
                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
                    if(!textField_29.getText().equals(""))
                    {
                    	amt16=textField_29.getText();
                    }
                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
                    		Double.parseDouble(amt14)+/*Double.parseDouble(amt15)*/+Double.parseDouble(amt16);
                    BigDecimal bc = new BigDecimal(sum);
            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		sum = bc.doubleValue();
                    textField_19.setText(Double.toString(sum));
                    
                    double bal=Double.parseDouble(balancetxt)-sum;
                    BigDecimal ba = new BigDecimal(bal);
            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		bal = ba.doubleValue();
                    textField_7.setText(Double.toString(bal));
                }
            }
        };
		((AbstractDocument) (textField_2.getDocument())).setDocumentFilter(dn);
		
		
//		textField_3 = new JTextField();
//		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
//		textField_3.setForeground(new Color(224, 255, 255));
//		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 28));
//		textField_3.setColumns(10);
//		textField_3.setBackground(Color.BLACK);
//		textField_3.setBounds(154, 118, 140, 30);
//		panel_1.add(textField_3);
//		
//		DocumentFilter dp = new DocumentFilter() {
//            @Override
//            public void insertString(FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {
//
//                if (isDigit(string)) {
//                    super.insertString(fb, i, string, as);
//                    calcAndSetTotal();
//                }
//            }
//
//            @Override
//            public void remove(FilterBypass fb, int i, int i1) throws BadLocationException {
//                super.remove(fb, i, i1);
//                calcAndSetTotal();
//            }
//
//            @Override
//            public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
//                if (isDigit(string)) {
//                    super.replace(fb, i, i1, string, as);
//                    calcAndSetTotal();
//
//                }
//            }
//
//            private boolean isDigit(String string) {
//                for (int n = 0; n < string.length(); n++) {
//                    char c = string.charAt(n);
//                    if(c!='.')
//                    {
//                    	if (!Character.isDigit(c)) {
//                            return false;
//                        }
//                    }
//                    else
//                    {
//                    	return true;
//                    }
//                    
//                }
//                return true;
//            }
//
//            void calcAndSetTotal() {
//            	if (!textField_3.getText().isEmpty()) {
//                	String amt1="0";
//                    String amt2="0";
//                    String amt3="0";
//                    String amt4="0";
//                    String amt5="0";
//                    String amt6="0";
//                    String amt7="0";
//                    String amt8="0";
//                    String amt9="0";
//                    String amt10="0";
//                    String amt11="0";
//                    String amt12="0";
//                    String amt13="0";
//                    String amt14="0";
//                    String amt15="0";
//                    String amt16="0";
//                    
//                    if(!textField_32.getText().equals(""))
//                    {
//                    	amt1=textField_32.getText();
//                    }
//                    if(!textField_27.getText().equals(""))
//                    {
//                    	amt2=textField_27.getText();
//                    }
//                    if(!textField_26.getText().equals(""))
//                    {
//                    	amt3=textField_26.getText();
//                    }
//                    if(!textField_25.getText().equals(""))
//                    {
//                    	amt4=textField_25.getText();
//                    }
//                    if(!textField_17.getText().equals(""))
//                    {
//                    	amt5=textField_17.getText();
//                    }
//                    if(!textField_18.getText().equals(""))
//                    {
//                    	amt6=textField_18.getText();
//                    }
//                    if(!textField_20.getText().equals(""))
//                    {
//                    	amt7=textField_20.getText();
//                    }
//                    if(!textField_21.getText().equals(""))
//                    {
//                    	amt8=textField_21.getText();
//                    }
//                    if(!textField_22.getText().equals(""))
//                    {
//                    	amt9=textField_22.getText();
//                    }
//                    if(!textField_23.getText().equals(""))
//                    {
//                    	amt10=textField_23.getText();
//                    }
//                    if(!textField_24.getText().equals(""))
//                    {
//                    	amt11=textField_24.getText();
//                    }
//                    if(!textField.getText().equals(""))
//                    {
//                    	amt12=textField.getText();
//                    }
//                    if(!textField_1.getText().equals(""))
//                    {
//                    	amt13=textField_1.getText();
//                    }
//                    if(!textField_2.getText().equals(""))
//                    {
//                    	amt14=textField_2.getText();
//                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
//                    if(!textField_29.getText().equals(""))
//                    {
//                    	amt16=textField_29.getText();
//                    }
//                    if(!textField_33.getText().equals(""))
//                    {
//                    	disc=textField_33.getText();
//                    }
//                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
//                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
//                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
//                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
//                    		Double.parseDouble(amt14)+Double.parseDouble(amt15)+Double.parseDouble(amt16)+Double.parseDouble(disc);
//                    BigDecimal bc = new BigDecimal(sum);
//            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
//            		sum = bc.doubleValue();
//                    textField_19.setText(Double.toString(sum));
//                    
//                    double bal=Double.parseDouble(total_amt)-sum;
//                    BigDecimal ba = new BigDecimal(bal);
//            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
//            		bal = ba.doubleValue();
//                    textField_7.setText(Double.toString(bal));
//                }
//            }
//        };
//		((AbstractDocument) (textField_3.getDocument())).setDocumentFilter(dp);
		
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_4.setForeground(new Color(224, 255, 255));
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_4.setColumns(10);
		textField_4.setBackground(Color.BLACK);
		textField_4.setBounds(154, 204, 140, 30);
		panel_1.add(textField_4);
		
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
                double multiply1 = 0;
                if (!textField_4.getText().isEmpty()) {
                	String val= button_1.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_4.getText())*Double.parseDouble(split[1]);
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    textField_32.setText(String.valueOf(multiply1));
                    String amt1="0";
                    String amt2="0";
                    String amt3="0";
                    String amt4="0";
                    String amt5="0";
                    String amt6="0";
                    String amt7="0";
                    String amt8="0";
                    String amt9="0";
                    String amt10="0";
                    String amt11="0";
                    String amt12="0";
                    String amt13="0";
                    String amt14="0";
                    String amt16="0";
                    
                    if(!textField_32.getText().equals(""))
                    {
                    	amt1=textField_32.getText();
                    }
                    if(!textField_27.getText().equals(""))
                    {
                    	amt2=textField_27.getText();
                    }
                    if(!textField_26.getText().equals(""))
                    {
                    	amt3=textField_26.getText();
                    }
                    if(!textField_25.getText().equals(""))
                    {
                    	amt4=textField_25.getText();
                    }
                    if(!textField_17.getText().equals(""))
                    {
                    	amt5=textField_17.getText();
                    }
                    if(!textField_18.getText().equals(""))
                    {
                    	amt6=textField_18.getText();
                    }
                    if(!textField_20.getText().equals(""))
                    {
                    	amt7=textField_20.getText();
                    }
                    if(!textField_21.getText().equals(""))
                    {
                    	amt8=textField_21.getText();
                    }
                    if(!textField_22.getText().equals(""))
                    {
                    	amt9=textField_22.getText();
                    }
                    if(!textField_23.getText().equals(""))
                    {
                    	amt10=textField_23.getText();
                    }
                    if(!textField_24.getText().equals(""))
                    {
                    	amt11=textField_24.getText();
                    }
                    if(!textField.getText().equals(""))
                    {
                    	amt12=textField.getText();
                    }
                    if(!textField_1.getText().equals(""))
                    {
                    	amt13=textField_1.getText();
                    }
                    if(!textField_2.getText().equals(""))
                    {
                    	amt14=textField_2.getText();
                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
                    if(!textField_29.getText().equals(""))
                    {
                    	amt16=textField_29.getText();
                    }
                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
                    		Double.parseDouble(amt14)+/*Double.parseDouble(amt15)*/+Double.parseDouble(amt16);
                    BigDecimal bc = new BigDecimal(sum);
            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		sum = bc.doubleValue();
                    textField_19.setText(Double.toString(sum));
                    
                    double bal=Double.parseDouble(balancetxt)-sum;
                    BigDecimal ba = new BigDecimal(bal);
            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		bal = ba.doubleValue();
                    textField_7.setText(Double.toString(bal));
                }
            }
        };
		((AbstractDocument) (textField_4.getDocument())).setDocumentFilter(df);
		
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_5.setForeground(new Color(224, 255, 255));
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_5.setColumns(10);
		textField_5.setBackground(Color.BLACK);
		textField_5.setBounds(154, 321, 140, 30);
		panel_1.add(textField_5);
		
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
                double multiply1 = 0;
                if (!textField_5.getText().isEmpty()) {
                	String val= button_4.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_5.getText())*Double.parseDouble(split[1]);
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    textField_25.setText(String.valueOf(multiply1));
                    String amt1="0";
                    String amt2="0";
                    String amt3="0";
                    String amt4="0";
                    String amt5="0";
                    String amt6="0";
                    String amt7="0";
                    String amt8="0";
                    String amt9="0";
                    String amt10="0";
                    String amt11="0";
                    String amt12="0";
                    String amt13="0";
                    String amt14="0";
                    String amt16="0";
                    
                    if(!textField_32.getText().equals(""))
                    {
                    	amt1=textField_32.getText();
                    }
                    if(!textField_27.getText().equals(""))
                    {
                    	amt2=textField_27.getText();
                    }
                    if(!textField_26.getText().equals(""))
                    {
                    	amt3=textField_26.getText();
                    }
                    if(!textField_25.getText().equals(""))
                    {
                    	amt4=textField_25.getText();
                    }
                    if(!textField_17.getText().equals(""))
                    {
                    	amt5=textField_17.getText();
                    }
                    if(!textField_18.getText().equals(""))
                    {
                    	amt6=textField_18.getText();
                    }
                    if(!textField_20.getText().equals(""))
                    {
                    	amt7=textField_20.getText();
                    }
                    if(!textField_21.getText().equals(""))
                    {
                    	amt8=textField_21.getText();
                    }
                    if(!textField_22.getText().equals(""))
                    {
                    	amt9=textField_22.getText();
                    }
                    if(!textField_23.getText().equals(""))
                    {
                    	amt10=textField_23.getText();
                    }
                    if(!textField_24.getText().equals(""))
                    {
                    	amt11=textField_24.getText();
                    }
                    if(!textField.getText().equals(""))
                    {
                    	amt12=textField.getText();
                    }
                    if(!textField_1.getText().equals(""))
                    {
                    	amt13=textField_1.getText();
                    }
                    if(!textField_2.getText().equals(""))
                    {
                    	amt14=textField_2.getText();
                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
                    if(!textField_29.getText().equals(""))
                    {
                    	amt16=textField_29.getText();
                    }
                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
                    		Double.parseDouble(amt14)+/*Double.parseDouble(amt15)*/+Double.parseDouble(amt16);
                    BigDecimal bc = new BigDecimal(sum);
            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		sum = bc.doubleValue();
                    textField_19.setText(Double.toString(sum));
                    
                    double bal=Double.parseDouble(balancetxt)-sum;
                    BigDecimal ba = new BigDecimal(bal);
            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		bal = ba.doubleValue();
                    textField_7.setText(Double.toString(bal));
                }
            }
        };
		((AbstractDocument) (textField_5.getDocument())).setDocumentFilter(db);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_6.setForeground(new Color(224, 255, 255));
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_6.setColumns(10);
		textField_6.setBackground(Color.BLACK);
		textField_6.setBounds(154, 282, 140, 30);
		panel_1.add(textField_6);
		
		
		DocumentFilter dc = new DocumentFilter() {
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
                double multiply1 = 0;
                if (!textField_6.getText().isEmpty()) {
                	String val= button_3.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_6.getText())*Double.parseDouble(split[1]);
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    textField_26.setText(String.valueOf(multiply1));
                    String amt1="0";
                    String amt2="0";
                    String amt3="0";
                    String amt4="0";
                    String amt5="0";
                    String amt6="0";
                    String amt7="0";
                    String amt8="0";
                    String amt9="0";
                    String amt10="0";
                    String amt11="0";
                    String amt12="0";
                    String amt13="0";
                    String amt14="0";
                    String amt16="0";
                    
                    if(!textField_32.getText().equals(""))
                    {
                    	amt1=textField_32.getText();
                    }
                    if(!textField_27.getText().equals(""))
                    {
                    	amt2=textField_27.getText();
                    }
                    if(!textField_26.getText().equals(""))
                    {
                    	amt3=textField_26.getText();
                    }
                    if(!textField_25.getText().equals(""))
                    {
                    	amt4=textField_25.getText();
                    }
                    if(!textField_17.getText().equals(""))
                    {
                    	amt5=textField_17.getText();
                    }
                    if(!textField_18.getText().equals(""))
                    {
                    	amt6=textField_18.getText();
                    }
                    if(!textField_20.getText().equals(""))
                    {
                    	amt7=textField_20.getText();
                    }
                    if(!textField_21.getText().equals(""))
                    {
                    	amt8=textField_21.getText();
                    }
                    if(!textField_22.getText().equals(""))
                    {
                    	amt9=textField_22.getText();
                    }
                    if(!textField_23.getText().equals(""))
                    {
                    	amt10=textField_23.getText();
                    }
                    if(!textField_24.getText().equals(""))
                    {
                    	amt11=textField_24.getText();
                    }
                    if(!textField.getText().equals(""))
                    {
                    	amt12=textField.getText();
                    }
                    if(!textField_1.getText().equals(""))
                    {
                    	amt13=textField_1.getText();
                    }
                    if(!textField_2.getText().equals(""))
                    {
                    	amt14=textField_2.getText();
                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
                    if(!textField_29.getText().equals(""))
                    {
                    	amt16=textField_29.getText();
                    }
                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
                    		Double.parseDouble(amt14)+/*Double.parseDouble(amt15)*/+Double.parseDouble(amt16);
                    BigDecimal bc = new BigDecimal(sum);
            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		sum = bc.doubleValue();
                    textField_19.setText(Double.toString(sum));
                    
                    double bal=Double.parseDouble(balancetxt)-sum;
                    BigDecimal ba = new BigDecimal(bal);
            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		bal = ba.doubleValue();
                    textField_7.setText(Double.toString(bal));
                }
            }
        };
		((AbstractDocument) (textField_6.getDocument())).setDocumentFilter(dc);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_8.setForeground(new Color(224, 255, 255));
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_8.setColumns(10);
		textField_8.setBackground(Color.BLACK);
		textField_8.setBounds(154, 243, 140, 30);
		panel_1.add(textField_8);
		
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
                double multiply1 = 0;
                if (!textField_8.getText().isEmpty()) {
                	String val= button_2.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_8.getText())*Double.parseDouble(split[1]);//we must add this
                   
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    textField_27.setText(String.valueOf(multiply1));
                    String amt1="0";
                    String amt2="0";
                    String amt3="0";
                    String amt4="0";
                    String amt5="0";
                    String amt6="0";
                    String amt7="0";
                    String amt8="0";
                    String amt9="0";
                    String amt10="0";
                    String amt11="0";
                    String amt12="0";
                    String amt13="0";
                    String amt14="0";
                    String amt16="0";
                    
                    if(!textField_32.getText().equals(""))
                    {
                    	amt1=textField_32.getText();
                    }
                    if(!textField_27.getText().equals(""))
                    {
                    	amt2=textField_27.getText();
                    }
                    if(!textField_26.getText().equals(""))
                    {
                    	amt3=textField_26.getText();
                    }
                    if(!textField_25.getText().equals(""))
                    {
                    	amt4=textField_25.getText();
                    }
                    if(!textField_17.getText().equals(""))
                    {
                    	amt5=textField_17.getText();
                    }
                    if(!textField_18.getText().equals(""))
                    {
                    	amt6=textField_18.getText();
                    }
                    if(!textField_20.getText().equals(""))
                    {
                    	amt7=textField_20.getText();
                    }
                    if(!textField_21.getText().equals(""))
                    {
                    	amt8=textField_21.getText();
                    }
                    if(!textField_22.getText().equals(""))
                    {
                    	amt9=textField_22.getText();
                    }
                    if(!textField_23.getText().equals(""))
                    {
                    	amt10=textField_23.getText();
                    }
                    if(!textField_24.getText().equals(""))
                    {
                    	amt11=textField_24.getText();
                    }
                    if(!textField.getText().equals(""))
                    {
                    	amt12=textField.getText();
                    }
                    if(!textField_1.getText().equals(""))
                    {
                    	amt13=textField_1.getText();
                    }
                    if(!textField_2.getText().equals(""))
                    {
                    	amt14=textField_2.getText();
                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
                    if(!textField_29.getText().equals(""))
                    {
                    	amt16=textField_29.getText();
                    }
                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
                    		Double.parseDouble(amt14)+/*Double.parseDouble(amt15)*/+Double.parseDouble(amt16);
                    BigDecimal bc = new BigDecimal(sum);
            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		sum = bc.doubleValue();
                    textField_19.setText(Double.toString(sum));
                    
                    double bal=Double.parseDouble(balancetxt)-sum;
                    BigDecimal ba = new BigDecimal(bal);
            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		bal = ba.doubleValue();
                    textField_7.setText(Double.toString(bal));
                }
            }
        };
		((AbstractDocument) (textField_8.getDocument())).setDocumentFilter(da);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_9.setForeground(new Color(224, 255, 255));
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_9.setColumns(10);
		textField_9.setBackground(Color.BLACK);
		textField_9.setBounds(154, 399, 140, 30);
		panel_1.add(textField_9);
		
		DocumentFilter dd = new DocumentFilter() {
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
                double multiply1 = 0;
                if (!textField_9.getText().isEmpty()) {
                	String val= button_6.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_9.getText())*Double.parseDouble(split[1]);
                   
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    textField_18.setText(String.valueOf(multiply1));
                    String amt1="0";
                    String amt2="0";
                    String amt3="0";
                    String amt4="0";
                    String amt5="0";
                    String amt6="0";
                    String amt7="0";
                    String amt8="0";
                    String amt9="0";
                    String amt10="0";
                    String amt11="0";
                    String amt12="0";
                    String amt13="0";
                    String amt14="0";
                    String amt16="0";
                    
                    if(!textField_32.getText().equals(""))
                    {
                    	amt1=textField_32.getText();
                    }
                    if(!textField_27.getText().equals(""))
                    {
                    	amt2=textField_27.getText();
                    }
                    if(!textField_26.getText().equals(""))
                    {
                    	amt3=textField_26.getText();
                    }
                    if(!textField_25.getText().equals(""))
                    {
                    	amt4=textField_25.getText();
                    }
                    if(!textField_17.getText().equals(""))
                    {
                    	amt5=textField_17.getText();
                    }
                    if(!textField_18.getText().equals(""))
                    {
                    	amt6=textField_18.getText();
                    }
                    if(!textField_20.getText().equals(""))
                    {
                    	amt7=textField_20.getText();
                    }
                    if(!textField_21.getText().equals(""))
                    {
                    	amt8=textField_21.getText();
                    }
                    if(!textField_22.getText().equals(""))
                    {
                    	amt9=textField_22.getText();
                    }
                    if(!textField_23.getText().equals(""))
                    {
                    	amt10=textField_23.getText();
                    }
                    if(!textField_24.getText().equals(""))
                    {
                    	amt11=textField_24.getText();
                    }
                    if(!textField.getText().equals(""))
                    {
                    	amt12=textField.getText();
                    }
                    if(!textField_1.getText().equals(""))
                    {
                    	amt13=textField_1.getText();
                    }
                    if(!textField_2.getText().equals(""))
                    {
                    	amt14=textField_2.getText();
                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
                    if(!textField_29.getText().equals(""))
                    {
                    	amt16=textField_29.getText();
                    }
                    
                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
                    		Double.parseDouble(amt14)+/*Double.parseDouble(amt15)*/+Double.parseDouble(amt16);
                    BigDecimal bc = new BigDecimal(sum);
            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		sum = bc.doubleValue();
                    textField_19.setText(Double.toString(sum));
                    
                    double bal=Double.parseDouble(balancetxt)-sum;
                    BigDecimal ba = new BigDecimal(bal);
            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		bal = ba.doubleValue();
                    textField_7.setText(Double.toString(bal));
                }
            }
        };
		((AbstractDocument) (textField_9.getDocument())).setDocumentFilter(dd);
		
		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_10.setForeground(new Color(224, 255, 255));
		textField_10.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_10.setColumns(10);
		textField_10.setBackground(Color.BLACK);
		textField_10.setBounds(154, 477, 140, 30);
		panel_1.add(textField_10);
		
		DocumentFilter de = new DocumentFilter() {
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
                double multiply1 = 0;
                if (!textField_10.getText().isEmpty()) {
                	String val= button_8.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_10.getText())*Double.parseDouble(split[1]);
                   
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    textField_21.setText(String.valueOf(multiply1));
                    String amt1="0";
                    String amt2="0";
                    String amt3="0";
                    String amt4="0";
                    String amt5="0";
                    String amt6="0";
                    String amt7="0";
                    String amt8="0";
                    String amt9="0";
                    String amt10="0";
                    String amt11="0";
                    String amt12="0";
                    String amt13="0";
                    String amt14="0";
                    String amt16="0";
                    
                    if(!textField_32.getText().equals(""))
                    {
                    	amt1=textField_32.getText();
                    }
                    if(!textField_27.getText().equals(""))
                    {
                    	amt2=textField_27.getText();
                    }
                    if(!textField_26.getText().equals(""))
                    {
                    	amt3=textField_26.getText();
                    }
                    if(!textField_25.getText().equals(""))
                    {
                    	amt4=textField_25.getText();
                    }
                    if(!textField_17.getText().equals(""))
                    {
                    	amt5=textField_17.getText();
                    }
                    if(!textField_18.getText().equals(""))
                    {
                    	amt6=textField_18.getText();
                    }
                    if(!textField_20.getText().equals(""))
                    {
                    	amt7=textField_20.getText();
                    }
                    if(!textField_21.getText().equals(""))
                    {
                    	amt8=textField_21.getText();
                    }
                    if(!textField_22.getText().equals(""))
                    {
                    	amt9=textField_22.getText();
                    }
                    if(!textField_23.getText().equals(""))
                    {
                    	amt10=textField_23.getText();
                    }
                    if(!textField_24.getText().equals(""))
                    {
                    	amt11=textField_24.getText();
                    }
                    if(!textField.getText().equals(""))
                    {
                    	amt12=textField.getText();
                    }
                    if(!textField_1.getText().equals(""))
                    {
                    	amt13=textField_1.getText();
                    }
                    if(!textField_2.getText().equals(""))
                    {
                    	amt14=textField_2.getText();
                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
                    if(!textField_29.getText().equals(""))
                    {
                    	amt16=textField_29.getText();
                    }
                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
                    		Double.parseDouble(amt14)+/*Double.parseDouble(amt15)*/+Double.parseDouble(amt16);
                    BigDecimal bc = new BigDecimal(sum);
            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		sum = bc.doubleValue();
                    textField_19.setText(Double.toString(sum));
                    
                    double bal=Double.parseDouble(balancetxt)-sum;
                    BigDecimal ba = new BigDecimal(bal);
            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		bal = ba.doubleValue();
                    textField_7.setText(Double.toString(bal));
                }
            }
        };
		((AbstractDocument) (textField_10.getDocument())).setDocumentFilter(de);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_11.setForeground(new Color(224, 255, 255));
		textField_11.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_11.setColumns(10);
		textField_11.setBackground(Color.BLACK);
		textField_11.setBounds(154, 516, 140, 30);
		panel_1.add(textField_11);
		
		DocumentFilter dg = new DocumentFilter() {
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
                double multiply1 = 0;
                if (!textField_11.getText().isEmpty()) {
                	String val= button_9.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_11.getText())*Double.parseDouble(split[1]);
                   
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    textField_22.setText(String.valueOf(multiply1));
                    String amt1="0";
                    String amt2="0";
                    String amt3="0";
                    String amt4="0";
                    String amt5="0";
                    String amt6="0";
                    String amt7="0";
                    String amt8="0";
                    String amt9="0";
                    String amt10="0";
                    String amt11="0";
                    String amt12="0";
                    String amt13="0";
                    String amt14="0";
                    String amt16="0";
                    
                    if(!textField_32.getText().equals(""))
                    {
                    	amt1=textField_32.getText();
                    }
                    if(!textField_27.getText().equals(""))
                    {
                    	amt2=textField_27.getText();
                    }
                    if(!textField_26.getText().equals(""))
                    {
                    	amt3=textField_26.getText();
                    }
                    if(!textField_25.getText().equals(""))
                    {
                    	amt4=textField_25.getText();
                    }
                    if(!textField_17.getText().equals(""))
                    {
                    	amt5=textField_17.getText();
                    }
                    if(!textField_18.getText().equals(""))
                    {
                    	amt6=textField_18.getText();
                    }
                    if(!textField_20.getText().equals(""))
                    {
                    	amt7=textField_20.getText();
                    }
                    if(!textField_21.getText().equals(""))
                    {
                    	amt8=textField_21.getText();
                    }
                    if(!textField_22.getText().equals(""))
                    {
                    	amt9=textField_22.getText();
                    }
                    if(!textField_23.getText().equals(""))
                    {
                    	amt10=textField_23.getText();
                    }
                    if(!textField_24.getText().equals(""))
                    {
                    	amt11=textField_24.getText();
                    }
                    if(!textField.getText().equals(""))
                    {
                    	amt12=textField.getText();
                    }
                    if(!textField_1.getText().equals(""))
                    {
                    	amt13=textField_1.getText();
                    }
                    if(!textField_2.getText().equals(""))
                    {
                    	amt14=textField_2.getText();
                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
                    if(!textField_29.getText().equals(""))
                    {
                    	amt16=textField_29.getText();
                    }
                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
                    		Double.parseDouble(amt14)+/*Double.parseDouble(amt15)*/+Double.parseDouble(amt16);
                    BigDecimal bc = new BigDecimal(sum);
            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		sum = bc.doubleValue();
                    textField_19.setText(Double.toString(sum));
                    
                    double bal=Double.parseDouble(balancetxt)-sum;
                    BigDecimal ba = new BigDecimal(bal);
            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		bal = ba.doubleValue();
                    textField_7.setText(Double.toString(bal));
                }
            }
        };
		((AbstractDocument) (textField_11.getDocument())).setDocumentFilter(dg);
		
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_13.setForeground(new Color(224, 255, 255));
		textField_13.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_13.setColumns(10);
		textField_13.setBackground(Color.BLACK);
		textField_13.setBounds(154, 555, 140, 30);
		panel_1.add(textField_13);
		
		DocumentFilter dh = new DocumentFilter() {
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
                double multiply1 = 0;
                if (!textField_13.getText().isEmpty()) {
                	String val= button_10.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_13.getText())*Double.parseDouble(split[1]);
                   
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    textField_24.setText(String.valueOf(multiply1));
                    String amt1="0";
                    String amt2="0";
                    String amt3="0";
                    String amt4="0";
                    String amt5="0";
                    String amt6="0";
                    String amt7="0";
                    String amt8="0";
                    String amt9="0";
                    String amt10="0";
                    String amt11="0";
                    String amt12="0";
                    String amt13="0";
                    String amt14="0";
                    String amt16="0";
                    
                    if(!textField_32.getText().equals(""))
                    {
                    	amt1=textField_32.getText();
                    }
                    if(!textField_27.getText().equals(""))
                    {
                    	amt2=textField_27.getText();
                    }
                    if(!textField_26.getText().equals(""))
                    {
                    	amt3=textField_26.getText();
                    }
                    if(!textField_25.getText().equals(""))
                    {
                    	amt4=textField_25.getText();
                    }
                    if(!textField_17.getText().equals(""))
                    {
                    	amt5=textField_17.getText();
                    }
                    if(!textField_18.getText().equals(""))
                    {
                    	amt6=textField_18.getText();
                    }
                    if(!textField_20.getText().equals(""))
                    {
                    	amt7=textField_20.getText();
                    }
                    if(!textField_21.getText().equals(""))
                    {
                    	amt8=textField_21.getText();
                    }
                    if(!textField_22.getText().equals(""))
                    {
                    	amt9=textField_22.getText();
                    }
                    if(!textField_23.getText().equals(""))
                    {
                    	amt10=textField_23.getText();
                    }
                    if(!textField_24.getText().equals(""))
                    {
                    	amt11=textField_24.getText();
                    }
                    if(!textField.getText().equals(""))
                    {
                    	amt12=textField.getText();
                    }
                    if(!textField_1.getText().equals(""))
                    {
                    	amt13=textField_1.getText();
                    }
                    if(!textField_2.getText().equals(""))
                    {
                    	amt14=textField_2.getText();
                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
                    if(!textField_29.getText().equals(""))
                    {
                    	amt16=textField_29.getText();
                    }
                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
                    		Double.parseDouble(amt14)+/*Double.parseDouble(amt15)*/+Double.parseDouble(amt16);
                    BigDecimal bc = new BigDecimal(sum);
            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		sum = bc.doubleValue();
                    textField_19.setText(Double.toString(sum));
                    
                    double bal=Double.parseDouble(balancetxt)-sum;
                    BigDecimal ba = new BigDecimal(bal);
            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		bal = ba.doubleValue();
                    textField_7.setText(Double.toString(bal));
                }
            }
        };
		((AbstractDocument) (textField_13.getDocument())).setDocumentFilter(dh);
		
		textField_14 = new JTextField();
		textField_14.setEditable(false);
		textField_14.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_14.setForeground(new Color(224, 255, 255));
		textField_14.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_14.setColumns(10);
		textField_14.setBackground(Color.BLACK);
		textField_14.setBounds(154, 594, 140, 30);
		panel_1.add(textField_14);
		
		DocumentFilter di = new DocumentFilter() {
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
                double multiply1 = 0;
                if (!textField_14.getText().isEmpty()) {
                	String val= button_11.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_14.getText())*Double.parseDouble(split[1]);
                   
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    textField_23.setText(String.valueOf(multiply1));
                    String amt1="0";
                    String amt2="0";
                    String amt3="0";
                    String amt4="0";
                    String amt5="0";
                    String amt6="0";
                    String amt7="0";
                    String amt8="0";
                    String amt9="0";
                    String amt10="0";
                    String amt11="0";
                    String amt12="0";
                    String amt13="0";
                    String amt14="0";
                    String amt16="0";
                    
                    if(!textField_32.getText().equals(""))
                    {
                    	amt1=textField_32.getText();
                    }
                    if(!textField_27.getText().equals(""))
                    {
                    	amt2=textField_27.getText();
                    }
                    if(!textField_26.getText().equals(""))
                    {
                    	amt3=textField_26.getText();
                    }
                    if(!textField_25.getText().equals(""))
                    {
                    	amt4=textField_25.getText();
                    }
                    if(!textField_17.getText().equals(""))
                    {
                    	amt5=textField_17.getText();
                    }
                    if(!textField_18.getText().equals(""))
                    {
                    	amt6=textField_18.getText();
                    }
                    if(!textField_20.getText().equals(""))
                    {
                    	amt7=textField_20.getText();
                    }
                    if(!textField_21.getText().equals(""))
                    {
                    	amt8=textField_21.getText();
                    }
                    if(!textField_22.getText().equals(""))
                    {
                    	amt9=textField_22.getText();
                    }
                    if(!textField_23.getText().equals(""))
                    {
                    	amt10=textField_23.getText();
                    }
                    if(!textField_24.getText().equals(""))
                    {
                    	amt11=textField_24.getText();
                    }
                    if(!textField.getText().equals(""))
                    {
                    	amt12=textField.getText();
                    }
                    if(!textField_1.getText().equals(""))
                    {
                    	amt13=textField_1.getText();
                    }
                    if(!textField_2.getText().equals(""))
                    {
                    	amt14=textField_2.getText();
                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
                    if(!textField_29.getText().equals(""))
                    {
                    	amt16=textField_29.getText();
                    }
                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
                    		Double.parseDouble(amt14)+/*Double.parseDouble(amt15)*/+Double.parseDouble(amt16);
                    BigDecimal bc = new BigDecimal(sum);
            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		sum = bc.doubleValue();
                    textField_19.setText(Double.toString(sum));
                    
                    double bal=Double.parseDouble(balancetxt)-sum;
                    BigDecimal ba = new BigDecimal(bal);
            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		bal = ba.doubleValue();
                    textField_7.setText(Double.toString(bal));
                }
            }
        };
		((AbstractDocument) (textField_14.getDocument())).setDocumentFilter(di);
		
		textField_15 = new JTextField();
		textField_15.setEditable(false);
		textField_15.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_15.setForeground(new Color(224, 255, 255));
		textField_15.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_15.setColumns(10);
		textField_15.setBackground(Color.BLACK);
		textField_15.setBounds(154, 438, 140, 30);
		panel_1.add(textField_15);
		
		DocumentFilter dj = new DocumentFilter() {
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
                double multiply1 = 0;
                if (!textField_15.getText().isEmpty()) {
                	String val= button_7.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_15.getText())*Double.parseDouble(split[1]);
                   
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    textField_20.setText(String.valueOf(multiply1));
                    String amt1="0";
                    String amt2="0";
                    String amt3="0";
                    String amt4="0";
                    String amt5="0";
                    String amt6="0";
                    String amt7="0";
                    String amt8="0";
                    String amt9="0";
                    String amt10="0";
                    String amt11="0";
                    String amt12="0";
                    String amt13="0";
                    String amt14="0";
                    String amt16="0";
                    
                    if(!textField_32.getText().equals(""))
                    {
                    	amt1=textField_32.getText();
                    }
                    if(!textField_27.getText().equals(""))
                    {
                    	amt2=textField_27.getText();
                    }
                    if(!textField_26.getText().equals(""))
                    {
                    	amt3=textField_26.getText();
                    }
                    if(!textField_25.getText().equals(""))
                    {
                    	amt4=textField_25.getText();
                    }
                    if(!textField_17.getText().equals(""))
                    {
                    	amt5=textField_17.getText();
                    }
                    if(!textField_18.getText().equals(""))
                    {
                    	amt6=textField_18.getText();
                    }
                    if(!textField_20.getText().equals(""))
                    {
                    	amt7=textField_20.getText();
                    }
                    if(!textField_21.getText().equals(""))
                    {
                    	amt8=textField_21.getText();
                    }
                    if(!textField_22.getText().equals(""))
                    {
                    	amt9=textField_22.getText();
                    }
                    if(!textField_23.getText().equals(""))
                    {
                    	amt10=textField_23.getText();
                    }
                    if(!textField_24.getText().equals(""))
                    {
                    	amt11=textField_24.getText();
                    }
                    if(!textField.getText().equals(""))
                    {
                    	amt12=textField.getText();
                    }
                    if(!textField_1.getText().equals(""))
                    {
                    	amt13=textField_1.getText();
                    }
                    if(!textField_2.getText().equals(""))
                    {
                    	amt14=textField_2.getText();
                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
                    if(!textField_29.getText().equals(""))
                    {
                    	amt16=textField_29.getText();
                    }
                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
                    		Double.parseDouble(amt14)+/*Double.parseDouble(amt15)*/+Double.parseDouble(amt16);
                    BigDecimal bc = new BigDecimal(sum);
            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		sum = bc.doubleValue();
                    textField_19.setText(Double.toString(sum));
                    
                    double bal=Double.parseDouble(balancetxt)-sum;
                    BigDecimal ba = new BigDecimal(bal);
            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		bal = ba.doubleValue();
                    textField_7.setText(Double.toString(bal));
                }
            }
        };
		((AbstractDocument) (textField_15.getDocument())).setDocumentFilter(dj);
		
		textField_16 = new JTextField();
		textField_16.setEditable(false);
		textField_16.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_16.setForeground(new Color(224, 255, 255));
		textField_16.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_16.setColumns(10);
		textField_16.setBackground(Color.BLACK);
		textField_16.setBounds(154, 360, 140, 30);
		panel_1.add(textField_16);
		
		DocumentFilter dk = new DocumentFilter() {
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
                double multiply1 = 0;
                if (!textField_16.getText().isEmpty()) {
                	String val= button_5.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_16.getText())*Double.parseDouble(split[1]);
                   
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    textField_17.setText(String.valueOf(multiply1));
                    String amt1="0";
                    String amt2="0";
                    String amt3="0";
                    String amt4="0";
                    String amt5="0";
                    String amt6="0";
                    String amt7="0";
                    String amt8="0";
                    String amt9="0";
                    String amt10="0";
                    String amt11="0";
                    String amt12="0";
                    String amt13="0";
                    String amt14="0";
                    String amt16="0";
                    
                    if(!textField_32.getText().equals(""))
                    {
                    	amt1=textField_32.getText();
                    }
                    if(!textField_27.getText().equals(""))
                    {
                    	amt2=textField_27.getText();
                    }
                    if(!textField_26.getText().equals(""))
                    {
                    	amt3=textField_26.getText();
                    }
                    if(!textField_25.getText().equals(""))
                    {
                    	amt4=textField_25.getText();
                    }
                    if(!textField_17.getText().equals(""))
                    {
                    	amt5=textField_17.getText();
                    }
                    if(!textField_18.getText().equals(""))
                    {
                    	amt6=textField_18.getText();
                    }
                    if(!textField_20.getText().equals(""))
                    {
                    	amt7=textField_20.getText();
                    }
                    if(!textField_21.getText().equals(""))
                    {
                    	amt8=textField_21.getText();
                    }
                    if(!textField_22.getText().equals(""))
                    {
                    	amt9=textField_22.getText();
                    }
                    if(!textField_23.getText().equals(""))
                    {
                    	amt10=textField_23.getText();
                    }
                    if(!textField_24.getText().equals(""))
                    {
                    	amt11=textField_24.getText();
                    }
                    if(!textField.getText().equals(""))
                    {
                    	amt12=textField.getText();
                    }
                    if(!textField_1.getText().equals(""))
                    {
                    	amt13=textField_1.getText();
                    }
                    if(!textField_2.getText().equals(""))
                    {
                    	amt14=textField_2.getText();
                    }
//                    if(!textField_3.getText().equals(""))
//                    {
//                    	amt15=textField_3.getText();
//                    }
                    if(!textField_29.getText().equals(""))
                    {
                    	amt16=textField_29.getText();
                    }
                    double sum=Double.parseDouble(amt1)+Double.parseDouble(amt2)+Double.parseDouble(amt3)+
                    		Double.parseDouble(amt4)+Double.parseDouble(amt5)+Double.parseDouble(amt6)+
                    		Double.parseDouble(amt7)+Double.parseDouble(amt8)+Double.parseDouble(amt9)+
                    		Double.parseDouble(amt10)+Double.parseDouble(amt11)+Double.parseDouble(amt12)+Double.parseDouble(amt13)+
                    		Double.parseDouble(amt14)+/*Double.parseDouble(amt15)*/+Double.parseDouble(amt16);
                    BigDecimal bc = new BigDecimal(sum);
            		bc = bc.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		sum = bc.doubleValue();
                    textField_19.setText(Double.toString(sum));
                    
                    double bal=Double.parseDouble(balancetxt)-sum;
                    BigDecimal ba = new BigDecimal(bal);
            		ba = ba.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		bal = ba.doubleValue();
                    textField_7.setText(Double.toString(bal));
                }
            }
        };
		((AbstractDocument) (textField_16.getDocument())).setDocumentFilter(dk);
		
		textField_17 = new JTextField();
		textField_17.setEditable(false);
		textField_17.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_17.setForeground(new Color(224, 255, 255));
		textField_17.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_17.setColumns(10);
		textField_17.setBackground(Color.BLACK);
		textField_17.setBounds(318, 360, 147, 30);
		panel_1.add(textField_17);
		
		textField_18 = new JTextField();
		textField_18.setEditable(false);
		textField_18.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_18.setForeground(new Color(224, 255, 255));
		textField_18.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_18.setColumns(10);
		textField_18.setBackground(Color.BLACK);
		textField_18.setBounds(318, 399, 147, 30);
		panel_1.add(textField_18);
		
		textField_20 = new JTextField();
		textField_20.setEditable(false);
		textField_20.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_20.setForeground(new Color(224, 255, 255));
		textField_20.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_20.setColumns(10);
		textField_20.setBackground(Color.BLACK);
		textField_20.setBounds(318, 438, 147, 30);
		panel_1.add(textField_20);
		
		textField_21 = new JTextField();
		textField_21.setEditable(false);
		textField_21.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_21.setForeground(new Color(224, 255, 255));
		textField_21.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_21.setColumns(10);
		textField_21.setBackground(Color.BLACK);
		textField_21.setBounds(318, 477, 147, 30);
		panel_1.add(textField_21);
		
		textField_22 = new JTextField();
		textField_22.setEditable(false);
		textField_22.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_22.setForeground(new Color(224, 255, 255));
		textField_22.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_22.setColumns(10);
		textField_22.setBackground(Color.BLACK);
		textField_22.setBounds(318, 516, 147, 30);
		panel_1.add(textField_22);
		
		textField_23 = new JTextField();
		textField_23.setEditable(false);
		textField_23.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_23.setForeground(new Color(224, 255, 255));
		textField_23.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_23.setColumns(10);
		textField_23.setBackground(Color.BLACK);
		textField_23.setBounds(318, 594, 147, 30);
		panel_1.add(textField_23);
		
		textField_24 = new JTextField();
		textField_24.setEditable(false);
		textField_24.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_24.setForeground(new Color(224, 255, 255));
		textField_24.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_24.setColumns(10);
		textField_24.setBackground(Color.BLACK);
		textField_24.setBounds(318, 555, 147, 30);
		panel_1.add(textField_24);
		
		textField_25 = new JTextField();
		textField_25.setEditable(false);
		textField_25.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_25.setForeground(new Color(224, 255, 255));
		textField_25.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_25.setColumns(10);
		textField_25.setBackground(Color.BLACK);
		textField_25.setBounds(318, 321, 147, 30);
		panel_1.add(textField_25);
		
		textField_26 = new JTextField();
		textField_26.setEditable(false);
		textField_26.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_26.setForeground(new Color(224, 255, 255));
		textField_26.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_26.setColumns(10);
		textField_26.setBackground(Color.BLACK);
		textField_26.setBounds(318, 282, 147, 30);
		panel_1.add(textField_26);
		
		textField_27 = new JTextField();
		textField_27.setEditable(false);
		textField_27.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_27.setForeground(new Color(224, 255, 255));
		textField_27.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_27.setColumns(10);
		textField_27.setBackground(Color.BLACK);
		textField_27.setBounds(318, 243, 147, 30);
		panel_1.add(textField_27);
		
		textField_32 = new JTextField();
		textField_32.setEditable(false);
		textField_32.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_32.setForeground(new Color(224, 255, 255));
		textField_32.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_32.setColumns(10);
		textField_32.setBackground(Color.BLACK);
		textField_32.setBounds(318, 204, 147, 30);
		panel_1.add(textField_32);
		
		JLabel label_1 = new JLabel("Foreign Currency");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(154, 165, 140, 30);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Home Currency");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(318, 165, 147, 30);
		panel_1.add(label_2);
		
		button_1 = new JButton( );
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_12.getText();
				double multiply1 = 0;
               
				if(amt.equals(""))
				{
					String val= button_1.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_7.getText())/Double.parseDouble(split[1]);
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    
					amt=String.valueOf(multiply1);
				}
				textField_4.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
				
			}
		});
		button_1.setEnabled(false);
		button_1.setForeground(Color.DARK_GRAY);
		button_1.setFont(new Font("Arial", Font.BOLD, 13));
		button_1.setBorder(new LineBorder(Color.GRAY, 2, true));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(12, 204, 142, 30);
		panel_1.add(button_1);
		
		button_2 = new JButton( );
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_12.getText();
				double multiply1 = 0;
               
				if(amt.equals(""))
				{
					String val= button_2.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_7.getText())/Double.parseDouble(split[1]);
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    
					amt=String.valueOf(multiply1);
				}
				textField_8.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
			}
		});
		button_2.setEnabled(false);
		button_2.setForeground(Color.DARK_GRAY);
		button_2.setFont(new Font("Arial", Font.BOLD, 13));
		button_2.setBorder(new LineBorder(Color.GRAY, 2, true));
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setBounds(12, 243, 142, 30);
		panel_1.add(button_2);
		
		button_3 = new JButton( );
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_12.getText();
				double multiply1 = 0;
               
				if(amt.equals(""))
				{
					String val= button_3.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_7.getText())/Double.parseDouble(split[1]);
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    
					amt=String.valueOf(multiply1);
				}
				textField_6.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
			}
		});
		button_3.setEnabled(false);
		button_3.setForeground(Color.DARK_GRAY);
		button_3.setFont(new Font("Arial", Font.BOLD, 13));
		button_3.setBorder(new LineBorder(Color.GRAY, 2, true));
		button_3.setBackground(Color.LIGHT_GRAY);
		button_3.setBounds(12, 282, 142, 30);
		panel_1.add(button_3);
		
		button_8 = new JButton( );
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_12.getText();
				double multiply1 = 0;
               
				if(amt.equals(""))
				{
					String val= button_8.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_7.getText())/Double.parseDouble(split[1]);
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    
					amt=String.valueOf(multiply1);
				}
				textField_10.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
			}
		});
		button_8.setEnabled(false);
		button_8.setForeground(Color.DARK_GRAY);
		button_8.setFont(new Font("Arial", Font.BOLD, 13));
		button_8.setBorder(new LineBorder(Color.GRAY, 2, true));
		button_8.setBackground(Color.LIGHT_GRAY);
		button_8.setBounds(12, 477, 142, 30);
		panel_1.add(button_8);
		
		button_9 = new JButton( );
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_12.getText();
				double multiply1 = 0;
               
				if(amt.equals(""))
				{
					String val= button_9.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_7.getText())/Double.parseDouble(split[1]);
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    
					amt=String.valueOf(multiply1);
				}
				textField_11.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
			}
		});
		button_9.setEnabled(false);
		button_9.setForeground(Color.DARK_GRAY);
		button_9.setFont(new Font("Arial", Font.BOLD, 13));
		button_9.setBorder(new LineBorder(Color.GRAY, 2, true));
		button_9.setBackground(Color.LIGHT_GRAY);
		button_9.setBounds(12, 516, 142, 30);
		panel_1.add(button_9);
		
		button_10 = new JButton( );
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_12.getText();
				double multiply1 = 0;
               
				if(amt.equals(""))
				{
					String val= button_10.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_7.getText())/Double.parseDouble(split[1]);
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    
					amt=String.valueOf(multiply1);
				}
				textField_13.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
			}
		});
		button_10.setEnabled(false);
		button_10.setForeground(Color.DARK_GRAY);
		button_10.setFont(new Font("Arial", Font.BOLD, 13));
		button_10.setBorder(new LineBorder(Color.GRAY, 2, true));
		button_10.setBackground(Color.LIGHT_GRAY);
		button_10.setBounds(12, 555, 142, 30);
		panel_1.add(button_10);
		
		button_11 = new JButton( );
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amt=textField_12.getText();
				double multiply1 = 0;
               
				if(amt.equals(""))
				{
					String val= button_11.getText();
                	String[] split = val.split("@");
                    multiply1 = Double.parseDouble(textField_7.getText())/Double.parseDouble(split[1]);
                    BigDecimal bd = new BigDecimal(multiply1);
            		bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
            		multiply1 = bd.doubleValue();
                    
					amt=String.valueOf(multiply1);
				}
				textField_14.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
			}
		});
		button_11.setEnabled(false);
		button_11.setForeground(Color.DARK_GRAY);
		button_11.setFont(new Font("Arial", Font.BOLD, 13));
		button_11.setBorder(new LineBorder(Color.GRAY, 2, true));
		button_11.setBackground(Color.LIGHT_GRAY);
		button_11.setBounds(12, 594, 142, 30);
		panel_1.add(button_11);
		
		textField_28 = new JTextField();
		textField_28.setText("0");
		textField_28.setEditable(false);
		textField_28.setEnabled(false);
		textField_28.setVisible(false);
		textField_28.setBounds(318, 44, 116, 30);
		panel_1.add(textField_28);
		textField_28.setColumns(10);
		
		btnStoreCredit = new JButton("<html>STORE CREDIT<br>\u0645\u062E\u0632\u0646 \u0627\u0639\u062A\u0645\u0627\u062F\u0627\u062A</html>");
		btnStoreCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Store_credit_list(company, username, version, textField_7.getText(), store_name, "",Integer.toString(decimal));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnStoreCredit.setForeground(Color.DARK_GRAY);
		btnStoreCredit.setFont(new Font("Arial", Font.BOLD, 15));
		btnStoreCredit.setBorder(new LineBorder(Color.GRAY, 2, true));
		btnStoreCredit.setBackground(Color.LIGHT_GRAY);
		btnStoreCredit.setBounds(12, 126, 142, 35);
		panel_1.add(btnStoreCredit);
		
		textField_29 = new JTextField(store_credit_amt);
		textField_29.setEditable(false);
		textField_29.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_29.setForeground(new Color(224, 255, 255));
		textField_29.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_29.setColumns(10);
		textField_29.setBackground(Color.BLACK);
		textField_29.setBounds(154, 126, 140, 35);
		panel_1.add(textField_29);
		
		String disc_perc=discperc.replace("%", "");
		textField_34 = new JTextField(disc_perc);
		textField_34.setEditable(false);
		textField_34.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_34.getText().equals(""))
			        dot = false;
			    if (dot == false){
			        if (vChar == '.') dot = true;
			        else if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    } else {
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    }
			    String amt=textField_34.getText();
				if(Double.parseDouble(disc_amt)>=Double.parseDouble(amt))
				{
					double desc=0;
					double grand_total=0;
					double total=0;
					double vatpric=0;
					float tax_rate = 0;
					
					if(Mainpage.SoftLang.equals("Ar"))
					{
						for(int i=0;i<PosscreenArabic.model.getRowCount();i++)
						{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement = null;
							try {
								statement = con.createStatement();
								ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_item_master where item_name='"+PosscreenArabic.table.getModel().getValueAt(i, 2).toString()+"'");
								if(resultSet.next())
								{
									String vat_per=resultSet.getString("output_tax");
									ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_tax where tax_code='"+vat_per+"'");
									if(resultSet1.next())
									{
										tax_rate=Float.parseFloat(resultSet1.getString("tax_rate"));
									}
								}
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							double unit_price=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 4).toString());
							double qty=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 5).toString());
							String vattype=PosscreenArabic.table.getModel().getValueAt(i, 9).toString();
							
							PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(Double.parseDouble(amt)), i, 6);
							if(PosscreenArabic.gs.length>1)
							{
							SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(Double.parseDouble(amt)), i, 6);
							}
							if(vattype.equals("EXC"))
							{
								desc=((unit_price*qty)*(Double.parseDouble(amt)))/100;	
								total+=(unit_price*qty)-desc;
								vatpric+=total*tax_rate/100;
								PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format((unit_price*qty)-desc), i, 7);
								PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total*tax_rate/100), i, 8);
								if(PosscreenArabic.gs.length>1)
								{
								SecondDisplay.table.setValueAt(PosscreenArabic.df2.format((unit_price*qty)-desc), i, 7);
								SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total*tax_rate/100), i, 8);
								}
							}
							if(vattype.equals("INC"))
							{
								desc=(((unit_price*qty)*100/105)*(Double.parseDouble(amt)))/100;	
								total+=((unit_price*100/105)*qty)-desc;
								vatpric+=total*tax_rate/105;
								PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(((unit_price*100/105)*qty)-desc), i, 7);
								PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format((((unit_price*100/105)*qty)-desc)*tax_rate/105), i, 8);
								if(PosscreenArabic.gs.length>1)
								{
								SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(((unit_price*100/105)*qty)-desc), i, 7);
								SecondDisplay.table.setValueAt(PosscreenArabic.df2.format((((unit_price*100/105)*qty)-desc)*tax_rate/105), i, 8);
								}
							}
							if(vattype.equals("NON"))
							{
								desc=((unit_price*qty)*(Double.parseDouble(amt)))/100;	
								total+=((unit_price)*qty)-desc;
								vatpric+=0;
								PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), i, 7);
								if(PosscreenArabic.gs.length>1)
								{
								SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), i, 7);
								}
							}
						}
						
		    			String itm_disc=amt;
		    			if(!itm_disc.equals(""))
						{
		    				//desc=((total)*(Double.parseDouble(itm_disc)))/100;	
							//total=total-desc;
							//vatpric=((total * 5)/100);
							grand_total=total+vatpric;

							PosscreenArabic.textField.setText(PosscreenArabic.df2.format(total));
							PosscreenArabic.textField_3.setText(PosscreenArabic.df2.format(grand_total));
							PosscreenArabic.textField_1.setText(PosscreenArabic.df2.format(vatpric));
							PosscreenArabic.textField_2.setText(PosscreenArabic.df2.format(desc));
							PosscreenArabic.textField_5.setText(PosscreenArabic.df2.format(itm_disc)+"%");
							PosscreenArabic.txtDisc.setText("");
							PosscreenArabic.txtDisc.requestFocus();
							if(PosscreenArabic.gs.length>1)
							{
							SecondDisplay.textField.setText(PosscreenArabic.df2.format(total));
							SecondDisplay.textField_3.setText(PosscreenArabic.df2.format(grand_total));
							SecondDisplay.textField_1.setText(PosscreenArabic.df2.format(vatpric));
							SecondDisplay.textField_2.setText(PosscreenArabic.df2.format(desc));
							SecondDisplay.textField_5.setText(PosscreenArabic.df2.format(itm_disc)+"%");
							}
						}
		    			
		    			double bal_amt=0;
		    			String paid_amt=textField_19.getText();
		    			
		    			if(!paid_amt.equals(""))
		         	   	{
		         	   		bal_amt=grand_total-Double.parseDouble(paid_amt);
		         	   	}
		         	   	else
		         	   	{
		         	   		bal_amt=grand_total;
		         	   	}
	         			
	         			BigDecimal bd = new BigDecimal(bal_amt);
	         			bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	         			bal_amt = bd.doubleValue();
	         			
		    			textField_34.setText(amt);	
			         	textField_7.setText(Double.toString(bal_amt));
			         	textField_12.setText("");
			         	textField_12.requestFocus();
			         	textField_33.setText(PosscreenArabic.textField_2.getText());
			         	
			         	
			         	textField_3.setText(PosscreenArabic.df2.format(total));
						textField_31.setText(PosscreenArabic.df2.format(grand_total));
						textField_35.setText(PosscreenArabic.df2.format(vatpric));
					}
					else
					{
						for(int i=0;i<posscreen.model.getRowCount();i++)
						{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement = null;
							try {
								statement = con.createStatement();
								ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_item_master where item_name='"+posscreen.table.getModel().getValueAt(i, 2).toString()+"'");
								if(resultSet.next())
								{
									String vat_per=resultSet.getString("output_tax");
									ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_tax where tax_code='"+vat_per+"'");
									if(resultSet1.next())
									{
										tax_rate=Float.parseFloat(resultSet1.getString("tax_rate"));
									}
								}
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							double unit_price=Double.parseDouble(posscreen.table.getValueAt(i, 4).toString());
							double qty=Double.parseDouble(posscreen.table.getValueAt(i, 5).toString());
							String vattype=posscreen.table.getModel().getValueAt(i, 9).toString();
							
							posscreen.table.setValueAt(posscreen.df2.format(Double.parseDouble(amt)), i, 6);
							if(posscreen.gs.length>1)
							{
							SecondDisplay.table.setValueAt(posscreen.df2.format(Double.parseDouble(amt)), i, 6);
							}
							if(vattype.equals("EXC"))
							{
								desc=((unit_price*qty)*(Double.parseDouble(amt)))/100;	
								total+=(unit_price*qty)-desc;
								vatpric+=total*tax_rate/100;
								posscreen.table.setValueAt(posscreen.df2.format((unit_price*qty)-desc), i, 7);
								posscreen.table.setValueAt(posscreen.df2.format(total*tax_rate/100), i, 8);
								if(posscreen.gs.length>1)
								{
								SecondDisplay.table.setValueAt(posscreen.df2.format((unit_price*qty)-desc), i, 7);
								SecondDisplay.table.setValueAt(posscreen.df2.format(total*tax_rate/100), i, 8);
								}
							}
							if(vattype.equals("INC"))
							{
								desc=(((unit_price*qty)*100/105)*(Double.parseDouble(amt)))/100;	
								total+=((unit_price*100/105)*qty)-desc;
								vatpric+=total*tax_rate/105;
								posscreen.table.setValueAt(posscreen.df2.format(((unit_price*100/105)*qty)-desc), i, 7);
								posscreen.table.setValueAt(posscreen.df2.format((((unit_price*100/105)*qty)-desc)*tax_rate/105), i, 8);
								if(posscreen.gs.length>1)
								{
								SecondDisplay.table.setValueAt(posscreen.df2.format(((unit_price*100/105)*qty)-desc), i, 7);
								SecondDisplay.table.setValueAt(posscreen.df2.format((((unit_price*100/105)*qty)-desc)*tax_rate/105), i, 8);
								}
							}
							if(vattype.equals("NON"))
							{
								desc=((unit_price*qty)*(Double.parseDouble(amt)))/100;	
								total+=((unit_price)*qty)-desc;
								vatpric+=0;
								posscreen.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), i, 7);
								if(posscreen.gs.length>1)
								{
								SecondDisplay.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), i, 7);
								}
							}
						}
						
		    			String itm_disc=amt;
		    			if(!itm_disc.equals(""))
						{
		    				//desc=((total)*(Double.parseDouble(itm_disc)))/100;	
							//total=total-desc;
							//vatpric=((total * 5)/100);
							grand_total=total+vatpric;

							posscreen.textField.setText(posscreen.df2.format(total));
							posscreen.textField_3.setText(posscreen.df2.format(grand_total));
							posscreen.textField_1.setText(posscreen.df2.format(vatpric));
							posscreen.textField_2.setText(posscreen.df2.format(desc));
							posscreen.textField_5.setText(posscreen.df2.format(itm_disc)+"%");
							posscreen.txtDisc.setText("");
							posscreen.txtDisc.requestFocus();
							if(posscreen.gs.length>1)
							{
							SecondDisplay.textField.setText(posscreen.df2.format(total));
							SecondDisplay.textField_3.setText(posscreen.df2.format(grand_total));
							SecondDisplay.textField_1.setText(posscreen.df2.format(vatpric));
							SecondDisplay.textField_2.setText(posscreen.df2.format(desc));
							SecondDisplay.textField_5.setText(posscreen.df2.format(itm_disc)+"%");
							}
						}
		    			
		    			double bal_amt=0;
		    			String paid_amt=textField_19.getText();
		    			
		    			if(!paid_amt.equals(""))
		         	   	{
		         	   		bal_amt=grand_total-Double.parseDouble(paid_amt);
		         	   	}
		         	   	else
		         	   	{
		         	   		bal_amt=grand_total;
		         	   	}
	         			
	         			BigDecimal bd = new BigDecimal(bal_amt);
	         			bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	         			bal_amt = bd.doubleValue();
	         			
		    			textField_34.setText(amt);	
			         	textField_7.setText(Double.toString(bal_amt));
			         	textField_12.setText("");
			         	textField_12.requestFocus();
			         	textField_33.setText(posscreen.textField_2.getText());
			         	
			         	
			         	textField_3.setText(posscreen.df2.format(total));
						textField_31.setText(posscreen.df2.format(grand_total));
						textField_35.setText(posscreen.df2.format(vatpric));
					}
					
					
					//textField_19.setText(paid_amt);
		         	frmMultiplePayCode.setAlwaysOnTop(true);
		         	
				}
				else
				{
					JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>Maximum Sale Discount is "+disc_amt +"%<br>الحد الأقصى لخصم البيع هو "+disc_amt +"%</html>");
					
				}
			}
		});
		textField_34.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_34.setForeground(Color.DARK_GRAY);
		textField_34.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_34.setColumns(10);
		textField_34.setBackground(Color.YELLOW);
		textField_34.setBounds(154, 655, 140, 35);
		frmMultiplePayCode.getContentPane().add(textField_34);
		
		
		textField_33 = new JTextField(discamt);
		textField_33.setEditable(false);
		textField_33.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_33.getText().equals(""))
			        dot = false;
			    if (dot == false){
			        if (vChar == '.') dot = true;
			        else if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    } else {
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    }
			    String amt=textField_34.getText();
				if(Double.parseDouble(disc_amt)>=Double.parseDouble(amt))
				{
					double desc=0;
					double grand_total=0;
					double total=0;
					double vatpric=0;
					float tax_rate = 0;
					if(Mainpage.SoftLang.equals("Ar"))
					{
						for(int i=0;i<PosscreenArabic.model.getRowCount();i++)
						{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement = null;
							try {
								statement = con.createStatement();
								ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_item_master where item_name='"+PosscreenArabic.table.getModel().getValueAt(i, 2).toString()+"'");
								if(resultSet.next())
								{
									String vat_per=resultSet.getString("output_tax");
									ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_tax where tax_code='"+vat_per+"'");
									if(resultSet1.next())
									{
										tax_rate=Float.parseFloat(resultSet1.getString("tax_rate"));
									}
								}
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							double unit_price=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 4).toString());
							double qty=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 5).toString());
							String vattype=PosscreenArabic.table.getModel().getValueAt(i, 9).toString();
							
							PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(Double.parseDouble(amt)), i, 6);
							if(PosscreenArabic.gs.length>1)
							{
							SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(Double.parseDouble(amt)), i, 6);
							}
							if(vattype.equals("EXC"))
							{
								desc=((unit_price*qty)*(Double.parseDouble(amt)))/100;	
								total+=(unit_price*qty)-desc;
								vatpric+=total*tax_rate/100;
								PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format((unit_price*qty)-desc), i, 7);
								PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total*tax_rate/100), i, 8);
								if(PosscreenArabic.gs.length>1)
								{
								SecondDisplay.table.setValueAt(PosscreenArabic.df2.format((unit_price*qty)-desc), i, 7);
								SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total*tax_rate/100), i, 8);
								}
							}
							if(vattype.equals("INC"))
							{
								desc=(((unit_price*qty)*100/105)*(Double.parseDouble(amt)))/100;	
								total+=((unit_price*100/105)*qty)-desc;
								vatpric+=total*tax_rate/105;
								PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(((unit_price*100/105)*qty)-desc), i, 7);
								PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format((((unit_price*100/105)*qty)-desc)*tax_rate/105), i, 8);
								if(PosscreenArabic.gs.length>1)
								{
								SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(((unit_price*100/105)*qty)-desc), i, 7);
								SecondDisplay.table.setValueAt(PosscreenArabic.df2.format((((unit_price*100/105)*qty)-desc)*tax_rate/105), i, 8);
								}
							}
							if(vattype.equals("NON"))
							{
								desc=((unit_price*qty)*(Double.parseDouble(amt)))/100;	
								total+=((unit_price)*qty)-desc;
								vatpric+=0;
								PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), i, 7);
								if(PosscreenArabic.gs.length>1)
								{
								SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), i, 7);
								}
							}
						}
						
		    			String itm_disc=amt;
		    			if(!itm_disc.equals(""))
						{
		    				//desc=((total)*(Double.parseDouble(itm_disc)))/100;	
							//total=total-desc;
							//vatpric=((total * 5)/100);
							grand_total=total+vatpric;

							PosscreenArabic.textField.setText(PosscreenArabic.df2.format(total));
							PosscreenArabic.textField_3.setText(PosscreenArabic.df2.format(grand_total));
							PosscreenArabic.textField_1.setText(PosscreenArabic.df2.format(vatpric));
							PosscreenArabic.textField_2.setText(PosscreenArabic.df2.format(desc));
							PosscreenArabic.textField_5.setText(itm_disc+"%");
							PosscreenArabic.txtDisc.setText("");
							PosscreenArabic.txtDisc.requestFocus();
							
							if(PosscreenArabic.gs.length>1)
							{
							SecondDisplay.textField.setText(PosscreenArabic.df2.format(total));
							SecondDisplay.textField_3.setText(PosscreenArabic.df2.format(grand_total));
							SecondDisplay.textField_1.setText(PosscreenArabic.df2.format(vatpric));
							SecondDisplay.textField_2.setText(PosscreenArabic.df2.format(desc));
							SecondDisplay.textField_5.setText(itm_disc+"%");
							}
						}
		    			
		    			double bal_amt=0;
		    			String paid_amt=textField_19.getText();
		    			
		    			if(!paid_amt.equals(""))
		         	   	{
		         	   		bal_amt=grand_total-Double.parseDouble(paid_amt);
		         	   	}
		         	   	else
		         	   	{
		         	   		bal_amt=grand_total;
		         	   	}
	         			
	         			BigDecimal bd = new BigDecimal(bal_amt);
	         			bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	         			bal_amt = bd.doubleValue();
	         			
		    			textField_34.setText(amt);	
			         	textField_7.setText(Double.toString(bal_amt));
			         	textField_12.setText("");
			         	textField_12.requestFocus();
			         	textField_33.setText(PosscreenArabic.textField_2.getText());
			         	
			         	
			         	textField_3.setText(PosscreenArabic.df2.format(total));
						textField_31.setText(PosscreenArabic.df2.format(grand_total));
						textField_35.setText(PosscreenArabic.df2.format(vatpric));
					}
					else
					{
						for(int i=0;i<posscreen.model.getRowCount();i++)
						{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement = null;
							try {
								statement = con.createStatement();
								ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_item_master where item_name='"+posscreen.table.getModel().getValueAt(i, 2).toString()+"'");
								if(resultSet.next())
								{
									String vat_per=resultSet.getString("output_tax");
									ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_tax where tax_code='"+vat_per+"'");
									if(resultSet1.next())
									{
										tax_rate=Float.parseFloat(resultSet1.getString("tax_rate"));
									}
								}
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							double unit_price=Double.parseDouble(posscreen.table.getValueAt(i, 4).toString());
							double qty=Double.parseDouble(posscreen.table.getValueAt(i, 5).toString());
							String vattype=posscreen.table.getModel().getValueAt(i, 9).toString();
							
							posscreen.table.setValueAt(posscreen.df2.format(Double.parseDouble(amt)), i, 6);
							if(posscreen.gs.length>1)
							{
							SecondDisplay.table.setValueAt(posscreen.df2.format(Double.parseDouble(amt)), i, 6);
							}
							if(vattype.equals("EXC"))
							{
								desc=((unit_price*qty)*(Double.parseDouble(amt)))/100;	
								total+=(unit_price*qty)-desc;
								vatpric+=total*tax_rate/100;
								posscreen.table.setValueAt(posscreen.df2.format((unit_price*qty)-desc), i, 7);
								posscreen.table.setValueAt(posscreen.df2.format(total*tax_rate/100), i, 8);
								if(posscreen.gs.length>1)
								{
								SecondDisplay.table.setValueAt(posscreen.df2.format((unit_price*qty)-desc), i, 7);
								SecondDisplay.table.setValueAt(posscreen.df2.format(total*tax_rate/100), i, 8);
								}
							}
							if(vattype.equals("INC"))
							{
								desc=(((unit_price*qty)*100/105)*(Double.parseDouble(amt)))/100;	
								total+=((unit_price*100/105)*qty)-desc;
								vatpric+=total*tax_rate/105;
								posscreen.table.setValueAt(posscreen.df2.format(((unit_price*100/105)*qty)-desc), i, 7);
								posscreen.table.setValueAt(posscreen.df2.format((((unit_price*100/105)*qty)-desc)*tax_rate/105), i, 8);
								if(posscreen.gs.length>1)
								{
								SecondDisplay.table.setValueAt(posscreen.df2.format(((unit_price*100/105)*qty)-desc), i, 7);
								SecondDisplay.table.setValueAt(posscreen.df2.format((((unit_price*100/105)*qty)-desc)*tax_rate/105), i, 8);
								}
							}
							if(vattype.equals("NON"))
							{
								desc=((unit_price*qty)*(Double.parseDouble(amt)))/100;	
								total+=((unit_price)*qty)-desc;
								vatpric+=0;
								posscreen.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), i, 7);
								if(posscreen.gs.length>1)
								{
								SecondDisplay.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), i, 7);
								}
							}
						}
						
		    			String itm_disc=amt;
		    			if(!itm_disc.equals(""))
						{
		    				//desc=((total)*(Double.parseDouble(itm_disc)))/100;	
							//total=total-desc;
							//vatpric=((total * 5)/100);
							grand_total=total+vatpric;

							posscreen.textField.setText(posscreen.df2.format(total));
							posscreen.textField_3.setText(posscreen.df2.format(grand_total));
							posscreen.textField_1.setText(posscreen.df2.format(vatpric));
							posscreen.textField_2.setText(posscreen.df2.format(desc));
							posscreen.textField_5.setText(itm_disc+"%");
							posscreen.txtDisc.setText("");
							posscreen.txtDisc.requestFocus();
							
							if(posscreen.gs.length>1)
							{
							SecondDisplay.textField.setText(posscreen.df2.format(total));
							SecondDisplay.textField_3.setText(posscreen.df2.format(grand_total));
							SecondDisplay.textField_1.setText(posscreen.df2.format(vatpric));
							SecondDisplay.textField_2.setText(posscreen.df2.format(desc));
							SecondDisplay.textField_5.setText(itm_disc+"%");
							}
						}
		    			
		    			double bal_amt=0;
		    			String paid_amt=textField_19.getText();
		    			
		    			if(!paid_amt.equals(""))
		         	   	{
		         	   		bal_amt=grand_total-Double.parseDouble(paid_amt);
		         	   	}
		         	   	else
		         	   	{
		         	   		bal_amt=grand_total;
		         	   	}
	         			
	         			BigDecimal bd = new BigDecimal(bal_amt);
	         			bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	         			bal_amt = bd.doubleValue();
	         			
		    			textField_34.setText(amt);	
			         	textField_7.setText(Double.toString(bal_amt));
			         	textField_12.setText("");
			         	textField_12.requestFocus();
			         	textField_33.setText(posscreen.textField_2.getText());
			         	
			         	
			         	textField_3.setText(posscreen.df2.format(total));
						textField_31.setText(posscreen.df2.format(grand_total));
						textField_35.setText(posscreen.df2.format(vatpric));
					}
					
					//textField_19.setText(paid_amt);
		         	frmMultiplePayCode.setAlwaysOnTop(true);
		         	
				}
				else
				{
					JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>Maximum Sale Discount is "+disc_amt +"%<br>الحد الأقصى لخصم البيع هو "+disc_amt +"%</html>");
					
				}
			}
		});
		textField_33.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_33.setForeground(Color.DARK_GRAY);
		textField_33.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textField_33.setColumns(10);
		textField_33.setBackground(Color.YELLOW);
		textField_33.setBounds(154, 693, 140, 35);
		frmMultiplePayCode.getContentPane().add(textField_33);
		
		JButton btnDiscAmt_1 = new JButton("<html>DISC. AMT. <br> \u0645\u0642\u062F\u0627\u0631 \u0627\u0644\u062E\u0635\u0645</html>");
		btnDiscAmt_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(Mainpage.SoftLang.equals("Ar"))
					{
						String amount=textField_12.getText();
						if(PosscreenArabic.table.getRowCount()<=0)
						{
							JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>Please Add Some items<br>الرجاء إضافة بعض العناصر</html>");
							PosscreenArabic.txtDisc.requestFocus();
						}
						else
						{
							//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
							if(amount.equals(""))
							{
								amount="0";
							}
							float total_sub=0;
							for(int i=0;i<PosscreenArabic.model.getRowCount();i++)
							{
								String disc_type=PosscreenArabic.table.getValueAt(i, 15).toString();
								if(disc_type.equals("N"))
								{
									double unit_price=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 4).toString());
									double qty=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 5).toString());
									String vattype=PosscreenArabic.table.getModel().getValueAt(i, 10).toString();
									if(vattype.equals("EXC"))
									{
										total_sub+=(unit_price*qty);
									}
									if(vattype.equals("INC"))
									{
										total_sub+=((unit_price*100/100)*qty);
									}
									if(vattype.equals("NON"))
									{
										total_sub+=((unit_price)*qty);
									}
								}
								
							}
							double disc_perc=(Double.parseDouble(amount)*100)/total_sub;
							if(Double.parseDouble(disc_amt)>=disc_perc)
							{
								double desc=0;
								double desc_amt=0;
								double grand_total=0;
								double total=0;
								double vatpric=0;
								double tax_rate = 0;
								double ttl_bf_disc=0;
								double ttl_af_disc=0;
								
								for(int i=0;i<PosscreenArabic.model.getRowCount();i++)
								{
									double unit_price=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 4).toString());
									double qty=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 5).toString());
									String vattype=PosscreenArabic.table.getModel().getValueAt(i, 10).toString();
									String disc_type=PosscreenArabic.table.getValueAt(i, 15).toString();
									if(disc_type.equals("N"))
									{
										tax_rate=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 16).toString());
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(disc_perc), i, 6);
										if(PosscreenArabic.gs.length>1)
										{
										SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(disc_perc), i, 6);
										}
										if(vattype.equals("EXC"))
										{
											desc=((unit_price*qty)*(disc_perc))/100;	
											desc_amt+=desc;
											total+=(unit_price*qty)-desc;
											vatpric+=((unit_price*qty)-desc)*tax_rate/100;
											double total_line=(unit_price*qty)-desc;
											double line_vat=((unit_price*qty)-desc)*tax_rate/100;
											
											PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line), i, 7);
											PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(line_vat), i, 8);
											PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), i, 9);
											if(PosscreenArabic.gs.length>1)
											{
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line), i, 7);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(line_vat), i, 8);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), i, 9);
											}
										}
										if(vattype.equals("INC"))
										{
											desc=(((unit_price*qty)*100/105)*(disc_perc))/100;
											desc_amt+=desc;
											total+=((unit_price*100/105)*qty)-desc;
											vatpric+=(((unit_price*100/105)*qty)-desc)*tax_rate/100;
											double total_line=((unit_price*qty)*100/105)-desc;
											double line_vat=(((unit_price)*qty*100/105)-desc)*tax_rate/100;
											
											PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line), i, 7);
											PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(line_vat), i, 8);
											PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), i, 9);
											if(PosscreenArabic.gs.length>1)
											{
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line), i, 7);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(line_vat), i, 8);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), i, 9);
											}
										}
										if(vattype.equals("NON"))
										{
											desc=((unit_price*qty)*(disc_perc))/100;	
											desc_amt+=desc;
											total+=((unit_price)*qty)-desc;
											vatpric+=0;
											PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), i, 7);
											PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), i, 9);
											if(PosscreenArabic.gs.length>1)
											{
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), i, 7);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), i, 9);
											}
										}
									}
									else
									{
										total+=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 7).toString());
										vatpric+=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 8).toString());
									}
									
									double totl=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 7).toString());
									double vt=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 8).toString());
									
									if(vattype.equals("INC"))
									{
										ttl_af_disc+=totl+vt;
									}
									else
									{
										ttl_af_disc+=totl;
									}
									
									String ttl_before=PosscreenArabic.df2.format(unit_price*qty);
									
									ttl_bf_disc+=Double.parseDouble(ttl_before);
									//ttl_bf_disc+=unit_price*qty;
								}
								String before_disc=PosscreenArabic.df2.format(ttl_bf_disc);
								String after_disc=PosscreenArabic.df2.format(ttl_af_disc);
								double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
								if(Double.isNaN(ttl_disc_perc))
								{
									ttl_disc_perc=0;
								}
								double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
								
				    			//String itm_disc=Double.toString(ttl_disc_perc);
				    			String itm_disc=PosscreenArabic.df2.format(ttl_disc_perc);
				    			if(!itm_disc.equals(""))
								{
				    				grand_total=total+vatpric;
				    				
				    				double checksum=0;
				    				if(PosscreenArabic.allow_roundoff.equals("1"))
				    				{
				    					String nettotal=PosscreenArabic.df2.format(grand_total);
							    		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
							    		int number_decimal=Integer.parseInt(splitnumber);
							    		
							    		if(number_decimal>0 && number_decimal<13 )
							    		{
							    			checksum=0-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
							    		if(number_decimal>12 && number_decimal<26 )
							    		{
							    			checksum=25-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
							    		if(number_decimal>25 && number_decimal<38 )
							    		{
							    			checksum=25-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
							    		if(number_decimal>37 && number_decimal<51 )
							    		{
							    			checksum=50-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
							    		if(number_decimal>50 && number_decimal<63 )
							    		{
							    			checksum=50-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
							    		if(number_decimal>62 && number_decimal<76 )
							    		{
							    			checksum=75-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
							    		if(number_decimal>75 && number_decimal<88 )
							    		{
							    			checksum=75-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
							    		if(number_decimal>87 && number_decimal<101 )
							    		{
							    			checksum=100-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
				    				}
									
				    				PosscreenArabic.lblNewLabel_1.setText(Double.toString(checksum/100));
						    		textField_31.setText(Double.toString(checksum/100));
						    		
						    		PosscreenArabic.textField.setText(PosscreenArabic.df2.format(total));
						    		PosscreenArabic.textField_3.setText(PosscreenArabic.df2.format(grand_total));
						    		PosscreenArabic.textField_1.setText(PosscreenArabic.df2.format(vatpric));
									PosscreenArabic.textField_2.setText(PosscreenArabic.df2.format(ttl_disc_amt));
									PosscreenArabic.textField_5.setText(itm_disc+"%");
									if(PosscreenArabic.gs.length>1)
									{
									SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
									SecondDisplay.textField.setText(PosscreenArabic.df2.format(total));
									SecondDisplay.textField_3.setText(PosscreenArabic.df2.format(grand_total));
									SecondDisplay.textField_1.setText(PosscreenArabic.df2.format(vatpric));
									SecondDisplay.textField_2.setText(PosscreenArabic.df2.format(ttl_disc_amt));
									SecondDisplay.textField_5.setText(itm_disc+"%");
									}
									
									String space="";
						    		int print_price;
						    		print_price=8-textField_2.getText().length();
					    			for(int i=0; i<print_price;i++)
					    			{
					    				space+=" ";
					    			}
					    			String gap="";
					    			double grand_ttl=grand_total;
						    		String grnd_ttl=PosscreenArabic.df2.format(grand_ttl);
						    		
									int grand_price=15-grnd_ttl.length();
									for(int i=0; i<grand_price;i++)
									{
										gap+=" ";
									}
									Runtime.getRuntime().exec("cmd /c \"CLS>"+PosscreenArabic.display_port+"&&ECHO DISC AMOUNT "+space+""+PosscreenArabic.df2.format(desc_amt)+">"+PosscreenArabic.display_port+"&&ECHO TOTAL"+""+gap+""+PosscreenArabic.df2.format(grand_total)+">"+PosscreenArabic.display_port+"\"");
						    		//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO DISC AMOUNT "+space+""+df2.format(desc_amt)+"TOTAL"+""+gap+""+df2.format(grand_total)+">"+display_port+"\""});
									PosscreenArabic.txtDisc.setText("");
									PosscreenArabic.txtDisc.requestFocus();
								}
				    			PosscreenArabic.get_user_validate_dt(company, username,store_name,reg_num);
				    			
				    			double bal_amt=0;
				    			String paid_amt=textField_19.getText();
				    			
				    			if(!paid_amt.equals(""))
				         	   	{
				         	   		bal_amt=grand_total-Double.parseDouble(paid_amt);
				         	   	}
				         	   	else
				         	   	{
				         	   		bal_amt=grand_total;
				         	   	}
			         			
			         			BigDecimal bd = new BigDecimal(bal_amt);
			         			bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
			         			bal_amt = bd.doubleValue();
			         			
			         			BigDecimal be = new BigDecimal(disc_perc);
			         			be = be.setScale(decimal, BigDecimal.ROUND_HALF_UP);
			         			disc_perc = be.doubleValue();
			         			
				    			textField_34.setText(Double.toString(disc_perc));	
					         	textField_7.setText(Double.toString(bal_amt));
					         	textField_12.setText("");
					         	textField_12.requestFocus();
					         	textField_33.setText(PosscreenArabic.textField_2.getText());
					         	
					         	
					         	textField_3.setText(PosscreenArabic.df2.format(total));
								textField_36.setText(PosscreenArabic.df2.format(grand_total));
								textField_35.setText(PosscreenArabic.df2.format(vatpric));
								//textField_19.setText(paid_amt);
					         	frmMultiplePayCode.setAlwaysOnTop(true);
							}
							else
							{
								frmMultiplePayCode.setAlwaysOnTop(true);
								JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>Maximum Sale Discount is "+disc_amt+" %<br>الحد الأقصى لخصم البيع هو"+disc_amt+"% </html>");
								textField_12.requestFocus();
							}
						}
					}
					else
					{
						String amount=textField_12.getText();
						if(posscreen.table.getRowCount()<=0)
						{
							JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>Please Add Some items<br>الرجاء إضافة بعض العناصر</html>");
							posscreen.txtDisc.requestFocus();
						}
						else
						{
							//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+display_port+"\""});
							if(amount.equals(""))
							{
								amount="0";
							}
							float total_sub=0;
							for(int i=0;i<posscreen.model.getRowCount();i++)
							{
								String disc_type=posscreen.table.getValueAt(i, 15).toString();
								if(disc_type.equals("N"))
								{
									double unit_price=Double.parseDouble(posscreen.table.getValueAt(i, 4).toString());
									double qty=Double.parseDouble(posscreen.table.getValueAt(i, 5).toString());
									String vattype=posscreen.table.getModel().getValueAt(i, 10).toString();
									if(vattype.equals("EXC"))
									{
										total_sub+=(unit_price*qty);
									}
									if(vattype.equals("INC"))
									{
										total_sub+=((unit_price*100/100)*qty);
									}
									if(vattype.equals("NON"))
									{
										total_sub+=((unit_price)*qty);
									}
								}
								
							}
							double disc_perc=(Double.parseDouble(amount)*100)/total_sub;
							if(Double.parseDouble(disc_amt)>=disc_perc)
							{
								double desc=0;
								double desc_amt=0;
								double grand_total=0;
								double total=0;
								double vatpric=0;
								double tax_rate = 0;
								double ttl_bf_disc=0;
								double ttl_af_disc=0;
								
								for(int i=0;i<posscreen.model.getRowCount();i++)
								{
									double unit_price=Double.parseDouble(posscreen.table.getValueAt(i, 4).toString());
									double qty=Double.parseDouble(posscreen.table.getValueAt(i, 5).toString());
									String vattype=posscreen.table.getModel().getValueAt(i, 10).toString();
									String disc_type=posscreen.table.getValueAt(i, 15).toString();
									if(disc_type.equals("N"))
									{
										tax_rate=Double.parseDouble(posscreen.table.getValueAt(i, 16).toString());
										posscreen.table.setValueAt(posscreen.df2.format(disc_perc), i, 6);
										if(posscreen.gs.length>1)
										{
										SecondDisplay.table.setValueAt(posscreen.df2.format(disc_perc), i, 6);
										}
										if(vattype.equals("EXC"))
										{
											desc=((unit_price*qty)*(disc_perc))/100;	
											desc_amt+=desc;
											total+=(unit_price*qty)-desc;
											vatpric+=((unit_price*qty)-desc)*tax_rate/100;
											double total_line=(unit_price*qty)-desc;
											double line_vat=((unit_price*qty)-desc)*tax_rate/100;
											
											posscreen.table.setValueAt(posscreen.df2.format(total_line), i, 7);
											posscreen.table.setValueAt(posscreen.df2.format(line_vat), i, 8);
											posscreen.table.setValueAt(posscreen.df2.format(total_line+line_vat), i, 9);
											if(posscreen.gs.length>1)
											{
											SecondDisplay.table.setValueAt(posscreen.df2.format(total_line), i, 7);
											SecondDisplay.table.setValueAt(posscreen.df2.format(line_vat), i, 8);
											SecondDisplay.table.setValueAt(posscreen.df2.format(total_line+line_vat), i, 9);
											}
										}
										if(vattype.equals("INC"))
										{
											desc=(((unit_price*qty)*100/105)*(disc_perc))/100;
											desc_amt+=desc;
											total+=((unit_price*100/105)*qty)-desc;
											vatpric+=(((unit_price*100/105)*qty)-desc)*tax_rate/100;
											double total_line=((unit_price*qty)*100/105)-desc;
											double line_vat=(((unit_price)*qty*100/105)-desc)*tax_rate/100;
											
											posscreen.table.setValueAt(posscreen.df2.format(total_line), i, 7);
											posscreen.table.setValueAt(posscreen.df2.format(line_vat), i, 8);
											posscreen.table.setValueAt(posscreen.df2.format(total_line+line_vat), i, 9);
											if(posscreen.gs.length>1)
											{
											SecondDisplay.table.setValueAt(posscreen.df2.format(total_line), i, 7);
											SecondDisplay.table.setValueAt(posscreen.df2.format(line_vat), i, 8);
											SecondDisplay.table.setValueAt(posscreen.df2.format(total_line+line_vat), i, 9);
											}
										}
										if(vattype.equals("NON"))
										{
											desc=((unit_price*qty)*(disc_perc))/100;	
											desc_amt+=desc;
											total+=((unit_price)*qty)-desc;
											vatpric+=0;
											posscreen.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), i, 7);
											posscreen.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), i, 9);
											if(posscreen.gs.length>1)
											{
											SecondDisplay.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), i, 7);
											SecondDisplay.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), i, 9);
											}
										}
									}
									else
									{
										total+=Double.parseDouble(posscreen.table.getValueAt(i, 7).toString());
										vatpric+=Double.parseDouble(posscreen.table.getValueAt(i, 8).toString());
									}
									
									double totl=Double.parseDouble(posscreen.table.getValueAt(i, 7).toString());
									double vt=Double.parseDouble(posscreen.table.getValueAt(i, 8).toString());
									
									if(vattype.equals("INC"))
									{
										ttl_af_disc+=totl+vt;
									}
									else
									{
										ttl_af_disc+=totl;
									}
									
									String ttl_before=posscreen.df2.format(unit_price*qty);
									
									ttl_bf_disc+=Double.parseDouble(ttl_before);
									//ttl_bf_disc+=unit_price*qty;
								}
								String before_disc=posscreen.df2.format(ttl_bf_disc);
								String after_disc=posscreen.df2.format(ttl_af_disc);
								double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
								if(Double.isNaN(ttl_disc_perc))
								{
									ttl_disc_perc=0;
								}
								double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
								
				    			//String itm_disc=Double.toString(ttl_disc_perc);
				    			String itm_disc=posscreen.df2.format(ttl_disc_perc);
				    			if(!itm_disc.equals(""))
								{
				    				grand_total=total+vatpric;
				    				
				    				double checksum=0;
				    				if(posscreen.allow_roundoff.equals("1"))
				    				{
				    					String nettotal=posscreen.df2.format(grand_total);
							    		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
							    		int number_decimal=Integer.parseInt(splitnumber);
							    		
							    		if(number_decimal>0 && number_decimal<13 )
							    		{
							    			checksum=0-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
							    		if(number_decimal>12 && number_decimal<26 )
							    		{
							    			checksum=25-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
							    		if(number_decimal>25 && number_decimal<38 )
							    		{
							    			checksum=25-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
							    		if(number_decimal>37 && number_decimal<51 )
							    		{
							    			checksum=50-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
							    		if(number_decimal>50 && number_decimal<63 )
							    		{
							    			checksum=50-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
							    		if(number_decimal>62 && number_decimal<76 )
							    		{
							    			checksum=75-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
							    		if(number_decimal>75 && number_decimal<88 )
							    		{
							    			checksum=75-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
							    		if(number_decimal>87 && number_decimal<101 )
							    		{
							    			checksum=100-number_decimal;
							    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
							    		}
				    				}
									
				    				posscreen.lblNewLabel_1.setText(Double.toString(checksum/100));
						    		textField_31.setText(Double.toString(checksum/100));
						    		
						    		posscreen.textField.setText(posscreen.df2.format(total));
						    		posscreen.textField_3.setText(posscreen.df2.format(grand_total));
						    		posscreen.textField_1.setText(posscreen.df2.format(vatpric));
									posscreen.textField_2.setText(posscreen.df2.format(ttl_disc_amt));
									posscreen.textField_5.setText(itm_disc+"%");
									if(posscreen.gs.length>1)
									{
									SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
									SecondDisplay.textField.setText(posscreen.df2.format(total));
									SecondDisplay.textField_3.setText(posscreen.df2.format(grand_total));
									SecondDisplay.textField_1.setText(posscreen.df2.format(vatpric));
									SecondDisplay.textField_2.setText(posscreen.df2.format(ttl_disc_amt));
									SecondDisplay.textField_5.setText(itm_disc+"%");
									}
									
									String space="";
						    		int print_price;
						    		print_price=8-textField_2.getText().length();
					    			for(int i=0; i<print_price;i++)
					    			{
					    				space+=" ";
					    			}
					    			String gap="";
					    			double grand_ttl=grand_total;
						    		String grnd_ttl=posscreen.df2.format(grand_ttl);
						    		
									int grand_price=15-grnd_ttl.length();
									for(int i=0; i<grand_price;i++)
									{
										gap+=" ";
									}
									Runtime.getRuntime().exec("cmd /c \"CLS>"+posscreen.display_port+"&&ECHO DISC AMOUNT "+space+""+posscreen.df2.format(desc_amt)+">"+posscreen.display_port+"&&ECHO TOTAL"+""+gap+""+posscreen.df2.format(grand_total)+">"+posscreen.display_port+"\"");
						    		//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO DISC AMOUNT "+space+""+df2.format(desc_amt)+"TOTAL"+""+gap+""+df2.format(grand_total)+">"+display_port+"\""});
									posscreen.txtDisc.setText("");
									posscreen.txtDisc.requestFocus();
								}
				    			posscreen.get_user_validate_dt(company, username,store_name,reg_num);
				    			
				    			double bal_amt=0;
				    			String paid_amt=textField_19.getText();
				    			
				    			if(!paid_amt.equals(""))
				         	   	{
				         	   		bal_amt=grand_total-Double.parseDouble(paid_amt);
				         	   	}
				         	   	else
				         	   	{
				         	   		bal_amt=grand_total;
				         	   	}
			         			
			         			BigDecimal bd = new BigDecimal(bal_amt);
			         			bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
			         			bal_amt = bd.doubleValue();
			         			
			         			BigDecimal be = new BigDecimal(disc_perc);
			         			be = be.setScale(decimal, BigDecimal.ROUND_HALF_UP);
			         			disc_perc = be.doubleValue();
			         			
				    			textField_34.setText(Double.toString(disc_perc));	
					         	textField_7.setText(Double.toString(bal_amt));
					         	textField_12.setText("");
					         	textField_12.requestFocus();
					         	textField_33.setText(posscreen.textField_2.getText());
					         	
					         	
					         	textField_3.setText(posscreen.df2.format(total));
								textField_36.setText(posscreen.df2.format(grand_total));
								textField_35.setText(posscreen.df2.format(vatpric));
								//textField_19.setText(paid_amt);
					         	frmMultiplePayCode.setAlwaysOnTop(true);
							}
							else
							{
								frmMultiplePayCode.setAlwaysOnTop(true);
								JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>Maximum Sale Discount is "+disc_amt+" %<br>الحد الأقصى لخصم البيع هو"+disc_amt+"% </html>");
								textField_12.requestFocus();
							}
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		btnDiscAmt_1.setForeground(Color.DARK_GRAY);
		btnDiscAmt_1.setFont(new Font("Arial", Font.BOLD, 15));
		btnDiscAmt_1.setBorder(new LineBorder(SystemColor.control));
		btnDiscAmt_1.setBackground(Color.ORANGE);
		btnDiscAmt_1.setBounds(12, 693, 142, 35);
		frmMultiplePayCode.getContentPane().add(btnDiscAmt_1);
		
		JButton btnDisc_1 = new JButton("<html>DISC. % <br> \u0646\u0633\u0628\u0629 \u0627\u0644\u062E\u0635\u0645</html>");
		btnDisc_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String amount=textField_12.getText();				
					if(amount.equals(""))
					{
						amount="0";
					}
					if(Mainpage.SoftLang.equals("Ar"))
					{
						if(Double.parseDouble(disc_amt)>=Double.parseDouble(amount))
						{
							double desc=0;
							//double desc_amt=0;
							double grand_total=0;
							double total=0;
							double vatpric=0;
							double tax_rate = 0;
							double ttl_bf_disc=0;
							double ttl_af_disc=0;
							
							for(int i=0;i<PosscreenArabic.model.getRowCount();i++)
							{
								String vattype=PosscreenArabic.table.getModel().getValueAt(i, 10).toString();
								double unit_price=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 4).toString());
								double qty=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 5).toString());
								String disc_type=PosscreenArabic.table.getValueAt(i, 15).toString();
								if(disc_type.equals("N"))
								{
									tax_rate=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 16).toString());
									PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(Double.parseDouble(amount)), i, 6);
									if(vattype.equals("EXC"))
									{
										desc=((unit_price*qty)*(Double.parseDouble(amount)))/100;
										//desc_amt+=desc;
										total+=(unit_price*qty)-desc;
										vatpric+=((unit_price*qty)-desc)*tax_rate/100;
										double total_line=(unit_price*qty)-desc;
										double line_vat=((unit_price*qty)-desc)*tax_rate/100;
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line), i, 7);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(line_vat), i, 8);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), i, 9);
										if(PosscreenArabic.gs.length>1)
										{
										SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line), i, 7);
										SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(line_vat), i, 8);
										SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), i, 9);
										}
									}
									if(vattype.equals("INC"))
									{
										desc=(((unit_price*qty)*100/105)*(Double.parseDouble(amount)))/100;
										//desc_amt+=desc;
										total+=((unit_price*100/105)*qty)-desc;
										vatpric+=(((unit_price*100/105)*qty)-desc)*tax_rate/100;
										double total_line=((unit_price)*qty*100/105)-desc;
										double line_vat=(((unit_price)*qty*100/105)-desc)*tax_rate/100;
										
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line), i, 7);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(line_vat), i, 8);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), i, 9);
										if(PosscreenArabic.gs.length>1)
										{
										SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line), i, 7);
										SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(line_vat), i, 8);
										SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), i, 9);
										}
									}
									if(vattype.equals("NON"))
									{
										desc=((unit_price*qty)*(Double.parseDouble(amount)))/100;
										//desc_amt+=desc;
										total+=((unit_price)*qty)-desc;
										vatpric+=0;
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), i, 7);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), i, 9);
										if(PosscreenArabic.gs.length>1)
										{
										SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), i, 7);
										SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), i, 9);
										}
									}
									
									
								}
								else
								{
									total+=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 7).toString());
									vatpric+=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 8).toString());
								}
								
								
								double totl=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 7).toString());
								double vt=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 8).toString());
								
								if(vattype.equals("INC"))
								{
									ttl_af_disc+=totl+vt;
								}
								else
								{
									ttl_af_disc+=totl;
								}
								
								String ttl_before=PosscreenArabic.df2.format(unit_price*qty);
								
								ttl_bf_disc+=Double.parseDouble(ttl_before);
								//ttl_bf_disc+=unit_price*qty;
								
								
							}
							
							String before_disc=PosscreenArabic.df2.format(ttl_bf_disc);
							String after_disc=PosscreenArabic.df2.format(ttl_af_disc);
							double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
							if(Double.isNaN(ttl_disc_perc))
							{
								ttl_disc_perc=0;
							}
							double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
							
			    			String itm_disc=Double.toString(ttl_disc_perc);
			    			if(!itm_disc.equals(""))
							{
			    				grand_total=total+vatpric;
			    				
			    				double checksum=0;
			    				if(PosscreenArabic.allow_roundoff.equals("1"))
			    				{
			    					String nettotal=PosscreenArabic.df2.format(grand_total);
						    		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
						    		int number_decimal=Integer.parseInt(splitnumber);
						    		
						    		if(number_decimal>0 && number_decimal<13 )
						    		{
						    			checksum=0-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
						    		if(number_decimal>12 && number_decimal<26 )
						    		{
						    			checksum=25-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
						    		if(number_decimal>25 && number_decimal<38 )
						    		{
						    			checksum=25-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
						    		if(number_decimal>37 && number_decimal<51 )
						    		{
						    			checksum=50-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
						    		if(number_decimal>50 && number_decimal<63 )
						    		{
						    			checksum=50-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
						    		if(number_decimal>62 && number_decimal<76 )
						    		{
						    			checksum=75-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
						    		if(number_decimal>75 && number_decimal<88 )
						    		{
						    			checksum=75-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
						    		if(number_decimal>87 && number_decimal<101 )
						    		{
						    			checksum=100-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
			    				}
			    				PosscreenArabic.lblNewLabel_1.setText(Double.toString(checksum/100));
			    				textField_31.setText(Double.toString(checksum/100));
			    				if(PosscreenArabic.gs.length>1)
								{
			    				SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
								}
					    		
			    				PosscreenArabic.textField.setText(PosscreenArabic.df2.format(total));
			    				PosscreenArabic.textField_3.setText(PosscreenArabic.df2.format(grand_total));
								PosscreenArabic.textField_1.setText(PosscreenArabic.df2.format(vatpric));
								PosscreenArabic.textField_2.setText(PosscreenArabic.df2.format(ttl_disc_amt));
								PosscreenArabic.textField_5.setText(PosscreenArabic.df2.format(Double.parseDouble(itm_disc))+"%");
								if(PosscreenArabic.gs.length>1)
								{
								SecondDisplay.textField.setText(PosscreenArabic.df2.format(total));
								SecondDisplay.textField_3.setText(PosscreenArabic.df2.format(grand_total));
								SecondDisplay.textField_1.setText(PosscreenArabic.df2.format(vatpric));
								SecondDisplay.textField_2.setText(PosscreenArabic.df2.format(ttl_disc_amt));
								SecondDisplay.textField_5.setText(PosscreenArabic.df2.format(Double.parseDouble(itm_disc))+"%");
								}
								String space="";
					    		int print_price;
					    		String perc_disc=PosscreenArabic.df2.format(Double.parseDouble(itm_disc));
					    		print_price=10-perc_disc.length();
				    			for(int i=0; i<print_price;i++)
				    			{
				    				space+=" ";
				    			}
				    			String gap="";
				    			double grand_ttl=grand_total;
					    		String grnd_ttl=PosscreenArabic.df2.format(grand_ttl);
					    		
								int grand_price=15-grnd_ttl.length();
								for(int i=0; i<grand_price;i++)
								{
									gap+=" ";
								}
								Runtime.getRuntime().exec("cmd /c \"CLS>"+PosscreenArabic.display_port+"&&ECHO DISC PERC "+space+""+PosscreenArabic.df2.format(Double.parseDouble(itm_disc))+">"+PosscreenArabic.display_port+"&&ECHO TOTAL"+""+gap+""+PosscreenArabic.df2.format(grand_total)+">"+PosscreenArabic.display_port+"\"");
								PosscreenArabic.txtDisc.setText("");
								PosscreenArabic.txtDisc.requestFocus();
							}
			    			PosscreenArabic.get_user_validate_dt(company, username,store_name,reg_num);
			    			
			    			double bal_amt=0;
			    			String paid_amt=textField_19.getText();
			    			
			    			if(!paid_amt.equals(""))
			         	   	{
			         	   		bal_amt=grand_total-Double.parseDouble(paid_amt);
			         	   	}
			         	   	else
			         	   	{
			         	   		bal_amt=grand_total;
			         	   	}
		         			
		         			BigDecimal bd = new BigDecimal(bal_amt);
		         			bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
		         			bal_amt = bd.doubleValue();
			    			
			    			textField_34.setText(amount);	
				         	textField_7.setText(Double.toString(bal_amt));
				         	textField_12.setText("");
				         	textField_12.requestFocus();
				         	textField_33.setText(PosscreenArabic.textField_2.getText());
				         	
				         	
				         	textField_3.setText(PosscreenArabic.df2.format(total));
							textField_36.setText(PosscreenArabic.df2.format(grand_total));
							textField_35.setText(PosscreenArabic.df2.format(vatpric));
							//textField_19.setText(paid_amt);
				         	frmMultiplePayCode.setAlwaysOnTop(true);
						}
						else
						{
							frmMultiplePayCode.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>Maximum Sale Discount is "+disc_amt+" %<br>الحد الأقصى لخصم البيع هو"+disc_amt+"% </html>");
							PosscreenArabic.txtDisc.requestFocus();
						}
					}
					else
					{
						if(Double.parseDouble(disc_amt)>=Double.parseDouble(amount))
						{
							double desc=0;
							//double desc_amt=0;
							double grand_total=0;
							double total=0;
							double vatpric=0;
							double tax_rate = 0;
							double ttl_bf_disc=0;
							double ttl_af_disc=0;
							
							for(int i=0;i<posscreen.model.getRowCount();i++)
							{
								String vattype=posscreen.table.getModel().getValueAt(i, 10).toString();
								double unit_price=Double.parseDouble(posscreen.table.getValueAt(i, 4).toString());
								double qty=Double.parseDouble(posscreen.table.getValueAt(i, 5).toString());
								String disc_type=posscreen.table.getValueAt(i, 15).toString();
								if(disc_type.equals("N"))
								{
									tax_rate=Double.parseDouble(posscreen.table.getValueAt(i, 16).toString());
									posscreen.table.setValueAt(posscreen.df2.format(Double.parseDouble(amount)), i, 6);
									if(vattype.equals("EXC"))
									{
										desc=((unit_price*qty)*(Double.parseDouble(amount)))/100;
										//desc_amt+=desc;
										total+=(unit_price*qty)-desc;
										vatpric+=((unit_price*qty)-desc)*tax_rate/100;
										double total_line=(unit_price*qty)-desc;
										double line_vat=((unit_price*qty)-desc)*tax_rate/100;
										posscreen.table.setValueAt(posscreen.df2.format(total_line), i, 7);
										posscreen.table.setValueAt(posscreen.df2.format(line_vat), i, 8);
										posscreen.table.setValueAt(posscreen.df2.format(total_line+line_vat), i, 9);
										if(posscreen.gs.length>1)
										{
										SecondDisplay.table.setValueAt(posscreen.df2.format(total_line), i, 7);
										SecondDisplay.table.setValueAt(posscreen.df2.format(line_vat), i, 8);
										SecondDisplay.table.setValueAt(posscreen.df2.format(total_line+line_vat), i, 9);
										}
									}
									if(vattype.equals("INC"))
									{
										desc=(((unit_price*qty)*100/105)*(Double.parseDouble(amount)))/100;
										//desc_amt+=desc;
										total+=((unit_price*100/105)*qty)-desc;
										vatpric+=(((unit_price*100/105)*qty)-desc)*tax_rate/100;
										double total_line=((unit_price)*qty*100/105)-desc;
										double line_vat=(((unit_price)*qty*100/105)-desc)*tax_rate/100;
										
										posscreen.table.setValueAt(posscreen.df2.format(total_line), i, 7);
										posscreen.table.setValueAt(posscreen.df2.format(line_vat), i, 8);
										posscreen.table.setValueAt(posscreen.df2.format(total_line+line_vat), i, 9);
										if(posscreen.gs.length>1)
										{
										SecondDisplay.table.setValueAt(posscreen.df2.format(total_line), i, 7);
										SecondDisplay.table.setValueAt(posscreen.df2.format(line_vat), i, 8);
										SecondDisplay.table.setValueAt(posscreen.df2.format(total_line+line_vat), i, 9);
										}
									}
									if(vattype.equals("NON"))
									{
										desc=((unit_price*qty)*(Double.parseDouble(amount)))/100;
										//desc_amt+=desc;
										total+=((unit_price)*qty)-desc;
										vatpric+=0;
										posscreen.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), i, 7);
										posscreen.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), i, 9);
										if(posscreen.gs.length>1)
										{
										SecondDisplay.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), i, 7);
										SecondDisplay.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), i, 9);
										}
									}
									
									
								}
								else
								{
									total+=Double.parseDouble(posscreen.table.getValueAt(i, 7).toString());
									vatpric+=Double.parseDouble(posscreen.table.getValueAt(i, 8).toString());
								}
								
								
								double totl=Double.parseDouble(posscreen.table.getValueAt(i, 7).toString());
								double vt=Double.parseDouble(posscreen.table.getValueAt(i, 8).toString());
								
								if(vattype.equals("INC"))
								{
									ttl_af_disc+=totl+vt;
								}
								else
								{
									ttl_af_disc+=totl;
								}
								
								String ttl_before=posscreen.df2.format(unit_price*qty);
								
								ttl_bf_disc+=Double.parseDouble(ttl_before);
								//ttl_bf_disc+=unit_price*qty;
								
								
							}
							
							String before_disc=posscreen.df2.format(ttl_bf_disc);
							String after_disc=posscreen.df2.format(ttl_af_disc);
							double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
							if(Double.isNaN(ttl_disc_perc))
							{
								ttl_disc_perc=0;
							}
							double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
							
			    			String itm_disc=Double.toString(ttl_disc_perc);
			    			if(!itm_disc.equals(""))
							{
			    				grand_total=total+vatpric;
			    				
			    				double checksum=0;
			    				if(posscreen.allow_roundoff.equals("1"))
			    				{
			    					String nettotal=posscreen.df2.format(grand_total);
						    		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
						    		int number_decimal=Integer.parseInt(splitnumber);
						    		
						    		if(number_decimal>0 && number_decimal<13 )
						    		{
						    			checksum=0-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
						    		if(number_decimal>12 && number_decimal<26 )
						    		{
						    			checksum=25-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
						    		if(number_decimal>25 && number_decimal<38 )
						    		{
						    			checksum=25-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
						    		if(number_decimal>37 && number_decimal<51 )
						    		{
						    			checksum=50-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
						    		if(number_decimal>50 && number_decimal<63 )
						    		{
						    			checksum=50-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
						    		if(number_decimal>62 && number_decimal<76 )
						    		{
						    			checksum=75-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
						    		if(number_decimal>75 && number_decimal<88 )
						    		{
						    			checksum=75-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
						    		if(number_decimal>87 && number_decimal<101 )
						    		{
						    			checksum=100-number_decimal;
						    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
						    		}
			    				}
			    				posscreen.lblNewLabel_1.setText(Double.toString(checksum/100));
			    				textField_31.setText(Double.toString(checksum/100));
			    				if(posscreen.gs.length>1)
								{
			    				SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
								}
					    		
			    				posscreen.textField.setText(posscreen.df2.format(total));
			    				posscreen.textField_3.setText(posscreen.df2.format(grand_total));
								posscreen.textField_1.setText(posscreen.df2.format(vatpric));
								posscreen.textField_2.setText(posscreen.df2.format(ttl_disc_amt));
								posscreen.textField_5.setText(posscreen.df2.format(Double.parseDouble(itm_disc))+"%");
								if(posscreen.gs.length>1)
								{
								SecondDisplay.textField.setText(posscreen.df2.format(total));
								SecondDisplay.textField_3.setText(posscreen.df2.format(grand_total));
								SecondDisplay.textField_1.setText(posscreen.df2.format(vatpric));
								SecondDisplay.textField_2.setText(posscreen.df2.format(ttl_disc_amt));
								SecondDisplay.textField_5.setText(posscreen.df2.format(Double.parseDouble(itm_disc))+"%");
								}
								String space="";
					    		int print_price;
					    		String perc_disc=posscreen.df2.format(Double.parseDouble(itm_disc));
					    		print_price=10-perc_disc.length();
				    			for(int i=0; i<print_price;i++)
				    			{
				    				space+=" ";
				    			}
				    			String gap="";
				    			double grand_ttl=grand_total;
					    		String grnd_ttl=posscreen.df2.format(grand_ttl);
					    		
								int grand_price=15-grnd_ttl.length();
								for(int i=0; i<grand_price;i++)
								{
									gap+=" ";
								}
								Runtime.getRuntime().exec("cmd /c \"CLS>"+posscreen.display_port+"&&ECHO DISC PERC "+space+""+posscreen.df2.format(Double.parseDouble(itm_disc))+">"+posscreen.display_port+"&&ECHO TOTAL"+""+gap+""+posscreen.df2.format(grand_total)+">"+posscreen.display_port+"\"");
								posscreen.txtDisc.setText("");
								posscreen.txtDisc.requestFocus();
							}
			    			posscreen.get_user_validate_dt(company, username,store_name,reg_num);
			    			
			    			double bal_amt=0;
			    			String paid_amt=textField_19.getText();
			    			
			    			if(!paid_amt.equals(""))
			         	   	{
			         	   		bal_amt=grand_total-Double.parseDouble(paid_amt);
			         	   	}
			         	   	else
			         	   	{
			         	   		bal_amt=grand_total;
			         	   	}
		         			
		         			BigDecimal bd = new BigDecimal(bal_amt);
		         			bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
		         			bal_amt = bd.doubleValue();
			    			
			    			textField_34.setText(amount);	
				         	textField_7.setText(Double.toString(bal_amt));
				         	textField_12.setText("");
				         	textField_12.requestFocus();
				         	textField_33.setText(posscreen.textField_2.getText());
				         	
				         	
				         	textField_3.setText(posscreen.df2.format(total));
							textField_36.setText(posscreen.df2.format(grand_total));
							textField_35.setText(posscreen.df2.format(vatpric));
							//textField_19.setText(paid_amt);
				         	frmMultiplePayCode.setAlwaysOnTop(true);
						}
						else
						{
							frmMultiplePayCode.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>Maximum Sale Discount is "+disc_amt+" %<br>الحد الأقصى لخصم البيع هو"+disc_amt+"% </html>");
							posscreen.txtDisc.requestFocus();
						}
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(frmMultiplePayCode, e.getMessage());
				}
				
			}
		});
		btnDisc_1.setForeground(Color.DARK_GRAY);
		btnDisc_1.setFont(new Font("Arial", Font.BOLD, 15));
		btnDisc_1.setBorder(new LineBorder(SystemColor.control));
		btnDisc_1.setBackground(Color.ORANGE);
		btnDisc_1.setBounds(12, 655, 142, 35);
		frmMultiplePayCode.getContentPane().add(btnDisc_1);
		
		textField_30 = new JTextField();
		textField_30.setEnabled(false);
		textField_30.setEditable(false);
		textField_30.setVisible(false);
		textField_30.setBounds(388, 667, 116, 22);
		frmMultiplePayCode.getContentPane().add(textField_30);
		textField_30.setColumns(10);
		
		textField_3 = new JTextField(ttlamt);
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setForeground(Color.WHITE);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBackground(Color.BLACK);
		textField_3.setBounds(639, 7, 190, 30);
		frmMultiplePayCode.getContentPane().add(textField_3);
		
		textField_31 = new JTextField(roundoff);
		textField_31.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_31.setForeground(Color.WHITE);
		textField_31.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField_31.setEditable(false);
		textField_31.setColumns(10);
		textField_31.setBackground(Color.BLACK);
		textField_31.setBounds(639, 81, 190, 30);
		frmMultiplePayCode.getContentPane().add(textField_31);
		
		textField_35 = new JTextField(ttlvat);
		textField_35.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_35.setForeground(Color.WHITE);
		textField_35.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField_35.setEditable(false);
		textField_35.setColumns(10);
		textField_35.setBackground(Color.BLACK);
		textField_35.setBounds(639, 44, 190, 30);
		frmMultiplePayCode.getContentPane().add(textField_35);
		
		JLabel lblTotalAmount = new JLabel("<html>TOTAL AMOUNT<br>\u0627\u0644\u0645\u0628\u0644\u063A \u0627\u0644\u0625\u062C\u0645\u0627\u0644\u064A</html>");
		lblTotalAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAmount.setForeground(Color.LIGHT_GRAY);
		lblTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalAmount.setBackground(Color.RED);
		lblTotalAmount.setBounds(508, 5, 131, 35);
		frmMultiplePayCode.getContentPane().add(lblTotalAmount);
		
		JLabel lblTotalVat = new JLabel("<html>TOTAL VAT<br>\u0645\u062C\u0645\u0648\u0639 \u0627\u0644\u0636\u0631\u064A\u0628\u0629 </html>");
		lblTotalVat.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalVat.setForeground(Color.LIGHT_GRAY);
		lblTotalVat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalVat.setBackground(Color.RED);
		lblTotalVat.setBounds(508, 40, 131, 35);
		frmMultiplePayCode.getContentPane().add(lblTotalVat);
		
		JLabel lblGrandTotal = new JLabel("<html>GRAND TOTAL<br>\u0627\u0644\u0645\u062C\u0645\u0648\u0639 \u0627\u0644\u0625\u062C\u0645\u0627\u0644\u064A </html>");
		lblGrandTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrandTotal.setForeground(Color.LIGHT_GRAY);
		lblGrandTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGrandTotal.setBackground(Color.RED);
		lblGrandTotal.setBounds(508, 118, 131, 35);
		frmMultiplePayCode.getContentPane().add(lblGrandTotal);
		
		textField_36 = new JTextField(balancetxt);
		textField_36.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_36.setForeground(Color.WHITE);
		textField_36.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField_36.setEditable(false);
		textField_36.setColumns(10);
		textField_36.setBackground(Color.BLACK);
		textField_36.setBounds(639, 118, 190, 30);
		frmMultiplePayCode.getContentPane().add(textField_36);
		
		JLabel lblRoundOff = new JLabel("<html>ROUND OFF<br>\u0646\u0647\u0627\u064A\u0629 \u0627\u0644\u062C\u0648\u0644\u0629</html>");
		lblRoundOff.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoundOff.setForeground(Color.LIGHT_GRAY);
		lblRoundOff.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRoundOff.setBackground(Color.RED);
		lblRoundOff.setBounds(508, 77, 131, 35);
		frmMultiplePayCode.getContentPane().add(lblRoundOff);
		
		if(!Credit_list.equals(""))
		{
			textField_30.setText(Credit_list);
		}
		
		textField_12.requestFocus();
		get_full_paycode(company);
		get_custmer_balance(company,store_name, cust_code);
		get_register_name(company,username,store_name,reg_num);
		
		
		clickOncash( btncashf, "play", KeyEvent.VK_F1 );
		clickOnar( btnAr, "play", KeyEvent.VK_F2 );
		clickOnvisa( btnVisa, "play", KeyEvent.VK_F3 );
		
		
		clickOnsettle(btnSettleF,"play",KeyEvent.VK_F10,company,receipt_no, reg_num, cust_name, store_name, username, qty, type, cust_code, decimal, version );
		clickOnback( btnBackToPos, "play", KeyEvent.VK_ESCAPE,company, store_name, version );
	}
	public static void clickOncash(final AbstractButton button, String actionName, int key)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6694624852420723982L;

			/**
			 * 
			 */
			public void actionPerformed( ActionEvent e )
	        {
				String amt=textField_12.getText();
				if(amt.equals(""))
				{
					amt=textField_7.getText();
				}
				textField.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
	        }
	    } );
	}
	public static void clickOnar(final AbstractButton button, String actionName, int key)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6694624852420723982L;

			/**
			 * 
			 */
			public void actionPerformed( ActionEvent e )
	        {
				String amt=textField_12.getText();
				if(amt.equals(""))
				{
					amt=textField_7.getText();
				}
				if(Float.parseFloat(textField_28.getText()) >= Float.parseFloat(amt))
				{
					textField_1.setText(amt);
					textField_12.setText("");
					textField_12.requestFocus();
				}
				else
				{
					JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>Do not have sufficient Amount / Choose A Customer<br>ليس لديك المبلغ الكافي / اختر عميلاً</html>");
				}
	        }
	    } );
	}
	public static void clickOnvisa(final AbstractButton button, String actionName, int key)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6694624852420723982L;

			/**
			 * 
			 */
			public void actionPerformed( ActionEvent e )
	        {
				String amt=textField_12.getText();
				if(amt.equals(""))
				{
					amt=textField_7.getText();
				}
				else if(Double.parseDouble(amt)==Double.parseDouble(textField_7.getText()))
				{
					amt=textField_7.getText();
				}
				else if(Double.parseDouble(amt)<Double.parseDouble(textField_7.getText()))
				{
					amt=textField_12.getText();
					
				}
				else
				{
					JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>Settle Amount is Less than Total Amount<br>مبلغ التسوية أقل من المبلغ الإجمالي</html>");
					return;
				}
				textField_2.setText(amt);
				textField_12.setText("");
				textField_12.requestFocus();
	        }
	    } );
	}
	public static void clickOnsettle(final AbstractButton button, String actionName, int key,final String company,final String receipt_no,final String reg_num,final String cust_name,final String store_name,final String username,final String qty,final String type,final String cust_code,final int decimal, final String version)
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
				getsettled(company, receipt_no, reg_num, cust_name, store_name, username, qty, type, cust_code, decimal,version);
	        }
	    } );
	}
	
	
	
	private static void getsettled(String company,String receipt_no,String reg_num,String cust_name,String store_name,String username,String qty,String type,String cust_code,int decimal, String version)
	{
		if(Double.parseDouble(textField_7.getText())<=0.00)
		{
			boolean btns=false;
			if(textField_1.getText().equals(""))
			{
				btns=true;
			}
			else
			{
				if(Double.parseDouble(textField_28.getText())>Double.parseDouble(textField_1.getText()))
				{
					
					btns=true;
				}
				else
				{
					JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>Do Not Have Balance To Purchase This Invoice<br>ليس لديك الرصيد لشراء هذه الفاتورة</html>");
				}
			}
			if(btns==true)
			{
				String btn1="CASH";
				String btn2="A/R";
				String btn3="VISA";
				//String btn4=btnMastercard.getText();
				String btn5=button_1.getText();
				String btn6=button_2.getText();
				String btn7=button_3.getText();
				String btn8=button_4.getText();
				String btn9=button_5.getText();
				String btn10=button_6.getText();
				String btn11=button_7.getText();
				String btn12=button_8.getText();
				String btn13=button_9.getText();
				String btn14=button_10.getText();
				String btn15=button_11.getText();
				String StoreCredit=btnStoreCredit.getText();
				
				String txt1=textField.getText();
				String txt2=textField_1.getText();
				String txt3=textField_2.getText();
				//String txt4=textField_3 .getText();
				String txt5=textField_4.getText();
				String txt6=textField_8.getText();
				String txt7=textField_6.getText();
				String txt8=textField_5.getText();
				String txt9=textField_16.getText();
				String txt10=textField_9.getText();
				String txt11=textField_15.getText();
				String txt12=textField_10.getText();
				String txt13=textField_11.getText();
				String txt14=textField_13.getText();
				String txt15=textField_14.getText();
				String txt29=textField_29.getText();
				
				String discount_amt=textField_33.getText();
				String balance=textField_7.getText();
				
				Connection con=Dbsetup.dbconn.getConnection();
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    final String formattedDate = myDateObj.format(myFormatObj);
			    final Calendar cal = Calendar.getInstance();
		        final SimpleDateFormat times = new SimpleDateFormat("HH:mm:ss");
				Statement statement;
				try {
					statement = con.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_paycode_settlement where ref_receipt_num='"+receipt_no+"' and ref_reg_num='"+reg_num+"' ");
					if(!resultSet.next())
					{
						
						String kotsts="0";
						for(int i=0;i<posscreen.table.getRowCount();i++)
						{
							String kot=posscreen.model.getValueAt(i, 19).toString();
							if(kot.equals("1"))
							{
								kotsts="1";
							}
						}
						
						if(kotsts.equals("0"))
						{
							posscreen.label_2.setText("HOME DELIVERY");
							posscreen.label.setText("HOME DELIVERY");
							//hold_inv(company, username, store_name, posreg,con,"",version,"","",textField_8.getText(),"0");	
							posscreen.Kot_hold_inv(company, username,store_name, reg_num, con,"HOME DELIVERY",version,"","HOME DELIVERY",posscreen.textField_8.getText(),"0","PAYCODE");
						}
						
						
						String paycode_name;
						String paycode_rate;
						float paycode_amt=0;
						double paycode_ttl=0;
						float cashpaid=0;
						if(Mainpage.SoftLang.equals("Ar"))
						{
							if(!txt29.equals(""))
							{
								paycode_name=StoreCredit;
								paycode_rate="1";
								paycode_amt=Float.parseFloat(txt29);
								paycode_ttl=Float.parseFloat(txt29)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(PosscreenArabic.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt1.equals(""))
							{
								paycode_name=btn1;
								paycode_rate="1";
								paycode_amt=Float.parseFloat(txt1);
								paycode_ttl=Float.parseFloat(txt1)*Float.parseFloat(paycode_rate);
								if(Double.parseDouble(balance)<0)
								{
									paycode_ttl=paycode_ttl+Double.parseDouble(balance);
								}
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(PosscreenArabic.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt3.equals(""))
							{
								paycode_name=btn3;
								paycode_rate="1";
								paycode_amt=Float.parseFloat(txt3);
								paycode_ttl=Float.parseFloat(txt3)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(PosscreenArabic.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt5.equals(""))
							{
								String[] name=btn5.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt5);
								paycode_ttl=Float.parseFloat(txt5)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(PosscreenArabic.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt6.equals(""))
							{
								String[] name=btn6.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt6);
								paycode_ttl=Float.parseFloat(txt6)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(PosscreenArabic.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt7.equals(""))
							{
								String[] name=btn7.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt7);
								paycode_ttl=Float.parseFloat(txt7)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(PosscreenArabic.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt8.equals(""))
							{
								String[] name=btn8.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt8);
								paycode_ttl=Float.parseFloat(txt8)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(PosscreenArabic.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt9.equals(""))
							{
								String[] name=btn9.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt9);
								paycode_ttl=Float.parseFloat(txt9)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(PosscreenArabic.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt10.equals(""))
							{
								String[] name=btn10.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt10);
								paycode_ttl=Float.parseFloat(txt10)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(PosscreenArabic.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt11.equals(""))
							{
								String[] name=btn11.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt11);
								paycode_ttl=Float.parseFloat(txt11)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(PosscreenArabic.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt12.equals(""))
							{
								String[] name=btn12.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt12);
								paycode_ttl=Float.parseFloat(txt12)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(PosscreenArabic.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt13.equals(""))
							{
								String[] name=btn13.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt13);
								paycode_ttl=Float.parseFloat(txt13)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(PosscreenArabic.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt14.equals(""))
							{
								String[] name=btn14.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt14);
								paycode_ttl=Float.parseFloat(txt14)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(PosscreenArabic.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt15.equals(""))
							{
								String[] name=btn15.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt15);
								paycode_ttl=Float.parseFloat(txt15)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(PosscreenArabic.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							

							if(!txt2.equals(""))
							{
								paycode_name=btn2;
								paycode_rate="1";
								paycode_amt=Float.parseFloat(txt2);
								paycode_ttl=Float.parseFloat(txt2)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"') ";
									statement.executeUpdate(query);
									float bal_amt=0;
									float avail_crdt=0;
									float adv_amt=0;
									ResultSet resultSet11 = statement.executeQuery("select * from "+company+".mr_customer_master where cust_code='"+cust_code+"' ");
									if(resultSet11.next())
									{
										
										if(!resultSet11.getString("advance_amt").equals("0"))
										{
											bal_amt=Float.parseFloat(resultSet11.getString("advance_amt"))-Float.parseFloat(txt2);
											if(bal_amt>=0)
											{
												adv_amt=bal_amt;
												avail_crdt=Float.parseFloat(resultSet11.getString("avail_credit"));
											}
											else{
												avail_crdt=bal_amt+Float.parseFloat(resultSet11.getString("avail_credit"));
											}
										}
										else{
											avail_crdt=Float.parseFloat(resultSet11.getString("avail_credit"))-Float.parseFloat(txt2);
										}
									}
									String update_customer_master="update "+company+".mr_customer_master set avail_credit='"+avail_crdt+"',advance_amt='"+adv_amt+"',last_sale_date='"+formattedDate+"' where cust_code='"+cust_code+"' ";
									statement.executeUpdate(update_customer_master);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							
							if(!textField_30.getText().equals(""))
							{
								String[] split_txt=textField_30.getText().split(",");
								for(int y=0;y<split_txt.length;y++)
								{
									if(!split_txt[y].equals(""))
									{
										String[] creditlist=split_txt[y].split("@");
										String query12="update "+company+".mr_store_credit set `modified_date`='"+formattedDate+"', `status`='1', `used_date`='"+formattedDate+"', `used_receipt`='"+PosscreenArabic.textField_8.getText()+"', `used_reg_num`='"+PosscreenArabic.current_register+"', `used_store`='"+store_name+"' where ref_invoice_num='"+creditlist[0]+"' and register='"+creditlist[1]+"' ";
										statement.executeUpdate(query12);
									}
									
								}
							}
							
							
							ResultSet resultSet11 = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ");
							if(!resultSet11.next())
							{
								if(discount_amt.equals(""))
								{
									discount_amt="0";
								}
								double vat=Double.parseDouble(textField_35.getText());
								double subtotal=Double.parseDouble(textField_3.getText());
								
								
								String table_num=PosscreenArabic.label.getText();
								String delivery_user=PosscreenArabic.label_1.getText();
								String mode=PosscreenArabic.label_2.getText();
								if(PosscreenArabic.change_hold_type.equals("1"))
								{
									if(table_num.equals(""))
									{
										table_num="PAYCODE SALE";
									}
									
									if(delivery_user.equals(""))
									{
										delivery_user="PAYCODE SALE";
									}
									
									if(mode.equals(""))
									{
										mode="PAYCODE SALE";
									}
								}
								
								if(cust_code.equals("WALK IN"))
								{
									String query11="insert into "+company+".mr_pos_settlement_cash (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, type,store_id, `created_date`,cash_paid,paycode_disc,grand_total,cust_code,cust_prev_balance,table_num,delivery_user,mode,cust_ph) values('"+reg_num+"','"+receipt_no+"','"+username+"','"+times.format(cal.getTime())+"','"+PosscreenArabic.df2.format(subtotal)+"','"+cust_name+"','"+PosscreenArabic.df2.format(vat)+"','"+discount_amt+"','"+qty+"','"+type+"','"+store_name+"','"+formattedDate+"','"+cashpaid+"','"+Double.parseDouble(textField_7.getText())+"','"+textField_36.getText()+"','"+cust_code+"','0','"+table_num+"','"+delivery_user+"','"+mode+"','"+PosscreenArabic.textField_12.getText()+"') ";
									statement.executeUpdate(query11);
								}
								else
								{
									String cust_prev_balance=PosscreenArabic.textField_6.getText();
									if(cust_prev_balance.equals(""))
									{
										cust_prev_balance="0";
									}
									String query11="insert into "+company+".mr_pos_settlement_cash (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, type,store_id, `created_date`,cash_paid,paycode_disc,grand_total,cust_code,cust_prev_balance,table_num,delivery_user,mode,cust_ph) values('"+reg_num+"','"+receipt_no+"','"+username+"','"+times.format(cal.getTime())+"','"+PosscreenArabic.df2.format(subtotal)+"','"+cust_name+"','"+PosscreenArabic.df2.format(vat)+"','"+discount_amt+"','"+qty+"','"+type+"','"+store_name+"','"+formattedDate+"','"+cashpaid+"','"+Double.parseDouble(textField_7.getText())+"','"+textField_36.getText()+"','"+cust_code+"','"+cust_prev_balance+"','"+table_num+"','"+delivery_user+"','"+mode+"','"+PosscreenArabic.textField_12.getText()+"') ";
									statement.executeUpdate(query11);
								}
								
								
								if(PosscreenArabic.old_recipt.equals(""))
								{
									ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_num+"' ");
									if(resultSet2.next())
									{
										int nxt_num=Integer.parseInt(resultSet2.getString("next_num"))+1;
										String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+reg_num+"' ";
										statement.executeUpdate(update_receipt_num);
									}
								}
								
								con.setAutoCommit(false);
								PreparedStatement ps = con.prepareStatement("insert into "+company+".mr_pos_settlement_cash_items ( `ref_receipt_num`,ref_reg_num, `barcode`, `item_desc`,item_code,item_cost, `unit`, `unit_price`, `qty`, `total`, `vat`,discount, `created_date`,sp, `vat_type`, `vat_perc`, `disc_type`,cf) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
								for(int i=0;i<PosscreenArabic.table.getRowCount();i++)
								{
									ps.setString(1,receipt_no);
									ps.setString(2,reg_num);
									ps.setString(3,PosscreenArabic.model.getValueAt(i, 1).toString().trim());
									ps.setString(4,PosscreenArabic.model.getValueAt(i, 2).toString().trim().replaceAll("<html>", "").replaceAll("</html>", ""));
									ps.setString(5,PosscreenArabic.model.getValueAt(i, 13).toString().trim());
									ps.setString(6,PosscreenArabic.model.getValueAt(i, 11).toString().trim());
									ps.setString(7,PosscreenArabic.model.getValueAt(i, 3).toString().trim());
									ps.setString(8,PosscreenArabic.model.getValueAt(i,4).toString().trim());
									ps.setString(9,PosscreenArabic.model.getValueAt(i, 5).toString().trim());
									ps.setString(10,PosscreenArabic.model.getValueAt(i, 7).toString().trim());
									ps.setString(11,PosscreenArabic.model.getValueAt(i, 8).toString().trim());
									ps.setString(12,PosscreenArabic.model.getValueAt(i, 6).toString().trim());
									ps.setString(13,formattedDate);
									ps.setString(14,PosscreenArabic.model.getValueAt(i, 12).toString().trim());
									ps.setString(15,PosscreenArabic.model.getValueAt(i, 10).toString().trim());
									ps.setString(16,PosscreenArabic.model.getValueAt(i, 16).toString().trim());
									ps.setString(17,PosscreenArabic.model.getValueAt(i, 15).toString().trim());
									ps.setString(18,PosscreenArabic.model.getValueAt(i, 22).toString().trim());
									ps.addBatch();
									ps.clearParameters();
								}
								ps.executeBatch();
								con.commit();
								con.setAutoCommit(true);
								
								String query5="SELECT * FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ";
								ResultSet resultSet5= statement.executeQuery(query5);
								if(resultSet5.next())
								{
									String query6="delete FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ";
									statement.executeUpdate(query6);
									String query7="delete FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+receipt_no+"' and ref_reg_no='"+reg_num+"' ";
									statement.executeUpdate(query7);
								}
								int copy=1;
								if(PosscreenArabic.print_duplicate_invoice.equals("1"))
								{
									copy=2;
								}
								
								
								if(PosscreenArabic.promt_print.equals("0"))
								{
									//byte[] open = {27,112,48,55,121};
							        PrintService pservice = 
							        PrintServiceLookup.lookupDefaultPrintService(); 
							        DocPrintJob job = pservice.createPrintJob();
							        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
							        Doc doc = new SimpleDoc(PosscreenArabic.open,flavor,null);
							        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
							        try {
							            job.print(doc, aset);
										
							        } catch (PrintException ex) {
							            System.out.println(ex.getMessage());
							        }
									
									report_master.Bill_template.report(receipt_no, con,PosscreenArabic.printer,PosscreenArabic.current_register,"",company,copy);
								}
								else
								{
									int a=JOptionPane.showConfirmDialog(frmMultiplePayCode, "<html>DO YOU WANT TO PRINT RECEIPT<br>هل تريد طباعة الإيصال</html>","",JOptionPane.YES_NO_OPTION);
									if(a==0)
									{
										//byte[] open = {27,112,48,55,121};
								        PrintService pservice = 
								        PrintServiceLookup.lookupDefaultPrintService(); 
								        DocPrintJob job = pservice.createPrintJob();
								        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
								        Doc doc = new SimpleDoc(PosscreenArabic.open,flavor,null);
								        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
								        try {
								            job.print(doc, aset);
											
								        } catch (PrintException ex) {
								            System.out.println(ex.getMessage());
								        }
										report_master.Bill_template.report(receipt_no, con, PosscreenArabic.printer,PosscreenArabic.current_register,"",company,copy);
									}
									else
									{
										//byte[] open = {27,112,48,55,121};
								        PrintService pservice = 
								        PrintServiceLookup.lookupDefaultPrintService(); 
								        DocPrintJob job = pservice.createPrintJob();
								        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
								        Doc doc = new SimpleDoc(PosscreenArabic.open,flavor,null);
								        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
								        try {
								            job.print(doc, aset);
											PosscreenArabic.get_user_validate_dt( company, username,store_name,reg_num);
								            
								        } catch (PrintException ex) {
								            System.out.println(ex.getMessage());
								        }
									}
								}
								
								if(PosscreenArabic.CashChange.equals("1"))
								{
									new sales_master.cash_change(company, username, store_name, textField_36.getText(), textField_19.getText(), decimal,"CASH");
								}
								sales_master.PosscreenArabic.current_register=PosscreenArabic.reg_nme;
								sales_master.PosscreenArabic.get_register_name(company,  username, store_name,con,PosscreenArabic.reg_nme);
								sales_master.PosscreenArabic.current_register=PosscreenArabic.reg_nme;
								sales_master.PosscreenArabic.txtDisc.setText("");
								sales_master.PosscreenArabic.btnNewButton.setText("WALK IN");

								sales_master.PosscreenArabic.textField_9.setText("1");
								sales_master.PosscreenArabic.textField_12.setText("0");
								sales_master.PosscreenArabic.textField_6.setText("");
								sales_master.PosscreenArabic.textField_7.setText("0");
								sales_master.PosscreenArabic.lblNewLabel_2.setText("0%");
								sales_master.PosscreenArabic.textField_4.setText("0");
								sales_master.PosscreenArabic.textField_2.setText("0");
								sales_master.PosscreenArabic.textField.setText("0.00");
								sales_master.PosscreenArabic.textField_1.setText("0.00");
								sales_master.PosscreenArabic.textField_11.setText("0.00");
								sales_master.PosscreenArabic.textField_3.setText("0.00");
								sales_master.PosscreenArabic.textField_5.setText("");
								sales_master.PosscreenArabic.textField_7.setText("0");
								sales_master.PosscreenArabic.lblNewLabel_2.setText("0");
								sales_master.PosscreenArabic.lblNewLabel_1.setText("0");
								sales_master.PosscreenArabic.label.setText("");
								sales_master.PosscreenArabic.label_1.setText("");
								sales_master.PosscreenArabic.label_2.setText("");
								sales_master.PosscreenArabic.checkbillstatus="0";
								sales_master.PosscreenArabic.model.setRowCount(0);
								
								if(PosscreenArabic.gs.length>1)
								{
									sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
									sales_master.SecondDisplay.textField_7.setText("0");
									sales_master.SecondDisplay.textField_4.setText("0");
									sales_master.SecondDisplay.textField_2.setText("0");
									sales_master.SecondDisplay.textField.setText("0.00");
									sales_master.SecondDisplay.textField_1.setText("0.00");
									sales_master.SecondDisplay.textField_11.setText("0.00");
									sales_master.SecondDisplay.textField_3.setText("0.00");
									sales_master.SecondDisplay.textField_5.setText("");
									sales_master.SecondDisplay.textField_7.setText("0");
									sales_master.SecondDisplay.model.setRowCount(0);
								}
								
								//sales_master.PosscreenArabic.txtDisc.requestFocus();
							}
							else
							{
								JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>RECEIPT NUMBER ALREADY EXIST<br>رقم الإيصال موجود بالفعل</html>");
							}
							PosscreenArabic.btnPayCode.setEnabled(true);
						}
						else
						{
							if(!txt29.equals(""))
							{
								paycode_name=StoreCredit;
								paycode_rate="1";
								paycode_amt=Float.parseFloat(txt29);
								paycode_ttl=Float.parseFloat(txt29)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(posscreen.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt1.equals(""))
							{
								paycode_name=btn1;
								paycode_rate="1";
								paycode_amt=Float.parseFloat(txt1);
								paycode_ttl=Float.parseFloat(txt1)*Float.parseFloat(paycode_rate);
								if(Double.parseDouble(balance)<0)
								{
									paycode_ttl=paycode_ttl+Double.parseDouble(balance);
								}
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(posscreen.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt3.equals(""))
							{
								paycode_name=btn3;
								paycode_rate="1";
								paycode_amt=Float.parseFloat(txt3);
								paycode_ttl=Float.parseFloat(txt3)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(posscreen.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt5.equals(""))
							{
								String[] name=btn5.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt5);
								paycode_ttl=Float.parseFloat(txt5)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(posscreen.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt6.equals(""))
							{
								String[] name=btn6.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt6);
								paycode_ttl=Float.parseFloat(txt6)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(posscreen.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt7.equals(""))
							{
								String[] name=btn7.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt7);
								paycode_ttl=Float.parseFloat(txt7)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(posscreen.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt8.equals(""))
							{
								String[] name=btn8.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt8);
								paycode_ttl=Float.parseFloat(txt8)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(posscreen.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt9.equals(""))
							{
								String[] name=btn9.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt9);
								paycode_ttl=Float.parseFloat(txt9)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(posscreen.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt10.equals(""))
							{
								String[] name=btn10.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt10);
								paycode_ttl=Float.parseFloat(txt10)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(posscreen.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt11.equals(""))
							{
								String[] name=btn11.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt11);
								paycode_ttl=Float.parseFloat(txt11)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(posscreen.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt12.equals(""))
							{
								String[] name=btn12.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt12);
								paycode_ttl=Float.parseFloat(txt12)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(posscreen.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt13.equals(""))
							{
								String[] name=btn13.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt13);
								paycode_ttl=Float.parseFloat(txt13)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(posscreen.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt14.equals(""))
							{
								String[] name=btn14.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt14);
								paycode_ttl=Float.parseFloat(txt14)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(posscreen.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							if(!txt15.equals(""))
							{
								String[] name=btn15.split("@");
								paycode_name=name[0];
								paycode_rate=name[1];
								paycode_amt=Float.parseFloat(txt15);
								paycode_ttl=Float.parseFloat(txt15)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									double pay_code_vat=0;
									if(Double.parseDouble(posscreen.textField_1.getText())==0)
									{
										pay_code_vat=0;
									}
									else
									{
										pay_code_vat=(paycode_ttl*5)/105;
									}
									
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`,pay_code_vat) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"','"+pay_code_vat+"') ";
									statement.executeUpdate(query);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							

							if(!txt2.equals(""))
							{
								paycode_name=btn2;
								paycode_rate="1";
								paycode_amt=Float.parseFloat(txt2);
								paycode_ttl=Float.parseFloat(txt2)*Float.parseFloat(paycode_rate);
								if(paycode_amt!=0)
								{
									cashpaid+=paycode_ttl;
									String query="insert into "+company+".mr_paycode_settlement (`ref_reg_num`, `ref_receipt_num`, `paycode_name`, `paycode_rate`, `paycode_amt`, `paid_amt`, `created_date`) values('"+reg_num+"','"+receipt_no+"','"+paycode_name+"','"+paycode_rate+"','"+paycode_amt+"','"+paycode_ttl+"','"+formattedDate+"') ";
									statement.executeUpdate(query);
									float bal_amt=0;
									float avail_crdt=0;
									float adv_amt=0;
									ResultSet resultSet11 = statement.executeQuery("select * from "+company+".mr_customer_master where cust_code='"+cust_code+"' ");
									if(resultSet11.next())
									{
										
										if(!resultSet11.getString("advance_amt").equals("0"))
										{
											bal_amt=Float.parseFloat(resultSet11.getString("advance_amt"))-Float.parseFloat(txt2);
											if(bal_amt>=0)
											{
												adv_amt=bal_amt;
												avail_crdt=Float.parseFloat(resultSet11.getString("avail_credit"));
											}
											else{
												avail_crdt=bal_amt+Float.parseFloat(resultSet11.getString("avail_credit"));
											}
										}
										else{
											avail_crdt=Float.parseFloat(resultSet11.getString("avail_credit"))-Float.parseFloat(txt2);
										}
									}
									String update_customer_master="update "+company+".mr_customer_master set avail_credit='"+avail_crdt+"',advance_amt='"+adv_amt+"',last_sale_date='"+formattedDate+"' where cust_code='"+cust_code+"' ";
									statement.executeUpdate(update_customer_master);
								}
								paycode_amt=0;
								paycode_ttl=0;
							}
							
							if(!textField_30.getText().equals(""))
							{
								String[] split_txt=textField_30.getText().split(",");
								for(int y=0;y<split_txt.length;y++)
								{
									if(!split_txt[y].equals(""))
									{
										String[] creditlist=split_txt[y].split("@");
										String query12="update "+company+".mr_store_credit set `modified_date`='"+formattedDate+"', `status`='1', `used_date`='"+formattedDate+"', `used_receipt`='"+posscreen.textField_8.getText()+"', `used_reg_num`='"+posscreen.current_register+"', `used_store`='"+store_name+"' where ref_invoice_num='"+creditlist[0]+"' and register='"+creditlist[1]+"' ";
										statement.executeUpdate(query12);
									}
									
								}
							}
							
							
							ResultSet resultSet11 = statement.executeQuery("select * from "+company+".mr_pos_settlement_cash where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ");
							if(!resultSet11.next())
							{
								if(discount_amt.equals(""))
								{
									discount_amt="0";
								}
								double vat=Double.parseDouble(textField_35.getText());
								double subtotal=Double.parseDouble(textField_3.getText());
								
								
								String table_num=posscreen.label.getText();
								String delivery_user=posscreen.label_1.getText();
								String mode=posscreen.label_2.getText();
								if(posscreen.change_hold_type.equals("1"))
								{
									if(table_num.equals(""))
									{
										table_num="PAYCODE SALE";
									}
									
									if(delivery_user.equals(""))
									{
										delivery_user="PAYCODE SALE";
									}
									
									if(mode.equals(""))
									{
										mode="PAYCODE SALE";
									}
								}
								
								if(cust_code.equals("WALK IN"))
								{
									String query11="insert into "+company+".mr_pos_settlement_cash (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, type,store_id, `created_date`,cash_paid,paycode_disc,grand_total,cust_code,cust_prev_balance,table_num,delivery_user,mode,cust_ph) values('"+reg_num+"','"+receipt_no+"','"+username+"','"+times.format(cal.getTime())+"','"+posscreen.df2.format(subtotal)+"','"+cust_name+"','"+posscreen.df2.format(vat)+"','"+discount_amt+"','"+qty+"','"+type+"','"+store_name+"','"+formattedDate+"','"+cashpaid+"','"+Double.parseDouble(textField_7.getText())+"','"+textField_36.getText()+"','"+cust_code+"','0','"+table_num+"','"+delivery_user+"','"+mode+"','"+posscreen.textField_12.getText()+"') ";
									statement.executeUpdate(query11);
								}
								else
								{
									String cust_prev_balance=posscreen.textField_6.getText();
									if(cust_prev_balance.equals(""))
									{
										cust_prev_balance="0";
									}
									String query11="insert into "+company+".mr_pos_settlement_cash (`reg_num`, `receipt_no`, `user`, `time`, `total`, `cust_name`, `vat`, `disc`, `qty`, type,store_id, `created_date`,cash_paid,paycode_disc,grand_total,cust_code,cust_prev_balance,table_num,delivery_user,mode,cust_ph) values('"+reg_num+"','"+receipt_no+"','"+username+"','"+times.format(cal.getTime())+"','"+posscreen.df2.format(subtotal)+"','"+cust_name+"','"+posscreen.df2.format(vat)+"','"+discount_amt+"','"+qty+"','"+type+"','"+store_name+"','"+formattedDate+"','"+cashpaid+"','"+Double.parseDouble(textField_7.getText())+"','"+textField_36.getText()+"','"+cust_code+"','"+cust_prev_balance+"','"+table_num+"','"+delivery_user+"','"+mode+"','"+posscreen.textField_12.getText()+"') ";
									statement.executeUpdate(query11);
								}
								
								
								if(posscreen.old_recipt.equals(""))
								{
									ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_num+"' ");
									if(resultSet2.next())
									{
										int nxt_num=Integer.parseInt(resultSet2.getString("next_num"))+1;
										String update_receipt_num="update "+company+".mr_register_setup set next_num='"+nxt_num+"'  where register_number='"+reg_num+"' ";
										statement.executeUpdate(update_receipt_num);
									}
								}
								
								con.setAutoCommit(false);
								PreparedStatement ps = con.prepareStatement("insert into "+company+".mr_pos_settlement_cash_items ( `ref_receipt_num`,ref_reg_num, `barcode`, `item_desc`,item_code,item_cost, `unit`, `unit_price`, `qty`, `total`, `vat`,discount, `created_date`,sp, `vat_type`, `vat_perc`, `disc_type`,cf) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
								for(int i=0;i<posscreen.table.getRowCount();i++)
								{
									ps.setString(1,receipt_no);
									ps.setString(2,reg_num);
									ps.setString(3,posscreen.model.getValueAt(i, 1).toString().trim());
									ps.setString(4,posscreen.model.getValueAt(i, 2).toString().trim().replaceAll("<html>", "").replaceAll("</html>", ""));
									ps.setString(5,posscreen.model.getValueAt(i, 13).toString().trim());
									ps.setString(6,posscreen.model.getValueAt(i, 11).toString().trim());
									ps.setString(7,posscreen.model.getValueAt(i, 3).toString().trim());
									ps.setString(8,posscreen.model.getValueAt(i,4).toString().trim());
									ps.setString(9,posscreen.model.getValueAt(i, 5).toString().trim());
									ps.setString(10,posscreen.model.getValueAt(i, 7).toString().trim());
									ps.setString(11,posscreen.model.getValueAt(i, 8).toString().trim());
									ps.setString(12,posscreen.model.getValueAt(i, 6).toString().trim());
									ps.setString(13,formattedDate);
									ps.setString(14,posscreen.model.getValueAt(i, 12).toString().trim());
									ps.setString(15,posscreen.model.getValueAt(i, 10).toString().trim());
									ps.setString(16,posscreen.model.getValueAt(i, 16).toString().trim());
									ps.setString(17,posscreen.model.getValueAt(i, 15).toString().trim());
									ps.setString(18,posscreen.model.getValueAt(i, 22).toString().trim());
									ps.addBatch();
									ps.clearParameters();
								}
								ps.executeBatch();
								con.commit();
								con.setAutoCommit(true);
								
								String query5="SELECT * FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ";
								ResultSet resultSet5= statement.executeQuery(query5);
								if(resultSet5.next())
								{
									String query6="delete FROM "+company+".mr_pos_hold_invoice where receipt_no='"+receipt_no+"' and reg_num='"+reg_num+"' ";
									statement.executeUpdate(query6);
									String query7="delete FROM "+company+".mr_pos_hold_invoice_items where ref_receipt_num='"+receipt_no+"' and ref_reg_no='"+reg_num+"' ";
									statement.executeUpdate(query7);
								}
								int copy=1;
								if(posscreen.print_duplicate_invoice.equals("1"))
								{
									copy=2;
								}
								
								
								if(posscreen.promt_print.equals("0"))
								{
									//byte[] open = {27,112,48,55,121};
							        PrintService pservice = 
							        PrintServiceLookup.lookupDefaultPrintService(); 
							        DocPrintJob job = pservice.createPrintJob();
							        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
							        Doc doc = new SimpleDoc(posscreen.open,flavor,null);
							        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
							        try {
							            job.print(doc, aset);
										
							        } catch (PrintException ex) {
							            System.out.println(ex.getMessage());
							        }
									
									report_master.Bill_template.report(receipt_no, con,posscreen.printer,posscreen.current_register,"",company,copy);
								}
								else
								{
									int a=JOptionPane.showConfirmDialog(frmMultiplePayCode, "<html>DO YOU WANT TO PRINT RECEIPT<br>هل تريد طباعة الإيصال</html>","",JOptionPane.YES_NO_OPTION);
									if(a==0)
									{
										//byte[] open = {27,112,48,55,121};
								        PrintService pservice = 
								        PrintServiceLookup.lookupDefaultPrintService(); 
								        DocPrintJob job = pservice.createPrintJob();
								        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
								        Doc doc = new SimpleDoc(posscreen.open,flavor,null);
								        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
								        try {
								            job.print(doc, aset);
											
								        } catch (PrintException ex) {
								            System.out.println(ex.getMessage());
								        }
										report_master.Bill_template.report(receipt_no, con, posscreen.printer,posscreen.current_register,"",company,copy);
									}
									else
									{
										//byte[] open = {27,112,48,55,121};
								        PrintService pservice = 
								        PrintServiceLookup.lookupDefaultPrintService(); 
								        DocPrintJob job = pservice.createPrintJob();
								        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
								        Doc doc = new SimpleDoc(posscreen.open,flavor,null);
								        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
								        try {
								            job.print(doc, aset);
											posscreen.get_user_validate_dt( company, username,store_name,reg_num);
								            
								        } catch (PrintException ex) {
								            System.out.println(ex.getMessage());
								        }
									}
								}
								
								if(posscreen.CashChange.equals("1"))
								{
									new sales_master.cash_change(company, username, store_name, textField_36.getText(), textField_19.getText(), decimal,"CASH");
								}
								sales_master.posscreen.current_register=posscreen.reg_nme;
								sales_master.posscreen.get_register_name(company,  username, store_name,con,posscreen.reg_nme);
								sales_master.posscreen.current_register=posscreen.reg_nme;
								sales_master.posscreen.txtDisc.setText("");
								sales_master.posscreen.btnNewButton.setText("WALK IN");

								sales_master.posscreen.textField_9.setText("1");
								sales_master.posscreen.textField_12.setText("0");
								sales_master.posscreen.textField_6.setText("");
								sales_master.posscreen.textField_7.setText("0");
								sales_master.posscreen.lblNewLabel_2.setText("0%");
								sales_master.posscreen.textField_4.setText("0");
								sales_master.posscreen.textField_2.setText("0");
								sales_master.posscreen.textField.setText("0.00");
								sales_master.posscreen.textField_1.setText("0.00");
								sales_master.posscreen.textField_11.setText("0.00");
								sales_master.posscreen.textField_3.setText("0.00");
								sales_master.posscreen.textField_5.setText("");
								sales_master.posscreen.textField_7.setText("0");
								sales_master.posscreen.lblNewLabel_2.setText("0");
								sales_master.posscreen.lblNewLabel_1.setText("0");
								sales_master.posscreen.label.setText("");
								sales_master.posscreen.label_1.setText("");
								sales_master.posscreen.label_2.setText("");
								sales_master.posscreen.checkbillstatus="0";
								sales_master.posscreen.model.setRowCount(0);
								
								if(posscreen.gs.length>1)
								{
									sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
									sales_master.SecondDisplay.textField_7.setText("0");
									sales_master.SecondDisplay.textField_4.setText("0");
									sales_master.SecondDisplay.textField_2.setText("0");
									sales_master.SecondDisplay.textField.setText("0.00");
									sales_master.SecondDisplay.textField_1.setText("0.00");
									sales_master.SecondDisplay.textField_11.setText("0.00");
									sales_master.SecondDisplay.textField_3.setText("0.00");
									sales_master.SecondDisplay.textField_5.setText("");
									sales_master.SecondDisplay.textField_7.setText("0");
									sales_master.SecondDisplay.model.setRowCount(0);
								}
								
								//sales_master.posscreen.txtDisc.requestFocus();
							}
							else
							{
								JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>RECEIPT NUMBER ALREADY EXIST<br>رقم الإيصال موجود بالفعل</html>");
							}
							posscreen.btnPayCode.setEnabled(true);
						}
						
						
						frmMultiplePayCode.setVisible(false);
						
					}
					else
					{
						JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>RECEIPT NUMBER ALREADY EXIST<br>رقم الإيصال موجود بالفعل</html>");
					}
					
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
		}
		else
		{
			JOptionPane.showMessageDialog(frmMultiplePayCode, "<html>Balance Amount Must Be Zero<br>يجب أن يكون مبلغ الرصيد صفرًا</html>");
		}
	}
	
	public static void clickOnback(final AbstractButton button, String actionName, int key,final String company,final String store_name,final String version)
	{
	    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW ).put( KeyStroke.getKeyStroke( key, 0 ), actionName );

	    button.getActionMap().put( actionName, new AbstractAction()
	    {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -6694624852420723982L;

			/**
			 * 
			 */
			public void actionPerformed( ActionEvent e )
	        {
				frmMultiplePayCode.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.btnPayCode.setEnabled(true);
					PosscreenArabic.txtDisc.requestFocus();
				}
				else
				{
					posscreen.btnPayCode.setEnabled(true);
					posscreen.txtDisc.requestFocus();
				}
				
	        }
	    } );
	}
	private void get_full_paycode(String company)
	{
		try {
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_store_paycodes");
			int x=1;
			while(resultSet.next())
			{
				String name=resultSet.getString("paycode");
				String ex_rate=resultSet.getString("ex_rate");
				if(!name.equals(""))
				{
					if(!name.equals("CASH"))
					{
						if(!name.equals("A/R"))
						{
							if(!name.equals("VISA"))
							{
								if(!name.equals("MASTER CARD"))
								{
									if(!name.equals("STORE CREDIT"))
									{
										if(x==1)
										{
											button_1.setText(name+" @ "+ex_rate);
											button_1.setEnabled(true);
											textField_4.setEditable(true);
										}
										if(x==2)
										{
											button_2.setText(name+" @ "+ex_rate);
											button_2.setEnabled(true);
											textField_8.setEditable(true);
										}
										if(x==3)
										{
											button_3.setText(name+" @ "+ex_rate);
											button_3.setEnabled(true);
											textField_6.setEditable(true);
										}
										if(x==4)
										{
											button_4.setText(name+" @ "+ex_rate);
											button_4.setEnabled(true);
											textField_5.setEditable(true);
										}
										if(x==5)
										{
											button_5.setText(name+" @ "+ex_rate);
											button_5.setEnabled(true);
											textField_16.setEditable(true);
										}
										if(x==6)
										{
											button_6.setText(name+" @ "+ex_rate);
											button_6.setEnabled(true);
											textField_9.setEditable(true);
										}
										if(x==7)
										{
											button_7.setText(name+" @ "+ex_rate);
											button_7.setEnabled(true);
											textField_15.setEditable(true);
										}
										if(x==8)
										{
											button_8.setText(name+" @ "+ex_rate);
											button_8.setEnabled(true);
											textField_10.setEditable(true);
										}
										if(x==9)
										{
											button_9.setText(name+" @ "+ex_rate);
											button_9.setEnabled(true);
											textField_11.setEditable(true);
										}
										if(x==10)
										{
											button_10.setText(name+" @ "+ex_rate);
											button_10.setEnabled(true);
											textField_13.setEditable(true);
										}
										if(x==11)
										{
											button_11.setText(name+" @ "+ex_rate);
											button_10.setEnabled(true);
											textField_14.setEditable(true);
										}
										x++;
										
									}
								}
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
	private void click_btn(String num)
	{
		try{
			String pass_string=textField_12.getText();
			pass_string+=num;
			textField_12.setText(pass_string);
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	public void get_register_name(String company,String username, String store_name, String reg_num){
		try {
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet2 = statement.executeQuery("select * from "+company+".mr_register_setup where register_number='"+reg_num+"' ");
			if(resultSet2.next())
			{
				printer=resultSet2.getString("printer1");
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	private void get_custmer_balance(String company,String store_name,  String cust_code){
		try {
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement(); 
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_customer_master where cust_code='"+cust_code+"' and cust_name!='WALK IN' ");
			if(resultSet.next())
			{
				float bal= Float.parseFloat(resultSet.getString("avail_credit"))+Float.parseFloat(resultSet.getString("advance_amt"));
				textField_28.setText(Float.toString(bal));
				textField_1.setEditable(true);
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
