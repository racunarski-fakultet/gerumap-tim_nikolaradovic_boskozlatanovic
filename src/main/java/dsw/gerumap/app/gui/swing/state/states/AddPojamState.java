package dsw.gerumap.app.gui.swing.state.states;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.Command;
import dsw.gerumap.app.errorHandling.EventType;
import dsw.gerumap.app.mapRepository.commands.implementations.AddElementCommand;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.CustomDrawingPopUp;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.mapRepository.factory.utils.SubElements;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.gui.swing.state.State;

import javax.swing.*;
import java.awt.*;

public class AddPojamState extends State {
    @Override
    public void execute(TabItemModel tb, Point point) {

        if (tb.overlaps(point)) {
            return;
        }

        if(tb.getPainters().size() == 0){
            AppCore.getInstance().getMessageGenerator().generateMessage(EventType.GlAVNI_POJAM);
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
        Command command = new AddElementCommand(el,tb.getMapNode());
        tb.getMapNode().getCommandManager().addCommand(command);

        CustomDrawingPopUp cdw = new CustomDrawingPopUp();

        int response = cdw.makePopUp();

        if(response == JOptionPane.OK_OPTION){
            if ( cdw.getStroke().getText() != null &&  !cdw.getStroke().getText().isEmpty() && !(cdw.getStroke().getText().matches(".*[a-zA-Z]+.*"))){

                el.setStroke((int) Float.parseFloat(cdw.getStroke().getText()));

            }
            if(cdw.getC() != null){
                el.setPaint(new int[]{cdw.getC().getRed(), cdw.getC().getGreen(), cdw.getC().getBlue()});
            }
        }
        tb.getPainters().add(painter);
        tb.repaint();
    }

    @Override
    public void drag(TabItemModel tb, Point point) {

    }

    @Override
    public boolean isConnected(TabItemModel tb, Point point) {
        return false;
    }
}
