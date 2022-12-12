package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.state.states.*;

public class StateManager {

    private AddLinijaState addLinijaState;
    private AddPojamState addPojamState;
    private DeleteElementsState deleteElementsState;

    private SelectionElementsState selectionElementsState;
    private MoveState moveState;
    private State currentState;




    public StateManager() {
        initialise();
    }

    private void initialise(){
        addLinijaState = new AddLinijaState();
        addPojamState = new AddPojamState();
        moveState = new MoveState();
        deleteElementsState = new DeleteElementsState();
        selectionElementsState = new SelectionElementsState();
        MoveState moveState = new MoveState();
        currentState = selectionElementsState;
    }

    public void setAddLinijaState() {
        currentState = addLinijaState;
    }

    public void setAddPojamState() {
        currentState= addPojamState;
    }

    public void setDeleteElementsState() {
        currentState = deleteElementsState;
    }

    public void setSelectElementsState() {
        currentState = selectionElementsState;
    }

    public void setMoveState() {
        currentState = moveState ;
    }

    public State getCurrentState() {
        return currentState;
    }
}
