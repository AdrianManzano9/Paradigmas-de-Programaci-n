package Login;

import ConnectionSingleton.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController {
    
    public boolean validarCredenciales(String nombreUsuario, String password) {
    String sql = "SELECT idMedico, password FROM medicos WHERE nombreUsuario = ?";

    try (Connection conn = ConnectionBD.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, nombreUsuario);  // Utiliza nombreUsuario en lugar del nombre real
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String storedHash = rs.getString("password");
            
            // Valida la contraseña ingresada contra el hash almacenado
            if (BCrypt.checkpw(password, storedHash)) {
                int idMedico = rs.getInt("idMedico");  // Obtiene el idMedico asociado
                SesionActiva.getInstancia().iniciarSesion(idMedico, nombreUsuario); // Inicia la sesión con el idMedico
                return true;
            }
        }

    } catch (SQLException e) {
        System.out.println("Error al validar las credenciales: " + e.getMessage());
    }

    return false; // Retorna falso si la validación falla
}
}