package dsw.gerumap.app.serializable;

import com.google.gson.*;
import dsw.gerumap.app.core.Serializer;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.mapRepository.implementation.subElements.PojamElement;
import dsw.gerumap.app.mapRepository.implementation.subElements.VezaElement;
import lombok.Getter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class GSonSerializer implements Serializer {
    private Gson gson = new Gson();

    private static HashMap<MapNode, List<Element>> mindMapElements = new HashMap<>();

    @Override
    public Project loadMindMap(File file) {
        try {
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder.excludeFieldsWithoutExposeAnnotation();
//            gson = gsonBuilder.create();
            FileReader fileReader = new FileReader(file);
            FileReader customFileReader = new FileReader(file);
            Project var3;
            try {
                var3 = (Project)this.gson.fromJson(fileReader, Project.class);

                JsonElement fileElement = JsonParser.parseReader(customFileReader);
                JsonObject fileObject = fileElement.getAsJsonObject();

                JsonArray jsonArrayOfMindMap = (JsonArray) fileObject.get("children");

                for (JsonElement mindMapJs: jsonArrayOfMindMap){

                    JsonObject mindMap  = mindMapJs.getAsJsonObject();
                    MindMap mp = this.gson.fromJson(mindMapJs.toString(), MindMap.class);
                    mindMapElements.put(mp,new ArrayList<>());

                    JsonArray jsonArrayOfElements = (JsonArray) mindMap.get("children");

                    for (JsonElement elementJs: jsonArrayOfElements){

                        if (elementJs.getAsJsonObject().has("X2")){
                            ArrayList<Element> elements1 = new ArrayList<>();
                            JsonArray pojmovi = (JsonArray) elementJs.getAsJsonObject().get("elements");
                            for(JsonElement p: pojmovi){
                                elements1.add(this.gson.fromJson(p.toString(), PojamElement.class));
                            }
                            VezaElement veza = this.gson.fromJson(elementJs.toString(),VezaElement.class);
                            veza.getElements().clear();
                            veza.getElements().addAll(elements1);
                            mindMapElements.get(mp).add(veza);

                        }
                        else {
                            PojamElement pojam = this.gson.fromJson(elementJs.toString(),PojamElement.class);
                            mindMapElements.get(mp).add(pojam);
                        }

                    }
                }

            } catch (Throwable var6) {
                try {
                    fileReader.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            fileReader.close();
            return var3;
        } catch (IOException var7) {
            var7.printStackTrace();
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

    public static HashMap<MapNode,List<Element>> getElements(){
        return mindMapElements;
    }
}
