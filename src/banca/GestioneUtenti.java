package banca;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

  public static void salvaDatiSuFile(String nomeFile) {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeFile))) {
      out.writeObject(utenti);
      System.out.println("Dati salvati con successo.");
    } catch (IOException e) {
      System.out.println("Errore nel salvataggio dei dati.");
    }
  }

  @SuppressWarnings("unchecked")
  public static void caricaDatiDaFile(String nomeFile) {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeFile))) {
      utenti = (HashMap<String, Utente>) in.readObject();
      System.out.println("Dati caricati con successo.");

    } catch (IOException | ClassNotFoundException e) {
      System.out.println("Nessun dato precedente trovato, avvio con nuova banca.");
    }
  }

  public static boolean login(String nome, String password) {
    Utente utente = utenti.get(nome);
    if (utente != null && utente.verificaPassword(password)) {
      utenteAutenticato = utente;
      return true;
    }
    return false;
  }

  public static boolean registraUtente(String nome, String password) {
    if (utenti.containsKey(nome)) {
      return false; // Utente già esistente
    }
    Utente nuovoUtente = new Utente(nome, password, 1000, 100);
    utenti.put(nome, nuovoUtente);
    salvaDatiSuFile("dati_banca.dat"); // Salva automaticamente l'utente
    return true;
  }
}
