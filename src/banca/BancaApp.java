package banca;

// Classe principale

import java.util.Scanner;


public class BancaApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Utente utente = new Utente("Pippo", "1234");
        ContoBancario conto = new ContoBancario(1000, 100);
        Investimento investimento = new Investimento();

       // Menu.menuIniziale();

        System.out.print("Inserisci password: ");
        String password = scanner.nextLine();

        if (!utente.verificaPassword(password)) {
            System.out.println("Accesso negato.");
            return;
        }

        boolean esci = false;
        while (!esci) {
            short scelta = Menu.menuBanca();
            switch (scelta) {
                case 1:
                    System.out.print("Importo da depositare: ");
                    conto.deposita(Menu.getDoubleInput());
                    break;
                case 2:
                    System.out.print("Importo da prelevare: ");
                    conto.preleva(Menu.getDoubleInput());
                    break;
                case 3:
                    conto.mostraSaldo();
                    break;
                case 4:
                    System.out.print("Quanti mesi vuoi far avanzare? ");
                    int mesi = Menu.menuBanca();
                    conto.avanzaMesi(mesi);
                    break;
                case 5:
                     investimento.avviaInvestimento(conto);
                    break;
                case 0:
                    esci = true;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
        scanner.close();
    }
}
