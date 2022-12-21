package dsw.gerumap.app.core;

import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import dsw.gerumap.app.mapRepository.implementation.Project;

import java.io.File;

public interface Serializer {
    Project loadMindMap(File file);
    void saveMindMap(MapNode node, String filePath);
}
