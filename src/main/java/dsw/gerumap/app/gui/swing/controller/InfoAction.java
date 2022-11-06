package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends AbstractGerumapAction {

    public InfoAction() {

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK)); // ako bude trebalo
        putValue(NAME,"Info");
        putValue(SHORT_DESCRIPTION,"Info");
        putValue(SMALL_ICON,loadIcon("/images/Info.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

       JOptionPane.showMessageDialog(MainFrame.getIntance(),"Bosko Zlatanovic 36/21 i Nikola Radovic 49/21","Info",JOptionPane.INFORMATION_MESSAGE,loadIcon("/images/Info.png"));

    }
}
