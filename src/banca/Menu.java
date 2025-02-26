// Classe per il menu 
import java.util.Scanner;

// Classe per il menu interattivo
class Menu {
    private static Scanner scanner = new Scanner(System.in);

    public static short menuIniziale() {
        System.out.println("1. Accedi\n2. Esci");
        return getShortInput();
    }

    public static short menuBanca() {
        System.out.println("1. Deposita\n2. Preleva\n3. Mostra Saldo\n4. Avanza di X mesi\n5. Esci");
        return getShortInput();
    }

    private static short getShortInput() {
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