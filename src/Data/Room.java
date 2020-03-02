package Data;

public class Room {
    private int id;
    private String room_name;

    public Room(int id, String room_name) {
        this.id = id;
        this.room_name = room_name;
    }

    public int getId() {
        return id;
    }

    public String getRoom_name() {
        return room_name;
    }
}
