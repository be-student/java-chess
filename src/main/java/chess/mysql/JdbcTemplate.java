package chess.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    private static final String DB_ERROR_MESSAGE = "입력이 올바르지 않습니다.";

    private final ConnectionPool connectionPool;

    public JdbcTemplate(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public int save(String query, Object... parameters) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);
                ResultSet resultSet = executeUpdate(preparedStatement, parameters)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            throw new IllegalArgumentException(DB_ERROR_MESSAGE);
        } catch (SQLException ex) {
            throw new IllegalArgumentException(DB_ERROR_MESSAGE, ex);
        }
    }

    private ResultSet executeUpdate(PreparedStatement preparedStatement, Object... parameters)
            throws SQLException {
        for (int i = 1; i <= parameters.length; i++) {
            preparedStatement.setObject(i, parameters[i - 1]);
        }
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }

    public void executeUpdate(String query, Object... parameters) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalArgumentException(DB_ERROR_MESSAGE, ex);
        }
    }

    public <T> T query(String query, RowMapper<T> rowMapper, Object... parameters) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                ResultSet resultSet = executeQuery(preparedStatement, parameters)) {
            return rowMapper.mapRow(resultSet);
        } catch (SQLException e) {
            throw new IllegalArgumentException(DB_ERROR_MESSAGE, e);
        }
    }

    public <T> List<T> queryResults(String query, RowMapper<T> rowMapper, Object... parameters) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                ResultSet resultSet = executeQuery(preparedStatement, parameters)) {
            List<T> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;
        } catch (SQLException e) {
            throw new IllegalArgumentException(DB_ERROR_MESSAGE, e);
        }
    }

    private ResultSet executeQuery(PreparedStatement preparedStatement, Object... parameters) throws SQLException {
        for (int i = 1; i <= parameters.length; i++) {
            preparedStatement.setObject(i, parameters[i - 1]);
        }
        return preparedStatement.executeQuery();
    }

    private Connection getConnection() {
        return connectionPool.getConnection();
    }
}
