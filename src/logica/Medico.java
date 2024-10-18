/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;

/**
 *
 * @author adria
 */
public class Medico {

    private int idMedico;
    private String nombre;
    private String especialidad;
    private AgendaMedica agenda;

    public Medico(int idMedico, String nombre, String especialidad) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.agenda = new AgendaMedica();
    }

    public AgendaMedica getAgenda() {
        return agenda;
    }

    public void agendarTurno(Turno turno) {
        agenda.agregarTurno(turno);
    }

    public void cancelarTurno(int idTurno) {
        agenda.cancelarTurno(idTurno);
    }

    public List<Turno> listarTurnos() {
        return agenda.listarTurnos();
    }
}