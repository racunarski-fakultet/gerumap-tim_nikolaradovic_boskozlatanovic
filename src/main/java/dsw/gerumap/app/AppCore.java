package dsw.gerumap.app;

import dsw.gerumap.app.core.*;
import dsw.gerumap.app.errorHandling.loggerImplementations.ConsoleLogger;
import dsw.gerumap.app.errorHandling.loggerImplementations.FileLogger;
import dsw.gerumap.app.errorHandling.messageImplementation.MessageGeneratorImplementation;
import dsw.gerumap.app.gui.swing.SwingGui;
import dsw.gerumap.app.mapRepository.MapRepositoryImplementation;
import lombok.Getter;

@Getter
public class AppCore extends ApplicationFramework {

    private static AppCore instance;
    private MapRepository mapRepository;
    private MessageGenerator messageGenerator;

    private  ErrorLogger consoleErrorLogger;


    @Override
    public void start() {
        this.gui.start();
        this.gui.addToSubscriberList();
        errorLogger.addToSubscriberList();

    }

    private AppCore(){
        mapRepository = new MapRepositoryImplementation();
        messageGenerator = new MessageGeneratorImplementation();
        consoleErrorLogger = new FileLogger();
    }
    public static AppCore getInstance() {
        if (instance == null){
            return instance = new AppCore();
        }
        return instance;
    }


    public static void main(String[] args) {

        ApplicationFramework appCore = AppCore.getInstance();
        Gui gui = new SwingGui();
        appCore.initialize(gui, getInstance().mapRepository, getInstance().consoleErrorLogger, getInstance().getMessageGenerator());
        appCore.start();
    }


}
