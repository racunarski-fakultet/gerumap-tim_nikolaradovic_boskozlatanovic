package dsw.gerumap.app.mapRepository.factory.utils;

import dsw.gerumap.app.mapRepository.factory.NodeFactory;
import dsw.gerumap.app.mapRepository.factory.factoryInstance.ElementFactory;
import dsw.gerumap.app.mapRepository.factory.factoryInstance.MindMapFactory;
import dsw.gerumap.app.mapRepository.factory.factoryInstance.ProjectExplorerFactory;
import dsw.gerumap.app.mapRepository.factory.factoryInstance.ProjectFactory;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;
import lombok.Setter;


@Setter

public class NewNodeAction {
    private static NewNodeAction instance;
    private NodeFactory elementFactory;
    private NodeFactory mindMapFactory;
    private NodeFactory projectFactory;
    private NodeFactory projectExplorerFactory;


    private NewNodeAction(){
        elementFactory = new ElementFactory();
        mindMapFactory = new MindMapFactory();
        projectFactory = new ProjectFactory();
        projectExplorerFactory = new ProjectExplorerFactory();
    }

    public static NewNodeAction getInstance() {
        if(instance == null)
            return new NewNodeAction();
        return instance;
    }

    public NodeFactory returnNodeFactory(MapNode mapNode){
        if(mapNode instanceof ProjectExplorer){
            return projectFactory;
        }
        else if(mapNode instanceof Project){
            return mindMapFactory;
        }
        else if(mapNode == null)
            return projectExplorerFactory;
        return elementFactory;
    }



}
