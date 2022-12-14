package pizarrafiguras;

import java.awt.Graphics;
import java.util.ArrayList;
import modelos.formas2D.Forma2D;

/**
 *
 * @author Luis Correa
 */
public class Figuras extends ArrayList<Forma2D> {

    public void dibujar(Graphics g) {
        for (Forma2D figuras : this) {
            figuras.dibujar(g);
        }
    }

}
