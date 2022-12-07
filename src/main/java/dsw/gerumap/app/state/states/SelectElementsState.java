package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.elements.PojamElement;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.state.State;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SelectElementsState extends State {

    private DevicePainter currentylSelected;
    @Override
    public void execute(TabItemModel tb, Point point) {
        currentylSelected = tb.returnSelected(point);
    }

    @Override
    public void drag(TabItemModel tb, Point point) {


        if (currentylSelected == null){
            return;
        }
        float dx = point.x - ((PojamElement)currentylSelected.getElement()).getWidth()/2.f;
        float dy = point.y - ((PojamElement)currentylSelected.getElement()).getHeight()/2.f;

        currentylSelected.getElement().setX(dx);
        currentylSelected.getElement().setY(dy);


        tb.repaint();
    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {
        currentylSelected = null;
        return true;
    }
}
