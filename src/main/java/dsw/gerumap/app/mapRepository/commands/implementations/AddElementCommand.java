package dsw.gerumap.app.mapRepository.commands.implementations;

import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.Command;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Element;

public class AddElementCommand implements Command {

    Element element;
    MapNode parent;

    public AddElementCommand(Element element, MapNode parent) {
        this.element = element;
        this.parent = parent;
    }

    @Override
    public void doCommand() {

        AppCore.getInstance().getMapRepository().addChild(parent,element);

    }

    @Override
    public void undoCommand() {
        AppCore.getInstance().getMapRepository().removeChild(element);
    }
}
