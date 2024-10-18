/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import db.DaO;
import java.util.ArrayList;
import java.util.List;

public class AgendaMedica {

    DaO Dao = new DaO();
    private List<Turno> turnos;

    public AgendaMedica() {
        this.turnos = new ArrayList<>();
    }

    public void agregarTurno(Turno turno) {
        Dao.guardarTurno(turno.getFecha(), turno.getHora(), turno.getPacienteContacto(), turno.getPacienteNombre());
    }

    public void cancelarTurno(int idTurno) {
        turnos.removeIf(turno -> turno.getIdTurno() == idTurno);
    }

    public List<Turno> listarTurnos() {
        return turnos;
    }

    public List<Turno> buscarTurnosPorFecha(String fecha) {
        List<Turno> turnosPorFecha = new ArrayList<>();
        for (Turno turno : turnos) {
            if (turno.getFecha().equals(fecha)) {
                turnosPorFecha.add(turno);
            }
        }
        return turnosPorFecha;
    }
}
