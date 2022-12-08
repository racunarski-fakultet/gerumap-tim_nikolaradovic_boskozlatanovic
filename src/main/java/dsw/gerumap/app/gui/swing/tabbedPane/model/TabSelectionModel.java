package dsw.gerumap.app.gui.swing.tabbedPane.model;

import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.mapRepository.Actions;
import dsw.gerumap.app.mapRepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TabSelectionModel implements Publisher {

    List<Subscriber> subscribers;
    List<Element> selected;

    public TabSelectionModel() {
        subscribers = new ArrayList<>();
        selected = new ArrayList<>();
    }

    @Override
    public void addSubscriber(Object obj) {
            subscribers.add((Subscriber) obj);
    }

    @Override
    public void removeSubscriber(Object obj) {

        subscribers.remove(obj);
    }

    @Override
    public void notifySubscribers(Object obj, Enum e) {

        for (Subscriber s: subscribers){
            s.update(obj, e);
        }
    }

    public void addSelection(Element e){
        if(e == null || selected.contains(e))return;
        selected.add(e);
        notifySubscribers(e,Actions.SELECTED);
    }

    public void removeAll() {
        this.getSelected().removeAll(getSelected());
        notifySubscribers(null,Actions.SELECTED);
    }
}
