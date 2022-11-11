package dsw.gerumap.app.errorHandling.loggerImplementations;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.ErrorLogger;
import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.errorHandling.messageImplementation.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements ErrorLogger, Subscriber {

    File file;

    public FileLogger() {
        createFile();
    }

    private void createFile(){

        file = new File("log.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile(Message message){
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            FileWriter fw = new FileWriter("log.txt");
            fw.write("ERROR: "+ dtf.format(now) + " " + message.getMessage()+ " naziv greske: " +message.getEventType().toString().replace('_',' ') + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void log(Message message) {
        writeToFile(message);
    }

    @Override
    public void addToSubscriberList() {
        ((Publisher) AppCore.getInstance().getMessageGenerator()).addSubscriber(this);
    }

    @Override
    public void update(Object obj, Enum e) {
        log((Message) obj);
    }
}
