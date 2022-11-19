package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.errorHandling.EventType;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;

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

        if (MainFrame.getIntance().getMapTree().getSelectedNode() == null){
            AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NO_NODE_SELECTED);
            return;
        }


        String name = JOptionPane.showInputDialog(MainFrame.getIntance(),
                "Promeni ime");

        if(name == null){
            return;
            // for cancel button
        }
        else if (!name.isEmpty()){
            if (((MapNodeComposite)selected.getMapNode().getParent()).containsChild(name)){
                AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NAME_EXISTS);
                return;
            }
            else{
                AppCore.getInstance().getMapRepository().rename(selected.getMapNode(),name);
            }


        }
        else if(name.isEmpty()){
            AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NAME_CANNOT_BE_EMPTY);


        }



    }
}
