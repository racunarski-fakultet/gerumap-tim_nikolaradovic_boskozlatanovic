package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.controller.ActionManager;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class MainFrame extends JFrame {

    private static MainFrame instance;

    private ActionManager actionManager;

    private JMenuBar menuBar;

    private JToolBar toolBar;

    private MainFrame(){

    }

    public static MainFrame getIntance() {
        if (instance == null)
            return instance = new MainFrame();
        return instance;
    }

    private void initialise(){

    }
    private void initialiseGUI(){

    }
}
