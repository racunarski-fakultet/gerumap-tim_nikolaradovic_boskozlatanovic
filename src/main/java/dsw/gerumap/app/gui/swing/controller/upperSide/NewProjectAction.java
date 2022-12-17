package dsw.gerumap.app.gui.swing.controller.upperSide;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.errorHandling.EventType;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.CustomPopUp;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.MapRepositoryImplementation;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractGerumapAction {

    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        putValue(NAME,"New Project");
        putValue(SHORT_DESCRIPTION,"New Project");
         putValue(SMALL_ICON,loadIcon("/images/Add.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // custom selection
        MapTreeItem selected = (MapTreeItem) MainFrame.getIntance().getMapTree().getSelectedNode();
        CustomPopUp customPopUp = new CustomPopUp();
        if(selected == null || selected.getMapNode() instanceof MindMap){
            AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NO_NODE_SELECTED);
            return;
        }


        int response = customPopUp.makePopUp(selected.getMapNode());

        if (response == JOptionPane.OK_OPTION) {

            if (((MapNodeComposite)selected.getMapNode()).containsChild(customPopUp.getName().getText())){
                AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NAME_EXISTS);

            }

            else if(customPopUp.getName().getText().isEmpty()){
                AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NAME_CANNOT_BE_EMPTY);

            }

            else {
                if (selected.getMapNode() instanceof ProjectExplorer){
                    ((MapRepositoryImplementation) AppCore.getInstance().getMapRepository()).addChild(selected.getMapNode(),customPopUp.getName().getText(),customPopUp.getAuthor().getText());
                }
                else {
                    ((MapRepositoryImplementation) AppCore.getInstance().getMapRepository()).addChild(selected.getMapNode(),customPopUp.getName().getText(),"");
                }
            }


        }



    }
}
