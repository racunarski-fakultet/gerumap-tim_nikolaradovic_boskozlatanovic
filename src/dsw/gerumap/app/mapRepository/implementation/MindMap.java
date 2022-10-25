package dsw.gerumap.app.mapRepository.implementation;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;

import java.util.ArrayList;
import java.util.List;

public class MindMap extends MapNodeComposite {
    private List<Element> elementi = new ArrayList<>();

    @Override
    public void addChildren(MapNode child) {
        if(child instanceof Element){
            elementi.add((Element) child);
        }
    }
}
