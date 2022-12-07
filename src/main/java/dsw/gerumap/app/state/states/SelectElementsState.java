package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.elements.PojamElement;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.state.State;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SelectElementsState extends State {
    @Override
    public void execute(TabItemModel tb, Point point) {

    }

    @Override
    public void drag(TabItemModel tb, Point point) {

        DevicePainter painter = tb.returnSelected(point);

        if (painter == null){
            return;
        }
        float dx = point.x - ((PojamElement)painter.getElement()).getWidth()/2.f;
        float dy = point.y - ((PojamElement)painter.getElement()).getHeight()/2.f;

        painter.getElement().setX(dx);
        painter.getElement().setY(dy);


        tb.repaint();
    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {
        return false;
    }
}
