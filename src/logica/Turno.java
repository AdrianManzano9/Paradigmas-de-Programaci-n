package logica;

import java.time.LocalDateTime;

public class Turno {
    
    private int idTurno;
    private LocalDateTime fechaHora;
    private int pacienteContacto;
    private String pacienteNombre;

    public Turno(int idTurno, LocalDateTime fechaHora, int pacienteContacto, String pacienteNombre) {
        this.idTurno = idTurno;
        this.fechaHora = fechaHora;
        this.pacienteContacto = pacienteContacto;
        this.pacienteNombre = pacienteNombre;
    }

    // Getters y setters
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getIdTurno() {
        return idTurno;
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