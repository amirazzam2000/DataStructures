package MapObjects;

public class RTree {
    private Node root;

    public RTree(int order) {
        this.root = new Node(order);
    }

    public boolean addObject(MapObject object){
        if(!root.addChild(object)){

        }
        return true;
    }

    /*
    * Steps to insert:
    *   - find the closest Branch to the new object
    *   - try to add the object (branch.getNode().addChild) -> if it fits
    *           done. else :
    *       - break the leaf Branch (store all leaves in an array[4]).
    *       - delete the branch that contains the node.
    *       - create two new Branch.
    *       - divide the objects between the branches
    *           (find the two relatively far away rectangle, then make them the
    *               first rectangle in the nodes then start from there)
    *       - for each rectangle left check to whom its closer and add it there
    *       - add the branches to the FATHER node (remember to run the resize
    *               after each add)
    *       - repeat the process with the new Branch on the higher level
    *       - if it doesn't fit do the process and add them to the CURRENT node
    *
    * */
}
