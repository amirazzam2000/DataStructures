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
        tree.addItem(items[2]);
        tree.addItem(items[3]);
        tree.addItem(items[4]);

        System.out.println(BTree.root);

        System.out.println("a");
    }
}
