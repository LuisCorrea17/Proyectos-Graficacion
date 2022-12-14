
package pizarrafiguras;

import javax.swing.JFrame;

/**
 *
 * @author Luis Correa
 */
public class PiazarraFiguras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Figuras figuras = new Figuras();
        JFrame f = new JFrame("Pizarra de Figuras");
        PanelPizarra panel = new PanelPizarra(figuras);
        OyenteFiguras oyente = new OyenteFiguras(figuras, panel);
        panel.addEventos(oyente);
        f.setSize(625, 600);
        f.setLocation(100, 100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(panel);
        f.setVisible(true);
    }
    
}
