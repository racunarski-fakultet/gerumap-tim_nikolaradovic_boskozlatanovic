package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.state.State;

import java.awt.*;
import java.util.Iterator;

public class DeleteElementsState extends State {
    @Override
    public void execute(TabItemModel tb, Point point) {


        Iterator painter = (Iterator) tb.getPainters();

        while(painter.hasNext()){
            if(((PojamPainter)painter).getShape().contains(point)){
                painter.remove();
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
}
