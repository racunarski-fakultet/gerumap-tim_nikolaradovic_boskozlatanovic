package dsw.gerumap.app.mapRepository.commands.implementations;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.Command;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class DeleteElementsCommand implements Command {

    private Set<Element> selektovaniElementi;
    private MapNode parent;

    public DeleteElementsCommand(MapNode parent) {
        this.parent = parent;
        selektovaniElementi = new HashSet<>();
    }

    @Override
    public void doCommand() {
        for (Element element: selektovaniElementi){
            AppCore.getInstance().getMapRepository().removeChild(element);
        }
    }

    @Override
    public void undoCommand() {
        for (Element element: selektovaniElementi){
            AppCore.getInstance().getMapRepository().addChild(parent,element);
        }
    }
}
