package banca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JLabel statusLabel;

    public LoginFrame() {
        setTitle("Login Banca");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        userField = new JTextField();
        panel.add(userField);

        panel.add(new JLabel("Password:"));
        passField = new JPasswordField();
        panel.add(passField);

        JButton loginButton = new JButton("Accedi");
        loginButton.addActionListener(new LoginAction());

        JButton registerButton = new JButton("Registrati");
        registerButton.addActionListener(e -> new RegisterFrame());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        statusLabel = new JLabel("", SwingConstants.CENTER);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    private class LoginAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (GestioneUtenti.login(user, pass)) {
                statusLabel.setText("Accesso riuscito!");
                new MainFrame(user);
                dispose();
            } else {
                statusLabel.setText("Credenziali errate!");
            }
        }
    }
}



