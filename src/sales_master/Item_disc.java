package sales_master;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import CafePro.Mainpage;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Item_disc {

	private JFrame frame;
	private JTextField textField_12;
	private boolean dot = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Item_disc window = new Item_disc( "mr_pro","admin","S01","S0102", null);
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
	public Item_disc(String company, String username, String store_name, String reg_nme,String version) {
		initialize(company, username, store_name, reg_nme, version);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final String company,final String username ,final String store_name ,final String reg_nme,String version) {
		frame = new JFrame(version);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if(Mainpage.SoftLang.equals("Ar"))
				{
					PosscreenArabic.btnManager.setEnabled(true);
					PosscreenArabic.txtDisc.requestFocus();
				}
				else
				{
					posscreen.btnManager.setEnabled(true);
					posscreen.txtDisc.requestFocus();
				}
				
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/cafepro.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 241, 445);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);
		panel.setBounds(15, 15, 190, 313);
		frame.getContentPane().add(panel);
		
		JButton button_12 = new JButton("1");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("1");
			}
		});
		button_12.setFont(new Font("Arial", Font.BOLD, 30));
		button_12.setBounds(0, 53, 60, 60);
		panel.add(button_12);
		
		JButton button_13 = new JButton("2");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("2");
			}
		});
		button_13.setFont(new Font("Arial", Font.BOLD, 30));
		button_13.setBounds(65, 53, 60, 60);
		panel.add(button_13);
		
		JButton button_14 = new JButton("3");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("3");
			}
		});
		button_14.setFont(new Font("Arial", Font.BOLD, 30));
		button_14.setBounds(130, 53, 60, 60);
		panel.add(button_14);
		
		JButton button_15 = new JButton("4");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("4");
			}
		});
		button_15.setFont(new Font("Arial", Font.BOLD, 30));
		button_15.setBounds(0, 118, 60, 60);
		panel.add(button_15);
		
		JButton button_16 = new JButton("5");
		button_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("5");
			}
		});
		button_16.setFont(new Font("Arial", Font.BOLD, 30));
		button_16.setBounds(65, 118, 60, 60);
		panel.add(button_16);
		
		JButton button_17 = new JButton("6");
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("6");
			}
		});
		button_17.setFont(new Font("Arial", Font.BOLD, 30));
		button_17.setBounds(130, 118, 60, 60);
		panel.add(button_17);
		
		JButton button_18 = new JButton("7");
		button_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("7");
			}
		});
		button_18.setFont(new Font("Arial", Font.BOLD, 30));
		button_18.setBounds(0, 183, 60, 60);
		panel.add(button_18);
		
		JButton button_19 = new JButton("8");
		button_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("8");
			}
		});
		button_19.setFont(new Font("Arial", Font.BOLD, 30));
		button_19.setBounds(65, 183, 60, 60);
		panel.add(button_19);
		
		JButton button_20 = new JButton("9");
		button_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("9");
			}
		});
		button_20.setFont(new Font("Arial", Font.BOLD, 30));
		button_20.setBounds(130, 183, 60, 60);
		panel.add(button_20);
		
		JButton button_21 = new JButton("0");
		button_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn("0");
			}
		});
		button_21.setFont(new Font("Arial", Font.BOLD, 30));
		button_21.setBounds(65, 248, 60, 60);
		panel.add(button_21);
		
		JButton button_22 = new JButton("<<");
		button_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pass=new String(textField_12.getText()); 
				if(!pass.equals(""))
				{
					textField_12.setText(pass.substring(0, pass.length() - 1));
				}
			}
		});
		button_22.setFont(new Font("Arial", Font.BOLD, 20));
		button_22.setBounds(130, 248, 60, 60);
		panel.add(button_22);
		
		JButton button_23 = new JButton(".");
		button_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click_btn(".");
			}
		});
		button_23.setFont(new Font("Arial", Font.BOLD, 30));
		button_23.setBounds(0, 248, 60, 60);
		panel.add(button_23);
		
		
		textField_12 = new JTextField();
		textField_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submit_btn(company, store_name,username,reg_nme);
			}
		});
		textField_12.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				String pass=new String(textField_12.getText()); 
			    if (pass.equals(""))
			        dot = false;
			    if (dot == false){
			        if (vChar == '.') dot = true;
			        else if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    } else {
			        if (!(Character.isDigit(vChar)
			                || (vChar == KeyEvent.VK_BACK_SPACE)
			                || (vChar == KeyEvent.VK_DELETE))) {
			                e.consume();
			        }
			    }
			}
		});
		textField_12.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLUE, null, null, null));
		textField_12.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_12.setForeground(Color.DARK_GRAY);
		textField_12.setFont(new Font("Tahoma", Font.PLAIN, 40));
		//textField_12.setEchoChar('*');
		textField_12.setColumns(10);
		textField_12.setBackground(new Color(176, 224, 230));
		textField_12.setBounds(0, 0, 190, 50);
		panel.add(textField_12);
		
		JButton btnNewButton = new JButton("Disc %");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submit_btn(company, store_name,username,reg_nme);
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(15, 341, 95, 44);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDisc = new JButton("Disc Amt.");
		btnDisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				amt_btn(company, store_name,username,reg_nme);
				frame.setVisible(false);
			}
		});
		btnDisc.setBounds(122, 341, 95, 44);
		frame.getContentPane().add(btnDisc);
		
	}
	private void click_btn(String num)
	{
		try{
			String pass_string=new String(textField_12.getText()); 
			pass_string+=num;
			textField_12.setText(pass_string);
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	private void amt_btn(String company,String store,String username,String reg_num)
	{
		try{
			if(Mainpage.SoftLang.equals("Ar"))
			{
				String amount=new String(textField_12.getText()); 
				double line_ttl=0;
				int sel_table=PosscreenArabic.table.getSelectedRow();
				String disc_type=PosscreenArabic.table.getValueAt(sel_table, 15).toString();
				String disc_item=PosscreenArabic.table.getValueAt(sel_table, 14).toString();
				if(disc_type.equals("N") || disc_type.equals("ID"))
				{
					if(disc_item.equals("0"))
					{
						double unit_price=Double.parseDouble(PosscreenArabic.table.getValueAt(sel_table, 4).toString());
						double qty=Double.parseDouble(PosscreenArabic.table.getValueAt(sel_table, 5).toString());
						String vattype=PosscreenArabic.table.getModel().getValueAt(sel_table, 10).toString();
						if(vattype.equals("EXC"))
						{
							line_ttl=(unit_price*qty);
						}
						if(vattype.equals("INC"))
						{
							line_ttl=((unit_price*100/105)*qty);
						}
						if(vattype.equals("NON"))
						{
							line_ttl=((unit_price)*qty);
						}
					}
				}
				double disc_perc=(Double.parseDouble(amount)*100)/line_ttl;
				if(Double.parseDouble(PosscreenArabic.disc_amt)>=disc_perc)
				{
					double desc=0;
					double grand_total=0;
					double total=0;
					double vatpric=0;
					double tax_rate = 0;
					double ttl_bf_disc=0;
					double ttl_af_disc=0;
					for(int i=0;i<PosscreenArabic.model.getRowCount();i++)
					{
						String vattype=PosscreenArabic.table.getModel().getValueAt(i, 10).toString();
						double unit_price=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 4).toString());
						double qty=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 5).toString());
						if(i==sel_table)
						{
							
							if(disc_type.equals("N") || disc_type.equals("ID"))
							{
								if(disc_item.equals("0"))
								{
									tax_rate=Double.parseDouble(PosscreenArabic.table.getValueAt(sel_table, 16).toString());
									PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(disc_perc), sel_table, 6);
									if(vattype.equals("EXC"))
									{
										desc=((unit_price*qty)*disc_perc)/100;
										//desc_amt+=desc;
										total+=(unit_price*qty)-desc;
										vatpric+=((unit_price*qty)-desc)*tax_rate/100;
										double total_line=(unit_price*qty)-desc;
										double line_vat=((unit_price*qty)-desc)*tax_rate/100;
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line), sel_table, 7);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(line_vat), sel_table, 8);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), sel_table, 9);
										if(PosscreenArabic.gs.length>1)
										{
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line), sel_table, 7);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(line_vat), sel_table, 8);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), sel_table, 9);
										}
									}
									if(vattype.equals("INC"))
									{
										desc=(((unit_price*qty)*100/105)*disc_perc)/100;
										//desc_amt+=desc;
										total+=((unit_price*100/105)*qty)-desc;
										vatpric+=(((unit_price*100/105)*qty)-desc)*tax_rate/100;
										double total_line=((unit_price)*qty*100/105)-desc;
										double line_vat=(((unit_price)*qty*100/105)-desc)*tax_rate/100;
										
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line), sel_table, 7);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(line_vat), sel_table, 8);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), sel_table, 9);
										if(PosscreenArabic.gs.length>1)
										{
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line), sel_table, 7);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(line_vat), sel_table, 8);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), sel_table, 9);
										}
									}
									if(vattype.equals("NON"))
									{
										desc=((unit_price*qty)*disc_perc)/100;
										//desc_amt+=desc;
										total+=((unit_price)*qty)-desc;
										vatpric+=0;
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), sel_table, 7);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), sel_table, 9);
										if(PosscreenArabic.gs.length>1)
										{
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), sel_table, 7);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), sel_table, 9);
										}
									}
									if(Double.parseDouble(amount)==0)
									{
										PosscreenArabic.table.setValueAt("N", sel_table, 15);
										if(PosscreenArabic.gs.length>1)
										{
											SecondDisplay.table.setValueAt("N", sel_table, 15);
										}
									}
									else
									{
										PosscreenArabic.table.setValueAt("ID", sel_table, 15);
										if(PosscreenArabic.gs.length>1)
										{
											SecondDisplay.table.setValueAt("ID", sel_table, 15);
										}
									}
									
									
								}
								else
								{
									total+=Double.parseDouble(PosscreenArabic.table.getValueAt(sel_table, 7).toString());
									vatpric+=Double.parseDouble(PosscreenArabic.table.getValueAt(sel_table, 8).toString());
								}
								
							}
							else
							{
								total+=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 7).toString());
								vatpric+=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 8).toString());
							}
						}
						else
						{
							total+=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 7).toString());
							vatpric+=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 8).toString());
						}
						
						
						double totl=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 7).toString());
						double vt=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 8).toString());
						
						if(vattype.equals("INC"))
						{
							ttl_af_disc+=totl+vt;
						}
						else
						{
							ttl_af_disc+=totl;
						}
						
						String ttl_before=PosscreenArabic.df2.format(unit_price*qty);
						
						ttl_bf_disc+=Double.parseDouble(ttl_before);
					}
					String before_disc=PosscreenArabic.df2.format(ttl_bf_disc);
					String after_disc=PosscreenArabic.df2.format(ttl_af_disc);
					double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
					if(Double.isNaN(ttl_disc_perc))
					{
						ttl_disc_perc=0;
					}
					double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
					
	    			String itm_disc=Double.toString(ttl_disc_perc);
	    			if(!itm_disc.equals(""))
					{
	    				grand_total=total+vatpric;
	    				
	    				double checksum=0;
	    				if(PosscreenArabic.allow_roundoff.equals("1"))
	    				{
	    					String nettotal=PosscreenArabic.df2.format(grand_total);
				    		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
				    		int number_decimal=Integer.parseInt(splitnumber);
				    		
				    		if(number_decimal>0 && number_decimal<13 )
				    		{
				    			checksum=0-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>12 && number_decimal<26 )
				    		{
				    			checksum=25-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>25 && number_decimal<38 )
				    		{
				    			checksum=25-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>37 && number_decimal<51 )
				    		{
				    			checksum=50-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>50 && number_decimal<63 )
				    		{
				    			checksum=50-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>62 && number_decimal<76 )
				    		{
				    			checksum=75-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>75 && number_decimal<88 )
				    		{
				    			checksum=75-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>87 && number_decimal<101 )
				    		{
				    			checksum=100-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
	    				}
	    				PosscreenArabic.lblNewLabel_1.setText(Double.toString(checksum/100));
	    				if(PosscreenArabic.gs.length>1)
						{
	    				SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
						}
			    		
	    				PosscreenArabic.textField.setText(PosscreenArabic.df2.format(total));
	    				PosscreenArabic.textField_3.setText(PosscreenArabic.df2.format(grand_total));
	    				PosscreenArabic.textField_1.setText(PosscreenArabic.df2.format(vatpric));
	    				PosscreenArabic.textField_2.setText(PosscreenArabic.df2.format(ttl_disc_amt));
	    				PosscreenArabic.textField_5.setText(PosscreenArabic.df2.format(Double.parseDouble(itm_disc))+"%");
						if(PosscreenArabic.gs.length>1)
						{
							SecondDisplay.textField.setText(PosscreenArabic.df2.format(total));
							SecondDisplay.textField_3.setText(PosscreenArabic.df2.format(grand_total));
							SecondDisplay.textField_1.setText(PosscreenArabic.df2.format(vatpric));
							SecondDisplay.textField_2.setText(PosscreenArabic.df2.format(ttl_disc_amt));
							SecondDisplay.textField_5.setText(PosscreenArabic.df2.format(Double.parseDouble(itm_disc))+"%");
						}
						String space="";
			    		int print_price;
			    		String perc_disc=PosscreenArabic.df2.format(Double.parseDouble(itm_disc));
			    		print_price=10-perc_disc.length();
		    			for(int i=0; i<print_price;i++)
		    			{
		    				space+=" ";
		    			}
		    			String gap="";
		    			double grand_ttl=grand_total;
			    		String grnd_ttl=PosscreenArabic.df2.format(grand_ttl);
			    		
						int grand_price=15-grnd_ttl.length();
						for(int i=0; i<grand_price;i++)
						{
							gap+=" ";
						}
						Runtime.getRuntime().exec("cmd /c \"CLS>"+PosscreenArabic.display_port+"&&ECHO DISC PERC "+space+""+PosscreenArabic.df2.format(Double.parseDouble(itm_disc))+">"+PosscreenArabic.display_port+"&&ECHO TOTAL"+""+gap+""+PosscreenArabic.df2.format(grand_total)+">"+PosscreenArabic.display_port+"\"");
						PosscreenArabic.txtDisc.setText("");
						PosscreenArabic.txtDisc.requestFocus();
					}
	    			PosscreenArabic.get_user_validate_dt(company, username,store,reg_num);
				}
				else
				{
					PosscreenArabic.frmRegisterNoS.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(PosscreenArabic.frmRegisterNoS, "Maximum Sale Discount is "+PosscreenArabic.disc_amt+" %");
					PosscreenArabic.txtDisc.requestFocus();
				}
			}
			else
			{
				String amount=new String(textField_12.getText()); 
				double line_ttl=0;
				int sel_table=posscreen.table.getSelectedRow();
				String disc_type=posscreen.table.getValueAt(sel_table, 15).toString();
				String disc_item=posscreen.table.getValueAt(sel_table, 14).toString();
				if(disc_type.equals("N") || disc_type.equals("ID"))
				{
					if(disc_item.equals("0"))
					{
						double unit_price=Double.parseDouble(posscreen.table.getValueAt(sel_table, 4).toString());
						double qty=Double.parseDouble(posscreen.table.getValueAt(sel_table, 5).toString());
						String vattype=posscreen.table.getModel().getValueAt(sel_table, 10).toString();
						if(vattype.equals("EXC"))
						{
							line_ttl=(unit_price*qty);
						}
						if(vattype.equals("INC"))
						{
							line_ttl=((unit_price*100/105)*qty);
						}
						if(vattype.equals("NON"))
						{
							line_ttl=((unit_price)*qty);
						}
					}
				}
				double disc_perc=(Double.parseDouble(amount)*100)/line_ttl;
				if(Double.parseDouble(posscreen.disc_amt)>=disc_perc)
				{
					double desc=0;
					double grand_total=0;
					double total=0;
					double vatpric=0;
					double tax_rate = 0;
					double ttl_bf_disc=0;
					double ttl_af_disc=0;
					for(int i=0;i<posscreen.model.getRowCount();i++)
					{
						String vattype=posscreen.table.getModel().getValueAt(i, 10).toString();
						double unit_price=Double.parseDouble(posscreen.table.getValueAt(i, 4).toString());
						double qty=Double.parseDouble(posscreen.table.getValueAt(i, 5).toString());
						if(i==sel_table)
						{
							
							if(disc_type.equals("N") || disc_type.equals("ID"))
							{
								if(disc_item.equals("0"))
								{
									tax_rate=Double.parseDouble(posscreen.table.getValueAt(sel_table, 16).toString());
									posscreen.table.setValueAt(posscreen.df2.format(disc_perc), sel_table, 6);
									if(vattype.equals("EXC"))
									{
										desc=((unit_price*qty)*disc_perc)/100;
										//desc_amt+=desc;
										total+=(unit_price*qty)-desc;
										vatpric+=((unit_price*qty)-desc)*tax_rate/100;
										double total_line=(unit_price*qty)-desc;
										double line_vat=((unit_price*qty)-desc)*tax_rate/100;
										posscreen.table.setValueAt(posscreen.df2.format(total_line), sel_table, 7);
										posscreen.table.setValueAt(posscreen.df2.format(line_vat), sel_table, 8);
										posscreen.table.setValueAt(posscreen.df2.format(total_line+line_vat), sel_table, 9);
										if(posscreen.gs.length>1)
										{
											SecondDisplay.table.setValueAt(posscreen.df2.format(total_line), sel_table, 7);
											SecondDisplay.table.setValueAt(posscreen.df2.format(line_vat), sel_table, 8);
											SecondDisplay.table.setValueAt(posscreen.df2.format(total_line+line_vat), sel_table, 9);
										}
									}
									if(vattype.equals("INC"))
									{
										desc=(((unit_price*qty)*100/105)*disc_perc)/100;
										//desc_amt+=desc;
										total+=((unit_price*100/105)*qty)-desc;
										vatpric+=(((unit_price*100/105)*qty)-desc)*tax_rate/100;
										double total_line=((unit_price)*qty*100/105)-desc;
										double line_vat=(((unit_price)*qty*100/105)-desc)*tax_rate/100;
										
										posscreen.table.setValueAt(posscreen.df2.format(total_line), sel_table, 7);
										posscreen.table.setValueAt(posscreen.df2.format(line_vat), sel_table, 8);
										posscreen.table.setValueAt(posscreen.df2.format(total_line+line_vat), sel_table, 9);
										if(posscreen.gs.length>1)
										{
											SecondDisplay.table.setValueAt(posscreen.df2.format(total_line), sel_table, 7);
											SecondDisplay.table.setValueAt(posscreen.df2.format(line_vat), sel_table, 8);
											SecondDisplay.table.setValueAt(posscreen.df2.format(total_line+line_vat), sel_table, 9);
										}
									}
									if(vattype.equals("NON"))
									{
										desc=((unit_price*qty)*disc_perc)/100;
										//desc_amt+=desc;
										total+=((unit_price)*qty)-desc;
										vatpric+=0;
										posscreen.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), sel_table, 7);
										posscreen.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), sel_table, 9);
										if(posscreen.gs.length>1)
										{
											SecondDisplay.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), sel_table, 7);
											SecondDisplay.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), sel_table, 9);
										}
									}
									if(Double.parseDouble(amount)==0)
									{
										posscreen.table.setValueAt("N", sel_table, 15);
										if(posscreen.gs.length>1)
										{
											SecondDisplay.table.setValueAt("N", sel_table, 15);
										}
									}
									else
									{
										posscreen.table.setValueAt("ID", sel_table, 15);
										if(posscreen.gs.length>1)
										{
											SecondDisplay.table.setValueAt("ID", sel_table, 15);
										}
									}
									
									
								}
								else
								{
									total+=Double.parseDouble(posscreen.table.getValueAt(sel_table, 7).toString());
									vatpric+=Double.parseDouble(posscreen.table.getValueAt(sel_table, 8).toString());
								}
								
							}
							else
							{
								total+=Double.parseDouble(posscreen.table.getValueAt(i, 7).toString());
								vatpric+=Double.parseDouble(posscreen.table.getValueAt(i, 8).toString());
							}
						}
						else
						{
							total+=Double.parseDouble(posscreen.table.getValueAt(i, 7).toString());
							vatpric+=Double.parseDouble(posscreen.table.getValueAt(i, 8).toString());
						}
						
						
						double totl=Double.parseDouble(posscreen.table.getValueAt(i, 7).toString());
						double vt=Double.parseDouble(posscreen.table.getValueAt(i, 8).toString());
						
						if(vattype.equals("INC"))
						{
							ttl_af_disc+=totl+vt;
						}
						else
						{
							ttl_af_disc+=totl;
						}
						
						String ttl_before=posscreen.df2.format(unit_price*qty);
						
						ttl_bf_disc+=Double.parseDouble(ttl_before);
					}
					String before_disc=posscreen.df2.format(ttl_bf_disc);
					String after_disc=posscreen.df2.format(ttl_af_disc);
					double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
					if(Double.isNaN(ttl_disc_perc))
					{
						ttl_disc_perc=0;
					}
					double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
					
	    			String itm_disc=Double.toString(ttl_disc_perc);
	    			if(!itm_disc.equals(""))
					{
	    				grand_total=total+vatpric;
	    				
	    				double checksum=0;
	    				if(posscreen.allow_roundoff.equals("1"))
	    				{
	    					String nettotal=posscreen.df2.format(grand_total);
				    		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
				    		int number_decimal=Integer.parseInt(splitnumber);
				    		
				    		if(number_decimal>0 && number_decimal<13 )
				    		{
				    			checksum=0-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>12 && number_decimal<26 )
				    		{
				    			checksum=25-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>25 && number_decimal<38 )
				    		{
				    			checksum=25-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>37 && number_decimal<51 )
				    		{
				    			checksum=50-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>50 && number_decimal<63 )
				    		{
				    			checksum=50-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>62 && number_decimal<76 )
				    		{
				    			checksum=75-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>75 && number_decimal<88 )
				    		{
				    			checksum=75-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>87 && number_decimal<101 )
				    		{
				    			checksum=100-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
	    				}
	    				posscreen.lblNewLabel_1.setText(Double.toString(checksum/100));
	    				if(posscreen.gs.length>1)
						{
	    				SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
						}
			    		
	    				posscreen.textField.setText(posscreen.df2.format(total));
	    				posscreen.textField_3.setText(posscreen.df2.format(grand_total));
	    				posscreen.textField_1.setText(posscreen.df2.format(vatpric));
	    				posscreen.textField_2.setText(posscreen.df2.format(ttl_disc_amt));
	    				posscreen.textField_5.setText(posscreen.df2.format(Double.parseDouble(itm_disc))+"%");
						if(posscreen.gs.length>1)
						{
							SecondDisplay.textField.setText(posscreen.df2.format(total));
							SecondDisplay.textField_3.setText(posscreen.df2.format(grand_total));
							SecondDisplay.textField_1.setText(posscreen.df2.format(vatpric));
							SecondDisplay.textField_2.setText(posscreen.df2.format(ttl_disc_amt));
							SecondDisplay.textField_5.setText(posscreen.df2.format(Double.parseDouble(itm_disc))+"%");
						}
						String space="";
			    		int print_price;
			    		String perc_disc=posscreen.df2.format(Double.parseDouble(itm_disc));
			    		print_price=10-perc_disc.length();
		    			for(int i=0; i<print_price;i++)
		    			{
		    				space+=" ";
		    			}
		    			String gap="";
		    			double grand_ttl=grand_total;
			    		String grnd_ttl=posscreen.df2.format(grand_ttl);
			    		
						int grand_price=15-grnd_ttl.length();
						for(int i=0; i<grand_price;i++)
						{
							gap+=" ";
						}
						Runtime.getRuntime().exec("cmd /c \"CLS>"+posscreen.display_port+"&&ECHO DISC PERC "+space+""+posscreen.df2.format(Double.parseDouble(itm_disc))+">"+posscreen.display_port+"&&ECHO TOTAL"+""+gap+""+posscreen.df2.format(grand_total)+">"+posscreen.display_port+"\"");
						posscreen.txtDisc.setText("");
						posscreen.txtDisc.requestFocus();
					}
	    			posscreen.get_user_validate_dt(company, username,store,reg_num);
				}
				else
				{
					posscreen.frmRegisterNoS.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(posscreen.frmRegisterNoS, "Maximum Sale Discount is "+posscreen.disc_amt+" %");
					posscreen.txtDisc.requestFocus();
				}
			}
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	
	private void submit_btn(String company,String store,String username,String reg_num)
	{
		try{
			if(Mainpage.SoftLang.equals("Ar"))
			{
				String amount=new String(textField_12.getText()); 
				if(Double.parseDouble(PosscreenArabic.disc_amt)>=Double.parseDouble(amount))
				{
					double desc=0;
					double grand_total=0;
					double total=0;
					double vatpric=0;
					double tax_rate = 0;
					double ttl_bf_disc=0;
					double ttl_af_disc=0;
					for(int i=0;i<PosscreenArabic.model.getRowCount();i++)
					{
						int sel_table=PosscreenArabic.table.getSelectedRow();
						String vattype=PosscreenArabic.table.getModel().getValueAt(i, 10).toString();
						double unit_price=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 4).toString());
						double qty=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 5).toString());
						if(i==sel_table)
						{
							String disc_type=PosscreenArabic.table.getValueAt(sel_table, 15).toString();
							String disc_item=PosscreenArabic.table.getValueAt(sel_table, 14).toString();
							if(disc_type.equals("N") || disc_type.equals("ID"))
							{
								if(disc_item.equals("0"))
								{
									tax_rate=Double.parseDouble(PosscreenArabic.table.getValueAt(sel_table, 16).toString());
									PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(Double.parseDouble(amount)), sel_table, 6);
									if(vattype.equals("EXC"))
									{
										desc=((unit_price*qty)*(Double.parseDouble(amount)))/100;
										//desc_amt+=desc;
										total+=(unit_price*qty)-desc;
										vatpric+=((unit_price*qty)-desc)*tax_rate/100;
										double total_line=(unit_price*qty)-desc;
										double line_vat=((unit_price*qty)-desc)*tax_rate/100;
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line), sel_table, 7);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(line_vat), sel_table, 8);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), sel_table, 9);
										if(PosscreenArabic.gs.length>1)
										{
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line), sel_table, 7);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(line_vat), sel_table, 8);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), sel_table, 9);
										}
									}
									if(vattype.equals("INC"))
									{
										desc=(((unit_price*qty)*100/105)*(Double.parseDouble(amount)))/100;
										//desc_amt+=desc;
										total+=((unit_price*100/105)*qty)-desc;
										vatpric+=(((unit_price*100/105)*qty)-desc)*tax_rate/100;
										double total_line=((unit_price)*qty*100/105)-desc;
										double line_vat=(((unit_price)*qty*100/105)-desc)*tax_rate/100;
										
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line), sel_table, 7);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(line_vat), sel_table, 8);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), sel_table, 9);
										if(PosscreenArabic.gs.length>1)
										{
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line), sel_table, 7);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(line_vat), sel_table, 8);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(total_line+line_vat), sel_table, 9);
										}
									}
									if(vattype.equals("NON"))
									{
										desc=((unit_price*qty)*(Double.parseDouble(amount)))/100;
										//desc_amt+=desc;
										total+=((unit_price)*qty)-desc;
										vatpric+=0;
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), sel_table, 7);
										PosscreenArabic.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), sel_table, 9);
										if(PosscreenArabic.gs.length>1)
										{
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), sel_table, 7);
											SecondDisplay.table.setValueAt(PosscreenArabic.df2.format(((unit_price)*qty)-desc), sel_table, 9);
										}
									}
									if(Double.parseDouble(amount)==0)
									{
										PosscreenArabic.table.setValueAt("N", sel_table, 15);
										if(PosscreenArabic.gs.length>1)
										{
											SecondDisplay.table.setValueAt("N", sel_table, 15);
										}
									}
									else
									{
										PosscreenArabic.table.setValueAt("ID", sel_table, 15);
										if(PosscreenArabic.gs.length>1)
										{
											SecondDisplay.table.setValueAt("ID", sel_table, 15);
										}
									}
									
								}
								else
								{
									total+=Double.parseDouble(PosscreenArabic.table.getValueAt(sel_table, 7).toString());
									vatpric+=Double.parseDouble(PosscreenArabic.table.getValueAt(sel_table, 8).toString());
								}
								
							}
							else
							{
								total+=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 7).toString());
								vatpric+=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 8).toString());
							}
						}
						else
						{
							total+=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 7).toString());
							vatpric+=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 8).toString());
						}
						
						
						double totl=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 7).toString());
						double vt=Double.parseDouble(PosscreenArabic.table.getValueAt(i, 8).toString());
						
						if(vattype.equals("INC"))
						{
							ttl_af_disc+=totl+vt;
						}
						else
						{
							ttl_af_disc+=totl;
						}
						
						String ttl_before=PosscreenArabic.df2.format(unit_price*qty);
						
						ttl_bf_disc+=Double.parseDouble(ttl_before);
					}
					String before_disc=PosscreenArabic.df2.format(ttl_bf_disc);
					String after_disc=PosscreenArabic.df2.format(ttl_af_disc);
					double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
					if(Double.isNaN(ttl_disc_perc))
					{
						ttl_disc_perc=0;
					}
					double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
					
	    			String itm_disc=Double.toString(ttl_disc_perc);
	    			if(!itm_disc.equals(""))
					{
	    				grand_total=total+vatpric;
	    				
	    				double checksum=0;
	    				if(PosscreenArabic.allow_roundoff.equals("1"))
	    				{
	    					String nettotal=PosscreenArabic.df2.format(grand_total);
				    		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
				    		int number_decimal=Integer.parseInt(splitnumber);
				    		
				    		if(number_decimal>0 && number_decimal<13 )
				    		{
				    			checksum=0-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>12 && number_decimal<26 )
				    		{
				    			checksum=25-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>25 && number_decimal<38 )
				    		{
				    			checksum=25-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>37 && number_decimal<51 )
				    		{
				    			checksum=50-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>50 && number_decimal<63 )
				    		{
				    			checksum=50-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>62 && number_decimal<76 )
				    		{
				    			checksum=75-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>75 && number_decimal<88 )
				    		{
				    			checksum=75-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>87 && number_decimal<101 )
				    		{
				    			checksum=100-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
	    				}
	    				PosscreenArabic.lblNewLabel_1.setText(Double.toString(checksum/100));
	    				if(PosscreenArabic.gs.length>1)
						{
	    				SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
						}
			    		
	    				PosscreenArabic.textField.setText(PosscreenArabic.df2.format(total));
	    				PosscreenArabic.textField_3.setText(PosscreenArabic.df2.format(grand_total));
	    				PosscreenArabic.textField_1.setText(PosscreenArabic.df2.format(vatpric));
	    				PosscreenArabic.textField_2.setText(PosscreenArabic.df2.format(ttl_disc_amt));
	    				PosscreenArabic.textField_5.setText(PosscreenArabic.df2.format(Double.parseDouble(itm_disc))+"%");
						if(PosscreenArabic.gs.length>1)
						{
						SecondDisplay.textField.setText(PosscreenArabic.df2.format(total));
						SecondDisplay.textField_3.setText(PosscreenArabic.df2.format(grand_total));
						SecondDisplay.textField_1.setText(PosscreenArabic.df2.format(vatpric));
						SecondDisplay.textField_2.setText(PosscreenArabic.df2.format(ttl_disc_amt));
						SecondDisplay.textField_5.setText(PosscreenArabic.df2.format(Double.parseDouble(itm_disc))+"%");
						}
						String space="";
			    		int print_price;
			    		String perc_disc=PosscreenArabic.df2.format(Double.parseDouble(itm_disc));
			    		print_price=10-perc_disc.length();
		    			for(int i=0; i<print_price;i++)
		    			{
		    				space+=" ";
		    			}
		    			String gap="";
		    			double grand_ttl=grand_total;
			    		String grnd_ttl=PosscreenArabic.df2.format(grand_ttl);
			    		
						int grand_price=15-grnd_ttl.length();
						for(int i=0; i<grand_price;i++)
						{
							gap+=" ";
						}
						Runtime.getRuntime().exec("cmd /c \"CLS>"+PosscreenArabic.display_port+"&&ECHO DISC PERC "+space+""+PosscreenArabic.df2.format(Double.parseDouble(itm_disc))+">"+PosscreenArabic.display_port+"&&ECHO TOTAL"+""+gap+""+PosscreenArabic.df2.format(grand_total)+">"+PosscreenArabic.display_port+"\"");
						PosscreenArabic.txtDisc.setText("");
						PosscreenArabic.txtDisc.requestFocus();
					}
	    			PosscreenArabic.get_user_validate_dt(company, username,store,reg_num);
				}
				else
				{
					posscreen.frmRegisterNoS.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(posscreen.frmRegisterNoS, "Maximum Sale Discount is "+posscreen.disc_amt+" %");
					posscreen.txtDisc.requestFocus();
				}
				
			}
			else
			{
				String amount=new String(textField_12.getText()); 
				if(Double.parseDouble(posscreen.disc_amt)>=Double.parseDouble(amount))
				{
					double desc=0;
					double grand_total=0;
					double total=0;
					double vatpric=0;
					double tax_rate = 0;
					double ttl_bf_disc=0;
					double ttl_af_disc=0;
					for(int i=0;i<posscreen.model.getRowCount();i++)
					{
						int sel_table=posscreen.table.getSelectedRow();
						String vattype=posscreen.table.getModel().getValueAt(i, 10).toString();
						double unit_price=Double.parseDouble(posscreen.table.getValueAt(i, 4).toString());
						double qty=Double.parseDouble(posscreen.table.getValueAt(i, 5).toString());
						if(i==sel_table)
						{
							String disc_type=posscreen.table.getValueAt(sel_table, 15).toString();
							String disc_item=posscreen.table.getValueAt(sel_table, 14).toString();
							if(disc_type.equals("N") || disc_type.equals("ID"))
							{
								if(disc_item.equals("0"))
								{
									tax_rate=Double.parseDouble(posscreen.table.getValueAt(sel_table, 16).toString());
									posscreen.table.setValueAt(posscreen.df2.format(Double.parseDouble(amount)), sel_table, 6);
									if(vattype.equals("EXC"))
									{
										desc=((unit_price*qty)*(Double.parseDouble(amount)))/100;
										//desc_amt+=desc;
										total+=(unit_price*qty)-desc;
										vatpric+=((unit_price*qty)-desc)*tax_rate/100;
										double total_line=(unit_price*qty)-desc;
										double line_vat=((unit_price*qty)-desc)*tax_rate/100;
										posscreen.table.setValueAt(posscreen.df2.format(total_line), sel_table, 7);
										posscreen.table.setValueAt(posscreen.df2.format(line_vat), sel_table, 8);
										posscreen.table.setValueAt(posscreen.df2.format(total_line+line_vat), sel_table, 9);
										if(posscreen.gs.length>1)
										{
											SecondDisplay.table.setValueAt(posscreen.df2.format(total_line), sel_table, 7);
											SecondDisplay.table.setValueAt(posscreen.df2.format(line_vat), sel_table, 8);
											SecondDisplay.table.setValueAt(posscreen.df2.format(total_line+line_vat), sel_table, 9);
										}
									}
									if(vattype.equals("INC"))
									{
										desc=(((unit_price*qty)*100/105)*(Double.parseDouble(amount)))/100;
										//desc_amt+=desc;
										total+=((unit_price*100/105)*qty)-desc;
										vatpric+=(((unit_price*100/105)*qty)-desc)*tax_rate/100;
										double total_line=((unit_price)*qty*100/105)-desc;
										double line_vat=(((unit_price)*qty*100/105)-desc)*tax_rate/100;
										
										posscreen.table.setValueAt(posscreen.df2.format(total_line), sel_table, 7);
										posscreen.table.setValueAt(posscreen.df2.format(line_vat), sel_table, 8);
										posscreen.table.setValueAt(posscreen.df2.format(total_line+line_vat), sel_table, 9);
										if(posscreen.gs.length>1)
										{
											SecondDisplay.table.setValueAt(posscreen.df2.format(total_line), sel_table, 7);
											SecondDisplay.table.setValueAt(posscreen.df2.format(line_vat), sel_table, 8);
											SecondDisplay.table.setValueAt(posscreen.df2.format(total_line+line_vat), sel_table, 9);
										}
									}
									if(vattype.equals("NON"))
									{
										desc=((unit_price*qty)*(Double.parseDouble(amount)))/100;
										//desc_amt+=desc;
										total+=((unit_price)*qty)-desc;
										vatpric+=0;
										posscreen.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), sel_table, 7);
										posscreen.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), sel_table, 9);
										if(posscreen.gs.length>1)
										{
											SecondDisplay.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), sel_table, 7);
											SecondDisplay.table.setValueAt(posscreen.df2.format(((unit_price)*qty)-desc), sel_table, 9);
										}
									}
									if(Double.parseDouble(amount)==0)
									{
										posscreen.table.setValueAt("N", sel_table, 15);
										if(posscreen.gs.length>1)
										{
											SecondDisplay.table.setValueAt("N", sel_table, 15);
										}
									}
									else
									{
										posscreen.table.setValueAt("ID", sel_table, 15);
										if(posscreen.gs.length>1)
										{
											SecondDisplay.table.setValueAt("ID", sel_table, 15);
										}
									}
									
								}
								else
								{
									total+=Double.parseDouble(posscreen.table.getValueAt(sel_table, 7).toString());
									vatpric+=Double.parseDouble(posscreen.table.getValueAt(sel_table, 8).toString());
								}
								
							}
							else
							{
								total+=Double.parseDouble(posscreen.table.getValueAt(i, 7).toString());
								vatpric+=Double.parseDouble(posscreen.table.getValueAt(i, 8).toString());
							}
						}
						else
						{
							total+=Double.parseDouble(posscreen.table.getValueAt(i, 7).toString());
							vatpric+=Double.parseDouble(posscreen.table.getValueAt(i, 8).toString());
						}
						
						
						double totl=Double.parseDouble(posscreen.table.getValueAt(i, 7).toString());
						double vt=Double.parseDouble(posscreen.table.getValueAt(i, 8).toString());
						
						if(vattype.equals("INC"))
						{
							ttl_af_disc+=totl+vt;
						}
						else
						{
							ttl_af_disc+=totl;
						}
						
						String ttl_before=posscreen.df2.format(unit_price*qty);
						
						ttl_bf_disc+=Double.parseDouble(ttl_before);
					}
					String before_disc=posscreen.df2.format(ttl_bf_disc);
					String after_disc=posscreen.df2.format(ttl_af_disc);
					double ttl_disc_perc=((Double.parseDouble(before_disc)-Double.parseDouble(after_disc))*100/Double.parseDouble(before_disc));
					if(Double.isNaN(ttl_disc_perc))
					{
						ttl_disc_perc=0;
					}
					double ttl_disc_amt=Double.parseDouble(before_disc)-Double.parseDouble(after_disc);
					
	    			String itm_disc=Double.toString(ttl_disc_perc);
	    			if(!itm_disc.equals(""))
					{
	    				grand_total=total+vatpric;
	    				
	    				double checksum=0;
	    				if(posscreen.allow_roundoff.equals("1"))
	    				{
	    					String nettotal=posscreen.df2.format(grand_total);
				    		String splitnumber=String.valueOf(nettotal).split("\\.")[1];
				    		int number_decimal=Integer.parseInt(splitnumber);
				    		
				    		if(number_decimal>0 && number_decimal<13 )
				    		{
				    			checksum=0-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>12 && number_decimal<26 )
				    		{
				    			checksum=25-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>25 && number_decimal<38 )
				    		{
				    			checksum=25-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>37 && number_decimal<51 )
				    		{
				    			checksum=50-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>50 && number_decimal<63 )
				    		{
				    			checksum=50-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>62 && number_decimal<76 )
				    		{
				    			checksum=75-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>75 && number_decimal<88 )
				    		{
				    			checksum=75-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
				    		if(number_decimal>87 && number_decimal<101 )
				    		{
				    			checksum=100-number_decimal;
				    			grand_total=Double.parseDouble(nettotal)+(checksum/100);
				    		}
	    				}
	    				posscreen.lblNewLabel_1.setText(Double.toString(checksum/100));
	    				if(posscreen.gs.length>1)
						{
	    				SecondDisplay.lblNewLabel_1.setText(Double.toString(checksum/100));
						}
			    		
	    				posscreen.textField.setText(posscreen.df2.format(total));
	    				posscreen.textField_3.setText(posscreen.df2.format(grand_total));
	    				posscreen.textField_1.setText(posscreen.df2.format(vatpric));
	    				posscreen.textField_2.setText(posscreen.df2.format(ttl_disc_amt));
	    				posscreen.textField_5.setText(posscreen.df2.format(Double.parseDouble(itm_disc))+"%");
						if(posscreen.gs.length>1)
						{
						SecondDisplay.textField.setText(posscreen.df2.format(total));
						SecondDisplay.textField_3.setText(posscreen.df2.format(grand_total));
						SecondDisplay.textField_1.setText(posscreen.df2.format(vatpric));
						SecondDisplay.textField_2.setText(posscreen.df2.format(ttl_disc_amt));
						SecondDisplay.textField_5.setText(posscreen.df2.format(Double.parseDouble(itm_disc))+"%");
						}
						String space="";
			    		int print_price;
			    		String perc_disc=posscreen.df2.format(Double.parseDouble(itm_disc));
			    		print_price=10-perc_disc.length();
		    			for(int i=0; i<print_price;i++)
		    			{
		    				space+=" ";
		    			}
		    			String gap="";
		    			double grand_ttl=grand_total;
			    		String grnd_ttl=posscreen.df2.format(grand_ttl);
			    		
						int grand_price=15-grnd_ttl.length();
						for(int i=0; i<grand_price;i++)
						{
							gap+=" ";
						}
						Runtime.getRuntime().exec("cmd /c \"CLS>"+posscreen.display_port+"&&ECHO DISC PERC "+space+""+posscreen.df2.format(Double.parseDouble(itm_disc))+">"+posscreen.display_port+"&&ECHO TOTAL"+""+gap+""+posscreen.df2.format(grand_total)+">"+posscreen.display_port+"\"");
						posscreen.txtDisc.setText("");
						posscreen.txtDisc.requestFocus();
					}
	    			posscreen.get_user_validate_dt(company, username,store,reg_num);
				}
				else
				{
					posscreen.frmRegisterNoS.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(posscreen.frmRegisterNoS, "Maximum Sale Discount is "+posscreen.disc_amt+" %");
					posscreen.txtDisc.requestFocus();
				}
				
			}
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
