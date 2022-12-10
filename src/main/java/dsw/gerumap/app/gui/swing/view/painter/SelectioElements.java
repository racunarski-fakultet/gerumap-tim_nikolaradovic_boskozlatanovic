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

        g.fill(dashedStroke.createStrokedShape(shape));

    }

    @Override
    public boolean overlaps(Point point) {
        return false;
    }

    @Override
    public boolean contains(Point point) {
        return false;
    }

    @Override
    public void paintSelected(Graphics2D g) {

    }
}
