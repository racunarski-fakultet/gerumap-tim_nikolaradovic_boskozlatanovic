package dsw.gerumap.app.core;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;

public interface MapRepository {

    ProjectExplorer getProjectExplorer();
    void addChild( MapNode child);
    void removeChild(MapNode child);

}
