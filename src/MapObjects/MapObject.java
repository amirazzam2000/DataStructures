package MapObjects;

import com.google.gson.annotations.SerializedName;

public class MapObject extends Rectangle {


    public MapObject(Branch fatherNode ,int id, int x1, int y1, int x2,
                     int y2) {
        super(fatherNode,id, x1, y1, x2, y2);
    }

}
