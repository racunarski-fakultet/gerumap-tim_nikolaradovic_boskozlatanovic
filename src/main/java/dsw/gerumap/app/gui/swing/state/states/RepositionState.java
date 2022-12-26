package dsw.gerumap.app.gui.swing.state.states;

import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.mapRepository.implementation.subElements.PojamElement;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.PriorityQueue;
import java.util.Queue;

public class RepositionState extends State {
    @Override
    public void execute(TabItemModel tb, Point point) {
        customSort(tb);
    }


    @Override
    public void drag(TabItemModel tb, Point point) {

    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {
        return false;
    }

    private void customSort(TabItemModel tb) {

        for (DevicePainter dp : tb.getPainters()){

            if (dp instanceof PojamPainter && tb.getPainters().indexOf(dp) != 0){

                double dist1 = Line2D.ptLineDist(0,tb.getHeight()/3,tb.getWidth(),tb.getHeight()/3,dp.getShape().getBounds2D().getCenterX(), dp.getShape().getBounds2D().getCenterX());

                double centerX = dp.getShape().getBounds2D().getCenterX();
                double centerY = dp.getShape().getBounds2D().getCenterY();

                double dist2 = Line2D.ptLineDist(0,tb.getHeight()/1.5,tb.getWidth(),tb.getHeight()/1.5,dp.getElement().getX(), dp.getElement().getY());

                if (dist1 < dist2){

                    centerX -= dist1;
                    centerY -= dist1;

                    dp.getElement().setX((float) (centerX - ((PojamElement)dp.getElement()).getWidth()/2.f));
                    dp.getElement().setY((float) (centerY - ((PojamElement)dp.getElement()).getHeight()/2.f));

                }
                else {
                    centerX -= dist2;
                    centerY -= dist2;

                    dp.getElement().setX((float) (centerX - ((PojamElement)dp.getElement()).getWidth()/2.f));
                    dp.getElement().setY((float) (centerY - ((PojamElement)dp.getElement()).getHeight()/2.f));
                }
            }


        }
        tb.repaint();
    }

}
