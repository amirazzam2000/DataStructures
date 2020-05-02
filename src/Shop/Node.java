package Shop;

import java.util.Arrays;

//For implementing a B-tree of order MAX_ORDER
class Node {
    public static final int MAX_ORDER = 3;

    public ShopObject[] items = new ShopObject[MAX_ORDER];

    public Node[] children = new Node[MAX_ORDER+1];

    public Node(){

        Arrays.fill(children, null);

        Arrays.fill(items, null);
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

    public boolean isEmpty(){
        boolean isEmpty = true;

        for (int i = 0; i < this.items.length; i++) {
            if(this.items[i] != null){
                return false;
            }
        }
        return isEmpty;
    }

    public boolean hasNoChildren(){
        boolean hasChildren = true;

        for (int i = 0; i < this.children.length; i++) {
            if(this.children[i] != null){
                return false;
            }
        }
        return hasChildren;
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

    public void addChildToNode(Node child){
        this.children[this.itemsInNode()] = child;
    }

    public int itemsInNode(){

        int num_items=0;

        while (this.items[num_items] != null){
            num_items++;
        }
        return num_items;
    }
}

