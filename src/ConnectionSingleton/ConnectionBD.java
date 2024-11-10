package ConnectionSingleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {

    private static final ConnectionBD instance = new ConnectionBD();
    private Connection conn = null;


    public static ConnectionBD getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            String projectPath = System.getProperty("user.dir");
            String url = "jdbc:sqlite:" + projectPath + "\\src\\agendas.db";
            conn = DriverManager.getConnection(url);
        }
        return conn;
    }

    public void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}