package Shop;

public class BTree {
    public static Node root;

    //We would initialize the root, and this is the only attribute the tree would have

    public BTree(Node root){

    }

    public void addItem(ShopObject item){
        insertObject(root, item);
    }

    public void insertObject(Node currentNode, ShopObject item) {

        //get root
        //check all values in root
        //check if the current Node is leaf

        if(currentNode.isLeaf()){
            //we check where this value should be inserted and insert it

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
