package dsw.gerumap.app.gui.swing.elements;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter


public class VezaElement extends Element {

    private float X2 = x,Y2 = y;
    public VezaElement(MapNode parent, String name) {
        super(parent, name);
    }

    public VezaElement(MapNode parent, String name, List<MapNode> children) {
        super(parent, name, children);
    }
}
