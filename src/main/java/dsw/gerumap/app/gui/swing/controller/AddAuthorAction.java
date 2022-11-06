package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddAuthorAction extends AbstractGerumapAction {

    public AddAuthorAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        putValue(NAME,"Add author");
        putValue(SHORT_DESCRIPTION,"Author");
        putValue(SMALL_ICON,loadIcon("/images/Magic hat.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog(MainFrame.getIntance(),
                "Ime autora");

        if (!name.isEmpty()){
            MapTreeItem selected = (MapTreeItem) MainFrame.getIntance().getMapTree().getSelectedNode();
            MainFrame.getIntance().getMapTree().setAuthor(selected,name);
        }

    }
}
