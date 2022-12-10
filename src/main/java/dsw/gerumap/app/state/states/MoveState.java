package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.elements.PojamElement;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.gui.swing.view.painter.SelectioElements;
import dsw.gerumap.app.state.State;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class MoveState extends State {

    Shape rectangle;
    int startX;
    int startY;

    private DevicePainter currentylSelected;
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
        else if(tb.returnSelected(point) != null && tb.returnSelected(point) instanceof PojamPainter){

            tb.getTabSelectionModel().getSelected().removeAll(tb.getTabSelectionModel().getSelected());
            tb.getTabSelectionModel().getSelected().add(tb.returnSelected(point));
            tb.getPainters().remove(rectanglePainter);
            rectangle = null;
            tb.repaint();

            currentylSelected = tb.returnSelected(point);
        }

        else if(rectangle != null && !rectangle.contains(point)){
            tb.getTabSelectionModel().getSelected().removeAll(tb.getTabSelectionModel().getSelected());
            tb.getPainters().remove(rectanglePainter);
            rectangle = null;

        }

    }

    @Override
    public void drag(TabItemModel tb, Point point) {

        if(rectangle == null && currentylSelected != null) {

            float dx = point.x - ((PojamElement)currentylSelected.getElement()).getWidth()/2.f;
            float dy = point.y - ((PojamElement)currentylSelected.getElement()).getHeight()/2.f;

            currentylSelected.getElement().setX(dx);
            currentylSelected.getElement().setY(dy);


            tb.repaint();
            return;
        }
        tb.getTabSelectionModel().getSelected().removeAll(tb.getTabSelectionModel().getSelected());
        drawRectangle(point);

        List<DevicePainter> newPainters = addSelected(tb);
        if(newPainters.size() != 0){
            tb.getTabSelectionModel().getSelected().addAll(newPainters);
        }
        tb.repaint();

    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {
        currentylSelected = null;
        return true;
    }

    private void drawRectangle(Point point){

        float[] dash = {5f,5f};

        Stroke dashedStroke = new BasicStroke(2f,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER,3f,dash,0f);

        rectangle = new Rectangle2D.Float(startX,startY,point.x-startX,point.y- startY);

        rectanglePainter.setShape(rectangle);
        ((SelectioElements)rectanglePainter).setDashedStroke(dashedStroke);
    }
    private List<DevicePainter> addSelected(TabItemModel tb){

        List<DevicePainter> dv = new ArrayList<>();

        for (DevicePainter p: tb.getPainters()){
            if (rectangle.contains(p.getShape().getBounds2D()) && !(p instanceof SelectioElements)){
                dv.add(p);
            }
        }
        return dv;
    }
}
