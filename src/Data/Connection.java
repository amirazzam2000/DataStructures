package Data;

public class Connection {
    private int id;
    private String connection_name;
    private int[] room_connected;
    private int enemy_probability;

    public Connection(int id, String connection_name, int[] room_connected, int enemy_probability) {
        this.id = id;
        this.connection_name = connection_name;
        this.room_connected = room_connected;
        this.enemy_probability = enemy_probability;
    }

    public int getId() {
        return id;
    }

    public String getConnection_name() {
        return connection_name;
    }

    public int[] getRoom_connected() {
        return room_connected;
    }

    public int getEnemy_probability() {
        return enemy_probability;
    }
}
