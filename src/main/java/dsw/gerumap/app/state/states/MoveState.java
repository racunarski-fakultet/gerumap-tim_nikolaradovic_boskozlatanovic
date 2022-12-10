package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.SelectioElements;
import dsw.gerumap.app.state.State;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MoveState extends State {

    Shape rectangle;
    int startX;
    int startY;
    DevicePainter rectanglePainter = new SelectioElements(null);;
    @Override
    public void execute(TabItemModel tb, Point point) {

        if(rectangle == null && tb.returnSelected(point) == null){
            tb.getTabSelectionModel().getSelected().removeAll(tb.getTabSelectionModel().getSelected());
            startX = point.x;
            startY = point.y;
            drawRectangle(point);
            tb.getPainters().add(rectanglePainter);
            tb.repaint();

        }
        else if(tb.returnSelected(point) != null){
            tb.getTabSelectionModel().getSelected().removeAll(tb.getTabSelectionModel().getSelected());
            tb.getTabSelectionModel().getSelected().add(tb.returnSelected(point));
            tb.getPainters().remove(rectanglePainter);
            tb.repaint();
            rectangle = null;

        }

    }

    @Override
    public void drag(TabItemModel tb, Point point) {
        if(rectangle == null) return;
        drawRectangle(point);
        tb.repaint();
    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {
        return false;
    }

    private void drawRectangle(Point point){

        float[] dash = {5f,5f};

        Stroke dashedStroke = new BasicStroke(2f,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER,3f,dash,0f);

        rectangle = new Rectangle2D.Float(startX,startY,point.x-startX,point.y- startY);

        rectanglePainter.setShape(rectangle);
        ((SelectioElements)rectanglePainter).setDashedStroke(dashedStroke);
    }
}
