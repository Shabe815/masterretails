package sales_master;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Independent restaurant settlement UI. It does not depend on the old retail
 * paycode screen or on pos screen classes. The caller supplies a handler that
 * persists the payment and completes the invoice.
 * Java 7 compatible.
 */
public class RestaurantSettlementScreen extends JDialog {
    private static final long serialVersionUID = 1L;

    private static final Color BG = new Color(245, 246, 250);
    private static final Color DARK = new Color(38, 50, 56);
    private static final Color PRIMARY = new Color(0, 121, 107);
    private static final Color GREEN = new Color(46, 125, 50);
    private static final Color BLUE = new Color(21, 101, 192);
    private static final Color DANGER = new Color(198, 40, 40);
    private static final Color MUTED = new Color(96, 109, 116);

    public interface SettlementHandler {
        /** Persist the payment and settle the invoice. Return false if it failed. */
        boolean confirmPayment(Payment payment) throws Exception;
    }

    public static class PaymentLine {
        public final String paycode;
        public final double amount;
        public final double exchangeRate;
        /** Amount multiplied by the exchange rate shown in column 3. */
        public final double convertedAmount;

        public PaymentLine(String paycode, double amount, double exchangeRate) {
            this.paycode = paycode == null ? "" : paycode;
            this.amount = amount;
            this.exchangeRate = exchangeRate;
            this.convertedAmount = amount * exchangeRate;
        }
    }

    public static class Payment {
        public final String invoiceNo;
        public final String customerCode;
        public final String customerName;
        /** Legacy convenience field: first paycode, or MULTIPLE when more than one is used. */
        public final String method;
        public final String reference;
        public final double amountDue;
        /** Sum of the entered row amounts, before any currency conversion. */
        public final double amountTendered;
        public final double change;
        public final List<PaymentLine> lines;

        private Payment(String invoiceNo, String customerCode, String customerName,String method, String reference, double amountDue,double amountTendered, double change, List<PaymentLine> lines) {
            this.invoiceNo = invoiceNo;
            this.customerCode = customerCode;
            this.customerName = customerName;
            this.method = method;
            this.reference = reference;
            this.amountDue = amountDue;
            this.amountTendered = amountTendered;
            this.change = change;
            this.lines = Collections.unmodifiableList(new ArrayList<PaymentLine>(lines));
        }
    }

    private final String invoiceNo;
    private final String customerCode;
    private final String customerName;
    private final double subtotal;
    private final double discount;
    private final double vat;
    private final double amountDue;
    private final int decimals;
    private final SettlementHandler handler;
    private final DecimalFormat moneyFormat;
    private final Connection connection;
    private final String schema;

    private JTextField activeAmountField;
    private JTextField txtReference;
    private JLabel lblDue;
    private JLabel lblPaid;
    private JLabel lblBalance;
    private JLabel lblEnteredTotal;
    private JButton btnConfirm;
    private final List<PaymentRow> paymentRows = new ArrayList<PaymentRow>();

    private static class PaymentRow {
        String paycode;
        double exchangeRate;
        JTextField amountField;
        PaymentRow(String paycode, double exchangeRate, JTextField amountField) {
            this.paycode = paycode;
            this.exchangeRate = exchangeRate;
            this.amountField = amountField;
        }
    }

    /** Backwards-compatible constructor. Without a DB connection, a simple CASH row is shown. 
     * @param dbName */
    public RestaurantSettlementScreen(Window owner, String invoiceNo,String customerCode, String customerName, double amountDue,int decimals, SettlementHandler handler,Connection con1, String dbName) 
    {
        this(owner, invoiceNo, customerCode, customerName, amountDue, 0, 0,amountDue, decimals,  handler,con1, dbName);
    }

