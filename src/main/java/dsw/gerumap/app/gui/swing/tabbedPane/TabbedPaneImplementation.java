package dsw.gerumap.app.gui.swing.tabbedPane;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.tabbedPane.model.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.Actions;
import dsw.gerumap.app.mapRepository.MapRepositoryImplementation;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import dsw.gerumap.app.mapRepository.implementation.Project;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
@Getter
@Setter
public class TabbedPaneImplementation extends JTabbedPane implements TabbedPane, Subscriber {
    private HashMap<MapNode, TabItemModel> container;
    private JLabel lb;
    public TabbedPaneImplementation() {
        ((MapRepositoryImplementation) AppCore.getInstance().getMapRepository()).addSubscriber(this);
        container = new HashMap<>();
    }

    @Override
    public void addToPanel(MapNode mp) {

        MainFrame.getIntance().getDesktop().removeAll();
        lb = new JLabel(mp.getName() + " " + ((Project)mp).getAutor());
        MainFrame.getIntance().getDesktop().add(lb);

        lb.setVerticalAlignment(JLabel.TOP);

        // currently not positioned well

        if(container.isEmpty() || !this.containsKey((MapNodeComposite) mp)){
            TabItemModel tab;
            this.removeAll();

            for(MapNode i: ((MapNodeComposite)mp).getChildren()){
                tab = new TabItemModel(i);


                this.addTab(tab.getMapNode().getName(), tab.getPanel());
                container.put(i, tab);
            }

            MainFrame.getIntance().getDesktop().add(this, BoxLayout.class);
            MainFrame.getIntance().getDesktop().updateUI();
        }
        else{
            this.removeAll();

            for(MapNode i: ((MapNodeComposite)mp).getChildren()){
                this.addTab(i.getName(), container.get(i).getPanel());
            }
            MainFrame.getIntance().getDesktop().add(this, BoxLayout.class);
            MainFrame.getIntance().getDesktop().updateUI();
        }


    }
    private boolean containsKey(MapNodeComposite mapNode){
        for(MapNode mp: mapNode.getChildren()){
            if(!container.containsKey(mp)){
                return false;
            }
        }
        return true;
    }
    @Override
    public void setAuthor(MapNode mp) {
        lb.setText(mp.getName() + " " + ((Project)mp).getAutor());

    }

    @Override
    public void update(Object obj, Enum e) {
        if(e.equals(Actions.ADD)){
            if(obj instanceof MindMap) {
                addToPanel(((MapNode) obj).getParent());
            }
        }
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
