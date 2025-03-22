package banca;

import java.util.Random;
import java.util.Scanner;

// Classe per la gestione degli investimenti
class Investimento {
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    public static double GeneraRandom(double max, double min) {
        double valore = min + (Math.random() * (max - min));
        System.out.println("Generato valore random: " + valore); // Debug
        return Math.round(valore * 100) / 100.0;
    }

    public void avviaInvestimento(ContoBancario conto) {
        double soldiDaInvestire = 0;
        boolean inputValido = false;

        while (!inputValido) {
            try {
                System.out.print("Inserisci quanti soldi vuoi investire --> ");
                soldiDaInvestire = scanner.nextDouble();
                scanner.nextLine(); // Consuma il newline per evitare problemi

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
        int mesi = 0;
        switch (durataInvestimento) {
            case 1:
                mesi = 3;
                break;
            case 2:
                mesi = 6;
                break;
            case 3:
                mesi = 12;
                break;
        }

        System.out.println("1) Basso Rischio e Basso Guadagno");
        System.out.println("2) Medio Rischio e Medio Guadagno");
        System.out.println("3) Alto Rischio e Alto Guadagno");
        System.out.print("Selezione: ");
        int rischioInvestimento = tryMenu(1, 3);

        double guadagno = 0;
        int NGenerato = random.nextInt(10); // Genera un numero tra 0 e 9
        System.out.println("Numero generato: " + NGenerato); // Debug

        switch (rischioInvestimento) {
            case 1:
                if (NGenerato <= 8) {
                    System.out.println("Hai vinto!");
                    guadagno = (soldiDaInvestire / 100) * GeneraRandom(25, 1) * mesi;
                } else {
                    System.out.println("Hai perso!");
                    guadagno = -soldiDaInvestire * (mesi / 3.0);
                }
                break;
            case 2:
                if (NGenerato <= 5) {
                    System.out.println("Hai vinto!");
                    guadagno = (soldiDaInvestire / 100) * GeneraRandom(50, 15) * mesi;
                } else {
                    System.out.println("Hai perso!");
                    guadagno = -soldiDaInvestire * (mesi / 3.0);
                }
                break;
            case 3:
                if (NGenerato <= 2) {
                    System.out.println("Hai vinto!");
                    guadagno = (soldiDaInvestire / 100) * GeneraRandom(100, 40) * mesi;
                } else {
                    System.out.println("Hai perso!");
                    guadagno = -soldiDaInvestire * (mesi / 3.0);
                }
                break;
        }

        System.out.println("Guadagno calcolato: " + guadagno); // Debug
        System.out.println("Investimento completato! Guadagno: " + guadagno);
        conto.guadagnoInvestimento(guadagno);
        conto.avanzaMesi(mesi); // Aggiunge 100 euro per mese trascorso
    }

    private int tryMenu(int min, int max) {
        int scelta;
        while (true) {
            try {
                scelta = scanner.nextInt();
                scanner.nextLine(); // Consuma il newline
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