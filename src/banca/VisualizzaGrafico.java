package banca;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class VisualizzaGrafico extends JFrame {

  private List<String> timestamps;
  private List<Double> saldoTotale;

  public VisualizzaGrafico(
      List<String> storicoTransazioni, double saldoInizialeBanca, double saldoInizialePortafoglio) {
    this.timestamps = new ArrayList<>();
    this.saldoTotale = new ArrayList<>();

    parseStorico(storicoTransazioni, saldoInizialeBanca + saldoInizialePortafoglio);

    setTitle("Money Changes Over Time");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
  }

  private void parseStorico(List<String> storico, double saldoTotaleIniziale) {
    timestamps.add("Start");
    saldoTotale.add(saldoTotaleIniziale);
    double saldoAttuale = saldoTotaleIniziale;

    for (String transazione : storico) {
      timestamps.add(transazione);

      double variazione = extractImporto(transazione);
      if (transazione.contains("Deposito di") || transazione.contains("Guadagno investimento")) {
        saldoAttuale += variazione;
      } else if (transazione.contains("Prelievo di")
          || transazione.contains("Perdita investimento")) {
        saldoAttuale -= variazione;
      }

      saldoTotale.add(saldoAttuale);
    }
  }

  private double extractImporto(String transazione) {
    String[] words = transazione.split(" ");
    try {
      return Double.parseDouble(words[words.length - 1]);
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    int width = getWidth();
    int height = getHeight();
    int padding = 60;
    int graphWidth = width - 2 * padding;
    int graphHeight = height - 2 * padding;

    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, width, height);

    g2d.setColor(Color.LIGHT_GRAY);
    for (int i = 1; i <= 5; i++) {
      int y = height - padding - i * (graphHeight / 5);
      g2d.drawLine(padding, y, width - padding, y);
    }

    g2d.setColor(Color.BLACK);
    g2d.drawLine(padding, height - padding, width - padding, height - padding);
    g2d.drawLine(padding, padding, padding, height - padding);

    g2d.setFont(new Font("SansSerif", Font.PLAIN, 12));
    g2d.drawString("Transazioni", width / 2 - 30, height - 10);
    g2d.drawString("Saldo", 10, height / 2);

    if (timestamps.isEmpty()) return;

    int pointCount = timestamps.size();
    int stepX = Math.max(1, graphWidth / (pointCount - 1));

    double maxY = saldoTotale.stream().max(Double::compare).orElse(1.0) * 1.1;
    double minY = saldoTotale.stream().min(Double::compare).orElse(0.0) * 0.9;
    double rangeY = maxY - minY;

    int lastX = padding;
    int lastY = height - padding - (int) (((saldoTotale.get(0) - minY) / rangeY) * graphHeight);

    for (int i = 1; i < pointCount; i++) {
      int x = padding + i * stepX;
      int y = height - padding - (int) (((saldoTotale.get(i) - minY) / rangeY) * graphHeight);

      g2d.setColor(Color.BLUE);
      g2d.drawLine(lastX, lastY, x, y);
      g2d.fillOval(x - 4, y - 4, 8, 8);

      g2d.setColor(Color.BLACK);
      g2d.drawString(String.format("%.2f", saldoTotale.get(i)), x - 15, y - 10);

      lastX = x;
      lastY = y;
    }
  }
}
