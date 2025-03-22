package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import banca.ContoBancario;
import banca.Investimento;

class InvestimentoTest {

  @Test
  void testGeneraRandom() {
    double valore = Investimento.GeneraRandom(10, 5);
    assertTrue(valore >= 5 && valore <= 10, "Il valore generato deve essere compreso tra 5 e 10");
  }

  @Test
  void testAvviaInvestimento() {
    ContoBancario conto = new ContoBancario(1000.0, 500.0);
    Investimento investimento = new Investimento();

    double saldoIniziale = conto.getSaldoBanca();
    investimento.avviaInvestimento(conto);

    assertNotEquals(
        saldoIniziale,
        conto.getSaldoBanca(),
        "Il saldo del conto deve cambiare dopo l'investimento");
  }
}
