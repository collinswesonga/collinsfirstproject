import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class AdminDashboard extends JFrame {

    // Input fields
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;

    // Users table
    private JTable usersTable;
    private DefaultTableModel tableModel;

    public AdminDashboard() {

        // =========================
        // WINDOW SETTINGS
        // =========================

        setTitle("POS System - Admin Dashboard");
        setSize(1000, 650);
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

        JLabel titleLabel = new JLabel("ADMIN DASHBOARD");

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        titleLabel.setForeground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Administrator");

        welcomeLabel.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        welcomeLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(welcomeLabel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);


        // =========================
        // LEFT SIDEBAR
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

        JLabel menuLabel = new JLabel("MENU");

        menuLabel.setForeground(Color.LIGHT_GRAY);
        menuLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        menuLabel.setBorder(
                new EmptyBorder(25, 20, 15, 0)
        );

        sidebarPanel.add(menuLabel);


        JButton dashboardButton =
                createMenuButton("Dashboard");

        JButton usersButton =
                createMenuButton("Manage Users");

        JButton reportsButton =
                createMenuButton("Reports");

        JButton logoutButton =
                createMenuButton("Logout");


        sidebarPanel.add(dashboardButton);
        sidebarPanel.add(usersButton);
        sidebarPanel.add(reportsButton);

        sidebarPanel.add(Box.createVerticalGlue());

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

        JPanel centerPanel = new JPanel(
                new BorderLayout(15, 15)
        );

        centerPanel.setBackground(
                new Color(245, 247, 250)
        );

        centerPanel.setBorder(
                new EmptyBorder(20, 20, 20, 20)
        );


        // =========================
        // ADD USER PANEL
        // =========================

        JPanel addUserPanel = new JPanel();

        addUserPanel.setLayout(
                new GridBagLayout()
        );

        addUserPanel.setBackground(Color.WHITE);

        addUserPanel.setBorder(
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


        JLabel addUserTitle =
                new JLabel("Add New User");

        addUserTitle.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        addUserPanel.add(
                addUserTitle,
                gbc
        );


        // Username

        JLabel usernameLabel =
                new JLabel("Username:");

        usernameField =
                new JTextField(20);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;

        addUserPanel.add(
                usernameLabel,
                gbc
        );

        gbc.gridx = 1;

        addUserPanel.add(
                usernameField,
                gbc
        );


        // Password

        JLabel passwordLabel =
                new JLabel("Password:");

        passwordField =
                new JPasswordField(20);

        gbc.gridx = 0;
        gbc.gridy = 2;

        addUserPanel.add(
                passwordLabel,
                gbc
        );

        gbc.gridx = 1;

        addUserPanel.add(
                passwordField,
                gbc
        );


        // Role

        JLabel roleLabel =
                new JLabel("Role:");

        roleComboBox =
                new JComboBox<>(
                        new String[]{
                                "Admin",
                                "Cashier",
                                "Stockist"
                        }
                );

        gbc.gridx = 0;
        gbc.gridy = 3;

        addUserPanel.add(
                roleLabel,
                gbc
        );

        gbc.gridx = 1;

        addUserPanel.add(
                roleComboBox,
                gbc
        );


        // Add button

        JButton addUserButton =
                new JButton("ADD USER");

        addUserButton.setBackground(
                new Color(45, 120, 75)
        );

        addUserButton.setForeground(
                Color.WHITE
        );

        addUserButton.setFocusPainted(false);


        gbc.gridx = 1;
        gbc.gridy = 4;

        addUserPanel.add(
                addUserButton,
                gbc
        );


        centerPanel.add(
                addUserPanel,
                BorderLayout.NORTH
        );


        // =========================
        // USERS TABLE
        // =========================

        String[] columns = {
                "ID",
                "Username",
                "Role"
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


        usersTable =
                new JTable(tableModel);

        usersTable.setRowHeight(30);

        usersTable.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        usersTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );


        JScrollPane scrollPane =
                new JScrollPane(usersTable);


        JPanel tablePanel =
                new JPanel(
                        new BorderLayout(0, 10)
                );

        tablePanel.setBackground(
                new Color(245, 247, 250)
        );


        JLabel usersTitle =
                new JLabel("System Users");

        usersTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );


        tablePanel.add(
                usersTitle,
                BorderLayout.NORTH
        );

        tablePanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        // Delete button

        JButton deleteButton =
                new JButton("DELETE SELECTED USER");

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

        addUserButton.addActionListener(
                e -> addUser()
        );


        deleteButton.addActionListener(
                e -> deleteUser()
        );


        logoutButton.addActionListener(
                e -> logout()
        );


        // =========================
        // LOAD USERS
        // =========================

        loadUsers();


        // Add everything to frame
        add(mainPanel);
    }


    // =========================
    // CREATE SIDEBAR BUTTON
    // =========================

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
                new Color(30, 43, 60)
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        return button;
    }


    // =========================
    // ADD USER
    // =========================

    private void addUser() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        String role =
                roleComboBox
                        .getSelectedItem()
                        .toString();


        // Check empty fields

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
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
        // SQL INSERT
        // =========================

        String sql =
                "INSERT INTO users "
                + "(username, password, role) "
                + "VALUES (?, ?, ?)";


        try (
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    username
            );

            statement.setString(
                    2,
                    password
            );

            statement.setString(
                    3,
                    role
            );


            statement.executeUpdate();


            JOptionPane.showMessageDialog(
                    this,
                    "User added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );


            // Clear fields

            usernameField.setText("");

            passwordField.setText("");

            roleComboBox.setSelectedIndex(0);


            // Refresh table

            loadUsers();


        } catch (SQLException e) {

            if (
                    e.getMessage()
                            .toLowerCase()
                            .contains("duplicate")
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "That username already exists.",
                        "Username Error",
                        JOptionPane.ERROR_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add user.\n\n"
                        + e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } finally {

            try {

                connection.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }


    // =========================
    // LOAD USERS
    // =========================

    private void loadUsers() {

        tableModel.setRowCount(0);


        Connection connection =
                DatabaseConnection.getConnection();


        if (connection == null) {

            return;
        }


        String sql =
                "SELECT id, username, role "
                + "FROM users "
                + "ORDER BY id";


        try (
                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet result =
                        statement.executeQuery()
        ) {

            while (result.next()) {

                int id =
                        result.getInt("id");

                String username =
                        result.getString("username");

                String role =
                        result.getString("role");


                tableModel.addRow(
                        new Object[]{
                                id,
                                username,
                                role
                        }
                );
            }


        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to load users.\n\n"
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
    // DELETE USER
    // =========================

    private void deleteUser() {

        int selectedRow =
                usersTable.getSelectedRow();


        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a user first.",
                    "No User Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int userId =
                (int) tableModel.getValueAt(
                        selectedRow,
                        0
                );


        String username =
                tableModel.getValueAt(
                        selectedRow,
                        1
                ).toString();


        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete user: "
                                + username
                                + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );


        if (
                confirmation
                        != JOptionPane.YES_OPTION
        ) {

            return;
        }


        Connection connection =
                DatabaseConnection.getConnection();


        if (connection == null) {

            return;
        }


        String sql =
                "DELETE FROM users WHERE id = ?";


        try (
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    userId
            );

            statement.executeUpdate();


            JOptionPane.showMessageDialog(
                    this,
                    "User deleted successfully!"
            );


            loadUsers();


        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete user.\n\n"
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
                choice == JOptionPane.YES_OPTION
        ) {

            dispose();

            new LoginPage().setVisible(true);
        }
    }
}