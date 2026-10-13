package inventory_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JMenuItem;

import org.json.JSONException;

import system_setups.Item_Tax;
import system_setups.category;
import system_setups.department;
import system_setups.sub_category;
import system_setups.subdepartment;


public class Inventory_Config {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory_Config window = new Inventory_Config("mr_pro","",  null,"S01","");
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
	public Inventory_Config(String company,String dbname, String version, String store,String language) {
		initialize(company, dbname,version,store, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param store 
	 */
	private void initialize(final String company,final String dbname,final String version, final String store,final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		Image Barcode=new ImageIcon(this.getClass().getResource("/Barcode.png")).getImage();
		Image shop1=new ImageIcon(this.getClass().getResource("/shop1.png")).getImage();
		Image Settings=new ImageIcon(this.getClass().getResource("/Setting.png")).getImage();
		Image percentage=new ImageIcon(this.getClass().getResource("/percentage.png")).getImage();
		Image Gift=new ImageIcon(this.getClass().getResource("/Gift.png")).getImage();
		Image SEO=new ImageIcon(this.getClass().getResource("/SEO.png")).getImage();
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBounds(12, 0, 60, 48);
		frame.getContentPane().add(menuItem);
		
		
		JLabel lblNewLabel = new JLabel("5.5 INVENTORY CONTROL");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(25, 61, 220, 26);
		
		frame.getContentPane().add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("1. CATEGORY");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new category(dbname, "", version);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(Barcode));
		btnNewButton.setBounds(46, 124, 230, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnInve = new JButton("2. SUB CATEGORY");
		btnInve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new sub_category(dbname,"","", version);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnInve.setHorizontalAlignment(SwingConstants.LEFT);
		btnInve.setIcon(new ImageIcon(shop1));
		btnInve.setBackground(new Color(240, 240, 240));
		btnInve.setBounds(322, 124, 230, 38);
		frame.getContentPane().add(btnInve);
		
		JButton btnStockCount = new JButton("4. PROMOTION");
		btnStockCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Offer_zone(company,dbname,store,"",version, language);
			}
		});
		btnStockCount.setIcon(new ImageIcon(Gift));
		btnStockCount.setHorizontalAlignment(SwingConstants.LEFT);
		btnStockCount.setBackground(SystemColor.menu);
		btnStockCount.setBounds(46, 206, 230, 38);
		frame.getContentPane().add(btnStockCount);
		
		JButton btnInventoryTransfer = new JButton("5. INVENTORY REPORTS");
		btnInventoryTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new Itemmaster();
			}
		});
		btnInventoryTransfer.setIcon(new ImageIcon(SEO));
		btnInventoryTransfer.setHorizontalAlignment(SwingConstants.LEFT);
		btnInventoryTransfer.setBackground(SystemColor.menu);
		btnInventoryTransfer.setBounds(322, 206, 230, 38);
		frame.getContentPane().add(btnInventoryTransfer);

		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		 
	    frame.setVisible(true);
	
	    frame.setResizable(false);
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 437, 895, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel1 = new JLabel("");
		//lblNewLabel1.setIcon(new ImageIcon(cafepro));
		lblNewLabel1.setBounds(0, 683, 58, 50);
		frame.getContentPane().add(lblNewLabel1);
		
		JLabel lblCurrentUser = new JLabel("CURRENT USER ");
		lblCurrentUser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCurrentUser.setBounds(31, 437, 161, 26);
		frame.getContentPane().add(lblCurrentUser);
		
		JLabel lblRegistredToCompany = new JLabel("REGISTERED TO ");
		lblRegistredToCompany.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegistredToCompany.setBounds(366, 454, 516, 14);
		frame.getContentPane().add(lblRegistredToCompany);
		
		JLabel lblNewLabel_1 = new JLabel(version);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(488, 486, 394, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		final JLabel timeLabel  = new JLabel("");
		timeLabel .setBounds(24, 474, 293, 16);
		frame.getContentPane().add(timeLabel );
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 49, 895, 2);
		frame.getContentPane().add(separator_1);
		
		JButton btnSubCategory = new JButton("3. INVENTORY CONFIGURATION");
		btnSubCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Inventory_control(dbname, version,store, language);
			}
		});
		btnSubCategory.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSubCategory.setIcon(new ImageIcon(Settings));
		btnSubCategory.setHorizontalAlignment(SwingConstants.LEFT);
		btnSubCategory.setBackground(SystemColor.menu);
		btnSubCategory.setBounds(604, 124, 247, 38);
		frame.getContentPane().add(btnSubCategory);
		
		JButton btnItemTaxes = new JButton("6. ITEM TAXES");
		btnItemTaxes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Item_Tax(dbname, version,store, language);
			}
		});
		btnItemTaxes.setIcon(new ImageIcon(percentage));
		btnItemTaxes.setHorizontalAlignment(SwingConstants.LEFT);
		btnItemTaxes.setBackground(SystemColor.menu);
		btnItemTaxes.setBounds(604, 206, 247, 38);
		frame.getContentPane().add(btnItemTaxes);
		
		JButton btnDepartment = new JButton("7. DEPARTMENT");
		btnDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new department(dbname, "", version);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDepartment.setHorizontalAlignment(SwingConstants.LEFT);
		btnDepartment.setIcon(new ImageIcon(percentage));
		btnDepartment.setBackground(SystemColor.menu);
		btnDepartment.setBounds(46, 289, 230, 38);
		frame.getContentPane().add(btnDepartment);
		
		JButton btnSubDepartment = new JButton("8. SUB DEPARTMENT");
		btnSubDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new subdepartment(dbname, "","", version);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSubDepartment.setHorizontalAlignment(SwingConstants.LEFT);
		btnSubDepartment.setIcon(new ImageIcon(percentage));
		btnSubDepartment.setBackground(SystemColor.menu);
		btnSubDepartment.setBounds(322, 289, 230, 38);
		frame.getContentPane().add(btnSubDepartment);
		
		JButton btnBarcodeFormat = new JButton("9. BARCODE FORMAT");
		btnBarcodeFormat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new system_setups.Barcode_Format(dbname,  version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnBarcodeFormat.setHorizontalAlignment(SwingConstants.LEFT);
		btnBarcodeFormat.setBackground(SystemColor.menu);
		btnBarcodeFormat.setBounds(604, 289, 247, 38);
		btnBarcodeFormat.setIcon(new ImageIcon(Barcode));
		frame.getContentPane().add(btnBarcodeFormat);
		
		
		final DateFormat timeFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
        ActionListener timerListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Date date = new Date();
                String time = timeFormat.format(date);
                timeLabel.setText(time);
            }
        };
        Timer timer = new Timer(1000, timerListener);
        timer.setInitialDelay(0);
        timer.start();
	}
}
