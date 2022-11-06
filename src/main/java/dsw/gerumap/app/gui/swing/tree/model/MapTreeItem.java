package dsw.gerumap.app.gui.swing.tree.model;


import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.DefaultMutableTreeNode;
@Getter
@Setter
public class MapTreeItem extends DefaultMutableTreeNode {

    private MapNode mapNode;


    public MapTreeItem(MapNode nodeModel) {
        this.mapNode = nodeModel;
    }

    @Override
    public String toString() {
        return mapNode.getName();
    }
    public void setName(String name){
        mapNode.setName(name);
    }
}
