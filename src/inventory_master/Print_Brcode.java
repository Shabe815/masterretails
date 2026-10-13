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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Print_Brcode {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Print_Brcode window = new Print_Brcode(null);
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
	public Print_Brcode(String version) {
		initialize(version);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String version) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("7. PRINT BARCODE");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(25, 61, 220, 26);
		
		frame.getContentPane().add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("1. ITEM BARCODE");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new Itemmaster();
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\abdullasuhail\\Desktop\\caferpro icons\\Barcode-icon.png"));
		btnNewButton.setBounds(46, 124, 230, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnInve = new JButton("2. RANGE ITEM BARCODE");
		btnInve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new Itemmaster();
			}
		});
		btnInve.setHorizontalAlignment(SwingConstants.LEFT);
		btnInve.setIcon(new ImageIcon("C:\\Users\\abdullasuhail\\Desktop\\caferpro icons\\store-icon (1).png"));
		btnInve.setBackground(new Color(240, 240, 240));
		btnInve.setBounds(322, 124, 230, 38);
		frame.getContentPane().add(btnInve);

		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		 
	    frame.setVisible(true);
	
	    frame.setResizable(false);
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
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
		
		JLabel lblNewLabel_1 = new JLabel(" Cafepro Version 1.1.0");
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
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\abdullasuhail\\Desktop\\caferpro icons\\logout-icon (1).png"));
		btnNewButton_1.setBounds(10, 11, 93, 33);
		frame.getContentPane().add(btnNewButton_1);
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
