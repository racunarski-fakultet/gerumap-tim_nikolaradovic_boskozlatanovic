package dsw.gerumap.app.state.states;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.errorHandling.EventType;
import dsw.gerumap.app.gui.swing.elements.VezaElement;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
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
    @Override
    public void execute(TabItemModel tb, Point point) {
        if (!tb.overlaps(point)) {
            return;
        }

        Element el = (Element) AppCore.getInstance().getMapRepository().addChild(tb.getMapNode(), "", SubElements.VEZA);

        el.setX(point.x);
        el.setY(point.y);
        DevicePainter painter = new PojamPainter(el);
        ((VezaElement)el).setX2(el.getX());
        ((VezaElement)el).setY2(el.getY());



        tb.getPainters().add(painter);
        tb.repaint();
        currentPainter = painter;
    }

    @Override
    public void drag(TabItemModel tb, Point point) {


        ((VezaElement)currentPainter.getElement()).setX2(point.x);
        ((VezaElement)currentPainter.getElement()).setX2(point.y);

        tb.repaint();
    }
}
