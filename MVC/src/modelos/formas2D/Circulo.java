package modelos.formas2D;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * @author Luis Correa
 */
public class Circulo extends Forma2D {

  private Shape circulo;
  private final AffineTransform af;
  
  public Circulo(int x, int y, int ancho, int alto, Color color, Color colorBorde) {
    super(x, y, ancho, alto,color, colorBorde);
    af = new AffineTransform();
  }

  @Override
  public void construirForma() {
    circulo = new Ellipse2D.Float(x, y, ancho, ancho);
    this.setShape(circulo);
  }

  @Override
  public Forma2D copiar(Color color) {
    return new Circulo(x, y, ancho, ancho, color, colorBorde);
  }
  
}
