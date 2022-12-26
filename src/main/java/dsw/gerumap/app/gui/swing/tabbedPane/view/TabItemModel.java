package dsw.gerumap.app.gui.swing.tabbedPane.view;

import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.mapRepository.implementation.subElements.VezaElement;
import dsw.gerumap.app.gui.swing.tabbedPane.controller.MouseDragged;
import dsw.gerumap.app.gui.swing.tabbedPane.controller.MousePainter;
import dsw.gerumap.app.gui.swing.tabbedPane.model.TabSelectionModel;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.gui.swing.view.painter.VezaPainter;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TabItemModel extends JPanel implements Subscriber {
    private MapNode mapNode;


    private int w;
    private int h;
    private double scalingFactor = 1.2;

    private double scaling = 1;

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

    /**
     * Save the Panel as image with the name and the type in parameters
     *
     */
    public void saveImage() {
        BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        paint(g2);
        try{
            ImageIO.write(image, "jpg", new File("export"+"."+"jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform affineTransform = AffineTransform.getTranslateInstance(xMove, yMove);
        affineTransform.scale(scaling, scaling);
        g2.transform(affineTransform);


        for (DevicePainter p: painters){
            if(p instanceof VezaPainter) {
                if (tabSelectionModel.getSelected().contains(p)) {
                        p.paintSelected(g2);

                }
                else {
                        p.paint(g2);
                }
            }
        }

        for (DevicePainter p: painters){
            if(!(p instanceof VezaPainter)) {
                if (tabSelectionModel.getSelected().contains(p)) {
                    p.paintSelected(g2);
                }
                else {
                    p.paint(g2);

                }
            }
        }
        g.drawLine(0, (int) (this.getHeight()/1.5),this.getWidth(), (int) (this.getHeight()/1.5));
        g.drawLine(0, (int) (this.getHeight()/3),this.getWidth(), (int) (this.getHeight()/3));

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

        if(scaling > 5){
            scaling = 5;
            repaint();
            return;
        }
        scaling *= scalingFactor;
        repaint();

    }
    public void zoomOut(){
        if(scalingFactor < 0.2){
            scalingFactor = 0.2;
            repaint();
            return;
        }

        scaling /= scalingFactor;
        repaint();

    }


    @Override
    public void update(Object obj, Enum e) {
        this.repaint();
    }

}
