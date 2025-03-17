package banca;

// Classe per la gestione del conto bancario
class ContoBancario {
    private double saldoBanca;
    private double saldoPortafoglio;

    public ContoBancario(double saldoBanca, double saldoPortafoglio) {
        this.saldoBanca = saldoBanca;
        this.saldoPortafoglio = saldoPortafoglio;
    }

    public void deposita(double importo) {
        if (importo > 0 && importo <= saldoPortafoglio) { 
            saldoBanca += importo;
            saldoPortafoglio -= importo; 
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

    public double getSaldoBanca() {
        return this.saldoBanca;
    }

}