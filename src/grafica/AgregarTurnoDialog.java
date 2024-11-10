package grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import DAO.TurnoDAOImpl;
import logica.Turno;

public class AgregarTurnoDialog extends JDialog {

    private JTextField pacienteNombreField;
    private JTextField pacienteContactoField;
    private JTextField fechaField;
    private JTextField horaField;
    private JButton agregarButton, cancelarButton;
    private TurnoDAOImpl turnoDAO;

    public AgregarTurnoDialog(Frame parent, TurnoDAOImpl turnoDAO) {
        super(parent, "Agregar Turno", true);
        this.turnoDAO = turnoDAO;

        setLayout(new GridLayout(5, 2, 10, 10));
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Campos del formulario
        pacienteNombreField = new JTextField();
        pacienteContactoField = new JTextField();
        fechaField = new JTextField("yyyy-MM-dd");  // Ejemplo de formato de fecha
        horaField = new JTextField("HH:mm");  // Ejemplo de formato de hora

        agregarButton = new JButton("Agregar");
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
        add(agregarButton);
        add(cancelarButton);

        // Acción para el botón de agregar
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String pacienteNombre = pacienteNombreField.getText();
                    int pacienteContacto = Integer.parseInt(pacienteContactoField.getText());
                    LocalDateTime fechaHora = LocalDateTime.parse(
                            fechaField.getText() + "T" + horaField.getText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                    Turno nuevoTurno = new Turno(0, fechaHora, pacienteContacto, pacienteNombre);
                    turnoDAO.agregarTurno(nuevoTurno);
                    JOptionPane.showMessageDialog(AgregarTurnoDialog.this, "Turno agregado exitosamente.");
                    dispose();  // Cierra el diálogo
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AgregarTurnoDialog.this, "Error en el formato de datos: " + ex.getMessage());
                }
            }
        });

        // Acción para el botón de cancelar
        cancelarButton.addActionListener(e -> dispose());
    }
}
