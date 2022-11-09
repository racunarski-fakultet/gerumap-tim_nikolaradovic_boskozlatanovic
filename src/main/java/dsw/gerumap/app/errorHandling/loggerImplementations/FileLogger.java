package dsw.gerumap.app.errorHandling.loggerImplementations;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.ErrorLogger;
import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;

import java.io.File;

public class FileLogger implements ErrorLogger, Subscriber {

    File file;


    @Override
    public void log() {

    }

    @Override
    public void addToSubscriberList() {
        ((Publisher) AppCore.getInstance().getMessageGenerator()).addSubscriber(this);
    }

    @Override
    public void update(Object obj, Enum e) {

    }
}
