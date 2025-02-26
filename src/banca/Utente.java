// Classe per la gestione dell'utente
class Utente {
    private String nome;
    private String password;

    public Utente(String nome, String password) {
        this.nome = nome;
        this.password = password;
    }

    public boolean verificaPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public String getNome() {
        return nome;
    }
}