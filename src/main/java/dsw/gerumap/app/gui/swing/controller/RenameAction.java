package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.errorHandling.EventType;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RenameAction extends AbstractGerumapAction{
    public RenameAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
        putValue(NAME,"Rename");
        putValue(SHORT_DESCRIPTION,"Rename");
        putValue(SMALL_ICON,loadIcon("/images/Rename.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selected = MainFrame.getIntance().getMapTree().getSelectedNode();

        String name = JOptionPane.showInputDialog(MainFrame.getIntance(),
                "Promeni ime");

        if(name == null){

        }
        else if (!name.isEmpty()){

            AppCore.getInstance().getMapRepository().rename(selected.getMapNode(),name);

        }
        else if(name.isEmpty()){
            AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NO_AUTHOR);

        }



    }
}
