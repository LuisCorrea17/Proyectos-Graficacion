package pizarrafiguras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Luis Correa
 */
public class Pizarron extends JPanel {

    private final Figuras figuras;

    public Pizarron() {
        this(new Figuras());
    }

    public Pizarron(Figuras figuras) {
        this.figuras = figuras;
        this.setBackground(Color.WHITE);
    }

    public void addEventos(OyenteFiguras oyente) {
        this.addMouseListener(oyente);
        this.addMouseMotionListener(oyente);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        figuras.dibujar(g);
    }

}
