package dsw.gerumap.app.gui.swing.view;



import javax.swing.*;
import java.awt.event.KeyEvent;



public class MyMenuBar extends JMenuBar{

    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getIntance().getActionManager().getInfoAction());
        fileMenu.add(MainFrame.getIntance().getActionManager().getNewProjectAction());

        this.add(fileMenu);
    }




}
