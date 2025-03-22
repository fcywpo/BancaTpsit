package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContoBancarioTest {

  @Test
  void testDeposita() {
    ContoBancario conto = new ContoBancario(100, 50);
    conto.deposita(30);

    assertEquals(130, conto.getSaldoBanca(), "Il saldo deve essere aumentato a 130 euro");
    assertEquals(20, conto.getSaldoBanca(), "Il saldo deve essere diminuito a 20 euro");
  }

  @Test
  void testPreleva() {
    ContoBancario conto = new ContoBancario(100, 50);
    boolean prelievoEffetuato = conto.preleva(40, false);

    assertTrue(PrelievoEffetuato, "Il prelievo deve essere riuscito");

    assertEquals(60, conto.getSaldoBanca(), "Il saldo deve essere diminuito a 60 euro");
    assertEquals(90, conto.getSaldoBanca(), "Il saldo deve essere aumentato a 90 euro");
  }
}
