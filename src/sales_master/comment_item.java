package sales_master;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONException;

import CafePro.Mainpage;

public class comment_item {

	private static JFrame frame;
	private DefaultTableModel model;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private String txtvalue="";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					comment_item window = new comment_item("mr_pro", "admin");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param reg_nme 
	 * @throws JSONException 
	 */
	public comment_item(String company, String value) throws JSONException {
		initialize(company,  value);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param reg_nme 
	 * @param store_name2 
	 */
	private void initialize(final String company,final String value) throws JSONException {
		frame = new JFrame();
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 909, 696);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
				}
				else
				{
					posscreen.txtDisc.requestFocus();
				}
				
				frame.setVisible(false);
			}
		});
		txtvalue=value.trim();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 893, 60);
		frame.getContentPane().add(panel);
		
		Image save = new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh = new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Save");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(Mainpage.SoftLang.equals("Ar"))
					{
						PosscreenArabic.table.setValueAt(textField_1.getText(), PosscreenArabic.table.getSelectedRow(), 21);
						if(txtvalue.length()==textField_1.getText().trim().length())
						{
							
						}
						else
						{
							PosscreenArabic.table.setValueAt(0, PosscreenArabic.table.getSelectedRow(), 23);
						}
						//model.setValueAt("0", tbl_sel, 23);
						frame.setVisible(false);
						PosscreenArabic.txtDisc.requestFocus();
					}
					else
					{
						posscreen.table.setValueAt(textField_1.getText(), posscreen.table.getSelectedRow(), 21);
						if(txtvalue.length()==textField_1.getText().trim().length())
						{
							
						}
						else
						{
							posscreen.table.setValueAt(0, posscreen.table.getSelectedRow(), 23);
						}
						//model.setValueAt("0", tbl_sel, 23);
						frame.setVisible(false);
						posscreen.txtDisc.requestFocus();
					}
					
					
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(frame, e);
				}
			}
		});
	    menuItem.setBorder(null);
	    menuItem.setIcon(new ImageIcon(save));
	    menuItem.setBackground(new Color(243, 229, 216));
	    menuItem.setBounds(811, 7, 135, 48);
	    panel.add(menuItem);
		
		JLabel lblItemMasterList = new JLabel("Item Comments");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 228, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem_2 = new JMenuItem("Exit");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(661, 7, 60, 48);
		menuItem_2.setIcon(new ImageIcon(exit));
		panel.add(menuItem_2);
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				get_itemdisp(company,textField.getText());
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(725, 7, 80, 48);
		menuItem_1.setIcon(new ImageIcon(refresh));
		panel.add(menuItem_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 893, 589);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 220, 881, 356);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel_1.add(scrollPane);

		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
				if(column==0)
		        {
		        	return true;  
		        }
				return false;  
		    }
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				
				default:
					return String.class;
				}
			}
		};
		    table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
		    table.setRowHeight(40);
		    
		    model.addColumn("S.No"); 
		    model.addColumn("Comments"); 
		    model.addColumn("id"); 
		scrollPane.setViewportView(table);
		
		
		table.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e1) {
	    		if (e1.getKeyChar() == KeyEvent.VK_ENTER) {
	    			int column = 1;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	String selctedcomm=textField_1.getText().trim();
                	selctedcomm=selctedcomm+""+value+", ";
                	textField_1.setText(selctedcomm);
                	textField.requestFocus();
	    		}
	    	}
	    });
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                	int column = 1;
                	int rows = table.getSelectedRow();
                	String value = table.getModel().getValueAt(rows, column).toString();
                	String selctedcomm=textField_1.getText().trim();
                	selctedcomm=selctedcomm+""+value+", ";
                	textField_1.setText(selctedcomm);
                	textField.requestFocus();
                }
            }
        });
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columns=model.getColumnCount();
		for (int i=0; i<columns; i++) {
			  TableColumn column = table.getColumnModel().getColumn(i);
			  if (i==0) column.setPreferredWidth(40);
			  if (i==1) column.setPreferredWidth(400);
			  if (i==2) column.setPreferredWidth(00);
			}
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 114, 881, 52);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }

			  public void warn() {
				  int rowCount = model.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					get_itemdisp( company,textField.getText());
			  }
			});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_itemdisp( company,textField.getText());
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e1) {
				int keycode=e1.getKeyCode();
				if(keycode==40)
				{
					table.requestFocus();
				}
			}
		});
		textField.setBounds(10, 13, 769, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Connection con=Dbsetup.local_server.getConnection();
					Statement statement=con.createStatement();  
					
					String query="SELECT * FROM "+company+".mr_pos_comments where comments='"+textField.getText().trim()+"'  "; 
					ResultSet resultSet = statement.executeQuery(query);
					int rowCount = model.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					if(resultSet.next())
					{
						String query1="SELECT * FROM "+company+".mr_pos_comments where comments='"+textField.getText().trim()+"'  "; 
						ResultSet resultSet1 = statement.executeQuery(query1);
						if(resultSet1.next())
						{
							String comments=resultSet1.getString("comments");
			                model.addRow(new Object[]{table.getRowCount()+1, comments});
						}
					}
					else
					{
//						//JOptionPane.showMessageDialog(frame, "No Invoives List Found");
//			        	for (int i = model.getRowCount() - 1; i >= 0; i--) {
//			        	    model.removeRow(i);
//			        	}
						String query110_1="insert into "+company+".mr_pos_comments (comments) values('"+textField.getText().trim()+"');";
						statement.executeUpdate(query110_1);
						model.addRow(new Object[]{table.getRowCount()+1, textField.getText().trim()});
					}
					textField.setText("");
					get_itemdisp( company,  "");
					
				}
				catch(Exception e){
					System.err.println(e);
				}
			}
		});
		btnEnter.setBounds(784, 13, 85, 25);
		panel_2.add(btnEnter);
		
		JLabel lblSelectedComments = new JLabel("Selected Comments");
		lblSelectedComments.setBounds(12, 13, 141, 16);
		panel_1.add(lblSelectedComments);
		
		JLabel lblSearchComments = new JLabel("Search Comments");
		lblSearchComments.setBounds(12, 97, 141, 16);
		panel_1.add(lblSearchComments);
		
		textField_1 = new JTextField(value);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setEditable(false);
		textField_1.setBounds(12, 42, 780, 52);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_1.setText("");
			}
		});
		btnNewButton.setBounds(796, 42, 85, 52);
		panel_1.add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete Comments");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int rows = table.getSelectedRow();
					if(rows==-1)
					{
						JOptionPane.showMessageDialog(frame, "Select A Row");
					}
					else
					{
						int a= JOptionPane.showConfirmDialog(frame, "Do You Want to Delete This Comments","",JOptionPane.YES_NO_OPTION);
						if(a==0)
						{
							Connection con=Dbsetup.dbconn.getConnection();
							Statement statement=con.createStatement(); 
							String id = table.getModel().getValueAt(rows, 2).toString();
							String delete_pl="delete from "+company+".mr_pos_comments where id ='"+id+"' ";
							statement.executeUpdate(delete_pl);
							JOptionPane.showMessageDialog(frame, "Deleted Successfully");
							get_itemdisp( company,  "");
						}
					}
                	
                	
                	
				}
				catch(Exception ex)
				{
					System.err.println(ex);
				}
			}
		});
		btnDelete.setBounds(12, 182, 192, 25);
		panel_1.add(btnDelete);
		
		
		get_itemdisp( company,  "");
		textField.requestFocus();
	}
	private void  get_itemdisp(String company, String search)
	{
		
		String serch_str = "";
		if(!search.equals(""))
		{
			serch_str="where (Comments LIKE '%"+search+"%' ) ";
		}
		try{
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			
			String query="SELECT * FROM "+company+".mr_pos_comments "+serch_str+"  "; 
			ResultSet resultSet = statement.executeQuery(query);
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			if(resultSet.next())
			{
				String query1="SELECT * FROM "+company+".mr_pos_comments "+serch_str+" "; 
				ResultSet resultSet1 = statement.executeQuery(query1);
				while(resultSet1.next())
				{
					String comments=resultSet1.getString("comments");
	                model.addRow(new Object[]{table.getRowCount()+1, comments,resultSet1.getString("id")});
				}
			}
			else
			{
				//JOptionPane.showMessageDialog(frame, "No Invoives List Found");
	        	for (int i = model.getRowCount() - 1; i >= 0; i--) {
	        	    model.removeRow(i);
	        	}
			}
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}

