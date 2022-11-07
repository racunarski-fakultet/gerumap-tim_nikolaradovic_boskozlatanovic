package dsw.gerumap.app.gui.swing.tree.controller;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.mapRepository.implementation.Project;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MapTreeSelectionListener implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        MapTreeItem treeItemSelected = (MapTreeItem)path.getLastPathComponent();

//        if(treeItemSelected.getMapNode() instanceof Project && isCellEditable()){}


    }
    public boolean isCellEditable(EventObject arg0){
        if(arg0 instanceof MouseEvent) {
            if ((((MouseEvent) arg0)).getClickCount() == 2) {
                MapTreeItem source = (MapTreeItem) arg0.getSource();
                System.out.println("tet");
                return true;
            }

        }
        return false;
    }
}
