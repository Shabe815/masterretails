package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.swing.JPanel;

import org.json.JSONException;

public class company_setup {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtaddrs1;
	private JTextField txtaddrs2;
	private JTextField txtaddrs3;
	private JTextField txtaddrs4;
	private JTextField txtaddrs5;
	private JTextField txtph;
	private JTextField txtweb;
	private JTextField txtemail;
	private JTextField txttrn;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					company_setup window = new company_setup(null, "Master Retails");
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
	 * @throws JSONException 
	 */
	public company_setup(String version,String company_name) throws JSONException {
		initialize(version,company_name);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param company 
	 * @param host_name 
	 * @throws JSONException 
	 */
	private JTextField txtHidenimagepath;
	private void initialize(final String version, final String company_name) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(470, 70, 2, 356);
		frame.getContentPane().add(separator_1);
		
		JLabel lblCompanyName = new JLabel("Company Name :");
		lblCompanyName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCompanyName.setBounds(21, 79, 102, 21);
		frame.getContentPane().add(lblCompanyName);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(21, 111, 102, 21);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblAddress_1 = new JLabel("Address 2 :");
		lblAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress_1.setBounds(21, 143, 102, 21);
		frame.getContentPane().add(lblAddress_1);
		
		JLabel lblAddress_2 = new JLabel("Address 3 :");
		lblAddress_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress_2.setBounds(21, 175, 102, 21);
		frame.getContentPane().add(lblAddress_2);
		
		JLabel lblAddress_3 = new JLabel("City :");
		lblAddress_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress_3.setBounds(21, 207, 102, 21);
		frame.getContentPane().add(lblAddress_3);
		
		JLabel lblAddress_4 = new JLabel("Country :");
		lblAddress_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress_4.setBounds(21, 239, 102, 21);
		frame.getContentPane().add(lblAddress_4);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number :");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPhoneNumber.setBounds(21, 271, 102, 21);
		frame.getContentPane().add(lblPhoneNumber);
		
		JLabel lblWebsiteAddress = new JLabel("Web Address :");
		lblWebsiteAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWebsiteAddress.setBounds(21, 303, 102, 21);
		frame.getContentPane().add(lblWebsiteAddress);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(21, 335, 102, 21);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblTrn = new JLabel("TRN :");
		lblTrn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTrn.setBounds(21, 367, 102, 21);
		frame.getContentPane().add(lblTrn);
		
		Image shop=new ImageIcon(this.getClass().getResource("/shop.png")).getImage();
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JLabel lblCompanySetup = new JLabel("Company Setup");
		lblCompanySetup.setIcon(new ImageIcon(shop));
		lblCompanySetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCompanySetup.setBounds(21, 11, 228, 44);
		frame.getContentPane().add(lblCompanySetup);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBounds(470, 11, 60, 48);
		mntmNewMenuItem.setBorder(null);
		frame.getContentPane().add(mntmNewMenuItem);
		
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				try {
					new company_setup(version, company_name);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mntmRefresh.setBackground(new Color(243, 229, 216));
		mntmRefresh.setIcon(new ImageIcon(refresh));
		mntmRefresh.setBounds(530, 11, 80, 48);
		mntmRefresh.setBorder(null);
		frame.getContentPane().add(mntmRefresh);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String addr1=txtaddrs1.getText();
				String addr2=txtaddrs2.getText();
				String addr3=txtaddrs3.getText();
				String addr4=txtaddrs4.getText();
				String addr5=txtaddrs5.getText();
				String web=txtweb.getText();
				String email=txtemail.getText();
				String trn=txttrn.getText();
				String ph=txtph.getText();
				String image=txtHidenimagepath.getText();
				LocalDateTime myDateObj = LocalDateTime.now();  
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			    String formattedDate = myDateObj.format(myFormatObj);  
			    Connection con=Dbsetup.dbconn.getConnection();
				Statement statement;
				try {
					statement = con.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from config_db.company where company_name='"+company_name+"' ");
					if(resultSet.next())
					{
						if(!image.equals(""))
						{
							File configFile = new File("C:/MasterRetails/config.properties");
							FileReader reader = new FileReader(configFile);
						    Properties props = new Properties();
						    props.load(reader);
						    String imageurl = props.getProperty("image");
						    reader.close();
						    Path temp = Files.copy(Paths.get(image),Paths.get("\\"+imageurl+"\\logo.jpg") , StandardCopyOption.REPLACE_EXISTING);
						    if(temp == null) 
					        { 
					            System.out.println("Image Uploading Failed, Please Try Again"); 
					        } 
						    else
						    {
						    	 String query="update config_db.company set addr1='"+addr1+"',addr2='"+addr2+"',addr3='"+addr3+"',city='"+addr4+"',country='"+addr5+"',web='"+web+"',ph='"+ph+"',email='"+email+"',trn='"+trn+"',image='\\"+imageurl+"\\logo.jpg', `modified_date`='"+formattedDate+"' WHERE id='"+resultSet.getString("id")+"' ";
								 statement.executeUpdate(query);
								 JOptionPane.showMessageDialog(frame, "Saved Successfully");
						    }
						}
						else
						{
							String query="update config_db.company set addr1='"+addr1+"',addr2='"+addr2+"',addr3='"+addr3+"',city='"+addr4+"',country='"+addr5+"',web='"+web+"',ph='"+ph+"',email='"+email+"',trn='"+trn+"',`modified_date`='"+formattedDate+"' WHERE id='"+resultSet.getString("id")+"' ";
							 statement.executeUpdate(query);
							 JOptionPane.showMessageDialog(frame, "Saved Successfully");
						}
						
					}
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
				
			}
		});
		mntmSave.setBackground(new Color(243, 229, 216));
		mntmSave.setIcon(new ImageIcon(save));
		mntmSave.setBorder(null);
		mntmSave.setBounds(612, 11, 70, 48);
		frame.getContentPane().add(mntmSave);
		
