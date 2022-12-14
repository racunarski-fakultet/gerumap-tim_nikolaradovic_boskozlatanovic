package dsw.gerumap.app.gui.swing.view.painter;


import dsw.gerumap.app.mapRepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public abstract class DevicePainter{

    protected Element element;
    protected Shape shape;
    protected Color color;
    public DevicePainter(Element element){
        this.element = element;
    }
    public abstract void paint(Graphics2D g);
    public abstract boolean overlaps(Point point);

    public abstract boolean contains(Point point);
    public abstract void paintSelected(Graphics2D g);
    
}
