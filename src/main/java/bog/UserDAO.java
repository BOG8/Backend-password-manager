package bog;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by zac on 02.04.17.
 */

@Repository
public class UserDAO {
    protected DataSource dataSource;

    @Autowired
    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Nullable
    public IdResponse registration(UserModel user) {
        final IdResponse idResponse;
        final Connection connection = DataSourceUtils.getConnection(dataSource);
        final String query = "INSERT INTO users(username, password) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            try (ResultSet resultSet = ps.getGeneratedKeys()) {
                resultSet.next();
                idResponse = new IdResponse(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            return null;
        }

        return idResponse;
    }

    @Nullable
    public String getPassword(String username) {
        final Connection connection = DataSourceUtils.getConnection(dataSource);
        final String query = "SELECT password FROM users WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet resultSet = ps.executeQuery()) {
                resultSet.next();
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean setData(UserModel user) {
        final Connection connection = DataSourceUtils.getConnection(dataSource);
        final String query = "UPDATE users SET data = ? WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getData());
            ps.setString(2, user.getUsername());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
