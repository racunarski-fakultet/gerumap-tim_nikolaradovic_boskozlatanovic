package dsw.gerumap.app.core;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;


public interface MapRepository {

    MapNode getProjectExplorer();
    void addChild( MapNode child);
    void removeChild(MapNode child);

    void setAuthoer(MapNode mapNode,String name);
    void rename(MapNode mapNode, String name);

}
