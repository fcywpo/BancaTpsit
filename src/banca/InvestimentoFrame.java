package banca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class InvestimentoFrame extends JFrame {
    private Random random = new Random();

    public InvestimentoFrame() {
        setTitle("💹 Investimenti");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(20, 20, 40));

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        container.setBackground(new Color(20, 20, 40));

        JLabel titleLabel = new JLabel("📈 Scegli il tuo investimento", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(titleLabel);

        container.add(Box.createVerticalStrut(20));

        JButton bassoRischio = createStyledButton("📉 Basso Rischio (5%)", e -> chiediDurata(1));
        JButton medioRischio = createStyledButton("📊 Medio Rischio (10%)", e -> chiediDurata(2));
        JButton altoRischio = createStyledButton("🚀 Alto Rischio (20%)", e -> chiediDurata(3));
        JButton chiudi = createStyledButton("❌ Chiudi", e -> dispose());

        container.add(bassoRischio);
        container.add(Box.createVerticalStrut(10));
        container.add(medioRischio);
        container.add(Box.createVerticalStrut(10));
        container.add(altoRischio);
        container.add(Box.createVerticalStrut(10));
        container.add(chiudi);

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

    private void chiediDurata(int rischioInvestimento) {
        String[] opzioni = {"3 mesi", "6 mesi", "12 mesi"};
        int scelta = JOptionPane.showOptionDialog(this, "Seleziona la durata dell'investimento:", "Durata",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opzioni, opzioni[0]);
        if (scelta >= 0) {
            int mesi = (scelta + 1) * 3;
            investi(rischioInvestimento, mesi);
        }
    }

    private void investi(int rischioInvestimento, int mesi) {
        ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
        String input = JOptionPane.showInputDialog(this, "💰 Inserisci l'importo da investire:");
        try {
            double importo = Double.parseDouble(input);
            if (importo > 0 && importo <= conto.getSaldoBanca()) {
                int numeroCasuale = random.nextInt(10);
                double risultatoInvestimento;
                double moltiplicatore = mesi / 3.0;
                double minRend = 0, maxRend = 0, sogliaVittoria = 0;

                switch (rischioInvestimento) {
                    case 1: minRend = 1; maxRend = 25; sogliaVittoria = 8; break;
                    case 2: minRend = 15; maxRend = 50; sogliaVittoria = 5; break;
                    case 3: minRend = 40; maxRend = 100; sogliaVittoria = 2; break;
                }

                if (numeroCasuale <= sogliaVittoria) {
                    double rendimento = GeneraRandom(maxRend, minRend);
                    risultatoInvestimento = importo + (importo * rendimento / 100) * moltiplicatore;
                    conto.deposita(risultatoInvestimento - importo);
                    JOptionPane.showMessageDialog(this, "🎉 Guadagno: " + (risultatoInvestimento - importo), "Successo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    risultatoInvestimento = -importo * moltiplicatore;
                    conto.preleva(importo, true);
                    JOptionPane.showMessageDialog(this, "😢 Hai perso " + (importo * moltiplicatore) + " nel tuo investimento.", "Perdita", JOptionPane.ERROR_MESSAGE);
                }

                conto.avanzaMesi(mesi);
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Importo non valido o saldo insufficiente!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Errore: Inserire un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static double GeneraRandom(double max, double min) {
        double valore = min + (Math.random() * (max - min));
        return Math.round(valore * 100) / 100.0;
    }
}
