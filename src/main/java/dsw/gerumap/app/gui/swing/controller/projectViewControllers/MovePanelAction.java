package dsw.gerumap.app.gui.swing.controller.projectViewControllers;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MovePanelAction extends AbstractGerumapAction {

    public MovePanelAction() {
        putValue(NAME,"Move");
        putValue(SHORT_DESCRIPTION,"Move");
        putValue(SMALL_ICON,loadIcon("/images/Left-right.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getIntance().getProjectView().switchToMovePanelState();
    }
}
