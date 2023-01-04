package dsw.gerumap.app.gui.swing.controller.projectViewControllers;

import dsw.gerumap.app.gui.swing.controller.AbstractGerumapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class GlavniPojamAction extends AbstractGerumapAction {

    public GlavniPojamAction(){
        putValue(NAME,"Add concept");
        putValue(SHORT_DESCRIPTION,"Glavni Pojam");
        putValue(SMALL_ICON,loadIcon("/images/Rounded rectangle.png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getIntance().getProjectView().switchToGlavniPojamState();
    }
}
