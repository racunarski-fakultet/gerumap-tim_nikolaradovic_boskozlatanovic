package dsw.gerumap.app.state;

import dsw.gerumap.app.state.states.*;

public class StateManager {

    private AddLinijaState addLinijaState;
    private AddPojamState addPojamState;
    private DeleteElementsState deleteElementsState;

    private MoveState moveState;
    private SelectElementsState selectElementsState;
    private State currentState;




    public StateManager() {
        initialise();
    }

    private void initialise(){
        addLinijaState = new AddLinijaState();
        addPojamState = new AddPojamState();
        selectElementsState = new SelectElementsState();
        deleteElementsState = new DeleteElementsState();
        moveState = new MoveState();
        currentState = selectElementsState;
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
        currentState = selectElementsState;
    }

    public void setMoveState() {
        currentState = moveState;
    }

    public State getCurrentState() {
        return currentState;
    }
}
