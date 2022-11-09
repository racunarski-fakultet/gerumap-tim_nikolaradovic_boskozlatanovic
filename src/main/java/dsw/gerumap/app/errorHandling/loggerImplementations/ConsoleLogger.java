package dsw.gerumap.app.errorHandling.loggerImplementations;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.ErrorLogger;
import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.errorHandling.messageImplementation.Message;

public class ConsoleLogger implements ErrorLogger, Subscriber {


    @Override
    public void log() {

    }

    @Override
    public void addToSubscriberList() {
        ((Publisher)AppCore.getInstance().getMessageGenerator()).addSubscriber(this);
    }

    @Override
    public void update(Object obj, Enum e) {
        System.out.println("ERROR: " + ((Message)obj).getMessage()+ " " +((Message)obj).getEventType().toString().replace('_',' '));
    }
}
