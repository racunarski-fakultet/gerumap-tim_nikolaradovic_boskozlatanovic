package dsw.gerumap.app.gui.swing.state.states;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.Command;
import dsw.gerumap.app.gui.swing.commands.implementations.AddElementCommand;
import dsw.gerumap.app.gui.swing.view.CustomDrawingPopUp;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.implementation.subElements.VezaElement;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.gui.swing.view.painter.VezaPainter;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.factory.utils.SubElements;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.gui.swing.state.State;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

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

        DevicePainter endPainter = tb.returnSelected(point);

        if(startPainter != null && endPainter != null){

            if ((startPainter.equals(endPainter) || endPainter instanceof VezaPainter) || tb.hasPainter(startPainter, endPainter)){
                tb.getPainters().remove(currentPainter);
                ((MapNodeComposite)tb.getMapNode()).removeChildren(el);
                tb.repaint();
                AppCore.getInstance().getMapRepository().removeChild(el);
                currentPainter = null;
                startPainter = null;
            }

        }

        if(endPainter == null){
            tb.getPainters().remove(currentPainter);
            tb.repaint();
            currentPainter = null;
            startPainter = null;
            return false;
        }
        if(currentPainter ==null && startPainter == null) return false;

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

        ((PojamPainter)endPainter).getVeze().add(currentPainter);
        ((PojamPainter)startPainter).getVeze().add(currentPainter);
        ((VezaElement)el).getElements().add(startPainter.getElement());
        ((VezaElement)el).getElements().add(endPainter.getElement());


        AppCore.getInstance().getMapRepository().rename(el,"Od " + startPainter.getElement().getName() + " do " +tb.returnSelected(point).getElement().getName());
        Command command = new AddElementCommand(currentPainter);
        MainFrame.getIntance().getCommandManager().addCommand(command);
        currentPainter = null;
        startPainter = null;

        return true;

    }
}
