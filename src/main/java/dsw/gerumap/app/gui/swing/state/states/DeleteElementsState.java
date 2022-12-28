package dsw.gerumap.app.gui.swing.state.states;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.Command;
import dsw.gerumap.app.mapRepository.commands.implementations.DeleteElementsCommand;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.gui.swing.view.painter.SelectioElements;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.mapRepository.implementation.Element;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DeleteElementsState extends State {
    @Override
    public void execute(TabItemModel tb, Point point) {

        Set<Element> tempElements = new HashSet<>();

            for (DevicePainter painter : tb.getTabSelectionModel().getSelected()){

               // tb.getPainters().remove(painter);
                tempElements.add(painter.getElement());

                if(painter instanceof PojamPainter){

                    //tb.getPainters().removeAll(((PojamPainter) painter).getVeze());

                    ((PojamPainter) painter).getVeze().removeAll(Collections.singletonList(null));



                    for (DevicePainter veze: ((PojamPainter) painter).getVeze()){
                        AppCore.getInstance().getMapRepository().removeChild(veze.getElement());
                        tempElements.add(veze.getElement());
                    }
                }
                AppCore.getInstance().getMapRepository().removeChild(painter.getElement());
                deleteSelectionRectangle(tb);
                tb.repaint();


            }
        Command command = new DeleteElementsCommand(tb.getMapNode());
        ((DeleteElementsCommand)command).setSelektovaniElementi(tempElements);
        tb.getMapNode().getCommandManager().addCommand(command);
            tb.getTabSelectionModel().setSelected(new ArrayList<>());
    }

    @Override
    public void drag(TabItemModel tb, Point point) {

    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {
        return false;
    }

    public void deleteSelectionRectangle(TabItemModel tb){

        int index = -1;

        for (DevicePainter p: tb.getPainters()){
            if (p instanceof SelectioElements) index = tb.getPainters().indexOf(p);
        }
        if (index != -1){
            tb.getPainters().remove(index);
        }
    }
}
