package modelos.formas2D;

import java.awt.Color;

/**
 * @author Luis Correa
 */
public class Triangulo extends Forma2D {

  public Triangulo(int x, int y, int ancho, int alto, Color color, Color colorBorde) {
    super(x, y, ancho, alto, color, colorBorde);
  }

  @Override
  public void construirForma() {
    int d = ancho / 2;
    int[] xD = {2 * d, 4 * d,     0};
    int[] yD = {    0, 4 * d, 4 * d};
    
    this.moveTo(x + xD[0], y + yD[0]);
    this.lineTo(x + xD[1], y + yD[1]);
    this.lineTo(x + xD[2], y + yD[2]);
    this.closePath();
  }

  @Override
  public Forma2D copiar(Color color) {
    return new Triangulo(x, y, ancho, alto, color, colorBorde);
  }
  
}
