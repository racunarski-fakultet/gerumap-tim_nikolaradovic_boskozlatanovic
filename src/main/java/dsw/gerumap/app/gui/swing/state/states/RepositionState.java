package dsw.gerumap.app.gui.swing.state.states;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.gui.swing.view.painter.VezaPainter;
import dsw.gerumap.app.mapRepository.Actions;
import dsw.gerumap.app.mapRepository.implementation.subElements.PojamElement;
import dsw.gerumap.app.mapRepository.implementation.subElements.VezaElement;

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
        PojamPainter centralniPojam = (PojamPainter) tb.getPainters().get(0);
        tb.setStart(0);
        tb.setStart2(0);
        for (DevicePainter dp : tb.getPainters()){

            if (dp instanceof PojamPainter && tb.getPainters().indexOf(dp) != 0){

                double dist1 = Line2D.ptLineDist(0,tb.getHeight()/4,tb.getWidth(),tb.getHeight()/4,dp.getShape().getBounds2D().getCenterX(), dp.getShape().getBounds2D().getCenterY());
                double dist2 = Line2D.ptLineDist(0,tb.getHeight()/1.2,tb.getWidth(),tb.getHeight()/1.2,dp.getShape().getBounds2D().getCenterX(), dp.getShape().getBounds2D().getCenterY());
                int increment = 120;



                if (dist1 < dist2){


                    dp.getElement().setX((float) (tb.getStart()+increment - ((PojamElement)dp.getElement()).getWidth()/2.f));
                    dp.getElement().setY((float) (tb.getHeight()/4 - ((PojamElement)dp.getElement()).getHeight()/2.f));
                    float x1 = (float) dp.getElement().getX()+ ((PojamElement)dp.getElement()).getWidth()/2.f;
                    float y1 = (float) dp.getElement().getY() + ((PojamElement)dp.getElement()).getHeight()/2.f;
                    float x2 = (float) centralniPojam.getElement().getX() +  ((PojamElement)centralniPojam.getElement()).getWidth()/2.f;
                    float y2 = (float) centralniPojam.getElement().getY() +  ((PojamElement)centralniPojam.getElement()).getHeight()/2.f;
                    for(DevicePainter veza: ((PojamPainter) dp).getVeze()){
                        Point point = new Point((int) ((VezaElement)veza.getElement()).getX2(), (int) ((VezaElement)veza.getElement()).getY2());
                        if(centralniPojam.contains(point)){
                            ((VezaElement)veza.getElement()).setX(x1);
                            ((VezaElement)veza.getElement()).setY(y1);
                            ((VezaElement)veza.getElement()).setX2(x2);
                            ((VezaElement)veza.getElement()).setY2(y2);
                        }
                    }
                    tb.setStart(tb.getStart()+increment);
                }
                else {

                    dp.getElement().setX((float) (tb.getStart2()+increment - ((PojamElement)dp.getElement()).getWidth()/2.f));
                    dp.getElement().setY((float) (tb.getHeight()/1.2 - ((PojamElement)dp.getElement()).getHeight()/2.f));
                    float x1 = (float) dp.getElement().getX() + ((PojamElement)dp.getElement()).getWidth()/2.f;
                    float y1 = (float) dp.getElement().getY() + ((PojamElement)dp.getElement()).getHeight()/2.f;
                    float x2 = (float) centralniPojam.getElement().getX() +  ((PojamElement)centralniPojam.getElement()).getWidth()/2.f;
                    float y2 = (float) centralniPojam.getElement().getY() +  ((PojamElement)centralniPojam.getElement()).getHeight()/2.f;
                    for(DevicePainter veza: ((PojamPainter) dp).getVeze()){
                        Point point = new Point((int) ((VezaElement)veza.getElement()).getX2(), (int) ((VezaElement)veza.getElement()).getY2());
                        if(centralniPojam.contains(point)){
                            ((VezaElement)veza.getElement()).setX(x1);
                            ((VezaElement)veza.getElement()).setY(y1);
                            ((VezaElement)veza.getElement()).setX2(x2);
                            ((VezaElement)veza.getElement()).setY2(y2);
                        }
                    }
                    tb.setStart2(tb.getStart2()+increment);
                }

            }


        }
        tb.repaint();
    }

}
