package dsw.gerumap.app.gui.swing.state.states;

import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;

import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;

import dsw.gerumap.app.mapRepository.implementation.subElements.PojamElement;
import dsw.gerumap.app.mapRepository.implementation.subElements.VezaElement;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;

public class RepositionState extends State {

    private Queue<DevicePainter> painters;
    private List<DevicePainter> seen;

    @Override
    public void execute(TabItemModel tb, Point point) {
        bfs(tb);
    }

    private void bfs(TabItemModel tb) {
        painters = new LinkedList<>();
        seen = new ArrayList<>();

        addToQueue((PojamPainter) tb.getPainters().get(0),tb);

        int depth = 0;
        float koef1 = 4;
        float koef2 = 1.2f;
        float scailing = 1.3f;
        while (!painters.isEmpty()){

            ++depth;

            int size = painters.size();

            for (int i = 0; i < size; i++){

                // izvucemo prvo povezanost dodamo u seen set i pokrenemo customSort za njega
                // dodajem decu dece

                DevicePainter dp = painters.poll();

                if (!seen.contains(dp)){

                    seen.add(dp);
                    addToQueue((PojamPainter) dp,tb);

                    customSort(tb,koef1,koef2,dp);
                }

            }

            koef1 *=scailing;
            koef2 /=scailing;

            tb.setStart(0);
            tb.setStart2(0);

        }

    }

    private void addToQueue(PojamPainter pp,TabItemModel tb){

        PojamPainter pojamPainter = pp;



        for(DevicePainter veza : pojamPainter.getVeze()){

            if(!(((VezaElement)veza.getElement()).getElements().get(0).equals(pojamPainter.getElement()))){
                DevicePainter dv = tb.elementByPainter(((VezaElement)veza.getElement()).getElements().get(0));
                painters.add(dv);
            }
            else{
                DevicePainter dv = tb.elementByPainter(((VezaElement)veza.getElement()).getElements().get(1));
                painters.add(dv);
            }

        }
    }

    @Override
    public void drag(TabItemModel tb, Point point) {

    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {
        return false;
    }

    private void customSort(TabItemModel tb,float koef1, float koef2,DevicePainter dp) {

        tb.setStart(0);
        tb.setStart2(0);


        if (dp instanceof PojamPainter && tb.getPainters().indexOf(dp) != 0){

        double dist1 = Line2D.ptLineDist(0,tb.getHeight()/koef1,tb.getWidth(),tb.getHeight()/koef1,dp.getShape().getBounds2D().getCenterX(), dp.getShape().getBounds2D().getCenterY());
        double dist2 = Line2D.ptLineDist(0,tb.getHeight()/koef2,tb.getWidth(),tb.getHeight()/koef2,dp.getShape().getBounds2D().getCenterX(), dp.getShape().getBounds2D().getCenterY());

        int increment = 120;

        if (dist1 < dist2){

                dp.getElement().setX((tb.getStart()+increment - ((PojamElement)dp.getElement()).getWidth()/2.f));
                dp.getElement().setY((tb.getHeight()/koef1 - ((PojamElement)dp.getElement()).getHeight()/2.f));
                ((PojamElement)dp.getElement()).setCenterX(tb.getStart()+increment);
                ((PojamElement)dp.getElement()).setCenterY(tb.getHeight()/koef1);

                tb.setStart(tb.getStart()+increment);
        }
        else {
            dp.getElement().setX((float) (tb.getStart2()+increment - ((PojamElement)dp.getElement()).getWidth()/2.f));
            dp.getElement().setY((float) (tb.getHeight()/koef2 - ((PojamElement)dp.getElement()).getHeight()/2.f));
            ((PojamElement)dp.getElement()).setCenterX(tb.getStart2()+increment);
            ((PojamElement)dp.getElement()).setCenterY((float) (tb.getHeight()/koef2));
            tb.setStart2(tb.getStart2()+increment);
        }

            }


        tb.repaint();
    }

    private void positionLine(DevicePainter dp, TabItemModel tb, PojamPainter centralniPojam){
        float x1 = (float) dp.getElement().getX()+ ((PojamElement)dp.getElement()).getWidth()/2.f;
        float y1 = (float) dp.getElement().getY() + ((PojamElement)dp.getElement()).getHeight()/2.f;
        float x2 = (float) centralniPojam.getElement().getX() +  ((PojamElement)centralniPojam.getElement()).getWidth()/2.f;
        float y2 = (float) centralniPojam.getElement().getY() +  ((PojamElement)centralniPojam.getElement()).getHeight()/2.f;
        for(DevicePainter veza: ((PojamPainter) dp).getVeze()){
            Point point = new Point((int) ((VezaElement)veza.getElement()).getX2(), (int) ((VezaElement)veza.getElement()).getY2());
            if(centralniPojam.contains(point) || dp.contains(point)){
                ((VezaElement)veza.getElement()).setX(x1);
                ((VezaElement)veza.getElement()).setY(y1);
                ((VezaElement)veza.getElement()).setX2(x2);
                ((VezaElement)veza.getElement()).setY2(y2);
            }
        }
    }

}
