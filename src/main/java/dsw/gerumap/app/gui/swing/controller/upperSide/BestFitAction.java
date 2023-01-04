package dsw.gerumap.app.gui.swing.controller.upperSide;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class BestFitAction extends AbstractGerumapAction {
    public BestFitAction(){

        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK)); // ako bude trebalo
        putValue(NAME,"BestFit");
        putValue(SHORT_DESCRIPTION,"BestFit");
        putValue(SMALL_ICON,loadIcon("/images/Objects.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getIntance().getProjectView().switchToRepositionState();
    }
}
