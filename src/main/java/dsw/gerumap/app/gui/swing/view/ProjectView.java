package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.state.StateManager;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
@Getter
@Setter
public class ProjectView extends JPanel {

    private StateManager stateManager;
    public ProjectView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        stateManager = new StateManager();
    }

    public void switchToSelectState(){
        stateManager.setSelectElementsState();
    }
    public void switchToDeleteState(){
        stateManager.setDeleteElementsState();
    }
    public void switchToAddPojamState(){
        stateManager.setAddPojamState();
    }
    public void switchToAddLinijaState(){
        stateManager.setAddLinijaState();
    }
    public void switchToGlavniPojamState(){
        stateManager.setGlavniPojamState();
    }
    public void switchToZoomInState(){
        stateManager.setZoomInState();
    }
    public void switchToZoomOutState(){
        stateManager.setZoomOutState();
    }

    public void switchToMoveState(){
        stateManager.setMoveState();
    }

    public void switchToMovePanelState(){
        stateManager.setDragPanelState();
    }
    public void switchToRepositionState(){
        stateManager.setRepositionState();
    }
}
