package banca;

import java.io.Serializable;

// Classe per la gestione dell'utente
class Utente implements Serializable {
  private String nome;
  private String password;
  private ContoBancario conto;

  public Utente(String nome, String password, double saldoBanca, double saldoPortafoglio) {
    this.nome = nome;
    this.password = password;
    this.conto = new ContoBancario(saldoBanca, saldoPortafoglio);
  }

  public boolean verificaPassword(String inputPassword) {
    return this.password.equals(inputPassword);
  }

  public String getNome() {
    return nome;
  }

  public ContoBancario getConto() {
    return conto;
  }
}
