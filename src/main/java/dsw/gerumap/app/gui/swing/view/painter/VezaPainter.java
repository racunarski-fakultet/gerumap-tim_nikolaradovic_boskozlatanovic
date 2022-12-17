package dsw.gerumap.app.gui.swing.view.painter;

import dsw.gerumap.app.mapRepository.implementation.subElements.VezaElement;
import dsw.gerumap.app.mapRepository.implementation.Element;

import java.awt.*;
import java.awt.geom.Line2D;

public class VezaPainter extends DevicePainter{

    private Shape rectangle;
    public VezaPainter(Element element) {
        super(element);
        element.setPaint(new int[]{0, 0, 0});
    }

    @Override
    public void paint(Graphics2D g) {
        BasicStroke stroke = new BasicStroke(3f);
        g.setStroke(stroke);
        g.setColor(new Color(element.getPaint()[0], element.getPaint()[1], element.getPaint()[2]));
        //g.setComposite(AlphaComposite.getInstance((AlphaComposite.DST_OUT),1f));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        shape = new Line2D.Float(element.getX(),element.getY(),((VezaElement)element).getX2(),((VezaElement)element).getY2());
        g.draw(shape);
     //   g.draw(shape.getBounds2D());
    }

    @Override
    public boolean overlaps(Point point) {
        return shape.getBounds().contains(point);
    }

    @Override
    public boolean contains(Point point) {

        boolean b = ((Line2D) shape).ptSegDist(point) < 8;
        return b;
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
       // g.draw(shape.getBounds2D());
    }

    public boolean hasElements(DevicePainter startPainter, DevicePainter endPainter){

        return (((VezaElement)this.getElement()).getElements().contains(startPainter.getElement())) && (((VezaElement)this.getElement()).getElements().contains(endPainter.getElement()));

    }
}
