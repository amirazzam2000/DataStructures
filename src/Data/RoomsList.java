package Data;

import java.util.HashMap;
import java.util.Set;

public class RoomsList {
    private HashMap<Integer, Integer> adjacentRooms;
    public RoomsList(){
        adjacentRooms = new HashMap<Integer, Integer>(15,0.9f);
    }
    public void add(Integer key, Integer prob){
        adjacentRooms.put(key, prob);
    }


    public Integer get( int key){
        return adjacentRooms.get(key);
    }

    public int size() {
        return adjacentRooms.size();
    }

    public Set<Integer> keySet() {
        return adjacentRooms.keySet();
    }
}
