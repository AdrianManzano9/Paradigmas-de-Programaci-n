package db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaO {

    Connection conn = null;
    Statement stmt = null;
    String sql;

    public void conectar() {
        try {
            // Paso 1: Registrar el driver JDBC de SQLite
            Class.forName("org.sqlite.JDBC");

            // Paso 2: Verificar si la base de datos ya existe
            String url = "jdbc:sqlite:C:\\Users\\adria\\OneDrive\\Documentos\\NetBeansProjects\\AgendaDeTurnos\\src\\agendas.db";
            File file = new File(url.replace("jdbc:sqlite:", ""));
            if (file.exists()) {
                System.out.println("La base de datos ya existe");
                conn = DriverManager.getConnection(url);
                return;
            } else {

                // Paso 2.1: Establecer la conexión con la base de datos
                url = "jdbc:sqlite:C:\\Users\\adria\\OneDrive\\Documentos\\NetBeansProjects\\AgendaDeTurnos\\src\\agendas.db";
                conn = DriverManager.getConnection(url);

                // Autenticar la conexión exitosa y mostrar la ruta
                System.out.println("Conexión a la base de datos establecida correctamente");
                System.out.println("La base de datos se ha creado en: " + conn.getMetaData().getURL());
            }

            // Paso 3: Crear la tabla de turnos
            stmt = conn.createStatement();
            sql = "CREATE TABLE turnos "
                    + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "fecha TEXT NOT NULL, "
                    + "hora TEXT NOT NULL, "
                    + "pacienteContacto INTEGER NOT NULL UNIQUE, "
                    + "pacienteNombre TEXT NOT NULL)";
            stmt.executeUpdate(sql);

            System.out.println("Base de datos creada correctamente");

//posibles errores de conexion
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public void guardarTurno(String fecha, String hora, int pacienteContacto, String pacienteNombre) {
        try {
            stmt = conn.createStatement();
            sql = "INSERT INTO turnos (fecha, hora, pacienteContacto, pacienteNombre) "
                    + "VALUES ('" + fecha + "', '" + hora + "', " + pacienteContacto + ", '" + pacienteNombre + "')";
            stmt.executeUpdate(sql);

            System.out.println("Usuario agregado correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarTurnos() {
        // Paso 6: Mostrar los usuarios
        try {
            stmt = conn.createStatement();
            sql = "SELECT * FROM turnos";
            ResultSet rs = stmt.executeQuery(sql);

            // Paso 4: Procesar los resultados de la consulta
            while (rs.next()) {
                int id = rs.getInt("id");
                String fecha = rs.getString("fecha");
                String hora = rs.getString("hora");
                int pacienteContacto = rs.getInt("pacienteContacto");
                String pacienteNombre = rs.getString("pacienteNombre");
                System.out.println("ID: " + id + ", fecha: " + fecha + ", hora: " + hora + ", pacienteContacto: " + pacienteContacto + ", pacienteNombre: " + pacienteNombre);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}