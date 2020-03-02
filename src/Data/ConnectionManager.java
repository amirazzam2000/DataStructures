package Data;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConnectionManager {
    private Connection[] connections;

    public ConnectionManager(String file_name){
        try {
            FileReader fileReader = new FileReader(file_name);
            Gson gson = new Gson();
            connections = gson.fromJson(fileReader, Connection[].class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
