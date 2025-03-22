package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import banca.ContoBancario;
import banca.Investimento;

class InvestimentoTest {

  @Test
  void testGeneraRandom() {
    double min = 1.0, max = 10.0;
    double risultato = Investimento.GeneraRandom(max, min);

    assertTrue(
        risultato >= min && risultato <= max, "Il valore deve essere generato tra min e max");
  }

  @Test
  void testAvviaInvestimento() {
    ContoBancario conto = new ContoBancario(1000.0, 200.0);
    Investimento investimento = new Investimento();

    assertDoesNotThrow(() -> investimento.avviaInvestimento(conto));

    assertTrue(conto.getSaldoBanca() >= 0, "Il saldo non deve essere negativo");
    assertTrue(conto.getSaldoPortafoglio() >= 0, "Il saldo non deve essere negativo");
  }
}
