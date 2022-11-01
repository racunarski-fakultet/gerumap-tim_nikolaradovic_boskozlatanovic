package dsw.gerumap.app.mapRepository.implementation;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class MindMap extends MapNodeComposite {

    private boolean isTemplate;

    public MindMap(MapNode parent, String name) {
        super(parent, name);
    }

    public MindMap(MapNode parent, String name, List<MapNode> children) {
        super(parent, name, children);
    }

    @Override
    public void addChildren(MapNode child) {
        if(child instanceof Element && !this.getChildren().contains((Element)child)){
            this.getChildren().add(child);
        }
    }

    @Override
    public void removeChildren(MapNode child) {
        if (child instanceof Element && !this.getChildren().contains((Element)child)){
            this.getChildren().remove(child);
        }

    }
}
