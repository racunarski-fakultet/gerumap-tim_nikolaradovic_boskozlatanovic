package dsw.gerumap.app.gui.swing.controller.upperSide;

import com.sun.tools.javac.Main;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExportImageAction extends AbstractGerumapAction {

    public ExportImageAction() {
        putValue(NAME,"Export");
        putValue(SHORT_DESCRIPTION,"Export");
        putValue(SMALL_ICON,loadIcon("/images/Picture.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(MainFrame.getIntance().getTabbedPane().getSelectedComponent() == null) return;

        ((TabItemModel)MainFrame.getIntance().getTabbedPane().getSelectedComponent()).saveImage();
    }
}
