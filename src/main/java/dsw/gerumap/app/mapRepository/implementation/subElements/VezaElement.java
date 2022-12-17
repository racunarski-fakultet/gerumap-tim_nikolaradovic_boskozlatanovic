package dsw.gerumap.app.mapRepository.implementation.subElements;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter


public class VezaElement extends Element {

    private float X2 = x,Y2 = y;
    private List<Element> elements;
    public VezaElement(MapNode parent, String name) {
        super(parent, name);
        elements = new ArrayList<>();
    }

    public VezaElement(MapNode parent, String name, List<MapNode> children) {
        super(parent, name, children);
    }
}
