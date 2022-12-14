package pizarrafiguras;

import java.awt.Color;
import java.awt.Component;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import javax.swing.JColorChooser;
import javax.swing.SwingUtilities;
import modelos.formas2D.Circulo;
import modelos.formas2D.Estrella;
import modelos.formas2D.Forma2D;
import modelos.formas2D.Hexagono;
import modelos.formas2D.Rectangulo;
import modelos.formas2D.Triangulo;

/**
 *
 * @author Luis Correa
 */
public class OyenteFiguras extends MouseAdapter implements ActionListener {

    private final Figuras figuras;
    private final PanelPizarra panel;

    private Forma2D formaseleccionada;
    private boolean seleccionado;
    private final AffineTransform af;

    private Color relleno;
    private Color borde;
    private int ancho;
    private int alto;
    private int forma;

    public OyenteFiguras(Figuras figuras, PanelPizarra panel) {
        this.figuras = figuras;
        this.panel = panel;
        this.relleno = Color.WHITE;
        this.borde = Color.BLACK;
        this.af = new AffineTransform();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component origen = (Component) e.getSource();

        switch (origen.getName()) {
            case "aceptar": {
                this.ancho = panel.getCampos()[0].trim().isEmpty() ? 0 : Integer.parseInt(panel.getCampos()[0].trim());
                this.alto = panel.getCampos()[1].trim().isEmpty() ? 0 : Integer.parseInt(panel.getCampos()[1].trim());
                break;
            }

            case "circulo": {
                this.forma = Forma2D.CIRCULO;
                this.panel.gettextAlto().setEnabled(false);
                break;
            }

            case "estrella": {
                this.forma = Forma2D.ESTRELLA;
                this.panel.gettextAlto().setEnabled(false);
                break;
            }

            case "hexagono": {
                this.forma = Forma2D.HEXAGONO;
                this.panel.gettextAlto().setEnabled(false);
                break;
            }

            case "rectangulo": {
                this.forma = Forma2D.RECTANGULO;
                this.panel.gettextAlto().setEnabled(true);
                break;
            }

            case "triangulo": {
                this.forma = Forma2D.TRIANGULO;
                this.panel.gettextAlto().setEnabled(false);
                break;
            }

            case "relleno": {
                this.relleno = JColorChooser.showDialog(panel, "Seleccione un color", Color.WHITE);
                panel.getpanelVistaRelleno().setBackground(relleno);
                break;
            }

            case "borde": {
                this.borde = JColorChooser.showDialog(panel, "Seleccione un color", Color.BLACK);
                panel.getpanelVistaContorno().setBackground(borde);
                break;
            }

            case "arriba": {
                moverFigura(1);
                break;
            }

            case "abajo": {
                moverFigura(2);
                break;
            }

            case "izquierda": {
                moverFigura(3);
                break;
            }

            case "derecha": {
                moverFigura(4);
                break;
            }

            case "rotar": {
                rotar();
                break;
            }
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            switch (forma) {
                case Forma2D.CIRCULO: {
                    if (ancho != 0) {
                        figuras.add(new Circulo(e.getX() - ancho, e.getY() - ancho, ancho * 2, ancho * 2, relleno, borde));
                    }
                    break;
                }

                case Forma2D.ESTRELLA: {
                    figuras.add(new Estrella(e.getX(), e.getY(), ancho, ancho, relleno, borde));
                    break;
                }

                case Forma2D.HEXAGONO: {
                    figuras.add(new Hexagono(e.getX() - ancho, e.getY() - ancho, ancho, ancho, relleno, borde));
                    break;
                }

                case Forma2D.RECTANGULO: {
                    figuras.add(new Rectangulo(e.getX() - ancho, e.getY() - alto, ancho * 2, alto * 2, relleno, borde));
                    break;
                }

                case Forma2D.TRIANGULO: {
                    figuras.add(new Triangulo(e.getX() - ancho, e.getY() - ancho, ancho, ancho, relleno, borde));
                    break;
                }
            }
            panel.getPanelCentro().setFocusable(true);
            panel.getPanelCentro().repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            for (Forma2D figura : figuras) {
                if (figura.contiene(e.getX(), e.getY())) {
                    System.out.println("Figura seleccionada correctamente");
                    formaseleccionada = figura;
                    break;
                }
            }
        }
    }

    private void moverFigura(int opcion) {
        int dX = 0;
        int dY = 0;

        switch (opcion) {
            case 1: {
                dY -= 5;
                break;
            }

            case 2: {
                dY += 5;
                break;
            }
            case 3: {
                dX -= 5;
                break;
            }

            case 4: {
                dX += 5;
                break;
            }
        }

        af.setToTranslation(dX, dY);
        formaseleccionada.setShape(formaseleccionada.createTransformedShape(af));
        panel.getPanelCentro().repaint();
    }

    private void rotar() {
        if (!panel.getCampos()[2].isEmpty() && formaseleccionada != null) {
            double angulo = Double.parseDouble(panel.getCampos()[2]);
            int x = formaseleccionada.getX();
            int y = formaseleccionada.getY();
            AffineTransform at = new AffineTransform();

            at.setToTranslation(0, 0);
            at.rotate(angulo);
            at.translate(-x, -y);
            Shape s = at.createTransformedShape(formaseleccionada);
            formaseleccionada.setShape(s);
            panel.getPanelCentro().repaint();
        }
    }
    

}
