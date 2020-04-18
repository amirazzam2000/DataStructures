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
    }

    private void insertObject(Node currentNode, ShopObject item) {

        //get node
        //check all values in node
        //check if the current Node is leaf

        if(currentNode.isLeaf()){
            //we check where this value should be inserted and insert it

            //if no item there
            if(currentNode.items[0] == null){
                currentNode.items[0] = item;

            //if items already there
            } else{

                for (int i = 0; i < currentNode.items.length; i++) {

                    if(currentNode.items[i] == null){
                        currentNode.items[i] = item;
                        break;
                    } else if(currentNode.items[i].getPrice() > item.getPrice()){

                        //shift all other items
                        for (int j = currentNode.items.length-2; j >= i; j--) {
                            currentNode.items[j+1] = currentNode.items[j];
                        }
                        //insert item into i+1 (where we started shifting the items)
                        currentNode.items[i] = item;
                        break;
                    }

                }
            }

        } else{
            Node next = currentNode.children[0];

            //check where we should go down
            for (int i = 0; i < currentNode.items.length; i++) {
                if(currentNode.items[i] == null){
                    next = currentNode.children[i];
                    break;
                }else if(item.getPrice() < currentNode.items[i].getPrice()){
                    next = currentNode.children[i];
                }
            }

            insertObject(next, item);
        }

        // once we come back up, we reorder the values based on where we inserted the value (on the int j, check the princeton code, you understood (Felipe))
        // ++ to the order of the current node
        // if the node is now full, break it
    }

    private void organizeTree(Node currentNode){

        /*

        //need recursion

        //base case
        if(isLeaf()){
            if(currentNode.isFull()){
                //pull up middle boi into parent node
            }
        }
        //recursive case
        else{
            if(currentNode.isFull()){

                if(currentNode.isRoot()){

                    //create new node, pull up middle boi into new node, organize children blah blah blah

                }
                else{

                    //pull up middle boi into parent node

                }
            }
            else{
                organizeTree(childNode);
            }
        }
        */
    }

    private void deleteNode(){

    }
}
