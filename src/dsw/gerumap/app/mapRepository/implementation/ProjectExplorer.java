package dsw.gerumap.app.mapRepository.implementation;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;

public class ProjectExplorer extends MapNodeComposite {

    @Override
    public void addChildren(MapNode child) {

        if (child instanceof Project) {
            super.addChildren(child);
        }
    }

    @Override
    public void removeChildren(MapNode child) {

        if (child instanceof MindMap){
            super.removeChildren(child);
        }

    }
}
