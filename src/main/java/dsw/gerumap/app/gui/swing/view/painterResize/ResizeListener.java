package dsw.gerumap.app.gui.swing.view.painterResize;

import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painter.VezaPainter;
import dsw.gerumap.app.mapRepository.implementation.subElements.VezaElement;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ResizeListener extends ComponentAdapter {

    private DevicePainter glavniPojam;

    private float starX;
    private float startY;

    private float currentX;

    private float currentY;

    public void componentResized(ComponentEvent e) {

        if (MainFrame.getIntance().getTabbedPane().getContainer().size() == 0) return;

        for (DevicePainter dp: ((TabItemModel)MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getPainters()){
            if (dp.getElement().getStroke() == 5){
                glavniPojam = dp;
                starX = ((TabItemModel)MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getW()/2;
                startY = ((TabItemModel)MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getH()/2;

                currentX = ((TabItemModel)MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getWidth()/2;
                currentY = ((TabItemModel)MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getHeight()/2;
                dp.getElement().setX(currentX);
                dp.getElement().setY(currentY);
            }
        }
        float dx = currentX - starX;
        float dy = currentY - startY;

//        for(DevicePainter dp: ((TabItemModel)MainFrame.getIntance().getTabbedPane().getSelectedComponent()).getPainters()){
//
//            if (dp instanceof VezaPainter){
//                float newLineX1 = dp.getElement().getX() - dx;
//                float newLineY1 = dp.getElement().getY() - dy;
//                float newLineX2 = ((VezaElement)dp.getElement()).getX2() - dx;
//                float newLineY2 = ((VezaElement)dp.getElement()).getY2() - dy;
//
//                dp.getElement().setX(newLineX1);
//                dp.getElement().setY(newLineY1);
//                ((VezaElement) dp.getElement()).setX2(newLineX2);
//                ((VezaElement) dp.getElement()).setY2(newLineY2);
//            }
//            else {
//
//                float newEllipseX = dp.getElement().getX() - dx;
//                float newEllipseY = dp.getElement().getY() - dy;
//
//
//                dp.getElement().setX(newEllipseX);
//                dp.getElement().setY(newEllipseY);
//
//            }
//
//        }
        ((TabItemModel)MainFrame.getIntance().getTabbedPane().getSelectedComponent()).repaint();
    }
}
