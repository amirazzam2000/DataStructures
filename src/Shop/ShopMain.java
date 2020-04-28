package Shop;

import java.util.Scanner;

public class ShopMain {
    public static void main(String[] args) {
        String shopfile = "data/dataset_ObjectS.json";

        ShopObjectManager sm = new ShopObjectManager(shopfile);

        ShopObject[] items = sm.getShopObjects();

        BTree tree = new BTree();

        for (int i = 0; i < items.length; i++) {
            System.out.println("Inserting item: " + items[i].getPrice());
            tree.addItem(items[i]);
        }

        Node found = (tree.search(items[7]));

        tree.delete(items[7]);
        tree.delete(items[11]);

        System.out.println(BTree.root);
    }
}
