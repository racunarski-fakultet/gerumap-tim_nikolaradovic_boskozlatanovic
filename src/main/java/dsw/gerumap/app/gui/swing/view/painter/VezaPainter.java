package dsw.gerumap.app.gui.swing.view.painter;

import dsw.gerumap.app.gui.swing.elements.VezaElement;
import dsw.gerumap.app.mapRepository.implementation.Element;

import java.awt.*;
import java.awt.geom.Line2D;

public class VezaPainter extends DevicePainter{
    public VezaPainter(Element element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g) {
        BasicStroke stroke = new BasicStroke(element.getStroke());
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        shape = new Line2D.Float(element.getX(),element.getY(),((VezaElement)element).getX2(),((VezaElement)element).getY2());
    }
}
