package grafica;

import Login.LoginController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JTextField usuarioField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        usuarioField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Iniciar sesión");
        registerButton = new JButton("Registrarse");

        add(new JLabel("Usuario:"));
        add(usuarioField);
        add(new JLabel("Contraseña:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                String password = new String(passwordField.getPassword());

                LoginController loginController = new LoginController();
                if (loginController.validarCredenciales(usuario, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login exitoso");
                    new AgendaFrame().setVisible(true);  // Abrir ventana de agenda
                    dispose();  // Cerrar ventana de login
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Usuario o contraseña incorrectos");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistroMedicoFrame().setVisible(true); // Abre la ventana de registro
            }
        });
    }
}