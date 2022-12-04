package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.state.StateManager;

import javax.swing.*;

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
}
