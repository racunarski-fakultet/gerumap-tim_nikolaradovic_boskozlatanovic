package dsw.gerumap.app.state.states;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.errorHandling.EventType;
import dsw.gerumap.app.gui.swing.elements.PojamElement;
import dsw.gerumap.app.gui.swing.elements.VezaElement;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.gui.swing.view.painter.VezaPainter;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.factory.utils.SubElements;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.state.State;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

@Getter
@Setter
public class AddLinijaState extends State {

    private DevicePainter currentPainter;
    private DevicePainter startPainter;
    private Element el;
    @Override
    public void execute(TabItemModel tb, Point point) {
        startPainter = tb.returnSelected(point);
        if (startPainter == null || startPainter instanceof VezaPainter) {
            startPainter = null;

            return;
        }

        el = (Element) AppCore.getInstance().getMapRepository().addChild(tb.getMapNode(), "Od " + startPainter.getElement().getName() + " do ", SubElements.VEZA);

        el.setX(point.x);
        el.setY(point.y);
        DevicePainter painter = new VezaPainter(el);
        ((VezaElement)el).setX2(el.getX());
        ((VezaElement)el).setY2(el.getY());
        ((VezaElement)el).getElements().add(startPainter.getElement());




        tb.getPainters().add(painter);
        tb.repaint();
        currentPainter = painter;
       // ((PojamPainter)startPainter).getVeze().add(currentPainter);
    }

    @Override
    public void drag(TabItemModel tb, Point point) {

        if (currentPainter == null) return;

        ((VezaElement)currentPainter.getElement()).setX2(point.x);
        ((VezaElement)currentPainter.getElement()).setY2(point.y);

        tb.repaint();
    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {

        DevicePainter endPinter  = tb.returnSelected(point);

        if(startPainter != null && endPinter != null){
            boolean b1 = tb.hasPainter(startPainter,endPinter);
            if (startPainter.equals(endPinter) || endPinter instanceof VezaPainter && tb.hasPainter(startPainter,endPinter)){
                tb.getPainters().remove(currentPainter);
                ((MapNodeComposite)tb.getMapNode()).removeChildren(el);
                tb.repaint();
                AppCore.getInstance().getMapRepository().removeChild(el);
                currentPainter = null;
                startPainter = null;
            }

            return false;
        }

        if(endPinter == null){
            tb.getPainters().remove(currentPainter);
            tb.repaint();
            currentPainter = null;
            startPainter = null;
            return false;
        }
        if(currentPainter ==null && startPainter == null) return false;

        ((PojamPainter)tb.returnSelected(point)).getVeze().add(currentPainter);
        ((VezaElement)currentPainter.getElement()).getElements().add(startPainter.getElement());
        ((VezaElement)currentPainter.getElement()).getElements().add(endPinter.getElement());

        AppCore.getInstance().getMapRepository().rename(el,"Od " + startPainter.getElement().getName() + " do " +tb.returnSelected(point).getElement().getName());
        currentPainter = null;
        startPainter = null;
        ((VezaElement)el).getElements().add(tb.returnSelected(point).getElement());
        return true;

    }
}
