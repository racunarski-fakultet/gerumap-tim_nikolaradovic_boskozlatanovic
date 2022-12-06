package dsw.gerumap.app.state.states;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.errorHandling.EventType;
import dsw.gerumap.app.gui.swing.elements.PojamElement;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.mapRepository.factory.utils.SubElements;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.state.State;

import javax.swing.*;
import java.awt.*;

public class AddPojamState extends State {
    @Override
    public void execute(TabItemModel tb, Point point) {

        if (tb.overlaps(point)) {
            return;
        }

        String name = JOptionPane.showInputDialog(MainFrame.getIntance(),
                "Ime pojma");

        if(name == null)return;

        else if(name.isEmpty()){
            AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NAME_CANNOT_BE_EMPTY);
            return;

        }



        Element el = (Element) AppCore.getInstance().getMapRepository().addChild(tb.getMapNode(), name,SubElements.POJAM);

        el.setX(point.x);
        el.setY(point.y);
        DevicePainter painter = new PojamPainter(el);


        tb.getPainters().add(painter);
        tb.repaint();
    }
}
