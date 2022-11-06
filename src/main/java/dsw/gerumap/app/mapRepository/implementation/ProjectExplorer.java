package dsw.gerumap.app.mapRepository.implementation;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ProjectExplorer extends MapNodeComposite {

    public ProjectExplorer(String name) {
        super(null, name);
    }

    public ProjectExplorer(MapNode parent, String name, List<MapNode> children) {
        super(parent, name, children);
    }

    @Override
    public void addChildren(MapNode child) {
        if (child instanceof Project && !this.getChildren().contains((Project)child)) {
            this.getChildren().add(child);
            child.setParent(this);
        }
    }


    @Override
    public void removeChildren(MapNode child) {
        if (child instanceof Project && this.getChildren().contains((Project)child)){
            this.getChildren().remove(child);
        }
    }
}
