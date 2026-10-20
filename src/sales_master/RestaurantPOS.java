package sales_master;

import javax.swing.*;
import javax.swing.table.*;

import org.json.JSONException;

import CafePro.Mainpage;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Restaurant POS screen - Java 7 compatible (no lambdas, no streams).
 *
 * Layout:
 *   WEST   : 6 mode buttons (Dining, Home Delivery, Takeaway, Online, Orders, Miscellaneous)
 *   CENTER : Info bar (mode / invoice / table)  +  card area
 *              TABLES -> table selection (Dining only)
 *              MENU   -> departments + items
 *              ORDERS -> held invoices (tabbed by mode)
 *              MISC   -> Duplicate Invoices, Manager, Historical Bill ...
 */

public class RestaurantPOS extends JFrame {

    // ---------- colours ----------
    private static final Color BG        = new Color(245, 246, 250);
    private static final Color DARK      = new Color(38, 50, 56);
    private static final Color PRIMARY   = new Color(0, 121, 107);
    private static final Color ACCENT    = new Color(255, 143, 0);
    private static final Color DANGER    = new Color(198, 40, 40);
    private static final Color BLUE      = new Color(21, 101, 192);
    private static final Color GREEN     = new Color(46, 125, 50);
    private static final Color MODE_OFF  = new Color(69, 90, 100);

    private static final String[] MODES = {
        "Dinein", "Home Delivery", "Takeaway", "Online", "Orders", "Miscellaneous"
    };
    // Dining=green, Home Delivery=red, Takeaway=yellow, Online=blue, Orders=black, Misc=gray
    private static final Color[] MODE_COLORS = {
        new Color(46, 125, 50), new Color(198, 40, 40), new Color(253, 216, 53),
        new Color(21, 101, 192), new Color(33, 33, 33), new Color(117, 117, 117)
    };

    // item master "unit" values used to separate the selling prices
    //   Dining / Home Delivery / Takeaway -> UNIT_INDOOR price,  Online -> UNIT_ONLINE price
    private static final String UNIT_INDOOR = "indoor";
    private static final String UNIT_ONLINE = "online";

    // default customer shown on a new invoice
    private static final String DEF_CUST_CODE  = "1";
    private static final String DEF_CUST_NAME  = "WALKIN";
    private static final String DEF_CUST_PHONE = "";
    private static final String DEF_CUST_BAL   = "0.00";

    // ---------- authorization tables (CONFIRM these column names) ----------
    private static final String USER_COL      = "username";   // login-name column in mr_users and mr_user_poscontrol
    private static final String USER_TYPE_COL = "user_type";  // mr_users.user_type ('Administrator' = full power)

    private static final double EPS = 0.0005;

    // ---------- state ----------
    private String currentMode = "Dinein";
    private String selectedTable = null;
    private String selectedProvider = null;
    private String deliveryUser = null;
    static String Printer="";
    private String activeInvoiceNo = "";
    private String activeReg;                 // register of the active invoice (changes when a held invoice of another register is recalled)
    private boolean activeInvoiceNumberAdvanced = false; // true once this invoice advanced the register sequence
    private String activeDept = null;
    static double discount = 0; 
    static double discountperc = 0; // invoice discount amount (VAT inclusive)
    private double lastTax = 0;               // VAT of the current invoice (after discount)
    private int kotSeq = 0;                   // KOTs already printed for this invoice
    private final List<Object[]> pendingCancel = new ArrayList<Object[]>();  // deleted after KOT: {name, qty}
    private final Map<String, JButton> tableButtons = new LinkedHashMap<String, JButton>();
    private JLabel lblDisc;
    private boolean billPrinted = false;      // Check Bill pressed (= Chkbill_Sts 1)
    private boolean settlementOpening = false; // Prevent duplicate settlement dialogs.
    private String overrideUser = null;       // manager user for ONE action, then reverts
    private static RestaurantPOS current;     // so other forms can call applyManagerUser() / setCustomer() / paymentCompleted()
    private final Map<String, Color[]> deptColors = new HashMap<String, Color[]>(); // {background, foreground}
    private long lastInvoice = -1;
    private List<String> tableNums = new ArrayList<String>();   // table_num from mr_kot_tables
    private final List<HeldInvoice> heldInvoices = new ArrayList<HeldInvoice>();
    private boolean heldWarned = false;
    private final Map<String, String[][]> menu = new LinkedHashMap<String, String[][]>(); // sample fallback only
    private List<String[]> departments = new ArrayList<String[]>();                      // {dept_code, dept_name, color, fore}
    private final Map<String, String> deptCodes = new HashMap<String, String>();
    private final Map<String, List<ItemRec>> itemCache = new HashMap<String, List<ItemRec>>();  // key: dept|unit
    private boolean dbOk = false;

    // One cached snapshot per user. Every mr_user_poscontrol column is loaded
    // into parallel arrays (controlNames/controlValues), so controls can be reused.
    private final Map<String, UserAuthorization> authorizationCache =
        new HashMap<String, UserAuthorization>();

    private static class UserAuthorization {
        boolean administrator;
        String[] controlNames = new String[0];
        int[] controlValues = new int[0];

        boolean hasControl(String requestedName) {
            if (requestedName == null) return false;
            for (int i = 0; i < controlNames.length; i++) {
                if (controlNames[i].equalsIgnoreCase(requestedName.trim())) {
                    return controlValues[i] == 1;
                }
            }
            return false;
        }
    }

    
    // customer (code is a hidden value, the phone is what is displayed)
    private String custCode = DEF_CUST_CODE;
    private String custPhone = DEF_CUST_PHONE;

    // ---------- values passed from the calling module ----------
    private final String company, dbName, username, storeName, posReg, version, server, language;
    private final Connection con1;     // shared connection - never closed here
    private int dec = 2;               // decimal places

    // ---------- components ----------
    private final Map<String, JButton> modeButtons = new LinkedHashMap<String, JButton>();
    private JLabel lblMode, lblInvoice, lblTable;
    private JLabel lblTableCaption;
    private JPanel tablePanelInfo;
    private CardLayout cards;
    private JPanel cardPanel;
    private JPanel tablesGrid, deptPanel, itemsGrid;
    private JPanel deliveryPanel;
    private JTabbedPane deliveryTabs;
    private final Map<String, JButton> deptButtons = new LinkedHashMap<String, JButton>();
    private String activeMode = null;

    static JLabel lblCustPhone;
	static JLabel lblCustName;
	static JLabel lblCustBalance;
	static String lblCustCode=DEF_CUST_CODE;
    private DefaultTableModel orderModel;
    private JTable orderTable;
    static JLabel lblSub, lblTax, lblSubtotalInc;
	static JLabel lblTotal;
	
	public static JButton settle;

    private JTabbedPane ordersTabs;
    private final Map<String, DefaultTableModel> ordersModels = new LinkedHashMap<String, DefaultTableModel>();
    private final Map<String, List<HeldInvoice>> ordersLists = new LinkedHashMap<String, List<HeldInvoice>>();
    private final Map<String, JTable> ordersTables = new LinkedHashMap<String, JTable>();
    private final Map<String, DefaultTableModel> deliveryModels = new LinkedHashMap<String, DefaultTableModel>();
    private final Map<String, JTable> deliveryTables = new LinkedHashMap<String, JTable>();

    // Order-table model columns (0-3 visible, the rest hidden):
    //   0 Item | 1 Qty | 2 Price | 3 Total | 4 Code | 5 VAT% | 6 Qty already sent to kitchen | 7 ItemRec

    // ---------- item record (from mr_tmp_item_master) ----------
    private static class ItemRec {
        String code, name, barcode = "", unit = "", dept = "", cost = "0";
        double price;   // sp_include_tax  (VAT inclusive selling price)
        double sp;      // sp (selling price without VAT)
        double cf;
        int taxRate;    // output_tax_rate (e.g. 5)
        Color bg, fg;   // color_code / fore_color_code (may be null)
    }

    // ---------- held invoice (mr_pos_hold_invoice + mr_pos_hold_invoice_items) ----------
    private static class HeldInvoice {
        String no, reg, mode, table, deliveryUser, custCode, custName, custPh, custBal = "";
        boolean billPrinted;
        double discount, total, vat;
        int kotSeq;
        List<Object[]> cancels = new ArrayList<Object[]>();   // items removed after KOT: {name, qty}
        List<Object[]> rows = new ArrayList<Object[]>();      // order-table model rows (8 columns)
    }

