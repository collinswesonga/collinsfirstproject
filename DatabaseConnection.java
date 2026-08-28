import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/login";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "2015";

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );

        } catch (ClassNotFoundException e) {

            System.out.println(
                    "MySQL JDBC Driver not found."
            );

            return null;

        } catch (SQLException e) {

            System.out.println(
                    "Database connection failed: "
                    + e.getMessage()
            );

            return null;
        }
    }
}