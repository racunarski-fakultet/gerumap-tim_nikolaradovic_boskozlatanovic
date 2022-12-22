package dsw.gerumap.app.gui.swing.commands.implementations;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.Command;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteElementsCommand implements Command {


    private List<DevicePainter> selectedPainters = new ArrayList<>();
    private TabItemModel tab;
    public DeleteElementsCommand(List<DevicePainter> selectedPainters) {

        this.selectedPainters.addAll(selectedPainters);
        tab = (TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent();
    }

    @Override
    public void doCommand() {

        for (DevicePainter painter : selectedPainters){

            tab.getPainters().remove(painter);
            if(painter instanceof PojamPainter){

                ((PojamPainter) painter).getVeze().removeAll(Collections.singletonList(null));

                tab.getPainters().removeAll(((PojamPainter) painter).getVeze());

                for (DevicePainter veze: ((PojamPainter) painter).getVeze()){
                    AppCore.getInstance().getMapRepository().removeChild(veze.getElement());
                    if (!selectedPainters.contains(veze)){
                        selectedPainters.add(veze);
                    }
                }
            }
            AppCore.getInstance().getMapRepository().removeChild(painter.getElement());
            tab.repaint();


        }
        tab.getTabSelectionModel().setSelected(new ArrayList<>());
    }

    @Override
    public void undoCommand() {

        tab.getPainters().addAll(selectedPainters);

        for (DevicePainter dp: selectedPainters){
            AppCore.getInstance().getMapRepository().addChild(dp.getElement().getParent(), dp.getElement());
        }
        tab.repaint();
    }
}
