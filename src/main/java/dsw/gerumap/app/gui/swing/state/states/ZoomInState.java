package dsw.gerumap.app.gui.swing.state.states;

import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;

import java.awt.*;
import java.awt.geom.Point2D;

public class ZoomInState extends State {
    @Override
    public void execute(TabItemModel tb, Point point) {

        tb.zoomIn();

    }

    @Override
    public void drag(TabItemModel tb, Point point) {

    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {
        return false;
    }
}
