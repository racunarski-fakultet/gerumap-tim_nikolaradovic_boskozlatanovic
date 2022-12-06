package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.state.State;

import java.awt.*;

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

        painter.getElement().setX(point.x);
        painter.getElement().setY(point.y);

        tb.repaint();
    }
}
