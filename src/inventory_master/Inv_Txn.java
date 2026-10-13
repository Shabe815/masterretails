package inventory_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
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

public class Inv_Txn {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inv_Txn window = new Inv_Txn(null, null, null, null, null, null,"");
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
	public Inv_Txn(String db_name,String company,String host_name,String username,String store,String version,String language) {
		initialize( db_name,company, host_name, username, store, version,language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param language 
	 */
	private void initialize(final String db_name,final String company,final String host_name,final String username,final String store,final String version,final  String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image barcode=new ImageIcon(this.getClass().getResource("/Barcode.png")).getImage();
		Image Store=new ImageIcon(this.getClass().getResource("/store.png")).getImage();
		
		
		
		
				
		JLabel lblNewLabel = new JLabel("2. INVENTORY TRANSACTION");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(25, 61, 220, 26);
		
		frame.getContentPane().add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("1. ADJUSTMENTS");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new inventory_master.Inv_adjust_list(db_name,company, username, store, version,"",language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(barcode));
		btnNewButton.setBounds(46, 124, 230, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnInve = new JButton("2. MANAGE BATCH/SERIAL");
		btnInve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new Itemmaster();
			}
		});
		btnInve.setHorizontalAlignment(SwingConstants.LEFT);
		btnInve.setIcon(new ImageIcon(Store));
		btnInve.setBackground(new Color(240, 240, 240));
		btnInve.setBounds(322, 124, 230, 38);
		frame.getContentPane().add(btnInve);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 437, 1018, 2);
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
		lblRegistredToCompany.setBounds(280, 456, 516, 14);
		frame.getContentPane().add(lblRegistredToCompany);
		
		JLabel lblNewLabel_1 = new JLabel(version);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(402, 481, 394, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		final JLabel timeLabel  = new JLabel("");
		timeLabel .setBounds(24, 474, 293, 16);
		frame.getContentPane().add(timeLabel );
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 49, 1018, 2);
		frame.getContentPane().add(separator_1);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBounds(-2, 0, 60, 48);
		frame.getContentPane().add(menuItem);
		
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
