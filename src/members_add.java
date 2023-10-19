import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class members_add {
    public boolean add_member(String username,String member){
        boolean result = false;
        try{
            
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginDetails", "root", "mysqlrootpassword");
        System.out.println("Connected");
        String query = "INSERT INTO `LoginDetails`.`Members`(`username`,`member_name`) VALUES(?,?);";
        
        PreparedStatement statement =connection.prepareStatement(query);
    
        statement.setString(1, username);
        statement.setString(2, member);
        
        int row = statement.executeUpdate();
        if(row>0){
            result=true;

        }else{
            result=false;
        }

    }catch(Exception e){
        e.printStackTrace( );
    }
    return result;
    }
}
