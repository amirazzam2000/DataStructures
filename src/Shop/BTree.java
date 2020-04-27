package Shop;

public class BTree {
    public static Node root;

    //We would initialize the root, and this is the only attribute the tree would have

    public BTree(){
        BTree.root = new Node();
    }

    public void addItem(ShopObject item){
        insertObject(root, item);

        //check if root full
        if(root.isFull()){

            //split root
            ShopObject middle = root.items[root.items.length/2];

            //new root node
            Node newRoot = new Node(middle);

            //new children
            Node left = new Node();
            Node right = new Node();

            //assign the children to the left and right nodes
            for (int i = 0; i < root.children.length; i++) {
                if (i < (root.children.length / 2)) {
                    left.children[i] = root.children[i];
                }else{
                    right.children[i - (root.children.length / 2)] = root.children[i];
                }
            }

            //assign the items to the left and right nodes
            for (int i = 0; i < root.items.length-1; i++) {

                if(i <=(root.items.length/2)-1 ){
                    left.items[i] = root.items[i];
                }
                else{
                    right.items[i-(root.items.length/2)] = root.items[i+1];
                }
            }

            newRoot.children[0] = left;
            newRoot.children[1] = right;

            root = newRoot;
        }
    }

    private void insertObject(Node currentNode, ShopObject item) {

        //get node
        //check all values in node
        //check if the current Node is leaf

        if(currentNode.isLeaf()){
            //we check where this value should be inserted and insert it
            addItemIntoNode(currentNode, item);

        } else{
            int childPos = 0;
            //check where we should go down
            for (int i = 0; i < currentNode.items.length; i++) {
                if(currentNode.items[i] == null){
                    break;
                }else if(item.getPrice() > currentNode.items[i].getPrice()){
                    childPos =i+1;
                }
            }
            insertObject(currentNode.children[childPos], item);

            // check child if full.
            if(currentNode.children[childPos].isFull()){
                // if yes, split child into 2 and recib middle value
                ShopObject middle = currentNode.children[childPos].items[currentNode.items.length/2];

                int index = addItemIntoNode(currentNode, middle);

                Node left = new Node();
                Node right = new Node();

                //assign the children to the left and right nodes
                for (int i = 0; i < currentNode.children.length; i++) {
                    if (i < (currentNode.children.length / 2)) {
                        left.children[i] = currentNode.children[childPos].children[i];
                    }else{
                        right.children[i - (currentNode.children.length / 2)] = currentNode.children[childPos].children[i];
                    }
                }

                //assign the items to the left and right nodes
                for (int i = 0; i < currentNode.items.length-1; i++) {

                    if(i <=(currentNode.items.length/2)-1){
                        left.items[i] = currentNode.children[childPos].items[i];
                    }
                    else{
                        right.items[i-(currentNode.items.length/2)] = currentNode.children[childPos].items[i+1];
                    }
                }

                //move all the previous children 1 place to the right, from where we have
                //inserted the item from the child
                for (int j = currentNode.children.length-2; j >= index; j--) {
                    currentNode.children[j+1] = currentNode.children[j];
                }

                //assign the children
                currentNode.children[index] = left;
                currentNode.children[index+1] = right;
            }
        }
    }

    private int addItemIntoNode(Node node, ShopObject item){

        //if no item there
        if(node.items[0] == null){
            node.items[0] = item;
            return 0;

        //if items already there
        } else{
            int index = 1;
            for (int i = 0; i < node.items.length; i++) {

                if(node.items[i] == null){
                    node.items[i] = item;
                    return i;

                } else if(node.items[i].getPrice() > item.getPrice()){

                    //shift all other items
                    for (int j = node.items.length-2; j >= i; j--) {
                        node.items[j+1] = node.items[j];
                    }
                    //insert item into i+1 (where we started shifting the items)
                    node.items[i] = item;
                    return i;
                }
            }
            return index;
        }
    }

    public Node search(ShopObject item){
        return traverse(item, root);
    }

    //returns node
    private Node traverse(ShopObject item, Node node){

        int childPos = 0;

        for (int i = 0; i < node.items.length; i++) {
            if(node.items[i] == item){

                return node;
            }else if(node.items[i] != null && node.items[i].getPrice() < item.getPrice()){
                childPos = i+1;
            }
        }

        if(node.isLeaf()){
            return null;
        }else{
            return traverse(item, node.children[childPos]);
        }
    }


    public boolean delete(ShopObject item){
        return traverseDeletion(item, root, -1);
    }

