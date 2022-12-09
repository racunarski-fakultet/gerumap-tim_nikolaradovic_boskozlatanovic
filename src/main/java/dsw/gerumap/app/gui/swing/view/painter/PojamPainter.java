package dsw.gerumap.app.gui.swing.view.painter;
import dsw.gerumap.app.gui.swing.elements.PojamElement;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.mapRepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PojamPainter extends DevicePainter{

    private List<DevicePainter> veze;
    public PojamPainter(Element element) {
        super(element);
        veze = new ArrayList<>();
    }

    @Override
    public void paint(Graphics2D g) {


        BasicStroke stroke = new BasicStroke(element.getStroke());
        g.setStroke(stroke);
        g.setColor(Color.BLUE);
        //g.drawString(element.getName(),  getElement().getX()+33, getElement().getY()+30 );
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        shape = new Ellipse2D.Float(element.getX(), element.getY(), ((PojamElement)element).getWidth(),((PojamElement)element).getHeight());
        drawStirng(g,g.getFont(),shape.getBounds2D(), element.getName());
        g.draw(shape);
    }

    @Override
    public boolean overlaps(Point point) {
        return shape.intersects(point.x, point.y, 100,50);
    }

    @Override
    public boolean contains(Point point) {
        return shape.contains(point);
    }

    @Override
    public void paintSelected(Graphics2D g) {

        BasicStroke stroke = new BasicStroke(element.getStroke());
        g.setStroke(stroke);
        g.setColor(Color.RED);
        //g.drawString(element.getName(),  getElement().getX()+33, getElement().getY()+30 );

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        shape = new Ellipse2D.Float(element.getX(), element.getY(), ((PojamElement)element).getWidth(),((PojamElement)element).getHeight());
        drawStirng(g,g.getFont(),shape.getBounds2D(), element.getName());
        g.draw(shape);
    }

    private void drawStirng(Graphics2D g,Font font, Rectangle2D r,String s){

        FontRenderContext frc =
                new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (int) ((r.getWidth() / 2) - (rWidth / 2) - rX);
        int b = (int) ((r.getHeight() / 2) - (rHeight / 2) - rY);

        g.setFont(font);
        g.drawString(s, (int) (r.getX() + a), (int) (r.getY() + b));

    }
}
