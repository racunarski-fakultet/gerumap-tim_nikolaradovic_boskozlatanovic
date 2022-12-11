package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.elements.PojamElement;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.SelectioElements;
import dsw.gerumap.app.state.State;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MoveState extends State {

    private Integer startX;
    private Integer startY;
    private DevicePainter rectangle;

    @Override
    public void execute(TabItemModel tb, Point point) {
        rectangle = findSelectionElement(tb);

        if(rectangle != null && rectangle.contains(point)){
            startX = point.x;
            startY = point.y;
        }
    }

    @Override
    public void drag(TabItemModel tb, Point point) {



        if(tb.getTabSelectionModel().getSelected().size() == 1){

            DevicePainter currentylSelected = tb.getTabSelectionModel().getSelected().get(0);

            float dx = point.x - ((PojamElement)currentylSelected.getElement()).getWidth()/2.f;
            float dy = point.y - ((PojamElement)currentylSelected.getElement()).getHeight()/2.f;

            currentylSelected.getElement().setX(dx);
            currentylSelected.getElement().setY(dy);


            tb.repaint();
        }
        else{

            if(rectangle == null) return;

            int dx = startX - point.x;
            int dy = startY - point.y;

            double newX = ((Rectangle2D) rectangle.getShape()).getX() - dx;
            double newY = ((Rectangle2D) rectangle.getShape()).getY() - dy;



            ((Rectangle2D)rectangle.getShape()).setFrame(newX,newY,((Rectangle2D) rectangle.getShape()).getWidth(),((Rectangle2D) rectangle.getShape()).getHeight());

            for (DevicePainter p: tb.getTabSelectionModel().getSelected()){

                float newElipseX = p.getElement().getX() - dx;
                float  newElipseY = p.getElement().getY() - dy;

                p.getElement().setX(newElipseX);
                p.getElement().setY(newElipseY);

                tb.repaint();
            }


            startX = point.x;

            startY = point.y;
            tb.repaint();


        }
    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {
        return false;
    }


    private DevicePainter findSelectionElement(TabItemModel tb){
        for (DevicePainter p: tb.getPainters()){
            if(p instanceof SelectioElements){
                return p;
            }
        }
        return null;
    }
}

