package dsw.gerumap.app.core.observer;

public interface Publisher {

    void addSubscriber(Object obj);
    void removeSubscriber(Object obj);
    void notifySubscribers(Object obj,Enum e);
}
