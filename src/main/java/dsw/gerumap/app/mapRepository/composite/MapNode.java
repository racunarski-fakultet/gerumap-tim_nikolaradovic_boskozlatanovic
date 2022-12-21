package dsw.gerumap.app.mapRepository.composite;

import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
public class MapNode {

    private transient MapNode parent;

    private String filePath;
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

            if (this.name.equals(otherObj.getName()) && this.getParent() !=null && otherObj.getParent() != null){
                return this.getParent().getName().equals(otherObj.getParent().getName());
            }
            return this.name.equals(otherObj.getName());
        }
        return false;
    }
}
