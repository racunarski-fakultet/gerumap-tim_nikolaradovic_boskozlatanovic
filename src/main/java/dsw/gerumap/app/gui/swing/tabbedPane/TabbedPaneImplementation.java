package dsw.gerumap.app.gui.swing.tabbedPane;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.tabbedPane.model.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.Actions;
import dsw.gerumap.app.mapRepository.MapRepositoryImplementation;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        lb = new JLabel("ProjectName: " + mp.getName() + " " + "\n" + "Author: " + ((Project)mp).getAutor());
        MainFrame.getIntance().getDesktop().add(lb);
        lb.setBounds(0, 0, 15, 15);
        //lb.setVerticalAlignment(JLabel.TOP);


        if(container.isEmpty() || !this.containsKey((MapNodeComposite) mp)){
            TabItemModel tab;
            this.removeAll();

            for(MapNode i: ((MapNodeComposite)mp).getChildren()){
                tab = new TabItemModel(i);


                this.addTab(tab.getMapNode().getName(), tab.getPanel());
                container.put(i, tab);
            }

        }
        else{
            this.removeAll();

            for(MapNode i: ((MapNodeComposite)mp).getChildren()){
                this.addTab(i.getName(), container.get(i).getPanel());
            }
        }
        MainFrame.getIntance().getDesktop().add(this, BoxLayout.class);
        MainFrame.getIntance().getDesktop().updateUI();

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
        lb.setText("ProjectName: " + mp.getName() + " " + "Author: " + ((Project)mp).getAutor());

    }

    @Override
    public void deleteNode(MapNode mp) {

        if (mp instanceof MindMap){
            this.remove(container.get(mp).getPanel());
            container.remove(mp);
            return;
        }

        if (this.getSelectedIndex() == -1 && container.isEmpty()){

            if(lb != null){
                MainFrame.getIntance().getDesktop().remove(lb);
                MainFrame.getIntance().getDesktop().updateUI();
            }

            return;
        }
        if (currentSelectedPane(mp)){
            MainFrame.getIntance().getDesktop().remove(lb);

        }
        for (MapNode mapNode: ((MapNodeComposite)mp).getChildren()){

            this.remove(container.get(mapNode).getPanel());
            container.remove(mapNode);
        }

        MainFrame.getIntance().getDesktop().updateUI();


    }

    private boolean currentSelectedPane(MapNode mapNode){// radi samo ako su tabovi napravljeni odnosno ako postoje u slucaju da ne postoje vraca error ali zast ranije nije vracalo tro
        Component cp = this.getComponentAt(this.getSelectedIndex());
        for(MapNode mp:  ((MapNodeComposite)mapNode).getChildren()){
            TabItemModel tb = container.get(mp);
            if(tb.getPanel().equals(cp)){
                return true;
            }
        }
        return false;
    }
    @Override
    public void update(Object obj, Enum e) {
        if(e.equals(Actions.ADD)){
            if(obj instanceof MindMap) {
                addToPanel(((MapNode) obj).getParent());
            }
        }
        if (e.equals(Actions.SETAUTHOR)&& lb != null) {
            if (!(MainFrame.getIntance().getMapTree().getSelectedNode().getMapNode() instanceof ProjectExplorer)) {
                if (currentSelectedPane((MapNode) obj))
                    setAuthor((MapNode) obj);
            }
        }
        if(e.equals(Actions.RENAME) ){

            if (obj instanceof Project)
                addToPanel((MapNode) obj);
            else if(obj instanceof MindMap)
                addToPanel(((MapNode) obj).getParent());
        }
        if(e.equals(Actions.DELETE)){

            deleteNode((MapNode) obj);

        }
    }
}
