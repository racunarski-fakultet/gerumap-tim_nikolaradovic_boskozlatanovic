package dsw.gerumap.app.gui.swing.view;

import javax.swing.*;

public class ToolBar extends JToolBar {

    public ToolBar() {
        super(HORIZONTAL);
        setFloatable(false);
        add(MainFrame.getIntance().getActionManager().getNewProjectAction());
        add(MainFrame.getIntance().getActionManager().getInfoAction());
    }

}
