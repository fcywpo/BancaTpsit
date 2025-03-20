package banca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvestimentoFrame extends JFrame {
    public InvestimentoFrame() {
        setTitle("💹 Investimenti");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));
        getContentPane().setBackground(new Color(45, 45, 45));

        JLabel titleLabel = new JLabel("Scegli il tuo investimento", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel);

        JButton bassoRischio = createStyledButton("📉 Investi a Basso Rischio (5%)", e -> investi(0.05));
        JButton medioRischio = createStyledButton("📊 Investi a Medio Rischio (10%)", e -> investi(0.10));
        JButton altoRischio = createStyledButton("🚀 Investi ad Alto Rischio (20%)", e -> investi(0.20));
        JButton chiudi = createStyledButton("❌ Chiudi", e -> dispose());

        add(bassoRischio);
        add(medioRischio);
        add(altoRischio);
        add(chiudi);

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

private void investi(double rendimento) {
        ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
        String input = JOptionPane.showInputDialog(this, "💰 Inserisci l'importo da investire:");
        try {
            double importo = Double.parseDouble(input);
            if (importo > 0 && importo <= conto.getSaldoBanca()) {
                double risultatoInvestimento = importo * rendimento;

                conto.preleva(importo, true);  // Preleva l'investimento
                GestioneUtenti.salvaDatiSuFile("dati_banca.dat");

                if (risultatoInvestimento > importo) {
                    double guadagno = risultatoInvestimento - importo;
                    conto.deposita(guadagno);
                    JOptionPane.showMessageDialog(this, "🎉 Investimento completato! Guadagno: " + guadagno, "Successo", JOptionPane.INFORMATION_MESSAGE);
                } else if (risultatoInvestimento < importo) {
                    double perdita = importo - risultatoInvestimento;
                    JOptionPane.showMessageDialog(this, "😢 Mi dispiace, hai perso " + perdita + " nel tuo investimento.", "Perdita", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "⚖️ Hai pareggiato, nessun guadagno né perdita.", "Neutro", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Importo non valido o saldo insufficiente!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Errore: Inserire un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

}