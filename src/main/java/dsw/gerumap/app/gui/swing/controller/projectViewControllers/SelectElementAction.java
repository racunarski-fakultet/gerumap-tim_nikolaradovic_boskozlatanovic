package dsw.gerumap.app.gui.swing.controller.projectViewControllers;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelectElementAction extends AbstractGerumapAction {

    public SelectElementAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        putValue(NAME,"Select elements");
        putValue(SHORT_DESCRIPTION,"Select");
        putValue(SMALL_ICON,loadIcon("/images/Mouse pointer.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getIntance().getProjectView().switchToMoveState();
    }
}
