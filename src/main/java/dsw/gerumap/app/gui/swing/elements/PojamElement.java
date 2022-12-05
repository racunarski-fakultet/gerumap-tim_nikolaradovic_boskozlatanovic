package dsw.gerumap.app.gui.swing.elements;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PojamElement extends Element {
    private int width;
    private int height;
    public PojamElement(MapNode parent, String name) {
        super(parent, name);
        width = 70;
        height = 50;
    }

    public PojamElement(MapNode parent, String name, List<MapNode> children) {
        super(parent, name, children);
    }
}
