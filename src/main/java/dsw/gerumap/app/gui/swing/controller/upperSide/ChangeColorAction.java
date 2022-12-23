package dsw.gerumap.app.gui.swing.controller.upperSide;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.gui.swing.view.painter.VezaPainter;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ChangeColorAction extends AbstractGerumapAction {

    public ChangeColorAction(){
        putValue(NAME,"Settings");
        putValue(SHORT_DESCRIPTION,"Settings");
        putValue(SMALL_ICON,loadIcon("/images/Graphic designer.png"));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<PojamPainter> pojmovi = new ArrayList<>();
        ArrayList<VezaPainter> veze1 = new ArrayList<>();
        TabItemModel tb = ((TabItemModel)MainFrame.getIntance().getTabbedPane().getSelectedComponent());
        if(tb.getTabSelectionModel().getSelected().size() != 0){

          for(DevicePainter dp: tb.getTabSelectionModel().getSelected()){
                if(dp instanceof VezaPainter) {
                    veze1.add((VezaPainter) dp);
                } else{
                    pojmovi.add((PojamPainter) dp);
                }
            }
            Color color = null;
            if(pojmovi.size() != 0) {
                color = JColorChooser.showDialog(MainFrame.getIntance(), "Select PojamColor", Color.BLUE);
            }
            for(PojamPainter pp: pojmovi){
                if (color == null)
                    return;
                pp.getElement().getPaint()[0] = color.getRed();
                pp.getElement().getPaint()[1] = color.getGreen();
                pp.getElement().getPaint()[2] = color.getBlue();
            }
            Color color2 = null;
            if(veze1.size() != 0) {
                color2 = JColorChooser.showDialog(MainFrame.getIntance(), "Select VezeColor", Color.BLACK);
            }
            for(VezaPainter vp: veze1){
                if (color2 == null)
                    return;
                vp.getElement().getPaint()[0] = color2.getRed();
                vp.getElement().getPaint()[1] = color2.getGreen();
                vp.getElement().getPaint()[2] = color2.getBlue();
            }


           tb.getTabSelectionModel().getSelected().removeAll(tb.getTabSelectionModel().getSelected());
           tb.repaint();
        }
    }
}
