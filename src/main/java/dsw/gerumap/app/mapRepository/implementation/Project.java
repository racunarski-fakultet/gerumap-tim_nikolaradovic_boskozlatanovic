package dsw.gerumap.app.mapRepository.implementation;

import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Project extends MapNodeComposite {

    private String ime;

    private String autor;

    private String putanjaResursi;

    public Project(MapNode parent, String name) {
        super(parent, name);
    }

    public Project(MapNode parent, String name, List<MapNode> children) {
        super(parent, name, children);
    }

    @Override
    public void addChildren(MapNode child) {
        if (child instanceof MindMap && !this.getChildren().contains((MindMap)child)){
           this.getChildren().add(child);
        }
    }

    @Override
    public void removeChildren(MapNode child) {
        if (child instanceof MindMap && this.getChildren().contains((MindMap)child)){
            this.getChildren().remove(child);
        }
    }
}
