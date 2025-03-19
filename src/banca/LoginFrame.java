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
        setTitle("🔑 Login Banca");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(45, 45, 45));

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(45, 45, 45));

        JLabel userLabel = new JLabel("👤 Username:");
        userLabel.setForeground(Color.WHITE);
        userField = new JTextField();

        JLabel passLabel = new JLabel("🔒 Password:");
        passLabel.setForeground(Color.WHITE);
        passField = new JPasswordField();

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);

        JButton loginButton = createStyledButton("✅ Accedi", e -> login());
        JButton registerButton = createStyledButton("🆕 Registrati", e -> new RegisterFrame());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(45, 45, 45));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.WHITE);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    private JButton createStyledButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(action);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 144, 255));
            }
        });
        return button;
    }

    private void login() {
        String user = userField.getText();
        String pass = new String(passField.getPassword());

        if (GestioneUtenti.login(user, pass)) {
            statusLabel.setText("✅ Accesso riuscito!");
            JOptionPane.showMessageDialog(this, "🎉 Benvenuto, " + user + "!", "Login Successo", JOptionPane.INFORMATION_MESSAGE);
            new MainFrame(user);
            dispose();
        } else {
            statusLabel.setText("❌ Credenziali errate!");
            JOptionPane.showMessageDialog(this, "⚠️ Username o password errati!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}