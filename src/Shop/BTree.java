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
            boolean result = traverseDeletion(item, node.children[childPos], childPos);

            if(node.children[childPos].isEmpty()){

                int siblingPos =0;

                //get the sibling position
                for (int i = 0; i < node.children.length; i++) {
                    if(node.children[childPos] == node.children[i]){
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
                if(node.children[siblingPos].itemsInNode() >= 2){

                    //copy value in parent node to deleted value in child
                    node.children[childPos].items[0] = node.items[childPos-1];

                    //get the position from where we should get the item from the sibling
                    //we use it to place the item in the parent node
                    if(childPos !=0){
                        //get value from sibling into parent
                        //check what sibling pos is and get sibling based on that
                        if(childPos > siblingPos) {
                            node.items[childPos-1] = node.children[siblingPos].items[node.children[siblingPos].itemsInNode()-1];
                            //delete value from sibling
                            deleteItemFromLeaf(node.children[siblingPos], node.children[siblingPos].itemsInNode()-1);
                        }else{
                            node.items[childPos-1] = node.children[siblingPos].items[0];
                            //delete value from sibling
                            deleteItemFromLeaf(node.children[siblingPos], 0);
                        }
                    }else{
                        //get value from sibling into parent
                        //since the child is 0 (checked before), we can assume we need to get the first item in the sibling
                        node.items[childPos] = node.children[siblingPos].items[0];
                        //delete value from sibling
                        deleteItemFromLeaf(node.children[siblingPos], 0);
                    }

                    //If the node we took from had children, it will have one child too many now
                    //So we take the child that is closest to us from them
                    if(!node.children[childPos].hasNoChildren()){
                        // and add this child to the node what was empty
                        if(childPos > siblingPos){
                            // this means we need to add the child to position 0, but first shift the existing children one to the right

                            for (int i = node.children[childPos].children.length-2; i >0 ; i--) {
                                node.children[childPos].children[i] = node.children[childPos].children[i-1];
                            }

                            //add the child to pos 0 of the child
                            //Note the plus one, since this node has 1 less child than it used to --------------------------------------- here
                            node.children[childPos].children[0] = node.children[siblingPos].children[node.children[siblingPos].itemsInNode()+1];

                            //erase the child from the sibling node
                            node.children[siblingPos].children[node.children[siblingPos].itemsInNode()+1] = null;

                        }else{
                            //this means we need to add to the end of our node the 0th child from our sibling
                            node.children[childPos].addChildToNode(node.children[siblingPos].children[0]);

                            //then shift all the children in the sibling 1 pos to the left, overwriting the child we just stole
                            for (int i = 0; i < node.children[siblingPos].children.length-2; i++) {
                                node.children[siblingPos].children[i] = node.children[siblingPos].children[i+1];
                            }
                        }
                    }


                }else{
                    //else, merge

                    //add value in parent node to the sibling
                    addItemIntoNode(node.children[siblingPos], node.items[childPos-1]);
                    node.items[childPos-1] = null;

                    //if the child has no children
                    if(node.children[childPos].hasNoChildren()){
                        //set the empty node to null, since it is gone now due to the merge
                        node.children[childPos] = null;
                    }else{
                        //if it does, it is one child left from the merge,
                        // and add this child to the sibling

                        if(childPos > siblingPos){
                            //this means we have to add the child in our child to the sibling's last position
                            node.children[siblingPos].addChildToNode(node.children[childPos].children[0]);

                            //delete the remaining node
                            node.children[childPos] = null;
                        }else{
                            //this means that we have to add the child in our child to the first pos in our sibling
                            for (int i = node.children[childPos].children.length-2; i >0 ; i--) {
                                node.children[childPos].children[i] = node.children[childPos].children[i-1];
                            }

                            node.children[siblingPos].children[0] = node.children[childPos].children[0];
                        }
                    }


                    //shift children before and including childpos in parent to the left
                    for (int j = childPos; j < node.children.length-1; j++) {
                        node.children[j] = node.children[j+1];
                        node.children[j+1] = null;
                    }
                }
            }


            return result;
        }
    }

    //predecesor is removed once returned, and the tree is fixed on the "way up"
    private ShopObject getPredecesorAndDelete(Node node, int childPos, ShopObject delItem){
        int pred =0;

        if(node.isLeaf()){

            pred = node.itemsInNode()-1;

            ShopObject predecesor = node.items[pred];
            deleteItemFromLeaf(node, pred);
            return predecesor;

        }else{
            pred = node.itemsInNode();

            ShopObject predecesor = getPredecesorAndDelete(node.children[childPos], pred, delItem);

            //check if we need to merge/redistribute
            //first check if the child is empty
            if(node.children[childPos].isEmpty()){

                int siblingPos =0;

                //get the sibling position
                for (int i = 0; i < node.children.length; i++) {
                    if(node.children[childPos] == node.children[i]){
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
                if(node.children[siblingPos].itemsInNode() >= 2){

                    // check if the value at the parent isnt the one we want to delete,
                    // because if it is, we need to replace the parent with the predecesor
                    if(node.items[childPos] == delItem){
                        //copy value in parent node to deleted value in child
                        node.children[childPos].items[0] = predecesor;
                    }else{
                        //copy value in parent node to deleted value in child
                        node.children[childPos].items[0] = node.items[childPos-1];
                    }

                    //get the position from where we should get the item from the sibling
                    //we use it to place the item in the parent node
                    if(childPos !=0){
                        //get value from sibling into parent
                        //check what sibling pos is and get sibling based on that
                        if(childPos > siblingPos) {
                            node.items[childPos-1] = node.children[siblingPos].items[node.children[siblingPos].itemsInNode()-1];
                            //delete value from sibling
                            deleteItemFromLeaf(node.children[siblingPos], node.children[siblingPos].itemsInNode()-1);
                        }else{
                            node.items[childPos-1] = node.children[siblingPos].items[0];
                            //delete value from sibling
                            deleteItemFromLeaf(node.children[siblingPos], 0);
                        }
                    }else{
                        //get value from sibling into parent
                        //since the child is 0 (checked before), we can assume we need to get the first item in the sibling
                        node.items[childPos] = node.children[siblingPos].items[0];
                        //delete value from sibling
                        deleteItemFromLeaf(node.children[siblingPos], 0);
                    }

                    //If the node we took from had children, it will have one child too many now
                    //So we take the child that is closest to us from them
                    if(!node.children[childPos].hasNoChildren()){
                        // and add this child to the node what was empty
                        if(childPos > siblingPos){
                            // this means we need to add the child to position 0, but first shift the existing children one to the right

                            for (int i = node.children[childPos].children.length-2; i >0 ; i--) {
                                node.children[childPos].children[i] = node.children[childPos].children[i-1];
                            }

                            //add the child to pos 0 of the child
                            //Note the plus one, since this node has 1 less child than it used to --------------------------------------- here
                            node.children[childPos].children[0] = node.children[siblingPos].children[node.children[siblingPos].itemsInNode()+1];

                            //erase the child from the sibling node
                            node.children[siblingPos].children[node.children[siblingPos].itemsInNode()+1] = null;

                        }else{
                            //this means we need to add to the end of our node the 0th child from our sibling
                            node.children[childPos].addChildToNode(node.children[siblingPos].children[0]);

                            //then shift all the children in the sibling 1 pos to the left, overwriting the child we just stole
                            for (int i = 0; i < node.children[siblingPos].children.length-2; i++) {
                                node.children[siblingPos].children[i] = node.children[siblingPos].children[i+1];
                            }
                        }
                    }


                }else{
                    //else, merge

                    // check if the value at the parent isnt the one we want to delete,
                    // because if it is, we need to add value in parent to the sibling
                    if(node.items[childPos] == delItem){
                        //add value in parent node to the sibling
                        addItemIntoNode(node.children[siblingPos], predecesor);
                        node.items[childPos] = null;
                    }else{
                        //add value in parent node to the sibling
                        addItemIntoNode(node.children[siblingPos], node.items[childPos-1]);
                        node.items[childPos-1] = null;
                    }

                    //if the child has no children
                    if(node.children[childPos].hasNoChildren()){
                        //set the empty node to null, since it is gone now due to the merge
                        node.children[childPos] = null;
                    }else{
                        //if it does, it is one child left from the merge,
                        // and add this child to the sibling

                        if(childPos > siblingPos){
                            //this means we have to add the child in our child to the sibling's last position
                            node.children[siblingPos].addChildToNode(node.children[childPos].children[0]);

                            //delete the remaining node
                            node.children[childPos] = null;
                        }else{
                            //this means that we have to add the child in our child to the first pos in our sibling
                            for (int i = node.children[childPos].children.length-2; i >0 ; i--) {
                                node.children[childPos].children[i] = node.children[childPos].children[i-1];
                            }

                            node.children[siblingPos].children[0] = node.children[childPos].children[0];
                        }
                    }

                    //shift children before and including childpos in parent to the left
                    for (int j = childPos; j < node.children.length-1; j++) {
                        node.children[j] = node.children[j+1];
                        node.children[j+1] = null;
                    }
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
