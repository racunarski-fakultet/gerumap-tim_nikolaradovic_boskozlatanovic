package dsw.gerumap.app.mapRepository.implementation;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.List;

@Getter
@Setter

public class Element extends MapNodeComposite {
    protected int[] paint = {150, 200, 100};
    protected int stroke = 2;
    protected float x;
    protected float y;
    protected String description;


    public Element(MapNode parent, String name) {
        super(parent, name);
    }

    public Element(MapNode parent, String name, List<MapNode> children) {
        super(parent, name, null);
    }

    @Override
    public void addChildren(MapNode child) {

    }

    @Override
    public void removeChildren(MapNode child) {

    }
}
