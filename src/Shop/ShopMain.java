package Shop;

import java.util.Scanner;

public class ShopMain {
    public static void main(String[] args) {
        String shopfile = "data/dataset_ObjectS.json";

        ShopObjectManager sm = new ShopObjectManager(shopfile);

        ShopObject[] items = sm.getShopObjects();

        BTree tree = new BTree();

        tree.addItem(items[0]);
        tree.addItem(items[1]);

        System.out.println("a");
    }
}
