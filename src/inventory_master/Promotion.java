package inventory_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;

import java.awt.Font;

public class Promotion {

	private JFrame frame;
	
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Promotion window = new Promotion("mr_pro", null, null, null,"");
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
	public Promotion(String company,String dbname,String store,String username,String version) {
		initialize(company,dbname,store,username,version);
	}
	/**
	 * Initialize the contents of the frame.
	 * @param username 
	 * @param store 
	 * @param dbname 
	 */
	private void initialize(final String company, final String dbname,final String store,final String username,final String version) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.getContentPane().setBackground(new Color(243, 229, 216));
		frame.getContentPane().setLayout(null);
		
		Image exit=new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		Image refresh=new ImageIcon(this.getClass().getResource("/refresh.png")).getImage();
		Image save=new ImageIcon(this.getClass().getResource("/save.png")).getImage();
		
		JMenuItem menuItem = new JMenuItem("Exit", KeyEvent.VK_F4);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(623, 13, 79, 30);
		menuItem.setIcon(new ImageIcon(exit));
		frame.getContentPane().add(menuItem);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 0, 205));
		progressBar.setBackground(new Color(192, 192, 192));
		progressBar.setStringPainted(true);
		//progressBar.setBounds(12, 198, 622, 31);
		//frame.getContentPane().add(progressBar);
		progressBar.setBounds(10, 191, 854, 31);
		progressBar.setVisible(false);
		frame.getContentPane().add(progressBar);
		
		
		JMenuItem menuItem_1 = new JMenuItem("Refresh", KeyEvent.VK_F5);
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		menuItem_1.setBorder(null);
		menuItem_1.setBackground(new Color(243, 229, 216));
		menuItem_1.setBounds(707, 13, 95, 30);
		menuItem_1.setIcon(new ImageIcon(refresh));
		frame.getContentPane().add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Save");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame, "Saved Successfull");
			}
		});
		menuItem_2.setBorder(null);
		menuItem_2.setBackground(new Color(243, 229, 216));
		menuItem_2.setBounds(803, 13, 79, 30);
		menuItem_2.setIcon(new ImageIcon(save));
		frame.getContentPane().add(menuItem_2);
		
		JLabel lblKitItems = new JLabel("PROMOTION");
		lblKitItems.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblKitItems.setBounds(12, 10, 364, 49);
		frame.getContentPane().add(lblKitItems);
		
		final JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int i=0;
				if(e.getSource()==btnOk)
	            {
					progressBar.setVisible(true);
	                try
	                {
	                   while(i<=100)
	                   {
	                	   Thread.sleep(50);
	                	   progressBar.paintImmediately(0, 0, 854, 31);
	                	   progressBar.setValue(i);
	                	   i++;
	                   	}
	                 }
	                 catch(Exception e1)
	                 {
	                	 System.out.print("Caughted exception is ="+e1);
	                 }
	            }
			}
		});
		btnOk.setBounds(48, 157, 89, 23);
		frame.getContentPane().add(btnOk);
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 950, 600);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
	}
	
}
