package banca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame(String user) {
        setTitle("🏦 Banking App - " + user);
        setSize(350, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(20, 20, 40));

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));
        container.setBackground(new Color(20, 20, 40));

        JLabel titleLabel = new JLabel("👋 Benvenuto, " + user + "!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(titleLabel);
        
        container.add(Box.createVerticalStrut(30));

        JButton saldoButton = createStyledButton("💰 Mostra Saldo", e -> mostraSaldo());
        JButton depositoButton = createStyledButton("📥 Deposita", e -> deposita());
        JButton prelievoButton = createStyledButton("📤 Preleva", e -> preleva());
        JButton investimentoButton = createStyledButton("📈 Investi", e -> apriFinestraInvestimenti());
        JButton storicoButton = createStyledButton("📜 Storico Transazioni", e -> visualizzaStorico());
        JButton avanzaMesiButton = createStyledButton("⏩ Avanza Mesi", e -> avanzaMesi());
        JButton logoutButton = createStyledButton("🚪 Logout", e -> {
            GestioneUtenti.logout();
            new LoginFrame();
            dispose();
        });

        container.add(saldoButton);
        container.add(Box.createVerticalStrut(10));
        container.add(depositoButton);
        container.add(Box.createVerticalStrut(10));
        container.add(prelievoButton);
        container.add(Box.createVerticalStrut(10));
        container.add(investimentoButton);
        container.add(Box.createVerticalStrut(10));
        container.add(storicoButton);
        container.add(Box.createVerticalStrut(10));
        container.add(avanzaMesiButton);
        container.add(Box.createVerticalStrut(10));
        container.add(logoutButton);

        add(container, BorderLayout.CENTER);
        setVisible(true);
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

    private void mostraSaldo() {
        ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
        JOptionPane.showMessageDialog(this, "🏦 Saldo Banca: " + conto.getSaldoBanca() + "\n💵 Saldo Portafoglio: " + conto.getSaldoPortafoglio(), "Saldo", JOptionPane.INFORMATION_MESSAGE);
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
            if (conto.preleva(importo, false)) {
                GestioneUtenti.salvaDatiSuFile("dati_banca.dat");
                JOptionPane.showMessageDialog(this, "✅ Prelievo effettuato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Saldo insufficiente!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Errore: Inserire un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void avanzaMesi() {
        ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
        String input = JOptionPane.showInputDialog(this, "⏩ Quanti mesi vuoi far avanzare?");
        try {
            int mesi = Integer.parseInt(input);
            conto.avanzaMesi(mesi);
            GestioneUtenti.salvaDatiSuFile("dati_banca.dat");
            JOptionPane.showMessageDialog(this, "✅ Sono passati " + mesi + " mesi. Nuovo saldo aggiornato!", "Avanzamento Tempo", JOptionPane.INFORMATION_MESSAGE);
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
