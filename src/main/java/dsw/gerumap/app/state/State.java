package dsw.gerumap.app.state;

import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;

import java.awt.*;

public abstract class State {
    public abstract void execute(TabItemModel tb, Point point);

}
