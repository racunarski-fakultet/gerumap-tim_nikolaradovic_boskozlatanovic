package dsw.gerumap.app.gui.swing.tabbedPane.controller;

import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MousePainter implements MouseListener {
    private TabItemModel tb;
    public MousePainter(TabItemModel tb){
        this.tb = tb;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point point = State.PointMultiply(e.getPoint());
        MainFrame.getIntance().getProjectView().getStateManager().getCurrentState().execute(tb, point);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point point = State.PointMultiply(e.getPoint());
        MainFrame.getIntance().getProjectView().getStateManager().getCurrentState().isConnected(tb, point);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
