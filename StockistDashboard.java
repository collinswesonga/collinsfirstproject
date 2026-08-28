import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class StockistDashboard extends JFrame {

    private JTextField itemNameField;
    private JTextField categoryField;
    private JTextField quantityField;
    private JTextField weightField;
    private JComboBox<String> unitComboBox;
    private JTextField buyingPriceField;
    private JTextField sellingPriceField;
    private JTextField supplierField;
    private JTextField reorderLevelField;

    private JTable productTable;
    private DefaultTableModel tableModel;

    public StockistDashboard() {

        setTitle("POS System - Stockist Dashboard");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));


        // =========================
        // HEADER
        // =========================

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(35, 55, 85));
        headerPanel.setBorder(
                new EmptyBorder(15, 25, 15, 25)
        );

        JLabel titleLabel =
                new JLabel("STOCKIST DASHBOARD");

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        titleLabel.setForeground(Color.WHITE);

        JLabel userLabel =
                new JLabel("Stockist");

        userLabel.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        userLabel.setForeground(Color.WHITE);

        headerPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        headerPanel.add(
                userLabel,
                BorderLayout.EAST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =========================
        // SIDEBAR
        // =========================

        JPanel sidebarPanel = new JPanel();

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
                new JLabel("STOCK MANAGEMENT");

        menuLabel.setForeground(Color.LIGHT_GRAY);

        menuLabel.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        menuLabel.setBorder(
                new EmptyBorder(25, 20, 15, 0)
        );

        sidebarPanel.add(menuLabel);

        JButton dashboardButton =
                createMenuButton("Dashboard");

        JButton productsButton =
                createMenuButton("Products");

        JButton stockButton =
                createMenuButton("Stock");

        JButton logoutButton =
                createMenuButton("Logout");

        sidebarPanel.add(dashboardButton);
        sidebarPanel.add(productsButton);
        sidebarPanel.add(stockButton);

        sidebarPanel.add(
                Box.createVerticalGlue()
        );

        sidebarPanel.add(logoutButton);

        sidebarPanel.add(
                Box.createVerticalStrut(20)
        );

        mainPanel.add(
                sidebarPanel,
                BorderLayout.WEST
        );


        // =========================
        // CENTER PANEL
        // =========================

        JPanel centerPanel =
                new JPanel(new BorderLayout(15, 15));

        centerPanel.setBackground(
                new Color(245, 247, 250)
        );

        centerPanel.setBorder(
                new EmptyBorder(20, 20, 20, 20)
        );


        // =========================
        // ADD PRODUCT FORM
        // =========================

        JPanel formPanel =
                new JPanel(new GridBagLayout());

        formPanel.setBackground(Color.WHITE);

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        new EmptyBorder(
                                15, 20, 15, 20
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(5, 8, 5, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;


        JLabel formTitle =
                new JLabel("Add New Product");

        formTitle.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;

        formPanel.add(formTitle, gbc);


        // =========================
        // ITEM NAME
        // =========================

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("Item Name:"),
                gbc
        );

        itemNameField =
                new JTextField();

        gbc.gridx = 1;
        gbc.gridwidth = 3;

        formPanel.add(
                itemNameField,
                gbc
        );


        // =========================
        // CATEGORY
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        formPanel.add(
                new JLabel("Category:"),
                gbc
        );

        categoryField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                categoryField,
                gbc
        );


        // =========================
        // QUANTITY
        // =========================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Quantity:"),
                gbc
        );

        quantityField =
                new JTextField();

        gbc.gridx = 3;

        formPanel.add(
                quantityField,
                gbc
        );


        // =========================
        // WEIGHT
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 3;

        formPanel.add(
                new JLabel("Weight:"),
                gbc
        );

        weightField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                weightField,
                gbc
        );


        // =========================
        // UNIT
        // =========================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Unit:"),
                gbc
        );

        unitComboBox =
                new JComboBox<>(
                        new String[]{
                                "kg",
                                "g",
                                "litre",
                                "ml",
                                "piece",
                                "box",
                                "pack"
                        }
                );

        gbc.gridx = 3;

        formPanel.add(
                unitComboBox,
                gbc
        );


        // =========================
        // BUYING PRICE
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 4;

        formPanel.add(
                new JLabel("Buying Price:"),
                gbc
        );

        buyingPriceField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                buyingPriceField,
                gbc
        );


        // =========================
        // SELLING PRICE
        // =========================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Selling Price:"),
                gbc
        );

        sellingPriceField =
                new JTextField();

        gbc.gridx = 3;

        formPanel.add(
                sellingPriceField,
                gbc
        );


        // =========================
        // SUPPLIER
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 5;

        formPanel.add(
                new JLabel("Supplier:"),
                gbc
        );

        supplierField =
                new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                supplierField,
                gbc
        );


        // =========================
        // REORDER LEVEL
        // =========================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Reorder Level:"),
                gbc
        );

        reorderLevelField =
                new JTextField("10");

        gbc.gridx = 3;

        formPanel.add(
                reorderLevelField,
                gbc
        );


        // =========================
        // ADD BUTTON
        // =========================

        JButton addProductButton =
                new JButton("ADD PRODUCT");

        addProductButton.setBackground(
                new Color(45, 120, 75)
        );

        addProductButton.setForeground(
                Color.WHITE
        );

        addProductButton.setFocusPainted(false);

        gbc.gridx = 3;
        gbc.gridy = 6;

        formPanel.add(
                addProductButton,
                gbc
        );


        centerPanel.add(
                formPanel,
                BorderLayout.NORTH
        );


        // =========================
        // PRODUCT TABLE
        // =========================

        String[] columns = {
                "ID",
                "Item Name",
                "Category",
                "Quantity",
                "Weight",
                "Unit",
                "Buying Price",
                "Selling Price",
                "Supplier",
                "Reorder Level"
        };

        tableModel =
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

        productTable =
                new JTable(tableModel);

        productTable.setRowHeight(28);

        JScrollPane scrollPane =
                new JScrollPane(productTable);


        JPanel tablePanel =
                new JPanel(new BorderLayout(0, 10));

        tablePanel.setBackground(
                new Color(245, 247, 250)
        );

        JLabel productsTitle =
                new JLabel("Products / Stock");

        productsTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        tablePanel.add(
                productsTitle,
                BorderLayout.NORTH
        );

        tablePanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =========================
        // DELETE BUTTON
        // =========================

        JButton deleteButton =
                new JButton("DELETE SELECTED PRODUCT");

        deleteButton.setBackground(
                new Color(190, 55, 55)
        );

        deleteButton.setForeground(
                Color.WHITE
        );

        deleteButton.setFocusPainted(false);

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        bottomPanel.setBackground(
                new Color(245, 247, 250)
        );

        bottomPanel.add(deleteButton);

        tablePanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        centerPanel.add(
                tablePanel,
                BorderLayout.CENTER
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // =========================
        // BUTTON ACTIONS
        // =========================

        addProductButton.addActionListener(
                e -> addProduct()
        );

        deleteButton.addActionListener(
                e -> deleteProduct()
        );

        logoutButton.addActionListener(
                e -> logout()
        );


        // =========================
        // LOAD PRODUCTS
        // =========================

        loadProducts();


        add(mainPanel);
    }


    // =========================
    // SIDEBAR BUTTON
    // =========================

    private JButton createMenuButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(190, 45)
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setBorder(
                new EmptyBorder(
                        10, 20, 10, 10
                )
        );

        button.setBackground(
                new Color(30, 43, 60)
        );

        button.setForeground(Color.WHITE);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        return button;
    }


    // =========================
    // ADD PRODUCT TO DATABASE
    // =========================

    private void addProduct() {

        String itemName =
                itemNameField.getText().trim();

        String category =
                categoryField.getText().trim();

        String quantityText =
                quantityField.getText().trim();

        String weightText =
                weightField.getText().trim();

        String unit =
                unitComboBox
                        .getSelectedItem()
                        .toString();

        String buyingPriceText =
                buyingPriceField
                        .getText()
                        .trim();

        String sellingPriceText =
                sellingPriceField
                        .getText()
                        .trim();

        String supplier =
                supplierField.getText().trim();

        String reorderLevelText =
                reorderLevelField
                        .getText()
                        .trim();


        // =========================
        // VALIDATION
        // =========================

        if (itemName.isEmpty()
                || quantityText.isEmpty()
                || weightText.isEmpty()
                || buyingPriceText.isEmpty()
                || sellingPriceText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all required fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int quantity;
        double weight;
        double buyingPrice;
        double sellingPrice;
        int reorderLevel;


        try {

            quantity =
                    Integer.parseInt(quantityText);

            weight =
                    Double.parseDouble(weightText);

            buyingPrice =
                    Double.parseDouble(
                            buyingPriceText
                    );

            sellingPrice =
                    Double.parseDouble(
                            sellingPriceText
                    );

            reorderLevel =
                    Integer.parseInt(
                            reorderLevelText
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quantity, weight, prices and reorder level must be numbers.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // =========================
        // DATABASE CONNECTION
        // =========================

        Connection connection =
                DatabaseConnection.getConnection();

        if (connection == null) {
            return;
        }


        // =========================
        // INSERT PRODUCT
        // =========================

        String sql =
                "INSERT INTO productmanager "
                + "(item_name, category, quantity, weight, "
                + "unit, buying_price, selling_price, "
                + "supplier, reorder_level) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, itemName);
            statement.setString(2, category);
            statement.setInt(3, quantity);
            statement.setDouble(4, weight);
            statement.setString(5, unit);
            statement.setDouble(6, buyingPrice);
            statement.setDouble(7, sellingPrice);
            statement.setString(8, supplier);
            statement.setInt(9, reorderLevel);

            statement.executeUpdate();


            JOptionPane.showMessageDialog(
                    this,
                    "Product added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );


            clearFields();

            loadProducts();


        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to add product.\n\n"
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


    // =========================
    // LOAD PRODUCTS
    // =========================

    private void loadProducts() {

        tableModel.setRowCount(0);

        Connection connection =
                DatabaseConnection.getConnection();

        if (connection == null) {
            return;
        }


        String sql =
                "SELECT * FROM productmanager "
                + "ORDER BY id DESC";


        try (
                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            while (result.next()) {

                tableModel.addRow(
                        new Object[]{
                                result.getInt("id"),
                                result.getString("item_name"),
                                result.getString("category"),
                                result.getInt("quantity"),
                                result.getDouble("weight"),
                                result.getString("unit"),
                                result.getDouble("buying_price"),
                                result.getDouble("selling_price"),
                                result.getString("supplier"),
                                result.getInt("reorder_level")
                        }
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to load products.\n\n"
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


    // =========================
    // DELETE PRODUCT
    // =========================

    private void deleteProduct() {

        int selectedRow =
                productTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a product first.",
                    "No Product Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int productId =
                (int) tableModel.getValueAt(
                        selectedRow,
                        0
                );

        String itemName =
                tableModel.getValueAt(
                        selectedRow,
                        1
                ).toString();


        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete " + itemName + "?",
                        "Confirm Delete",
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
            return;
        }


        String sql =
                "DELETE FROM productmanager "
                + "WHERE id = ?";


        try (
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    productId
            );

            statement.executeUpdate();


            JOptionPane.showMessageDialog(
                    this,
                    "Product deleted successfully!"
            );


            loadProducts();


        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete product.\n\n"
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


    // =========================
    // CLEAR FORM
    // =========================

    private void clearFields() {

        itemNameField.setText("");
        categoryField.setText("");
        quantityField.setText("");
        weightField.setText("");
        buyingPriceField.setText("");
        sellingPriceField.setText("");
        supplierField.setText("");
        reorderLevelField.setText("10");

        unitComboBox.setSelectedIndex(0);
    }


    // =========================
    // LOGOUT
    // =========================

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

            new LoginPage().setVisible(true);
        }
    }
}