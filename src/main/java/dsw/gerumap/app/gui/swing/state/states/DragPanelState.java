package dsw.gerumap.app.gui.swing.state.states;

import dsw.gerumap.app.gui.swing.elements.VezaElement;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.gui.swing.view.painter.VezaPainter;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DragPanelState extends State {

    private double startX;
    private double startY;

    private Point startPoint;

    @Override
    public void execute(TabItemModel tb, Point point) {

         startPoint = point;

    }

    @Override
    public void drag(TabItemModel tb, Point point) {

        int offsetX = (int) - (startPoint.x - point.getX());
        int offsetY = (int) - (startPoint.y - point.getY());



        double x = ((TabItemModel)MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getXMove() + offsetX* ((TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getScaling();

        double y =  ((TabItemModel)MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getYMove() + offsetY* ((TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getScaling();


        if (x <= 0) {
            ((TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent()).setXMove(x);
        }
        if (y <= 0) {
            ((TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent()).setYMove(y);
        }
        startPoint = PointMultiply(point);
        tb.repaint();

    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {

        return false;
    }
}
