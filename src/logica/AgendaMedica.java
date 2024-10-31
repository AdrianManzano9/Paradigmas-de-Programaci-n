/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import db.DaO;
import java.util.ArrayList;
import java.util.List;

public class AgendaMedica {

    DaO Dao = DaO.getInstance();
    private List<Turno> turnos;

    public AgendaMedica() {
        this.turnos = new ArrayList<>();
    }

    public void agregarTurno(Turno turno) {
        Dao.conectar(); // aca quieren conectar la base 
        Dao.guardarTurno(turno.getFecha(), turno.getHora(), turno.getPacienteContacto(), turno.getPacienteNombre());
        Dao.desconectar();
    }

    public void cancelarTurno(int idTurno) {

        Dao.conectar(); // aca quieren conectar la base 
        turnos.removeIf(turno -> turno.getIdTurno() == idTurno);
        Dao.desconectar();
    }

    public String[] listarTurnos() {
        Dao.conectar(); // aca quieren conectar la base 
        String[] turnoSting = Dao.mostrarTurnos();
        Dao.desconectar();
        return turnoSting;
    }

    
}
