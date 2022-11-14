package dsw.gerumap.app.gui.swing.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class  ActionManager {

    private InfoAction infoAction;
    private NewProjectAction newProjectAction;

    private DeleteAction deleteAction;

    private AddAuthorAction addAuthorAction;

    private RenameAction renameAction;
    private void initialliseActions(){
        infoAction = new InfoAction();
        newProjectAction = new NewProjectAction();
        deleteAction = new DeleteAction();
        addAuthorAction = new AddAuthorAction();
        renameAction = new RenameAction();
    }

    public ActionManager(){
        initialliseActions();
    }


}
