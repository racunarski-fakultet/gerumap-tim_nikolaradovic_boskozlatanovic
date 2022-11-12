package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.errorHandling.EventType;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.MapRepositoryImplementation;

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
        String response = JOptionPane.showInputDialog
                (null,"<html>Whats your name?"+ "<br>Enter your name:",JOptionPane.QUESTION_MESSAGE);
        MapTreeItem selected = (MapTreeItem) MainFrame.getIntance().getMapTree().getSelectedNode();
        if(selected == null){
            AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NO_NODE_SELECTED);
            return;
        }

        ((MapRepositoryImplementation) AppCore.getInstance().getMapRepository()).addChild(selected.getMapNode());


    }
}
