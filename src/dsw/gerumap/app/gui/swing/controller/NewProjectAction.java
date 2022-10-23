package dsw.gerumap.app.gui.swing.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractGerumapAction{

    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        putValue(NAME,"New Project");
        putValue(SHORT_DESCRIPTION,"New Project");
        putValue(SMALL_ICON,"images/new.png");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
