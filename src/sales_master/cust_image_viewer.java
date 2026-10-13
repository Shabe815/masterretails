package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.json.JSONException;

import javax.swing.JSeparator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

public class cust_image_viewer {

	private JFrame frame;
	private JLabel lblImages;
	private JLabel label;
	private JLabel label_1;
	private int i;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cust_image_viewer window = new cust_image_viewer("mr_pro","","","", "A0001");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param company 
	 * @param host_name 
	 * @param page_type 
	 * @param username 
	 * @param store_name 
	 * @param total 
	 * @param old_recipt 
	 * @param sts 
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public cust_image_viewer(String company, String username, String store_name,String version, String cust_id) throws JSONException, IOException {
		initialize(company,username,store_name,version,cust_id);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param company 
	 * @param host_name 
	 * @param page_type 
	 * @param username 
	 * @param cust_id 
	 * @param version 
	 * @param store_name 
	 * @param store_name 
	 * @throws JSONException 
	 * @throws IOException 
	 */
	private void initialize(final String company, final String username, String store_name, String version, final String cust_id) throws JSONException, IOException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 551, 458);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		i=1;
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 534, 411);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblShifts = new JLabel("Customer Images");
		lblShifts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShifts.setBounds(10, 11, 228, 44);
		panel.add(lblShifts);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(439, 7, 83, 48);
		panel.add(mntmNewMenuItem);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 68, 534, 2);
		panel.add(separator);
		
		lblImages = new JLabel("No Images Found");
		lblImages.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblImages.setHorizontalAlignment(SwingConstants.CENTER);
		lblImages.setBounds(80, 83, 384, 295);
		panel.add(lblImages);
		
		final JLabel label = new JLabel("<");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
			    	i=i-1;
			    	File configFile = new File("C:/MasterRetails/config.properties");
					FileReader reader = new FileReader(configFile);
				    Properties props = new Properties();
					props.load(reader);
					String imageurl = props.getProperty("image");
				    reader.close();
				    File path=new File( imageurl+"\\"+cust_id+"-"+i+".jpg");
				    if(path.exists())
				    {
				    	lblImages.setIcon(ResizeImage(imageurl+"\\"+cust_id+"-"+i+".jpg"));
				    	label.setVisible(true);
				    	label_1.setVisible(true);
				    }
				    else
				    {
				    	label.setVisible(false);
				    }
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		label.setFont(new Font("Tahoma", Font.BOLD, 50));
		label.setBounds(12, 205, 45, 45);
		panel.add(label);
		
		final JLabel label_1 = new JLabel(">");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			    try {
			    	i=i+1;
			    	File configFile = new File("C:/MasterRetails/config.properties");
					FileReader reader = new FileReader(configFile);
				    Properties props = new Properties();
					props.load(reader);
					String imageurl = props.getProperty("image");
				    reader.close();
				    File path=new File( imageurl+"\\"+cust_id+"-"+i+".jpg");
				    if(path.exists())
				    {
				    	lblImages.setIcon(ResizeImage(imageurl+"\\"+cust_id+"-"+i+".jpg"));
				    	label_1.setVisible(true);
				    	label.setVisible(true);
				    }
				    else
				    {
				    	label_1.setVisible(false);
				    }
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			}
		});
		label_1.setFont(new Font("Tahoma", Font.BOLD, 50));
		label_1.setBounds(476, 205, 45, 45);
		panel.add(label_1);
		
		get_imagedisp( company, cust_id);
	}
	private void get_imagedisp(String company, String cust_id) throws IOException
	{
		File configFile = new File("C:/MasterRetails/config.properties");
		FileReader reader = new FileReader(configFile);
	    Properties props = new Properties();
	    props.load(reader);
	    String imageurl = props.getProperty("image");
	    reader.close();
	    File path=File.createTempFile( imageurl,""+cust_id+"-"+i+".jpg");
	    if(path.exists())
	    {
	    	lblImages.setIcon(ResizeImage(imageurl+"\\"+cust_id+"-"+i+".jpg"));
	    }
	    else
	    {
	    	label_1.setVisible(false);
	    	label.setVisible(false);
	    }
		
	}
	public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblImages.getWidth(), lblImages.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
}
