package dsw.gerumap.app.gui.swing.tabbedPane;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.tabbedPane.view.Tab;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.Actions;
import dsw.gerumap.app.mapRepository.MapRepositoryImplementation;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;

import java.util.HashMap;

public class TabbedPaneImplementation implements TabbedPane, Subscriber {
    private HashMap<MapNode, Tab> container;

    public TabbedPaneImplementation() {
        ((MapRepositoryImplementation) AppCore.getInstance().getMapRepository()).addSubscriber(this);
        container = new HashMap<>();
    }

    @Override
    public void addToPanel(MapNode mp) {
        MainFrame.getIntance().getDesktop().removeAll();
        if(container.isEmpty() || !container.containsKey(mp)){
            Tab tab;

            for(MapNode i: ((MapNodeComposite)mp).getChildren()){
                tab = new Tab(i);
                MainFrame.getIntance().getDesktop().add(i.getName(),tab);
                MainFrame.getIntance().getDesktop().updateUI();
                container.put(i, tab);
            }
        }else{
            for(MapNode i: container.keySet()){
                MainFrame.getIntance().getDesktop().add(i.getName(), container.get(i));
                MainFrame.getIntance().getDesktop().updateUI();

            }
        }

    }

    @Override
    public void setAuthor(String author) {

    }

    @Override
    public void update(Object obj, Enum e) {
        MapNodeComposite mapNode = (MapNodeComposite) ((MapNodeComposite)obj).getParent();
        MainFrame.getIntance().getDesktop().removeAll();
        if(e.equals(Actions.RENAME)){
            addToPanel(mapNode);
        }
    }
}
