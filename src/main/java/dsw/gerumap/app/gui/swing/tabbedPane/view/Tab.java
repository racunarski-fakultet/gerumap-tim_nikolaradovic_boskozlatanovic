package dsw.gerumap.app.gui.swing.tabbedPane.view;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Project;

import javax.swing.*;

public class Tab extends JTabbedPane {
    private MapNode mapNode;

    public Tab(MapNode mapNode) {
        super(JTabbedPane.TOP,JTabbedPane.WRAP_TAB_LAYOUT);
        this.mapNode = mapNode;
            this.addTab(mapNode.getName(), null);
//            this.setComponentAt();

    }
}
