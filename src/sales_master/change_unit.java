package sales_master;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;

import CafePro.Mainpage;

public class change_unit {

	private static JFrame frmChangeUnitScreen;
	private static JTextField textField;
	private static JTextField textField_1;
	private JTable table;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static DefaultTableModel model;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					change_unit window = new change_unit("","mr_pro","admin","S01", null,null,"","","");
					window.frmChangeUnitScreen.setVisible(true);
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
	 * @param tbl_sel 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	public change_unit(String db_name,String company, String username, String store_name,String version,Connection con,String itemcode,String qty,String language) throws JSONException, SQLException {
		initialize(db_name,company,username,store_name, version,con,itemcode,qty, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @param itemcode 
	 * @param qty 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String db_name,final String company,final String username,final String store_name,final String version,final Connection con, String itemcode, final String qty,final String language) throws JSONException, SQLException {
		frmChangeUnitScreen = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frmChangeUnitScreen.setIconImage(img);
		frmChangeUnitScreen.setVisible(true);
		frmChangeUnitScreen.getContentPane().setBackground(Color.DARK_GRAY);
		frmChangeUnitScreen.setForeground(Color.RED);
		frmChangeUnitScreen.setTitle("CHANGE UNIT SCREEN");
		frmChangeUnitScreen.setBounds(100, 100, 658, 571);
		frmChangeUnitScreen.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btnchangeUnit.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btnchangeUnit.setEnabled(true);
				}
				
			}
			@Override
		      public void windowIconified(WindowEvent we) {
				frmChangeUnitScreen.setState(JFrame.NORMAL);
		      }
		});
		frmChangeUnitScreen.setAlwaysOnTop(true);
		frmChangeUnitScreen.getContentPane().setLayout(null);
		
		JLabel lblScanBarcode = new JLabel("Scan Barcode / Type Name:");
		lblScanBarcode.setBounds(10, 11, 194, 25);
		lblScanBarcode.setForeground(new Color(100, 149, 237));
		lblScanBarcode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmChangeUnitScreen.getContentPane().add(lblScanBarcode);
		
		textField = new JTextField(itemcode);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				get_item_bsd_name(company,textField.getText());
			}
		});
		
		textField.setBounds(222, 13, 374, 25);
		frmChangeUnitScreen.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel lblItemName = new JLabel("Item Name:");
		lblItemName.setBounds(10, 47, 187, 25);
		lblItemName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemName.setForeground(new Color(100, 149, 237));
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmChangeUnitScreen.getContentPane().add(lblItemName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(222, 47, 410, 25);
		textField_1.setColumns(10);
		frmChangeUnitScreen.getContentPane().add(textField_1);
		
		JLabel lblItemQtyAvailable = new JLabel("Item Qty. Available:");
		lblItemQtyAvailable.setBounds(10, 83, 187, 25);
		lblItemQtyAvailable.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemQtyAvailable.setForeground(new Color(100, 149, 237));
		lblItemQtyAvailable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmChangeUnitScreen.getContentPane().add(lblItemQtyAvailable);
		
		textField_2 = new JTextField();
		textField_2.setBounds(222, 83, 86, 25);
		textField_2.setColumns(10);
		frmChangeUnitScreen.getContentPane().add(textField_2);
		
		JLabel lblLastPoCost = new JLabel("Vendor:");
		lblLastPoCost.setBounds(318, 83, 59, 25);
		lblLastPoCost.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastPoCost.setForeground(new Color(100, 149, 237));
		lblLastPoCost.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmChangeUnitScreen.getContentPane().add(lblLastPoCost);
		
		textField_3 = new JTextField();
		textField_3.setBounds(387, 83, 245, 25);
		textField_3.setColumns(10);
		frmChangeUnitScreen.getContentPane().add(textField_3);
		
		JLabel lblNoteDoubleClick = new JLabel("Note: Double click on the unit to select for sales");
		lblNoteDoubleClick.setBounds(10, 119, 622, 25);
		lblNoteDoubleClick.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoteDoubleClick.setForeground(new Color(255, 255, 0));
		lblNoteDoubleClick.setFont(new Font("Arial", Font.BOLD, 16));
		frmChangeUnitScreen.getContentPane().add(lblNoteDoubleClick);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 149, 622, 315);
		frmChangeUnitScreen.getContentPane().add(scrollPane);
		
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
		table.setRowHeight(25);
		//table.setBorder(new LineBorder(Color.WHITE));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Arial", Font.BOLD, 15));
		
		model.addColumn("S.No.");
	    model.addColumn("Barcode"); 
	    model.addColumn("Unit");
	    model.addColumn("CF");
	    model.addColumn("Selling price");
		
	    table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table_2 = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table_2.getSelectedRow() != -1) {
					//int column = 1;
					int rows = table_2.getSelectedRow();
					//String value = table_2.getModel().getValueAt(rows, column).toString();
					Connection con=Dbsetup.dbconn.getConnection();
					if(Mainpage.SoftLang.equals("Ar"))
					{
						if(PosscreenArabic.table.getSelectedRow()>=0)
						{
							sales_master.PosscreenArabic.model.removeRow(PosscreenArabic.table.getSelectedRow());
						}
						String type="";
						if(Double.parseDouble(qty)<0)
						{
							type="return";
						}
						sales_master.PosscreenArabic.validate_barcode(company, table.getModel().getValueAt(rows, 1).toString(), username,store_name, type,con);
					}
					else
					{
						if(posscreen.table.getSelectedRow()>=0)
						{
							sales_master.posscreen.model.removeRow(posscreen.table.getSelectedRow());
						}
						String type="";
						if(Double.parseDouble(qty)<0)
						{
							type="return";
						}
						sales_master.posscreen.validate_barcode(company, table.getModel().getValueAt(rows, 1).toString(), username,store_name, type,con);
					}
					
					
					frmChangeUnitScreen.setVisible(false);
					
					}
				}
		});
	    
	    scrollPane.setViewportView(table);
	    
		JButton button = new JButton("CLEAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				model.setRowCount(0);
			}
		});
		button.setBounds(10, 475, 135, 38);
		frmChangeUnitScreen.getContentPane().add(button);
		
		JButton button_1 = new JButton("EXIT");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmChangeUnitScreen.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btnchangeUnit.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btnchangeUnit.setEnabled(true);
				}
			}
		});
		button_1.setBounds(497, 475, 135, 38);
		frmChangeUnitScreen.getContentPane().add(button_1);
		
		Image serch=new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		JButton btnF = new JButton("");
		btnF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new inventory_master.item_list_master(db_name,company,"unit_change",store_name, version,0,con,"10",language);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnF.setBounds(595, 12, 37, 25);
		btnF.setIcon(new ImageIcon(serch));
		frmChangeUnitScreen.getContentPane().add(btnF);
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(49);
		table.getColumnModel().getColumn(1).setPreferredWidth(189);
		get_item_bsd_name(company,itemcode);
	}

	public static void  get_item_bsd_name(String company,String item_name)
	{
		try {
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
			Statement statement1=con.createStatement();  
			String query="SELECT * FROM "+company+".mr_tmp_item_master where item_code='"+item_name+"' or item_name='"+item_name+"' or barcode='"+item_name+"'";
			ResultSet resultSet11= statement.executeQuery(query);
			int rowCount = model.getRowCount();
			
			if(resultSet11.next())
			{
				String code=resultSet11.getString("item_code");
				String query11="SELECT * FROM "+company+".mr_tmp_item_master where item_code='"+code+"' ";
				ResultSet resultSet= statement.executeQuery(query11);
				for (int x = rowCount - 1; x >= 0; x--) {
					model.removeRow(x);
				}
				while(resultSet.next())
				{
					textField.setText(resultSet.getString("item_code"));
					textField_1.setText(resultSet.getString("item_name"));
		        	textField_2.setText(resultSet.getString("qty"));
		        	if(!resultSet.getString("barcode").equals("") )
					{
						model.addRow(new Object[]{model.getRowCount()+1,resultSet.getString("barcode"),resultSet.getString("unit"),resultSet.getString("cf"),resultSet.getString("sp")});
					}
		        	String query1="select * from "+company+".mr_vendor_items where ref_item_id='"+resultSet.getString("item_code")+"' ";
		        	ResultSet resultSet1= statement1.executeQuery(query1);
					if(resultSet1.next())
					{
						String query2="select vendor_name from "+company+".mr_vendor where vendor_code='"+resultSet1.getString("ref_vendor_id")+"' ";
			        	ResultSet resultSet2= statement1.executeQuery(query2);
						if(resultSet2.next())
						{
							textField_3.setText(resultSet2.getString("vendor_name"));
						}
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frmChangeUnitScreen, "NO ITEMS FOUND");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				model.setRowCount(0);
			}
			
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
}
