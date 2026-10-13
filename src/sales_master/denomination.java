package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import CafePro.Mainpage;

import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;

public class denomination {

	static JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JSpinner spinner_2;
	private JSpinner spinner_3;
	private JSpinner spinner_4;
	private JSpinner spinner_5;
	private JSpinner spinner_6;
	private JSpinner spinner_7;
	private JSpinner spinner_8;
	private JSpinner spinner_9;
	private JSpinner spinner_10;
	
	double total_amount=0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					denomination window = new denomination("mr_pro","S0101","2");
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
	public denomination(String company, String reg_nme, String version) {
		initialize(company,reg_nme,version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store_name 
	 * @param host_name 
	 * @param company 
	 * @param version 
	 */
	private void initialize(final String company,final String reg_nme, String version) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 516, 566);
		//frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				Mainpage.btnCloseSales.setEnabled(true);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 510, 682);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(397, 11, 1, 2);
		panel.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 77, 776, 2);
		panel.add(separator_2);
			
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();	
		
		JLabel lblCloseSales = new JLabel("Denomination Screen");
		lblCloseSales.setFont(new Font("Monospaced", Font.BOLD, 25));
		lblCloseSales.setBounds(12, 11, 292, 43);
		panel.add(lblCloseSales);
		
		JLabel lblDenomination = new JLabel("Denomination");
		lblDenomination.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDenomination.setBounds(62, 100, 120, 25);
		panel.add(lblDenomination);
		
		JMenuItem menuItem = new JMenuItem("Save");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					LocalDateTime myDateObj = LocalDateTime.now();  
				    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				    String formattedDate = myDateObj.format(myFormatObj);  
				    Connection con=Dbsetup.dbconn.getConnection();
					Statement statement=con.createStatement();  
					
					String l1=label.getText().trim();
					String l2=label_1.getText().trim();
					String l3=label_2.getText().trim();
					String l4=label_3.getText().trim();
					String l5=label_4.getText().trim();
					String l6=label_5.getText().trim();
					String l7=label_6.getText().trim();
					String l8=label_7.getText().trim();
					String l9=label_8.getText().trim();
					String l10=label_9.getText().trim();
					String l11=label_10.getText().trim();
					
					int s1=(Integer) spinner.getValue();
					int s2=(Integer) spinner_1.getValue();
					int s3=(Integer) spinner_2.getValue();
					int s4=(Integer) spinner_3.getValue();
					int s5=(Integer) spinner_4.getValue();
					int s6=(Integer) spinner_5.getValue();
					int s7=(Integer) spinner_6.getValue();
					int s8=(Integer) spinner_7.getValue();
					int s9=(Integer) spinner_8.getValue();
					int s10=(Integer) spinner_9.getValue();
					int s11=(Integer) spinner_10.getValue();
					
					String t1=textField.getText().trim();
					String t2=textField_1.getText().trim();
					String t3=textField_2.getText().trim();
					String t4=textField_3.getText().trim();
					String t5=textField_4.getText().trim();
					String t6=textField_5.getText().trim();
					String t7=textField_6.getText().trim();
					String t8=textField_7.getText().trim();
					String t9=textField_8.getText().trim();
					String t10=textField_9.getText().trim();
					String t11=textField_10.getText().trim();
					
					String select="select * from "+company+".mr_denomination where ref_reg_num='"+reg_nme+"' and status='0' ";
					ResultSet resultSet = statement.executeQuery(select);
					if(resultSet.next())
					{
						String delete="delete from "+company+".mr_denomination where ref_reg_num='"+reg_nme+"' and status='0' ";
						statement.execute(delete);
					}
					String query="INSERT INTO "+company+".mr_denomination( `ref_reg_num`, `denomination`, `count`, `total_amt`, `created_date`, `modified_date`) VALUES"
							+ "('"+reg_nme+"','"+l1+"','"+s1+"' ,'"+t1+"','"+formattedDate+"','"+formattedDate+"'),"
							+ "('"+reg_nme+"','"+l2+"','"+s2+"' ,'"+t2+"','"+formattedDate+"','"+formattedDate+"'),"
							+ "('"+reg_nme+"','"+l3+"','"+s3+"' ,'"+t3+"','"+formattedDate+"','"+formattedDate+"'),"
							+ "('"+reg_nme+"','"+l4+"','"+s4+"' ,'"+t4+"','"+formattedDate+"','"+formattedDate+"'),"
							+ "('"+reg_nme+"','"+l5+"','"+s5+"' ,'"+t5+"','"+formattedDate+"','"+formattedDate+"'),"
							+ "('"+reg_nme+"','"+l6+"','"+s6+"' ,'"+t6+"','"+formattedDate+"','"+formattedDate+"'),"
							+ "('"+reg_nme+"','"+l7+"','"+s7+"' ,'"+t7+"','"+formattedDate+"','"+formattedDate+"'),"
							+ "('"+reg_nme+"','"+l8+"','"+s8+"' ,'"+t8+"','"+formattedDate+"','"+formattedDate+"'),"
							+ "('"+reg_nme+"','"+l9+"','"+s9+"' ,'"+t9+"','"+formattedDate+"','"+formattedDate+"'),"
							+ "('"+reg_nme+"','"+l10+"','"+s10+"' ,'"+t10+"','"+formattedDate+"','"+formattedDate+"'),"
							+ "('"+reg_nme+"','"+l11+"','"+s11+"' ,'"+t11+"','"+formattedDate+"','"+formattedDate+"') ";
					statement.execute(query);
					
