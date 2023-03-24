package kr.ac.jejunu.user;

import java.sql.*;

public interface ConnectionMaker {

    public Connection getConnection() throws ClassNotFoundException, SQLException;
//    {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost/jeju", "root", "1234");
//        return connection;
//    }
}
