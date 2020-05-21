package Hash;

import com.google.gson.annotations.SerializedName;

public class HashItem {
    @SerializedName("name")
    private String name;
    @SerializedName("KDA")
    private int kda;
    @SerializedName("Games")
    private int games;

    public String getName() {
        return name;
    }

    public int getKda() {
        return kda;
    }

    public int getGames() {
        return games;
    }
}