    /** Preferred constructor: supplies invoice totals and the restaurant DB connection. 
     * @param handler2 
     * @param dbName 
     * @param handler2 
     * @param con1 */
    public RestaurantSettlementScreen(Window owner, String invoiceNo,String customerCode, String customerName, double subtotal, double discount,double vat, double amountDue, int decimals,  SettlementHandler handler, Connection con1, String schema) {
        super(owner, "Restaurant Settlement", ModalityType.APPLICATION_MODAL);
        this.invoiceNo = safe(invoiceNo);
        this.connection=con1;
        this.customerCode = safe(customerCode);
        this.customerName = safe(customerName);
        this.subtotal = subtotal;
        this.discount = discount;
        this.vat = vat;
        this.amountDue = Math.max(0.0, amountDue);
        this.decimals = Math.max(0, Math.min(4, decimals));
        //this.connection = connection;
        this.schema = safe(schema);
        this.handler = handler;
        this.moneyFormat = createMoneyFormat(this.decimals);
        buildScreen();
        updateEnteredTotal();
        setMinimumSize(new Dimension(850, 590));
        setSize(1000, 680);
        setLocationRelativeTo(owner);
    }

    private void buildScreen() {
        JPanel root = new JPanel(new BorderLayout(0, 12));
        root.setBackground(BG);
        root.setBorder(new EmptyBorder(16, 18, 16, 18));
        setContentPane(root);

        JPanel header = new JPanel(new BorderLayout(8, 4));
        header.setOpaque(false);
        JPanel titles = new JPanel();
        titles.setOpaque(false);
        titles.setLayout(new BoxLayout(titles, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("SETTLEMENT");
        title.setFont(new Font("SansSerif", Font.BOLD, 23));
        title.setForeground(DARK);
        JLabel subtitle = new JLabel("Invoice " + invoiceNo + "   |   " + customerName
            + (customerCode.length() > 0 ? " (" + customerCode + ")" : ""));
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(MUTED);
        titles.add(title);
        titles.add(Box.createVerticalStrut(3));
        titles.add(subtitle);
        header.add(titles, BorderLayout.WEST);
        header.add(buildTotalsHeader(), BorderLayout.CENTER);
        root.add(header, BorderLayout.NORTH);

        JPanel body = new JPanel(new BorderLayout(12, 0));
        body.setOpaque(false);
        // Keep the touch keypad on the left and the four-column payment table on the right.
        body.add(buildKeypadPanel(), BorderLayout.WEST);
        body.add(buildPaycodeTable(), BorderLayout.CENTER);
        root.add(body, BorderLayout.CENTER);
        root.add(buildActions(), BorderLayout.SOUTH);
    }

    private JPanel buildTotalsHeader() {
        JPanel card = new JPanel(new GridLayout(2, 3, 12, 8));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(224, 228, 232)),
            new EmptyBorder(10, 12, 10, 12)));
        card.add(totalCell("SUB TOTAL", subtotal, DARK));
        card.add(totalCell("DISCOUNT", discount, DANGER));
        card.add(totalCell("VAT", vat, BLUE));
        card.add(totalCell("TOTAL DUE", amountDue, PRIMARY));
        JPanel paid = totalCell("PAID AMOUNT", 0, GREEN);
        lblPaid = (JLabel) paid.getClientProperty("amountLabel");
        card.add(paid);
        JPanel balance = totalCell("BALANCE TO PAY", amountDue, DANGER);
        lblBalance = (JLabel) balance.getClientProperty("amountLabel");
        lblDue = (JLabel) totalCellAmountLabel(balance);
        card.add(balance);
        return card;
    }

    private JPanel totalCell(String caption, double value, Color color) {
        JPanel cell = new JPanel();
        cell.setOpaque(false);
        cell.setLayout(new BoxLayout(cell, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(caption);
        label.setFont(new Font("SansSerif", Font.BOLD, 10));
        label.setForeground(MUTED);
        JLabel amount = new JLabel(format(value));
        amount.setFont(new Font("SansSerif", Font.BOLD, 17));
        amount.setForeground(color);
        cell.add(label);
        cell.add(Box.createVerticalStrut(5));
        cell.add(amount);
        cell.putClientProperty("amountLabel", amount);
        return cell;
    }

    private JLabel totalCellAmountLabel(JPanel cell) {
        return (JLabel) cell.getClientProperty("amountLabel");
    }

    private JPanel buildPaycodeTable() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(224, 228, 232)),
            new EmptyBorder(12, 12, 12, 12)));
        JLabel heading = new JLabel("PAYMENT METHODS");
        heading.setFont(new Font("SansSerif", Font.BOLD, 14));
        heading.setForeground(DARK);
        panel.add(heading, BorderLayout.NORTH);

        JPanel table = new JPanel(new GridBagLayout());
        table.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 6, 5, 6);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridy = 0;
        addTableHeader(table, c, 0, "PAY CODE", 0.28);
        addTableHeader(table, c, 1, "AMOUNT", 0.24);
        addTableHeader(table, c, 2, "EX. RATE", 0.18);
        addTableHeader(table, c, 3, "AMOUNT × RATE", 0.30);
        int row = 1;
        List<String[]> codes = loadPaycodes();
        if (codes.isEmpty()) {
            JLabel noCodes = new JLabel(connection == null
                ? "No database connection was supplied. Use the constructor with Connection and schema."
                : "No payment codes found in mr_store_paycodes.");
            noCodes.setForeground(DANGER);
            noCodes.setFont(new Font("SansSerif", Font.BOLD, 13));
            c.gridy = row++;
            c.gridx = 0;
            c.gridwidth = 4;
            c.weightx = 1;
            table.add(noCodes, c);
        }
        for (String[] code : codes) {
            final JTextField amount = new JTextField();
            amount.setFont(new Font("SansSerif", Font.BOLD, 18));
            amount.setHorizontalAlignment(JTextField.RIGHT);
            amount.setPreferredSize(new Dimension(150, 42));
            amount.setFocusTraversalKeysEnabled(false);
            amount.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent e) { activeAmountField = amount; }
            });
            amount.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                public void insertUpdate(javax.swing.event.DocumentEvent e) { updateEnteredTotal(); }
                public void removeUpdate(javax.swing.event.DocumentEvent e) { updateEnteredTotal(); }
                public void changedUpdate(javax.swing.event.DocumentEvent e) { updateEnteredTotal(); }
            });
            JLabel paycode = new JLabel(code[0]);
            paycode.setFont(new Font("SansSerif", Font.BOLD, 14));
            final JLabel rate = new JLabel(format(parseAmount(code[1])), SwingConstants.RIGHT);
            rate.setFont(new Font("SansSerif", Font.PLAIN, 14));
            final JLabel converted = new JLabel(format(0), SwingConstants.RIGHT);
            converted.setFont(new Font("SansSerif", Font.BOLD, 14));
            amount.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                private void refreshConverted() {
                    converted.setText(format(parseAmount(amount.getText()) * parseAmount(rate.getText())));
                }
                public void insertUpdate(javax.swing.event.DocumentEvent e) { refreshConverted(); }
                public void removeUpdate(javax.swing.event.DocumentEvent e) { refreshConverted(); }
                public void changedUpdate(javax.swing.event.DocumentEvent e) { refreshConverted(); }
            });
            c.gridy = row++;
            c.gridx = 0; c.weightx = 0.28; c.gridwidth = 1;
            table.add(paycode, c);
            c.gridx = 1; c.weightx = 0.24;
            table.add(amount, c);
            c.gridx = 2; c.weightx = 0.18;
            table.add(rate, c);
            c.gridx = 3; c.weightx = 0.30;
            table.add(converted, c);
            paymentRows.add(new PaymentRow(code[0], parseAmount(code[1]), amount));
            if (activeAmountField == null) activeAmountField = amount;
        }
        c.gridy = row; c.gridx = 0; c.gridwidth = 4; c.weightx = 1;
        lblEnteredTotal = new JLabel("Entered total: " + format(0));
        lblEnteredTotal.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblEnteredTotal.setForeground(PRIMARY);
        lblEnteredTotal.setBorder(new EmptyBorder(12, 6, 4, 6));
        table.add(lblEnteredTotal, c);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel reference = new JPanel(new BorderLayout(8, 0));
        reference.setOpaque(false);
        JLabel refLabel = new JLabel("Reference");
        refLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        txtReference = new JTextField();
        txtReference.setFont(new Font("SansSerif", Font.PLAIN, 15));
        txtReference.setPreferredSize(new Dimension(180, 36));
        reference.add(refLabel, BorderLayout.WEST);
        reference.add(txtReference, BorderLayout.CENTER);
        panel.add(reference, BorderLayout.SOUTH);
        return panel;
    }

    private void addTableHeader(JPanel table, GridBagConstraints c, int col, String text, double weight) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 11));
        label.setForeground(MUTED);
        label.setBorder(new EmptyBorder(0, 0, 8, 0));
        c.gridx = col; c.weightx = weight;
        table.add(label, c);
    }

    private List<String[]> loadPaycodes() {
        List<String[]> result = new ArrayList<String[]>();
        if (connection == null) return result;
        String tableName;
        if (schema.matches("[A-Za-z0-9_$]+")) tableName = "`" + schema + "`.mr_store_paycodes";
        else tableName = "mr_store_paycodes";
        String sql = "SELECT paycode, ex_rate FROM " + tableName + " ORDER BY id";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String paycode = safe(rs.getString("paycode")).trim();
                if (paycode.length() == 0) continue;
                result.add(new String[] { paycode, safe(rs.getString("ex_rate")).trim() });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Could not load payment codes:\n" + ex.getMessage(),
                "Payment codes", JOptionPane.WARNING_MESSAGE);
        }
        return result;
    }

    private JPanel buildKeypadPanel() {
        JPanel outer = new JPanel(new BorderLayout(0, 8));
        outer.setBackground(BG);
        outer.setPreferredSize(new Dimension(245, 0));
        JLabel title = new JLabel("TOUCH KEYPAD");
        title.setFont(new Font("SansSerif", Font.BOLD, 13));
        title.setForeground(DARK);
        outer.add(title, BorderLayout.NORTH);
        JPanel keys = new JPanel(new GridLayout(4, 3, 6, 6));
        keys.setBackground(BG);
        String[] labels = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "CLR", "0", "."};
        for (final String key : labels) {
            JButton b = makeButton(key, Color.WHITE);
            b.setForeground(DARK);
            b.setBorder(BorderFactory.createLineBorder(new Color(210, 216, 220)));
            b.setPreferredSize(new Dimension(70, 62));
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { pressKey(key); }
            });
            keys.add(b);
        }
        JPanel lower = new JPanel(new GridLayout(1, 2, 6, 0));
        lower.setOpaque(false);
        JButton back = makeButton("⌫", new Color(96, 109, 116));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { pressKey("BACK"); }
        });
        JButton clear = makeButton("CLEAR", DANGER);
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { pressKey("CLR"); }
        });
        lower.add(back); lower.add(clear);
        JPanel center = new JPanel(new BorderLayout(0, 8));
        center.setOpaque(false);
        center.add(keys, BorderLayout.CENTER);
        center.add(lower, BorderLayout.SOUTH);
        outer.add(center, BorderLayout.CENTER);
        JLabel hint = new JLabel("Tap an amount field or type directly.");
        hint.setForeground(MUTED);
        hint.setFont(new Font("SansSerif", Font.PLAIN, 11));
        outer.add(hint, BorderLayout.SOUTH);
        return outer;
    }

    private void pressKey(String key) {
        if (activeAmountField == null) return;
        String text = activeAmountField.getText();
        if ("CLR".equals(key)) text = "";
        else if ("BACK".equals(key)) {
            if (text.length() > 0) text = text.substring(0, text.length() - 1);
        } else if (".".equals(key)) {
            if (text.indexOf('.') < 0) text = text.length() == 0 ? "0." : text + ".";
        } else {
            text += key;
        }
        activeAmountField.setText(text);
        activeAmountField.requestFocusInWindow();
        activeAmountField.setCaretPosition(activeAmountField.getText().length());
    }

    private JPanel buildActions() {
        JPanel actions = new JPanel(new BorderLayout(10, 0));
        actions.setOpaque(false);
        JButton cancel = makeButton("CANCEL", new Color(96, 109, 116));
        btnConfirm = makeButton("SETTLE", GREEN);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { dispose(); }
        });
        btnConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { confirmPayment(); }
        });
        JButton clear = makeButton("CLEAR", DANGER);
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { clearAmounts(); }
        });
        JButton backToPos = makeButton("BACK TO POS", new Color(69, 90, 100));
        backToPos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { dispose(); }
        });
        JPanel leftActions = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        leftActions.setOpaque(false);
        leftActions.add(clear);
        leftActions.add(backToPos);
        actions.add(leftActions, BorderLayout.WEST);
        actions.add(btnConfirm, BorderLayout.EAST);
        return actions;
    }

    private JButton makeButton(String text, Color color) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.BOLD, 14));
        b.setForeground(Color.WHITE);
        b.setBackground(color);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(12, 14, 12, 14));
        b.setOpaque(true);
        return b;
    }

    private void updateEnteredTotal() {
        if (lblEnteredTotal == null) return;
        double entered = 0;
        double convertedTotal = 0;
        for (PaymentRow row : paymentRows) {
            double amount = parseAmount(row.amountField.getText());
            entered += amount;
            convertedTotal += amount * row.exchangeRate;
        }
        double balance = amountDue - convertedTotal;
        lblEnteredTotal.setText("Entered: " + format(entered) + "   |   Converted paid: "
            + format(convertedTotal) + "   |   Due: " + format(amountDue));
        lblEnteredTotal.setForeground(convertedTotal + epsilon() >= amountDue ? GREEN : DANGER);
        if (lblPaid != null) {
            lblPaid.setText(format(convertedTotal));
            lblPaid.setForeground(GREEN);
        }
        if (lblBalance != null) {
            lblBalance.setText(format(balance));
            lblBalance.setForeground(balance <= epsilon() ? GREEN : DANGER);
        }
    }

    private void clearAmounts() {
        for (PaymentRow row : paymentRows) row.amountField.setText("");
        if (txtReference != null) txtReference.setText("");
        if (activeAmountField != null) activeAmountField.requestFocusInWindow();
        updateEnteredTotal();
    }

    private void confirmPayment() {
        if (handler == null) {
            JOptionPane.showMessageDialog(this,
                "Payment cannot be completed because no settlement handler was provided.",
                "Settlement unavailable", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<PaymentLine> lines = new ArrayList<PaymentLine>();
        double entered = 0;
        double convertedTotal = 0;
        String firstCode = "";
        for (PaymentRow row : paymentRows) {
            double amount = parseAmount(row.amountField.getText());
            if (amount < 0) {
                JOptionPane.showMessageDialog(this, "Enter valid non-negative payment amounts.",
                    "Invalid amount", JOptionPane.WARNING_MESSAGE);
                row.amountField.requestFocusInWindow();
                return;
            }
            if (amount > 0) {
                lines.add(new PaymentLine(row.paycode, amount, row.exchangeRate));
                entered += amount;
                convertedTotal += amount * row.exchangeRate;
                if (firstCode.length() == 0) firstCode = row.paycode;
            }
        }
        if (lines.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter an amount for at least one payment code.",
                "Payment amount required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (convertedTotal + epsilon() < amountDue) {
            JOptionPane.showMessageDialog(this,
                "Paid amount after exchange-rate conversion is less than the invoice total.",
                "Insufficient payment", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Payment payment = new Payment(invoiceNo, customerCode, customerName,
            lines.size() == 1 ? firstCode : "MULTIPLE", safe(txtReference.getText().trim()),
            amountDue, entered, Math.max(0, convertedTotal - amountDue), lines);
        btnConfirm.setEnabled(false);
        try {
            if (handler.confirmPayment(payment)) dispose();
            else JOptionPane.showMessageDialog(this,
                "The payment was not saved. The invoice remains open.",
                "Payment not completed", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Could not complete payment:\n" + ex.getMessage(),
                "Settlement error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (isDisplayable()) btnConfirm.setEnabled(true);
        }
    }

    private double parseAmount(String value) {
        try {
            if (value == null || value.trim().length() == 0) return 0;
            return Double.parseDouble(value.trim().replace(",", ""));
        } catch (NumberFormatException ex) { return 0; }
    }

    private double epsilon() { return 0.5 / Math.pow(10, decimals); }
    private String format(double value) { return moneyFormat.format(value); }

    private static DecimalFormat createMoneyFormat(int decimals) {
        StringBuilder pattern = new StringBuilder("#,##0");
        if (decimals > 0) {
            pattern.append('.');
            for (int i = 0; i < decimals; i++) pattern.append('0');
        }
        return new DecimalFormat(pattern.toString());
    }

    private static String safe(String value) { return value == null ? "" : value; }
}