    private boolean traverseDeletion(ShopObject item, Node node, int parentPos){

        int childPos = 0;

        for (int i = 0; i < node.items.length; i++) {
            if(node.items[i] == item){
                //if we found the item, perform the deletion

                //if it is not a leaf
                if(!node.isLeaf()){
                    ShopObject predecesor = getPredecesorAndDelete(node, i, item);

                    //if value hasnt been replaced yet
                    if(node.items[i] == item){
                        node.items[i] = predecesor;
                    }

                }else{
                    deleteItemFromLeaf(node, i);
                }
                return true;

            }else if(node.items[i] != null && node.items[i].getPrice() < item.getPrice()){
                childPos = i+1;
            }
        }

        if(node.isLeaf()){
            return false;
        }else{
            return traverseDeletion(item, node.children[childPos], childPos);
            //check if the node we are at is empty / has too few children, etc.

        }
    }

    //predecesor is removed once returned, and the tree is fixed on the "way up"
    private ShopObject getPredecesorAndDelete(Node node, int childIndex, ShopObject delItem){
        int pred =0;

        if(node.isLeaf()){

            pred = itemsInNode(node)-1;

            ShopObject predecesor = node.items[pred];
            deleteItemFromLeaf(node, pred);
            return predecesor;

        }else{
            pred = itemsInNode(node);

            ShopObject predecesor = getPredecesorAndDelete(node.children[childIndex], pred, delItem);

            //check if we need to merge/redistribute
            //first check if the child is empty
            if(node.children[childIndex].isEmpty()){

                int siblingPos =0;

                for (int i = 0; i < node.children.length; i++) {
                    if(node.children[childIndex] == node.children[i]){
                        if(i == Node.MAX_ORDER-1){
                            siblingPos = Node.MAX_ORDER-2;
                            break;
                        }else if(i == 0){
                            siblingPos = 1;
                            break;
                        }else{
                            siblingPos = i-1;
                            break;
                        }
                    }
                }

                //if sibling has 2 or more items, redistribute
                if(itemsInNode(node.children[siblingPos]) >= 2){

                    // check if the value at the parent isnt the one we want to delete,
                    // because if it is, we need to replace the parent with the predecesor
                    if(node.items[childIndex] == delItem){
                        //copy value in parent node to deleted value in child
                        node.children[childIndex].items[0] = predecesor;
                    }else{
                        //copy value in parent node to deleted value in child
                        node.children[childIndex].items[0] = node.items[childIndex-1];
                    }

                    //check child index value
                    //we use it to place the item in the parents
                    if(childIndex !=0){
                        //get value from sibling into parent
                        //check what sibling pos is and get sibling based on that
                        if(siblingPos == 0) {
                            node.items[childIndex-1] = node.children[siblingPos].items[itemsInNode(node.children[siblingPos])-1];
                            //delete value from sibling
                            deleteItemFromLeaf(node.children[siblingPos], itemsInNode(node.children[siblingPos])-1);
                        }else{
                            node.items[childIndex-1] = node.children[siblingPos].items[0];
                            //delete value from sibling
                            deleteItemFromLeaf(node.children[siblingPos], 0);
                        }
                    }else{
                        //get value from sibling into parent
                        //check what sibling pos is and get sibling based on that
                        if(siblingPos == 0) {
                            node.items[childIndex] = node.children[siblingPos].items[itemsInNode(node.children[siblingPos])-1];
                            //delete value from sibling
                            deleteItemFromLeaf(node.children[siblingPos], itemsInNode(node.children[siblingPos])-1);
                        }else{
                            node.items[childIndex] = node.children[siblingPos].items[0];
                            //delete value from sibling
                            deleteItemFromLeaf(node.children[siblingPos], 0);
                        }
                    }


                }else{
                    //else, merge
                    System.out.println("a");
                }
            }

            return predecesor;
        }
    }

    private void deleteItemFromLeaf(Node node, int itemPos){
        //set item to null (delete it)
        node.items[itemPos] = null;

        //shift other items (if not empty after that)
        if(!node.isEmpty()){
            for (int j = itemPos; j < node.items.length-1; j++) {
                node.items[j] = node.items[j+1];
                node.items[j+1] = null;
            }
        }

    }

    private int itemsInNode(Node node){

        int num_items=0;

        while (node.items[num_items] != null){
            num_items++;
        }
        return num_items;
    }

    //precondition is to pass this function the correct child node of the item's node we are looking for
    //successor removed once it is returned
    /*
    private ShopObject getSucc(Node node) {
        if(!node.isLeaf()){
            return getSucc(node.children[0]);

        }else{
            ShopObject succ = node.items[0];
            node.items[0] = null;

            //if the remaining node is not empty, shift the items to the right
            if(!node.isEmpty()){
                for (int j = 0; j < node.items.length-1; j++) {
                    node.items[j] = node.items[j+1];
                    node.items[j+1] = null;
                }
            }
        }
    }

     */

}
