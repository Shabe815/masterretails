package sales_master;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 * Duplicate / settled invoice browser for Restaurant Mode.
 *
 * Constructor kept compatible with RestaurantPOS.openDuplicateBills():
 * new duplicate_invoice(dbName, username, storeName, posReg, Printer, version, decimals)
 *
 * This screen deliberately updates the settlement header to type='VOID' rather than
 * deleting financial records. PAYCODE status is updated exactly as in the old screen.
 */
public class duplicate_invoice extends JFrame {
    private static final long serialVersionUID = 1L;

    private final String company;
    private final String username;
    private final String storeName;
    private final String registerName;
    private final String printerName;
    private final String version;
    private final String decimalPlaces;
    private final RestaurantPOS posOwner;

    private final JTable table = new JTable();
    private final DefaultTableModel model = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) { return false; }
    };
    private final JTextField receiptFilter = new JTextField(12);
    private final JTextField registerFilter = new JTextField(8);
    private final JTextField customerFilter = new JTextField(12);
    private final JComboBox<String> typeFilter =
            new JComboBox<String>(new String[] {"ALL", "PAYCODE", "CASH", "VOID"});
    private final JLabel status = new JLabel(" ");

    private int receiptColumn = -1;
    private int registerColumn = -1;
    private int typeColumn = -1;
    private int customerColumn = -1;

    public duplicate_invoice(RestaurantPOS posOwner, String company, String username,
            String storeName, String registerName, String printerName, String version,
            String decimalPlaces) {
        super("Duplicate / Settled Bills" + (version == null ? "" : " " + version));
        if (posOwner == null) {
            throw new IllegalArgumentException("Active RestaurantPOS instance is required.");
        }
        this.posOwner = posOwner;
        this.company = safeIdentifier(company);
        this.username = username;
        this.storeName = storeName;
        this.registerName = registerName;
        this.printerName = printerName;
        this.version = version;
        this.decimalPlaces = decimalPlaces;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1180, 730);
        setMinimumSize(new Dimension(960, 620));
        setLocationRelativeTo(null);
        buildUi();
        loadInvoices();
        setVisible(true);
    }

    private void buildUi() {
        JPanel root = new JPanel(new BorderLayout(0, 14));
        root.setBackground(new Color(243, 246, 248));
        root.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));

        JPanel header = new JPanel(new BorderLayout(14, 0));
        header.setBackground(new Color(38, 50, 56));
        header.setBorder(BorderFactory.createEmptyBorder(16, 20, 16, 20));
        JLabel badge = new JLabel("POS", JLabel.CENTER);
        badge.setFont(new Font("SansSerif", Font.BOLD, 13));
        badge.setForeground(Color.WHITE);
        badge.setOpaque(true);
        badge.setBackground(new Color(0, 121, 107));
        badge.setBorder(BorderFactory.createEmptyBorder(9, 12, 9, 12));
        JPanel heading = new JPanel(new BorderLayout(0, 3));
        heading.setOpaque(false);
        JLabel title = new JLabel("Settled Bill History");
        title.setFont(new Font("SansSerif", Font.BOLD, 21));
        title.setForeground(Color.WHITE);
        JLabel subtitle = new JLabel("Search invoices, review details, print, recall, or void a bill.");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitle.setForeground(new Color(207, 216, 220));
        heading.add(title, BorderLayout.NORTH);
        heading.add(subtitle, BorderLayout.SOUTH);
        header.add(badge, BorderLayout.WEST);
        header.add(heading, BorderLayout.CENTER);
        root.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout(0, 12));
        content.setOpaque(false);
        JPanel top = new JPanel(new BorderLayout(0, 10));
        top.setOpaque(false);

        JPanel filters = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        filters.setBackground(Color.WHITE);
        filters.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 236)),
            BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        styleFilterField(receiptFilter);
        styleFilterField(registerFilter);
        styleFilterField(customerFilter);
        typeFilter.setFont(new Font("SansSerif", Font.PLAIN, 13));
        typeFilter.setPreferredSize(new Dimension(110, 32));
        filters.add(new JLabel("Receipt:"));
        filters.add(receiptFilter);
        filters.add(new JLabel("Register:"));
        filters.add(registerFilter);
        filters.add(new JLabel("Customer:"));
        filters.add(customerFilter);
        filters.add(new JLabel("Type:"));
        filters.add(typeFilter);

        JButton filterButton = new JButton("Filter");
        styleActionButton(filterButton, new Color(0, 121, 107));
        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { applyFilters(); }
        });
        JButton refreshButton = new JButton("Refresh");
        styleActionButton(refreshButton, new Color(69, 90, 100));
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { loadInvoices(); }
        });
        filters.add(filterButton);
        filters.add(refreshButton);
        JLabel filterHeading = new JLabel("SEARCH & FILTER");
        filterHeading.setFont(new Font("SansSerif", Font.BOLD, 11));
        filterHeading.setForeground(new Color(96, 110, 120));
        top.add(filterHeading, BorderLayout.NORTH);
        top.add(filters, BorderLayout.SOUTH);

        table.setModel(model);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setFillsViewportHeight(true);
        styleTable();
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createEmptyBorder());
        tableScroll.getViewport().setBackground(Color.WHITE);
        JPanel tableCard = new JPanel(new BorderLayout(0, 8));
        tableCard.setBackground(Color.WHITE);
        tableCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(225, 231, 235)),
            BorderFactory.createEmptyBorder(12, 14, 12, 14)));
        JLabel tableHeading = new JLabel("INVOICE RECORDS");
        tableHeading.setFont(new Font("SansSerif", Font.BOLD, 11));
        tableHeading.setForeground(new Color(96, 110, 120));
        tableCard.add(tableHeading, BorderLayout.NORTH);
        tableCard.add(tableScroll, BorderLayout.CENTER);
        content.add(top, BorderLayout.NORTH);
        content.add(tableCard, BorderLayout.CENTER);
        root.add(content, BorderLayout.CENTER);

        JPanel actions = new JPanel(new BorderLayout(10, 0));
        actions.setOpaque(false);
        actions.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        JPanel actionButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 2));
        actionButtons.setOpaque(false);
        JButton printButton = new JButton("Print");
        styleActionButton(printButton, new Color(69, 90, 100));
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { printSelected(); }
        });
        JButton viewButton = new JButton("View Bill");
        styleActionButton(viewButton, new Color(21, 101, 192));
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { viewSelected(); }
        });
        JButton voidButton = new JButton("Void Settled Invoice");
        styleActionButton(voidButton, new Color(198, 40, 40));
        voidButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { voidSelected(); }
        });
        JButton recallButton = new JButton("Recall Bill");
        styleActionButton(recallButton, new Color(0, 121, 107));
        recallButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { try {
				recallSelected();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} }
        });
        actionButtons.add(printButton);
        actionButtons.add(viewButton);
        actionButtons.add(recallButton);
        actionButtons.add(voidButton);
        status.setFont(new Font("SansSerif", Font.PLAIN, 12));
        status.setForeground(new Color(96, 110, 120));
        actions.add(status, BorderLayout.WEST);
        actions.add(actionButtons, BorderLayout.EAST);
        root.add(actions, BorderLayout.SOUTH);

        setContentPane(root);
    }

    private void loadInvoices() {
        model.setRowCount(0);
        model.setColumnCount(0);
        status.setText("Loading...");
        String sql = "SELECT reg_num, receipt_no, created_date, `time`, cust_name, qty, disc, total, vat, type, grand_total "
                + "FROM " + company + ".mr_pos_settlement_cash";
        try (Connection con = Dbsetup.local_server.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            ResultSetMetaData md = rs.getMetaData();
            int count = md.getColumnCount();
            int idColumn = -1;
            for (int i = 1; i <= count; i++) {
                String label = md.getColumnLabel(i);
                if (label == null || label.trim().length() == 0) label = md.getColumnName(i);
                if ("id".equalsIgnoreCase(label == null ? "" : label.trim())) {
                    idColumn = i;
                } else {
                    model.addColumn(label);
                }
            }
            findImportantColumns();
            while (rs.next()) {
                Object[] row = new Object[count - (idColumn > 0 ? 1 : 0)];
                int modelColumn = 0;
                for (int i = 1; i <= count; i++) {
                    if (i == idColumn) continue;
                    row[modelColumn++] = rs.getObject(i);
                }
                model.addRow(row);
            }
            findImportantColumns();
            TableRowSorter<DefaultTableModel> sorter =
                    new TableRowSorter<DefaultTableModel>(model);
            table.setRowSorter(sorter);
            applyFilters();
            status.setText(model.getRowCount() + " invoices loaded");
        } catch (Exception ex) {
            status.setText("Load failed");
            JOptionPane.showMessageDialog(this,
                    "Could not load settled invoices:\n" + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void styleTable() {
        table.setRowHeight(32);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setForeground(new Color(38, 50, 56));
        table.setBackground(Color.WHITE);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(232, 237, 240));
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setSelectionBackground(new Color(224, 242, 241));
        table.setSelectionForeground(new Color(38, 50, 56));
        table.setRowMargin(1);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 12));
        header.setBackground(new Color(238, 243, 246));
        header.setForeground(new Color(55, 71, 79));
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 36));
        header.setReorderingAllowed(false);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable source, Object value,
                    boolean selected, boolean focused, int row, int column) {
                JLabel cell = (JLabel) super.getTableCellRendererComponent(
                    source, value, selected, focused, row, column);
                cell.setFont(source.getFont());
                if (selected) {
                    cell.setBackground(new Color(0, 121, 107));
                    cell.setForeground(Color.WHITE);
                } else {
                    cell.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 250, 252));
                    cell.setForeground(new Color(38, 50, 56));
                }
                cell.setBorder(BorderFactory.createEmptyBorder(0, 9, 0, 9));
                int modelColumn = source.convertColumnIndexToModel(column);
                String columnName = source.getModel().getColumnName(modelColumn);
                String text = value == null ? "" : value.toString();
                if (!selected && "type".equalsIgnoreCase(columnName)) {
                    if ("VOID".equalsIgnoreCase(text)) {
                        cell.setBackground(new Color(255, 235, 238));
                        cell.setForeground(new Color(183, 28, 28));
                    } else if ("PAYCODE".equalsIgnoreCase(text)) {
                        cell.setBackground(new Color(232, 240, 254));
                        cell.setForeground(new Color(21, 101, 192));
                    } else if ("CASH".equalsIgnoreCase(text)) {
                        cell.setBackground(new Color(232, 245, 233));
                        cell.setForeground(new Color(46, 125, 50));
                    }
                    cell.setFont(new Font("SansSerif", Font.BOLD, 12));
                    cell.setHorizontalAlignment(SwingConstants.CENTER);
                } else if ("total".equalsIgnoreCase(columnName)
                        || "grand_total".equalsIgnoreCase(columnName)) {
                    cell.setHorizontalAlignment(SwingConstants.RIGHT);
                } else {
                    cell.setHorizontalAlignment(SwingConstants.LEFT);
                }
                return cell;
            }
        });
    }

    private void styleFilterField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 32));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(207, 216, 220)),
            BorderFactory.createEmptyBorder(4, 7, 4, 7)));
    }

    private void styleActionButton(JButton button, Color color) {
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 13, 8, 13));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void findImportantColumns() {
        receiptColumn = findColumn("receipt_no");
        registerColumn = findColumn("reg_num");
        typeColumn = findColumn("type");
        customerColumn = findColumn("cust_name");
    }

    private int findColumn(String name) {
        for (int i = 0; i < model.getColumnCount(); i++) {
            if (name.equalsIgnoreCase(model.getColumnName(i))) return i;
        }
        return -1;
    }

    private void applyFilters() {
        if (!(table.getRowSorter() instanceof TableRowSorter)) return;
        @SuppressWarnings("unchecked")
        TableRowSorter<DefaultTableModel> sorter =
                (TableRowSorter<DefaultTableModel>) table.getRowSorter();
        final String receipt = receiptFilter.getText().trim().toLowerCase(Locale.ENGLISH);
        final String register = registerFilter.getText().trim().toLowerCase(Locale.ENGLISH);
        final String customer = customerFilter.getText().trim().toLowerCase(Locale.ENGLISH);
        final String type = String.valueOf(typeFilter.getSelectedItem());
        sorter.setRowFilter(new javax.swing.RowFilter<DefaultTableModel, Integer>() {
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> e) {
                if (!contains(e, receiptColumn, receipt)) return false;
                if (!contains(e, registerColumn, register)) return false;
                if (!contains(e, customerColumn, customer)) return false;
                if (!"ALL".equalsIgnoreCase(type)
                        && !type.equalsIgnoreCase(value(e, typeColumn))) return false;
                return true;
            }
            private boolean contains(Entry<? extends DefaultTableModel, ? extends Integer> e,
                    int col, String text) {
                return text.length() == 0 || (col >= 0
                        && value(e, col).toLowerCase(Locale.ENGLISH).contains(text));
            }
            private String value(Entry<? extends DefaultTableModel, ? extends Integer> e, int col) {
                Object v = col < 0 ? null : e.getValue(col);
                return v == null ? "" : v.toString();
            }
        });
        status.setText(sorter.getViewRowCount() + " matching invoices");
    }

    private int selectedModelRow() {
        int viewRow = table.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(this, "Select an invoice first.");
            return -1;
        }
        return table.convertRowIndexToModel(viewRow);
    }

    private String selectedValue(int row, int col) {
        if (col < 0 || col >= model.getColumnCount()) return "";
        Object v = model.getValueAt(row, col);
        return v == null ? "" : v.toString();
    }

    private void viewSelected() {
        int row = selectedModelRow();
        if (row < 0) return;
        Connection con1 = Dbsetup.local_server.getConnection();
        int copy = 1;
        String receipt = selectedValue(row, receiptColumn);
        String register = selectedValue(row, registerColumn);
        try {
            report_master.Bill_template.report(
            		receipt,
                con1,
                RestaurantPOS.Printer,
                register,
                "DUP",
                company,
                1
            );
        } catch (Exception printEx) {
            // Payment is already committed: keep it settled if printing fails.
            JOptionPane.showMessageDialog(
                this,
                "Payment was saved, but the receipt could not be printed.\n"
                    + printEx.getMessage(),
                "Receipt printing",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void printSelected() {
    	int row = selectedModelRow();
        if (row < 0) return;
        Connection con1 = Dbsetup.local_server.getConnection();
        int copy = 1;
        String receipt = selectedValue(row, receiptColumn);
        String register = selectedValue(row, registerColumn);
        try {
            report_master.Bill_template.report(
            		receipt,
                con1,
                RestaurantPOS.Printer,
                register,
                "",
                company,
                1
            );
        } catch (Exception printEx) {
            // Payment is already committed: keep it settled if printing fails.
            JOptionPane.showMessageDialog(
                this,
                "Payment was saved, but the receipt could not be printed.\n"
                    + printEx.getMessage(),
                "Receipt printing",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void voidSelected() {
    	int row = selectedModelRow();
        if (row < 0) return;
        if (receiptColumn < 0 || registerColumn < 0 || typeColumn < 0) {
            JOptionPane.showMessageDialog(this,
                    "Required columns receipt_no, reg_num, or type are missing.");
            return;
        }

        String receipt = selectedValue(row, receiptColumn);
        String register = selectedValue(row, registerColumn);
        String type = selectedValue(row, typeColumn);
        if ("VOID".equalsIgnoreCase(type)) {
            JOptionPane.showMessageDialog(this, "This invoice is already VOID.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Void settled invoice " + receipt + " on register " + register + "?",
                "Confirm Void", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;

        if (!posOwner.hasActingUserPosControl("VoidSettledinvo")) {
            JOptionPane.showMessageDialog(this,
                    "You do not have permission to void settled invoices.",
                    "Permission Denied", JOptionPane.WARNING_MESSAGE);
            return;
        }

        
        
        String headerSql = "UPDATE " + company
                + ".mr_pos_settlement_cash SET type='VOID' "
                + "WHERE receipt_no=? AND reg_num=? AND (type IS NULL OR type<>'VOID')";
        String paycodeSql = "UPDATE " + company
                + ".mr_paycode_settlement SET status='1' "
                + "WHERE ref_receipt_num=? AND ref_reg_num=?";

        try (Connection con = Dbsetup.local_server.getConnection()) {
            boolean oldAutoCommit = con.getAutoCommit();
            try {
                con.setAutoCommit(false);

                // Preserve the original PAYCODE behavior exactly.
                if ("PAYCODE".equalsIgnoreCase(type)) {
                    try (PreparedStatement ps = con.prepareStatement(paycodeSql)) {
                        ps.setString(1, receipt);
                        ps.setString(2, register);
                        ps.executeUpdate();
                    }
                }

                int updated;
                try (PreparedStatement ps = con.prepareStatement(headerSql)) {
                    ps.setString(1, receipt);
                    ps.setString(2, register);
                    updated = ps.executeUpdate();
                }
                if (updated == 0) {
                    con.rollback();
                    JOptionPane.showMessageDialog(this,
                            "Invoice was not voided; it may already be VOID.");
                    return;
                }
                con.commit();
            } catch (Exception ex) {
                con.rollback();
                throw ex;
            } finally {
                con.setAutoCommit(oldAutoCommit);
            }

            JOptionPane.showMessageDialog(this,
                    "<html>Void Successfully<br>تم الفراغ بنجاح</html>");
            loadInvoices();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Unable to void invoice:\n" + ex.getMessage(),
                    "Void Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void recallSelected() throws SQLException {
        if (!hasPermission("ManageSettledInvoices")) {
            JOptionPane.showMessageDialog(this, "You do not have permission to manage settled invoices.",
                    "Permission Denied", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int row = selectedModelRow();
        if (row < 0) return;
        if (receiptColumn < 0 || registerColumn < 0 || typeColumn < 0) {
            JOptionPane.showMessageDialog(this, "Required receipt_no, reg_num, or type column is missing.");
            return;
        }
        String receipt = selectedValue(row, receiptColumn);
        String register = selectedValue(row, registerColumn);
        String type = selectedValue(row, typeColumn);
        if ("VOID".equalsIgnoreCase(type)) {
            JOptionPane.showMessageDialog(this, "A VOID invoice cannot be recalled.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Recall invoice " + receipt + " into Restaurant POS for editing?\n"
                + "The original settlement and payment history will remain unchanged.",
                "Confirm Recall", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;
        if (posOwner.recallSettledInvoice(receipt, register)) dispose();
    }

    private boolean hasPermission(String controlName) {
        try (Connection con = Dbsetup.local_server.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM " + company + ".mr_user_poscontrol inner join " + company + ".mr_users on ref_user_code=user_code WHERE user_name=?")) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return false;
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    if (controlName.equalsIgnoreCase(md.getColumnLabel(i))) {
                        Object v = rs.getObject(i);
                        return v != null && ("1".equals(v.toString())
                                || "true".equalsIgnoreCase(v.toString())
                                || "yes".equalsIgnoreCase(v.toString()));
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Could not verify permission " + controlName + ":\n" + ex.getMessage(),
                    "Permission Check Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private static String safeIdentifier(String value) {
        if (value == null || value.trim().isEmpty()) {
            // Use the supplied database/company name; blank names are not valid SQL identifiers.
            throw new IllegalArgumentException("Company/database name is required.");
        }
        String s = value.trim();
        if (!s.matches("[A-Za-z0-9_$]+")) {
            throw new IllegalArgumentException("Invalid company/database identifier: " + s);
        }
        return s;
    }

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
}
