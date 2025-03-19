package banca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame(String user) {
        setTitle("🏦 Banca - " + user);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(45, 45, 45));

        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(45, 45, 45));

        JLabel welcomeLabel = new JLabel("👋 Benvenuto, " + user + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.WHITE);
        panel.add(welcomeLabel);

        JButton saldoButton = createStyledButton("💰 Mostra Saldo", e -> mostraSaldo());
        JButton depositoButton = createStyledButton("📥 Deposita", e -> deposita());
        JButton prelievoButton = createStyledButton("📤 Preleva", e -> preleva());
        JButton investimentoButton = createStyledButton("📈 Investi", e -> apriFinestraInvestimenti());
        JButton storicoButton = createStyledButton("📜 Storico Transazioni", e -> visualizzaStorico());
        JButton logoutButton = createStyledButton("🚪 Logout", e -> {
            GestioneUtenti.logout();
            new LoginFrame();
            dispose();
        });

        panel.add(saldoButton);
        panel.add(depositoButton);
        panel.add(prelievoButton);
        panel.add(investimentoButton);
        panel.add(storicoButton);
        panel.add(logoutButton);

        add(panel, BorderLayout.CENTER);
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

    private void mostraSaldo() {
        ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
        JOptionPane.showMessageDialog(this, "🏦 Saldo Banca: " + conto.getSaldoBanca() + "\n" + "💵 Saldo Portafoglio: " + conto.getSaldoPortafoglio(), "Saldo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deposita() {
        ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
        String input = JOptionPane.showInputDialog(this, "💰 Importo da depositare:");
        try {
            double importo = Double.parseDouble(input);
            conto.deposita(importo);
            GestioneUtenti.salvaDatiSuFile("dati_banca.dat");
            JOptionPane.showMessageDialog(this, "✅ Deposito effettuato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Errore: Inserire un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void preleva() {
        ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
        String input = JOptionPane.showInputDialog(this, "💵 Importo da prelevare:");
        try {
            double importo = Double.parseDouble(input);
            if (conto.preleva(importo)) {
                GestioneUtenti.salvaDatiSuFile("dati_banca.dat");
                JOptionPane.showMessageDialog(this, "✅ Prelievo effettuato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Saldo insufficiente!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Errore: Inserire un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void apriFinestraInvestimenti() {
        new InvestimentoFrame();
    }

    private void visualizzaStorico() {
        ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
        JOptionPane.showMessageDialog(this, "📜 Storico Transazioni:\n" + String.join("\n", conto.getStoricoTransazioni()), "Storico", JOptionPane.INFORMATION_MESSAGE);
    }
}