import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class members_name_Fetch {
    public List<String> getListOfStrings(String Username) {
        List<String> stringList = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {

            // Establish a connection to the database
            try (
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginDetails",
                            "root", "mysqlrootpassword");) {
                // Prepare a SQL query to check the username and password
                String query = "SELECT * FROM Members WHERE username =?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, Username);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {

                            while (resultSet.next()) {
                                String value = resultSet.getString("member_name");
                                stringList.add(value);
                            }
                        }
                        // User authentication successful
                        else {
                            stringList.add("No members yet");
                            System.out.println("No members");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stringList;
    }
}
