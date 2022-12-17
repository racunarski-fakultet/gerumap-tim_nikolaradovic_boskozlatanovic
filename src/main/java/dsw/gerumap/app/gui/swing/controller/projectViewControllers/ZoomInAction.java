package dsw.gerumap.app.gui.swing.controller.projectViewControllers;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomInAction extends AbstractGerumapAction {

    public ZoomInAction() {
        putValue(NAME,"Zoom in ");
        putValue(SHORT_DESCRIPTION,"Zoom in");
        putValue(SMALL_ICON,loadIcon("/images/Zoom in.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getIntance().getProjectView().switchToZoomInState();
    }
}
