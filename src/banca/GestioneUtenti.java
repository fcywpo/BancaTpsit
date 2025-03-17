package banca;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GestioneUtenti {
    private static Map<String, Utente> utenti = new HashMap<>();
    private static Utente utenteAutenticato = null;
    private static Scanner scanner = new Scanner(System.in);

    // Metodo per registrare un nuovo utente
    public static void registraUtente() {
        System.out.print("Inserisci il nome utente: ");
        String nome = scanner.next();
        if (utenti.containsKey(nome)) {
            System.out.println("Errore: Nome utente già esistente.");
            return;
        }
        System.out.print("Inserisci la password: ");
        String password = scanner.next();

        // Inizializza ogni utente con saldo predefinito (modificabile)
        Utente nuovoUtente = new Utente(nome, password, 1000, 100);
        utenti.put(nome, nuovoUtente);
        System.out.println("Utente registrato con successo!");
    }

    // Metodo per il login
    public static boolean login() {
        System.out.print("Inserisci il nome utente: ");
        String nome = scanner.next();
        System.out.print("Inserisci la password: ");
        String password = scanner.next();

        Utente utente = utenti.get(nome);
        if (utente != null && utente.verificaPassword(password)) {
            utenteAutenticato = utente;
            System.out.println("Login effettuato con successo! Benvenuto, " + nome + "!");
            return true;
        } else {
            System.out.println("Credenziali errate.");
            return false;
        }
    }

    // Metodo per il logout
    public static void logout() {
        if (utenteAutenticato != null) {
            System.out.println("Logout effettuato per " + utenteAutenticato.getNome());
            utenteAutenticato = null;
        } else {
            System.out.println("Nessun utente è attualmente loggato.");
        }
    }

    // Metodo per ottenere l'utente attualmente loggato
    public static Utente getUtenteAutenticato() {
        return utenteAutenticato;
    }
}

