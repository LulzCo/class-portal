package kr.ac.jejunu.user;

import java.sql.*;

public class UserDao {

    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }


    public User findById(Long id) throws SQLException {
        String sql = "select id, name, password from userinfo where id = ?";
        Object[] params = {id};
        return jdbcContext.find(sql, params);
    }

    public void insert(User user) throws SQLException {
        String sql = "insert into userinfo(name, password) values(?, ?)";
        Object[] params = {user.getName(), user.getPassword()};
        jdbcContext.insert(user, sql, params);
    }

    public void update(User user) throws SQLException {
        Object[] params = {user.getName(), user.getPassword(), user.getId()};
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        jdbcContext.update(params, sql);
    }

    public void delete(Long id) throws SQLException {
        Object[] params = {id};
        String sql = "delete from userinfo where id = ?";
        jdbcContext.update(params, sql);
    }
}