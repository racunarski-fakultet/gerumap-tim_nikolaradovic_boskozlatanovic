package dsw.gerumap.app.state;

import dsw.gerumap.app.state.states.AddLinijaState;
import dsw.gerumap.app.state.states.AddPojamState;
import dsw.gerumap.app.state.states.DeleteElementsState;
import dsw.gerumap.app.state.states.SelectElementsState;

public class StateManager {

    private AddLinijaState addLinijaState;
    private AddPojamState addPojamState;
    private DeleteElementsState deleteElementsState;
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

    public State getCurrentState() {
        return currentState;
    }
}
