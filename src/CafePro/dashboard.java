package CafePro;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.json.JSONException;

import java.awt.SystemColor;

public class dashboard {

	JFrame frame;
	static JLabel lblloader;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dashboard window = new dashboard("mr_pro", null,null, null, null, null, null,"");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param posreg 
	 */
	public dashboard(String company, String version,String dbname,String username,String store,java.sql.Connection con, String posreg,String language) throws JSONException {
		initialize(company, version,dbname,username,store,con,posreg, language);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param posreg 
	 */
	private void initialize(final String company,final String version,final String dbname,final String username,final String store,final java.sql.Connection con, final String posreg,final String language) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 910, 560);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		Image Barcode=new ImageIcon(this.getClass().getResource("/Barcode.png")).getImage();
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		
		Image loader=new ImageIcon(getClass().getResource("/loader.gif")).getImage();
		
		
		lblloader = new JLabel("");
		lblloader.setBounds(277, 361, 38, 38);
		frame.getContentPane().add(lblloader);
		lblloader.setIcon(new ImageIcon(loader));
		lblloader.setVisible(false);
		
		JMenuItem menuItem = new JMenuItem("<html>EXIT<br>„Œ—Ã</html>");
		if(language.equals("Arabic"))
		{
			menuItem.setText("„Œ—Ã");
		}
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setIcon(new ImageIcon(exit));
		menuItem.setBounds(12, 0, 79, 48);
		frame.getContentPane().add(menuItem);
		
		
		JLabel lblNewLabel = new JLabel("DASHBOARD");
		if(language.equals("Arabic"))
		{
			lblNewLabel.setText("·ÊÕ… «·ﬁÌ«œ…");
		}
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(125, 0, 220, 48);
		
