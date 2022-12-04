package dsw.gerumap.app.gui.swing.controller.projectViewControllers;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddLinijaAction extends AbstractGerumapAction {

    public AddLinijaAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
        putValue(NAME,"Add line");
        putValue(SHORT_DESCRIPTION,"Line");
        putValue(SMALL_ICON,loadIcon("/images/Line.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MainFrame.getIntance().getProjectView().switchToAddLinijaState();

    }
}
