package dsw.gerumap.app.gui.swing.tree;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.errorHandling.EventType;
import dsw.gerumap.app.gui.swing.elements.PojamElement;
import dsw.gerumap.app.gui.swing.elements.VezaElement;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.mapRepository.Actions;
import dsw.gerumap.app.mapRepository.MapRepositoryImplementation;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.List;

public class MapTreeImplementation implements MapTree, Subscriber {
    private MapTreeView treeView;
    private List<MapTreeItem> items = new ArrayList<>();

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

    @Override
    public void renameMapTreeItem() {
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    private DefaultTreeModel treeModel;


    @Override
    public MapTreeView generateTree(MapNode projectExplorer) {

        MapTreeItem root = new MapTreeItem(projectExplorer);
        items.add(root);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        ((MapRepositoryImplementation)AppCore.getInstance().getMapRepository()).addSubscriber(this);
        return treeView;


    }

    @Override
    public void addChild(MapTreeItem child) {
        items.add(child);
        MapTreeItem parent = getParentNode(child);
        parent.add(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild(MapNode node) {
        MapTreeItem child = getPassedNode(node);
        treeModel.removeNodeFromParent(child);
        items.remove(child);
    }



    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }

    private MapTreeItem getParentNode(MapTreeItem child){

        for (MapTreeItem mpi: items){

            String inst1="";
            String inst2=" ";

            if(mpi.getMapNode() instanceof ProjectExplorer) inst1 ="pp";
            if(mpi.getMapNode() instanceof Project) inst1 = "p";
            if(mpi.getMapNode() instanceof MindMap) inst1 = "m";
            if(mpi.getMapNode() instanceof VezaElement) inst1 = "v";
            if(mpi.getMapNode() instanceof PojamElement) inst1 = "pj";

            if(child.getMapNode().getParent() instanceof ProjectExplorer) inst2 = "pp";
            if(child.getMapNode().getParent() instanceof Project) inst2 = "p";
            if(child.getMapNode().getParent() instanceof MindMap) inst2 = "m";
            if(child.getMapNode().getParent() instanceof VezaElement) inst2 = "v";
            if(child.getMapNode().getParent() instanceof PojamElement) inst2 = "pj";

            if(mpi.getMapNode().equals(child.getMapNode().getParent()) && inst1.equals(inst2)){
                return mpi;
            }

        }
        return null;
    }

    private MapTreeItem getPassedNode(MapNode node){
        for (MapTreeItem mpi: items){
            String inst1="";
            String inst2=" ";


            if(mpi.getMapNode() instanceof ProjectExplorer) inst1 ="pp";
            if(mpi.getMapNode() instanceof Project) inst1 = "p";
            if(mpi.getMapNode() instanceof MindMap) inst1 = "m";
            if(mpi.getMapNode() instanceof VezaElement) inst1 = "v";
            if(mpi.getMapNode() instanceof PojamElement) inst1 = "pj";

            if(node instanceof ProjectExplorer) inst1 ="pp";
            if(node instanceof Project) inst2 = "p";
            if(node instanceof MindMap) inst2 = "m";
            if(node instanceof VezaElement) inst2 = "v";
            if(node instanceof PojamElement) inst2 = "pj";


            if(mpi.getMapNode().equals(node)&& inst1.equals(inst2)){
                return mpi;
            }
        }
        return null;
    }
    @Override
    public void update(Object obj, Enum e) {
        if (e.equals(Actions.ADD)){
            addChild(new MapTreeItem((MapNode) obj));
        }
        if(e.equals(Actions.DELETE)){
            removeChild((MapNode) obj);
        }
        if(e.equals(Actions.RENAME)){
            renameMapTreeItem();
        }

    }
}
