package dsw.gerumap.app.mapRepository.composite;

import dsw.gerumap.app.mapRepository.commands.CommandManager;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public abstract class MapNode {

    private transient MapNode parent;

    private transient CommandManager commandManager;

    private String filePath;
    @ToString.Exclude
    private String name;

    public MapNode(MapNode parent, String name) {
        this.parent = parent;
        this.name = name;
        commandManager = new CommandManager();
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
