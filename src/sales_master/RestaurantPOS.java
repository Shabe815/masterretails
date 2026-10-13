package sales_master;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 * Restaurant POS
 *
 * Modes:
 *
 * DINE IN       = dinein
 * HOME DELIVERY = delivery
 * TAKE AWAY     = takeaway
 * ONLINE        = online
 *
 * Hold Invoice Header:
 * mr_pos_hold_invoice
 *
 * Hold Invoice Items:
 * mr_pos_hold_invoice_items
 */
public class RestaurantPOS extends JFrame {

    private static final long serialVersionUID = 1L;

    // =========================================================
    // COMPANY DATABASE
    // =========================================================

    private String company;

    // =========================================================
    // MAIN PANELS
    // =========================================================

    private JPanel centerPanel;
    private JPanel categoryContainer;
    private JPanel categoryPanel;
    private JPanel tablePanel;
    private JPanel itemPanel;

    private JPanel centerContent;
    private CardLayout centerCardLayout;

    // =========================================================
    // LABELS
    // =========================================================

    private JLabel lblOrder;
    private JLabel lblTable;
    private JLabel lblInvoice;

    private JLabel lblSubTotal;
    private JLabel lblTax;
    private JLabel lblTotal;

    // =========================================================
    // ORDER TABLE
    // =========================================================

    private JTable orderTable;
    private DefaultTableModel orderModel;

    // =========================================================
    // CURRENT POS MODE
    // =========================================================

    private String selectedMode = "";

    private String selectedCategory = "";

    // =========================================================
    // CURRENT TABLE
    // =========================================================

    private String selectedTable = "";

    // =========================================================
    // CURRENT HOLD INVOICE
    // =========================================================

    private String currentRegNum = "";

    private String currentReceiptNo = "";

    // =========================================================
    // ORDER ITEMS
    // =========================================================

    private List<OrderItem> orderItems =
            new ArrayList<OrderItem>();

    // =========================================================
    // TAX
    // =========================================================

