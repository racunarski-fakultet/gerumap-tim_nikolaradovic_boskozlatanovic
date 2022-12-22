package dsw.gerumap.app.gui.swing.tabbedPane;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painter.PojamPainter;
import dsw.gerumap.app.gui.swing.view.painter.VezaPainter;
import dsw.gerumap.app.mapRepository.Actions;
import dsw.gerumap.app.mapRepository.MapRepositoryImplementation;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;
import dsw.gerumap.app.mapRepository.implementation.subElements.VezaElement;
import dsw.gerumap.app.serializable.GSonSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
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


        MainFrame.getIntance().getProjectView().removeAll();
        lb = new JLabel("ProjectName: " + mp.getName() + " " + "\n" + "Author: " + ((Project)mp).getAutor());
        MainFrame.getIntance().getProjectView().add(lb);
        lb.setBounds(0, 0, 15, 15);

        if (container.isEmpty() && ((Project)mp).getChildren().size() > 0){
            TabItemModel tab = new TabItemModel(((Project) mp).getChildren().get(0));
            MapNode m = ((Project) mp).getChildren().get(0);
            this.addTab(m.getName(), tab);
            container.put(m, tab);
            addTabsByParent(mp);
            MainFrame.getIntance().getProjectView().add(this, BoxLayout.class);
            MainFrame.getIntance().getProjectView().updateUI();

            return;
        }

        for(MapNode mapNode: ((Project) mp).getChildren()){
                if(!container.containsKey(mapNode)){
                    TabItemModel tab = new TabItemModel(mapNode);
                    this.addTab(mapNode.getName(), tab);
                    container.put(mapNode, tab);
                }
            }
        addTabsByParent(mp);
        MainFrame.getIntance().getProjectView().add(this, BoxLayout.class);
        MainFrame.getIntance().getProjectView().updateUI();

    }

    private void addTabsByParent(MapNode mp){

        this.removeAll();

        for (MapNode node: container.keySet()){

            if(node.getParent().equals(mp)){
                this.addTab(node.getName(), container.get(node));
            }

        }

    }
    private void addToTabItem(MapNode mapNode) {
        for (MapNode mp: container.keySet()){
            if (mp.equals(mapNode.getParent())){

                if (mapNode instanceof VezaElement){

                    container.get(mp).getPainters().add(new VezaPainter((Element) mapNode));
                    break;
                }
                else {
                    container.get(mp).getPainters().add(new PojamPainter((Element) mapNode));
                }
            }
        }
    }
    @Override
    public void setAuthor(MapNode mp) {
        lb.setText("ProjectName: " + mp.getName() + " " + "Author: " + ((Project)mp).getAutor());

    }

    @Override
    public void deleteNode(MapNode mp) {

        if (mp instanceof MindMap){
            this.remove(container.get(mp));
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

            this.remove(container.get(mapNode));
            container.remove(mapNode);
        }

        MainFrame.getIntance().getProjectView().updateUI();


    }

    private boolean currentSelectedPane(MapNode mapNode){
        Component cp = this.getComponentAt(this.getSelectedIndex());
        for(MapNode mp:  ((MapNodeComposite)mapNode).getChildren()){
            TabItemModel tb = container.get(mp);
            if(tb.equals(cp)){
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
            else{
                if(GSonSerializer.getElements().size() > 0){
                    addToTabItem((MapNode) obj);
                }
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
