package kr.ac.jejunu.user;

import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User findById(Long id) throws SQLException {
        StatementStrategy statementStrategy = new FindStatementStrategy(id);
        return jdbcContext.jdbcContextForFind(statementStrategy);
    }

    private User jdbcContextForFind(Long id, StatementStrategy statementStrategy) throws SQLException {
        return jdbcContext.jdbcContextForFind(id, statementStrategy);
    }

    public void insert(User user) throws SQLException {
        StatementStrategy statementStrategy = new InsertStatementStrategy();
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }

    private void jdbcContextForInsert(User user, StatementStrategy statementStrategy) throws SQLException {
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy();
        jdbcContext.jdbcContextForUpdate(user, statementStrategy);
    }

    private void jdbcContextForUpdate(User user, StatementStrategy statementStrategy) throws SQLException {
        jdbcContext.jdbcContextForUpdate(user, statementStrategy);
    }

    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteStatementStrategy();
        jdbcContext.jdbcContextForDelete(id, statementStrategy);
    }

    private void jdbcContextForDelete(Long id, StatementStrategy statementStrategy) throws SQLException {
        jdbcContext.jdbcContextForDelete(id, statementStrategy);
    }

}