					JOptionPane.showMessageDialog(frame, "Saved Successfully");
					frame.setVisible(false);
					sales_master.Close_sales.model.setValueAt(Double.parseDouble(textField_11.getText()), 0, 3);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(frame, e.getMessage());
				}
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(424, 24, 129, 30);
		menuItem.setIcon(new ImageIcon(save));
		panel.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Exit");
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(354, 24, 60, 30);
		menuItem_1.setIcon(new ImageIcon(exit));
		panel.add(menuItem_1);
		
		JLabel lblCount = new JLabel("Count");
		lblCount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCount.setBounds(244, 100, 60, 25);
		panel.add(lblCount);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal.setBounds(366, 100, 80, 25);
		panel.add(lblTotal);
		
		label = new JLabel("1000");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(62, 139, 120, 20);
		panel.add(label);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String amount=label.getText().trim();
				int value = (Integer) spinner.getValue();
				int total= Integer.parseInt(amount)*value;
				textField.setText(Integer.toString(total));
				total_amount=Double.parseDouble(textField.getText())+Double.parseDouble(textField_1.getText())+Double.parseDouble(textField_2.getText())+Double.parseDouble(textField_3.getText())+Double.parseDouble(textField_4.getText())+Double.parseDouble(textField_5.getText())+Double.parseDouble(textField_6.getText())+Double.parseDouble(textField_7.getText())+Double.parseDouble(textField_8.getText())+Double.parseDouble(textField_9.getText())+Double.parseDouble(textField_10.getText());
				textField_11.setText(Double.toString(total_amount));
			}
		});
		spinner.setBounds(244, 141, 60, 20);
		panel.add(spinner);
		
		textField = new JTextField();
		textField.setText("0");
		textField.setEditable(false);
		textField.setBounds(366, 141, 80, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("500");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(62, 170, 120, 20);
		panel.add(label_1);
		
		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String amount=label_1.getText().trim();
				int value = (Integer) spinner_1.getValue();
				int total= Integer.parseInt(amount)*value;
				textField_1.setText(Integer.toString(total));
				total_amount=Double.parseDouble(textField.getText())+Double.parseDouble(textField_1.getText())+Double.parseDouble(textField_2.getText())+Double.parseDouble(textField_3.getText())+Double.parseDouble(textField_4.getText())+Double.parseDouble(textField_5.getText())+Double.parseDouble(textField_6.getText())+Double.parseDouble(textField_7.getText())+Double.parseDouble(textField_8.getText())+Double.parseDouble(textField_9.getText())+Double.parseDouble(textField_10.getText());
				textField_11.setText(Double.toString(total_amount));
			}
		});
		spinner_1.setBounds(244, 172, 60, 20);
		panel.add(spinner_1);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(366, 172, 80, 20);
		panel.add(textField_1);
		
		label_2 = new JLabel("200");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(62, 201, 120, 20);
		panel.add(label_2);
		
		spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String amount=label_2.getText().trim();
				int value = (Integer) spinner_2.getValue();
				int total= Integer.parseInt(amount)*value;
				textField_2.setText(Integer.toString(total));
				total_amount=Double.parseDouble(textField.getText())+Double.parseDouble(textField_1.getText())+Double.parseDouble(textField_2.getText())+Double.parseDouble(textField_3.getText())+Double.parseDouble(textField_4.getText())+Double.parseDouble(textField_5.getText())+Double.parseDouble(textField_6.getText())+Double.parseDouble(textField_7.getText())+Double.parseDouble(textField_8.getText())+Double.parseDouble(textField_9.getText())+Double.parseDouble(textField_10.getText());
				textField_11.setText(Double.toString(total_amount));
			}
		});
		spinner_2.setBounds(244, 203, 60, 20);
		panel.add(spinner_2);
		
		textField_2 = new JTextField();
		textField_2.setText("0");
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(366, 203, 80, 20);
		panel.add(textField_2);
		
		label_3 = new JLabel("100");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(62, 232, 120, 20);
		panel.add(label_3);
		
		spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String amount=label_3.getText().trim();
				int value = (Integer) spinner_3.getValue();
				int total= Integer.parseInt(amount)*value;
				textField_3.setText(Integer.toString(total));
				total_amount=Double.parseDouble(textField.getText())+Double.parseDouble(textField_1.getText())+Double.parseDouble(textField_2.getText())+Double.parseDouble(textField_3.getText())+Double.parseDouble(textField_4.getText())+Double.parseDouble(textField_5.getText())+Double.parseDouble(textField_6.getText())+Double.parseDouble(textField_7.getText())+Double.parseDouble(textField_8.getText())+Double.parseDouble(textField_9.getText())+Double.parseDouble(textField_10.getText());
				textField_11.setText(Double.toString(total_amount));
			}
		});
		spinner_3.setBounds(244, 234, 60, 20);
		panel.add(spinner_3);
		
		textField_3 = new JTextField();
		textField_3.setText("0");
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(366, 234, 80, 20);
		panel.add(textField_3);
		
		label_4 = new JLabel("50");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(62, 263, 120, 20);
		panel.add(label_4);
		
		spinner_4 = new JSpinner();
		spinner_4.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String amount=label_4.getText().trim();
				int value = (Integer) spinner_4.getValue();
				int total= Integer.parseInt(amount)*value;
				textField_4.setText(Integer.toString(total));
				total_amount=Double.parseDouble(textField.getText())+Double.parseDouble(textField_1.getText())+Double.parseDouble(textField_2.getText())+Double.parseDouble(textField_3.getText())+Double.parseDouble(textField_4.getText())+Double.parseDouble(textField_5.getText())+Double.parseDouble(textField_6.getText())+Double.parseDouble(textField_7.getText())+Double.parseDouble(textField_8.getText())+Double.parseDouble(textField_9.getText())+Double.parseDouble(textField_10.getText());
				textField_11.setText(Double.toString(total_amount));
			}
		});
		spinner_4.setBounds(244, 265, 60, 20);
		panel.add(spinner_4);
		
		textField_4 = new JTextField();
		textField_4.setText("0");
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(366, 265, 80, 20);
		panel.add(textField_4);
		
		label_5 = new JLabel("20");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_5.setBounds(62, 294, 120, 20);
		panel.add(label_5);
		
		spinner_5 = new JSpinner();
		spinner_5.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_5.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String amount=label_5.getText().trim();
				int value = (Integer) spinner_5.getValue();
				int total= Integer.parseInt(amount)*value;
				textField_5.setText(Integer.toString(total));
				total_amount=Double.parseDouble(textField.getText())+Double.parseDouble(textField_1.getText())+Double.parseDouble(textField_2.getText())+Double.parseDouble(textField_3.getText())+Double.parseDouble(textField_4.getText())+Double.parseDouble(textField_5.getText())+Double.parseDouble(textField_6.getText())+Double.parseDouble(textField_7.getText())+Double.parseDouble(textField_8.getText())+Double.parseDouble(textField_9.getText())+Double.parseDouble(textField_10.getText());
				textField_11.setText(Double.toString(total_amount));
			}
		});
		spinner_5.setBounds(244, 296, 60, 20);
		panel.add(spinner_5);
		
		textField_5 = new JTextField();
		textField_5.setText("0");
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(366, 296, 80, 20);
		panel.add(textField_5);
		
		label_6 = new JLabel("10");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_6.setBounds(62, 325, 120, 20);
		panel.add(label_6);
		
		spinner_6 = new JSpinner();
		spinner_6.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_6.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String amount=label_6.getText().trim();
				int value = (Integer) spinner_6.getValue();
				int total= Integer.parseInt(amount)*value;
				textField_6.setText(Integer.toString(total));
				total_amount=Double.parseDouble(textField.getText())+Double.parseDouble(textField_1.getText())+Double.parseDouble(textField_2.getText())+Double.parseDouble(textField_3.getText())+Double.parseDouble(textField_4.getText())+Double.parseDouble(textField_5.getText())+Double.parseDouble(textField_6.getText())+Double.parseDouble(textField_7.getText())+Double.parseDouble(textField_8.getText())+Double.parseDouble(textField_9.getText())+Double.parseDouble(textField_10.getText());
				textField_11.setText(Double.toString(total_amount));
			}
		});
		spinner_6.setBounds(244, 327, 60, 20);
		panel.add(spinner_6);
		
		textField_6 = new JTextField();
		textField_6.setText("0");
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(366, 327, 80, 20);
		panel.add(textField_6);
		
		label_7 = new JLabel("5");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_7.setBounds(62, 356, 120, 20);
		panel.add(label_7);
		
		spinner_7 = new JSpinner();
		spinner_7.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_7.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String amount=label_7.getText().trim();
				int value = (Integer) spinner_7.getValue();
				int total= Integer.parseInt(amount)*value;
				textField_7.setText(Integer.toString(total));
				total_amount=Double.parseDouble(textField.getText())+Double.parseDouble(textField_1.getText())+Double.parseDouble(textField_2.getText())+Double.parseDouble(textField_3.getText())+Double.parseDouble(textField_4.getText())+Double.parseDouble(textField_5.getText())+Double.parseDouble(textField_6.getText())+Double.parseDouble(textField_7.getText())+Double.parseDouble(textField_8.getText())+Double.parseDouble(textField_9.getText())+Double.parseDouble(textField_10.getText());
				textField_11.setText(Double.toString(total_amount));
			}
		});
		spinner_7.setBounds(244, 358, 60, 20);
		panel.add(spinner_7);
		
		textField_7 = new JTextField();
		textField_7.setText("0");
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(366, 358, 80, 20);
		panel.add(textField_7);
		
		label_8 = new JLabel("1");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_8.setBounds(62, 387, 120, 20);
		panel.add(label_8);
		
		spinner_8 = new JSpinner();
		spinner_8.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_8.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String amount=label_8.getText().trim();
				int value = (Integer) spinner_8.getValue();
				int total= Integer.parseInt(amount)*value;
				textField_8.setText(Integer.toString(total));
				total_amount=Double.parseDouble(textField.getText())+Double.parseDouble(textField_1.getText())+Double.parseDouble(textField_2.getText())+Double.parseDouble(textField_3.getText())+Double.parseDouble(textField_4.getText())+Double.parseDouble(textField_5.getText())+Double.parseDouble(textField_6.getText())+Double.parseDouble(textField_7.getText())+Double.parseDouble(textField_8.getText())+Double.parseDouble(textField_9.getText())+Double.parseDouble(textField_10.getText());
				textField_11.setText(Double.toString(total_amount));
			}
		});
		spinner_8.setBounds(244, 389, 60, 20);
		panel.add(spinner_8);
		
		textField_8 = new JTextField();
		textField_8.setText("0");
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(366, 389, 80, 20);
		panel.add(textField_8);
		
		label_9 = new JLabel("0.50");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_9.setBounds(62, 418, 120, 20);
		panel.add(label_9);
		
		spinner_9 = new JSpinner();
		spinner_9.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_9.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String amount=label_9.getText().trim();
				int value = (Integer) spinner_9.getValue();
				double total= Double.parseDouble(amount)*value;
				textField_9.setText(Double.toString(total));
				total_amount=Double.parseDouble(textField.getText())+Double.parseDouble(textField_1.getText())+Double.parseDouble(textField_2.getText())+Double.parseDouble(textField_3.getText())+Double.parseDouble(textField_4.getText())+Double.parseDouble(textField_5.getText())+Double.parseDouble(textField_6.getText())+Double.parseDouble(textField_7.getText())+Double.parseDouble(textField_8.getText())+Double.parseDouble(textField_9.getText())+Double.parseDouble(textField_10.getText());
				textField_11.setText(Double.toString(total_amount));
			}
		});
		spinner_9.setBounds(244, 420, 60, 20);
		panel.add(spinner_9);
		
		textField_9 = new JTextField();
		textField_9.setText("0");
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBounds(366, 420, 80, 20);
		panel.add(textField_9);
		
		label_10 = new JLabel("0.25");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_10.setBounds(62, 449, 120, 20);
		panel.add(label_10);
		
		 spinner_10 = new JSpinner();
		 spinner_10.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		 spinner_10.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent arg0) {
		 		String amount=label_10.getText().trim();
		 		int value = (Integer) spinner_10.getValue();
				double total= Double.parseDouble(amount)*value;
				textField_10.setText(Double.toString(total));
				total_amount=Double.parseDouble(textField.getText())+Double.parseDouble(textField_1.getText())+Double.parseDouble(textField_2.getText())+Double.parseDouble(textField_3.getText())+Double.parseDouble(textField_4.getText())+Double.parseDouble(textField_5.getText())+Double.parseDouble(textField_6.getText())+Double.parseDouble(textField_7.getText())+Double.parseDouble(textField_8.getText())+Double.parseDouble(textField_9.getText())+Double.parseDouble(textField_10.getText());
				textField_11.setText(Double.toString(total_amount));
		 	}
		 });
		spinner_10.setBounds(244, 451, 60, 20);
		panel.add(spinner_10);
		
		textField_10 = new JTextField();
		textField_10.setText("0");
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBounds(366, 451, 80, 20);
		panel.add(textField_10);
		
		JLabel lblTotalCash = new JLabel("Total Cash");
		lblTotalCash.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalCash.setBounds(244, 498, 80, 20);
		panel.add(lblTotalCash);
		
		textField_11 = new JTextField();
		textField_11.setFont(new Font("Tahoma", Font.BOLD, 13));
		textField_11.setText("0");
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBounds(354, 497, 92, 20);
		panel.add(textField_11);
		
		get_data(company,reg_nme);
		
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{spinner, spinner_1, spinner_2, spinner_3, spinner_4, spinner_5, spinner_6, spinner_7, spinner_8, spinner_9, spinner_10, separator, separator_2, lblCloseSales, lblDenomination, menuItem, menuItem_1, lblCount, lblTotal, label, textField, label_1, textField_1, label_2, textField_2, label_3, textField_3, label_4, textField_4, label_5, textField_5, label_6, textField_6, label_7, textField_7, label_8, textField_8, label_9, textField_9, label_10, textField_10, lblTotalCash, textField_11}));
			
	}

	private void get_data(String company, String reg_nme) {
		try
		{
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_denomination where ref_reg_num='"+reg_nme+"' and status='0' ");
			while(resultSet.next())
			{
				if(resultSet.getString("denomination").equals("1000"))
				{
					spinner.setValue(Integer.valueOf(resultSet.getString("count")));
					textField.setText(resultSet.getString("total_amt"));
				}
				if(resultSet.getString("denomination").equals("500"))
				{
					spinner_1.setValue(Integer.valueOf(resultSet.getString("count")));
					textField_1.setText(resultSet.getString("total_amt"));
				}
				if(resultSet.getString("denomination").equals("200"))
				{
					spinner_2.setValue(Integer.valueOf(resultSet.getString("count")));
					textField_2.setText(resultSet.getString("total_amt"));
				}
				if(resultSet.getString("denomination").equals("100"))
				{
					spinner_3.setValue(Integer.valueOf(resultSet.getString("count")));
					textField_3.setText(resultSet.getString("total_amt"));
				}
				if(resultSet.getString("denomination").equals("50"))
				{
					spinner_4.setValue(Integer.valueOf(resultSet.getString("count")));
					textField_4.setText(resultSet.getString("total_amt"));
				}
				if(resultSet.getString("denomination").equals("20"))
				{
					spinner_5.setValue(Integer.valueOf(resultSet.getString("count")));
					textField_5.setText(resultSet.getString("total_amt"));
				}
				if(resultSet.getString("denomination").equals("10"))
				{
					spinner_6.setValue(Integer.valueOf(resultSet.getString("count")));
					textField_6.setText(resultSet.getString("total_amt"));
				}
				if(resultSet.getString("denomination").equals("5"))
				{
					spinner_7.setValue(Integer.valueOf(resultSet.getString("count")));
					textField_7.setText(resultSet.getString("total_amt"));
				}
				if(resultSet.getString("denomination").equals("1"))
				{
					spinner_8.setValue(Integer.valueOf(resultSet.getString("count")));
					textField_8.setText(resultSet.getString("total_amt"));
				}
				if(resultSet.getString("denomination").equals("0.50"))
				{
					spinner_9.setValue(Integer.valueOf(resultSet.getString("count")));
					textField_9.setText(resultSet.getString("total_amt"));
				}
				if(resultSet.getString("denomination").equals("0.25"))
				{
					spinner_10.setValue(Integer.valueOf(resultSet.getString("count")));
					textField_10.setText(resultSet.getString("total_amt"));
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
		
	}
}