		frame.getContentPane().add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("<html>1. BACKUP DATABASE\r\n<br>\r\n\u0642\u0627\u0639\u062F\u0629 \u0628\u064A\u0627\u0646\u0627\u062A \u0627\u062D\u062A\u064A\u0627\u0637\u064A\u0629</html>");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		if(language.equals("Arabic"))
		{
			btnNewButton.setText("1. «·‰”Œ «·«Õ Ì«ÿÌ ·ﬁ«⁄œ… «·»Ì«‰« ");
		}
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String[] command = {"C:\\MasterRetails\\backup\\backupbatch.bat","start"};
				    ProcessBuilder pb = new ProcessBuilder(command);
				    pb.redirectErrorStream(true);
				    Process exec = pb.start();
				    BufferedReader br = new BufferedReader(new InputStreamReader(exec.getInputStream()));
				    String text = null;
				    while ((text = br.readLine()) != null) {
				        System.out.println(text);
				    }
				    JOptionPane.showMessageDialog(frame, "Backup Completed «ﬂ „· «·‰”Œ «·«Õ Ì«ÿÌ" );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(Barcode));
		btnNewButton.setBounds(46, 124, 230, 45);
		frame.getContentPane().add(btnNewButton);

		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 437, 895, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel1 = new JLabel("");
		//lblNewLabel1.setIcon(new ImageIcon(cafepro));
		lblNewLabel1.setBounds(0, 683, 58, 50);
		frame.getContentPane().add(lblNewLabel1);
		
		JLabel lblCurrentUser = new JLabel("CURRENT USER ");
		if(language.equals("Arabic"))
		{
			lblCurrentUser.setText("«·„” Œœ„ «·Õ«·Ì");
		}
		lblCurrentUser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCurrentUser.setBounds(31, 437, 230, 26);
		frame.getContentPane().add(lblCurrentUser);
		
		JLabel lblRegistredToCompany = new JLabel("REGISTERED TO  "+company);
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
		
		JButton btnLoginControls = new JButton("<html>2. LOGIN CONTROLS<br>\u0636\u0648\u0627\u0628\u0637 \u062A\u0633\u062C\u064A\u0644 \u0627\u0644\u062F\u062E\u0648\u0644</html>");
		btnLoginControls.setFont(new Font("Tahoma", Font.BOLD, 13));
		if(language.equals("Arabic"))
		{
			btnLoginControls.setText("2. ⁄‰«’— «· Õﬂ„ ›Ì  ”ÃÌ· «·œŒÊ·");
		}
		btnLoginControls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new login_controls (dbname, version, language);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLoginControls.setHorizontalAlignment(SwingConstants.LEFT);
		btnLoginControls.setBackground(SystemColor.menu);
		btnLoginControls.setBounds(333, 124, 230, 45);
		frame.getContentPane().add(btnLoginControls);
		
		JButton btnUpcomingCheque = new JButton("<html>3. CHEQUE LIST<br>\u0642\u0627\u0626\u0645\u0629 \u0627\u0644\u062A\u062D\u0642\u0642</html>");
		btnUpcomingCheque.setFont(new Font("Tahoma", Font.BOLD, 13));
		if(language.equals("Arabic"))
		{
			btnUpcomingCheque.setText("3. ﬁ«∆„… «·‘Ìﬂ« ");
		}
		btnUpcomingCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					new CafePro.cheque_list (dbname, version);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUpcomingCheque.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpcomingCheque.setBackground(SystemColor.menu);
		btnUpcomingCheque.setBounds(621, 124, 230, 45);
		frame.getContentPane().add(btnUpcomingCheque);
		
		JButton btnMinimumQty_1 = new JButton("<html>4. MINIMUM QTY ITEMS<br>\u0627\u0644\u062D\u062F \u0627\u0644\u0623\u062F\u0646\u0649 \u0644\u0643\u0645\u064A\u0629 \u0627\u0644\u0645\u0646\u062A\u062C\u0627\u062A</html>");
		btnMinimumQty_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		if(language.equals("Arabic"))
		{
			btnMinimumQty_1.setText("4. «·Õœ «·√œ‰Ï ·ﬂ„Ì… «·⁄‰«’—");
		}
		btnMinimumQty_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new min_qty(company, version,dbname,username,language);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnMinimumQty_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnMinimumQty_1.setBackground(SystemColor.menu);
		btnMinimumQty_1.setBounds(46, 210, 230, 45);
		frame.getContentPane().add(btnMinimumQty_1);
		
		JButton btnExportScale = new JButton("<html>5. EXPORT SCALE FILES<br>\u0645\u0646\u062A\u062C\u0627\u062A \u0645\u0648\u0627\u0632\u064A\u0646 \u0627\u0644\u062A\u0635\u062F\u064A\u0631</html>");
		btnExportScale.setFont(new Font("Tahoma", Font.BOLD, 13));
		if(language.equals("Arabic"))
		{
			btnExportScale.setText("5.  ’œÌ— „·›«  «·„ﬁÌ«”");
		}
		btnExportScale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new inventory_master.Scale_items(dbname,version,store);
				} catch (JSONException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnExportScale.setHorizontalAlignment(SwingConstants.LEFT);
		btnExportScale.setBackground(SystemColor.menu);
		btnExportScale.setBounds(333, 210, 230, 45);
		frame.getContentPane().add(btnExportScale);
		
		JButton btnPriceEnquiry = new JButton("<html>6. PRICE ENQUIRY<br>\u0627\u0633\u062A\u0641\u0633\u0627\u0631 \u0639\u0646 \u0627\u0644\u0633\u0639\u0631</html>");
		btnPriceEnquiry.setFont(new Font("Tahoma", Font.BOLD, 13));
		if(language.equals("Arabic"))
		{
			btnPriceEnquiry.setText("6. «·«” ⁄·«„ ⁄‰ «·”⁄—");
		}
		btnPriceEnquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Price_enquiry_dash(dbname, company, username, store, version, con,posreg,language);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
		btnPriceEnquiry.setHorizontalAlignment(SwingConstants.LEFT);
		btnPriceEnquiry.setBackground(SystemColor.menu);
		btnPriceEnquiry.setBounds(621, 210, 230, 45);
		frame.getContentPane().add(btnPriceEnquiry);
		
		JButton button = new JButton("7. SYNC STORE DATABASES");
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		if(language.equals("Arabic"))
		{
			button.setText("7. „“«„‰… ﬁÊ«⁄œ »Ì«‰«  «·„ Ã—");
		}
		button.setVisible(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new sync_store(dbname, company, username, store, version, con,posreg);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBackground(SystemColor.menu);
		button.setBounds(46, 279, 230, 38);
		frame.getContentPane().add(button);
		
		
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
