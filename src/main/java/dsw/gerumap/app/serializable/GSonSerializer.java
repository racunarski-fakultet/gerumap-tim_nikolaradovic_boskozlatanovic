package dsw.gerumap.app.serializable;

import com.google.gson.Gson;
import dsw.gerumap.app.core.Serializer;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import dsw.gerumap.app.mapRepository.implementation.Project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GSonSerializer implements Serializer {
    private final Gson gson = new Gson();
    @Override
    public MindMap loadMindMap(File file) {
        try(FileReader fileReader = new FileReader(file)){
            return gson.fromJson(fileReader, MindMap.class);

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveMindMap(MapNode node, String filePath) {
        try(FileWriter fileWriter = new FileWriter(filePath)){
           gson.toJson(node, fileWriter);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
