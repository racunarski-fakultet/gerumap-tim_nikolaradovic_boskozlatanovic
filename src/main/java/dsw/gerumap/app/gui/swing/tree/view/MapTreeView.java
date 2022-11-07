package dsw.gerumap.app.gui.swing.tree.view;

import dsw.gerumap.app.gui.swing.tree.controller.AddTabListener;
import dsw.gerumap.app.gui.swing.tree.controller.MapTreeCellEditor;
import dsw.gerumap.app.gui.swing.tree.controller.MapTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class MapTreeView extends JTree {
    public MapTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);
        MapTreeCellRender geruTreeCellRenderer = new MapTreeCellRender();
        addTreeSelectionListener(new MapTreeSelectionListener());
        addMouseListener(new AddTabListener());
        setCellEditor(new MapTreeCellEditor(this, geruTreeCellRenderer));
        setCellRenderer(geruTreeCellRenderer);
        setEditable(true);

    }
}
