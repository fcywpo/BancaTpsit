package banca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame(String user) {
        setTitle("Banca - " + user);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1));

        JLabel welcomeLabel = new JLabel("Benvenuto, " + user + "!", SwingConstants.CENTER);
        panel.add(welcomeLabel);

        JButton saldoButton = new JButton("Mostra Saldo");
        saldoButton.addActionListener(e -> mostraSaldo());

        JButton depositoButton = new JButton("Deposita");
        depositoButton.addActionListener(e -> deposita());

        JButton prelievoButton = new JButton("Preleva");
        prelievoButton.addActionListener(e -> preleva());

        JButton investimentoButton = new JButton("Investi");
        investimentoButton.addActionListener(e -> investi());

        JButton storicoButton = new JButton("Storico Transazioni");
        storicoButton.addActionListener(e -> visualizzaStorico());

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
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

        add(panel);
        setVisible(true);
    }

    private void mostraSaldo() {
        ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
        JOptionPane.showMessageDialog(this, "Saldo Banca: " + conto.getSaldoBanca());
    }

    private void deposita() {
        ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
        String input = JOptionPane.showInputDialog(this, "Importo da depositare:");
        try {
            double importo = Double.parseDouble(input);
            conto.deposita(importo);
            JOptionPane.showMessageDialog(this, "Deposito effettuato!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore: Inserire un numero valido.");
        }
    }

    private void preleva() {
        ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
        String input = JOptionPane.showInputDialog(this, "Importo da prelevare:");
        try {
            double importo = Double.parseDouble(input);
            if (conto.preleva(importo)) {
                JOptionPane.showMessageDialog(this, "Prelievo effettuato!");
            } else {
                JOptionPane.showMessageDialog(this, "Saldo insufficiente!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore: Inserire un numero valido.");
        }
    }

    private void investi() {
        ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
        new Investimento().avviaInvestimento(conto);
    }

    private void visualizzaStorico() {
        ContoBancario conto = GestioneUtenti.getUtenteAutenticato().getConto();
        JOptionPane.showMessageDialog(this, String.join("\n", conto.getStoricoTransazioni()));
    }
}

