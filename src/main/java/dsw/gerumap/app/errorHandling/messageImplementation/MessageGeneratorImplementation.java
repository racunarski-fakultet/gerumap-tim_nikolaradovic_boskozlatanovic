package dsw.gerumap.app.errorHandling.messageImplementation;

import dsw.gerumap.app.core.MessageGenerator;
import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.errorHandling.EventType;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorImplementation implements MessageGenerator, Publisher {

    private List<Subscriber> subscribers;
    private Message message;

    public MessageGeneratorImplementation() {
        subscribers = new ArrayList<>();
    }

    @Override
    public void generateMessage(Enum e) {

        if(e.equals(EventType.NAME_EXISTS)){
            message = new Message("Nije moguce izabrati isto ime", (EventType) e);
            notifySubscribers(message,e);
        }
        if(e.equals(EventType.NO_AUTHOR)){
            message = new Message("Molim vas dodajte ime autora",(EventType) e);
            notifySubscribers(message,e);
        }
        if(e.equals(EventType.ONLY_FOR_PROJECT)){
            message = new Message("Nije moguce izbrisati project explorer",(EventType) e);
            notifySubscribers(message,e);
        }
        if(e.equals(EventType.DELETE_PROJECT_EXPLORER)){
            message = new Message("Nije moguce izbrisati project explorer",(EventType) e);
            notifySubscribers(message,e);
        }

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
