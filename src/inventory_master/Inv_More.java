package inventory_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.json.JSONException;

import system_setups.category;
import system_setups.department;
import system_setups.sub_category;
import system_setups.subdepartment;

public class Inv_More {

	static JFrame frame;
	static JLabel lblloader;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Inv_More window = new Inv_More("mr_pro","", null, null,"","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param host_name 
	 * @param company 
	 */
	public Inv_More(String company, String version,String store, String username,String company_name,String language) throws JSONException {
		initialize(company, version,store,username,company_name, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @param username 
	 * @param store 
	 * @param company_name 
	 */
	private void initialize(final String company,final String version,final String store,final String username, final String company_name,final String language) {
		frame = new JFrame("ver_"+version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 546);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		Image Barcode=new ImageIcon(this.getClass().getResource("/Barcode.png")).getImage();
		Image shop1=new ImageIcon(this.getClass().getResource("/shop1.png")).getImage();
		//Image Settings=new ImageIcon(this.getClass().getResource("/Setting.png")).getImage();
		//Image percentage=new ImageIcon(this.getClass().getResource("/percentage.png")).getImage();
		Image dept=new ImageIcon(this.getClass().getResource("/department.png")).getImage();
		Image sub_dept=new ImageIcon(this.getClass().getResource("/sub_department.png")).getImage();
		Image Gift=new ImageIcon(this.getClass().getResource("/Gift.png")).getImage();
		Image SEO=new ImageIcon(this.getClass().getResource("/SEO.png")).getImage();
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		
		Image loader=new ImageIcon(getClass().getResource("/loader.gif")).getImage();
		
		
		lblloader = new JLabel("");
		lblloader.setBounds(277, 361, 38, 38);
		frame.getContentPane().add(lblloader);
		lblloader.setIcon(new ImageIcon(loader));
		lblloader.setVisible(false);
		
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
					new category(company,"", version);
				} catch (JSONException | SQLException e) {
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
					new sub_category(company, "","", version);
				} catch (JSONException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnInve.setHorizontalAlignment(SwingConstants.LEFT);
		btnInve.setIcon(new ImageIcon(shop1));
		btnInve.setBackground(new Color(240, 240, 240));
		btnInve.setBounds(322, 124, 230, 38);
		frame.getContentPane().add(btnInve);
		
		JButton btnStockCount = new JButton("5. PROMOTION");
		btnStockCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new inventory_master.Promotion(version);
				new Offer_zone(company,company,store,"",version, language);
			}
		});
		btnStockCount.setIcon(new ImageIcon(Gift));
		btnStockCount.setHorizontalAlignment(SwingConstants.LEFT);
		btnStockCount.setBackground(SystemColor.menu);
		btnStockCount.setBounds(322, 206, 230, 38);
		frame.getContentPane().add(btnStockCount);
		
		JButton btnInventoryTransfer = new JButton("6. INVENTORY REPORTS");
		btnInventoryTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new inventory_master.Inventory_Reports(version,company,store,username,company_name, language);
			}
		});
		btnInventoryTransfer.setIcon(new ImageIcon(SEO));
		btnInventoryTransfer.setHorizontalAlignment(SwingConstants.LEFT);
		btnInventoryTransfer.setBackground(SystemColor.menu);
		btnInventoryTransfer.setBounds(604, 206, 230, 38);
		frame.getContentPane().add(btnInventoryTransfer);

		
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
		
		JLabel lblRegistredToCompany = new JLabel("REGISTERED TO "+company_name);
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
		
		JButton btnSubCategory = new JButton("10. Assembly of Items");
		btnSubCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new inventory_master.assembly_item(company, username, store, version, "2");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSubCategory.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSubCategory.setIcon(new ImageIcon(sub_dept));
		btnSubCategory.setHorizontalAlignment(SwingConstants.LEFT);
		btnSubCategory.setBackground(SystemColor.menu);
		btnSubCategory.setBounds(46, 361, 230, 38);
		frame.getContentPane().add(btnSubCategory);
		
		JButton btnDepartment = new JButton("3. DEPARTMENT");
		btnDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new department(company, "", version);
				} catch (JSONException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnDepartment.setHorizontalAlignment(SwingConstants.LEFT);
		btnDepartment.setIcon(new ImageIcon(dept));
		btnDepartment.setBackground(SystemColor.menu);
		btnDepartment.setBounds(604, 124, 230, 38);
		frame.getContentPane().add(btnDepartment);
		
		JButton btnSubDepartment = new JButton("4. SUB DEPARTMENT");
		btnSubDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new subdepartment(company,"","", version);
				} catch (JSONException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnSubDepartment.setHorizontalAlignment(SwingConstants.LEFT);
		btnSubDepartment.setIcon(new ImageIcon(sub_dept));
		btnSubDepartment.setBackground(SystemColor.menu);
		btnSubDepartment.setBounds(46, 206, 230, 38);
		frame.getContentPane().add(btnSubDepartment);
		
		JButton btnpricing = new JButton("11. Create Auto Items");
		btnpricing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new inventory_master.create_item_utility(company,company,username,store, version);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnpricing.setHorizontalAlignment(SwingConstants.LEFT);
		btnpricing.setBackground(SystemColor.menu);
		btnpricing.setBounds(322, 361, 230, 38);
		frame.getContentPane().add(btnpricing);
		
		
		JButton btnExportScaleItems = new JButton("9. Export Scale Items as CSV");
		btnExportScaleItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new inventory_master.Scale_items(company,version,store);
				} catch (JSONException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnExportScaleItems.setHorizontalAlignment(SwingConstants.LEFT);
		btnExportScaleItems.setBackground(SystemColor.menu);
		btnExportScaleItems.setBounds(604, 289, 230, 38);
		frame.getContentPane().add(btnExportScaleItems);
		
		JButton btnImportItemsFrom = new JButton("7. Import Items from CSV");
		btnImportItemsFrom.setToolTipText("CSV file must be in Export folder, file name must be itemmaster.csv");
		btnImportItemsFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					inventory_master.Import_csv.load( company);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnImportItemsFrom.setHorizontalAlignment(SwingConstants.LEFT);
		btnImportItemsFrom.setBackground(SystemColor.menu);
		btnImportItemsFrom.setBounds(46, 289, 230, 38);
		frame.getContentPane().add(btnImportItemsFrom);
		
		JButton btnImportCustomersFrom = new JButton("8. Import Customers from CSV");
		btnImportCustomersFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					inventory_master.import_customer.load( company);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnImportCustomersFrom.setToolTipText("CSV file must be in Export folder, file name must be customer_list.csv");
		btnImportCustomersFrom.setHorizontalAlignment(SwingConstants.LEFT);
		btnImportCustomersFrom.setBackground(SystemColor.menu);
		btnImportCustomersFrom.setBounds(322, 289, 230, 38);
		frame.getContentPane().add(btnImportCustomersFrom);
		
		
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
