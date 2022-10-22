package dsw.gerumap.app;

import dsw.gerumap.app.core.ApplicationFramework;

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


}
