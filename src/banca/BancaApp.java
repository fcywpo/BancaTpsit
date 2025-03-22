package banca;

// Classe principale

import javax.swing.SwingUtilities;

public class BancaApp {
  private static final String FILE_DATI = "dati_banca.dat";

  public static void main(String[] args) {
    GestioneUtenti.caricaDatiDaFile(FILE_DATI);
    SwingUtilities.invokeLater(LoginFrame::new);
  }
}
