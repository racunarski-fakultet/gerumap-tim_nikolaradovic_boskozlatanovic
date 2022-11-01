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
    @Override
    public void addChildren(MapNode child) {
        if(child instanceof Element){
            super.addChildren(child);
        }
    }

    @Override
    public void removeChildren(MapNode child) {
        if (child instanceof Element){
            super.removeChildren(child);
        }

    }
}
