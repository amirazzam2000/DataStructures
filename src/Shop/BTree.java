package Shop;

import java.awt.*;

public class BTree {
    public static Node root;

    //We would initialize the root, and this is the only attribute the tree would have

    public BTree(){
        BTree.root = new Node();
        BTree.root.isLeaf = true;
    }

    public void addItem(ShopObject item){
        insertObject(root, item);

        if(root.isFull()){
            //split root
            ShopObject middle = root.items[root.items.length/2];

            Node newRoot = new Node(middle);

            Node left = new Node();
            Node right = new Node();

            for (int i = 0; i < root.children.length; i++) {
                if (i < (root.items.length / 2)) {
                    left.children[i] = root.children[i];
                }else{
                    right.children[i - (root.items.length / 2)] = root.children[i];
                }
            }

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
            root.isLeaf = false;
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
            Node next = currentNode.children[0];
            int childPos =0;
            //check where we should go down
            for (int i = 0; i < currentNode.items.length; i++) {
                if(currentNode.items[i] == null){
                    next = currentNode.children[i];
                    childPos =i;
                    break;
                }else if(item.getPrice() < currentNode.items[i].getPrice()){
                    next = currentNode.children[i];
                    childPos =i;
                }
            }
            insertObject(next, item);

            // check child if full.
            if(currentNode.children[childPos].isFull()){
                // if yes, split child into 2 and recib middle value
                ShopObject middle = currentNode.children[childPos].items[currentNode.items.length/2];

                int index = addItemIntoNode(currentNode, middle);

                Node left = new Node();
                Node right = new Node();

                for (int i = 0; i < currentNode.children.length; i++) {
                    if (i < (currentNode.items.length / 2)) {
                        left.children[i] = currentNode.children[i];
                    }else{
                        right.children[i - (currentNode.items.length / 2)] = currentNode.children[i];
                    }
                }

                for (int i = 0; i < currentNode.items.length-1; i++) {

                    if(i <=(currentNode.items.length/2)-1 ){
                        left.items[i] = currentNode.items[i];
                    }
                    else{
                        right.items[i-(root.items.length/2)] = root.items[i+1];
                    }
                }

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

    private void deleteNode(){

    }
}
