package dsw.gerumap.app.gui.swing.commands.implementations;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.Command;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;

public class AddElementCommand implements Command {

    DevicePainter pojamPainter;
    TabItemModel tab;

    public AddElementCommand(DevicePainter pojamPainter) {


        this.pojamPainter = pojamPainter;
        tab = (TabItemModel) MainFrame.getIntance().getTabbedPane().getSelectedComponent();

    }

    @Override
    public void doCommand() {

        tab.getPainters().add(pojamPainter);
        AppCore.getInstance().getMapRepository().addChild(pojamPainter.getElement().getParent(), pojamPainter.getElement());
        tab.repaint();
    }

    @Override
    public void undoCommand() {
        tab.getPainters().remove(pojamPainter);
        AppCore.getInstance().getMapRepository().removeChild(pojamPainter.getElement());
        tab.repaint();
    }
}
