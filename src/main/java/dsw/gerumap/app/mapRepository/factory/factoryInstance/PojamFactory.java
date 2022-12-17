package dsw.gerumap.app.mapRepository.factory.factoryInstance;

import dsw.gerumap.app.mapRepository.implementation.subElements.PojamElement;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.factory.NodeFactory;

public class PojamFactory extends NodeFactory {
    @Override
    public MapNode createNode(MapNode parent, String name) {
        return new PojamElement(parent,name);
    }
}
