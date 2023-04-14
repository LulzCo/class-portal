package kr.ac.jejunu.user;

import java.sql.*;

public class UserDao {

    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User findById(Long id) throws SQLException {
//        StatementStrategy statementStrategy = new FindStatementStrategy(id);
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id, name, password from userinfo where id = ?");
            preparedStatement.setObject(1, id);
            return preparedStatement;
        };
        return jdbcContext.jdbcContextForFind(statementStrategy);
    }

    public void insert(User user) throws SQLException {
//        StatementStrategy statementStrategy = new InsertStatementStrategy(user);
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("insert into userinfo (name, password) values (?, ?)"
                            , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, user.getName());
            preparedStatement.setObject(2, user.getPassword());
            return preparedStatement;
        };
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }

    public void update(User user) throws SQLException {
//        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update userinfo set name = ?, password = ? where id = ?");
            Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            preparedStatement.setObject(1, user.getName());
            preparedStatement.setObject(2, user.getPassword());
            preparedStatement.setObject(3, user.getId());
            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException {
//        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from userinfo where id = ?");
            preparedStatement.setObject(1, id);

            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}
