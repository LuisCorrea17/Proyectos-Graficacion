package modelos.formas2D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import modelos.Dibujable;
import static java.awt.geom.PathIterator.*;

/**
 * @author Oswaldo Daniel Cruz Cruz
 */
public abstract class Forma2D extends Path2D.Float implements Dibujable {

  public static final int CIRCULO = 1;
  public static final int ESTRELLA = 2;
  public static final int HEXAGONO = 3;
  public static final int RECTANGULO = 4;
  public static final int TRIANGULO = 5;

  protected int x;
  protected int y;
  protected int ancho;
  protected int alto;
  protected Color color;
  protected Color colorBorde;

  protected Forma2D(int x, int y, int ancho, int alto, Color color, Color colorBorde) {
    this.x = x;
    this.y = y;
    this.ancho = ancho;
    this.alto = alto;
    this.color = color;
    this.colorBorde = colorBorde;
    construirForma();
  }

  public abstract void construirForma();

  public abstract Forma2D copiar(Color color);

  public boolean contiene(int pX, int pY) {
    return this.distance(pX, pY) <= ancho;
  }

  private double distance(double px, double py) {
    px -= getX();
    py -= getY();
    return Math.sqrt(px * px + py * py);
  }

  public void setShape(Shape s) {
    this.reset();
    this.append(s.getPathIterator(null), true);
    Rectangle rect = this.getBounds();
    this.x = rect.x;
    this.y = rect.y;
    this.ancho = rect.width;
    this.alto = rect.height;
  }

  @Override
  public void dibujar(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(color);
    g2.fill(this);
    g2.setColor(getColorBorde());
    g2.setStroke(new BasicStroke(2));
    g2.draw(this);
    g2.setStroke(new BasicStroke(1));
  }
  
  public ArrayList<Point2D.Float> getPuntos() {
    ArrayList<Point2D.Float> puntos = new ArrayList<>();
    PathIterator path = this.getPathIterator(null);
    while (!path.isDone()) {
      float[] coords = new float[6];
      int tipo = path.currentSegment(coords);
      float[] xP = {coords[0], coords[2], coords[4]};
      float[] yP = {coords[1], coords[3], coords[5]};

      switch (tipo) {
        case SEG_MOVETO -> {
          puntos.add(new Point2D.Float(xP[0], yP[0]));
        }

        case SEG_LINETO -> {
          puntos.add(new Point2D.Float(xP[0], yP[0]));
        }

        case SEG_QUADTO -> {
          puntos.add(new Point2D.Float(xP[0], yP[0]));
          puntos.add(new Point2D.Float(xP[1], yP[1]));
        }

        case SEG_CUBICTO -> {
          puntos.add(new Point2D.Float(xP[0], yP[0]));
          puntos.add(new Point2D.Float(xP[1], yP[1]));
          puntos.add(new Point2D.Float(xP[2], yP[2]));
        }

        case SEG_CLOSE -> {
          puntos.add(puntos.get(0));
        }
      }
      path.next();
    }
    return puntos;
  }
  
  public void setPuntos(ArrayList<Point2D.Float> puntos) {
    ArrayList<Integer> tipos = new ArrayList<>();
    PathIterator path = this.getPathIterator(null);

    while (!path.isDone()) {
      double[] coords = new double[6];
      tipos.add(path.currentSegment(coords));
      path.next();
    }

    this.reset();
    int i = 0;
    for (int tipo : tipos) {
      Point2D.Float p1 = puntos.get(i++);
      switch (tipo) {
        case SEG_MOVETO -> {
          this.moveTo(p1.x, p1.y);
        }

        case SEG_LINETO -> {
          this.lineTo(p1.x, p1.y);
        }

        case SEG_QUADTO -> {
          Point2D.Float p2 = puntos.get(i++);
          this.quadTo(p1.x, p1.y, p2.x, p2.y);
        }

        case SEG_CUBICTO -> {
          Point2D.Float p2 = puntos.get(i++);
          Point2D.Float p3 = puntos.get(i++);
          this.curveTo(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
        }

        case SEG_CLOSE -> {
          this.closePath();
        }
      }
    }
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getAncho() {
    return ancho;
  }

  public void setAncho(int ancho) {
    this.ancho = ancho;
  }

  public int getAlto() {
    return alto;
  }

  public void setAlto(int alto) {
    this.alto = alto;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Color getColorBorde() {
    return colorBorde;
  }

  public void setColorBorde(Color colorBorde) {
    this.colorBorde = colorBorde;
  }

}
