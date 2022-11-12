package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.mapRepository.composite.MapNode;

public interface MapTree {

    MapTreeView generateTree(MapNode projectExplorer);
    void addChild(MapTreeItem parent);

    void removeChild();

    void setAuthor(MapTreeItem node,String name);
    void renameMapTreeItem();

    MapTreeItem getSelectedNode();
}
