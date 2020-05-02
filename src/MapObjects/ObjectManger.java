package MapObjects;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ObjectManger {
    private MapObject[] objects;

    public ObjectManger(){
        try {
            FileReader fileReader = new FileReader("data/dataset_MapS.json");
            Gson gson = new Gson();
            objects = gson.fromJson(fileReader, MapObject[].class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addCenters(){
        for (MapObject o: objects) {
            o.addCenter();
        }
    }

    public MapObject getObjectsById(int id){

        return objects[id - 1];
    }

    public MapObject[] getObjects() {
        return objects;
    }
}
