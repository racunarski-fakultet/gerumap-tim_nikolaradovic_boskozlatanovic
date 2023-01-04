package dsw.gerumap.app.gui.swing.state.states;

import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;

import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;

import dsw.gerumap.app.mapRepository.implementation.subElements.PojamElement;
import dsw.gerumap.app.mapRepository.implementation.subElements.VezaElement;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;

public class RepositionState extends State {

    private Deque<DevicePainter> painters;
    private List<DevicePainter> seen;
    private Deque<DevicePainter> children;


    double ugao;

    private boolean flag = true;
    double r;
    @Override
    public void execute(TabItemModel tb, Point point) {
        bfs(tb);
    }


    private void bfs(TabItemModel tb) {

        r = 300;
        ugao = 60;
        painters = new LinkedList<>();
        seen = new ArrayList<>();

        addToQueue((PojamPainter) tb.getPainters().get(0),tb, painters);
        bestFit(tb,tb.getPainters().get(0),r);
        r = (r/1.75);

        while (!painters.isEmpty()){

            int size = painters.size();

            for (int i = 0; i < size; i++){

                DevicePainter dp = painters.poll();

                if (!seen.contains(dp)){

                    addToQueue((PojamPainter) dp,tb, painters);
                    closestLine(tb,dp);

                    bestFit(tb, dp,r);
                    seen.add(dp);
                    //customSort(tb,koef1,koef2,dp);
                }
            }
            r = (r/1.5);
        }
    }

    private void addToQueue(PojamPainter pp,TabItemModel tb, Deque<DevicePainter> painters){

        PojamPainter pojamPainter = pp;

        for(DevicePainter veza : pojamPainter.getVeze()){

            if(!(((VezaElement)veza.getElement()).getElements().get(0).equals(pojamPainter.getElement()))){
                DevicePainter dv = tb.elementByPainter(((VezaElement)veza.getElement()).getElements().get(0));
                painters.add(dv);
            }
            else{
                DevicePainter dv = tb.elementByPainter(((VezaElement)veza.getElement()).getElements().get(1));
                painters.add(dv);
            }

        }
    }

    private void bestFit(TabItemModel tb, DevicePainter dp, double r){
        ugao = 0;

       PojamElement element = (PojamElement) dp.getElement();

       children = new LinkedList<>();

       addToQueue((PojamPainter) dp,tb,children);

       for (DevicePainter dv : children){

           if (dv instanceof PojamPainter && tb.getPainters().indexOf(dv) != 0 && !seen.contains(dv)){
               dv.getElement().setX((float) (r*Math.sin(Math.PI/180*ugao) + element.getCenterX() - ((PojamElement)dv.getElement()).getWidth()/2.f));
               dv.getElement().setY((float) (r*Math.cos(Math.PI/180*ugao) + element.getCenterY() - ((PojamElement)dv.getElement()).getHeight()/2.f));

               ((PojamElement) dv.getElement()).setCenterX((float) (r*Math.sin(Math.PI/180*ugao) + element.getCenterX()));
               ((PojamElement) dv.getElement()).setCenterY((float) (r*Math.cos(Math.PI/180*ugao) + element.getCenterY()));
               System.out.println(ugao);
              /** if (flag){
                   ugao +=45;
               }
               else {
                   ugao += 45;
               }
               */

           }
           ugao+=55;
           ugao %= 360;
       }
        //System.out.println(ugao);
        tb.repaint();
    }

    private void closestLine(TabItemModel tb,DevicePainter dp) {

   /**     if (dp instanceof PojamPainter && tb.getPainters().indexOf(dp) != 0){

            double upperHorizontal = Line2D.ptLineDist(0, (int) (tb.getHeight()/1.0005),tb.getWidth(), (int) (tb.getHeight()/1.0005),dp.getElement().getX(),dp.getElement().getY());
            double underHorizontal = Line2D.ptLineDist(0, (int) (tb.getHeight()/(tb.getHeight()/1.1)),tb.getWidth(), (int) (tb.getHeight()/(tb.getHeight()/1.1)),dp.getElement().getX(),dp.getElement().getY());
            double rightVertical = Line2D.ptLineDist((tb.getWidth()/1.0005), 0, (int) (tb.getWidth()/1.0005),(tb.getHeight()),dp.getElement().getX(),dp.getElement().getY());
            double leftVertical = Line2D.ptLineDist((int) (tb.getWidth()/(tb.getWidth()/1.0005)), 0, (int) (tb.getWidth()/(tb.getWidth()/1.0005)), (int) (tb.getHeight()),dp.getElement().getX(),dp.getElement().getY());

            if (upperHorizontal < underHorizontal){

                if (upperHorizontal < rightVertical && upperHorizontal < leftVertical){

                    ugao = 90;
                    flag = false;

                }
                else if (upperHorizontal > rightVertical){
                    //right vertical
                    ugao = 0;
                    flag = true;
                }
                else {
                    //left vertical
                    ugao = 180;
                    flag = false;

                }
            }
            else if (underHorizontal < upperHorizontal) {

                if (underHorizontal < rightVertical && underHorizontal < leftVertical){
                    ugao = 270;
                    flag = true;
                }
                else if (underHorizontal > rightVertical){
                    //right vertical
                    ugao = 0;
                    flag = true;
                }
                else {
                    //left vertical
                    ugao = 180;
                    flag = false;
                }
            }
            else if (rightVertical < leftVertical) {

                if (rightVertical < upperHorizontal && rightVertical < underHorizontal){
                    ugao = 0;
                    flag = true;

                }
                else if(rightVertical > underHorizontal){
                    // under horizontal
                    ugao = 270;
                    flag = true;
                }
                else {
                    //upper horizontal
                    ugao = 90;
                    flag = false;
                }

            }
            else {
                if (leftVertical < upperHorizontal && leftVertical < underHorizontal){

                    ugao = 180;
                    flag = false;
                }
                else if(leftVertical > underHorizontal){
                    // under horizontal
                    ugao = 270;
                    flag = true;
                }
                else {
                    //upper horizontal
                    ugao = 90;
                    flag = false;
                }
            }


        }
    */

    }

    @Override
    public void drag(TabItemModel tb, Point point) {

    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {
        return false;
    }


}
