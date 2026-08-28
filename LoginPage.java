import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {

        // =========================
        // WINDOW SETTINGS
        // =========================

        setTitle("POS System - Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));


        // =========================
        // TITLE
        // =========================

        JLabel title = new JLabel(
                "POS SYSTEM",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        title.setForeground(
                new Color(40, 40, 40)
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 0, 20, 0
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );


        // =========================
        // FORM PANEL
        // =========================

        JPanel formPanel = new JPanel();

        formPanel.setLayout(
                new GridLayout(5, 1, 10, 10)
        );

        formPanel.setBackground(Color.WHITE);

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 50, 30, 50
                )
        );


        // =========================
        // USERNAME
        // =========================

        JLabel usernameLabel = new JLabel(
                "Username"
        );

        usernameLabel.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        usernameField = new JTextField();

        usernameField.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );


        // =========================
        // PASSWORD
        // =========================

        JLabel passwordLabel = new JLabel(
                "Password"
        );

        passwordLabel.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        passwordField = new JPasswordField();

        passwordField.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );


        // =========================
        // LOGIN BUTTON
        // =========================

        JButton loginButton = new JButton(
                "LOGIN"
        );

        loginButton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        loginButton.setBackground(
                new Color(45, 100, 200)
        );

        loginButton.setForeground(
                Color.WHITE
        );

        loginButton.setFocusPainted(false);


        // =========================
        // ADD COMPONENTS
        // =========================

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(loginButton);

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);


        // =========================
        // LOGIN BUTTON ACTION
        // =========================

        loginButton.addActionListener(e -> loginUser());
    }


    // =========================
    // LOGIN METHOD
    // =========================

    private void loginUser() {

        String username = usernameField.getText().trim();

        String password = new String(
                passwordField.getPassword()
        );


        // Check empty fields
        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =========================
        // DATABASE CONNECTION
        // =========================

        Connection connection =
                DatabaseConnection.getConnection();


        // If connection failed
        if (connection == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to connect to the database.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // =========================
        // SQL QUERY
        // =========================

        String sql =
                "SELECT username, role FROM users "
                + "WHERE username = ? AND password = ?";


        try (
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            // Put username into first ?
            statement.setString(1, username);

            // Put password into second ?
            statement.setString(2, password);


            // Execute query
            ResultSet result =
                    statement.executeQuery();


            // =========================
            // USER FOUND
            // =========================

            if (result.next()) {

                String role =
                        result.getString("role");


                JOptionPane.showMessageDialog(
                        this,
                        "Login successful!\nRole: " + role,
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE
                );


                // Close login window
                dispose();


                // =========================
                // CHECK ROLE
                // =========================

                if (role.equalsIgnoreCase("Admin")) {

                    new AdminDashboard().setVisible(true);

                }

                else if (role.equalsIgnoreCase("Cashier")) {

                    new CashierDashboard().setVisible(true);

                }

                else if (role.equalsIgnoreCase("Stockist")) {

                    new StockistDashboard().setVisible(true);

                }

                else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Unknown user role: " + role,
                            "Role Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }


            }

            // =========================
            // USER NOT FOUND
            // =========================

            else {

                JOptionPane.showMessageDialog(
                        this,
                        "Incorrect username or password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }


        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } finally {

            // Close database connection
            try {

                connection.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }
}
