package dsw.gerumap.app.mapRepository.composite;

import dsw.gerumap.app.core.MapRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public abstract class MapNodeComposite extends MapNode {

    List<MapNode> children;

    public MapNodeComposite(MapNode parent, String name) {
        super(parent, name);
        this.children = new ArrayList<>();
    }

    public MapNodeComposite(MapNode parent, String name, List<MapNode> children) {
        super(parent, name);
        this.children = children;
    }

    public abstract void addChildren(MapNode child);

    public abstract void removeChildren(MapNode child);

    public MapNode getChildByName(String name){
        for (MapNode child: children){
            if (name.equals(child.getName())){
                return child;
            }
        }
        return null;
    }


}
