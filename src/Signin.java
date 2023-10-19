import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Signin {
    boolean signin(String username, String password) {
        boolean Result = false;
        try (Scanner scanner = new Scanner(System.in)) {

            // Establish a connection to the database
            try (
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginDetails",
                            "root", "mysqlrootpassword");) {
                // Prepare a SQL query to check the username and password
                String query = "SELECT * FROM Login WHERE username =? AND password =?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            Result = true;
                            // User authentication successful
                            System.out.println("Login successful! Welcome, " + username);
                        } else {

                            Result = false;
                            // User authentication failed
                            System.out.println("Login failed. Please check your username and password.");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Result;
    }
}
