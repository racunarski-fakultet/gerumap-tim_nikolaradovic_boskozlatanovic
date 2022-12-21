package dsw.gerumap.app.gui.swing.loader;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.serializable.GSonSerializer;

import java.util.List;

public class ProjectLoader {

    public static void loadProject(MapNode node){

        MapNode returnedNode = null;
        ((MapNodeComposite)node).getChildren().clear();
        AppCore.getInstance().getMapRepository().addChild(AppCore.getInstance().getMapRepository().getProjectExplorer(),node);

        for (MapNode mp : GSonSerializer.getElements().keySet()){
            List<Element> children = GSonSerializer.getElements().get(mp);
            ((MapNodeComposite)mp).getChildren().clear();

            AppCore.getInstance().getMapRepository().addChild(node,mp);

            for (Element el: children){
                AppCore.getInstance().getMapRepository().addChild(mp,el);
            }

        }


    }
}
