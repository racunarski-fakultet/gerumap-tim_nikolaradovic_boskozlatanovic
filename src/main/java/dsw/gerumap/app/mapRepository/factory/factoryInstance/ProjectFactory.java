package dsw.gerumap.app.mapRepository.factory.factoryInstance;

import dsw.gerumap.app.mapRepository.factory.NodeFactory;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Project;

public class ProjectFactory extends NodeFactory {
    @Override
    public MapNode createNode(MapNode parent, String name) {
        return new Project(parent, name);
    }
}
