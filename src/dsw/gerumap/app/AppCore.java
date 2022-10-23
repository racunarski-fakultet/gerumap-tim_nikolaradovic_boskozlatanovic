package dsw.gerumap.app;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.gui.swing.SwingGui;

public class AppCore extends ApplicationFramework {

    private static AppCore instance;
    @Override
    public void start() {

    }

    private AppCore(){

    }
    public static AppCore getInstance() {
        if (instance == null){
            return instance = new AppCore();
        }
        return instance;
    }

    public void run(){
        this.gui.start();
    }

    public static void main(String[] args) {
        Gui gui = new SwingGui();
        ApplicationFramework appCore = AppCore.getInstance();
        appCore.initialize(gui);
        appCore.start();
    }


}
