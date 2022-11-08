package dsw.gerumap.app.gui.swing.tree.controller;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.gui.swing.tabbedPane.view.Tab;
import dsw.gerumap.app.gui.swing.tree.MapTree;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.MapRepositoryImplementation;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.implementation.Project;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

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
