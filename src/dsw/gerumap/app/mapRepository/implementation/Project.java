package dsw.gerumap.app.mapRepository.implementation;

import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Project extends MapNodeComposite {

    private String ime;

    private String autor;

    private String putanjaResursi;

    @Override
    public void addChildren(MapNode child) {
        if (child instanceof MindMap){

            super.addChildren(child);

        }

    }

    @Override
    public void removeChildren(MapNode child) {
        if (child instanceof MindMap){

            super.removeChildren(child);
        }


    }
}
