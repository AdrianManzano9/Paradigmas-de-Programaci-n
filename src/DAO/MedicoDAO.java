package DAO;

import logica.Medico;

public interface MedicoDAO {
    
  // Método para agregar un médico a la base de datos
    void agregarMedico(String nombre, String especialidad, String nombreUsuario, String password);

    // Método para obtener un médico por su ID
    Medico obtenerMedico(int idMedico);  
    
    // Método para actualizar un médico existente
    void actualizarMedico(Medico medico);

    // Método para eliminar un médico por su ID
    void eliminarMedico(int idMedico);
}
