package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractGerumapAction{

    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        putValue(NAME,"New Project");
        putValue(SHORT_DESCRIPTION,"New Project");
         putValue(SMALL_ICON,loadIcon("/images/Add.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selected = (MapTreeItem) MainFrame.getIntance().getMapTree().getSelectedNode();
        MainFrame.getIntance().getMapTree().addChild(selected);
    }
}
