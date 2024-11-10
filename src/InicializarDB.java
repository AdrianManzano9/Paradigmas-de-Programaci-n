import grafica.LoginFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.SwingUtilities;

public class InicializarDB {

    public static void crearTablas() {
        String url = "jdbc:sqlite:src/agendas.db";  // Ruta a tu archivo agendas.db

        // Sentencias SQL para crear las tablas
        String sqlMedicos = "CREATE TABLE IF NOT EXISTS medicos ("
                + "idMedico INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre TEXT NOT NULL,"
                + "especialidad TEXT NOT NULL,"
                + "nombreUsuario TEXT UNIQUE NOT NULL,"
                + "password TEXT NOT NULL"
                + ");";
        
        String sqlTurnos = "CREATE TABLE IF NOT EXISTS turnos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "fechaHora TEXT NOT NULL,"
                + "pacienteContacto INTEGER NOT NULL,"
                + "pacienteNombre TEXT NOT NULL,"
                + "idMedico INTEGER NOT NULL,"
                + "FOREIGN KEY(idMedico) REFERENCES medicos(idMedico)"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Ejecutar la creación de las tablas
            stmt.execute(sqlMedicos);
            stmt.execute(sqlTurnos);

            System.out.println("Tablas creadas o ya existen.");

        } catch (Exception e) {
            System.out.println("Error al crear tablas: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        crearTablas();
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

