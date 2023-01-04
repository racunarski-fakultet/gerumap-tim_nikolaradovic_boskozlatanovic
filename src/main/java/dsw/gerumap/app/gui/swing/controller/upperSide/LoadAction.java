package dsw.gerumap.app.gui.swing.controller.upperSide;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.loader.ProjectLoader;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import dsw.gerumap.app.mapRepository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class LoadAction extends AbstractGerumapAction {
    public LoadAction() {
        putValue(NAME,"Open");
        putValue(SHORT_DESCRIPTION,"Open");
        putValue(SMALL_ICON,loadIcon("/images/Open.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(MainFrame.getIntance()) == 0) {
            try {
                File file = jfc.getSelectedFile();
                Project p = AppCore.getInstance().getSerializer().loadProject(file);
                ProjectLoader.loadProject((MapNode) p);
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }
    }
}
