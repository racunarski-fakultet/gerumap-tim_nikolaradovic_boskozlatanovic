package dsw.gerumap.app.gui.swing.controller.upperSide;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class DoAction extends AbstractGerumapAction {
    public DoAction() {
        putValue(NAME,"Redo");
        putValue(SHORT_DESCRIPTION,"Redo");
        putValue(SMALL_ICON,loadIcon("/images/Redo.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((TabItemModel)MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getMapNode().getCommandManager().doCommand();
    }
}
