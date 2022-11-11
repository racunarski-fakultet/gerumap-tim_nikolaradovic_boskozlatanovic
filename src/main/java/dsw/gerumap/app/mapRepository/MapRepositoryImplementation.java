package dsw.gerumap.app.mapRepository;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.errorHandling.EventType;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter

public class MapRepositoryImplementation implements MapRepository, Publisher {

    private ProjectExplorer projectExplorer;
    List<Subscriber> subscribers;

    public MapRepositoryImplementation() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
        subscribers = new ArrayList<>();
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public void addChild(MapNode parent) {


        MapNode newNode;
        if (!(parent instanceof MapNodeComposite) || parent instanceof MindMap)
            return;

        if (parent instanceof Project){
             newNode = new MindMap(parent, "MindMap" + new Random().nextInt(100));
        }
        else{
            newNode = new Project(parent, "Project" + new Random().nextInt(100));
        }

        ((MapNodeComposite) parent).addChildren(newNode);

       this.notifySubscribers(newNode,Actions.ADD);

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
        for (Subscriber s: subscribers){
            s.update(obj,e);
        }
    }


}
