package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.view.MyDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends AbstractGerumapAction {

    public InfoAction() {

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK)); // ako bude trebalo
        putValue(NAME,"Info");
        putValue(SHORT_DESCRIPTION,"Info");
        putValue(SMALL_ICON,"");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //novi jdialog dodati sliku i poruku iz view modela
        JDialog dialog = new MyDialog();
        dialog.add(new JLabel(loadIcon("")));

    }
}
