package dsw.gerumap.app.state.states;

import dsw.gerumap.app.gui.swing.elements.PojamElement;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.VezaPainter;
import dsw.gerumap.app.state.State;

import java.awt.*;

public class SelectElementsState extends State {

    private DevicePainter currentylSelected;
    @Override
    public void execute(TabItemModel tb, Point point) {

        currentylSelected = tb.returnSelected(point);

        if (currentylSelected == null){
            tb.getTabSelectionModel().removeAll();

        }
        tb.getTabSelectionModel().addSelection(currentylSelected);
    }

    @Override
    public void drag(TabItemModel tb, Point point) {


        if (currentylSelected == null || currentylSelected instanceof VezaPainter){

            return;
        }

    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {
        currentylSelected = null;
        return true;
    }
}