		textField = new JTextField(company_name);
		textField.setEditable(false);
		textField.setBounds(124, 80, 320, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtaddrs1 = new JTextField();
		txtaddrs1.setBounds(124, 111, 320, 20);
		frame.getContentPane().add(txtaddrs1);
		txtaddrs1.setColumns(10);
		
		txtaddrs2 = new JTextField();
		txtaddrs2.setColumns(10);
		txtaddrs2.setBounds(124, 144, 320, 20);
		frame.getContentPane().add(txtaddrs2);
		
		txtaddrs3 = new JTextField();
		txtaddrs3.setColumns(10);
		txtaddrs3.setBounds(124, 176, 320, 20);
		frame.getContentPane().add(txtaddrs3);
		
		txtaddrs4 = new JTextField();
		txtaddrs4.setColumns(10);
		txtaddrs4.setBounds(124, 207, 320, 20);
		frame.getContentPane().add(txtaddrs4);
		
		txtaddrs5 = new JTextField();
		txtaddrs5.setText("UAE");
		txtaddrs5.setColumns(10);
		txtaddrs5.setBounds(124, 240, 320, 20);
		frame.getContentPane().add(txtaddrs5);
		
		txtph = new JTextField();
		txtph.setColumns(10);
		txtph.setBounds(124, 272, 320, 20);
		frame.getContentPane().add(txtph);
		
		txtweb = new JTextField();
		txtweb.setText("www.example.com");
		txtweb.setColumns(10);
		txtweb.setBounds(124, 304, 320, 20);
		frame.getContentPane().add(txtweb);
		
		txtemail = new JTextField();
		txtemail.setText("example@gmail.com");
		txtemail.setColumns(10);
		txtemail.setBounds(124, 336, 320, 20);
		frame.getContentPane().add(txtemail);
		
		txttrn = new JTextField();
		txttrn.setColumns(10);
		txttrn.setBounds(124, 367, 320, 20);
		frame.getContentPane().add(txttrn);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(480, 79, 214, 323);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(30, 27, 157, 113);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		lblNewLabel.setBorder(border);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Upload Logo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser file = new JFileChooser();
		          file.setCurrentDirectory(new File(System.getProperty("user.home")));
		          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
		          file.addChoosableFileFilter(filter);
		          file.setFileFilter(filter);
		          file.setMultiSelectionEnabled(false);
		          int result = file.showSaveDialog(null);
		          if(result == JFileChooser.APPROVE_OPTION){
		              File selectedFile = file.getSelectedFile();
		              String path = selectedFile.getAbsolutePath();
		              lblNewLabel.setIcon(ResizeImage(path));
		              txtHidenimagepath.setText(path);
		          }
			}
		});
		btnNewButton.setBounds(30, 168, 157, 35);
		panel.add(btnNewButton);
		
		txtHidenimagepath = new JTextField();
		txtHidenimagepath.setEditable(false);
		txtHidenimagepath.setVisible(false);
		txtHidenimagepath.setBounds(28, 216, 116, 22);
		panel.add(txtHidenimagepath);
		txtHidenimagepath.setColumns(10);
		
		
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		get_storedisp(company_name);
	}
	private void get_storedisp(String company_name)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from config_db.company where company_name='"+company_name+"' ");
			if(resultSet.next())
			{
				textField.setText(resultSet.getString("company_name"));
	        	txtaddrs1.setText(resultSet.getString("addr1"));
	        	txtaddrs2.setText(resultSet.getString("addr2"));
	        	txtaddrs3.setText(resultSet.getString("addr3"));
	        	txtaddrs4.setText(resultSet.getString("city"));
	        	txtaddrs5.setText(resultSet.getString("country"));
	        	txtweb.setText(resultSet.getString("web"));
	        	txtph.setText(resultSet.getString("ph"));
	        	txtemail.setText(resultSet.getString("email"));
	        	txttrn.setText(resultSet.getString("trn"));
	        	String dbimg=resultSet.getString("image");
	        	if(dbimg!="null")
	        	{
	        		lblNewLabel.setIcon(ResizeImage(dbimg));
	        	}
	        	else
	        	{
	        		lblNewLabel.setIcon(ResizeImage(""));
	        	}
	        	
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
}
