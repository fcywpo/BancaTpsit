package banca;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RegisterFrame extends JFrame {
  private JTextField userField;
  private JPasswordField passField;
  private JLabel statusLabel;

  public RegisterFrame() {
    setTitle("🆕 Banking App - Registrazione");
    setSize(350, 600);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());
    getContentPane().setBackground(new Color(20, 20, 40));

    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    container.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));
    container.setBackground(new Color(20, 20, 40));

    JLabel titleLabel = new JLabel("🆕 Crea un Account", SwingConstants.CENTER);
    titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
    titleLabel.setForeground(Color.WHITE);
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    container.add(titleLabel);

    container.add(Box.createVerticalStrut(30));

    userField = createStyledField("", "👤 Username");
    passField = createStyledPasswordField("", "🔒 Password");

    container.add(userField);
    container.add(Box.createVerticalStrut(20));
    container.add(passField);
    container.add(Box.createVerticalStrut(30));

    @SuppressWarnings("unused")
    JButton registerButton = createStyledButton("✅ Registrati", ignored -> register());

    container.add(registerButton);

    statusLabel = new JLabel("", SwingConstants.CENTER);
    statusLabel.setForeground(Color.WHITE);
    container.add(Box.createVerticalStrut(20));
    container.add(statusLabel);

    add(container, BorderLayout.CENTER);
    setVisible(true);
  }

  private JTextField createStyledField(String text, String placeholder) {
    JTextField field = new JTextField(text);
    field.setFont(new Font("SansSerif", Font.PLAIN, 16));
    field.setForeground(Color.GRAY);
    field.setBackground(new Color(35, 35, 55));
    field.setCaretColor(Color.WHITE);
    field.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    field.setPreferredSize(new Dimension(300, 40));
    field.setMaximumSize(new Dimension(300, 40));
    field.setText(placeholder);
    field.addFocusListener(
        new java.awt.event.FocusAdapter() {
          public void focusGained(java.awt.event.FocusEvent evt) {
            if (field.getText().equals(placeholder)) {
              field.setText("");
              field.setForeground(Color.WHITE);
            }
          }

          public void focusLost(java.awt.event.FocusEvent evt) {
            if (field.getText().isEmpty()) {
              field.setText(placeholder);
              field.setForeground(Color.GRAY);
            }
          }
        });
    return field;
  }

  private JPasswordField createStyledPasswordField(String text, String placeholder) {
    JPasswordField field = new JPasswordField(text);
    field.setFont(new Font("SansSerif", Font.PLAIN, 16));
    field.setForeground(Color.GRAY);
    field.setBackground(new Color(35, 35, 55));
    field.setCaretColor(Color.WHITE);
    field.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    field.setPreferredSize(new Dimension(300, 40));
    field.setMaximumSize(new Dimension(300, 40));
    field.setEchoChar((char) 0);
    field.setText(placeholder);
    field.addFocusListener(
        new java.awt.event.FocusAdapter() {
          public void focusGained(java.awt.event.FocusEvent evt) {
            if (new String(field.getPassword()).equals(placeholder)) {
              field.setText("");
              field.setForeground(Color.WHITE);
              field.setEchoChar('●');
            }
          }

          public void focusLost(java.awt.event.FocusEvent evt) {
            if (field.getPassword().length == 0) {
              field.setText(placeholder);
              field.setForeground(Color.GRAY);
              field.setEchoChar((char) 0);
            }
          }
        });
    return field;
  }

  private JButton createStyledButton(String text, ActionListener action) {
    JButton button = new JButton(text);
    button.setFont(new Font("SansSerif", Font.BOLD, 16));
    button.setBackground(new Color(255, 215, 0));
    button.setForeground(Color.BLACK);
    button.setFocusPainted(false);
    button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    button.setMaximumSize(new Dimension(300, 50));
    button.addActionListener(action);
    return button;
  }

  private void register() {
    String user = userField.getText();
    String pass = new String(passField.getPassword());

    if (GestioneUtenti.registraUtente(user, pass)) {
      statusLabel.setText("✅ Registrazione completata!");
      JOptionPane.showMessageDialog(
          this,
          "🎉 Account creato con successo!",
          "Registrazione Successo",
          JOptionPane.INFORMATION_MESSAGE);
      dispose();
    } else {
      statusLabel.setText("❌ Errore: utente già esistente!");
      JOptionPane.showMessageDialog(
          this, "⚠️ Questo username è già in uso!", "Errore", JOptionPane.ERROR_MESSAGE);
    }
  }
}
