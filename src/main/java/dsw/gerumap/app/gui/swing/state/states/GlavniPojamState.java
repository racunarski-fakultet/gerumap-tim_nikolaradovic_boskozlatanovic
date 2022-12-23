package dsw.gerumap.app.gui.swing.state.states;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.Command;
import dsw.gerumap.app.errorHandling.EventType;
import dsw.gerumap.app.gui.swing.commands.implementations.AddElementCommand;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.CustomDrawingPopUp;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.mapRepository.factory.utils.SubElements;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.mapRepository.implementation.subElements.PojamElement;

import javax.swing.*;
import java.awt.*;

public class GlavniPojamState extends State {
    @Override
    public void execute(TabItemModel tb, Point point) {
        if (tb.overlaps(point)) {
            return;
        }

        String name = JOptionPane.showInputDialog(MainFrame.getIntance(),
                "Ime Glavnog Pojma");

        if(name == null)return;

        else if(name.isEmpty()){
            AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NAME_CANNOT_BE_EMPTY);
            return;

        }
        Element el = (Element) AppCore.getInstance().getMapRepository().addChild(tb.getMapNode(), name, SubElements.POJAM);
        int x = MainFrame.getIntance().getTabbedPane().getWidth()/2;
        int y = MainFrame.getIntance().getTabbedPane().getHeight()/2;
        ((PojamElement) el).setHeight(65);
        ((PojamElement) el).setWidth(130);
        el.setX(x-((PojamElement)el).getWidth()/2);
        el.setY(y-((PojamElement)el).getHeight()/2);

        DevicePainter painter = new PojamPainter(el);

      //  CustomDrawingPopUp cdw = new CustomDrawingPopUp();

      //  int response = cdw.makePopUp();

//        if(response == JOptionPane.OK_OPTION){
//            if ( cdw.getStroke().getText() != null &&  !cdw.getStroke().getText().isEmpty() && !(cdw.getStroke().getText().matches(".*[a-zA-Z]+.*"))){
//
//                el.setStroke(10);
//
//            }
//            if(cdw.getC() != null){
//                el.setPaint(new int[]{cdw.getC().getRed(), cdw.getC().getGreen(), cdw.getC().getBlue()});
//            }
//        }
        int[] boje = {173, 216, 231};
        el.setStroke(5);
        el.setPaint(boje);
        Command command = new AddElementCommand((PojamPainter) painter);
        MainFrame.getIntance().getCommandManager().addCommand(command);


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
