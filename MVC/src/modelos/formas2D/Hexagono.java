package modelos.formas2D;

import java.awt.Color;

/**
 * @author Luis Correa
 */
public class Hexagono extends Forma2D {

  public Hexagono(int x, int y, int ancho, int alto, Color color, Color colorBorde) {
    super(x, y, ancho, alto, color, colorBorde);
  }

  @Override
  public void construirForma() {
    int d = ancho / 2;
    int[] xD = {d, 3 * d, 4 * d, 3 * d,     d,     0};
    int[] yD = {0,     0, 2 * d, 4 * d, 4 * d, 2 * d};
    
    this.moveTo(x + xD[0], y + yD[0]);
    for (int i = 1; i < xD.length; i++) {
      this.lineTo(x + xD[i], y + yD[i]);
    }
    this.closePath();
  }

  @Override
  public Forma2D copiar(Color color) {
    return new Hexagono(x, y, ancho, alto, color, colorBorde);
  }
  
}
