package inventory_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class QtyOHList {

	private JFrame frame;
	private static DefaultTableModel model;
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QtyOHList window = new QtyOHList(null, null, null, null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param con 
	 * @param company 
	 * @param db_name 
	 * @param itemcode 
	 */
	public QtyOHList(String db_name, String company, Connection con, String itemcode) {
		initialize(db_name, company, con, itemcode);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String db_name, String company, Connection con, String itemcode) {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 533, 314);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 527, 276);
		panel.setBackground(new Color(243, 229, 216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPostPurchaseOrser = new JLabel("QTY OH");
		lblPostPurchaseOrser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPostPurchaseOrser.setBounds(10, 11, 301, 14);
		panel.add(lblPostPurchaseOrser);
		
		
JScrollPane scrollPane = new JScrollPane(table);
		
		scrollPane.setBounds(0, 30, 515, 233);
		scrollPane.setBackground(new Color(243, 229, 216));
		panel.add(scrollPane);

		model = new DefaultTableModel() {
		      /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int columnIndex) {
		        Object o = getValueAt(0, columnIndex);
		        if (o == null) {
		          return Object.class;
		        } else {
		          return o.getClass();
		        }
		      }
		    };
		    table = new JTable(model);
		    table.setFont(new Font("Serif", Font.BOLD, 18));
			table.setRowHeight(20);
		    model.addColumn("Unit"); 
			model.addColumn("Convert Factor"); 
			model.addColumn("QtyOH");
			model.addColumn("Qty Sold");
			model.addColumn("Net Qty");
			scrollPane.setViewportView(table);
			
			Statement statement;
			Statement statement1;
			try {
				statement = con.createStatement();
				statement1 = con.createStatement();
				double qtysold=0;
				String query="select case when sum(mr_sd_lines.qty*mr_sd_lines.cf)!=0 then sum(mr_sd_lines.qty*mr_sd_lines.cf) else 0 end as qtysold from "+company+".mr_sd_lines where mr_sd_lines.item_code='"+itemcode+"' ";
				ResultSet resultSet32 = statement.executeQuery(query);
				if(resultSet32.next())
				{
					qtysold += Double.parseDouble(resultSet32.getString("qtysold"));
				}
				String query1="select * from "+company+".mr_kit_items where parent_itemcode='"+itemcode+"' ";
				ResultSet resultSet321 = statement1.executeQuery(query1);
				while(resultSet321.next())
				{
					String query12="select case when sum(mr_sd_lines.qty*mr_sd_lines.cf)!=0 then sum(mr_sd_lines.qty*mr_sd_lines.cf) else 0 end as qtysold from "+company+".mr_sd_lines where mr_sd_lines.item_code='"+resultSet321.getString("child_itemcode")+"' ";
					ResultSet resultSet3212 = statement.executeQuery(query12);
					if(resultSet3212.next())
					{
						qtysold += Double.parseDouble(resultSet3212.getString("qtysold"));
					}
				}
				
				//String query11="SELECT mr_tmp_item_master.item_name,mr_tmp_item_master.unit,mr_tmp_item_master.cf,mr_tmp_item_master.qty as qtyoh,sum(mr_sd_lines.qty) as qtysold FROM "+company+".mr_tmp_item_master left join "+company+".mr_sd_lines on mr_sd_lines.item_code=mr_tmp_item_master.item_code and mr_sd_lines.barcode=mr_tmp_item_master.barcode where mr_tmp_item_master.item_code='"+itemcode+"' group by unit ";
				String query11="SELECT * FROM "+company+".mr_tmp_item_master where item_code='"+itemcode+"' "; 
				ResultSet resultSet = statement.executeQuery(query11);
				while(resultSet.next())
				{
					String unit=resultSet.getString("unit");
	                String cf=resultSet.getString("cf");
	                String qty=resultSet.getString("qty");
	                double qtyoh=Double.parseDouble(qty)/Double.parseDouble(cf);
	                
	                
	                double qtysold1=qtysold/Double.parseDouble(cf);
	                double netqty=qtyoh-qtysold1;
	                
	                model.addRow(new Object[]{unit,cf,qtyoh,qtysold1,netqty});
	                	
	                
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}
