package banca;

import java.io.Serializable;
import java.util.Vector;

// Classe per la gestione del conto bancario
class ContoBancario implements Serializable{
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
        } else {
            System.out.println("Importo non valido o fondi insufficienti nel portafoglio.");
        }
    }

    public void guadagnoInvestimento(double guadagno) {
        if (guadagno > 0) {
            saldoPortafoglio += guadagno;
            System.out.println("Guadagno accreditato nel portafoglio. Nuovo saldo: " + saldoPortafoglio);
            deposita(guadagno);
        }
    }

    public boolean preleva(double importo) {
        if (importo > 0 && importo <= saldoBanca) {
            saldoBanca -= importo;
            saldoPortafoglio += importo;
            storicoTransazioni.add("Prelievo di " + importo);
            System.out.println("Prelievo effettuato. Nuovo saldo: " + saldoBanca);
            return true;
        } else {
            System.out.println("Saldo insufficiente o importo non valido.");
            return false;
        }
    }

    public void mostraSaldo() {
        System.out.println("Saldo in banca: " + saldoBanca + "\nSaldo in portafoglio: " + saldoPortafoglio);
    }

    public void avanzaMesi(int mesi) {
        if (mesi > 0) {
            saldoPortafoglio += mesi * 100;
            System.out.println("Sono passati " + mesi + " mesi. Nuovo saldo: " + saldoPortafoglio);
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
    public Vector<String> getStoricoTransazioni() {
        return storicoTransazioni;
    }
}