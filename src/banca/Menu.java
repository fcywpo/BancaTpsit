package banca;

// Classe per il menu

import java.util.InputMismatchException;
import java.util.Scanner;

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
    System.out.println(
        "1. Deposita\n"
            + "2. Preleva\n"
            + "3. Mostra Saldo\n"
            + "4. Avanza di X mesi\n"
            + "5.Fai un investimento\n"
            + "6. Storico transazioni\n"
            + "0. logout");
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
