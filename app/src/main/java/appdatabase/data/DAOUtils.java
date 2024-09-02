package appdatabase.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

public class DAOUtils {
    public static Connection localConnection (String database, String username, String password) {
        try {
            var host = "localhost";
            var port = 3306;
            var connectionString = "jdbc:mysql://" + host + ":" + port + "/" + database;
            return DriverManager.getConnection(connectionString, username, password);
        } catch (Exception e) {
            throw new RuntimeErrorException(null);
        }
    }

    public static PreparedStatement prepare (Connection connection, String query, Object... values) throws SQLException {
        PreparedStatement prepared = null;
        try {
            prepared = connection.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                prepared.setObject(i + 1, values[i]);
            }
            return prepared;
        } catch (Exception e) {
            if (prepared != null) {
                prepared.close();
            }
            throw e;
        }
    }
}
