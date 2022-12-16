package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.*;

public abstract class State {
    public abstract void execute(TabItemModel tb, Point point);
    public abstract void drag(TabItemModel tb, Point point);
    public abstract boolean isConnected(TabItemModel tb, Point point);


    public static Point PointMultiply(Point p){
        TabItemModel model = (TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent();

        return new Point((int) ((p.x + Math.abs(model.getXMove()))/model.getScaling()), (int) ((p.y + Math.abs(model.getYMove()))/model.getScaling()));
    }


}
