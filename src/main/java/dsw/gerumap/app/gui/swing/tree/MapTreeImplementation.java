package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.errorHandling.EventType;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.Actions;
import dsw.gerumap.app.mapRepository.MapRepositoryImplementation;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class MapTreeImplementation implements MapTree, Subscriber {
    private MapTreeView treeView;

    @Override
    public void setAuthor(MapTreeItem node,String name) {

        if (node.getMapNode() instanceof Project){
            Project p = (Project) node.getMapNode();
            p.setAutor(name);
            System.out.println(name);
        }
        else {
            AppCore.getInstance().getMessageGenerator().generateMessage(EventType.ONLY_FOR_PROJECT);
            return;
        }
    }

    private DefaultTreeModel treeModel;


    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {

        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        ((MapRepositoryImplementation)AppCore.getInstance().getMapRepository()).addSubscriber(this);
        return treeView;


    }

    @Override
    public void addChild(MapTreeItem child) {
        MapTreeItem parent = MainFrame.getIntance().getMapTree().getSelectedNode();
        parent.add(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild() {
        MapTreeItem child = MainFrame.getIntance().getMapTree().getSelectedNode();
        treeModel.removeNodeFromParent(child);

    }



    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }


    @Override
    public void update(Object obj, Enum e) {
        if (e.equals(Actions.ADD)){
            addChild(new MapTreeItem((MapNode) obj));
        }
        if(e.equals(Actions.DELETE)){
            removeChild();
        }
    }
}
