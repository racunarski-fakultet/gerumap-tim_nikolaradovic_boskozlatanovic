package dsw.gerumap.app.gui.swing.controller.upperSide;

import com.sun.tools.javac.Main;
import dsw.gerumap.app.AppCore;

import dsw.gerumap.app.core.MessageGenerator;
import dsw.gerumap.app.errorHandling.EventType;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.Actions;
import dsw.gerumap.app.mapRepository.implementation.Project;

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

        if (MainFrame.getIntance().getMapTree().getSelectedNode() == null){
            AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NO_NODE_SELECTED);
            return;
        }

        if(!(MainFrame.getIntance().getMapTree().getSelectedNode().getMapNode() instanceof Project)){
            AppCore.getInstance().getMessageGenerator().generateMessage(EventType.ONLY_FOR_PROJECT);
            return;
        }

        String name = JOptionPane.showInputDialog(MainFrame.getIntance(),
                "Ime autora");
        if(name == null){
           // AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NO_AUTHOR);
            return;
        }
        else if (!name.isEmpty()){
            MapTreeItem selected = (MapTreeItem) MainFrame.getIntance().getMapTree().getSelectedNode();

            AppCore.getInstance().getMapRepository().setAuthoer(selected.getMapNode(),name);
        }
        else if(name.isEmpty()){
            AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NAME_CANNOT_BE_EMPTY);

        }



    }
}
