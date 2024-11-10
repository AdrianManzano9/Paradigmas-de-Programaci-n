package Login;

public class SesionActiva {
    private static SesionActiva instancia;
    private int idMedico;
    private String nombreUsuario; // Puede almacenar también el nombre de usuario, si es necesario

    // Constructor privado para el patrón Singleton
    private SesionActiva() {
        this.idMedico = -1; // Valor que indica que  no hay sesión activa
    }

    // Método para obtener la instancia única
    public static SesionActiva getInstancia() {
        if (instancia == null) {
            instancia = new SesionActiva();
        }
        return instancia;
    }

    // Método para iniciar sesión
    public void iniciarSesion(int idMedico, String nombreUsuario) {
        this.idMedico = idMedico;
        this.nombreUsuario = nombreUsuario;
    }

    // Método para cerrar sesión
    public void cerrarSesion() {
        this.idMedico = -1;
        this.nombreUsuario = null;
    }

    // Getters para obtener el idMedico y el nombre de usuario
    public int getIdMedico() {
        return idMedico;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    // Método para verificar si hay una sesión activa
    public boolean isSesionActiva() {
        return idMedico != -1;
    }
}