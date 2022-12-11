package dsw.gerumap.app.gui.swing.view.painter;

import dsw.gerumap.app.gui.swing.elements.PojamElement;
import dsw.gerumap.app.mapRepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Ellipse2D;

@Getter
@Setter
public class SelectioElements extends  DevicePainter{

    Stroke dashedStroke;

    public SelectioElements(Element element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g) {

        dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, new float[]{10}, 0);
        g.setColor(Color.BLACK);
        g.setStroke(dashedStroke);
        g.draw(shape);
    }

    @Override
    public boolean overlaps(Point point) {
        return false;
    }

    @Override
    public boolean contains(Point point) {
        return shape.contains(point);
    }

    @Override
    public void paintSelected(Graphics2D g) {

    }
}
