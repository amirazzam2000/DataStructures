package Shop;

public class BTree {
    public static Node root;

    //We would initialize the root, and this is the only attribute the tree would have

    public BTree(Node root){



    }

    public void addItem(ShopObject item){
        insertObject(root, item);
    }

    public void insertObject(Node currentNode,ShopObject item) {

        //get root
        //check all values in root
        //if
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
