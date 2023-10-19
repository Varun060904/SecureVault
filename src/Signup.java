import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Signup {
    static boolean signup(String userName,String passWord, String name , String email , String phone_number ){
        boolean result = false;
        try{
            
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginDetails", "root", "mysqlrootpassword");
        System.out.println("Connected");
        String query = "INSERT INTO `LoginDetails`.`Login`(`username`,`password`,`name`,`email`,`phonenumber`) VALUES(?,?,?,?,?);";
        
        PreparedStatement statement =connection.prepareStatement(query);
    
        statement.setString(1, userName);
        statement.setString(2, passWord);
        statement.setString(3, name);
        statement.setString(4, email);
        statement.setString(5, phone_number);
        
        int row = statement.executeUpdate();
        if(row>0){
            result=true;

        }else{
            result=false;
        }
        String query2 = "SELECT * FROM Login";
        Statement statement2 = connection.createStatement();
        ResultSet resultSet = statement2.executeQuery(query2);
        
        while (resultSet.next()) {
            String id = resultSet.getString("username");
            String name_ = resultSet.getString("password");
            System.out.println("USERNAME: " + id + ", PASSWORD: " + name_);
            }


    }catch(Exception e){
        e.printStackTrace( );
    }
    return result;
    }
}
