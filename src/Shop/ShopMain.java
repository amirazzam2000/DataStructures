package Shop;

import java.util.Scanner;

public class ShopMain {
    public static void main(String[] args) {
        String shopfile = "data/dataset_ObjectS.json";

        ShopObjectManager sm = new ShopObjectManager(shopfile);

        ShopObject[] items = sm.getShopObjects();

        BTree tree = new BTree();

        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i].getPrice());
        }

        tree.addItem(items[0]);
        tree.addItem(items[1]);
        tree.addItem(items[2]);
        tree.addItem(items[3]);
        tree.addItem(items[4]);
        tree.addItem(items[5]);
        tree.addItem(items[6]);

        System.out.println(BTree.root);

        System.out.println("a");
    }
}
