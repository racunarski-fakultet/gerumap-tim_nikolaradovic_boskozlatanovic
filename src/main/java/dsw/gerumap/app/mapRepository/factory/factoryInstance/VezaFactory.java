package dsw.gerumap.app.mapRepository.factory.factoryInstance;

import dsw.gerumap.app.gui.swing.elements.VezaElement;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.factory.NodeFactory;

public class VezaFactory extends NodeFactory {
    @Override
    public MapNode createNode(MapNode parent, String name) {
        return new VezaElement(parent,name);
    }
}
