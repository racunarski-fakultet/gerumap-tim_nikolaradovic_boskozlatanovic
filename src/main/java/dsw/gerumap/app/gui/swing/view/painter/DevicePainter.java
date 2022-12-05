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
    public DevicePainter(Element element){
        this.element = element;
    }
    public abstract void paint(Graphics2D g);

}
