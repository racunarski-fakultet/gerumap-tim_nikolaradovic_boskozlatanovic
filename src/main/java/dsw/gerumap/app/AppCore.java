package dsw.gerumap.app;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.gui.swing.SwingGui;
import dsw.gerumap.app.mapRepository.MapRepositoryImplementation;
import lombok.Getter;

@Getter
public class AppCore extends ApplicationFramework {

    private static AppCore instance;
    private MapRepository mapRepository = new MapRepositoryImplementation();

    @Override
    public void start() {
        this.gui.start();
    }

    private AppCore(){

    }
    public static AppCore getInstance() {
        if (instance == null){
            return instance = new AppCore();
        }
        return instance;
    }


    public static void main(String[] args) {
        Gui gui = new SwingGui();
        ApplicationFramework appCore = AppCore.getInstance();
        appCore.initialize(gui, getInstance().mapRepository);
        appCore.start();
    }


}
