package Data;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class RoomManager {
    private Room[] rooms;

    public RoomManager(String file_name){
        try {
            FileReader fileReader = new FileReader(file_name);
            Gson gson = new Gson();
            rooms = gson.fromJson(fileReader, Room[].class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Room[] getRooms() {
        return rooms;
    }
}
