package dsw.gerumap.app.gui.swing.tabbedPane;

import dsw.gerumap.app.mapRepository.composite.MapNode;

public interface TabbedPane {
    void addToPanel(MapNode mp);
    void setAuthor(MapNode mp);

    void deleteNode(MapNode mp);

}
