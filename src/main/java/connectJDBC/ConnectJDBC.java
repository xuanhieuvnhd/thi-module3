package connectJDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectJDBC {
private static final String URL = "jdbc:mysql://localhost:3306/studentmanager";
    private static final String USER = "root";
    private static final String PASSWORD = "761321";

    public static java.sql.Connection getConnection() {
        java.sql.Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("ket noi thanh cong");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ket noi that bai");
            e.printStackTrace();
        }
        return connection;
    }
}