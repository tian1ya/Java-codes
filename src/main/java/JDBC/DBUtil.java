package JDBC;

import java.sql.*;

public class DBUtil {

    private final static String URL="jdbc:mysql://127.0.0.1/springbootdb";
    private final static String USER="root";
    private final static String PASSWORD="liuxu";
    private  static Connection connection;

    // 静态块
    static {
        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获得数据库连接
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}
