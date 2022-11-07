package dsw.gerumap.app.gui.swing.tree.controller;

import dsw.gerumap.app.gui.swing.tabbedPane.view.Tab;
import dsw.gerumap.app.gui.swing.tree.MapTree;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;

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
        Tab tab;
        if ((((MouseEvent) e)).getClickCount() == 2) {
            MapTreeItem item = MainFrame.getIntance().getMapTree().getSelectedNode();
            MapNode mp = item.getMapNode();

            for(MapNode i: ((MapNodeComposite)mp).getChildren()){
                tab = new Tab(i);
                MainFrame.getIntance().getDesktop().add(i.getName(),tab);
                MainFrame.getIntance().getDesktop().updateUI();
            }
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
