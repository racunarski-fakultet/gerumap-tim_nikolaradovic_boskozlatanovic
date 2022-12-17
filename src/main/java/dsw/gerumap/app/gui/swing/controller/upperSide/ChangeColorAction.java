package dsw.gerumap.app.gui.swing.controller.upperSide;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.VezaPainter;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ChangeColorAction extends AbstractGerumapAction {

    public ChangeColorAction(){
        putValue(NAME,"Settings");
        putValue(SHORT_DESCRIPTION,"Settings");
        putValue(SMALL_ICON,loadIcon("/images/Graphic designer.png"));
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        TabItemModel tb = ((TabItemModel)MainFrame.getIntance().getTabbedPane().getSelectedComponent());
        if(tb.getTabSelectionModel().getSelected().size() != 0){
            Color c = JColorChooser.showDialog(MainFrame.getIntance(), "Select Color", Color.BLUE);
            if(c == null)
                return;
            for(DevicePainter dp: tb.getTabSelectionModel().getSelected()){
               if(dp instanceof VezaPainter){
                   Color color = JColorChooser.showDialog(MainFrame.getIntance(), "Select VezaColor", Color.BLUE);
                   dp.getElement().getPaint()[0] = color.getRed();
                   dp.getElement().getPaint()[1] = color.getGreen();
                   dp.getElement().getPaint()[2] = color.getBlue();
               }
               else {
                   dp.getElement().getPaint()[0] = c.getRed();
                   dp.getElement().getPaint()[1] = c.getGreen();
                   dp.getElement().getPaint()[2] = c.getBlue();
               }
           }
           tb.getTabSelectionModel().getSelected().removeAll(tb.getTabSelectionModel().getSelected());
           tb.repaint();
        }

    }
}
