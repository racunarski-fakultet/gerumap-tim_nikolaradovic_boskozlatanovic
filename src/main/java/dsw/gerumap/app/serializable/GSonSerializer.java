package dsw.gerumap.app.serializable;

import com.google.gson.*;
import dsw.gerumap.app.core.Serializer;
import dsw.gerumap.app.mapRepository.commands.CommandManager;
import dsw.gerumap.app.mapRepository.composite.MapNode;
import dsw.gerumap.app.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.mapRepository.implementation.Element;
import dsw.gerumap.app.mapRepository.implementation.MindMap;
import dsw.gerumap.app.mapRepository.implementation.Project;
import dsw.gerumap.app.mapRepository.implementation.subElements.PojamElement;
import dsw.gerumap.app.mapRepository.implementation.subElements.VezaElement;
import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class GSonSerializer implements Serializer {
    private Gson gson = new Gson();

    private static HashMap<MapNode, List<Element>> mindMapElements = new HashMap<>();

    @Override
    public Project loadProject(File file) {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.addDeserializationExclusionStrategy(new HiddenAnnotationExclusionStrategy());
            gson = gsonBuilder.create();
            FileReader fileReader = new FileReader(file);
            FileReader customFileReader = new FileReader(file);
            Project var3;
            try {
                var3 = (Project)this.gson.fromJson(fileReader, Project.class);

                var3.setChildren(new ArrayList<>());

               loadMindMap(customFileReader);

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

    private void loadMindMap(FileReader customFileReader){
        mindMapElements = new HashMap<>();

        JsonElement fileElement = JsonParser.parseReader(customFileReader);
        JsonObject fileObject = fileElement.getAsJsonObject();

        JsonArray jsonArrayOfMindMap = (JsonArray) fileObject.get("children");

        for (JsonElement mindMapJs: jsonArrayOfMindMap){

            JsonObject mindMap  = mindMapJs.getAsJsonObject();
            MindMap mp = this.gson.fromJson(mindMapJs.toString(), MindMap.class);
            mp.setChildren(new ArrayList<>());
            mp.setCommandManager(new CommandManager());
            mindMapElements.put(mp,new ArrayList<>());

            JsonArray jsonArrayOfElements = (JsonArray) mindMap.get("children");

            for (JsonElement elementJs: jsonArrayOfElements){

                if (elementJs.getAsJsonObject().has("X2")){
                    ArrayList<Element> elements1 = new ArrayList<>();
                    JsonArray pojmovi = (JsonArray) elementJs.getAsJsonObject().get("elements");
                    for(JsonElement p: pojmovi){
                        Element pojam = this.gson.fromJson(p.toString(), PojamElement.class);
                        pojam.setChildren(new ArrayList<>());
                        elements1.add(pojam);
                    }
                    VezaElement veza = this.gson.fromJson(elementJs.toString(),VezaElement.class);
                    veza.setChildren(new ArrayList<>());
                    veza.getElements().clear();
                    veza.getElements().addAll(elements1);
                    mindMapElements.get(mp).add(veza);

                }
                else {
                    PojamElement pojam = this.gson.fromJson(elementJs.toString(),PojamElement.class);
                    pojam.setChildren(new ArrayList<>());
                    mindMapElements.get(mp).add(pojam);
                }

            }
        }
    }

    public void loadMindMap(File file) throws FileNotFoundException {
        mindMapElements = new HashMap<>();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.addDeserializationExclusionStrategy(new HiddenAnnotationExclusionStrategy());
        gson = gsonBuilder.create();
        FileReader fileReader = new FileReader(file);

        JsonElement mindMap = JsonParser.parseReader(fileReader);

        JsonObject mindMapObject = mindMap.getAsJsonObject();

        MindMap mp = this.gson.fromJson(mindMap.toString(), MindMap.class);
        mp.setChildren(new ArrayList<>());
        mp.setCommandManager(new CommandManager());
        mindMapElements.put(mp,new ArrayList<>());

        JsonArray jsonArrayOfElements = (JsonArray) mindMapObject.get("children");

        for (JsonElement elementJs: jsonArrayOfElements){

            if (elementJs.getAsJsonObject().has("X2")){
                ArrayList<Element> elements1 = new ArrayList<>();
                JsonArray pojmovi = (JsonArray) elementJs.getAsJsonObject().get("elements");
                for(JsonElement p: pojmovi){
                    Element pojam = this.gson.fromJson(p.toString(), PojamElement.class);
                    pojam.setChildren(new ArrayList<>());
                    elements1.add(pojam);
                }
                VezaElement veza = this.gson.fromJson(elementJs.toString(),VezaElement.class);
                veza.setChildren(new ArrayList<>());
                veza.getElements().clear();
                veza.getElements().addAll(elements1);
                mindMapElements.get(mp).add(veza);

            }
            else {
                PojamElement pojam = this.gson.fromJson(elementJs.toString(),PojamElement.class);
                pojam.setChildren(new ArrayList<>());
                mindMapElements.get(mp).add(pojam);
            }

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
