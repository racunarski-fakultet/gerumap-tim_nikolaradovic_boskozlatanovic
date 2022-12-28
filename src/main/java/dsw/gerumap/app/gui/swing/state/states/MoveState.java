package dsw.gerumap.app.gui.swing.state.states;

import dsw.gerumap.app.mapRepository.commands.implementations.MoveElementsCommand;
import dsw.gerumap.app.mapRepository.implementation.subElements.PojamElement;
import dsw.gerumap.app.mapRepository.implementation.subElements.VezaElement;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.gui.swing.view.painter.SelectioElements;
import dsw.gerumap.app.gui.swing.view.painter.VezaPainter;
import dsw.gerumap.app.gui.swing.state.State;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MoveState extends State {

    private Integer startX = 0;
    private Integer startY = 0;
    private DevicePainter rectangle;
    DevicePainter currentylSelected;
    private boolean released = true;

    @Override
    public void execute(TabItemModel tb, Point point) {
        rectangle = findSelectionElement(tb);
        //currentylSelected = tb.returnSelected(point);
        if(rectangle != null && rectangle.contains(point)){
            startX = point.x;
            startY = point.y;
            released = false;
        }
        else if(tb.returnSelected(point) == null && currentylSelected != null){
            MainFrame.getIntance().getProjectView().switchToSelectState();
            MainFrame.getIntance().getProjectView().getStateManager().getCurrentState().execute(tb,point);
            currentylSelected = null;
        }
        else if (currentylSelected != null && !tb.returnSelected(point).equals(currentylSelected)  && !(currentylSelected instanceof SelectioElements)) {
            MainFrame.getIntance().getProjectView().switchToSelectState();
            MainFrame.getIntance().getProjectView().getStateManager().getCurrentState().execute(tb,point);
            currentylSelected = null;

        }
        else if (currentylSelected == null && tb.returnSelected(point) == null){
            MainFrame.getIntance().getProjectView().switchToSelectState();
            MainFrame.getIntance().getProjectView().getStateManager().getCurrentState().execute(tb,point);
        }
        else{
            released = false;
        }
    }

    @Override
    public void drag(TabItemModel tb, Point point) {



        if(tb.getTabSelectionModel().getSelected().size() == 1 && !released && findSelectionElement(tb) == null){


           currentylSelected = tb.getTabSelectionModel().getSelected().get(0);

           for (DevicePainter v : ((PojamPainter)currentylSelected).getVeze()){


               if (((VezaElement)v.getElement()).getElements().indexOf(currentylSelected.getElement()) == 0){


                   v.getElement().setX(point.x);
                   v.getElement().setY(point.y);

               }
               else {

                   ((VezaElement) v.getElement()).setX2(point.x);
                   ((VezaElement) v.getElement()).setY2(point.y);
               }
           }

            float dx = point.x - ((PojamElement)currentylSelected.getElement()).getWidth()/2.f;
            float dy = point.y - ((PojamElement)currentylSelected.getElement()).getHeight()/2.f;

            currentylSelected.getElement().setX(dx);
            currentylSelected.getElement().setY(dy);


            tb.repaint();
        }
        else if(!released && rectangle.contains(point)){

            if(rectangle == null) return;

            int dx = startX - point.x;
            int dy = startY - point.y;

            double newX = ((Rectangle2D) rectangle.getShape()).getX() - dx;
            double newY = ((Rectangle2D) rectangle.getShape()).getY() - dy;



            ((Rectangle2D)rectangle.getShape()).setFrame(newX,newY,((Rectangle2D) rectangle.getShape()).getWidth(),((Rectangle2D) rectangle.getShape()).getHeight());

            for (DevicePainter p: tb.getTabSelectionModel().getSelected()){

                if (p instanceof PojamPainter){
                    float newEllipseX = p.getElement().getX() - dx;
                    float newEllipseY = p.getElement().getY() - dy;


                    p.getElement().setX(newEllipseX);
                    p.getElement().setY(newEllipseY);
                }
                else if (p instanceof VezaPainter){

                    float newLineX1 = p.getElement().getX() - dx;
                    float newLineY1 = p.getElement().getY() - dy;
                    float newLineX2 = ((VezaElement)p.getElement()).getX2() - dx;
                    float newLineY2 = ((VezaElement)p.getElement()).getY2() - dy;

                    p.getElement().setX(newLineX1);
                    p.getElement().setY(newLineY1);
                    ((VezaElement) p.getElement()).setX2(newLineX2);
                    ((VezaElement) p.getElement()).setY2(newLineY2);

                }



                tb.repaint();
            }

            startX = point.x;

            startY = point.y;
            tb.repaint();


        }
    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {

        ((MoveElementsCommand)MainFrame.getIntance().getProjectView().getStateManager().getSelectionElementsState().getMoveElementsCommand()).setEndingPoints();
        tb.getMapNode().getCommandManager().addCommand(MainFrame.getIntance().getProjectView().getStateManager().getSelectionElementsState().getMoveElementsCommand());


        released = true;
        return false;
    }


    private DevicePainter findSelectionElement(TabItemModel tb){
        for (DevicePainter p: tb.getPainters()){
            if(p instanceof SelectioElements){
                return p;
            }
        }
        return null;
    }
}

