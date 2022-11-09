package dsw.gerumap.app.gui.swing.tabbedPane;

import com.sun.tools.javac.Main;
import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.tabbedPane.view.Tab;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.Actions;
import dsw.gerumap.app.mapRepository.MapRepositoryImplementation;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.implementation.Project;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.HashMap;
@Getter
@Setter
public class TabbedPaneImplementation implements TabbedPane, Subscriber {
    private HashMap<MapNode, Tab> container;
    private Label lb;
    public TabbedPaneImplementation() {
        ((MapRepositoryImplementation) AppCore.getInstance().getMapRepository()).addSubscriber(this);
        container = new HashMap<>();
    }

    @Override
    public void addToPanel(MapNode mp) {

        MainFrame.getIntance().getDesktop().removeAll();
        lb = new Label(mp.getName() + " " + ((Project)mp).getAutor());
        MainFrame.getIntance().getDesktop().add(lb);// currently not positioned well

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
    public void setAuthor(MapNode mp) {
        lb.setText(mp.getName() + " " + ((Project)mp).getAutor());

    }

    @Override
    public void update(Object obj, Enum e) {
        if (e.equals(Actions.SETAUTHOR)){
            setAuthor((MapNode) obj);
        }

        if(e.equals(Actions.RENAME)){
            MapNodeComposite mapNode = (MapNodeComposite) ((MapNodeComposite)obj).getParent();
            MainFrame.getIntance().getDesktop().removeAll();
            addToPanel(mapNode);
        }
    }
}
