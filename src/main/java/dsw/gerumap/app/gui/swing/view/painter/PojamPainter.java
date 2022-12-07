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

    public PojamPainter(Element element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g) {


        BasicStroke stroke = new BasicStroke(element.getStroke());
        g.setStroke(stroke);
        g.setColor(Color.BLUE);
        g.drawString(element.getName(),  getElement().getX()+33, getElement().getY()+30 );
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        shape = new Ellipse2D.Float(element.getX(), element.getY(), ((PojamElement)element).getWidth(),((PojamElement)element).getHeight());

        g.draw(shape);
    }

    @Override
    public boolean overlaps(Point point) {
        return shape.intersects(point.x, point.y, 100,50);
    }
}
