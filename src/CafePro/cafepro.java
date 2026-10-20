package CafePro;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import org.json.JSONException;






import javax.swing.border.LineBorder;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class cafepro {

	public static JFrame frame;
	 private static int count=0;
	    private static Timer timer1;
	    private JProgressBar progressBar;
	    private JLabel label_2;
	    private JLabel lblCopyrightAl;
	    private String Version = "MR Ver_1.2.1";//check itemmaster qty update in closing 2484 to 2501
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					cafepro window = new cafepro(null,null);
					cafepro.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws JSONException
	 * @throws IOException 
	 */
	public cafepro(String company, String host_name) throws JSONException, IOException {
		initialize(company, host_name);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws JSONException
	 * @throws IOException 
	 */
	private void initialize(final String company, final String host_name)throws JSONException, IOException {
		frame = new JFrame(Version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.setBounds(100, 100, 725, 451);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		Image user = new ImageIcon(this.getClass().getResource("/mrlogo.png")).getImage();
        
        label_2 = new JLabel(Version);
        label_2.setHorizontalAlignment(SwingConstants.RIGHT);
        label_2.setFont(new Font("Monospaced", Font.PLAIN, 14));
        label_2.setBounds(490, 366, 226, 31);
        frame.getContentPane().add(label_2);
        
        lblCopyrightAl = new JLabel("Copyright \u00A9 Al Safi General Trading LLC");
        lblCopyrightAl.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCopyrightAl.setFont(new Font("Arial", Font.PLAIN, 15));
        lblCopyrightAl.setBounds(406, 401, 310, 16);
        frame.getContentPane().add(lblCopyrightAl);
        
        JLabel lblCompanySetup = new JLabel("");
        lblCompanySetup.setBounds(0, -101, 1015, 518);
        frame.getContentPane().add(lblCompanySetup);
        lblCompanySetup.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblCompanySetup.setIcon(new ImageIcon(user));
        
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(102, 51, 204));
        progressBar.setBounds(0, 420, 725, 31);
        frame.getContentPane().add(progressBar);
        progressBar.setEnabled(false);
        progressBar.setValue(10);
        progressBar.setBackground(new Color(255, 255, 255));
        progressBar.setMaximum(100);
        //progressBar.setForeground(new Color(160, 82, 45));
        progressBar.setBorder(new LineBorder(Color.WHITE));
        loadProgressBar(Version);
		
	}
	 private void loadProgressBar(final String Version) {
	        ActionListener al = new ActionListener() {

	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                count=count+2;

	                progressBar.setValue(count);
	                if (count == 100) {

	                	try {
	                		new acces_code(Version);
	                		frame.setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
	                	timer1.stop();
	                }

	            }
	        };
	        timer1 = new Timer(50, al);
	        timer1.start();
	    }
}