package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractGerumapAction{

    public DeleteAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
        putValue(NAME,"Delete");
        putValue(SHORT_DESCRIPTION,"Delete");
        putValue(SMALL_ICON,loadIcon("/images/Delete.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MapTreeItem selected = (MapTreeItem) MainFrame.getIntance().getMapTree().getSelectedNode();
        if(selected == null)
            return;
        AppCore.getInstance().getMapRepository().removeChild(selected.getMapNode());
    }
}
