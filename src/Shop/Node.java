package Shop;

//For implementing a B-tree of order MAX_ORDER
class Node {
    private static final int MAX_ORDER = 3;

    public ShopObject[] items = new ShopObject[MAX_ORDER];

    public Node leftChild;
    public Node middleChild;
    public Node rightChild;

    //would we need this since we only have root as the class variable in BTree?
    public boolean isRoot = false;

    public Node(boolean isRoot){
        leftChild = null;
        middleChild = null;
        rightChild = null;

        this.isRoot = isRoot;

        for (int i = 0; i < MAX_ORDER-1; i++) {
            items[i] = null;
        }
    }

    public boolean isRoot() {
        return isRoot;
    }

    public boolean isFull(){
        for (int i = 0; i < MAX_ORDER; i++) {
            if(items[i] == null){
                return false;
            }
        }
        return true;
    }



}

