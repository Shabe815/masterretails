package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

import CafePro.Mainpage;

import com.sun.glass.events.KeyEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class price_enquiry {

	private static JFrame frmChangeUnitScreen;
	private static JTextField textField;
	private static JTextField textField_1;
	private JTable table;
	private static JTextField textField_2;
	private static DefaultTableModel model;
	private JButton button;
	private JButton button_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					price_enquiry window = new price_enquiry("","mr_pro","admin","S01", null,null,"");
					price_enquiry.frmChangeUnitScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public price_enquiry(String db_name,String company, String username, String store_name,String version,Connection con,String language) throws JSONException, SQLException {
		initialize(db_name,company,username,store_name, version,con, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param store_name 
	 * @param username 
	 * @param host_name 
	 * @param company 
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	private void initialize(final String db_name,final String company,final String username,final String store_name,final String version,final Connection con,final String language) throws JSONException, SQLException {
		frmChangeUnitScreen = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frmChangeUnitScreen.setIconImage(img);
		frmChangeUnitScreen.setVisible(true);
		frmChangeUnitScreen.getContentPane().setBackground(Color.DARK_GRAY);
		frmChangeUnitScreen.setForeground(Color.RED);
		frmChangeUnitScreen.setTitle("PRICE ENQUIRY");
		frmChangeUnitScreen.setBounds(100, 100, 658, 571);
		frmChangeUnitScreen.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btnQuickReturn.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btnQuickReturn.setEnabled(true);
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
		lblScanBarcode.setForeground(Color.YELLOW);
		lblScanBarcode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmChangeUnitScreen.getContentPane().add(lblScanBarcode);
		
		textField = new JTextField();
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
		lblItemName.setForeground(Color.YELLOW);
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmChangeUnitScreen.getContentPane().add(lblItemName);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(222, 47, 410, 25);
		textField_1.setColumns(10);
		frmChangeUnitScreen.getContentPane().add(textField_1);
		
		JLabel lblItemQtyAvailable = new JLabel("Item Qty. Available:");
		lblItemQtyAvailable.setBounds(10, 83, 187, 25);
		lblItemQtyAvailable.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemQtyAvailable.setForeground(Color.YELLOW);
		lblItemQtyAvailable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmChangeUnitScreen.getContentPane().add(lblItemQtyAvailable);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(222, 83, 149, 25);
		textField_2.setColumns(10);
		frmChangeUnitScreen.getContentPane().add(textField_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 622, 343);
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
		table.setShowGrid(true);
		table.setRowHeight(25);
		//table.setBorder(new LineBorder(Color.WHITE));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Arial", Font.BOLD, 15));
		
		model.addColumn("S.No.");
	    model.addColumn("Barcode"); 
	    model.addColumn("Unit"); 
	    model.addColumn("Selling price");
	    model.addColumn("C.F");
		
	    
	    scrollPane.setViewportView(table);
	    
	    button = new JButton("CLEAR");
		button.setToolTipText("F5");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				model.setRowCount(0);
				textField.requestFocus();
			}
		});
		button.setBounds(10, 475, 135, 38);
		frmChangeUnitScreen.getContentPane().add(button);
		
		button_1 = new JButton("EXIT");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmChangeUnitScreen.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
					PosscreenArabic.btnQuickReturn.setEnabled(true);
				}
				else
				{
					posscreen.txtDisc.requestFocus();
					posscreen.btnQuickReturn.setEnabled(true);
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
		clickOnrefresh( button, "play", KeyEvent.VK_F5 );
		clickOnexit( button_1, "play", KeyEvent.VK_F4 );
		clickOnback( button_1, "play", KeyEvent.VK_ESCAPE );
	}
	public static void clickOnback(final AbstractButton button, String actionName, int key)
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
				frmChangeUnitScreen.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
				}
				else
				{
					posscreen.txtDisc.requestFocus();
				}
				
	        }
	    } );
	}
	public static void clickOnrefresh(final AbstractButton button, String actionName, int key)
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
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				model.setRowCount(0);
				textField.requestFocus();
	        }
	    } );
	}
	public static void clickOnexit(final AbstractButton button, String actionName, int key)
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
				frmChangeUnitScreen.setVisible(false);
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.txtDisc.requestFocus();
				}
				else
				{
					posscreen.txtDisc.requestFocus();
				}
				
	        }
	    } );
	}
	public static void  get_item_bsd_name(String company,String item_name)
	{
		try {
			Connection con=Dbsetup.local_server.getConnection();
			Statement statement=con.createStatement();  
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
						model.addRow(new Object[]{model.getRowCount()+1,resultSet.getString("barcode"),resultSet.getString("unit"),resultSet.getString("sp"),resultSet.getString("cf")});
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frmChangeUnitScreen, "NO ITEMS FOUND");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				model.setRowCount(0);
			}
			
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
}
