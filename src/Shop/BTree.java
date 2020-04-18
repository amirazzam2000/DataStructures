package Shop;

import java.awt.*;

public class BTree {
    public static Node root;

    //We would initialize the root, and this is the only attribute the tree would have

    public BTree(Node root){
        this.root = new Node(true);
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
                    if(currentNode.items[i].getPrice() > item.getPrice()){
                        ShopObject temp = currentNode.items[i];
                        currentNode.items[i] = item;

                        //shift all other items
                        for (int j = currentNode.items.length-1; j > i+1; j--) {
                            currentNode.items[j+1] = currentNode.items[j];
                        }

                        //insert old item into i+1
                        currentNode.items[i+1] = temp;
                    }
                }
            }

        } else{
            //we check what node we should go to next, and save this value somehow
            // (for example, if we go to the middle child, we save j = 1)
            // we go down and insert it
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
