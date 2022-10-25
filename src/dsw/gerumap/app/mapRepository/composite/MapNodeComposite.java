package dsw.gerumap.app.mapRepository.composite;

import dsw.gerumap.app.core.MapRepository;

import java.util.List;

public class MapNodeComposite extends MapNode {

    List<MapNode> children;

    public void addChildren(MapNode child){
        this.children.add(child);
    }

    public void removeChildren(MapNode child){
        this.children.remove(child);
    }


}
