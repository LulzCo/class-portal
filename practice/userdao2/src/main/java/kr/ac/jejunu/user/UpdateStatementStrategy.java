package kr.ac.jejunu.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStatementStrategy implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Connection connection, Object[] param) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("update userinfo set name = ?, password = ? where id = ?");
        for (int i = 0; i < param.length; i++) {
            preparedStatement.setObject(i + 1, param[i]);
        }
        return preparedStatement;
    }
}
