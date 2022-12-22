package dsw.gerumap.app.gui.swing.controller.upperSide;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class UndoAction extends AbstractGerumapAction {

    public UndoAction() {
        putValue(NAME,"Undo");
        putValue(SHORT_DESCRIPTION,"Undo");
        putValue(SMALL_ICON,loadIcon("/images/Revert.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getIntance().getCommandManager().undoCommand();
    }
}
