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

        tree.delete(items[14]);
        tree.delete(items[3]);
        tree.delete(items[1]);
        tree.delete(items[6]);

        tree.delete(items[8]);

        tree.delete(items[4]);
        tree.delete(items[0]);
        tree.delete(items[7]);
        tree.delete(items[5]);
        tree.delete(items[9]);



        //tree.delete(items[15]); //left double merge
        //tree.delete(items[11]); //right double merge

        //tree.delete(items[2]);


        System.out.println(BTree.root);
    }
}
