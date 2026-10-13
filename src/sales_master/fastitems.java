package sales_master;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JMenuItem;

import org.json.JSONException;

import CafePro.Mainpage;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class fastitems {

    public static JFrame frame;

    private JTable table;
    private JTable table1;

    private static DefaultTableModel model;
    private static DefaultTableModel model1;

    private JLabel lblNewLabel;
    private JButton btnBack;

    private ArrayList<String> color_code = new ArrayList<String>();
    private ArrayList<String> fore_color_code = new ArrayList<String>();

    private ArrayList<String> color_item = new ArrayList<String>();
    private ArrayList<String> fore_color_item = new ArrayList<String>();

    private String[][] color_codes;
    private String[][] fore_color_codes;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @SuppressWarnings("static-access")
            public void run() {
                try {
                    fastitems window = new fastitems(
                            "mr_pro",
                            "S01",
                            null,
                            "",
                            ""
                    );

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
    public fastitems(
            String company,
            String store_name,
            String version,
            String username,
            String type
    ) throws JSONException {

        initialize(company, store_name, version, username, type);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(
            final String company,
            final String store_name,
            String version,
            final String username,
            final String type
    ) throws JSONException {

        frame = new JFrame(version);

        frame.setResizable(false);

        frame.getContentPane().setForeground(new Color(0, 0, 0));

        Image img = new ImageIcon(
                this.getClass().getResource("/cafepro.png")
        ).getImage();

        frame.setIconImage(img);

        frame.getContentPane().setBackground(Color.DARK_GRAY);

        frame.getContentPane().setLayout(null);

        frame.setLocationRelativeTo(null);

        /*
         * WINDOW EVENTS
         */
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                if (Mainpage.SoftLang.equals("Ar")) {

                    PosscreenArabic.txtDisc.requestFocus();
                    PosscreenArabic.btnFastItems.setEnabled(true);

                } else {

                    posscreen.txtDisc.requestFocus();
                    posscreen.btnFastItems.setEnabled(true);
                }
            }

            @Override
            public void windowIconified(WindowEvent we) {

                frame.setState(JFrame.NORMAL);
            }
        });

        /*
         * ---------------------------------------------------------
         * DEPARTMENT / SUB DEPARTMENT TABLE
         * ---------------------------------------------------------
         */

        JScrollPane scrollPane = new JScrollPane();

        scrollPane.getVerticalScrollBar()
                .setPreferredSize(new Dimension(25, 0));

        scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.setBounds(10, 42, 305, 561);

        frame.getContentPane().add(scrollPane);

        /*
         * TABLE MODEL
         */
        model = new DefaultTableModel() {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);

        JTableHeader header = table.getTableHeader();

        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(100, 35));

        table.setBackground(Color.DARK_GRAY);

        table.setFont(new Font("Serif", Font.BOLD, 15));

        table.setRowHeight(40);

        scrollPane.setViewportView(table);

        /*
         * LOAD DEPARTMENTS
         */
        dept_list(company);

        /*
         * DEPARTMENT TABLE RENDERER
         */
        table.setDefaultRenderer(
                Object.class,
                new TableCellRenderer() {

                    private DefaultTableCellRenderer DEFAULT_RENDERER =
                            new DefaultTableCellRenderer();

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {

                        Component c =
                                DEFAULT_RENDERER.getTableCellRendererComponent(
                                        table,
                                        value,
                                        isSelected,
                                        hasFocus,
                                        row,
                                        column
                                );

                        DEFAULT_RENDERER.setHorizontalAlignment(
                                JLabel.CENTER
                        );

                        if (color_code.isEmpty()
                                || row >= color_code.size()) {

                            if (row % 2 == 0) {

                                c.setBackground(Color.BLACK);
                                c.setForeground(Color.WHITE);

                            } else {

                                c.setBackground(Color.GRAY);
                                c.setForeground(Color.WHITE);
                            }

                        } else {

                            try {

                                c.setBackground(
                                        Color.decode(
                                                color_code.get(row)
                                        )
                                );

                                c.setForeground(
                                        Color.decode(
                                                fore_color_code.get(row)
                                        )
                                );

                            } catch (Exception e) {

                                c.setBackground(Color.DARK_GRAY);
                                c.setForeground(Color.WHITE);
                            }
                        }

                        if (isSelected) {

                            c.setBackground(Color.YELLOW);
                            c.setForeground(Color.BLACK);
                        }

                        return c;
                    }
                }
        );

        /*
         * ---------------------------------------------------------
         * DEPARTMENT TABLE CLICK
         * ---------------------------------------------------------
         */
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

                int row = table.getSelectedRow();

                if (row < 0) {
                    return;
                }

                String sts = "";

                if (table.getColumnCount() > 1
                        && table.getValueAt(row, 1) != null) {

                    sts = table.getValueAt(row, 1).toString();
                }

                String dept_name = "";
                String subdept_name = "";

                /*
                 * DEPARTMENT SELECTED
                 */
                if (sts.equals("")) {

                    dept_name =
                            table.getValueAt(row, 0).toString();

                    /*
                     * SHOW BACK BUTTON
                     */
                    btnBack.setVisible(true);

                }

                /*
                 * SUB DEPARTMENT SELECTED
                 */
                else {

                    subdept_name =
                            table.getValueAt(row, 0).toString();

                    dept_name =
                            table.getValueAt(row, 1).toString();

                    /*
                     * BACK BUTTON SHOULD REMAIN VISIBLE
                     */
                    btnBack.setVisible(true);
                }

                /*
                 * LOAD SUB DEPARTMENTS
                 */
                sub_dept(
                        company,
                        dept_name
                );

                /*
                 * LOAD ITEMS
                 */
                itemlist(
                        company,
                        dept_name,
                        sts,
                        subdept_name
                );
            }
        });

        /*
         * ---------------------------------------------------------
         * ITEMS TABLE
         * ---------------------------------------------------------
         */

        JScrollPane scrollPane_1 = new JScrollPane();

        scrollPane_1.setBounds(
                325,
                42,
                539,
                561
        );

        scrollPane_1.getVerticalScrollBar()
                .setPreferredSize(new Dimension(40, 0));

        frame.getContentPane().add(scrollPane_1);

        /*
         * ITEM TABLE MODEL
         */
        model1 = new DefaultTableModel() {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table1 = new JTable(model1);

        table1.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        table1.setBackground(Color.DARK_GRAY);

        table1.setRowHeight(100);

        model1.addColumn("");
        model1.addColumn("");
        model1.addColumn("");

        scrollPane_1.setViewportView(table1);

        table1.setShowGrid(false);

        Dimension dim = new Dimension(10, 10);

        table1.setIntercellSpacing(
                new Dimension(dim)
        );

        table1.setRowMargin(10);

        /*
         * ---------------------------------------------------------
         * TITLE / BREADCRUMB LABEL
         * ---------------------------------------------------------
         */

        lblNewLabel = new JLabel("");

        lblNewLabel.setForeground(Color.WHITE);

        lblNewLabel.setFont(
                new Font("Tahoma", Font.BOLD, 13)
        );

        /*
         * Leave space for BACK button
         */
        lblNewLabel.setBounds(
                327,
                11,
                350,
                25
        );

        frame.getContentPane().add(lblNewLabel);

        /*
         * ---------------------------------------------------------
         * BACK BUTTON
         * ---------------------------------------------------------
         */

        btnBack = new JButton("BACK");

        btnBack.setFont(
                new Font("Tahoma", Font.BOLD, 12)
        );

        btnBack.setForeground(Color.WHITE);

        btnBack.setBackground(Color.DARK_GRAY);

        btnBack.setFocusPainted(false);

        btnBack.setBorderPainted(true);

        /*
         * Position beside the heading
         */
        btnBack.setBounds(
                10,
                6,
                90,
                30
        );

        /*
         * IMPORTANT:
         * Hidden when department list is displayed.
         */
        btnBack.setVisible(false);

        frame.getContentPane().add(btnBack);

        /*
         * ---------------------------------------------------------
         * BACK BUTTON ACTION
         * ---------------------------------------------------------
         */
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                /*
                 * Return to department list
                 */
                lblNewLabel.setText("");

                /*
                 * Hide back button
                 */
                btnBack.setVisible(false);

                /*
                 * Load departments
                 */
                dept_list(company);

                /*
                 * Clear items
                 */
                int row =
                        model1.getRowCount();

                for (int i = row - 1; i >= 0; i--) {

                    model1.removeRow(i);
                }

                /*
                 * Refresh the UI
                 */
                table.clearSelection();

                table.revalidate();
                table.repaint();

                table1.revalidate();
                table1.repaint();
            }
        });

        /*
         * ---------------------------------------------------------
         * ITEM TABLE RENDERER
         * ---------------------------------------------------------
         */

        table1.setDefaultRenderer(
                Object.class,
                new TableCellRenderer() {

                    private DefaultTableCellRenderer DEFAULT_RENDERER =
                            new DefaultTableCellRenderer();

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {

                        Component c1 =
                                DEFAULT_RENDERER
                                        .getTableCellRendererComponent(
                                                table,
                                                value,
                                                isSelected,
                                                hasFocus,
                                                row,
                                                column
                                        );

                        DEFAULT_RENDERER.setHorizontalAlignment(
                                JLabel.CENTER
                        );

                        DEFAULT_RENDERER.setVerticalAlignment(
                                JLabel.TOP
                        );

                        if (color_item.isEmpty()
                                || color_codes == null
                                || row >= color_codes.length
                                || column >= color_codes[row].length) {

                            if (row % 2 == 0) {

                                c1.setBackground(Color.BLACK);
                                c1.setForeground(Color.WHITE);

                            } else {

                                c1.setBackground(Color.GRAY);
                                c1.setForeground(Color.WHITE);
                            }

                        } else {

                            try {

                                if (color_codes[row][column] != null) {

                                    c1.setBackground(
                                            Color.decode(
                                                    color_codes[row][column]
                                            )
                                    );
                                }

                                if (fore_color_codes[row][column] != null) {

                                    c1.setForeground(
                                            Color.decode(
                                                    fore_color_codes[row][column]
                                            )
                                    );
                                }

                            } catch (Exception e) {

                                c1.setBackground(Color.BLACK);
                                c1.setForeground(Color.WHITE);
                            }
                        }

                        return c1;
                    }
                }
        );

        /*
         * ---------------------------------------------------------
         * ITEM CLICK
         * ---------------------------------------------------------
         */

        table1.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

                try {

                    int row =
                            table1.getSelectedRow();

                    int col =
                            table1.getSelectedColumn();

                    if (row < 0 || col < 0) {
                        return;
                    }

                    Object value =
                            table1.getValueAt(row, col);

                    if (value == null) {
                        return;
                    }

                    String item_name =
                            value.toString();

                    if (item_name.equals("")) {
                        return;
                    }

                    item_name =
                            item_name.replaceAll(
                                    "\\<.*?\\>",
                                    ""
                            );

                    String itemname = "";

                    String[] button_name =
                            item_name.split("Dhs");

                    for (
                            int a = 0;
                            a < button_name.length - 1;
                            a++
                    ) {

                        if (a > 0) {
                            itemname += "-";
                        }

                        itemname += button_name[a];
                    }

                    Connection con =
                            Dbsetup.dbconn.getConnection();

                    Statement statement =
                            con.createStatement();

                    ResultSet resultSet =
                            statement.executeQuery(
                                    "select * from "
                                    + company
                                    + ".mr_tmp_item_master "
                                    + "where item_name='"
                                    + itemname
                                    + "' "
                            );

                    String barcode = "";

                    if (resultSet.next()) {

                        barcode =
                                resultSet.getString("barcode");

                        if (!barcode.equals("")) {

                            if (Mainpage.SoftLang.equals("Ar")) {

                                sales_master.PosscreenArabic
                                        .validate_barcode(
                                                company,
                                                barcode,
                                                username,
                                                store_name,
                                                type,
                                                con
                                        );

                                if (type.equals("return")) {

                                    PosscreenArabic
                                            .btnQuickReturn_1
                                            .setText(
                                                    "QUICK RETURN (F8)"
                                            );

                                    PosscreenArabic
                                            .txtDisc
                                            .setBackground(
                                                    Color.BLACK
                                            );

                                    PosscreenArabic
                                            .txtDisc
                                            .setCaretColor(
                                                    Color.WHITE
                                            );

                                    PosscreenArabic
                                            .txtDisc
                                            .setForeground(
                                                    Color.WHITE
                                            );

                                    PosscreenArabic
                                            .txtDisc
                                            .requestFocus();

                                    PosscreenArabic.returns = "";
                                }

                                if (
                                        PosscreenArabic
                                                .fastitem_Screen
                                                .equals("1")
                                ) {

                                    frame.setVisible(false);

                                    PosscreenArabic
                                            .btnFastItems
                                            .setEnabled(true);
                                }

                            } else {

                                sales_master.posscreen
                                        .validate_barcode(
                                                company,
                                                barcode,
                                                username,
                                                store_name,
                                                type,
                                                con
                                        );

                                if (type.equals("return")) {

                                    posscreen
                                            .btnQuickReturn_1
                                            .setText(
                                                    "QUICK RETURN (F8)"
                                            );

                                    posscreen
                                            .txtDisc
                                            .setBackground(
                                                    Color.BLACK
                                            );

                                    posscreen
                                            .txtDisc
                                            .setCaretColor(
                                                    Color.WHITE
                                            );

                                    posscreen
                                            .txtDisc
                                            .setForeground(
                                                    Color.WHITE
                                            );

                                    posscreen
                                            .txtDisc
                                            .requestFocus();

                                    posscreen.returns = "";
                                }

                                if (
                                        posscreen
                                                .fastitem_Screen
                                                .equals("1")
                                ) {

                                    frame.setVisible(false);

                                    posscreen
                                            .btnFastItems
                                            .setEnabled(true);
                                }
                            }
                        }
                    }

                } catch (Exception e) {

                    System.err.println(e);
                }
            }
        });

        /*
         * ---------------------------------------------------------
         * REFRESH
         * ---------------------------------------------------------
         */

        Image refresh =
                new ImageIcon(
                        this.getClass()
                                .getResource("/refresh.png")
                ).getImage();

        JMenuItem menuItem =
                new JMenuItem("Refresh");

        menuItem.addActionListener(
                new ActionListener() {

                    public void actionPerformed(
                            ActionEvent arg0) {

                        /*
                         * Reset title
                         */
                        lblNewLabel.setText("");

                        /*
                         * Hide Back button
                         */
                        btnBack.setVisible(false);

                        /*
                         * Load departments
                         */
                        dept_list(company);

                        /*
                         * Clear items
                         */
                        int row =
                                model1.getRowCount();

                        for (
                                int i = row - 1;
                                i >= 0;
                                i--
                        ) {

                            model1.removeRow(i);
                        }

                        table.revalidate();
                        table.repaint();

                        table1.revalidate();
                        table1.repaint();
                    }
                }
        );

        menuItem.setForeground(Color.WHITE);

        menuItem.setBorder(null);

        menuItem.setBackground(Color.DARK_GRAY);

        menuItem.setBounds(
                784,
                0,
                80,
                48
        );

        menuItem.setIcon(
                new ImageIcon(refresh)
        );

        frame.getContentPane().add(menuItem);

        /*
         * ---------------------------------------------------------
         * FRAME
         * ---------------------------------------------------------
         */

        frame.setTitle(
                "FAST ITEMS SCREEN"
        );

        frame.setBounds(
                10,
                10,
                890,
                665
        );

        frame.setAlwaysOnTop(true);

        frame.setVisible(true);
    }

    /*
     * =============================================================
     * DEPARTMENT LIST
     * =============================================================
     */

    private void dept_list(String company) {

        try {

            /*
             * IMPORTANT:
             * Clear old colors before loading departments.
             */
            color_code.clear();
            fore_color_code.clear();

            Connection con =
                    Dbsetup.dbconn.getConnection();

            Statement statement =
                    con.createStatement();

            String query =
                    "SELECT *,"
                    + "mr_department.color_code as backcolor,"
                    + "mr_department.fore_color_code as forecolor "
                    + "FROM "
                    + company
                    + ".mr_tmp_item_master "
                    + "join "
                    + company
                    + ".mr_department "
                    + "on dept_name=department "
                    + "where fast_item='1' "
                    + "group by department "
                    + "order by dept_code asc";

            ResultSet resultSet =
                    statement.executeQuery(query);

            /*
             * Clear old rows
             */
            int row =
                    model.getRowCount();

            for (
                    int i = row - 1;
                    i >= 0;
                    i--
            ) {

                model.removeRow(i);
            }

            /*
             * Reset columns
             */
            model.setColumnCount(0);

            model.addColumn("DEPARTMENT");
            model.addColumn("");

            table.setAutoResizeMode(
                    JTable.AUTO_RESIZE_OFF
            );

            /*
             * Column widths
             */
            int columns =
                    model.getColumnCount();

            for (
                    int i = 0;
                    i < columns;
                    i++
            ) {

                TableColumn column =
                        table.getColumnModel()
                                .getColumn(i);

                if (i == 0) {

                    column.setPreferredWidth(304);

                } else if (i == 1) {

                    column.setPreferredWidth(1);
                }
            }

            /*
             * Add departments
             */
            while (resultSet.next()) {

                String name =
                        resultSet.getString(
                                "department"
                        );

                model.addRow(
                        new Object[]{
                                name,
                                ""
                        }
                );

                color_code.add(
                        resultSet.getString(
                                "backcolor"
                        )
                );

                fore_color_code.add(
                        resultSet.getString(
                                "forecolor"
                        )
                );
            }

            table.revalidate();
            table.repaint();

        } catch (Exception e) {

            System.err.println(e);
        }
    }

    /*
     * =============================================================
     * SUB DEPARTMENT LIST
     * =============================================================
     */

    private void sub_dept(
            String company,
            String dept_name
    ) {

        try {

            Connection con =
                    Dbsetup.dbconn.getConnection();

            Statement statement =
                    con.createStatement();

            String query =
                    "SELECT * FROM "
                    + company
                    + ".mr_tmp_item_master "
                    + "where fast_item='1' "
                    + "and department='"
                    + dept_name
                    + "' "
                    + "group by sub_dept "
                    + "order by sub_dept asc";

            ResultSet resultSet =
                    statement.executeQuery(query);

            /*
             * Clear old department rows
             */
            int row =
                    model.getRowCount();

            for (
                    int i = row - 1;
                    i >= 0;
                    i--
            ) {

                model.removeRow(i);
            }

            /*
             * Reset columns
             */
            model.setColumnCount(0);

            model.addColumn(
                    "SUB DEPARTMENT"
            );

            model.addColumn("");

            table.setAutoResizeMode(
                    JTable.AUTO_RESIZE_OFF
            );

            int columns =
                    model.getColumnCount();

            for (
                    int i = 0;
                    i < columns;
                    i++
            ) {

                TableColumn column =
                        table.getColumnModel()
                                .getColumn(i);

                if (i == 0) {

                    column.setPreferredWidth(304);

                } else if (i == 1) {

                    column.setPreferredWidth(1);
                }
            }

            /*
             * Add sub departments
             */
            while (resultSet.next()) {

                model.addRow(
                        new Object[]{
                                resultSet.getString(
                                        "sub_dept"
                                ),
                                resultSet.getString(
                                        "department"
                                )
                        }
                );
            }

            table.revalidate();
            table.repaint();

        } catch (Exception e) {

            System.err.println(e);
        }
    }

    /*
     * =============================================================
     * ITEM LIST
     * =============================================================
     */

    private void itemlist(
            String company,
            String dept_name,
            String sts,
            String subdept_name
    ) {

        try {

            /*
             * Clear item colors
             */
            color_item.clear();
            fore_color_item.clear();

            Connection con =
                    Dbsetup.dbconn.getConnection();

            Statement statement =
                    con.createStatement();

            String query = "";

            /*
             * DEPARTMENT ITEMS
             */
            if (sts.equals("")) {

                query =
                        "select * from "
                        + company
                        + ".mr_tmp_item_master "
                        + "where department='"
                        + dept_name
                        + "' "
                        + "and fast_item='1' "
                        + "group by item_code,item_vareint "
                        + "order by item_name";

                lblNewLabel.setText(
                        dept_name
                );

            }

            /*
             * SUB DEPARTMENT ITEMS
             */
            else {

                query =
                        "select * from "
                        + company
                        + ".mr_tmp_item_master "
                        + "where department='"
                        + dept_name
                        + "' "
                        + "and sub_dept='"
                        + subdept_name
                        + "' "
                        + "and fast_item='1' "
                        + "group by item_code,item_vareint "
                        + "order by item_name";

                lblNewLabel.setText(
                        dept_name
                        + " -> "
                        + subdept_name
                );
            }

            ResultSet resultSet =
                    statement.executeQuery(query);

            /*
             * Clear old items
             */
            int row =
                    model1.getRowCount();

            for (
                    int i = row - 1;
                    i >= 0;
                    i--
            ) {

                model1.removeRow(i);
            }

            int x = 0;
            int y = 0;

            String a = "";
            String b = "";
            String c = "";

            String[] item_name = null;

            String item = "";
            String item_disp = "";
            String item_tl = "";

            /*
             * Start first row
             */
            model1.addRow(
                    new Object[]{
                            a,
                            b,
                            c
                    }
            );

            /*
             * Load items
             */
            while (resultSet.next()) {

                color_item.add(
                        resultSet.getString(
                                "color_code"
                        )
                );

                fore_color_item.add(
                        resultSet.getString(
                                "fore_color_code"
                        )
                );

                String sp =
                        resultSet.getString("sp");

                String selling = "";

                String shrt_nme =
                        resultSet.getString(
                                "item_name"
                        );

                /*
                 * Reset item display
                 */
                item = "";
                item_tl = "";
                item_disp = "";

                if (shrt_nme != null
                        && shrt_nme.length() > 1) {

                    item_name =
                            shrt_nme.split(
                                    "(?<=\\G.{15})"
                            );

                    for (
                            int v = 0;
                            v < item_name.length;
                            v++
                    ) {

                        if (v == 0) {

                            item_tl =
                                    item_name[v];

                        } else {

                            item +=
                                    item_name[v];
                        }

                        selling = sp;

                        item_disp =
                                "<html>"
                                + "<span STYLE='font-size:11px'>"
                                + item_tl
                                + item
                                + "</span>"
                                + "<br>"
                                + "<span STYLE='font-size:14px'>"
                                + "Dhs "
                                + selling
                                + "</span>"
                                + "</html>";
                    }
                }

                /*
                 * Column 0
                 */
                if (x == 0) {

                    a = item_disp;

                    model1.setValueAt(
                            a,
                            y,
                            0
                    );
                }

                /*
                 * Column 1
                 */
                if (x == 1) {

                    b = item_disp;

                    model1.setValueAt(
                            b,
                            y,
                            1
                    );
                }

                /*
                 * Column 2
                 */
                if (x == 2) {

                    c = item_disp;

                    model1.setValueAt(
                            c,
                            y,
                            2
                    );
                }

                /*
                 * Start new row after 3 items
                 */
                if (x == 2) {

                    x = 0;

                    model1.addRow(
                            new Object[]{
                                    "",
                                    "",
                                    ""
                            }
                    );

                    y++;

                } else {

                    x++;
                }
            }

            /*
             * -----------------------------------------------------
             * COLOR ARRAYS
             * -----------------------------------------------------
             */

            color_codes =
                    new String[
                            table1.getRowCount()
                    ][
                            table1.getColumnCount()
                    ];

            fore_color_codes =
                    new String[
                            table1.getRowCount()
                    ][
                            table1.getColumnCount()
                    ];

            int d = 0;

            for (
                    int v = 0;
                    v < table1.getRowCount();
                    v++
            ) {

                for (
                        int w = 0;
                        w < table1.getColumnCount();
                        w++
                ) {

                    Object value =
                            model1.getValueAt(
                                    v,
                                    w
                            );

                    if (
                            value != null
                            && !value.toString().equals("")
                            && d < color_item.size()
                    ) {

                        color_codes[v][w] =
                                color_item.get(d);

                        fore_color_codes[v][w] =
                                fore_color_item.get(d);

                    } else {

                        color_codes[v][w] =
                                "#000000";

                        fore_color_codes[v][w] =
                                "#000000";
                    }

                    d++;
                }
            }

            table1.revalidate();
            table1.repaint();

        } catch (Exception e) {

            System.err.println(e);
        }
    }
}