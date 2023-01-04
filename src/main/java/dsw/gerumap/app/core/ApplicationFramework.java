package dsw.gerumap.app.core;
import javax.swing.*;
public abstract class ApplicationFramework {
    protected Gui gui;

    protected MapRepository mapRepository;

    private Serializer serializer;
    protected ErrorLogger errorLogger;

    protected MessageGenerator messageGenerator;
    public abstract void start();

    public void initialize(Gui gui,MapRepository mapRepository,ErrorLogger errorLogger,MessageGenerator messageGenerator, Serializer serializer){
        this.gui = gui;
        this.mapRepository = mapRepository;
        this.errorLogger = errorLogger;
        this.messageGenerator = messageGenerator;
        this.serializer = serializer;
    }
}
