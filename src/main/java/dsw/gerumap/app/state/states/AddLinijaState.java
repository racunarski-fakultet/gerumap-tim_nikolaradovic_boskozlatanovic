package dsw.gerumap.app.state.states;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.errorHandling.EventType;
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

@Getter
@Setter
public class AddLinijaState extends State {

    private DevicePainter currentPainter;
    private DevicePainter startPainter;
    @Override
    public void execute(TabItemModel tb, Point point) {
        startPainter = tb.overlaps(point);
        if (startPainter == null) {
            return;
        }

        Element el = (Element) AppCore.getInstance().getMapRepository().addChild(tb.getMapNode(), "", SubElements.VEZA);

        el.setX(point.x);
        el.setY(point.y);
        DevicePainter painter = new VezaPainter(el);
        ((VezaElement)el).setX2(el.getX());
        ((VezaElement)el).setY2(el.getY());



        tb.getPainters().add(painter);
        tb.repaint();
        currentPainter = painter;
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

        if (currentPainter != null && startPainter != null && (tb.overlaps(point) == null || tb.overlaps(point).equals(startPainter))){
            tb.getPainters().remove(currentPainter);
            ((MapNodeComposite)tb.getMapNode()).removeChildren(currentPainter.getElement());
            tb.repaint();

            return false;
        }
        currentPainter = null;
        startPainter = null;
        return true;

    }
}
