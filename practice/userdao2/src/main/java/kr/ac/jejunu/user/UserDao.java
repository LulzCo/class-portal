package kr.ac.jejunu.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public User findById(Long id) throws SQLException {
        StatementStrategy statementStrategy = new FindStatementStrategy();
        Object[] param = {id};
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection, param);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public void insert(User user) throws SQLException {
        StatementStrategy statementStrategy = new InsertStatementStrategy();
        Object[] param = {user.getName(), user.getPassword()};
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection, param);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getLong(1));
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy();
        Object[] param = {user.getName(), user.getPassword(), user.getId()};
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection, param);
            preparedStatement.executeUpdate();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteStatementStrategy();
        Object[] param = {id};
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection, param);
            preparedStatement.executeUpdate();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
