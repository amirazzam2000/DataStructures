package Shop;

import Data.Connection;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ShopObjectManager {
    private ShopObject[] shopObjects;

    public ShopObjectManager(String filename){
        try {
            FileReader fileReader = new FileReader(filename);
            Gson gson = new Gson();
            shopObjects = gson.fromJson(fileReader, ShopObject[].class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ShopObject[] getShopObjects() {
        return shopObjects;
    }
}
