package sales_distribution;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.JSONException;

import javax.swing.JTextArea;

public class itemdetails {

	static JFrame frame;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					itemdetails window = new itemdetails("");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param string 
	 */
	public itemdetails(String value) throws JSONException {
		initialize(value);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param value 
	 * @param decimal 
	 * @param page 
	 */
	private void initialize(String value) throws JSONException {
		frame = new JFrame("");
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 536, 435);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 526, 76);
		frame.getContentPane().add(panel);
		
		Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		
		
		JLabel lblItemMasterList = new JLabel("Item Details");
		lblItemMasterList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemMasterList.setBounds(21, 11, 122, 44);
		panel.add(lblItemMasterList);
		
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				String textdt=textArea.getText();
				sd_lines.textField_7.setText(textdt);
			}
		});
		menuItem.setBorder(null);
		menuItem.setBackground(new Color(243, 229, 216));
		menuItem.setBounds(436, 7, 60, 48);
		menuItem.setIcon(new ImageIcon(exit));
		panel.add(menuItem);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 75, 526, 322);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(739, 472, -726, -457);
		panel_1.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setBounds(12, 13, 505, 301);
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int max = 500;
				if(textArea.getText().length() > max+1) {
			        e.consume();
			        String shortened = textArea.getText().substring(0, max);
			        textArea.setText(shortened);
			    }else if(textArea.getText().length() > max) {
			        e.consume();
			    }
			}
		});
		panel_1.add(textArea);
		textArea.setText(value);
		
	}
}