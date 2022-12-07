package dsw.gerumap.app.gui.swing.tabbedPane.view;

import dsw.gerumap.app.gui.swing.elements.VezaElement;
import dsw.gerumap.app.gui.swing.tabbedPane.controller.MouseDragged;
import dsw.gerumap.app.gui.swing.tabbedPane.controller.MousePainter;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class TabItemModel extends JPanel{
    private MapNode mapNode;
    //private JPanel panel;
    Random r = new Random();
    private List<DevicePainter> painters = new ArrayList<DevicePainter>();
    public TabItemModel(MapNode mapNode) {

        this.mapNode = mapNode;
        //this.panel = new JPanel();
        this.addMouseListener(new MousePainter(this));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBackground(Color.WHITE);
        this.addMouseMotionListener(new MouseDragged(this));
      //  panel.add(new Label("string" + r.nextInt(100)));
    }

    @Override
    public void paintComponent(Graphics g) {
        if(painters.size() == 0){
            super.paintComponent(g);

        }
        else{
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            for (DevicePainter p: painters){
                p.paint(g2);
            }
        }
    }


    public boolean overlaps(Point point){

        for(DevicePainter p: painters){
            if(p.overlaps(point)) return true;
        }
        return false;
    }


    public DevicePainter returnSelected(Point point){
        for(DevicePainter p: painters){
            if(p.getShape().contains(point)) return p;
        }
        return null;
    }

    public boolean hasPainter(DevicePainter painter){
        if(painter == null) return false;

        for (DevicePainter p: painters){
            if(p.getElement() instanceof VezaElement){
                if(((VezaElement)p.getElement()).getElements().contains(painter.getElement())) return true;
            }
        }
        return false;

    }
}
