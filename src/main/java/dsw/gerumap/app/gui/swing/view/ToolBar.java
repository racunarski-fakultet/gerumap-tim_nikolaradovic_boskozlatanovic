package dsw.gerumap.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {

    public ToolBar() {
        super(HORIZONTAL);
        setFloatable(false);
        add(MainFrame.getIntance().getActionManager().getNewProjectAction());
        this.addSeparator(new Dimension(3,3));
        add(MainFrame.getIntance().getActionManager().getInfoAction());
        this.addSeparator(new Dimension(3,3));
        add(MainFrame.getIntance().getActionManager().getDeleteAction());
        this.addSeparator(new Dimension(3,3));
        add(MainFrame.getIntance().getActionManager().getAddAuthorAction());
        this.addSeparator(new Dimension(3,3));
        add(MainFrame.getIntance().getActionManager().getRenameAction());
        this.addSeparator(new Dimension(3,3));
        add(MainFrame.getIntance().getActionManager().getSaveAction());
    }

}
