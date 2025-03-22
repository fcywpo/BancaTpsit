package banca;

import java.io.Serializable;
import java.util.Vector;

// Classe per la gestione del conto bancario
public class ContoBancario implements Serializable {
  private double saldoBanca;
  private double saldoPortafoglio;
  private Vector<String> storicoTransazioni;

  public ContoBancario(double saldoBanca, double saldoPortafoglio) {
    this.saldoBanca = saldoBanca;
    this.saldoPortafoglio = saldoPortafoglio;
    this.storicoTransazioni = new Vector<>();
  }

  public void deposita(double importo) {
    if (importo > 0 && importo <= saldoPortafoglio) {
      saldoBanca += importo;
      saldoPortafoglio -= importo;
      storicoTransazioni.add("Deposito di " + importo);
      System.out.println("Deposito effettuato. Nuovo saldo banca: " + saldoBanca);
      System.out.println("Saldo portafoglio rimanente: " + saldoPortafoglio);
      GestioneUtenti.esportaDatiCSV("data.csv");
    } else {
      System.out.println("Importo non valido o fondi insufficienti nel portafoglio.");
    }
  }

  public void guadagnoInvestimento(double guadagno) {
    if (guadagno > 0) {
      saldoBanca += guadagno; // Guadagno va direttamente in banca
      storicoTransazioni.add("Guadagno investimento: " + guadagno);
      System.out.println("Guadagno accreditato in banca. Nuovo saldo banca: " + saldoBanca);
    } else if (guadagno < 0) {
      saldoBanca += guadagno; // Perdita viene sottratta dalla banca
      storicoTransazioni.add("Perdita investimento: " + guadagno);
      System.out.println("Perdita applicata in banca. Nuovo saldo banca: " + saldoBanca);
    }
    GestioneUtenti.esportaDatiCSV("data.csv");
  }

  public boolean preleva(double importo, boolean perInvestimento) {
    if (importo > 0 && importo <= saldoBanca) {
      saldoBanca -= importo;
      if (!perInvestimento) {
        saldoPortafoglio += importo; // Aggiunge al portafoglio solo se non è un investimento
      }
      storicoTransazioni.add(
          "Prelievo di " + importo + (perInvestimento ? " per investimento" : ""));
      System.out.println("Prelievo effettuato. Nuovo saldo banca: " + saldoBanca);
      GestioneUtenti.esportaDatiCSV("data.csv");
      return true;
    } else {
      System.out.println("Saldo insufficiente o importo non valido.");
      return false;
    }
  }

  public void mostraSaldo() {
    System.out.println(
        "Saldo in banca: " + saldoBanca + "\nSaldo in portafoglio: " + saldoPortafoglio);
  }

  public void avanzaMesi(int mesi) {
    if (mesi > 0) {
      saldoPortafoglio += mesi * 100;
      System.out.println("Sono passati " + mesi + " mesi. Nuovo saldo: " + saldoPortafoglio);
      GestioneUtenti.esportaDatiCSV("data.csv");
    } else {
      System.out.println("Numero di mesi non valido.");
    }
  }

  public void visualizzaStorico() {
    System.out.println("Storico transazioni:");
    for (int i = 0; i < storicoTransazioni.size(); i++) {
      System.out.println(storicoTransazioni.get(i));
    }
  }

  public double getSaldoBanca() {
    return this.saldoBanca;
  }

  public double getSaldoPortafoglio() {
    return this.saldoPortafoglio;
  }

  public Vector<String> getStoricoTransazioni() {
    return storicoTransazioni;
  }
}