    private double taxPercent = 5.0;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public RestaurantPOS(String company) {

        this.company = company;

        setTitle("Restaurant POS");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setMinimumSize(
                new Dimension(
                        1024,
                        768
                )
        );

        setSize(
                new Dimension(
                        1280,
                        800
                )
        );

        setLocationRelativeTo(null);

        getContentPane().setLayout(
                new BorderLayout()
        );


        // =====================================================
        // LEFT MENU
        // =====================================================

        JPanel leftPanel = new JPanel();

        leftPanel.setPreferredSize(
                new Dimension(
                        160,
                        0
                )
        );

        leftPanel.setMinimumSize(
                new Dimension(
                        150,
                        0
                )
        );

        leftPanel.setLayout(
                new GridLayout(
                        5,
                        1,
                        5,
                        5
                )
        );

        leftPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        5,
                        5,
                        5
                )
        );


        // =====================================================
        // DINE IN
        // =====================================================

        JButton buttonDineIn =
                createMenuButton(
                        "DINE IN",
                        new Color(
                                46,
                                125,
                                50
                        ),
                        Color.WHITE
                );


        // =====================================================
        // HOME DELIVERY
        // =====================================================

        JButton buttonHomeDelivery =
                createMenuButton(
                        "HOME DELIVERY",
                        new Color(
                                211,
                                47,
                                47
                        ),
                        Color.WHITE
                );


        // =====================================================
        // TAKE AWAY
        // =====================================================

        JButton buttonTakeAway =
                createMenuButton(
                        "TAKE AWAY",
                        new Color(
                                255,
                                193,
                                7
                        ),
                        Color.BLACK
                );


        // =====================================================
        // ONLINE
        // =====================================================

        JButton buttonOnline =
                createMenuButton(
                        "ONLINE",
                        new Color(
                                30,
                                100,
                                200
                        ),
                        Color.WHITE
                );


        // =====================================================
        // ORDERS
        // =====================================================

        JButton buttonOrders =
                createMenuButton(
                        "ORDERS",
                        Color.BLACK,
                        Color.WHITE
                );


        leftPanel.add(
                buttonDineIn
        );

        leftPanel.add(
                buttonHomeDelivery
        );

        leftPanel.add(
                buttonTakeAway
        );

        leftPanel.add(
                buttonOnline
        );

        leftPanel.add(
                buttonOrders
        );


        getContentPane().add(
                leftPanel,
                BorderLayout.WEST
        );


        // =====================================================
        // CENTER PANEL
        // =====================================================

        centerPanel =
                new JPanel();

        centerPanel.setLayout(
                new BorderLayout()
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        5,
                        5,
                        5
                )
        );

        getContentPane().add(
                centerPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // TOP INFORMATION PANEL
        // =====================================================

        JPanel topInfoPanel =
                new JPanel(
                        new BorderLayout()
                );

        topInfoPanel.setPreferredSize(
                new Dimension(
                        0,
                        55
                )
        );


        // =====================================================
        // ORDER LABEL
        // =====================================================

        lblOrder =
                new JLabel(
                        "DINE IN"
                );

        lblOrder.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        lblOrder.setHorizontalAlignment(
                SwingConstants.LEFT
        );


        // =====================================================
        // TABLE LABEL
        // =====================================================

        lblTable =
                new JLabel(
                        "SELECT TABLE"
                );

        lblTable.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        lblTable.setHorizontalAlignment(
                SwingConstants.RIGHT
        );


        // =====================================================
        // INVOICE NUMBER
        // =====================================================

        lblInvoice =
                new JLabel(
                        "INVOICE: -"
                );

        lblInvoice.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        lblInvoice.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        topInfoPanel.add(
                lblOrder,
                BorderLayout.WEST
        );

        topInfoPanel.add(
                lblInvoice,
                BorderLayout.CENTER
        );

        topInfoPanel.add(
                lblTable,
                BorderLayout.EAST
        );


        centerPanel.add(
                topInfoPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // CATEGORY CONTAINER
        // =====================================================

        categoryContainer =
                new JPanel(
                        new BorderLayout()
                );

        categoryContainer.setPreferredSize(
                new Dimension(
                        0,
                        100
                )
        );

        categoryContainer.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        0,
                        5,
                        0
                )
        );


        // =====================================================
        // CATEGORY PANEL
        // =====================================================

        categoryPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                8,
                                5
                        )
                );


        JScrollPane categoryScrollPane =
                new JScrollPane(
                        categoryPanel,
                        JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
                );

        categoryScrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );


        categoryContainer.add(
                categoryScrollPane,
                BorderLayout.CENTER
        );


        centerPanel.add(
                categoryContainer,
                BorderLayout.SOUTH
        );


        // =====================================================
        // CENTER CARD LAYOUT
        // =====================================================

        centerCardLayout =
                new CardLayout();

        centerContent =
                new JPanel(
                        centerCardLayout
                );


        // =====================================================
        // TABLE PANEL
        // =====================================================

        tablePanel =
                new JPanel(
                        new GridLayout(
                                0,
                                4,
                                10,
                                10
                        )
                );

        tablePanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );


        JScrollPane tableScrollPane =
                new JScrollPane(
                        tablePanel
                );

        tableScrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );


        centerContent.add(
                tableScrollPane,
                "TABLES"
        );


        // =====================================================
        // ITEM PANEL
        // =====================================================

        itemPanel =
                new JPanel(
                        new GridLayout(
                                0,
                                4,
                                10,
                                10
                        )
                );

        itemPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );


        JScrollPane itemScrollPane =
                new JScrollPane(
                        itemPanel
                );

        itemScrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );


        centerContent.add(
                itemScrollPane,
                "ITEMS"
        );


        centerPanel.add(
                centerContent,
                BorderLayout.CENTER
        );


        // =====================================================
        // RIGHT ORDER PANEL
        // =====================================================

        JPanel rightPanel =
                new JPanel();

        rightPanel.setPreferredSize(
                new Dimension(
                        400,
                        0
                )
        );

        rightPanel.setMinimumSize(
                new Dimension(
                        380,
                        0
                )
        );

        rightPanel.setLayout(
                new BorderLayout()
        );

        rightPanel.setBorder(
                BorderFactory.createMatteBorder(
                        0,
                        1,
                        0,
                        0,
                        new Color(
                                200,
                                200,
                                200
                        )
                )
        );


        // =====================================================
        // ORDER TABLE
        // =====================================================

        orderModel =
                new DefaultTableModel(
                        new Object[] {
                                "ITEM",
                                "QTY",
                                "PRICE",
                                "TOTAL"
                        },
                        0
                ) {

                    private static final long serialVersionUID =
                            1L;

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };


        orderTable =
                new JTable(
                        orderModel
                );

        orderTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        orderTable.setRowHeight(
                50
        );

        orderTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        orderTable.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        orderTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_LAST_COLUMN
        );


        // =====================================================
        // COLUMN WIDTHS
        // =====================================================

        orderTable.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(
                        210
                );

        orderTable.getColumnModel()
                .getColumn(0)
                .setMinWidth(
                        170
                );

        orderTable.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(
                        45
                );

        orderTable.getColumnModel()
                .getColumn(1)
                .setMaxWidth(
                        55
                );

        orderTable.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(
                        70
                );

        orderTable.getColumnModel()
                .getColumn(2)
                .setMaxWidth(
                        80
                );

        orderTable.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(
                        80
                );

        orderTable.getColumnModel()
                .getColumn(3)
                .setMaxWidth(
                        90
                );


        // =====================================================
        // ITEM RENDERER
        // =====================================================

        orderTable.getColumnModel()
                .getColumn(0)
                .setCellRenderer(
                        new OrderItemRenderer()
                );


        // =====================================================
        // CENTER RENDERER
        // =====================================================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        orderTable.getColumnModel()
                .getColumn(1)
                .setCellRenderer(
                        centerRenderer
                );


        // =====================================================
        // RIGHT RENDERER
        // =====================================================

        DefaultTableCellRenderer rightRenderer =
                new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        orderTable.getColumnModel()
                .getColumn(2)
                .setCellRenderer(
                        rightRenderer
                );

        orderTable.getColumnModel()
                .getColumn(3)
                .setCellRenderer(
                        rightRenderer
                );


        JScrollPane orderScrollPane =
                new JScrollPane(
                        orderTable
                );

        rightPanel.add(
                orderScrollPane,
                BorderLayout.CENTER
        );


        // =====================================================
        // TOTAL PANEL
        // =====================================================

        JPanel totalPanel =
                new JPanel();

        totalPanel.setLayout(
                new GridLayout(
                        3,
                        2,
                        5,
                        5
                )
        );

        totalPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        10,
                        5,
                        10
                )
        );

        JLabel lblSubTotalText =
                new JLabel(
                        "SUB TOTAL"
                );

        JLabel lblTaxText =
                new JLabel(
                        "TAX 5%"
                );

        JLabel lblTotalText =
                new JLabel(
                        "TOTAL"
                );

        lblSubTotalText.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        lblTaxText.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        lblTotalText.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        lblSubTotal =
                new JLabel(
                        "0.00"
                );

        lblTax =
                new JLabel(
                        "0.00"
                );

        lblTotal =
                new JLabel(
                        "0.00"
                );

        lblSubTotal.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        lblTax.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        lblTotal.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        lblTotal.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        totalPanel.add(lblSubTotalText);
        totalPanel.add(lblSubTotal);
        totalPanel.add(lblTaxText);
        totalPanel.add(lblTax);
        totalPanel.add(lblTotalText);
        totalPanel.add(lblTotal);

        // =========================================================
        // BOTTOM ACTION BUTTONS
        // =========================================================

        JPanel actionPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                5,
                                5
                        )
                );

        actionPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        10,
                        10,
                        10
                )
        );

        JButton buttonSettle =
                createActionButton(
                        "SETTLE",
                        new Color(46, 125, 50),
                        Color.WHITE
                );

        JButton buttonKotPrint =
                createActionButton(
                        "KOT PRINT",
                        new Color(30, 100, 200),
                        Color.WHITE
                );

        JButton buttonCheckBill =
                createActionButton(
                        "CHECK BILL",
                        new Color(0, 121, 107),
                        Color.WHITE
                );

        JButton buttonDuplicateBill =
                createActionButton(
                        "DUPLICATE BILL",
                        new Color(123, 31, 162),
                        Color.WHITE
                );

        JButton buttonManager =
                createActionButton(
                        "MANAGER",
                        new Color(245, 124, 0),
                        Color.WHITE
                );

        JButton buttonHold =
                createActionButton(
                        "HOLD",
                        new Color(255, 193, 7),
                        Color.BLACK
                );

        JButton buttonDelete =
                createActionButton(
                        "DELETE",
                        new Color(211, 47, 47),
                        Color.WHITE
                );

        actionPanel.add(buttonSettle);
        actionPanel.add(buttonKotPrint);
        actionPanel.add(buttonCheckBill);
        actionPanel.add(buttonDuplicateBill);
        actionPanel.add(buttonManager);
        actionPanel.add(buttonHold);
        actionPanel.add(buttonDelete);
        actionPanel.add(new JPanel());

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        bottomPanel.add(
                totalPanel,
                BorderLayout.NORTH
        );

        bottomPanel.add(
                actionPanel,
                BorderLayout.CENTER
        );

        rightPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =========================================================
        // ACTION BUTTON EVENTS
        // =========================================================

        buttonSettle.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showActionMessage("SETTLE");
                    }
                }
        );

        buttonKotPrint.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showActionMessage("KOT PRINT");
                    }
                }
        );

        buttonCheckBill.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showActionMessage("CHECK BILL");
                    }
                }
        );

        buttonDuplicateBill.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showActionMessage("DUPLICATE BILL");
                    }
                }
        );

        buttonManager.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showActionMessage("MANAGER");
                    }
                }
        );

        buttonHold.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showActionMessage("HOLD");
                    }
                }
        );

        buttonDelete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showActionMessage("DELETE");
                    }
                }
        );

        getContentPane().add(
                rightPanel,
                BorderLayout.EAST
        );


        // =====================================================
        // INITIAL STATE
        // =====================================================

        selectedMode =
                "dinein";

        selectedTable =
                "";

        currentRegNum =
                "";

        currentReceiptNo =
                "";


        loadTables();

        loadCategories();


        centerCardLayout.show(
                centerContent,
                "TABLES"
        );


        categoryContainer.setVisible(
                false
        );


        // =====================================================
        // DINE IN
        // =====================================================

        buttonDineIn.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        selectedMode =
                                "dinein";

                        selectedCategory =
                                "";

                        selectedTable =
                                "";

                        currentRegNum =
                                "";

                        currentReceiptNo =
                                "";

                        clearOrder();


                        lblOrder.setText(
                                "DINE IN"
                        );

                        lblTable.setText(
                                "SELECT TABLE"
                        );


                        categoryContainer.setVisible(
                                false
                        );


                        loadTables();


                        centerCardLayout.show(
                                centerContent,
                                "TABLES"
                        );


                        centerContent.revalidate();
                        centerContent.repaint();

                        centerPanel.revalidate();
                        centerPanel.repaint();
                    }
                }
        );


        // =====================================================
        // HOME DELIVERY
        // =====================================================

        buttonHomeDelivery.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        selectedMode =
                                "delivery";

                        selectedCategory =
                                "";

                        selectedTable =
                                "";

                        currentRegNum =
                                "";

                        currentReceiptNo =
                                "";

                        clearOrder();


                        lblOrder.setText(
                                "HOME DELIVERY"
                        );

                        lblTable.setText(
                                ""
                        );


                        categoryContainer.setVisible(
                                true
                        );


                        loadCategories();


                        centerCardLayout.show(
                                centerContent,
                                "ITEMS"
                        );


                        centerContent.revalidate();
                        centerContent.repaint();
                    }
                }
        );


        // =====================================================
        // TAKE AWAY
        // =====================================================

        buttonTakeAway.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        selectedMode =
                                "takeaway";

                        selectedCategory =
                                "";

                        selectedTable =
                                "";

                        currentRegNum =
                                "";

                        currentReceiptNo =
                                "";

                        clearOrder();


                        lblOrder.setText(
                                "TAKE AWAY"
                        );

                        lblTable.setText(
                                ""
                        );


                        categoryContainer.setVisible(
                                true
                        );


                        loadCategories();


                        centerCardLayout.show(
                                centerContent,
                                "ITEMS"
                        );


                        centerContent.revalidate();
                        centerContent.repaint();
                    }
                }
        );


        // =====================================================
        // ONLINE
        // =====================================================

        buttonOnline.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        selectedMode =
                                "online";

                        selectedCategory =
                                "";

                        selectedTable =
                                "";

                        currentRegNum =
                                "";

                        currentReceiptNo =
                                "";

                        clearOrder();


                        lblOrder.setText(
                                "ONLINE"
                        );

                        lblTable.setText(
                                ""
                        );


                        categoryContainer.setVisible(
                                true
                        );


                        loadCategories();


                        centerCardLayout.show(
                                centerContent,
                                "ITEMS"
                        );


                        centerContent.revalidate();
                        centerContent.repaint();
                    }
                }
        );


        // =====================================================
        // ORDERS
        // =====================================================

        buttonOrders.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        selectedMode =
                                "";

                        selectedCategory =
                                "";

                        selectedTable =
                                "";

                        currentRegNum =
                                "";

                        currentReceiptNo =
                                "";

                        clearOrder();


                        lblOrder.setText(
                                "ORDERS"
                        );

                        lblTable.setText(
                                ""
                        );


                        categoryContainer.setVisible(
                                false
                        );
                    }
                }
        );
    }


    // =========================================================
    // CREATE ACTION BUTTON
    // =========================================================

    private JButton createActionButton(
            String text,
            Color background,
            Color foreground
    ) {

        JButton button =
                new JButton(
                        text
                );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setBackground(background);
        button.setForeground(foreground);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setMargin(
                new Insets(
                        4,
                        4,
                        4,
                        4
                )
        );

        return button;
    }


    // =========================================================
    // ACTION BUTTON PLACEHOLDER
    // =========================================================

    private void showActionMessage(
            String action
    ) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                action
                + " button clicked\nInvoice: "
                + currentReceiptNo,
                action,
                javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }


    // =========================================================
    // UPDATE INVOICE LABEL
    // =========================================================

    private void updateInvoiceLabel() {

        if (lblInvoice == null) {
            return;
        }

        if (currentReceiptNo == null
                || currentReceiptNo.length() == 0) {

            lblInvoice.setText(
                    "INVOICE: -"
            );

        } else {

            lblInvoice.setText(
                    "INVOICE: "
                    + currentReceiptNo
            );
        }
    }


    // =========================================================
    // CREATE MENU BUTTON
    // =========================================================

    private JButton createMenuButton(
            String text,
            Color background,
            Color foreground
    ) {

        JButton button =
                new JButton(
                        text
                );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setBackground(
                background
        );

        button.setForeground(
                foreground
        );

        button.setOpaque(
                true
        );

        button.setContentAreaFilled(
                true
        );

        button.setBorderPainted(
                false
        );

        button.setFocusPainted(
                false
        );

        button.setMargin(
                new Insets(
                        5,
                        5,
                        5,
                        5
                )
        );

        return button;
    }


    // =========================================================
    // LOAD TABLES
    // =========================================================

    private void loadTables() {

        tablePanel.removeAll();

        tablePanel.setLayout(
                new GridLayout(
                        0,
                        4,
                        10,
                        10
                )
        );


        Connection connection =
                null;

        Statement statement =
                null;

        ResultSet resultSet =
                null;


        int tableCount =
                0;


        try {

            connection =
                    Dbsetup.dbconn.getConnection();

            statement =
                    connection.createStatement();


            String query =
                    "SELECT table_num, chair_count "
                    + "FROM "
                    + company
                    + ".mr_kot_tables "
                    + "ORDER BY table_num ASC";


            resultSet =
                    statement.executeQuery(
                            query
                    );


            while (resultSet.next()) {

                tableCount++;


                final String tableNumber =
                        resultSet.getString(
                                "table_num"
                        );


                final int chairCount =
                        resultSet.getInt(
                                "chair_count"
                        );


                JButton tableButton =
                        new JButton(
                                "<html>"
                                + "<center>"
                                + "<b>"
                                + tableNumber
                                + "</b>"
                                + "<br>"
                                + chairCount
                                + " Chairs"
                                + "</center>"
                                + "</html>"
                        );


                tableButton.setPreferredSize(
                        new Dimension(
                                145,
                                80
                        )
                );


                tableButton.setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );


                tableButton.setBackground(
                        Color.WHITE
                );

                tableButton.setForeground(
                        Color.BLACK
                );

                tableButton.setOpaque(
                        true
                );

                tableButton.setContentAreaFilled(
                        true
                );

                tableButton.setFocusPainted(
                        false
                );


                tableButton.setBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        180,
                                        180,
                                        180
                                )
                        )
                );


                // =================================================
                // TABLE CLICK
                // =================================================

                tableButton.addActionListener(
                        new ActionListener() {

                            @Override
                            public void actionPerformed(
                                    ActionEvent e
                            ) {

                                selectDineInTable(
                                        tableNumber
                                );
                            }
                        }
                );


                tablePanel.add(
                        tableButton
                );
            }


            // =====================================================
            // NO TABLES
            // =====================================================

            if (tableCount == 0) {

                tablePanel.removeAll();

                tablePanel.setLayout(
                        new BorderLayout()
                );


                JLabel noTables =
                        new JLabel(
                                "NO TABLES FOUND"
                        );

                noTables.setHorizontalAlignment(
                        SwingConstants.CENTER
                );

                noTables.setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                18
                        )
                );


                tablePanel.add(
                        noTables,
                        BorderLayout.CENTER
                );
            }

        } catch (Exception e) {

            e.printStackTrace();


            tablePanel.removeAll();

            tablePanel.setLayout(
                    new BorderLayout()
            );


            JLabel errorLabel =
                    new JLabel(
                            "<html>"
                            + "<center>"
                            + "<b>"
                            + "TABLE LOAD ERROR"
                            + "</b>"
                            + "<br><br>"
                            + e.getMessage()
                            + "</center>"
                            + "</html>"
                    );


            errorLabel.setHorizontalAlignment(
                    SwingConstants.CENTER
            );


            tablePanel.add(
                    errorLabel,
                    BorderLayout.CENTER
            );

        } finally {

            try {

                if (resultSet != null) {
                    resultSet.close();
                }

            } catch (Exception e) {
            }


            try {

                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e) {
            }


            try {

                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
            }
        }


        tablePanel.revalidate();
        tablePanel.repaint();

        centerContent.revalidate();
        centerContent.repaint();
    }


    // =========================================================
    // SELECT DINE IN TABLE
    // =========================================================

    private void selectDineInTable(
            String tableNumber
    ) {

        selectedMode =
                "dinein";

        selectedTable =
                tableNumber;


        lblOrder.setText(
                "DINE IN"
        );

        lblTable.setText(
                "TABLE - "
                + tableNumber
        );


        // =====================================================
        // CLEAR PREVIOUS ORDER
        // =====================================================

        clearOrder();


        currentRegNum =
                "";

        currentReceiptNo =
                "";


        // =====================================================
        // CHECK EXISTING OPEN ORDER
        // =====================================================

        boolean existing =
                loadExistingHoldInvoice(
                        "dinein",
                        tableNumber
                );


        // =====================================================
        // CREATE NEW ORDER IF NOT FOUND
        // =====================================================

        if (!existing) {

            createNewHoldInvoice();
        }


        // =====================================================
        // SHOW CATEGORIES
        // =====================================================

        categoryContainer.setVisible(
                true
        );


        loadCategories();


        centerCardLayout.show(
                centerContent,
                "ITEMS"
        );


        centerContent.revalidate();
        centerContent.repaint();

        centerPanel.revalidate();
        centerPanel.repaint();
    }


    // =========================================================
    // LOAD EXISTING HOLD INVOICE
    // =========================================================

    private boolean loadExistingHoldInvoice(
            String mode,
            String tableNumber
    ) {

        Connection connection =
                null;

        Statement statement =
                null;

        ResultSet resultSet =
                null;


        boolean found =
                false;


        try {

            connection =
                    Dbsetup.dbconn.getConnection();

            statement =
                    connection.createStatement();


            String safeMode =
                    escapeSql(
                            mode
                    );


            String safeTable =
                    escapeSql(
                            tableNumber
                    );


            String query =
                    "SELECT "
                    + "reg_num, "
                    + "receipt_no "
                    + "FROM "
                    + company
                    + ".mr_pos_hold_invoice "
                    + "WHERE mode = '"
                    + safeMode
                    + "' "
                    + "AND table_num = '"
                    + safeTable
                    + "' "
                    + "AND Chkbill_Sts = 0 "
                    + "ORDER BY id DESC "
                    + "LIMIT 1";


            System.out.println(
                    "EXISTING ORDER QUERY:"
            );

            System.out.println(
                    query
            );


            resultSet =
                    statement.executeQuery(
                            query
                    );


            if (resultSet.next()) {

                found =
                        true;


                currentRegNum =
                        resultSet.getString(
                                "reg_num"
                        );


                currentReceiptNo =
                        resultSet.getString(
                                "receipt_no"
                        );

                updateInvoiceLabel();


                loadHoldInvoiceItems();


                refreshOrderTable();
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (resultSet != null) {
                    resultSet.close();
                }

            } catch (Exception e) {
            }


            try {

                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e) {
            }


            try {

                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
            }
        }


        return found;
    }


    // =========================================================
    // CREATE NEW HOLD INVOICE
    // =========================================================

    private void createNewHoldInvoice() {

        if (selectedMode == null
                || selectedMode.length() == 0) {

            return;
        }


        Connection connection =
                null;

        Statement statement =
                null;


        try {

            connection =
                    Dbsetup.dbconn.getConnection();

            statement =
                    connection.createStatement();


            // =================================================
            // GENERATE UNIQUE NUMBERS
            // =================================================

            String unique =
                    new SimpleDateFormat(
                            "yyyyMMddHHmmssSSS"
                    ).format(
                            new Date()
                    );


            currentRegNum =
                    "REG-"
                    + unique;


            currentReceiptNo =
                    "R-"
                    + unique;


            String currentUser =
                    System.getProperty(
                            "user.name"
                    );


            if (currentUser == null) {
                currentUser = "";
            }


            String safeUser =
                    escapeSql(
                            currentUser
                    );


            String safeMode =
                    escapeSql(
                            selectedMode
                    );


            String safeTable =
                    escapeSql(
                            selectedTable
                    );


            String query =
                    "INSERT INTO "
                    + company
                    + ".mr_pos_hold_invoice "
                    + "("
                    + "reg_num, "
                    + "receipt_no, "
                    + "`user`, "
                    + "`time`, "
                    + "total, "
                    + "cust_name, "
                    + "vat, "
                    + "disc, "
                    + "total_qty, "
                    + "store_id, "
                    + "created_date, "
                    + "cust_code, "
                    + "mode, "
                    + "table_num, "
                    + "delivery_user, "
                    + "cust_ph, "
                    + "Chkbill_Sts"
                    + ") "
                    + "VALUES "
                    + "("
                    + "'"
                    + escapeSql(
                            currentRegNum
                    )
                    + "',"
                    + "'"
                    + escapeSql(
                            currentReceiptNo
                    )
                    + "',"
                    + "'"
                    + safeUser
                    + "',"
                    + "CURTIME(),"
                    + "0,"
                    + "''"
                    + ","
                    + "0,"
                    + "0,"
                    + "0,"
                    + "'"
                    + escapeSql(
                            company
                    )
                    + "',"
                    + "CURDATE(),"
                    + "''"
                    + ","
                    + "'"
                    + safeMode
                    + "',"
                    + "'"
                    + safeTable
                    + "',"
                    + "''"
                    + ","
                    + "''"
                    + ","
                    + "0"
                    + ")";


            System.out.println(
                    "CREATE HOLD INVOICE:"
            );

            System.out.println(
                    query
            );


            statement.executeUpdate(
                    query
            );


        } catch (Exception e) {

            e.printStackTrace();

            currentRegNum = "";
            currentReceiptNo = "";
            updateInvoiceLabel();

        } finally {

            try {

                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e) {
            }


            try {

                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
            }
        }
    }


    // =========================================================
    // LOAD HOLD INVOICE ITEMS
    // =========================================================

    private void loadHoldInvoiceItems() {

        orderItems.clear();


        if (currentRegNum == null
                || currentRegNum.length() == 0
                || currentReceiptNo == null
                || currentReceiptNo.length() == 0) {

            return;
        }


        Connection connection =
                null;

        Statement statement =
                null;

        ResultSet resultSet =
                null;


        try {

            connection =
                    Dbsetup.dbconn.getConnection();

            statement =
                    connection.createStatement();


            String query =
                    "SELECT "
                    + "item_code, "
                    + "item_desc, "
                    + "unit_price, "
                    + "qty, "
                    + "total "
                    + "FROM "
                    + company
                    + ".mr_pos_hold_invoice_items "
                    + "WHERE ref_receipt_num = '"
                    + escapeSql(
                            currentReceiptNo
                    )
                    + "' "
                    + "AND ref_reg_no = '"
                    + escapeSql(
                            currentRegNum
                    )
                    + "' "
                    + "ORDER BY id ASC";


            System.out.println(
                    "LOAD ITEMS QUERY:"
            );

            System.out.println(
                    query
            );


            resultSet =
                    statement.executeQuery(
                            query
                    );


            while (resultSet.next()) {

                OrderItem item =
                        new OrderItem();


                item.itemCode =
                        resultSet.getString(
                                "item_code"
                        );


                item.itemName =
                        resultSet.getString(
                                "item_desc"
                        );


                item.quantity =
                        resultSet.getDouble(
                                "qty"
                        );


                item.price =
                        resultSet.getDouble(
                                "unit_price"
                        );


                item.total =
                        resultSet.getDouble(
                                "total"
                        );


                orderItems.add(
                        item
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (resultSet != null) {
                    resultSet.close();
                }

            } catch (Exception e) {
            }


            try {

                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e) {
            }


            try {

                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
            }
        }
    }


    // =========================================================
    // LOAD CATEGORIES
    // =========================================================

    private void loadCategories() {

        categoryPanel.removeAll();

        categoryPanel.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        8,
                        5
                )
        );


        Connection connection =
                null;

        Statement statement =
                null;

        ResultSet resultSet =
                null;


        try {

            connection =
                    Dbsetup.dbconn.getConnection();

            statement =
                    connection.createStatement();


            String mode =
                    getItemUnitForMode(
                            selectedMode
                    );


            String query =
                    "SELECT "
                    + "m.department AS category, "
                    + "d.dept_code, "
                    + "d.color_code AS backcolor, "
                    + "d.fore_color_code AS forecolor "
                    + "FROM "
                    + company
                    + ".mr_tmp_item_master m "
                    + "LEFT JOIN "
                    + company
                    + ".mr_department d "
                    + "ON d.dept_name = m.department "
                    + "WHERE m.unit = '"
                    + escapeSql(
                            mode
                    )
                    + "' "
                    + "GROUP BY "
                    + "m.department, "
                    + "d.dept_code, "
                    + "d.color_code, "
                    + "d.fore_color_code "
                    + "ORDER BY d.dept_code ASC";


            resultSet =
                    statement.executeQuery(
                            query
                    );


            while (resultSet.next()) {

                final String category =
                        resultSet.getString(
                                "category"
                        );


                String backColor =
                        resultSet.getString(
                                "backcolor"
                        );


                String foreColor =
                        resultSet.getString(
                                "forecolor"
                        );


                JButton categoryButton =
                        new JButton(
                                category
                        );


                // =================================================
                // BACK COLOR
                // =================================================

                try {

                    if (backColor != null
                            && backColor.trim().length() > 0) {

                        categoryButton.setBackground(
                                Color.decode(
                                        backColor
                                )
                        );

                    } else {

                        categoryButton.setBackground(
                                Color.WHITE
                        );
                    }

                } catch (Exception ex) {

                    categoryButton.setBackground(
                            Color.WHITE
                    );
                }


                // =================================================
                // FORE COLOR
                // =================================================

                try {

                    if (foreColor != null
                            && foreColor.trim().length() > 0) {

                        categoryButton.setForeground(
                                Color.decode(
                                        foreColor
                                )
                        );

                    } else {

                        categoryButton.setForeground(
                                Color.BLACK
                        );
                    }

                } catch (Exception ex) {

                    categoryButton.setForeground(
                            Color.BLACK
                    );
                }


                categoryButton.setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );


                categoryButton.setOpaque(
                        true
                );

                categoryButton.setContentAreaFilled(
                        true
                );

                categoryButton.setBorderPainted(
                        false
                );

                categoryButton.setFocusPainted(
                        false
                );


                // =================================================
                // DYNAMIC WIDTH
                // =================================================

                FontMetrics metrics =
                        categoryButton.getFontMetrics(
                                categoryButton.getFont()
                        );


                int textWidth =
                        metrics.stringWidth(
                                category
                        );


                int buttonWidth =
                        textWidth
                        + 45;


                if (buttonWidth < 145) {

                    buttonWidth =
                            145;
                }


                if (buttonWidth > 400) {

                    buttonWidth =
                            400;
                }


                categoryButton.setPreferredSize(
                        new Dimension(
                                buttonWidth,
                                65
                        )
                );


                // =================================================
                // CATEGORY CLICK
                // =================================================

                categoryButton.addActionListener(
                        new ActionListener() {

                            @Override
                            public void actionPerformed(
                                    ActionEvent e
                            ) {

                                selectedCategory =
                                        category;


                                loadItems(
                                        selectedCategory
                                );
                            }
                        }
                );


                categoryPanel.add(
                        categoryButton
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (resultSet != null) {
                    resultSet.close();
                }

            } catch (Exception e) {
            }


            try {

                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e) {
            }


            try {

                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
            }
        }


        categoryPanel.revalidate();
        categoryPanel.repaint();

        categoryContainer.revalidate();
        categoryContainer.repaint();
    }


    // =========================================================
    // GET ITEM UNIT FROM POS MODE
    // =========================================================

    private String getItemUnitForMode(
            String mode
    ) {

        if ("online".equalsIgnoreCase(
                mode
        )) {

            return "online";
        }


        return "indoor";
    }


    // =========================================================
    // LOAD ITEMS
    // =========================================================

    private void loadItems(
            String category
    ) {

        itemPanel.removeAll();

        itemPanel.setLayout(
                new GridLayout(
                        0,
                        4,
                        10,
                        10
                )
        );


        Connection connection =
                null;

        Statement statement =
                null;

        ResultSet resultSet =
                null;


        try {

            connection =
                    Dbsetup.dbconn.getConnection();

            statement =
                    connection.createStatement();


            String mode =
                    getItemUnitForMode(
                            selectedMode
                    );


            String safeCategory =
                    escapeSql(
                            category
                    );


            String query =
                    "SELECT "
                    + "item_code, "
                    + "item_name, "
                    + "department AS category, "
                    + "unit AS mode, "
                    + "sp AS selling_price "
                    + "FROM "
                    + company
                    + ".mr_tmp_item_master "
                    + "WHERE department = '"
                    + safeCategory
                    + "' "
                    + "AND unit = '"
                    + escapeSql(
                            mode
                    )
                    + "' "
                    + "ORDER BY item_name ASC";


            resultSet =
                    statement.executeQuery(
                            query
                    );


            while (resultSet.next()) {

                String itemCode =
                        resultSet.getString(
                                "item_code"
                        );


                String itemName =
                        resultSet.getString(
                                "item_name"
                        );


                double price =
                        resultSet.getDouble(
                                "selling_price"
                        );


                addItemButton(
                        itemCode,
                        itemName,
                        price
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (resultSet != null) {
                    resultSet.close();
                }

            } catch (Exception e) {
            }


            try {

                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e) {
            }


            try {

                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
            }
        }


        itemPanel.revalidate();
        itemPanel.repaint();

        centerContent.revalidate();
        centerContent.repaint();
    }


    // =========================================================
    // ADD ITEM BUTTON
    // =========================================================

    private void addItemButton(
            final String itemCode,
            final String itemName,
            final double price
    ) {

        JButton itemButton =
                new JButton(
                        "<html>"
                        + "<center>"
                        + itemName
                        + "<br>"
                        + String.format(
                                "%.2f",
                                price
                        )
                        + "</center>"
                        + "</html>"
                );


        itemButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );


        itemButton.setBackground(
                Color.WHITE
        );

        itemButton.setForeground(
                Color.BLACK
        );


        itemButton.setOpaque(
                true
        );

        itemButton.setContentAreaFilled(
                true
        );

        itemButton.setFocusPainted(
                false
        );


        itemButton.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                190,
                                190,
                                190
                        )
                )
        );


        itemButton.setPreferredSize(
                new Dimension(
                        150,
                        90
                )
        );


        itemButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        addItemToOrder(
                                itemCode,
                                itemName,
                                price
                        );
                    }
                }
        );


        itemPanel.add(
                itemButton
        );
    }


    // =========================================================
    // ADD ITEM TO ORDER
    // =========================================================

    private void addItemToOrder(
            String itemCode,
            String itemName,
            double price
    ) {

        // =====================================================
        // MAKE SURE HEADER EXISTS
        // =====================================================

        if (currentRegNum == null
                || currentRegNum.length() == 0
                || currentReceiptNo == null
                || currentReceiptNo.length() == 0) {

            createNewHoldInvoice();
        }


        if (currentRegNum == null
                || currentRegNum.length() == 0) {

            return;
        }


        if (currentReceiptNo == null
                || currentReceiptNo.length() == 0) {

            return;
        }


        // =====================================================
        // FIND EXISTING ITEM
        // =====================================================

        OrderItem existingItem =
                null;


        for (OrderItem item :
                orderItems) {

            if (item.itemCode.equals(
                    itemCode
            )) {

                existingItem =
                        item;

                break;
            }
        }


        // =====================================================
        // EXISTING ITEM
        // =====================================================

        if (existingItem != null) {

            existingItem.quantity =
                    existingItem.quantity
                    + 1;


            existingItem.total =
                    existingItem.quantity
                    * existingItem.price;


            updateExistingItemInDatabase(
                    existingItem
            );

        }

        // =====================================================
        // NEW ITEM
        // =====================================================

        else {

            OrderItem item =
                    new OrderItem();


            item.itemCode =
                    itemCode;


            item.itemName =
                    itemName;


            item.quantity =
                    1;


            item.price =
                    price;


            item.total =
                    price;


            orderItems.add(
                    item
            );


            insertItemIntoDatabase(
                    item
            );
        }


        // =====================================================
        // UPDATE HEADER
        // =====================================================

        updateHoldInvoiceHeader();


        // =====================================================
        // REFRESH SCREEN
        // =====================================================

        refreshOrderTable();
    }


    // =========================================================
    // INSERT ITEM INTO DATABASE
    // =========================================================

    private void insertItemIntoDatabase(
            OrderItem item
    ) {

        Connection connection =
                null;

        Statement statement =
                null;


        try {

            connection =
                    Dbsetup.dbconn.getConnection();

            statement =
                    connection.createStatement();


            String query =
                    "INSERT INTO "
                    + company
                    + ".mr_pos_hold_invoice_items "
                    + "("
                    + "ref_receipt_num, "
                    + "ref_reg_no, "
                    + "barcode, "
                    + "item_desc, "
                    + "item_code, "
                    + "unit, "
                    + "unit_price, "
                    + "qty, "
                    + "total, "
                    + "vat, "
                    + "discount, "
                    + "vat_type, "
                    + "created_date, "
                    + "cost, "
                    + "sp, "
                    + "disc_type, "
                    + "commited_qty, "
                    + "vat_perc, "
                    + "department, "
                    + "kot_number, "
                    + "comments_item, "
                    + "print_sts, "
                    + "cf, "
                    + "print_cmd, "
                    + "scale_sts"
                    + ") "
                    + "VALUES "
                    + "("
                    + "'"
                    + escapeSql(
                            currentReceiptNo
                    )
                    + "',"
                    + "'"
                    + escapeSql(
                            currentRegNum
                    )
                    + "',"
                    + "'"
                    + escapeSql(
                            item.itemCode
                    )
                    + "',"
                    + "'"
                    + escapeSql(
                            item.itemName
                    )
                    + "',"
                    + "'"
                    + escapeSql(
                            item.itemCode
                    )
                    + "',"
                    + "''"
                    + ","
                    + item.price
                    + ","
                    + item.quantity
                    + ","
                    + item.total
                    + ","
                    + "0,"
                    + "0,"
                    + "''"
                    + ","
                    + "CURDATE(),"
                    + "''"
                    + ","
                    + item.price
                    + ","
                    + "''"
                    + ","
                    + "''"
                    + ","
                    + "0,"
                    + "''"
                    + ","
                    + "0,"
                    + "''"
                    + ","
                    + "0,"
                    + "0,"
                    + "0,"
                    + "0"
                    + ")";


            System.out.println(
                    "INSERT ITEM:"
            );

            System.out.println(
                    query
            );


            statement.executeUpdate(
                    query
            );

            updateInvoiceLabel();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e) {
            }


            try {

                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
            }
        }
    }


    // =========================================================
    // UPDATE EXISTING ITEM
    // =========================================================

    private void updateExistingItemInDatabase(
            OrderItem item
    ) {

        Connection connection =
                null;

        Statement statement =
                null;


        try {

            connection =
                    Dbsetup.dbconn.getConnection();

            statement =
                    connection.createStatement();


            String query =
                    "UPDATE "
                    + company
                    + ".mr_pos_hold_invoice_items "
                    + "SET "
                    + "qty = "
                    + item.quantity
                    + ", total = "
                    + item.total
                    + " "
                    + "WHERE ref_receipt_num = '"
                    + escapeSql(
                            currentReceiptNo
                    )
                    + "' "
                    + "AND ref_reg_no = '"
                    + escapeSql(
                            currentRegNum
                    )
                    + "' "
                    + "AND item_code = '"
                    + escapeSql(
                            item.itemCode
                    )
                    + "'";


            System.out.println(
                    "UPDATE ITEM:"
            );

            System.out.println(
                    query
            );


            statement.executeUpdate(
                    query
            );

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e) {
            }


            try {

                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
            }
        }
    }


    // =========================================================
    // UPDATE HOLD INVOICE HEADER
    // =========================================================

    private void updateHoldInvoiceHeader() {

        if (currentRegNum == null
                || currentRegNum.length() == 0
                || currentReceiptNo == null
                || currentReceiptNo.length() == 0) {

            return;
        }


        double subtotal =
                calculateSubtotal();


        double vat =
                subtotal
                * taxPercent
                / 100.0;


        double total =
                subtotal
                + vat;


        double totalQty =
                calculateTotalQty();


        Connection connection =
                null;

        Statement statement =
                null;


        try {

            connection =
                    Dbsetup.dbconn.getConnection();

            statement =
                    connection.createStatement();


            String query =
                    "UPDATE "
                    + company
                    + ".mr_pos_hold_invoice "
                    + "SET "
                    + "total = "
                    + total
                    + ", vat = "
                    + vat
                    + ", total_qty = "
                    + totalQty
                    + " "
                    + "WHERE reg_num = '"
                    + escapeSql(
                            currentRegNum
                    )
                    + "' "
                    + "AND receipt_no = '"
                    + escapeSql(
                            currentReceiptNo
                    )
                    + "'";


            System.out.println(
                    "UPDATE HEADER:"
            );

            System.out.println(
                    query
            );


            statement.executeUpdate(
                    query
            );

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e) {
            }


            try {

                if (connection != null) {
                    connection.close();
                }

            } catch (Exception e) {
            }
        }
    }


    // =========================================================
    // CALCULATE SUBTOTAL
    // =========================================================

    private double calculateSubtotal() {

        double subtotal =
                0.0;


        for (OrderItem item :
                orderItems) {

            subtotal =
                    subtotal
                    + item.total;
        }


        return subtotal;
    }


    // =========================================================
    // CALCULATE TOTAL QTY
    // =========================================================

    private double calculateTotalQty() {

        double qty =
                0.0;


        for (OrderItem item :
                orderItems) {

            qty =
                    qty
                    + item.quantity;
        }


        return qty;
    }


    // =========================================================
    // REFRESH ORDER TABLE
    // =========================================================

    private void refreshOrderTable() {

        orderModel.setRowCount(
                0
        );


        double subtotal =
                0.0;


        for (OrderItem item :
                orderItems) {

            orderModel.addRow(
                    new Object[] {
                            item.itemName,
                            formatQty(
                                    item.quantity
                            ),
                            String.format(
                                    "%.2f",
                                    item.price
                            ),
                            String.format(
                                    "%.2f",
                                    item.total
                            )
                    }
            );


            subtotal =
                    subtotal
                    + item.total;
        }


        double tax =
                subtotal
                * taxPercent
                / 100.0;


        double total =
                subtotal
                + tax;


        lblSubTotal.setText(
                String.format(
                        "%.2f",
                        subtotal
                )
        );


        lblTax.setText(
                String.format(
                        "%.2f",
                        tax
                )
        );


        lblTotal.setText(
                String.format(
                        "%.2f",
                        total
                )
        );


        // =====================================================
        // DYNAMIC ROW HEIGHT
        // =====================================================

        for (int i = 0;
             i < orderModel.getRowCount();
             i++) {

            String name =
                    String.valueOf(
                            orderModel.getValueAt(
                                    i,
                                    0
                            )
                    );


            if (name.length() > 30) {

                orderTable.setRowHeight(
                        i,
                        70
                );

            } else {

                orderTable.setRowHeight(
                        i,
                        50
                );
            }
        }


        orderTable.revalidate();
        orderTable.repaint();
    }


    // =========================================================
    // FORMAT QTY
    // =========================================================

    private String formatQty(
            double qty
    ) {

        if (qty ==
                Math.floor(
                        qty
                )) {

            return String.format(
                    "%.0f",
                    qty
            );
        }


        return String.format(
                "%.3f",
                qty
        );
    }


    // =========================================================
    // CLEAR ORDER
    // =========================================================

    private void clearOrder() {

        orderItems.clear();

        orderModel.setRowCount(
                0
        );


        lblSubTotal.setText(
                "0.00"
        );

        lblTax.setText(
                "0.00"
        );

        lblTotal.setText(
                "0.00"
        );

        updateInvoiceLabel();
    }


    // =========================================================
    // ESCAPE SQL
    // =========================================================

    private String escapeSql(
            String value
    ) {

        if (value == null) {

            return "";
        }


        return value.replace(
                "'",
                "''"
        );
    }


    // =========================================================
    // ORDER ITEM CLASS
    // =========================================================

    private static class OrderItem {

        String itemCode;

        String itemName;

        double quantity;

        double price;

        double total;
    }


    // =========================================================
    // ORDER ITEM RENDERER
    // =========================================================

    private static class OrderItemRenderer
            extends DefaultTableCellRenderer {

        private static final long serialVersionUID =
                1L;


        @Override
        public void setValue(
                Object value
        ) {

            if (value == null) {

                setText("");

                return;
            }


            String text =
                    value.toString();


            text =
                    text.replace(
                            "&",
                            "&amp;"
                    );


            text =
                    text.replace(
                            "<",
                            "&lt;"
                    );


            text =
                    text.replace(
                            ">",
                            "&gt;"
                    );


            setText(
                    "<html>"
                    + "<div style='width:190px;'>"
                    + text
                    + "</div>"
                    + "</html>"
            );


            setVerticalAlignment(
                    SwingConstants.CENTER
            );
        }
    }


    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        java.awt.EventQueue.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {

                        RestaurantPOS frame =
                                new RestaurantPOS(
                                        "mr_pro"
                                );


                        frame.setVisible(
                                true
                        );
                    }
                }
        );
    }
}