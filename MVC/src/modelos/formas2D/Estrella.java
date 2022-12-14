package modelos.formas2D;

import java.awt.Color;

/**
 * @author Luis Correa
 */
public class Estrella extends Forma2D {

  public Estrella(int x, int y, int ancho, int alto, Color color, Color colorBorde) {
    super(x, y, ancho, alto, color, colorBorde);
  }

  @Override
  public void construirForma() {
    double rE = ancho / 2;
    double alfa = Math.toRadians(36);
    double beta = Math.toRadians(18);
    double rI = rE * Math.sin(beta) / Math.sin(Math.PI - beta - alfa);
    double offset = Math.toRadians(-Math.PI / 2);
    double[] xD = new double[10];
    double[] yD = new double[10];
    for (int i = 0; i < 10; i+=2) {
      double aExt = alfa * i + offset;
      double aInt = alfa * (i + 1) + offset;
      xD[i] = x + rE * Math.cos(aExt);
      yD[i] = y + rE * Math.sin(aExt);
      xD[i + 1] = x + rI * Math.cos(aInt);
      yD[i + 1] = y + rI * Math.sin(aInt);
    }
    this.moveTo(xD[0], yD[0]);
    for (int i = 1; i < 10; i++) {
      this.lineTo(xD[i], yD[i]);
    }
    this.closePath();
  }

  @Override
  public Forma2D copiar(Color color) {
    return new Estrella(x, y, ancho, ancho, color, colorBorde);
  }
  
}
