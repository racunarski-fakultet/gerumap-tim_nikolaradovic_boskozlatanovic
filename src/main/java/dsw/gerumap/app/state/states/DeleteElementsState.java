package dsw.gerumap.app.state.states;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.state.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class DeleteElementsState extends State {
    @Override
    public void execute(TabItemModel tb, Point point) {

            for (DevicePainter painter : tb.getTabSelectionModel().getSelected()){

                tb.getPainters().remove(painter);
                if(painter instanceof PojamPainter){

                    ((PojamPainter) painter).getVeze().removeAll(Collections.singletonList(null));

                    tb.getPainters().removeAll(((PojamPainter) painter).getVeze());

                    for (DevicePainter veze: ((PojamPainter) painter).getVeze()){
                        AppCore.getInstance().getMapRepository().removeChild(veze.getElement());
                    }
                }
                AppCore.getInstance().getMapRepository().removeChild(painter.getElement());
                tb.repaint();


            }
            tb.getTabSelectionModel().setSelected(new ArrayList<>());
    }

    @Override
    public void drag(TabItemModel tb, Point point) {

    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {
        return false;
    }
}
