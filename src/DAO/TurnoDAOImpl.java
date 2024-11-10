package DAO;

import Login.SesionActiva;
import ConnectionSingleton.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import logica.Turno;

public class TurnoDAOImpl implements TurnoDAO {
    
     // Método para agregar un turno a la base de datos
    @Override
    public void agregarTurno(Turno turno) {
    String sql = "INSERT INTO turnos (fechaHora, pacienteContacto, pacienteNombre, idMedico) VALUES (?, ?, ?, ?)";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    try (Connection conn = ConnectionBD.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        int idMedico = SesionActiva.getInstancia().getIdMedico(); // Obtener el idMedico de la sesión activa
        stmt.setString(1, turno.getFechaHora().format(formatter));
        stmt.setInt(2, turno.getPacienteContacto());
        stmt.setString(3, turno.getPacienteNombre());
        stmt.setInt(4, idMedico); // Relacionar el turno con el médico en sesión
        stmt.executeUpdate();

    } catch (SQLException e) {
        System.out.println("Error al agregar turno: " + e.getMessage());
    }
}

    // Método para obtener un turno por su ID
    @Override
    public Turno obtenerTurno(int idTurno) {
    String sql = "SELECT * FROM turnos WHERE id = ? AND idMedico = ?";
    Turno turno = null;

    try (Connection conn = ConnectionBD.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        int idMedico = SesionActiva.getInstancia().getIdMedico(); // Obtener el idMedico de la sesión activa
        stmt.setInt(1, idTurno);
        stmt.setInt(2, idMedico);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            LocalDateTime fechaHora = rs.getTimestamp("fechaHora").toLocalDateTime();
            turno = new Turno(
                rs.getInt("id"),
                fechaHora,
                rs.getInt("pacienteContacto"),
                rs.getString("pacienteNombre")
            );
        }

    } catch (SQLException e) {
        System.out.println("Error al obtener turno: " + e.getMessage());
    }

    return turno;
}
    
    // Método para actualizar un turno existente
    @Override
    public void actualizarTurno(Turno turno) {
    String sql = "UPDATE turnos SET fechaHora = ?, pacienteContacto = ?, pacienteNombre = ? WHERE id = ? AND idMedico = ?";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    try (Connection conn = ConnectionBD.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        int idMedico = SesionActiva.getInstancia().getIdMedico(); // Obtener el idMedico de la sesión activa
        stmt.setString(1, turno.getFechaHora().format(formatter));
        stmt.setInt(2, turno.getPacienteContacto());
        stmt.setString(3, turno.getPacienteNombre());
        stmt.setInt(4, turno.getIdTurno());
        stmt.setInt(5, idMedico); // Asegurarse de que el turno pertenece al médico en sesión
        stmt.executeUpdate();

        System.out.println("Turno actualizado correctamente.");

    } catch (SQLException e) {
        System.out.println("Error al actualizar turno: " + e.getMessage());
    }
}

    // Método para eliminar un turno por su ID
    @Override
    public void eliminarTurno(int idTurno) {
    String sql = "DELETE FROM turnos WHERE id = ? AND idMedico = ?";

    try (Connection conn = ConnectionBD.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        int idMedico = SesionActiva.getInstancia().getIdMedico(); // Obtener el idMedico de la sesión activa
        stmt.setInt(1, idTurno);
        stmt.setInt(2, idMedico); // Asegurarse de que el turno pertenece al médico en sesión
        stmt.executeUpdate();

        System.out.println("Turno eliminado correctamente.");

    } catch (SQLException e) {
        System.out.println("Error al eliminar turno: " + e.getMessage());
    }
}

    // Método para obtener todos los turnos de la base de datos
    @Override
    public List<Turno> obtenerTodosLosTurnos() {
    String sql = "SELECT * FROM turnos WHERE idMedico = ?";
    List<Turno> turnos = new ArrayList<>();

    try (Connection conn = ConnectionBD.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // Obtiene el idMedico de la sesión activa
        int idMedico = SesionActiva.getInstancia().getIdMedico(); 
        stmt.setInt(1, idMedico); // Filtra los turnos del médico en sesión
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            LocalDateTime fechaHora = rs.getTimestamp("fechaHora").toLocalDateTime();
            Turno turno = new Turno(
                rs.getInt("id"),
                fechaHora,
                rs.getInt("pacienteContacto"),
                rs.getString("pacienteNombre")
            );
            turnos.add(turno);
        }

    } catch (SQLException e) {
        System.out.println("Error al obtener turnos: " + e.getMessage());
    }

    return turnos;
}
}