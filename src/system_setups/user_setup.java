package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONException;

import system_setups.user_controls;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import java.util.Base64;
import java.util.Properties;

import javax.swing.JPanel;

public class user_setup {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private static JTextField hidden_id;
	private JLabel lblNewLabel;
	private JTextField txtHidenimagepath;
	private JComboBox<String> comboBox ;
	private JComboBox<String> comboBox_1 ;
	private JComboBox<String> comboBox_2 ;
	private JComboBox<String> comboBox_3 ;
	private JButton user_control;
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					user_setup window = new user_setup("mr_pro", null);
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
	 * @throws JSONException 
	 */
	public user_setup(String company, String version) throws JSONException {
		initialize(company, version);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param host_name 
	 * @param company 
	 * @throws JSONException 
	 */
	private void initialize(final String company, final String version) throws JSONException {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
	    frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		
		JLabel lblCompanySetup = new JLabel(" User Setup");
		lblCompanySetup.setFont(new Font("Tahoma", Font.BOLD, 15));
		Image user=new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		lblCompanySetup.setIcon(new ImageIcon(user));
		lblCompanySetup.setBounds(21, 11, 228, 44);
		frame.getContentPane().add(lblCompanySetup);
		
		hidden_id = new JTextField();
		hidden_id.setEditable(false);
		hidden_id.setVisible(false);
		hidden_id.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    try {
					warn();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
			  public void removeUpdate(DocumentEvent e) {
			    try {
					warn();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
			  public void insertUpdate(DocumentEvent e) {
			    try {
					warn();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }

			  public void warn() throws JSONException {
			     if (!hidden_id.getText().equals("")){
			    	user_setupdisp(company, hidden_id.getText());
			 		
			     }
			  }
			});
		hidden_id.setBounds(10, 85, 116, 22);
		hidden_id.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(hidden_id);
		hidden_id.setColumns(10);
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 66, 694, 2);
		frame.getContentPane().add(separator);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		mntmNewMenuItem.setBackground(new Color(243, 229, 216));
		mntmNewMenuItem.setIcon(new ImageIcon(exit));
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBounds(427, 11, 60, 48);
		frame.getContentPane().add(mntmNewMenuItem);
		
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_4.setText("");
				hidden_id.setText("");
				passwordField.setText("");
				passwordField_1.setText("");
				lblNewLabel.setIcon(null);
			}
		});
		mntmRefresh.setBackground(new Color(243, 229, 216));
		mntmRefresh.setIcon(new ImageIcon(refresh));
		mntmRefresh.setBounds(487, 11, 80, 48);
		mntmRefresh.setBorder(null);
		frame.getContentPane().add(mntmRefresh);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user_code=textField.getText();
				String user_name=textField_1.getText();
				String pwd = new String(passwordField.getPassword());
				String c_pwd = new String(passwordField_1.getPassword());
				String swipe_card=textField_4.getText();
				Object  user_type=comboBox.getSelectedItem();
				Object  shift_code= comboBox_3.getSelectedItem();
				Object  lang= comboBox_2.getSelectedItem();
				Object  sts= comboBox_1.getSelectedItem();
				String image=txtHidenimagepath.getText();
				String mobile=textField_2.getText();
				if(user_code.equals(""))
				{
					JOptionPane.showMessageDialog(frame, "Plaese Enter The User Code");
				}
				else
				{
					if(sts.equals("Active"))
					{
						sts="1";
					}
					else
					{
						sts="0";
					}
					if(!image.equals(""))
					{
						File configFile = new File("C:/MasterRetails/config.properties");
						FileReader reader;
						try {
							reader = new FileReader(configFile);
							Properties props = new Properties();
						    props.load(reader);
						    String imageurl = props.getProperty("image");
						    reader.close();
						    Path temp = Files.copy(Paths.get(image),Paths.get("\\"+imageurl+"\\"+user_code+".jpg") , StandardCopyOption.REPLACE_EXISTING);
						    if(temp == null) 
					        { 
					            System.out.println("Image Uploading Failed, Please Try Again"); 
					        } 
						    else
						    {
						    	if(!pwd.equals(""))
								{
									if(pwd.equals(c_pwd))
									{
										LocalDateTime myDateObj = LocalDateTime.now();  
									    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
									    String formattedDate = myDateObj.format(myFormatObj);  
									    Connection con=Dbsetup.dbconn.getConnection();
										Statement statement;
										try {
											statement = con.createStatement();
											ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_users where user_code='"+user_code+"' ");
											if(resultSet.next())
											{
												String query="update "+company+".mr_users set mobile='"+mobile+"', `user_name`='"+user_name+"', `swipe_id`='"+swipe_card+"', `password`='"+pwd+"', `user_type`='"+user_type+"',status='"+sts+"', `image`='"+user_code+".jpg', `shift_code`='"+shift_code+"', `language`='"+lang+"', `modified_date`='"+formattedDate+"' WHERE user_code='"+user_code+"' ";
												statement.executeUpdate(query);
												JOptionPane.showMessageDialog(frame, "Saved Successfully");
											}
											else
											{
												String query="INSERT INTO "+company+".mr_users(`user_code`, `user_name`, `swipe_id`, `password`, `user_type`, `image`, `shift_code`, `language`, `created_date`, `modified_date`, `status`,mobile)"
														+ "values('"+user_code+"','"+user_name+"','"+swipe_card+"','"+pwd+"','"+user_type+"','"+user_code+".jpg','"+shift_code+"','"+lang+"','"+formattedDate+"','"+formattedDate+"','"+sts+"','"+mobile+"')";
												statement.executeUpdate(query);
												JOptionPane.showMessageDialog(frame, "Saved Successfully");
											}
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}  
									}
									else
									{
										JOptionPane.showMessageDialog(frame, "Password Not Match");
									}
								}
								else
								{
									LocalDateTime myDateObj = LocalDateTime.now();  
								    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
								    String formattedDate = myDateObj.format(myFormatObj);  
								    Connection con=Dbsetup.dbconn.getConnection();
									Statement statement;
									try {
										statement = con.createStatement();
										ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_users where user_code='"+user_code+"' ");
										if(resultSet.next())
										{
											String query="update "+company+".mr_users set  mobile='"+mobile+"',`user_name`='"+user_name+"', `swipe_id`='"+swipe_card+"', `user_type`='"+user_type+"',status='"+sts+"', `image`='"+user_code+".jpg', `shift_code`='"+shift_code+"', `language`='"+lang+"', `modified_date`='"+formattedDate+"'  where user_code='"+user_code+"' ";
											statement.executeUpdate(query);
											JOptionPane.showMessageDialog(frame, "Saved Successfully");
										}
										else
										{
											String query="INSERT INTO "+company+".mr_users(`user_code`, `user_name`, `swipe_id`, `user_type`, `image`, `shift_code`, `language`, `created_date`, `modified_date`, `status`,mobile)"
													+ "values('"+user_code+"','"+user_name+"','"+swipe_card+"','"+user_type+"','"+user_code+".jpg','"+shift_code+"','"+lang+"','"+formattedDate+"','"+formattedDate+"','"+sts+"','"+mobile+"')";
											statement.executeUpdate(query);
											JOptionPane.showMessageDialog(frame, "Saved Successfully");
										}
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}  
								}
						    }
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
					else
					{
						if(!pwd.equals(""))
						{
							if(pwd.equals(c_pwd))
							{
								LocalDateTime myDateObj = LocalDateTime.now();  
							    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
							    String formattedDate = myDateObj.format(myFormatObj);  
							    Connection con=Dbsetup.dbconn.getConnection();
								Statement statement;
								try {
									statement = con.createStatement();
									ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_users where user_code='"+user_code+"' ");
									if(resultSet.next())
									{
										String query="update "+company+".mr_users set  mobile='"+mobile+"',`user_name`='"+user_name+"', `swipe_id`='"+swipe_card+"', `password`='"+pwd+"', `user_type`='"+user_type+"',status='"+sts+"', `image`='"+image+"', `shift_code`='"+shift_code+"', `language`='"+lang+"', `modified_date`='"+formattedDate+"' WHERE user_code='"+user_code+"' ";
										statement.executeUpdate(query);
										JOptionPane.showMessageDialog(frame, "Saved Successfully");
									}
									else
									{
										String query="INSERT INTO "+company+".mr_users(`user_code`, `user_name`, `swipe_id`, `password`, `user_type`, `image`, `shift_code`, `language`, `created_date`, `modified_date`, `status`,mobile)"
												+ "values('"+user_code+"','"+user_name+"','"+swipe_card+"','"+pwd+"','"+user_type+"','"+image+"','"+shift_code+"','"+lang+"','"+formattedDate+"','"+formattedDate+"','"+sts+"','"+mobile+"')";
										statement.executeUpdate(query);
										JOptionPane.showMessageDialog(frame, "Saved Successfully");
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}  
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Password Not Match");
							}
						}
						else
						{
							LocalDateTime myDateObj = LocalDateTime.now();  
						    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
						    String formattedDate = myDateObj.format(myFormatObj);  
						    Connection con=Dbsetup.dbconn.getConnection();
							Statement statement;
							try {
								statement = con.createStatement();
								ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_users where user_code='"+user_code+"' ");
								if(resultSet.next())
								{
									String query="update "+company+".mr_users set  mobile='"+mobile+"',`user_name`='"+user_name+"', `swipe_id`='"+swipe_card+"',  `user_type`='"+user_type+"',status='"+sts+"', `image`='"+image+"', `shift_code`='"+shift_code+"', `language`='"+lang+"', `modified_date`='"+formattedDate+"'  where user_code='"+user_code+"' ";
									statement.executeUpdate(query);
									JOptionPane.showMessageDialog(frame, "Saved Successfully");
								}
								else
								{
									String query="INSERT INTO "+company+".mr_users(`user_code`, `user_name`, `swipe_id`,  `user_type`, `image`, `shift_code`, `language`, `created_date`, `modified_date`, `status`,mobile)"
											+ "values('"+user_code+"','"+user_name+"','"+swipe_card+"','"+user_type+"','"+image+"','"+shift_code+"','"+lang+"','"+formattedDate+"','"+formattedDate+"','"+sts+"','"+mobile+"')";
									statement.executeUpdate(query);
									JOptionPane.showMessageDialog(frame, "Saved Successfully");
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}  
						}
					}
				}
			}
		});
		mntmSave.setBackground(new Color(243, 229, 216));
		mntmSave.setIcon(new ImageIcon(save));
		mntmSave.setBounds(569, 11, 70, 48);
		mntmSave.setBorder(null);
		frame.getContentPane().add(mntmSave);
		
		JLabel lblUserCode = new JLabel("User Code :");
		lblUserCode.setBounds(21, 133, 83, 21);
		frame.getContentPane().add(lblUserCode);
		
		JLabel lblUserName = new JLabel("User Name :");
		lblUserName.setBounds(21, 165, 83, 21);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(21, 197, 83, 21);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password :");
		lblConfirmPassword.setBounds(21, 229, 119, 21);
		frame.getContentPane().add(lblConfirmPassword);
		
		JLabel lblSwipeCard = new JLabel("Swipe Card :");
		lblSwipeCard.setBounds(21, 261, 108, 21);
		frame.getContentPane().add(lblSwipeCard);
		
		JLabel lblUserType = new JLabel("User Type :");
		lblUserType.setBounds(21, 293, 108, 21);
		frame.getContentPane().add(lblUserType);
		
		JLabel lblShiftCode = new JLabel("Shift Code :");
		lblShiftCode.setBounds(21, 325, 83, 14);
		frame.getContentPane().add(lblShiftCode);
		
		JLabel lblLanguage = new JLabel("Language :");
		lblLanguage.setBounds(21, 353, 83, 17);
		frame.getContentPane().add(lblLanguage);
		
		textField = new JTextField();
		textField.setBounds(139, 133, 184, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(139, 165, 184, 20);
		frame.getContentPane().add(textField_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(139, 261, 184, 20);
		frame.getContentPane().add(textField_4);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(139, 350, 184, 20);
		comboBox_2.addItem("English");
		comboBox_2.addItem("Arabic");
		comboBox_2.setSelectedIndex(-1);
		frame.getContentPane().add(comboBox_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(139, 197, 184, 20);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(139, 229, 184, 20);
		frame.getContentPane().add(passwordField_1);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(139, 126, 162, 34);
		
		
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_usertypes");
			while(resultSet.next())
			{
				String name=resultSet.getString("types");
		        comboBox.addItem(name);
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(139, 293, 184, 20);
		frame.getContentPane().add(comboBox);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(412, 66, 1, 393);
		frame.getContentPane().add(separator_2);
		
		JButton btnNewButton = new JButton("Select User");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new user_list(company,"user_setup", version);
					hidden_id.setText("");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(139, 79, 184, 34);
		frame.getContentPane().add(btnNewButton);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		comboBox_3 = new JComboBox<String>();
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_usershift");
			while(resultSet.next())
			{
				String name=resultSet.getString("shift_name");
				comboBox_3.addItem(name);
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		comboBox_3.setSelectedIndex(-1);
		comboBox_3.setBounds(139, 321, 184, 20);
		frame.getContentPane().add(comboBox_3);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(412, 66, 237, 393);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(30, 27, 174, 117);
		lblNewLabel.setBorder(border);
		panel.add(lblNewLabel);
		
		JButton btnNewButton1 = new JButton("Upload Photo");
		txtHidenimagepath = null;
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser file = new JFileChooser();
		          file.setCurrentDirectory(new File(System.getProperty("user.home")));
		          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
		          file.addChoosableFileFilter(filter);
		          int result = file.showSaveDialog(null);
		          if(result == JFileChooser.APPROVE_OPTION){
		              File selectedFile = file.getSelectedFile();
		              String path = selectedFile.getAbsolutePath();
		              lblNewLabel.setIcon(ResizeImage(path));
		              txtHidenimagepath.setText(path);
		          }
			}
		});
		btnNewButton1.setBounds(30, 181, 174, 35);
		panel.add(btnNewButton1);
		
		txtHidenimagepath = new JTextField();
		txtHidenimagepath.setEditable(false);
		txtHidenimagepath.setVisible(false);
		txtHidenimagepath.setBounds(88, 358, 116, 22);
		panel.add(txtHidenimagepath);
		txtHidenimagepath.setColumns(10);
		
		user_control = new JButton("User Controls");
		user_control.setVisible(false);
		user_control.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new user_controls(company,hidden_id.getText(), version);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		user_control.setBounds(139, 439, 184, 34);
		frame.getContentPane().add(user_control);
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setBounds(21, 384, 83, 17);
		frame.getContentPane().add(lblStatus);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(139, 381, 184, 20);
		comboBox_1.addItem("Active");
		comboBox_1.addItem("Inactive");
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblMobileNo = new JLabel("Mobile No. :");
		lblMobileNo.setBounds(21, 412, 95, 14);
		frame.getContentPane().add(lblMobileNo);
		
		textField_2 = new JTextField();
		textField_2.setBounds(139, 409, 184, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		frame.setResizable(false);
		frame.setBounds(100, 100, 655, 531);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
	private void user_setupdisp(String company, String selctd_id)
	{
		try {
			Connection con=Dbsetup.dbconn.getConnection();
			Statement statement=con.createStatement();  
			ResultSet resultSet = statement.executeQuery("select * from "+company+".mr_users where user_code='"+selctd_id+"' ");
			if(resultSet.next())
			{
				textField.setText(resultSet.getString("user_code"));
	         	//textField.setEditable(false);
	         	textField_1.setText(resultSet.getString("user_name"));
	         	textField_4.setText(resultSet.getString("swipe_id"));
	         	comboBox.setSelectedItem(resultSet.getString("user_type"));
	         	comboBox_3.setSelectedItem(resultSet.getString("shift_code"));
	         	comboBox_2.setSelectedItem(resultSet.getString("language"));
	         	if(resultSet.getString("status").equals("1"))
	         	{
	         		comboBox_1.setSelectedItem("Active");
	         	}
	         	else
	         	{
	         		comboBox_1.setSelectedItem("Inactive");
	         	}
	         	String dbimg=resultSet.getString("image");
	        	if(dbimg!="null")
	        	{
	        		File configFile = new File("C:/MasterRetails/config.properties");
					FileReader reader = new FileReader(configFile);
				    Properties props = new Properties();
				    props.load(reader);
				    String image = props.getProperty("image");
				    reader.close();
				    lblNewLabel.setIcon(ResizeImage("\\"+image+"\\"+dbimg));
	        	}
	        	else
	        	{
	        		lblNewLabel.setIcon(ResizeImage(""));
	        	}
	         	user_control.setVisible(true);
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	public static String encoder(File file) {
		String base64Image = "";
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a Image file from file system
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			base64Image = Base64.getEncoder().encodeToString(imageData);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
		return base64Image;
	}
	 public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
	 static String user_id(String id)
	 {
		 hidden_id.setText(id);
		return null;
	 }
}
