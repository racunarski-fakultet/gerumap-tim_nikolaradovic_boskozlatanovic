package dsw.gerumap.app.gui.swing.tabbedPane.view;

import dsw.gerumap.app.gui.swing.tabbedPane.controller.MouseDragged;
import dsw.gerumap.app.gui.swing.tabbedPane.controller.MousePainter;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
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
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
            for (DevicePainter p: painters){
                p.paint(g2);
            }
        }
    }


    public boolean overlaps(Point point){

        for(DevicePainter p: painters){
            if(p.getShape().contains(point)) return true;
        }
        return false;
    }
    public DevicePainter returnSelected(Point point){
        for(DevicePainter p: painters){
            if(p.getShape().contains(point)) return p;
        }
        return null;
    }
}
