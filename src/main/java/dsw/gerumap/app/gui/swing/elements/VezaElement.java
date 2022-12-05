package dsw.gerumap.app.gui.swing.elements;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Element;

import java.util.List;

public class VezaElement extends Element {
    public VezaElement(MapNode parent, String name) {
        super(parent, name);
    }

    public VezaElement(MapNode parent, String name, List<MapNode> children) {
        super(parent, name, children);
    }
}
