package kr.ac.jejunu.user;

import java.sql.*;

public abstract class UserDao {
    public User findById(Long id) throws ClassNotFoundException, SQLException {

        Connection connection = getConnection();


        PreparedStatement preparedStatement = connection.prepareStatement(
                "select id, name, password from userinfo where id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {

        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into userinfo (name, password) values (?, ?)"
                , Statement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        user.setId(resultSet.getLong(1));

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    abstract public Connection getConnection() throws ClassNotFoundException, SQLException;

}
