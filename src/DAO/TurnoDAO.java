package DAO;

import java.util.List;
import logica.Turno;


public interface TurnoDAO {
    // Método para agregar un turno
    void agregarTurno(Turno turno);

    // Método para obtener un turno por su ID
    Turno obtenerTurno(int idTurno);

    // Método para actualizar un turno existente
    void actualizarTurno(Turno turno);

    // Método para eliminar un turno por su ID
    void eliminarTurno(int idTurno);

    // Método para obtener todos los turnos
    List<Turno> obtenerTodosLosTurnos();
}
