package dsw.gerumap.app.gui.swing.tabbedPane.view;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Project;

import javax.swing.*;
import java.awt.*;

public class Tab extends JTabbedPane {
    private MapNode mapNode;

    public Tab(MapNode mapNode) {
        this.mapNode = mapNode;
        this.addTab(mapNode.getName(), null);
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

    }
}
