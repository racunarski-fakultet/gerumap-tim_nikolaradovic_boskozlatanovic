package dsw.gerumap.app.core;

import dsw.gerumap.app.errorHandling.messageImplementation.Message;

public interface ErrorLogger {
    void log(Message message);
    void addToSubscriberList();     
}
