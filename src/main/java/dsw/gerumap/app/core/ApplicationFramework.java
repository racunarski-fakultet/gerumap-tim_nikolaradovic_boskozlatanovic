package dsw.gerumap.app.core;
import javax.swing.*;
public abstract class ApplicationFramework {
    protected Gui gui;

    protected MapRepository mapRepository;
    public abstract void start();

    public void initialize(Gui gui,MapRepository mapRepository){
        this.gui = gui;
        this.mapRepository = mapRepository;
    }
}
