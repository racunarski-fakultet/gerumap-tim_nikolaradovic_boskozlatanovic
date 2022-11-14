package dsw.gerumap.app.mapRepository.factory.factoryInstance;

import dsw.gerumap.app.mapRepository.factory.NodeFactory;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.MindMap;

public class MindMapFactory extends NodeFactory {
    @Override
    public MapNode createNode(MapNode parent, String name) {
        return new MindMap(parent, name);
    }
}
