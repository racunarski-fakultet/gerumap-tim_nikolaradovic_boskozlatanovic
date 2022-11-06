package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

public class MapTreeImplementation implements MapTree{
    private MapTreeView treeView;

    @Override
    public void setAuthor(MapTreeItem node,String name) {

        if (node.getMapNode() instanceof Project){
            Project p = (Project) node.getMapNode();
            p.setAutor(name);
            System.out.println(name);
        }
    }

    private DefaultTreeModel treeModel;


    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {

        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        return treeView;


    }

    @Override
    public void addChild(MapTreeItem parent) {
        if (!(parent.getMapNode() instanceof MapNodeComposite) || parent.getMapNode() instanceof MindMap)
            return;

        MapNode child = createChild(parent.getMapNode());
        parent.add(new MapTreeItem(child));
        ((MapNodeComposite) parent.getMapNode()).addChildren(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild(MapTreeItem child) {

        if (!(child.getMapNode() instanceof MapNodeComposite))
            return;
        MapNodeComposite parent = (MapNodeComposite) child.getMapNode().getParent();
        parent.removeChildren((MapNode) child.getMapNode());

        treeModel.removeNodeFromParent(child);

    }



    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }

    private MapNode createChild(MapNode parent){

        if (parent instanceof Project){
            return new MindMap(parent, "MindMap" + new Random().nextInt(100));
        }
            return new Project(parent, "Project" + new Random().nextInt(100));

    }

}
