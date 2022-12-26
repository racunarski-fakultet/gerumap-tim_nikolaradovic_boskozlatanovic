package dsw.gerumap.app.gui.swing.commands.implementations;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.Command;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class MoveElementsCommand implements Command {


    private List<DevicePainter> startingPointElements;
    private List<DevicePainter> endingPointElements;
    private TabItemModel tab;


    public MoveElementsCommand(List<DevicePainter> startingPointElements) {
        this.startingPointElements = startingPointElements;
        tab = (TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent();
    }

    @Override
    public void doCommand() {
        tab.getPainters().removeAll(startingPointElements);
        tab.getPainters().addAll(endingPointElements);
        for (DevicePainter dp: endingPointElements){
            AppCore.getInstance().getMapRepository().addChild(dp.getElement().getParent(), dp.getElement());
        }
        tab.repaint();
    }

    @Override
    public void undoCommand() {

        tab.getPainters().removeAll(endingPointElements);
        tab.getPainters().addAll(startingPointElements);

        for (DevicePainter dp: startingPointElements){
            AppCore.getInstance().getMapRepository().addChild(dp.getElement().getParent(), dp.getElement());
        }
        tab.repaint();
    }
}
