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

        MainFrame.getIntance().getProjectView().removeAll();
        lb = new JLabel("ProjectName: " + mp.getName() + " " + "\n" + "Author: " + ((Project)mp).getAutor());
        MainFrame.getIntance().getProjectView().add(lb);
        lb.setBounds(0, 0, 15, 15);



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
        MainFrame.getIntance().getProjectView().add(this, BoxLayout.class);
        MainFrame.getIntance().getProjectView().updateUI();

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
                MainFrame.getIntance().getProjectView().remove(lb);
                MainFrame.getIntance().getProjectView().updateUI();
            }

            return;
        }
        if (this.getSelectedIndex() != -1 && currentSelectedPane(mp)){
            MainFrame.getIntance().getProjectView().remove(lb);

        }
        for (MapNode mapNode: ((MapNodeComposite)mp).getChildren()){

            this.remove(container.get(mapNode).getPanel());
            container.remove(mapNode);
        }

        MainFrame.getIntance().getProjectView().updateUI();


    }

    private boolean currentSelectedPane(MapNode mapNode){
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
        if (e.equals(Actions.SETAUTHOR) && lb != null) {
            if (!(MainFrame.getIntance().getMapTree().getSelectedNode().getMapNode() instanceof ProjectExplorer)) {

                if (MainFrame.getIntance().getTabbedPane().getSelectedIndex() != -1){

                    if (currentSelectedPane((MapNode) obj)){
                        setAuthor((MapNode) obj);
                    }

                }
                else if(container.size() == 0 && lb != null && ((MapNodeComposite)MainFrame.getIntance().getMapTree().getSelectedNode().getMapNode().getParent()).getChildren().size() <= 1){
                    setAuthor((MapNode) obj);
                }

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
