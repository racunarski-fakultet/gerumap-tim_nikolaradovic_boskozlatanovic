package dsw.gerumap.app.gui.swing.tabbedPane.view;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Project;

import javax.swing.*;
import java.awt.*;

public class Tab extends JTabbedPane {
    private MapNode mapNode;
    private JPanel panel;
    public Tab(MapNode mapNode) {
        super(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        this.mapNode = mapNode;

        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        this.panel = new JPanel();
        this.setBounds(50, 50, 250, 200);
        this.addTab(mapNode.getName(), panel);

    }
}
