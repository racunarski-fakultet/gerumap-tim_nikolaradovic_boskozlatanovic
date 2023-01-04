package dsw.gerumap.app.mapRepository.commands.implementations;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.Command;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.mapRepository.implementation.subElements.VezaElement;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter

public class MoveElementsCommand implements Command {

    private HashMap<Element, float[]> startingPoints;
    private HashMap<Element, float[]> endingPoints;
    private List<Element> selectedElements;


    public MoveElementsCommand() {
        selectedElements = new ArrayList<>();
        endingPoints = new HashMap<>();
        startingPoints = new HashMap<>();
    }

    @Override
    public void doCommand() {

        removeElements();

        for (Element element: selectedElements){
            float[] points = endingPoints.get(element);
            if (element instanceof VezaElement){

                element.setX(points[0]);
                element.setY(points[1]);
                ((VezaElement) element).setX2(points[2]);
                ((VezaElement) element).setY2(points[3]);
            }
            else {
                element.setX(points[0]);
                element.setY(points[1]);
            }


        }
        addElements();
    }

    @Override
    public void undoCommand() {
        removeElements();

        for (Element element: selectedElements){

            float[] points = startingPoints.get(element);

            if (element instanceof VezaElement){

                element.setX(points[0]);
                element.setY(points[1]);
                ((VezaElement) element).setX2(points[2]);
                ((VezaElement) element).setY2(points[3]);
            }
            else {
                element.setX(points[0]);
                element.setY(points[1]);
            }

        }
        addElements();
    }

    public void setElements(List<Element> elements){

        selectedElements = elements;

        for (Element el: selectedElements){
            if (el instanceof VezaElement){
                startingPoints.put(el, new float[]{el.getX(), el.getY(), ((VezaElement) el).getX2(), ((VezaElement) el).getY2()});
            }
            else {
                startingPoints.put(el, new float[]{el.getX(), el.getY()});
            }
        }
    }
    public void setEndingPoints(){

        for (Element el : selectedElements){

            if (el instanceof VezaElement){
                endingPoints.put(el, new float[]{el.getX(), el.getY(), ((VezaElement) el).getX2(), ((VezaElement) el).getY2()});
            }
            else {
                endingPoints.put(el, new float[]{el.getX(), el.getY()});
            }
        }

    }

    private void removeElements(){

        for (Element el: selectedElements){
            AppCore.getInstance().getMapRepository().removeChild(el);
        }

    }

    private void addElements(){

        for (Element el: selectedElements){
            AppCore.getInstance().getMapRepository().addChild(el.getParent(),el);
        }
    }
}
