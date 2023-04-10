package kr.ac.jejunu.user;

import java.sql.*;

public interface ConnectionMaker {

    Connection getConnection() throws ClassNotFoundException, SQLException;
}
