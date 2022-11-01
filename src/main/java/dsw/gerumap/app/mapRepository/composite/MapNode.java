package dsw.gerumap.app.mapRepository.composite;

import dsw.gerumap.app.core.MapRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
public abstract class MapNode implements MapRepository {

    private MapNode parent;

    @ToString.Exclude
    private String name;

    public MapNode(MapNode parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof MapNode){
            MapNode otherObj = (MapNode) obj;
            return this.name.equals(otherObj.getName());
        }
        return false;
    }
}
