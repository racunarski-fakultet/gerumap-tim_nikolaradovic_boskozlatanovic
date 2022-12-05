package dsw.gerumap.app.mapRepository.factory.utils;

import dsw.gerumap.app.mapRepository.factory.NodeFactory;
import dsw.gerumap.app.mapRepository.factory.factoryInstance.*;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.mapRepository.implementation.ProjectExplorer;
import lombok.Setter;


@Setter

public class NewNodeAction {
    private static NewNodeAction instance;
    private static NodeFactory pojamFactory;
    private static NodeFactory vezaFactory;
    private NodeFactory mindMapFactory;
    private NodeFactory projectFactory;
    private NodeFactory projectExplorerFactory;


    private NewNodeAction(){
        pojamFactory = new PojamFactory();
        vezaFactory = new VezaFactory();
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
        return null;
    }
    public NodeFactory returnNodeFactory(MapNode mapNode,SubElements subElements){

        if(subElements.equals(SubElements.POJAM)){
            return pojamFactory;
        }
        return vezaFactory;
    }



}
