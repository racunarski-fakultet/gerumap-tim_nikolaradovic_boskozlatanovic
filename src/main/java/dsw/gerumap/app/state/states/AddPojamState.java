package dsw.gerumap.app.state.states;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.gui.swing.elements.PojamElement;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.mapRepository.factory.utils.SubElements;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.state.State;

import java.awt.*;

public class AddPojamState extends State {
    @Override
    public void execute(TabItemModel tb, Point point) {

        Element el = (Element) AppCore.getInstance().getMapRepository().addChild(tb.getMapNode(), "Test",SubElements.POJAM);

        el.setX(point.x);
        el.setY(point.y);
        DevicePainter painter = new PojamPainter(el);

        painter.paint((Graphics2D) tb.getPanel().getGraphics());
        tb.getPainters().add(painter);

    }
}
