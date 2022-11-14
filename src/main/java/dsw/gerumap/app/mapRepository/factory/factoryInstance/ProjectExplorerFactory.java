package dsw.gerumap.app.mapRepository.factory.factoryInstance;

import dsw.gerumap.app.mapRepository.factory.NodeFactory;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;

public class ProjectExplorerFactory extends NodeFactory {
    @Override
    public MapNode createNode(MapNode parent, String name) {
        return new ProjectExplorer(name);
    }
}
