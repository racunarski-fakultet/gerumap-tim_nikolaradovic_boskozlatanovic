package dsw.gerumap.app.gui.swing.tabbedPane.view;

import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.elements.VezaElement;
import dsw.gerumap.app.gui.swing.tabbedPane.controller.MouseDragged;
import dsw.gerumap.app.gui.swing.tabbedPane.controller.MousePainter;
import dsw.gerumap.app.gui.swing.tabbedPane.model.TabSelectionModel;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.gui.swing.view.painter.VezaPainter;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class TabItemModel extends JPanel implements Subscriber {
    private MapNode mapNode;

    private AffineTransform transform = new AffineTransform();

    private double scailingFactor = 1;

    private double xMove;
    private double yMove;

    private double oldX;
    private double oldY;

    private TabSelectionModel tabSelectionModel;
    private List<DevicePainter> painters = new ArrayList<DevicePainter>();

    public TabItemModel(MapNode mapNode) {

        tabSelectionModel = new TabSelectionModel();
        tabSelectionModel.addSubscriber(this);
        this.mapNode = mapNode;

        this.addMouseListener(new MousePainter(this));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBackground(Color.WHITE);
        this.addMouseMotionListener(new MouseDragged(this));

    }

    @Override
    public void paintComponent(Graphics g) {
        if(painters.size() == 0){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            //g2.setTransform(transform);



        }
        else{
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            //g2.setTransform(transform);

            for (DevicePainter p: painters){
                if(p instanceof VezaPainter) {
                    if (tabSelectionModel.getSelected().contains(p)) {
                        p.paintSelected(g2);

                    } else {
                        p.paint(g2);
                    }
                }
            }

            for (DevicePainter p: painters){
                if(!(p instanceof VezaPainter)) {
                    if (tabSelectionModel.getSelected().contains(p)) {
                        p.paintSelected(g2);
                    } else {
                        p.paint(g2);
                    }
                }
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
            if(p.contains(point)) return p;
        }
        return null;
    }

    public boolean hasPainter(DevicePainter startPainter,DevicePainter endPainter){

        if(startPainter == null || endPainter == null) return false;

        for (DevicePainter p: painters){

            if(p.getElement() instanceof VezaElement && startPainter instanceof PojamPainter && endPainter instanceof PojamPainter){
                if(((VezaPainter)p).hasElements(startPainter,endPainter)) return true;
            }

        }
        return false;

    }

    public void zoomIn(){
        if(scailingFactor > 5){
            scailingFactor = 5;
            return;
        }
        if(scailingFactor < 1) scailingFactor =1;
        scailingFactor *= 1.05;
        setupTransform();

    }
    public void zoomOut(){
        if(scailingFactor < 0.2){
            scailingFactor = 0.2;
            return;
        }
        if(scailingFactor > 1) scailingFactor = 1;
        scailingFactor /= 1.05;
        setupTransform();

    }

    public void setupTransform(){
        transform.scale(scailingFactor,scailingFactor);
        xMove = (1-scailingFactor) * oldX;
        yMove = (1-scailingFactor) * oldY;
        transform.translate(xMove, yMove);
        repaint();
    }

    public DevicePainter getPainerByElement(Element element){
        for (DevicePainter d: painters){
            if(d.getElement().equals(element)) return d;
        }
        return null;
    }

    public void transformToUSerSpace(Point2D deviceSpace){
        try{
            transform.inverseTransform(deviceSpace, deviceSpace);
        }catch (NoninvertibleTransformException e){
            e.printStackTrace();
        }
    }
    @Override
    public void update(Object obj, Enum e) {
        this.repaint();
    }

}
