import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.PrinterException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CashierDashboard extends JFrame {

    private JComboBox<String> searchComboBox;
    private JTextField purchaseQuantityField;

    private JLabel itemNameLabel;
    private JLabel categoryLabel;
    private JLabel availableQuantityLabel;
    private JLabel weightLabel;
    private JLabel unitLabel;
    private JLabel sellingPriceLabel;
    private JLabel supplierLabel;

    private JTable cartTable;
    private DefaultTableModel cartModel;

    private JLabel totalLabel;

    private int selectedProductId = -1;
    private double selectedProductPrice = 0;

    private Timer searchTimer;


    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public CashierDashboard() {

        setTitle("POS System - Cashier Dashboard");

        setSize(1200, 750);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);


        // ==========================================
        // MAIN PANEL
        // ==========================================

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );


        // ==========================================
        // HEADER
        // ==========================================

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(
                new Color(35, 55, 85)
        );

        headerPanel.setBorder(
                new EmptyBorder(
                        15,
                        25,
                        15,
                        25
                )
        );


        JLabel titleLabel =
                new JLabel(
                        "CASHIER DASHBOARD"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        titleLabel.setForeground(
                Color.WHITE
        );


        JLabel cashierLabel =
                new JLabel("Cashier");

        cashierLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        cashierLabel.setForeground(
                Color.WHITE
        );


        headerPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        headerPanel.add(
                cashierLabel,
                BorderLayout.EAST
        );


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // ==========================================
        // SIDEBAR
        // ==========================================

        JPanel sidebarPanel =
                new JPanel();

        sidebarPanel.setPreferredSize(
                new Dimension(190, 0)
        );

        sidebarPanel.setBackground(
                new Color(30, 43, 60)
        );

        sidebarPanel.setLayout(
                new BoxLayout(
                        sidebarPanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel menuLabel =
                new JLabel("SALES MENU");

        menuLabel.setForeground(
                Color.LIGHT_GRAY
        );

        menuLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        menuLabel.setBorder(
                new EmptyBorder(
                        25,
                        20,
                        15,
                        0
                )
        );


        sidebarPanel.add(menuLabel);


        JButton dashboardButton =
                createMenuButton(
                        "Dashboard"
                );

        JButton newSaleButton =
                createMenuButton(
                        "New Sale"
                );

        JButton receiptsButton =
                createMenuButton(
                        "Receipts"
                );

        JButton logoutButton =
                createMenuButton(
                        "Logout"
                );


        sidebarPanel.add(
                dashboardButton
        );

        sidebarPanel.add(
                newSaleButton
        );

        sidebarPanel.add(
                receiptsButton
        );


        sidebarPanel.add(
                Box.createVerticalGlue()
        );

        sidebarPanel.add(
                logoutButton
        );

        sidebarPanel.add(
                Box.createVerticalStrut(20)
        );


        mainPanel.add(
                sidebarPanel,
                BorderLayout.WEST
        );


        // ==========================================
        // CENTER PANEL
        // ==========================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        centerPanel.setBackground(
                new Color(245, 247, 250)
        );

        centerPanel.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );


        // ==========================================
        // SEARCH PANEL
        // ==========================================

        JPanel searchPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        searchPanel.setBackground(
                Color.WHITE
        );

        searchPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        new EmptyBorder(
                                15,
                                20,
                                15,
                                20
                        )
                )
        );


        JLabel searchTitle =
                new JLabel(
                        "Search Product"
                );

        searchTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );


        // ==========================================
        // AUTOCOMPLETE COMBOBOX
        // ==========================================

        searchComboBox =
                new JComboBox<>();

        searchComboBox.setEditable(true);

        searchComboBox.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        searchComboBox.setPreferredSize(
                new Dimension(
                        500,
                        38
                )
        );


        JPanel searchInputPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        searchInputPanel.setBackground(
                Color.WHITE
        );


        JButton searchButton =
                new JButton("SEARCH");

        searchButton.setBackground(
                new Color(45, 120, 75)
        );

        searchButton.setForeground(
                Color.WHITE
        );

        searchButton.setFocusPainted(
                false
        );


        searchInputPanel.add(
                searchComboBox,
                BorderLayout.CENTER
        );

        searchInputPanel.add(
                searchButton,
                BorderLayout.EAST
        );


        searchPanel.add(
                searchTitle,
                BorderLayout.NORTH
        );

        searchPanel.add(
                searchInputPanel,
                BorderLayout.CENTER
        );


        centerPanel.add(
                searchPanel,
                BorderLayout.NORTH
        );


        // ==========================================
        // PRODUCT DETAILS
        // ==========================================

        JPanel productPanel =
                new JPanel(
                        new GridBagLayout()
                );

        productPanel.setBackground(
                Color.WHITE
        );

        productPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        new EmptyBorder(
                                15,
                                20,
                                15,
                                20
                        )
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        5,
                        8,
                        5,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;


        JLabel detailsTitle =
                new JLabel(
                        "Product Details"
                );

        detailsTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;


        productPanel.add(
                detailsTitle,
                gbc
        );


        itemNameLabel =
                new JLabel("-");

        categoryLabel =
                new JLabel("-");

        availableQuantityLabel =
                new JLabel("-");

        weightLabel =
                new JLabel("-");

        unitLabel =
                new JLabel("-");

        sellingPriceLabel =
                new JLabel("-");

        supplierLabel =
                new JLabel("-");


        addDetail(
                productPanel,
                gbc,
                "Item:",
                itemNameLabel,
                1,
                0
        );

        addDetail(
                productPanel,
                gbc,
                "Category:",
                categoryLabel,
                1,
                2
        );

        addDetail(
                productPanel,
                gbc,
                "Available:",
                availableQuantityLabel,
                2,
                0
        );

        addDetail(
                productPanel,
                gbc,
                "Weight:",
                weightLabel,
                2,
                2
        );

        addDetail(
                productPanel,
                gbc,
                "Unit:",
                unitLabel,
                3,
                0
        );

        addDetail(
                productPanel,
                gbc,
                "Selling Price:",
                sellingPriceLabel,
                3,
                2
        );

        addDetail(
                productPanel,
                gbc,
                "Supplier:",
                supplierLabel,
                4,
                0
        );


        // ==========================================
        // QUANTITY
        // ==========================================

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;


        productPanel.add(
                new JLabel(
                        "Purchase Quantity:"
                ),
                gbc
        );


        purchaseQuantityField =
                new JTextField("1");


        gbc.gridx = 3;


        productPanel.add(
                purchaseQuantityField,
                gbc
        );


        // ==========================================
        // ADD TO CART
        // ==========================================

        JButton addToCartButton =
                new JButton(
                        "ADD TO CART"
                );

        addToCartButton.setBackground(
                new Color(35, 105, 170)
        );

        addToCartButton.setForeground(
                Color.WHITE
        );

        addToCartButton.setFocusPainted(
                false
        );


        gbc.gridx = 3;
        gbc.gridy = 5;


        productPanel.add(
                addToCartButton,
                gbc
        );


        // ==========================================
        // CART TABLE
        // ==========================================

        String[] columns = {
                "Product ID",
                "Product",
                "Quantity",
                "Unit Price",
                "Total"
        };


        cartModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };


        cartTable =
                new JTable(cartModel);

        cartTable.setRowHeight(28);


        JScrollPane cartScrollPane =
                new JScrollPane(
                        cartTable
                );


        JPanel cartPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                10
                        )
                );

        cartPanel.setBackground(
                new Color(245, 247, 250)
        );


        JLabel cartTitle =
                new JLabel(
                        "Shopping Cart"
                );

        cartTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );


        cartPanel.add(
                cartTitle,
                BorderLayout.NORTH
        );

        cartPanel.add(
                cartScrollPane,
                BorderLayout.CENTER
        );


        // ==========================================
        // CART BOTTOM
        // ==========================================

        JPanel cartBottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        cartBottomPanel.setBackground(
                new Color(245, 247, 250)
        );


        totalLabel =
                new JLabel(
                        "TOTAL: KSh 0.00"
                );

        totalLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );


        cartBottomPanel.add(
                totalLabel,
                BorderLayout.WEST
        );


        JButton removeButton =
                new JButton(
                        "REMOVE SELECTED"
                );

        removeButton.setBackground(
                new Color(190, 55, 55)
        );

        removeButton.setForeground(
                Color.WHITE
        );

        removeButton.setFocusPainted(
                false
        );


        JButton clearCartButton =
                new JButton(
                        "CLEAR CART"
                );

        clearCartButton.setFocusPainted(
                false
        );


        JButton checkoutButton =
                new JButton(
                        "CHECKOUT"
                );

        checkoutButton.setBackground(
                new Color(45, 120, 75)
        );

        checkoutButton.setForeground(
                Color.WHITE
        );

        checkoutButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        checkoutButton.setFocusPainted(
                false
        );


        JPanel cartButtons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        cartButtons.setBackground(
                new Color(245, 247, 250)
        );


        cartButtons.add(
                removeButton
        );

        cartButtons.add(
                clearCartButton
        );

        cartButtons.add(
                checkoutButton
        );


        cartBottomPanel.add(
                cartButtons,
                BorderLayout.EAST
        );


        cartPanel.add(
                cartBottomPanel,
                BorderLayout.SOUTH
        );


        // ==========================================
        // COMBINE PRODUCT AND CART
        // ==========================================

        JPanel combinedPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                10
                        )
                );

        combinedPanel.setBackground(
                new Color(245, 247, 250)
        );


        combinedPanel.add(
                productPanel,
                BorderLayout.NORTH
        );

        combinedPanel.add(
                cartPanel,
                BorderLayout.CENTER
        );


        centerPanel.add(
                combinedPanel,
                BorderLayout.CENTER
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        add(mainPanel);


        // ==========================================
        // SEARCH BUTTON
        // ==========================================

        searchButton.addActionListener(
                e -> searchSelectedProduct()
        );


        // ==========================================
        // SEARCH COMBOBOX
        // ==========================================

        JTextField editor =
                (JTextField)
                        searchComboBox
                                .getEditor()
                                .getEditorComponent();


        editor.getDocument()
                .addDocumentListener(
                        new javax.swing.event.DocumentListener() {

                            @Override
                            public void insertUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {

                                updateSuggestions();
                            }


                            @Override
                            public void removeUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {

                                updateSuggestions();
                            }


                            @Override
                            public void changedUpdate(
                                    javax.swing.event.DocumentEvent e
                            ) {

                                updateSuggestions();
                            }
                        }
                );


        // ==========================================
        // SELECT PRODUCT FROM DROPDOWN
        // ==========================================

        searchComboBox.addActionListener(
                e -> {

                    if (
                            searchComboBox
                                    .getSelectedIndex()
                                    >= 0
                    ) {

                        searchSelectedProduct();
                    }
                }
        );


        // ==========================================
        // ADD TO CART
        // ==========================================

        addToCartButton.addActionListener(
                e -> addToCart()
        );


        // ==========================================
        // REMOVE
        // ==========================================

        removeButton.addActionListener(
                e -> removeSelectedItem()
        );


        // ==========================================
        // CLEAR CART
        // ==========================================

        clearCartButton.addActionListener(
                e -> clearCart()
        );


        // ==========================================
        // CHECKOUT
        // ==========================================

        checkoutButton.addActionListener(
                e -> checkout()
        );


        // ==========================================
        // LOGOUT
        // ==========================================

        logoutButton.addActionListener(
                e -> logout()
        );
    }


    // =================================================
    // AUTOCOMPLETE SUGGESTIONS
    // =================================================

    private void updateSuggestions() {

        if (searchTimer != null) {
            searchTimer.stop();
        }


        searchTimer =
                new Timer(
                        300,
                        e -> loadSuggestions()
                );


        searchTimer.setRepeats(false);

        searchTimer.start();
    }


    // =================================================
    // LOAD SUGGESTIONS FROM MYSQL
    // =================================================

    private void loadSuggestions() {

        JTextField editor =
                (JTextField)
                        searchComboBox
                                .getEditor()
                                .getEditorComponent();


        String text =
                editor.getText().trim();


        if (text.length() < 1) {
            return;
        }


        Connection connection =
                DatabaseConnection.getConnection();


        if (connection == null) {
            return;
        }


        String sql =
                "SELECT item_name "
                + "FROM productmanager "
                + "WHERE item_name LIKE ? "
                + "ORDER BY item_name "
                + "LIMIT 10";


        try (
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    "%" + text + "%"
            );


            ResultSet result =
                    statement.executeQuery();


            List<String> products =
                    new ArrayList<>();


            while (result.next()) {

                products.add(
                        result.getString(
                                "item_name"
                        )
                );
            }


            updateComboBox(
                    products,
                    text
            );


        } catch (SQLException e) {

            System.out.println(
                    "Suggestion error: "
                    + e.getMessage()
            );

        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // =================================================
    // UPDATE DROPDOWN
    // =================================================

    private void updateComboBox(
            List<String> products,
            String typedText
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    JTextField editor =
                            (JTextField)
                                    searchComboBox
                                            .getEditor()
                                            .getEditorComponent();


                    searchComboBox.removeAllItems();


                    for (
                            String product :
                            products
                    ) {

                        searchComboBox.addItem(
                                product
                        );
                    }


                    editor.setText(
                            typedText
                    );

                    editor.setCaretPosition(
                            typedText.length()
                    );


                    if (!products.isEmpty()) {

                        searchComboBox.setPopupVisible(
                                true
                        );
                    }
                }
        );
    }


    // =================================================
    // SEARCH SELECTED PRODUCT
    // =================================================

    private void searchSelectedProduct() {

        String searchText =
                searchComboBox
                        .getEditor()
                        .getItem()
                        .toString()
                        .trim();


        if (searchText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a product name."
            );

            return;
        }


        Connection connection =
                DatabaseConnection.getConnection();


        if (connection == null) {
            return;
        }


        String sql =
                "SELECT * "
                + "FROM productmanager "
                + "WHERE item_name = ? "
                + "LIMIT 1";


        try (
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    searchText
            );


            ResultSet result =
                    statement.executeQuery();


            if (result.next()) {

                selectedProductId =
                        result.getInt("id");


                selectedProductPrice =
                        result.getDouble(
                                "selling_price"
                        );


                itemNameLabel.setText(
                        result.getString(
                                "item_name"
                        )
                );


                categoryLabel.setText(
                        result.getString(
                                "category"
                        )
                );


                availableQuantityLabel.setText(
                        String.valueOf(
                                result.getInt(
                                        "quantity"
                                )
                        )
                );


                weightLabel.setText(
                        String.valueOf(
                                result.getDouble(
                                        "weight"
                                )
                        )
                );


                unitLabel.setText(
                        result.getString(
                                "unit"
                        )
                );


                sellingPriceLabel.setText(
                        String.format(
                                "KSh %.2f",
                                selectedProductPrice
                        )
                );


                supplierLabel.setText(
                        result.getString(
                                "supplier"
                        )
                );


                searchComboBox
                        .getEditor()
                        .setItem(
                                result.getString(
                                        "item_name"
                                )
                        );


            } else {

                clearProductDetails();

                JOptionPane.showMessageDialog(
                        this,
                        "Product not found."
                );
            }


        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // =================================================
    // ADD TO CART
    // =================================================

    private void addToCart() {

        if (selectedProductId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please search and select a product first."
            );

            return;
        }


        int purchaseQuantity;


        try {

            purchaseQuantity =
                    Integer.parseInt(
                            purchaseQuantityField
                                    .getText()
                                    .trim()
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid quantity."
            );

            return;
        }


        if (purchaseQuantity <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quantity must be greater than zero."
            );

            return;
        }


        int availableQuantity;


        try {

            availableQuantity =
                    Integer.parseInt(
                            availableQuantityLabel
                                    .getText()
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid available quantity."
            );

            return;
        }


        if (
                purchaseQuantity >
                availableQuantity
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Not enough stock available.\n"
                    + "Available: "
                    + availableQuantity
            );

            return;
        }


        double total =
                purchaseQuantity
                * selectedProductPrice;


        // Check if already in cart

        for (
                int i = 0;
                i < cartModel.getRowCount();
                i++
        ) {

            int productId =
                    Integer.parseInt(
                            cartModel
                                    .getValueAt(
                                            i,
                                            0
                                    )
                                    .toString()
                    );


            if (
                    productId ==
                    selectedProductId
            ) {

                int oldQuantity =
                        Integer.parseInt(
                                cartModel
                                        .getValueAt(
                                                i,
                                                2
                                        )
                                        .toString()
                        );


                int newQuantity =
                        oldQuantity
                        + purchaseQuantity;


                if (
                        newQuantity >
                        availableQuantity
                ) {

                    JOptionPane.showMessageDialog(
                            this,
                            "You cannot add more than "
                            + availableQuantity
                            + " units."
                    );

                    return;
                }


                double newTotal =
                        newQuantity
                        * selectedProductPrice;


                cartModel.setValueAt(
                        newQuantity,
                        i,
                        2
                );


                cartModel.setValueAt(
                        newTotal,
                        i,
                        4
                );


                updateTotal();

                return;
            }
        }


        // Add new product

        cartModel.addRow(
                new Object[]{
                        selectedProductId,
                        itemNameLabel.getText(),
                        purchaseQuantity,
                        selectedProductPrice,
                        total
                }
        );


        updateTotal();


        purchaseQuantityField.setText(
                "1"
        );
    }


    // =================================================
    // UPDATE TOTAL
    // =================================================

    private void updateTotal() {

        double total = 0;


        for (
                int i = 0;
                i < cartModel.getRowCount();
                i++
        ) {

            double itemTotal =
                    Double.parseDouble(
                            cartModel
                                    .getValueAt(
                                            i,
                                            4
                                    )
                                    .toString()
                    );


            total += itemTotal;
        }


        totalLabel.setText(
                String.format(
                        "TOTAL: KSh %.2f",
                        total
                )
        );
    }


    // =================================================
    // REMOVE ITEM
    // =================================================

    private void removeSelectedItem() {

        int selectedRow =
                cartTable.getSelectedRow();


        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an item."
            );

            return;
        }


        cartModel.removeRow(
                selectedRow
        );


        updateTotal();
    }


    // =================================================
    // CLEAR CART
    // =================================================

    private void clearCart() {

        cartModel.setRowCount(0);

        updateTotal();
    }


    // =================================================
    // CHECKOUT
    // =================================================

    private void checkout() {

        if (cartModel.getRowCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "The cart is empty.",
                    "Checkout",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        double total = 0;


        for (
                int i = 0;
                i < cartModel.getRowCount();
                i++
        ) {

            double itemTotal =
                    Double.parseDouble(
                            cartModel
                                    .getValueAt(
                                            i,
                                            4
                                    )
                                    .toString()
                    );

            total += itemTotal;
        }


        String paymentInput =
                JOptionPane.showInputDialog(
                        this,
                        String.format(
                                "Total amount: KSh %.2f\n\n"
                                + "Enter amount paid:",
                                total
                        ),
                        "Customer Payment",
                        JOptionPane.PLAIN_MESSAGE
                );


        if (paymentInput == null) {
            return;
        }


        double amountPaid;


        try {

            amountPaid =
                    Double.parseDouble(
                            paymentInput.trim()
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid amount.",
                    "Invalid Payment",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        if (amountPaid < total) {

            JOptionPane.showMessageDialog(
                    this,
                    String.format(
                            "Insufficient payment.\n\n"
                            + "Total: KSh %.2f\n"
                            + "Paid: KSh %.2f",
                            total,
                            amountPaid
                    ),
                    "Insufficient Payment",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        double change =
                amountPaid - total;


        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        String.format(
                                "TOTAL: KSh %.2f\n"
                                + "PAID: KSh %.2f\n"
                                + "CHANGE: KSh %.2f\n\n"
                                + "Complete this sale?",
                                total,
                                amountPaid,
                                change
                        ),
                        "Confirm Sale",
                        JOptionPane.YES_NO_OPTION
                );


        if (
                confirmation !=
                JOptionPane.YES_OPTION
        ) {

            return;
        }


        Connection connection =
                DatabaseConnection.getConnection();


        if (connection == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database connection failed.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        try {

            connection.setAutoCommit(
                    false
            );


            // =================================
            // INSERT SALE
            // =================================

            String saleSQL =
                    "INSERT INTO sales "
                    + "(cashier, total_amount, "
                    + "amount_paid, change_amount) "
                    + "VALUES (?, ?, ?, ?)";


            PreparedStatement saleStatement =
                    connection.prepareStatement(
                            saleSQL,
                            Statement.RETURN_GENERATED_KEYS
                    );


            saleStatement.setString(
                    1,
                    "Cashier"
            );


            saleStatement.setDouble(
                    2,
                    total
            );


            saleStatement.setDouble(
                    3,
                    amountPaid
            );


            saleStatement.setDouble(
                    4,
                    change
            );


            saleStatement.executeUpdate();


            ResultSet generatedKeys =
                    saleStatement.getGeneratedKeys();


            if (!generatedKeys.next()) {

                throw new SQLException(
                        "Could not obtain sale ID."
                );
            }


            int saleId =
                    generatedKeys.getInt(1);


            // =================================
            // INSERT SALE ITEMS
            // =================================

            String itemSQL =
                    "INSERT INTO sale_items "
                    + "(sale_id, product_id, quantity, "
                    + "unit_price, total_price) "
                    + "VALUES (?, ?, ?, ?, ?)";


            PreparedStatement itemStatement =
                    connection.prepareStatement(
                            itemSQL
                    );


            // =================================
            // UPDATE STOCK
            // =================================

            String stockSQL =
                    "UPDATE productmanager "
                    + "SET quantity = quantity - ? "
                    + "WHERE id = ? "
                    + "AND quantity >= ?";


            PreparedStatement stockStatement =
                    connection.prepareStatement(
                            stockSQL
                    );


            for (
                    int i = 0;
                    i < cartModel.getRowCount();
                    i++
            ) {

                int productId =
                        Integer.parseInt(
                                cartModel
                                        .getValueAt(
                                                i,
                                                0
                                        )
                                        .toString()
                        );


                int quantity =
                        Integer.parseInt(
                                cartModel
                                        .getValueAt(
                                                i,
                                                2
                                        )
                                        .toString()
                        );


                double unitPrice =
                        Double.parseDouble(
                                cartModel
                                        .getValueAt(
                                                i,
                                                3
                                        )
                                        .toString()
                        );


                double itemTotal =
                        Double.parseDouble(
                                cartModel
                                        .getValueAt(
                                                i,
                                                4
                                        )
                                        .toString()
                        );


                // Insert sale item

                itemStatement.setInt(
                        1,
                        saleId
                );

                itemStatement.setInt(
                        2,
                        productId
                );

                itemStatement.setInt(
                        3,
                        quantity
                );

                itemStatement.setDouble(
                        4,
                        unitPrice
                );

                itemStatement.setDouble(
                        5,
                        itemTotal
                );


                itemStatement.executeUpdate();


                // Reduce stock

                stockStatement.setInt(
                        1,
                        quantity
                );

                stockStatement.setInt(
                        2,
                        productId
                );

                stockStatement.setInt(
                        3,
                        quantity
                );


                int updatedRows =
                        stockStatement.executeUpdate();


                if (updatedRows == 0) {

                    throw new SQLException(
                            "Insufficient stock for product ID: "
                            + productId
                    );
                }
            }


            // =================================
            // COMMIT
            // =================================

            connection.commit();


            // =================================
            // SHOW RECEIPT
            // =================================

            showReceipt(
                    saleId,
                    total,
                    amountPaid,
                    change
            );


            // Clear cart

            cartModel.setRowCount(0);

            updateTotal();

            clearProductDetails();


        } catch (SQLException e) {


            try {

                connection.rollback();

            } catch (SQLException rollbackError) {

                rollbackError.printStackTrace();
            }


            JOptionPane.showMessageDialog(
                    this,
                    "Sale could not be completed.\n\n"
                    + e.getMessage(),
                    "Sale Error",
                    JOptionPane.ERROR_MESSAGE
            );


        } finally {

            try {

                connection.setAutoCommit(
                        true
                );

            } catch (SQLException e) {

                e.printStackTrace();
            }


            try {

                connection.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }


    // =================================================
    // RECEIPT
    // =================================================

    private void showReceipt(
            int saleId,
            double total,
            double amountPaid,
            double change
    ) {

        StringBuilder receipt =
                new StringBuilder();


        receipt.append(
                "================================\n"
        );

        receipt.append(
                "          MY POS SYSTEM\n"
        );

        receipt.append(
                "         SALES RECEIPT\n"
        );

        receipt.append(
                "================================\n"
        );

        receipt.append(
                "Sale ID: "
                + saleId
                + "\n"
        );

        receipt.append(
                "Cashier: Cashier\n"
        );

        receipt.append(
                "--------------------------------\n"
        );

        receipt.append(
                String.format(
                        "%-20s %5s %10s\n",
                        "Product",
                        "Qty",
                        "Total"
                )
        );

        receipt.append(
                "--------------------------------\n"
        );


        for (
                int i = 0;
                i < cartModel.getRowCount();
                i++
        ) {

            String productName =
                    cartModel
                            .getValueAt(
                                    i,
                                    1
                            )
                            .toString();


            int quantity =
                    Integer.parseInt(
                            cartModel
                                    .getValueAt(
                                            i,
                                            2
                                    )
                                    .toString()
                    );


            double itemTotal =
                    Double.parseDouble(
                            cartModel
                                    .getValueAt(
                                            i,
                                            4
                                    )
                                    .toString()
                    );


            receipt.append(
                    String.format(
                            "%-20s %5d %10.2f\n",
                            productName,
                            quantity,
                            itemTotal
                    )
            );
        }


        receipt.append(
                "--------------------------------\n"
        );


        receipt.append(
                String.format(
                        "TOTAL:        KSh %.2f\n",
                        total
                )
        );


        receipt.append(
                String.format(
                        "PAID:         KSh %.2f\n",
                        amountPaid
                )
        );


        receipt.append(
                String.format(
                        "CHANGE:       KSh %.2f\n",
                        change
                )
        );


        receipt.append(
                "================================\n"
        );


        receipt.append(
                "      THANK YOU FOR SHOPPING!\n"
        );


        receipt.append(
                "================================\n"
        );


        JTextArea receiptArea =
                new JTextArea(
                        receipt.toString()
                );


        receiptArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );


        receiptArea.setEditable(
                false
        );


        receiptArea.setMargin(
                new Insets(
                        15,
                        15,
                        15,
                        15
                )
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        receiptArea
                );


        scrollPane.setPreferredSize(
                new Dimension(
                        500,
                        500
                )
        );


        JDialog receiptDialog =
                new JDialog(
                        this,
                        "Sales Receipt",
                        true
                );


        receiptDialog.setLayout(
                new BorderLayout(
                        10,
                        10
                )
        );


        receiptDialog.add(
                scrollPane,
                BorderLayout.CENTER
        );


        JButton printButton =
                new JButton(
                        "PRINT RECEIPT"
                );


        JButton closeButton =
                new JButton(
                        "CLOSE"
                );


        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );


        buttonPanel.add(
                printButton
        );

        buttonPanel.add(
                closeButton
        );


        receiptDialog.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        printButton.addActionListener(
                e -> {

                    try {

                        boolean printed =
                                receiptArea.print();


                        if (printed) {

                            JOptionPane.showMessageDialog(
                                    receiptDialog,
                                    "Receipt sent to printer.",
                                    "Print",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }

                    } catch (
                            PrinterException ex
                    ) {

                        JOptionPane.showMessageDialog(
                                receiptDialog,
                                "Unable to print receipt.\n\n"
                                + ex.getMessage(),
                                "Print Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
        );


        closeButton.addActionListener(
                e ->
                        receiptDialog.dispose()
        );


        receiptDialog.setSize(
                550,
                600
        );


        receiptDialog.setLocationRelativeTo(
                this
        );


        receiptDialog.setVisible(
                true
        );
    }


    // =================================================
    // CLEAR PRODUCT DETAILS
    // =================================================

    private void clearProductDetails() {

        selectedProductId = -1;

        selectedProductPrice = 0;


        itemNameLabel.setText("-");

        categoryLabel.setText("-");

        availableQuantityLabel.setText("-");

        weightLabel.setText("-");

        unitLabel.setText("-");

        sellingPriceLabel.setText("-");

        supplierLabel.setText("-");


        searchComboBox.setSelectedItem(
                ""
        );


        purchaseQuantityField.setText(
                "1"
        );
    }


    // =================================================
    // PRODUCT DETAIL HELPER
    // =================================================

    private void addDetail(
            JPanel panel,
            GridBagConstraints gbc,
            String title,
            JLabel value,
            int row,
            int column
    ) {

        gbc.gridwidth = 1;

        gbc.gridx = column;

        gbc.gridy = row;


        JLabel titleLabel =
                new JLabel(title);


        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );


        panel.add(
                titleLabel,
                gbc
        );


        gbc.gridx =
                column + 1;


        panel.add(
                value,
                gbc
        );
    }


    // =================================================
    // MENU BUTTON
    // =================================================

    private JButton createMenuButton(
            String text
    ) {

        JButton button =
                new JButton(text);


        button.setMaximumSize(
                new Dimension(
                        190,
                        45
                )
        );


        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );


        button.setBorder(
                new EmptyBorder(
                        10,
                        20,
                        10,
                        10
                )
        );


        button.setBackground(
                new Color(
                        30,
                        43,
                        60
                )
        );


        button.setForeground(
                Color.WHITE
        );


        button.setFocusPainted(
                false
        );


        button.setBorderPainted(
                false
        );


        return button;
    }


    // =================================================
    // LOGOUT
    // =================================================

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );


        if (
                choice ==
                JOptionPane.YES_OPTION
        ) {

            dispose();

            new LoginPage().setVisible(
                    true
            );
        }
    }
}