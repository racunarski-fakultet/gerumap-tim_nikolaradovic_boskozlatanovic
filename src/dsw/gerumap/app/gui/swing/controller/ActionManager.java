package dsw.gerumap.app.gui.swing.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

    private InfoAction infoAction;
    private NewProjectAction newProjectAction;

    private void initialliseActions(){
        infoAction = new InfoAction();
        newProjectAction = new NewProjectAction();
    }
}