    /**
     * Example: new sales_master.RestaurantPOS("", "mr_pro", "ADMIN", "S01", "S0101", "",
     *                                         "127.0.0.1", "", Dbsetup.server.getConnection(), "");
     * @throws SQLException 
     */
    public RestaurantPOS(String company, String dbName, String username, String storeName,
                         String posReg, String version, String server, String decimal,
                         Connection con1, String language) throws SQLException {
        super("Restaurant POS");
        this.company = company;
        this.dbName = dbName;
        this.username = username;
        this.storeName = storeName;
        this.posReg = posReg;
        this.version = version;
        this.server = server;
        this.language = language;
        this.con1 = con1;
        this.activeReg = posReg;
        try {
            dec = Math.max(0, Math.min(4, Integer.parseInt(decimal.trim())));
        } catch (Exception ex) {
            dec = 2;
        }
        updateTitle();
        current = this;
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) { if (current == RestaurantPOS.this) current = null; }
        });
        loadData();
        buildUI();
        loadHeldFromDb();
        setMode("Dinein");
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE); // do not close the whole application
     // Prevent closing POS when the order contains items
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (orderModel != null && orderModel.getRowCount() > 0) {
                    JOptionPane.showMessageDialog(
                        RestaurantPOS.this,
                        "Cannot close POS while items are in the order.\n"
                        + "Please Hold or Settle the order first.",
                        "POS Close Restricted",
                        JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    dispose();
                    Mainpage.btnSales.setEnabled(true);
                }
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(1280, 760);
        setMinimumSize(new Dimension(1100, 650));
        setLocationRelativeTo(null);
        Printer=GetPrinterNames();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
    }

    // =====================================================================
    //  Database access via con1
    // =====================================================================
    /** Table name prefixed with the schema (db_name), e.g. `mr_pro`.mr_department */
    private String tbl(String table) {
        if (dbName != null && dbName.matches("[A-Za-z0-9_$]+")) {
            return "`" + dbName + "`." + table;
        }
        return table;
    }

    private static String nz(String s) {
        return s == null ? "" : s;
    }

    private List<String[]> loadDepartmentsFromDb() throws SQLException {
        if (con1 == null) throw new SQLException("No database connection supplied.");
        List<String[]> list = new ArrayList<String[]>();
        String sql = "SELECT dept_code, dept_name, color_code, fore_color_code FROM " + tbl("mr_department")
                   + " ORDER BY dept_name, CAST(seq_num AS UNSIGNED)";
        PreparedStatement ps = con1.prepareStatement(sql);   // con1 is shared: do not close it
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new String[]{rs.getString("dept_code"), rs.getString("dept_name"),
                    rs.getString("color_code"), rs.getString("fore_color_code")});
            }
            rs.close();
        } finally {
            ps.close();
        }
        return list;
    }

    /**
     * Items of one department for one price unit (indoor / online).
     * item_master.department may hold the department name or its code, so both are matched.
     */
    private List<ItemRec> loadItemsFromDb(String deptCode, String deptName, String unit) throws SQLException {
        if (con1 == null) throw new SQLException("No database connection supplied.");
        List<ItemRec> list = new ArrayList<ItemRec>();
        String sql = "SELECT item_code, item_name, barcode, department, unit, sp, sp_include_tax, cost, cf, "
                   + "output_tax_rate, color_code, fore_color_code "
                   + "FROM " + tbl("mr_tmp_item_master") + " "
                   + "WHERE activeForSale = 1 AND unit = ? AND (department = ? OR department = ?) "
                   + "ORDER BY item_name";
        PreparedStatement ps = con1.prepareStatement(sql);
        try {
            ps.setString(1, unit);
            ps.setString(2, deptName);
            ps.setString(3, deptCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemRec it = new ItemRec();
                it.code = rs.getString("item_code");
                it.name = rs.getString("item_name");
                it.barcode = nz(rs.getString("barcode"));
                it.dept = nz(rs.getString("department"));
                it.unit = nz(rs.getString("unit"));
                it.cost = nz(rs.getString("cost"));
                it.cf = rs.getDouble("cf");
                it.taxRate = rs.getInt("output_tax_rate");
                it.price = Math.round(rs.getDouble("sp") * 10000.0) / 10000.0; // VAT inclusive, float noise removed
                try {
                    it.sp = Double.parseDouble(rs.getString("sp").trim());
                } catch (Exception ex) {
                    it.sp = it.price * 100.0 / (100.0 + it.taxRate);
                }
                it.bg = parseColor(rs.getString("color_code"));
                it.fg = parseColor(rs.getString("fore_color_code"));
                list.add(it);
            }
            rs.close();
        } finally {
            ps.close();
        }
        return list;
    }

    private void loadData() {
        try {
            departments = loadDepartmentsFromDb();
            dbOk = true;
        } catch (Exception ex) {
            dbOk = false;
            JOptionPane.showMessageDialog(null,
                "Cannot read departments from the database.\n" + ex.getMessage()
                + "\n\nSample data will be used instead.",
                "Database", JOptionPane.WARNING_MESSAGE);
            loadMenu();
            departments.clear();
            for (String d : menu.keySet()) {
                departments.add(new String[]{d, d});
                List<ItemRec> l = new ArrayList<ItemRec>();
                String[][] arr = menu.get(d);
                for (int i = 0; i < arr.length; i++) {
                    ItemRec it = new ItemRec();
                    it.code = arr[i][0];
                    it.name = arr[i][0];
                    it.price = Double.parseDouble(arr[i][1]);
                    it.sp = it.price * 100.0 / 105.0;
                    it.taxRate = 5;
                    l.add(it);
                }
                itemCache.put(d + "|" + UNIT_INDOOR, l);
                itemCache.put(d + "|" + UNIT_ONLINE, l);
            }
        }
        for (String[] d : departments) {
            deptCodes.put(d[1], d[0]);
            Color bg = d.length > 3 ? parseColor(d[2]) : null;
            Color fg = d.length > 3 ? parseColor(d[3]) : null;
            if (bg == null) {                      // no colour in DB -> black / white
                bg = Color.BLACK;
                if (fg == null) fg = Color.WHITE;
            } else if (fg == null) {
                fg = contrast(bg);
            }
            deptColors.put(d[1], new Color[]{bg, fg});
        }

        // tables of this store
        try {
            tableNums = loadTablesFromDb();
        } catch (Exception ex) {
            if (dbOk) {
                JOptionPane.showMessageDialog(null, "Cannot read tables from mr_kot_tables.\n"
                    + ex.getMessage() + "\n\nSample tables will be used.", "Database",
                    JOptionPane.WARNING_MESSAGE);
            }
            tableNums = new ArrayList<String>();
            for (int i = 1; i <= 12; i++) tableNums.add(String.valueOf(i));
        }

        // first invoice number
        refreshInvoiceNo(dbOk);
    }

    /** Online mode has its own selling price (unit = online); every other mode uses the indoor price. */
    private String priceUnit() {
        return "Online".equals(currentMode) ? UNIT_ONLINE : UNIT_INDOOR;
    }

    private List<ItemRec> getItems(String deptName) {
        String unit = priceUnit();
        String key = deptName + "|" + unit;
        List<ItemRec> l = itemCache.get(key);
        if (l != null) return l;
        try {
            l = loadItemsFromDb(deptCodes.get(deptName), deptName, unit);
            itemCache.put(key, l);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Cannot load items for " + deptName + "\n" + ex.getMessage());
            l = new ArrayList<ItemRec>();
        }
        return l;
    }

    // ---------------- hold invoice tables ----------------

    private void deleteHoldFromDb(String receipt, String reg) throws SQLException {
        String[] sqls = {
            "DELETE FROM " + tbl("mr_pos_hold_invoice_items") + " WHERE ref_receipt_num = ? AND ref_reg_no = ?",
            "DELETE FROM " + tbl("mr_pos_hold_invoice") + " WHERE receipt_no = ? AND reg_num = ?"
        };
        for (int i = 0; i < sqls.length; i++) {
            PreparedStatement ps = con1.prepareStatement(sqls[i]);
            try {
                ps.setString(1, receipt);
                ps.setString(2, reg);
                ps.executeUpdate();
            } finally {
                ps.close();
            }
        }
    }

    /**
     * Saves (or re-saves) a held invoice: header in mr_pos_hold_invoice, items in mr_pos_hold_invoice_items.
     * Items already sent to the kitchen are stored as a separate line with print_sts = 1 and the KOT number,
     * the not-yet-sent part with print_sts = 0, so the sent quantity survives hold / recall.
     */
    private void saveHoldToDb(HeldInvoice h, int newKotNum, boolean advanceInvoiceNumber) throws SQLException {
        boolean auto = con1.getAutoCommit();
        con1.setAutoCommit(false);
        try {
            deleteHoldFromDb(h.no, h.reg);                 // re-hold replaces the old copy

            double totalQty = 0;
            for (Object[] r : h.rows) totalQty += (Double) r[1];

            PreparedStatement ps = con1.prepareStatement("INSERT INTO " + tbl("mr_pos_hold_invoice")
                + " (reg_num, receipt_no, `user`, `time`, total, cust_name, vat, disc, total_qty, store_id,"
                + " created_date, cust_code, `mode`, table_num, delivery_user, cust_ph, Chkbill_Sts)"
                + " VALUES (?,?,?,CURTIME(),?,?,?,?,?,?,CURDATE(),?,?,?,?,?,?)");
            try {
                ps.setString(1, h.reg);
                ps.setString(2, h.no);
                ps.setString(3, username);
                ps.setDouble(4, h.total);
                ps.setString(5, nz(h.custName));
                ps.setDouble(6, roundToDec(h.vat));
                ps.setDouble(7, h.discount);
                ps.setDouble(8, totalQty);
                ps.setString(9, storeName);
                ps.setString(10, nz(h.custCode));
                ps.setString(11, h.mode);
                ps.setString(12, h.table == null || h.table.length() == 0 ? h.mode : h.table);
                ps.setString(13, nz(h.deliveryUser));
                ps.setString(14, nz(h.custPh));
                ps.setInt(15, h.billPrinted ? 1 : 0);
                ps.executeUpdate();
            } finally {
                ps.close();
            }

            PreparedStatement pi = con1.prepareStatement("INSERT INTO " + tbl("mr_pos_hold_invoice_items")
                + " (ref_receipt_num, ref_reg_no, barcode, item_desc, item_code, unit, unit_price, qty, total,"
                + " vat, discount, vat_type, created_date, cost, sp, vat_perc, department, kot_number,"
                + " print_sts, print_cmd, cf)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,0,'inclusive',CURDATE(),?,?,?,?,?,?,?,?)");
            try {
                for (Object[] r : h.rows) {
                    ItemRec it = (ItemRec) r[7];
                    double qty = (Double) r[1];
                    double sent = Math.min((Double) r[6], qty);
                    double unsent = qty - sent;

                    // Previously sent quantity must never be selected for the current KOT.
                    if (sent > EPS) {
                        int previousKotNum = newKotNum > 0 ? newKotNum - 1 : h.kotSeq;
                        addHoldItemRow(pi, h, it, sent, previousKotNum, 1, 1);
                    }

                    // Only the unsent quantity belongs to this KOT. print_cmd=0 means pending.
                    if (unsent > EPS) {
                        int itemKotNum = newKotNum > 0 ? newKotNum : 0;
                        addHoldItemRow(pi, h, it, unsent, itemKotNum, 0, 0);
                    }

                }
            } finally {
                pi.close();
            }
            if (advanceInvoiceNumber) incrementInvoiceNumber();
            con1.commit();
        } catch (SQLException ex) {
            con1.rollback();
            throw ex;
        } finally {
            con1.setAutoCommit(auto);
        }
    }

    private void addHoldItemRow(PreparedStatement pi, HeldInvoice h, ItemRec it, double qty,
                                int kotNo, int printSts, int printCmd) throws SQLException {
        double totalIncludingVat = qty * it.price;
        double vat = roundToDec(totalIncludingVat * it.taxRate / (100.0 + it.taxRate));
        double totalBeforeVat = roundToDec(totalIncludingVat - vat);
        pi.setString(1, h.no);
        pi.setString(2, h.reg);
        pi.setString(3, nz(it.barcode));
        pi.setString(4, it.name);
        pi.setString(5, it.code);
        pi.setString(6, nz(it.unit));
        pi.setDouble(7, it.price);
        pi.setDouble(8, qty);
        pi.setDouble(9, totalBeforeVat);
        pi.setDouble(10, vat);
        pi.setString(11, nz(it.cost));
        pi.setDouble(12, it.sp);
        pi.setDouble(13, it.taxRate);
        pi.setString(14, nz(it.dept));
        pi.setInt(15, kotNo);
        pi.setInt(16, printSts);
        pi.setInt(17, printCmd);
        pi.setDouble(18, it.cf);
        pi.executeUpdate();
    }

    /** Reloads every held invoice of this store from the database (the invoice open on screen is skipped). */
    private void loadHeldFromDb() {
        if (con1 == null) return;
        List<HeldInvoice> list = new ArrayList<HeldInvoice>();
        try {
            PreparedStatement ps = con1.prepareStatement("SELECT reg_num, receipt_no, total, cust_name, vat, disc,"
                + " cust_code, `mode`, table_num, delivery_user, cust_ph, Chkbill_Sts FROM " + tbl("mr_pos_hold_invoice")
                + " WHERE store_id = ? ORDER BY id");
            try {
                ps.setString(1, storeName);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    HeldInvoice h = new HeldInvoice();
                    h.reg = rs.getString("reg_num");
                    h.no = rs.getString("receipt_no");
                    h.total = rs.getDouble("total");
                    h.custName = rs.getString("cust_name");
                    h.vat = rs.getDouble("vat");
                    h.discount = rs.getDouble("disc");
                    h.custCode = rs.getString("cust_code");
                    h.mode = rs.getString("mode");
                    h.table = rs.getString("table_num");
                    h.deliveryUser = rs.getString("delivery_user");
                    h.custPh = rs.getString("cust_ph");
                    h.billPrinted = rs.getInt("Chkbill_Sts") == 1;
                    list.add(h);
                }
                rs.close();
            } finally {
                ps.close();
            }
            for (HeldInvoice h : list) loadHeldItems(h);
        } catch (SQLException ex) {
            if (!heldWarned) {
                heldWarned = true;
                JOptionPane.showMessageDialog(this, "Cannot read held invoices.\n" + ex.getMessage(),
                    "Hold", JOptionPane.WARNING_MESSAGE);
            }
            return;
        }
        heldInvoices.clear();
        for (HeldInvoice h : list) {
            boolean open = orderModel != null && orderModel.getRowCount() > 0
                           && h.no.equals(activeInvoiceNo) && h.reg.equals(activeReg);
            if (!open) heldInvoices.add(h);
        }
    }

    private void loadHeldItems(HeldInvoice h) throws SQLException {
        // print_cmd is the durable "already printed" flag. Some printing modules
        // may change print_sts, so do not rely on print_sts alone when recalling.
        PreparedStatement ps = con1.prepareStatement("SELECT item_code, item_desc, barcode, unit, unit_price, qty,"
            + " vat_perc, sp, cost, department, kot_number, print_sts, print_cmd, cf FROM "
            + tbl("mr_pos_hold_invoice_items")
            + " WHERE ref_receipt_num = ? AND ref_reg_no = ? ORDER BY id");
        try {
            ps.setString(1, h.no);
            ps.setString(2, h.reg);
            ResultSet rs = ps.executeQuery();
            Map<String, Object[]> merged = new LinkedHashMap<String, Object[]>();
            while (rs.next()) {
                String code = rs.getString("item_code");
                double qty = rs.getDouble("qty");
                Object[] row = merged.get(code);
                if (row == null) {
                    ItemRec it = new ItemRec();
                    it.code = code;
                    it.name = rs.getString("item_desc");
                    it.barcode = nz(rs.getString("barcode"));
                    it.unit = nz(rs.getString("unit"));
                    it.price = rs.getDouble("unit_price");
                    it.sp = rs.getDouble("sp");
                    it.cost = nz(rs.getString("cost"));
                    it.dept = nz(rs.getString("department"));
                    it.cf = rs.getDouble("cf");
                    it.taxRate = (int) Math.round(rs.getDouble("vat_perc"));
                    row = new Object[]{it.name, 0.0, it.price, 0.0, code, it.taxRate, 0.0, it};
                    merged.put(code, row);
                }
                row[1] = (Double) row[1] + qty;
                // A quantity is already sent if either flag says it was printed.
                // print_cmd=1 prevents the same KOT being re-sent after recall.
                if (rs.getInt("print_sts") == 1 || rs.getInt("print_cmd") == 1) {
                    row[6] = (Double) row[6] + qty;
                }
                int kn = rs.getInt("kot_number");
                if (kn > h.kotSeq) h.kotSeq = kn;
            }
            rs.close();
            for (Object[] row : merged.values()) {
                row[3] = (Double) row[1] * (Double) row[2];
                h.rows.add(row);
            }
        } finally {
            ps.close();
        }
    }

    /** Check Bill pressed on an invoice that is already held -> Chkbill_Sts = 1. */
    private void markBilledInDb(String receipt, String register) {
        if (con1 == null) return;
        try {
            PreparedStatement ps = con1.prepareStatement("UPDATE " + tbl("mr_pos_hold_invoice")
                + " SET Chkbill_Sts = 1 WHERE receipt_no = ? AND reg_num = ?");
            try {
                ps.setString(1, receipt);
                ps.setString(2, register);
                ps.executeUpdate();
            } finally {
                ps.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Cannot update bill status.\n" + ex.getMessage(),
                "Hold", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ---------------- helpers: colours ----------------

    /** Accepts #RRGGBB, 0xRRGGBB, R,G,B, a decimal RGB integer, or a java colour name. Returns null if empty/invalid. */
    private static Color parseColor(String t) {
        if (t == null) return null;
        t = t.trim();
        if (t.length() == 0) return null;
        try {
            if (t.startsWith("#") || t.toLowerCase().startsWith("0x")) return Color.decode(t);
            if (t.indexOf(',') > 0) {
                String[] p = t.split(",");
                return new Color(Integer.parseInt(p[0].trim()), Integer.parseInt(p[1].trim()),
                                 Integer.parseInt(p[2].trim()));
            }
            if (t.matches("-?\\d+")) return new Color(Integer.parseInt(t) & 0xFFFFFF);
            if (t.matches("[0-9a-fA-F]{6}")) return Color.decode("#" + t);
            return (Color) Color.class.getField(t.toLowerCase()).get(null);
        } catch (Exception ex) {
            return null;
        }
    }

    /** Black or white, whichever is readable on the given background. */
    private static Color contrast(Color bg) {
        double lum = 0.299 * bg.getRed() + 0.587 * bg.getGreen() + 0.114 * bg.getBlue();
        return lum > 150 ? Color.BLACK : Color.WHITE;
    }

    private static String esc(String t) {
        return t == null ? "" : t.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    /** 2 -> "2", 1.5 -> "1.5", 0.125 -> "0.125" */
    private static String qtyFmt(double v) {
        if (Math.abs(v - Math.rint(v)) < EPS) return String.valueOf((long) Math.rint(v));
        String s = String.format("%.3f", v);
        while (s.endsWith("0")) s = s.substring(0, s.length() - 1);
        return s;
    }

    // =====================================================================
    //  Sample data (fallback only when the database is not reachable)
    // =====================================================================
    private void loadMenu() {
        menu.put("Starters", new String[][]{
            {"Veg Soup", "12"}, {"Chicken Soup", "15"}, {"Spring Roll", "18"},
            {"Chicken 65", "25"}, {"Paneer Tikka", "28"}, {"French Fries", "14"}});
        menu.put("Main Course", new String[][]{
            {"Butter Chicken", "38"}, {"Dal Makhani", "28"}, {"Paneer Masala", "34"},
            {"Mutton Curry", "48"}, {"Fish Curry", "42"}, {"Veg Kurma", "26"}});
        menu.put("Biryani", new String[][]{
            {"Chicken Biryani", "35"}, {"Mutton Biryani", "45"}, {"Veg Biryani", "28"},
            {"Egg Biryani", "30"}});
        menu.put("Breads", new String[][]{
            {"Plain Naan", "6"}, {"Butter Naan", "8"}, {"Garlic Naan", "10"},
            {"Tandoori Roti", "5"}, {"Parotta", "6"}});
        menu.put("Beverages", new String[][]{
            {"Fresh Lime", "10"}, {"Mango Juice", "14"}, {"Cold Coffee", "16"},
            {"Soft Drink", "7"}, {"Water Bottle", "3"}, {"Masala Tea", "6"}});
        menu.put("Desserts", new String[][]{
            {"Gulab Jamun", "12"}, {"Ice Cream", "10"}, {"Brownie", "16"},
            {"Rasmalai", "15"}});
    }

    // =====================================================================
    //  UI construction
    // =====================================================================
    private void buildUI() {
        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBackground(BG);
        root.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        setContentPane(root);

        root.add(buildModePanel(), BorderLayout.WEST);
        root.add(buildCenterPanel(), BorderLayout.CENTER);
        root.add(buildOrderPanel(), BorderLayout.EAST);
    }

    // ---------------- LEFT : mode buttons ----------------
    private JPanel buildModePanel() {
        JPanel p = new JPanel(new GridLayout(MODES.length + 1, 1, 0, 8));
        p.setPreferredSize(new Dimension(170, 0));
        p.setBackground(BG);
        for (int i = 0; i < MODES.length; i++) {
            final String m = MODES[i];
            JButton b = makeButton(m, MODE_COLORS[i], 15);
            if (m.equals("Takeaway")) b.setForeground(Color.BLACK); // readable on yellow
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    onModeClicked(m);
                }
            });
            modeButtons.put(m, b);
            p.add(b);
        }
        JButton delivery = makeButton("ASSIGN DELIVERY", new Color(0, 96, 100), 13);
        delivery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshDeliveryLists();
                highlight("ASSIGN DELIVERY");
                cards.show(cardPanel, "DELIVERY");
            }
        });
        p.add(delivery);
        return p;
    }

    // ---------------- CENTER : info + cards ----------------
    private JPanel buildCenterPanel() {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setBackground(BG);

        // ---- 1) information section ----
        JPanel info = new JPanel(new GridLayout(1, 3, 10, 0));
        info.setBackground(Color.WHITE);
        info.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(207, 216, 220)),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)));

        lblMode = infoLabel();
        lblInvoice = infoLabel();
        lblTable = infoLabel();

        info.add(wrapInfo("MODE", lblMode));
        info.add(wrapInfo("INVOICE NO", lblInvoice));
        tablePanelInfo = wrapInfo("SELECT TABLE", lblTable);
        lblTableCaption = (JLabel) tablePanelInfo.getComponent(0);
        lblTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { if ("Online".equalsIgnoreCase(currentMode)) chooseOnlineProvider(); }
        });
        lblTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        info.add(tablePanelInfo);
        p.add(info, BorderLayout.NORTH);

        // ---- cards ----
        cards = new CardLayout();
        cardPanel = new JPanel(cards);
        cardPanel.setBackground(BG);
        cardPanel.add(buildTablesCard(), "TABLES");
        cardPanel.add(buildMenuCard(), "MENU");
        cardPanel.add(buildOrdersCard(), "ORDERS");
        cardPanel.add(buildDeliveryCard(), "DELIVERY");
        cardPanel.add(buildMiscCard(), "MISC");
        p.add(cardPanel, BorderLayout.CENTER);
        return p;
    }

    private JLabel infoLabel() {
        JLabel l = new JLabel("-");
        l.setFont(new Font("SansSerif", Font.BOLD, 20));
        l.setForeground(PRIMARY);
        return l;
    }

    private JPanel wrapInfo(String caption, JLabel value) {
        JPanel w = new JPanel(new BorderLayout());
        w.setOpaque(false);
        JLabel c = new JLabel(caption);
        c.setFont(new Font("SansSerif", Font.PLAIN, 11));
        c.setForeground(Color.GRAY);
        w.add(c, BorderLayout.NORTH);
        w.add(value, BorderLayout.CENTER);
        return w;
    }

    // ---- Tables card (Dining) ----
    private JPanel buildTablesCard() {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setBackground(Color.WHITE);
        p.setBorder(titled("Select Table"));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setOpaque(false);
        top.add(new JLabel("Total Tables: " + tableNums.size()));
        p.add(top, BorderLayout.NORTH);

        tablesGrid = new JPanel(new GridLayout(0, 4, 10, 10));
        tablesGrid.setOpaque(false);
        tablesGrid.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        for (int i = 0; i < tableNums.size(); i++) {
            final String num = tableNums.get(i);
            JButton b = makeButton(num, Color.WHITE, 24);   // table_num only
            b.setForeground(Color.BLACK);
            b.setBorder(BorderFactory.createLineBorder(GREEN, 4));   // free table = green border
            b.setBorderPainted(true);
            b.setPreferredSize(new Dimension(100, 90));
            tableButtons.put(num, b);
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    HeldInvoice held = heldForTable(num);
                    if (held != null) {          // occupied -> open the held invoice
                        recallInvoice(held);
                        return;
                    }
                    selectedTable = num;
                    updateInfo();
                    cards.show(cardPanel, "MENU");
                }
            });
            tablesGrid.add(b);
        }
        p.add(new JScrollPane(tablesGrid), BorderLayout.CENTER);
        return p;
    }

    // ---- Menu card (department + items) ----
    private JPanel buildMenuCard() {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setBackground(Color.WHITE);
        p.setBorder(titled("Department / Items"));

        deptPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 8, 6));
        deptPanel.setOpaque(false);
        deptPanel.addComponentListener(new ComponentAdapter() {
            private int lastH = -1;
            public void componentResized(ComponentEvent e) {
                int h = deptPanel.getPreferredSize().height;
                if (h != lastH) { lastH = h; deptPanel.revalidate(); }
            }
        });
        String firstDept = null;
        for (final String[] dep : departments) {
            final String d = dep[1];
            if (firstDept == null) firstDept = d;
            Color[] dc = deptColors.get(d);
            JButton b = makeButton(d, dc[0], 14);         // colours from mr_department
            b.setForeground(dc[1]);
            b.setBorderPainted(true);
            b.setBorder(deptBorder(false, dc[1]));        // size follows the text
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showItems(d);
                }
            });
            deptButtons.put(d, b);
            deptPanel.add(b);
        }
        p.add(deptPanel, BorderLayout.NORTH);

        itemsGrid = new JPanel(new GridLayout(0, 4, 10, 10));
        itemsGrid.setOpaque(false);
        itemsGrid.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JPanel holder = new JPanel(new BorderLayout());
        holder.setOpaque(false);
        holder.add(itemsGrid, BorderLayout.NORTH);
        p.add(new JScrollPane(holder), BorderLayout.CENTER);

        if (firstDept != null) showItems(firstDept);
        return p;
    }

    private javax.swing.border.Border deptBorder(boolean active, Color bg) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 4, 0, active ? ACCENT : bg),
            BorderFactory.createEmptyBorder(10, 18, 6, 18));
    }

    /** Shows the items of a department; the price list (indoor / online) follows the selected mode. */
    private void showItems(String dept) {
        activeDept = dept;
        for (String k : deptButtons.keySet()) {
            deptButtons.get(k).setBorder(deptBorder(k.equals(dept), deptColors.get(k)[0]));
        }
        itemsGrid.removeAll();
        List<ItemRec> items = getItems(dept);
        for (int i = 0; i < items.size(); i++) {
            final ItemRec it = items.get(i);
            
            Color ibg = it.bg != null ? it.bg : ACCENT;
            Color ifg = it.fg != null ? it.fg : contrast(ibg);
            
            
            //Color ibg = it.bg != null ? it.bg : ACCENT;
            JButton b = makeButton("<html><center>" + esc(it.name) + "<br><small style='font-size:14pt'>"
                + fmt(it.price) + "</small></center></html>", ibg, 14);
            
            b.setForeground(ifg);
            b.setPreferredSize(new Dimension(130, 80));

            // Explicit visible border
            b.setBorder(BorderFactory.createLineBorder(ifg, 2));
            b.setBorderPainted(true);
            b.setOpaque(true);
            b.setContentAreaFilled(true);
            
            //b.setForeground(it.fg != null ? it.fg : contrast(ibg));
            //b.setPreferredSize(new Dimension(130, 80));
            //b.setBorder(deptBorder(false, it.fg != null ? it.fg : contrast(ibg))); 
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addItem(it);
                }
            });
            itemsGrid.add(b);
        }
        itemsGrid.revalidate();
        itemsGrid.repaint();
    }

    // ---- Orders card (held invoices, tabbed by mode) ----
    private JPanel buildOrdersCard() {
        JPanel p = new JPanel(new BorderLayout(0, 10));
        p.setBackground(new Color(248, 250, 252));
        p.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));

        JPanel heading = new JPanel(new BorderLayout(0, 3));
        heading.setOpaque(false);
        JLabel title = new JLabel("Held Orders");
        title.setFont(new Font("SansSerif", Font.BOLD, 19));
        title.setForeground(DARK);
        JLabel hint = new JLabel("Double-click an invoice row to recall it.");
        hint.setFont(new Font("SansSerif", Font.PLAIN, 12));
        hint.setForeground(new Color(96, 110, 120));
        heading.add(title, BorderLayout.NORTH);
        heading.add(hint, BorderLayout.SOUTH);
        p.add(heading, BorderLayout.NORTH);

        ordersTabs = new JTabbedPane();
        ordersTabs.setFont(new Font("SansSerif", Font.BOLD, 13));
        ordersTabs.setBackground(Color.WHITE);
        String[] modes = {"Dinein", "Home Delivery", "Takeaway", "Online"};
        for (int i = 0; i < modes.length; i++) {
            final String mode = modes[i];
            DefaultTableModel m = new DefaultTableModel(
                new Object[]{"Invoice", "Table", "Customer", "Items", "Total"}, 0) {
                public boolean isCellEditable(int r, int c) { return false; }
            };
            final JTable t = new JTable(m);
            styleOrdersTable(t);
            ordersModels.put(modes[i], m);
            ordersTables.put(modes[i], t);
            ordersLists.put(modes[i], new ArrayList<HeldInvoice>());
            JScrollPane scroll = new JScrollPane(t);
            scroll.setBorder(BorderFactory.createEmptyBorder());
            scroll.getViewport().setBackground(Color.WHITE);
            ordersTabs.addTab(modes[i], scroll);
            t.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                        int row = t.rowAtPoint(e.getPoint());
                        if (row >= 0) {
                            t.setRowSelectionInterval(row, row);
                            recallOrderAt(mode, row);
                        }
                    }
                }
            });
        }
        p.add(ordersTabs, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setOpaque(false);

        JButton recall = makeButton("Recall Selected", PRIMARY, 14);
        recall.setPreferredSize(new Dimension(170, 42));
        recall.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 105, 92), 1),
            BorderFactory.createEmptyBorder(5, 12, 5, 12)));
        recall.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { recallSelected(); }
        });

        JButton reprintHeldKot = makeButton("Reprint KOT", BLUE, 14);
        reprintHeldKot.setPreferredSize(new Dimension(150, 42));
        reprintHeldKot.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(13, 71, 161), 1),
            BorderFactory.createEmptyBorder(5, 12, 5, 12)));
        reprintHeldKot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { reprintSelectedHeldKot(); }
        });

        bottom.add(reprintHeldKot);
        bottom.add(recall);
        p.add(bottom, BorderLayout.SOUTH);
        return p;
    }

    private JPanel buildDeliveryCard() {
        deliveryPanel = new JPanel(new BorderLayout(0, 8));
        deliveryPanel.setBackground(new Color(248, 250, 252));
        deliveryPanel.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));
        JLabel title = new JLabel("Delivery Management");
        title.setFont(new Font("SansSerif", Font.BOLD, 19));
        deliveryPanel.add(title, BorderLayout.NORTH);
        deliveryTabs = new JTabbedPane();
        addDeliveryTab("ASSIGN DELIVERY", "Delivery users not assigned", "assign");
        addDeliveryTab("ASSIGNED DELIVERY", "Invoices assigned to delivery users", "assigned");
        addDeliveryTab("DELIVERED INVOICES", "Invoices paid after delivery", "delivered");
        deliveryPanel.add(deliveryTabs, BorderLayout.CENTER);
        return deliveryPanel;
    }

    private void addDeliveryTab(String title, String hint, String key) {
        JPanel page = new JPanel(new BorderLayout(0, 8));
        page.setBackground(Color.WHITE);
        JLabel label = new JLabel(hint);
        label.setBorder(BorderFactory.createEmptyBorder(6, 8, 0, 8));
        page.add(label, BorderLayout.NORTH);
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Invoice", "Mode", "Provider / Table", "Customer", "Delivery User", "Total", "Register"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        styleOrdersTable(table);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        deliveryModels.put(key, model);
        deliveryTables.put(key, table);
        page.add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.setOpaque(false);
        if (!"delivered".equals(key)) {
            if ("assign".equals(key)) {
                JButton assign = makeButton("Assign & Print", PRIMARY, 14);
                assign.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { assignSelectedDelivery(); }
                });
                actions.add(assign);
            } else {
                JButton change = makeButton("Change User", BLUE, 14);
                JButton print = makeButton("Print Invoice", MODE_OFF, 14);
                JButton pay = makeButton("Make Payment", GREEN, 14);
                change.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { changeSelectedDeliveryUser(); }
                });
                print.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { printSelectedDeliveryInvoice(); }
                });
                pay.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { paySelectedDeliveryInvoice(); }
                });
                actions.add(change); actions.add(print); actions.add(pay);
            }
        }
        JButton refresh = makeButton("Refresh", MODE_OFF, 14);
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { refreshDeliveryLists(); }
        });
        actions.add(refresh);
        page.add(actions, BorderLayout.SOUTH);
        deliveryTabs.addTab(title, page);
    }

    private void refreshDeliveryLists() {
        if (con1 == null) return;
        for (DefaultTableModel m : deliveryModels.values()) m.setRowCount(0);
        String sql = "SELECT receipt_no, reg_num, `mode`, table_num, delivery_user, cust_name, total FROM "
            + tbl("mr_pos_hold_invoice") + " WHERE store_id=? ORDER BY id";
        try (PreparedStatement ps = con1.prepareStatement(sql)) {
            ps.setString(1, storeName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String user = rs.getString("delivery_user");
                    String mode = rs.getString("mode");
                    if (mode == null || !(mode.equalsIgnoreCase("Home Delivery") || mode.equalsIgnoreCase("Takeaway") || mode.equalsIgnoreCase("Online"))) continue;
                    String key = user == null || user.trim().length() == 0 ? "assign" : "assigned";
                    DefaultTableModel m = deliveryModels.get(key);
                    if (m != null) m.addRow(new Object[]{rs.getString("receipt_no"), mode,
                        rs.getString("table_num"), rs.getString("cust_name"), user == null ? "" : user,
                        fmt(rs.getDouble("total")), rs.getString("reg_num")});
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Cannot load delivery invoices.\n" + ex.getMessage(),
                "Delivery", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String paid = "SELECT receipt_no, reg_num, mode, table_num, delivery_user, cust_name, grand_total FROM "
            + tbl("mr_pos_settlement_cash") + " WHERE store_id=? AND delivery_user IS NOT NULL AND delivery_user<>'' "
            + "AND type='PAYCODE' ORDER BY id DESC";
        try (PreparedStatement ps = con1.prepareStatement(paid)) {
            ps.setString(1, storeName);
            try (ResultSet rs = ps.executeQuery()) {
                DefaultTableModel m = deliveryModels.get("delivered");
                while (rs.next()) m.addRow(new Object[]{rs.getString("receipt_no"), rs.getString("mode"),
                    rs.getString("table_num"), rs.getString("cust_name"), rs.getString("delivery_user"),
                    fmt(rs.getDouble("grand_total")), rs.getString("reg_num")});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Cannot load delivered invoices.\n" + ex.getMessage(),
                "Delivery", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String[] loadDeliveryUsers() throws SQLException {
        List<String> users = new ArrayList<String>();
        PreparedStatement ps = con1.prepareStatement("SELECT user_name FROM " + tbl("mr_users")
            + " WHERE user_type='Delivery' ORDER BY user_name");
        try (ResultSet rs = ps.executeQuery()) { while (rs.next()) users.add(rs.getString(1)); }
        finally { ps.close(); }
        return users.toArray(new String[users.size()]);
    }

    private void assignSelectedDelivery() {
        JTable t = deliveryTables.get("assign");
        int[] rows = t == null ? new int[0] : t.getSelectedRows();
        if (rows.length == 0) { JOptionPane.showMessageDialog(this, "Select one or more invoices first."); return; }
        List<String[]> invoices = new ArrayList<String[]>();
        for (int row : rows) invoices.add(new String[]{String.valueOf(t.getValueAt(row, 0)), String.valueOf(t.getValueAt(row, 6))});
        if (!changeDeliveryUser("assign")) return;
        if (JOptionPane.showConfirmDialog(this, "Print the assigned invoice(s) now?", "Print Invoice",
                JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
        for (String[] invoice : invoices) {
            try { report_master.holded_invoice.report(invoice[0], con1, Printer, invoice[1], "", dbName,
                "Hold Invoice", "", ""); }
            catch (Exception ex) { JOptionPane.showMessageDialog(this, "Unable to print invoice " + invoice[0] + ".\n" + ex.getMessage(),
                "Delivery", JOptionPane.ERROR_MESSAGE); }
        }
    }

    private void changeSelectedDeliveryUser() {
        changeDeliveryUser("assigned");
    }

    private boolean changeDeliveryUser(String key) {
        JTable t = deliveryTables.get(key); int[] rows = t == null ? new int[0] : t.getSelectedRows();
        if (rows.length == 0) { JOptionPane.showMessageDialog(this, "Select one or more invoices first."); return false; }
        String[] users;
        try { users = loadDeliveryUsers(); }
        catch (SQLException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Delivery", JOptionPane.ERROR_MESSAGE); return false; }
        if (users.length == 0) { JOptionPane.showMessageDialog(this, "No users with user_type='Delivery' were found."); return false; }
        String user = (String) JOptionPane.showInputDialog(this, "Select delivery user:", "Assign Delivery",
            JOptionPane.PLAIN_MESSAGE, null, users, users[0]);
        if (user == null) return false;
        String update = "UPDATE " + tbl("mr_pos_hold_invoice") + " SET delivery_user=? WHERE receipt_no=? AND reg_num=? AND store_id=?";
        try (PreparedStatement ps = con1.prepareStatement(update)) {
            for (int row : rows) {
                ps.setString(1, user); ps.setString(2, String.valueOf(t.getValueAt(row, 0)));
                ps.setString(3, String.valueOf(t.getValueAt(row, 6))); ps.setString(4, storeName); ps.addBatch();
            }
            ps.executeBatch();
            refreshDeliveryLists();
            return true;
        } catch (SQLException ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Delivery", JOptionPane.ERROR_MESSAGE); return false; }
    }

    private void printSelectedDeliveryInvoice() {
        JTable t = deliveryTables.get("assigned"); int row = t == null ? -1 : t.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Select an invoice first."); return; }
        int answer = JOptionPane.showConfirmDialog(this, "Print invoice " + t.getValueAt(row, 0) + "?",
            "Print Invoice", JOptionPane.YES_NO_OPTION);
        if (answer != JOptionPane.YES_OPTION) return;
        try { report_master.holded_invoice.report(String.valueOf(t.getValueAt(row, 0)), con1, Printer,
            String.valueOf(t.getValueAt(row, 6)), "", dbName, "Hold Invoice", "", ""); }
        catch (Exception ex) { JOptionPane.showMessageDialog(this, "Unable to print invoice.\n" + ex.getMessage(),
            "Delivery", JOptionPane.ERROR_MESSAGE); }
    }

    private void paySelectedDeliveryInvoice() {
        JTable t = deliveryTables.get("assigned"); int row = t == null ? -1 : t.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Select an invoice first."); return; }
        if (orderModel.getRowCount() > 0) { JOptionPane.showMessageDialog(this, "Hold or settle the current order first."); return; }
        String invoice = String.valueOf(t.getValueAt(row, 0));
        String register = String.valueOf(t.getValueAt(row, 6));
        loadHeldFromDb();
        HeldInvoice selected = null;
        for (HeldInvoice h : heldInvoices) if (invoice.equals(h.no) && register.equals(h.reg)) { selected = h; break; }
        if (selected == null) {
            try { selected = loadHeldInvoice(invoice, register); } catch (SQLException ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); return; }
        }
        if (selected == null) { JOptionPane.showMessageDialog(this, "Invoice not found."); return; }
        selected.deliveryUser = String.valueOf(t.getValueAt(row, 4));
        recallInvoice(selected);
        if (orderModel.getRowCount() > 0) openPayScreen();
    }

    private HeldInvoice loadHeldInvoice(String invoice, String register) throws SQLException {
        PreparedStatement ps = con1.prepareStatement("SELECT reg_num, receipt_no, total, cust_name, vat, disc, cust_code, `mode`, table_num, delivery_user, cust_ph, Chkbill_Sts FROM "
            + tbl("mr_pos_hold_invoice") + " WHERE receipt_no=? AND reg_num=? AND store_id=? ORDER BY id DESC LIMIT 1");
        HeldInvoice h = null;
        try { ps.setString(1, invoice); ps.setString(2, register); ps.setString(3, storeName); try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) { h = new HeldInvoice(); h.reg=rs.getString("reg_num"); h.no=rs.getString("receipt_no");
                h.total=rs.getDouble("total"); h.custName=rs.getString("cust_name"); h.vat=rs.getDouble("vat");
                h.discount=rs.getDouble("disc"); h.custCode=rs.getString("cust_code"); h.mode=rs.getString("mode");
                h.table=rs.getString("table_num"); h.deliveryUser=rs.getString("delivery_user"); h.custPh=rs.getString("cust_ph");
                h.billPrinted=rs.getInt("Chkbill_Sts")==1; } } } finally { ps.close(); }
        if (h != null) loadHeldItems(h);
        return h;
    }

    private void refreshOrders() {
        loadHeldFromDb();
        for (String m : ordersModels.keySet()) {
            ordersModels.get(m).setRowCount(0);
            ordersLists.get(m).clear();
        }
        for (HeldInvoice h : heldInvoices) {
            DefaultTableModel dm = ordersModels.get(h.mode);
            if (dm == null) continue;
            ordersLists.get(h.mode).add(h);
            dm.addRow(new Object[]{h.no, h.table == null ? "-" : h.table,
                nz(h.custName), h.rows.size(), fmt(h.total)});
        }
    }

    /** Reprint KOT for the invoice selected in the Orders table, without recalling it. */
    private void reprintSelectedHeldKot() {
        if (ordersTabs == null || ordersTabs.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Please select an invoice.");
            return;
        }

        String mode = ordersTabs.getTitleAt(ordersTabs.getSelectedIndex());
        JTable table = ordersTables.get(mode);
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select an invoice from the table first.",
                "Reprint KOT", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        List<HeldInvoice> list = ordersLists.get(mode);
        if (list == null || row >= list.size()) {
            JOptionPane.showMessageDialog(this, "The selected invoice could not be found. Refresh the Orders list and try again.",
                "Reprint KOT", JOptionPane.WARNING_MESSAGE);
            return;
        }

        HeldInvoice h = list.get(row);
        reprintKotForInvoice(h.no, h.reg, activeDept);
    }

    private void recallSelected() {
        if (ordersTabs == null || ordersTabs.getSelectedIndex() < 0) return;
        String mode = ordersTabs.getTitleAt(ordersTabs.getSelectedIndex());
        int row = ordersTables.get(mode).getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select an invoice.");
            return;
        }
        recallOrderAt(mode, row);
    }

    private void recallOrderAt(String mode, int row) {
        List<HeldInvoice> list = ordersLists.get(mode);
        if (list == null || row < 0 || row >= list.size()) {
            JOptionPane.showMessageDialog(this,
                "The selected invoice could not be found. Refresh the Orders list and try again.",
                "Recall Invoice", JOptionPane.WARNING_MESSAGE);
            return;
        }
        recallInvoice(list.get(row));
    }

    /**
     * Opens a held invoice again (from the Orders list or by clicking an occupied table).
     * The database copy stays until the invoice is held again (replaced) or settled (deleted).
     */
    private void recallInvoice(HeldInvoice h) {
        if (orderModel.getRowCount() > 0) {
            JOptionPane.showMessageDialog(this, "Current order is not empty. Hold or settle it first.");
            return;
        }
        heldInvoices.remove(h);
        for (Object[] r : h.rows) orderModel.addRow(r);
        setCustomer(h.custCode, h.custName, h.custBal, h.custPh);
        billPrinted = h.billPrinted;
        discount = h.discount;
        
        kotSeq = h.kotSeq;
        pendingCancel.clear();
        pendingCancel.addAll(h.cancels);
        activeInvoiceNo = h.no;
        activeReg = h.reg;
        deliveryUser = h.deliveryUser;
        selectedProvider = "Online".equalsIgnoreCase(h.mode) ? h.table : null;
        activeInvoiceNumberAdvanced = true; // a recalled held invoice already consumed its number
        currentMode = h.mode;
        selectedTable = "Dinein".equalsIgnoreCase(h.mode) ? h.table : null;
        recalc();
        setMode(h.mode);
        discountperc=(discount/Double.parseDouble(lblSubtotalInc.getText()))*100;
        cards.show(cardPanel, "MENU");
        
    }

    /** Held Dining invoice of this table, or null when the table is free. */
    
    private HeldInvoice heldForTable(String num) {
        for (HeldInvoice h : heldInvoices) {
            if ("Dinein".equals(h.mode) && num.equals(h.table)) return h;
        }
        return null;
    }

    /** Free table = green border; table with a held Dining invoice = red border + "Occupied". */
    private void refreshTableStates() {
        for (Map.Entry<String, JButton> en : tableButtons.entrySet()) {
            boolean occupied = heldForTable(en.getKey()) != null;
            JButton b = en.getValue();
            b.setBorder(BorderFactory.createLineBorder(occupied ? DANGER : GREEN, 4));
            b.setForeground(occupied ? DANGER : Color.BLACK);
            b.setText(occupied
                ? "<html><center>" + esc(en.getKey()) + "<br><small>Occupied</small>"
                    + "<br><small style='font-size:14pt'>AED " + esc(fmt(heldForTable(en.getKey()).total))
                    + "</small></center></html>"
                : en.getKey());
        }
    }

    // ---- Miscellaneous card ----
    private JPanel buildMiscCard() {
        JPanel p = new JPanel(new GridLayout(0, 3, 15, 15));
        p.setBackground(Color.WHITE);
        p.setBorder(titled("Miscellaneous"));
        String[] names = {"Duplicate Invoices", "Manager", "Historical Bill", "Split Table", "Split Invoice"};
        for (int i = 0; i < names.length; i++) {
            final String n = names[i];
            JButton b = makeButton(n, i < 3 ? BLUE : Color.GRAY, 15);
            b.setPreferredSize(new Dimension(0, 90));
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (n.equals("Manager")) {
                        // manager form: reg_nme = register code
                        showManagerCodeDialog();
                    } else if (n.equals("Duplicate Invoices")) {
                        try {
							openDuplicateBills();
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    } else if (n.equals("Historical Bill")) {
                        try {
							openHistoricalBills();
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    } else if (n.equals("Split Table")) {
                        splitCurrentTable();
                    } else if (n.equals("Split Invoice")) {
                        splitCurrentInvoice();
                    } else {
                        JOptionPane.showMessageDialog(RestaurantPOS.this, n + " - to be implemented");
                    }
                }
            });
            p.add(b);
        }
        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setBackground(Color.WHITE);
        wrap.setBorder(titled("Miscellaneous"));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(p, BorderLayout.NORTH);
        wrap.add(top, BorderLayout.CENTER);
        return wrap;
    }
    /** Split selected order lines into a second held invoice. The original invoice stays open. */
    private void splitCurrentTable() {
        splitOrderLines(true);
    }

    /** Split selected order lines into a separate invoice on the same table for separate settlement. */
    private void splitCurrentInvoice() {
        splitOrderLines(false);
    }

    private void splitOrderLines(boolean chooseTable) {
        if (orderEmpty()) {
            JOptionPane.showMessageDialog(this, "There are no items in the current invoice.");
            return;
        }
        if (!"Dinein".equalsIgnoreCase(currentMode) || selectedTable == null) {
            JOptionPane.showMessageDialog(this, "Table and invoice splitting is available for a Dinein order with a selected table.");
            return;
        }
        final JList<String> lines = new JList<String>();
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            listModel.addElement((i + 1) + ". " + orderModel.getValueAt(i, 0) + "  x" +
                qtyFmt(((Number) orderModel.getValueAt(i, 1)).doubleValue()) + "  " +
                fmt(((Number) orderModel.getValueAt(i, 3)).doubleValue()));
        }
        lines.setModel(listModel);
        lines.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        lines.setVisibleRowCount(Math.min(10, orderModel.getRowCount()));
        JComboBox<String> destination = new JComboBox<String>();
        if (chooseTable) {
            for (String table : tableNums) if (!table.equals(selectedTable)) destination.addItem(table);
            if (destination.getItemCount() == 0) {
                JOptionPane.showMessageDialog(this, "There is no other table to receive these items.");
                return;
            }
        }
        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.add(new JLabel(chooseTable ? "Select items to move to another table:" :
            "Select items to place on a separate invoice for this table:"), BorderLayout.NORTH);
        panel.add(new JScrollPane(lines), BorderLayout.CENTER);
        if (chooseTable) panel.add(destination, BorderLayout.SOUTH);
        int answer = JOptionPane.showConfirmDialog(this, panel,
            chooseTable ? "Split Table" : "Split Invoice", JOptionPane.OK_CANCEL_OPTION);
        int[] selected = lines.getSelectedIndices();
        if (answer != JOptionPane.OK_OPTION) return;
        if (selected.length == 0) {
            JOptionPane.showMessageDialog(this, "Select at least one item.");
            return;
        }
        if (con1 == null) {
            JOptionPane.showMessageDialog(this, "A database connection is required to split invoices.");
            return;
        }
        HeldInvoice source = snapshotCurrentInvoice();
        HeldInvoice split = new HeldInvoice();
        try {
            long activeNo = Long.parseLong(activeInvoiceNo);
            split.no = String.valueOf(activeInvoiceNumberAdvanced ? fetchInvoiceNoFromDb() : activeNo + 1);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Cannot allocate a new invoice number for the split.\n" + ex.getMessage());
            return;
        }
        split.reg = activeReg;
        split.mode = currentMode;
        split.table = chooseTable ? String.valueOf(destination.getSelectedItem()) : selectedTable;
        split.custCode = source.custCode; split.custName = source.custName;
        split.custPh = source.custPh; split.custBal = source.custBal;
        split.discount = 0; split.kotSeq = 0; split.billPrinted = false;
        Set<Integer> moved = new HashSet<Integer>();
        for (int i : selected) moved.add(Integer.valueOf(i));
        for (int i = 0; i < source.rows.size(); i++) {
            Object[] row = source.rows.get(i);
            if (moved.contains(Integer.valueOf(i))) split.rows.add(row.clone());
        }
        source.rows.clear();
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            if (!moved.contains(Integer.valueOf(i))) {
                Object[] row = new Object[orderModel.getColumnCount()];
                for (int c = 0; c < row.length; c++) row[c] = orderModel.getValueAt(i, c);
                source.rows.add(row);
            }
        }
        double originalGross = 0, remainingGross = 0, splitGross = 0;
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            double gross = ((Number) orderModel.getValueAt(i, 3)).doubleValue();
            originalGross += gross;
            if (moved.contains(Integer.valueOf(i))) splitGross += gross; else remainingGross += gross;
        }
        if (originalGross > EPS) {
            source.discount = discount * remainingGross / originalGross;
            split.discount = discount - source.discount;
        }
        recalculateHeldTotals(source);
        recalculateHeldTotals(split);
        HeldInvoice mergeTarget = chooseTable ? heldForTable(split.table) : null;
        if (mergeTarget != null) {
            mergeTarget.rows.addAll(split.rows);
            recalculateHeldTotals(mergeTarget);
        }
        try {
            if (source.rows.isEmpty()) {
                deleteHoldFromDb(source.no, source.reg);
                if (!activeInvoiceNumberAdvanced) incrementInvoiceNumber();
            }
            else saveHoldToDb(source, 0, !activeInvoiceNumberAdvanced);
            if (mergeTarget != null) {
                saveHoldToDb(mergeTarget, 0, false);
            } else {
                saveHoldToDb(split, 0, true);
                activeInvoiceNumberAdvanced = true;
            }
            orderModel.setRowCount(0);
            for (Object[] row : source.rows) orderModel.addRow(row);
            recalc();
            loadHeldFromDb();
            JOptionPane.showMessageDialog(this, mergeTarget != null
                ? "Moved selected items into the existing invoice for table " + split.table + "."
                : "Created separate held invoice " + split.no +
                (chooseTable ? " for table " + split.table : " for table " + selectedTable) +
                ". Recall it from Orders to settle it separately.", "Split Complete", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Could not split the order.\n" + ex.getMessage(),
                "Split Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private HeldInvoice snapshotCurrentInvoice() {
        HeldInvoice h = new HeldInvoice();
        h.no = activeInvoiceNo; h.reg = activeReg; h.mode = currentMode;
        h.table = "Online".equalsIgnoreCase(currentMode) ? selectedProvider : selectedTable;
        h.deliveryUser = deliveryUser;
        h.custCode = lblCustCode; h.custName = lblCustName.getText();
        h.custPh = lblCustPhone.getText(); h.custBal = lblCustBalance.getText();
        h.billPrinted = billPrinted; h.discount = discount; h.kotSeq = kotSeq;
        h.cancels.addAll(pendingCancel);
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            Object[] row = new Object[orderModel.getColumnCount()];
            for (int c = 0; c < row.length; c++) row[c] = orderModel.getValueAt(i, c);
            h.rows.add(row);
        }
        return h;
    }

    private void recalculateHeldTotals(HeldInvoice h) {
        h.total = 0; h.vat = 0;
        for (Object[] row : h.rows) {
            double qty = ((Number) row[1]).doubleValue();
            double price = ((Number) row[2]).doubleValue();
            h.total += qty * price;
            int rate = ((Number) row[5]).intValue();
            h.vat += qty * price * rate / (100.0 + rate);
        }
        h.total = Math.max(0, h.total - h.discount);
    }

    private void showManagerCodeDialog() {
        final JDialog dialog = new JDialog(this, "Manager Code", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        final JPasswordField codeField = new JPasswordField(18);
        codeField.setFont(codeField.getFont().deriveFont(Font.PLAIN, 20.0f));
        codeField.setEchoChar('\u2022');
        JPanel entry = new JPanel(new BorderLayout(6, 6));
        entry.add(new JLabel("Manager Code:"), BorderLayout.WEST);
        entry.add(codeField, BorderLayout.CENTER);
        content.add(entry, BorderLayout.NORTH);

        JPanel keypad = new JPanel(new GridLayout(4, 3, 6, 6));
        String[] keys = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "Clear", "0", "Backspace"};
        for (int i = 0; i < keys.length; i++) {
            final String key = keys[i];
            JButton button = new JButton(key);
            button.setFont(button.getFont().deriveFont(Font.BOLD, 16.0f));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if ("Clear".equals(key)) {
                        codeField.setText("");
                    } else if ("Backspace".equals(key)) {
                        int length = codeField.getPassword().length;
                        if (length > 0) codeField.setText(new String(codeField.getPassword(), 0, length - 1));
                    } else {
                        codeField.setText(new String(codeField.getPassword()) + key);
                    }
                    codeField.requestFocusInWindow();
                }
            });
            keypad.add(button);
        }
        content.add(keypad, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton enterButton = new JButton("Enter");
        JButton cancelButton = new JButton("Cancel");
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 char[] enteredCode = codeField.getPassword();
                 String swipeId = new String(enteredCode);
                 Arrays.fill(enteredCode, '\0');
                 if (swipeId.length() == 0) {
                     JOptionPane.showMessageDialog(dialog, "Enter or scan a Manager Code.",
                         "Manager Code", JOptionPane.WARNING_MESSAGE);
                     codeField.requestFocusInWindow();
                     return;
                 }
                 if (con1 == null) {
                     JOptionPane.showMessageDialog(dialog, "Cannot verify Manager Code without a database connection.",
                         "Manager Code", JOptionPane.ERROR_MESSAGE);
                     codeField.requestFocusInWindow();
                     return;
                 }

                 PreparedStatement ps = null;
                 ResultSet rs = null;
                 try {
                     ps = con1.prepareStatement("SELECT user_name FROM " + tbl("mr_users")
                         + " WHERE swipe_id = ?");
                     ps.setString(1, swipeId);
                     rs = ps.executeQuery();
                     if (!rs.next()) {
                         JOptionPane.showMessageDialog(dialog, "Invalid Manager Code.",
                             "Manager Code", JOptionPane.WARNING_MESSAGE);
                         codeField.setText("");
                         codeField.requestFocusInWindow();
                         return;
                     }

                     String managerUser = rs.getString(1);
                     if (managerUser == null || managerUser.trim().length() == 0) {
                         JOptionPane.showMessageDialog(dialog, "The Manager Code is not linked to a username.",
                             "Manager Code", JOptionPane.ERROR_MESSAGE);
                         codeField.setText("");
                         codeField.requestFocusInWindow();
                         return;
                     }
                     if (rs.next()) {
                         JOptionPane.showMessageDialog(dialog,
                             "This Manager Code matches more than one user. Ask an administrator to resolve it.",
                             "Manager Code", JOptionPane.ERROR_MESSAGE);
                         codeField.setText("");
                         codeField.requestFocusInWindow();
                         return;
                     }

                     // Load the existing user-type and POS controls before granting
                     // this identity for the next protected action.
                     UserAuthorization auth = getUserAuthorization(managerUser);
                     if (auth == null) {
                         codeField.requestFocusInWindow();
                         return;
                     }
                     applyManagerUser(managerUser);
                     dialog.dispose();
                 } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(dialog,
                         "Cannot verify Manager Code.\n" + ex.getMessage(),
                         "Authorization", JOptionPane.ERROR_MESSAGE);
                     codeField.requestFocusInWindow();
                 } finally {
                     if (rs != null) {
                         try { rs.close(); } catch (SQLException ignored) { }
                     }
                     if (ps != null) {
                         try { ps.close(); } catch (SQLException ignored) { }
                     }
                 }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { dialog.dispose(); }
        });
        codeField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { enterButton.doClick(); }
        });
        actions.add(enterButton);
        actions.add(cancelButton);
        content.add(actions, BorderLayout.SOUTH);

        dialog.setContentPane(content);
        dialog.getRootPane().setDefaultButton(enterButton);
        dialog.pack();
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this);
        codeField.requestFocusInWindow();
        dialog.setVisible(true);
    }
    /** Opens the duplicate-bill window for settled/closed invoices. 
     * @throws JSONException */
    private void openDuplicateBills() throws JSONException {
    	new duplicate_invoice(this, dbName,username,storeName,posReg,Printer, version,Integer.toString(dec));
    }

    /** Opens the historical-bill window for settled/closed invoices. 
     * @throws JSONException */
    private void openHistoricalBills() throws JSONException {
    	new historical_invoice(dbName,username,storeName,posReg,Printer, version,Integer.toString(dec));
    }

    // ---------------- RIGHT : customer + order table + buttons ----------------
    private JPanel buildOrderPanel() {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setPreferredSize(new Dimension(430, 0));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(207, 216, 220)),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)));

        // customer section (top): Customer <phone1> [Change] / Name / Balance
        JPanel cust = new JPanel(new GridBagLayout());
        cust.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(3, 3, 3, 3);
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx = 0; g.gridy = 0; g.weightx = 0;
        cust.add(boldLabel("Customer"), g);
        lblCustPhone = new JLabel(custPhone.length() == 0 ? "-" : custPhone);
        lblCustPhone.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.gridx = 1; g.weightx = 1;
        cust.add(lblCustPhone, g);

        JLabel link = new JLabel("<html><u>Change</u></html>");
        link.setFont(new Font("SansSerif", Font.BOLD, 13));
        link.setForeground(BLUE);
        link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        link.setToolTipText("Click to select customer");
        link.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // customer list form; it must call setCustomer(code, name, balance, phone1) on this screen
                try {
                    new delivery_customer(dbName, version, "1", username, storeName, posReg, con1, "cust_chooser");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });
        g.gridx = 2; g.weightx = 0;
        cust.add(link, g);

        g.gridx = 0; g.gridy = 1; g.weightx = 0;
        cust.add(boldLabel("Name"), g);
        lblCustName = new JLabel(DEF_CUST_NAME);
        g.gridx = 1; g.gridwidth = 2; g.weightx = 1;
        cust.add(lblCustName, g);

        g.gridx = 0; g.gridy = 2; g.gridwidth = 1; g.weightx = 0;
        cust.add(boldLabel("Balance"), g);
        lblCustBalance = new JLabel(DEF_CUST_BAL);
        lblCustBalance.setForeground(DANGER);
        g.gridx = 1; g.gridwidth = 2; g.weightx = 1;
        cust.add(lblCustBalance, g);
        p.add(cust, BorderLayout.NORTH);

        // order table (center)
        orderModel = new DefaultTableModel(
            new Object[]{"Item", "Qty", "Price", "Total", "Code", "VAT%", "KOT", "REC"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        orderTable = new JTable(orderModel);
        styleTable(orderTable);
        orderTable.getColumnModel().getColumn(0).setPreferredWidth(170);
        orderTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        orderTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        orderTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        // hidden data columns - still available in the model
        orderTable.removeColumn(orderTable.getColumnModel().getColumn(7));   // ItemRec
        orderTable.removeColumn(orderTable.getColumnModel().getColumn(6));   // qty already sent to kitchen
        orderTable.removeColumn(orderTable.getColumnModel().getColumn(5));   // VAT %
        orderTable.removeColumn(orderTable.getColumnModel().getColumn(4));   // item code
        // rows with quantity not yet sent to the kitchen are shown in yellow
        orderTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable t, Object v, boolean sel,
                                                           boolean focus, int row, int col) {
                Component c = super.getTableCellRendererComponent(t, v, sel, focus, row, col);
                if (v instanceof Double) setText(col == 1 ? qtyFmt((Double) v) : fmt((Double) v));
                setHorizontalAlignment(col == 0 ? SwingConstants.LEFT : SwingConstants.RIGHT);
                if (!sel) {
                    double qty = (Double) orderModel.getValueAt(row, 1);
                    double sent = (Double) orderModel.getValueAt(row, 6);
                    c.setBackground(Math.abs(qty - sent) > EPS ? new Color(255, 243, 190) : Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });
        orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // double-click a row -> delete (with prompt)
        orderTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int r = orderTable.rowAtPoint(e.getPoint());
                    if (r >= 0) {
                        orderTable.setRowSelectionInterval(r, r);
                        deleteSelectedRow();
                    }
                }
            }
        });
        // Delete key -> delete (with prompt)
        orderTable.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteRow");
        orderTable.getActionMap().put("deleteRow", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { deleteSelectedRow(); }
        });

        // quantity row:  [-1]  [+1]  [Qty...]  [Delete]
        JPanel qtyRow = new JPanel(new GridLayout(1, 4, 4, 0));
        qtyRow.setOpaque(false);
        qtyRow.setPreferredSize(new Dimension(0, 38));
        JButton bMinus = makeButton("\u2212 1", MODE_OFF, 14);
        JButton bPlus  = makeButton("+ 1", MODE_OFF, 14);
        JButton bQty   = makeButton("Qty...", BLUE, 14);
        JButton bDel   = makeButton("Delete", DANGER, 14);
        bMinus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { changeQty(-1); }
        });
        bPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { changeQty(1); }
        });
        bQty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { setQtyDialog(); }
        });
        bDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { deleteSelectedRow(); }
        });
        qtyRow.add(bMinus); qtyRow.add(bPlus); qtyRow.add(bQty); qtyRow.add(bDel);

        JPanel center = new JPanel(new BorderLayout(0, 4));
        center.setOpaque(false);
        center.add(new JScrollPane(orderTable), BorderLayout.CENTER);
        center.add(qtyRow, BorderLayout.SOUTH);
        p.add(center, BorderLayout.CENTER);

        // totals + buttons (bottom)
        JPanel bottom = new JPanel(new BorderLayout(0, 8));
        bottom.setOpaque(false);

        JPanel totals = new JPanel(new GridLayout(5, 2, 4, 4));
        totals.setOpaque(false);
        lblSub = valueLabel(false);
        lblTax = valueLabel(false);
        lblSubtotalInc = valueLabel(false);
        lblDisc = valueLabel(false);
        lblDisc.setForeground(DANGER);
        lblTotal = valueLabel(true);
        totals.add(boldLabel("Sub Total (exc VAT)")); totals.add(lblSub);
        totals.add(boldLabel("VAT")); totals.add(lblTax);
        totals.add(boldLabel("Sub Total (inc VAT)")); totals.add(lblSubtotalInc);
        totals.add(boldLabel("Discount")); totals.add(lblDisc);
        JLabel tl = boldLabel("TOTAL"); tl.setFont(new Font("SansSerif", Font.BOLD, 18));
        totals.add(tl); totals.add(lblTotal);
        bottom.add(totals, BorderLayout.NORTH);

        // 3 rows: [Hold | Check Bill] / [KOT Print | Discount] / [Settle]
        JPanel btns = new JPanel(new GridLayout(3, 1, 0, 6));
        btns.setOpaque(false);

        JPanel row1 = new JPanel(new GridLayout(1, 2, 6, 0));
        row1.setOpaque(false);
        JButton hold = makeButton("HOLD", ACCENT, 15);
        JButton check = makeButton("CHECK BILL", BLUE, 15);
        row1.add(hold); row1.add(check);

        JButton kot = makeButton("KOT PRINT", MODE_OFF, 15);
        JButton disc = makeButton("DISCOUNT", new Color(123, 31, 162), 15);
        JPanel row2 = new JPanel(new GridLayout(1, 2, 6, 0));
        row2.setOpaque(false);
        row2.add(kot); row2.add(disc);

        settle = makeButton("SETTLE", GREEN, 18);

        btns.add(row1); btns.add(row2); btns.add(settle);
        btns.setPreferredSize(new Dimension(0, 168));
        bottom.add(btns, BorderLayout.CENTER);
        p.add(bottom, BorderLayout.SOUTH);

        hold.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { doHold(); }
        });
        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { doCheckBill(); }
        });
        kot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { doKot(); }
        });
        disc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { doDiscount(); }
        });
        settle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
            	//settle.setEnabled(false);
            	doSettle();
            	
            }
        });
        recalc();
        return p;
    }

    // =====================================================================
    //  Behaviour
    // =====================================================================
    private void onModeClicked(String m) {
        // Not allowed to change the mode while the current invoice has items
        if (orderModel.getRowCount() > 0 && !m.equals(activeMode)
                && !m.equals("Miscellaneous")) {
            JOptionPane.showMessageDialog(this,
                "Current invoice has items.\nPlease Hold or Settle it before changing the mode.",
                "Mode locked", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Clicking Dining again with an empty order lets the user pick another table
        if (m.equals("Dinein") && m.equals(activeMode) && orderModel.getRowCount() == 0) {
            selectedTable = null;
        }
        if (m.equals("Online")) {
            if (!chooseOnlineProvider()) return;
        }
        if (m.equals("Orders")) {
            refreshOrders();
            highlight(m);
            cards.show(cardPanel, "ORDERS");
        } else if (m.equals("Miscellaneous")) {
            highlight(m);
            cards.show(cardPanel, "MISC");
        } else {
            setMode(m);
        }
    }

    private void setMode(String m) {
        currentMode = m;
        highlight(m);
        if (m.equals("Dinein")) {
            tablePanelInfo.setVisible(true);
            lblTableCaption.setText("SELECT TABLE");
            if (selectedTable == null) loadHeldFromDb();       // fresh occupied / free state of the tables
            cards.show(cardPanel, selectedTable == null ? "TABLES" : "MENU");
        } else {
            selectedTable = null;
            boolean online = "Online".equalsIgnoreCase(m);
            tablePanelInfo.setVisible(online);
            lblTableCaption.setText(online ? "ONLINE PROVIDER (click to change)" : "SELECT TABLE");
            cards.show(cardPanel, "MENU");
        }
        if (activeDept != null) showItems(activeDept);          // price list follows the mode (online / indoor)
        updateInfo();
    }

    private boolean chooseOnlineProvider() {
        if (con1 == null) { JOptionPane.showMessageDialog(this, "Database connection is not available."); return false; }
        List<String> providers = new ArrayList<String>();
        try (PreparedStatement ps = con1.prepareStatement("SELECT user_name FROM " + tbl("mr_take_away_users")
                + " WHERE status='1' ORDER BY user_name"); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) providers.add(rs.getString(1));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Cannot load online providers.\n" + ex.getMessage(), "Online Provider", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (providers.isEmpty()) { JOptionPane.showMessageDialog(this, "No active online providers were found."); return false; }
        Object choice = JOptionPane.showInputDialog(this, "Select online provider:", "Online Provider",
            JOptionPane.PLAIN_MESSAGE, null, providers.toArray(new String[providers.size()]),
            selectedProvider == null ? providers.get(0) : selectedProvider);
        if (choice == null) return false;
        selectedProvider = String.valueOf(choice);
        if ("Online".equalsIgnoreCase(currentMode)) updateInfo();
        return true;
    }

    private void highlight(String active) {
        activeMode = active;
        for (String k : modeButtons.keySet()) {
            JButton b = modeButtons.get(k);
            boolean on = k.equals(active);
            b.setBorder(on ? BorderFactory.createLineBorder(new Color(255, 111, 0), 5)
                           : BorderFactory.createEmptyBorder());
            b.setBorderPainted(on);
        }
    }

    private double roundToDec(double value) {
        double factor = Math.pow(10.0, dec);
        return Math.round(value * factor) / factor;
    }

    private String fmt(double v) {
        return String.format("%." + dec + "f", v);
    }

    private String currentInvoiceNo() {
        return activeInvoiceNo;
    }

    /** SELECT (invoice_number + next_num) AS inv_num FROM <db_name>.mr_register_setup */
//    private long fetchInvoiceNoFromDb() throws SQLException {
//        if (con1 == null) throw new SQLException("No database connection supplied.");
//        PreparedStatement ps = con1.prepareStatement(
//            "SELECT (invoice_number+next_num) AS inv_num FROM " + tbl("mr_register_setup"));
//        try {
//            ResultSet rs = ps.executeQuery();
//            long n = -1;
//            if (rs.next()) n = rs.getLong("inv_num");
//            rs.close();
//            return n;
//        } finally {
//            ps.close();
//        }
//    }


/**
 * Reserves one invoice number for this register and store.
 * Example: invoice_number = 1000, next_num = 5
 * Returns 1005 and changes next_num to 6.
 */
//private long fetchInvoiceNoFromDb() throws SQLException {
//    if (con1 == null) {
//        throw new SQLException("No database connection supplied.");
//    }
//
//    // This method must not commit another module's transaction.
//    if (!con1.getAutoCommit()) {
//        throw new SQLException(
//            "Invoice reservation requires an auto-commit connection."
//        );
//    }
//
//    boolean transactionStarted = false;
//
//    try {
//        con1.setAutoCommit(false);
//        transactionStarted = true;
//
//        long invoiceNo;
//        long rowId;
//        int nextNum;
//
//        String selectSql =
//            "SELECT id, invoice_number, next_num " +
//            "FROM " + tbl("mr_register_setup") + " " +
//            "WHERE register_number = ? AND store_number = ? " +
//            "ORDER BY id LIMIT 1 FOR UPDATE";
//
//        PreparedStatement ps = con1.prepareStatement(selectSql);
//        try {
//            ps.setString(1, posReg);
//            ps.setString(2, storeName);
//
//            ResultSet rs = ps.executeQuery();
//            try {
//                if (!rs.next()) {
//                    throw new SQLException(
//                        "Register setup not found for register "
//                        + posReg + " and store " + storeName
//                    );
//                }
//
//                rowId = rs.getLong("id");
//                long base = rs.getLong("invoice_number");
//                nextNum = rs.getInt("next_num");
//
//                invoiceNo = base + nextNum;
//            } finally {
//                rs.close();
//            }
//        } finally {
//            ps.close();
//        }
//
//        String updateSql =
//            "UPDATE " + tbl("mr_register_setup") + " " +
//            "SET next_num = next_num + 1 WHERE id = ?";
//
//        PreparedStatement update = con1.prepareStatement(updateSql);
//        try {
//            update.setLong(1, rowId);
//
//            if (update.executeUpdate() != 1) {
//                throw new SQLException(
//                    "Could not advance the invoice counter."
//                );
//            }
//        } finally {
//            update.close();
//        }
//
//        con1.commit();
//        transactionStarted = false;
//
//        return invoiceNo;
//
//    } catch (SQLException ex) {
//        if (transactionStarted) {
//            try {
//                con1.rollback();
//            } catch (SQLException rollbackEx) {
//                ex.addSuppressed(rollbackEx);
//            }
//        }
//        throw ex;
//
//    } finally {
//        if (transactionStarted) {
//            try {
//                con1.rollback();
//            } catch (SQLException ignored) {
//            }
//        }
//
//        con1.setAutoCommit(true);
//    }
//}
private long fetchInvoiceNoFromDb() throws SQLException {
    if (con1 == null) {
        throw new SQLException("No database connection supplied.");
    }

    String sql =
        "SELECT (invoice_number + next_num) AS inv_num " +
        "FROM " + tbl("mr_register_setup") + " " +
        "WHERE register_number = ? AND store_number = ?";

    try (PreparedStatement ps = con1.prepareStatement(sql)) {
        ps.setString(1, posReg);
        ps.setString(2, storeName);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getLong("inv_num");
            }
        }
    }

    throw new SQLException("Register setup not found.");
}
private void incrementInvoiceNumber() throws SQLException {
    String updateSql =
        "UPDATE " + tbl("mr_register_setup") + " " +
        "SET next_num = next_num + 1 " +
        "WHERE register_number = ? AND store_number = ?";

    try (PreparedStatement update = con1.prepareStatement(updateSql)) {
        update.setString(1, posReg);
        update.setString(2, storeName);

        int rowsUpdated = update.executeUpdate();

        if (rowsUpdated != 1) {
            throw new SQLException(
                "Expected to update one register setup row, but updated "
                + rowsUpdated
            );
        }
    }
}

    private String GetPrinterNames() throws SQLException {
        if (con1 == null) throw new SQLException("No database connection supplied.");
        PreparedStatement ps = con1.prepareStatement(
            "SELECT printer1 AS receiptprinter FROM " + tbl("mr_register_setup"));
        try {
            ResultSet rs = ps.executeQuery();
            String n = "";
            if (rs.next()) n = rs.getString("receiptprinter");
            rs.close();
            return n;
        } finally {
            ps.close();
        }
    }

    /** Gets the next invoice number from the register setup. */
    private void refreshInvoiceNo(boolean warn) {
        long n = -1;
        try {
            n = fetchInvoiceNoFromDb();
        } catch (Exception ex) {
            if (warn) {
                JOptionPane.showMessageDialog(this, "Cannot read the invoice number.\n" + ex.getMessage(),
                    "Invoice", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (n < 0) n = (lastInvoice < 0) ? 1001 : lastInvoice + 1;   // fallback
        lastInvoice = n;
        activeInvoiceNo = String.valueOf(n);
    }

    private List<String> loadTablesFromDb() throws SQLException {
        if (con1 == null) throw new SQLException("No database connection supplied.");
        List<String> list = new ArrayList<String>();
        PreparedStatement ps = con1.prepareStatement("SELECT table_num FROM " + tbl("mr_kot_tables")
            + " WHERE store_code = ? ORDER BY id, CAST(table_num AS UNSIGNED), table_num");
        try {
            ps.setString(1, storeName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(rs.getString("table_num"));
            rs.close();
        } finally {
            ps.close();
        }
        return list;
    }

    /** Title: Restaurant POS v<version> | Store | Register | User [| AUTHORIZED BY manager]. */
    private void updateTitle() {
        String v = (version == null || version.trim().length() == 0) ? "" : " v" + version.trim();
        String t = "Restaurant POS" + v + "  |  Store: " + storeName + "  |  Register: " + posReg
                 + "  |  User: " + username;
        if (overrideUser != null) t += "  |  AUTHORIZED BY: " + overrideUser + " (one action)";
        setTitle(t);
    }

    // =====================================================================
    //  Callbacks for other forms
    // =====================================================================

    /** Static access for other forms:  RestaurantPOS.getCurrent().applyManagerUser(name) ... */
    public static RestaurantPOS getCurrent() { return current; }

    /** Called by the manager form after the manager card is accepted. Valid for ONE protected action only. */
    public void applyManagerUser(final String user) {
        Runnable r = new Runnable() {
            public void run() {
                overrideUser = (user == null || user.trim().length() == 0) ? null : user.trim();
                updateTitle();
            }
        };
        if (SwingUtilities.isEventDispatchThread()) r.run(); else SwingUtilities.invokeLater(r);
    }

    /** Called by the customer list form: the phone is displayed, the code is kept as a hidden value. */
    public void setCustomer(final String code, final String name, final String balance, final String phone1) {
        Runnable r = new Runnable() {
            public void run() {
                custCode = code == null ? "" : code;
                custPhone = phone1 == null ? "" : phone1;
                lblCustCode=(code == null ? "" : code);
                lblCustPhone.setText(custPhone.length() == 0 ? "0" : custPhone);
                lblCustName.setText(name == null ? "" : name);
                lblCustBalance.setText(balance == null ? "" : balance);
            }
        };
        if (SwingUtilities.isEventDispatchThread()) r.run(); else SwingUtilities.invokeLater(r);
    }

    /** Called by the pay screen after the payment is completed: removes the held copy and starts a new invoice. */
    public void paymentCompleted() {
        Runnable r = new Runnable() {
            public void run() {
                if (con1 != null) {
                    try {
                        deleteHoldFromDb(activeInvoiceNo, activeReg);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(RestaurantPOS.this,
                            "Cannot clear the held invoice.\n" + ex.getMessage(), "Hold", JOptionPane.WARNING_MESSAGE);
                    }
                }
                newInvoice();
                if (deliveryModels.size() > 0) refreshDeliveryLists();
            }
        };
        if (SwingUtilities.isEventDispatchThread()) r.run(); else SwingUtilities.invokeLater(r);
    }

    // =====================================================================
    //  Manager authorization / order-row editing
    // =====================================================================

    /** Existing permission: Administrator OR OverrideGrossMargin = 1. */
    private boolean isAuthorized(String user) {
        if (con1 == null) return true;   // stand-alone test mode
        UserAuthorization auth = getUserAuthorization(user);
        return auth != null && (auth.administrator || auth.hasControl("OverrideGrossMargin"));
    }

    /**
     * Reusable check for any 0/1 control in mr_user_poscontrol.
     * Examples: hasPosControl(user, "AllowDiscount"), hasPosControl(user, "DeleteSettledInvoice").
     */
    public boolean hasPosControl(String user, String controlName) {
        if (con1 == null) return true;   // stand-alone test mode
        UserAuthorization auth = getUserAuthorization(user);
        return auth != null && (auth.administrator || auth.hasControl(controlName));
    }
    public boolean hasActingUserPosControl(String controlName) {
        return hasPosControl(takeActingUser(), controlName);
    }

    /**
     * Loads all control columns in one SELECT and stores them in arrays.
     * Newly added columns are discovered automatically by ResultSetMetaData.
     */
    private UserAuthorization getUserAuthorization(String user) {
        if (user == null || user.trim().length() == 0) return null;
        String cacheKey = user.trim().toLowerCase(Locale.ENGLISH);

        UserAuthorization cached = authorizationCache.get(cacheKey);
        if (cached != null) return cached;

        UserAuthorization loaded = new UserAuthorization();
        try {
            PreparedStatement userPs = con1.prepareStatement(
                "SELECT " + USER_TYPE_COL + " FROM " + tbl("mr_users")
                + " WHERE user_name = ?");
            try {
                userPs.setString(1, user);
                ResultSet userRs = userPs.executeQuery();
                try {
                    loaded.administrator = userRs.next()
                        && userRs.getString(1) != null
                        && "Administrator".equalsIgnoreCase(userRs.getString(1).trim());
                } finally {
                    userRs.close();
                }
            } finally {
                userPs.close();
            }

            PreparedStatement controlPs = con1.prepareStatement(
                "SELECT * FROM "+ tbl("mr_user_poscontrol") + " inner join "+ tbl("mr_users") + " on user_code=ref_user_code WHERE user_name= ?");
            try {
                controlPs.setString(1, user);
                ResultSet rs = controlPs.executeQuery();
                try {
                    if (rs.next()) {
                        java.sql.ResultSetMetaData md = rs.getMetaData();
                        List<String> names = new ArrayList<String>();
                        List<Integer> values = new ArrayList<Integer>();

                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            String column = md.getColumnLabel(i);
                            if (column == null || column.trim().length() == 0) {
                                column = md.getColumnName(i);
                            }
                            if (column == null || USER_COL.equalsIgnoreCase(column.trim())) {
                                continue;
                            }

                            Object raw = rs.getObject(i);
                            int flag = 0;
                            if (raw instanceof Number) {
                                flag = ((Number) raw).intValue();
                            } else if (raw != null) {
                                String text = raw.toString().trim();
                                if ("true".equalsIgnoreCase(text) || "yes".equalsIgnoreCase(text)) {
                                    flag = 1;
                                } else {
                                    try { flag = Integer.parseInt(text); }
                                    catch (NumberFormatException ignored) { flag = 0; }
                                }
                            }
                            names.add(column.trim());
                            values.add(Integer.valueOf(flag));
                        }

                        loaded.controlNames = names.toArray(new String[names.size()]);
                        loaded.controlValues = new int[values.size()];
                        for (int i = 0; i < values.size(); i++) {
                            loaded.controlValues[i] = values.get(i).intValue();
                        }
                    }
                } finally {
                    rs.close();
                }
            } finally {
                controlPs.close();
            }

            authorizationCache.put(cacheKey, loaded);
            return loaded;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Cannot verify user rights.\\n" + ex.getMessage(),
                "Authorization", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /** Clear after a manager changes rights or when the logged-in user changes. */
    private void clearAuthorizationCache() {
        authorizationCache.clear();
    }

    /**
     * Removing/reducing items is free until the bill is printed (Chkbill_Sts = 1). After that only an
     * authorized user (current user, or a manager applied for one action) may do it.
     */
    private boolean allowRemoval() {
        if (!billPrinted) return true;
        String user = takeActingUser();
        boolean ok = isAuthorized(user);
        if (!ok) {
            JOptionPane.showMessageDialog(this, "You are not authorized for this. Contact manager.",
                "Not authorized", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }

    /** User who performs the next protected action; a manager applied for one action is used up here. */
    private String takeActingUser() {
        String u = (overrideUser != null) ? overrideUser : username;
        if (overrideUser != null) {
            overrideUser = null;
            updateTitle();
        }
        return u;
    }

    private void deleteSelectedRow() {
        int r = orderTable.getSelectedRow();
        if (r < 0) {
            JOptionPane.showMessageDialog(this, "Please select an item in the order table.");
            return;
        }
        String name = String.valueOf(orderModel.getValueAt(r, 0));
        int ans = JOptionPane.showConfirmDialog(this, "Delete \"" + name + "\" from the order?",
            "Delete item", JOptionPane.YES_NO_OPTION);
        if (ans != JOptionPane.YES_OPTION) return;
        if (!allowRemoval()) return;
        double sent = (Double) orderModel.getValueAt(r, 6);
        if (sent > EPS) pendingCancel.add(new Object[]{name, sent});   // kitchen must be told
        orderModel.removeRow(r);
        recalc();
    }

    private void changeQty(double delta) {
        int r = orderTable.getSelectedRow();
        if (r < 0) {
            JOptionPane.showMessageDialog(this, "Please select an item in the order table.");
            return;
        }
        double q = (Double) orderModel.getValueAt(r, 1) + delta;
        if (q <= EPS) { deleteSelectedRow(); return; }
        if (delta < 0 && !allowRemoval()) return;
        setQty(r, q);
    }

    /** Quantity change with the on-screen number pad (0-9 and decimal point). */
    private void setQtyDialog() {
        int r = orderTable.getSelectedRow();
        if (r < 0) {
            JOptionPane.showMessageDialog(this, "Please select an item in the order table.");
            return;
        }
        double cur = (Double) orderModel.getValueAt(r, 1);
        NumPad d = askNumber("Quantity - " + orderModel.getValueAt(r, 0), qtyFmt(cur), true, null);
        if (d == null) return;
        double q;
        try {
            q = Double.parseDouble(d.result);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            return;
        }
        if (q <= EPS) { deleteSelectedRow(); return; }
        if (q < cur - EPS && !allowRemoval()) return;
        setQty(r, q);
    }

    private void setQty(int r, double q) {
        double price = (Double) orderModel.getValueAt(r, 2);
        orderModel.setValueAt(q, r, 1);
        orderModel.setValueAt(q * price, r, 3);
        recalc();
    }

    private void updateInfo() {
        refreshTableStates();
        lblMode.setText(currentMode.toUpperCase());
        lblInvoice.setText(currentInvoiceNo() + (billPrinted ? "  [BILLED]" : ""));
        if ("Online".equalsIgnoreCase(currentMode)) lblTable.setText(selectedProvider == null ? "Select provider" : selectedProvider);
        else lblTable.setText(selectedTable == null ? "Not selected" : "Table " + selectedTable);
    }

    private void addItem(ItemRec it) {
        if (currentMode.equals("Dinein") && selectedTable == null) {
            JOptionPane.showMessageDialog(this, "Please select a table first.");
            cards.show(cardPanel, "TABLES");
            return;
        }
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            if (orderModel.getValueAt(i, 4).equals(it.code)) {
                double q = (Double) orderModel.getValueAt(i, 1) + 1;
                orderModel.setValueAt(q, i, 1);
                orderModel.setValueAt(q * it.price, i, 3);
                recalc();
                return;
            }
        }
        orderModel.addRow(new Object[]{it.name, 1.0, it.price, it.price, it.code, it.taxRate, 0.0, it});
        recalc();
    }

    /** Sum of line totals. Item prices already INCLUDE VAT, so this is the final payable total. */
    private double grossTotal() {
        double s = 0;
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            s += (Double) orderModel.getValueAt(i, 3);
        }
        return s;
    }

    /** Prices include VAT. VAT part of each line = lineTotal * rate / (100 + rate), rate from output_tax_rate. */
    private void recalc() {
        double total = 0, tax = 0;
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            double lt = (Double) orderModel.getValueAt(i, 3);
            int rate = (Integer) orderModel.getValueAt(i, 5);
            total += lt;
            tax += lt * rate / (100.0 + rate);
        }
        if (orderModel.getRowCount() == 0) discount = 0;
        if (discount > total) discount = total;
        double subtotalIncVat = total;                       // VAT-inclusive subtotal before discount
        double net = Math.max(0, subtotalIncVat - discount);  // final payable after discount
        double subtotalExcVat = subtotalIncVat - tax;         // pre-discount amount excluding VAT
        lastTax = tax;
        lblSub.setText(fmt(subtotalExcVat));
        lblTax.setText(fmt(tax));
        lblSubtotalInc.setText(fmt(subtotalIncVat));
        lblDisc.setText(discount > 0 ? "-" + fmt(discount) : fmt(0));
        lblTotal.setText(fmt(net));
    }

    private boolean orderEmpty() {
        if (orderModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No items in the order.");
            return true;
        }
        return false;
    }

    /** Cancelled items the kitchen has not been told about (no KOT printed since the removal). */
    private boolean hasUnsentCancels() {
        if (!pendingCancel.isEmpty()) return true;
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            if ((Double) orderModel.getValueAt(i, 6) > (Double) orderModel.getValueAt(i, 1) + EPS) return true;
        }
        return false;
    }

    // ---------------- HOLD ----------------
    private void doHold() {
        if (orderEmpty()) return;
        if (hasUnsentCancels()) {
            int a = JOptionPane.showConfirmDialog(this,
                "Some cancelled items have not been sent to the kitchen (KOT).\nHold anyway?",
                "Hold", JOptionPane.YES_NO_OPTION);
            if (a != JOptionPane.YES_OPTION) return;
        }
        holdInvoice(false,"HOLD");
    }

    /**
     * Saves the current invoice to mr_pos_hold_invoice / mr_pos_hold_invoice_items and starts a new one.
     * Also used by KOT PRINT (print, then hold).
     */
    private boolean holdInvoice(boolean silent,String pagetype) {
        return holdInvoice(silent, 0,pagetype);
    }

    /** newKotNum > 0 means this save is being performed for that specific KOT. */
    private boolean holdInvoice(boolean silent, int newKotNum,String pagetype) {
        HeldInvoice h = new HeldInvoice();
        h.no = activeInvoiceNo;
        h.reg = activeReg;
        h.mode = currentMode;
        h.table = "Online".equalsIgnoreCase(currentMode) ? selectedProvider : selectedTable;
        h.deliveryUser = deliveryUser;
        h.custCode = lblCustCode;
        h.custName = lblCustName.getText();
        h.custPh = lblCustPhone.getText();;
        h.custBal = lblCustBalance.getText();
        h.billPrinted = billPrinted;
        h.discount = discount;
        h.kotSeq = kotSeq;
        h.cancels.addAll(pendingCancel);
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            Object[] r = new Object[orderModel.getColumnCount()];
            for (int c = 0; c < r.length; c++) r[c] = orderModel.getValueAt(i, c);
            h.rows.add(r);
        }
        // Save the same VAT-inclusive, post-discount amount shown as TOTAL.
        h.total = parseMoney(lblTotal.getText());
        h.vat = lastTax;

        if (con1 != null) {
            try {
                saveHoldToDb(h, newKotNum, !activeInvoiceNumberAdvanced);
                activeInvoiceNumberAdvanced = true;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Cannot save the held invoice.\n" + ex.getMessage(),
                    "Hold", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            heldInvoices.add(h);              // stand-alone test mode: keep in memory only
            activeInvoiceNumberAdvanced = true;
        }

        if(pagetype.equals("CHECK"))
        {
        	if (!silent) {
        		int choice = JOptionPane.showConfirmDialog(
        			    this,
        			    "Do you want to print Check Bill invoice?",
        			    "Print Check Bill",
        			    JOptionPane.YES_NO_OPTION,
        			    JOptionPane.QUESTION_MESSAGE
        			);

        			if (choice == JOptionPane.YES_OPTION) {
        			    try {
        			        report_master.holded_invoice.report(
        			            activeInvoiceNo,
        			            con1,
        			            Printer,
        			            posReg,
        			            "",
        			            dbName,
        			            "Hold Invoice",
        			            "",
        			            Integer.toString(kotSeq)
        			        );
        			    } catch (Exception ex) {
        			        ex.printStackTrace();
        			        JOptionPane.showMessageDialog(
        			            this,
        			            "Unable to print hold invoice: " + ex.getMessage(),
        			            "Print Error",
        			            JOptionPane.ERROR_MESSAGE
        			        );
        			    }
        			}
        	}
        }
        else
        {
        	if (!silent) {
        		int choice = JOptionPane.showConfirmDialog(
        			    this,
        			    "Do you want to print hold invoice?",
        			    "Print Hold Invoice",
        			    JOptionPane.YES_NO_OPTION,
        			    JOptionPane.QUESTION_MESSAGE
        			);

        			if (choice == JOptionPane.YES_OPTION) {
        			    try {
        			        report_master.holded_invoice.report(
        			            activeInvoiceNo,
        			            con1,
        			            Printer,
        			            posReg,
        			            "",
        			            dbName,
        			            "Hold Invoice",
        			            "",
        			            Integer.toString(kotSeq)
        			        );
        			    } catch (Exception ex) {
        			        ex.printStackTrace();
        			        JOptionPane.showMessageDialog(
        			            this,
        			            "Unable to print hold invoice: " + ex.getMessage(),
        			            "Print Error",
        			            JOptionPane.ERROR_MESSAGE
        			        );
        			    }
        			}
        	}
        }
        


// Start a new invoice with the next invoice number
//newInvoice();
//refreshInvoiceNo(true);
//updateInfo();
loadHeldFromDb();
refreshTableStates();

newInvoice();
updateInfo();
return true;

    }

    // ---------------- CHECK BILL ----------------
    private void doCheckBill() {
        if (orderEmpty()) return;
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice : ").append(currentInvoiceNo()).append("\n");
        sb.append("Mode    : ").append(currentMode).append("\n");
        if (selectedTable != null) sb.append("Table   : ").append(selectedTable).append("\n");
        sb.append("---------------------------------\n");
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            sb.append(String.format("%-16s %5s x %6.2f = %8.2f\n",
                orderModel.getValueAt(i, 0), qtyFmt((Double) orderModel.getValueAt(i, 1)),
                orderModel.getValueAt(i, 2), orderModel.getValueAt(i, 3)));
        }
        sb.append("---------------------------------\n");
        if (discount > 0) sb.append("Discount   : -").append(fmt(discount)).append("\n");
        sb.append("Sub Total (exc VAT): ").append(lblSub.getText()).append("\n");
        sb.append("VAT               : ").append(lblTax.getText()).append("\n");
        sb.append("Sub Total (inc VAT): ").append(lblSubtotalInc.getText()).append("\n");
        sb.append("Discount          : ").append(lblDisc.getText()).append("\n");
        sb.append("TOTAL             : ").append(lblTotal.getText());
//        JTextArea ta = new JTextArea(sb.toString());
//        ta.setFont(new Font("Monospaced", Font.PLAIN, 13));
//        ta.setEditable(false);
//        JOptionPane.showMessageDialog(this, new JScrollPane(ta), "Check Bill", JOptionPane.PLAIN_MESSAGE);
        billPrinted = true;
        String checkedInvoiceNo = activeInvoiceNo;
        String checkedRegister = activeReg;
        if (!holdInvoice(false,"CHECK")) return;
        // Chkbill_Sts = 1: from now on removing items needs authorization
        markBilledInDb(checkedInvoiceNo, checkedRegister);
        updateInfo();
    }

    // ---------------- KOT PRINT (= print the new items, then hold) ----------------
    /**
     * KOT prints only what the kitchen has not received yet.
     * Every order row remembers the quantity already sent (hidden column 6):
     *   qty to send = qty - sent      (+ = new items, - = reduced)
     * Items deleted after a KOT are listed as cancelled. After printing, the invoice is held.
     */
    private void doKot() {
        StringBuilder add = new StringBuilder(), cancel = new StringBuilder();
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            double d = (Double) orderModel.getValueAt(i, 1) - (Double) orderModel.getValueAt(i, 6);
            String name = String.valueOf(orderModel.getValueAt(i, 0));
            if (d > EPS) add.append(String.format("  %-24s +%s\n", name, qtyFmt(d)));
            else if (d < -EPS) cancel.append(String.format("  %-24s -%s\n", name, qtyFmt(-d)));
        }
        for (Object[] c : pendingCancel) {
            cancel.append(String.format("  %-24s -%s\n", c[0], qtyFmt((Double) c[1])));
        }
        if (add.length() == 0 && cancel.length() == 0) {
            JOptionPane.showMessageDialog(this, orderModel.getRowCount() == 0
                ? "No items in the order." : "No new items to send to the kitchen.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("KOT #").append(kotSeq + 1).append("   Invoice ").append(currentInvoiceNo()).append("\n");
        sb.append("Mode : ").append(currentMode);
        if (selectedTable != null) sb.append("    Table : ").append(selectedTable);
        sb.append("\n---------------------------------\n");
        if (add.length() > 0) sb.append("NEW ITEMS\n").append(add);
        if (cancel.length() > 0) sb.append("CANCELLED\n").append(cancel);
//        JTextArea ta = new JTextArea(sb.toString());
//        ta.setFont(new Font("Monospaced", Font.PLAIN, 13));
//        ta.setEditable(false);
//        JOptionPane.showMessageDialog(this, new JScrollPane(ta), "KOT Print", JOptionPane.PLAIN_MESSAGE);
        
        final String inv_num = activeInvoiceNo;
        final int kot_num = kotSeq + 1;
        final String current_register = activeReg;

        // Do not mark every model row as sent here. The save routine splits each
        // row into previously-sent and newly-added quantities for this KOT.
        pendingCancel.clear();
        kotSeq = kot_num;
        orderTable.repaint();

        // Save the invoice and tag only the unsent quantity with this KOT number.
        if (!holdInvoice(true, kot_num,"KOT")) {
            return;
        }

        try {
            // Only print if this exact KOT has pending rows. This prevents a stale
            // report query from reprinting a previous KOT after invoice recall.
            if (!hasPendingKotRows(inv_num, current_register, kot_num)) {
                return;
            }
            report_master.holded_invoice.report(
                inv_num,
                con1,
                Printer,
                current_register,
                "",
                dbName,
                "Kitchen KOT",
                activeDept ,
                Integer.toString(kot_num)
            );
            markKotItemsForPrinting2(inv_num, current_register, kot_num);
            loadHeldFromDb();
            refreshTableStates();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this,
                "Unable to print KOT #" + kot_num + ".\n" + ex.getMessage(),
                "KOT Printing",
                JOptionPane.ERROR_MESSAGE
            );
        }
        
    }
    /**
     * Reprints existing KOTs without changing the invoice, KOT sequence, or print flags.
     * Last KOT prints only the highest saved KOT number; Reprint All uses the
     * report type "dup_kot_invoice" to print the complete invoice KOT history.
     */
    private void doReprintKot() {
        reprintKotForInvoice(activeInvoiceNo, activeReg, activeDept);
    }

    /**
     * Reprints saved KOTs for a specified invoice/register.
     * Last KOT prints only the highest saved KOT number; Reprint All uses
     * the report type "dup_kot_invoice". This method does not change print flags.
     */
    private void reprintKotForInvoice(String invNum, String register, String dept) {
        if (invNum == null || invNum.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Please select an invoice to reprint.");
            return;
        }

        Object[] options = {"Last KOT", "Reprint All KOTs", "Cancel"};
        int choice = JOptionPane.showOptionDialog(
            this,
            "Invoice: " + invNum + "\\nChoose which kitchen ticket to reprint.",
            "Reprint KOT",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (choice == 2 || choice == JOptionPane.CLOSED_OPTION) return;

        if (con1 == null) {
            JOptionPane.showMessageDialog(this, "Database connection is not available.",
                "Reprint KOT", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int lastKot = 0;
        try {
            String sql = "SELECT COALESCE(MAX(kot_number), 0) FROM "
                + tbl("mr_pos_hold_invoice_items")
                + " WHERE ref_receipt_num = ? AND ref_reg_no = ? AND kot_number > 0";
            try (PreparedStatement ps = con1.prepareStatement(sql)) {
                ps.setString(1, invNum);
                ps.setString(2, register);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) lastKot = rs.getInt(1);
                }
            }

            if (lastKot <= 0) {
                JOptionPane.showMessageDialog(this,
                    "No saved KOT was found for invoice " + invNum + ".",
                    "Reprint KOT", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (choice == 0) {
                report_master.holded_invoice.report(
                    invNum, con1, Printer, register, "", dbName,
                    "Kitchen KOT", dept, Integer.toString(lastKot)
                );
            } else {
                report_master.holded_invoice.report(
                    invNum, con1, Printer, register, "", dbName,
                    "dup_kot_invoice", dept, ""
                );
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Unable to reprint KOT for invoice " + invNum + ".\\n" + ex.getMessage(),
                "Reprint KOT", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean hasPendingKotRows(String invNum, String register, int kotNum) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tbl("mr_pos_hold_invoice_items")
            + " WHERE ref_receipt_num = ? AND ref_reg_no = ? AND kot_number = ?"
            + " AND print_sts = 0 AND print_cmd = 0";
        try (PreparedStatement ps = con1.prepareStatement(sql)) {
            ps.setString(1, invNum);
            ps.setString(2, register);
            ps.setInt(3, kotNum);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private void markKotItemsForPrinting2(String invNum, String register, int kotNum) throws SQLException {

        
        String updateSql =
            "UPDATE " + tbl("mr_pos_hold_invoice_items") +
            " SET print_cmd = 1,print_sts = 1 " +
            "WHERE ref_receipt_num = ? AND ref_reg_no = ? " +
            "AND kot_number = ? AND print_sts = 0 AND print_cmd = 0";

        try (PreparedStatement ps = con1.prepareStatement(updateSql)) {
            ps.setString(1, invNum);
            ps.setString(2, register);
            ps.setInt(3, kotNum);

            ps.executeUpdate();
        }
    }

    // ---------------- DISCOUNT (number pad: Amount / Percentage) ----------------

    /**
     * -1 = discount not allowed (maxdisc_user_sale <> 1), otherwise the maximum discount in PERCENT of the
     * invoice (mr_user_poscontrol.discount_amount).
     */
    private double discountLimit(String user) {
        if (con1 == null) return Double.MAX_VALUE;   // stand-alone test mode
        try {
            PreparedStatement ps = con1.prepareStatement("SELECT maxdisc_user_sale, discount_amount FROM "+ tbl("mr_user_poscontrol") + " inner join "+ tbl("mr_users") + " on user_code=ref_user_code WHERE user_name = ?");
            try {
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();
                double limit = -1;
                if (rs.next() && rs.getInt(1) == 1) limit = rs.getDouble(2);
                rs.close();
                return limit;
            } finally {
                ps.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Cannot verify discount rights.\n" + ex.getMessage(),
                "Authorization", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private void doDiscount() {
        if (orderEmpty()) return;
        double gross = grossTotal();
        NumPad d = askNumber("Discount on total " + fmt(gross), discount > 0 ? fmt(discount) : "", true,
                             new String[]{"Discount Amount", "Discount %"});
        if (d == null) return;
        double val;
        try {
            val = Double.parseDouble(d.result);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            return;
        }
        discountperc=(d.mode == 1) ? val : (val/gross)*100;
        double amt = (d.mode == 1) ? gross * val / 100.0 : val;
        double f = Math.pow(10, dec);
        amt = Math.round(amt * f) / f;
        if (amt < 0 || amt > gross) {
            JOptionPane.showMessageDialog(this, "Discount must be between 0 and " + fmt(gross) + ".");
            return;
        }
        if (amt > 0) {
            double pct = gross > 0 ? amt * 100.0 / gross : 0;
            double limit = discountLimit(takeActingUser());
            if (limit < 0) {
                JOptionPane.showMessageDialog(this, "You are not authorized for discount. Contact manager.",
                    "Not authorized", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (pct > limit + 0.0001) {
                JOptionPane.showMessageDialog(this, "Maximum discount allowed is " + String.format("%.2f", limit)
                    + "% of the invoice. Contact manager for more.", "Not authorized", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        discount = amt;
        recalc();
    }

    // ---------------- SETTLE ----------------
    private void doSettle() {
        if (orderEmpty()) return;
        openPayScreen();
    }

    /**
     * TODO: open the pay code screen here and pass: invoice no (activeInvoiceNo), net total (lblTotal),
     * customer (custCode), mode, table, the order rows ...
     * When the payment is finished the pay screen must call  RestaurantPOS.getCurrent().paymentCompleted().
     */
    
    private double parseMoney(String value) {
        if (value == null) return 0.0;
        try { return Double.parseDouble(value.replace(",", "").trim()); }
        catch (NumberFormatException ex) { return 0.0; }
    }
private void openPayScreen() {
    final double subtotal = parseMoney(lblSub.getText());
    final double vat = lastTax;
    final double total = parseMoney(lblTotal.getText());

//    RestaurantSettlementScreen.SettlementHandler settlementHandler =
//        new RestaurantSettlementScreen.SettlementHandler() {
//            public boolean confirmPayment(RestaurantSettlementScreen.Payment payment)
//                    throws Exception {
//                saveRestaurantPayment(payment);
//                paymentCompleted();
//                return true;
//            }
//        };
    RestaurantSettlementScreen.SettlementHandler settlementHandler =
            new RestaurantSettlementScreen.SettlementHandler() {
                public boolean confirmPayment(RestaurantSettlementScreen.Payment payment)
                        throws Exception {
                    // Save the settlement first. Do not print a receipt for an unsaved payment.
                    saveRestaurantPayment(payment);

                    // Print before newInvoice() changes the active invoice/register context.
                    int copy = 1;
                    try {
                        report_master.Bill_template.report(
                            payment.invoiceNo,
                            con1,
                            Printer,
                            activeReg,
                            "",
                            dbName,
                            1
                        );
                    } catch (Exception printEx) {
                        // Payment is already committed: keep it settled if printing fails.
                        JOptionPane.showMessageDialog(
                            RestaurantPOS.this,
                            "Payment was saved, but the receipt could not be printed.\n"
                                + printEx.getMessage(),
                            "Receipt printing",
                            JOptionPane.WARNING_MESSAGE
                        );
                    }

                    paymentCompleted();
                    return true;
                }
            };

    RestaurantSettlementScreen screen = new RestaurantSettlementScreen(
        this,
        activeInvoiceNo,
        lblCustCode,
        lblCustName.getText(),
        subtotal,
        discount,
        vat,
        total,
        dec,
        settlementHandler,
        con1,
        dbName
    );
    screen.setVisible(true);
}

/**
 * Persist settlement header, invoice items and every used paycode in one transaction.
 * The caller's shared Connection is not closed.
 */
private void saveRestaurantPayment(RestaurantSettlementScreen.Payment payment)
        throws SQLException {
    if (con1 == null) throw new SQLException("No database connection supplied.");
    if (payment == null || payment.lines == null || payment.lines.isEmpty())
        throw new SQLException("No payment lines were supplied.");
    if (orderModel == null || orderModel.getRowCount() == 0)
        throw new SQLException("The invoice has no items to settle.");

    // The settlement schema stores the pre-discount, pre-VAT subtotal in `total`;
    // grand_total stores the final VAT-inclusive payable amount.
    final double invoiceTotal = parseMoney(lblTotal.getText());
    final double invoiceSubtotalBeforeVat = parseMoney(lblSub.getText());

    boolean oldAutoCommit = con1.getAutoCommit();
    try {
        con1.setAutoCommit(false);
        String headerSql = "INSERT INTO " + tbl("mr_pos_settlement_cash") +
            " (reg_num, receipt_no, `user`, `time`, total, cust_name, vat, disc, qty, cash_paid, type, " +
            "paycode_disc, store_id, created_date, grand_total, cust_code, cust_prev_balance, " +
            "delivery_user, table_num, mode, cust_ph) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        double qtyTotal = 0.0;
        double cashPaid = 0.0;
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            qtyTotal += ((Number) orderModel.getValueAt(i, 1)).doubleValue();
        }
        for (RestaurantSettlementScreen.PaymentLine line : payment.lines) {
            if (line.paycode != null && line.paycode.trim().equalsIgnoreCase("CASH")) {
                cashPaid += line.amount;
            }
        }
        long headerId;
        try (PreparedStatement ps = con1.prepareStatement(headerSql,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            ps.setString(i++, activeReg == null ? posReg : activeReg);
            ps.setString(i++, activeInvoiceNo);
            ps.setString(i++, username);
            ps.setTime(i++, new java.sql.Time(System.currentTimeMillis()));
            ps.setDouble(i++, invoiceSubtotalBeforeVat);
            ps.setString(i++, lblCustName.getText());
            ps.setDouble(i++, roundToDec(lastTax));
            ps.setDouble(i++, discount);
            ps.setDouble(i++, qtyTotal);
            ps.setDouble(i++, cashPaid);
            ps.setString(i++, "PAYCODE");
            ps.setDouble(i++, 0.0); // paycode_disc
            ps.setString(i++, storeName);
            ps.setDate(i++, new java.sql.Date(System.currentTimeMillis()));
            ps.setDouble(i++, invoiceTotal);
            ps.setString(i++, lblCustCode);
            ps.setDouble(i++, parseMoney(lblCustBalance.getText()));
            ps.setString(i++, nz(deliveryUser));
            String tableOrProvider = "Online".equalsIgnoreCase(currentMode) ? selectedProvider : selectedTable;
            if (tableOrProvider == null || tableOrProvider.trim().length() == 0)
                ps.setNull(i++, java.sql.Types.VARCHAR);
            else ps.setString(i++, tableOrProvider);
            ps.setString(i++, currentMode);
            ps.setString(i++, custPhone == null ? "" : custPhone);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (!keys.next()) throw new SQLException("Could not get settlement header ID.");
                headerId = keys.getLong(1);
            }
        }

        String itemSql = "INSERT INTO " + tbl("mr_pos_settlement_cash_items") +
            " (ref_receipt_num, ref_reg_num, barcode, item_desc, item_code, item_cost, unit, " +
            "unit_price, sp, qty, total, vat, discount, created_date, vat_type, vat_perc, disc_type, cf) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        double gross = grossTotal();
        for (int row = 0; row < orderModel.getRowCount(); row++) {
            Object rawRec = orderModel.getValueAt(row, 7);
            ItemRec item = rawRec instanceof ItemRec ? (ItemRec) rawRec : null;
            double qty = ((Number) orderModel.getValueAt(row, 1)).doubleValue();
            double lineTotal = ((Number) orderModel.getValueAt(row, 3)).doubleValue();
            int vatRate = ((Number) orderModel.getValueAt(row, 5)).intValue();
            double lineVat = roundToDec(lineTotal * vatRate / (100.0 + vatRate));
            double lineDiscount = gross > 0 ? discount * lineTotal / gross : 0.0;
            try (PreparedStatement ps = con1.prepareStatement(itemSql)) {
                int i = 1;
                ps.setString(i++, activeInvoiceNo);
                ps.setString(i++, activeReg == null ? posReg : activeReg);
                ps.setString(i++, item == null ? "" : nz(item.barcode));
                ps.setString(i++, String.valueOf(orderModel.getValueAt(row, 0)));
                ps.setString(i++, String.valueOf(orderModel.getValueAt(row, 4)));
                ps.setDouble(i++, item == null ? 0.0 : parseMoney(item.cost));
                ps.setString(i++, item == null ? "" : nz(item.unit));
                ps.setDouble(i++, item == null ? ((Number) orderModel.getValueAt(row, 2)).doubleValue() : item.price);
                ps.setDouble(i++, item == null ? ((Number) orderModel.getValueAt(row, 2)).doubleValue() : item.sp);
                ps.setDouble(i++, qty);
                ps.setDouble(i++, roundToDec(lineTotal - lineVat));
                ps.setDouble(i++, lineVat);
                ps.setDouble(i++, lineDiscount);
                ps.setDate(i++, new java.sql.Date(System.currentTimeMillis()));
                if (vatRate > 0) ps.setString(i++, "VAT"); else ps.setNull(i++, java.sql.Types.VARCHAR);
                ps.setDouble(i++, vatRate);
                ps.setNull(i++, java.sql.Types.VARCHAR);
                ps.setDouble(i++, item == null ? 0.0 : item.cf);
                ps.executeUpdate();
            }
        }

        String paySql = "INSERT INTO " + tbl("mr_paycode_settlement") +
            " (ref_reg_num, ref_receipt_num, paycode_name, paycode_rate, paycode_amt, paid_amt, " +
            "created_date, pay_code_vat, status) VALUES (?,?,?,?,?,?,?,?,?)";
        for (RestaurantSettlementScreen.PaymentLine line : payment.lines) {
            try (PreparedStatement ps = con1.prepareStatement(paySql)) {
                ps.setString(1, activeReg == null ? posReg : activeReg);
                ps.setString(2, activeInvoiceNo);
                ps.setString(3, line.paycode);
                ps.setDouble(4, line.exchangeRate);
                ps.setDouble(5, line.amount);
                ps.setDouble(6, line.convertedAmount);
                ps.setDate(7, new java.sql.Date(System.currentTimeMillis()));
                ps.setDouble(8, 0.0);
                ps.setInt(9, 0);
                ps.executeUpdate();
            }
        }
        con1.commit();
    } catch (SQLException ex) {
        try { con1.rollback(); } catch (SQLException ignored) { }
        throw ex;
    } finally {
        try { con1.setAutoCommit(oldAutoCommit); } catch (SQLException ignored) { }
    }
}
private boolean voidPreviousSettledBill(
        Connection con,
        String receiptNo,
        String receiptReg,
        boolean isPaycode) throws SQLException {

    PreparedStatement psPaycode = null;
    PreparedStatement psHeader = null;

    boolean oldAutoCommit = con.getAutoCommit();

    try {
        con.setAutoCommit(false);

        // Preserve the existing PAYCODE reversal behavior.
        if (isPaycode) {
            psPaycode = con.prepareStatement(
                "UPDATE " + tbl("mr_paycode_settlement") + " SET status = '1' " +
                "WHERE ref_receipt_num = ? " +
                "AND ref_reg_num = ?"
            );

            psPaycode.setString(1, receiptNo);
            psPaycode.setString(2, receiptReg);
            psPaycode.executeUpdate();
        }

        // Void the original settled bill; never delete it.
        psHeader = con.prepareStatement(
            "UPDATE " + tbl("mr_pos_settlement_cash") + " SET type = 'VOID' " +
            "WHERE receipt_no = ? " +
            "AND reg_num = ? " +
            "AND (type IS NULL OR type <> 'VOID')"
        );

        psHeader.setString(1, receiptNo);
        psHeader.setString(2, receiptReg);

        int updated = psHeader.executeUpdate();

        if (updated != 1) {
            throw new SQLException(
                "Original settled bill was not voided. " +
                "Receipt: " + receiptNo +
                ", Register: " + receiptReg
            );
        }

        con.commit();
        return true;

    } catch (SQLException ex) {
        try {
            con.rollback();
        } catch (SQLException rollbackEx) {
            ex.addSuppressed(rollbackEx);
        }

        throw ex;

    } finally {
        if (psPaycode != null) {
            try {
                psPaycode.close();
            } catch (SQLException ignored) {
            }
        }

        if (psHeader != null) {
            try {
                psHeader.close();
            } catch (SQLException ignored) {
            }
        }

        try {
            con.setAutoCommit(oldAutoCommit);
        } catch (SQLException ignored) {
        }
    }
}
public boolean recallSettledInvoice(final String receiptNo, final String receiptReg) throws SQLException {
    if (!SwingUtilities.isEventDispatchThread()) {
        final boolean[] result = new boolean[] { false };
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() { try {
					result[0] = recallSettledInvoice(receiptNo, receiptReg);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Could not recall invoice:\n" + ex.getMessage());
        }
        return result[0];
    }
    if (orderModel == null || orderModel.getRowCount() > 0) {
        JOptionPane.showMessageDialog(this, "Current order is not empty. Hold or settle it first.");
        return false;
    }
    if (con1 == null) {
        JOptionPane.showMessageDialog(this, "No database connection is available.");
        return false;
    }
    boolean isPaycode =true; /* determine this using your existing
            PAYCODE invoice identification logic */;
    if (!voidPreviousSettledBill(
            con1, receiptNo, receiptReg, isPaycode)) {
        return false;
    }
    String headerSql = "SELECT receipt_no, reg_num, cust_name, vat, disc, cust_code, cust_prev_balance, table_num, mode, cust_ph "
        + "FROM " + tbl("mr_pos_settlement_cash") + " WHERE receipt_no=? AND reg_num=?";
    String itemsSql = "SELECT barcode, item_desc, item_code, item_cost, unit, unit_price, sp, qty, vat_perc, cf "
        + "FROM " + tbl("mr_pos_settlement_cash_items") + " WHERE ref_receipt_num=? AND ref_reg_num=?";
    try {
        String mode = "Takeaway", tableNo = null, custCode1 = DEF_CUST_CODE;
        String custName1 = DEF_CUST_NAME, custPhone1 = "", custBalance1 = DEF_CUST_BAL;
        double oldDiscount = 0.0;
        boolean found = false;
        PreparedStatement hp = con1.prepareStatement(headerSql);
        try {
            hp.setString(1, receiptNo); hp.setString(2, receiptReg);
            ResultSet rs = hp.executeQuery();
            try {
                if (rs.next()) {
                    found = true;
                    mode = rs.getString("mode");
                    if (mode == null || mode.trim().length() == 0) mode = "Takeaway";
                    tableNo = rs.getString("table_num");
                    custCode1 = rs.getString("cust_code");
                    custName1 = rs.getString("cust_name");
                    custPhone1 = rs.getString("cust_ph");
                    Object bal = rs.getObject("cust_prev_balance");
                    custBalance1 = bal == null ? DEF_CUST_BAL : String.valueOf(bal);
                    oldDiscount = rs.getDouble("disc");
                }
            } finally { rs.close(); }
        } finally { hp.close(); }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Settled invoice was not found.");
            return false;
        }
        PreparedStatement ip = con1.prepareStatement(itemsSql);
        int loaded = 0;
        try {
            ip.setString(1, receiptNo); ip.setString(2, receiptReg);
            ResultSet rs = ip.executeQuery();
            try {
                while (rs.next()) {
                    ItemRec item = new ItemRec();
                    item.name = nz(rs.getString("item_desc"));
                    item.code = nz(rs.getString("item_code"));
                    item.barcode = nz(rs.getString("barcode"));
                    Object cost = rs.getObject("item_cost");
                    item.cost = cost == null ? "0" : String.valueOf(cost);
                    item.unit = nz(rs.getString("unit"));
                    item.price = rs.getDouble("unit_price");
                    item.sp = rs.getDouble("sp");
                    item.taxRate = rs.getInt("vat_perc");
                    item.cf = rs.getDouble("cf");
                    double qty = rs.getDouble("qty");
                    orderModel.addRow(new Object[] { item.name, Double.valueOf(qty),
                        Double.valueOf(item.price), Double.valueOf(qty * item.price), item.code,
                        Integer.valueOf(item.taxRate), Double.valueOf(0.0), item });
                    loaded++;
                }
            } finally { rs.close(); }
        } finally { ip.close(); }
        if (loaded == 0) {
            orderModel.setRowCount(0);
            JOptionPane.showMessageDialog(this, "No items found for receipt " + receiptNo + ".");
            return false;
        }
        refreshInvoiceNo(false); // preserve original settlement; edits become a new invoice
        activeReg = posReg;
        billPrinted = false;
        kotSeq = 0;
        pendingCancel.clear();
        discount = oldDiscount;
        currentMode = mode;
        selectedTable = "Dinein".equalsIgnoreCase(mode) ? tableNo : null;
        setCustomer(custCode1, custName1, custBalance1, custPhone1);
        setMode(mode);
        recalc();
        cards.show(cardPanel, "MENU");
        JOptionPane.showMessageDialog(this, "Invoice " + receiptNo + " recalled for editing.\n"
            + "Working invoice number: " + activeInvoiceNo + ". Original settlement history is unchanged.",
            "Recall Bill", JOptionPane.INFORMATION_MESSAGE);
        return true;
    } catch (Exception ex) {
        orderModel.setRowCount(0);
        JOptionPane.showMessageDialog(this, "Could not recall settled invoice:\n" + ex.getMessage(),
            "Recall Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
    private void newInvoice() {
        refreshInvoiceNo(false);
        activeInvoiceNumberAdvanced = false;
        activeReg = posReg;
        billPrinted = false;
        discount = 0;
        kotSeq = 0;
        pendingCancel.clear();
        overrideUser = null;
        updateTitle();
        setCustomer(DEF_CUST_CODE, DEF_CUST_NAME, DEF_CUST_BAL, DEF_CUST_PHONE);
        orderModel.setRowCount(0);
        selectedTable = null;
        selectedProvider = null;
        deliveryUser = null;
        recalc();
        setMode(currentMode);
    }

    // =====================================================================
    //  Number pad
    // =====================================================================

    /**
     * Shows the on-screen number pad. Returns null when cancelled, otherwise the dialog with
     * .result (the typed number) and .mode (selected mode button, 0-based).
     * To use the number pad that already exists in the manager form, only this method needs to change.
     */
    private NumPad askNumber(String title, String initial, boolean decimal, String[] modes) {
        NumPad d = new NumPad(this, title, initial, decimal, modes);
        d.pack();
        d.setLocationRelativeTo(this);
        d.setVisible(true);
        return d.result == null ? null : d;
    }

    /** Modal number pad: 0-9, decimal point, delete, clear, Cancel, OK. Optional mode buttons (e.g. Amount / %). */
    private static class NumPad extends JDialog {
        private final JTextField field = new JTextField();
        private final boolean allowDecimal;
        private boolean fresh = true;     // first key replaces the initial text
        String result = null;
        int mode = 0;

        NumPad(Frame owner, String title, String initial, boolean allowDecimal, String[] modes) {
            super(owner, title, true);
            this.allowDecimal = allowDecimal;

            field.setText(initial == null ? "" : initial);
            field.setEditable(false);
            field.setBackground(Color.WHITE);
            field.setFont(new Font("SansSerif", Font.BOLD, 32));
            field.setHorizontalAlignment(SwingConstants.RIGHT);
            field.setPreferredSize(new Dimension(300, 60));
            // physical keyboard also works
            field.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (c >= '0' && c <= '9') press(String.valueOf(c));
                    else if (c == '.') press(".");
                    else if (c == '\b') press("DEL");
                    else if (c == '\n') accept();
                    else if (c == 27) dispose();
                }
            });

            JPanel root = new JPanel(new BorderLayout(8, 8));
            root.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            root.add(field, BorderLayout.NORTH);

            JPanel centre = new JPanel(new BorderLayout(0, 8));
            if (modes != null) {
                JPanel mp = new JPanel(new GridLayout(1, modes.length, 6, 0));
                ButtonGroup grp = new ButtonGroup();
                for (int i = 0; i < modes.length; i++) {
                    final int idx = i;
                    JToggleButton tb = new JToggleButton(modes[i]);
                    tb.setFont(new Font("SansSerif", Font.BOLD, 15));
                    tb.setFocusable(false);
                    tb.setSelected(i == 0);
                    tb.setPreferredSize(new Dimension(100, 44));
                    tb.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) { mode = idx; }
                    });
                    grp.add(tb);
                    mp.add(tb);
                }
                centre.add(mp, BorderLayout.NORTH);
            }

            JPanel pad = new JPanel(new GridLayout(4, 3, 6, 6));
            String[] keys = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "DEL"};
            for (int i = 0; i < keys.length; i++) {
                final String k = keys[i];
                JButton b = new JButton(k);
                b.setFont(new Font("SansSerif", Font.BOLD, 24));
                b.setFocusable(false);
                b.setPreferredSize(new Dimension(90, 62));
                if (k.equals(".") && !allowDecimal) b.setEnabled(false);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { press(k); }
                });
                pad.add(b);
            }
            centre.add(pad, BorderLayout.CENTER);
            root.add(centre, BorderLayout.CENTER);

            JPanel bottom = new JPanel(new GridLayout(1, 3, 6, 0));
            JButton clear = new JButton("Clear");
            JButton cancel = new JButton("Cancel");
            JButton ok = new JButton("OK");
            clear.setFont(new Font("SansSerif", Font.BOLD, 16));
            cancel.setFont(new Font("SansSerif", Font.BOLD, 16));
            ok.setFont(new Font("SansSerif", Font.BOLD, 18));
            ok.setBackground(new Color(46, 125, 50));
            ok.setForeground(Color.WHITE);
            ok.setOpaque(true);
            clear.setFocusable(false);
            cancel.setFocusable(false);
            ok.setFocusable(false);
            clear.setPreferredSize(new Dimension(90, 54));
            clear.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { field.setText(""); fresh = false; }
            });
            cancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { result = null; dispose(); }
            });
            ok.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { accept(); }
            });
            bottom.add(clear); bottom.add(cancel); bottom.add(ok);
            root.add(bottom, BorderLayout.SOUTH);

            setContentPane(root);
            setResizable(false);
            addWindowListener(new WindowAdapter() {
                public void windowOpened(WindowEvent e) { field.requestFocusInWindow(); }
            });
        }

        private void press(String k) {
            String t = field.getText();
            if (k.equals("DEL")) {
                if (t.length() > 0) field.setText(t.substring(0, t.length() - 1));
                fresh = false;
                return;
            }
            if (k.equals(".")) {
                if (!allowDecimal) return;
                if (fresh || t.length() == 0) { field.setText("0."); fresh = false; return; }
                if (t.indexOf('.') < 0) field.setText(t + ".");
                return;
            }
            if (fresh) { field.setText(k); fresh = false; return; }
            if (t.length() >= 12) return;
            field.setText(t.equals("0") ? k : t + k);
        }

        private void accept() {
            String t = field.getText().trim();
            if (t.length() == 0 || t.equals(".")) return;
            result = t;
            dispose();
        }
    }

    // =====================================================================
    //  Helpers
    // =====================================================================
    private JButton makeButton(String text, Color bg, int size) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.BOLD, size));
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    private JLabel boldLabel(String t) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("SansSerif", Font.BOLD, 13));
        return l;
    }

    private JLabel valueLabel(boolean big) {
        JLabel l = new JLabel("0.00", SwingConstants.RIGHT);
        l.setFont(new Font("SansSerif", Font.BOLD, big ? 20 : 14));
        if (big) l.setForeground(PRIMARY);
        return l;
    }

    private javax.swing.border.Border titled(String t) {
        return BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(207, 216, 220)), t);
    }

    private void styleTable(JTable t) {
        t.setRowHeight(28);
        t.setFont(new Font("SansSerif", Font.PLAIN, 13));
        t.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        t.getTableHeader().setBackground(DARK);
        t.getTableHeader().setForeground(Color.WHITE);
        t.setSelectionBackground(new Color(178, 223, 219));
        t.setSelectionForeground(Color.BLACK);
    }

    private void styleOrdersTable(JTable t) {
        t.setRowHeight(34);
        t.setFont(new Font("SansSerif", Font.PLAIN, 13));
        t.setForeground(DARK);
        t.setBackground(Color.WHITE);
        t.setFillsViewportHeight(true);
        t.setShowVerticalLines(false);
        t.setShowHorizontalLines(true);
        t.setGridColor(new Color(232, 237, 240));
        t.setIntercellSpacing(new Dimension(0, 1));
        t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        t.setSelectionBackground(new Color(224, 242, 241));
        t.setSelectionForeground(DARK);
        t.setRowMargin(1);

        JTableHeader header = t.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 12));
        header.setBackground(new Color(238, 243, 246));
        header.setForeground(new Color(55, 71, 79));
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 36));
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean selected, boolean focused, int row, int column) {
                JLabel cell = (JLabel) super.getTableCellRendererComponent(
                    table, value, selected, focused, row, column);
                if (selected) {
                    cell.setBackground(new Color(224, 242, 241));
                    cell.setForeground(DARK);
                } else {
                    cell.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 250, 252));
                    cell.setForeground(DARK);
                }
                cell.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                if (column == 3) cell.setHorizontalAlignment(SwingConstants.CENTER);
                else if (column == 4) cell.setHorizontalAlignment(SwingConstants.RIGHT);
                else cell.setHorizontalAlignment(SwingConstants.LEFT);
                return cell;
            }
        };
        t.setDefaultRenderer(Object.class, cellRenderer);
    }

    /** FlowLayout that wraps to new rows, so department buttons adapt to the screen width. */
    private static class WrapLayout extends FlowLayout {
        WrapLayout(int align, int hgap, int vgap) { super(align, hgap, vgap); }

        public Dimension preferredLayoutSize(Container target) { return layoutSize(target, true); }

        public Dimension minimumLayoutSize(Container target) {
            Dimension d = layoutSize(target, false);
            d.width -= (getHgap() + 1);
            return d;
        }

        private Dimension layoutSize(Container target, boolean preferred) {
            synchronized (target.getTreeLock()) {
                int targetWidth = target.getSize().width;
                if (targetWidth == 0) targetWidth = Integer.MAX_VALUE;
                int hgap = getHgap(), vgap = getVgap();
                Insets in = target.getInsets();
                int extra = in.left + in.right + (hgap * 2);
                int maxWidth = targetWidth - extra;
                Dimension dim = new Dimension(0, 0);
                int rowW = 0, rowH = 0;
                for (int i = 0; i < target.getComponentCount(); i++) {
                    Component c = target.getComponent(i);
                    if (!c.isVisible()) continue;
                    Dimension d = preferred ? c.getPreferredSize() : c.getMinimumSize();
                    if (rowW + d.width > maxWidth && rowW > 0) {
                        addRow(dim, rowW, rowH);
                        rowW = 0; rowH = 0;
                    }
                    if (rowW != 0) rowW += hgap;
                    rowW += d.width;
                    rowH = Math.max(rowH, d.height);
                }
                addRow(dim, rowW, rowH);
                dim.width += extra;
                dim.height += in.top + in.bottom + vgap * 2;
                return dim;
            }
        }

        private void addRow(Dimension dim, int rowW, int rowH) {
            dim.width = Math.max(dim.width, rowW);
            if (dim.height > 0) dim.height += getVgap();
            dim.height += rowH;
        }
    }

    // =====================================================================
    /** Stand-alone test only. In the real app the Sale button creates this screen. */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignore) { }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					new RestaurantPOS("", "mr_pro", "ADMIN", "S01", "S0101", "",
					                  "127.0.0.1", "", Dbsetup.server.getConnection(), "").setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
}
