/*
 *@author  alvise sacconato
 *@author2 alessandro zago
 *@version 1.0
 *@description tramite questo programma si puo' gestire una banca monoutente con opzioni di prelievo, deposito,
 *			   investimento con diversi tipi di durata ovvero: breve media e lunga e con diversi tipi di rischio,
 *			   ovvero: basso rischio/guadagno, medio rischio/guadagno e alto rischio/guadagno, permette di avanzare di tempo (mesi),
 *			   e di visualizzare le info del conto corrente.
 */

package banca;
import java.util.Scanner;

// -- COSE DA FARE -- // 

// NEL PROGRAMMA MANCA DA FARE TUTTA LA PARTE DEGLI INVESTIMENTI
// INSERIRE TUTTI I CONTROLLI PER EVITARE PROBLEMI DI INPUT
// INSERIRE TUTTI I TRY E I CATCH PER EVITARE IL BLOCCO DEL PROGRAMMA
// USARE GLI INTERS E I FORMATTERS PER LA LEGGIBILITA' DEL CODICE E LE ALTRE COSE RICHIESTE SU CLASSROOM


// Volendo si puo' aggiungere che prima di fare "Accedi al conto corrente" devi registrati con: nome, cognome, email e password.
// Volendo si puo' farla multiutente (si puo' farlo anche con solo i "metodi" come stiamo facendo ora oppure con gli oggetti.
// Volendo trasformare questo programma con gli oggetti.

// -- FINE COSE DA FARE -- //

public class main {
	
	// Menu iniziale di avvio del programma
	public static short MenuIniziale() {
		Scanner tastiera = new Scanner(System.in);
		
		short selezione;

		System.out.println("BENVENUTO NELLA BANCA!");
		System.out.println("Cosa desideri fare?\n");
		
		System.out.println("1)Accedi al conto corrente");
		System.out.println("0)Esci");
		
		System.out.print("Selezione: \n");
		selezione = tastiera.nextShort();
		
		return(selezione);
	}
	
	// Menu per fare le operazioni in banca
	public static short MenuBanca(String nome) {
		Scanner tastiera = new Scanner(System.in);
		
		short selezione;
		
		System.out.println("Ciao " + nome + " Cosa desideri fare?");
		System.out.println("1)Visualizza stato del conto e del portafoglio");
		System.out.println("2)Deposita Soldi");
		System.out.println("3)Preleva Soldi");
		System.out.println("4)Fai un investimento");
		System.out.println("5)Avanza di X Mesi \n");
		
		System.out.println("0)Esci dalla banca");
		
		System.out.print("Selezione: \n");
		selezione = tastiera.nextShort();
		
		return(selezione);
	}
	
	// Stampa informazioni sul conto corrente dell'utente
	public static void InfoConto(String nome, double Soldi[], int ContatoreMesi) {
		System.out.println("Nome: " + nome);
		System.out.println("Saldo in banca: " + Soldi[0]);
		System.out.println("Saldo nel portafoglio: " + Soldi[1]);
		System.out.println("Mesi trascorsi dall'appertura del conto: " + ContatoreMesi + "\n");
	}
	
	// Genera soldi iniziali in banca in modo random (i soldi sono compresi tra 5 e 75)
	public static double GeneraRandomSoldiBanca() {
        double max = 75.0;
        double min = 5.0;
      //  double gen = min + (Math.random() * (max - min)); // Genera il numero random
      //  double random = Math.round(gen * 100.0) / 100.0; // Lascia solo 2 cifre decimali
        
        double random = Math.round((min + (Math.random() * max)) * 100 ) / 100;
 		
		return random;
	}
	
	// Metodo per depositare i soldi nel conto corrente
	public static void DepositoSoldi(double Soldi[]) {
		Scanner tastiera = new Scanner(System.in);

		System.out.println("Quanti soldi vuoi depositare? -->");
		double SoldiInseriti = tastiera.nextDouble();
		
		// Verifica se l'utente ha abbastanza soldi nel portafoglio per poi depositarli nel conto corrente
		if(SoldiInseriti <= Soldi[1]) {
			Soldi[0] = Soldi[0] + SoldiInseriti;
			Soldi[1] = Soldi[1] - SoldiInseriti;
			System.out.println("Hai depositato: " + SoldiInseriti + "€");
		}else {
			System.out.println("Non hai abbastanza soldi!");
		}	
	}
	
	// Metodo per prelevare i soldi dal conto corrente
	public static void PrelievoSoldi(double Soldi[]) {
		Scanner tastiera = new Scanner(System.in);
	
		System.out.println("Quanti soldi vuoi ritirare? -->");
		double SoldiInseriti = tastiera.nextDouble();
		
		// Verifica se l'utente ha abbastanza soldi nel conto corrente per poi portarli nel portafoglio
		if(SoldiInseriti <= Soldi[0]) {
			Soldi[1] = Soldi[1] + SoldiInseriti;
			Soldi[0] = Soldi[0] - SoldiInseriti;
			System.out.println("Hai ritirato: " + SoldiInseriti + "€");
		}else {
			System.out.println("Non hai abbastanza soldi!");
		}
	}

