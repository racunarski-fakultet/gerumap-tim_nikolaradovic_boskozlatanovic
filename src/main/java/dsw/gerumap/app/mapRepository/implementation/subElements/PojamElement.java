package dsw.gerumap.app.mapRepository.implementation.subElements;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class PojamElement extends Element {

    private int width;
    private int height;

    private float centerX;
    private float centerY;


    public PojamElement(MapNode parent, String name) {
        super(parent, name);
        width = 100;
        height =50 ;
    }

    public PojamElement(MapNode parent, String name, List<MapNode> children) {
        super(parent, name, children);
    }
}
