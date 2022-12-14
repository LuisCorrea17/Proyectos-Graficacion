package modelos.formas2D;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 * @author Luis Correa
 */
public class Rectangulo extends Forma2D {

  public Rectangulo(int x, int y, int ancho, int alto, Color color, Color colorBorde) {
    super(x, y, ancho, alto, color, colorBorde);
    this.alto = alto;
  }

  @Override
  public void construirForma() {
    Rectangle2D rectangulo = new Rectangle2D.Double(x, y, ancho, alto);
    this.setShape(rectangulo);
  }

  @Override
  public Forma2D copiar(Color color) {
    return new Rectangulo(x, y, ancho, alto, color, colorBorde);
  }
  
}
