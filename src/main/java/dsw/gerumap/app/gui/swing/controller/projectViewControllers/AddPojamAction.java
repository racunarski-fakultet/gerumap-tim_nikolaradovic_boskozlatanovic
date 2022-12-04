package dsw.gerumap.app.gui.swing.controller.projectViewControllers;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddPojamAction extends AbstractGerumapAction {

    public AddPojamAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        putValue(NAME,"Add concept");
        putValue(SHORT_DESCRIPTION,"Concept");
        putValue(SMALL_ICON,loadIcon("/images/Magic hat.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
