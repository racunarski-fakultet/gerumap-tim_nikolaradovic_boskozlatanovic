package dsw.gerumap.app.errorHandling.loggerImplementations;

import dsw.gerumap.app.core.ErrorLogger;
import dsw.gerumap.app.core.observer.Subscriber;

public class ConsoleLogger implements ErrorLogger, Subscriber {

    @Override
    public void log() {

    }

    @Override
    public void update(Object obj, Enum e) {

    }
}
