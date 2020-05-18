package Hash;

import MapObjects.MapObject;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class HashItemManager {
    private HashItem[] hashItems;

    public HashItemManager(String fileSize) {
        try {
            String fileName;
            switch (fileSize){
                case "S":
                case "s":
                    fileName = "data/HashMapS.json";
                    break;
                case "M":
                case "m":
                    fileName = "data/HashMapM.json";
                    break;
                case "L":
                case "l":
                    fileName = "data/HashMapL.json";
                    break;
                default:
                    fileName = null;
            }
            if (fileName != null) {
                FileReader fileReader = new FileReader(fileName);
                Gson gson = new Gson();
                hashItems = gson.fromJson(fileReader, HashItem[].class);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HashItem[] getHashItems() {
        return hashItems;
    }
}
