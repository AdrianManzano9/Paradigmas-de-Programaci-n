package grafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DAO.MedicoDAOImpl;

public class RegistroMedicoFrame extends JFrame {

    private JTextField nombreField;
    private JTextField especialidadField;
    private JTextField nombreUsuarioField;
    private JPasswordField passwordField;
    private JButton registrarButton;

    public RegistroMedicoFrame() {
        setTitle("Registro de Nuevo Médico");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        nombreField = new JTextField(15);
        especialidadField = new JTextField(15);
        nombreUsuarioField = new JTextField(15);
        passwordField = new JPasswordField(15);
        registrarButton = new JButton("Registrar");

        add(new JLabel("Nombre:"));
        add(nombreField);
        add(new JLabel("Especialidad:"));
        add(especialidadField);
        add(new JLabel("Nombre de Usuario:"));
        add(nombreUsuarioField);
        add(new JLabel("Contraseña:"));
        add(passwordField);
        add(registrarButton);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String especialidad = especialidadField.getText();
                String nombreUsuario = nombreUsuarioField.getText();
                String password = new String(passwordField.getPassword());

                MedicoDAOImpl medicoDAO = new MedicoDAOImpl();
                medicoDAO.agregarMedico(nombre, especialidad, nombreUsuario, password);

                JOptionPane.showMessageDialog(RegistroMedicoFrame.this, "Médico registrado exitosamente.");
                dispose();  // Cerrar la ventana de registro
            }
        });
    }
}
