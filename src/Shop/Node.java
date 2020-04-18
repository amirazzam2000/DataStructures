package Shop;

import java.util.Arrays;

//For implementing a B-tree of order MAX_ORDER
class Node {
    private static final int MAX_ORDER = 3;

    public ShopObject[] items = new ShopObject[MAX_ORDER];

    public Node[] children = new Node[MAX_ORDER];

    public boolean isLeaf = true;

    public Node(){

        Arrays.fill(children, null);

        for (int i = 0; i < MAX_ORDER-1; i++) {
            items[i] = null;
        }
    }

    public boolean isFull(){
        return items[MAX_ORDER-1] == null;
    }


    public boolean isLeaf() {
        return this.isLeaf;
    }
}

