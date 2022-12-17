package dsw.gerumap.app.gui.swing.controller.projectViewControllers;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomOutAction extends AbstractGerumapAction {

    public ZoomOutAction() {
        putValue(NAME,"Zoom out ");
        putValue(SHORT_DESCRIPTION,"Zoom out");
        putValue(SMALL_ICON,loadIcon("/images/Zoom out.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getIntance().getProjectView().switchToZoomOutState();
    }
}
