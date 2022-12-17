package dsw.gerumap.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class ProjectViewToolBar extends JToolBar {
    public ProjectViewToolBar() {
        super(VERTICAL);
        setFloatable(false);
        add(MainFrame.getIntance().getActionManager().getZoomInAction());
        this.addSeparator(new Dimension(1,2));
        add(MainFrame.getIntance().getActionManager().getZoomOutAction());
        this.addSeparator(new Dimension(1,2));
        add(MainFrame.getIntance().getActionManager().getAddPojamAction());
        this.addSeparator(new Dimension(1,2));
        add(MainFrame.getIntance().getActionManager().getAddLinijaAction());
        this.addSeparator(new Dimension(1,2));
        add(MainFrame.getIntance().getActionManager().getMovePanelAction());
        this.addSeparator(new Dimension(1,2));
        add(MainFrame.getIntance().getActionManager().getDeleteElementsAction());
        this.addSeparator(new Dimension(1,2));
        add(MainFrame.getIntance().getActionManager().getSelectElementAction());
        this.addSeparator(new Dimension(1,2));
        add(MainFrame.getIntance().getActionManager().getChangeColorAction());


    }
}
