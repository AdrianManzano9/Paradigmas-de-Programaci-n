package grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import DAO.TurnoDAOImpl;
import logica.Turno;

public class ModificarTurnoDialog extends JDialog {

    private JTextField pacienteNombreField;
    private JTextField pacienteContactoField;
    private JTextField fechaField;
    private JTextField horaField;
    private JButton modificarButton, cancelarButton;
    private Turno turnoOriginal;
    private TurnoDAOImpl turnoDAO;

    public ModificarTurnoDialog(Frame parent, Turno turno, TurnoDAOImpl turnoDAO) {
        super(parent, "Modificar Turno", true);
        this.turnoOriginal = turno;
        this.turnoDAO = turnoDAO;

        setLayout(new GridLayout(5, 2, 10, 10));
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Campos del formulario
        pacienteNombreField = new JTextField(turno.getPacienteNombre());
        pacienteContactoField = new JTextField(String.valueOf(turno.getPacienteContacto()));
        fechaField = new JTextField(turno.getFechaHora().toLocalDate().toString());  // Fecha actual del turno
        horaField = new JTextField(turno.getFechaHora().toLocalTime().toString());  // Hora actual del turno

        modificarButton = new JButton("Modificar");
        cancelarButton = new JButton("Cancelar");

        // Añadir componentes al diálogo
        add(new JLabel("Nombre del Paciente:"));
        add(pacienteNombreField);
        add(new JLabel("Contacto del Paciente:"));
        add(pacienteContactoField);
        add(new JLabel("Fecha (yyyy-MM-dd):"));
        add(fechaField);
        add(new JLabel("Hora (HH:mm):"));
        add(horaField);
        add(modificarButton);
        add(cancelarButton);

        // Acción para el botón de modificar
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String pacienteNombre = pacienteNombreField.getText();
                    int pacienteContacto = Integer.parseInt(pacienteContactoField.getText());
                    LocalDateTime fechaHora = LocalDateTime.parse(
                            fechaField.getText() + "T" + horaField.getText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                    // Actualizar el turno
                    turnoOriginal.setFechaHora(fechaHora);
                    turnoOriginal.setPacienteContacto(pacienteContacto);
                    turnoOriginal.setPacienteNombre(pacienteNombre);
                    turnoDAO.actualizarTurno(turnoOriginal);
                    JOptionPane.showMessageDialog(ModificarTurnoDialog.this, "Turno modificado exitosamente.");
                    dispose();  // Cierra el diálogo
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ModificarTurnoDialog.this, "Error en el formato de datos: " + ex.getMessage());
                }
            }
        });

        // Acción para el botón de cancelar
        cancelarButton.addActionListener(e -> dispose());
    }
}