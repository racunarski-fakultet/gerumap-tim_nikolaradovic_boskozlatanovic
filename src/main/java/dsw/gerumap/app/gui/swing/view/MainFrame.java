package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.Command;
import dsw.gerumap.app.gui.swing.commands.CommandManager;
import dsw.gerumap.app.gui.swing.controller.ActionManager;
import dsw.gerumap.app.gui.swing.tabbedPane.TabbedPaneImplementation;
import dsw.gerumap.app.gui.swing.tabbedPane.view.TabItemModel;
import dsw.gerumap.app.gui.swing.tree.MapTree;
import dsw.gerumap.app.gui.swing.tree.MapTreeImplementation;
import dsw.gerumap.app.gui.swing.view.painterResize.ResizeListener;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame {

    private static MainFrame instance;

    private ActionManager actionManager;

    private JMenuBar menu;

    private JToolBar toolBar;

    private MapTree mapTree;

    private TabItemModel tab;

    private ProjectViewToolBar projectViewToolBar;

    private CommandManager commandManager;

    private ProjectView projectView;
    private TabbedPaneImplementation tabbedPane;
    private MainFrame(){

    }

    private void initialise(){
        actionManager = new ActionManager();
        mapTree = new MapTreeImplementation();
        tabbedPane = new TabbedPaneImplementation();
        commandManager = new CommandManager();
        this.addComponentListener(new ResizeListener());
        initialiseGUI();

    }

    public static MainFrame getIntance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }


    private void initialiseGUI(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screensize = kit.getScreenSize();

        int screenWidth = screensize.width;
        int screenHeight = screensize.height;

        setSize(screenWidth / 2, screenHeight / 2);
        System.out.println("scr width = " + screenWidth / 2);
        System.out.println("scr height = " + screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gerumap app");

        menu = new MyMenuBar();
        setJMenuBar(menu);

        toolBar = new ToolBar();
        add(toolBar, BorderLayout.NORTH);

        JTree projectExplorer = mapTree.generateTree(AppCore.getInstance().getMapRepository().getProjectExplorer());

        projectView = new ProjectView();
        projectViewToolBar = new ProjectViewToolBar();

        JScrollPane scroll = new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200, 150));
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, projectView);
        getContentPane().add(split, BorderLayout.CENTER);
        getContentPane().add(projectViewToolBar,BorderLayout.EAST);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);





    }


}
