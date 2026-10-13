package sales_master;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import CafePro.Mainpage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;

public class cash_change {

	public static JFrame frmCashTenderedAnd;
	private JTextField textField;
	private JTextField textField_1;
	private static DecimalFormat df2;
	private static int decimalPlaces;
	public static String Active="0";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					cash_change window = new cash_change("mr_pro","admin","S01","15.15","62", 0,"");
					window.frmCashTenderedAnd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(frmCashTenderedAnd, e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param cash 
	 * @param total 
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param decimalPlaces2 
	 * @throws IOException 
	 */
	public cash_change(String company, String username, String store_name, String total, String cash, int decimalPlaces2,String type) throws IOException {
		initialize(company,username,store_name,total,cash,decimalPlaces2,type);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param cash 
	 * @param total 
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @throws IOException 
	 */
	private void initialize(final String company,final String username,final String store_name, String total, String cash,int decimalPlaces2,String type) throws IOException {
		//Runtime.getRuntime().exec(new String[] {"cmd","/c","cls>"+posscreen.display_port+"\""});
		frmCashTenderedAnd = new JFrame();
		frmCashTenderedAnd.getContentPane().setBackground(Color.DARK_GRAY);
		frmCashTenderedAnd.setTitle("CASH TENDERED AND CHANGE GIVEN");
		frmCashTenderedAnd.setBounds(100, 100, 655, 293);
		frmCashTenderedAnd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmCashTenderedAnd.getContentPane().setLayout(null);
		frmCashTenderedAnd.setUndecorated(true);
		frmCashTenderedAnd.setVisible(true);
		Active="1";
		frmCashTenderedAnd.setResizable(false);
		frmCashTenderedAnd.setAlwaysOnTop(true);
		frmCashTenderedAnd.setLocationRelativeTo(null);
		decimalPlaces=decimalPlaces2;
		String gap="";
		if(decimalPlaces==2)
		{
			df2=new DecimalFormat("0.00");
		}
		else
		{
			df2=new DecimalFormat("0.000");
		}
		String head="";
		String head2="";
		double data1=0;
		double data2=0;
		double balance=Double.parseDouble(total)-Double.parseDouble(cash);
		if(type.equals("AR"))
		{
			String grnd_ttl=df2.format(balance);
			int grand_price=7-grnd_ttl.length();
			for(int i=0; i<grand_price;i++)
			{
				gap+=" ";
			}
			head="<html>CREDIT AMOUNT :<br>مبلغ الائتمان </html>";
			head2="<html>BALANCE :<br>توازن</html>";
			data1=balance;
			Runtime.getRuntime().exec(new String[] {"cmd","/c","CLS>"+posscreen.display_port+"&&ECHO CREDIT AMOUNT"+gap+""+balance+">"+posscreen.display_port+"\""});
		}
		else
		{
			data1=Double.parseDouble(cash);
			String grnd_ttl=cash;
			int grand_price=7-grnd_ttl.length();
			for(int i=0; i<grand_price;i++)
			{
				gap+=" ";
			}
			head="<html>AMOUNT RECEIVED :<br> تم استلام المبلغ</html>";
			head2="<html>CHANGE :<br>يتغير</html>";
			data2=balance;
			//Runtime.getRuntime().exec("cmd /c \"CLS>"+posscreen.display_port+"&&ECHO "+posscreen.ready_msg1+">"+posscreen.display_port+"&&ECHO "+posscreen.ready_msg2+">"+posscreen.display_port+"\"");
			Runtime.getRuntime().exec(new String[] {"cmd","/c","CLS>"+posscreen.display_port+"&&ECHO CASH TENDERED"+gap+""+grnd_ttl+"CHANGE "+  balance+">"+posscreen.display_port+"\""});
		}
		
		
		
		
		JLabel lblNewLabel = new JLabel(head);
		lblNewLabel.setForeground(new Color(127, 255, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(55, 40, 262, 47);
		frmCashTenderedAnd.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setFont(new Font("Arial", Font.BOLD, 50));
		textField.setText(df2.format(data1));
		textField.setForeground(new Color(100, 149, 237));
		textField.setBounds(329, 40, 250, 50);
		frmCashTenderedAnd.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblChange = new JLabel(head2);
		lblChange.setForeground(new Color(255, 0, 0));
		lblChange.setFont(new Font("Arial", Font.BOLD, 25));
		lblChange.setBounds(55, 100, 262, 47);
		frmCashTenderedAnd.getContentPane().add(lblChange);
		
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setFont(new Font("Arial", Font.BOLD, 50));
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setText(df2.format(data2));
		textField_1.setColumns(10);
		textField_1.setBounds(329, 100, 250, 50);
		frmCashTenderedAnd.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					ok_button_action(company,username,store_name);
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ok_button_action(company,username,store_name);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton.setForeground(new Color(100, 149, 237));
		btnNewButton.setBackground(new Color(255, 192, 203));
		btnNewButton.setBounds(245, 182, 121, 51);
		frmCashTenderedAnd.getContentPane().add(btnNewButton);
		btnNewButton.requestFocus();
	}
	private void ok_button_action(String company,String username,String store_name)
	{
		frmCashTenderedAnd.setVisible(false);
		Connection con=Dbsetup.local_server.getConnection();
		if(Mainpage.SoftLang.equals("Ar"))
		{
			sales_master.PosscreenArabic.get_register_name(company, username, store_name,con,PosscreenArabic.reg_nme);
			sales_master.PosscreenArabic.txtDisc.setText("");
			sales_master.PosscreenArabic.btnNewButton.setText("WALK IN");
			sales_master.PosscreenArabic.textField_6.setText("");
			
			sales_master.PosscreenArabic.textField_4.setText("0");
			sales_master.PosscreenArabic.textField_2.setText("0");
			sales_master.PosscreenArabic.textField.setText("0.00");
			sales_master.PosscreenArabic.textField_1.setText("0.00");
			sales_master.PosscreenArabic.textField_3.setText("0.00");
			sales_master.PosscreenArabic.textField_5.setText("");
			sales_master.PosscreenArabic.textField_7.setText("0");
			sales_master.PosscreenArabic.model.setRowCount(0);
			if(PosscreenArabic.gs.length>1)
			{
				sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
				sales_master.SecondDisplay.textField_4.setText("0");
				sales_master.SecondDisplay.textField_2.setText("0");
				sales_master.SecondDisplay.textField.setText("0.00");
				sales_master.SecondDisplay.textField_1.setText("0.00");
				sales_master.SecondDisplay.textField_3.setText("0.00");
				sales_master.SecondDisplay.textField_5.setText("");
				sales_master.SecondDisplay.textField_7.setText("0");
				sales_master.SecondDisplay.lblNewLabel_1.setText("0");
			}
			sales_master.PosscreenArabic.current_register=PosscreenArabic.reg_nme;
			sales_master.PosscreenArabic.txtDisc.requestFocus();
			Active="0";
			try {
				Runtime.getRuntime().exec("cmd /c \"CLS>"+PosscreenArabic.display_port+"&&ECHO "+PosscreenArabic.ready_msg1+">"+PosscreenArabic.display_port+"&&ECHO "+PosscreenArabic.ready_msg2+">"+PosscreenArabic.display_port+"\"");
				//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO "+posscreen.ready_msg1+""+posscreen.ready_msg2+">"+posscreen.display_port+"\""});
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(frmCashTenderedAnd, e1.getMessage());
			}
		}
		else
		{
			sales_master.posscreen.get_register_name(company, username, store_name,con,posscreen.reg_nme);
			sales_master.posscreen.txtDisc.setText("");
			sales_master.posscreen.btnNewButton.setText("WALK IN");
			sales_master.posscreen.textField_6.setText("");
			
			sales_master.posscreen.textField_4.setText("0");
			sales_master.posscreen.textField_2.setText("0");
			sales_master.posscreen.textField.setText("0.00");
			sales_master.posscreen.textField_1.setText("0.00");
			sales_master.posscreen.textField_3.setText("0.00");
			sales_master.posscreen.textField_5.setText("");
			sales_master.posscreen.textField_7.setText("0");
			sales_master.posscreen.model.setRowCount(0);
			if(posscreen.gs.length>1)
			{
			sales_master.SecondDisplay.btnNewButton.setText("WALK IN");
			sales_master.SecondDisplay.textField_4.setText("0");
			sales_master.SecondDisplay.textField_2.setText("0");
			sales_master.SecondDisplay.textField.setText("0.00");
			sales_master.SecondDisplay.textField_1.setText("0.00");
			sales_master.SecondDisplay.textField_3.setText("0.00");
			sales_master.SecondDisplay.textField_5.setText("");
			sales_master.SecondDisplay.textField_7.setText("0");
			sales_master.SecondDisplay.lblNewLabel_1.setText("0");
			}
			sales_master.posscreen.current_register=posscreen.reg_nme;
			sales_master.posscreen.txtDisc.requestFocus();
			Active="0";
			try {
				Runtime.getRuntime().exec("cmd /c \"CLS>"+posscreen.display_port+"&&ECHO "+posscreen.ready_msg1+">"+posscreen.display_port+"&&ECHO "+posscreen.ready_msg2+">"+posscreen.display_port+"\"");
				//Runtime.getRuntime().exec(new String[] {"cmd","/c","ECHO "+posscreen.ready_msg1+""+posscreen.ready_msg2+">"+posscreen.display_port+"\""});
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(frmCashTenderedAnd, e1.getMessage());
			}
		}
		
		
		
	}
}
