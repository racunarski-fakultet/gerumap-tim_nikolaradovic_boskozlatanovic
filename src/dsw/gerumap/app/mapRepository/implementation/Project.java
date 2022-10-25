package dsw.gerumap.app.mapRepository.implementation;

import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Project extends MapNodeComposite {

    private String ime;

    private String autor;

    private String putanjaResursi;


}
