package dsw.gerumap.app.gui.swing.tree.controller;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.MapRepositoryImplementation;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MapTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedOn = null;

    private JTextField edit = null;
    public MapTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    public MapTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer, TreeCellEditor editor) {
        super(tree, renderer, editor);
    }
    public Component getTreeCellEditorComponent(JTree tree,Object arg1, boolean arg2,boolean arg3,boolean arg4,int arg5){
        clickedOn = arg1;
        edit = new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject arg0){
        if(arg0 instanceof MouseEvent) {
            if ((((MouseEvent) arg0)).getClickCount() == 3) {
                return true;
            }

        }
        return false;
    }

    public void actionPerformed(ActionEvent e){
        if(!(clickedOn instanceof MapTreeItem))
            return;
        MapTreeItem clicked = (MapTreeItem) clickedOn;
        System.out.println(e.getActionCommand());
        ((MapRepositoryImplementation) AppCore.getInstance().getMapRepository()).rename(clicked.getMapNode(), e.getActionCommand());
    }


}
