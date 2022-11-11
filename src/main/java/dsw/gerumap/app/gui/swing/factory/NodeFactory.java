package dsw.gerumap.app.gui.swing.factory;

import dsw.gerumap.app.mapRepository.composite.MapNode;

public abstract class NodeFactory {

    public MapNode getNode(MapNode parent, String name){
        MapNode mapNode = createNode(parent, name);
        return mapNode;
    }
    public abstract MapNode createNode(MapNode parent, String name);
}
