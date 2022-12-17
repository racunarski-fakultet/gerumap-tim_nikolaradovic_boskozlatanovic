package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.controller.projectViewControllers.*;
import dsw.gerumap.app.gui.swing.controller.upperSide.*;
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

    private AddLinijaAction addLinijaAction;

    private AddPojamAction addPojamAction;

    private DeleteElementsAction deleteElementsAction;

    private SelectElementAction selectElementAction;

    private ZoomOutAction zoomOutAction;

    private ZoomInAction zoomInAction;

    private ChangeColorAction changeColorAction;

    private MovePanelAction movePanelAction;

    private void initialliseActions(){
        infoAction = new InfoAction();
        newProjectAction = new NewProjectAction();
        deleteAction = new DeleteAction();
        addAuthorAction = new AddAuthorAction();
        renameAction = new RenameAction();
        addLinijaAction = new AddLinijaAction();
        deleteElementsAction = new DeleteElementsAction();
        selectElementAction = new SelectElementAction();
        addPojamAction = new AddPojamAction();
        zoomOutAction = new ZoomOutAction();
        zoomInAction = new ZoomInAction();
        changeColorAction = new ChangeColorAction();
        movePanelAction = new MovePanelAction();
    }

    public ActionManager(){
        initialliseActions();
    }


}
