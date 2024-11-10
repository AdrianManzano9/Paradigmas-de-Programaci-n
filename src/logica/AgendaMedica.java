package logica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AgendaMedica {
    private List<Turno> turnos;

    public AgendaMedica() {
        this.turnos = new ArrayList<>();
    }

    public void agregarTurno(Turno turno) {
        turnos.add(turno);
    }

    public void cancelarTurno(int idTurno) {
        turnos.removeIf(turno -> turno.getIdTurno() == idTurno);
    }

    public List<Turno> listarTurnos() {
        return new ArrayList<>(turnos);
    }

    // Método para obtener turnos ordenados por fechaHora
    public List<Turno> listarTurnosOrdenadosPorFecha() {
        return turnos.stream()
                .sorted(Comparator.comparing(Turno::getFechaHora))
                .collect(Collectors.toList());
    }
}