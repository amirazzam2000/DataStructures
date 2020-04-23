package Shop;

import java.util.Arrays;

//For implementing a B-tree of order MAX_ORDER
class Node {
    public static final int MAX_ORDER = 3;

    public ShopObject[] items = new ShopObject[MAX_ORDER];

    public Node[] children = new Node[MAX_ORDER+1];

    public Node(){

        Arrays.fill(children, null);

        for (int i = 0; i < MAX_ORDER-1; i++) {
            items[i] = null;
        }
    }

    public Node(ShopObject item){
        Arrays.fill(children, null);

        items[0] = item;

        for (int i = 1; i < MAX_ORDER-1; i++) {
            items[i] = null;
        }
    }

    public boolean isFull(){
        return items[MAX_ORDER-1] != null;
    }

    public void makeLeftChild(ShopObject[] items, Node[] children){
        for (int i = 0; i < items.length; i++) {
            this.items[i] = items[i];
        }
    }

    public boolean isLeaf() {
        boolean isLeaf = true;

        for (int i = 0; i < this.children.length; i++) {
            if(this.children[i] != null){
                return false;
            }
        }
        return isLeaf;
    }
}

