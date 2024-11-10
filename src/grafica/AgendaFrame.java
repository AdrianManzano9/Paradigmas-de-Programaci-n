package grafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import DAO.TurnoDAOImpl;
import logica.Turno;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class AgendaFrame extends JFrame {

    private JTable tablaTurnos;
    private JButton agregarButton, modificarButton, eliminarButton;
    private TurnoDAOImpl turnoDAO = new TurnoDAOImpl();

    public AgendaFrame() {
        setTitle("Agenda de Turnos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Asegura que la aplicación se detenga completamente al cerrar la ventana
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de tabla
        JPanel tablaPanel = new JPanel(new BorderLayout());
        tablaTurnos = new JTable();
        actualizarTabla();  // Cargar turnos en la tabla
        tablaPanel.add(new JScrollPane(tablaTurnos), BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        agregarButton = new JButton("Agregar Turno");
        modificarButton = new JButton("Modificar Turno");
        eliminarButton = new JButton("Eliminar Turno");
        buttonPanel.add(agregarButton);
        buttonPanel.add(modificarButton);
        buttonPanel.add(eliminarButton);

        // Agregar paneles al JFrame
        add(tablaPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Acción para el botón "Agregar Turno"
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarTurnoDialog agregarTurnoDialog = new AgregarTurnoDialog(AgendaFrame.this, turnoDAO);
                agregarTurnoDialog.setVisible(true);
                actualizarTabla();  // Refrescar la tabla de turnos después de agregar uno nuevo
            }
        });

        // Acción para el botón "Modificar Turno"
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaTurnos.getSelectedRow();
                if (selectedRow != -1) {
                    int idTurno = (int) tablaTurnos.getValueAt(selectedRow, 0);
                    Turno turno = turnoDAO.obtenerTurno(idTurno);  // Obtiene el turno a modificar

                    if (turno != null) {
                        ModificarTurnoDialog modificarTurnoDialog = new ModificarTurnoDialog(AgendaFrame.this, turno, turnoDAO);
                        modificarTurnoDialog.setVisible(true);
                        actualizarTabla();  // Refrescar la tabla después de modificar el turno
                    } else {
                        JOptionPane.showMessageDialog(AgendaFrame.this, "No se pudo obtener el turno para modificar.");
                    }
                } else {
                    JOptionPane.showMessageDialog(AgendaFrame.this, "Selecciona un turno para modificar.");
                }
            }
        });

        // Acción para el botón "Eliminar Turno"
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaTurnos.getSelectedRow();
                if (selectedRow != -1) {
                    int idTurno = (int) tablaTurnos.getValueAt(selectedRow, 0);
                    turnoDAO.eliminarTurno(idTurno);
                    actualizarTabla();  // Refrescar la tabla de turnos después de eliminar uno
                } else {
                    JOptionPane.showMessageDialog(AgendaFrame.this, "Selecciona un turno para eliminar.");
                }
            }
        });
    }

    // Método para actualizar la tabla de turnos
    private void actualizarTabla() {
        String[] columnas = {"ID", "Fecha y Hora", "Paciente", "Contacto"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        List<Turno> turnos = turnoDAO.obtenerTodosLosTurnos();
        for (Turno turno : turnos) {
            modelo.addRow(new Object[]{
                    turno.getIdTurno(),
                    turno.getFechaHora(),
                    turno.getPacienteNombre(),
                    turno.getPacienteContacto()
            });
        }
        tablaTurnos.setModel(modelo);
    }
}