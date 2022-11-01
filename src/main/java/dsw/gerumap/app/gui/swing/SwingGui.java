package dsw.gerumap.app.gui.swing;

import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.gui.swing.view.MainFrame;

public class SwingGui implements Gui {
    @Override
    public void start() {
        MainFrame.getIntance().setVisible(true);
    }
}
