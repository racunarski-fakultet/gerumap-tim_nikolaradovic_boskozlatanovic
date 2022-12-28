package dsw.gerumap.app.mapRepository.implementation.subElements;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.serializable.Hide;

import java.util.List;
@Hide
public class SelectionElement extends Element {

    public SelectionElement(MapNode parent, String name) {
        super(parent, name);
    }

    public SelectionElement(MapNode parent, String name, List<MapNode> children) {
        super(parent, name, children);
    }
}
