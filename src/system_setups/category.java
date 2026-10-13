package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import org.json.JSONException;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import com.toedter.calendar.JDateChooser;

public class category {

	private JFrame frame;
	private JTable table;
	private JTextField hiddentxt;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;
	private JDateChooser dateChooser_1;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBox;
	private JCheckBox chckbxNewCheckBox;
	private String disc_status="";
	private boolean dot=false;
	private JComboBox<String> comboBox_1;
	private JPanel panel_2;
	private DefaultTableModel model;
	
	private JProgressBar progressBar;
	//private JMenuItem mntmSave;
	
    private String company="";
    private String version_num="";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					category window = new category("mr_pro","", null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog( null, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws JSONException
	 * @throws SQLException 
	 */
	public category(String company,String page,String version) throws JSONException, SQLException {
		initialize(company, page, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param page 
	 * 
	 * @throws JSONException
	 * @throws SQLException 
	 */
	private void initialize(final String db_name, final String page,final String version)
			throws JSONException, SQLException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.setBounds(0, 0, 750, 500);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		
		version_num=version;
		company=db_name;
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 59, 732, 2);
		frame.getContentPane().add(separator);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 732, 58);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		frame.setVisible(true);

		Image dept=new ImageIcon(this.getClass().getResource("/Barcode.png")).getImage();
		JLabel lblCompanySetup = new JLabel("Category");
		lblCompanySetup.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCompanySetup.setIcon(new ImageIcon(dept));
		lblCompanySetup.setBounds(21, 11, 228, 44);
		panel.add(lblCompanySetup);

		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();

		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				if(page=="item")
				{
					inventory_master.Item_master.frame.setAlwaysOnTop(true);
					inventory_master.Item_master.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					inventory_master.Item_master.menuItem.setEnabled(true);	
					inventory_master.Item_master.comboBox_4.removeAllItems();
					inventory_master.Item_master.get_item_cat(company);
				}
				
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(514, 0, 60, 48);
		panel.add(mntmNewMenuItem);

		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_dept_list(company,"");
				
			}
		});
		mntmRefresh.setBackground(new Color(243, 229, 216));
		mntmRefresh.setIcon(new ImageIcon(refresh));
		mntmRefresh.setBounds(574, 0, 80, 48);
		mntmRefresh.setBorder(null);
		panel.add(mntmRefresh);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sync(e);
			}

		});
		mntmSave.setBackground(new Color(243, 229, 216));
		mntmSave.setIcon(new ImageIcon(save));
		mntmSave.setBounds(656, 0, 152, 48);
		mntmSave.setBorder(null);
		panel.add(mntmSave);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(243, 229, 216));
		panel_1.setBounds(0, 62, 732, 391);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 0, 205));
		progressBar.setBackground(new Color(192, 192, 192));
		progressBar.setStringPainted(true);
		//progressBar.setBounds(12, 198, 622, 31);
		//frame.getContentPane().add(progressBar);
		progressBar.setBounds(336, 6, 396, 31);
		progressBar.setVisible(false);
		panel_1.add(progressBar);

		JLabel lblNewDepartment = new JLabel("New Category");
		lblNewDepartment.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewDepartment.setBounds(12, 13, 119, 16);
		panel_1.add(lblNewDepartment);

		JLabel lblDepartmentList = new JLabel("Category List");
		lblDepartmentList.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDepartmentList.setBounds(12, 190, 119, 16);
		panel_1.add(lblDepartmentList);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 219, 708, 172);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel_1.add(scrollPane);

		model = new DefaultTableModel() {

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
		model.addColumn("ID");
		model.addColumn("Category Code");
		model.addColumn("Category Name");
		model.addColumn("Sequence Number");
		model.addColumn("Status");
		model.addColumn("Starting Date");
		model.addColumn("Ending Date");
		model.addColumn("Disc Price");
		model.addColumn("Type");
		scrollPane.setViewportView(table);

		hiddentxt = new JTextField();
		hiddentxt.setBounds(604, 187, 116, 22);
		panel_1.add(hiddentxt);
		hiddentxt.setVisible(false);
		hiddentxt.setEditable(false);
		hiddentxt.setColumns(10);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(243, 229, 216));
		panel_2.setBounds(22, 42, 300, 150);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		textField = new JTextField("1");
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(130, 80, 152, 25);
		panel_2.add(textField);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField.getText().equals(""))
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

		JLabel label_1 = new JLabel("Category Name");
		label_1.setBounds(5, 45, 111, 25);
		panel_2.add(label_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(130, 45, 152, 25);
		panel_2.add(textField_1);
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			textField_1.setText(null);
			panel_2.add(comboBox_1);
			if(comboBox_1.getItemCount()>0)
			{
				comboBox_1.showPopup();
			}
		   	}
			@Override
			public void mousePressed(MouseEvent arg0) {

				//JOptionPane.showMessageDialog(frame, comboBox_1.getSelectedItem().toString());
			}
			});
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
				public void keyPressed(KeyEvent e) {
			    	if (e.getKeyCode() == 38) {
			    		int x = comboBox_1.getSelectedIndex();
			    		if (x > 0) {
			    			comboBox_1.setSelectedIndex(x - 1);
			    		}
			    		panel_2.add(comboBox_1);
			    		if(comboBox_1.getItemCount()>0)
						{
							comboBox_1.showPopup();
						}
			    	} 
			    	else if (e.getKeyCode() == 40) {
			    		int x = comboBox_1.getSelectedIndex();
			    		int y = comboBox_1.getItemCount();
			    		if (x + 1 < y)
			    			comboBox_1.setSelectedIndex(x + 1);
			    		panel_2.add(comboBox_1);
			    		if(comboBox_1.getItemCount()>0)
						{
							comboBox_1.showPopup();
						}
			    	}
			   	}
			});
			textField_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if(textField_1.getText().equals("")){
							textField_1.setText(comboBox_1.getSelectedItem().toString());
							
			    		}
						textField_1.setText(comboBox_1.getSelectedItem().toString());
						comboBox_1.removeAllItems();
						comboBox_1.hidePopup();
						panel_2.remove(comboBox_1);
						
					} catch (Exception e) {
					}
				}
			});
			textField_1.addCaretListener(new TextFieldCaretListener(company,page));
			textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		textField_2.setBounds(130, 10, 152, 25);
		panel_2.add(textField_2);
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
			    if (textField_2.getText().equals(""))
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

		JLabel label_2 = new JLabel("Category Code");
		label_2.setBounds(5, 10, 119, 25);
		panel_2.add(label_2);

		JLabel label = new JLabel("Item Code Sequence");
		label.setBounds(5, 80, 125, 25);
		panel_2.add(label);

		JLabel lblPromoStatus = new JLabel("Promo Status");
		lblPromoStatus.setBounds(5, 115, 111, 25);
		panel_2.add(lblPromoStatus);

		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxNewCheckBox.isSelected()) {
					comboBox.setEnabled(true);
					dateChooser.setEnabled(true);
					dateChooser_1.setEnabled(true);
					textField_5.setEditable(true);
				} else {
					comboBox.setEnabled(false);
					dateChooser.setEnabled(false);
					dateChooser_1.setEnabled(false);
					textField_5.setEditable(false);
				}
			}
		});
		
		chckbxNewCheckBox.setBackground(new Color(243, 229, 216));
		chckbxNewCheckBox.setBounds(130, 115, 25, 25);
		panel_2.add(chckbxNewCheckBox);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(130, 48, 152, 22);
		panel_2.add(comboBox_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(243, 229, 216));
		panel_3.setBounds(348, 39, 372, 149);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblEndingDate = new JLabel("Ending Date");
		lblEndingDate.setBounds(12, 45, 111, 25);
		panel_3.add(lblEndingDate);

		JLabel lblStartingDate = new JLabel("Starting Date");
		lblStartingDate.setBounds(12, 10, 111, 25);
		panel_3.add(lblStartingDate);

		JLabel lblPromoDiscount = new JLabel("Promo Discount");
		lblPromoDiscount.setBounds(12, 80, 111, 25);
		panel_3.add(lblPromoDiscount);

		JLabel lblPromoType = new JLabel("Promo Type");
		lblPromoType.setBounds(12, 115, 111, 25);
		panel_3.add(lblPromoType);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(135, 80, 154, 25);
		panel_3.add(textField_5);

		comboBox = new JComboBox<String>();
		comboBox.setEnabled(false);
		comboBox.setBounds(135, 115, 154, 25);
		comboBox.addItem("Discount");
		comboBox.addItem("Markup");
		comboBox.setSelectedIndex(-1);
		panel_3.add(comboBox);

		dateChooser = new JDateChooser();
		dateChooser.setEnabled(false);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(135, 10, 154, 22);
		dateChooser.setMinSelectableDate(new Date());
		panel_3.add(dateChooser);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setEnabled(false);
		dateChooser_1.getCalendarButton().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Date date = dateChooser.getDate();
						dateChooser_1.getJCalendar().setMinSelectableDate(date);
						dateChooser_1.setDate(date);
					}
				});
		dateChooser_1.setDateFormatString("dd/MM/yyyy");
		dateChooser_1.setBounds(135, 48, 154, 22);
		panel_3.add(dateChooser_1);

		get_dept_list(company, version);
		
		

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					int rows = table.getSelectedRow();
					textField_2.setText(table.getModel().getValueAt(rows, 1).toString());
					textField_2.setEditable(false);
					textField_1.setText(table.getModel().getValueAt(rows, 2).toString());
					textField.setText(table.getModel().getValueAt(rows, 3).toString());
					disc_status=table.getModel().getValueAt(rows, 4).toString();
					if(disc_status.equals("1"))
					{
						chckbxNewCheckBox.setSelected(true);
						comboBox.setEnabled(true);
						dateChooser.setEnabled(true);
						dateChooser_1.setEnabled(true);
						textField_5.setEditable(true);
					}
					else
					{
						chckbxNewCheckBox.setSelected(false);
						comboBox.setEnabled(false);
						dateChooser.setEnabled(false);
						dateChooser_1.setEnabled(false);
						textField_5.setEditable(false);
					}
					java.util.Date date = null;
					try {
						if(table.getModel().getValueAt(rows, 5).toString()!="")
						{
							date = new SimpleDateFormat("yyyy-MM-dd").parse(table.getModel().getValueAt(rows, 5).toString());
						}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog( null, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
					}
					dateChooser.setDate(date);
					java.util.Date date_end = null;
					try {
						if(table.getModel().getValueAt(rows, 6).toString()!="")
						{
							date_end = new SimpleDateFormat("yyyy-MM-dd").parse(table.getModel().getValueAt(rows, 6).toString());
						}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog( null, e.getMessage(),"Exception error", JOptionPane.ERROR_MESSAGE );
					}
					dateChooser_1.setDate(date_end);
					textField_5.setText(table.getModel().getValueAt(rows, 7).toString());
					comboBox.setSelectedItem(table.getModel().getValueAt(rows, 8).toString());
					hiddentxt.setText(table.getModel().getValueAt(rows, 1).toString());
					
					}
				}
		});
	}
	
	private void sync(ActionEvent e)
	{
		progressBar.setVisible(true);
		double number_progress=0;
		int b=1;
		String dept_cde = textField_2.getText();
		String dept_nme = textField_1.getText();
		String seq_num = textField.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String start_date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		String startdate = null;
		String enddate = null;
		int dept_code = 0;
		if(!start_date.equals(""))
		{
			startdate = sdf.format(dateChooser.getDate());
		}
		String end_date = ((JTextField) dateChooser_1.getDateEditor().getUiComponent()).getText();
		if(!end_date.equals(""))
		{
			enddate = sdf.format(dateChooser_1.getDate());
		}
		String disc_perc = textField_5.getText();
		if(disc_perc.equals(""))
		{
			disc_perc="0";
		}
		String type = (String) comboBox.getSelectedItem();
		String old_dept = hiddentxt.getText();
		
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	    String formattedDate = myDateObj.format(myFormatObj);  
		Connection con=Dbsetup.dbconn.getConnection();
		Statement statement;
		Statement statement1;
		String name;
		if(old_dept.equals(""))
		{
			name=dept_nme;
		}
		else
		{
			name=old_dept;
		}
		if (dept_nme.equals("")) {
			JOptionPane.showMessageDialog(frame,
					"Please, Enter the Category Name");
		} else {
			if(!old_dept.equals("")){
				if (chckbxNewCheckBox.isSelected()) {
					if (start_date.equals("")) {
						JOptionPane.showMessageDialog(frame,
								"Please, Select the Starting Date");
					} else if (end_date.equals("")) {
						JOptionPane.showMessageDialog(frame,
								"Please, Select the Ending Date");
					} else if (disc_perc.equals("")) {
						JOptionPane.showMessageDialog(frame,
								"Please, Enter the Discount Percent");
					} else if (!disc_perc.matches("\\d+(\\.\\d+)*")) {
						JOptionPane.showMessageDialog(frame,
								"Please, Enter the valid Discount Percent");
					} else if (comboBox.getSelectedIndex() == -1){
						JOptionPane.showMessageDialog(frame,
								"Please, Select the Type");
					} else 
					{
						try 
						{
							statement = con.createStatement();
							statement1 = con.createStatement();
							ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_category where cat_code='"+name+"' ");
							if(resultSet.next())
							{
								String query="update "+company+".mr_category set cat_name='"+dept_nme+"',status='1',starting_date='"+startdate+"',ending_date='"+enddate+"',disc_price='"+disc_perc+"',promo_type='"+type+"', `modified_date`='"+formattedDate+"' WHERE id='"+resultSet.getString("id")+"' ";
								statement.executeUpdate(query);
								String qury="select count(*) as ttl from "+company+".mr_tmp_item_master where category='"+dept_nme+"' ";
								ResultSet resultSet121 = statement1.executeQuery(qury);
								if(resultSet121.next())
								{
									double count=Double.parseDouble(resultSet121.getString("ttl"));
									number_progress=100/count;
								}
									
								String remove_dup="delete from "+company+".mr_promotion where  category='"+dept_nme+"'";
								statement.executeUpdate(remove_dup);
								String query_item="select * from "+company+".mr_tmp_item_master where category='"+dept_nme+"' ";
								ResultSet resultSet1 = statement.executeQuery(query_item);
								while(resultSet1.next())
								{
									String update_item_master="update "+company+".mr_item_master set allowDiscount='0', Onpromo='0' where item_code='"+resultSet1.getString("item_code")+"' ";
									 statement1.execute(update_item_master);
									String query1="INSERT INTO "+company+".mr_promotion( `itemcode`, `barcode`, `itemname`, `dept`, `sub_dept`, `category`, `sub_cat`, `percentage`, `from_date`, `to_date`, `promo_type`, `created_date`, `modified_date`) VALUES('"+resultSet1.getString("item_code")+"','"+resultSet1.getString("barcode")+"','"+resultSet1.getString("item_name")+"','"+resultSet1.getString("department")+"','"+resultSet1.getString("sub_dept")+"','"+resultSet1.getString("category")+"','"+resultSet1.getString("sub_cat")+"','"+disc_perc+"','"+startdate+"','"+enddate+"','Category Wise','"+formattedDate+"','"+formattedDate+"' ) ";
									statement1.execute(query1);
									double ttl_count=b*number_progress;
									Thread.sleep(50);
				                	progressBar.paintImmediately(0, 0, 854, 31);
				                	progressBar.setValue((int)ttl_count);
				                	b++;
								}
								JOptionPane.showMessageDialog(frame, "Saved Successfully");
								get_dept_list( company, version_num);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog( null, e1.getMessage(),"SQLException error", JOptionPane.ERROR_MESSAGE );
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
				}
				else
				{
					try {
						statement = con.createStatement();
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_category where cat_code='"+name+"' ");
						if(resultSet.next())
						{
							 String query="update "+company+".mr_category set cat_name='"+dept_nme+"',status='0', `modified_date`='"+formattedDate+"' WHERE id='"+resultSet.getString("id")+"' ";
							 statement.executeUpdate(query);
							 JOptionPane.showMessageDialog(frame, "Saved Successfully");
							 get_dept_list( company, version_num);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog( null, e1.getMessage(),"SQLException error", JOptionPane.ERROR_MESSAGE );
					}  
					
				}
			}
			else
			{
				if (chckbxNewCheckBox.isSelected()) {
					if (start_date.equals("")) {
						JOptionPane.showMessageDialog(frame,
								"Please, Select the Starting Date");
					} else if (end_date.equals("")) {
						JOptionPane.showMessageDialog(frame,
								"Please, Select the Ending Date");
					} else if (disc_perc.equals("")) {
						JOptionPane.showMessageDialog(frame,
								"Please, Enter the Discount Percent");
					} else if (!disc_perc.matches("\\d+(\\.\\d+)*")) {
						JOptionPane.showMessageDialog(frame,
								"Please, Enter the valid Discount Percent");
					} else if (comboBox.getSelectedIndex() == -1){
						JOptionPane.showMessageDialog(frame,
								"Please, Select the Type");
					} else {
						try {
							statement1 = con.createStatement();
							statement = con.createStatement();
							if(dept_cde.equals(""))
							{
								ResultSet resultSet = statement.executeQuery("select MAX(cat_code) AS Lastcode from "+company+".mr_category");
								if(resultSet.next())
								{
									String dept_num=resultSet.getString("Lastcode");
									if(dept_num==null)
									{
										dept_code=100;
									}
									else
									{
										dept_code=Integer.parseInt(resultSet.getString("Lastcode"))+1;
									}
								}
								else
								{
									dept_code=100;
								}
							}
							else
							{
								dept_code=Integer.parseInt(dept_cde);
							}
							
							ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_category  where cat_code='"+dept_code+"'");
							if(!resultSet.next())
							{
								ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_category  where cat_name='"+dept_nme+"'");
								if(!resultSet1.next())
								{
									String query="insert into "+company+".mr_category(`cat_code`, `cat_name`, `seq_num`, `status`, `starting_date`, `ending_date`, `disc_price`, `promo_type`, `created_date`, `modified_date`)  VALUES('"+dept_code+"','"+dept_nme+"','"+seq_num+"','1','"+startdate+"','"+enddate+"','"+disc_perc+"','"+type+"','"+formattedDate+"','"+formattedDate+"')";
									statement.executeUpdate(query);
									int subdept_code = 0;
									ResultSet resultSet2 = statement.executeQuery("select MAX(sub_cat_code) AS Lastcode from "+company+".mr_subcategory");
									if(resultSet2.next())
									{
										String dept_num=resultSet2.getString("Lastcode");
										if(dept_num==null)
										{
											subdept_code=100;
										}
										else
										{

											subdept_code=Integer.parseInt(resultSet2.getString("Lastcode"))+1;
										}
									}
									else
									{
										subdept_code=100;
									}
									String query1="insert into "+company+".mr_subcategory ( `ref_cat_code`, `ref_cat_name`, `sub_cat_code`, `sub_cat_name`, `subcat_status`, `subcat_starting_date`, `subcat_ending_date`, `subcat_disc_price`, `subcat_promo_type`, `created_date`, `modified_date`) VALUES ('"+dept_code+"','"+dept_nme+"','"+subdept_code+"','NIL','0','"+startdate+"','"+enddate+"','"+disc_perc+"','"+type+"','"+formattedDate+"','"+formattedDate+"' ) ";
									statement.executeUpdate(query1);
									String qury="select count(*) as ttl from "+company+".mr_tmp_item_master where category='"+dept_nme+"' ";
									ResultSet resultSet121 = statement1.executeQuery(qury);
									if(resultSet121.next())
									{
										double count=Double.parseDouble(resultSet121.getString("ttl"));
										number_progress=100/count;
									}
										
									String remove_dup="delete from "+company+".mr_promotion where  category='"+dept_nme+"'";
									statement.executeUpdate(remove_dup);
									String query_item="select * from "+company+".mr_tmp_item_master where category='"+dept_nme+"' ";
									ResultSet resultSet11 = statement.executeQuery(query_item);
									while(resultSet11.next())
									{
										String update_item_master="update "+company+".mr_item_master set allowDiscount='0', Onpromo='0' where item_code='"+resultSet11.getString("item_code")+"' ";
										 statement1.execute(update_item_master);
										String query11="INSERT INTO "+company+".mr_promotion( `itemcode`, `barcode`, `itemname`, `dept`, `sub_dept`, `category`, `sub_cat`, `percentage`, `from_date`, `to_date`, `promo_type`, `created_date`, `modified_date`) VALUES('"+resultSet11.getString("item_code")+"','"+resultSet11.getString("barcode")+"','"+resultSet11.getString("item_name")+"','"+resultSet11.getString("department")+"','"+resultSet11.getString("sub_dept")+"','"+resultSet11.getString("category")+"','"+resultSet11.getString("sub_cat")+"','"+disc_perc+"','"+startdate+"','"+enddate+"','Category Wise','"+formattedDate+"','"+formattedDate+"' ) ";
										statement1.execute(query11);
										double ttl_count=b*number_progress;
										Thread.sleep(50);
					                	progressBar.paintImmediately(0, 0, 854, 31);
					                	progressBar.setValue((int)ttl_count);
					                	b++;
									}
									 JOptionPane.showMessageDialog(frame, "Saved Successfully");
									 get_dept_list( company, version_num);
								}
								else
								{
									JOptionPane.showMessageDialog(frame, "Category Name or Code Already Exist");
								}
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Category Code or Code Already Exist");
							}
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog( null, e1.getMessage(),"SQLException error", JOptionPane.ERROR_MESSAGE );
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}  
						
					}
				}
				else
				{
					try {
						statement = con.createStatement();
						if(dept_cde.equals(""))
						{
							ResultSet resultSet = statement.executeQuery("select MAX(cat_code) AS Lastcode from "+company+".mr_category");
							if(resultSet.next())
							{
								String dept_num=resultSet.getString("Lastcode");
								if(dept_num==null)
								{
									dept_code=100;
								}
								else
								{
									dept_code=Integer.parseInt(resultSet.getString("Lastcode"))+1;
									
								}
								
							}
							else
							{
								dept_code=100;
							}
						}
						else
						{
							dept_code=Integer.parseInt(dept_cde);
						}
						ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_category where cat_code='"+dept_code+"' ");
						if(!resultSet.next())
						{
							ResultSet resultSet1 = statement.executeQuery("select * from "+company+".mr_category  where cat_name='"+dept_nme+"'");
							if(!resultSet1.next())
							{
								String query="insert into "+company+".mr_category(`cat_code`, `cat_name`, `seq_num`, `status`, `starting_date`, `ending_date`,`disc_price`, `promo_type`, `created_date`, `modified_date`)  VALUES('"+dept_code+"','"+dept_nme+"','"+seq_num+"','0','"+formattedDate+"','"+formattedDate+"','0','','"+formattedDate+"','"+formattedDate+"')";
								statement.executeUpdate(query);
								int subdept_code = 0;
								ResultSet resultSet2 = statement.executeQuery("select MAX(sub_cat_code) AS Lastcode from "+company+".mr_subcategory");
								if(resultSet2.next())
								{
									String dept_num=resultSet2.getString("Lastcode");
									if(dept_num==null)
									{
										subdept_code=100;
									}
									else
									{

										subdept_code=Integer.parseInt(resultSet2.getString("Lastcode"))+1;
									}
								}
								else
								{
									subdept_code=100;
								}
								String query1="insert into "+company+".mr_subcategory ( `ref_cat_code`, `ref_cat_name`, `sub_cat_code`, `sub_cat_name`, `subcat_status`, `subcat_starting_date`, `subcat_ending_date`, `subcat_disc_price`, `subcat_promo_type`, `created_date`, `modified_date`) VALUES ('"+dept_code+"','"+dept_nme+"','"+subdept_code+"','NIL','0','"+formattedDate+"','"+formattedDate+"','"+disc_perc+"','"+type+"','"+formattedDate+"','"+formattedDate+"' ) ";
								statement.executeUpdate(query1);
								JOptionPane.showMessageDialog(frame, "Saved Successfully");
								get_dept_list( company, version_num);
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Category Name or Code Already Exist");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Category Code or Code Already Exist");
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog( null, e1.getMessage(),"SQLException error", JOptionPane.ERROR_MESSAGE );
					}  
				}
				
			}
		}
		progressBar.setVisible(false);
	}

	private void get_dept_list( String company, String page) {
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_category ");
			int id=1;
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			while(resultSet.next())
			{
				
				String code = resultSet.getString("cat_code");
				String name = resultSet.getString("cat_name");
				String stats = resultSet.getString("seq_num");
				String sts = resultSet.getString("status");
				String std = "";
				String end = "";
				String pri = "";
				String typ = "";
				if(sts.equals("1"))
				{
					std = resultSet.getString("starting_date");
					end = resultSet.getString("ending_date");
					pri = resultSet.getString("disc_price");
					typ = resultSet.getString("promo_type");
				}
				model.addRow(new Object[] { id, code, name, stats,sts,std,end,pri,typ });
				id=id+1;
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				textField_2.setEditable(true);
				textField_2.setText("");
				textField_1.setText("");
				textField.setText("1");
				disc_status="";
				textField_5.setText("");
				comboBox.setSelectedIndex(-1);
				dateChooser.setDate(null);
				dateChooser_1.setDate(null);
				hiddentxt.setText("");
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private class TextFieldCaretListener implements CaretListener {
		List<String> listcode=null;
		public TextFieldCaretListener(String company,String page) throws SQLException{
			listcode = new ArrayList<String>();
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_category ");
			while(resultSet.next())
			{
				listcode.add(resultSet.getString("cat_name"));
			}
		}
		public void caretUpdate(CaretEvent e) {
			try {
				comboBox_1.removeAllItems();
				comboBox_1.hidePopup();
				panel_2.remove(comboBox_1);
				if (e.getMark() >=0) {
					for (String string : listcode) {
						if (string.toLowerCase().contains(textField_1.getText().toLowerCase())) {
							panel_2.add(comboBox_1);
							comboBox_1.addItem(string);
							if(comboBox_1.getItemCount()>0)
							{
								comboBox_1.showPopup();
							}
						}
					}
				}
			} 
			catch (Exception e1) {
			}
			if (e.getMark() < 1) {
				panel_2.remove(comboBox_1);
			}
		}
	}
}
