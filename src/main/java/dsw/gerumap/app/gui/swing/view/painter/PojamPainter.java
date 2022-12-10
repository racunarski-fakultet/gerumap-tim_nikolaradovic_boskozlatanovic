package dsw.gerumap.app.gui.swing.view.painter;
import dsw.gerumap.app.gui.swing.elements.PojamElement;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
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
        g.setColor(new Color(150, 200, 100));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = g.getFontMetrics().stringWidth(element.getName());
        if(width < ((PojamElement)element).getWidth()){
            width = ((PojamElement)element).getWidth();
        }
        shape = new Ellipse2D.Float(element.getX(), element.getY(), width+7,((PojamElement)element).getHeight());

        g.fill(shape);


        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        g.draw(shape);




        g.setColor(Color.BLACK);
        drawString(g,g.getFont(),shape.getBounds2D(), element.getName());



    }

    @Override
    public boolean overlaps(Point point) {
        return shape.intersects(point.x, point.y, 160,85);
    }

    @Override
    public boolean contains(Point point) {
        return shape.contains(point);
    }

    @Override
    public void paintSelected(Graphics2D g) {
        BasicStroke stroke = new BasicStroke(element.getStroke());
        g.setColor(Color.RED);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = g.getFontMetrics().stringWidth(element.getName());
        if(width < ((PojamElement)element).getWidth()){
            width = ((PojamElement)element).getWidth();
        }
        shape = new Ellipse2D.Float(element.getX(), element.getY(), width+7,((PojamElement)element).getHeight());

        g.fill(shape);

        g.setStroke(stroke);

        g.setColor(Color.BLACK);
        g.draw(shape);




        g.setColor(Color.BLACK);
        drawString(g,g.getFont(),shape.getBounds2D(), element.getName());

    }

    private void drawString(Graphics2D g, Font font, Rectangle2D r, String s){

        Graphics2D g2 = g;
        FontRenderContext frc =
                new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (int) ((r.getWidth() / 2) - (rWidth / 2) - rX);
        int b = (int) ((r.getHeight() / 2) - (rHeight / 2) - rY);

        g2.setFont(font);
        g2.setColor(Color.BLACK);
        g2.drawString(s, (int) (r.getX() + a), (int) (r.getY() + b));

    }


}
