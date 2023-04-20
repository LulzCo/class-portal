package kr.ac.jejunu.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatementStrategy implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Connection connection, Object[] param) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("delete from userinfo where id = ?");
        for (int i = 0; i < param.length; i++) {
            preparedStatement.setObject(i + 1, param[i]);
        }
        return preparedStatement;
    }
}
