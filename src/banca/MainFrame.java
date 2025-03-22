package banca;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame extends JFrame {
  public MainFrame(String user) {
    setTitle("🏦 Banking App - " + user);
    setSize(450, 600);
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

    @SuppressWarnings("unused")
    JButton saldoButton = createStyledButton("💰 Mostra Saldo", ignored -> mostraSaldo());
    @SuppressWarnings("unused")
    JButton depositoButton = createStyledButton("📥 Deposita", ignored -> deposita());
    @SuppressWarnings("unused")
    JButton prelievoButton = createStyledButton("📤 Preleva", ignored -> preleva());
    @SuppressWarnings("unused")
    JButton investimentoButton =
        createStyledButton("📈 Investi", ignored -> apriFinestraInvestimenti());
    @SuppressWarnings("unused")
    JButton storicoButton =
        createStyledButton("📜 Storico Transazioni", ignored -> visualizzaStorico());
    @SuppressWarnings("unused")
    JButton avanzaMesiButton = createStyledButton("⏩ Avanza Mesi", ignored -> avanzaMesi());
    @SuppressWarnings("unused")
    JButton exportButton = createStyledButton("📂 Esporta Dati", ignored -> esportaDati());
    @SuppressWarnings("unused")
    JButton logoutButton =
        createStyledButton(
            "🚪 Logout",
            ignored -> {
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
    container.add(exportButton);
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
    JOptionPane.showMessageDialog(
        this,
        "🏦 Saldo Banca: "
            + conto.getSaldoBanca()
            + "\n💵 Saldo Portafoglio: "
            + conto.getSaldoPortafoglio(),
        "Saldo",
        JOptionPane.INFORMATION_MESSAGE);
  }

  private void deposita() {
    ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
    String input = JOptionPane.showInputDialog(this, "💰 Importo da depositare:");
    try {
      double importo = Double.parseDouble(input);
      conto.deposita(importo, false);
      GestioneUtenti.salvaDatiSuFile("dati_banca.dat");
      JOptionPane.showMessageDialog(
          this, "✅ Deposito effettuato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(
          this, "❌ Errore: Inserire un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void preleva() {
    ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
    String input = JOptionPane.showInputDialog(this, "💵 Importo da prelevare:");
    try {
      double importo = Double.parseDouble(input);
      if (conto.preleva(importo, false)) {
        GestioneUtenti.salvaDatiSuFile("dati_banca.dat");
        JOptionPane.showMessageDialog(
            this,
            "✅ Prelievo effettuato con successo!",
            "Successo",
            JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(
            this, "⚠️ Saldo insufficiente!", "Errore", JOptionPane.ERROR_MESSAGE);
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(
          this, "❌ Errore: Inserire un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void avanzaMesi() {
    ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
    String input = JOptionPane.showInputDialog(this, "⏩ Quanti mesi vuoi far avanzare?");
    try {
      int mesi = Integer.parseInt(input);
      conto.avanzaMesi(mesi);
      GestioneUtenti.salvaDatiSuFile("dati_banca.dat");
      JOptionPane.showMessageDialog(
          this,
          "✅ Sono passati " + mesi + " mesi. Nuovo saldo aggiornato!",
          "Avanzamento Tempo",
          JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(
          this, "❌ Errore: Inserire un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void apriFinestraInvestimenti() {
    new InvestimentoFrame();
  }

  private void visualizzaStorico() {
    ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
    JOptionPane.showMessageDialog(
        this,
        "📜 Storico Transazioni:\n" + String.join("\n", conto.getStoricoTransazioni()),
        "Storico",
        JOptionPane.INFORMATION_MESSAGE);
  }

  private void esportaDati() {
    GestioneUtenti.esportaDatiCSV("data.csv");
    JOptionPane.showMessageDialog(
        this, "📂 Dati esportati con successo!", "Esportazione", JOptionPane.INFORMATION_MESSAGE);
  }
}
