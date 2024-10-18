/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.Date;

/**
 *
 * @author adria
 */
public class Turno {
    
    private int idTurno;
    private String fecha;
    private String hora;
    private int pacienteContacto;
    private String pacienteNombre;

    public Turno( String fecha, String hora, int pacienteContacto, String pacienteNombre) {
                    
        this.fecha = fecha;
        this.hora = hora;
        this.pacienteContacto = pacienteContacto;
        this.pacienteNombre = pacienteNombre;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String Fecha) {
        this.fecha = Fecha;
    }

    public int getPacienteContacto() {
        return pacienteContacto;
    }

    public void setPacienteContacto(int pacienteContacto) {
        this.pacienteContacto = pacienteContacto;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

   

   
    }

   
    
    

