package Shop;


//For implementing a binary or red-black tree
//would either have an int for order or a boolean for color
public class Node {
    public ShopObject item;
    public Node leftChild;
    public Node rightChild;

    public boolean isRoot = false;

    public Node(ShopObject item, boolean isRoot){
        this.item = item;

        this.isRoot = isRoot;

        leftChild = null;
        rightChild = null;
    }

}

//For implementing a B-tree, initial idea was of making it of order 3 (what we have now)
class NodeB {
    private static final int MAX_ORDER = 3;

    public ShopObject[] items = new ShopObject[MAX_ORDER-1];

    public Node leftChild;
    public Node middleChild;
    public Node rightChild;

    //would we need this since we only have root as the class variable in BTree?
    public boolean isRoot = false;

    public NodeB(boolean isRoot){
        leftChild = null;
        middleChild = null;
        rightChild = null;

        this.isRoot = isRoot;

        /*
                 r

              [34,45]
         10,12  42  55,69

         */


        for (int i = 0; i < MAX_ORDER-1; i++) {
            items[i] = null;
        }
    }

}

