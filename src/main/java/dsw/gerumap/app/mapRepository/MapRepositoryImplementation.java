package dsw.gerumap.app.mapRepository;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.errorHandling.EventType;
import dsw.gerumap.app.mapRepository.factory.utils.NewNodeAction;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.factory.utils.SubElements;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Getter
@Setter

public class MapRepositoryImplementation implements MapRepository, Publisher {

    private MapNode projectExplorer;
    List<Subscriber> subscribers;

    public MapRepositoryImplementation() {
        projectExplorer = NewNodeAction.getInstance().returnNodeFactory(null).getNode(null, "MyProjectExplorer");
        subscribers = new ArrayList<>();
    }

    @Override
    public MapNode getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public MapNode addChild(MapNode parent,String name, String author) {

        if (!(parent instanceof MapNodeComposite) || parent instanceof Element)
            return null;

        MapNode newNode = NewNodeAction.getInstance().returnNodeFactory(parent).getNode(parent,name);

       this.setAuthoer(newNode,author);

        ((MapNodeComposite) parent).addChildren(newNode);

       this.notifySubscribers(newNode,Actions.ADD);

       return newNode;
    }

    @Override
    public MapNode addChild(MapNode parent, String name, Enum e) {

        MapNode newNode = NewNodeAction.getInstance().returnNodeFactory(parent,(SubElements) e).getNode(parent,name);
        ((MapNodeComposite) parent).addChildren(newNode);
        this.notifySubscribers(newNode,Actions.ADD);
        return newNode;
    }

    @Override
    public void removeChild(MapNode child) {
        if (!(child instanceof MapNodeComposite) || child instanceof ProjectExplorer){
            AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NODE_CANNOT_BE_DELETED);
            return;
        }

        MapNodeComposite parent = (MapNodeComposite) child.getParent();
        parent.removeChildren(child);
        this.notifySubscribers(child, Actions.DELETE);
    }

    @Override
    public void setAuthoer(MapNode mapNode,String name) {


        if (!(mapNode instanceof Project)) {
            return;
        }


        ((Project) mapNode).setAutor(name);

        notifySubscribers(mapNode,Actions.SETAUTHOR);
    }

    @Override
    public void rename(MapNode mapNode, String name) {

        if(nodeExists(mapNode,name)){
            AppCore.getInstance().getMessageGenerator().generateMessage(EventType.NAME_EXISTS);
            return;
        }
        mapNode.setName(name);
        this.notifySubscribers(mapNode, Actions.RENAME);
    }

    private boolean nodeExists(MapNode node,String newName){

        MapNode parent = node.getParent();

        for (MapNode mp: ((MapNodeComposite)parent).getChildren()){
            if(mp.getName().equals(newName)) return true;
        }
        return false;
    }
    @Override
    public void addSubscriber(Object obj) {
        if(obj != null && !subscribers.contains(obj) && obj instanceof Subscriber){
            subscribers.add((Subscriber) obj);
        }
    }

    @Override
    public void removeSubscriber(Object obj) {
        if(obj != null && subscribers.contains(obj)){
            subscribers.remove(obj);
        }
    }

    @Override
    public void notifySubscribers(Object obj, Enum e) {

        for (int i = 0; i < subscribers.size(); i++){
            subscribers.get(i).update(obj,e);
        }
    }




}
