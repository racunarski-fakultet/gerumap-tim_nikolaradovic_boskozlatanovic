package dsw.gerumap.app.errorHandling.loggerImplementations;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.ErrorLogger;
import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.errorHandling.messageImplementation.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ConsoleLogger implements ErrorLogger, Subscriber {


    @Override
    public void log(Message message) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("ERROR: "+ dtf.format(now) + " " + message.getMessage()+ " naziv greske: " +message.getEventType().toString().replace('_',' '));
    }

    @Override
    public void addToSubscriberList() {
        ((Publisher)AppCore.getInstance().getMessageGenerator()).addSubscriber(this);
    }

    @Override
    public void update(Object obj, Enum e) {
        log((Message) obj);
    }
}
