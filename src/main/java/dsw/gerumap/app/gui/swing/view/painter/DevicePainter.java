package dsw.gerumap.app.gui.swing.view.painter;


import dsw.gerumap.app.mapRepository.implementation.Element;

import java.awt.*;

public abstract class DevicePainter{
    public Element element;
    public DevicePainter(Element element){
        this.element = element;
    }
    public abstract void paint(Graphics2D g);

}
