package sales_master;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class Miscposscreen {

	private JFrame frmMiscelleneousOptions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Miscposscreen window = new Miscposscreen();
					window.frmMiscelleneousOptions.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Miscposscreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMiscelleneousOptions = new JFrame();
		frmMiscelleneousOptions.setVisible(true);
		frmMiscelleneousOptions.setTitle("Miscelleneous Options:");
		frmMiscelleneousOptions.setBounds(100, 100, 702, 416);
		frmMiscelleneousOptions.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmMiscelleneousOptions.getContentPane().setLayout(null);
		
		JButton btnNoSale = new JButton("1- NO SALE");
		btnNoSale.setToolTipText("Click on this button to open cash drawer");
		btnNoSale.setForeground(new Color(176, 224, 230));
		btnNoSale.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNoSale.setBackground(Color.DARK_GRAY);
		btnNoSale.setBounds(12, 11, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(btnNoSale);
		
		JButton btnDuplicate = new JButton("2 - DUP.INVOICE");
		btnDuplicate.setToolTipText("Click on this button to open cash drawer");
		btnDuplicate.setForeground(Color.WHITE);
		btnDuplicate.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDuplicate.setBackground(Color.DARK_GRAY);
		btnDuplicate.setBounds(124, 11, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(btnDuplicate);
		
		JButton btnCustpayment = new JButton("3 - CUST.PAYMENT");
		btnCustpayment.setToolTipText("Click on this button to open cash drawer");
		btnCustpayment.setForeground(Color.WHITE);
		btnCustpayment.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCustpayment.setBackground(Color.DARK_GRAY);
		btnCustpayment.setBounds(236, 11, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(btnCustpayment);
		
		JButton btncustStatement = new JButton("4-CUST. STATEMENT");
		btncustStatement.setToolTipText("Click on this button to open cash drawer");
		btncustStatement.setForeground(Color.WHITE);
		btncustStatement.setBorder(new LineBorder(new Color(0, 0, 0)));
		btncustStatement.setBackground(Color.DARK_GRAY);
		btncustStatement.setBounds(348, 11, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(btncustStatement);
		
		JButton btnDupinvhistory = new JButton("5- DUP.INV.HISTORY");
		btnDupinvhistory.setToolTipText("Click on this button to open cash drawer");
		btnDupinvhistory.setForeground(Color.WHITE);
		btnDupinvhistory.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDupinvhistory.setBackground(Color.DARK_GRAY);
		btnDupinvhistory.setBounds(460, 11, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(btnDupinvhistory);
		
		JButton btnSupppayment = new JButton("6- SUPP.PAYMENT");
		btnSupppayment.setToolTipText("Click on this button to open cash drawer");
		btnSupppayment.setForeground(Color.WHITE);
		btnSupppayment.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnSupppayment.setBackground(Color.DARK_GRAY);
		btnSupppayment.setBounds(572, 11, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(btnSupppayment);
		
		JButton btnstockEnq = new JButton("7-STOCK ENQ.");
		btnstockEnq.setToolTipText("Click on this button to open cash drawer");
		btnstockEnq.setForeground(Color.WHITE);
		btnstockEnq.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnstockEnq.setBackground(Color.DARK_GRAY);
		btnstockEnq.setBounds(12, 82, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(btnstockEnq);
		
		JButton btnVoidSettled = new JButton("8- Void Settled Invoice");
		btnVoidSettled.setToolTipText("Click on this button to open cash drawer");
		btnVoidSettled.setForeground(Color.WHITE);
		btnVoidSettled.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnVoidSettled.setBackground(Color.DARK_GRAY);
		btnVoidSettled.setBounds(124, 82, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(btnVoidSettled);
		
		JButton button_7 = new JButton("9-");
		button_7.setToolTipText("Click on this button to open cash drawer");
		button_7.setForeground(Color.WHITE);
		button_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_7.setBackground(Color.DARK_GRAY);
		button_7.setBounds(236, 82, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_7);
		
		JButton button_8 = new JButton("10-");
		button_8.setToolTipText("Click on this button to open cash drawer");
		button_8.setForeground(Color.WHITE);
		button_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_8.setBackground(Color.DARK_GRAY);
		button_8.setBounds(348, 82, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_8);
		
		JButton button_9 = new JButton("11-");
		button_9.setToolTipText("Click on this button to open cash drawer");
		button_9.setForeground(Color.WHITE);
		button_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_9.setBackground(Color.DARK_GRAY);
		button_9.setBounds(460, 82, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_9);
		
		JButton button_10 = new JButton("1- NO SALE");
		button_10.setToolTipText("Click on this button to open cash drawer");
		button_10.setForeground(Color.WHITE);
		button_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_10.setBackground(Color.DARK_GRAY);
		button_10.setBounds(572, 82, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_10);
		
		JButton button_11 = new JButton("1- NO SALE");
		button_11.setToolTipText("Click on this button to open cash drawer");
		button_11.setForeground(Color.WHITE);
		button_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_11.setBackground(Color.DARK_GRAY);
		button_11.setBounds(12, 153, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_11);
		
		JButton button_12 = new JButton("1- NO SALE");
		button_12.setToolTipText("Click on this button to open cash drawer");
		button_12.setForeground(Color.WHITE);
		button_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_12.setBackground(Color.DARK_GRAY);
		button_12.setBounds(124, 153, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_12);
		
		JButton button_13 = new JButton("1- NO SALE");
		button_13.setToolTipText("Click on this button to open cash drawer");
		button_13.setForeground(Color.WHITE);
		button_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_13.setBackground(Color.DARK_GRAY);
		button_13.setBounds(236, 153, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_13);
		
		JButton button_14 = new JButton("1- NO SALE");
		button_14.setToolTipText("Click on this button to open cash drawer");
		button_14.setForeground(Color.WHITE);
		button_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_14.setBackground(Color.DARK_GRAY);
		button_14.setBounds(348, 153, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_14);
		
		JButton button_15 = new JButton("1- NO SALE");
		button_15.setToolTipText("Click on this button to open cash drawer");
		button_15.setForeground(Color.WHITE);
		button_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_15.setBackground(Color.DARK_GRAY);
		button_15.setBounds(460, 153, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_15);
		
		JButton button_16 = new JButton("1- NO SALE");
		button_16.setToolTipText("Click on this button to open cash drawer");
		button_16.setForeground(Color.WHITE);
		button_16.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_16.setBackground(Color.DARK_GRAY);
		button_16.setBounds(572, 153, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_16);
		
		JButton button_17 = new JButton("1- NO SALE");
		button_17.setToolTipText("Click on this button to open cash drawer");
		button_17.setForeground(Color.WHITE);
		button_17.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_17.setBackground(Color.DARK_GRAY);
		button_17.setBounds(12, 224, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_17);
		
		JButton button_18 = new JButton("1- NO SALE");
		button_18.setToolTipText("Click on this button to open cash drawer");
		button_18.setForeground(Color.WHITE);
		button_18.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_18.setBackground(Color.DARK_GRAY);
		button_18.setBounds(124, 224, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_18);
		
		JButton button_19 = new JButton("1- NO SALE");
		button_19.setToolTipText("Click on this button to open cash drawer");
		button_19.setForeground(Color.WHITE);
		button_19.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_19.setBackground(Color.DARK_GRAY);
		button_19.setBounds(236, 224, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_19);
		
		JButton button_20 = new JButton("1- NO SALE");
		button_20.setToolTipText("Click on this button to open cash drawer");
		button_20.setForeground(Color.WHITE);
		button_20.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_20.setBackground(Color.DARK_GRAY);
		button_20.setBounds(460, 224, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_20);
		
		JButton button_21 = new JButton("1- NO SALE");
		button_21.setToolTipText("Click on this button to open cash drawer");
		button_21.setForeground(Color.WHITE);
		button_21.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_21.setBackground(Color.DARK_GRAY);
		button_21.setBounds(572, 224, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_21);
		
		JButton button_22 = new JButton("1- NO SALE");
		button_22.setToolTipText("Click on this button to open cash drawer");
		button_22.setForeground(Color.WHITE);
		button_22.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_22.setBackground(Color.DARK_GRAY);
		button_22.setBounds(348, 224, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_22);
		
		JButton button_23 = new JButton("1- NO SALE");
		button_23.setToolTipText("Click on this button to open cash drawer");
		button_23.setForeground(Color.WHITE);
		button_23.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_23.setBackground(Color.DARK_GRAY);
		button_23.setBounds(12, 295, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_23);
		
		JButton button_24 = new JButton("1- NO SALE");
		button_24.setToolTipText("Click on this button to open cash drawer");
		button_24.setForeground(Color.WHITE);
		button_24.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_24.setBackground(Color.DARK_GRAY);
		button_24.setBounds(124, 295, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_24);
		
		JButton button_25 = new JButton("1- NO SALE");
		button_25.setToolTipText("Click on this button to open cash drawer");
		button_25.setForeground(Color.WHITE);
		button_25.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_25.setBackground(Color.DARK_GRAY);
		button_25.setBounds(236, 295, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_25);
		
		JButton button_26 = new JButton("1- NO SALE");
		button_26.setToolTipText("Click on this button to open cash drawer");
		button_26.setForeground(Color.WHITE);
		button_26.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_26.setBackground(Color.DARK_GRAY);
		button_26.setBounds(348, 295, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_26);
		
		JButton button_27 = new JButton("1- NO SALE");
		button_27.setToolTipText("Click on this button to open cash drawer");
		button_27.setForeground(Color.WHITE);
		button_27.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_27.setBackground(Color.DARK_GRAY);
		button_27.setBounds(460, 295, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_27);
		
		JButton button_28 = new JButton("1- NO SALE");
		button_28.setToolTipText("Click on this button to open cash drawer");
		button_28.setForeground(Color.WHITE);
		button_28.setBorder(new LineBorder(new Color(0, 0, 0)));
		button_28.setBackground(Color.DARK_GRAY);
		button_28.setBounds(572, 295, 100, 60);
		frmMiscelleneousOptions.getContentPane().add(button_28);
	}
}
