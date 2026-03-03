import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDatabase {

    private static final String URL = "jdbc:postgresql://localhost:5432/test_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static Connection con;

    public static Connection getConnection(){
        try{
            con = DriverManager.getConnection(URL,USER,PASSWORD);
        }
        catch(SQLException e){
            System.out.println("Connection Failed! Check output console");
        }
        return  con;
    }

}
