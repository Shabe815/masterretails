package accounts_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

import org.json.JSONException;

public class Acc_Config {

	private static JFrame frame;
	private static JTextField txtCcgggssxxx;
	private static JTextField textField_4;
	private static JTextField textField_5;
	private static JTextField textField_6;
	private JTable table;
	private DefaultTableModel model;
	private static JDateChooser dateChooser;
	private static JDateChooser dateChooser_1;
	private static JYearChooser yearChooser;
	private static JCheckBox chckbxUseAp;
	private static JCheckBox chckbxUseTransaction;
	private static JCheckBox chckbxImmediatePosting;
	private static JCheckBox chckbxAllowVoids;
	private static JCheckBox chckbxApprovalRequired_1;
	private static JCheckBox chckbxApprovalRequired;
	private static JCheckBox chckbxApprovalRequired_2;
	private static JCheckBox chckbxAllowInter;
	private static JCheckBox chckbxAutomaticAccountNumber;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					Acc_Config window = new Acc_Config("mr_pro", null);
					Acc_Config.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Acc_Config(String company,String version) throws JSONException {
		initialize(company, version);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company,String version) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 900, 600);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 884, 562);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAccountingConfiguration = new JLabel("Accounting Configuration");
		lblAccountingConfiguration.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAccountingConfiguration.setBounds(10, 11, 228, 44);
		panel.add(lblAccountingConfiguration);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 77, 884, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Financial Year Date :");
		lblNewLabel.setBounds(22, 99, 117, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("To");
		lblNewLabel_1.setBounds(290, 99, 29, 14);
		panel.add(lblNewLabel_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(447, 77, 2, 485);
		panel.add(separator_1);
		
		JLabel lblFinancialYear = new JLabel("Financial Year  :");
		lblFinancialYear.setBounds(22, 138, 117, 14);
		panel.add(lblFinancialYear);
		
		JLabel lblAccountingFormat = new JLabel("Accounting Format  :");
		lblAccountingFormat.setBounds(22, 175, 117, 14);
		panel.add(lblAccountingFormat);
		
		txtCcgggssxxx = new JTextField();
		txtCcgggssxxx.setEditable(false);
		txtCcgggssxxx.setText("CC-GG-SS-XXXXXX");
		txtCcgggssxxx.setColumns(10);
		txtCcgggssxxx.setBounds(162, 172, 215, 20);
		panel.add(txtCcgggssxxx);
		
		JLabel lblNoofSegments = new JLabel("No.of Segments :");
		lblNoofSegments.setBounds(22, 218, 117, 14);
		panel.add(lblNoofSegments);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setText("4");
		textField_4.setColumns(10);
		textField_4.setBounds(162, 215, 70, 20);
		panel.add(textField_4);
		
		JLabel lblMainAccountSegment = new JLabel("Main Account Segment# :");
		lblMainAccountSegment.setBounds(22, 260, 128, 14);
		panel.add(lblMainAccountSegment);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setText("4");
		textField_5.setColumns(10);
		textField_5.setBounds(162, 257, 70, 20);
		panel.add(textField_5);
		
		JLabel lblBranchSegment = new JLabel("Branch Segment# :");
		lblBranchSegment.setBounds(22, 302, 128, 14);
		panel.add(lblBranchSegment);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setText("2");
		textField_6.setColumns(10);
		textField_6.setBounds(162, 299, 70, 20);
		panel.add(textField_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 342, 447, 140);
		panel.add(scrollPane);
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
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(20);
		    model.addColumn("Segment"); 
		    model.addColumn("Position"); 
		    model.addColumn("Len"); 
		    model.addColumn("Description "); 
			model.addRow(new Object[]{"Segment1","0","2","Account Type"});
			model.addRow(new Object[]{"Segment2","3","3","Account Group"});
			model.addRow(new Object[]{"Segment3","6","2","Store Code"});
			model.addRow(new Object[]{"Segment4","8","3","Main Account"});
			
		    scrollPane.setViewportView(table);
		    
		    JSeparator separator_2 = new JSeparator();
		    scrollPane.setRowHeaderView(separator_2);
		    
		    chckbxAutomaticAccountNumber = new JCheckBox("Automatic Account Number Creation\r\n");
		    chckbxAutomaticAccountNumber.setEnabled(false);
		    chckbxAutomaticAccountNumber.setSelected(true);
		    chckbxAutomaticAccountNumber.setBackground(new Color(243, 229, 216));
		    chckbxAutomaticAccountNumber.setBounds(22, 504, 254, 23);
		    panel.add(chckbxAutomaticAccountNumber);
		    
		    JLabel lblNewLabel_2 = new JLabel((String) null);
		    lblNewLabel_2.setBounds(463, 99, 46, 14);
		    panel.add(lblNewLabel_2);
		    
		    chckbxAllowInter = new JCheckBox("1. Allow Inter Branch Transaction in Finance ");
		    chckbxAllowInter.setBackground(new Color(243, 229, 216));
		    chckbxAllowInter.setBounds(483, 134, 307, 23);
		    panel.add(chckbxAllowInter);
		    
		    chckbxApprovalRequired = new JCheckBox("2. Approval required for posting G/L Transactions");
		    chckbxApprovalRequired.setBackground(new Color(243, 229, 216));
		    chckbxApprovalRequired.setBounds(483, 175, 383, 23);
		    panel.add(chckbxApprovalRequired);
		    
		    chckbxApprovalRequired_1 = new JCheckBox("3. Approval required for posting A/P Transactions");
		    chckbxApprovalRequired_1.setBackground(new Color(243, 229, 216));
		    chckbxApprovalRequired_1.setBounds(483, 214, 357, 23);
		    panel.add(chckbxApprovalRequired_1);
		    
		    chckbxApprovalRequired_2 = new JCheckBox("4. Approval required for posting A/R Transactions");
		    chckbxApprovalRequired_2.setBackground(new Color(243, 229, 216));
		    chckbxApprovalRequired_2.setBounds(483, 256, 357, 23);
		    panel.add(chckbxApprovalRequired_2);
		    
		    chckbxAllowVoids = new JCheckBox("5. Allow Voids after approval of Transactions");
		    chckbxAllowVoids.setBackground(new Color(243, 229, 216));
		    chckbxAllowVoids.setBounds(483, 298, 341, 23);
		    panel.add(chckbxAllowVoids);
		    
		    chckbxImmediatePosting = new JCheckBox("6. Immediate posting of transaction after approval");
		    chckbxImmediatePosting.setBackground(new Color(243, 229, 216));
		    chckbxImmediatePosting.setBounds(483, 343, 331, 23);
		    panel.add(chckbxImmediatePosting);
		    
		    chckbxUseTransaction = new JCheckBox("7. Use Transaction Date as Posting Date");
		    chckbxUseTransaction.setBackground(new Color(243, 229, 216));
		    chckbxUseTransaction.setBounds(483, 384, 331, 23);
		    panel.add(chckbxUseTransaction);
		    
		    chckbxUseAp = new JCheckBox("8. Use A/P Voucher for full Payments of Vendor Invoices");
		    chckbxUseAp.setBackground(new Color(243, 229, 216));
		    chckbxUseAp.setBounds(483, 428, 383, 23);
		    panel.add(chckbxUseAp);
		    
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
			menuItem.setBounds(654, 7, 60, 48);
			panel.add(menuItem);
			
			JMenuItem menuItem_1 = new JMenuItem("Refresh");
			menuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Acc_Config.get_po_term_edit(company);
				}
			});
			menuItem_1.setBorder(null);
			menuItem_1.setIcon(new ImageIcon(refresh));
			menuItem_1.setBackground(new Color(243, 229, 216));
			menuItem_1.setBounds(714, 7, 80, 48);
			panel.add(menuItem_1);
			
			JMenuItem menuItem_2 = new JMenuItem("Save");
			menuItem_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String finc_date_from=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					if(!finc_date_from.equals(""))
					{
						finc_date_from = sdf.format(dateChooser.getDate());
					}
					String finc_date_to=((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
					if(!finc_date_to.equals(""))
					{
						finc_date_to = sdf.format(dateChooser.getDate());
					}
					int finc_year=yearChooser.getYear();
					String acc_format=txtCcgggssxxx.getText();
					String no_segment=textField_4.getText();
					String main_acc__segment=textField_5.getText();
					String branch_segment=textField_6.getText();
					String auto_acc_create="false";
					String inter_branch_trans="false";
					String approval_for_GL="false";
					String approval_for_AP="false";
					String approval_for_AR="false";
					String void_after_trans="false";
					String ImmediatePosting="false";
					String tran_date_as_post_date="false";
					String Ap_voucher_pay_vend_inv="false";
					if(finc_date_from.equals(""))
					{
						JOptionPane.showMessageDialog(frame, "Please Select or Enter the Category Code");
					}
					else
					{
						if(chckbxAutomaticAccountNumber.isSelected())
						{
							auto_acc_create="true";
						}
						if(chckbxAllowInter.isSelected())
						{
							inter_branch_trans="true";
						}
						if(chckbxApprovalRequired.isSelected())
						{
							approval_for_GL="true";
						}
						if(chckbxApprovalRequired_1.isSelected())
						{
							approval_for_AP="true";
						}
						if(chckbxApprovalRequired_2.isSelected())
						{
							approval_for_AR="true";
						}
						if(chckbxAllowVoids.isSelected())
						{
							void_after_trans="true";
						}
						if(chckbxImmediatePosting.isSelected())
						{
							ImmediatePosting="true";
						}
						if(chckbxUseTransaction.isSelected())
						{
							tran_date_as_post_date="true";
						}
						if(chckbxUseAp.isSelected())
						{
							Ap_voucher_pay_vend_inv="true";
						}
						
						LocalDateTime myDateObj = LocalDateTime.now();  
					    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
					    String formattedDate = myDateObj.format(myFormatObj);  
					    Connection con=Dbsetup.dbconn.getConnection();
						Statement statement;
						try {
							statement = con.createStatement();
							ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_account_config where finanical_year='"+finc_year+"' ");
							if(resultSet.next())
							{
								String query="update "+company+".mr_account_config set finanical_year_from='"+finc_date_from+"',finanical_year_to='"+finc_date_to+"',acc_format='"+acc_format+"',noof_segment='"+no_segment+"',main_acc_segment='"+main_acc__segment+"',branch_segment='"+branch_segment+"',automatic_acc_num_create='"+auto_acc_create+"',inter_branch_transcation='"+inter_branch_trans+"',approval_required_GL_trans='"+approval_for_GL+"',approval_required_AP_trans='"+approval_for_AP+"',approval_required_AR_trans='"+approval_for_AR+"',allow_void_after_trans='"+void_after_trans+"',`immediate_post_after_approve`='"+ImmediatePosting+"',use_trans_date_as_posting_date='"+tran_date_as_post_date+"',use_AP_voucher_for_full_payment_of_vendor_invoice='"+Ap_voucher_pay_vend_inv+"',modified_date='"+formattedDate+"'  where finanical_year='"+finc_year+"' ";
								statement.executeUpdate(query);
								JOptionPane.showMessageDialog(frame, "Saved Successfully");
							}
							else
							{
								
							}
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}  
					}
				}
			});
			menuItem_2.setBorder(null);
			menuItem_2.setIcon(new ImageIcon(save));
			menuItem_2.setBackground(new Color(243, 229, 216));
			menuItem_2.setBounds(796, 7, 70, 48);
			panel.add(menuItem_2);
			
			dateChooser = new JDateChooser();
			dateChooser.setEnabled(false);
			dateChooser.setDateFormatString("dd/MM/yyyy");
			dateChooser.setBounds(162, 99, 100, 22);
			panel.add(dateChooser);
			
			dateChooser_1 = new JDateChooser();
			dateChooser_1.setEnabled(false);
			dateChooser_1.setDateFormatString("dd/MM/yyyy");
			dateChooser_1.setBounds(335, 99, 100, 22);
			panel.add(dateChooser_1);
			
			yearChooser = new JYearChooser();
			yearChooser.setEnabled(false);
			yearChooser.setBounds(162, 138, 51, 22);
			panel.add(yearChooser);
			get_po_term_edit( company);
		
	}
	public static void get_po_term_edit(String company)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_account_config ");
			if(resultSet.next())
			{
				//yearChooser.setYear(Integer.parseInt(resultSet.getString("finanical_year")));
				txtCcgggssxxx.setText(resultSet.getString("acc_format"));
				textField_4.setText(resultSet.getString("noof_segment"));
				textField_5.setText(resultSet.getString("main_acc_segment"));
				textField_6.setText(resultSet.getString("branch_segment"));
				
				if(resultSet.getString("automatic_acc_num_create").equals("true"))
				{
					chckbxAutomaticAccountNumber.setSelected(true);
				}
				else
				{
					chckbxAutomaticAccountNumber.setSelected(false);
				}
				if(resultSet.getString("inter_branch_transcation").equals("true"))
				{
					chckbxAllowInter.setSelected(true);
				}
				else
				{
					chckbxAllowInter.setSelected(false);
				}
				if(resultSet.getString("approval_required_GL_trans").equals("true"))
				{
					chckbxApprovalRequired.setSelected(true);
				}
				else
				{
					chckbxApprovalRequired.setSelected(false);
				}
				if(resultSet.getString("approval_required_AP_trans").equals("true"))
				{
					chckbxApprovalRequired_1.setSelected(true);
				}
				else
				{
					chckbxApprovalRequired_1.setSelected(false);
				}
				if(resultSet.getString("approval_required_AR_trans").equals("true"))
				{
					chckbxApprovalRequired_2.setSelected(true);
				}
				else
				{
					chckbxApprovalRequired_2.setSelected(false);
				}
				if(resultSet.getString("allow_void_after_trans").equals("true"))
				{
					chckbxAllowVoids.setSelected(true);
				}
				else
				{
					chckbxAllowVoids.setSelected(false);
				}
				if(resultSet.getString("immediate_post_after_approve").equals("true"))
				{
					chckbxImmediatePosting.setSelected(true);
				}
				else
				{
					chckbxImmediatePosting.setSelected(false);
				}
				if(resultSet.getString("use_trans_date_as_posting_date").equals("true"))
				{
					chckbxUseTransaction.setSelected(true);
				}
				else
				{
					chckbxUseTransaction.setSelected(false);
				}
				if(resultSet.getString("use_AP_voucher_for_full_payment_of_vendor_invoice").equals("true"))
				{
					chckbxUseAp.setSelected(true);
				}
				else
				{
					chckbxUseAp.setSelected(false);
				}
				java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("finanical_year_from"));
				dateChooser.setDate(date);
				java.util.Date date_end = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("finanical_year_to"));
				dateChooser_1.setDate(date_end);
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
