package kr.ac.jejunu.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindStatementStrategy implements StatementStrategy {
    private Long id;

    public FindStatementStrategy(Long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        Object[] param = {id};
        String sql = "select id, name, password from userinfo where id = ?";
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < param.length; i++) {
            preparedStatement.setObject(i + 1, param[i]);
        }
        return preparedStatement;
    }
}
