package banca;

// Classe per il menu 
import java.util.Scanner;
import java.util.InputMismatchException;

// Classe per il menu interattivo
class Menu {
    private static Scanner scanner = new Scanner(System.in);

     
     public static short menuIniziale() {
        System.out.println("\n--- Menu Iniziale ---");
        System.out.println("1. Registrati");
        System.out.println("2. Accedi");
        System.out.println("3. Esci");
        System.out.print("Scelta: ");
        return getShortInput();
    }

    public static short menuBanca() {
        System.out.println("1. Deposita\n2. Preleva\n3. Mostra Saldo\n4. Avanza di X mesi\n5.Fai un investimento\n6. Storico transazioni\n0. logout");
        return getShortInput();
    }

    protected static short getShortInput() {
        while (true) {
            try {
                return scanner.nextShort();
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Riprova.");
                scanner.next();
            }
        }
    }

    public static double getDoubleInput() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Riprova.");
                scanner.next();
            }
        }
    }
}