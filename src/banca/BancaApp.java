package banca;

// Classe principale

import java.util.Scanner;


public class BancaApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
   
         // Gestione degli utenti
         Utente utente = null;
         ContoBancario conto = null;
 
         boolean esegui = true;
         while (esegui) {
             short scelta = Menu.menuIniziale();
             switch (scelta) {
                 case 1: 
                     GestioneUtenti.registraUtente();
                     break;
 
                 case 2: 
                     if (GestioneUtenti.login()) {
                         utente = GestioneUtenti.getUtenteAutenticato();
                         conto = utente.getConto();
                         menuBanca();
                     }
                     break;
 
                 case 3: 
                     System.out.println("Uscita dal sistema.");
                     esegui = false;
                     break;
 
                 default:
                     System.out.println("Scelta non valida.");
             }
         }
 
         scanner.close();
     }

        
      // Gestisce il menu bancario una volta che l'utente è loggato
    private static void menuBanca() {
        Utente utente = GestioneUtenti.getUtenteAutenticato();
        ContoBancario conto = utente.getConto();
        Investimento investimento = new Investimento();


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
                int mesi = Menu.getShortInput();  
                conto.avanzaMesi(mesi);
                break;
                case 5:
                     investimento.avviaInvestimento(conto);
                    break;
                case 0:
                    GestioneUtenti.logout();
                    esci=false;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
        
    }
}
