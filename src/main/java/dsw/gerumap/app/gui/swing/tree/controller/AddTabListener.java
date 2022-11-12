package dsw.gerumap.app.gui.swing.tree.controller;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Project;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddTabListener implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {
        if ((((MouseEvent) e)).getClickCount() == 2) {
            MapTreeItem item = MainFrame.getIntance().getMapTree().getSelectedNode();
            MapNode mp = item.getMapNode();
            if(mp instanceof Project)
                MainFrame.getIntance().getTabbedPane().addToPanel(mp);

          //  ((MapRepositoryImplementation) AppCore.getInstance().getMapRepository()).addTab(mp);
        }



    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
