package dsw.gerumap.app.gui.swing.controller.projectViewControllers;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddPojamAction extends AbstractGerumapAction {

    public AddPojamAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        putValue(NAME,"Add concept");
        putValue(SHORT_DESCRIPTION,"Pojam");
        putValue(SMALL_ICON,loadIcon("/images/Ellipse.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getIntance().getProjectView().switchToAddPojamState();

    }
}
