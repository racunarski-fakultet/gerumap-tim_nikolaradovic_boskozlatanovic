package dsw.gerumap.app.gui.swing.view.painter;

import dsw.gerumap.app.gui.swing.elements.VezaElement;
import dsw.gerumap.app.mapRepository.implementation.Element;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VezaPainter extends DevicePainter{

    private Shape rectangle;
    public VezaPainter(Element element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g) {
        BasicStroke stroke = new BasicStroke(3f);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        g.setComposite(AlphaComposite.getInstance((AlphaComposite.DST_OUT),1f));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        shape = new Line2D.Float(element.getX(),element.getY(),((VezaElement)element).getX2(),((VezaElement)element).getY2());
        g.draw(shape);
    }

    @Override
    public boolean overlaps(Point point) {
        return shape.getBounds().contains(point);
    }

    @Override
    public boolean contains(Point point) {


        return shape.getBounds().contains(point);
    }




    @Override
    public void paintSelected(Graphics2D g) {
        BasicStroke stroke = new BasicStroke(3f);
        g.setStroke(stroke);

        g.setColor(Color.cyan);
       // g.setComposite(AlphaComposite.getInstance((AlphaComposite.DST_OUT)));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        shape = new Line2D.Float(element.getX(),element.getY(),((VezaElement)element).getX2(),((VezaElement)element).getY2());
        g.draw(shape);
    }

    public boolean hasElements(DevicePainter startPainter, DevicePainter endPainter){

        return (((VezaElement)this.getElement()).getElements().contains(startPainter.getElement())) && (((VezaElement)this.getElement()).getElements().contains(endPainter.getElement()));

    }
}
