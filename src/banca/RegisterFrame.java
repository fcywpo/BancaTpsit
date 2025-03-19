package banca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JLabel statusLabel;

    public RegisterFrame() {
        setTitle("Registrazione Utente");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        userField = new JTextField();
        panel.add(userField);

        panel.add(new JLabel("Password:"));
        passField = new JPasswordField();
        panel.add(passField);

        JButton registerButton = new JButton("Registrati");
        registerButton.addActionListener(new RegisterAction());

        statusLabel = new JLabel("", SwingConstants.CENTER);

        add(panel, BorderLayout.CENTER);
        add(registerButton, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    private class RegisterAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (GestioneUtenti.registraUtente(user, pass)) {
                statusLabel.setText("Registrazione completata!");
                JOptionPane.showMessageDialog(null, "Registrazione avvenuta con successo!");
                dispose();
            } else {
                statusLabel.setText("Errore: utente già esistente!");
            }
        }
    }
}

