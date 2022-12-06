package dsw.gerumap.app.gui.swing.tabbedPane.controller;

import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseDragged implements MouseMotionListener {

    private TabItemModel tb;

    public MouseDragged(TabItemModel tb) {
        this.tb = tb;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MainFrame.getIntance().getProjectView().getStateManager().getCurrentState().execute(tb, e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
