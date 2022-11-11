package dsw.gerumap.app.core;

import dsw.gerumap.app.mapRepository.composite.MapNode;

public interface MapRepository {

    MapNode getProjectExplorer();
    void addChild( MapNode child);
    void removeChild(MapNode child);

}
