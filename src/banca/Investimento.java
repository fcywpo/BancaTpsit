package banca;

import java.util.Random;
import java.util.Scanner;

// Classe per la gestione degli investimenti
class Investimento {
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

   public double calcolaRendimento(double importo, int durata, int rischio) {
       double fattore = 1 + (rischio / 100.0) * random.nextDouble();
        return importo * Math.pow(fattore, durata);
    }


    public void avviaInvestimento(ContoBancario conto) {
        double soldiDaInvestire = 0;
        boolean inputValido = false;

        while (!inputValido) {
            try {
                System.out.print("Inserisci quanti soldi vuoi investire --> ");
                soldiDaInvestire = scanner.nextDouble();
                if (soldiDaInvestire > 0 && soldiDaInvestire <= conto.getSaldoBanca()) {
                    inputValido = true;
                } else {
                    System.out.println("Importo non valido o saldo insufficiente!");
                }
            } catch (Exception e) {
                System.out.println("Errore: Inserire un valore valido!");
                scanner.next();
            }
        }

        System.out.println("1) Investimento di Breve durata");
        System.out.println("2) Investimento di Media durata");
        System.out.println("3) Investimento di Lunga durata");
        System.out.print("Selezione: ");
        int durataInvestimento = tryMenu(1, 3);

        System.out.println("1) Basso Rischio e Basso Guadagno");
        System.out.println("2) Medio Rischio e Medio Guadagno");
        System.out.println("3) Alto Rischio e Alto Guadagno");
        System.out.print("Selezione: ");
        int rischioInvestimento = tryMenu(1, 3);

        double rendimento = calcolaRendimento(soldiDaInvestire, durataInvestimento, rischioInvestimento * 10);
        double guadagno = rendimento - soldiDaInvestire;


        System.out.println("Investimento completato! Guadagno: " + guadagno);


        conto.guadagnoInvestimento(guadagno);

        if (guadagno > 0) {
            System.out.println("Congratulazioni, hai vinto! Guadagno positivo.");
        } else if (guadagno < 0) {
            System.out.println("Mi dispiace, hai perso l'investimento.");
        } else {
            System.out.println("Hai pareggiato, non hai guadagnato né perso.");
        }
    }
    

    private int tryMenu(int min, int max) {
        int scelta;
        while (true) {
            try {
                scelta = scanner.nextInt();
                if (scelta >= min && scelta <= max) {
                    return scelta;
                } else {
                    System.out.println("Selezione non valida, riprova.");
                }
            } catch (Exception e) {
                System.out.println("Errore: Inserire un numero valido!");
                scanner.next();
            }
        }
    }
}
