package dsw.gerumap.app.gui.swing.view.painter;
import dsw.gerumap.app.gui.swing.elements.PojamElement;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.mapRepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Ellipse2D;
@Getter
@Setter
public class PojamPainter extends DevicePainter{
    private Shape ln;
    public PojamPainter(Element element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g) {
        BasicStroke stroke = new BasicStroke(element.getStroke());
        g.setStroke(stroke);
        g.setColor(Color.gray);
        g.drawString(element.getName(), element.getX()+20, element.getY()+10);
        ln = new Ellipse2D.Float(element.getX(), element.getY(), ((PojamElement)element).getWidth(),((PojamElement)element).getHeight());


        g.draw(ln);
    }
}
