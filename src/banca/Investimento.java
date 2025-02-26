// Classe per la gestione degli investimenti
import java.util.Random;
class Investimento {
    private Random random = new Random();

    public double calcolaRendimento(double importo, int durata, int rischio) {
        double fattore = 1 + (rischio / 100.0) * random.nextDouble();
        return importo * Math.pow(fattore, durata);
    }
}