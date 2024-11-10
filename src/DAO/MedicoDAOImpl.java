package DAO;

import ConnectionSingleton.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Medico;
import org.mindrot.jbcrypt.BCrypt;

public class MedicoDAOImpl implements MedicoDAO {

    @Override
    public void agregarMedico(String nombre, String especialidad, String nombreUsuario, String password) {
        String sql = "INSERT INTO medicos (nombre, especialidad, nombreUsuario, password) VALUES (?, ?, ?, ?)";

        // Hashear la contraseña
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection conn = ConnectionBD.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, especialidad);
            stmt.setString(3, nombreUsuario);
            stmt.setString(4, hashedPassword); // Guarda el hash en lugar de la contraseña en texto plano
            stmt.executeUpdate();

            System.out.println("Médico agregado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al agregar médico: " + e.getMessage());
        }
    }

    @Override
    public Medico obtenerMedico(int idMedico) {
        String sql = "SELECT * FROM medicos WHERE idMedico = ?";
        Medico medico = null;

        try (Connection conn = ConnectionBD.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMedico);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                medico = new Medico(
                    rs.getInt("idMedico"),
                    rs.getString("nombre"),
                    rs. getString("especialidad")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener médico: " + e.getMessage());
        }

        return medico;
    }

    @Override
    public void actualizarMedico(Medico medico) {
    String sql = "UPDATE medicos SET nombre = ?, especialidad = ? WHERE idMedico = ?";

    try (Connection conn = ConnectionBD.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, medico.getNombre());
        stmt.setString(2, medico.getEspecialidad());
        stmt.setInt(3, medico.getIdMedico());
        stmt.executeUpdate();

        System.out.println("Médico actualizado correctamente.");

    } catch (SQLException e) {
        System.out.println("Error al actualizar médico: " + e.getMessage());
    }
}

    @Override
    public void eliminarMedico(int idMedico) {
    String sql = "DELETE FROM medicos WHERE idMedico = ?";

    try (Connection conn = ConnectionBD.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idMedico);
        stmt.executeUpdate();

        System.out.println("Médico eliminado correctamente.");

    } catch (SQLException e) {
        System.out.println("Error al eliminar médico: " + e.getMessage());
    }
}
}