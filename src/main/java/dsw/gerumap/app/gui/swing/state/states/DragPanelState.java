package dsw.gerumap.app.gui.swing.state.states;

import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.*;

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

        double offsetX = - (startPoint.x - point.x);
        double offsetY = - (startPoint.y - point.y);

        if(((TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getScaling() != 1 && tb.getTabSelectionModel().getSelected().size() == 0) {
            double x = ((TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getXMove() + offsetX * ((TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getScaling();

            double y = ((TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getYMove() + offsetY * ((TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getScaling();


            if (x <= 0) {
                ((TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent()).setXMove(x);
            }
            if (y <= 0) {
                ((TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent()).setYMove(y);
            }


        }
        tb.repaint();
    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {

        return false;
    }
}
