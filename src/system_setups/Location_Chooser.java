package system_setups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Location_Chooser {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Location_Chooser window = new Location_Chooser(null);
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
	public Location_Chooser(String version) {
		initialize(version);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String version) {
		frame = new JFrame(version);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(0, 0, 600, 400);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(243, 229, 216));
		panel.setBounds(0, 0, 584, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblShifts = new JLabel("Locations");
		lblShifts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblShifts.setBounds(10, 11, 228, 44);
		panel.add(lblShifts);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 72, 684, 2);
		panel.add(separator);
		
		JButton button = new JButton("Exit");
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(317, 13, 108, 44);
		panel.add(button);
		
		JButton button_1 = new JButton("Refresh");
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setBounds(435, 13, 128, 44);
		panel.add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 73, 584, 289);
		panel.add(scrollPane);
		
		model = new DefaultTableModel(){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
		   
		};
		table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 15));
			table.setRowHeight(20);
		    model.addColumn("Location Code"); 
		    model.addColumn("Location Name"); 
			model.addColumn("Location Manager"); 
			model.addColumn("Location Name1"); 
			model.addColumn("Location Address1"); 
			model.addRow(new Object[]{"","","","",""});
		    scrollPane.setViewportView(table);
	}
	

}
