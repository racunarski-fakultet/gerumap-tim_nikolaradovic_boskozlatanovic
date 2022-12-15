package dsw.gerumap.app.gui.swing.state.states;


import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;

import java.awt.*;

public class ZoomOutState extends State {
    @Override
    public void execute(TabItemModel tb, Point point) {
//        tb.setOldX((point.x + Math.abs(tb.getXMove())) / tb.getScailingFactor());
////        tb.setOldY((point.y + Math.abs(tb.getYMove())) / tb.getScailingFactor());
        tb.zoomOut();
    }

    @Override
    public void drag(TabItemModel tb, Point point) {

    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {
        return false;
    }
}
