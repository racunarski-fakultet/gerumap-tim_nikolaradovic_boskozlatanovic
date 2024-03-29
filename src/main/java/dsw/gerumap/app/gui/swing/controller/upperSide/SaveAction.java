package dsw.gerumap.app.gui.swing.controller.upperSide;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
@Getter
public class SaveAction extends AbstractGerumapAction {

    public SaveAction(){
      //  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        putValue(NAME,"Save");
        putValue(SHORT_DESCRIPTION,"Save");
        putValue(SMALL_ICON,loadIcon("/images/Save.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        MapNode mp = MainFrame.getIntance().getMapTree().getSelectedNode().getMapNode();

//        if(!mp.getFilePath().isEmpty()){
//            AppCore.getInstance().getSerializer().saveMindMap(mp, mp.getFilePath());
//            return;
//        }
        if(((MainFrame.getIntance().getMapTree().getSelectedNode().getMapNode()) instanceof Element) || mp instanceof ProjectExplorer){
            return;
        }
        if (mp instanceof Project && ((Project) mp).getFilePath() != null){
           String filePath =  ((Project)mp).getFilePath();
            AppCore.getInstance().getSerializer().saveMindMap(mp, filePath);
        }
        JFileChooser jfc = new JFileChooser();

        if(((MapNodeComposite)AppCore.getInstance().getMapRepository().getProjectExplorer()).getChildren().size() == 0){
            return;
        }

        MapNode mindMap = MainFrame.getIntance().getMapTree().getSelectedNode().getMapNode();
        File mindMapFile = null;

        if(mindMap.getFilePath() == null || mindMap.getFilePath().isEmpty()){
            if (jfc.showSaveDialog(MainFrame.getIntance()) == JFileChooser.APPROVE_OPTION) {
                mindMapFile = jfc.getSelectedFile();
                mindMap.setFilePath(mindMapFile.getPath());
            }else{
                return;
            }

        }
        AppCore.getInstance().getSerializer().saveMindMap(mindMap, mindMapFile.getPath());

    }
}
