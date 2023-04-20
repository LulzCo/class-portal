package kr.ac.jejunu.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertStatementStrategy implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Connection connection, Object[] param) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("insert into userinfo(name, password) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < param.length; i++) {
            preparedStatement.setObject(i + 1, param[i]);
        }
        return preparedStatement;
    }
}
