package Shop;

import java.util.Scanner;

public class ShopMain {
    public static void main(String[] args) {
        String shopfile = "data/dataset_ObjectS.json";
        Scanner sc = new Scanner(System.in);

        ShopObjectManager sm = new ShopObjectManager(shopfile);

        ShopObject[] items = sm.getShopObjects();
    }
}