	// Metodo che permette di andare avanti di un mese nella vita del conto corrente
	public static int AvanzaMesi(int ContatoreMesi, double Soldi[]) {
		Scanner tastiera = new Scanner(System.in);
		
		System.out.println("Di quanti mesi vuoi avanzare --> ");
		short MesiDaAggiungere = tastiera.nextShort();
		
		ContatoreMesi = ContatoreMesi + MesiDaAggiungere;
		
		Soldi[1] = Soldi[1] + (100*MesiDaAggiungere); // serve per aggiungere ogni mese 100 euro.
		
		System.out.println("Hai avanzato di: " + MesiDaAggiungere + "!");
	
		return ContatoreMesi;
	}
	
	// ANCORA DA FARE
	
	public static void Investimento(double Soldi[]) {
		Scanner tastiera = new Scanner(System.in);
		
		short DurataInvestimento, RischioInvestimento;

		System.out.println("1)Investimento di Breve durata");
		System.out.println("2)Investimento di Media durata");
		System.out.println("3)Investimento di Lunga durata");
		System.out.println("4)Selezione: ");
		
		DurataInvestimento = tastiera.nextShort();
		
		System.out.println("1)Investimento di Basso Rischio e Basso Guadagno");
		System.out.println("2)Investimento di Medio Rischio e Medio Guadagno");
		System.out.println("3)Investimento di Alto Rischio e Alto Guadagno");
		System.out.println("4)Selezione: ");
		
		RischioInvestimento = tastiera.nextShort();
	}
	
	// FINE ANCORA DA FARE
	
	public static void main(String[] args) {
		Scanner tastiera = new Scanner(System.in);
		
		// Inizializzazione variabili globali
		
		String nome, password;
		
		double Soldi[] = new double[2]; // [0] = SOLDI BANCA - [1] = SOLDI PORTAFOGLIO
		Soldi[1] = 100; 				// Inizializza soldi nel portafoglio 
		Soldi[0] = GeneraRandomSoldiBanca(); // Genera soldi random nel conto corrente

		int ContatoreMesi = 0;
		
		// Esecuzione di tutto il programma
		while(true) {

			short selezione = MenuIniziale(); // Mostra il Menu iniziale di avvio del programma

			switch(selezione) {
				case 1:{
					// Login dell'utente
					
					System.out.println("Inserisci nome utente: ");
					nome = tastiera.nextLine();
					
					System.out.println("Inserisci la password: ");
					password = tastiera.nextLine();
					
					System.out.println("ATTENZIONE!! ricordati la password! \nPremi un tasto per continuare... \n");
					
					
					boolean esci = false; // Serve per uscire dal while e quindi da questo sotto_case e tornare nel case principale
					
					// Ciclo per le operazioni nel conto corrente
					while(!esci) {
						
						short selezione2 = MenuBanca(nome); // Assegna ad una variabile l'operazione da fare scelta nel metodo menu + Stampa il menu
						
						if(selezione2 != '0') { // Serve per non far stampare le info nel conto nel case 0 che e' dedicato alla chiusura del sottomenu
							InfoConto(nome, Soldi, ContatoreMesi);
						}
						
						// Gestione delle opzioni visionate nel menu
						switch(selezione2) {
							case 1:{
								InfoConto(nome, Soldi, ContatoreMesi);
								break;
							}
							case 2:{
								DepositoSoldi(Soldi);
								break;
							}
							case 3:{
								PrelievoSoldi(Soldi);
								break;
							}
							case 4:{
								Investimento(Soldi); // Ancora da fare
								break;
							}
							case 5:{
								ContatoreMesi = AvanzaMesi(ContatoreMesi, Soldi);
								break;
							}
							case 0:{
								// Fa uscire da questo switch (sotto menu) (gestione conto corrente)
								
							    System.out.println("Sei sicuro di voler uscire? (Y = si, N = no)");
								
							    String scelta = tastiera.next();
				
							    if (scelta.equalsIgnoreCase("Y")) {
							        System.out.println("Sei uscito!");
									esci = true;
							    }else {
							        System.out.println("Hai scelto di non uscire!");
							    }
							    break;
							}
						}
					}
					break;
				}
				case 2:{
					// Fa chiudere il programma
					
				    System.out.println("Sei sicuro di voler uscire? (Y = si, N = no)");
	
				    String scelta = tastiera.next();
	
				    if (scelta.equalsIgnoreCase("Y")) {
				        System.out.println("Sei uscito!");
				        System.exit(0); // Chiude il programma
				    }else {
				        System.out.println("Hai scelto di non uscire!");
				    }
	
				    break;
				}
			}
		}
	}
}
